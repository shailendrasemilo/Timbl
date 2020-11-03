package com.np.tele.crm.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.ext.pojos.UserMasterPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public final class CRMServiceCache
{
    private static final Logger              LOGGER     = Logger.getLogger( CRMServiceCache.class );
    private static final Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
    private static final Map<String, Date>   timeMap    = new LinkedHashMap<String, Date>();

    public static void toRefresh( String inKey )
    {
        synchronized ( timeMap )
        {
            timeMap.remove( inKey );
        }
    }

    private static Object getCachedData( String inKey )
    {
        LOGGER.info( "Content Map:" + contentMap );
        if ( timeMap.containsKey( inKey ) && contentMap.containsKey( inKey ) )
        {
            if ( DateUtils.minuteDiff( timeMap.get( inKey ), Calendar.getInstance().getTime() ) < 1 )
            {
                return contentMap.get( inKey );
            }
        }
        return null;
    }

    private static void setCachedData( String inKey, Object inObj )
    {
        timeMap.put( inKey, Calendar.getInstance().getTime() );
        contentMap.put( inKey, inObj );
    }

    public static final UserMasterPojo getUserMasterPojo()
    {
        UserMasterPojo userMasterPojo = (UserMasterPojo) getCachedData( UserMasterPojo.class.getSimpleName() );
        ConfigDto configDto = null;
        if ( !StringUtils.isValidObj( userMasterPojo ) )
        {
            try
            {
                configDto = CRMServicesProxy
                        .getInstance()
                        .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .configOperations( ServiceParameter.LIST.getParameter(),
                                           CRMParameter.USER_ACCESS.getParameter(), null );
                userMasterPojo = configDto.getUserMasterPojo();
                setCachedData( UserMasterPojo.class.getSimpleName(), userMasterPojo );
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Unable to retrive User Master Pojo:", ex );
            }
        }
        return userMasterPojo;
    }
}
