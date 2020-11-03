
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmTicketHistoryPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmTicketHistoryPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionTaken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attributedTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="fromBin" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="fromUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newFollowupOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="oldFollowupOn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rootCause" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ticketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toBin" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="toUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="visitRequired" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wrongTagging" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmTicketHistoryPojo", propOrder = {
    "action",
    "actionTaken",
    "attributedTo",
    "clientIp",
    "createdBy",
    "createdTime",
    "fromBin",
    "fromUser",
    "newFollowupOn",
    "oldFollowupOn",
    "recordId",
    "remarks",
    "rootCause",
    "serverIp",
    "serviceIp",
    "ticketId",
    "toBin",
    "toUser",
    "visitRequired",
    "wrongTagging"
})
public class CrmTicketHistoryPojo {

    protected String action;
    protected String actionTaken;
    protected String attributedTo;
    protected String clientIp;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected long fromBin;
    protected String fromUser;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar newFollowupOn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar oldFollowupOn;
    protected long recordId;
    protected String remarks;
    protected String rootCause;
    protected String serverIp;
    protected String serviceIp;
    protected String ticketId;
    protected long toBin;
    protected String toUser;
    protected String visitRequired;
    protected String wrongTagging;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
    }

    /**
     * Gets the value of the actionTaken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionTaken() {
        return actionTaken;
    }

    /**
     * Sets the value of the actionTaken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionTaken(String value) {
        this.actionTaken = value;
    }

    /**
     * Gets the value of the attributedTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttributedTo() {
        return attributedTo;
    }

    /**
     * Sets the value of the attributedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttributedTo(String value) {
        this.attributedTo = value;
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
     * Gets the value of the fromBin property.
     * 
     */
    public long getFromBin() {
        return fromBin;
    }

    /**
     * Sets the value of the fromBin property.
     * 
     */
    public void setFromBin(long value) {
        this.fromBin = value;
    }

    /**
     * Gets the value of the fromUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromUser() {
        return fromUser;
    }

    /**
     * Sets the value of the fromUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromUser(String value) {
        this.fromUser = value;
    }

    /**
     * Gets the value of the newFollowupOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getNewFollowupOn() {
        return newFollowupOn;
    }

    /**
     * Sets the value of the newFollowupOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setNewFollowupOn(XMLGregorianCalendar value) {
        this.newFollowupOn = value;
    }

    /**
     * Gets the value of the oldFollowupOn property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOldFollowupOn() {
        return oldFollowupOn;
    }

    /**
     * Sets the value of the oldFollowupOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOldFollowupOn(XMLGregorianCalendar value) {
        this.oldFollowupOn = value;
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
     * Gets the value of the rootCause property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRootCause() {
        return rootCause;
    }

    /**
     * Sets the value of the rootCause property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRootCause(String value) {
        this.rootCause = value;
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
     * Gets the value of the ticketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTicketId() {
        return ticketId;
    }

    /**
     * Sets the value of the ticketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTicketId(String value) {
        this.ticketId = value;
    }

    /**
     * Gets the value of the toBin property.
     * 
     */
    public long getToBin() {
        return toBin;
    }

    /**
     * Sets the value of the toBin property.
     * 
     */
    public void setToBin(long value) {
        this.toBin = value;
    }

    /**
     * Gets the value of the toUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToUser() {
        return toUser;
    }

    /**
     * Sets the value of the toUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToUser(String value) {
        this.toUser = value;
    }

    /**
     * Gets the value of the visitRequired property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitRequired() {
        return visitRequired;
    }

    /**
     * Sets the value of the visitRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitRequired(String value) {
        this.visitRequired = value;
    }

    /**
     * Gets the value of the wrongTagging property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWrongTagging() {
        return wrongTagging;
    }

    /**
     * Sets the value of the wrongTagging property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWrongTagging(String value) {
        this.wrongTagging = value;
    }

}
