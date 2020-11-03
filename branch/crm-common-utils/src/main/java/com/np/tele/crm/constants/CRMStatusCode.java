package com.np.tele.crm.constants;


public enum CRMStatusCode {
    //  Codes for User Management 001 - 020
    NEW("N", "New","UserStatus"), 
    PENDING("P", "Pending","UserStatus,BillingStatus"), 
    ACTIVATION_PENDING("AP", "Activation Pending","CustomerStatus"), 
    ACTIVE("A", "Active","UserStatus,AciveInactiveStatus,PartialStatus,CustomerStatus,LeadStatus"), 
    LOCK("L", "Lock","UserStatus"),  
    EXPIRE("E", "Expire","UserStatus"),  
    INACTIVE("I", "Inactive","UserStatus,AciveInactiveStatus,PartialStatus,LeadStatus"),  
    DELETE("D", "Deleted","UserStatus,PartialStatus"),  
    MATURE("M", "Matured","LeadStatus"),  
    DEPOSIT("DP","Deposit","CMSFileStatus"), 
    CLEARANCE("CL","Clearance","CMSFileStatus"), 
    UNKNOWN("UN","Unknown","CMSFileStatus"), 
    ARCHIVE("AR","Archive","CMSFileStatus"), 
    SUCCESS("S","Success","CMSFileStatus,PaymentStatus"), 
    FAILURE("F","Failure","CMSFileStatus,PaymentStatus"),
    INVALID("IV","Invalid","CMSFileStatus"),
    REJECTED("RJ","Rejected","CMSRecordStatus,WaiverStatus"), 
    SUSPENCE("SP","Suspense","CMSRecordStatus"),
    BARRED("B","Barred","CustomerStatus,ManageStatus"),
    TDC("T","Temporary Disconnected","CustomerStatus,ManageStatus"),
    SC("SC","Safe Custody","CustomerStatus,ManageStatus"),
    PDC("PD","Permanent Disconnected","CustomerStatus,ManageStatus"),
    CLOSE("C","Closed","Close,LeadStatus,qrcTicketStatus"),
    UNBARRED("U","Unbarred","CustomerStatus,ManageStatus"),
    COMPLETED("CP","Completed","BillingStatus"),
    PAYMENT_POSTED("PP","Payment Posted","BillingStatus"),
    PAYMENT_REVERSAL("PR","Payment Reversed","BillingStatus"),
    OPEN("O","Open","qrcTicketStatus,WaiverStatus"),
    RESOLVED("R","Resolved","qrcTicketStatus"),    
    REOPEN("RO","Reopen","qrcTicketStatus"),
    USED("US","Used","MasterStatus"),
    UNUSED("UU","Unused","MasterStatus"),
    WAIVER_POSTED("WP","Waiver Posted","WaiverStatus"),
    WAIVER_APPROVE("WA","Waiver Approve","WaiverStatus"),
    DELIVER("D","Delivered","PODStatus"),
    NOT_DELIVER("ND","Not Delivered","PODStatus"),
    ATTEMPTED("AT","Attempted","PaymentStatus"),
    INPROCESS("IP","In Process","WorkflowStatus"),
    PROCESSED("PS","Processed","WorkflowStatus"),
    CANCELED("CN","Canceled",""),
    MATCHED("M","Matched","")
    ;
    
    String statusCode = null;
    String statusDesc = null;
    String module     = null;

    private CRMStatusCode( String statusCode, String statusDesc, String inModule )
    {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
        this.module = inModule;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public String getModule()
    {
        return module;
    }

    public static String getStatus( String inStatusCode, String inModule )
    {
        String status = null;
        for ( CRMStatusCode crmStatusCode : CRMStatusCode.values() )
        {
            if ( crmStatusCode.getStatusCode().equals( inStatusCode )
                    && crmStatusCode.getModule().contains( inModule ) )
            {
                status = crmStatusCode.getStatusDesc();
                break;
            }
        }
        return status;
    }

    public static CRMStatusCode getCRMStatus( String inStatusCode, String inModule )
    {
        for ( CRMStatusCode crmStatusCode : CRMStatusCode.values() )
        {
            if ( crmStatusCode.getStatusCode().equals( inStatusCode )
                    && crmStatusCode.getModule().contains( inModule ))
            {
                return crmStatusCode;
            }
        }
        return null;
    }

    public static String getStatus( String inStatusCode )
    {
        String status = null;
        for ( CRMStatusCode crmStatusCode : CRMStatusCode.values() )
        {
            if ( crmStatusCode.getStatusCode().equals( inStatusCode ) )
            {
                status = crmStatusCode.getStatusDesc();
                break;
            }
        }
        return status;
    }
}
