package com.np.tele.crm.usrmngmnt.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.masterdata.bm.IAppDataManager;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUserAssociationPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.UserMasterPojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.service.client.ValidationMasterPojo;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class LoginAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( LoginAction.class );
    private IUserBMngr          usermngmntbm;
    private IAppDataManager     appDataManager;
    private ICrmConfigManager   crmConfigManager;
    private IMasterBMngr        masterDataBm;

    public IUserBMngr getUsermngmntbm()
    {
        return usermngmntbm;
    }

    public void setUsermngmntbm( IUserBMngr usermngmntbm )
    {
        this.usermngmntbm = usermngmntbm;
    }

    public IAppDataManager getAppDataManager()
    {
        return appDataManager;
    }

    public void setAppDataManager( IAppDataManager inAppDataManager )
    {
        appDataManager = inAppDataManager;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr inMasterDataBm )
    {
        masterDataBm = inMasterDataBm;
    }

    @Override
    protected ActionForward unspecified( ActionMapping inMapping,
                                         ActionForm inForm,
                                         HttpServletRequest inRequest,
                                         HttpServletResponse inResponse )
        throws Exception
    {
        return inMapping.findForward( IActionForwardConstant.LOGIN );
    }

    public ActionForward loginAuthentication( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.LOGIN;
        LoginForm loginForm = (LoginForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        boolean process = false;
        boolean ipBasedmsgStatus = false;
        boolean timeBasedMsgStatus = false;
        boolean validateProcess = true;
        boolean ipFound = false;
        try
        {
            CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().loginAuthentication( loginForm.getUserId(),
                                                                                         loginForm.getPassword() );
            if ( null != crmUserDetailsDto )
            {
                String statusCode = crmUserDetailsDto.getStatusCode();
                String requestedIP = request.getRemoteAddr();
                int startTime = 0;
                int endTime = 0;
                int finalStartTime = 0;
                int finalEndTime = 0;
                long allowedIPId = 0;
                long startTimeId = 0;
                long endTimeId = 0;
                long waiverLimitId = 0;
                List<String> iPList = new ArrayList<String>();
                List<Integer> startTimeList = new ArrayList<Integer>();
                List<Integer> endTimeList = new ArrayList<Integer>();
                Date currentDate = new Date();
                DateFormat df = new SimpleDateFormat( "H" );
                int LoginTime = Integer.parseInt( df.format( currentDate ) );
                LOGGER.info( "requestedIP" + requestedIP + "currenthour........" + LoginTime );
                if ( null != statusCode )
                {
                    LOGGER.info( "Response Status code " + statusCode );
                    if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                    {
                        UserMasterPojo userMasterPojo = CRMCacheManager.getUserMaster();
                        if ( StringUtils.isValidObj( userMasterPojo ) )
                        {
                            allowedIPId = userMasterPojo.getAllowedIpId();
                            startTimeId = userMasterPojo.getStartTimeId();
                            endTimeId = userMasterPojo.getEndTimeId();
                            waiverLimitId = userMasterPojo.getWaiverLimitId();
                        }
                        forwardPage = IActionForwardConstant.MY_ACCOUNT;
                        process = true;
                        List<UserRolesPojo> userRolePojoList = crmUserDetailsDto.getUserRolesPojos();
                        for ( UserRolesPojo userRolesPojo : userRolePojoList )
                        {
                            if ( StringUtils.isValidObj( userRolesPojo.getCrmParameter() ) )
                            {
                                LOGGER.info( "value of crmpparameter" + userRolesPojo.getCrmParameter()
                                        + "and parameter value " + userRolesPojo.getParameterValue() );
                                if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                         userRolesPojo.getStatus() ) )
                                {
                                    if ( allowedIPId == userRolesPojo.getCrmParameter().getParameterId() )
                                    {
                                        LOGGER.info( "IP ready to  add in list" + userRolesPojo.getParameterValue() );
                                        iPList.add( userRolesPojo.getParameterValue() );
                                    }
                                    else if ( startTimeId == userRolesPojo.getCrmParameter().getParameterId() )
                                    {
                                        LOGGER.info( "Start Time ready to add in start time list"
                                                + userRolesPojo.getParameterValue() );
                                        startTimeList.add( Integer.parseInt( userRolesPojo.getParameterValue() ) );
                                    }
                                    else if ( waiverLimitId == userRolesPojo.getCrmParameter().getParameterId() )
                                    {
                                        LOGGER.info( "Waiver Limit amount add" + userRolesPojo.getParameterValue() );
                                        crmUserDetailsDto.setWaiverLimitAmmount( userRolesPojo.getParameterValue() );
                                    }
                                    if ( endTimeId == userRolesPojo.getCrmParameter().getParameterId() )
                                    {
                                        LOGGER.info( "end Time ready to add in end time list"
                                                + userRolesPojo.getParameterValue() );
                                        endTimeList.add( Integer.parseInt( userRolesPojo.getParameterValue() ) );
                                    }
                                }
                            }
                            else if ( StringUtils.isValidObj( userRolesPojo.getGroups() ) )
                            {
                                if ( CRMParameter.PARAMETER_GROUP.getParameter().equals( userRolesPojo.getGroups()
                                                                                                 .getGroupType() )
                                        && ( CRMStatusCode.ACTIVE.getStatusCode().equals( userRolesPojo.getStatus() ) ) )
                                {
                                    LOGGER.info( "In Group Type Parameter and Size of access group Pojo"
                                            + userRolesPojo.getGroups().getAccessGroups().size() );
                                    for ( AccessGroupPojo accessGroupPojo : userRolesPojo.getGroups().getAccessGroups() )
                                    {
                                        LOGGER.info( "Parameter Name "
                                                + accessGroupPojo.getCrmParameter().getParameterName() );
                                        if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                                 accessGroupPojo.getStatus() ) )
                                        {
                                            if ( allowedIPId == accessGroupPojo.getCrmParameter().getParameterId() )
                                            {
                                                iPList.add( accessGroupPojo.getParameterValue() );
                                            }
                                            else if ( startTimeId == accessGroupPojo.getCrmParameter().getParameterId() )
                                            {
                                                startTimeList.add( Integer.parseInt( accessGroupPojo
                                                        .getParameterValue() ) );
                                            }
                                            else if ( endTimeId == accessGroupPojo.getCrmParameter().getParameterId() )
                                            {
                                                endTimeList
                                                        .add( Integer.parseInt( accessGroupPojo.getParameterValue() ) );
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        LOGGER.info( "IPLIst size" + iPList.size() );
                        if ( iPList.isEmpty() || ( !iPList.isEmpty() && iPList.contains( requestedIP ) ) )
                        {
                            LOGGER.info( "IP matched  " + requestedIP );
                            validateProcess = true;
                            ipBasedmsgStatus = false;
                            ipFound = true;
                        }
                        else
                        {
                            LOGGER.info( "IP not matched  " );
                            validateProcess = false;
                            ipBasedmsgStatus = true;
                            ipFound = false;
                        }
                        if ( ( ipFound ) && ( !endTimeList.isEmpty() ) && ( !startTimeList.isEmpty() ) )
                        {
                            for ( int i = 0; i < endTimeList.size(); i++ )
                            {
                                endTime = endTimeList.get( i );
                                startTime = startTimeList.get( i );
                                if ( ( finalStartTime == 0 && startTime > 0 ) || finalStartTime > startTime )
                                {
                                    finalStartTime = startTime;
                                }
                                if ( finalEndTime < endTime )
                                {
                                    finalEndTime = endTime;
                                }
                                if ( endTime > startTime )
                                {
                                    LOGGER.info( "IN " + endTime + ">" + startTime );
                                    LOGGER.info( ( LoginTime >= startTime ) && ( LoginTime < endTime ) );
                                    if ( ( LoginTime >= startTime ) && ( LoginTime < endTime ) )
                                    {
                                        validateProcess = true;
                                        timeBasedMsgStatus = false;
                                    }
                                    else
                                    {
                                        validateProcess = false;
                                        timeBasedMsgStatus = true;
                                    }
                                }
                                else
                                {
                                    LOGGER.info( "IN startTime > endTime  "
                                            + ( ( ( startTime > LoginTime ) && ( LoginTime <= IAppConstants.LOGIN_MAX_TIME ) ) && ( ( endTime > LoginTime ) && ( LoginTime >= IAppConstants.LOGIN_MIN_TIME ) ) ) );
                                    if ( ( ( startTime > LoginTime ) && ( LoginTime <= IAppConstants.LOGIN_MAX_TIME ) )
                                            && ( ( endTime > LoginTime ) && ( LoginTime >= IAppConstants.LOGIN_MIN_TIME ) ) )
                                    {
                                        validateProcess = true;
                                        timeBasedMsgStatus = false;
                                    }
                                    else
                                    {
                                        validateProcess = false;
                                        timeBasedMsgStatus = true;
                                    }
                                }
                                if ( validateProcess )
                                {
                                    break;
                                }
                            }
                        }
                        if ( !validateProcess )
                        {
                            if ( ipBasedmsgStatus )
                            {
                                errors.add( IAppConstants.APP_ERROR,
                                            new ActionMessage( IPropertiesConstant.ERROR_IP_RESTRICT ) );
                                process = false;
                            }
                            else if ( timeBasedMsgStatus )
                            {
                                DateFormat sdf = new SimpleDateFormat( "hh a" );
                                errors.add( IAppConstants.APP_ERROR,
                                            new ActionMessage( IPropertiesConstant.ERROR_TIME_RESTRICT, sdf.format( df
                                                    .parse( finalStartTime + "" ) ), sdf.format( df.parse( finalEndTime
                                                    + "" ) ) ) );
                                process = false;
                            }
                            forwardPage = IActionForwardConstant.LOGIN;
                        }
                    }
                    else if ( CRMServiceCode.CRM012.getStatusCode().equals( statusCode ) )
                    {
                        LOGGER.info( "New user redirect to change password page." );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.FIRST_TIME_LOGIN ) );
                        forwardPage = IActionForwardConstant.CHANGE_PASSWORD;
                        process = true;
                    }
                    else if ( CRMServiceCode.CRM008.getStatusCode().equals( statusCode ) )
                    {
                        UserMasterPojo userMasterPojo = CRMCacheManager.getUserMaster();
                        if ( StringUtils.isValidObj( userMasterPojo ) )
                        {
                            LOGGER.info( "Status Desc" + CRMServiceCode.CRM008.getStatusDesc() );
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( statusCode, userMasterPojo.getLockDuration() ) );
                            process = false;
                        }
                        else
                        {
                            LOGGER.info( "userMasterPojo found null. " );
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                            process = false;
                        }
                    }
                    else
                    {
                        LOGGER.info( "Status Desc " + crmUserDetailsDto.getStatusDesc() );
                        errors.add( "message", new ActionMessage( statusCode ) );
                        process = false;
                    }
                    if ( process )
                    {
                        invalidateOtherUser( request, crmUserDetailsDto.getCrmUserDetailsPojo().getUserId() );
                        HttpSession session = request.getSession( true );
                        CrmuserDetailsDto crmuserDetailsDto = getUsermngmntbm()
                                .getCustomerAssociations( crmUserDetailsDto.getCrmUserDetailsPojo().getUserId() );
                        LOGGER.info( "CrmUserAssociationPojos Size : "
                                + crmuserDetailsDto.getCrmUserAssociationPojos().size() );
                        if ( CommonValidator.isValidCollection( crmuserDetailsDto.getCrmUserAssociationPojos() ) )
                        {
                            List<String> serviceNameList = new ArrayList<String>();
                            List<String> partnerList = new ArrayList<String>();
                            for ( CrmUserAssociationPojo crmUserAssociationPojo : crmuserDetailsDto
                                    .getCrmUserAssociationPojos() )
                            {
                                if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                         crmUserAssociationPojo.getStatus() ) )
                                {
                                    if ( StringUtils.equals( "SN", crmUserAssociationPojo.getAssociationType() ) )
                                    {
                                        serviceNameList.add( crmUserAssociationPojo.getAssociatedValue() );
                                    }
                                    else if ( StringUtils.equals( "PT", crmUserAssociationPojo.getAssociationType() ) )
                                    {
                                        partnerList.add( crmUserAssociationPojo.getAssociatedValue() );
                                    }
                                }
                            }
                            crmUserDetailsDto.getUserAssociatedServices().addAll( serviceNameList );
                            crmUserDetailsDto.getUserAssociatedPartners().addAll( partnerList );
                        }
                        session.setAttribute( IAppConstants.CRM_USER_OBJECT, crmUserDetailsDto );
                        session.setAttribute( IAppConstants.MOBILE_NUMBER_REGEX, getMobileValidationRegex() );
                        session.setAttribute( IAppConstants.MOBILE_NUMBER_MESSAGE, getMobileValidationMessage() );
                        if ( null != crmUserDetailsDto.getCrmUserDetailsPojo() )
                        {
                            session.setAttribute( IAppConstants.CRM_USER_POJO,
                                                  crmUserDetailsDto.getCrmUserDetailsPojo() );
                            LOGGER.info( "Password in session ......"
                                    + crmUserDetailsDto.getCrmUserDetailsPojo().getPassword() );
                            setFBForSession( crmUserDetailsDto.getCrmUserDetailsPojo(), session,
                                             CRMOperationStages.CUSTOMER_CARE.getCode() );
                        }
                        if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                        {
                            Map<String, Boolean> rolesPojos = new TreeMap<String, Boolean>();
                            if ( crmUserDetailsDto.getCrmUserDetailsPojo().getHierarchy() > 0 )
                            {
                                rolesPojos.put( "ALL", true );
                                rolesPojos.putAll( CRMCacheManager.getCrmRoles() );
                            }
                            else
                            {
                                rolesPojos.putAll( processRoles( crmUserDetailsDto.getUserRolesPojos() ) );
                            }
                            RolesPojo rolesPojo = new RolesPojo();
                            rolesPojo.setRolesPojos( rolesPojos );
                            session.setAttribute( IAppConstants.CRM_ROLES, rolesPojo );
                            List<ContentPojo> pojos = new ArrayList<ContentPojo>();
                            if ( rolesPojo.getRolesPojos().containsKey( "ALL" ) )
                            {
                                pojos.addAll( CRMUtils.getcustomerProfileSearchList( null, true ) );
                            }
                            else
                            {
                                if ( StringUtils.equals( rolesPojo.getAvailable( "view_ina" ), "true" ) )
                                {
                                    pojos.addAll( CRMUtils.getcustomerProfileSearchList( CRMRequestType.INA
                                            .getRequestCode(), true ) );
                                }
                                if ( StringUtils.equals( rolesPojo.getAvailable( "view_qrc" ), "true" ) )
                                {
                                    pojos.addAll( CRMUtils.getcustomerProfileSearchList( CRMRequestType.QRC
                                            .getRequestCode(), true ) );
                                }
                                if ( StringUtils.equals( rolesPojo.getAvailable( "view_lead" ), "true" ) )
                                {
                                    pojos.addAll( CRMUtils.getcustomerProfileSearchList( CustomerProfile.LEAD_ID
                                            .getModule(), true ) );
                                }
                                Object[] newPojos = pojos.toArray();
                                for ( Object removeDuplicates : newPojos )
                                {
                                    if ( pojos.indexOf( removeDuplicates ) != pojos.lastIndexOf( removeDuplicates ) )
                                    {
                                        pojos.remove( pojos.lastIndexOf( removeDuplicates ) );
                                    }
                                }
                            }
                            List<ContentPojo> allowedModeList = CRMUtils.getAllowedList( pojos );
                            session.setAttribute( IAppConstants.CUSTOMER_PROFILE_LIST, allowedModeList );
                        }
                    }
                }
                else
                {
                    LOGGER.info( "status code found null" );
                    errors.add( "errorMessage", new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
            }
            else
            {
                LOGGER.info( "Service Unavailable ." );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "In catch block error occured", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            forwardPage = IActionForwardConstant.LOGIN;
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    private void setFBForSession( CrmUserPojo inCrmUserPojo, HttpSession inSession, String inBin )
    {
        List<String> functionalBin = new ArrayList<String>();
        if ( StringUtils.isNotEmpty( inCrmUserPojo.getFunctionalBin() ) )
        {
            LOGGER.info( "Functional Bin  ::::::::::" + inCrmUserPojo.getFunctionalBin() );
            functionalBin.addAll( Arrays.asList( StringUtils.split( inCrmUserPojo.getFunctionalBin(), "," ) ) );
            LOGGER.info( "Size of Functionl Bin :::: " + functionalBin.size() );
            List<CrmRcaReasonPojo> functionalBinCache = CRMCacheManager.getFunctionalBins();
            for ( String bin : functionalBin )
            {
                inner: for ( CrmRcaReasonPojo crmRcaReasonPojo : functionalBinCache )
                {
                    if ( StringUtils.equals( String.valueOf( crmRcaReasonPojo.getCategoryId() ), bin )
                            && StringUtils.equals( crmRcaReasonPojo.getValueAlias(), inBin ) )
                    {
                        LOGGER.info( "Category ID:: " + crmRcaReasonPojo.getCategoryId() );
                        LOGGER.info( "User Bin ID:: " + bin );
                        inSession.setAttribute( inBin, inBin );
                        break inner;
                    }
                }
            }
        }
    }

    private void invalidateOtherUser( HttpServletRequest inRequest, String inUserId )
    {
        HttpSession session = inRequest.getSession( false );
        if ( StringUtils.isValidObj( session ) )
        {
            CrmuserDetailsDto userDetails = (CrmuserDetailsDto) session.getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( StringUtils.isValidObj( userDetails ) )
            {
                LOGGER.info( "differentUser : userDetails.getUserID():"
                        + userDetails.getCrmUserDetailsPojo().getUserId() );
                LOGGER.info( "differentUser : loginForm.getUserId() :" + inUserId );
                if ( StringUtils.isValidObj( userDetails.getCrmUserDetailsPojo() )
                        && StringUtils.isNotBlank( userDetails.getCrmUserDetailsPojo().getUserId() )
                        && StringUtils.isNotBlank( inUserId )
                        && !StringUtils.equals( userDetails.getCrmUserDetailsPojo().getUserId(), inUserId ) )
                {
                    session.invalidate();
                }
            }
        }
    }

    private Map<String, Boolean> processRoles( List<UserRolesPojo> inUserRolesPojos )
    {
        Map<String, Boolean> rolePojos = new TreeMap<String, Boolean>();
        for ( UserRolesPojo userRolesPojo : inUserRolesPojos )
        {
            if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), userRolesPojo.getStatus() ) )
            {
                if ( StringUtils.isValidObj( userRolesPojo.getCrmRoles() ) )
                {
                    CRMCacheManager.processCRMRoles( rolePojos, userRolesPojo );
                }
                if ( StringUtils.isValidObj( userRolesPojo.getGroups() ) )
                {
                    GroupsPojo groupsPojo = userRolesPojo.getGroups();
                    if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), groupsPojo.getStatus() ) )
                    {
                        String groupType = groupsPojo.getGroupType();
                        if ( StringUtils.equals( CRMParameter.ROLES_GROUP.getParameter(), groupType ) )
                        {
                            List<AccessGroupPojo> accessGroupPojos = groupsPojo.getAccessGroups();
                            for ( AccessGroupPojo accessGroupPojo : accessGroupPojos )
                            {
                                if ( StringUtils.isValidObj( accessGroupPojo.getCrmRoles() )
                                        && StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                               accessGroupPojo.getStatus() ) )
                                {
                                    CRMCacheManager.processCRMRoles( rolePojos, accessGroupPojo );
                                }
                            }
                        }
                    }
                }
            }
        }
        return rolePojos;
    }

    public ActionForward logout( ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.LOGIN;
        try
        {
            request.removeAttribute( IAppConstants.CRM_USER_OBJECT );
            request.removeAttribute( IAppConstants.CRM_USER_POJO );
            request.getSession().invalidate();
        }
        catch ( Exception e )
        {
            LOGGER.error( "In catch block error occured", e );
        }
        return mapping.findForward( forwardPage );
    }

    public ActionForward redirectHome( ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response )
    {
        return mapping.findForward( IActionForwardConstant.HOME_PAGE );
    }

    private String getMobileValidationRegex()
    {
        ValidationMasterPojo validationMasterPojo = CRMCacheManager.getValidationMaster();
        String startWithNos = StringUtils.replace( validationMasterPojo.getMobileNoStartsWith(), ",", "|" );
        String validationPattern = "(" + startWithNos + ")[0-9]{9}$";
        return validationPattern;
    }

    private String getMobileValidationMessage()
    {
        ValidationMasterPojo validationMasterPojo = CRMCacheManager.getValidationMaster();
        String startWithNos = StringUtils.replaceLast( validationMasterPojo.getMobileNoStartsWith(), ",", " or " );
        return "should be of 10 digits long and starts with " + startWithNos;
    }
}
