package com.np.tele.crm.constants;

public enum QRCRolCategories {
    //ROL
    ADDRESS_MODIFY_BILLING ("updateBillingAddress", "Billing","Address Change/ Correction","Billing Address Change/ Correction","R", 0),
    RMN_MODIFY( "updateRMN", "Customer Service","Email/ RMN/ Alternate No Change Request","RMN","R", 0),
    RTN_MODIFY( "updateRTN", "Customer Service","Email/ RMN/ Alternate No Change Request","RTN","R", 0),
    ALT_CONTACT_NO( "updateAltMob", "Customer Service","Email/ RMN/ Alternate No Change Request","Alternate Number","R", 0),
    ALTERNATE_EMAIL_MODIFY( "updateAltEmail","Customer Service", "Email/ RMN/ Alternate No Change Request","Email Id","R", 0),
    REGISTERED_EMAIL_ID( "updateEmail", "Customer Service","Email/ RMN/ Alternate No Change Request","Email Id","R", 0),
    UNBARRING_REASON_PTP("Promise to Pay - P2P","Billing","Restore/ Unbar Service Request","PTP Basis","R", 0),
    UNBARRING_REASON_PAYMENT_REFLECTING("Payment Reflecting","Billing","Restore/ Unbar Service Request","Payment Reflecting","R", 0),
    UNBARRING_REASON_OTHERS("Other","Billing","Restore/ Unbar Service Request","Others","R", 0),
    RESET_SELF_CARE_PASSWORD("Reset Password","Customer Service","My Account Password","Reset My Account Password","R", 0),
    PLAN_MIGRATION_REQUEST("Base plan migration","Provisioning","Plan Migration/ Add On Plan Provisioning","Plan Migration Request","R", 0),
    SMART_BROADBAND_ACTIVATION("Smart broadband activation","Provisioning","Smart Broadband","Activation","R", 0),
    SMART_BROADBAND_DEACTIVATION("Smart broadband deactivation","Provisioning","Smart Broadband","Deactivation","R", 0),
    ADD_OR_REMOVE_ACCESSORY( "Change Accessory", "Network and Connectivity","Static IP Request","Static IP","R", 0),
    CONTACT_PERSON( "updateContactPerson", "Customer Service","Authorized Person","Change Of Authorized Person","R", 0),
    AUTHORIZED_PERSON( "updateAuthorizedPerson", "Customer Service","Authorized Person","Change Of Authorized Person","R", 0),
    PAPERBILL_TO_EBILL( "updateBillModeEB", "Bill Delivery","Bill Mode Change Request","Paper Bill To E-Bill","R", 0),
    EBILL_TO_PAPERBILL( "updateBillModePB", "Bill Delivery","Bill Mode Change Request","E-Bill To Paper Bill","R", 0),

    UPDATE_CONNECTION_TYPE( "updateConnectionType", "Backend Other","Customer Category Change","Network Partner Change","R", 0),
    // NO ROL Required but kept for on screen modification
    ADDRESS_MODIFY_INSTALLATION( "updateInstallationAddress", "","","","R", -1),
    NAME_CHANGE( "updateCustName", "","","","R", -1),
    //Auto Ticket
    CHEQUE_BOUNCE_ADVANCE_PAYMENT("CHQNRLAP","Backend Finance","Cheque Bounce Post Activation/ Advance Payment","","R", 1),
    CHEQUE_BOUNCE_BILL_PAYMENT("CHQNRLBP","Backend Finance","Cheque Bounce Bill Payment","","R", 1),
    SPEED_UP_GRADATION("speedUpGradation","Backend Network","Base Plan Migration","Core Network Updation","R", 1),
    REFUND_REQUEST( "Refund Request", "Backend Sales","Refund Related Request","SRP Refund","R", 1),
    // For Auto Unbilled Usage Mail on Ticket generation
    USAGE_DISPUTE("UsageDispute","Billing","Data Usage Dispute","Internet Usage Dispute","C",1),
    // Plan Related Sub Sub Categories
    BOOSTER_REQUEST("Booster plan","Provisioning","Booster Request","Booster Activation","R", 2),
    PREPAID_PLAN_RENEWAL("Plan Renewal","Billing","Prepaid Plan Renewal","Prepaid Plan Renewal","R", 0),
    // For Waiver Heads
    GOODWILL_WAIVER("GDWLWaiver","Billing","Goodwill Waiver","","R", 1),
    PROCESS_WAIVER("PRCSWaiver","Billing","Process Waiver","","R", 1),
    ADDON_ACTIVATION("CHANGE_ADDON","Provisioning","Plan Migration/ Add On Plan Provisioning","Add On Plan Activation","R",2),
    ADDON_DEACTIVATION("REMOVE_ADDON","Provisioning","Plan Migration/ Add On Plan Provisioning","Add On Plan Deactivation","R",2),
    // TDC Auto Generate ticket for Slave recovery
    TDC_SLAVE_RECOVERY("updateTDC","Backend EOC","Slave Recovery","Mac Release","R",1),
    SECURITY_DEPOSIT("updateSecurityDeposit","","","","R",-1);








    private String event;
    private String category;
    private String subCategory;
    private String subSubCategory;
    private String qrcType;
    private int resolutionType;

    private QRCRolCategories( String event, String category, String subCategory, String subSubCategory ,String qrcType, int resolutionType)
    {
        this.event = event;
        this.category = category;
        this.subCategory = subCategory;
        this.subSubCategory = subSubCategory;
        this.qrcType = qrcType;
        this.resolutionType = resolutionType;
    }

    public String getEvent()
    {
        return event;
    }

    public String getCategory()
    {
        return category;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public String getSubSubCategory()
    {
        return subSubCategory;
    }

    public String getQrcType()
    {
        return qrcType;
    }

    public int getResolutionType()
    {
        return resolutionType;
    }

    public static String[] getCategoriesByEvent( String event )
    {
        for ( QRCRolCategories action : QRCRolCategories.values() )
        {
            if ( action.getEvent().equals( event ) )
            {
                return new String[]
                { action.getCategory(), action.getSubCategory(), action.getSubSubCategory() };
            }
        }
        return null;
    }
    public static QRCRolCategories getQRCCategoriesByEvent( String event )
    {
        for ( QRCRolCategories action : QRCRolCategories.values() )
        {
            if ( action.getEvent().equals( event ) )
            {
                return action;
            }
        }
        return null;
    }
    public static String getSubSubCategoryByEvent( String event )
    {
        for ( QRCRolCategories action : QRCRolCategories.values() )
        {
            if ( action.getEvent().equals( event ) )
            {
                return action.getSubSubCategory();
            }
        }
        return null;
    }
}
