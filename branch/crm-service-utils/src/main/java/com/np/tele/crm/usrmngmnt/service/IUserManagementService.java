package com.np.tele.crm.usrmngmnt.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.CrmuserDetailsDto;

@WebService(name = "CRMUserManagement")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IUserManagementService
{
    @WebMethod
    public CrmuserDetailsDto loginAuthentication( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto changePassword( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto userOperations( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto,
                                             @WebParam(name = "operationName") final String inOperation )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto searchUser( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto userMapping( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto getUserAssignAccess( @WebParam(name = "userId") final String inUserID )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto userEscalationAndWorkflowMapping( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto,
                                                               @WebParam(name = "operationName") final String inOperation )
        throws SOAPException;

    @WebMethod
    public CrmuserDetailsDto getUsersByParameter( @WebParam(name = "userDto") final CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException;
    
    @WebMethod
    public CrmuserDetailsDto getCustomerAssociations( @WebParam(name = "userId") final String inUserId )
        throws SOAPException;
}
