package com.np.tele.crm.ext.pojos;

public class CustomerDetailsPojo
{
    private String status;
    private String remarks;

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
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
        builder.append( "CustomerDetailsPojo [status=" ).append( status ).append( ", remarks=" ).append( remarks )
                .append( "]" );
        return builder.toString();
    }
}
