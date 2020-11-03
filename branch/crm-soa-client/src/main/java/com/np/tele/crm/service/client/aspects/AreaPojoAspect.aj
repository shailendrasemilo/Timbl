package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.AreaPojo;

public aspect AreaPojoAspect
{
    public int AreaPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getAreaId() ^ ( this.getAreaId() >>> 32 ) );
        return result;
    }

    public boolean AreaPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        AreaPojo other = (AreaPojo) obj;
        if ( this.getAreaId() != other.getAreaId() )
            return false;
        return true;
    }

    public String AreaPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "AreaPojo [area=" ).append( this.getArea() ).append( ", areaId=" ).append( this.getAreaId() )
                .append( "]" );
        return builder.toString();
    }
}
