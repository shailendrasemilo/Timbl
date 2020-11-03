package com.np.tele.crm.billing.utils;

import java.io.IOException;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.apache.log4j.Logger;

public class BillingHandler
    implements SOAPHandler<SOAPMessageContext>
{
    private static final Logger LOGGER = Logger.getLogger( BillingHandler.class );

    @Override
    public void close( MessageContext inContext )
    {
    }

    @Override
    public boolean handleMessage( SOAPMessageContext inContext )
    {
        SOAPMessage message = inContext.getMessage();
        boolean isOutboundMessage = (Boolean) inContext.get( MessageContext.MESSAGE_OUTBOUND_PROPERTY );
        if ( isOutboundMessage )
        {
            LOGGER.info( "OUTBOUND MESSAGE\n" );
        }
        else
        {
            LOGGER.info( "INBOUND MESSAGE\n" );
        }
        try
        {
            message.writeTo( System.out );
        }
        catch ( SOAPException e )
        {
            LOGGER.error( "getting error while in handleMessage "+e );
        }
        catch ( IOException e )
        {
            LOGGER.error( "getting error while in handleMessage "+e );
        }
        return false;
    }

    @Override
    public boolean handleFault( SOAPMessageContext inContext )
    {
        SOAPMessage message = inContext.getMessage();
        try
        {
            message.writeTo( System.out );
        }
        catch ( SOAPException e )
        {
            LOGGER.error( "getting error while in handleFault "+e );
        }
        catch ( IOException e )
        {
            LOGGER.error( "getting error while in handleFault "+e );
        }
        return false;
    }

    @Override
    public Set<QName> getHeaders()
    {
        // TODO Auto-generated method stub
        return null;
    }
}
