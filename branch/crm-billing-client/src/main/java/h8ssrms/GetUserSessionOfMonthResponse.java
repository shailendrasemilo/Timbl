
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.UserSessionResult;


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
 *         &lt;element name="GetUserSessionOfMonthResult" type="{http://schemas.datacontract.org/2004/07/}UserSessionResult" minOccurs="0"/>
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
    "getUserSessionOfMonthResult"
})
@XmlRootElement(name = "GetUserSessionOfMonthResponse")
public class GetUserSessionOfMonthResponse {

    @XmlElementRef(name = "GetUserSessionOfMonthResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<UserSessionResult> getUserSessionOfMonthResult;

    /**
     * Gets the value of the getUserSessionOfMonthResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}
     *     
     */
    public JAXBElement<UserSessionResult> getGetUserSessionOfMonthResult() {
        return getUserSessionOfMonthResult;
    }

    /**
     * Sets the value of the getUserSessionOfMonthResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}
     *     
     */
    public void setGetUserSessionOfMonthResult(JAXBElement<UserSessionResult> value) {
        this.getUserSessionOfMonthResult = ((JAXBElement<UserSessionResult> ) value);
    }

}
