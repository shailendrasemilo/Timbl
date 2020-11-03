
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.ApiRequest;


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
 *         &lt;element name="custBillAdd" type="{http://schemas.datacontract.org/2004/07/}ChangeAddress" minOccurs="0"/>
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
    "custBillAdd",
    "request"
})
@XmlRootElement(name = "ChangeAddress")
public class ChangeAddress {

    @XmlElementRef(name = "custBillAdd", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<org.datacontract.schemas._2004._07.ChangeAddress> custBillAdd;
    @XmlElementRef(name = "Request", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<ApiRequest> request;

    /**
     * Gets the value of the custBillAdd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.ChangeAddress }{@code >}
     *     
     */
    public JAXBElement<org.datacontract.schemas._2004._07.ChangeAddress> getCustBillAdd() {
        return custBillAdd;
    }

    /**
     * Sets the value of the custBillAdd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link org.datacontract.schemas._2004._07.ChangeAddress }{@code >}
     *     
     */
    public void setCustBillAdd(JAXBElement<org.datacontract.schemas._2004._07.ChangeAddress> value) {
        this.custBillAdd = ((JAXBElement<org.datacontract.schemas._2004._07.ChangeAddress> ) value);
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
