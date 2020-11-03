package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.np.tele.crm.pojos.AlertStatusPojo;
import com.np.tele.crm.pojos.EmailCcBccPojo;
import com.np.tele.crm.pojos.EmailTemplatePojo;
import com.np.tele.crm.pojos.EventTemplatesPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.tele.crm.pojos.SmsTemplatePojo;

public class AlertsDto
    implements Serializable
{
    private String                         statusCode         = null;
    private String                         statusDesc         = null;
    private String                         clientIPAddress    = null;
    private String                         serverIPAddress    = null;
    private SmsTemplatePojo                smsTemplatePojo    = null;
    private List<SmsTemplatePojo>          smsTemplatePojos   = null;
    private Map<String, SmsTemplatePojo>   smsTemplateMap     = null;
    private EmailTemplatePojo              emailTemplatePojo  = null;
    private List<EmailTemplatePojo>        emailTemplatePojos = null;
    private Map<String, EmailTemplatePojo> emailTemplateMap   = null;
    private EmailCcBccPojo                 emailCcBccPojo     = null;
    private Set<EmailCcBccPojo>            emailCcBccPojoSet  = null;
    private EventTemplatesPojo             eventTemplatesPojo = null;
    private List<EventTemplatesPojo>       evTempalteList     = null;
    private EventsPojo                     eventsPojo         = null;
    private List<EventsPojo>               eventsPojoList     = null;
    private String                         userId             = null;
    private String                         crfNumber          = null;
    private String                         customerId         = null;
    private String                         leadId             = null;
    private AlertStatusPojo                alertStatusPojo    = null;
    private List<AlertStatusPojo>          alertStatusPojos   = null;
    private boolean                        scheduler          = false;
    private String                         partnerId          = null;
    private String                         srNoQrc            = null;
    private List<String>                   attachmentPaths    = null;
    private String                         paramName          = null;
    private String                         paramValue         = null;
    private String                         emailParameter     = null;
    private String                         smsParameter       = null;
    private Map<String, String>            paramValues        = null;
    private String                         paymentId          = null;
    private List<String>                   userAssociatedServices;
    private List<String>                   userAssociatedPartners;

    public List<String> getUserAssociatedServices()
    {
        return userAssociatedServices;
    }

    public void setUserAssociatedServices( List<String> inUserAssociatedServices )
    {
        userAssociatedServices = inUserAssociatedServices;
    }

    public List<String> getUserAssociatedPartners()
    {
        return userAssociatedPartners;
    }

    public void setUserAssociatedPartners( List<String> inUserAssociatedPartners )
    {
        userAssociatedPartners = inUserAssociatedPartners;
    }

    public List<String> getAttachmentPaths()
    {
        return attachmentPaths;
    }

    public void setAttachmentPaths( List<String> inAttachmentPaths )
    {
        attachmentPaths = inAttachmentPaths;
    }

    public String getSrNoQrc()
    {
        return srNoQrc;
    }

    public void setSrNoQrc( String inSrNoQrc )
    {
        srNoQrc = inSrNoQrc;
    }

    public String getLeadId()
    {
        return leadId;
    }

    public void setLeadId( String inLeadId )
    {
        leadId = inLeadId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    public String getCrfNumber()
    {
        return crfNumber;
    }

    public void setCrfNumber( String inCrfNumber )
    {
        crfNumber = inCrfNumber;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public Map<String, SmsTemplatePojo> getSmsTemplateMap()
    {
        return smsTemplateMap;
    }

    public void setSmsTemplateMap( Map<String, SmsTemplatePojo> inSmsTemplateMap )
    {
        smsTemplateMap = inSmsTemplateMap;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public SmsTemplatePojo getSmsTemplatePojo()
    {
        return smsTemplatePojo;
    }

    public void setSmsTemplatePojo( SmsTemplatePojo inSmsTemplatePojo )
    {
        smsTemplatePojo = inSmsTemplatePojo;
    }

    public List<SmsTemplatePojo> getSmsTemplatePojos()
    {
        return smsTemplatePojos;
    }

    public void setSmsTemplatePojos( List<SmsTemplatePojo> inSmsTemplatePojos )
    {
        smsTemplatePojos = inSmsTemplatePojos;
    }

    public EmailTemplatePojo getEmailTemplatePojo()
    {
        return emailTemplatePojo;
    }

    public void setEmailTemplatePojo( EmailTemplatePojo emailTemplatePojo )
    {
        this.emailTemplatePojo = emailTemplatePojo;
    }

    public List<EmailTemplatePojo> getEmailTemplatePojos()
    {
        return emailTemplatePojos;
    }

    public void setEmailTemplatePojos( List<EmailTemplatePojo> emailTemplatePojos )
    {
        this.emailTemplatePojos = emailTemplatePojos;
    }

    public Map<String, EmailTemplatePojo> getEmailTemplateMap()
    {
        return emailTemplateMap;
    }

    public void setEmailTemplateMap( Map<String, EmailTemplatePojo> inEmailTemplateMap )
    {
        emailTemplateMap = inEmailTemplateMap;
    }

    public EmailCcBccPojo getEmailCcBccPojo()
    {
        return emailCcBccPojo;
    }

    public void setEmailCcBccPojo( EmailCcBccPojo inEmailCcBccPojo )
    {
        emailCcBccPojo = inEmailCcBccPojo;
    }

    public Set<EmailCcBccPojo> getEmailCcBccPojoSet()
    {
        return emailCcBccPojoSet;
    }

    public void setEmailCcBccPojoSet( Set<EmailCcBccPojo> inEmailCcBccPojoSet )
    {
        emailCcBccPojoSet = inEmailCcBccPojoSet;
    }

    public EventTemplatesPojo getEventTemplatesPojo()
    {
        return eventTemplatesPojo;
    }

    public void setEventTemplatesPojo( EventTemplatesPojo inEventTemplatesPojo )
    {
        eventTemplatesPojo = inEventTemplatesPojo;
    }

    public List<EventTemplatesPojo> getEvTempalteList()
    {
        return evTempalteList;
    }

    public void setEvTempalteList( List<EventTemplatesPojo> inEvTempalteList )
    {
        evTempalteList = inEvTempalteList;
    }

    public EventsPojo getEventsPojo()
    {
        return eventsPojo;
    }

    public void setEventsPojo( EventsPojo inEventsPojo )
    {
        eventsPojo = inEventsPojo;
    }

    public List<EventsPojo> getEventsPojoList()
    {
        return eventsPojoList;
    }

    public void setEventsPojoList( List<EventsPojo> inEventsPojoList )
    {
        eventsPojoList = inEventsPojoList;
    }

    public AlertStatusPojo getAlertStatusPojo()
    {
        return alertStatusPojo;
    }

    public void setAlertStatusPojo( AlertStatusPojo inAlertStatusPojo )
    {
        alertStatusPojo = inAlertStatusPojo;
    }

    public List<AlertStatusPojo> getAlertStatusPojos()
    {
        return alertStatusPojos;
    }

    public void setAlertStatusPojos( List<AlertStatusPojo> inAlertStatusPojos )
    {
        alertStatusPojos = inAlertStatusPojos;
    }

    public boolean isScheduler()
    {
        return scheduler;
    }

    public void setScheduler( boolean inScheduler )
    {
        scheduler = inScheduler;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( String inPartnerId )
    {
        partnerId = inPartnerId;
    }

    public String getParamName()
    {
        return paramName;
    }

    public void setParamName( String inParamName )
    {
        paramName = inParamName;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue( String inParamValue )
    {
        paramValue = inParamValue;
    }

    public String getEmailParameter()
    {
        return emailParameter;
    }

    public void setEmailParameter( String inEmailParameter )
    {
        emailParameter = inEmailParameter;
    }

    public String getSmsParameter()
    {
        return smsParameter;
    }

    public void setSmsParameter( String inSmsParameter )
    {
        smsParameter = inSmsParameter;
    }

    public Map<String, String> getParamValues()
    {
        return paramValues;
    }

    public String getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId( String inPaymentId )
    {
        paymentId = inPaymentId;
    }

    public void setParamValues( Map<String, String> inParamValues )
    {
        paramValues = inParamValues;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AlertsDto [statusCode=" ).append( statusCode ).append( ", statusDesc=" ).append( statusDesc )
                .append( ", clientIPAddress=" ).append( clientIPAddress ).append( ", serverIPAddress=" )
                .append( serverIPAddress ).append( ", smsTemplatePojo=" ).append( smsTemplatePojo )
                .append( ", smsTemplatePojos=" ).append( smsTemplatePojos ).append( ", smsTemplateMap=" )
                .append( smsTemplateMap ).append( ", emailTemplatePojo=" ).append( emailTemplatePojo )
                .append( ", emailTemplatePojos=" ).append( emailTemplatePojos ).append( ", emailTemplateMap=" )
                .append( emailTemplateMap ).append( ", emailCcBccPojo=" ).append( emailCcBccPojo )
                .append( ", emailCcBccPojoSet=" ).append( emailCcBccPojoSet ).append( ", eventTemplatesPojo=" )
                .append( eventTemplatesPojo ).append( ", evTempalteList=" ).append( evTempalteList )
                .append( ", eventsPojo=" ).append( eventsPojo ).append( ", eventsPojoList=" ).append( eventsPojoList )
                .append( ", userId=" ).append( userId ).append( ", crfNumber=" ).append( crfNumber )
                .append( ", customerId=" ).append( customerId ).append( ", leadId=" ).append( leadId )
                .append( ", alertStatusPojo=" ).append( alertStatusPojo ).append( ", alertStatusPojos=" )
                .append( alertStatusPojos ).append( ", scheduler=" ).append( scheduler ).append( ", partnerId=" )
                .append( partnerId ).append( ", srNoQrc=" ).append( srNoQrc ).append( ", attachmentPaths=" )
                .append( attachmentPaths ).append( ", paramValue=" ).append( paramValue ).append( ", emailParameter=" )
                .append( emailParameter ).append( ", smsParameter=" ).append( smsParameter ).append( ", paramValues=" )
                .append( paramValues ).append( "]" );
        return builder.toString();
    }
}
