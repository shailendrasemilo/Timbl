package com.np.tele.crm.masterdata.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.tele.crm.dto.MasterDataDto;

@WebService(name = "MasterData")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IMasterDataService
{
    @WebMethod
    public MasterDataDto getMasterData( @WebParam(name = "masterDto") final MasterDataDto inMasterDataDto,
                                        @WebParam(name = "pojoName") final String inPojoName )
        throws SOAPException;

    @WebMethod
    public MasterDataDto rolesGroupOperations( @WebParam(name = "masterDto") final MasterDataDto inMasterDataDto,
                                               @WebParam(name = "operation") final String inOperation )
        throws SOAPException;

    @WebMethod
    public MasterDataDto parameterGroupOperations( @WebParam(name = "masterDto") final MasterDataDto inMasterDataDto,
                                                   @WebParam(name = "operation") final String inOperation )
        throws SOAPException;

    @WebMethod
    public MasterDataDto externalApplication( @WebParam(name = "ServiceParam") final String inServiceParam,
                                              @WebParam(name = "masterDto") final MasterDataDto inMasterDataDto )
        throws SOAPException;

    @WebMethod
    public MasterDataDto partnerOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                            @WebParam(name = "masterDto") final MasterDataDto inMasterDataDto )
        throws SOAPException;
    
    @WebMethod
    public MasterDataDto masterOperations( @WebParam(name = "ServiceParam") final String inServiceParam,
                                           @WebParam(name = "MasterType") final String inMasterType,
                                            @WebParam(name = "masterDto") final MasterDataDto inMasterDataDto )
        throws SOAPException;
   
}
