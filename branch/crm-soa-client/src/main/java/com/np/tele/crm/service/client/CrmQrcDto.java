
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
 * <p>Java class for crmQrcDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmQrcDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="activationTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activityAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addOnPlanPojo" type="{http://service.qrc.crm.tele.np.com/}crmPlanMasterPojo" minOccurs="0"/>
 *         &lt;element name="billCyclePojo" type="{http://service.qrc.crm.tele.np.com/}crmBillCyclePojo" minOccurs="0"/>
 *         &lt;element name="billList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="billingErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingPlanRequestPojo" type="{http://service.qrc.crm.tele.np.com/}crmBillingPlanRequestPojo" minOccurs="0"/>
 *         &lt;element name="billingPlanRequestPojos" type="{http://service.qrc.crm.tele.np.com/}crmBillingPlanRequestPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="churnType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commonWorkflowPojos" type="{http://service.qrc.crm.tele.np.com/}commonWorkflowPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contentPojos" type="{http://service.qrc.crm.tele.np.com/}contentPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmAddressDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmAddressDetailsPojo" minOccurs="0"/>
 *         &lt;element name="crmBillCyclePojosList" type="{http://service.qrc.crm.tele.np.com/}crmBillCyclePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCustAssetDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmCustAssetDetailsPojo" minOccurs="0"/>
 *         &lt;element name="crmCustAssetDetailsPojos" type="{http://service.qrc.crm.tele.np.com/}crmCustAssetDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmCustWaiverPojo" type="{http://service.qrc.crm.tele.np.com/}crmCustWaiverPojo" minOccurs="0"/>
 *         &lt;element name="crmCustWaiverPojosList" type="{http://service.qrc.crm.tele.np.com/}crmCustWaiverPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmInvoiceDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmInvoiceDetailsPojo" minOccurs="0"/>
 *         &lt;element name="crmPartnerNetworkConfigPojo" type="{http://service.qrc.crm.tele.np.com/}crmPartnerNetworkConfigPojo" minOccurs="0"/>
 *         &lt;element name="crmPlanDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmPlanDetailsPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcCategoriesPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcCategoriesPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcCategoriesPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmQrcCategoryBinMappingPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcCategoryBinMappingPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcCategoryBinMappingPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcCategoryBinMappingPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmQrcCommonPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcCommonPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcSubCategoriesPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubCategoriesPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcSubCategoriesPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmQrcSubCategoriesPojosList" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmQrcSubSubCategoriesPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubSubCategoriesPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcSubSubCategoriesPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubSubCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmQrcWhitelistPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcWhitelistPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcWhitelistPojoList" type="{http://service.qrc.crm.tele.np.com/}crmQrcWhitelistPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmRcaReasonPojos" type="{http://service.qrc.crm.tele.np.com/}crmRcaReasonPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmSelfcareCategoriesPojo" type="{http://service.qrc.crm.tele.np.com/}crmSelfcareCategoriesPojo" minOccurs="0"/>
 *         &lt;element name="crmSelfcareCategoriesPojos" type="{http://service.qrc.crm.tele.np.com/}crmSelfcareCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmShiftingWorkflowPojo" type="{http://service.qrc.crm.tele.np.com/}crmShiftingWorkflowPojo" minOccurs="0"/>
 *         &lt;element name="crmShiftingWorkflowPojos" type="{http://service.qrc.crm.tele.np.com/}crmShiftingWorkflowPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmSrTicketsPojo" type="{http://service.qrc.crm.tele.np.com/}crmSrTicketsPojo" minOccurs="0"/>
 *         &lt;element name="crmSrTicketsPojos" type="{http://service.qrc.crm.tele.np.com/}crmSrTicketsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="custAdditionalDetails" type="{http://service.qrc.crm.tele.np.com/}crmCustAdditionalDetails" minOccurs="0"/>
 *         &lt;element name="custInteractionsPojo" type="{http://service.qrc.crm.tele.np.com/}crmCustInteractionsPojo" minOccurs="0"/>
 *         &lt;element name="custInteractionsPojos" type="{http://service.qrc.crm.tele.np.com/}crmCustInteractionsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="custUsageDetailsPojos" type="{http://service.qrc.crm.tele.np.com/}customerUsageDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmCustomerDetailsPojo" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojos" type="{http://service.qrc.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRecordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="customerUsageDetailsPojos" type="{http://service.qrc.crm.tele.np.com/}customerUsageDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="documetDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmDocumentDetailsPojo" minOccurs="0"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="failedCustomerMapList">
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
 *         &lt;element name="failedWhitelistCustomer" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="fromDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futureOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futureStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gracePeriodReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inboxSelected" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="invoiceDetailsPojos" type="{http://service.qrc.crm.tele.np.com/}crmInvoiceDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="macFaulty" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="massOutagePojo" type="{http://service.qrc.crm.tele.np.com/}crmMassOutagePojo" minOccurs="0"/>
 *         &lt;element name="massOutagePojos" type="{http://service.qrc.crm.tele.np.com/}crmMassOutagePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="maxResultSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nationaltyDetailPojo" type="{http://service.qrc.crm.tele.np.com/}crmNationalityDetailsPojo" minOccurs="0"/>
 *         &lt;element name="networkConfigurationsPojo" type="{http://service.qrc.crm.tele.np.com/}crmNetworkConfigurationsPojo" minOccurs="0"/>
 *         &lt;element name="newAddonPlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newBasePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noOfdays" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="oldAddonPlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldBasePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderDetailsPojo" type="{http://service.qrc.crm.tele.np.com/}crmOrderDetailsPojo" minOccurs="0"/>
 *         &lt;element name="planCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="planMasterPojo" type="{http://service.qrc.crm.tele.np.com/}crmPlanMasterPojo" minOccurs="0"/>
 *         &lt;element name="presentOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="presentStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcActionTakenPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcActionTakenPojo" minOccurs="0"/>
 *         &lt;element name="qrcActionTakenPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcActionTakenPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="remarksPojo" type="{http://service.qrc.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *         &lt;element name="remarksPojos" type="{http://service.qrc.crm.tele.np.com/}remarksPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rootCausePojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcRootCausePojo" minOccurs="0"/>
 *         &lt;element name="rootCausePojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcRootCausePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="selfcareSubjects" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="srTicketNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ticketHistory" type="{http://service.qrc.crm.tele.np.com/}crmTicketHistoryPojo" minOccurs="0"/>
 *         &lt;element name="ticketHistoryList" type="{http://service.qrc.crm.tele.np.com/}crmTicketHistoryPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="toDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageFormDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageToDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="validityExtensionPojo" type="{http://service.qrc.crm.tele.np.com/}validityExtensionPojo" minOccurs="0"/>
 *         &lt;element name="validityExtensionPojos" type="{http://service.qrc.crm.tele.np.com/}validityExtensionPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="webRequest" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmQrcDto", propOrder = {
    "activationTime",
    "activityAction",
    "addOnPlanPojo",
    "billCyclePojo",
    "billList",
    "billingErrorCode",
    "billingPlanRequestPojo",
    "billingPlanRequestPojos",
    "churnType",
    "clientIPAddress",
    "commonWorkflowPojos",
    "contentPojos",
    "crmAddressDetailsPojo",
    "crmBillCyclePojosList",
    "crmCustAssetDetailsPojo",
    "crmCustAssetDetailsPojos",
    "crmCustWaiverPojo",
    "crmCustWaiverPojosList",
    "crmInvoiceDetailsPojo",
    "crmPartnerNetworkConfigPojo",
    "crmPlanDetailsPojo",
    "crmQrcCategoriesPojo",
    "crmQrcCategoriesPojos",
    "crmQrcCategoryBinMappingPojo",
    "crmQrcCategoryBinMappingPojos",
    "crmQrcCommonPojo",
    "crmQrcSubCategoriesPojo",
    "crmQrcSubCategoriesPojos",
    "crmQrcSubCategoriesPojosList",
    "crmQrcSubSubCategoriesPojo",
    "crmQrcSubSubCategoriesPojos",
    "crmQrcWhitelistPojo",
    "crmQrcWhitelistPojoList",
    "crmRcaReasonPojos",
    "crmSelfcareCategoriesPojo",
    "crmSelfcareCategoriesPojos",
    "crmShiftingWorkflowPojo",
    "crmShiftingWorkflowPojos",
    "crmSrTicketsPojo",
    "crmSrTicketsPojos",
    "custAdditionalDetails",
    "custInteractionsPojo",
    "custInteractionsPojos",
    "custUsageDetailsPojos",
    "customerDetailsPojo",
    "customerDetailsPojos",
    "customerId",
    "customerRecordId",
    "customerUsageDetailsPojos",
    "documetDetailsPojo",
    "expiryDate",
    "failedCustomerMapList",
    "failedWhitelistCustomer",
    "fromDate",
    "futureOwner",
    "futureStage",
    "gracePeriodReason",
    "inboxSelected",
    "invoiceDetailsPojos",
    "macFaulty",
    "massOutagePojo",
    "massOutagePojos",
    "maxResultSize",
    "nationaltyDetailPojo",
    "networkConfigurationsPojo",
    "newAddonPlanCode",
    "newBasePlanCode",
    "noOfdays",
    "oldAddonPlanCode",
    "oldBasePlanCode",
    "orderDetailsPojo",
    "planCategory",
    "planMasterPojo",
    "presentOwner",
    "presentStage",
    "qrcActionTakenPojo",
    "qrcActionTakenPojos",
    "remarksPojo",
    "remarksPojos",
    "rootCausePojo",
    "rootCausePojos",
    "selfcareSubjects",
    "serverIPAddress",
    "serviceType",
    "srTicketNo",
    "statusCode",
    "statusDesc",
    "ticketHistory",
    "ticketHistoryList",
    "toDate",
    "usageFormDate",
    "usageToDate",
    "usageType",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userId",
    "validityExtensionPojo",
    "validityExtensionPojos",
    "webRequest",
    "addonAction"
})
public class CrmQrcDto {

    protected String activationTime;
    protected String activityAction;
    protected CrmPlanMasterPojo addOnPlanPojo;
    protected CrmBillCyclePojo billCyclePojo;
    @XmlElement(nillable = true)
    protected List<String> billList;
    protected String billingErrorCode;
    protected CrmBillingPlanRequestPojo billingPlanRequestPojo;
    @XmlElement(nillable = true)
    protected List<CrmBillingPlanRequestPojo> billingPlanRequestPojos;
    protected String churnType;
    protected String clientIPAddress;
    @XmlElement(nillable = true)
    protected List<CommonWorkflowPojo> commonWorkflowPojos;
    @XmlElement(nillable = true)
    protected List<ContentPojo> contentPojos;
    protected CrmAddressDetailsPojo crmAddressDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmBillCyclePojo> crmBillCyclePojosList;
    protected CrmCustAssetDetailsPojo crmCustAssetDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustAssetDetailsPojo> crmCustAssetDetailsPojos;
    protected CrmCustWaiverPojo crmCustWaiverPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustWaiverPojo> crmCustWaiverPojosList;
    protected CrmInvoiceDetailsPojo crmInvoiceDetailsPojo;
    protected CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo;
    protected CrmPlanDetailsPojo crmPlanDetailsPojo;
    protected CrmQrcCategoriesPojo crmQrcCategoriesPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcCategoriesPojo> crmQrcCategoriesPojos;
    protected CrmQrcCategoryBinMappingPojo crmQrcCategoryBinMappingPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcCategoryBinMappingPojo> crmQrcCategoryBinMappingPojos;
    protected CrmQrcCommonPojo crmQrcCommonPojo;
    protected CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcSubCategoriesPojo> crmQrcSubCategoriesPojos;
    @XmlElement(nillable = true)
    protected List<CrmQrcSubCategoriesPojo> crmQrcSubCategoriesPojosList;
    protected CrmQrcSubSubCategoriesPojo crmQrcSubSubCategoriesPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcSubSubCategoriesPojo> crmQrcSubSubCategoriesPojos;
    protected CrmQrcWhitelistPojo crmQrcWhitelistPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcWhitelistPojo> crmQrcWhitelistPojoList;
    @XmlElement(nillable = true)
    protected List<CrmRcaReasonPojo> crmRcaReasonPojos;
    protected CrmSelfcareCategoriesPojo crmSelfcareCategoriesPojo;
    @XmlElement(nillable = true)
    protected List<CrmSelfcareCategoriesPojo> crmSelfcareCategoriesPojos;
    protected CrmShiftingWorkflowPojo crmShiftingWorkflowPojo;
    @XmlElement(nillable = true)
    protected List<CrmShiftingWorkflowPojo> crmShiftingWorkflowPojos;
    protected CrmSrTicketsPojo crmSrTicketsPojo;
    @XmlElement(nillable = true)
    protected List<CrmSrTicketsPojo> crmSrTicketsPojos;
    protected CrmCustAdditionalDetails custAdditionalDetails;
    protected CrmCustInteractionsPojo custInteractionsPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustInteractionsPojo> custInteractionsPojos;
    @XmlElement(nillable = true)
    protected List<CustomerUsageDetailsPojo> custUsageDetailsPojos;
    protected CrmCustomerDetailsPojo customerDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> customerDetailsPojos;
    protected String customerId;
    protected long customerRecordId;
    @XmlElement(nillable = true)
    protected List<CustomerUsageDetailsPojo> customerUsageDetailsPojos;
    protected CrmDocumentDetailsPojo documetDetailsPojo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expiryDate;
    @XmlElement(required = true)
    protected CrmQrcDto.FailedCustomerMapList failedCustomerMapList;
    @XmlElement(nillable = true)
    protected List<String> failedWhitelistCustomer;
    protected String fromDate;
    protected String futureOwner;
    protected String futureStage;
    protected String gracePeriodReason;
    protected String inboxSelected;
    @XmlElement(nillable = true)
    protected List<CrmInvoiceDetailsPojo> invoiceDetailsPojos;
    protected boolean macFaulty;
    protected CrmMassOutagePojo massOutagePojo;
    @XmlElement(nillable = true)
    protected List<CrmMassOutagePojo> massOutagePojos;
    protected int maxResultSize;
    protected CrmNationalityDetailsPojo nationaltyDetailPojo;
    protected CrmNetworkConfigurationsPojo networkConfigurationsPojo;
    protected String newAddonPlanCode;
    protected String newBasePlanCode;
    protected int noOfdays;
    protected String oldAddonPlanCode;
    protected String oldBasePlanCode;
    protected CrmOrderDetailsPojo orderDetailsPojo;
    protected String planCategory;
    protected CrmPlanMasterPojo planMasterPojo;
    protected String presentOwner;
    protected String presentStage;
    protected CrmQrcActionTakenPojo qrcActionTakenPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcActionTakenPojo> qrcActionTakenPojos;
    protected RemarksPojo remarksPojo;
    @XmlElement(nillable = true)
    protected List<RemarksPojo> remarksPojos;
    protected CrmQrcRootCausePojo rootCausePojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcRootCausePojo> rootCausePojos;
    @XmlElement(nillable = true)
    protected List<String> selfcareSubjects;
    protected String serverIPAddress;
    protected String serviceType;
    protected String srTicketNo;
    protected String statusCode;
    protected String statusDesc;
    protected CrmTicketHistoryPojo ticketHistory;
    @XmlElement(nillable = true)
    protected List<CrmTicketHistoryPojo> ticketHistoryList;
    protected String toDate;
    protected String usageFormDate;
    protected String usageToDate;
    protected String usageType;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userId;
    protected ValidityExtensionPojo validityExtensionPojo;
    @XmlElement(nillable = true)
    protected List<ValidityExtensionPojo> validityExtensionPojos;
    protected boolean webRequest;
    protected String addonAction;

    /**
     * Gets the value of the activationTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivationTime() {
        return activationTime;
    }

    /**
     * Sets the value of the activationTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivationTime(String value) {
        this.activationTime = value;
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
     * Gets the value of the addOnPlanPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPlanMasterPojo }
     *     
     */
    public CrmPlanMasterPojo getAddOnPlanPojo() {
        return addOnPlanPojo;
    }

    /**
     * Sets the value of the addOnPlanPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPlanMasterPojo }
     *     
     */
    public void setAddOnPlanPojo(CrmPlanMasterPojo value) {
        this.addOnPlanPojo = value;
    }

    /**
     * Gets the value of the billCyclePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmBillCyclePojo }
     *     
     */
    public CrmBillCyclePojo getBillCyclePojo() {
        return billCyclePojo;
    }

    /**
     * Sets the value of the billCyclePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmBillCyclePojo }
     *     
     */
    public void setBillCyclePojo(CrmBillCyclePojo value) {
        this.billCyclePojo = value;
    }

    /**
     * Gets the value of the billList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getBillList() {
        if (billList == null) {
            billList = new ArrayList<String>();
        }
        return this.billList;
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
     * Gets the value of the billingPlanRequestPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmBillingPlanRequestPojo }
     *     
     */
    public CrmBillingPlanRequestPojo getBillingPlanRequestPojo() {
        return billingPlanRequestPojo;
    }

    /**
     * Sets the value of the billingPlanRequestPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmBillingPlanRequestPojo }
     *     
     */
    public void setBillingPlanRequestPojo(CrmBillingPlanRequestPojo value) {
        this.billingPlanRequestPojo = value;
    }

    /**
     * Gets the value of the billingPlanRequestPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billingPlanRequestPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingPlanRequestPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmBillingPlanRequestPojo }
     * 
     * 
     */
    public List<CrmBillingPlanRequestPojo> getBillingPlanRequestPojos() {
        if (billingPlanRequestPojos == null) {
            billingPlanRequestPojos = new ArrayList<CrmBillingPlanRequestPojo>();
        }
        return this.billingPlanRequestPojos;
    }

    /**
     * Gets the value of the churnType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChurnType() {
        return churnType;
    }

    /**
     * Sets the value of the churnType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChurnType(String value) {
        this.churnType = value;
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
     * Gets the value of the commonWorkflowPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commonWorkflowPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommonWorkflowPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CommonWorkflowPojo }
     * 
     * 
     */
    public List<CommonWorkflowPojo> getCommonWorkflowPojos() {
        if (commonWorkflowPojos == null) {
            commonWorkflowPojos = new ArrayList<CommonWorkflowPojo>();
        }
        return this.commonWorkflowPojos;
    }

    /**
     * Gets the value of the contentPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contentPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContentPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ContentPojo }
     * 
     * 
     */
    public List<ContentPojo> getContentPojos() {
        if (contentPojos == null) {
            contentPojos = new ArrayList<ContentPojo>();
        }
        return this.contentPojos;
    }

    /**
     * Gets the value of the crmAddressDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmAddressDetailsPojo }
     *     
     */
    public CrmAddressDetailsPojo getCrmAddressDetailsPojo() {
        return crmAddressDetailsPojo;
    }

    /**
     * Sets the value of the crmAddressDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmAddressDetailsPojo }
     *     
     */
    public void setCrmAddressDetailsPojo(CrmAddressDetailsPojo value) {
        this.crmAddressDetailsPojo = value;
    }

    /**
     * Gets the value of the crmBillCyclePojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmBillCyclePojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmBillCyclePojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmBillCyclePojo }
     * 
     * 
     */
    public List<CrmBillCyclePojo> getCrmBillCyclePojosList() {
        if (crmBillCyclePojosList == null) {
            crmBillCyclePojosList = new ArrayList<CrmBillCyclePojo>();
        }
        return this.crmBillCyclePojosList;
    }

    /**
     * Gets the value of the crmCustAssetDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustAssetDetailsPojo }
     *     
     */
    public CrmCustAssetDetailsPojo getCrmCustAssetDetailsPojo() {
        return crmCustAssetDetailsPojo;
    }

    /**
     * Sets the value of the crmCustAssetDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustAssetDetailsPojo }
     *     
     */
    public void setCrmCustAssetDetailsPojo(CrmCustAssetDetailsPojo value) {
        this.crmCustAssetDetailsPojo = value;
    }

    /**
     * Gets the value of the crmCustAssetDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCustAssetDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCustAssetDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustAssetDetailsPojo }
     * 
     * 
     */
    public List<CrmCustAssetDetailsPojo> getCrmCustAssetDetailsPojos() {
        if (crmCustAssetDetailsPojos == null) {
            crmCustAssetDetailsPojos = new ArrayList<CrmCustAssetDetailsPojo>();
        }
        return this.crmCustAssetDetailsPojos;
    }

    /**
     * Gets the value of the crmCustWaiverPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustWaiverPojo }
     *     
     */
    public CrmCustWaiverPojo getCrmCustWaiverPojo() {
        return crmCustWaiverPojo;
    }

    /**
     * Sets the value of the crmCustWaiverPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustWaiverPojo }
     *     
     */
    public void setCrmCustWaiverPojo(CrmCustWaiverPojo value) {
        this.crmCustWaiverPojo = value;
    }

    /**
     * Gets the value of the crmCustWaiverPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmCustWaiverPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmCustWaiverPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustWaiverPojo }
     * 
     * 
     */
    public List<CrmCustWaiverPojo> getCrmCustWaiverPojosList() {
        if (crmCustWaiverPojosList == null) {
            crmCustWaiverPojosList = new ArrayList<CrmCustWaiverPojo>();
        }
        return this.crmCustWaiverPojosList;
    }

    /**
     * Gets the value of the crmInvoiceDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmInvoiceDetailsPojo }
     *     
     */
    public CrmInvoiceDetailsPojo getCrmInvoiceDetailsPojo() {
        return crmInvoiceDetailsPojo;
    }

    /**
     * Sets the value of the crmInvoiceDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmInvoiceDetailsPojo }
     *     
     */
    public void setCrmInvoiceDetailsPojo(CrmInvoiceDetailsPojo value) {
        this.crmInvoiceDetailsPojo = value;
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
     * Gets the value of the crmPlanDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPlanDetailsPojo }
     *     
     */
    public CrmPlanDetailsPojo getCrmPlanDetailsPojo() {
        return crmPlanDetailsPojo;
    }

    /**
     * Sets the value of the crmPlanDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPlanDetailsPojo }
     *     
     */
    public void setCrmPlanDetailsPojo(CrmPlanDetailsPojo value) {
        this.crmPlanDetailsPojo = value;
    }

    /**
     * Gets the value of the crmQrcCategoriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcCategoriesPojo }
     *     
     */
    public CrmQrcCategoriesPojo getCrmQrcCategoriesPojo() {
        return crmQrcCategoriesPojo;
    }

    /**
     * Sets the value of the crmQrcCategoriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcCategoriesPojo }
     *     
     */
    public void setCrmQrcCategoriesPojo(CrmQrcCategoriesPojo value) {
        this.crmQrcCategoriesPojo = value;
    }

    /**
     * Gets the value of the crmQrcCategoriesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcCategoriesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcCategoriesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcCategoriesPojo }
     * 
     * 
     */
    public List<CrmQrcCategoriesPojo> getCrmQrcCategoriesPojos() {
        if (crmQrcCategoriesPojos == null) {
            crmQrcCategoriesPojos = new ArrayList<CrmQrcCategoriesPojo>();
        }
        return this.crmQrcCategoriesPojos;
    }

    /**
     * Gets the value of the crmQrcCategoryBinMappingPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcCategoryBinMappingPojo }
     *     
     */
    public CrmQrcCategoryBinMappingPojo getCrmQrcCategoryBinMappingPojo() {
        return crmQrcCategoryBinMappingPojo;
    }

    /**
     * Sets the value of the crmQrcCategoryBinMappingPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcCategoryBinMappingPojo }
     *     
     */
    public void setCrmQrcCategoryBinMappingPojo(CrmQrcCategoryBinMappingPojo value) {
        this.crmQrcCategoryBinMappingPojo = value;
    }

    /**
     * Gets the value of the crmQrcCategoryBinMappingPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcCategoryBinMappingPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcCategoryBinMappingPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcCategoryBinMappingPojo }
     * 
     * 
     */
    public List<CrmQrcCategoryBinMappingPojo> getCrmQrcCategoryBinMappingPojos() {
        if (crmQrcCategoryBinMappingPojos == null) {
            crmQrcCategoryBinMappingPojos = new ArrayList<CrmQrcCategoryBinMappingPojo>();
        }
        return this.crmQrcCategoryBinMappingPojos;
    }

    /**
     * Gets the value of the crmQrcCommonPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcCommonPojo }
     *     
     */
    public CrmQrcCommonPojo getCrmQrcCommonPojo() {
        return crmQrcCommonPojo;
    }

    /**
     * Sets the value of the crmQrcCommonPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcCommonPojo }
     *     
     */
    public void setCrmQrcCommonPojo(CrmQrcCommonPojo value) {
        this.crmQrcCommonPojo = value;
    }

    /**
     * Gets the value of the crmQrcSubCategoriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcSubCategoriesPojo }
     *     
     */
    public CrmQrcSubCategoriesPojo getCrmQrcSubCategoriesPojo() {
        return crmQrcSubCategoriesPojo;
    }

    /**
     * Sets the value of the crmQrcSubCategoriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcSubCategoriesPojo }
     *     
     */
    public void setCrmQrcSubCategoriesPojo(CrmQrcSubCategoriesPojo value) {
        this.crmQrcSubCategoriesPojo = value;
    }

    /**
     * Gets the value of the crmQrcSubCategoriesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcSubCategoriesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcSubCategoriesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcSubCategoriesPojo }
     * 
     * 
     */
    public List<CrmQrcSubCategoriesPojo> getCrmQrcSubCategoriesPojos() {
        if (crmQrcSubCategoriesPojos == null) {
            crmQrcSubCategoriesPojos = new ArrayList<CrmQrcSubCategoriesPojo>();
        }
        return this.crmQrcSubCategoriesPojos;
    }

    /**
     * Gets the value of the crmQrcSubCategoriesPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcSubCategoriesPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcSubCategoriesPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcSubCategoriesPojo }
     * 
     * 
     */
    public List<CrmQrcSubCategoriesPojo> getCrmQrcSubCategoriesPojosList() {
        if (crmQrcSubCategoriesPojosList == null) {
            crmQrcSubCategoriesPojosList = new ArrayList<CrmQrcSubCategoriesPojo>();
        }
        return this.crmQrcSubCategoriesPojosList;
    }

    /**
     * Gets the value of the crmQrcSubSubCategoriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcSubSubCategoriesPojo }
     *     
     */
    public CrmQrcSubSubCategoriesPojo getCrmQrcSubSubCategoriesPojo() {
        return crmQrcSubSubCategoriesPojo;
    }

    /**
     * Sets the value of the crmQrcSubSubCategoriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcSubSubCategoriesPojo }
     *     
     */
    public void setCrmQrcSubSubCategoriesPojo(CrmQrcSubSubCategoriesPojo value) {
        this.crmQrcSubSubCategoriesPojo = value;
    }

    /**
     * Gets the value of the crmQrcSubSubCategoriesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcSubSubCategoriesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcSubSubCategoriesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcSubSubCategoriesPojo }
     * 
     * 
     */
    public List<CrmQrcSubSubCategoriesPojo> getCrmQrcSubSubCategoriesPojos() {
        if (crmQrcSubSubCategoriesPojos == null) {
            crmQrcSubSubCategoriesPojos = new ArrayList<CrmQrcSubSubCategoriesPojo>();
        }
        return this.crmQrcSubSubCategoriesPojos;
    }

    /**
     * Gets the value of the crmQrcWhitelistPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcWhitelistPojo }
     *     
     */
    public CrmQrcWhitelistPojo getCrmQrcWhitelistPojo() {
        return crmQrcWhitelistPojo;
    }

    /**
     * Sets the value of the crmQrcWhitelistPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcWhitelistPojo }
     *     
     */
    public void setCrmQrcWhitelistPojo(CrmQrcWhitelistPojo value) {
        this.crmQrcWhitelistPojo = value;
    }

    /**
     * Gets the value of the crmQrcWhitelistPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcWhitelistPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcWhitelistPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcWhitelistPojo }
     * 
     * 
     */
    public List<CrmQrcWhitelistPojo> getCrmQrcWhitelistPojoList() {
        if (crmQrcWhitelistPojoList == null) {
            crmQrcWhitelistPojoList = new ArrayList<CrmQrcWhitelistPojo>();
        }
        return this.crmQrcWhitelistPojoList;
    }

    /**
     * Gets the value of the crmRcaReasonPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmRcaReasonPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmRcaReasonPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmRcaReasonPojo }
     * 
     * 
     */
    public List<CrmRcaReasonPojo> getCrmRcaReasonPojos() {
        if (crmRcaReasonPojos == null) {
            crmRcaReasonPojos = new ArrayList<CrmRcaReasonPojo>();
        }
        return this.crmRcaReasonPojos;
    }

    /**
     * Gets the value of the crmSelfcareCategoriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmSelfcareCategoriesPojo }
     *     
     */
    public CrmSelfcareCategoriesPojo getCrmSelfcareCategoriesPojo() {
        return crmSelfcareCategoriesPojo;
    }

    /**
     * Sets the value of the crmSelfcareCategoriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmSelfcareCategoriesPojo }
     *     
     */
    public void setCrmSelfcareCategoriesPojo(CrmSelfcareCategoriesPojo value) {
        this.crmSelfcareCategoriesPojo = value;
    }

    /**
     * Gets the value of the crmSelfcareCategoriesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmSelfcareCategoriesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmSelfcareCategoriesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmSelfcareCategoriesPojo }
     * 
     * 
     */
    public List<CrmSelfcareCategoriesPojo> getCrmSelfcareCategoriesPojos() {
        if (crmSelfcareCategoriesPojos == null) {
            crmSelfcareCategoriesPojos = new ArrayList<CrmSelfcareCategoriesPojo>();
        }
        return this.crmSelfcareCategoriesPojos;
    }

    /**
     * Gets the value of the crmShiftingWorkflowPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmShiftingWorkflowPojo }
     *     
     */
    public CrmShiftingWorkflowPojo getCrmShiftingWorkflowPojo() {
        return crmShiftingWorkflowPojo;
    }

    /**
     * Sets the value of the crmShiftingWorkflowPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmShiftingWorkflowPojo }
     *     
     */
    public void setCrmShiftingWorkflowPojo(CrmShiftingWorkflowPojo value) {
        this.crmShiftingWorkflowPojo = value;
    }

    /**
     * Gets the value of the crmShiftingWorkflowPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmShiftingWorkflowPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmShiftingWorkflowPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmShiftingWorkflowPojo }
     * 
     * 
     */
    public List<CrmShiftingWorkflowPojo> getCrmShiftingWorkflowPojos() {
        if (crmShiftingWorkflowPojos == null) {
            crmShiftingWorkflowPojos = new ArrayList<CrmShiftingWorkflowPojo>();
        }
        return this.crmShiftingWorkflowPojos;
    }

    /**
     * Gets the value of the crmSrTicketsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmSrTicketsPojo }
     *     
     */
    public CrmSrTicketsPojo getCrmSrTicketsPojo() {
        return crmSrTicketsPojo;
    }

    /**
     * Sets the value of the crmSrTicketsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmSrTicketsPojo }
     *     
     */
    public void setCrmSrTicketsPojo(CrmSrTicketsPojo value) {
        this.crmSrTicketsPojo = value;
    }

    /**
     * Gets the value of the crmSrTicketsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmSrTicketsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmSrTicketsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmSrTicketsPojo }
     * 
     * 
     */
    public List<CrmSrTicketsPojo> getCrmSrTicketsPojos() {
        if (crmSrTicketsPojos == null) {
            crmSrTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
        }
        return this.crmSrTicketsPojos;
    }

    /**
     * Gets the value of the custAdditionalDetails property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustAdditionalDetails }
     *     
     */
    public CrmCustAdditionalDetails getCustAdditionalDetails() {
        return custAdditionalDetails;
    }

    /**
     * Sets the value of the custAdditionalDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustAdditionalDetails }
     *     
     */
    public void setCustAdditionalDetails(CrmCustAdditionalDetails value) {
        this.custAdditionalDetails = value;
    }

    /**
     * Gets the value of the custInteractionsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustInteractionsPojo }
     *     
     */
    public CrmCustInteractionsPojo getCustInteractionsPojo() {
        return custInteractionsPojo;
    }

    /**
     * Sets the value of the custInteractionsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustInteractionsPojo }
     *     
     */
    public void setCustInteractionsPojo(CrmCustInteractionsPojo value) {
        this.custInteractionsPojo = value;
    }

    /**
     * Gets the value of the custInteractionsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custInteractionsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustInteractionsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustInteractionsPojo }
     * 
     * 
     */
    public List<CrmCustInteractionsPojo> getCustInteractionsPojos() {
        if (custInteractionsPojos == null) {
            custInteractionsPojos = new ArrayList<CrmCustInteractionsPojo>();
        }
        return this.custInteractionsPojos;
    }

    /**
     * Gets the value of the custUsageDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custUsageDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustUsageDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerUsageDetailsPojo }
     * 
     * 
     */
    public List<CustomerUsageDetailsPojo> getCustUsageDetailsPojos() {
        if (custUsageDetailsPojos == null) {
            custUsageDetailsPojos = new ArrayList<CustomerUsageDetailsPojo>();
        }
        return this.custUsageDetailsPojos;
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
     * Gets the value of the customerDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojos() {
        if (customerDetailsPojos == null) {
            customerDetailsPojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.customerDetailsPojos;
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
     * Gets the value of the customerUsageDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerUsageDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerUsageDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerUsageDetailsPojo }
     * 
     * 
     */
    public List<CustomerUsageDetailsPojo> getCustomerUsageDetailsPojos() {
        if (customerUsageDetailsPojos == null) {
            customerUsageDetailsPojos = new ArrayList<CustomerUsageDetailsPojo>();
        }
        return this.customerUsageDetailsPojos;
    }

    /**
     * Gets the value of the documetDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmDocumentDetailsPojo }
     *     
     */
    public CrmDocumentDetailsPojo getDocumetDetailsPojo() {
        return documetDetailsPojo;
    }

    /**
     * Sets the value of the documetDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmDocumentDetailsPojo }
     *     
     */
    public void setDocumetDetailsPojo(CrmDocumentDetailsPojo value) {
        this.documetDetailsPojo = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the failedCustomerMapList property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcDto.FailedCustomerMapList }
     *     
     */
    public CrmQrcDto.FailedCustomerMapList getFailedCustomerMapList() {
        return failedCustomerMapList;
    }

    /**
     * Sets the value of the failedCustomerMapList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcDto.FailedCustomerMapList }
     *     
     */
    public void setFailedCustomerMapList(CrmQrcDto.FailedCustomerMapList value) {
        this.failedCustomerMapList = value;
    }

    /**
     * Gets the value of the failedWhitelistCustomer property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the failedWhitelistCustomer property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFailedWhitelistCustomer().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFailedWhitelistCustomer() {
        if (failedWhitelistCustomer == null) {
            failedWhitelistCustomer = new ArrayList<String>();
        }
        return this.failedWhitelistCustomer;
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
     * Gets the value of the futureOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFutureOwner() {
        return futureOwner;
    }

    /**
     * Sets the value of the futureOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFutureOwner(String value) {
        this.futureOwner = value;
    }

    /**
     * Gets the value of the futureStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFutureStage() {
        return futureStage;
    }

    /**
     * Sets the value of the futureStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFutureStage(String value) {
        this.futureStage = value;
    }

    /**
     * Gets the value of the gracePeriodReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGracePeriodReason() {
        return gracePeriodReason;
    }

    /**
     * Sets the value of the gracePeriodReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGracePeriodReason(String value) {
        this.gracePeriodReason = value;
    }

    /**
     * Gets the value of the inboxSelected property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInboxSelected() {
        return inboxSelected;
    }

    /**
     * Sets the value of the inboxSelected property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInboxSelected(String value) {
        this.inboxSelected = value;
    }

    /**
     * Gets the value of the invoiceDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the invoiceDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInvoiceDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmInvoiceDetailsPojo }
     * 
     * 
     */
    public List<CrmInvoiceDetailsPojo> getInvoiceDetailsPojos() {
        if (invoiceDetailsPojos == null) {
            invoiceDetailsPojos = new ArrayList<CrmInvoiceDetailsPojo>();
        }
        return this.invoiceDetailsPojos;
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
     * Gets the value of the massOutagePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutagePojo }
     *     
     */
    public CrmMassOutagePojo getMassOutagePojo() {
        return massOutagePojo;
    }

    /**
     * Sets the value of the massOutagePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutagePojo }
     *     
     */
    public void setMassOutagePojo(CrmMassOutagePojo value) {
        this.massOutagePojo = value;
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
     * Gets the value of the maxResultSize property.
     * 
     */
    public int getMaxResultSize() {
        return maxResultSize;
    }

    /**
     * Sets the value of the maxResultSize property.
     * 
     */
    public void setMaxResultSize(int value) {
        this.maxResultSize = value;
    }

    /**
     * Gets the value of the nationaltyDetailPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmNationalityDetailsPojo }
     *     
     */
    public CrmNationalityDetailsPojo getNationaltyDetailPojo() {
        return nationaltyDetailPojo;
    }

    /**
     * Sets the value of the nationaltyDetailPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmNationalityDetailsPojo }
     *     
     */
    public void setNationaltyDetailPojo(CrmNationalityDetailsPojo value) {
        this.nationaltyDetailPojo = value;
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
     * Gets the value of the newAddonPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewAddonPlanCode() {
        return newAddonPlanCode;
    }

    /**
     * Sets the value of the newAddonPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewAddonPlanCode(String value) {
        this.newAddonPlanCode = value;
    }

    /**
     * Gets the value of the newBasePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewBasePlanCode() {
        return newBasePlanCode;
    }

    /**
     * Sets the value of the newBasePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewBasePlanCode(String value) {
        this.newBasePlanCode = value;
    }

    /**
     * Gets the value of the noOfdays property.
     * 
     */
    public int getNoOfdays() {
        return noOfdays;
    }

    /**
     * Sets the value of the noOfdays property.
     * 
     */
    public void setNoOfdays(int value) {
        this.noOfdays = value;
    }

    /**
     * Gets the value of the oldAddonPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldAddonPlanCode() {
        return oldAddonPlanCode;
    }

    /**
     * Sets the value of the oldAddonPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldAddonPlanCode(String value) {
        this.oldAddonPlanCode = value;
    }

    /**
     * Gets the value of the oldBasePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldBasePlanCode() {
        return oldBasePlanCode;
    }

    /**
     * Sets the value of the oldBasePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldBasePlanCode(String value) {
        this.oldBasePlanCode = value;
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
     * Gets the value of the planCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanCategory() {
        return planCategory;
    }

    /**
     * Sets the value of the planCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanCategory(String value) {
        this.planCategory = value;
    }

    /**
     * Gets the value of the planMasterPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmPlanMasterPojo }
     *     
     */
    public CrmPlanMasterPojo getPlanMasterPojo() {
        return planMasterPojo;
    }

    /**
     * Sets the value of the planMasterPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmPlanMasterPojo }
     *     
     */
    public void setPlanMasterPojo(CrmPlanMasterPojo value) {
        this.planMasterPojo = value;
    }

    /**
     * Gets the value of the presentOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentOwner() {
        return presentOwner;
    }

    /**
     * Sets the value of the presentOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentOwner(String value) {
        this.presentOwner = value;
    }

    /**
     * Gets the value of the presentStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPresentStage() {
        return presentStage;
    }

    /**
     * Sets the value of the presentStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPresentStage(String value) {
        this.presentStage = value;
    }

    /**
     * Gets the value of the qrcActionTakenPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcActionTakenPojo }
     *     
     */
    public CrmQrcActionTakenPojo getQrcActionTakenPojo() {
        return qrcActionTakenPojo;
    }

    /**
     * Sets the value of the qrcActionTakenPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcActionTakenPojo }
     *     
     */
    public void setQrcActionTakenPojo(CrmQrcActionTakenPojo value) {
        this.qrcActionTakenPojo = value;
    }

    /**
     * Gets the value of the qrcActionTakenPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcActionTakenPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcActionTakenPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcActionTakenPojo }
     * 
     * 
     */
    public List<CrmQrcActionTakenPojo> getQrcActionTakenPojos() {
        if (qrcActionTakenPojos == null) {
            qrcActionTakenPojos = new ArrayList<CrmQrcActionTakenPojo>();
        }
        return this.qrcActionTakenPojos;
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
     * Gets the value of the rootCausePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcRootCausePojo }
     *     
     */
    public CrmQrcRootCausePojo getRootCausePojo() {
        return rootCausePojo;
    }

    /**
     * Sets the value of the rootCausePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcRootCausePojo }
     *     
     */
    public void setRootCausePojo(CrmQrcRootCausePojo value) {
        this.rootCausePojo = value;
    }

    /**
     * Gets the value of the rootCausePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rootCausePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRootCausePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcRootCausePojo }
     * 
     * 
     */
    public List<CrmQrcRootCausePojo> getRootCausePojos() {
        if (rootCausePojos == null) {
            rootCausePojos = new ArrayList<CrmQrcRootCausePojo>();
        }
        return this.rootCausePojos;
    }

    /**
     * Gets the value of the selfcareSubjects property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selfcareSubjects property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelfcareSubjects().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getSelfcareSubjects() {
        if (selfcareSubjects == null) {
            selfcareSubjects = new ArrayList<String>();
        }
        return this.selfcareSubjects;
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
     * Gets the value of the srTicketNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrTicketNo() {
        return srTicketNo;
    }

    /**
     * Sets the value of the srTicketNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrTicketNo(String value) {
        this.srTicketNo = value;
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
     * Gets the value of the ticketHistoryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ticketHistoryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTicketHistoryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmTicketHistoryPojo }
     * 
     * 
     */
    public List<CrmTicketHistoryPojo> getTicketHistoryList() {
        if (ticketHistoryList == null) {
            ticketHistoryList = new ArrayList<CrmTicketHistoryPojo>();
        }
        return this.ticketHistoryList;
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
     * Gets the value of the usageFormDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageFormDate() {
        return usageFormDate;
    }

    /**
     * Sets the value of the usageFormDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageFormDate(String value) {
        this.usageFormDate = value;
    }

    /**
     * Gets the value of the usageToDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageToDate() {
        return usageToDate;
    }

    /**
     * Sets the value of the usageToDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageToDate(String value) {
        this.usageToDate = value;
    }

    /**
     * Gets the value of the usageType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageType() {
        return usageType;
    }

    /**
     * Sets the value of the usageType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageType(String value) {
        this.usageType = value;
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

    /**
     * Gets the value of the validityExtensionPojo property.
     * 
     * @return
     *     possible object is
     *     {@link ValidityExtensionPojo }
     *     
     */
    public ValidityExtensionPojo getValidityExtensionPojo() {
        return validityExtensionPojo;
    }

    /**
     * Sets the value of the validityExtensionPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidityExtensionPojo }
     *     
     */
    public void setValidityExtensionPojo(ValidityExtensionPojo value) {
        this.validityExtensionPojo = value;
    }

    /**
     * Gets the value of the validityExtensionPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validityExtensionPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidityExtensionPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidityExtensionPojo }
     * 
     * 
     */
    public List<ValidityExtensionPojo> getValidityExtensionPojos() {
        if (validityExtensionPojos == null) {
            validityExtensionPojos = new ArrayList<ValidityExtensionPojo>();
        }
        return this.validityExtensionPojos;
    }

    /**
     * Gets the value of the webRequest property.
     * 
     */
    public boolean isWebRequest() {
        return webRequest;
    }

    /**
     * Sets the value of the webRequest property.
     * 
     */
    public void setWebRequest(boolean value) {
        this.webRequest = value;
    }

    public String getAddonAction()
    {
        return addonAction;
    }

    public void setAddonAction( String inAddonAction )
    {
        addonAction = inAddonAction;
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
    public static class FailedCustomerMapList {

        protected List<CrmQrcDto.FailedCustomerMapList.Entry> entry;

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
         * {@link CrmQrcDto.FailedCustomerMapList.Entry }
         * 
         * 
         */
        public List<CrmQrcDto.FailedCustomerMapList.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<CrmQrcDto.FailedCustomerMapList.Entry>();
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

}
