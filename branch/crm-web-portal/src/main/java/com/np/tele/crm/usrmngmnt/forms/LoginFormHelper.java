package com.np.tele.crm.usrmngmnt.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ValidationEnum;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.UserMasterPojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class LoginFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( LoginFormHelper.class );

    public static ActionErrors validateForm( ActionErrors inErrors, LoginForm inLoginForm )
    {
        LOGGER.info( "validateForm method called " );
        boolean status = uniqueRoles( inLoginForm.getUserRolesPojos() );
        LOGGER.info( "validateForm method called status " + status );
        if ( !status )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.DUPLICATE_ENTRY ) );
        }
        if ( !viewAccessValidation( inErrors, inLoginForm ) )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_READACCESS_REQUIRED ) );
        }
        accessValidation( inErrors, inLoginForm );
        return null;
    }

    public static void accessValidation( ActionErrors inErrors, LoginForm inLoginForm )
    {
        for ( UserRolesPojo userRolesPojo : inLoginForm.getUserRolesPojos() )
        {
            if ( ( !userRolesPojo.isCreateAccess() ) && ( !userRolesPojo.isDeleteAccess() )
                    && ( !userRolesPojo.isUpdateAccess() ) && ( !userRolesPojo.isRecoverAccess() )
                    && ( !userRolesPojo.isReadAccess() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_ACCESS_REQUIRED ) );
                break;
            }
        }
    }

    public static boolean viewAccessValidation( ActionErrors inErrors, LoginForm inLoginForm )
    {
        boolean returnValue = true;
        for ( UserRolesPojo userRolesPojo : inLoginForm.getUserRolesPojos() )
        {
            if ( ( userRolesPojo.isCreateAccess() ) || ( userRolesPojo.isDeleteAccess() )
                    || ( userRolesPojo.isUpdateAccess() ) || ( userRolesPojo.isRecoverAccess() ) )
            {
                boolean readAccessStatus = userRolesPojo.isReadAccess();
                if ( !readAccessStatus )
                {
                    returnValue = false;
                    break;
                }
            }
        }
        return returnValue;
    }

    public static ActionErrors validateParameters( ActionErrors inActionError, LoginForm inLoginForm )
    {
        //        long allowedIPId = 0;
        long startTimeId = 0;
        long endTimeId = 0;
        LOGGER.info( "validateParameters method called" );
        UserMasterPojo userMasterPojo = CRMCacheManager.getUserMaster();
        Set<String> keyVal = new HashSet<String>();
        Set<Long> parameIDs = new HashSet<Long>();
        if ( StringUtils.isValidObj( userMasterPojo ) )
        {
            startTimeId = userMasterPojo.getStartTimeId();
            endTimeId = userMasterPojo.getEndTimeId();
            if ( CommonValidator.isValidCollection( inLoginForm.getUserRolesPojos() ) )
            {
                outer: for ( UserRolesPojo userRolepPojo : inLoginForm.getUserRolesPojos() )
                {
                    long paramId = userRolepPojo.getCrmParameter().getParameterId();
                    if ( StringUtils.isValidObj( userRolepPojo.getCrmParameter() ) )
                    {
                        if ( StringUtils.isEmpty( userRolepPojo.getParameterValue() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.PARAMETER_VALUE_REQUIRED ) );
                            break;
                        }
                        else
                        {
                            boolean unique = keyVal.add( paramId + IAppConstants.DASH
                                    + userRolepPojo.getParameterValue() );
                            if ( !unique )
                            {
                                inActionError
                                        .add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_VALUE_FOR_PARAMETER,
                                                                 userRolepPojo.getParameterValue() ) );
                                break;
                            }
                        }
                        for ( CrmParameterPojo pojo : inLoginForm.getCrmParameterPojos() )
                        {
                            if ( pojo.getParameterId() == paramId )
                            {
                                if ( !StringUtils.compareRegularExp( pojo.getParameterPattern(),
                                                                     userRolepPojo.getParameterValue() ) )
                                {
                                    LOGGER.info( "Parameter Pattern:" + pojo.getParameterPattern() );
                                    LOGGER.info( "Error Key for Parameter:"
                                            + CRMRegex.getErrorKeyByPattern( pojo.getParameterPattern() ) );
                                    inActionError.add( IAppConstants.APP_ERROR,
                                                       new ActionMessage( CRMRegex.getErrorKeyByPattern( pojo
                                                               .getParameterPattern() ), pojo.getParameterName() ) );
                                    break outer;
                                }
                                if ( StringUtils.equals( ValidationEnum.SINGLE_MULTIPLICITY.getParameter(),
                                                         pojo.getParameterMultiplicity() ) )
                                {
                                    if ( !parameIDs.add( paramId ) )
                                    {
                                        inActionError.add( IAppConstants.APP_ERROR,
                                                           new ActionMessage( ValidationEnum.SINGLE_MULTIPLICITY
                                                                   .getErrorKey(), pojo.getParameterName() ) );
                                        break outer;
                                    }
                                }
                            }
                        }
                        if ( ( ( paramId == startTimeId ) ) || ( ( paramId == endTimeId ) ) )
                        {
                            if ( StringUtils.isNumeric( userRolepPojo.getParameterValue() ) )
                            {
                                if ( !validateSimilarTimeParameterValue( inLoginForm.getUserRolesPojos(), startTimeId,
                                                                         endTimeId ) )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.ERROR_SIMILAR_TIME_BASED_PARAMETER ) );
                                    break;
                                }
                                else if ( !validateTimeParameterPairing( inLoginForm.getUserRolesPojos(), startTimeId,
                                                                         endTimeId ) )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.ERROR_PARAMETER_PAIR_REQUIRED ) );
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            else if ( CommonValidator.isValidCollection( inLoginForm.getUserRolesPojosForGroup() ) )
            {
                boolean toProceed = false;
                for ( UserRolesPojo pojo : inLoginForm.getUserRolesPojosForGroup() )
                {
                    if ( null != pojo.getGroups() && ( pojo.isEditable() || pojo.getRecordId() > 0 ) )
                    {
                        toProceed = true;
                        break;
                    }
                }
                if ( !toProceed )
                {
                    LOGGER.info( "Parameter groups are null." );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
                }
            }
            else
            {
                LOGGER.info( "Prameter and Parameter groups are null " );
                inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
            }
        }
        else
        {
            LOGGER.info( "userMasterPojo found null. " );
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
        }
        return inActionError;
    }

    private static boolean validateTimeParameterPairing( List<UserRolesPojo> inUserRolePojo,
                                                         long startTimeId,
                                                         long endTimeId )
    {
        long startTimeID = -1;
        long endTimeID = -1;
        long startTimeInactiveID = -1;
        long endTimeInactiveID = -1;
        boolean returnValue = false;
        for ( UserRolesPojo userRolePojo : inUserRolePojo )
        {
            if ( userRolePojo.getCrmParameter().getParameterId() == startTimeId
                    && userRolePojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                startTimeID = userRolePojo.getCrmParameter().getParameterId();
            }
            if ( userRolePojo.getCrmParameter().getParameterId() == endTimeId
                    && userRolePojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                endTimeID = userRolePojo.getCrmParameter().getParameterId();
            }
            if ( userRolePojo.getCrmParameter().getParameterId() == startTimeId
                    && userRolePojo.getStatus().equals( CRMStatusCode.INACTIVE.getStatusCode() ) )
            {
                startTimeInactiveID = userRolePojo.getCrmParameter().getParameterId();
            }
            if ( userRolePojo.getCrmParameter().getParameterId() == endTimeId
                    && userRolePojo.getStatus().equals( CRMStatusCode.INACTIVE.getStatusCode() ) )
            {
                endTimeInactiveID = userRolePojo.getCrmParameter().getParameterId();
            }
            if ( ( startTimeID > IAppConstants.ZERO ) && ( endTimeID > IAppConstants.ZERO ) )
            {
                returnValue = true;
            }
            if ( ( startTimeInactiveID > IAppConstants.ZERO ) && ( endTimeInactiveID > IAppConstants.ZERO ) )
            {
                returnValue = true;
            }
        }
        return returnValue;
    }

    private static boolean validateSimilarTimeParameterValue( List<UserRolesPojo> inUserRolePojo,
                                                              long startTimeId,
                                                              long endTimeId )
    {
        int startTimeValue = -1;
        int endTimeValue = -1;
        boolean status = true;
        LOGGER.info( "validateSimilarTimeParameterValue" + inUserRolePojo.size() );
        for ( UserRolesPojo userRolePojo : inUserRolePojo )
        {
            LOGGER.info( "validateSimilarTimeParameterValue" + userRolePojo.getCrmParameter().getParameterId() );
            if ( userRolePojo.getCrmParameter().getParameterId() == startTimeId )
            {
                if ( StringUtils.isNumeric( userRolePojo.getParameterValue() ) )
                {
                    startTimeValue = Integer.parseInt( userRolePojo.getParameterValue() );
                }
                LOGGER.info( "validateSimilarTimeParameterValue" + startTimeValue );
            }
            if ( userRolePojo.getCrmParameter().getParameterId() == endTimeId )
            {
                if ( StringUtils.isNumeric( userRolePojo.getParameterValue() ) )
                {
                    LOGGER.info( "validateSimilarTimeParameterValue endTimeValue" + endTimeValue );
                    endTimeValue = Integer.parseInt( userRolePojo.getParameterValue() );
                }
                LOGGER.info( "validateSimilarTimeParameterValue endTimeValue" + endTimeValue );
            }
            LOGGER.info( "Start Time " + startTimeValue + " End Time Value :::" + endTimeValue );
        }
        if ( endTimeValue == startTimeValue )
        {
            status = false;
        }
        return status;
    }

    private static boolean uniqueTimeBasedParameter( final List<UserRolesPojo> inUserRolesPojos,
                                                     long startTimeId,
                                                     long endTimeId )
    {
        Set<Long> parameterSet = new HashSet<Long>();
        for ( UserRolesPojo userRolePojo : inUserRolesPojos )
        {
            if ( ( userRolePojo.getCrmParameter().getParameterId() == startTimeId && StringUtils
                    .equals( CRMStatusCode.ACTIVE.getStatusCode(), userRolePojo.getStatus() ) )
                    || ( userRolePojo.getCrmParameter().getParameterId() == endTimeId )
                    && StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), userRolePojo.getStatus() ) )
            {
                if ( !parameterSet.add( userRolePojo.getCrmParameter().getParameterId() ) )
                {
                    parameterSet = null;
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean uniqueRoles( final List<UserRolesPojo> inUserRolesPojos )
    {
        Set<Long> roleSet = new HashSet<Long>();
        for ( UserRolesPojo userRolesPojo : inUserRolesPojos )
        {
            if ( !roleSet.add( userRolesPojo.getCrmRoles().getRoleId() ) )
            {
                roleSet = null;
                return false;
            }
        }
        return true;
    }

    public static void resetUserForm( LoginForm inLoginForm, String inMethodName )
    {
        LOGGER.info( "resetUserForm method called" );
        if ( StringUtils.equals( IAppConstants.METHOD_NEW_USER_FORWARD, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_USER_BY_ID, inMethodName ) )
        {
            inLoginForm.setFirstName( "" );
            inLoginForm.setLastName( "" );
            inLoginForm.setUserId( "" );
            inLoginForm.setEmailId( "" );
            inLoginForm.setMobileNo( 0 );
            //            inLoginForm.setEmpType( "0" );
            //            inLoginForm.setEmpCode( " " );
            //            inLoginForm.setPartnerId( "" );
            inLoginForm.setPasswordExpiry( 3 );
            inLoginForm.setUserAccountExpiry( 10 );
            inLoginForm.setFunctionalBin( null );
            inLoginForm.setCrmUserPojo( null );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_ASSIGN_USER_PARAMETER, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inLoginForm.getUserRolesPojosForGroup() )
                    && !inLoginForm.getUserRolesPojosForGroup().isEmpty() )
            {
                int size = inLoginForm.getUserRolesPojosForGroup().size();
                List<UserRolesPojo> userRolesPojos = new ArrayList<UserRolesPojo>( size );
                UserRolesPojo userRolesPojo = null;
                for ( int i = 0; i < size; i++ )
                {
                    userRolesPojo = new UserRolesPojo();
                    userRolesPojo.setGroups( new GroupsPojo() );
                    userRolesPojos.add( userRolesPojo );
                }
                inLoginForm.setUserRolesPojosForGroup( userRolesPojos );
            }
            if ( StringUtils.isValidObj( inLoginForm.getUserRolesPojos() )
                    && !inLoginForm.getUserRolesPojos().isEmpty() )
            {
                int size = inLoginForm.getUserRolesPojos().size();
                List<UserRolesPojo> userRolesPojos = new ArrayList<UserRolesPojo>( size );
                UserRolesPojo userRolesPojo = null;
                for ( int i = 0; i < size; i++ )
                {
                    userRolesPojo = new UserRolesPojo();
                    userRolesPojo.setCrmParameter( new CrmParameterPojo() );
                    userRolesPojos.add( userRolesPojo );
                }
                inLoginForm.setUserRolesPojos( userRolesPojos );
            }
        }
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_USER_ROLE_ROW, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inLoginForm.getUserRolesPojos() )
                    && !inLoginForm.getUserRolesPojos().isEmpty() )
            {
                for ( UserRolesPojo userRolesPojos : inLoginForm.getUserRolesPojos() )
                {
                    resetAccessToFalse( userRolesPojos );
                }
            }
            if ( StringUtils.isValidObj( inLoginForm.getUserRolesPojosForGroup() )
                    && !inLoginForm.getUserRolesPojosForGroup().isEmpty() )
            {
                for ( UserRolesPojo userRolesPojos : inLoginForm.getUserRolesPojosForGroup() )
                {
                    resetAccessToFalse( userRolesPojos );
                }
            }
        }
        if ( StringUtils.equals( "assignAreaPage", inMethodName ) )
        {
            if ( null != inLoginForm.getAreaMappingPojos() && inLoginForm.getAreaMappingPojos().size() > 0 )
            {
                inLoginForm.getAreaMappingPojos().clear();
            }
        }
        if ( StringUtils.equals( "addAreaRow", inMethodName ) )
        {
        }
        if ( StringUtils.equals( IAppConstants.METHOD_ASSIGN_USER_ROLE, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inLoginForm.getUserRolesPojosForGroup() )
                    && !inLoginForm.getUserRolesPojosForGroup().isEmpty() )
            {
                int size = inLoginForm.getUserRolesPojosForGroup().size();
                List<UserRolesPojo> userRolesPojos = new ArrayList<UserRolesPojo>( size );
                UserRolesPojo userRolesPojo = null;
                for ( int i = 0; i < size; i++ )
                {
                    userRolesPojo = new UserRolesPojo();
                    userRolesPojo.setGroups( new GroupsPojo() );
                    userRolesPojos.add( userRolesPojo );
                }
                inLoginForm.setUserRolesPojosForGroup( userRolesPojos );
            }
            if ( StringUtils.isValidObj( inLoginForm.getUserRolesPojos() )
                    && !inLoginForm.getUserRolesPojos().isEmpty() )
            {
                int size = inLoginForm.getUserRolesPojos().size();
                List<UserRolesPojo> userRolesPojos = new ArrayList<UserRolesPojo>( size );
                UserRolesPojo userRolesPojo = null;
                for ( int i = 0; i < size; i++ )
                {
                    userRolesPojo = new UserRolesPojo();
                    userRolesPojo.setCrmRoles( new CrmRolesPojo() );
                    userRolesPojos.add( userRolesPojo );
                }
                inLoginForm.setUserRolesPojos( userRolesPojos );
            }
        }
        else if ( StringUtils.equals( IAppConstants.VERIFY_USER, inMethodName ) )
        {
            inLoginForm.setRecordID( 0 );
            inLoginForm.setFirstName( null );
            inLoginForm.setLastName( null );
        }
        LOGGER.info( "resetUserForm method end" );
    }

    private static void resetAccessToFalse( UserRolesPojo inUserRolesPojos )
    {
        inUserRolesPojos.setEditable( false );
        inUserRolesPojos.setReadAccess( false );
        inUserRolesPojos.setCreateAccess( false );
        inUserRolesPojos.setUpdateAccess( false );
        inUserRolesPojos.setDeleteAccess( false );
        inUserRolesPojos.setRecoverAccess( false );
    }

    private static void resetAccessToFalseArea( AreaMappingPojo inpojo )
    {
        LOGGER.info( "resetAccessToFalseArea Method called::" );
        inpojo.setEditable( false );
        // inUserRolesPojos.setMappingId( 0l);
    }

    public static ActionMessages checkFunctionalBinforSales( ActionMessages inActionError,
                                                             LoginForm inLoginForm,
                                                             String inMethod )
    {
        boolean flag = false;
        for ( CrmRcaReasonPojo crmRcaReasonPojo : CRMCacheManager.getFunctionalBins() )
        {
            for ( String str : inLoginForm.getFunctionalBinArray() )
            {
                if ( crmRcaReasonPojo.getCategoryId() == Long.parseLong( str )
                        && crmRcaReasonPojo.getCategoryValue().equals( IAppConstants.CATEGORY_VALUE ) )
                {
                    flag = true;
                    if ( StringUtils.isEmpty( inLoginForm.getSrCode() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_SR_CODE_EMPTY ) );
                    }
                }
            }
        }
        if ( !flag )
        {
            inLoginForm.setSrCode( null );
        }
        return inActionError;
    }

    public static void validateArea( ActionMessages inActionError, LoginForm inLoginForm )
    {
        LOGGER.info( "validateArea method called" );
        UserMasterPojo userMasterPojo = CRMCacheManager.getUserMaster();
        Set<String> keyVal = new HashSet<String>();
        if ( StringUtils.isValidObj( userMasterPojo ) )
        {
            if ( CommonValidator.isValidCollection( inLoginForm.getAreaMappingPojos() ) )
            {
                outer: for ( AreaMappingPojo userRolepPojo : inLoginForm.getAreaMappingPojos() )
                {
                    long paramId = 1;
                    if ( StringUtils.isValidObj( userRolepPojo ) )
                    {
                        if ( userRolepPojo.getAreaId() == 0 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.AREA_VALUE_REQUIRED ) );
                            break;
                        }
                        else
                        {
                            boolean unique = keyVal.add( paramId + IAppConstants.DASH + userRolepPojo.getAreaId() );
                            if ( !unique )
                            {
                                inActionError.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( "error.duplicate.value.area", userRolepPojo
                                                           .getAreaName() ) );
                                break;
                            }
                        }
                    }
                }
            }
        }
        else
        {
            LOGGER.info( "userMasterPojo found null. " );
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
        }
        // return inActionError;
    }
}
