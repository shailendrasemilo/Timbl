
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for reportDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="reportDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaPojo" type="{http://service.report.crm.tele.np.com/}areaPojo" minOccurs="0"/>
 *         &lt;element name="auditLogPojos" type="{http://service.report.crm.tele.np.com/}crmAuditLogPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmAuditLogPojo" type="{http://service.report.crm.tele.np.com/}crmAuditLogPojo" minOccurs="0"/>
 *         &lt;element name="crmCustInteractionsPojos" type="{http://service.report.crm.tele.np.com/}crmCustInteractionsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCustWaiverPojos" type="{http://service.report.crm.tele.np.com/}crmCustWaiverPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCustomerActivityPojos" type="{http://service.report.crm.tele.np.com/}crmCustomerActivityPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmMassOutagePojo" type="{http://service.report.crm.tele.np.com/}crmMassOutagePojo" minOccurs="0"/>
 *         &lt;element name="crmMassOutagePojos" type="{http://service.report.crm.tele.np.com/}crmMassOutagePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmSlaLogPojo" type="{http://service.report.crm.tele.np.com/}crmSlaLogPojo" minOccurs="0"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inaReportPojos" type="{http://service.report.crm.tele.np.com/}inaReportPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="leadPojo" type="{http://service.report.crm.tele.np.com/}lmsPojo" minOccurs="0"/>
 *         &lt;element name="lmsReportPojos" type="{http://service.report.crm.tele.np.com/}lmsReportPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paramMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="paymentReportPojo" type="{http://service.report.crm.tele.np.com/}paymentReportPojo" minOccurs="0"/>
 *         &lt;element name="paymentReportPojos" type="{http://service.report.crm.tele.np.com/}paymentReportPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcReportPojos" type="{http://service.report.crm.tele.np.com/}qrcTicketReportPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="qrcSubSubCategoriesPojos" type="{http://service.report.crm.tele.np.com/}crmQrcSubSubCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="qrcTicketReportPojo" type="{http://service.report.crm.tele.np.com/}qrcTicketReportPojo" minOccurs="0"/>
 *         &lt;element name="remarksPojo" type="{http://service.report.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *         &lt;element name="remarksPojos" type="{http://service.report.crm.tele.np.com/}remarksPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rfsReportPojos" type="{http://service.report.crm.tele.np.com/}rfsReportPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaLogPojos" type="{http://service.report.crm.tele.np.com/}crmSlaLogPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="slaReportPojo" type="{http://service.report.crm.tele.np.com/}slaReportPojo" minOccurs="0"/>
 *         &lt;element name="slaReportPojos" type="{http://service.report.crm.tele.np.com/}slaReportPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subSubCategoriesPojo" type="{http://service.report.crm.tele.np.com/}crmQrcSubSubCategoriesPojo" minOccurs="0"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "reportDto", propOrder = {
    "areaPojo",
    "auditLogPojos",
    "clientIPAddress",
    "crmAuditLogPojo",
    "crmCustInteractionsPojos",
    "crmCustWaiverPojos",
    "crmCustomerActivityPojos",
    "crmMassOutagePojo",
    "crmMassOutagePojos",
    "crmSlaLogPojo",
    "fromDate",
    "inaReportPojos",
    "leadPojo",
    "lmsReportPojos",
    "paramMap",
    "paymentReportPojo",
    "paymentReportPojos",
    "productType",
    "qrcReportPojos",
    "qrcSubSubCategoriesPojos",
    "qrcTicketReportPojo",
    "remarksPojo",
    "remarksPojos",
    "rfsReportPojos",
    "serverIPAddress",
    "serviceType",
    "slaLogPojos",
    "slaReportPojo",
    "slaReportPojos",
    "statusCode",
    "statusDesc",
    "subSubCategoriesPojo",
    "toDate",
    "stateName",
    "cityName",
    "areaName",
    "societyName"
})
public class ReportDto {

    protected AreaPojo areaPojo;
    @XmlElement(nillable = true)
    protected List<CrmAuditLogPojo> auditLogPojos;
    protected String clientIPAddress;
    protected CrmAuditLogPojo crmAuditLogPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustInteractionsPojo> crmCustInteractionsPojos;
    @XmlElement(nillable = true)
    protected List<CrmCustWaiverPojo> crmCustWaiverPojos;
    @XmlElement(nillable = true)
    protected List<CrmCustomerActivityPojo> crmCustomerActivityPojos;
    protected CrmMassOutagePojo crmMassOutagePojo;
    @XmlElement(nillable = true)
    protected List<CrmMassOutagePojo> crmMassOutagePojos;
    protected CrmSlaLogPojo crmSlaLogPojo;
    protected String fromDate;
    @XmlElement(nillable = true)
    protected List<InaReportPojo> inaReportPojos;
    protected LmsPojo leadPojo;
    @XmlElement(nillable = true)
    protected List<LmsReportPojo> lmsReportPojos;
    @XmlElement(required = true)
    protected ReportDto.ParamMap paramMap;
    protected PaymentReportPojo paymentReportPojo;
    @XmlElement(nillable = true)
    protected List<PaymentReportPojo> paymentReportPojos;
    protected String productType;
    @XmlElement(nillable = true)
    protected List<QrcTicketReportPojo> qrcReportPojos;
    @XmlElement(nillable = true)
    protected List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoriesPojos;
    protected QrcTicketReportPojo qrcTicketReportPojo;
    protected RemarksPojo remarksPojo;
    @XmlElement(nillable = true)
    protected List<RemarksPojo> remarksPojos;
    @XmlElement(nillable = true)
    protected List<RfsReportPojo> rfsReportPojos;
    protected String serverIPAddress;
    protected String serviceType;
    @XmlElement(nillable = true)
    protected List<CrmSlaLogPojo> slaLogPojos;
    protected SlaReportPojo slaReportPojo;
    @XmlElement(nillable = true)
    protected List<SlaReportPojo> slaReportPojos;
    protected String statusCode;
    protected String statusDesc;
    protected CrmQrcSubSubCategoriesPojo subSubCategoriesPojo;
    protected String toDate;
    protected String stateName;
    protected String cityName;
    protected String areaName;
    protected String societyName;
    
    
    /**
     * Gets the value of the areaPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AreaPojo }
     *     
     */
    public AreaPojo getAreaPojo() {
        return areaPojo;
    }

    /**
     * Sets the value of the areaPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaPojo }
     *     
     */
    public void setAreaPojo(AreaPojo value) {
        this.areaPojo = value;
    }

    /**
     * Gets the value of the auditLogPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the auditLogPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAuditLogPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmAuditLogPojo }
     * 
     * 
     */
    public List<CrmAuditLogPojo> getAuditLogPojos() {
        if (auditLogPojos == null) {
            auditLogPojos = new ArrayList<CrmAuditLogPojo>();
        }
        return this.auditLogPojos;
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
     * Gets the value of the crmAuditLogPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmAuditLogPojo }
     *     
     */
    public CrmAuditLogPojo getCrmAuditLogPojo() {
        return crmAuditLogPojo;
    }

    /**
     * Sets the value of the crmAuditLogPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmAuditLogPojo }
     *     
     */
    public void setCrmAuditLogPojo(CrmAuditLogPojo value) {
        this.crmAuditLogPojo = value;
    }

    /**
     * Gets the value of the crmCustInteractionsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCustInteractionsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCustInteractionsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustInteractionsPojo }
     * 
     * 
     */
    public List<CrmCustInteractionsPojo> getCrmCustInteractionsPojos() {
        if (crmCustInteractionsPojos == null) {
            crmCustInteractionsPojos = new ArrayList<CrmCustInteractionsPojo>();
        }
        return this.crmCustInteractionsPojos;
    }

    /**
     * Gets the value of the crmCustWaiverPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCustWaiverPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCustWaiverPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustWaiverPojo }
     * 
     * 
     */
    public List<CrmCustWaiverPojo> getCrmCustWaiverPojos() {
        if (crmCustWaiverPojos == null) {
            crmCustWaiverPojos = new ArrayList<CrmCustWaiverPojo>();
        }
        return this.crmCustWaiverPojos;
    }

    /**
     * Gets the value of the crmCustomerActivityPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCustomerActivityPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCustomerActivityPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerActivityPojo }
     * 
     * 
     */
    public List<CrmCustomerActivityPojo> getCrmCustomerActivityPojos() {
        if (crmCustomerActivityPojos == null) {
            crmCustomerActivityPojos = new ArrayList<CrmCustomerActivityPojo>();
        }
        return this.crmCustomerActivityPojos;
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
     * Gets the value of the crmMassOutagePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmMassOutagePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmMassOutagePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmMassOutagePojo }
     * 
     * 
     */
    public List<CrmMassOutagePojo> getCrmMassOutagePojos() {
        if (crmMassOutagePojos == null) {
            crmMassOutagePojos = new ArrayList<CrmMassOutagePojo>();
        }
        return this.crmMassOutagePojos;
    }

    /**
     * Gets the value of the crmSlaLogPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmSlaLogPojo }
     *     
     */
    public CrmSlaLogPojo getCrmSlaLogPojo() {
        return crmSlaLogPojo;
    }

    /**
     * Sets the value of the crmSlaLogPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmSlaLogPojo }
     *     
     */
    public void setCrmSlaLogPojo(CrmSlaLogPojo value) {
        this.crmSlaLogPojo = value;
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
     * Gets the value of the inaReportPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the inaReportPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInaReportPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InaReportPojo }
     * 
     * 
     */
    public List<InaReportPojo> getInaReportPojos() {
        if (inaReportPojos == null) {
            inaReportPojos = new ArrayList<InaReportPojo>();
        }
        return this.inaReportPojos;
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
     * Gets the value of the lmsReportPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lmsReportPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLmsReportPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LmsReportPojo }
     * 
     * 
     */
    public List<LmsReportPojo> getLmsReportPojos() {
        if (lmsReportPojos == null) {
            lmsReportPojos = new ArrayList<LmsReportPojo>();
        }
        return this.lmsReportPojos;
    }

    /**
     * Gets the value of the paramMap property.
     * 
     * @return
     *     possible object is
     *     {@link ReportDto.ParamMap }
     *     
     */
    public ReportDto.ParamMap getParamMap() {
        return paramMap;
    }

    /**
     * Sets the value of the paramMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportDto.ParamMap }
     *     
     */
    public void setParamMap(ReportDto.ParamMap value) {
        this.paramMap = value;
    }

    /**
     * Gets the value of the paymentReportPojo property.
     * 
     * @return
     *     possible object is
     *     {@link PaymentReportPojo }
     *     
     */
    public PaymentReportPojo getPaymentReportPojo() {
        return paymentReportPojo;
    }

    /**
     * Sets the value of the paymentReportPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PaymentReportPojo }
     *     
     */
    public void setPaymentReportPojo(PaymentReportPojo value) {
        this.paymentReportPojo = value;
    }

    /**
     * Gets the value of the paymentReportPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentReportPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentReportPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentReportPojo }
     * 
     * 
     */
    public List<PaymentReportPojo> getPaymentReportPojos() {
        if (paymentReportPojos == null) {
            paymentReportPojos = new ArrayList<PaymentReportPojo>();
        }
        return this.paymentReportPojos;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the qrcReportPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcReportPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcReportPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link QrcTicketReportPojo }
     * 
     * 
     */
    public List<QrcTicketReportPojo> getQrcReportPojos() {
        if (qrcReportPojos == null) {
            qrcReportPojos = new ArrayList<QrcTicketReportPojo>();
        }
        return this.qrcReportPojos;
    }

    /**
     * Gets the value of the qrcSubSubCategoriesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcSubSubCategoriesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcSubSubCategoriesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcSubSubCategoriesPojo }
     * 
     * 
     */
    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategoriesPojos() {
        if (qrcSubSubCategoriesPojos == null) {
            qrcSubSubCategoriesPojos = new ArrayList<CrmQrcSubSubCategoriesPojo>();
        }
        return this.qrcSubSubCategoriesPojos;
    }

    /**
     * Gets the value of the qrcTicketReportPojo property.
     * 
     * @return
     *     possible object is
     *     {@link QrcTicketReportPojo }
     *     
     */
    public QrcTicketReportPojo getQrcTicketReportPojo() {
        return qrcTicketReportPojo;
    }

    /**
     * Sets the value of the qrcTicketReportPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link QrcTicketReportPojo }
     *     
     */
    public void setQrcTicketReportPojo(QrcTicketReportPojo value) {
        this.qrcTicketReportPojo = value;
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
     * Gets the value of the rfsReportPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rfsReportPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRfsReportPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RfsReportPojo }
     * 
     * 
     */
    public List<RfsReportPojo> getRfsReportPojos() {
        if (rfsReportPojos == null) {
            rfsReportPojos = new ArrayList<RfsReportPojo>();
        }
        return this.rfsReportPojos;
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
     * Gets the value of the slaLogPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slaLogPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlaLogPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmSlaLogPojo }
     * 
     * 
     */
    public List<CrmSlaLogPojo> getSlaLogPojos() {
        if (slaLogPojos == null) {
            slaLogPojos = new ArrayList<CrmSlaLogPojo>();
        }
        return this.slaLogPojos;
    }

    /**
     * Gets the value of the slaReportPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SlaReportPojo }
     *     
     */
    public SlaReportPojo getSlaReportPojo() {
        return slaReportPojo;
    }

    /**
     * Sets the value of the slaReportPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SlaReportPojo }
     *     
     */
    public void setSlaReportPojo(SlaReportPojo value) {
        this.slaReportPojo = value;
    }

    /**
     * Gets the value of the slaReportPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slaReportPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlaReportPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SlaReportPojo }
     * 
     * 
     */
    public List<SlaReportPojo> getSlaReportPojos() {
        if (slaReportPojos == null) {
            slaReportPojos = new ArrayList<SlaReportPojo>();
        }
        return this.slaReportPojos;
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
     * Gets the value of the subSubCategoriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcSubSubCategoriesPojo }
     *     
     */
    public CrmQrcSubSubCategoriesPojo getSubSubCategoriesPojo() {
        return subSubCategoriesPojo;
    }

    /**
     * Sets the value of the subSubCategoriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcSubSubCategoriesPojo }
     *     
     */
    public void setSubSubCategoriesPojo(CrmQrcSubSubCategoriesPojo value) {
        this.subSubCategoriesPojo = value;
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
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class ParamMap {

        protected List<ReportDto.ParamMap.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ReportDto.ParamMap.Entry }
         * 
         * 
         */
        public List<ReportDto.ParamMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ReportDto.ParamMap.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected String value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }
            

        }

    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String stateName )
    {
        this.stateName = stateName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String cityName )
    {
        this.cityName = cityName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String areaName )
    {
        this.areaName = areaName;
    }

    public String getSocietyName()
    {
        return societyName;
    }

    public void setSocietyName( String societyName )
    {
        this.societyName = societyName;
    }
          
    
}
