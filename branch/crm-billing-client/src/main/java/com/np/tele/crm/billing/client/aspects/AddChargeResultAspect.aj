package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.AddChargeResult;

public aspect AddChargeResultAspect
{
    public String AddChargeResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AddChargeResult [chargeAmount=" ).append( this.getChargeAmount() ).append( ", toString()=" )
                .append( super.toString() ).append( "]" );
        return builder.toString();
    }
}
