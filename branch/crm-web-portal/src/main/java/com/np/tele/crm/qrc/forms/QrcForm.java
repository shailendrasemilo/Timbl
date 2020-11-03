package com.np.tele.crm.qrc.forms;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.qrc.action.Device;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmBillCyclePojo;
import com.np.tele.crm.service.client.CrmCustAdditionalDetails;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustInteractionsPojo;
import com.np.tele.crm.service.client.CrmCustWaiverPojo;
import com.np.tele.crm.service.client.CrmCustomerActivityPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmDocumentDetailsPojo;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmOrderDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmShiftingWorkflowPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.StringUtils;

public class QrcForm
    extends
    ActionForm
{
    private static final long                 serialVersionUID           = 6060398559237048443L;
    private final static Logger               LOGGER                     = Logger.getLogger( QrcForm.class );
    private PartnerPojo                       partnerPojo                = new PartnerPojo();
    private boolean                           showDiv;
    private String                            networkType;
    private List<ContentPojo>                 productList;
    private String                            previousPartnerId;
    private String                            physicalInstallation;
    private String                            cpeAvailable;
    private String                            cpeMacId;
    private CrmQrcCategoriesPojo              qrcCategoriesPojo          = new CrmQrcCategoriesPojo();
    private RemarksPojo                       remarksPojo                = new RemarksPojo();
    private CrmCustomerDetailsPojo            custDetailsPojo            = new CrmCustomerDetailsPojo();
    private CrmAddressDetailsPojo             installationAddressPojo    = new CrmAddressDetailsPojo();
    private CrmAddressDetailsPojo             billingAddressPojo         = new CrmAddressDetailsPojo();
    private CrmOrderDetailsPojo               orderDetailsPojo           = new CrmOrderDetailsPojo();
    private CrmPlanDetailsPojo                planDetailsPojo            = new CrmPlanDetailsPojo();
    private CrmPlanDetailsPojo                oldplanDetailsPojo         = new CrmPlanDetailsPojo();
    private CrmNetworkConfigurationsPojo      networkConfigurationsPojo  = new CrmNetworkConfigurationsPojo();
    private CrmNationalityDetailsPojo         nationalityDetailsPojo     = new CrmNationalityDetailsPojo();
    private List<CrmCustomerActivityPojo>     crmCustomerActivityList;
    private List<CrmSrTicketsPojo>            srTicketsPojos;
    private List<ContentPojo>                 connectionTypes;
    private List<ContentPojo>                 billModes;
    private List<StatePojo>                   stateList;
    private List<CityPojo>                    cityList;
    private List<AreaPojo>                    areaList;
    private List<LocalityPojo>                localityList;
    private List<SocietyPojo>                 societyList;
    private String                            crmUserId;
    private String                            customerId                 = "";
    private String                            searchString               = "";
    private List<CrmQrcCategoriesPojo>        qrcCategoriesPojos;
    private List<CrmQrcSubCategoriesPojo>     qrcSubCategoriesPojos;
    private List<CrmQrcSubSubCategoriesPojo>  qrcSubSubCategoriesPojos;
    private CrmSrTicketsPojo                  srTicketPojo;
    private List<CrmRcaReasonPojo>            crmRcaReasonPojos;
    private CrmQrcWhitelistPojo               crmQrcWhitelistPojo;
    private String                            endDate;
    private String                            removeOption;
    private boolean                           inExceptionList;
    private long                              exceptionReason;
    private String                            srTicketNo;
    private CrmAddressDetailsPojo             newInstallationAddress;
    private String                            instAddrArea;
    private String                            instAddrLocality;
    private String                            instAddrSociety;
    private List<CrmRcaReasonPojo>            disconnectionReasons;
    // for bulk whitelist
    private FormFile                          whitelistExcelFile;
    private CrmQrcActionTakenPojo             crmQrcActionTaken          = new CrmQrcActionTakenPojo();
    private CrmQrcRootCausePojo               crmQrcRootCause            = new CrmQrcRootCausePojo();
    private List<CrmUserPojo>                 crmUserPojos;
    private String                            qrcActions;
    private String                            boolValue;
    private String                            presentStage;
    private String                            presentOwner;
    private String                            futureStage;
    private String                            futureOwner;
    private CrmMassOutagePojo                 massOutagePojo             = new CrmMassOutagePojo();
    private List<CrmCustInteractionsPojo>     custInteractionsPojos;
    public CrmCustInteractionsPojo            custInteractionsPojo       = new CrmCustInteractionsPojo();
    private List<ContentPojo>                 customerInteractionCategories;
    private CrmCustWaiverPojo                 custWaiverPojo             = new CrmCustWaiverPojo();
    private List<CrmCustWaiverPojo>           custWaiverPojos;
    private CrmPartnerNetworkConfigPojo       partnerNetworkConfigPojo   = new CrmPartnerNetworkConfigPojo();
    private String                            inType;
    private String                            newPrimaryMacId;
    private String                            newOption82;
    private List<String>                      secondaryMACAddrList;
    private String                            newSecondaryMacId;
    private long                              partnerId;
    private List<CrmPartnerNetworkConfigPojo> masterNameList;
    private List<ContentPojo>                 oltTypeList;
    private List<CrmCustAssetDetailsPojo>     crmCustAssetDetailsPojos;
    private CrmCustAssetDetailsPojo           crmCustAssetDetailsPojo    = new CrmCustAssetDetailsPojo();
    private List<CrmPlanMasterPojo>           planMasterList;
    private String                            planType;
    private String                            planUsageType;
    private String                            planCategory;
    private List<CrmPlanMasterPojo>           addonPlanMasterList;
    private CrmPlanMasterPojo                 crmPlanMasterPojo          = new CrmPlanMasterPojo();
    private CrmPlanMasterPojo                 crmAddonPlanMasterPojo     = new CrmPlanMasterPojo();
    private String                            activationTime;
    private String                            showDivValue;
    private List<CrmRcaReasonPojo>            interactionSubCategories;
    private List<ContentPojo>                 ticketActions;
    private CrmPaymentDetailsPojo             crmPaymentDetailsPojo;
    private List<CrmPaymentDetailsPojo>       custPaymentDetailsPojos;
    private String                            selectedPlanCode;
    private String                            addonPlanCode;
    private List<ContentPojo>                 rcaList;
    private List<ContentPojo>                 resoulationCodelist;
    private List<String>                      masterNames;
    private CrmPartnerNetworkConfigPojo       crmPartnerNetworkConfig    = new CrmPartnerNetworkConfigPojo();
    private CrmPartnerNetworkConfigPojo       newcrmPartnerNetworkConfig = new CrmPartnerNetworkConfigPojo();
    private List<CrmPlanMasterPojo>           vasToActivatePojos;
    private List<CrmPlanMasterPojo>           vasToDeactivatePojos;
    private List<CrmInvoiceDetailsPojo>       invoiceList;
    private String                            oldBasePlanCode;
    private String                            oldAddonPlanCode;
    private String                            activationType;
    private CrmInvoiceDetailsPojo             invoicePojo                = new CrmInvoiceDetailsPojo();
    private String                            usageFormDate;
    private String                            usageToDate;
    private String                            planCode;
    private CrmCustAdditionalDetails          custAdditionalDetails      = new CrmCustAdditionalDetails();
    private double                            usedPercentage             = 0;
    private double                            percentage                 = 1;
    private Long                              currentUsage               = 0l;
    private Long                              basePlanQuota              = 0l;
    private Long                              addonPlanQuota             = 0l;
    private Long                              totalQuota                 = 0l;
    private Long                              oldbasePlanQuota           = 0l;
    private Long                              oldaddonPlanQuota          = 0l;
    private Double                            oldtotalRenatal;
    private boolean                           visibileButton;
    private List<ContentPojo>                 customerStatusList;
    private String                            paramValue;
    private List<ContentPojo>                 visaList;
    private CrmDocumentDetailsPojo            documentDetailsPojo        = new CrmDocumentDetailsPojo();
    private CrmCustomerDetailsPojo            tempCustomerDetailsPojo    = new CrmCustomerDetailsPojo();
    private CrmNationalityDetailsPojo         tempNationalityDetailsPojo = new CrmNationalityDetailsPojo();
    private CrmDocumentDetailsPojo            tempDocumentDetailsPojo    = new CrmDocumentDetailsPojo();
    private CrmShiftingWorkflowPojo           shiftingWorkflowPojo       = new CrmShiftingWorkflowPojo();
    private CrmShiftingWorkflowPojo           oldshiftingWorkflowPojo    = new CrmShiftingWorkflowPojo();
    private List<PartnerPojo>                 partnerPojoList;
    private String[]                          partnerList;
    private String[]                          serviceList;
    private List<CrmRcaReasonPojo>            rejectionReasons;
    private List<ContentPojo>                 spList;
    private double                            miniTelesolutionAmount;
    private List<ContentPojo>                 paymentModes;
    private List<CrmRcaReasonPojo>            bankList;
    private String                            presentName;
    private List<CrmShiftingWorkflowPojo>     crmShiftingWorkflowPojos;
    private List<RemarksPojo>                 remarksPojoList;
    private List<CrmBillCyclePojo>            crmBillCyclePojosList;
    private List<ContentPojo>                 ticketStatusList;
    private String                            days;
    private String                            gracePeriodReason;
    private String                            oldCpe;
    private boolean                           addonAllowed;
    private CrmTicketHistoryPojo              ticketHistory              = new CrmTicketHistoryPojo();
    private String                            followupOn;
    private List<ContentPojo>                 actionTakens;
    private List<String>                      crmUsers;
    private boolean                           macFaulty;
    private String                            churnType;
    private String                            customerResponse;
    List<CrmRcaReasonPojo>                    closerReasonList;
    private String                            stateName;
    private String                            cityName;
    private String                            areaName;
    private String                            societyName;
    private LmsPojo                           lmsPojo                    = new LmsPojo();
    private String                            inboxSelected;
    private String                            nassPortId;
    private CrmAddressDetailsPojo             changeInstallationAddressPojo;
    private String                            changeInstArea;
    private String                            changeInnstSociety;
    private int                               stageIndex;
    private boolean                           nonEditableAtSC;
    private String                            feasibility;
    private String                            planTypeShifting;
    private CrmPaymentDetailsPojo             teleservicesPayment        = new CrmPaymentDetailsPojo();
    private List<CrmPlanMasterPojo>           basePlanNames;
    private List<CrmPlanMasterPojo>           addonPlanNames;
    private boolean                           addOnNotAllowed;
    private String                            planService;
    private String                            houseNumber;
    private double                            diffRentInclTax;
    private String                            addonAction;
    private String                            inventoryName;
    private String                            runningSession;
    private long                              subSubCategoryId;
    private Device                            device1Object;
    public Device getDevice1Object()
    {
        return device1Object;
    }

    public void setDevice1Object( Device device1Object )
    {
        this.device1Object = device1Object;
    }


    public long getSubSubCategoryId()
    {
        return subSubCategoryId;
    }

    public void setSubSubCategoryId( long subSubCategoryId )
    {
        this.subSubCategoryId = subSubCategoryId;
    }

    public String getRunningSession()
    {
        return runningSession;
    }

    public void setRunningSession( String runningSession )
    {
        this.runningSession = runningSession;
    }

    public String getInventoryName()
    {
        return inventoryName;
    }

    public void setInventoryName( String inventoryName )
    {
        this.inventoryName = inventoryName;
    }

    public List<String> getCrmUsers()
    {
        return crmUsers;
    }

    public void setCrmUsers( List<String> inCrmUsers )
    {
        crmUsers = inCrmUsers;
    }

    public List<ContentPojo> getActionTakens()
    {
        return actionTakens;
    }

    public void setActionTakens( List<ContentPojo> inActionTakens )
    {
        actionTakens = inActionTakens;
    }

    public String getGracePeriodReason()
    {
        return gracePeriodReason;
    }

    public void setGracePeriodReason( String inGracePeriodReason )
    {
        gracePeriodReason = inGracePeriodReason;
    }

    public String getDays()
    {
        return days;
    }

    public void setDays( String inDays )
    {
        days = inDays;
    }

    public List<ContentPojo> getTicketStatusList()
    {
        return ticketStatusList;
    }

    public void setTicketStatusList( List<ContentPojo> inTicketStatusList )
    {
        this.ticketStatusList = inTicketStatusList;
    }

    public String getPresentName()
    {
        return presentName;
    }

    public void setPresentName( String inPresentName )
    {
        presentName = inPresentName;
    }

    public CrmCustomerDetailsPojo getTempCustomerDetailsPojo()
    {
        return tempCustomerDetailsPojo;
    }

    public void setTempCustomerDetailsPojo( CrmCustomerDetailsPojo tempCustomerDetailsPojo )
    {
        this.tempCustomerDetailsPojo = tempCustomerDetailsPojo;
    }

    public CrmNationalityDetailsPojo getTempNationalityDetailsPojo()
    {
        return tempNationalityDetailsPojo;
    }

    public void setTempNationalityDetailsPojo( CrmNationalityDetailsPojo tempNationalityDetailsPojo )
    {
        this.tempNationalityDetailsPojo = tempNationalityDetailsPojo;
    }

    public CrmDocumentDetailsPojo getTempDocumentDetailsPojo()
    {
        return tempDocumentDetailsPojo;
    }

    public void setTempDocumentDetailsPojo( CrmDocumentDetailsPojo tempDocumentDetailsPojo )
    {
        this.tempDocumentDetailsPojo = tempDocumentDetailsPojo;
    }

    public CrmDocumentDetailsPojo getDocumentDetailsPojo()
    {
        return documentDetailsPojo;
    }

    public void setDocumentDetailsPojo( CrmDocumentDetailsPojo documentDetailsPojo )
    {
        this.documentDetailsPojo = documentDetailsPojo;
    }

    public String getPlanCode()
    {
        return planCode;
    }

    public void setPlanCode( String inPlanCode )
    {
        planCode = inPlanCode;
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

    public List<CrmPlanMasterPojo> getVasToActivatePojos()
    {
        return vasToActivatePojos;
    }

    public void setVasToActivatePojos( List<CrmPlanMasterPojo> inVasToActivatePojos )
    {
        vasToActivatePojos = inVasToActivatePojos;
    }

    public List<CrmPlanMasterPojo> getVasToDeactivatePojos()
    {
        return vasToDeactivatePojos;
    }

    public void setVasToDeactivatePojos( List<CrmPlanMasterPojo> inVasToDeactivatePojos )
    {
        vasToDeactivatePojos = inVasToDeactivatePojos;
    }

    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfig()
    {
        return crmPartnerNetworkConfig;
    }

    public void setCrmPartnerNetworkConfig( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfig )
    {
        this.crmPartnerNetworkConfig = crmPartnerNetworkConfig;
    }

    public List<String> getMasterNames()
    {
        return masterNames;
    }

    public void setMasterNames( List<String> masterNames )
    {
        this.masterNames = masterNames;
    }

    public List<ContentPojo> getRcaList()
    {
        return rcaList;
    }

    public void setRcaList( List<ContentPojo> rcaList )
    {
        this.rcaList = rcaList;
    }

    public List<ContentPojo> getResoulationCodelist()
    {
        return resoulationCodelist;
    }

    public void setResoulationCodelist( List<ContentPojo> resoulationCodelist )
    {
        this.resoulationCodelist = resoulationCodelist;
    }

    public List<ContentPojo> getTicketActions()
    {
        return ticketActions;
    }

    public void setTicketActions( List<ContentPojo> inTicketActions )
    {
        ticketActions = inTicketActions;
    }

    public List<CrmQrcSubCategoriesPojo> getQrcSubCategoriesPojos()
    {
        return qrcSubCategoriesPojos;
    }

    public void setQrcSubCategoriesPojos( List<CrmQrcSubCategoriesPojo> inQrcSubCategoriesPojos )
    {
        qrcSubCategoriesPojos = inQrcSubCategoriesPojos;
    }

    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategoriesPojos()
    {
        return qrcSubSubCategoriesPojos;
    }

    public void setQrcSubSubCategoriesPojos( List<CrmQrcSubSubCategoriesPojo> inQrcSubSubCategoriesPojos )
    {
        qrcSubSubCategoriesPojos = inQrcSubSubCategoriesPojos;
    }

    public List<CrmRcaReasonPojo> getInteractionSubCategories()
    {
        return interactionSubCategories;
    }

    public void setInteractionSubCategories( List<CrmRcaReasonPojo> inInteractionSubCategories )
    {
        interactionSubCategories = inInteractionSubCategories;
    }

    public List<ContentPojo> getCustomerInteractionCategories()
    {
        return customerInteractionCategories;
    }

    public void setCustomerInteractionCategories( List<ContentPojo> inCustomerInteractionCategories )
    {
        customerInteractionCategories = inCustomerInteractionCategories;
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

    public CrmMassOutagePojo getMassOutagePojo()
    {
        return massOutagePojo;
    }

    public void setMassOutagePojo( CrmMassOutagePojo inMassOutagePojo )
    {
        massOutagePojo = inMassOutagePojo;
    }

    public CrmSrTicketsPojo getSrTicketPojo()
    {
        return srTicketPojo;
    }

    public void setSrTicketPojo( CrmSrTicketsPojo inSrTicketPojo )
    {
        srTicketPojo = inSrTicketPojo;
    }

    public List<CrmQrcCategoriesPojo> getQrcCategoriesPojos()
    {
        if ( !StringUtils.isValidObj( qrcCategoriesPojos ) )
        {
            qrcCategoriesPojos = new ArrayList<CrmQrcCategoriesPojo>();
            return qrcCategoriesPojos;
        }
        return qrcCategoriesPojos;
    }

    public void setQrcCategoriesPojos( List<CrmQrcCategoriesPojo> inQrcCategoriesPojos )
    {
        qrcCategoriesPojos = inQrcCategoriesPojos;
    }

    /**
     * @return the installationAddressPojo
     */
    public CrmAddressDetailsPojo getInstallationAddressPojo()
    {
        return installationAddressPojo;
    }

    /**
     * @param inInstallationAddressPojo the installationAddressPojo to set
     */
    public void setInstallationAddressPojo( CrmAddressDetailsPojo inInstallationAddressPojo )
    {
        installationAddressPojo = inInstallationAddressPojo;
    }

    /**
     * @return the billingAddressPojo
     */
    public CrmAddressDetailsPojo getBillingAddressPojo()
    {
        return billingAddressPojo;
    }

    /**
     * @param inBillingAddressPojo the billingAddressPojo to set
     */
    public void setBillingAddressPojo( CrmAddressDetailsPojo inBillingAddressPojo )
    {
        billingAddressPojo = inBillingAddressPojo;
    }

    /**
     * @return the qrcCategoriesPojo
     */
    public CrmQrcCategoriesPojo getQrcCategoriesPojo()
    {
        return qrcCategoriesPojo;
    }

    /**
     * @param inQrcCategoriesPojo the qrcCategoriesPojo to set
     */
    public void setQrcCategoriesPojo( CrmQrcCategoriesPojo inQrcCategoriesPojo )
    {
        qrcCategoriesPojo = inQrcCategoriesPojo;
    }

    /**
     * @return the remarksPojo
     */
    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    /**
     * @param inRemarksPojo the remarksPojo to set
     */
    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    /**
     * @return the custDetailsPojo
     */
    public CrmCustomerDetailsPojo getCustDetailsPojo()
    {
        return custDetailsPojo;
    }

    /**
     * @param inCustDetailsPojo the custDetailsPojo to set
     */
    public void setCustDetailsPojo( CrmCustomerDetailsPojo inCustDetailsPojo )
    {
        custDetailsPojo = inCustDetailsPojo;
    }

    public CrmOrderDetailsPojo getOrderDetailsPojo()
    {
        return orderDetailsPojo;
    }

    public void setOrderDetailsPojo( CrmOrderDetailsPojo inCrmOrderDetailsPojo )
    {
        orderDetailsPojo = inCrmOrderDetailsPojo;
    }

    public CrmPlanDetailsPojo getPlanDetailsPojo()
    {
        return planDetailsPojo;
    }

    public void setPlanDetailsPojo( CrmPlanDetailsPojo inPlanDetailsPojo )
    {
        planDetailsPojo = inPlanDetailsPojo;
    }

    public CrmNetworkConfigurationsPojo getNetworkConfigurationsPojo()
    {
        return networkConfigurationsPojo;
    }

    public void setNetworkConfigurationsPojo( CrmNetworkConfigurationsPojo inNetworkConfigurationsPojo )
    {
        networkConfigurationsPojo = inNetworkConfigurationsPojo;
    }

    public CrmNationalityDetailsPojo getNationalityDetailsPojo()
    {
        return nationalityDetailsPojo;
    }

    public void setNationalityDetailsPojo( CrmNationalityDetailsPojo inNationalityDetailsPojo )
    {
        nationalityDetailsPojo = inNationalityDetailsPojo;
    }

    public List<CrmCustomerActivityPojo> getCrmCustomerActivityList()
    {
        return crmCustomerActivityList;
    }

    public void setCrmCustomerActivityList( List<CrmCustomerActivityPojo> inCrmCustomerActivityList )
    {
        crmCustomerActivityList = inCrmCustomerActivityList;
    }

    public List<CrmSrTicketsPojo> getSrTicketsPojos()
    {
        return srTicketsPojos;
    }

    public void setSrTicketsPojos( List<CrmSrTicketsPojo> inSrTicketsPojos )
    {
        srTicketsPojos = inSrTicketsPojos;
    }

    public List<ContentPojo> getConnectionTypes()
    {
        return connectionTypes;
    }

    public void setConnectionTypes( List<ContentPojo> inConnectionTypes )
    {
        connectionTypes = inConnectionTypes;
    }

    public List<ContentPojo> getBillModes()
    {
        return billModes;
    }

    public void setBillModes( List<ContentPojo> inBillModes )
    {
        billModes = inBillModes;
    }

    public List<StatePojo> getStateList()
    {
        return stateList;
    }

    public void setStateList( List<StatePojo> inStateList )
    {
        stateList = inStateList;
    }

    public List<CityPojo> getCityList()
    {
        return cityList;
    }

    public void setCityList( List<CityPojo> inCityList )
    {
        cityList = inCityList;
    }

    public String getCrmUserId()
    {
        return crmUserId;
    }

    public void setCrmUserId( String inCrmUserId )
    {
        crmUserId = inCrmUserId;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getSearchString()
    {
        return searchString;
    }

    public void setSearchString( String inSearchString )
    {
        searchString = inSearchString;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasonPojos()
    {
        return crmRcaReasonPojos;
    }

    public void setCrmRcaReasonPojos( List<CrmRcaReasonPojo> inCrmRcaReasonPojos )
    {
        crmRcaReasonPojos = inCrmRcaReasonPojos;
    }

    public CrmQrcWhitelistPojo getCrmQrcWhitelistPojo()
    {
        return crmQrcWhitelistPojo;
    }

    public void setCrmQrcWhitelistPojo( CrmQrcWhitelistPojo inCrmQrcWhitelistPojo )
    {
        crmQrcWhitelistPojo = inCrmQrcWhitelistPojo;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate( String endDate )
    {
        this.endDate = endDate;
    }

    public String getRemoveOption()
    {
        return removeOption;
    }

    public void setRemoveOption( String inRemoveOption )
    {
        removeOption = inRemoveOption;
    }

    public boolean isInExceptionList()
    {
        return inExceptionList;
    }

    public void setInExceptionList( boolean inInExceptionList )
    {
        inExceptionList = inInExceptionList;
    }

    public long getExceptionReason()
    {
        return exceptionReason;
    }

    public void setExceptionReason( long inExceptionReason )
    {
        exceptionReason = inExceptionReason;
    }

    public FormFile getWhitelistExcelFile()
    {
        return whitelistExcelFile;
    }

    public void setWhitelistExcelFile( FormFile whitelistExcelFile )
    {
        this.whitelistExcelFile = whitelistExcelFile;
    }

    public String getSrTicketNo()
    {
        return srTicketNo;
    }

    public void setSrTicketNo( String srTicketNo )
    {
        this.srTicketNo = srTicketNo;
    }

    public CrmAddressDetailsPojo getNewInstallationAddress()
    {
        return newInstallationAddress;
    }

    public void setNewInstallationAddress( CrmAddressDetailsPojo inNewInstallationAddress )
    {
        newInstallationAddress = inNewInstallationAddress;
    }

    public String getInstAddrArea()
    {
        return instAddrArea;
    }

    public void setInstAddrArea( String inInstAddrArea )
    {
        instAddrArea = inInstAddrArea;
    }

    public String getInstAddrLocality()
    {
        return instAddrLocality;
    }

    public void setInstAddrLocality( String inInstAddrLocality )
    {
        instAddrLocality = inInstAddrLocality;
    }

    public String getInstAddrSociety()
    {
        return instAddrSociety;
    }

    public void setInstAddrSociety( String inInstAddrSociety )
    {
        instAddrSociety = inInstAddrSociety;
    }

    public List<CrmRcaReasonPojo> getDisconnectionReasons()
    {
        return disconnectionReasons;
    }

    public void setDisconnectionReasons( List<CrmRcaReasonPojo> inDisconnectionReasons )
    {
        disconnectionReasons = inDisconnectionReasons;
    }

    public CrmCustWaiverPojo getCustWaiverPojo()
    {
        return custWaiverPojo;
    }

    public void setCustWaiverPojo( CrmCustWaiverPojo inCustWaiverPojo )
    {
        custWaiverPojo = inCustWaiverPojo;
    }

    public List<CrmCustWaiverPojo> getCustWaiverPojos()
    {
        return custWaiverPojos;
    }

    public void setCustWaiverPojos( List<CrmCustWaiverPojo> inCustWaiverPojos )
    {
        custWaiverPojos = inCustWaiverPojos;
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

    public String getBoolValue()
    {
        return boolValue;
    }

    public void setBoolValue( String inBoolValue )
    {
        boolValue = inBoolValue;
    }

    public String getQrcActions()
    {
        return qrcActions;
    }

    public void setQrcActions( String inQrcActions )
    {
        qrcActions = inQrcActions;
    }

    public List<CrmUserPojo> getCrmUserPojos()
    {
        return crmUserPojos;
    }

    public void setCrmUserPojos( List<CrmUserPojo> inCrmUserPojos )
    {
        crmUserPojos = inCrmUserPojos;
    }

    public CrmQrcActionTakenPojo getCrmQrcActionTaken()
    {
        return crmQrcActionTaken;
    }

    public void setCrmQrcActionTaken( CrmQrcActionTakenPojo inCrmQrcActionTaken )
    {
        crmQrcActionTaken = inCrmQrcActionTaken;
    }

    public CrmQrcRootCausePojo getCrmQrcRootCause()
    {
        return crmQrcRootCause;
    }

    public void setCrmQrcRootCause( CrmQrcRootCausePojo inCrmQrcRootCause )
    {
        crmQrcRootCause = inCrmQrcRootCause;
    }

    public CrmPartnerNetworkConfigPojo getPartnerNetworkConfigPojo()
    {
        return partnerNetworkConfigPojo;
    }

    public void setPartnerNetworkConfigPojo( CrmPartnerNetworkConfigPojo partnerNetworkConfigPojo )
    {
        this.partnerNetworkConfigPojo = partnerNetworkConfigPojo;
    }

    public String getInType()
    {
        return inType;
    }

    public void setInType( String inInType )
    {
        inType = inInType;
    }

    public String getNewPrimaryMacId()
    {
        return newPrimaryMacId;
    }

    public void setNewPrimaryMacId( String newPrimaryMacId )
    {
        this.newPrimaryMacId = newPrimaryMacId;
    }

    public String getNewOption82()
    {
        return newOption82;
    }

    public void setNewOption82( String newOption82 )
    {
        this.newOption82 = newOption82;
    }

    public List<String> getSecondaryMACAddrList()
    {
        return secondaryMACAddrList;
    }

    public void setSecondaryMACAddrList( List<String> secondaryMACAddrList )
    {
        this.secondaryMACAddrList = secondaryMACAddrList;
    }

    public String getNewSecondaryMacId()
    {
        return newSecondaryMacId;
    }

    public void setNewSecondaryMacId( String newSecondaryMacId )
    {
        this.newSecondaryMacId = newSecondaryMacId;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long partnerId )
    {
        this.partnerId = partnerId;
    }

    public List<CrmPartnerNetworkConfigPojo> getMasterNameList()
    {
        return masterNameList;
    }

    public void setMasterNameList( List<CrmPartnerNetworkConfigPojo> masterNameList )
    {
        this.masterNameList = masterNameList;
    }

    public List<ContentPojo> getOltTypeList()
    {
        return oltTypeList;
    }

    public void setOltTypeList( List<ContentPojo> oltTypeList )
    {
        this.oltTypeList = oltTypeList;
    }

    public List<CrmCustAssetDetailsPojo> getCrmCustAssetDetailsPojos()
    {
        return crmCustAssetDetailsPojos;
    }

    public void setCrmCustAssetDetailsPojos( List<CrmCustAssetDetailsPojo> crmCustAssetDetailsPojos )
    {
        this.crmCustAssetDetailsPojos = crmCustAssetDetailsPojos;
    }

    public CrmCustAssetDetailsPojo getCrmCustAssetDetailsPojo()
    {
        return crmCustAssetDetailsPojo;
    }

    public void setCrmCustAssetDetailsPojo( CrmCustAssetDetailsPojo crmCustAssetDetailsPojo )
    {
        this.crmCustAssetDetailsPojo = crmCustAssetDetailsPojo;
    }

    public String getShowDivValue()
    {
        return showDivValue;
    }

    public void setShowDivValue( String showDivValue )
    {
        this.showDivValue = showDivValue;
    }

    public List<CrmPlanMasterPojo> getPlanMasterList()
    {
        return planMasterList;
    }

    public void setPlanMasterList( List<CrmPlanMasterPojo> planMasterList )
    {
        this.planMasterList = planMasterList;
    }

    public String getPlanType()
    {
        return planType;
    }

    public void setPlanType( String planType )
    {
        this.planType = planType;
    }

    public String getPlanUsageType()
    {
        return planUsageType;
    }

    public void setPlanUsageType( String planUsageType )
    {
        this.planUsageType = planUsageType;
    }

    public String getPlanCategory()
    {
        return planCategory;
    }

    public void setPlanCategory( String planCategory )
    {
        this.planCategory = planCategory;
    }

    public List<CrmPlanMasterPojo> getAddonPlanMasterList()
    {
        return addonPlanMasterList;
    }

    public void setAddonPlanMasterList( List<CrmPlanMasterPojo> addonPlanMasterList )
    {
        this.addonPlanMasterList = addonPlanMasterList;
    }

    public CrmPlanMasterPojo getCrmPlanMasterPojo()
    {
        return crmPlanMasterPojo;
    }

    public void setCrmPlanMasterPojo( CrmPlanMasterPojo crmPlanMasterPojo )
    {
        this.crmPlanMasterPojo = crmPlanMasterPojo;
    }

    public CrmPlanMasterPojo getCrmAddonPlanMasterPojo()
    {
        return crmAddonPlanMasterPojo;
    }

    public void setCrmAddonPlanMasterPojo( CrmPlanMasterPojo crmAddonPlanMasterPojo )
    {
        this.crmAddonPlanMasterPojo = crmAddonPlanMasterPojo;
    }

    public String getActivationTime()
    {
        return activationTime;
    }

    public void setActivationTime( String activationTime )
    {
        this.activationTime = activationTime;
    }

    public CrmPaymentDetailsPojo getCrmPaymentDetailsPojo()
    {
        return crmPaymentDetailsPojo;
    }

    public void setCrmPaymentDetailsPojo( CrmPaymentDetailsPojo inCrmPaymentDetailsPojo )
    {
        crmPaymentDetailsPojo = inCrmPaymentDetailsPojo;
    }

    public List<CrmPaymentDetailsPojo> getCustPaymentDetailsPojos()
    {
        return custPaymentDetailsPojos;
    }

    public void setCustPaymentDetailsPojos( List<CrmPaymentDetailsPojo> custPaymentDetailsPojos )
    {
        this.custPaymentDetailsPojos = custPaymentDetailsPojos;
    }

    public String getSelectedPlanCode()
    {
        return selectedPlanCode;
    }

    public void setSelectedPlanCode( String selectedPlanCode )
    {
        this.selectedPlanCode = selectedPlanCode;
    }

    public String getAddonPlanCode()
    {
        return addonPlanCode;
    }

    public void setAddonPlanCode( String addonPlanCode )
    {
        this.addonPlanCode = addonPlanCode;
    }

    public String getOldBasePlanCode()
    {
        return oldBasePlanCode;
    }

    public void setOldBasePlanCode( String oldBasePlanCode )
    {
        this.oldBasePlanCode = oldBasePlanCode;
    }

    public String getOldAddonPlanCode()
    {
        return oldAddonPlanCode;
    }

    public void setOldAddonPlanCode( String oldAddonPlanCode )
    {
        this.oldAddonPlanCode = oldAddonPlanCode;
    }

    public String getActivationType()
    {
        return activationType;
    }

    public void setActivationType( String activationType )
    {
        this.activationType = activationType;
    }

    public List<CrmInvoiceDetailsPojo> getInvoiceList()
    {
        return invoiceList;
    }

    public void setInvoiceList( List<CrmInvoiceDetailsPojo> inInvoiceList )
    {
        invoiceList = inInvoiceList;
    }

    public CrmInvoiceDetailsPojo getInvoicePojo()
    {
        return invoicePojo;
    }

    public void setInvoicePojo( CrmInvoiceDetailsPojo inInvoicePojo )
    {
        invoicePojo = inInvoicePojo;
    }

    public CrmCustAdditionalDetails getCustAdditionalDetails()
    {
        return custAdditionalDetails;
    }

    public void setCustAdditionalDetails( CrmCustAdditionalDetails inCustAdditionalDetails )
    {
        custAdditionalDetails = inCustAdditionalDetails;
    }

    public double getUsedPercentage()
    {
        return usedPercentage;
    }

    public void setUsedPercentage( double usedPercentage )
    {
        this.usedPercentage = usedPercentage;
    }

    public double getPercentage()
    {
        return percentage;
    }

    public void setPercentage( double percentage )
    {
        this.percentage = percentage;
    }

    public Long getCurrentUsage()
    {
        return currentUsage;
    }

    public void setCurrentUsage( Long currentUsage )
    {
        this.currentUsage = currentUsage;
    }

    public Long getBasePlanQuota()
    {
        return basePlanQuota;
    }

    public void setBasePlanQuota( Long basePlanQuota )
    {
        this.basePlanQuota = basePlanQuota;
    }

    public Long getAddonPlanQuota()
    {
        return addonPlanQuota;
    }

    public void setAddonPlanQuota( Long addonPlanQuota )
    {
        this.addonPlanQuota = addonPlanQuota;
    }

    public Long getTotalQuota()
    {
        return totalQuota;
    }

    public void setTotalQuota( Long totalQuota )
    {
        this.totalQuota = totalQuota;
    }

    public boolean isVisibileButton()
    {
        return visibileButton;
    }

    public void setVisibileButton( boolean visibileButton )
    {
        this.visibileButton = visibileButton;
    }

    public List<ContentPojo> getCustomerStatusList()
    {
        return customerStatusList;
    }

    public void setCustomerStatusList( List<ContentPojo> customerStatusList )
    {
        this.customerStatusList = customerStatusList;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue( String inParamValue )
    {
        paramValue = inParamValue;
    }

    public List<ContentPojo> getVisaList()
    {
        return visaList;
    }

    public void setVisaList( List<ContentPojo> inVisaList )
    {
        visaList = inVisaList;
    }

    public CrmShiftingWorkflowPojo getShiftingWorkflowPojo()
    {
        return shiftingWorkflowPojo;
    }

    public void setShiftingWorkflowPojo( CrmShiftingWorkflowPojo inShiftingWorkflowPojo )
    {
        shiftingWorkflowPojo = inShiftingWorkflowPojo;
    }

    public List<PartnerPojo> getPartnerPojoList()
    {
        return partnerPojoList;
    }

    public void setPartnerPojoList( List<PartnerPojo> inPartnerPojoList )
    {
        partnerPojoList = inPartnerPojoList;
    }

    public String[] getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( String[] inPartnerList )
    {
        partnerList = inPartnerList;
    }

    public String[] getServiceList()
    {
        return serviceList;
    }

    public void setServiceList( String[] inServiceList )
    {
        serviceList = inServiceList;
    }

    public List<CrmRcaReasonPojo> getRejectionReasons()
    {
        return rejectionReasons;
    }

    public void setRejectionReasons( List<CrmRcaReasonPojo> inRejectionReasons )
    {
        rejectionReasons = inRejectionReasons;
    }

    public List<ContentPojo> getSpList()
    {
        return spList;
    }

    public void setSpList( List<ContentPojo> inSpList )
    {
        spList = inSpList;
    }

    public double getMiniTelesolutionAmount()
    {
        return miniTelesolutionAmount;
    }

    public void setMiniTelesolutionAmount( double inMiniTelesolutionAmount )
    {
        miniTelesolutionAmount = inMiniTelesolutionAmount;
    }

    public List<ContentPojo> getPaymentModes()
    {
        return paymentModes;
    }

    public void setPaymentModes( List<ContentPojo> inPaymentModes )
    {
        paymentModes = inPaymentModes;
    }

    public List<CrmRcaReasonPojo> getBankList()
    {
        return bankList;
    }

    public void setBankList( List<CrmRcaReasonPojo> inBankList )
    {
        bankList = inBankList;
    }

    public List<CrmShiftingWorkflowPojo> getCrmShiftingWorkflowPojos()
    {
        return crmShiftingWorkflowPojos;
    }

    public void setCrmShiftingWorkflowPojos( List<CrmShiftingWorkflowPojo> inCrmShiftingWorkflowPojos )
    {
        crmShiftingWorkflowPojos = inCrmShiftingWorkflowPojos;
    }

    public List<RemarksPojo> getRemarksPojoList()
    {
        return remarksPojoList;
    }

    public void setRemarksPojoList( List<RemarksPojo> inRemarksPojoList )
    {
        remarksPojoList = inRemarksPojoList;
    }

    public List<CrmBillCyclePojo> getCrmBillCyclePojosList()
    {
        return crmBillCyclePojosList;
    }

    public void setCrmBillCyclePojosList( List<CrmBillCyclePojo> crmBillCyclePojosList )
    {
        this.crmBillCyclePojosList = crmBillCyclePojosList;
    }

    public String getOldCpe()
    {
        return oldCpe;
    }

    public void setOldCpe( String inOldCpe )
    {
        oldCpe = inOldCpe;
    }

    public boolean isAddonAllowed()
    {
        return addonAllowed;
    }

    public void setAddonAllowed( boolean inAddonAllowed )
    {
        addonAllowed = inAddonAllowed;
    }

    public CrmTicketHistoryPojo getTicketHistory()
    {
        return ticketHistory;
    }

    public void setTicketHistory( CrmTicketHistoryPojo inTicketHistory )
    {
        ticketHistory = inTicketHistory;
    }

    public String getFollowupOn()
    {
        return followupOn;
    }

    public void setFollowupOn( String inFollowupOn )
    {
        followupOn = inFollowupOn;
    }

    public List<AreaPojo> getAreaList()
    {
        return areaList;
    }

    public List<LocalityPojo> getLocalityList()
    {
        return localityList;
    }

    public List<SocietyPojo> getSocietyList()
    {
        return societyList;
    }

    public void setAreaList( List<AreaPojo> inAreaList )
    {
        areaList = inAreaList;
    }

    public void setLocalityList( List<LocalityPojo> inLocalityList )
    {
        localityList = inLocalityList;
    }

    public void setSocietyList( List<SocietyPojo> inSocietyList )
    {
        societyList = inSocietyList;
    }

    public boolean isMacFaulty()
    {
        return macFaulty;
    }

    public void setMacFaulty( boolean inMacFaulty )
    {
        macFaulty = inMacFaulty;
    }

    public String getChurnType()
    {
        return churnType;
    }

    public void setChurnType( String inChurnType )
    {
        churnType = inChurnType;
    }

    public String getCustomerResponse()
    {
        return customerResponse;
    }

    public void setCustomerResponse( String customerResponse )
    {
        this.customerResponse = customerResponse;
    }

    public List<CrmRcaReasonPojo> getCloserReasonList()
    {
        return closerReasonList;
    }

    public void setCloserReasonList( List<CrmRcaReasonPojo> closerReasonList )
    {
        this.closerReasonList = closerReasonList;
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

    public LmsPojo getLmsPojo()
    {
        return lmsPojo;
    }

    public void setLmsPojo( LmsPojo inLmsPojo )
    {
        lmsPojo = inLmsPojo;
    }

    public PartnerPojo getPartnerPojo()
    {
        return partnerPojo;
    }

    public void setPartnerPojo( PartnerPojo inPartnerPojo )
    {
        partnerPojo = inPartnerPojo;
    }

    public boolean isShowDiv()
    {
        return showDiv;
    }

    public void setShowDiv( boolean inShowDiv )
    {
        showDiv = inShowDiv;
    }

    public String getNetworkType()
    {
        return networkType;
    }

    public void setNetworkType( String inNetworkType )
    {
        networkType = inNetworkType;
    }

    public List<ContentPojo> getProductList()
    {
        return productList;
    }

    public void setProductList( List<ContentPojo> inProductList )
    {
        productList = inProductList;
    }

    public String getPreviousPartnerId()
    {
        return previousPartnerId;
    }

    public void setPreviousPartnerId( String inPreviousPartnerId )
    {
        previousPartnerId = inPreviousPartnerId;
    }

    public String getPhysicalInstallation()
    {
        return physicalInstallation;
    }

    public void setPhysicalInstallation( String inPhysicalInstallation )
    {
        physicalInstallation = inPhysicalInstallation;
    }

    public String getCpeAvailable()
    {
        return cpeAvailable;
    }

    public void setCpeAvailable( String inCpeAvailable )
    {
        cpeAvailable = inCpeAvailable;
    }

    public String getCpeMacId()
    {
        return cpeMacId;
    }

    public void setCpeMacId( String inCpeMacId )
    {
        cpeMacId = inCpeMacId;
    }

    public String getNassPortId()
    {
        return nassPortId;
    }

    public void setNassPortId( String inNassPortId )
    {
        nassPortId = inNassPortId;
    }

    public CrmAddressDetailsPojo getChangeInstallationAddressPojo()
    {
        return changeInstallationAddressPojo;
    }

    public void setChangeInstallationAddressPojo( CrmAddressDetailsPojo inChangeInstallationAddressPojo )
    {
        changeInstallationAddressPojo = inChangeInstallationAddressPojo;
    }

    public String getChangeInstArea()
    {
        return changeInstArea;
    }

    public void setChangeInstArea( String inChangeInstArea )
    {
        changeInstArea = inChangeInstArea;
    }

    public String getChangeInnstSociety()
    {
        return changeInnstSociety;
    }

    public void setChangeInnstSociety( String inChangeInnstSociety )
    {
        changeInnstSociety = inChangeInnstSociety;
    }

    public CrmPartnerNetworkConfigPojo getNewcrmPartnerNetworkConfig()
    {
        return newcrmPartnerNetworkConfig;
    }

    public void setNewcrmPartnerNetworkConfig( CrmPartnerNetworkConfigPojo inNewcrmPartnerNetworkConfig )
    {
        newcrmPartnerNetworkConfig = inNewcrmPartnerNetworkConfig;
    }

    public String getFeasibility()
    {
        return feasibility;
    }

    public void setFeasibility( String inFeasibility )
    {
        feasibility = inFeasibility;
    }

    public int getStageIndex()
    {
        return stageIndex;
    }

    public void setStageIndex( int inStageIndex )
    {
        stageIndex = inStageIndex;
    }

    public boolean isNonEditableAtSC()
    {
        return nonEditableAtSC;
    }

    public void setNonEditableAtSC( boolean inNonEditableAtSC )
    {
        nonEditableAtSC = inNonEditableAtSC;
    }

    public String getPlanTypeShifting()
    {
        return planTypeShifting;
    }

    public void setPlanTypeShifting( String inPlanTypeShifting )
    {
        planTypeShifting = inPlanTypeShifting;
    }

    public CrmPaymentDetailsPojo getTeleservicesPayment()
    {
        return teleservicesPayment;
    }

    public void setTeleservicesPayment( CrmPaymentDetailsPojo inTeleservicesPayment )
    {
        teleservicesPayment = inTeleservicesPayment;
    }

    public List<CrmPlanMasterPojo> getBasePlanNames()
    {
        return basePlanNames;
    }

    public void setBasePlanNames( List<CrmPlanMasterPojo> inBasePlanNames )
    {
        basePlanNames = inBasePlanNames;
    }

    public List<CrmPlanMasterPojo> getAddonPlanNames()
    {
        return addonPlanNames;
    }

    public void setAddonPlanNames( List<CrmPlanMasterPojo> inAddonPlanNames )
    {
        addonPlanNames = inAddonPlanNames;
    }

    public CrmPlanDetailsPojo getOldplanDetailsPojo()
    {
        return oldplanDetailsPojo;
    }

    public void setOldplanDetailsPojo( CrmPlanDetailsPojo inOldplanDetailsPojo )
    {
        oldplanDetailsPojo = inOldplanDetailsPojo;
    }

    public Long getOldbasePlanQuota()
    {
        return oldbasePlanQuota;
    }

    public void setOldbasePlanQuota( Long inOldbasePlanQuota )
    {
        oldbasePlanQuota = inOldbasePlanQuota;
    }

    public Long getOldaddonPlanQuota()
    {
        return oldaddonPlanQuota;
    }

    public void setOldaddonPlanQuota( Long inOldaddonPlanQuota )
    {
        oldaddonPlanQuota = inOldaddonPlanQuota;
    }

    public Double getOldtotalRenatal()
    {
        return oldtotalRenatal;
    }

    public void setOldtotalRenatal( Double inOldtotalRenatal )
    {
        oldtotalRenatal = inOldtotalRenatal;
    }

    public boolean isAddOnNotAllowed()
    {
        return addOnNotAllowed;
    }

    public void setAddOnNotAllowed( boolean inAddOnNotAllowed )
    {
        addOnNotAllowed = inAddOnNotAllowed;
    }

    public CrmShiftingWorkflowPojo getOldshiftingWorkflowPojo()
    {
        return oldshiftingWorkflowPojo;
    }

    public void setOldshiftingWorkflowPojo( CrmShiftingWorkflowPojo inOldshiftingWorkflowPojo )
    {
        oldshiftingWorkflowPojo = inOldshiftingWorkflowPojo;
    }

    public String getPlanService()
    {
        return planService;
    }

    public void setPlanService( String inPlanService )
    {
        planService = inPlanService;
    }

    public String getHouseNumber()
    {
        return houseNumber;
    }

    public void setHouseNumber( String inHouseNumber )
    {
        houseNumber = inHouseNumber;
    }

    @Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "In validate method........" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        ActionErrors actionErrors = new ActionErrors();
        //QrcFormHelper.validateQRCForm( method, actionErrors, this );
        if ( StringUtils.equals( method, IAppConstants.METHOD_QRC_SEARCH_CUSTOMER ) )
        {
            String workingID = (String) inRequest.getSession().getAttribute( "workingID" );
            String workingId = inRequest.getParameter( "workingId" );
            if ( StringUtils.isNotEmpty( workingId ) )
            {
                setSearchString( workingId );
                inRequest.getSession().removeAttribute( "workingID" );
            }
            else if ( StringUtils.isNotEmpty( workingID ) )
            {
                setSearchString( workingID );
            }
            /*if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CRF_CUSTOMER_ID.getPattern(), getSearchString() ) )
            {
                actionErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( CRMRegex.PATTERN_CRF_CUSTOMER_ID.getErrorKey() ) );
            }*/
        }
        else if ( StringUtils.equals( method, IAppConstants.BULK_WHITELIST ) )
        {
            /*
            LOGGER.info( "uploaded file type: " + getWhitelistExcelFile().getContentType() );
            if ( getWhitelistExcelFile().getFileSize() == 0 )
            {
                .addactionErrors
                        ( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_REQUIRED ) );
                return actionErrors;
            }
            if ( getWhitelistExcelFile().getFileSize() > 20480 )
            {
                actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_SIZE ) );
                return actionErrors;
            }
            */
        }
        return actionErrors;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "in reset" );
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "method: " + method );
        resetShiftingForm( method );
        QrcFormHelper.resetQRCForm( this, method );
        super.reset( inMapping, inRequest );
    }

    private void resetShiftingForm( String method )
    {
        if ( StringUtils.equals( method, "shiftingInitiation" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
        }
        if ( StringUtils.equals( method, "submitIfrStage" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setPartnerPojoList( new ArrayList<PartnerPojo>() );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_CSD_STAGE ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setPartnerPojo( new PartnerPojo() );
            this.setProductList( new ArrayList<ContentPojo>() );
            this.setTeleservicesPayment( new CrmPaymentDetailsPojo() );
            this.setPlanDetailsPojo( new CrmPlanDetailsPojo() );
        }
        if ( StringUtils.equals( method, "submitftLevel2" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setPartnerPojo( new PartnerPojo() );
            this.setProductList( new ArrayList<ContentPojo>() );
        }
        if ( StringUtils.equals( method, "submitIfrEOCL1" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
        }
        if ( StringUtils.equals( method, "submitCSDLevel3" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setCloserReasonList( new ArrayList<CrmRcaReasonPojo>() );
        }
        if ( StringUtils.equals( method, "submitNpLevel1" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
        }
        if ( StringUtils.equals( method, "bindOption82" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
            this.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            this.setNewcrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
        }
        if ( StringUtils.equals( method, "editMacForShifting" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
            this.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            this.setNewcrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
        }
        if ( StringUtils.equals( method, IAppConstants.METHOD_NOC_LEVEL1 ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
            this.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            this.setNewcrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
        }
        if ( StringUtils.equals( method, "changeInstallationAddress" ) )
        {
            this.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            this.setRemarksPojo( new RemarksPojo() );
            this.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
            this.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
            this.setChangeInstallationAddressPojo( new CrmAddressDetailsPojo() );
        }
    }

    public String getInboxSelected()
    {
        return inboxSelected;
    }

    public void setInboxSelected( String inInboxSelected )
    {
        inboxSelected = inInboxSelected;
    }

    public double getDiffRentInclTax()
    {
        return diffRentInclTax;
    }

    public void setDiffRentInclTax( double inDiffRentInclTax )
    {
        diffRentInclTax = inDiffRentInclTax;
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
