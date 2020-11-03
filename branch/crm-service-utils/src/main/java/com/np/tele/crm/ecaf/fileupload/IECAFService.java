package com.np.tele.crm.ecaf.fileupload;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.ECafDto;

@WebService(name = "ECAFService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IECAFService
{
    @WebMethod
    public ECafDto saveECAF( @WebParam(header = true, name = "username") final String inAuthUsername,
                             @WebParam(header = true, name = "password") final String inAuthPassword,
                             @WebParam(name = "ECafDto") final ECafDto inECafDto )
        throws SOAPException;
}
