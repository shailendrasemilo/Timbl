
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmCustAadharNumberPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCustAadharNumberPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aadharAdd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aadharNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="propertyDetails" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCustAadharNumberPojo", propOrder = {
    "aadharAdd",
    "aadharNumber",
    "instAddress",
    "propertyDetails"
})
public class CrmCustAadharNumberPojo {

    protected String aadharAdd;
    protected String aadharNumber;
    protected String instAddress;
    protected String propertyDetails;

    /**
     * Gets the value of the aadharAdd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAadharAdd() {
        return aadharAdd;
    }

    /**
     * Sets the value of the aadharAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAadharAdd(String value) {
        this.aadharAdd = value;
    }

    /**
     * Gets the value of the aadharNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAadharNumber() {
        return aadharNumber;
    }

    /**
     * Sets the value of the aadharNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAadharNumber(String value) {
        this.aadharNumber = value;
    }

    /**
     * Gets the value of the instAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstAddress() {
        return instAddress;
    }

    /**
     * Sets the value of the instAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstAddress(String value) {
        this.instAddress = value;
    }

    /**
     * Gets the value of the propertyDetails property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyDetails() {
        return propertyDetails;
    }

    /**
     * Sets the value of the propertyDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyDetails(String value) {
        this.propertyDetails = value;
    }

}
