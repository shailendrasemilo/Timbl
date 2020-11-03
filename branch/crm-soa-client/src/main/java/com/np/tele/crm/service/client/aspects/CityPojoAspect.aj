package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CityPojo;

public aspect CityPojoAspect
{
    public int CityPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getCityId() ^ ( this.getCityId() >>> 32 ) );
        return result;
    }

    public boolean CityPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CityPojo other = (CityPojo) obj;
        if ( this.getCityId() != other.getCityId() )
            return false;
        return true;
    }

    public String CityPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CityPojo [cityId=" ).append( this.getCityId() ).append( ", cityName=" )
                .append( this.getCityName() ).append( ", areas=" ).append( this.getAreas() ).append( ", status=" )
                .append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
