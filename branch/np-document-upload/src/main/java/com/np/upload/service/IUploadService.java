package com.np.upload.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.soap.SOAPException;

import com.np.upload.pojo.UploadDataDto;

@WebService(name = "UploadService")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
public interface IUploadService
{
    @WebMethod
    public UploadDataDto validateUploadedData( @WebParam(name = "module") String module,
                                               @WebParam(name = "mapping") String mapping,
                                               @WebParam(name = "doc") String docType )
        throws SOAPException;
}
