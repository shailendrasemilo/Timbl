package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmNpMobilePojo;

public aspect CrmNpMobilePojoAspect
{
    public int CrmNpMobilePojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getEmailId() == null ) ? 0 : this.getEmailId().hashCode() );
        result = prime * result + ( ( this.getEventId() == null ) ? 0 : this.getEventId().hashCode() );
        result = prime * result + (int) ( this.getMobileNo() ^ ( this.getMobileNo() >>> 32 ) );
        result = prime * result + (int) ( this.getNpId() ^ ( this.getNpId() >>> 32 ) );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getStatus() == null ) ? 0 : this.getStatus().hashCode() );
        return result;
    }

    public boolean CrmNpMobilePojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmNpMobilePojo other = (CrmNpMobilePojo) obj;
        if ( this.getEmailId() == null )
        {
            if ( other.getEmailId() != null )
                return false;
        }
        else if ( !this.getEmailId().equals( other.getEmailId() ) )
            return false;
        if ( this.getEventId() == null )
        {
            if ( other.getEventId() != null )
                return false;
        }
        else if ( !this.getEventId().equals( other.getEventId() ) )
            return false;
        if ( this.getMobileNo() != other.getMobileNo() )
            return false;
        if ( this.getNpId() != 0 && other.getNpId() != 0 )// if condition for handling event case
        {
            if ( this.getNpId() != other.getNpId() )
                return false;
        }
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        if ( this.getStatus() == null )
        {
            if ( other.getStatus() != null )
                return false;
        }
        else if ( !this.getStatus().equals( other.getStatus() ) )
            return false;
        return true;
    }

    public String CrmNpMobilePojo.toString()
    {
        return "CrmNpMobilePojo [recordId=" + this.getRecordId() + ", npId=" + this.getNpId() + ", mobileNo="
                + this.getMobileNo() + ", status=" + this.getStatus() + ", editable=" + this.isEditable() + "]";
    }
}
