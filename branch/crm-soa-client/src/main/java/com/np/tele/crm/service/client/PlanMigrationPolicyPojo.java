
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for planMigrationPolicyPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="planMigrationPolicyPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="decDULIMMAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="decDULNBCAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="decSpeedIMMAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="decSpeedNBCAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incDULIMMAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incDULNBCAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incSpeedIMMAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="incSpeedNBCAdvice" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "planMigrationPolicyPojo", propOrder = {
    "category",
    "decDULIMMAdvice",
    "decDULNBCAdvice",
    "decSpeedIMMAdvice",
    "decSpeedNBCAdvice",
    "incDULIMMAdvice",
    "incDULNBCAdvice",
    "incSpeedIMMAdvice",
    "incSpeedNBCAdvice",
    "subCategory"
})
public class PlanMigrationPolicyPojo {

    protected String category;
    protected String decDULIMMAdvice;
    protected String decDULNBCAdvice;
    protected String decSpeedIMMAdvice;
    protected String decSpeedNBCAdvice;
    protected String incDULIMMAdvice;
    protected String incDULNBCAdvice;
    protected String incSpeedIMMAdvice;
    protected String incSpeedNBCAdvice;
    protected String subCategory;

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
     * Gets the value of the decDULIMMAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecDULIMMAdvice() {
        return decDULIMMAdvice;
    }

    /**
     * Sets the value of the decDULIMMAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecDULIMMAdvice(String value) {
        this.decDULIMMAdvice = value;
    }

    /**
     * Gets the value of the decDULNBCAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecDULNBCAdvice() {
        return decDULNBCAdvice;
    }

    /**
     * Sets the value of the decDULNBCAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecDULNBCAdvice(String value) {
        this.decDULNBCAdvice = value;
    }

    /**
     * Gets the value of the decSpeedIMMAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecSpeedIMMAdvice() {
        return decSpeedIMMAdvice;
    }

    /**
     * Sets the value of the decSpeedIMMAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecSpeedIMMAdvice(String value) {
        this.decSpeedIMMAdvice = value;
    }

    /**
     * Gets the value of the decSpeedNBCAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecSpeedNBCAdvice() {
        return decSpeedNBCAdvice;
    }

    /**
     * Sets the value of the decSpeedNBCAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecSpeedNBCAdvice(String value) {
        this.decSpeedNBCAdvice = value;
    }

    /**
     * Gets the value of the incDULIMMAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncDULIMMAdvice() {
        return incDULIMMAdvice;
    }

    /**
     * Sets the value of the incDULIMMAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncDULIMMAdvice(String value) {
        this.incDULIMMAdvice = value;
    }

    /**
     * Gets the value of the incDULNBCAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncDULNBCAdvice() {
        return incDULNBCAdvice;
    }

    /**
     * Sets the value of the incDULNBCAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncDULNBCAdvice(String value) {
        this.incDULNBCAdvice = value;
    }

    /**
     * Gets the value of the incSpeedIMMAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncSpeedIMMAdvice() {
        return incSpeedIMMAdvice;
    }

    /**
     * Sets the value of the incSpeedIMMAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncSpeedIMMAdvice(String value) {
        this.incSpeedIMMAdvice = value;
    }

    /**
     * Gets the value of the incSpeedNBCAdvice property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncSpeedNBCAdvice() {
        return incSpeedNBCAdvice;
    }

    /**
     * Sets the value of the incSpeedNBCAdvice property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncSpeedNBCAdvice(String value) {
        this.incSpeedNBCAdvice = value;
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

}
