package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmAddressDetailsPojo;

public aspect CrmAddressDetailsAspect
{
    public int CrmAddressDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAddLine1() == null ) ? 0 : this.getAddLine1().hashCode() );
        result = prime * result + ( ( this.getAddLine2() == null ) ? 0 : this.getAddLine2().hashCode() );
        result = prime * result + ( ( this.getAddLine3() == null ) ? 0 : this.getAddLine3().hashCode() );
        result = prime * result + ( ( this.getAddressType() == null ) ? 0 : this.getAddressType().hashCode() );
        result = prime * result + (int) ( this.getContactNo() ^ ( this.getContactNo() >>> 32 ) );
        result = prime * result + (int) ( this.getInstAreaId() ^ ( this.getInstAreaId() >>> 32 ) );
        result = prime * result + (int) ( this.getInstLocalityId() ^ ( this.getInstLocalityId() >>> 32 ) );
        result = prime * result + (int) ( this.getInstSocietyId() ^ ( this.getInstSocietyId() >>> 32 ) );
        result = prime * result + ( ( this.getLandmark() == null ) ? 0 : this.getLandmark().hashCode() );
        result = prime * result + (int) ( this.getPincode() ^ ( this.getPincode() >>> 32 ) );
        result = prime * result + ( ( this.getPropertyDetails() == null ) ? 0 : this.getPropertyDetails().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        return result;
    }

    public boolean CrmAddressDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmAddressDetailsPojo other = (CrmAddressDetailsPojo) obj;
        if ( this.getAddLine1() == null )
        {
            if ( other.getAddLine1() != null )
                return false;
        }
        else if ( !this.getAddLine1().equals( other.getAddLine1() ) )
            return false;
        if ( this.getAddLine2() == null )
        {
            if ( other.getAddLine2() != null )
                return false;
        }
        else if ( !this.getAddLine2().equals( other.getAddLine2() ) )
            return false;
        if ( this.getAddLine3() == null )
        {
            if ( other.getAddLine3() != null )
                return false;
        }
        else if ( !this.getAddLine3().equals( other.getAddLine3() ) )
            return false;
        if ( this.getAddressType() == null )
        {
            if ( other.getAddressType() != null )
                return false;
        }
        else if ( !this.getAddressType().equals( other.getAddressType() ) )
            return false;
        if ( this.getContactNo() != other.getContactNo() )
            return false;
        if ( this.getInstAreaId() != other.getInstAreaId() )
            return false;
        if ( this.getInstLocalityId() != other.getInstLocalityId() )
            return false;
        if ( this.getInstSocietyId()  != other.getInstSocietyId() )
            return false;
        if ( this.getLandmark() == null )
        {
            if ( other.getLandmark() != null )
                return false;
        }
        else if ( !this.getLandmark().equals( other.getLandmark() ) )
            return false;
        if ( this.getPincode() != other.getPincode() )
            return false;
        if ( this.getPropertyDetails() == null )
        {
            if ( other.getPropertyDetails() != null )
                return false;
        }
        else if ( !this.getPropertyDetails().equals( other.getPropertyDetails() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        return true;
    }
}
