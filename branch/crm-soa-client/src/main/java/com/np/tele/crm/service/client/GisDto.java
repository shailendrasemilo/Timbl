
package com.np.tele.crm.service.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for gisDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="gisDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="areaPojo" type="{http://service.gis.crm.tele.np.com/}areaPojo" minOccurs="0"/>
 *         &lt;element name="areaPojosList" type="{http://service.gis.crm.tele.np.com/}areaPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="cityPojo" type="{http://service.gis.crm.tele.np.com/}cityPojo" minOccurs="0"/>
 *         &lt;element name="cityPojosList" type="{http://service.gis.crm.tele.np.com/}cityPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="clientIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryPojo" type="{http://service.gis.crm.tele.np.com/}countryPojo" minOccurs="0"/>
 *         &lt;element name="countryPojosList" type="{http://service.gis.crm.tele.np.com/}countryPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="localityPojo" type="{http://service.gis.crm.tele.np.com/}localityPojo" minOccurs="0"/>
 *         &lt;element name="localityPojosList" type="{http://service.gis.crm.tele.np.com/}localityPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="operationType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serverIPAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="soNetworkPartnerPojo" type="{http://service.gis.crm.tele.np.com/}societyNetworkPartnerPojo" minOccurs="0"/>
 *         &lt;element name="soNetworkPartnerPojoList" type="{http://service.gis.crm.tele.np.com/}societyNetworkPartnerPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="societyPojo" type="{http://service.gis.crm.tele.np.com/}societyPojo" minOccurs="0"/>
 *         &lt;element name="societyPojosList" type="{http://service.gis.crm.tele.np.com/}societyPojo" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="statePojo" type="{http://service.gis.crm.tele.np.com/}statePojo" minOccurs="0"/>
 *         &lt;element name="statePojosList" type="{http://service.gis.crm.tele.np.com/}statePojo" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "gisDto", propOrder = {
    "areaPojo",
    "areaPojosList",
    "cityPojo",
    "cityPojosList",
    "clientIPAddress",
    "countryPojo",
    "countryPojosList",
    "localityPojo",
    "localityPojosList",
    "operationType",
    "partnerId",
    "productName",
    "serverIPAddress",
    "soNetworkPartnerPojo",
    "soNetworkPartnerPojoList",
    "societyPojo",
    "societyPojosList",
    "statePojo",
    "statePojosList",
    "statusCode",
    "statusDesc",
    "userAssociatedPartners",
    "userAssociatedServices",
    "stateName",
    "cityName",
    "areaName",
    "societyName"
    
})
public class GisDto {

    protected AreaPojo areaPojo;
    @XmlElement(nillable = true)
    protected List<AreaPojo> areaPojosList;
    protected CityPojo cityPojo;
    @XmlElement(nillable = true)
    protected List<CityPojo> cityPojosList;
    protected String clientIPAddress;
    protected CountryPojo countryPojo;
    @XmlElement(nillable = true)
    protected List<CountryPojo> countryPojosList;
    protected LocalityPojo localityPojo;
    @XmlElement(nillable = true)
    protected List<LocalityPojo> localityPojosList;
    protected String operationType;
    protected long partnerId;
    protected String productName;
    protected String serverIPAddress;
    protected SocietyNetworkPartnerPojo soNetworkPartnerPojo;
    @XmlElement(nillable = true)
    protected List<SocietyNetworkPartnerPojo> soNetworkPartnerPojoList;
    protected SocietyPojo societyPojo;
    @XmlElement(nillable = true)
    protected List<SocietyPojo> societyPojosList;
    protected StatePojo statePojo;
    @XmlElement(nillable = true)
    protected List<StatePojo> statePojosList;
    protected String statusCode;
    protected String statusDesc;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedPartners;
    @XmlElement(nillable = true)
    protected List<String> userAssociatedServices;
    protected String                          stateName;
    protected String                          cityName;
    protected String                          areaName;
    protected String                          societyName;


    /**
     * Gets the value of the areaPojo property.
     * 
     * @return
     *     possible object is
     *     {@link AreaPojo }
     *     
     */
    public AreaPojo getAreaPojo() {
        return areaPojo;
    }

    /**
     * Sets the value of the areaPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaPojo }
     *     
     */
    public void setAreaPojo(AreaPojo value) {
        this.areaPojo = value;
    }

    /**
     * Gets the value of the areaPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the areaPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAreaPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AreaPojo }
     * 
     * 
     */
    public List<AreaPojo> getAreaPojosList() {
        if (areaPojosList == null) {
            areaPojosList = new ArrayList<AreaPojo>();
        }
        return this.areaPojosList;
    }

    /**
     * Gets the value of the cityPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CityPojo }
     *     
     */
    public CityPojo getCityPojo() {
        return cityPojo;
    }

    /**
     * Sets the value of the cityPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CityPojo }
     *     
     */
    public void setCityPojo(CityPojo value) {
        this.cityPojo = value;
    }

    /**
     * Gets the value of the cityPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cityPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCityPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CityPojo }
     * 
     * 
     */
    public List<CityPojo> getCityPojosList() {
        if (cityPojosList == null) {
            cityPojosList = new ArrayList<CityPojo>();
        }
        return this.cityPojosList;
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
     * Gets the value of the countryPojo property.
     * 
     * @return
     *     possible object is
     *     {@link CountryPojo }
     *     
     */
    public CountryPojo getCountryPojo() {
        return countryPojo;
    }

    /**
     * Sets the value of the countryPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link CountryPojo }
     *     
     */
    public void setCountryPojo(CountryPojo value) {
        this.countryPojo = value;
    }

    /**
     * Gets the value of the countryPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the countryPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCountryPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CountryPojo }
     * 
     * 
     */
    public List<CountryPojo> getCountryPojosList() {
        if (countryPojosList == null) {
            countryPojosList = new ArrayList<CountryPojo>();
        }
        return this.countryPojosList;
    }

    /**
     * Gets the value of the localityPojo property.
     * 
     * @return
     *     possible object is
     *     {@link LocalityPojo }
     *     
     */
    public LocalityPojo getLocalityPojo() {
        return localityPojo;
    }

    /**
     * Sets the value of the localityPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalityPojo }
     *     
     */
    public void setLocalityPojo(LocalityPojo value) {
        this.localityPojo = value;
    }

    /**
     * Gets the value of the localityPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the localityPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLocalityPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LocalityPojo }
     * 
     * 
     */
    public List<LocalityPojo> getLocalityPojosList() {
        if (localityPojosList == null) {
            localityPojosList = new ArrayList<LocalityPojo>();
        }
        return this.localityPojosList;
    }

    /**
     * Gets the value of the operationType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * Sets the value of the operationType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationType(String value) {
        this.operationType = value;
    }

    /**
     * Gets the value of the partnerId property.
     * 
     */
    public long getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     */
    public void setPartnerId(long value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
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
     * Gets the value of the soNetworkPartnerPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SocietyNetworkPartnerPojo }
     *     
     */
    public SocietyNetworkPartnerPojo getSoNetworkPartnerPojo() {
        return soNetworkPartnerPojo;
    }

    /**
     * Sets the value of the soNetworkPartnerPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SocietyNetworkPartnerPojo }
     *     
     */
    public void setSoNetworkPartnerPojo(SocietyNetworkPartnerPojo value) {
        this.soNetworkPartnerPojo = value;
    }

    /**
     * Gets the value of the soNetworkPartnerPojoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the soNetworkPartnerPojoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSoNetworkPartnerPojoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SocietyNetworkPartnerPojo }
     * 
     * 
     */
    public List<SocietyNetworkPartnerPojo> getSoNetworkPartnerPojoList() {
        if (soNetworkPartnerPojoList == null) {
            soNetworkPartnerPojoList = new ArrayList<SocietyNetworkPartnerPojo>();
        }
        return this.soNetworkPartnerPojoList;
    }

    /**
     * Gets the value of the societyPojo property.
     * 
     * @return
     *     possible object is
     *     {@link SocietyPojo }
     *     
     */
    public SocietyPojo getSocietyPojo() {
        return societyPojo;
    }

    /**
     * Sets the value of the societyPojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link SocietyPojo }
     *     
     */
    public void setSocietyPojo(SocietyPojo value) {
        this.societyPojo = value;
    }

    /**
     * Gets the value of the societyPojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the societyPojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSocietyPojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SocietyPojo }
     * 
     * 
     */
    public List<SocietyPojo> getSocietyPojosList() {
        if (societyPojosList == null) {
            societyPojosList = new ArrayList<SocietyPojo>();
        }
        return this.societyPojosList;
    }

    /**
     * Gets the value of the statePojo property.
     * 
     * @return
     *     possible object is
     *     {@link StatePojo }
     *     
     */
    public StatePojo getStatePojo() {
        return statePojo;
    }

    /**
     * Sets the value of the statePojo property.
     * 
     * @param value
     *     allowed object is
     *     {@link StatePojo }
     *     
     */
    public void setStatePojo(StatePojo value) {
        this.statePojo = value;
    }

    /**
     * Gets the value of the statePojosList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the statePojosList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStatePojosList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StatePojo }
     * 
     * 
     */
    public List<StatePojo> getStatePojosList() {
        if (statePojosList == null) {
            statePojosList = new ArrayList<StatePojo>();
        }
        return this.statePojosList;
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

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String stateName )
    {
        this.stateName = stateName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String cityName )
    {
        this.cityName = cityName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String areaName )
    {
        this.areaName = areaName;
    }

    public String getSocietyName()
    {
        return societyName;
    }

    public void setSocietyName( String societyName )
    {
        this.societyName = societyName;
    }
    
}
