
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmSlaLogPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmSlaLogPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alertType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auditLogId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="crmModule" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mappingId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaActualMillis" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="slaDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="slaFailReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaFromStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="slaRecipients" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaToStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaUnit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaValueMillis" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmSlaLogPojo", propOrder = {
    "alertType",
    "auditLogId",
    "crmModule",
    "mappingId",
    "slaActualMillis",
    "slaDateTime",
    "slaFailReason",
    "slaFromStage",
    "slaId",
    "slaRecipients",
    "slaStatus",
    "slaToStage",
    "slaUnit",
    "slaValueMillis"
})
public class CrmSlaLogPojo {

    protected String alertType;
    protected long auditLogId;
    protected String crmModule;
    protected String mappingId;
    protected long slaActualMillis;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar slaDateTime;
    protected String slaFailReason;
    protected String slaFromStage;
    protected long slaId;
    protected String slaRecipients;
    protected String slaStatus;
    protected String slaToStage;
    protected String slaUnit;
    protected long slaValueMillis;

    /**
     * Gets the value of the alertType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * Sets the value of the alertType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlertType(String value) {
        this.alertType = value;
    }

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
     * Gets the value of the crmModule property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmModule() {
        return crmModule;
    }

    /**
     * Sets the value of the crmModule property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmModule(String value) {
        this.crmModule = value;
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
     * Gets the value of the slaActualMillis property.
     * 
     */
    public long getSlaActualMillis() {
        return slaActualMillis;
    }

    /**
     * Sets the value of the slaActualMillis property.
     * 
     */
    public void setSlaActualMillis(long value) {
        this.slaActualMillis = value;
    }

    /**
     * Gets the value of the slaDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSlaDateTime() {
        return slaDateTime;
    }

    /**
     * Sets the value of the slaDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSlaDateTime(XMLGregorianCalendar value) {
        this.slaDateTime = value;
    }

    /**
     * Gets the value of the slaFailReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaFailReason() {
        return slaFailReason;
    }

    /**
     * Sets the value of the slaFailReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaFailReason(String value) {
        this.slaFailReason = value;
    }

    /**
     * Gets the value of the slaFromStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaFromStage() {
        return slaFromStage;
    }

    /**
     * Sets the value of the slaFromStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaFromStage(String value) {
        this.slaFromStage = value;
    }

    /**
     * Gets the value of the slaId property.
     * 
     */
    public long getSlaId() {
        return slaId;
    }

    /**
     * Sets the value of the slaId property.
     * 
     */
    public void setSlaId(long value) {
        this.slaId = value;
    }

    /**
     * Gets the value of the slaRecipients property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaRecipients() {
        return slaRecipients;
    }

    /**
     * Sets the value of the slaRecipients property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaRecipients(String value) {
        this.slaRecipients = value;
    }

    /**
     * Gets the value of the slaStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaStatus() {
        return slaStatus;
    }

    /**
     * Sets the value of the slaStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaStatus(String value) {
        this.slaStatus = value;
    }

    /**
     * Gets the value of the slaToStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaToStage() {
        return slaToStage;
    }

    /**
     * Sets the value of the slaToStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaToStage(String value) {
        this.slaToStage = value;
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
     * Gets the value of the slaValueMillis property.
     * 
     */
    public long getSlaValueMillis() {
        return slaValueMillis;
    }

    /**
     * Sets the value of the slaValueMillis property.
     * 
     */
    public void setSlaValueMillis(long value) {
        this.slaValueMillis = value;
    }

}
