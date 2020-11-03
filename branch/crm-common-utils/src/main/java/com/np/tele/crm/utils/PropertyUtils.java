package com.np.tele.crm.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;

public class PropertyUtils
{
    private static Properties   clientDetails      = null;
    private static Properties   moduleDetails      = null;
    //    private static Properties   validationDetails     = null;
    //    private static Properties   formValidationDetails = null;
    private static Properties   cmsPaymentKeys     = null;
    private static Properties   billingMappingKeys = null;
    private static final Logger LOGGER             = Logger.getLogger( PropertyUtils.class );

    private static final synchronized void loadClientDetails()
        throws IOException
    {
        if ( null == clientDetails )
        {
            clientDetails = new Properties();
            InputStream inStream = FileUtils.getInputStreamForFile( IAppConstants.COMMON_UTIL_FILE_NAME );
            try
            {
                clientDetails.load( inStream );
            }
            catch ( IOException ex )
            {
                throw new IOException( IAppConstants.COMMON_UTIL_FILE_NAME + " has not been loaded sucessfully", ex );
            }
        }
    }

    public static String getServiceDetails( String inKey )
    {
        if ( null == clientDetails )
        {
            try
            {
                loadClientDetails();
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Unable to load properties file", ex );
            }
        }
        if ( clientDetails.containsKey( inKey ) )
        {
            return clientDetails.getProperty( inKey );
        }
        return null;
    }

    public static String getModuleDetails( String inKey )
    {
        if ( null == moduleDetails )
        {
            try
            {
                String filePath=PropertyUtils.getServiceDetails( IPropertiesConstant.MODULE_PATH );
                LOGGER.info( "Module file" + filePath );
                loadModuleDetails( filePath );
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Unable to load properties file", ex );
            }
        }
        if ( moduleDetails.containsKey( inKey ) )
        {
            return moduleDetails.getProperty( inKey );
        }
        return null;
    }

    private static final synchronized void loadModuleDetails( String fileName )
        throws IOException
    {
        if ( null == moduleDetails )
        {
            moduleDetails = new Properties();
            InputStream inStream = FileUtils.getInputStreamForFile( fileName );
            try
            {
                moduleDetails.load( inStream );
            }
            catch ( IOException ex )
            {
                throw new IOException( fileName + " has not been loaded sucessfully", ex );
            }
        }
    }

    public static String getCmsKeyValue( String inKey )
    {
        if ( null == cmsPaymentKeys )
        {
            try
            {
                loadCmsPaymentKeys();
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Unable to load properties file", ex );
            }
        }
        if ( cmsPaymentKeys.containsKey( inKey ) )
        {
            return cmsPaymentKeys.getProperty( inKey );
        }
        else
        {
            return "";
        }
    }

    private static final synchronized void loadCmsPaymentKeys()
        throws IOException
    {
        if ( null == cmsPaymentKeys )
        {
            cmsPaymentKeys = new Properties();
            InputStream inStream = FileUtils.getInputStreamForFile( IAppConstants.CRM_CMS_PAYMENT_FILE_NAME );
            try
            {
                cmsPaymentKeys.load( inStream );
            }
            catch ( IOException ex )
            {
                throw new IOException( IAppConstants.CRM_CMS_PAYMENT_FILE_NAME + " has not been loaded sucessfully", ex );
            }
        }
    }

    public static String getBillingMappingKeyValue( String inKey )
    {
        if ( null == billingMappingKeys )
        {
            try
            {
                loadBillingMappingKeys();
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Unable to load properties file", ex );
            }
        }
        if ( billingMappingKeys.containsKey( inKey ) )
        {
            return billingMappingKeys.getProperty( inKey );
        }
        else
        {
            return "";
        }
    }

    private static final synchronized void loadBillingMappingKeys()
        throws IOException
    {
        if ( null == billingMappingKeys )
        {
            billingMappingKeys = new Properties();
            InputStream inStream = FileUtils.getInputStreamForFile( IAppConstants.CRM_BILLING_MAPPING_FILE_NAME );
            try
            {
                billingMappingKeys.load( inStream );
            }
            catch ( IOException ex )
            {
                throw new IOException( IAppConstants.CRM_BILLING_MAPPING_FILE_NAME + " has not been loaded sucessfully",
                                       ex );
            }
        }
    }

    /*public static String getFilePath( String inKey )
    {
        if ( null == clientDetails )
        {
            try
            {
                loadClientDetails();
            }
            catch ( IOException ex )
            {
                LOGGER.error( "Unable to load properties file", ex );
            }
        }
        if ( clientDetails.containsKey( inKey ) )
        {
            return clientDetails.getProperty( inKey );
        }
        return null;
    }*/
}
