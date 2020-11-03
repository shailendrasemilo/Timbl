
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmShiftingWorkflowPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmShiftingWorkflowPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addOnPlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressLine3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="areaId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="basePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cityId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="closerReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpeAvailable" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="currentBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentCPeMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentProcessOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentSlaveMacId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerRefusal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="houseNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="landmark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="locality" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="macBind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nassportChange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="npId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="option82" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="physicalInstallation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pincode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="planChange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousBin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousNetwork" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousNpId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="previousStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="proprtyType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceChange" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shiftingAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="shiftingId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="shiftingType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="society" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="societyId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="spId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="stateId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmShiftingWorkflowPojo", propOrder = {
    "addOnPlanCode",
    "addressLine1",
    "addressLine2",
    "addressLine3",
    "areaId",
    "basePlanCode",
    "cityId",
    "closerReason",
    "cpeAvailable",
    "createdBy",
    "createdTime",
    "currentBin",
    "currentCPeMacId",
    "currentProcessOwner",
    "currentSlaveMacId",
    "customerId",
    "customerRefusal",
    "houseNumber",
    "landmark",
    "lastModifiedBy",
    "lastModifiedTime",
    "locality",
    "macBind",
    "nassportChange",
    "npId",
    "option82",
    "physicalInstallation",
    "pincode",
    "planChange",
    "previousBin",
    "previousNetwork",
    "previousNpId",
    "previousStage",
    "product",
    "proprtyType",
    "serviceChange",
    "shiftingAddress",
    "shiftingId",
    "shiftingType",
    "society",
    "societyId",
    "spId",
    "stateId",
    "status",
    "workflowId",
    "workflowStage"
})
public class CrmShiftingWorkflowPojo {

    protected String addOnPlanCode;
    protected String addressLine1;
    protected String addressLine2;
    protected String addressLine3;
    protected Long areaId;
    protected String basePlanCode;
    protected Long cityId;
    protected String closerReason;
    protected String cpeAvailable;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String currentBin;
    protected String currentCPeMacId;
    protected String currentProcessOwner;
    protected String currentSlaveMacId;
    protected String customerId;
    protected String customerRefusal;
    protected String houseNumber;
    protected String landmark;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    protected String locality;
    protected String macBind;
    protected String nassportChange;
    protected long npId;
    protected String option82;
    protected String physicalInstallation;
    protected Integer pincode;
    protected String planChange;
    protected String previousBin;
    protected String previousNetwork;
    protected Long previousNpId;
    protected String previousStage;
    protected String product;
    protected String proprtyType;
    protected String serviceChange;
    protected String shiftingAddress;
    protected long shiftingId;
    protected String shiftingType;
    protected String society;
    protected Long societyId;
    protected long spId;
    protected Long stateId;
    protected String status;
    protected String workflowId;
    protected String workflowStage;

    /**
     * Gets the value of the addOnPlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddOnPlanCode() {
        return addOnPlanCode;
    }

    /**
     * Sets the value of the addOnPlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddOnPlanCode(String value) {
        this.addOnPlanCode = value;
    }

    /**
     * Gets the value of the addressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the value of the addressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine1(String value) {
        this.addressLine1 = value;
    }

    /**
     * Gets the value of the addressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the value of the addressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine2(String value) {
        this.addressLine2 = value;
    }

    /**
     * Gets the value of the addressLine3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressLine3() {
        return addressLine3;
    }

    /**
     * Sets the value of the addressLine3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressLine3(String value) {
        this.addressLine3 = value;
    }

    /**
     * Gets the value of the areaId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAreaId() {
        return areaId;
    }

    /**
     * Sets the value of the areaId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAreaId(Long value) {
        this.areaId = value;
    }

    /**
     * Gets the value of the basePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBasePlanCode() {
        return basePlanCode;
    }

    /**
     * Sets the value of the basePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBasePlanCode(String value) {
        this.basePlanCode = value;
    }

    /**
     * Gets the value of the cityId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * Sets the value of the cityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCityId(Long value) {
        this.cityId = value;
    }

    /**
     * Gets the value of the closerReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCloserReason() {
        return closerReason;
    }

    /**
     * Sets the value of the closerReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCloserReason(String value) {
        this.closerReason = value;
    }

    /**
     * Gets the value of the cpeAvailable property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpeAvailable() {
        return cpeAvailable;
    }

    /**
     * Sets the value of the cpeAvailable property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpeAvailable(String value) {
        this.cpeAvailable = value;
    }

    /**
     * Gets the value of the createdBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * Sets the value of the createdBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreatedBy(String value) {
        this.createdBy = value;
    }

    /**
     * Gets the value of the createdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTime() {
        return createdTime;
    }

    /**
     * Sets the value of the createdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTime(XMLGregorianCalendar value) {
        this.createdTime = value;
    }

    /**
     * Gets the value of the currentBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentBin() {
        return currentBin;
    }

    /**
     * Sets the value of the currentBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentBin(String value) {
        this.currentBin = value;
    }

    /**
     * Gets the value of the currentCPeMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentCPeMacId() {
        return currentCPeMacId;
    }

    /**
     * Sets the value of the currentCPeMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentCPeMacId(String value) {
        this.currentCPeMacId = value;
    }

    /**
     * Gets the value of the currentProcessOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentProcessOwner() {
        return currentProcessOwner;
    }

    /**
     * Sets the value of the currentProcessOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentProcessOwner(String value) {
        this.currentProcessOwner = value;
    }

    /**
     * Gets the value of the currentSlaveMacId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentSlaveMacId() {
        return currentSlaveMacId;
    }

    /**
     * Sets the value of the currentSlaveMacId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentSlaveMacId(String value) {
        this.currentSlaveMacId = value;
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
     * Gets the value of the customerRefusal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerRefusal() {
        return customerRefusal;
    }

    /**
     * Sets the value of the customerRefusal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerRefusal(String value) {
        this.customerRefusal = value;
    }

    /**
     * Gets the value of the houseNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNumber() {
        return houseNumber;
    }

    /**
     * Sets the value of the houseNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNumber(String value) {
        this.houseNumber = value;
    }

    /**
     * Gets the value of the landmark property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLandmark() {
        return landmark;
    }

    /**
     * Sets the value of the landmark property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLandmark(String value) {
        this.landmark = value;
    }

    /**
     * Gets the value of the lastModifiedBy property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    /**
     * Sets the value of the lastModifiedBy property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastModifiedBy(String value) {
        this.lastModifiedBy = value;
    }

    /**
     * Gets the value of the lastModifiedTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastModifiedTime() {
        return lastModifiedTime;
    }

    /**
     * Sets the value of the lastModifiedTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastModifiedTime(XMLGregorianCalendar value) {
        this.lastModifiedTime = value;
    }

    /**
     * Gets the value of the locality property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocality() {
        return locality;
    }

    /**
     * Sets the value of the locality property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocality(String value) {
        this.locality = value;
    }

    /**
     * Gets the value of the macBind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacBind() {
        return macBind;
    }

    /**
     * Sets the value of the macBind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacBind(String value) {
        this.macBind = value;
    }

    /**
     * Gets the value of the nassportChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNassportChange() {
        return nassportChange;
    }

    /**
     * Sets the value of the nassportChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNassportChange(String value) {
        this.nassportChange = value;
    }

    /**
     * Gets the value of the npId property.
     * 
     */
    public long getNpId() {
        return npId;
    }

    /**
     * Sets the value of the npId property.
     * 
     */
    public void setNpId(long value) {
        this.npId = value;
    }

    /**
     * Gets the value of the option82 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOption82() {
        return option82;
    }

    /**
     * Sets the value of the option82 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOption82(String value) {
        this.option82 = value;
    }

    /**
     * Gets the value of the physicalInstallation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhysicalInstallation() {
        return physicalInstallation;
    }

    /**
     * Sets the value of the physicalInstallation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhysicalInstallation(String value) {
        this.physicalInstallation = value;
    }

    /**
     * Gets the value of the pincode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPincode() {
        return pincode;
    }

    /**
     * Sets the value of the pincode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPincode(Integer value) {
        this.pincode = value;
    }

    /**
     * Gets the value of the planChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanChange() {
        return planChange;
    }

    /**
     * Sets the value of the planChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanChange(String value) {
        this.planChange = value;
    }

    /**
     * Gets the value of the previousBin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousBin() {
        return previousBin;
    }

    /**
     * Sets the value of the previousBin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousBin(String value) {
        this.previousBin = value;
    }

    /**
     * Gets the value of the previousNetwork property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousNetwork() {
        return previousNetwork;
    }

    /**
     * Sets the value of the previousNetwork property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousNetwork(String value) {
        this.previousNetwork = value;
    }

    /**
     * Gets the value of the previousNpId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPreviousNpId() {
        return previousNpId;
    }

    /**
     * Sets the value of the previousNpId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPreviousNpId(Long value) {
        this.previousNpId = value;
    }

    /**
     * Gets the value of the previousStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousStage() {
        return previousStage;
    }

    /**
     * Sets the value of the previousStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousStage(String value) {
        this.previousStage = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProduct(String value) {
        this.product = value;
    }

    /**
     * Gets the value of the proprtyType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProprtyType() {
        return proprtyType;
    }

    /**
     * Sets the value of the proprtyType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProprtyType(String value) {
        this.proprtyType = value;
    }

    /**
     * Gets the value of the serviceChange property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceChange() {
        return serviceChange;
    }

    /**
     * Sets the value of the serviceChange property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceChange(String value) {
        this.serviceChange = value;
    }

    /**
     * Gets the value of the shiftingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShiftingAddress() {
        return shiftingAddress;
    }

    /**
     * Sets the value of the shiftingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShiftingAddress(String value) {
        this.shiftingAddress = value;
    }

    /**
     * Gets the value of the shiftingId property.
     * 
     */
    public long getShiftingId() {
        return shiftingId;
    }

    /**
     * Sets the value of the shiftingId property.
     * 
     */
    public void setShiftingId(long value) {
        this.shiftingId = value;
    }

    /**
     * Gets the value of the shiftingType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getShiftingType() {
        return shiftingType;
    }

    /**
     * Sets the value of the shiftingType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setShiftingType(String value) {
        this.shiftingType = value;
    }

    /**
     * Gets the value of the society property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSociety() {
        return society;
    }

    /**
     * Sets the value of the society property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSociety(String value) {
        this.society = value;
    }

    /**
     * Gets the value of the societyId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSocietyId() {
        return societyId;
    }

    /**
     * Sets the value of the societyId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSocietyId(Long value) {
        this.societyId = value;
    }

    /**
     * Gets the value of the spId property.
     * 
     */
    public long getSpId() {
        return spId;
    }

    /**
     * Sets the value of the spId property.
     * 
     */
    public void setSpId(long value) {
        this.spId = value;
    }

    /**
     * Gets the value of the stateId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStateId() {
        return stateId;
    }

    /**
     * Sets the value of the stateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStateId(Long value) {
        this.stateId = value;
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
     * Gets the value of the workflowId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowId() {
        return workflowId;
    }

    /**
     * Sets the value of the workflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowId(String value) {
        this.workflowId = value;
    }

    /**
     * Gets the value of the workflowStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowStage() {
        return workflowStage;
    }

    /**
     * Sets the value of the workflowStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowStage(String value) {
        this.workflowStage = value;
    }

}
