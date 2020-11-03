package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.utils.StringUtils;

public aspect SocietyPojoAspect
{
    public String SocietyPojo.getSearchName()
    {
        String value = "";
        if ( StringUtils.isNotEmpty( this.getLocalityName() ) && StringUtils.isNotEmpty( this.getSocietyName() ) )
        {
            value = this.getLocalityName() + IAppConstants.SPACE + "-" + IAppConstants.SPACE + this.getSocietyName();
        }
        else if ( StringUtils.isNotEmpty( this.getLocalityName() ) )
        {
            value = this.getLocalityName();
        }
        else if ( StringUtils.isNotEmpty( this.getSocietyName() ) )
        {
            value = this.getSocietyName();
        }
        return value;
    }

    public int SocietyPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getSocietyId() ^ ( this.getSocietyId() >>> 32 ) );
        return result;
    }

    public boolean SocietyPojo.equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        SocietyPojo other = (SocietyPojo) obj;
        if ( this.getSocietyId() != other.getSocietyId() )
        {
            return false;
        }
        return true;
    }

    public String SocietyPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "SocietyPojo [balanceDus=" ).append( ", societyName=" ).append( this.getSocietyName() )
                .append( ", customerCategory=" ).append( this.getCustomerCategory() ).append( ", dusConnectable3Days=" )
                .append( ", dusConnectable7Days=" ).append( ", latitude=" ).append( this.getLatitude() )
                .append( ", longitude=" ).append( this.getLongitude() ).append( ", networkAvailability=" )
                .append( this.getNetworkAvailability() ).append( ", noOfDus=" ).append( ", rfsDus=" )
                .append( this.getRfsDus() ).append( ", societyId=" ).append( this.getSocietyId() ).append( ", status=" )
                .append( this.getStatus() ).append( ", towerConnectable3Days=" ).append( ", towerConnectable7Days=" )
                .append( "]" );
        return builder.toString();
    }
}
