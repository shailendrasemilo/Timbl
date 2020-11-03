package com.np.tele.crm.gis.bm;

import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.service.client.GisDto;

public interface IGISMaster
{
    GisDto getStateList( GISForm inGisForm );

    GisDto getCityList( GISForm inGisForm );

    GisDto getAreaList( GISForm inGisForm );

    GisDto getCountries( GisDto inGisDto );

    GisDto createNewState( GISForm inGisForm, String inUserId );

    GisDto createNewCity( GISForm inGisForm, String inUserId );

    GisDto createNewArea( GISForm inGisForm, String inUserId );
}
