package com.np.tele.crm.ext.pojos;

public class EasyBillCustomerDetailsPojo
{
    private String customerId;
    private String crfNo;
    private long   rmn;
    private long   rtn;
    private String emailId;
    private Double balance;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getCrfNo()
    {
        return crfNo;
    }

    public void setCrfNo( String inCrfNo )
    {
        crfNo = inCrfNo;
    }

    public long getRmn()
    {
        return rmn;
    }

    public void setRmn( long inRmn )
    {
        rmn = inRmn;
    }

    public long getRtn()
    {
        return rtn;
    }

    public void setRtn( long inRtn )
    {
        rtn = inRtn;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId( String inEmailId )
    {
        emailId = inEmailId;
    }

    public Double getBalance()
    {
        return balance;
    }

    public void setBalance( Double inBalance )
    {
        balance = inBalance;
    }
}
