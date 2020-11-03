package com.np.tele.crm.masterdata.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
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
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.masterdata.bm.IPartnerManagement;
import com.np.tele.crm.masterdata.forms.MasterFormHelper;
import com.np.tele.crm.masterdata.forms.PartnerManagementForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class PartnerManagementAction
    extends DispatchAction
{
    private static final Logger LOGGER         = Logger.getLogger( PartnerManagementAction.class );
    private IPartnerManagement  managePartner  = null;
    private IMasterBMngr        masterBMngr    = null;
    private IGISManager         gisManagerImpl = null;

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public IPartnerManagement getManagePartner()
    {
        return managePartner;
    }

    public void setManagePartner( IPartnerManagement inManagePartner )
    {
        managePartner = inManagePartner;
    }

    public IGISManager getGisManagerImpl()
    {
        return gisManagerImpl;
    }

    public void setGisManagerImpl( IGISManager inGisManagerImpl )
    {
        gisManagerImpl = inGisManagerImpl;
    }

    public ActionForward addPartnerPage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
        throws Exception
    {
        try
        {
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            pmForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            SortingComparator<StatePojo> sortComparator = new SortingComparator<StatePojo>( "stateName" );
            Collections.sort( pmForm.getStatePojoList(), sortComparator );
            sortComparator = null;
            pmForm.setProducts( CRMUtils.getProducts() );
            pmForm.setPartnerTypeList( CRMUtils.getPartnerTypes() );
            pmForm.setStatusList( CRMUtils.getPartialStatusList() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in add partner Page", ex );
        }
        return inMapping.findForward( IActionForwardConstant.ADD_MODIFY_PARTNER );
    }

    public ActionForward addPartner( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.SEARCH_PARTNER_SUCCESSFULL;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            MasterDataDto masterDataDto = getManagePartner().addPartner( pmForm,
                                                                         userDto.getCrmUserDetailsPojo().getUserId() );
            String statusCode = masterDataDto.getStatusCode();
            LOGGER.info( "Status Code :" + statusCode );
            if ( StringUtils.isNotEmpty( statusCode ) )
            {
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Partner Management has been successfully save" );
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESSFULLY_PARTNER_CREATED ) );
                    forwardPage = IActionForwardConstant.SEARCH_PARTNER_SUCCESSFULL;
                    refreshPartnerCache();
                }
                else
                {
                    LOGGER.info( "Somthing has wrong in add partner management.... " );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.ADD_MODIFY_PARTNER;
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                forwardPage = IActionForwardConstant.ADD_MODIFY_PARTNER;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while add partner management", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward searchPartnerPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
        throws Exception
    {
        try
        {
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            pmForm.setStatusList( CRMUtils.getPartialStatusList() );
            pmForm.setPartnerTypeList( CRMUtils.getPartnerTypes() );
            pmForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            SortingComparator<StatePojo> sortComparator = new SortingComparator<StatePojo>( "stateName" );
            Collections.sort( pmForm.getStatePojoList(), sortComparator );
            sortComparator = null;
            pmForm.setProducts( CRMUtils.getProducts() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error in search partner Page", ex );
        }
        return inMapping.findForward( IActionForwardConstant.SEARCH_PARTNER );
    }

    public ActionForward searchPartner( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        String forwardPage = IActionForwardConstant.SEARCH_PARTNER;
        try
        {
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            pmForm.setProducts( CRMUtils.getProducts() );
            pmForm.setPartnerTypeList( CRMUtils.getPartnerTypes() );
            if ( StringUtils.isNotEmpty( pmForm.getPartnerState() ) )
            {
                pmForm.setCityPojoList( GISUtils.getActiveCities( IAppConstants.COUNTRY_INDIA, pmForm.getPartnerState() ) );
            }
            if ( StringUtils.isNotEmpty( pmForm.getBoardedDateTo() )
                    && StringUtils.isEmpty( pmForm.getBoardedDateFrom() ) )
            {
                LOGGER.info( "BoardedDateTo " + pmForm.getBoardedDateTo() );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_PM_BOARDED_FORM_DATE ) );
                saveErrors( inRequest, errors );
                return inMapping.findForward( IActionForwardConstant.SEARCH_PARTNER );
            }
            MasterDataDto masterDataDto = getManagePartner().searchPartner( pmForm );
            String statusCode = masterDataDto.getStatusCode();
            if ( StringUtils.isNotEmpty( statusCode ) )
            {
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    LOGGER.info( "Search Manage Partner Successfully.." );
                    if ( masterDataDto.getPartnerPojoList().isEmpty() )
                    {
                        LOGGER.info( "No record found" );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                    else
                    {
                        LOGGER.info( "size of partner List ::" + masterDataDto.getPartnerPojoList().size() );
                        setPartnerTypeNP( masterDataDto );
                        pmForm.setPartnerList( masterDataDto.getPartnerPojoList() );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            else
            {
                LOGGER.info( "Status code ::" + statusCode );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while search partner", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    private void setPartnerTypeNP( MasterDataDto masterDataDto )
    {
        for ( PartnerPojo partnerPojo : masterDataDto.getPartnerPojoList() )
        {
            for ( CrmPartnerDetailsPojo partnerDetailsPojo : partnerPojo.getCrmPartnerDetailses() )
            {
                if ( StringUtils.equals( partnerDetailsPojo.getPartnerType(),
                                         CRMDisplayListConstants.NETWORK_PARTNER.getCode() ) )
                {
                    partnerPojo.setPartnerType( CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
                    break;
                }
            }
        }
    }

    public ActionForward editPartner( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
        throws Exception
    {
        ConfigDto configDto = null;
        HttpSession httpSession = inRequest.getSession( false );
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        List<PartnerPojo> partnerPojoList = getMasterBMngr().getPartnersById( Long.toString( pmForm.getPartnerId() ) );
        if ( StringUtils.isValidObj( partnerPojoList ) && partnerPojoList.size() > 0 )
        {
            PartnerPojo partner = partnerPojoList.get( 0 );
            getManagePartner().setPartnerForm( pmForm, partner );
            retrivePartnerDetailMapping( pmForm, partner );
        }
        if ( StringUtils.isNotBlank( pmForm.getPartnerState() ) )
        {
            pmForm.setCityPojoList( GISUtils.getActiveCities( IAppConstants.COUNTRY_INDIA, pmForm.getPartnerState() ) );
        }
        configDto = getManagePartner().getActivityLogs( pmForm );
        httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
        if ( configDto.getAuditLogPojos().size() > 0 )
        {
            httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
        }
        LOGGER.info( "List of AuditLog Size ::" + configDto.getAuditLogPojos().size() );
        return inMapping.findForward( IActionForwardConstant.ADD_MODIFY_PARTNER );
    }

    private void retrivePartnerDetailMapping( PartnerManagementForm pmForm, PartnerPojo partnerPojo )
    {
        List<ContentPojo> serviceNames = new ArrayList<ContentPojo>();
        serviceNames.addAll( CRMUtils.getProducts() );
        List<ContentPojo> partnerTypes = new ArrayList<ContentPojo>();
        partnerTypes.addAll( CRMUtils.getPartnerTypes() );
        if ( CommonValidator.isValidCollection( partnerPojo.getCrmPartnerDetailses() ) )
        {
            Set<String> uniqueBussiness = new HashSet<String>();
            Set<String> uniquePartner = new HashSet<String>();
            for ( CrmPartnerDetailsPojo partnerDetailsPojo : partnerPojo.getCrmPartnerDetailses() )
            {
                if ( StringUtils.equals( partnerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    uniqueBussiness.add( partnerDetailsPojo.getBussinessType() );
                    uniquePartner.add( partnerDetailsPojo.getPartnerType() );
                }
            }
            for ( ContentPojo contentPojo : serviceNames )
            {
                if ( uniqueBussiness.contains( contentPojo.getContentValue() ) )
                {
                    contentPojo.setSelected( true );
                }
            }
            for ( ContentPojo contentPojo : partnerTypes )
            {
                if ( uniquePartner.contains( contentPojo.getContentValue() ) )
                {
                    contentPojo.setSelected( true );
                }
            }
            pmForm.setCrmPartnerDetailsPojosList( partnerPojo.getCrmPartnerDetailses() );
        }
        pmForm.setProducts( serviceNames );
        pmForm.setPartnerTypeList( partnerTypes );
    }

    public ActionForward viewPartner( ActionMapping inMapping,
                                      ActionForm inForm,
                                      HttpServletRequest inRequest,
                                      HttpServletResponse inResponse )
    {
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        ActionMessages messages = new ActionMessages();
        HttpSession httpSession = inRequest.getSession( false );
        ConfigDto configDto = null;
        try
        {
            List<PartnerPojo> partnerPojoList = pmForm.getPartnerList();
            LOGGER.info( "No of pojo find in list" + partnerPojoList.size() );
            pmForm.setProducts( CRMUtils.getProducts() );
            pmForm.setPartnerTypeList( CRMUtils.getPartnerTypes() );
            PartnerPojo partnerPojo = new PartnerPojo();
            partnerPojo.setPartnerId( ( Long.parseLong( inRequest.getParameter( "inPartnerId" ) ) ) );
            pmForm.setPartnerId( ( Long.parseLong( inRequest.getParameter( "inPartnerId" ) ) ) );
            int pojoIndex = partnerPojoList.indexOf( partnerPojo );
            if ( pojoIndex >= 0 )
            {
                partnerPojo = partnerPojoList.get( pojoIndex );
                LOGGER.info( "Partner Name :" + partnerPojo.getPartnerName() );
                pmForm.setPartnerPojo( partnerPojo );
            }
            retrivePartnerDetailMapping( pmForm, partnerPojo );
            LOGGER.info( "pojo index " + pojoIndex );
            configDto = getManagePartner().getActivityLogs( pmForm );
            httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
            if ( configDto.getAuditLogPojos().size() > 0 )
            {
                httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in view partner action", e );
            messages.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        return inMapping.findForward( IActionForwardConstant.VIEW_PARTNER_MANAGEMENT );
    }

    public ActionForward modifyPartner( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
        throws Exception
    {
        MasterDataDto masterDataDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forwardPage = IActionForwardConstant.SEARCH_PARTNER;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            masterDataDto = getManagePartner().modifyPartner( pmForm, userDto.getCrmUserDetailsPojo().getUserId() );
            String statusCode = masterDataDto.getStatusCode();
            LOGGER.info( "Status Code :" + statusCode );
            if ( StringUtils.isNotEmpty( statusCode ) )
            {
                if ( statusCode.equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Partner Management has been successfully modify" );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.SEARCH_PARTNER_SUCCESSFULL;
                    refreshPartnerCache();
                }
                else
                {
                    LOGGER.info( "Somthing has wrong in modify partner management.... " );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    forwardPage = IActionForwardConstant.ADD_MODIFY_PARTNER;
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                forwardPage = IActionForwardConstant.ADD_MODIFY_PARTNER;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while modify partner management", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    private void refreshPartnerCache()
    {
        CRMCacheManager.toRefresh( PartnerPojo.class.getSimpleName() );
        for ( ContentPojo cPojo : CRMUtils.getPartnerTypes() )
        {
            CRMCacheManager.toRefresh( cPojo.getContentValue() );
        }
    }

    public ActionForward assignServicePartnerPage( final ActionMapping inMapping,
                                                   final ActionForm inForm,
                                                   final HttpServletRequest inRequest,
                                                   final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        String forwardPage = IActionForwardConstant.ASSIGN_SERVICE_PARTNER;
        try
        {
            List<PartnerPojo> partnerPojos = getMasterBMngr().getPartnersById( String.valueOf( pmForm.getPartnerId() ) );
            pmForm.setPartnerName( partnerPojos.get( 0 ).getPartnerName() );
            if ( !CommonValidator.isValidCollection( pmForm.getProducts() ) )
            {
                List<ContentPojo> selectedProductType = getMasterBMngr()
                        .getProductsByPartnerId( String.valueOf( pmForm.getPartnerId() ),
                                                 CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
                LOGGER.info( "Selected product list size::" + selectedProductType.size() );
                pmForm.setProducts( selectedProductType );
            }
            if ( StringUtils.isBlank( pmForm.getBusinessTypeSP() )
                    && CommonValidator.isValidCollection( pmForm.getProducts() ) )
            {
                pmForm.setBusinessTypeSP( pmForm.getProducts().get( 0 ).getContentValue() );
            }
            if ( StringUtils.isNotBlank( pmForm.getBusinessTypeSP() ) )
            {
                List<CrmPartnerMappingPojo> crmPartnerMappingList = getMasterBMngr().getCrmPartnerMappingList( pmForm )
                        .getCrmPartnerMappingList();
                List<CrmPartnerMappingPojo> oldCrmPartnerMappingList = new ArrayList<CrmPartnerMappingPojo>();
                CrmPartnerMappingPojo oldCrmPartnerMappingPojo = null;
                for ( CrmPartnerMappingPojo crmPartnerMappingPojo : crmPartnerMappingList )
                {
                    oldCrmPartnerMappingPojo = new CrmPartnerMappingPojo();
                    org.apache.commons.beanutils.PropertyUtils.copyProperties( oldCrmPartnerMappingPojo,
                                                                               crmPartnerMappingPojo );
                    oldCrmPartnerMappingList.add( oldCrmPartnerMappingPojo );
                }
                pmForm.setOldCrmMappingList( oldCrmPartnerMappingList );
                LOGGER.info( "Current Mapping Size:" + crmPartnerMappingList.size() );
                LOGGER.info( "Old Mapping Size:" + oldCrmPartnerMappingList.size() );
                pmForm.setCrmMappingList( crmPartnerMappingList );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while assignServicePartnerPage", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward assignServicePartnerRow( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        String forwardPage = IActionForwardConstant.ASSIGN_SERVICE_PARTNER;
        try
        {
            List<PartnerPojo> servicePartnerPojoListByProduct = CRMCacheManager
                    .getPartnerbyType( CRMDisplayListConstants.SERVICE_PARTNER.getCode(), pmForm.getBusinessTypeSP(),
                                       CRMStatusCode.ACTIVE.getStatusCode() );
            if ( CommonValidator.isValidCollection( servicePartnerPojoListByProduct ) )
            {
                if ( !StringUtils.isValidObj( pmForm.getCrmMappingList() ) )
                {
                    pmForm.setCrmMappingList( new ArrayList<CrmPartnerMappingPojo>() );
                }
                CrmPartnerMappingPojo pojo = new CrmPartnerMappingPojo();
                pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                pojo.setEditable( true );
                pojo.setPartnerByNpId( new PartnerPojo() );
                pojo.setPartnerBySpId( new PartnerPojo() );
                Set<PartnerPojo> spList = new HashSet<PartnerPojo>();
                spList.addAll( servicePartnerPojoListByProduct );
                pmForm.setServicePartnerList( spList );
                pmForm.getCrmMappingList().add( 0, pojo );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.SERVICE_PARTNER_NOT_AVAILABLE ) );
            }
            if ( StringUtils.isValidObj( pmForm.getNetworkPartnerList() ) && pmForm.getNetworkPartnerList().isEmpty() )
            {
                pmForm.setNetworkPartnerList( CRMCacheManager.getNetworkPartners() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while assignServicePartnerRow", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward deletePartnerRow( ActionMapping inMapping,
                                           ActionForm inForm,
                                           HttpServletRequest inRequest,
                                           HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            LOGGER.info( "Row counter" + pmForm.getRowCounter() );
            pmForm.getCrmMappingList().remove( pmForm.getRowCounter() );
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.info( "Error in DeleteParameterGroupRow Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.ASSIGN_SERVICE_PARTNER );
    }

    public ActionForward assignServicePartner( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        String forwardPage = IActionForwardConstant.ASSIGN_PARTNER_REDIRECT;
        try
        {
            if ( CommonValidator.isValidCollection( pmForm.getCrmMappingList() ) )
            {
                boolean status = uniqueServiceMapping( pmForm.getCrmMappingList() );
                if ( status )
                {
                    MasterDataDto masterDto = getMasterBMngr().npToSpMapping( pmForm );
                    String statusCode = masterDto.getStatusCode();
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    }
                    else
                    {
                        LOGGER.info( "Response status  desc::" + masterDto.getStatusDesc()
                                + "Response status code  :: " + statusCode );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_SERVICE_PARTNER ) );
                    forwardPage = IActionForwardConstant.ASSIGN_SERVICE_PARTNER;
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_ONE_PARTNER_REQUIRED ) );
                forwardPage = IActionForwardConstant.ASSIGN_SERVICE_PARTNER;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while assignServicePartner", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    private static boolean uniqueServiceMapping( List<CrmPartnerMappingPojo> crmMappingList )
    {
        Set<Long> SpIdSet = new HashSet<Long>();
        for ( CrmPartnerMappingPojo pojo : crmMappingList )
        {
            LOGGER.info( "Partner Id" + pojo.getPartnerBySpId().getPartnerId() );
            if ( !SpIdSet.add( pojo.getPartnerBySpId().getPartnerId() ) )
            {
                SpIdSet = null;
                return false;
            }
        }
        return true;
    }

    private static boolean validateEmptyServicePartner( List<CrmPartnerMappingPojo> crmMappingList )
    {
        return true;
    }

    public ActionForward addNPMobilePage( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        List<CrmNpMobilePojo> oldCrmNpMobilePojos = new ArrayList<CrmNpMobilePojo>();
        CrmNpMobilePojo oldCrmNpMobilePojo = null;
        List<CrmNpMobilePojo> crmNpMobilePojos = null;
        try
        {
            crmNpMobilePojos = getMasterBMngr().getCrmNpMobileList( pmForm ).getCrmNpMobilePojos();
            if ( CommonValidator.isValidCollection( crmNpMobilePojos ) )
            {
                for ( CrmNpMobilePojo npMobilePojo : crmNpMobilePojos )
                {
                    oldCrmNpMobilePojo = new CrmNpMobilePojo();
                    org.apache.commons.beanutils.PropertyUtils.copyProperties( oldCrmNpMobilePojo, npMobilePojo );
                    oldCrmNpMobilePojos.add( oldCrmNpMobilePojo );
                }
                pmForm.setOldCrmNpMobileList( oldCrmNpMobilePojos );
            }
            pmForm.setCrmNpMobileList( crmNpMobilePojos );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while addNPMobilePage", ex );
        }
        return inMapping.findForward( IActionForwardConstant.ADD_NP_MOBILE_PAGE );
    }

    public ActionForward deleteAddNPMobileRow( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
            LOGGER.info( "Row counter" + pmForm.getRowCounter() );
            pmForm.getCrmNpMobileList().remove( pmForm.getRowCounter() );
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            LOGGER.info( "Error in deleteAddNPMobileRow Method" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.ADD_NP_MOBILE_PAGE );
    }

    public ActionForward addNPMobileNewRow( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
        throws Exception
    {
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        if ( !StringUtils.isValidObj( pmForm.getCrmNpMobileList() ) )
        {
            pmForm.setCrmNpMobileList( new ArrayList<CrmNpMobilePojo>() );
        }
        CrmNpMobilePojo pojo = new CrmNpMobilePojo();
        pojo.setEditable( true );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        pmForm.getCrmNpMobileList().add( 0, pojo );
        return inMapping.findForward( IActionForwardConstant.ADD_NP_MOBILE_PAGE );
    }

    public ActionForward addNPMobile( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
        throws Exception
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        PartnerManagementForm pmForm = (PartnerManagementForm) inForm;
        String forwardPage = IActionForwardConstant.ADD_NP_MOBILE_REDIRECT;
        try
        {
            if ( !MasterFormHelper.validateNPMobile( errors, pmForm ).isEmpty() )
            {
                LOGGER.info( "Forward Search page" );
                forwardPage = IActionForwardConstant.ADD_NP_MOBILE_PAGE;
            }
            else
            {
                if ( CommonValidator.isValidCollection( pmForm.getCrmNpMobileList() ) )
                {
                    MasterDataDto masterDto = getMasterBMngr().addMultipleMobileWithNP( pmForm );
                    String statusCode = masterDto.getStatusCode();
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    }
                    else
                    {
                        LOGGER.info( "Response status  desc::" + masterDto.getStatusDesc()
                                + "Response status code  :: " + statusCode );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_PM_PARTNER_MOBILE ) );
                    forwardPage = IActionForwardConstant.ADD_NP_MOBILE_PAGE;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while assignServicePartner", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }
}
