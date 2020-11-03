
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for crmCmsPaymentPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="crmCmsPaymentPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bandId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clearanceFileId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cmsId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="createdBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="debitCreditFlag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="depositDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="depositFileId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="depositRemarks" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositSlipNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="draweeBank" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="draweeBranch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="drawerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="entryAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="entryId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="faliureReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ie1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ie2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="instrumentAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="instrumentDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="instrumentNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="internalDepNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedBy" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastModifiedTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="pickupDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="postingDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="returnReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="totalAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalInstruments" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="typeOfEntry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="valueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "crmCmsPaymentPojo", propOrder = {
    "bandId",
    "clearanceFileId",
    "cmsId",
    "createdBy",
    "createdTime",
    "debitCreditFlag",
    "depositAmount",
    "depositDate",
    "depositFileId",
    "depositRemarks",
    "depositSlipNo",
    "draweeBank",
    "draweeBranch",
    "drawerName",
    "entryAmount",
    "entryId",
    "faliureReason",
    "ie1",
    "ie2",
    "instrumentAmount",
    "instrumentDate",
    "instrumentNo",
    "internalDepNo",
    "lastModifiedBy",
    "lastModifiedTime",
    "pickupDate",
    "postingDate",
    "returnReason",
    "status",
    "totalAmount",
    "totalInstruments",
    "typeOfEntry",
    "valueDate"
})
public class CrmCmsPaymentPojo {

    protected String bandId;
    protected long clearanceFileId;
    protected long cmsId;
    protected String createdBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String debitCreditFlag;
    protected Double depositAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar depositDate;
    protected long depositFileId;
    protected String depositRemarks;
    protected String depositSlipNo;
    protected String draweeBank;
    protected String draweeBranch;
    protected String drawerName;
    protected Double entryAmount;
    protected String entryId;
    protected String faliureReason;
    protected String ie1;
    protected String ie2;
    protected Double instrumentAmount;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar instrumentDate;
    protected String instrumentNo;
    protected String internalDepNo;
    protected String lastModifiedBy;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastModifiedTime;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pickupDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar postingDate;
    protected String returnReason;
    protected String status;
    protected Double totalAmount;
    protected Integer totalInstruments;
    protected String typeOfEntry;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar valueDate;

    /**
     * Gets the value of the bandId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBandId() {
        return bandId;
    }

    /**
     * Sets the value of the bandId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBandId(String value) {
        this.bandId = value;
    }

    /**
     * Gets the value of the clearanceFileId property.
     * 
     */
    public long getClearanceFileId() {
        return clearanceFileId;
    }

    /**
     * Sets the value of the clearanceFileId property.
     * 
     */
    public void setClearanceFileId(long value) {
        this.clearanceFileId = value;
    }

    /**
     * Gets the value of the cmsId property.
     * 
     */
    public long getCmsId() {
        return cmsId;
    }

    /**
     * Sets the value of the cmsId property.
     * 
     */
    public void setCmsId(long value) {
        this.cmsId = value;
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
     * Gets the value of the debitCreditFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDebitCreditFlag() {
        return debitCreditFlag;
    }

    /**
     * Sets the value of the debitCreditFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDebitCreditFlag(String value) {
        this.debitCreditFlag = value;
    }

    /**
     * Gets the value of the depositAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDepositAmount() {
        return depositAmount;
    }

    /**
     * Sets the value of the depositAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDepositAmount(Double value) {
        this.depositAmount = value;
    }

    /**
     * Gets the value of the depositDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDepositDate() {
        return depositDate;
    }

    /**
     * Sets the value of the depositDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDepositDate(XMLGregorianCalendar value) {
        this.depositDate = value;
    }

    /**
     * Gets the value of the depositFileId property.
     * 
     */
    public long getDepositFileId() {
        return depositFileId;
    }

    /**
     * Sets the value of the depositFileId property.
     * 
     */
    public void setDepositFileId(long value) {
        this.depositFileId = value;
    }

    /**
     * Gets the value of the depositRemarks property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositRemarks() {
        return depositRemarks;
    }

    /**
     * Sets the value of the depositRemarks property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositRemarks(String value) {
        this.depositRemarks = value;
    }

    /**
     * Gets the value of the depositSlipNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositSlipNo() {
        return depositSlipNo;
    }

    /**
     * Sets the value of the depositSlipNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositSlipNo(String value) {
        this.depositSlipNo = value;
    }

    /**
     * Gets the value of the draweeBank property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDraweeBank() {
        return draweeBank;
    }

    /**
     * Sets the value of the draweeBank property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDraweeBank(String value) {
        this.draweeBank = value;
    }

    /**
     * Gets the value of the draweeBranch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDraweeBranch() {
        return draweeBranch;
    }

    /**
     * Sets the value of the draweeBranch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDraweeBranch(String value) {
        this.draweeBranch = value;
    }

    /**
     * Gets the value of the drawerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDrawerName() {
        return drawerName;
    }

    /**
     * Sets the value of the drawerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDrawerName(String value) {
        this.drawerName = value;
    }

    /**
     * Gets the value of the entryAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEntryAmount() {
        return entryAmount;
    }

    /**
     * Sets the value of the entryAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEntryAmount(Double value) {
        this.entryAmount = value;
    }

    /**
     * Gets the value of the entryId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntryId() {
        return entryId;
    }

    /**
     * Sets the value of the entryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntryId(String value) {
        this.entryId = value;
    }

    /**
     * Gets the value of the faliureReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFaliureReason() {
        return faliureReason;
    }

    /**
     * Sets the value of the faliureReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFaliureReason(String value) {
        this.faliureReason = value;
    }

    /**
     * Gets the value of the ie1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIe1() {
        return ie1;
    }

    /**
     * Sets the value of the ie1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIe1(String value) {
        this.ie1 = value;
    }

    /**
     * Gets the value of the ie2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIe2() {
        return ie2;
    }

    /**
     * Sets the value of the ie2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIe2(String value) {
        this.ie2 = value;
    }

    /**
     * Gets the value of the instrumentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInstrumentAmount() {
        return instrumentAmount;
    }

    /**
     * Sets the value of the instrumentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInstrumentAmount(Double value) {
        this.instrumentAmount = value;
    }

    /**
     * Gets the value of the instrumentDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInstrumentDate() {
        return instrumentDate;
    }

    /**
     * Sets the value of the instrumentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInstrumentDate(XMLGregorianCalendar value) {
        this.instrumentDate = value;
    }

    /**
     * Gets the value of the instrumentNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstrumentNo() {
        return instrumentNo;
    }

    /**
     * Sets the value of the instrumentNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstrumentNo(String value) {
        this.instrumentNo = value;
    }

    /**
     * Gets the value of the internalDepNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInternalDepNo() {
        return internalDepNo;
    }

    /**
     * Sets the value of the internalDepNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInternalDepNo(String value) {
        this.internalDepNo = value;
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
     * Gets the value of the pickupDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPickupDate() {
        return pickupDate;
    }

    /**
     * Sets the value of the pickupDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPickupDate(XMLGregorianCalendar value) {
        this.pickupDate = value;
    }

    /**
     * Gets the value of the postingDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPostingDate() {
        return postingDate;
    }

    /**
     * Sets the value of the postingDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPostingDate(XMLGregorianCalendar value) {
        this.postingDate = value;
    }

    /**
     * Gets the value of the returnReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReturnReason() {
        return returnReason;
    }

    /**
     * Sets the value of the returnReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReturnReason(String value) {
        this.returnReason = value;
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
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalAmount(Double value) {
        this.totalAmount = value;
    }

    /**
     * Gets the value of the totalInstruments property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTotalInstruments() {
        return totalInstruments;
    }

    /**
     * Sets the value of the totalInstruments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTotalInstruments(Integer value) {
        this.totalInstruments = value;
    }

    /**
     * Gets the value of the typeOfEntry property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfEntry() {
        return typeOfEntry;
    }

    /**
     * Sets the value of the typeOfEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfEntry(String value) {
        this.typeOfEntry = value;
    }

    /**
     * Gets the value of the valueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getValueDate() {
        return valueDate;
    }

    /**
     * Sets the value of the valueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setValueDate(XMLGregorianCalendar value) {
        this.valueDate = value;
    }

}
