package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ClsCustomerBaringExceptionList;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect ClsCustomerBaringExceptionListAspect
{
    public String ClsCustomerBaringExceptionList.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ClsCustomerBaringExceptionList [action=" )
                .append( BillingUtils.convertToJavaObject( this.getAction() ) ).append( ", barredOutDate=" )
                .append( this.getBarredOutDate() ).append( ", customerNo=" ).append( this.getCustomerNo() )
                .append( ", reason=" ).append( BillingUtils.convertToJavaObject( this.getReason() ) ).append( "]" );
        return builder.toString();
    }
}
