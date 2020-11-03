package com.np.tele.crm.cap.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.SelfcareDto;

@WebService(name = "CrmCapService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICrmCapService
{
    @WebMethod
    public CrmCapDto crmCapOperation( @WebParam(name = "ServiceParam") final String inServiceParam,
                                      @WebParam(name = "operationParam") final String inOperationParam,
                                      @WebParam(name = "CrmCapDto") final CrmCapDto inCrmCapDto )
        throws SOAPException;

    @WebMethod
    public SelfcareDto selfcareOperation( @WebParam(name = "ServiceParam") final String inServiceParam,
                                          @WebParam(name = "operationParam") final String inOperationParam,
                                          @WebParam(name = "SelfcareDto") final SelfcareDto inSelfcareDto )
        throws SOAPException;
}
