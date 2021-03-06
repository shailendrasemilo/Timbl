package com.np.tele.crm.pojos;

// default package
// Generated Sep 9, 2014 6:05:03 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmQrcAttributedToPojo generated by hbm2java
 */
public class CrmQrcAttributedToPojo
    implements java.io.Serializable
{
    private long    attributedToId;
    //    private CrmQrcResolutionCodePojo crmQrcResolutionCodePojo;
    private long    resolutionCodeId;
    private String  attributedTo;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  status;
    private boolean editable;

    public CrmQrcAttributedToPojo()
    {
    }

    public CrmQrcAttributedToPojo( long inAttributedToId,
                                   String inAttributedTo,
                                   String inCreatedBy,
                                   Date inCreatedTime,
                                   String inLastModifiedBy,
                                   Date inLastModifiedTime,
                                   String inStatus )
    {
        super();
        attributedToId = inAttributedToId;
        attributedTo = inAttributedTo;
        createdBy = inCreatedBy;
        createdTime = inCreatedTime;
        lastModifiedBy = inLastModifiedBy;
        lastModifiedTime = inLastModifiedTime;
        status = inStatus;
    }

    //    public CrmQrcAttributedToPojo( long attributedToId,
    //                                   CrmQrcResolutionCodePojo crmQrcResolutionCodePojo,
    //                                   String attributedTo,
    //                                   String createdBy )
    //    {
    //        this.attributedToId = attributedToId;
    //        this.crmQrcResolutionCodePojo = crmQrcResolutionCodePojo;
    //        this.attributedTo = attributedTo;
    //        this.createdBy = createdBy;
    //    }
    //    public CrmQrcAttributedToPojo( long attributedToId,
    //                                   CrmQrcResolutionCodePojo crmQrcResolutionCodePojo,
    //                                   String attributedTo,
    //                                   String createdBy,
    //                                   Date createdTime,
    //                                   String lastModifiedBy,
    //                                   Date lastModifiedTime,
    //                                   String status )
    //    {
    //        this.attributedToId = attributedToId;
    //        this.crmQrcResolutionCodePojo = crmQrcResolutionCodePojo;
    //        this.attributedTo = attributedTo;
    //        this.createdBy = createdBy;
    //        this.createdTime = createdTime;
    //        this.lastModifiedBy = lastModifiedBy;
    //        this.lastModifiedTime = lastModifiedTime;
    //        this.status = status;
    //    }
    public long getAttributedToId()
    {
        return this.attributedToId;
    }

    public void setAttributedToId( long attributedToId )
    {
        this.attributedToId = attributedToId;
    }

    //    public CrmQrcResolutionCodePojo getCrmQrcResolutionCode()
    //    {
    //        return this.crmQrcResolutionCodePojo;
    //    }
    //
    //    public void setCrmQrcResolutionCode( CrmQrcResolutionCodePojo crmQrcResolutionCodePojo )
    //    {
    //        this.crmQrcResolutionCodePojo = crmQrcResolutionCodePojo;
    //    }
    public String getAttributedTo()
    {
        return this.attributedTo;
    }

    public void setAttributedTo( String attributedTo )
    {
        this.attributedTo = attributedTo;
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

    public long getResolutionCodeId()
    {
        return resolutionCodeId;
    }

    public void setResolutionCodeId( long resolutionCodeId )
    {
        this.resolutionCodeId = resolutionCodeId;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable( boolean editable )
    {
        this.editable = editable;
    }
}
