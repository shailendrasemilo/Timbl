package com.np.tele.crm.billing.constants;

import java.util.HashMap;
import java.util.Map;

import com.np.tele.crm.utils.StringUtils;

public enum CrmBillingDataTypes {
    JAXB_STRING("javax.xml.bind.JAXBElement<java.lang.String>"),
    JAXB_BIGDECIMAL("javax.xml.bind.JAXBElement<java.math.BigDecimal>"),
    BOOLEAN("class java.lang.Boolean"),
    DOUBLE("class java.lang.Double"),
    INTEGER("class java.lang.Integer"),
    LONG("class java.lang.Long"),
    BIGDECIMAL("class java.math.BigDecimal"),
    XMLGREGORIANCALENDAR("class javax.xml.datatype.XMLGregorianCalendar"),
    JAXB_BILLING_ADDRESS("javax.xml.bind.JAXBElement<org.datacontract.schemas._2004._07.BillingAddress>"),
    JAXB_INSTALLATION_ADDRESS("javax.xml.bind.JAXBElement<org.datacontract.schemas._2004._07.InstallationAddress>"),
    JAXB_CONTACT_ADDRESS("javax.xml.bind.JAXBElement<org.datacontract.schemas._2004._07.LocalContactAddress>"),
    JAXB_STRING_ARRAY("javax.xml.bind.JAXBElement<com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring>");


    private String                                  type;
    private static Map<String, CrmBillingDataTypes> map = null;

    private CrmBillingDataTypes( String inType )
    {
        type = inType;
    }

    public String getType()
    {
        return type;
    }
    static
    {
        map = new HashMap<String, CrmBillingDataTypes>();
        for ( CrmBillingDataTypes dataType : CrmBillingDataTypes.values() )
        {
            map.put( dataType.getType(), dataType );
        }
    }

    public static CrmBillingDataTypes getBillingDataType( String inStr )
    {
        CrmBillingDataTypes dataType = null;
        if ( StringUtils.isValidObj( map ) && !map.isEmpty() )
        {
            dataType = map.get( inStr );
        }
        return dataType;
    }
}
