package com.np.tele.crm.pojos;

import java.util.Date;

/**
 * CrmNetworkConfigurations generated by hbm2java
 */
public class CrmNetworkConfigurationsPojo
    implements java.io.Serializable
{
    private long    recordId;
    private long    customerRecordId;
    private String  serviceModel;
    private long    vlanId;
    private String  oltNoteId;
    private byte    oltPort;
    private byte    oltSubPort;
    private byte    oltSlot;
    private String  ontOnuPort;
    private String  serviceType;
    private boolean ontRgMduDone;
    private String  radiusCustomerId;
    private String  option82;
    private String  createdBy;
    private Date    createdTime;
    private String  lastModifiedBy;
    private Date    lastModifiedTime;
    private String  firstCpeMacId;
    private String  currentCpeMacId;
    private String  firstSlaveMacId;
    private String  currentSlaveMacId;
    private boolean spOntRgMduDone;
    private String  secondaryMACAddr;
    private String  macBind;

    public String getSecondaryMACAddr()
    {
        return secondaryMACAddr;
    }

    public void setSecondaryMACAddr( String secondaryMACAddr )
    {
        this.secondaryMACAddr = secondaryMACAddr;
    }

    public CrmNetworkConfigurationsPojo()
    {
    }

    public CrmNetworkConfigurationsPojo( Long recordId, long customerRecordId, String serviceModel, String createdBy )
    {
        this.recordId = recordId;
        this.customerRecordId = customerRecordId;
        this.serviceModel = serviceModel;
        this.createdBy = createdBy;
    }

    public CrmNetworkConfigurationsPojo( Long recordId,
                                         long customerRecordId,
                                         String serviceModel,
                                         Long vlanId,
                                         String oltNoteId,
                                         byte oltPort,
                                         byte oltSubPort,
                                         byte oltSlot,
                                         String ontOnuPort,
                                         String serviceType,
                                         boolean ontRgMduDone,
                                         String radiusCustomerId,
                                         String option82,
                                         String createdBy,
                                         Date createdTime,
                                         String lastModifiedBy,
                                         Date lastModifiedTime )
    {
        this.recordId = recordId;
        this.customerRecordId = customerRecordId;
        this.serviceModel = serviceModel;
        this.vlanId = vlanId;
        this.oltNoteId = oltNoteId;
        this.oltPort = oltPort;
        this.oltSubPort = oltSubPort;
        this.oltSlot = oltSlot;
        this.ontOnuPort = ontOnuPort;
        this.serviceType = serviceType;
        this.ontRgMduDone = ontRgMduDone;
        this.radiusCustomerId = radiusCustomerId;
        this.option82 = option82;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public long getRecordId()
    {
        return recordId;
    }

    public void setRecordId( long recordId )
    {
        this.recordId = recordId;
    }

    public long getCustomerRecordId()
    {
        return customerRecordId;
    }

    public void setCustomerRecordId( long customerRecordId )
    {
        this.customerRecordId = customerRecordId;
    }

    public long getVlanId()
    {
        return vlanId;
    }

    public void setVlanId( long vlanId )
    {
        this.vlanId = vlanId;
    }

    public String getServiceModel()
    {
        return serviceModel;
    }

    public void setServiceModel( String inServiceModel )
    {
        serviceModel = inServiceModel;
    }

    public String getOltNoteId()
    {
        return oltNoteId;
    }

    public void setOltNoteId( String inOltNoteId )
    {
        oltNoteId = inOltNoteId;
    }

    public byte getOltPort()
    {
        return oltPort;
    }

    public void setOltPort( byte inOltPort )
    {
        oltPort = inOltPort;
    }

    public byte getOltSubPort()
    {
        return oltSubPort;
    }

    public void setOltSubPort( byte inOltSubPort )
    {
        oltSubPort = inOltSubPort;
    }

    public byte getOltSlot()
    {
        return oltSlot;
    }

    public void setOltSlot( byte inOltSlot )
    {
        oltSlot = inOltSlot;
    }

    public String getOntOnuPort()
    {
        return ontOnuPort;
    }

    public void setOntOnuPort( String inOntOnuPort )
    {
        ontOnuPort = inOntOnuPort;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType( String inServiceType )
    {
        serviceType = inServiceType;
    }

    public boolean isOntRgMduDone()
    {
        return ontRgMduDone;
    }

    public void setOntRgMduDone( boolean inOntRgMduDone )
    {
        ontRgMduDone = inOntRgMduDone;
    }

    public String getRadiusCustomerId()
    {
        return radiusCustomerId;
    }

    public void setRadiusCustomerId( String inRadiusCustomerId )
    {
        radiusCustomerId = inRadiusCustomerId;
    }

    public String getOption82()
    {
        return option82;
    }

    public void setOption82( String inOption82 )
    {
        option82 = inOption82;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy( String inCreatedBy )
    {
        createdBy = inCreatedBy;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime( Date inCreatedTime )
    {
        createdTime = inCreatedTime;
    }

    public String getLastModifiedBy()
    {
        return lastModifiedBy;
    }

    public void setLastModifiedBy( String inLastModifiedBy )
    {
        lastModifiedBy = inLastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    public void setLastModifiedTime( Date inLastModifiedTime )
    {
        lastModifiedTime = inLastModifiedTime;
    }

    public String getFirstCpeMacId()
    {
        return firstCpeMacId;
    }

    public void setFirstCpeMacId( String firstCpeMacId )
    {
        this.firstCpeMacId = firstCpeMacId;
    }

    public String getCurrentCpeMacId()
    {
        return currentCpeMacId;
    }

    public void setCurrentCpeMacId( String currentCpeMacId )
    {
        this.currentCpeMacId = currentCpeMacId;
    }

    public String getFirstSlaveMacId()
    {
        return firstSlaveMacId;
    }

    public void setFirstSlaveMacId( String firstSlaveMacId )
    {
        this.firstSlaveMacId = firstSlaveMacId;
    }

    public String getCurrentSlaveMacId()
    {
        return currentSlaveMacId;
    }

    public void setCurrentSlaveMacId( String currentSlaveMacId )
    {
        this.currentSlaveMacId = currentSlaveMacId;
    }

    public boolean isSpOntRgMduDone()
    {
        return spOntRgMduDone;
    }

    public void setSpOntRgMduDone( boolean spOntRgMduDone )
    {
        this.spOntRgMduDone = spOntRgMduDone;
    }

    public String getMacBind()
    {
        return macBind;
    }

    public void setMacBind( String inMacBind )
    {
        macBind = inMacBind;
    }
}
