package com.np.tele.crm.pojos;

public class CrmQrcCommonPojo
{
    private String           customerId;
    private CrmRcaReasonPojo rcaReasonPojo;
    private String           action;
    private RemarksPojo      remarksPojo;
    private long             customerRecordId;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public CrmRcaReasonPojo getRcaReasonPojo()
    {
        return rcaReasonPojo;
    }

    public void setRcaReasonPojo( CrmRcaReasonPojo rcaReasonPojo )
    {
        this.rcaReasonPojo = rcaReasonPojo;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction( String action )
    {
        this.action = action;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo remarksPojo )
    {
        this.remarksPojo = remarksPojo;
    }

    public long getCustomerRecordId()
    {
        return customerRecordId;
    }

    public void setCustomerRecordId( long customerRecordId )
    {
        this.customerRecordId = customerRecordId;
    }
}
