package com.np.tele.crm.billing.manager;

import java.util.Date;

import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;

public interface ICrmBillingManager
{
    void createCustomer( CrmCapDto inCustomerDetails );

    void customerActivation( CrmCapDto inCrmCapDto );

    void getDistributorInventoryList( ConfigDto inConfigDto );

    void postPayment( CrmFinanceDto inCrmFinanceDto );

    void cancelPayment( CrmFinanceDto inCrmFinanceDto );

    void addCharge( CrmBillingDto inBillingDto );

    void changeStatus( CrmBillingDto inBillingDto );

    CrmBillingDto changeAddress( CrmAddressDetailsPojo inCrmAddrDetailsPojo );

    CrmBillingDto changeDeviceDetails( CrmQrcDto inCrmQrcDto );

    CrmBillingDto postWaiver( CrmQrcDto inCrmQrcDto );

    void getBillingDetails( CrmQrcDto inCrmQrcDto );

    CrmBillingDto barringException( CrmQrcDto inCrmQrcDto, String param );

    CrmBillingDto unbarringException( CrmQrcDto inCrmQrcDto, String param );

    <E> CrmBillingDto updateCustomer( E e, long customerNo );

    CrmBillingDto addPlan( CrmQrcDto inCrmQrcDto, String param, boolean immediate );

    CrmBillingDto cancelPlanOfferMigration( String inApiTransID );

    void instantOfferRenew( CrmQrcDto inCrmQrcDto );

    CrmBillingDto getCustomerUsageDetail( String customerId, boolean isPostPaid );

    void changeOffer( CrmQrcDto inCrmQrcDto, Date date );

    void cancelAdditionalPlan( CrmBillingDto inBillingDto );

    void changeBillCycle( CrmBillingDto inBillingDto );

    void allowGracePeriod( CrmBillingDto billingDto );

    void terminationRefund( CrmBillingDto inBillingDto );

    CrmBillingDto changeBusinessPartner( String inCustomerId, String inOldPartner, String inNewPartner );

	void instantPrepaidOfferRenew(CrmQrcDto inCrmQrcDto);
}
