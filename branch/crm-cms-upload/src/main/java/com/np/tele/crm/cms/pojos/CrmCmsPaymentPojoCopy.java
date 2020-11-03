package com.np.tele.crm.cms.pojos;

import com.np.tele.crm.service.client.CrmCmsPaymentPojo;

public class CrmCmsPaymentPojoCopy extends CrmCmsPaymentPojo
{
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( draweeBank == null ) ? 0 : draweeBank.hashCode() );
        result = prime * result + ( ( ie2 == null ) ? 0 : ie2.hashCode() );
        result = prime * result + ( ( instrumentAmount == null ) ? 0 : instrumentAmount.hashCode() );
        result = prime * result + ( ( instrumentNo == null ) ? 0 : instrumentNo.hashCode() );
        return result;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCmsPaymentPojoCopy other = (CrmCmsPaymentPojoCopy) obj;
        if ( draweeBank == null )
        {
            if ( other.draweeBank != null )
                return false;
        }
        else if ( !draweeBank.equals( other.draweeBank ) )
            return false;
        if ( ie2 == null )
        {
            if ( other.ie2 != null )
                return false;
        }
        else if ( !ie2.equals( other.ie2 ) )
            return false;
        if ( instrumentAmount == null )
        {
            if ( other.instrumentAmount != null )
                return false;
        }
        else if ( instrumentAmount.compareTo( other.instrumentAmount ) != 0 )
            return false;
        if ( instrumentNo == null )
        {
            if ( other.instrumentNo != null )
                return false;
        }
        else if ( !instrumentNo.equals( other.instrumentNo ) )
            return false;
        return true;
    }
}
