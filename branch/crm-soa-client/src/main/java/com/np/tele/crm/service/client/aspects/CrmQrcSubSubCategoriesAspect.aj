package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;

public aspect CrmQrcSubSubCategoriesAspect
{
    public int CrmQrcSubSubCategoriesPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        if ( this.getQrcSubSubCategoryId() > 0 )
        {
            result = prime * result + (int) ( this.getQrcSubSubCategoryId() ^ ( this.getQrcSubSubCategoryId() >>> 32 ) );
        }
        else
        {
            result = prime * result + (int) ( this.getQrcSubCategoryId() ^ ( this.getQrcSubCategoryId() >>> 32 ) );
            result = prime * result
                    + ( ( this.getQrcSubSubCategory() == null ) ? 0 : this.getQrcSubSubCategory().hashCode() );
            result = prime * result + ( ( this.getQrcType() == null ) ? 0 : this.getQrcType().hashCode() );
        }
        return result;
    }

    public boolean CrmQrcSubSubCategoriesPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcSubSubCategoriesPojo other = (CrmQrcSubSubCategoriesPojo) obj;
        if ( this.getQrcSubSubCategoryId() > 0 && other.getQrcSubSubCategoryId() > 0 )
        {
            if ( this.getQrcSubSubCategoryId() != other.getQrcSubSubCategoryId() )
                return false;
        }
        else
        {
            if ( this.getQrcSubSubCategory() == null )
            {
                if ( other.getQrcSubSubCategory() != null )
                    return false;
            }
            else if ( !this.getQrcSubSubCategory().equalsIgnoreCase( other.getQrcSubSubCategory() ) )
                return false;
            if ( this.getQrcType() == null )
            {
                if ( other.getQrcType() != null )
                    return false;
            }
            else if ( !this.getQrcType().equals( other.getQrcType() ) )
                return false;
        }
        return true;
    }

    public String CrmQrcSubSubCategoriesPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmQrcSubSubCategoriesPojo [createdBy=" ).append( this.getCreatedBy() )
                .append( ", createdTime=" ).append( this.getCreatedTime() ).append( ", functionalBinId=" )
                .append( this.getFunctionalBinId() ).append( ", lastModifiedBy=" ).append( this.getLastModifiedBy() )
                .append( ", lastModifiedTime=" ).append( this.getLastModifiedTime() ).append( ", qrcSubCategoryId=" )
                .append( this.getQrcSubCategoryId() ).append( ", qrcSubSubCategory=" )
                .append( this.getQrcSubSubCategory() ).append( ", qrcSubSubCategoryId=" )
                .append( this.getQrcSubSubCategoryId() ).append( ", qrcType=" ).append( this.getQrcType() )
                .append( ", resolutionType=" ).append( this.getResolutionType() ).append( ", status=" )
                .append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
