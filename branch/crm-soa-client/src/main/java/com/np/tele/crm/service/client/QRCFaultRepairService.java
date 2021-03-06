
package com.np.tele.crm.service.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "QRCFaultRepairService", targetNamespace = "http://faultRepair.crm.tele.np.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface QRCFaultRepairService {


    /**
     * 
     * @param sender
     * @param message
     * @param to
     * @param oprator
     * @param date
     * @throws SOAPException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "qrcFaultRepair", targetNamespace = "http://faultRepair.crm.tele.np.com/", className = "com.np.tele.crm.service.client.QrcFaultRepair")
    @ResponseWrapper(localName = "qrcFaultRepairResponse", targetNamespace = "http://faultRepair.crm.tele.np.com/", className = "com.np.tele.crm.service.client.QrcFaultRepairResponse")
    public void qrcFaultRepair(
        @WebParam(name = "To", targetNamespace = "")
        String to,
        @WebParam(name = "Message", targetNamespace = "")
        String message,
        @WebParam(name = "Oprator", targetNamespace = "")
        String oprator,
        @WebParam(name = "Sender", targetNamespace = "")
        String sender,
        @WebParam(name = "Date", targetNamespace = "")
        String date)
        throws SOAPException_Exception
    ;

}
