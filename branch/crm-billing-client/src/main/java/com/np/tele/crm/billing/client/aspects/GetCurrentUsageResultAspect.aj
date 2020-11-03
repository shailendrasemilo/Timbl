package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.GetCurrentUsageResult;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect GetCurrentUsageResultAspect
{
    public String GetCurrentUsageResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "GetCurrentUsageResult [activationDate=" ).append( this.getActivationDate() )
                .append( ", allocatedTimeQuota=" )
                .append( BillingUtils.convertToJavaObject( this.getAllocatedTimeQuota() ) )
                .append( ", allocatedVolumeQuota=" )
                .append( BillingUtils.convertToJavaObject( this.getAllocatedTimeQuota() ) )
                .append( ", expiryDate=" )
                .append( BillingUtils.convertToJavaObject( this.getExpiryDate() ) ).append( ", bucket=" )
                .append( BillingUtils.convertToJavaObject( this.getBucket() ) ).append( ", remainTimeQuota=" )
                .append( BillingUtils.convertToJavaObject( this.getRemainTimeQuota() ) )
                .append( ", remainVolumeQuota=" )
                .append( BillingUtils.convertToJavaObject( this.getRemainVolumeQuota() ) ).append( ", usedTimeQuota=" )
                .append( BillingUtils.convertToJavaObject( this.getUsedTimeQuota() ) ).append( ", usedVolumeQuota=" )
                .append( BillingUtils.convertToJavaObject( this.getUsedVolumeQuota() ) ).append( "]" );
        return builder.toString();
    }
}
