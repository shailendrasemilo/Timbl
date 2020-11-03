
package com.np.tele.crm.service.client;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for customerUsageDetailsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="customerUsageDetailsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="callEndDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerNo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="downloadInKB" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="downloadKB" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="endTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="framedIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="startTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="uploadInKB" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="uploadKB" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         &lt;element name="usageID" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "customerUsageDetailsPojo", propOrder = {
    "callEndDate",
    "createdate",
    "customerNo",
    "downloadInKB",
    "downloadKB",
    "endTime",
    "framedIP",
    "startTime",
    "uploadInKB",
    "uploadKB",
    "usageID"
})
public class CustomerUsageDetailsPojo {

    protected String callEndDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdate;
    protected Long customerNo;
    protected long downloadInKB;
    protected BigDecimal downloadKB;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar endTime;
    protected String framedIP;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    protected long uploadInKB;
    protected BigDecimal uploadKB;
    protected BigDecimal usageID;

    /**
     * Gets the value of the callEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallEndDate() {
        return callEndDate;
    }

    /**
     * Sets the value of the callEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallEndDate(String value) {
        this.callEndDate = value;
    }

    /**
     * Gets the value of the createdate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedate() {
        return createdate;
    }

    /**
     * Sets the value of the createdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedate(XMLGregorianCalendar value) {
        this.createdate = value;
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
     * Gets the value of the downloadInKB property.
     * 
     */
    public long getDownloadInKB() {
        return downloadInKB;
    }

    /**
     * Sets the value of the downloadInKB property.
     * 
     */
    public void setDownloadInKB(long value) {
        this.downloadInKB = value;
    }

    /**
     * Gets the value of the downloadKB property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDownloadKB() {
        return downloadKB;
    }

    /**
     * Sets the value of the downloadKB property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDownloadKB(BigDecimal value) {
        this.downloadKB = value;
    }

    /**
     * Gets the value of the endTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEndTime() {
        return endTime;
    }

    /**
     * Sets the value of the endTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEndTime(XMLGregorianCalendar value) {
        this.endTime = value;
    }

    /**
     * Gets the value of the framedIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFramedIP() {
        return framedIP;
    }

    /**
     * Sets the value of the framedIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFramedIP(String value) {
        this.framedIP = value;
    }

    /**
     * Gets the value of the startTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * Sets the value of the startTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * Gets the value of the uploadInKB property.
     * 
     */
    public long getUploadInKB() {
        return uploadInKB;
    }

    /**
     * Sets the value of the uploadInKB property.
     * 
     */
    public void setUploadInKB(long value) {
        this.uploadInKB = value;
    }

    /**
     * Gets the value of the uploadKB property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUploadKB() {
        return uploadKB;
    }

    /**
     * Sets the value of the uploadKB property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUploadKB(BigDecimal value) {
        this.uploadKB = value;
    }

    /**
     * Gets the value of the usageID property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUsageID() {
        return usageID;
    }

    /**
     * Sets the value of the usageID property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUsageID(BigDecimal value) {
        this.usageID = value;
    }

}
