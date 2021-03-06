package com.np.tele.crm.pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlTransient;

/**
 * AlertStatus generated by hbm2java
 */
public class AlertStatusPojo
    implements java.io.Serializable
{
    private long                    alertId;
    private String                  mappingId;
    private String                  alertRequestType;
    private String                  emailSent;
    private String                  emailFailureReason;
    private String                  smsSent;
    private String                  smsFailureReason;
    private String                  emailId;
    private long                    mobileNo;
    private long                    eventId;
    private long                    smsTemplateId;
    private long                    emailTempleteId;
    private Date                    sentDateTime;
    private Date                    smsDeliveryDateTime;
    private String                  alertType;
    private long                    eventTemplateId;
    private Byte                    smsRetry;
    private Byte                    emailRetry;
    private String                  fromEmail;
    private String                  cc;
    private String                  bcc;
    private String                  subject;
    private byte[]                  emailMessage;
    private String                  emailServer;
    private byte[]                  smsMessage;
    private String                  smsServer;
    private Set<CrmEmailAttachment> crmEmailAttachments = new HashSet<CrmEmailAttachment>();

    public long getAlertId()
    {
        return alertId;
    }

    public void setAlertId( long alertId )
    {
        this.alertId = alertId;
    }

    public long getSmsTemplateId()
    {
        return smsTemplateId;
    }

    public void setSmsTemplateId( long smsTemplateId )
    {
        this.smsTemplateId = smsTemplateId;
    }

    public long getEmailTempleteId()
    {
        return emailTempleteId;
    }

    public void setEmailTempleteId( long emailTempleteId )
    {
        this.emailTempleteId = emailTempleteId;
    }

    public long getEventTemplateId()
    {
        return eventTemplateId;
    }

    public void setEventTemplateId( long eventTemplateId )
    {
        this.eventTemplateId = eventTemplateId;
    }

    public void setEventId( long eventId )
    {
        this.eventId = eventId;
    }

    public Byte getSmsRetry()
    {
        return smsRetry;
    }

    public void setSmsRetry( Byte smsRetry )
    {
        this.smsRetry = smsRetry;
    }

    public Byte getEmailRetry()
    {
        return emailRetry;
    }

    public void setEmailRetry( Byte emailRetry )
    {
        this.emailRetry = emailRetry;
    }

    public String getEmailFailureReason()
    {
        return emailFailureReason;
    }

    public void setEmailFailureReason( String inEmailFailureReason )
    {
        emailFailureReason = inEmailFailureReason;
    }

    public String getSmsFailureReason()
    {
        return smsFailureReason;
    }

    public void setSmsFailureReason( String inSmsFailureReason )
    {
        smsFailureReason = inSmsFailureReason;
    }

    public String getAlertType()
    {
        return alertType;
    }

    public void setAlertType( String inAlertType )
    {
        alertType = inAlertType;
    }

    public String getEmailSentFailureReason()
    {
        return emailFailureReason;
    }

    public void setEmailSentFailureReason( String inEmailSentFailureReason )
    {
        emailFailureReason = inEmailSentFailureReason;
    }

    public String getSmsSentFailureReason()
    {
        return smsFailureReason;
    }

    public void setSmsSentFailureReason( String inSmsSentFailureReason )
    {
        smsFailureReason = inSmsSentFailureReason;
    }

    public String getEmailSent()
    {
        return this.emailSent;
    }

    public void setEmailSent( String emailSent )
    {
        this.emailSent = emailSent;
    }

    public String getSmsSent()
    {
        return this.smsSent;
    }

    public void setSmsSent( String smsSent )
    {
        this.smsSent = smsSent;
    }

    public String getEmailId()
    {
        return this.emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    public long getMobileNo()
    {
        return this.mobileNo;
    }

    public void setMobileNo( long mobileNo )
    {
        this.mobileNo = mobileNo;
    }

    public long getEventId()
    {
        return this.eventId;
    }

    public Date getSentDateTime()
    {
        return this.sentDateTime;
    }

    public void setSentDateTime( Date sentDateTime )
    {
        this.sentDateTime = sentDateTime;
    }

    public Date getSmsDeliveryDateTime()
    {
        return this.smsDeliveryDateTime;
    }

    public void setSmsDeliveryDateTime( Date smsDeliveryDateTime )
    {
        this.smsDeliveryDateTime = smsDeliveryDateTime;
    }

    public String getMappingId()
    {
        return mappingId;
    }

    public void setMappingId( String inMappingId )
    {
        mappingId = inMappingId;
    }

    public String getAlertRequestType()
    {
        return alertRequestType;
    }

    public void setAlertRequestType( String inAlertRequestType )
    {
        alertRequestType = inAlertRequestType;
    }

    public String getFromEmail()
    {
        return fromEmail;
    }

    public void setFromEmail( String fromEmail )
    {
        this.fromEmail = fromEmail;
    }

    public String getCc()
    {
        return cc;
    }

    public void setCc( String cc )
    {
        this.cc = cc;
    }

    public String getBcc()
    {
        return bcc;
    }

    public void setBcc( String bcc )
    {
        this.bcc = bcc;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject( String subject )
    {
        this.subject = subject;
    }

    public String getEmailServer()
    {
        return emailServer;
    }

    public void setEmailServer( String emailServer )
    {
        this.emailServer = emailServer;
    }

    public byte[] getEmailMessage()
    {
        return emailMessage;
    }

    public void setEmailMessage( byte[] emailMessage )
    {
        this.emailMessage = emailMessage;
    }

    public byte[] getSmsMessage()
    {
        return smsMessage;
    }

    public void setSmsMessage( byte[] smsMessage )
    {
        this.smsMessage = smsMessage;
    }

    public String getSmsServer()
    {
        return smsServer;
    }

    public void setSmsServer( String smsServer )
    {
        this.smsServer = smsServer;
    }

    @XmlTransient
    public Set<CrmEmailAttachment> getCrmEmailAttachments()
    {
        return crmEmailAttachments;
    }

    public void setCrmEmailAttachments( Set<CrmEmailAttachment> crmEmailAttachments )
    {
        this.crmEmailAttachments = crmEmailAttachments;
    }
}
