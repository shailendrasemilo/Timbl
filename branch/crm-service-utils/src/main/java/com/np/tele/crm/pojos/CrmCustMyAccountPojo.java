package com.np.tele.crm.pojos;

// default package
// Generated Oct 13, 2014 1:09:38 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmCustMyAccountPojo generated by hbm2java
 */
public class CrmCustMyAccountPojo
    implements java.io.Serializable
{
    private long   recordId;
    private String customerId;
    private String password;
    private String password1;
    private String password2;
    private String password3;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;
    private long   rmn;
    private String custEmailId;

    public CrmCustMyAccountPojo()
    {
    }

    public CrmCustMyAccountPojo( Long recordId, String customerId, String createdBy, String status )
    {
        this.recordId = recordId;
        this.customerId = customerId;
        this.createdBy = createdBy;
        this.status = status;
    }

    public CrmCustMyAccountPojo( Long recordId,
                                 String customerId,
                                 String password,
                                 String password1,
                                 String password2,
                                 String password3,
                                 String createdBy,
                                 Date createdTime,
                                 String lastModifiedBy,
                                 Date lastModifiedTime,
                                 String status )
    {
        this.recordId = recordId;
        this.customerId = customerId;
        this.password = password;
        this.password1 = password1;
        this.password2 = password2;
        this.password3 = password3;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    public long getRecordId()
    {
        return recordId;
    }

    public void setRecordId( long recordId )
    {
        this.recordId = recordId;
    }

    public String getCustomerId()
    {
        return this.customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getPassword1()
    {
        return this.password1;
    }

    public void setPassword1( String password1 )
    {
        this.password1 = password1;
    }

    public String getPassword2()
    {
        return this.password2;
    }

    public void setPassword2( String password2 )
    {
        this.password2 = password2;
    }

    public String getPassword3()
    {
        return this.password3;
    }

    public void setPassword3( String password3 )
    {
        this.password3 = password3;
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

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public long getRmn()
    {
        return rmn;
    }

    public void setRmn( long inRmn )
    {
        rmn = inRmn;
    }

    public String getCustEmailId()
    {
        return custEmailId;
    }

    public void setCustEmailId( String inCustEmailId )
    {
        custEmailId = inCustEmailId;
    }
}