
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for accessGroupPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="accessGroupPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="crmParameter" type="{http://service.usrmngmnt.crm.tele.np.com/}crmParameterPojo" minOccurs="0"/>
 *         &lt;element name="crmRoles" type="{http://service.usrmngmnt.crm.tele.np.com/}crmRolesPojo" minOccurs="0"/>
 *         &lt;element name="deleteAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="parameterValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="readAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recoverAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateAccess" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "accessGroupPojo", propOrder = {
    "createAccess",
    "crmParameter",
    "crmRoles",
    "deleteAccess",
    "editable",
    "parameterValue",
    "readAccess",
    "recordId",
    "recoverAccess",
    "status",
    "updateAccess"
})
public class AccessGroupPojo {

    protected boolean createAccess;
    protected CrmParameterPojo crmParameter;
    protected CrmRolesPojo crmRoles;
    protected boolean deleteAccess;
    protected boolean editable;
    protected String parameterValue;
    protected boolean readAccess;
    protected long recordId;
    protected boolean recoverAccess;
    protected String status;
    protected boolean updateAccess;

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

}
