
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for smsGatewayPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="smsGatewayPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="connection_time_out" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="enable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="response" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="retryValue" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="socket_time_out" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "smsGatewayPojo", propOrder = {
    "alias",
    "category",
    "connectionTimeOut",
    "enable",
    "password",
    "from",
    "response",
    "gatewayProvider",
    "retryValue",
    "socketTimeOut",
    "subCategory",
    "url",
    "userid"
})
public class SmsGatewayPojo {

    protected String alias;
    protected String category;
    @XmlElement(name = "connection_time_out")
    protected int connectionTimeOut;
    protected boolean enable;
    protected String password;
    protected String  from;
    protected String  response;
    protected String   gatewayProvider;
    protected int retryValue;
    @XmlElement(name = "socket_time_out")
    protected int socketTimeOut;
    protected String subCategory;
    protected String url;
    protected String userid;

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the connectionTimeOut property.
     * 
     */
    public int getConnectionTimeOut() {
        return connectionTimeOut;
    }

    /**
     * Sets the value of the connectionTimeOut property.
     * 
     */
    public void setConnectionTimeOut(int value) {
        this.connectionTimeOut = value;
    }

    /**
     * Gets the value of the enable property.
     * 
     */
    public boolean isEnable() {
        return enable;
    }

    /**
     * Sets the value of the enable property.
     * 
     */
    public void setEnable(boolean value) {
        this.enable = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponse(String value) {
        this.response = value;
    }

    /**
     * Gets the value of the retryValue property.
     * 
     */
    public int getRetryValue() {
        return retryValue;
    }

    /**
     * Sets the value of the retryValue property.
     * 
     */
    public void setRetryValue(int value) {
        this.retryValue = value;
    }

    /**
     * Gets the value of the socketTimeOut property.
     * 
     */
    public int getSocketTimeOut() {
        return socketTimeOut;
    }

    /**
     * Sets the value of the socketTimeOut property.
     * 
     */
    public void setSocketTimeOut(int value) {
        this.socketTimeOut = value;
    }

    /**
     * Gets the value of the subCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Sets the value of the subCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubCategory(String value) {
        this.subCategory = value;
    }

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the userid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserid() {
        return userid;
    }

    /**
     * Sets the value of the userid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserid(String value) {
        this.userid = value;
    }

    public String getFrom()
    {
        return from;
    }

    public void setFrom( String inFrom )
    {
        from = inFrom;
    }

    public String getGatewayProvider()
    {
        return gatewayProvider;
    }

    public void setGatewayProvider( String inGatewayProvider )
    {
        gatewayProvider = inGatewayProvider;
    }

}
