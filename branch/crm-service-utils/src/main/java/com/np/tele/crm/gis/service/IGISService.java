package com.np.tele.crm.gis.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.GISDto;

@WebService(name = "CRMGISService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IGISService
{
    @WebMethod
    public GISDto gisOperation( @WebParam(name = "ServiceParam") final String inServiceParam,
                                @WebParam(name = "operationParam") final String inOperationParam,
                                @WebParam(name = "GISDto") final GISDto inGisDto )
        throws SOAPException;
}
