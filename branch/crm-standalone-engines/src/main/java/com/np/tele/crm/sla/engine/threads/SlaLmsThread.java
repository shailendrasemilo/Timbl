package com.np.tele.crm.sla.engine.threads;

import java.util.Calendar;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMReportsClient;
import com.np.tele.crm.client.LMSServiceClient;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMLMSService;
import com.np.tele.crm.service.client.CRMReportService;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmSlaLogPojo;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.sla.engine.constants.CRMSLAConstants;
import com.np.tele.crm.sla.engine.constants.IApplicationConstants;
import com.np.tele.crm.sla.engine.utils.SLACommonUtils;
import com.np.tele.crm.sla.engine.utils.SLAPropertyUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SLACalculationUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SlaLmsThread
    implements Runnable
{
    private static final Logger LOGGER                  = Logger.getLogger( SlaLmsThread.class );
    private SLACalculationUtil  slaCalculationUtil      = null;
    private int                 threadSleepTimeInMinute = 15;
    private boolean             isLmsSCToAMalert        = false;
    private String              lmsSCToAMalertUnit;
    private int                 lmsSCToAMalertHours;
    private boolean             isLmsSCToCHalert        = false;
    private String              lmsSCToCHalertUnit;
    private int                 lmsSCToCHalertHours;
    private boolean             isLmsAMToCHalert        = false;
    private String              lmsAMToCHalertUnit;
    private int                 lmsAMToCHalertHours;
    private boolean             isLmsBPToAMalert        = false;
    private String              lmsBPToAMalertUnit;
    private int                 lmsBPToAMalertHours;
    private boolean             isLmsBPToCHalert        = false;
    private String              lmsBPToCHalertUnit;
    private int                 lmsBPToCHalertHours;
    private boolean             isLmsBPToAMforward      = false;
    private String              lmsBPToAMforwardUnit;
    private int                 lmsBPToAMforwardHours;

    public SlaLmsThread( Properties inProperties, SLACalculationUtil inSLACalculationUtil )
    {
        slaCalculationUtil = inSLACalculationUtil;
        threadSleepTimeInMinute = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.CRM_SLA_THREAD_SLEEP_TIME_MINUTE,
                                                           threadSleepTimeInMinute );
        isLmsSCToAMalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_LMS_SC_TO_AM_ALERT_ACTIVE,
                                                        isLmsSCToAMalert );
        isLmsSCToCHalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_LMS_SC_TO_CH_ALERT_ACTIVE,
                                                        isLmsSCToCHalert );
        if ( isLmsSCToAMalert || isLmsSCToCHalert )
        {
            lmsSCToAMalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_LMS_SC_TO_AM_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_HOURS.getValue() );
            lmsSCToAMalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_LMS_SC_TO_AM_ALERT_HOURS,
                                                           CRMSLAConstants.LMS_SC_AM_ALERT.getSlaTimeInHours() );
            lmsSCToCHalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_LMS_SC_TO_CH_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_HOURS.getValue() );
            lmsSCToCHalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_LMS_SC_TO_CH_ALERT_HOURS,
                                                           CRMSLAConstants.LMS_SC_CH_ALERT.getSlaTimeInHours() );
        }
        isLmsAMToCHalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_LMS_AM_TO_CH_ALERT_ACTIVE,
                                                        isLmsAMToCHalert );
        if ( isLmsAMToCHalert )
        {
            lmsAMToCHalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_LMS_AM_TO_CH_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_DAYS.getValue() );
            lmsAMToCHalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_LMS_AM_TO_CH_ALERT_HOURS,
                                                           CRMSLAConstants.LMS_AM_CH_ALERT.getSlaTimeInHours() );
        }
        isLmsBPToAMalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_LMS_BP_TO_AM_ALERT_ACTIVE,
                                                        isLmsBPToAMalert );
        isLmsBPToCHalert = SLAPropertyUtils.getBoolean( inProperties,
                                                        IApplicationConstants.SLA_LMS_BP_TO_CH_ALERT_ACTIVE,
                                                        isLmsBPToCHalert );
        isLmsBPToAMforward = SLAPropertyUtils.getBoolean( inProperties,
                                                          IApplicationConstants.SLA_LMS_BP_TO_AM_FORWARD_ACTIVE,
                                                          isLmsBPToAMforward );
        if ( isLmsBPToAMalert || isLmsBPToCHalert || isLmsBPToAMforward )
        {
            lmsBPToAMalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_LMS_BP_TO_AM_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_DAYS.getValue() );
            lmsBPToAMalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_LMS_BP_TO_AM_ALERT_HOURS,
                                                           CRMSLAConstants.LMS_BP_AM_ALERT.getSlaTimeInHours() );
            lmsBPToCHalertUnit = SLAPropertyUtils.getString( inProperties,
                                                             IApplicationConstants.SLA_LMS_BP_TO_CH_ALERT_UNIT,
                                                             CRMDisplayListConstants.WORKING_DAYS.getValue() );
            lmsBPToCHalertHours = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.SLA_LMS_BP_TO_CH_ALERT_HOURS,
                                                           CRMSLAConstants.LMS_BP_CH_ALERT.getSlaTimeInHours() );
            lmsBPToAMforwardUnit = SLAPropertyUtils.getString( inProperties,
                                                               IApplicationConstants.SLA_LMS_BP_TO_AM_FORWARD_UNIT,
                                                               CRMDisplayListConstants.WORKING_DAYS.getValue() );
            lmsBPToAMforwardHours = SLAPropertyUtils.getInt( inProperties,
                                                             IApplicationConstants.SLA_LMS_BP_TO_AM_FORWARD_HOURS,
                                                             CRMSLAConstants.LMS_BP_AM_FORWARD.getSlaTimeInHours() );
        }
    }

    @Override
    public void run()
    {
        while ( true )
        {
            String module;
            String fromStage;
            if ( isLmsSCToAMalert || isLmsSCToCHalert )
            {
                LOGGER.info( "SLA processing start for Sales Coordinator stage. " );
                module = CRMRequestType.LMS.getRequestCode();
                fromStage = CRMOperationStages.SALES_COORDINATOR.getCode();
                processLmsSlaForSC( module, fromStage );
            }
            if ( isLmsAMToCHalert )
            {
                LOGGER.info( "SLA processing start for Area Manager stage. " );
                module = CRMRequestType.LMS.getRequestCode();
                fromStage = CRMOperationStages.AREA_MANAGER.getCode();
                processLmsSlaForAM( module, fromStage );
            }
            if ( isLmsBPToAMalert || isLmsBPToCHalert || isLmsBPToAMforward )
            {
                LOGGER.info( "SLA processing start for Business Partner stage. " );
                module = CRMRequestType.LMS.getRequestCode();
                fromStage = CRMOperationStages.SALES.getCode();
                processLmsSlaForBP( module, fromStage );
            }
            try
            {
                LOGGER.info( "LMS SLA process holds for " + threadSleepTimeInMinute + " Minutes." );
                Thread.sleep( threadSleepTimeInMinute * 60 * 1000 );
            }
            catch ( InterruptedException ex )
            {
                LOGGER.error( "Exception while sleeping the LMS thread", ex );
            }
        }
    }

    private void processLmsSlaForSC( String inModule, String inFromStage )
    {
        LOGGER.info( "Inside SlaLmsThread, processLmsSlaForSC" );
        ListIterator<LmsPojo> listIterator = null;
        CRMReportService crmReportService = null;
        CrmAuditLogPojo auditLogPojo = null;
        CRMLMSService crmLmsService = null;
        CrmSlaLogPojo slaLogPojo = null;
        List<LmsPojo> lmsPojos = null;
        ReportDto reportDto = null;
        LmsPojo lmsPojo = null;
        LmsDto lmsDto = null;
        try
        {
            crmReportService = new CRMReportsClient();
            crmLmsService = new LMSServiceClient();
            reportDto = new ReportDto();
            lmsPojo = new LmsPojo();
            lmsDto = new LmsDto();
            lmsPojo.setLmsStage( inFromStage );
            lmsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            lmsDto.setLeadPojo( lmsPojo );
            lmsDto = crmLmsService.lmsOperation( ServiceParameter.LIST.getParameter(), lmsDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), lmsDto.getStatusCode() ) )
            {
                lmsPojos = lmsDto.getLeadPojos();
                listIterator = lmsPojos.listIterator();
                while ( listIterator.hasNext() )
                {
                    LmsPojo leadPojo = (LmsPojo) listIterator.next();
                    LOGGER.info( "Working for Lead Id - " + leadPojo.getLeadId() );
                    slaLogPojo = new CrmSlaLogPojo();
                    slaLogPojo.setCrmModule( CRMRequestType.LMS.getRequestCode() );
                    slaLogPojo.setAlertType( CRMParameter.SLA_ALERT.getParameter() );
                    slaLogPojo.setMappingId( leadPojo.getLmsId() + "" );
                    slaLogPojo.setSlaFromStage( inFromStage );
                    auditLogPojo = new CrmAuditLogPojo();
                    auditLogPojo.setModule( CRMRequestType.LMS.getRequestCode() );
                    auditLogPojo.setMappingId( leadPojo.getLmsId() + "" );
                    auditLogPojo.setToBin( leadPojo.getLmsStage() );
                    //auditLogPojo.setFromBin( leadPojo.getPreviousStage() );
                    reportDto.setCrmAuditLogPojo( auditLogPojo );
                    auditLogPojo = SLACommonUtils.getLatestAuditLog( ServiceParameter.SLA_LMS.getParameter(),
                                                                     CRMParameter.AUDIT_LOG.getParameter(), reportDto );
                    if ( StringUtils.isValidObj( auditLogPojo ) )
                    {
                        long currentSpan = slaCalculationUtil.getHoursForWorkingHours( DateUtils
                                .convertXmlCalToCalendar( auditLogPojo.getCreatedTime() ), Calendar.getInstance() );
                        if ( isLmsSCToCHalert
                                && currentSpan >= slaCalculationUtil.changeHoursToMillis( lmsSCToCHalertHours ) )
                        {
                            LOGGER.info( "Processing SC To CH" );
                            slaLogPojo.setAuditLogId( auditLogPojo.getAuditLogId() );
                            slaLogPojo.setSlaToStage( CRMOperationStages.CLUSTER_HEAD.getCode() );
                            slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit",
                                                                                           lmsSCToCHalertUnit ) );
                            slaLogPojo
                                    .setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( lmsSCToCHalertHours ) );
                            reportDto.setCrmSlaLogPojo( slaLogPojo );
                            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                        CRMParameter.SLA_LOG.getParameter(), reportDto );
                            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                    && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                            {
                                slaLogPojo.setSlaActualMillis( currentSpan );
                                processToLmsAlertRecipients( slaLogPojo, leadPojo, auditLogPojo );
                            }
                            else
                            {
                                LOGGER.info( "Alert already sent." );
                            }
                        }
                        else if ( isLmsSCToAMalert
                                && currentSpan >= slaCalculationUtil.changeHoursToMillis( lmsSCToAMalertHours ) )
                        {
                            LOGGER.info( "Processing SC To AM" );
                            slaLogPojo.setAuditLogId( auditLogPojo.getAuditLogId() );
                            slaLogPojo.setSlaToStage( CRMOperationStages.AREA_MANAGER.getCode() );
                            slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit",
                                                                                           lmsSCToAMalertUnit ) );
                            slaLogPojo
                                    .setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( lmsSCToAMalertHours ) );
                            reportDto.setCrmSlaLogPojo( slaLogPojo );
                            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                        CRMParameter.SLA_LOG.getParameter(), reportDto );
                            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                    && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                            {
                                slaLogPojo.setSlaActualMillis( currentSpan );
                                processToLmsAlertRecipients( slaLogPojo, leadPojo, auditLogPojo );
                            }
                            else
                            {
                                LOGGER.info( "Alert already sent." );
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process LMS SLA for sales coordinator ", ex );
        }
    }

    private void processLmsSlaForAM( String inModule, String inFromStage )
    {
        LOGGER.info( "Inside SlaLmsThread, processLmsSlaForAM" );
        ListIterator<LmsPojo> listIterator = null;
        CRMReportService crmReportService = null;
        CrmAuditLogPojo auditLogPojo = null;
        CRMLMSService crmLmsService = null;
        CrmSlaLogPojo slaLogPojo = null;
        List<LmsPojo> lmsPojos = null;
        ReportDto reportDto = null;
        LmsPojo lmsPojo = null;
        LmsDto lmsDto = null;
        try
        {
            crmReportService = new CRMReportsClient();
            crmLmsService = new LMSServiceClient();
            reportDto = new ReportDto();
            lmsPojo = new LmsPojo();
            lmsDto = new LmsDto();
            lmsPojo.setLmsStage( inFromStage );
            lmsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            lmsDto.setLeadPojo( lmsPojo );
            lmsDto = crmLmsService.lmsOperation( ServiceParameter.LIST.getParameter(), lmsDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), lmsDto.getStatusCode() ) )
            {
                lmsPojos = lmsDto.getLeadPojos();
                listIterator = lmsPojos.listIterator();
                while ( listIterator.hasNext() )
                {
                    LmsPojo leadPojo = (LmsPojo) listIterator.next();
                    LOGGER.info( "Working for Lead Id - " + leadPojo.getLeadId() );
                    slaLogPojo = new CrmSlaLogPojo();
                    slaLogPojo.setCrmModule( CRMRequestType.LMS.getRequestCode() );
                    slaLogPojo.setAlertType( CRMParameter.SLA_ALERT.getParameter() );
                    slaLogPojo.setMappingId( leadPojo.getLmsId() + "" );
                    slaLogPojo.setSlaFromStage( inFromStage );
                    auditLogPojo = new CrmAuditLogPojo();
                    auditLogPojo.setModule( CRMRequestType.LMS.getRequestCode() );
                    auditLogPojo.setMappingId( leadPojo.getLmsId() + "" );
                    auditLogPojo.setToBin( leadPojo.getLmsStage() );
                    auditLogPojo.setFromBin( leadPojo.getPreviousStage() );
                    reportDto.setCrmAuditLogPojo( auditLogPojo );
                    auditLogPojo = SLACommonUtils.getLatestAuditLog( ServiceParameter.SLA_LMS.getParameter(),
                                                                     CRMParameter.AUDIT_LOG.getParameter(), reportDto );
                    if ( StringUtils.isValidObj( auditLogPojo ) )
                    {
                        long currentSpan = slaCalculationUtil
                                .getHoursForDays( DateUtils.convertXmlCalToCalendar( auditLogPojo.getCreatedTime() ),
                                                  Calendar.getInstance(), true );
                        if ( isLmsAMToCHalert
                                && currentSpan >= slaCalculationUtil.changeHoursToMillis( lmsAMToCHalertHours ) )
                        {
                            LOGGER.info( "Processing AM To CH" );
                            slaLogPojo.setAuditLogId( auditLogPojo.getAuditLogId() );
                            slaLogPojo.setSlaToStage( CRMOperationStages.CLUSTER_HEAD.getCode() );
                            slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit",
                                                                                           lmsAMToCHalertUnit ) );
                            slaLogPojo
                                    .setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( lmsAMToCHalertHours ) );
                            reportDto.setCrmSlaLogPojo( slaLogPojo );
                            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                        CRMParameter.SLA_LOG.getParameter(), reportDto );
                            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                    && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                            {
                                slaLogPojo.setSlaActualMillis( currentSpan );
                                processToLmsAlertRecipients( slaLogPojo, leadPojo, auditLogPojo );
                            }
                            else
                            {
                                LOGGER.info( "Alert already sent." );
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process LMS SLA for Area Manager ", ex );
        }
    }

    private void processLmsSlaForBP( String inModule, String inFromStage )
    {
        LOGGER.info( "Inside SlaLmsThread, processLmsSlaForBP" );
        ListIterator<LmsPojo> listIterator = null;
        CRMReportService crmReportService = null;
        CrmAuditLogPojo auditLogPojo = null;
        CRMLMSService crmLmsService = null;
        CrmSlaLogPojo slaLogPojo = null;
        List<LmsPojo> lmsPojos = null;
        ReportDto reportDto = null;
        LmsPojo lmsPojo = null;
        LmsDto lmsDto = null;
        try
        {
            crmReportService = new CRMReportsClient();
            crmLmsService = new LMSServiceClient();
            reportDto = new ReportDto();
            lmsPojo = new LmsPojo();
            lmsDto = new LmsDto();
            lmsPojo.setLmsStage( inFromStage );
            lmsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            lmsDto.setLeadPojo( lmsPojo );
            lmsDto = crmLmsService.lmsOperation( ServiceParameter.LIST.getParameter(), lmsDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), lmsDto.getStatusCode() ) )
            {
                lmsPojos = lmsDto.getLeadPojos();
                listIterator = lmsPojos.listIterator();
                while ( listIterator.hasNext() )
                {
                    LmsPojo leadPojo = (LmsPojo) listIterator.next();
                    LOGGER.info( "Working for Lead Id - " + leadPojo.getLeadId() );
                    slaLogPojo = new CrmSlaLogPojo();
                    slaLogPojo.setCrmModule( CRMRequestType.LMS.getRequestCode() );
                    slaLogPojo.setAlertType( CRMParameter.SLA_ALERT.getParameter() );
                    slaLogPojo.setMappingId( leadPojo.getLmsId() + "" );
                    slaLogPojo.setSlaFromStage( inFromStage );
                    auditLogPojo = new CrmAuditLogPojo();
                    auditLogPojo.setModule( CRMRequestType.LMS.getRequestCode() );
                    auditLogPojo.setMappingId( leadPojo.getLmsId() + "" );
                    auditLogPojo.setToBin( leadPojo.getLmsStage() );
                    auditLogPojo.setFromBin( leadPojo.getPreviousStage() );
                    reportDto.setCrmAuditLogPojo( auditLogPojo );
                    auditLogPojo = SLACommonUtils.getLatestAuditLog( ServiceParameter.SLA_LMS.getParameter(),
                                                                     CRMParameter.AUDIT_LOG.getParameter(), reportDto );
                    if ( StringUtils.isValidObj( auditLogPojo ) )
                    {
                        RemarksPojo remarksPojo = SLACommonUtils.getLatestRemarks( leadPojo.getLeadId(),
                                                                                   auditLogPojo.getCreatedTime(),
                                                                                   leadPojo.getCurrentUser() );
                        Calendar startTime = null;
                        if ( StringUtils.isValidObj( remarksPojo.getCreatedTime() ) )
                        {
                            startTime = DateUtils.convertXmlCalToCalendar( remarksPojo.getCreatedTime() );
                        }
                        else
                        {
                            startTime = DateUtils.convertXmlCalToCalendar( auditLogPojo.getCreatedTime() );
                        }
                        LOGGER.info( "SLA start time: " + startTime.getTime() );
                        long currentSpan = slaCalculationUtil.getHoursForDays( startTime, Calendar.getInstance(), true );
                        if ( isLmsBPToAMforward
                                && currentSpan >= slaCalculationUtil.changeHoursToMillis( lmsBPToAMforwardHours ) )
                        {
                            LOGGER.info( "Processing BP To AM forward" );
                            slaLogPojo.setAuditLogId( auditLogPojo.getAuditLogId() );
                            slaLogPojo.setAlertType( CRMParameter.SLA_FORWARD.getParameter() );
                            slaLogPojo.setSlaToStage( CRMOperationStages.AREA_MANAGER.getCode() );
                            slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit",
                                                                                           lmsBPToAMforwardUnit ) );
                            slaLogPojo.setSlaValueMillis( slaCalculationUtil
                                    .changeHoursToMillis( lmsBPToAMforwardHours ) );
                            reportDto.setCrmSlaLogPojo( slaLogPojo );
                            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                        CRMParameter.SLA_LOG.getParameter(), reportDto );
                            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                    && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                            {
                                slaLogPojo.setSlaActualMillis( currentSpan );
                                processToLmsAlertRecipients( slaLogPojo, leadPojo, auditLogPojo );
                            }
                            else
                            {
                                LOGGER.info( "Alert already forwarded." );
                            }
                        }
                        else if ( isLmsBPToCHalert
                                && currentSpan >= slaCalculationUtil.changeHoursToMillis( lmsBPToCHalertHours ) )
                        {
                            LOGGER.info( "Processing BP To CH" );
                            slaLogPojo.setAuditLogId( auditLogPojo.getAuditLogId() );
                            slaLogPojo.setSlaToStage( CRMOperationStages.CLUSTER_HEAD.getCode() );
                            slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit",
                                                                                           lmsBPToCHalertUnit ) );
                            slaLogPojo
                                    .setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( lmsBPToCHalertHours ) );
                            reportDto.setCrmSlaLogPojo( slaLogPojo );
                            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                        CRMParameter.SLA_LOG.getParameter(), reportDto );
                            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                    && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                            {
                                slaLogPojo.setSlaActualMillis( currentSpan );
                                processToLmsAlertRecipients( slaLogPojo, leadPojo, auditLogPojo );
                            }
                            else
                            {
                                LOGGER.info( "Alert already sent." );
                            }
                        }
                        else if ( isLmsBPToAMalert
                                && currentSpan >= slaCalculationUtil.changeHoursToMillis( lmsBPToAMalertHours ) )
                        {
                            LOGGER.info( "Processing BP To AM" );
                            slaLogPojo.setAuditLogId( auditLogPojo.getAuditLogId() );
                            slaLogPojo.setSlaToStage( CRMOperationStages.AREA_MANAGER.getCode() );
                            slaLogPojo.setSlaUnit( CRMDisplayListConstants.getCodeByValue( "SlaUnit",
                                                                                           lmsBPToAMalertUnit ) );
                            slaLogPojo
                                    .setSlaValueMillis( slaCalculationUtil.changeHoursToMillis( lmsBPToAMalertHours ) );
                            reportDto.setCrmSlaLogPojo( slaLogPojo );
                            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                                        CRMParameter.SLA_LOG.getParameter(), reportDto );
                            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                                    && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                            {
                                slaLogPojo.setSlaActualMillis( currentSpan );
                                processToLmsAlertRecipients( slaLogPojo, leadPojo, auditLogPojo );
                            }
                            else
                            {
                                LOGGER.info( "Alert already sent." );
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process LMS SLA for Business Partner ", ex );
        }
    }

    private void processToLmsAlertRecipients( CrmSlaLogPojo inSlaLogPojo,
                                              LmsPojo inLeadPojo,
                                              CrmAuditLogPojo inCrmAuditLogPojo )
    {
        LOGGER.info( "Inside SlaLmsThread, processToLmsAlertRecipients" );
        String recipients = null;
        RemarksPojo remarksPojo = null;
        CRMLMSService crmLmsService = null;
        LmsDto lmsDto = new LmsDto();
        try
        {
            lmsDto.setLeadPojo( inLeadPojo );
            crmLmsService = new LMSServiceClient();
            if ( StringUtils.equals( inSlaLogPojo.getSlaToStage(), CRMOperationStages.AREA_MANAGER.getCode() ) )
            {
                if ( StringUtils.isNotBlank( inLeadPojo.getArea() ) )
                {
                    lmsDto = crmLmsService.lmsOperation( "AreaId", lmsDto );
                    recipients = SLACommonUtils.getUsersByParameter( "Area", lmsDto.getLeadPojo().getArea(),
                                                                     inSlaLogPojo.getSlaToStage() );
                    if ( StringUtils.isNotBlank( recipients ) )
                    {
                        inSlaLogPojo.setSlaRecipients( recipients );
                        inSlaLogPojo.setSlaStatus( CRMStatusCode.SUCCESS.getStatusCode() );
                    }
                    else
                    {
                        inSlaLogPojo.setSlaFailReason( "Area Manager not assign to Area : " + inLeadPojo.getArea() );
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
            if ( StringUtils.equals( inLeadPojo.getLmsStage(), CRMOperationStages.SALES_COORDINATOR.getCode() ) )
            {
                currentBin = CRMOperationStages.SALES_COORDINATOR.getDesc();
            }
            else if ( StringUtils.equals( inLeadPojo.getLmsStage(), CRMOperationStages.AREA_MANAGER.getCode() ) )
            {
                currentBin = CRMOperationStages.AREA_MANAGER.getDesc();
            }
            else if ( StringUtils.equals( inLeadPojo.getLmsStage(), CRMOperationStages.SALES.getCode() ) )
            {
                currentBin = "Business Partner";
            }
            remarksPojo = SLACommonUtils.getLatestRemarks( inLeadPojo.getLeadId(), null, null );
            if ( StringUtils.equals( inSlaLogPojo.getAlertType(), CRMParameter.SLA_FORWARD.getParameter() ) )
            {
                processToAMForward( inLeadPojo, inSlaLogPojo );
            }
            else
            {
                Map<String, String> paramValues = SLACommonUtils.prepareParameterMap( currentBin, inLeadPojo
                        .getCurrentUser(), inLeadPojo.getArea(), inLeadPojo.getProduct(), CRMRequestType.LMS
                        .getRequestCode(), inLeadPojo.getLeadId(), inCrmAuditLogPojo.getCreatedTime(), remarksPojo
                        .getRemarks() );
                processToAlert( paramValues, inSlaLogPojo );
            }
            LOGGER.info( "SLA time : " + slaCalculationUtil.getDurationBreakdown( inSlaLogPojo.getSlaActualMillis() ) );
            LOGGER.info( "Remarks DateTime : "
                    + DateUtils.convertXmlCalToCalendar( remarksPojo.getCreatedTime() ).getTime() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing Lms Alert to Recipients", ex );
        }
    }

    private void processToAMForward( LmsPojo inLeadPojo, CrmSlaLogPojo inSlaLogPojo )
    {
        LOGGER.info( "Inside SlaLmsThread, processToAMForward" );
        CRMReportService crmReportService = null;
        ReportDto reportDto = null;
        try
        {
            crmReportService = new CRMReportsClient();
            reportDto = new ReportDto();
            reportDto.setLeadPojo( inLeadPojo );
            reportDto.setCrmSlaLogPojo( inSlaLogPojo );
            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                        inSlaLogPojo.getAlertType(), reportDto );
            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( StringUtils.equals( reportDto.getCrmSlaLogPojo().getSlaStatus(),
                                         CRMStatusCode.SUCCESS.getStatusCode() ) )
                {
                    LOGGER.info( "Successfully processed SLA action for LMS ID: "
                            + reportDto.getCrmSlaLogPojo().getMappingId() );
                }
                else
                {
                    LOGGER.info( "SLA action failed for LMS ID: " + reportDto.getCrmSlaLogPojo().getMappingId()
                            + " due to reason: " + reportDto.getCrmSlaLogPojo().getSlaFailReason() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process to forward", ex );
        }
    }

    private void processToAlert( final Map<String, String> inParamMap, final CrmSlaLogPojo inSlaLogPojo )
    {
        LOGGER.info( "Inside SlaLmsThread, processToAlert" );
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
            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_LMS.getParameter(),
                                                        inSlaLogPojo.getAlertType(), reportDto );
            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( StringUtils.equals( reportDto.getCrmSlaLogPojo().getSlaStatus(),
                                         CRMStatusCode.SUCCESS.getStatusCode() ) )
                {
                    LOGGER.info( "Successfully processed SLA action for LMS ID: "
                            + reportDto.getCrmSlaLogPojo().getMappingId() );
                }
                else
                {
                    LOGGER.info( "SLA action failed for LMS ID: " + reportDto.getCrmSlaLogPojo().getMappingId()
                            + " due to reason: " + reportDto.getCrmSlaLogPojo().getSlaFailReason() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process to alert", ex );
        }
    }
}
