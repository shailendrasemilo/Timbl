package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerPostPaymentResult;

public aspect CustomerPostPaymentResultAspect
{
    public String CustomerPostPaymentResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerPostPaymentResult [balance=" ).append( this.getBalance() ).append( ", toString()=" )
                .append( super.toString() ).append( "]" );
        return builder.toString();
    }
}
