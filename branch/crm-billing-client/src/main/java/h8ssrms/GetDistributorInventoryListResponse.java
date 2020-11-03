
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.InventoryItemToLCOResult;


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
 *         &lt;element name="GetDistributorInventoryListResult" type="{http://schemas.datacontract.org/2004/07/}InventoryItemToLCOResult" minOccurs="0"/>
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
    "getDistributorInventoryListResult"
})
@XmlRootElement(name = "GetDistributorInventoryListResponse")
public class GetDistributorInventoryListResponse {

    @XmlElementRef(name = "GetDistributorInventoryListResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<InventoryItemToLCOResult> getDistributorInventoryListResult;

    /**
     * Gets the value of the getDistributorInventoryListResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link InventoryItemToLCOResult }{@code >}
     *     
     */
    public JAXBElement<InventoryItemToLCOResult> getGetDistributorInventoryListResult() {
        return getDistributorInventoryListResult;
    }

    /**
     * Sets the value of the getDistributorInventoryListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link InventoryItemToLCOResult }{@code >}
     *     
     */
    public void setGetDistributorInventoryListResult(JAXBElement<InventoryItemToLCOResult> value) {
        this.getDistributorInventoryListResult = ((JAXBElement<InventoryItemToLCOResult> ) value);
    }

}
