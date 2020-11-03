package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.LocalityPojo;

public aspect LocalityPojoAspect
{
    public int LocalityPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getLocalityId() ^ ( this.getLocalityId() >>> 32 ) );
        return result;
    }

    public boolean LocalityPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        LocalityPojo other = (LocalityPojo) obj;
        if ( this.getLocalityId() != other.getLocalityId() )
            return false;
        return true;
    }

    public String LocalityPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "LocalityPojo [locality=" ).append( this.getLocality() ).append( ", localityId=" )
                .append( this.getLocalityId() ).append( "]" );
        return builder.toString();
    }
}
