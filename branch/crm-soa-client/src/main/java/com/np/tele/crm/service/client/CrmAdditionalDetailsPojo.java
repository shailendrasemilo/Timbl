
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmAdditionalDetailsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmAdditionalDetailsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bankAccountNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bankAccountType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bankName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crcdCardNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="crcdExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crcdType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerRecordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="displayCrcdExpiryDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mobileImeiNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileMake" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobileNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="vehicleDetail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="vehicleMake" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmAdditionalDetailsPojo", propOrder = {
    "bankAccountNo",
    "bankAccountType",
    "bankBranch",
    "bankName",
    "crcdCardNo",
    "crcdExpiryDate",
    "crcdType",
    "createdBy",
    "createdTime",
    "customerRecordId",
    "displayCrcdExpiryDate",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "lastModifiedBy",
    "lastModifiedTime",
    "mobileImeiNo",
    "mobileMake",
    "mobileNo",
    "recordId",
    "vehicleDetail",
    "vehicleMake"
})
public class CrmAdditionalDetailsPojo {

    protected long bankAccountNo;
    protected String bankAccountType;
    protected String bankBranch;
    protected String bankName;
    protected long crcdCardNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crcdExpiryDate;
    protected String crcdType;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected long customerRecordId;
    protected String displayCrcdExpiryDate;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String mobileImeiNo;
    protected String mobileMake;
    protected String mobileNo;
    protected long recordId;
    protected String vehicleDetail;
    protected String vehicleMake;

    /**
     * Gets the value of the bankAccountNo property.
     * 
     */
    public long getBankAccountNo() {
        return bankAccountNo;
    }

    /**
     * Sets the value of the bankAccountNo property.
     * 
     */
    public void setBankAccountNo(long value) {
        this.bankAccountNo = value;
    }

    /**
     * Gets the value of the bankAccountType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBankAccountType() {
        return bankAccountType;
    }

    /**
     * Sets the value of the bankAccountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBankAccountType(String value) {
        this.bankAccountType = value;
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
     * Gets the value of the crcdCardNo property.
     * 
     */
    public long getCrcdCardNo() {
        return crcdCardNo;
    }

    /**
     * Sets the value of the crcdCardNo property.
     * 
     */
    public void setCrcdCardNo(long value) {
        this.crcdCardNo = value;
    }

    /**
     * Gets the value of the crcdExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrcdExpiryDate() {
        return crcdExpiryDate;
    }

    /**
     * Sets the value of the crcdExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrcdExpiryDate(XMLGregorianCalendar value) {
        this.crcdExpiryDate = value;
    }

    /**
     * Gets the value of the crcdType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrcdType() {
        return crcdType;
    }

    /**
     * Sets the value of the crcdType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrcdType(String value) {
        this.crcdType = value;
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
     * Gets the value of the displayCrcdExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayCrcdExpiryDate() {
        return displayCrcdExpiryDate;
    }

    /**
     * Sets the value of the displayCrcdExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayCrcdExpiryDate(String value) {
        this.displayCrcdExpiryDate = value;
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
     * Gets the value of the mobileImeiNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileImeiNo() {
        return mobileImeiNo;
    }

    /**
     * Sets the value of the mobileImeiNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileImeiNo(String value) {
        this.mobileImeiNo = value;
    }

    /**
     * Gets the value of the mobileMake property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileMake() {
        return mobileMake;
    }

    /**
     * Sets the value of the mobileMake property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileMake(String value) {
        this.mobileMake = value;
    }

    /**
     * Gets the value of the mobileNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobileNo(String value) {
        this.mobileNo = value;
    }

    /**
     * Gets the value of the recordId property.
     * 
     */
    public long getRecordId() {
        return recordId;
    }

    /**
     * Sets the value of the recordId property.
     * 
     */
    public void setRecordId(long value) {
        this.recordId = value;
    }

    /**
     * Gets the value of the vehicleDetail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleDetail() {
        return vehicleDetail;
    }

    /**
     * Sets the value of the vehicleDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleDetail(String value) {
        this.vehicleDetail = value;
    }

    /**
     * Gets the value of the vehicleMake property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVehicleMake() {
        return vehicleMake;
    }

    /**
     * Sets the value of the vehicleMake property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVehicleMake(String value) {
        this.vehicleMake = value;
    }

}
