
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmCustAdditionalDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCustAdditionalDetails">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="addonPlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allocatedVolumeQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="billCycle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentBandwidth" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="currentSession" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="planCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planRental" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="primaryQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="primaryRemainQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="primaryUsedQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="remainVolumeQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="secondaryQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryRemainQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="secondaryUsedQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usedVolumeQuota" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCustAdditionalDetails", propOrder = {
    "activationDate",
    "addonPlanCode",
    "allocatedVolumeQuota",
    "balance",
    "billCycle",
    "currentBandwidth",
    "currentSession",
    "expiryDate",
    "planCode",
    "planRental",
    "primaryQuota",
    "primaryRemainQuota",
    "primaryUsedQuota",
    "remainVolumeQuota",
    "secondaryQuota",
    "secondaryRemainQuota",
    "secondaryUsedQuota",
    "status",
    "usedVolumeQuota"
})
public class CrmCustAdditionalDetails {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    protected String addonPlanCode;
    protected Long allocatedVolumeQuota;
    protected Double balance;
    protected String billCycle;
    protected Long currentBandwidth;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar currentSession;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiryDate;
    protected String planCode;
    protected Double planRental;
    protected Long primaryQuota;
    protected Long primaryRemainQuota;
    protected Long primaryUsedQuota;
    protected Long remainVolumeQuota;
    protected String secondaryQuota;
    protected Long secondaryRemainQuota;
    protected Long secondaryUsedQuota;
    protected String status;
    protected Long usedVolumeQuota;

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
     * Gets the value of the addonPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddonPlanCode() {
        return addonPlanCode;
    }

    /**
     * Sets the value of the addonPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddonPlanCode(String value) {
        this.addonPlanCode = value;
    }

    /**
     * Gets the value of the allocatedVolumeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAllocatedVolumeQuota() {
        return allocatedVolumeQuota;
    }

    /**
     * Sets the value of the allocatedVolumeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAllocatedVolumeQuota(Long value) {
        this.allocatedVolumeQuota = value;
    }

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBalance(Double value) {
        this.balance = value;
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
     * Gets the value of the currentBandwidth property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCurrentBandwidth() {
        return currentBandwidth;
    }

    /**
     * Sets the value of the currentBandwidth property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCurrentBandwidth(Long value) {
        this.currentBandwidth = value;
    }

    /**
     * Gets the value of the currentSession property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurrentSession() {
        return currentSession;
    }

    /**
     * Sets the value of the currentSession property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrentSession(XMLGregorianCalendar value) {
        this.currentSession = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
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
     * Gets the value of the planRental property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPlanRental() {
        return planRental;
    }

    /**
     * Sets the value of the planRental property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPlanRental(Double value) {
        this.planRental = value;
    }

    /**
     * Gets the value of the primaryQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrimaryQuota() {
        return primaryQuota;
    }

    /**
     * Sets the value of the primaryQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrimaryQuota(Long value) {
        this.primaryQuota = value;
    }

    /**
     * Gets the value of the primaryRemainQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrimaryRemainQuota() {
        return primaryRemainQuota;
    }

    /**
     * Sets the value of the primaryRemainQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrimaryRemainQuota(Long value) {
        this.primaryRemainQuota = value;
    }

    /**
     * Gets the value of the primaryUsedQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPrimaryUsedQuota() {
        return primaryUsedQuota;
    }

    /**
     * Sets the value of the primaryUsedQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPrimaryUsedQuota(Long value) {
        this.primaryUsedQuota = value;
    }

    /**
     * Gets the value of the remainVolumeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRemainVolumeQuota() {
        return remainVolumeQuota;
    }

    /**
     * Sets the value of the remainVolumeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRemainVolumeQuota(Long value) {
        this.remainVolumeQuota = value;
    }

    /**
     * Gets the value of the secondaryQuota property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryQuota() {
        return secondaryQuota;
    }

    /**
     * Sets the value of the secondaryQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryQuota(String value) {
        this.secondaryQuota = value;
    }

    /**
     * Gets the value of the secondaryRemainQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSecondaryRemainQuota() {
        return secondaryRemainQuota;
    }

    /**
     * Sets the value of the secondaryRemainQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSecondaryRemainQuota(Long value) {
        this.secondaryRemainQuota = value;
    }

    /**
     * Gets the value of the secondaryUsedQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSecondaryUsedQuota() {
        return secondaryUsedQuota;
    }

    /**
     * Sets the value of the secondaryUsedQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSecondaryUsedQuota(Long value) {
        this.secondaryUsedQuota = value;
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
     * Gets the value of the usedVolumeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getUsedVolumeQuota() {
        return usedVolumeQuota;
    }

    /**
     * Sets the value of the usedVolumeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setUsedVolumeQuota(Long value) {
        this.usedVolumeQuota = value;
    }

}
