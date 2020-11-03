package com.np.tele.crm.dto;

public class TriggerResponseDto
{
    private String customerId;
    private String extTransactionId;
    private String crmTransactionId;
    private String statusDesc;
    private String statusCode;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getExtTransactionId()
    {
        return extTransactionId;
    }

    public void setExtTransactionId( String inExtTransactionId )
    {
        extTransactionId = inExtTransactionId;
    }

    public String getCrmTransactionId()
    {
        return crmTransactionId;
    }

    public void setCrmTransactionId( String inCrmTransactionId )
    {
        crmTransactionId = inCrmTransactionId;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "TriggerResponseDto [customerId=" ).append( customerId ).append( ", extTransactionId=" )
                .append( extTransactionId ).append( ", crmTransactionId=" ).append( crmTransactionId )
                .append( ", statusDesc=" ).append( statusDesc ).append( ", statusCode=" ).append( statusCode )
                .append( "]" );
        return builder.toString();
    }
}
