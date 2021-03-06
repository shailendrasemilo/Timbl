package com.np.tele.crm.pojos;

// Generated Nov 18, 2014 6:15:08 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmCustAssetDetails generated by hbm2java
 */
public class CrmCustAssetDetailsPojo
    implements java.io.Serializable
{
    private long   assetDetailsId;
    private long   customerRecordId;
    private String categoryName;
    private String categoryValue;
    private Byte   categoryCount;
    private Double categoryAmount;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;

    public CrmCustAssetDetailsPojo()
    {
    }

    public CrmCustAssetDetailsPojo( long assetDetailsId,
                                    long customerRecordId,
                                    String categoryName,
                                    String createdBy,
                                    String status )
    {
        this.assetDetailsId = assetDetailsId;
        this.customerRecordId = customerRecordId;
        this.categoryName = categoryName;
        this.createdBy = createdBy;
        this.status = status;
    }

    public CrmCustAssetDetailsPojo( long assetDetailsId,
                                    long customerRecordId,
                                    String categoryName,
                                    String categoryValue,
                                    Byte categoryCount,
                                    Double categoryAmount,
                                    String createdBy,
                                    Date createdTime,
                                    String lastModifiedBy,
                                    Date lastModifiedTime,
                                    String status )
    {
        this.assetDetailsId = assetDetailsId;
        this.customerRecordId = customerRecordId;
        this.categoryName = categoryName;
        this.categoryValue = categoryValue;
        this.categoryCount = categoryCount;
        this.categoryAmount = categoryAmount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    public long getAssetDetailsId()
    {
        return assetDetailsId;
    }

    public void setAssetDetailsId( long assetDetailsId )
    {
        this.assetDetailsId = assetDetailsId;
    }

    public Double getCategoryAmount()
    {
        return categoryAmount;
    }

    public void setCategoryAmount( Double categoryAmount )
    {
        this.categoryAmount = categoryAmount;
    }

    public long getCustomerRecordId()
    {
        return this.customerRecordId;
    }

    public void setCustomerRecordId( long customerRecordId )
    {
        this.customerRecordId = customerRecordId;
    }

    public String getCategoryName()
    {
        return this.categoryName;
    }

    public void setCategoryName( String categoryName )
    {
        this.categoryName = categoryName;
    }

    public String getCategoryValue()
    {
        return this.categoryValue;
    }

    public void setCategoryValue( String categoryValue )
    {
        this.categoryValue = categoryValue;
    }

    public Byte getCategoryCount()
    {
        return this.categoryCount;
    }

    public void setCategoryCount( Byte categoryCount )
    {
        this.categoryCount = categoryCount;
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
}
