package com.np.tele.crm.billing.utils;

import javax.xml.bind.JAXBElement;

public class BillingUtils
{
    public static <E> E convertToJavaObject( JAXBElement<E> jaxbElement )
    {
        return jaxbElement != null ? jaxbElement.getValue() : null;
    }

    public static <E> String toJavaString( JAXBElement<E> jaxbElement )
    {
        return jaxbElement != null ? jaxbElement.toString() : null;
    }

    public static <E> String toObjectString( E object )
    {
        return object != null ? object.toString() : null;
    }
}
