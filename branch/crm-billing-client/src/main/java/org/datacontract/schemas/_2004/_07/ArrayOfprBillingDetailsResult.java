
package org.datacontract.schemas._2004._07;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfpr_BillingDetailsResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfpr_BillingDetailsResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pr_BillingDetailsResult" type="{http://schemas.datacontract.org/2004/07/}pr_BillingDetailsResult" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfpr_BillingDetailsResult", propOrder = {
    "prBillingDetailsResult"
})
public class ArrayOfprBillingDetailsResult {

    @XmlElement(name = "pr_BillingDetailsResult", nillable = true)
    protected List<PrBillingDetailsResult> prBillingDetailsResult;

    /**
     * Gets the value of the prBillingDetailsResult property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prBillingDetailsResult property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrBillingDetailsResult().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrBillingDetailsResult }
     * 
     * 
     */
    public List<PrBillingDetailsResult> getPrBillingDetailsResult() {
        if (prBillingDetailsResult == null) {
            prBillingDetailsResult = new ArrayList<PrBillingDetailsResult>();
        }
        return this.prBillingDetailsResult;
    }

}
