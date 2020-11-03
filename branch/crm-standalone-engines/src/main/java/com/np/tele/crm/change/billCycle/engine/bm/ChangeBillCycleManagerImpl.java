package com.np.tele.crm.change.billCycle.engine.bm;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CrmBillCyclePojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ChangeBillCycleManagerImpl
    implements IChangeBillCycleManager
{
    private static final Logger LOGGER = Logger.getLogger( ChangeBillCycleManagerImpl.class );

    @Override
    public boolean changeCustomersBillCycle( String inAuthority, int inProcessChunkSize, String inProcessingDate )
    {
        LOGGER.info( "Inside ChangeBillCycleManagerImpl, +changeCustomersBillCycle" );
        boolean isProcessed = false;
        List<CrmBillCyclePojo> crmBillCyclePojos = null;
        try
        {
            crmBillCyclePojos = getBillCycleDataToProcess( inAuthority, inProcessChunkSize, inProcessingDate );
            if ( CommonValidator.isValidCollection( crmBillCyclePojos ) )
            {
                isProcessed = processFetchedRecords( inAuthority, crmBillCyclePojos );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while changing bill cycle for customers", ex );
        }
        return isProcessed;
    }

    private List<CrmBillCyclePojo> getBillCycleDataToProcess( String inAuthority,
                                                              int inProcessChunkSize,
                                                              String inProcessingDate )
    {
        LOGGER.info( "Inside ChangeBillCycleManagerImpl, -getBillCycleDataToProcess" );
        CrmQrcDto crmQrcDto = null;
        CrmQrcService qrcService = null;
        CrmBillCyclePojo billCyclePojo = null;
        List<CrmBillCyclePojo> crmBillCyclePojos = null;
        try
        {
            crmQrcDto = new CrmQrcDto();
            billCyclePojo = new CrmBillCyclePojo();
            Calendar cal = DateUtils.getDate( inProcessingDate, "yyyy-MM-dd" );
            DateUtils.setCurrentTime( cal );
            billCyclePojo.setProcessingDate( DateUtils.toXMLGregorianCalendar( cal ) );
            billCyclePojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
            crmQrcDto.setMaxResultSize( inProcessChunkSize );
            crmQrcDto.setBillCyclePojo( billCyclePojo );
            qrcService = new CrmQrcClient();
            crmQrcDto = qrcService.customerProfileOperations( ServiceParameter.LIST.getParameter(),
                                                              IAppConstants.METHOD_VIEW_CUSTOMER_BILLCYCLE, crmQrcDto );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                crmBillCyclePojos = crmQrcDto.getCrmBillCyclePojosList();
                LOGGER.info( "Size of fetched records for bill cycle change : " + crmBillCyclePojos.size() );
            }
            else
            {
                LOGGER.info( "No record found for bill cycle change for provided date : " + inProcessingDate );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetch records to change bill cycle ", ex );
        }
        return crmBillCyclePojos;
    }

    private boolean processFetchedRecords( String inAuthority, List<CrmBillCyclePojo> inCrmBillCyclePojos )
    {
        LOGGER.info( "Inside ChangeBillCycleManagerImpl, -processFetchedRecords" );
        boolean batchProcessed = false;
        CrmQrcDto crmQrcDto = null;
        CrmQrcService qrcService = null;
        try
        {
            crmQrcDto = new CrmQrcDto();
            qrcService = new CrmQrcClient();
            CrmSrTicketsPojo ticketPojo = new CrmSrTicketsPojo();
            for ( CrmBillCyclePojo crmBillCyclePojo : inCrmBillCyclePojos )
            {
                crmBillCyclePojo.setLastModifiedBy( inAuthority );
                crmQrcDto.setBillCyclePojo( crmBillCyclePojo );
                crmQrcDto = qrcService
                        .customerProfileOperations( ServiceParameter.MODIFY.getParameter(),
                                                    IAppConstants.SYNC_BILL_CYCLE_WITH_BILLING, crmQrcDto );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    //resolve call WITH crmBillCyclePojo.getTicketId();
                    ticketPojo.setSrId( crmBillCyclePojo.getTicketId() );
                    ticketPojo.setLastModifiedBy( inAuthority );
                    ticketPojo.setResolvedBy( inAuthority );
                    crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
                    qrcService.ticketOperations( CrmActionEnum.RESOLVED.getActionCode(),
                                                 CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
                    LOGGER.info( "Bill cycle has been updated for customer Id : " + crmBillCyclePojo.getCustomerId() );
                }
                else
                {
                    //set follow up with current date time
                    ticketPojo.setSrId( crmBillCyclePojo.getTicketId() );
                    ticketPojo.setLastModifiedBy( inAuthority );
                    ticketPojo.setFollowupOn( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
                    CrmTicketHistoryPojo history = new CrmTicketHistoryPojo();
                    history.setAction( CrmActionEnum.FOLLOW_UP.getActionCode() );
                    history.setCreatedBy( inAuthority );
                    history.setRemarks( crmQrcDto.getBillingErrorCode() );
                    history.setTicketId( crmBillCyclePojo.getTicketId() );
                    crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
                    crmQrcDto.setTicketHistory( history );
                    qrcService.ticketOperations( CrmActionEnum.FOLLOW_UP.getActionCode(),
                                                 CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
                    LOGGER.info( "Bill cycle is already in sync with billing for customer Id : "
                            + crmBillCyclePojo.getCustomerId() );
                }
            }
            batchProcessed = true;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing fetched records for bill cycle change ", ex );
        }
        return batchProcessed;
    }
}
