package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.SmsGatewayPojo;

public aspect SmsGatewayAspect
{
    public int SmsGatewayPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAlias() == null ) ? 0 : this.getAlias().hashCode() );
        return result;
    }

    public boolean SmsGatewayPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        SmsGatewayPojo other = (SmsGatewayPojo) obj;
        if ( this.getAlias() == null )
        {
            if ( other.getAlias() != null )
                return false;
        }
        else if ( !this.getAlias().equals( other.getAlias() ) )
            return false;
        return true;
    }

    public String SmsGatewayPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "SmsGatewayPojo [alias=" ).append( this.getAlias() ).append( ", category=" ).append( this.getCategory() )
                .append( ", enable=" ).append( this.isEnable()).append( ", response=" ).append( this.getResponse() )
                .append( ", subCategory=" ).append( this.getSubCategory() ).append( ", url=" ).append( this.getUrl() ).append( "]" );
        return builder.toString();
    }
}
