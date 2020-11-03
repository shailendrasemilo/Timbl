
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for auditLogOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="auditLogOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operationParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "auditLogOperation", propOrder = {
    "operationParam",
    "configDto"
})
public class AuditLogOperation {

    protected String operationParam;
    @XmlElement(name = "ConfigDto")
    protected ConfigDto configDto;

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
