
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CreateCustomerResult;


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
 *         &lt;element name="CreateCustomerResult" type="{http://schemas.datacontract.org/2004/07/}CreateCustomerResult" minOccurs="0"/>
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
    "createCustomerResult"
})
@XmlRootElement(name = "CreateCustomerResponse")
public class CreateCustomerResponse {

    @XmlElementRef(name = "CreateCustomerResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CreateCustomerResult> createCustomerResult;

    /**
     * Gets the value of the createCustomerResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CreateCustomerResult }{@code >}
     *     
     */
    public JAXBElement<CreateCustomerResult> getCreateCustomerResult() {
        return createCustomerResult;
    }

    /**
     * Sets the value of the createCustomerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CreateCustomerResult }{@code >}
     *     
     */
    public void setCreateCustomerResult(JAXBElement<CreateCustomerResult> value) {
        this.createCustomerResult = ((JAXBElement<CreateCustomerResult> ) value);
    }

}
