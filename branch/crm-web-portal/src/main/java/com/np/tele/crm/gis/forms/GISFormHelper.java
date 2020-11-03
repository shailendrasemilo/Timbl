package com.np.tele.crm.gis.forms;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.StringUtils;

public class GISFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( GISFormHelper.class );

    public static void resetGisFormList( String inMethodName, GISForm inGisForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_ADD_GIS_STATE_ROW, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_ADD_GIS_STATE, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getStateDataList() ) )
            {
                for ( StatePojo statePojo : inGisForm.getStateDataList() )
                {
                    statePojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_ADD_GIS_CITY_ROW, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_ADD_GIS_CITY, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getCityDataList() ) )
            {
                for ( CityPojo cityPojo : inGisForm.getCityDataList() )
                {
                    cityPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_ADD_GIS_AREA_ROW, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_ADD_GIS_AREA, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getAreaDataList() ) )
            {
                for ( AreaPojo areaPojo : inGisForm.getAreaDataList() )
                {
                    areaPojo.setEditable( false );
                }
            }
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_MASTER_GIS_PAGE, inMethodName ) )
        {
            inGisForm.setStatePojoList( new LinkedList<StatePojo>() );
            inGisForm.setCityList( new LinkedList<CityPojo>() );
            inGisForm.setAreaList( new LinkedList<AreaPojo>() );
            inGisForm.setStateDataList( null );
            inGisForm.setCityDataList( null );
            inGisForm.setAreaDataList( null );
            inGisForm.setState( null );
            inGisForm.setCity( null );
            inGisForm.setArea( null );
            inGisForm.setLocality( null );
            inGisForm.setStateStatus( IAppConstants.EMPTY_STRING );
            inGisForm.setCityStatus( IAppConstants.EMPTY_STRING );
            inGisForm.setAreaStatus( IAppConstants.EMPTY_STRING );
            inGisForm.setLocalityStatus( IAppConstants.EMPTY_STRING );
            inGisForm.setStateId( 0l );
            inGisForm.setCityId( 0l );
            inGisForm.setAreaId( 0l );
            inGisForm.setStateIdForArea( 0l );
            inGisForm.setStateIdForLoc( 0l );
            inGisForm.setCityIdForLoc( 0l );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CREATE_SOCIETY_PAGE, inMethodName ) )
        {
            resetSociety( inGisForm );
            inGisForm.setState( null );
            inGisForm.setAreaList( null );
            inGisForm.setCityList( null );
            inGisForm.setAreaList( null );
            if ( StringUtils.isValidObj( inGisForm.getPartnerPojoList() ) )
            {
                inGisForm.getPartnerPojoList().clear();
            }
            inGisForm.setPartnerName( null );
            inGisForm.setPartnerId( 0L );
            inGisForm.setProductArray( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SEARCH_SOCIETY_PAGE, inMethodName )
                || ( StringUtils.equals( IAppConstants.METHOD_SEARCH_SOCIETY, inMethodName ) ) )
        {
            inGisForm.setProduct( IAppConstants.EMPTY_STRING );
            inGisForm.setPartnerId( 0L );
            inGisForm.setStateId( 0L );
            inGisForm.setCityId( 0L );
            inGisForm.setAreaId( 0L );
            inGisForm.setCityList( new LinkedList<CityPojo>() );
            inGisForm.setAreaList( new LinkedList<AreaPojo>() );
            inGisForm.setSearchSocietyList( new LinkedList<SocietyPojo>() );
            inGisForm.setOldSocietyPojo( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_GIS_SEARCH_STATE, inMethodName ) )
        {
            inGisForm.setState( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_GIS_SEARCH_CITY, inMethodName ) )
        {
            inGisForm.setStateId( 0l );
            inGisForm.setCity( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_GIS_SEARCH_AREA, inMethodName ) )
        {
            inGisForm.setStateIdForArea( 0l );
            inGisForm.setCityId( 0l );
            inGisForm.setArea( null );
        }
        else if ( StringUtils.equals( IAppConstants.UPLOAD_GIS_PAGE, inMethodName ) )
        {
            inGisForm.setPartnerId( 0L );
            inGisForm.setProduct( null );
            inGisForm.setPartnerList( null );
            inGisForm.setProductArray( null );
            if ( StringUtils.isValidObj( inGisForm.getPartnerPojoList() ) )
            {
                inGisForm.getPartnerPojoList().clear();
            }
            inGisForm.setPartnerName( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_SOCIETY_PAGE, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_VIEW_SOCIETY_PAGE, inMethodName ) )
        {
            inGisForm.setOldSocietyPojo( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_COPY_SOCIETY_PAGE, inMethodName ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getPartnerPojoList() ) )
            {
                inGisForm.getPartnerPojoList().clear();
            }
            inGisForm.setPartnerName( null );
        }
    }

    public static void resetSociety( GISForm inGisForm )
    {
        inGisForm.setStateId( 0L );
        if ( StringUtils.isValidObj( inGisForm.getSocietyPojo() ) )
        {
            inGisForm.setProduct( "" );
            try
            {
                SocietyPojo pojo = new SocietyPojo();
                org.apache.commons.beanutils.PropertyUtils.copyProperties( inGisForm.getSocietyPojo(), pojo );
            }
            catch ( IllegalAccessException e )
            {
                LOGGER.info( "IllegalAccessException ", e );
            }
            catch ( InvocationTargetException e )
            {
                LOGGER.info( "InvocationTargetException ", e );
            }
            catch ( NoSuchMethodException e )
            {
                LOGGER.info( "NoSuchMethodException ", e );
            }
        }
    }

    public static void validateGisForm( String inMethod, ActionErrors inActionError, GISForm inGisForm )
    {
        if ( StringUtils.equals( IAppConstants.METHOD_GIS_SEARCH_STATE, inMethod ) )
        {
            inGisForm.setStateDataList( new LinkedList<StatePojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_GIS_SEARCH_CITY, inMethod ) )
        {
            inGisForm.setCityDataList( new LinkedList<CityPojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_GIS_SEARCH_AREA, inMethod ) )
        {
            inGisForm.setAreaDataList( new LinkedList<AreaPojo>() );
        }
        else if ( inMethod.equals( IAppConstants.METHOD_ADD_GIS_STATE ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getStateDataList() ) )
            {
                List<StatePojo> states = GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA );
                List<StatePojo> unusedStates = new ArrayList<StatePojo>();
                for ( StatePojo statePojo : inGisForm.getStateDataList() )
                {
                    LOGGER.info( "In Validate GIS" );
                    if ( statePojo.isEditable() )
                    {
                        if ( StringUtils.isEmpty( statePojo.getStateName() ) )
                        {
                            LOGGER.info( "In Validate getStateName" );
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_STATE_REQUIRED ) );
                            break;
                        }
                        else if ( !StringUtils.isEmpty( statePojo.getStateName() )
                                && ( !StringUtils
                                        .compareRegularExp( CRMRegex.PATTERN_CAPS_SPACE_TEXT_3_28.getPattern(),
                                                            statePojo.getStateName() ) ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( CRMRegex.PATTERN_CAPS_SPACE_TEXT_3_28.getErrorKey(),
                                                                  statePojo.getStateName() ) );
                            break;
                        }
                        else if ( statePojo.getStateId() == 0 )
                        {
                            for ( StatePojo tmpStatePojo : states )
                            {
                                if ( StringUtils.equalsIgnoreCase( tmpStatePojo.getStateName(),
                                                                   statePojo.getStateName() ) )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.SAME_STATE_ALREADY_EXISTS,
                                                                     statePojo.getStateName() ) );
                                    break;
                                }
                            }
                        }
                    }
                    else if ( statePojo.getStateId() == 0 )
                    {
                        unusedStates.add( statePojo );
                    }
                }
                inGisForm.getStateDataList().removeAll( unusedStates );
            }
        }
        else if ( inMethod.equals( IAppConstants.METHOD_ADD_GIS_CITY_ROW ) )
        {
            String action = inGisForm.getHiddenGisOperation();
            if ( !StringUtils.equals( "E", action ) )
            {
                if ( inGisForm.getStateId() == 0 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_STATE_NOT_SELECTED ) );
                    if ( StringUtils.isValidObj( inGisForm.getCityDataList() ) )
                    {
                        for ( CityPojo cityPojo : inGisForm.getCityDataList() )
                        {
                            if ( cityPojo.getCityId() > 0 )
                            {
                                cityPojo.setEditable( false );
                            }
                        }
                    }
                }
            }
            inGisForm.setManageGis( IAppConstants.CITY_GIS );
        }
        else if ( inMethod.equals( IAppConstants.METHOD_ADD_GIS_CITY ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getCityDataList() ) )
            {
                List<CityPojo> cities = null;
                if ( inGisForm.getStateId() > 0 )
                {
                    cities = GISUtils.getAllCities( IAppConstants.COUNTRY_INDIA, inGisForm.getStateId() );
                }
                List<CityPojo> unusedCities = new ArrayList<CityPojo>();
                for ( CityPojo cityPojo : inGisForm.getCityDataList() )
                {
                    if ( cityPojo.isEditable() )
                    {
                        LOGGER.info( " In Validate CityId in City Block" + cityPojo.getCityId() );
                        LOGGER.info( "In Validate StateId City Block" + cityPojo.getStateId() );
                        LOGGER.info( "In Validate STD code" + cityPojo.getStdCode() );
                        if ( StringUtils.isEmpty( cityPojo.getCityName() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_CITY_REQUIRED ) );
                            break;
                        }
                        else if ( !StringUtils.isEmpty( cityPojo.getCityName() )
                                && !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_SPACE_TEXT_3_28.getPattern(),
                                                                   cityPojo.getCityName() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( CRMRegex.PATTERN_CAPS_SPACE_TEXT_3_28.getErrorKey(),
                                                                  cityPojo.getCityName() ) );
                            break;
                        }
                        else if ( cityPojo.getStdCode() == 0 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_STD_CODE_REQUIRED, cityPojo
                                                       .getCityName() ) );
                            break;
                        }
                        else if ( cityPojo.getCityId() == 0 )
                        {
                            if ( cityPojo.getStateId() == 0 )
                            {
                                LOGGER.info( "In Validate StateId City Block" + cityPojo.getStateId() );
                                if ( inGisForm.getStateId() == 0 )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.ERROR_STATE_NOT_SELECTED ) );
                                    break;
                                }
                                else if ( inGisForm.getStateId() > 0 )
                                {
                                    cityPojo.setStateId( inGisForm.getStateId() );
                                }
                            }
                            if ( cityPojo.getStateId() > 0 && cityPojo.getStateId() != inGisForm.getStateId() )
                            {
                                List<CityPojo> tmpCities = GISUtils.getAllCities( IAppConstants.COUNTRY_INDIA,
                                                                                  cityPojo.getStateId() );
                                if ( StringUtils.isValidObj( tmpCities ) )
                                {
                                    for ( CityPojo tmpCityPojo : tmpCities )
                                    {
                                        if ( StringUtils.equalsIgnoreCase( tmpCityPojo.getCityName(),
                                                                           cityPojo.getCityName() ) )
                                        {
                                            inActionError
                                                    .add( IAppConstants.APP_ERROR,
                                                          new ActionMessage( IPropertiesConstant.SAME_CITY_ALREADY_EXISTS,
                                                                             cityPojo.getCityName() ) );
                                            break;
                                        }
                                    }
                                }
                            }
                            else if ( cityPojo.getStateId() > 0 )
                            {
                                if ( StringUtils.isValidObj( cities ) )
                                {
                                    for ( CityPojo tmpCityPojo : cities )
                                    {
                                        if ( StringUtils.equalsIgnoreCase( tmpCityPojo.getCityName(),
                                                                           cityPojo.getCityName() ) )
                                        {
                                            inActionError
                                                    .add( IAppConstants.APP_ERROR,
                                                          new ActionMessage( IPropertiesConstant.SAME_CITY_ALREADY_EXISTS,
                                                                             cityPojo.getCityName() ) );
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if ( cityPojo.getCityId() == 0 )
                    {
                        unusedCities.add( cityPojo );
                    }
                }
                inGisForm.getCityDataList().removeAll( unusedCities );
            }
        }
        else if ( inMethod.equals( IAppConstants.METHOD_ADD_GIS_AREA_ROW ) )
        {
            String action = inGisForm.getHiddenGisOperation();
            if ( !StringUtils.equals( "E", action ) )
            {
                if ( inGisForm.getStateIdForArea() == 0 || inGisForm.getCityId() == 0 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_CITY_NOT_SELECTED ) );
                    if ( inGisForm.getStateIdForArea() > 0 )
                    {
                        inGisForm.setCityList( GISUtils.getCities( inGisForm.getStatePojoList(),
                                                                   inGisForm.getStateIdForArea() ) );
                    }
                    if ( StringUtils.isValidObj( inGisForm.getAreaDataList() ) )
                    {
                        for ( AreaPojo areaPojo : inGisForm.getAreaDataList() )
                        {
                            if ( areaPojo.getAreaId() > 0 )
                            {
                                areaPojo.setEditable( false );
                            }
                        }
                    }
                }
            }
            inGisForm.setManageGis( IAppConstants.AREA_GIS );
        }
        else if ( inMethod.equals( IAppConstants.METHOD_ADD_GIS_AREA ) )
        {
            if ( StringUtils.isValidObj( inGisForm.getAreaDataList() ) )
            {
                List<AreaPojo> areaPojos = null;
                if ( inGisForm.getCityId() > 0 )
                {
                    areaPojos = GISUtils.getAllAreas( null, null, inGisForm.getCityId() );
                }
                List<AreaPojo> unusedAreas = new ArrayList<AreaPojo>();
                for ( AreaPojo areaPojo : inGisForm.getAreaDataList() )
                {
                    if ( areaPojo.isEditable() )
                    {
                        if ( StringUtils.isEmpty( areaPojo.getArea() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_AREA_REQUIRED ) );
                            break;
                        }
                        else if ( !StringUtils.isEmpty( areaPojo.getArea() )
                                && !StringUtils.compareRegularExp( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_DASH_3_30
                                        .getPattern(), areaPojo.getArea() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( CRMRegex.PATTERN_ALPHA_NUMERIC_SPACE_DASH_3_30
                                                       .getErrorKey(), areaPojo.getArea() ) );
                            break;
                        }
                        else if ( areaPojo.getAreaId() == 0 )
                        {
                            if ( areaPojo.getCityId() == 0 )
                            {
                                if ( inGisForm.getCityId() == 0 )
                                {
                                    inActionError
                                            .add( IAppConstants.APP_ERROR,
                                                  new ActionMessage( IPropertiesConstant.ERROR_CITY_NOT_SELECTED ) );
                                    break;
                                }
                                else if ( inGisForm.getCityId() > 0 )
                                {
                                    areaPojo.setCityId( inGisForm.getCityId() );
                                }
                            }
                            if ( areaPojo.getCityId() > 0 && areaPojo.getCityId() != inGisForm.getCityId() )
                            {
                                List<AreaPojo> tmpAreas = GISUtils.getAllAreas( null, null, areaPojo.getCityId() );
                                if ( StringUtils.isValidObj( tmpAreas ) )
                                {
                                    for ( AreaPojo tmpAreaPojo : tmpAreas )
                                    {
                                        if ( StringUtils.equalsIgnoreCase( tmpAreaPojo.getArea(), areaPojo.getArea() ) )
                                        {
                                            inActionError
                                                    .add( IAppConstants.APP_ERROR,
                                                          new ActionMessage( IPropertiesConstant.SAME_AREA_ALREADY_EXISTS,
                                                                             areaPojo.getArea() ) );
                                            break;
                                        }
                                    }
                                }
                            }
                            else if ( areaPojo.getCityId() > 0 )
                            {
                                if ( StringUtils.isValidObj( areaPojos ) )
                                {
                                    for ( AreaPojo tmpAreaPojo : areaPojos )
                                    {
                                        if ( StringUtils.equalsIgnoreCase( tmpAreaPojo.getArea(), areaPojo.getArea() ) )
                                        {
                                            inActionError
                                                    .add( IAppConstants.APP_ERROR,
                                                          new ActionMessage( IPropertiesConstant.SAME_AREA_ALREADY_EXISTS,
                                                                             areaPojo.getArea() ) );
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    else if ( areaPojo.getAreaId() == 0 )
                    {
                        unusedAreas.add( areaPojo );
                    }
                }
                inGisForm.getAreaDataList().removeAll( unusedAreas );
            }
        }
        else if ( StringUtils.equals( IActionForwardConstant.CREATE_SOCIETY, inMethod ) )
        {
            LOGGER.info( "validateForm method called:" );
            if ( inGisForm.getStateId() > 0 )
            {
                inGisForm.setCityList( GISUtils.getCities( inGisForm.getStatePojoList(), inGisForm.getStateId() ) );
            }
            if ( StringUtils.isNotBlank( inGisForm.getCity() ) )
            {
                inGisForm.setAreaList( GISUtils.getAreas( inGisForm.getCityList(), inGisForm.getCity() ) );
            }
            if ( inGisForm.getSocietyPojo().getSocietyId() == 0 )
            {
                if ( inGisForm.getStateId() == 0 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_STATE_REQUIRED ) );
                }
                else if ( StringUtils.isEmpty( inGisForm.getCity() ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_CITY_REQUIRED ) );
                }
                else if ( inGisForm.getSocietyPojo().getAreaId() == 0 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_AREA_REQUIRED ) );
                }
            }
            else if ( StringUtils.isEmpty( inGisForm.getSocietyPojo().getLocalityName() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LOCALITY_REQUIRED ) );
            }
            else if ( inGisForm.getSocietyPojo().getPrimaryPincode() == 0 )
            {
                inActionError
                        .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PINCODE ) );
            }
            else if ( inGisForm.getSocietyPojo().getPrimaryPincode() > 0 )
            {
                if ( String.valueOf( inGisForm.getSocietyPojo().getPrimaryPincode() ).length() != 6 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_PINCODE_LENGTH ) );
                }
            }
            else if ( inGisForm.getNetworkPartnerPojo().getRfsDus() < 0 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_RFS_DUS_REQUIRED ) );
            }
            else if ( inGisForm.getPartnerId() <= 0 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_GIS_NETWORKPARTNER ) );
            }
            else if ( !StringUtils.isValidObj( inGisForm.getProductArray() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_GIS_PRODUCT ) );
            }
            else if ( StringUtils.isEmpty( inGisForm.getSocietyPojo().getNetworkAvailability() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_TYPE_OF_FIELD_REQUIRED ) );
            }
            else if ( StringUtils.isEmpty( inGisForm.getSocietyPojo().getCustomerCategory() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_CUSTOMER_CATEGORY_REQUIRED ) );
            }
            if ( !inActionError.isEmpty() && inGisForm.getPartnerId() > 0 )
            {
                inGisForm
                        .setProductList( CRMCacheManager.getProductByPartnerID( inGisForm.getPartnerId(),
                                                                                CRMDisplayListConstants.NETWORK_PARTNER
                                                                                        .getCode() ) );
            }
            /*else if ( StringUtils.isEmpty( inGisForm.getSocietyPojo().getLatitude() )
                    || !StringUtils.compareRegularExp( CRMRegex.PATTERN_REAL_NUMBER.getPattern(), inGisForm
                            .getSocietyPojo().getLatitude() ) )
            {
                inActionError.add( "latitude", new ActionMessage( CRMRegex.PATTERN_REAL_NUMBER.getErrorKey(),
                                                                  "Latitude" ) );
            }
            else if ( StringUtils.isEmpty( inGisForm.getSocietyPojo().getLongitude() )
                    || !StringUtils.compareRegularExp( CRMRegex.PATTERN_REAL_NUMBER.getPattern(), inGisForm
                            .getSocietyPojo().getLongitude() ) )
            {
                inActionError.add( "longitude", new ActionMessage( CRMRegex.PATTERN_REAL_NUMBER.getErrorKey(),
                                                                   "Longitude" ) );
            }*/
        }
    }

    public static ActionErrors validateGisForm( ActionErrors inActionError, GISForm inGisForm )
    {
        String fileName = null;
        if ( StringUtils.isValidObj( inGisForm.getFormFile() ) )
        {
            fileName = inGisForm.getFormFile().getFileName();
        }
        if ( StringUtils.isEmpty( fileName ) )
        {
            inActionError.add( IAppConstants.GIS_ERROR, new ActionMessage( IPropertiesConstant.ERROR_GIS_FORMFILE ) );
        }
        else if ( !fileName.endsWith( ".xls" ) && !fileName.endsWith( ".xlsx" ) )
        {
            inActionError
                    .add( IAppConstants.GIS_ERROR, new ActionMessage( IPropertiesConstant.ERROR_GIS_INVALID_FILE ) );
        }
        //        else if ( inGisForm.getPartnerId() <= 0 )
        //        {
        //            inActionError.add( IAppConstants.GIS_ERROR,
        //                               new ActionMessage( IPropertiesConstant.ERROR_GIS_NETWORKPARTNER ) );
        //        }
        //        else if ( inGisForm.getProductArray().length == 0 )
        //        {
        //            inActionError.add( IAppConstants.GIS_ERROR, new ActionMessage( IPropertiesConstant.ERROR_GIS_PRODUCT ) );
        //        }
        return inActionError;
    }
}
