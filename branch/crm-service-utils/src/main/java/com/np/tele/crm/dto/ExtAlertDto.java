package com.np.tele.crm.dto;

import java.util.Map;

public class ExtAlertDto
{
    private String              smsTemplate    = null;
    private String              emailTemplate  = null;
    private String              emailRecipient = null;
    private Long                smsRecipient   = null;
    private Map<String, String> paramMap       = null;

    public String getEmailRecipient()
    {
        return emailRecipient;
    }

    public void setEmailRecipient( String inEmailRecipient )
    {
        emailRecipient = inEmailRecipient;
    }

    public Long getSmsRecipient()
    {
        return smsRecipient;
    }

    public void setSmsRecipient( Long inSmsRecipient )
    {
        smsRecipient = inSmsRecipient;
    }

    public String getSmsTemplate()
    {
        return smsTemplate;
    }

    public void setSmsTemplate( String inSmsTemplate )
    {
        smsTemplate = inSmsTemplate;
    }

    public String getEmailTemplate()
    {
        return emailTemplate;
    }

    public void setEmailTemplate( String inEmailTemplate )
    {
        emailTemplate = inEmailTemplate;
    }

    public Map<String, String> getParamMap()
    {
        return paramMap;
    }

    public void setParamMap( Map<String, String> inParamMap )
    {
        paramMap = inParamMap;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ExtAlertDto [smsTemplate=" ).append( smsTemplate ).append( ", emailTemplate=" )
                .append( emailTemplate ).append( ", emailRecipient=" ).append( emailRecipient )
                .append( ", smsRecipient=" ).append( smsRecipient ).append( ", paramMap=" ).append( paramMap )
                .append( "]" );
        return builder.toString();
    }
}
