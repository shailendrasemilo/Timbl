
package com.np.tele.crm.service.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmPaymentDetailsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmPaymentDetailsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activationCharges" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="bankBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bouncingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="bouncingReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="caseStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chequeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="chequeDdNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmsId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRecordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="deposit" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="displayChequeDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="entityType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="installationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paymentChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paymentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="paymentMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwTransId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="realzationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="receiptNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="registrationCharges" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="reversalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="reversalReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="securityCharges" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmPaymentDetailsPojo", propOrder = {
    "activationCharges",
    "amount",
    "bankBranch",
    "bankCity",
    "bankName",
    "bankTransId",
    "bouncingDate",
    "bouncingReason",
    "caseStatus",
    "chequeDate",
    "chequeDdNo",
    "cmsId",
    "createdBy",
    "createdTime",
    "crfId",
    "customerId",
    "customerRecordId",
    "deposit",
    "displayChequeDate",
    "editable",
    "entityType",
    "installationStatus",
    "lastModifiedBy",
    "lastModifiedTime",
    "paymentChannel",
    "paymentDate",
    "paymentId",
    "paymentMode",
    "paymentStatus",
    "pgwTransId",
    "postingDate",
    "realzationStatus",
    "receiptNo",
    "registrationCharges",
    "reversalDate",
    "reversalReason",
    "securityCharges",
    "serviceType",
    "srId",
    "status",
    "transactionId"
})
public class CrmPaymentDetailsPojo {

    protected double activationCharges;
    protected double amount;
    protected String bankBranch;
    protected String bankCity;
    protected String bankName;
    protected String bankTransId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar bouncingDate;
    protected String bouncingReason;
    protected String caseStatus;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar chequeDate;
    protected String chequeDdNo;
    protected Long cmsId;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String crfId;
    protected String customerId;
    protected long customerRecordId;
    protected BigDecimal deposit;
    protected String displayChequeDate;
    protected boolean editable;
    protected String entityType;
    protected String installationStatus;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String paymentChannel;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar paymentDate;
    protected long paymentId;
    protected String paymentMode;
    protected String paymentStatus;
    protected String pgwTransId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postingDate;
    protected String realzationStatus;
    protected String receiptNo;
    protected double registrationCharges;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar reversalDate;
    protected String reversalReason;
    protected double securityCharges;
    protected String serviceType;
    protected String srId;
    protected String status;
    protected String transactionId;

    /**
     * Gets the value of the activationCharges property.
     * 
     */
    public double getActivationCharges() {
        return activationCharges;
    }

    /**
     * Sets the value of the activationCharges property.
     * 
     */
    public void setActivationCharges(double value) {
        this.activationCharges = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the bankBranch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankBranch() {
        return bankBranch;
    }

    /**
     * Sets the value of the bankBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankBranch(String value) {
        this.bankBranch = value;
    }

    /**
     * Gets the value of the bankCity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankCity() {
        return bankCity;
    }

    /**
     * Sets the value of the bankCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankCity(String value) {
        this.bankCity = value;
    }

    /**
     * Gets the value of the bankName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Sets the value of the bankName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankName(String value) {
        this.bankName = value;
    }

    /**
     * Gets the value of the bankTransId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankTransId() {
        return bankTransId;
    }

    /**
     * Sets the value of the bankTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankTransId(String value) {
        this.bankTransId = value;
    }

    /**
     * Gets the value of the bouncingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBouncingDate() {
        return bouncingDate;
    }

    /**
     * Sets the value of the bouncingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBouncingDate(XMLGregorianCalendar value) {
        this.bouncingDate = value;
    }

    /**
     * Gets the value of the bouncingReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBouncingReason() {
        return bouncingReason;
    }

    /**
     * Sets the value of the bouncingReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBouncingReason(String value) {
        this.bouncingReason = value;
    }

    /**
     * Gets the value of the caseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaseStatus() {
        return caseStatus;
    }

    /**
     * Sets the value of the caseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaseStatus(String value) {
        this.caseStatus = value;
    }

    /**
     * Gets the value of the chequeDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getChequeDate() {
        return chequeDate;
    }

    /**
     * Sets the value of the chequeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setChequeDate(XMLGregorianCalendar value) {
        this.chequeDate = value;
    }

    /**
     * Gets the value of the chequeDdNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChequeDdNo() {
        return chequeDdNo;
    }

    /**
     * Sets the value of the chequeDdNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChequeDdNo(String value) {
        this.chequeDdNo = value;
    }

    /**
     * Gets the value of the cmsId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCmsId() {
        return cmsId;
    }

    /**
     * Sets the value of the cmsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCmsId(Long value) {
        this.cmsId = value;
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
     * Gets the value of the customerRecordId property.
     * 
     */
    public long getCustomerRecordId() {
        return customerRecordId;
    }

    /**
     * Sets the value of the customerRecordId property.
     * 
     */
    public void setCustomerRecordId(long value) {
        this.customerRecordId = value;
    }

    /**
     * Gets the value of the deposit property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDeposit() {
        return deposit;
    }

    /**
     * Sets the value of the deposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDeposit(BigDecimal value) {
        this.deposit = value;
    }

    /**
     * Gets the value of the displayChequeDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayChequeDate() {
        return displayChequeDate;
    }

    /**
     * Sets the value of the displayChequeDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayChequeDate(String value) {
        this.displayChequeDate = value;
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
     * Gets the value of the entityType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * Sets the value of the entityType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityType(String value) {
        this.entityType = value;
    }

    /**
     * Gets the value of the installationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallationStatus() {
        return installationStatus;
    }

    /**
     * Sets the value of the installationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallationStatus(String value) {
        this.installationStatus = value;
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
     * Gets the value of the paymentChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentChannel() {
        return paymentChannel;
    }

    /**
     * Sets the value of the paymentChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentChannel(String value) {
        this.paymentChannel = value;
    }

    /**
     * Gets the value of the paymentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPaymentDate() {
        return paymentDate;
    }

    /**
     * Sets the value of the paymentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPaymentDate(XMLGregorianCalendar value) {
        this.paymentDate = value;
    }

    /**
     * Gets the value of the paymentId property.
     * 
     */
    public long getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     */
    public void setPaymentId(long value) {
        this.paymentId = value;
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
     * Gets the value of the paymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentStatus() {
        return paymentStatus;
    }

    /**
     * Sets the value of the paymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentStatus(String value) {
        this.paymentStatus = value;
    }

    /**
     * Gets the value of the pgwTransId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwTransId() {
        return pgwTransId;
    }

    /**
     * Sets the value of the pgwTransId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwTransId(String value) {
        this.pgwTransId = value;
    }

    /**
     * Gets the value of the postingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostingDate(XMLGregorianCalendar value) {
        this.postingDate = value;
    }

    /**
     * Gets the value of the realzationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRealzationStatus() {
        return realzationStatus;
    }

    /**
     * Sets the value of the realzationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRealzationStatus(String value) {
        this.realzationStatus = value;
    }

    /**
     * Gets the value of the receiptNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiptNo() {
        return receiptNo;
    }

    /**
     * Sets the value of the receiptNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiptNo(String value) {
        this.receiptNo = value;
    }

    /**
     * Gets the value of the registrationCharges property.
     * 
     */
    public double getRegistrationCharges() {
        return registrationCharges;
    }

    /**
     * Sets the value of the registrationCharges property.
     * 
     */
    public void setRegistrationCharges(double value) {
        this.registrationCharges = value;
    }

    /**
     * Gets the value of the reversalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getReversalDate() {
        return reversalDate;
    }

    /**
     * Sets the value of the reversalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setReversalDate(XMLGregorianCalendar value) {
        this.reversalDate = value;
    }

    /**
     * Gets the value of the reversalReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReversalReason() {
        return reversalReason;
    }

    /**
     * Sets the value of the reversalReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReversalReason(String value) {
        this.reversalReason = value;
    }

    /**
     * Gets the value of the securityCharges property.
     * 
     */
    public double getSecurityCharges() {
        return securityCharges;
    }

    /**
     * Sets the value of the securityCharges property.
     * 
     */
    public void setSecurityCharges(double value) {
        this.securityCharges = value;
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
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionId(String value) {
        this.transactionId = value;
    }

}
