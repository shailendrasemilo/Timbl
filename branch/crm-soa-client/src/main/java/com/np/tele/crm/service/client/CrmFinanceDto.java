
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmFinanceDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmFinanceDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cmsFile" type="{http://service.finance.crm.tele.np.com/}crmCmsFilePojo" minOccurs="0"/>
 *         &lt;element name="cmsFiles" type="{http://service.finance.crm.tele.np.com/}crmCmsFilePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cmsPayment" type="{http://service.finance.crm.tele.np.com/}crmCmsPaymentPojo" minOccurs="0"/>
 *         &lt;element name="cmsPayments" type="{http://service.finance.crm.tele.np.com/}crmCmsPaymentPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crfIDs" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crfMappingPojo" type="{http://service.finance.crm.tele.np.com/}crmUpCrfMappingPojo" minOccurs="0"/>
 *         &lt;element name="crfMappingPojos" type="{http://service.finance.crm.tele.np.com/}crmUpCrfMappingPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCustomerDetailsPojos" type="{http://service.finance.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCustomerRefundsPojo" type="{http://service.finance.crm.tele.np.com/}crmCustomerRefundsPojo" minOccurs="0"/>
 *         &lt;element name="crmPgwTransactionsPojo" type="{http://service.finance.crm.tele.np.com/}crmPgwTransactionsPojo" minOccurs="0"/>
 *         &lt;element name="crmPodDetailsPojo" type="{http://service.finance.crm.tele.np.com/}crmPodDetailsPojo" minOccurs="0"/>
 *         &lt;element name="crmPodDetailsPojos" type="{http://service.finance.crm.tele.np.com/}crmPodDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmUpfrontPaymentPojo" type="{http://service.finance.crm.tele.np.com/}crmUpfrontPaymentPojo" minOccurs="0"/>
 *         &lt;element name="crmUpfrontPaymentPojos" type="{http://service.finance.crm.tele.np.com/}crmUpfrontPaymentPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojo" type="{http://service.finance.crm.tele.np.com/}crmCustomerDetailsPojo" minOccurs="0"/>
 *         &lt;element name="dbSuccessCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentDetailsPojo" type="{http://service.finance.crm.tele.np.com/}crmPaymentDetailsPojo" minOccurs="0"/>
 *         &lt;element name="paymentDetailsPojos" type="{http://service.finance.crm.tele.np.com/}crmPaymentDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="refundDetailsPojos" type="{http://service.finance.crm.tele.np.com/}crmCustomerRefundsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://service.finance.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *         &lt;element name="reversalWOCrf" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCodes" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "crmFinanceDto", propOrder = {
    "billingErrorCode",
    "clientIPAddress",
    "cmsFile",
    "cmsFiles",
    "cmsPayment",
    "cmsPayments",
    "crfIDs",
    "crfMappingPojo",
    "crfMappingPojos",
    "crmCustomerDetailsPojos",
    "crmCustomerRefundsPojo",
    "crmPgwTransactionsPojo",
    "crmPodDetailsPojo",
    "crmPodDetailsPojos",
    "crmUpfrontPaymentPojo",
    "crmUpfrontPaymentPojos",
    "customerDetailsPojo",
    "dbSuccessCount",
    "fromDate",
    "password",
    "paymentDetailsPojo",
    "paymentDetailsPojos",
    "refundDetailsPojos",
    "remarks",
    "reversalWOCrf",
    "serverIPAddress",
    "statusCode",
    "statusCodes",
    "statusDesc",
    "toDate",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userId"
})
public class CrmFinanceDto {

    protected String billingErrorCode;
    protected String clientIPAddress;
    protected CrmCmsFilePojo cmsFile;
    @XmlElement(nillable = true)
    protected List<CrmCmsFilePojo> cmsFiles;
    protected CrmCmsPaymentPojo cmsPayment;
    @XmlElement(nillable = true)
    protected List<CrmCmsPaymentPojo> cmsPayments;
    @XmlElement(nillable = true)
    protected List<String> crfIDs;
    protected CrmUpCrfMappingPojo crfMappingPojo;
    @XmlElement(nillable = true)
    protected List<CrmUpCrfMappingPojo> crfMappingPojos;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos;
    protected CrmCustomerRefundsPojo crmCustomerRefundsPojo;
    protected CrmPgwTransactionsPojo crmPgwTransactionsPojo;
    protected CrmPodDetailsPojo crmPodDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmPodDetailsPojo> crmPodDetailsPojos;
    protected CrmUpfrontPaymentPojo crmUpfrontPaymentPojo;
    @XmlElement(nillable = true)
    protected List<CrmUpfrontPaymentPojo> crmUpfrontPaymentPojos;
    protected CrmCustomerDetailsPojo customerDetailsPojo;
    protected int dbSuccessCount;
    protected String fromDate;
    protected String password;
    protected CrmPaymentDetailsPojo paymentDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmPaymentDetailsPojo> paymentDetailsPojos;
    @XmlElement(nillable = true)
    protected List<CrmCustomerRefundsPojo> refundDetailsPojos;
    protected RemarksPojo remarks;
    protected boolean reversalWOCrf;
    protected String serverIPAddress;
    protected String statusCode;
    @XmlElement(nillable = true)
    protected List<String> statusCodes;
    protected String statusDesc;
    protected String toDate;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userId;

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
     * Gets the value of the cmsFile property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCmsFilePojo }
     *     
     */
    public CrmCmsFilePojo getCmsFile() {
        return cmsFile;
    }

    /**
     * Sets the value of the cmsFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCmsFilePojo }
     *     
     */
    public void setCmsFile(CrmCmsFilePojo value) {
        this.cmsFile = value;
    }

    /**
     * Gets the value of the cmsFiles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cmsFiles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCmsFiles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCmsFilePojo }
     * 
     * 
     */
    public List<CrmCmsFilePojo> getCmsFiles() {
        if (cmsFiles == null) {
            cmsFiles = new ArrayList<CrmCmsFilePojo>();
        }
        return this.cmsFiles;
    }

    /**
     * Gets the value of the cmsPayment property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCmsPaymentPojo }
     *     
     */
    public CrmCmsPaymentPojo getCmsPayment() {
        return cmsPayment;
    }

    /**
     * Sets the value of the cmsPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCmsPaymentPojo }
     *     
     */
    public void setCmsPayment(CrmCmsPaymentPojo value) {
        this.cmsPayment = value;
    }

    /**
     * Gets the value of the cmsPayments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cmsPayments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCmsPayments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCmsPaymentPojo }
     * 
     * 
     */
    public List<CrmCmsPaymentPojo> getCmsPayments() {
        if (cmsPayments == null) {
            cmsPayments = new ArrayList<CrmCmsPaymentPojo>();
        }
        return this.cmsPayments;
    }

    /**
     * Gets the value of the crfIDs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crfIDs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrfIDs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCrfIDs() {
        if (crfIDs == null) {
            crfIDs = new ArrayList<String>();
        }
        return this.crfIDs;
    }

    /**
     * Gets the value of the crfMappingPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmUpCrfMappingPojo }
     *     
     */
    public CrmUpCrfMappingPojo getCrfMappingPojo() {
        return crfMappingPojo;
    }

    /**
     * Sets the value of the crfMappingPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmUpCrfMappingPojo }
     *     
     */
    public void setCrfMappingPojo(CrmUpCrfMappingPojo value) {
        this.crfMappingPojo = value;
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
     * {@link CrmUpCrfMappingPojo }
     * 
     * 
     */
    public List<CrmUpCrfMappingPojo> getCrfMappingPojos() {
        if (crfMappingPojos == null) {
            crfMappingPojos = new ArrayList<CrmUpCrfMappingPojo>();
        }
        return this.crfMappingPojos;
    }

    /**
     * Gets the value of the crmCustomerDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCustomerDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCustomerDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getCrmCustomerDetailsPojos() {
        if (crmCustomerDetailsPojos == null) {
            crmCustomerDetailsPojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.crmCustomerDetailsPojos;
    }

    /**
     * Gets the value of the crmCustomerRefundsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomerRefundsPojo }
     *     
     */
    public CrmCustomerRefundsPojo getCrmCustomerRefundsPojo() {
        return crmCustomerRefundsPojo;
    }

    /**
     * Sets the value of the crmCustomerRefundsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomerRefundsPojo }
     *     
     */
    public void setCrmCustomerRefundsPojo(CrmCustomerRefundsPojo value) {
        this.crmCustomerRefundsPojo = value;
    }

    /**
     * Gets the value of the crmPgwTransactionsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPgwTransactionsPojo }
     *     
     */
    public CrmPgwTransactionsPojo getCrmPgwTransactionsPojo() {
        return crmPgwTransactionsPojo;
    }

    /**
     * Sets the value of the crmPgwTransactionsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPgwTransactionsPojo }
     *     
     */
    public void setCrmPgwTransactionsPojo(CrmPgwTransactionsPojo value) {
        this.crmPgwTransactionsPojo = value;
    }

    /**
     * Gets the value of the crmPodDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPodDetailsPojo }
     *     
     */
    public CrmPodDetailsPojo getCrmPodDetailsPojo() {
        return crmPodDetailsPojo;
    }

    /**
     * Sets the value of the crmPodDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPodDetailsPojo }
     *     
     */
    public void setCrmPodDetailsPojo(CrmPodDetailsPojo value) {
        this.crmPodDetailsPojo = value;
    }

    /**
     * Gets the value of the crmPodDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPodDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPodDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPodDetailsPojo }
     * 
     * 
     */
    public List<CrmPodDetailsPojo> getCrmPodDetailsPojos() {
        if (crmPodDetailsPojos == null) {
            crmPodDetailsPojos = new ArrayList<CrmPodDetailsPojo>();
        }
        return this.crmPodDetailsPojos;
    }

    /**
     * Gets the value of the crmUpfrontPaymentPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmUpfrontPaymentPojo }
     *     
     */
    public CrmUpfrontPaymentPojo getCrmUpfrontPaymentPojo() {
        return crmUpfrontPaymentPojo;
    }

    /**
     * Sets the value of the crmUpfrontPaymentPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmUpfrontPaymentPojo }
     *     
     */
    public void setCrmUpfrontPaymentPojo(CrmUpfrontPaymentPojo value) {
        this.crmUpfrontPaymentPojo = value;
    }

    /**
     * Gets the value of the crmUpfrontPaymentPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmUpfrontPaymentPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmUpfrontPaymentPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmUpfrontPaymentPojo }
     * 
     * 
     */
    public List<CrmUpfrontPaymentPojo> getCrmUpfrontPaymentPojos() {
        if (crmUpfrontPaymentPojos == null) {
            crmUpfrontPaymentPojos = new ArrayList<CrmUpfrontPaymentPojo>();
        }
        return this.crmUpfrontPaymentPojos;
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
     * Gets the value of the dbSuccessCount property.
     * 
     */
    public int getDbSuccessCount() {
        return dbSuccessCount;
    }

    /**
     * Sets the value of the dbSuccessCount property.
     * 
     */
    public void setDbSuccessCount(int value) {
        this.dbSuccessCount = value;
    }

    /**
     * Gets the value of the fromDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFromDate() {
        return fromDate;
    }

    /**
     * Sets the value of the fromDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFromDate(String value) {
        this.fromDate = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
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
     * Gets the value of the paymentDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPaymentDetailsPojo }
     * 
     * 
     */
    public List<CrmPaymentDetailsPojo> getPaymentDetailsPojos() {
        if (paymentDetailsPojos == null) {
            paymentDetailsPojos = new ArrayList<CrmPaymentDetailsPojo>();
        }
        return this.paymentDetailsPojos;
    }

    /**
     * Gets the value of the refundDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the refundDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRefundDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerRefundsPojo }
     * 
     * 
     */
    public List<CrmCustomerRefundsPojo> getRefundDetailsPojos() {
        if (refundDetailsPojos == null) {
            refundDetailsPojos = new ArrayList<CrmCustomerRefundsPojo>();
        }
        return this.refundDetailsPojos;
    }

    /**
     * Gets the value of the remarks property.
     * 
     * @return
     *     possible object is
     *     {@link RemarksPojo }
     *     
     */
    public RemarksPojo getRemarks() {
        return remarks;
    }

    /**
     * Sets the value of the remarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemarksPojo }
     *     
     */
    public void setRemarks(RemarksPojo value) {
        this.remarks = value;
    }

    /**
     * Gets the value of the reversalWOCrf property.
     * 
     */
    public boolean isReversalWOCrf() {
        return reversalWOCrf;
    }

    /**
     * Sets the value of the reversalWOCrf property.
     * 
     */
    public void setReversalWOCrf(boolean value) {
        this.reversalWOCrf = value;
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
     * Gets the value of the statusCodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statusCodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatusCodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getStatusCodes() {
        if (statusCodes == null) {
            statusCodes = new ArrayList<String>();
        }
        return this.statusCodes;
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
     * Gets the value of the toDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToDate() {
        return toDate;
    }

    /**
     * Sets the value of the toDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToDate(String value) {
        this.toDate = value;
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
