package com.np.tele.crm.alerts.action;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

import com.np.tele.crm.alerts.bm.IEmailAlert;
import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.EmailCcBccPojo;
import com.np.tele.crm.service.client.EmailServerPojo;
import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class EmailAlertAction
    extends DispatchAction
{
    private static final Logger LOGGER           = Logger.getLogger( EmailAlertAction.class );
    private IEmailAlert         emailAlert       = null;
    private IMasterBMngr        masterDataBm     = null;
    private ICrmConfigManager   crmConfigManager = null;

    public IEmailAlert getEmailAlert()
    {
        return emailAlert;
    }

    public void setEmailAlert( IEmailAlert inEmailAlertImp )
    {
        emailAlert = inEmailAlertImp;
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr inMasterDataBm )
    {
        masterDataBm = inMasterDataBm;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ActionForward newEmailTemplate( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
        throws Exception
    {
        AlertsForm alertsForm = (AlertsForm) inForm;
        List<EmailServerPojo> emailServerList = getCrmConfigManager().getEmailServers();
        if ( CommonValidator.isValidCollection( emailServerList ) )
        {
            Iterator<EmailServerPojo> emailServerItr = emailServerList.iterator();
            while ( emailServerItr.hasNext() )
            {
                EmailServerPojo emailServerPojo = emailServerItr.next();
                if ( !emailServerPojo.isEnable() )
                {
                    emailServerItr.remove();
                }
            }
        }
        SortingComparator<EmailServerPojo> sortComparator = new SortingComparator<EmailServerPojo>( "subCategory" );
        Collections.sort( emailServerList, sortComparator );
        sortComparator = null;
        alertsForm.setEmailServers( emailServerList );
        return inMapping.findForward( IActionForwardConstant.CREATE_EMAIL_TEMPLATE );
    }

    public ActionForward createEmailTemplate( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
        throws Exception
    {
        AlertsDto alertDto = null;
        boolean isValid = true;
        String target = IActionForwardConstant.SEARCH_VIEW_EMAIL;
        ActionMessages actionMessages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            AlertsForm alertsForm = (AlertsForm) inForm;
            List<EmailCcBccPojo> emailCcBccList = new ArrayList<EmailCcBccPojo>();
            if ( isValid )
            {
                isValid = getEmailAlert().setCCBcc( alertsForm.getEmailCc(), CRMParameter.CC.getParameter(),
                                                    emailCcBccList );
            }
            if ( isValid )
            {
                isValid = getEmailAlert().setCCBcc( alertsForm.getEmailBcc(), CRMParameter.BCC.getParameter(),
                                                    emailCcBccList );
            }
            LOGGER.info( "CC BCC List :" + emailCcBccList );
            if ( isValid )
            {
                EmailTemplatePojo emailTemplatePojo = new EmailTemplatePojo();
                emailTemplatePojo.getEmailCcBccs().addAll( emailCcBccList );
                emailTemplatePojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                alertsForm.setTemplateType( IAppConstants.EMAIL );
                alertDto = getEmailAlert().createEmailTemplate( alertsForm, emailTemplatePojo );
                alertsForm.setProjects( getMasterDataBm().getProjectList( alertsForm.getProjectType() ) );
                alertsForm.setStatusList( CRMUtils.getAciveInactiveStatusList() );
                LOGGER.info( "Status Code :" + alertDto.getStatusCode() );
                if ( !alertDto.getStatusCode().equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( " Unable to Process Request Status Code " + alertDto.getStatusCode()
                            + " & Status Desc:" + alertDto.getStatusDesc() );
                    errors.add( "emailTemplate", new ActionMessage( alertDto.getStatusCode() ) );
                    target = IActionForwardConstant.CREATE_EMAIL_TEMPLATE;
                }
                else
                {
                    actionMessages.add( IAppConstants.APP_MESSAGE,
                                        new ActionMessage( IPropertiesConstant.SUCCESSFULLY_TEMPLATE_CREATED,
                                                           StringUtils.capitalize( IAppConstants.EMAIL ),
                                                           alertDto.getEmailTemplatePojo().getEmailTemplateName() ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_MESSAGE, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "error occured while creting email templete", ex );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, actionMessages );
        return inMapping.findForward( target );
    }

    public ActionForward viewEmailTemplate( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {
        AlertsForm alertsForm = (AlertsForm) form;
        EmailTemplatePojo emailTemplatePojo = new EmailTemplatePojo();
        emailTemplatePojo.setEmailTemplateId( alertsForm.getTemplateId() );
        List<EmailServerPojo> emailServerList = getCrmConfigManager().getEmailServers();
        if ( CommonValidator.isValidCollection( emailServerList ) )
        {
            Iterator<EmailServerPojo> emailServerItr = emailServerList.iterator();
            while ( emailServerItr.hasNext() )
            {
                EmailServerPojo emailServerPojo = emailServerItr.next();
                if ( !emailServerPojo.isEnable() )
                {
                    emailServerItr.remove();
                }
            }
        }
        alertsForm.setEmailServers( emailServerList );
        int pojoIndex = alertsForm.getEmailTemplateList().indexOf( emailTemplatePojo );
        if ( pojoIndex >= 0 )
        {
            EmailTemplatePojo emailpojo = alertsForm.getEmailTemplateList().get( pojoIndex );
            alertsForm.setEmailFrom( emailpojo.getEmailFrom() );
            alertsForm.setTemplateName( emailpojo.getEmailTemplateName() );
            alertsForm.setProjectName( emailpojo.getProjects().getProjectName() );
            alertsForm.setProjectId( emailpojo.getProjects().getProjectId() );
            alertsForm.setEmailSubject( emailpojo.getEmailSubject() );
            alertsForm.setEmailServer( emailpojo.getEmailServer() );
            getEmailAlert().setCCBCCtoForm( alertsForm, emailpojo.getEmailCcBccs() );
            byte[] temp = emailpojo.getEmailTemplate();
            String emailBody = new String( temp );
            alertsForm.setEmailTemplateBody( emailBody );
            alertsForm.setParameterList( getMasterDataBm().getParameters( emailpojo.getProjects().getProjectId(),
                                                                          CRMParameter.ALERT, null ) );
            alertsForm.setEmailTemplatePojo( emailpojo );
        }
        LOGGER.info( "Size Of LIST:--------" + alertsForm.getEmailTemplateList().size() );
        return mapping.findForward( IActionForwardConstant.CREATE_EMAIL_TEMPLATE );
    }

    public ActionForward exportEmailTemplate( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
        throws Exception
    {
        OutputStream out = null;
        try
        {
            AlertsForm alertsForm = (AlertsForm) form;
            EmailTemplatePojo tmpEmailPojo = new EmailTemplatePojo();
            tmpEmailPojo.setEmailTemplateId( alertsForm.getTemplateId() );
            int pojoIndex = alertsForm.getEmailTemplateList().indexOf( tmpEmailPojo );
            if ( pojoIndex >= 0 )
            {
                EmailTemplatePojo emailTemplatePojo = alertsForm.getEmailTemplateList().get( pojoIndex );
                if ( StringUtils.isValidObj( emailTemplatePojo ) )
                {
                    response.setHeader( "Content-type", "application/download" );
                    response.setHeader( "Content-disposition",
                                        "inline; filename=" + emailTemplatePojo.getEmailTemplateName() + ".html" );
                    byte[] body = emailTemplatePojo.getEmailTemplate();
                    out = response.getOutputStream();
                    out.write( body );
                    out.close();
                    response.flushBuffer();
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( " Failed to generate email Template Reason ", ex );
        }
        return null;
    }

    public ActionForward searchAndViewEmailTemplate( ActionMapping mapping,
                                                     ActionForm form,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response )
        throws Exception
    {
        AlertsDto alertDto = null;
        List<EmailTemplatePojo> emailTemplateList = null;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        String target = IActionForwardConstant.SEARCH_TEMPLATE;
        try
        {
            AlertsForm alertsForm = (AlertsForm) form;
            alertsForm.setProjects( getMasterDataBm().getProjectList( alertsForm.getProjectType() ) );
            alertDto = getEmailAlert().searchAndViewEmailTemplate( alertsForm );
            LOGGER.info( "Status Code " + alertDto.getStatusCode() );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), alertDto.getStatusCode() ) )
            {
                emailTemplateList = alertDto.getEmailTemplatePojos();
                LOGGER.info( "Size of emailTemplateList" + emailTemplateList.size() );
                alertsForm.setEmailTemplateList( emailTemplateList );
                if ( emailTemplateList.size() < 1 )
                {
                    LOGGER.info( "Record not found..  " );
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "error occured while search and view email template", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( target );
    }

    public ActionForward activeAndDeactiveEmailTemplate( ActionMapping mapping,
                                                         ActionForm form,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response )
        throws Exception
    {
        AlertsDto alertDto = null;
        List<EmailTemplatePojo> emailTemplateList = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            HttpSession session = request.getSession( false );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) session.getAttribute( IAppConstants.CRM_USER_OBJECT );
            AlertsForm alertsForm = (AlertsForm) form;
            EmailTemplatePojo emailTemplatePojo = new EmailTemplatePojo();
            emailTemplatePojo.setEmailTemplateId( alertsForm.getTemplateId() );
            int pojoIndex = alertsForm.getEmailTemplateList().indexOf( emailTemplatePojo );
            if ( pojoIndex >= 0 )
            {
                EmailTemplatePojo emailpojo = alertsForm.getEmailTemplateList().get( pojoIndex );
                if ( StringUtils.isValidObj( emailpojo ) )
                {
                    emailpojo.setStatus( alertsForm.getStatus() );
                    emailpojo.setLastModifiedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                    alertDto = getEmailAlert().activeAndDeactiveEmailTemplate( emailpojo );
                }
            }
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), alertDto.getStatusCode() ) )
            {
                LOGGER.info( "email Template modify status successfully..." );
                alertDto = getEmailAlert().searchAndViewEmailTemplate( alertsForm );
                emailTemplateList = (List<EmailTemplatePojo>) alertDto.getEmailTemplatePojos();
                LOGGER.info( "Size of emailTemplateList :" + emailTemplateList.size() );
                if ( emailTemplateList.size() > 0 )
                {
                    alertsForm.setEmailTemplateList( emailTemplateList );
                }
                else
                {
                    LOGGER.info( "List is Empty" + emailTemplateList.size() );
                    alertsForm.setStatusCode( alertDto.getStatusCode() );
                }
            }
            else
            {
                LOGGER.info( "somthing has wrong in modify status of email template ..." + alertDto.getStatusCode() );
                alertsForm.setStatusCode( alertDto.getStatusCode() );
                return mapping.findForward( IActionForwardConstant.SEARCH_TEMPLATE );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "error occured while active and deactive email templete", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.SEARCH_TEMPLATE );
    }
}