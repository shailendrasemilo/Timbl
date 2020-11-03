package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmHolidayDetails;

public aspect CrmHolidayDetailsAspect
{
    public int CrmHolidayDetails.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getHolidayDate() == null ) ? 0 : this.getHolidayDate().hashCode() );
        return result;
    }

    public boolean CrmHolidayDetails.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmHolidayDetails other = (CrmHolidayDetails) obj;
        if ( this.getHolidayDate() == null )
        {
            if ( other.getHolidayDate() != null )
                return false;
        }
        else if ( !this.getHolidayDate().equals( other.getHolidayDate() ) )
            return false;
        return true;
    }
}
