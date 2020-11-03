package com.np.tele.crm.alerts.businessmgr;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import com.np.tele.crm.alert.dao.IAlertTemplateDao;
import com.np.tele.crm.alerts.util.SendEmailUtils;
import com.np.tele.crm.alerts.util.SendSmsUtils;
import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.config.service.ICrmConfigService;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmReasonsConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.ExtAlertDto;
import com.np.tele.crm.ext.pojos.AlertMasterPojo;
import com.np.tele.crm.ext.pojos.EmailServerPojo;
import com.np.tele.crm.ext.pojos.SmsGatewayPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.AlertStatusPojo;
import com.np.tele.crm.pojos.CrmEmailAttachment;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.EmailCcBccPojo;
import com.np.tele.crm.pojos.EmailTemplatePojo;
import com.np.tele.crm.pojos.EventTemplatesPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.tele.crm.pojos.SmsTemplatePojo;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class AlertsTemplateMgrImpl
    implements IAlertsTemplateMgr
{
    private IAlertTemplateDao   alertTemplateDao = null;
    private static final Logger LOGGER           = Logger.getLogger( AlertsTemplateMgrImpl.class );
    private ICrmConfigService   configService    = null;
    private ConfigDto           configDto        = null;
    private IConfigManagerDao   configManagerDao = null;

    public IAlertTemplateDao getAlertTemplateDao()
    {
        return alertTemplateDao;
    }

    public void setAlertTemplateDao( IAlertTemplateDao inAlertTemplateDao )
    {
        alertTemplateDao = inAlertTemplateDao;
    }

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
    }

    @Override
    public AlertsDto alertTemplate( String inServiceParam, String inAlertType, AlertsDto inAlertsDto )
    {
        LOGGER.info( "Inside alertTemplate Service" );
        if ( StringUtils.isValidObj( inServiceParam ) & StringUtils.isValidObj( inAlertType ) )
        {
            if ( inAlertType.equals( CRMParameter.EMAIL_ALERT.getParameter() ) )
            {
                if ( inServiceParam.equals( ServiceParameter.CREATE.getParameter() ) )
                {
                    return createEmailTemplate( inAlertsDto.getEmailTemplatePojo() );
                }
                if ( inServiceParam.equals( ServiceParameter.LIST.getParameter() ) )
                {
                    return listEmailTemplate( inAlertsDto.getEmailTemplatePojo() );
                }
                if ( inServiceParam.equals( ServiceParameter.MODIFY_STATUS.getParameter() ) )
                {
                    return modifyEmailTemplateStatus( inAlertsDto.getEmailTemplatePojo() );
                }
            }
            else if ( inAlertType.equals( CRMParameter.SMS_ALERT.getParameter() ) )
            {
                if ( inServiceParam.equals( ServiceParameter.CREATE.getParameter() ) )
                {
                    return createSMSTemplate( inAlertsDto.getSmsTemplatePojo() );
                }
                if ( inServiceParam.equals( ServiceParameter.LIST.getParameter() ) )
                {
                    return listSMSTemplate( inAlertsDto.getSmsTemplatePojo() );
                }
                if ( inServiceParam.equals( ServiceParameter.MODIFY_STATUS.getParameter() ) )
                {
                    return modifySMSTemplateStatus( inAlertsDto.getSmsTemplatePojo() );
                }
            }
        }
        else
        {
            inAlertsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            inAlertsDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        LOGGER.info( "Exit alertTemplate Service" );
        return inAlertsDto;
    }

    private AlertsDto modifyEmailTemplateStatus( EmailTemplatePojo inEmailTemplatePojo )
    {
        LOGGER.info( "Inside modifyEmailTemplateStatus Service" );
        AlertsDto alertsDto = new AlertsDto();
        boolean isModified = false;
        if ( StringUtils.isValidObj( inEmailTemplatePojo ) )
        {
            isModified = getAlertTemplateDao().updateEmailTemplateByArchive( inEmailTemplatePojo );
            if ( isModified )
            {
                alertsDto.setEmailTemplatePojo( inEmailTemplatePojo );
                alertsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            }
        }
        LOGGER.info( "Exit modifyEmailTemplateStatus Service" );
        return alertsDto;
    }

    private AlertsDto listEmailTemplate( EmailTemplatePojo inEmailTemplatePojo )
    {
        LOGGER.info( "Inside listEmailTemplate Service" );
        AlertsDto alertsDto = new AlertsDto();
        if ( StringUtils.isValidObj( inEmailTemplatePojo ) )
        {
            List<EmailTemplatePojo> emailTemplatePojosList = getAlertTemplateDao()
                    .listEmailTemplate( inEmailTemplatePojo );
            if ( emailTemplatePojosList.size() > 0 )
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                alertsDto.setEmailTemplatePojos( emailTemplatePojosList );
            }
            else
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM021.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM021.getStatusDesc() );
            }
        }
        LOGGER.info( "Exit listEmailTemplate Service" );
        return alertsDto;
    }

    private AlertsDto createEmailTemplate( EmailTemplatePojo inEmailTemplatePojo )
    {
        LOGGER.info( "Inside createEmailTemplate Service" );
        AlertsDto alertsDto = new AlertsDto();
        boolean isCreated = false;
        if ( StringUtils.isValidObj( inEmailTemplatePojo ) )
        {
            if ( StringUtils.isValidObj( inEmailTemplatePojo.getEmailTemplate() )
                    && inEmailTemplatePojo.getEmailTemplate().length <= 0 )
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM028.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM028.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inEmailTemplatePojo.getEmailTemplateName() ) )
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM029.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM029.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inEmailTemplatePojo.getCreatedBy() ) )
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM030.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM030.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inEmailTemplatePojo.getEmailFrom() ) )
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM031.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM031.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( inEmailTemplatePojo.getEmailServer() ) )
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM032.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM032.getStatusDesc() );
            }
            else
            {
                LOGGER.info( "EmailCCBCC List" + inEmailTemplatePojo.getEmailCcBccs() );
                // inEmailTemplatePojo.setEmailCcBccs( inEmailCcBccPojosSet );
                // if ( StringUtils.isValidObj( inEmailCcBccPojosSet ) )
                // {
                //
                // LOGGER.info( "EmailCCBCC block..." );
                // for ( EmailCcBccPojo emailCcBccPojo : inEmailCcBccPojosSet )
                // {
                // emailCcBccPojo.setEmailTemplatePojo( inEmailTemplatePojo );
                // }
                //
                //
                // }
                isCreated = getAlertTemplateDao().createEmailTemplate( inEmailTemplatePojo );
                if ( isCreated )
                {
                    alertsDto.setEmailTemplatePojo( inEmailTemplatePojo );
                    alertsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    alertsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                }
                else
                {
                    alertsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                    alertsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
                }
            }
        }
        else
        {
            alertsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            alertsDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        LOGGER.info( "Exit createEmailTemplate Service" );
        return alertsDto;
    }

    private AlertsDto modifySMSTemplateStatus( SmsTemplatePojo inSmsTemplatePojo )
    {
        LOGGER.info( "Inside modifySMSTemplateStatus Service" );
        AlertsDto alertsDto = new AlertsDto();
        if ( StringUtils.isValidObj( inSmsTemplatePojo ) )
        {
            boolean updated = getAlertTemplateDao().updateSMSTemplateByArchive( inSmsTemplatePojo );
            if ( updated )
            {
                alertsDto.setSmsTemplatePojo( inSmsTemplatePojo );
                alertsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            }
        }
        else
        {
            alertsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            alertsDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        LOGGER.info( "Exit modifySMSTemplateStatus Service" );
        return alertsDto;
    }

    private AlertsDto listSMSTemplate( SmsTemplatePojo insmsTemplatePojo )
    {
        LOGGER.info( "Inside listSMSTemplate Service" );
        AlertsDto alertsDto = new AlertsDto();
        if ( StringUtils.isValidObj( insmsTemplatePojo ) )
        {
            List<SmsTemplatePojo> smTemplatePojosList = getAlertTemplateDao().listSMSTemplate( insmsTemplatePojo );
            if ( smTemplatePojosList.size() > 0 )
            {
                alertsDto.setSmsTemplatePojos( smTemplatePojosList );
                alertsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                alertsDto.setStatusCode( CRMServiceCode.CRM021.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM021.getStatusDesc() );
            }
        }
        LOGGER.info( "Exit listSMSTemplate Service" );
        return alertsDto;
    }

    private AlertsDto createSMSTemplate( SmsTemplatePojo insmsTemplatePojo )
    {
        LOGGER.info( "Inside createSMSTemplate Service" );
        AlertsDto alertsDto = new AlertsDto();
        if ( StringUtils.isValidObj( insmsTemplatePojo ) )
        {
            if ( StringUtils.isValidObj( insmsTemplatePojo.getSmsTemplate() )
                    && insmsTemplatePojo.getSmsTemplate().length <= 0 )
            {
                LOGGER.info( "SMS Tempalte Not Left Blank." );
                alertsDto.setStatusCode( CRMServiceCode.CRM022.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM022.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( insmsTemplatePojo.getSmsTemplateName() ) )
            {
                LOGGER.info( "SMS Tempalte Name Not Left Blank" );
                alertsDto.setStatusCode( CRMServiceCode.CRM023.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM023.getStatusDesc() );
            }
            else if ( insmsTemplatePojo.getSmsType() == null )
            {
                LOGGER.info( "SMS Type Not Left Blank" );
                alertsDto.setStatusCode( CRMServiceCode.CRM025.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM025.getStatusDesc() );
            }
            else if ( StringUtils.isEmpty( insmsTemplatePojo.getSmsGateway() ) )
            {
                LOGGER.info( "SMS Gateway Not Left Blank" );
                alertsDto.setStatusCode( CRMServiceCode.CRM026.getStatusCode() );
                alertsDto.setStatusDesc( CRMServiceCode.CRM026.getStatusDesc() );
            }
            else
            {
                boolean iscreated = getAlertTemplateDao().createSMSTemplate( insmsTemplatePojo );
                if ( iscreated )
                {
                    alertsDto.setSmsTemplatePojo( insmsTemplatePojo );
                    alertsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    alertsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                }
                else
                {
                    alertsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
                    alertsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
                }
            }
        }
        else
        {
            LOGGER.info( "Invalid Request Unable to Process." );
            alertsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            alertsDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        LOGGER.info( "Exit createSMSTemplate Service" );
        return alertsDto;
    }

    // private void convertSmsTemplate( SmsTemplatePojo smsTemplatePojo,
    // SmsTemplatePojo insmsTemplateDto,
    // boolean inConvertToPojo )
    // {
    // try
    // {
    // if ( inConvertToPojo )
    // {
    // org.apache.commons.beanutils.PropertyUtils.copyProperties(
    // smsTemplatePojo, insmsTemplateDto );
    // try
    // {
    // Blob blob = new SerialBlob(
    // insmsTemplateDto.getStrSmsTemplate().getBytes() );
    // smsTemplatePojo.setSmsTemplate( blob );
    // }
    // catch ( SerialException ex )
    // {
    // ex.printStackTrace();
    // }
    // catch ( SQLException ex )
    // {
    // ex.printStackTrace();
    // }
    // }
    // else
    // {
    // org.apache.commons.beanutils.PropertyUtils.copyProperties(
    // insmsTemplateDto, smsTemplatePojo );
    // byte[] bdata;
    // try
    // {
    // bdata = smsTemplatePojo.getSmsTemplate().getBytes( 1,
    // (int) smsTemplatePojo.getSmsTemplate().length() );
    // String data1 = new String( bdata );
    // insmsTemplateDto.setStrSmsTemplate( data1 );
    // }
    // catch ( SQLException ex )
    // {
    // ex.printStackTrace();
    // }
    // }
    // }
    // catch ( IllegalAccessException ex )
    // {
    // ex.printStackTrace();
    // }
    // catch ( InvocationTargetException ex )
    // {
    // ex.printStackTrace();
    // }
    // catch ( NoSuchMethodException ex )
    // {
    // ex.printStackTrace();
    // }
    // }
    @Override
    public AlertsDto eventTemplate( String inServiceParam, AlertsDto inAlertsDto )
    {
        if ( StringUtils.isValidObj( inServiceParam ) )
        {
            if ( inServiceParam.equals( ServiceParameter.LIST.getParameter() ) )
            {
                return listingTemplates( inAlertsDto );
            }
            if ( inServiceParam.equals( ServiceParameter.CREATE.getParameter() ) )
            {
                return createEventTempalteMapping( inAlertsDto );
            }
            if ( inServiceParam.equals( ServiceParameter.VIEW.getParameter() ) )
            {
                return viewEventTemplateMapping( inAlertsDto );
            }
        }
        return inAlertsDto;
    }

    private AlertsDto viewEventTemplateMapping( AlertsDto inAlertsDto )
    {
        inAlertsDto = getAlertTemplateDao().viewEvTemplateMapping( inAlertsDto );
        return inAlertsDto;
    }

    private AlertsDto createEventTempalteMapping( AlertsDto inAlertsDto )
    {
        if ( StringUtils.isValidObj( inAlertsDto.getEvTempalteList() ) )
        {
            getAlertTemplateDao().createTemplateMapping( inAlertsDto );
        }
        return inAlertsDto;
    }

    private AlertsDto listingTemplates( AlertsDto inAlertsDto )
    {
        getAlertTemplateDao().getEventTemplatesList( inAlertsDto );
        getAlertTemplateDao().getSmsTemplateList( inAlertsDto );
        getAlertTemplateDao().getEmailTemplateList( inAlertsDto );
        return inAlertsDto;
    }

    /*
     * Method used to send email and sms on internal calls.
     */
    @Override
    public AlertStatusPojo sendAlerts( AlertsDto inAlertsDto )
    {
        LOGGER.info( "Inside AlertsTemplateMgrImpl: sendAlerts: " + inAlertsDto );
        AlertStatusPojo alertStatusPojo = null;
        EventTemplatesPojo eventTemplatesPojo = null;
        if ( inAlertsDto.isScheduler() )
        {
            alertStatusPojo = inAlertsDto.getAlertStatusPojo();
        }
        else if ( inAlertsDto.getEventsPojo().getEventId() > 0 )
        {
            LOGGER.info( "Initiate alerts sending for event: " + inAlertsDto.getEventsPojo().getEventDescription() );
            alertStatusPojo = new AlertStatusPojo();
            alertStatusPojo.setEventId( inAlertsDto.getEventsPojo().getEventId() );
            alertStatusPojo.setAlertType( CRMParameter.INTERNAL.getParameter() );
        }
        else if ( StringUtils.isNotBlank( inAlertsDto.getEventsPojo().getEventName() ) )
        {
            EventsPojo eventsPojo = getConfigManagerDao().getEventsPojoByName( inAlertsDto.getEventsPojo()
                                                                                       .getEventName() );
            inAlertsDto.setEventsPojo( eventsPojo );
            alertStatusPojo = new AlertStatusPojo();
            alertStatusPojo.setEventId( inAlertsDto.getEventsPojo().getEventId() );
            alertStatusPojo.setAlertType( CRMParameter.INTERNAL.getParameter() );
        }
        if ( StringUtils.isValidObj( alertStatusPojo ) )
        {
            alertStatusPojo.setAlertRequestType( inAlertsDto.getParamName() );
            alertStatusPojo.setMappingId( inAlertsDto.getParamValue() );
            if ( inAlertsDto.isScheduler() )
            {
                boolean sendEmail = alertStatusPojo.getEmailSent().equals( CRMParameter.NO.getParameter() ) ? true
                                                                                                           : false;
                boolean sendSMS = alertStatusPojo.getSmsSent().equals( CRMParameter.NO.getParameter() ) ? true : false;
                // sendAlerts( inAlertsDto, alertStatusPojo, eventTemplatesPojo,
                // sendEmail, sendSMS );
                sendAlerts( inAlertsDto, alertStatusPojo, sendEmail, sendSMS );
            }
            else
            {
                inAlertsDto.setEventTemplatesPojo( getAlertTemplateDao()
                        .getEventTemplate( alertStatusPojo.getEventId() ) );
                eventTemplatesPojo = inAlertsDto.getEventTemplatesPojo();
                if ( StringUtils.isValidObj( eventTemplatesPojo ) )
                {
                    sendAlerts( inAlertsDto, alertStatusPojo, eventTemplatesPojo, true, true );
                }
                else
                {
                    alertStatusPojo.setMappingId( inAlertsDto.getParamValue() );
                    alertStatusPojo.setEmailFailureReason( CrmReasonsConstants.EVENTNOTCONFIGURED.getDescription() );
                    alertStatusPojo.setSmsFailureReason( CrmReasonsConstants.EVENTNOTCONFIGURED.getDescription() );
                }
            }
            setSendStatus( alertStatusPojo );
            getAlertTemplateDao().saveAlertStatus( alertStatusPojo );
        }
        return alertStatusPojo;
    }

    private void sendAlerts( AlertsDto inAlertsDto,
                             AlertStatusPojo inAlertStatusPojo,
                             boolean sendEmail,
                             boolean sendSMS )
    {
        if ( sendEmail )
        {
            getAlertTemplateDao().getEmailAttachment( inAlertStatusPojo );
            if ( sendMailAcknowledge( inAlertStatusPojo ) )
            {
                LOGGER.info( "Email Successfully Sent for Event: " + inAlertStatusPojo.getEventId() );
            }
        }
        if ( sendSMS )
        {
            if ( sendSmsAcknowledge( inAlertStatusPojo ) )
            {
                LOGGER.info( "SMS Successfully Sent for Event: " + inAlertStatusPojo.getEventId() );
            }
        }
    }

    private boolean sendSmsAcknowledge( AlertStatusPojo inAlertStatusPojo )
    {
        boolean isSend = false;
        String message = null;
        try
        {
            configDto = configService.configOperations( ServiceParameter.LIST.getParameter(),
                                                        SmsGatewayPojo.class.getSimpleName(), null );
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Error retrieving SMS Server Configuration.", ex );
        }
        SmsGatewayPojo smsGatewayPojo = new SmsGatewayPojo();
        smsGatewayPojo.setAlias( inAlertStatusPojo.getSmsServer() );
        int pojoIndex = configDto.getSmsGateways().indexOf( smsGatewayPojo );
        if ( pojoIndex < 0 )
        {
            inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.SERVERNOTFOUND.getDescription() );
        }
        else
        {
            smsGatewayPojo = configDto.getSmsGateways().get( pojoIndex );
            if ( smsGatewayPojo.isEnable() )
            {
                message = new String( inAlertStatusPojo.getSmsMessage() );
                SendSmsUtils sendSmsUtils = new SendSmsUtils( smsGatewayPojo );
                isSend = sendSmsUtils.sendSms( String.valueOf( inAlertStatusPojo.getMobileNo() ), message );
            }
            else
                inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.SERVERALERTSOFF.getDescription() );
        }
        return isSend;
    }

    private boolean sendMailAcknowledge( AlertStatusPojo inAlertStatusPojo )
    {
        boolean isSend = false;
        String from = null;
        Address[] to = null;
        Set<EmailCcBccPojo> ccBcc = null;
        String subject = null;
        String message = null;
        List<String> attachment = null;
        try
        {
            configDto = configService.configOperations( ServiceParameter.LIST.getParameter(),
                                                        EmailServerPojo.class.getSimpleName(), null );
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Error retrieving Email Server Configuration.", ex );
        }
        EmailServerPojo emailServerPojo = new EmailServerPojo();
        emailServerPojo.setAlias( inAlertStatusPojo.getEmailServer() );
        int pojoIndex = configDto.getEmailServers().indexOf( emailServerPojo );
        if ( pojoIndex < 0 )
        {
            inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.SERVERNOTFOUND.getDescription() );
        }
        else
        {
            emailServerPojo = configDto.getEmailServers().get( pojoIndex );
            if ( emailServerPojo.isEnable() )
            {
                from = inAlertStatusPojo.getFromEmail();
                to = getAddressList( inAlertStatusPojo.getEmailId() );
                ccBcc = new HashSet<EmailCcBccPojo>();
                if ( StringUtils.isNotEmpty( inAlertStatusPojo.getCc() ) )
                {
                    String ccSplit[] = inAlertStatusPojo.getCc().split( IAppConstants.COMMA );
                    for ( String str : ccSplit )
                    {
                        if ( StringUtils.isNotBlank( str ) )
                        {
                            EmailCcBccPojo emailCcBccPojo = new EmailCcBccPojo();
                            emailCcBccPojo.setEmailType( CRMParameter.CC.getParameter() );
                            emailCcBccPojo.setEmailId( str );
                            ccBcc.add( emailCcBccPojo );
                        }
                    }
                }
                if ( StringUtils.isNotEmpty( inAlertStatusPojo.getBcc() ) )
                {
                    String bccSplit[] = inAlertStatusPojo.getBcc().split( IAppConstants.COMMA );
                    for ( String str : bccSplit )
                    {
                        if ( StringUtils.isNotBlank( str ) )
                        {
                            EmailCcBccPojo emailCcBccPojo = new EmailCcBccPojo();
                            emailCcBccPojo.setEmailType( CRMParameter.BCC.getParameter() );
                            emailCcBccPojo.setEmailId( str );
                            ccBcc.add( emailCcBccPojo );
                        }
                    }
                }
                subject = inAlertStatusPojo.getSubject();
                message = new String( inAlertStatusPojo.getEmailMessage() );
                attachment = new ArrayList<String>();
                if ( CommonValidator.isValidCollection( inAlertStatusPojo.getCrmEmailAttachments() ) )
                {
                    for ( CrmEmailAttachment crmEmailAttachment : inAlertStatusPojo.getCrmEmailAttachments() )
                    {
                        if ( StringUtils.isNotEmpty( crmEmailAttachment.getAttachmentPath() ) )
                        {
                            attachment.add( crmEmailAttachment.getAttachmentPath() );
                        }
                        else
                        {
                            String filePath = PropertyUtils.getServiceDetails( IPropertiesConstant.UNBILLED_USAGE_PATH );
                            String fileName = crmEmailAttachment.getFileName();
                            BufferedOutputStream bs = null;
                            try
                            {
                                File file = new File( filePath + fileName );
                                FileOutputStream fs = new FileOutputStream( file );
                                bs = new BufferedOutputStream( fs );
                                bs.write( crmEmailAttachment.getAttachment() );
                                bs.close();
                                attachment.add( file.getAbsolutePath() );
                                bs = null;
                            }
                            catch ( Exception e )
                            {
                                e.printStackTrace();
                            }
                            finally
                            {
                                if ( bs != null )
                                    try
                                    {
                                        bs.close();
                                    }
                                    catch ( Exception e )
                                    {
                                        e.printStackTrace();
                                    }
                            }
                        }
                    }
                }
                SendEmailUtils sendEmailUtils = new SendEmailUtils( emailServerPojo );
                isSend = sendEmailUtils.sendEmail( from, to, ccBcc, subject, message, attachment );
            }
            else
                inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.SERVERALERTSOFF.getDescription() );
        }
        return isSend;
    }

    private void sendAlerts( AlertsDto inAlertsDto,
                             AlertStatusPojo inAlertStatusPojo,
                             EventTemplatesPojo inEventTemplatesPojo,
                             boolean sendEmail,
                             boolean sendSMS )
    {
        inAlertStatusPojo.setEventTemplateId( inEventTemplatesPojo.getEventTemplateId() );
        EmailTemplatePojo emailTemplatePojo = inEventTemplatesPojo.getEmailTemplate();
        SmsTemplatePojo smsTemplatePojo = inEventTemplatesPojo.getSmsTemplate();
        Map<String, String> paraMeterMap = resolveRecipient( inAlertsDto, inAlertStatusPojo );
        configService = CRMServicesProxy.getInstance().getCRMConfigService( IGlobalConstants.REMOTE,
                                                                            IGlobalConstants.APP );
        if ( StringUtils.isValidObj( emailTemplatePojo ) && sendEmail )
        {
            inAlertStatusPojo.setEmailTempleteId( emailTemplatePojo.getEmailTemplateId() );
            if ( StringUtils.isEmpty( inAlertStatusPojo.getEmailId() ) )
                inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.EMAILIDMISSSING.getDescription() );
            else if ( !inEventTemplatesPojo.isEmailEnabled() )
                inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.ALERTDISABLED.getDescription() );
            else if ( inAlertStatusPojo.getEmailTempleteId() == 0 )
                inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.TEMPLATENOTMAPPED.getDescription() );
            else if ( !StringUtils.equals( emailTemplatePojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.TEMPLATEINACTIVE.getDescription() );
            else
            {
                LOGGER.info( "Initiating mail sending..." );
                if ( StringUtils.isValidObj( paraMeterMap ) )
                {
                    if ( CommonValidator.isValidCollection( inAlertsDto.getAttachmentPaths() ) )
                    {
                        emailTemplatePojo.setAttachmentPaths( inAlertsDto.getAttachmentPaths() );
                    }
                    if ( sendMailAcknowledge( inAlertStatusPojo, emailTemplatePojo, paraMeterMap ) )
                    {
                        LOGGER.info( "Email Successfully Sent for Event: " + inAlertsDto.getEventsPojo().getEventName() );
                    }
                    else
                    {
                        inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.CONFIGUREDSERVERISSUE
                                .getDescription() );
                    }
                }
            }
        }
        if ( StringUtils.isValidObj( smsTemplatePojo ) && sendSMS )
        {
            inAlertStatusPojo.setSmsTemplateId( smsTemplatePojo.getSmsTemplateId() );
            if ( inAlertStatusPojo.getMobileNo() == 0 )
                inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.MOBILENOMISSING.getDescription() );
            else if ( !inEventTemplatesPojo.isSmsEnabled() )
                inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.ALERTDISABLED.getDescription() );
            else if ( inAlertStatusPojo.getSmsTemplateId() == 0 )
                inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.TEMPLATENOTMAPPED.getDescription() );
            else if ( !StringUtils.equals( smsTemplatePojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.TEMPLATEINACTIVE.getDescription() );
            else
            {
                LOGGER.info( "Initiating sms sending..." );
                if ( StringUtils.isValidObj( paraMeterMap ) )
                {
                    if ( sendSmsAcknowledge( inAlertStatusPojo, smsTemplatePojo, paraMeterMap ) )
                    {
                        LOGGER.info( "SMS Successfully Sent for Event: " + inAlertsDto.getEventsPojo().getEventName() );
                    }
                    else
                    {
                        inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.CONFIGUREDSERVERISSUE
                                .getDescription() );
                    }
                }
            }
        }
    }

    /*
     * Method used to send email and sms on external calls.
     */
    @Override
    public AlertStatusPojo sendAlerts( final ExtAlertDto inExtAlertsDto )
    {
        LOGGER.info( "Inside AlertsTemplateMgrImpl: sendAlerts" + inExtAlertsDto );
        AlertStatusPojo alertStatusPojo = null;
        EmailTemplatePojo emailTemplatePojo = null;
        SmsTemplatePojo smsTemplatePojo = null;
        Map<String, String> paraMeterMap = null;
        if ( StringUtils.isValidObj( inExtAlertsDto ) )
        {
            configService = CRMServicesProxy.getInstance().getCRMConfigService( IGlobalConstants.REMOTE,
                                                                                IGlobalConstants.APP );
            if ( StringUtils.isValidObj( inExtAlertsDto.getParamMap() ) && !inExtAlertsDto.getParamMap().isEmpty() )
            {
                paraMeterMap = inExtAlertsDto.getParamMap();
            }
            alertStatusPojo = new AlertStatusPojo();
            alertStatusPojo.setAlertType( CRMParameter.EXTERNAL.getParameter() );
            if ( StringUtils.isEmpty( inExtAlertsDto.getEmailRecipient() ) )
                alertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.EMAILIDMISSSING.getDescription() );
            else if ( StringUtils.isEmpty( inExtAlertsDto.getEmailTemplate() ) )
                alertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.ALERTDISABLED.getDescription() );
            else
            {
                alertStatusPojo.setEmailId( inExtAlertsDto.getEmailRecipient() );
                emailTemplatePojo = new EmailTemplatePojo();
                emailTemplatePojo.setEmailTemplateName( inExtAlertsDto.getEmailTemplate() );
                emailTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                List<EmailTemplatePojo> emailTemplatePojoList = getAlertTemplateDao()
                        .listEmailTemplate( emailTemplatePojo );
                if ( StringUtils.isValidObj( emailTemplatePojoList ) && !emailTemplatePojoList.isEmpty() )
                {
                    emailTemplatePojo = emailTemplatePojoList.get( 0 );
                    alertStatusPojo.setEmailTempleteId( emailTemplatePojo.getEmailTemplateId() );
                    if ( sendMailAcknowledge( alertStatusPojo, emailTemplatePojo, inExtAlertsDto.getParamMap() ) )
                        LOGGER.info( "Email Successfully Sent using template: "
                                + emailTemplatePojo.getEmailTemplateName() );
                }
                else
                {
                    alertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.TEMPLATEINACTIVE.getDescription() );
                }
            }
            if ( inExtAlertsDto.getSmsRecipient() == 0 )
                alertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.MOBILENOMISSING.getDescription() );
            else if ( StringUtils.isEmpty( inExtAlertsDto.getSmsTemplate() ) )
                alertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.ALERTDISABLED.getDescription() );
            else
            {
                alertStatusPojo.setMobileNo( inExtAlertsDto.getSmsRecipient() );
                smsTemplatePojo = new SmsTemplatePojo();
                smsTemplatePojo.setSmsTemplateName( inExtAlertsDto.getSmsTemplate() );
                smsTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                List<SmsTemplatePojo> smsTemplatePojoList = getAlertTemplateDao().listSMSTemplate( smsTemplatePojo );
                if ( StringUtils.isValidObj( smsTemplatePojoList ) && !smsTemplatePojoList.isEmpty() )
                {
                    smsTemplatePojo = smsTemplatePojoList.get( 0 );
                    alertStatusPojo.setSmsTemplateId( smsTemplatePojo.getSmsTemplateId() );
                    if ( sendSmsAcknowledge( alertStatusPojo, smsTemplatePojo, paraMeterMap ) )
                        LOGGER.info( "SMS Successfully Sent using template: " + smsTemplatePojo.getSmsTemplateName() );
                }
                else
                {
                    alertStatusPojo.setSmsFailureReason( CrmReasonsConstants.TEMPLATEINACTIVE.getDescription() );
                }
            }
        }
        return setSendStatus( alertStatusPojo );
    }

    /*
     * Local Method to fetch parameters from template need to convert in message
     */
    private Set<String> getParameterList( final byte[] inTemplate )
    {
        String msgTemplate = new String( inTemplate );
        String[] str = StringUtils.substringsBetween( msgTemplate, "${", "}" );
        Set<String> parameterSet;
        if ( StringUtils.isValidObj( str ) )
            parameterSet = new HashSet<String>( Arrays.asList( str ) );
        else
            parameterSet = new HashSet<String>();
        return parameterSet;
    }

    /*
     * Local Method to prepare final message using Mail & SMS templates
     */
    private String prepareMessage( String inTemplate, Map<String, String> inMap )
    {
        String finalMessage = null;
        try
        {
            for ( Map.Entry<String, String> entry : inMap.entrySet() )
            {
                if ( StringUtils.isValidObj( entry.getValue() ) )
                {
                    try
                    {
                        String key = StringUtils.trimToEmpty( entry.getKey() );
                        LOGGER.info( "Key::"  +key);
                        if ( StringUtils.equals( key, CRMParameter.USERCURRENTPASSWORD.getParameter() )
                                || StringUtils.equals( key, CRMParameter.MYACCOUNTPASSWORD.getParameter() ) )
                        {
                            finalMessage = inTemplate.replace( "${" + key + "}",
                                                               EncryptionUtil.decrypt( entry.getValue() ) );
                        }
                        else
                        {
                            finalMessage = inTemplate.replace( "${" + key + "}", entry.getValue().toString() );
                        }
                        inTemplate = finalMessage;
                    }
                    catch ( Exception ex )
                    {
                        LOGGER.error( "Exception while replacing parameter by values in Template : ", ex );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while replacing parameter by values in Template : ", ex );
        }
        return finalMessage;
    }

    /*
     * Method to resolve recipient, while it's CRM User or Nextra Consumer.
     */
    private Map<String, String> resolveRecipient( final AlertsDto inAlertsDto, AlertStatusPojo inAlertStatusPojo )
    {
        Map<String, String> parameterMap = new HashMap<String, String>();
        CrmMasterPojo crmMasterPojo = null;
        if ( StringUtils.isValidObj( inAlertsDto.getParamValues() ) && !inAlertsDto.getParamValues().isEmpty() )
        {
            parameterMap.putAll( inAlertsDto.getParamValues() );
        }
        CRMRequestType inParam = CRMRequestType.getRequestType( inAlertsDto.getParamName() );
        if ( null != inParam )
        {
            inAlertStatusPojo.setAlertRequestType( inParam.getRequestCode() );
            inAlertStatusPojo.setMappingId( inAlertsDto.getParamValue() );
            switch ( inParam )
            {
                case USER:
                    parameterMap
                            .putAll( getAlertTemplateDao().getParameterByUserId( inAlertStatusPojo.getMappingId() ) );
                    LOGGER.info( "in resolve method" + parameterMap );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        inAlertStatusPojo.setEmailId( parameterMap.get( CRMParameter.USEREMAILID.getParameter() ) );
                        inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.USERMOBILENO
                                .getParameter() ) ) );
                    }
                    break;
                case LEAD:
                    parameterMap
                            .putAll( getAlertTemplateDao().getParameterByLeadId( inAlertStatusPojo.getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.LEADEMAILID.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setEmailId( parameterMap.get( CRMParameter.LEADEMAILID.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.LEADCONTACTNUMBER.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap
                                    .get( CRMParameter.LEADCONTACTNUMBER.getParameter() ) ) );
                        }
                        else
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.LEADCTINUMBER
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case CAF:
                    parameterMap.putAll( getAlertTemplateDao().getParameterByCrfNumber( inAlertStatusPojo
                                                                                                .getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_RMN.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.CUSTOMER_RMN
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case CUSTOMER_ID:
                    parameterMap.putAll( getAlertTemplateDao().getParameterByCustomerId( inAlertStatusPojo
                                                                                                 .getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_RMN.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.CUSTOMER_RMN
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case PARTNER_MANAGEMENT:
                    parameterMap.putAll( getAlertTemplateDao().getParameterByPartnerId( inAlertStatusPojo
                                                                                                .getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.PARTNEREMAILID.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.PARTNEREMAILID.getParameter() ) );
                        }
                        if ( StringUtils
                                .isNotBlank( parameterMap.get( CRMParameter.PARTNERMOBILENUMBER.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap
                                    .get( CRMParameter.PARTNERMOBILENUMBER.getParameter() ) ) );
                        }
                    }
                    break;
                case QRC:
                    parameterMap.putAll( getAlertTemplateDao().getParameterBySrNo( inAlertStatusPojo.getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        inAlertStatusPojo.setMappingId( parameterMap.get( CRMParameter.CUSTOMER_ID.getParameter() ) );
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_RMN.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.CUSTOMER_RMN
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case LMS_TKT:
                    parameterMap
                            .putAll( getAlertTemplateDao().getParameterByLMSSrNo( inAlertStatusPojo.getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        inAlertStatusPojo.setMappingId( parameterMap.get( CRMParameter.CUSTOMER_ID.getParameter() ) );
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_RMN.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.CUSTOMER_RMN
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case PAYMENT_ID:
                    parameterMap.putAll( getAlertTemplateDao().getParameterByPaymentId( inAlertStatusPojo
                                                                                                .getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.CUSTOMER_EMAIL.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_RMN.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.CUSTOMER_RMN
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case CMS_FILE:
                    crmMasterPojo = getConfigManagerDao().getCMSRecipient( "cms_status_email" );
                    if ( StringUtils.isValidObj( crmMasterPojo ) )
                    {
                        inAlertStatusPojo.setEmailId( crmMasterPojo.getCategoryValue() );
                    }
                    else
                    {
                        inAlertStatusPojo.setEmailId( inAlertsDto.getEmailParameter() );
                    }
                    Map<String, String> fileStatusMap = new HashMap<String, String>();
                    List<Map<String, String>> fileRecordsStatusMap = new ArrayList<Map<String, String>>();
                    fileStatusMap.putAll( getAlertTemplateDao()
                            .getFileStatusByFileId( inAlertStatusPojo.getMappingId() ) );
                    if ( !fileStatusMap.isEmpty() )
                    {
                        parameterMap.put( "CMS_FILE_NAME", fileStatusMap.get( "FILENAME" ) );
                        if ( StringUtils.equals( fileStatusMap.get( "STATUS" ), CRMStatusCode.ARCHIVE.getStatusCode() ) )
                        {
                            if ( StringUtils.equals( fileStatusMap.get( "FILETYPE" ),
                                                     CRMStatusCode.DEPOSIT.getStatusCode() ) )
                            {
                                parameterMap.put( "CMS_FILE_TYPE", CRMStatusCode.DEPOSIT.getStatusDesc() );
                            }
                            else if ( StringUtils.equals( fileStatusMap.get( "FILETYPE" ),
                                                          CRMStatusCode.CLEARANCE.getStatusCode() ) )
                            {
                                parameterMap.put( "CMS_FILE_TYPE", CRMStatusCode.CLEARANCE.getStatusDesc() );
                            }
                            parameterMap.put( "CMS_FILE_STATUS", CRMStatusCode.SUCCESS.getStatusDesc() );
                            fileRecordsStatusMap.addAll( getAlertTemplateDao()
                                    .getFileRecordsStatusByFileId( inAlertStatusPojo.getMappingId() ) );
                        }
                        else if ( StringUtils.equals( fileStatusMap.get( "STATUS" ),
                                                      CRMStatusCode.FAILURE.getStatusCode() ) )
                        {
                            parameterMap.put( "CMS_FILE_STATUS", CRMStatusCode.FAILURE.getStatusDesc() + " - "
                                    + fileStatusMap.get( "FAILREASON" ) );
                        }
                        if ( !fileRecordsStatusMap.isEmpty() )
                        {
                            for ( Map<String, String> map : fileRecordsStatusMap )
                            {
                                if ( StringUtils.equals( map.get( "RECORDSTATUS" ),
                                                         CRMStatusCode.REJECTED.getStatusCode() ) )
                                {
                                    parameterMap.put( "CMS_FAILED_RECORDS", map.get( "COUNT" ) );
                                }
                                else if ( StringUtils.equals( map.get( "RECORDSTATUS" ), "TOTAL" ) )
                                {
                                    parameterMap.put( "CMS_TOTAL_RECORDS", map.get( "COUNT" ) );
                                }
                            }
                        }
                        else
                        {
                            parameterMap.put( "CMS_FAILED_RECORDS", "0" );
                            parameterMap.put( "CMS_TOTAL_RECORDS", "0" );
                        }
                    }
                    break;
                case SLA:
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.RECIPIENTS.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setEmailId( parameterMap.get( CRMParameter.RECIPIENTS.getParameter() ) );
                        }
                    }
                    break;
                case ALERT:
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( inAlertsDto.getEmailParameter() ) ) )
                        {
                            inAlertStatusPojo.setEmailId( parameterMap.get( inAlertsDto.getEmailParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( inAlertsDto.getSmsParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( inAlertsDto
                                    .getSmsParameter() ) ) );
                        }
                    }
                    break;
                case MASS_OUTAGE:
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        parameterMap.putAll( getAlertTemplateDao().getParameterByOutageId( inAlertStatusPojo
                                                                                                   .getMappingId() ) );
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.RECIPIENTS.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setEmailId( parameterMap.get( CRMParameter.RECIPIENTS.getParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.CUSTOMER_RMN.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.CUSTOMER_RMN
                                    .getParameter() ) ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.USERMOBILENO.getParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( CRMParameter.USERMOBILENO
                                    .getParameter() ) ) );
                        }
                    }
                    break;
                case NETWORK_PARTNER:
                    parameterMap.putAll( getAlertTemplateDao()
                            .getNetworkParameterByCustomerId( inAlertStatusPojo.getMappingId() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( CRMParameter.PARTNEREMAILID.getParameter() ) ) )
                        {
                            inAlertStatusPojo
                                    .setEmailId( parameterMap.get( CRMParameter.PARTNEREMAILID.getParameter() ) );
                        }
                    }
                    break;
                default:
                    parameterMap.putAll( getAlertTemplateDao().getParameterByParamValue( inAlertsDto.getParamName(),
                                                                                         inAlertsDto.getParamValue() ) );
                    if ( StringUtils.isValidObj( parameterMap ) )
                    {
                        if ( StringUtils.isNotBlank( parameterMap.get( inAlertsDto.getEmailParameter() ) ) )
                        {
                            inAlertStatusPojo.setEmailId( parameterMap.get( inAlertsDto.getEmailParameter() ) );
                        }
                        if ( StringUtils.isNotBlank( parameterMap.get( inAlertsDto.getSmsParameter() ) ) )
                        {
                            inAlertStatusPojo.setMobileNo( Long.parseLong( parameterMap.get( inAlertsDto
                                    .getSmsParameter() ) ) );
                        }
                    }
                    if ( StringUtils.equals( CRMParameter.INVENTORYEMAIL.getParameter(),
                                             inAlertsDto.getEmailParameter() ) )
                    {
                        crmMasterPojo = getConfigManagerDao().getCMSRecipient( "inventory_email" );
                        if ( StringUtils.isValidObj( crmMasterPojo ) )
                        {
                            inAlertStatusPojo.setEmailId( crmMasterPojo.getCategoryValue() );
                        }
                    }
                    handleParameters( parameterMap, "PGW_PAYMENT_STATUS", CRMStatusCode.class.getSimpleName() );
                    break;
            }
        }
        return parameterMap;
    }

    private void handleParameters( Map<String, String> inParameterMap, String inParamName, String inSimpleName )
    {
        if ( StringUtils.equals( CRMStatusCode.class.getSimpleName(), inSimpleName ) )
        {
            inParameterMap.put( inParamName, CRMStatusCode.getStatus( inParameterMap.get( inParamName ) ) );
        }
    }

    /*
     * Method to extract comma separated input string to email addresses.
     */
    private Address[] getAddressList( final String inAddStr )
    {
        Address[] addresses = null;
        if ( !StringUtils.isEmpty( inAddStr ) )
        {
            String[] inAddresses = inAddStr.split( "," );
            addresses = new Address[inAddresses.length];
            int index = 0;
            for ( String anAddress : inAddresses )
            {
                try
                {
                    if ( null != anAddress )
                    {
                        addresses[index] = new InternetAddress( anAddress.toString() );
                    }
                }
                catch ( AddressException addrEx )
                {
                    LOGGER.error( "AddressException in parsing email address : " + anAddress + " Exception is : "
                            + addrEx );
                }
                index++;
            }
        }
        else
            LOGGER.info( "addresses are empty...skipping parse..." );
        return addresses;
    }

    /*
     * Method to interact with SendEmailUtils to send email and receive status.
     */
    private boolean sendMailAcknowledge( AlertStatusPojo inAlertStatusPojo,
                                         EmailTemplatePojo inEmailTemplatePojo,
                                         final Map<String, String> inMap )
    {
        boolean isSend = false;
        String from = null;
        Address[] to = null;
        Set<EmailCcBccPojo> ccBcc = null;
        String subject = null;
        String message = null;
        List<String> attachment = null;
        if ( inEmailTemplatePojo.getEmailTemplate().length == 0 )
        {
            inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.TEMPLATEEMPTY.getDescription() );
        }
        else
        {
            Set<String> emailList = getParameterList( inEmailTemplatePojo.getEmailTemplate() );
            if ( !emailList.isEmpty() )
            {
                String emailToSend = prepareMessage( new String( inEmailTemplatePojo.getEmailTemplate() ), inMap );
                inEmailTemplatePojo.setEmailTemplate( emailToSend.getBytes() );
            }
            byte[] emailSubTemp = inEmailTemplatePojo.getEmailSubject().getBytes();
            Set<String> subParaList = getParameterList( emailSubTemp );
            if ( !subParaList.isEmpty() )
            {
                String emailSubject = prepareMessage( new String( emailSubTemp ), inMap );
                inEmailTemplatePojo.setEmailSubject( emailSubject );
            }
            try
            {
                configDto = configService.configOperations( ServiceParameter.LIST.getParameter(),
                                                            EmailServerPojo.class.getSimpleName(), null );
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Error retrieving Email Server Configuration.", ex );
            }
            EmailServerPojo emailServerPojo = new EmailServerPojo();
            emailServerPojo.setAlias( inEmailTemplatePojo.getEmailServer() );
            int pojoIndex = configDto.getEmailServers().indexOf( emailServerPojo );
            if ( pojoIndex < 0 )
            {
                inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.SERVERNOTFOUND.getDescription() );
            }
            else
            {
                emailServerPojo = configDto.getEmailServers().get( pojoIndex );
                if ( emailServerPojo.isEnable() )
                {
                    from = inEmailTemplatePojo.getEmailFrom();
                    to = getAddressList( inAlertStatusPojo.getEmailId() );
                    ccBcc = inEmailTemplatePojo.getEmailCcBccs();
                    subject = inEmailTemplatePojo.getEmailSubject();
                    message = new String( inEmailTemplatePojo.getEmailTemplate() );
                    attachment = inEmailTemplatePojo.getAttachmentPaths();
                    SendEmailUtils sendEmailUtils = new SendEmailUtils( emailServerPojo );
                    isSend = sendEmailUtils.sendEmail( from, to, ccBcc, subject, message, attachment );
                    // isSend = false;
                    if ( !isSend )
                    {
                        inAlertStatusPojo.setFromEmail( from );
                        inAlertStatusPojo.setSubject( subject );
                        inAlertStatusPojo.setEmailMessage( inEmailTemplatePojo.getEmailTemplate() );
                        inAlertStatusPojo.setEmailServer( inEmailTemplatePojo.getEmailServer() );
                        StringBuilder ccBuilder = new StringBuilder();
                        StringBuilder bccBuilder = new StringBuilder();
                        for ( EmailCcBccPojo emailCcBccPojo : ccBcc )
                        {
                            if ( emailCcBccPojo.getEmailType().equals( CRMParameter.CC.getParameter() ) )
                            {
                                ccBuilder.append( emailCcBccPojo.getEmailId() ).append( IAppConstants.COMMA );
                            }
                            else if ( emailCcBccPojo.getEmailType().equals( CRMParameter.BCC.getParameter() ) )
                            {
                                bccBuilder.append( emailCcBccPojo.getEmailId() ).append( IAppConstants.COMMA );
                            }
                        }
                        inAlertStatusPojo.setCc( ccBuilder.toString() );
                        inAlertStatusPojo.setBcc( bccBuilder.toString() );
                        /*
                         * attachment = new ArrayList<String>(); attachment.add(
                         * "F:/CRM-Master.xls" ); attachment.add(
                         * "F:/Nextra-Doc/NP-Email SMS Engine Module -FRD v1.2.pdf"
                         * );
                         */
                        if ( CommonValidator.isValidCollection( attachment ) )
                        {
                            // LOGGER.info( "Attachment size:: "
                            // +attachment.size());
                            Set<CrmEmailAttachment> crmEmailAttachments = new HashSet<CrmEmailAttachment>();
                            CrmEmailAttachment crmEmailAttachment = null;
                            for ( String attachments : attachment )
                            {
                                crmEmailAttachment = new CrmEmailAttachment();
                                if ( StringUtils.endsWith( attachments, ".pdf" ) )
                                {
                                    crmEmailAttachment.setAttachmentPath( attachments );
                                }
                                else
                                {
                                    File f = new File( attachments );
                                    LOGGER.info( "File path:: " + f.getAbsolutePath() + " Name " + f.getName() );
                                    try
                                    {
                                        crmEmailAttachment.setAttachment( read( f ) );
                                        crmEmailAttachment.setFileName( f.getName() );
                                    }
                                    catch ( IOException e )
                                    {
                                        e.printStackTrace();
                                    }
                                }
                                crmEmailAttachments.add( crmEmailAttachment );
                            }
                            inAlertStatusPojo.setCrmEmailAttachments( crmEmailAttachments );
                        }
                    }
                }
                else
                {
                    inAlertStatusPojo.setEmailSentFailureReason( CrmReasonsConstants.SERVERALERTSOFF.getDescription() );
                }
            }
        }
        return isSend;
    }

    public static byte[] read( File file )
        throws IOException
    {
        ByteArrayOutputStream ous = null;
        InputStream ios = null;
        try
        {
            byte[] buffer = new byte[4096];
            ous = new ByteArrayOutputStream();
            ios = new FileInputStream( file );
            int read = 0;
            while ( ( read = ios.read( buffer ) ) != -1 )
            {
                ous.write( buffer, 0, read );
            }
        }
        finally
        {
            try
            {
                if ( ous != null )
                    ous.close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
            try
            {
                if ( ios != null )
                    ios.close();
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
        return ous.toByteArray();
    }

    /*
     * Method to interact with SendSmsUtils to send sms and receive status.
     */
    private boolean sendSmsAcknowledge( AlertStatusPojo inAlertStatusPojo,
                                        SmsTemplatePojo inSmsTemplatePojo,
                                        final Map<String, String> inMap )
    {
        boolean isSend = false;
        String message = null;
        if ( inSmsTemplatePojo.getSmsTemplate().length == 0 )
        {
            inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.TEMPLATEEMPTY.getDescription() );
        }
        else
        {
            byte[] smsTemplate = inSmsTemplatePojo.getSmsTemplate();
            Set<String> smsList = getParameterList( smsTemplate );
            if ( !smsList.isEmpty() )
            {
                String smsToSend = prepareMessage( new String( smsTemplate ), inMap );
                inSmsTemplatePojo.setSmsTemplate( smsToSend.getBytes() );
            }
            try
            {
                configDto = configService.configOperations( ServiceParameter.LIST.getParameter(),
                                                            SmsGatewayPojo.class.getSimpleName(), null );
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Error retrieving SMS Server Configuration.", ex );
            }
            SmsGatewayPojo smsGatewayPojo = new SmsGatewayPojo();
            smsGatewayPojo.setAlias( inSmsTemplatePojo.getSmsGateway() );
            int pojoIndex = configDto.getSmsGateways().indexOf( smsGatewayPojo );
            if ( pojoIndex < 0 )
            {
                inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.SERVERNOTFOUND.getDescription() );
            }
            else
            {
                smsGatewayPojo = configDto.getSmsGateways().get( pojoIndex );
                if ( smsGatewayPojo.isEnable() )
                {
                    message = new String( inSmsTemplatePojo.getSmsTemplate() );
                    SendSmsUtils sendSmsUtils = new SendSmsUtils( smsGatewayPojo );
                    isSend = sendSmsUtils.sendSms( String.valueOf( inAlertStatusPojo.getMobileNo() ), message );
                    if ( !isSend )
                    {
                        inAlertStatusPojo.setSmsMessage( inSmsTemplatePojo.getSmsTemplate() );
                        inAlertStatusPojo.setSmsServer( inSmsTemplatePojo.getSmsGateway() );
                    }
                }
                else
                    inAlertStatusPojo.setSmsSentFailureReason( CrmReasonsConstants.SERVERALERTSOFF.getDescription() );
            }
        }
        return isSend;
    }

    private AlertStatusPojo setSendStatus( AlertStatusPojo inAlertStatusPojo )
    {
        try
        {
            String emailStatus = inAlertStatusPojo.getEmailFailureReason();
            if ( StringUtils.isEmpty( emailStatus ) )
                inAlertStatusPojo.setEmailSent( CRMParameter.YES.getParameter() );
            else
                inAlertStatusPojo.setEmailSent( CRMParameter.NO.getParameter() );
            String smsStatus = inAlertStatusPojo.getSmsFailureReason();
            if ( StringUtils.isEmpty( smsStatus ) )
                inAlertStatusPojo.setSmsSent( CRMParameter.YES.getParameter() );
            else
                inAlertStatusPojo.setSmsSent( CRMParameter.NO.getParameter() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while setting send status : ", ex );
        }
        return inAlertStatusPojo;
    }

    @Override
    public AlertsDto alertOperations( String inServiceParam, String inAlertType, AlertsDto inAlertsDto )
    {
        AlertMasterPojo alertMasterPojo = null;
        if ( StringUtils.equals( CRMParameter.PENDING_ALERTS.getParameter(), inServiceParam ) )
        {
            try
            {
                configService = CRMServicesProxy.getInstance().getCRMConfigService( IGlobalConstants.REMOTE,
                                                                                    IGlobalConstants.APP );
                alertMasterPojo = configService
                        .configOperations( CRMParameter.ALERTS_MASTER.getParameter(), null, null ).getAlertMasterPojo();
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Error while retrieving AlertMasterPojo :", ex );
            }
            if ( StringUtils.isValidObj( alertMasterPojo ) )
            {
                inAlertsDto.setAlertStatusPojos( getAlertStatusPojos( alertMasterPojo.getMax_retry() ) );
            }
        }
        return inAlertsDto;
    }

    private List<AlertStatusPojo> getAlertStatusPojos( int inMaxRetry )
    {
        return getAlertTemplateDao().getAlertStatusPojos( inMaxRetry );
    }
}
