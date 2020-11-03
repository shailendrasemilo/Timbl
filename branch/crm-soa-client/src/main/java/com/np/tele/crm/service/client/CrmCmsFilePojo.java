
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmCmsFilePojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCmsFilePojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cmsFileId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cmsFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmsFileType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="failReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "crmCmsFilePojo", propOrder = {
    "cmsFileId",
    "cmsFileName",
    "cmsFileType",
    "createdBy",
    "createdTime",
    "failReason",
    "status"
})
public class CrmCmsFilePojo {

    protected long cmsFileId;
    protected String cmsFileName;
    protected String cmsFileType;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String failReason;
    protected String status;

    /**
     * Gets the value of the cmsFileId property.
     * 
     */
    public long getCmsFileId() {
        return cmsFileId;
    }

    /**
     * Sets the value of the cmsFileId property.
     * 
     */
    public void setCmsFileId(long value) {
        this.cmsFileId = value;
    }

    /**
     * Gets the value of the cmsFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmsFileName() {
        return cmsFileName;
    }

    /**
     * Sets the value of the cmsFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmsFileName(String value) {
        this.cmsFileName = value;
    }

    /**
     * Gets the value of the cmsFileType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCmsFileType() {
        return cmsFileType;
    }

    /**
     * Sets the value of the cmsFileType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCmsFileType(String value) {
        this.cmsFileType = value;
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
     * Gets the value of the failReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFailReason() {
        return failReason;
    }

    /**
     * Sets the value of the failReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFailReason(String value) {
        this.failReason = value;
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
