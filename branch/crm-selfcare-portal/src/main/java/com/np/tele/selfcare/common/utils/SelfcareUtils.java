package com.np.tele.selfcare.common.utils;

import java.io.Serializable;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public class SelfcareUtils
    implements Serializable
{
    private static final Logger LOGGER        = Logger.getLogger( SelfcareUtils.class );
    String                      available     = null;
    String                      functionalBin = null;
    String                      displayEnum   = null;
    String                      toDate        = null;
    String                      formatDate    = null;
    //    private ICRMCacheManager    crmCacheManger = null;
    String                      xmlDate       = null;

    public String getFunctionalBin( String inKey )
    {
        LOGGER.info( "inKey is" + inKey );
        return StringUtils.removeCommaStartEnd( inKey );
    }

    public String getDisplayEnum( String inKey )
    {
        //        if ( !StringUtils.isValidObj( crmCacheManger ) )
        //        {
        //            crmCacheManger = CRMUtils.loadClass( "com.np.nextra.crm.common.utils.CRMCacheManager" );
        //            LOGGER.info( "CRMCacheManager:-" + crmCacheManger );
        //        }
        if ( !StringUtils.contains( inKey, IAppConstants.COMMA ) )
        {
            throw new UnsupportedOperationException( "Wrong Prameters provided while retriving Enum value." );
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
                //                if ( StringUtils.isBlank( displayValue ) )
                //                {
                //                    displayValue = crmCacheManger.getDisplayValue( values[0], values[i] );
                //                }
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
                return displayValue;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to get the display value of key:" + inKey, ex );
        }
        return StringUtils.substring( inKey, inKey.indexOf( IAppConstants.COMMA ) + 1, inKey.length() );
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

    public String getFormatDate( String inKey )
    {
        if ( !StringUtils.contains( inKey, IAppConstants.COMMA ) )
        {
            throw new UnsupportedOperationException( "Wrong Prameters provided while retriving Enum value." );
        }
        String[] values = StringUtils.split( inKey, IAppConstants.COMMA );
        if ( StringUtils.isNotBlank( values[0] ) && values.length == 3 )
        {
            String formatDate = DateUtils.getFormattedDate( values[0], values[1], values[2] );
            if ( StringUtils.isNotBlank( formatDate ) )
                return formatDate;
            else
                return values[0];
        }
        else if ( StringUtils.isNotBlank( values[0] ) )
        {
            return values[0];
        }
        return IAppConstants.DASH;
    }

    public String getXmlDate( String xmlDate )
    {
        return DateUtils.convertXMLXGCtoCal( xmlDate );
    }
}
