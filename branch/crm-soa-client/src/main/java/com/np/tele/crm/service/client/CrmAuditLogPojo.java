
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmAuditLogPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmAuditLogPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="auditLogId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="events" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fromBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mappingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="module" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldValues" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmAuditLogPojo", propOrder = {
    "auditLogId",
    "clientIp",
    "createdBy",
    "createdTime",
    "displayCreatedTime",
    "events",
    "fromBin",
    "mappingId",
    "module",
    "newValues",
    "oldValues",
    "remarks",
    "serverIp",
    "serviceIp",
    "toBin"
})
public class CrmAuditLogPojo {

    protected long auditLogId;
    protected String clientIp;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String displayCreatedTime;
    protected String events;
    protected String fromBin;
    protected String mappingId;
    protected String module;
    protected String newValues;
    protected String oldValues;
    protected String remarks;
    protected String serverIp;
    protected String serviceIp;
    protected String toBin;

    /**
     * Gets the value of the auditLogId property.
     * 
     */
    public long getAuditLogId() {
        return auditLogId;
    }

    /**
     * Sets the value of the auditLogId property.
     * 
     */
    public void setAuditLogId(long value) {
        this.auditLogId = value;
    }

    /**
     * Gets the value of the clientIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIp() {
        return clientIp;
    }

    /**
     * Sets the value of the clientIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIp(String value) {
        this.clientIp = value;
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
     * Gets the value of the events property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEvents() {
        return events;
    }

    /**
     * Sets the value of the events property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEvents(String value) {
        this.events = value;
    }

    /**
     * Gets the value of the fromBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromBin() {
        return fromBin;
    }

    /**
     * Sets the value of the fromBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromBin(String value) {
        this.fromBin = value;
    }

    /**
     * Gets the value of the mappingId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMappingId() {
        return mappingId;
    }

    /**
     * Sets the value of the mappingId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMappingId(String value) {
        this.mappingId = value;
    }

    /**
     * Gets the value of the module property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModule() {
        return module;
    }

    /**
     * Sets the value of the module property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModule(String value) {
        this.module = value;
    }

    /**
     * Gets the value of the newValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewValues() {
        return newValues;
    }

    /**
     * Sets the value of the newValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewValues(String value) {
        this.newValues = value;
    }

    /**
     * Gets the value of the oldValues property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldValues() {
        return oldValues;
    }

    /**
     * Sets the value of the oldValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldValues(String value) {
        this.oldValues = value;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemarks(String value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the serverIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerIp() {
        return serverIp;
    }

    /**
     * Sets the value of the serverIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerIp(String value) {
        this.serverIp = value;
    }

    /**
     * Gets the value of the serviceIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceIp() {
        return serviceIp;
    }

    /**
     * Sets the value of the serviceIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceIp(String value) {
        this.serviceIp = value;
    }

    /**
     * Gets the value of the toBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToBin() {
        return toBin;
    }

    /**
     * Sets the value of the toBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToBin(String value) {
        this.toBin = value;
    }

}
