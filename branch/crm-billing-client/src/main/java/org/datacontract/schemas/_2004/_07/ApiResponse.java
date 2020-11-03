
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ApiResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ApiResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="extTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="returnCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="returnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApiResponse", propOrder = {
    "extTransactionId",
    "returnCode",
    "returnMessage",
    "transactionId"
})
@XmlSeeAlso({
    ChangeBusinessPartnerResult.class,
    DeviceDetailResult.class,
    InventoryItemToLCOResult.class,
    CustomerUsageDetail.class,
    InstantOfferAllocation.class,
    LastPaymentResult.class,
    CancelAddPostpaidPlanResult.class,
    UserSessionResult.class,
    CustomerSpeedExceptionListResult.class,
    ChangeBillCycleResult.class,
    ChangeDeviceDetail.class,
    AddPlanResult.class,
    AddChargeResult.class,
    CustomerDiscountDetails.class,
    CustomerNotificationExceptionListResult.class,
    TerminationRefundResult.class,
    PaymentAdjustment.class,
    P2PReBarringResult.class,
    CustomerBaringExceptionListResult.class,
    Customer.class,
    CustomerUnbaringExceptionListResult.class,
    BillDetails.class,
    CustomerPostPaymentResult.class,
    BillDetail.class,
    PaymentAdjustmentResult.class,
    ChangeAddress.class,
    CancelPlanMigrationResult.class,
    RefferalDetailsResult.class,
    DisableWalledGardenResult.class,
    CustomerPostPayment.class,
    AddPlan.class,
    CreateCustomerResult.class,
    CustomerActivationResult.class,
    OfferAllocation.class,
    CheckPlanFeasibilityResult.class
})
public class ApiResponse {

    @XmlElementRef(name = "extTransactionId", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> extTransactionId;
    protected Integer returnCode;
    @XmlElementRef(name = "returnMessage", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<String> returnMessage;
    protected Long transactionId;

    /**
     * Gets the value of the extTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getExtTransactionId() {
        return extTransactionId;
    }

    /**
     * Sets the value of the extTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setExtTransactionId(JAXBElement<String> value) {
        this.extTransactionId = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the returnCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getReturnCode() {
        return returnCode;
    }

    /**
     * Sets the value of the returnCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setReturnCode(Integer value) {
        this.returnCode = value;
    }

    /**
     * Gets the value of the returnMessage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReturnMessage() {
        return returnMessage;
    }

    /**
     * Sets the value of the returnMessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReturnMessage(JAXBElement<String> value) {
        this.returnMessage = ((JAXBElement<String> ) value);
    }

    /**
     * Gets the value of the transactionId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getTransactionId() {
        return transactionId;
    }

    /**
     * Sets the value of the transactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setTransactionId(Long value) {
        this.transactionId = value;
    }

}
