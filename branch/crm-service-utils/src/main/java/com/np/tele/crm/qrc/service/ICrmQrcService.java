package com.np.tele.crm.qrc.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.dto.QrcConfigDto;

@WebService(name = "CrmQrcService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICrmQrcService
{
    @WebMethod
    public CrmQrcDto qrcOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                    @WebParam(name = "CrmParam") final String inCrmParam,
                                    @WebParam(name = "CrmQrcDto") final CrmQrcDto inCrmQrcDto )
        throws SOAPException;

    @WebMethod
    public CrmQrcDto ticketOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                       @WebParam(name = "CrmParam") final String inCrmParam,
                                       @WebParam(name = "CrmQrcDto") final CrmQrcDto inCrmQrcDto )
        throws SOAPException;

    @WebMethod
    public QrcConfigDto qrcConfigOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                             @WebParam(name = "CrmParam") final String inCrmParam,
                                             @WebParam(name = "CrmQrcDto") final QrcConfigDto inQrcConfigDto )
        throws SOAPException;

    @WebMethod
    public CrmQrcDto customerProfileOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                                @WebParam(name = "CrmParam") final String inCrmParam,
                                                @WebParam(name = "CrmQrcDto") final CrmQrcDto inCrmQrcDto )
        throws SOAPException;

    @WebMethod
    public CrmQrcDto workFlowOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                         @WebParam(name = "CrmParam") final String inCrmParam,
                                         @WebParam(name = "CrmQrcDto") final CrmQrcDto inCrmQrcDto )
        throws SOAPException;

    @WebMethod
    public CrmMassOutageDto massOutageOperations( @WebParam(name = "ServiceParam") final String inCrmParam,
                                                  @WebParam(name = "CrmMassOutageDto") final CrmMassOutageDto inCrmMassOutageDto )
        throws SOAPException;
}
