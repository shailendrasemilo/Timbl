package com.np.upload.service;

import javax.jws.WebService;
import javax.xml.soap.SOAPException;

import com.np.upload.pojo.UploadDataDto;

@WebService(endpointInterface = "com.np.upload.service.IUploadService")
public class UploadService
    implements IUploadService
{
    public UploadDataDto validateUploadedData( String module, String mapping, String docType )
        throws SOAPException
    {
        return null;
    }
}
