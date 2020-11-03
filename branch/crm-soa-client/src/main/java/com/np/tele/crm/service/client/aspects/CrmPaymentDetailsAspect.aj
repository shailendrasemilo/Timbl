package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;

public aspect CrmPaymentDetailsAspect
{
    public int CrmPaymentDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getPaymentId() ^ ( this.getPaymentId() >>> 32 ) );
        return result;
    }

    public boolean CrmPaymentDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmPaymentDetailsPojo other = (CrmPaymentDetailsPojo) obj;
        if ( this.getPaymentId() != other.getPaymentId() )
            return false;
        return true;
    }

    public String CrmPaymentDetailsPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmPaymentDetailsPojo [amount=" ).append( this.getAmount() ).append( ", bankName=" )
                .append( this.getBankName() ).append( ", caseStatus=" ).append( this.getCaseStatus() )
                .append( ", chequeDdNo=" ).append( this.getChequeDdNo() ).append( ", customerRecordId=" )
                .append( this.getCustomerRecordId() ).append( ", entityType=" ).append( this.getEntityType() )
                .append( ", installationStatus=" ).append( this.getInstallationStatus() ).append( ", paymentChannel=" )
                .append( this.getPaymentChannel() ).append( ", paymentId=" ).append( this.getPaymentId() )
                .append( ", paymentMode=" ).append( this.getPaymentMode() ).append( ", paymentStatus=" )
                .append( this.getPaymentStatus() ).append( ", realzationStatus=" ).append( this.getRealzationStatus() )
                .append( "]" );
        return builder.toString();
    }
}
