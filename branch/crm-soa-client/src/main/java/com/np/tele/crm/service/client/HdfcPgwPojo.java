
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hdfcPgwPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="hdfcPgwPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currency_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="error_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gateway_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="language_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="req_action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="response_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportal_id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transportal_password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "hdfcPgwPojo", propOrder = {
    "currencyCode",
    "errorUrl",
    "gatewayUrl",
    "languageCode",
    "reqAction",
    "responseUrl",
    "transportalId",
    "transportalPassword"
})
public class HdfcPgwPojo {

    @XmlElement(name = "currency_code")
    protected String currencyCode;
    @XmlElement(name = "error_url")
    protected String errorUrl;
    @XmlElement(name = "gateway_url")
    protected String gatewayUrl;
    @XmlElement(name = "language_code")
    protected String languageCode;
    @XmlElement(name = "req_action")
    protected String reqAction;
    @XmlElement(name = "response_url")
    protected String responseUrl;
    @XmlElement(name = "transportal_id")
    protected String transportalId;
    @XmlElement(name = "transportal_password")
    protected String transportalPassword;

    /**
     * Gets the value of the currencyCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrencyCode() {
        return currencyCode;
    }

    /**
     * Sets the value of the currencyCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrencyCode(String value) {
        this.currencyCode = value;
    }

    /**
     * Gets the value of the errorUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrorUrl() {
        return errorUrl;
    }

    /**
     * Sets the value of the errorUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrorUrl(String value) {
        this.errorUrl = value;
    }

    /**
     * Gets the value of the gatewayUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGatewayUrl() {
        return gatewayUrl;
    }

    /**
     * Sets the value of the gatewayUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGatewayUrl(String value) {
        this.gatewayUrl = value;
    }

    /**
     * Gets the value of the languageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLanguageCode() {
        return languageCode;
    }

    /**
     * Sets the value of the languageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLanguageCode(String value) {
        this.languageCode = value;
    }

    /**
     * Gets the value of the reqAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReqAction() {
        return reqAction;
    }

    /**
     * Sets the value of the reqAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReqAction(String value) {
        this.reqAction = value;
    }

    /**
     * Gets the value of the responseUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseUrl() {
        return responseUrl;
    }

    /**
     * Sets the value of the responseUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseUrl(String value) {
        this.responseUrl = value;
    }

    /**
     * Gets the value of the transportalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportalId() {
        return transportalId;
    }

    /**
     * Sets the value of the transportalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportalId(String value) {
        this.transportalId = value;
    }

    /**
     * Gets the value of the transportalPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportalPassword() {
        return transportalPassword;
    }

    /**
     * Sets the value of the transportalPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportalPassword(String value) {
        this.transportalPassword = value;
    }

}
