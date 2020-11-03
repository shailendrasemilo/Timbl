package com.np.tele.crm.ext.pojos;

import java.io.Serializable;

public class FileUploader
    implements Serializable
{
    private String fileName;
    private String docType;
    private byte[] dataHanderFile;

    public String getFileName()
    {
        return fileName;
    }

    public void setFileName( String inFileName )
    {
        fileName = inFileName;
    }

    public byte[] getDataHanderFile()
    {
        return dataHanderFile;
    }

    public void setDataHanderFile( byte[] inDataHanderFile )
    {
        dataHanderFile = inDataHanderFile;
    }

    public String getDocType()
    {
        return docType;
    }

    public void setDocType( String inDocType )
    {
        docType = inDocType;
    }
}
