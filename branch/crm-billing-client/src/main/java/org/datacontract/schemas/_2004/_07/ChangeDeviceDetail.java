
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ChangeDeviceDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChangeDeviceDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="MACFaultyStatus" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="cpePassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="deviceOwnership" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dynamicPool" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nasPortId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="option82" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staticIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChangeDeviceDetail", propOrder = {
    "macFaultyStatus",
    "cpePassword",
    "customerNo",
    "deviceOwnership",
    "dynamicPool",
    "nasPortId",
    "option82",
    "primaryMacId",
    "secondaryMacId",
    "serviceModel",
    "staticIp",
    "userName"
})
public class ChangeDeviceDetail
    extends ApiResponse
{

    @XmlElement(name = "MACFaultyStatus")
    protected Boolean macFaultyStatus;
    @XmlElementRef(name = "cpePassword", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> cpePassword;
    protected Long customerNo;
    @XmlElementRef(name = "deviceOwnership", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> deviceOwnership;
    @XmlElementRef(name = "dynamicPool", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> dynamicPool;
    @XmlElementRef(name = "nasPortId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> nasPortId;
    @XmlElementRef(name = "option82", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> option82;
    @XmlElementRef(name = "primaryMacId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> primaryMacId;
    @XmlElementRef(name = "secondaryMacId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> secondaryMacId;
    @XmlElementRef(name = "serviceModel", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> serviceModel;
    @XmlElementRef(name = "staticIp", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> staticIp;
    @XmlElementRef(name = "userName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> userName;

    /**
     * Gets the value of the macFaultyStatus property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMACFaultyStatus() {
        return macFaultyStatus;
    }

    /**
     * Sets the value of the macFaultyStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMACFaultyStatus(Boolean value) {
        this.macFaultyStatus = value;
    }

    /**
     * Gets the value of the cpePassword property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCpePassword() {
        return cpePassword;
    }

    /**
     * Sets the value of the cpePassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCpePassword(JAXBElement<String> value) {
        this.cpePassword = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the customerNo property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCustomerNo() {
        return customerNo;
    }

    /**
     * Sets the value of the customerNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCustomerNo(Long value) {
        this.customerNo = value;
    }

    /**
     * Gets the value of the deviceOwnership property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeviceOwnership() {
        return deviceOwnership;
    }

    /**
     * Sets the value of the deviceOwnership property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeviceOwnership(JAXBElement<String> value) {
        this.deviceOwnership = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dynamicPool property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDynamicPool() {
        return dynamicPool;
    }

    /**
     * Sets the value of the dynamicPool property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDynamicPool(JAXBElement<String> value) {
        this.dynamicPool = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the nasPortId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNasPortId() {
        return nasPortId;
    }

    /**
     * Sets the value of the nasPortId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNasPortId(JAXBElement<String> value) {
        this.nasPortId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the option82 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOption82() {
        return option82;
    }

    /**
     * Sets the value of the option82 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOption82(JAXBElement<String> value) {
        this.option82 = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the primaryMacId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPrimaryMacId() {
        return primaryMacId;
    }

    /**
     * Sets the value of the primaryMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPrimaryMacId(JAXBElement<String> value) {
        this.primaryMacId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the secondaryMacId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSecondaryMacId() {
        return secondaryMacId;
    }

    /**
     * Sets the value of the secondaryMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSecondaryMacId(JAXBElement<String> value) {
        this.secondaryMacId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the serviceModel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceModel() {
        return serviceModel;
    }

    /**
     * Sets the value of the serviceModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceModel(JAXBElement<String> value) {
        this.serviceModel = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the staticIp property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getStaticIp() {
        return staticIp;
    }

    /**
     * Sets the value of the staticIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setStaticIp(JAXBElement<String> value) {
        this.staticIp = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setUserName(JAXBElement<String> value) {
        this.userName = ((JAXBElement<String> ) value);
    }

}
