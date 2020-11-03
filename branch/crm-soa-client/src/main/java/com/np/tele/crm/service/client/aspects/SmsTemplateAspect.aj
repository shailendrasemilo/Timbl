package com.np.tele.crm.service.client.aspects;

import java.util.Arrays;

import com.np.tele.crm.service.client.SmsTemplatePojo;

public aspect SmsTemplateAspect
{
    public int SmsTemplatePojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getSmsTemplateId() ^ ( this.getSmsTemplateId() >>> 32 ) );
        return result;
    }

    public boolean SmsTemplatePojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        SmsTemplatePojo other = (SmsTemplatePojo) obj;
        if ( this.getSmsTemplateId() != other.getSmsTemplateId() )
            return false;
        return true;
    }

    public String SmsTemplatePojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "SmsTemplatePojo [smsGateway=" ).append( this.getSmsGateway() ).append( ", smsTemplate=" )
                .append( new String( this.getSmsTemplate() ) ).append( ", smsTemplateId=" )
                .append( this.getSmsTemplateId() ).append( ", smsTemplateName=" ).append( this.getSmsTemplateName() )
                .append( ", smsType=" ).append( this.getSmsType() ).append( ", status=" ).append( this.getStatus() )
                .append( ", projects=" ).append( this.getProjects() ).append( "]" );
        return builder.toString();
    }
}
