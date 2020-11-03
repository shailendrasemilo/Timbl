
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmMassOutageMastersPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutageMastersPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="masterId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="masterName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nasPortId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageActivity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="outageNpId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="poolName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "crmMassOutageMastersPojo", propOrder = {
    "enabled",
    "masterId",
    "masterName",
    "nasPortId",
    "outageActivity",
    "outageId",
    "outageNpId",
    "poolName",
    "recordId",
    "resolvedBy",
    "resolvedOn",
    "startPoint"
})
public class CrmMassOutageMastersPojo {

    protected boolean enabled;
    protected long masterId;
    protected String masterName;
    protected String nasPortId;
    protected String outageActivity;
    protected long outageId;
    protected long outageNpId;
    protected String poolName;
    protected long recordId;
    protected String resolvedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resolvedOn;
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
     * Gets the value of the masterId property.
     * 
     */
    public long getMasterId() {
        return masterId;
    }

    /**
     * Sets the value of the masterId property.
     * 
     */
    public void setMasterId(long value) {
        this.masterId = value;
    }

    /**
     * Gets the value of the masterName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterName() {
        return masterName;
    }

    /**
     * Sets the value of the masterName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterName(String value) {
        this.masterName = value;
    }

    /**
     * Gets the value of the nasPortId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNasPortId() {
        return nasPortId;
    }

    /**
     * Sets the value of the nasPortId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNasPortId(String value) {
        this.nasPortId = value;
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
     * Gets the value of the outageNpId property.
     * 
     */
    public long getOutageNpId() {
        return outageNpId;
    }

    /**
     * Sets the value of the outageNpId property.
     * 
     */
    public void setOutageNpId(long value) {
        this.outageNpId = value;
    }

    /**
     * Gets the value of the poolName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoolName() {
        return poolName;
    }

    /**
     * Sets the value of the poolName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoolName(String value) {
        this.poolName = value;
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
