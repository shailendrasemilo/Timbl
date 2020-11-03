
package com.np.tele.crm.service.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for slaReportPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="slaReportPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="day10_15Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day1_2Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day2_3Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day3_4Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day4_5Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day5_6Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day6_7Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="day7_10Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="dayAbove15Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="expectedSlaTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="hr0_4Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="hr4_24Count" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="qrcType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="slaTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subSubCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "slaReportPojo", propOrder = {
    "category",
    "day1015Count",
    "day12Count",
    "day23Count",
    "day34Count",
    "day45Count",
    "day56Count",
    "day67Count",
    "day710Count",
    "dayAbove15Count",
    "expectedSlaTime",
    "hr04Count",
    "hr424Count",
    "qrcType",
    "slaTime",
    "subCategory",
    "subSubCategory",
    "totalCount",
    "userName"
})
public class SlaReportPojo {

    protected String category;
    @XmlElement(name = "day10_15Count")
    protected BigInteger day1015Count;
    @XmlElement(name = "day1_2Count")
    protected BigInteger day12Count;
    @XmlElement(name = "day2_3Count")
    protected BigInteger day23Count;
    @XmlElement(name = "day3_4Count")
    protected BigInteger day34Count;
    @XmlElement(name = "day4_5Count")
    protected BigInteger day45Count;
    @XmlElement(name = "day5_6Count")
    protected BigInteger day56Count;
    @XmlElement(name = "day6_7Count")
    protected BigInteger day67Count;
    @XmlElement(name = "day7_10Count")
    protected BigInteger day710Count;
    protected BigInteger dayAbove15Count;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar expectedSlaTime;
    @XmlElement(name = "hr0_4Count")
    protected BigInteger hr04Count;
    @XmlElement(name = "hr4_24Count")
    protected BigInteger hr424Count;
    protected String qrcType;
    protected String slaTime;
    protected String subCategory;
    protected String subSubCategory;
    protected BigInteger totalCount;
    protected String userName;

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
     * Gets the value of the day1015Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay1015Count() {
        return day1015Count;
    }

    /**
     * Sets the value of the day1015Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay1015Count(BigInteger value) {
        this.day1015Count = value;
    }

    /**
     * Gets the value of the day12Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay12Count() {
        return day12Count;
    }

    /**
     * Sets the value of the day12Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay12Count(BigInteger value) {
        this.day12Count = value;
    }

    /**
     * Gets the value of the day23Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay23Count() {
        return day23Count;
    }

    /**
     * Sets the value of the day23Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay23Count(BigInteger value) {
        this.day23Count = value;
    }

    /**
     * Gets the value of the day34Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay34Count() {
        return day34Count;
    }

    /**
     * Sets the value of the day34Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay34Count(BigInteger value) {
        this.day34Count = value;
    }

    /**
     * Gets the value of the day45Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay45Count() {
        return day45Count;
    }

    /**
     * Sets the value of the day45Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay45Count(BigInteger value) {
        this.day45Count = value;
    }

    /**
     * Gets the value of the day56Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay56Count() {
        return day56Count;
    }

    /**
     * Sets the value of the day56Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay56Count(BigInteger value) {
        this.day56Count = value;
    }

    /**
     * Gets the value of the day67Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay67Count() {
        return day67Count;
    }

    /**
     * Sets the value of the day67Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay67Count(BigInteger value) {
        this.day67Count = value;
    }

    /**
     * Gets the value of the day710Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDay710Count() {
        return day710Count;
    }

    /**
     * Sets the value of the day710Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDay710Count(BigInteger value) {
        this.day710Count = value;
    }

    /**
     * Gets the value of the dayAbove15Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDayAbove15Count() {
        return dayAbove15Count;
    }

    /**
     * Sets the value of the dayAbove15Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDayAbove15Count(BigInteger value) {
        this.dayAbove15Count = value;
    }

    /**
     * Gets the value of the expectedSlaTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpectedSlaTime() {
        return expectedSlaTime;
    }

    /**
     * Sets the value of the expectedSlaTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpectedSlaTime(XMLGregorianCalendar value) {
        this.expectedSlaTime = value;
    }

    /**
     * Gets the value of the hr04Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHr04Count() {
        return hr04Count;
    }

    /**
     * Sets the value of the hr04Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHr04Count(BigInteger value) {
        this.hr04Count = value;
    }

    /**
     * Gets the value of the hr424Count property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getHr424Count() {
        return hr424Count;
    }

    /**
     * Sets the value of the hr424Count property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setHr424Count(BigInteger value) {
        this.hr424Count = value;
    }

    /**
     * Gets the value of the qrcType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQrcType() {
        return qrcType;
    }

    /**
     * Sets the value of the qrcType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQrcType(String value) {
        this.qrcType = value;
    }

    /**
     * Gets the value of the slaTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSlaTime() {
        return slaTime;
    }

    /**
     * Sets the value of the slaTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSlaTime(String value) {
        this.slaTime = value;
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
     * Gets the value of the subSubCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubSubCategory() {
        return subSubCategory;
    }

    /**
     * Sets the value of the subSubCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubSubCategory(String value) {
        this.subSubCategory = value;
    }

    /**
     * Gets the value of the totalCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalCount() {
        return totalCount;
    }

    /**
     * Sets the value of the totalCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalCount(BigInteger value) {
        this.totalCount = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

}
