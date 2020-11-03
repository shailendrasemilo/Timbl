
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerPostPaymentResult;


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
 *         &lt;element name="PostPaymentResult" type="{http://schemas.datacontract.org/2004/07/}CustomerPostPaymentResult" minOccurs="0"/>
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
    "postPaymentResult"
})
@XmlRootElement(name = "PostPaymentResponse")
public class PostPaymentResponse {

    @XmlElementRef(name = "PostPaymentResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerPostPaymentResult> postPaymentResult;

    /**
     * Gets the value of the postPaymentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerPostPaymentResult }{@code >}
     *     
     */
    public JAXBElement<CustomerPostPaymentResult> getPostPaymentResult() {
        return postPaymentResult;
    }

    /**
     * Sets the value of the postPaymentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerPostPaymentResult }{@code >}
     *     
     */
    public void setPostPaymentResult(JAXBElement<CustomerPostPaymentResult> value) {
        this.postPaymentResult = ((JAXBElement<CustomerPostPaymentResult> ) value);
    }

}
