package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ArrayOfprBillingDetailsResult;

public aspect ArrayOfprBillingDetailsResultAspect {

    public String ArrayOfprBillingDetailsResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ArrayOfprBillingDetailsResult [prBillingDetailsResult=" ).append( this.getPrBillingDetailsResult().toString() )
                .append( "]" );
        return builder.toString();
    }
}
