package com.np.lms.rest.dto;

import java.io.Serializable;
import java.util.List;

import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.LocalityPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;

public class MasterResponsedto
    implements Serializable
{
    private List<StatePojo>        statePojosList;
    private List<CityPojo>         cityPojosList;
    private List<AreaPojo>         areaPojosList;
    private List<LocalityPojo>     localityPojosList;
    private List<SocietyPojo>      societyPojosList;
    private String                 statusCode;
    private String                 statusDesc;
    private List<LMSPojo>          inboxLeadPojos;
    private List<LMSPojo>          groupInboxLeadPojos;
    private List<PartnerPojo>      partnerPojoList;
    private List<CrmRcaReasonPojo> crmRcaReasonsList;

    public List<PartnerPojo> getPartnerPojoList()
    {
        return partnerPojoList;
    }

    public void setPartnerPojoList( List<PartnerPojo> inPartnerPojoList )
    {
        partnerPojoList = inPartnerPojoList;
    }

    public List<StatePojo> getStatePojosList()
    {
        return statePojosList;
    }

    public void setStatePojosList( List<StatePojo> inStatePojosList )
    {
        statePojosList = inStatePojosList;
    }

    public List<CityPojo> getCityPojosList()
    {
        return cityPojosList;
    }

    public void setCityPojosList( List<CityPojo> inCityPojosList )
    {
        cityPojosList = inCityPojosList;
    }

    public List<AreaPojo> getAreaPojosList()
    {
        return areaPojosList;
    }

    public void setAreaPojosList( List<AreaPojo> inAreaPojosList )
    {
        areaPojosList = inAreaPojosList;
    }

    public List<LocalityPojo> getLocalityPojosList()
    {
        return localityPojosList;
    }

    public void setLocalityPojosList( List<LocalityPojo> inLocalityPojosList )
    {
        localityPojosList = inLocalityPojosList;
    }

    public List<SocietyPojo> getSocietyPojosList()
    {
        return societyPojosList;
    }

    public void setSocietyPojosList( List<SocietyPojo> inSocietyPojosList )
    {
        societyPojosList = inSocietyPojosList;
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

    public List<LMSPojo> getInboxLeadPojos()
    {
        return inboxLeadPojos;
    }

    public void setInboxLeadPojos( List<LMSPojo> inInboxLeadPojos )
    {
        inboxLeadPojos = inInboxLeadPojos;
    }

    public List<LMSPojo> getGroupInboxLeadPojos()
    {
        return groupInboxLeadPojos;
    }

    public void setGroupInboxLeadPojos( List<LMSPojo> inGroupInboxLeadPojos )
    {
        groupInboxLeadPojos = inGroupInboxLeadPojos;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasonsList()
    {
        return crmRcaReasonsList;
    }

    public void setCrmRcaReasonsList( List<CrmRcaReasonPojo> inCrmRcaReasonsList )
    {
        crmRcaReasonsList = inCrmRcaReasonsList;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "MasterResponsedto [statePojosList=" ).append( statePojosList ).append( ", cityPojosList=" )
                .append( cityPojosList ).append( ", areaPojosList=" ).append( areaPojosList )
                .append( ", localityPojosList=" ).append( localityPojosList ).append( ", societyPojosList=" )
                .append( societyPojosList ).append( ", statusCode=" ).append( statusCode ).append( ", statusDesc=" )
                .append( statusDesc ).append( ", inboxLeadPojos=" ).append( inboxLeadPojos )
                .append( ", groupInboxLeadPojos=" ).append( groupInboxLeadPojos ).append( ", partnerPojoList=" )
                .append( partnerPojoList ).append( ", crmRcaReasonsList=" ).append( crmRcaReasonsList ).append( "]" );
        return builder.toString();
    }

    
}
