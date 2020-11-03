
package org.datacontract.schemas._2004._07;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CustomerActivation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerActivation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="chargeamount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="chargename" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crfNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dynamicIpPool" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ftApprovalDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="nasPortId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noOfWiringPoints" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="option82" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="primaryMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resellerNWPartner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="secondaryMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="staticIp" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workOrderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerActivation", propOrder = {
    "chargeamount",
    "chargename",
    "crfNo",
    "deviceOwner",
    "dynamicIpPool",
    "ftApprovalDate",
    "nasPortId",
    "noOfWiringPoints",
    "option82",
    "password",
    "primaryMacId",
    "resellerNWPartner",
    "secondaryMacId",
    "staticIp",
    "userName",
    "workOrderDate"
})
public class CustomerActivation {

    protected BigDecimal chargeamount;
    @XmlElementRef(name = "chargename", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> chargename;
    @XmlElementRef(name = "crfNo", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> crfNo;
    @XmlElementRef(name = "deviceOwner", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> deviceOwner;
    @XmlElementRef(name = "dynamicIpPool", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> dynamicIpPool;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar ftApprovalDate;
    @XmlElementRef(name = "nasPortId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> nasPortId;
    protected Integer noOfWiringPoints;
    @XmlElementRef(name = "option82", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> option82;
    @XmlElementRef(name = "password", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> password;
    @XmlElementRef(name = "primaryMacId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> primaryMacId;
    @XmlElementRef(name = "resellerNWPartner", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> resellerNWPartner;
    @XmlElementRef(name = "secondaryMacId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> secondaryMacId;
    @XmlElementRef(name = "staticIp", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> staticIp;
    @XmlElementRef(name = "userName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> userName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar workOrderDate;

    /**
     * Gets the value of the chargeamount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getChargeamount() {
        return chargeamount;
    }

    /**
     * Sets the value of the chargeamount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setChargeamount(BigDecimal value) {
        this.chargeamount = value;
    }

    /**
     * Gets the value of the chargename property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getChargename() {
        return chargename;
    }

    /**
     * Sets the value of the chargename property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setChargename(JAXBElement<String> value) {
        this.chargename = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the crfNo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCrfNo() {
        return crfNo;
    }

    /**
     * Sets the value of the crfNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCrfNo(JAXBElement<String> value) {
        this.crfNo = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the deviceOwner property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDeviceOwner() {
        return deviceOwner;
    }

    /**
     * Sets the value of the deviceOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDeviceOwner(JAXBElement<String> value) {
        this.deviceOwner = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the dynamicIpPool property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDynamicIpPool() {
        return dynamicIpPool;
    }

    /**
     * Sets the value of the dynamicIpPool property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDynamicIpPool(JAXBElement<String> value) {
        this.dynamicIpPool = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the ftApprovalDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFtApprovalDate() {
        return ftApprovalDate;
    }

    /**
     * Sets the value of the ftApprovalDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFtApprovalDate(XMLGregorianCalendar value) {
        this.ftApprovalDate = value;
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
     * Gets the value of the noOfWiringPoints property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNoOfWiringPoints() {
        return noOfWiringPoints;
    }

    /**
     * Sets the value of the noOfWiringPoints property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNoOfWiringPoints(Integer value) {
        this.noOfWiringPoints = value;
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
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPassword(JAXBElement<String> value) {
        this.password = ((JAXBElement<String> ) value);
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
     * Gets the value of the resellerNWPartner property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getResellerNWPartner() {
        return resellerNWPartner;
    }

    /**
     * Sets the value of the resellerNWPartner property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setResellerNWPartner(JAXBElement<String> value) {
        this.resellerNWPartner = ((JAXBElement<String> ) value);
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

    /**
     * Gets the value of the workOrderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWorkOrderDate() {
        return workOrderDate;
    }

    /**
     * Sets the value of the workOrderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWorkOrderDate(XMLGregorianCalendar value) {
        this.workOrderDate = value;
    }

}
