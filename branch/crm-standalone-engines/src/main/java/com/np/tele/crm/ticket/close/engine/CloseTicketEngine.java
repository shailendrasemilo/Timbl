package com.np.tele.crm.ticket.close.engine;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.ticket.close.engine.bm.CloseTicketEngineManagerImpl;
import com.np.tele.crm.ticket.close.engine.bm.ICloseTicketEngineManager;
import com.np.tele.crm.utils.StringUtils;

public class CloseTicketEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger       LOGGER                   = Logger.getLogger( CloseTicketEngine.class );
    private ICloseTicketEngineManager closeTicketEngineManager = null;

    public ICloseTicketEngineManager getCloseTicketEngineManager()
    {
        return closeTicketEngineManager;
    }

    public void setCloseTicketEngineManager( ICloseTicketEngineManager inCloseTicketEngineManager )
    {
        closeTicketEngineManager = inCloseTicketEngineManager;
    }

    private boolean proces( String inAuthority, byte inHourDifference, int inProcessChunkSize )
    {
        return getCloseTicketEngineManager().closeResolvedTickets( inAuthority, inHourDifference, inProcessChunkSize );
    }

    public void init()
    {
        LOGGER.info( "Close Tickets Engine initiated..." );
        setCloseTicketEngineManager( new CloseTicketEngineManagerImpl() );
    }

    public static void main( String[] args )
    {
        if ( StringUtils.isNotBlank( args[0] ) && StringUtils.isNotBlank( args[1] ) && StringUtils.isNotBlank( args[2] ) )
        {
            String authority = args[0];
            byte hourDifference = Byte.valueOf( args[1] );
            int chunkSize = Integer.valueOf( args[2] );
            LOGGER.info( "Authority : " + authority );
            LOGGER.info( "Hours Difference : " + hourDifference );
            LOGGER.info( "Chunk Size : " + chunkSize );
            CloseTicketEngine closeTicketEngine = new CloseTicketEngine();
            closeTicketEngine.init();
            boolean processFlag = true;
            while ( true )
            {
                processFlag = closeTicketEngine.proces( authority, hourDifference, chunkSize );
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
