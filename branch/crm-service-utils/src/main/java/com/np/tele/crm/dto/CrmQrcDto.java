package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.np.tele.crm.ext.pojos.CommonWorkflowPojo;
import com.np.tele.crm.ext.pojos.CrmCustAdditionalDetails;
import com.np.tele.crm.ext.pojos.CustomerUsageDetailsPojo;
import com.np.tele.crm.ext.pojos.ValidityExtensionPojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmBillCyclePojo;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;
import com.np.tele.crm.pojos.CrmCustAssetDetailsPojo;
import com.np.tele.crm.pojos.CrmCustInteractionsPojo;
import com.np.tele.crm.pojos.CrmCustWaiverPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmDocumentDetailsPojo;
import com.np.tele.crm.pojos.CrmInvoiceDetailsPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.CrmNationalityDetailsPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmOrderDetailsPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.pojos.CrmQrcActionTakenPojo;
import com.np.tele.crm.pojos.CrmQrcCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.pojos.CrmQrcCommonPojo;
import com.np.tele.crm.pojos.CrmQrcRootCausePojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcWhitelistPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.pojos.CrmShiftingWorkflowPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class CrmQrcDto
    implements Serializable
{
    private static final long                  serialVersionUID              = 3854338618551365462L;
    private String                             statusCode                    = null;
    private String                             statusDesc                    = null;
    private String                             clientIPAddress               = null;
    private String                             serverIPAddress               = null;
    private CrmQrcCategoriesPojo               crmQrcCategoriesPojo          = null;
    private CrmQrcCategoryBinMappingPojo       crmQrcCategoryBinMappingPojo  = null;
    private CrmSrTicketsPojo                   crmSrTicketsPojo              = null;
    private List<CrmQrcCategoriesPojo>         crmQrcCategoriesPojos         = null;
    private List<CrmQrcSubCategoriesPojo>      crmQrcSubCategoriesPojos      = null;
    private List<CrmQrcSubSubCategoriesPojo>   crmQrcSubSubCategoriesPojos   = null;
    private List<CrmQrcCategoryBinMappingPojo> crmQrcCategoryBinMappingPojos = null;
    private List<CrmSrTicketsPojo>             CrmSrTicketsPojos             = null;
    private List<CrmBillingPlanRequestPojo>    billingPlanRequestPojos       = null;
    private CrmBillingPlanRequestPojo          billingPlanRequestPojo        = null;
    private long                               customerRecordId;
    private RemarksPojo                        remarksPojo                   = null;
    private List<RemarksPojo>                  remarksPojos                  = null;
    private CrmQrcSubCategoriesPojo            crmQrcSubCategoriesPojo;
    private CrmQrcSubSubCategoriesPojo         crmQrcSubSubCategoriesPojo;
    private List<CrmQrcSubCategoriesPojo>      crmQrcSubCategoriesPojosList;
    private String                             presentStage;
    private String                             presentOwner;
    private String                             futureStage;
    private String                             futureOwner;
    private CrmQrcWhitelistPojo                crmQrcWhitelistPojo;
    private List<CrmQrcWhitelistPojo>          crmQrcWhitelistPojoList;
    private String                             userId;
    private List<String>                       failedWhitelistCustomer;
    private String                             srTicketNo;
    private CrmQrcCommonPojo                   crmQrcCommonPojo;
    private CrmQrcActionTakenPojo              qrcActionTakenPojo;
    private List<CrmQrcActionTakenPojo>        qrcActionTakenPojos;
    private CrmQrcRootCausePojo                rootCausePojo;
    private List<CrmQrcRootCausePojo>          rootCausePojos;
    private CrmCustomerDetailsPojo             customerDetailsPojo;
    private List<CrmCustInteractionsPojo>      custInteractionsPojos;
    private CrmCustInteractionsPojo            custInteractionsPojo;
    private List<CrmMassOutagePojo>            massOutagePojos;
    private CrmMassOutagePojo                  massOutagePojo;
    private CrmCustWaiverPojo                  crmCustWaiverPojo;
    private List<CrmCustWaiverPojo>            crmCustWaiverPojosList;
    private String                             customerId;
    private CrmAddressDetailsPojo              crmAddressDetailsPojo;
    private Map<String, String>                failedCustomerMapList;
    private CrmNetworkConfigurationsPojo       networkConfigurationsPojo;
    private CrmOrderDetailsPojo                orderDetailsPojo;
    private String                             activityAction;
    private String                             billingErrorCode              = null;
    private CrmCustAssetDetailsPojo            crmCustAssetDetailsPojo;
    private List<CrmCustAssetDetailsPojo>      crmCustAssetDetailsPojos;
    private CrmPlanMasterPojo                  planMasterPojo;
    private CrmCustAdditionalDetails           custAdditionalDetails;
    private List<CrmInvoiceDetailsPojo>        invoiceDetailsPojos;
    private CrmInvoiceDetailsPojo              crmInvoiceDetailsPojo;
    private CrmPlanDetailsPojo                 crmPlanDetailsPojo;
    private String                             activationTime;
    private CrmPlanMasterPojo                  addOnPlanPojo;
    private List<String>                       selfcareSubjects;
    private CrmSelfcareCategoriesPojo          crmSelfcareCategoriesPojo;
    private List<CrmSelfcareCategoriesPojo>    crmSelfcareCategoriesPojos;
    private CrmPartnerNetworkConfigPojo        crmPartnerNetworkConfigPojo;
    private List<String>                       billList;
    private List<ContentPojo>                  contentPojos;
    private String                             oldBasePlanCode;
    private String                             newBasePlanCode;
    private String                             oldAddonPlanCode;
    private String                             newAddonPlanCode;
    private String                             serviceType;
    private String                             usageFormDate;
    private String                             usageToDate;
    private List<CustomerUsageDetailsPojo>     customerUsageDetailsPojos;
    private String                             usageType;
    private int                                maxResultSize;
    private String                             planCategory;
    private Date                               expiryDate;
    private List<CrmCustomerDetailsPojo>       customerDetailsPojos;
    private CrmNationalityDetailsPojo          nationaltyDetailPojo;
    private CrmDocumentDetailsPojo             documetDetailsPojo;
    private CrmShiftingWorkflowPojo            crmShiftingWorkflowPojo       = null;
    private List<CrmShiftingWorkflowPojo>      crmShiftingWorkflowPojos;
    private List<CommonWorkflowPojo>           commonWorkflowPojos;
    private List<CrmRcaReasonPojo>             crmRcaReasonPojos             = null;
    private List<String>                       userAssociatedServices;
    private List<String>                       userAssociatedPartners;
    private List<CrmBillCyclePojo>             crmBillCyclePojosList;
    private int                                noOfdays;
    private String                             gracePeriodReason;
    private CrmBillCyclePojo                   billCyclePojo;
    private String                             toDate;
    private String                             fromDate;
    private CrmTicketHistoryPojo               ticketHistory                 = null;
    private List<CrmTicketHistoryPojo>         ticketHistoryList             = null;
    private boolean                            macFaulty;
    private List<CustomerUsageDetailsPojo>     custUsageDetailsPojos;
    private String                             churnType;
    boolean                                    webRequest;
    private String                             inboxSelected;
    private List<ValidityExtensionPojo>        validityExtensionPojos;
    private ValidityExtensionPojo              validityExtensionPojo;
    private String                             addonAction;

    public boolean isWebRequest()
    {
        return webRequest;
    }

    public void setWebRequest( boolean webRequest )
    {
        this.webRequest = webRequest;
    }

    public CrmBillCyclePojo getBillCyclePojo()
    {
        return billCyclePojo;
    }

    public void setBillCyclePojo( CrmBillCyclePojo billCyclePojo )
    {
        this.billCyclePojo = billCyclePojo;
    }

    public String getGracePeriodReason()
    {
        return gracePeriodReason;
    }

    public void setGracePeriodReason( String gracePeriodReason )
    {
        this.gracePeriodReason = gracePeriodReason;
    }

    public int getNoOfdays()
    {
        return noOfdays;
    }

    public void setNoOfdays( int inNoOfdays )
    {
        noOfdays = inNoOfdays;
    }

    public List<String> getUserAssociatedServices()
    {
        return userAssociatedServices;
    }

    public void setUserAssociatedServices( List<String> inUserAssociatedServices )
    {
        userAssociatedServices = inUserAssociatedServices;
    }

    public List<String> getUserAssociatedPartners()
    {
        return userAssociatedPartners;
    }

    public void setUserAssociatedPartners( List<String> inUserAssociatedPartners )
    {
        userAssociatedPartners = inUserAssociatedPartners;
    }

    public CrmInvoiceDetailsPojo getCrmInvoiceDetailsPojo()
    {
        return crmInvoiceDetailsPojo;
    }

    public void setCrmInvoiceDetailsPojo( CrmInvoiceDetailsPojo inCrmInvoiceDetailsPojo )
    {
        crmInvoiceDetailsPojo = inCrmInvoiceDetailsPojo;
    }

    public int getMaxResultSize()
    {
        return maxResultSize;
    }

    public void setMaxResultSize( int inMaxResultSize )
    {
        maxResultSize = inMaxResultSize;
    }

    public String getUsageType()
    {
        return usageType;
    }

    public void setUsageType( String inUsageType )
    {
        usageType = inUsageType;
    }

    public String getUsageFormDate()
    {
        return usageFormDate;
    }

    public void setUsageFormDate( String inUsageFormDate )
    {
        usageFormDate = inUsageFormDate;
    }

    public String getUsageToDate()
    {
        return usageToDate;
    }

    public void setUsageToDate( String inUsageToDate )
    {
        usageToDate = inUsageToDate;
    }

    public List<CustomerUsageDetailsPojo> getCustomerUsageDetailsPojos()
    {
        return customerUsageDetailsPojos;
    }

    public void setCustomerUsageDetailsPojos( List<CustomerUsageDetailsPojo> inCustomerUsageDetailsPojos )
    {
        customerUsageDetailsPojos = inCustomerUsageDetailsPojos;
    }

    public List<CrmMassOutagePojo> getMassOutagePojos()
    {
        return massOutagePojos;
    }

    public void setMassOutagePojos( List<CrmMassOutagePojo> inMassOutagePojos )
    {
        massOutagePojos = inMassOutagePojos;
    }

    public CrmMassOutagePojo getMassOutagePojo()
    {
        return massOutagePojo;
    }

    public void setMassOutagePojo( CrmMassOutagePojo inMassOutagePojo )
    {
        massOutagePojo = inMassOutagePojo;
    }

    public List<CrmCustInteractionsPojo> getCustInteractionsPojos()
    {
        return custInteractionsPojos;
    }

    public void setCustInteractionsPojos( List<CrmCustInteractionsPojo> inCustInteractionsPojos )
    {
        custInteractionsPojos = inCustInteractionsPojos;
    }

    public CrmCustInteractionsPojo getCustInteractionsPojo()
    {
        return custInteractionsPojo;
    }

    public void setCustInteractionsPojo( CrmCustInteractionsPojo inCustInteractionsPojo )
    {
        custInteractionsPojo = inCustInteractionsPojo;
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }

    public CrmQrcActionTakenPojo getQrcActionTakenPojo()
    {
        return qrcActionTakenPojo;
    }

    public void setQrcActionTakenPojo( CrmQrcActionTakenPojo inQrcRcaPojo )
    {
        qrcActionTakenPojo = inQrcRcaPojo;
    }

    public CrmQrcRootCausePojo getRootCausePojo()
    {
        return rootCausePojo;
    }

    public void setRootCausePojo( CrmQrcRootCausePojo inResolutionCodePojo )
    {
        rootCausePojo = inResolutionCodePojo;
    }

    public List<CrmQrcActionTakenPojo> getQrcActionTakenPojos()
    {
        return qrcActionTakenPojos;
    }

    public void setQrcActionTakenPojos( List<CrmQrcActionTakenPojo> inQrcRcaPojos )
    {
        qrcActionTakenPojos = inQrcRcaPojos;
    }

    public List<CrmQrcRootCausePojo> getRootCausePojos()
    {
        return rootCausePojos;
    }

    public void setRootCausePojos( List<CrmQrcRootCausePojo> inResolutionCodePojos )
    {
        rootCausePojos = inResolutionCodePojos;
    }

    public String getPresentStage()
    {
        return presentStage;
    }

    public void setPresentStage( String inPresentStage )
    {
        presentStage = inPresentStage;
    }

    public String getPresentOwner()
    {
        return presentOwner;
    }

    public void setPresentOwner( String inPresentOwner )
    {
        presentOwner = inPresentOwner;
    }

    public String getFutureStage()
    {
        return futureStage;
    }

    public void setFutureStage( String inFutureStage )
    {
        futureStage = inFutureStage;
    }

    public String getFutureOwner()
    {
        return futureOwner;
    }

    public void setFutureOwner( String inFutureOwner )
    {
        futureOwner = inFutureOwner;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String statusCode )
    {
        this.statusCode = statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String statusDesc )
    {
        this.statusDesc = statusDesc;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }

    public CrmQrcCategoriesPojo getCrmQrcCategoriesPojo()
    {
        return crmQrcCategoriesPojo;
    }

    public void setCrmQrcCategoriesPojo( CrmQrcCategoriesPojo crmQrcCategoriesPojo )
    {
        this.crmQrcCategoriesPojo = crmQrcCategoriesPojo;
    }

    public CrmQrcCategoryBinMappingPojo getCrmQrcCategoryBinMappingPojo()
    {
        return crmQrcCategoryBinMappingPojo;
    }

    public void setCrmQrcCategoryBinMappingPojo( CrmQrcCategoryBinMappingPojo crmQrcCategoryBinMappingPojo )
    {
        this.crmQrcCategoryBinMappingPojo = crmQrcCategoryBinMappingPojo;
    }

    public CrmSrTicketsPojo getCrmSrTicketsPojo()
    {
        return crmSrTicketsPojo;
    }

    public void setCrmSrTicketsPojo( CrmSrTicketsPojo crmSrTicketsPojo )
    {
        this.crmSrTicketsPojo = crmSrTicketsPojo;
    }

    public List<CrmQrcCategoriesPojo> getCrmQrcCategoriesPojos()
    {
        return crmQrcCategoriesPojos;
    }

    public void setCrmQrcCategoriesPojos( List<CrmQrcCategoriesPojo> crmQrcCategoriesPojos )
    {
        this.crmQrcCategoriesPojos = crmQrcCategoriesPojos;
    }

    public List<CrmQrcCategoryBinMappingPojo> getCrmQrcCategoryBinMappingPojos()
    {
        return crmQrcCategoryBinMappingPojos;
    }

    public void setCrmQrcCategoryBinMappingPojos( List<CrmQrcCategoryBinMappingPojo> crmQrcCategoryBinMappingPojos )
    {
        this.crmQrcCategoryBinMappingPojos = crmQrcCategoryBinMappingPojos;
    }

    public List<CrmSrTicketsPojo> getCrmSrTicketsPojos()
    {
        return CrmSrTicketsPojos;
    }

    public void setCrmSrTicketsPojos( List<CrmSrTicketsPojo> crmSrTicketsPojos )
    {
        CrmSrTicketsPojos = crmSrTicketsPojos;
    }

    //    public long getCustomerRecordId()
    //    {
    //        return customerRecordId;
    //    }
    //
    //    public void setCustomerRecordId( long customerRecordId )
    //    {
    //        this.customerRecordId = customerRecordId;
    //    }
    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo remarksPojo )
    {
        this.remarksPojo = remarksPojo;
    }

    public List<RemarksPojo> getRemarksPojos()
    {
        return remarksPojos;
    }

    public void setRemarksPojos( List<RemarksPojo> remarksPojos )
    {
        this.remarksPojos = remarksPojos;
    }

    public CrmQrcSubCategoriesPojo getCrmQrcSubCategoriesPojo()
    {
        return crmQrcSubCategoriesPojo;
    }

    public void setCrmQrcSubCategoriesPojo( CrmQrcSubCategoriesPojo crmQrcSubCategoriesPojo )
    {
        this.crmQrcSubCategoriesPojo = crmQrcSubCategoriesPojo;
    }

    public CrmQrcSubSubCategoriesPojo getCrmQrcSubSubCategoriesPojo()
    {
        return crmQrcSubSubCategoriesPojo;
    }

    public void setCrmQrcSubSubCategoriesPojo( CrmQrcSubSubCategoriesPojo crmQrcSubSubCategoriesPojo )
    {
        this.crmQrcSubSubCategoriesPojo = crmQrcSubSubCategoriesPojo;
    }

    public List<CrmQrcSubCategoriesPojo> getCrmQrcSubCategoriesPojosList()
    {
        return crmQrcSubCategoriesPojosList;
    }

    public void setCrmQrcSubCategoriesPojosList( List<CrmQrcSubCategoriesPojo> crmQrcSubCategoriesPojosList )
    {
        this.crmQrcSubCategoriesPojosList = crmQrcSubCategoriesPojosList;
    }

    public CrmQrcWhitelistPojo getCrmQrcWhitelistPojo()
    {
        return crmQrcWhitelistPojo;
    }

    public void setCrmQrcWhitelistPojo( CrmQrcWhitelistPojo crmQrcWhitelistPojo )
    {
        this.crmQrcWhitelistPojo = crmQrcWhitelistPojo;
    }

    public List<CrmQrcWhitelistPojo> getCrmQrcWhitelistPojoList()
    {
        return crmQrcWhitelistPojoList;
    }

    public void setCrmQrcWhitelistPojoList( List<CrmQrcWhitelistPojo> crmQrcWhitelistPojoList )
    {
        this.crmQrcWhitelistPojoList = crmQrcWhitelistPojoList;
    }

    public List<String> getFailedWhitelistCustomer()
    {
        return failedWhitelistCustomer;
    }

    public void setFailedWhitelistCustomer( List<String> failedWhitelistCustomer )
    {
        this.failedWhitelistCustomer = failedWhitelistCustomer;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public String getSrTicketNo()
    {
        return srTicketNo;
    }

    public void setSrTicketNo( String srTicketNo )
    {
        this.srTicketNo = srTicketNo;
    }

    public CrmQrcCommonPojo getCrmQrcCommonPojo()
    {
        return crmQrcCommonPojo;
    }

    public void setCrmQrcCommonPojo( CrmQrcCommonPojo crmQrcCommonPojo )
    {
        this.crmQrcCommonPojo = crmQrcCommonPojo;
    }

    public long getCustomerRecordId()
    {
        return customerRecordId;
    }

    public void setCustomerRecordId( long customerRecordId )
    {
        this.customerRecordId = customerRecordId;
    }

    public CrmCustWaiverPojo getCrmCustWaiverPojo()
    {
        return crmCustWaiverPojo;
    }

    public void setCrmCustWaiverPojo( CrmCustWaiverPojo crmCustWaiverPojo )
    {
        this.crmCustWaiverPojo = crmCustWaiverPojo;
    }

    public List<CrmCustWaiverPojo> getCrmCustWaiverPojosList()
    {
        return crmCustWaiverPojosList;
    }

    public void setCrmCustWaiverPojosList( List<CrmCustWaiverPojo> crmCustWaiverPojosList )
    {
        this.crmCustWaiverPojosList = crmCustWaiverPojosList;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public CrmAddressDetailsPojo getCrmAddressDetailsPojo()
    {
        return crmAddressDetailsPojo;
    }

    public void setCrmAddressDetailsPojo( CrmAddressDetailsPojo crmAddressDetailsPojo )
    {
        this.crmAddressDetailsPojo = crmAddressDetailsPojo;
    }

    public Map<String, String> getFailedCustomerMapList()
    {
        return failedCustomerMapList;
    }

    public void setFailedCustomerMapList( Map<String, String> failedCustomerMapList )
    {
        this.failedCustomerMapList = failedCustomerMapList;
    }

    public CrmNetworkConfigurationsPojo getNetworkConfigurationsPojo()
    {
        return networkConfigurationsPojo;
    }

    public void setNetworkConfigurationsPojo( CrmNetworkConfigurationsPojo networkConfigurationsPojo )
    {
        this.networkConfigurationsPojo = networkConfigurationsPojo;
    }

    public String getActivityAction()
    {
        return activityAction;
    }

    public void setActivityAction( String activityAction )
    {
        this.activityAction = activityAction;
    }

    public CrmOrderDetailsPojo getOrderDetailsPojo()
    {
        return orderDetailsPojo;
    }

    public void setOrderDetailsPojo( CrmOrderDetailsPojo orderDetailsPojo )
    {
        this.orderDetailsPojo = orderDetailsPojo;
    }

    public String getBillingErrorCode()
    {
        return billingErrorCode;
    }

    public void setBillingErrorCode( String billingErrorCode )
    {
        this.billingErrorCode = billingErrorCode;
    }

    public List<CrmQrcSubCategoriesPojo> getCrmQrcSubCategoriesPojos()
    {
        return crmQrcSubCategoriesPojos;
    }

    public void setCrmQrcSubCategoriesPojos( List<CrmQrcSubCategoriesPojo> crmQrcSubCategoriesPojos )
    {
        this.crmQrcSubCategoriesPojos = crmQrcSubCategoriesPojos;
    }

    public List<CrmQrcSubSubCategoriesPojo> getCrmQrcSubSubCategoriesPojos()
    {
        return crmQrcSubSubCategoriesPojos;
    }

    public void setCrmQrcSubSubCategoriesPojos( List<CrmQrcSubSubCategoriesPojo> crmQrcSubSubCategoriesPojos )
    {
        this.crmQrcSubSubCategoriesPojos = crmQrcSubSubCategoriesPojos;
    }

    public CrmCustAssetDetailsPojo getCrmCustAssetDetailsPojo()
    {
        return crmCustAssetDetailsPojo;
    }

    public void setCrmCustAssetDetailsPojo( CrmCustAssetDetailsPojo crmCustAssetDetailsPojo )
    {
        this.crmCustAssetDetailsPojo = crmCustAssetDetailsPojo;
    }

    public List<CrmCustAssetDetailsPojo> getCrmCustAssetDetailsPojos()
    {
        return crmCustAssetDetailsPojos;
    }

    public void setCrmCustAssetDetailsPojos( List<CrmCustAssetDetailsPojo> crmCustAssetDetailsPojos )
    {
        this.crmCustAssetDetailsPojos = crmCustAssetDetailsPojos;
    }

    public CrmPlanMasterPojo getPlanMasterPojo()
    {
        return planMasterPojo;
    }

    public void setPlanMasterPojo( CrmPlanMasterPojo planMasterPojo )
    {
        this.planMasterPojo = planMasterPojo;
    }

    public CrmCustAdditionalDetails getCustAdditionalDetails()
    {
        return custAdditionalDetails;
    }

    public void setCustAdditionalDetails( CrmCustAdditionalDetails inCustAdditionalDetails )
    {
        custAdditionalDetails = inCustAdditionalDetails;
    }

    public List<CrmInvoiceDetailsPojo> getInvoiceDetailsPojos()
    {
        return invoiceDetailsPojos;
    }

    public void setInvoiceDetailsPojos( List<CrmInvoiceDetailsPojo> inInvoiceDetailsPojos )
    {
        invoiceDetailsPojos = inInvoiceDetailsPojos;
    }

    public CrmPlanDetailsPojo getCrmPlanDetailsPojo()
    {
        return crmPlanDetailsPojo;
    }

    public void setCrmPlanDetailsPojo( CrmPlanDetailsPojo crmPlanDetailsPojo )
    {
        this.crmPlanDetailsPojo = crmPlanDetailsPojo;
    }

    public String getActivationTime()
    {
        return activationTime;
    }

    public void setActivationTime( String activationTime )
    {
        this.activationTime = activationTime;
    }

    public CrmPlanMasterPojo getAddOnPlanPojo()
    {
        return addOnPlanPojo;
    }

    public void setAddOnPlanPojo( CrmPlanMasterPojo addOnPlanPojo )
    {
        this.addOnPlanPojo = addOnPlanPojo;
    }

    public List<String> getSelfcareSubjects()
    {
        return selfcareSubjects;
    }

    public void setSelfcareSubjects( List<String> inSelfcareSubjects )
    {
        selfcareSubjects = inSelfcareSubjects;
    }

    public CrmSelfcareCategoriesPojo getCrmSelfcareCategoriesPojo()
    {
        return crmSelfcareCategoriesPojo;
    }

    public void setCrmSelfcareCategoriesPojo( CrmSelfcareCategoriesPojo inCrmSelfcareCategoriesPojo )
    {
        crmSelfcareCategoriesPojo = inCrmSelfcareCategoriesPojo;
    }

    public List<CrmSelfcareCategoriesPojo> getCrmSelfcareCategoriesPojos()
    {
        return crmSelfcareCategoriesPojos;
    }

    public void setCrmSelfcareCategoriesPojos( List<CrmSelfcareCategoriesPojo> inCrmSelfcareCategoriesPojos )
    {
        crmSelfcareCategoriesPojos = inCrmSelfcareCategoriesPojos;
    }

    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfigPojo()
    {
        return crmPartnerNetworkConfigPojo;
    }

    public void setCrmPartnerNetworkConfigPojo( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo )
    {
        this.crmPartnerNetworkConfigPojo = crmPartnerNetworkConfigPojo;
    }

    public List<String> getBillList()
    {
        return billList;
    }

    public void setBillList( List<String> billList )
    {
        this.billList = billList;
    }

    public List<ContentPojo> getContentPojos()
    {
        return contentPojos;
    }

    public void setContentPojos( List<ContentPojo> contentPojos )
    {
        this.contentPojos = contentPojos;
    }

    public String getOldBasePlanCode()
    {
        return oldBasePlanCode;
    }

    public void setOldBasePlanCode( String oldBasePlanCode )
    {
        this.oldBasePlanCode = oldBasePlanCode;
    }

    public String getNewBasePlanCode()
    {
        return newBasePlanCode;
    }

    public void setNewBasePlanCode( String newBasePlanCode )
    {
        this.newBasePlanCode = newBasePlanCode;
    }

    public String getOldAddonPlanCode()
    {
        return oldAddonPlanCode;
    }

    public void setOldAddonPlanCode( String oldAddonPlanCode )
    {
        this.oldAddonPlanCode = oldAddonPlanCode;
    }

    public String getNewAddonPlanCode()
    {
        return newAddonPlanCode;
    }

    public void setNewAddonPlanCode( String newAddonPlanCode )
    {
        this.newAddonPlanCode = newAddonPlanCode;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType( String serviceType )
    {
        this.serviceType = serviceType;
    }

    public String getPlanCategory()
    {
        return planCategory;
    }

    public void setPlanCategory( String planCategory )
    {
        this.planCategory = planCategory;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate( Date expiryDate )
    {
        this.expiryDate = expiryDate;
    }

    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojos()
    {
        return customerDetailsPojos;
    }

    public void setCustomerDetailsPojos( List<CrmCustomerDetailsPojo> inCustomerDetailsPojos )
    {
        customerDetailsPojos = inCustomerDetailsPojos;
    }

    public CrmNationalityDetailsPojo getNationaltyDetailPojo()
    {
        return nationaltyDetailPojo;
    }

    public void setNationaltyDetailPojo( CrmNationalityDetailsPojo nationaltyDetailPojo )
    {
        this.nationaltyDetailPojo = nationaltyDetailPojo;
    }

    public CrmDocumentDetailsPojo getDocumetDetailsPojo()
    {
        return documetDetailsPojo;
    }

    public void setDocumetDetailsPojo( CrmDocumentDetailsPojo documetDetailsPojo )
    {
        this.documetDetailsPojo = documetDetailsPojo;
    }

    public CrmShiftingWorkflowPojo getCrmShiftingWorkflowPojo()
    {
        return crmShiftingWorkflowPojo;
    }

    public void setCrmShiftingWorkflowPojo( CrmShiftingWorkflowPojo inCrmShiftingWorkflowPojo )
    {
        crmShiftingWorkflowPojo = inCrmShiftingWorkflowPojo;
    }

    public List<CommonWorkflowPojo> getCommonWorkflowPojos()
    {
        return commonWorkflowPojos;
    }

    public void setCommonWorkflowPojos( List<CommonWorkflowPojo> commonWorkflowPojos )
    {
        this.commonWorkflowPojos = commonWorkflowPojos;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasonPojos()
    {
        return crmRcaReasonPojos;
    }

    public void setCrmRcaReasonPojos( List<CrmRcaReasonPojo> inCrmRcaReasonPojos )
    {
        crmRcaReasonPojos = inCrmRcaReasonPojos;
    }

    public List<CrmShiftingWorkflowPojo> getCrmShiftingWorkflowPojos()
    {
        return crmShiftingWorkflowPojos;
    }

    public void setCrmShiftingWorkflowPojos( List<CrmShiftingWorkflowPojo> inCrmShiftingWorkflowPojos )
    {
        crmShiftingWorkflowPojos = inCrmShiftingWorkflowPojos;
    }

    public List<CrmBillCyclePojo> getCrmBillCyclePojosList()
    {
        return crmBillCyclePojosList;
    }

    public void setCrmBillCyclePojosList( List<CrmBillCyclePojo> crmBillCyclePojosList )
    {
        this.crmBillCyclePojosList = crmBillCyclePojosList;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate( String inToDate )
    {
        toDate = inToDate;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate( String inFromDate )
    {
        fromDate = inFromDate;
    }

    public List<CrmBillingPlanRequestPojo> getBillingPlanRequestPojos()
    {
        return billingPlanRequestPojos;
    }

    public CrmBillingPlanRequestPojo getBillingPlanRequestPojo()
    {
        return billingPlanRequestPojo;
    }

    public void setBillingPlanRequestPojos( List<CrmBillingPlanRequestPojo> inBillingPlanRequestPojos )
    {
        billingPlanRequestPojos = inBillingPlanRequestPojos;
    }

    public void setBillingPlanRequestPojo( CrmBillingPlanRequestPojo inBillingPlanRequestPojo )
    {
        billingPlanRequestPojo = inBillingPlanRequestPojo;
    }

    public CrmTicketHistoryPojo getTicketHistory()
    {
        return ticketHistory;
    }

    public void setTicketHistory( CrmTicketHistoryPojo inTicketHistory )
    {
        ticketHistory = inTicketHistory;
    }

    public List<CrmTicketHistoryPojo> getTicketHistoryList()
    {
        return ticketHistoryList;
    }

    public void setTicketHistoryList( List<CrmTicketHistoryPojo> inTicketHistoryList )
    {
        ticketHistoryList = inTicketHistoryList;
    }

    public boolean isMacFaulty()
    {
        return macFaulty;
    }

    public void setMacFaulty( boolean inMacFaulty )
    {
        macFaulty = inMacFaulty;
    }

    public List<CustomerUsageDetailsPojo> getCustUsageDetailsPojos()
    {
        return custUsageDetailsPojos;
    }

    public void setCustUsageDetailsPojos( List<CustomerUsageDetailsPojo> inCustUsageDetailsPojos )
    {
        custUsageDetailsPojos = inCustUsageDetailsPojos;
    }

    public String getChurnType()
    {
        return churnType;
    }

    public void setChurnType( String inChurnType )
    {
        churnType = inChurnType;
    }

    public String getInboxSelected()
    {
        return inboxSelected;
    }

    public void setInboxSelected( String inInboxSelected )
    {
        inboxSelected = inInboxSelected;
    }

    public List<ValidityExtensionPojo> getValidityExtensionPojos()
    {
        return validityExtensionPojos;
    }

    public void setValidityExtensionPojos( List<ValidityExtensionPojo> inValidityExtensionPojos )
    {
        validityExtensionPojos = inValidityExtensionPojos;
    }

    public ValidityExtensionPojo getValidityExtensionPojo()
    {
        return validityExtensionPojo;
    }

    public void setValidityExtensionPojo( ValidityExtensionPojo inValidityExtensionPojo )
    {
        validityExtensionPojo = inValidityExtensionPojo;
    }

    public String getAddonAction()
    {
        return addonAction;
    }

    public void setAddonAction( String inAddonAction )
    {
        addonAction = inAddonAction;
    }
}
