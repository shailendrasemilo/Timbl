package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmOrderDetailsPojo;

public aspect CrmOrderDetailsAspect
{
    public int CrmOrderDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getCpeStatus() == null ) ? 0 : this.getCpeStatus().hashCode() );
        result = prime * result + (int) ( this.getCustomerRecordId() ^ ( this.getCustomerRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getDeviceDetail() == null ) ? 0 : this.getDeviceDetail().hashCode() );
        result = prime * result + ( ( this.getReceiverDetail() == null ) ? 0 : this.getReceiverDetail().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getServices() == null ) ? 0 : this.getServices().hashCode() );
        return result;
    }

    public boolean CrmOrderDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmOrderDetailsPojo other = (CrmOrderDetailsPojo) obj;
        if ( this.getCpeStatus() == null )
        {
            if ( other.getCpeStatus() != null )
                return false;
        }
        else if ( !this.getCpeStatus().equals( other.getCpeStatus() ) )
            return false;
        if ( this.getCustomerRecordId() != other.getCustomerRecordId() )
            return false;
        if ( this.getDeviceDetail() == null )
        {
            if ( other.getDeviceDetail() != null )
                return false;
        }
        else if ( !this.getDeviceDetail().equals( other.getDeviceDetail() ) )
            return false;
        if ( this.getReceiverDetail() == null )
        {
            if ( other.getReceiverDetail() != null )
                return false;
        }
        else if ( !this.getReceiverDetail().equals( other.getReceiverDetail() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        if ( this.getServices() == null )
        {
            if ( other.getServices() != null )
                return false;
        }
        else if ( !this.getServices().equals( other.getServices() ) )
            return false;
        return true;
    }
}
