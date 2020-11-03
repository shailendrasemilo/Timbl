
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for eventTemplatesPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eventTemplatesPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="emailEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="emailTemplate" type="{http://service.alerts.crm.tele.np.com/}emailTemplatePojo" minOccurs="0"/>
 *         &lt;element name="eventTemplateId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="events" type="{http://service.alerts.crm.tele.np.com/}eventsPojo" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="smsEnabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="smsTemplate" type="{http://service.alerts.crm.tele.np.com/}smsTemplatePojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "eventTemplatesPojo", propOrder = {
    "createdBy",
    "createdTime",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "editable",
    "emailEnabled",
    "emailTemplate",
    "eventTemplateId",
    "events",
    "lastModifiedBy",
    "lastModifiedTime",
    "smsEnabled",
    "smsTemplate"
})
public class EventTemplatesPojo {

    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    protected boolean editable;
    protected boolean emailEnabled;
    protected EmailTemplatePojo emailTemplate;
    protected long eventTemplateId;
    protected EventsPojo events;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected boolean smsEnabled;
    protected SmsTemplatePojo smsTemplate;

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the createdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the value of the createdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTime(XMLGregorianCalendar value) {
        this.createdTime = value;
    }

    /**
     * Gets the value of the displayCreatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayCreatedTime() {
        return displayCreatedTime;
    }

    /**
     * Sets the value of the displayCreatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayCreatedTime(String value) {
        this.displayCreatedTime = value;
    }

    /**
     * Gets the value of the displayLastModifiedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayLastModifiedTime() {
        return displayLastModifiedTime;
    }

    /**
     * Sets the value of the displayLastModifiedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayLastModifiedTime(String value) {
        this.displayLastModifiedTime = value;
    }

    /**
     * Gets the value of the editable property.
     * 
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     * 
     */
    public void setEditable(boolean value) {
        this.editable = value;
    }

    /**
     * Gets the value of the emailEnabled property.
     * 
     */
    public boolean isEmailEnabled() {
        return emailEnabled;
    }

    /**
     * Sets the value of the emailEnabled property.
     * 
     */
    public void setEmailEnabled(boolean value) {
        this.emailEnabled = value;
    }

    /**
     * Gets the value of the emailTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link EmailTemplatePojo }
     *     
     */
    public EmailTemplatePojo getEmailTemplate() {
        return emailTemplate;
    }

    /**
     * Sets the value of the emailTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailTemplatePojo }
     *     
     */
    public void setEmailTemplate(EmailTemplatePojo value) {
        this.emailTemplate = value;
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
     * Gets the value of the events property.
     * 
     * @return
     *     possible object is
     *     {@link EventsPojo }
     *     
     */
    public EventsPojo getEvents() {
        return events;
    }

    /**
     * Sets the value of the events property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventsPojo }
     *     
     */
    public void setEvents(EventsPojo value) {
        this.events = value;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedBy(String value) {
        this.lastModifiedBy = value;
    }

    /**
     * Gets the value of the lastModifiedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Sets the value of the lastModifiedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedTime(XMLGregorianCalendar value) {
        this.lastModifiedTime = value;
    }

    /**
     * Gets the value of the smsEnabled property.
     * 
     */
    public boolean isSmsEnabled() {
        return smsEnabled;
    }

    /**
     * Sets the value of the smsEnabled property.
     * 
     */
    public void setSmsEnabled(boolean value) {
        this.smsEnabled = value;
    }

    /**
     * Gets the value of the smsTemplate property.
     * 
     * @return
     *     possible object is
     *     {@link SmsTemplatePojo }
     *     
     */
    public SmsTemplatePojo getSmsTemplate() {
        return smsTemplate;
    }

    /**
     * Sets the value of the smsTemplate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmsTemplatePojo }
     *     
     */
    public void setSmsTemplate(SmsTemplatePojo value) {
        this.smsTemplate = value;
    }

}
