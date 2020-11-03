
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for selfcareDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="selfcareDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custMyAccountPojo" type="{http://service.cap.crm.tele.np.com/}crmCustMyAccountPojo" minOccurs="0"/>
 *         &lt;element name="custMyAccountPojos" type="{http://service.cap.crm.tele.np.com/}crmCustMyAccountPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojo" type="{http://service.cap.crm.tele.np.com/}crmCustomerDetailsPojo" minOccurs="0"/>
 *         &lt;element name="customerDetailsPojos" type="{http://service.cap.crm.tele.np.com/}crmCustomerDetailsPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "selfcareDto", propOrder = {
    "clientIPAddress",
    "custMyAccountPojo",
    "custMyAccountPojos",
    "customerDetailsPojo",
    "customerDetailsPojos",
    "serverIPAddress",
    "statusCode",
    "statusDesc"
})
public class SelfcareDto {

    protected String clientIPAddress;
    protected CrmCustMyAccountPojo custMyAccountPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustMyAccountPojo> custMyAccountPojos;
    protected CrmCustomerDetailsPojo customerDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmCustomerDetailsPojo> customerDetailsPojos;
    protected String serverIPAddress;
    protected String statusCode;
    protected String statusDesc;

    /**
     * Gets the value of the clientIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientIPAddress() {
        return clientIPAddress;
    }

    /**
     * Sets the value of the clientIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientIPAddress(String value) {
        this.clientIPAddress = value;
    }

    /**
     * Gets the value of the custMyAccountPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustMyAccountPojo }
     *     
     */
    public CrmCustMyAccountPojo getCustMyAccountPojo() {
        return custMyAccountPojo;
    }

    /**
     * Sets the value of the custMyAccountPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustMyAccountPojo }
     *     
     */
    public void setCustMyAccountPojo(CrmCustMyAccountPojo value) {
        this.custMyAccountPojo = value;
    }

    /**
     * Gets the value of the custMyAccountPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the custMyAccountPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustMyAccountPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustMyAccountPojo }
     * 
     * 
     */
    public List<CrmCustMyAccountPojo> getCustMyAccountPojos() {
        if (custMyAccountPojos == null) {
            custMyAccountPojos = new ArrayList<CrmCustMyAccountPojo>();
        }
        return this.custMyAccountPojos;
    }

    /**
     * Gets the value of the customerDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public CrmCustomerDetailsPojo getCustomerDetailsPojo() {
        return customerDetailsPojo;
    }

    /**
     * Sets the value of the customerDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmCustomerDetailsPojo }
     *     
     */
    public void setCustomerDetailsPojo(CrmCustomerDetailsPojo value) {
        this.customerDetailsPojo = value;
    }

    /**
     * Gets the value of the customerDetailsPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customerDetailsPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerDetailsPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmCustomerDetailsPojo }
     * 
     * 
     */
    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojos() {
        if (customerDetailsPojos == null) {
            customerDetailsPojos = new ArrayList<CrmCustomerDetailsPojo>();
        }
        return this.customerDetailsPojos;
    }

    /**
     * Gets the value of the serverIPAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServerIPAddress() {
        return serverIPAddress;
    }

    /**
     * Sets the value of the serverIPAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServerIPAddress(String value) {
        this.serverIPAddress = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the statusDesc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusDesc() {
        return statusDesc;
    }

    /**
     * Sets the value of the statusDesc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusDesc(String value) {
        this.statusDesc = value;
    }

}
