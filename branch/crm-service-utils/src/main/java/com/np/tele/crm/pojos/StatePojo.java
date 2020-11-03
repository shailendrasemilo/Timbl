package com.np.tele.crm.pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.np.tele.crm.utils.StringUtils;

public class StatePojo
    implements java.io.Serializable
{
    private long          stateId;
    private short         countryId;
    // private CountryPojo country;
    private String        stateName;
    private String        stateAlias;
    private String        createdBy;
    private Date          createdTime;
    private String        lastModifiedBy;
    private Date          lastModifiedTime;
    private String        status;
    private boolean       editable;
    private Set<CityPojo> cities = new HashSet<CityPojo>( 0 );
    private String        displayCreatedTime;
    private String        displayLastModifiedTime;

    public StatePojo()
    {
    }

    public StatePojo( long stateId, String stateName, String createdBy )
    {
        this.stateId = stateId;
        this.stateName = stateName;
        this.createdBy = createdBy;
    }

    public StatePojo( long stateId,
                      CountryPojo country,
                      String stateName,
                      String stateAlias,
                      String createdBy,
                      Date createdTime,
                      String lastModifiedBy,
                      Date lastModifiedTime,
                      String status,
                      Set<CityPojo> cities )
    {
        this.stateId = stateId;
        this.stateName = stateName;
        this.stateAlias = stateAlias;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    public long getStateId()
    {
        return stateId;
    }

    public void setStateId( long stateId )
    {
        this.stateId = stateId;
    }

    public short getCountryId()
    {
        return countryId;
    }

    public void setCountryId( short countryId )
    {
        this.countryId = countryId;
    }

    /*public CountryPojo getCountry()
    {
        return this.country;
    }

    public void setCountry( CountryPojo country )
    {
        this.country = country;
    }*/
    public String getStateName()
    {
        return this.stateName;
    }

    public void setStateName( String stateName )
    {
        this.stateName = StringUtils.upperCase( stateName );
    }

    public String getStateAlias()
    {
        return this.stateAlias;
    }

    public void setStateAlias( String stateAlias )
    {
        this.stateAlias = stateAlias;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date inCreatedTime )
    {
        createdTime = inCreatedTime;
        /*if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
        {
            displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( createdTime );
        }*/
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date inLastModifiedTime )
    {
        lastModifiedTime = inLastModifiedTime;
        /*if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
        {
            displayLastModifiedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( lastModifiedTime );
        }*/
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public Set<CityPojo> getCities()
    {
        return this.cities;
    }

    public void setCities( Set<CityPojo> cities )
    {
        this.cities = cities;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable( boolean inEditable )
    {
        editable = inEditable;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public String getDisplayLastModifiedTime()
    {
        return displayLastModifiedTime;
    }

    public void setDisplayLastModifiedTime( String inDisplayLastModifiedTime )
    {
        displayLastModifiedTime = inDisplayLastModifiedTime;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "StatePojo [stateName=" ).append( stateName ).append( ", status=" ).append( status )
                .append( ", cities=" ).append( cities ).append( "]" );
        return builder.toString();
    }
}
