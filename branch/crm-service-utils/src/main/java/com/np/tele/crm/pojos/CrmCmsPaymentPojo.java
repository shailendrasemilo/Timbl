package com.np.tele.crm.pojos;

// Generated Dec 2, 2013 5:15:16 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmCmsPaymentPojo generated by hbm2java
 */
public class CrmCmsPaymentPojo
    implements java.io.Serializable
{
    private long    cmsId;
    private long    depositFileId;
    private long    clearanceFileId;
    private String  internalDepNo;
    private String  depositSlipNo;
    private String  depositRemarks;
    private Date    depositDate;
    private Integer totalInstruments;
    private Date    pickupDate;
    private String  instrumentNo;
    private Date    instrumentDate;
    private Double  instrumentAmount;
    private Double  totalAmount;
    private String  drawerName;
    private String  draweeBank;
    private String  draweeBranch;
    private String  ie1;
    private String  ie2;
    private String  bandId;
    private String  entryId;
    private String  typeOfEntry;
    private String  debitCreditFlag;
    private Double  entryAmount;
    private Date    valueDate;
    private Date    postingDate;
    private Double  depositAmount;
    private String  returnReason;
    private String  faliureReason;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  status;

    public CrmCmsPaymentPojo()
    {
    }

    public CrmCmsPaymentPojo( long cmsId, Long depositFileId, String createdBy )
    {
        this.cmsId = cmsId;
        this.depositFileId = depositFileId;
        this.createdBy = createdBy;
    }

    public CrmCmsPaymentPojo( long cmsId,
                              Long depositFileId,
                              String internalDepNo,
                              String depositSlipNo,
                              String depositRemarks,
                              Date depositDate,
                              Integer totalInstruments,
                              Date pickupDate,
                              String instrumentNo,
                              Date instrumentDate,
                              Double instrumentAmount,
                              String drawerName,
                              String draweeBank,
                              String draweeBranch,
                              String ie1,
                              String ie2,
                              String bandId,
                              String entryId,
                              String typeOfEntry,
                              String debitCreditFlag,
                              Double entryAmount,
                              Date valueDate,
                              Date postingDate,
                              Double depositAmount,
                              String returnReason,
                              String faliureReason,
                              String createdBy,
                              Date createdTime,
                              String lastModifiedBy,
                              Date lastModifiedTime,
                              String status )
    {
        this.cmsId = cmsId;
        this.depositFileId = depositFileId;
        this.internalDepNo = internalDepNo;
        this.depositSlipNo = depositSlipNo;
        this.depositRemarks = depositRemarks;
        this.depositDate = depositDate;
        this.totalInstruments = totalInstruments;
        this.pickupDate = pickupDate;
        this.instrumentNo = instrumentNo;
        this.instrumentDate = instrumentDate;
        this.instrumentAmount = instrumentAmount;
        this.drawerName = drawerName;
        this.draweeBank = draweeBank;
        this.draweeBranch = draweeBranch;
        this.ie1 = ie1;
        this.ie2 = ie2;
        this.bandId = bandId;
        this.entryId = entryId;
        this.typeOfEntry = typeOfEntry;
        this.debitCreditFlag = debitCreditFlag;
        this.entryAmount = entryAmount;
        this.valueDate = valueDate;
        this.postingDate = postingDate;
        this.depositAmount = depositAmount;
        this.returnReason = returnReason;
        this.faliureReason = faliureReason;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    public long getCmsId()
    {
        return this.cmsId;
    }

    public void setCmsId( long cmsId )
    {
        this.cmsId = cmsId;
    }

    public String getInternalDepNo()
    {
        return this.internalDepNo;
    }

    public void setInternalDepNo( String internalDepNo )
    {
        this.internalDepNo = internalDepNo;
    }

    public String getDepositSlipNo()
    {
        return this.depositSlipNo;
    }

    public void setDepositSlipNo( String depositSlipNo )
    {
        this.depositSlipNo = depositSlipNo;
    }

    public String getDepositRemarks()
    {
        return this.depositRemarks;
    }

    public void setDepositRemarks( String depositRemarks )
    {
        this.depositRemarks = depositRemarks;
    }

    public Date getDepositDate()
    {
        return this.depositDate;
    }

    public void setDepositDate( Date depositDate )
    {
        this.depositDate = depositDate;
    }

    public Date getPickupDate()
    {
        return this.pickupDate;
    }

    public void setPickupDate( Date pickupDate )
    {
        this.pickupDate = pickupDate;
    }

    public String getInstrumentNo()
    {
        return this.instrumentNo;
    }

    public void setInstrumentNo( String instrumentNo )
    {
        this.instrumentNo = instrumentNo;
    }

    public Date getInstrumentDate()
    {
        return this.instrumentDate;
    }

    public void setInstrumentDate( Date instrumentDate )
    {
        this.instrumentDate = instrumentDate;
    }

    public String getDrawerName()
    {
        return this.drawerName;
    }

    public void setDrawerName( String drawerName )
    {
        this.drawerName = drawerName;
    }

    public String getDraweeBank()
    {
        return this.draweeBank;
    }

    public void setDraweeBank( String draweeBank )
    {
        this.draweeBank = draweeBank;
    }

    public String getDraweeBranch()
    {
        return this.draweeBranch;
    }

    public void setDraweeBranch( String draweeBranch )
    {
        this.draweeBranch = draweeBranch;
    }

    public String getIe1()
    {
        return this.ie1;
    }

    public void setIe1( String ie1 )
    {
        this.ie1 = ie1;
    }

    public String getIe2()
    {
        return this.ie2;
    }

    public void setIe2( String ie2 )
    {
        this.ie2 = ie2;
    }

    public String getBandId()
    {
        return this.bandId;
    }

    public void setBandId( String bandId )
    {
        this.bandId = bandId;
    }

    public String getEntryId()
    {
        return this.entryId;
    }

    public void setEntryId( String entryId )
    {
        this.entryId = entryId;
    }

    public String getTypeOfEntry()
    {
        return this.typeOfEntry;
    }

    public void setTypeOfEntry( String typeOfEntry )
    {
        this.typeOfEntry = typeOfEntry;
    }

    public String getDebitCreditFlag()
    {
        return this.debitCreditFlag;
    }

    public void setDebitCreditFlag( String debitCreditFlag )
    {
        this.debitCreditFlag = debitCreditFlag;
    }

    public Date getValueDate()
    {
        return this.valueDate;
    }

    public void setValueDate( Date valueDate )
    {
        this.valueDate = valueDate;
    }

    public Date getPostingDate()
    {
        return this.postingDate;
    }

    public void setPostingDate( Date postingDate )
    {
        this.postingDate = postingDate;
    }

    public String getReturnReason()
    {
        return this.returnReason;
    }

    public void setReturnReason( String returnReason )
    {
        this.returnReason = returnReason;
    }

    public String getFaliureReason()
    {
        return this.faliureReason;
    }

    public void setFaliureReason( String faliureReason )
    {
        this.faliureReason = faliureReason;
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

    public long getDepositFileId()
    {
        return depositFileId;
    }

    public void setDepositFileId( long depositFileId )
    {
        this.depositFileId = depositFileId;
    }

    public long getClearanceFileId()
    {
        return clearanceFileId;
    }

    public void setClearanceFileId( long clearanceFileId )
    {
        this.clearanceFileId = clearanceFileId;
    }

    public Integer getTotalInstruments()
    {
        return totalInstruments;
    }

    public void setTotalInstruments( Integer totalInstruments )
    {
        this.totalInstruments = totalInstruments;
    }

    public Double getInstrumentAmount()
    {
        return instrumentAmount;
    }

    public void setInstrumentAmount( Double instrumentAmount )
    {
        this.instrumentAmount = instrumentAmount;
    }

    public Double getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount( Double totalAmount )
    {
        this.totalAmount = totalAmount;
    }

    public Double getEntryAmount()
    {
        return entryAmount;
    }

    public void setEntryAmount( Double entryAmount )
    {
        this.entryAmount = entryAmount;
    }

    public Double getDepositAmount()
    {
        return depositAmount;
    }

    public void setDepositAmount( Double depositAmount )
    {
        this.depositAmount = depositAmount;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( draweeBank == null ) ? 0 : draweeBank.hashCode() );
        result = prime * result + ( ( ie2 == null ) ? 0 : ie2.hashCode() );
        result = prime * result + ( ( instrumentAmount == null ) ? 0 : instrumentAmount.hashCode() );
        result = prime * result + ( ( instrumentNo == null ) ? 0 : instrumentNo.hashCode() );
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCmsPaymentPojo other = (CrmCmsPaymentPojo) obj;
        if ( draweeBank == null )
        {
            if ( other.draweeBank != null )
                return false;
        }
        else if ( !draweeBank.equals( other.draweeBank ) )
            return false;
        if ( ie2 == null )
        {
            if ( other.ie2 != null )
                return false;
        }
        else if ( !ie2.equals( other.ie2 ) )
            return false;
        if ( instrumentAmount == null )
        {
            if ( other.instrumentAmount != null )
                return false;
        }
        else if ( instrumentAmount.compareTo( other.instrumentAmount ) != 0 )
            return false;
        if ( instrumentNo == null )
        {
            if ( other.instrumentNo != null )
                return false;
        }
        else if ( !instrumentNo.equals( other.instrumentNo ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCmsPaymentPojo [cmsId=" ).append( cmsId ).append( ", depositFileId=" )
                .append( depositFileId ).append( ", clearanceFileId=" ).append( clearanceFileId )
                .append( ", instrumentNo=" ).append( instrumentNo ).append( ", instrumentDate=" )
                .append( instrumentDate ).append( ", instrumentAmount=" ).append( instrumentAmount )
                .append( ", totalAmount=" ).append( totalAmount ).append( ", draweeBank=" ).append( draweeBank )
                .append( ", ie2=" ).append( ie2 ).append( ", debitCreditFlag=" ).append( debitCreditFlag )
                .append( ", returnReason=" ).append( returnReason ).append( ", faliureReason=" ).append( faliureReason )
                .append( ", status=" ).append( status ).append( "]" );
        return builder.toString();
    }
}
