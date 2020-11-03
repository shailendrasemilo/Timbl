package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmUserPojo;

public aspect CrmUserPojoAspect
{
    public String CrmUserPojo.getFullName()
    {
        return this.getFirstName() + " " + this.getLastName();
    }

    public int CrmUserPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        return result;
    }

    public boolean CrmUserPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmUserPojo other = (CrmUserPojo) obj;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        return true;
    }

    public String CrmUserPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmUserPojo [emailId=" ).append( this.getEmailId() ).append( ", firstName=" )
                .append( this.getFirstName() ).append( ", lastName=" ).append( this.getLastName() )
                .append( ", mobileNo=" ).append( this.getMobileNo() ).append( ", passwordExpiry=" )
                .append( this.getPasswordExpiry() ).append( ", status=" ).append( this.getStatus() )
                .append( ", userAccountExpiry=" ).append( this.getUserAccountExpiry() ).append( ", userId=" )
                .append( this.getUserId() ).append( "]" );
        return builder.toString();
    }
}
