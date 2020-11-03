package com.np.tele.crm.constants;

public enum CRMRequestType {
    //  Codes for User Management 001 - 020
    CUSTOMER_ID("CUSTOMER_ID","Customer ID"),
    LEAD("lead", "Lead"), 
    USER("USER", "User"), 
    CAF("CAF", "CAF"),
    MASTER("MASTER", "Master"),
    PARTNER_MANAGEMENT("PARTNER_MANAGEMENT", "Partner Management"),
    ALERT("ALERT", "Alert"),
    LMS("LMS", "Lead Management System"),
    QRC("QRC","Query Request Complaint"),
    FINANCE("FINANCE","Finance"),
    ALL_INBOX("ALL_INBOX","ALL_INBOX"),
    INA("INA","Installation And Activation"),
    REQUEST("R","R"),
    QUERY("Q","Q"),
    COMPLAIN("C","C"),
    OTHERS("others","Others"),
    SELFCARE("SELFCARE","My Account"),
    PAYMENT_ID("PAYMENT_ID","Payment ID"),
    PGW_TRACK_ID("PGW_TRACK_ID","Payment Gateway Tracking ID"),
    CMS_FILE("CMS_FILE","CMS File"),
    SHIFTING("Shifting","Shifting"),
    WAIVER("Waiver","Waiver"),
    LINK_TO_CRF("LINK_TO_CRF","LINK_TO_CRF"),
    SLA("SLA","Service Level Agreement"),
    LEAD_USER("LeadUser", "Lead User"), 
    FREEZE("Freeze","Freeze"),
    MASS_OUTAGE("MASS_OUTAGE","Mass Outage"),
    LMS_TKT("LMS_TKT", "Lead Ticket"),
    MAC_ADDRESS("MAC_ADDRESS","MAC Address"),
    NETWORK_PARTNER("NETWORK PARTNER","FT APPROVAL NETWORK PARTNER")
    ;    
    
    String requestCode = null;
    String requestDesc = null;

    private CRMRequestType( String requestCode, String requestDesc )
    {
        this.requestCode = requestCode;
        this.requestDesc = requestDesc;
    }

    public String getRequestCode()
    {
        return requestCode;
    }

    public String getRequestDesc()
    {
        return requestDesc;
    }

    public static String getStatus( String inStatusCode )
    {
        String status = null;
        for ( CRMRequestType crmRequestType : CRMRequestType.values() )
        {
            if ( crmRequestType.getRequestCode().equals( inStatusCode ) )
            {
                status = crmRequestType.getRequestDesc();
                break;
            }
        }
        return status;
    }
    public static String getCode( String requestDesc )
    {
        String statusCode = null;
        for ( CRMRequestType crmRequestType : CRMRequestType.values() )
        {
            if ( crmRequestType.getRequestDesc().equals( requestDesc ) )
            {
                statusCode = crmRequestType.getRequestCode();
                break;
            }
        }
        return statusCode;
    }
    
    public static CRMRequestType getRequestType( String inStatusCode )
    {
        CRMRequestType requestType = null;
        for ( CRMRequestType crmRequestType : CRMRequestType.values() )
        {
            if ( crmRequestType.getRequestCode().equals( inStatusCode ) )
            {
                requestType = crmRequestType;
                break;
            }
        }
        return requestType;
    }
}
