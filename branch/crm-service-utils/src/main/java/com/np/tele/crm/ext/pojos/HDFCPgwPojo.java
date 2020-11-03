package com.np.tele.crm.ext.pojos;

import java.io.Serializable;

public class HDFCPgwPojo
    implements Serializable
{
    // 90003818
    private String transportal_id       = null;
    // 90003818
    private String transportal_password = null;
    // 356
    private String currency_code        = null;
    // USA
    private String language_code        = null;
    // 1
    private String req_action           = null;
    // https://securepgtest.fssnet.co.in/pgway/servlet/PaymentInitHTTPServlet
    private String gateway_url          = null;
    // http://125.63.90.212:18080/hdfcresponse.do
    private String response_url         = null;
    // http://125.63.90.212:18080/hdfcresponse.do
    private String error_url            = null;

    public String getTransportal_id()
    {
        return transportal_id;
    }

    public void setTransportal_id( String inTransportal_id )
    {
        transportal_id = inTransportal_id;
    }

    public String getTransportal_password()
    {
        return transportal_password;
    }

    public void setTransportal_password( String inTransportal_password )
    {
        transportal_password = inTransportal_password;
    }

    public String getCurrency_code()
    {
        return currency_code;
    }

    public void setCurrency_code( String inCurrency_code )
    {
        currency_code = inCurrency_code;
    }

    public String getLanguage_code()
    {
        return language_code;
    }

    public void setLanguage_code( String inLanguage_code )
    {
        language_code = inLanguage_code;
    }

    public String getReq_action()
    {
        return req_action;
    }

    public void setReq_action( String inReq_action )
    {
        req_action = inReq_action;
    }

    public String getGateway_url()
    {
        return gateway_url;
    }

    public void setGateway_url( String inGateway_url )
    {
        gateway_url = inGateway_url;
    }

    public String getResponse_url()
    {
        return response_url;
    }

    public void setResponse_url( String inResponse_url )
    {
        response_url = inResponse_url;
    }

    public String getError_url()
    {
        return error_url;
    }

    public void setError_url( String inError_url )
    {
        error_url = inError_url;
    }
}
