
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
 * <p>Java class for crmCustomerDetailsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCustomerDetailsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aadharNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="altEmailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authSignDesg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authSignFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authSignLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="barringDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="billDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessPartner" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="bussinessPartnerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connectionType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="crfId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfPreviousStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfReferenceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmAddressDetailses" type="{http://service.cap.crm.tele.np.com/}crmAddressDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmPaymentDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmPaymentDetailsPojo" minOccurs="0"/>
 *         &lt;element name="crmPlanDetailses" type="{http://service.cap.crm.tele.np.com/}crmPlanDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="currentUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custDob" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custEmailId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custGender" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custMname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custMobileNo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="custPanGirNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dateOnIsr" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="displayCreatedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayCrfDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayCustDob" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayLastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailValidationFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fhFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fhLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ftApprovalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="ftSubmittionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="inboxId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="installationStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isrReferenceNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="linkedCRF" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nationality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newNetworkPartner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="npId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="oldNetworkPartner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgCordFname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orgCordLname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="permanentDisconnectDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rmn" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rtn" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="safeCustodyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="salesRepresentative" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesUser" type="{http://service.cap.crm.tele.np.com/}crmUserPojo" minOccurs="0"/>
 *         &lt;element name="secondaryMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="spId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="tempDisconnectDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="tokenDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="unBarringDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="unRead" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCustomerDetailsPojo", propOrder = {
    "aadharNo",
    "activationDate",
    "altEmailId",
    "authSignDesg",
    "authSignFname",
    "authSignLname",
    "barringDate",
    "billDate",
    "brand",
    "businessPartner",
    "bussinessPartnerCode",
    "connectionType",
    "createdBy",
    "createdTime",
    "crfDate",
    "crfId",
    "crfPreviousStage",
    "crfReferenceNo",
    "crfStage",
    "crmAddressDetailses",
    "crmPaymentDetailsPojo",
    "crmPlanDetailses",
    "currentUser",
    "custDob",
    "custEmailId",
    "custFname",
    "custGender",
    "custLname",
    "custMname",
    "custMobileNo",
    "custPanGirNo",
    "custToken",
    "customerId",
    "dateOnIsr",
    "displayCreatedTime",
    "displayCrfDate",
    "displayCustDob",
    "displayLastModifiedTime",
    "emailValidationFlag",
    "fhFname",
    "fhLname",
    "ftApprovalDate",
    "ftSubmittionDate",
    "inboxId",
    "installationStatus",
    "isrReferenceNo",
    "lastModifiedBy",
    "lastModifiedTime",
    "linkedCRF",
    "nationality",
    "newNetworkPartner",
    "npId",
    "oldNetworkPartner",
    "orgCordFname",
    "orgCordLname",
    "permanentDisconnectDate",
    "product",
    "recordId",
    "rmn",
    "rtn",
    "safeCustodyDate",
    "salesRepresentative",
    "salesUser",
    "secondaryMacId",
    "serviceType",
    "spId",
    "status",
    "tempDisconnectDate",
    "tokenDate",
    "unBarringDate",
    "unRead"
})
public class CrmCustomerDetailsPojo {

    protected String aadharNo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar activationDate;
    protected String altEmailId;
    protected String authSignDesg;
    protected String authSignFname;
    protected String authSignLname;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar barringDate;
    protected String billDate;
    protected String brand;
    protected long businessPartner;
    protected String bussinessPartnerCode;
    protected String connectionType;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar crfDate;
    protected String crfId;
    protected String crfPreviousStage;
    protected String crfReferenceNo;
    protected String crfStage;
    @XmlElement(nillable = true)
    protected List<CrmAddressDetailsPojo> crmAddressDetailses;
    protected CrmPaymentDetailsPojo crmPaymentDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmPlanDetailsPojo> crmPlanDetailses;
    protected String currentUser;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDob;
    protected String custEmailId;
    protected String custFname;
    protected String custGender;
    protected String custLname;
    protected String custMname;
    protected long custMobileNo;
    protected String custPanGirNo;
    protected String custToken;
    protected String customerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dateOnIsr;
    protected String displayCreatedTime;
    protected String displayCrfDate;
    protected String displayCustDob;
    protected String displayLastModifiedTime;
    protected String emailValidationFlag;
    protected String fhFname;
    protected String fhLname;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftApprovalDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftSubmittionDate;
    protected long inboxId;
    protected String installationStatus;
    protected String isrReferenceNo;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String linkedCRF;
    protected String nationality;
    protected String newNetworkPartner;
    protected long npId;
    protected String oldNetworkPartner;
    protected String orgCordFname;
    protected String orgCordLname;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar permanentDisconnectDate;
    protected String product;
    protected long recordId;
    protected long rmn;
    protected long rtn;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar safeCustodyDate;
    protected String salesRepresentative;
    protected CrmUserPojo salesUser;
    protected String secondaryMacId;
    protected String serviceType;
    protected long spId;
    protected String status;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tempDisconnectDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tokenDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar unBarringDate;
    protected boolean unRead;

    /**
     * Gets the value of the aadharNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAadharNo() {
        return aadharNo;
    }

    /**
     * Sets the value of the aadharNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAadharNo(String value) {
        this.aadharNo = value;
    }

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
     * Gets the value of the altEmailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAltEmailId() {
        return altEmailId;
    }

    /**
     * Sets the value of the altEmailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAltEmailId(String value) {
        this.altEmailId = value;
    }

    /**
     * Gets the value of the authSignDesg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthSignDesg() {
        return authSignDesg;
    }

    /**
     * Sets the value of the authSignDesg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthSignDesg(String value) {
        this.authSignDesg = value;
    }

    /**
     * Gets the value of the authSignFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthSignFname() {
        return authSignFname;
    }

    /**
     * Sets the value of the authSignFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthSignFname(String value) {
        this.authSignFname = value;
    }

    /**
     * Gets the value of the authSignLname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthSignLname() {
        return authSignLname;
    }

    /**
     * Sets the value of the authSignLname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthSignLname(String value) {
        this.authSignLname = value;
    }

    /**
     * Gets the value of the barringDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBarringDate() {
        return barringDate;
    }

    /**
     * Sets the value of the barringDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBarringDate(XMLGregorianCalendar value) {
        this.barringDate = value;
    }

    /**
     * Gets the value of the billDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillDate() {
        return billDate;
    }

    /**
     * Sets the value of the billDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillDate(String value) {
        this.billDate = value;
    }

    /**
     * Gets the value of the brand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the value of the brand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrand(String value) {
        this.brand = value;
    }

    /**
     * Gets the value of the businessPartner property.
     * 
     */
    public long getBusinessPartner() {
        return businessPartner;
    }

    /**
     * Sets the value of the businessPartner property.
     * 
     */
    public void setBusinessPartner(long value) {
        this.businessPartner = value;
    }

    /**
     * Gets the value of the bussinessPartnerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBussinessPartnerCode() {
        return bussinessPartnerCode;
    }

    /**
     * Sets the value of the bussinessPartnerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBussinessPartnerCode(String value) {
        this.bussinessPartnerCode = value;
    }

    /**
     * Gets the value of the connectionType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConnectionType() {
        return connectionType;
    }

    /**
     * Sets the value of the connectionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConnectionType(String value) {
        this.connectionType = value;
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
     * Gets the value of the crfDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCrfDate() {
        return crfDate;
    }

    /**
     * Sets the value of the crfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCrfDate(XMLGregorianCalendar value) {
        this.crfDate = value;
    }

    /**
     * Gets the value of the crfId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfId() {
        return crfId;
    }

    /**
     * Sets the value of the crfId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfId(String value) {
        this.crfId = value;
    }

    /**
     * Gets the value of the crfPreviousStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfPreviousStage() {
        return crfPreviousStage;
    }

    /**
     * Sets the value of the crfPreviousStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfPreviousStage(String value) {
        this.crfPreviousStage = value;
    }

    /**
     * Gets the value of the crfReferenceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfReferenceNo() {
        return crfReferenceNo;
    }

    /**
     * Sets the value of the crfReferenceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfReferenceNo(String value) {
        this.crfReferenceNo = value;
    }

    /**
     * Gets the value of the crfStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfStage() {
        return crfStage;
    }

    /**
     * Sets the value of the crfStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfStage(String value) {
        this.crfStage = value;
    }

    /**
     * Gets the value of the crmAddressDetailses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmAddressDetailses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmAddressDetailses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmAddressDetailsPojo }
     * 
     * 
     */
    public List<CrmAddressDetailsPojo> getCrmAddressDetailses() {
        if (crmAddressDetailses == null) {
            crmAddressDetailses = new ArrayList<CrmAddressDetailsPojo>();
        }
        return this.crmAddressDetailses;
    }

    /**
     * Gets the value of the crmPaymentDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPaymentDetailsPojo }
     *     
     */
    public CrmPaymentDetailsPojo getCrmPaymentDetailsPojo() {
        return crmPaymentDetailsPojo;
    }

    /**
     * Sets the value of the crmPaymentDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPaymentDetailsPojo }
     *     
     */
    public void setCrmPaymentDetailsPojo(CrmPaymentDetailsPojo value) {
        this.crmPaymentDetailsPojo = value;
    }

    /**
     * Gets the value of the crmPlanDetailses property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPlanDetailses property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPlanDetailses().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPlanDetailsPojo }
     * 
     * 
     */
    public List<CrmPlanDetailsPojo> getCrmPlanDetailses() {
        if (crmPlanDetailses == null) {
            crmPlanDetailses = new ArrayList<CrmPlanDetailsPojo>();
        }
        return this.crmPlanDetailses;
    }

    /**
     * Gets the value of the currentUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the value of the currentUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentUser(String value) {
        this.currentUser = value;
    }

    /**
     * Gets the value of the custDob property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDob() {
        return custDob;
    }

    /**
     * Sets the value of the custDob property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDob(XMLGregorianCalendar value) {
        this.custDob = value;
    }

    /**
     * Gets the value of the custEmailId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustEmailId() {
        return custEmailId;
    }

    /**
     * Sets the value of the custEmailId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustEmailId(String value) {
        this.custEmailId = value;
    }

    /**
     * Gets the value of the custFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustFname() {
        return custFname;
    }

    /**
     * Sets the value of the custFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustFname(String value) {
        this.custFname = value;
    }

    /**
     * Gets the value of the custGender property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustGender() {
        return custGender;
    }

    /**
     * Sets the value of the custGender property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustGender(String value) {
        this.custGender = value;
    }

    /**
     * Gets the value of the custLname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLname() {
        return custLname;
    }

    /**
     * Sets the value of the custLname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLname(String value) {
        this.custLname = value;
    }

    /**
     * Gets the value of the custMname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustMname() {
        return custMname;
    }

    /**
     * Sets the value of the custMname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustMname(String value) {
        this.custMname = value;
    }

    /**
     * Gets the value of the custMobileNo property.
     * 
     */
    public long getCustMobileNo() {
        return custMobileNo;
    }

    /**
     * Sets the value of the custMobileNo property.
     * 
     */
    public void setCustMobileNo(long value) {
        this.custMobileNo = value;
    }

    /**
     * Gets the value of the custPanGirNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustPanGirNo() {
        return custPanGirNo;
    }

    /**
     * Sets the value of the custPanGirNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustPanGirNo(String value) {
        this.custPanGirNo = value;
    }

    /**
     * Gets the value of the custToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustToken() {
        return custToken;
    }

    /**
     * Sets the value of the custToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustToken(String value) {
        this.custToken = value;
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
     * Gets the value of the dateOnIsr property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDateOnIsr() {
        return dateOnIsr;
    }

    /**
     * Sets the value of the dateOnIsr property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDateOnIsr(XMLGregorianCalendar value) {
        this.dateOnIsr = value;
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
     * Gets the value of the displayCrfDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayCrfDate() {
        return displayCrfDate;
    }

    /**
     * Sets the value of the displayCrfDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayCrfDate(String value) {
        this.displayCrfDate = value;
    }

    /**
     * Gets the value of the displayCustDob property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayCustDob() {
        return displayCustDob;
    }

    /**
     * Sets the value of the displayCustDob property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayCustDob(String value) {
        this.displayCustDob = value;
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
     * Gets the value of the emailValidationFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailValidationFlag() {
        return emailValidationFlag;
    }

    /**
     * Sets the value of the emailValidationFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailValidationFlag(String value) {
        this.emailValidationFlag = value;
    }

    /**
     * Gets the value of the fhFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFhFname() {
        return fhFname;
    }

    /**
     * Sets the value of the fhFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFhFname(String value) {
        this.fhFname = value;
    }

    /**
     * Gets the value of the fhLname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFhLname() {
        return fhLname;
    }

    /**
     * Sets the value of the fhLname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFhLname(String value) {
        this.fhLname = value;
    }

    /**
     * Gets the value of the ftApprovalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtApprovalDate() {
        return ftApprovalDate;
    }

    /**
     * Sets the value of the ftApprovalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtApprovalDate(XMLGregorianCalendar value) {
        this.ftApprovalDate = value;
    }

    /**
     * Gets the value of the ftSubmittionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtSubmittionDate() {
        return ftSubmittionDate;
    }

    /**
     * Sets the value of the ftSubmittionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtSubmittionDate(XMLGregorianCalendar value) {
        this.ftSubmittionDate = value;
    }

    /**
     * Gets the value of the inboxId property.
     * 
     */
    public long getInboxId() {
        return inboxId;
    }

    /**
     * Sets the value of the inboxId property.
     * 
     */
    public void setInboxId(long value) {
        this.inboxId = value;
    }

    /**
     * Gets the value of the installationStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallationStatus() {
        return installationStatus;
    }

    /**
     * Sets the value of the installationStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallationStatus(String value) {
        this.installationStatus = value;
    }

    /**
     * Gets the value of the isrReferenceNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsrReferenceNo() {
        return isrReferenceNo;
    }

    /**
     * Sets the value of the isrReferenceNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsrReferenceNo(String value) {
        this.isrReferenceNo = value;
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
     * Gets the value of the linkedCRF property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLinkedCRF() {
        return linkedCRF;
    }

    /**
     * Sets the value of the linkedCRF property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLinkedCRF(String value) {
        this.linkedCRF = value;
    }

    /**
     * Gets the value of the nationality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * Sets the value of the nationality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationality(String value) {
        this.nationality = value;
    }

    /**
     * Gets the value of the newNetworkPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewNetworkPartner() {
        return newNetworkPartner;
    }

    /**
     * Sets the value of the newNetworkPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewNetworkPartner(String value) {
        this.newNetworkPartner = value;
    }

    /**
     * Gets the value of the npId property.
     * 
     */
    public long getNpId() {
        return npId;
    }

    /**
     * Sets the value of the npId property.
     * 
     */
    public void setNpId(long value) {
        this.npId = value;
    }

    /**
     * Gets the value of the oldNetworkPartner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldNetworkPartner() {
        return oldNetworkPartner;
    }

    /**
     * Sets the value of the oldNetworkPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldNetworkPartner(String value) {
        this.oldNetworkPartner = value;
    }

    /**
     * Gets the value of the orgCordFname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCordFname() {
        return orgCordFname;
    }

    /**
     * Sets the value of the orgCordFname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCordFname(String value) {
        this.orgCordFname = value;
    }

    /**
     * Gets the value of the orgCordLname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrgCordLname() {
        return orgCordLname;
    }

    /**
     * Sets the value of the orgCordLname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrgCordLname(String value) {
        this.orgCordLname = value;
    }

    /**
     * Gets the value of the permanentDisconnectDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPermanentDisconnectDate() {
        return permanentDisconnectDate;
    }

    /**
     * Sets the value of the permanentDisconnectDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPermanentDisconnectDate(XMLGregorianCalendar value) {
        this.permanentDisconnectDate = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
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
     * Gets the value of the rmn property.
     * 
     */
    public long getRmn() {
        return rmn;
    }

    /**
     * Sets the value of the rmn property.
     * 
     */
    public void setRmn(long value) {
        this.rmn = value;
    }

    /**
     * Gets the value of the rtn property.
     * 
     */
    public long getRtn() {
        return rtn;
    }

    /**
     * Sets the value of the rtn property.
     * 
     */
    public void setRtn(long value) {
        this.rtn = value;
    }

    /**
     * Gets the value of the safeCustodyDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSafeCustodyDate() {
        return safeCustodyDate;
    }

    /**
     * Sets the value of the safeCustodyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSafeCustodyDate(XMLGregorianCalendar value) {
        this.safeCustodyDate = value;
    }

    /**
     * Gets the value of the salesRepresentative property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesRepresentative() {
        return salesRepresentative;
    }

    /**
     * Sets the value of the salesRepresentative property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesRepresentative(String value) {
        this.salesRepresentative = value;
    }

    /**
     * Gets the value of the salesUser property.
     * 
     * @return
     *     possible object is
     *     {@link CrmUserPojo }
     *     
     */
    public CrmUserPojo getSalesUser() {
        return salesUser;
    }

    /**
     * Sets the value of the salesUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmUserPojo }
     *     
     */
    public void setSalesUser(CrmUserPojo value) {
        this.salesUser = value;
    }

    /**
     * Gets the value of the secondaryMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryMacId() {
        return secondaryMacId;
    }

    /**
     * Sets the value of the secondaryMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryMacId(String value) {
        this.secondaryMacId = value;
    }

    /**
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the spId property.
     * 
     */
    public long getSpId() {
        return spId;
    }

    /**
     * Sets the value of the spId property.
     * 
     */
    public void setSpId(long value) {
        this.spId = value;
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
     * Gets the value of the tempDisconnectDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTempDisconnectDate() {
        return tempDisconnectDate;
    }

    /**
     * Sets the value of the tempDisconnectDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTempDisconnectDate(XMLGregorianCalendar value) {
        this.tempDisconnectDate = value;
    }

    /**
     * Gets the value of the tokenDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTokenDate() {
        return tokenDate;
    }

    /**
     * Sets the value of the tokenDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTokenDate(XMLGregorianCalendar value) {
        this.tokenDate = value;
    }

    /**
     * Gets the value of the unBarringDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUnBarringDate() {
        return unBarringDate;
    }

    /**
     * Sets the value of the unBarringDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUnBarringDate(XMLGregorianCalendar value) {
        this.unBarringDate = value;
    }

    /**
     * Gets the value of the unRead property.
     * 
     */
    public boolean isUnRead() {
        return unRead;
    }

    /**
     * Sets the value of the unRead property.
     * 
     */
    public void setUnRead(boolean value) {
        this.unRead = value;
    }

}
