package com.np.tele.crm.constants;

import java.util.HashMap;
import java.util.Map;

public enum CustomerProfile {
    CUSTOMER_ID("customerId", "Customer ID", "Inbox,waiver,CAF,QRC", "INA",0),
    CAF_ID("crfid", "CAF Number", "Inbox,waiver,CAF,QRC,Freeze", "INA",0),
    REGISTERED_MOBILE("rmn", "Registered Mobile Number", "", "INA",0),
    TICKET_ID("ticketId","Ticket ID","Inbox,QRC","QRC",0),
    LEAD_ID("leadid","Lead ID","Inbox,lead","LEAD",0),
    NETWORK_PARTNER("networkPartner","Network Partner","Inbox,CAF,QRC","INA,QRC",1),
    SERVICE_NAME("product","Service Name", "Inbox,CAF,QRC,lead", "INA,LEAD,QRC",1),
    LEAD_CUSTOMER_NAME("customername","Lead - Customer Name","","LEAD",0),
    LEAD_CONTACT_NO("contactno","Lead - Contact Number","","LEAD",0),
    CUSTOMER_FIRST_NAME("cFName", "Customer First Name", "", "INA",0),
    CUSTOMER_LAST_NAME("cLName", "Customer Last Name", "", "INA",0),
    SUB_SUB_CATEGORY("subSubCategory","Sub Sub Category","Inbox,QRC","QRC",1),
    //REGISTERED_TELEPHONE("rtn", "Registered Telephone Number", "", "INA",0),
    CUSTOMER_MAC_ADDRESS("cMacAddress", "Customer MAC Address", "", "INA",0),
    ;
    
    private String                              code;
    private String                              value;
    private String                              method;
    private String                              module;
    private int                                 showValue;
    private static Map<String, CustomerProfile> searCriteriaMap;
    static
    {
        searCriteriaMap = new HashMap<String, CustomerProfile>();
        for ( CustomerProfile customerProfile : CustomerProfile.values() )
        {
            searCriteriaMap.put( customerProfile.getCode(), customerProfile );
        }
    }

    private CustomerProfile( String inCode, String inValue, String inMethod, String inModule,int inShowValue )
    {
        code = inCode;
        value = inValue;
        method = inMethod;
        module = inModule;
        showValue = inShowValue;
    }

    public String getValue()
    {
        return value;
    }

    public String getCode()
    {
        return code;
    }

    public String getMethod()
    {
        return method;
    }

    public String getModule()
    {
        return module;
    }

    public int getShowValue()
    {
        return showValue;
    }

    public static String getCodeByModule( String inCode )
    {
        return searCriteriaMap.get( inCode ).getModule();
    }

    public static CustomerProfile getProfiler( String inCode )
    {
        return searCriteriaMap.get( inCode );
    }
}
