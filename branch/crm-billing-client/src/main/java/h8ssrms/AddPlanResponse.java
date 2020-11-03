
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.AddPlanResult;


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
 *         &lt;element name="AddPlanResult" type="{http://schemas.datacontract.org/2004/07/}AddPlanResult" minOccurs="0"/>
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
    "addPlanResult"
})
@XmlRootElement(name = "AddPlanResponse")
public class AddPlanResponse {

    @XmlElementRef(name = "AddPlanResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<AddPlanResult> addPlanResult;

    /**
     * Gets the value of the addPlanResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link AddPlanResult }{@code >}
     *     
     */
    public JAXBElement<AddPlanResult> getAddPlanResult() {
        return addPlanResult;
    }

    /**
     * Sets the value of the addPlanResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link AddPlanResult }{@code >}
     *     
     */
    public void setAddPlanResult(JAXBElement<AddPlanResult> value) {
        this.addPlanResult = ((JAXBElement<AddPlanResult> ) value);
    }

}
