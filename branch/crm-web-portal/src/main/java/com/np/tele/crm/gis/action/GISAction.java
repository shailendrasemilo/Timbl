package com.np.tele.crm.gis.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.gis.bm.IGISManager;
import com.np.tele.crm.gis.forms.GISForm;
import com.np.tele.crm.gis.forms.GISFormHelper;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.GISMasterPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class GISAction
    extends DispatchAction
{
    private static final Logger LOGGER         = Logger.getLogger( GISAction.class );
    private IGISManager         gisManagerImpl = null;
    private IMasterBMngr        masterBMngr    = null;

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public IGISManager getGisManagerImpl()
    {
        return gisManagerImpl;
    }

    public void setGisManagerImpl( IGISManager inGisManagerImpl )
    {
        gisManagerImpl = inGisManagerImpl;
    }

    public ActionForward uploadGisPage( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
        throws Exception
    {
        GISForm gisForm = (GISForm) inForm;
        gisForm.setPartnerList( CRMCacheManager.getNetworkPartners() );
        return inMapping.findForward( IActionForwardConstant.UPLOAD_GIS );
    }

    public ActionForward uploadGis( ActionMapping inMapping,
                                    ActionForm inForm,
                                    HttpServletRequest inRequest,
                                    HttpServletResponse inResponse )
        throws Exception
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        ActionErrors errors2 = new ActionErrors();
        GISForm gisForm = (GISForm) inForm;
        errors2 = GISFormHelper.validateGisForm( errors2, gisForm );
        if ( !errors2.isEmpty() )
        {
            errors = errors2;
        }
        else
        {
            String fileName = gisForm.getFormFile().getFileName();
            String user = userDto.getCrmUserDetailsPojo().getUserId();
            LOGGER.info( "File : " + fileName + " Size : " + gisForm.getFormFile().getFileSize() );
            String filePath = getServlet().getServletContext().getRealPath( "/" ) + "GisExcelUpload";
            List<String> statusList = getGisManagerImpl().processGISExcel( gisForm, user, filePath );
            if ( statusList.size() > 1 )
            {
                String status = statusList.get( 0 );
                int totalRecords = Integer.parseInt( statusList.get( 1 ) );
                int validCount = Integer.parseInt( statusList.get( 2 ) );
                String errorFile = statusList.get( 3 );
                LOGGER.info( "Valid Records: " + validCount + " Total Records : " + totalRecords
                        + " Valid Records db status: " + status );
                messages.add( "uploadStatus",
                              new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS2, totalRecords ) );
                messages.add( "uploadStatus",
                              new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS3, validCount ) );
                if ( validCount < totalRecords )
                {
                    messages.add( "displayErrorFile", new ActionMessage( "" ) );
                    inRequest.setAttribute( "fileName", errorFile );
                }
                if ( validCount > 0 )
                {
                    messages.add( "uploadStatus",
                                  new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS4, status ) );
                    gisForm.setFormFile( null );
                }
            }
            else
            {
                messages.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
            }
        }
        gisForm.setProductList( getMasterBMngr()
                .getProductsByPartnerId( gisForm.getPartnerId() + "",
                                         CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) );
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.UPLOAD_GIS );
    }

    public ActionForward createSocietyPage( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
        throws Exception
    {
        ActionMessages errors = null;
        try
        {
            errors = new ActionMessages();
            SocietyPojo societyPojo = new SocietyPojo();
            societyPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            GISForm gisForm = (GISForm) form;
            gisForm.setSocietyPojo( societyPojo );
            gisForm.setNetworkPartnerPojo( new SocietyNetworkPartnerPojo() );
            gisForm.setPartnerList( CRMCacheManager.getNetworkPartners() );
            gisForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            gisForm.setFieldTypeList( CRMUtils.getFieldTypes() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in Create Society Page", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveErrors( request, errors );
        return mapping.findForward( IActionForwardConstant.CREATE_SOCIETY );
    }

    public ActionForward createSociety( ActionMapping mapping,
                                        ActionForm inForm,
                                        HttpServletRequest request,
                                        HttpServletResponse response )
        throws Exception
    {
        ActionMessages messages = null;
        ActionMessages errors = null;
        String forward = IActionForwardConstant.CREATE_SOCIETY;
        try
        {
            messages = new ActionMessages();
            errors = new ActionMessages();
            GisDto gisDto = new GisDto();
            GISForm gisForm = (GISForm) inForm;
            String value = null;
            LOGGER.info( "in create Society" + gisForm.getSocietyPojo() );
            if ( StringUtils.isNotBlank( gisForm.getSocietyPojo().getLongitude() ) )
            {
                gisForm.getSocietyPojo()
                        .setLongitude( StringUtils.changeStringToDouble( gisForm.getSocietyPojo().getLongitude() ) );
            }
            if ( StringUtils.isNotBlank( gisForm.getSocietyPojo().getLatitude() ) )
            {
                gisForm.getSocietyPojo()
                        .setLatitude( StringUtils.changeStringToDouble( gisForm.getSocietyPojo().getLatitude() ) );
            }
            gisDto = getGisManagerImpl().createGisSocietyData( gisForm );
            if ( StringUtils.isValidObj( gisDto ) )
            {
                String statusCode = gisDto.getStatusCode();
                LOGGER.info( "\n:::status code:?:" + gisDto.getStatusCode() + "\n status code description:?:"
                        + gisDto.getStatusDesc() );
                if ( CRMServiceCode.CRM001.getStatusCode().equalsIgnoreCase( statusCode ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    HttpSession session = request.getSession( false );
                    session.setAttribute( IAppConstants.SOCIETY_POJO, gisForm.getSocietyPojo() );
                    forward = IActionForwardConstant.SEARCH_GIS_FOR_CREATE_SOCIETY;
                }
                else if ( CRMServiceCode.CRM161.getStatusCode().equalsIgnoreCase( statusCode ) )
                {
                    GISMasterPojo pojo = GISUtils.getActiveGisMasterByArea( gisForm.getSocietyPojo().getAreaId() );
                    if ( StringUtils.isNotEmpty( gisForm.getSocietyPojo().getSocietyName() ) )
                    {
                        value = gisForm.getSocietyPojo().getSocietyName();
                    }
                    else if ( StringUtils.isNotEmpty( gisForm.getSocietyPojo().getLocalityName() ) )
                    {
                        value = gisForm.getSocietyPojo().getLocalityName();
                    }
                    String areaString="State -"+pojo.getState()+",  City - "+pojo.getCity()+", Area -"+pojo.getArea();
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode, value, areaString ) );
                }
                else if ( CRMServiceCode.CRM162.getStatusCode().equalsIgnoreCase( statusCode ) )
                {
                    LOGGER.info( "Hello" );
                   /* GISMasterPojo pojo = GISUtils.getActiveGisMasterByArea( gisForm.getSocietyPojo().getAreaId() );
                    if ( StringUtils.isNotEmpty( gisForm.getSocietyPojo().getSocietyName() ) )
                    {
                        value = gisForm.getSocietyPojo().getSocietyName();
                    }
                    else if ( StringUtils.isNotEmpty( gisForm.getSocietyPojo().getLocalityName() ) )
                    {
                        value = gisForm.getSocietyPojo().getLocalityName();
                    }*/
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    HttpSession session = request.getSession( false );
                    session.setAttribute( IAppConstants.SOCIETY_POJO, gisForm.getSocietyPojo() );
                    forward = IActionForwardConstant.SEARCH_GIS_FOR_CREATE_SOCIETY;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.info( "Exception: ", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forward );
    }

    public ActionForward searchSocietyPage( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages errors = new ActionMessages();
        try
        {
            GISForm gisForm = (GISForm) inForm;
            gisForm.setStatusList( CRMUtils.getPartialStatusList() );
            List<PartnerPojo> partnerList = CRMCacheManager.getNetworkPartners();
            if ( StringUtils.isValidObj( partnerList ) )
            {
                gisForm.setPartnerPojoList( partnerList );
            }
            gisForm.setProductList( CRMUtils.getProducts() );
            SocietyPojo societyPojo = new SocietyPojo();
            gisForm.setSocietyPojo( societyPojo );
            gisForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            LOGGER.info( "After Fetching State List" + gisForm.getStatePojoList().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Fond Error in Search Society Page Method ", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.SEARCH_GIS );
    }

    public ActionForward searchSociety( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        try
        {
            HttpSession httpSession = inRequest.getSession( false );
            httpSession.removeAttribute( IAppConstants.SOCIETY_POJO_LIST_SESS );
            GISForm gisForm = (GISForm) inForm;
            List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
            gisForm.setStatusList( statusList );
            List<PartnerPojo> partnerList = CRMCacheManager.getNetworkPartners();
            gisForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            LOGGER.info( "State List" + gisForm.getStatePojoList().size() );
            if ( gisForm.getStateId() > 0 )
            {
                gisForm.setCityList( GISUtils.getCities( gisForm.getStatePojoList(), gisForm.getStateId() ) );
            }
            if ( gisForm.getCityId() > 0 )
            {
                gisForm.setAreaList( GISUtils.getAreas( gisForm.getCityList(), gisForm.getCityId() ) );
            }
            if ( StringUtils.isValidObj( partnerList ) )
            {
                gisForm.setPartnerPojoList( partnerList );
            }
            gisForm.setProductList( CRMUtils.getProducts() );
            GisDto gisDto = new GisDto();
            if ( !StringUtils.isValidObj( gisForm.getSocietyPojo() ) )
            {
                gisForm.setSocietyPojo( (SocietyPojo) httpSession.getAttribute( IAppConstants.SOCIETY_POJO ) );
            }
            LOGGER.info( "Searched Society name " + gisForm.getSocietyPojo().getSocietyName() + " society Id ::"
                    + gisForm.getSocietyPojo().getSocietyId() );
            gisDto = getGisManagerImpl().searchSociety( gisForm );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), gisDto.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( gisDto.getSocietyPojosList() ) )
                {
                    LOGGER.info( "size of searched societies::" + gisDto.getSocietyPojosList().size() );
                    for ( SocietyPojo societyPojo : gisDto.getSocietyPojosList() )
                    {
                        GISMasterPojo gisMasterPojo = GISUtils.getActiveGisMasterByArea( societyPojo.getAreaId() );
                        if ( StringUtils.isValidObj( gisMasterPojo ) )
                        {
                            societyPojo.setEditable( true );
                        }
                    }
                    httpSession.setAttribute( IAppConstants.SOCIETY_POJO_LIST_SESS, gisDto.getSocietyPojosList() );
                    gisForm.setSearchSocietyList( gisDto.getSocietyPojosList() );
                }
                else
                {
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( gisDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error In Search Society Method" + ex );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.SEARCH_GIS );
    }

    public ActionForward modifySocietyPage( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages errors = null;
        ActionMessages messages = null;
        try
        {
            messages = new ActionMessages();
            errors = new ActionMessages();
            HttpSession httpSession = inRequest.getSession( false );
            GISForm gisForm = (GISForm) inForm;
            List<SocietyPojo> societyPojos = (List<SocietyPojo>) httpSession
                    .getAttribute( IAppConstants.SOCIETY_POJO_LIST_SESS );
            List<ContentPojo> statusList = CRMUtils.getPartialStatusList();
            gisForm.setStatusList( statusList );
            if ( !societyPojos.isEmpty() )
            {
                LOGGER.info( "size of searched societies" + societyPojos.size() );
                SocietyPojo societyPojo = new SocietyPojo();
                societyPojo.setSocietyId( gisForm.getSocietyId() );
                int index = societyPojos.indexOf( societyPojo );
                LOGGER.info( "Index No : " + index );
                if ( index >= IAppConstants.ZERO )
                {
                    societyPojo = societyPojos.get( index );
                    GISMasterPojo gisMasterPojo = GISUtils.getActiveGisMasterByArea( societyPojo.getAreaId() );
                    if ( StringUtils.isValidObj( gisMasterPojo ) )
                    {
                        gisForm.setState( gisMasterPojo.getState() );
                        gisForm.setCity( gisMasterPojo.getCity() );
                        gisForm.setArea( gisMasterPojo.getArea() );
                    }
                    gisForm.setPartnerList( CRMCacheManager.getNetworkPartners() );
                    gisForm.setSocietyPojo( societyPojo );
                    SocietyPojo oldSocietyPojo = new SocietyPojo();
                    org.apache.commons.beanutils.PropertyUtils.copyProperties( oldSocietyPojo, societyPojo );
                    oldSocietyPojo.getSocietyNetworkPartners().addAll( societyPojo.getSocietyNetworkPartners() );
                    gisForm.setOldSocietyPojo( oldSocietyPojo );
                    Set<SocietyNetworkPartnerPojo> societyNetworkPartnerPojos = new HashSet<SocietyNetworkPartnerPojo>();
                    for ( SocietyNetworkPartnerPojo pojo : societyPojo.getSocietyNetworkPartners() )
                    {
                        gisForm.setPartnerId( pojo.getPartnerId() );
                        gisForm.setNetworkPartnerPojo( pojo );
                        societyNetworkPartnerPojos.add( pojo );
                    }
                    gisForm.setSocietyNetworkPartners( societyNetworkPartnerPojos );
                    gisForm.setProductList( CRMCacheManager
                            .getProductByPartnerID( gisForm.getPartnerId(),
                                                    CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) );
                }
            }
            else
            {
                LOGGER.info( "No Society Pojo List Found In Session " );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
            SocietyNetworkPartnerPojo oldSocietyNetworkPartnerpojo = new SocietyNetworkPartnerPojo();
            org.apache.commons.beanutils.PropertyUtils.copyProperties( oldSocietyNetworkPartnerpojo,
                                                                       gisForm.getNetworkPartnerPojo() );
            gisForm.setFieldTypeList( CRMUtils.getFieldTypes() );
            gisForm.setOldNetworkPartnerPojo( oldSocietyNetworkPartnerpojo );
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error In Search Society Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CREATE_SOCIETY );
    }

    public ActionForward copySocietyPage( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages errors = null;
        ActionMessages messages = null;
        try
        {
            messages = new ActionMessages();
            errors = new ActionMessages();
            HttpSession httpSession = inRequest.getSession( false );
            GISForm gisForm = (GISForm) inForm;
            List<SocietyPojo> societyPojos = (List<SocietyPojo>) httpSession
                    .getAttribute( IAppConstants.SOCIETY_POJO_LIST_SESS );
            if ( !societyPojos.isEmpty() )
            {
                LOGGER.info( "size of searched societies" + societyPojos.size() );
                SocietyPojo societyPojo = new SocietyPojo();
                societyPojo.setSocietyId( gisForm.getSocietyId() );
                int index = societyPojos.indexOf( societyPojo );
                LOGGER.info( "Index No : " + index );
                if ( index >= IAppConstants.ZERO )
                {
                    SocietyPojo pojo = societyPojos.get( index );
                    GISMasterPojo gisMasterPojo = GISUtils.getGisMasterByAreaForViewSociety( pojo.getAreaId() );
                    if ( StringUtils.isValidObj( gisMasterPojo ) )
                    {
                        gisForm.setStateId( gisMasterPojo.getStateId() );
                        gisForm.setCity( String.valueOf( gisMasterPojo.getCityId() ) );
                        LOGGER.info( "City Id" + gisMasterPojo.getCityId() );
                        gisForm.setArea( String.valueOf( gisMasterPojo.getAreaId() ) );
                    }
                    LOGGER.info( pojo.getSocietyNetworkPartners().get( 0 ).getPartnerId() );
                    gisForm.setPartnerId( pojo.getSocietyNetworkPartners().get( 0 ).getPartnerId() );
                    if ( gisForm.getStateId() > 0 )
                    {
                        gisForm.setCityList( GISUtils.getCities( gisForm.getStatePojoList(), gisForm.getStateId() ) );
                    }
                    if ( StringUtils.isNumeric( gisForm.getCity() ) && ( Long.parseLong( gisForm.getCity() ) ) > 0 )
                    {
                        gisForm.setAreaList( GISUtils.getAreas( gisForm.getCityList(), gisMasterPojo.getCityId() ) );
                    }
                    gisForm.setNetworkPartnerPojo( pojo.getSocietyNetworkPartners().get( 0 ) );
                    LOGGER.info( "Locality:::" + gisForm.getLocality() );
                    pojo.setSocietyId( 0 );
                    gisForm.setSocietyPojo( pojo );
                    LOGGER.info( "Found Society Pojo In List" );
                    gisForm.setPartnerList( CRMCacheManager.getNetworkPartners() );
                }
                //                gisForm.setProductList( CRMUtils.getProducts() );
                gisForm.setFieldTypeList( CRMUtils.getFieldTypes() );
            }
            else
            {
                LOGGER.info( "No Society Pojo List Found In Session " );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.info( "Error In Search Society Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CREATE_SOCIETY );
    }

    public ActionForward viewSociety( ActionMapping mapping,
                                      ActionForm inForm,
                                      HttpServletRequest request,
                                      HttpServletResponse response )
    {
        try
        {
            HttpSession httpSession = request.getSession( false );
            List<SocietyPojo> societyPojoList = (List<SocietyPojo>) httpSession
                    .getAttribute( IAppConstants.SOCIETY_POJO_LIST_SESS );
            SocietyPojo societyPojo = new SocietyPojo();
            GISForm gisForm = (GISForm) inForm;
            LOGGER.info( "ID" + request.getParameter( "societyId" ) );
            societyPojo.setSocietyId( ( Long.parseLong( request.getParameter( "societyId" ) ) ) );
            int pojoIndex = societyPojoList.indexOf( societyPojo );
            if ( pojoIndex >= 0 )
            {
                societyPojo = societyPojoList.get( pojoIndex );
                SocietyPojo viewPojo = new SocietyPojo();
                org.apache.commons.beanutils.PropertyUtils.copyProperties( viewPojo, societyPojo );
                viewPojo.getSocietyNetworkPartners().addAll( societyPojo.getSocietyNetworkPartners() );
                gisForm.setSocietyPojo( viewPojo );
                Set<SocietyNetworkPartnerPojo> societyNetworkPartnerPojos = new HashSet<SocietyNetworkPartnerPojo>();
                for ( SocietyNetworkPartnerPojo pojo : societyPojo.getSocietyNetworkPartners() )
                {
                    societyNetworkPartnerPojos.add( pojo );
                }
                gisForm.setSocietyNetworkPartners( societyNetworkPartnerPojos );
                GISMasterPojo gisMasterPojo = GISUtils.getGisMasterByAreaForViewSociety( societyPojo.getAreaId() );
                if ( StringUtils.isValidObj( gisMasterPojo ) )
                {
                    gisForm.setState( gisMasterPojo.getState() );
                    gisForm.setCity( gisMasterPojo.getCity() );
                    gisForm.setArea( gisMasterPojo.getArea() );
                }
            }
            gisForm.setPartnerPojoList( getMasterBMngr().getPartnersByStatus( CRMStatusCode.ACTIVE.getStatusCode() ) );
            LOGGER.info( "pojo index " + pojoIndex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in view User", ex );
        }
        return mapping.findForward( IActionForwardConstant.VIEW_SOCIETY );
    }

    public ActionForward changeSocietyStatus( ActionMapping mapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
    {
        LOGGER.info( "In Change Status" );
        ActionMessages messages = new ActionMessages();
        ActionMessages errros = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            GISForm gisForm = (GISForm) inForm;
            LOGGER.info( "Status" + gisForm.getStatus() );
            GisDto gisDto = getGisManagerImpl().changeSocietyStatus( gisForm,
                                                                     userDto.getCrmUserDetailsPojo().getUserId() );
            if ( StringUtils.isValidObj( gisDto ) )
            {
                String statusCode = gisDto.getStatusCode();
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                }
                else
                {
                    LOGGER.info( "statusCode::::::" + statusCode );
                    errros.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                LOGGER.info( "gis Dto null" );
                errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
        }
        catch ( Exception e )
        {
            errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errros );
        return mapping.findForward( IActionForwardConstant.CHANGE_SOCIETY_REDIRECT );
    }

    public ActionForward modifySocietyNPStatus( ActionMapping mapping,
                                                ActionForm inForm,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.CREATE_SOCIETY;
        LOGGER.info( "In Change Status Society Network Partner" );
        ActionMessages messages = new ActionMessages();
        ActionMessages errros = new ActionMessages();
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            GISForm gisForm = (GISForm) inForm;
            GisDto gisDto = getGisManagerImpl().changeSocietyNPStatus( gisForm,
                                                                       userDto.getCrmUserDetailsPojo().getUserId() );
            if ( StringUtils.isValidObj( gisDto ) )
            {
                String statusCode = gisDto.getStatusCode();
                if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    GISUtils.toRefresh( null );
                    target = IActionForwardConstant.SEARCH_GIS_FOR_CREATE_SOCIETY;
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                }
                else
                {
                    LOGGER.info( "statusCode::::::" + statusCode );
                    errros.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                LOGGER.info( "gis Dto null" );
                errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in modify status ", ex );
            errros.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errros );
        return mapping.findForward( target );
    }
}
