package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.PaymentAdjustment;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect PaymentAdjustmentAspect
{
    public String PaymentAdjustment.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "PaymentAdjustment [adjustDate=" ).append( this.getAdjustDate() ).append( ", adjustType=" )
                .append( BillingUtils.convertToJavaObject( this.getAdjustType() ) ).append( ", adjustmentHead=" )
                .append( BillingUtils.convertToJavaObject( this.getAdjustmentHead() ) ).append( ", amount=" )
                .append( this.getAmount() ).append( ", billNo=" )
                .append( BillingUtils.convertToJavaObject( this.getBillNo() ) ).append( ", remark=" )
                .append( BillingUtils.convertToJavaObject( this.getRemark() ) ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( "]" );
        return builder.toString();
    }
}
