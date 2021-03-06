package com.np.tele.crm.pojos;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.np.tele.crm.utils.StringUtils;

/**
 * LocalityPojo generated by hbm2java
 */
public class LocalityPojo
    implements java.io.Serializable
{
    private long             localityId;
    //private AreaPojo area;
    private String           locality;
    private String           localityAlias;
    private String           createdBy;
    private Date             createdTime;
    private String           lastModifiedBy;
    private Date             lastModifiedTime;
    private String           status;
    private Set<SocietyPojo> societies = new HashSet<SocietyPojo>( 0 );
    private long             areaId;
    private boolean          editable;
    private String           displayCreatedTime;
    private String           displayLastModifiedTime;

    public LocalityPojo()
    {
    }

    public LocalityPojo( Long localityId, String locality, String createdBy )
    {
        this.localityId = localityId;
        this.locality = locality;
        this.createdBy = createdBy;
    }

    public LocalityPojo( Long localityId,
                         AreaPojo area,
                         String locality,
                         String localityAlias,
                         String createdBy,
                         Date createdTime,
                         String lastModifiedBy,
                         Date lastModifiedTime,
                         String status,
                         Set<SocietyPojo> societies )
    {
        this.localityId = localityId;
        //this.area = area;
        this.locality = locality;
        this.localityAlias = localityAlias;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    /*public AreaPojo getArea()
    {
        return this.area;
    }

    public void setArea( AreaPojo area )
    {
        this.area = area;
    }*/
    public String getLocality()
    {
        return this.locality;
    }

    public void setLocality( String locality )
    {
        this.locality = StringUtils.upperCase( locality );
    }

    public String getLocalityAlias()
    {
        return this.localityAlias;
    }

    public void setLocalityAlias( String localityAlias )
    {
        this.localityAlias = localityAlias;
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
       /* if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
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

    public Set<SocietyPojo> getSocieties()
    {
        return this.societies;
    }

    public void setSocieties( Set<SocietyPojo> societies )
    {
        this.societies = societies;
    }

    public long getLocalityId()
    {
        return localityId;
    }

    public void setLocalityId( long localityId )
    {
        this.localityId = localityId;
    }

    public long getAreaId()
    {
        return areaId;
    }

    public void setAreaId( long areaId )
    {
        this.areaId = areaId;
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
        builder.append( "LocalityPojo [locality=" ).append( locality ).append( ", status=" ).append( status )
                .append( "]" );
        return builder.toString();
    }
}
