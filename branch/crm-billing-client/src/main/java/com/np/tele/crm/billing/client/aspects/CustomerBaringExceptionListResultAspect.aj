package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerBaringExceptionListResult;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect CustomerBaringExceptionListResultAspect
{
    public String CustomerBaringExceptionListResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerBaringExceptionListResult [barredInDate=" ).append( this.getBarredInDate() )
                .append( ", barredOutDate=" ).append( this.getBarredOutDate() ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( ", reason=" )
                .append( BillingUtils.convertToJavaObject( this.getReason() ) ).append( ", status=" )
                .append( BillingUtils.convertToJavaObject( this.getStatus() ) ).append( ", userName=" )
                .append( BillingUtils.convertToJavaObject( this.getUserName() ) ).append( super.toString() )
                .append( "]" );
        return builder.toString();
    }
}
