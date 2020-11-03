package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.AccessGroupPojo;

public aspect AccessGroupAspect
{
    public int AccessGroupPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( this.isCreateAccess() ? 1231 : 1237 );
        result = prime * result + ( ( this.getCrmParameter() == null ) ? 0 : this.getCrmParameter().hashCode() );
        result = prime * result + ( ( this.getCrmRoles() == null ) ? 0 : this.getCrmRoles().hashCode() );
        result = prime * result + ( this.isDeleteAccess() ? 1231 : 1237 );
        result = prime * result + ( ( this.getParameterValue() == null ) ? 0 : this.getParameterValue().hashCode() );
        result = prime * result + ( this.isReadAccess() ? 1231 : 1237 );
        result = prime * result + ( this.isRecoverAccess() ? 1231 : 1237 );
        result = prime * result + ( ( this.getStatus() == null ) ? 0 : this.getStatus().hashCode() );
        result = prime * result + ( this.isUpdateAccess() ? 1231 : 1237 );
        return result;
    }

    public boolean AccessGroupPojo.equals( Object obj )
    {
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        AccessGroupPojo other = (AccessGroupPojo) obj;
        if ( this.isCreateAccess() != other.isCreateAccess() )
            return false;
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
        if ( this.isDeleteAccess() != other.isDeleteAccess() )
            return false;
        if ( this.getParameterValue() == null )
        {
            if ( other.getParameterValue() != null )
                return false;
        }
        else if ( !this.getParameterValue().equals( other.getParameterValue() ) )
            return false;
        if ( this.isReadAccess() != other.isReadAccess() )
            return false;
        if ( this.isRecoverAccess() != other.isRecoverAccess() )
            return false;
        if ( this.getStatus() == null )
        {
            if ( other.getStatus() != null )
                return false;
        }
        else if ( !this.getStatus().equals( other.getStatus() ) )
            return false;
        if ( this.isUpdateAccess() != other.isUpdateAccess() )
            return false;
        return true;
    }

    public String AccessGroupPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AccessGroupPojo [recordId=" ).append( this.getRecordId() ).append( ", createAccess=" )
                .append( this.isCreateAccess() ).append( ", crmParameter=" ).append( this.getCrmParameter() )
                .append( ", crmRoles=" ).append( this.getCrmRoles() ).append( ", deleteAccess=" )
                .append( this.isDeleteAccess() ).append( ", parameterValue=" ).append( this.getParameterValue() )
                .append( ", readAccess=" ).append( this.isReadAccess() ).append( ", recoverAccess=" )
                .append( this.isRecoverAccess() ).append( ", status=" ).append( this.getStatus() )
                .append( ", updateAccess=" ).append( this.isUpdateAccess() ).append( "]" );
        return builder.toString();
    }
}
