
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for userRolesPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userRolesPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crmParameter" type="{http://service.usrmngmnt.crm.tele.np.com/}crmParameterPojo" minOccurs="0"/>
 *         &lt;element name="crmRoles" type="{http://service.usrmngmnt.crm.tele.np.com/}crmRolesPojo" minOccurs="0"/>
 *         &lt;element name="deleteAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="groups" type="{http://service.usrmngmnt.crm.tele.np.com/}groupsPojo" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="parameterValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="readAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recoverAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userRolesPojo", propOrder = {
    "createAccess",
    "createdBy",
    "createdTime",
    "crmParameter",
    "crmRoles",
    "deleteAccess",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "editable",
    "groups",
    "lastModifiedBy",
    "lastModifiedTime",
    "parameterValue",
    "readAccess",
    "recordId",
    "recoverAccess",
    "status",
    "updateAccess",
    "userId"
})
public class UserRolesPojo {

    protected boolean createAccess;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected CrmParameterPojo crmParameter;
    protected CrmRolesPojo crmRoles;
    protected boolean deleteAccess;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    protected boolean editable;
    protected GroupsPojo groups;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String parameterValue;
    protected boolean readAccess;
    protected long recordId;
    protected boolean recoverAccess;
    protected String status;
    protected boolean updateAccess;
    protected String userId;

    /**
     * Gets the value of the createAccess property.
     * 
     */
    public boolean isCreateAccess() {
        return createAccess;
    }

    /**
     * Sets the value of the createAccess property.
     * 
     */
    public void setCreateAccess(boolean value) {
        this.createAccess = value;
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
     * Gets the value of the crmParameter property.
     * 
     * @return
     *     possible object is
     *     {@link CrmParameterPojo }
     *     
     */
    public CrmParameterPojo getCrmParameter() {
        return crmParameter;
    }

    /**
     * Sets the value of the crmParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmParameterPojo }
     *     
     */
    public void setCrmParameter(CrmParameterPojo value) {
        this.crmParameter = value;
    }

    /**
     * Gets the value of the crmRoles property.
     * 
     * @return
     *     possible object is
     *     {@link CrmRolesPojo }
     *     
     */
    public CrmRolesPojo getCrmRoles() {
        return crmRoles;
    }

    /**
     * Sets the value of the crmRoles property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmRolesPojo }
     *     
     */
    public void setCrmRoles(CrmRolesPojo value) {
        this.crmRoles = value;
    }

    /**
     * Gets the value of the deleteAccess property.
     * 
     */
    public boolean isDeleteAccess() {
        return deleteAccess;
    }

    /**
     * Sets the value of the deleteAccess property.
     * 
     */
    public void setDeleteAccess(boolean value) {
        this.deleteAccess = value;
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
     * Gets the value of the editable property.
     * 
     */
    public boolean isEditable() {
        return editable;
    }

    /**
     * Sets the value of the editable property.
     * 
     */
    public void setEditable(boolean value) {
        this.editable = value;
    }

    /**
     * Gets the value of the groups property.
     * 
     * @return
     *     possible object is
     *     {@link GroupsPojo }
     *     
     */
    public GroupsPojo getGroups() {
        return groups;
    }

    /**
     * Sets the value of the groups property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupsPojo }
     *     
     */
    public void setGroups(GroupsPojo value) {
        this.groups = value;
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
     * Gets the value of the parameterValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * Sets the value of the parameterValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParameterValue(String value) {
        this.parameterValue = value;
    }

    /**
     * Gets the value of the readAccess property.
     * 
     */
    public boolean isReadAccess() {
        return readAccess;
    }

    /**
     * Sets the value of the readAccess property.
     * 
     */
    public void setReadAccess(boolean value) {
        this.readAccess = value;
    }

    /**
     * Gets the value of the recordId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getRecordId() {
        return recordId;
    }

    /**
     * Sets the value of the recordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecordId(long value) {
        this.recordId = value;
    }

    /**
     * Gets the value of the recoverAccess property.
     * 
     */
    public boolean isRecoverAccess() {
        return recoverAccess;
    }

    /**
     * Sets the value of the recoverAccess property.
     * 
     */
    public void setRecoverAccess(boolean value) {
        this.recoverAccess = value;
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

    /**
     * Gets the value of the updateAccess property.
     * 
     */
    public boolean isUpdateAccess() {
        return updateAccess;
    }

    /**
     * Sets the value of the updateAccess property.
     * 
     */
    public void setUpdateAccess(boolean value) {
        this.updateAccess = value;
    }

    /**
     * Gets the value of the userId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the value of the userId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserId(String value) {
        this.userId = value;
    }

}
