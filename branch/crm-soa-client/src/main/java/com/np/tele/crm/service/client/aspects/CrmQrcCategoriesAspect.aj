package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;

public aspect CrmQrcCategoriesAspect
{
    public int CrmQrcCategoriesPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getQrcCategoryId() ^ ( this.getQrcCategoryId() >>> 32 ) );
        return result;
    }

    public boolean CrmQrcCategoriesPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcCategoriesPojo other = (CrmQrcCategoriesPojo) obj;
        if ( this.getQrcCategoryId() != other.getQrcCategoryId() )
            return false;
        return true;
    }
}
