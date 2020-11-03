package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;

public aspect SocietyNetworkPartnerAspect
{
    public int SocietyNetworkPartnerPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getPartnerId() ^ ( this.getPartnerId() >>> 32 ) );
        result = prime * result + (int) ( this.getRfsDus() ^ ( this.getRfsDus() >>> 32 ) );
        return result;
    }

    public boolean SocietyNetworkPartnerPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        SocietyNetworkPartnerPojo other = (SocietyNetworkPartnerPojo) obj;
        if ( this.getPartnerId() != other.getPartnerId() )
            return false;
        if ( this.getRfsDus() != other.getRfsDus() )
            return false;
        return true;
    }
}
