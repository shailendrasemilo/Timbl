package com.np.tele.crm.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.validator.util.CommonValidator;

public class SLACalculationUtil
{
    private final Logger LOGGER                         = Logger.getLogger( SLACalculationUtil.class );
    private final String TIME_DIFFERENCE_FROM_DAY_END   = "timeDifferenceFromDayEnd";
    private final String TIME_DIFFERENCE_FROM_DAY_START = "timeDifferenceFromDayStart";
    private List<String> publicHolidayList              = null;

    public SLACalculationUtil( List<String> inPublicHolidayList )
    {
        publicHolidayList = inPublicHolidayList;
    }

    public Calendar calculateExpectedSLA( Calendar inStartTime, String inSLAUnit, long inSLATime )
    {
        LOGGER.info( "Inside SLAUtil, +calculateExpectedSLA" );
        long calculatedSLA = 0;
        Calendar calendar = null;
        try
        {
            if ( inSLATime > 0 )
            {
                if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.WORKING_HOURS.getCode() ) )
                {
                    calculatedSLA = calculateFutureHoursForWorkingHours( inSLATime, inStartTime );
                }
                else if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.WORKING_DAYS.getCode() ) )
                {
                    calculatedSLA = calculateFutureHoursForDays( inSLATime, inStartTime, true );
                }
                else if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.CALENDAR_DAYS.getCode() ) )
                {
                    calculatedSLA = calculateFutureHoursForDays( inSLATime, inStartTime, false );
                }
                calendar = Calendar.getInstance();
                calendar.setTimeInMillis( calculatedSLA );
                LOGGER.info( "Expected calculated SLA " + calendar.getTime() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while calculating expected SLA time", ex );
        }
        return calendar;
    }

    public Calendar forFollowUpEnd( Calendar inOldFollowUp,
                                    Calendar inNewFollowUp,
                                    Calendar inExpectedSLA,
                                    String inSLAUnit )
    {
        LOGGER.info( "Inside SLAUtil, +forFollowUpEnd" );
        Calendar calendar = null;
        try
        {
            long diff = actualSLADifference( inOldFollowUp, inExpectedSLA, inSLAUnit );
            calendar = calculateExpectedSLA( inNewFollowUp, inSLAUnit, diff );
            LOGGER.info( "Expected calculated SLA " + calendar.getTime() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while handling follow up stop", ex );
        }
        return calendar;
    }

    public Calendar forFollowUpCreated( Calendar inCreated,
                                        Calendar inFollowUp,
                                        Calendar inExpectedSLA,
                                        String inSLAUnit )
    {
        LOGGER.info( "Inside SLAUtil, +forFollowUpCreated" );
        Calendar calendar = null;
        try
        {
            long diff = actualSLADifference( inCreated, inExpectedSLA, inSLAUnit );
            calendar = calculateExpectedSLA( inFollowUp, inSLAUnit, diff );
            //LOGGER.info( "Expected calculated SLA " + calendar.getTime() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while handling follow up creation", ex );
        }
        return calendar;
    }

    public long actualSLADifference( Calendar inStartTime, Calendar inEndTime, String inSLAUnit )
    {
        LOGGER.info( "Inside SLAUtil, +nonSLATimeSpan" );
        long diff = 0;
        try
        {
            if ( StringUtils.isValidObj( inStartTime ) && StringUtils.isValidObj( inEndTime )
                    && StringUtils.isNotBlank( inSLAUnit ) )
            {
                LOGGER.info( "StartTime : " + inStartTime + " EndTime : " + inEndTime + " SLAUnit : " + inSLAUnit );
                diff = inEndTime.getTimeInMillis() - inStartTime.getTimeInMillis();
                LOGGER.info( "End to start time difference : " + diff );
                if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.WORKING_HOURS.getCode() ) )
                {
                    diff = getHoursForWorkingHours( inStartTime, inEndTime );
                }
                else if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.WORKING_DAYS.getCode() ) )
                {
                    diff = getHoursForDays( inStartTime, inEndTime, true );
                }
                else if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.CALENDAR_DAYS.getCode() ) )
                {
                    diff = getHoursForDays( inStartTime, inEndTime, false );
                }
                LOGGER.info( "Calculated time difference : " + diff );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while calculating Non SLA time span", ex );
        }
        return diff;
    }

    //    public long nonSLATimeSpan( Calendar inStartTime, Calendar inEndTime, String inSLAUnit )
    //    {
    //        LOGGER.info( "Inside SLAUtil, +nonSLATimeSpan" );
    //        long diff = 0;
    //        try
    //        {
    //            if ( StringUtils.isValidObj( inStartTime ) && StringUtils.isValidObj( inEndTime )
    //                    && StringUtils.isNotBlank( inSLAUnit ) )
    //            {
    //                LOGGER.info( "StartTime : " + inStartTime + " EndTime : " + inEndTime + " SLAUnit : " + inSLAUnit );
    //                diff = inEndTime.getTimeInMillis() - inStartTime.getTimeInMillis();
    //                LOGGER.info( "End to start time difference : " + diff );
    //                if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.WORKING_HOURS.getCode() ) )
    //                {
    //                    diff -= getHoursForWorkingHours( inStartTime, inEndTime );
    //                }
    //                else if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.WORKING_DAYS.getCode() ) )
    //                {
    //                    diff -= getHoursForDays( inStartTime, inEndTime, true );
    //                }
    //                else if ( StringUtils.equals( inSLAUnit, CRMDisplayListConstants.CALENDAR_DAYS.getCode() ) )
    //                {
    //                    diff -= getHoursForDays( inStartTime, inEndTime, false );
    //                }
    //                LOGGER.info( "Calculated time difference : " + diff );
    //            }
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Exception while calculating Non SLA time span", ex );
    //        }
    //        return diff;
    //    }
    public long getHoursForWorkingHours( Calendar inCreationTime, Calendar inReportingTime )
    {
        LOGGER.info( "Inside SLAUtil, +getHoursForWorkingHours" );
        long diff = 0;
        inCreationTime = setWorkingHoursStartTime( inCreationTime );
        inReportingTime = setWorkingHoursEndTime( inReportingTime );
        //LOGGER.info( "inReportingTime : " + inReportingTime.getTime() );
        //LOGGER.info( "inCreationTime : " + inCreationTime.getTime() );
        long diffInMillis = inReportingTime.getTimeInMillis() - inCreationTime.getTimeInMillis();
        int daysDifference = (int) ( diffInMillis / ( 1000 * 60 * 60 * 24 ) );
        if ( daysDifference == 0 )
        {
            daysDifference = inReportingTime.get( Calendar.DAY_OF_YEAR ) - inCreationTime.get( Calendar.DAY_OF_YEAR );
        }
        if ( daysDifference == 0
                && ( !isHoliday( inReportingTime ) && inReportingTime.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY ) )
        {
            diff = inReportingTime.getTime().getTime() - inCreationTime.getTime().getTime();
        }
        else if ( daysDifference > 0 )
        {
            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.set( inCreationTime.get( Calendar.YEAR ), inCreationTime.get( Calendar.MONTH ),
                              inCreationTime.get( Calendar.DAY_OF_MONTH ), inCreationTime.get( Calendar.HOUR_OF_DAY ),
                              inCreationTime.get( Calendar.MINUTE ), inCreationTime.get( Calendar.SECOND ) );
            Calendar endDay = increaseEndDayByOne( inReportingTime );
            while ( tempCalendar.before( endDay ) )
            {
                if ( !isHoliday( tempCalendar ) && tempCalendar.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
                {
                    if ( compareDateEquality( tempCalendar, inCreationTime ) )
                    {
                        diff = diff + calculateTimeDiffWorkingHours( inCreationTime, TIME_DIFFERENCE_FROM_DAY_END );
                    }
                    else if ( compareDateEquality( tempCalendar, inReportingTime ) )
                    {
                        diff = diff + calculateTimeDiffWorkingHours( inReportingTime, TIME_DIFFERENCE_FROM_DAY_START );
                    }
                    else
                    {
                        diff = diff + calculateTimeDiffWorkingHours( null, null );
                    }
                }
                tempCalendar.add( Calendar.DAY_OF_YEAR, 1 );
            }
        }
        LOGGER.info( "Difference " + diff );
        return diff;
    }

    public long getHoursForDays( Calendar inCreationTime, Calendar inReportingTime, boolean isWorkingDayCalculation )
    {
        LOGGER.info( "Inside SLAUtil, +getHoursForDays" );
        long diff = 0;
        long diffInMillis = inReportingTime.getTimeInMillis() - inCreationTime.getTimeInMillis();
        int daysDifference = (int) ( diffInMillis / ( 1000 * 60 * 60 * 24 ) );
        if ( daysDifference == 0 )
        {
            daysDifference = inReportingTime.get( Calendar.DAY_OF_YEAR ) - inCreationTime.get( Calendar.DAY_OF_YEAR );
        }
        if ( daysDifference == 0
                && ( !isHoliday( inReportingTime ) && ( isWorkingDayCalculation ? ( inReportingTime
                        .get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY ) : true ) ) )
        {
            diff = inReportingTime.getTime().getTime() - inCreationTime.getTime().getTime();
        }
        else if ( daysDifference > 0 )
        {
            Calendar tempCalendar = Calendar.getInstance();
            tempCalendar.set( inCreationTime.get( Calendar.YEAR ), inCreationTime.get( Calendar.MONTH ),
                              inCreationTime.get( Calendar.DAY_OF_MONTH ), inCreationTime.get( Calendar.HOUR_OF_DAY ),
                              inCreationTime.get( Calendar.MINUTE ), inCreationTime.get( Calendar.SECOND ) );
            Calendar endDay = increaseEndDayByOne( inReportingTime );
            while ( tempCalendar.before( endDay ) )
            {
                if ( !isHoliday( tempCalendar )
                        && ( isWorkingDayCalculation ? ( tempCalendar.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
                                                    : true ) )
                {
                    if ( compareDateEquality( tempCalendar, inCreationTime ) )
                    {
                        diff = diff + calculateTimeDiff24Hours( inCreationTime, TIME_DIFFERENCE_FROM_DAY_END );
                    }
                    else if ( compareDateEquality( tempCalendar, inReportingTime ) )
                    {
                        diff = diff + calculateTimeDiff24Hours( inReportingTime, TIME_DIFFERENCE_FROM_DAY_START );
                    }
                    else
                    {
                        diff = diff + calculateTimeDiff24Hours( null, null );
                    }
                }
                tempCalendar.add( Calendar.DAY_OF_YEAR, 1 );
            }
        }
        LOGGER.info( "Difference " + diff );
        return diff;
    }

    private Calendar increaseEndDayByOne( Calendar inReportingTime )
    {
        Calendar calendar = Calendar.getInstance();
        calendar.set( inReportingTime.get( Calendar.YEAR ), inReportingTime.get( Calendar.MONTH ),
                      inReportingTime.get( Calendar.DAY_OF_MONTH ), 0, 0, 0 );
        calendar.add( Calendar.DAY_OF_YEAR, 1 );
        return calendar;
    }

    private boolean compareDateEquality( Calendar inTempCalendar, Calendar inCreationTime )
    {
        String inTempDateStr = DateUtils.convertXmlCalToString( DateUtils.toXMLGregorianCalendar( inTempCalendar ),
                                                                IDateConstants.SDF_DD_MM_YYYY );
        String inCreateDateStr = DateUtils.convertXmlCalToString( DateUtils.toXMLGregorianCalendar( inCreationTime ),
                                                                  IDateConstants.SDF_DD_MM_YYYY );
        return StringUtils.equals( inTempDateStr, inCreateDateStr );
    }

    private boolean isHoliday( Calendar inDateTime )
    {
        boolean isHoliday = false;
        try
        {
            if ( CommonValidator.isValidCollection( publicHolidayList ) )
            {
                String inDate = DateUtils.getFormattedDate( inDateTime.getTime(), IDateConstants.SDF_DD_MMM_YYYY );
                isHoliday = publicHolidayList.contains( inDate );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking for holiday date", ex );
        }
        return isHoliday;
    }

    /*private boolean isHoliday( Calendar inDateTime )
    {
        CrmHolidayDetails crmHolidayDetails = null;
        boolean isHoliday = true;
        try
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( inDateTime.getTime() );
            DateUtils.setDayStartTime( calendar );
            crmHolidayDetails = new CrmHolidayDetails();
            crmHolidayDetails.setHolidayDate( DateUtils.toXMLGregorianCalendar( calendar ) );
            int pojoIndex = -1;
            if ( SLACommonUtils.getHolidayList().size() > 0 )
            {
                pojoIndex = SLACommonUtils.getHolidayList().indexOf( crmHolidayDetails );
            }
            if ( pojoIndex == -1 )
            {
                isHoliday = false;
            }
            else
            {
                LOGGER.info( "Holiday by passed: " + crmHolidayDetails.getHolidayDate() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking for holiday date", ex );
        }
        return isHoliday;
    }*/
    //    public long getHoursForWorkingHours( Calendar inCreationTime, Calendar inReportingTime )
    //    {
    //        LOGGER.info( "Inside ServiceLevelManagerImpl, +getHoursForWorkingHours" );
    //        boolean isValid = false;
    //        inCreationTime = SLAUtil.setWorkingHoursStartTime( inCreationTime );
    //        inReportingTime = SLAUtil.setWorkingHoursEndTime( inReportingTime );
    //        int creationDayOfYear = inCreationTime.get( Calendar.DAY_OF_YEAR );
    //        int creationYear = inCreationTime.get( Calendar.YEAR );
    //        int reportingDayOfYear = inReportingTime.get( Calendar.DAY_OF_YEAR );
    //        int reportingYear = inReportingTime.get( Calendar.YEAR );
    //        int daysDifference = reportingDayOfYear - creationDayOfYear;
    //        long diff = 0;
    //        if ( reportingYear - creationYear > 0 )
    //        {
    //            creationDayOfYear = - ( reportingYear - creationYear ) * ( 365 - creationDayOfYear );
    //        }
    //        daysDifference = reportingDayOfYear - creationDayOfYear;
    //        Map<Integer, Integer> holidaysList = new HashMap<Integer, Integer>();
    //        isValid = StringUtils.isValidObj( holidaysList.get( reportingDayOfYear ) ) ? holidaysList
    //                .get( reportingDayOfYear ) != reportingYear : true;
    //        if ( daysDifference == 0 && ( isValid && inReportingTime.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY ) )
    //        {
    //            diff = inReportingTime.getTime().getTime() - inCreationTime.getTime().getTime();
    //        }
    //        else if ( daysDifference > 0 )
    //        {
    //            Calendar tempCalendar = Calendar.getInstance();
    //            for ( int indexParam = creationDayOfYear; indexParam <= reportingDayOfYear; indexParam++ )
    //            {
    //                int dayOfYear = indexParam < 0 ? 365 + indexParam : indexParam;
    //                tempCalendar.set( Calendar.DAY_OF_YEAR, indexParam );
    //                isValid = StringUtils.isValidObj( holidaysList.get( dayOfYear ) )
    //                                                                                 ? holidaysList.get( dayOfYear ) != tempCalendar
    //                                                                                         .get( Calendar.YEAR ) : true;
    //                if ( isValid && tempCalendar.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
    //                {
    //                    if ( indexParam == creationDayOfYear )
    //                    {
    //                        diff = diff
    //                                + SLAUtil.calculateTimeDiffWorkingHours( inCreationTime, TIME_DIFFERENCE_FROM_DAY_END );
    //                    }
    //                    else if ( indexParam == reportingDayOfYear )
    //                    {
    //                        diff = diff
    //                                + SLAUtil.calculateTimeDiffWorkingHours( inReportingTime,
    //                                                                         TIME_DIFFERENCE_FROM_DAY_START );
    //                    }
    //                    else
    //                    {
    //                        diff = diff + SLAUtil.calculateTimeDiffWorkingHours( null, null );
    //                    }
    //                }
    //            }
    //        }
    //        return diff;
    //    }
    //    public long getHoursForDays( Calendar inCreationTime,
    //                                        Calendar inReportingTime,
    //                                        boolean isWorkingDayCalculation )
    //    {
    //        LOGGER.info( "Inside ServiceLevelManagerImpl, +getHoursForDays" );
    //        long diff = 0;
    //        boolean isValid = false;
    //        int daysDifference = 0;
    //        int creationDayOfYear = inCreationTime.get( Calendar.DAY_OF_YEAR );
    //        int creationYear = inCreationTime.get( Calendar.YEAR );
    //        int reportingDayOfYear = inReportingTime.get( Calendar.DAY_OF_YEAR );
    //        int reportingYear = inReportingTime.get( Calendar.YEAR );
    //        if ( reportingYear - creationYear > 0 )
    //        {
    //            creationDayOfYear = - ( reportingYear - creationYear ) * ( 365 - creationDayOfYear );
    //        }
    //        daysDifference = reportingDayOfYear - creationDayOfYear;
    //        Map<Integer, Integer> holidaysList = new HashMap<Integer, Integer>();
    //        isValid = StringUtils.isValidObj( holidaysList.get( reportingDayOfYear ) ) ? holidaysList
    //                .get( reportingDayOfYear ) != reportingYear : true;
    //        if ( daysDifference == 0
    //                && ( isValid && ( isWorkingDayCalculation
    //                                                         ? ( inReportingTime.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
    //                                                         : true ) ) )
    //        {
    //            diff = inReportingTime.getTime().getTime() - inCreationTime.getTime().getTime();
    //        }
    //        else if ( daysDifference > 0 )
    //        {
    //            Calendar tempCalendar = isWorkingDayCalculation ? Calendar.getInstance() : null;
    //            for ( int indexParam = creationDayOfYear; indexParam <= reportingDayOfYear; indexParam++ )
    //            {
    //                int dayOfYear = indexParam < 0 ? 365 + indexParam : indexParam;
    //                if ( isWorkingDayCalculation )
    //                {
    //                    tempCalendar.set( Calendar.DAY_OF_YEAR, indexParam );;
    //                }
    //                isValid = StringUtils.isValidObj( holidaysList.get( dayOfYear ) )
    //                                                                                 ? holidaysList.get( dayOfYear ) != tempCalendar
    //                                                                                         .get( Calendar.YEAR ) : true;
    //                if ( isValid
    //                        && ( isWorkingDayCalculation ? ( tempCalendar.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
    //                                                    : true ) )
    //                {
    //                    if ( indexParam == creationDayOfYear )
    //                    {
    //                        diff = diff + SLAUtil.calculateTimeDiff24Hours( inCreationTime, TIME_DIFFERENCE_FROM_DAY_END );
    //                    }
    //                    else if ( indexParam == reportingDayOfYear )
    //                    {
    //                        diff = diff
    //                                + SLAUtil.calculateTimeDiff24Hours( inReportingTime, TIME_DIFFERENCE_FROM_DAY_START );
    //                    }
    //                    else
    //                    {
    //                        diff = diff + SLAUtil.calculateTimeDiff24Hours( null, null );
    //                    }
    //                }
    //            }
    //        }
    //        return diff;
    //    }
    public static String getDurationBreakdown( long millis )
    {
        //LOGGER.info( "Inside SLAUtils, getDurationBreakdown" );
        if ( millis < 0 )
        {
            throw new IllegalArgumentException( "Duration must be greater than zero!" );
        }
        long days = TimeUnit.MILLISECONDS.toDays( millis );
        millis -= TimeUnit.DAYS.toMillis( days );
        long hours = TimeUnit.MILLISECONDS.toHours( millis );
        millis -= TimeUnit.HOURS.toMillis( hours );
        long minutes = TimeUnit.MILLISECONDS.toMinutes( millis );
        millis -= TimeUnit.MINUTES.toMillis( minutes );
        long seconds = TimeUnit.MILLISECONDS.toSeconds( millis );
        StringBuilder sb = new StringBuilder( 64 );
        sb.append( days );
        sb.append( " Days " );
        sb.append( hours );
        sb.append( " Hours " );
        sb.append( minutes );
        sb.append( " Minutes " );
        sb.append( seconds );
        sb.append( " Seconds" );
        return ( sb.toString() );
    }

    private long calculateTimeDiffWorkingHours( Calendar inTime, String inParam )
    {
        //LOGGER.info( "Inside SLAUtils, calculateTimeDiffWorkingHours" );
        long diff = 0;
        long dayStartInMillis = 0;
        long dayEndInMillis = 0;
        if ( StringUtils.equals( inParam, TIME_DIFFERENCE_FROM_DAY_END ) )
        {
            dayStartInMillis = inTime.getTimeInMillis();
            setWorkingHourEnds( inTime );
            dayEndInMillis = inTime.getTimeInMillis();
            diff = dayEndInMillis - dayStartInMillis;
        }
        else if ( StringUtils.equals( inParam, TIME_DIFFERENCE_FROM_DAY_START ) )
        {
            dayEndInMillis = inTime.getTimeInMillis();
            setWorkingHourStarts( inTime );
            dayStartInMillis = inTime.getTimeInMillis();
            diff = dayEndInMillis - dayStartInMillis;
        }
        else
        {
            diff = 9 * 60 * 60 * 1000; // 32400000
        }
        return diff;
    }

    private long calculateTimeDiff24Hours( Calendar inTime, String inParam )
    {
        //LOGGER.info( "Inside SLAUtils, calculateTimeDiff24Hours" );
        long diff = 0;
        long dayStartInMillis = 0;
        long dayEndInMillis = 0;
        if ( StringUtils.equals( inParam, TIME_DIFFERENCE_FROM_DAY_END ) )
        {
            dayStartInMillis = inTime.getTimeInMillis();
            DateUtils.setDayEndTime( inTime );
            dayEndInMillis = inTime.getTimeInMillis();
            diff = dayEndInMillis - dayStartInMillis;
        }
        else if ( StringUtils.equals( inParam, TIME_DIFFERENCE_FROM_DAY_START ) )
        {
            dayEndInMillis = inTime.getTimeInMillis();
            DateUtils.setDayStartTime( inTime );
            dayStartInMillis = inTime.getTimeInMillis();
            diff = dayEndInMillis - dayStartInMillis;
        }
        else
        {
            diff = 24 * 60 * 60 * 1000; // 86400000
        }
        return diff;
    }

    private Calendar setWorkingHoursEndTime( Calendar inCal )
    {
        LOGGER.info( "Inside SLAUtils, setWorkingHoursEndTime" );
        Calendar temp = Calendar.getInstance();
        temp.set( Calendar.DAY_OF_YEAR, inCal.get( Calendar.DAY_OF_YEAR ) );
        setWorkingHourEnds( temp );
        if ( inCal.after( temp ) )
        {
            return temp;
        }
        else
        {
            setWorkingHourStarts( temp );
            if ( inCal.before( temp ) )
            {
                inCal.add( Calendar.DAY_OF_YEAR, -1 );
                setWorkingHourEnds( inCal );
                return inCal;
            }
            else
            {
                return inCal;
            }
        }
    }

    private Calendar setWorkingHoursStartTime( Calendar inCal )
    {
        LOGGER.info( "Inside SLAUtils, setWorkingHoursStartTime" );
        Calendar temp = Calendar.getInstance();
        temp.set( Calendar.DAY_OF_YEAR, inCal.get( Calendar.DAY_OF_YEAR ) );
        setWorkingHourStarts( temp );
        if ( inCal.before( temp ) )
        {
            return temp;
        }
        else
        {
            setWorkingHourEnds( temp );
            if ( inCal.after( temp ) )
            {
                inCal.add( Calendar.DAY_OF_YEAR, 1 );
                setWorkingHourStarts( inCal );
                return inCal;
            }
            else
            {
                return inCal;
            }
        }
    }

    private void setWorkingHourEnds( Calendar inCalendar )
    {
        LOGGER.info( "Inside SLAUtils, setWorkingHourEnds" );
        inCalendar.set( Calendar.HOUR_OF_DAY, 20 );
        inCalendar.set( Calendar.MINUTE, 0 );
        inCalendar.set( Calendar.SECOND, 0 );
        inCalendar.set( Calendar.MILLISECOND, 0 );
    }

    private void setWorkingHourStarts( Calendar inCalendar )
    {
        LOGGER.info( "Inside SLAUtils, setWorkingHourStarts" );
        inCalendar.set( Calendar.HOUR_OF_DAY, 9 );
        inCalendar.set( Calendar.MINUTE, 30 );
        inCalendar.set( Calendar.SECOND, 0 );
        inCalendar.set( Calendar.MILLISECOND, 0 );
    }

    public boolean checkValidTimeCondition( XMLGregorianCalendar inTime, int difference )
    {
        boolean isValid = false;
        Date createdTime = DateUtils.convertXmlCalToDate( inTime );
        Date currentTime = Calendar.getInstance().getTime();
        long timeDifference = currentTime.getTime() - createdTime.getTime();
        long atleastDifference = difference * 60 * 60 * 1000;
        if ( timeDifference >= atleastDifference )
        {
            isValid = true;
        }
        return isValid;
    }

    public long changeHoursToMillis( int inHours )
    {
        return inHours * 60 * 60 * 1000;
    }

    public long changeDaysToMillis( int inDays )
    {
        return inDays * 24 * 60 * 60 * 1000;
    }

    public long changeHoursToMillis( long inHours )
    {
        return inHours * 60 * 60 * 1000;
    }

    public long changeDaysToMillis( long inDays )
    {
        return inDays * 24 * 60 * 60 * 1000;
    }

    public long calculateFutureHoursForWorkingHours( long inSLATime, Calendar inCreationTime )
    {
        LOGGER.info( "Inside SLAUtil, +calculateFutureHoursForWorkingHours" );
        long expectedTimeInMillis = 0;
        Calendar dayEndTime = null;
        Calendar tempCalendar = null;
        try
        {
            dayEndTime = Calendar.getInstance();
            tempCalendar = Calendar.getInstance();
            inCreationTime = setWorkingHoursStartTime( inCreationTime );
            dayEndTime.setTime( inCreationTime.getTime() );
            tempCalendar.setTime( inCreationTime.getTime() );
            setWorkingHourEnds( dayEndTime );
            long remainingSLA = inSLATime;
            long startTimeInMillis = 0;
            long dayEndTimeInMillis = 0;
            do
            {
                if ( !isHoliday( tempCalendar ) && tempCalendar.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
                {
                    startTimeInMillis = tempCalendar.getTimeInMillis();
                    dayEndTimeInMillis = dayEndTime.getTimeInMillis();
                    expectedTimeInMillis = startTimeInMillis + remainingSLA;
                    if ( expectedTimeInMillis <= dayEndTimeInMillis )
                    {
                        remainingSLA = 0;
                    }
                    else
                    {
                        remainingSLA = expectedTimeInMillis - dayEndTimeInMillis;
                    }
                }
                tempCalendar.add( Calendar.DAY_OF_YEAR, 1 );
                dayEndTime.add( Calendar.DAY_OF_YEAR, 1 );
                setWorkingHourStarts( tempCalendar );
            }
            while ( remainingSLA > 0 );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while calculating future hours for working hours", ex );
        }
        LOGGER.info( "Expected Time In Millis " + expectedTimeInMillis );
        return expectedTimeInMillis;
    }

    public long calculateFutureHoursForDays( long inSLATime, Calendar inCreationTime, boolean isWorkingDayCalculation )
    {
        LOGGER.info( "Inside SLAUtil, +calculateFutureHoursForWorkingHours" );
        long expectedTimeInMillis = 0;
        Calendar dayEndTime = null;
        Calendar tempCalendar = null;
        try
        {
            dayEndTime = Calendar.getInstance();
            tempCalendar = Calendar.getInstance();
            dayEndTime.setTime( inCreationTime.getTime() );
            tempCalendar.setTime( inCreationTime.getTime() );
            DateUtils.setDayEndTime( dayEndTime );
            long remainingSLA = inSLATime;
            long startTimeInMillis = 0;
            long dayEndTimeInMillis = 0;
            do
            {
                if ( !isHoliday( tempCalendar )
                        && ( isWorkingDayCalculation ? ( tempCalendar.get( Calendar.DAY_OF_WEEK ) != Calendar.SUNDAY )
                                                    : true ) )
                {
                    startTimeInMillis = tempCalendar.getTimeInMillis();
                    dayEndTimeInMillis = dayEndTime.getTimeInMillis();
                    expectedTimeInMillis = startTimeInMillis + remainingSLA;
                    if ( expectedTimeInMillis <= dayEndTimeInMillis )
                    {
                        remainingSLA = 0;
                    }
                    else
                    {
                        remainingSLA = expectedTimeInMillis - dayEndTimeInMillis;
                    }
                }
                tempCalendar.add( Calendar.DAY_OF_YEAR, 1 );
                dayEndTime.add( Calendar.DAY_OF_YEAR, 1 );
                DateUtils.setDayStartTime( tempCalendar );
            }
            while ( remainingSLA > 0 );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while calculating future hours for working hours", ex );
        }
        LOGGER.info( "Expected Time In Millis " + expectedTimeInMillis );
        return expectedTimeInMillis;
    }
    //    public Calendar calculateExpectedSLAReverse( Calendar inStartTime, String inSlaUnit, long inSLATime )
    //    {
    //        LOGGER.info( "Inside SLAUtil, +calculateExpectedSLA" );
    //        long calculatedSLA = 0;
    //        Calendar calendar = null;
    //        try
    //        {
    //            if ( inSLATime > 0 )
    //            {
    //                if ( StringUtils.equals( inSlaUnit, CRMDisplayListConstants.WORKING_HOURS.getCode() ) )
    //                {
    //                    calculatedSLA = calculateFutureHoursForWorkingHours( inSLATime, inStartTime );
    //                }
    //                else if ( StringUtils.equals( inSlaUnit, CRMDisplayListConstants.WORKING_DAYS.getCode() ) )
    //                {
    //                    calculatedSLA = calculateFutureHoursForDays( inSLATime, inStartTime, true );
    //                }
    //                else if ( StringUtils.equals( inSlaUnit, CRMDisplayListConstants.CALENDAR_DAYS.getCode() ) )
    //                {
    //                    calculatedSLA = calculateFutureHoursForDays( inSLATime, inStartTime, false );
    //                }
    //                calendar = Calendar.getInstance();
    //                calendar.setTimeInMillis( calculatedSLA );
    //                LOGGER.info( "Expected calculated SLA " + calendar.getTime() );
    //            }
    //        }
    //        catch ( Exception ex )
    //        {
    //            LOGGER.error( "Exception while calculating expected SLA time", ex );
    //        }
    //        return calendar;
    //    }
}
