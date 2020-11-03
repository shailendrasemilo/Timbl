
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmQrcCommonPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmQrcCommonPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="action" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRecordId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="rcaReasonPojo" type="{http://service.qrc.crm.tele.np.com/}crmRcaReasonPojo" minOccurs="0"/>
 *         &lt;element name="remarksPojo" type="{http://service.qrc.crm.tele.np.com/}remarksPojo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmQrcCommonPojo", propOrder = {
    "action",
    "customerId",
    "customerRecordId",
    "rcaReasonPojo",
    "remarksPojo"
})
public class CrmQrcCommonPojo {

    protected String action;
    protected String customerId;
    protected long customerRecordId;
    protected CrmRcaReasonPojo rcaReasonPojo;
    protected RemarksPojo remarksPojo;

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAction(String value) {
        this.action = value;
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
     * Gets the value of the customerRecordId property.
     * 
     */
    public long getCustomerRecordId() {
        return customerRecordId;
    }

    /**
     * Sets the value of the customerRecordId property.
     * 
     */
    public void setCustomerRecordId(long value) {
        this.customerRecordId = value;
    }

    /**
     * Gets the value of the rcaReasonPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmRcaReasonPojo }
     *     
     */
    public CrmRcaReasonPojo getRcaReasonPojo() {
        return rcaReasonPojo;
    }

    /**
     * Sets the value of the rcaReasonPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmRcaReasonPojo }
     *     
     */
    public void setRcaReasonPojo(CrmRcaReasonPojo value) {
        this.rcaReasonPojo = value;
    }

    /**
     * Gets the value of the remarksPojo property.
     * 
     * @return
     *     possible object is
     *     {@link RemarksPojo }
     *     
     */
    public RemarksPojo getRemarksPojo() {
        return remarksPojo;
    }

    /**
     * Sets the value of the remarksPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link RemarksPojo }
     *     
     */
    public void setRemarksPojo(RemarksPojo value) {
        this.remarksPojo = value;
    }

}
