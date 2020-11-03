
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for societyPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="societyPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="connectableHomes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="latitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="longitude" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="networkAvailability" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryPincode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="rfsDus" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="secPincode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="societyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="societyName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="societyNetworkPartners" type="{http://service.gis.crm.tele.np.com/}societyNetworkPartnerPojo" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "societyPojo", propOrder = {
    "areaId",
    "connectableHomes",
    "createdBy",
    "createdTime",
    "customerCategory",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "editable",
    "lastModifiedBy",
    "lastModifiedTime",
    "latitude",
    "localityName",
    "longitude",
    "networkAvailability",
    "primaryPincode",
    "rfsDus",
    "secPincode",
    "societyId",
    "societyName",
    "societyNetworkPartners",
    "status"
})
public class SocietyPojo {

    protected long areaId;
    protected String connectableHomes;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String customerCategory;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    protected boolean editable;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String latitude;
    protected String localityName;
    protected String longitude;
    protected String networkAvailability;
    protected Integer primaryPincode;
    protected long rfsDus;
    protected Integer secPincode;
    protected long societyId;
    protected String societyName;
    @XmlElement(nillable = true)
    protected List<SocietyNetworkPartnerPojo> societyNetworkPartners;
    protected String status;

    /**
     * Gets the value of the areaId property.
     * 
     */
    public long getAreaId() {
        return areaId;
    }

    /**
     * Sets the value of the areaId property.
     * 
     */
    public void setAreaId(long value) {
        this.areaId = value;
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
     * Gets the value of the localityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalityName() {
        return localityName;
    }

    /**
     * Sets the value of the localityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalityName(String value) {
        this.localityName = value;
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
     * Gets the value of the networkAvailability property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkAvailability() {
        return networkAvailability;
    }

    /**
     * Sets the value of the networkAvailability property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkAvailability(String value) {
        this.networkAvailability = value;
    }

    /**
     * Gets the value of the primaryPincode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPrimaryPincode() {
        return primaryPincode;
    }

    /**
     * Sets the value of the primaryPincode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPrimaryPincode(Integer value) {
        this.primaryPincode = value;
    }

    /**
     * Gets the value of the rfsDus property.
     * 
     */
    public long getRfsDus() {
        return rfsDus;
    }

    /**
     * Sets the value of the rfsDus property.
     * 
     */
    public void setRfsDus(long value) {
        this.rfsDus = value;
    }

    /**
     * Gets the value of the secPincode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSecPincode() {
        return secPincode;
    }

    /**
     * Sets the value of the secPincode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSecPincode(Integer value) {
        this.secPincode = value;
    }

    /**
     * Gets the value of the societyId property.
     * 
     */
    public long getSocietyId() {
        return societyId;
    }

    /**
     * Sets the value of the societyId property.
     * 
     */
    public void setSocietyId(long value) {
        this.societyId = value;
    }

    /**
     * Gets the value of the societyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSocietyName() {
        return societyName;
    }

    /**
     * Sets the value of the societyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSocietyName(String value) {
        this.societyName = value;
    }

    /**
     * Gets the value of the societyNetworkPartners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the societyNetworkPartners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSocietyNetworkPartners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SocietyNetworkPartnerPojo }
     * 
     * 
     */
    public List<SocietyNetworkPartnerPojo> getSocietyNetworkPartners() {
        if (societyNetworkPartners == null) {
            societyNetworkPartners = new ArrayList<SocietyNetworkPartnerPojo>();
        }
        return this.societyNetworkPartners;
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
