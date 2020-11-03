package com.np.tele.crm.usrmngmnt.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmUserMappingPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.bm.IEWUserMappingMgr;
import com.np.tele.crm.usrmngmnt.forms.EWUserMappingForm;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;

public class EWUserMappingAction
    extends DispatchAction
{
    private IEWUserMappingMgr   ewUserMappingMgr;
    private static final Logger LOGGER = Logger.getLogger( EWUserMappingAction.class );

    public IEWUserMappingMgr getEwUserMappingMgr()
    {
        return ewUserMappingMgr;
    }

    public void setEwUserMappingMgr( IEWUserMappingMgr inEwUserMappingMgr )
    {
        ewUserMappingMgr = inEwUserMappingMgr;
    }

    public ActionForward searchEWUserPage( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.SEARCH_EW_USER_PAGE );
    }

    public ActionForward searchUser( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
    {
        EWUserMappingForm mappingForm = (EWUserMappingForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages error = getErrors( inRequest );
        LOGGER.info( "Going to search User by User ID: " + mappingForm.getUserId() );
        commonMethodForSearchUser( inRequest, mappingForm, messages, error );
        return inMapping.findForward( IActionForwardConstant.SEARCH_EW_USER_PAGE );
    }

    public ActionForward searchEWUserMapping( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        EWUserMappingForm mappingForm = (EWUserMappingForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        LOGGER.info( "Hidden User List: " + mappingForm.getHiddenUserList() );
        if ( StringUtils.isNotBlank( mappingForm.getHiddenUserList() ) )
        {
            if ( !StringUtils.isValidObj( mappingForm.getCrmUserMappingPojosList() ) )
            {
                mappingForm.setCrmUserMappingPojosList( new ArrayList<CrmUserMappingPojo>() );
            }
            if ( StringUtils.contains( mappingForm.getHiddenUserList(), IAppConstants.COMMA ) )
            {
                String[] userMap = mappingForm.getHiddenUserList().split( IAppConstants.COMMA );
                for ( int i = 0; i < userMap.length; i++ )
                {
                    LOGGER.info( "ID OTHER GOT:" + mappingForm.getHiddenUserId() + "ID USERMAP OTHER" + userMap[i] );
                    if ( !StringUtils.equals( userMap[i], mappingForm.getHiddenUserId() ) )
                    {
                        LOGGER.info( "ID GOT:" + mappingForm.getHiddenUserId() );
                        CrmUserMappingPojo crmUserMappingPojo = new CrmUserMappingPojo();
                        crmUserMappingPojo.setUserId( mappingForm.getHiddenUserId() );
                        crmUserMappingPojo.setMappedUserId( userMap[i] );
                        crmUserMappingPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        crmUserMappingPojo.setMappingType( mappingForm.getMappingType() );
                        crmUserMappingPojo.setEditable( true );
                        mappingForm.getCrmUserMappingPojosList().add( crmUserMappingPojo );
                    }
                }
            }
            else
            {
                if ( !StringUtils.equals( mappingForm.getHiddenUserId(), mappingForm.getHiddenUserList() ) )
                {
                    LOGGER.info( "ID in else" + mappingForm.getHiddenUserId() );
                    CrmUserMappingPojo crmUserMappingPojo = new CrmUserMappingPojo();
                    crmUserMappingPojo.setUserId( mappingForm.getHiddenUserId() );
                    crmUserMappingPojo.setMappedUserId( mappingForm.getHiddenUserList() );
                    crmUserMappingPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmUserMappingPojo.setMappingType( mappingForm.getMappingType() );
                    crmUserMappingPojo.setEditable( true );
                    mappingForm.getCrmUserMappingPojosList().add( crmUserMappingPojo );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EW_SAME_USER ) );
                }
            }
            mappingForm
                    .setCrmUserMappingPojosList( new ArrayList<CrmUserMappingPojo>( new HashSet<CrmUserMappingPojo>( mappingForm
                            .getCrmUserMappingPojosList() ) ) );
            mappingForm.setHiddenUserList( null );
        }
        else
        {
            CrmuserDetailsDto crmUserDetailsDto = getEwUserMappingMgr().searchEWUserMapping( mappingForm );
            if ( StringUtils.isValidObj( crmUserDetailsDto ) )
            {
                String statusCode = crmUserDetailsDto.getStatusCode();
                if ( StringUtils.isNotEmpty( statusCode ) )
                {
                    LOGGER.info( "Response status code::" + statusCode + "Response status desc::"
                            + crmUserDetailsDto.getStatusDesc() );
                    if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                    {
                        Set<CrmUserMappingPojo> crmUserMappingPojosList = new HashSet<CrmUserMappingPojo>( crmUserDetailsDto
                                .getCrMappingPojosList() );
                        LOGGER.info( "Size of User List: " + crmUserMappingPojosList.size() );
                        if ( !crmUserMappingPojosList.isEmpty() )
                        {
                            LOGGER.info( "Set List On Form" );
                            if ( !StringUtils.isValidObj( mappingForm.getCrmUserMappingPojosList() ) )
                            {
                                mappingForm.setCrmUserMappingPojosList( new ArrayList<CrmUserMappingPojo>() );
                            }
                            mappingForm.getCrmUserMappingPojosList().addAll( crmUserMappingPojosList );
                            mappingForm
                                    .setCrmUserMappingPojosList( new ArrayList<CrmUserMappingPojo>( new HashSet<CrmUserMappingPojo>( mappingForm
                                            .getCrmUserMappingPojosList() ) ) );
                        }
                        else
                        {
                            mappingForm.setCrmUserMappingPojosList( new ArrayList<CrmUserMappingPojo>() );
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
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.EW_USER_MAPPING_PAGE );
    }

    public ActionForward selectMappedUser( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        EWUserMappingForm mappingForm = (EWUserMappingForm) inForm;
        mappingForm.setLeadStagesList( CRMCacheManager.getFunctionalBinsContent() );
        mappingForm.setStatusList( CRMUtils.getFullUserStatusList() );
        return inMapping.findForward( IActionForwardConstant.SEARCH_USER_POP_UP );
    }

    public ActionForward searchUserPopUp( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        EWUserMappingForm mappingForm = (EWUserMappingForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages error = getErrors( inRequest );
        mappingForm.setLeadStagesList( CRMCacheManager.getFunctionalBinsContent() );
        mappingForm.setStatusList( CRMUtils.getFullUserStatusList() );
        LOGGER.info( "Going to search User by User ID: " + mappingForm.getUserId() );
        commonMethodForSearchUser( inRequest, mappingForm, messages, error );
        return inMapping.findForward( IActionForwardConstant.SEARCH_USER_POP_UP );
    }

    public ActionForward createMappedUser( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        EWUserMappingForm mappingForm = (EWUserMappingForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getMessages( inRequest );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        CrmuserDetailsDto detailsDto = getEwUserMappingMgr().createEWUserMapping( mappingForm,
                                                                                  userDto.getCrmUserDetailsPojo()
                                                                                          .getUserId() );
        String statusCode = detailsDto.getStatusCode();
        LOGGER.info( "Status Code: " + detailsDto.getStatusCode() );
        if ( StringUtils.isNotEmpty( statusCode ) )
        {
            if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Successfully Insert/Update Ecalation Workflow user mapping." );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                CrmuserDetailsDto crmUserDetailsDto = getEwUserMappingMgr().searchEWUserMapping( mappingForm );
                if ( StringUtils.isValidObj( crmUserDetailsDto.getCrMappingPojosList() ) )
                {
                    mappingForm.setCrmUserMappingPojosList( crmUserDetailsDto.getCrMappingPojosList() );
                }
            }
            else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM993.getStatusCode() ) )
            {
                LOGGER.info( detailsDto.getStatusDesc() );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
            else
            {
                LOGGER.info( "Somthing has wrong in Insert/Update Ecalation Workflow user mapping." );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.EW_USER_MAPPING_PAGE );
    }

    public ActionForward deleteUserMappingRow( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        EWUserMappingForm mappingForm = (EWUserMappingForm) inForm;
        LOGGER.info( "Index ::" + mappingForm.getRowCounter() );
        mappingForm.getCrmUserMappingPojosList().remove( mappingForm.getRowCounter() );
        LOGGER.info( "size after remove :: " + mappingForm.getCrmUserMappingPojosList().size() );
        return inMapping.findForward( IActionForwardConstant.EW_USER_MAPPING_PAGE );
    }

    private void commonMethodForSearchUser( final HttpServletRequest inRequest,
                                            EWUserMappingForm mappingForm,
                                            ActionMessages messages,
                                            ActionMessages error )
    {
        CrmuserDetailsDto detailsDto = getEwUserMappingMgr().searchUserById( mappingForm );
        if ( StringUtils.isValidObj( detailsDto ) )
        {
            String statusCode = detailsDto.getStatusCode();
            if ( StringUtils.isNotEmpty( statusCode ) )
            {
                LOGGER.info( "Response status code::" + statusCode + "Response status desc::"
                        + detailsDto.getStatusDesc() );
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    List<CrmUserPojo> crmUserList = detailsDto.getCrmUserDetailsPojoList();
                    LOGGER.info( "Size of User List: " + crmUserList.size() );
                    if ( !crmUserList.isEmpty() )
                    {
                        LOGGER.info( "Set List On Form" );
                        mappingForm.setCrmUserPojos( crmUserList );
                        mappingForm.setUserPojoSize( crmUserList.size() );
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
                error.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
        }
        else
        {
            LOGGER.info( "Get Value NULL for crmuserdetailsdto from business manager" );
            error.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
        }
        saveErrors( inRequest, error );
        saveMessages( inRequest, messages );
    }
}
