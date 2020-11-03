package com.np.tele.crm.gis.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CRMGISService;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.StatePojo;

@AppMonitor
public class GISMasterImpl
    implements IGISMaster
{
    private static final Logger LOGGER           = Logger.getLogger( GISMasterImpl.class );
    private CRMGISService       gisServiceClient = null;

    public CRMGISService getGisServiceClient()
    {
        return gisServiceClient;
    }

    public void setGisServiceClient( CRMGISService inGisServiceClient )
    {
        gisServiceClient = inGisServiceClient;
    }

    @Override
    public GisDto getStateList( GISForm inGisForm )
    {
        GisDto gisDto = new GisDto();
        StatePojo statePojo = new StatePojo();
        statePojo.setStateName( inGisForm.getState() );
        statePojo.setStatus( inGisForm.getStateStatus() );
        gisDto.setStatePojo( statePojo );
        try
        {
            gisDto = getGisServiceClient().gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                         CRMParameter.STATE.getParameter(), gisDto );
            LOGGER.info( "++++No Of STATE Found:: BMNGR " + gisDto.getStatePojosList().size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Error occured calling client to service for search State:", ex );
        }
        return gisDto;
    }

    @Override
    public GisDto getCityList( GISForm inGisForm )
    {
        GisDto gisDto = new GisDto();
        CityPojo cityPojo = new CityPojo();
        if ( inGisForm.getStateId() > 0 )
        {
            cityPojo.setStateId( inGisForm.getStateId() );
        }
        cityPojo.setStatus( inGisForm.getCityStatus() );
        cityPojo.setCityName( inGisForm.getCity() );
        gisDto.setCityPojo( cityPojo );
        try
        {
            gisDto = getGisServiceClient().gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                         CRMParameter.CITY.getParameter(), gisDto );
            LOGGER.info( "++++No Of City Found :: in client" + gisDto.getCityPojosList().size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Error occured calling client to service for search City:", ex );
        }
        return gisDto;
    }

    @Override
    public GisDto getAreaList( GISForm inGisForm )
    {
        GisDto gisDto = new GisDto();
        AreaPojo areaPojo = new AreaPojo();
        if ( inGisForm.getCityId() == 0 )
        {
            long stateId = inGisForm.getStateIdForArea();
            if ( stateId > 0 )
            {
                List<CityPojo> cities = GISUtils.getActiveCities( IAppConstants.COUNTRY_INDIA, stateId );
                if ( cities.isEmpty() )
                {
                    gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                    return gisDto;
                }
                gisDto.getCityPojosList().addAll( cities );
            }
        }
        else if ( inGisForm.getCityId() > 0 )
        {
            areaPojo.setCityId( inGisForm.getCityId() );
        }
        areaPojo.setStatus( inGisForm.getAreaStatus() );
        areaPojo.setArea( inGisForm.getArea() );
        gisDto.setAreaPojo( areaPojo );
        try
        {
            gisDto = getGisServiceClient().gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                         CRMParameter.AREA.getParameter(), gisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Error occured calling client to service for search State:", ex );
        }
        return gisDto;
    }

    @Override
    public GisDto getCountries( GisDto inGisDto )
    {
        try
        {
            inGisDto = getGisServiceClient().gisOperation( ServiceParameter.SEARCH.getParameter(),
                                                           CRMParameter.COUNTRY.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while retriving States", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto createNewState( GISForm inGisForm, String inUserId )
    {
        LOGGER.info( "Size OF added State List" + inGisForm.getStateDataList().size() );
        GisDto inGisDto = new GisDto();
        List<StatePojo> updateStateList = new ArrayList<StatePojo>();
        for ( StatePojo statePojo : inGisForm.getStateDataList() )
        {
            if ( statePojo.isEditable() )
            {
                updateStateList.add( statePojo );
            }
        }
        inGisDto.getStatePojosList().addAll( updateStateList );
        try
        {
            inGisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                           CRMParameter.STATE.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while creating States", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto createNewCity( GISForm inGisForm, String inUserId )
    {
        LOGGER.info( "Size OF added City List" + inGisForm.getCityDataList().size() );
        GisDto inGisDto = new GisDto();
        List<CityPojo> updateCityList = new ArrayList<CityPojo>();
        for ( CityPojo cityPojo : inGisForm.getCityDataList() )
        {
            if ( cityPojo.isEditable() )
            {
                updateCityList.add( cityPojo );
            }
        }
        inGisDto.getCityPojosList().addAll( updateCityList );
        try
        {
            inGisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                           CRMParameter.CITY.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while creating City", ex );
        }
        return inGisDto;
    }

    @Override
    public GisDto createNewArea( GISForm inGisForm, String inUserId )
    {
        LOGGER.info( "Size OF added Area List" + inGisForm.getAreaDataList().size() );
        LOGGER.info( "City ID:" + inGisForm.getCityId() );
        GisDto inGisDto = new GisDto();
        List<AreaPojo> updateAreaList = new ArrayList<AreaPojo>();
        for ( AreaPojo areaPojo : inGisForm.getAreaDataList() )
        {
            if ( areaPojo.isEditable() )
            {
                updateAreaList.add( areaPojo );
            }
        }
        inGisDto.getAreaPojosList().addAll( updateAreaList );
        try
        {
            inGisDto = getGisServiceClient().gisOperation( ServiceParameter.CREATE.getParameter(),
                                                           CRMParameter.AREA.getParameter(), inGisDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while creating Area", ex );
        }
        return inGisDto;
    }
}
