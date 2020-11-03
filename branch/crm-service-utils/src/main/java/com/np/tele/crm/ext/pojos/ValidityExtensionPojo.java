package com.np.tele.crm.ext.pojos;

public class ValidityExtensionPojo
    implements java.io.Serializable, Cloneable
{
    private String customerId;
    private String extensionDays;
    private String ticketId;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getExtensionDays()
    {
        return extensionDays;
    }

    public void setExtensionDays( String inExtensionDays )
    {
        extensionDays = inExtensionDays;
    }

    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId( String inTicketId )
    {
        ticketId = inTicketId;
    }
}
