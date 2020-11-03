package com.np.tele.crm.service.client.aspects;

import java.util.Calendar;

import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SLACalculationUtil;
import com.np.tele.crm.utils.StringUtils;

public aspect CrmSrTicketsPojoAspect
{
    public String CrmSrTicketsPojo.getColor()
    {
        if ( StringUtils.equals( CRMStatusCode.OPEN.getStatusCode(), this.getStatus() )
                || StringUtils.equals( CRMStatusCode.REOPEN.getStatusCode(), this.getStatus() ) )
        {
            if ( StringUtils.isValidObj( this.getExpectedSLATime() )
                    && Calendar.getInstance().getTime()
                            .after( DateUtils.convertXmlCalToDate( this.getExpectedSLATime() ) ) )
            {
                return "color_SLA";
            }
        }
        return "color_" + this.getStatus();
    }

    /**
     * 
     * @return css class if Ticket is Out of SLA.
     */
    public String CrmSrTicketsPojo.getSlaColor()
    {
        if ( StringUtils.equals( CRMStatusCode.OPEN.getStatusCode(), this.getStatus() )
                || StringUtils.equals( CRMStatusCode.REOPEN.getStatusCode(), this.getStatus() ) )
        {
            if ( StringUtils.isValidObj( this.getExpectedSLATime() )
                    && Calendar.getInstance().getTime()
                            .after( DateUtils.convertXmlCalToDate( this.getExpectedSLATime() ) ) )
            {
                return "color_SLA";
            }
        }
        return "";
    }

    public String CrmSrTicketsPojo.getSlaCalculateTime()
    {
        if ( this.getSlaTime() > 0 )
        {
            return SLACalculationUtil.getDurationBreakdown( this.getSlaTime() );
        }
        return "";
    }

    public int CrmSrTicketsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getTicketId() ^ ( this.getTicketId() >>> 32 ) );
        return result;
    }

    public boolean CrmSrTicketsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmSrTicketsPojo other = (CrmSrTicketsPojo) obj;
        if ( this.getTicketId() != other.getTicketId() )
            return false;
        return true;
    }
}
