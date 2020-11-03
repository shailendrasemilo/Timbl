
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rfsReportPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="rfsReportPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectableHomes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localitySector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="networkPartner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="networkType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryPinCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rfsDus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryPinCodes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="society" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeOfField" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rfsReportPojo", propOrder = {
    "area",
    "city",
    "connectableHomes",
    "customerCategory",
    "latitude",
    "localitySector",
    "longitude",
    "networkPartner",
    "networkType",
    "primaryPinCodes",
    "rfsDus",
    "secondaryPinCodes",
    "society",
    "state",
    "typeOfField",
    "societyStatus"
})
public class RfsReportPojo {

    protected String area;
    protected String city;
    protected String connectableHomes;
    protected String customerCategory;
    protected String latitude;
    protected String localitySector;
    protected String longitude;
    protected String networkPartner;
    protected String networkType;
    protected String primaryPinCodes;
    protected String rfsDus;
    protected String secondaryPinCodes;
    protected String society;
    protected String state;
    protected String typeOfField;
    protected String societyStatus;

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the connectableHomes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectableHomes() {
        return connectableHomes;
    }

    /**
     * Sets the value of the connectableHomes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectableHomes(String value) {
        this.connectableHomes = value;
    }

    /**
     * Gets the value of the customerCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerCategory() {
        return customerCategory;
    }

    /**
     * Sets the value of the customerCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerCategory(String value) {
        this.customerCategory = value;
    }

    /**
     * Gets the value of the latitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLatitude(String value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the localitySector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalitySector() {
        return localitySector;
    }

    /**
     * Sets the value of the localitySector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalitySector(String value) {
        this.localitySector = value;
    }

    /**
     * Gets the value of the longitude property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * Sets the value of the longitude property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLongitude(String value) {
        this.longitude = value;
    }

    /**
     * Gets the value of the networkPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkPartner() {
        return networkPartner;
    }

    /**
     * Sets the value of the networkPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkPartner(String value) {
        this.networkPartner = value;
    }

    /**
     * Gets the value of the networkType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkType() {
        return networkType;
    }

    /**
     * Sets the value of the networkType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkType(String value) {
        this.networkType = value;
    }

    /**
     * Gets the value of the primaryPinCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryPinCodes() {
        return primaryPinCodes;
    }

    /**
     * Sets the value of the primaryPinCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryPinCodes(String value) {
        this.primaryPinCodes = value;
    }

    /**
     * Gets the value of the rfsDus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRfsDus() {
        return rfsDus;
    }

    /**
     * Sets the value of the rfsDus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRfsDus(String value) {
        this.rfsDus = value;
    }

    /**
     * Gets the value of the secondaryPinCodes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryPinCodes() {
        return secondaryPinCodes;
    }

    /**
     * Sets the value of the secondaryPinCodes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryPinCodes(String value) {
        this.secondaryPinCodes = value;
    }

    /**
     * Gets the value of the society property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSociety() {
        return society;
    }

    /**
     * Sets the value of the society property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSociety(String value) {
        this.society = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the typeOfField property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfField() {
        return typeOfField;
    }

    /**
     * Sets the value of the typeOfField property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfField(String value) {
        this.typeOfField = value;
    }

    /**
     * Gets the value of the societyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocietyStatus()
    {
        return societyStatus;
    }

    /**
     * Sets the value of the societyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocietyStatus( String societyStatus )
    {
        this.societyStatus = societyStatus;
    }

    
}
