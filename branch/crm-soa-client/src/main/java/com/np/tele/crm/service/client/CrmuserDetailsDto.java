
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for crmuserDetailsDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmuserDetailsDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="crMappingPojosList" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserMappingPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmUserAssociationPojo" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserAssociationPojo" minOccurs="0"/>
 *         &lt;element name="crmUserAssociationPojos" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserAssociationPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmUserDetailsPojo" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserPojo" minOccurs="0"/>
 *         &lt;element name="crmUserDetailsPojoList" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="crmUserMap">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserPojo" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="crmUserMappingPojo" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserMappingPojo" minOccurs="0"/>
 *         &lt;element name="customerToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newPassword" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="statusDesc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userAssociatedPartners" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userAssociatedServices" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userRolesPojo" type="{http://service.usrmngmnt.crm.tele.np.com/}userRolesPojo" minOccurs="0"/>
 *         &lt;element name="userRolesPojos" type="{http://service.usrmngmnt.crm.tele.np.com/}userRolesPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="userToken" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waiverLimitAmmount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmuserDetailsDto", propOrder = {
    "clientIPAddress",
    "crMappingPojosList",
    "crmUserAssociationPojo",
    "crmUserAssociationPojos",
    "crmUserDetailsPojo",
    "crmUserDetailsPojoList",
    "crmUserMap",
    "crmUserMappingPojo",
    "customerToken",
    "newPassword",
    "password",
    "serverIPAddress",
    "statusCode",
    "statusDesc",
    "userAssociatedPartners",
    "userAssociatedServices",
    "userID",
    "userRolesPojo",
    "userRolesPojos",
    "userToken",
    "waiverLimitAmmount"
})
public class CrmuserDetailsDto {

    protected String clientIPAddress;
    @XmlElement(nillable = true)
    protected List<CrmUserMappingPojo> crMappingPojosList;
    protected CrmUserAssociationPojo crmUserAssociationPojo;
    @XmlElement(nillable = true)
    protected List<CrmUserAssociationPojo> crmUserAssociationPojos;
    protected CrmUserPojo crmUserDetailsPojo;
    @XmlElement(nillable = true)
    protected List<CrmUserPojo> crmUserDetailsPojoList;
    @XmlElement(required = true)
    protected CrmuserDetailsDto.CrmUserMap crmUserMap;
    protected CrmUserMappingPojo crmUserMappingPojo;
    protected String customerToken;
    protected String newPassword;
    protected String password;
    protected String serverIPAddress;
    protected String statusCode;
    protected String statusDesc;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String userID;
    protected UserRolesPojo userRolesPojo;
    @XmlElement(nillable = true)
    protected List<UserRolesPojo> userRolesPojos;
    protected String userToken;
    protected String waiverLimitAmmount;

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
     * Gets the value of the crMappingPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crMappingPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrMappingPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmUserMappingPojo }
     * 
     * 
     */
    public List<CrmUserMappingPojo> getCrMappingPojosList() {
        if (crMappingPojosList == null) {
            crMappingPojosList = new ArrayList<CrmUserMappingPojo>();
        }
        return this.crMappingPojosList;
    }

    /**
     * Gets the value of the crmUserAssociationPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmUserAssociationPojo }
     *     
     */
    public CrmUserAssociationPojo getCrmUserAssociationPojo() {
        return crmUserAssociationPojo;
    }

    /**
     * Sets the value of the crmUserAssociationPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmUserAssociationPojo }
     *     
     */
    public void setCrmUserAssociationPojo(CrmUserAssociationPojo value) {
        this.crmUserAssociationPojo = value;
    }

    /**
     * Gets the value of the crmUserAssociationPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmUserAssociationPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmUserAssociationPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmUserAssociationPojo }
     * 
     * 
     */
    public List<CrmUserAssociationPojo> getCrmUserAssociationPojos() {
        if (crmUserAssociationPojos == null) {
            crmUserAssociationPojos = new ArrayList<CrmUserAssociationPojo>();
        }
        return this.crmUserAssociationPojos;
    }

    /**
     * Gets the value of the crmUserDetailsPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmUserPojo }
     *     
     */
    public CrmUserPojo getCrmUserDetailsPojo() {
        return crmUserDetailsPojo;
    }

    /**
     * Sets the value of the crmUserDetailsPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmUserPojo }
     *     
     */
    public void setCrmUserDetailsPojo(CrmUserPojo value) {
        this.crmUserDetailsPojo = value;
    }

    /**
     * Gets the value of the crmUserDetailsPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crmUserDetailsPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrmUserDetailsPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrmUserPojo }
     * 
     * 
     */
    public List<CrmUserPojo> getCrmUserDetailsPojoList() {
        if (crmUserDetailsPojoList == null) {
            crmUserDetailsPojoList = new ArrayList<CrmUserPojo>();
        }
        return this.crmUserDetailsPojoList;
    }

    /**
     * Gets the value of the crmUserMap property.
     * 
     * @return
     *     possible object is
     *     {@link CrmuserDetailsDto.CrmUserMap }
     *     
     */
    public CrmuserDetailsDto.CrmUserMap getCrmUserMap() {
        return crmUserMap;
    }

    /**
     * Sets the value of the crmUserMap property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmuserDetailsDto.CrmUserMap }
     *     
     */
    public void setCrmUserMap(CrmuserDetailsDto.CrmUserMap value) {
        this.crmUserMap = value;
    }

    /**
     * Gets the value of the crmUserMappingPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CrmUserMappingPojo }
     *     
     */
    public CrmUserMappingPojo getCrmUserMappingPojo() {
        return crmUserMappingPojo;
    }

    /**
     * Sets the value of the crmUserMappingPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CrmUserMappingPojo }
     *     
     */
    public void setCrmUserMappingPojo(CrmUserMappingPojo value) {
        this.crmUserMappingPojo = value;
    }

    /**
     * Gets the value of the customerToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerToken() {
        return customerToken;
    }

    /**
     * Sets the value of the customerToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerToken(String value) {
        this.customerToken = value;
    }

    /**
     * Gets the value of the newPassword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNewPassword() {
        return newPassword;
    }

    /**
     * Sets the value of the newPassword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNewPassword(String value) {
        this.newPassword = value;
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

    /**
     * Gets the value of the userID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the userRolesPojo property.
     * 
     * @return
     *     possible object is
     *     {@link UserRolesPojo }
     *     
     */
    public UserRolesPojo getUserRolesPojo() {
        return userRolesPojo;
    }

    /**
     * Sets the value of the userRolesPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link UserRolesPojo }
     *     
     */
    public void setUserRolesPojo(UserRolesPojo value) {
        this.userRolesPojo = value;
    }

    /**
     * Gets the value of the userRolesPojos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userRolesPojos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserRolesPojos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link UserRolesPojo }
     * 
     * 
     */
    public List<UserRolesPojo> getUserRolesPojos() {
        if (userRolesPojos == null) {
            userRolesPojos = new ArrayList<UserRolesPojo>();
        }
        return this.userRolesPojos;
    }

    /**
     * Gets the value of the userToken property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserToken() {
        return userToken;
    }

    /**
     * Sets the value of the userToken property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserToken(String value) {
        this.userToken = value;
    }

    /**
     * Gets the value of the waiverLimitAmmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaiverLimitAmmount() {
        return waiverLimitAmmount;
    }

    /**
     * Sets the value of the waiverLimitAmmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaiverLimitAmmount(String value) {
        this.waiverLimitAmmount = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserPojo" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class CrmUserMap {

        protected List<CrmuserDetailsDto.CrmUserMap.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link CrmuserDetailsDto.CrmUserMap.Entry }
         * 
         * 
         */
        public List<CrmuserDetailsDto.CrmUserMap.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<CrmuserDetailsDto.CrmUserMap.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
         *         &lt;element name="value" type="{http://service.usrmngmnt.crm.tele.np.com/}crmUserPojo" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Long key;
            protected CrmUserPojo value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link CrmUserPojo }
             *     
             */
            public CrmUserPojo getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link CrmUserPojo }
             *     
             */
            public void setValue(CrmUserPojo value) {
                this.value = value;
            }

        }

    }

}
