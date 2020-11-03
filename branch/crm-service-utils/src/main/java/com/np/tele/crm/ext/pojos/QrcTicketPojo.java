package com.np.tele.crm.ext.pojos;

public class QrcTicketPojo
{
    private String qrcEvent;
    private String reason;
    private String remarks;

    public String getQrcEvent()
    {
        return qrcEvent;
    }

    public void setQrcEvent( String inQrcEvent )
    {
        qrcEvent = inQrcEvent;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason( String inReason )
    {
        reason = inReason;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String inRemarks )
    {
        remarks = inRemarks;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "QrcTicketPojo [qrcEvent=" ).append( qrcEvent ).append( ", reason=" ).append( reason )
                .append( ", remarks=" ).append( remarks ).append( "]" );
        return builder.toString();
    }
}
