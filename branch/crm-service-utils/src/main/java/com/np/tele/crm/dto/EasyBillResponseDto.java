package com.np.tele.crm.dto;

import java.util.List;

import com.np.tele.crm.ext.pojos.EasyBillCustomerDetailsPojo;

public class EasyBillResponseDto
{
    private String                            statusDesc;
    private String                            statusCode;
    private List<EasyBillCustomerDetailsPojo> easyBillCustDetailsPojos = null;
    private List<EasyBillPaymentDto>          easyBillPaymentList      = null;

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public List<EasyBillCustomerDetailsPojo> getEasyBillCustDetailsPojos()
    {
        return easyBillCustDetailsPojos;
    }

    public void setEasyBillCustDetailsPojos( List<EasyBillCustomerDetailsPojo> inEasyBillCustDetailsPojos )
    {
        easyBillCustDetailsPojos = inEasyBillCustDetailsPojos;
    }

    public List<EasyBillPaymentDto> getEasyBillPaymentList()
    {
        return easyBillPaymentList;
    }

    public void setEasyBillPaymentList( List<EasyBillPaymentDto> inEasyBillPaymentList )
    {
        easyBillPaymentList = inEasyBillPaymentList;
    }
}
