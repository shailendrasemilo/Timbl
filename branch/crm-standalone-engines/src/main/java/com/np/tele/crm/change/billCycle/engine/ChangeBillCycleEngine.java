package com.np.tele.crm.change.billCycle.engine;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.change.billCycle.engine.bm.ChangeBillCycleManagerImpl;
import com.np.tele.crm.change.billCycle.engine.bm.IChangeBillCycleManager;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public class ChangeBillCycleEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger     LOGGER                 = Logger.getLogger( ChangeBillCycleEngine.class );
    private IChangeBillCycleManager changeBillCycleManager = null;

    public IChangeBillCycleManager getChangeBillCycleManager()
    {
        return changeBillCycleManager;
    }

    public void setChangeBillCycleManager( IChangeBillCycleManager inChangeBillCycleManager )
    {
        changeBillCycleManager = inChangeBillCycleManager;
    }

    private boolean process( String inAuthority, int inProcessChunkSize, String inBillCycleDate )
    {
        return getChangeBillCycleManager().changeCustomersBillCycle( inAuthority, inProcessChunkSize, inBillCycleDate );
    }

    public void init()
    {
        LOGGER.info( "Change Bill Cycle Engine initiated..." );
        setChangeBillCycleManager( new ChangeBillCycleManagerImpl() );
    }

    public static void main( String[] args )
    {
        if ( StringUtils.isNotBlank( args[0] ) && StringUtils.isNotBlank( args[1] ) && StringUtils.isNotBlank( args[2] ) )
        {
            String authority = args[0];
            int chunkSize = Integer.valueOf( args[1] );
            String processingDate = args[2];
            Date pDate = DateUtils.getDate( processingDate, "yyyy-MM-dd" ).getTime();
            Calendar cal = Calendar.getInstance();
            Date date = cal.getTime();
            long minDiff = DateUtils.minuteDiff( pDate, date );
            if ( minDiff <= 30 )
            {
                LOGGER.info( "Going to sleep" );
                try
                {
                    Thread.sleep( ( 30 - minDiff ) * 1000 * 60 );
                }
                catch ( InterruptedException ex )
                {
                    LOGGER.info( "Error while sleep" + ex );
                }
            }
            LOGGER.info( "Authority : " + authority );
            LOGGER.info( "Chunk Size : " + chunkSize );
            ChangeBillCycleEngine changeBillCycleEngine = new ChangeBillCycleEngine();
            changeBillCycleEngine.init();
            boolean processFlag = true;
            while ( processFlag )
            {
                processFlag = changeBillCycleEngine.process( authority, chunkSize, processingDate );
                LOGGER.info( "Returned to main, is further processing required : " + ( processFlag ? "Yes" : "No" ) );
                try
                {
                    Thread.sleep( 60 * 60 * 60 );
                }
                catch ( InterruptedException ex )
                {
                    LOGGER.error( "Interrupted Exception : ", ex );
                }
            }
        }
    }
}
