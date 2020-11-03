
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmQrcSubSubCategoriesPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmQrcSubSubCategoriesPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="functionalBinId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="massOutageRol" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="modificationAllowed" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="moduleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcSubCategoryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qrcSubSubCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcSubSubCategoryId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="qrcType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolutionType" type="{http://www.w3.org/2001/XMLSchema}byte" minOccurs="0"/>
 *         &lt;element name="sla" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="slaUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "crmQrcSubSubCategoriesPojo", propOrder = {
    "createdBy",
    "createdTime",
    "functionalBinId",
    "lastModifiedBy",
    "lastModifiedTime",
    "massOutageRol",
    "modificationAllowed",
    "moduleType",
    "qrcSubCategoryId",
    "qrcSubSubCategory",
    "qrcSubSubCategoryId",
    "qrcType",
    "resolutionType",
    "sla",
    "slaUnit",
    "status"
})
public class CrmQrcSubSubCategoriesPojo {

    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected long functionalBinId;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String massOutageRol;
    protected String modificationAllowed;
    protected String moduleType;
    protected long qrcSubCategoryId;
    protected String qrcSubSubCategory;
    protected long qrcSubSubCategoryId;
    protected String qrcType;
    protected Byte resolutionType;
    protected Integer sla;
    protected String slaUnit;
    protected String status;

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
     * Gets the value of the functionalBinId property.
     * 
     */
    public long getFunctionalBinId() {
        return functionalBinId;
    }

    /**
     * Sets the value of the functionalBinId property.
     * 
     */
    public void setFunctionalBinId(long value) {
        this.functionalBinId = value;
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
     * Gets the value of the massOutageRol property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMassOutageRol() {
        return massOutageRol;
    }

    /**
     * Sets the value of the massOutageRol property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMassOutageRol(String value) {
        this.massOutageRol = value;
    }

    /**
     * Gets the value of the modificationAllowed property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModificationAllowed() {
        return modificationAllowed;
    }

    /**
     * Sets the value of the modificationAllowed property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModificationAllowed(String value) {
        this.modificationAllowed = value;
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
     * Gets the value of the resolutionType property.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getResolutionType() {
        return resolutionType;
    }

    /**
     * Sets the value of the resolutionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setResolutionType(Byte value) {
        this.resolutionType = value;
    }

    /**
     * Gets the value of the sla property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSla() {
        return sla;
    }

    /**
     * Sets the value of the sla property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSla(Integer value) {
        this.sla = value;
    }

    /**
     * Gets the value of the slaUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaUnit() {
        return slaUnit;
    }

    /**
     * Sets the value of the slaUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaUnit(String value) {
        this.slaUnit = value;
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
