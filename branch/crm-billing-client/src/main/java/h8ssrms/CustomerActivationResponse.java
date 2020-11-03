
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerActivationResult;


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
 *         &lt;element name="CustomerActivationResult" type="{http://schemas.datacontract.org/2004/07/}CustomerActivationResult" minOccurs="0"/>
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
    "customerActivationResult"
})
@XmlRootElement(name = "CustomerActivationResponse")
public class CustomerActivationResponse {

    @XmlElementRef(name = "CustomerActivationResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerActivationResult> customerActivationResult;

    /**
     * Gets the value of the customerActivationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerActivationResult }{@code >}
     *     
     */
    public JAXBElement<CustomerActivationResult> getCustomerActivationResult() {
        return customerActivationResult;
    }

    /**
     * Sets the value of the customerActivationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerActivationResult }{@code >}
     *     
     */
    public void setCustomerActivationResult(JAXBElement<CustomerActivationResult> value) {
        this.customerActivationResult = ((JAXBElement<CustomerActivationResult> ) value);
    }

}
