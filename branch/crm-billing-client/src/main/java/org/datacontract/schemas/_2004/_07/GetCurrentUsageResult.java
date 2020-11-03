
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for getCurrentUsageResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getCurrentUsageResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ActivationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="AllocatedTimeQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="AllocatedVolumeQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Bucket" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="RemainTimeQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="RemainVolumeQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsedTimeQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UsedVolumeQuota" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getCurrentUsageResult", propOrder = {
    "activationDate",
    "allocatedTimeQuota",
    "allocatedVolumeQuota",
    "bucket",
    "expiryDate",
    "remainTimeQuota",
    "remainVolumeQuota",
    "usedTimeQuota",
    "usedVolumeQuota"
})
public class GetCurrentUsageResult {

    @XmlElement(name = "ActivationDate")
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    @XmlElementRef(name = "AllocatedTimeQuota", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> allocatedTimeQuota;
    @XmlElementRef(name = "AllocatedVolumeQuota", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> allocatedVolumeQuota;
    @XmlElementRef(name = "Bucket", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> bucket;
    @XmlElementRef(name = "ExpiryDate", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<XMLGregorianCalendar> expiryDate;
    @XmlElementRef(name = "RemainTimeQuota", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> remainTimeQuota;
    @XmlElementRef(name = "RemainVolumeQuota", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> remainVolumeQuota;
    @XmlElementRef(name = "UsedTimeQuota", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> usedTimeQuota;
    @XmlElementRef(name = "UsedVolumeQuota", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> usedVolumeQuota;

    /**
     * Gets the value of the activationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getActivationDate() {
        return activationDate;
    }

    /**
     * Sets the value of the activationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setActivationDate(XMLGregorianCalendar value) {
        this.activationDate = value;
    }

    /**
     * Gets the value of the allocatedTimeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAllocatedTimeQuota() {
        return allocatedTimeQuota;
    }

    /**
     * Sets the value of the allocatedTimeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAllocatedTimeQuota(JAXBElement<String> value) {
        this.allocatedTimeQuota = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the allocatedVolumeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAllocatedVolumeQuota() {
        return allocatedVolumeQuota;
    }

    /**
     * Sets the value of the allocatedVolumeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAllocatedVolumeQuota(JAXBElement<String> value) {
        this.allocatedVolumeQuota = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the bucket property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBucket() {
        return bucket;
    }

    /**
     * Sets the value of the bucket property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBucket(JAXBElement<String> value) {
        this.bucket = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setExpiryDate(JAXBElement<XMLGregorianCalendar> value) {
        this.expiryDate = ((JAXBElement<XMLGregorianCalendar> ) value);
    }

    /**
     * Gets the value of the remainTimeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRemainTimeQuota() {
        return remainTimeQuota;
    }

    /**
     * Sets the value of the remainTimeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRemainTimeQuota(JAXBElement<String> value) {
        this.remainTimeQuota = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the remainVolumeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRemainVolumeQuota() {
        return remainVolumeQuota;
    }

    /**
     * Sets the value of the remainVolumeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRemainVolumeQuota(JAXBElement<String> value) {
        this.remainVolumeQuota = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the usedTimeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsedTimeQuota() {
        return usedTimeQuota;
    }

    /**
     * Sets the value of the usedTimeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsedTimeQuota(JAXBElement<String> value) {
        this.usedTimeQuota = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the usedVolumeQuota property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUsedVolumeQuota() {
        return usedVolumeQuota;
    }

    /**
     * Sets the value of the usedVolumeQuota property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUsedVolumeQuota(JAXBElement<String> value) {
        this.usedVolumeQuota = ((JAXBElement<String> ) value);
    }

}
