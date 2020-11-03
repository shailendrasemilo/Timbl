
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BillDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillDetails">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="BillingDetails" type="{http://schemas.datacontract.org/2004/07/}ArrayOfpr_BillingDetailsResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillDetails", propOrder = {
    "billingDetails"
})
public class BillDetails
    extends ApiResponse
{

    @XmlElementRef(name = "BillingDetails", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfprBillingDetailsResult> billingDetails;

    /**
     * Gets the value of the billingDetails property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfprBillingDetailsResult }{@code >}
     *     
     */
    public JAXBElement<ArrayOfprBillingDetailsResult> getBillingDetails() {
        return billingDetails;
    }

    /**
     * Sets the value of the billingDetails property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfprBillingDetailsResult }{@code >}
     *     
     */
    public void setBillingDetails(JAXBElement<ArrayOfprBillingDetailsResult> value) {
        this.billingDetails = ((JAXBElement<ArrayOfprBillingDetailsResult> ) value);
    }

}
