package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.OfferAllocation;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect OfferAllocationAspect
{
    public String OfferAllocation.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "OfferAllocation [activationDate=" ).append( this.getActivationDate() )
                .append( ", addonPlanCode=" ).append( BillingUtils.toJavaString( this.getAddonPlanCode() ) )
                .append( ", chargeName=" ).append( BillingUtils.toJavaString( this.getChargeName() ) )
                .append( ", customerNo=" ).append( this.getCustomerNo() ).append( ", itemCode=" )
                .append( BillingUtils.toJavaString( this.getItemCode() ) ).append( ", offerName=" )
                .append( BillingUtils.convertToJavaObject( this.getOfferName() ) ).append( ", primaryMacId=" )
                .append( BillingUtils.convertToJavaObject( this.getPrimaryMacId() ) ).append( ", secondaryMacId=" )
                .append( BillingUtils.convertToJavaObject( this.getSecondaryMacId() ) )
                .append( ", speedBoosterPlanCode=" )
                .append( BillingUtils.toJavaString( this.getSpeedBoosterPlanCode() ) )
                .append( ", usageBoosterPlanCode=" )
                .append( BillingUtils.toJavaString( this.getUsageBoosterPlanCode() ) ).append( "]" );
        return builder.toString();
    }
}
