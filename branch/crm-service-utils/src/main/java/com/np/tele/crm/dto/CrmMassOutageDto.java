package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;

import com.np.tele.crm.pojos.CrmMassOutageAreaPojo;
import com.np.tele.crm.pojos.CrmMassOutageCityPojo;
import com.np.tele.crm.pojos.CrmMassOutageLocalityPojo;
import com.np.tele.crm.pojos.CrmMassOutageMastersPojo;
import com.np.tele.crm.pojos.CrmMassOutagePartnerPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.CrmMassOutageSocietyPojo;
import com.np.tele.crm.pojos.CrmMassOutageStatePojo;

public class CrmMassOutageDto
    implements Serializable
{
    private String                         statusCode      = null;
    private String                         statusDesc      = null;
    private String                         clientIPAddress = null;
    private String                         serverIPAddress = null;
    private String                         userId          = null;
    private CrmMassOutagePojo              crmMassOutagePojo;
    private CrmMassOutagePartnerPojo       crmMassOutagePartnerPojo;
    private CrmMassOutageMastersPojo       crmMassOutageMastersPojo;
    private CrmMassOutageStatePojo         crmMassOutageStatePojo;
    private CrmMassOutageCityPojo          crmMassOutageCityPojo;
    private CrmMassOutageAreaPojo          crmMassOutageAreaPojo;
    private CrmMassOutageLocalityPojo      crmMassOutageLocalityPojo;
    private CrmMassOutageSocietyPojo       crmMassOutageSocietyPojo;
    private List<CrmMassOutagePojo>        massOutagePojos;
    private List<CrmMassOutageMastersPojo> outageMastersPojos;
    private String                         customerId;
    private List<String>                   masterList;
    private List<String>                   customerList;
    private List<CrmMassOutageSocietyPojo> crmMassOutageSocietyPojos;
    private List<Long>                     societyList;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    public CrmMassOutagePojo getCrmMassOutagePojo()
    {
        return crmMassOutagePojo;
    }

    public void setCrmMassOutagePojo( CrmMassOutagePojo crmMassOutagePojo )
    {
        this.crmMassOutagePojo = crmMassOutagePojo;
    }

    public CrmMassOutagePartnerPojo getCrmMassOutagePartnerPojo()
    {
        return crmMassOutagePartnerPojo;
    }

    public void setCrmMassOutagePartnerPojo( CrmMassOutagePartnerPojo crmMassOutagePartnerPojo )
    {
        this.crmMassOutagePartnerPojo = crmMassOutagePartnerPojo;
    }

    public CrmMassOutageMastersPojo getCrmMassOutageMastersPojo()
    {
        return crmMassOutageMastersPojo;
    }

    public void setCrmMassOutageMastersPojo( CrmMassOutageMastersPojo crmMassOutageMastersPojo )
    {
        this.crmMassOutageMastersPojo = crmMassOutageMastersPojo;
    }

    public CrmMassOutageStatePojo getCrmMassOutageStatePojo()
    {
        return crmMassOutageStatePojo;
    }

    public void setCrmMassOutageStatePojo( CrmMassOutageStatePojo crmMassOutageStatePojo )
    {
        this.crmMassOutageStatePojo = crmMassOutageStatePojo;
    }

    public CrmMassOutageCityPojo getCrmMassOutageCityPojo()
    {
        return crmMassOutageCityPojo;
    }

    public void setCrmMassOutageCityPojo( CrmMassOutageCityPojo crmMassOutageCityPojo )
    {
        this.crmMassOutageCityPojo = crmMassOutageCityPojo;
    }

    public CrmMassOutageAreaPojo getCrmMassOutageAreaPojo()
    {
        return crmMassOutageAreaPojo;
    }

    public void setCrmMassOutageAreaPojo( CrmMassOutageAreaPojo crmMassOutageAreaPojo )
    {
        this.crmMassOutageAreaPojo = crmMassOutageAreaPojo;
    }

    public CrmMassOutageLocalityPojo getCrmMassOutageLocalityPojo()
    {
        return crmMassOutageLocalityPojo;
    }

    public void setCrmMassOutageLocalityPojo( CrmMassOutageLocalityPojo crmMassOutageLocalityPojo )
    {
        this.crmMassOutageLocalityPojo = crmMassOutageLocalityPojo;
    }

    public CrmMassOutageSocietyPojo getCrmMassOutageSocietyPojo()
    {
        return crmMassOutageSocietyPojo;
    }

    public void setCrmMassOutageSocietyPojo( CrmMassOutageSocietyPojo crmMassOutageSocietyPojo )
    {
        this.crmMassOutageSocietyPojo = crmMassOutageSocietyPojo;
    }

    public List<CrmMassOutagePojo> getMassOutagePojos()
    {
        return massOutagePojos;
    }

    public void setMassOutagePojos( List<CrmMassOutagePojo> inMassOutagePojos )
    {
        massOutagePojos = inMassOutagePojos;
    }

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

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }

    public List<CrmMassOutageMastersPojo> getOutageMastersPojos()
    {
        return outageMastersPojos;
    }

    public void setOutageMastersPojos( List<CrmMassOutageMastersPojo> inOutageMastersPojos )
    {
        outageMastersPojos = inOutageMastersPojos;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public List<String> getMasterList()
    {
        return masterList;
    }

    public void setMasterList( List<String> masterList )
    {
        this.masterList = masterList;
    }

    public List<String> getCustomerList()
    {
        return customerList;
    }

    public void setCustomerList( List<String> customerList )
    {
        this.customerList = customerList;
    }

    public List<CrmMassOutageSocietyPojo> getCrmMassOutageSocietyPojos()
    {
        return crmMassOutageSocietyPojos;
    }

    public void setCrmMassOutageSocietyPojos( List<CrmMassOutageSocietyPojo> crmMassOutageSocietyPojos )
    {
        this.crmMassOutageSocietyPojos = crmMassOutageSocietyPojos;
    }

    public List<Long> getSocietyList()
    {
        return societyList;
    }

    public void setSocietyList( List<Long> societyList )
    {
        this.societyList = societyList;
    }
}
