
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.PaymentAdjustmentResult;


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
 *         &lt;element name="PaymentAdjustmentResult" type="{http://schemas.datacontract.org/2004/07/}PaymentAdjustmentResult" minOccurs="0"/>
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
    "paymentAdjustmentResult"
})
@XmlRootElement(name = "PaymentAdjustmentResponse")
public class PaymentAdjustmentResponse {

    @XmlElementRef(name = "PaymentAdjustmentResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<PaymentAdjustmentResult> paymentAdjustmentResult;

    /**
     * Gets the value of the paymentAdjustmentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PaymentAdjustmentResult }{@code >}
     *     
     */
    public JAXBElement<PaymentAdjustmentResult> getPaymentAdjustmentResult() {
        return paymentAdjustmentResult;
    }

    /**
     * Sets the value of the paymentAdjustmentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PaymentAdjustmentResult }{@code >}
     *     
     */
    public void setPaymentAdjustmentResult(JAXBElement<PaymentAdjustmentResult> value) {
        this.paymentAdjustmentResult = ((JAXBElement<PaymentAdjustmentResult> ) value);
    }

}
