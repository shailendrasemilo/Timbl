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
@WebServiceClient(name = "QRCFaultRepairServiceService", targetNamespace = "http://faultRepair.crm.tele.np.com/")
public class QRCFaultRepairServiceService
    extends Service
{
    private final static URL    QRCFAULTREPAIRSERVICESERVICE_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger( com.np.tele.crm.service.client.QRCFaultRepairServiceService.class
                                               .getName() );
    static
    {
        URL url = null;
        try
        {
            URL baseUrl;
            baseUrl = com.np.tele.crm.service.client.QRCFaultRepairServiceService.class.getResource( "." );
            url = new URL( baseUrl, IAppConstants.CRM_QRC_FAULT_REPAIR_WSDL );
        }
        catch ( MalformedURLException e )
        {
            logger.warning( "Failed to create URL for the wsdl Location: " + IAppConstants.CRM_QRC_FAULT_REPAIR_WSDL
                    + ", retrying as a local file" );
            logger.warning( e.getMessage() );
        }
        QRCFAULTREPAIRSERVICESERVICE_WSDL_LOCATION = url;
    }

    public QRCFaultRepairServiceService( URL wsdlLocation, QName serviceName )
    {
        super( wsdlLocation, serviceName );
    }

    public QRCFaultRepairServiceService()
    {
        super( QRCFAULTREPAIRSERVICESERVICE_WSDL_LOCATION, new QName( "http://faultRepair.crm.tele.np.com/",
                                                                      "QRCFaultRepairServiceService" ) );
    }

    /**
     * 
     * @return
     *     returns QRCFaultRepairService
     */
    @WebEndpoint(name = "QRCFaultRepairServicePort")
    public QRCFaultRepairService getQRCFaultRepairServicePort()
    {
        //return super.getPort(new QName("http://faultRepair.crm.tele.np.com/", "QRCFaultRepairServicePort"), QRCFaultRepairService.class);
        return CrmClientUtil.setEndPoint( super.getPort( new QName( "http://faultRepair.crm.tele.np.com/",
                                                                    "QRCFaultRepairServicePort" ),
                                                         QRCFaultRepairService.class ),
                                          IAppConstants.CRM_QRC_FAULT_REPAIR_END_POINT );
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns QRCFaultRepairService
     */
    @WebEndpoint(name = "QRCFaultRepairServicePort")
    public QRCFaultRepairService getQRCFaultRepairServicePort( WebServiceFeature... features )
    {
        return super.getPort( new QName( "http://faultRepair.crm.tele.np.com/", "QRCFaultRepairServicePort" ),
                              QRCFaultRepairService.class, features );
    }
}
