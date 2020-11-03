
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import org.datacontract.schemas._2004._07.AddChargeResult;
import org.datacontract.schemas._2004._07.AddPlanResult;
import org.datacontract.schemas._2004._07.ApiRequest;
import org.datacontract.schemas._2004._07.ApiResponse;
import org.datacontract.schemas._2004._07.BillDetail;
import org.datacontract.schemas._2004._07.BillDetails;
import org.datacontract.schemas._2004._07.CancelAddPostpaidPlanResult;
import org.datacontract.schemas._2004._07.CancelPlanMigrationResult;
import org.datacontract.schemas._2004._07.ChangeBillCycleResult;
import org.datacontract.schemas._2004._07.ChangeBusinessPartnerResult;
import org.datacontract.schemas._2004._07.CheckPlanFeasibilityResult;
import org.datacontract.schemas._2004._07.ClsAddCharge;
import org.datacontract.schemas._2004._07.ClsCustomerBaringExceptionList;
import org.datacontract.schemas._2004._07.ClsCustomerNotificationExceptionList;
import org.datacontract.schemas._2004._07.ClsCustomerSpeedExceptionList;
import org.datacontract.schemas._2004._07.ClsCustomerUnbaringExceptionList;
import org.datacontract.schemas._2004._07.ClsDisableWalledGarden;
import org.datacontract.schemas._2004._07.ClsP2PReBarring;
import org.datacontract.schemas._2004._07.ClsTerminationRefund;
import org.datacontract.schemas._2004._07.CreateCustomerResult;
import org.datacontract.schemas._2004._07.Customer;
import org.datacontract.schemas._2004._07.CustomerActivationResult;
import org.datacontract.schemas._2004._07.CustomerBaringExceptionListResult;
import org.datacontract.schemas._2004._07.CustomerCancelPayment;
import org.datacontract.schemas._2004._07.CustomerDetail;
import org.datacontract.schemas._2004._07.CustomerDiscountDetails;
import org.datacontract.schemas._2004._07.CustomerNotificationExceptionListResult;
import org.datacontract.schemas._2004._07.CustomerPostPayment;
import org.datacontract.schemas._2004._07.CustomerPostPaymentResult;
import org.datacontract.schemas._2004._07.CustomerSpeedExceptionListResult;
import org.datacontract.schemas._2004._07.CustomerUnbaringExceptionListResult;
import org.datacontract.schemas._2004._07.CustomerUpdate;
import org.datacontract.schemas._2004._07.CustomerUsageDetail;
import org.datacontract.schemas._2004._07.DeviceDetailResult;
import org.datacontract.schemas._2004._07.DisableWalledGardenResult;
import org.datacontract.schemas._2004._07.InstantOfferAllocation;
import org.datacontract.schemas._2004._07.InventoryItemToLCOResult;
import org.datacontract.schemas._2004._07.LastPaymentResult;
import org.datacontract.schemas._2004._07.OfferAllocation;
import org.datacontract.schemas._2004._07.P2PReBarringResult;
import org.datacontract.schemas._2004._07.PaymentAdjustmentResult;
import org.datacontract.schemas._2004._07.RefferalDetailsResult;
import org.datacontract.schemas._2004._07.TerminationRefundResult;
import org.datacontract.schemas._2004._07.UserSessionResult;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the h8ssrms package. 
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

    private final static QName _CustomerUnbaringExceptionListResponseCustomerUnbaringExceptionListResult_QNAME = new QName("h8SSRMS", "CustomerUnbaringExceptionListResult");
    private final static QName _CustomerBaringExceptionListRequest_QNAME = new QName("h8SSRMS", "Request");
    private final static QName _CustomerBaringExceptionListCBEL_QNAME = new QName("h8SSRMS", "CBEL");
    private final static QName _GetDistributorInventoryListResponseGetDistributorInventoryListResult_QNAME = new QName("h8SSRMS", "GetDistributorInventoryListResult");
    private final static QName _CancelPlanOfferMigrationResponseCancelPlanOfferMigrationResult_QNAME = new QName("h8SSRMS", "CancelPlanOfferMigrationResult");
    private final static QName _GetCustomerUsageDetailResponseGetCustomerUsageDetailResult_QNAME = new QName("h8SSRMS", "GetCustomerUsageDetailResult");
    private final static QName _ChangeOfferOffer_QNAME = new QName("h8SSRMS", "offer");
    private final static QName _CustomerUnbaringExceptionListCUEL_QNAME = new QName("h8SSRMS", "CUEL");
    private final static QName _CustomerBaringExceptionListResponseCustomerBaringExceptionListResult_QNAME = new QName("h8SSRMS", "CustomerBaringExceptionListResult");
    private final static QName _InstantPrepaidOfferRenewResponseInstantPrepaidOfferRenewResult_QNAME = new QName("h8SSRMS", "InstantPrepaidOfferRenewResult");
    private final static QName _GetRefferalPointsResponseGetRefferalPointsResult_QNAME = new QName("h8SSRMS", "GetRefferalPointsResult");
    private final static QName _GetDeviceDetailResponseGetDeviceDetailResult_QNAME = new QName("h8SSRMS", "GetDeviceDetailResult");
    private final static QName _TerminationRefundResponseTerminationRefundResult_QNAME = new QName("h8SSRMS", "TerminationRefundResult");
    private final static QName _GetBillDetailBillNumber_QNAME = new QName("h8SSRMS", "billNumber");
    private final static QName _CustomerActivationResponseCustomerActivationResult_QNAME = new QName("h8SSRMS", "CustomerActivationResult");
    private final static QName _GetBillingDetailsResponseGetBillingDetailsResult_QNAME = new QName("h8SSRMS", "getBillingDetailsResult");
    private final static QName _GetDiscountDetailsResponseGetDiscountDetailsResult_QNAME = new QName("h8SSRMS", "GetDiscountDetailsResult");
    private final static QName _CancelAdditionalPlanPlanCode_QNAME = new QName("h8SSRMS", "PlanCode");
    private final static QName _PaymentAdjustmentResponsePaymentAdjustmentResult_QNAME = new QName("h8SSRMS", "PaymentAdjustmentResult");
    private final static QName _AllowGracePeriodResponseAllowGracePeriodResult_QNAME = new QName("h8SSRMS", "AllowGracePeriodResult");
    private final static QName _UpdateCustomerResponseUpdateCustomerResult_QNAME = new QName("h8SSRMS", "UpdateCustomerResult");
    private final static QName _AddChargeResponseAddChargeResult_QNAME = new QName("h8SSRMS", "AddChargeResult");
    private final static QName _P2PReBarringResponseP2PReBarringResult_QNAME = new QName("h8SSRMS", "P2PReBarringResult");
    private final static QName _GetUserSessionOfWeekResponseGetUserSessionOfWeekResult_QNAME = new QName("h8SSRMS", "GetUserSessionOfWeekResult");
    private final static QName _ChangeOfferResponseChangeOfferResult_QNAME = new QName("h8SSRMS", "ChangeOfferResult");
    private final static QName _ChangeBusinessPartnerOldBusinessPartner_QNAME = new QName("h8SSRMS", "OldBusinessPartner");
    private final static QName _ChangeBusinessPartnerNewBusinessPartner_QNAME = new QName("h8SSRMS", "NewBusinessPartner");
    private final static QName _CancelPaymentResponseCancelPaymentResult_QNAME = new QName("h8SSRMS", "CancelPaymentResult");
    private final static QName _GetDistributorInventoryListLCO_QNAME = new QName("h8SSRMS", "LCO");
    private final static QName _GetDistributorInventoryListModelNo_QNAME = new QName("h8SSRMS", "ModelNo");
    private final static QName _CheckFeasibilityResponseCheckFeasibilityResult_QNAME = new QName("h8SSRMS", "CheckFeasibilityResult");
    private final static QName _PostPaymentCustpay_QNAME = new QName("h8SSRMS", "custpay");
    private final static QName _PaymentAdjustmentAdjustment_QNAME = new QName("h8SSRMS", "Adjustment");
    private final static QName _GetBillDetailResponseGetBillDetailResult_QNAME = new QName("h8SSRMS", "GetBillDetailResult");
    private final static QName _AddPlanChangePlan_QNAME = new QName("h8SSRMS", "changePlan");
    private final static QName _ChangeDeviceDetailResponseChangeDeviceDetailResult_QNAME = new QName("h8SSRMS", "ChangeDeviceDetailResult");
    private final static QName _GetBillingDetailsEndDate_QNAME = new QName("h8SSRMS", "endDate");
    private final static QName _GetBillingDetailsStartDate_QNAME = new QName("h8SSRMS", "startDate");
    private final static QName _AddChargeAc_QNAME = new QName("h8SSRMS", "ac");
    private final static QName _AddChargeRequest_QNAME = new QName("h8SSRMS", "request");
    private final static QName _CancelPaymentPayment_QNAME = new QName("h8SSRMS", "Payment");
    private final static QName _ChangeDeviceDetailChangedevice_QNAME = new QName("h8SSRMS", "changedevice");
    private final static QName _AddPlanResponseAddPlanResult_QNAME = new QName("h8SSRMS", "AddPlanResult");
    private final static QName _ChangeStatusStatusName_QNAME = new QName("h8SSRMS", "statusName");
    private final static QName _ChangeStatusRemark_QNAME = new QName("h8SSRMS", "remark");
    private final static QName _ChangeBillCycleStatus_QNAME = new QName("h8SSRMS", "Status");
    private final static QName _ChangeBillCycleBillCycleName_QNAME = new QName("h8SSRMS", "BillCycleName");
    private final static QName _CustomerSpeedExceptionListResponseCustomerSpeedExceptionListResult_QNAME = new QName("h8SSRMS", "CustomerSpeedExceptionListResult");
    private final static QName _InstantOfferRenewResponseInstantOfferRenewResult_QNAME = new QName("h8SSRMS", "InstantOfferRenewResult");
    private final static QName _GetCustomerUsageDetailEmailId_QNAME = new QName("h8SSRMS", "EmailId");
    private final static QName _GetCustomerUsageDetailRMN_QNAME = new QName("h8SSRMS", "RMN");
    private final static QName _GetCustomerUsageDetailCRFNo_QNAME = new QName("h8SSRMS", "CRFNo");
    private final static QName _GetCustomerUsageDetailRTN_QNAME = new QName("h8SSRMS", "RTN");
    private final static QName _CreateCustomerResponseCreateCustomerResult_QNAME = new QName("h8SSRMS", "CreateCustomerResult");
    private final static QName _GetUserSessionOfMonthResponseGetUserSessionOfMonthResult_QNAME = new QName("h8SSRMS", "GetUserSessionOfMonthResult");
    private final static QName _ChangeAddressResponseChangeAddressResult_QNAME = new QName("h8SSRMS", "ChangeAddressResult");
    private final static QName _CreateCustomerAcc_QNAME = new QName("h8SSRMS", "acc");
    private final static QName _CustomerNotificationExceptionListResponseCustomerNotificationExceptionListResult_QNAME = new QName("h8SSRMS", "CustomerNotificationExceptionListResult");
    private final static QName _CancelAdditionalPlanResponseCancelAdditionalPlanResult_QNAME = new QName("h8SSRMS", "CancelAdditionalPlanResult");
    private final static QName _CustomerActivationCustact_QNAME = new QName("h8SSRMS", "custact");
    private final static QName _PostPaymentResponsePostPaymentResult_QNAME = new QName("h8SSRMS", "PostPaymentResult");
    private final static QName _GetUserSessionOfDayResponseGetUserSessionOfDayResult_QNAME = new QName("h8SSRMS", "GetUserSessionOfDayResult");
    private final static QName _ChangeBusinessPartnerResponseChangeBusinessPartnerResult_QNAME = new QName("h8SSRMS", "ChangeBusinessPartnerResult");
    private final static QName _GetCustomerDetailResponseGetCustomerDetailResult_QNAME = new QName("h8SSRMS", "GetCustomerDetailResult");
    private final static QName _GetLastPaymentResponseGetLastPaymentResult_QNAME = new QName("h8SSRMS", "GetLastPaymentResult");
    private final static QName _CheckFeasibilityPlanCode_QNAME = new QName("h8SSRMS", "planCode");
    private final static QName _ChangeAddressCustBillAdd_QNAME = new QName("h8SSRMS", "custBillAdd");
    private final static QName _ChangeStatusResponseChangeStatusResult_QNAME = new QName("h8SSRMS", "ChangeStatusResult");
    private final static QName _DisableWalledGardenResponseDisableWalledGardenResult_QNAME = new QName("h8SSRMS", "DisableWalledGardenResult");
    private final static QName _ChangeBillCycleResponseChangeBillCycleResult_QNAME = new QName("h8SSRMS", "ChangeBillCycleResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: h8ssrms
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomerUnbaringExceptionListResponse }
     * 
     */
    public CustomerUnbaringExceptionListResponse createCustomerUnbaringExceptionListResponse() {
        return new CustomerUnbaringExceptionListResponse();
    }

    /**
     * Create an instance of {@link CustomerBaringExceptionList }
     * 
     */
    public CustomerBaringExceptionList createCustomerBaringExceptionList() {
        return new CustomerBaringExceptionList();
    }

    /**
     * Create an instance of {@link GetRefferalPoints }
     * 
     */
    public GetRefferalPoints createGetRefferalPoints() {
        return new GetRefferalPoints();
    }

    /**
     * Create an instance of {@link GetDistributorInventoryListResponse }
     * 
     */
    public GetDistributorInventoryListResponse createGetDistributorInventoryListResponse() {
        return new GetDistributorInventoryListResponse();
    }

    /**
     * Create an instance of {@link CancelPlanOfferMigrationResponse }
     * 
     */
    public CancelPlanOfferMigrationResponse createCancelPlanOfferMigrationResponse() {
        return new CancelPlanOfferMigrationResponse();
    }

    /**
     * Create an instance of {@link GetCustomerUsageDetailResponse }
     * 
     */
    public GetCustomerUsageDetailResponse createGetCustomerUsageDetailResponse() {
        return new GetCustomerUsageDetailResponse();
    }

    /**
     * Create an instance of {@link DisableWalledGarden }
     * 
     */
    public DisableWalledGarden createDisableWalledGarden() {
        return new DisableWalledGarden();
    }

    /**
     * Create an instance of {@link ChangeOffer }
     * 
     */
    public ChangeOffer createChangeOffer() {
        return new ChangeOffer();
    }

    /**
     * Create an instance of {@link GetUserSessionOfWeek }
     * 
     */
    public GetUserSessionOfWeek createGetUserSessionOfWeek() {
        return new GetUserSessionOfWeek();
    }

    /**
     * Create an instance of {@link CustomerUnbaringExceptionList }
     * 
     */
    public CustomerUnbaringExceptionList createCustomerUnbaringExceptionList() {
        return new CustomerUnbaringExceptionList();
    }

    /**
     * Create an instance of {@link CustomerBaringExceptionListResponse }
     * 
     */
    public CustomerBaringExceptionListResponse createCustomerBaringExceptionListResponse() {
        return new CustomerBaringExceptionListResponse();
    }

    /**
     * Create an instance of {@link InstantPrepaidOfferRenewResponse }
     * 
     */
    public InstantPrepaidOfferRenewResponse createInstantPrepaidOfferRenewResponse() {
        return new InstantPrepaidOfferRenewResponse();
    }

    /**
     * Create an instance of {@link GetRefferalPointsResponse }
     * 
     */
    public GetRefferalPointsResponse createGetRefferalPointsResponse() {
        return new GetRefferalPointsResponse();
    }

    /**
     * Create an instance of {@link GetLastPayment }
     * 
     */
    public GetLastPayment createGetLastPayment() {
        return new GetLastPayment();
    }

    /**
     * Create an instance of {@link InstantOfferRenew }
     * 
     */
    public InstantOfferRenew createInstantOfferRenew() {
        return new InstantOfferRenew();
    }

    /**
     * Create an instance of {@link GetDeviceDetailResponse }
     * 
     */
    public GetDeviceDetailResponse createGetDeviceDetailResponse() {
        return new GetDeviceDetailResponse();
    }

    /**
     * Create an instance of {@link TerminationRefundResponse }
     * 
     */
    public TerminationRefundResponse createTerminationRefundResponse() {
        return new TerminationRefundResponse();
    }

    /**
     * Create an instance of {@link GetBillDetail }
     * 
     */
    public GetBillDetail createGetBillDetail() {
        return new GetBillDetail();
    }

    /**
     * Create an instance of {@link CustomerActivationResponse }
     * 
     */
    public CustomerActivationResponse createCustomerActivationResponse() {
        return new CustomerActivationResponse();
    }

    /**
     * Create an instance of {@link GetBillingDetailsResponse }
     * 
     */
    public GetBillingDetailsResponse createGetBillingDetailsResponse() {
        return new GetBillingDetailsResponse();
    }

    /**
     * Create an instance of {@link GetDiscountDetailsResponse }
     * 
     */
    public GetDiscountDetailsResponse createGetDiscountDetailsResponse() {
        return new GetDiscountDetailsResponse();
    }

    /**
     * Create an instance of {@link CancelAdditionalPlan }
     * 
     */
    public CancelAdditionalPlan createCancelAdditionalPlan() {
        return new CancelAdditionalPlan();
    }

    /**
     * Create an instance of {@link PaymentAdjustmentResponse }
     * 
     */
    public PaymentAdjustmentResponse createPaymentAdjustmentResponse() {
        return new PaymentAdjustmentResponse();
    }

    /**
     * Create an instance of {@link TerminationRefund }
     * 
     */
    public TerminationRefund createTerminationRefund() {
        return new TerminationRefund();
    }

    /**
     * Create an instance of {@link AllowGracePeriodResponse }
     * 
     */
    public AllowGracePeriodResponse createAllowGracePeriodResponse() {
        return new AllowGracePeriodResponse();
    }

    /**
     * Create an instance of {@link UpdateCustomerResponse }
     * 
     */
    public UpdateCustomerResponse createUpdateCustomerResponse() {
        return new UpdateCustomerResponse();
    }

    /**
     * Create an instance of {@link AddChargeResponse }
     * 
     */
    public AddChargeResponse createAddChargeResponse() {
        return new AddChargeResponse();
    }

    /**
     * Create an instance of {@link InstantPrepaidOfferRenew }
     * 
     */
    public InstantPrepaidOfferRenew createInstantPrepaidOfferRenew() {
        return new InstantPrepaidOfferRenew();
    }

    /**
     * Create an instance of {@link P2PReBarringResponse }
     * 
     */
    public P2PReBarringResponse createP2PReBarringResponse() {
        return new P2PReBarringResponse();
    }

    /**
     * Create an instance of {@link GetUserSessionOfWeekResponse }
     * 
     */
    public GetUserSessionOfWeekResponse createGetUserSessionOfWeekResponse() {
        return new GetUserSessionOfWeekResponse();
    }

    /**
     * Create an instance of {@link GetUserSessionOfMonth }
     * 
     */
    public GetUserSessionOfMonth createGetUserSessionOfMonth() {
        return new GetUserSessionOfMonth();
    }

    /**
     * Create an instance of {@link ChangeOfferResponse }
     * 
     */
    public ChangeOfferResponse createChangeOfferResponse() {
        return new ChangeOfferResponse();
    }

    /**
     * Create an instance of {@link ChangeBusinessPartner }
     * 
     */
    public ChangeBusinessPartner createChangeBusinessPartner() {
        return new ChangeBusinessPartner();
    }

    /**
     * Create an instance of {@link CancelPaymentResponse }
     * 
     */
    public CancelPaymentResponse createCancelPaymentResponse() {
        return new CancelPaymentResponse();
    }

    /**
     * Create an instance of {@link CustomerSpeedExceptionList }
     * 
     */
    public CustomerSpeedExceptionList createCustomerSpeedExceptionList() {
        return new CustomerSpeedExceptionList();
    }

    /**
     * Create an instance of {@link GetDistributorInventoryList }
     * 
     */
    public GetDistributorInventoryList createGetDistributorInventoryList() {
        return new GetDistributorInventoryList();
    }

    /**
     * Create an instance of {@link CheckFeasibilityResponse }
     * 
     */
    public CheckFeasibilityResponse createCheckFeasibilityResponse() {
        return new CheckFeasibilityResponse();
    }

    /**
     * Create an instance of {@link PostPayment }
     * 
     */
    public PostPayment createPostPayment() {
        return new PostPayment();
    }

    /**
     * Create an instance of {@link h8ssrms.PaymentAdjustment }
     * 
     */
    public h8ssrms.PaymentAdjustment createPaymentAdjustment() {
        return new h8ssrms.PaymentAdjustment();
    }

    /**
     * Create an instance of {@link GetBillDetailResponse }
     * 
     */
    public GetBillDetailResponse createGetBillDetailResponse() {
        return new GetBillDetailResponse();
    }

    /**
     * Create an instance of {@link h8ssrms.AddPlan }
     * 
     */
    public h8ssrms.AddPlan createAddPlan() {
        return new h8ssrms.AddPlan();
    }

    /**
     * Create an instance of {@link P2PReBarring }
     * 
     */
    public P2PReBarring createP2PReBarring() {
        return new P2PReBarring();
    }

    /**
     * Create an instance of {@link AddCharge }
     * 
     */
    public AddCharge createAddCharge() {
        return new AddCharge();
    }

    /**
     * Create an instance of {@link GetBillingDetails }
     * 
     */
    public GetBillingDetails createGetBillingDetails() {
        return new GetBillingDetails();
    }

    /**
     * Create an instance of {@link ChangeDeviceDetailResponse }
     * 
     */
    public ChangeDeviceDetailResponse createChangeDeviceDetailResponse() {
        return new ChangeDeviceDetailResponse();
    }

    /**
     * Create an instance of {@link CancelPlanOfferMigration }
     * 
     */
    public CancelPlanOfferMigration createCancelPlanOfferMigration() {
        return new CancelPlanOfferMigration();
    }

    /**
     * Create an instance of {@link CancelPayment }
     * 
     */
    public CancelPayment createCancelPayment() {
        return new CancelPayment();
    }

    /**
     * Create an instance of {@link GetDiscountDetails }
     * 
     */
    public GetDiscountDetails createGetDiscountDetails() {
        return new GetDiscountDetails();
    }

    /**
     * Create an instance of {@link h8ssrms.ChangeDeviceDetail }
     * 
     */
    public h8ssrms.ChangeDeviceDetail createChangeDeviceDetail() {
        return new h8ssrms.ChangeDeviceDetail();
    }

    /**
     * Create an instance of {@link AddPlanResponse }
     * 
     */
    public AddPlanResponse createAddPlanResponse() {
        return new AddPlanResponse();
    }

    /**
     * Create an instance of {@link ChangeStatus }
     * 
     */
    public ChangeStatus createChangeStatus() {
        return new ChangeStatus();
    }

    /**
     * Create an instance of {@link ChangeBillCycle }
     * 
     */
    public ChangeBillCycle createChangeBillCycle() {
        return new ChangeBillCycle();
    }

    /**
     * Create an instance of {@link CustomerSpeedExceptionListResponse }
     * 
     */
    public CustomerSpeedExceptionListResponse createCustomerSpeedExceptionListResponse() {
        return new CustomerSpeedExceptionListResponse();
    }

    /**
     * Create an instance of {@link AllowGracePeriod }
     * 
     */
    public AllowGracePeriod createAllowGracePeriod() {
        return new AllowGracePeriod();
    }

    /**
     * Create an instance of {@link InstantOfferRenewResponse }
     * 
     */
    public InstantOfferRenewResponse createInstantOfferRenewResponse() {
        return new InstantOfferRenewResponse();
    }

    /**
     * Create an instance of {@link GetCustomerUsageDetail }
     * 
     */
    public GetCustomerUsageDetail createGetCustomerUsageDetail() {
        return new GetCustomerUsageDetail();
    }

    /**
     * Create an instance of {@link CreateCustomerResponse }
     * 
     */
    public CreateCustomerResponse createCreateCustomerResponse() {
        return new CreateCustomerResponse();
    }

    /**
     * Create an instance of {@link GetUserSessionOfMonthResponse }
     * 
     */
    public GetUserSessionOfMonthResponse createGetUserSessionOfMonthResponse() {
        return new GetUserSessionOfMonthResponse();
    }

    /**
     * Create an instance of {@link ChangeAddressResponse }
     * 
     */
    public ChangeAddressResponse createChangeAddressResponse() {
        return new ChangeAddressResponse();
    }

    /**
     * Create an instance of {@link CreateCustomer }
     * 
     */
    public CreateCustomer createCreateCustomer() {
        return new CreateCustomer();
    }

    /**
     * Create an instance of {@link CustomerNotificationExceptionListResponse }
     * 
     */
    public CustomerNotificationExceptionListResponse createCustomerNotificationExceptionListResponse() {
        return new CustomerNotificationExceptionListResponse();
    }

    /**
     * Create an instance of {@link h8ssrms.CustomerActivation }
     * 
     */
    public h8ssrms.CustomerActivation createCustomerActivation() {
        return new h8ssrms.CustomerActivation();
    }

    /**
     * Create an instance of {@link CancelAdditionalPlanResponse }
     * 
     */
    public CancelAdditionalPlanResponse createCancelAdditionalPlanResponse() {
        return new CancelAdditionalPlanResponse();
    }

    /**
     * Create an instance of {@link PostPaymentResponse }
     * 
     */
    public PostPaymentResponse createPostPaymentResponse() {
        return new PostPaymentResponse();
    }

    /**
     * Create an instance of {@link GetUserSessionOfDayResponse }
     * 
     */
    public GetUserSessionOfDayResponse createGetUserSessionOfDayResponse() {
        return new GetUserSessionOfDayResponse();
    }

    /**
     * Create an instance of {@link ChangeBusinessPartnerResponse }
     * 
     */
    public ChangeBusinessPartnerResponse createChangeBusinessPartnerResponse() {
        return new ChangeBusinessPartnerResponse();
    }

    /**
     * Create an instance of {@link GetCustomerDetail }
     * 
     */
    public GetCustomerDetail createGetCustomerDetail() {
        return new GetCustomerDetail();
    }

    /**
     * Create an instance of {@link GetLastPaymentResponse }
     * 
     */
    public GetLastPaymentResponse createGetLastPaymentResponse() {
        return new GetLastPaymentResponse();
    }

    /**
     * Create an instance of {@link GetCustomerDetailResponse }
     * 
     */
    public GetCustomerDetailResponse createGetCustomerDetailResponse() {
        return new GetCustomerDetailResponse();
    }

    /**
     * Create an instance of {@link CheckFeasibility }
     * 
     */
    public CheckFeasibility createCheckFeasibility() {
        return new CheckFeasibility();
    }

    /**
     * Create an instance of {@link GetUserSessionOfDay }
     * 
     */
    public GetUserSessionOfDay createGetUserSessionOfDay() {
        return new GetUserSessionOfDay();
    }

    /**
     * Create an instance of {@link UpdateCustomer }
     * 
     */
    public UpdateCustomer createUpdateCustomer() {
        return new UpdateCustomer();
    }

    /**
     * Create an instance of {@link GetDeviceDetail }
     * 
     */
    public GetDeviceDetail createGetDeviceDetail() {
        return new GetDeviceDetail();
    }

    /**
     * Create an instance of {@link CustomerNotificationExceptionList }
     * 
     */
    public CustomerNotificationExceptionList createCustomerNotificationExceptionList() {
        return new CustomerNotificationExceptionList();
    }

    /**
     * Create an instance of {@link h8ssrms.ChangeAddress }
     * 
     */
    public h8ssrms.ChangeAddress createChangeAddress() {
        return new h8ssrms.ChangeAddress();
    }

    /**
     * Create an instance of {@link ChangeStatusResponse }
     * 
     */
    public ChangeStatusResponse createChangeStatusResponse() {
        return new ChangeStatusResponse();
    }

    /**
     * Create an instance of {@link DisableWalledGardenResponse }
     * 
     */
    public DisableWalledGardenResponse createDisableWalledGardenResponse() {
        return new DisableWalledGardenResponse();
    }

    /**
     * Create an instance of {@link ChangeBillCycleResponse }
     * 
     */
    public ChangeBillCycleResponse createChangeBillCycleResponse() {
        return new ChangeBillCycleResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerUnbaringExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CustomerUnbaringExceptionListResult", scope = CustomerUnbaringExceptionListResponse.class)
    public JAXBElement<CustomerUnbaringExceptionListResult> createCustomerUnbaringExceptionListResponseCustomerUnbaringExceptionListResult(CustomerUnbaringExceptionListResult value) {
        return new JAXBElement<CustomerUnbaringExceptionListResult>(_CustomerUnbaringExceptionListResponseCustomerUnbaringExceptionListResult_QNAME, CustomerUnbaringExceptionListResult.class, CustomerUnbaringExceptionListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CustomerBaringExceptionList.class)
    public JAXBElement<ApiRequest> createCustomerBaringExceptionListRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CustomerBaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerBaringExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CBEL", scope = CustomerBaringExceptionList.class)
    public JAXBElement<ClsCustomerBaringExceptionList> createCustomerBaringExceptionListCBEL(ClsCustomerBaringExceptionList value) {
        return new JAXBElement<ClsCustomerBaringExceptionList>(_CustomerBaringExceptionListCBEL_QNAME, ClsCustomerBaringExceptionList.class, CustomerBaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetRefferalPoints.class)
    public JAXBElement<ApiRequest> createGetRefferalPointsRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetRefferalPoints.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InventoryItemToLCOResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetDistributorInventoryListResult", scope = GetDistributorInventoryListResponse.class)
    public JAXBElement<InventoryItemToLCOResult> createGetDistributorInventoryListResponseGetDistributorInventoryListResult(InventoryItemToLCOResult value) {
        return new JAXBElement<InventoryItemToLCOResult>(_GetDistributorInventoryListResponseGetDistributorInventoryListResult_QNAME, InventoryItemToLCOResult.class, GetDistributorInventoryListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelPlanMigrationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CancelPlanOfferMigrationResult", scope = CancelPlanOfferMigrationResponse.class)
    public JAXBElement<CancelPlanMigrationResult> createCancelPlanOfferMigrationResponseCancelPlanOfferMigrationResult(CancelPlanMigrationResult value) {
        return new JAXBElement<CancelPlanMigrationResult>(_CancelPlanOfferMigrationResponseCancelPlanOfferMigrationResult_QNAME, CancelPlanMigrationResult.class, CancelPlanOfferMigrationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerUsageDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetCustomerUsageDetailResult", scope = GetCustomerUsageDetailResponse.class)
    public JAXBElement<CustomerUsageDetail> createGetCustomerUsageDetailResponseGetCustomerUsageDetailResult(CustomerUsageDetail value) {
        return new JAXBElement<CustomerUsageDetail>(_GetCustomerUsageDetailResponseGetCustomerUsageDetailResult_QNAME, CustomerUsageDetail.class, GetCustomerUsageDetailResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = DisableWalledGarden.class)
    public JAXBElement<ApiRequest> createDisableWalledGardenRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, DisableWalledGarden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsDisableWalledGarden }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CBEL", scope = DisableWalledGarden.class)
    public JAXBElement<ClsDisableWalledGarden> createDisableWalledGardenCBEL(ClsDisableWalledGarden value) {
        return new JAXBElement<ClsDisableWalledGarden>(_CustomerBaringExceptionListCBEL_QNAME, ClsDisableWalledGarden.class, DisableWalledGarden.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = ChangeOffer.class)
    public JAXBElement<ApiRequest> createChangeOfferRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, ChangeOffer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OfferAllocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "offer", scope = ChangeOffer.class)
    public JAXBElement<OfferAllocation> createChangeOfferOffer(OfferAllocation value) {
        return new JAXBElement<OfferAllocation>(_ChangeOfferOffer_QNAME, OfferAllocation.class, ChangeOffer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetUserSessionOfWeek.class)
    public JAXBElement<ApiRequest> createGetUserSessionOfWeekRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetUserSessionOfWeek.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerUnbaringExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CUEL", scope = CustomerUnbaringExceptionList.class)
    public JAXBElement<ClsCustomerUnbaringExceptionList> createCustomerUnbaringExceptionListCUEL(ClsCustomerUnbaringExceptionList value) {
        return new JAXBElement<ClsCustomerUnbaringExceptionList>(_CustomerUnbaringExceptionListCUEL_QNAME, ClsCustomerUnbaringExceptionList.class, CustomerUnbaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CustomerUnbaringExceptionList.class)
    public JAXBElement<ApiRequest> createCustomerUnbaringExceptionListRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CustomerUnbaringExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerBaringExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CustomerBaringExceptionListResult", scope = CustomerBaringExceptionListResponse.class)
    public JAXBElement<CustomerBaringExceptionListResult> createCustomerBaringExceptionListResponseCustomerBaringExceptionListResult(CustomerBaringExceptionListResult value) {
        return new JAXBElement<CustomerBaringExceptionListResult>(_CustomerBaringExceptionListResponseCustomerBaringExceptionListResult_QNAME, CustomerBaringExceptionListResult.class, CustomerBaringExceptionListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "InstantPrepaidOfferRenewResult", scope = InstantPrepaidOfferRenewResponse.class)
    public JAXBElement<ApiResponse> createInstantPrepaidOfferRenewResponseInstantPrepaidOfferRenewResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_InstantPrepaidOfferRenewResponseInstantPrepaidOfferRenewResult_QNAME, ApiResponse.class, InstantPrepaidOfferRenewResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RefferalDetailsResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetRefferalPointsResult", scope = GetRefferalPointsResponse.class)
    public JAXBElement<RefferalDetailsResult> createGetRefferalPointsResponseGetRefferalPointsResult(RefferalDetailsResult value) {
        return new JAXBElement<RefferalDetailsResult>(_GetRefferalPointsResponseGetRefferalPointsResult_QNAME, RefferalDetailsResult.class, GetRefferalPointsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetLastPayment.class)
    public JAXBElement<ApiRequest> createGetLastPaymentRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetLastPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = InstantOfferRenew.class)
    public JAXBElement<ApiRequest> createInstantOfferRenewRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, InstantOfferRenew.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantOfferAllocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "offer", scope = InstantOfferRenew.class)
    public JAXBElement<InstantOfferAllocation> createInstantOfferRenewOffer(InstantOfferAllocation value) {
        return new JAXBElement<InstantOfferAllocation>(_ChangeOfferOffer_QNAME, InstantOfferAllocation.class, InstantOfferRenew.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeviceDetailResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetDeviceDetailResult", scope = GetDeviceDetailResponse.class)
    public JAXBElement<DeviceDetailResult> createGetDeviceDetailResponseGetDeviceDetailResult(DeviceDetailResult value) {
        return new JAXBElement<DeviceDetailResult>(_GetDeviceDetailResponseGetDeviceDetailResult_QNAME, DeviceDetailResult.class, GetDeviceDetailResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TerminationRefundResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "TerminationRefundResult", scope = TerminationRefundResponse.class)
    public JAXBElement<TerminationRefundResult> createTerminationRefundResponseTerminationRefundResult(TerminationRefundResult value) {
        return new JAXBElement<TerminationRefundResult>(_TerminationRefundResponseTerminationRefundResult_QNAME, TerminationRefundResult.class, TerminationRefundResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetBillDetail.class)
    public JAXBElement<ApiRequest> createGetBillDetailRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetBillDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "billNumber", scope = GetBillDetail.class)
    public JAXBElement<String> createGetBillDetailBillNumber(String value) {
        return new JAXBElement<String>(_GetBillDetailBillNumber_QNAME, String.class, GetBillDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerActivationResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CustomerActivationResult", scope = CustomerActivationResponse.class)
    public JAXBElement<CustomerActivationResult> createCustomerActivationResponseCustomerActivationResult(CustomerActivationResult value) {
        return new JAXBElement<CustomerActivationResult>(_CustomerActivationResponseCustomerActivationResult_QNAME, CustomerActivationResult.class, CustomerActivationResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "getBillingDetailsResult", scope = GetBillingDetailsResponse.class)
    public JAXBElement<BillDetails> createGetBillingDetailsResponseGetBillingDetailsResult(BillDetails value) {
        return new JAXBElement<BillDetails>(_GetBillingDetailsResponseGetBillingDetailsResult_QNAME, BillDetails.class, GetBillingDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerDiscountDetails }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetDiscountDetailsResult", scope = GetDiscountDetailsResponse.class)
    public JAXBElement<CustomerDiscountDetails> createGetDiscountDetailsResponseGetDiscountDetailsResult(CustomerDiscountDetails value) {
        return new JAXBElement<CustomerDiscountDetails>(_GetDiscountDetailsResponseGetDiscountDetailsResult_QNAME, CustomerDiscountDetails.class, GetDiscountDetailsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "PlanCode", scope = CancelAdditionalPlan.class)
    public JAXBElement<String> createCancelAdditionalPlanPlanCode(String value) {
        return new JAXBElement<String>(_CancelAdditionalPlanPlanCode_QNAME, String.class, CancelAdditionalPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CancelAdditionalPlan.class)
    public JAXBElement<ApiRequest> createCancelAdditionalPlanRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CancelAdditionalPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PaymentAdjustmentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "PaymentAdjustmentResult", scope = PaymentAdjustmentResponse.class)
    public JAXBElement<PaymentAdjustmentResult> createPaymentAdjustmentResponsePaymentAdjustmentResult(PaymentAdjustmentResult value) {
        return new JAXBElement<PaymentAdjustmentResult>(_PaymentAdjustmentResponsePaymentAdjustmentResult_QNAME, PaymentAdjustmentResult.class, PaymentAdjustmentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = TerminationRefund.class)
    public JAXBElement<ApiRequest> createTerminationRefundRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, TerminationRefund.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsTerminationRefund }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CBEL", scope = TerminationRefund.class)
    public JAXBElement<ClsTerminationRefund> createTerminationRefundCBEL(ClsTerminationRefund value) {
        return new JAXBElement<ClsTerminationRefund>(_CustomerBaringExceptionListCBEL_QNAME, ClsTerminationRefund.class, TerminationRefund.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "AllowGracePeriodResult", scope = AllowGracePeriodResponse.class)
    public JAXBElement<ApiResponse> createAllowGracePeriodResponseAllowGracePeriodResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_AllowGracePeriodResponseAllowGracePeriodResult_QNAME, ApiResponse.class, AllowGracePeriodResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "UpdateCustomerResult", scope = UpdateCustomerResponse.class)
    public JAXBElement<ApiResponse> createUpdateCustomerResponseUpdateCustomerResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_UpdateCustomerResponseUpdateCustomerResult_QNAME, ApiResponse.class, UpdateCustomerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddChargeResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "AddChargeResult", scope = AddChargeResponse.class)
    public JAXBElement<AddChargeResult> createAddChargeResponseAddChargeResult(AddChargeResult value) {
        return new JAXBElement<AddChargeResult>(_AddChargeResponseAddChargeResult_QNAME, AddChargeResult.class, AddChargeResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = InstantPrepaidOfferRenew.class)
    public JAXBElement<ApiRequest> createInstantPrepaidOfferRenewRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, InstantPrepaidOfferRenew.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstantOfferAllocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "offer", scope = InstantPrepaidOfferRenew.class)
    public JAXBElement<InstantOfferAllocation> createInstantPrepaidOfferRenewOffer(InstantOfferAllocation value) {
        return new JAXBElement<InstantOfferAllocation>(_ChangeOfferOffer_QNAME, InstantOfferAllocation.class, InstantPrepaidOfferRenew.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link P2PReBarringResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "P2PReBarringResult", scope = P2PReBarringResponse.class)
    public JAXBElement<P2PReBarringResult> createP2PReBarringResponseP2PReBarringResult(P2PReBarringResult value) {
        return new JAXBElement<P2PReBarringResult>(_P2PReBarringResponseP2PReBarringResult_QNAME, P2PReBarringResult.class, P2PReBarringResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetUserSessionOfWeekResult", scope = GetUserSessionOfWeekResponse.class)
    public JAXBElement<UserSessionResult> createGetUserSessionOfWeekResponseGetUserSessionOfWeekResult(UserSessionResult value) {
        return new JAXBElement<UserSessionResult>(_GetUserSessionOfWeekResponseGetUserSessionOfWeekResult_QNAME, UserSessionResult.class, GetUserSessionOfWeekResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetUserSessionOfMonth.class)
    public JAXBElement<ApiRequest> createGetUserSessionOfMonthRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetUserSessionOfMonth.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ChangeOfferResult", scope = ChangeOfferResponse.class)
    public JAXBElement<ApiResponse> createChangeOfferResponseChangeOfferResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_ChangeOfferResponseChangeOfferResult_QNAME, ApiResponse.class, ChangeOfferResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "OldBusinessPartner", scope = ChangeBusinessPartner.class)
    public JAXBElement<String> createChangeBusinessPartnerOldBusinessPartner(String value) {
        return new JAXBElement<String>(_ChangeBusinessPartnerOldBusinessPartner_QNAME, String.class, ChangeBusinessPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = ChangeBusinessPartner.class)
    public JAXBElement<ApiRequest> createChangeBusinessPartnerRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, ChangeBusinessPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "NewBusinessPartner", scope = ChangeBusinessPartner.class)
    public JAXBElement<String> createChangeBusinessPartnerNewBusinessPartner(String value) {
        return new JAXBElement<String>(_ChangeBusinessPartnerNewBusinessPartner_QNAME, String.class, ChangeBusinessPartner.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CustomerSpeedExceptionList.class)
    public JAXBElement<ApiRequest> createCustomerSpeedExceptionListRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CustomerSpeedExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerSpeedExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CBEL", scope = CustomerSpeedExceptionList.class)
    public JAXBElement<ClsCustomerSpeedExceptionList> createCustomerSpeedExceptionListCBEL(ClsCustomerSpeedExceptionList value) {
        return new JAXBElement<ClsCustomerSpeedExceptionList>(_CustomerBaringExceptionListCBEL_QNAME, ClsCustomerSpeedExceptionList.class, CustomerSpeedExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CancelPaymentResult", scope = CancelPaymentResponse.class)
    public JAXBElement<ApiResponse> createCancelPaymentResponseCancelPaymentResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_CancelPaymentResponseCancelPaymentResult_QNAME, ApiResponse.class, CancelPaymentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetDistributorInventoryList.class)
    public JAXBElement<ApiRequest> createGetDistributorInventoryListRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetDistributorInventoryList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "LCO", scope = GetDistributorInventoryList.class)
    public JAXBElement<String> createGetDistributorInventoryListLCO(String value) {
        return new JAXBElement<String>(_GetDistributorInventoryListLCO_QNAME, String.class, GetDistributorInventoryList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ModelNo", scope = GetDistributorInventoryList.class)
    public JAXBElement<String> createGetDistributorInventoryListModelNo(String value) {
        return new JAXBElement<String>(_GetDistributorInventoryListModelNo_QNAME, String.class, GetDistributorInventoryList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CheckPlanFeasibilityResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CheckFeasibilityResult", scope = CheckFeasibilityResponse.class)
    public JAXBElement<CheckPlanFeasibilityResult> createCheckFeasibilityResponseCheckFeasibilityResult(CheckPlanFeasibilityResult value) {
        return new JAXBElement<CheckPlanFeasibilityResult>(_CheckFeasibilityResponseCheckFeasibilityResult_QNAME, CheckPlanFeasibilityResult.class, CheckFeasibilityResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = PostPayment.class)
    public JAXBElement<ApiRequest> createPostPaymentRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, PostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerPostPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "custpay", scope = PostPayment.class)
    public JAXBElement<CustomerPostPayment> createPostPaymentCustpay(CustomerPostPayment value) {
        return new JAXBElement<CustomerPostPayment>(_PostPaymentCustpay_QNAME, CustomerPostPayment.class, PostPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.PaymentAdjustment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Adjustment", scope = h8ssrms.PaymentAdjustment.class)
    public JAXBElement<org.datacontract.schemas._2004._07.PaymentAdjustment> createPaymentAdjustmentAdjustment(org.datacontract.schemas._2004._07.PaymentAdjustment value) {
        return new JAXBElement<org.datacontract.schemas._2004._07.PaymentAdjustment>(_PaymentAdjustmentAdjustment_QNAME, org.datacontract.schemas._2004._07.PaymentAdjustment.class, h8ssrms.PaymentAdjustment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = h8ssrms.PaymentAdjustment.class)
    public JAXBElement<ApiRequest> createPaymentAdjustmentRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, h8ssrms.PaymentAdjustment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BillDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetBillDetailResult", scope = GetBillDetailResponse.class)
    public JAXBElement<BillDetail> createGetBillDetailResponseGetBillDetailResult(BillDetail value) {
        return new JAXBElement<BillDetail>(_GetBillDetailResponseGetBillDetailResult_QNAME, BillDetail.class, GetBillDetailResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = h8ssrms.AddPlan.class)
    public JAXBElement<ApiRequest> createAddPlanRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, h8ssrms.AddPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.AddPlan }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "changePlan", scope = h8ssrms.AddPlan.class)
    public JAXBElement<org.datacontract.schemas._2004._07.AddPlan> createAddPlanChangePlan(org.datacontract.schemas._2004._07.AddPlan value) {
        return new JAXBElement<org.datacontract.schemas._2004._07.AddPlan>(_AddPlanChangePlan_QNAME, org.datacontract.schemas._2004._07.AddPlan.class, h8ssrms.AddPlan.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = P2PReBarring.class)
    public JAXBElement<ApiRequest> createP2PReBarringRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, P2PReBarring.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsP2PReBarring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CBEL", scope = P2PReBarring.class)
    public JAXBElement<ClsP2PReBarring> createP2PReBarringCBEL(ClsP2PReBarring value) {
        return new JAXBElement<ClsP2PReBarring>(_CustomerBaringExceptionListCBEL_QNAME, ClsP2PReBarring.class, P2PReBarring.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ChangeDeviceDetailResult", scope = ChangeDeviceDetailResponse.class)
    public JAXBElement<ApiResponse> createChangeDeviceDetailResponseChangeDeviceDetailResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_ChangeDeviceDetailResponseChangeDeviceDetailResult_QNAME, ApiResponse.class, ChangeDeviceDetailResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetBillingDetails.class)
    public JAXBElement<ApiRequest> createGetBillingDetailsRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetBillingDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "endDate", scope = GetBillingDetails.class)
    public JAXBElement<XMLGregorianCalendar> createGetBillingDetailsEndDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetBillingDetailsEndDate_QNAME, XMLGregorianCalendar.class, GetBillingDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "startDate", scope = GetBillingDetails.class)
    public JAXBElement<XMLGregorianCalendar> createGetBillingDetailsStartDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_GetBillingDetailsStartDate_QNAME, XMLGregorianCalendar.class, GetBillingDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsAddCharge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ac", scope = AddCharge.class)
    public JAXBElement<ClsAddCharge> createAddChargeAc(ClsAddCharge value) {
        return new JAXBElement<ClsAddCharge>(_AddChargeAc_QNAME, ClsAddCharge.class, AddCharge.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "request", scope = AddCharge.class)
    public JAXBElement<ApiRequest> createAddChargeRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_AddChargeRequest_QNAME, ApiRequest.class, AddCharge.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CancelPlanOfferMigration.class)
    public JAXBElement<ApiRequest> createCancelPlanOfferMigrationRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CancelPlanOfferMigration.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerCancelPayment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Payment", scope = CancelPayment.class)
    public JAXBElement<CustomerCancelPayment> createCancelPaymentPayment(CustomerCancelPayment value) {
        return new JAXBElement<CustomerCancelPayment>(_CancelPaymentPayment_QNAME, CustomerCancelPayment.class, CancelPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CancelPayment.class)
    public JAXBElement<ApiRequest> createCancelPaymentRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CancelPayment.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetDiscountDetails.class)
    public JAXBElement<ApiRequest> createGetDiscountDetailsRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetDiscountDetails.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = h8ssrms.ChangeDeviceDetail.class)
    public JAXBElement<ApiRequest> createChangeDeviceDetailRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, h8ssrms.ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.ChangeDeviceDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "changedevice", scope = h8ssrms.ChangeDeviceDetail.class)
    public JAXBElement<org.datacontract.schemas._2004._07.ChangeDeviceDetail> createChangeDeviceDetailChangedevice(org.datacontract.schemas._2004._07.ChangeDeviceDetail value) {
        return new JAXBElement<org.datacontract.schemas._2004._07.ChangeDeviceDetail>(_ChangeDeviceDetailChangedevice_QNAME, org.datacontract.schemas._2004._07.ChangeDeviceDetail.class, h8ssrms.ChangeDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddPlanResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "AddPlanResult", scope = AddPlanResponse.class)
    public JAXBElement<AddPlanResult> createAddPlanResponseAddPlanResult(AddPlanResult value) {
        return new JAXBElement<AddPlanResult>(_AddPlanResponseAddPlanResult_QNAME, AddPlanResult.class, AddPlanResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = ChangeStatus.class)
    public JAXBElement<ApiRequest> createChangeStatusRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, ChangeStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "statusName", scope = ChangeStatus.class)
    public JAXBElement<String> createChangeStatusStatusName(String value) {
        return new JAXBElement<String>(_ChangeStatusStatusName_QNAME, String.class, ChangeStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "remark", scope = ChangeStatus.class)
    public JAXBElement<String> createChangeStatusRemark(String value) {
        return new JAXBElement<String>(_ChangeStatusRemark_QNAME, String.class, ChangeStatus.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = ChangeBillCycle.class)
    public JAXBElement<ApiRequest> createChangeBillCycleRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, ChangeBillCycle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Status", scope = ChangeBillCycle.class)
    public JAXBElement<String> createChangeBillCycleStatus(String value) {
        return new JAXBElement<String>(_ChangeBillCycleStatus_QNAME, String.class, ChangeBillCycle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "BillCycleName", scope = ChangeBillCycle.class)
    public JAXBElement<String> createChangeBillCycleBillCycleName(String value) {
        return new JAXBElement<String>(_ChangeBillCycleBillCycleName_QNAME, String.class, ChangeBillCycle.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerSpeedExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CustomerSpeedExceptionListResult", scope = CustomerSpeedExceptionListResponse.class)
    public JAXBElement<CustomerSpeedExceptionListResult> createCustomerSpeedExceptionListResponseCustomerSpeedExceptionListResult(CustomerSpeedExceptionListResult value) {
        return new JAXBElement<CustomerSpeedExceptionListResult>(_CustomerSpeedExceptionListResponseCustomerSpeedExceptionListResult_QNAME, CustomerSpeedExceptionListResult.class, CustomerSpeedExceptionListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = AllowGracePeriod.class)
    public JAXBElement<ApiRequest> createAllowGracePeriodRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, AllowGracePeriod.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "InstantOfferRenewResult", scope = InstantOfferRenewResponse.class)
    public JAXBElement<ApiResponse> createInstantOfferRenewResponseInstantOfferRenewResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_InstantOfferRenewResponseInstantOfferRenewResult_QNAME, ApiResponse.class, InstantOfferRenewResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "EmailId", scope = GetCustomerUsageDetail.class)
    public JAXBElement<String> createGetCustomerUsageDetailEmailId(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailEmailId_QNAME, String.class, GetCustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "RMN", scope = GetCustomerUsageDetail.class)
    public JAXBElement<String> createGetCustomerUsageDetailRMN(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailRMN_QNAME, String.class, GetCustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetCustomerUsageDetail.class)
    public JAXBElement<ApiRequest> createGetCustomerUsageDetailRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetCustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CRFNo", scope = GetCustomerUsageDetail.class)
    public JAXBElement<String> createGetCustomerUsageDetailCRFNo(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailCRFNo_QNAME, String.class, GetCustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "RTN", scope = GetCustomerUsageDetail.class)
    public JAXBElement<String> createGetCustomerUsageDetailRTN(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailRTN_QNAME, String.class, GetCustomerUsageDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCustomerResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CreateCustomerResult", scope = CreateCustomerResponse.class)
    public JAXBElement<CreateCustomerResult> createCreateCustomerResponseCreateCustomerResult(CreateCustomerResult value) {
        return new JAXBElement<CreateCustomerResult>(_CreateCustomerResponseCreateCustomerResult_QNAME, CreateCustomerResult.class, CreateCustomerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetUserSessionOfMonthResult", scope = GetUserSessionOfMonthResponse.class)
    public JAXBElement<UserSessionResult> createGetUserSessionOfMonthResponseGetUserSessionOfMonthResult(UserSessionResult value) {
        return new JAXBElement<UserSessionResult>(_GetUserSessionOfMonthResponseGetUserSessionOfMonthResult_QNAME, UserSessionResult.class, GetUserSessionOfMonthResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ChangeAddressResult", scope = ChangeAddressResponse.class)
    public JAXBElement<ApiResponse> createChangeAddressResponseChangeAddressResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_ChangeAddressResponseChangeAddressResult_QNAME, ApiResponse.class, ChangeAddressResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Customer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "acc", scope = CreateCustomer.class)
    public JAXBElement<Customer> createCreateCustomerAcc(Customer value) {
        return new JAXBElement<Customer>(_CreateCustomerAcc_QNAME, Customer.class, CreateCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CreateCustomer.class)
    public JAXBElement<ApiRequest> createCreateCustomerRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CreateCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerNotificationExceptionListResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CustomerNotificationExceptionListResult", scope = CustomerNotificationExceptionListResponse.class)
    public JAXBElement<CustomerNotificationExceptionListResult> createCustomerNotificationExceptionListResponseCustomerNotificationExceptionListResult(CustomerNotificationExceptionListResult value) {
        return new JAXBElement<CustomerNotificationExceptionListResult>(_CustomerNotificationExceptionListResponseCustomerNotificationExceptionListResult_QNAME, CustomerNotificationExceptionListResult.class, CustomerNotificationExceptionListResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CancelAddPostpaidPlanResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CancelAdditionalPlanResult", scope = CancelAdditionalPlanResponse.class)
    public JAXBElement<CancelAddPostpaidPlanResult> createCancelAdditionalPlanResponseCancelAdditionalPlanResult(CancelAddPostpaidPlanResult value) {
        return new JAXBElement<CancelAddPostpaidPlanResult>(_CancelAdditionalPlanResponseCancelAdditionalPlanResult_QNAME, CancelAddPostpaidPlanResult.class, CancelAdditionalPlanResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.CustomerActivation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "custact", scope = h8ssrms.CustomerActivation.class)
    public JAXBElement<org.datacontract.schemas._2004._07.CustomerActivation> createCustomerActivationCustact(org.datacontract.schemas._2004._07.CustomerActivation value) {
        return new JAXBElement<org.datacontract.schemas._2004._07.CustomerActivation>(_CustomerActivationCustact_QNAME, org.datacontract.schemas._2004._07.CustomerActivation.class, h8ssrms.CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = h8ssrms.CustomerActivation.class)
    public JAXBElement<ApiRequest> createCustomerActivationRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, h8ssrms.CustomerActivation.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerPostPaymentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "PostPaymentResult", scope = PostPaymentResponse.class)
    public JAXBElement<CustomerPostPaymentResult> createPostPaymentResponsePostPaymentResult(CustomerPostPaymentResult value) {
        return new JAXBElement<CustomerPostPaymentResult>(_PostPaymentResponsePostPaymentResult_QNAME, CustomerPostPaymentResult.class, PostPaymentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetUserSessionOfDayResult", scope = GetUserSessionOfDayResponse.class)
    public JAXBElement<UserSessionResult> createGetUserSessionOfDayResponseGetUserSessionOfDayResult(UserSessionResult value) {
        return new JAXBElement<UserSessionResult>(_GetUserSessionOfDayResponseGetUserSessionOfDayResult_QNAME, UserSessionResult.class, GetUserSessionOfDayResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeBusinessPartnerResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ChangeBusinessPartnerResult", scope = ChangeBusinessPartnerResponse.class)
    public JAXBElement<ChangeBusinessPartnerResult> createChangeBusinessPartnerResponseChangeBusinessPartnerResult(ChangeBusinessPartnerResult value) {
        return new JAXBElement<ChangeBusinessPartnerResult>(_ChangeBusinessPartnerResponseChangeBusinessPartnerResult_QNAME, ChangeBusinessPartnerResult.class, ChangeBusinessPartnerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "EmailId", scope = GetCustomerDetail.class)
    public JAXBElement<String> createGetCustomerDetailEmailId(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailEmailId_QNAME, String.class, GetCustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "RMN", scope = GetCustomerDetail.class)
    public JAXBElement<String> createGetCustomerDetailRMN(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailRMN_QNAME, String.class, GetCustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetCustomerDetail.class)
    public JAXBElement<ApiRequest> createGetCustomerDetailRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetCustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CRFNo", scope = GetCustomerDetail.class)
    public JAXBElement<String> createGetCustomerDetailCRFNo(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailCRFNo_QNAME, String.class, GetCustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "RTN", scope = GetCustomerDetail.class)
    public JAXBElement<String> createGetCustomerDetailRTN(String value) {
        return new JAXBElement<String>(_GetCustomerUsageDetailRTN_QNAME, String.class, GetCustomerDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerDetail }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetCustomerDetailResult", scope = GetCustomerDetailResponse.class)
    public JAXBElement<CustomerDetail> createGetCustomerDetailResponseGetCustomerDetailResult(CustomerDetail value) {
        return new JAXBElement<CustomerDetail>(_GetCustomerDetailResponseGetCustomerDetailResult_QNAME, CustomerDetail.class, GetCustomerDetailResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LastPaymentResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "GetLastPaymentResult", scope = GetLastPaymentResponse.class)
    public JAXBElement<LastPaymentResult> createGetLastPaymentResponseGetLastPaymentResult(LastPaymentResult value) {
        return new JAXBElement<LastPaymentResult>(_GetLastPaymentResponseGetLastPaymentResult_QNAME, LastPaymentResult.class, GetLastPaymentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CheckFeasibility.class)
    public JAXBElement<ApiRequest> createCheckFeasibilityRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CheckFeasibility.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "planCode", scope = CheckFeasibility.class)
    public JAXBElement<String> createCheckFeasibilityPlanCode(String value) {
        return new JAXBElement<String>(_CheckFeasibilityPlanCode_QNAME, String.class, CheckFeasibility.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetUserSessionOfDay.class)
    public JAXBElement<ApiRequest> createGetUserSessionOfDayRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetUserSessionOfDay.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = CustomerNotificationExceptionList.class)
    public JAXBElement<ApiRequest> createCustomerNotificationExceptionListRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, CustomerNotificationExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClsCustomerNotificationExceptionList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "CBEL", scope = CustomerNotificationExceptionList.class)
    public JAXBElement<ClsCustomerNotificationExceptionList> createCustomerNotificationExceptionListCBEL(ClsCustomerNotificationExceptionList value) {
        return new JAXBElement<ClsCustomerNotificationExceptionList>(_CustomerBaringExceptionListCBEL_QNAME, ClsCustomerNotificationExceptionList.class, CustomerNotificationExceptionList.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = GetDeviceDetail.class)
    public JAXBElement<ApiRequest> createGetDeviceDetailRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, GetDeviceDetail.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CustomerUpdate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "acc", scope = UpdateCustomer.class)
    public JAXBElement<CustomerUpdate> createUpdateCustomerAcc(CustomerUpdate value) {
        return new JAXBElement<CustomerUpdate>(_CreateCustomerAcc_QNAME, CustomerUpdate.class, UpdateCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = UpdateCustomer.class)
    public JAXBElement<ApiRequest> createUpdateCustomerRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, UpdateCustomer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "Request", scope = h8ssrms.ChangeAddress.class)
    public JAXBElement<ApiRequest> createChangeAddressRequest(ApiRequest value) {
        return new JAXBElement<ApiRequest>(_CustomerBaringExceptionListRequest_QNAME, ApiRequest.class, h8ssrms.ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.ChangeAddress }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "custBillAdd", scope = h8ssrms.ChangeAddress.class)
    public JAXBElement<org.datacontract.schemas._2004._07.ChangeAddress> createChangeAddressCustBillAdd(org.datacontract.schemas._2004._07.ChangeAddress value) {
        return new JAXBElement<org.datacontract.schemas._2004._07.ChangeAddress>(_ChangeAddressCustBillAdd_QNAME, org.datacontract.schemas._2004._07.ChangeAddress.class, h8ssrms.ChangeAddress.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ChangeStatusResult", scope = ChangeStatusResponse.class)
    public JAXBElement<ApiResponse> createChangeStatusResponseChangeStatusResult(ApiResponse value) {
        return new JAXBElement<ApiResponse>(_ChangeStatusResponseChangeStatusResult_QNAME, ApiResponse.class, ChangeStatusResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DisableWalledGardenResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "DisableWalledGardenResult", scope = DisableWalledGardenResponse.class)
    public JAXBElement<DisableWalledGardenResult> createDisableWalledGardenResponseDisableWalledGardenResult(DisableWalledGardenResult value) {
        return new JAXBElement<DisableWalledGardenResult>(_DisableWalledGardenResponseDisableWalledGardenResult_QNAME, DisableWalledGardenResult.class, DisableWalledGardenResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeBillCycleResult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "h8SSRMS", name = "ChangeBillCycleResult", scope = ChangeBillCycleResponse.class)
    public JAXBElement<ChangeBillCycleResult> createChangeBillCycleResponseChangeBillCycleResult(ChangeBillCycleResult value) {
        return new JAXBElement<ChangeBillCycleResult>(_ChangeBillCycleResponseChangeBillCycleResult_QNAME, ChangeBillCycleResult.class, ChangeBillCycleResponse.class, value);
    }

}
