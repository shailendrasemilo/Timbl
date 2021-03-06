package com.np.tele.crm.pojos;

// Generated 8 May, 2015 3:13:44 PM by Hibernate Tools 4.3.1
import java.util.Date;

/**
 * CrmMassOutageMastersPojo generated by hbm2java
 */
public class CrmMassOutageMastersPojo
    implements java.io.Serializable
{
    private static final long serialVersionUID = 8391577359036494571L;
    private long              recordId;
    private long              outageId;
    private long              outageNpId;
    private long              masterId;
    private String            masterName;
    private String            nasPortId;
    private String            poolName;
    private String            resolvedBy;
    private Date              resolvedOn;
    private boolean           enabled;
    private boolean           startPoint;
    private String            outageActivity;

    public CrmMassOutageMastersPojo()
    {
    }

    public long getRecordId()
    {
        return recordId;
    }

    public void setRecordId( long recordId )
    {
        this.recordId = recordId;
    }

    public long getOutageId()
    {
        return outageId;
    }

    public void setOutageId( long outageId )
    {
        this.outageId = outageId;
    }

    public long getOutageNpId()
    {
        return outageNpId;
    }

    public void setOutageNpId( long outageNpId )
    {
        this.outageNpId = outageNpId;
    }

    public long getMasterId()
    {
        return masterId;
    }

    public void setMasterId( long masterId )
    {
        this.masterId = masterId;
    }

    public String getMasterName()
    {
        return this.masterName;
    }

    public void setMasterName( String masterName )
    {
        this.masterName = masterName;
    }

    public String getNasPortId()
    {
        return this.nasPortId;
    }

    public void setNasPortId( String nasPortId )
    {
        this.nasPortId = nasPortId;
    }

    public String getPoolName()
    {
        return this.poolName;
    }

    public void setPoolName( String poolName )
    {
        this.poolName = poolName;
    }

    public String getResolvedBy()
    {
        return this.resolvedBy;
    }

    public void setResolvedBy( String resolvedBy )
    {
        this.resolvedBy = resolvedBy;
    }

    public Date getResolvedOn()
    {
        return this.resolvedOn;
    }

    public void setResolvedOn( Date resolvedOn )
    {
        this.resolvedOn = resolvedOn;
    }

    public boolean isEnabled()
    {
        return enabled;
    }

    public void setEnabled( boolean enabled )
    {
        this.enabled = enabled;
    }

    public boolean isStartPoint()
    {
        return startPoint;
    }

    public void setStartPoint( boolean startPoint )
    {
        this.startPoint = startPoint;
    }

    public String getOutageActivity()
    {
        return outageActivity;
    }

    public void setOutageActivity( String outageActivity )
    {
        this.outageActivity = outageActivity;
    }
}
