
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for selfcareOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="selfcareOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="SelfcareDto" type="{http://service.cap.crm.tele.np.com/}selfcareDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selfcareOperation", propOrder = {
    "serviceParam",
    "operationParam",
    "selfcareDto"
})
public class SelfcareOperation {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    protected String operationParam;
    @XmlElement(name = "SelfcareDto")
    protected SelfcareDto selfcareDto;

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
     * Gets the value of the selfcareDto property.
     * 
     * @return
     *     possible object is
     *     {@link SelfcareDto }
     *     
     */
    public SelfcareDto getSelfcareDto() {
        return selfcareDto;
    }

    /**
     * Sets the value of the selfcareDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelfcareDto }
     *     
     */
    public void setSelfcareDto(SelfcareDto value) {
        this.selfcareDto = value;
    }

}
