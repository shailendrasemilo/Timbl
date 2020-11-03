package com.np.tele.crm.change.status.engine;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.change.status.engine.bm.ChangeStatusEngineManagerImpl;
import com.np.tele.crm.change.status.engine.bm.IChangeStatusEngineManager;
import com.np.tele.crm.utils.StringUtils;

public class ChangeStatusEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger        LOGGER                    = Logger.getLogger( ChangeStatusEngine.class );
    private IChangeStatusEngineManager changeStatusEngineManager = null;

    public IChangeStatusEngineManager getChangeStatusEngineManager()
    {
        return changeStatusEngineManager;
    }

    public void setChangeStatusEngineManager( IChangeStatusEngineManager inChangeStatusEngineManager )
    {
        changeStatusEngineManager = inChangeStatusEngineManager;
    }

    private boolean proces( String inAuthority, byte inMonthDifference, int inProcessChunkSize )
    {
        return getChangeStatusEngineManager()
                .changeCustomersStatus( inAuthority, inMonthDifference, inProcessChunkSize );
    }

    public void init()
    {
        LOGGER.info( "Change Status Engine initiated..." );
        setChangeStatusEngineManager( new ChangeStatusEngineManagerImpl() );
    }

    public static void main( String[] args )
    {
        if ( StringUtils.isNotBlank( args[0] ) && StringUtils.isNotBlank( args[1] ) && StringUtils.isNotBlank( args[2] ) )
        {
            String authority = args[0];
            byte monthDifference = Byte.valueOf( args[1] );
            int chunkSize = Integer.valueOf( args[2] );
            LOGGER.info( "Authority : " + authority );
            LOGGER.info( "Months Difference : " + monthDifference );
            LOGGER.info( "Chunk Size : " + chunkSize );
            ChangeStatusEngine changeStatusEngine = new ChangeStatusEngine();
            changeStatusEngine.init();
            boolean processFlag = true;
            while ( true )
            {
                processFlag = changeStatusEngine.proces( authority, monthDifference, chunkSize );
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
