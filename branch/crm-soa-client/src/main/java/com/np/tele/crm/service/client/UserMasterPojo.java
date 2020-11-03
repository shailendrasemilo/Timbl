
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for userMasterPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="userMasterPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="alias" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowed_ip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="allowed_ip_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="area" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="area_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="end_time_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="expire_attempts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lock_attempts" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="lock_duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="login_end_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="login_start_time" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="start_time_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="village" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="waiver_limit_id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "userMasterPojo", propOrder = {
    "alias",
    "allowedIp",
    "allowedIpId",
    "area",
    "areaId",
    "category",
    "city",
    "country",
    "endTimeId",
    "expireAttempts",
    "lockAttempts",
    "lockDuration",
    "loginEndTime",
    "loginStartTime",
    "startTimeId",
    "state",
    "subCategory",
    "village",
    "waiverLimitId"
})
public class UserMasterPojo {

    protected String alias;
    @XmlElement(name = "allowed_ip")
    protected String allowedIp;
    @XmlElement(name = "allowed_ip_id")
    protected long allowedIpId;
    protected String area;
    @XmlElement(name = "area_id")
    protected long areaId;
    protected String category;
    protected String city;
    protected String country;
    @XmlElement(name = "end_time_id")
    protected long endTimeId;
    @XmlElement(name = "expire_attempts")
    protected int expireAttempts;
    @XmlElement(name = "lock_attempts")
    protected int lockAttempts;
    @XmlElement(name = "lock_duration")
    protected int lockDuration;
    @XmlElement(name = "login_end_time")
    protected String loginEndTime;
    @XmlElement(name = "login_start_time")
    protected String loginStartTime;
    @XmlElement(name = "start_time_id")
    protected long startTimeId;
    protected String state;
    protected String subCategory;
    protected String village;
    @XmlElement(name = "waiver_limit_id")
    protected long waiverLimitId;

    /**
     * Gets the value of the alias property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Sets the value of the alias property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlias(String value) {
        this.alias = value;
    }

    /**
     * Gets the value of the allowedIp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllowedIp() {
        return allowedIp;
    }

    /**
     * Sets the value of the allowedIp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllowedIp(String value) {
        this.allowedIp = value;
    }

    /**
     * Gets the value of the allowedIpId property.
     * 
     */
    public long getAllowedIpId() {
        return allowedIpId;
    }

    /**
     * Sets the value of the allowedIpId property.
     * 
     */
    public void setAllowedIpId(long value) {
        this.allowedIpId = value;
    }

    /**
     * Gets the value of the area property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getArea() {
        return area;
    }

    /**
     * Sets the value of the area property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setArea(String value) {
        this.area = value;
    }

    /**
     * Gets the value of the areaId property.
     * 
     */
    public long getAreaId() {
        return areaId;
    }

    /**
     * Sets the value of the areaId property.
     * 
     */
    public void setAreaId(long value) {
        this.areaId = value;
    }

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
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the endTimeId property.
     * 
     */
    public long getEndTimeId() {
        return endTimeId;
    }

    /**
     * Sets the value of the endTimeId property.
     * 
     */
    public void setEndTimeId(long value) {
        this.endTimeId = value;
    }

    /**
     * Gets the value of the expireAttempts property.
     * 
     */
    public int getExpireAttempts() {
        return expireAttempts;
    }

    /**
     * Sets the value of the expireAttempts property.
     * 
     */
    public void setExpireAttempts(int value) {
        this.expireAttempts = value;
    }

    /**
     * Gets the value of the lockAttempts property.
     * 
     */
    public int getLockAttempts() {
        return lockAttempts;
    }

    /**
     * Sets the value of the lockAttempts property.
     * 
     */
    public void setLockAttempts(int value) {
        this.lockAttempts = value;
    }

    /**
     * Gets the value of the lockDuration property.
     * 
     */
    public int getLockDuration() {
        return lockDuration;
    }

    /**
     * Sets the value of the lockDuration property.
     * 
     */
    public void setLockDuration(int value) {
        this.lockDuration = value;
    }

    /**
     * Gets the value of the loginEndTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginEndTime() {
        return loginEndTime;
    }

    /**
     * Sets the value of the loginEndTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginEndTime(String value) {
        this.loginEndTime = value;
    }

    /**
     * Gets the value of the loginStartTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginStartTime() {
        return loginStartTime;
    }

    /**
     * Sets the value of the loginStartTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginStartTime(String value) {
        this.loginStartTime = value;
    }

    /**
     * Gets the value of the startTimeId property.
     * 
     */
    public long getStartTimeId() {
        return startTimeId;
    }

    /**
     * Sets the value of the startTimeId property.
     * 
     */
    public void setStartTimeId(long value) {
        this.startTimeId = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
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
     * Gets the value of the village property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVillage() {
        return village;
    }

    /**
     * Sets the value of the village property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVillage(String value) {
        this.village = value;
    }

    /**
     * Gets the value of the waiverLimitId property.
     * 
     */
    public long getWaiverLimitId() {
        return waiverLimitId;
    }

    /**
     * Sets the value of the waiverLimitId property.
     * 
     */
    public void setWaiverLimitId(long value) {
        this.waiverLimitId = value;
    }

}
