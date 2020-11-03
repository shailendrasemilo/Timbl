package com.np.tele.crm.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

public class TriggerRequestDto
{
    private String customerId;
    private Date   actionDate;
    private String extTransactionId;
    private String authUsername;
    private String authPassword;
    private String clientIp;
    private long   transId;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public Date getActionDate()
    {
        return actionDate;
    }

    public void setActionDate( Date inActionDate )
    {
        actionDate = inActionDate;
    }

    public String getExtTransactionId()
    {
        return extTransactionId;
    }

    public void setExtTransactionId( String inExtTransactionId )
    {
        extTransactionId = inExtTransactionId;
    }

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

    public void setClientIp( String inServerIp )
    {
        clientIp = inServerIp;
    }

    @XmlTransient
    public long getTransId()
    {
        return transId;
    }

    public void setTransId( long inTransId )
    {
        transId = inTransId;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "TriggerRequestDto [customerId=" ).append( customerId ).append( ", actionDate=" )
                .append( actionDate ).append( ", extTransactionId=" ).append( extTransactionId ).append( ", clientIp=" )
                .append( clientIp ).append( "]" );
        return builder.toString();
    }
}
