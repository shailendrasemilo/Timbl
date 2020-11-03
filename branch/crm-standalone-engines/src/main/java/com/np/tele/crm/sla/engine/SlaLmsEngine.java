package com.np.tele.crm.sla.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.np.tele.crm.sla.engine.constants.IApplicationConstants;
import com.np.tele.crm.sla.engine.threads.SlaLmsThread;
import com.np.tele.crm.sla.engine.utils.SLACommonUtils;
import com.np.tele.crm.sla.engine.utils.SLAPropertyUtils;
import com.np.tele.crm.utils.SLACalculationUtil;
import com.np.tele.crm.utils.StringUtils;

public class SlaLmsEngine
{
    static
    {
        DOMConfigurator.configureAndWatch( System.getProperty( "log4j.configuration" ) );
    }
    private static final Logger LOGGER                = Logger.getLogger( SlaLmsEngine.class );
    public static Properties    applicationProperties = null;
    private static boolean      isLmsSlaActive        = false;

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
            isLmsSlaActive = SLAPropertyUtils.getBoolean( applicationProperties,
                                                          IApplicationConstants.SLA_LMS_MODULE_ACTIVE, isLmsSlaActive );
            if ( isLmsSlaActive )
            {
                SLACalculationUtil slaCalculationUtil = new SLACalculationUtil( SLACommonUtils.getHolidayList() );
                LOGGER.info( "Going to process SLA for LMS" );
                Thread slaLmsThread = new Thread( new SlaLmsThread( applicationProperties, slaCalculationUtil ) );
                slaLmsThread.setName( "SLA LMS Thread" );
                slaLmsThread.start();
            }
        }
    }
}
