package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ChangeAddress;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect ChangeAddressAspect
{
    public String ChangeAddress.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ChangeAddress [city=" ).append( BillingUtils.convertToJavaObject( this.getCity() ) )
                .append( ", nation=" ).append( BillingUtils.convertToJavaObject( this.getNation() ) )
                .append( ", state=" ).append( BillingUtils.convertToJavaObject( this.getState() ) )
                .append( ", zipCode=" ).append( BillingUtils.convertToJavaObject( this.getZipCode() ) )
                .append( ", addressLine1=" ).append( BillingUtils.convertToJavaObject( this.getAddressLine1() ) )
                .append( ", addressLine2=" ).append( BillingUtils.convertToJavaObject( this.getAddressLine2() ) )
                .append( ", addressType=" )
                .append( this.getAddressType() != null ? this.getAddressType().value() : null )
                .append( ", customerNo=" ).append( this.getCustomerNo() ).append( "]" );
        return builder.toString();
    }
}
