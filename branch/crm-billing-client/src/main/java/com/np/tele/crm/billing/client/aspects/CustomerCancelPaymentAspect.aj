package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerCancelPayment;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect CustomerCancelPaymentAspect
{
    public String CustomerCancelPayment.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerCancelPayment [cancelDate=" ).append( this.getCancelDate() ).append( ", refTransID=" )
                .append( BillingUtils.convertToJavaObject( this.getRefTransID() ) ).append( ", remark=" )
                .append( BillingUtils.convertToJavaObject( this.getRemark() ) ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( "]" );
        return builder.toString();
    }
}
