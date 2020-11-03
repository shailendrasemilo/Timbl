package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ApiRequest;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect ApiRequestAspect
{
    public String ApiRequest.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ApiRequest [ password=" ).append( BillingUtils.convertToJavaObject( this.getPassword() ) )
                .append( ", requestDate=" ).append( this.getRequestDate() ).append( ", systemId=" )
                .append( BillingUtils.convertToJavaObject( this.getSystemId() ) ).append( ", extTransactionId=" )
                .append( BillingUtils.convertToJavaObject( this.getExtTransactionId() ) ).append( "]" );
        return builder.toString();
    }
}
