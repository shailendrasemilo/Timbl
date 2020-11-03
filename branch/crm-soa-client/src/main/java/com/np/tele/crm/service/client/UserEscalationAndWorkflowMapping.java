
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userEscalationAndWorkflowMapping complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userEscalationAndWorkflowMapping">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userDto" type="{http://service.usrmngmnt.crm.tele.np.com/}crmuserDetailsDto" minOccurs="0"/>
 *         &lt;element name="operationName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userEscalationAndWorkflowMapping", propOrder = {
    "userDto",
    "operationName"
})
public class UserEscalationAndWorkflowMapping {

    protected CrmuserDetailsDto userDto;
    protected String operationName;

    /**
     * Gets the value of the userDto property.
     * 
     * @return
     *     possible object is
     *     {@link CrmuserDetailsDto }
     *     
     */
    public CrmuserDetailsDto getUserDto() {
        return userDto;
    }

    /**
     * Sets the value of the userDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmuserDetailsDto }
     *     
     */
    public void setUserDto(CrmuserDetailsDto value) {
        this.userDto = value;
    }

    /**
     * Gets the value of the operationName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationName() {
        return operationName;
    }

    /**
     * Sets the value of the operationName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationName(String value) {
        this.operationName = value;
    }

}
