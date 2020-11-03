package com.np.tele.crm.gis.bm;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.LmsPojo;

public interface IGISManager
{
    GisDto createGisSocietyData( ActionForm inForm, CrmuserDetailsDto inUserDto );

    GisDto createGisSocietyData( GISForm inForm );

    GisDto getCities( final GisDto inGisDto );

    GisDto getStates( final GisDto inGisDto );

    GisDto getCountries( GisDto inGisDto );

    GisDto searchSociety( GISForm inGisForm );

    List<String> processGISExcel( GISForm inGisForm, String inUser, String inFilePath );

    GisDto changeSocietyStatus( GISForm inForm, String modifiedBy );

    GisDto getSocietiesByArea( GisDto gisDto );

    GisDto changeSocietyNPStatus( GISForm inGISForm, String modifiedBy );

    LmsPojo getSocietyByPinCode( Integer pincode );
}
