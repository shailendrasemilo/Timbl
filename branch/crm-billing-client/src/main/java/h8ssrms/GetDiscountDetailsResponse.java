
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerDiscountDetails;


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
 *         &lt;element name="GetDiscountDetailsResult" type="{http://schemas.datacontract.org/2004/07/}CustomerDiscountDetails" minOccurs="0"/>
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
    "getDiscountDetailsResult"
})
@XmlRootElement(name = "GetDiscountDetailsResponse")
public class GetDiscountDetailsResponse {

    @XmlElementRef(name = "GetDiscountDetailsResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerDiscountDetails> getDiscountDetailsResult;

    /**
     * Gets the value of the getDiscountDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerDiscountDetails }{@code >}
     *     
     */
    public JAXBElement<CustomerDiscountDetails> getGetDiscountDetailsResult() {
        return getDiscountDetailsResult;
    }

    /**
     * Sets the value of the getDiscountDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerDiscountDetails }{@code >}
     *     
     */
    public void setGetDiscountDetailsResult(JAXBElement<CustomerDiscountDetails> value) {
        this.getDiscountDetailsResult = ((JAXBElement<CustomerDiscountDetails> ) value);
    }

}
