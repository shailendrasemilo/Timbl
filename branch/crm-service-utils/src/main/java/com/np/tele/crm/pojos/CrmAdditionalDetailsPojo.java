package com.np.tele.crm.pojos;

// default package
// Generated Oct 16, 2013 12:03:19 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmAdditionalDetailsPojo generated by hbm2java
 */
public class CrmAdditionalDetailsPojo
    implements java.io.Serializable
{
    private long   recordId;
    private long   customerRecordId;
    private String vehicleDetail;
    private String vehicleMake;
    private String mobileMake;
    private String mobileNo;
    private String mobileImeiNo;
    private String crcdType;
    private long   crcdCardNo;
    private Date   crcdExpiryDate;
    private String bankName;
    private String bankBranch;
    private long   bankAccountNo;
    private String bankAccountType;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String displayCreatedTime;
    private String displayLastModifiedTime;
    private String displayCrcdExpiryDate;

    public CrmAdditionalDetailsPojo()
    {
    }

    public CrmAdditionalDetailsPojo( Long recordId, String createdBy )
    {
        this.recordId = recordId;
        this.createdBy = createdBy;
    }

    public CrmAdditionalDetailsPojo( Long recordId,
                                     Long customerRecordId,
                                     String vehicleDetail,
                                     String mobileMake,
                                     String mobileNo,
                                     String mobileImeiNo,
                                     String crcdType,
                                     long crcdCardNo,
                                     Date crcdExpiryDate,
                                     String bankName,
                                     String bankBranch,
                                     long bankAccountNo,
                                     String bankAccountType,
                                     String createdBy,
                                     Date createdTime,
                                     String lastModifiedBy,
                                     Date lastModifiedTime )
    {
        this.recordId = recordId;
        this.customerRecordId = customerRecordId;
        this.vehicleDetail = vehicleDetail;
        this.mobileMake = mobileMake;
        this.mobileNo = mobileNo;
        this.mobileImeiNo = mobileImeiNo;
        this.crcdType = crcdType;
        this.crcdCardNo = crcdCardNo;
        this.crcdExpiryDate = crcdExpiryDate;
        this.bankName = bankName;
        this.bankBranch = bankBranch;
        this.bankAccountNo = bankAccountNo;
        this.bankAccountType = bankAccountType;
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

    public String getVehicleDetail()
    {
        return this.vehicleDetail;
    }

    public void setVehicleDetail( String vehicleDetail )
    {
        this.vehicleDetail = vehicleDetail;
    }

    public String getMobileMake()
    {
        return this.mobileMake;
    }

    public void setMobileMake( String mobileMake )
    {
        this.mobileMake = mobileMake;
    }

    public String getMobileNo()
    {
        return this.mobileNo;
    }

    public void setMobileNo( String mobileNo )
    {
        this.mobileNo = mobileNo;
    }

    public String getMobileImeiNo()
    {
        return this.mobileImeiNo;
    }

    public void setMobileImeiNo( String mobileImeiNo )
    {
        this.mobileImeiNo = mobileImeiNo;
    }

    public String getCrcdType()
    {
        return this.crcdType;
    }

    public void setCrcdType( String crcdType )
    {
        this.crcdType = crcdType;
    }

    public long getCrcdCardNo()
    {
        return this.crcdCardNo;
    }

    public void setCrcdCardNo( long crcdCardNo )
    {
        this.crcdCardNo = crcdCardNo;
    }

    public Date getCrcdExpiryDate()
    {
        return this.crcdExpiryDate;
    }

    public void setCrcdExpiryDate( Date crcdExpiryDate )
    {
        this.crcdExpiryDate = crcdExpiryDate;
        /*if ( StringUtils.isEmpty( displayCrcdExpiryDate ) && StringUtils.isValidObj( crcdExpiryDate ) )
        {
            displayCrcdExpiryDate = IDateConstants.SDF_DD_MMM_YYYY.format( crcdExpiryDate );
        }*/
    }

    public String getBankName()
    {
        return this.bankName;
    }

    public void setBankName( String bankName )
    {
        this.bankName = bankName;
    }

    public String getBankBranch()
    {
        return this.bankBranch;
    }

    public void setBankBranch( String bankBranch )
    {
        this.bankBranch = bankBranch;
    }

    public long getBankAccountNo()
    {
        return bankAccountNo;
    }

    public void setBankAccountNo( long inBankAccountNo )
    {
        bankAccountNo = inBankAccountNo;
    }

    public String getBankAccountType()
    {
        return this.bankAccountType;
    }

    public void setBankAccountType( String bankAccountType )
    {
        this.bankAccountType = bankAccountType;
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

    public String getDisplayCrcdExpiryDate()
    {
        return displayCrcdExpiryDate;
    }

    public void setDisplayCrcdExpiryDate( String displayCrcdExpiryDate )
    {
        this.displayCrcdExpiryDate = displayCrcdExpiryDate;
    }

    public String getVehicleMake()
    {
        return vehicleMake;
    }

    public void setVehicleMake( String inVehicleMake )
    {
        vehicleMake = inVehicleMake;
    }
}