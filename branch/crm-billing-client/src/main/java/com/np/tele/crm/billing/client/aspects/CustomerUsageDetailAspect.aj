package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerUsageDetail;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect CustomerUsageDetailAspect
{
    public String CustomerUsageDetail.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerUsageDetail [balance=" ).append( this.getBalance() ).append( ", planCode=" )
                .append( BillingUtils.convertToJavaObject( this.getPlanCode() ) ).append( ", addOnCode=" )
                .append( BillingUtils.convertToJavaObject( this.getAddOnCode() ) ).append( ", currentBandwidth=" )
                .append( this.getCurrentBandwidth() ).append( ", usageDetails=" )
                .append( BillingUtils.toObjectString( BillingUtils.convertToJavaObject( this.getUsageDetails() ) ) )
                .append( ", createdById=" ).append( this.getCreatedById() ).append( ", status=" )
                .append( BillingUtils.convertToJavaObject( this.getStatus() ) ).append( ", userName=" )
                .append( BillingUtils.convertToJavaObject( this.getUserName() ) ).append( ", billCycleName=" )
                .append( BillingUtils.convertToJavaObject( this.getBillCycleName() ) ).append( " " + super.toString() )
                .append( "]" );
        return builder.toString();
    }
}
