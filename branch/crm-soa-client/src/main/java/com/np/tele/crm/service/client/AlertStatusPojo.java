
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for alertStatusPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alertStatusPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alertId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="alertRequestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alertType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bcc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailFailureReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailMessage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="emailRetry" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="emailSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailSentFailureReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailTempleteId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="eventTemplateId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fromEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mappingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="sentDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="smsDeliveryDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="smsFailureReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsMessage" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="smsRetry" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="smsSent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsSentFailureReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsTemplateId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="subject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alertStatusPojo", propOrder = {
    "alertId",
    "alertRequestType",
    "alertType",
    "bcc",
    "cc",
    "emailFailureReason",
    "emailId",
    "emailMessage",
    "emailRetry",
    "emailSent",
    "emailSentFailureReason",
    "emailServer",
    "emailTempleteId",
    "eventId",
    "eventTemplateId",
    "fromEmail",
    "mappingId",
    "mobileNo",
    "sentDateTime",
    "smsDeliveryDateTime",
    "smsFailureReason",
    "smsMessage",
    "smsRetry",
    "smsSent",
    "smsSentFailureReason",
    "smsServer",
    "smsTemplateId",
    "subject"
})
public class AlertStatusPojo {

    protected long alertId;
    protected String alertRequestType;
    protected String alertType;
    protected String bcc;
    protected String cc;
    protected String emailFailureReason;
    protected String emailId;
    protected byte[] emailMessage;
    protected Byte emailRetry;
    protected String emailSent;
    protected String emailSentFailureReason;
    protected String emailServer;
    protected long emailTempleteId;
    protected long eventId;
    protected long eventTemplateId;
    protected String fromEmail;
    protected String mappingId;
    protected long mobileNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar sentDateTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar smsDeliveryDateTime;
    protected String smsFailureReason;
    protected byte[] smsMessage;
    protected Byte smsRetry;
    protected String smsSent;
    protected String smsSentFailureReason;
    protected String smsServer;
    protected long smsTemplateId;
    protected String subject;

    /**
     * Gets the value of the alertId property.
     * 
     */
    public long getAlertId() {
        return alertId;
    }

    /**
     * Sets the value of the alertId property.
     * 
     */
    public void setAlertId(long value) {
        this.alertId = value;
    }

    /**
     * Gets the value of the alertRequestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertRequestType() {
        return alertRequestType;
    }

    /**
     * Sets the value of the alertRequestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertRequestType(String value) {
        this.alertRequestType = value;
    }

    /**
     * Gets the value of the alertType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * Sets the value of the alertType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertType(String value) {
        this.alertType = value;
    }

    /**
     * Gets the value of the bcc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBcc() {
        return bcc;
    }

    /**
     * Sets the value of the bcc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBcc(String value) {
        this.bcc = value;
    }

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCc(String value) {
        this.cc = value;
    }

    /**
     * Gets the value of the emailFailureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailFailureReason() {
        return emailFailureReason;
    }

    /**
     * Sets the value of the emailFailureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailFailureReason(String value) {
        this.emailFailureReason = value;
    }

    /**
     * Gets the value of the emailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the value of the emailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailId(String value) {
        this.emailId = value;
    }

    /**
     * Gets the value of the emailMessage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEmailMessage() {
        return emailMessage;
    }

    /**
     * Sets the value of the emailMessage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEmailMessage(byte[] value) {
        this.emailMessage = ((byte[]) value);
    }

    /**
     * Gets the value of the emailRetry property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getEmailRetry() {
        return emailRetry;
    }

    /**
     * Sets the value of the emailRetry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setEmailRetry(Byte value) {
        this.emailRetry = value;
    }

    /**
     * Gets the value of the emailSent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSent() {
        return emailSent;
    }

    /**
     * Sets the value of the emailSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSent(String value) {
        this.emailSent = value;
    }

    /**
     * Gets the value of the emailSentFailureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSentFailureReason() {
        return emailSentFailureReason;
    }

    /**
     * Sets the value of the emailSentFailureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSentFailureReason(String value) {
        this.emailSentFailureReason = value;
    }

    /**
     * Gets the value of the emailServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailServer() {
        return emailServer;
    }

    /**
     * Sets the value of the emailServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailServer(String value) {
        this.emailServer = value;
    }

    /**
     * Gets the value of the emailTempleteId property.
     * 
     */
    public long getEmailTempleteId() {
        return emailTempleteId;
    }

    /**
     * Sets the value of the emailTempleteId property.
     * 
     */
    public void setEmailTempleteId(long value) {
        this.emailTempleteId = value;
    }

    /**
     * Gets the value of the eventId property.
     * 
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     */
    public void setEventId(long value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the eventTemplateId property.
     * 
     */
    public long getEventTemplateId() {
        return eventTemplateId;
    }

    /**
     * Sets the value of the eventTemplateId property.
     * 
     */
    public void setEventTemplateId(long value) {
        this.eventTemplateId = value;
    }

    /**
     * Gets the value of the fromEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromEmail() {
        return fromEmail;
    }

    /**
     * Sets the value of the fromEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromEmail(String value) {
        this.fromEmail = value;
    }

    /**
     * Gets the value of the mappingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMappingId() {
        return mappingId;
    }

    /**
     * Sets the value of the mappingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMappingId(String value) {
        this.mappingId = value;
    }

    /**
     * Gets the value of the mobileNo property.
     * 
     */
    public long getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     */
    public void setMobileNo(long value) {
        this.mobileNo = value;
    }

    /**
     * Gets the value of the sentDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSentDateTime() {
        return sentDateTime;
    }

    /**
     * Sets the value of the sentDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSentDateTime(XMLGregorianCalendar value) {
        this.sentDateTime = value;
    }

    /**
     * Gets the value of the smsDeliveryDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSmsDeliveryDateTime() {
        return smsDeliveryDateTime;
    }

    /**
     * Sets the value of the smsDeliveryDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSmsDeliveryDateTime(XMLGregorianCalendar value) {
        this.smsDeliveryDateTime = value;
    }

    /**
     * Gets the value of the smsFailureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsFailureReason() {
        return smsFailureReason;
    }

    /**
     * Sets the value of the smsFailureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsFailureReason(String value) {
        this.smsFailureReason = value;
    }

    /**
     * Gets the value of the smsMessage property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getSmsMessage() {
        return smsMessage;
    }

    /**
     * Sets the value of the smsMessage property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setSmsMessage(byte[] value) {
        this.smsMessage = ((byte[]) value);
    }

    /**
     * Gets the value of the smsRetry property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getSmsRetry() {
        return smsRetry;
    }

    /**
     * Sets the value of the smsRetry property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setSmsRetry(Byte value) {
        this.smsRetry = value;
    }

    /**
     * Gets the value of the smsSent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsSent() {
        return smsSent;
    }

    /**
     * Sets the value of the smsSent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsSent(String value) {
        this.smsSent = value;
    }

    /**
     * Gets the value of the smsSentFailureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsSentFailureReason() {
        return smsSentFailureReason;
    }

    /**
     * Sets the value of the smsSentFailureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsSentFailureReason(String value) {
        this.smsSentFailureReason = value;
    }

    /**
     * Gets the value of the smsServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsServer() {
        return smsServer;
    }

    /**
     * Sets the value of the smsServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsServer(String value) {
        this.smsServer = value;
    }

    /**
     * Gets the value of the smsTemplateId property.
     * 
     */
    public long getSmsTemplateId() {
        return smsTemplateId;
    }

    /**
     * Sets the value of the smsTemplateId property.
     * 
     */
    public void setSmsTemplateId(long value) {
        this.smsTemplateId = value;
    }

    /**
     * Gets the value of the subject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the value of the subject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubject(String value) {
        this.subject = value;
    }

}
