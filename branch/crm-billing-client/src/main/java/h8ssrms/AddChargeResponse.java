
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.AddChargeResult;


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
 *         &lt;element name="AddChargeResult" type="{http://schemas.datacontract.org/2004/07/}AddChargeResult" minOccurs="0"/>
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
    "addChargeResult"
})
@XmlRootElement(name = "AddChargeResponse")
public class AddChargeResponse {

    @XmlElementRef(name = "AddChargeResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<AddChargeResult> addChargeResult;

    /**
     * Gets the value of the addChargeResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddChargeResult }{@code >}
     *     
     */
    public JAXBElement<AddChargeResult> getAddChargeResult() {
        return addChargeResult;
    }

    /**
     * Sets the value of the addChargeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddChargeResult }{@code >}
     *     
     */
    public void setAddChargeResult(JAXBElement<AddChargeResult> value) {
        this.addChargeResult = ((JAXBElement<AddChargeResult> ) value);
    }

}
