
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
@WebService(name = "MasterData", targetNamespace = "http://service.masterdata.crm.tele.np.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MasterData {


    /**
     * 
     * @param masterDto
     * @param serviceParam
     * @return
     *     returns com.np.tele.crm.service.client.MasterDataDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "partnerOperations", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.PartnerOperations")
    @ResponseWrapper(localName = "partnerOperationsResponse", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.PartnerOperationsResponse")
    public MasterDataDto partnerOperations(
        @WebParam(name = "ServiceParam", targetNamespace = "")
        String serviceParam,
        @WebParam(name = "masterDto", targetNamespace = "")
        MasterDataDto masterDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param masterDto
     * @param pojoName
     * @return
     *     returns com.np.tele.crm.service.client.MasterDataDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getMasterData", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetMasterData")
    @ResponseWrapper(localName = "getMasterDataResponse", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.GetMasterDataResponse")
    public MasterDataDto getMasterData(
        @WebParam(name = "masterDto", targetNamespace = "")
        MasterDataDto masterDto,
        @WebParam(name = "pojoName", targetNamespace = "")
        String pojoName)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param masterDto
     * @param operation
     * @return
     *     returns com.np.tele.crm.service.client.MasterDataDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "rolesGroupOperations", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.RolesGroupOperations")
    @ResponseWrapper(localName = "rolesGroupOperationsResponse", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.RolesGroupOperationsResponse")
    public MasterDataDto rolesGroupOperations(
        @WebParam(name = "masterDto", targetNamespace = "")
        MasterDataDto masterDto,
        @WebParam(name = "operation", targetNamespace = "")
        String operation)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param masterDto
     * @param serviceParam
     * @param masterType
     * @return
     *     returns com.np.tele.crm.service.client.MasterDataDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "masterOperations", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.MasterOperations")
    @ResponseWrapper(localName = "masterOperationsResponse", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.MasterOperationsResponse")
    public MasterDataDto masterOperations(
        @WebParam(name = "ServiceParam", targetNamespace = "")
        String serviceParam,
        @WebParam(name = "MasterType", targetNamespace = "")
        String masterType,
        @WebParam(name = "masterDto", targetNamespace = "")
        MasterDataDto masterDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param masterDto
     * @param serviceParam
     * @return
     *     returns com.np.tele.crm.service.client.MasterDataDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "externalApplication", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ExternalApplication")
    @ResponseWrapper(localName = "externalApplicationResponse", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ExternalApplicationResponse")
    public MasterDataDto externalApplication(
        @WebParam(name = "ServiceParam", targetNamespace = "")
        String serviceParam,
        @WebParam(name = "masterDto", targetNamespace = "")
        MasterDataDto masterDto)
        throws SOAPException_Exception
    ;

    /**
     * 
     * @param masterDto
     * @param operation
     * @return
     *     returns com.np.tele.crm.service.client.MasterDataDto
     * @throws SOAPException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "parameterGroupOperations", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ParameterGroupOperations")
    @ResponseWrapper(localName = "parameterGroupOperationsResponse", targetNamespace = "http://service.masterdata.crm.tele.np.com/", className = "com.np.tele.crm.service.client.ParameterGroupOperationsResponse")
    public MasterDataDto parameterGroupOperations(
        @WebParam(name = "masterDto", targetNamespace = "")
        MasterDataDto masterDto,
        @WebParam(name = "operation", targetNamespace = "")
        String operation)
        throws SOAPException_Exception
    ;

}
