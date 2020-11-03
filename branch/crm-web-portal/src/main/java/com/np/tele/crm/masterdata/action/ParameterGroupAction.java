package com.np.tele.crm.masterdata.action;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.forms.ParameterGroupForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;

public class ParameterGroupAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( ParameterGroupAction.class );
    private IMasterBMngr        masterDataBm;

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public ActionForward createParameterGroupPage( final ActionMapping inMapping,
                                                   ActionForm inForm,
                                                   HttpServletRequest inRequest,
                                                   HttpServletResponse inResponse )
    {
        List<AccessGroupPojo> accessGroupPojoList = new ArrayList<AccessGroupPojo>();
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        List<CrmParameterPojo> newCrmParameterList = new ArrayList<CrmParameterPojo>();
        List<CrmParameterPojo> crmParameterList = getMasterDataBm()
                .getParameters( 0, CRMParameter.USER, CRMParameter.INTERNAL.getParameter() );
        AccessGroupPojo pojo = new AccessGroupPojo();
        pojo.setCrmParameter( new CrmParameterPojo() );
        pojo.setEditable( true );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        accessGroupPojoList.add( pojo );
        for ( CrmParameterPojo crmParameterPojo : crmParameterList )
        {
            if ( !crmParameterPojo.getParameterName().equalsIgnoreCase( "Area" ) )
            {
                newCrmParameterList.add( crmParameterPojo );
            }
        }
        parameterGroupForm.setParameterPojos( newCrmParameterList );
        parameterGroupForm.setAccessGroups( accessGroupPojoList );
        return inMapping.findForward( IActionForwardConstant.CREATE_PARAMETER_GROUP_PAGE );
    }

    public ActionForward addParameterGroupRow( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        AccessGroupPojo pojo = new AccessGroupPojo();
        pojo.setCrmParameter( new CrmParameterPojo() );
        pojo.setEditable( true );
        pojo.setRecordId( 0l );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        if ( !StringUtils.isValidObj( parameterGroupForm.getAccessGroups() ) )
        {
            parameterGroupForm.setAccessGroups( new ArrayList<AccessGroupPojo>() );
        }
        parameterGroupForm.getAccessGroups().add( 0, pojo );
        LOGGER.info( "ParametereGroupAction AccessGroups:" + parameterGroupForm.getAccessGroups() );
        return inMapping.findForward( IActionForwardConstant.CREATE_PARAMETER_GROUP_PAGE );
    }

    public ActionForward createParameterGroup( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.CREATE_PARAMETER_GROUP_PAGE;
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LOGGER.info( "parameterGroupname:" + parameterGroupForm.getParameterGroupName()
                + "\nparameterGroupDescription:" + parameterGroupForm.getParameterGroupDescription() );
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                MasterDataDto masterDataDto = getMasterDataBm().createParameterGroup( parameterGroupForm,
                                                                                      userDto.getCrmUserDetailsPojo()
                                                                                              .getUserId() );
                if ( null != masterDataDto )
                {
                    String statusCode = masterDataDto.getStatusCode();
                    if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESSFULLY_PARAMETER_CREATED,
                                                         masterDataDto.getGroupsPojo().getGroupName() ) );
                        forwardPage = IActionForwardConstant.SEARCH_PARAMETER_GROUP;
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
            }
            else
            {
                LOGGER.info( "user dto in session null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured createParameterGroup", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward searchParameterGroupPage( ActionMapping inMapping,
                                                   ActionForm inForm,
                                                   HttpServletRequest inRequest,
                                                   HttpServletResponse inResponse )
    {
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
        parameterGroupForm.setSearchStatus( null );
        parameterGroupForm.setStatusList( statusList );
        return inMapping.findForward( IActionForwardConstant.SEARCH_PARAMETER_GROUP_PAGE );
    }

    public ActionForward searchParameterGroup( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        try
        {
            MasterDataDto masterDataDto = getMasterDataBm().searchParameterGroup( parameterGroupForm,
                                                                                  ServiceParameter.SEARCH
                                                                                          .getParameter() );
            if ( null != masterDataDto )
            {
                String statusCode = masterDataDto.getStatusCode();
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( masterDataDto.getGroupsPojoList().isEmpty() )
                    {
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                    // else
                    //  {
                    parameterGroupForm.setGroupsPojos( masterDataDto.getGroupsPojoList() );
                    //  }
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
            parameterGroupForm.setStatusList( statusList );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action searchParameterGroup", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.SEARCH_PARAMETER_GROUP_PAGE );
    }

    /* public ActionForward listParameterGroup( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
     {
         LOGGER.info( "Entering ParameterGroupAction: listParameterGroup--" );
         LOGGER.info( "Exiting ParameterGroupAction: listParameterGroup--" );
         return inMapping.findForward( IActionForwardConstant.SEARCH_PARAMETER_GROUP_PAGE );
     }*/
    public ActionForward viewParameterGroup( ActionMapping inMapping,
                                             ActionForm inForm,
                                             HttpServletRequest inRequest,
                                             HttpServletResponse inResponse )
    {
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        List<GroupsPojo> crmGroupPojoList = parameterGroupForm.getGroupsPojos();
        LOGGER.info( "record id found" + parameterGroupForm.getParameterGroupId() );
        GroupsPojo groupPojo = new GroupsPojo();
        groupPojo.setGroupId( Long.parseLong( inRequest.getParameter( "inGroupId" ) ) );
        //groupPojo.setGroupId( parameterGroupForm.getParameterGroupId() );
        int pojoIndex = crmGroupPojoList.indexOf( groupPojo );
        if ( pojoIndex >= 0 )
        {
            groupPojo = crmGroupPojoList.get( pojoIndex );
            parameterGroupForm.setGroupPojo( crmGroupPojoList.get( pojoIndex ) );
            parameterGroupForm.setAccessGroups( groupPojo.getAccessGroups() );
        }
        return inMapping.findForward( IActionForwardConstant.VIEW_PARAMETER_GROUP );
    }

    //searchParameter(){}
    public ActionForward changeParameterGroupStatus( ActionMapping inMapping,
                                                     ActionForm inForm,
                                                     HttpServletRequest inRequest,
                                                     HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.SEARCH_PARAMETER_GROUP_PAGE;
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                LOGGER.info( "Parameter GroupId" + parameterGroupForm.getParameterGroupId() );
                LOGGER.info( "Role status" + parameterGroupForm.getStatus() );
                MasterDataDto masterDataDto = getMasterDataBm()
                        .changeParameterGroupStatus( parameterGroupForm, CRMParameter.CHANGE_STATUS.getParameter(),
                                                     userDto.getCrmUserDetailsPojo().getUserId() );
                if ( null != masterDataDto )
                {
                    String statusCode = masterDataDto.getStatusCode();
                    if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        masterDataDto = getMasterDataBm().searchParameterGroup( parameterGroupForm,
                                                                                ServiceParameter.SEARCH.getParameter() );
                        parameterGroupForm.setGroupsPojos( masterDataDto.getGroupsPojoList() );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( "CRM001" ) );
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
            }
            else
            {
                LOGGER.info( "User Dto null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                forwardPage = IActionForwardConstant.LOGIN;
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error Occured in action changeParameterGroupStatus", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward deleteParameterGroupRow( ActionMapping inMapping,
                                                  ActionForm inForm,
                                                  HttpServletRequest inRequest,
                                                  HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
            if ( parameterGroupForm.getAccessGroups().size() > 1 )
            {
                parameterGroupForm.getAccessGroups().remove( parameterGroupForm.getRowCounter() );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_ONE_PARAMETER_REQUIRED ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.info( "Error in DeleteParameterGroupRow Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CREATE_PARAMETER_GROUP_PAGE );
    }

    public ActionForward modifyParameterGroupPage( ActionMapping inMapping,
                                                   ActionForm inForm,
                                                   HttpServletRequest inRequest,
                                                   HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.CREATE_PARAMETER_GROUP_PAGE;
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            List<GroupsPojo> crmGroupPojoList = parameterGroupForm.getGroupsPojos();
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupId( parameterGroupForm.getParameterGroupId() );
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
                parameterGroupForm.setParameterGroupName( groupPojo.getGroupName() );
                parameterGroupForm.setParameterGroupDescription( groupPojo.getGroupDescription() );
                parameterGroupForm.setGroupPojo( groupPojo );
                parameterGroupForm.setAccessGroups( accessGroups );
            }
            parameterGroupForm.setParameterPojos( getMasterDataBm()
                    .getParameters( 0, CRMParameter.USER, CRMParameter.INTERNAL.getParameter() ) );
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error occured in action", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward modifyParameterGroup( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.CREATE_PARAMETER_GROUP_PAGE;
        ParameterGroupForm parameterGroupForm = (ParameterGroupForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            LOGGER.info( "GroupName:" + parameterGroupForm.getParameterGroupName() + "\nGroupDescription:"
                    + parameterGroupForm.getParameterGroupDescription() );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( null != userDto )
            {
                MasterDataDto masterDataDto = getMasterDataBm().modifyParameterGroup( parameterGroupForm,
                                                                                      userDto.getCrmUserDetailsPojo()
                                                                                              .getUserId() );
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
                        parameterGroupForm.setParameterGroupName( groupPojo.getGroupName() );
                        parameterGroupForm.setParameterGroupDescription( groupPojo.getGroupDescription() );
                        parameterGroupForm.setGroupPojo( groupPojo );
                        parameterGroupForm.setAccessGroups( accessGroups );
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_UPDATE_PARAMETERGROUP ) );
                    }
                    else
                    {
                        LOGGER.info( "statusCode::::::" + statusCode );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    LOGGER.info( "Master Dto null" );
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
            LOGGER.info( "Error occured in action modifyRoleGroup", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward showDetails( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        List<CrmParameterPojo> crmParameterList = getMasterDataBm()
                .getParameters( 0, CRMParameter.USER, CRMParameter.INTERNAL.getParameter() );
        //Start# for removing area entry from parameters list show parameters page
        for ( Iterator<CrmParameterPojo> iter = crmParameterList.listIterator(); iter.hasNext(); )
        {
            CrmParameterPojo crmParameterPojo = iter.next();
            if ( crmParameterPojo.getParameterName().equalsIgnoreCase( IAppConstants.AREA ) )
            {
                iter.remove();
            }
        }
        //End#
        inRequest.setAttribute( CrmParameterPojo.class.getSimpleName(), crmParameterList );
        return inMapping.findForward( IActionForwardConstant.SHOW_PARAMETER_DETAILS );
    }
}
