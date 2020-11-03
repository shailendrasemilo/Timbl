
package h8ssrms;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.datacontract.schemas._2004._07.BillDetail;


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
 *         &lt;element name="GetBillDetailResult" type="{http://schemas.datacontract.org/2004/07/}BillDetail" minOccurs="0"/>
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
    "getBillDetailResult"
})
@XmlRootElement(name = "GetBillDetailResponse")
public class GetBillDetailResponse {

    @XmlElementRef(name = "GetBillDetailResult", namespace = "h8SSRMS", type = JAXBElement.class)
    protected JAXBElement<BillDetail> getBillDetailResult;

    /**
     * Gets the value of the getBillDetailResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BillDetail }{@code >}
     *     
     */
    public JAXBElement<BillDetail> getGetBillDetailResult() {
        return getBillDetailResult;
    }

    /**
     * Sets the value of the getBillDetailResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BillDetail }{@code >}
     *     
     */
    public void setGetBillDetailResult(JAXBElement<BillDetail> value) {
        this.getBillDetailResult = ((JAXBElement<BillDetail> ) value);
    }

}
