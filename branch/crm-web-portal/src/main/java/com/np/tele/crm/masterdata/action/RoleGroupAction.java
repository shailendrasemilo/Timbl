package com.np.tele.crm.masterdata.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.forms.RoleGroupForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;

public class RoleGroupAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( RoleGroupAction.class );
    private IMasterBMngr        masterDataBm;

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public ActionForward createRoleGroupPage( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            RoleGroupForm roleGroupForm = (RoleGroupForm) inForm;
            List<CrmRolesPojo> crmRoleList = getMasterDataBm().getRoles();
            List<AccessGroupPojo> accessGroupPojoList = new ArrayList<AccessGroupPojo>();
            AccessGroupPojo pojo = new AccessGroupPojo();
            pojo.setCrmRoles( new CrmRolesPojo() );
            pojo.setEditable( true );
            pojo.setReadAccess( true );
            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            accessGroupPojoList.add( pojo );
            roleGroupForm.setCrmRoles( crmRoleList );
            roleGroupForm.setAccessGroups( accessGroupPojoList );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action createRoleGroupPage", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.CREATE_ROLE_GROUP_PAGE );
    }

    public ActionForward addRoleGroupRow( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        RoleGroupForm roleGroupForm = (RoleGroupForm) inForm;
        try
        {
            AccessGroupPojo pojo = new AccessGroupPojo();
            pojo.setCrmRoles( new CrmRolesPojo() );
            pojo.setEditable( true );
            pojo.setReadAccess( true );
            pojo.setRecordId( 0l );
            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            if ( !StringUtils.isValidObj( roleGroupForm.getAccessGroups() ) )
            {
                roleGroupForm.setAccessGroups( new ArrayList<AccessGroupPojo>() );
            }
            roleGroupForm.getAccessGroups().add( 0, pojo );
            LOGGER.info( "AccessGroups values :" + roleGroupForm.getAccessGroups() );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action addRoleGroupRow", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.CREATE_ROLE_GROUP_PAGE );
    }

    public ActionForward deleteRoleGroupRow( ActionMapping inMapping,
                                             ActionForm inForm,
                                             HttpServletRequest inRequest,
                                             HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            RoleGroupForm roleGroupForm = (RoleGroupForm) inForm;
            if ( roleGroupForm.getAccessGroups().size() > 1 )
            {
                roleGroupForm.getAccessGroups().remove( roleGroupForm.getRowCounter() );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_ONE_ROLE_REQUIRED ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error in DeleteRoleGroupRow Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CREATE_ROLE_GROUP_PAGE );
    }

    public ActionForward createRoleGroup( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.CREATE_ROLE_GROUP_PAGE;
        RoleGroupForm roleGroupForm = (RoleGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            LOGGER.info( "GroupName:" + roleGroupForm.getRoleGroupName() + "\nGroupDescription:"
                    + roleGroupForm.getRoleGroupDescription() );
            LOGGER.info( "Role Name:" + roleGroupForm.getAccessGroups() );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                MasterDataDto masterDataDto = getMasterDataBm()
                        .createRoleGroup( roleGroupForm, ServiceParameter.CREATE.getParameter(),
                                          userDto.getCrmUserDetailsPojo().getUserId() );
                if ( null != masterDataDto )
                {
                    String statusCode = masterDataDto.getStatusCode();
                    LOGGER.info( "statusCode::::::" + statusCode + "status description" + masterDataDto.getStatusDesc() );
                    if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESSFULLY_ROLE_GROUP_CREATED,
                                                         roleGroupForm.getRoleGroupName() ) );
                        forwardPage = IActionForwardConstant.SEARCH_ROLE_GROUP;
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    LOGGER.info( "Master dto found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                }
            }
            else
            {
                LOGGER.info( "User Dto found null in session" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action createRoleGroup", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward searchRoleGroupPage( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
    {
        RoleGroupForm inRoleGroupForm = (RoleGroupForm) inForm;
        List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
        inRoleGroupForm.setSearchStatus( null );
        inRoleGroupForm.setStatusList( statusList );
        return inMapping.findForward( IActionForwardConstant.SEARCH_ROLE_GROUP_PAGE );
    }

    public ActionForward searchRoleGroup( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        RoleGroupForm inRoleGroupForm = (RoleGroupForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        if ( !StringUtils.isValidObj( messages ) )
        {
            messages = new ActionMessages();
        }
        if ( !StringUtils.isValidObj( errors ) )
        {
            errors = new ActionMessages();
        }
        try
        {
            MasterDataDto masterDataDto = getMasterDataBm().searchRoleGroup( inRoleGroupForm,
                                                                             ServiceParameter.SEARCH.getParameter() );
            if ( null != masterDataDto )
            {
                String statusCode = masterDataDto.getStatusCode();
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    if ( masterDataDto.getGroupsPojoList().isEmpty() )
                    {
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                    //  else
                    //   {
                    inRoleGroupForm.setGroupsPojos( masterDataDto.getGroupsPojoList() );
                    // }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                LOGGER.info( "Master Dto null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
            List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
            inRoleGroupForm.setStatusList( statusList );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action searchRoleGroup", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.SEARCH_ROLE_GROUP_PAGE );
    }

    public ActionForward viewRoleGroup( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
    {
        RoleGroupForm inRoleGroupForm = (RoleGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            List<GroupsPojo> crmGroupPojoList = inRoleGroupForm.getGroupsPojos();
            LOGGER.info( "No of pojo find in list" + crmGroupPojoList.size() );
            LOGGER.info( "record id found" + inRoleGroupForm.getRoleGroupId() );
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupId( Long.parseLong( inRequest.getParameter( "inRoleGroupId" ) ) );
            int pojoIndex = crmGroupPojoList.indexOf( groupPojo );
            if ( pojoIndex >= 0 )
            {
                groupPojo = crmGroupPojoList.get( pojoIndex );
                List<AccessGroupPojo> accessGroups = new ArrayList<AccessGroupPojo>();
                accessGroups.addAll( groupPojo.getAccessGroups() );
                if ( !accessGroups.isEmpty() )
                {
                    for ( AccessGroupPojo accessGroupPojo : accessGroups )
                    {
                        accessGroupPojo.setEditable( false );
                    }
                }
                inRoleGroupForm.setGroupPojo( groupPojo );
                inRoleGroupForm.setAccessGroups( accessGroups );
            }
            LOGGER.info( "pojo index " + pojoIndex );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.VIEW_ROLE_GROUP );
    }

    public ActionForward modifyRoleGroupPage( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.CREATE_ROLE_GROUP_PAGE;
        RoleGroupForm inRoleGroupForm = (RoleGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            List<GroupsPojo> crmGroupPojoList = inRoleGroupForm.getGroupsPojos();
            LOGGER.info( "No of pojo find in list" + crmGroupPojoList.size() );
            LOGGER.info( "record id found" + inRoleGroupForm.getRoleGroupId() );
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupId( inRoleGroupForm.getRoleGroupId() );
            int pojoIndex = crmGroupPojoList.indexOf( groupPojo );
            if ( pojoIndex >= 0 )
            {
                groupPojo = crmGroupPojoList.get( pojoIndex );
                List<AccessGroupPojo> accessGroups = new ArrayList<AccessGroupPojo>();
                accessGroups.addAll( groupPojo.getAccessGroups() );
                if ( !accessGroups.isEmpty() )
                {
                    for ( AccessGroupPojo accessGroupPojo : accessGroups )
                    {
                        accessGroupPojo.setEditable( true );
                    }
                }
                inRoleGroupForm.setRoleGroupName( groupPojo.getGroupName() );
                inRoleGroupForm.setRoleGroupDescription( groupPojo.getGroupDescription() );
                inRoleGroupForm.setGroupPojo( groupPojo );
                inRoleGroupForm.setAccessGroups( accessGroups );
            }
            inRoleGroupForm.setCrmRoles( getMasterDataBm().getRoles() );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action", e );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward modifyRoleGroup( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.CREATE_ROLE_GROUP_PAGE;
        RoleGroupForm roleGroupForm = (RoleGroupForm) inForm;
        LOGGER.info( "ID " + roleGroupForm.getGroupPojo().getGroupId() + " Status ::::"
                + roleGroupForm.getGroupPojo().getStatus() );
        ActionMessages messages = new ActionMessages();
        ActionMessages errros = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                MasterDataDto masterDataDto = getMasterDataBm()
                        .modifyRoleGroup( roleGroupForm, ServiceParameter.MODIFY.getParameter(),
                                          userDto.getCrmUserDetailsPojo().getUserId() );
                if ( null != masterDataDto )
                {
                    String statusCode = masterDataDto.getStatusCode();
                    if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        GroupsPojo groupPojo = masterDataDto.getGroupsPojo();
                        List<AccessGroupPojo> accessGroups = new ArrayList<AccessGroupPojo>();
                        accessGroups.addAll( groupPojo.getAccessGroups() );
                        if ( !accessGroups.isEmpty() )
                        {
                            for ( AccessGroupPojo accessGroupPojo : accessGroups )
                            {
                                accessGroupPojo.setEditable( true );
                            }
                        }
                        roleGroupForm.setRoleGroupName( groupPojo.getGroupName() );
                        roleGroupForm.setRoleGroupDescription( groupPojo.getGroupDescription() );
                        roleGroupForm.setGroupPojo( groupPojo );
                        roleGroupForm.setAccessGroups( accessGroups );
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCESSFULLY_ROLE_GROUP_UPDATED ) );
                    }
                    else
                    {
                        LOGGER.info( "statusCode::::::" + statusCode );
                        errros.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    LOGGER.info( "Master Dto null" );
                    errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                }
            }
            else
            {
                LOGGER.info( "User Dto found null in session" );
                errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action modifyRoleGroup", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errros );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward changeRoleGroupStatus( ActionMapping inMapping,
                                                ActionForm inForm,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.SEARCH_ROLE_GROUP_PAGE;
        RoleGroupForm inRoleGroupForm = (RoleGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errros = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                LOGGER.info( "Role GroupId:::" + inRoleGroupForm.getRoleGroupId() + ">>Role status::"
                        + inRoleGroupForm.getStatus() );
                MasterDataDto masterDataDto = getMasterDataBm().changeRoleGroupStatus( inRoleGroupForm,
                                                                                       CRMParameter.CHANGE_STATUS
                                                                                               .getParameter(),
                                                                                       userDto.getCrmUserDetailsPojo()
                                                                                               .getUserId() );
                if ( null != masterDataDto )
                {
                    String statusCode = masterDataDto.getStatusCode();
                    if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        masterDataDto = getMasterDataBm().searchRoleGroup( inRoleGroupForm,
                                                                           ServiceParameter.SEARCH.getParameter() );
                        inRoleGroupForm.setGroupsPojos( masterDataDto.getGroupsPojoList() );
                        messages.add( "successAppMessage", new ActionMessage( statusCode ) );
                    }
                    else
                    {
                        LOGGER.info( "statusCode::::::" + statusCode );
                        errros.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    LOGGER.info( "Master Dto null" );
                    errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                }
            }
            else
            {
                LOGGER.info( "User Dto null" );
                errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
            }
        }
        catch ( Exception e )
        {
            errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action modifyRoleGroup", e );
        }
        LOGGER.info( "Exiting RoleGroupAction: deleteRoleGroup--" );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, messages );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward showRolesDetails( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        if ( CRMCacheManager.getCrmRolePojos().size() > 0 )
        {
            inRequest.setAttribute( CrmRolesPojo.class.getSimpleName(), CRMCacheManager.getCrmRolePojos() );
        }
        return inMapping.findForward( IActionForwardConstant.SHOW_ROLES_DETAILS );
    }
}
