
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
 * <p>Java class for crmMassOutageLocalityPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutageLocalityPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crmMassOutageSocieties" type="{http://service.report.crm.tele.np.com/}crmMassOutageSocietyPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="locality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="outageAreaId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="outageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="resolvedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolvedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="startPoint" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmMassOutageLocalityPojo", propOrder = {
    "crmMassOutageSocieties",
    "enabled",
    "locality",
    "localityId",
    "outageAreaId",
    "outageId",
    "recordId",
    "resolvedBy",
    "resolvedOn",
    "startPoint"
})
public class CrmMassOutageLocalityPojo {

    @XmlElement(nillable = true)
    protected List<CrmMassOutageSocietyPojo> crmMassOutageSocieties;
    protected boolean enabled;
    protected String locality;
    protected long localityId;
    protected long outageAreaId;
    protected long outageId;
    protected long recordId;
    protected String resolvedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resolvedOn;
    protected boolean startPoint;

    /**
     * Gets the value of the crmMassOutageSocieties property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutageSocieties property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutageSocieties().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutageSocietyPojo }
     * 
     * 
     */
    public List<CrmMassOutageSocietyPojo> getCrmMassOutageSocieties() {
        if (crmMassOutageSocieties == null) {
            crmMassOutageSocieties = new ArrayList<CrmMassOutageSocietyPojo>();
        }
        return this.crmMassOutageSocieties;
    }

    /**
     * Gets the value of the enabled property.
     * 
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets the value of the enabled property.
     * 
     */
    public void setEnabled(boolean value) {
        this.enabled = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocality(String value) {
        this.locality = value;
    }

    /**
     * Gets the value of the localityId property.
     * 
     */
    public long getLocalityId() {
        return localityId;
    }

    /**
     * Sets the value of the localityId property.
     * 
     */
    public void setLocalityId(long value) {
        this.localityId = value;
    }

    /**
     * Gets the value of the outageAreaId property.
     * 
     */
    public long getOutageAreaId() {
        return outageAreaId;
    }

    /**
     * Sets the value of the outageAreaId property.
     * 
     */
    public void setOutageAreaId(long value) {
        this.outageAreaId = value;
    }

    /**
     * Gets the value of the outageId property.
     * 
     */
    public long getOutageId() {
        return outageId;
    }

    /**
     * Sets the value of the outageId property.
     * 
     */
    public void setOutageId(long value) {
        this.outageId = value;
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
     * Gets the value of the resolvedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolvedBy() {
        return resolvedBy;
    }

    /**
     * Sets the value of the resolvedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolvedBy(String value) {
        this.resolvedBy = value;
    }

    /**
     * Gets the value of the resolvedOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResolvedOn() {
        return resolvedOn;
    }

    /**
     * Sets the value of the resolvedOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResolvedOn(XMLGregorianCalendar value) {
        this.resolvedOn = value;
    }

    /**
     * Gets the value of the startPoint property.
     * 
     */
    public boolean isStartPoint() {
        return startPoint;
    }

    /**
     * Sets the value of the startPoint property.
     * 
     */
    public void setStartPoint(boolean value) {
        this.startPoint = value;
    }

}
