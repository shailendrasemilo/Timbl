package com.np.tele.crm.billing.client;

import javax.xml.datatype.XMLGregorianCalendar;

import org.datacontract.schemas._2004._07.AddChargeResult;
import org.datacontract.schemas._2004._07.AddPlan;
import org.datacontract.schemas._2004._07.AddPlanResult;
import org.datacontract.schemas._2004._07.ApiRequest;
import org.datacontract.schemas._2004._07.ApiResponse;
import org.datacontract.schemas._2004._07.BillDetail;
import org.datacontract.schemas._2004._07.BillDetails;
import org.datacontract.schemas._2004._07.CancelAddPostpaidPlanResult;
import org.datacontract.schemas._2004._07.CancelPlanMigrationResult;
import org.datacontract.schemas._2004._07.ChangeAddress;
import org.datacontract.schemas._2004._07.ChangeBillCycleResult;
import org.datacontract.schemas._2004._07.ChangeBusinessPartnerResult;
import org.datacontract.schemas._2004._07.ChangeDeviceDetail;
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
import org.datacontract.schemas._2004._07.CustomerActivation;
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
import org.datacontract.schemas._2004._07.PaymentAdjustment;
import org.datacontract.schemas._2004._07.PaymentAdjustmentResult;
import org.datacontract.schemas._2004._07.RefferalDetailsResult;
import org.datacontract.schemas._2004._07.TerminationRefundResult;
import org.datacontract.schemas._2004._07.UserSessionResult;
import org.tempuri.CustomerOperations;
import org.tempuri.ICustomer;

public class CrmBillingClient
    implements ICustomer
{
    static
    {
        System.setProperty( "com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true" );
    }

    /**
     * Integrated
     */
    @Override
    public CreateCustomerResult createCustomer( Customer inAcc, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.createCustomer( inAcc, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public InventoryItemToLCOResult getDistributorInventoryList( String inModelNo, String inLco, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getDistributorInventoryList( inModelNo, inLco, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public CustomerActivationResult customerActivation( CustomerActivation inCustact, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.customerActivation( inCustact, inRequest );
    }

    /**
     * Integrated
     * 
     * @usage pending
     */
    @Override
    public ApiResponse updateCustomer( CustomerUpdate inAcc, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.updateCustomer( inAcc, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public BillDetails getBillingDetails( Long inCustomerNumber,
                                          XMLGregorianCalendar inStartDate,
                                          XMLGregorianCalendar inEndDate,
                                          ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getBillingDetails( inCustomerNumber, inStartDate, inEndDate, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public CustomerUsageDetail getCustomerUsageDetail( ApiRequest inRequest,
                                                       Long inCustomerNo,
                                                       String inCrfNo,
                                                       String inRmn,
                                                       String inRtn,
                                                       String inEmailId )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getCustomerUsageDetail( inRequest, inCustomerNo, inCrfNo, inRmn, inRtn, inEmailId );
    }

    /**
     * Integrated
     */
    @Override
    public ApiResponse changeStatus( Long inCustomerNo, String inRemark, String inStatusName, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.changeStatus( inCustomerNo, inRemark, inStatusName, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public ApiResponse changeDeviceDetail( ChangeDeviceDetail inChangedevice, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.changeDeviceDetail( inChangedevice, inRequest );
    }

    // TBD
    @Override
    public ApiResponse changeOffer( OfferAllocation inOffer, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.changeOffer( inOffer, inRequest );
    }

    // TBD
    @Override
    public AddPlanResult addPlan( AddPlan inChangePlan, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.addPlan( inChangePlan, inRequest );
    }

    // TBD
    @Override
    public ApiResponse instantOfferRenew( InstantOfferAllocation inOffer, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.instantOfferRenew( inOffer, inRequest );
    }

    // TBD
    @Override
    public ApiResponse instantPrepaidOfferRenew( InstantOfferAllocation inOffer, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.instantPrepaidOfferRenew( inOffer, inRequest );
    }

    // TBD
    @Override
    public CancelPlanMigrationResult cancelPlanOfferMigration( Long inApiTransID, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.cancelPlanOfferMigration( inApiTransID, inRequest );
    }

    // TBD
    @Override
    public CancelAddPostpaidPlanResult cancelAdditionalPlan( Long inCustomerNo,
                                                             String inPlanCode,
                                                             Boolean inIsImmediate,
                                                             ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.cancelAdditionalPlan( inCustomerNo, inPlanCode, inIsImmediate, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public CustomerPostPaymentResult postPayment( CustomerPostPayment inCustpay, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.postPayment( inCustpay, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public ApiResponse cancelPayment( CustomerCancelPayment inPayment, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.cancelPayment( inPayment, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public PaymentAdjustmentResult paymentAdjustment( PaymentAdjustment inAdjustment, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.paymentAdjustment( inAdjustment, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public AddChargeResult addCharge( ClsAddCharge inAc, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.addCharge( inAc, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public ApiResponse changeAddress( ChangeAddress inCustBillAdd, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.changeAddress( inCustBillAdd, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public CustomerBaringExceptionListResult customerBaringExceptionList( ClsCustomerBaringExceptionList inCbel,
                                                                          ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.customerBaringExceptionList( inCbel, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public CustomerUnbaringExceptionListResult customerUnbaringExceptionList( ClsCustomerUnbaringExceptionList inCuel,
                                                                              ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.customerUnbaringExceptionList( inCuel, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public ChangeBillCycleResult changeBillCycle( Long inCustomerNo,
                                                  String inBillCycleName,
                                                  String inStatus,
                                                  ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.changeBillCycle( inCustomerNo, inBillCycleName, inStatus, inRequest );
    }

    @Override
    public ChangeBusinessPartnerResult changeBusinessPartner( Long inCustomerNo,
                                                              String inOldBusinessPartner,
                                                              String inNewBusinessPartner,
                                                              ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.changeBusinessPartner( inCustomerNo, inOldBusinessPartner, inNewBusinessPartner, inRequest );
    }

    @Override
    public TerminationRefundResult terminationRefund( ClsTerminationRefund inCbel, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.terminationRefund( inCbel, inRequest );
    }

    /**
     * Integrated
     */
    @Override
    public ApiResponse allowGracePeriod( Long inCustomerNo, XMLGregorianCalendar inExpiryDate, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.allowGracePeriod( inCustomerNo, inExpiryDate, inRequest );
    }

    /**
     * Not Required
     */
    @Override
    public CustomerDetail getCustomerDetail( ApiRequest inRequest,
                                             Long inCustomerNo,
                                             String inCrfNo,
                                             String inRmn,
                                             String inRtn,
                                             String inEmailId )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getCustomerDetail( inRequest, inCustomerNo, inCrfNo, inRmn, inRtn, inEmailId );
    }

    /**
     * Not Required
     */
    @Override
    public LastPaymentResult getLastPayment( Long inCustomerNo, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getLastPayment( inCustomerNo, inRequest );
    }

    /**
     * Not Required
     */
    @Override
    public DeviceDetailResult getDeviceDetail( Long inCustomerNo, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getDeviceDetail( inCustomerNo, inRequest );
    }

    /**
     * Not Required
     */
    @Override
    public RefferalDetailsResult getRefferalPoints( Long inCustomerNo, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getRefferalPoints( inCustomerNo, inRequest );
    }

    /**
     * Not Required
     */
    @Override
    public CustomerDiscountDetails getDiscountDetails( Long inCustomerNo, ApiRequest inRequest )
    {
        CustomerOperations operations = new CustomerOperations();
        ICustomer customer = operations.getBasicHttpBindingICustomer();
        return customer.getDiscountDetails( inCustomerNo, inRequest );
    }

    /**
     * Not Required
     */
    @Override
    public CustomerSpeedExceptionListResult customerSpeedExceptionList( ClsCustomerSpeedExceptionList inCbel,
                                                                        ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public CheckPlanFeasibilityResult checkFeasibility( Long inCustomerNo, String inPlanCode, ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public UserSessionResult getUserSessionOfDay( Long inCustomerNo, ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public UserSessionResult getUserSessionOfWeek( Long inCustomerNo, ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public UserSessionResult getUserSessionOfMonth( Long inCustomerNo, ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public P2PReBarringResult p2PReBarring( ClsP2PReBarring inCbel, ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public CustomerNotificationExceptionListResult customerNotificationExceptionList( ClsCustomerNotificationExceptionList inCbel,
                                                                                      ApiRequest inRequest )
    {
        return null;
    }

    /**
     * Not Required
     */
    @Override
    public BillDetail getBillDetail( ApiRequest inRequest, String inBillNumber )
    {
        return null;
    }

    @Override
    public DisableWalledGardenResult disableWalledGarden( ClsDisableWalledGarden inCbel, ApiRequest inRequest )
    {
        // TODO Auto-generated method stub
        return null;
    }
}
