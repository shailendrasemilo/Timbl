package com.np.tele.crm.read.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.cms.constants.IAppCons;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.DateUtils;

public class UploadEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( IAppCons.LOG4J_CONFIG_FILE ) );
    }
    private static final Logger LOGGER         = Logger.getLogger( UploadEngine.class );
    public static Properties    appConfigProps = new Properties();

    public static void main( String[] args )
    {
        System.out.println( DateUtils.getCurrentDateInFormat( IDateConstants.yyMMddHHmmSS ) );
        final String appConfigPath = System.getProperty( IAppCons.APP_CONFIG_FILE );
        try
        {
            LOGGER.info( " appConfigPath : " + appConfigPath );
            appConfigProps.load( new FileInputStream( appConfigPath ) );
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
        Thread monitorThread = new Thread( new MonitorThread( appConfigProps ) );
        monitorThread.setName( "Monitoring Thread" );
        monitorThread.start();
    }
}
