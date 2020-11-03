
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.ChangeBusinessPartnerResult;


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
 *         &lt;element name="ChangeBusinessPartnerResult" type="{http://schemas.datacontract.org/2004/07/}ChangeBusinessPartnerResult" minOccurs="0"/>
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
    "changeBusinessPartnerResult"
})
@XmlRootElement(name = "ChangeBusinessPartnerResponse")
public class ChangeBusinessPartnerResponse {

    @XmlElementRef(name = "ChangeBusinessPartnerResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<ChangeBusinessPartnerResult> changeBusinessPartnerResult;

    /**
     * Gets the value of the changeBusinessPartnerResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ChangeBusinessPartnerResult }{@code >}
     *     
     */
    public JAXBElement<ChangeBusinessPartnerResult> getChangeBusinessPartnerResult() {
        return changeBusinessPartnerResult;
    }

    /**
     * Sets the value of the changeBusinessPartnerResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ChangeBusinessPartnerResult }{@code >}
     *     
     */
    public void setChangeBusinessPartnerResult(JAXBElement<ChangeBusinessPartnerResult> value) {
        this.changeBusinessPartnerResult = ((JAXBElement<ChangeBusinessPartnerResult> ) value);
    }

}
