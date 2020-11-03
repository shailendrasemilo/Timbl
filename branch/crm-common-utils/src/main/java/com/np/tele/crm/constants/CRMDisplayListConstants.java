package com.np.tele.crm.constants;

import com.np.tele.crm.utils.StringUtils;

public enum CRMDisplayListConstants {
    CAF("other","CAF","Customer Activation Form"),
    CASH_RECEIPT("other","CashReceipt","Cash Receipt"),
    BROADBAND("Product", "BB", "FTTX"), 
    RADIO_FREQUENCY("Product", "RF", "RF"), 
    ETHERNET_ON_CABLE("Product", "EOC","FTTN"), 
    BROWN("FieldType", "B", "Brown Field"), 
    GREEN("FieldType", "G", "Green Field"),
    NETWORK_PARTNER("PartnerType", "NP", "Network Partner"), 
    SERVICE_PARTNER("PartnerType", "SP", "Installation Partner"), 
    ASSOCIATE_CONSULTANT("PartnerType", "AC", "Associate Consultant"), 
    BUSINESS_PARTNER("PartnerType", "ST", "Sales"), 
    //FILE_UPLOAD("LeadSource", "FU", "File Upload"), 
    SMS("LeadSource", "SMS", "SMS"), 
    CUSTOMER_CARE("LeadSource", "CC","Customer Care"), 
    WEBSITE("LeadSource", "WB", "Website"),
    APP("LeadSource", "APP", "Mobile App"),
    UNABLETOCONTACT("CloseReason","12345","Customer not aviable on phone."),
    CONNECTIONNOTREQUIRE("CloseReason","12346","Customer don't want connection."),    
    MORNING("appointmentTime","M","Morning (9am-12pm)"),
    AFTERNOON("appointmentTime","A","Afternoon (12pm-4pm)"),
    Evening("appointmentTime","E","Evening (4pm-8pm)"),
    ANY_TIME("appointmentTime","AT","Any Time"),
    F2F("modeOfAppointment","F","Face to Face"),
    TEL("modeOfAppointment","T","Telephonic"),
    INDIVIDUAL("ConnectionType","Ind","Individual"),
    PROPRIETORSHIP("ConnectionType","Prop","Proprietorship"),
    PARTNERSHIP("ConnectionType","Part","Partnership"),
    LTD_PVTLTD("ConnectionType","Ltd/Pvt","Ltd./Pvt. Ltd."),
    SOCIETY_TRUST_NGO("ConnectionType","Ngo","Society/Trust/NGO"),
    GOVERNMENT_OF_INDIA("ConnectionType","Gov","Government of India Undertaking"),
    FOREIGN_MISSION_AGENCY("ConnectionType","Fma","Foreign Mission Agency"),
    BUSINESS("VisaType","Business","Business"),
    TOURIST("VisaType","Tourist","Tourist"),
    STUDENT("VisaType","Student","Student"),
    WORK("VisaType","Work","Work/Employment"),
    EXCHANGE_VISITOR("VisaType","ExVstor","Exchange Visitor"),
    TRANSIT_CREW("VisaType","TrnsCrew","Transit/Ship Crew"),
    RELIGIOUS_WORKER("VisaType","RelWrk","Religious Worker"),
    DOMESTIC_EMPLOYEE("VisaType","DomEmp","Domestic Employee"),
    JOURNALIST("VisaType","Journalist","Journalist/Media"),
    WEEKDAY("DayOfInstallation","weekday","Weekday"),
    WEEKEND("DayOfInstallation","Weekend","Weekend"),
    ANY_DAY("DayOfInstallation","anyday","Any day"),
    OWNED("CPEStatus","CO","Customer Owned"),
//    PROVIDEED_BY_NEXTRA("CPEStatus","N","Provided by Nextra"),
    //Company provided
    NEXTRA_RECOMMENDED("CPEStatus","NR","RI Recommended"),
    PAPER_BILL("BillingPreferences","PB","Paper Bill"),
    E_BILL("BillingPreferences","EB","E-Bill"),
    CHEQUE("PaymentMode","Q","Cheque/DD","I&A,Refund",""),
    CASH("PaymentMode","C","Cash","I&A",""),
    ONLINE_PAYMENT("PaymentMode","O","Online","Refund",""),
    //NATIONAL_ELECTRONIC_FUND_TRANSFER("PaymentMode","N","NEFT"),
    PAN_CARD("Documents","PC","PAN Card"),
    ELECTRICITY_PAPER_BILL("AddressProof","E-PB","Electricity/Paper Bill"),
    TELEPHONE_BILL("AddressProof","TB","Telephone Bill"),
    RENT_LEASE_AGREEMENT("AddressProof","RLA","Rent/Lease Agreement"),
    BANK_STATEMENT("AddressProof","BS","Bank Statement"),
    CREDIT_CARD_STATEMENT("AddressProof","CCS","Credit Card Statement"),
    //common proof
    DRIVING_LICENSE("commonProof","DL","Driving License"),
    VOTER_ID_CARD("commonProof","VIC","Voter ID Card"),
    PASSPORT("commonProof","PP","Passport"),
    AADHAR("commonProof","AD","Aadhar"),
    RATION_CARD("commonProof","RC","Ration Card"),
    ANY_OTHER("commonProof","AO","Any Other"),
    TERMINAL_DEVICE_INSTALLED("networkConnectivityInfo","TDI","Terminal Device Installed"),
    FIBER_THERNET_JECTION_BOX("networkConnectivityInfo","F/E","No Terminal Device, but Fiber/Ethernet Junction Box Installed"),
    NO_WIRING("networkConnectivityInfo","NW","No Wiring at Customer Premises"),
    //--------------Payment Channels
    HDFC("PaymentChannel","HDFC","HDFC Payment Gateway","O",""),
    ATOM("PaymentChannel","ATOM","ATOM Payment Gateway","O",""),
    TECH_PROCESS("PaymentChannel","TP","Tech Process","O",""),
    EASYBILL("PaymentChannel","EasyBill","Easy Bill","Q,O",""),
    CMS("PaymentChannel","CMS","CMS","Q",""),
    NEFT("PaymentChannel","NEFT","HDFC NEFT","O",""),
    SECURITY_DEPOSIT("PaymentChannel","SD","Security Deposit","Q,O,C",""),
    ARYAVRAT("PaymentChannel","Aryavrat","Aryavrat","Q,C",""),
    TDS_PAYMENT("PaymentChannel","TDS","TDS Payment","C",""),   
    DIRECT("PaymentChannel","Direct","Direct","Q,C",""),
    PAYTM("PaymentChannel","PAYTM","PAYTM","O",""),
    // ECS_SI("PaymentChannel","ECS/SI","ECSSI"),
    //------------------------
    CHEQUE_REALIZED("RealizationStatus","RL","Realized"),
    CHEQUE_NOTREALIZED("RealizationStatus","NRL","Not Realized"),
    CHEQUE_CLEARANCE_AWAITED("RealizationStatus","CLRAWT","Clearance Awaited"),
    PAYMENT_RECIEVED("PaymentStatus","R","Received"),
    PAYMENT_NOTRECIEVED("PaymentStatus","NR","Not Received"),
    CS_NOT_SUBMITTED("CaseStatus","NS","Cash Received Physically Not Received by Finance"),
    CS_CRDT_BP("CaseStatus","CDBP","Credit Done In BP Wallet"),
    CS_PNDNG_BP("CaseStatus","PNBP","Pending for Credit in BP Wallet"),
    CS_CASH_FIN("CaseStatus","CRFN","Cash Received by Finance"),
    CS_ONL_FIN("CaseStatus","ONFN","Online Payment Received"),
    CS_EBILL_FIN("CaseStatus","EBFN","Easy Bill Payment Received"),
    CS_CHEQUE_FIN("CaseStatus","CQFN","Cheque Payment Received"),
    CS_OTHERS("CaseStatus","CSO","Others"),
    CS_CLOSED("CaseStatus","CLS","Closed"),    
    PREINSTALLATION("InstallationStatus","PreIns","Pre-Installation"),
    POSTINSTALLATION("InstallationStatus","PostIns","Post-Installation"),
    QUERY("QRC","Q","Query"),
    REQUEST("QRC","R","Request"),
    COMPLAINTS("QRC","C","Complaints"),
    TELESERVICES("EntityType","T","Services"),
    TELESOLUTIONS("EntityType","E","Associate Co."),
    ERICSSON("OltType","E","Ericsson"),
    HUAWEI("OltType","H","Huawei"),
    BASE_PLAN("PlanCategory","BASE","BasePlan"),
    ADDON_PLAN("PlanCategory","ADDON","AddonPlan"),
    BOOSTER_PLAN("PlanCategory","BOOSTER","Booster"),
    SPEED_PLAN("PlanType","SPEED","SpeedPlan"),
    TOPUP_PLAN("PlanType","TOPUP","TopupPlan"),    
    VAS_PLAN("PlanType","VAS","VasPlan"),
    EOC_POSTPAID("PlanService","EOC","EOCPostPaid","EOC","PO"),
    EOC_PREPAID("PlanService","EOCPR","EOCPrePaid","EOC","PR"),
    BB_POSTPAID("PlanService","FTTH","BBPostpaid","BB","PO"),
    BB_PREPAID("PlanService","FTTH PREPAID","BBPrepaid","BB","PR"),
    RF_POSTPAID("PlanService","RF","RFPostpaid","RF","PO"),
    RF_PREPAID("PlanService","RF","RFPrepaid","RF","PR"),
    POST_PAID("ServiceType","PO","Postpaid"),
    PRE_PAID("ServiceType","PR","Prepaid"),
    WITHIN_PREMISES("ShiftingType","WP","Within Premises"),
    OUTSIDE_PREMISES("ShiftingType","OP","Outside Premises"),
    WORKING_HOURS("SlaUnit","WH","Working Hours"),
    WORKING_DAYS("SlaUnit","WD","Working Days"),
    CALENDAR_DAYS("SlaUnit","CD","Calendar Days"),
    RESIDENTIAL("CustomerCategory","Residential","Residential"),
    COMMERCIAL("CustomerCategory","Commercial","Commercial"),
    MIXED("CustomerCategory","Mixed","Mixed"),
    ;
    
    private String listType;
    private String code;
    private String value;
    private String filter1;
    private String filter2;

    private CRMDisplayListConstants( String inListType, String inCode, String inValue )
    {
        listType = inListType;
        code = inCode;
        value = inValue;
    }
    private CRMDisplayListConstants( String inListType, String inCode, String inValue, String inFilter1, String inFilter2 )
    {
        listType = inListType;
        code = inCode;
        value = inValue;
        filter1 = inFilter1;
        filter2 = inFilter2;
    }

    public String getValue()
    {
        return value;
    }

    public String getCode()
    {
        return code;
    }

    public String getListType()
    {
        return listType;
    }
    
    public String getFilter1()
    {
        return filter1;
    }
    public String getFilter2()
    {
        return filter2;
    }
    public static String getValueByCode( String inType, String inCode )
    {
        for ( CRMDisplayListConstants displayConstant : CRMDisplayListConstants.values() )
        {
            if ( StringUtils.equals( inType, displayConstant.getListType() )
                    && StringUtils.equals( inCode, displayConstant.getCode() ) )
            {
                return displayConstant.getValue();
            }
        }
        return null;
    }
    
    public static String getCodeByValue( String inType, String inValue )
    {
        for ( CRMDisplayListConstants displayConstant : CRMDisplayListConstants.values() )
        {
            if ( StringUtils.equals( inType, displayConstant.getListType() )
                    && StringUtils.equals( inValue, displayConstant.getValue() ) )
            {
                return displayConstant.getCode();
            }
        }
        return null;
    }

    public static CRMDisplayListConstants getConstantByFilter( String inType, String inFilter1, String inFilter2 )
    {
        for ( CRMDisplayListConstants displayConstant : CRMDisplayListConstants.values() )
        {
            if ( StringUtils.equals( inType, displayConstant.getListType() )
                    && StringUtils.equals( inFilter1, displayConstant.getFilter1() )
                    && StringUtils.equals( inFilter2, displayConstant.getFilter2() ) )
            {
                return displayConstant;
            }
        }
        return null;
    }
}
