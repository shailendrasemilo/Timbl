package com.np.tele.selfcare.forms;

import org.apache.struts.action.ActionForm;

import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;

public class SelfcareVerificationForm
    extends ActionForm
{
    private CrmCustomerDetailsPojo customerDetailsPojo;

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }
}
