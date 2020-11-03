package com.np.tele.crm.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

public class EasyBillRequestDto
{
    private String authUsername;
    private String authPassword;
    private String clientIp;
    private String rmn;
    private String customerId;
    private Date   paymentDate;
    private String transactionId;

    @XmlTransient
    public String getAuthUsername()
    {
        return authUsername;
    }

    public void setAuthUsername( String inAuthUsername )
    {
        authUsername = inAuthUsername;
    }

    @XmlTransient
    public String getAuthPassword()
    {
        return authPassword;
    }

    public void setAuthPassword( String inAuthPassword )
    {
        authPassword = inAuthPassword;
    }

    @XmlTransient
    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp( String inClientIp )
    {
        clientIp = inClientIp;
    }

    @XmlTransient
    public String getRmn()
    {
        return rmn;
    }

    public void setRmn( String inRmn )
    {
        rmn = inRmn;
    }

    @XmlTransient
    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    @XmlTransient
    public Date getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate( Date inPaymentDate )
    {
        paymentDate = inPaymentDate;
    }

    @XmlTransient
    public String getTransactionId()
    {
        return transactionId;
    }

    public void setTransactionId( String inTransactionId )
    {
        transactionId = inTransactionId;
    }
}
