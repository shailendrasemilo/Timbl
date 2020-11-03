package com.np.tele.crm.masterdata.action;

import java.util.ArrayList;
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
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.masterdata.bm.IRgstrExtrnlProject;
import com.np.tele.crm.masterdata.forms.CrmProjectForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;

public class CrmProjectAction
    extends DispatchAction
{
    private static final Logger LOGGER                = Logger.getLogger( CrmProjectAction.class );
    private IRgstrExtrnlProject rgstrExtrnlProjectImp = null;

    public IRgstrExtrnlProject getRgstrExtrnlProjectImp()
    {
        return rgstrExtrnlProjectImp;
    }

    public void setRgstrExtrnlProjectImp( IRgstrExtrnlProject inRgstrExtrnlProjectImp )
    {
        rgstrExtrnlProjectImp = inRgstrExtrnlProjectImp;
    }

    public ActionForward rgstrExtrnlPrjctPg( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "Entering CrmProjectAction: rgstrExtrnlPrjctPg" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        CrmProjectForm projectForm = (CrmProjectForm) inForm;
        projectForm.setParameterTypes( getRgstrExtrnlProjectImp().getParameterType() );
        List<CrmParameterPojo> crmParameterPojos = new ArrayList<CrmParameterPojo>();
        CrmParameterPojo crmParameterPojo = new CrmParameterPojo();
        crmParameterPojo.setEditable( true );
        crmParameterPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
        crmParameterPojo.setParameterGroup( CRMParameter.ALERT.getParameter() );
        crmParameterPojo.setParameterGroupType( CRMParameter.TEMPLATE.getParameter() );
        crmParameterPojos.add( crmParameterPojo );
        projectForm.setRegValue( true );
        projectForm.setCrmParameters( crmParameterPojos );
        LOGGER.info( "Exiting CrmProjectAction: rgstrExtrnlPrjctPg" );
        return inMapping.findForward( IActionForwardConstant.EXTERNAL_PROJECT_PAGE );
    }

    public ActionForward addExtProjectParameterRow( ActionMapping mapping,
                                                    ActionForm form,
                                                    HttpServletRequest request,
                                                    HttpServletResponse response )
        throws Exception
    {
        LOGGER.info( "Entering CrmProjectAction: addExtProjectParameterRow" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        CrmProjectForm projectForm = (CrmProjectForm) form;
        if ( !StringUtils.isValidObj( projectForm.getCrmParameters() ) )
        {
            projectForm.setCrmParameters( new ArrayList<CrmParameterPojo>() );
        }
        CrmParameterPojo crmParameterPojo = new CrmParameterPojo();
        crmParameterPojo.setEditable( true );
        crmParameterPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
        crmParameterPojo.setParameterGroup( CRMParameter.ALERT.getParameter() );
        crmParameterPojo.setParameterGroupType( CRMParameter.TEMPLATE.getParameter() );
        LOGGER.info( "Size of list :" + projectForm.getCrmParameters().size() );
        projectForm.getCrmParameters().add( 0, crmParameterPojo );
        LOGGER.info( "Exiting CrmProjectAction: addParameterR:" );
        return mapping.findForward( IActionForwardConstant.EXTERNAL_PROJECT_PAGE );
    }

    public ActionForward rgstrExtrnlProject( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
        throws Exception
    {
        MasterDataDto masterDataDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_PAGE;
        String statusCode;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            CrmProjectForm projectForm = (CrmProjectForm) inForm;
            LOGGER.info( "User Id :" + userDto.getCrmUserDetailsPojo().getUserId() );
            masterDataDto = getRgstrExtrnlProjectImp().rgstrExtrnlProject( projectForm,
                                                                           userDto.getCrmUserDetailsPojo().getUserId() );
            statusCode = masterDataDto.getStatusCode();
            LOGGER.info( "Status Code :" + statusCode );
            if ( null != statusCode )
            {
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Registeration of external project has been successfully save" );
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESSFULLY_PROJECT_CREATED, masterDataDto
                                          .getProjectsPojo().getProjectName() ) );
                    forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_SEARCH;
                }
                else if ( statusCode.equals( CRMServiceCode.CRM054.getStatusCode() ) )
                {
                    LOGGER.info( "Project Name Already Exist" );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_PAGE;
                }
                else
                {
                    LOGGER.info( "Somthing has wrong in register external project.... " );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_PAGE;
                }
            }
            else
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_PAGE;
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while register external project", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward searchExtrnlPrjctPg( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
        throws Exception
    {
        LOGGER.info( "Entering CrmProjectAction: searchExtrnlPrjctPg" );
        CrmProjectForm projectForm = (CrmProjectForm) form;
        projectForm.setDirectSearch( true );
        projectForm.setSearchStatus( null );
        List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
        projectForm.setStatusList( statusList );
        return mapping.findForward( IActionForwardConstant.SEARCH_EXTERNAL_PROJECT );
    }

    public ActionForward searchExtrnlProject( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
        throws Exception
    {
        MasterDataDto masterDataDto = null;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        String statusCode;
        try
        {
            CrmProjectForm projectForm = (CrmProjectForm) form;
            projectForm.setDirectSearch( false );
            masterDataDto = getRgstrExtrnlProjectImp().searchRgstrExtrnlProject( projectForm );
            if ( null != masterDataDto )
            {
                statusCode = masterDataDto.getStatusCode();
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( masterDataDto.getProjectsPojos().isEmpty() )
                    {
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                    //else
                    //{
                    projectForm.setProjectList( masterDataDto.getProjectsPojos() );
                    //}
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
            List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
            projectForm.setStatusList( statusList );
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while search external project", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.SEARCH_EXTERNAL_PROJECT );
    }

    public ActionForward viewCRMProject( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        return mapping.findForward( IActionForwardConstant.EXTERNAL_PROJECT_PAGE );
    }

    public ActionForward modifyCRMProject( ActionMapping mapping,
                                           ActionForm form,
                                           HttpServletRequest request,
                                           HttpServletResponse response )
        throws Exception
    {
        MasterDataDto masterDataDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String statusCode;
        String forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_PAGE;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            CrmProjectForm projectForm = (CrmProjectForm) form;
            masterDataDto = getRgstrExtrnlProjectImp().modifyExtrProject( projectForm,
                                                                          userDto.getCrmUserDetailsPojo().getUserId() );
            statusCode = masterDataDto.getStatusCode();
            LOGGER.info( "Status Code : " + statusCode );
            if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "External project modify successfully..." );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( IPropertiesConstant.SUCCESS_UPDATE_PROJECT,
                                                                            masterDataDto.getProjectsPojo()
                                                                                    .getProjectName() ) );
                forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_SEARCH;
            }
            else
            {
                LOGGER.info( "somthing has wrong in modify crm project..." );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_PAGE;
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Getting Error while modify external project", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward changeExtrnalProjectStatus( ActionMapping mapping,
                                                     ActionForm form,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response )
        throws Exception
    {
        MasterDataDto masterDataDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.SEARCH_EXTERNAL_PROJECT;
        try
        {
            HttpSession session = request.getSession( false );
            CrmProjectForm projectForm = (CrmProjectForm) form;
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) session.getAttribute( IAppConstants.CRM_USER_OBJECT );
            masterDataDto = getRgstrExtrnlProjectImp().changeExtrnalProjectStatus( projectForm,
                                                                                   userDto.getCrmUserDetailsPojo()
                                                                                           .getUserId() );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), masterDataDto.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( masterDataDto.getStatusCode() ) );
                forwardPage = IActionForwardConstant.EXTERNAL_PROJECT_SEARCH;
            }
            else
            {
                LOGGER.info( "somthing has wrong in modify status of external project ..."
                        + masterDataDto.getStatusCode() );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( masterDataDto.getStatusCode() ) );
                forwardPage = IActionForwardConstant.SEARCH_EXTERNAL_PROJECT;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured while active and deactive external project", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward editExtrnalProject( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
        throws Exception
    {
        LOGGER.info( "Inside editExtrnalProject" );
        CrmProjectForm projectForm = (CrmProjectForm) form;
        ProjectsPojo projectsPojo = new ProjectsPojo();
        projectForm.setParameterTypes( getRgstrExtrnlProjectImp().getParameterType() );
        projectsPojo.setProjectId( projectForm.getProjectId() );
        int pojoIndex = projectForm.getProjectList().indexOf( projectsPojo );
        if ( pojoIndex >= 0 )
        {
            projectsPojo = projectForm.getProjectList().get( pojoIndex );
            projectForm.setRegValue( false );
            projectForm.setExtProjectName( projectsPojo.getProjectName() );
            projectForm.setProjectDesc( projectsPojo.getProjectDescription() );
            projectForm.setCrmParameters( projectsPojo.getCrmParameterPojosSet() );
            projectForm.setProjectPojo( projectsPojo );
        }
        LOGGER.info( "Size Of LIST:--------" + projectForm.getProjectList().size() );
        return mapping.findForward( IActionForwardConstant.EXTERNAL_PROJECT_PAGE );
    }

    public ActionForward deleteExtProjectParameterRow( ActionMapping inMapping,
                                                       ActionForm inForm,
                                                       HttpServletRequest inRequest,
                                                       HttpServletResponse inResponse )
    {
        CrmProjectForm projectForm = (CrmProjectForm) inForm;
        LOGGER.info( "Index ::" + projectForm.getRowCounter() );
        LOGGER.info( "size before remove :: " + projectForm.getCrmParameters().size() );
        projectForm.getCrmParameters().remove( projectForm.getRowCounter() );
        LOGGER.info( "size after remove :: " + projectForm.getCrmParameters().size() );
        return inMapping.findForward( IActionForwardConstant.EXTERNAL_PROJECT_PAGE );
    }
}
