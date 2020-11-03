
package org.datacontract.schemas._2004._07;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DeviceDetailResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceDetailResult">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/}ApiResponse">
 *       &lt;sequence>
 *         &lt;element name="DeviceDetail" type="{http://schemas.datacontract.org/2004/07/}getDeviceDetailResult" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceDetailResult", propOrder = {
    "deviceDetail"
})
public class DeviceDetailResult
    extends ApiResponse
{

    @XmlElementRef(name = "DeviceDetail", namespace = "http://schemas.datacontract.org/2004/07/", type = JAXBElement.class)
    protected JAXBElement<GetDeviceDetailResult> deviceDetail;

    /**
     * Gets the value of the deviceDetail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link GetDeviceDetailResult }{@code >}
     *     
     */
    public JAXBElement<GetDeviceDetailResult> getDeviceDetail() {
        return deviceDetail;
    }

    /**
     * Sets the value of the deviceDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link GetDeviceDetailResult }{@code >}
     *     
     */
    public void setDeviceDetail(JAXBElement<GetDeviceDetailResult> value) {
        this.deviceDetail = ((JAXBElement<GetDeviceDetailResult> ) value);
    }

}
