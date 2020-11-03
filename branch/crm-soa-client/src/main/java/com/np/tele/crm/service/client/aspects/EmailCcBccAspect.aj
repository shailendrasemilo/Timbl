package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.EmailCcBccPojo;

public aspect EmailCcBccAspect
{
    public int EmailCcBccPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getEmailId() == null ) ? 0 : this.getEmailId().hashCode() );
        result = prime * result + ( ( this.getEmailType() == null ) ? 0 : this.getEmailType().hashCode() );
        return result;
    }

    public boolean EmailCcBccPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        EmailCcBccPojo other = (EmailCcBccPojo) obj;
        if ( this.getEmailId() == null )
        {
            if ( other.getEmailId() != null )
                return false;
        }
        else if ( !this.getEmailId().equals( other.getEmailId() ) )
            return false;
        if ( this.getEmailType() == null )
        {
            if ( other.getEmailType() != null )
                return false;
        }
        else if ( !this.getEmailType().equals( other.getEmailType() ) )
            return false;
        return true;
    }
}
