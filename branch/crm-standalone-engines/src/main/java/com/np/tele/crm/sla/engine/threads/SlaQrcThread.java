package com.np.tele.crm.sla.engine.threads;

import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMReportsClient;
import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMReportService;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSlaLogPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.ReportDto;
import com.np.tele.crm.sla.engine.constants.IApplicationConstants;
import com.np.tele.crm.sla.engine.utils.SLACommonUtils;
import com.np.tele.crm.sla.engine.utils.SLAPropertyUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SLACalculationUtil;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SlaQrcThread
    implements Runnable
{
    private static final Logger LOGGER                  = Logger.getLogger( SlaQrcThread.class );
    private SLACalculationUtil  slaCalculationUtil      = null;
    private Properties          properties              = null;
    private int                 threadSleepTimeInMinute = 15;
    private static long         EOC_RB_ID               = 0l;

    public SlaQrcThread( Properties inProperties, SLACalculationUtil inSLACalculationUtil )
    {
        slaCalculationUtil = inSLACalculationUtil;
        properties = inProperties;
        threadSleepTimeInMinute = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.CRM_SLA_THREAD_SLEEP_TIME_MINUTE,
                                                           threadSleepTimeInMinute );
    }
    static
    {
        MasterDataDto inMasterDataDto = new MasterDataDto();
        MasterDataClient dataClient = new MasterDataClient();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
        crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
        crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
        crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmRcaReasonPojo.setValueAlias( CRMOperationStages.EOC_RESOLVED_BIN.getCode() );
        inMasterDataDto.setCrmRcaReason( crmRcaReasonPojo );
        try
        {
            inMasterDataDto = dataClient.masterOperations( ServiceParameter.LIST.getParameter(),
                                                           CRMParameter.CRM_RCA_REASON.getParameter(), inMasterDataDto );
            if ( CommonValidator.isValidCollection( inMasterDataDto.getCrmRcaReasonsList() ) )
            {
                EOC_RB_ID = inMasterDataDto.getCrmRcaReasonsList().get( 0 ).getCategoryId();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
    }

    @Override
    public void run()
    {
        while ( true )
        {
            slaCalculationUtil = new SLACalculationUtil( SLACommonUtils.getHolidayList() );
            for ( CrmQrcSubSubCategoriesPojo subSubCategoriesPojo : SLACommonUtils.getQrcSubSubCategoriesPojos() )
            {
                LOGGER.info( "Working for Sub sub category : " + subSubCategoriesPojo.getQrcSubSubCategory() );
                initializeSLAProcess( subSubCategoriesPojo );
            }
            try
            {
                LOGGER.info( "QRC SLA process holds for " + threadSleepTimeInMinute + " Minutes." );
                Thread.sleep( threadSleepTimeInMinute * 60 * 1000 );
            }
            catch ( InterruptedException ex )
            {
                LOGGER.error( "Exception while sleeping the QRC thread", ex );
            }
        }
    }

    private void initializeSLAProcess( CrmQrcSubSubCategoriesPojo inSubSubCategoriesPojo )
    {
        LOGGER.info( "Inside SlaQrcThread, initializeSLAProcess" );
        CrmSrTicketsPojo crmSrTicketsPojo = null;
        CrmQrcService crmQrcService = null;
        CrmQrcDto crmQrcDto = null;
        try
        {
            crmQrcDto = new CrmQrcDto();
            crmQrcService = new CrmQrcClient();
            crmSrTicketsPojo = new CrmSrTicketsPojo();
            crmSrTicketsPojo.setQrcSubCategoryId( inSubSubCategoriesPojo.getQrcSubCategoryId() );
            crmSrTicketsPojo.setQrcSubSubCategoryId( inSubSubCategoriesPojo.getQrcSubSubCategoryId() );
            crmSrTicketsPojo.setStatus( CRMStatusCode.OPEN.getStatusCode() );
            crmSrTicketsPojo.setResolutionType( (byte) 1 );
            crmQrcDto.setCrmSrTicketsPojo( crmSrTicketsPojo );
            crmQrcDto = crmQrcService.ticketOperations( ServiceParameter.LIST.getParameter(),
                                                        CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
            {
                LOGGER.info( "Fetch Tickets list size : " + crmQrcDto.getCrmSrTicketsPojos().size() );
                for ( CrmSrTicketsPojo ticketPojo : crmQrcDto.getCrmSrTicketsPojos() )
                {
                    if ( EOC_RB_ID != ticketPojo.getFunctionalbinId() )
                    {
                        fetchTicketHistory( ticketPojo, inSubSubCategoriesPojo );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing request for category : "
                                  + inSubSubCategoriesPojo.getQrcSubSubCategory(), ex );
        }
    }

    private void fetchTicketHistory( CrmSrTicketsPojo inTicketPojo, CrmQrcSubSubCategoriesPojo inSubSubCategoriesPojo )
    {
        LOGGER.info( "Inside SlaQrcThread, fetchTicketHistory" );
        CrmTicketHistoryPojo crmTicketHistoryPojo = null;
        CrmQrcService crmQrcService = null;
        CrmQrcDto crmQrcDto = null;
        Calendar startPoint = null;
        Calendar currentTime = Calendar.getInstance();
        try
        {
            startPoint = StringUtils.isValidObj( inTicketPojo.getSrReopenedOn() ) ? DateUtils
                    .convertXmlCalToCalendar( inTicketPojo.getSrReopenedOn() ) : DateUtils
                    .convertXmlCalToCalendar( inTicketPojo.getCreatedTime() );
            crmQrcDto = new CrmQrcDto();
            crmQrcService = new CrmQrcClient();
            crmTicketHistoryPojo = new CrmTicketHistoryPojo();
            crmTicketHistoryPojo.setAction( CrmActionEnum.FOLLOW_UP.getActionCode() );
            crmTicketHistoryPojo.setTicketId( inTicketPojo.getSrId() );
            crmTicketHistoryPojo.setCreatedTime( DateUtils.toXMLGregorianCalendar( startPoint ) );
            crmQrcDto.setTicketHistory( crmTicketHistoryPojo );
            crmQrcDto = crmQrcService.ticketOperations( ServiceParameter.LIST.getParameter(),
                                                        CrmTicketHistoryPojo.class.getSimpleName(), crmQrcDto );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "ticket history size : " + crmQrcDto.getTicketHistoryList().size() );
                Map<Calendar, Calendar> timeMap = new HashMap<Calendar, Calendar>();
                boolean timeMapPrepared = timeMapAnalysis( timeMap, crmQrcDto.getTicketHistoryList(), startPoint,
                                                           currentTime );
                if ( timeMapPrepared )
                {
                    boolean isSLACalculated = calculatedExactSLA( timeMap, inTicketPojo, currentTime,
                                                                  inSubSubCategoriesPojo );
                    if ( isSLACalculated )
                    {
                        updateTicketAndPrepareSLALog( inTicketPojo, inSubSubCategoriesPojo );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching ticket history, for Ticket ID : " + inTicketPojo.getSrId(), ex );
        }
    }

    private void updateTicketAndPrepareSLALog( CrmSrTicketsPojo inTicketPojo,
                                               CrmQrcSubSubCategoriesPojo inSubSubCategoriesPojo )
    {
        LOGGER.info( "Inside SlaQrcThread, updateTicketAndPrepareSLALog" );
        CRMReportService crmReportService = null;
        CrmRcaReasonPojo crmRcaReasonPojo = null;
        CrmSlaLogPojo slaLogPojo = null;
        ReportDto reportDto = null;
        long definedSLA = 0;
        boolean isTicketUpdated = false;
        try
        {
            isTicketUpdated = updateSLADetails( inTicketPojo );
            definedSLA = getDefinedSLA( inSubSubCategoriesPojo );
            if ( isTicketUpdated && definedSLA < inTicketPojo.getSlaTime() )
            {
                crmRcaReasonPojo = SLACommonUtils.getFunctionBinPojo( inTicketPojo.getFunctionalbinId() + "" );
                crmReportService = new CRMReportsClient();
                slaLogPojo = new CrmSlaLogPojo();
                reportDto = new ReportDto();
                slaLogPojo.setCrmModule( CRMRequestType.QRC.getRequestCode() );
                slaLogPojo.setAlertType( CRMParameter.SLA_ALERT.getParameter() );
                slaLogPojo.setMappingId( inTicketPojo.getSrId() );
                slaLogPojo.setSlaFromStage( SLACommonUtils.getFunctionBinPojo( inSubSubCategoriesPojo
                                                                                       .getFunctionalBinId() + "" )
                        .getValueAlias() );
                slaLogPojo.setSlaUnit( inSubSubCategoriesPojo.getSlaUnit() );
                slaLogPojo.setSlaValueMillis( definedSLA );
                reportDto.setCrmSlaLogPojo( slaLogPojo );
                reportDto = crmReportService.slaOperations( ServiceParameter.SLA_QRC.getParameter(),
                                                            CRMParameter.SLA_LOG.getParameter(), reportDto );
                if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && !CommonValidator.isValidCollection( reportDto.getSlaLogPojos() ) )
                {
                    slaLogPojo.setSlaToStage( crmRcaReasonPojo.getValueAlias() );
                    slaLogPojo.setSlaActualMillis( inTicketPojo.getSlaTime() );
                    processToQRCAlertRecipients( slaLogPojo, inTicketPojo, crmRcaReasonPojo.getCategoryValue(),
                                                 inSubSubCategoriesPojo.getQrcSubSubCategory(),
                                                 crmRcaReasonPojo.getValueAlias() );
                }
                else
                {
                    LOGGER.info( "Alert already sent for Ticket ID : " + inTicketPojo.getSrId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while update Ticket and Prepare SLA Log", ex );
        }
    }

    private boolean updateSLADetails( CrmSrTicketsPojo inTicketPojo )
    {
        LOGGER.info( "Inside SlaQrcThread, updateSLADetails" );
        boolean isTicketUpdated = false;
        CrmQrcService crmQrcService = null;
        CrmQrcDto crmQrcDto = null;
        try
        {
            if ( inTicketPojo.getSlaTime() > 0 && StringUtils.isValidObj( inTicketPojo.getSlaCalculatedOn() ) )
            {
                crmQrcDto = new CrmQrcDto();
                crmQrcService = new CrmQrcClient();
                crmQrcDto.setCrmSrTicketsPojo( inTicketPojo );
                crmQrcDto = crmQrcService.ticketOperations( ServiceParameter.UPDATE.getParameter(),
                                                            CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    isTicketUpdated = true;
                    LOGGER.info( "Ticket updated successfully. Ticket ID : " + inTicketPojo.getSrId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while update Ticket for SLA details", ex );
        }
        return isTicketUpdated;
    }

    private void processToQRCAlertRecipients( CrmSlaLogPojo inSlaLogPojo,
                                              CrmSrTicketsPojo inTicketPojo,
                                              String inCurrentBin,
                                              String inQrcSubSubCategory,
                                              String inValueAlias )
    {
        LOGGER.info( "Inside SlaQrcThread, processToQRCAlertRecipients" );
        try
        {
            LOGGER.info( "Incoming value alias" + inValueAlias );
            //            inValueAlias = "test"; //TODO comment this variable for live process
            String recipient = SLAPropertyUtils.getString( properties, IApplicationConstants.SLA_QRC_RECIPIENT
                    + inValueAlias, "" );
            if ( StringUtils.isNotBlank( recipient ) )
            {
                inSlaLogPojo.setSlaRecipients( recipient );
                inSlaLogPojo.setSlaStatus( CRMStatusCode.SUCCESS.getStatusCode() );
                Map<String, String> paramValues = new HashMap<String, String>();
                paramValues.put( CRMParameter.TICKETID.getParameter(), inTicketPojo.getSrId() );
                paramValues.put( CRMParameter.QRC_SUB_CATEGORY.getParameter(),
                                 SLACommonUtils.getQrcSubCategory( inTicketPojo.getQrcSubCategoryId() ) );
                paramValues.put( CRMParameter.QRC_SUB_SUB_CATEGORY.getParameter(), inQrcSubSubCategory );
                paramValues
                        .put( CRMParameter.CURRENT_OWNER.getParameter(),
                              StringUtils.isNotBlank( inTicketPojo.getCurrentUser() )
                                      && !StringUtils.equals( inTicketPojo.getCurrentUser(), IAppConstants.DASH )
                                                                                                                 ? inTicketPojo
                                                                                                                         .getCurrentUser()
                                                                                                                 : IAppConstants.NOTAPPLICABLE );
                paramValues.put( CRMParameter.CURRENT_BIN.getParameter(), inCurrentBin );
                processToAlert( paramValues, inSlaLogPojo );
            }
            else
            {
                inSlaLogPojo.setSlaStatus( CRMStatusCode.FAILURE.getStatusCode() );
                inSlaLogPojo.setSlaFailReason( "Recipeint not defined for " + inCurrentBin );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while update Ticket and Prepare SLA Log", ex );
        }
    }

    private void processToAlert( final Map<String, String> inParamMap, final CrmSlaLogPojo inSlaLogPojo )
    {
        LOGGER.info( "Inside SlaQrcThread, processToAlert" );
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
            reportDto = crmReportService.slaOperations( ServiceParameter.SLA_QRC.getParameter(),
                                                        inSlaLogPojo.getAlertType(), reportDto );
            if ( StringUtils.equals( reportDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( StringUtils.equals( reportDto.getCrmSlaLogPojo().getSlaStatus(),
                                         CRMStatusCode.SUCCESS.getStatusCode() ) )
                {
                    LOGGER.info( "Successfully processed SLA action for Ticket ID: "
                            + reportDto.getCrmSlaLogPojo().getMappingId() );
                }
                else
                {
                    LOGGER.info( "SLA action failed for Ticket ID: " + reportDto.getCrmSlaLogPojo().getMappingId()
                            + " due to reason: " + reportDto.getCrmSlaLogPojo().getSlaFailReason() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process to alert", ex );
        }
    }

    private long getDefinedSLA( CrmQrcSubSubCategoriesPojo inSubSubCategoriesPojo )
    {
        long definedSLA = 0;
        if ( StringUtils.equals( inSubSubCategoriesPojo.getSlaUnit(), CRMDisplayListConstants.WORKING_HOURS.getCode() ) )
        {
            definedSLA = slaCalculationUtil.changeHoursToMillis( inSubSubCategoriesPojo.getSla() );
        }
        else
        {
            definedSLA = slaCalculationUtil.changeDaysToMillis( inSubSubCategoriesPojo.getSla() );
        }
        return definedSLA;
    }

    private boolean calculatedExactSLA( Map<Calendar, Calendar> inTimeMap,
                                        CrmSrTicketsPojo inTicketPojo,
                                        Calendar inCurrentTime,
                                        CrmQrcSubSubCategoriesPojo inSubSubCategoriesPojo )
    {
        LOGGER.info( "Inside SlaQrcThread, calculatedExactSLA" );
        boolean isSLACalculated = false;
        try
        {
            long slaCalculated = 0;
            long slaDefined = 0;
            XMLGregorianCalendar xmlGregorianCalendar = StringUtils.isValidObj( inTicketPojo.getSlaCalculatedOn() )
                                                                                                                   ? inTicketPojo
                                                                                                                           .getSlaCalculatedOn()
                                                                                                                   : inTicketPojo
                                                                                                                           .getCreatedTime();
            Calendar slaCalculatedOn = DateUtils.convertXmlCalToCalendar( xmlGregorianCalendar );
            for ( Entry<Calendar, Calendar> entry : inTimeMap.entrySet() )
            {
                Calendar key = entry.getKey();
                Calendar value = entry.getValue();
                if ( slaCalculatedOn.before( value ) )
                {
                    if ( slaCalculatedOn.after( key ) )
                    {
                        key = slaCalculatedOn;
                    }
                    LOGGER.info( "SLA start : " + key.getTime() + "\tSLA stop : " + value.getTime() );
                    if ( StringUtils.equals( inSubSubCategoriesPojo.getSlaUnit(),
                                             CRMDisplayListConstants.WORKING_HOURS.getCode() ) )
                    {
                        slaDefined = inSubSubCategoriesPojo.getSla() * 60 * 60 * 1000;
                        slaCalculated += slaCalculationUtil.getHoursForWorkingHours( key, value );
                    }
                    else if ( StringUtils.equals( inSubSubCategoriesPojo.getSlaUnit(),
                                                  CRMDisplayListConstants.WORKING_DAYS.getCode() ) )
                    {
                        slaDefined = inSubSubCategoriesPojo.getSla() * 24 * 60 * 60 * 1000;
                        slaCalculated += slaCalculationUtil.getHoursForDays( key, value, true );
                    }
                    else if ( StringUtils.equals( inSubSubCategoriesPojo.getSlaUnit(),
                                                  CRMDisplayListConstants.CALENDAR_DAYS.getCode() ) )
                    {
                        slaDefined = inSubSubCategoriesPojo.getSla() * 24 * 60 * 60 * 1000;
                        slaCalculated += slaCalculationUtil.getHoursForDays( key, value, false );
                    }
                }
            }
            inTicketPojo.setSlaCalculatedOn( DateUtils.toXMLGregorianCalendar( inCurrentTime ) );
            inTicketPojo.setSlaTime( ( StringUtils.isValidObj( inTicketPojo.getSlaTime() ) ? inTicketPojo.getSlaTime()
                                                                                          : 0 ) + slaCalculated );
            if ( slaCalculated > 0 )
            {
                LOGGER.info( "SLA calculated : " + slaCalculationUtil.getDurationBreakdown( slaCalculated )
                        + "\tSLA defined : " + slaCalculationUtil.getDurationBreakdown( slaDefined ) );
                isSLACalculated = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception while calculating SLA", ex );
        }
        return isSLACalculated;
    }

    private boolean timeMapAnalysis( Map<Calendar, Calendar> inTimeMap,
                                     List<CrmTicketHistoryPojo> inTicketHistoryList,
                                     Calendar inCreatedOn,
                                     Calendar inCurrentTime )
    {
        LOGGER.info( "Inside SlaQrcThread, timeMapAnalysis" );
        Calendar newFollowUp = null;
        Calendar followUpCreated = null;
        Calendar startSLATime = null;
        Calendar endSLATime = null;
        boolean processed = false;
        try
        {
            if ( CommonValidator.isValidCollection( inTicketHistoryList ) )
            {
                if ( inTicketHistoryList.size() > 1 )
                {
                    SortingComparator<CrmTicketHistoryPojo> sortComparator = new SortingComparator<CrmTicketHistoryPojo>( "recordId" );
                    Collections.sort( inTicketHistoryList, sortComparator );
                }
                startSLATime = inCreatedOn;
                for ( int i = 0; i < inTicketHistoryList.size(); i++ )
                {
                    CrmTicketHistoryPojo ticketHistoryPojo = inTicketHistoryList.get( i );
                    if ( StringUtils.isValidObj( ticketHistoryPojo.getNewFollowupOn() ) )
                    {
                        newFollowUp = DateUtils.convertXmlCalToCalendar( ticketHistoryPojo.getNewFollowupOn() );
                        followUpCreated = DateUtils.convertXmlCalToCalendar( ticketHistoryPojo.getCreatedTime() );
                        if ( followUpCreated.before( newFollowUp ) )
                        {
                            endSLATime = followUpCreated;
                            inTimeMap.put( startSLATime, endSLATime );
                            startSLATime = newFollowUp;
                        }
                        else if ( followUpCreated.after( newFollowUp ) || followUpCreated.equals( newFollowUp ) )
                        {
                            startSLATime = newFollowUp;
                        }
                    }
                }
                if ( startSLATime.before( inCurrentTime ) )
                {
                    inTimeMap.put( startSLATime, inCurrentTime );
                }
            }
            else
            {
                inTimeMap.put( inCreatedOn, inCurrentTime );
            }
            if ( inTimeMap.size() > 0 )
            {
                processed = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while preparing time map", ex );
        }
        return processed;
    }
}
