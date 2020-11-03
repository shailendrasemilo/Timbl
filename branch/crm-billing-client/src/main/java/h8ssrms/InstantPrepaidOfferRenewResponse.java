
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.ApiResponse;


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
 *         &lt;element name="InstantPrepaidOfferRenewResult" type="{http://schemas.datacontract.org/2004/07/}ApiResponse" minOccurs="0"/>
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
    "instantPrepaidOfferRenewResult"
})
@XmlRootElement(name = "InstantPrepaidOfferRenewResponse")
public class InstantPrepaidOfferRenewResponse {

    @XmlElementRef(name = "InstantPrepaidOfferRenewResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<ApiResponse> instantPrepaidOfferRenewResult;

    /**
     * Gets the value of the instantPrepaidOfferRenewResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}
     *     
     */
    public JAXBElement<ApiResponse> getInstantPrepaidOfferRenewResult() {
        return instantPrepaidOfferRenewResult;
    }

    /**
     * Sets the value of the instantPrepaidOfferRenewResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ApiResponse }{@code >}
     *     
     */
    public void setInstantPrepaidOfferRenewResult(JAXBElement<ApiResponse> value) {
        this.instantPrepaidOfferRenewResult = ((JAXBElement<ApiResponse> ) value);
    }

}
