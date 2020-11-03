package com.np.tele.crm.ext.pojos;

import java.io.Serializable;

public class AtomPgwPojo
    implements Serializable
{
    // 160
    private String loginId;
    // Test@123
    private String password;
    // NBFundTransfer
    private String transType;
    // NSE
    private String prodId;
    // INR
    private String txnCurr;
    // 0
    private String txnScAmt;
    // http://203.114.240.183/paynetz/epi/fts
    private String paymentUrl;
    // http://125.63.90.212:8080/atomresponse.do
    private String responseUrl;

    public String getLoginId()
    {
        return loginId;
    }

    public void setLoginId( String inLoginId )
    {
        loginId = inLoginId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String inPassword )
    {
        password = inPassword;
    }

    public String getTransType()
    {
        return transType;
    }

    public void setTransType( String inTransType )
    {
        transType = inTransType;
    }

    public String getProdId()
    {
        return prodId;
    }

    public void setProdId( String inProdId )
    {
        prodId = inProdId;
    }

    public String getTxnCurr()
    {
        return txnCurr;
    }

    public void setTxnCurr( String inTxnCurr )
    {
        txnCurr = inTxnCurr;
    }

    public String getTxnScAmt()
    {
        return txnScAmt;
    }

    public void setTxnScAmt( String inTxnScAmt )
    {
        txnScAmt = inTxnScAmt;
    }

    public String getPaymentUrl()
    {
        return paymentUrl;
    }

    public void setPaymentUrl( String inPaymentUrl )
    {
        paymentUrl = inPaymentUrl;
    }

    public String getResponseUrl()
    {
        return responseUrl;
    }

    public void setResponseUrl( String inResponseUrl )
    {
        responseUrl = inResponseUrl;
    }
}
