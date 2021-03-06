
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for eventTemplate complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="eventTemplate">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "eventTemplate", propOrder = {
    "serviceParam",
    "alertDto"
})
public class EventTemplate {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    @XmlElement(name = "AlertDto")
    protected AlertsDto alertDto;

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
