package com.np.tele.crm.dto;

import java.math.BigDecimal;

public class EasyBillPaymentDto
{
    private String     customerId;
    private BigDecimal amount;
    private String     transactionId;
    private String     status;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount( BigDecimal inAmount )
    {
        amount = inAmount;
    }

    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId( String inTransactionId )
    {
        transactionId = inTransactionId;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }
}
