package com.np.tele.crm.service.client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;

import com.np.tele.crm.client.util.CrmClientUtil;
import com.np.tele.crm.constants.IAppConstants;

/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "CrmFinanceServiceService", targetNamespace = "http://service.finance.crm.tele.np.com/")
public class CrmFinanceServiceService
    extends Service
{
    private final static URL    CRMFINANCESERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger( com.np.tele.crm.service.client.CrmFinanceServiceService.class
                                               .getName() );
    static
    {
        URL url = null;
        try
        {
            URL baseUrl;
            baseUrl = com.np.tele.crm.service.client.CrmFinanceServiceService.class.getResource( "." );
            url = new URL( baseUrl, IAppConstants.CRM_FINANCE_WSDL );
        }
        catch ( MalformedURLException e )
        {
            logger.warning( "Failed to create URL for the wsdl Location: " + IAppConstants.CRM_FINANCE_WSDL
                    + ", retrying as a local file" );
            logger.warning( e.getMessage() );
        }
        CRMFINANCESERVICESERVICE_WSDL_LOCATION = url;
    }

    public CrmFinanceServiceService( URL wsdlLocation, QName serviceName )
    {
        super( wsdlLocation, serviceName );
    }

    public CrmFinanceServiceService()
    {
        super( CRMFINANCESERVICESERVICE_WSDL_LOCATION, new QName( "http://service.finance.crm.tele.np.com/",
                                                                  "CrmFinanceServiceService" ) );
    }

    /**
     * 
     * @return
     *     returns CrmFinanceService
     */
    @WebEndpoint(name = "CrmFinanceServicePort")
    public CrmFinanceService getCrmFinanceServicePort()
    {
        /*   return super.getPort( new QName( "http://service.finance.crm.tele.np.com/", "CrmFinanceServicePort" ),
                                 CrmFinanceService.class );*/
        return CrmClientUtil
                .setEndPoint( super.getPort( new QName( "http://service.finance.crm.tele.np.com/", "CrmFinanceServicePort" ),
                              CrmFinanceService.class ),IAppConstants.CRM_FINANCE_END_POINT);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns CrmFinanceService
     */
    @WebEndpoint(name = "CrmFinanceServicePort")
    public CrmFinanceService getCrmFinanceServicePort( WebServiceFeature... features )
    {
        return super.getPort( new QName( "http://service.finance.crm.tele.np.com/", "CrmFinanceServicePort" ),
                              CrmFinanceService.class, features );
    }
}