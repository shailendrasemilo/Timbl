package com.np.tele.crm.service.client.aspects;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmMassOutageMastersPojo;
import com.np.tele.crm.service.client.CrmMassOutagePartnerPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public aspect CrmMassOutagePojoAspect
{
    public String CrmMassOutagePojo.getDownTime()
    {
        StringBuilder sb = new StringBuilder();
        long totalSeconds = 0;
        if ( StringUtils.isValidObj( this.getResolutionTime() ) && StringUtils.isValidObj( this.getOutageStartTime() ) )
        {
            Date startTime = DateUtils.convertXmlCalToDate( this.getOutageStartTime() );
            Date resolutionTime = DateUtils.convertXmlCalToDate( this.getResolutionTime() );
            if ( startTime.compareTo( resolutionTime ) > 0 )
            {
                return sb.append( "Canceled Outage before start" ).toString();
            }
            totalSeconds = ( resolutionTime.getTime() - startTime.getTime() ) / 1000;
            //int day = (int) TimeUnit.SECONDS.toDays( totalSeconds );
            //long hours = TimeUnit.SECONDS.toHours( totalSeconds ) - ( day * 24 );
            long hours = TimeUnit.SECONDS.toHours( totalSeconds );
            long minute = TimeUnit.SECONDS.toMinutes( totalSeconds ) - ( TimeUnit.SECONDS.toHours( totalSeconds ) * 60 );
            long second = TimeUnit.SECONDS.toSeconds( totalSeconds )
                    - ( TimeUnit.SECONDS.toMinutes( totalSeconds ) * 60 );
            /* if ( day > 0 )
             {
                 sb.append( day ).append( "Day" ).append( IAppConstants.SPACE );
             }*/
            if ( hours > 0 )
            {
                //sb.append( hours ).append( "Hour" ).append( IAppConstants.SPACE );
                sb.append( hours ).append( ":" );
            }
            else
            {
                sb.append( "00" ).append( ":" );
            }
            if ( minute > 0 )
            {
                //sb.append( hours ).append( "Hour" ).append( IAppConstants.SPACE );
                sb.append( minute ).append( ":" );
            }
            else
            {
                sb.append( "00" ).append( ":" );
            }
            if ( second > 0 )
            {
                //sb.append( hours ).append( "Hour" ).append( IAppConstants.SPACE );
                sb.append( second );
            }
            else
            {
                sb.append( "00" );
            }
            //  sb.append( minute ).append( "Minute" ).append( IAppConstants.SPACE ).append( second ).append( "Seconds" );
            System.out.println( "String ::" + sb.toString() );
            return sb.toString();
        }
        return "";
    }

    public String CrmMassOutagePojo.getPartnerNames()
    {
        StringBuilder sb = new StringBuilder();
        List<CrmMassOutagePartnerPojo> data = this.getCrmMassOutagePartners();
        for ( CrmMassOutagePartnerPojo pojo : data )
        {
            sb.append( IAppConstants.COMMA ).append( IAppConstants.SPACE ).append( pojo.getPartnerName() );
        }
        if ( sb.length() > 1 )
        {
            sb.deleteCharAt( 0 );
        }
        return sb.toString();
    }

    public String CrmMassOutagePojo.getMasterNames()
    {
        StringBuilder sb = new StringBuilder();
        List<CrmMassOutageMastersPojo> data = this.getCrmMassOutageMastersPojos();
        for ( CrmMassOutageMastersPojo pojo : data )
        {
            sb.append( IAppConstants.COMMA ).append( IAppConstants.SPACE ).append( pojo.getMasterName() );
        }
        if ( sb.length() > 1 )
        {
            sb.deleteCharAt( 0 );
        }
        return sb.toString();
    }

    public String CrmMassOutagePojo.getCustomerId()
    {
        StringBuilder sb = new StringBuilder();
        List<String> data = this.getCustomerList();
        for ( String customer : data )
        {
            if ( StringUtils.isNotBlank( customer ) )
            {
                sb.append( IAppConstants.COMMA ).append( IAppConstants.SPACE ).append( customer );
            }
        }
        if ( sb.length() > 1 )
        {
            sb.deleteCharAt( 0 );
        }
        return sb.toString();
    }
}
