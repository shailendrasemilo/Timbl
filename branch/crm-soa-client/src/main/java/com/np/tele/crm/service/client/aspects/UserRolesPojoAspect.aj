package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.UserRolesPojo;

public aspect UserRolesPojoAspect
{
    public int UserRolesPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getCrmParameter() == null ) ? 0 : this.getCrmParameter().hashCode() );
        result = prime * result + ( ( this.getCrmRoles() == null ) ? 0 : this.getCrmRoles().hashCode() );
        result = prime * result + ( ( this.getGroups() == null ) ? 0 : this.getGroups().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        return result;
    }

    public boolean UserRolesPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        UserRolesPojo other = (UserRolesPojo) obj;
        if ( this.getCrmParameter() == null )
        {
            if ( other.getCrmParameter() != null )
                return false;
        }
        else if ( !this.getCrmParameter().equals( other.getCrmParameter() ) )
            return false;
        if ( this.getCrmRoles() == null )
        {
            if ( other.getCrmRoles() != null )
                return false;
        }
        else if ( !this.getCrmRoles().equals( other.getCrmRoles() ) )
            return false;
        if ( this.getGroups() == null )
        {
            if ( other.getGroups() != null )
                return false;
        }
        else if ( !this.getGroups().equals( other.getGroups() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        return true;
    }

    public String UserRolesPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "UserRolesPojo [crmParameter=" ).append( this.getCrmParameter() ).append( ", crmRoles=" )
                .append( this.getCrmRoles() ).append( ", groups=" ).append( this.getGroups() ).append( ", recordId=" )
                .append( this.getRecordId() ).append( "]" );
        return builder.toString();
    }
}
