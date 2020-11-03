
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
 *         &lt;element name="GetUserSessionOfWeekResult" type="{http://schemas.datacontract.org/2004/07/}UserSessionResult" minOccurs="0"/>
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
    "getUserSessionOfWeekResult"
})
@XmlRootElement(name = "GetUserSessionOfWeekResponse")
public class GetUserSessionOfWeekResponse {

    @XmlElementRef(name = "GetUserSessionOfWeekResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<UserSessionResult> getUserSessionOfWeekResult;

    /**
     * Gets the value of the getUserSessionOfWeekResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}
     *     
     */
    public JAXBElement<UserSessionResult> getGetUserSessionOfWeekResult() {
        return getUserSessionOfWeekResult;
    }

    /**
     * Sets the value of the getUserSessionOfWeekResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link UserSessionResult }{@code >}
     *     
     */
    public void setGetUserSessionOfWeekResult(JAXBElement<UserSessionResult> value) {
        this.getUserSessionOfWeekResult = ((JAXBElement<UserSessionResult> ) value);
    }

}
