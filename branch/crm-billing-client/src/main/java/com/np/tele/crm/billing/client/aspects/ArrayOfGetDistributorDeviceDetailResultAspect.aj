package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ArrayOfGetDistributorDeviceDetailResult;

public aspect ArrayOfGetDistributorDeviceDetailResultAspect
{
    public String ArrayOfGetDistributorDeviceDetailResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ArrayOfGetDistributorDeviceDetailResult [getDistributorDeviceDetailResult=" )
                .append( this.getGetDistributorDeviceDetailResult().toString() ).append( "]" );
        return builder.toString();
    }
}
