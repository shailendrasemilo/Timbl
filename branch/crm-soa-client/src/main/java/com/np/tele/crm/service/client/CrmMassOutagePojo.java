
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
 * <p>Java class for crmMassOutagePojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutagePojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crmMassOutageMastersPojos" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageMastersPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmMassOutagePartners" type="{http://service.qrc.crm.tele.np.com/}crmMassOutagePartnerPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="customerList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="displayOutageEtrTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayOutageStartTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="outageActivity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageEtrTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="outageId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="outageLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="outageStartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="outageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolutionTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="sendSms" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="serviceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmMassOutagePojo", propOrder = {
    "createdBy",
    "createdTime",
    "crmMassOutageMastersPojos",
    "crmMassOutagePartners",
    "customerCount",
    "customerList",
    "displayOutageEtrTime",
    "displayOutageStartTime",
    "lastModifiedBy",
    "lastModifiedTime",
    "outageActivity",
    "outageEtrTime",
    "outageId",
    "outageLevel",
    "outageStartTime",
    "outageType",
    "reason",
    "remarks",
    "resolutionTime",
    "sendSms",
    "serviceName",
    "status",
    "statusCode",
    "statusDesc"
})
public class CrmMassOutagePojo {

    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    @XmlElement(nillable = true)
    protected List<CrmMassOutageMastersPojo> crmMassOutageMastersPojos;
    @XmlElement(nillable = true)
    protected List<CrmMassOutagePartnerPojo> crmMassOutagePartners;
    protected int customerCount;
    @XmlElement(nillable = true)
    protected List<String> customerList;
    protected String displayOutageEtrTime;
    protected String displayOutageStartTime;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String outageActivity;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar outageEtrTime;
    protected long outageId;
    protected String outageLevel;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar outageStartTime;
    protected String outageType;
    protected String reason;
    protected String remarks;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar resolutionTime;
    protected boolean sendSms;
    protected String serviceName;
    protected String status;
    protected String statusCode;
    protected String statusDesc;

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
     * Gets the value of the crmMassOutageMastersPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutageMastersPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutageMastersPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutageMastersPojo }
     * 
     * 
     */
    public List<CrmMassOutageMastersPojo> getCrmMassOutageMastersPojos() {
        if (crmMassOutageMastersPojos == null) {
            crmMassOutageMastersPojos = new ArrayList<CrmMassOutageMastersPojo>();
        }
        return this.crmMassOutageMastersPojos;
    }

    /**
     * Gets the value of the crmMassOutagePartners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutagePartners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutagePartners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutagePartnerPojo }
     * 
     * 
     */
    public List<CrmMassOutagePartnerPojo> getCrmMassOutagePartners() {
        if (crmMassOutagePartners == null) {
            crmMassOutagePartners = new ArrayList<CrmMassOutagePartnerPojo>();
        }
        return this.crmMassOutagePartners;
    }

    /**
     * Gets the value of the customerCount property.
     * 
     */
    public int getCustomerCount() {
        return customerCount;
    }

    /**
     * Sets the value of the customerCount property.
     * 
     */
    public void setCustomerCount(int value) {
        this.customerCount = value;
    }

    /**
     * Gets the value of the customerList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCustomerList() {
        if (customerList == null) {
            customerList = new ArrayList<String>();
        }
        return this.customerList;
    }

    /**
     * Gets the value of the displayOutageEtrTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayOutageEtrTime() {
        return displayOutageEtrTime;
    }

    /**
     * Sets the value of the displayOutageEtrTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayOutageEtrTime(String value) {
        this.displayOutageEtrTime = value;
    }

    /**
     * Gets the value of the displayOutageStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayOutageStartTime() {
        return displayOutageStartTime;
    }

    /**
     * Sets the value of the displayOutageStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayOutageStartTime(String value) {
        this.displayOutageStartTime = value;
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
     * Gets the value of the outageActivity property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutageActivity() {
        return outageActivity;
    }

    /**
     * Sets the value of the outageActivity property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutageActivity(String value) {
        this.outageActivity = value;
    }

    /**
     * Gets the value of the outageEtrTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOutageEtrTime() {
        return outageEtrTime;
    }

    /**
     * Sets the value of the outageEtrTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOutageEtrTime(XMLGregorianCalendar value) {
        this.outageEtrTime = value;
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
     * Gets the value of the outageLevel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutageLevel() {
        return outageLevel;
    }

    /**
     * Sets the value of the outageLevel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutageLevel(String value) {
        this.outageLevel = value;
    }

    /**
     * Gets the value of the outageStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOutageStartTime() {
        return outageStartTime;
    }

    /**
     * Sets the value of the outageStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOutageStartTime(XMLGregorianCalendar value) {
        this.outageStartTime = value;
    }

    /**
     * Gets the value of the outageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOutageType() {
        return outageType;
    }

    /**
     * Sets the value of the outageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOutageType(String value) {
        this.outageType = value;
    }

    /**
     * Gets the value of the reason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReason() {
        return reason;
    }

    /**
     * Sets the value of the reason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReason(String value) {
        this.reason = value;
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
     * Gets the value of the resolutionTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getResolutionTime() {
        return resolutionTime;
    }

    /**
     * Sets the value of the resolutionTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setResolutionTime(XMLGregorianCalendar value) {
        this.resolutionTime = value;
    }

    /**
     * Gets the value of the sendSms property.
     * 
     */
    public boolean isSendSms() {
        return sendSms;
    }

    /**
     * Sets the value of the sendSms property.
     * 
     */
    public void setSendSms(boolean value) {
        this.sendSms = value;
    }

    /**
     * Gets the value of the serviceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * Sets the value of the serviceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceName(String value) {
        this.serviceName = value;
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

}
