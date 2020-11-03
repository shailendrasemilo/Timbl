package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CreateCustomerResult;

public aspect CreateCustomerResultAspect
{
    public String CreateCustomerResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CreateCustomerResult [customerNo=" ).append( this.getCustomerNo() ).append( super.toString() )
                .append( "]" );
        return builder.toString();
    }
}
