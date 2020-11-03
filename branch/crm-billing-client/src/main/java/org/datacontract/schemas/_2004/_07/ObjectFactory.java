
package org.datacontract.schemas._2004._07;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.datacontract.schemas._2004._07 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CustomerBaringExceptionListResultUserName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "userName");
    private final static QName _CustomerBaringExceptionListResultStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "status");
    private final static QName _CustomerBaringExceptionListResultReason_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "reason");
    private final static QName _PrBillingDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "pr_BillingDetailsResult");
    private final static QName _ArrayOfGetChargeDetailResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ArrayOfGetChargeDetailResult");
    private final static QName _RefferalDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RefferalDetailsResult");
    private final static QName _GetDeviceDetailResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "getDeviceDetailResult");
    private final static QName _UserSessionResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "UserSessionResult");
    private final static QName _GetDiscountDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "GetDiscountDetailsResult");
    private final static QName _CustomerBaringExceptionListResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerBaringExceptionListResult");
    private final static QName _BillDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillDetails");
    private final static QName _TerminationRefundResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "TerminationRefundResult");
    private final static QName _AddPlanResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AddPlanResult");
    private final static QName _ApiResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ApiResponse");
    private final static QName _ArrayOfprBillingDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ArrayOfpr_BillingDetailsResult");
    private final static QName _LocalContactAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "LocalContactAddress");
    private final static QName _ChangeBusinessPartnerResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChangeBusinessPartnerResult");
    private final static QName _P2PReBarringResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "P2PReBarringResult");
    private final static QName _CustomerNotificationExceptionListResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerNotificationExceptionListResult");
    private final static QName _CustomerUpdate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerUpdate");
    private final static QName _CheckPlanFeasibilityResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CheckPlanFeasibilityResult");
    private final static QName _ArrayOfgetCurrentUsageResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ArrayOfgetCurrentUsageResult");
    private final static QName _ChangeDeviceDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChangeDeviceDetail");
    private final static QName _ClsCustomerUnbaringExceptionList_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsCustomerUnbaringExceptionList");
    private final static QName _GetDistributorDeviceDetailResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "GetDistributorDeviceDetailResult");
    private final static QName _GetChargeDetailResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "GetChargeDetailResult");
    private final static QName _BillingAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillingAddress");
    private final static QName _CancelPlanMigrationResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CancelPlanMigrationResult");
    private final static QName _DisableWalledGardenResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DisableWalledGardenResult");
    private final static QName _ClsP2PReBarring_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsP2PReBarring");
    private final static QName _ArrayOfGetDiscountDetailsResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ArrayOfGetDiscountDetailsResult");
    private final static QName _ChangeAddressAddressType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChangeAddress.AddressType");
    private final static QName _CancelAddPostpaidPlanResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CancelAddPostpaidPlanResult");
    private final static QName _AddChargeResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AddChargeResult");
    private final static QName _CustomerActivation_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerActivation");
    private final static QName _InstallationAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "InstallationAddress");
    private final static QName _ClsTerminationRefund_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsTerminationRefund");
    private final static QName _CustomerUnbaringExceptionListResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerUnbaringExceptionListResult");
    private final static QName _GetLastPaymentResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "getLastPaymentResult");
    private final static QName _PaymentAdjustment_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PaymentAdjustment");
    private final static QName _PaymentAdjustmentResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PaymentAdjustmentResult");
    private final static QName _OfferAllocation_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "OfferAllocation");
    private final static QName _BillDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillDetail");
    private final static QName _LastPaymentResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "LastPaymentResult");
    private final static QName _ClsCustomerNotificationExceptionList_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsCustomerNotificationExceptionList");
    private final static QName _CreateCustomerResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CreateCustomerResult");
    private final static QName _CustomerActivationResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerActivationResult");
    private final static QName _DeviceDetailResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DeviceDetailResult");
    private final static QName _ChangeBillCycleResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChangeBillCycleResult");
    private final static QName _ArrayOfGetUserSessionResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ArrayOfGetUserSessionResult");
    private final static QName _ClsDisableWalledGarden_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsDisableWalledGarden");
    private final static QName _InventoryItemToLCOResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "InventoryItemToLCOResult");
    private final static QName _CustomerPostPaymentResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerPostPaymentResult");
    private final static QName _GetUserSessionResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "GetUserSessionResult");
    private final static QName _GetCurrentUsageResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "getCurrentUsageResult");
    private final static QName _ArrayOfGetDistributorDeviceDetailResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ArrayOfGetDistributorDeviceDetailResult");
    private final static QName _CustomerDiscountDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerDiscountDetails");
    private final static QName _ApiRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ApiRequest");
    private final static QName _AddPlan_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AddPlan");
    private final static QName _ClsCustomerSpeedExceptionList_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsCustomerSpeedExceptionList");
    private final static QName _CustomerUsageDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerUsageDetail");
    private final static QName _ClsAddCharge_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsAddCharge");
    private final static QName _ClsCustomerBaringExceptionList_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "clsCustomerBaringExceptionList");
    private final static QName _CustomerSpeedExceptionListResult_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerSpeedExceptionListResult");
    private final static QName _CustomerDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerDetail");
    private final static QName _Customer_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Customer");
    private final static QName _InstantOfferAllocation_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "InstantOfferAllocation");
    private final static QName _ChangeAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChangeAddress");
    private final static QName _CustomerCancelPayment_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerCancelPayment");
    private final static QName _CustomerPostPayment_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerPostPayment");
    private final static QName _BillDetailCustomerName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "customerName");
    private final static QName _BillDetailChargesDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "chargesDetail");
    private final static QName _BillDetailBillNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "billNumber");
    private final static QName _ClsCustomerNotificationExceptionListAction_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "action");
    private final static QName _CustomerUpdateGender_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "gender");
    private final static QName _CustomerUpdateLocalContactPersonContactNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "localContactPersonContactNo");
    private final static QName _CustomerUpdateSaleRepresentativeCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "saleRepresentativeCode");
    private final static QName _CustomerUpdateZone_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Zone");
    private final static QName _CustomerUpdateBusinessPartnerName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "businessPartnerName");
    private final static QName _CustomerUpdateFirstName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "firstName");
    private final static QName _CustomerUpdateTypeOfApplication_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "typeOfApplication");
    private final static QName _CustomerUpdateTitle_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "title");
    private final static QName _CustomerUpdateEmailId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "emailId");
    private final static QName _CustomerUpdateVisaType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "visaType");
    private final static QName _CustomerUpdateSecondaryMacId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "secondaryMacId");
    private final static QName _CustomerUpdateBankAccountNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "bankAccountNo");
    private final static QName _CustomerUpdateEmailIdValidationFlag_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "emailIdValidationFlag");
    private final static QName _CustomerUpdateVehicleRegistrationNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "vehicleRegistrationNo");
    private final static QName _CustomerUpdateBusinessPartnerCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "businessPartnerCode");
    private final static QName _CustomerUpdateOldNetworkPartner_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "OldNetworkPartner");
    private final static QName _CustomerUpdateCrfNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "crfNo");
    private final static QName _CustomerUpdatePassportNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "passportNo");
    private final static QName _CustomerUpdateOrganizationName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "organizationName");
    private final static QName _CustomerUpdateMobileHandsetImieNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "mobileHandsetImieNo");
    private final static QName _CustomerUpdateDeposit_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "deposit");
    private final static QName _CustomerUpdateMobileHandsetMake_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "mobileHandsetMake");
    private final static QName _CustomerUpdateAlternateEmailId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "alternateEmailId");
    private final static QName _CustomerUpdateBillDetailPreference_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "billDetailPreference");
    private final static QName _CustomerUpdateTelephone_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telephone");
    private final static QName _CustomerUpdateLocalContactPersonName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "localContactPersonName");
    private final static QName _CustomerUpdateBankBranch_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "bankBranch");
    private final static QName _CustomerUpdateMobileNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "mobileNo");
    private final static QName _CustomerUpdateFatherName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "fatherName");
    private final static QName _CustomerUpdateAuthorizedSignatoryName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "authorizedSignatoryName");
    private final static QName _CustomerUpdatePropertyType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "propertyType");
    private final static QName _CustomerUpdatePanNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "panNo");
    private final static QName _CustomerUpdateNationality_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "nationality");
    private final static QName _CustomerUpdateMobileHandsetModelNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "mobileHandsetModelNo");
    private final static QName _CustomerUpdateBankAccountType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "bankAccountType");
    private final static QName _CustomerUpdateBankName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "bankName");
    private final static QName _CustomerUpdateNewNetworkPartner_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "NewNetworkPartner");
    private final static QName _CustomerUpdateAlternateNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "alternateNumber");
    private final static QName _CustomerUpdateLastName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "lastName");
    private final static QName _CustomerUpdateMiddleName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "middleName");
    private final static QName _CustomerUpdateMotherName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "motherName");
    private final static QName _CustomerUpdateVehicleMake_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "vehicleMake");
    private final static QName _CustomerUpdateSaleRepresentativeName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "saleRepresentativeName");
    private final static QName _CustomerUpdateCategoryType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "categoryType");
    private final static QName _ClsDisableWalledGardenUserId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "UserId");
    private final static QName _ClsDisableWalledGardenAcctSessionID_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AcctSessionID");
    private final static QName _ClsDisableWalledGardenNASIPAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "NASIPAddress");
    private final static QName _ClsDisableWalledGardenRequestType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RequestType");
    private final static QName _BillDetailsBillingDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillingDetails");
    private final static QName _ApiResponseReturnMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "returnMessage");
    private final static QName _ApiResponseExtTransactionId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "extTransactionId");
    private final static QName _LocalContactAddressState_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "state");
    private final static QName _LocalContactAddressCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "city");
    private final static QName _LocalContactAddressNation_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "nation");
    private final static QName _LocalContactAddressAddressline2_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "addressline2");
    private final static QName _LocalContactAddressAddressline1_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "addressline1");
    private final static QName _LocalContactAddressZipcode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "zipcode");
    private final static QName _CheckPlanFeasibilityResultPlanAllowedYN_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "planAllowedYN");
    private final static QName _CheckPlanFeasibilityResultRemark_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "remark");
    private final static QName _CheckPlanFeasibilityResultMigrationTime_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "migrationTime");
    private final static QName _InstantOfferAllocationChargeName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "chargeName");
    private final static QName _InstantOfferAllocationUsageBoosterPlanCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "usageBoosterPlanCode");
    private final static QName _InstantOfferAllocationSpeedBoosterPlanCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "speedBoosterPlanCode");
    private final static QName _InstantOfferAllocationOfferName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "offerName");
    private final static QName _InstantOfferAllocationItemCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "itemCode");
    private final static QName _InstantOfferAllocationAddonPlanCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "addonPlanCode");
    private final static QName _InstantOfferAllocationPrimaryMacId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "primaryMacId");
    private final static QName _PrBillingDetailsResultBillStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillStatus");
    private final static QName _PrBillingDetailsResultAmountPaid_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AmountPaid");
    private final static QName _PrBillingDetailsResultBillDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillDate");
    private final static QName _PrBillingDetailsResultCustomerNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerNumber");
    private final static QName _PrBillingDetailsResultBillNumber_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillNumber");
    private final static QName _PrBillingDetailsResultBillAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillAmount");
    private final static QName _PrBillingDetailsResultBillPeriod_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillPeriod");
    private final static QName _PrBillingDetailsResultPaymentDueDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PaymentDueDate");
    private final static QName _GetChargeDetailResultChargeName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChargeName");
    private final static QName _GetChargeDetailResultChargeAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ChargeAmount");
    private final static QName _GetCurrentUsageResultAllocatedTimeQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AllocatedTimeQuota");
    private final static QName _GetCurrentUsageResultUsedTimeQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "UsedTimeQuota");
    private final static QName _GetCurrentUsageResultRemainTimeQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RemainTimeQuota");
    private final static QName _GetCurrentUsageResultRemainVolumeQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RemainVolumeQuota");
    private final static QName _GetCurrentUsageResultExpiryDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ExpiryDate");
    private final static QName _GetCurrentUsageResultUsedVolumeQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "UsedVolumeQuota");
    private final static QName _GetCurrentUsageResultAllocatedVolumeQuota_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AllocatedVolumeQuota");
    private final static QName _GetCurrentUsageResultBucket_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Bucket");
    private final static QName _CustomerCancelPaymentRemark_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Remark");
    private final static QName _CustomerCancelPaymentRefTransID_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RefTransID");
    private final static QName _CustomerCancelPaymentAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Amount");
    private final static QName _CustomerCancelPaymentAdjustmentType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AdjustmentType");
    private final static QName _CustomerDetailCurrentSecondryUsage_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "currentSecondryUsage");
    private final static QName _CustomerDetailNasPortId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "nasPortId");
    private final static QName _CustomerDetailDynamicIPPool_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "dynamicIPPool");
    private final static QName _CustomerDetailResellerNWPartner_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "resellerNWPartner");
    private final static QName _CustomerDetailPlanCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "planCode");
    private final static QName _CustomerDetailUsageDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "UsageDetails");
    private final static QName _CustomerDetailCurrentQuotaLimit_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "currentQuotaLimit");
    private final static QName _CustomerDetailPassword_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "password");
    private final static QName _CustomerDetailStaticIP_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "staticIP");
    private final static QName _CustomerDetailCurrentPrimaryUsage_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "currentPrimaryUsage");
    private final static QName _CustomerDetailCurrentBandwidth_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "currentBandwidth");
    private final static QName _TerminationRefundResultBalanceAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BalanceAmount");
    private final static QName _GetDistributorDeviceDetailResultSerialNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "SerialNo");
    private final static QName _GetDistributorDeviceDetailResultMfgUniqueId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "MfgUniqueId");
    private final static QName _GetDistributorDeviceDetailResultSoftwareVersion_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "SoftwareVersion");
    private final static QName _GetDistributorDeviceDetailResultDeviceName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DeviceName");
    private final static QName _GetDistributorDeviceDetailResultModelNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ModelNo");
    private final static QName _GetDistributorDeviceDetailResultDistributor_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Distributor");
    private final static QName _GetDeviceDetailResultUserName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "User_Name");
    private final static QName _GetDeviceDetailResultCustomerNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "customerNo");
    private final static QName _GetDeviceDetailResultOption82_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Option_82");
    private final static QName _GetDeviceDetailResultServiceModel_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Service_Model");
    private final static QName _GetDeviceDetailResultCpePassword_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "cpePassword");
    private final static QName _GetDeviceDetailResultMacId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Mac_Id");
    private final static QName _GetDeviceDetailResultStaticIp_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Static_Ip");
    private final static QName _GetDeviceDetailResultDynamicPool_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DynamicPool");
    private final static QName _GetDeviceDetailResultDeviceOwnerShip_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Device_OwnerShip");
    private final static QName _GetDeviceDetailResultNasPortId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Nas_Port_id");
    private final static QName _ClsAddChargeChargeAmount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "chargeAmount");
    private final static QName _ApiRequestSystemId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "systemId");
    private final static QName _CustomerUsageDetailPlanCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PlanCode");
    private final static QName _CustomerUsageDetailCurrentSessionStartTime_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CurrentSessionStartTime");
    private final static QName _CustomerUsageDetailBillCycleName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillCycleName");
    private final static QName _CustomerUsageDetailSessionStatus_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "SessionStatus");
    private final static QName _CustomerUsageDetailAddOnCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AddOnCode");
    private final static QName _RefferalDetailsResultCustomerNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CustomerNo");
    private final static QName _ChangeAddressAddressLine2_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "addressLine2");
    private final static QName _ChangeAddressZipCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ZipCode");
    private final static QName _ChangeAddressAddressLine1_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "addressLine1");
    private final static QName _ChangeAddressCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "City");
    private final static QName _ChangeAddressState_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "State");
    private final static QName _ChangeAddressNation_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Nation");
    private final static QName _ChangeDeviceDetailDeviceOwnership_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "deviceOwnership");
    private final static QName _ChangeDeviceDetailDynamicPool_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "dynamicPool");
    private final static QName _ChangeDeviceDetailStaticIp_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "staticIp");
    private final static QName _ChangeDeviceDetailServiceModel_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "serviceModel");
    private final static QName _ChangeDeviceDetailOption82_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "option82");
    private final static QName _DeviceDetailResultDeviceDetail_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DeviceDetail");
    private final static QName _GetDiscountDetailsResultIsDeleted_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "IsDeleted");
    private final static QName _GetDiscountDetailsResultDiscountPercent_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Discount_Percent");
    private final static QName _GetDiscountDetailsResultDiscountValue_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DiscountValue");
    private final static QName _GetDiscountDetailsResultPlan_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Plan");
    private final static QName _GetDiscountDetailsResultPaymentMode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PaymentMode");
    private final static QName _GetDiscountDetailsResultRegion_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Region");
    private final static QName _GetDiscountDetailsResultCategory_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Category");
    private final static QName _GetDiscountDetailsResultDiscountApplied_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "DiscountApplied");
    private final static QName _GetDiscountDetailsResultStartDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Start_Date");
    private final static QName _GetDiscountDetailsResultPayModeDiscount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PayModeDiscount");
    private final static QName _GetDiscountDetailsResultEndDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "End_Date");
    private final static QName _GetDiscountDetailsResultDiscountTypeName_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Discount_Type_Name");
    private final static QName _GetDiscountDetailsResultRegionDiscount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RegionDiscount");
    private final static QName _GetDiscountDetailsResultDiscountAbsolute_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Discount_Absolute");
    private final static QName _GetDiscountDetailsResultPlanType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PlanType");
    private final static QName _GetDiscountDetailsResultCategoryDiscount_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CategoryDiscount");
    private final static QName _PaymentAdjustmentBillNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BillNo");
    private final static QName _PaymentAdjustmentAdjustType_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AdjustType");
    private final static QName _PaymentAdjustmentAdjustmentHead_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "AdjustmentHead");
    private final static QName _UserSessionResultCDR_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CDR");
    private final static QName _LastPaymentResultLastPayment_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "LastPayment");
    private final static QName _CustomerPostPaymentPaymethod_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "paymethod");
    private final static QName _CustomerPostPaymentBranch_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "branch");
    private final static QName _CustomerPostPaymentPgwTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "pgwTransId");
    private final static QName _CustomerPostPaymentChqNo_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "chq_No");
    private final static QName _CustomerPostPaymentBankTransID_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "BankTransID");
    private final static QName _CustomerPostPaymentPartnerCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "partnerCode");
    private final static QName _CustomerPostPaymentBank_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Bank");
    private final static QName _CustomerPostPaymentPaymentRefId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "paymentRefId");
    private final static QName _CustomerPostPaymentRefTransid_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "RefTransid");
    private final static QName _CustomerDiscountDetailsDiscountDetails_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "discountDetails");
    private final static QName _GetUserSessionResultCallEnd_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CallEnd");
    private final static QName _GetUserSessionResultCallStart_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "CallStart");
    private final static QName _GetUserSessionResultUsageMB_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "UsageMB");
    private final static QName _GetUserSessionResultMacID_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "MacID");
    private final static QName _GetUserSessionResultTerminateCause_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Terminate_Cause");
    private final static QName _GetUserSessionResultSessionID_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "SessionID");
    private final static QName _GetUserSessionResultIPAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "IPAddress");
    private final static QName _CustomerActivationChargename_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "chargename");
    private final static QName _CustomerActivationDeviceOwner_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "deviceOwner");
    private final static QName _CustomerActivationDynamicIpPool_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "dynamicIpPool");
    private final static QName _InventoryItemToLCOResultItems_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "Items");
    private final static QName _GetLastPaymentResultChqDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "chq_Date");
    private final static QName _GetLastPaymentResultPaymentDate_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "PaymentDate");
    private final static QName _GetLastPaymentResultBank_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "bank");
    private final static QName _GetLastPaymentResultOldBalance_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "oldBalance");
    private final static QName _CustomerTeleserviceBankTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleserviceBankTransId");
    private final static QName _CustomerTeleservicePgTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleservicePgTransId");
    private final static QName _CustomerTelesolutionPaymentCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionPaymentCity");
    private final static QName _CustomerTeleservicePaymentRefId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleservicePaymentRefId");
    private final static QName _CustomerTeleservicePaymentBank_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleservicePaymentBank");
    private final static QName _CustomerTelesolutionBankTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionBankTransId");
    private final static QName _CustomerServiceCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "serviceCode");
    private final static QName _CustomerTelesolutionPaymentBranch_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionPaymentBranch");
    private final static QName _CustomerPrepaidorPostpaidFlag_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "prepaidorPostpaidFlag");
    private final static QName _CustomerTelesolutionPaymentRefId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionPaymentRefId");
    private final static QName _CustomerTelesolutionPgTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionPgTransId");
    private final static QName _CustomerTelesolutionPaymentMode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionPaymentMode");
    private final static QName _CustomerTeleservicePaymentCity_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleservicePaymentCity");
    private final static QName _CustomerInstallationAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "installationAddress");
    private final static QName _CustomerBillingAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "billingAddress");
    private final static QName _CustomerZone_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "zone");
    private final static QName _CustomerTeleservicePaymentMode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleservicePaymentMode");
    private final static QName _CustomerTeleservicePaymentBranch_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleservicePaymentBranch");
    private final static QName _CustomerTelesolutionPaymentBank_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionPaymentBank");
    private final static QName _CustomerTeleserviceReferenceTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "teleserviceReferenceTransId");
    private final static QName _CustomerServiceAddOnPlanCode_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "ServiceAddOnPlanCode");
    private final static QName _CustomerLocalContactAddress_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "localContactAddress");
    private final static QName _CustomerTelesolutionReferenceTransId_QNAME = new QName("http://schemas.datacontract.org/2004/07/", "telesolutionReferenceTransId");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.datacontract.schemas._2004._07
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerBaringExceptionListResult }
     * 
     */
    public CustomerBaringExceptionListResult createCustomerBaringExceptionListResult() {
        return new CustomerBaringExceptionListResult();
    }

    /**
     * Create an instance of {@link P2PReBarringResult }
     * 
     */
    public P2PReBarringResult createP2PReBarringResult() {
        return new P2PReBarringResult();
    }

    /**
     * Create an instance of {@link CustomerNotificationExceptionListResult }
     * 
     */
    public CustomerNotificationExceptionListResult createCustomerNotificationExceptionListResult() {
        return new CustomerNotificationExceptionListResult();
    }

    /**
     * Create an instance of {@link BillDetail }
     * 
     */
    public BillDetail createBillDetail() {
        return new BillDetail();
    }

    /**
     * Create an instance of {@link ArrayOfGetUserSessionResult }
     * 
     */
    public ArrayOfGetUserSessionResult createArrayOfGetUserSessionResult() {
        return new ArrayOfGetUserSessionResult();
    }

    /**
     * Create an instance of {@link ClsCustomerNotificationExceptionList }
     * 
     */
    public ClsCustomerNotificationExceptionList createClsCustomerNotificationExceptionList() {
        return new ClsCustomerNotificationExceptionList();
    }

    /**
     * Create an instance of {@link CustomerUpdate }
     * 
     */
    public CustomerUpdate createCustomerUpdate() {
        return new CustomerUpdate();
    }

    /**
     * Create an instance of {@link ClsDisableWalledGarden }
     * 
     */
    public ClsDisableWalledGarden createClsDisableWalledGarden() {
        return new ClsDisableWalledGarden();
    }

    /**
     * Create an instance of {@link ArrayOfgetCurrentUsageResult }
     * 
     */
    public ArrayOfgetCurrentUsageResult createArrayOfgetCurrentUsageResult() {
        return new ArrayOfgetCurrentUsageResult();
    }

    /**
     * Create an instance of {@link BillDetails }
     * 
     */
    public BillDetails createBillDetails() {
        return new BillDetails();
    }

    /**
     * Create an instance of {@link ClsCustomerUnbaringExceptionList }
     * 
     */
    public ClsCustomerUnbaringExceptionList createClsCustomerUnbaringExceptionList() {
        return new ClsCustomerUnbaringExceptionList();
    }

    /**
     * Create an instance of {@link ArrayOfprBillingDetailsResult }
     * 
     */
    public ArrayOfprBillingDetailsResult createArrayOfprBillingDetailsResult() {
        return new ArrayOfprBillingDetailsResult();
    }

    /**
     * Create an instance of {@link ApiResponse }
     * 
     */
    public ApiResponse createApiResponse() {
        return new ApiResponse();
    }

    /**
     * Create an instance of {@link LocalContactAddress }
     * 
     */
    public LocalContactAddress createLocalContactAddress() {
        return new LocalContactAddress();
    }

    /**
     * Create an instance of {@link ChangeBillCycleResult }
     * 
     */
    public ChangeBillCycleResult createChangeBillCycleResult() {
        return new ChangeBillCycleResult();
    }

    /**
     * Create an instance of {@link CheckPlanFeasibilityResult }
     * 
     */
    public CheckPlanFeasibilityResult createCheckPlanFeasibilityResult() {
        return new CheckPlanFeasibilityResult();
    }

    /**
     * Create an instance of {@link ArrayOfGetDistributorDeviceDetailResult }
     * 
     */
    public ArrayOfGetDistributorDeviceDetailResult createArrayOfGetDistributorDeviceDetailResult() {
        return new ArrayOfGetDistributorDeviceDetailResult();
    }

    /**
     * Create an instance of {@link PrBillingDetailsResult }
     * 
     */
    public PrBillingDetailsResult createPrBillingDetailsResult() {
        return new PrBillingDetailsResult();
    }

    /**
     * Create an instance of {@link InstantOfferAllocation }
     * 
     */
    public InstantOfferAllocation createInstantOfferAllocation() {
        return new InstantOfferAllocation();
    }

    /**
     * Create an instance of {@link GetChargeDetailResult }
     * 
     */
    public GetChargeDetailResult createGetChargeDetailResult() {
        return new GetChargeDetailResult();
    }

    /**
     * Create an instance of {@link GetCurrentUsageResult }
     * 
     */
    public GetCurrentUsageResult createGetCurrentUsageResult() {
        return new GetCurrentUsageResult();
    }

    /**
     * Create an instance of {@link CustomerCancelPayment }
     * 
     */
    public CustomerCancelPayment createCustomerCancelPayment() {
        return new CustomerCancelPayment();
    }

    /**
     * Create an instance of {@link DisableWalledGardenResult }
     * 
     */
    public DisableWalledGardenResult createDisableWalledGardenResult() {
        return new DisableWalledGardenResult();
    }

    /**
     * Create an instance of {@link OfferAllocation }
     * 
     */
    public OfferAllocation createOfferAllocation() {
        return new OfferAllocation();
    }

    /**
     * Create an instance of {@link CustomerUnbaringExceptionListResult }
     * 
     */
    public CustomerUnbaringExceptionListResult createCustomerUnbaringExceptionListResult() {
        return new CustomerUnbaringExceptionListResult();
    }

    /**
     * Create an instance of {@link ClsP2PReBarring }
     * 
     */
    public ClsP2PReBarring createClsP2PReBarring() {
        return new ClsP2PReBarring();
    }

    /**
     * Create an instance of {@link CustomerDetail }
     * 
     */
    public CustomerDetail createCustomerDetail() {
        return new CustomerDetail();
    }

    /**
     * Create an instance of {@link TerminationRefundResult }
     * 
     */
    public TerminationRefundResult createTerminationRefundResult() {
        return new TerminationRefundResult();
    }

    /**
     * Create an instance of {@link GetDistributorDeviceDetailResult }
     * 
     */
    public GetDistributorDeviceDetailResult createGetDistributorDeviceDetailResult() {
        return new GetDistributorDeviceDetailResult();
    }

    /**
     * Create an instance of {@link GetDeviceDetailResult }
     * 
     */
    public GetDeviceDetailResult createGetDeviceDetailResult() {
        return new GetDeviceDetailResult();
    }

    /**
     * Create an instance of {@link ClsAddCharge }
     * 
     */
    public ClsAddCharge createClsAddCharge() {
        return new ClsAddCharge();
    }

    /**
     * Create an instance of {@link CancelPlanMigrationResult }
     * 
     */
    public CancelPlanMigrationResult createCancelPlanMigrationResult() {
        return new CancelPlanMigrationResult();
    }

    /**
     * Create an instance of {@link ApiRequest }
     * 
     */
    public ApiRequest createApiRequest() {
        return new ApiRequest();
    }

    /**
     * Create an instance of {@link CustomerUsageDetail }
     * 
     */
    public CustomerUsageDetail createCustomerUsageDetail() {
        return new CustomerUsageDetail();
    }

    /**
     * Create an instance of {@link RefferalDetailsResult }
     * 
     */
    public RefferalDetailsResult createRefferalDetailsResult() {
        return new RefferalDetailsResult();
    }

    /**
     * Create an instance of {@link CancelAddPostpaidPlanResult }
     * 
     */
    public CancelAddPostpaidPlanResult createCancelAddPostpaidPlanResult() {
        return new CancelAddPostpaidPlanResult();
    }

    /**
     * Create an instance of {@link ChangeAddress }
     * 
     */
    public ChangeAddress createChangeAddress() {
        return new ChangeAddress();
    }

    /**
     * Create an instance of {@link CreateCustomerResult }
     * 
     */
    public CreateCustomerResult createCreateCustomerResult() {
        return new CreateCustomerResult();
    }

    /**
     * Create an instance of {@link ChangeDeviceDetail }
     * 
     */
    public ChangeDeviceDetail createChangeDeviceDetail() {
        return new ChangeDeviceDetail();
    }

    /**
     * Create an instance of {@link DeviceDetailResult }
     * 
     */
    public DeviceDetailResult createDeviceDetailResult() {
        return new DeviceDetailResult();
    }

    /**
     * Create an instance of {@link CustomerActivationResult }
     * 
     */
    public CustomerActivationResult createCustomerActivationResult() {
        return new CustomerActivationResult();
    }

    /**
     * Create an instance of {@link PaymentAdjustmentResult }
     * 
     */
    public PaymentAdjustmentResult createPaymentAdjustmentResult() {
        return new PaymentAdjustmentResult();
    }

    /**
     * Create an instance of {@link GetDiscountDetailsResult }
     * 
     */
    public GetDiscountDetailsResult createGetDiscountDetailsResult() {
        return new GetDiscountDetailsResult();
    }

    /**
     * Create an instance of {@link AddChargeResult }
     * 
     */
    public AddChargeResult createAddChargeResult() {
        return new AddChargeResult();
    }

    /**
     * Create an instance of {@link AddPlan }
     * 
     */
    public AddPlan createAddPlan() {
        return new AddPlan();
    }

    /**
     * Create an instance of {@link PaymentAdjustment }
     * 
     */
    public PaymentAdjustment createPaymentAdjustment() {
        return new PaymentAdjustment();
    }

    /**
     * Create an instance of {@link BillingAddress }
     * 
     */
    public BillingAddress createBillingAddress() {
        return new BillingAddress();
    }

    /**
     * Create an instance of {@link UserSessionResult }
     * 
     */
    public UserSessionResult createUserSessionResult() {
        return new UserSessionResult();
    }

    /**
     * Create an instance of {@link LastPaymentResult }
     * 
     */
    public LastPaymentResult createLastPaymentResult() {
        return new LastPaymentResult();
    }

    /**
     * Create an instance of {@link ChangeBusinessPartnerResult }
     * 
     */
    public ChangeBusinessPartnerResult createChangeBusinessPartnerResult() {
        return new ChangeBusinessPartnerResult();
    }

    /**
     * Create an instance of {@link CustomerSpeedExceptionListResult }
     * 
     */
    public CustomerSpeedExceptionListResult createCustomerSpeedExceptionListResult() {
        return new CustomerSpeedExceptionListResult();
    }

    /**
     * Create an instance of {@link CustomerPostPayment }
     * 
     */
    public CustomerPostPayment createCustomerPostPayment() {
        return new CustomerPostPayment();
    }

    /**
     * Create an instance of {@link CustomerDiscountDetails }
     * 
     */
    public CustomerDiscountDetails createCustomerDiscountDetails() {
        return new CustomerDiscountDetails();
    }

    /**
     * Create an instance of {@link ArrayOfGetChargeDetailResult }
     * 
     */
    public ArrayOfGetChargeDetailResult createArrayOfGetChargeDetailResult() {
        return new ArrayOfGetChargeDetailResult();
    }

    /**
     * Create an instance of {@link GetUserSessionResult }
     * 
     */
    public GetUserSessionResult createGetUserSessionResult() {
        return new GetUserSessionResult();
    }

    /**
     * Create an instance of {@link ClsCustomerBaringExceptionList }
     * 
     */
    public ClsCustomerBaringExceptionList createClsCustomerBaringExceptionList() {
        return new ClsCustomerBaringExceptionList();
    }

    /**
     * Create an instance of {@link ArrayOfGetDiscountDetailsResult }
     * 
     */
    public ArrayOfGetDiscountDetailsResult createArrayOfGetDiscountDetailsResult() {
        return new ArrayOfGetDiscountDetailsResult();
    }

    /**
     * Create an instance of {@link CustomerActivation }
     * 
     */
    public CustomerActivation createCustomerActivation() {
        return new CustomerActivation();
    }

    /**
     * Create an instance of {@link InventoryItemToLCOResult }
     * 
     */
    public InventoryItemToLCOResult createInventoryItemToLCOResult() {
        return new InventoryItemToLCOResult();
    }

    /**
     * Create an instance of {@link ClsCustomerSpeedExceptionList }
     * 
     */
    public ClsCustomerSpeedExceptionList createClsCustomerSpeedExceptionList() {
        return new ClsCustomerSpeedExceptionList();
    }

    /**
     * Create an instance of {@link ClsTerminationRefund }
     * 
     */
    public ClsTerminationRefund createClsTerminationRefund() {
        return new ClsTerminationRefund();
    }

    /**
     * Create an instance of {@link GetLastPaymentResult }
     * 
     */
    public GetLastPaymentResult createGetLastPaymentResult() {
        return new GetLastPaymentResult();
    }

    /**
     * Create an instance of {@link InstallationAddress }
     * 
     */
    public InstallationAddress createInstallationAddress() {
        return new InstallationAddress();
    }

    /**
     * Create an instance of {@link CustomerPostPaymentResult }
     * 
     */
    public CustomerPostPaymentResult createCustomerPostPaymentResult() {
        return new CustomerPostPaymentResult();
    }

    /**
     * Create an instance of {@link AddPlanResult }
     * 
     */
    public AddPlanResult createAddPlanResult() {
        return new AddPlanResult();
    }

    /**
     * Create an instance of {@link Customer }
     * 
     */
    public Customer createCustomer() {
        return new Customer();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerBaringExceptionListResult.class)
    public JAXBElement<String> createCustomerBaringExceptionListResultUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerBaringExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "status", scope = CustomerBaringExceptionListResult.class)
    public JAXBElement<String> createCustomerBaringExceptionListResultStatus(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultStatus_QNAME, String.class, CustomerBaringExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = CustomerBaringExceptionListResult.class)
    public JAXBElement<String> createCustomerBaringExceptionListResultReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, CustomerBaringExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PrBillingDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "pr_BillingDetailsResult")
    public JAXBElement<PrBillingDetailsResult> createPrBillingDetailsResult(PrBillingDetailsResult value) {
        return new JAXBElement<PrBillingDetailsResult>(_PrBillingDetailsResult_QNAME, PrBillingDetailsResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetChargeDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ArrayOfGetChargeDetailResult")
    public JAXBElement<ArrayOfGetChargeDetailResult> createArrayOfGetChargeDetailResult(ArrayOfGetChargeDetailResult value) {
        return new JAXBElement<ArrayOfGetChargeDetailResult>(_ArrayOfGetChargeDetailResult_QNAME, ArrayOfGetChargeDetailResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefferalDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RefferalDetailsResult")
    public JAXBElement<RefferalDetailsResult> createRefferalDetailsResult(RefferalDetailsResult value) {
        return new JAXBElement<RefferalDetailsResult>(_RefferalDetailsResult_QNAME, RefferalDetailsResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "getDeviceDetailResult")
    public JAXBElement<GetDeviceDetailResult> createGetDeviceDetailResult(GetDeviceDetailResult value) {
        return new JAXBElement<GetDeviceDetailResult>(_GetDeviceDetailResult_QNAME, GetDeviceDetailResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UserSessionResult")
    public JAXBElement<UserSessionResult> createUserSessionResult(UserSessionResult value) {
        return new JAXBElement<UserSessionResult>(_UserSessionResult_QNAME, UserSessionResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDiscountDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "GetDiscountDetailsResult")
    public JAXBElement<GetDiscountDetailsResult> createGetDiscountDetailsResult(GetDiscountDetailsResult value) {
        return new JAXBElement<GetDiscountDetailsResult>(_GetDiscountDetailsResult_QNAME, GetDiscountDetailsResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerBaringExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerBaringExceptionListResult")
    public JAXBElement<CustomerBaringExceptionListResult> createCustomerBaringExceptionListResult(CustomerBaringExceptionListResult value) {
        return new JAXBElement<CustomerBaringExceptionListResult>(_CustomerBaringExceptionListResult_QNAME, CustomerBaringExceptionListResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillDetails")
    public JAXBElement<BillDetails> createBillDetails(BillDetails value) {
        return new JAXBElement<BillDetails>(_BillDetails_QNAME, BillDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TerminationRefundResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "TerminationRefundResult")
    public JAXBElement<TerminationRefundResult> createTerminationRefundResult(TerminationRefundResult value) {
        return new JAXBElement<TerminationRefundResult>(_TerminationRefundResult_QNAME, TerminationRefundResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPlanResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AddPlanResult")
    public JAXBElement<AddPlanResult> createAddPlanResult(AddPlanResult value) {
        return new JAXBElement<AddPlanResult>(_AddPlanResult_QNAME, AddPlanResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ApiResponse")
    public JAXBElement<ApiResponse> createApiResponse(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_ApiResponse_QNAME, ApiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfprBillingDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ArrayOfpr_BillingDetailsResult")
    public JAXBElement<ArrayOfprBillingDetailsResult> createArrayOfprBillingDetailsResult(ArrayOfprBillingDetailsResult value) {
        return new JAXBElement<ArrayOfprBillingDetailsResult>(_ArrayOfprBillingDetailsResult_QNAME, ArrayOfprBillingDetailsResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalContactAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "LocalContactAddress")
    public JAXBElement<LocalContactAddress> createLocalContactAddress(LocalContactAddress value) {
        return new JAXBElement<LocalContactAddress>(_LocalContactAddress_QNAME, LocalContactAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeBusinessPartnerResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChangeBusinessPartnerResult")
    public JAXBElement<ChangeBusinessPartnerResult> createChangeBusinessPartnerResult(ChangeBusinessPartnerResult value) {
        return new JAXBElement<ChangeBusinessPartnerResult>(_ChangeBusinessPartnerResult_QNAME, ChangeBusinessPartnerResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link P2PReBarringResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "P2PReBarringResult")
    public JAXBElement<P2PReBarringResult> createP2PReBarringResult(P2PReBarringResult value) {
        return new JAXBElement<P2PReBarringResult>(_P2PReBarringResult_QNAME, P2PReBarringResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNotificationExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerNotificationExceptionListResult")
    public JAXBElement<CustomerNotificationExceptionListResult> createCustomerNotificationExceptionListResult(CustomerNotificationExceptionListResult value) {
        return new JAXBElement<CustomerNotificationExceptionListResult>(_CustomerNotificationExceptionListResult_QNAME, CustomerNotificationExceptionListResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerUpdate")
    public JAXBElement<CustomerUpdate> createCustomerUpdate(CustomerUpdate value) {
        return new JAXBElement<CustomerUpdate>(_CustomerUpdate_QNAME, CustomerUpdate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPlanFeasibilityResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CheckPlanFeasibilityResult")
    public JAXBElement<CheckPlanFeasibilityResult> createCheckPlanFeasibilityResult(CheckPlanFeasibilityResult value) {
        return new JAXBElement<CheckPlanFeasibilityResult>(_CheckPlanFeasibilityResult_QNAME, CheckPlanFeasibilityResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfgetCurrentUsageResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ArrayOfgetCurrentUsageResult")
    public JAXBElement<ArrayOfgetCurrentUsageResult> createArrayOfgetCurrentUsageResult(ArrayOfgetCurrentUsageResult value) {
        return new JAXBElement<ArrayOfgetCurrentUsageResult>(_ArrayOfgetCurrentUsageResult_QNAME, ArrayOfgetCurrentUsageResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeDeviceDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChangeDeviceDetail")
    public JAXBElement<ChangeDeviceDetail> createChangeDeviceDetail(ChangeDeviceDetail value) {
        return new JAXBElement<ChangeDeviceDetail>(_ChangeDeviceDetail_QNAME, ChangeDeviceDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerUnbaringExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsCustomerUnbaringExceptionList")
    public JAXBElement<ClsCustomerUnbaringExceptionList> createClsCustomerUnbaringExceptionList(ClsCustomerUnbaringExceptionList value) {
        return new JAXBElement<ClsCustomerUnbaringExceptionList>(_ClsCustomerUnbaringExceptionList_QNAME, ClsCustomerUnbaringExceptionList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDistributorDeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "GetDistributorDeviceDetailResult")
    public JAXBElement<GetDistributorDeviceDetailResult> createGetDistributorDeviceDetailResult(GetDistributorDeviceDetailResult value) {
        return new JAXBElement<GetDistributorDeviceDetailResult>(_GetDistributorDeviceDetailResult_QNAME, GetDistributorDeviceDetailResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChargeDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "GetChargeDetailResult")
    public JAXBElement<GetChargeDetailResult> createGetChargeDetailResult(GetChargeDetailResult value) {
        return new JAXBElement<GetChargeDetailResult>(_GetChargeDetailResult_QNAME, GetChargeDetailResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillingAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillingAddress")
    public JAXBElement<BillingAddress> createBillingAddress(BillingAddress value) {
        return new JAXBElement<BillingAddress>(_BillingAddress_QNAME, BillingAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelPlanMigrationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CancelPlanMigrationResult")
    public JAXBElement<CancelPlanMigrationResult> createCancelPlanMigrationResult(CancelPlanMigrationResult value) {
        return new JAXBElement<CancelPlanMigrationResult>(_CancelPlanMigrationResult_QNAME, CancelPlanMigrationResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableWalledGardenResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DisableWalledGardenResult")
    public JAXBElement<DisableWalledGardenResult> createDisableWalledGardenResult(DisableWalledGardenResult value) {
        return new JAXBElement<DisableWalledGardenResult>(_DisableWalledGardenResult_QNAME, DisableWalledGardenResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsP2PReBarring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsP2PReBarring")
    public JAXBElement<ClsP2PReBarring> createClsP2PReBarring(ClsP2PReBarring value) {
        return new JAXBElement<ClsP2PReBarring>(_ClsP2PReBarring_QNAME, ClsP2PReBarring.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetDiscountDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ArrayOfGetDiscountDetailsResult")
    public JAXBElement<ArrayOfGetDiscountDetailsResult> createArrayOfGetDiscountDetailsResult(ArrayOfGetDiscountDetailsResult value) {
        return new JAXBElement<ArrayOfGetDiscountDetailsResult>(_ArrayOfGetDiscountDetailsResult_QNAME, ArrayOfGetDiscountDetailsResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeAddressAddressType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChangeAddress.AddressType")
    public JAXBElement<ChangeAddressAddressType> createChangeAddressAddressType(ChangeAddressAddressType value) {
        return new JAXBElement<ChangeAddressAddressType>(_ChangeAddressAddressType_QNAME, ChangeAddressAddressType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAddPostpaidPlanResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CancelAddPostpaidPlanResult")
    public JAXBElement<CancelAddPostpaidPlanResult> createCancelAddPostpaidPlanResult(CancelAddPostpaidPlanResult value) {
        return new JAXBElement<CancelAddPostpaidPlanResult>(_CancelAddPostpaidPlanResult_QNAME, CancelAddPostpaidPlanResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddChargeResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AddChargeResult")
    public JAXBElement<AddChargeResult> createAddChargeResult(AddChargeResult value) {
        return new JAXBElement<AddChargeResult>(_AddChargeResult_QNAME, AddChargeResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerActivation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerActivation")
    public JAXBElement<CustomerActivation> createCustomerActivation(CustomerActivation value) {
        return new JAXBElement<CustomerActivation>(_CustomerActivation_QNAME, CustomerActivation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstallationAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "InstallationAddress")
    public JAXBElement<InstallationAddress> createInstallationAddress(InstallationAddress value) {
        return new JAXBElement<InstallationAddress>(_InstallationAddress_QNAME, InstallationAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsTerminationRefund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsTerminationRefund")
    public JAXBElement<ClsTerminationRefund> createClsTerminationRefund(ClsTerminationRefund value) {
        return new JAXBElement<ClsTerminationRefund>(_ClsTerminationRefund_QNAME, ClsTerminationRefund.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerUnbaringExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerUnbaringExceptionListResult")
    public JAXBElement<CustomerUnbaringExceptionListResult> createCustomerUnbaringExceptionListResult(CustomerUnbaringExceptionListResult value) {
        return new JAXBElement<CustomerUnbaringExceptionListResult>(_CustomerUnbaringExceptionListResult_QNAME, CustomerUnbaringExceptionListResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLastPaymentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "getLastPaymentResult")
    public JAXBElement<GetLastPaymentResult> createGetLastPaymentResult(GetLastPaymentResult value) {
        return new JAXBElement<GetLastPaymentResult>(_GetLastPaymentResult_QNAME, GetLastPaymentResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentAdjustment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PaymentAdjustment")
    public JAXBElement<PaymentAdjustment> createPaymentAdjustment(PaymentAdjustment value) {
        return new JAXBElement<PaymentAdjustment>(_PaymentAdjustment_QNAME, PaymentAdjustment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentAdjustmentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PaymentAdjustmentResult")
    public JAXBElement<PaymentAdjustmentResult> createPaymentAdjustmentResult(PaymentAdjustmentResult value) {
        return new JAXBElement<PaymentAdjustmentResult>(_PaymentAdjustmentResult_QNAME, PaymentAdjustmentResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferAllocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "OfferAllocation")
    public JAXBElement<OfferAllocation> createOfferAllocation(OfferAllocation value) {
        return new JAXBElement<OfferAllocation>(_OfferAllocation_QNAME, OfferAllocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillDetail")
    public JAXBElement<BillDetail> createBillDetail(BillDetail value) {
        return new JAXBElement<BillDetail>(_BillDetail_QNAME, BillDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LastPaymentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "LastPaymentResult")
    public JAXBElement<LastPaymentResult> createLastPaymentResult(LastPaymentResult value) {
        return new JAXBElement<LastPaymentResult>(_LastPaymentResult_QNAME, LastPaymentResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerNotificationExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsCustomerNotificationExceptionList")
    public JAXBElement<ClsCustomerNotificationExceptionList> createClsCustomerNotificationExceptionList(ClsCustomerNotificationExceptionList value) {
        return new JAXBElement<ClsCustomerNotificationExceptionList>(_ClsCustomerNotificationExceptionList_QNAME, ClsCustomerNotificationExceptionList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCustomerResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CreateCustomerResult")
    public JAXBElement<CreateCustomerResult> createCreateCustomerResult(CreateCustomerResult value) {
        return new JAXBElement<CreateCustomerResult>(_CreateCustomerResult_QNAME, CreateCustomerResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerActivationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerActivationResult")
    public JAXBElement<CustomerActivationResult> createCustomerActivationResult(CustomerActivationResult value) {
        return new JAXBElement<CustomerActivationResult>(_CustomerActivationResult_QNAME, CustomerActivationResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DeviceDetailResult")
    public JAXBElement<DeviceDetailResult> createDeviceDetailResult(DeviceDetailResult value) {
        return new JAXBElement<DeviceDetailResult>(_DeviceDetailResult_QNAME, DeviceDetailResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeBillCycleResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChangeBillCycleResult")
    public JAXBElement<ChangeBillCycleResult> createChangeBillCycleResult(ChangeBillCycleResult value) {
        return new JAXBElement<ChangeBillCycleResult>(_ChangeBillCycleResult_QNAME, ChangeBillCycleResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetUserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ArrayOfGetUserSessionResult")
    public JAXBElement<ArrayOfGetUserSessionResult> createArrayOfGetUserSessionResult(ArrayOfGetUserSessionResult value) {
        return new JAXBElement<ArrayOfGetUserSessionResult>(_ArrayOfGetUserSessionResult_QNAME, ArrayOfGetUserSessionResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsDisableWalledGarden }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsDisableWalledGarden")
    public JAXBElement<ClsDisableWalledGarden> createClsDisableWalledGarden(ClsDisableWalledGarden value) {
        return new JAXBElement<ClsDisableWalledGarden>(_ClsDisableWalledGarden_QNAME, ClsDisableWalledGarden.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InventoryItemToLCOResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "InventoryItemToLCOResult")
    public JAXBElement<InventoryItemToLCOResult> createInventoryItemToLCOResult(InventoryItemToLCOResult value) {
        return new JAXBElement<InventoryItemToLCOResult>(_InventoryItemToLCOResult_QNAME, InventoryItemToLCOResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerPostPaymentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerPostPaymentResult")
    public JAXBElement<CustomerPostPaymentResult> createCustomerPostPaymentResult(CustomerPostPaymentResult value) {
        return new JAXBElement<CustomerPostPaymentResult>(_CustomerPostPaymentResult_QNAME, CustomerPostPaymentResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "GetUserSessionResult")
    public JAXBElement<GetUserSessionResult> createGetUserSessionResult(GetUserSessionResult value) {
        return new JAXBElement<GetUserSessionResult>(_GetUserSessionResult_QNAME, GetUserSessionResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentUsageResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "getCurrentUsageResult")
    public JAXBElement<GetCurrentUsageResult> createGetCurrentUsageResult(GetCurrentUsageResult value) {
        return new JAXBElement<GetCurrentUsageResult>(_GetCurrentUsageResult_QNAME, GetCurrentUsageResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetDistributorDeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ArrayOfGetDistributorDeviceDetailResult")
    public JAXBElement<ArrayOfGetDistributorDeviceDetailResult> createArrayOfGetDistributorDeviceDetailResult(ArrayOfGetDistributorDeviceDetailResult value) {
        return new JAXBElement<ArrayOfGetDistributorDeviceDetailResult>(_ArrayOfGetDistributorDeviceDetailResult_QNAME, ArrayOfGetDistributorDeviceDetailResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerDiscountDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerDiscountDetails")
    public JAXBElement<CustomerDiscountDetails> createCustomerDiscountDetails(CustomerDiscountDetails value) {
        return new JAXBElement<CustomerDiscountDetails>(_CustomerDiscountDetails_QNAME, CustomerDiscountDetails.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ApiRequest")
    public JAXBElement<ApiRequest> createApiRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_ApiRequest_QNAME, ApiRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AddPlan")
    public JAXBElement<AddPlan> createAddPlan(AddPlan value) {
        return new JAXBElement<AddPlan>(_AddPlan_QNAME, AddPlan.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerSpeedExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsCustomerSpeedExceptionList")
    public JAXBElement<ClsCustomerSpeedExceptionList> createClsCustomerSpeedExceptionList(ClsCustomerSpeedExceptionList value) {
        return new JAXBElement<ClsCustomerSpeedExceptionList>(_ClsCustomerSpeedExceptionList_QNAME, ClsCustomerSpeedExceptionList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerUsageDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerUsageDetail")
    public JAXBElement<CustomerUsageDetail> createCustomerUsageDetail(CustomerUsageDetail value) {
        return new JAXBElement<CustomerUsageDetail>(_CustomerUsageDetail_QNAME, CustomerUsageDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsAddCharge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsAddCharge")
    public JAXBElement<ClsAddCharge> createClsAddCharge(ClsAddCharge value) {
        return new JAXBElement<ClsAddCharge>(_ClsAddCharge_QNAME, ClsAddCharge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerBaringExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "clsCustomerBaringExceptionList")
    public JAXBElement<ClsCustomerBaringExceptionList> createClsCustomerBaringExceptionList(ClsCustomerBaringExceptionList value) {
        return new JAXBElement<ClsCustomerBaringExceptionList>(_ClsCustomerBaringExceptionList_QNAME, ClsCustomerBaringExceptionList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerSpeedExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerSpeedExceptionListResult")
    public JAXBElement<CustomerSpeedExceptionListResult> createCustomerSpeedExceptionListResult(CustomerSpeedExceptionListResult value) {
        return new JAXBElement<CustomerSpeedExceptionListResult>(_CustomerSpeedExceptionListResult_QNAME, CustomerSpeedExceptionListResult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerDetail")
    public JAXBElement<CustomerDetail> createCustomerDetail(CustomerDetail value) {
        return new JAXBElement<CustomerDetail>(_CustomerDetail_QNAME, CustomerDetail.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Customer")
    public JAXBElement<Customer> createCustomer(Customer value) {
        return new JAXBElement<Customer>(_Customer_QNAME, Customer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantOfferAllocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "InstantOfferAllocation")
    public JAXBElement<InstantOfferAllocation> createInstantOfferAllocation(InstantOfferAllocation value) {
        return new JAXBElement<InstantOfferAllocation>(_InstantOfferAllocation_QNAME, InstantOfferAllocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChangeAddress")
    public JAXBElement<ChangeAddress> createChangeAddress(ChangeAddress value) {
        return new JAXBElement<ChangeAddress>(_ChangeAddress_QNAME, ChangeAddress.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerCancelPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerCancelPayment")
    public JAXBElement<CustomerCancelPayment> createCustomerCancelPayment(CustomerCancelPayment value) {
        return new JAXBElement<CustomerCancelPayment>(_CustomerCancelPayment_QNAME, CustomerCancelPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerPostPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerPostPayment")
    public JAXBElement<CustomerPostPayment> createCustomerPostPayment(CustomerPostPayment value) {
        return new JAXBElement<CustomerPostPayment>(_CustomerPostPayment_QNAME, CustomerPostPayment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerNotificationExceptionListResult.class)
    public JAXBElement<String> createCustomerNotificationExceptionListResultUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerNotificationExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = CustomerNotificationExceptionListResult.class)
    public JAXBElement<String> createCustomerNotificationExceptionListResultReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, CustomerNotificationExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "customerName", scope = BillDetail.class)
    public JAXBElement<String> createBillDetailCustomerName(String value) {
        return new JAXBElement<String>(_BillDetailCustomerName_QNAME, String.class, BillDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetChargeDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargesDetail", scope = BillDetail.class)
    public JAXBElement<ArrayOfGetChargeDetailResult> createBillDetailChargesDetail(ArrayOfGetChargeDetailResult value) {
        return new JAXBElement<ArrayOfGetChargeDetailResult>(_BillDetailChargesDetail_QNAME, ArrayOfGetChargeDetailResult.class, BillDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "billNumber", scope = BillDetail.class)
    public JAXBElement<String> createBillDetailBillNumber(String value) {
        return new JAXBElement<String>(_BillDetailBillNumber_QNAME, String.class, BillDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "action", scope = ClsCustomerNotificationExceptionList.class)
    public JAXBElement<String> createClsCustomerNotificationExceptionListAction(String value) {
        return new JAXBElement<String>(_ClsCustomerNotificationExceptionListAction_QNAME, String.class, ClsCustomerNotificationExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = ClsCustomerNotificationExceptionList.class)
    public JAXBElement<String> createClsCustomerNotificationExceptionListReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, ClsCustomerNotificationExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "gender", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateGender(String value) {
        return new JAXBElement<String>(_CustomerUpdateGender_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "localContactPersonContactNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateLocalContactPersonContactNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateLocalContactPersonContactNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "saleRepresentativeCode", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateSaleRepresentativeCode(String value) {
        return new JAXBElement<String>(_CustomerUpdateSaleRepresentativeCode_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Zone", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateZone(String value) {
        return new JAXBElement<String>(_CustomerUpdateZone_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "businessPartnerName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBusinessPartnerName(String value) {
        return new JAXBElement<String>(_CustomerUpdateBusinessPartnerName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "firstName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateFirstName(String value) {
        return new JAXBElement<String>(_CustomerUpdateFirstName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "typeOfApplication", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateTypeOfApplication(String value) {
        return new JAXBElement<String>(_CustomerUpdateTypeOfApplication_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "title", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateTitle(String value) {
        return new JAXBElement<String>(_CustomerUpdateTitle_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "emailId", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateEmailId(String value) {
        return new JAXBElement<String>(_CustomerUpdateEmailId_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "visaType", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateVisaType(String value) {
        return new JAXBElement<String>(_CustomerUpdateVisaType_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "secondaryMacId", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateSecondaryMacId(String value) {
        return new JAXBElement<String>(_CustomerUpdateSecondaryMacId_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankAccountNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBankAccountNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankAccountNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "emailIdValidationFlag", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateEmailIdValidationFlag(String value) {
        return new JAXBElement<String>(_CustomerUpdateEmailIdValidationFlag_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "vehicleRegistrationNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateVehicleRegistrationNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateVehicleRegistrationNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "businessPartnerCode", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBusinessPartnerCode(String value) {
        return new JAXBElement<String>(_CustomerUpdateBusinessPartnerCode_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "OldNetworkPartner", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateOldNetworkPartner(String value) {
        return new JAXBElement<String>(_CustomerUpdateOldNetworkPartner_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "crfNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateCrfNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateCrfNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "passportNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdatePassportNo(String value) {
        return new JAXBElement<String>(_CustomerUpdatePassportNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "organizationName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateOrganizationName(String value) {
        return new JAXBElement<String>(_CustomerUpdateOrganizationName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileHandsetImieNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateMobileHandsetImieNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileHandsetImieNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "deposit", scope = CustomerUpdate.class)
    public JAXBElement<BigDecimal> createCustomerUpdateDeposit(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CustomerUpdateDeposit_QNAME, BigDecimal.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileHandsetMake", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateMobileHandsetMake(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileHandsetMake_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "alternateEmailId", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateAlternateEmailId(String value) {
        return new JAXBElement<String>(_CustomerUpdateAlternateEmailId_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "billDetailPreference", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBillDetailPreference(String value) {
        return new JAXBElement<String>(_CustomerUpdateBillDetailPreference_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telephone", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateTelephone(String value) {
        return new JAXBElement<String>(_CustomerUpdateTelephone_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "localContactPersonName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateLocalContactPersonName(String value) {
        return new JAXBElement<String>(_CustomerUpdateLocalContactPersonName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankBranch", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBankBranch(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankBranch_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateMobileNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "fatherName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateFatherName(String value) {
        return new JAXBElement<String>(_CustomerUpdateFatherName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "authorizedSignatoryName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateAuthorizedSignatoryName(String value) {
        return new JAXBElement<String>(_CustomerUpdateAuthorizedSignatoryName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "propertyType", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdatePropertyType(String value) {
        return new JAXBElement<String>(_CustomerUpdatePropertyType_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "panNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdatePanNo(String value) {
        return new JAXBElement<String>(_CustomerUpdatePanNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nationality", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateNationality(String value) {
        return new JAXBElement<String>(_CustomerUpdateNationality_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileHandsetModelNo", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateMobileHandsetModelNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileHandsetModelNo_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankAccountType", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBankAccountType(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankAccountType_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateBankName(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "NewNetworkPartner", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateNewNetworkPartner(String value) {
        return new JAXBElement<String>(_CustomerUpdateNewNetworkPartner_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "alternateNumber", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateAlternateNumber(String value) {
        return new JAXBElement<String>(_CustomerUpdateAlternateNumber_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "lastName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateLastName(String value) {
        return new JAXBElement<String>(_CustomerUpdateLastName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "middleName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateMiddleName(String value) {
        return new JAXBElement<String>(_CustomerUpdateMiddleName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "motherName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateMotherName(String value) {
        return new JAXBElement<String>(_CustomerUpdateMotherName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "vehicleMake", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateVehicleMake(String value) {
        return new JAXBElement<String>(_CustomerUpdateVehicleMake_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "saleRepresentativeName", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateSaleRepresentativeName(String value) {
        return new JAXBElement<String>(_CustomerUpdateSaleRepresentativeName_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "categoryType", scope = CustomerUpdate.class)
    public JAXBElement<String> createCustomerUpdateCategoryType(String value) {
        return new JAXBElement<String>(_CustomerUpdateCategoryType_QNAME, String.class, CustomerUpdate.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UserId", scope = ClsDisableWalledGarden.class)
    public JAXBElement<String> createClsDisableWalledGardenUserId(String value) {
        return new JAXBElement<String>(_ClsDisableWalledGardenUserId_QNAME, String.class, ClsDisableWalledGarden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AcctSessionID", scope = ClsDisableWalledGarden.class)
    public JAXBElement<String> createClsDisableWalledGardenAcctSessionID(String value) {
        return new JAXBElement<String>(_ClsDisableWalledGardenAcctSessionID_QNAME, String.class, ClsDisableWalledGarden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "NASIPAddress", scope = ClsDisableWalledGarden.class)
    public JAXBElement<String> createClsDisableWalledGardenNASIPAddress(String value) {
        return new JAXBElement<String>(_ClsDisableWalledGardenNASIPAddress_QNAME, String.class, ClsDisableWalledGarden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RequestType", scope = ClsDisableWalledGarden.class)
    public JAXBElement<String> createClsDisableWalledGardenRequestType(String value) {
        return new JAXBElement<String>(_ClsDisableWalledGardenRequestType_QNAME, String.class, ClsDisableWalledGarden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfprBillingDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillingDetails", scope = BillDetails.class)
    public JAXBElement<ArrayOfprBillingDetailsResult> createBillDetailsBillingDetails(ArrayOfprBillingDetailsResult value) {
        return new JAXBElement<ArrayOfprBillingDetailsResult>(_BillDetailsBillingDetails_QNAME, ArrayOfprBillingDetailsResult.class, BillDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "action", scope = ClsCustomerUnbaringExceptionList.class)
    public JAXBElement<String> createClsCustomerUnbaringExceptionListAction(String value) {
        return new JAXBElement<String>(_ClsCustomerNotificationExceptionListAction_QNAME, String.class, ClsCustomerUnbaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = ClsCustomerUnbaringExceptionList.class)
    public JAXBElement<String> createClsCustomerUnbaringExceptionListReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, ClsCustomerUnbaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "returnMessage", scope = ApiResponse.class)
    public JAXBElement<String> createApiResponseReturnMessage(String value) {
        return new JAXBElement<String>(_ApiResponseReturnMessage_QNAME, String.class, ApiResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "extTransactionId", scope = ApiResponse.class)
    public JAXBElement<String> createApiResponseExtTransactionId(String value) {
        return new JAXBElement<String>(_ApiResponseExtTransactionId_QNAME, String.class, ApiResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "state", scope = LocalContactAddress.class)
    public JAXBElement<String> createLocalContactAddressState(String value) {
        return new JAXBElement<String>(_LocalContactAddressState_QNAME, String.class, LocalContactAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "city", scope = LocalContactAddress.class)
    public JAXBElement<String> createLocalContactAddressCity(String value) {
        return new JAXBElement<String>(_LocalContactAddressCity_QNAME, String.class, LocalContactAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nation", scope = LocalContactAddress.class)
    public JAXBElement<String> createLocalContactAddressNation(String value) {
        return new JAXBElement<String>(_LocalContactAddressNation_QNAME, String.class, LocalContactAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressline2", scope = LocalContactAddress.class)
    public JAXBElement<String> createLocalContactAddressAddressline2(String value) {
        return new JAXBElement<String>(_LocalContactAddressAddressline2_QNAME, String.class, LocalContactAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressline1", scope = LocalContactAddress.class)
    public JAXBElement<String> createLocalContactAddressAddressline1(String value) {
        return new JAXBElement<String>(_LocalContactAddressAddressline1_QNAME, String.class, LocalContactAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "zipcode", scope = LocalContactAddress.class)
    public JAXBElement<String> createLocalContactAddressZipcode(String value) {
        return new JAXBElement<String>(_LocalContactAddressZipcode_QNAME, String.class, LocalContactAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "planAllowedYN", scope = CheckPlanFeasibilityResult.class)
    public JAXBElement<String> createCheckPlanFeasibilityResultPlanAllowedYN(String value) {
        return new JAXBElement<String>(_CheckPlanFeasibilityResultPlanAllowedYN_QNAME, String.class, CheckPlanFeasibilityResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "remark", scope = CheckPlanFeasibilityResult.class)
    public JAXBElement<String> createCheckPlanFeasibilityResultRemark(String value) {
        return new JAXBElement<String>(_CheckPlanFeasibilityResultRemark_QNAME, String.class, CheckPlanFeasibilityResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "migrationTime", scope = CheckPlanFeasibilityResult.class)
    public JAXBElement<String> createCheckPlanFeasibilityResultMigrationTime(String value) {
        return new JAXBElement<String>(_CheckPlanFeasibilityResultMigrationTime_QNAME, String.class, CheckPlanFeasibilityResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargeName", scope = InstantOfferAllocation.class)
    public JAXBElement<ArrayOfstring> createInstantOfferAllocationChargeName(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationChargeName_QNAME, ArrayOfstring.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "usageBoosterPlanCode", scope = InstantOfferAllocation.class)
    public JAXBElement<ArrayOfstring> createInstantOfferAllocationUsageBoosterPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationUsageBoosterPlanCode_QNAME, ArrayOfstring.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "speedBoosterPlanCode", scope = InstantOfferAllocation.class)
    public JAXBElement<ArrayOfstring> createInstantOfferAllocationSpeedBoosterPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationSpeedBoosterPlanCode_QNAME, ArrayOfstring.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "offerName", scope = InstantOfferAllocation.class)
    public JAXBElement<String> createInstantOfferAllocationOfferName(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationOfferName_QNAME, String.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "itemCode", scope = InstantOfferAllocation.class)
    public JAXBElement<ArrayOfstring> createInstantOfferAllocationItemCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationItemCode_QNAME, ArrayOfstring.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addonPlanCode", scope = InstantOfferAllocation.class)
    public JAXBElement<ArrayOfstring> createInstantOfferAllocationAddonPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationAddonPlanCode_QNAME, ArrayOfstring.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "primaryMacId", scope = InstantOfferAllocation.class)
    public JAXBElement<String> createInstantOfferAllocationPrimaryMacId(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationPrimaryMacId_QNAME, String.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "secondaryMacId", scope = InstantOfferAllocation.class)
    public JAXBElement<String> createInstantOfferAllocationSecondaryMacId(String value) {
        return new JAXBElement<String>(_CustomerUpdateSecondaryMacId_QNAME, String.class, InstantOfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillStatus", scope = PrBillingDetailsResult.class)
    public JAXBElement<String> createPrBillingDetailsResultBillStatus(String value) {
        return new JAXBElement<String>(_PrBillingDetailsResultBillStatus_QNAME, String.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AmountPaid", scope = PrBillingDetailsResult.class)
    public JAXBElement<BigDecimal> createPrBillingDetailsResultAmountPaid(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PrBillingDetailsResultAmountPaid_QNAME, BigDecimal.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillDate", scope = PrBillingDetailsResult.class)
    public JAXBElement<String> createPrBillingDetailsResultBillDate(String value) {
        return new JAXBElement<String>(_PrBillingDetailsResultBillDate_QNAME, String.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerNumber", scope = PrBillingDetailsResult.class)
    public JAXBElement<String> createPrBillingDetailsResultCustomerNumber(String value) {
        return new JAXBElement<String>(_PrBillingDetailsResultCustomerNumber_QNAME, String.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillNumber", scope = PrBillingDetailsResult.class)
    public JAXBElement<String> createPrBillingDetailsResultBillNumber(String value) {
        return new JAXBElement<String>(_PrBillingDetailsResultBillNumber_QNAME, String.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillAmount", scope = PrBillingDetailsResult.class)
    public JAXBElement<BigDecimal> createPrBillingDetailsResultBillAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_PrBillingDetailsResultBillAmount_QNAME, BigDecimal.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillPeriod", scope = PrBillingDetailsResult.class)
    public JAXBElement<String> createPrBillingDetailsResultBillPeriod(String value) {
        return new JAXBElement<String>(_PrBillingDetailsResultBillPeriod_QNAME, String.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PaymentDueDate", scope = PrBillingDetailsResult.class)
    public JAXBElement<String> createPrBillingDetailsResultPaymentDueDate(String value) {
        return new JAXBElement<String>(_PrBillingDetailsResultPaymentDueDate_QNAME, String.class, PrBillingDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChargeName", scope = GetChargeDetailResult.class)
    public JAXBElement<String> createGetChargeDetailResultChargeName(String value) {
        return new JAXBElement<String>(_GetChargeDetailResultChargeName_QNAME, String.class, GetChargeDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ChargeAmount", scope = GetChargeDetailResult.class)
    public JAXBElement<BigDecimal> createGetChargeDetailResultChargeAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetChargeDetailResultChargeAmount_QNAME, BigDecimal.class, GetChargeDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AllocatedTimeQuota", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultAllocatedTimeQuota(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultAllocatedTimeQuota_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UsedTimeQuota", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultUsedTimeQuota(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultUsedTimeQuota_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RemainTimeQuota", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultRemainTimeQuota(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultRemainTimeQuota_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RemainVolumeQuota", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultRemainVolumeQuota(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultRemainVolumeQuota_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ExpiryDate", scope = GetCurrentUsageResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetCurrentUsageResultExpiryDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetCurrentUsageResultExpiryDate_QNAME, XMLGregorianCalendar.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UsedVolumeQuota", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultUsedVolumeQuota(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultUsedVolumeQuota_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AllocatedVolumeQuota", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultAllocatedVolumeQuota(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultAllocatedVolumeQuota_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Bucket", scope = GetCurrentUsageResult.class)
    public JAXBElement<String> createGetCurrentUsageResultBucket(String value) {
        return new JAXBElement<String>(_GetCurrentUsageResultBucket_QNAME, String.class, GetCurrentUsageResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Remark", scope = CustomerCancelPayment.class)
    public JAXBElement<String> createCustomerCancelPaymentRemark(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentRemark_QNAME, String.class, CustomerCancelPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RefTransID", scope = CustomerCancelPayment.class)
    public JAXBElement<String> createCustomerCancelPaymentRefTransID(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentRefTransID_QNAME, String.class, CustomerCancelPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Amount", scope = CustomerCancelPayment.class)
    public JAXBElement<String> createCustomerCancelPaymentAmount(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentAmount_QNAME, String.class, CustomerCancelPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AdjustmentType", scope = CustomerCancelPayment.class)
    public JAXBElement<String> createCustomerCancelPaymentAdjustmentType(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentAdjustmentType_QNAME, String.class, CustomerCancelPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargeName", scope = OfferAllocation.class)
    public JAXBElement<ArrayOfstring> createOfferAllocationChargeName(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationChargeName_QNAME, ArrayOfstring.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "usageBoosterPlanCode", scope = OfferAllocation.class)
    public JAXBElement<ArrayOfstring> createOfferAllocationUsageBoosterPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationUsageBoosterPlanCode_QNAME, ArrayOfstring.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "speedBoosterPlanCode", scope = OfferAllocation.class)
    public JAXBElement<ArrayOfstring> createOfferAllocationSpeedBoosterPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationSpeedBoosterPlanCode_QNAME, ArrayOfstring.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "offerName", scope = OfferAllocation.class)
    public JAXBElement<String> createOfferAllocationOfferName(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationOfferName_QNAME, String.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "itemCode", scope = OfferAllocation.class)
    public JAXBElement<ArrayOfstring> createOfferAllocationItemCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationItemCode_QNAME, ArrayOfstring.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addonPlanCode", scope = OfferAllocation.class)
    public JAXBElement<ArrayOfstring> createOfferAllocationAddonPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationAddonPlanCode_QNAME, ArrayOfstring.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "primaryMacId", scope = OfferAllocation.class)
    public JAXBElement<String> createOfferAllocationPrimaryMacId(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationPrimaryMacId_QNAME, String.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "secondaryMacId", scope = OfferAllocation.class)
    public JAXBElement<String> createOfferAllocationSecondaryMacId(String value) {
        return new JAXBElement<String>(_CustomerUpdateSecondaryMacId_QNAME, String.class, OfferAllocation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerUnbaringExceptionListResult.class)
    public JAXBElement<String> createCustomerUnbaringExceptionListResultUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerUnbaringExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "status", scope = CustomerUnbaringExceptionListResult.class)
    public JAXBElement<String> createCustomerUnbaringExceptionListResultStatus(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultStatus_QNAME, String.class, CustomerUnbaringExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = CustomerUnbaringExceptionListResult.class)
    public JAXBElement<String> createCustomerUnbaringExceptionListResultReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, CustomerUnbaringExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "currentSecondryUsage", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailCurrentSecondryUsage(String value) {
        return new JAXBElement<String>(_CustomerDetailCurrentSecondryUsage_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nasPortId", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailNasPortId(String value) {
        return new JAXBElement<String>(_CustomerDetailNasPortId_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "dynamicIPPool", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailDynamicIPPool(String value) {
        return new JAXBElement<String>(_CustomerDetailDynamicIPPool_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "resellerNWPartner", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailResellerNWPartner(String value) {
        return new JAXBElement<String>(_CustomerDetailResellerNWPartner_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "secondaryMacId", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailSecondaryMacId(String value) {
        return new JAXBElement<String>(_CustomerUpdateSecondaryMacId_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "planCode", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailPlanCode(String value) {
        return new JAXBElement<String>(_CustomerDetailPlanCode_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfgetCurrentUsageResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UsageDetails", scope = CustomerDetail.class)
    public JAXBElement<ArrayOfgetCurrentUsageResult> createCustomerDetailUsageDetails(ArrayOfgetCurrentUsageResult value) {
        return new JAXBElement<ArrayOfgetCurrentUsageResult>(_CustomerDetailUsageDetails_QNAME, ArrayOfgetCurrentUsageResult.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "currentQuotaLimit", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailCurrentQuotaLimit(String value) {
        return new JAXBElement<String>(_CustomerDetailCurrentQuotaLimit_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "password", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailPassword(String value) {
        return new JAXBElement<String>(_CustomerDetailPassword_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "staticIP", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailStaticIP(String value) {
        return new JAXBElement<String>(_CustomerDetailStaticIP_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "currentPrimaryUsage", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailCurrentPrimaryUsage(String value) {
        return new JAXBElement<String>(_CustomerDetailCurrentPrimaryUsage_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "currentBandwidth", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailCurrentBandwidth(String value) {
        return new JAXBElement<String>(_CustomerDetailCurrentBandwidth_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "status", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailStatus(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultStatus_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "primaryMacId", scope = CustomerDetail.class)
    public JAXBElement<String> createCustomerDetailPrimaryMacId(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationPrimaryMacId_QNAME, String.class, CustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BalanceAmount", scope = TerminationRefundResult.class)
    public JAXBElement<BigDecimal> createTerminationRefundResultBalanceAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_TerminationRefundResultBalanceAmount_QNAME, BigDecimal.class, TerminationRefundResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "SerialNo", scope = GetDistributorDeviceDetailResult.class)
    public JAXBElement<String> createGetDistributorDeviceDetailResultSerialNo(String value) {
        return new JAXBElement<String>(_GetDistributorDeviceDetailResultSerialNo_QNAME, String.class, GetDistributorDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "MfgUniqueId", scope = GetDistributorDeviceDetailResult.class)
    public JAXBElement<String> createGetDistributorDeviceDetailResultMfgUniqueId(String value) {
        return new JAXBElement<String>(_GetDistributorDeviceDetailResultMfgUniqueId_QNAME, String.class, GetDistributorDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "SoftwareVersion", scope = GetDistributorDeviceDetailResult.class)
    public JAXBElement<String> createGetDistributorDeviceDetailResultSoftwareVersion(String value) {
        return new JAXBElement<String>(_GetDistributorDeviceDetailResultSoftwareVersion_QNAME, String.class, GetDistributorDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DeviceName", scope = GetDistributorDeviceDetailResult.class)
    public JAXBElement<String> createGetDistributorDeviceDetailResultDeviceName(String value) {
        return new JAXBElement<String>(_GetDistributorDeviceDetailResultDeviceName_QNAME, String.class, GetDistributorDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ModelNo", scope = GetDistributorDeviceDetailResult.class)
    public JAXBElement<String> createGetDistributorDeviceDetailResultModelNo(String value) {
        return new JAXBElement<String>(_GetDistributorDeviceDetailResultModelNo_QNAME, String.class, GetDistributorDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Distributor", scope = GetDistributorDeviceDetailResult.class)
    public JAXBElement<String> createGetDistributorDeviceDetailResultDistributor(String value) {
        return new JAXBElement<String>(_GetDistributorDeviceDetailResultDistributor_QNAME, String.class, GetDistributorDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "User_Name", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultUserName(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultUserName_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "customerNo", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultCustomerNo(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultCustomerNo_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Option_82", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultOption82(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultOption82_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Service_Model", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultServiceModel(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultServiceModel_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "cpePassword", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultCpePassword(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultCpePassword_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Mac_Id", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultMacId(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultMacId_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Static_Ip", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultStaticIp(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultStaticIp_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DynamicPool", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultDynamicPool(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultDynamicPool_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Device_OwnerShip", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultDeviceOwnerShip(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultDeviceOwnerShip_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Nas_Port_id", scope = GetDeviceDetailResult.class)
    public JAXBElement<String> createGetDeviceDetailResultNasPortId(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultNasPortId_QNAME, String.class, GetDeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargeName", scope = ClsAddCharge.class)
    public JAXBElement<String> createClsAddChargeChargeName(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationChargeName_QNAME, String.class, ClsAddCharge.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargeAmount", scope = ClsAddCharge.class)
    public JAXBElement<BigDecimal> createClsAddChargeChargeAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_ClsAddChargeChargeAmount_QNAME, BigDecimal.class, ClsAddCharge.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "systemId", scope = ApiRequest.class)
    public JAXBElement<String> createApiRequestSystemId(String value) {
        return new JAXBElement<String>(_ApiRequestSystemId_QNAME, String.class, ApiRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "password", scope = ApiRequest.class)
    public JAXBElement<String> createApiRequestPassword(String value) {
        return new JAXBElement<String>(_CustomerDetailPassword_QNAME, String.class, ApiRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "extTransactionId", scope = ApiRequest.class)
    public JAXBElement<String> createApiRequestExtTransactionId(String value) {
        return new JAXBElement<String>(_ApiResponseExtTransactionId_QNAME, String.class, ApiRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerUsageDetail.class)
    public JAXBElement<String> createCustomerUsageDetailUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PlanCode", scope = CustomerUsageDetail.class)
    public JAXBElement<String> createCustomerUsageDetailPlanCode(String value) {
        return new JAXBElement<String>(_CustomerUsageDetailPlanCode_QNAME, String.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CurrentSessionStartTime", scope = CustomerUsageDetail.class)
    public JAXBElement<XMLGregorianCalendar> createCustomerUsageDetailCurrentSessionStartTime(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CustomerUsageDetailCurrentSessionStartTime_QNAME, XMLGregorianCalendar.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillCycleName", scope = CustomerUsageDetail.class)
    public JAXBElement<String> createCustomerUsageDetailBillCycleName(String value) {
        return new JAXBElement<String>(_CustomerUsageDetailBillCycleName_QNAME, String.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "SessionStatus", scope = CustomerUsageDetail.class)
    public JAXBElement<String> createCustomerUsageDetailSessionStatus(String value) {
        return new JAXBElement<String>(_CustomerUsageDetailSessionStatus_QNAME, String.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "status", scope = CustomerUsageDetail.class)
    public JAXBElement<String> createCustomerUsageDetailStatus(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultStatus_QNAME, String.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AddOnCode", scope = CustomerUsageDetail.class)
    public JAXBElement<String> createCustomerUsageDetailAddOnCode(String value) {
        return new JAXBElement<String>(_CustomerUsageDetailAddOnCode_QNAME, String.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfgetCurrentUsageResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UsageDetails", scope = CustomerUsageDetail.class)
    public JAXBElement<ArrayOfgetCurrentUsageResult> createCustomerUsageDetailUsageDetails(ArrayOfgetCurrentUsageResult value) {
        return new JAXBElement<ArrayOfgetCurrentUsageResult>(_CustomerDetailUsageDetails_QNAME, ArrayOfgetCurrentUsageResult.class, CustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CustomerNo", scope = RefferalDetailsResult.class)
    public JAXBElement<ArrayOfstring> createRefferalDetailsResultCustomerNo(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_RefferalDetailsResultCustomerNo_QNAME, ArrayOfstring.class, RefferalDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressLine2", scope = ChangeAddress.class)
    public JAXBElement<String> createChangeAddressAddressLine2(String value) {
        return new JAXBElement<String>(_ChangeAddressAddressLine2_QNAME, String.class, ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ZipCode", scope = ChangeAddress.class)
    public JAXBElement<String> createChangeAddressZipCode(String value) {
        return new JAXBElement<String>(_ChangeAddressZipCode_QNAME, String.class, ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressLine1", scope = ChangeAddress.class)
    public JAXBElement<String> createChangeAddressAddressLine1(String value) {
        return new JAXBElement<String>(_ChangeAddressAddressLine1_QNAME, String.class, ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "City", scope = ChangeAddress.class)
    public JAXBElement<String> createChangeAddressCity(String value) {
        return new JAXBElement<String>(_ChangeAddressCity_QNAME, String.class, ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "State", scope = ChangeAddress.class)
    public JAXBElement<String> createChangeAddressState(String value) {
        return new JAXBElement<String>(_ChangeAddressState_QNAME, String.class, ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Nation", scope = ChangeAddress.class)
    public JAXBElement<String> createChangeAddressNation(String value) {
        return new JAXBElement<String>(_ChangeAddressNation_QNAME, String.class, ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "deviceOwnership", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailDeviceOwnership(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailDeviceOwnership_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "dynamicPool", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailDynamicPool(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailDynamicPool_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nasPortId", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailNasPortId(String value) {
        return new JAXBElement<String>(_CustomerDetailNasPortId_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "staticIp", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailStaticIp(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailStaticIp_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "cpePassword", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailCpePassword(String value) {
        return new JAXBElement<String>(_GetDeviceDetailResultCpePassword_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "serviceModel", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailServiceModel(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailServiceModel_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "primaryMacId", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailPrimaryMacId(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationPrimaryMacId_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "secondaryMacId", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailSecondaryMacId(String value) {
        return new JAXBElement<String>(_CustomerUpdateSecondaryMacId_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "option82", scope = ChangeDeviceDetail.class)
    public JAXBElement<String> createChangeDeviceDetailOption82(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailOption82_QNAME, String.class, ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DeviceDetail", scope = DeviceDetailResult.class)
    public JAXBElement<GetDeviceDetailResult> createDeviceDetailResultDeviceDetail(GetDeviceDetailResult value) {
        return new JAXBElement<GetDeviceDetailResult>(_DeviceDetailResultDeviceDetail_QNAME, GetDeviceDetailResult.class, DeviceDetailResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "status", scope = CustomerActivationResult.class)
    public JAXBElement<String> createCustomerActivationResultStatus(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultStatus_QNAME, String.class, CustomerActivationResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "IsDeleted", scope = GetDiscountDetailsResult.class)
    public JAXBElement<Boolean> createGetDiscountDetailsResultIsDeleted(Boolean value) {
        return new JAXBElement<Boolean>(_GetDiscountDetailsResultIsDeleted_QNAME, Boolean.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Discount_Percent", scope = GetDiscountDetailsResult.class)
    public JAXBElement<BigDecimal> createGetDiscountDetailsResultDiscountPercent(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetDiscountDetailsResultDiscountPercent_QNAME, BigDecimal.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DiscountValue", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultDiscountValue(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultDiscountValue_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Plan", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultPlan(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultPlan_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PaymentMode", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultPaymentMode(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultPaymentMode_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Region", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultRegion(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultRegion_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Category", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultCategory(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultCategory_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "DiscountApplied", scope = GetDiscountDetailsResult.class)
    public JAXBElement<BigDecimal> createGetDiscountDetailsResultDiscountApplied(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetDiscountDetailsResultDiscountApplied_QNAME, BigDecimal.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Start_Date", scope = GetDiscountDetailsResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetDiscountDetailsResultStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetDiscountDetailsResultStartDate_QNAME, XMLGregorianCalendar.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PayModeDiscount", scope = GetDiscountDetailsResult.class)
    public JAXBElement<BigDecimal> createGetDiscountDetailsResultPayModeDiscount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetDiscountDetailsResultPayModeDiscount_QNAME, BigDecimal.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "End_Date", scope = GetDiscountDetailsResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetDiscountDetailsResultEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetDiscountDetailsResultEndDate_QNAME, XMLGregorianCalendar.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Discount_Type_Name", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultDiscountTypeName(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultDiscountTypeName_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RegionDiscount", scope = GetDiscountDetailsResult.class)
    public JAXBElement<BigDecimal> createGetDiscountDetailsResultRegionDiscount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetDiscountDetailsResultRegionDiscount_QNAME, BigDecimal.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Discount_Absolute", scope = GetDiscountDetailsResult.class)
    public JAXBElement<BigDecimal> createGetDiscountDetailsResultDiscountAbsolute(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetDiscountDetailsResultDiscountAbsolute_QNAME, BigDecimal.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PlanType", scope = GetDiscountDetailsResult.class)
    public JAXBElement<String> createGetDiscountDetailsResultPlanType(String value) {
        return new JAXBElement<String>(_GetDiscountDetailsResultPlanType_QNAME, String.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CategoryDiscount", scope = GetDiscountDetailsResult.class)
    public JAXBElement<BigDecimal> createGetDiscountDetailsResultCategoryDiscount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetDiscountDetailsResultCategoryDiscount_QNAME, BigDecimal.class, GetDiscountDetailsResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "planCode", scope = AddPlan.class)
    public JAXBElement<String> createAddPlanPlanCode(String value) {
        return new JAXBElement<String>(_CustomerDetailPlanCode_QNAME, String.class, AddPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Remark", scope = PaymentAdjustment.class)
    public JAXBElement<String> createPaymentAdjustmentRemark(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentRemark_QNAME, String.class, PaymentAdjustment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BillNo", scope = PaymentAdjustment.class)
    public JAXBElement<String> createPaymentAdjustmentBillNo(String value) {
        return new JAXBElement<String>(_PaymentAdjustmentBillNo_QNAME, String.class, PaymentAdjustment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AdjustType", scope = PaymentAdjustment.class)
    public JAXBElement<String> createPaymentAdjustmentAdjustType(String value) {
        return new JAXBElement<String>(_PaymentAdjustmentAdjustType_QNAME, String.class, PaymentAdjustment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AdjustmentHead", scope = PaymentAdjustment.class)
    public JAXBElement<String> createPaymentAdjustmentAdjustmentHead(String value) {
        return new JAXBElement<String>(_PaymentAdjustmentAdjustmentHead_QNAME, String.class, PaymentAdjustment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "state", scope = BillingAddress.class)
    public JAXBElement<String> createBillingAddressState(String value) {
        return new JAXBElement<String>(_LocalContactAddressState_QNAME, String.class, BillingAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "city", scope = BillingAddress.class)
    public JAXBElement<String> createBillingAddressCity(String value) {
        return new JAXBElement<String>(_LocalContactAddressCity_QNAME, String.class, BillingAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nation", scope = BillingAddress.class)
    public JAXBElement<String> createBillingAddressNation(String value) {
        return new JAXBElement<String>(_LocalContactAddressNation_QNAME, String.class, BillingAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressline2", scope = BillingAddress.class)
    public JAXBElement<String> createBillingAddressAddressline2(String value) {
        return new JAXBElement<String>(_LocalContactAddressAddressline2_QNAME, String.class, BillingAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressline1", scope = BillingAddress.class)
    public JAXBElement<String> createBillingAddressAddressline1(String value) {
        return new JAXBElement<String>(_LocalContactAddressAddressline1_QNAME, String.class, BillingAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "zipcode", scope = BillingAddress.class)
    public JAXBElement<String> createBillingAddressZipcode(String value) {
        return new JAXBElement<String>(_LocalContactAddressZipcode_QNAME, String.class, BillingAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetUserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CDR", scope = UserSessionResult.class)
    public JAXBElement<ArrayOfGetUserSessionResult> createUserSessionResultCDR(ArrayOfGetUserSessionResult value) {
        return new JAXBElement<ArrayOfGetUserSessionResult>(_UserSessionResultCDR_QNAME, ArrayOfGetUserSessionResult.class, UserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLastPaymentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "LastPayment", scope = LastPaymentResult.class)
    public JAXBElement<GetLastPaymentResult> createLastPaymentResultLastPayment(GetLastPaymentResult value) {
        return new JAXBElement<GetLastPaymentResult>(_LastPaymentResultLastPayment_QNAME, GetLastPaymentResult.class, LastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerSpeedExceptionListResult.class)
    public JAXBElement<String> createCustomerSpeedExceptionListResultUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerSpeedExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = CustomerSpeedExceptionListResult.class)
    public JAXBElement<String> createCustomerSpeedExceptionListResultReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, CustomerSpeedExceptionListResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "paymethod", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentPaymethod(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentPaymethod_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "branch", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentBranch(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentBranch_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "pgwTransId", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentPgwTransId(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentPgwTransId_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Remark", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentRemark(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentRemark_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chq_No", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentChqNo(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentChqNo_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "City", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentCity(String value) {
        return new JAXBElement<String>(_ChangeAddressCity_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "BankTransID", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentBankTransID(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentBankTransID_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "partnerCode", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentPartnerCode(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentPartnerCode_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Bank", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentBank(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentBank_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "paymentRefId", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentPaymentRefId(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentPaymentRefId_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "RefTransid", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentRefTransid(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentRefTransid_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "AdjustmentType", scope = CustomerPostPayment.class)
    public JAXBElement<String> createCustomerPostPaymentAdjustmentType(String value) {
        return new JAXBElement<String>(_CustomerCancelPaymentAdjustmentType_QNAME, String.class, CustomerPostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetDiscountDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "discountDetails", scope = CustomerDiscountDetails.class)
    public JAXBElement<ArrayOfGetDiscountDetailsResult> createCustomerDiscountDetailsDiscountDetails(ArrayOfGetDiscountDetailsResult value) {
        return new JAXBElement<ArrayOfGetDiscountDetailsResult>(_CustomerDiscountDetailsDiscountDetails_QNAME, ArrayOfGetDiscountDetailsResult.class, CustomerDiscountDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CallEnd", scope = GetUserSessionResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetUserSessionResultCallEnd(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetUserSessionResultCallEnd_QNAME, XMLGregorianCalendar.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "CallStart", scope = GetUserSessionResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetUserSessionResultCallStart(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetUserSessionResultCallStart_QNAME, XMLGregorianCalendar.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Long }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "UsageMB", scope = GetUserSessionResult.class)
    public JAXBElement<Long> createGetUserSessionResultUsageMB(Long value) {
        return new JAXBElement<Long>(_GetUserSessionResultUsageMB_QNAME, Long.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "MacID", scope = GetUserSessionResult.class)
    public JAXBElement<String> createGetUserSessionResultMacID(String value) {
        return new JAXBElement<String>(_GetUserSessionResultMacID_QNAME, String.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Terminate_Cause", scope = GetUserSessionResult.class)
    public JAXBElement<String> createGetUserSessionResultTerminateCause(String value) {
        return new JAXBElement<String>(_GetUserSessionResultTerminateCause_QNAME, String.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "SessionID", scope = GetUserSessionResult.class)
    public JAXBElement<String> createGetUserSessionResultSessionID(String value) {
        return new JAXBElement<String>(_GetUserSessionResultSessionID_QNAME, String.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "IPAddress", scope = GetUserSessionResult.class)
    public JAXBElement<String> createGetUserSessionResultIPAddress(String value) {
        return new JAXBElement<String>(_GetUserSessionResultIPAddress_QNAME, String.class, GetUserSessionResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "action", scope = ClsCustomerBaringExceptionList.class)
    public JAXBElement<String> createClsCustomerBaringExceptionListAction(String value) {
        return new JAXBElement<String>(_ClsCustomerNotificationExceptionListAction_QNAME, String.class, ClsCustomerBaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = ClsCustomerBaringExceptionList.class)
    public JAXBElement<String> createClsCustomerBaringExceptionListReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, ClsCustomerBaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "userName", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationUserName(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultUserName_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nasPortId", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationNasPortId(String value) {
        return new JAXBElement<String>(_CustomerDetailNasPortId_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "password", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationPassword(String value) {
        return new JAXBElement<String>(_CustomerDetailPassword_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargename", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationChargename(String value) {
        return new JAXBElement<String>(_CustomerActivationChargename_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "staticIp", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationStaticIp(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailStaticIp_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "deviceOwner", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationDeviceOwner(String value) {
        return new JAXBElement<String>(_CustomerActivationDeviceOwner_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "resellerNWPartner", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationResellerNWPartner(String value) {
        return new JAXBElement<String>(_CustomerDetailResellerNWPartner_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "crfNo", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationCrfNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateCrfNo_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "primaryMacId", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationPrimaryMacId(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationPrimaryMacId_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "secondaryMacId", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationSecondaryMacId(String value) {
        return new JAXBElement<String>(_CustomerUpdateSecondaryMacId_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "option82", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationOption82(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailOption82_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "dynamicIpPool", scope = CustomerActivation.class)
    public JAXBElement<String> createCustomerActivationDynamicIpPool(String value) {
        return new JAXBElement<String>(_CustomerActivationDynamicIpPool_QNAME, String.class, CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfGetDistributorDeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "Items", scope = InventoryItemToLCOResult.class)
    public JAXBElement<ArrayOfGetDistributorDeviceDetailResult> createInventoryItemToLCOResultItems(ArrayOfGetDistributorDeviceDetailResult value) {
        return new JAXBElement<ArrayOfGetDistributorDeviceDetailResult>(_InventoryItemToLCOResultItems_QNAME, ArrayOfGetDistributorDeviceDetailResult.class, InventoryItemToLCOResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "action", scope = ClsCustomerSpeedExceptionList.class)
    public JAXBElement<String> createClsCustomerSpeedExceptionListAction(String value) {
        return new JAXBElement<String>(_ClsCustomerNotificationExceptionListAction_QNAME, String.class, ClsCustomerSpeedExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "reason", scope = ClsCustomerSpeedExceptionList.class)
    public JAXBElement<String> createClsCustomerSpeedExceptionListReason(String value) {
        return new JAXBElement<String>(_CustomerBaringExceptionListResultReason_QNAME, String.class, ClsCustomerSpeedExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "branch", scope = GetLastPaymentResult.class)
    public JAXBElement<String> createGetLastPaymentResultBranch(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentBranch_QNAME, String.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chq_Date", scope = GetLastPaymentResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetLastPaymentResultChqDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetLastPaymentResultChqDate_QNAME, XMLGregorianCalendar.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "PaymentDate", scope = GetLastPaymentResult.class)
    public JAXBElement<XMLGregorianCalendar> createGetLastPaymentResultPaymentDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetLastPaymentResultPaymentDate_QNAME, XMLGregorianCalendar.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bank", scope = GetLastPaymentResult.class)
    public JAXBElement<String> createGetLastPaymentResultBank(String value) {
        return new JAXBElement<String>(_GetLastPaymentResultBank_QNAME, String.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "city", scope = GetLastPaymentResult.class)
    public JAXBElement<String> createGetLastPaymentResultCity(String value) {
        return new JAXBElement<String>(_LocalContactAddressCity_QNAME, String.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chq_No", scope = GetLastPaymentResult.class)
    public JAXBElement<String> createGetLastPaymentResultChqNo(String value) {
        return new JAXBElement<String>(_CustomerPostPaymentChqNo_QNAME, String.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "remark", scope = GetLastPaymentResult.class)
    public JAXBElement<String> createGetLastPaymentResultRemark(String value) {
        return new JAXBElement<String>(_CheckPlanFeasibilityResultRemark_QNAME, String.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "oldBalance", scope = GetLastPaymentResult.class)
    public JAXBElement<BigDecimal> createGetLastPaymentResultOldBalance(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_GetLastPaymentResultOldBalance_QNAME, BigDecimal.class, GetLastPaymentResult.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "state", scope = InstallationAddress.class)
    public JAXBElement<String> createInstallationAddressState(String value) {
        return new JAXBElement<String>(_LocalContactAddressState_QNAME, String.class, InstallationAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "city", scope = InstallationAddress.class)
    public JAXBElement<String> createInstallationAddressCity(String value) {
        return new JAXBElement<String>(_LocalContactAddressCity_QNAME, String.class, InstallationAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nation", scope = InstallationAddress.class)
    public JAXBElement<String> createInstallationAddressNation(String value) {
        return new JAXBElement<String>(_LocalContactAddressNation_QNAME, String.class, InstallationAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressline2", scope = InstallationAddress.class)
    public JAXBElement<String> createInstallationAddressAddressline2(String value) {
        return new JAXBElement<String>(_LocalContactAddressAddressline2_QNAME, String.class, InstallationAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addressline1", scope = InstallationAddress.class)
    public JAXBElement<String> createInstallationAddressAddressline1(String value) {
        return new JAXBElement<String>(_LocalContactAddressAddressline1_QNAME, String.class, InstallationAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "zipcode", scope = InstallationAddress.class)
    public JAXBElement<String> createInstallationAddressZipcode(String value) {
        return new JAXBElement<String>(_LocalContactAddressZipcode_QNAME, String.class, InstallationAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "gender", scope = Customer.class)
    public JAXBElement<String> createCustomerGender(String value) {
        return new JAXBElement<String>(_CustomerUpdateGender_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "localContactPersonContactNo", scope = Customer.class)
    public JAXBElement<String> createCustomerLocalContactPersonContactNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateLocalContactPersonContactNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "speedBoosterPlanCode", scope = Customer.class)
    public JAXBElement<ArrayOfstring> createCustomerSpeedBoosterPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationSpeedBoosterPlanCode_QNAME, ArrayOfstring.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "itemCode", scope = Customer.class)
    public JAXBElement<ArrayOfstring> createCustomerItemCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationItemCode_QNAME, ArrayOfstring.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleserviceBankTransId", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleserviceBankTransId(String value) {
        return new JAXBElement<String>(_CustomerTeleserviceBankTransId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleservicePgTransId", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleservicePgTransId(String value) {
        return new JAXBElement<String>(_CustomerTeleservicePgTransId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "emailId", scope = Customer.class)
    public JAXBElement<String> createCustomerEmailId(String value) {
        return new JAXBElement<String>(_CustomerUpdateEmailId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "visaType", scope = Customer.class)
    public JAXBElement<String> createCustomerVisaType(String value) {
        return new JAXBElement<String>(_CustomerUpdateVisaType_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionPaymentCity", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionPaymentCity(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionPaymentCity_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "emailIdValidationFlag", scope = Customer.class)
    public JAXBElement<String> createCustomerEmailIdValidationFlag(String value) {
        return new JAXBElement<String>(_CustomerUpdateEmailIdValidationFlag_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "businessPartnerCode", scope = Customer.class)
    public JAXBElement<String> createCustomerBusinessPartnerCode(String value) {
        return new JAXBElement<String>(_CustomerUpdateBusinessPartnerCode_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleservicePaymentRefId", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleservicePaymentRefId(String value) {
        return new JAXBElement<String>(_CustomerTeleservicePaymentRefId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleservicePaymentBank", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleservicePaymentBank(String value) {
        return new JAXBElement<String>(_CustomerTeleservicePaymentBank_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "passportNo", scope = Customer.class)
    public JAXBElement<String> createCustomerPassportNo(String value) {
        return new JAXBElement<String>(_CustomerUpdatePassportNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileHandsetImieNo", scope = Customer.class)
    public JAXBElement<String> createCustomerMobileHandsetImieNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileHandsetImieNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileHandsetMake", scope = Customer.class)
    public JAXBElement<String> createCustomerMobileHandsetMake(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileHandsetMake_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionBankTransId", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionBankTransId(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionBankTransId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "serviceCode", scope = Customer.class)
    public JAXBElement<String> createCustomerServiceCode(String value) {
        return new JAXBElement<String>(_CustomerServiceCode_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "chargeName", scope = Customer.class)
    public JAXBElement<ArrayOfstring> createCustomerChargeName(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationChargeName_QNAME, ArrayOfstring.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "localContactPersonName", scope = Customer.class)
    public JAXBElement<String> createCustomerLocalContactPersonName(String value) {
        return new JAXBElement<String>(_CustomerUpdateLocalContactPersonName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileHandsetModelNo", scope = Customer.class)
    public JAXBElement<String> createCustomerMobileHandsetModelNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileHandsetModelNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "panNo", scope = Customer.class)
    public JAXBElement<String> createCustomerPanNo(String value) {
        return new JAXBElement<String>(_CustomerUpdatePanNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "serviceModel", scope = Customer.class)
    public JAXBElement<String> createCustomerServiceModel(String value) {
        return new JAXBElement<String>(_ChangeDeviceDetailServiceModel_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "usageBoosterPlanCode", scope = Customer.class)
    public JAXBElement<ArrayOfstring> createCustomerUsageBoosterPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationUsageBoosterPlanCode_QNAME, ArrayOfstring.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionPaymentBranch", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionPaymentBranch(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionPaymentBranch_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankName", scope = Customer.class)
    public JAXBElement<String> createCustomerBankName(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "alternateNumber", scope = Customer.class)
    public JAXBElement<String> createCustomerAlternateNumber(String value) {
        return new JAXBElement<String>(_CustomerUpdateAlternateNumber_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "prepaidorPostpaidFlag", scope = Customer.class)
    public JAXBElement<String> createCustomerPrepaidorPostpaidFlag(String value) {
        return new JAXBElement<String>(_CustomerPrepaidorPostpaidFlag_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "lastName", scope = Customer.class)
    public JAXBElement<String> createCustomerLastName(String value) {
        return new JAXBElement<String>(_CustomerUpdateLastName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "middleName", scope = Customer.class)
    public JAXBElement<String> createCustomerMiddleName(String value) {
        return new JAXBElement<String>(_CustomerUpdateMiddleName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "motherName", scope = Customer.class)
    public JAXBElement<String> createCustomerMotherName(String value) {
        return new JAXBElement<String>(_CustomerUpdateMotherName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "vehicleMake", scope = Customer.class)
    public JAXBElement<String> createCustomerVehicleMake(String value) {
        return new JAXBElement<String>(_CustomerUpdateVehicleMake_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "categoryType", scope = Customer.class)
    public JAXBElement<String> createCustomerCategoryType(String value) {
        return new JAXBElement<String>(_CustomerUpdateCategoryType_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "saleRepresentativeName", scope = Customer.class)
    public JAXBElement<String> createCustomerSaleRepresentativeName(String value) {
        return new JAXBElement<String>(_CustomerUpdateSaleRepresentativeName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "saleRepresentativeCode", scope = Customer.class)
    public JAXBElement<String> createCustomerSaleRepresentativeCode(String value) {
        return new JAXBElement<String>(_CustomerUpdateSaleRepresentativeCode_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "businessPartnerName", scope = Customer.class)
    public JAXBElement<String> createCustomerBusinessPartnerName(String value) {
        return new JAXBElement<String>(_CustomerUpdateBusinessPartnerName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "offerName", scope = Customer.class)
    public JAXBElement<String> createCustomerOfferName(String value) {
        return new JAXBElement<String>(_InstantOfferAllocationOfferName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionPaymentRefId", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionPaymentRefId(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionPaymentRefId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "firstName", scope = Customer.class)
    public JAXBElement<String> createCustomerFirstName(String value) {
        return new JAXBElement<String>(_CustomerUpdateFirstName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionPgTransId", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionPgTransId(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionPgTransId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "typeOfApplication", scope = Customer.class)
    public JAXBElement<String> createCustomerTypeOfApplication(String value) {
        return new JAXBElement<String>(_CustomerUpdateTypeOfApplication_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "title", scope = Customer.class)
    public JAXBElement<String> createCustomerTitle(String value) {
        return new JAXBElement<String>(_CustomerUpdateTitle_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankAccountNo", scope = Customer.class)
    public JAXBElement<String> createCustomerBankAccountNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankAccountNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionPaymentMode", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionPaymentMode(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionPaymentMode_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "vehicleRegistrationNo", scope = Customer.class)
    public JAXBElement<String> createCustomerVehicleRegistrationNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateVehicleRegistrationNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "addonPlanCode", scope = Customer.class)
    public JAXBElement<ArrayOfstring> createCustomerAddonPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_InstantOfferAllocationAddonPlanCode_QNAME, ArrayOfstring.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "crfNo", scope = Customer.class)
    public JAXBElement<String> createCustomerCrfNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateCrfNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleservicePaymentCity", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleservicePaymentCity(String value) {
        return new JAXBElement<String>(_CustomerTeleservicePaymentCity_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstallationAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "installationAddress", scope = Customer.class)
    public JAXBElement<InstallationAddress> createCustomerInstallationAddress(InstallationAddress value) {
        return new JAXBElement<InstallationAddress>(_CustomerInstallationAddress_QNAME, InstallationAddress.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "organizationName", scope = Customer.class)
    public JAXBElement<String> createCustomerOrganizationName(String value) {
        return new JAXBElement<String>(_CustomerUpdateOrganizationName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "alternateEmailId", scope = Customer.class)
    public JAXBElement<String> createCustomerAlternateEmailId(String value) {
        return new JAXBElement<String>(_CustomerUpdateAlternateEmailId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "billDetailPreference", scope = Customer.class)
    public JAXBElement<String> createCustomerBillDetailPreference(String value) {
        return new JAXBElement<String>(_CustomerUpdateBillDetailPreference_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillingAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "billingAddress", scope = Customer.class)
    public JAXBElement<BillingAddress> createCustomerBillingAddress(BillingAddress value) {
        return new JAXBElement<BillingAddress>(_CustomerBillingAddress_QNAME, BillingAddress.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telephone", scope = Customer.class)
    public JAXBElement<String> createCustomerTelephone(String value) {
        return new JAXBElement<String>(_CustomerUpdateTelephone_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankBranch", scope = Customer.class)
    public JAXBElement<String> createCustomerBankBranch(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankBranch_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "zone", scope = Customer.class)
    public JAXBElement<String> createCustomerZone(String value) {
        return new JAXBElement<String>(_CustomerZone_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "fatherName", scope = Customer.class)
    public JAXBElement<String> createCustomerFatherName(String value) {
        return new JAXBElement<String>(_CustomerUpdateFatherName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "mobileNo", scope = Customer.class)
    public JAXBElement<String> createCustomerMobileNo(String value) {
        return new JAXBElement<String>(_CustomerUpdateMobileNo_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "authorizedSignatoryName", scope = Customer.class)
    public JAXBElement<String> createCustomerAuthorizedSignatoryName(String value) {
        return new JAXBElement<String>(_CustomerUpdateAuthorizedSignatoryName_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleservicePaymentMode", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleservicePaymentMode(String value) {
        return new JAXBElement<String>(_CustomerTeleservicePaymentMode_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "nationality", scope = Customer.class)
    public JAXBElement<String> createCustomerNationality(String value) {
        return new JAXBElement<String>(_CustomerUpdateNationality_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "propertyType", scope = Customer.class)
    public JAXBElement<String> createCustomerPropertyType(String value) {
        return new JAXBElement<String>(_CustomerUpdatePropertyType_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleservicePaymentBranch", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleservicePaymentBranch(String value) {
        return new JAXBElement<String>(_CustomerTeleservicePaymentBranch_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "bankAccountType", scope = Customer.class)
    public JAXBElement<String> createCustomerBankAccountType(String value) {
        return new JAXBElement<String>(_CustomerUpdateBankAccountType_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionPaymentBank", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionPaymentBank(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionPaymentBank_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "teleserviceReferenceTransId", scope = Customer.class)
    public JAXBElement<String> createCustomerTeleserviceReferenceTransId(String value) {
        return new JAXBElement<String>(_CustomerTeleserviceReferenceTransId_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "ServiceAddOnPlanCode", scope = Customer.class)
    public JAXBElement<ArrayOfstring> createCustomerServiceAddOnPlanCode(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_CustomerServiceAddOnPlanCode_QNAME, ArrayOfstring.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LocalContactAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "localContactAddress", scope = Customer.class)
    public JAXBElement<LocalContactAddress> createCustomerLocalContactAddress(LocalContactAddress value) {
        return new JAXBElement<LocalContactAddress>(_CustomerLocalContactAddress_QNAME, LocalContactAddress.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "deviceOwner", scope = Customer.class)
    public JAXBElement<String> createCustomerDeviceOwner(String value) {
        return new JAXBElement<String>(_CustomerActivationDeviceOwner_QNAME, String.class, Customer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/", name = "telesolutionReferenceTransId", scope = Customer.class)
    public JAXBElement<String> createCustomerTelesolutionReferenceTransId(String value) {
        return new JAXBElement<String>(_CustomerTelesolutionReferenceTransId_QNAME, String.class, Customer.class, value);
    }

}
