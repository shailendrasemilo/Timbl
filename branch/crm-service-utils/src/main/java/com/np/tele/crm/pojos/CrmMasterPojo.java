package com.np.tele.crm.pojos;

// Generated 5 Jul, 2013 11:15:37 AM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

/**
 * CrmMaster generated by hbm2java
 */
public class CrmMasterPojo
    implements java.io.Serializable
{
    private long   categoryId;
    private String category;
    private String subCategory;
    private String subSubCategory;
    private String categoryAlias;
    private String categoryValue;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;
    private String dataType;
    private String displayCreatedTime;
    private String displayLastModifiedTime;

    public CrmMasterPojo()
    {
    }

    public CrmMasterPojo( long categoryId,
                          String category,
                          String subCategory,
                          String subSubCategory,
                          String categoryValue,
                          String createdBy,
                          String lastModifiedBy,
                          Date lastModifiedTime )
    {
        this.categoryId = categoryId;
        this.category = category;
        this.subCategory = subCategory;
        this.subSubCategory = subSubCategory;
        this.categoryValue = categoryValue;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public CrmMasterPojo( long categoryId,
                          String category,
                          String subCategory,
                          String subSubCategory,
                          String categoryAlias,
                          String categoryValue,
                          String createdBy,
                          Date createdTime,
                          String lastModifiedBy,
                          Date lastModifiedTime,
                          String status )
    {
        this.categoryId = categoryId;
        this.category = category;
        this.subCategory = subCategory;
        this.subSubCategory = subSubCategory;
        this.categoryAlias = categoryAlias;
        this.categoryValue = categoryValue;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
    }

    public String getDataType()
    {
        return dataType;
    }

    public void setDataType( String inDataType )
    {
        dataType = inDataType;
    }

    public long getCategoryId()
    {
        return categoryId;
    }

    public void setCategoryId( long categoryId )
    {
        this.categoryId = categoryId;
    }

    public String getCategory()
    {
        return this.category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }

    public String getSubCategory()
    {
        return this.subCategory;
    }

    public void setSubCategory( String subCategory )
    {
        this.subCategory = subCategory;
    }

    public String getSubSubCategory()
    {
        return this.subSubCategory;
    }

    public void setSubSubCategory( String subSubCategory )
    {
        this.subSubCategory = subSubCategory;
    }

    public String getCategoryAlias()
    {
        return this.categoryAlias;
    }

    public void setCategoryAlias( String categoryAlias )
    {
        this.categoryAlias = categoryAlias;
    }

    public String getCategoryValue()
    {
        return this.categoryValue;
    }

    public void setCategoryValue( String categoryValue )
    {
        this.categoryValue = categoryValue;
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
        builder.append( "CrmMasterPojo [categoryId=" ).append( categoryId ).append( ", category=" ).append( category )
                .append( ", subCategory=" ).append( subCategory ).append( ", subSubCategory=" ).append( subSubCategory )
                .append( ", categoryAlias=" ).append( categoryAlias ).append( ", categoryValue=" )
                .append( categoryValue ).append( ", createdBy=" ).append( createdBy ).append( ", createdTime=" )
                .append( createdTime ).append( ", lastModifiedBy=" ).append( lastModifiedBy )
                .append( ", lastModifiedTime=" ).append( lastModifiedTime ).append( ", status=" ).append( status )
                .append( "]" );
        return builder.toString();
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( categoryAlias == null ) ? 0 : categoryAlias.hashCode() );
        result = prime * result + ( ( subSubCategory == null ) ? 0 : subSubCategory.hashCode() );
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
        CrmMasterPojo other = (CrmMasterPojo) obj;
        if ( categoryAlias == null )
        {
            if ( other.categoryAlias != null )
                return false;
        }
        else if ( !categoryAlias.equals( other.categoryAlias ) )
            return false;
        if ( subSubCategory == null )
        {
            if ( other.subSubCategory != null )
                return false;
        }
        else if ( !subSubCategory.equals( other.subSubCategory ) )
            return false;
        return true;
    }
}
