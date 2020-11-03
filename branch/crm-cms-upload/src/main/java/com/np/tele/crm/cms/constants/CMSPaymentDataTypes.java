package com.np.tele.crm.cms.constants;

public enum CMSPaymentDataTypes {
    Double("Double"),
    INTEGER("Integer"),
    SHORT("Short"),
    DATE("XMLGregorianCalendar"),
    STRING("String");
    
    private String type;

    private CMSPaymentDataTypes( String inType )
    {
        type = inType;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }
}
