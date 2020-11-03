package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;

public aspect CrmCustAssetDetailsPojoAspect
{
    public int CrmCustAssetDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getCategoryName() == null ) ? 0 : this.getCategoryName().hashCode() );
        result = prime * result + ( ( this.getCategoryValue() == null ) ? 0 : this.getCategoryValue().hashCode() );
        result = prime * result + (int) ( this.getCustomerRecordId() ^ ( this.getCustomerRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getStatus() == null ) ? 0 : this.getStatus().hashCode() );
        return result;
    }

    public boolean CrmCustAssetDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCustAssetDetailsPojo other = (CrmCustAssetDetailsPojo) obj;
        if ( this.getCategoryName() == null )
        {
            if ( other.getCategoryName() != null )
                return false;
        }
        else if ( !this.getCategoryName().equals( other.getCategoryName() ) )
            return false;
        if ( this.getCategoryValue() == null )
        {
            if ( other.getCategoryValue() != null )
                return false;
        }
        else if ( !this.getCategoryValue().equals( other.getCategoryValue() ) )
            return false;
        if ( this.getCustomerRecordId() != other.getCustomerRecordId() )
            return false;
        if ( this.getStatus() == null )
        {
            if ( other.getStatus() != null )
                return false;
        }
        else if ( !this.getStatus().equals( other.getStatus() ) )
            return false;
        return true;
    }
}
