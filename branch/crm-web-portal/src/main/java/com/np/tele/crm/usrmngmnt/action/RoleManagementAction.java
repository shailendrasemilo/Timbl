package com.np.tele.crm.usrmngmnt.action;

import java.util.ArrayList;
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

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.service.comparators.UserRolesSortComparator;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.AreaMappingPojo;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.usrmngmnt.forms.LoginFormHelper;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;

public class RoleManagementAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( RoleManagementAction.class );
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

    public ActionForward addRolesRow( ActionMapping mapping,
                                      ActionForm inForm,
                                      HttpServletRequest inRequest,
                                      HttpServletResponse response )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm loginform = (LoginForm) inForm;
        List<UserRolesPojo> rolesPojos = null;
        try
        {
            UserRolesPojo pojo = new UserRolesPojo();
            pojo.setCrmRoles( new CrmRolesPojo() );
            pojo.setEditable( true );
            pojo.setReadAccess( true );
            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            pojo.setRecordId( 0l );
            if ( !StringUtils.isValidObj( loginform.getUserRolesPojos() ) )
            {
                loginform.setUserRolesPojos( new ArrayList<UserRolesPojo>() );
            }
            rolesPojos = loginform.getUserRolesPojos();
            for ( UserRolesPojo userRolesPojo : rolesPojos )
            {
                if ( loginform.getCrmRoles().contains( userRolesPojo.getCrmRoles() ) && userRolesPojo.getRecordId() > 0 )
                {
                    loginform.getCrmRoles().remove( userRolesPojo.getCrmRoles() );
                }
            }
            loginform.getUserRolesPojos().add( 0, pojo );
            LOGGER.info( "size in add role method ::::::::::::::::::::::::::" + loginform.getUserRolesPojos().size() );
            LOGGER.info( "UserRolesPojos values :" + loginform.getUserRolesPojos() );
        }
        catch ( Exception e )
        {
            LOGGER.info( "Error occured in action addRoleGroupRow", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return mapping.findForward( IActionForwardConstant.ASSIGN_ROLE_GROUP );
    }

    public ActionForward deleteRolesRow( ActionMapping mapping,
                                         ActionForm inForm,
                                         HttpServletRequest inRequest,
                                         HttpServletResponse response )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            LoginForm loginform = (LoginForm) inForm;
            loginform.getUserRolesPojos().remove( loginform.getRowCounter() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in DeleteRoleGroupRow Method" + ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return mapping.findForward( IActionForwardConstant.ASSIGN_ROLE_GROUP );
    }

    public ActionForward assingUserRole( ActionMapping mapping,
                                         ActionForm inForm,
                                         HttpServletRequest inRequest,
                                         HttpServletResponse response )
        throws Exception
    {
        LOGGER.info( "in assing User role::::::::::::::::::::::::::::::::::::::::::::::::::::" );
        String forwardPage = IActionForwardConstant.ASSIGN_ROLE_GROUP;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm loginForm = (LoginForm) inForm;
        try
        {
            CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().assignUserRole( loginForm );
            if ( null != crmUserDetailsDto )
            {
                String statusCode = crmUserDetailsDto.getStatusCode();
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.ASSIGN_ROLE_GROUP_PAGE;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                LOGGER.info( "Find Dto Null from Service" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
            LOGGER.info( "size of crm roles ::::::::::::::::::::::::::::::::::::::" + loginForm.getCrmRoles().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error occured in action assingUserRole", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward updateRoleAssignPage( ActionMapping mapping,
                                               ActionForm form,
                                               HttpServletRequest request,
                                               HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.ASSIGN_ROLE_GROUP;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        try
        {
            LoginForm loginForm = (LoginForm) form;
            HttpSession httpSession = request.getSession( false );
            CrmUserPojo crmUserPojo = (CrmUserPojo) httpSession.getAttribute( IAppConstants.SEARCH_USER_POJO );
            List<UserRolesPojo> userRolesPojos = getUsermngmntbm().getUserAssignAccess( crmUserPojo.getUserId() )
                    .getUserRolesPojos();
            List<CrmRolesPojo> crmRoleList = getMasterDataBm().getRoles();
            SortingComparator<CrmRolesPojo> sortComparator = new SortingComparator<CrmRolesPojo>( "roleDescription" );
            Collections.sort( crmRoleList, sortComparator );
            sortComparator = null;
            //work for set rolegroup
            List<GroupsPojo> groupsPojos = getMasterDataBm().getActiveGroup( CRMParameter.CRM_ACTIVE_ROLE_GROUP
                                                                                     .getParameter() );
            SortingComparator<GroupsPojo> gorupSort = new SortingComparator<GroupsPojo>( "groupName" );
            Collections.sort( groupsPojos, gorupSort );
            gorupSort = null;
            List<UserRolesPojo> userRolesPojosForgroup = new ArrayList<UserRolesPojo>();
            UserRolesPojo pojoForgroup = null;
            for ( GroupsPojo groupsPojo : groupsPojos )
            {
                pojoForgroup = new UserRolesPojo();
                pojoForgroup.setGroups( groupsPojo );
                userRolesPojosForgroup.add( pojoForgroup );
            }
            LOGGER.info( "size of userRolesPojosForgroup::::" + userRolesPojosForgroup.size() );
            List<UserRolesPojo> userRolePojoList = new ArrayList<UserRolesPojo>();
            List<UserRolesPojo> existingUserRolesPojos = new ArrayList<UserRolesPojo>();
            if ( !userRolesPojos.isEmpty() )
            {
                LOGGER.info( "IN Update Block" );
                for ( UserRolesPojo userRolesPojo : userRolesPojos )
                {
                    if ( StringUtils.isValidObj( userRolesPojo.getGroups() ) )
                    {
                        if ( CRMParameter.ROLES_GROUP.getParameter().equals( userRolesPojo.getGroups().getGroupType() ) )
                        {
                            long recordID = userRolesPojo.getRecordId();
                            userRolesPojo.setRecordId( 0L );
                            userRolesPojosForgroup.remove( userRolesPojo );
                            userRolesPojo.setRecordId( recordID );
                            userRolesPojosForgroup.add( userRolesPojo );
                            if ( CRMStatusCode.ACTIVE.getStatusCode().equals( userRolesPojo.getStatus() ) )
                            {
                                userRolesPojo.setEditable( true );
                            }
                            else
                            {
                                userRolesPojo.setEditable( false );
                            }
                            existingUserRolesPojos.add( userRolesPojo );
                        }
                    }
                    if ( null != userRolesPojo.getCrmRoles() )
                    {
                        userRolePojoList.add( userRolesPojo );
                        existingUserRolesPojos.add( userRolesPojo );
                    }
                }
                //forwardPage = IActionForwardConstant.UPDATE_ROLE_GROUP;
            }
            LOGGER.info( "::::::::::::Pojo values:::::::::" + userRolesPojosForgroup.size() );
            loginForm.setOldUserRolesPojos( existingUserRolesPojos );
            loginForm.setUserRolesPojosForGroup( userRolesPojosForgroup );
            Collections.sort( loginForm.getUserRolesPojosForGroup(), new UserRolesSortComparator() );
            loginForm.setUserRolesPojos( userRolePojoList );
            loginForm.setCrmRoles( crmRoleList );
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

    public ActionForward assignAreaPage( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.ASSIGN_AREA_PAGE;
        List<AreaMappingPojo> pojos = new ArrayList<AreaMappingPojo>();
        LOGGER.info( "AssignAreaPage method called:" );
        long parameterId = 1l;
        ActionMessages messages = new ActionMessages();
        try
        {
            HttpSession httpSession = request.getSession( false );
            List<CrmUserPojo> crmUserPojoList = (List<CrmUserPojo>) httpSession
                    .getAttribute( IAppConstants.CRM_USER_SEARCH_LIST );
            List<CrmParameterPojo> crmParameterList = getMasterDataBm().getParameters( 0,
                                                                                       CRMParameter.USER,
                                                                                       CRMParameter.INTERNAL
                                                                                               .getParameter() );
            for ( CrmParameterPojo crmParameterPojo : crmParameterList )
            {
                if ( crmParameterPojo.getParameterName().equals( "Area" ) )
                {
                    parameterId = crmParameterPojo.getParameterId();
                }
            }
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            LoginForm userForm = (LoginForm) form;
            crmUserPojo.setRecordId( userForm.getRecordID() );
            LOGGER.info( "RecordId" + userForm.getRecordID() );
            int pojoIndex = crmUserPojoList.indexOf( crmUserPojo );
            if ( pojoIndex >= 0 )
            {
                CrmUserPojo userPojo = crmUserPojoList.get( pojoIndex );
                userForm.setCrmUserPojo( userPojo );
                httpSession.setAttribute( IAppConstants.SEARCH_USER_POJO, userPojo );
                List<UserRolesPojo> userRolesPojos = getUsermngmntbm().getUserAssignAccess( userPojo.getUserId() )
                        .getUserRolesPojos();
                userForm.setOldUserRolesPojos( userRolesPojos );
                for ( UserRolesPojo userRolesPojo : userRolesPojos )
                {
                    if ( null != userRolesPojo.getCrmParameter() )
                    {
                        if ( userRolesPojo.getCrmParameter().getParameterId() == parameterId )
                        {
                            AreaMappingPojo pojo = new AreaMappingPojo();
                            pojo.setUserRecordId( userPojo.getRecordId() );
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
                    }
                }
                userForm.setStateDataList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
                if ( null != pojos && pojos.size() > 0 )
                {
                    fillAreaMappingpojos( pojos );
                }
                //  userForm.setCityList( new ArrayList<CityPojo>() );
                // userForm.setAreaList( new ArrayList<AreaPojo>() );
                userForm.setAreaMappingPojos( pojos );
                userForm.setAreaMappingPojo( new AreaMappingPojo() );
                LOGGER.info( "AssignAreaPage method called:" + userPojo );
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

    public ActionForward addAssignAreaRow( ActionMapping mapping,
                                           ActionForm inForm,
                                           HttpServletRequest inRequest,
                                           HttpServletResponse response )
    {
        LOGGER.info( "addAreaRow method called:" );
        String forwardPage = IActionForwardConstant.ASSIGN_AREA_PAGE;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm userForm = (LoginForm) inForm;
        if ( null != userForm.getAreaMappingPojos() && userForm.getAreaMappingPojos().size() > 0 )
        {
            getCityList( userForm.getAreaMappingPojos() );
            getAreaList( userForm.getAreaMappingPojos() );
        }
        AreaMappingPojo pojo = new AreaMappingPojo();
        pojo.setMappingId( 0l );
        pojo.setEditable( true );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        if ( !StringUtils.isValidObj( userForm.getAreaMappingPojos() ) )
        {
            userForm.setAreaMappingPojos( new ArrayList<AreaMappingPojo>() );
        }
        userForm.getAreaMappingPojos().add( 0, pojo );
        userForm.setStateDataList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        //  userForm.setCityList( getCityList( pojo.getStateId() ) );
        //userForm.setAreaList( getAreaList( pojo.getCityId() ) );
        saveErrors( inRequest, errors );
        saveMessages( inRequest.getSession(), messages );
        return mapping.findForward( IActionForwardConstant.ASSIGN_AREA_PAGE );
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

    private void getCityList( List<AreaMappingPojo> areaMappingPojos )
    {
        List<CityPojo> finalPojoList = new ArrayList<CityPojo>();
        List<CityPojo> cityPojos = GISUtils.getAllActiveCities();
        for ( AreaMappingPojo areaMappingPojo : areaMappingPojos )
        {
            finalPojoList = new ArrayList<CityPojo>();
            if ( areaMappingPojo.getStateId() > 0 )
            {
                if ( null == areaMappingPojo.getCityList() || areaMappingPojo.getCityList().size() == 0 )
                {
                    for ( CityPojo cityPojo : cityPojos )
                    {
                        if ( cityPojo.getStateId() == areaMappingPojo.getStateId() && areaMappingPojo.getStateId() > 0 )
                        {
                            finalPojoList.add( cityPojo );
                        }
                        areaMappingPojo.setCityList( finalPojoList );
                    }
                }
            }
            else
            {
                finalPojoList.clear();
            }
        }
    }

    private void getAreaList( List<AreaMappingPojo> AreaMappingPojos )
    {
        List<AreaPojo> finalPojoList = new ArrayList<AreaPojo>();
        List<AreaPojo> areaPojos = GISUtils.getAllActiveAreas( 0, 0, true );
        for ( AreaMappingPojo areaMappingPojo : AreaMappingPojos )
        {
            finalPojoList = new ArrayList<AreaPojo>();
            if ( areaMappingPojo.getCityId() > 0 )
            {
                if ( null == areaMappingPojo.getAreaList() || areaMappingPojo.getAreaList().size() == 0 )
                {
                    for ( AreaPojo areaPojo : areaPojos )
                    {
                        if ( areaPojo.getCityId() == areaMappingPojo.getCityId() && areaMappingPojo.getCityId() > 0 )
                        {
                            finalPojoList.add( areaPojo );
                        }
                        areaMappingPojo.setAreaList( finalPojoList );
                    }
                }
            }
            else
            {
                finalPojoList.clear();
            }
        }
    }

    public ActionForward deleteAreaRow( final ActionMapping mapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse response )
    {
        final ActionMessages messages = new ActionMessages();
        final ActionMessages errors = new ActionMessages();
        try
        {
            LoginForm loginform = (LoginForm) inForm;
            loginform.getAreaMappingPojos().remove( loginform.getRowCounter() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
            // errors.add( IAppConstants.APP_ERROR, new ActionMessage( VTSServiceCode.VTS999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return mapping.findForward( IActionForwardConstant.ASSIGN_AREA_PAGE );
    }

    public ActionForward submitAssignArea( final ActionMapping mapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse response )
    {
        LOGGER.info( "submitAssignArea method called::" );
        final ActionMessages messages = new ActionMessages();
        final ActionMessages errors = new ActionMessages();
        final LoginForm userForm = (LoginForm) inForm;
        String forwardPage = IActionForwardConstant.ASSIGN_AREA_PAGE;
        long parameterId = 1l;
        try
        {
            LoginFormHelper.validateArea( errors, userForm );
            if ( errors.isEmpty() )
            {
                List<CrmParameterPojo> crmParameterList = getMasterDataBm().getParameters( 0,
                                                                                           CRMParameter.USER,
                                                                                           CRMParameter.INTERNAL
                                                                                                   .getParameter() );
                for ( CrmParameterPojo crmParameterPojo : crmParameterList )
                {
                    if ( crmParameterPojo.getParameterName().equals( "Area" ) )
                    {
                        parameterId = crmParameterPojo.getParameterId();
                    }
                }
                CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().assignUserArea( userForm, parameterId );
                if ( CRMServiceCode.CRM001.getStatusCode().equals( crmUserDetailsDto.getStatusCode() ) )
                {
                    getUserArea( userForm, parameterId );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmUserDetailsDto.getStatusCode() ) );
                    forwardPage = IActionForwardConstant.ASSIGN_AREA_PAGE;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmUserDetailsDto.getStatusCode() ) );
                }
            }
            else
            {
                getCityList( userForm.getAreaMappingPojos() );
                getAreaList( userForm.getAreaMappingPojos() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
        }
        saveMessages( inRequest.getSession(), messages );
        saveErrors( inRequest.getSession(), errors );
        return mapping.findForward( forwardPage );
    }

    private void getUserArea( final LoginForm userForm, long parameterId )
    {
        List<AreaMappingPojo> pojos = new ArrayList<AreaMappingPojo>();
        List<UserRolesPojo> newUserRolesPojos = getUsermngmntbm().getUserAssignAccess( userForm.getAreaMappingPojos()
                                                                                               .get( 0 ).getUserId() )
                .getUserRolesPojos();
        LOGGER.info( newUserRolesPojos.size() );
        for ( UserRolesPojo userRolesPojo : newUserRolesPojos )
        {
            if ( null != userRolesPojo.getCrmParameter() )
            {
                if ( userRolesPojo.getCrmParameter().getParameterId() == parameterId )
                {
                    AreaMappingPojo pojo = new AreaMappingPojo();
                    //  pojo.setUserRecordId( userPojo.getRecordId() );
                    pojo.setAreaId( Long.parseLong( userRolesPojo.getParameterValue() ) );
                    pojo.setMappingId( userRolesPojo.getRecordId() );
                    pojo.setUserId( userRolesPojo.getUserId() );
                    pojo.setCreatedBy( userRolesPojo.getCreatedBy() );
                    pojo.setStatus( userRolesPojo.getStatus() );
                    pojo.setEditable( true );
                    pojos.add( pojo );
                }
            }
        }
        if ( null != pojos && pojos.size() > 0 )
        {
            fillAreaMappingpojos( pojos );
        }
        userForm.setAreaMappingPojos( pojos );
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
