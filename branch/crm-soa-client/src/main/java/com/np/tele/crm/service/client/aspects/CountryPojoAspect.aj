package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CountryPojo;

public aspect CountryPojoAspect
{
    public String CountryPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CountryPojo [countryId=" ).append( this.getCountryId() ).append( ", countryName=" )
                .append( this.getCountryName() ).append( ", status=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }

    public int CountryPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getCountryId() ^ ( this.getCountryId() >>> 32 ) );
        return result;
    }

    public boolean CountryPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CountryPojo other = (CountryPojo) obj;
        if ( this.getCountryId() != other.getCountryId() )
            return false;
        return true;
    }
}
