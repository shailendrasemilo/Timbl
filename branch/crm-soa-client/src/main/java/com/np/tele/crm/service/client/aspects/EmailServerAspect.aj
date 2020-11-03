package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.EmailServerPojo;

public aspect EmailServerAspect {
   
    public int EmailServerPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAlias() == null ) ? 0 : this.getAlias().hashCode() );
        return result;
    }

    
    public boolean EmailServerPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        EmailServerPojo other = (EmailServerPojo) obj;
        if ( this.getAlias() == null )
        {
            if ( other.getAlias() != null )
                return false;
        }
        else if ( !this.getAlias().equals( other.getAlias() ) )
            return false;
        return true;
    }

   
    public String EmailServerPojo.toString()
    {
        return "EmailServerPojo [alias=" + this.getAlias() + ", category=" + this.getCategory() + ", connectionTimeOut="
                + this.getConnectionTimeOut() + ", display=" + this.getDisplay() +", host=" + this.getHost() + ", port="
                + this.getPort() + ", replyTo=" + this.getReplyTo() + ", retryValue=" + this.getRetryValue() + ", socketTimeOut=" + this.getSocketTimeOut()
                + ", subCategory=" + this.getSubCategory() + ", userid=" + this.getUserid() + "]";
    }


}
