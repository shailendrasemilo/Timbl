package com.np.tele.crm.ext.pojos;

import java.io.Serializable;

public class TechProcessPgwPojo
    implements Serializable
{
    // INR
    private String currencyCode   = null;
    // provided to merchant
    private String iv             = null;
    // provided to merchant
    private String key            = null;
    // provided to merchant
    private String merchantCode   = null;
    // T / S / R / O
    private String requestType    = null;
    // http://125.63.90.212:18080/tpresponse.do
    private String returnURL      = null;
    // https://www.tekprocess.co.in/PaymentGateway/services/TransactionDetailsNew
    private String serviceLocator = null;
    // NTPL
    private String schemeCode     = null;
    // our url for iframe parent
    private String appReturnUrl   = null;

    public String getCurrencyCode()
    {
        return currencyCode;
    }

    public void setCurrencyCode( String inCurrencyCode )
    {
        currencyCode = inCurrencyCode;
    }

    public String getIv()
    {
        return iv;
    }

    public void setIv( String inIv )
    {
        iv = inIv;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey( String inKey )
    {
        key = inKey;
    }

    public String getMerchantCode()
    {
        return merchantCode;
    }

    public void setMerchantCode( String inMerchantCode )
    {
        merchantCode = inMerchantCode;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType( String inRequestType )
    {
        requestType = inRequestType;
    }

    public String getReturnURL()
    {
        return returnURL;
    }

    public void setReturnURL( String inReturnURL )
    {
        returnURL = inReturnURL;
    }

    public String getServiceLocator()
    {
        return serviceLocator;
    }

    public void setServiceLocator( String inServiceLocator )
    {
        serviceLocator = inServiceLocator;
    }

    public String getSchemeCode()
    {
        return schemeCode;
    }

    public void setSchemeCode( String inSchemeCode )
    {
        schemeCode = inSchemeCode;
    }

    public String getAppReturnUrl()
    {
        return appReturnUrl;
    }

    public void setAppReturnUrl( String inAppReturnUrl )
    {
        appReturnUrl = inAppReturnUrl;
    }
}
