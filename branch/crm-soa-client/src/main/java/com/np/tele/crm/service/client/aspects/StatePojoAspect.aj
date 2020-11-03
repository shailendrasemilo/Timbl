package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.StatePojo;

public aspect StatePojoAspect
{
    public int StatePojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getStateId() ^ ( this.getStateId() >>> 32 ) );
        return result;
    }

    public boolean StatePojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        StatePojo other = (StatePojo) obj;
        if ( this.getStateId() != other.getStateId() )
            return false;
        return true;
    }

    public String StatePojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "StatePojo [cities=" ).append( this.getCities() ).append( ", editable=" )
                .append( this.isEditable() ).append( ", stateAlias=" ).append( this.getStateAlias() )
                .append( ", stateId=" ).append( this.getStateId() ).append( ", stateName=" )
                .append( this.getStateName() ).append( ", status=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
