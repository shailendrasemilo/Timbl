package com.np.tele.crm.billing.utils;

import javax.xml.ws.BindingProvider;

public final class BillingClientBinding
{
    public static <E> E setEndPoint( E inBillingService, String endPoint )
    {
        /*BindingProvider bp = (BindingProvider) inBillingService;
        bp.getRequestContext().put( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint );
        */
        
        //        java.util.List<Handler> handlers = bp.getBinding().getHandlerChain();
        //        handlers.add( new BillingHandler() );
        //        bp.getBinding().setHandlerChain( handlers );
        //        return BillingClientBinding.setEndPoint( super.getPort( new QName( "http://tempuri.org/",
        //                                                                           "BasicHttpBinding_ICustomer" ),
        //                                                                ICustomer.class ), ENDPOINT_STR );
        
        ( (BindingProvider) inBillingService ).getRequestContext().put( BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint );
        ( (BindingProvider) inBillingService ).getRequestContext().put( "com.sun.xml.ws.request.timeout", 500000 );
        ( (BindingProvider) inBillingService ).getRequestContext().put( "com.sun.xml.ws.connect.timeout", 100000 );
        ( (BindingProvider) inBillingService ).getRequestContext().put( "javax.xml.ws.client.receiveTimeout", 1200000 );
        return inBillingService;
    }
}
