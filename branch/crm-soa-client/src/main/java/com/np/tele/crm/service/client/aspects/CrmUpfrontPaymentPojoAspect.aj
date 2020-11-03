package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;

public aspect CrmUpfrontPaymentPojoAspect
{
    public int CrmUpfrontPaymentPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        if ( this.getUpfrontId() > 0 )
        {
            result = prime * result + (int) ( this.getUpfrontId() ^ ( this.getUpfrontId() >>> 32 ) );
        }
        else
        {
            long temp;
            temp = Double.doubleToLongBits( this.getAmount() );
            result = prime * result + (int) ( temp ^ ( temp >>> 32 ) );
            result = prime * result + (int) ( this.getBankId() ^ ( this.getBankId() >>> 32 ) );
            result = prime * result + ( ( this.getChequeDate() == null ) ? 0 : this.getChequeDate().hashCode() );
            result = prime * result + ( ( this.getChequeNo() == null ) ? 0 : this.getChequeNo().hashCode() );
        }
        return result;
    }

    public boolean CrmUpfrontPaymentPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmUpfrontPaymentPojo other = (CrmUpfrontPaymentPojo) obj;
        if ( this.getUpfrontId() > 0 )
        {
            if ( this.getUpfrontId() != other.getUpfrontId() )
                return false;
        }
        else
        {
            if ( Double.doubleToLongBits( this.getAmount() ) != Double.doubleToLongBits( other.getAmount() ) )
            {
                return false;
            }
            if ( this.getBankId() != other.getBankId() )
                return false;
            if ( this.getChequeDate() == null )
            {
                if ( other.getChequeDate() != null )
                    return false;
            }
            else if ( !this.getChequeDate().equals( other.getChequeDate() ) )
                return false;
            if ( this.getChequeNo() == null )
            {
                if ( other.getChequeNo() != null )
                    return false;
            }
            else if ( !this.getChequeNo().equals( other.getChequeNo() ) )
                return false;
        }
        return true;
    }
}
