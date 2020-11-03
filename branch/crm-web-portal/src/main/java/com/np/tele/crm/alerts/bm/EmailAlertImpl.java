package com.np.tele.crm.alerts.bm;

import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CRMAlertsService;
import com.np.tele.crm.service.client.EmailCcBccPojo;
import com.np.tele.crm.service.client.EmailTemplatePojo;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.comparators.EmailPojoComparator;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class EmailAlertImpl
    implements IEmailAlert
{
    private static final Logger LOGGER          = Logger.getLogger( EmailAlertImpl.class );
    private CRMAlertsService    crmAlertsClient = null;

    public CRMAlertsService getCrmAlertsClient()
    {
        return crmAlertsClient;
    }

    public void setCrmAlertsClient( CRMAlertsService crmAlertsClient )
    {
        this.crmAlertsClient = crmAlertsClient;
    }

    @Override
    public AlertsDto createEmailTemplate( final AlertsForm inAlertsForm, final EmailTemplatePojo inEmailTemplatePojo )
    {
        AlertsDto alertDto = new AlertsDto();
        try
        {
            ProjectsPojo projectPojo = new ProjectsPojo();
            projectPojo.setProjectId( inAlertsForm.getProjectId() );
            projectPojo.setProjectType( inAlertsForm.getProjectType() );
            inEmailTemplatePojo.setEmailTemplateName( inAlertsForm.getTemplateName() );
            inEmailTemplatePojo.setEmailTemplate( inAlertsForm.getEmailTemplateBody().getBytes() );
            inEmailTemplatePojo.setEmailFrom( inAlertsForm.getEmailFrom() );
            inEmailTemplatePojo.setEmailServer( inAlertsForm.getEmailServer() );
            inEmailTemplatePojo.setEmailSubject( inAlertsForm.getEmailSubject() );
            inEmailTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            inEmailTemplatePojo.setProjects( projectPojo );
            alertDto.setEmailTemplatePojo( inEmailTemplatePojo );
            if ( StringUtils.isValidObj( inAlertsForm.getEmailTemplatePojo() ) )
            {
                EmailPojoComparator comparator = new EmailPojoComparator();
                if ( comparator.compare( inAlertsForm.getEmailTemplatePojo(), inEmailTemplatePojo ) == 0 )
                {
                    alertDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
                else
                {
                    LOGGER.info( "Both the Templates ar different." );
                }
            }
            if ( StringUtils.isBlank( alertDto.getStatusCode() ) )
            {
                alertDto = getCrmAlertsClient().alertTemplate( ServiceParameter.CREATE.getParameter(),
                                                               CRMParameter.EMAIL_ALERT.getParameter(), alertDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for create email template", ex );
        }
        return alertDto;
    }

    @Override
    public boolean setCCBcc( String inCcBCCMail, String inType, List<EmailCcBccPojo> inEmailCcBccList )
    {
        LOGGER.info( "Inside setCCBcc method" );
        if ( StringUtils.isNotBlank( inCcBCCMail ) )
        {
            EmailCcBccPojo emailCcBccPojo = null;
            if ( StringUtils.contains( inCcBCCMail, IAppConstants.COMMA ) )
            {
                String[] ccBcc = inCcBCCMail.split( IAppConstants.COMMA );
                for ( int i = 0; i < ccBcc.length; i++ )
                {
                    emailCcBccPojo = new EmailCcBccPojo();
                    if ( StringUtils.isValidEmailID( ccBcc[i] ) )
                    {
                        emailCcBccPojo.setEmailId( ccBcc[i] );
                        emailCcBccPojo.setEmailType( inType );
                        inEmailCcBccList.add( emailCcBccPojo );
                    }
                    else
                    {
                        LOGGER.info( "Invalid Mail ID:" + ccBcc[i] );
                        return false;
                    }
                }
            }
            else if ( StringUtils.isValidEmailID( inCcBCCMail ) )
            {
                emailCcBccPojo = new EmailCcBccPojo();
                emailCcBccPojo.setEmailId( inCcBCCMail );
                emailCcBccPojo.setEmailType( inType );
                inEmailCcBccList.add( emailCcBccPojo );
            }
        }
        return true;
    }

    public AlertsDto searchAndViewEmailTemplate( AlertsForm alertsForm )
    {
        LOGGER.info( "Calling Service for search  and  view  in  EmailAlertImp  Class" );
        EmailTemplatePojo emailTemplatePojo = null;
        ProjectsPojo projectPojo = null;
        AlertsDto alertDto = null;
        try
        {
            emailTemplatePojo = new EmailTemplatePojo();
            alertDto = new AlertsDto();
            projectPojo = new ProjectsPojo();
            if ( StringUtils.isNotEmpty( alertsForm.getTemplateName() ) )
            {
                emailTemplatePojo.setEmailTemplateName( alertsForm.getTemplateName().trim() );
            }
            LOGGER.info( "Email Template Name :" + alertsForm.getTemplateName() );
            projectPojo.setProjectType( alertsForm.getProjectType() );
            projectPojo.setProjectId( alertsForm.getProjectId() );
            emailTemplatePojo.setProjects( projectPojo );
            if ( StringUtils.isNotEmpty( alertsForm.getStatusFor() ) )
            {
                if ( !alertsForm.getStatusFor().equals( "-1" ) )
                {
                    emailTemplatePojo.setStatus( alertsForm.getStatusFor() );
                }
            }
            alertDto.setEmailTemplatePojo( emailTemplatePojo );
            alertDto = getCrmAlertsClient().alertTemplate( ServiceParameter.LIST.getParameter(),
                                                           CRMParameter.EMAIL_ALERT.getParameter(), alertDto );
            LOGGER.info( "Exit search and view email template" );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search and view email template", ex );
        }
        return alertDto;
    }
    
    public EmailTemplatePojo searchByTemplateId( long inTemplateId )
    {
        LOGGER.info( "Calling Service for search  and  view  in  EmailAlertImp  Class" );
        EmailTemplatePojo emailTemplatePojo = null;        
        AlertsDto alertDto = null;
        try
        {
            emailTemplatePojo = new EmailTemplatePojo();
            alertDto = new AlertsDto();
            emailTemplatePojo.setEmailTemplateId( inTemplateId );
            alertDto.setEmailTemplatePojo( emailTemplatePojo );
            alertDto = getCrmAlertsClient().alertTemplate( ServiceParameter.LIST.getParameter(),
                                                           CRMParameter.EMAIL_ALERT.getParameter(), alertDto );
            LOGGER.info( "Exit search and view email template" );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search and view email template", ex );
        }
        return alertDto.getEmailTemplatePojos().get( 0 );
    }

    @Override
    public AlertsDto activeAndDeactiveEmailTemplate( EmailTemplatePojo emailTemplatePojo )
    {
        LOGGER.info( "Calling Service for active and deactive email template  in  EmailAlertImp  Class" );
        AlertsDto alertDto = null;
        try
        {
            alertDto = new AlertsDto();
            if ( StringUtils.isNotEmpty( emailTemplatePojo.getStatus() )
                    && StringUtils.equals( emailTemplatePojo.getStatus(), CRMStatusCode.INACTIVE.getStatusCode() ) )
            {
                emailTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            }
            else
            {
                emailTemplatePojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
            }
            alertDto.setEmailTemplatePojo( emailTemplatePojo );
            alertDto = getCrmAlertsClient().alertTemplate( ServiceParameter.MODIFY_STATUS.getParameter(),
                                                           CRMParameter.EMAIL_ALERT.getParameter(), alertDto );
            LOGGER.info( "Exit active and deactive email template" );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for active and deactive email template", ex );
        }
        return alertDto;
    }

    @Override
    public void setCCBCCtoForm( AlertsForm inAlertsForm, List<EmailCcBccPojo> inEmailCcBccs )
    {
        StringBuilder emailCC = null;
        StringBuilder emailBcc = null;
        if ( !inEmailCcBccs.isEmpty() )
        {
            emailCC = new StringBuilder();
            emailBcc = new StringBuilder();
            for ( EmailCcBccPojo ccbccPojo : inEmailCcBccs )
            {
                if ( StringUtils.equals( CRMParameter.CC.getParameter(), ccbccPojo.getEmailType() ) )
                {
                    emailCC.append( ccbccPojo.getEmailId() );
                    emailCC.append( IAppConstants.COMMA );
                }
                else if ( StringUtils.equals( CRMParameter.BCC.getParameter(), ccbccPojo.getEmailType() ) )
                {
                    emailBcc.append( ccbccPojo.getEmailId() );
                    emailBcc.append( IAppConstants.COMMA );
                }
            }
            if ( StringUtils.isValidObj( emailCC ) && emailCC.length() > 0 )
            {
                inAlertsForm.setEmailCc( emailCC.substring( 0, ( emailCC.lastIndexOf( IAppConstants.COMMA ) ) ) );
            }
            if ( StringUtils.isValidObj( emailBcc ) && emailBcc.length() > 0 )
            {
                inAlertsForm.setEmailBcc( emailBcc.substring( 0, ( emailBcc.lastIndexOf( IAppConstants.COMMA ) ) ) );
            }
        }
    }
}
