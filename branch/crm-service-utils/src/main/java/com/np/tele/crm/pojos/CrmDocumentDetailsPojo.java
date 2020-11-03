package com.np.tele.crm.pojos;

// default package
// Generated Oct 16, 2013 12:03:19 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmDocumentDetailsPojo generated by hbm2java
 */
public class CrmDocumentDetailsPojo
    implements java.io.Serializable
{
    private long   recordId;
    private long   customerRecordId;
    private String idProof;
    private String addressProof;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String displayCreatedTime;
    private String displayLastModifiedTime;

    public CrmDocumentDetailsPojo()
    {
    }

    public CrmDocumentDetailsPojo( Long recordId, String createdBy )
    {
        this.recordId = recordId;
        this.createdBy = createdBy;
    }

    public CrmDocumentDetailsPojo( Long recordId,
                                   Long customerRecordId,
                                   String idProof,
                                   String addressProof,
                                   String createdBy,
                                   Date createdTime,
                                   String lastModifiedBy,
                                   Date lastModifiedTime )
    {
        this.recordId = recordId;
        this.customerRecordId = customerRecordId;
        this.idProof = idProof;
        this.addressProof = addressProof;
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

    public long getCustomerRecordId()
    {
        return customerRecordId;
    }

    public void setCustomerRecordId( long customerRecordId )
    {
        this.customerRecordId = customerRecordId;
    }

    public String getIdProof()
    {
        return this.idProof;
    }

    public void setIdProof( String idProof )
    {
        this.idProof = idProof;
    }

    public String getAddressProof()
    {
        return this.addressProof;
    }

    public void setAddressProof( String addressProof )
    {
        this.addressProof = addressProof;
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
        /* if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
         {
             displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( createdTime );
         }*/
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
       /* if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
        {
            displayLastModifiedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( lastModifiedTime );
        }*/
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String displayCreatedTime )
    {
        this.displayCreatedTime = displayCreatedTime;
    }

    public String getDisplayLastModifiedTime()
    {
        return displayLastModifiedTime;
    }

    public void setDisplayLastModifiedTime( String displayLastModifiedTime )
    {
        this.displayLastModifiedTime = displayLastModifiedTime;
    }
}