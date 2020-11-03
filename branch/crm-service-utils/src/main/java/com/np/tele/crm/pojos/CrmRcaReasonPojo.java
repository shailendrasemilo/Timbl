package com.np.tele.crm.pojos;

// default package
// Generated Sep 26, 2013 6:47:51 PM by Hibernate Tools 3.4.0.CR1
import java.math.BigInteger;
import java.util.Date;

import com.np.tele.crm.utils.StringUtils;

/**
 * CrmRcaReasonPojo generated by hbm2java
 */
public class CrmRcaReasonPojo
    implements java.io.Serializable
{
    private long    categoryId;
    private String  category;
    private String  subCategory;
    private String  subSubCategory;
    private String  categoryAlias;
    private String  categoryValue;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  status;
    private boolean isEditable;
    private String  displayCreatedTime;
    private String  displayLastModifiedTime;
    private BigInteger    categoryIdentity;
    private String  valueAlias;
    private String  modificationAllowed;

    public CrmRcaReasonPojo()
    {
    }

    public CrmRcaReasonPojo( long categoryId,
                             String category,
                             String subCategory,
                             String subSubCategory,
                             String categoryValue,
                             String createdBy )
    {
        this.categoryId = categoryId;
        this.category = category;
        this.subCategory = subCategory;
        this.subSubCategory = subSubCategory;
        this.categoryValue = categoryValue;
        this.createdBy = createdBy;
    }

    public CrmRcaReasonPojo( long categoryId,
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

    public long getCategoryId()
    {
        return this.categoryId;
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
        /*  if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
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

    public boolean isEditable()
    {
        return isEditable;
    }

    public void setEditable( boolean inIsEditable )
    {
        isEditable = inIsEditable;
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

    public BigInteger getCategoryIdentity()
    {
        return categoryIdentity;
    }

    public void setCategoryIdentity( BigInteger inCategoryIdentity )
    {
        categoryIdentity = inCategoryIdentity;
        if ( StringUtils.isValidObj( inCategoryIdentity ) )
        {
            setCategoryId( categoryIdentity.longValue() );
        }
    }

    public String getValueAlias()
    {
        return valueAlias;
    }

    public void setValueAlias( String valueAlias )
    {
        this.valueAlias = valueAlias;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( category == null ) ? 0 : category.hashCode() );
        result = prime * result + ( ( categoryValue == null ) ? 0 : categoryValue.hashCode() );
        result = prime * result + ( ( subCategory == null ) ? 0 : subCategory.hashCode() );
        result = prime * result + ( ( subSubCategory == null ) ? 0 : subSubCategory.hashCode() );
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmRcaReasonPojo other = (CrmRcaReasonPojo) obj;
        if ( category == null )
        {
            if ( other.category != null )
                return false;
        }
        else if ( !category.equals( other.category ) )
            return false;
        if ( categoryValue == null )
        {
            if ( other.categoryValue != null )
                return false;
        }
        else if ( !categoryValue.equals( other.categoryValue ) )
            return false;
        if ( subCategory == null )
        {
            if ( other.subCategory != null )
                return false;
        }
        else if ( !subCategory.equals( other.subCategory ) )
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

    public String getModificationAllowed()
    {
        return modificationAllowed;
    }

    public void setModificationAllowed( String modificationAllowed )
    {
        this.modificationAllowed = modificationAllowed;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmRcaReasonPojo [categoryId=" ).append( categoryId ).append( ", category=" )
                .append( category ).append( ", subCategory=" ).append( subCategory ).append( ", subSubCategory=" )
                .append( subSubCategory ).append( ", categoryValue=" ).append( categoryValue ).append( "]" );
        return builder.toString();
    }
}
