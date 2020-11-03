
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for UserSessionResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="UserSessionResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="CDR" type="{http://schemas.datacontract.org/2004/07/}ArrayOfGetUserSessionResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UserSessionResult", propOrder = {
    "cdr"
})
public class UserSessionResult
    extends ApiResponse
{

    @XmlElementRef(name = "CDR", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfGetUserSessionResult> cdr;

    /**
     * Gets the value of the cdr property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetUserSessionResult }{@code >}
     *     
     */
    public JAXBElement<ArrayOfGetUserSessionResult> getCDR() {
        return cdr;
    }

    /**
     * Sets the value of the cdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetUserSessionResult }{@code >}
     *     
     */
    public void setCDR(JAXBElement<ArrayOfGetUserSessionResult> value) {
        this.cdr = ((JAXBElement<ArrayOfGetUserSessionResult> ) value);
    }

}
