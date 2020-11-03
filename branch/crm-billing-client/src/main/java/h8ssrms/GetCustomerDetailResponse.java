
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerDetail;


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
 *         &lt;element name="GetCustomerDetailResult" type="{http://schemas.datacontract.org/2004/07/}CustomerDetail" minOccurs="0"/>
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
    "getCustomerDetailResult"
})
@XmlRootElement(name = "GetCustomerDetailResponse")
public class GetCustomerDetailResponse {

    @XmlElementRef(name = "GetCustomerDetailResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerDetail> getCustomerDetailResult;

    /**
     * Gets the value of the getCustomerDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerDetail }{@code >}
     *     
     */
    public JAXBElement<CustomerDetail> getGetCustomerDetailResult() {
        return getCustomerDetailResult;
    }

    /**
     * Sets the value of the getCustomerDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerDetail }{@code >}
     *     
     */
    public void setGetCustomerDetailResult(JAXBElement<CustomerDetail> value) {
        this.getCustomerDetailResult = ((JAXBElement<CustomerDetail> ) value);
    }

}
