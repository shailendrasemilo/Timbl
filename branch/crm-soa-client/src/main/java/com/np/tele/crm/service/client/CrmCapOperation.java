
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmCapOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCapOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CrmCapDto" type="{http://service.cap.crm.tele.np.com/}crmCapDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCapOperation", propOrder = {
    "serviceParam",
    "operationParam",
    "crmCapDto"
})
public class CrmCapOperation {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    protected String operationParam;
    @XmlElement(name = "CrmCapDto")
    protected CrmCapDto crmCapDto;

    /**
     * Gets the value of the serviceParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceParam() {
        return serviceParam;
    }

    /**
     * Sets the value of the serviceParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceParam(String value) {
        this.serviceParam = value;
    }

    /**
     * Gets the value of the operationParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationParam() {
        return operationParam;
    }

    /**
     * Sets the value of the operationParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationParam(String value) {
        this.operationParam = value;
    }

    /**
     * Gets the value of the crmCapDto property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCapDto }
     *     
     */
    public CrmCapDto getCrmCapDto() {
        return crmCapDto;
    }

    /**
     * Sets the value of the crmCapDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCapDto }
     *     
     */
    public void setCrmCapDto(CrmCapDto value) {
        this.crmCapDto = value;
    }

}
