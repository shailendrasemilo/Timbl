package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.GroupsPojo;


public aspect GroupPojoAspect
{
    public int GroupsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getGroupId() ^ ( this.getGroupId() >>> 32 ) );
        return result;
    }

    public boolean GroupsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        GroupsPojo other = (GroupsPojo) obj;
        if ( this.getGroupId() != other.getGroupId() )
            return false;
        return true;
    }

    public String GroupsPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "GroupsPojo [groupDescription=" ).append( this.getGroupDescription() ).append( ", groupId=" )
                .append( this.getGroupId() ).append( ", groupName=" ).append( this.getGroupName() )
                .append( ", groupType=" ).append( this.getGroupType() ).append( ", status=" ).append( this.getStatus() )
                .append( "]" );
        return builder.toString();
    }
}
