
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for configOperations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="configOperations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConfigParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ConfigDto" type="{http://service.config.crm.tele.np.com/}configDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "configOperations", propOrder = {
    "serviceParam",
    "configParam",
    "configDto"
})
public class ConfigOperations {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    @XmlElement(name = "ConfigParam")
    protected String configParam;
    @XmlElement(name = "ConfigDto")
    protected ConfigDto configDto;

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
     * Gets the value of the configParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConfigParam() {
        return configParam;
    }

    /**
     * Sets the value of the configParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConfigParam(String value) {
        this.configParam = value;
    }

    /**
     * Gets the value of the configDto property.
     * 
     * @return
     *     possible object is
     *     {@link ConfigDto }
     *     
     */
    public ConfigDto getConfigDto() {
        return configDto;
    }

    /**
     * Sets the value of the configDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link ConfigDto }
     *     
     */
    public void setConfigDto(ConfigDto value) {
        this.configDto = value;
    }

}
