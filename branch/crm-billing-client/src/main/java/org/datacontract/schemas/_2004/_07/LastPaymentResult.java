
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LastPaymentResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LastPaymentResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="LastPayment" type="{http://schemas.datacontract.org/2004/07/}getLastPaymentResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LastPaymentResult", propOrder = {
    "lastPayment"
})
public class LastPaymentResult
    extends ApiResponse
{

    @XmlElementRef(name = "LastPayment", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<GetLastPaymentResult> lastPayment;

    /**
     * Gets the value of the lastPayment property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetLastPaymentResult }{@code >}
     *     
     */
    public JAXBElement<GetLastPaymentResult> getLastPayment() {
        return lastPayment;
    }

    /**
     * Sets the value of the lastPayment property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetLastPaymentResult }{@code >}
     *     
     */
    public void setLastPayment(JAXBElement<GetLastPaymentResult> value) {
        this.lastPayment = ((JAXBElement<GetLastPaymentResult> ) value);
    }

}
