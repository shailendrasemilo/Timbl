package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmDocumentDetailsPojo;

public aspect CrmDocumentDetailsAspect
{
    public int CrmDocumentDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAddressProof() == null ) ? 0 : this.getAddressProof().hashCode() );
        result = prime * result + (int) ( this.getCustomerRecordId() ^ ( this.getCustomerRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getIdProof() == null ) ? 0 : this.getIdProof().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        return result;
    }

    public boolean CrmDocumentDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmDocumentDetailsPojo other = (CrmDocumentDetailsPojo) obj;
        if ( this.getAddressProof() == null )
        {
            if ( other.getAddressProof() != null )
                return false;
        }
        else if ( !this.getAddressProof().equals( other.getAddressProof() ) )
            return false;
        if ( this.getCustomerRecordId() != other.getCustomerRecordId() )
            return false;
        if ( this.getIdProof() == null )
        {
            if ( other.getIdProof() != null )
                return false;
        }
        else if ( !this.getIdProof().equals( other.getIdProof() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        return true;
    }
}
