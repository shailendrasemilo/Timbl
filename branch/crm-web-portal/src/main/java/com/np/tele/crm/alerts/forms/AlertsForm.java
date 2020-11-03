package com.np.tele.crm.alerts.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.EmailServerPojo;
import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.service.client.EventTemplatesPojo;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.SmsGatewayPojo;
import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.utils.StringUtils;

public class AlertsForm
    extends ActionForm
{
    /**
     * Anand Pandey
     */
    private static final long        serialVersionUID = 1L;
    private static final Logger      LOGGER           = Logger.getLogger( AlertsForm.class );
    private String                   templateName;
    private String                   smsTemplateBody;
    private FormFile                 templateFile;
    private String                   smsType;
    private String                   templateType;
    private String                   projectType;
    private String                   projectName;
    private String                   emailTemplateBody;
    private String                   status;
    private String                   statusFor;
    private String                   emailServer;
    private String                   emailFrom;
    private String                   emailSubject;
    private String                   parameter;
    private String                   smsGateway;
    private String                   statusDesc;
    private String                   statusCode;
    private List<SmsTemplatePojo>    smsTemplateList;
    private List<EmailTemplatePojo>  emailTemplateList;
    private String                   emailCc;
    private String                   emailBcc;
    private long                     templateId;
    private long                     projectId;
    private List<ContentPojo>        contentList;
    private List<ContentPojo>        statusList;
    private List<CrmParameterPojo>   parameterList;
    private List<EventsPojo>         eventList;
    private String                   eventName;
    private int                      rowCounter;
    private String                   emailTemplateName;
    private String                   smsTemplateName;
    private List<EmailServerPojo>    emailServers;
    private List<SmsGatewayPojo>     smsGateways;
    private List<ProjectsPojo>       projects;
    private List<EventTemplatesPojo> evntTemplateList;
    private SmsTemplatePojo          smsTemplatePojo;
    private EmailTemplatePojo        emailTemplatePojo;
    private List<CrmNpMobilePojo>    crmNpMobileList;
    private List<CrmNpMobilePojo>    oldCrmNpMobileList;
    private long                     eventId;

    public String getStatusFor()
    {
        return statusFor;
    }

    public void setStatusFor( String inStatusFor )
    {
        statusFor = inStatusFor;
    }

    public String getTemplateName()
    {
        return templateName;
    }

    public void setTemplateName( String inTemplateName )
    {
        templateName = inTemplateName;
    }

    public List<EmailTemplatePojo> getEmailTemplateList()
    {
        return emailTemplateList;
    }

    public void setEmailTemplateList( List<EmailTemplatePojo> inEmailTemplateList )
    {
        emailTemplateList = inEmailTemplateList;
    }

    public String getEmailCc()
    {
        return emailCc;
    }

    public void setEmailCc( String inEmailCc )
    {
        emailCc = inEmailCc;
    }

    public String getEmailBcc()
    {
        return emailBcc;
    }

    public void setEmailBcc( String inEmailBcc )
    {
        emailBcc = inEmailBcc;
    }

    public List<SmsTemplatePojo> getSmsTemplateList()
    {
        return smsTemplateList;
    }

    public void setSmsTemplateList( List<SmsTemplatePojo> inSmsTemplateList )
    {
        smsTemplateList = inSmsTemplateList;
    }

    public String gettemplateName()
    {
        return templateName;
    }

    public String getSmsType()
    {
        return smsType;
    }

    public void setSmsType( String inSmsType )
    {
        smsType = inSmsType;
    }

    public FormFile getTemplateFile()
    {
        return templateFile;
    }

    public void setTemplateFile( FormFile inTemplateFile )
    {
        this.templateFile = inTemplateFile;
    }

    public String getTemplateType()
    {
        return templateType;
    }

    public void setTemplateType( String templateType )
    {
        this.templateType = templateType;
    }

    public String getProjectType()
    {
        return projectType;
    }

    public void setProjectType( String projectType )
    {
        this.projectType = projectType;
    }

    public String getProjectName()
    {
        return projectName;
    }

    public void setProjectName( String projectName )
    {
        this.projectName = projectName;
    }

    public String getParameter()
    {
        return parameter;
    }

    public void setParameter( String parameter )
    {
        this.parameter = parameter;
    }

    public String getEmailServer()
    {
        return emailServer;
    }

    public void setEmailServer( String inEmailServer )
    {
        emailServer = inEmailServer;
    }

    public String getEmailFrom()
    {
        return emailFrom;
    }

    public void setEmailFrom( String inEmailFrom )
    {
        emailFrom = inEmailFrom;
    }

    public String getEmailSubject()
    {
        return emailSubject;
    }

    public void setEmailSubject( String inEmailSubject )
    {
        emailSubject = inEmailSubject;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    public String getSmsGateway()
    {
        return smsGateway;
    }

    public void setSmsGateway( String inSmsGateway )
    {
        smsGateway = inSmsGateway;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String statusDesc )
    {
        this.statusDesc = statusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getSmsTemplateBody()
    {
        return smsTemplateBody;
    }

    public void setSmsTemplateBody( String inSmsTemplateBody )
    {
        smsTemplateBody = inSmsTemplateBody;
    }

    public String getEmailTemplateBody()
    {
        return emailTemplateBody;
    }

    public void setEmailTemplateBody( String inEmailTemplateBody )
    {
        emailTemplateBody = inEmailTemplateBody;
    }

    public long getTemplateId()
    {
        return templateId;
    }

    public void setTemplateId( long inTemplateId )
    {
        templateId = inTemplateId;
    }

    public long getProjectId()
    {
        return projectId;
    }

    public void setProjectId( long inProjectId )
    {
        projectId = inProjectId;
    }

    public List<ContentPojo> getContentList()
    {
        return contentList;
    }

    public void setContentList( List<ContentPojo> inContentList )
    {
        contentList = inContentList;
    }

    public List<EventsPojo> getEventList()
    {
        return eventList;
    }

    public void setEventList( List<EventsPojo> inEventList )
    {
        eventList = inEventList;
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName( String inEventName )
    {
        eventName = inEventName;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int inRowCounter )
    {
        rowCounter = inRowCounter;
    }

    public List<EventTemplatesPojo> getEvntTemplateList()
    {
        return evntTemplateList;
    }

    public void setEvntTemplateList( List<EventTemplatesPojo> inEvntTemplateList )
    {
        evntTemplateList = inEvntTemplateList;
    }

    public String getEmailTemplateName()
    {
        return emailTemplateName;
    }

    public void setEmailTemplateName( String inEmailTemplateName )
    {
        emailTemplateName = inEmailTemplateName;
    }

    public String getSmsTemplateName()
    {
        return smsTemplateName;
    }

    public void setSmsTemplateName( String inSmsTemplateName )
    {
        smsTemplateName = inSmsTemplateName;
    }

    public List<CrmParameterPojo> getParameterList()
    {
        return parameterList;
    }

    public void setParameterList( List<CrmParameterPojo> inParameterList )
    {
        parameterList = inParameterList;
    }

    public List<EmailServerPojo> getEmailServers()
    {
        return emailServers;
    }

    public void setEmailServers( List<EmailServerPojo> inEmailServers )
    {
        emailServers = inEmailServers;
    }

    public List<SmsGatewayPojo> getSmsGateways()
    {
        return smsGateways;
    }

    public void setSmsGateways( List<SmsGatewayPojo> inSmsGateways )
    {
        smsGateways = inSmsGateways;
    }

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> inStatusList )
    {
        statusList = inStatusList;
    }

    public List<ProjectsPojo> getProjects()
    {
        return projects;
    }

    public void setProjects( List<ProjectsPojo> inProjects )
    {
        projects = inProjects;
    }

    public SmsTemplatePojo getSmsTemplatePojo()
    {
        return smsTemplatePojo;
    }

    public void setSmsTemplatePojo( SmsTemplatePojo inSmsTemplatePojo )
    {
        smsTemplatePojo = inSmsTemplatePojo;
    }

    public EmailTemplatePojo getEmailTemplatePojo()
    {
        return emailTemplatePojo;
    }

    public void setEmailTemplatePojo( EmailTemplatePojo inEmailTemplatePojo )
    {
        emailTemplatePojo = inEmailTemplatePojo;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        AlertFormHelper.resetSmsList( methodName, getSmsTemplateList() );
        AlertFormHelper.resetEmailList( methodName, getEmailTemplateList() );
        AlertFormHelper.resetTemplates( methodName, this );
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "In validate method........" + method );
        ActionErrors actionError = new ActionErrors();
        if ( method.equals( "createSMSTemplate" ) )
        {
            if ( ( this.templateName == null ) || ( this.templateName.isEmpty() ) )
            {
                actionError.add( "smsTemplateName", new ActionMessage( "error.sms.templateName.required" ) );
            }
            else if ( this.templateName.trim().length() > 50 )
            {
                actionError.add( "smsTemplateName", new ActionMessage( "error.sms.templateName.max" ) );
            }
            else if ( ( this.smsTemplateBody == null ) || ( this.smsTemplateBody.isEmpty() ) )
            {
                actionError.add( "smsTemplateBody", new ActionMessage( "error.sms.smsTemplateBody.required" ) );
            }
            else if ( ( this.smsType == null ) || ( this.smsType.trim().isEmpty() ) )
            {
                actionError.add( "smsType", new ActionMessage( "error.sms.smsType.required" ) );
            }
            else if ( ( this.projectType == null ) || ( this.projectType.trim().isEmpty() ) )
            {
                actionError.add( "projectType", new ActionMessage( "error.projectType.required" ) );
            }
            else if ( this.getProjectId() == 0 )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( "error.project.name" ) );
            }
        }
        else if ( method.equals( "modifySMSTemplate" ) )
        {
            if ( ( this.templateName == null ) || ( this.templateName.trim().isEmpty() ) )
            {
                actionError.add( "smsTemplateName", new ActionMessage( "error.sms.templateName.required" ) );
            }
            else if ( this.templateName.trim().length() > 50 )
            {
                actionError.add( "smsTemplateName", new ActionMessage( "error.sms.templateName.max" ) );
            }
            if ( ( this.smsTemplateBody == null ) || ( this.smsTemplateBody.isEmpty() ) )
            {
                actionError.add( "smsTemplateBody", new ActionMessage( "error.sms.smsTemplateBody.required" ) );
            }
            else if ( ( this.smsType == null ) || ( this.smsType.trim().isEmpty() ) )
            {
                actionError.add( "smsType", new ActionMessage( "error.sms.smsType.required" ) );
            }
            else if ( ( this.projectType == null ) || ( this.projectType.trim().isEmpty() ) )
            {
                actionError.add( "projectType", new ActionMessage( "error.projectType.required" ) );
            }
            else if ( this.getProjectId() == 0 )
            {
                actionError.add( IAppConstants.APP_ERROR, new ActionMessage( "error.project.name" ) );
            }
        }
        else if ( method.equals( "createEmailTemplate" ) )
        {
            if ( ( this.templateName == null ) || ( this.templateName.trim().isEmpty() ) )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.email.templateName.required" ) );
            }
            else if ( this.templateName.trim().length() > 50 )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.email.templateName.max" ) );
            }
            else if ( ( this.emailSubject == null ) || ( this.emailSubject.trim().isEmpty() ) )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.email.emailSubject.required" ) );
            }
            else if ( this.emailSubject.trim().length() > 128 )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.email.emailSubject.max" ) );
            }
            else if ( StringUtils.isEmpty( StringUtils.trimToNull( this.emailTemplateBody ) )
                    || StringUtils.trimToNull( this.emailTemplateBody ).length() < IAppConstants.CKEDITOR_BLANK_BODY_LENGTH )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.email.smsTemplateBody.required" ) );
                this.emailTemplateBody = "";
            }
            else if ( ( this.projectType == null ) || ( this.projectType.trim().isEmpty() ) )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.projectType.required" ) );
            }
            else if ( this.getProjectId() == 0 )
            {
                actionError.add( "emailTemplate", new ActionMessage( "error.project.name" ) );
            }
        }
        AlertFormHelper.validateEventTemplates( method, actionError, this );
        if ( !actionError.isEmpty() )
        {
            if ( StringUtils.isNotBlank( projectType ) )
            {
                IMasterBMngr masterBMngr = (IMasterBMngr) IAppConstants.flyWeightBeanMap
                        .get( IAppConstants.MASTER_DATA );
                projects = masterBMngr.getProjectList( projectType );
                if ( projectId > 0 )
                {
                    parameterList = masterBMngr.getParameters( projectId, CRMParameter.ALERT, null );
                }
            }
        }
        LOGGER.info( "Error size :" + actionError.size() );
        return actionError;
    }

    /* public void reset( ActionMapping mapping, HttpServletRequest request )
     {
         this.smsTemplateName = "";
         this.emailTemplateName = "";
         this.projectName = "";
         this.projectType = "";
         this.smsType = "";
         this.templateType = "";
         this.parameter = "";
     }*/
    public List<CrmNpMobilePojo> getCrmNpMobileList()
    {
        return crmNpMobileList;
    }

    public void setCrmNpMobileList( List<CrmNpMobilePojo> inCrmNpMobileList )
    {
        crmNpMobileList = inCrmNpMobileList;
    }

    public List<CrmNpMobilePojo> getOldCrmNpMobileList()
    {
        return oldCrmNpMobileList;
    }

    public void setOldCrmNpMobileList( List<CrmNpMobilePojo> inOldCrmNpMobileList )
    {
        oldCrmNpMobileList = inOldCrmNpMobileList;
    }

    public long getEventId()
    {
        return eventId;
    }

    public void setEventId( long inEventId )
    {
        eventId = inEventId;
    }
}
