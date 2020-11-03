
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerNotificationExceptionListResult;


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
 *         &lt;element name="CustomerNotificationExceptionListResult" type="{http://schemas.datacontract.org/2004/07/}CustomerNotificationExceptionListResult" minOccurs="0"/>
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
    "customerNotificationExceptionListResult"
})
@XmlRootElement(name = "CustomerNotificationExceptionListResponse")
public class CustomerNotificationExceptionListResponse {

    @XmlElementRef(name = "CustomerNotificationExceptionListResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerNotificationExceptionListResult> customerNotificationExceptionListResult;

    /**
     * Gets the value of the customerNotificationExceptionListResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerNotificationExceptionListResult }{@code >}
     *     
     */
    public JAXBElement<CustomerNotificationExceptionListResult> getCustomerNotificationExceptionListResult() {
        return customerNotificationExceptionListResult;
    }

    /**
     * Sets the value of the customerNotificationExceptionListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerNotificationExceptionListResult }{@code >}
     *     
     */
    public void setCustomerNotificationExceptionListResult(JAXBElement<CustomerNotificationExceptionListResult> value) {
        this.customerNotificationExceptionListResult = ((JAXBElement<CustomerNotificationExceptionListResult> ) value);
    }

}
