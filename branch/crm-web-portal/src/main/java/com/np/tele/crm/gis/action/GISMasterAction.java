package com.np.tele.crm.gis.action;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.gis.bm.IGISMaster;
import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class GISMasterAction
    extends DispatchAction
{
    private static final Logger LOGGER        = Logger.getLogger( GISMasterAction.class );
    private IGISMaster          gisMasterImpl = null;

    public IGISMaster getGisMasterImpl()
    {
        return gisMasterImpl;
    }

    public void setGisMasterImpl( IGISMaster inGisMasterImpl )
    {
        gisMasterImpl = inGisMasterImpl;
    }

    public ActionForward masterGisPage( ActionMapping mapping,
                                        ActionForm form,
                                        HttpServletRequest request,
                                        HttpServletResponse response )
        throws Exception
    {
        GISForm gisForm = (GISForm) form;
        List<StatePojo> stateList = GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA );
        gisForm.setStatePojoList( stateList );
        SortingComparator<StatePojo> sortComparator = new SortingComparator<StatePojo>( "stateName" );
        Collections.sort( gisForm.getStatePojoList(), sortComparator );
        sortComparator = null;
        gisForm.setManageGis( IAppConstants.STATE_GIS );
        List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
        gisForm.setStatusList( statusList );
        return mapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward searchState( ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response )
        throws Exception
    {
        ActionMessages errors = getErrors( request );
        ActionMessages messages = getMessages( request );
        try
        {
            GISForm gisForm = (GISForm) form;
            gisForm.setManageGis( IAppConstants.STATE_GIS );
            LOGGER.info( "Manage Gis: " + gisForm.getManageGis() );
            GisDto gisDto = getGisMasterImpl().getStateList( gisForm );
            if ( StringUtils.equals( gisDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && StringUtils.isValidObj( gisDto.getStatePojosList() ) )
            {
                LOGGER.info( "Size OF State List: " + gisDto.getStatePojosList().size() );
                if ( gisDto.getStatePojosList().isEmpty() )
                {
                    errors.add( IAppConstants.NO_STATE_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
                else
                {
                    gisForm.setStateDataList( gisDto.getStatePojosList() );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( gisDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Excpetion:", ex );
        }
        saveErrors( request, errors );
        saveMessages( request, messages );
        return mapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward searchCity( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
        throws Exception
    {
        GISForm gisForm = (GISForm) form;
        ActionMessages errors = getErrors( request );
        ActionMessages messages = getMessages( request );
        gisForm.setManageGis( IAppConstants.CITY_GIS );
        GisDto gisDto = getGisMasterImpl().getCityList( gisForm );
        if ( StringUtils.equals( gisDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                && StringUtils.isValidObj( gisDto.getCityPojosList() ) )
        {
            LOGGER.info( "Size OF City List: " + gisDto.getCityPojosList().size() );
            if ( !gisDto.getCityPojosList().isEmpty() )
            {
                gisForm.setCityDataList( gisDto.getCityPojosList() );
            }
            else
            {
                LOGGER.info( "City List Empty" );
                errors.add( IAppConstants.NO_CITY_RECORD_FOUND,
                            new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
            }
        }
        else
        {
            LOGGER.info( "ERROR City Not Found" );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( gisDto.getStatusCode() ) );
        }
        saveErrors( request, errors );
        saveMessages( request, messages );
        return mapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward searchArea( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
        throws Exception
    {
        GISForm gisForm = (GISForm) form;
        ActionMessages errors = getErrors( request );
        ActionMessages messages = getMessages( request );
        gisForm.setManageGis( IAppConstants.AREA_GIS );
        if ( gisForm.getStateIdForArea() > 0 )
        {
            gisForm.setCityList( GISUtils.getCities( gisForm.getStatePojoList(), gisForm.getStateIdForArea() ) );
        }
        GisDto gisDto = getGisMasterImpl().getAreaList( gisForm );
        if ( StringUtils.equals( gisDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                && StringUtils.isValidObj( gisDto.getAreaPojosList() ) )
        {
            LOGGER.info( "Size OF Area List: " + gisDto.getAreaPojosList().size() );
            if ( !gisDto.getAreaPojosList().isEmpty() )
            {
                gisForm.setAreaDataList( gisDto.getAreaPojosList() );
            }
            else
            {
                LOGGER.info( "Area List Empty" );
                errors.add( IAppConstants.NO_AREA_RECORD_FOUND,
                            new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
            }
        }
        else
        {
            LOGGER.info( "Area Not Found" );
        }
        saveErrors( request, errors );
        saveMessages( request, messages );
        return mapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward addStateRow( ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.MASTER_GIS_PAGE;
        GISForm gisForm = (GISForm) form;
        if ( !StringUtils.isValidObj( gisForm.getStateDataList() ) )
        {
            gisForm.setStateDataList( ( new LinkedList<StatePojo>() ) );
        }
        gisForm.setManageGis( IAppConstants.STATE_GIS );
        if ( StringUtils.isNotEmpty( gisForm.getHiddenGisOperation() )
                && StringUtils.equals( gisForm.getHiddenGisOperation(), "C" ) )
        {
            LOGGER.info( "Added state rows for copy" );
            StatePojo statePojo1 = null;
            List<StatePojo> newStatePojos = new LinkedList<StatePojo>();
            for ( StatePojo statePojo : gisForm.getStateDataList() )
            {
                if ( statePojo.isEditable() && statePojo.getStateId() > 0 )
                {
                    statePojo1 = new StatePojo();
                    statePojo1.setCountryId( statePojo.getCountryId() );
                    statePojo1.setStateName( statePojo.getStateName() );
                    statePojo1.setStateAlias( statePojo.getStateAlias() );
                    statePojo1.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    statePojo1.setEditable( true );
                    newStatePojos.add( statePojo1 );
                    statePojo.setEditable( false );
                }
            }
            if ( !newStatePojos.isEmpty() )
            {
                gisForm.getStateDataList().addAll( 0, newStatePojos );
            }
        }
        else if ( StringUtils.isNotEmpty( gisForm.getHiddenGisOperation() )
                && StringUtils.equals( gisForm.getHiddenGisOperation(), "E" ) )
        {
            LOGGER.info( "Edit state rows." );
        }
        else
        {
            LOGGER.info( "Added New State Row" );
            StatePojo statePojo = new StatePojo();
            statePojo.setEditable( true );
            statePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            statePojo.setCountryId( GISUtils.getCountryId( IAppConstants.COUNTRY_INDIA ) );
            gisForm.getStateDataList().add( 0, statePojo );
        }
        LOGGER.info( "Size of list :" + gisForm.getStateDataList().size() );
        return mapping.findForward( forwardPage );
    }

    public ActionForward deleteGISRow( ActionMapping inMapping,
                                       ActionForm inForm,
                                       HttpServletRequest inRequest,
                                       HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        GISForm gisForm = (GISForm) inForm;
        try
        {
            if ( StringUtils.equals( IAppConstants.STATE_GIS, gisForm.getManageGis() ) )
            {
                gisForm.getStateDataList().remove( gisForm.getRowCounter() );
            }
            else if ( StringUtils.equals( IAppConstants.CITY_GIS, gisForm.getManageGis() ) )
            {
                gisForm.getCityDataList().remove( gisForm.getRowCounter() );
            }
            else if ( StringUtils.equals( IAppConstants.AREA_GIS, gisForm.getManageGis() ) )
            {
                gisForm.getAreaDataList().remove( gisForm.getRowCounter() );
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward addNewState( ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.SEARCH_AFTER_EDIT_STATE;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        GISForm gisForm = (GISForm) form;
        LOGGER.info( "State Id:" + gisForm.getHiddenGisId() );
        gisForm.setManageGis( IAppConstants.STATE_GIS );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        GisDto gisDto = getGisMasterImpl().createNewState( gisForm, userDto.getCrmUserDetailsPojo().getUserId() );
        String statusCode = gisDto.getStatusCode();
        if ( null != statusCode )
        {
            if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Successfully State created" );
                gisForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward addCityRow( final ActionMapping mapping,
                                     final ActionForm form,
                                     final HttpServletRequest request,
                                     final HttpServletResponse response )
        throws Exception
    {
        GISForm gisForm = (GISForm) form;
        if ( !StringUtils.isValidObj( gisForm.getCityDataList() ) )
        {
            gisForm.setCityDataList( ( new LinkedList<CityPojo>() ) );
        }
        gisForm.setManageGis( IAppConstants.CITY_GIS );
        if ( StringUtils.isNotEmpty( gisForm.getHiddenGisOperation() )
                && StringUtils.equals( gisForm.getHiddenGisOperation(), "C" ) )
        {
            LOGGER.info( "Added city rows for copy." );
            CityPojo cityPojo1 = null;
            List<CityPojo> newCityPojos = new LinkedList<CityPojo>();
            for ( CityPojo cityPojo : gisForm.getCityDataList() )
            {
                if ( cityPojo.isEditable() && cityPojo.getCityId() > 0 )
                {
                    cityPojo1 = new CityPojo();
                    cityPojo1.setCityName( cityPojo.getCityName() );
                    cityPojo1.setCityAlias( cityPojo.getCityAlias() );
                    cityPojo1.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    cityPojo1.setEditable( true );
                    cityPojo1.setStateId( gisForm.getStateId() );
                    newCityPojos.add( cityPojo1 );
                    cityPojo.setEditable( false );
                }
            }
            gisForm.getCityDataList().addAll( 0, newCityPojos );
        }
        else if ( StringUtils.isNotEmpty( gisForm.getHiddenGisOperation() )
                && StringUtils.equals( gisForm.getHiddenGisOperation(), "E" ) )
        {
            LOGGER.info( "Editing city rows." );
        }
        else
        {
            LOGGER.info( "Added new city rows." );
            CityPojo cityPojo = new CityPojo();
            cityPojo.setEditable( true );
            cityPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            cityPojo.setStateId( gisForm.getStateId() );
            gisForm.getCityDataList().add( 0, cityPojo );
        }
        LOGGER.info( "Size of City list :" + gisForm.getCityDataList().size() );
        return mapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward addNewCity( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.SEARCH_AFTER_EDIT_CITY;
        GISForm gisForm = (GISForm) form;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        gisForm.setManageGis( IAppConstants.CITY_GIS );
        LOGGER.info( "City Id:" + gisForm.getHiddenGisId() );
        LOGGER.info( "Satate Id:  " + gisForm.getStateId() );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        GisDto gisDto = getGisMasterImpl().createNewCity( gisForm, userDto.getCrmUserDetailsPojo().getUserId() );
        String statusCode = gisDto.getStatusCode();
        if ( null != statusCode )
        {
            if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Successfully City created" );
                gisForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
            }
            else
            {
                LOGGER.info( "Somthing has wrong in create City.... " );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward addAreaRow( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
        throws Exception
    {
        GISForm gisForm = (GISForm) form;
        if ( !StringUtils.isValidObj( gisForm.getAreaDataList() ) )
        {
            gisForm.setAreaDataList( ( new LinkedList<AreaPojo>() ) );
        }
        gisForm.setManageGis( IAppConstants.AREA_GIS );
        LOGGER.info( "HIdden Operation: " + gisForm.getHiddenGisOperation() );
        if ( StringUtils.isNotEmpty( gisForm.getHiddenGisOperation() )
                && StringUtils.equals( gisForm.getHiddenGisOperation(), "C" ) )
        {
            LOGGER.info( "Added Area ROW for Copy" );
            AreaPojo areaPojo1 = null;
            List<AreaPojo> newAreaPojos = new LinkedList<AreaPojo>();
            for ( AreaPojo areaPojo : gisForm.getAreaDataList() )
            {
                if ( areaPojo.isEditable() && areaPojo.getAreaId() > 0 )
                {
                    areaPojo1 = new AreaPojo();
                    areaPojo1.setArea( areaPojo.getArea() );
                    areaPojo1.setAreaAlias( areaPojo.getAreaAlias() );
                    areaPojo1.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    areaPojo1.setEditable( true );
                    areaPojo1.setCityId( areaPojo.getCityId() );
                    newAreaPojos.add( areaPojo1 );
                    areaPojo.setEditable( false );
                }
            }
            gisForm.getAreaDataList().addAll( 0, newAreaPojos );
        }
        else if ( StringUtils.isNotEmpty( gisForm.getHiddenGisOperation() )
                && StringUtils.equals( gisForm.getHiddenGisOperation(), "E" ) )
        {
            LOGGER.info( "Added Area ROW for Edit" );
        }
        else
        {
            LOGGER.info( "Added Area ROW for New" );
            AreaPojo areaPojo = new AreaPojo();
            areaPojo.setEditable( true );
            areaPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            areaPojo.setCityId( gisForm.getCityId() );
            gisForm.getAreaDataList().add( 0, areaPojo );
        }
        if ( gisForm.getStateIdForArea() > 0 )
        {
            gisForm.setCityList( GISUtils.getCities( gisForm.getStatePojoList(), gisForm.getStateIdForArea() ) );
        }
        LOGGER.info( "Size of Area list :" + gisForm.getAreaList().size() );
        return mapping.findForward( IActionForwardConstant.MASTER_GIS_PAGE );
    }

    public ActionForward addNewArea( ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.SEARCH_AFTER_EDIT_AREA;
        GISForm gisForm = (GISForm) form;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        gisForm.setManageGis( IAppConstants.AREA_GIS );
        LOGGER.info( "Area Id:" + gisForm.getHiddenGisId() );
        LOGGER.info( "Satate Id:  " + gisForm.getStateId() );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        GisDto gisDto = getGisMasterImpl().createNewArea( gisForm, userDto.getCrmUserDetailsPojo().getUserId() );
        String statusCode = gisDto.getStatusCode();
        if ( null != statusCode )
        {
            if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Successfully Area created" );
                gisForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
            }
            else
            {
                LOGGER.info( "Somthing has wrong in create Area.... " );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }
}
