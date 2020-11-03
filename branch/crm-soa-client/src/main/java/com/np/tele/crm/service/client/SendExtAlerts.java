
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendExtAlerts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendExtAlerts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ExtAlertDto" type="{http://service.alerts.crm.tele.np.com/}extAlertDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendExtAlerts", propOrder = {
    "extAlertDto"
})
public class SendExtAlerts {

    @XmlElement(name = "ExtAlertDto")
    protected ExtAlertDto extAlertDto;

    /**
     * Gets the value of the extAlertDto property.
     * 
     * @return
     *     possible object is
     *     {@link ExtAlertDto }
     *     
     */
    public ExtAlertDto getExtAlertDto() {
        return extAlertDto;
    }

    /**
     * Sets the value of the extAlertDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link ExtAlertDto }
     *     
     */
    public void setExtAlertDto(ExtAlertDto value) {
        this.extAlertDto = value;
    }

}
