
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.LastPaymentResult;


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
 *         &lt;element name="GetLastPaymentResult" type="{http://schemas.datacontract.org/2004/07/}LastPaymentResult" minOccurs="0"/>
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
    "getLastPaymentResult"
})
@XmlRootElement(name = "GetLastPaymentResponse")
public class GetLastPaymentResponse {

    @XmlElementRef(name = "GetLastPaymentResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<LastPaymentResult> getLastPaymentResult;

    /**
     * Gets the value of the getLastPaymentResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link LastPaymentResult }{@code >}
     *     
     */
    public JAXBElement<LastPaymentResult> getGetLastPaymentResult() {
        return getLastPaymentResult;
    }

    /**
     * Sets the value of the getLastPaymentResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link LastPaymentResult }{@code >}
     *     
     */
    public void setGetLastPaymentResult(JAXBElement<LastPaymentResult> value) {
        this.getLastPaymentResult = ((JAXBElement<LastPaymentResult> ) value);
    }

}
