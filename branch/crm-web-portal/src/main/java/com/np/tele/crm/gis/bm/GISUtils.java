package com.np.tele.crm.gis.bm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.GISMasterPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CountryPojo;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public final class GISUtils
{
    private static final Logger                     LOGGER          = Logger.getLogger( GISUtils.class );
    private static final Map<String, Collection<?>> GIS_CONTENT_MAP = new LinkedHashMap<String, Collection<?>>();

    public static IGISManager getGisManagerImpl()
    {
        return (IGISManager) IAppConstants.flyWeightBeanMap.get( IAppConstants.GIS_MANAGER );
    }

    public static void toRefresh( String inKey )
    {
        LOGGER.info( "Clear GIS_CONTENT_MAP chache" );
        synchronized ( IAppConstants.GIS_TIME_MAP )
        {
            //            GIS_TIME_MAP.remove( CRMStatusCode.ACTIVE.getStatusCode() + IAppConstants.UNDERSCORE + inKey );
            //            GIS_TIME_MAP.remove( IAppConstants.ALL + IAppConstants.UNDERSCORE + inKey );
            IAppConstants.GIS_TIME_MAP.clear();
        }
    }

    private static Collection<?> getActiveCollection( String inKey )
    {
        inKey = CRMStatusCode.ACTIVE.getStatusCode() + IAppConstants.UNDERSCORE + inKey;
        if ( IAppConstants.GIS_TIME_MAP.containsKey( inKey ) && GIS_CONTENT_MAP.containsKey( inKey ) )
        {
            if ( DateUtils.minuteDiff( IAppConstants.GIS_TIME_MAP.get( inKey ), Calendar.getInstance().getTime() ) >= 10 )
            {
                return null;
            }
            return GIS_CONTENT_MAP.get( inKey );
        }
        return null;
    }

    private static void setActiveCollection( String inKey, Collection<?> inList )
    {
        inKey = CRMStatusCode.ACTIVE.getStatusCode() + IAppConstants.UNDERSCORE + inKey;
        IAppConstants.GIS_TIME_MAP.put( inKey, Calendar.getInstance().getTime() );
        GIS_CONTENT_MAP.put( inKey, inList );
    }

    private static Collection<?> getAllCollection( String inKey )
    {
        inKey = IAppConstants.ALL + IAppConstants.UNDERSCORE + inKey;
        if ( IAppConstants.GIS_TIME_MAP.containsKey( inKey ) && GIS_CONTENT_MAP.containsKey( inKey ) )
        {
            if ( DateUtils.minuteDiff( IAppConstants.GIS_TIME_MAP.get( inKey ), Calendar.getInstance().getTime() ) >= 10 )
            {
                return null;
            }
            return GIS_CONTENT_MAP.get( inKey );
        }
        return null;
    }

    private static void setAllCollection( String inKey, Collection<?> inList )
    {
        inKey = IAppConstants.ALL + IAppConstants.UNDERSCORE + inKey;
        IAppConstants.GIS_TIME_MAP.put( inKey, Calendar.getInstance().getTime() );
        GIS_CONTENT_MAP.put( inKey, inList );
    }

    public static short getCountryId( String inCountryName )
    {
        @SuppressWarnings("unchecked")
        List<CountryPojo> countryPojos = (List<CountryPojo>) getActiveCollection( IAppConstants.COUNTRY_LIST );
        if ( ValidationUtil.isNotValidCollection( countryPojos ) )
        {
            getActiveStates( inCountryName );
            countryPojos = (List<CountryPojo>) getAllCollection( IAppConstants.COUNTRY_LIST );
        }
        if ( ValidationUtil.isValidCollection( countryPojos ) )
        {
            for ( CountryPojo countryPojo : countryPojos )
            {
                if ( StringUtils.equals( inCountryName, countryPojo.getCountryName() ) )
                {
                    return countryPojo.getCountryId();
                }
            }
        }
        return 0;
    }

    /**
     *
     * @param inCountry: Either CountryName or Country ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E> List<StatePojo> getActiveStates( E inCountry )
    {
        List<StatePojo> masterList = (List<StatePojo>) getActiveCollection( inCountry.toString() );
        if ( ValidationUtil.isNotValidCollection( masterList ) && StringUtils.isValidObj( inCountry ) )
        {
            List<CountryPojo> countryPojos = (List<CountryPojo>) getActiveCollection( IAppConstants.COUNTRY_LIST );
            if ( ValidationUtil.isNotValidCollection( countryPojos ) )
            {
                countryPojos = getGisMasterData( CRMStatusCode.ACTIVE.getStatusCode() );
                setActiveCollection( IAppConstants.COUNTRY_LIST, countryPojos );
            }
            masterList = getStates( countryPojos, inCountry );
            SortingComparator<StatePojo> sortComparator = new SortingComparator<StatePojo>( "stateName" );
            Collections.sort( masterList, sortComparator );
            sortComparator = null;
            setActiveCollection( inCountry.toString(), masterList );
        }
        return masterList;
    }

    /**
     *
     * @param inState: Either State Name or State ID
     * @param inCountry: Either CountryName or Country ID
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E> List<CityPojo> getActiveCities( E inCountry, E inState )
    {
        List<CityPojo> masterList = (List<CityPojo>) getActiveCollection( inCountry.toString()
                + IAppConstants.UNDERSCORE + inState.toString() );
        if ( ValidationUtil.isNotValidCollection( masterList ) && StringUtils.isValidObj( inCountry )
                && StringUtils.isValidObj( inState ) )
        {
            masterList = getCities( getActiveStates( inCountry ), inState );
            setActiveCollection( inCountry.toString() + IAppConstants.UNDERSCORE + inState.toString(), masterList );
        }
        SortingComparator<CityPojo> sortComparator = new SortingComparator<CityPojo>( "cityName" );
        Collections.sort( masterList, sortComparator );
        sortComparator = null;
        return masterList;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<AreaPojo> getActiveAreas( E inCountry, E inState, E inCity )
    {
        List<AreaPojo> masterList = new LinkedList<AreaPojo>();
        if ( !StringUtils.isValidObj( inCountry ) || !StringUtils.isValidObj( inState ) )
        {
            if ( StringUtils.isValidObj( inCity )
                    && ( inCity instanceof Long || StringUtils.isNumeric( inCity.toString() ) ) )
            {
                List<StatePojo> statePojos = getActiveStates( IAppConstants.COUNTRY_INDIA );
                if ( StringUtils.isValidObj( statePojos ) )
                {
                    List<CityPojo> cityPojos = new LinkedList<CityPojo>();
                    for ( StatePojo statePojo : statePojos )
                    {
                        cityPojos.addAll( statePojo.getCities() );
                    }
                    masterList = getAreas( cityPojos, inCity );
                }
            }
        }
        else
        {
            masterList = (List<AreaPojo>) getActiveCollection( inCountry.toString() + IAppConstants.UNDERSCORE
                    + inState.toString() + IAppConstants.UNDERSCORE + inCity.toString() );
            if ( ValidationUtil.isNotValidCollection( masterList ) )
            {
                masterList = getAreas( getActiveCities( inCountry, inState ), inCity );
                setActiveCollection( inCountry.toString() + IAppConstants.UNDERSCORE + inState.toString()
                        + IAppConstants.UNDERSCORE + inCity.toString(), masterList );
            }
        }
        SortingComparator<AreaPojo> sortComparator = new SortingComparator<AreaPojo>( "area" );
        Collections.sort( masterList, sortComparator );
        sortComparator = null;
        return masterList;
    }

    //    @SuppressWarnings("unchecked")
    //    public static <E> List<LocalityPojo> getActiveLocalities( E inCountry, E inState, E inCity, E inArea )
    //    {
    //        List<LocalityPojo> masterList = new LinkedList<LocalityPojo>();
    //        if ( !StringUtils.isValidObj( inCountry ) || !StringUtils.isValidObj( inState )
    //                || !StringUtils.isValidObj( inCity ) )
    //        {
    //            if ( StringUtils.isValidObj( inArea )
    //                    && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
    //            {
    //                List<StatePojo> statePojos = getActiveStates( IAppConstants.COUNTRY_INDIA );
    //                if ( StringUtils.isValidObj( statePojos ) )
    //                {
    //                    List<CityPojo> cityPojos = new LinkedList<CityPojo>();
    //                    for ( StatePojo statePojo : statePojos )
    //                    {
    //                        cityPojos.addAll( statePojo.getCities() );
    //                    }
    //                    if ( StringUtils.isValidObj( cityPojos ) )
    //                    {
    //                        List<AreaPojo> areaPojos = new LinkedList<AreaPojo>();
    //                        for ( CityPojo cityPojo : cityPojos )
    //                        {
    //                            areaPojos.addAll( cityPojo.getAreas() );
    //                        }
    //                        masterList = getLocalities( areaPojos, inArea );
    //                    }
    //                }
    //            }
    //        }
    //        else
    //        {
    //            masterList = (List<LocalityPojo>) getActiveCollection( inCountry.toString() + inState.toString()
    //                    + inCity.toString() + inArea.toString() );
    //            if ( ValidationUtil.isNotValidCollection( masterList ) )
    //            {
    //                masterList = getLocalities( getActiveAreas( inCountry, inState, inCity ), inArea );
    //                setActiveCollection( inCountry.toString() + inState.toString() + inCity.toString() + inArea.toString(),
    //                                     masterList );
    //            }
    //        }
    //        SortingComparator<LocalityPojo> sortComparator = new SortingComparator<LocalityPojo>( "locality" );
    //        Collections.sort( masterList, sortComparator );
    //        sortComparator = null;
    //        return masterList;
    //    }
    @SuppressWarnings("unchecked")
    public static <E> List<SocietyPojo> getActiveSocieties( E inCountry, E inState, E inCity, E inArea )
    {
        List<SocietyPojo> masterList = new LinkedList<SocietyPojo>();
        if ( !StringUtils.isValidObj( inCountry ) || !StringUtils.isValidObj( inState )
                || !StringUtils.isValidObj( inCity ) )
        {
            LOGGER.info( "AreaId" + inArea );
            if ( StringUtils.isValidObj( inArea )
                    && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
            {
                LOGGER.info( "AreaId" + inArea );
                List<StatePojo> statePojos = getActiveStates( inCountry );
                if ( StringUtils.isValidObj( statePojos ) )
                {
                    List<CityPojo> cityPojos = new LinkedList<CityPojo>();
                    for ( StatePojo statePojo : statePojos )
                    {
                        cityPojos.addAll( statePojo.getCities() );
                    }
                    if ( StringUtils.isValidObj( cityPojos ) )
                    {
                        List<AreaPojo> areaPojos = new LinkedList<AreaPojo>();
                        for ( CityPojo cityPojo : cityPojos )
                        {
                            areaPojos.addAll( cityPojo.getAreas() );
                        }
                        if ( StringUtils.isValidObj( areaPojos ) )
                        {
                            masterList = getSocieties( areaPojos, inArea );
                        }
                    }
                }
            }
        }
        else
        {
            masterList = (List<SocietyPojo>) getActiveCollection( inCountry.toString() + IAppConstants.UNDERSCORE
                    + inState.toString() + IAppConstants.UNDERSCORE + inCity.toString() + IAppConstants.UNDERSCORE
                    + inArea.toString() );
            if ( ValidationUtil.isNotValidCollection( masterList ) )
            {
                masterList = getSocieties( getActiveAreas( inCountry, inState, inCity ), inArea.toString() );
                setActiveCollection( inCountry.toString() + IAppConstants.UNDERSCORE + inState.toString()
                                             + IAppConstants.UNDERSCORE + inCity.toString() + IAppConstants.UNDERSCORE
                                             + inArea.toString(),
                                     masterList );
            }
        }
        return masterList;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<StatePojo> getAllStates( E inCountry )
    {
        List<StatePojo> masterList = (List<StatePojo>) getAllCollection( inCountry.toString() );
        if ( ValidationUtil.isNotValidCollection( masterList ) && StringUtils.isValidObj( inCountry ) )
        {
            List<CountryPojo> countryPojos = (List<CountryPojo>) getAllCollection( IAppConstants.COUNTRY_LIST );
            if ( ValidationUtil.isNotValidCollection( countryPojos ) )
            {
                countryPojos = getGisMasterData( null );
                setAllCollection( IAppConstants.COUNTRY_LIST, countryPojos );
                masterList = getStates( countryPojos, inCountry );
                setAllCollection( inCountry.toString(), masterList );
            }
        }
        return masterList;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<CityPojo> getAllCities( E inCountry, E inState )
    {
        List<CityPojo> masterList = (List<CityPojo>) getAllCollection( inCountry.toString() + IAppConstants.UNDERSCORE
                + inState.toString() );
        if ( ValidationUtil.isNotValidCollection( masterList ) && StringUtils.isValidObj( inCountry )
                && StringUtils.isValidObj( inState ) )
        {
            masterList = getCities( getAllStates( inCountry ), inState );
            setAllCollection( inCountry.toString() + IAppConstants.UNDERSCORE + inState.toString(), masterList );
        }
        return masterList;
    }

    @SuppressWarnings("unchecked")
    public static <E> List<AreaPojo> getAllAreas( E inCountry, E inState, E inCity )
    {
        List<AreaPojo> masterList = new LinkedList<AreaPojo>();
        if ( !StringUtils.isValidObj( inCountry ) || !StringUtils.isValidObj( inState ) )
        {
            if ( StringUtils.isValidObj( inCity )
                    && ( inCity instanceof Long || StringUtils.isNumeric( inCity.toString() ) ) )
            {
                List<StatePojo> statePojos = getAllStates( IAppConstants.COUNTRY_INDIA );
                if ( StringUtils.isValidObj( statePojos ) )
                {
                    List<CityPojo> cityPojos = new LinkedList<CityPojo>();
                    for ( StatePojo statePojo : statePojos )
                    {
                        cityPojos.addAll( statePojo.getCities() );
                    }
                    masterList = getAreas( cityPojos, inCity );
                }
            }
        }
        else
        {
            masterList = (List<AreaPojo>) getAllCollection( inCountry.toString() + IAppConstants.UNDERSCORE
                    + inState.toString() + IAppConstants.UNDERSCORE + inCity.toString() );
            if ( ValidationUtil.isNotValidCollection( masterList ) )
            {
                masterList = getAreas( getAllCities( inCountry, inState ), inCity );
                setAllCollection( inCountry.toString() + IAppConstants.UNDERSCORE + inState.toString()
                        + IAppConstants.UNDERSCORE + inCity.toString(), masterList );
            }
        }
        return masterList;
    }

    //    @SuppressWarnings("unchecked")
    //    public static <E> List<LocalityPojo> getAllLocalities( E inCountry, E inState, E inCity, E inArea )
    //    {
    //        List<LocalityPojo> masterList = new LinkedList<LocalityPojo>();
    //        if ( !StringUtils.isValidObj( inCountry ) || !StringUtils.isValidObj( inState )
    //                || !StringUtils.isValidObj( inCity ) )
    //        {
    //            if ( StringUtils.isValidObj( inArea )
    //                    && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
    //            {
    //                List<StatePojo> statePojos = getAllStates( IAppConstants.COUNTRY_INDIA );
    //                if ( StringUtils.isValidObj( statePojos ) )
    //                {
    //                    List<CityPojo> cityPojos = new LinkedList<CityPojo>();
    //                    for ( StatePojo statePojo : statePojos )
    //                    {
    //                        cityPojos.addAll( statePojo.getCities() );
    //                    }
    //                    if ( StringUtils.isValidObj( cityPojos ) )
    //                    {
    //                        List<AreaPojo> areaPojos = new LinkedList<AreaPojo>();
    //                        for ( CityPojo cityPojo : cityPojos )
    //                        {
    //                            areaPojos.addAll( cityPojo.getAreas() );
    //                        }
    //                        masterList = getLocalities( areaPojos, inArea );
    //                    }
    //                }
    //            }
    //        }
    //        else
    //        {
    //            masterList = (List<LocalityPojo>) getAllCollection( inCountry.toString() + inState.toString()
    //                    + inCity.toString() + inArea.toString() );
    //            if ( ValidationUtil.isNotValidCollection( masterList ) )
    //            {
    //                masterList = getLocalities( getAllAreas( inCountry, inState, inCity ), inArea );
    //                setAllCollection( inCountry.toString() + inState.toString() + inCity.toString() + inArea.toString(),
    //                                  masterList );
    //            }
    //        }
    //        return masterList;
    //    }
    @SuppressWarnings("unchecked")
    public static <E> List<SocietyPojo> getAllSocieties( E inCountry, E inState, E inCity, E inArea )
    {
        List<SocietyPojo> masterList = new LinkedList<SocietyPojo>();
        if ( !StringUtils.isValidObj( inCountry ) || !StringUtils.isValidObj( inState )
                || !StringUtils.isValidObj( inCity ) || !StringUtils.isValidObj( inArea ) )
        {
            if ( StringUtils.isValidObj( inArea )
                    && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
            {
                List<StatePojo> statePojos = getAllStates( inCountry );
                if ( StringUtils.isValidObj( statePojos ) )
                {
                    List<CityPojo> cityPojos = new LinkedList<CityPojo>();
                    for ( StatePojo statePojo : statePojos )
                    {
                        cityPojos.addAll( statePojo.getCities() );
                    }
                    if ( StringUtils.isValidObj( cityPojos ) )
                    {
                        List<AreaPojo> areaPojos = new LinkedList<AreaPojo>();
                        for ( CityPojo cityPojo : cityPojos )
                        {
                            areaPojos.addAll( cityPojo.getAreas() );
                        }
                        if ( StringUtils.isValidObj( areaPojos ) )
                        {
                            masterList = getSocieties( areaPojos, inArea );
                        }
                    }
                }
            }
        }
        else
        {
            masterList = (List<SocietyPojo>) getAllCollection( inCountry.toString() + inState.toString()
                    + inCity.toString() + inArea.toString() + inArea.toString() );
            if ( ValidationUtil.isNotValidCollection( masterList ) )
            {
                masterList = getSocieties( getAllAreas( inCountry, inState, inCity ), inArea );
                setAllCollection( inCountry.toString() + inState.toString() + inCity.toString() + inArea.toString()
                        + inArea.toString(), masterList );
            }
        }
        return masterList;
    }

    public static <E> List<StatePojo> getStates( List<CountryPojo> inCountryPojos, E inCountry )
    {
        List<StatePojo> statePojos = new LinkedList<StatePojo>();
        if ( StringUtils.isValidObj( inCountryPojos ) && StringUtils.isValidObj( inCountry ) )
        {
            if ( inCountry instanceof Long || StringUtils.isNumeric( inCountry.toString() ) )
            {
                CountryPojo countryPojo = new CountryPojo();
                countryPojo.setCountryId( Short.valueOf( inCountry.toString() ) );
                int pojoIndex = inCountryPojos.indexOf( countryPojo );
                if ( pojoIndex >= 0 )
                {
                    countryPojo = inCountryPojos.get( pojoIndex );
                    statePojos = countryPojo.getStates();
                }
            }
            else if ( inCountry instanceof String )
            {
                for ( CountryPojo countryPojo : inCountryPojos )
                {
                    if ( StringUtils.equals( inCountry.toString(), countryPojo.getCountryName() ) )
                    {
                        statePojos = countryPojo.getStates();
                        break;
                    }
                }
            }
        }
        return statePojos;
    }

    public static <E> List<CityPojo> getCities( List<StatePojo> inStatePojos, E inState )
    {
        List<CityPojo> cityPojos = new LinkedList<CityPojo>();
        if ( StringUtils.isValidObj( inStatePojos ) && StringUtils.isValidObj( inState ) )
        {
            if ( inState instanceof Long || StringUtils.isNumeric( inState.toString() ) )
            {
                StatePojo statePojo = new StatePojo();
                statePojo.setStateId( Long.parseLong( inState.toString() ) );
                int pojoIndex = inStatePojos.indexOf( statePojo );
                if ( pojoIndex >= 0 )
                {
                    statePojo = inStatePojos.get( pojoIndex );
                    cityPojos = statePojo.getCities();
                }
            }
            else if ( inState instanceof String )
            {
                for ( StatePojo statePojo : inStatePojos )
                {
                    if ( StringUtils.equals( statePojo.getStateName(), inState.toString() ) )
                    {
                        cityPojos = statePojo.getCities();
                        break;
                    }
                }
            }
        }
        return cityPojos;
    }

    public static <E> List<AreaPojo> getAreas( List<CityPojo> inCityPojos, E inCity )
    {
        List<AreaPojo> areaPojos = new LinkedList<AreaPojo>();
        if ( StringUtils.isValidObj( inCityPojos ) && StringUtils.isValidObj( inCity ) )
        {
            if ( inCity instanceof Long || StringUtils.isNumeric( inCity.toString() ) )
            {
                CityPojo cityPojo = new CityPojo();
                cityPojo.setCityId( Long.parseLong( inCity.toString() ) );
                int pojoIndex = inCityPojos.indexOf( cityPojo );
                if ( pojoIndex >= 0 )
                {
                    cityPojo = inCityPojos.get( pojoIndex );
                    areaPojos = cityPojo.getAreas();
                }
            }
            else if ( inCity instanceof String )
            {
                for ( CityPojo cityPojo : inCityPojos )
                {
                    if ( StringUtils.equals( cityPojo.getCityName(), inCity.toString() ) )
                    {
                        areaPojos = cityPojo.getAreas();
                        break;
                    }
                }
            }
        }
        LOGGER.info( "Area Pojos Size:" + areaPojos.size() );
        return areaPojos;
    }

    //    public static <E> List<LocalityPojo> getLocalities( List<AreaPojo> inAreaPojos, E inArea )
    //    {
    //        List<LocalityPojo> localityPojos = new LinkedList<LocalityPojo>();
    //        if ( StringUtils.isValidObj( inAreaPojos ) && StringUtils.isValidObj( inArea ) )
    //        {
    //            if ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) )
    //            {
    //                AreaPojo areaPojo = new AreaPojo();
    //                areaPojo.setAreaId( Long.parseLong( inArea.toString() ) );
    //                int pojoIndex = inAreaPojos.indexOf( areaPojo );
    //                if ( pojoIndex >= 0 )
    //                {
    //                    areaPojo = inAreaPojos.get( pojoIndex );
    //                    localityPojos = areaPojo.getLocalities();
    //                }
    //            }
    //            else if ( inArea instanceof String )
    //            {
    //                for ( AreaPojo areaPojo : inAreaPojos )
    //                {
    //                    if ( StringUtils.equals( areaPojo.getArea(), inArea.toString() ) )
    //                    {
    //                        localityPojos = areaPojo.getLocalities();
    //                        break;
    //                    }
    //                }
    //            }
    //        }
    //        LOGGER.info( "Locality Pojos:" + localityPojos );
    //        return localityPojos;
    //    }
    public static <E> List<SocietyPojo> getSocieties( List<AreaPojo> inAreaPojos, E inArea )
    {
        List<SocietyPojo> societyPojos = new LinkedList<SocietyPojo>();
        if ( CommonValidator.isValidCollection( inAreaPojos ) && StringUtils.isValidObj( inArea ) )
        {
            if ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) )
            {
                long areaId = StringUtils.numericValue( inArea.toString() );
                if ( areaId > 0 )
                {
                    AreaPojo areaPojo = new AreaPojo();
                    areaPojo.setAreaId( Long.parseLong( inArea.toString() ) );
                    int pojoIndex = inAreaPojos.indexOf( areaPojo );
                    if ( pojoIndex >= 0 )
                    {
                        areaPojo = inAreaPojos.get( pojoIndex );
                        societyPojos = getSocietybyArea( areaPojo.getAreaId() );
                    }
                }
                else
                {
                    for ( AreaPojo areaPojo : inAreaPojos )
                    {
                        societyPojos.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
                    }
                }
            }
            else if ( inArea instanceof String )
            {
                if ( StringUtils.isNotBlank( inArea.toString() ) )
                {
                    for ( AreaPojo areaPojo : inAreaPojos )
                    {
                        if ( StringUtils.equals( areaPojo.getArea(), inArea.toString() ) )
                        {
                            societyPojos = getSocietybyArea( areaPojo.getAreaId() );
                            break;
                        }
                    }
                }
                else
                {
                    for ( AreaPojo areaPojo : inAreaPojos )
                    {
                        societyPojos.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
                    }
                }
            }
        }
        LOGGER.info( "Society Pojos:" + societyPojos );
        return societyPojos;
    }

    public static <E> List<SocietyPojo> getSocieties( List<AreaPojo> inAreaPojos, E inArea, String inProductName )
    {
        List<SocietyPojo> societyPojos = new LinkedList<SocietyPojo>();
        if ( CommonValidator.isValidCollection( inAreaPojos ) && StringUtils.isValidObj( inArea ) )
        {
            if ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) )
            {
                long areaId = StringUtils.numericValue( inArea.toString() );
                if ( areaId > 0 )
                {
                    AreaPojo areaPojo = new AreaPojo();
                    areaPojo.setAreaId( Long.parseLong( inArea.toString() ) );
                    int pojoIndex = inAreaPojos.indexOf( areaPojo );
                    if ( pojoIndex >= 0 )
                    {
                        areaPojo = inAreaPojos.get( pojoIndex );
                        societyPojos = getSocietybyArea( areaPojo.getAreaId() );
                    }
                }
                else
                {
                    for ( AreaPojo areaPojo : inAreaPojos )
                    {
                        societyPojos.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
                    }
                }
            }
            else if ( inArea instanceof String )
            {
                if ( StringUtils.isNotBlank( inArea.toString() ) )
                {
                    for ( AreaPojo areaPojo : inAreaPojos )
                    {
                        if ( StringUtils.equals( areaPojo.getArea(), inArea.toString() ) )
                        {
                            societyPojos = getSocietybyArea( areaPojo.getAreaId() );
                            break;
                        }
                    }
                }
                else
                {
                    for ( AreaPojo areaPojo : inAreaPojos )
                    {
                        societyPojos.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
                    }
                }
            }
        }
        LOGGER.info( "Society Pojos:" + societyPojos );
        List<SocietyPojo> societyPojoWithProduct = new ArrayList<SocietyPojo>();
        LOGGER.info( "Product Name For search Society:: " + inProductName );
        if ( CommonValidator.isValidCollection( societyPojos ) )
        {
            for ( SocietyPojo societyPojo : societyPojos )
            {
                List<SocietyNetworkPartnerPojo> soList = societyPojo.getSocietyNetworkPartners();
                LOGGER.info( "Society partner list:: " + soList.size() );
                inner: for ( SocietyNetworkPartnerPojo soPartnerPojo : soList )
                {
                    if ( StringUtils.equals( soPartnerPojo.getProductName(), inProductName ) )
                    {
                        societyPojoWithProduct.add( societyPojo );
                        break inner;
                    }
                }
            }
            LOGGER.info( "Size Of Society Pojo List basis OF Product Name:: " + societyPojoWithProduct.size() );
        }
        SortingComparator<SocietyPojo> sortComparator = new SortingComparator<SocietyPojo>( "societyName" );
        Collections.sort( societyPojoWithProduct, sortComparator );
        sortComparator = null;
        return societyPojoWithProduct;
    }

    @SuppressWarnings("unchecked")
    public static List<GISMasterPojo> getActiveGisMasterPojos()
    {
        List<GISMasterPojo> masterList = (List<GISMasterPojo>) getActiveCollection( IAppConstants.GIS_MASTER );
        if ( ValidationUtil.isNotValidCollection( masterList ) )
        {
            List<CountryPojo> countryPojos = (List<CountryPojo>) getActiveCollection( IAppConstants.COUNTRY_LIST );
            if ( ValidationUtil.isNotValidCollection( countryPojos ) )
            {
                getActiveStates( IAppConstants.COUNTRY_INDIA );
                countryPojos = (List<CountryPojo>) getActiveCollection( IAppConstants.COUNTRY_LIST );
            }
            masterList = processGisMaster( countryPojos );
            setActiveCollection( IAppConstants.GIS_MASTER, masterList );
        }
        return masterList;
    }

    @SuppressWarnings("unchecked")
    public static List<GISMasterPojo> getAllGisMasterPojos()
    {
        List<GISMasterPojo> masterList = (List<GISMasterPojo>) getAllCollection( IAppConstants.GIS_MASTER );
        if ( ValidationUtil.isNotValidCollection( masterList ) )
        {
            List<CountryPojo> countryPojos = (List<CountryPojo>) getAllCollection( IAppConstants.COUNTRY_LIST );
            if ( ValidationUtil.isNotValidCollection( countryPojos ) )
            {
                getAllStates( IAppConstants.COUNTRY_INDIA );
                countryPojos = (List<CountryPojo>) getAllCollection( IAppConstants.COUNTRY_LIST );
            }
            masterList = processGisMaster( countryPojos );
            setAllCollection( IAppConstants.GIS_MASTER, masterList );
        }
        return masterList;
    }

    private static List<GISMasterPojo> processGisMaster( List<CountryPojo> countryPojos )
    {
        List<GISMasterPojo> masterList = new LinkedList<GISMasterPojo>();
        String country = null;
        String state = null;
        String city = null;
        String area = null;
        long countryId = 0l;
        long stateId = 0l;
        long cityId = 0l;
        long areaId = 0l;
        GISMasterPojo gisMasterPojo = null;
        for ( CountryPojo countryPojo : countryPojos )
        {
            country = countryPojo.getCountryName();
            countryId = countryPojo.getCountryId();
            List<StatePojo> objStatePojos = countryPojo.getStates();
            for ( StatePojo statePojo : objStatePojos )
            {
                state = statePojo.getStateName();
                stateId = statePojo.getStateId();
                List<CityPojo> objCityPojos = statePojo.getCities();
                for ( CityPojo cityPojo : objCityPojos )
                {
                    city = cityPojo.getCityName();
                    cityId = cityPojo.getCityId();
                    List<AreaPojo> objAreaPojos = cityPojo.getAreas();
                    for ( AreaPojo areaPojo : objAreaPojos )
                    {
                        area = areaPojo.getArea();
                        areaId = areaPojo.getAreaId();
                        gisMasterPojo = new GISMasterPojo();
                        gisMasterPojo.setCountry( country );
                        gisMasterPojo.setState( state );
                        gisMasterPojo.setCity( city );
                        gisMasterPojo.setArea( area );
                        gisMasterPojo.setCountryId( countryId );
                        gisMasterPojo.setStateId( stateId );
                        gisMasterPojo.setCityId( cityId );
                        gisMasterPojo.setAreaId( areaId );
                        masterList.add( gisMasterPojo );
                    }
                }
            }
        }
        return masterList;
    }

    public static <E> GISMasterPojo getActiveGisMasterByArea( E inArea )
    {
        Long inAreaId = 0l;
        String area = null;
        if ( inArea instanceof Long || inArea instanceof Integer || StringUtils.isNumeric( inArea.toString() ) )
        {
            inAreaId = Long.parseLong( inArea.toString() );
        }
        else if ( inArea instanceof String )
        {
            area = inArea.toString();
        }
        List<GISMasterPojo> gisMasterPojos = getActiveGisMasterPojos();
        for ( GISMasterPojo gisMasterPojo : gisMasterPojos )
        {
            if ( StringUtils.equals( gisMasterPojo.getArea(), area ) || inAreaId == gisMasterPojo.getAreaId() )
            {
                return gisMasterPojo;
            }
        }
        return null;
    }

    public static <E> GISMasterPojo getGisMasterByAreaForViewSociety( E inArea )
    {
        Long inAreaId = 0l;
        String area = null;
        if ( inArea instanceof Long || inArea instanceof Integer || StringUtils.isNumeric( inArea.toString() ) )
        {
            inAreaId = Long.parseLong( inArea.toString() );
        }
        else if ( inArea instanceof String )
        {
            area = inArea.toString();
        }
        List<GISMasterPojo> gisMasterPojos = getAllGisMasterPojos();
        for ( GISMasterPojo gisMasterPojo : gisMasterPojos )
        {
            if ( StringUtils.equals( gisMasterPojo.getArea(), area ) || inAreaId == gisMasterPojo.getAreaId() )
            {
                return gisMasterPojo;
            }
        }
        return null;
    }

    //    public static <E> GISMasterPojo getAllGisMasterByLocality( E inLocality )
    //    {
    //        Long inLocalityId = 0l;
    //        String locality = null;
    //        if ( inLocality instanceof Long || inLocality instanceof Integer
    //                || StringUtils.isNumeric( inLocality.toString() ) )
    //        {
    //            inLocalityId = Long.parseLong( inLocality.toString() );
    //        }
    //        else if ( inLocality instanceof String )
    //        {
    //            locality = inLocality.toString();
    //        }
    //        List<GISMasterPojo> gisMasterPojos = getAllGisMasterPojos();
    //        for ( GISMasterPojo gisMasterPojo : gisMasterPojos )
    //        {
    //            if ( StringUtils.equals( gisMasterPojo.getLocality(), locality )
    //                    || inLocalityId == gisMasterPojo.getLocalityId() )
    //            {
    //                return gisMasterPojo;
    //            }
    //        }
    //        return null;
    //    }
    public static List<SocietyPojo> getAllActiveSocieties( final long inArea,
                                                           final long inCity,
                                                           final long inStateId,
                                                           final boolean all )
    {
        List<SocietyPojo> societies = new LinkedList<SocietyPojo>();
        if ( inArea > 0 )
        {
            societies.addAll( getActiveSocieties( IAppConstants.COUNTRY_INDIA, inStateId, inCity, inArea ) );
        }
        //        else if ( inArea > 0 )
        //        {
        //            List<LocalityPojo> localities = getActiveLocalities( IAppConstants.COUNTRY_INDIA, inStateId, inCity, inArea );
        //            for ( LocalityPojo localityPojo : localities )
        //            {
        //                societies.addAll( getSocietybyLocality( localityPojo.getLocalityId() ) );
        //            }
        //        }
        else if ( inCity > 0 )
        {
            List<AreaPojo> areas = getActiveAreas( IAppConstants.COUNTRY_INDIA, inStateId, inCity );
            for ( AreaPojo areaPojo : areas )
            {
                societies.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
            }
        }
        else if ( inStateId > 0 )
        {
            List<CityPojo> cityList = getActiveCities( IAppConstants.COUNTRY_INDIA, inStateId );
            for ( CityPojo cityPojo : cityList )
            {
                List<AreaPojo> areaList = cityPojo.getAreas();
                if ( StringUtils.isValidObj( areaList ) )
                {
                    for ( AreaPojo areaPojo : areaList )
                    {
                        societies.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
                    }
                }
            }
        }
        else if ( all )
        {
            List<StatePojo> states = getActiveStates( IAppConstants.COUNTRY_INDIA );
            for ( StatePojo statePojo : states )
            {
                List<CityPojo> cityList = statePojo.getCities();
                for ( CityPojo cityPojo : cityList )
                {
                    List<AreaPojo> areaList = cityPojo.getAreas();
                    if ( StringUtils.isValidObj( areaList ) )
                    {
                        for ( AreaPojo areaPojo : areaList )
                        {
                            societies.addAll( getSocietybyArea( areaPojo.getAreaId() ) );
                        }
                    }
                }
            }
        }
        return societies;
    }

    //    public static List<LocalityPojo> getAllActiveLocalities( final long area,
    //                                                             final long city,
    //                                                             final long inStateId,
    //                                                             final boolean all )
    //    {
    //        List<LocalityPojo> localities = new LinkedList<LocalityPojo>();
    //        if ( area > 0 )
    //        {
    //            localities.addAll( getActiveLocalities( IAppConstants.COUNTRY_INDIA, inStateId, city, area ) );
    //        }
    //        else if ( city > 0 )
    //        {
    //            List<AreaPojo> areas = getActiveAreas( IAppConstants.COUNTRY_INDIA, inStateId, city );
    //            for ( AreaPojo areaPojo : areas )
    //            {
    //                localities.addAll( areaPojo.getLocalities() );
    //            }
    //        }
    //        else if ( inStateId > 0 )
    //        {
    //            List<CityPojo> cityList = getActiveCities( IAppConstants.COUNTRY_INDIA, inStateId );
    //            for ( CityPojo cityPojo : cityList )
    //            {
    //                List<AreaPojo> areaList = cityPojo.getAreas();
    //                if ( StringUtils.isValidObj( areaList ) )
    //                {
    //                    for ( AreaPojo areaPojo : areaList )
    //                    {
    //                        localities.addAll( areaPojo.getLocalities() );
    //                    }
    //                }
    //            }
    //        }
    //        else if ( all )
    //        {
    //            List<StatePojo> states = getActiveStates( IAppConstants.COUNTRY_INDIA );
    //            for ( StatePojo statePojo : states )
    //            {
    //                List<CityPojo> cityList = statePojo.getCities();
    //                for ( CityPojo cityPojo : cityList )
    //                {
    //                    List<AreaPojo> areaList = cityPojo.getAreas();
    //                    if ( StringUtils.isValidObj( areaList ) )
    //                    {
    //                        for ( AreaPojo areaPojo : areaList )
    //                        {
    //                            localities.addAll( areaPojo.getLocalities() );
    //                        }
    //                    }
    //                }
    //            }
    //        }
    //        return localities;
    //    }
    public static List<AreaPojo> getAllActiveAreas( long city, long inStateId, boolean all )
    {
        List<AreaPojo> areas = new LinkedList<AreaPojo>();
        if ( city > 0 )
        {
            areas.addAll( getActiveAreas( IAppConstants.COUNTRY_INDIA, inStateId, city ) );
        }
        else if ( inStateId > 0 )
        {
            List<CityPojo> cityList = getActiveCities( IAppConstants.COUNTRY_INDIA, inStateId );
            for ( CityPojo cityPojo : cityList )
            {
                areas.addAll( cityPojo.getAreas() );
            }
        }
        else if ( all )
        {
            List<StatePojo> states = getAllStates( IAppConstants.COUNTRY_INDIA );
            for ( StatePojo statePojo : states )
            {
                List<CityPojo> cityList = statePojo.getCities();
                for ( CityPojo cityPojo : cityList )
                {
                    areas.addAll( cityPojo.getAreas() );
                }
            }
        }
        return areas;
    }

    public static List<CityPojo> getAllActiveCities()
    {
        List<StatePojo> states = getActiveStates( IAppConstants.COUNTRY_INDIA );
        List<CityPojo> cityList = new ArrayList<CityPojo>();
        for ( StatePojo statePojo : states )
        {
            cityList.addAll( statePojo.getCities() );
        }
        return cityList;
    }

    public static boolean isSocietyFeasible( LmsPojo inLmsPojo )
    {
        boolean isExist = false;
        List<SocietyPojo> socityPojoList = null;
        try
        {
            if ( StringUtils.isNotBlank( inLmsPojo.getState() ) && StringUtils.isNotBlank( inLmsPojo.getCity() )
                    && StringUtils.isNotBlank( inLmsPojo.getArea() ) )
            {
                socityPojoList = GISUtils.getActiveSocieties( IAppConstants.COUNTRY_INDIA, inLmsPojo.getState(),
                                                              inLmsPojo.getCity(), inLmsPojo.getArea() );
            }
            if ( CommonValidator.isValidCollection( socityPojoList ) )
            {
                String locality = inLmsPojo.getLocality();
                String society = null;
                String[] arr = StringUtils.split( locality, IAppConstants.DASH );
                if ( arr.length > 1 )
                {
                    locality = arr[0];
                    society = arr[1];
                }
                SocietyPojo societyPojo = GISUtils.getSociety( locality, society, socityPojoList );
                if ( StringUtils.isValidObj( societyPojo ) )
                {
                    isExist = true;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occurred while validating Master Data : ", ex );
        }
        return isExist;
    }

    public static boolean isAreaFeasible( LmsPojo inLmsPojo )
    {
        boolean isExist = false;
        List<SocietyPojo> socityPojoList = null;
        try
        {
            if ( StringUtils.isNotBlank( inLmsPojo.getState() ) && StringUtils.isNotBlank( inLmsPojo.getCity() )
                    && StringUtils.isNotBlank( inLmsPojo.getArea() ) )
            {
                socityPojoList = GISUtils.getActiveSocieties( IAppConstants.COUNTRY_INDIA, inLmsPojo.getState(),
                                                              inLmsPojo.getCity(), inLmsPojo.getArea() );
            }
            if ( CommonValidator.isValidCollection( socityPojoList ) )
            {
                isExist = true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occurred while validating isAreaFeasible Data : ", ex );
        }
        return isExist;
    }

    public static <E> List<SocietyPojo> getFeasibleSocieties( E inCountryIndia,
                                                              E inStateName,
                                                              E inCityName,
                                                              E inAreaName,
                                                              String inProductName )
    {
        List<SocietyPojo> societyPojos = getActiveSocieties( inCountryIndia, inStateName, inCityName, inAreaName );
        List<SocietyPojo> societyPojoWithProduct = new ArrayList<SocietyPojo>();
        LOGGER.info( "Product Name For search Society:: " + inProductName );
        if ( !societyPojos.isEmpty() )
        {
            for ( SocietyPojo societyPojo : societyPojos )
            {
                //                if ( StringUtils.equals( CRMDisplayListConstants.BROWN.getCode(), societyPojo.getNetworkAvailability() ) )
                //                {
                List<SocietyNetworkPartnerPojo> soList = societyPojo.getSocietyNetworkPartners();
                inner: for ( SocietyNetworkPartnerPojo soPartnerPojo : soList )
                {
                    if ( StringUtils.equals( soPartnerPojo.getProductName(), inProductName )
                            && StringUtils.equals( soPartnerPojo.getStatus(), "A" ) )
                    {
                        societyPojoWithProduct.add( societyPojo );
                        break inner;
                    }
                }
                //                }
            }
            LOGGER.info( "Size Of Society Pojo List basis OF Product Name:: " + societyPojoWithProduct.size() );
        }
        SortingComparator<SocietyPojo> sortComparator = new SortingComparator<SocietyPojo>( "societyName" );
        Collections.sort( societyPojoWithProduct, sortComparator );
        sortComparator = null;
        return societyPojoWithProduct;
    }

    //    public static <E> List<SocietyPojo> getAllFeasibleSocieties()
    //    {
    //        List<SocietyPojo> masterList = (List<SocietyPojo>) getActiveCollection( "AllFeasibleSocieties" );
    //        if ( ValidationUtil.isNotValidCollection( masterList ) )
    //        {
    //            List<SocietyPojo> societyPojos = getAllActiveSocieties( 0, 0, 0, 0, true );
    //            masterList = new ArrayList<SocietyPojo>();
    //            if ( CommonValidator.isValidCollection( societyPojos ) )
    //            {
    //                for ( SocietyPojo societyPojo : societyPojos )
    //                {
    //                    if ( StringUtils.equals( CRMDisplayListConstants.BROWN.getCode(),
    //                                             societyPojo.getNetworkAvailability() ) )
    //                    {
    //                        masterList.add( societyPojo );
    //                    }
    //                }
    //                if ( CommonValidator.isValidCollection( masterList ) )
    //                {
    //                    setActiveCollection( "AllFeasibleSocieties", masterList );
    //                }
    //            }
    //        }
    //        return masterList;
    //    }
    //    public static <E> List<LocalityPojo> getAllFeasibleLocalities()
    //    {
    //        List<LocalityPojo> masterList = (List<LocalityPojo>) getActiveCollection( "AllFeasibleLocalities" );
    //        if ( ValidationUtil.isNotValidCollection( masterList ) )
    //        {
    //            List<SocietyPojo> societyPojos = getAllFeasibleSocieties();
    //            Set<LocalityPojo> masterSet = new HashSet<LocalityPojo>();
    //            if ( CommonValidator.isValidCollection( societyPojos ) )
    //            {
    //                for ( SocietyPojo societyPojo : societyPojos )
    //                {
    //                    masterSet.add( getLocality( societyPojo.getLocalityId(), null ) );
    //                }
    //            }
    //            if ( CommonValidator.isValidCollection( masterSet ) )
    //            {
    //                masterList = new ArrayList<LocalityPojo>( masterSet );
    //                setActiveCollection( "AllFeasibleLocalities", masterList );
    //            }
    //        }
    //        return masterList;
    //    }
    private static <E> List<CountryPojo> getGisMasterData( String inStatus )
    {
        GisDto gisDto = new GisDto();
        CountryPojo countryPojo = new CountryPojo();
        countryPojo.setCountryName( IAppConstants.COUNTRY_INDIA );
        countryPojo.setStatus( inStatus );
        gisDto.setCountryPojo( countryPojo );
        gisDto = getGisManagerImpl().getCountries( gisDto );
        return gisDto.getCountryPojosList();
    }

    public static final <E> List<SocietyPojo> getSocietybyLocality( E inLocality )
    {
        return getSocietybyAreaPartnerService( inLocality, null, null );
    }

    public static final <E> List<SocietyPojo> getSocietybyArea( E inArea )
    {
        return getSocietybyArea( inArea, null, null );
    }

    public static final <E> List<SocietyPojo> getSocietybyArea( E inArea, Long inNetworkPartnerId, String inService )
    {
        GisDto gisDto = new GisDto();
        SocietyPojo society = null;
        if ( StringUtils.isValidObj( inArea )
                && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
        {
            society = new SocietyPojo();
            society.setAreaId( Long.parseLong( inArea.toString() ) );
            society.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        }
        if ( StringUtils.isValidObj( society ) )
        {
            if ( StringUtils.isValidObj( inNetworkPartnerId ) || StringUtils.isNotBlank( inService ) )
            {
                List<SocietyNetworkPartnerPojo> networkPartnerPojos = new ArrayList<SocietyNetworkPartnerPojo>();
                SocietyNetworkPartnerPojo record = new SocietyNetworkPartnerPojo();
                record.setProductName( inService );
                if ( inNetworkPartnerId > 0 )
                {
                    record.setPartnerId( inNetworkPartnerId );
                }
                networkPartnerPojos.add( record );
                society.getSocietyNetworkPartners().addAll( networkPartnerPojos );
            }
            gisDto.setSocietyPojo( society );
            gisDto = getGisManagerImpl().getSocietiesByArea( gisDto );
        }
        return gisDto.getSocietyPojosList();
    }

    public static final <E> List<SocietyPojo> getAllSocietybyArea( E inArea )
    {
        LOGGER.info( "getAllSocietybyArea" );
        GisDto gisDto = new GisDto();
        SocietyPojo society = null;
        if ( StringUtils.isValidObj( inArea )
                && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
        {
            society = new SocietyPojo();
            society.setAreaId( Long.parseLong( inArea.toString() ) );
        }
        else
        {
            society = new SocietyPojo();
        }
        gisDto.setSocietyPojo( society );
        gisDto = getGisManagerImpl().getSocietiesByArea( gisDto );
        return gisDto.getSocietyPojosList();
    }

    public static final <E> List<SocietyPojo> getSocietybyAreaPartnerService( E inArea,
                                                                              Long inNetworkPartnerId,
                                                                              String inService )
    {
        GisDto gisDto = new GisDto();
        SocietyPojo society = null;
        if ( StringUtils.isValidObj( inArea )
                && ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) ) )
        {
            society = new SocietyPojo();
            society.setAreaId( StringUtils.numericValue( inArea.toString() ) );
            society.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        }
        if ( StringUtils.isValidObj( society ) )
        {
            if ( StringUtils.isValidObj( inNetworkPartnerId ) || StringUtils.isNotBlank( inService ) )
            {
                List<SocietyNetworkPartnerPojo> networkPartnerPojos = new ArrayList<SocietyNetworkPartnerPojo>();
                SocietyNetworkPartnerPojo record = new SocietyNetworkPartnerPojo();
                record.setProductName( inService );
                if ( inNetworkPartnerId > 0 )
                {
                    record.setPartnerId( inNetworkPartnerId );
                }
                networkPartnerPojos.add( record );
                society.getSocietyNetworkPartners().addAll( networkPartnerPojos );
            }
            gisDto.setSocietyPojo( society );
            gisDto = getGisManagerImpl().getSocietiesByArea( gisDto );
        }
        return gisDto.getSocietyPojosList();
    }

    public static final <E> StatePojo getState( E inState )
    {
        if ( StringUtils.isValidObj( inState ) )
        {
            List<StatePojo> states = getActiveStates( IAppConstants.COUNTRY_INDIA );
            if ( inState instanceof Long || StringUtils.isNumeric( inState.toString() ) )
            {
                StatePojo statePojo = new StatePojo();
                statePojo.setStateId( Long.parseLong( inState.toString() ) );
                int pojoIndex = states.indexOf( statePojo );
                if ( pojoIndex >= 0 )
                {
                    return states.get( pojoIndex );
                }
            }
            else if ( inState instanceof String )
            {
                for ( StatePojo statePojo : states )
                {
                    if ( StringUtils.equals( statePojo.getStateName(), inState.toString() ) )
                    {
                        return statePojo;
                    }
                }
            }
        }
        return null;
    }

    public static final <E> CityPojo getCity( E inCity, List<CityPojo> inCities )
    {
        if ( StringUtils.isValidObj( inCity ) && CommonValidator.isValidCollection( inCities ) )
        {
            if ( inCity instanceof Long || StringUtils.isNumeric( inCity.toString() ) )
            {
                CityPojo cityPojo = new CityPojo();
                cityPojo.setCityId( Long.parseLong( inCity.toString() ) );
                int index = inCities.indexOf( cityPojo );
                if ( index >= 0 )
                {
                    return inCities.get( index );
                }
            }
            else if ( inCity instanceof String )
            {
                for ( CityPojo cityPojo : inCities )
                {
                    if ( StringUtils.equals( cityPojo.getCityName(), inCity.toString() ) )
                    {
                        return cityPojo;
                    }
                }
            }
        }
        return null;
    }

    public static final <E> AreaPojo getArea( E inArea, List<AreaPojo> inAreas )
    {
        if ( StringUtils.isValidObj( inArea ) && CommonValidator.isValidCollection( inAreas ) )
        {
            if ( inArea instanceof Long || StringUtils.isNumeric( inArea.toString() ) )
            {
                AreaPojo areaPojo = new AreaPojo();
                areaPojo.setAreaId( Long.parseLong( inArea.toString() ) );
                int index = inAreas.indexOf( areaPojo );
                if ( index >= 0 )
                {
                    return inAreas.get( index );
                }
            }
            else if ( inArea instanceof String )
            {
                for ( AreaPojo areaPojo : inAreas )
                {
                    if ( StringUtils.equals( areaPojo.getArea(), inArea.toString() ) )
                    {
                        return areaPojo;
                    }
                }
            }
        }
        return null;
    }

    //    public static final <E> LocalityPojo getLocality( E inLocality, List<LocalityPojo> inLocalities )
    //    {
    //        if ( !CommonValidator.isValidCollection( inLocalities ) )
    //        {
    //            inLocalities = getAllActiveLocalities( 0, 0, 0, true );
    //        }
    //        if ( StringUtils.isValidObj( inLocality ) && CommonValidator.isValidCollection( inLocalities ) )
    //        {
    //            if ( inLocality instanceof Long || StringUtils.isNumeric( inLocality.toString() ) )
    //            {
    //                LocalityPojo localityPojo = new LocalityPojo();
    //                localityPojo.setLocalityId( Long.parseLong( inLocality.toString() ) );
    //                int index = inLocalities.indexOf( localityPojo );
    //                if ( index >= 0 )
    //                {
    //                    return inLocalities.get( index );
    //                }
    //            }
    //            else if ( inLocality instanceof String )
    //            {
    //                for ( LocalityPojo localityPojo : inLocalities )
    //                {
    //                    if ( StringUtils.equals( localityPojo.getLocality(), inLocality.toString() ) )
    //                    {
    //                        return localityPojo;
    //                    }
    //                }
    //            }
    //        }
    //        return null;
    //    }
    public static final <E> SocietyPojo getSociety( E inSociety, List<SocietyPojo> inSocieties )
    {
        if ( StringUtils.isValidObj( inSociety ) && CommonValidator.isValidCollection( inSocieties ) )
        {
            if ( inSociety instanceof Long || StringUtils.isNumeric( inSociety.toString() ) )
            {
                SocietyPojo society = new SocietyPojo();
                society.setSocietyId( Long.parseLong( inSociety.toString() ) );
                int index = inSocieties.indexOf( society );
                if ( index >= 0 )
                {
                    return inSocieties.get( index );
                }
            }
            else if ( inSociety instanceof String )
            {
                for ( SocietyPojo societyPojo : inSocieties )
                {
                    LOGGER.info( "Society Pojos:" + societyPojo );
                    if ( StringUtils.equals( societyPojo.getSocietyName(), inSociety.toString() ) )
                    {
                        return societyPojo;
                    }
                }
            }
        }
        return null;
    }

    public static final <E> SocietyPojo getSociety( String inLocality, String inSociety, List<SocietyPojo> inSocieties )
    {
        if ( StringUtils.isNotBlank( inLocality ) && CommonValidator.isValidCollection( inSocieties ) )
        {
            for ( SocietyPojo societyPojo : inSocieties )
            {
                LOGGER.info( "Society Pojos:" + societyPojo );
                if ( StringUtils.equals( StringUtils.trimToNull( societyPojo.getLocalityName() ),
                                         StringUtils.trimToNull( inLocality ) ) )
                {
                    if ( StringUtils.equals( StringUtils.trimToNull( societyPojo.getSocietyName() ),
                                             StringUtils.trimToNull( inSociety ) ) )
                    {
                        return societyPojo;
                    }
                }
            }
        }
        return null;
    }

    public static List<StatePojo> getGISByNP( List<StatePojo> inStatePojos, long inPartnerID, String inService )
    {
        List<StatePojo> statePojos = (List<StatePojo>) getActiveCollection( "GISBYNP" + inPartnerID + inService );
        if ( !CommonValidator.isValidCollection( statePojos ) && CommonValidator.isValidCollection( inStatePojos ) )
        {
            statePojos = new ArrayList<StatePojo>();
            if ( CommonValidator.isValidCollection( inStatePojos ) )
            {
                boolean toAdd = false;
                StatePojo tempStatePojo = null;
                CityPojo tempCityPojo = null;
                AreaPojo tempAreaPojo = null;
                LocalityPojo tempLocalityPojo = null;
                SocietyPojo tempSocietyPojo = null;
                Iterator<StatePojo> itrState = inStatePojos.iterator();
                while ( itrState.hasNext() )
                {
                    toAdd = false;
                    StatePojo statePojo = (StatePojo) itrState.next();
                    Iterator<CityPojo> itrCity = statePojo.getCities().iterator();
                    while ( itrCity.hasNext() )
                    {
                        CityPojo cityPojo = (CityPojo) itrCity.next();
                        Iterator<AreaPojo> itrArea = cityPojo.getAreas().iterator();
                        while ( itrArea.hasNext() )
                        {
                            AreaPojo areaPojo = (AreaPojo) itrArea.next();
                            List<SocietyPojo> societyPojos = getSocietybyAreaPartnerService( areaPojo.getAreaId(),
                                                                                             inPartnerID, inService );
                            if ( CommonValidator.isValidCollection( societyPojos ) )
                            {
                                if ( statePojos.contains( statePojo ) )
                                {
                                    tempStatePojo = statePojos.get( statePojos.indexOf( statePojo ) );
                                }
                                else
                                {
                                    tempStatePojo = new StatePojo();
                                    CRMUtils.copyAllProperties( tempStatePojo, statePojo );
                                    statePojos.add( tempStatePojo );
                                }
                                if ( tempStatePojo.getCities().contains( cityPojo ) )
                                {
                                    tempCityPojo = tempStatePojo.getCities().get( tempStatePojo.getCities()
                                                                                          .indexOf( cityPojo ) );
                                }
                                else
                                {
                                    tempCityPojo = new CityPojo();
                                    CRMUtils.copyAllProperties( tempCityPojo, cityPojo );
                                    tempStatePojo.getCities().add( tempCityPojo );
                                }
                                if ( tempCityPojo.getAreas().contains( areaPojo ) )
                                {
                                    tempAreaPojo = tempCityPojo.getAreas().get( tempCityPojo.getAreas()
                                                                                        .indexOf( areaPojo ) );
                                }
                                else
                                {
                                    tempAreaPojo = new AreaPojo();
                                    CRMUtils.copyAllProperties( tempAreaPojo, areaPojo );
                                    tempCityPojo.getAreas().add( tempAreaPojo );
                                }
                                //                                if ( tempAreaPojo.getLocalities().contains( localityPojo ) )
                                //                                {
                                //                                    tempLocalityPojo = tempAreaPojo.getLocalities()
                                //                                            .get( tempAreaPojo.getLocalities().indexOf( localityPojo ) );
                                //                                }
                                //                                else
                                //                                {
                                //                                    tempLocalityPojo = new LocalityPojo();
                                //                                    CRMUtils.copyAllProperties( tempLocalityPojo, localityPojo );
                                //                                    tempAreaPojo.getLocalities().add( tempLocalityPojo );
                                //                                }
                                for ( SocietyPojo societyPojo : societyPojos )
                                {
                                    if ( !tempAreaPojo.getSocieties().contains( societyPojo ) )
                                    {
                                        tempSocietyPojo = new SocietyPojo();
                                        CRMUtils.copyAllProperties( tempSocietyPojo, societyPojo );
                                        tempAreaPojo.getSocieties().add( tempSocietyPojo );
                                    }
                                }
                            }
                            // Don't delete the following code
                            //                                Iterator<SocietyPojo> itrSociety = societyPojos.iterator();
                            //                                while ( itrSociety.hasNext() )
                            //                                {
                            //                                    SocietyPojo societyPojo = (SocietyPojo) itrSociety.next();
                            //                                    Iterator<SocietyNetworkPartnerPojo> itrList = societyPojo
                            //                                            .getSocietyNetworkPartners().iterator();
                            //                                    toAdd = false;
                            //                                    np: while ( itrList.hasNext() )
                            //                                    {
                            //                                        SocietyNetworkPartnerPojo societyNetworkPartnerPojo = itrList.next();
                            //                                        if ( StringUtils.isValidObj( societyNetworkPartnerPojo )
                            //                                                && societyNetworkPartnerPojo.getPartnerId() == inPartnerID
                            //                                                && StringUtils.equals( societyNetworkPartnerPojo.getProductName(),
                            //                                                                       inService )
                            //                                                && StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(),
                            //                                                                       societyNetworkPartnerPojo.getStatus() ) )
                            //                                        {
                            //                                            toAdd = true;
                            //                                            break np;
                            //                                        }
                            //                                    }
                            //                                    if ( toAdd )
                            //                                    {
                            //                                        if ( statePojos.contains( statePojo ) )
                            //                                        {
                            //                                            tempStatePojo = statePojos.get( statePojos.indexOf( statePojo ) );
                            //                                        }
                            //                                        else
                            //                                        {
                            //                                            tempStatePojo = new StatePojo();
                            //                                            CRMUtils.copyAllProperties( tempStatePojo, statePojo );
                            //                                            statePojos.add( tempStatePojo );
                            //                                        }
                            //                                        if ( tempStatePojo.getCities().contains( cityPojo ) )
                            //                                        {
                            //                                            tempCityPojo = tempStatePojo.getCities().get( tempStatePojo.getCities()
                            //                                                                                                  .indexOf( cityPojo ) );
                            //                                        }
                            //                                        else
                            //                                        {
                            //                                            tempCityPojo = new CityPojo();
                            //                                            CRMUtils.copyAllProperties( tempCityPojo, cityPojo );
                            //                                            tempStatePojo.getCities().add( tempCityPojo );
                            //                                        }
                            //                                        if ( tempCityPojo.getAreas().contains( areaPojo ) )
                            //                                        {
                            //                                            tempAreaPojo = tempCityPojo.getAreas().get( tempCityPojo.getAreas()
                            //                                                                                                .indexOf( areaPojo ) );
                            //                                        }
                            //                                        else
                            //                                        {
                            //                                            tempAreaPojo = new AreaPojo();
                            //                                            CRMUtils.copyAllProperties( tempAreaPojo, areaPojo );
                            //                                            tempCityPojo.getAreas().add( tempAreaPojo );
                            //                                        }
                            //                                        if ( tempAreaPojo.getLocalities().contains( localityPojo ) )
                            //                                        {
                            //                                            tempLocalityPojo = tempAreaPojo.getLocalities()
                            //                                                    .get( tempAreaPojo.getLocalities().indexOf( localityPojo ) );
                            //                                        }
                            //                                        else
                            //                                        {
                            //                                            tempLocalityPojo = new LocalityPojo();
                            //                                            CRMUtils.copyAllProperties( tempLocalityPojo, localityPojo );
                            //                                            tempAreaPojo.getLocalities().add( tempLocalityPojo );
                            //                                        }
                            //                                        if ( !tempLocalityPojo.getSocieties().contains( societyPojo ) )
                            //                                        {
                            //                                            tempSocietyPojo = new SocietyPojo();
                            //                                            CRMUtils.copyAllProperties( tempSocietyPojo, societyPojo );
                            //                                            tempLocalityPojo.getSocieties().add( tempSocietyPojo );
                            //                                        }
                            //                                    }
                            //                                }
                        }
                    }
                }
                setActiveCollection( "GISBYNP" + inPartnerID + inService, statePojos );
            }
        }
        return statePojos;
    }

    public static LmsPojo getSocietyByPinCode( Integer pincode )
    {
        return getGisManagerImpl().getSocietyByPinCode( pincode );
    }
}
