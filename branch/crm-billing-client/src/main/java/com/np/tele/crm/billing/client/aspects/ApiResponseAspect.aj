package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ApiResponse;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect ApiResponseAspect
{
    public String ApiResponse.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ApiResponse [extTransactionId=" )
                .append( BillingUtils.convertToJavaObject( this.getExtTransactionId() ) ).append( ", returnCode=" )
                .append( this.getReturnCode() ).append( ", returnMessage=" )
                .append( BillingUtils.convertToJavaObject( this.getReturnMessage() ) ).append( ", transactionId=" )
                .append( this.getTransactionId() ).append( "]" );
        return builder.toString();
    }
}
