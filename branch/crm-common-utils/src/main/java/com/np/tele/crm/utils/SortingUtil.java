package com.np.tele.crm.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Comparator;

import org.apache.log4j.Logger;

public class SortingUtil<E, T>
    implements Comparator<E>
{
    private static final Logger LOGGER       = Logger.getLogger( SortingUtil.class );
    private String              propertyName = null;
    private T                   t            = null;

    public SortingUtil( String inPropertyName, T t )
    {
        this.propertyName = inPropertyName;
        this.t = t;
    }

    public int compare( E pojo1, E pojo2 )
    {
        if ( StringUtils.isValidObj( pojo1 ) && StringUtils.isValidObj( pojo2 ) )
        {
            try
            {
                Object value1 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo1, propertyName );
                Object value2 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo2, propertyName );
                if ( StringUtils.isValidObj( value1 ) && StringUtils.isValidObj( value2 ) )
                {
                    if ( t instanceof String && value1 instanceof String && value2 instanceof String )
                    {
                        String str1 = (String) value1;
                        String str2 = (String) value2;
                        return str1.compareToIgnoreCase( str2 );
                    }
                    else if ( t instanceof BigDecimal && value1 instanceof String && value2 instanceof String )
                    {
                        String str1 = (String) value1;
                        String str2 = (String) value2;
                        BigDecimal bd1 = new BigDecimal( str1 );
                        BigDecimal bd2 = new BigDecimal( str2 );
                        return bd1.compareTo( bd2 );
                    }
                    else if ( value1 instanceof Integer && value2 instanceof Integer )
                    {
                        Integer str1 = (Integer) value1;
                        Integer str2 = (Integer) value2;
                        return str1.compareTo( str2 );
                    }
                    else if ( value1 instanceof Long && value2 instanceof Long )
                    {
                        Long str1 = (Long) value1;
                        Long str2 = (Long) value2;
                        return str1.compareTo( str2 );
                    }
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
        }
        return 0;
    }
}
