
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for externalApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="externalApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="masterDto" type="{http://service.masterdata.crm.tele.np.com/}masterDataDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "externalApplication", propOrder = {
    "serviceParam",
    "masterDto"
})
public class ExternalApplication {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    protected MasterDataDto masterDto;

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
     * Gets the value of the masterDto property.
     * 
     * @return
     *     possible object is
     *     {@link MasterDataDto }
     *     
     */
    public MasterDataDto getMasterDto() {
        return masterDto;
    }

    /**
     * Sets the value of the masterDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterDataDto }
     *     
     */
    public void setMasterDto(MasterDataDto value) {
        this.masterDto = value;
    }

}
