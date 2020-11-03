package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmAdditionalDetailsPojo;

public aspect CrmAdditionalDetailsAspect
{
    public int CrmAdditionalDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getBankAccountNo() ^ ( this.getBankAccountNo() >>> 32 ) );
        result = prime * result + ( ( this.getBankAccountType() == null ) ? 0 : this.getBankAccountType().hashCode() );
        result = prime * result + ( ( this.getBankBranch() == null ) ? 0 : this.getBankBranch().hashCode() );
        result = prime * result + ( ( this.getBankName() == null ) ? 0 : this.getBankName().hashCode() );
        result = prime * result + (int) ( this.getCrcdCardNo() ^ ( this.getCrcdCardNo() >>> 32 ) );
        result = prime * result + ( ( this.getCrcdExpiryDate() == null ) ? 0 : this.getCrcdExpiryDate().hashCode() );
        result = prime * result + ( ( this.getCrcdType() == null ) ? 0 : this.getCrcdType().hashCode() );
        result = prime * result + (int) ( this.getCustomerRecordId() ^ ( this.getCustomerRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getMobileImeiNo() == null ) ? 0 : this.getMobileImeiNo().hashCode() );
        result = prime * result + ( ( this.getMobileMake() == null ) ? 0 : this.getMobileMake().hashCode() );
        result = prime * result + ( ( this.getMobileNo() == null ) ? 0 : this.getMobileNo().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getVehicleDetail() == null ) ? 0 : this.getVehicleDetail().hashCode() );
        return result;
    }

    public boolean CrmAdditionalDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmAdditionalDetailsPojo other = (CrmAdditionalDetailsPojo) obj;
        if ( this.getBankAccountNo() != other.getBankAccountNo() )
            return false;
        if ( this.getBankAccountType() == null )
        {
            if ( other.getBankAccountType() != null )
                return false;
        }
        else if ( !this.getBankAccountType().equals( other.getBankAccountType() ) )
            return false;
        if ( this.getBankBranch() == null )
        {
            if ( other.getBankBranch() != null )
                return false;
        }
        else if ( !this.getBankBranch().equals( other.getBankBranch() ) )
            return false;
        if ( this.getBankName() == null )
        {
            if ( other.getBankName() != null )
                return false;
        }
        else if ( !this.getBankName().equals( other.getBankName() ) )
            return false;
        if ( this.getCrcdCardNo() != other.getCrcdCardNo() )
            return false;
        if ( this.getCrcdExpiryDate() == null )
        {
            if ( other.getCrcdExpiryDate() != null )
                return false;
        }
        else if ( !this.getCrcdExpiryDate().equals( other.getCrcdExpiryDate() ) )
            return false;
        if ( this.getCrcdType() == null )
        {
            if ( other.getCrcdType() != null )
                return false;
        }
        else if ( !this.getCrcdType().equals( other.getCrcdType() ) )
            return false;
        if ( this.getCustomerRecordId() != other.getCustomerRecordId() )
            return false;
        if ( this.getMobileImeiNo() == null )
        {
            if ( other.getMobileImeiNo() != null )
                return false;
        }
        else if ( !this.getMobileImeiNo().equals( other.getMobileImeiNo() ) )
            return false;
        if ( this.getMobileMake() == null )
        {
            if ( other.getMobileMake() != null )
                return false;
        }
        else if ( !this.getMobileMake().equals( other.getMobileMake() ) )
            return false;
        if ( this.getMobileNo() == null )
        {
            if ( other.getMobileNo() != null )
                return false;
        }
        else if ( !this.getMobileNo().equals( other.getMobileNo() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        if ( this.getVehicleDetail() == null )
        {
            if ( other.getVehicleDetail() != null )
                return false;
        }
        else if ( !this.getVehicleDetail().equals( other.getVehicleDetail() ) )
            return false;
        return true;
    }
}
