package com.np.tele.crm.sla.engine.utils;

import java.util.Properties;

import org.apache.log4j.Logger;

import com.np.tele.crm.utils.StringUtils;

public class SLAPropertyUtils
    extends com.np.tele.crm.utils.PropertyUtils
{
    private static Logger logger = Logger.getLogger( SLAPropertyUtils.class );

    public static String getString( Properties props, String key, String defaultValue )
    {
        String value = "";
        try
        {
            value = props.getProperty( key );
        }
        catch ( Exception ex )
        {
            logger.error( "Exception fetching property " + key + " ; Defaulting to " + defaultValue
                    + " ; Exception is : " + ex );
        }
        if ( StringUtils.isEmpty( value ) )
        {
            value = defaultValue;
        }
        if ( key.toLowerCase().indexOf( "password" ) > -1 )
        {
            logger.warn( key + "=" + "****************" );
        }
        else
        {
            logger.warn( key + "=" + value );
        }
        return value.trim();
    }

    public static int getInt( Properties props, String key, int defaultValue )
    {
        Integer value = null;
        try
        {
            String str = props.getProperty( key );
            if ( StringUtils.isEmpty( str ) )
            {
                value = defaultValue;
            }
            else
            {
                value = Integer.parseInt( props.getProperty( key ) );
            }
        }
        catch ( Exception ex )
        {
            logger.error( "Exception fetching property " + key + " ; Defaulting to " + defaultValue
                    + " ; Exception is : " + ex );
        }
        if ( value == null )
        {
            value = defaultValue;
        }
        logger.warn( key + "=" + value );
        return value;
    }

    public static long getLong( Properties props, String key, long defaultValue )
    {
        Long value = null;
        try
        {
            String str = props.getProperty( key );
            if ( StringUtils.isEmpty( str ) )
            {
                value = defaultValue;
            }
            else
            {
                value = Long.parseLong( props.getProperty( key ) );
            }
        }
        catch ( Exception ex )
        {
            logger.error( "Exception fetching property " + key + " ; Defaulting to " + defaultValue
                    + " ; Exception is : " + ex );
        }
        if ( value == null )
        {
            value = defaultValue;
        }
        logger.warn( key + "=" + value );
        return value;
    }

    public static boolean getBoolean( Properties props, String key, boolean defaultValue )
    {
        Boolean value = null;
        try
        {
            String str = props.getProperty( key );
            if ( StringUtils.isEmpty( str ) )
            {
                value = defaultValue;
            }
            else
            {
                value = Boolean.parseBoolean( str );
            }
        }
        catch ( Exception ex )
        {
            logger.warn( "Exception fetching property " + key + " ; Defaulting to " + defaultValue
                    + " ; Exception is : " + ex );
        }
        if ( value == null )
        {
            value = defaultValue;
        }
        logger.info( key + "=" + value );
        return value;
    }

    public static char getChar( Properties props, String key, char defaultValue )
    {
        char value = ' ';
        try
        {
            String str = props.getProperty( key );
            if ( StringUtils.isEmpty( str ) )
            {
                value = defaultValue;
            }
            else
            {
                value = str.charAt( 0 );
            }
        }
        catch ( Exception ex )
        {
            logger.error( "Exception fetching property " + key + " ; Defaulting to " + defaultValue
                    + " ; Exception is : " + ex );
        }
        if ( value == ' ' )
        {
            value = defaultValue;
        }
        logger.warn( key + "=" + value );
        return value;
    }
}
