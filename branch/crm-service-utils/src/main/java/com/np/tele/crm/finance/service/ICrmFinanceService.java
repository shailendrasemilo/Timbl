package com.np.tele.crm.finance.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.CrmFinanceDto;

@WebService(name = "CrmFinanceService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICrmFinanceService
{
    @WebMethod
    public CrmFinanceDto trackPayment( @WebParam(name = "ServiceParam") final String inServiceParam,
                                       @WebParam(name = "CrmParam") final String inCrmParam,
                                       @WebParam(name = "CrmFinanceDto") final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException;

    @WebMethod
    public CrmFinanceDto postPayment( @WebParam(name = "ServiceParam") final String inServiceParam,
                                      @WebParam(name = "CrmParam") final String inCrmParam,
                                      @WebParam(name = "CrmFinanceDto") final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException;

    @WebMethod
    public CrmFinanceDto pgwOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                         @WebParam(name = "CrmParam") final String inCrmParam,
                                         @WebParam(name = "CrmFinanceDto") final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException;
}
