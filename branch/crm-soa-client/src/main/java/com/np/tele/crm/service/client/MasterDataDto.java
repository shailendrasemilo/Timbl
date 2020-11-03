
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for masterDataDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="masterDataDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessGroupPojo" type="{http://service.masterdata.crm.tele.np.com/}accessGroupPojo" minOccurs="0"/>
 *         &lt;element name="accessGroupPojoList" type="{http://service.masterdata.crm.tele.np.com/}accessGroupPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accessGroupPojoMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}accessGroupPojo" minOccurs="0"/>
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
 *         &lt;element name="alertMasterPojo" type="{http://service.masterdata.crm.tele.np.com/}alertMasterPojo" minOccurs="0"/>
 *         &lt;element name="categoryValuePrefix" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryValueRangeEnd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="categoryValueRangeStart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmHolidayPojo" type="{http://service.masterdata.crm.tele.np.com/}crmHolidayDetails" minOccurs="0"/>
 *         &lt;element name="crmHolidayPojos" type="{http://service.masterdata.crm.tele.np.com/}crmHolidayDetails" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmNpMobilePojo" type="{http://service.masterdata.crm.tele.np.com/}crmNpMobilePojo" minOccurs="0"/>
 *         &lt;element name="crmNpMobilePojos" type="{http://service.masterdata.crm.tele.np.com/}crmNpMobilePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmParameterPojos" type="{http://service.masterdata.crm.tele.np.com/}crmParameterPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmPartnerDetailsPojos" type="{http://service.masterdata.crm.tele.np.com/}crmPartnerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmPartnerMappingList" type="{http://service.masterdata.crm.tele.np.com/}crmPartnerMappingPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmPartnerMappingPojo" type="{http://service.masterdata.crm.tele.np.com/}crmPartnerMappingPojo" minOccurs="0"/>
 *         &lt;element name="crmPartnerNetworkConfigPojo" type="{http://service.masterdata.crm.tele.np.com/}crmPartnerNetworkConfigPojo" minOccurs="0"/>
 *         &lt;element name="crmPartnerNetworkConfigPojos" type="{http://service.masterdata.crm.tele.np.com/}crmPartnerNetworkConfigPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmPlanMasterPojos" type="{http://service.masterdata.crm.tele.np.com/}crmPlanMasterPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmRcaReason" type="{http://service.masterdata.crm.tele.np.com/}crmRcaReasonPojo" minOccurs="0"/>
 *         &lt;element name="crmRcaReasonsList" type="{http://service.masterdata.crm.tele.np.com/}crmRcaReasonPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmRolesPojo" type="{http://service.masterdata.crm.tele.np.com/}crmRolesPojo" minOccurs="0"/>
 *         &lt;element name="crmRolesPojoList" type="{http://service.masterdata.crm.tele.np.com/}crmRolesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmRolesPojoMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}crmRolesPojo" minOccurs="0"/>
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
 *         &lt;element name="crmUserPojos" type="{http://service.masterdata.crm.tele.np.com/}crmUserPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="emailServerPojo" type="{http://service.masterdata.crm.tele.np.com/}emailServerPojo" minOccurs="0"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="groupsPojo" type="{http://service.masterdata.crm.tele.np.com/}groupsPojo" minOccurs="0"/>
 *         &lt;element name="groupsPojoList" type="{http://service.masterdata.crm.tele.np.com/}groupsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="inboxId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="masterNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="partnerPojo" type="{http://service.masterdata.crm.tele.np.com/}partnerPojo" minOccurs="0"/>
 *         &lt;element name="partnerPojoList" type="{http://service.masterdata.crm.tele.np.com/}partnerPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="partnerPojoMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}partnerPojo" minOccurs="0"/>
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
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planMaster" type="{http://service.masterdata.crm.tele.np.com/}crmPlanMasterPojo" minOccurs="0"/>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="projectsPojo" type="{http://service.masterdata.crm.tele.np.com/}projectsPojo" minOccurs="0"/>
 *         &lt;element name="projectsPojos" type="{http://service.masterdata.crm.tele.np.com/}projectsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarks" type="{http://service.masterdata.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smsGatewayPojo" type="{http://service.masterdata.crm.tele.np.com/}smsGatewayPojo" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ticketHistory" type="{http://service.masterdata.crm.tele.np.com/}crmTicketHistoryPojo" minOccurs="0"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userMasterPojo" type="{http://service.masterdata.crm.tele.np.com/}userMasterPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "masterDataDto", propOrder = {
    "accessGroupPojo",
    "accessGroupPojoList",
    "accessGroupPojoMap",
    "alertMasterPojo",
    "categoryValuePrefix",
    "categoryValueRangeEnd",
    "categoryValueRangeStart",
    "clientIPAddress",
    "crmHolidayPojo",
    "crmHolidayPojos",
    "crmNpMobilePojo",
    "crmNpMobilePojos",
    "crmParameterPojos",
    "crmPartnerDetailsPojos",
    "crmPartnerMappingList",
    "crmPartnerMappingPojo",
    "crmPartnerNetworkConfigPojo",
    "crmPartnerNetworkConfigPojos",
    "crmPlanMasterPojos",
    "crmRcaReason",
    "crmRcaReasonsList",
    "crmRolesPojo",
    "crmRolesPojoList",
    "crmRolesPojoMap",
    "crmUserPojos",
    "emailServerPojo",
    "fromDate",
    "groupsPojo",
    "groupsPojoList",
    "inboxId",
    "masterNames",
    "partnerId",
    "partnerPojo",
    "partnerPojoList",
    "partnerPojoMap",
    "password",
    "planMaster",
    "product",
    "projectsPojo",
    "projectsPojos",
    "remarks",
    "serverIPAddress",
    "smsGatewayPojo",
    "statusCode",
    "statusDesc",
    "ticketHistory",
    "toDate",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userID",
    "userMasterPojo"
})
public class MasterDataDto {

    protected AccessGroupPojo accessGroupPojo;
    @XmlElement(nillable = true)
    protected List<AccessGroupPojo> accessGroupPojoList;
    @XmlElement(required = true)
    protected MasterDataDto.AccessGroupPojoMap accessGroupPojoMap;
    protected AlertMasterPojo alertMasterPojo;
    protected String categoryValuePrefix;
    protected String categoryValueRangeEnd;
    protected String categoryValueRangeStart;
    protected String clientIPAddress;
    protected CrmHolidayDetails crmHolidayPojo;
    @XmlElement(nillable = true)
    protected List<CrmHolidayDetails> crmHolidayPojos;
    protected CrmNpMobilePojo crmNpMobilePojo;
    @XmlElement(nillable = true)
    protected List<CrmNpMobilePojo> crmNpMobilePojos;
    @XmlElement(nillable = true)
    protected List<CrmParameterPojo> crmParameterPojos;
    @XmlElement(nillable = true)
    protected List<CrmPartnerDetailsPojo> crmPartnerDetailsPojos;
    @XmlElement(nillable = true)
    protected List<CrmPartnerMappingPojo> crmPartnerMappingList;
    protected CrmPartnerMappingPojo crmPartnerMappingPojo;
    protected CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo;
    @XmlElement(nillable = true)
    protected List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos;
    @XmlElement(nillable = true)
    protected List<CrmPlanMasterPojo> crmPlanMasterPojos;
    protected CrmRcaReasonPojo crmRcaReason;
    @XmlElement(nillable = true)
    protected List<CrmRcaReasonPojo> crmRcaReasonsList;
    protected CrmRolesPojo crmRolesPojo;
    @XmlElement(nillable = true)
    protected List<CrmRolesPojo> crmRolesPojoList;
    @XmlElement(required = true)
    protected MasterDataDto.CrmRolesPojoMap crmRolesPojoMap;
    @XmlElement(nillable = true)
    protected List<CrmUserPojo> crmUserPojos;
    protected EmailServerPojo emailServerPojo;
    protected String fromDate;
    protected GroupsPojo groupsPojo;
    @XmlElement(nillable = true)
    protected List<GroupsPojo> groupsPojoList;
    protected long inboxId;
    @XmlElement(nillable = true)
    protected List<String> masterNames;
    protected long partnerId;
    protected PartnerPojo partnerPojo;
    @XmlElement(nillable = true)
    protected List<PartnerPojo> partnerPojoList;
    @XmlElement(required = true)
    protected MasterDataDto.PartnerPojoMap partnerPojoMap;
    protected String password;
    protected CrmPlanMasterPojo planMaster;
    protected String product;
    protected ProjectsPojo projectsPojo;
    @XmlElement(nillable = true)
    protected List<ProjectsPojo> projectsPojos;
    protected RemarksPojo remarks;
    protected String serverIPAddress;
    protected SmsGatewayPojo smsGatewayPojo;
    protected String statusCode;
    protected String statusDesc;
    protected CrmTicketHistoryPojo ticketHistory;
    protected String toDate;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userID;
    protected UserMasterPojo userMasterPojo;

    /**
     * Gets the value of the accessGroupPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AccessGroupPojo }
     *     
     */
    public AccessGroupPojo getAccessGroupPojo() {
        return accessGroupPojo;
    }

    /**
     * Sets the value of the accessGroupPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessGroupPojo }
     *     
     */
    public void setAccessGroupPojo(AccessGroupPojo value) {
        this.accessGroupPojo = value;
    }

    /**
     * Gets the value of the accessGroupPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessGroupPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessGroupPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessGroupPojo }
     * 
     * 
     */
    public List<AccessGroupPojo> getAccessGroupPojoList() {
        if (accessGroupPojoList == null) {
            accessGroupPojoList = new ArrayList<AccessGroupPojo>();
        }
        return this.accessGroupPojoList;
    }

    /**
     * Gets the value of the accessGroupPojoMap property.
     * 
     * @return
     *     possible object is
     *     {@link MasterDataDto.AccessGroupPojoMap }
     *     
     */
    public MasterDataDto.AccessGroupPojoMap getAccessGroupPojoMap() {
        return accessGroupPojoMap;
    }

    /**
     * Sets the value of the accessGroupPojoMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterDataDto.AccessGroupPojoMap }
     *     
     */
    public void setAccessGroupPojoMap(MasterDataDto.AccessGroupPojoMap value) {
        this.accessGroupPojoMap = value;
    }

    /**
     * Gets the value of the alertMasterPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AlertMasterPojo }
     *     
     */
    public AlertMasterPojo getAlertMasterPojo() {
        return alertMasterPojo;
    }

    /**
     * Sets the value of the alertMasterPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertMasterPojo }
     *     
     */
    public void setAlertMasterPojo(AlertMasterPojo value) {
        this.alertMasterPojo = value;
    }

    /**
     * Gets the value of the categoryValuePrefix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryValuePrefix() {
        return categoryValuePrefix;
    }

    /**
     * Sets the value of the categoryValuePrefix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryValuePrefix(String value) {
        this.categoryValuePrefix = value;
    }

    /**
     * Gets the value of the categoryValueRangeEnd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryValueRangeEnd() {
        return categoryValueRangeEnd;
    }

    /**
     * Sets the value of the categoryValueRangeEnd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryValueRangeEnd(String value) {
        this.categoryValueRangeEnd = value;
    }

    /**
     * Gets the value of the categoryValueRangeStart property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryValueRangeStart() {
        return categoryValueRangeStart;
    }

    /**
     * Sets the value of the categoryValueRangeStart property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryValueRangeStart(String value) {
        this.categoryValueRangeStart = value;
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
     * Gets the value of the crmHolidayPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmHolidayDetails }
     *     
     */
    public CrmHolidayDetails getCrmHolidayPojo() {
        return crmHolidayPojo;
    }

    /**
     * Sets the value of the crmHolidayPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmHolidayDetails }
     *     
     */
    public void setCrmHolidayPojo(CrmHolidayDetails value) {
        this.crmHolidayPojo = value;
    }

    /**
     * Gets the value of the crmHolidayPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmHolidayPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmHolidayPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmHolidayDetails }
     * 
     * 
     */
    public List<CrmHolidayDetails> getCrmHolidayPojos() {
        if (crmHolidayPojos == null) {
            crmHolidayPojos = new ArrayList<CrmHolidayDetails>();
        }
        return this.crmHolidayPojos;
    }

    /**
     * Gets the value of the crmNpMobilePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmNpMobilePojo }
     *     
     */
    public CrmNpMobilePojo getCrmNpMobilePojo() {
        return crmNpMobilePojo;
    }

    /**
     * Sets the value of the crmNpMobilePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmNpMobilePojo }
     *     
     */
    public void setCrmNpMobilePojo(CrmNpMobilePojo value) {
        this.crmNpMobilePojo = value;
    }

    /**
     * Gets the value of the crmNpMobilePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmNpMobilePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmNpMobilePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmNpMobilePojo }
     * 
     * 
     */
    public List<CrmNpMobilePojo> getCrmNpMobilePojos() {
        if (crmNpMobilePojos == null) {
            crmNpMobilePojos = new ArrayList<CrmNpMobilePojo>();
        }
        return this.crmNpMobilePojos;
    }

    /**
     * Gets the value of the crmParameterPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmParameterPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmParameterPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmParameterPojo }
     * 
     * 
     */
    public List<CrmParameterPojo> getCrmParameterPojos() {
        if (crmParameterPojos == null) {
            crmParameterPojos = new ArrayList<CrmParameterPojo>();
        }
        return this.crmParameterPojos;
    }

    /**
     * Gets the value of the crmPartnerDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPartnerDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPartnerDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPartnerDetailsPojo }
     * 
     * 
     */
    public List<CrmPartnerDetailsPojo> getCrmPartnerDetailsPojos() {
        if (crmPartnerDetailsPojos == null) {
            crmPartnerDetailsPojos = new ArrayList<CrmPartnerDetailsPojo>();
        }
        return this.crmPartnerDetailsPojos;
    }

    /**
     * Gets the value of the crmPartnerMappingList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPartnerMappingList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPartnerMappingList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPartnerMappingPojo }
     * 
     * 
     */
    public List<CrmPartnerMappingPojo> getCrmPartnerMappingList() {
        if (crmPartnerMappingList == null) {
            crmPartnerMappingList = new ArrayList<CrmPartnerMappingPojo>();
        }
        return this.crmPartnerMappingList;
    }

    /**
     * Gets the value of the crmPartnerMappingPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPartnerMappingPojo }
     *     
     */
    public CrmPartnerMappingPojo getCrmPartnerMappingPojo() {
        return crmPartnerMappingPojo;
    }

    /**
     * Sets the value of the crmPartnerMappingPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPartnerMappingPojo }
     *     
     */
    public void setCrmPartnerMappingPojo(CrmPartnerMappingPojo value) {
        this.crmPartnerMappingPojo = value;
    }

    /**
     * Gets the value of the crmPartnerNetworkConfigPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPartnerNetworkConfigPojo }
     *     
     */
    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfigPojo() {
        return crmPartnerNetworkConfigPojo;
    }

    /**
     * Sets the value of the crmPartnerNetworkConfigPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPartnerNetworkConfigPojo }
     *     
     */
    public void setCrmPartnerNetworkConfigPojo(CrmPartnerNetworkConfigPojo value) {
        this.crmPartnerNetworkConfigPojo = value;
    }

    /**
     * Gets the value of the crmPartnerNetworkConfigPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPartnerNetworkConfigPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPartnerNetworkConfigPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPartnerNetworkConfigPojo }
     * 
     * 
     */
    public List<CrmPartnerNetworkConfigPojo> getCrmPartnerNetworkConfigPojos() {
        if (crmPartnerNetworkConfigPojos == null) {
            crmPartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
        }
        return this.crmPartnerNetworkConfigPojos;
    }

    /**
     * Gets the value of the crmPlanMasterPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmPlanMasterPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmPlanMasterPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmPlanMasterPojo }
     * 
     * 
     */
    public List<CrmPlanMasterPojo> getCrmPlanMasterPojos() {
        if (crmPlanMasterPojos == null) {
            crmPlanMasterPojos = new ArrayList<CrmPlanMasterPojo>();
        }
        return this.crmPlanMasterPojos;
    }

    /**
     * Gets the value of the crmRcaReason property.
     * 
     * @return
     *     possible object is
     *     {@link CrmRcaReasonPojo }
     *     
     */
    public CrmRcaReasonPojo getCrmRcaReason() {
        return crmRcaReason;
    }

    /**
     * Sets the value of the crmRcaReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmRcaReasonPojo }
     *     
     */
    public void setCrmRcaReason(CrmRcaReasonPojo value) {
        this.crmRcaReason = value;
    }

    /**
     * Gets the value of the crmRcaReasonsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmRcaReasonsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmRcaReasonsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmRcaReasonPojo }
     * 
     * 
     */
    public List<CrmRcaReasonPojo> getCrmRcaReasonsList() {
        if (crmRcaReasonsList == null) {
            crmRcaReasonsList = new ArrayList<CrmRcaReasonPojo>();
        }
        return this.crmRcaReasonsList;
    }

    /**
     * Gets the value of the crmRolesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmRolesPojo }
     *     
     */
    public CrmRolesPojo getCrmRolesPojo() {
        return crmRolesPojo;
    }

    /**
     * Sets the value of the crmRolesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmRolesPojo }
     *     
     */
    public void setCrmRolesPojo(CrmRolesPojo value) {
        this.crmRolesPojo = value;
    }

    /**
     * Gets the value of the crmRolesPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmRolesPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmRolesPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmRolesPojo }
     * 
     * 
     */
    public List<CrmRolesPojo> getCrmRolesPojoList() {
        if (crmRolesPojoList == null) {
            crmRolesPojoList = new ArrayList<CrmRolesPojo>();
        }
        return this.crmRolesPojoList;
    }

    /**
     * Gets the value of the crmRolesPojoMap property.
     * 
     * @return
     *     possible object is
     *     {@link MasterDataDto.CrmRolesPojoMap }
     *     
     */
    public MasterDataDto.CrmRolesPojoMap getCrmRolesPojoMap() {
        return crmRolesPojoMap;
    }

    /**
     * Sets the value of the crmRolesPojoMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterDataDto.CrmRolesPojoMap }
     *     
     */
    public void setCrmRolesPojoMap(MasterDataDto.CrmRolesPojoMap value) {
        this.crmRolesPojoMap = value;
    }

    /**
     * Gets the value of the crmUserPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmUserPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmUserPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmUserPojo }
     * 
     * 
     */
    public List<CrmUserPojo> getCrmUserPojos() {
        if (crmUserPojos == null) {
            crmUserPojos = new ArrayList<CrmUserPojo>();
        }
        return this.crmUserPojos;
    }

    /**
     * Gets the value of the emailServerPojo property.
     * 
     * @return
     *     possible object is
     *     {@link EmailServerPojo }
     *     
     */
    public EmailServerPojo getEmailServerPojo() {
        return emailServerPojo;
    }

    /**
     * Sets the value of the emailServerPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmailServerPojo }
     *     
     */
    public void setEmailServerPojo(EmailServerPojo value) {
        this.emailServerPojo = value;
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
     * Gets the value of the groupsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link GroupsPojo }
     *     
     */
    public GroupsPojo getGroupsPojo() {
        return groupsPojo;
    }

    /**
     * Sets the value of the groupsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link GroupsPojo }
     *     
     */
    public void setGroupsPojo(GroupsPojo value) {
        this.groupsPojo = value;
    }

    /**
     * Gets the value of the groupsPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupsPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupsPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GroupsPojo }
     * 
     * 
     */
    public List<GroupsPojo> getGroupsPojoList() {
        if (groupsPojoList == null) {
            groupsPojoList = new ArrayList<GroupsPojo>();
        }
        return this.groupsPojoList;
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
     * Gets the value of the masterNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the masterNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMasterNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getMasterNames() {
        if (masterNames == null) {
            masterNames = new ArrayList<String>();
        }
        return this.masterNames;
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
     * Gets the value of the partnerPojo property.
     * 
     * @return
     *     possible object is
     *     {@link PartnerPojo }
     *     
     */
    public PartnerPojo getPartnerPojo() {
        return partnerPojo;
    }

    /**
     * Sets the value of the partnerPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartnerPojo }
     *     
     */
    public void setPartnerPojo(PartnerPojo value) {
        this.partnerPojo = value;
    }

    /**
     * Gets the value of the partnerPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the partnerPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPartnerPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartnerPojo }
     * 
     * 
     */
    public List<PartnerPojo> getPartnerPojoList() {
        if (partnerPojoList == null) {
            partnerPojoList = new ArrayList<PartnerPojo>();
        }
        return this.partnerPojoList;
    }

    /**
     * Gets the value of the partnerPojoMap property.
     * 
     * @return
     *     possible object is
     *     {@link MasterDataDto.PartnerPojoMap }
     *     
     */
    public MasterDataDto.PartnerPojoMap getPartnerPojoMap() {
        return partnerPojoMap;
    }

    /**
     * Sets the value of the partnerPojoMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterDataDto.PartnerPojoMap }
     *     
     */
    public void setPartnerPojoMap(MasterDataDto.PartnerPojoMap value) {
        this.partnerPojoMap = value;
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
     * Gets the value of the planMaster property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPlanMasterPojo }
     *     
     */
    public CrmPlanMasterPojo getPlanMaster() {
        return planMaster;
    }

    /**
     * Sets the value of the planMaster property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPlanMasterPojo }
     *     
     */
    public void setPlanMaster(CrmPlanMasterPojo value) {
        this.planMaster = value;
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
     * Gets the value of the projectsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link ProjectsPojo }
     *     
     */
    public ProjectsPojo getProjectsPojo() {
        return projectsPojo;
    }

    /**
     * Sets the value of the projectsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProjectsPojo }
     *     
     */
    public void setProjectsPojo(ProjectsPojo value) {
        this.projectsPojo = value;
    }

    /**
     * Gets the value of the projectsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the projectsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProjectsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProjectsPojo }
     * 
     * 
     */
    public List<ProjectsPojo> getProjectsPojos() {
        if (projectsPojos == null) {
            projectsPojos = new ArrayList<ProjectsPojo>();
        }
        return this.projectsPojos;
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
     * Gets the value of the smsGatewayPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SmsGatewayPojo }
     *     
     */
    public SmsGatewayPojo getSmsGatewayPojo() {
        return smsGatewayPojo;
    }

    /**
     * Sets the value of the smsGatewayPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SmsGatewayPojo }
     *     
     */
    public void setSmsGatewayPojo(SmsGatewayPojo value) {
        this.smsGatewayPojo = value;
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
     * Gets the value of the ticketHistory property.
     * 
     * @return
     *     possible object is
     *     {@link CrmTicketHistoryPojo }
     *     
     */
    public CrmTicketHistoryPojo getTicketHistory() {
        return ticketHistory;
    }

    /**
     * Sets the value of the ticketHistory property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmTicketHistoryPojo }
     *     
     */
    public void setTicketHistory(CrmTicketHistoryPojo value) {
        this.ticketHistory = value;
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
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the userMasterPojo property.
     * 
     * @return
     *     possible object is
     *     {@link UserMasterPojo }
     *     
     */
    public UserMasterPojo getUserMasterPojo() {
        return userMasterPojo;
    }

    /**
     * Sets the value of the userMasterPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserMasterPojo }
     *     
     */
    public void setUserMasterPojo(UserMasterPojo value) {
        this.userMasterPojo = value;
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
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}accessGroupPojo" minOccurs="0"/>
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
    public static class AccessGroupPojoMap {

        protected List<MasterDataDto.AccessGroupPojoMap.Entry> entry;

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
         * {@link MasterDataDto.AccessGroupPojoMap.Entry }
         * 
         * 
         */
        public List<MasterDataDto.AccessGroupPojoMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<MasterDataDto.AccessGroupPojoMap.Entry>();
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
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}accessGroupPojo" minOccurs="0"/>
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

            protected Long key;
            protected AccessGroupPojo value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link AccessGroupPojo }
             *     
             */
            public AccessGroupPojo getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link AccessGroupPojo }
             *     
             */
            public void setValue(AccessGroupPojo value) {
                this.value = value;
            }

        }

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
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}crmRolesPojo" minOccurs="0"/>
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
    public static class CrmRolesPojoMap {

        protected List<MasterDataDto.CrmRolesPojoMap.Entry> entry;

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
         * {@link MasterDataDto.CrmRolesPojoMap.Entry }
         * 
         * 
         */
        public List<MasterDataDto.CrmRolesPojoMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<MasterDataDto.CrmRolesPojoMap.Entry>();
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
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}crmRolesPojo" minOccurs="0"/>
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

            protected Long key;
            protected CrmRolesPojo value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link CrmRolesPojo }
             *     
             */
            public CrmRolesPojo getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link CrmRolesPojo }
             *     
             */
            public void setValue(CrmRolesPojo value) {
                this.value = value;
            }

        }

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
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}partnerPojo" minOccurs="0"/>
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
    public static class PartnerPojoMap {

        protected List<MasterDataDto.PartnerPojoMap.Entry> entry;

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
         * {@link MasterDataDto.PartnerPojoMap.Entry }
         * 
         * 
         */
        public List<MasterDataDto.PartnerPojoMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<MasterDataDto.PartnerPojoMap.Entry>();
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
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="value" type="{http://service.masterdata.crm.tele.np.com/}partnerPojo" minOccurs="0"/>
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

            protected Long key;
            protected PartnerPojo value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link PartnerPojo }
             *     
             */
            public PartnerPojo getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link PartnerPojo }
             *     
             */
            public void setValue(PartnerPojo value) {
                this.value = value;
            }

        }

    }

}
