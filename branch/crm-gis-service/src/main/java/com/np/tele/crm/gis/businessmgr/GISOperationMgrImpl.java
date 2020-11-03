package com.np.tele.crm.gis.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.GISDto;
import com.np.tele.crm.gis.dao.IGISOperationDao;
import com.np.tele.crm.pojos.SocietyNetworkPartnerPojo;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class GISOperationMgrImpl
    implements IGISOperationMgr
{
    private IGISOperationDao    gisOperationDao = null;
    private static final Logger LOGGER          = Logger.getLogger( GISOperationMgrImpl.class );

    public IGISOperationDao getGisOperationDao()
    {
        return gisOperationDao;
    }

    public void setGisOperationDao( IGISOperationDao inGisOperationDao )
    {
        gisOperationDao = inGisOperationDao;
    }

    @Override
    public GISDto gisOperation( String inServiceParam, String inOperationParam, GISDto inGisDto )
    {
        boolean wrongService = false;
        try
        {
            LOGGER.info( "In Gis Operation:: ServiceParam ::: " + inServiceParam + " :: OperationParam : "
                    + inOperationParam );
            if ( StringUtils.equals( CRMParameter.SOCIETY.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam )
                        || StringUtils.equals( ServiceParameter.MODIFY.getParameter(), inServiceParam ) )
                {
                    loadGISSocietyData( inGisDto );
                }
                else if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inServiceParam ) )
                {
                    return getGisOperationDao().getSocietyList( inGisDto );
                }
                else if ( StringUtils.equals( ServiceParameter.MODIFY_STATUS.getParameter(), inServiceParam ) )
                {
                    return getGisOperationDao().updateSocietyStatus( inGisDto.getSocietyPojo() );
                }
                else if ( StringUtils.equals( SocietyNetworkPartnerPojo.class.getSimpleName(), inServiceParam ) )
                {
                    return getGisOperationDao().updateSocietyNPStatus( inGisDto.getSoNetworkPartnerPojo() );
                }
                else
                {
                    wrongService = true;
                }
            }
            else if ( StringUtils.equals( CRMParameter.COUNTRY.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam )
                        || StringUtils.equals( ServiceParameter.MODIFY.getParameter(), inServiceParam ) )
                {
                    return countryOperation( inGisDto );
                }
                else if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inServiceParam ) )
                {
                    return getGisOperationDao().getCountryList( inGisDto.getCountryPojo() );
                }
                else
                {
                    wrongService = true;
                }
            }
            else if ( StringUtils.equals( CRMParameter.STATE.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam )
                        || StringUtils.equals( ServiceParameter.MODIFY.getParameter(), inServiceParam ) )
                {
                    inGisDto = stateOperation( inGisDto );
                }
                else if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inServiceParam ) )
                {
                    return getGisOperationDao().getStateList( inGisDto.getStatePojo() );
                }
                else
                {
                    wrongService = true;
                }
            }
            else if ( StringUtils.equals( CRMParameter.CITY.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam )
                        || StringUtils.equals( ServiceParameter.MODIFY.getParameter(), inServiceParam ) )
                {
                    return cityOperation( inGisDto );
                }
                else if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inServiceParam ) )
                {
                    return getGisOperationDao().getCityList( inGisDto.getCityPojo() );
                }
                else
                {
                    wrongService = true;
                }
            }
            else if ( StringUtils.equals( CRMParameter.AREA.getParameter(), inOperationParam ) )
            {
                if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam )
                        || StringUtils.equals( ServiceParameter.MODIFY.getParameter(), inServiceParam ) )
                {
                    return areaOperation( inGisDto );
                }
                else if ( StringUtils.equals( ServiceParameter.SEARCH.getParameter(), inServiceParam ) )
                {
                    return getGisOperationDao().getAreaList( inGisDto );
                }
                else
                {
                    wrongService = true;
                }
            }
            else if ( StringUtils.equals( CRMParameter.GIS_DETAILS.getParameter(), inOperationParam ) )
            {
                 if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam ) )
                {
                     return getGisOperationDao().getGISIdByName(inGisDto,"ByName");
                }
            }
            else if ( StringUtils.equals( CRMParameter.GIS_DETAILS_BY_ID.getParameter(), inOperationParam ) )
            {
                 if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam ) )
                {
                     return getGisOperationDao().getGISIdByName(inGisDto,"ByID");
                }
            }
            if ( wrongService )
            {
                LOGGER.info( "Service Name Is Not Correct. Service Name ::" + inServiceParam );
                inGisDto.setStatusCode( CRMServiceCode.CRM994.getStatusCode() );
                inGisDto.setStatusDesc( CRMServiceCode.CRM994.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Get Exception in Gis Operation Method ::", ex );
            inGisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inGisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inGisDto;
    }

    private GISDto loadGISSocietyData( GISDto inGisDto )
    {
        try
        {
            if ( StringUtils.isValidObj( inGisDto.getSocietyPojosList() ) )
            {
                getGisOperationDao().createSociety( inGisDto );
            }
            else
            {
                LOGGER.info( "Find society pojo list null" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception in load society data", ex );
        }
        return inGisDto;
    }

    @Override
    public GISDto countryOperation( GISDto inGisDto )
    {
        try
        {
            if ( StringUtils.isValidObj( inGisDto.getCountryPojo() ) )
            {
                inGisDto = getGisOperationDao().countryOperation( inGisDto.getCountryPojo() );
            }
            else
            {
                LOGGER.info( "Country Pojo Found Null. Can't Process." );
                inGisDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inGisDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in Country Operation Method", ex );
            inGisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inGisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inGisDto;
    }

    @Override
    public GISDto stateOperation( GISDto inGisDto )
    {
        try
        {
            if ( StringUtils.isValidObj( inGisDto.getStatePojosList() ) && !inGisDto.getStatePojosList().isEmpty() )
            {
                inGisDto = getGisOperationDao().stateOperation( inGisDto.getStatePojosList() );
            }
            else
            {
                LOGGER.info( "State Pojo Found Null. Can't Process." );
                inGisDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inGisDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in State Operation Method", ex );
            inGisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inGisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inGisDto;
    }

    @Override
    public GISDto cityOperation( GISDto inGisDto )
    {
        try
        {
            if ( StringUtils.isValidObj( inGisDto.getCityPojosList() ) && !inGisDto.getCityPojosList().isEmpty() )
            {
                inGisDto = getGisOperationDao().cityOperation( inGisDto.getCityPojosList() );
            }
            else
            {
                LOGGER.info( "City Pojo Found Null. Can't Process." );
                inGisDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inGisDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in City Operation Method", ex );
            inGisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inGisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inGisDto;
    }

    @Override
    public GISDto areaOperation( GISDto inGisDto )
    {
        try
        {
            if ( StringUtils.isValidObj( inGisDto.getAreaPojosList() ) && !inGisDto.getAreaPojosList().isEmpty() )
            {
                inGisDto = getGisOperationDao().areaOperation( inGisDto.getAreaPojosList() );
            }
            else
            {
                LOGGER.info( "Area Pojo Found Null. Can't Process." );
                inGisDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inGisDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in Area Operation Method", ex );
            inGisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inGisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inGisDto;
    }
}
