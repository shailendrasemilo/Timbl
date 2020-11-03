
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmMassOutageDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmMassOutageDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmMassOutageAreaPojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageAreaPojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutageCityPojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageCityPojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutageLocalityPojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageLocalityPojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutageMastersPojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageMastersPojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutagePartnerPojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutagePartnerPojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutagePojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutagePojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutageSocietyPojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageSocietyPojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutageSocietyPojos" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageSocietyPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmMassOutageStatePojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageStatePojo" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="massOutagePojos" type="{http://service.qrc.crm.tele.np.com/}crmMassOutagePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="masterList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="outageMastersPojos" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageMastersPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="societyList" type="{http://www.w3.org/2001/XMLSchema}long" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "crmMassOutageDto", propOrder = {
    "clientIPAddress",
    "crmMassOutageAreaPojo",
    "crmMassOutageCityPojo",
    "crmMassOutageLocalityPojo",
    "crmMassOutageMastersPojo",
    "crmMassOutagePartnerPojo",
    "crmMassOutagePojo",
    "crmMassOutageSocietyPojo",
    "crmMassOutageSocietyPojos",
    "crmMassOutageStatePojo",
    "customerId",
    "customerList",
    "massOutagePojos",
    "masterList",
    "outageMastersPojos",
    "serverIPAddress",
    "societyList",
    "statusCode",
    "statusDesc",
    "userId"
})
public class CrmMassOutageDto {

    protected String clientIPAddress;
    protected CrmMassOutageAreaPojo crmMassOutageAreaPojo;
    protected CrmMassOutageCityPojo crmMassOutageCityPojo;
    protected CrmMassOutageLocalityPojo crmMassOutageLocalityPojo;
    protected CrmMassOutageMastersPojo crmMassOutageMastersPojo;
    protected CrmMassOutagePartnerPojo crmMassOutagePartnerPojo;
    protected CrmMassOutagePojo crmMassOutagePojo;
    protected CrmMassOutageSocietyPojo crmMassOutageSocietyPojo;
    @XmlElement(nillable = true)
    protected List<CrmMassOutageSocietyPojo> crmMassOutageSocietyPojos;
    protected CrmMassOutageStatePojo crmMassOutageStatePojo;
    protected String customerId;
    @XmlElement(nillable = true)
    protected List<String> customerList;
    @XmlElement(nillable = true)
    protected List<CrmMassOutagePojo> massOutagePojos;
    @XmlElement(nillable = true)
    protected List<String> masterList;
    @XmlElement(nillable = true)
    protected List<CrmMassOutageMastersPojo> outageMastersPojos;
    protected String serverIPAddress;
    @XmlElement(nillable = true)
    protected List<Long> societyList;
    protected String statusCode;
    protected String statusDesc;
    protected String userId;

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
     * Gets the value of the crmMassOutageAreaPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageAreaPojo }
     *     
     */
    public CrmMassOutageAreaPojo getCrmMassOutageAreaPojo() {
        return crmMassOutageAreaPojo;
    }

    /**
     * Sets the value of the crmMassOutageAreaPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageAreaPojo }
     *     
     */
    public void setCrmMassOutageAreaPojo(CrmMassOutageAreaPojo value) {
        this.crmMassOutageAreaPojo = value;
    }

    /**
     * Gets the value of the crmMassOutageCityPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageCityPojo }
     *     
     */
    public CrmMassOutageCityPojo getCrmMassOutageCityPojo() {
        return crmMassOutageCityPojo;
    }

    /**
     * Sets the value of the crmMassOutageCityPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageCityPojo }
     *     
     */
    public void setCrmMassOutageCityPojo(CrmMassOutageCityPojo value) {
        this.crmMassOutageCityPojo = value;
    }

    /**
     * Gets the value of the crmMassOutageLocalityPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageLocalityPojo }
     *     
     */
    public CrmMassOutageLocalityPojo getCrmMassOutageLocalityPojo() {
        return crmMassOutageLocalityPojo;
    }

    /**
     * Sets the value of the crmMassOutageLocalityPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageLocalityPojo }
     *     
     */
    public void setCrmMassOutageLocalityPojo(CrmMassOutageLocalityPojo value) {
        this.crmMassOutageLocalityPojo = value;
    }

    /**
     * Gets the value of the crmMassOutageMastersPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageMastersPojo }
     *     
     */
    public CrmMassOutageMastersPojo getCrmMassOutageMastersPojo() {
        return crmMassOutageMastersPojo;
    }

    /**
     * Sets the value of the crmMassOutageMastersPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageMastersPojo }
     *     
     */
    public void setCrmMassOutageMastersPojo(CrmMassOutageMastersPojo value) {
        this.crmMassOutageMastersPojo = value;
    }

    /**
     * Gets the value of the crmMassOutagePartnerPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutagePartnerPojo }
     *     
     */
    public CrmMassOutagePartnerPojo getCrmMassOutagePartnerPojo() {
        return crmMassOutagePartnerPojo;
    }

    /**
     * Sets the value of the crmMassOutagePartnerPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutagePartnerPojo }
     *     
     */
    public void setCrmMassOutagePartnerPojo(CrmMassOutagePartnerPojo value) {
        this.crmMassOutagePartnerPojo = value;
    }

    /**
     * Gets the value of the crmMassOutagePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutagePojo }
     *     
     */
    public CrmMassOutagePojo getCrmMassOutagePojo() {
        return crmMassOutagePojo;
    }

    /**
     * Sets the value of the crmMassOutagePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutagePojo }
     *     
     */
    public void setCrmMassOutagePojo(CrmMassOutagePojo value) {
        this.crmMassOutagePojo = value;
    }

    /**
     * Gets the value of the crmMassOutageSocietyPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageSocietyPojo }
     *     
     */
    public CrmMassOutageSocietyPojo getCrmMassOutageSocietyPojo() {
        return crmMassOutageSocietyPojo;
    }

    /**
     * Sets the value of the crmMassOutageSocietyPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageSocietyPojo }
     *     
     */
    public void setCrmMassOutageSocietyPojo(CrmMassOutageSocietyPojo value) {
        this.crmMassOutageSocietyPojo = value;
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
     * Gets the value of the crmMassOutageStatePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageStatePojo }
     *     
     */
    public CrmMassOutageStatePojo getCrmMassOutageStatePojo() {
        return crmMassOutageStatePojo;
    }

    /**
     * Sets the value of the crmMassOutageStatePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageStatePojo }
     *     
     */
    public void setCrmMassOutageStatePojo(CrmMassOutageStatePojo value) {
        this.crmMassOutageStatePojo = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
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
     * Gets the value of the massOutagePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the massOutagePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMassOutagePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutagePojo }
     * 
     * 
     */
    public List<CrmMassOutagePojo> getMassOutagePojos() {
        if (massOutagePojos == null) {
            massOutagePojos = new ArrayList<CrmMassOutagePojo>();
        }
        return this.massOutagePojos;
    }

    /**
     * Gets the value of the masterList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the masterList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMasterList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMasterList() {
        if (masterList == null) {
            masterList = new ArrayList<String>();
        }
        return this.masterList;
    }

    /**
     * Gets the value of the outageMastersPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the outageMastersPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOutageMastersPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutageMastersPojo }
     * 
     * 
     */
    public List<CrmMassOutageMastersPojo> getOutageMastersPojos() {
        if (outageMastersPojos == null) {
            outageMastersPojos = new ArrayList<CrmMassOutageMastersPojo>();
        }
        return this.outageMastersPojos;
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
     * Gets the value of the societyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the societyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSocietyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    public List<Long> getSocietyList() {
        if (societyList == null) {
            societyList = new ArrayList<Long>();
        }
        return this.societyList;
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
