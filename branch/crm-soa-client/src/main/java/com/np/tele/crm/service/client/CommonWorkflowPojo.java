package com.np.tele.crm.service.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for commonWorkflowPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="commonWorkflowPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="createdTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inboxId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="previousStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="previousStageOwner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="unRead" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="workflowId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowStage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="workflowType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "commonWorkflowPojo", propOrder =
{ "createdTime", "customerId", "inboxId", "previousStage", "previousStageOwner", "requestType", "unRead", "workflowId",
 "workflowStage", "workflowType", "currentBin" })
public class CommonWorkflowPojo
{
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createdTime;
    protected String               customerId;
    protected long                 inboxId;
    protected String               previousStage;
    protected String               previousStageOwner;
    protected String               requestType;
    protected boolean              unRead;
    protected String               workflowId;
    protected String               workflowStage;
    protected String               workflowType;
    protected String               currentBin;

    /**
     * Gets the value of the createdTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreatedTime()
    {
        return createdTime;
    }

    /**
     * Sets the value of the createdTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreatedTime( XMLGregorianCalendar value )
    {
        this.createdTime = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId()
    {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId( String value )
    {
        this.customerId = value;
    }

    /**
     * Gets the value of the inboxId property.
     * 
     */
    public long getInboxId()
    {
        return inboxId;
    }

    /**
     * Sets the value of the inboxId property.
     * 
     */
    public void setInboxId( long value )
    {
        this.inboxId = value;
    }

    /**
     * Gets the value of the previousStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousStage()
    {
        return previousStage;
    }

    /**
     * Sets the value of the previousStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousStage( String value )
    {
        this.previousStage = value;
    }

    /**
     * Gets the value of the previousStageOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreviousStageOwner()
    {
        return previousStageOwner;
    }

    /**
     * Sets the value of the previousStageOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreviousStageOwner( String value )
    {
        this.previousStageOwner = value;
    }

    /**
     * Gets the value of the requestType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestType()
    {
        return requestType;
    }

    /**
     * Sets the value of the requestType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestType( String value )
    {
        this.requestType = value;
    }

    /**
     * Gets the value of the unRead property.
     * 
     */
    public boolean isUnRead()
    {
        return unRead;
    }

    /**
     * Sets the value of the unRead property.
     * 
     */
    public void setUnRead( boolean value )
    {
        this.unRead = value;
    }

    /**
     * Gets the value of the workflowId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowId()
    {
        return workflowId;
    }

    /**
     * Sets the value of the workflowId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowId( String value )
    {
        this.workflowId = value;
    }

    /**
     * Gets the value of the workflowStage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowStage()
    {
        return workflowStage;
    }

    /**
     * Sets the value of the workflowStage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowStage( String value )
    {
        this.workflowStage = value;
    }

    /**
     * Gets the value of the workflowType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkflowType()
    {
        return workflowType;
    }

    /**
     * Sets the value of the workflowType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkflowType( String value )
    {
        this.workflowType = value;
    }

    public String getCurrentBin()
    {
        return currentBin;
    }

    public void setCurrentBin( String inCurrentBin )
    {
        currentBin = inCurrentBin;
    }
}
