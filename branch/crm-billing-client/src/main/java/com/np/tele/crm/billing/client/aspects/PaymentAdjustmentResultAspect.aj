package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.PaymentAdjustmentResult;

public aspect PaymentAdjustmentResultAspect {

    public String PaymentAdjustmentResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "PaymentAdjustmentResult [currentBalance=" ).append( this.getCurrentBalance() ).append( "]" );
        return builder.toString();
    }
}
