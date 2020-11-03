package com.np.lms.rest.dto;

import java.util.List;

import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class LeadResponsedto
{
    private String        statusCode;
    private String        statusDesc;
    private RemarksPojo   remarksPojo;
    private LMSPojo       leadPojo;
    private List<LMSPojo> successInsertPojos;
    List<LMSPojo>         leadPojos;

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public LMSPojo getLeadPojo()
    {
        return leadPojo;
    }

    public void setLeadPojo( LMSPojo inLeadPojo )
    {
        leadPojo = inLeadPojo;
    }

    public List<LMSPojo> getSuccessInsertPojos()
    {
        return successInsertPojos;
    }

    public void setSuccessInsertPojos( List<LMSPojo> inSuccessInsertPojos )
    {
        successInsertPojos = inSuccessInsertPojos;
    }

    public List<LMSPojo> getLeadPojos()
    {
        return leadPojos;
    }

    public void setLeadPojos( List<LMSPojo> inLeadPojos )
    {
        leadPojos = inLeadPojos;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "LeadResponsedto [statusCode=" ).append( statusCode ).append( ", statusDesc=" )
                .append( statusDesc ).append( ", remarksPojo=" ).append( remarksPojo ).append( ", leadPojo=" )
                .append( leadPojo ).append( ", successInsertPojos=" ).append( successInsertPojos )
                .append( ", leadPojos=" ).append( leadPojos ).append( "]" );
        return builder.toString();
    }

   
}
