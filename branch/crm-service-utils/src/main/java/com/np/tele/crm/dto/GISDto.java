package com.np.tele.crm.dto;

import java.util.List;

import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CountryPojo;
import com.np.tele.crm.pojos.LocalityPojo;
import com.np.tele.crm.pojos.SocietyNetworkPartnerPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;

public class GISDto
{
    private String                          statusCode      = null;
    private String                          statusDesc      = null;
    private String                          clientIPAddress = null;
    private String                          serverIPAddress = null;
    private CountryPojo                     countryPojo;
    private List<CountryPojo>               countryPojosList;
    private StatePojo                       statePojo;
    private List<StatePojo>                 statePojosList;
    private CityPojo                        cityPojo;
    private List<CityPojo>                  cityPojosList;
    private AreaPojo                        areaPojo;
    private List<AreaPojo>                  areaPojosList;
    private SocietyPojo                     societyPojo;
    private List<SocietyPojo>               societyPojosList;
    private LocalityPojo                    localityPojo;
    private List<LocalityPojo>              localityPojosList;
    private SocietyNetworkPartnerPojo       soNetworkPartnerPojo;
    private List<SocietyNetworkPartnerPojo> soNetworkPartnerPojoList;
    private long                            partnerId;
    private String                          productName     = null;
    private String                          operationType   = null;
    private List<String>                    userAssociatedServices;
    private List<String>                    userAssociatedPartners;
    private String                          stateName;
    private String                          cityName;
    private String                          areaName;
    private String                          societyName;

    public List<String> getUserAssociatedServices()
    {
        return userAssociatedServices;
    }

    public void setUserAssociatedServices( List<String> inUserAssociatedServices )
    {
        userAssociatedServices = inUserAssociatedServices;
    }

    public List<String> getUserAssociatedPartners()
    {
        return userAssociatedPartners;
    }

    public void setUserAssociatedPartners( List<String> inUserAssociatedPartners )
    {
        userAssociatedPartners = inUserAssociatedPartners;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long partnerId )
    {
        this.partnerId = partnerId;
    }

    public String getProductName()
    {
        return productName;
    }

    public void setProductName( String productName )
    {
        this.productName = productName;
    }

    public LocalityPojo getLocalityPojo()
    {
        return localityPojo;
    }

    public void setLocalityPojo( LocalityPojo localityPojo )
    {
        this.localityPojo = localityPojo;
    }

    public List<LocalityPojo> getLocalityPojosList()
    {
        return localityPojosList;
    }

    public void setLocalityPojosList( List<LocalityPojo> localityPojosList )
    {
        this.localityPojosList = localityPojosList;
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

    public CountryPojo getCountryPojo()
    {
        return countryPojo;
    }

    public void setCountryPojo( CountryPojo inCountryPojo )
    {
        countryPojo = inCountryPojo;
    }

    public List<CountryPojo> getCountryPojosList()
    {
        return countryPojosList;
    }

    public void setCountryPojosList( List<CountryPojo> inCountryPojosList )
    {
        countryPojosList = inCountryPojosList;
    }

    public StatePojo getStatePojo()
    {
        return statePojo;
    }

    public void setStatePojo( StatePojo inStatePojo )
    {
        statePojo = inStatePojo;
    }

    public List<StatePojo> getStatePojosList()
    {
        return statePojosList;
    }

    public void setStatePojosList( List<StatePojo> inStatePojosList )
    {
        statePojosList = inStatePojosList;
    }

    public CityPojo getCityPojo()
    {
        return cityPojo;
    }

    public void setCityPojo( CityPojo inCityPojo )
    {
        cityPojo = inCityPojo;
    }

    public List<CityPojo> getCityPojosList()
    {
        return cityPojosList;
    }

    public void setCityPojosList( List<CityPojo> inCityPojosList )
    {
        cityPojosList = inCityPojosList;
    }

    public AreaPojo getAreaPojo()
    {
        return areaPojo;
    }

    public void setAreaPojo( AreaPojo inAreaPojo )
    {
        areaPojo = inAreaPojo;
    }

    public List<AreaPojo> getAreaPojosList()
    {
        return areaPojosList;
    }

    public void setAreaPojosList( List<AreaPojo> inAreaPojosList )
    {
        areaPojosList = inAreaPojosList;
    }

    public SocietyPojo getSocietyPojo()
    {
        return societyPojo;
    }

    public void setSocietyPojo( SocietyPojo inSocietyPojo )
    {
        societyPojo = inSocietyPojo;
    }

    public List<SocietyPojo> getSocietyPojosList()
    {
        return societyPojosList;
    }

    public void setSocietyPojosList( List<SocietyPojo> inSocietyPojosList )
    {
        societyPojosList = inSocietyPojosList;
    }

    public SocietyNetworkPartnerPojo getSoNetworkPartnerPojo()
    {
        return soNetworkPartnerPojo;
    }

    public void setSoNetworkPartnerPojo( SocietyNetworkPartnerPojo inSoNetworkPartnerPojo )
    {
        soNetworkPartnerPojo = inSoNetworkPartnerPojo;
    }

    public List<SocietyNetworkPartnerPojo> getSoNetworkPartnerPojoList()
    {
        return soNetworkPartnerPojoList;
    }

    public void setSoNetworkPartnerPojoList( List<SocietyNetworkPartnerPojo> inSoNetworkPartnerPojoList )
    {
        soNetworkPartnerPojoList = inSoNetworkPartnerPojoList;
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

    public String getOperationType()
    {
        return operationType;
    }

    public void setOperationType( String operationType )
    {
        this.operationType = operationType;
    }

  
    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String stateName )
    {
        this.stateName = stateName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String cityName )
    {
        this.cityName = cityName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String areaName )
    {
        this.areaName = areaName;
    }

    public String getSocietyName()
    {
        return societyName;
    }

    public void setSocietyName( String societyName )
    {
        this.societyName = societyName;
    }
}
