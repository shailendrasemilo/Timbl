
package com.np.tele.crm.service.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
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
@WebService(name = "CRMConfigService", targetNamespace = "http://service.config.crm.tele.np.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface CRMConfigService {


    /**
     * 
     * @param configDto
     * @param operationParam
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "customerActivityOps", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.CustomerActivityOps")
    @ResponseWrapper(localName = "customerActivityOpsResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.CustomerActivityOpsResponse")
    public ConfigDto customerActivityOps(
        @WebParam(name = "operationParam", targetNamespace = "")
        String operationParam,
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "geMappingIdByRemarks", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GeMappingIdByRemarks")
    @ResponseWrapper(localName = "geMappingIdByRemarksResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GeMappingIdByRemarksResponse")
    public ConfigDto geMappingIdByRemarks(
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMappedUsersByUser", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetMappedUsersByUser")
    @ResponseWrapper(localName = "getMappedUsersByUserResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetMappedUsersByUserResponse")
    public ConfigDto getMappedUsersByUser(
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param serviceParam
     * @param configDto
     * @param configParam
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "configOperations", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ConfigOperations")
    @ResponseWrapper(localName = "configOperationsResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ConfigOperationsResponse")
    public ConfigDto configOperations(
        @WebParam(name = "ServiceParam", targetNamespace = "")
        String serviceParam,
        @WebParam(name = "ConfigParam", targetNamespace = "")
        String configParam,
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @param operationParam
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "auditLogOperation", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.AuditLogOperation")
    @ResponseWrapper(localName = "auditLogOperationResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.AuditLogOperationResponse")
    public ConfigDto auditLogOperation(
        @WebParam(name = "operationParam", targetNamespace = "")
        String operationParam,
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMappingIdtByAppointMents", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetMappingIdtByAppointMents")
    @ResponseWrapper(localName = "getMappingIdtByAppointMentsResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetMappingIdtByAppointMentsResponse")
    public ConfigDto getMappingIdtByAppointMents(
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "saveAppointment", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.SaveAppointment")
    @ResponseWrapper(localName = "saveAppointmentResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.SaveAppointmentResponse")
    public ConfigDto saveAppointment(
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "changeInboxBin", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ChangeInboxBin")
    @ResponseWrapper(localName = "changeInboxBinResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ChangeInboxBinResponse")
    public ConfigDto changeInboxBin(
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param configDto
     * @return
     *     returns com.np.tele.crm.service.client.ConfigDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getInbox", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetInbox")
    @ResponseWrapper(localName = "getInboxResponse", targetNamespace = "http://service.config.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetInboxResponse")
    public ConfigDto getInbox(
        @WebParam(name = "ConfigDto", targetNamespace = "")
        ConfigDto configDto)
        throws SOAPException_Exception
    ;

}
