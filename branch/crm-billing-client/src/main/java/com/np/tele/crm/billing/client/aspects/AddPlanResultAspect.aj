package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.AddPlanResult;

public aspect AddPlanResultAspect
{
    public String AddPlanResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AddPlanResult [custId=" ).append( this.getCustId() ).append( "]" );
        return builder.toString();
    }
}
