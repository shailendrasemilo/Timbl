package com.np.tele.crm.sla.engine.threads;

import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMCapClient;
import com.np.tele.crm.client.CRMReportsClient;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CRMReportService;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmSlaLogPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.sla.engine.constants.CRMSLAConstants;
import com.np.tele.crm.sla.engine.constants.IApplicationConstants;
import com.np.tele.crm.sla.engine.utils.SLACommonUtils;
import com.np.tele.crm.sla.engine.utils.SLAPropertyUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SLACalculationUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SlaInaThread
    implements Runnable
{
    private static final Logger LOGGER                  = Logger.getLogger( SlaInaThread.class );
    private SLACalculationUtil  slaCalculationUtil      = null;
    private int                 threadSleepTimeInMinute = 15;
    private boolean             isInaBPToAMalert        = false;
    private String              inaBPToAMalertUnit;
    private int                 inaBPToAMalertHours;
    private boolean             isInaBPToCHalert        = false;
    private String              inaBPToCHalertUnit;
    private int                 inaBPToCHalertHours;

    public SlaInaThread( Properties inProperties, SLACalculationUtil inSLACalculationUtil )
    {
        slaCalculationUtil = inSLACalculationUtil;
        threadSleepTimeInMinute = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.CRM_SLA_THREAD_SLEEP_TIME_MINUTE,
                                                           threadSleepTimeInMinute );
        isInaBPToAMalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_INA_BP_TO_AM_ALERT_ACTIVE,
                                                        isInaBPToAMalert );
        isInaBPToCHalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_INA_BP_TO_CH_ALERT_ACTIVE,
                                                        isInaBPToCHalert );
        if ( isInaBPToAMalert || isInaBPToCHalert )
        {
            inaBPToAMalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_INA_BP_TO_AM_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_DAYS.getValue() );
            inaBPToAMalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_INA_BP_TO_AM_ALERT_HOURS,
                                                           CRMSLAConstants.INA_BP_AM_ALERT.getSlaTimeInHours() );
            inaBPToCHalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_INA_BP_TO_CH_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_DAYS.getValue() );
            inaBPToCHalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_INA_BP_TO_CH_ALERT_HOURS,
                                                           CRMSLAConstants.INA_BP_CH_ALERT.getSlaTimeInHours() );
        }
    }

    @Override
    public void run()
    {
        while ( true )
        {
            if ( isInaBPToAMalert || isInaBPToCHalert )
            {
                LOGGER.info( "SLA processing start for Business Partner stage. " );
                processInaSlaForBP( CRMRequestType.INA.getRequestCode(), CRMOperationStages.INITIATE.getCode() );
            }
            try
            {
                Thread.sleep( threadSleepTimeInMinute * 60 * 1000 );
            }
            catch ( InterruptedException ex )
            {
                LOGGER.error( "Exception while sleeping the INA thread", ex );
            }
        }
    }

    private void processInaSlaForBP( String inModule, String inFromStage )
    {
        LOGGER.info( "Inside SlaInaThread, processInaSlaForBP" );
        ListIterator<CrmCustomerDetailsPojo> listIterator = null;
        CRMReportService crmReportService = null;
        CrmCapService crmCapService = null;
        CrmSlaLogPojo slaLogPojo = null;
        List<CrmCustomerDetailsPojo> customerDetailsPojos = null;
        ReportDto reportDto = null;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        CrmCapDto crmCapDto = null;
        try
        {
            crmReportService = new CRMReportsClient();
            crmCapService = new CRMCapClient();
            reportDto = new ReportDto();
            customerDetailsPojo = new CrmCustomerDetailsPojo();
            crmCapDto = new CrmCapDto();
            customerDetailsPojo.setCrfStage( inFromStage );
            customerDetailsPojo.setStatus( CRMStatusCode.ACTIVATION_PENDING.getStatusCode() );
            crmCapDto.setCustomerDetailsPojo( customerDetailsPojo );
            crmCapDto = crmCapService.crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                       CRMRequestType.CAF.getRequestCode(), crmCapDto ); //
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
            {
                customerDetailsPojos = crmCapDto.getCustomerDetailsPojosList();
                listIterator = customerDetailsPojos.listIterator();
                while ( listIterator.hasNext() )
                {
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) listIterator.next();
                    LOGGER.info( "Working for CAF Id - " + crmCustomerDetailsPojo.getCrfId() );
                    slaLogPojo = new CrmSlaLogPojo();
                    slaLogPojo.setCrmModule( CRMRequestType.INA.getRequestCode() );
                    slaLogPojo.setAlertType( CRMParameter.SLA_ALERT.getParameter() );
                    slaLogPojo.setMappingId( crmCustomerDetailsPojo.getRecordId() + "" );
                    slaLogPojo.setSlaFromStage( inFromStage );
                    long currentSpan = slaCalculationUtil
                            .getHoursForDays( DateUtils.convertXmlCalToCalendar( crmCustomerDetailsPojo
                                    .getCreatedTime() ), Calendar.getInstance(), true );
                    if ( isInaBPToCHalert
                            && currentSpan >= slaCalculationUtil.changeHoursToMillis( inaBPToCHalertHours ) )
                    {
                        LOGGER.info( "Processing BP To CH" );
                        slaLogPojo.setSlaToStage( CRMOperationStages.CLUSTER_HEAD.getCode() );
                        slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit", inaBPToCHalertUnit ) );
                        slaLogPojo.setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( inaBPToCHalertHours ) );
                        reportDto.setCrmSlaLogPojo( slaLogPojo );
                        reportDto = crmReportService.slaOperations( ServiceParameter.SLA_INA.getParameter(),
                                                                    CRMParameter.SLA_LOG.getParameter(), reportDto );
                        if ( !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                        {
                            slaLogPojo.setSlaActualMillis( currentSpan );
                            processToINAAlertRecipients( slaLogPojo, crmCustomerDetailsPojo );
                        }
                    }
                    else if ( isInaBPToAMalert
                            && currentSpan >= slaCalculationUtil.changeHoursToMillis( inaBPToAMalertHours ) )
                    {
                        LOGGER.info( "Processing BP To AM" );
                        slaLogPojo.setSlaToStage( CRMOperationStages.AREA_MANAGER.getCode() );
                        slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit", inaBPToAMalertUnit ) );
                        slaLogPojo.setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( inaBPToAMalertHours ) );
                        reportDto.setCrmSlaLogPojo( slaLogPojo );
                        reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                    CRMParameter.SLA_LOG.getParameter(), reportDto );
                        if ( !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                        {
                            slaLogPojo.setSlaActualMillis( currentSpan );
                            processToINAAlertRecipients( slaLogPojo, crmCustomerDetailsPojo );
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process INA SLA for Business Partner ", ex );
        }
    }

    private void processToINAAlertRecipients( CrmSlaLogPojo inSlaLogPojo,
                                              CrmCustomerDetailsPojo inCrmCustomerDetailsPojo )
    {
        LOGGER.info( "Inside SlaInaThread, processToINAAlertRecipients" );
        String area = null;
        AreaPojo areaPojo = null;
        String recipients = null;
        ReportDto reportDto = null;
        CRMReportService crmReportService = null;
        try
        {
            for ( CrmAddressDetailsPojo addressDetailsPojo : inCrmCustomerDetailsPojo.getCrmAddressDetailses() )
            {
                if ( StringUtils.equals( addressDetailsPojo.getAddressType(), "IN" ) )
                {
                    areaPojo = new AreaPojo();
                    areaPojo.setAreaId( addressDetailsPojo.getInstAreaId() );
                    reportDto = new ReportDto();
                    reportDto.setAreaPojo( areaPojo );
                    crmReportService = new CRMReportsClient();
                    reportDto = crmReportService.slaOperations( ServiceParameter.SLA_INA.getParameter(),
                                                                CRMParameter.AREA.getParameter(), reportDto );
                    area = reportDto.getAreaPojo().getArea();
                }
            }
            if ( StringUtils.equals( inSlaLogPojo.getSlaToStage(), CRMOperationStages.AREA_MANAGER.getCode() ) )
            {
                if ( StringUtils.isNotBlank( area ) )
                {
                    recipients = SLACommonUtils.getUsersByParameter( "Area", area, inSlaLogPojo.getSlaToStage() );
                    if ( StringUtils.isNotBlank( recipients ) )
                    {
                        inSlaLogPojo.setSlaRecipients( recipients );
                        inSlaLogPojo.setSlaStatus( CRMStatusCode.SUCCESS.getStatusCode() );
                    }
                    else
                    {
                        inSlaLogPojo.setSlaFailReason( "Area Manager not assign to Area : " + area );
                        inSlaLogPojo.setSlaStatus( CRMStatusCode.FAILURE.getStatusCode() );
                    }
                }
                else
                {
                    inSlaLogPojo.setSlaFailReason( "Area not defined yet." );
                    inSlaLogPojo.setSlaStatus( CRMStatusCode.FAILURE.getStatusCode() );
                }
            }
            else if ( StringUtils.equals( inSlaLogPojo.getSlaToStage(), CRMOperationStages.CLUSTER_HEAD.getCode() ) )
            {
                recipients = SLACommonUtils.getUsersByBinID( inSlaLogPojo.getSlaToStage() );
                if ( StringUtils.isNotBlank( recipients ) )
                {
                    inSlaLogPojo.setSlaRecipients( recipients );
                    inSlaLogPojo.setSlaStatus( CRMStatusCode.SUCCESS.getStatusCode() );
                }
                else
                {
                    inSlaLogPojo.setSlaFailReason( "Cluster Head not available" );
                    inSlaLogPojo.setSlaStatus( CRMStatusCode.FAILURE.getStatusCode() );
                }
            }
            String currentBin = "";
            if ( StringUtils.equals( inCrmCustomerDetailsPojo.getCrfStage(), CRMOperationStages.INITIATE.getCode() ) )
            {
                currentBin = CRMOperationStages.SALES_COORDINATOR.getDesc();
            }
            Map<String, String> paramValues = SLACommonUtils
                    .prepareParameterMap( currentBin, inCrmCustomerDetailsPojo.getCurrentUser(), area,
                                          inCrmCustomerDetailsPojo.getProduct(), CRMRequestType.INA.getRequestCode(),
                                          inCrmCustomerDetailsPojo.getCrfId(),
                                          inCrmCustomerDetailsPojo.getCreatedTime(), null );
            LOGGER.info( "SLA time : " + slaCalculationUtil.getDurationBreakdown( inSlaLogPojo.getSlaActualMillis() ) );
            processToService( paramValues, inSlaLogPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing INA alert.", ex );
        }
    }

    private void processToService( final Map<String, String> inParamMap, final CrmSlaLogPojo inSlaLogPojo )
    {
        LOGGER.info( "Inside SlaInaThread, processToService" );
        CRMReportService crmReportService = null;
        ReportDto reportDto = null;
        try
        {
            crmReportService = new CRMReportsClient();
            reportDto = new ReportDto();
            reportDto.setCrmSlaLogPojo( inSlaLogPojo );
            if ( StringUtils.isValidObj( inParamMap ) )
            {
                reportDto.setParamMap( SLACommonUtils.convertToClientMap( inParamMap ) );
            }
            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_INA.getParameter(),
                                                        inSlaLogPojo.getAlertType(), reportDto );
            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( StringUtils.equals( reportDto.getCrmSlaLogPojo().getSlaStatus(),
                                         CRMStatusCode.SUCCESS.getStatusCode() ) )
                {
                    LOGGER.info( "Successfully processed SLA action for CAF ID: "
                            + reportDto.getCrmSlaLogPojo().getMappingId() );
                }
                else
                {
                    LOGGER.info( "SLA action failed for CAF ID: " + reportDto.getCrmSlaLogPojo().getMappingId()
                            + " due to reason: " + reportDto.getCrmSlaLogPojo().getSlaFailReason() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process to service", ex );
        }
    }
}
