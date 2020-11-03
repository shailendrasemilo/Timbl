
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alertMasterPojo" type="{http://service.config.crm.tele.np.com/}alertMasterPojo" minOccurs="0"/>
 *         &lt;element name="appointmentPojo" type="{http://service.config.crm.tele.np.com/}appointmentPojo" minOccurs="0"/>
 *         &lt;element name="appointmentPojos" type="{http://service.config.crm.tele.np.com/}appointmentPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="atomPgwPojo" type="{http://service.config.crm.tele.np.com/}atomPgwPojo" minOccurs="0"/>
 *         &lt;element name="auditLogPojo" type="{http://service.config.crm.tele.np.com/}crmAuditLogPojo" minOccurs="0"/>
 *         &lt;element name="auditLogPojos" type="{http://service.config.crm.tele.np.com/}crmAuditLogPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="billingErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingTransactionsPojo" type="{http://service.config.crm.tele.np.com/}crmBillingTransactionsPojo" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commonWorkflowPojoGroup" type="{http://service.config.crm.tele.np.com/}commonWorkflowPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="commonWorkflowPojos" type="{http://service.config.crm.tele.np.com/}commonWorkflowPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crfIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCloseReasons" type="{http://service.config.crm.tele.np.com/}crmRcaReasonPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmRcaReasons" type="{http://service.config.crm.tele.np.com/}crmRcaReasonPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerActivityPojo" type="{http://service.config.crm.tele.np.com/}crmCustomerActivityPojo" minOccurs="0"/>
 *         &lt;element name="customerActivityPojos" type="{http://service.config.crm.tele.np.com/}crmCustomerActivityPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerDetail" type="{http://service.config.crm.tele.np.com/}crmCustomerDetailsPojo" minOccurs="0"/>
 *         &lt;element name="customerDetailPojos" type="{http://service.config.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailServers" type="{http://service.config.crm.tele.np.com/}emailServerPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eventsPojoList" type="{http://service.config.crm.tele.np.com/}eventsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fromStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupInboxCRFPojos" type="{http://service.config.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="groupInboxFreezePojos" type="{http://service.config.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="groupInboxLeadPojos" type="{http://service.config.crm.tele.np.com/}lmsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="hdfcPgwPojo" type="{http://service.config.crm.tele.np.com/}hdfcPgwPojo" minOccurs="0"/>
 *         &lt;element name="inboxCRFPojos" type="{http://service.config.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inboxFreezePojos" type="{http://service.config.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inboxId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="inboxLeadPojos" type="{http://service.config.crm.tele.np.com/}lmsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inboxSrPojosGroup" type="{http://service.config.crm.tele.np.com/}crmSrTicketsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inboxSrPojosPersonal" type="{http://service.config.crm.tele.np.com/}crmSrTicketsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inboxStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inboxType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inventType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leadStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macAddressList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="mappingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentCentre" type="{http://service.config.crm.tele.np.com/}crmPaymentCentresPojo" minOccurs="0"/>
 *         &lt;element name="paymentCentres" type="{http://service.config.crm.tele.np.com/}crmPaymentCentresPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="planMigrationPolicyPojo" type="{http://service.config.crm.tele.np.com/}planMigrationPolicyPojo" minOccurs="0"/>
 *         &lt;element name="podUploadedList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarksPojos" type="{http://service.config.crm.tele.np.com/}remarksPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="requestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicePartner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsGateways" type="{http://service.config.crm.tele.np.com/}smsGatewayPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="stages" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="techProcessPgwPojo" type="{http://service.config.crm.tele.np.com/}techProcessPgwPojo" minOccurs="0"/>
 *         &lt;element name="ticketHistoryList" type="{http://service.config.crm.tele.np.com/}crmTicketHistoryPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="toUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tostage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unRead" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userIdChange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userIds" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userMasterPojo" type="{http://service.config.crm.tele.np.com/}userMasterPojo" minOccurs="0"/>
 *         &lt;element name="userType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validationMaster" type="{http://service.config.crm.tele.np.com/}validationMasterPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configDto", propOrder = {
    "alertMasterPojo",
    "appointmentPojo",
    "appointmentPojos",
    "atomPgwPojo",
    "auditLogPojo",
    "auditLogPojos",
    "billingErrorCode",
    "billingTransactionsPojo",
    "clientIPAddress",
    "commonWorkflowPojoGroup",
    "commonWorkflowPojos",
    "crfIds",
    "crmCloseReasons",
    "crmRcaReasons",
    "customerActivityPojo",
    "customerActivityPojos",
    "customerDetail",
    "customerDetailPojos",
    "customerId",
    "emailServers",
    "eventsPojoList",
    "fromStage",
    "fromUserId",
    "groupInboxCRFPojos",
    "groupInboxFreezePojos",
    "groupInboxLeadPojos",
    "hdfcPgwPojo",
    "inboxCRFPojos",
    "inboxFreezePojos",
    "inboxId",
    "inboxLeadPojos",
    "inboxSrPojosGroup",
    "inboxSrPojosPersonal",
    "inboxStatus",
    "inboxType",
    "inventType",
    "leadStatus",
    "macAddressList",
    "mappingId",
    "partnerId",
    "paymentCentre",
    "paymentCentres",
    "planMigrationPolicyPojo",
    "podUploadedList",
    "remarksPojos",
    "requestType",
    "serverIPAddress",
    "servicePartner",
    "smsGateways",
    "stages",
    "statusCode",
    "statusDesc",
    "techProcessPgwPojo",
    "ticketHistoryList",
    "toUserId",
    "tostage",
    "unRead",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userId",
    "userIdChange",
    "userIds",
    "userMasterPojo",
    "userType",
    "validationMaster"
})
public class ConfigDto {

    protected AlertMasterPojo alertMasterPojo;
    protected AppointmentPojo appointmentPojo;
    @XmlElement(nillable = true)
    protected List<AppointmentPojo> appointmentPojos;
    protected AtomPgwPojo atomPgwPojo;
    protected CrmAuditLogPojo auditLogPojo;
    @XmlElement(nillable = true)
    protected List<CrmAuditLogPojo> auditLogPojos;
    protected String billingErrorCode;
    protected CrmBillingTransactionsPojo billingTransactionsPojo;
    protected String clientIPAddress;
    @XmlElement(nillable = true)
    protected List<CommonWorkflowPojo> commonWorkflowPojoGroup;
    @XmlElement(nillable = true)
    protected List<CommonWorkflowPojo> commonWorkflowPojos;
    @XmlElement(nillable = true)
    protected List<String> crfIds;
    @XmlElement(nillable = true)
    protected List<CrmRcaReasonPojo> crmCloseReasons;
    @XmlElement(nillable = true)
    protected List<CrmRcaReasonPojo> crmRcaReasons;
    protected CrmCustomerActivityPojo customerActivityPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustomerActivityPojo> customerActivityPojos;
    protected CrmCustomerDetailsPojo customerDetail;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> customerDetailPojos;
    protected String customerId;
    @XmlElement(nillable = true)
    protected List<EmailServerPojo> emailServers;
    @XmlElement(nillable = true)
    protected List<EventsPojo> eventsPojoList;
    protected String fromStage;
    protected String fromUserId;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> groupInboxCRFPojos;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> groupInboxFreezePojos;
    @XmlElement(nillable = true)
    protected List<LmsPojo> groupInboxLeadPojos;
    protected HdfcPgwPojo hdfcPgwPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> inboxCRFPojos;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> inboxFreezePojos;
    protected long inboxId;
    @XmlElement(nillable = true)
    protected List<LmsPojo> inboxLeadPojos;
    @XmlElement(nillable = true)
    protected List<CrmSrTicketsPojo> inboxSrPojosGroup;
    @XmlElement(nillable = true)
    protected List<CrmSrTicketsPojo> inboxSrPojosPersonal;
    protected String inboxStatus;
    protected String inboxType;
    protected String inventType;
    protected String leadStatus;
    @XmlElement(nillable = true)
    protected List<String> macAddressList;
    protected String mappingId;
    protected long partnerId;
    protected CrmPaymentCentresPojo paymentCentre;
    @XmlElement(nillable = true)
    protected List<CrmPaymentCentresPojo> paymentCentres;
    protected PlanMigrationPolicyPojo planMigrationPolicyPojo;
    @XmlElement(nillable = true)
    protected List<String> podUploadedList;
    @XmlElement(nillable = true)
    protected List<RemarksPojo> remarksPojos;
    protected String requestType;
    protected String serverIPAddress;
    protected String servicePartner;
    @XmlElement(nillable = true)
    protected List<SmsGatewayPojo> smsGateways;
    @XmlElement(nillable = true)
    protected List<String> stages;
    protected String statusCode;
    protected String statusDesc;
    protected TechProcessPgwPojo techProcessPgwPojo;
    @XmlElement(nillable = true)
    protected List<CrmTicketHistoryPojo> ticketHistoryList;
    protected String toUserId;
    protected String tostage;
    protected boolean unRead;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userId;
    protected String userIdChange;
    @XmlElement(nillable = true)
    protected List<String> userIds;
    protected UserMasterPojo userMasterPojo;
    protected String userType;
    protected ValidationMasterPojo validationMaster;

    /**
     * Gets the value of the alertMasterPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AlertMasterPojo }
     *     
     */
    public AlertMasterPojo getAlertMasterPojo() {
        return alertMasterPojo;
    }

    /**
     * Sets the value of the alertMasterPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertMasterPojo }
     *     
     */
    public void setAlertMasterPojo(AlertMasterPojo value) {
        this.alertMasterPojo = value;
    }

    /**
     * Gets the value of the appointmentPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentPojo }
     *     
     */
    public AppointmentPojo getAppointmentPojo() {
        return appointmentPojo;
    }

    /**
     * Sets the value of the appointmentPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentPojo }
     *     
     */
    public void setAppointmentPojo(AppointmentPojo value) {
        this.appointmentPojo = value;
    }

    /**
     * Gets the value of the appointmentPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appointmentPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppointmentPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentPojo }
     * 
     * 
     */
    public List<AppointmentPojo> getAppointmentPojos() {
        if (appointmentPojos == null) {
            appointmentPojos = new ArrayList<AppointmentPojo>();
        }
        return this.appointmentPojos;
    }

    /**
     * Gets the value of the atomPgwPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AtomPgwPojo }
     *     
     */
    public AtomPgwPojo getAtomPgwPojo() {
        return atomPgwPojo;
    }

    /**
     * Sets the value of the atomPgwPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AtomPgwPojo }
     *     
     */
    public void setAtomPgwPojo(AtomPgwPojo value) {
        this.atomPgwPojo = value;
    }

    /**
     * Gets the value of the auditLogPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmAuditLogPojo }
     *     
     */
    public CrmAuditLogPojo getAuditLogPojo() {
        return auditLogPojo;
    }

    /**
     * Sets the value of the auditLogPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmAuditLogPojo }
     *     
     */
    public void setAuditLogPojo(CrmAuditLogPojo value) {
        this.auditLogPojo = value;
    }

    /**
     * Gets the value of the auditLogPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auditLogPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuditLogPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmAuditLogPojo }
     * 
     * 
     */
    public List<CrmAuditLogPojo> getAuditLogPojos() {
        if (auditLogPojos == null) {
            auditLogPojos = new ArrayList<CrmAuditLogPojo>();
        }
        return this.auditLogPojos;
    }

    /**
     * Gets the value of the billingErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingErrorCode() {
        return billingErrorCode;
    }

    /**
     * Sets the value of the billingErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingErrorCode(String value) {
        this.billingErrorCode = value;
    }

    /**
     * Gets the value of the billingTransactionsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmBillingTransactionsPojo }
     *     
     */
    public CrmBillingTransactionsPojo getBillingTransactionsPojo() {
        return billingTransactionsPojo;
    }

    /**
     * Sets the value of the billingTransactionsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmBillingTransactionsPojo }
     *     
     */
    public void setBillingTransactionsPojo(CrmBillingTransactionsPojo value) {
        this.billingTransactionsPojo = value;
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
     * Gets the value of the commonWorkflowPojoGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commonWorkflowPojoGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommonWorkflowPojoGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommonWorkflowPojo }
     * 
     * 
     */
    public List<CommonWorkflowPojo> getCommonWorkflowPojoGroup() {
        if (commonWorkflowPojoGroup == null) {
            commonWorkflowPojoGroup = new ArrayList<CommonWorkflowPojo>();
        }
        return this.commonWorkflowPojoGroup;
    }

    /**
     * Gets the value of the commonWorkflowPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commonWorkflowPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommonWorkflowPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommonWorkflowPojo }
     * 
     * 
     */
    public List<CommonWorkflowPojo> getCommonWorkflowPojos() {
        if (commonWorkflowPojos == null) {
            commonWorkflowPojos = new ArrayList<CommonWorkflowPojo>();
        }
        return this.commonWorkflowPojos;
    }

    /**
     * Gets the value of the crfIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crfIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrfIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCrfIds() {
        if (crfIds == null) {
            crfIds = new ArrayList<String>();
        }
        return this.crfIds;
    }

    /**
     * Gets the value of the crmCloseReasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCloseReasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCloseReasons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmRcaReasonPojo }
     * 
     * 
     */
    public List<CrmRcaReasonPojo> getCrmCloseReasons() {
        if (crmCloseReasons == null) {
            crmCloseReasons = new ArrayList<CrmRcaReasonPojo>();
        }
        return this.crmCloseReasons;
    }

    /**
     * Gets the value of the crmRcaReasons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmRcaReasons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmRcaReasons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmRcaReasonPojo }
     * 
     * 
     */
    public List<CrmRcaReasonPojo> getCrmRcaReasons() {
        if (crmRcaReasons == null) {
            crmRcaReasons = new ArrayList<CrmRcaReasonPojo>();
        }
        return this.crmRcaReasons;
    }

    /**
     * Gets the value of the customerActivityPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomerActivityPojo }
     *     
     */
    public CrmCustomerActivityPojo getCustomerActivityPojo() {
        return customerActivityPojo;
    }

    /**
     * Sets the value of the customerActivityPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomerActivityPojo }
     *     
     */
    public void setCustomerActivityPojo(CrmCustomerActivityPojo value) {
        this.customerActivityPojo = value;
    }

    /**
     * Gets the value of the customerActivityPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerActivityPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerActivityPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerActivityPojo }
     * 
     * 
     */
    public List<CrmCustomerActivityPojo> getCustomerActivityPojos() {
        if (customerActivityPojos == null) {
            customerActivityPojos = new ArrayList<CrmCustomerActivityPojo>();
        }
        return this.customerActivityPojos;
    }

    /**
     * Gets the value of the customerDetail property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public CrmCustomerDetailsPojo getCustomerDetail() {
        return customerDetail;
    }

    /**
     * Sets the value of the customerDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public void setCustomerDetail(CrmCustomerDetailsPojo value) {
        this.customerDetail = value;
    }

    /**
     * Gets the value of the customerDetailPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerDetailPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerDetailPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getCustomerDetailPojos() {
        if (customerDetailPojos == null) {
            customerDetailPojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.customerDetailPojos;
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
     * Gets the value of the emailServers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailServers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailServers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailServerPojo }
     * 
     * 
     */
    public List<EmailServerPojo> getEmailServers() {
        if (emailServers == null) {
            emailServers = new ArrayList<EmailServerPojo>();
        }
        return this.emailServers;
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
     * Gets the value of the fromStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromStage() {
        return fromStage;
    }

    /**
     * Sets the value of the fromStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromStage(String value) {
        this.fromStage = value;
    }

    /**
     * Gets the value of the fromUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromUserId() {
        return fromUserId;
    }

    /**
     * Sets the value of the fromUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromUserId(String value) {
        this.fromUserId = value;
    }

    /**
     * Gets the value of the groupInboxCRFPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupInboxCRFPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupInboxCRFPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getGroupInboxCRFPojos() {
        if (groupInboxCRFPojos == null) {
            groupInboxCRFPojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.groupInboxCRFPojos;
    }

    /**
     * Gets the value of the groupInboxFreezePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupInboxFreezePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupInboxFreezePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getGroupInboxFreezePojos() {
        if (groupInboxFreezePojos == null) {
            groupInboxFreezePojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.groupInboxFreezePojos;
    }

    /**
     * Gets the value of the groupInboxLeadPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupInboxLeadPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupInboxLeadPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsPojo }
     * 
     * 
     */
    public List<LmsPojo> getGroupInboxLeadPojos() {
        if (groupInboxLeadPojos == null) {
            groupInboxLeadPojos = new ArrayList<LmsPojo>();
        }
        return this.groupInboxLeadPojos;
    }

    /**
     * Gets the value of the hdfcPgwPojo property.
     * 
     * @return
     *     possible object is
     *     {@link HdfcPgwPojo }
     *     
     */
    public HdfcPgwPojo getHdfcPgwPojo() {
        return hdfcPgwPojo;
    }

    /**
     * Sets the value of the hdfcPgwPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HdfcPgwPojo }
     *     
     */
    public void setHdfcPgwPojo(HdfcPgwPojo value) {
        this.hdfcPgwPojo = value;
    }

    /**
     * Gets the value of the inboxCRFPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboxCRFPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboxCRFPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getInboxCRFPojos() {
        if (inboxCRFPojos == null) {
            inboxCRFPojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.inboxCRFPojos;
    }

    /**
     * Gets the value of the inboxFreezePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboxFreezePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboxFreezePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getInboxFreezePojos() {
        if (inboxFreezePojos == null) {
            inboxFreezePojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.inboxFreezePojos;
    }

    /**
     * Gets the value of the inboxId property.
     * 
     */
    public long getInboxId() {
        return inboxId;
    }

    /**
     * Sets the value of the inboxId property.
     * 
     */
    public void setInboxId(long value) {
        this.inboxId = value;
    }

    /**
     * Gets the value of the inboxLeadPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboxLeadPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboxLeadPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsPojo }
     * 
     * 
     */
    public List<LmsPojo> getInboxLeadPojos() {
        if (inboxLeadPojos == null) {
            inboxLeadPojos = new ArrayList<LmsPojo>();
        }
        return this.inboxLeadPojos;
    }

    /**
     * Gets the value of the inboxSrPojosGroup property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboxSrPojosGroup property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboxSrPojosGroup().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmSrTicketsPojo }
     * 
     * 
     */
    public List<CrmSrTicketsPojo> getInboxSrPojosGroup() {
        if (inboxSrPojosGroup == null) {
            inboxSrPojosGroup = new ArrayList<CrmSrTicketsPojo>();
        }
        return this.inboxSrPojosGroup;
    }

    /**
     * Gets the value of the inboxSrPojosPersonal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inboxSrPojosPersonal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInboxSrPojosPersonal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmSrTicketsPojo }
     * 
     * 
     */
    public List<CrmSrTicketsPojo> getInboxSrPojosPersonal() {
        if (inboxSrPojosPersonal == null) {
            inboxSrPojosPersonal = new ArrayList<CrmSrTicketsPojo>();
        }
        return this.inboxSrPojosPersonal;
    }

    /**
     * Gets the value of the inboxStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInboxStatus() {
        return inboxStatus;
    }

    /**
     * Sets the value of the inboxStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInboxStatus(String value) {
        this.inboxStatus = value;
    }

    /**
     * Gets the value of the inboxType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInboxType() {
        return inboxType;
    }

    /**
     * Sets the value of the inboxType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInboxType(String value) {
        this.inboxType = value;
    }

    /**
     * Gets the value of the inventType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInventType() {
        return inventType;
    }

    /**
     * Sets the value of the inventType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInventType(String value) {
        this.inventType = value;
    }

    /**
     * Gets the value of the leadStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeadStatus() {
        return leadStatus;
    }

    /**
     * Sets the value of the leadStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeadStatus(String value) {
        this.leadStatus = value;
    }

    /**
     * Gets the value of the macAddressList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the macAddressList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMacAddressList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMacAddressList() {
        if (macAddressList == null) {
            macAddressList = new ArrayList<String>();
        }
        return this.macAddressList;
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
     * Gets the value of the partnerId property.
     * 
     */
    public long getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     */
    public void setPartnerId(long value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the paymentCentre property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPaymentCentresPojo }
     *     
     */
    public CrmPaymentCentresPojo getPaymentCentre() {
        return paymentCentre;
    }

    /**
     * Sets the value of the paymentCentre property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPaymentCentresPojo }
     *     
     */
    public void setPaymentCentre(CrmPaymentCentresPojo value) {
        this.paymentCentre = value;
    }

    /**
     * Gets the value of the paymentCentres property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentCentres property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentCentres().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPaymentCentresPojo }
     * 
     * 
     */
    public List<CrmPaymentCentresPojo> getPaymentCentres() {
        if (paymentCentres == null) {
            paymentCentres = new ArrayList<CrmPaymentCentresPojo>();
        }
        return this.paymentCentres;
    }

    /**
     * Gets the value of the planMigrationPolicyPojo property.
     * 
     * @return
     *     possible object is
     *     {@link PlanMigrationPolicyPojo }
     *     
     */
    public PlanMigrationPolicyPojo getPlanMigrationPolicyPojo() {
        return planMigrationPolicyPojo;
    }

    /**
     * Sets the value of the planMigrationPolicyPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PlanMigrationPolicyPojo }
     *     
     */
    public void setPlanMigrationPolicyPojo(PlanMigrationPolicyPojo value) {
        this.planMigrationPolicyPojo = value;
    }

    /**
     * Gets the value of the podUploadedList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the podUploadedList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPodUploadedList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPodUploadedList() {
        if (podUploadedList == null) {
            podUploadedList = new ArrayList<String>();
        }
        return this.podUploadedList;
    }

    /**
     * Gets the value of the remarksPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remarksPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemarksPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemarksPojo }
     * 
     * 
     */
    public List<RemarksPojo> getRemarksPojos() {
        if (remarksPojos == null) {
            remarksPojos = new ArrayList<RemarksPojo>();
        }
        return this.remarksPojos;
    }

    /**
     * Gets the value of the requestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestType() {
        return requestType;
    }

    /**
     * Sets the value of the requestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestType(String value) {
        this.requestType = value;
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
     * Gets the value of the servicePartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePartner() {
        return servicePartner;
    }

    /**
     * Sets the value of the servicePartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePartner(String value) {
        this.servicePartner = value;
    }

    /**
     * Gets the value of the smsGateways property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the smsGateways property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSmsGateways().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SmsGatewayPojo }
     * 
     * 
     */
    public List<SmsGatewayPojo> getSmsGateways() {
        if (smsGateways == null) {
            smsGateways = new ArrayList<SmsGatewayPojo>();
        }
        return this.smsGateways;
    }

    /**
     * Gets the value of the stages property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stages property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStages().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getStages() {
        if (stages == null) {
            stages = new ArrayList<String>();
        }
        return this.stages;
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
     * Gets the value of the techProcessPgwPojo property.
     * 
     * @return
     *     possible object is
     *     {@link TechProcessPgwPojo }
     *     
     */
    public TechProcessPgwPojo getTechProcessPgwPojo() {
        return techProcessPgwPojo;
    }

    /**
     * Sets the value of the techProcessPgwPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link TechProcessPgwPojo }
     *     
     */
    public void setTechProcessPgwPojo(TechProcessPgwPojo value) {
        this.techProcessPgwPojo = value;
    }

    /**
     * Gets the value of the ticketHistoryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ticketHistoryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTicketHistoryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmTicketHistoryPojo }
     * 
     * 
     */
    public List<CrmTicketHistoryPojo> getTicketHistoryList() {
        if (ticketHistoryList == null) {
            ticketHistoryList = new ArrayList<CrmTicketHistoryPojo>();
        }
        return this.ticketHistoryList;
    }

    /**
     * Gets the value of the toUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * Sets the value of the toUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToUserId(String value) {
        this.toUserId = value;
    }

    /**
     * Gets the value of the tostage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTostage() {
        return tostage;
    }

    /**
     * Sets the value of the tostage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTostage(String value) {
        this.tostage = value;
    }

    /**
     * Gets the value of the unRead property.
     * 
     */
    public boolean isUnRead() {
        return unRead;
    }

    /**
     * Sets the value of the unRead property.
     * 
     */
    public void setUnRead(boolean value) {
        this.unRead = value;
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
     * Gets the value of the userIdChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserIdChange() {
        return userIdChange;
    }

    /**
     * Sets the value of the userIdChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserIdChange(String value) {
        this.userIdChange = value;
    }

    /**
     * Gets the value of the userIds property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userIds property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserIds().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserIds() {
        if (userIds == null) {
            userIds = new ArrayList<String>();
        }
        return this.userIds;
    }

    /**
     * Gets the value of the userMasterPojo property.
     * 
     * @return
     *     possible object is
     *     {@link UserMasterPojo }
     *     
     */
    public UserMasterPojo getUserMasterPojo() {
        return userMasterPojo;
    }

    /**
     * Sets the value of the userMasterPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserMasterPojo }
     *     
     */
    public void setUserMasterPojo(UserMasterPojo value) {
        this.userMasterPojo = value;
    }

    /**
     * Gets the value of the userType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserType() {
        return userType;
    }

    /**
     * Sets the value of the userType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserType(String value) {
        this.userType = value;
    }

    /**
     * Gets the value of the validationMaster property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationMasterPojo }
     *     
     */
    public ValidationMasterPojo getValidationMaster() {
        return validationMaster;
    }

    /**
     * Sets the value of the validationMaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationMasterPojo }
     *     
     */
    public void setValidationMaster(ValidationMasterPojo value) {
        this.validationMaster = value;
    }

}
