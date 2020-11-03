
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getUsersByParameter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getUsersByParameter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userDto" type="{http://service.usrmngmnt.crm.tele.np.com/}crmuserDetailsDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getUsersByParameter", propOrder = {
    "userDto"
})
public class GetUsersByParameter {

    protected CrmuserDetailsDto userDto;

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

}
