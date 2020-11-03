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
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.service.comparators.UserRolesSortComparator;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.StringUtils;

public class ParameterManagementAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( ParameterManagementAction.class );
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

    public ActionForward assingUserParameter( ActionMapping mapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse response )
        throws Exception
    {
        LOGGER.info( "in ParameterManagementAction assing User Parameter::::::::::::::::::::::::::::::::::::::::::::::::::::" );
        String forwardPage = IActionForwardConstant.ASSIGN_PARAMETER_GROUP;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm loginForm = (LoginForm) inForm;
        try
        {
            CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm().assignUserParameter( loginForm );
            if ( null != crmUserDetailsDto )
            {
                String statusCode = crmUserDetailsDto.getStatusCode();
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.ASSIGN_PARAMETER_GROUP_PAGE;
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

    public ActionForward updateParameterAssignPage( ActionMapping mapping,
                                                    ActionForm form,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.ASSIGN_PARAMETER_GROUP;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        long parameterId = 1l;
        try
        {
            LOGGER.info( " updateParameterAssignPage" );
            List<UserRolesPojo> userParameterPojoList = new ArrayList<UserRolesPojo>();
            LoginForm loginForm = (LoginForm) form;
            HttpSession httpSessionForSearchUser = request.getSession( false );
            CrmUserPojo crmUserPojo = (CrmUserPojo) httpSessionForSearchUser
                    .getAttribute( IAppConstants.SEARCH_USER_POJO );
            List<UserRolesPojo> userRolesPojos = getUsermngmntbm().getUserAssignAccess( crmUserPojo.getUserId() )
                    .getUserRolesPojos();
            LOGGER.info( " crmParameterList" + userRolesPojos.size() );
            List<CrmParameterPojo> crmParameterList = getMasterDataBm().getParameters( 0,
                                                                                       CRMParameter.USER,
                                                                                       CRMParameter.INTERNAL
                                                                                               .getParameter() );
            LOGGER.info( " crmParameterList" + crmParameterList.size() );
            for ( CrmParameterPojo crmParameterPojo : crmParameterList )
            {
                if ( crmParameterPojo.getParameterName().equalsIgnoreCase( "Area" ) )
                {
                    parameterId = crmParameterPojo.getParameterId();
                }
            }
            //work for set Parametergroup
            List<GroupsPojo> groupsPojos = getMasterDataBm().getActiveGroup( CRMParameter.CRM_ACTIVE_PARAMETER_GROUP
                                                                                     .getParameter() );
            List<UserRolesPojo> userRolesPojosForgroup = new ArrayList<UserRolesPojo>();
            List<UserRolesPojo> existingUserRolesPojos = new ArrayList<UserRolesPojo>();
            List<CrmParameterPojo> newCrmParameterList = new ArrayList<CrmParameterPojo>();
            UserRolesPojo pojoForgroup = null;
            for ( GroupsPojo groupsPojo : groupsPojos )
            {
                pojoForgroup = new UserRolesPojo();
                pojoForgroup.setGroups( groupsPojo );
                userRolesPojosForgroup.add( pojoForgroup );
            }
            LOGGER.info( "size of userRolesPojosForgroup::::" + userRolesPojosForgroup.size() );
            //            if ( userRolesPojos.isEmpty() )
            //            {
            //                LOGGER.info( "List of Parameter for create" );
            //                UserRolesPojo pojo = new UserRolesPojo();
            //                pojo.setCrmParameter( new CrmParameterPojo() );
            //                pojo.setEditable( true );
            //                pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            //                userParameterPojoList.add( pojo );
            //            }
            if ( !userRolesPojos.isEmpty() )
            {
                LOGGER.info( "List of Update Parameter" );
                for ( UserRolesPojo userRolesPojo : userRolesPojos )
                {
                    if ( StringUtils.isValidObj( userRolesPojo.getGroups() ) )
                    {
                        if ( CRMParameter.PARAMETER_GROUP.getParameter().equals( userRolesPojo.getGroups()
                                                                                         .getGroupType() ) )
                        {
                            if ( userRolesPojo.getCrmParameter().getParameterId() != parameterId )
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
                    }
                    if ( StringUtils.isValidObj( userRolesPojo.getCrmParameter() ) )
                    {
                        if ( userRolesPojo.getCrmParameter().getParameterId() != parameterId )
                        {
                            userParameterPojoList.add( userRolesPojo );
                            existingUserRolesPojos.add( userRolesPojo );
                        }
                    }
                }
            }
            for ( CrmParameterPojo crmParameterPojo : crmParameterList )
            {
                if ( !crmParameterPojo.getParameterName().equalsIgnoreCase( "Area" ) )
                {
                    newCrmParameterList.add( crmParameterPojo );
                }
            }
            loginForm.setOldUserRolesPojos( existingUserRolesPojos );
            //get current access on user id
            loginForm.setUserRolesPojosForGroup( userRolesPojosForgroup );
            Collections.sort( loginForm.getUserRolesPojosForGroup(), new UserRolesSortComparator() );
            loginForm.setUserRolesPojos( userParameterPojoList );
            loginForm.setCrmParameterPojos( newCrmParameterList );
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

    public ActionForward addParameterRow( ActionMapping mapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse response )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LoginForm loginform = (LoginForm) inForm;
        try
        {
            UserRolesPojo pojo = new UserRolesPojo();
            pojo.setCrmParameter( new CrmParameterPojo() );
            pojo.setEditable( true );
            pojo.setRecordId( 0l );
            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            if ( !StringUtils.isValidObj( loginform.getUserRolesPojos() ) )
            {
                loginform.setUserRolesPojos( new ArrayList<UserRolesPojo>() );
            }
            loginform.getUserRolesPojos().add( 0, pojo );
            LOGGER.info( "UserRolesPojos values :" + loginform.getUserRolesPojos() );
        }
        catch ( Exception e )
        {
            LOGGER.info( "Error occured in action addRoleGroupRow", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return mapping.findForward( IActionForwardConstant.ASSIGN_PARAMETER_GROUP );
    }

    public ActionForward deleteParameterRow( ActionMapping mapping,
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
        return mapping.findForward( IActionForwardConstant.ASSIGN_PARAMETER_GROUP );
    }
}
