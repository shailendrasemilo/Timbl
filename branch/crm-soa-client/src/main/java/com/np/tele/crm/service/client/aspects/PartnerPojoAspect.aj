package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.PartnerPojo;

public aspect PartnerPojoAspect
{
    public int PartnerPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getPartnerId() ^ ( this.getPartnerId() >>> 32 ) );
        return result;
    }

    public boolean PartnerPojo.equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        PartnerPojo other = (PartnerPojo) obj;
        if ( this.getPartnerId() != other.getPartnerId() )
        {
            return false;
        }
        return true;
    }

    
    public String PartnerPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "PartnerPojo [bussinessType=" ).append( this.getBussinessType() ).append( ", partnerAlias=" )
                .append( this.getPartnerAlias() ).append( ", partnerId=" ).append( this.getPartnerId() )
                .append( ", partnerName=" ).append( this.getPartnerName() ).append( ", partnerType=" )
                .append( this.getPartnerType() ).append( "]" );
        return builder.toString();
    }
}
