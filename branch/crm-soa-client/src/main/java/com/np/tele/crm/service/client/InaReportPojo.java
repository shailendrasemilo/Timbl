
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for inaReportPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="inaReportPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activated" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="addOnActivationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="addOnPlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addOnQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addOnRent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addonPlanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BPCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BPName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basePlanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseRent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billPref" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cancelled" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="companyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpeMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpeStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfActivatedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfApprovedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfEntrySLA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="crfId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfPreviousStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfReferenceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfToISRSLA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="currentOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custEmailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custMobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extendedDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ftApprovalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ftInOutSLA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ftRejectionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ftSubmittionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="installationAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isrDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="isrEntryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="isrPunchedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LMOName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="macBindDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="month" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nasportId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outStandingBalance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="paymentMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primarySpeed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reacivationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rejectedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rmn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rtn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SRCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SRName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondarySpeed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="societyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tempDisconnectDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ticketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="woToISRSLA" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "inaReportPojo", propOrder = {
    "activated",
    "activationDate",
    "addOnActivationDate",
    "addOnPlanCode",
    "addOnQuota",
    "addOnRent",
    "addonPlanName",
    "areaManager",
    "areaName",
    "bpCode",
    "bpName",
    "basePlanName",
    "baseRent",
    "billCycle",
    "billMode",
    "billPref",
    "billingAddress",
    "cancelled",
    "category",
    "cityName",
    "companyName",
    "connectionType",
    "cpeMacId",
    "cpeStatus",
    "createdTime",
    "crfActivatedBy",
    "crfApprovedBy",
    "crfDate",
    "crfEntrySLA",
    "crfId",
    "crfPreviousStage",
    "crfReferenceNo",
    "crfStage",
    "crfToISRSLA",
    "currentOwner",
    "custEmailId",
    "custFname",
    "custLname",
    "custMobileNo",
    "customerId",
    "extendedDays",
    "ftApprovalDate",
    "ftInOutSLA",
    "ftRejectionDate",
    "ftSubmittionDate",
    "installationAddress",
    "isrDate",
    "isrEntryDate",
    "isrPunchedBy",
    "lmoName",
    "lastModifiedTime",
    "macBindDate",
    "month",
    "nasportId",
    "outStandingBalance",
    "paymentMode",
    "planCode",
    "previousOwner",
    "primaryQuota",
    "primarySpeed",
    "product",
    "reacivationDate",
    "reason",
    "rejectedBy",
    "remarks",
    "rmn",
    "rtn",
    "srCode",
    "srName",
    "secondarySpeed",
    "serviceType",
    "societyName",
    "status",
    "statusDate",
    "tempDisconnectDate",
    "ticketId",
    "woToISRSLA"
})
public class InaReportPojo {

    protected String activated;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar addOnActivationDate;
    protected String addOnPlanCode;
    protected String addOnQuota;
    protected String addOnRent;
    protected String addonPlanName;
    protected String areaManager;
    protected String areaName;
    @XmlElement(name = "BPCode")
    protected String bpCode;
    @XmlElement(name = "BPName")
    protected String bpName;
    protected String basePlanName;
    protected String baseRent;
    protected String billCycle;
    protected String billMode;
    protected String billPref;
    protected String billingAddress;
    protected String cancelled;
    protected String category;
    protected String cityName;
    protected String companyName;
    protected String connectionType;
    protected String cpeMacId;
    protected String cpeStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String crfActivatedBy;
    protected String crfApprovedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crfDate;
    protected int crfEntrySLA;
    protected String crfId;
    protected String crfPreviousStage;
    protected String crfReferenceNo;
    protected String crfStage;
    protected int crfToISRSLA;
    protected String currentOwner;
    protected String custEmailId;
    protected String custFname;
    protected String custLname;
    protected String custMobileNo;
    protected String customerId;
    protected String extendedDays;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftApprovalDate;
    protected int ftInOutSLA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftRejectionDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftSubmittionDate;
    protected String installationAddress;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar isrDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar isrEntryDate;
    protected String isrPunchedBy;
    @XmlElement(name = "LMOName")
    protected String lmoName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar macBindDate;
    protected String month;
    protected String nasportId;
    protected Double outStandingBalance;
    protected String paymentMode;
    protected String planCode;
    protected String previousOwner;
    protected String primaryQuota;
    protected String primarySpeed;
    protected String product;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reacivationDate;
    protected String reason;
    protected String rejectedBy;
    protected String remarks;
    protected String rmn;
    protected String rtn;
    @XmlElement(name = "SRCode")
    protected String srCode;
    @XmlElement(name = "SRName")
    protected String srName;
    protected String secondarySpeed;
    protected String serviceType;
    protected String societyName;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar statusDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tempDisconnectDate;
    protected String ticketId;
    protected int woToISRSLA;

    /**
     * Gets the value of the activated property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivated() {
        return activated;
    }

    /**
     * Sets the value of the activated property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivated(String value) {
        this.activated = value;
    }

    /**
     * Gets the value of the activationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivationDate() {
        return activationDate;
    }

    /**
     * Sets the value of the activationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivationDate(XMLGregorianCalendar value) {
        this.activationDate = value;
    }

    /**
     * Gets the value of the addOnActivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAddOnActivationDate() {
        return addOnActivationDate;
    }

    /**
     * Sets the value of the addOnActivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAddOnActivationDate(XMLGregorianCalendar value) {
        this.addOnActivationDate = value;
    }

    /**
     * Gets the value of the addOnPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddOnPlanCode() {
        return addOnPlanCode;
    }

    /**
     * Sets the value of the addOnPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddOnPlanCode(String value) {
        this.addOnPlanCode = value;
    }

    /**
     * Gets the value of the addOnQuota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddOnQuota() {
        return addOnQuota;
    }

    /**
     * Sets the value of the addOnQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddOnQuota(String value) {
        this.addOnQuota = value;
    }

    /**
     * Gets the value of the addOnRent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddOnRent() {
        return addOnRent;
    }

    /**
     * Sets the value of the addOnRent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddOnRent(String value) {
        this.addOnRent = value;
    }

    /**
     * Gets the value of the addonPlanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddonPlanName() {
        return addonPlanName;
    }

    /**
     * Sets the value of the addonPlanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddonPlanName(String value) {
        this.addonPlanName = value;
    }

    /**
     * Gets the value of the areaManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaManager() {
        return areaManager;
    }

    /**
     * Sets the value of the areaManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaManager(String value) {
        this.areaManager = value;
    }

    /**
     * Gets the value of the areaName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * Sets the value of the areaName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAreaName(String value) {
        this.areaName = value;
    }

    /**
     * Gets the value of the bpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBPCode() {
        return bpCode;
    }

    /**
     * Sets the value of the bpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBPCode(String value) {
        this.bpCode = value;
    }

    /**
     * Gets the value of the bpName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBPName() {
        return bpName;
    }

    /**
     * Sets the value of the bpName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBPName(String value) {
        this.bpName = value;
    }

    /**
     * Gets the value of the basePlanName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasePlanName() {
        return basePlanName;
    }

    /**
     * Sets the value of the basePlanName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasePlanName(String value) {
        this.basePlanName = value;
    }

    /**
     * Gets the value of the baseRent property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseRent() {
        return baseRent;
    }

    /**
     * Sets the value of the baseRent property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseRent(String value) {
        this.baseRent = value;
    }

    /**
     * Gets the value of the billCycle property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCycle() {
        return billCycle;
    }

    /**
     * Sets the value of the billCycle property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCycle(String value) {
        this.billCycle = value;
    }

    /**
     * Gets the value of the billMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillMode() {
        return billMode;
    }

    /**
     * Sets the value of the billMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillMode(String value) {
        this.billMode = value;
    }

    /**
     * Gets the value of the billPref property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillPref() {
        return billPref;
    }

    /**
     * Sets the value of the billPref property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillPref(String value) {
        this.billPref = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAddress(String value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the cancelled property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCancelled() {
        return cancelled;
    }

    /**
     * Sets the value of the cancelled property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCancelled(String value) {
        this.cancelled = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the cityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * Sets the value of the cityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityName(String value) {
        this.cityName = value;
    }

    /**
     * Gets the value of the companyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the value of the companyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanyName(String value) {
        this.companyName = value;
    }

    /**
     * Gets the value of the connectionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * Sets the value of the connectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionType(String value) {
        this.connectionType = value;
    }

    /**
     * Gets the value of the cpeMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpeMacId() {
        return cpeMacId;
    }

    /**
     * Sets the value of the cpeMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpeMacId(String value) {
        this.cpeMacId = value;
    }

    /**
     * Gets the value of the cpeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpeStatus() {
        return cpeStatus;
    }

    /**
     * Sets the value of the cpeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpeStatus(String value) {
        this.cpeStatus = value;
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
     * Gets the value of the crfActivatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfActivatedBy() {
        return crfActivatedBy;
    }

    /**
     * Sets the value of the crfActivatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfActivatedBy(String value) {
        this.crfActivatedBy = value;
    }

    /**
     * Gets the value of the crfApprovedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfApprovedBy() {
        return crfApprovedBy;
    }

    /**
     * Sets the value of the crfApprovedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfApprovedBy(String value) {
        this.crfApprovedBy = value;
    }

    /**
     * Gets the value of the crfDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrfDate() {
        return crfDate;
    }

    /**
     * Sets the value of the crfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrfDate(XMLGregorianCalendar value) {
        this.crfDate = value;
    }

    /**
     * Gets the value of the crfEntrySLA property.
     * 
     */
    public int getCrfEntrySLA() {
        return crfEntrySLA;
    }

    /**
     * Sets the value of the crfEntrySLA property.
     * 
     */
    public void setCrfEntrySLA(int value) {
        this.crfEntrySLA = value;
    }

    /**
     * Gets the value of the crfId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfId() {
        return crfId;
    }

    /**
     * Sets the value of the crfId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfId(String value) {
        this.crfId = value;
    }

    /**
     * Gets the value of the crfPreviousStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfPreviousStage() {
        return crfPreviousStage;
    }

    /**
     * Sets the value of the crfPreviousStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfPreviousStage(String value) {
        this.crfPreviousStage = value;
    }

    /**
     * Gets the value of the crfReferenceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfReferenceNo() {
        return crfReferenceNo;
    }

    /**
     * Sets the value of the crfReferenceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfReferenceNo(String value) {
        this.crfReferenceNo = value;
    }

    /**
     * Gets the value of the crfStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfStage() {
        return crfStage;
    }

    /**
     * Sets the value of the crfStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfStage(String value) {
        this.crfStage = value;
    }

    /**
     * Gets the value of the crfToISRSLA property.
     * 
     */
    public int getCrfToISRSLA() {
        return crfToISRSLA;
    }

    /**
     * Sets the value of the crfToISRSLA property.
     * 
     */
    public void setCrfToISRSLA(int value) {
        this.crfToISRSLA = value;
    }

    /**
     * Gets the value of the currentOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentOwner() {
        return currentOwner;
    }

    /**
     * Sets the value of the currentOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentOwner(String value) {
        this.currentOwner = value;
    }

    /**
     * Gets the value of the custEmailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustEmailId() {
        return custEmailId;
    }

    /**
     * Sets the value of the custEmailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustEmailId(String value) {
        this.custEmailId = value;
    }

    /**
     * Gets the value of the custFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustFname() {
        return custFname;
    }

    /**
     * Sets the value of the custFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustFname(String value) {
        this.custFname = value;
    }

    /**
     * Gets the value of the custLname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLname() {
        return custLname;
    }

    /**
     * Sets the value of the custLname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLname(String value) {
        this.custLname = value;
    }

    /**
     * Gets the value of the custMobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustMobileNo() {
        return custMobileNo;
    }

    /**
     * Sets the value of the custMobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustMobileNo(String value) {
        this.custMobileNo = value;
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
     * Gets the value of the extendedDays property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendedDays() {
        return extendedDays;
    }

    /**
     * Sets the value of the extendedDays property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendedDays(String value) {
        this.extendedDays = value;
    }

    /**
     * Gets the value of the ftApprovalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtApprovalDate() {
        return ftApprovalDate;
    }

    /**
     * Sets the value of the ftApprovalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtApprovalDate(XMLGregorianCalendar value) {
        this.ftApprovalDate = value;
    }

    /**
     * Gets the value of the ftInOutSLA property.
     * 
     */
    public int getFtInOutSLA() {
        return ftInOutSLA;
    }

    /**
     * Sets the value of the ftInOutSLA property.
     * 
     */
    public void setFtInOutSLA(int value) {
        this.ftInOutSLA = value;
    }

    /**
     * Gets the value of the ftRejectionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtRejectionDate() {
        return ftRejectionDate;
    }

    /**
     * Sets the value of the ftRejectionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtRejectionDate(XMLGregorianCalendar value) {
        this.ftRejectionDate = value;
    }

    /**
     * Gets the value of the ftSubmittionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtSubmittionDate() {
        return ftSubmittionDate;
    }

    /**
     * Sets the value of the ftSubmittionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtSubmittionDate(XMLGregorianCalendar value) {
        this.ftSubmittionDate = value;
    }

    /**
     * Gets the value of the installationAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallationAddress() {
        return installationAddress;
    }

    /**
     * Sets the value of the installationAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallationAddress(String value) {
        this.installationAddress = value;
    }

    /**
     * Gets the value of the isrDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIsrDate() {
        return isrDate;
    }

    /**
     * Sets the value of the isrDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIsrDate(XMLGregorianCalendar value) {
        this.isrDate = value;
    }

    /**
     * Gets the value of the isrEntryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getIsrEntryDate() {
        return isrEntryDate;
    }

    /**
     * Sets the value of the isrEntryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setIsrEntryDate(XMLGregorianCalendar value) {
        this.isrEntryDate = value;
    }

    /**
     * Gets the value of the isrPunchedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsrPunchedBy() {
        return isrPunchedBy;
    }

    /**
     * Sets the value of the isrPunchedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsrPunchedBy(String value) {
        this.isrPunchedBy = value;
    }

    /**
     * Gets the value of the lmoName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLMOName() {
        return lmoName;
    }

    /**
     * Sets the value of the lmoName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLMOName(String value) {
        this.lmoName = value;
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
     * Gets the value of the macBindDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getMacBindDate() {
        return macBindDate;
    }

    /**
     * Sets the value of the macBindDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setMacBindDate(XMLGregorianCalendar value) {
        this.macBindDate = value;
    }

    /**
     * Gets the value of the month property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonth() {
        return month;
    }

    /**
     * Sets the value of the month property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonth(String value) {
        this.month = value;
    }

    /**
     * Gets the value of the nasportId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNasportId() {
        return nasportId;
    }

    /**
     * Sets the value of the nasportId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNasportId(String value) {
        this.nasportId = value;
    }

    /**
     * Gets the value of the outStandingBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOutStandingBalance() {
        return outStandingBalance;
    }

    /**
     * Sets the value of the outStandingBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOutStandingBalance(Double value) {
        this.outStandingBalance = value;
    }

    /**
     * Gets the value of the paymentMode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMode() {
        return paymentMode;
    }

    /**
     * Sets the value of the paymentMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMode(String value) {
        this.paymentMode = value;
    }

    /**
     * Gets the value of the planCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanCode() {
        return planCode;
    }

    /**
     * Sets the value of the planCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanCode(String value) {
        this.planCode = value;
    }

    /**
     * Gets the value of the previousOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousOwner() {
        return previousOwner;
    }

    /**
     * Sets the value of the previousOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousOwner(String value) {
        this.previousOwner = value;
    }

    /**
     * Gets the value of the primaryQuota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryQuota() {
        return primaryQuota;
    }

    /**
     * Sets the value of the primaryQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryQuota(String value) {
        this.primaryQuota = value;
    }

    /**
     * Gets the value of the primarySpeed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimarySpeed() {
        return primarySpeed;
    }

    /**
     * Sets the value of the primarySpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimarySpeed(String value) {
        this.primarySpeed = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the reacivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReacivationDate() {
        return reacivationDate;
    }

    /**
     * Sets the value of the reacivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReacivationDate(XMLGregorianCalendar value) {
        this.reacivationDate = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
    }

    /**
     * Gets the value of the rejectedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRejectedBy() {
        return rejectedBy;
    }

    /**
     * Sets the value of the rejectedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRejectedBy(String value) {
        this.rejectedBy = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the rmn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRmn() {
        return rmn;
    }

    /**
     * Sets the value of the rmn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRmn(String value) {
        this.rmn = value;
    }

    /**
     * Gets the value of the rtn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtn() {
        return rtn;
    }

    /**
     * Sets the value of the rtn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtn(String value) {
        this.rtn = value;
    }

    /**
     * Gets the value of the srCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRCode() {
        return srCode;
    }

    /**
     * Sets the value of the srCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRCode(String value) {
        this.srCode = value;
    }

    /**
     * Gets the value of the srName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSRName() {
        return srName;
    }

    /**
     * Sets the value of the srName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSRName(String value) {
        this.srName = value;
    }

    /**
     * Gets the value of the secondarySpeed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondarySpeed() {
        return secondarySpeed;
    }

    /**
     * Sets the value of the secondarySpeed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondarySpeed(String value) {
        this.secondarySpeed = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the societyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocietyName() {
        return societyName;
    }

    /**
     * Sets the value of the societyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocietyName(String value) {
        this.societyName = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the statusDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStatusDate() {
        return statusDate;
    }

    /**
     * Sets the value of the statusDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStatusDate(XMLGregorianCalendar value) {
        this.statusDate = value;
    }

    /**
     * Gets the value of the tempDisconnectDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTempDisconnectDate() {
        return tempDisconnectDate;
    }

    /**
     * Sets the value of the tempDisconnectDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTempDisconnectDate(XMLGregorianCalendar value) {
        this.tempDisconnectDate = value;
    }

    /**
     * Gets the value of the ticketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Sets the value of the ticketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the value of the woToISRSLA property.
     * 
     */
    public int getWoToISRSLA() {
        return woToISRSLA;
    }

    /**
     * Sets the value of the woToISRSLA property.
     * 
     */
    public void setWoToISRSLA(int value) {
        this.woToISRSLA = value;
    }

}
