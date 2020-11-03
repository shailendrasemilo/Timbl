package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.EmailTemplatePojo;

public aspect EmailTemplateAspect
{
    public int EmailTemplatePojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (this.getEmailTemplateId() ^ ( this.getEmailTemplateId() >>> 32 ) );
        return result;
    }

    public boolean EmailTemplatePojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        EmailTemplatePojo other = (EmailTemplatePojo) obj;
        if ( this.getEmailTemplateId() != other.getEmailTemplateId() )
            return false;
        return true;
    }
}
