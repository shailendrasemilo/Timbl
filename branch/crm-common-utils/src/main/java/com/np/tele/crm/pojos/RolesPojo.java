package com.np.tele.crm.pojos;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.GlobalUtils;
import com.np.tele.crm.utils.ICRMCacheManager;
import com.np.tele.crm.utils.StringUtils;

public class RolesPojo
    implements Serializable
{
    private static final Logger LOGGER         = Logger.getLogger( RolesPojo.class );
    Map<String, Boolean>        rolesPojos     = new TreeMap<String, Boolean>();
    String                      available      = null;
    String                      functionalBin  = null;
    String                      displayEnum    = null;
    String                      toDate         = null;
    private ICRMCacheManager    crmCacheManger = null;
    String                      xmlDate        = null;
    String                      reportXmlDate  = null;

    public Map<String, Boolean> getRolesPojos()
    {
        return rolesPojos;
    }

    public void setRolesPojos( Map<String, Boolean> inRolesPojos )
    {
        rolesPojos = inRolesPojos;
    }

    public String getAvailable( String inKey )
    {
        if ( rolesPojos.containsKey( inKey ) )
        {
            return rolesPojos.get( inKey ) + "";
        }
        else if ( StringUtils.contains( inKey, IAppConstants.COMMA ) )
        {
            return getMultiAvailable( StringUtils.split( inKey, IAppConstants.COMMA ) );
        }
        return false + "";
    }

    private String getMultiAvailable( String... inKey )
    {
        if ( StringUtils.isValidObj( inKey ) )
        {
            for ( int i = 0; i < inKey.length; i++ )
            {
                String key = StringUtils.trim( inKey[i] );
                if ( rolesPojos.containsKey( key ) && rolesPojos.get( key ) )
                {
                    return true + "";
                }
            }
        }
        return false + "";
    }

    public String getFunctionalBin( String inKey )
    {
        LOGGER.info( "inKey is" + inKey );
        return StringUtils.removeCommaStartEnd( inKey );
    }

    public String getDisplayEnum( String inKey )
    {
        if ( !StringUtils.contains( inKey, IAppConstants.COMMA ) )
        {
            throw new UnsupportedOperationException( "Wrong Prameters provided while retriving Enum value." );
        }
        if ( !StringUtils.isValidObj( crmCacheManger ) )
        {
            crmCacheManger = GlobalUtils.loadClass( "com.np.tele.crm.common.utils.CRMCacheManager" );
            LOGGER.info( "CRMCacheManager:-" + crmCacheManger );
        }
        try
        {
            String[] values = StringUtils.split( inKey, IAppConstants.COMMA );
            String displayValue = "";
            StringBuilder displayBuilder = new StringBuilder();
            if ( StringUtils.isNotBlank( values[0] ) && values.length == 1 )
            {
                return IAppConstants.DASH;
            }
            for ( int i = 1; i < values.length; i++ )
            {
                displayValue = CRMUtils.getEnumValue( values[0], values[i] );
                if ( StringUtils.isBlank( displayValue ) )
                {
                    displayValue = crmCacheManger.getDisplayValue( values[0], values[i] );
                }
                if ( StringUtils.isNotBlank( displayValue ) )
                {
                    if ( i > 1 )
                    {
                        displayBuilder.append( IAppConstants.COMMA + IAppConstants.WHITE_SPACE );
                    }
                    displayBuilder.append( displayValue );
                }
            }
            displayValue = displayBuilder.toString();
            if ( StringUtils.isNotBlank( displayValue ) )
            {
                displayValue = StringUtils.removeStart( displayValue, IAppConstants.COMMA + IAppConstants.WHITE_SPACE );
                displayValue = StringUtils.removeCommaStartEnd( displayValue );
                if ( StringUtils.equals( "0", displayValue ) || StringUtils.equals( " ", displayValue ) )
                {
                    displayValue = IAppConstants.DASH;
                }
                return displayValue;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to get the display value of key:" + inKey, ex );
        }
        return StringUtils.substring( inKey, inKey.indexOf( IAppConstants.COMMA ) + 1, inKey.length() );
    }

    public String getDisplayValues( String inKey )
    {
        if ( StringUtils.isBlank( inKey ) || StringUtils.equals( inKey, "0" ) )
        {
            return IAppConstants.DASH;
        }
        if ( !StringUtils.contains( inKey, IAppConstants.EQUAL ) && StringUtils.contains( inKey, IAppConstants.COMMA ) )
        {
            inKey = getDisplayEnum( inKey );
            return inKey;
        }
        String constantVal = IAppConstants.FUNCTIONAL_BIN;
        boolean cacheFile = true;
        int index = inKey.indexOf( constantVal );
        if ( index > -1 )
        {
            inKey = getDisplayValue( inKey, index, constantVal, "functionalBins", cacheFile );
        }
        cacheFile = false;
        constantVal = "Stage=";
        index = inKey.indexOf( constantVal );
        if ( index > -1 )
        {
            inKey = getDisplayValue( inKey, index, constantVal, CRMOperationStages.class.getSimpleName(), cacheFile );
        }
        constantVal = "PaymentStatus=";
        index = inKey.indexOf( constantVal );
        if ( index > -1 )
        {
            inKey = getDisplayValue( inKey, index, constantVal, "PaymentStatus", cacheFile );
        }
        constantVal = "CaseStatus=";
        index = inKey.indexOf( constantVal );
        if ( index > -1 )
        {
            inKey = getDisplayValue( inKey, index, constantVal, "CaseStatus", cacheFile );
        }
        constantVal = "RLStatus=";
        index = inKey.indexOf( constantVal );
        if ( index > -1 )
        {
            inKey = getDisplayValue( inKey, index, constantVal, "RealizationStatus", cacheFile );
        }
        return inKey;
    }

    public String getDisplayValue( String inKey, int index, String constantVal, String propType, boolean cacheFile )
    {
        String firtsPart = StringUtils.substring( inKey, 0, index );
        String secondPart = StringUtils.substring( inKey, index, inKey.length() );
        int tempIndex = StringUtils.ordinalIndexOf( secondPart, IAppConstants.EQUAL, 2 );
        String thirdPart = "";
        if ( tempIndex > 0 )
        {
            String tempStr = StringUtils.substring( secondPart, 0, tempIndex );
            int secondIndex = StringUtils.lastIndexOf( tempStr, IAppConstants.COMMA );
            if ( secondIndex > 0 )
            {
                secondPart = StringUtils.substring( tempStr, 0, secondIndex );
                thirdPart = StringUtils.substring( inKey, index + secondIndex, inKey.length() );
            }
        }
        if ( cacheFile && !StringUtils.isValidObj( crmCacheManger ) )
        {
            crmCacheManger = GlobalUtils.loadClass( "com.np.tele.crm.common.utils.CRMCacheManager" );
            LOGGER.info( "CRMCacheManager:-" + crmCacheManger );
        }
        try
        {
            String[] values = StringUtils.split( secondPart, IAppConstants.EQUAL );
            values = StringUtils.split( values[1], IAppConstants.COMMA );
            String displayValue = "";
            StringBuilder displayBuilder = new StringBuilder();
            for ( int i = 0; i < values.length; i++ )
            {
                if ( cacheFile )
                {
                    displayValue = crmCacheManger.getDisplayValue( propType, values[i] );
                }
                else
                {
                    if ( StringUtils.isNotBlank( values[i] ) )
                    {
                        displayValue = CRMUtils.getEnumValue( propType, values[i] );
                    }
                    else
                    {
                        displayValue = IAppConstants.DASH;
                    }
                }
                if ( StringUtils.isNotBlank( displayValue ) )
                {
                    if ( i > 0 )
                    {
                        displayBuilder.append( IAppConstants.COMMA + IAppConstants.WHITE_SPACE );
                    }
                    displayBuilder.append( displayValue );
                }
            }
            displayValue = displayBuilder.toString();
            if ( StringUtils.isNotBlank( displayValue ) )
            {
                displayValue = StringUtils.removeStart( displayValue, IAppConstants.COMMA + IAppConstants.WHITE_SPACE );
                displayValue = StringUtils.removeCommaStartEnd( displayValue );
                return firtsPart + constantVal + displayValue + thirdPart;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to get the display value of key:" + inKey, ex );
        }
        return inKey;
    }

    public String getToDate( String calendar )
    {
        if ( StringUtils.isBlank( calendar ) )
        {
            return IAppConstants.DASH;
        }
        String formattedDate = DateUtils.convertXGCtoCal( calendar );
        return formattedDate;
    }

    public String getXmlDate( String xmlDate )
    {
        if ( StringUtils.isBlank( xmlDate ) )
        {
            return IAppConstants.DASH;
        }
        return DateUtils.convertXMLXGCtoCal( xmlDate );
    }

    public String getReportXmlDate( String xmlDate )
    {
        if ( StringUtils.isBlank( xmlDate ) )
        {
            return IAppConstants.EMPTY_STRING;
        }
        return DateUtils.convertXMLXGCtoCal( xmlDate );
    }
}
