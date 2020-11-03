package com.np.tele.crm.pojos;

// default package
// Generated Oct 13, 2014 1:09:38 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmCustInteractionsPojo generated by hbm2java
 */
public class CrmCustInteractionsPojo
    implements java.io.Serializable
{
    private long   interactionId;
    private String customerId;
    private String interactionCategory;
    private String interactionSubCategory;
    private String remarks;
    private String createdBy;
    private Date   createdTime;
    private String displayCreatedTime;
    public CrmCustInteractionsPojo()
    {
    }

    public CrmCustInteractionsPojo( long interactionId,
                                    String customerId,
                                    String interactionCategory,
                                    String interactionSubCategory,
                                    String remarks,
                                    String createdBy )
    {
        this.interactionId = interactionId;
        this.customerId = customerId;
        this.interactionCategory = interactionCategory;
        this.interactionSubCategory = interactionSubCategory;
        this.remarks = remarks;
        this.createdBy = createdBy;
    }

    public CrmCustInteractionsPojo( long interactionId,
                                    String customerId,
                                    String interactionCategory,
                                    String interactionSubCategory,
                                    String remarks,
                                    String createdBy,
                                    Date createdTime )
    {
        this.interactionId = interactionId;
        this.customerId = customerId;
        this.interactionCategory = interactionCategory;
        this.interactionSubCategory = interactionSubCategory;
        this.remarks = remarks;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public long getInteractionId()
    {
        return this.interactionId;
    }

    public void setInteractionId( long interactionId )
    {
        this.interactionId = interactionId;
    }

    public String getCustomerId()
    {
        return this.customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getInteractionCategory()
    {
        return this.interactionCategory;
    }

    public void setInteractionCategory( String interactionCategory )
    {
        this.interactionCategory = interactionCategory;
    }

    public String getInteractionSubCategory()
    {
        return this.interactionSubCategory;
    }

    public void setInteractionSubCategory( String interactionSubCategory )
    {
        this.interactionSubCategory = interactionSubCategory;
    }

    public String getRemarks()
    {
        return this.remarks;
    }

    public void setRemarks( String remarks )
    {
        this.remarks = remarks;
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
        /*if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
        {
            displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( createdTime );
        }*/
    }

   
    
}
