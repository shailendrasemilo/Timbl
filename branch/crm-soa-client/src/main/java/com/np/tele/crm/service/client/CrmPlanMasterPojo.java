
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmPlanMasterPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmPlanMasterPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="actvAllowedYn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addonAllowedYn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="advTelservPayment" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="advTelsolnPayment" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="boosterAllowedYn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="deactivationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="migrAllowedYn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="planName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planUsageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prepaidYn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryQuota" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="primarySpeed" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="quotaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rentExclTax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="rentInclTax" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="secondaryQuota" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="secondarySpeed" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="securityDeposit" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="service" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmPlanMasterPojo", propOrder = {
    "activationDate",
    "actvAllowedYn",
    "addonAllowedYn",
    "advTelservPayment",
    "advTelsolnPayment",
    "boosterAllowedYn",
    "brand",
    "createdBy",
    "createdTime",
    "deactivationDate",
    "editable",
    "lastModifiedBy",
    "lastModifiedTime",
    "migrAllowedYn",
    "offerCode",
    "planCategory",
    "planCode",
    "planId",
    "planName",
    "planType",
    "planUsageType",
    "prepaidYn",
    "primaryQuota",
    "primarySpeed",
    "quantity",
    "quotaType",
    "rentExclTax",
    "rentInclTax",
    "secondaryQuota",
    "secondarySpeed",
    "securityDeposit",
    "service",
    "status"
})
public class CrmPlanMasterPojo {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    protected String actvAllowedYn;
    protected String addonAllowedYn;
    protected Double advTelservPayment;
    protected Double advTelsolnPayment;
    protected String boosterAllowedYn;
    protected String brand;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar deactivationDate;
    protected boolean editable;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String migrAllowedYn;
    protected String offerCode;
    protected String planCategory;
    protected String planCode;
    protected long planId;
    protected String planName;
    protected String planType;
    protected String planUsageType;
    protected String prepaidYn;
    protected long primaryQuota;
    protected long primarySpeed;
    protected byte quantity;
    protected String quotaType;
    protected Double rentExclTax;
    protected Double rentInclTax;
    protected long secondaryQuota;
    protected long secondarySpeed;
    protected Double securityDeposit;
    protected String service;
    protected String status;

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
     * Gets the value of the actvAllowedYn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActvAllowedYn() {
        return actvAllowedYn;
    }

    /**
     * Sets the value of the actvAllowedYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActvAllowedYn(String value) {
        this.actvAllowedYn = value;
    }

    /**
     * Gets the value of the addonAllowedYn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddonAllowedYn() {
        return addonAllowedYn;
    }

    /**
     * Sets the value of the addonAllowedYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddonAllowedYn(String value) {
        this.addonAllowedYn = value;
    }

    /**
     * Gets the value of the advTelservPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAdvTelservPayment() {
        return advTelservPayment;
    }

    /**
     * Sets the value of the advTelservPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAdvTelservPayment(Double value) {
        this.advTelservPayment = value;
    }

    /**
     * Gets the value of the advTelsolnPayment property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAdvTelsolnPayment() {
        return advTelsolnPayment;
    }

    /**
     * Sets the value of the advTelsolnPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAdvTelsolnPayment(Double value) {
        this.advTelsolnPayment = value;
    }

    /**
     * Gets the value of the boosterAllowedYn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoosterAllowedYn() {
        return boosterAllowedYn;
    }

    /**
     * Sets the value of the boosterAllowedYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoosterAllowedYn(String value) {
        this.boosterAllowedYn = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
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
     * Gets the value of the deactivationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDeactivationDate() {
        return deactivationDate;
    }

    /**
     * Sets the value of the deactivationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDeactivationDate(XMLGregorianCalendar value) {
        this.deactivationDate = value;
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
     * Gets the value of the migrAllowedYn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMigrAllowedYn() {
        return migrAllowedYn;
    }

    /**
     * Sets the value of the migrAllowedYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMigrAllowedYn(String value) {
        this.migrAllowedYn = value;
    }

    /**
     * Gets the value of the offerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferCode() {
        return offerCode;
    }

    /**
     * Sets the value of the offerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferCode(String value) {
        this.offerCode = value;
    }

    /**
     * Gets the value of the planCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanCategory() {
        return planCategory;
    }

    /**
     * Sets the value of the planCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanCategory(String value) {
        this.planCategory = value;
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
     * Gets the value of the planId property.
     * 
     */
    public long getPlanId() {
        return planId;
    }

    /**
     * Sets the value of the planId property.
     * 
     */
    public void setPlanId(long value) {
        this.planId = value;
    }

    /**
     * Gets the value of the planName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * Sets the value of the planName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanName(String value) {
        this.planName = value;
    }

    /**
     * Gets the value of the planType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanType() {
        return planType;
    }

    /**
     * Sets the value of the planType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanType(String value) {
        this.planType = value;
    }

    /**
     * Gets the value of the planUsageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanUsageType() {
        return planUsageType;
    }

    /**
     * Sets the value of the planUsageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanUsageType(String value) {
        this.planUsageType = value;
    }

    /**
     * Gets the value of the prepaidYn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrepaidYn() {
        return prepaidYn;
    }

    /**
     * Sets the value of the prepaidYn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrepaidYn(String value) {
        this.prepaidYn = value;
    }

    /**
     * Gets the value of the primaryQuota property.
     * 
     */
    public long getPrimaryQuota() {
        return primaryQuota;
    }

    /**
     * Sets the value of the primaryQuota property.
     * 
     */
    public void setPrimaryQuota(long value) {
        this.primaryQuota = value;
    }

    /**
     * Gets the value of the primarySpeed property.
     * 
     */
    public long getPrimarySpeed() {
        return primarySpeed;
    }

    /**
     * Sets the value of the primarySpeed property.
     * 
     */
    public void setPrimarySpeed(long value) {
        this.primarySpeed = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     */
    public byte getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     */
    public void setQuantity(byte value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the quotaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQuotaType() {
        return quotaType;
    }

    /**
     * Sets the value of the quotaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQuotaType(String value) {
        this.quotaType = value;
    }

    /**
     * Gets the value of the rentExclTax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRentExclTax() {
        return rentExclTax;
    }

    /**
     * Sets the value of the rentExclTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRentExclTax(Double value) {
        this.rentExclTax = value;
    }

    /**
     * Gets the value of the rentInclTax property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRentInclTax() {
        return rentInclTax;
    }

    /**
     * Sets the value of the rentInclTax property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRentInclTax(Double value) {
        this.rentInclTax = value;
    }

    /**
     * Gets the value of the secondaryQuota property.
     * 
     */
    public long getSecondaryQuota() {
        return secondaryQuota;
    }

    /**
     * Sets the value of the secondaryQuota property.
     * 
     */
    public void setSecondaryQuota(long value) {
        this.secondaryQuota = value;
    }

    /**
     * Gets the value of the secondarySpeed property.
     * 
     */
    public long getSecondarySpeed() {
        return secondarySpeed;
    }

    /**
     * Sets the value of the secondarySpeed property.
     * 
     */
    public void setSecondarySpeed(long value) {
        this.secondarySpeed = value;
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
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setService(String value) {
        this.service = value;
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

}
