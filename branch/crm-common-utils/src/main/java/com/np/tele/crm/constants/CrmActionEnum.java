package com.np.tele.crm.constants;

public enum CrmActionEnum {
    GENERATED("GN","Generated","LMS"),
    FORWARDED("FW","Forwarded","CAF,QRC,LMS"),
    REJECTED("RJ","Rejected","CAF,LMS"),
    MATURED("MT","Matured","LMS"),
    CANCEL("CN","Cancel",""),
    REFUSAL("RF","Refusal",""),
    CUSTOMER_ACTIVATED("CA","Customer Activated",""),
    MAC_CHNAGE("MC","MAC Change",""),
    SAVED("SV","Saved",""),
    SAVED_REMARKS("SR","Remarks Saved","LMS,QRC"),
    RESOLVED("RV","Resolved","QRC"),
    FOLLOW_UP("FU","Follow up","QRC"),
    OPENED("OP","Opened","QRC"),
    REOPENED("RO","Reopened","QRC"),
    CLOSED("CL","Closed","QRC"),
    ASSIGNED("AS","Assigned","QRC"),
    UPDATED("UP","Updated","USER"), 
    PASSWORD("PC","Password","USER"), 
    ERP("ERP","ERP",""),
    FLAG_REMARKS("FR","Flag Remarks","LMS,QRC"),
    
    ;
    String actionCode = null;
    String actionName = null;
    String module     = null;

    private CrmActionEnum( String actionCode, String actionName, String module )
    {
        this.actionCode = actionCode;
        this.actionName = actionName;
        this.module = module;
    }

    public String getActionCode()
    {
        return actionCode;
    }

    public String getActionName()
    {
        return actionName;
    }

    public String getModule()
    {
        return module;
    }
}
