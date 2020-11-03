package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcAttributedToPojo;

public aspect CrmQrcAttributedToAspect
{
    public int CrmQrcAttributedToPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAttributedTo() == null ) ? 0 : this.getAttributedTo().hashCode() );
        result = prime * result + (int) ( this.getResolutionCodeId() ^ ( this.getResolutionCodeId() >>> 32 ) );
        return result;
    }

    public boolean CrmQrcAttributedToPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcAttributedToPojo other = (CrmQrcAttributedToPojo) obj;
        if ( this.getAttributedTo() == null )
        {
            if ( other.getAttributedTo() != null )
                return false;
        }
        else if ( !this.getAttributedTo().equals( other.getAttributedTo() ) )
            return false;
        if ( this.getResolutionCodeId() != other.getResolutionCodeId() )
            return false;
        return true;
    }
    
}
