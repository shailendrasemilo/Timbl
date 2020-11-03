package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.AddPlan;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect AddPlanAspect
{
    public String AddPlan.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AddPlan [isImmediate=" ).append( this.isIsImmediate() ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( ", planCode=" )
                .append( BillingUtils.convertToJavaObject( this.getPlanCode() ) ).append( "]" );
        return builder.toString();
    }
}
