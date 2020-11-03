
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerSpeedExceptionListResult;


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
 *         &lt;element name="CustomerSpeedExceptionListResult" type="{http://schemas.datacontract.org/2004/07/}CustomerSpeedExceptionListResult" minOccurs="0"/>
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
    "customerSpeedExceptionListResult"
})
@XmlRootElement(name = "CustomerSpeedExceptionListResponse")
public class CustomerSpeedExceptionListResponse {

    @XmlElementRef(name = "CustomerSpeedExceptionListResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerSpeedExceptionListResult> customerSpeedExceptionListResult;

    /**
     * Gets the value of the customerSpeedExceptionListResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerSpeedExceptionListResult }{@code >}
     *     
     */
    public JAXBElement<CustomerSpeedExceptionListResult> getCustomerSpeedExceptionListResult() {
        return customerSpeedExceptionListResult;
    }

    /**
     * Sets the value of the customerSpeedExceptionListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerSpeedExceptionListResult }{@code >}
     *     
     */
    public void setCustomerSpeedExceptionListResult(JAXBElement<CustomerSpeedExceptionListResult> value) {
        this.customerSpeedExceptionListResult = ((JAXBElement<CustomerSpeedExceptionListResult> ) value);
    }

}
