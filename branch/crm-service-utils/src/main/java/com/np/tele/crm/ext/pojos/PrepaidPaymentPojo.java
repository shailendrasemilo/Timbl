package com.np.tele.crm.ext.pojos;

import java.math.BigDecimal;
import java.util.Date;

public class PrepaidPaymentPojo
{
    private String     receiptNo;
    private Date       paymentDate;
    private BigDecimal amount;

    public String getReceiptNo()
    {
        return receiptNo;
    }

    public void setReceiptNo( String inReceiptNo )
    {
        receiptNo = inReceiptNo;
    }

    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate( Date inPaymentDate )
    {
        paymentDate = inPaymentDate;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount( BigDecimal inAmount )
    {
        amount = inAmount;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "PrepaidPaymentPojo [receiptNo=" ).append( receiptNo ).append( ", paymentDate=" )
                .append( paymentDate ).append( ", amount=" ).append( amount ).append( "]" );
        return builder.toString();
    }
}
