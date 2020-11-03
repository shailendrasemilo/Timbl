
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.DisableWalledGardenResult;


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
 *         &lt;element name="DisableWalledGardenResult" type="{http://schemas.datacontract.org/2004/07/}DisableWalledGardenResult" minOccurs="0"/>
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
    "disableWalledGardenResult"
})
@XmlRootElement(name = "DisableWalledGardenResponse")
public class DisableWalledGardenResponse {

    @XmlElementRef(name = "DisableWalledGardenResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<DisableWalledGardenResult> disableWalledGardenResult;

    /**
     * Gets the value of the disableWalledGardenResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DisableWalledGardenResult }{@code >}
     *     
     */
    public JAXBElement<DisableWalledGardenResult> getDisableWalledGardenResult() {
        return disableWalledGardenResult;
    }

    /**
     * Sets the value of the disableWalledGardenResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DisableWalledGardenResult }{@code >}
     *     
     */
    public void setDisableWalledGardenResult(JAXBElement<DisableWalledGardenResult> value) {
        this.disableWalledGardenResult = ((JAXBElement<DisableWalledGardenResult> ) value);
    }

}
