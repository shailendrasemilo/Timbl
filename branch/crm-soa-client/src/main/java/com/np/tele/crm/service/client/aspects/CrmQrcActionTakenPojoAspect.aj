package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;

public aspect CrmQrcActionTakenPojoAspect
{
    public int CrmQrcActionTakenPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        if ( this.getActionId() > 0 )
        {
            result = prime * result + (int) ( this.getActionId() ^ ( this.getActionId() >>> 32 ) );
        }
        else
        {
            result = prime * result + (int) ( this.getQrcCategoryId() ^ ( this.getQrcCategoryId() >>> 32 ) );
            result = prime * result + ( ( this.getServiceName() == null ) ? 0 : this.getServiceName().hashCode() );
            result = prime * result + ( ( this.getActionTaken() == null ) ? 0 :this.getActionTaken().hashCode() );
        }
        return result;
    }

    public boolean CrmQrcActionTakenPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmQrcActionTakenPojo other = (CrmQrcActionTakenPojo) obj;
        if ( this.getActionId() > 0 )
        {
            if ( this.getActionId() != other.getActionId() )
                return false;
        }
        else
        {
            if ( this.getQrcCategoryId() != other.getQrcCategoryId() )
                return false;
            if ( this.getServiceName() == null )
            {
                if ( other.getServiceName() != null )
                    return false;
            }
            else if ( !this.getServiceName().equals( other.getServiceName() ) )
                return false;
                if (this.getActionTaken() == null )
        {
            if ( other.getActionTaken() != null )
                return false;
        }
        else if ( !this.getActionTaken().equals( other.getActionTaken() ) )
            return false;
        }
        return true;
    }
}
