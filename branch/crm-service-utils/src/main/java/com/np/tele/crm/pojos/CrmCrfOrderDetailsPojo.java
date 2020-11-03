package com.np.tele.crm.pojos;

// default package
import java.util.Date;

import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.StringUtils;

/**
 * CrmCrfOrderDetailsPojo generated by hbm2java
 */
public class CrmCrfOrderDetailsPojo
    extends CrmOrderDetailsPojo
    implements java.io.Serializable
{
    private long    recordId;
    private long    customerRecordId;
    private String  services;
    private long    wiringPoints;
    private String  cpeStatus;
    private boolean receiverRequired;
    private long    receiverNo;
    private String  deviceDetail;
    private String  receiverDetail;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  displayCreatedTime;
    private String  displayLastModifiedTime;

    public CrmCrfOrderDetailsPojo()
    {
    }

    public CrmCrfOrderDetailsPojo( Long recordId, String createdBy )
    {
        this.recordId = recordId;
        this.createdBy = createdBy;
    }

    public CrmCrfOrderDetailsPojo( Long recordId,
                                   Long customerRecordId,
                                   String services,
                                   Long wiringPoints,
                                   String cpeStatus,
                                   boolean receiverRequired,
                                   Long receiverNo,
                                   String deviceDetail,
                                   String receiverDetail,
                                   String createdBy,
                                   Date createdTime,
                                   String lastModifiedBy,
                                   Date lastModifiedTime )
    {
        this.recordId = recordId;
        this.customerRecordId = customerRecordId;
        this.services = services;
        this.wiringPoints = wiringPoints;
        this.cpeStatus = cpeStatus;
        this.receiverRequired = receiverRequired;
        this.receiverNo = receiverNo;
        this.deviceDetail = deviceDetail;
        this.receiverDetail = receiverDetail;
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

    public long getWiringPoints()
    {
        return wiringPoints;
    }

    public void setWiringPoints( long wiringPoints )
    {
        this.wiringPoints = wiringPoints;
    }

    public long getReceiverNo()
    {
        return receiverNo;
    }

    public void setReceiverNo( long receiverNo )
    {
        this.receiverNo = receiverNo;
    }

    public String getServices()
    {
        return this.services;
    }

    public void setServices( String services )
    {
        this.services = services;
    }

    public String getCpeStatus()
    {
        return this.cpeStatus;
    }

    public void setCpeStatus( String cpeStatus )
    {
        this.cpeStatus = cpeStatus;
    }

    public boolean getReceiverRequired()
    {
        return this.receiverRequired;
    }

    public void setReceiverRequired( boolean receiverRequired )
    {
        this.receiverRequired = receiverRequired;
    }

    public String getDeviceDetail()
    {
        return this.deviceDetail;
    }

    public void setDeviceDetail( String deviceDetail )
    {
        this.deviceDetail = deviceDetail;
    }

    public String getReceiverDetail()
    {
        return this.receiverDetail;
    }

    public void setReceiverDetail( String receiverDetail )
    {
        this.receiverDetail = receiverDetail;
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
        /*if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
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
