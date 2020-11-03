package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;

public aspect CrmQrcCategoryBinMappingPojoAspect
{
    public int CrmQrcCategoryBinMappingPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        if ( this.getMappingId() > 0 )
        {
            result = prime * result + (int) ( this.getMappingId() ^ ( this.getMappingId() >>> 32 ) );
        }
        else
        {
            result = prime * result + (int) (this.getFromBinId() ^ ( this.getFromBinId() >>> 32 ) );
            result = prime * result + (int) ( this.getQrcSubSubCategoryId() ^ (this.getQrcSubSubCategoryId() >>> 32 ) );
            result = prime * result + (int) (this.getToBinId() ^ (this.getToBinId() >>> 32 ) );
        }
        return result;
    }

    public boolean CrmQrcCategoryBinMappingPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcCategoryBinMappingPojo other = (CrmQrcCategoryBinMappingPojo) obj;
        if ( this.getMappingId() > 0 )
        {
            if ( this.getMappingId() != other.getMappingId() )
                return false;
        }
        else
        {
            if ( this.getFromBinId() != other.getFromBinId() )
                return false;
            if (this.getQrcSubSubCategoryId() != other.getQrcSubSubCategoryId() )
                return false;
            if ( this.getToBinId() != other.getToBinId() )
                return false;
        }
        return true;
    }
}
