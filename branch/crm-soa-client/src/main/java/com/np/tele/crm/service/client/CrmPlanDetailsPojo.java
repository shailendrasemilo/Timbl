
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmPlanDetailsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmPlanDetailsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addOnPlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="basePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="preferredBillLang" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promoCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="usageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmPlanDetailsPojo", propOrder = {
    "addOnPlanCode",
    "basePlanCode",
    "billMode",
    "createdBy",
    "createdTime",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "lastModifiedBy",
    "lastModifiedTime",
    "preferredBillLang",
    "promoCode",
    "recordId",
    "usageType"
})
public class CrmPlanDetailsPojo {

    protected String addOnPlanCode;
    protected String basePlanCode;
    protected String billMode;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String preferredBillLang;
    protected String promoCode;
    protected long recordId;
    protected String usageType;

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
     * Gets the value of the basePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasePlanCode() {
        return basePlanCode;
    }

    /**
     * Sets the value of the basePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasePlanCode(String value) {
        this.basePlanCode = value;
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
     * Gets the value of the preferredBillLang property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredBillLang() {
        return preferredBillLang;
    }

    /**
     * Sets the value of the preferredBillLang property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredBillLang(String value) {
        this.preferredBillLang = value;
    }

    /**
     * Gets the value of the promoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromoCode() {
        return promoCode;
    }

    /**
     * Sets the value of the promoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoCode(String value) {
        this.promoCode = value;
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
     * Gets the value of the usageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageType() {
        return usageType;
    }

    /**
     * Sets the value of the usageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageType(String value) {
        this.usageType = value;
    }

    @Override
    public String toString()
    {
        return "CrmPlanDetailsPojo [addOnPlanCode=" + addOnPlanCode + ", basePlanCode=" + basePlanCode + ", billMode="
                + billMode + ", createdBy=" + createdBy + ", createdTime=" + createdTime + ", displayCreatedTime="
                + displayCreatedTime + ", displayLastModifiedTime=" + displayLastModifiedTime + ", lastModifiedBy="
                + lastModifiedBy + ", lastModifiedTime=" + lastModifiedTime + ", preferredBillLang="
                + preferredBillLang + ", promoCode=" + promoCode + ", recordId=" + recordId + ", usageType="
                + usageType + "]";
    }

}
