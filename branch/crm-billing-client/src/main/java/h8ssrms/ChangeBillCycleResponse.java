
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.ChangeBillCycleResult;


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
 *         &lt;element name="ChangeBillCycleResult" type="{http://schemas.datacontract.org/2004/07/}ChangeBillCycleResult" minOccurs="0"/>
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
    "changeBillCycleResult"
})
@XmlRootElement(name = "ChangeBillCycleResponse")
public class ChangeBillCycleResponse {

    @XmlElementRef(name = "ChangeBillCycleResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<ChangeBillCycleResult> changeBillCycleResult;

    /**
     * Gets the value of the changeBillCycleResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ChangeBillCycleResult }{@code >}
     *     
     */
    public JAXBElement<ChangeBillCycleResult> getChangeBillCycleResult() {
        return changeBillCycleResult;
    }

    /**
     * Sets the value of the changeBillCycleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ChangeBillCycleResult }{@code >}
     *     
     */
    public void setChangeBillCycleResult(JAXBElement<ChangeBillCycleResult> value) {
        this.changeBillCycleResult = ((JAXBElement<ChangeBillCycleResult> ) value);
    }

}
