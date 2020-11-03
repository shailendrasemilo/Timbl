package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;

public aspect CrmNationalityDetailsAspect
{
    public int CrmNationalityDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getCustomerRecordId() ^ ( this.getCustomerRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getLocalCpAdd() == null ) ? 0 : this.getLocalCpAdd().hashCode() );
        result = prime * result + ( ( this.getLocalCpFname() == null ) ? 0 : this.getLocalCpFname().hashCode() );
        result = prime * result + ( ( this.getLocalCpLandmark() == null ) ? 0 : this.getLocalCpLandmark().hashCode() );
        result = prime * result + ( ( this.getLocalCpLname() == null ) ? 0 : this.getLocalCpLname().hashCode() );
        result = prime * result + (int) ( this.getLocalCpMobileNo() ^ ( this.getLocalCpMobileNo() >>> 32 ) );
        result = prime * result + ( ( this.getPassportNo() == null ) ? 0 : this.getPassportNo().hashCode() );
        result = prime * result + ( ( this.getPassportValidity() == null ) ? 0 : this.getPassportValidity().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getVisaType() == null ) ? 0 : this.getVisaType().hashCode() );
        result = prime * result + ( ( this.getVisaValidity() == null ) ? 0 : this.getVisaValidity().hashCode() );
        return result;
    }

    public boolean CrmNationalityDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmNationalityDetailsPojo other = (CrmNationalityDetailsPojo) obj;
        if ( this.getCustomerRecordId() != other.getCustomerRecordId() )
            return false;
        if ( this.getLocalCpAdd() == null )
        {
            if ( other.getLocalCpAdd() != null )
                return false;
        }
        else if ( !this.getLocalCpAdd().equals( other.getLocalCpAdd() ) )
            return false;
        if ( this.getLocalCpFname() == null )
        {
            if ( other.getLocalCpFname() != null )
                return false;
        }
        else if ( !this.getLocalCpFname().equals( other.getLocalCpFname() ) )
            return false;
        if ( this.getLocalCpLandmark() == null )
        {
            if ( other.getLocalCpLandmark() != null )
                return false;
        }
        else if ( !this.getLocalCpLandmark().equals( other.getLocalCpLandmark() ) )
            return false;
        if ( this.getLocalCpLname() == null )
        {
            if ( other.getLocalCpLname() != null )
                return false;
        }
        else if ( !this.getLocalCpLname().equals( other.getLocalCpLname() ) )
            return false;
        if ( this.getLocalCpMobileNo() != other.getLocalCpMobileNo() )
            return false;
        if ( this.getPassportNo() == null )
        {
            if ( other.getPassportNo() != null )
                return false;
        }
        else if ( !this.getPassportNo().equals( other.getPassportNo() ) )
            return false;
        if ( this.getPassportValidity() == null )
        {
            if ( other.getPassportValidity() != null )
                return false;
        }
        else if ( !this.getPassportValidity().equals( other.getPassportValidity() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        if ( this.getVisaType() == null )
        {
            if ( other.getVisaType() != null )
                return false;
        }
        else if ( !this.getVisaType().equals( other.getVisaType() ) )
            return false;
        if ( this.getVisaValidity() == null )
        {
            if ( other.getVisaValidity() != null )
                return false;
        }
        else if ( !this.getVisaValidity().equals( other.getVisaValidity() ) )
            return false;
        return true;
    }
}
