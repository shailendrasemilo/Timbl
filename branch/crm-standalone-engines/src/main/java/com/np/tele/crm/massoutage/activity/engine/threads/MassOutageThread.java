package com.np.tele.crm.massoutage.activity.engine.threads;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.np.tele.crm.massoutage.activity.engine.bm.IMassOutageActivityEngineManager;
import com.np.tele.crm.massoutage.activity.engine.bm.OutageActivityEngineMngrImpl;
import com.np.tele.crm.sla.engine.constants.IApplicationConstants;
import com.np.tele.crm.sla.engine.threads.SlaInaThread;
import com.np.tele.crm.sla.engine.utils.SLAPropertyUtils;

public class MassOutageThread
    implements Runnable
{
    private static final Logger              LOGGER                      = Logger.getLogger( SlaInaThread.class );
    private int                              threadSleepTimeInMinute     = 15;
    private IMassOutageActivityEngineManager outageActivityEngineManager = null;

    public IMassOutageActivityEngineManager getOutageActivityEngineManager()
    {
        return outageActivityEngineManager;
    }

    public void setOutageActivityEngineManager( IMassOutageActivityEngineManager inOutageActivityEngineManager )
    {
        outageActivityEngineManager = inOutageActivityEngineManager;
    }

    public MassOutageThread( Properties inProperties )
    {
        setOutageActivityEngineManager( new OutageActivityEngineMngrImpl() );
        threadSleepTimeInMinute = SLAPropertyUtils.getInt( inProperties,
                                                           IApplicationConstants.MASS_OUTAGE_THREAD_SLEEP_TIME_MINUTE,
                                                           threadSleepTimeInMinute );
    }

    @Override
    public void run()
    {
        while ( true )
        {
            getOutageActivityEngineManager().getListOfMassOutage();
            try
            {
                Thread.sleep( threadSleepTimeInMinute * 60 * 1000 );
            }
            catch ( InterruptedException ex )
            {
                LOGGER.info( "InterruptedException:" + ex.getMessage() );
            }
        }
    }
}
