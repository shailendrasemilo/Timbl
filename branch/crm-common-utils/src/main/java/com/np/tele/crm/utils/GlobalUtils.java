package com.np.tele.crm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

import org.apache.log4j.Logger;

public class GlobalUtils
{
    private static final Logger LOGGER = Logger.getLogger( GlobalUtils.class );

    public static <E> void executeSpecificMethod( Class inClass, String inMethodName )
    {
        try
        {
            Method method = inClass.getDeclaredMethod( inMethodName, null );
            if ( StringUtils.isValidObj( method ) )
            {
                int modifier = method.getModifiers();
                if ( Modifier.isStatic( modifier ) && Modifier.isPublic( modifier ) && !method.isSynthetic()
                        && !StringUtils.equals( method.getReturnType() + "", "void" ) )
                {
                    try
                    {
                        LOGGER.info( "Invoking Method -- " + method.getName() );
                        method.invoke( null, null );
                    }
                    catch ( IllegalArgumentException ex )
                    {
                        LOGGER.error( "Exception: ", ex );
                    }
                    catch ( IllegalAccessException ex )
                    {
                        LOGGER.error( "Exception: ", ex );
                    }
                    catch ( InvocationTargetException ex )
                    {
                        LOGGER.error( "Exception: ", ex );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception: ", ex );
        }
    }

    public static void executeStaticNoArgsMethods( Class<?> c )
    {
        Method[] methods = c.getDeclaredMethods();
        for ( Method method : methods )
        {
            int modifier = method.getModifiers();
            Class<?>[] params = method.getParameterTypes();
            if ( Modifier.isStatic( modifier ) && Modifier.isPublic( modifier ) && !method.isSynthetic()
                    && params.length == 0 && !StringUtils.equals( method.getReturnType() + "", "void" ) )
            {
                try
                {
                    LOGGER.info( "Invoking Method -- " + method.getName() );
                    method.invoke( null, null );
                }
                catch ( IllegalArgumentException ex )
                {
                    LOGGER.error( "Exception: ", ex );
                }
                catch ( IllegalAccessException ex )
                {
                    LOGGER.error( "Exception: ", ex );
                }
                catch ( InvocationTargetException ex )
                {
                    LOGGER.error( "Exception: ", ex );
                }
            }
        }
    }

    public static Object fillDefaultValues( Class<?> c, Class<?> fieldType, Object defaultVal )
    {
        Field[] fields = c.getDeclaredFields();
        Object obj = null;
        try
        {
            obj = c.newInstance();
            for ( Field field : fields )
            {
                int modifier = field.getModifiers();
                if ( Modifier.isProtected( modifier ) && !field.isSynthetic() && ( field.getType() == fieldType ) )
                {
                    CRMUtils.setSimpleProperty( obj, field.getName(), defaultVal );
                }
            }
        }
        catch ( IllegalArgumentException ex )
        {
            LOGGER.error( "Exception: ", ex );
        }
        catch ( IllegalAccessException ex )
        {
            LOGGER.error( "Exception: ", ex );
        }
        catch ( InstantiationException ex )
        {
            LOGGER.error( "Exception: ", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception: ", ex );
        }
        return obj;
    }

    public static <E> E loadClass( String inClass )
    {
        Object obj = null;
        try
        {
            try
            {
                obj = Class.forName( inClass ).newInstance();
            }
            catch ( InstantiationException ex )
            {
                LOGGER.error( "Exception", ex );
            }
            catch ( IllegalAccessException ex )
            {
                LOGGER.error( "Exception", ex );
            }
        }
        catch ( ClassNotFoundException ex )
        {
            LOGGER.error( "Exception", ex );
        }
        if ( StringUtils.isValidObj( obj ) )
        {
            return (E) obj;
        }
        return null;
    }

    public static boolean isValidAmount( Double inAmount )
    {
        if ( StringUtils.isValidObj( inAmount ) )
        {
            if ( inAmount.doubleValue() > 0 )
            {
                return true;
            }
        }
        return false;
    }
}
