
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.BillDetails;


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
 *         &lt;element name="getBillingDetailsResult" type="{http://schemas.datacontract.org/2004/07/}BillDetails" minOccurs="0"/>
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
    "getBillingDetailsResult"
})
@XmlRootElement(name = "getBillingDetailsResponse")
public class GetBillingDetailsResponse {

    @XmlElementRef(name = "getBillingDetailsResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<BillDetails> getBillingDetailsResult;

    /**
     * Gets the value of the getBillingDetailsResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BillDetails }{@code >}
     *     
     */
    public JAXBElement<BillDetails> getGetBillingDetailsResult() {
        return getBillingDetailsResult;
    }

    /**
     * Sets the value of the getBillingDetailsResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BillDetails }{@code >}
     *     
     */
    public void setGetBillingDetailsResult(JAXBElement<BillDetails> value) {
        this.getBillingDetailsResult = ((JAXBElement<BillDetails> ) value);
    }

}
