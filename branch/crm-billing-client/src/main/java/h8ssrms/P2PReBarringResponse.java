
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.P2PReBarringResult;


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
 *         &lt;element name="P2PReBarringResult" type="{http://schemas.datacontract.org/2004/07/}P2PReBarringResult" minOccurs="0"/>
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
    "p2PReBarringResult"
})
@XmlRootElement(name = "P2PReBarringResponse")
public class P2PReBarringResponse {

    @XmlElementRef(name = "P2PReBarringResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<P2PReBarringResult> p2PReBarringResult;

    /**
     * Gets the value of the p2PReBarringResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link P2PReBarringResult }{@code >}
     *     
     */
    public JAXBElement<P2PReBarringResult> getP2PReBarringResult() {
        return p2PReBarringResult;
    }

    /**
     * Sets the value of the p2PReBarringResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link P2PReBarringResult }{@code >}
     *     
     */
    public void setP2PReBarringResult(JAXBElement<P2PReBarringResult> value) {
        this.p2PReBarringResult = ((JAXBElement<P2PReBarringResult> ) value);
    }

}
