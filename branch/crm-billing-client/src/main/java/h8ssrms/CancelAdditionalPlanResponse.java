
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CancelAddPostpaidPlanResult;


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
 *         &lt;element name="CancelAdditionalPlanResult" type="{http://schemas.datacontract.org/2004/07/}CancelAddPostpaidPlanResult" minOccurs="0"/>
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
    "cancelAdditionalPlanResult"
})
@XmlRootElement(name = "CancelAdditionalPlanResponse")
public class CancelAdditionalPlanResponse {

    @XmlElementRef(name = "CancelAdditionalPlanResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CancelAddPostpaidPlanResult> cancelAdditionalPlanResult;

    /**
     * Gets the value of the cancelAdditionalPlanResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CancelAddPostpaidPlanResult }{@code >}
     *     
     */
    public JAXBElement<CancelAddPostpaidPlanResult> getCancelAdditionalPlanResult() {
        return cancelAdditionalPlanResult;
    }

    /**
     * Sets the value of the cancelAdditionalPlanResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CancelAddPostpaidPlanResult }{@code >}
     *     
     */
    public void setCancelAdditionalPlanResult(JAXBElement<CancelAddPostpaidPlanResult> value) {
        this.cancelAdditionalPlanResult = ((JAXBElement<CancelAddPostpaidPlanResult> ) value);
    }

}
