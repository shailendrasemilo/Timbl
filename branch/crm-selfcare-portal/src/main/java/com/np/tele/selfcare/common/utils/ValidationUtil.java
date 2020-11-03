package com.np.tele.selfcare.common.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.StringUtils;

public class ValidationUtil
{
    private static final Logger LOGGER = Logger.getLogger( ValidationUtil.class );

    //    public static String validateInputMobile( String inStr )
    //    {
    //        ValidationMasterPojo validationMasterPojo = CRMCacheManager.getValidationMaster();
    //        String[] startWithNos = StringUtils.split( validationMasterPojo.getMobileNoStartsWith(), "," );
    //        String startNos = validationMasterPojo.getMobileNoStartsWith();
    //        for ( String str : startWithNos )
    //        {
    //            if ( inStr.startsWith( str ) )
    //            {
    //                startNos = "";
    //                break;
    //            }
    //        }
    //        return startNos;
    //    }
    public static boolean isValidCollection( Collection<?> inCollection )
    {
        if ( StringUtils.isValidObj( inCollection ) && !inCollection.isEmpty() )
        {
            return true;
        }
        return false;
    }

    public static boolean isNotValidCollection( Collection<?> inCollection )
    {
        return !isValidCollection( inCollection );
    }

    /* public static boolean validatePassportNo( String inStr )
     {
         String pattern = "^([0-9]+[a-zA-Z]|[a-zA-Z])[0-9a-zA-Z]{15}*$";
         Pattern r = Pattern.compile( pattern );
         Matcher matcher = r.matcher( inStr );
         LOGGER.info( "Result of validating passport number  is: " + matcher.matches() );
         return matcher.matches();
     }

     public static boolean validateName( String inStr )
     {
         LOGGER.info( "validating name for string: " + inStr );
         String pattern =CRMRegex.PATTERN_EMAIL.getPattern(); 
        // String pattern =      "^[a-zA-Z]{3,45}$";
         Pattern r = Pattern.compile( pattern );
         Matcher matcher = r.matcher( inStr );
         LOGGER.info( "Result of validating name is: " + matcher.matches() );
         return matcher.matches();
     }

     public static boolean validatePanGirNo( String inStr )
     {
         String pattern = "^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$";
         Pattern r = Pattern.compile( pattern );
         Matcher matcher = r.matcher( inStr );
         return matcher.matches();
     }*/
    public static void prepareErrorMessage( ActionMessages inActionError, Map<String, Object[]> resultMap )
    {
        LOGGER.info( "Error Map:" + resultMap );
        if ( StringUtils.isValidObj( resultMap ) && !resultMap.isEmpty() )
        {
            Set<String> keyString = resultMap.keySet();
            for ( String key : keyString )
            {
                Object[] obj = resultMap.get( key );
                inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( key, obj ) );
            }
        }
    }
}
