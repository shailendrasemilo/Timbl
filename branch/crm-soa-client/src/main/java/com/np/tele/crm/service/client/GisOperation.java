
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gisOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gisOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="operationParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="GISDto" type="{http://service.gis.crm.tele.np.com/}gisDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "gisOperation", propOrder = {
    "serviceParam",
    "operationParam",
    "gisDto"
})
public class GisOperation {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    protected String operationParam;
    @XmlElement(name = "GISDto")
    protected GisDto gisDto;

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
     * Gets the value of the gisDto property.
     * 
     * @return
     *     possible object is
     *     {@link GisDto }
     *     
     */
    public GisDto getGISDto() {
        return gisDto;
    }

    /**
     * Sets the value of the gisDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link GisDto }
     *     
     */
    public void setGISDto(GisDto value) {
        this.gisDto = value;
    }

}
