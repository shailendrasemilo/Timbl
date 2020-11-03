package com.np.tele.crm.utils;

import java.math.BigDecimal;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

public class StringUtils
    extends org.apache.commons.lang3.StringUtils
{
    private static final String  NORMALIZE         = "[^a-zA-Z 0-9]+";
    private static final Logger  logger            = Logger.getLogger( StringUtils.class );
    private static final String  IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                                                           + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                                                           + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                                                           + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
    private static final Pattern pattern           = Pattern.compile( IPADDRESS_PATTERN );
    public static final String   NEW_LINE          = System.getProperty( "line.separator" );

    public static boolean isValidObj( final Object inObj )
    {
        if ( ( null == inObj ) )
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public static boolean isEmpty( final String str )
    {
        if ( ( str == null ) || ( str.trim().length() == 0 ) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static String[] tokenizeString( final String str, final char delimiter )
    {
        final List<String> tokens = new ArrayList<String>();
        if ( StringUtils.isEmpty( str ) )
        {
            return null;
        }
        else
        {
            final StringCharacterIterator strCharIter = new StringCharacterIterator( str );
            char character = strCharIter.first();
            StringBuffer stringBuffer = new StringBuffer();
            while ( character != CharacterIterator.DONE )
            {
                if ( delimiter == character )
                {
                    tokens.add( stringBuffer.toString() );
                    stringBuffer = new StringBuffer();
                }
                else
                {
                    stringBuffer.append( character );
                }
                character = strCharIter.next();
            }
            tokens.add( stringBuffer.toString() );
        }
        return (String[]) ( tokens.toArray( new String[tokens.size()] ) );
    }

    public static String safeSubString( final String str, final int begin, final int end )
    {
        String result = "";
        if ( !isEmpty( str ) && begin >= 0 && end >= 0 && begin < end )
        {
            if ( end <= str.length() )
            {
                result = str.substring( begin, end );
            }
            else
            {
                result = str;
            }
        }
        return result;
    }

    public static String normalize( String text )
    {
        if ( !isEmpty( text ) )
        {
            text = text.replaceAll( NORMALIZE, "" );
            text = text.replaceAll( " ", "" );
            text = StringUtils.lowerCase( text );
            return text;
        }
        else
            return text;
    }

    public static boolean isValidEmailID( final String emailAddress )
    {
        boolean isValid = false;
        try
        {
            if ( !isEmpty( emailAddress ) )
            {
                if ( emailAddress.contains( "," ) )
                {
                    String ar[] = emailAddress.split( "," );
                    for ( String emailId : ar )
                    {
                        final InternetAddress[] address = InternetAddress.parse( emailId );
                        if ( address != null )
                        {
                            if ( emailId.indexOf( '@' ) > -1 && emailId.indexOf( '.' ) > -1 )
                            {
                                isValid = true;
                            }
                            else
                            {
                                isValid = false;
                                break;
                            }
                        }
                    }
                }
                else
                {
                    final InternetAddress[] address = InternetAddress.parse( emailAddress );
                    if ( address != null )
                    {
                        if ( emailAddress.indexOf( '@' ) > -1 && emailAddress.indexOf( '.' ) > -1 )
                        {
                            isValid = true;
                        }
                    }
                }
            }
        }
        catch ( AddressException addrEx )
        {
            logger.warn( "AddressException in parsing email address : " + emailAddress + " Exception is : " + addrEx );
        }
        catch ( Exception ex )
        {
            logger.warn( "Exception in parsing email address : " + emailAddress + " Exception is : " + ex );
        }
        return isValid;
    }

    public static String getMaskedMobileNo( String inMobileNo )
    {
        StringBuilder mobileNo = new StringBuilder( inMobileNo );
        Integer[] index =
        { 2, 3, 5, 6, 7 };
        for ( int i = 0; i < index.length; i++ )
        {
            mobileNo.setCharAt( index[i], 'X' );
        }
        return mobileNo.toString();
    }

    public static String getMaskedEmailID( String inEmailID )
    {
        StringBuilder email = new StringBuilder( inEmailID );
        int j = email.indexOf( "@" );
        for ( int i = 1; i < j; i = i + 2 )
        {
            email.setCharAt( i, 'X' );
        }
        return email.toString();
    }

    public static boolean isValidInet4Address( String inet4Address )
    {
        Matcher matcher = pattern.matcher( inet4Address );
        if ( !matcher.matches() )
        {
            logger.info( "Invalid IP Format for " + inet4Address );
            return false;
        }
        return true;
    }

    public static String getStackTraceAsString( Throwable t )
    {
        StringBuffer sb = new StringBuffer();
        if ( t != null )
        {
            sb.append( "Exception is : " ).append( t.getMessage() ).append( NEW_LINE );
            StackTraceElement[] stackTrace = t.getStackTrace();
            if ( stackTrace != null && stackTrace.length > 0 )
            {
                sb.append( "Stack Trace is :" ).append( NEW_LINE );
                for ( StackTraceElement elem : stackTrace )
                {
                    sb.append( elem.toString() ).append( NEW_LINE );
                }
            }
        }
        return sb.toString();
    }

    public static boolean isValidLength( final String inStr, final int inMin, final int inMax )
    {
        final int inStrLength = inStr.length();
        if ( inStrLength < inMin || inStrLength > inMax )
            return false;
        else
            return true;
    }

    public static boolean isValidPinCode( final String inStr )
    {
        final String str = inStr.trim();
        final String rule = "[0-9]{6}$";
        final Pattern pattern = Pattern.compile( rule );
        final Matcher matcher = pattern.matcher( str );
        if ( matcher.matches() )
            return true;
        else
            return false;
    }

    public static boolean compareRegularExp( String inRegex, String str )
    {
        return Pattern.matches( inRegex, str );
    }

    public static String arrayToStringWithComma( String str[] )
    {
        String processedString = "";
        processedString = Arrays.toString( str ).replace( "[", "" ).replace( "]", "" ).replace( " ", "" );
        processedString = "," + processedString + ",";
        return processedString;
    }

    public static String[] commaStringToArray( String str )
    {
        str = StringUtils.removeStart( str, "," );
        str = StringUtils.removeEnd( str, "," );
        str = str.replace( " ", "" );
        //System.out.println( "string is:" + str );
        String str2[] = str.split( "," );
        return str2;
    }

    public static String removeCommaStartEnd( String str )
    {
        if ( StringUtils.isNotBlank( str ) )
        {
            str = StringUtils.removeStart( str, "," );
            str = StringUtils.removeEnd( str, "," );
            str.replace( " ", "" );
        }
        return str;
    }

    public static void main( String args[] )
    {
        String s = "";
        String k = "";
        String m = null;
        String str[] =
        { s, k, m };
        // String str1 = "nawab1 ,nawab2,nawab3,";
        System.out.println( StringUtils.decimalValue( "-1.000" ).doubleValue() == new BigDecimal( -1 ).doubleValue() );
        //        /*for ( String str3 : commaStringToArray( str1 ) )
        //        {
        //            System.out.print( str3 );
        //        }
        //        System.out.println( "yoyo" );*/
        //        // System.out.println( isValidInet4Address( "399.9.9.9" ) );
        //        int[] A =
        //        { 45, 43, 56, 76, 8907542, 4984646, 4984, 6256  };
        //        // write your code in Java SE 8
        //        if ( null == A || A.length < 5 )
        //        {
        //            return;
        //        }
        //        int aLength = A.length;
        //        List<Integer> listInteger = new ArrayList<Integer>();
        //        for ( int i = 0; i < aLength; i++ )
        //        {
        //            listInteger.add( A[i] );
        //        }
        //        Arrays.sort( A );
        //        int min = A[0];
        //        int secondMin = A[1];
        //        int minIndex = listInteger.indexOf( min );
        //        int secondIndex = listInteger.indexOf( secondMin );
        //        int diff = minIndex - secondIndex;
        //        int count = 1;
        //        while ( diff <= 1 && diff >= -1 )
        //        {
        //            if ( minIndex != listInteger.lastIndexOf( min ) )
        //            {
        //                minIndex = listInteger.lastIndexOf( min );
        //            }
        //            else if ( secondIndex != listInteger.lastIndexOf( secondMin ) )
        //            {
        //                secondIndex = listInteger.lastIndexOf( secondMin );
        //            }
        //            else if ( count < aLength - 1 )
        //            {
        //                count++;
        //                secondMin = A[count];
        //            }
        //            diff = minIndex - secondIndex;
        //        }
        //        System.out.println( Integer.toBinaryString( 38 ) );
        //        System.out.println( Integer.toBinaryString( 39 ) );
        //        System.out.println( Integer.toBinaryString( 40 ) );
        //        int N = 10731824;
        //        int sparse = N, count = N;
        //        String sparseStr = null;
        //        while ( sparse == count )
        //        {
        //            N++;
        //            sparseStr = Integer.toBinaryString( N );
        //            System.out.println( sparseStr );
        //            if ( !sparseStr.contains( "11" ) )
        //            {
        //                sparse = N;
        //            }
        //        }
        //        System.out.println( sparse );
        // write your code in Java SE 8
        //        if(null == A || null == B || A.length ==0 || B.length ==0 || (A.length != B.length)){
        //            return -1;
        //        }
        //        int arrLength = A.length;
        //        if(arrLength == 1){
        //            return 1;
        //        }        
        //        int[] arr =null;
        //        for(int j=0; j<arrLength-1; j++){
        //            for(int i=j+1;i<arrLength;i++){
        //               if((A[j]>=A[i] && A[j]<=B[i]) || (B[j]>=A[i] && B[j]<=B[i])){
        //                   
        //               }
        //            }
        //        }          
        //        
        //        return 0;
        //        String sDate = "2014-12-19 16:11:44";
        //        try
        //        {
        //            Calendar cal = Calendar.getInstance();
        //            cal.setTime( IDateConstants.SDF_YYYY_DD_MM_HH_MM_SS.parse( sDate ) );
        //            //System.out.println( cal.getTimeInMillis() );
        //            System.out.println( cal );
        //            long timeL = 1418985704859l;
        //            cal.setTimeInMillis( timeL );
        //            System.out.println( cal );
        //        }
        //        catch ( ParseException ex )
        //        {
        //            ex.printStackTrace();
        //        }
        //        System.out.println( Long.MIN_VALUE );
        //        System.out.println( Calendar.getInstance().getTimeInMillis() );
        //        System.out.println( Long.MAX_VALUE );
        //        String str = "1234567 56";
        //        String regex = "[a-zA-Z0-9\\s]+";
        System.out.println( replaceLast( "7,8,9,6", ",", " or " ) );
    }

    public static String removeET( String inStr )
    {
        if ( isNotBlank( inStr ) )
        {
            char[] charArray = inStr.toCharArray();
            for ( int i = 0; i < charArray.length; i++ )
            {
                if ( Character.isSpaceChar( charArray[i] ) & !Character.isWhitespace( charArray[i] ) )
                {
                    charArray[i] = ' ';
                }
            }
            return new String( charArray );
        }
        return inStr;
    }

    public static String changeStringToDouble( String inStr )
    {
        double decimalStr = 0.0;
        if ( !StringUtils.isEmpty( inStr ) )
        {
            decimalStr = Double.parseDouble( inStr );
        }
        return String.valueOf( decimalStr );
    }

    //condition:- True means OR, False Means AND
    /**
     * 
     * @param obj String[]
     * @param condition boolean. <b>true</b> for <i>OR</i> operation, <b>false</b> for <i>AND</i> operation 
     * @return boolean
     */
    public static boolean checkAllvalidObj( String obj[], boolean condition )
    {
        boolean flag = false;
        if ( condition )
        {
            for ( String str : obj )
            {
                if ( StringUtils.isNotBlank( str ) )
                {
                    flag = true;
                    break;
                }
            }
        }
        else
        {
            for ( String str : obj )
            {
                if ( StringUtils.isNotBlank( str ) )
                {
                    flag = true;
                    continue;
                }
                else
                {
                    flag = false;
                    break;
                }
            }
        }
        return flag;
    }

    public static Long numericValue( String inString )
    {
        if ( isNumeric( inString ) )
        {
            return Long.parseLong( inString );
        }
        return 0L;
    }

    public static Double decimalValue( String val )
    {
        Double bg = new Double( 0 );
        try
        {
            bg = new Double( val );
        }
        catch ( Exception ex )
        {
            logger.error( ex.getMessage(), ex );
        }
        return bg;
    }

    public static String insertCharacterAtIndex( String inMacAddress, int inIndex, String inCharacter )
    {
        if ( isNotBlank( inMacAddress ) )
        {
            int length = inMacAddress.length();
            int loopIndex = length / inIndex;
            String[] arr = new String[loopIndex];
            int j = 0;
            int k = 0;
            for ( int i = 0; i < loopIndex; i++ )
            {
                j = i * inIndex;
                k = j + inIndex;
                arr[i] = substring( inMacAddress, j, k );
            }
            StringBuilder sb = new StringBuilder();
            for ( int i = 0; i < arr.length; i++ )
            {
                sb.append( arr[i] ).append( "." );
            }
            inMacAddress = sb.substring( 0, sb.length() - 1 );
        }
        return inMacAddress;
    }

    public static String replaceLast( String text, String searchString, String replacement )
    {
        if ( StringUtils.isNotBlank( text ) )
        {
            int lastIndex = text.lastIndexOf( searchString );
            text = text.substring( 0, lastIndex ) + replacement + text.substring( lastIndex + 1 );
        }
        return text;
    }
}
