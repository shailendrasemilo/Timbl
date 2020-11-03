package com.np.tele.crm.qrc.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.qrc.bm.IQrcMassOutageManager;
import com.np.tele.crm.qrc.forms.QrcMassOutageForm;
import com.np.tele.crm.qrc.forms.QrcMassOutageHelper;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmMassOutageAreaPojo;
import com.np.tele.crm.service.client.CrmMassOutageCityPojo;
import com.np.tele.crm.service.client.CrmMassOutageDto;
import com.np.tele.crm.service.client.CrmMassOutageMastersPojo;
import com.np.tele.crm.service.client.CrmMassOutagePartnerPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmMassOutageSocietyPojo;
import com.np.tele.crm.service.client.CrmMassOutageStatePojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QrcMassOutageAction
    extends DispatchAction
{
    private static final Logger   LOGGER = Logger.getLogger( QrcMassOutageAction.class );
    private IQrcMassOutageManager massOutageManagerBm;
    private IMasterBMngr          masterDataBm;

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public IQrcMassOutageManager getMassOutageManagerBm()
    {
        return massOutageManagerBm;
    }

    public void setMassOutageManagerBm( IQrcMassOutageManager inMassOutageManagerBm )
    {
        massOutageManagerBm = inMassOutageManagerBm;
    }

    public ActionForward searchMassOutagePage( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.SEARCH_MASSOUTAGE_PAGE;
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        massOutageForm.setProductTypeList( CRMUtils.getProducts() );
        //massOutageForm.setNetworkPartners( CRMCacheManager.getNetworkPartners() );
        massOutageForm.setMassOutagePojo( new CrmMassOutagePojo() );
        //        massOutageForm.setStatePojos( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        return inMapping.findForward( target );
    }

    public ActionForward searchMassOutage( ActionMapping inMapping,
                                           ActionForm inForm,
                                           HttpServletRequest inRequest,
                                           HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.SEARCH_MASSOUTAGE_PAGE;
        ActionMessages errors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        HttpSession session = inRequest.getSession( false );
        String appMsg = (String) session.getAttribute( IAppConstants.APP_MESSAGE );
        if ( StringUtils.isNotBlank( appMsg ) )
        {
            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( appMsg ) );
            session.removeAttribute( IAppConstants.APP_MESSAGE );
        }
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        CrmMassOutagePojo massOutagePojo = massOutageForm.getMassOutagePojo();
        if ( StringUtils.isValidObj( massOutagePojo ) )
        {
            XMLGregorianCalendar outageStartTime = DateUtils.changeDateFormatWithTime( massOutagePojo
                    .getDisplayOutageStartTime() );
            XMLGregorianCalendar outageEtrTime = DateUtils.changeDateFormatWithTime( massOutagePojo
                    .getDisplayOutageEtrTime() );
            if ( StringUtils.isValidObj( outageStartTime ) )
            {
                massOutagePojo.setOutageStartTime( outageStartTime );
            }
            if ( StringUtils.isValidObj( outageEtrTime ) )
            {
                massOutagePojo.setOutageEtrTime( outageEtrTime );
            }
            CrmMassOutageDto crmMassOutageDto = getMassOutageManagerBm().searchMassOutage( massOutageForm );
            if ( StringUtils.equals( crmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( crmMassOutageDto.getMassOutagePojos().isEmpty() )
                {
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    massOutageForm.setMassOutagePojos( new ArrayList<CrmMassOutagePojo>() );
                }
                else
                {
                    massOutageForm.setMassOutagePojos( crmMassOutageDto.getMassOutagePojos() );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmMassOutageDto.getStatusCode() ) );
            }
        }
        //        massOutageForm.setProductTypeList( CRMUtils.getProducts() );
        //        massOutageForm.setStatePojos( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( target );
    }

    public ActionForward addMassOutagePage( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.ADD_MASSOUTAGE_PAGE;
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        Set<String> outageMastersSet = getAllMastersInAllActiveMassOutages( massOutageForm );
        massOutageForm.setReasons( QRCCacheManager.getQRCReasons( CRMRCAReason.QRC_MASSOUTAGE ) );
        massOutageForm.setMassOutagePojo( new CrmMassOutagePojo() );
        massOutageForm
                .setNetworkPartnersFbr( new ArrayList<PartnerPojo>( CRMCacheManager
                        .getPartnerbyType( CRMDisplayListConstants.NETWORK_PARTNER.getCode(),
                                           CRMDisplayListConstants.BROADBAND.getCode(),
                                           CRMStatusCode.ACTIVE.getStatusCode() ) ) );
        massOutageForm.setNetworkPartnersEoc( getPartnerList( CRMCacheManager
                .getPartnerbyType( CRMDisplayListConstants.NETWORK_PARTNER.getCode(),
                                   CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode(),
                                   CRMStatusCode.ACTIVE.getStatusCode() ) ) );
        massOutageForm.setNetworkPartnersRf( getPartnerList( CRMCacheManager
                .getPartnerbyType( CRMDisplayListConstants.NETWORK_PARTNER.getCode(),
                                   CRMDisplayListConstants.RADIO_FREQUENCY.getCode(),
                                   CRMStatusCode.ACTIVE.getStatusCode() ) ) );
        Iterator<PartnerPojo> itr = null;
        if ( CommonValidator.isValidCollection( massOutageForm.getNetworkPartnersFbr() ) )
        {
            List<StatePojo> gisStatePojo = GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA );
            List<StatePojo> partnerStates = null;
            itr = massOutageForm.getNetworkPartnersFbr().iterator();
            synchronized ( itr )
            {
                while ( itr.hasNext() )
                {
                    PartnerPojo partner = itr.next();
                    partnerStates = GISUtils.getGISByNP( gisStatePojo, partner.getPartnerId(),
                                                         CRMDisplayListConstants.BROADBAND.getCode() );
                    if ( CommonValidator.isValidCollection( partnerStates ) )
                    {
                        partner.getStatePojos().clear();
                        partner.getStatePojos().addAll( partnerStates );
                        LOGGER.info( partner.getPartnerName() + " has " + partnerStates.size() + " states" );
                    }
                    else
                    {
                        itr.remove();
                        LOGGER.info( partner.getPartnerName() + " has no state" );
                    }
                }
            }
        }
        List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = null;
        if ( CommonValidator.isValidCollection( massOutageForm.getNetworkPartnersEoc() ) )
        {
            itr = massOutageForm.getNetworkPartnersEoc().iterator();
            while ( itr.hasNext() )
            {
                PartnerPojo partner = itr.next();
                crmPartnerNetworkConfigPojos = getPartnerNetworkConfig( partner,
                                                                        CRMDisplayListConstants.ETHERNET_ON_CABLE
                                                                                .getCode() );
                if ( CommonValidator.isValidCollection( crmPartnerNetworkConfigPojos ) )
                {
                    synchronized ( crmPartnerNetworkConfigPojos )
                    {
                        partner.getPartnerNetworkConfigPojos().clear();
                        for ( Iterator<CrmPartnerNetworkConfigPojo> iterator = crmPartnerNetworkConfigPojos.iterator(); iterator
                                .hasNext(); )
                        {
                            CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = iterator.next();
                            if ( outageMastersSet.contains( crmPartnerNetworkConfigPojo.getMasterName() ) )
                            {
                                iterator.remove();
                            }
                        }
                        partner.getPartnerNetworkConfigPojos().addAll( crmPartnerNetworkConfigPojos );
                        if ( !CommonValidator.isValidCollection( partner.getPartnerNetworkConfigPojos() ) )
                        {
                            itr.remove();
                        }
                    }
                }
                else
                {
                    LOGGER.info( "partner '" + partner.getPartnerName() + "' is removed" );
                    itr.remove();
                }
                LOGGER.info( "EOC:: " + partner.getPartnerId() + " - " + partner.getPartnerName() + " has "
                        + partner.getPartnerNetworkConfigPojos().size() + " masters" );
            }
        }
        crmPartnerNetworkConfigPojos = null;
        if ( CommonValidator.isValidCollection( massOutageForm.getNetworkPartnersRf() ) )
        {
            itr = massOutageForm.getNetworkPartnersRf().iterator();
            while ( itr.hasNext() )
            {
                PartnerPojo partner = itr.next();
                crmPartnerNetworkConfigPojos = getPartnerNetworkConfig( partner,
                                                                        CRMDisplayListConstants.RADIO_FREQUENCY
                                                                                .getCode() );
                if ( CommonValidator.isValidCollection( crmPartnerNetworkConfigPojos ) )
                {
                    partner.getPartnerNetworkConfigPojos().clear();
                    for ( Iterator<CrmPartnerNetworkConfigPojo> iterator = crmPartnerNetworkConfigPojos.iterator(); iterator
                            .hasNext(); )
                    {
                        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = iterator.next();
                        if ( outageMastersSet.contains( crmPartnerNetworkConfigPojo.getMasterName() ) )
                        {
                            iterator.remove();
                        }
                    }
                    partner.getPartnerNetworkConfigPojos().addAll( crmPartnerNetworkConfigPojos );
                    if ( !CommonValidator.isValidCollection( partner.getPartnerNetworkConfigPojos() ) )
                    {
                        itr.remove();
                    }
                }
                else
                {
                    LOGGER.info( "partner '" + partner.getPartnerName() + "' is removed" );
                    itr.remove();
                }
                LOGGER.info( "RF:: " + partner.getPartnerId() + " - " + partner.getPartnerName() + " has "
                        + partner.getPartnerNetworkConfigPojos().size() + " masters" );
            }
        }
        LOGGER.info( "eoc partners:: " + massOutageForm.getNetworkPartnersEoc().size() );
        LOGGER.info( "fbr partners:: " + massOutageForm.getNetworkPartnersFbr().size() );
        LOGGER.info( "rf partners:: " + massOutageForm.getNetworkPartnersRf().size() );
        massOutageForm.setProductTypeList( CRMUtils.getProducts() );
        return inMapping.findForward( forward );
    }

    public ActionForward addMassOutage( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
    {
        LOGGER.info( "here in addMassOutage" );
        String forward = IActionForwardConstant.ADD_MASSOUTAGE_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        HttpSession session = inRequest.getSession( false );
        String crmUser = ( (CrmuserDetailsDto) session.getAttribute( IAppConstants.CRM_USER_OBJECT ) )
                .getCrmUserDetailsPojo().getUserId();
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        QrcMassOutageHelper.validateAddMassOutage( massOutageForm, errors );
        if ( errors.isEmpty() )
        {
            CrmMassOutagePojo massOutagePojo = massOutageForm.getMassOutagePojo();
            String outageLevel = "";
            List<CrmMassOutagePartnerPojo> crmMassOutagePartnerPojos = null;
            if ( StringUtils.equals( massOutagePojo.getServiceName(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                LOGGER.info( massOutageForm.getNetworkPartnersEoc().size() + " EOC partners" );
                crmMassOutagePartnerPojos = new ArrayList<CrmMassOutagePartnerPojo>();
                for ( PartnerPojo partner : massOutageForm.getNetworkPartnersEoc() )
                {
                    LOGGER.info( ">>>> " + partner.getPartnerName() );
                    outageLevel = fillPartnersForMasters( partner, crmMassOutagePartnerPojos, outageLevel );
                }
                LOGGER.info( crmMassOutagePartnerPojos.size() + " EOC partners selected" );
            }
            else if ( StringUtils.equals( massOutagePojo.getServiceName(),
                                          CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
            {
                crmMassOutagePartnerPojos = new ArrayList<CrmMassOutagePartnerPojo>();
                for ( PartnerPojo partner : massOutageForm.getNetworkPartnersRf() )
                {
                    outageLevel = fillPartnersForMasters( partner, crmMassOutagePartnerPojos, outageLevel );
                }
                LOGGER.info( crmMassOutagePartnerPojos.size() + " RF partners selected" );
            }
            else if ( StringUtils.equals( massOutagePojo.getServiceName(), CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                crmMassOutagePartnerPojos = new ArrayList<CrmMassOutagePartnerPojo>();
                for ( PartnerPojo partner : massOutageForm.getNetworkPartnersFbr() )
                {
                    outageLevel = fillPartnersForGis( partner, crmMassOutagePartnerPojos, outageLevel );
                }
                LOGGER.info( crmMassOutagePartnerPojos.size() + " Fiber Broadband partners selected" );
            }
            if ( CommonValidator.isValidCollection( crmMassOutagePartnerPojos ) )
            {
                massOutagePojo.getCrmMassOutagePartners().clear();
                massOutagePojo.getCrmMassOutagePartners().addAll( crmMassOutagePartnerPojos );
                massOutagePojo.setOutageLevel( StringUtils.isNotBlank( outageLevel ) ? outageLevel : "Partner" );
                massOutagePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                massOutagePojo.setCreatedBy( crmUser );
                CrmMassOutageDto massOutageDto = getMassOutageManagerBm().addMassOutage( massOutageForm );
                LOGGER.info( massOutageDto.getStatusCode() + " :: " + massOutageDto.getStatusDesc() );
                if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( massOutageDto.getStatusCode() ) );
                    session.setAttribute( IAppConstants.APP_MESSAGE, massOutageDto.getStatusCode() );
                    forward = IActionForwardConstant.SEARCH_MASSOUTAGE;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( massOutageDto.getStatusCode() ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_EMPTY_OUTAGE_PARTNERS_LIST ) );
            }
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest.getSession(), messages );
        return inMapping.findForward( forward );
    }

    public ActionForward editMassOutagePage( ActionMapping inMapping,
                                             ActionForm inForm,
                                             HttpServletRequest inRequest,
                                             HttpServletResponse inResponse )
    {
        LOGGER.info( "in editMassOutagePage" );
        String forward = IActionForwardConstant.SEARCH_MASSOUTAGE_PAGE;
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        massOutageForm.setReasons( QRCCacheManager.getQRCReasons( CRMRCAReason.QRC_MASSOUTAGE ) );
        ActionMessages errors = getErrors( inRequest );
        String outageId = inRequest.getParameter( "oid" );
        if ( StringUtils.isNotBlank( outageId ) )
        {
            CrmMassOutagePojo crmMassOutagePojo = new CrmMassOutagePojo();
            crmMassOutagePojo.setOutageId( Long.parseLong( outageId ) );
            CrmMassOutageDto crmMassOutageDto = getMassOutageManagerBm().viewMassOutage( crmMassOutagePojo );
            if ( StringUtils.equals( crmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                crmMassOutagePojo = crmMassOutageDto.getCrmMassOutagePojo();
                crmMassOutagePojo.setDisplayOutageStartTime( DateUtils.convertXmlCalToString( crmMassOutagePojo
                        .getOutageStartTime(), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                crmMassOutagePojo.setDisplayOutageEtrTime( DateUtils.convertXmlCalToString( crmMassOutagePojo
                        .getOutageEtrTime(), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                for ( CrmMassOutagePartnerPojo crmMassOutagePartner : crmMassOutagePojo.getCrmMassOutagePartners() )
                {
                    SortingComparator<CrmMassOutageMastersPojo> sortComparator = new SortingComparator<CrmMassOutageMastersPojo>( "masterName" );
                    Collections.sort( crmMassOutagePartner.getCrmMassOutageMasterses(), sortComparator );
                }
                massOutageForm.setMassOutagePojo( crmMassOutagePojo );
                forward = IActionForwardConstant.EDIT_MASSOUTAGE_PAGE;
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmMassOutageDto.getStatusCode() ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_ID ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward updateMassOutage( ActionMapping inMapping,
                                           ActionForm inForm,
                                           HttpServletRequest inRequest,
                                           HttpServletResponse inResponse )
    {
        LOGGER.info( "in updateMassOutage" );
        String forward = IActionForwardConstant.EDIT_MASSOUTAGE_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        HttpSession session = inRequest.getSession( false );
        String crmUser = ( (CrmuserDetailsDto) session.getAttribute( IAppConstants.CRM_USER_OBJECT ) )
                .getCrmUserDetailsPojo().getUserId();
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        CrmMassOutagePojo massOutagePojo = massOutageForm.getMassOutagePojo();
        XMLGregorianCalendar outageEtrTime = DateUtils.changeDateFormatWithTime( massOutagePojo
                .getDisplayOutageEtrTime() );
        if ( StringUtils.isValidObj( outageEtrTime ) )
        {
            if ( QrcMassOutageHelper.validETRTime( massOutagePojo ) )
                massOutagePojo.setOutageEtrTime( outageEtrTime );
            else
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_ETR_TIME ) );
        }
        if ( errors.isEmpty() )
        {
            CrmMassOutageDto massOutageDto = getMassOutageManagerBm().updateOutage( massOutagePojo, crmUser );
            if ( StringUtils.equals( massOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( massOutageDto.getStatusCode() ) );
                session.setAttribute( IAppConstants.APP_MESSAGE, massOutageDto.getStatusCode() );
                forward = IActionForwardConstant.SEARCH_MASSOUTAGE;
            }
            else
            {
                LOGGER.info( "update mass outage response :: " + massOutageDto.getStatusCode() + " :: "
                        + massOutageDto.getStatusDesc() );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( massOutageDto.getStatusCode() ) );
            }
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest.getSession(), messages );
        return inMapping.findForward( forward );
    }

    public ActionForward resolveMassOutagePage( ActionMapping inMapping,
                                                ActionForm inForm,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        LOGGER.info( "in resolve" );
        String forward = IActionForwardConstant.SEARCH_MASSOUTAGE_PAGE;
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        massOutageForm.setReasons( QRCCacheManager.getQRCReasons( CRMRCAReason.QRC_MASSOUTAGE ) );
        ActionMessages errors = getErrors( inRequest );
        String outageId = inRequest.getParameter( "oid" );
        if ( StringUtils.isNotBlank( outageId ) )
        {
            CrmMassOutagePojo crmMassOutagePojo = new CrmMassOutagePojo();
            crmMassOutagePojo.setOutageId( Long.parseLong( outageId ) );
            CrmMassOutageDto crmMassOutageDto = getMassOutageManagerBm().viewMassOutage( crmMassOutagePojo );
            if ( StringUtils.equals( crmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                crmMassOutagePojo = crmMassOutageDto.getCrmMassOutagePojo();
                crmMassOutagePojo.setDisplayOutageStartTime( DateUtils.convertXmlCalToString( crmMassOutagePojo
                        .getOutageStartTime(), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                crmMassOutagePojo.setDisplayOutageEtrTime( DateUtils.convertXmlCalToString( crmMassOutagePojo
                        .getOutageEtrTime(), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                for ( CrmMassOutagePartnerPojo crmMassOutagePartner : crmMassOutagePojo.getCrmMassOutagePartners() )
                {
                    SortingComparator<CrmMassOutageMastersPojo> sortComparator = new SortingComparator<CrmMassOutageMastersPojo>( "masterName" );
                    Collections.sort( crmMassOutagePartner.getCrmMassOutageMasterses(), sortComparator );
                }
                massOutageForm.setMassOutagePojo( crmMassOutagePojo );
                forward = IActionForwardConstant.RESOLVE_MASSOUTAGE_PAGE;
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmMassOutageDto.getStatusCode() ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_ID ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward resolveMassOutage( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
    {
        LOGGER.info( "in resolveMassOutage" );
        String forward = IActionForwardConstant.SEARCH_MASSOUTAGE_PAGE;
        QrcMassOutageForm massOutageForm = (QrcMassOutageForm) inForm;
        String crmUser = ( (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT ) ).getCrmUserDetailsPojo().getUserId();
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        HttpSession session = inRequest.getSession( true );
        CrmMassOutagePojo crmMassOutagePojo = massOutageForm.getMassOutagePojo();
        if ( StringUtils.isValidObj( crmMassOutagePojo ) )
        {
            CrmMassOutageDto crmMassOutageDto = getMassOutageManagerBm().resolveMassOutage( crmMassOutagePojo, crmUser );
            if ( StringUtils.equals( crmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                massOutageForm.setMassOutagePojo( crmMassOutageDto.getCrmMassOutagePojo() );
                forward = IActionForwardConstant.SEARCH_MASSOUTAGE;
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmMassOutageDto.getStatusCode() ) );
                session.setAttribute( IAppConstants.APP_MESSAGE, crmMassOutageDto.getStatusCode() );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmMassOutageDto.getStatusCode() ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_INVALID_OUTAGE_ID ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    private String fillPartnersForGis( PartnerPojo partner,
                                       List<CrmMassOutagePartnerPojo> crmMassOutagePartnerPojos,
                                       String outageLevel )
    {
        List<CrmMassOutageStatePojo> crmMassOutageStatePojos;
        List<CrmMassOutageCityPojo> crmMassOutageCityPojos;
        List<CrmMassOutageAreaPojo> crmMassOutageAreaPojos;
        List<CrmMassOutageSocietyPojo> crmMassOutageSocietyPojos;
        CrmMassOutagePartnerPojo massOutagePartnerPojo;
        CrmMassOutageStatePojo massOutageStatePojo;
        CrmMassOutageCityPojo massOutageCityPojo;
        CrmMassOutageAreaPojo massOutageAreaPojo;
        CrmMassOutageSocietyPojo massOutageSocietyPojo;
        crmMassOutageStatePojos = new ArrayList<CrmMassOutageStatePojo>();
        for ( StatePojo state : partner.getStatePojos() )
        {
            crmMassOutageCityPojos = new ArrayList<CrmMassOutageCityPojo>();
            for ( CityPojo city : state.getCities() )
            {
                crmMassOutageAreaPojos = new ArrayList<CrmMassOutageAreaPojo>();
                for ( AreaPojo area : city.getAreas() )
                {
                    crmMassOutageSocietyPojos = new ArrayList<CrmMassOutageSocietyPojo>();
                    for ( SocietyPojo society : area.getSocieties() )
                    {
                        if ( society.isEditable() || area.isEditable() )
                        {
                            massOutageSocietyPojo = new CrmMassOutageSocietyPojo();
                            massOutageSocietyPojo.setSocietyId( society.getSocietyId() );
                            massOutageSocietyPojo.setSociety( society.getSocietyName() );
                            massOutageSocietyPojo.setEnabled( society.isEditable() );
                            massOutageSocietyPojo.setStartPoint( area.isEditable() ? false : society.isEditable() );
                            crmMassOutageSocietyPojos.add( massOutageSocietyPojo );
                        }
                    }
                    if ( area.isEditable() || CommonValidator.isValidCollection( crmMassOutageSocietyPojos ) )
                    {
                        massOutageAreaPojo = new CrmMassOutageAreaPojo();
                        massOutageAreaPojo.setAreaId( area.getAreaId() );
                        massOutageAreaPojo.setArea( area.getArea() );
                        massOutageAreaPojo.setEnabled( area.isEditable() );
                        massOutageAreaPojo.setStartPoint( city.isEditable() ? false : area.isEditable() );
                        massOutageAreaPojo.getCrmMassOutageSocietyPojos().addAll( crmMassOutageSocietyPojos );
                        crmMassOutageAreaPojos.add( massOutageAreaPojo );
                    }
                }
                if ( city.isEditable() || CommonValidator.isValidCollection( crmMassOutageAreaPojos ) )
                {
                    massOutageCityPojo = new CrmMassOutageCityPojo();
                    massOutageCityPojo.setCityId( city.getCityId() );
                    massOutageCityPojo.setCityName( city.getCityName() );
                    massOutageCityPojo.setEnabled( city.isEditable() );
                    massOutageCityPojo.setStartPoint( state.isEditable() ? false : city.isEditable() );
                    massOutageCityPojo.getCrmMassOutageAreas().addAll( crmMassOutageAreaPojos );
                    crmMassOutageCityPojos.add( massOutageCityPojo );
                }
            }
            if ( state.isEditable() || CommonValidator.isValidCollection( crmMassOutageCityPojos ) )
            {
                massOutageStatePojo = new CrmMassOutageStatePojo();
                massOutageStatePojo.setStateId( state.getStateId() );
                massOutageStatePojo.setStateName( state.getStateName() );
                massOutageStatePojo.setEnabled( state.isEditable() );
                massOutageStatePojo.setStartPoint( partner.isEditable() ? false : state.isEditable() );
                massOutageStatePojo.getCrmMassOutageCities().addAll( crmMassOutageCityPojos );
                crmMassOutageStatePojos.add( massOutageStatePojo );
                outageLevel = StringUtils.equals( outageLevel, "Partner" ) ? outageLevel : "GIS";
            }
        }
        if ( partner.isEditable() || CommonValidator.isValidCollection( crmMassOutageStatePojos ) )
        {
            LOGGER.info( ">>>> " + partner.getPartnerName() + " has selected " + crmMassOutageStatePojos.size()
                    + " states" );
            massOutagePartnerPojo = new CrmMassOutagePartnerPojo();
            massOutagePartnerPojo.setPartnerId( partner.getPartnerId() );
            massOutagePartnerPojo.setPartnerName( partner.getPartnerName() );
            massOutagePartnerPojo.setEnabled( partner.isEditable() );
            massOutagePartnerPojo.setStartPoint( partner.isEditable() );
            massOutagePartnerPojo.getCrmMassOutageStates().addAll( crmMassOutageStatePojos );
            crmMassOutagePartnerPojos.add( massOutagePartnerPojo );
            outageLevel = partner.isEditable() ? "Partner" : outageLevel;
        }
        return outageLevel;
    }

    private String fillPartnersForMasters( PartnerPojo partner,
                                           List<CrmMassOutagePartnerPojo> crmMassOutagePartnerPojos,
                                           String outageLevel )
    {
        CrmMassOutageMastersPojo massOutageMastersPojo;
        List<CrmMassOutageMastersPojo> crmMassOutageMastersPojos = new ArrayList<CrmMassOutageMastersPojo>();
        for ( CrmPartnerNetworkConfigPojo partnerNetworkConfigPojo : partner.getPartnerNetworkConfigPojos() )
        {
            if ( partnerNetworkConfigPojo.isEditable() || partner.isEditable() )
            {
                massOutageMastersPojo = new CrmMassOutageMastersPojo();
                //                massOutageMastersPojo.setMasterId( partnerNetworkConfigPojo.getRecordId() );
                massOutageMastersPojo.setMasterName( partnerNetworkConfigPojo.getMasterName() );
                //                massOutageMastersPojo.setNasPortId( partnerNetworkConfigPojo.getNasPortId() );
                //                massOutageMastersPojo.setPoolName( partnerNetworkConfigPojo.getPoolName() );
                massOutageMastersPojo.setEnabled( partnerNetworkConfigPojo.isEditable() );
                massOutageMastersPojo.setStartPoint( partner.isEditable() ? false : partnerNetworkConfigPojo
                        .isEditable() );
                crmMassOutageMastersPojos.add( massOutageMastersPojo );
                outageLevel = StringUtils.equals( outageLevel, "Partner" ) ? outageLevel : "Master";
            }
        }
        if ( partner.isEditable() || CommonValidator.isValidCollection( crmMassOutageMastersPojos ) )
        {
            LOGGER.info( partner.getPartnerName() + " has selected " + crmMassOutageMastersPojos.size() + " masters" );
            CrmMassOutagePartnerPojo massOutagePartnerPojo = new CrmMassOutagePartnerPojo();
            massOutagePartnerPojo.setPartnerId( partner.getPartnerId() );
            massOutagePartnerPojo.setPartnerName( partner.getPartnerName() );
            massOutagePartnerPojo.setEnabled( partner.isEditable() );
            massOutagePartnerPojo.setStartPoint( partner.isEditable() );
            massOutagePartnerPojo.getCrmMassOutageMasterses().addAll( crmMassOutageMastersPojos );
            crmMassOutagePartnerPojos.add( massOutagePartnerPojo );
            outageLevel = partner.isEditable() ? "Partner" : outageLevel;
        }
        return outageLevel;
    }

    private List<CrmPartnerNetworkConfigPojo> getPartnerNetworkConfig( PartnerPojo partner, String businessType )
    {
        long partnerDetailsId = 0l;
        for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : partner.getCrmPartnerDetailses() )
        {
            if ( StringUtils.equals( crmPartnerDetailsPojo.getBussinessType(), businessType )
                    && StringUtils.equals( crmPartnerDetailsPojo.getPartnerType(),
                                           CRMDisplayListConstants.NETWORK_PARTNER.getCode() )
                    && StringUtils.equals( crmPartnerDetailsPojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                partnerDetailsId = crmPartnerDetailsPojo.getRecordId();
                LOGGER.info( "partner details id:: " + partnerDetailsId );
                break;
            }
        }
        MasterDataDto masterDataDto = new MasterDataDto();
        masterDataDto.setProduct( businessType );
        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
        crmPartnerNetworkConfigPojo.setPartnerDetailsId( partnerDetailsId );
        masterDataDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
        masterDataDto = getMasterDataBm().masterOperation( ServiceParameter.SEARCH.getParameter(),
                                                           CRMParameter.OPTION82.getParameter(), masterDataDto );
        LOGGER.info( masterDataDto.getStatusCode() + " :::: " + masterDataDto.getStatusDesc() );
        List<CrmPartnerNetworkConfigPojo> partnerNetworkConfigPojos = masterDataDto.getCrmPartnerNetworkConfigPojos();
        Set<String> masters = new HashSet<String>();
        Iterator<CrmPartnerNetworkConfigPojo> iterator = partnerNetworkConfigPojos.iterator();
        while ( iterator.hasNext() )
        {
            if ( !masters.add( iterator.next().getMasterName() ) )
            {
                iterator.remove();
            }
        }
        LOGGER.info( partner.getPartnerName() + " has masters " + masters.size() + " " + masters );
        SortingComparator<CrmPartnerNetworkConfigPojo> sortComparator = new SortingComparator<CrmPartnerNetworkConfigPojo>( "masterName" );
        Collections.sort( partnerNetworkConfigPojos, sortComparator );
        sortComparator = null;
        return partnerNetworkConfigPojos;
    }

    List<PartnerPojo> getPartnerList( List<PartnerPojo> inList )
    {
        List<PartnerPojo> finalList = new ArrayList<PartnerPojo>();
        PartnerPojo pojo = null;
        for ( PartnerPojo partnerPojo : inList )
        {
            pojo = new PartnerPojo();
            CRMUtils.copyAllProperties( pojo, partnerPojo );
            if ( !CommonValidator.isValidCollection( pojo.getCrmPartnerDetailses() ) )
            {
                pojo.getCrmPartnerDetailses().addAll( new ArrayList<CrmPartnerDetailsPojo>( partnerPojo
                                                              .getCrmPartnerDetailses() ) );
            }
            finalList.add( pojo );
        }
        return finalList;
    }

    Set<String> getAllMastersInAllActiveMassOutages( QrcMassOutageForm massOutageForm )
    {
        Set<String> outageMastersSet = new LinkedHashSet<String>();
        List<CrmMassOutageMastersPojo> crmMassOutageMastersPojolist = new ArrayList<CrmMassOutageMastersPojo>();
        CrmMassOutagePojo massOutagePojo = new CrmMassOutagePojo();
        massOutagePojo.setStatusCode( "A" );
        massOutageForm.setMassOutagePojo( massOutagePojo );
        CrmMassOutageDto crmMassOutageDto = getMassOutageManagerBm().searchMassOutage( massOutageForm );
        if ( StringUtils.equals( crmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            if ( !crmMassOutageDto.getMassOutagePojos().isEmpty() )
            {
                List<CrmMassOutagePojo> crmMassOutagePojoList = crmMassOutageDto.getMassOutagePojos();
                CrmMassOutageDto crmMassOutageDto2 = null;
                for ( CrmMassOutagePojo crmMassOutagePojo : crmMassOutagePojoList )
                {
                    crmMassOutageDto2 = getMassOutageManagerBm().viewMassOutage( crmMassOutagePojo );
                    if ( StringUtils.equals( crmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        CrmMassOutagePojo crmMassOutagePojoDb = crmMassOutageDto2.getCrmMassOutagePojo();
                        for ( CrmMassOutagePartnerPojo crmMassOutagePartner : crmMassOutagePojoDb
                                .getCrmMassOutagePartners() )
                        {
                            crmMassOutageMastersPojolist = crmMassOutagePartner.getCrmMassOutageMasterses();
                            for ( CrmMassOutageMastersPojo crmMassOutageMastersPojo : crmMassOutageMastersPojolist )
                            {
                                outageMastersSet.add( crmMassOutageMastersPojo.getMasterName() );
                            }
                        }
                    }
                }
            }
        }
        return outageMastersSet;
    }
}
