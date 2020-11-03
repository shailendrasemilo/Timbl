package com.np.tele.crm.service.comparators;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.StringUtils;

public class PojoComparator<E>
    implements Comparator<E>
{
    private static final Logger LOGGER = Logger.getLogger( PojoComparator.class );

    public int compare( E pojo1, E pojo2 )
    {
        if ( StringUtils.isValidObj( pojo1 ) && StringUtils.isValidObj( pojo2 ) )
        {
            PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( pojo1 );
            for ( PropertyDescriptor descriptor : prDesc )
            {
                String propertyName = descriptor.getName();
                if ( IAppConstants.RESTRICTED_PROPERTIES.contains( propertyName ) )
                {
                    continue;
                }
                try
                {
                    Object value1 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo1, propertyName );
                    Object value2 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo2, propertyName );
                    if ( StringUtils.isValidObj( value1 ) && StringUtils.isValidObj( value2 ) )
                    {
                        if ( ! ( value1 instanceof List ) && !value1.equals( value2 ) )
                        {
                            LOGGER.info( "Value1:" + value1 + " & Value2:" + value2 + " are not equal for property:"
                                    + propertyName );
                            return -1;
                        }
                    }
                    else if ( StringUtils.isValidObj( value1 )
                            || ( StringUtils.isValidObj( value2 ) && StringUtils.isNotBlank( String.valueOf( value2 ) ) ) )
                    {
                        if ( !StringUtils.equals( StringUtils.trimToEmpty( String.valueOf( value1 ) ),
                                                  StringUtils.trimToEmpty( String.valueOf( value2 ) ) ) )
                        {
                            LOGGER.info( "Value1:" + value1 + " & Value2:" + value2 + " are not equal for property:"
                                    + propertyName );
                            return -1;
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
        }
        return 0;
    }

    public int exemptCompare( E pojo1, E pojo2, String exemptedParameter )
    {
        if ( StringUtils.isValidObj( pojo1 ) && StringUtils.isValidObj( pojo2 ) )
        {
            PropertyDescriptor[] prDesc = org.apache.commons.beanutils.PropertyUtils.getPropertyDescriptors( pojo1 );
            for ( PropertyDescriptor descriptor : prDesc )
            {
                String propertyName = descriptor.getName();
                if ( StringUtils.equals( propertyName, exemptedParameter ) )
                {
                    continue;
                }
               // System.out.println();
                try
                {
                    Object value1 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo1, propertyName );
                    Object value2 = org.apache.commons.beanutils.PropertyUtils.getProperty( pojo2, propertyName );
                    if ( StringUtils.isValidObj( value1 ) && StringUtils.isValidObj( value2 )
                            && ! ( value1 instanceof List ) )
                    {
                        if ( !value1.equals( value2 ) )
                        {
                            LOGGER.info( "Value1:" + value1 + " & Value2:" + value2 + " are not equal for property:"
                                    + propertyName );
                            return -1;
                        }
                        else
                        {
                            LOGGER.info( "Value1:" + value1 + " & Value2:" + value2 + "are not list" );
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
        }
        return 0;
    }
    /* public static void main( String[] args )
     {
         ArrayList<CrmPartnerDetailsPojo> a = new ArrayList<CrmPartnerDetailsPojo>();
         ArrayList<CrmPartnerDetailsPojo> a1 = new ArrayList<CrmPartnerDetailsPojo>();
         PartnerPojo pojo1 = new PartnerPojo();
         pojo1.setPartnerAlias( "abc" );
         pojo1.setPartnerId( 2 );
         pojo1.getCrmPartnerDetailses().addAll( a );
         PartnerPojo pojo2 = new PartnerPojo();
         pojo2.setPartnerAlias( "abc" );
         pojo2.setPartnerId( 1 );
         pojo2.getCrmPartnerDetailses().addAll( a1 );
         PojoComparator<PartnerPojo> comparator = new PojoComparator<PartnerPojo>();
         System.out.println( comparator.compare( pojo1, pojo2 ) );
     }*/
}
