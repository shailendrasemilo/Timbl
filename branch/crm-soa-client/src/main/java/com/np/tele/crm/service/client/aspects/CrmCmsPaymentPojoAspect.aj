package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmCmsPaymentPojo;

public aspect CrmCmsPaymentPojoAspect
{
    public int CrmCmsPaymentPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getCmsId() ^ ( this.getCmsId() >>> 32 ) );
        return result;
    }

    public boolean CrmCmsPaymentPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCmsPaymentPojo other = (CrmCmsPaymentPojo) obj;
        if ( this.getCmsId() != other.getCmsId() )
            return false;
        return true;
    }

    public String CrmCmsPaymentPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCmsPaymentPojo [bandId=" ).append( getBandId() ).append( ", cmsId=" ).append( getCmsId() )
                .append( ", createdBy=" ).append( getCreatedBy() ).append( ", createdTime=" ).append( getCreatedTime() )
                .append( ", debitCreditFlag=" ).append( getDebitCreditFlag() ).append( ", depositAmount=" )
                .append( getDepositAmount() ).append( ", depositDate=" ).append( getDepositDate() )
                .append( ", depositRemarks=" ).append( getDepositRemarks() ).append( ", depositSlipNo=" )
                .append( getDepositSlipNo() ).append( ", draweeBank=" ).append( getDraweeBank() )
                .append( ", draweeBranch=" ).append( getDraweeBranch() ).append( ", drawerName=" )
                .append( getDrawerName() ).append( ", entryAmount=" ).append( getEntryAmount() ).append( ", entryId=" )
                .append( getEntryId() ).append( ", faliureReason=" ).append( getFaliureReason() ).append( ", ie1=" )
                .append( getIe1() ).append( ", ie2=" ).append( getIe2() ).append( ", instrumentAmount=" )
                .append( getInstrumentAmount() ).append( ", instrumentDate=" ).append( getInstrumentDate() )
                .append( ", instrumentNo=" ).append( getInstrumentNo() ).append( ", internalDepNo=" )
                .append( getInternalDepNo() ).append( ", lastModifiedBy=" ).append( getLastModifiedBy() )
                .append( ", lastModifiedTime=" ).append( getLastModifiedTime() ).append( ", pickupDate=" )
                .append( getPickupDate() ).append( ", postingDate=" ).append( getPostingDate() )
                .append( ", returnReason=" ).append( getReturnReason() ).append( ", status=" ).append( getStatus() )
                .append( ", totalAmount=" ).append( getTotalAmount() ).append( ", totalInstruments=" )
                .append( getTotalInstruments() ).append( ", typeOfEntry=" ).append( getTypeOfEntry() )
                .append( ", valueDate=" ).append( getValueDate() ).append( "]" );
        return builder.toString();
    }
}
