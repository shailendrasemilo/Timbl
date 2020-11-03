package com.np.tele.crm.alerts.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CRMAlertsService;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SmsTemplatePojo;
import com.np.tele.crm.service.comparators.SMSPojoComparator;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class SmsAlertImp
    implements ISmsAlert
{
    private static final Logger LOGGER          = Logger.getLogger( SmsAlertImp.class );
    private CRMAlertsService    crmAlertsClient = null;

    public CRMAlertsService getCrmAlertsClient()
    {
        return crmAlertsClient;
    }

    public void setCrmAlertsClient( CRMAlertsService crmAlertsClient )
    {
        this.crmAlertsClient = crmAlertsClient;
    }

    public AlertsDto createSmsTemplate( AlertsForm alertsForm, String inUserId )
    {
        LOGGER.info( "Calling Service for create sms template  in  SmsAlertImp  Class" );
        SmsTemplatePojo smsTemplatePojo = null;
        AlertsDto alertDto = null;
        ProjectsPojo project = null;
        try
        {
            smsTemplatePojo = new SmsTemplatePojo();
            alertDto = new AlertsDto();
            project = new ProjectsPojo();
            smsTemplatePojo.setSmsTemplate( alertsForm.getSmsTemplateBody().getBytes() );
            smsTemplatePojo.setSmsType( alertsForm.getSmsType() );
            smsTemplatePojo.setSmsTemplateName( alertsForm.getTemplateName() );
            smsTemplatePojo.setSmsGateway( alertsForm.getSmsGateway() );
            project.setProjectType( alertsForm.getProjectType() );
            project.setProjectId( alertsForm.getProjectId() );
            smsTemplatePojo.setProjects( project );
            smsTemplatePojo.setCreatedBy( inUserId );
            smsTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            alertDto.setSmsTemplatePojo( smsTemplatePojo );
            alertDto = getCrmAlertsClient().alertTemplate( ServiceParameter.CREATE.getParameter(),
                                                           CRMParameter.SMS_ALERT.getParameter(), alertDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service", ex );
        }
        return alertDto;
    }

    public AlertsDto modifySmsTemplate( AlertsForm inAlertsForm, String userId )
    {
        LOGGER.info( "Calling Service for modify sms template  in SmsAlertImp  Class" );
        SmsTemplatePojo smsTemplatePojo = null;
        AlertsDto alertDto = null;
        ProjectsPojo project = null;
        try
        {
            smsTemplatePojo = new SmsTemplatePojo();
            alertDto = new AlertsDto();
            project = new ProjectsPojo();
            smsTemplatePojo.setSmsTemplateName( inAlertsForm.getTemplateName() );
            smsTemplatePojo.setSmsTemplate( inAlertsForm.getSmsTemplateBody().getBytes() );
            smsTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            smsTemplatePojo.setSmsGateway( inAlertsForm.getSmsGateway() );
            smsTemplatePojo.setSmsType( inAlertsForm.getSmsType() );
            smsTemplatePojo.setLastModifiedBy( userId );
            smsTemplatePojo.setCreatedBy( userId );
            alertDto.setSmsTemplatePojo( smsTemplatePojo );
            smsTemplatePojo.setSmsType( inAlertsForm.getSmsType() );
            project.setProjectId( inAlertsForm.getProjectId() );
            project.setProjectType( inAlertsForm.getProjectType() );
            smsTemplatePojo.setProjects( project );
            if ( StringUtils.isValidObj( inAlertsForm.getSmsTemplatePojo() ) )
            {
                SMSPojoComparator comparator = new SMSPojoComparator();
                if ( comparator.compare( inAlertsForm.getSmsTemplatePojo(), smsTemplatePojo ) == 0 )
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
                                                               CRMParameter.SMS_ALERT.getParameter(), alertDto );
            }
            LOGGER.info( "Exit of modifySmsTemplate method" );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service", ex );
        }
        return alertDto;
    }

    @Override
    public AlertsDto activeAndDeactiveSMSTemplate( SmsTemplatePojo inSmspojo )
    {
        LOGGER.info( "Calling Service for activate and deactivate sms template  in  SmsAlertImp  Class" );
        AlertsDto alertDto = null;
        try
        {
            alertDto = new AlertsDto();
            if ( StringUtils.isNotEmpty( inSmspojo.getStatus() )
                    && StringUtils.equals( inSmspojo.getStatus(), CRMStatusCode.INACTIVE.getStatusCode() ) )
            {
                inSmspojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            }
            else
            {
                inSmspojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
            }
            alertDto.setSmsTemplatePojo( inSmspojo );
            return getCrmAlertsClient().alertTemplate( ServiceParameter.MODIFY_STATUS.getParameter(),
                                                       CRMParameter.SMS_ALERT.getParameter(), alertDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service", ex );
        }
        return alertDto;
    }

    @Override
    public AlertsDto searchAndViewSMSTemplate( AlertsForm alertsForm )
    {
        LOGGER.info( "Calling Service for search and view sms template  in  SmsAlertImp  Class" );
        SmsTemplatePojo smsTemplatePojo = null;
        AlertsDto alertDto = null;
        ProjectsPojo projectPojo = null;
        try
        {
            smsTemplatePojo = new SmsTemplatePojo();
            alertDto = new AlertsDto();
            projectPojo = new ProjectsPojo();
            if ( StringUtils.isNotEmpty( alertsForm.getTemplateName() ) )
            {
                smsTemplatePojo.setSmsTemplateName( alertsForm.getTemplateName().trim() );
            }
            if ( StringUtils.isNotEmpty( alertsForm.getStatusFor() ) )
            {
                if ( !alertsForm.getStatusFor().equals( "-1" ) )
                {
                    smsTemplatePojo.setStatus( alertsForm.getStatusFor() );
                }
            }
            if ( StringUtils.isNotBlank( alertsForm.getSmsType() ) )
            {
                smsTemplatePojo.setSmsType( alertsForm.getSmsType() );
            }
            projectPojo.setProjectType( alertsForm.getProjectType() );
            projectPojo.setProjectId( alertsForm.getProjectId() );
            smsTemplatePojo.setProjects( projectPojo );
            alertDto.setSmsTemplatePojo( smsTemplatePojo );
            alertDto = getCrmAlertsClient().alertTemplate( ServiceParameter.LIST.getParameter(),
                                                           CRMParameter.SMS_ALERT.getParameter(), alertDto );
            LOGGER.info( "Exit Search And View SMSTemplate " );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service", ex );
        }
        return alertDto;
    }
}
