package com.np.tele.crm.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

import org.apache.log4j.Logger;

public final class FileUtils
{
    private static final Logger LOGGER = Logger.getLogger( FileUtils.class );

    protected FileUtils()
    {
    }

    public static InputStream getInputStreamForFile( String inFileName )
    {
        InputStream is = null;
        String extInFileName = "ext_" + inFileName;
        LOGGER.info( "Got getInputStream request for file : " + inFileName + " Looking up first : " + extInFileName );
        try
        {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream( extInFileName );
            if ( is == null )
            {
                LOGGER.info( "Not Found : " + extInFileName );
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream( inFileName );
                if ( is != null )
                {
                    LOGGER.info( "Found : " + inFileName );
                }
                else
                {
                    LOGGER.info( "Not Found : " + inFileName + ", " + extInFileName );
                }
            }
            else
            {
                LOGGER.info( "Found : " + extInFileName );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.warn( "Exception loading extFile : " + extInFileName + " - " + ex, ex );
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream( inFileName );
        }
        if ( is == null )
        {
            throw new IllegalArgumentException( "Can't find file " + inFileName + " on classpath" );
        }
        return is;
    }

    public static String getFilePath( String inFileName )
    {
        URL url = null;
        LOGGER.info( "Got getFilePath request for file : " + inFileName + " Looking up first in extFile : "
                + inFileName );
        try
        {
            url = Thread.currentThread().getContextClassLoader().getResource( inFileName );
            if ( url == null )
            {
                LOGGER.info( "Not Found extFile: " + inFileName );
                url = Thread.currentThread().getContextClassLoader().getResource( inFileName );
                if ( url != null )
                {
                    LOGGER.info( "Found : " + inFileName );
                }
                else
                {
                    LOGGER.info( "Not Found : " + inFileName );
                }
            }
            else
            {
                LOGGER.info( "Found extFile: " + inFileName );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.warn( "Exception loading extFile : " + inFileName + " - " + ex, ex );
            url = Thread.currentThread().getContextClassLoader().getResource( inFileName );
        }
        if ( url != null )
        {
            File file = new File( url.getPath() );
            if ( file.exists() )
            {
                return url.getPath();
            }
        }
        return null;
    }

    public static URI getURIForFile( String inFileName )
        throws URISyntaxException
    {
        URL url = null;
        LOGGER.info( "Got getURIForFile request for file : " + inFileName );
        try
        {
            url = Thread.currentThread().getContextClassLoader().getResource( inFileName );
            if ( url != null )
            {
                LOGGER.info( "Found URI: " + inFileName );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.warn( "Exception locating URI for file : " + inFileName + " - " + ex, ex );
        }
        if ( url == null )
        {
            throw new IllegalArgumentException( "Can't find file " + inFileName + " on classpath" );
        }
        return url.toURI();
    }

    protected String getFileAsString( final String inFilePath )
    {
        final StringBuilder sbuffer = new StringBuilder();
        final File file = new File( inFilePath );
        if ( file.exists() && file.canRead() )
        {
            BufferedReader reader = null;
            String str = "";
            try
            {
                reader = new BufferedReader( new FileReader( file ) );
                while ( null != ( str = reader.readLine() ) )
                {
                    sbuffer.append( str );
                }
            }
            catch ( IOException e )
            {
                LOGGER.error( "Exception file while reading file " + inFilePath, e );
                throw new IllegalArgumentException( "Exception file while reading file " + inFilePath, e );
            }
            finally
            {
                try
                {
                    reader.close();
                }
                catch ( IOException e )
                {
                    LOGGER.warn( "Exception file while reading file " + inFilePath, e );
                }
            }
        }
        else
        {
            throw new IllegalArgumentException( "Can't find/read file :" + inFilePath );
        }
        return sbuffer.toString();
    }

    protected Properties loadProperties( final String inFilePath )
    {
        final Properties props = new Properties();
        final File file = new File( inFilePath );
        if ( file.exists() && file.canRead() )
        {
            try
            {
                props.load( new FileInputStream( file ) );
            }
            catch ( IOException e )
            {
                LOGGER.error( "Exception file while reading file " + inFilePath, e );
                throw new IllegalArgumentException( "IOException loading properties : " + inFilePath, e );
            }
        }
        else
        {
            throw new IllegalArgumentException( "Error loading properties. Can't find/read file :" + inFilePath );
        }
        return props;
    }
}
