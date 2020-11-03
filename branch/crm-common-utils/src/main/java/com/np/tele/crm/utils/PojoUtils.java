package com.np.tele.crm.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

import org.apache.log4j.Logger;

public class PojoUtils<E>
{
    private static final Logger LOGGER = Logger.getLogger( PojoUtils.class );

    public boolean checkPojoEmpty( E pojo )
    {
        try
        {
            if ( StringUtils.isValidObj( pojo ) )
            {
                PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( pojo );
                for ( PropertyDescriptor descriptor : prDesc )
                {
                    String propertyName = descriptor.getName();
                    if ( null != org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) )
                    {
                        LOGGER.info( propertyName + " == "
                                + org.apache.commons.beanutils.PropertyUtils.getPropertyType( pojo, propertyName )
                                + " ==" + org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) );
                        if ( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) instanceof java.lang.Long )
                        {
                            if ( (Long) org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) > 0 )
                            {
                                return false;
                            }
                        }
                        if ( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) instanceof java.lang.Integer )
                        {
                            if ( (Integer) org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) > 0 )
                            {
                                return false;
                            }
                        }
                        if ( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) instanceof java.lang.String )
                        {
                            if ( StringUtils.isNotBlank( (String) org.apache.commons.beanutils.PropertyUtils
                                    .getProperty( pojo, propertyName ) ) )
                            {
                                return false;
                            }
                        }
                    }
                }
            }
            else
            {
                return true;
            }
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "IllegalAccessException:", ex );
        }
        catch ( InvocationTargetException ex )
        {
            LOGGER.error( "InvocationTargetException:", ex );
        }
        catch ( NoSuchMethodException ex )
        {
            LOGGER.error( "NoSuchMethodException:", ex );
        }
        return true;
    }

    public boolean isPojoEmpty( E pojo, Set<String> inProperties )
    {
        try
        {
            if ( StringUtils.isValidObj( pojo ) )
            {
                for ( String propertyName : inProperties )
                {
                    if ( StringUtils
                            .isValidObj( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) ) )
                    {
                        LOGGER.info( propertyName + " == "
                                + org.apache.commons.beanutils.PropertyUtils.getPropertyType( pojo, propertyName )
                                + " ==" + org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) );
                        if ( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) instanceof java.lang.Long )
                        {
                            if ( (Long) org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) > 0 )
                            {
                                return false;
                            }
                        }
                        if ( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) instanceof java.lang.Integer )
                        {
                            if ( (Integer) org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) > 0 )
                            {
                                return false;
                            }
                        }
                        if ( org.apache.commons.beanutils.PropertyUtils.getProperty( pojo, propertyName ) instanceof java.lang.String )
                        {
                            if ( StringUtils.isNotBlank( (String) org.apache.commons.beanutils.PropertyUtils
                                    .getProperty( pojo, propertyName ) ) )
                            {
                                return false;
                            }
                        }
                    }
                }
            }
            else
            {
                return true;
            }
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "IllegalAccessException:", ex );
        }
        catch ( InvocationTargetException ex )
        {
            LOGGER.error( "InvocationTargetException:", ex );
        }
        catch ( NoSuchMethodException ex )
        {
            LOGGER.error( "NoSuchMethodException:", ex );
        }
        return true;
    }
}
