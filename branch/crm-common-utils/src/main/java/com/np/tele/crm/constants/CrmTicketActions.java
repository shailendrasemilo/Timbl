package com.np.tele.crm.constants;

public enum CrmTicketActions {
    ROL(0, "ROL"), FORWARD(1, "Forward"), BOTH(2, "Both");
    ;
    private byte   code;
    private String desc;

    private CrmTicketActions( int inCode, String inDesc )
    {
        code = (byte) inCode;
        desc = inDesc;
    }

    public byte getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }

    public static CrmTicketActions getTicketActions( byte code )
    {
        CrmTicketActions crmTicketAction = null;
        for ( CrmTicketActions ticketAction : CrmTicketActions.values() )
        {
            if ( ticketAction.getCode() == code )
            {
                crmTicketAction = ticketAction;
            }
        }
        return crmTicketAction;
    }
}
