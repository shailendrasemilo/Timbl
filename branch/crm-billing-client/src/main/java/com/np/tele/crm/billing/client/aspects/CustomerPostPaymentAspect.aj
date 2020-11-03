package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerPostPayment;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect CustomerPostPaymentAspect
{
    public String CustomerPostPayment.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerPostPayment [bank=" ).append( BillingUtils.convertToJavaObject( this.getBank() ) )
                .append( ", bankTransID=" ).append( BillingUtils.convertToJavaObject( this.getBankTransID() ) )
                .append( ", city=" ).append( BillingUtils.convertToJavaObject( this.getCity() ) )
                .append( ", pgwTransId=" ).append( BillingUtils.convertToJavaObject( this.getPgwTransId() ) )
                .append( ", refTransid=" ).append( BillingUtils.convertToJavaObject( this.getRefTransid() ) )
                .append( ", amount=" ).append( this.getAmount() ).append( ", branch=" )
                .append( BillingUtils.convertToJavaObject( this.getBranch() ) ).append( ", chqDate=" )
                .append( this.getChqDate() ).append( ", chqNo=" )
                .append( BillingUtils.convertToJavaObject( this.getChqNo() ) ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( ", partnerCode=" )
                .append( BillingUtils.convertToJavaObject( this.getPartnerCode() ) ).append( ", payDate=" )
                .append( this.getPayDate() ).append( ", paymethod=" )
                .append( BillingUtils.convertToJavaObject( this.getPaymethod() ) ).append( ", isTelesolutionPayment=" )
                .append( this.isIsTelesolutionPayment() ).append( "]" );
        return builder.toString();
    }
}
