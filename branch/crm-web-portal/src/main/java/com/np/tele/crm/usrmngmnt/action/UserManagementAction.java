package com.np.tele.crm.usrmngmnt.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmUserAssociationPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.AreaMappingPojo;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.usrmngmnt.forms.LoginFormHelper;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class UserManagementAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( UserManagementAction.class );
    private IUserBMngr          usermngmntbm;
    private IMasterBMngr        masterDataBm;

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public IUserBMngr getUsermngmntbm()
    {
        return usermngmntbm;
    }

    public void setUsermngmntbm( IUserBMngr usermngmntbm )
    {
        this.usermngmntbm = usermngmntbm;
    }

    public ActionForward searchUserForward( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {
        LoginForm loginForm = (LoginForm) form;
        loginForm.setStatusList( CRMUtils.getFullUserStatusList() );
        loginForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
        return mapping.findForward( IActionForwardConstant.SEARCH_USER_PAGE );
    }

    public ActionForward searchUser( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.SEARCH_USER_PAGE;
        LoginForm loginForm = (LoginForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        HttpSession httpSession = request.getSession( false );
        httpSession.removeAttribute( IAppConstants.OLD_USER_OBJECT );
        List<ContentPojo> statusList = CRMUtils.getFullUserStatusList();
        loginForm.setStatusList( statusList );
        loginForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
        try
        {
            CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().searchUser( loginForm );
            if ( null != crmUserDetailsDto )
            {
                String statusCode = crmUserDetailsDto.getStatusCode();
                if ( null != statusCode )
                {
                    LOGGER.info( "Response status code::" + statusCode + "Response status desc::"
                            + crmUserDetailsDto.getStatusDesc() );
                    if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                    {
                        List<CrmUserPojo> crmUserList = crmUserDetailsDto.getCrmUserDetailsPojoList();
                        if ( !crmUserList.isEmpty() )
                        {
                            loginForm.setSearchUserList( crmUserList );
                        }
                        else
                        {
                            LOGGER.info( "Empty search list" );
                            errors.add( "noRecordFound", new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
            }
            else
            {
                LOGGER.info( "Get Value NULL for crmuserdetailsdto from business manager" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
            if ( ! ( crmUserDetailsDto.getCrmUserDetailsPojoList().isEmpty() ) )
            {
                HttpSession session = request.getSession( false );
                SortingComparator<CrmUserPojo> sortComparator = new SortingComparator<CrmUserPojo>( "firstName" );
                Collections.sort( crmUserDetailsDto.getCrmUserDetailsPojoList(), sortComparator );
                session.setAttribute( IAppConstants.CRM_USER_SEARCH_LIST, crmUserDetailsDto.getCrmUserDetailsPojoList() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in register action", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward modifyUserPage( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        ConfigDto configDto = null;
        try
        {
            HttpSession httpSession = request.getSession( false );
            List<CrmUserPojo> crmUserPojoList = (List<CrmUserPojo>) httpSession
                    .getAttribute( IAppConstants.CRM_USER_SEARCH_LIST );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            LOGGER.info( "CrmUserPojo List size" + crmUserPojoList.size() );
            LoginForm userForm = (LoginForm) form;
            crmUserPojo.setRecordId( userForm.getRecordID() );
            userForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
            userForm.setProductList( CRMUtils.getProducts() );
            userForm.setPartnerList( CRMCacheManager.getActivePartnerList() );
            int pojoIndex = crmUserPojoList.indexOf( crmUserPojo );
            if ( pojoIndex >= 0 )
            {
                userForm.setCrmUserPojo( crmUserPojoList.get( pojoIndex ) );
                if ( StringUtils.isValidObj( userForm.getCrmUserPojo().getFunctionalBin() ) )
                {
                    userForm.setFunctionalBinArray( StringUtils.commaStringToArray( userForm.getCrmUserPojo()
                            .getFunctionalBin() ) );
                }
                httpSession.setAttribute( IAppConstants.OLD_USER_OBJECT, userForm.getCrmUserPojo() );
            }
            LOGGER.info( "found pojo index " + pojoIndex );
            configDto = getUsermngmntbm().getActivityLogs( userForm );
            setUserAssociationToForm( userForm );
            httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
            if ( configDto.getAuditLogPojos().size() > 0 )
            {
                httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
            }
            LOGGER.info( "List of AuditLog Size ::" + configDto.getAuditLogPojos().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in register action", ex );
        }
        return mapping.findForward( IActionForwardConstant.CREATE_USER );
    }

    private void setUserAssociationToForm( LoginForm inUserForm )
    {
        CrmuserDetailsDto crmuserDetailsDto;
        crmuserDetailsDto = getUsermngmntbm().getCustomerAssociations( inUserForm.getCrmUserPojo().getUserId() );
        if ( CommonValidator.isValidCollection( crmuserDetailsDto.getCrmUserAssociationPojos() ) )
        {
            StringBuffer serviceNames = new StringBuffer();
            StringBuffer partners = new StringBuffer();
            List<String> serviceNameList = new ArrayList<String>();
            List<String> partnerList = new ArrayList<String>();
            for ( CrmUserAssociationPojo crmUserAssociationPojo : crmuserDetailsDto.getCrmUserAssociationPojos() )
            {
                if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), crmUserAssociationPojo.getStatus() ) )
                {
                    if ( StringUtils.equals( "SN", crmUserAssociationPojo.getAssociationType() ) )
                    {
                        serviceNameList.add( crmUserAssociationPojo.getAssociatedValue() );
                        serviceNames.append( crmUserAssociationPojo.getAssociatedValue() ).append( IAppConstants.COMMA );
                    }
                    else if ( StringUtils.equals( "PT", crmUserAssociationPojo.getAssociationType() ) )
                    {
                        partnerList.add( crmUserAssociationPojo.getAssociatedValue() );
                        partners.append( crmUserAssociationPojo.getAssociatedValue() ).append( IAppConstants.COMMA );
                    }
                }
            }
            inUserForm.setServiceNames( StringUtils.isNotBlank( serviceNames ) ? serviceNames.toString()
                    .substring( 0, serviceNames.length() - 1 ) : "" );
            inUserForm.setPartnerNames( StringUtils.isNotBlank( partners ) ? partners.toString()
                    .substring( 0, partners.length() - 1 ) : "" );
            inUserForm.setServiceNameArray( serviceNameList.toArray( new String[serviceNameList.size()] ) );
            inUserForm.setPartnerNameArray( partnerList.toArray( new String[partnerList.size()] ) );
        }
    }

    public ActionForward modifyUser( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.CREATE_USER;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm userForm = (LoginForm) form;
        userForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
        HttpSession httpSession = request.getSession( false );
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( StringUtils.isValidObj( userDto ) )
            {
                String userId = userDto.getCrmUserDetailsPojo().getUserId();
                LOGGER.info( "user Id in Modify User Method" + userId );
                userForm.setCrmUserPojo( (CrmUserPojo) httpSession.getAttribute( IAppConstants.OLD_USER_OBJECT ) );
                if ( !LoginFormHelper.checkFunctionalBinforSales( errors, userForm,
                                                                  request.getParameter( IAppConstants.PARAMETER_NAME ) )
                        .isEmpty() )
                {
                    saveErrors( request, errors );
                    //return mapping.findForward( forwardPage );
                }
                else
                {
                    List<CrmUserAssociationPojo> associationPojos = getToUpdateAssociations( userForm, userId );
                    userForm.setAssociationPojos( associationPojos );
                    CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm()
                            .modifyUserOperation( userForm, ServiceParameter.MODIFY.getParameter(),
                                                  userDto.getCrmUserDetailsPojo().getUserId() );
                    if ( null != crmUserDetailsDto.getCrmUserDetailsPojo() )
                    {
                        String statusCode = crmUserDetailsDto.getStatusCode();
                        if ( null != statusCode )
                        {
                            if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                            {
                                userForm.setCrmUserPojo( crmUserDetailsDto.getCrmUserDetailsPojo() );
                                if ( StringUtils.isValidObj( userForm.getCrmUserPojo().getFunctionalBin() ) )
                                {
                                    userForm.setFunctionalBinArray( StringUtils.commaStringToArray( userForm
                                            .getCrmUserPojo().getFunctionalBin() ) );
                                }
                                httpSession.setAttribute( IAppConstants.OLD_USER_OBJECT, userForm.getCrmUserPojo() );
                                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                            }
                            else
                            {
                                LOGGER.info( "Response status  desc::" + crmUserDetailsDto.getStatusDesc()
                                        + "Response status code  :: " + statusCode );
                                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                            }
                        }
                        else
                        {
                            LOGGER.info( "found status code null " );
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "found  CrmUserDetailsPojo  null " );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
            }
            else
            {
                LOGGER.info( "found session null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.error( "Error occured in Modify User", ex );
        }
        userForm.setProductList( CRMUtils.getProducts() );
        userForm.setPartnerList( CRMCacheManager.getActivePartnerList() );
        setUserAssociationToForm( userForm );
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward viewUser( ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response )
    {
        ConfigDto configDto = null;
        try
        {
            HttpSession httpSession = request.getSession( false );
            List<CrmUserPojo> crmUserPojoList = (List<CrmUserPojo>) httpSession
                    .getAttribute( IAppConstants.CRM_USER_SEARCH_LIST );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            LoginForm userForm = (LoginForm) form;
            LOGGER.info( "ID" + request.getParameter( "userId" ) );
            crmUserPojo.setRecordId( Long.parseLong( request.getParameter( "userId" ) ) );
            int pojoIndex = crmUserPojoList.indexOf( crmUserPojo );
            if ( pojoIndex >= 0 )
            {
                userForm.setCrmUserPojo( crmUserPojoList.get( pojoIndex ) );
                userForm.getCrmUserPojo().setFunctionalBin( StringUtils.removeCommaStartEnd( userForm.getCrmUserPojo()
                                                                    .getFunctionalBin() ) );
                userForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
                String userID = crmUserPojoList.get( pojoIndex ).getUserId();
                LOGGER.info( "userID........" + userID );
                CrmuserDetailsDto crmuserDetailsDto = getUsermngmntbm().getCustomerAssociations( userID );
                LOGGER.info( "CrmUserAssociationPojos Size : " + crmuserDetailsDto.getCrmUserAssociationPojos().size() );
                if ( CommonValidator.isValidCollection( crmuserDetailsDto.getCrmUserAssociationPojos() ) )
                {
                    StringBuffer serviceNames = new StringBuffer();
                    StringBuffer partners = new StringBuffer();
                    for ( CrmUserAssociationPojo crmUserAssociationPojo : crmuserDetailsDto
                            .getCrmUserAssociationPojos() )
                    {
                        if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                 crmUserAssociationPojo.getStatus() ) )
                        {
                            if ( StringUtils.equals( "SN", crmUserAssociationPojo.getAssociationType() ) )
                            {
                                serviceNames.append( crmUserAssociationPojo.getAssociatedValue() )
                                        .append( IAppConstants.COMMA );
                            }
                            else if ( StringUtils.equals( "PT", crmUserAssociationPojo.getAssociationType() ) )
                            {
                                partners.append( crmUserAssociationPojo.getAssociatedValue() )
                                        .append( IAppConstants.COMMA );
                            }
                        }
                    }
                    userForm.setServiceNames( StringUtils.isNotBlank( serviceNames ) ? serviceNames.toString()
                            .substring( 0, serviceNames.length() - 1 ) : "" );
                    userForm.setPartnerNames( StringUtils.isNotBlank( partners ) ? partners.toString()
                            .substring( 0, partners.length() - 1 ) : "" );
                    LOGGER.info( "Service Names : " + userForm.getServiceNames() );
                    LOGGER.info( "Partner Names : " + userForm.getPartnerNames() );
                }
                List<UserRolesPojo> userRolePojos = getUsermngmntbm().getUserAssignAccess( userID ).getUserRolesPojos();
                LOGGER.info( "Role list size are......." + userRolePojos.size() );
                if ( !userRolePojos.isEmpty() )
                {
                    userForm.setUserRolesPojos( userRolePojos );
                    areaAndParameterDisplay( userForm, userRolePojos );
                }
                request.removeAttribute( IAppConstants.CRM_USER_SEARCH_LIST );
            }
            LOGGER.info( "pojo index " + pojoIndex );
            configDto = getUsermngmntbm().getActivityLogs( userForm );
            httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
            if ( configDto.getAuditLogPojos().size() > 0 )
            {
                httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
            }
            LOGGER.info( "List of AuditLog Size ::" + configDto.getAuditLogPojos().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in Modify User", ex );
        }
        return mapping.findForward( IActionForwardConstant.VIEW_USER );
    }

    public ActionForward searchUserById( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.SEARCH_USER_BY_ID_PAGE;
        LoginForm loginForm = (LoginForm) form;
        ActionMessages messages = getMessages( request );
        ActionMessages error = getErrors( request );
        HttpSession httpSession = request.getSession( false );
        httpSession.removeAttribute( IAppConstants.OLD_USER_OBJECT );
        try
        {
            HttpSession session = request.getSession( false );
            if ( StringUtils.isEmpty( loginForm.getUserId() ) )
            {
                loginForm.setUserId( (String) request.getSession().getAttribute( "USERID" ) );
            }
            CrmuserDetailsDto crmUserDetailsDto = null;
            if ( StringUtils.isNotBlank( loginForm.getUserId() ) )
            {
                crmUserDetailsDto = getUsermngmntbm().searchUser( loginForm );
                if ( StringUtils.isValidObj( crmUserDetailsDto ) )
                {
                    String statusCode = crmUserDetailsDto.getStatusCode();
                    if ( StringUtils.isValidObj( statusCode ) )
                    {
                        LOGGER.info( "Response status code::" + statusCode + "Response status desc::"
                                + crmUserDetailsDto.getStatusDesc() );
                        if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                        {
                            List<CrmUserPojo> crmUserList = crmUserDetailsDto.getCrmUserDetailsPojoList();
                            if ( !crmUserList.isEmpty() )
                            {
                                loginForm.setSearchUserList( crmUserList );
                                loginForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
                            }
                            else
                            {
                                error.add( IAppConstants.NO_RECORD_FOUND,
                                           new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                            }
                        }
                        else
                        {
                            error.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Status code found null" );
                        error.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                    if ( ! ( crmUserDetailsDto.getCrmUserDetailsPojoList().isEmpty() ) )
                    {
                        session.setAttribute( IAppConstants.CRM_USER_SEARCH_LIST,
                                              crmUserDetailsDto.getCrmUserDetailsPojoList() );
                    }
                }
                else
                {
                    LOGGER.info( "Get Value NULL for crmuserdetailsdto from business manager" );
                    error.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                }
            }
            request.getSession().removeAttribute( "USERID" );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in Search  User by Id", ex );
        }
        saveErrors( request, error );
        saveMessages( request, messages );
        return mapping.findForward( forwardPage );
    }

    public ActionForward resetPassword( ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.CREATE_USER;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm userForm = (LoginForm) form;
        userForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().resetPassword( userForm,
                                                                                       CRMParameter.RESET_PASSWORD
                                                                                               .getParameter(),
                                                                                       userDto.getCrmUserDetailsPojo()
                                                                                               .getUserId() );
                if ( null != crmUserDetailsDto )
                {
                    if ( null != crmUserDetailsDto.getCrmUserDetailsPojo() )
                    {
                        userForm.setCrmUserPojo( crmUserDetailsDto.getCrmUserDetailsPojo() );
                        String statusCode = crmUserDetailsDto.getStatusCode();
                        if ( null != statusCode )
                        {
                            if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                            {
                                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                            }
                            else
                            {
                                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                            }
                        }
                        else
                        {
                            LOGGER.info( "Status code found null" );
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Crm UserDetails Pojo found  null" );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
                else
                {
                    LOGGER.info( "Crm UserDetails Dto found  null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.error( "Error occured", e );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward myProfile( ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response )
    {
        HttpSession httpSession = request.getSession( true );
        LoginForm userForm = (LoginForm) form;
        httpSession.getAttribute( IAppConstants.CRM_USER_POJO );
        CrmUserPojo crmUserPojo = (CrmUserPojo) httpSession.getAttribute( IAppConstants.CRM_USER_POJO );
        List<UserRolesPojo> userRolePojos = null;
        if ( null != crmUserPojo )
        {
            userForm.setCrmUserPojo( crmUserPojo );
            String userID = userForm.getCrmUserPojo().getUserId();
            LOGGER.info( "user id:::is:::" + userID );
            userRolePojos = getUsermngmntbm().getUserAssignAccess( userID ).getUserRolesPojos();
            if ( !userRolePojos.isEmpty() )
            {
                userForm.setUserRolesPojos( userRolePojos );
                areaAndParameterDisplay( userForm, userRolePojos );
            }
            else
            {
                LOGGER.info( "------------====userRolePojos is empty" );
            }
            CrmuserDetailsDto crmuserDetailsDto = getUsermngmntbm().getCustomerAssociations( userForm.getCrmUserPojo()
                                                                                                     .getUserId() );
            LOGGER.info( "CrmUserAssociationPojos Size : " + crmuserDetailsDto.getCrmUserAssociationPojos().size() );
            if ( CommonValidator.isValidCollection( crmuserDetailsDto.getCrmUserAssociationPojos() ) )
            {
                StringBuffer serviceNames = new StringBuffer();
                StringBuffer partners = new StringBuffer();
                for ( CrmUserAssociationPojo crmUserAssociationPojo : crmuserDetailsDto.getCrmUserAssociationPojos() )
                {
                    if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), crmUserAssociationPojo.getStatus() ) )
                    {
                        if ( StringUtils.equals( "SN", crmUserAssociationPojo.getAssociationType() ) )
                        {
                            serviceNames.append( crmUserAssociationPojo.getAssociatedValue() )
                                    .append( IAppConstants.COMMA );
                        }
                        else if ( StringUtils.equals( "PT", crmUserAssociationPojo.getAssociationType() ) )
                        {
                            partners.append( crmUserAssociationPojo.getAssociatedValue() ).append( IAppConstants.COMMA );
                        }
                    }
                }
                userForm.setServiceNames( StringUtils.isNotBlank( serviceNames ) ? serviceNames.toString()
                        .substring( 0, serviceNames.length() - 1 ) : "" );
                userForm.setPartnerNames( StringUtils.isNotBlank( partners ) ? partners.toString()
                        .substring( 0, partners.length() - 1 ) : "" );
                LOGGER.info( "Service Names : " + userForm.getServiceNames() );
                LOGGER.info( "Partner Names : " + userForm.getPartnerNames() );
            }
        }
        return mapping.findForward( IActionForwardConstant.MY_ACCOUNT );
    }

    public void areaAndParameterDisplay( LoginForm loginform, List<UserRolesPojo> userRolesPojos )
    {
        long parameterId = 1l;
        List<CrmParameterPojo> crmParameterList = getMasterDataBm()
                .getParameters( 0, CRMParameter.USER, CRMParameter.INTERNAL.getParameter() );
        for ( CrmParameterPojo crmParameterPojo : crmParameterList )
        {
            if ( crmParameterPojo.getParameterName().equals( "Area" ) )
            {
                parameterId = crmParameterPojo.getParameterId();
            }
        }
        List<UserRolesPojo> parameterList = new ArrayList<UserRolesPojo>();
        List<AreaMappingPojo> pojos = new ArrayList<AreaMappingPojo>();
        for ( UserRolesPojo userRolesPojo : userRolesPojos )
        {
            if ( null != userRolesPojo.getCrmParameter() )
            {
                if ( userRolesPojo.getCrmParameter().getParameterId() == parameterId )
                {
                    AreaMappingPojo pojo = new AreaMappingPojo();
                    if ( isNumeric( userRolesPojo.getParameterValue() ) )
                    {
                        pojo.setAreaId( Long.parseLong( userRolesPojo.getParameterValue() ) );
                    }
                    pojo.setMappingId( userRolesPojo.getRecordId() );
                    pojo.setUserId( userRolesPojo.getUserId() );
                    pojo.setCreatedBy( userRolesPojo.getCreatedBy() );
                    pojo.setStatus( userRolesPojo.getStatus() );
                    pojo.setEditable( true );
                    pojos.add( pojo );
                }
                else
                {
                    parameterList.add( userRolesPojo );
                }
            }
        }
        fillAreaMappingpojos( pojos );
        loginform.setParameterList( parameterList );
        loginform.setAreaMappingPojos( pojos );
    }

    public ActionForward changeUserStatus( ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.CREATE_USER;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm userForm = (LoginForm) form;
        HttpSession httpSession = request.getSession( false );
        userForm.setLeadStagesList( CRMCacheManager.getFunctionalBins() );
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm()
                        .changeUserStatus( userForm, CRMParameter.CHANGE_STATUS.getParameter(),
                                           userDto.getCrmUserDetailsPojo().getUserId() );
                if ( null != crmUserDetailsDto )
                {
                    if ( null != crmUserDetailsDto.getCrmUserDetailsPojo() )
                    {
                        userForm.setCrmUserPojo( crmUserDetailsDto.getCrmUserDetailsPojo() );
                        httpSession.setAttribute( IAppConstants.OLD_USER_OBJECT, userForm.getCrmUserPojo() );
                        String statusCode = crmUserDetailsDto.getStatusCode();
                        if ( null != statusCode )
                        {
                            if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                            {
                                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                            }
                            else
                            {
                                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                            }
                        }
                        else
                        {
                            LOGGER.info( "Status code found null" );
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Crm UserDetails Pojo found  null" );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
                else
                {
                    LOGGER.info( "Crm UserDetails Dto found  null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
                userForm.setProductList( CRMUtils.getProducts() );
                userForm.setPartnerList( CRMCacheManager.getActivePartnerList() );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.error( "Error occured", e );
        }
        saveMessages( request, messages );
        return mapping.findForward( forwardPage );
    }

    public ActionForward assignRolesPage( ActionMapping mapping,
                                          ActionForm form,
                                          HttpServletRequest request,
                                          HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.ASSIGN_ROLE_GROUP;
        ActionMessages messages = new ActionMessages();
        try
        {
            HttpSession httpSession = request.getSession( false );
            List<CrmUserPojo> crmUserPojoList = (List<CrmUserPojo>) httpSession
                    .getAttribute( IAppConstants.CRM_USER_SEARCH_LIST );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            LoginForm userForm = (LoginForm) form;
            crmUserPojo.setRecordId( userForm.getRecordID() );
            int pojoIndex = crmUserPojoList.indexOf( crmUserPojo );
            if ( pojoIndex >= 0 )
            {
                CrmUserPojo userPojo = crmUserPojoList.get( pojoIndex );
                userForm.setCrmUserPojo( userPojo );
                httpSession.setAttribute( IAppConstants.SEARCH_USER_POJO, userPojo );
            }
        }
        catch ( Exception e )
        {
            messages.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.error( "Error occured", e );
        }
        saveMessages( request, messages );
        return mapping.findForward( forwardPage );
    }

    public ActionForward assignParameterPage( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.ASSIGN_PARAMETER_GROUP;
        ActionMessages messages = new ActionMessages();
        try
        {
            HttpSession httpSession = request.getSession( false );
            List<CrmUserPojo> crmUserPojoList = (List<CrmUserPojo>) httpSession
                    .getAttribute( IAppConstants.CRM_USER_SEARCH_LIST );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            LoginForm userForm = (LoginForm) form;
            crmUserPojo.setRecordId( userForm.getRecordID() );
            int pojoIndex = crmUserPojoList.indexOf( crmUserPojo );
            if ( pojoIndex >= 0 )
            {
                CrmUserPojo userPojo = crmUserPojoList.get( pojoIndex );
                userForm.setCrmUserPojo( userPojo );
                httpSession.setAttribute( IAppConstants.SEARCH_USER_POJO, userPojo );
            }
        }
        catch ( Exception e )
        {
            messages.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.error( "Error occured", e );
        }
        saveMessages( request, messages );
        return mapping.findForward( forwardPage );
    }

    public List<CrmUserAssociationPojo> getToUpdateAssociations( LoginForm inLoginForm, String inUserId )
    {
        List<String> serviceNames = new ArrayList<String>();
        if ( StringUtils.isValidObj( inLoginForm.getServiceNameArray() )
                && inLoginForm.getServiceNameArray().length > 0 )
        {
            serviceNames = new ArrayList<String>( Arrays.asList( inLoginForm.getServiceNameArray() ) );
        }
        List<String> partners = new ArrayList<String>();
        if ( StringUtils.isValidObj( inLoginForm.getPartnerNameArray() )
                && inLoginForm.getPartnerNameArray().length > 0 )
        {
            partners = new ArrayList<String>( Arrays.asList( inLoginForm.getPartnerNameArray() ) );
        }
        CrmuserDetailsDto crmuserDetailsDto = getUsermngmntbm().getCustomerAssociations( inLoginForm.getCrmUserPojo()
                                                                                                 .getUserId() );
        List<CrmUserAssociationPojo> toUpdate = new ArrayList<CrmUserAssociationPojo>();
        if ( CommonValidator.isValidCollection( crmuserDetailsDto.getCrmUserAssociationPojos() ) )
        {
            for ( CrmUserAssociationPojo crmUserAssociationPojo : crmuserDetailsDto.getCrmUserAssociationPojos() )
            {
                if ( StringUtils.equals( "SN", crmUserAssociationPojo.getAssociationType() ) )
                {
                    if ( serviceNames.contains( crmUserAssociationPojo.getAssociatedValue() ) )
                    {
                        if ( StringUtils.equals( CRMStatusCode.INACTIVE.getStatusCode(),
                                                 crmUserAssociationPojo.getStatus() ) )
                        {
                            crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                            crmUserAssociationPojo.setLastModifiedBy( inUserId );
                            toUpdate.add( crmUserAssociationPojo );
                        }
                        serviceNames.remove( crmUserAssociationPojo.getAssociatedValue() );
                    }
                    else if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                  crmUserAssociationPojo.getStatus() ) )
                    {
                        crmUserAssociationPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                        crmUserAssociationPojo.setLastModifiedBy( inUserId );
                        toUpdate.add( crmUserAssociationPojo );
                    }
                }
                else if ( StringUtils.equals( "PT", crmUserAssociationPojo.getAssociationType() ) )
                {
                    if ( partners.contains( crmUserAssociationPojo.getAssociatedValue() ) )
                    {
                        if ( StringUtils.equals( CRMStatusCode.INACTIVE.getStatusCode(),
                                                 crmUserAssociationPojo.getStatus() ) )
                        {
                            crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                            crmUserAssociationPojo.setLastModifiedBy( inUserId );
                            toUpdate.add( crmUserAssociationPojo );
                        }
                        partners.remove( crmUserAssociationPojo.getAssociatedValue() );
                    }
                    else if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                                                  crmUserAssociationPojo.getStatus() ) )
                    {
                        crmUserAssociationPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                        crmUserAssociationPojo.setLastModifiedBy( inUserId );
                        toUpdate.add( crmUserAssociationPojo );
                    }
                }
            }
            if ( CommonValidator.isValidCollection( serviceNames ) )
            {
                for ( String service : serviceNames )
                {
                    CrmUserAssociationPojo crmUserAssociationPojo = new CrmUserAssociationPojo();
                    crmUserAssociationPojo.setAssociatedValue( service );
                    crmUserAssociationPojo.setAssociationType( "SN" );
                    crmUserAssociationPojo.setCreatedBy( inUserId );
                    crmUserAssociationPojo.setLastModifiedBy( null );
                    crmUserAssociationPojo.setUserId( inLoginForm.getUserId() );
                    crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    toUpdate.add( crmUserAssociationPojo );
                }
            }
            if ( CommonValidator.isValidCollection( partners ) )
            {
                for ( String partner : partners )
                {
                    CrmUserAssociationPojo crmUserAssociationPojo = new CrmUserAssociationPojo();
                    crmUserAssociationPojo.setAssociatedValue( partner );
                    crmUserAssociationPojo.setAssociationType( "PT" );
                    crmUserAssociationPojo.setCreatedBy( inUserId );
                    crmUserAssociationPojo.setLastModifiedBy( null );
                    crmUserAssociationPojo.setUserId( inLoginForm.getUserId() );
                    crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    toUpdate.add( crmUserAssociationPojo );
                }
            }
        }
        else
        {
            if ( CommonValidator.isValidCollection( serviceNames ) )
            {
                for ( String service : serviceNames )
                {
                    CrmUserAssociationPojo crmUserAssociationPojo = new CrmUserAssociationPojo();
                    crmUserAssociationPojo.setAssociatedValue( service );
                    crmUserAssociationPojo.setAssociationType( "SN" );
                    crmUserAssociationPojo.setCreatedBy( inUserId );
                    crmUserAssociationPojo.setUserId( inLoginForm.getUserId() );
                    crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    toUpdate.add( crmUserAssociationPojo );
                }
            }
            if ( CommonValidator.isValidCollection( partners ) )
            {
                for ( String partner : partners )
                {
                    CrmUserAssociationPojo crmUserAssociationPojo = new CrmUserAssociationPojo();
                    crmUserAssociationPojo.setAssociatedValue( partner );
                    crmUserAssociationPojo.setAssociationType( "PT" );
                    crmUserAssociationPojo.setCreatedBy( inUserId );
                    crmUserAssociationPojo.setUserId( inLoginForm.getUserId() );
                    crmUserAssociationPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    toUpdate.add( crmUserAssociationPojo );
                }
            }
        }
        return toUpdate;
    }

    private void fillAreaMappingpojos( List<AreaMappingPojo> areaMappingPojos )
    {
        List<AreaPojo> areaPojos = GISUtils.getAllActiveAreas( 0, 0, true );
        List<CityPojo> cityPojos = GISUtils.getAllActiveCities();
        List<StatePojo> statePojos = GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA );
        for ( AreaMappingPojo areaMappingPojo : areaMappingPojos )
        {
            for ( AreaPojo areaPojo : areaPojos )
            {
                if ( areaPojo.getAreaId() == areaMappingPojo.getAreaId() )
                {
                    areaMappingPojo.setCityId( areaPojo.getCityId() );
                    areaMappingPojo.setAreaName( areaPojo.getArea() );
                }
            }
            for ( CityPojo cityPojo : cityPojos )
            {
                if ( cityPojo.getCityId() == areaMappingPojo.getCityId() )
                {
                    areaMappingPojo.setStateId( cityPojo.getStateId() );
                    areaMappingPojo.setCityName( cityPojo.getCityName() );
                }
            }
            for ( StatePojo statePojo : statePojos )
            {
                if ( statePojo.getStateId() == areaMappingPojo.getStateId() )
                {
                    areaMappingPojo.setStateName( statePojo.getStateName() );
                }
            }
            LOGGER.info( "final Area Mapping Pojo::" + areaMappingPojo );
        }
    }

    public static boolean isNumeric( String str )
    {
        try
        {
            long d = Long.parseLong( str );
        }
        catch ( NumberFormatException nfe )
        {
            return false;
        }
        return true;
    }
}
