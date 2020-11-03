package com.np.tele.crm.utils;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.Comparator;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

public class SortingComparator<E>
    implements Comparator<E>
{
    private static final Logger LOGGER       = Logger.getLogger( SortingComparator.class );
    private String              propertyName = null;

    public SortingComparator( String inPropertyName )
    {
        this.propertyName = inPropertyName;
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
                    if ( value1 instanceof String && value2 instanceof String )
                    {
                        String str1 = (String) value1;
                        String str2 = (String) value2;
                        if ( StringUtils.equals( propertyName, "masterName" ) )
                        {
                            return extractInt( str1 ) - extractInt( str2 );
                        }
                      /*  if ( StringUtils.equals( propertyName, "callEndDate" ) )
                        {
                            SimpleDateFormat sdf = new SimpleDateFormat( "dd MMM yyyy" );
                            try
                            {
                                return sdf.parse( str1 ).compareTo( sdf.parse( str2 ) );
                            }
                            catch ( ParseException ex )
                            {
                                LOGGER.error( "Exception while parsing string date :", ex );
                            }
                        }*/
                        return str1.compareToIgnoreCase( str2 );
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
                    else if ( value1 instanceof Double && value2 instanceof Double )
                    {
                        Double str1 = (Double) value1;
                        Double str2 = (Double) value2;
                        return str1.compareTo( str2 );
                    }
                    else if ( value1 instanceof BigDecimal && value2 instanceof BigDecimal )
                    {
                        BigDecimal str1 = (BigDecimal) value1;
                        BigDecimal str2 = (BigDecimal) value2;
                        return str1.compareTo( str2 );
                    }
                    else if ( value1 instanceof XMLGregorianCalendar && value2 instanceof XMLGregorianCalendar )
                    {
                        XMLGregorianCalendar str1 = (XMLGregorianCalendar) value1;
                        XMLGregorianCalendar str2 = (XMLGregorianCalendar) value2;
                        return str1.compare( str2 );
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

    int extractInt( String s )
    {
        String num = s.replaceAll( "\\D", "" );
        return num.isEmpty() ? 0 : Integer.parseInt( num );
    }
    /* public static void main( String[] args )
         throws IOException
     {
         List<String> strings = Arrays.asList( "a1", "abc", "a2", "a100", "a10" );
         Collections.sort( strings, new Comparator<String>()
         {
             public int compare( String o1, String o2 )
             {
                 return extractInt( o1 ) - extractInt( o2 );
             }

             int extractInt( String s )
             {
                 String num = s.replaceAll( "\\D", "" );
                 return num.isEmpty() ? 0 : Integer.parseInt( num );
             }
         } );
         System.out.println( strings );
     }*/
    //    public static void main( String[] args )
    //    {
    //        StatePojo sp = null;
    //        List<StatePojo> listStates = new ArrayList<StatePojo>();
    //        for ( int i = 10; i > 0; i-- )
    //        {
    //            sp = new StatePojo();
    //            sp.setStateName( "State" + i );
    //            listStates.add( sp );
    //        }
    //        Collections.sort( listStates, new SortingComparator( "stateName" ) );
    //        for ( int i = 0; i < 10; i++ )
    //        {
    //            System.out.println( listStates.get( i ).getStateName() );
    //        }
    //    }
}
