
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CustomerUsageDetail;


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
 *         &lt;element name="GetCustomerUsageDetailResult" type="{http://schemas.datacontract.org/2004/07/}CustomerUsageDetail" minOccurs="0"/>
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
    "getCustomerUsageDetailResult"
})
@XmlRootElement(name = "GetCustomerUsageDetailResponse")
public class GetCustomerUsageDetailResponse {

    @XmlElementRef(name = "GetCustomerUsageDetailResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CustomerUsageDetail> getCustomerUsageDetailResult;

    /**
     * Gets the value of the getCustomerUsageDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CustomerUsageDetail }{@code >}
     *     
     */
    public JAXBElement<CustomerUsageDetail> getGetCustomerUsageDetailResult() {
        return getCustomerUsageDetailResult;
    }

    /**
     * Sets the value of the getCustomerUsageDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CustomerUsageDetail }{@code >}
     *     
     */
    public void setGetCustomerUsageDetailResult(JAXBElement<CustomerUsageDetail> value) {
        this.getCustomerUsageDetailResult = ((JAXBElement<CustomerUsageDetail> ) value);
    }

}
