
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for massOutageOperations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="massOutageOperations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CrmMassOutageDto" type="{http://service.qrc.crm.tele.np.com/}crmMassOutageDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "massOutageOperations", propOrder = {
    "serviceParam",
    "crmMassOutageDto"
})
public class MassOutageOperations {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    @XmlElement(name = "CrmMassOutageDto")
    protected CrmMassOutageDto crmMassOutageDto;

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
     * Gets the value of the crmMassOutageDto property.
     * 
     * @return
     *     possible object is
     *     {@link CrmMassOutageDto }
     *     
     */
    public CrmMassOutageDto getCrmMassOutageDto() {
        return crmMassOutageDto;
    }

    /**
     * Sets the value of the crmMassOutageDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmMassOutageDto }
     *     
     */
    public void setCrmMassOutageDto(CrmMassOutageDto value) {
        this.crmMassOutageDto = value;
    }

}
