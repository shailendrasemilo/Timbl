package com.np.tele.crm.alerts.engine;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.alerts.engine.bm.AlertsEngineManagerImpl;
import com.np.tele.crm.alerts.engine.bm.IAlertsEngineManager;

public class AlertsEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger  LOGGER              = Logger.getLogger( AlertsEngine.class );
    private IAlertsEngineManager alertsEngineManager = null;

    public void setAlertsEngineManager( IAlertsEngineManager inAlertsEngineManager )
    {
        alertsEngineManager = inAlertsEngineManager;
    }

    public IAlertsEngineManager getAlertsEngineManager()
    {
        return alertsEngineManager;
    }

    private void proces()
    {
        do
        {
            getAlertsEngineManager().processAlerts();
            try
            {
                Thread.sleep( 30 * 60 * 60 );
            }
            catch ( InterruptedException ex )
            {
                LOGGER.info( "InterruptedException:" + ex.getMessage() );
            }
        }
        while ( true );
    }

    public void init()
    {
        LOGGER.info( "Alerts Engine initiated..." );
        setAlertsEngineManager( new AlertsEngineManagerImpl() );
    }

    public static void main( String[] args )
    {
        AlertsEngine alertsEngine = new AlertsEngine();
        alertsEngine.init();
        alertsEngine.proces();
    }
}
