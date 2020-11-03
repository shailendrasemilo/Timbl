
package com.np.tele.crm.service.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for qrcTicketReportPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="qrcTicketReportPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="addonPlanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributedTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basePlanName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="binName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callingNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="configuredSLA" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="extendedDays" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fieldVisit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstAssignUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagRemarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inboxCreatedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inboxCreatedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="inboxLastmodifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="inboxUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installationAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LMOName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="latestRemarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="option82" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pendingCurrentBinHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pendingCurrentUserHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="raiseUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="raisedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="rca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reopenCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="repeatTicketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolutionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolutionTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolutionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolvedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rmn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rtn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityDeposit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slab" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slabTime" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="srReopenedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="srResolvedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusOfTicket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subSubCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ticketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ticketStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totDurAssignTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totDurRemarkTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalPendingHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "qrcTicketReportPojo", propOrder = {
    "action",
    "activationDate",
    "addonPlanName",
    "areaName",
    "attributedTo",
    "basePlanName",
    "binName",
    "callingNo",
    "category",
    "configuredSLA",
    "createdTime",
    "crfId",
    "currentBin",
    "custFname",
    "custLname",
    "customerId",
    "extendedDays",
    "fieldVisit",
    "firstAssignUser",
    "flagRemarks",
    "inboxCreatedBy",
    "inboxCreatedTime",
    "inboxLastmodifiedTime",
    "inboxUserId",
    "installationAddress",
    "lmoName",
    "lastModifiedTime",
    "latestRemarks",
    "option82",
    "pendingCurrentBinHours",
    "pendingCurrentUserHours",
    "product",
    "qrcType",
    "raiseUser",
    "raisedDate",
    "rca",
    "reason",
    "remarks",
    "reopenCount",
    "repeatTicketId",
    "resolutionCode",
    "resolutionTime",
    "resolutionType",
    "resolvedBy",
    "rmn",
    "rtn",
    "securityDeposit",
    "serviceType",
    "slaStatus",
    "slab",
    "slabTime",
    "srReopenedOn",
    "srResolvedOn",
    "status",
    "statusOfTicket",
    "subCategory",
    "subSubCategory",
    "ticketId",
    "ticketStatus",
    "totDurAssignTime",
    "totDurRemarkTime",
    "totalPendingHours"
})
public class QrcTicketReportPojo {

    protected String action;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    protected String addonPlanName;
    protected String areaName;
    protected String attributedTo;
    protected String basePlanName;
    protected String binName;
    protected String callingNo;
    protected String category;
    protected String configuredSLA;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String crfId;
    protected String currentBin;
    protected String custFname;
    protected String custLname;
    protected String customerId;
    protected String extendedDays;
    protected String fieldVisit;
    protected String firstAssignUser;
    protected String flagRemarks;
    protected String inboxCreatedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar inboxCreatedTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar inboxLastmodifiedTime;
    protected String inboxUserId;
    protected String installationAddress;
    @XmlElement(name = "LMOName")
    protected String lmoName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String latestRemarks;
    protected String option82;
    protected String pendingCurrentBinHours;
    protected String pendingCurrentUserHours;
    protected String product;
    protected String qrcType;
    protected String raiseUser;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar raisedDate;
    protected String rca;
    protected String reason;
    protected String remarks;
    protected BigInteger reopenCount;
    protected String repeatTicketId;
    protected String resolutionCode;
    protected String resolutionTime;
    protected String resolutionType;
    protected String resolvedBy;
    protected String rmn;
    protected String rtn;
    protected Double securityDeposit;
    protected String serviceType;
    protected String slaStatus;
    protected String slab;
    protected Long slabTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar srReopenedOn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar srResolvedOn;
    protected String status;
    protected String statusOfTicket;
    protected String subCategory;
    protected String subSubCategory;
    protected String ticketId;
    protected String ticketStatus;
    protected String totDurAssignTime;
    protected String totDurRemarkTime;
    protected String totalPendingHours;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
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
     * Gets the value of the attributedTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributedTo() {
        return attributedTo;
    }

    /**
     * Sets the value of the attributedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributedTo(String value) {
        this.attributedTo = value;
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
     * Gets the value of the binName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBinName() {
        return binName;
    }

    /**
     * Sets the value of the binName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBinName(String value) {
        this.binName = value;
    }

    /**
     * Gets the value of the callingNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallingNo() {
        return callingNo;
    }

    /**
     * Sets the value of the callingNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallingNo(String value) {
        this.callingNo = value;
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
     * Gets the value of the configuredSLA property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfiguredSLA() {
        return configuredSLA;
    }

    /**
     * Sets the value of the configuredSLA property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfiguredSLA(String value) {
        this.configuredSLA = value;
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
     * Gets the value of the currentBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentBin() {
        return currentBin;
    }

    /**
     * Sets the value of the currentBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentBin(String value) {
        this.currentBin = value;
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
     * Gets the value of the fieldVisit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldVisit() {
        return fieldVisit;
    }

    /**
     * Sets the value of the fieldVisit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldVisit(String value) {
        this.fieldVisit = value;
    }

    /**
     * Gets the value of the firstAssignUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstAssignUser() {
        return firstAssignUser;
    }

    /**
     * Sets the value of the firstAssignUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstAssignUser(String value) {
        this.firstAssignUser = value;
    }

    /**
     * Gets the value of the flagRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFlagRemarks() {
        return flagRemarks;
    }

    /**
     * Sets the value of the flagRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFlagRemarks(String value) {
        this.flagRemarks = value;
    }

    /**
     * Gets the value of the inboxCreatedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInboxCreatedBy() {
        return inboxCreatedBy;
    }

    /**
     * Sets the value of the inboxCreatedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInboxCreatedBy(String value) {
        this.inboxCreatedBy = value;
    }

    /**
     * Gets the value of the inboxCreatedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInboxCreatedTime() {
        return inboxCreatedTime;
    }

    /**
     * Sets the value of the inboxCreatedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInboxCreatedTime(XMLGregorianCalendar value) {
        this.inboxCreatedTime = value;
    }

    /**
     * Gets the value of the inboxLastmodifiedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInboxLastmodifiedTime() {
        return inboxLastmodifiedTime;
    }

    /**
     * Sets the value of the inboxLastmodifiedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInboxLastmodifiedTime(XMLGregorianCalendar value) {
        this.inboxLastmodifiedTime = value;
    }

    /**
     * Gets the value of the inboxUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInboxUserId() {
        return inboxUserId;
    }

    /**
     * Sets the value of the inboxUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInboxUserId(String value) {
        this.inboxUserId = value;
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
     * Gets the value of the latestRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatestRemarks() {
        return latestRemarks;
    }

    /**
     * Sets the value of the latestRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatestRemarks(String value) {
        this.latestRemarks = value;
    }

    /**
     * Gets the value of the option82 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOption82() {
        return option82;
    }

    /**
     * Sets the value of the option82 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOption82(String value) {
        this.option82 = value;
    }

    /**
     * Gets the value of the pendingCurrentBinHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPendingCurrentBinHours() {
        return pendingCurrentBinHours;
    }

    /**
     * Sets the value of the pendingCurrentBinHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPendingCurrentBinHours(String value) {
        this.pendingCurrentBinHours = value;
    }

    /**
     * Gets the value of the pendingCurrentUserHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPendingCurrentUserHours() {
        return pendingCurrentUserHours;
    }

    /**
     * Sets the value of the pendingCurrentUserHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPendingCurrentUserHours(String value) {
        this.pendingCurrentUserHours = value;
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
     * Gets the value of the qrcType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrcType() {
        return qrcType;
    }

    /**
     * Sets the value of the qrcType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrcType(String value) {
        this.qrcType = value;
    }

    /**
     * Gets the value of the raiseUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRaiseUser() {
        return raiseUser;
    }

    /**
     * Sets the value of the raiseUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRaiseUser(String value) {
        this.raiseUser = value;
    }

    /**
     * Gets the value of the raisedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRaisedDate() {
        return raisedDate;
    }

    /**
     * Sets the value of the raisedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRaisedDate(XMLGregorianCalendar value) {
        this.raisedDate = value;
    }

    /**
     * Gets the value of the rca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRca() {
        return rca;
    }

    /**
     * Sets the value of the rca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRca(String value) {
        this.rca = value;
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
     * Gets the value of the reopenCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getReopenCount() {
        return reopenCount;
    }

    /**
     * Sets the value of the reopenCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setReopenCount(BigInteger value) {
        this.reopenCount = value;
    }

    /**
     * Gets the value of the repeatTicketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRepeatTicketId() {
        return repeatTicketId;
    }

    /**
     * Sets the value of the repeatTicketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRepeatTicketId(String value) {
        this.repeatTicketId = value;
    }

    /**
     * Gets the value of the resolutionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolutionCode() {
        return resolutionCode;
    }

    /**
     * Sets the value of the resolutionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolutionCode(String value) {
        this.resolutionCode = value;
    }

    /**
     * Gets the value of the resolutionTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolutionTime() {
        return resolutionTime;
    }

    /**
     * Sets the value of the resolutionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolutionTime(String value) {
        this.resolutionTime = value;
    }

    /**
     * Gets the value of the resolutionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolutionType() {
        return resolutionType;
    }

    /**
     * Sets the value of the resolutionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolutionType(String value) {
        this.resolutionType = value;
    }

    /**
     * Gets the value of the resolvedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolvedBy() {
        return resolvedBy;
    }

    /**
     * Sets the value of the resolvedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolvedBy(String value) {
        this.resolvedBy = value;
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
     * Gets the value of the securityDeposit property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSecurityDeposit() {
        return securityDeposit;
    }

    /**
     * Sets the value of the securityDeposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSecurityDeposit(Double value) {
        this.securityDeposit = value;
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
     * Gets the value of the slaStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaStatus() {
        return slaStatus;
    }

    /**
     * Sets the value of the slaStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaStatus(String value) {
        this.slaStatus = value;
    }

    /**
     * Gets the value of the slab property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlab() {
        return slab;
    }

    /**
     * Sets the value of the slab property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlab(String value) {
        this.slab = value;
    }

    /**
     * Gets the value of the slabTime property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSlabTime() {
        return slabTime;
    }

    /**
     * Sets the value of the slabTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSlabTime(Long value) {
        this.slabTime = value;
    }

    /**
     * Gets the value of the srReopenedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSrReopenedOn() {
        return srReopenedOn;
    }

    /**
     * Sets the value of the srReopenedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSrReopenedOn(XMLGregorianCalendar value) {
        this.srReopenedOn = value;
    }

    /**
     * Gets the value of the srResolvedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSrResolvedOn() {
        return srResolvedOn;
    }

    /**
     * Sets the value of the srResolvedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSrResolvedOn(XMLGregorianCalendar value) {
        this.srResolvedOn = value;
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
     * Gets the value of the statusOfTicket property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusOfTicket() {
        return statusOfTicket;
    }

    /**
     * Sets the value of the statusOfTicket property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusOfTicket(String value) {
        this.statusOfTicket = value;
    }

    /**
     * Gets the value of the subCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Sets the value of the subCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubCategory(String value) {
        this.subCategory = value;
    }

    /**
     * Gets the value of the subSubCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubSubCategory() {
        return subSubCategory;
    }

    /**
     * Sets the value of the subSubCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubSubCategory(String value) {
        this.subSubCategory = value;
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
     * Gets the value of the ticketStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketStatus() {
        return ticketStatus;
    }

    /**
     * Sets the value of the ticketStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketStatus(String value) {
        this.ticketStatus = value;
    }

    /**
     * Gets the value of the totDurAssignTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotDurAssignTime() {
        return totDurAssignTime;
    }

    /**
     * Sets the value of the totDurAssignTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotDurAssignTime(String value) {
        this.totDurAssignTime = value;
    }

    /**
     * Gets the value of the totDurRemarkTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotDurRemarkTime() {
        return totDurRemarkTime;
    }

    /**
     * Sets the value of the totDurRemarkTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotDurRemarkTime(String value) {
        this.totDurRemarkTime = value;
    }

    /**
     * Gets the value of the totalPendingHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalPendingHours() {
        return totalPendingHours;
    }

    /**
     * Sets the value of the totalPendingHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalPendingHours(String value) {
        this.totalPendingHours = value;
    }

}
