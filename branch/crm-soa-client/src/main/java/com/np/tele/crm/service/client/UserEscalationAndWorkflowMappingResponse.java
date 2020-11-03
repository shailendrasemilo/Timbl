
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userEscalationAndWorkflowMappingResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userEscalationAndWorkflowMappingResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://service.usrmngmnt.crm.tele.np.com/}crmuserDetailsDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userEscalationAndWorkflowMappingResponse", propOrder = {
    "_return"
})
public class UserEscalationAndWorkflowMappingResponse {

    @XmlElement(name = "return")
    protected CrmuserDetailsDto _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link CrmuserDetailsDto }
     *     
     */
    public CrmuserDetailsDto getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmuserDetailsDto }
     *     
     */
    public void setReturn(CrmuserDetailsDto value) {
        this._return = value;
    }

}
