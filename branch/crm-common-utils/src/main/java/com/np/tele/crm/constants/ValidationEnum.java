package com.np.tele.crm.constants;

public enum ValidationEnum {
    SINGLE_MULTIPLICITY("S", "error.duplicate.parameter");
    private String parameter = null;
    private String errorKey  = null;

    private ValidationEnum( String inParameter, String inErrorKey )
    {
        this.parameter = inParameter;
        this.errorKey = inErrorKey;
    }

    public String getParameter()
    {
        return parameter;
    }

    public String getErrorKey()
    {
        return errorKey;
    }

    public static String getErrorKeyByParameter( String inParameter )
    {
        String strErrorKey = null;
        for ( ValidationEnum validations : ValidationEnum.values() )
        {
            if ( validations.getParameter().equals( inParameter ) )
            {
                strErrorKey = validations.getErrorKey();
                break;
            }
        }
        return strErrorKey;
    }
}
