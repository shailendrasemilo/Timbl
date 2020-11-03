package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ClsAddCharge;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect ClsAddChargeAspect
{
    public String ClsAddCharge.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ClsAddCharge [chargeName=" ).append( BillingUtils.convertToJavaObject( this.getChargeName() ) )
                .append( ", customerNo=" ).append( this.getCustomerNo() ).append( "]" );
        return builder.toString();
    }
}
