package com.np.tele.crm.pojos;

// Generated Dec 12, 2013 12:22:42 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;

import javax.xml.bind.annotation.XmlTransient;

/**
 * CrmUpCrfMapping generated by hbm2java
 */
public class CrmUpCrfMappingPojo
    implements java.io.Serializable
{
    private Long                  upCrfMappingId;
    private CrmUpfrontPaymentPojo crmUpfrontPayment;
    private String                crfId;
    private String                createdBy;
    private Date                  createdTime;
    private String                status;

    public CrmUpCrfMappingPojo()
    {
    }

    public CrmUpCrfMappingPojo( Long upCrfMappingId, String crfId, String createdBy )
    {
        this.upCrfMappingId = upCrfMappingId;
        this.crfId = crfId;
        this.createdBy = createdBy;
    }

    public CrmUpCrfMappingPojo( Long upCrfMappingId,
                                CrmUpfrontPaymentPojo crmUpfrontPayment,
                                String crfId,
                                String createdBy,
                                Date createdTime,
                                String status )
    {
        this.upCrfMappingId = upCrfMappingId;
        this.crfId = crfId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.status = status;
    }

    public Long getUpCrfMappingId()
    {
        return upCrfMappingId;
    }

    public void setUpCrfMappingId( Long upCrfMappingId )
    {
        this.upCrfMappingId = upCrfMappingId;
    }

    /**
     * @return the crfId
     */
    public String getCrfId()
    {
        return crfId;
    }

    /**
     * @param inCrfId the crfId to set
     */
    public void setCrfId( String inCrfId )
    {
        crfId = inCrfId;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy()
    {
        return createdBy;
    }

    /**
     * @param inCreatedBy the createdBy to set
     */
    public void setCreatedBy( String inCreatedBy )
    {
        createdBy = inCreatedBy;
    }

    /**
     * @return the createdTime
     */
    public Date getCreatedTime()
    {
        return createdTime;
    }

    /**
     * @param inCreatedTime the createdTime to set
     */
    public void setCreatedTime( Date inCreatedTime )
    {
        createdTime = inCreatedTime;
    }

    /**
     * @return the status
     */
    public String getStatus()
    {
        return status;
    }

    /**
     * @param inStatus the status to set
     */
    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    /**
     * @return the crmUpfrontPayment
     */
    @XmlTransient
    public CrmUpfrontPaymentPojo getCrmUpfrontPayment()
    {
        return crmUpfrontPayment;
    }

    /**
     * @param inCrmUpfrontPayment the crmUpfrontPayment to set
     */
    public void setCrmUpfrontPayment( CrmUpfrontPaymentPojo inCrmUpfrontPayment )
    {
        crmUpfrontPayment = inCrmUpfrontPayment;
    }
}
