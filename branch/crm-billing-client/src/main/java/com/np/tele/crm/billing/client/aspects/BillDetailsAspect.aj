package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.BillDetails;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect BillDetailsAspect
{
    public String BillDetails.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "BillDetails [billingDetails=" )
                .append( BillingUtils.toObjectString( BillingUtils.convertToJavaObject( this.getBillingDetails() ) ) )
                .append( "]" );
        return builder.toString();
    }
}
