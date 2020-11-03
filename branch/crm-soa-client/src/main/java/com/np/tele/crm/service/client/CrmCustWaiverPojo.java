
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmCustWaiverPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCustWaiverPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="approvedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="finalApprovedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="previousStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rejectedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rejectionReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srTicketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waiverAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="waiverHead" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waiverId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="waiverPostedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="waiverType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCustWaiverPojo", propOrder = {
    "approvedBy",
    "billCycle",
    "billNo",
    "createdBy",
    "createdTime",
    "crfNo",
    "customerId",
    "finalApprovedBy",
    "lastModifiedBy",
    "lastModifiedTime",
    "previousStage",
    "qrcCategory",
    "rejectedBy",
    "rejectionReason",
    "remarks",
    "srTicketId",
    "status",
    "waiverAmount",
    "waiverHead",
    "waiverId",
    "waiverPostedDate",
    "waiverType",
    "workflowId",
    "workflowStage"
})
public class CrmCustWaiverPojo {

    protected String approvedBy;
    protected String billCycle;
    protected String billNo;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String crfNo;
    protected String customerId;
    protected String finalApprovedBy;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String previousStage;
    protected String qrcCategory;
    protected String rejectedBy;
    protected String rejectionReason;
    protected String remarks;
    protected String srTicketId;
    protected String status;
    protected double waiverAmount;
    protected String waiverHead;
    protected long waiverId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar waiverPostedDate;
    protected String waiverType;
    protected String workflowId;
    protected String workflowStage;

    /**
     * Gets the value of the approvedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApprovedBy() {
        return approvedBy;
    }

    /**
     * Sets the value of the approvedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApprovedBy(String value) {
        this.approvedBy = value;
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
     * Gets the value of the billNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillNo() {
        return billNo;
    }

    /**
     * Sets the value of the billNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillNo(String value) {
        this.billNo = value;
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
     * Gets the value of the crfNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfNo() {
        return crfNo;
    }

    /**
     * Sets the value of the crfNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfNo(String value) {
        this.crfNo = value;
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
     * Gets the value of the finalApprovedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinalApprovedBy() {
        return finalApprovedBy;
    }

    /**
     * Sets the value of the finalApprovedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinalApprovedBy(String value) {
        this.finalApprovedBy = value;
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
     * Gets the value of the rejectionReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRejectionReason() {
        return rejectionReason;
    }

    /**
     * Sets the value of the rejectionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRejectionReason(String value) {
        this.rejectionReason = value;
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
     * Gets the value of the srTicketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrTicketId() {
        return srTicketId;
    }

    /**
     * Sets the value of the srTicketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrTicketId(String value) {
        this.srTicketId = value;
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
     * Gets the value of the waiverAmount property.
     * 
     */
    public double getWaiverAmount() {
        return waiverAmount;
    }

    /**
     * Sets the value of the waiverAmount property.
     * 
     */
    public void setWaiverAmount(double value) {
        this.waiverAmount = value;
    }

    /**
     * Gets the value of the waiverHead property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaiverHead() {
        return waiverHead;
    }

    /**
     * Sets the value of the waiverHead property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaiverHead(String value) {
        this.waiverHead = value;
    }

    /**
     * Gets the value of the waiverId property.
     * 
     */
    public long getWaiverId() {
        return waiverId;
    }

    /**
     * Sets the value of the waiverId property.
     * 
     */
    public void setWaiverId(long value) {
        this.waiverId = value;
    }

    /**
     * Gets the value of the waiverPostedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWaiverPostedDate() {
        return waiverPostedDate;
    }

    /**
     * Sets the value of the waiverPostedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWaiverPostedDate(XMLGregorianCalendar value) {
        this.waiverPostedDate = value;
    }

    /**
     * Gets the value of the waiverType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaiverType() {
        return waiverType;
    }

    /**
     * Sets the value of the waiverType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaiverType(String value) {
        this.waiverType = value;
    }

    /**
     * Gets the value of the workflowId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the value of the workflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowId(String value) {
        this.workflowId = value;
    }

    /**
     * Gets the value of the workflowStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowStage() {
        return workflowStage;
    }

    /**
     * Sets the value of the workflowStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowStage(String value) {
        this.workflowStage = value;
    }

}
