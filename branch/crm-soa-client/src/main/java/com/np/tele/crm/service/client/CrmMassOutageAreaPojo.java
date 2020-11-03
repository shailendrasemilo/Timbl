
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
 * <p>Java class for crmMassOutageAreaPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutageAreaPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="crmMassOutageSocietyPojos" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageSocietyPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="outageCityId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "crmMassOutageAreaPojo", propOrder = {
    "area",
    "areaId",
    "crmMassOutageSocietyPojos",
    "enabled",
    "outageCityId",
    "outageId",
    "recordId",
    "resolvedBy",
    "resolvedOn",
    "startPoint"
})
public class CrmMassOutageAreaPojo {

    protected String area;
    protected long areaId;
    @XmlElement(nillable = true)
    protected List<CrmMassOutageSocietyPojo> crmMassOutageSocietyPojos;
    protected boolean enabled;
    protected long outageCityId;
    protected long outageId;
    protected long recordId;
    protected String resolvedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resolvedOn;
    protected boolean startPoint;

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
     * Gets the value of the crmMassOutageSocietyPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutageSocietyPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutageSocietyPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutageSocietyPojo }
     * 
     * 
     */
    public List<CrmMassOutageSocietyPojo> getCrmMassOutageSocietyPojos() {
        if (crmMassOutageSocietyPojos == null) {
            crmMassOutageSocietyPojos = new ArrayList<CrmMassOutageSocietyPojo>();
        }
        return this.crmMassOutageSocietyPojos;
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
     * Gets the value of the outageCityId property.
     * 
     */
    public long getOutageCityId() {
        return outageCityId;
    }

    /**
     * Sets the value of the outageCityId property.
     * 
     */
    public void setOutageCityId(long value) {
        this.outageCityId = value;
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
