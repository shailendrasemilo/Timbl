package com.np.upload.spring.form;

public class UploadForm
{
    private String   module;
    private String   mapping;
    private String[] docTypes;

    public UploadForm()
    {
    }

    public UploadForm( String module, String mapping )
    {
        this.module = module;
        this.mapping = mapping;
    }

    public String getModule()
    {
        return module;
    }

    public void setModule( String module )
    {
        this.module = module;
    }

    public String getMapping()
    {
        return mapping;
    }

    public void setMapping( String mapping )
    {
        this.mapping = mapping;
    }

    public String[] getDocTypes()
    {
        return docTypes;
    }

    public void setDocTypes( String[] docTypes )
    {
        this.docTypes = docTypes;
    }
}
