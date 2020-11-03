package com.np.tele.crm.ticket.resolve.engine;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.ticket.resolve.engine.bm.IResolveTicketEngineManager;
import com.np.tele.crm.ticket.resolve.engine.bm.ResolveTicketEngineManagerImpl;
import com.np.tele.crm.utils.StringUtils;

public class ResolveTicketEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger         LOGGER                     = Logger.getLogger( ResolveTicketEngine.class );
    private IResolveTicketEngineManager resolveTicketEngineManager = null;

    public IResolveTicketEngineManager getResovleTicketEngineManager()
    {
        return resolveTicketEngineManager;
    }

    public void setResovleTicketEngineManager( IResolveTicketEngineManager inResolveTicketEngineManager )
    {
        resolveTicketEngineManager = inResolveTicketEngineManager;
    }

    private boolean proces( String inAuthority )
    {
        return getResovleTicketEngineManager().resovleOpenTickets( inAuthority );
    }

    public void init()
    {
        LOGGER.info( "Resovle Tickets Engine initiated..." );
        setResovleTicketEngineManager( new ResolveTicketEngineManagerImpl() );
    }

    public static void main( String[] args )
    {
        if ( StringUtils.isNotBlank( args[0] ) )
        {
            String authority = args[0];
            LOGGER.info( "Authority : " + authority );
            ResolveTicketEngine resolveTicketEngine = new ResolveTicketEngine();
            resolveTicketEngine.init();
            boolean processFlag = true;
            while ( true )
            {
                processFlag = resolveTicketEngine.proces( authority );
                LOGGER.info( "Returned to main, is further processing required : " + ( processFlag ? "Yes" : "No" ) );
                try
                {
                    Thread.sleep( 60 * 60 * 60 );
                }
                catch ( InterruptedException ex )
                {
                    LOGGER.info( "InterruptedException:" + ex.getMessage() );
                }
            }
        }
    }
}
