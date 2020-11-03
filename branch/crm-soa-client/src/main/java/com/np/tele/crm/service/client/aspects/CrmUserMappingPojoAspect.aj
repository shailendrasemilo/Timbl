package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmUserMappingPojo;

public aspect CrmUserMappingPojoAspect
{
    public int CrmUserMappingPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getMappedUserId() == null ) ? 0 : this.getMappedUserId().hashCode() );
        result = prime * result + ( ( this.getMappingType() == null ) ? 0 : this.getMappingType().hashCode() );
        return result;
    }

    public boolean CrmUserMappingPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmUserMappingPojo other = (CrmUserMappingPojo) obj;
        if ( this.getMappedUserId() == null )
        {
            if ( other.getMappedUserId() != null )
                return false;
        }
        else if ( !this.getMappedUserId().equals( other.getMappedUserId() ) )
            return false;
        if ( this.getMappingType() == null )
        {
            if ( other.getMappingType() != null )
                return false;
        }
        else if ( !this.getMappingType().equals( other.getMappingType() ) )
            return false;
        return true;
    }
}
