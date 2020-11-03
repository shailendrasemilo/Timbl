package com.np.tele.crm.massoutage.activity.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.massoutage.activity.engine.threads.MassOutageThread;
import com.np.tele.crm.sla.engine.constants.IApplicationConstants;
import com.np.tele.crm.utils.StringUtils;

public class MassoutageActivityEngine
{
    private static final Logger LOGGER                = Logger.getLogger( MassoutageActivityEngine.class );
    public static Properties    applicationProperties = null;
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }

    public static void main( String[] args )
    {
        final String appConfigPath = System.getProperty( IApplicationConstants.APPLICATION_PROPERTIES_FILE );
        try
        {
            LOGGER.info( " appConfigPath : " + appConfigPath );
            applicationProperties = new Properties();
            applicationProperties.load( new FileInputStream( appConfigPath ) );
        }
        catch ( FileNotFoundException e )
        {
            LOGGER.info( "Application configuration properties" + appConfigPath + " not found. Aborting program." );
            System.exit( 0 );
        }
        catch ( IOException e )
        {
            LOGGER.info( "Application configuration properties" + appConfigPath
                    + " gave IOException. Aborting program." );
            System.exit( 0 );
        }
        if ( StringUtils.isValidObj( applicationProperties ) )
        {
            LOGGER.info( "Going to process Mass Outage Activity" );
            Thread massOutageThread = new Thread( new MassOutageThread( applicationProperties ) );
            massOutageThread.setName( "Mass Outage Activity Thread" );
            massOutageThread.start();
        }
    }
}
