package com.np.tele.crm.constants;

import com.np.tele.crm.utils.StringUtils;

public enum CRMParamDataEnum {
    
    CAT5("MaterialDetials","CAT5","CAT5 Cable Used (meters)","Text",""),
    RJ45("MaterialDetials","RJ45","RJ45 Cable Used (meters)","Text",""),
    NH("MaterialDetials","NH","Nail Hooks (nos.)","Text",""),
    PC("MaterialDetials","PC","Patch Cord (nos.)","Text",""),
    QIEW("CustomerFeedback","QIEW","Quality of Installation & External Wiring","Radio","Poor-Average-Good-Excellent"),
    SAIC("CustomerFeedback","SAIC","Service attitude of Installation crew","Radio","Poor-Average-Good-Excellent"),
    OPIC("CustomerFeedback","OPIC","Overall presentability of Installation crew","Radio","Poor-Average-Good-Excellent"),
    DITMYCT("CustomerFeedback","DITMYCT","Did the installation team meet you at a convenient time?","Radio","Yes-No"),
    OEDIP("CustomerFeedback","OEDIP","Overall experience during Installation Process","Radio","Yes-No"),
    AOCRF("CustomerFeedback","AOCRF","Any other Customer Remarks/Feedback","Textarea","");
    
    private String category;
    private String fieldId;
    private String fieldValue;
    private String fieldType;
    private String fieldOption;
    
    private CRMParamDataEnum(String inCategory, String inFieldId, String inFieldValue, String inFieldType, String inFieldOption){
        
        category = inCategory;
        fieldId = inFieldId;
        fieldValue = inFieldValue;
        fieldType = inFieldType;
        fieldOption = inFieldOption;
        
    }

    public String getCategory()
    {
        return category;
    }

    public String getFieldId()
    {
        return fieldId;
    }

    public String getFieldValue()
    {
        return fieldValue;
    }

    public String getFieldType()
    {
        return fieldType;
    }

    public String getFieldOption()
    {
        return fieldOption;
    }
    
    public static String getFieldIdByFieldValue( String fieldValue )
    {
        for ( CRMParamDataEnum fieldValues : CRMParamDataEnum.values() )
        {
            if ( StringUtils.equals( fieldValues.getFieldValue(), fieldValue ) )
            {
                return fieldValues.getFieldId();
            }
        }
        return null;
    }
    
}
