
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
 * <p>Java class for crmMassOutagePartnerPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutagePartnerPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="crmMassOutageMasterses" type="{http://service.report.crm.tele.np.com/}crmMassOutageMastersPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmMassOutageStates" type="{http://service.report.crm.tele.np.com/}crmMassOutageStatePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="outageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="partnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "crmMassOutagePartnerPojo", propOrder = {
    "crmMassOutageMasterses",
    "crmMassOutageStates",
    "enabled",
    "outageId",
    "partnerId",
    "partnerName",
    "recordId",
    "resolvedBy",
    "resolvedOn",
    "startPoint"
})
public class CrmMassOutagePartnerPojo {

    @XmlElement(nillable = true)
    protected List<CrmMassOutageMastersPojo> crmMassOutageMasterses;
    @XmlElement(nillable = true)
    protected List<CrmMassOutageStatePojo> crmMassOutageStates;
    protected boolean enabled;
    protected long outageId;
    protected long partnerId;
    protected String partnerName;
    protected long recordId;
    protected String resolvedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resolvedOn;
    protected boolean startPoint;

    /**
     * Gets the value of the crmMassOutageMasterses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutageMasterses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutageMasterses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutageMastersPojo }
     * 
     * 
     */
    public List<CrmMassOutageMastersPojo> getCrmMassOutageMasterses() {
        if (crmMassOutageMasterses == null) {
            crmMassOutageMasterses = new ArrayList<CrmMassOutageMastersPojo>();
        }
        return this.crmMassOutageMasterses;
    }

    /**
     * Gets the value of the crmMassOutageStates property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutageStates property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutageStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutageStatePojo }
     * 
     * 
     */
    public List<CrmMassOutageStatePojo> getCrmMassOutageStates() {
        if (crmMassOutageStates == null) {
            crmMassOutageStates = new ArrayList<CrmMassOutageStatePojo>();
        }
        return this.crmMassOutageStates;
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
     * Gets the value of the partnerId property.
     * 
     */
    public long getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     */
    public void setPartnerId(long value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the partnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * Sets the value of the partnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerName(String value) {
        this.partnerName = value;
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
