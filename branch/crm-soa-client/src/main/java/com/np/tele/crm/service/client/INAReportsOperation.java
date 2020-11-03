
package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for INAReportsOperation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="INAReportsOperation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="operationParam" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ReportDto" type="{http://service.report.crm.tele.np.com/}reportDto" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INAReportsOperation", propOrder = {
    "operationParam",
    "reportDto"
})
public class INAReportsOperation {

    protected String operationParam;
    @XmlElement(name = "ReportDto")
    protected ReportDto reportDto;

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
     * Gets the value of the reportDto property.
     * 
     * @return
     *     possible object is
     *     {@link ReportDto }
     *     
     */
    public ReportDto getReportDto() {
        return reportDto;
    }

    /**
     * Sets the value of the reportDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReportDto }
     *     
     */
    public void setReportDto(ReportDto value) {
        this.reportDto = value;
    }

}
