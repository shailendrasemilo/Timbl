package com.np.tele.crm.lms.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.LMSDto;
@WebService(name = "CRMLMSService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ILMSService
{
    @WebMethod
    public LMSDto lmsOperation( @WebParam(name = "operationParam") final String inOperationParam,
                                @WebParam(name = "LMSDto") final LMSDto inLMSDto )
        throws SOAPException;
}


