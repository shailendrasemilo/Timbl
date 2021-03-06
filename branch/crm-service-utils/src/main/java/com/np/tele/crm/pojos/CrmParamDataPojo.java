package com.np.tele.crm.pojos;

import java.util.Date;
import java.util.List;

/**
 * CrmParamData generated by hbm2java
 */
public class CrmParamDataPojo
    implements java.io.Serializable
{
    private long         recordId;
    private String       paramId;
    private String       paramValue;
    private String       mappingId;
    private String       requestType;
    private String       paramType;
    private String       createdBy;
    private Date         createdTime;
    private String       lastModifiedBy;
    private Date         lastModifiedTime;
    private List<String> displayValues;

    public CrmParamDataPojo()
    {
    }

    public CrmParamDataPojo( Long recordId, String paramId, String paramValue, String createdBy )
    {
        this.recordId = recordId;
        this.paramId = paramId;
        this.paramValue = paramValue;
        this.createdBy = createdBy;
    }

    public CrmParamDataPojo( Long recordId,
                             String paramId,
                             String paramValue,
                             String mappingId,
                             String requestType,
                             String paramType,
                             String createdBy,
                             Date createdTime,
                             String lastModifiedBy,
                             Date lastModifiedTime )
    {
        this.recordId = recordId;
        this.paramId = paramId;
        this.paramValue = paramValue;
        this.mappingId = mappingId;
        this.requestType = requestType;
        this.paramType = paramType;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getRecordId()
    {
        return recordId;
    }

    public void setRecordId( long recordId )
    {
        this.recordId = recordId;
    }

    public String getParamId()
    {
        return this.paramId;
    }

    public void setParamId( String paramId )
    {
        this.paramId = paramId;
    }

    public String getParamValue()
    {
        return this.paramValue;
    }

    public void setParamValue( String paramValue )
    {
        this.paramValue = paramValue;
    }

    public String getMappingId()
    {
        return this.mappingId;
    }

    public void setMappingId( String mappingId )
    {
        this.mappingId = mappingId;
    }

    public String getRequestType()
    {
        return this.requestType;
    }

    public void setRequestType( String requestType )
    {
        this.requestType = requestType;
    }

    public String getParamType()
    {
        return this.paramType;
    }

    public void setParamType( String paramType )
    {
        this.paramType = paramType;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date lastModifiedTime )
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    public List<String> getDisplayValues()
    {
        return displayValues;
    }

    public void setDisplayValues( List<String> inDisplayValues )
    {
        displayValues = inDisplayValues;
    }
}
