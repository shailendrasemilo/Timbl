package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ClsTerminationRefund;

public aspect ClsTerminationRefundAspect {

    public String ClsTerminationRefund.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ClsTerminationRefund [amount=" ).append( this.getAmount() ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( "]" );
        return builder.toString();
    }
}
