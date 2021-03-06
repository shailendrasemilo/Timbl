package com.np.tele.crm.pojos;

// default package
// Generated Dec 12, 2013 12:22:42 PM by Hibernate Tools 3.4.0.CR1
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * CrmUpfrontPayment generated by hbm2java
 */
public class CrmUpfrontPaymentPojo
    implements java.io.Serializable
{
    private Long                     upfrontId;
    private String                   chequeNo;
    private Date                     chequeDate;
    private long                     bankId;
    private double                   amount;
    private long                     partnerId;
    private String                   entityName;
    private String                   createdBy;
    private Date                     createdTime;
    private String                   lastModifiedBy;
    private Date                     lastModifiedTime;
    private String                   status;
    private Set<CrmUpCrfMappingPojo> crmUpCrfMappings = new HashSet<CrmUpCrfMappingPojo>();
    private String                   displayChequeDate;

    public CrmUpfrontPaymentPojo()
    {
    }

    public CrmUpfrontPaymentPojo( Long upfrontId,
                                  String chequeNo,
                                  long bankId,
                                  double amount,
                                  long partnerId,
                                  String entityName,
                                  String createdBy )
    {
        this.upfrontId = upfrontId;
        this.chequeNo = chequeNo;
        this.bankId = bankId;
        this.amount = amount;
        this.partnerId = partnerId;
        this.entityName = entityName;
        this.createdBy = createdBy;
    }

    public CrmUpfrontPaymentPojo( Long upfrontId,
                                  String chequeNo,
                                  Date chequeDate,
                                  long bankId,
                                  double amount,
                                  int partnerId,
                                  String entityName,
                                  String createdBy,
                                  Date createdTime,
                                  String lastModifiedBy,
                                  Date lastModifiedTime,
                                  String status,
                                  Set<CrmUpCrfMappingPojo> crmUpCrfMappings )
    {
        this.upfrontId = upfrontId;
        this.chequeNo = chequeNo;
        this.chequeDate = chequeDate;
        this.bankId = bankId;
        this.amount = amount;
        this.partnerId = partnerId;
        this.entityName = entityName;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.status = status;
        this.crmUpCrfMappings = crmUpCrfMappings;
    }

    public Long getUpfrontId()
    {
        return upfrontId;
    }

    public void setUpfrontId( Long upfrontId )
    {
        this.upfrontId = upfrontId;
    }

    public long getBankId()
    {
        return bankId;
    }

    public void setBankId( long bankId )
    {
        this.bankId = bankId;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long partnerId )
    {
        this.partnerId = partnerId;
    }

    public double getAmount()
    {
        return amount;
    }

    public void setAmount( double amount )
    {
        this.amount = amount;
    }

    public String getChequeNo()
    {
        return this.chequeNo;
    }

    public void setChequeNo( String chequeNo )
    {
        this.chequeNo = chequeNo;
    }

    public Date getChequeDate()
    {
        return this.chequeDate;
    }

    public void setChequeDate( Date chequeDate )
    {
        this.chequeDate = chequeDate;
    }

    public String getEntityName()
    {
        return this.entityName;
    }

    public void setEntityName( String entityName )
    {
        this.entityName = entityName;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date lastModifiedTime )
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    /**
     * @return the crmUpCrfMappings
     */
    public Set<CrmUpCrfMappingPojo> getCrmUpCrfMappings()
    {
        return crmUpCrfMappings;
    }

    /**
     * @param inCrmUpCrfMappings the crmUpCrfMappings to set
     */
    public void setCrmUpCrfMappings( Set<CrmUpCrfMappingPojo> inCrmUpCrfMappings )
    {
        crmUpCrfMappings = inCrmUpCrfMappings;
    }

    public String getDisplayChequeDate()
    {
        return displayChequeDate;
    }

    public void setDisplayChequeDate( String displayChequeDate )
    {
        this.displayChequeDate = displayChequeDate;
    }
}
