
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for alertsDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alertsDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alertStatusPojo" type="{http://service.alerts.crm.tele.np.com/}alertStatusPojo" minOccurs="0"/>
 *         &lt;element name="alertStatusPojos" type="{http://service.alerts.crm.tele.np.com/}alertStatusPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="attachmentPaths" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailCcBccPojo" type="{http://service.alerts.crm.tele.np.com/}emailCcBccPojo" minOccurs="0"/>
 *         &lt;element name="emailCcBccPojoSet" type="{http://service.alerts.crm.tele.np.com/}emailCcBccPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="emailParameter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailTemplateMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://service.alerts.crm.tele.np.com/}emailTemplatePojo" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="emailTemplatePojo" type="{http://service.alerts.crm.tele.np.com/}emailTemplatePojo" minOccurs="0"/>
 *         &lt;element name="emailTemplatePojos" type="{http://service.alerts.crm.tele.np.com/}emailTemplatePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="evTempalteList" type="{http://service.alerts.crm.tele.np.com/}eventTemplatesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventTemplatesPojo" type="{http://service.alerts.crm.tele.np.com/}eventTemplatesPojo" minOccurs="0"/>
 *         &lt;element name="eventsPojo" type="{http://service.alerts.crm.tele.np.com/}eventsPojo" minOccurs="0"/>
 *         &lt;element name="eventsPojoList" type="{http://service.alerts.crm.tele.np.com/}eventsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="leadId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paramValues">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="scheduler" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsParameter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsTemplateMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://service.alerts.crm.tele.np.com/}smsTemplatePojo" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="smsTemplatePojo" type="{http://service.alerts.crm.tele.np.com/}smsTemplatePojo" minOccurs="0"/>
 *         &lt;element name="smsTemplatePojos" type="{http://service.alerts.crm.tele.np.com/}smsTemplatePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="srNoQrc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alertsDto", propOrder = {
    "alertStatusPojo",
    "alertStatusPojos",
    "attachmentPaths",
    "clientIPAddress",
    "crfNumber",
    "customerId",
    "emailCcBccPojo",
    "emailCcBccPojoSet",
    "emailParameter",
    "emailTemplateMap",
    "emailTemplatePojo",
    "emailTemplatePojos",
    "evTempalteList",
    "eventTemplatesPojo",
    "eventsPojo",
    "eventsPojoList",
    "leadId",
    "paramName",
    "paramValue",
    "paramValues",
    "partnerId",
    "paymentId",
    "scheduler",
    "serverIPAddress",
    "smsParameter",
    "smsTemplateMap",
    "smsTemplatePojo",
    "smsTemplatePojos",
    "srNoQrc",
    "statusCode",
    "statusDesc",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userId"
})
public class AlertsDto {

    protected AlertStatusPojo alertStatusPojo;
    @XmlElement(nillable = true)
    protected List<AlertStatusPojo> alertStatusPojos;
    @XmlElement(nillable = true)
    protected List<String> attachmentPaths;
    protected String clientIPAddress;
    protected String crfNumber;
    protected String customerId;
    protected EmailCcBccPojo emailCcBccPojo;
    @XmlElement(nillable = true)
    protected List<EmailCcBccPojo> emailCcBccPojoSet;
    protected String emailParameter;
    @XmlElement(required = true)
    protected AlertsDto.EmailTemplateMap emailTemplateMap;
    protected EmailTemplatePojo emailTemplatePojo;
    @XmlElement(nillable = true)
    protected List<EmailTemplatePojo> emailTemplatePojos;
    @XmlElement(nillable = true)
    protected List<EventTemplatesPojo> evTempalteList;
    protected EventTemplatesPojo eventTemplatesPojo;
    protected EventsPojo eventsPojo;
    @XmlElement(nillable = true)
    protected List<EventsPojo> eventsPojoList;
    protected String leadId;
    protected String paramName;
    protected String paramValue;
    @XmlElement(required = true)
    protected AlertsDto.ParamValues paramValues;
    protected String partnerId;
    protected String paymentId;
    protected boolean scheduler;
    protected String serverIPAddress;
    protected String smsParameter;
    @XmlElement(required = true)
    protected AlertsDto.SmsTemplateMap smsTemplateMap;
    protected SmsTemplatePojo smsTemplatePojo;
    @XmlElement(nillable = true)
    protected List<SmsTemplatePojo> smsTemplatePojos;
    protected String srNoQrc;
    protected String statusCode;
    protected String statusDesc;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userId;

    /**
     * Gets the value of the alertStatusPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AlertStatusPojo }
     *     
     */
    public AlertStatusPojo getAlertStatusPojo() {
        return alertStatusPojo;
    }

    /**
     * Sets the value of the alertStatusPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertStatusPojo }
     *     
     */
    public void setAlertStatusPojo(AlertStatusPojo value) {
        this.alertStatusPojo = value;
    }

    /**
     * Gets the value of the alertStatusPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the alertStatusPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAlertStatusPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AlertStatusPojo }
     * 
     * 
     */
    public List<AlertStatusPojo> getAlertStatusPojos() {
        if (alertStatusPojos == null) {
            alertStatusPojos = new ArrayList<AlertStatusPojo>();
        }
        return this.alertStatusPojos;
    }

    /**
     * Gets the value of the attachmentPaths property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachmentPaths property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachmentPaths().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAttachmentPaths() {
        if (attachmentPaths == null) {
            attachmentPaths = new ArrayList<String>();
        }
        return this.attachmentPaths;
    }

    /**
     * Gets the value of the clientIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIPAddress() {
        return clientIPAddress;
    }

    /**
     * Sets the value of the clientIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIPAddress(String value) {
        this.clientIPAddress = value;
    }

    /**
     * Gets the value of the crfNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfNumber() {
        return crfNumber;
    }

    /**
     * Sets the value of the crfNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfNumber(String value) {
        this.crfNumber = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the emailCcBccPojo property.
     * 
     * @return
     *     possible object is
     *     {@link EmailCcBccPojo }
     *     
     */
    public EmailCcBccPojo getEmailCcBccPojo() {
        return emailCcBccPojo;
    }

    /**
     * Sets the value of the emailCcBccPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailCcBccPojo }
     *     
     */
    public void setEmailCcBccPojo(EmailCcBccPojo value) {
        this.emailCcBccPojo = value;
    }

    /**
     * Gets the value of the emailCcBccPojoSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailCcBccPojoSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailCcBccPojoSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailCcBccPojo }
     * 
     * 
     */
    public List<EmailCcBccPojo> getEmailCcBccPojoSet() {
        if (emailCcBccPojoSet == null) {
            emailCcBccPojoSet = new ArrayList<EmailCcBccPojo>();
        }
        return this.emailCcBccPojoSet;
    }

    /**
     * Gets the value of the emailParameter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailParameter() {
        return emailParameter;
    }

    /**
     * Sets the value of the emailParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailParameter(String value) {
        this.emailParameter = value;
    }

    /**
     * Gets the value of the emailTemplateMap property.
     * 
     * @return
     *     possible object is
     *     {@link AlertsDto.EmailTemplateMap }
     *     
     */
    public AlertsDto.EmailTemplateMap getEmailTemplateMap() {
        return emailTemplateMap;
    }

    /**
     * Sets the value of the emailTemplateMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertsDto.EmailTemplateMap }
     *     
     */
    public void setEmailTemplateMap(AlertsDto.EmailTemplateMap value) {
        this.emailTemplateMap = value;
    }

    /**
     * Gets the value of the emailTemplatePojo property.
     * 
     * @return
     *     possible object is
     *     {@link EmailTemplatePojo }
     *     
     */
    public EmailTemplatePojo getEmailTemplatePojo() {
        return emailTemplatePojo;
    }

    /**
     * Sets the value of the emailTemplatePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailTemplatePojo }
     *     
     */
    public void setEmailTemplatePojo(EmailTemplatePojo value) {
        this.emailTemplatePojo = value;
    }

    /**
     * Gets the value of the emailTemplatePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailTemplatePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailTemplatePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailTemplatePojo }
     * 
     * 
     */
    public List<EmailTemplatePojo> getEmailTemplatePojos() {
        if (emailTemplatePojos == null) {
            emailTemplatePojos = new ArrayList<EmailTemplatePojo>();
        }
        return this.emailTemplatePojos;
    }

    /**
     * Gets the value of the evTempalteList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the evTempalteList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvTempalteList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventTemplatesPojo }
     * 
     * 
     */
    public List<EventTemplatesPojo> getEvTempalteList() {
        if (evTempalteList == null) {
            evTempalteList = new ArrayList<EventTemplatesPojo>();
        }
        return this.evTempalteList;
    }

    /**
     * Gets the value of the eventTemplatesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link EventTemplatesPojo }
     *     
     */
    public EventTemplatesPojo getEventTemplatesPojo() {
        return eventTemplatesPojo;
    }

    /**
     * Sets the value of the eventTemplatesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventTemplatesPojo }
     *     
     */
    public void setEventTemplatesPojo(EventTemplatesPojo value) {
        this.eventTemplatesPojo = value;
    }

    /**
     * Gets the value of the eventsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link EventsPojo }
     *     
     */
    public EventsPojo getEventsPojo() {
        return eventsPojo;
    }

    /**
     * Sets the value of the eventsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventsPojo }
     *     
     */
    public void setEventsPojo(EventsPojo value) {
        this.eventsPojo = value;
    }

    /**
     * Gets the value of the eventsPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eventsPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEventsPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EventsPojo }
     * 
     * 
     */
    public List<EventsPojo> getEventsPojoList() {
        if (eventsPojoList == null) {
            eventsPojoList = new ArrayList<EventsPojo>();
        }
        return this.eventsPojoList;
    }

    /**
     * Gets the value of the leadId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadId() {
        return leadId;
    }

    /**
     * Sets the value of the leadId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadId(String value) {
        this.leadId = value;
    }

    /**
     * Gets the value of the paramName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamName() {
        return paramName;
    }

    /**
     * Sets the value of the paramName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamName(String value) {
        this.paramName = value;
    }

    /**
     * Gets the value of the paramValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParamValue() {
        return paramValue;
    }

    /**
     * Sets the value of the paramValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParamValue(String value) {
        this.paramValue = value;
    }

    /**
     * Gets the value of the paramValues property.
     * 
     * @return
     *     possible object is
     *     {@link AlertsDto.ParamValues }
     *     
     */
    public AlertsDto.ParamValues getParamValues() {
        return paramValues;
    }

    /**
     * Sets the value of the paramValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertsDto.ParamValues }
     *     
     */
    public void setParamValues(AlertsDto.ParamValues value) {
        this.paramValues = value;
    }

    /**
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the paymentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentId(String value) {
        this.paymentId = value;
    }

    /**
     * Gets the value of the scheduler property.
     * 
     */
    public boolean isScheduler() {
        return scheduler;
    }

    /**
     * Sets the value of the scheduler property.
     * 
     */
    public void setScheduler(boolean value) {
        this.scheduler = value;
    }

    /**
     * Gets the value of the serverIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerIPAddress() {
        return serverIPAddress;
    }

    /**
     * Sets the value of the serverIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerIPAddress(String value) {
        this.serverIPAddress = value;
    }

    /**
     * Gets the value of the smsParameter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmsParameter() {
        return smsParameter;
    }

    /**
     * Sets the value of the smsParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmsParameter(String value) {
        this.smsParameter = value;
    }

    /**
     * Gets the value of the smsTemplateMap property.
     * 
     * @return
     *     possible object is
     *     {@link AlertsDto.SmsTemplateMap }
     *     
     */
    public AlertsDto.SmsTemplateMap getSmsTemplateMap() {
        return smsTemplateMap;
    }

    /**
     * Sets the value of the smsTemplateMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertsDto.SmsTemplateMap }
     *     
     */
    public void setSmsTemplateMap(AlertsDto.SmsTemplateMap value) {
        this.smsTemplateMap = value;
    }

    /**
     * Gets the value of the smsTemplatePojo property.
     * 
     * @return
     *     possible object is
     *     {@link SmsTemplatePojo }
     *     
     */
    public SmsTemplatePojo getSmsTemplatePojo() {
        return smsTemplatePojo;
    }

    /**
     * Sets the value of the smsTemplatePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmsTemplatePojo }
     *     
     */
    public void setSmsTemplatePojo(SmsTemplatePojo value) {
        this.smsTemplatePojo = value;
    }

    /**
     * Gets the value of the smsTemplatePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the smsTemplatePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSmsTemplatePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmsTemplatePojo }
     * 
     * 
     */
    public List<SmsTemplatePojo> getSmsTemplatePojos() {
        if (smsTemplatePojos == null) {
            smsTemplatePojos = new ArrayList<SmsTemplatePojo>();
        }
        return this.smsTemplatePojos;
    }

    /**
     * Gets the value of the srNoQrc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrNoQrc() {
        return srNoQrc;
    }

    /**
     * Sets the value of the srNoQrc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrNoQrc(String value) {
        this.srNoQrc = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Sets the value of the statusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

    /**
     * Gets the value of the userAssociatedPartners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAssociatedPartners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAssociatedPartners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserAssociatedPartners() {
        if (userAssociatedPartners == null) {
            userAssociatedPartners = new ArrayList<String>();
        }
        return this.userAssociatedPartners;
    }

    /**
     * Gets the value of the userAssociatedServices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAssociatedServices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAssociatedServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserAssociatedServices() {
        if (userAssociatedServices == null) {
            userAssociatedServices = new ArrayList<String>();
        }
        return this.userAssociatedServices;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://service.alerts.crm.tele.np.com/}emailTemplatePojo" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class EmailTemplateMap {

        protected List<AlertsDto.EmailTemplateMap.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AlertsDto.EmailTemplateMap.Entry }
         * 
         * 
         */
        public List<AlertsDto.EmailTemplateMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<AlertsDto.EmailTemplateMap.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://service.alerts.crm.tele.np.com/}emailTemplatePojo" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected EmailTemplatePojo value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link EmailTemplatePojo }
             *     
             */
            public EmailTemplatePojo getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link EmailTemplatePojo }
             *     
             */
            public void setValue(EmailTemplatePojo value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class ParamValues {

        protected List<AlertsDto.ParamValues.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AlertsDto.ParamValues.Entry }
         * 
         * 
         */
        public List<AlertsDto.ParamValues.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<AlertsDto.ParamValues.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected String value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://service.alerts.crm.tele.np.com/}smsTemplatePojo" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class SmsTemplateMap {

        protected List<AlertsDto.SmsTemplateMap.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link AlertsDto.SmsTemplateMap.Entry }
         * 
         * 
         */
        public List<AlertsDto.SmsTemplateMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<AlertsDto.SmsTemplateMap.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://service.alerts.crm.tele.np.com/}smsTemplatePojo" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected SmsTemplatePojo value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link SmsTemplatePojo }
             *     
             */
            public SmsTemplatePojo getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link SmsTemplatePojo }
             *     
             */
            public void setValue(SmsTemplatePojo value) {
                this.value = value;
            }

        }

    }

}
