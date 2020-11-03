
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for parameterGroupOperations complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="parameterGroupOperations">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="masterDto" type="{http://service.masterdata.crm.tele.np.com/}masterDataDto" minOccurs="0"/>
 *         &lt;element name="operation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "parameterGroupOperations", propOrder = {
    "masterDto",
    "operation"
})
public class ParameterGroupOperations {

    protected MasterDataDto masterDto;
    protected String operation;

    /**
     * Gets the value of the masterDto property.
     * 
     * @return
     *     possible object is
     *     {@link MasterDataDto }
     *     
     */
    public MasterDataDto getMasterDto() {
        return masterDto;
    }

    /**
     * Sets the value of the masterDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link MasterDataDto }
     *     
     */
    public void setMasterDto(MasterDataDto value) {
        this.masterDto = value;
    }

    /**
     * Gets the value of the operation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Sets the value of the operation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOperation(String value) {
        this.operation = value;
    }

}
