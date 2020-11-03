
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.ApiRequest;
import org.datacontract.schemas._2004._07.ClsCustomerSpeedExceptionList;


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
 *         &lt;element name="CBEL" type="{http://schemas.datacontract.org/2004/07/}clsCustomerSpeedExceptionList" minOccurs="0"/>
 *         &lt;element name="Request" type="{http://schemas.datacontract.org/2004/07/}ApiRequest" minOccurs="0"/>
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
    "cbel",
    "request"
})
@XmlRootElement(name = "CustomerSpeedExceptionList")
public class CustomerSpeedExceptionList {

    @XmlElementRef(name = "CBEL", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<ClsCustomerSpeedExceptionList> cbel;
    @XmlElementRef(name = "Request", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<ApiRequest> request;

    /**
     * Gets the value of the cbel property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ClsCustomerSpeedExceptionList }{@code >}
     *     
     */
    public JAXBElement<ClsCustomerSpeedExceptionList> getCBEL() {
        return cbel;
    }

    /**
     * Sets the value of the cbel property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ClsCustomerSpeedExceptionList }{@code >}
     *     
     */
    public void setCBEL(JAXBElement<ClsCustomerSpeedExceptionList> value) {
        this.cbel = ((JAXBElement<ClsCustomerSpeedExceptionList> ) value);
    }

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}
     *     
     */
    public JAXBElement<ApiRequest> getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ApiRequest }{@code >}
     *     
     */
    public void setRequest(JAXBElement<ApiRequest> value) {
        this.request = ((JAXBElement<ApiRequest> ) value);
    }

}
