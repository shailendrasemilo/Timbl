
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendAlerts complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendAlerts">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AlertDto" type="{http://service.alerts.crm.tele.np.com/}alertsDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendAlerts", propOrder = {
    "alertDto"
})
public class SendAlerts {

    @XmlElement(name = "AlertDto")
    protected AlertsDto alertDto;

    /**
     * Gets the value of the alertDto property.
     * 
     * @return
     *     possible object is
     *     {@link AlertsDto }
     *     
     */
    public AlertsDto getAlertDto() {
        return alertDto;
    }

    /**
     * Sets the value of the alertDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link AlertsDto }
     *     
     */
    public void setAlertDto(AlertsDto value) {
        this.alertDto = value;
    }

}
