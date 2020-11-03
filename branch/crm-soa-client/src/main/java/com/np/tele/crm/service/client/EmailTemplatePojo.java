
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
 * <p>Java class for emailTemplatePojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="emailTemplatePojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attachmentPaths" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailCcBccs" type="{http://service.alerts.crm.tele.np.com/}emailCcBccPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="emailFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailSubject" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailTemplate" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="emailTemplateId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="emailTemplateName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="projects" type="{http://service.alerts.crm.tele.np.com/}projectsPojo" minOccurs="0"/>
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
@XmlType(name = "emailTemplatePojo", propOrder = {
    "attachmentPaths",
    "createdBy",
    "createdTime",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "emailCcBccs",
    "emailFrom",
    "emailServer",
    "emailSubject",
    "emailTemplate",
    "emailTemplateId",
    "emailTemplateName",
    "lastModifiedBy",
    "lastModifiedTime",
    "projects",
    "status"
})
public class EmailTemplatePojo {

    @XmlElement(nillable = true)
    protected List<String> attachmentPaths;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    @XmlElement(nillable = true)
    protected List<EmailCcBccPojo> emailCcBccs;
    protected String emailFrom;
    protected String emailServer;
    protected String emailSubject;
    protected byte[] emailTemplate;
    protected long emailTemplateId;
    protected String emailTemplateName;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected ProjectsPojo projects;
    protected String status;

    /**
     * Gets the value of the attachmentPaths property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attachmentPaths property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttachmentPaths().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAttachmentPaths() {
        if (attachmentPaths == null) {
            attachmentPaths = new ArrayList<String>();
        }
        return this.attachmentPaths;
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
     * Gets the value of the displayLastModifiedTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayLastModifiedTime() {
        return displayLastModifiedTime;
    }

    /**
     * Sets the value of the displayLastModifiedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayLastModifiedTime(String value) {
        this.displayLastModifiedTime = value;
    }

    /**
     * Gets the value of the emailCcBccs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailCcBccs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailCcBccs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmailCcBccPojo }
     * 
     * 
     */
    public List<EmailCcBccPojo> getEmailCcBccs() {
        if (emailCcBccs == null) {
            emailCcBccs = new ArrayList<EmailCcBccPojo>();
        }
        return this.emailCcBccs;
    }

    /**
     * Gets the value of the emailFrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailFrom() {
        return emailFrom;
    }

    /**
     * Sets the value of the emailFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailFrom(String value) {
        this.emailFrom = value;
    }

    /**
     * Gets the value of the emailServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailServer() {
        return emailServer;
    }

    /**
     * Sets the value of the emailServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailServer(String value) {
        this.emailServer = value;
    }

    /**
     * Gets the value of the emailSubject property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailSubject() {
        return emailSubject;
    }

    /**
     * Sets the value of the emailSubject property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailSubject(String value) {
        this.emailSubject = value;
    }

    /**
     * Gets the value of the emailTemplate property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getEmailTemplate() {
        return emailTemplate;
    }

    /**
     * Sets the value of the emailTemplate property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setEmailTemplate(byte[] value) {
        this.emailTemplate = ((byte[]) value);
    }

    /**
     * Gets the value of the emailTemplateId property.
     * 
     */
    public long getEmailTemplateId() {
        return emailTemplateId;
    }

    /**
     * Sets the value of the emailTemplateId property.
     * 
     */
    public void setEmailTemplateId(long value) {
        this.emailTemplateId = value;
    }

    /**
     * Gets the value of the emailTemplateName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailTemplateName() {
        return emailTemplateName;
    }

    /**
     * Sets the value of the emailTemplateName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailTemplateName(String value) {
        this.emailTemplateName = value;
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
     * Gets the value of the projects property.
     * 
     * @return
     *     possible object is
     *     {@link ProjectsPojo }
     *     
     */
    public ProjectsPojo getProjects() {
        return projects;
    }

    /**
     * Sets the value of the projects property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectsPojo }
     *     
     */
    public void setProjects(ProjectsPojo value) {
        this.projects = value;
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
