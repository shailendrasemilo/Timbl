package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.EventTemplatesPojo;

public aspect EventTemplatesAspect
{
    public int EventTemplatesPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getEventTemplateId() ^ ( this.getEventTemplateId() >>> 32 ) );
        result = prime * result + ( ( this.getEvents() == null ) ? 0 : this.getEvents().hashCode() );
        return result;
    }

    public boolean EventTemplatesPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        EventTemplatesPojo other = (EventTemplatesPojo) obj;
        if ( this.getEventTemplateId() != other.getEventTemplateId() )
            return false;
        if ( this.getEvents() == null )
        {
            if ( other.getEvents() != null )
                return false;
        }
        else if ( !this.getEvents().equals( other.getEvents() ) )
            return false;
        return true;
    }

    public String EventTemplatesPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "EventTemplatesPojo [eventTemplateId=" ).append( this.getEventTemplateId() )
                .append( ", emailTemplate=" ).append( this.getEmailTemplate() ).append( ", smsTemplate=" )
                .append( this.getSmsTemplate() ).append( ", events=" ).append( this.getEvents() )
                .append( ", emailEnabled=" ).append( this.isEmailEnabled() ).append( ", smsEnabled=" )
                .append( this.isSmsEnabled() ).append( "]" );
        return builder.toString();
    }
}
