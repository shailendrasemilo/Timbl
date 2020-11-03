package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerActivationResult;

public aspect CustomerActivationResultAspect
{
    public String CustomerActivationResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerActivationResult [customerNo=" ).append( this.getCustomerNo() ).append( ", status=" )
                .append( this.getStatus().getValue() ).append( super.toString() ).append( "]" );
        return builder.toString();
    }
}
