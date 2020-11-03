package com.np.tele.crm.masterdata.forms;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ValidationEnum;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.UserMasterPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public final class MasterFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( MasterFormHelper.class );

    public static void resetAccessGroup( final String inMethodName, final List<AccessGroupPojo> inAccessGroup )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_ROLE_GROUP_ROW, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_CREATE_ROLE_GROUP, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inAccessGroup ) && !inAccessGroup.isEmpty() )
            {
                for ( AccessGroupPojo accessGroupPojo : inAccessGroup )
                {
                    accessGroupPojo.setEditable( false );
                    accessGroupPojo.setReadAccess( false );
                    accessGroupPojo.setCreateAccess( false );
                    accessGroupPojo.setUpdateAccess( false );
                    accessGroupPojo.setDeleteAccess( false );
                    accessGroupPojo.setRecoverAccess( false );
                }
            }
        }
    }

    public static void validateForm( final ActionErrors inErrors,
                                     final String inMethodName,
                                     final RoleGroupForm inRoleGroupForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_ROLE_GROUP_ROW, inMethodName ) )
        {
        }
        if ( StringUtils.equals( IAppConstants.METHOD_CREATE_ROLE_GROUP, inMethodName ) )
        {
            validateRoleGroup( inErrors, inRoleGroupForm.getRoleGroupName(), inRoleGroupForm.getRoleGroupDescription() );
            boolean status = uniqueRoles( inErrors, inRoleGroupForm.getAccessGroups() );
            if ( !status )
            {
                inErrors.add( "accessGroups", new ActionMessage( IPropertiesConstant.DUPLICATE_ENTRY ) );
            }
            if ( !viewAccessValidation( inErrors, inRoleGroupForm.getAccessGroups() ) )
            {
                inErrors.add( "accessGroups", new ActionMessage( IPropertiesConstant.ERROR_READACCESS_REQUIRED ) );
            }
            accessValidation( inErrors, inRoleGroupForm.getAccessGroups() );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_ROLE_GROUP, inMethodName ) )
        {
            LOGGER.info( IAppConstants.METHOD_MODIFY_ROLE_GROUP + "method called" );
            validateRoleGroup( inErrors, inRoleGroupForm.getGroupPojo().getGroupName(), inRoleGroupForm.getGroupPojo()
                    .getGroupDescription() );
            boolean status = uniqueRoles( inErrors, inRoleGroupForm.getAccessGroups() );
            if ( !status )
            {
                inErrors.add( "accessGroups", new ActionMessage( IPropertiesConstant.DUPLICATE_ENTRY ) );
            }
            if ( !viewAccessValidation( inErrors, inRoleGroupForm.getAccessGroups() ) )
            {
                inErrors.add( "accessGroups", new ActionMessage( IPropertiesConstant.ERROR_READACCESS_REQUIRED ) );
            }
            accessValidation( inErrors, inRoleGroupForm.getAccessGroups() );
        }
    }

    public static void validateForm( final ActionErrors inErrors,
                                     final String inMethodName,
                                     final MasterForm inMasterForm )
    {
        if ( StringUtils.equalsIgnoreCase( IAppConstants.METHOD_CREATE_EMAIL_SERVER, inMethodName ) )
        {
            if ( StringUtils.isEmpty( inMasterForm.getEmailServerPojo().getSubCategory().trim() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_NAME_REQUIRED ) );
            }
            if ( StringUtils.isEmpty( inMasterForm.getEmailServerPojo().getHost().trim() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_SMTP_HOST_REQUIRED ) );
            }
            if ( StringUtils.isEmpty( inMasterForm.getEmailServerPojo().getUserid().trim() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_USER_REQUIRED ) );
            }
            if ( StringUtils.isEmpty( inMasterForm.getEmailServerPojo().getFrom().trim() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_FROM_EMAILID_REQUIRED ) );
            }
            //            if ( StringUtils.isEmpty( inMasterForm.getEmailServerPojo().getReplyTo().trim() ) )
            //            {
            //                inErrors.add( IAppConstants.APP_ERROR,
            //                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_REPLYTO_EMAILID_REQUIRED ) );
            //            }
            if ( StringUtils.isEmpty( inMasterForm.getEmailServerPojo().getPassword().trim() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_PASSWORD_REQUIRED ) );
            }
            if ( inMasterForm.getEmailServerPojo().getPort() < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_SMTPPORT_REQUIRED ) );
            }
            if ( inMasterForm.getEmailServerPojo().getConnectionTimeOut() < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_CONNECTION_TIMEOUT_REQUIRED ) );
            }
            if ( inMasterForm.getEmailServerPojo().getSocketTimeOut() < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_SOCKET_TIMEOUT_REQUIRED ) );
            }
            /*if ( inMasterForm.getEmailServerPojo().getRetryValue() < 0 )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMAILSERVER_RETRY_VALUE_REQUIRED ) );
            }*/
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_OPERATION_82, inMethodName ) )
        {
            if ( ! ( inMasterForm.getPartnerId() > 0 ) )
            {
                LOGGER.info( "ERROR PARTNER ID REQUIRED" );
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_PARTNER_ID_REQUIRED ) );
            }
            if ( StringUtils.isBlank( inMasterForm.getBusinessType() ) )
            {
                LOGGER.info( "ERROR BUSINESS TYPE REQUIRED" );
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_BUSINESS_TYPE_REQUIRED ) );
            }
            if ( StringUtils.equals( inMasterForm.getBusinessType(), CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                if ( ( !StringUtils.isValidObj( inMasterForm.getBusinessType() ) )
                        || StringUtils.isEmpty( inMasterForm.getOltType() ) )
                {
                    LOGGER.info( "ERROR OLT TYPE REQUIRED" );
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_OLT_TYPE_REQUIRED ) );
                }
            }
            else if ( !StringUtils.equals( inMasterForm.getBusinessType(), CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                validateNassportData( inErrors, inMasterForm );
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CREATE_ALERT_CONFIG, inMethodName ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inMasterForm.getAlertMasterPojo(),
                                   ICRMValidationCriteriaUtil.FORM_ADMIN_CREATE_ALERTS_CONFIG, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
            if ( inErrors.isEmpty() )
            {
                if ( inMasterForm.getAlertMasterPojo().getTraiStart() < 0
                        || inMasterForm.getAlertMasterPojo().getTraiStart() > 23
                        || inMasterForm.getAlertMasterPojo().getTraiEnd() < 0
                        || inMasterForm.getAlertMasterPojo().getTraiEnd() > 23 )
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_ALERT_TRAI_NOT_SPECIFIED_CORRECTLY ) );
                }
                else if ( ( inMasterForm.getAlertMasterPojo().getMaxRetry() < 0 )
                        || ( inMasterForm.getAlertMasterPojo().getRetryTimeInterval() < 0 )
                        || ( inMasterForm.getAlertMasterPojo().getTraiStart() < 0 )
                        || ( inMasterForm.getAlertMasterPojo().getTraiEnd() < 0 ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_ALERT_EMPTY ) );
                }
            }
        }
    }

    public static void accessValidation( ActionErrors inErrors, List<AccessGroupPojo> inAccessGroup )
    {
        for ( AccessGroupPojo accessGroup : inAccessGroup )
        {
            if ( ( !accessGroup.isCreateAccess() ) && ( !accessGroup.isDeleteAccess() )
                    && ( !accessGroup.isUpdateAccess() ) && ( !accessGroup.isRecoverAccess() )
                    && ( !accessGroup.isReadAccess() ) )
            {
                inErrors.add( "accessGroups", new ActionMessage( IPropertiesConstant.ERROR_ACCESS_REQUIRED ) );
                break;
            }
        }
    }

    public static boolean viewAccessValidation( ActionErrors inErrors, List<AccessGroupPojo> inAccessGroup )
    {
        boolean returnValue = true;
        for ( AccessGroupPojo accessGroup : inAccessGroup )
        {
            if ( ( accessGroup.isCreateAccess() ) || ( accessGroup.isDeleteAccess() )
                    || ( accessGroup.isUpdateAccess() ) || ( accessGroup.isRecoverAccess() ) )
            {
                boolean readAccessStatus = accessGroup.isReadAccess();
                if ( !readAccessStatus )
                {
                    returnValue = false;
                    break;
                }
            }
        }
        return returnValue;
    }

    private static void validateRoleGroup( ActionErrors inErrors, String inRoleGroupName, String inRoleGroupDescription )
    {
        if ( StringUtils.isEmpty( inRoleGroupName ) )
        {
            inErrors.add( "roleGroupName", new ActionMessage( IPropertiesConstant.FIELDREQUIRED ) );
        }
        else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_3_50.getPattern(),
                                                  inRoleGroupName ) )
        {
            inErrors.add( "roleGroupName", new ActionMessage( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_3_50.getErrorKey(),
                                                              inRoleGroupName ) );
        }
        if ( StringUtils.isValidObj( inRoleGroupDescription ) && ( inRoleGroupDescription.length() > 128 ) )
        {
            inErrors.add( "roleGroupDescription", new ActionMessage( IPropertiesConstant.ERROR_GROUP_LENGTH,
                                                                     0,
                                                                     IAppConstants.ROLE_GROUP_MAX_LENGTH ) );
        }
    }

    public static ActionErrors validateParameterGroup( String inMethodName,
                                                       ActionErrors inErrors,
                                                       ParameterGroupForm inParameterGroupForm )
    {
        LOGGER.info( "validateParameterGroup" );
        LOGGER.info( "inMethod Name" + inMethodName );
        LOGGER.info( "group" + inParameterGroupForm.getAccessGroups() );
        long startTimeId = 0;
        long endTimeId = 0;
        UserMasterPojo userMasterPojo = CRMCacheManager.getUserMaster();
        if ( StringUtils.isValidObj( userMasterPojo ) )
        {
            startTimeId = userMasterPojo.getStartTimeId();
            endTimeId = userMasterPojo.getEndTimeId();
            if ( StringUtils.equals( IAppConstants.METHOD_CREATE_PARAMETER_GROUP, inMethodName ) )
            {
                if ( StringUtils.isEmpty( inParameterGroupForm.getParameterGroupName() ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.FIELDREQUIRED ) );
                }
                else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_3_50.getPattern(),
                                                          inParameterGroupForm.getParameterGroupName() ) )
                {
                    inErrors.add( "roleGroupName",
                                  new ActionMessage( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_3_50.getErrorKey(),
                                                     inParameterGroupForm.getParameterGroupName() ) );
                }
                if ( ( inParameterGroupForm.getParameterGroupDescription().length() > 128 ) )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_GROUP_LENGTH,
                                                                              0,
                                                                              IAppConstants.ROLE_GROUP_MAX_LENGTH ) );
                }
                if ( !inParameterGroupForm.getAccessGroups().isEmpty() )
                {
                    Set<String> keyVal = new HashSet<String>();
                    Set<Long> parameIDs = new HashSet<Long>();
                    outer: for ( AccessGroupPojo accessGroupPojo : inParameterGroupForm.getAccessGroups() )
                    {
                        accessGroupPojo
                                .setParameterValue( StringUtils.upperCase( accessGroupPojo.getParameterValue() ) );
                        long paramId = accessGroupPojo.getCrmParameter().getParameterId();
                        if ( StringUtils.isEmpty( accessGroupPojo.getParameterValue() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.PARAMETER_VALUE_REQUIRED ) );
                            break;
                        }
                        else
                        {
                            boolean unique = keyVal.add( paramId + IAppConstants.DASH
                                    + accessGroupPojo.getParameterValue() );
                            if ( !unique )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_VALUE_FOR_PARAMETER,
                                                                 accessGroupPojo.getParameterValue() ) );
                                break;
                            }
                        }
                        for ( CrmParameterPojo pojo : inParameterGroupForm.getParameterPojos() )
                        {
                            if ( pojo.getParameterId() == paramId )
                            {
                                if ( !StringUtils.compareRegularExp( pojo.getParameterPattern(),
                                                                     accessGroupPojo.getParameterValue() ) )
                                {
                                    inErrors.add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( CRMRegex.getErrorKeyByPattern( pojo
                                                          .getParameterPattern() ), pojo.getParameterName() ) );
                                    break outer;
                                }
                                if ( StringUtils.equals( ValidationEnum.SINGLE_MULTIPLICITY.getParameter(),
                                                         pojo.getParameterMultiplicity() ) )
                                {
                                    if ( !parameIDs.add( paramId ) )
                                    {
                                        inErrors.add( IAppConstants.APP_ERROR,
                                                      new ActionMessage( ValidationEnum.SINGLE_MULTIPLICITY
                                                              .getErrorKey(), pojo.getParameterName() ) );
                                        break outer;
                                    }
                                }
                            }
                        }
                        if ( paramId == startTimeId || paramId == endTimeId )
                        {
                            if ( !validateSimilarTimeParameterValue( inParameterGroupForm.getAccessGroups(),
                                                                     startTimeId, endTimeId ) )
                            {
                                LOGGER.info( "Same value find " );
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_SIMILAR_TIME_BASED_PARAMETER ) );
                                break;
                            }
                            if ( !validateTimeParameter( inParameterGroupForm.getAccessGroups(), startTimeId, endTimeId ) )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_PARAMETER_PAIR_REQUIRED ) );
                                break;
                            }
                        }
                    }
                }
            }
            if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_PARAMETER_GROUP, inMethodName ) )
            {
                if ( StringUtils.isValidObj( inParameterGroupForm.getParameterGroupDescription() )
                        && inParameterGroupForm.getParameterGroupDescription().length() > 128 )
                {
                    inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_GROUP_LENGTH,
                                                                              IAppConstants.ROLE_GROUP_MIN_LENGTH,
                                                                              IAppConstants.ROLE_GROUP_MAX_LENGTH ) );
                }
                if ( !inParameterGroupForm.getAccessGroups().isEmpty() )
                {
                    Set<String> keyVal = new HashSet<String>();
                    Set<Long> parameIDs = new HashSet<Long>();
                    outerloop: for ( AccessGroupPojo accessGroupPojo : inParameterGroupForm.getAccessGroups() )
                    {
                        accessGroupPojo
                                .setParameterValue( StringUtils.upperCase( accessGroupPojo.getParameterValue() ) );
                        long paramId = accessGroupPojo.getCrmParameter().getParameterId();
                        if ( StringUtils.isEmpty( accessGroupPojo.getParameterValue() ) )
                        {
                            inErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.PARAMETER_VALUE_REQUIRED ) );
                            break;
                        }
                        else
                        {
                            boolean unique = keyVal.add( paramId + IAppConstants.DASH
                                    + accessGroupPojo.getParameterValue() );
                            if ( !unique )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_VALUE_FOR_PARAMETER,
                                                                 accessGroupPojo.getParameterValue() ) );
                                break;
                            }
                        }
                        for ( CrmParameterPojo pojo : inParameterGroupForm.getParameterPojos() )
                        {
                            if ( pojo.getParameterId() == paramId )
                            {
                                if ( !StringUtils.compareRegularExp( pojo.getParameterPattern(),
                                                                     accessGroupPojo.getParameterValue() ) )
                                {
                                    inErrors.add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( CRMRegex.getErrorKeyByPattern( pojo
                                                          .getParameterPattern() ), pojo.getParameterName() ) );
                                    break outerloop;
                                }
                                if ( StringUtils.equals( ValidationEnum.SINGLE_MULTIPLICITY.getParameter(),
                                                         pojo.getParameterMultiplicity() ) )
                                {
                                    if ( !parameIDs.add( paramId ) )
                                    {
                                        inErrors.add( IAppConstants.APP_ERROR,
                                                      new ActionMessage( ValidationEnum.SINGLE_MULTIPLICITY
                                                              .getErrorKey(), pojo.getParameterName() ) );
                                        break outerloop;
                                    }
                                }
                            }
                        }
                        if ( paramId == startTimeId || paramId == endTimeId )
                        {
                            if ( !validateTimeParameter( inParameterGroupForm.getAccessGroups(), startTimeId, endTimeId ) )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_PARAMETER_PAIR_REQUIRED ) );
                                break;
                            }
                            else if ( !validateSimilarTimeParameterValue( inParameterGroupForm.getAccessGroups(),
                                                                          startTimeId, endTimeId ) )
                            {
                                inErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( IPropertiesConstant.ERROR_SIMILAR_TIME_BASED_PARAMETER ) );
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
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        return inErrors;
    }

    private static boolean validateTimeParameter( List<AccessGroupPojo> inAccessPojos, long startTimeId, long endTimeId )
    {
        long startTime = 0;
        long endTime = 0;
        long startTimeInactive = 0;
        long endTimeInactive = 0;
        boolean returnValue = false;
        for ( AccessGroupPojo accessPojo : inAccessPojos )
        {
            if ( accessPojo.getCrmParameter().getParameterId() == startTimeId
                    && accessPojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                startTime = accessPojo.getCrmParameter().getParameterId();
            }
            if ( accessPojo.getCrmParameter().getParameterId() == endTimeId
                    && accessPojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                endTime = accessPojo.getCrmParameter().getParameterId();
            }
            if ( accessPojo.getCrmParameter().getParameterId() == startTimeId
                    && accessPojo.getStatus().equals( CRMStatusCode.INACTIVE.getStatusCode() ) )
            {
                startTimeInactive = accessPojo.getCrmParameter().getParameterId();
            }
            if ( accessPojo.getCrmParameter().getParameterId() == endTimeId
                    && accessPojo.getStatus().equals( CRMStatusCode.INACTIVE.getStatusCode() ) )
            {
                endTimeInactive = accessPojo.getCrmParameter().getParameterId();
            }
            LOGGER.info( "Start Time parameter Id" + startTime + ":::End Time Parameter Id" + endTime );
        }
        if ( ( startTime > IAppConstants.ZERO ) && ( endTime > IAppConstants.ZERO ) )
        {
            returnValue = true;
        }
        if ( ( startTimeInactive > IAppConstants.ZERO ) && ( endTimeInactive > IAppConstants.ZERO ) )
        {
            returnValue = true;
        }
        return returnValue;
    }

    private static boolean validateSimilarTimeParameterValue( List<AccessGroupPojo> inAccessPojos,
                                                              long startTimeId,
                                                              long endTimeId )
    {
        int startTimeValue = -1;
        int endTimeValue = -1;
        boolean status = true;
        for ( AccessGroupPojo accessPojo : inAccessPojos )
        {
            if ( accessPojo.getCrmParameter().getParameterId() == startTimeId )
            {
                if ( StringUtils.isNumeric( accessPojo.getParameterValue() ) )
                {
                    LOGGER.info( "accessPojo.getParameterValue" + accessPojo.getParameterValue() );
                    startTimeValue = Integer.parseInt( accessPojo.getParameterValue() );
                }
            }
            if ( accessPojo.getCrmParameter().getParameterId() == endTimeId )
            {
                if ( StringUtils.isNumeric( accessPojo.getParameterValue() ) )
                {
                    endTimeValue = Integer.parseInt( accessPojo.getParameterValue() );
                }
            }
            LOGGER.info( "Start Time " + startTimeValue + " End Time Value :::" + endTimeValue );
        }
        if ( startTimeValue == endTimeValue )
        {
            status = false;
        }
        return status;
    }

    private static boolean uniqueRoles( final ActionErrors inErrors, final List<AccessGroupPojo> inAccessGroup )
    {
        Set<Long> roleSet = new HashSet<Long>();
        for ( AccessGroupPojo accessGroupPojo : inAccessGroup )
        {
            if ( !roleSet.add( accessGroupPojo.getCrmRoles().getRoleId() ) )
            {
                roleSet = null;
                return false;
            }
        }
        return true;
    }

    public static void validateExternalProject( ActionErrors inActionError,
                                                String inMethod,
                                                CrmProjectForm inCrmProjectForm )
    {
        if ( inMethod.equals( IAppConstants.METHOD_RGST_EXT_PROJECT )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_MODIFY_RGST_EXT_PROJECT ) )
        {
            if ( ( inCrmProjectForm.getExtProjectName() == null )
                    || ( StringUtils.isEmpty( inCrmProjectForm.getExtProjectName() ) ) )
            {
                inActionError.add( "extProjectName", new ActionMessage( "error.project.extProjectName.required" ) );
            }
            else
            {
                validateParameters( inActionError, inCrmProjectForm.getCrmParameters() );
            }
        }
    }

    private static void validateParameters( ActionErrors inActionError, List<CrmParameterPojo> inCrmParameters )
    {
        if ( StringUtils.isValidObj( inCrmParameters ) && !inCrmParameters.isEmpty() )
        {
            Set<String> paramNames = new HashSet<String>();
            for ( CrmParameterPojo paramPojo : inCrmParameters )
            {
                if ( paramPojo.getParameterLength() <= 0 )
                {
                    LOGGER.info( "Empty Parameter Length not allowed :: " + paramPojo.getParameterLength() );
                    inActionError.add( IAppConstants.APP_MESSAGE,
                                       new ActionMessage( CRMServiceCode.CRM056.getStatusCode() ) );
                    break;
                }
                if ( StringUtils.isEmpty( paramPojo.getParameterName() ) )
                {
                    LOGGER.info( "Empty row not allowed " );
                    inActionError.add( IAppConstants.APP_MESSAGE,
                                       new ActionMessage( CRMServiceCode.CRM056.getStatusCode() ) );
                    break;
                }
                else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_3_50.getPattern(),
                                                          paramPojo.getParameterName() ) )
                {
                    LOGGER.info( "Check specical charater for parameter name." );
                    inActionError.add( IAppConstants.APP_MESSAGE,
                                       new ActionMessage( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_3_50.getErrorKey(),
                                                          paramPojo.getParameterName() ) );
                    break;
                }
                if ( !paramNames.add( paramPojo.getParameterName() ) )
                {
                    LOGGER.info( "Parameter could not be same." );
                    inActionError.add( IAppConstants.APP_MESSAGE,
                                       new ActionMessage( CRMServiceCode.CRM055.getStatusCode() ) );
                    break;
                }
            }
        }
    }

    public static void resetCRMExtrProjectForm( String inMethodName, CrmProjectForm inCrmProjectForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_RGST_EXT_PROJECT_PAGE, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_EXT_PROJECT_PAGE, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_EXT_PROJECT, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_CHANGE_EXT_PROJECT_STATUS, inMethodName ) )
        {
            inCrmProjectForm.setExtProjectName( null );
            inCrmProjectForm.setProjectDesc( null );
            inCrmProjectForm.setDirectSearch( false );
            if ( StringUtils.isValidObj( inCrmProjectForm.getProjectList() )
                    && !inCrmProjectForm.getProjectList().isEmpty() )
            {
                inCrmProjectForm.getProjectList().clear();
            }
        }
        if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_RGST_EXT_PROJECT, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inCrmProjectForm.getProjectList() )
                    && !inCrmProjectForm.getProjectList().isEmpty() )
            {
                CrmParameterPojo crmParameterPojo = null;
                int size = inCrmProjectForm.getCrmParameters().size();
                List<CrmParameterPojo> crmParameterPojos = new ArrayList<CrmParameterPojo>( size );
                for ( int i = 0; i < size; i++ )
                {
                    crmParameterPojo = new CrmParameterPojo();
                    crmParameterPojo.setParameterGroup( CRMParameter.ALERT.getParameter() );
                    crmParameterPojo.setParameterGroupType( CRMParameter.TEMPLATE.getParameter() );
                    crmParameterPojos.add( crmParameterPojo );
                }
                inCrmProjectForm.setCrmParameters( crmParameterPojos );
            }
        }
    }

    private static void resetPartnerList( String inMethodName, PartnerManagementForm inPartnerManagementForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_PARTNER, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_ADD_PARTNER_PAGE, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_PARTNER, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_PARTNER_PAGE, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inPartnerManagementForm.getPartnerList() )
                    && !inPartnerManagementForm.getPartnerList().isEmpty() )
            {
                inPartnerManagementForm.getPartnerList().clear();
            }
        }
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_PARTNER_PAGE, inMethodName ) )
        {
            if ( ( StringUtils.isValidObj( inPartnerManagementForm.getProducts() ) && !inPartnerManagementForm
                    .getProducts().isEmpty() )
                    || ( StringUtils.isValidObj( inPartnerManagementForm.getPartnerTypeList() ) && !inPartnerManagementForm
                            .getPartnerTypeList().isEmpty() ) )
            {
                inPartnerManagementForm.getProducts().clear();
                inPartnerManagementForm.getPartnerTypeList().clear();
            }
        }
    }

    public static void resetPartner( String inMethodName, PartnerManagementForm inPartnerManagementForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_PARTNER, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_PARTNER, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_ADD_PARTNER_PAGE, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SEARCH_PARTNER_PAGE, inMethodName ) )
        {
            inPartnerManagementForm.setAddedInERP( null );
            inPartnerManagementForm.setBoardedDate( null );
            inPartnerManagementForm.setBoardedDateFrom( null );
            inPartnerManagementForm.setBoardedDateTo( null );
            inPartnerManagementForm.setBusinessType( null );
            inPartnerManagementForm.setChiefDesignation( null );
            inPartnerManagementForm.setChiefEmail( null );
            inPartnerManagementForm.setChiefExFirstName( null );
            inPartnerManagementForm.setChiefExLastName( null );
            inPartnerManagementForm.setChiefInitial( null );
            inPartnerManagementForm.setChiefMobile( null );
            inPartnerManagementForm.setContactFirstName( null );
            inPartnerManagementForm.setContactInitial( null );
            inPartnerManagementForm.setContactLastName( null );
            inPartnerManagementForm.setCurrentStatus( null );
            inPartnerManagementForm.setRelatedDept( null );
            inPartnerManagementForm.setPartnerAddress1( null );
            inPartnerManagementForm.setPartnerCity( null );
            inPartnerManagementForm.setPartnerEmail( null );
            inPartnerManagementForm.setPartnerERPCode( null );
            inPartnerManagementForm.setPartnerFax( null );
            inPartnerManagementForm.setPartnerMobile( null );
            inPartnerManagementForm.setPartnerName( null );
            inPartnerManagementForm.setPartnerPhone( null );
            inPartnerManagementForm.setPartnerPincode( null );
            inPartnerManagementForm.setPartnerType( null );
            inPartnerManagementForm.setPartnerState( null );
            inPartnerManagementForm.setStdCode( null );
            inPartnerManagementForm.setPartnerCode( null );
            inPartnerManagementForm.setPartnerId( 0 );
            if ( CommonValidator.isValidCollection( inPartnerManagementForm.getPartnerList() ) )
            {
                inPartnerManagementForm.getPartnerList().clear();
            }
            if ( CommonValidator.isValidCollection( inPartnerManagementForm.getProducts() ) )
            {
                for ( ContentPojo content : inPartnerManagementForm.getProducts() )
                {
                    content.setSelected( false );
                }
            }
            if ( CommonValidator.isValidCollection( inPartnerManagementForm.getPartnerTypeList() ) )
            {
                for ( ContentPojo partnerType : inPartnerManagementForm.getPartnerTypeList() )
                {
                    partnerType.setSelected( false );
                }
            }
            inPartnerManagementForm.setPartnerPojo( null );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_VIEW_PARTNER, inMethodName ) )
        {
            if ( CommonValidator.isValidCollection( inPartnerManagementForm.getProducts() ) )
            {
                for ( ContentPojo content : inPartnerManagementForm.getProducts() )
                {
                    content.setSelected( false );
                }
            }
            if ( CommonValidator.isValidCollection( inPartnerManagementForm.getPartnerTypeList() ) )
            {
                for ( ContentPojo partnerType : inPartnerManagementForm.getPartnerTypeList() )
                {
                    partnerType.setSelected( false );
                }
            }
            inPartnerManagementForm.setPartnerPojo( null );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_PARTNER, inMethodName ) )
        {
            inPartnerManagementForm.setCurrentStatus( null );
            inPartnerManagementForm.setPartnerId( 0 );
            for ( ContentPojo content : inPartnerManagementForm.getProducts() )
            {
                content.setSelected( false );
            }
            for ( ContentPojo partnerType : inPartnerManagementForm.getPartnerTypeList() )
            {
                partnerType.setSelected( false );
            }
        }
        if ( StringUtils.equals( IAppConstants.METHOD_SEARCH_PARTNER_PAGE, inMethodName ) )
        {
            inPartnerManagementForm.setCityPojoList( new ArrayList<CityPojo>() );
        }
        //        if ( StringUtils.equals( IAppConstants.METHOD_ADD_PARTNER, inMethodName )
        //                || StringUtils.equals( IAppConstants.METHOD_SEARCH_PARTNER, inMethodName ) )
        //        {
        //            for ( ContentPojo content : inPartnerManagementForm.getProducts() )
        //            {
        //                content.setSelected( false );
        //            }
        //            for ( ContentPojo partnerType : inPartnerManagementForm.getPartnerTypeList() )
        //            {
        //                partnerType.setSelected( false );
        //            }
        //        }
        //        else 
        if ( StringUtils.equals( IAppConstants.METHOD_ASSIGN_SERVICE_PARTNER, inMethodName )
                || ( StringUtils.equals( IAppConstants.METHOD_ASSIGN_SERVICE_PARTNER_PAGE, inMethodName ) ) )
        {
            resetPartner( inPartnerManagementForm );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_NP_MOBILE, inMethodName )
                || ( StringUtils.equals( IAppConstants.METHOD_ADD_NP_MOBILE_PAGE, inMethodName ) ) )
        {
            resetNPMobileList( inPartnerManagementForm );
        }
        resetPartnerList( inMethodName, inPartnerManagementForm );
    }

    private static void resetNPMobileList( PartnerManagementForm inPartnerManagementForm )
    {
        LOGGER.info( "resetNPMobileList called" );
        if ( CommonValidator.isValidCollection( inPartnerManagementForm.getCrmNpMobileList() ) )
        {
            for ( CrmNpMobilePojo pojo : inPartnerManagementForm.getCrmNpMobileList() )
            {
                pojo.setEditable( false );
            }
        }
    }

    private static void resetPartner( PartnerManagementForm inPartnerManagementForm )
    {
        LOGGER.info( "resetPartner Method called" );
        if ( StringUtils.isValidObj( inPartnerManagementForm.getCrmMappingList() )
                && ( !inPartnerManagementForm.getCrmMappingList().isEmpty() ) )
        {
            LOGGER.info( "resetPartner Method called" );
            // inPartnerManagementForm.getCrmMappingList().clear();
            for ( CrmPartnerMappingPojo pojo : inPartnerManagementForm.getCrmMappingList() )
            {
                pojo.setEditable( false );
            }
        }
    }

    public static void resetParameterGroup( String inMethodName, ParameterGroupForm inParameterGroupForm )
    {
        if ( StringUtils.equals( "searchParameterGroupPage", inMethodName )
                || StringUtils.equals( "createParameterGroupPage", inMethodName ) )
        {
            inParameterGroupForm.setParameterGroupName( null );
            inParameterGroupForm.setParameterGroupDescription( null );
            inParameterGroupForm.setParameterGroupId( 0 );
            inParameterGroupForm.setStatus( null );
            inParameterGroupForm.setGroupPojo( null );
            inParameterGroupForm.setAccessGroup( null );
            inParameterGroupForm.setGroupsPojos( null );
        }
        if ( StringUtils.equals( "createParameterGroup", inMethodName )
                || StringUtils.equals( "modifyParameterGroup", inMethodName ) )
        {
            inParameterGroupForm.setParameterGroupDescription( null );
            int size = inParameterGroupForm.getAccessGroups().size();
            List<AccessGroupPojo> accessGroupPojos = new ArrayList<AccessGroupPojo>( size );
            AccessGroupPojo accessPojo = null;
            for ( int i = 0; i < size; i++ )
            {
                accessPojo = new AccessGroupPojo();
                accessPojo.setCrmParameter( new CrmParameterPojo() );
                accessGroupPojos.add( accessPojo );
            }
            inParameterGroupForm.setAccessGroups( accessGroupPojos );
            inParameterGroupForm.setGroupsPojos( null );
        }
    }

    public static void resetRoleGroup( final String inMethodName, final RoleGroupForm inRoleGroupForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_CREATE_ROLE_GROUP_PAGE, inMethodName )
                || StringUtils.equals( "searchRoleGroupPage", inMethodName ) )
        {
            inRoleGroupForm.setRoleGroupName( null );
            inRoleGroupForm.setRoleGroupDescription( null );
            inRoleGroupForm.setRoleGroupId( 0 );
            inRoleGroupForm.setStatus( null );
            inRoleGroupForm.setAccessGroup( null );
            inRoleGroupForm.setGroupPojo( null );
            inRoleGroupForm.setGroupsPojos( null );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_CREATE_ROLE_GROUP, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_MODIFY_ROLE_GROUP, inMethodName ) )
        {
            inRoleGroupForm.setRoleGroupDescription( null );
            int size = inRoleGroupForm.getAccessGroups().size();
            List<AccessGroupPojo> accessGroupPojos = new ArrayList<AccessGroupPojo>( size );
            AccessGroupPojo accessPojo = null;
            for ( int i = 0; i < size; i++ )
            {
                accessPojo = new AccessGroupPojo();
                accessPojo.setCrmRoles( new CrmRolesPojo() );
                accessGroupPojos.add( accessPojo );
            }
            inRoleGroupForm.setAccessGroups( accessGroupPojos );
            inRoleGroupForm.setGroupsPojos( null );
        }
    }

    public static void validateNassportData( ActionErrors actionError, MasterForm inMasterForm )
    {
        if ( !StringUtils.equals( inMasterForm.getBusinessType(), CRMDisplayListConstants.BROADBAND.getCode() ) )
        {
            if ( StringUtils.isValidObj( inMasterForm.getCrmPartnerNetworkConfigPojos() )
                    && ( !inMasterForm.getCrmPartnerNetworkConfigPojos().isEmpty() ) )
            {
                for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo : inMasterForm
                        .getCrmPartnerNetworkConfigPojos() )
                {
                    if ( ! ( crmPartnerNetworkConfigPojo.getRecordId() > 0 ) )
                    {
                        if ( StringUtils.isEmpty( crmPartnerNetworkConfigPojo.getNasPortId() )
                                || StringUtils.isEmpty( crmPartnerNetworkConfigPojo.getPoolName() )
                                || StringUtils.isEmpty( crmPartnerNetworkConfigPojo.getMasterName() ) )
                        {
                            actionError
                                    .add( IAppConstants.APP_ERROR,
                                          new ActionMessage( IPropertiesConstant.ERROR_OPTION_82_NASSPORT_VALUES_REQUIRED ) );
                            break;
                        }
                    }
                }
            }
            //actionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_PARTNER_REQUIRED ) );
        }
        /* else if ( imMasterForm.getPartnerId() > 0 )
         {
             for ( CrmParameterPojo crmPojo : imMasterForm.getParamIdList() )
             {
                 if ( crmPojo.getParameterId() > 0 )
                 {
                     valid = true;
                     break;
                 }
             }
             if ( !valid )
             {
                 actionError.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_OPTION_82_STRING_REQUIRED ) );
             }
         }*/
    }

    public static void validateSmsConfiguration( ActionErrors actionError, String inMethod, MasterForm imMasterForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_SMS_CONFIGURATION, inMethod ) )
        {
            if ( StringUtils.isEmpty( imMasterForm.getSmsGatewayPojo().getSubCategory() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_SMS_SUB_CATEGORY_REQUIRED ) );
            }
            else if ( StringUtils.isEmpty( imMasterForm.getSmsGatewayPojo().getUserid() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_SMS_USERID_REQUIRED ) );
            }
            else if ( StringUtils.isEmpty( imMasterForm.getSmsGatewayPojo().getUrl() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_SMS_URL_REQUIRED ) );
            }
            else if ( StringUtils.isEmpty( imMasterForm.getSmsGatewayPojo().getPassword() ) )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_PASSWORD_REQUIRED ) );
            }
            else if ( imMasterForm.getSmsGatewayPojo().getConnectionTimeOut() == 0 )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_CONNECTION_TIME_OUT_REQUIRED ) );
            }
            else if ( imMasterForm.getSmsGatewayPojo().getSocketTimeOut() == 0 )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_SOCKET_TIME_OUT_REQUIRED ) );
            }
            /*else if ( imMasterForm.getSmsGatewayPojo().getRetryValue() == 0 )
            {
                actionError.add( IAppConstants.APP_ERROR,
                                 new ActionMessage( IPropertiesConstant.ERROR_RETRY_VALUE_REQUIRED ) );
            }*/
        }
    }

    public static void validatePartnerForm( ActionErrors inActionError,
                                            String inMethod,
                                            PartnerManagementForm inPartnerManagementForm )
    {
        if ( StringUtils.equals( inMethod, IAppConstants.METHOD_ADD_PARTNER )
                || StringUtils.equals( inMethod, IAppConstants.METHOD_MODIFY_PARTNER ) )
        {
            if ( StringUtils.isNotBlank( inPartnerManagementForm.getPartnerState() ) )
            {
                inPartnerManagementForm.setCityPojoList( GISUtils.getActiveCities( IAppConstants.COUNTRY_INDIA,
                                                                                   inPartnerManagementForm
                                                                                           .getPartnerState() ) );
            }
            if ( inPartnerManagementForm.getPartnerId() == 0 )
            {
                if ( StringUtils.isValidObj( inPartnerManagementForm.getProducts() ) )
                {
                    boolean valid = false;
                    for ( ContentPojo businessType : inPartnerManagementForm.getProducts() )
                    {
                        if ( businessType.isSelected() )
                        {
                            valid = true;
                            break;
                        }
                    }
                    if ( !valid )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_PM_BUSINESSTYPE ) );
                    }
                }
                if ( StringUtils.isValidObj( inPartnerManagementForm.getPartnerTypeList() ) )
                {
                    boolean validPartnerType = false;
                    for ( ContentPojo partnerType : inPartnerManagementForm.getPartnerTypeList() )
                    {
                        if ( partnerType.isSelected() )
                        {
                            validPartnerType = true;
                            break;
                        }
                    }
                    if ( !validPartnerType )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNERTYPE ) );
                    }
                }
                // Don't add else
                if ( StringUtils.isEmpty( inPartnerManagementForm.getBoardedDate() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_BOARDEDDATE ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNERNAME ) );
                /*else if ( !StringUtils.isAlphaSpace( inPartnerManagementForm.getPartnerName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNERNAME_CHARACTER ) );*/
                else if ( !StringUtils.isValidLength( inPartnerManagementForm.getPartnerName(), 3, 128 ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNERNAME_LENGTH ) );
                else if ( inPartnerManagementForm.getChiefInitial().equals( "Select" ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFINITIAL ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getChiefExFirstName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFEXFIRSTNAME ) );
                else if ( !StringUtils.isAlpha( inPartnerManagementForm.getChiefExFirstName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFEXFIRSTNAME_CHARACTER ) );
                else if ( !StringUtils.isValidLength( inPartnerManagementForm.getChiefExFirstName(), 1, 50 ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFEXFIRSTNAME_LENGTH ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getChiefExLastName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFEXLASTNAME ) );
                else if ( !StringUtils.isAlpha( inPartnerManagementForm.getChiefExLastName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFEXLASTNAME_CHARACTER ) );
                else if ( !StringUtils.isValidLength( inPartnerManagementForm.getChiefExLastName(), 3, 50 ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFEXLASTNAME_LENGTH ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getChiefDesignation() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFDESIGNATION ) );
                else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_DASH_OTHERS_3_50
                        .getPattern(), inPartnerManagementForm.getChiefDesignation() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_DASH_OTHERS_3_50
                                               .getErrorKey(), "Designation" ) );
                //                else if ( !StringUtils.isValidLength( inPartnerManagementForm.getChiefDesignation(), 3, 20 ) )
                //                    inActionError.add( IAppConstants.APP_ERROR,
                //                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFDESIGNATION_LENGTH ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getChiefMobile() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFE_MOBILE ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getChiefEmail() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFE_EMAIL ) );
                else if ( !StringUtils.isValidEmailID( inPartnerManagementForm.getChiefEmail() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CHIEFE_EMAIL_INVALID ) );
                else if ( StringUtils.equals( "nextraDepart", "0" ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_RELATED_DEPART ) );
                else if ( StringUtils.equals( "contactInitial", "0" ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_INITIAL ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getContactFirstName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_FIRST_NAME ) );
                else if ( !StringUtils.isAlpha( inPartnerManagementForm.getContactFirstName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_FIRST_NAME_CHARACTER ) );
                else if ( !StringUtils.isValidLength( inPartnerManagementForm.getContactFirstName(), 1, 50 ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_FIRST_NAME_LENGTH ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getContactLastName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_LAST_NAME ) );
                else if ( !StringUtils.isAlpha( inPartnerManagementForm.getContactLastName() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_LAST_NAME_CHARACTER ) );
                else if ( !StringUtils.isValidLength( inPartnerManagementForm.getContactLastName(), 3, 50 ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CONTACT_LAST_NAME_LENGTH ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerEmail() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_EMAIL ) );
                else if ( !StringUtils.isValidEmailID( inPartnerManagementForm.getPartnerEmail() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_EMAIL_INVALID ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerPhone() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_PHONE ) );
                else if ( !StringUtils.isNumeric( inPartnerManagementForm.getPartnerPhone() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_PHONE_INVALID ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerMobile() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_MOBILE ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerFax() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_FAX ) );
                else if ( !StringUtils.isNumeric( inPartnerManagementForm.getPartnerFax() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_FAX_INVALID ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerAddress1() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_ADDRESS1 ) );
                else if ( !StringUtils.isEmpty( inPartnerManagementForm.getPartnerAddress1() )
                        && ( inPartnerManagementForm.getPartnerAddress1().length() > 255 ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_ADDRESS_LIMIT ) );
                }
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerState() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_STATE ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerCity() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_CITY ) );
                else if ( inPartnerManagementForm.getPartnerCity().equals( "Please select" ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_CITY ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getPartnerPincode() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_PINCODE ) );
                else if ( !StringUtils.isValidPinCode( inPartnerManagementForm.getPartnerPincode() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_PINCODE_INVALID ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getCurrentStatus() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_CURRENT_STATUS ) );
                /*else if ( StringUtils.isEmpty( inPartnerManagementForm.getAddedInERP() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_ADDED_IN_ERP ) );
                else if ( StringUtils.isEmpty( inPartnerManagementForm.getAddedInERP() ) )
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_ERP_CODE ) );*/
            }
        }
        /*else if ( StringUtils.equals( inMethod, IAppConstants.METHOD_SEARCH_PARTNER ) )
        {
            if ( StringUtils.isNotEmpty( inPartnerManagementForm.getBoardedDateFrom() ) )
            {
                if ( StringUtils.isEmpty( inPartnerManagementForm.getBoardedDateTo() ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( "error.pm.boardedDateTo" ) );
                    inMapping.findForward( IActionForwardConstant.SEARCH_PARTNER );
                }
            }
        }*/
    }

    public static ActionMessages validateNPMobile( ActionMessages inErrors,
                                                   PartnerManagementForm inPartnerManagementForm )
    {
        LOGGER.info( "METHOD_ADD_NP_MOBILE" );
        Set<Long> crmNpMobilePojos = new HashSet<Long>();
        if ( CommonValidator.isValidCollection( inPartnerManagementForm.getCrmNpMobileList() ) )
        {
            for ( CrmNpMobilePojo pojo : inPartnerManagementForm.getCrmNpMobileList() )
            {
                LOGGER.info( "METHOD_ADD_NP_MOBILE ::" + pojo.getMobileNo() );
                if ( pojo.getMobileNo() > 0 )
                {
                    String mobileNum = String.valueOf( pojo.getMobileNo() );
                    String startNos = ValidationUtil.validateInputMobile( mobileNum );
                    if ( mobileNum.length() != FileHeaderConstants.LMS2.getSize() )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_MOBILE_LENGTH ) );
                        break;
                    }
                    else if ( !StringUtils.isEmpty( startNos ) )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH, startNos ) );
                        break;
                    }
                    else if ( !crmNpMobilePojos.add( pojo.getMobileNo() ) )
                    {
                        inErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                         pojo.getMobileNo(),
                                                         "Network Partner" ) );
                        break;
                    }
                }
                else
                {
                    inErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_MOBILE ) );
                    break;
                }
            }
        }
        return inErrors;
    }

    public static void resetMasterData( String inMethodName, MasterForm inMasterForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_OPTION82_PAGE, inMethodName ) )
        {
            inMasterForm.setShowDivValue( null );
            inMasterForm.setCrmPartnerNetworkConfigPojos( null );
            inMasterForm.setBusinessType( null );
            inMasterForm.setOltType( null );
            inMasterForm.setPartnerId( 0L );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_OPERATION_82, inMethodName ) )
        {
            if ( !StringUtils.equals( inMasterForm.getBusinessType(), CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                inMasterForm.setOltType( null );
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SEARCH_SMS_GATEWAY, inMethodName ) )
        {
            //this.setSmsAlias( "" );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SMS_CONFIGURATION, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inMasterForm.getSmsGatewayPojo() ) )
            {
                inMasterForm.getSmsGatewayPojo().setEnable( false );
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CREATE_EMAIL_SERVER, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inMasterForm.getEmailServerPojo() ) )
            {
                inMasterForm.getEmailServerPojo().setAuth( false );
                inMasterForm.getEmailServerPojo().setEnable( false );
            }
        }
    }
}
