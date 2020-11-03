package com.np.tele.crm.pojos;

// default package
// Generated Mar 24, 2015 6:24:56 PM by Hibernate Tools 3.4.0.CR1
import java.io.Serializable;
import java.util.Date;

/**
 * CrmCustomerRefundsPojo generated by hbm2java
 */
public class CrmCustomerRefundsPojo
    implements Serializable
{
    private long   refundId;
    private String customerId;
    private String entityType;
    private String refundMode;
    private String chequeNumber;
    private Date   chequeDate;
    private String chequeStatus;
    private String payerBankName;
    private String transactionId;
    private String custAccountNumber;
    private String custBankName;
    private double refundAmount;
    private Date   refundDate;
    private long   podId;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;
    private String ifscCode;
    private String rejectionReason;
    private String ticketID;

    public CrmCustomerRefundsPojo()
    {
    }

    public CrmCustomerRefundsPojo( Long refundId,
                                   String customerId,
                                   String entityType,
                                   double refundAmount,
                                   String status )
    {
        this.refundId = refundId;
        this.customerId = customerId;
        this.entityType = entityType;
        this.refundAmount = refundAmount;
        this.status = status;
    }

    public CrmCustomerRefundsPojo( Long refundId,
                                   String customerId,
                                   String entityType,
                                   String refundMode,
                                   String chequeNumber,
                                   Date chequeDate,
                                   String chequeStatus,
                                   String payerBankName,
                                   String transactionId,
                                   String custAccountNumber,
                                   String custBankName,
                                   double refundAmount,
                                   Date refundDate,
                                   Long podId,
                                   String createdBy,
                                   Date createdTime,
                                   String lastModifiedBy,
                                   Date lastModifiedTime,
                                   String status )
    {
        this.refundId = refundId;
        this.customerId = customerId;
        this.entityType = entityType;
        this.refundMode = refundMode;
        this.chequeNumber = chequeNumber;
        this.chequeDate = chequeDate;
        this.chequeStatus = chequeStatus;
        this.payerBankName = payerBankName;
        this.transactionId = transactionId;
        this.custAccountNumber = custAccountNumber;
        this.custBankName = custBankName;
        this.refundAmount = refundAmount;
        this.refundDate = refundDate;
        this.podId = podId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    public String getCustomerId()
    {
        return this.customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getEntityType()
    {
        return this.entityType;
    }

    public void setEntityType( String entityType )
    {
        this.entityType = entityType;
    }

    public String getRefundMode()
    {
        return this.refundMode;
    }

    public void setRefundMode( String refundMode )
    {
        this.refundMode = refundMode;
    }

    public String getChequeNumber()
    {
        return this.chequeNumber;
    }

    public void setChequeNumber( String chequeNumber )
    {
        this.chequeNumber = chequeNumber;
    }

    public Date getChequeDate()
    {
        return this.chequeDate;
    }

    public void setChequeDate( Date chequeDate )
    {
        this.chequeDate = chequeDate;
    }

    public String getChequeStatus()
    {
        return this.chequeStatus;
    }

    public void setChequeStatus( String chequeStatus )
    {
        this.chequeStatus = chequeStatus;
    }

    public String getPayerBankName()
    {
        return this.payerBankName;
    }

    public void setPayerBankName( String payerBankName )
    {
        this.payerBankName = payerBankName;
    }

    public String getTransactionId()
    {
        return this.transactionId;
    }

    public void setTransactionId( String transactionId )
    {
        this.transactionId = transactionId;
    }

    public String getCustAccountNumber()
    {
        return this.custAccountNumber;
    }

    public void setCustAccountNumber( String custAccountNumber )
    {
        this.custAccountNumber = custAccountNumber;
    }

    public String getCustBankName()
    {
        return this.custBankName;
    }

    public void setCustBankName( String custBankName )
    {
        this.custBankName = custBankName;
    }

    public Date getRefundDate()
    {
        return this.refundDate;
    }

    public void setRefundDate( Date refundDate )
    {
        this.refundDate = refundDate;
    }

    public double getRefundAmount()
    {
        return refundAmount;
    }

    public void setRefundAmount( double refundAmount )
    {
        this.refundAmount = refundAmount;
    }

    public long getRefundId()
    {
        return refundId;
    }

    public void setRefundId( long refundId )
    {
        this.refundId = refundId;
    }

    public long getPodId()
    {
        return podId;
    }

    public void setPodId( long podId )
    {
        this.podId = podId;
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

    public String getIfscCode()
    {
        return ifscCode;
    }

    public void setIfscCode( String ifscCode )
    {
        this.ifscCode = ifscCode;
    }

    public String getRejectionReason()
    {
        return rejectionReason;
    }

    public void setRejectionReason( String rejectionReason )
    {
        this.rejectionReason = rejectionReason;
    }

    public String getTicketID()
    {
        return ticketID;
    }

    public void setTicketID( String ticketID )
    {
        this.ticketID = ticketID;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCustomerRefundsPojo [refundId=" ).append( refundId ).append( ", customerId=" )
                .append( customerId ).append( ", entityType=" ).append( entityType ).append( ", refundMode=" )
                .append( refundMode ).append( ", chequeNumber=" ).append( chequeNumber ).append( ", chequeDate=" )
                .append( chequeDate ).append( ", chequeStatus=" ).append( chequeStatus ).append( ", payerBankName=" )
                .append( payerBankName ).append( ", transactionId=" ).append( transactionId )
                .append( ", custAccountNumber=" ).append( custAccountNumber ).append( ", custBankName=" )
                .append( custBankName ).append( ", refundAmount=" ).append( refundAmount ).append( ", refundDate=" )
                .append( refundDate ).append( ", podId=" ).append( podId ).append( ", createdBy=" ).append( createdBy )
                .append( ", createdTime=" ).append( createdTime ).append( ", lastModifiedBy=" ).append( lastModifiedBy )
                .append( ", lastModifiedTime=" ).append( lastModifiedTime ).append( ", status=" ).append( status )
                .append( "]" );
        return builder.toString();
    }
}
