package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.PrBillingDetailsResult;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect PrBillingDetailsResultAspect
{
    public String PrBillingDetailsResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "PrBillingDetailsResult [amountPaid=" )
                .append( BillingUtils.convertToJavaObject( this.getAmountPaid() ) ).append( ", billAmount=" )
                .append( BillingUtils.convertToJavaObject( this.getBillAmount() ) ).append( ", billDate=" )
                .append( BillingUtils.convertToJavaObject( this.getBillDate() ) ).append( ", billNumber=" )
                .append( BillingUtils.convertToJavaObject( this.getBillNumber() ) ).append( ", billPeriod=" )
                .append( BillingUtils.convertToJavaObject( this.getBillPeriod() ) ).append( ", billStatus=" )
                .append( BillingUtils.convertToJavaObject( this.getBillStatus() ) ).append( ", customerNumber=" )
                .append( BillingUtils.convertToJavaObject( this.getCustomerNumber() ) ).append( ", paymentDueDate=" )
                .append( BillingUtils.convertToJavaObject( this.getPaymentDueDate() ) ).append( "]" );
        return builder.toString();
    }
}
