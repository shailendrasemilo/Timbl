package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.InventoryItemToLCOResult;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect InventoryItemToLCOResultAspect
{
    public String InventoryItemToLCOResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "InventoryItemToLCOResult [items=" )
                .append( BillingUtils.toObjectString( BillingUtils.convertToJavaObject( this.getItems() ) ) )
                .append( super.toString() ).append( "]" );
        return builder.toString();
    }
}
