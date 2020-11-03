package com.np.tele.crm.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.np.tele.crm.ext.pojos.CrmCustAdditionalDetails;

public class CrmBillingDto
{
    private String                   statusCode            = null;
    private String                   statusDesc            = null;
    private String                   billingErrorCode      = null;
    private String                   customerId            = null;
    private String                   chargeName            = null;
    private BigDecimal               chargeAmount          = null;
    private String                   remarks               = null;
    private String                   status                = null;
    private Date                     fromDate              = null;
    private Date                     toDate                = null;
    private CrmCustAdditionalDetails custAdditionalDetails = null;
    private String                   paramValue            = null;
    private boolean                  boolValue             = false;
    private Date                     expiryDate            = null;

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getBillingErrorCode()
    {
        return billingErrorCode;
    }

    public void setBillingErrorCode( String inBillingErrorCode )
    {
        billingErrorCode = inBillingErrorCode;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getChargeName()
    {
        return chargeName;
    }

    public void setChargeName( String inChargeName )
    {
        chargeName = inChargeName;
    }

    public BigDecimal getChargeAmount()
    {
        return chargeAmount;
    }

    public void setChargeAmount( BigDecimal chargeAmount )
    {
        this.chargeAmount = chargeAmount;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String inRemarks )
    {
        remarks = inRemarks;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String inStatus )
    {
        status = inStatus;
    }

    public Date getFromDate()
    {
        return fromDate;
    }

    public void setFromDate( Date fromDate )
    {
        this.fromDate = fromDate;
    }

    public Date getToDate()
    {
        return toDate;
    }

    public void setToDate( Date toDate )
    {
        this.toDate = toDate;
    }

    public CrmCustAdditionalDetails getCustAdditionalDetails()
    {
        return custAdditionalDetails;
    }

    public void setCustAdditionalDetails( CrmCustAdditionalDetails inCustAdditionalDetails )
    {
        custAdditionalDetails = inCustAdditionalDetails;
    }

    /**
     * Will be used as
     * @return
     * PlanCode: For cancelAdditionalPlan API
     * 
     */
    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue( String inParamValue )
    {
        paramValue = inParamValue;
    }

    /**
     * Will be used as
     * @return
     * Immediate: For cancelAdditionalPlan API
     */
    public boolean isBoolValue()
    {
        return boolValue;
    }

    public void setBoolValue( boolean inBoolValue )
    {
        boolValue = inBoolValue;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate( Date inExpiryDate )
    {
        expiryDate = inExpiryDate;
    }
}
