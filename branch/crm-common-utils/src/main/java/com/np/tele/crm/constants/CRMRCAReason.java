package com.np.tele.crm.constants;

public enum CRMRCAReason {
    LMS("category", "LMS","Lead Managment System","","LEAD"),
    INA("category", "INA","Installation & Activation","","INA"),
    ADS("category", "ADS","Address Shifting","","QRC"),
    CRM("category", "CRM","CRM","","ADMIN"),
    FINANCE("category", "FINANCE","Finance","","FINANCE"),
    QRC("category", "QRC","Query Request Complaints","","QRC"),
    WAIVER("category","WAIVER","Customer Waiver","","QRC"),
    CRM_MASTER("CRM","MASTER","Master","",""),
    LMS_REASON("LMS","REASON","Reason","",""),
    FINANCE_REASON("FINANCE","REASON","Reason","",""),
    REFUND_REASON("FINANCE_REASON","REFUND","Refund Reason","Configure list of reasons for Refund Rejected of Customer.",""),
    INA_REASON("INA","REASON","Reason","",""),
    QRC_REASON("QRC","REASON","Reason","",""),
    ADS_REASON("ADS","REASON","Reason","",""),
    QRC_BARRED("QRC_REASON","BARRING","Barring","Configure list of reasons for Barring Customer Service.",""),
    QRC_UNBARRED("QRC_REASON","UNBARRING","Unbarring","Configure list of reasons for Unbarring Customer Service.",""),
    QRC_WHITELIST_BARRED("QRC_REASON","BAREXLIST","Whitelist Barring","Configure list of reasons for Barring Exception List.",""),
    QRC_WHITELIST_UNBARRED("QRC_REASON","UNBAREXLIST","Whitelist Unbarring","Configure list of reasons for Unbarring Exception List.",""),
    QRC_TEMPORARY_DISCONNECTION("QRC_REASON","TDC","Temporary Disconnection","Configure list of reasons for Temporary Disconnection of Customer.",""),
    QRC_PERMANENT_DISCONNECTION("QRC_REASON","PDC","Permanent Disconnection","Configure list of reasons for Permanent Disconnection of Customer.",""),
    QRC_SAFECUSTODY("QRC_REASON","SAFECUSTODY","Safe Custody","Configure list of reasons for Safe Custody of Customer.",""),
    QRC_MASSOUTAGE("QRC_REASON","MASSOUTAGE","QRC Mass Outage","Configure list of reasons for QRC Mass Outage.",""),
    LMS_REASON_CLOSE("LMS_REASON","CLOSE","Close","Configure list of reasons for Closing a Lead.",""),
    INA_REASON_CANCEL("INA_REASON","CANCELLATION","Cancellation","Configure list of reasons for Cancel Installation & Activation.",""),
    INA_CUSTOMER_REFUSAL("INA_REASON","REFUSAL","Customer Refusal","Configure list of reasons for Customer Refusal of Installation & Activation.",""),
    INA_FT_REJECTION("INA_REASON","FTR","FT Rejection","Configure list of reasons for FT Rejection of Installation & Activation.",""),
    INA_NP_REJECTION("INA_REASON","NPR","NP Rejection","Configure list of reasons for NP Rejection of Installation & Activation.",""),
    INA_SP_REJECTION("INA_REASON","SPR","SP Rejection","Configure list of reasons for SP Rejection of Installation & Activation.",""),
    INA_ERP_REJECTION("INA_REASON","ERP","ERP Rejection","Configure list of reasons for ERP Rejection of Installation & Activation.",""),
    CRM_BANK("CRM_MASTER","BANK","Banks","Configure list of Banks to be displayed.",""),
    ADDRESS_SHIFTING_REASON_CLOSE("ADS_REASON","CLOSE","Close","Configure list of reasons for Closing of Address Shifting.",""),
   
  
    //FINANCE_CHECK_BOUNCING("FINANCE_REASON","CHECK_BOUNCING","Check Bouncing","",""),
    FINANCE_PAYMENT_REVERSAL("FINANCE_REASON","REVERSAL","Payment Reversal","Configure list of reasons for Payment Reversal.",""),
    FUNCTIONAL_BIN("CRM_MASTER","FB","Functional Bin","Configure list of Functional Bins.",""),
//    CONNECTION_TYPE("CRM_MASTER","CT","Connection Type","Description for CONNECTION_TYPE"),
//    NATIONALITY_TYPE("CRM_MASTER","Nationality","Nationality Type","Description for NATIONALITY_TYPE"),
    QRC_INTERACTION("QRC","INTERACTION","Interaction","",""),
    QRC_INTERACTION_COLLECTION("QRC_INTERACTION","COLLECTION","Collection","Configure list of reasons of Customer Interaction for Collection.",""),
    QRC_INTERACTION_RETENTION("QRC_INTERACTION","RETENTION","Retention","Configure list of reasons of Customer Interaction for Retention.",""),
    QRC_INTERACTION_QRC("QRC_INTERACTION","QRC","QRC","Configure list of reasons of Customer Interaction for QRC (Query Request Complaint).",""),
    WAIVER_REASON("WAIVER","REASON","Reason","Configure list of Customer Waiver Rejection Reasons.",""),
    WAIVER_REASON_REJECT("WAIVER_REASON","REJECT","Reject","",""),
    VALIDITY_EXTENSION_REASON("QRC_REASON","VALEXT","Validity Extension","Configure list of reasons for validity extension of Customer.",""),
    ;

    private String id;
    private String statusCode;
    private String statusDesc;
    private String description;
    private String moduleName;

    private CRMRCAReason( String inId, String inStatusCode, String inStatusDesc, String inDescription,String inModuleName  )
    {
        id = inId;
        statusCode = inStatusCode;
        statusDesc = inStatusDesc;
        description = inDescription;
        moduleName=inModuleName;
    }

    public String getId()
    {
        return id;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public String getDescription()
    {
        return description;
    }

    public String getModuleName()
    {
        return moduleName;
    }

    public void setModuleName( String inModuleName )
    {
        moduleName = inModuleName;
    }
    
}
