package com.np.tele.crm.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;

import com.np.tele.crm.constants.IDateConstants;

public class DateUtils
{
    private static final Logger   logger = Logger.getLogger( DateUtils.class );
    private static final Category LOGGER = null;

    public static String getCurrentDate( int dd1 )
    {
        GregorianCalendar curDate = new GregorianCalendar();
        curDate.setTime( new Date() );
        int monthCurr = curDate.get( 2 );
        int yearCurr = curDate.get( 1 );
        GregorianCalendar requestTime = new GregorianCalendar( yearCurr, monthCurr, dd1 );
        requestTime.add( 2, 0 );
        String d = "" + requestTime.get( 5 );
        String m = "" + ( requestTime.get( 2 ) + 1 );
        String y = "" + requestTime.get( 1 );
        String date = y + "-" + m + "-" + d;
        return date;
    }

    public static String getNextMonthDate( int dd1 )
    {
        GregorianCalendar curDate = new GregorianCalendar();
        curDate.setTime( new Date() );
        int monthCurr = curDate.get( 2 );
        int yearCurr = curDate.get( 1 );
        GregorianCalendar requestTime = new GregorianCalendar( yearCurr, monthCurr, dd1 );
        requestTime.add( 2, 1 );
        String d = "" + requestTime.get( 5 );
        String m = "" + ( requestTime.get( 2 ) + 1 );
        String y = "" + requestTime.get( 1 );
        String date = y + "-" + m + "-" + d;
        return date;
    }

    public static int getWeekOfYear( int secondsBefore )
    {
        Calendar cal = Calendar.getInstance();
        if ( secondsBefore > 0 )
        {
            cal.add( Calendar.SECOND, ( secondsBefore * -1 ) );
        }
        cal.setMinimalDaysInFirstWeek( 7 );
        cal.setFirstDayOfWeek( cal.get( Calendar.DAY_OF_WEEK ) );
        return cal.get( Calendar.WEEK_OF_YEAR );
    }

    public static String getNextBillingDate( int billingDate )
    {
        final SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yy" );
        final Calendar start = Calendar.getInstance();
        start.set( Calendar.DAY_OF_MONTH, billingDate );
        setDayStartTime( start );
        final Calendar currentDate = Calendar.getInstance();
        if ( currentDate.after( start ) )
        {
            start.add( Calendar.MONTH, 1 );
        }
        String monthStart = sdf.format( start.getTime() );
        return monthStart;
    }

    // Postpaid
    public static Date getNextBillingDate( String billingDate )
    {
        final Calendar start = Calendar.getInstance();
        start.set( Calendar.DAY_OF_MONTH, Integer.parseInt( billingDate ) );
        setDayStartTime( start );
        final Calendar currentDate = Calendar.getInstance();
        setDayStartTime( currentDate );
        if ( currentDate.after( start ) )
        {
            start.add( Calendar.MONTH, 1 );
        }
        return start.getTime();
    }

    // Prepaid & Immediate
    public static Date getNextBillingDate( Date inDate, int inPeriod, int dateType )
    {
        if ( Calendar.getInstance().getTime().after( inDate ) )
        {
            inDate = Calendar.getInstance().getTime();
        }
        final Calendar start = getModifiedCalendar( inDate, inPeriod, dateType );
        setDayStartTime( start );
        return start.getTime();
    }

    // Postpaid
    public static Date getPreviousBillingDate( String billingDate )
    {
        final Calendar start = Calendar.getInstance();
        start.set( Calendar.DAY_OF_MONTH, Integer.parseInt( billingDate ) );
        setDayStartTime( start );
        final Calendar currentDate = Calendar.getInstance();
        setDayStartTime( currentDate );
        if ( !currentDate.after( start ) )
        {
            start.add( Calendar.MONTH, -1 );
        }
        return start.getTime();
    }

    // Prepaid
    public static <E> Date getPreviousBillingDate( E inDate )
    {
        Date date = null;
        if ( inDate instanceof XMLGregorianCalendar )
        {
            XMLGregorianCalendar xmlDate = (XMLGregorianCalendar) inDate;
            date = xmlDate.toGregorianCalendar().getTime();
        }
        else if ( inDate instanceof Date )
        {
            date = (Date) inDate;
        }
        if ( StringUtils.isValidObj( date ) )
        {
            final Calendar start = getModifiedCalendar( date, -1, Calendar.MONTH );
            setDayStartTime( start );
            return start.getTime();
        }
        return null;
    }

    public static String getCurrentDateStr()
    {
        Date today = Calendar.getInstance().getTime();
        String currDate = IDateConstants.SDF_DD_MMM_YYYY.format( today );
        return currDate;
    }

    public static Date getCurrentDate()
    {
        Calendar today = Calendar.getInstance();
        setDayStartTime( today );
        return today.getTime();
    }

    public static String getCurrentDateInFormat( DateFormat inFormat )
    {
        Date today = Calendar.getInstance().getTime();
        String currDate = inFormat.format( today );
        return currDate;
    }

    public static <E> String getFormattedDate( E inDate, DateFormat inFormat )
    {
        Date date = null;
        if ( inDate instanceof XMLGregorianCalendar )
        {
            XMLGregorianCalendar xmlDate = (XMLGregorianCalendar) inDate;
            date = xmlDate.toGregorianCalendar().getTime();
        }
        else if ( inDate instanceof Date )
        {
            date = (Date) inDate;
        }
        if ( StringUtils.isValidObj( date ) )
        {
            String formattedDate = inFormat.format( date );
            return formattedDate;
        }
        return null;
    }

    public static String getFormattedDate( Date inDate, DateFormat inFormat )
    {
        String formattedDate = inFormat.format( inDate );
        return formattedDate;
    }

    public static long daysDiff( Date inStartDate, Date inEndDate )
    {
        long dayDiff = 0;
        long diff = 0;
        try
        {
            diff = inEndDate.getTime() - inStartDate.getTime();
            dayDiff = diff / ( 24 * 60 * 60 * 1000 );
        }
        catch ( Exception ex )
        {
            logger.warn( "Error in date diff" + ex );
        }
        return dayDiff;
    }

    public static long minuteDiff( Date inStartDate, Date inEndDate )
    {
        long dayDiff = 0;
        long diff = 0;
        try
        {
            diff = inEndDate.getTime() - inStartDate.getTime();
            dayDiff = diff / ( 60 * 1000 );
        }
        catch ( Exception ex )
        {
            logger.warn( "Error in date diff" + ex );
        }
        return dayDiff;
    }

    public static long hourDiff( Date inStartDate, Date inEndDate )
    {
        long dayDiff = 0;
        long diff = 0;
        try
        {
            diff = inEndDate.getTime() - inStartDate.getTime();
            dayDiff = diff / ( 60 * 60 * 1000 );
        }
        catch ( Exception ex )
        {
            logger.warn( "Error in date diff" + ex );
        }
        return dayDiff;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar( Calendar date )
    {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime( date.getTime() );
        XMLGregorianCalendar xmlCalendar = null;
        try
        {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar( gCalendar );
        }
        catch ( DatatypeConfigurationException ex )
        {
            logger.error( "Getting Exception while Convert Date to XML Date", ex );
        }
        return xmlCalendar;
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar( Date date )
    {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime( date );
        XMLGregorianCalendar xmlCalendar = null;
        try
        {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar( gCalendar );
        }
        catch ( DatatypeConfigurationException ex )
        {
            logger.error( "Getting Exception while Convert Date to XML Date", ex );
        }
        return xmlCalendar;
    }

    public static XMLGregorianCalendar changeDateFormat( String formDate )
    {
        XMLGregorianCalendar xmlCRFDate = null;
        if ( StringUtils.isNotBlank( formDate ) )
        {
            Calendar date = Calendar.getInstance();
            try
            {
                date.setTime( IDateConstants.SDF_DD_MMM_YYYY.parse( formDate ) );
                xmlCRFDate = DateUtils.toXMLGregorianCalendar( date );
            }
            catch ( ParseException ex )
            {
                logger.error( "Getting Exception while Parsing Display Dates", ex );
            }
        }
        return xmlCRFDate;
    }

    public static XMLGregorianCalendar changeDateFormatWithTime( String formDate )
    {
        XMLGregorianCalendar xmlCRFDate = null;
        if ( StringUtils.isNotBlank( formDate ) )
        {
            Calendar date = Calendar.getInstance();
            try
            {
                date.setTime( IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( formDate ) );
                xmlCRFDate = DateUtils.toXMLGregorianCalendar( date );
            }
            catch ( ParseException ex )
            {
                logger.error( "Getting Exception while Parsing Display Dates and we are okay with it if null value is handled. "
                        + ex.getMessage() );
            }
        }
        return xmlCRFDate;
    }

    public static String convertXGCtoCal( String calendar )
    {
        String formattedDate = null;
        if ( StringUtils.isBlank( calendar ) )
        {
            return null;
        }
        else
        {
            try
            {
                final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd" );
                Calendar cal = Calendar.getInstance();
                cal.setTime( dateFormat.parse( calendar ) );
                formattedDate = IDateConstants.SDF_DD_MMM_YYYY.format( cal.getTime() );
            }
            catch ( Exception ex )
            {
                logger.error( "Exception", ex );
            }
        }
        return formattedDate;
    }

    //convert "2014-09-04T13:44:44+05:30" to Normal dateTime
    public static String convertXMLXGCtoCal( String calendar )
    {
        String formattedDate = null;
        if ( StringUtils.isBlank( calendar ) )
        {
            return null;
        }
        else
        {
            try
            {
                final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" );
                Calendar cal = Calendar.getInstance();
                cal.setTime( dateFormat.parse( calendar ) );
                formattedDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( cal.getTime() );
            }
            catch ( Exception ex )
            {
                logger.error( "Exception", ex );
            }
        }
        return formattedDate;
    }

    public static String convertXmlCalToString( XMLGregorianCalendar xmlDate )
    {
        GregorianCalendar gc = xmlDate.toGregorianCalendar();
        String formatted_string = IDateConstants.SDF_DD_MMM_YYYY.format( gc.getTime() );
        return formatted_string;
    }

    /**
     * Get specified format XMLGregorianCalendar as String
     * @param xmlDate
     * @param inDateFormat
     * @return
     */
    public static String convertXmlCalToString( XMLGregorianCalendar xmlDate, DateFormat inDateFormat )
    {
        GregorianCalendar gc = xmlDate.toGregorianCalendar();
        String formatted_string = inDateFormat.format( gc.getTime() );
        return formatted_string;
    }

    public static Date convertXmlCalToDate( XMLGregorianCalendar xmlDate )
    {
        if ( null != xmlDate )
        {
            GregorianCalendar gc = xmlDate.toGregorianCalendar();
            return gc.getTime();
        }
        return null;
    }

    public static Calendar convertXmlCalToCalendar( XMLGregorianCalendar xmlDate )
    {
        GregorianCalendar gc = xmlDate.toGregorianCalendar();
        return gc;
    }

    public static String getYesterdayDateString()
    {
        Calendar cal = Calendar.getInstance();
        cal.add( Calendar.DATE, -1 );
        return IDateConstants.SDF_DD_MMM_YYYY.format( cal.getTime() );
    }

    public static Calendar getFutureEndDate( int inPeriod, int dateType )
    {
        Calendar cal = Calendar.getInstance();
        setDayEndTime( cal );
        cal.add( dateType, inPeriod );
        return cal;
    }

    public static Calendar getModifiedCalendar( Date inDate, int inPeriod, int dateType )
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime( inDate );
        cal.add( dateType, inPeriod );
        return cal;
    }

    public static Date getModifiedDate( Date inDate, int inPeriod, int dateType )
    {
        return getModifiedCalendar( inDate, inPeriod, dateType ).getTime();
    }

    public static Calendar getDate( String inDate, String inFormat )
    {
        Calendar cal = Calendar.getInstance();
        if ( StringUtils.isBlank( inDate ) )
        {
            return null;
        }
        else
        {
            try
            {
                final SimpleDateFormat dateFormat = new SimpleDateFormat( inFormat );
                cal.setTime( dateFormat.parse( inDate ) );
            }
            catch ( Exception ex )
            {
                logger.error( "Exception", ex );
            }
        }
        return cal;
    }

    public static String getFormattedDate( String inDate, String inSrcFormat, String inDestFormat )
    {
        Calendar cal = getDate( inDate, inSrcFormat );
        if ( StringUtils.isValidObj( cal ) )
        {
            final SimpleDateFormat dateFormat = new SimpleDateFormat( inDestFormat );
            return dateFormat.format( cal.getTime() );
        }
        return null;
    }

    public static void setDayStartTime( Calendar inCal )
    {
        inCal.set( Calendar.HOUR_OF_DAY, 0 );
        inCal.set( Calendar.MINUTE, 0 );
        inCal.set( Calendar.SECOND, 0 );
        inCal.set( Calendar.MILLISECOND, 0 );
    }

    public static void setDayEndTime( Calendar inCal )
    {
        inCal.set( Calendar.HOUR_OF_DAY, 23 );
        inCal.set( Calendar.MINUTE, 59 );
        inCal.set( Calendar.SECOND, 59 );
        inCal.set( Calendar.MILLISECOND, 999 );
    }

    public static void setCurrentTime( Calendar inCal )
    {
        Calendar cal = Calendar.getInstance();
        inCal.set( Calendar.HOUR_OF_DAY, cal.get( Calendar.HOUR_OF_DAY ) );
        inCal.set( Calendar.MINUTE, cal.get( Calendar.MINUTE ) );
        inCal.set( Calendar.SECOND, cal.get( Calendar.SECOND ) );
        inCal.set( Calendar.MILLISECOND, cal.get( Calendar.MILLISECOND ) );
    }

    public static String getCurrDateEndTime()
    {
        Calendar calendar = Calendar.getInstance();
        setDayEndTime( calendar );
        return IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( calendar.getTime() );
    }

    public static String getOneMonthBackDateStartTime()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MONTH, -1 );
        setDayStartTime( calendar );
        return IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( calendar.getTime() );
    }

    public static XMLGregorianCalendar getBackTimeByHour( byte inHours )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.HOUR, -inHours );
        return toXMLGregorianCalendar( calendar.getTime() );
    }

    public static XMLGregorianCalendar getBackDateByMonth( byte inMonths )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add( Calendar.MONTH, -inMonths );
        return toXMLGregorianCalendar( calendar.getTime() );
    }

    //    public static Date stringDatetoDateDate(String inDate){
    //        DateFormat df = new SimpleDateFormat( "dd-MMM-yyyy" );
    //        Date date = null;
    //        try
    //        {
    //            date = df.parse( inDate );
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "error while resolving inDate", ex );
    //        }
    //        return date;
    //
    //    }
    public static void main( String[] args )
    {
        /*Calendar cal1 = DateUtils.getDate( "2015-07-02", "yyyy-MM-dd" );
        DateUtils.setCurrentTime( cal1 );
        Date pDate = cal1.getTime();
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        System.out.println( "Current date :: " + date + " and processing date is :: " + pDate );
        long minDiff = DateUtils.minuteDiff( pDate, date );
        System.out.println( "diff is :: " + minDiff );
        if ( minDiff <= 5 )
        {
            try
            {
                System.out.println( "Going to Sleep" );
                Thread.sleep( ( 5 - minDiff ) * 1000 * 60 );
            }
            catch ( InterruptedException ex )
            {
                LOGGER.info( "Error while sleep" + ex );
            }
        }*/
        Calendar cal = getDate( "27-May-2016", IDateConstants.FORMAT_DD_MMM_YYYY );
        setDayEndTime( cal );
        final SimpleDateFormat dateFormat = new SimpleDateFormat( IDateConstants.FORMAT_DD_MMM_YYYY_HH_MM_SS );
        String format = dateFormat.format( cal.getTime() );
        // String s = getFormattedDate( cal, IDateConstants.SDF_DD_MM_YYYY_HH_MM_SS  );
        System.out.println( format );
        System.out.println( getDate( "27-May-2016", IDateConstants.FORMAT_DD_MMM_YYYY ).getTime() );
    }

    public static Date getNextExpiryDate( Date expiryDate, int noOfdays, int dateType )
    {
        final Calendar start = getModifiedCalendar( expiryDate, noOfdays, dateType );
        setDayEndTime( start );
        return start.getTime();
    }

    public static Calendar getCalendarFromDate( Date inDate )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( inDate );
        return calendar;
    }

    public static String getEndTimeToDate( String toDate, String dateFormat )
    {
        Calendar cal = getDate( toDate, dateFormat );
        setDayEndTime( cal );
        final SimpleDateFormat simpleFormat = new SimpleDateFormat( IDateConstants.FORMAT_DD_MMM_YYYY_HH_MM_SS );
        String format = simpleFormat.format( cal.getTime() );
        return format;
    }
}
