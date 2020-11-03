package com.np.tele.crm.constants;

public enum CRMActionConstants {
    //FORWARD_FT("LMS","1","Forward To Fulfilment Team","FT"),
    FORWARD_AM("LMS","1,2,5","Forward To Area Manager","AM"),
    FORWARD_CH("LMS","3,4","Forward To Cluster Head","CLHD"),
    FORWARD_Sales("LMS","3","Forward To Sales","ST"),
    BACKWARD_SC("LMS","2,3,4","Back Route To Sales Coordinator","SC"),
    CLOSE_LEAD("LMS","1,3,4,5","Close Lead","CL"),
    MATURE_LEAD("LMS","3,4","Mature Lead","ML"),
    SAVE_LEAD("LMS","","Save Lead","SL"),
    CREATE_LEAD("LMS","","Create Lead","LC"),
    SAVE_REMARKS("QRC","","Save Remarks","SR"),
    SAVE_FLAG("QRC","","Save Flag","FR"),
    FORWARD("QRC","","Forward","FW"),
    RESOLVE("QRC","","Resolve","RV"),
    FOLLOW_UP("QRC","","Follow up","FU"),
    ;

    private String module;
    private String forStage;
    private String displayValue;
    private String storingValue;

    private CRMActionConstants( String inModule, String inForStage, String inDisplayValue, String inStoringValue )
    {
        module = inModule;
        forStage = inForStage;
        displayValue = inDisplayValue;
        storingValue = inStoringValue;
    }

    public String getModule()
    {
        return module;
    }

    public String getForStage()
    {
        return forStage;
    }

    public String getDisplayValue()
    {
        return displayValue;
    }

    public String getStoringValue()
    {
        return storingValue;
    }
    public static CRMActionConstants getCRMActionConstants( String storingValue )
    {
        CRMActionConstants constants = null;
        for ( CRMActionConstants actionConstants : CRMActionConstants.values() )
        {
            if ( actionConstants.getStoringValue().equals( storingValue ) )
            {
                constants = actionConstants;
                break;
            }
        }
        return constants;
    }
}
