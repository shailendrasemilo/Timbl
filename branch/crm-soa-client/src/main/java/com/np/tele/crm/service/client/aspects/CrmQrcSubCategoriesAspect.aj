package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;

public aspect CrmQrcSubCategoriesAspect
{
    public int CrmQrcSubCategoriesPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getQrcSubCategoryId() ^ ( this.getQrcSubCategoryId() >>> 32 ) );
        return result;
    }

    public boolean CrmQrcSubCategoriesPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcSubCategoriesPojo other = (CrmQrcSubCategoriesPojo) obj;
        if ( this.getQrcSubCategoryId() != other.getQrcSubCategoryId() )
            return false;
        return true;
    }
}
