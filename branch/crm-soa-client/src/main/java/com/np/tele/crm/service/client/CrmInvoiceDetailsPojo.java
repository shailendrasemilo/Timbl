
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmInvoiceDetailsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmInvoiceDetailsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="billDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="billGeneDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="billGroupId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billPeriod" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nopasswordPdfFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="passwordPdfFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentDueDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="psFilePath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmInvoiceDetailsPojo", propOrder = {
    "billAmount",
    "billDate",
    "billGeneDate",
    "billGroupId",
    "billNumber",
    "billPeriod",
    "billType",
    "createdBy",
    "createdTime",
    "customerId",
    "dueDate",
    "nopasswordPdfFile",
    "passwordPdfFile",
    "paymentDueDate",
    "psFilePath"
})
public class CrmInvoiceDetailsPojo {

    protected double billAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar billDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar billGeneDate;
    protected String billGroupId;
    protected String billNumber;
    protected String billPeriod;
    protected String billType;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String customerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    protected String nopasswordPdfFile;
    protected String passwordPdfFile;
    protected String paymentDueDate;
    protected String psFilePath;

    /**
     * Gets the value of the billAmount property.
     * 
     */
    public double getBillAmount() {
        return billAmount;
    }

    /**
     * Sets the value of the billAmount property.
     * 
     */
    public void setBillAmount(double value) {
        this.billAmount = value;
    }

    /**
     * Gets the value of the billDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBillDate() {
        return billDate;
    }

    /**
     * Sets the value of the billDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBillDate(XMLGregorianCalendar value) {
        this.billDate = value;
    }

    /**
     * Gets the value of the billGeneDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBillGeneDate() {
        return billGeneDate;
    }

    /**
     * Sets the value of the billGeneDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBillGeneDate(XMLGregorianCalendar value) {
        this.billGeneDate = value;
    }

    /**
     * Gets the value of the billGroupId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillGroupId() {
        return billGroupId;
    }

    /**
     * Sets the value of the billGroupId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillGroupId(String value) {
        this.billGroupId = value;
    }

    /**
     * Gets the value of the billNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillNumber() {
        return billNumber;
    }

    /**
     * Sets the value of the billNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillNumber(String value) {
        this.billNumber = value;
    }

    /**
     * Gets the value of the billPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillPeriod() {
        return billPeriod;
    }

    /**
     * Sets the value of the billPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillPeriod(String value) {
        this.billPeriod = value;
    }

    /**
     * Gets the value of the billType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillType() {
        return billType;
    }

    /**
     * Sets the value of the billType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillType(String value) {
        this.billType = value;
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
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the nopasswordPdfFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNopasswordPdfFile() {
        return nopasswordPdfFile;
    }

    /**
     * Sets the value of the nopasswordPdfFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNopasswordPdfFile(String value) {
        this.nopasswordPdfFile = value;
    }

    /**
     * Gets the value of the passwordPdfFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPasswordPdfFile() {
        return passwordPdfFile;
    }

    /**
     * Sets the value of the passwordPdfFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPasswordPdfFile(String value) {
        this.passwordPdfFile = value;
    }

    /**
     * Gets the value of the paymentDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentDueDate() {
        return paymentDueDate;
    }

    /**
     * Sets the value of the paymentDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentDueDate(String value) {
        this.paymentDueDate = value;
    }

    /**
     * Gets the value of the psFilePath property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPsFilePath() {
        return psFilePath;
    }

    /**
     * Sets the value of the psFilePath property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPsFilePath(String value) {
        this.psFilePath = value;
    }

}
