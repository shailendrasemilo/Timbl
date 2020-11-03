package com.np.tele.crm.pojos;

import java.util.Date;

import java.util.HashSet;
import java.util.Set;

import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.StringUtils;

public class CityPojo
    implements java.io.Serializable
{
    private long          cityId;
    //private StatePojo state;
    private String        cityName;
    private String        cityAlias;
    private int           stdCode;
    private String        createdBy;
    private Date          createdTime;
    private String        lastModifiedBy;
    private Date          lastModifiedTime;
    private String        status;
    private Set<AreaPojo> areas = new HashSet<AreaPojo>( 0 );
    private long          stateId;
    private boolean       editable;
    private String        displayCreatedTime;
    private String        displayLastModifiedTime;

    public CityPojo()
    {
    }

    public CityPojo( long cityId, String cityName, int stdCode, String createdBy )
    {
        this.cityId = cityId;
        this.cityName = cityName;
        this.stdCode = stdCode;
        this.createdBy = createdBy;
    }

    public CityPojo( long cityId,
                     StatePojo state,
                     String cityName,
                     String cityAlias,
                     int stdCode,
                     String createdBy,
                     Date createdTime,
                     String lastModifiedBy,
                     Date lastModifiedTime,
                     String status,
                     Set<AreaPojo> areas )
    {
        this.cityId = cityId;
        this.cityName = cityName;
        this.cityAlias = cityAlias;
        this.stdCode = stdCode;
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

    public void setStateId( long inStateId )
    {
        stateId = inStateId;
    }

    public long getCityId()
    {
        return cityId;
    }

    public void setCityId( long cityId )
    {
        this.cityId = cityId;
    }

    /*public StatePojo getState()
    {
        return this.state;
    }

    public void setState( StatePojo state )
    {
        this.state = state;
    }*/
    public String getCityName()
    {
        return this.cityName;
    }

    public void setCityName( String cityName )
    {
        this.cityName = StringUtils.upperCase( cityName );
    }

    public String getCityAlias()
    {
        return this.cityAlias;
    }

    public void setCityAlias( String cityAlias )
    {
        this.cityAlias = cityAlias;
    }

    public int getStdCode()
    {
        return this.stdCode;
    }

    public void setStdCode( int stdCode )
    {
        this.stdCode = stdCode;
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
       /* if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
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

    public Set<AreaPojo> getAreas()
    {
        return this.areas;
    }

    public void setAreas( Set<AreaPojo> areas )
    {
        this.areas = areas;
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
        builder.append( "CityPojo [cityName=" ).append( cityName ).append( ", status=" ).append( status )
                .append( ", areas=" ).append( areas ).append( "]" );
        return builder.toString();
    }
}
