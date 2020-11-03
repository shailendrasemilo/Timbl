package com.np.tele.crm.service.client.aspects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.np.tele.crm.service.client.QrcTicketReportPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public aspect QrcTicketReportAspect
{

    public String QrcTicketReportPojo.getResolutionHours()
    {
        if ( StringUtils.isValidObj( this.getCreatedTime() ) && StringUtils.isValidObj( this.getSrResolvedOn() ) )
        {
            return String.valueOf( DateUtils.hourDiff( DateUtils.convertXmlCalToDate( this.getSrResolvedOn() ),
                                                       DateUtils.convertXmlCalToDate( this.getCreatedTime() ) ) );
        }
        return "";
    }

    public String QrcTicketReportPojo.getResolutionDayTime()
    {
        if ( StringUtils.isNotBlank( this.getResolutionTime() ) )
        {
            long millis = Long.parseLong( this.getResolutionTime() );
            return String.format( "%02d:%02d:%02d:%02d",
                                  TimeUnit.MILLISECONDS.toDays( millis ),
                                  TimeUnit.MILLISECONDS.toHours( millis )
                                          - TimeUnit.DAYS.toHours( TimeUnit.MILLISECONDS.toDays( millis ) ),
                                  TimeUnit.MILLISECONDS.toMinutes( millis )
                                          - TimeUnit.HOURS.toMinutes( TimeUnit.MILLISECONDS.toHours( millis ) ),
                                  TimeUnit.MILLISECONDS.toSeconds( millis )
                                          - TimeUnit.MINUTES.toSeconds( TimeUnit.MILLISECONDS.toMinutes( millis ) ) );
        }
        return "00:00:00:00";
    }

    public String QrcTicketReportPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "QrcTicketReportPojo [customerId=" ).append( this.getCustomerId() ).append( ", ticketId=" )
                .append( this.getTicketId() ).append( ", category=" ).append( this.getCategory() )
                .append( ", subCategory=" ).append( this.getSubCategory() ).append( ", currentBin=" )
                .append( this.getCurrentBin() ).append( ", srResolvedOn=" ).append( this.getSrResolvedOn() )
                .append( ", ticketStatus=" ).append( this.getTicketStatus() ).append( "]" );
        return builder.toString();
    }
}
