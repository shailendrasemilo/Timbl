package com.np.tele.crm.constants;

import java.util.ArrayList;
import java.util.List;

import com.np.tele.crm.utils.StringUtils;

public enum CrmReasonsConstants {
    ALERTDISABLED("Alert Disabled"), 
    EMAILIDMISSSING("Email-Id not Available"), 
    MOBILENOMISSING("Mobile No not Available"), 
    TEMPLATENOTMAPPED("Email template not mapped"), 
    EVENTNOTCONFIGURED("Event Template mapping not configured"),
    TEMPLATEINACTIVE("Template not in used"), 
    SERVERNOTFOUND("Configured Server not found."), 
    EVENTNOTFOUND("Provided event not available"), 
    SERVERALERTSOFF("SMS Alerts turned off"),
    CONFIGUREDSERVERISSUE("Problem occured at configured Server."),
    TEMPLATEEMPTY("Template Empty, nothing to send."),
    STALE_CHEQUE("Stale Cheque","NonFreeze"),
    DATE_ISSUE("Date Issue","NonFreeze"),
    ALTERATION_CORRECTION("Alteration and Correction","NonFreeze"),
    SIGNATURE_MISMATCH("Signature Mismatch","NonFreeze"),
    ;
   
    private String description;
    private String module;

    private CrmReasonsConstants( String inDescription )
    {
        description = inDescription;
    }
    
    private CrmReasonsConstants( String inDescription, String inModule )
    {
        description = inDescription;
        module = inModule;
    }

    public String getDescription()
    {
        return description;
    }

    public String getModule()
    {
        return module;
    }

    public static List<String> getReasons( String inModule )
    {
        List<String> reasons = new ArrayList<String>();
        for ( CrmReasonsConstants reason : CrmReasonsConstants.values() )
        {
            if ( StringUtils.equals( inModule, reason.getModule() ) )
            {
                reasons.add( reason.getDescription() );
            }
        }
        return reasons;
    }
}
