package com.np.tele.crm.config.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.ConfigDto;

@WebService(name = "CRMConfigService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface ICrmConfigService
{
    @WebMethod
    public ConfigDto configOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                       @WebParam(name = "ConfigParam") final String inConfigParam,
                                       @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto getInbox( @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto getMappingIdtByAppointMents( @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto geMappingIdByRemarks( @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto changeInboxBin( @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto saveAppointment( @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto getMappedUsersByUser( @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto auditLogOperation( @WebParam(name = "operationParam") final String inOperationParam,
                                        @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;

    @WebMethod
    public ConfigDto customerActivityOps( @WebParam(name = "operationParam") final String inOperationParam,
                                          @WebParam(name = "ConfigDto") final ConfigDto inConfigDto )
        throws SOAPException;
}
