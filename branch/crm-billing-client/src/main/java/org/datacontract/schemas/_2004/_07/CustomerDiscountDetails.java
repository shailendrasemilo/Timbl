
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CustomerDiscountDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerDiscountDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="discountDetails" type="{http://schemas.datacontract.org/2004/07/}ArrayOfGetDiscountDetailsResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerDiscountDetails", propOrder = {
    "discountDetails"
})
public class CustomerDiscountDetails
    extends ApiResponse
{

    @XmlElementRef(name = "discountDetails", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfGetDiscountDetailsResult> discountDetails;

    /**
     * Gets the value of the discountDetails property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetDiscountDetailsResult }{@code >}
     *     
     */
    public JAXBElement<ArrayOfGetDiscountDetailsResult> getDiscountDetails() {
        return discountDetails;
    }

    /**
     * Sets the value of the discountDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetDiscountDetailsResult }{@code >}
     *     
     */
    public void setDiscountDetails(JAXBElement<ArrayOfGetDiscountDetailsResult> value) {
        this.discountDetails = ((JAXBElement<ArrayOfGetDiscountDetailsResult> ) value);
    }

}
