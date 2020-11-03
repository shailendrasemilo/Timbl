
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for qrcConfigDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="qrcConfigDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryBinMappingPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcCategoryBinMappingPojo" minOccurs="0"/>
 *         &lt;element name="categoryBinMappingPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcCategoryBinMappingPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crmQrcAttributedToPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcAttributedToPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcSubSubCategoriesPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubSubCategoriesPojo" minOccurs="0"/>
 *         &lt;element name="crmQrcSubSubCategoriesPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcSubSubCategoriesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="loggedUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="qrcActionTakenPojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcActionTakenPojo" minOccurs="0"/>
 *         &lt;element name="qrcActionTakenPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcActionTakenPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="qrcAttributedToPojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcAttributedToPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="qrcRootCausePojo" type="{http://service.qrc.crm.tele.np.com/}crmQrcRootCausePojo" minOccurs="0"/>
 *         &lt;element name="qrcRootCausePojos" type="{http://service.qrc.crm.tele.np.com/}crmQrcRootCausePojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "qrcConfigDto", propOrder = {
    "categoryBinMappingPojo",
    "categoryBinMappingPojos",
    "clientIPAddress",
    "crmQrcAttributedToPojo",
    "crmQrcSubSubCategoriesPojo",
    "crmQrcSubSubCategoriesPojos",
    "loggedUser",
    "qrcActionTakenPojo",
    "qrcActionTakenPojos",
    "qrcAttributedToPojos",
    "qrcRootCausePojo",
    "qrcRootCausePojos",
    "serverIPAddress",
    "statusCode",
    "statusDesc",
    "userAssociatedPartners",
    "userAssociatedServices"
})
public class QrcConfigDto {

    protected CrmQrcCategoryBinMappingPojo categoryBinMappingPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcCategoryBinMappingPojo> categoryBinMappingPojos;
    protected String clientIPAddress;
    protected CrmQrcAttributedToPojo crmQrcAttributedToPojo;
    protected CrmQrcSubSubCategoriesPojo crmQrcSubSubCategoriesPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcSubSubCategoriesPojo> crmQrcSubSubCategoriesPojos;
    protected String loggedUser;
    protected CrmQrcActionTakenPojo qrcActionTakenPojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcActionTakenPojo> qrcActionTakenPojos;
    @XmlElement(nillable = true)
    protected List<CrmQrcAttributedToPojo> qrcAttributedToPojos;
    protected CrmQrcRootCausePojo qrcRootCausePojo;
    @XmlElement(nillable = true)
    protected List<CrmQrcRootCausePojo> qrcRootCausePojos;
    protected String serverIPAddress;
    protected String statusCode;
    protected String statusDesc;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;

    /**
     * Gets the value of the categoryBinMappingPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcCategoryBinMappingPojo }
     *     
     */
    public CrmQrcCategoryBinMappingPojo getCategoryBinMappingPojo() {
        return categoryBinMappingPojo;
    }

    /**
     * Sets the value of the categoryBinMappingPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcCategoryBinMappingPojo }
     *     
     */
    public void setCategoryBinMappingPojo(CrmQrcCategoryBinMappingPojo value) {
        this.categoryBinMappingPojo = value;
    }

    /**
     * Gets the value of the categoryBinMappingPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryBinMappingPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryBinMappingPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcCategoryBinMappingPojo }
     * 
     * 
     */
    public List<CrmQrcCategoryBinMappingPojo> getCategoryBinMappingPojos() {
        if (categoryBinMappingPojos == null) {
            categoryBinMappingPojos = new ArrayList<CrmQrcCategoryBinMappingPojo>();
        }
        return this.categoryBinMappingPojos;
    }

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
     * Gets the value of the crmQrcAttributedToPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcAttributedToPojo }
     *     
     */
    public CrmQrcAttributedToPojo getCrmQrcAttributedToPojo() {
        return crmQrcAttributedToPojo;
    }

    /**
     * Sets the value of the crmQrcAttributedToPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcAttributedToPojo }
     *     
     */
    public void setCrmQrcAttributedToPojo(CrmQrcAttributedToPojo value) {
        this.crmQrcAttributedToPojo = value;
    }

    /**
     * Gets the value of the crmQrcSubSubCategoriesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcSubSubCategoriesPojo }
     *     
     */
    public CrmQrcSubSubCategoriesPojo getCrmQrcSubSubCategoriesPojo() {
        return crmQrcSubSubCategoriesPojo;
    }

    /**
     * Sets the value of the crmQrcSubSubCategoriesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcSubSubCategoriesPojo }
     *     
     */
    public void setCrmQrcSubSubCategoriesPojo(CrmQrcSubSubCategoriesPojo value) {
        this.crmQrcSubSubCategoriesPojo = value;
    }

    /**
     * Gets the value of the crmQrcSubSubCategoriesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmQrcSubSubCategoriesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmQrcSubSubCategoriesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcSubSubCategoriesPojo }
     * 
     * 
     */
    public List<CrmQrcSubSubCategoriesPojo> getCrmQrcSubSubCategoriesPojos() {
        if (crmQrcSubSubCategoriesPojos == null) {
            crmQrcSubSubCategoriesPojos = new ArrayList<CrmQrcSubSubCategoriesPojo>();
        }
        return this.crmQrcSubSubCategoriesPojos;
    }

    /**
     * Gets the value of the loggedUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoggedUser() {
        return loggedUser;
    }

    /**
     * Sets the value of the loggedUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoggedUser(String value) {
        this.loggedUser = value;
    }

    /**
     * Gets the value of the qrcActionTakenPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcActionTakenPojo }
     *     
     */
    public CrmQrcActionTakenPojo getQrcActionTakenPojo() {
        return qrcActionTakenPojo;
    }

    /**
     * Sets the value of the qrcActionTakenPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcActionTakenPojo }
     *     
     */
    public void setQrcActionTakenPojo(CrmQrcActionTakenPojo value) {
        this.qrcActionTakenPojo = value;
    }

    /**
     * Gets the value of the qrcActionTakenPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcActionTakenPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcActionTakenPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcActionTakenPojo }
     * 
     * 
     */
    public List<CrmQrcActionTakenPojo> getQrcActionTakenPojos() {
        if (qrcActionTakenPojos == null) {
            qrcActionTakenPojos = new ArrayList<CrmQrcActionTakenPojo>();
        }
        return this.qrcActionTakenPojos;
    }

    /**
     * Gets the value of the qrcAttributedToPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcAttributedToPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcAttributedToPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcAttributedToPojo }
     * 
     * 
     */
    public List<CrmQrcAttributedToPojo> getQrcAttributedToPojos() {
        if (qrcAttributedToPojos == null) {
            qrcAttributedToPojos = new ArrayList<CrmQrcAttributedToPojo>();
        }
        return this.qrcAttributedToPojos;
    }

    /**
     * Gets the value of the qrcRootCausePojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmQrcRootCausePojo }
     *     
     */
    public CrmQrcRootCausePojo getQrcRootCausePojo() {
        return qrcRootCausePojo;
    }

    /**
     * Sets the value of the qrcRootCausePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmQrcRootCausePojo }
     *     
     */
    public void setQrcRootCausePojo(CrmQrcRootCausePojo value) {
        this.qrcRootCausePojo = value;
    }

    /**
     * Gets the value of the qrcRootCausePojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the qrcRootCausePojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getQrcRootCausePojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmQrcRootCausePojo }
     * 
     * 
     */
    public List<CrmQrcRootCausePojo> getQrcRootCausePojos() {
        if (qrcRootCausePojos == null) {
            qrcRootCausePojos = new ArrayList<CrmQrcRootCausePojo>();
        }
        return this.qrcRootCausePojos;
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

    /**
     * Gets the value of the userAssociatedPartners property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAssociatedPartners property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAssociatedPartners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserAssociatedPartners() {
        if (userAssociatedPartners == null) {
            userAssociatedPartners = new ArrayList<String>();
        }
        return this.userAssociatedPartners;
    }

    /**
     * Gets the value of the userAssociatedServices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userAssociatedServices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserAssociatedServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserAssociatedServices() {
        if (userAssociatedServices == null) {
            userAssociatedServices = new ArrayList<String>();
        }
        return this.userAssociatedServices;
    }

}
