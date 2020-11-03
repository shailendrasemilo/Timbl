
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmCapDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCapDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="aadharNumberPojo" type="{http://service.cap.crm.tele.np.com/}crmCustAadharNumberPojo" minOccurs="0"/>
 *         &lt;element name="activityAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="additionalDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmAdditionalDetailsPojo" minOccurs="0"/>
 *         &lt;element name="additionalDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmAdditionalDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="addressDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmAddressDetailsPojo" minOccurs="0"/>
 *         &lt;element name="addressDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmAddressDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="appointmentPojo" type="{http://service.cap.crm.tele.np.com/}appointmentPojo" minOccurs="0"/>
 *         &lt;element name="appointmentPojos" type="{http://service.cap.crm.tele.np.com/}appointmentPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="billingErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changeRequest" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commonCustDetailsPojos" type="{http://service.cap.crm.tele.np.com/}commonCustDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crfID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmParamDataPojos" type="{http://service.cap.crm.tele.np.com/}crmParamDataPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmPartnerNetworkConfig" type="{http://service.cap.crm.tele.np.com/}crmPartnerNetworkConfigPojo" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmCustomerDetailsPojo" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRecordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="documentDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmDocumentDetailsPojo" minOccurs="0"/>
 *         &lt;element name="documentDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmDocumentDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="generatedTicketId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lmsPojo" type="{http://service.cap.crm.tele.np.com/}lmsPojo" minOccurs="0"/>
 *         &lt;element name="macFaulty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="nationalityDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmNationalityDetailsPojo" minOccurs="0"/>
 *         &lt;element name="nationalityDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmNationalityDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="networkConfigurationsList" type="{http://service.cap.crm.tele.np.com/}crmNetworkConfigurationsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="networkConfigurationsPojo" type="{http://service.cap.crm.tele.np.com/}crmNetworkConfigurationsPojo" minOccurs="0"/>
 *         &lt;element name="networkPartner" type="{http://service.cap.crm.tele.np.com/}partnerPojo" minOccurs="0"/>
 *         &lt;element name="orderDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmOrderDetailsPojo" minOccurs="0"/>
 *         &lt;element name="orderDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmOrderDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paymentDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmPaymentDetailsPojo" minOccurs="0"/>
 *         &lt;element name="paymentDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmPaymentDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="planDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmPlanDetailsPojo" minOccurs="0"/>
 *         &lt;element name="planDetailsPojosList" type="{http://service.cap.crm.tele.np.com/}crmPlanDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarksPojo" type="{http://service.cap.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *         &lt;element name="remarksPojos" type="{http://service.cap.crm.tele.np.com/}remarksPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "crmCapDto", propOrder = {
    "aadharNumberPojo",
    "activityAction",
    "additionalDetailsPojo",
    "additionalDetailsPojosList",
    "addressDetailsPojo",
    "addressDetailsPojosList",
    "appointmentPojo",
    "appointmentPojos",
    "billingErrorCode",
    "changeRequest",
    "clientIPAddress",
    "commonCustDetailsPojos",
    "crfID",
    "crmParamDataPojos",
    "crmPartnerNetworkConfig",
    "customerDetailsPojo",
    "customerDetailsPojosList",
    "customerId",
    "customerRecordId",
    "documentDetailsPojo",
    "documentDetailsPojosList",
    "generatedTicketId",
    "lmsPojo",
    "macFaulty",
    "nationalityDetailsPojo",
    "nationalityDetailsPojosList",
    "networkConfigurationsList",
    "networkConfigurationsPojo",
    "networkPartner",
    "orderDetailsPojo",
    "orderDetailsPojosList",
    "paymentDetailsPojo",
    "paymentDetailsPojosList",
    "planDetailsPojo",
    "planDetailsPojosList",
    "remarksPojo",
    "remarksPojos",
    "serverIPAddress",
    "statusCode",
    "statusDesc",
    "toStage",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userId"
})
public class CrmCapDto {

    protected CrmCustAadharNumberPojo aadharNumberPojo;
    protected String activityAction;
    protected CrmAdditionalDetailsPojo additionalDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmAdditionalDetailsPojo> additionalDetailsPojosList;
    protected CrmAddressDetailsPojo addressDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmAddressDetailsPojo> addressDetailsPojosList;
    protected AppointmentPojo appointmentPojo;
    @XmlElement(nillable = true)
    protected List<AppointmentPojo> appointmentPojos;
    protected String billingErrorCode;
    protected String changeRequest;
    protected String clientIPAddress;
    @XmlElement(nillable = true)
    protected List<CommonCustDetailsPojo> commonCustDetailsPojos;
    protected String crfID;
    @XmlElement(nillable = true)
    protected List<CrmParamDataPojo> crmParamDataPojos;
    protected CrmPartnerNetworkConfigPojo crmPartnerNetworkConfig;
    protected CrmCustomerDetailsPojo customerDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> customerDetailsPojosList;
    protected String customerId;
    protected long customerRecordId;
    protected CrmDocumentDetailsPojo documentDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmDocumentDetailsPojo> documentDetailsPojosList;
    protected String generatedTicketId;
    protected LmsPojo lmsPojo;
    protected boolean macFaulty;
    protected CrmNationalityDetailsPojo nationalityDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmNationalityDetailsPojo> nationalityDetailsPojosList;
    @XmlElement(nillable = true)
    protected List<CrmNetworkConfigurationsPojo> networkConfigurationsList;
    protected CrmNetworkConfigurationsPojo networkConfigurationsPojo;
    protected PartnerPojo networkPartner;
    protected CrmOrderDetailsPojo orderDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmOrderDetailsPojo> orderDetailsPojosList;
    protected CrmPaymentDetailsPojo paymentDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmPaymentDetailsPojo> paymentDetailsPojosList;
    protected CrmPlanDetailsPojo planDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmPlanDetailsPojo> planDetailsPojosList;
    protected RemarksPojo remarksPojo;
    @XmlElement(nillable = true)
    protected List<RemarksPojo> remarksPojos;
    protected String serverIPAddress;
    protected String statusCode;
    protected String statusDesc;
    protected String toStage;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userId;

    /**
     * Gets the value of the aadharNumberPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustAadharNumberPojo }
     *     
     */
    public CrmCustAadharNumberPojo getAadharNumberPojo() {
        return aadharNumberPojo;
    }

    /**
     * Sets the value of the aadharNumberPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustAadharNumberPojo }
     *     
     */
    public void setAadharNumberPojo(CrmCustAadharNumberPojo value) {
        this.aadharNumberPojo = value;
    }

    /**
     * Gets the value of the activityAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityAction() {
        return activityAction;
    }

    /**
     * Sets the value of the activityAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityAction(String value) {
        this.activityAction = value;
    }

    /**
     * Gets the value of the additionalDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmAdditionalDetailsPojo }
     *     
     */
    public CrmAdditionalDetailsPojo getAdditionalDetailsPojo() {
        return additionalDetailsPojo;
    }

    /**
     * Sets the value of the additionalDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmAdditionalDetailsPojo }
     *     
     */
    public void setAdditionalDetailsPojo(CrmAdditionalDetailsPojo value) {
        this.additionalDetailsPojo = value;
    }

    /**
     * Gets the value of the additionalDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmAdditionalDetailsPojo }
     * 
     * 
     */
    public List<CrmAdditionalDetailsPojo> getAdditionalDetailsPojosList() {
        if (additionalDetailsPojosList == null) {
            additionalDetailsPojosList = new ArrayList<CrmAdditionalDetailsPojo>();
        }
        return this.additionalDetailsPojosList;
    }

    /**
     * Gets the value of the addressDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmAddressDetailsPojo }
     *     
     */
    public CrmAddressDetailsPojo getAddressDetailsPojo() {
        return addressDetailsPojo;
    }

    /**
     * Sets the value of the addressDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmAddressDetailsPojo }
     *     
     */
    public void setAddressDetailsPojo(CrmAddressDetailsPojo value) {
        this.addressDetailsPojo = value;
    }

    /**
     * Gets the value of the addressDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmAddressDetailsPojo }
     * 
     * 
     */
    public List<CrmAddressDetailsPojo> getAddressDetailsPojosList() {
        if (addressDetailsPojosList == null) {
            addressDetailsPojosList = new ArrayList<CrmAddressDetailsPojo>();
        }
        return this.addressDetailsPojosList;
    }

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
     * Gets the value of the billingErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingErrorCode() {
        return billingErrorCode;
    }

    /**
     * Sets the value of the billingErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingErrorCode(String value) {
        this.billingErrorCode = value;
    }

    /**
     * Gets the value of the changeRequest property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChangeRequest() {
        return changeRequest;
    }

    /**
     * Sets the value of the changeRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChangeRequest(String value) {
        this.changeRequest = value;
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
     * Gets the value of the commonCustDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commonCustDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommonCustDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommonCustDetailsPojo }
     * 
     * 
     */
    public List<CommonCustDetailsPojo> getCommonCustDetailsPojos() {
        if (commonCustDetailsPojos == null) {
            commonCustDetailsPojos = new ArrayList<CommonCustDetailsPojo>();
        }
        return this.commonCustDetailsPojos;
    }

    /**
     * Gets the value of the crfID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrfID() {
        return crfID;
    }

    /**
     * Sets the value of the crfID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrfID(String value) {
        this.crfID = value;
    }

    /**
     * Gets the value of the crmParamDataPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmParamDataPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmParamDataPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmParamDataPojo }
     * 
     * 
     */
    public List<CrmParamDataPojo> getCrmParamDataPojos() {
        if (crmParamDataPojos == null) {
            crmParamDataPojos = new ArrayList<CrmParamDataPojo>();
        }
        return this.crmParamDataPojos;
    }

    /**
     * Gets the value of the crmPartnerNetworkConfig property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPartnerNetworkConfigPojo }
     *     
     */
    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfig() {
        return crmPartnerNetworkConfig;
    }

    /**
     * Sets the value of the crmPartnerNetworkConfig property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPartnerNetworkConfigPojo }
     *     
     */
    public void setCrmPartnerNetworkConfig(CrmPartnerNetworkConfigPojo value) {
        this.crmPartnerNetworkConfig = value;
    }

    /**
     * Gets the value of the customerDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public CrmCustomerDetailsPojo getCustomerDetailsPojo() {
        return customerDetailsPojo;
    }

    /**
     * Sets the value of the customerDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public void setCustomerDetailsPojo(CrmCustomerDetailsPojo value) {
        this.customerDetailsPojo = value;
    }

    /**
     * Gets the value of the customerDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojosList() {
        if (customerDetailsPojosList == null) {
            customerDetailsPojosList = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.customerDetailsPojosList;
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
     * Gets the value of the customerRecordId property.
     * 
     */
    public long getCustomerRecordId() {
        return customerRecordId;
    }

    /**
     * Sets the value of the customerRecordId property.
     * 
     */
    public void setCustomerRecordId(long value) {
        this.customerRecordId = value;
    }

    /**
     * Gets the value of the documentDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmDocumentDetailsPojo }
     *     
     */
    public CrmDocumentDetailsPojo getDocumentDetailsPojo() {
        return documentDetailsPojo;
    }

    /**
     * Sets the value of the documentDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmDocumentDetailsPojo }
     *     
     */
    public void setDocumentDetailsPojo(CrmDocumentDetailsPojo value) {
        this.documentDetailsPojo = value;
    }

    /**
     * Gets the value of the documentDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the documentDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocumentDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmDocumentDetailsPojo }
     * 
     * 
     */
    public List<CrmDocumentDetailsPojo> getDocumentDetailsPojosList() {
        if (documentDetailsPojosList == null) {
            documentDetailsPojosList = new ArrayList<CrmDocumentDetailsPojo>();
        }
        return this.documentDetailsPojosList;
    }

    /**
     * Gets the value of the generatedTicketId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGeneratedTicketId() {
        return generatedTicketId;
    }

    /**
     * Sets the value of the generatedTicketId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGeneratedTicketId(String value) {
        this.generatedTicketId = value;
    }

    /**
     * Gets the value of the lmsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link LmsPojo }
     *     
     */
    public LmsPojo getLmsPojo() {
        return lmsPojo;
    }

    /**
     * Sets the value of the lmsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LmsPojo }
     *     
     */
    public void setLmsPojo(LmsPojo value) {
        this.lmsPojo = value;
    }

    /**
     * Gets the value of the macFaulty property.
     * 
     */
    public boolean isMacFaulty() {
        return macFaulty;
    }

    /**
     * Sets the value of the macFaulty property.
     * 
     */
    public void setMacFaulty(boolean value) {
        this.macFaulty = value;
    }

    /**
     * Gets the value of the nationalityDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmNationalityDetailsPojo }
     *     
     */
    public CrmNationalityDetailsPojo getNationalityDetailsPojo() {
        return nationalityDetailsPojo;
    }

    /**
     * Sets the value of the nationalityDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmNationalityDetailsPojo }
     *     
     */
    public void setNationalityDetailsPojo(CrmNationalityDetailsPojo value) {
        this.nationalityDetailsPojo = value;
    }

    /**
     * Gets the value of the nationalityDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nationalityDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNationalityDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmNationalityDetailsPojo }
     * 
     * 
     */
    public List<CrmNationalityDetailsPojo> getNationalityDetailsPojosList() {
        if (nationalityDetailsPojosList == null) {
            nationalityDetailsPojosList = new ArrayList<CrmNationalityDetailsPojo>();
        }
        return this.nationalityDetailsPojosList;
    }

    /**
     * Gets the value of the networkConfigurationsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the networkConfigurationsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNetworkConfigurationsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmNetworkConfigurationsPojo }
     * 
     * 
     */
    public List<CrmNetworkConfigurationsPojo> getNetworkConfigurationsList() {
        if (networkConfigurationsList == null) {
            networkConfigurationsList = new ArrayList<CrmNetworkConfigurationsPojo>();
        }
        return this.networkConfigurationsList;
    }

    /**
     * Gets the value of the networkConfigurationsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmNetworkConfigurationsPojo }
     *     
     */
    public CrmNetworkConfigurationsPojo getNetworkConfigurationsPojo() {
        return networkConfigurationsPojo;
    }

    /**
     * Sets the value of the networkConfigurationsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmNetworkConfigurationsPojo }
     *     
     */
    public void setNetworkConfigurationsPojo(CrmNetworkConfigurationsPojo value) {
        this.networkConfigurationsPojo = value;
    }

    /**
     * Gets the value of the networkPartner property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerPojo }
     *     
     */
    public PartnerPojo getNetworkPartner() {
        return networkPartner;
    }

    /**
     * Sets the value of the networkPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerPojo }
     *     
     */
    public void setNetworkPartner(PartnerPojo value) {
        this.networkPartner = value;
    }

    /**
     * Gets the value of the orderDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmOrderDetailsPojo }
     *     
     */
    public CrmOrderDetailsPojo getOrderDetailsPojo() {
        return orderDetailsPojo;
    }

    /**
     * Sets the value of the orderDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmOrderDetailsPojo }
     *     
     */
    public void setOrderDetailsPojo(CrmOrderDetailsPojo value) {
        this.orderDetailsPojo = value;
    }

    /**
     * Gets the value of the orderDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmOrderDetailsPojo }
     * 
     * 
     */
    public List<CrmOrderDetailsPojo> getOrderDetailsPojosList() {
        if (orderDetailsPojosList == null) {
            orderDetailsPojosList = new ArrayList<CrmOrderDetailsPojo>();
        }
        return this.orderDetailsPojosList;
    }

    /**
     * Gets the value of the paymentDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPaymentDetailsPojo }
     *     
     */
    public CrmPaymentDetailsPojo getPaymentDetailsPojo() {
        return paymentDetailsPojo;
    }

    /**
     * Sets the value of the paymentDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPaymentDetailsPojo }
     *     
     */
    public void setPaymentDetailsPojo(CrmPaymentDetailsPojo value) {
        this.paymentDetailsPojo = value;
    }

    /**
     * Gets the value of the paymentDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPaymentDetailsPojo }
     * 
     * 
     */
    public List<CrmPaymentDetailsPojo> getPaymentDetailsPojosList() {
        if (paymentDetailsPojosList == null) {
            paymentDetailsPojosList = new ArrayList<CrmPaymentDetailsPojo>();
        }
        return this.paymentDetailsPojosList;
    }

    /**
     * Gets the value of the planDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPlanDetailsPojo }
     *     
     */
    public CrmPlanDetailsPojo getPlanDetailsPojo() {
        return planDetailsPojo;
    }

    /**
     * Sets the value of the planDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPlanDetailsPojo }
     *     
     */
    public void setPlanDetailsPojo(CrmPlanDetailsPojo value) {
        this.planDetailsPojo = value;
    }

    /**
     * Gets the value of the planDetailsPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the planDetailsPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlanDetailsPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPlanDetailsPojo }
     * 
     * 
     */
    public List<CrmPlanDetailsPojo> getPlanDetailsPojosList() {
        if (planDetailsPojosList == null) {
            planDetailsPojosList = new ArrayList<CrmPlanDetailsPojo>();
        }
        return this.planDetailsPojosList;
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
