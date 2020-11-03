package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcRootCausePojo;


public aspect CrmQrcRootCausePojoAspect
{
    public int CrmQrcRootCausePojo.hashCode()  {
        final int prime = 31;
        int result = 1;
        if ( this.getRootCauseId() > 0 )
        {
            result = prime * result + (int) ( this.getRootCauseId() ^ ( this.getRootCauseId() >>> 32 ) );
        }
        else
        {
            result = prime * result + (int) ( this.getActionId() ^ ( this.getActionId() >>> 32 ) );
            result = prime * result + ( (this.getRootCause() == null ) ? 0 : this.getRootCause().hashCode() );
        }
        return result;
    }

    public boolean CrmQrcRootCausePojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcRootCausePojo other = (CrmQrcRootCausePojo) obj;
        if ( this.getRootCauseId() > 0 )
        {
            if ( this.getRootCauseId() != other.getRootCauseId() )
                return false;
        }
        else
        {
            if ( this.getActionId() != other.getActionId() )
                return false;
            if (this.getRootCause() == null )
            {
                if ( other.getRootCause() != null )
                    return false;
            }
            else if ( !this.getRootCause().equals( other.getRootCause() ) )
                return false;
        }
        return true;
    }
}
