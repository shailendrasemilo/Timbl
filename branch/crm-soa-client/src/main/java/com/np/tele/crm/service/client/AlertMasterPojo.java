
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for alertMasterPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="alertMasterPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cms_status_email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inventory_email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="max_retry" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="retry_time_interval" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="trai_end" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="trai_start" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alertMasterPojo", propOrder = {
    "alias",
    "category",
    "cmsStatusEmail",
    "inventoryEmail",
    "maxRetry",
    "retryTimeInterval",
    "subCategory",
    "traiEnd",
    "traiStart"
})
public class AlertMasterPojo {

    protected String alias;
    protected String category;
    @XmlElement(name = "cms_status_email")
    protected String cmsStatusEmail;
    @XmlElement(name = "inventory_email")
    protected String inventoryEmail;
    @XmlElement(name = "max_retry")
    protected int maxRetry;
    @XmlElement(name = "retry_time_interval")
    protected int retryTimeInterval;
    protected String subCategory;
    @XmlElement(name = "trai_end")
    protected int traiEnd;
    @XmlElement(name = "trai_start")
    protected int traiStart;

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
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
     * Gets the value of the cmsStatusEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmsStatusEmail() {
        return cmsStatusEmail;
    }

    /**
     * Sets the value of the cmsStatusEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmsStatusEmail(String value) {
        this.cmsStatusEmail = value;
    }

    /**
     * Gets the value of the inventoryEmail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInventoryEmail() {
        return inventoryEmail;
    }

    /**
     * Sets the value of the inventoryEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInventoryEmail(String value) {
        this.inventoryEmail = value;
    }

    /**
     * Gets the value of the maxRetry property.
     * 
     */
    public int getMaxRetry() {
        return maxRetry;
    }

    /**
     * Sets the value of the maxRetry property.
     * 
     */
    public void setMaxRetry(int value) {
        this.maxRetry = value;
    }

    /**
     * Gets the value of the retryTimeInterval property.
     * 
     */
    public int getRetryTimeInterval() {
        return retryTimeInterval;
    }

    /**
     * Sets the value of the retryTimeInterval property.
     * 
     */
    public void setRetryTimeInterval(int value) {
        this.retryTimeInterval = value;
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
     * Gets the value of the traiEnd property.
     * 
     */
    public int getTraiEnd() {
        return traiEnd;
    }

    /**
     * Sets the value of the traiEnd property.
     * 
     */
    public void setTraiEnd(int value) {
        this.traiEnd = value;
    }

    /**
     * Gets the value of the traiStart property.
     * 
     */
    public int getTraiStart() {
        return traiStart;
    }

    /**
     * Sets the value of the traiStart property.
     * 
     */
    public void setTraiStart(int value) {
        this.traiStart = value;
    }

}
