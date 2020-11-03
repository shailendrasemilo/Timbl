package com.np.upload.constants;

public enum DocumentTypes {

    POI("POI", "Proof of Identity"),
    POA("POA", "Proof of Adress"),
    CAF("CAF", "Customer Activation Form"),
    ISR("ISR", "Installation Satisfaction Report");

    private String code;
    private String desc;

    private DocumentTypes( String code, String desc )
    {
        this.code = code;
        this.desc = desc;
    }

    public String getCode()
    {
        return code;
    }

    public String getDesc()
    {
        return desc;
    }
    
    public static String descByCode( String code )
    {
        for ( DocumentTypes types : DocumentTypes.values() )
        {
            if ( types.getCode().equals( code ) )
            {
                return types.getDesc();
            }
        }
        return null;
    }
}
