package com.np.tele.crm.billing.manager;

import org.datacontract.schemas._2004._07.AddPlan;
import org.datacontract.schemas._2004._07.ApiRequest;
import org.datacontract.schemas._2004._07.ChangeAddress;
import org.datacontract.schemas._2004._07.ChangeDeviceDetail;
import org.datacontract.schemas._2004._07.ClsAddCharge;
import org.datacontract.schemas._2004._07.ClsCustomerBaringExceptionList;
import org.datacontract.schemas._2004._07.ClsCustomerUnbaringExceptionList;
import org.datacontract.schemas._2004._07.ClsTerminationRefund;
import org.datacontract.schemas._2004._07.Customer;
import org.datacontract.schemas._2004._07.CustomerActivation;
import org.datacontract.schemas._2004._07.CustomerCancelPayment;
import org.datacontract.schemas._2004._07.CustomerPostPayment;
import org.datacontract.schemas._2004._07.CustomerUpdate;
import org.datacontract.schemas._2004._07.InstantOfferAllocation;
import org.datacontract.schemas._2004._07.ObjectFactory;
import org.datacontract.schemas._2004._07.OfferAllocation;
import org.datacontract.schemas._2004._07.PaymentAdjustment;

import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;

public interface IBillingConversionMgr
{
    public String CRM_BILLING_SYSTEM_ID       = "crm.billing.system.id";
    public String CRM_BILLING_SYSTEM_PASSWORD = "crm.billing.system.password";

    Customer createCustomer( CrmCapDto inCrmCapDto, ObjectFactory inBillingFactory );

    ApiRequest getApiRequestPOJO( ObjectFactory inBillingFactory );

    CustomerActivation customerActivation( CrmCapDto inCrmCapDto, ObjectFactory inBillingFactory );

    CustomerPostPayment postPayment( CrmPaymentDetailsPojo inCrmPaymentDetailsPojo, ObjectFactory inBillingFactory );

    CustomerCancelPayment cancelPayment( CrmPaymentDetailsPojo inPaymentDetailsPojo, ObjectFactory inBillingFactory );

    ClsAddCharge addCharge( CrmBillingDto inBillingDto, ObjectFactory inBillingFactory );

    ChangeAddress changeAddress( CrmAddressDetailsPojo inCrmAddrDetailsPojo, ObjectFactory inBillingFactory );

    ChangeDeviceDetail changeDeviceDetails( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory );

    PaymentAdjustment postWaiver( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory );

    ClsCustomerBaringExceptionList barringException( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory, String param );

    ClsCustomerUnbaringExceptionList unbarringException( CrmQrcDto inCrmQrcDto,
                                                         ObjectFactory inBillingFactory,
                                                         String param );

    <E> CustomerUpdate updateCustomer( E e, ObjectFactory inBillingFactory, long inCustomer );

    AddPlan addPlan( CrmQrcDto inCrmQrcDto, String inParam, ObjectFactory inBillingFactory );

    OfferAllocation changeOffer( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory );

    InstantOfferAllocation instantOfferRenew( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory );

    ClsTerminationRefund terminationRefund( CrmBillingDto inBillingDto, ObjectFactory inBillingFactory );

    InstantOfferAllocation instantPrepaidOfferRenew( CrmQrcDto inCrmQrcDto, ObjectFactory billingFactory );
}
