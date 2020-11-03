package com.np.tele.crm.client.util;

import javax.xml.ws.BindingProvider;

public final class CrmClientUtil
{
    public static <E> E setEndPoint( E inService, String endPoint )
    {
        ( (BindingProvider) inService ).getRequestContext().put( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint );
        ( (BindingProvider) inService ).getRequestContext().put( "com.sun.xml.ws.request.timeout", 500000 );
        ( (BindingProvider) inService ).getRequestContext().put( "com.sun.xml.ws.connect.timeout", 100000 );
        ( (BindingProvider) inService ).getRequestContext().put( "javax.xml.ws.client.receiveTimeout", 1200000 );
        return inService;
    }

    public static <E> E setEndPoint2( E inService, String endPoint )
    {
        ( (BindingProvider) inService ).getRequestContext().put( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint );
        ( (BindingProvider) inService ).getRequestContext().put( "com.sun.xml.ws.request.timeout", 500000 );
        ( (BindingProvider) inService ).getRequestContext().put( "com.sun.xml.ws.connect.timeout", 100000 );
        ( (BindingProvider) inService ).getRequestContext().put( "javax.xml.ws.client.receiveTimeout", 1200000 );
        return inService;
    }
}
