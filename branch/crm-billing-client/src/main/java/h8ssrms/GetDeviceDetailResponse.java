
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.DeviceDetailResult;


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
 *         &lt;element name="GetDeviceDetailResult" type="{http://schemas.datacontract.org/2004/07/}DeviceDetailResult" minOccurs="0"/>
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
    "getDeviceDetailResult"
})
@XmlRootElement(name = "GetDeviceDetailResponse")
public class GetDeviceDetailResponse {

    @XmlElementRef(name = "GetDeviceDetailResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<DeviceDetailResult> getDeviceDetailResult;

    /**
     * Gets the value of the getDeviceDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link DeviceDetailResult }{@code >}
     *     
     */
    public JAXBElement<DeviceDetailResult> getGetDeviceDetailResult() {
        return getDeviceDetailResult;
    }

    /**
     * Sets the value of the getDeviceDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link DeviceDetailResult }{@code >}
     *     
     */
    public void setGetDeviceDetailResult(JAXBElement<DeviceDetailResult> value) {
        this.getDeviceDetailResult = ((JAXBElement<DeviceDetailResult> ) value);
    }

}
