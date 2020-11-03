package com.np.tele.crm.gis.dao;

import java.util.List;

import com.np.tele.crm.dto.GISDto;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CountryPojo;
import com.np.tele.crm.pojos.LocalityPojo;
import com.np.tele.crm.pojos.SocietyNetworkPartnerPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;

public interface IGISOperationDao
{
    public GISDto countryOperation( CountryPojo countryPojo );

    public GISDto stateOperation( List<StatePojo> inList );

    public GISDto cityOperation( List<CityPojo> inList );

    public GISDto areaOperation( List<AreaPojo> inList );

    public GISDto getCountryList( CountryPojo countryPojo );

    public GISDto getStateList( StatePojo statePojo );

    public GISDto getCityList( CityPojo cityPojo );

    public GISDto getAreaList( GISDto inGisDto );

    public GISDto getSocietyList( GISDto inGisDto );

    public GISDto createSociety( GISDto inGisDto );

    public GISDto updateSocietyStatus( SocietyPojo inSocietyPojo );

    public GISDto updateSocietyNPStatus( SocietyNetworkPartnerPojo inSocietyNetworkPartnerPojo );

    public GISDto getGISIdByName( GISDto inGisDto,String str);
}
