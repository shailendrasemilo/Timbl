package com.np.tele.crm.constants;

import java.util.HashMap;
import java.util.Map;

import com.np.tele.crm.utils.StringUtils;
//Next Available Code CRMCA53
public enum CRMCustomerActivityActions {
    ADDON_PLAN_CHANGE("CRMCA01","Addon plan change","addOnPlanCode","addOnPlanCode","CrmPlanMasterPojo.planName"),
    BASE_PLAN_MIGRATION("CRMCA06","Base plan migration","basePlanCode","basePlanCode","CrmPlanMasterPojo.planName"),
    BOOSTER_PLAN("CRMCA10","Booster plan","categoryValue","categoryValue","CrmPlanMasterPojo.planName"),
    PLAN_REACTIVATION_BASE("CRMCA42","Plan Reactivation - Base","basePlanCode","basePlanCode","CrmPlanMasterPojo.planName"),
    PLAN_REACTIVATION_ADDON("CRMCA48","Plan Reactivation - ADDON","addOnPlanCode","addOnPlanCode","CrmPlanMasterPojo.planName"),
    PLAN_RENEW_BASE("CRMCA40","Plan Renewal - Base","basePlanCode","basePlanCode","CrmPlanMasterPojo.planName"),
    PLAN_RENEW_ADDON("CRMCA49","Plan Renewal - ADDON","addOnPlanCode","addOnPlanCode","CrmPlanMasterPojo.planName"),
    ALTERNATE_EMAIL_MODIFY("CRMCA03","Alternate email modify","altEmailId","altEmailId"),
    ALTERNATE_NUMBER_MODIFY("CRMCA04","Alternate number modify","custMobileNo","custMobileNo"),
    BARRING("CRMCA05","Barring","","","Active","Barred"),
    BILL_MODE_MODIFY("CRMCA07","Bill mode modify","billMode","billMode", "CRMUtils."+CRMParameter.BILLING_PREFERENCES.getParameter()),
   // BILL_CUT("CRMCA08","Bill cut","",""),
    BILLING_ADDRESS_CHANGE("CRMCA09","Billing address change","addLine1,addLine2,addLine3,pincode","addLine1,addLine2,addLine3,pincode"),
    CHEQUE_BOUNCE_CHARGE("CRMCA11","Cheque bounce charge","","chargeAmount"),
   // CNG_POOL("CRMCA12","CNG Pool","",""),
    CPE_OWNERSHIP_CHANGE("CRMCA13","CPE Ownership change","cpeStatus","cpeStatus", "CRMUtils."+CRMParameter.CPE_STATUS.getParameter()),
    CUSTOMER_CATEGORY_MODIFY("CRMCA14","Customer category modify","connectionType","connectionType", "CRMUtils."+CRMParameter.CONNECTION_TYPE.getParameter()),
    CUSTOMER_NAME_MODIFY("CRMCA15","Customer name modify","custFname,custLname","custFname,custLname"),
    //CUSTOMER_POOL_CHANGE("CRMCA16","Customer pool change","option82","option82","CrmPartnerNetworkConfigPojo.nasPortId"),
    DUPLICATE_EBILL("CRMCA17","Duplicate e-bill","","passwordPdfFile"),
    EMAIL_MODIFY("CRMCA18","email modify","custEmailId","custEmailId"),
    EMAIL_VERIFICATION_LINK_SENT("CRMCA19","Email Verification Link Sent","custEmailId","custEmailId"),
    EMAIL_VALIDATION("CRMCA20","email validation","emailValidationFlag","emailValidationFlag","Y:Validated,N:Non-validated"),
    INSTALLAITION_ADDRESS_CHANGE("CRMCA21","Shifting Address","addLine1,addLine2,addLine3,pincode","addLine1,addLine2,addLine3,pincode"),
    INSTALLAITION_ADDRESS_CORRECTION("CRMCA52","Installation Address Correction","addLine1","addLine1"),
    MAC_CHANGE("CRMCA22","MAC Change","currentCpeMacId,currentSlaveMacId","currentCpeMacId,currentSlaveMacId"),
    NAME_CHANGE("CRMCA23","Name change","custFname,custLname","custFname,custLname"),
    OPTION_82_CHANGE("CRMCA24","Option 82 change","option82","option82"),
    REMOVE_TDC("CRMCA25","Remove TDC","",""),
    RESET_PASSWORD("CRMCA26","Reset Password","",""),
    RMN_MODIFY("CRMCA27","RMN modify","rmn","rmn"),
    RTN_MODIFY("CRMCA28","RTN modify","rtn","rtn"),
    SMART_BROADBAND_ACTIVATION("CRMCA29","Smart broadband activation","","","","Smart Broadband"),
    SMART_BROADBAND_DEACTIVATION("CRMCA30","Smart broadband deactivation","","","Smart Broadband",""),
    UNBARRING("CRMCA31","Unbarring","","","Barred","Active"),
    UNBARRING_EXCEPTION("CRMCA32","Unbarring exception","","","Active","Barred"),
    WAIVER("CRMCA33","Waiver","waiverAmount","waiverAmount"),
    CONTACT_PERSON_CHANGE("CRMCA34","Contact Person Change","localCpFname","localCpFname"),
   //CONNECTION_TYPE_CHANGE("CRMCA35","Connection Type Change","connectionType","connectionType","CRMUtils."+CRMParameter.CONNECTION_TYPE.getParameter()),
    AUTHORIZED_PERSON_CHANGE("CRMCA36","Authorized Person Change","authSignFname,authSignLname","authSignFname,authSignLname"),
    TDC("CRMCA37","Temporary Disconnection","status","status","CRMUtils.CustomerStatus"),
    PDC("CRMCA38","Permanent Disconnection","status","status","CRMUtils.CustomerStatus"),
    SAFE_CUSTODY("CRMCA41","Safe Custody","status","status","CRMUtils.CustomerStatus"),
    BILL_CYCLE_MODIFY("CRMCA43","Bill cycle modify","billDate","billDate"),
    CUSTOMER_OWNERSHIP_MODIFY("CRMCA44","Customer ownership modify","custFname,custLname","custFname,custLname"),
   // CHEQUE_STATUS_CHANGE("CRMCA45","Customer Cheque Status Change" ,"chequeStatus","chequeStatus","CRMUtils."+CRMParameter.REALIZATION_STATUS_LIST.getParameter()),
    //SHIFTING("CRMCA47","Installation Address Change","shiftingAddress","shiftingAddress"),
    //REFUND_POST("CRMCA46","Post/Update Refund" ,"entityType,refundMode,chequeNumber,transactionId,refundAmount,refundDate","entityType,refundMode,chequeNumber,transactionId,refundAmount,refundDate"),
    STATIC_IP("CRMCA47","Static IP","categoryValue","categoryValue"),
    STATIC_IP_CHARGES("CRMCA48","Static IP Charges","","categoryAmount"),
    WIRING_CHARGES("CRMCA49","Wiring Charges","","categoryAmount"),
    VALIDITY_EXTENSION("CRMCA50","Validity Extension","",""),
    ADDON_PLAN_REMOVAL("CRMCA51","Addon Plan Removal","addOnPlanCode","addOnPlanCode","CrmPlanMasterPojo.planName"),
    SEND_LEGAL_MAIL("CRMCA53","Send Leagal Mail","billNumber",""), 
    UNBILLED_USAGE_SENT("CRMCA54","Unbilled Data Usage Email Sent","",""),
    BILLED_USAGE_SENT("CRMCA55","Billed Data Usage Email Sent","",""),
    ADD_MASSOUTAGE("CRMCA56","Mass Outage Added","",""),
    RESOLVED_MASSOUTAGE("CRMCA57","Mass Outage Resolved","",""),
    PARTNER_CHANGE("CRMCA58","Partner Change","",""),
    SECURITY_DEPOSIT_CHANGE("CRMCA59","Security Deposit Change","securityCharges","securityCharges"),
    ;




    private static Map<String, String> codeActionMap;
    private static Map<String, String> actionCodeMap;
    static
    {
        if ( !StringUtils.isValidObj( codeActionMap ) || !StringUtils.isValidObj( actionCodeMap ) )
        {
            codeActionMap = new HashMap<String, String>();
            actionCodeMap = new HashMap<String, String>();
            for ( CRMCustomerActivityActions customerActivityActions : CRMCustomerActivityActions.values() )
            {
                codeActionMap.put( customerActivityActions.getActionCode(), customerActivityActions.getActionDesc() );
                actionCodeMap.put( customerActivityActions.getActionDesc(), customerActivityActions.getActionCode() );
            }
        }
    }
    private String                     actionCode;
    private String                     actionDesc;
    private String                     oldProperty;
    private String                     newProperty;
    private String                     oldValue;
    private String                     newValue;
    private String                     propertyDetail;

    private CRMCustomerActivityActions( String inActionCode,
                                        String inActionDesc,
                                        String inOldProperty,
                                        String inNewProperty )
    {
        actionCode = inActionCode;
        actionDesc = inActionDesc;
        oldProperty = inOldProperty;
        newProperty = inNewProperty;
    }

    private CRMCustomerActivityActions( String inActionCode,
                                        String inActionDesc,
                                        String inOldProperty,
                                        String inNewProperty,
                                        String inPropertyDetail )
    {
        actionCode = inActionCode;
        actionDesc = inActionDesc;
        oldProperty = inOldProperty;
        newProperty = inNewProperty;
        propertyDetail = inPropertyDetail;
    }

    private CRMCustomerActivityActions( String inActionCode,
                                        String inActionDesc,
                                        String inOldProperty,
                                        String inNewProperty,
                                        String inOldValue,
                                        String inNewValue )
    {
        this.actionCode = inActionCode;
        this.actionDesc = inActionDesc;
        this.oldProperty = inOldProperty;
        this.newProperty = inNewProperty;
        this.oldValue = inOldValue;
        this.newValue = inNewValue;
    }

    public String getActionCode()
    {
        return actionCode;
    }

    public String getActionDesc()
    {
        return actionDesc;
    }

    public static String getActionByCode( String inCode )
    {
        return codeActionMap.get( inCode );
    }

    public static String getCodeByAction( String inAction )
    {
        return actionCodeMap.get( inAction );
    }

    public String getOldProperty()
    {
        return oldProperty;
    }

    public String getNewProperty()
    {
        return newProperty;
    }

    public String getOldValue()
    {
        return oldValue;
    }

    public String getNewValue()
    {
        return newValue;
    }

    public String getPropertyDetail()
    {
        return propertyDetail;
    }

    public static CRMCustomerActivityActions getObjByActionDesc( String actionDesc )
    {
        for ( CRMCustomerActivityActions actions : CRMCustomerActivityActions.values() )
        {
            if ( StringUtils.equals( actions.getActionDesc(), actionDesc ) )
            {
                return actions;
            }
        }
        return null;
    }
    public static CRMCustomerActivityActions getActivityByActionCode( String actionCode )
    {
        for ( CRMCustomerActivityActions actions : CRMCustomerActivityActions.values() )
        {
            if ( StringUtils.equals( actions.getActionCode(), actionCode ) )
            {
                return actions;
            }
        }
        return null;
    }


    public static void main( String[] args )
    {
       /* System.out.println( CRMCustomerActivityActions.getActionByCode( CRMCustomerActivityActions.BILL_CUT
                .getActionCode() ) );*/
        System.out.println( CRMCustomerActivityActions
                .getCodeByAction( CRMCustomerActivityActions.BILLING_ADDRESS_CHANGE.getActionDesc() ) );
    }
}
