package com.np.tele.crm.ext.pojos;

import java.util.Date;

public class CommonWorkflowPojo
{
    private String  workflowId;
    private String  workflowStage;
    private String  customerId;
    private String  requestType;
    private String  workflowType;
    private boolean unRead;
    private long    inboxId;
    private String  previousStage;
    private String  previousStageOwner;
    private Date    createdTime;
    private String  currentBin;

    public String getWorkflowId()
    {
        return workflowId;
    }

    public void setWorkflowId( String workflowId )
    {
        this.workflowId = workflowId;
    }

    public String getWorkflowStage()
    {
        return workflowStage;
    }

    public void setWorkflowStage( String workflowStage )
    {
        this.workflowStage = workflowStage;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType( String requestType )
    {
        this.requestType = requestType;
    }

    public String getWorkflowType()
    {
        return workflowType;
    }

    public void setWorkflowType( String workflowType )
    {
        this.workflowType = workflowType;
    }

    public boolean isUnRead()
    {
        return unRead;
    }

    public void setUnRead( boolean unRead )
    {
        this.unRead = unRead;
    }

    public long getInboxId()
    {
        return inboxId;
    }

    public void setInboxId( long inboxId )
    {
        this.inboxId = inboxId;
    }

    public String getPreviousStage()
    {
        return previousStage;
    }

    public void setPreviousStage( String previousStage )
    {
        this.previousStage = previousStage;
    }

    public String getPreviousStageOwner()
    {
        return previousStageOwner;
    }

    public void setPreviousStageOwner( String previousStageOwner )
    {
        this.previousStageOwner = previousStageOwner;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public String getCurrentBin()
    {
        return currentBin;
    }

    public void setCurrentBin( String inCurrentBin )
    {
        currentBin = inCurrentBin;
    }
    
}
