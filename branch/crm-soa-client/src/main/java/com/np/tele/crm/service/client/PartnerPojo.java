
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
 * <p>Java class for partnerPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="partnerPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addedErp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="boardedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="bussinessType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commissionPercent" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crmPartnerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmPartnerDetailses" type="{http://service.cap.crm.tele.np.com/}crmPartnerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="currentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="designation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="emailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hoCpn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hoEmailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hoMobileNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="mobileNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="option82" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerAlias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerCen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerErpCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="partnerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerNetworkConfigPojos" type="{http://service.cap.crm.tele.np.com/}crmPartnerNetworkConfigPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partnerShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="pincode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="relatedDept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statePojos" type="{http://service.cap.crm.tele.np.com/}statePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="strBoardedDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "partnerPojo", propOrder = {
    "addedErp",
    "address",
    "boardedDate",
    "bussinessType",
    "city",
    "commissionPercent",
    "createdBy",
    "createdTime",
    "crmPartnerCode",
    "crmPartnerDetailses",
    "currentStatus",
    "designation",
    "displayCreatedTime",
    "displayLastModifiedTime",
    "editable",
    "emailId",
    "fax",
    "hoCpn",
    "hoEmailId",
    "hoMobileNo",
    "lastModifiedBy",
    "lastModifiedTime",
    "mobileNo",
    "option82",
    "partnerAlias",
    "partnerCen",
    "partnerErpCode",
    "partnerId",
    "partnerName",
    "partnerNetworkConfigPojos",
    "partnerShortName",
    "partnerType",
    "phoneNo",
    "pincode",
    "relatedDept",
    "state",
    "statePojos",
    "strBoardedDate"
})
public class PartnerPojo {

    protected String addedErp;
    protected String address;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar boardedDate;
    protected String bussinessType;
    protected String city;
    protected double commissionPercent;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String crmPartnerCode;
    @XmlElement(nillable = true)
    protected List<CrmPartnerDetailsPojo> crmPartnerDetailses;
    protected String currentStatus;
    protected String designation;
    protected String displayCreatedTime;
    protected String displayLastModifiedTime;
    protected boolean editable;
    protected String emailId;
    protected String fax;
    protected String hoCpn;
    protected String hoEmailId;
    protected long hoMobileNo;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected long mobileNo;
    protected String option82;
    protected String partnerAlias;
    protected String partnerCen;
    protected String partnerErpCode;
    protected long partnerId;
    protected String partnerName;
    @XmlElement(nillable = true)
    protected List<CrmPartnerNetworkConfigPojo> partnerNetworkConfigPojos;
    protected String partnerShortName;
    protected String partnerType;
    protected long phoneNo;
    protected int pincode;
    protected String relatedDept;
    protected String state;
    @XmlElement(nillable = true)
    protected List<StatePojo> statePojos;
    protected String strBoardedDate;

    /**
     * Gets the value of the addedErp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddedErp() {
        return addedErp;
    }

    /**
     * Sets the value of the addedErp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddedErp(String value) {
        this.addedErp = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Gets the value of the boardedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBoardedDate() {
        return boardedDate;
    }

    /**
     * Sets the value of the boardedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBoardedDate(XMLGregorianCalendar value) {
        this.boardedDate = value;
    }

    /**
     * Gets the value of the bussinessType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBussinessType() {
        return bussinessType;
    }

    /**
     * Sets the value of the bussinessType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBussinessType(String value) {
        this.bussinessType = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the commissionPercent property.
     * 
     */
    public double getCommissionPercent() {
        return commissionPercent;
    }

    /**
     * Sets the value of the commissionPercent property.
     * 
     */
    public void setCommissionPercent(double value) {
        this.commissionPercent = value;
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
     * Gets the value of the crmPartnerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmPartnerCode() {
        return crmPartnerCode;
    }

    /**
     * Sets the value of the crmPartnerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmPartnerCode(String value) {
        this.crmPartnerCode = value;
    }

    /**
     * Gets the value of the crmPartnerDetailses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPartnerDetailses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPartnerDetailses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPartnerDetailsPojo }
     * 
     * 
     */
    public List<CrmPartnerDetailsPojo> getCrmPartnerDetailses() {
        if (crmPartnerDetailses == null) {
            crmPartnerDetailses = new ArrayList<CrmPartnerDetailsPojo>();
        }
        return this.crmPartnerDetailses;
    }

    /**
     * Gets the value of the currentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentStatus() {
        return currentStatus;
    }

    /**
     * Sets the value of the currentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentStatus(String value) {
        this.currentStatus = value;
    }

    /**
     * Gets the value of the designation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * Sets the value of the designation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesignation(String value) {
        this.designation = value;
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
     * Gets the value of the emailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * Sets the value of the emailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailId(String value) {
        this.emailId = value;
    }

    /**
     * Gets the value of the fax property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFax() {
        return fax;
    }

    /**
     * Sets the value of the fax property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFax(String value) {
        this.fax = value;
    }

    /**
     * Gets the value of the hoCpn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoCpn() {
        return hoCpn;
    }

    /**
     * Sets the value of the hoCpn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoCpn(String value) {
        this.hoCpn = value;
    }

    /**
     * Gets the value of the hoEmailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoEmailId() {
        return hoEmailId;
    }

    /**
     * Sets the value of the hoEmailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoEmailId(String value) {
        this.hoEmailId = value;
    }

    /**
     * Gets the value of the hoMobileNo property.
     * 
     */
    public long getHoMobileNo() {
        return hoMobileNo;
    }

    /**
     * Sets the value of the hoMobileNo property.
     * 
     */
    public void setHoMobileNo(long value) {
        this.hoMobileNo = value;
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
     * Gets the value of the mobileNo property.
     * 
     */
    public long getMobileNo() {
        return mobileNo;
    }

    /**
     * Sets the value of the mobileNo property.
     * 
     */
    public void setMobileNo(long value) {
        this.mobileNo = value;
    }

    /**
     * Gets the value of the option82 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOption82() {
        return option82;
    }

    /**
     * Sets the value of the option82 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOption82(String value) {
        this.option82 = value;
    }

    /**
     * Gets the value of the partnerAlias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerAlias() {
        return partnerAlias;
    }

    /**
     * Sets the value of the partnerAlias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerAlias(String value) {
        this.partnerAlias = value;
    }

    /**
     * Gets the value of the partnerCen property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerCen() {
        return partnerCen;
    }

    /**
     * Sets the value of the partnerCen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerCen(String value) {
        this.partnerCen = value;
    }

    /**
     * Gets the value of the partnerErpCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerErpCode() {
        return partnerErpCode;
    }

    /**
     * Sets the value of the partnerErpCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerErpCode(String value) {
        this.partnerErpCode = value;
    }

    /**
     * Gets the value of the partnerId property.
     * 
     */
    public long getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     */
    public void setPartnerId(long value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the partnerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerName() {
        return partnerName;
    }

    /**
     * Sets the value of the partnerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerName(String value) {
        this.partnerName = value;
    }

    /**
     * Gets the value of the partnerNetworkConfigPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerNetworkConfigPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerNetworkConfigPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPartnerNetworkConfigPojo }
     * 
     * 
     */
    public List<CrmPartnerNetworkConfigPojo> getPartnerNetworkConfigPojos() {
        if (partnerNetworkConfigPojos == null) {
            partnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
        }
        return this.partnerNetworkConfigPojos;
    }

    /**
     * Gets the value of the partnerShortName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerShortName() {
        return partnerShortName;
    }

    /**
     * Sets the value of the partnerShortName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerShortName(String value) {
        this.partnerShortName = value;
    }

    /**
     * Gets the value of the partnerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerType() {
        return partnerType;
    }

    /**
     * Sets the value of the partnerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerType(String value) {
        this.partnerType = value;
    }

    /**
     * Gets the value of the phoneNo property.
     * 
     */
    public long getPhoneNo() {
        return phoneNo;
    }

    /**
     * Sets the value of the phoneNo property.
     * 
     */
    public void setPhoneNo(long value) {
        this.phoneNo = value;
    }

    /**
     * Gets the value of the pincode property.
     * 
     */
    public int getPincode() {
        return pincode;
    }

    /**
     * Sets the value of the pincode property.
     * 
     */
    public void setPincode(int value) {
        this.pincode = value;
    }

    /**
     * Gets the value of the relatedDept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedDept() {
        return relatedDept;
    }

    /**
     * Sets the value of the relatedDept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedDept(String value) {
        this.relatedDept = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the statePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatePojo }
     * 
     * 
     */
    public List<StatePojo> getStatePojos() {
        if (statePojos == null) {
            statePojos = new ArrayList<StatePojo>();
        }
        return this.statePojos;
    }

    /**
     * Gets the value of the strBoardedDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrBoardedDate() {
        return strBoardedDate;
    }

    /**
     * Sets the value of the strBoardedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrBoardedDate(String value) {
        this.strBoardedDate = value;
    }

}
