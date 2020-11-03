
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for lmsOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="lmsOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operationParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="LMSDto" type="{http://service.lms.crm.tele.np.com/}lmsDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lmsOperation", propOrder = {
    "operationParam",
    "lmsDto"
})
public class LmsOperation {

    protected String operationParam;
    @XmlElement(name = "LMSDto")
    protected LmsDto lmsDto;

    /**
     * Gets the value of the operationParam property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperationParam() {
        return operationParam;
    }

    /**
     * Sets the value of the operationParam property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperationParam(String value) {
        this.operationParam = value;
    }

    /**
     * Gets the value of the lmsDto property.
     * 
     * @return
     *     possible object is
     *     {@link LmsDto }
     *     
     */
    public LmsDto getLMSDto() {
        return lmsDto;
    }

    /**
     * Sets the value of the lmsDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link LmsDto }
     *     
     */
    public void setLMSDto(LmsDto value) {
        this.lmsDto = value;
    }

}
