package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.utils.StringUtils;

public aspect CrmCustomerDetailsAspect
{
    public String CrmCustomerDetailsPojo.getCustName()
    {
        return this.getCustFname() + " " + StringUtils.trimToEmpty( this.getCustLname() );
    }

    public int CrmCustomerDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAuthSignDesg() == null ) ? 0 : this.getAuthSignDesg().hashCode() );
        result = prime * result + ( ( this.getAuthSignFname() == null ) ? 0 : this.getAuthSignFname().hashCode() );
        result = prime * result + ( ( this.getAuthSignLname() == null ) ? 0 : this.getAuthSignLname().hashCode() );
        result = prime * result + ( ( this.getConnectionType() == null ) ? 0 : this.getConnectionType().hashCode() );
        result = prime * result + ( ( this.getCrfDate() == null ) ? 0 : this.getCrfDate().hashCode() );
        result = prime * result + ( ( this.getCrfId() == null ) ? 0 : this.getCrfId().hashCode() );
        result = prime * result + ( ( this.getCrfStage() == null ) ? 0 : this.getCrfStage().hashCode() );
        result = prime * result + ( ( this.getCustDob() == null ) ? 0 : this.getCustDob().hashCode() );
        result = prime * result + ( ( this.getCustEmailId() == null ) ? 0 : this.getCustEmailId().hashCode() );
        result = prime * result + ( ( this.getCustFname() == null ) ? 0 : this.getCustFname().hashCode() );
        result = prime * result + ( ( this.getCustGender() == null ) ? 0 : this.getCustGender().hashCode() );
        result = prime * result + ( ( this.getCustLname() == null ) ? 0 : this.getCustLname().hashCode() );
        result = prime * result + (int) ( this.getCustMobileNo() ^ ( this.getCustMobileNo() >>> 32 ) );
        result = prime * result + ( ( this.getCustPanGirNo() == null ) ? 0 : this.getCustPanGirNo().hashCode() );
        result = prime * result + ( ( this.getCustomerId() == null ) ? 0 : this.getCustomerId().hashCode() );
        result = prime * result + ( ( this.getFhFname() == null ) ? 0 : this.getFhFname().hashCode() );
        result = prime * result + ( ( this.getFhLname() == null ) ? 0 : this.getFhLname().hashCode() );
        result = prime * result + ( ( this.getNationality() == null ) ? 0 : this.getNationality().hashCode() );
        result = prime * result + ( ( this.getOrgCordFname() == null ) ? 0 : this.getOrgCordFname().hashCode() );
        result = prime * result + ( ( this.getOrgCordLname() == null ) ? 0 : this.getOrgCordLname().hashCode() );
        result = prime * result + ( ( this.getProduct() == null ) ? 0 : this.getProduct().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + (int) ( this.getRmn() ^ ( this.getRmn() >>> 32 ) );
        result = prime * result + (int) ( this.getRtn() ^ ( this.getRtn() >>> 32 ) );
        result = prime * result + ( ( this.getStatus() == null ) ? 0 : this.getStatus().hashCode() );
        return result;
    }

    public boolean CrmCustomerDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCustomerDetailsPojo other = (CrmCustomerDetailsPojo) obj;
        if ( this.getAuthSignDesg() == null )
        {
            if ( other.getAuthSignDesg() != null )
                return false;
        }
        else if ( !this.getAuthSignDesg().equals( other.getAuthSignDesg() ) )
            return false;
        if ( this.getAuthSignFname() == null )
        {
            if ( other.getAuthSignFname() != null )
                return false;
        }
        else if ( !this.getAuthSignFname().equals( other.getAuthSignFname() ) )
            return false;
        if ( this.getAuthSignLname() == null )
        {
            if ( other.getAuthSignLname() != null )
                return false;
        }
        else if ( !this.getAuthSignLname().equals( other.getAuthSignLname() ) )
            return false;
        if ( this.getConnectionType() == null )
        {
            if ( other.getConnectionType() != null )
                return false;
        }
        else if ( !this.getConnectionType().equals( other.getConnectionType() ) )
            return false;
        if ( this.getCrfDate() == null )
        {
            if ( other.getCrfDate() != null )
                return false;
        }
        else if ( !this.getCrfDate().equals( other.getCrfDate() ) )
            return false;
        if ( this.getCrfId() == null )
        {
            if ( other.getCrfId() != null )
                return false;
        }
        else if ( !this.getCrfId().equals( other.getCrfId() ) )
            return false;
        if ( this.getCrfStage() == null )
        {
            if ( other.getCrfStage() != null )
                return false;
        }
        else if ( !this.getCrfStage().equals( other.getCrfStage() ) )
            return false;
        if ( this.getCustDob() == null )
        {
            if ( other.getCustDob() != null )
                return false;
        }
        else if ( !this.getCustDob().equals( other.getCustDob() ) )
            return false;
        if ( this.getCustEmailId() == null )
        {
            if ( other.getCustEmailId() != null )
                return false;
        }
        else if ( !this.getCustEmailId().equals( other.getCustEmailId() ) )
            return false;
        if ( this.getCustFname() == null )
        {
            if ( other.getCustFname() != null )
                return false;
        }
        else if ( !this.getCustFname().equals( other.getCustFname() ) )
            return false;
        if ( this.getCustGender() == null )
        {
            if ( other.getCustGender() != null )
                return false;
        }
        else if ( !this.getCustGender().equals( other.getCustGender() ) )
            return false;
        if ( this.getCustLname() == null )
        {
            if ( other.getCustLname() != null )
                return false;
        }
        else if ( !this.getCustLname().equals( other.getCustLname() ) )
            return false;
        if ( this.getCustMobileNo() != other.getCustMobileNo() )
            return false;
        if ( this.getCustPanGirNo() == null )
        {
            if ( other.getCustPanGirNo() != null )
                return false;
        }
        else if ( !this.getCustPanGirNo().equals( other.getCustPanGirNo() ) )
            return false;
        if ( this.getCustomerId() == null )
        {
            if ( other.getCustomerId() != null )
                return false;
        }
        else if ( !this.getCustomerId().equals( other.getCustomerId() ) )
            return false;
        if ( this.getFhFname() == null )
        {
            if ( other.getFhFname() != null )
                return false;
        }
        else if ( !this.getFhFname().equals( other.getFhFname() ) )
            return false;
        if ( this.getFhLname() == null )
        {
            if ( other.getFhLname() != null )
                return false;
        }
        else if ( !this.getFhLname().equals( other.getFhLname() ) )
            return false;
        if ( this.getNationality() == null )
        {
            if ( other.getNationality() != null )
                return false;
        }
        else if ( !this.getNationality().equals( other.getNationality() ) )
            return false;
        if ( this.getOrgCordFname() == null )
        {
            if ( other.getOrgCordFname() != null )
                return false;
        }
        else if ( !this.getOrgCordFname().equals( other.getOrgCordFname() ) )
            return false;
        if ( this.getOrgCordLname() == null )
        {
            if ( other.getOrgCordLname() != null )
                return false;
        }
        else if ( !this.getOrgCordLname().equals( other.getOrgCordLname() ) )
            return false;
        if ( this.getProduct() == null )
        {
            if ( other.getProduct() != null )
                return false;
        }
        else if ( !this.getProduct().equals( other.getProduct() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        if ( this.getRmn() != other.getRmn() )
            return false;
        if ( this.getRtn() != other.getRtn() )
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
