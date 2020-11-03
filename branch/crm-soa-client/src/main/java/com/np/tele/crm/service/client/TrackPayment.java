
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for trackPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="trackPayment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ServiceParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CrmParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CrmFinanceDto" type="{http://service.finance.crm.tele.np.com/}crmFinanceDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "trackPayment", propOrder = {
    "serviceParam",
    "crmParam",
    "crmFinanceDto"
})
public class TrackPayment {

    @XmlElement(name = "ServiceParam")
    protected String serviceParam;
    @XmlElement(name = "CrmParam")
    protected String crmParam;
    @XmlElement(name = "CrmFinanceDto")
    protected CrmFinanceDto crmFinanceDto;

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
     * Gets the value of the crmParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCrmParam() {
        return crmParam;
    }

    /**
     * Sets the value of the crmParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCrmParam(String value) {
        this.crmParam = value;
    }

    /**
     * Gets the value of the crmFinanceDto property.
     * 
     * @return
     *     possible object is
     *     {@link CrmFinanceDto }
     *     
     */
    public CrmFinanceDto getCrmFinanceDto() {
        return crmFinanceDto;
    }

    /**
     * Sets the value of the crmFinanceDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmFinanceDto }
     *     
     */
    public void setCrmFinanceDto(CrmFinanceDto value) {
        this.crmFinanceDto = value;
    }

}
