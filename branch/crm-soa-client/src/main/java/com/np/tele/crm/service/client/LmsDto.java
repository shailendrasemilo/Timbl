
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lmsDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lmsDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appointmentPojo" type="{http://service.lms.crm.tele.np.com/}appointmentPojo" minOccurs="0"/>
 *         &lt;element name="appointmentPojos" type="{http://service.lms.crm.tele.np.com/}appointmentPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfMappingPojos" type="{http://service.lms.crm.tele.np.com/}lmsCrfMappingPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="leadPojo" type="{http://service.lms.crm.tele.np.com/}lmsPojo" minOccurs="0"/>
 *         &lt;element name="leadPojos" type="{http://service.lms.crm.tele.np.com/}lmsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lmsCrfMappingPojo" type="{http://service.lms.crm.tele.np.com/}lmsCrfMappingPojo" minOccurs="0"/>
 *         &lt;element name="rejectedPojos" type="{http://service.lms.crm.tele.np.com/}lmsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarksPojo" type="{http://service.lms.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *         &lt;element name="remarksPojos" type="{http://service.lms.crm.tele.np.com/}remarksPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successInsertPojos" type="{http://service.lms.crm.tele.np.com/}lmsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="toStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toUserId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lmsDto", propOrder = {
    "appointmentPojo",
    "appointmentPojos",
    "clientIPAddress",
    "crfMappingPojos",
    "leadPojo",
    "leadPojos",
    "lmsCrfMappingPojo",
    "rejectedPojos",
    "remarksPojo",
    "remarksPojos",
    "serverIPAddress",
    "statusCode",
    "statusDesc",
    "successInsertPojos",
    "toStage",
    "toUserId",
    "userAssociatedPartners",
    "userAssociatedServices"
})
public class LmsDto {

    protected AppointmentPojo appointmentPojo;
    @XmlElement(nillable = true)
    protected List<AppointmentPojo> appointmentPojos;
    protected String clientIPAddress;
    @XmlElement(nillable = true)
    protected List<LmsCrfMappingPojo> crfMappingPojos;
    protected LmsPojo leadPojo;
    @XmlElement(nillable = true)
    protected List<LmsPojo> leadPojos;
    protected LmsCrfMappingPojo lmsCrfMappingPojo;
    @XmlElement(nillable = true)
    protected List<LmsPojo> rejectedPojos;
    protected RemarksPojo remarksPojo;
    @XmlElement(nillable = true)
    protected List<RemarksPojo> remarksPojos;
    protected String serverIPAddress;
    protected String statusCode;
    protected String statusDesc;
    @XmlElement(nillable = true)
    protected List<LmsPojo> successInsertPojos;
    protected String toStage;
    protected String toUserId;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;

    /**
     * Gets the value of the appointmentPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentPojo }
     *     
     */
    public AppointmentPojo getAppointmentPojo() {
        return appointmentPojo;
    }

    /**
     * Sets the value of the appointmentPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentPojo }
     *     
     */
    public void setAppointmentPojo(AppointmentPojo value) {
        this.appointmentPojo = value;
    }

    /**
     * Gets the value of the appointmentPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the appointmentPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAppointmentPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AppointmentPojo }
     * 
     * 
     */
    public List<AppointmentPojo> getAppointmentPojos() {
        if (appointmentPojos == null) {
            appointmentPojos = new ArrayList<AppointmentPojo>();
        }
        return this.appointmentPojos;
    }

    /**
     * Gets the value of the clientIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIPAddress() {
        return clientIPAddress;
    }

    /**
     * Sets the value of the clientIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIPAddress(String value) {
        this.clientIPAddress = value;
    }

    /**
     * Gets the value of the crfMappingPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crfMappingPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrfMappingPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsCrfMappingPojo }
     * 
     * 
     */
    public List<LmsCrfMappingPojo> getCrfMappingPojos() {
        if (crfMappingPojos == null) {
            crfMappingPojos = new ArrayList<LmsCrfMappingPojo>();
        }
        return this.crfMappingPojos;
    }

    /**
     * Gets the value of the leadPojo property.
     * 
     * @return
     *     possible object is
     *     {@link LmsPojo }
     *     
     */
    public LmsPojo getLeadPojo() {
        return leadPojo;
    }

    /**
     * Sets the value of the leadPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LmsPojo }
     *     
     */
    public void setLeadPojo(LmsPojo value) {
        this.leadPojo = value;
    }

    /**
     * Gets the value of the leadPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the leadPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLeadPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsPojo }
     * 
     * 
     */
    public List<LmsPojo> getLeadPojos() {
        if (leadPojos == null) {
            leadPojos = new ArrayList<LmsPojo>();
        }
        return this.leadPojos;
    }

    /**
     * Gets the value of the lmsCrfMappingPojo property.
     * 
     * @return
     *     possible object is
     *     {@link LmsCrfMappingPojo }
     *     
     */
    public LmsCrfMappingPojo getLmsCrfMappingPojo() {
        return lmsCrfMappingPojo;
    }

    /**
     * Sets the value of the lmsCrfMappingPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LmsCrfMappingPojo }
     *     
     */
    public void setLmsCrfMappingPojo(LmsCrfMappingPojo value) {
        this.lmsCrfMappingPojo = value;
    }

    /**
     * Gets the value of the rejectedPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rejectedPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRejectedPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsPojo }
     * 
     * 
     */
    public List<LmsPojo> getRejectedPojos() {
        if (rejectedPojos == null) {
            rejectedPojos = new ArrayList<LmsPojo>();
        }
        return this.rejectedPojos;
    }

    /**
     * Gets the value of the remarksPojo property.
     * 
     * @return
     *     possible object is
     *     {@link RemarksPojo }
     *     
     */
    public RemarksPojo getRemarksPojo() {
        return remarksPojo;
    }

    /**
     * Sets the value of the remarksPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemarksPojo }
     *     
     */
    public void setRemarksPojo(RemarksPojo value) {
        this.remarksPojo = value;
    }

    /**
     * Gets the value of the remarksPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the remarksPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRemarksPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RemarksPojo }
     * 
     * 
     */
    public List<RemarksPojo> getRemarksPojos() {
        if (remarksPojos == null) {
            remarksPojos = new ArrayList<RemarksPojo>();
        }
        return this.remarksPojos;
    }

    /**
     * Gets the value of the serverIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerIPAddress() {
        return serverIPAddress;
    }

    /**
     * Sets the value of the serverIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerIPAddress(String value) {
        this.serverIPAddress = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Sets the value of the statusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

    /**
     * Gets the value of the successInsertPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the successInsertPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuccessInsertPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsPojo }
     * 
     * 
     */
    public List<LmsPojo> getSuccessInsertPojos() {
        if (successInsertPojos == null) {
            successInsertPojos = new ArrayList<LmsPojo>();
        }
        return this.successInsertPojos;
    }

    /**
     * Gets the value of the toStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToStage() {
        return toStage;
    }

    /**
     * Sets the value of the toStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToStage(String value) {
        this.toStage = value;
    }

    /**
     * Gets the value of the toUserId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToUserId() {
        return toUserId;
    }

    /**
     * Sets the value of the toUserId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToUserId(String value) {
        this.toUserId = value;
    }

    /**
     * Gets the value of the userAssociatedPartners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAssociatedPartners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAssociatedPartners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserAssociatedPartners() {
        if (userAssociatedPartners == null) {
            userAssociatedPartners = new ArrayList<String>();
        }
        return this.userAssociatedPartners;
    }

    /**
     * Gets the value of the userAssociatedServices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAssociatedServices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAssociatedServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserAssociatedServices() {
        if (userAssociatedServices == null) {
            userAssociatedServices = new ArrayList<String>();
        }
        return this.userAssociatedServices;
    }

}
