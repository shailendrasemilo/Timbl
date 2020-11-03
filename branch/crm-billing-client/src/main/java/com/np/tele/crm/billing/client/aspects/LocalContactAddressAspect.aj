package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.LocalContactAddress;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect LocalContactAddressAspect
{
    public String LocalContactAddress.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "LocalContactAddress [addressline1=" )
                .append( BillingUtils.convertToJavaObject( this.getAddressline1() ) ).append( ", addressline2=" )
                .append( BillingUtils.convertToJavaObject( this.getAddressline2() ) ).append( ", city=" )
                .append( BillingUtils.convertToJavaObject( this.getCity() ) ).append( ", nation=" )
                .append( BillingUtils.convertToJavaObject( this.getNation() ) ).append( ", state=" )
                .append( BillingUtils.convertToJavaObject( this.getState() ) ).append( ", zipcode=" )
                .append( BillingUtils.convertToJavaObject( this.getZipcode() ) ).append( "]" );
        return builder.toString();
    }
}
