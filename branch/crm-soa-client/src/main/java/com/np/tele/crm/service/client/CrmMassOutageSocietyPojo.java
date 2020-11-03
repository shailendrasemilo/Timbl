
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmMassOutageSocietyPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutageSocietyPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="outageActivity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageAreaId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="outageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="resolvedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolvedOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="society" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="societyId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "crmMassOutageSocietyPojo", propOrder = {
    "enabled",
    "outageActivity",
    "outageAreaId",
    "outageId",
    "recordId",
    "resolvedBy",
    "resolvedOn",
    "society",
    "societyId",
    "startPoint"
})
public class CrmMassOutageSocietyPojo {

    protected boolean enabled;
    protected String outageActivity;
    protected long outageAreaId;
    protected long outageId;
    protected long recordId;
    protected String resolvedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resolvedOn;
    protected String society;
    protected long societyId;
    protected boolean startPoint;

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
     * Gets the value of the outageActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutageActivity() {
        return outageActivity;
    }

    /**
     * Sets the value of the outageActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutageActivity(String value) {
        this.outageActivity = value;
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
