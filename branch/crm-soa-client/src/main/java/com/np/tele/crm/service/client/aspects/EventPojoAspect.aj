package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.EventsPojo;

public aspect EventPojoAspect
{
    public int EventsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getEventId() ^ ( this.getEventId() >>> 32 ) );
        return result;
    }

    public boolean EventsPojo.equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( ! ( obj instanceof EventsPojo ) )
        {
            return false;
        }
        EventsPojo other = (EventsPojo) obj;
        if ( this.getEventId() != other.getEventId() )
        {
            return false;
        }
        return true;
    }
}
