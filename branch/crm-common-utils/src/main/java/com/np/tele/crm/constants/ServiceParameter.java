package com.np.tele.crm.constants;

public enum ServiceParameter {
    CREATE("Create"), 
    MODIFY_STATUS("ModifyStatus"), 
    DELETE("Delete"), 
    LIST("List"), 
    MODIFY("Modify"), 
    UPDATE("Update"),
    SEARCH("search"), 
    VIEW("view"), 
    SAVE("save"), 
    FORWARD("forward"), 
    MATURE("mature"), 
    CLOSE("close"), 
    PARTNER_MAPPING("PartnerMapping"), 
    VALIDATE("Validate"),
   /* POJO_VALIDATION("PojoValidation"),
    FORM_VALIDATION("FormValidation"),*/
    SAVE_CRF_REFERENCE("SaveCRFReference"), 
    SAVE_ISR_REFERENCE("SaveISRReference"), 
    SAVE_NETWORK_CONFIGURATION("SaveNetworkConfiguration"),
    TRACK("track"),
    TRACKBYCUSTOMERID("trackByCustomerId"),
    PROCESS("process"),
    POST("post"),
    CHECK_DUPLICATE("duplicate"),
    MAPPING_LIST("mappinglist"),
    APICALL("APIcall"),
    SAVE_CUSTOMER_PROFILE_DETAILS("saveCustomerProfileDetails"),
    OUTSTANDING_NOTICE("OutstandingNotice"),
    RESET("Reset"),
    SELFCARE("Selfcare"),
    ACTIVATE("Activate"), 
    RENEW("Renew"),
    SHIFTING("shifting"),
    CANCEL("Cancel"),
    SLA_LMS("lmsSla"),
    SLA_INA("inaSla"),
    SLA_QRC("qrcSla"),
    CUSTOMER_LIST("customerList"),
    UPDATE_STATUS("UpdateStatus"),
    SEARCH_OUTAGE_SOCIETY("SearchOutageSociety")
    ;
    
    String parameter = null;

    private ServiceParameter( String parameter )
    {
        this.parameter = parameter;
    }

    public String getParameter()
    {
        return parameter;
    }
}
