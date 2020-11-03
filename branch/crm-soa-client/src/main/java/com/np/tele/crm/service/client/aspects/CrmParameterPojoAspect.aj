package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmParameterPojo;

public aspect CrmParameterPojoAspect
{
    public int CrmParameterPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getParameterId() ^ ( this.getParameterId() >>> 32 ) );
        result = prime * result + ( ( this.getParameterName() == null ) ? 0 : this.getParameterName().hashCode() );
        result = prime * result + ( ( this.getStatus() == null ) ? 0 : this.getStatus().hashCode() );
        result = prime * result + (int) ( this.getParameterLength() ^ ( this.getParameterLength() >>> 32 ) );
        result = prime * result + ( ( this.getParameterType() == null ) ? 0 : this.getParameterType().hashCode() );
        return result;
    }

    public boolean CrmParameterPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmParameterPojo other = (CrmParameterPojo) obj;
        if ( this.getParameterId() != other.getParameterId() )
            return false;
        if ( this.getParameterName() == null )
        {
            if ( other.getParameterName() != null )
                return false;
        }
        else if ( !this.getParameterName().equals( other.getParameterName() ) )
            return false;
        if ( this.getStatus() == null )
        {
            if ( other.getStatus() != null )
                return false;
        }
        else if ( !this.getStatus().equals( other.getStatus() ) )
            return false;
        if ( this.getParameterLength() != other.getParameterLength() )
        {
            return false;
        }
        if ( this.getParameterType() == null )
        {
            if ( other.getParameterType() != null )
            {
                return false;
            }
        }
        else if ( !this.getParameterType().equals( other.getParameterType() ) )
        {
            return false;
        }
        return true;
    }

    public String CrmParameterPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmParameterPojo [parameterId=" ).append( this.getParameterId() ).append( ", parameterName=" )
                .append( this.getParameterName() ).append( ", status=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
