package com.np.tele.crm.pojos;

// default package
// Generated Mar 13, 2015 1:08:54 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmCustWaiverPojo generated by hbm2java
 */
public class CrmCustWaiverPojo
    implements java.io.Serializable
{
    private long   waiverId;
    private String customerId;
    private String billNo;
    private String qrcCategory;
    private String waiverType;
    private String waiverHead;
    private double waiverAmount;
    private String srTicketId;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;
    private String workflowId;
    private String workflowStage;
    private String approvedBy;
    private String finalApprovedBy;
    private String rejectedBy;
    private String rejectionReason;
    private String previousStage;
    private Date   waiverPostedDate;
    private String billCycle;
    private String crfNo;
    private String remarks;

    public CrmCustWaiverPojo()
    {
    }

    public CrmCustWaiverPojo( Long waiverId,
                              String customerId,
                              String billNo,
                              String waiverType,
                              String waiverHead,
                              double waiverAmount,
                              String srTicketId,
                              String createdBy,
                              String status,
                              String workflowStage )
    {
        this.waiverId = waiverId;
        this.customerId = customerId;
        this.billNo = billNo;
        this.waiverType = waiverType;
        this.waiverHead = waiverHead;
        this.waiverAmount = waiverAmount;
        this.srTicketId = srTicketId;
        this.createdBy = createdBy;
        this.status = status;
        this.workflowStage = workflowStage;
    }

    public CrmCustWaiverPojo( Long waiverId,
                              String customerId,
                              String billNo,
                              String waiverType,
                              String waiverHead,
                              double waiverAmount,
                              String srTicketId,
                              String createdBy,
                              Date createdTime,
                              String lastModifiedBy,
                              Date lastModifiedTime,
                              String status,
                              String workflowId,
                              String workflowStage,
                              String approvedBy,
                              String finalApprovedBy,
                              String rejectedBy,
                              String rejectionReason )
    {
        this.waiverId = waiverId;
        this.customerId = customerId;
        this.billNo = billNo;
        this.waiverType = waiverType;
        this.waiverHead = waiverHead;
        this.waiverAmount = waiverAmount;
        this.srTicketId = srTicketId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        this.workflowId = workflowId;
        this.workflowStage = workflowStage;
        this.approvedBy = approvedBy;
        this.finalApprovedBy = finalApprovedBy;
        this.rejectedBy = rejectedBy;
        this.rejectionReason = rejectionReason;
    }

    public Date getWaiverPostedDate()
    {
        return waiverPostedDate;
    }

    public void setWaiverPostedDate( Date inWaiverPostedDate )
    {
        waiverPostedDate = inWaiverPostedDate;
    }

    public String getCustomerId()
    {
        return this.customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getBillNo()
    {
        return this.billNo;
    }

    public void setBillNo( String billNo )
    {
        this.billNo = billNo;
    }

    public String getQrcCategory()
    {
        return qrcCategory;
    }

    public void setQrcCategory( String inQrcCategory )
    {
        qrcCategory = inQrcCategory;
    }

    public String getWaiverType()
    {
        return this.waiverType;
    }

    public void setWaiverType( String waiverType )
    {
        this.waiverType = waiverType;
    }

    public String getWaiverHead()
    {
        return this.waiverHead;
    }

    public void setWaiverHead( String waiverHead )
    {
        this.waiverHead = waiverHead;
    }

    public long getWaiverId()
    {
        return waiverId;
    }

    public void setWaiverId( long waiverId )
    {
        this.waiverId = waiverId;
    }

    public double getWaiverAmount()
    {
        return waiverAmount;
    }

    public void setWaiverAmount( double waiverAmount )
    {
        this.waiverAmount = waiverAmount;
    }

    public String getSrTicketId()
    {
        return this.srTicketId;
    }

    public void setSrTicketId( String srTicketId )
    {
        this.srTicketId = srTicketId;
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

    public String getWorkflowId()
    {
        return this.workflowId;
    }

    public void setWorkflowId( String workflowId )
    {
        this.workflowId = workflowId;
    }

    public String getWorkflowStage()
    {
        return this.workflowStage;
    }

    public void setWorkflowStage( String workflowStage )
    {
        this.workflowStage = workflowStage;
    }

    public String getApprovedBy()
    {
        return this.approvedBy;
    }

    public void setApprovedBy( String approvedBy )
    {
        this.approvedBy = approvedBy;
    }

    public String getFinalApprovedBy()
    {
        return this.finalApprovedBy;
    }

    public void setFinalApprovedBy( String finalApprovedBy )
    {
        this.finalApprovedBy = finalApprovedBy;
    }

    public String getRejectedBy()
    {
        return this.rejectedBy;
    }

    public void setRejectedBy( String rejectedBy )
    {
        this.rejectedBy = rejectedBy;
    }

    public String getRejectionReason()
    {
        return this.rejectionReason;
    }

    public void setRejectionReason( String rejectionReason )
    {
        this.rejectionReason = rejectionReason;
    }

    public String getPreviousStage()
    {
        return previousStage;
    }

    public void setPreviousStage( String previousStage )
    {
        this.previousStage = previousStage;
    }

    public String getBillCycle()
    {
        return billCycle;
    }

    public void setBillCycle( String inBillCycle )
    {
        billCycle = inBillCycle;
    }

    public String getCrfNo()
    {
        return crfNo;
    }

    public void setCrfNo( String inCrfNo )
    {
        crfNo = inCrfNo;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String inRemarks )
    {
        remarks = inRemarks;
    }
}
