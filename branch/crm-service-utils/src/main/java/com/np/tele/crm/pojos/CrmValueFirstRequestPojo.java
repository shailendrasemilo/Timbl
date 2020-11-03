package com.np.tele.crm.pojos;

// default package
// Generated Jul 20, 2016 3:50:33 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmValueFirstRequest generated by hbm2java
 */
public class CrmValueFirstRequestPojo
    implements java.io.Serializable
{
    private Long   recordId;
    private String reqTo;
    private String reqMessage;
    private String reqOprator;
    private String reqFrom;
    private Date   reqDate;
    private String createdBy;
    private Date   createdTime;
    private String status;

    public CrmValueFirstRequestPojo()
    {
    }

    public CrmValueFirstRequestPojo( String reqTo,
                                     String reqMessage,
                                     String reqOprator,
                                     String reqFrom,
                                     Date reqDate,
                                     String createdBy,
                                     Date createdTime )
    {
        this.reqTo = reqTo;
        this.reqMessage = reqMessage;
        this.reqOprator = reqOprator;
        this.reqFrom = reqFrom;
        this.reqDate = reqDate;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public CrmValueFirstRequestPojo( String reqTo,
                                     String reqMessage,
                                     String reqOprator,
                                     String reqFrom,
                                     Date reqDate,
                                     String createdBy,
                                     Date createdTime,
                                     String status )
    {
        this.reqTo = reqTo;
        this.reqMessage = reqMessage;
        this.reqOprator = reqOprator;
        this.reqFrom = reqFrom;
        this.reqDate = reqDate;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.status = status;
    }

    public Long getRecordId()
    {
        return this.recordId;
    }

    public void setRecordId( Long recordId )
    {
        this.recordId = recordId;
    }

    public String getReqTo()
    {
        return this.reqTo;
    }

    public void setReqTo( String reqTo )
    {
        this.reqTo = reqTo;
    }

    public String getReqMessage()
    {
        return this.reqMessage;
    }

    public void setReqMessage( String reqMessage )
    {
        this.reqMessage = reqMessage;
    }

    public String getReqOprator()
    {
        return this.reqOprator;
    }

    public void setReqOprator( String reqOprator )
    {
        this.reqOprator = reqOprator;
    }

    public String getReqFrom()
    {
        return this.reqFrom;
    }

    public void setReqFrom( String reqFrom )
    {
        this.reqFrom = reqFrom;
    }

    public Date getReqDate()
    {
        return this.reqDate;
    }

    public void setReqDate( Date reqDate )
    {
        this.reqDate = reqDate;
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

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }
}
