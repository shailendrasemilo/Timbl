package com.np.tele.crm.pojos;

public class GISMasterPojo
{
    private String country;
    private String state;
    private String city;
    private String area;
    private String locality;
    private long   countryId;
    private long   stateId;
    private long   cityId;
    private long   areaId;
    //    private long   localityId;
    private long   pincode;

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String area )
    {
        this.area = area;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality( String locality )
    {
        this.locality = locality;
    }

    public long getCountryId()
    {
        return countryId;
    }

    public void setCountryId( long inCountryId )
    {
        countryId = inCountryId;
    }

    public long getStateId()
    {
        return stateId;
    }

    public void setStateId( long inStateId )
    {
        stateId = inStateId;
    }

    public long getCityId()
    {
        return cityId;
    }

    public void setCityId( long inCityId )
    {
        cityId = inCityId;
    }

    public long getAreaId()
    {
        return areaId;
    }

    public void setAreaId( long inAreaId )
    {
        areaId = inAreaId;
    }

    //    public long getLocalityId()
    //    {
    //        return localityId;
    //    }
    //
    //    public void setLocalityId( long localityId )
    //    {
    //        this.localityId = localityId;
    //    }
    public long getPincode()
    {
        return pincode;
    }

    public void setPincode( long pincode )
    {
        this.pincode = pincode;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( area == null ) ? 0 : area.hashCode() );
        result = prime * result + ( ( city == null ) ? 0 : city.hashCode() );
        result = prime * result + ( ( country == null ) ? 0 : country.hashCode() );
        result = prime * result + ( ( state == null ) ? 0 : state.hashCode() );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        GISMasterPojo other = (GISMasterPojo) obj;
        if ( area == null )
        {
            if ( other.area != null )
                return false;
        }
        else if ( !area.equals( other.area ) )
            return false;
        if ( city == null )
        {
            if ( other.city != null )
                return false;
        }
        else if ( !city.equals( other.city ) )
            return false;
        if ( country == null )
        {
            if ( other.country != null )
                return false;
        }
        else if ( !country.equals( other.country ) )
            return false;
        if ( state == null )
        {
            if ( other.state != null )
                return false;
        }
        else if ( !state.equals( other.state ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "GISMasterPojo [country=" ).append( country ).append( ", state=" ).append( state )
                .append( ", city=" ).append( city ).append( ", area=" ).append( area ).append( ", countryId=" )
                .append( countryId ).append( ", stateId=" ).append( stateId ).append( ", cityId=" ).append( cityId )
                .append( ", areaId=" ).append( areaId ).append( ", pincode=" ).append( pincode ).append( "]" );
        return builder.toString();
    }
}
