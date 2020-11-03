
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for InventoryItemToLCOResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="InventoryItemToLCOResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="Items" type="{http://schemas.datacontract.org/2004/07/}ArrayOfGetDistributorDeviceDetailResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InventoryItemToLCOResult", propOrder = {
    "items"
})
public class InventoryItemToLCOResult
    extends ApiResponse
{

    @XmlElementRef(name = "Items", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<ArrayOfGetDistributorDeviceDetailResult> items;

    /**
     * Gets the value of the items property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetDistributorDeviceDetailResult }{@code >}
     *     
     */
    public JAXBElement<ArrayOfGetDistributorDeviceDetailResult> getItems() {
        return items;
    }

    /**
     * Sets the value of the items property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfGetDistributorDeviceDetailResult }{@code >}
     *     
     */
    public void setItems(JAXBElement<ArrayOfGetDistributorDeviceDetailResult> value) {
        this.items = ((JAXBElement<ArrayOfGetDistributorDeviceDetailResult> ) value);
    }

}
