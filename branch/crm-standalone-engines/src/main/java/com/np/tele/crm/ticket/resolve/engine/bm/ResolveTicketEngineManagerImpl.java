package com.np.tele.crm.ticket.resolve.engine.bm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ResolveTicketEngineManagerImpl
    implements IResolveTicketEngineManager
{
    private static final Logger LOGGER = Logger.getLogger( ResolveTicketEngineManagerImpl.class );

    @Override
    public boolean resovleOpenTickets( String inAuthority )
    {
        LOGGER.info( "Inside ResolveTicketEngineManagerImpl, resovleOpenTickets" );
        CrmQrcDto crmQrcDto = null;
        CrmSrTicketsPojo srTicketPojo = null;
        CrmQrcService qrcService = null;
        boolean needProcessing = true;
        try
        {
            qrcService = new CrmQrcClient();
            // Base Plan Migration
            srTicketPojo = new CrmSrTicketsPojo();
            srTicketPojo.setStatus( CRMStatusCode.OPEN.getStatusCode() );
            srTicketPojo.setProcessingDate( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setActivityAction( QRCRolCategories.PLAN_MIGRATION_REQUEST.getEvent() );
            crmQrcDto.setCrmSrTicketsPojo( srTicketPojo );
            crmQrcDto.setUserId( inAuthority );
            needProcessing = retrieveAndProcess( inAuthority, crmQrcDto, qrcService, needProcessing,
                                                 CRMCustomerActivityActions.BASE_PLAN_MIGRATION );
            // ADD ON Deactivation
            srTicketPojo = new CrmSrTicketsPojo();
            srTicketPojo.setStatus( CRMStatusCode.OPEN.getStatusCode() );
            srTicketPojo.setProcessingDate( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setActivityAction( QRCRolCategories.ADDON_DEACTIVATION.getEvent() );
            crmQrcDto.setCrmSrTicketsPojo( srTicketPojo );
            crmQrcDto.setUserId( inAuthority );
            needProcessing = retrieveAndProcess( inAuthority, crmQrcDto, qrcService, needProcessing,
                                                 CRMCustomerActivityActions.ADDON_PLAN_REMOVAL );
            // ADD ON Activation
            srTicketPojo = new CrmSrTicketsPojo();
            srTicketPojo.setStatus( CRMStatusCode.OPEN.getStatusCode() );
            srTicketPojo.setProcessingDate( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setActivityAction( QRCRolCategories.ADDON_ACTIVATION.getEvent() );
            crmQrcDto.setCrmSrTicketsPojo( srTicketPojo );
            crmQrcDto.setUserId( inAuthority );
            needProcessing = retrieveAndProcess( inAuthority, crmQrcDto, qrcService, needProcessing,
                                                 CRMCustomerActivityActions.ADDON_PLAN_CHANGE );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while resolving closed tickets ", ex );
        }
        return needProcessing;
    }

    private boolean retrieveAndProcess( String inAuthority,
                                        CrmQrcDto crmQrcDto,
                                        CrmQrcService qrcService,
                                        boolean needProcessing,
                                        CRMCustomerActivityActions inActivity )
        throws SOAPException_Exception
    {
        crmQrcDto = qrcService.qrcOperations( ServiceParameter.VIEW.getParameter(),
                                              CRMParameter.SERVICE_REQUEST.getParameter(), crmQrcDto );
        LOGGER.info( crmQrcDto.getStatusCode() + " >> " + crmQrcDto.getStatusDesc() );
        if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                && CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
        {
            List<CrmSrTicketsPojo> crmSrTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
            crmSrTicketsPojos.addAll( crmQrcDto.getCrmSrTicketsPojos() );
            LOGGER.info( " Working List Size : " + crmSrTicketsPojos.size() );
            for ( CrmSrTicketsPojo ticketPojo : crmSrTicketsPojos )
            {
                if ( StringUtils.isNotBlank( ticketPojo.getAPITransactionId() )
                        && DateUtils.convertXmlCalToDate( ticketPojo.getProcessingDate() )
                                .before( Calendar.getInstance().getTime() ) )
                {
                    crmQrcDto.setCustomerId( ticketPojo.getMappingId() );
                    crmQrcDto.setActivityAction( inActivity.getActionCode() );
                    crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
                    crmQrcDto = qrcService.qrcOperations( ServiceParameter.UPDATE.getParameter(),
                                                          CrmPlanDetailsPojo.class.getSimpleName(), crmQrcDto );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                    {
                        ticketPojo.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
                        ticketPojo
                                .setSrResolvedOn( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
                        ticketPojo.setLastModifiedBy( inAuthority );
                        crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
                        CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                        ticketHistory.setCreatedBy( inAuthority );
                        ticketHistory.setAction( CrmActionEnum.RESOLVED.getActionCode() );
                        ticketHistory.setRemarks( "Ticket resolved for NBC Plan Migration." );
                        crmQrcDto.setTicketHistory( ticketHistory );
                        crmQrcDto = qrcService.ticketOperations( CrmActionEnum.RESOLVED.getActionCode(),
                                                                 CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
                    }
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Ticket successfully resolved, Ticket ID : " + ticketPojo.getTicketId() );
                    }
                }
            }
        }
        else
        {
            needProcessing = false;
        }
        return needProcessing;
    }
}
