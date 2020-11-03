package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmPartnerMappingPojo;

public aspect CrmPartnerMappingAspect
{
    public int CrmPartnerMappingPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getPartnerByNpId() == null ) ? 0 : this.getPartnerByNpId().hashCode() );
        result = prime * result + ( ( this.getPartnerBySpId() == null ) ? 0 : this.getPartnerBySpId().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getStatus() == null ) ? 0 : this.getStatus().hashCode() );
        return result;
    }

    public boolean CrmPartnerMappingPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmPartnerMappingPojo other = (CrmPartnerMappingPojo) obj;
        if ( this.getPartnerByNpId() == null )
        {
            if ( other.getPartnerByNpId() != null )
                return false;
        }
        else if ( !this.getPartnerByNpId().equals( other.getPartnerByNpId() ) )
            return false;
        if ( this.getPartnerBySpId() == null )
        {
            if ( other.getPartnerBySpId() != null )
                return false;
        }
        else if ( !this.getPartnerBySpId().equals( other.getPartnerBySpId() ) )
            return false;
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

    public String CrmPartnerMappingPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmPartnerMappingPojo [partnerByNpId=" ).append( this.getPartnerByNpId() )
                .append( ", partnerBySpId=" ).append( this.getPartnerBySpId() ).append( ", recordId=" )
                .append( this.getRecordId() ).append( ", status=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
