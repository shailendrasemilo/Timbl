
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmSrTicketsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmSrTicketsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="APITransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionTaken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionTakenId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="attributedTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callingNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmCustomerDetailsPojo" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayFollowupOn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displaySrClosedOn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displaySrReopenedOn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displaySrResolvedOn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="expectedSLATime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="flagRemarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flagSelected" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="followupOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="functionalbinId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="inboxId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="lmsPojo" type="{http://service.qrc.crm.tele.np.com/}lmsPojo" minOccurs="0"/>
 *         &lt;element name="mappingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moduleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="processingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="qrcCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcCategoryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qrcRcaPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcActionTakenPojo" minOccurs="0"/>
 *         &lt;element name="qrcSubCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcSubCategoryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qrcSubSubCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcSubSubCategoryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qrcType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarksPojos" type="{http://service.qrc.crm.tele.np.com/}remarksPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="resolutionCodePojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcRootCausePojo" minOccurs="0"/>
 *         &lt;element name="resolutionType" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="resolvedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootCauseId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="slaCalculatedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="slaTime" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="srClosedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="srId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srReopenedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="srResolvedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ticketId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="unRead" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="visitRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wrongTagging" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmSrTicketsPojo", propOrder = {
    "apiTransactionId",
    "actionTaken",
    "actionTakenId",
    "attributedTo",
    "callingNo",
    "createdBy",
    "createdTime",
    "currentUser",
    "customerDetailsPojo",
    "displayCreatedTime",
    "displayFollowupOn",
    "displayLastModifiedTime",
    "displaySrClosedOn",
    "displaySrReopenedOn",
    "displaySrResolvedOn",
    "editable",
    "expectedSLATime",
    "flagRemarks",
    "flagSelected",
    "followupOn",
    "functionalbinId",
    "inboxId",
    "lastModifiedBy",
    "lastModifiedTime",
    "lmsPojo",
    "mappingId",
    "moduleType",
    "previousStage",
    "processingDate",
    "qrcCategory",
    "qrcCategoryId",
    "qrcRcaPojo",
    "qrcSubCategory",
    "qrcSubCategoryId",
    "qrcSubSubCategory",
    "qrcSubSubCategoryId",
    "qrcType",
    "remarksPojos",
    "resolutionCodePojo",
    "resolutionType",
    "resolvedBy",
    "rootCause",
    "rootCauseId",
    "slaCalculatedOn",
    "slaTime",
    "srClosedOn",
    "srId",
    "srReopenedOn",
    "srResolvedOn",
    "status",
    "ticketId",
    "unRead",
    "visitRequired",
    "wrongTagging"
})
public class CrmSrTicketsPojo {

    @XmlElement(name = "APITransactionId")
    protected String apiTransactionId;
    protected String actionTaken;
    protected long actionTakenId;
    protected String attributedTo;
    protected long callingNo;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String currentUser;
    protected CrmCustomerDetailsPojo customerDetailsPojo;
    protected String displayCreatedTime;
    protected String displayFollowupOn;
    protected String displayLastModifiedTime;
    protected String displaySrClosedOn;
    protected String displaySrReopenedOn;
    protected String displaySrResolvedOn;
    protected boolean editable;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expectedSLATime;
    protected String flagRemarks;
    protected boolean flagSelected;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar followupOn;
    protected long functionalbinId;
    protected long inboxId;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected LmsPojo lmsPojo;
    protected String mappingId;
    protected String moduleType;
    protected String previousStage;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar processingDate;
    protected String qrcCategory;
    protected long qrcCategoryId;
    protected CrmQrcActionTakenPojo qrcRcaPojo;
    protected String qrcSubCategory;
    protected long qrcSubCategoryId;
    protected String qrcSubSubCategory;
    protected long qrcSubSubCategoryId;
    protected String qrcType;
    @XmlElement(nillable = true)
    protected List<RemarksPojo> remarksPojos;
    protected CrmQrcRootCausePojo resolutionCodePojo;
    protected byte resolutionType;
    protected String resolvedBy;
    protected String rootCause;
    protected long rootCauseId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar slaCalculatedOn;
    protected long slaTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar srClosedOn;
    protected String srId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar srReopenedOn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar srResolvedOn;
    protected String status;
    protected long ticketId;
    protected boolean unRead;
    protected String visitRequired;
    protected String wrongTagging;

    /**
     * Gets the value of the apiTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAPITransactionId() {
        return apiTransactionId;
    }

    /**
     * Sets the value of the apiTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAPITransactionId(String value) {
        this.apiTransactionId = value;
    }

    /**
     * Gets the value of the actionTaken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionTaken() {
        return actionTaken;
    }

    /**
     * Sets the value of the actionTaken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionTaken(String value) {
        this.actionTaken = value;
    }

    /**
     * Gets the value of the actionTakenId property.
     * 
     */
    public long getActionTakenId() {
        return actionTakenId;
    }

    /**
     * Sets the value of the actionTakenId property.
     * 
     */
    public void setActionTakenId(long value) {
        this.actionTakenId = value;
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
     * Gets the value of the callingNo property.
     * 
     */
    public long getCallingNo() {
        return callingNo;
    }

    /**
     * Sets the value of the callingNo property.
     * 
     */
    public void setCallingNo(long value) {
        this.callingNo = value;
    }

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
     * Gets the value of the currentUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the value of the currentUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentUser(String value) {
        this.currentUser = value;
    }

    /**
     * Gets the value of the customerDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public CrmCustomerDetailsPojo getCustomerDetailsPojo() {
        return customerDetailsPojo;
    }

    /**
     * Sets the value of the customerDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public void setCustomerDetailsPojo(CrmCustomerDetailsPojo value) {
        this.customerDetailsPojo = value;
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
     * Gets the value of the displayFollowupOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayFollowupOn() {
        return displayFollowupOn;
    }

    /**
     * Sets the value of the displayFollowupOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayFollowupOn(String value) {
        this.displayFollowupOn = value;
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
     * Gets the value of the displaySrClosedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplaySrClosedOn() {
        return displaySrClosedOn;
    }

    /**
     * Sets the value of the displaySrClosedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplaySrClosedOn(String value) {
        this.displaySrClosedOn = value;
    }

    /**
     * Gets the value of the displaySrReopenedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplaySrReopenedOn() {
        return displaySrReopenedOn;
    }

    /**
     * Sets the value of the displaySrReopenedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplaySrReopenedOn(String value) {
        this.displaySrReopenedOn = value;
    }

    /**
     * Gets the value of the displaySrResolvedOn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplaySrResolvedOn() {
        return displaySrResolvedOn;
    }

    /**
     * Sets the value of the displaySrResolvedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplaySrResolvedOn(String value) {
        this.displaySrResolvedOn = value;
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
     * Gets the value of the expectedSLATime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpectedSLATime() {
        return expectedSLATime;
    }

    /**
     * Sets the value of the expectedSLATime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpectedSLATime(XMLGregorianCalendar value) {
        this.expectedSLATime = value;
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
     * Gets the value of the flagSelected property.
     * 
     */
    public boolean isFlagSelected() {
        return flagSelected;
    }

    /**
     * Sets the value of the flagSelected property.
     * 
     */
    public void setFlagSelected(boolean value) {
        this.flagSelected = value;
    }

    /**
     * Gets the value of the followupOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFollowupOn() {
        return followupOn;
    }

    /**
     * Sets the value of the followupOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFollowupOn(XMLGregorianCalendar value) {
        this.followupOn = value;
    }

    /**
     * Gets the value of the functionalbinId property.
     * 
     */
    public long getFunctionalbinId() {
        return functionalbinId;
    }

    /**
     * Sets the value of the functionalbinId property.
     * 
     */
    public void setFunctionalbinId(long value) {
        this.functionalbinId = value;
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
     * Gets the value of the lmsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link LmsPojo }
     *     
     */
    public LmsPojo getLmsPojo() {
        return lmsPojo;
    }

    /**
     * Sets the value of the lmsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LmsPojo }
     *     
     */
    public void setLmsPojo(LmsPojo value) {
        this.lmsPojo = value;
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
     * Gets the value of the moduleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModuleType() {
        return moduleType;
    }

    /**
     * Sets the value of the moduleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModuleType(String value) {
        this.moduleType = value;
    }

    /**
     * Gets the value of the previousStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousStage() {
        return previousStage;
    }

    /**
     * Sets the value of the previousStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousStage(String value) {
        this.previousStage = value;
    }

    /**
     * Gets the value of the processingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getProcessingDate() {
        return processingDate;
    }

    /**
     * Sets the value of the processingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setProcessingDate(XMLGregorianCalendar value) {
        this.processingDate = value;
    }

    /**
     * Gets the value of the qrcCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrcCategory() {
        return qrcCategory;
    }

    /**
     * Sets the value of the qrcCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrcCategory(String value) {
        this.qrcCategory = value;
    }

    /**
     * Gets the value of the qrcCategoryId property.
     * 
     */
    public long getQrcCategoryId() {
        return qrcCategoryId;
    }

    /**
     * Sets the value of the qrcCategoryId property.
     * 
     */
    public void setQrcCategoryId(long value) {
        this.qrcCategoryId = value;
    }

    /**
     * Gets the value of the qrcRcaPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcActionTakenPojo }
     *     
     */
    public CrmQrcActionTakenPojo getQrcRcaPojo() {
        return qrcRcaPojo;
    }

    /**
     * Sets the value of the qrcRcaPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcActionTakenPojo }
     *     
     */
    public void setQrcRcaPojo(CrmQrcActionTakenPojo value) {
        this.qrcRcaPojo = value;
    }

    /**
     * Gets the value of the qrcSubCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrcSubCategory() {
        return qrcSubCategory;
    }

    /**
     * Sets the value of the qrcSubCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrcSubCategory(String value) {
        this.qrcSubCategory = value;
    }

    /**
     * Gets the value of the qrcSubCategoryId property.
     * 
     */
    public long getQrcSubCategoryId() {
        return qrcSubCategoryId;
    }

    /**
     * Sets the value of the qrcSubCategoryId property.
     * 
     */
    public void setQrcSubCategoryId(long value) {
        this.qrcSubCategoryId = value;
    }

    /**
     * Gets the value of the qrcSubSubCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrcSubSubCategory() {
        return qrcSubSubCategory;
    }

    /**
     * Sets the value of the qrcSubSubCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrcSubSubCategory(String value) {
        this.qrcSubSubCategory = value;
    }

    /**
     * Gets the value of the qrcSubSubCategoryId property.
     * 
     */
    public long getQrcSubSubCategoryId() {
        return qrcSubSubCategoryId;
    }

    /**
     * Sets the value of the qrcSubSubCategoryId property.
     * 
     */
    public void setQrcSubSubCategoryId(long value) {
        this.qrcSubSubCategoryId = value;
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
     * Gets the value of the resolutionCodePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcRootCausePojo }
     *     
     */
    public CrmQrcRootCausePojo getResolutionCodePojo() {
        return resolutionCodePojo;
    }

    /**
     * Sets the value of the resolutionCodePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcRootCausePojo }
     *     
     */
    public void setResolutionCodePojo(CrmQrcRootCausePojo value) {
        this.resolutionCodePojo = value;
    }

    /**
     * Gets the value of the resolutionType property.
     * 
     */
    public byte getResolutionType() {
        return resolutionType;
    }

    /**
     * Sets the value of the resolutionType property.
     * 
     */
    public void setResolutionType(byte value) {
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
     * Gets the value of the rootCause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootCause() {
        return rootCause;
    }

    /**
     * Sets the value of the rootCause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootCause(String value) {
        this.rootCause = value;
    }

    /**
     * Gets the value of the rootCauseId property.
     * 
     */
    public long getRootCauseId() {
        return rootCauseId;
    }

    /**
     * Sets the value of the rootCauseId property.
     * 
     */
    public void setRootCauseId(long value) {
        this.rootCauseId = value;
    }

    /**
     * Gets the value of the slaCalculatedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSlaCalculatedOn() {
        return slaCalculatedOn;
    }

    /**
     * Sets the value of the slaCalculatedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSlaCalculatedOn(XMLGregorianCalendar value) {
        this.slaCalculatedOn = value;
    }

    /**
     * Gets the value of the slaTime property.
     * 
     */
    public long getSlaTime() {
        return slaTime;
    }

    /**
     * Sets the value of the slaTime property.
     * 
     */
    public void setSlaTime(long value) {
        this.slaTime = value;
    }

    /**
     * Gets the value of the srClosedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSrClosedOn() {
        return srClosedOn;
    }

    /**
     * Sets the value of the srClosedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSrClosedOn(XMLGregorianCalendar value) {
        this.srClosedOn = value;
    }

    /**
     * Gets the value of the srId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrId() {
        return srId;
    }

    /**
     * Sets the value of the srId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrId(String value) {
        this.srId = value;
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
     * Gets the value of the ticketId property.
     * 
     */
    public long getTicketId() {
        return ticketId;
    }

    /**
     * Sets the value of the ticketId property.
     * 
     */
    public void setTicketId(long value) {
        this.ticketId = value;
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
     * Gets the value of the visitRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitRequired() {
        return visitRequired;
    }

    /**
     * Sets the value of the visitRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitRequired(String value) {
        this.visitRequired = value;
    }

    /**
     * Gets the value of the wrongTagging property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrongTagging() {
        return wrongTagging;
    }

    /**
     * Sets the value of the wrongTagging property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrongTagging(String value) {
        this.wrongTagging = value;
    }

}
