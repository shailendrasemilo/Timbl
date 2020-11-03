
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmNetworkConfigurationsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmNetworkConfigurationsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentCpeMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentSlaveMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRecordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="firstCpeMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstSlaveMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="macBind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oltNoteId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oltPort" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="oltSlot" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="oltSubPort" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *         &lt;element name="ontOnuPort" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ontRgMduDone" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="option82" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="radiusCustomerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="secondaryMACAddr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spOntRgMduDone" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="vlanId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmNetworkConfigurationsPojo", propOrder = {
    "createdBy",
    "createdTime",
    "currentCpeMacId",
    "currentSlaveMacId",
    "customerRecordId",
    "firstCpeMacId",
    "firstSlaveMacId",
    "lastModifiedBy",
    "lastModifiedTime",
    "macBind",
    "oltNoteId",
    "oltPort",
    "oltSlot",
    "oltSubPort",
    "ontOnuPort",
    "ontRgMduDone",
    "option82",
    "radiusCustomerId",
    "recordId",
    "secondaryMACAddr",
    "serviceModel",
    "serviceType",
    "spOntRgMduDone",
    "vlanId"
})
public class CrmNetworkConfigurationsPojo {

    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String currentCpeMacId;
    protected String currentSlaveMacId;
    protected long customerRecordId;
    protected String firstCpeMacId;
    protected String firstSlaveMacId;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String macBind;
    protected String oltNoteId;
    protected byte oltPort;
    protected byte oltSlot;
    protected byte oltSubPort;
    protected String ontOnuPort;
    protected boolean ontRgMduDone;
    protected String option82;
    protected String radiusCustomerId;
    protected long recordId;
    protected String secondaryMACAddr;
    protected String serviceModel;
    protected String serviceType;
    protected boolean spOntRgMduDone;
    protected long vlanId;

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
     * Gets the value of the currentCpeMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentCpeMacId() {
        return currentCpeMacId;
    }

    /**
     * Sets the value of the currentCpeMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentCpeMacId(String value) {
        this.currentCpeMacId = value;
    }

    /**
     * Gets the value of the currentSlaveMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentSlaveMacId() {
        return currentSlaveMacId;
    }

    /**
     * Sets the value of the currentSlaveMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentSlaveMacId(String value) {
        this.currentSlaveMacId = value;
    }

    /**
     * Gets the value of the customerRecordId property.
     * 
     */
    public long getCustomerRecordId() {
        return customerRecordId;
    }

    /**
     * Sets the value of the customerRecordId property.
     * 
     */
    public void setCustomerRecordId(long value) {
        this.customerRecordId = value;
    }

    /**
     * Gets the value of the firstCpeMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstCpeMacId() {
        return firstCpeMacId;
    }

    /**
     * Sets the value of the firstCpeMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstCpeMacId(String value) {
        this.firstCpeMacId = value;
    }

    /**
     * Gets the value of the firstSlaveMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstSlaveMacId() {
        return firstSlaveMacId;
    }

    /**
     * Sets the value of the firstSlaveMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstSlaveMacId(String value) {
        this.firstSlaveMacId = value;
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
     * Gets the value of the macBind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacBind() {
        return macBind;
    }

    /**
     * Sets the value of the macBind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacBind(String value) {
        this.macBind = value;
    }

    /**
     * Gets the value of the oltNoteId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOltNoteId() {
        return oltNoteId;
    }

    /**
     * Sets the value of the oltNoteId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOltNoteId(String value) {
        this.oltNoteId = value;
    }

    /**
     * Gets the value of the oltPort property.
     * 
     */
    public byte getOltPort() {
        return oltPort;
    }

    /**
     * Sets the value of the oltPort property.
     * 
     */
    public void setOltPort(byte value) {
        this.oltPort = value;
    }

    /**
     * Gets the value of the oltSlot property.
     * 
     */
    public byte getOltSlot() {
        return oltSlot;
    }

    /**
     * Sets the value of the oltSlot property.
     * 
     */
    public void setOltSlot(byte value) {
        this.oltSlot = value;
    }

    /**
     * Gets the value of the oltSubPort property.
     * 
     */
    public byte getOltSubPort() {
        return oltSubPort;
    }

    /**
     * Sets the value of the oltSubPort property.
     * 
     */
    public void setOltSubPort(byte value) {
        this.oltSubPort = value;
    }

    /**
     * Gets the value of the ontOnuPort property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOntOnuPort() {
        return ontOnuPort;
    }

    /**
     * Sets the value of the ontOnuPort property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOntOnuPort(String value) {
        this.ontOnuPort = value;
    }

    /**
     * Gets the value of the ontRgMduDone property.
     * 
     */
    public boolean isOntRgMduDone() {
        return ontRgMduDone;
    }

    /**
     * Sets the value of the ontRgMduDone property.
     * 
     */
    public void setOntRgMduDone(boolean value) {
        this.ontRgMduDone = value;
    }

    /**
     * Gets the value of the option82 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOption82() {
        return option82;
    }

    /**
     * Sets the value of the option82 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOption82(String value) {
        this.option82 = value;
    }

    /**
     * Gets the value of the radiusCustomerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRadiusCustomerId() {
        return radiusCustomerId;
    }

    /**
     * Sets the value of the radiusCustomerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRadiusCustomerId(String value) {
        this.radiusCustomerId = value;
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
     * Gets the value of the secondaryMACAddr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryMACAddr() {
        return secondaryMACAddr;
    }

    /**
     * Sets the value of the secondaryMACAddr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryMACAddr(String value) {
        this.secondaryMACAddr = value;
    }

    /**
     * Gets the value of the serviceModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceModel() {
        return serviceModel;
    }

    /**
     * Sets the value of the serviceModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceModel(String value) {
        this.serviceModel = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the spOntRgMduDone property.
     * 
     */
    public boolean isSpOntRgMduDone() {
        return spOntRgMduDone;
    }

    /**
     * Sets the value of the spOntRgMduDone property.
     * 
     */
    public void setSpOntRgMduDone(boolean value) {
        this.spOntRgMduDone = value;
    }

    /**
     * Gets the value of the vlanId property.
     * 
     */
    public long getVlanId() {
        return vlanId;
    }

    /**
     * Sets the value of the vlanId property.
     * 
     */
    public void setVlanId(long value) {
        this.vlanId = value;
    }

}
