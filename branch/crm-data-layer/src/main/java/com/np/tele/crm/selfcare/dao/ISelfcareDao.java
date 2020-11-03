package com.np.tele.crm.selfcare.dao;

import java.util.List;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.pojos.CrmCustMyAccountPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;

public interface ISelfcareDao
{
    List<CrmCustomerDetailsPojo> getCustomerDetailAccounts( CrmCustMyAccountPojo inCustAccountPojo );

    CrmCustMyAccountPojo authenticate( CrmCustMyAccountPojo inCustAccountPojo );

    CRMServiceCode changePassword( CrmCustMyAccountPojo inCrmCustMyAccountPojo );

    List<CrmCustMyAccountPojo> getCustomerMyAccounts( List<String> inCustIds, String inPassword );

    CRMServiceCode resetPassword( CrmCustomerDetailsPojo inCustomerDetailsPojo );

    List<CrmCustomerDetailsPojo> getCustomerDetailAccounts( List<String> inCustIds );

    List<CrmCustomerDetailsPojo> getCustomerDetailsList( CrmCustomerDetailsPojo inCustomerDetailsPojo );
}
