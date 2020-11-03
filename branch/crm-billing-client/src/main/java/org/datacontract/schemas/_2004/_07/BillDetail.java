
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
 * <p>Java class for BillDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="adjustment" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="billEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="billNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="chargesDetail" type="{http://schemas.datacontract.org/2004/07/}ArrayOfGetChargeDetailResult" minOccurs="0"/>
 *         &lt;element name="createDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentCharges" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="customerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousBalance" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillDetail", propOrder = {
    "adjustment",
    "billEndDate",
    "billNumber",
    "billStartDate",
    "chargesDetail",
    "createDate",
    "currentCharges",
    "customerName",
    "previousBalance"
})
public class BillDetail
    extends ApiResponse
{

    protected BigDecimal adjustment;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar billEndDate;
    @XmlElementRef(name = "billNumber", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> billNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar billStartDate;
    @XmlElementRef(name = "chargesDetail", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfGetChargeDetailResult> chargesDetail;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    protected BigDecimal currentCharges;
    @XmlElementRef(name = "customerName", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> customerName;
    protected BigDecimal previousBalance;

    /**
     * Gets the value of the adjustment property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getAdjustment() {
        return adjustment;
    }

    /**
     * Sets the value of the adjustment property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setAdjustment(BigDecimal value) {
        this.adjustment = value;
    }

    /**
     * Gets the value of the billEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBillEndDate() {
        return billEndDate;
    }

    /**
     * Sets the value of the billEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBillEndDate(XMLGregorianCalendar value) {
        this.billEndDate = value;
    }

    /**
     * Gets the value of the billNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getBillNumber() {
        return billNumber;
    }

    /**
     * Sets the value of the billNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setBillNumber(JAXBElement<String> value) {
        this.billNumber = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the billStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBillStartDate() {
        return billStartDate;
    }

    /**
     * Sets the value of the billStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBillStartDate(XMLGregorianCalendar value) {
        this.billStartDate = value;
    }

    /**
     * Gets the value of the chargesDetail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetChargeDetailResult }{@code >}
     *     
     */
    public JAXBElement<ArrayOfGetChargeDetailResult> getChargesDetail() {
        return chargesDetail;
    }

    /**
     * Sets the value of the chargesDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetChargeDetailResult }{@code >}
     *     
     */
    public void setChargesDetail(JAXBElement<ArrayOfGetChargeDetailResult> value) {
        this.chargesDetail = ((JAXBElement<ArrayOfGetChargeDetailResult> ) value);
    }

    /**
     * Gets the value of the createDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * Sets the value of the createDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * Gets the value of the currentCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentCharges() {
        return currentCharges;
    }

    /**
     * Sets the value of the currentCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentCharges(BigDecimal value) {
        this.currentCharges = value;
    }

    /**
     * Gets the value of the customerName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCustomerName() {
        return customerName;
    }

    /**
     * Sets the value of the customerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCustomerName(JAXBElement<String> value) {
        this.customerName = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the previousBalance property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    /**
     * Sets the value of the previousBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPreviousBalance(BigDecimal value) {
        this.previousBalance = value;
    }

}
