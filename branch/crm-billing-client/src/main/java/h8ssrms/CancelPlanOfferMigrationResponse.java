
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.CancelPlanMigrationResult;


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
 *         &lt;element name="CancelPlanOfferMigrationResult" type="{http://schemas.datacontract.org/2004/07/}CancelPlanMigrationResult" minOccurs="0"/>
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
    "cancelPlanOfferMigrationResult"
})
@XmlRootElement(name = "CancelPlanOfferMigrationResponse")
public class CancelPlanOfferMigrationResponse {

    @XmlElementRef(name = "CancelPlanOfferMigrationResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<CancelPlanMigrationResult> cancelPlanOfferMigrationResult;

    /**
     * Gets the value of the cancelPlanOfferMigrationResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CancelPlanMigrationResult }{@code >}
     *     
     */
    public JAXBElement<CancelPlanMigrationResult> getCancelPlanOfferMigrationResult() {
        return cancelPlanOfferMigrationResult;
    }

    /**
     * Sets the value of the cancelPlanOfferMigrationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CancelPlanMigrationResult }{@code >}
     *     
     */
    public void setCancelPlanOfferMigrationResult(JAXBElement<CancelPlanMigrationResult> value) {
        this.cancelPlanOfferMigrationResult = ((JAXBElement<CancelPlanMigrationResult> ) value);
    }

}
