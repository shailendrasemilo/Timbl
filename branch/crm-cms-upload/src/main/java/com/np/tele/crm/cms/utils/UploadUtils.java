package com.np.tele.crm.cms.utils;

import java.io.File;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import com.np.tele.crm.cms.constants.CMSHeaderTitle;
import com.np.tele.crm.cms.constants.CMSPaymentDataTypes;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public class UploadUtils
{
    private static final Logger LOGGER = Logger.getLogger( UploadUtils.class );

    public static boolean isValidFile( String inName )
    {
        boolean isValid = false;
        try
        {
            if ( inName.matches( CRMRegex.PATTERN_VALIDATE_FILE_TYPE.getPattern() ) )
            {
                isValid = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while checking file type validity.", ex );
        }
        return isValid;
    }

    public static final Set<String> getHeaders( String inType, Map<String, Set<String>> inHeaderMap )
    {
        if ( StringUtils.isNotBlank( inType ) )
        {
            if ( StringUtils.isValidObj( inHeaderMap ) )
            {
                if ( inHeaderMap.containsKey( inType ) )
                {
                    return inHeaderMap.get( inType );
                }
                else
                {
                    Set<String> expectedHeader = new HashSet<String>();
                    for ( CMSHeaderTitle fileHeader : CMSHeaderTitle.values() )
                    {
                        if ( StringUtils.equalsIgnoreCase( "CMS_" + inType, fileHeader.getUpLoadType() ) )
                        {
                            expectedHeader.add( fileHeader.getTitle() );
                        }
                    }
                    inHeaderMap.put( inType, expectedHeader );
                    return expectedHeader;
                }
            }
        }
        return null;
    }

    public static final Set<String> getProperties( String inType, Map<String, Set<String>> inPropertyMap )
    {
        if ( StringUtils.isNotBlank( inType ) )
        {
            if ( StringUtils.isValidObj( inPropertyMap ) )
            {
                if ( inPropertyMap.containsKey( inType ) )
                {
                    return inPropertyMap.get( inType );
                }
                else
                {
                    Set<String> expectedProperties = new HashSet<String>();
                    for ( CMSHeaderTitle fileHeader : CMSHeaderTitle.values() )
                    {
                        if ( StringUtils.equalsIgnoreCase( "CMS_" + inType, fileHeader.getUpLoadType() ) )
                        {
                            if ( StringUtils.isNotBlank( fileHeader.getProperty() ) )
                            {
                                expectedProperties.add( fileHeader.getProperty() );
                            }
                        }
                    }
                    inPropertyMap.put( inType, expectedProperties );
                    return expectedProperties;
                }
            }
        }
        return null;
    }

    public static void changeFileLocation( String inSrc, String inDest, CrmCmsFilePojo inCrmCmsFilePojo )
    {
        File srcFile = new File( inSrc + IAppConstants.FILE_SEPERATOR + inCrmCmsFilePojo.getCmsFileName() );
        File destFile = null;
        if ( StringUtils.equals( inCrmCmsFilePojo.getStatus(), CRMStatusCode.FAILURE.getStatusCode() ) )
        {
            destFile = new File( inDest + IAppConstants.FILE_SEPERATOR
                    + getNameWithTime( inCrmCmsFilePojo.getCmsFileName() ) + IAppConstants.UNDERSCORE
                    + CRMStatusCode.FAILURE );
        }
        else if ( StringUtils.equals( inCrmCmsFilePojo.getStatus(), CRMStatusCode.ARCHIVE.getStatusCode() ) )
        {
            destFile = new File( inDest + IAppConstants.FILE_SEPERATOR
                    + getNameWithTime( inCrmCmsFilePojo.getCmsFileName() ) );
        }
        else
        {
            destFile = new File( inDest + IAppConstants.FILE_SEPERATOR + inCrmCmsFilePojo.getCmsFileName() );
        }
        try
        {
            if ( srcFile.renameTo( destFile ) )
            {
                LOGGER.info( "File moved successfully, New path : " + destFile.getAbsolutePath() );
            }
            else
            {
                LOGGER.info( "Unable to move file " + inCrmCmsFilePojo.getCmsFileName() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Source : " + inSrc + " Destination : " + destFile );
            LOGGER.error( "Error while moving file.", ex );
        }
    }

    public static void changeFileLocation( String inSrc, String inDest, String inFileName )
    {
        File srcFile = new File( inSrc + IAppConstants.FILE_SEPERATOR + inFileName );
        File destFile = new File( inDest + IAppConstants.FILE_SEPERATOR + inFileName );
        try
        {
            if ( srcFile.renameTo( destFile ) )
            {
                LOGGER.info( "File moved successfully, New path : " + destFile.getAbsolutePath() );
            }
            else
            {
                LOGGER.info( "Unable to move file " + inFileName );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Source : " + inSrc + " Destination : " + destFile );
            LOGGER.error( "Error while moving file.", ex );
        }
    }

    public static String getFilePath( String inSrc, String inFileName )
    {
        String filePath = null;
        try
        {
            filePath = inSrc + IAppConstants.FILE_SEPERATOR + inFileName;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while getting file...", ex );
        }
        return filePath;
    }

    public static String getNameWithTime( final String inName )
    {
        GregorianCalendar curDate = new GregorianCalendar();
        curDate.setTime( new Date() );
        return inName + IAppConstants.UNDERSCORE + IDateConstants.SDF_YYYYMMDD_HHMMSS.format( curDate.getTime() );
    }

    public static Object getValidObj( CMSPaymentDataTypes inType, String inValue )
    {
        Object obj = null;
        //LOGGER.info( "CMSPaymentDataTypes : " + inType.getType() + " Value : " + inValue );
        try
        {
            if ( !StringUtils.isEmpty( inValue ) )
            {
                inValue = neutralizeValue( inValue );
                switch ( inType )
                {
                    case Double:
                        //LOGGER.info( "Validity : " + inValue.matches( CRMRegex.PATTERN_DOUBLE_VALUE.getPattern() ) );
                        if ( inValue.matches( CRMRegex.PATTERN_DOUBLE_VALUE.getPattern() ) )
                        {
                            obj = Double.parseDouble( inValue ) ;
                        }
                        break;
                    case INTEGER:
                        if ( StringUtils.isNumeric( inValue ) )
                        {
                            obj = Integer.parseInt( inValue );
                        }
                        break;
                    case SHORT:
                        if ( StringUtils.isNumeric( inValue ) )
                        {
                            obj = Short.parseShort( inValue );
                        }
                        break;
                    case DATE:
                        Calendar date = Calendar.getInstance();
                        if ( inValue.matches( CRMRegex.PATTERN_DATE_VALUE.getPattern() ) )
                        {
                            XMLGregorianCalendar xmlCustDobDate = DateUtils
                                    .toXMLGregorianCalendar( resolveDate( inValue ) );
                            //LOGGER.info( "xmlCustDobDate ::::: " + xmlCustDobDate );
                            obj = xmlCustDobDate;
                            //LOGGER.info( "xmlCustDobDate To String::::: " + obj.toString() );
                        }
                        else
                        {
                            date.setTime( IDateConstants.SDF_GMT.parse( inValue ) );
                            XMLGregorianCalendar xmlCustDobDate = DateUtils.toXMLGregorianCalendar( date );
                            obj = xmlCustDobDate;
                        }
                        break;
                    case STRING:
                        obj = inValue;
                        break;
                    default:
                        break;
                }
            }
        }
        catch ( NumberFormatException ex )
        {
            LOGGER.error( "Number Format Exception", ex );
        }
        catch ( ParseException ex )
        {
            LOGGER.error( "Parse Exception", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while preparing value object", ex );
        }
        //LOGGER.info( "Object : " + obj );
        return obj;
    }

    public static Calendar resolveDate( String inValue )
    {
        Calendar date = Calendar.getInstance();
        try
        {
            String year = "", month = "", day = "";
            String[] factorizeDate;
            if ( inValue.contains( "/" ) )
            {
                factorizeDate = StringUtils.split( inValue, "/" );
                year = factorizeDate[2];
                month = factorizeDate[1];
                day = factorizeDate[0];
            }
            else
            {
                year = StringUtils.right( inValue, 4 );
                inValue = StringUtils.removeEnd( inValue, year );
                switch ( inValue.length() )
                {
                    case 2:
                        month = StringUtils.right( inValue, 1 );
                        inValue = StringUtils.removeEnd( inValue, month );
                        break;
                    case 3:
                        month = StringUtils.right( inValue, 2 );
                        if ( Integer.parseInt( month ) > 12 )
                        {
                            month = StringUtils.right( inValue, 1 );
                        }
                        inValue = StringUtils.removeEnd( inValue, month );
                        break;
                    case 4:
                        month = StringUtils.right( inValue, 2 );
                        inValue = StringUtils.removeEnd( inValue, month );
                        break;
                }
                day = inValue;
            }
            if ( !StringUtils.isEmpty( year ) && !StringUtils.isEmpty( month ) && !StringUtils.isEmpty( day ) )
            {
                String finalDate = day + "/" + month + "/" + year;
                date.setTime( IDateConstants.SDF_DD_MM_YYYY.parse( finalDate ) );
            }
        }
        catch ( NumberFormatException ex )
        {
            LOGGER.error( "Number Format Exception", ex );
        }
        catch ( ParseException ex )
        {
            LOGGER.error( "Parse Exception", ex );
        }
        return date;
    }

    public static CMSPaymentDataTypes getPropertyType( String inType )
    {
        CMSPaymentDataTypes cmsTypes = null;
        for ( CMSPaymentDataTypes cmsDataTypes : CMSPaymentDataTypes.values() )
        {
            if ( StringUtils.equals( inType, cmsDataTypes.getType() ) )
            {
                cmsTypes = cmsDataTypes;
            }
        }
        return cmsTypes;
    }

    public static String neutralizeValue( String inStr )
    {
        if ( !StringUtils.isEmpty( inStr ) && inStr.endsWith( ".0" ) )
        {
            inStr = StringUtils.removeEnd( inStr, ".0" );
        }
        else if ( !StringUtils.isEmpty( inStr ) && inStr.contains( "." ) && inStr.contains( "E" ) )
        {
            inStr = StringUtils.remove( inStr, "." );
            inStr = StringUtils.substringBefore( inStr, "E" );
        }
        return inStr;
    }

    public static void main( String[] args )
    {
        System.out.println( DateUtils.getCurrentDateInFormat( IDateConstants.yyMMddHHmmSS ) );
    }
}
