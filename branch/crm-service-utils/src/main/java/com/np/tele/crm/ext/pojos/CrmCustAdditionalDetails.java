package com.np.tele.crm.ext.pojos;

import java.util.Date;

public class CrmCustAdditionalDetails
{
    private Double balance              = 0.0;
    private Date   expiryDate           = null;
    private Date   activationDate       = null;
    private Long   currentBandwidth     = null;
    private String planCode             = null;
    private Long   allocatedVolumeQuota = null;
    private Long   usedVolumeQuota      = null;
    private Long   remainVolumeQuota    = null;
    private Long   primaryQuota         = null;
    private Long   primaryUsedQuota     = null;
    private Long   primaryRemainQuota   = null;
    private String secondaryQuota       = null;
    private Long   secondaryUsedQuota   = null;
    private Long   secondaryRemainQuota = null;
    private String addonPlanCode        = null;
    private String billCycle            = null;
    private Double planRental;
    private Date   currentSession;
    private String status;

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public Date getCurrentSession()
    {
        return currentSession;
    }

    public void setCurrentSession( Date currentSession )
    {
        this.currentSession = currentSession;
    }

    public Double getPlanRental()
    {
        return planRental;
    }

    public void setPlanRental( Double planRental )
    {
        this.planRental = planRental;
    }

    public String getAddonPlanCode()
    {
        return addonPlanCode;
    }

    public void setAddonPlanCode( String inAddonPlanCode )
    {
        addonPlanCode = inAddonPlanCode;
    }

    // Don't Delete may be used in future.
    //    private String status               = null;
    //    private String userName             = null;
    //    private Long   createdById          = 0l;
    //    private String planName             = null;
    public Double getBalance()
    {
        return balance;
    }

    public void setBalance( Double inBalance )
    {
        balance = inBalance;
    }

    public Date getExpiryDate()
    {
        return expiryDate;
    }

    public void setExpiryDate( Date inExpiryDate )
    {
        expiryDate = inExpiryDate;
    }

    public Date getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate( Date inActivationDate )
    {
        activationDate = inActivationDate;
    }

    public Long getCurrentBandwidth()
    {
        return currentBandwidth;
    }

    public void setCurrentBandwidth( Long inCurrentBandwidth )
    {
        currentBandwidth = inCurrentBandwidth;
    }

    public String getPlanCode()
    {
        return planCode;
    }

    public void setPlanCode( String inPlanCode )
    {
        planCode = inPlanCode;
    }

    public Long getAllocatedVolumeQuota()
    {
        return allocatedVolumeQuota;
    }

    public void setAllocatedVolumeQuota( Long inAllocatedVolumeQuota )
    {
        allocatedVolumeQuota = inAllocatedVolumeQuota;
    }

    public Long getUsedVolumeQuota()
    {
        return usedVolumeQuota;
    }

    public void setUsedVolumeQuota( Long inUsedVolumeQuota )
    {
        usedVolumeQuota = inUsedVolumeQuota;
    }

    public Long getRemainVolumeQuota()
    {
        return remainVolumeQuota;
    }

    public void setRemainVolumeQuota( Long inRemainVolumeQuota )
    {
        remainVolumeQuota = inRemainVolumeQuota;
    }

    public Long getPrimaryQuota()
    {
        return primaryQuota;
    }

    public void setPrimaryQuota( Long inPrimaryQuota )
    {
        primaryQuota = inPrimaryQuota;
    }

    public Long getPrimaryUsedQuota()
    {
        return primaryUsedQuota;
    }

    public void setPrimaryUsedQuota( Long inPrimaryUsedQuota )
    {
        primaryUsedQuota = inPrimaryUsedQuota;
    }

    public Long getPrimaryRemainQuota()
    {
        return primaryRemainQuota;
    }

    public void setPrimaryRemainQuota( Long inPrimaryRemainQuota )
    {
        primaryRemainQuota = inPrimaryRemainQuota;
    }

    public String getSecondaryQuota()
    {
        return secondaryQuota;
    }

    public void setSecondaryQuota( String inSecondaryQuota )
    {
        secondaryQuota = inSecondaryQuota;
    }

    public Long getSecondaryUsedQuota()
    {
        return secondaryUsedQuota;
    }

    public void setSecondaryUsedQuota( Long inSecondaryUsedQuota )
    {
        secondaryUsedQuota = inSecondaryUsedQuota;
    }

    public Long getSecondaryRemainQuota()
    {
        return secondaryRemainQuota;
    }

    public void setSecondaryRemainQuota( Long inSecondaryRemainQuota )
    {
        secondaryRemainQuota = inSecondaryRemainQuota;
    }

    public String getBillCycle()
    {
        return billCycle;
    }

    public void setBillCycle( String inBillCycle )
    {
        billCycle = inBillCycle;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCustAdditionalDetails [balance=" ).append( balance ).append( ", expiryDate=" )
                .append( expiryDate ).append( ", activationDate=" ).append( activationDate )
                .append( ", currentBandwidth=" ).append( currentBandwidth ).append( ", planCode=" ).append( planCode )
                .append( ", allocatedVolumeQuota=" ).append( allocatedVolumeQuota ).append( ", usedVolumeQuota=" )
                .append( usedVolumeQuota ).append( ", remainVolumeQuota=" ).append( remainVolumeQuota )
                .append( ", primaryQuota=" ).append( primaryQuota ).append( ", primaryUsedQuota=" )
                .append( primaryUsedQuota ).append( ", primaryRemainQuota=" ).append( primaryRemainQuota )
                .append( ", secondaryQuota=" ).append( secondaryQuota ).append( ", secondaryUsedQuota=" )
                .append( secondaryUsedQuota ).append( ", secondaryRemainQuota=" ).append( secondaryRemainQuota )
                .append( ", addonPlanCode=" ).append( addonPlanCode ).append( ", billCycle=" ).append( billCycle )
                .append( "]" );
        return builder.toString();
    }
}
