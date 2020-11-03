package com.np.tele.crm.ticket.close.engine.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CrmQrcClient;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CloseTicketEngineManagerImpl
    implements ICloseTicketEngineManager
{
    private static final Logger LOGGER = Logger.getLogger( CloseTicketEngineManagerImpl.class );

    @Override
    public boolean closeResolvedTickets( String inAuthority, byte inHourDifference, int inProcessChunkSize )
    {
        LOGGER.info( "Inside CloseTicketEngineManagerImpl, closeResolvedTickets" );
        CrmQrcDto crmQrcDto = null;
        CrmSrTicketsPojo srTicketPojo = null;
        CrmQrcService qrcService = null;
        boolean needProcessing = true;
        try
        {
            srTicketPojo = new CrmSrTicketsPojo();
            srTicketPojo.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
            srTicketPojo.setSrResolvedOn( DateUtils.getBackTimeByHour( inHourDifference ) );
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setUserId( inAuthority );
            crmQrcDto.setCrmSrTicketsPojo( srTicketPojo );
            crmQrcDto.setMaxResultSize( inProcessChunkSize );
            qrcService = new CrmQrcClient();
            crmQrcDto = qrcService.qrcOperations( ServiceParameter.VIEW.getParameter(),
                                                  CRMParameter.SERVICE_REQUEST.getParameter(), crmQrcDto );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
            {
                List<CrmSrTicketsPojo> crmSrTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
                crmSrTicketsPojos.addAll( crmQrcDto.getCrmSrTicketsPojos() );
                LOGGER.info( " Working List Size : " + crmSrTicketsPojos.size() );
                for ( CrmSrTicketsPojo ticketPojo : crmSrTicketsPojos )
                {
                    // LOGGER.info( " Working Ticket ID : " + ticketPojo.getTicketId() );
                    ticketPojo.setStatus( CRMStatusCode.CLOSE.getStatusCode() );
                    ticketPojo.setLastModifiedBy( inAuthority );
                    crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
                    crmQrcDto = qrcService.ticketOperations( CrmActionEnum.CLOSED.getActionCode(),
                                                             CrmSrTicketsPojo.class.getSimpleName(), crmQrcDto );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Ticket successfully closed, Ticket ID : " + ticketPojo.getTicketId() );
                    }
                }
            }
            else
            {
                needProcessing = false;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while closing resolved tickets ", ex );
        }
        return needProcessing;
    }
}
