package com.np.tele.crm.pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.StringUtils;

public class CountryPojo
    implements java.io.Serializable
{
    private short          countryId;
    private String         countryName;
    private short          isdCode;
    private String         description;
    private String         createdBy;
    private Date           createdTime;
    private String         lastModifiedBy;
    private Date           lastModifiedTime;
    private String         status;
    private Set<StatePojo> states = new HashSet<StatePojo>( 0 );
    private String         displayCreatedTime;
    private String         displayLastModifiedTime;

    public CountryPojo()
    {
    }

    public CountryPojo( short countryId, String countryName, Short isdCode, String createdBy )
    {
        this.countryId = countryId;
        this.countryName = countryName;
        this.isdCode = isdCode;
        this.createdBy = createdBy;
    }

    public CountryPojo( short countryId,
                        String countryName,
                        Short isdCode,
                        String description,
                        String createdBy,
                        Date createdTime,
                        String lastModifiedBy,
                        Date lastModifiedTime,
                        String status,
                        Set<StatePojo> states )
    {
        this.countryId = countryId;
        this.countryName = countryName;
        this.isdCode = isdCode;
        this.description = description;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        this.states = states;
    }

    public short getCountryId()
    {
        return countryId;
    }

    public void setCountryId( short countryId )
    {
        this.countryId = countryId;
    }

    public short getIsdCode()
    {
        return isdCode;
    }

    public void setIsdCode( short isdCode )
    {
        this.isdCode = isdCode;
    }

    public String getCountryName()
    {
        return this.countryName;
    }

    public void setCountryName( String countryName )
    {
        this.countryName = countryName;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription( String description )
    {
        this.description = description;
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

    public Set<StatePojo> getStates()
    {
        return this.states;
    }

    public void setStates( Set<StatePojo> states )
    {
        this.states = states;
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
        builder.append( "CountryPojo [countryName=" ).append( countryName ).append( ", status=" ).append( status )
                .append( ", states=" ).append( states ).append( "]" );
        return builder.toString();
    }
}
