package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmRolesPojo;

public aspect CRMRolesAspect
{
    public int CrmRolesPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getRoleId() ^ ( this.getRoleId() >>> 32 ) );
        return result;
    }

    public boolean CrmRolesPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmRolesPojo other = (CrmRolesPojo) obj;
        if ( this.getRoleId() != other.getRoleId() )
            return false;
        return true;
    }

    public String CrmRolesPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmRolesPojo [getRoleDescription()=" ).append( getRoleDescription() )
                .append( ", getRoleId()=" ).append( this.getRoleId() ).append( ", getRoleName()=" )
                .append( this.getRoleName() ).append( ", getStatus()=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
