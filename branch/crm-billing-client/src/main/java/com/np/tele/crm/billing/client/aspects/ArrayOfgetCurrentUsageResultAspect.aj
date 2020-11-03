package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ArrayOfgetCurrentUsageResult;

public aspect ArrayOfgetCurrentUsageResultAspect
{
    public String ArrayOfgetCurrentUsageResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ArrayOfgetCurrentUsageResult [getCurrentUsageResult=" )
                .append( this.getGetCurrentUsageResult().toString() ).append( "]" );
        return builder.toString();
    }
}
