package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.pojos.CrmCustMyAccountPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;

public class SelfcareDto
    implements Serializable
{
    private CrmCustomerDetailsPojo       customerDetailsPojo;
    private List<CrmCustomerDetailsPojo> customerDetailsPojos;
    private CrmCustMyAccountPojo         custMyAccountPojo;
    private List<CrmCustMyAccountPojo>   custMyAccountPojos;
    private String                       statusCode      = null;
    private String                       statusDesc      = null;
    private String                       clientIPAddress = null;
    private String                       serverIPAddress = null;

    public SelfcareDto()
    {
    }

    public SelfcareDto( CRMServiceCode serviceCode )
    {
        statusCode = serviceCode.getStatusCode();
        statusDesc = serviceCode.getStatusDesc();
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }

    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojos()
    {
        return customerDetailsPojos;
    }

    public void setCustomerDetailsPojos( List<CrmCustomerDetailsPojo> inCustomerDetailsPojos )
    {
        customerDetailsPojos = inCustomerDetailsPojos;
    }

    public CrmCustMyAccountPojo getCustMyAccountPojo()
    {
        return custMyAccountPojo;
    }

    public void setCustMyAccountPojo( CrmCustMyAccountPojo inCustMyAccountPojo )
    {
        custMyAccountPojo = inCustMyAccountPojo;
    }

    public List<CrmCustMyAccountPojo> getCustMyAccountPojos()
    {
        return custMyAccountPojos;
    }

    public void setCustMyAccountPojos( List<CrmCustMyAccountPojo> inCustMyAccountPojos )
    {
        custMyAccountPojos = inCustMyAccountPojos;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }
}
