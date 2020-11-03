
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmPgwTransactionsPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmPgwTransactionsPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="billingPaymentStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="initiationDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="paymentOption" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentReciept" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwAuthCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwAuthDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pgwAvr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwErrorMsg" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwPaymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwPaymentPage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwPostdate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwReferenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwResponseDatetime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pgwResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwTrackId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pgwTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recordId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="redirectUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="udf1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="udf2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="udf3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="udf4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="udf5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmPgwTransactionsPojo", propOrder = {
    "amount",
    "billingPaymentStatus",
    "customerId",
    "initiationDatetime",
    "paymentOption",
    "paymentReciept",
    "pgwAuthCode",
    "pgwAuthDatetime",
    "pgwAvr",
    "pgwErrorCode",
    "pgwErrorMsg",
    "pgwName",
    "pgwPaymentId",
    "pgwPaymentPage",
    "pgwPostdate",
    "pgwReferenceNumber",
    "pgwResponseDatetime",
    "pgwResult",
    "pgwTrackId",
    "pgwTransactionId",
    "recordId",
    "redirectUrl",
    "status",
    "udf1",
    "udf2",
    "udf3",
    "udf4",
    "udf5",
    "appReturnUrl"
})
public class CrmPgwTransactionsPojo {

    protected double amount;
    protected String billingPaymentStatus;
    protected String customerId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar initiationDatetime;
    protected String paymentOption;
    protected String paymentReciept;
    protected String pgwAuthCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pgwAuthDatetime;
    protected String pgwAvr;
    protected String pgwErrorCode;
    protected String pgwErrorMsg;
    protected String pgwName;
    protected String pgwPaymentId;
    protected String pgwPaymentPage;
    protected String pgwPostdate;
    protected String pgwReferenceNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pgwResponseDatetime;
    protected String pgwResult;
    protected String pgwTrackId;
    protected String pgwTransactionId;
    protected Long recordId;
    protected String redirectUrl;
    protected String status;
    protected String udf1;
    protected String udf2;
    protected String udf3;
    protected String udf4;
    protected String udf5;
    protected String appReturnUrl;

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the billingPaymentStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingPaymentStatus() {
        return billingPaymentStatus;
    }

    /**
     * Sets the value of the billingPaymentStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingPaymentStatus(String value) {
        this.billingPaymentStatus = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the initiationDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInitiationDatetime() {
        return initiationDatetime;
    }

    /**
     * Sets the value of the initiationDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInitiationDatetime(XMLGregorianCalendar value) {
        this.initiationDatetime = value;
    }

    /**
     * Gets the value of the paymentOption property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentOption() {
        return paymentOption;
    }

    /**
     * Sets the value of the paymentOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentOption(String value) {
        this.paymentOption = value;
    }

    /**
     * Gets the value of the paymentReciept property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentReciept() {
        return paymentReciept;
    }

    /**
     * Sets the value of the paymentReciept property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentReciept(String value) {
        this.paymentReciept = value;
    }

    /**
     * Gets the value of the pgwAuthCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwAuthCode() {
        return pgwAuthCode;
    }

    /**
     * Sets the value of the pgwAuthCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwAuthCode(String value) {
        this.pgwAuthCode = value;
    }

    /**
     * Gets the value of the pgwAuthDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPgwAuthDatetime() {
        return pgwAuthDatetime;
    }

    /**
     * Sets the value of the pgwAuthDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPgwAuthDatetime(XMLGregorianCalendar value) {
        this.pgwAuthDatetime = value;
    }

    /**
     * Gets the value of the pgwAvr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwAvr() {
        return pgwAvr;
    }

    /**
     * Sets the value of the pgwAvr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwAvr(String value) {
        this.pgwAvr = value;
    }

    /**
     * Gets the value of the pgwErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwErrorCode() {
        return pgwErrorCode;
    }

    /**
     * Sets the value of the pgwErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwErrorCode(String value) {
        this.pgwErrorCode = value;
    }

    /**
     * Gets the value of the pgwErrorMsg property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwErrorMsg() {
        return pgwErrorMsg;
    }

    /**
     * Sets the value of the pgwErrorMsg property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwErrorMsg(String value) {
        this.pgwErrorMsg = value;
    }

    /**
     * Gets the value of the pgwName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwName() {
        return pgwName;
    }

    /**
     * Sets the value of the pgwName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwName(String value) {
        this.pgwName = value;
    }

    /**
     * Gets the value of the pgwPaymentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwPaymentId() {
        return pgwPaymentId;
    }

    /**
     * Sets the value of the pgwPaymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwPaymentId(String value) {
        this.pgwPaymentId = value;
    }

    /**
     * Gets the value of the pgwPaymentPage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwPaymentPage() {
        return pgwPaymentPage;
    }

    /**
     * Sets the value of the pgwPaymentPage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwPaymentPage(String value) {
        this.pgwPaymentPage = value;
    }

    /**
     * Gets the value of the pgwPostdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwPostdate() {
        return pgwPostdate;
    }

    /**
     * Sets the value of the pgwPostdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwPostdate(String value) {
        this.pgwPostdate = value;
    }

    /**
     * Gets the value of the pgwReferenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwReferenceNumber() {
        return pgwReferenceNumber;
    }

    /**
     * Sets the value of the pgwReferenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwReferenceNumber(String value) {
        this.pgwReferenceNumber = value;
    }

    /**
     * Gets the value of the pgwResponseDatetime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPgwResponseDatetime() {
        return pgwResponseDatetime;
    }

    /**
     * Sets the value of the pgwResponseDatetime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPgwResponseDatetime(XMLGregorianCalendar value) {
        this.pgwResponseDatetime = value;
    }

    /**
     * Gets the value of the pgwResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwResult() {
        return pgwResult;
    }

    /**
     * Sets the value of the pgwResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwResult(String value) {
        this.pgwResult = value;
    }

    /**
     * Gets the value of the pgwTrackId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwTrackId() {
        return pgwTrackId;
    }

    /**
     * Sets the value of the pgwTrackId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwTrackId(String value) {
        this.pgwTrackId = value;
    }

    /**
     * Gets the value of the pgwTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPgwTransactionId() {
        return pgwTransactionId;
    }

    /**
     * Sets the value of the pgwTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPgwTransactionId(String value) {
        this.pgwTransactionId = value;
    }

    /**
     * Gets the value of the recordId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * Sets the value of the recordId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecordId(Long value) {
        this.recordId = value;
    }

    /**
     * Gets the value of the redirectUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedirectUrl() {
        return redirectUrl;
    }

    /**
     * Sets the value of the redirectUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedirectUrl(String value) {
        this.redirectUrl = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Gets the value of the udf1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf1() {
        return udf1;
    }

    /**
     * Sets the value of the udf1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf1(String value) {
        this.udf1 = value;
    }

    /**
     * Gets the value of the udf2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf2() {
        return udf2;
    }

    /**
     * Sets the value of the udf2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf2(String value) {
        this.udf2 = value;
    }

    /**
     * Gets the value of the udf3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf3() {
        return udf3;
    }

    /**
     * Sets the value of the udf3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf3(String value) {
        this.udf3 = value;
    }

    /**
     * Gets the value of the udf4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf4() {
        return udf4;
    }

    /**
     * Sets the value of the udf4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf4(String value) {
        this.udf4 = value;
    }

    /**
     * Gets the value of the udf5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUdf5() {
        return udf5;
    }

    /**
     * Sets the value of the udf5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUdf5(String value) {
        this.udf5 = value;
    }

    public String getAppReturnUrl()
    {
        return appReturnUrl;
    }

    public void setAppReturnUrl( String inAppReturnUrl )
    {
        appReturnUrl = inAppReturnUrl;
    }

}
