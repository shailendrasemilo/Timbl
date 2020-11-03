package com.np.tele.crm.cap.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

import com.itextpdf.text.DocumentException;
import com.np.tele.crm.cap.bm.ICrmCapManager;
import com.np.tele.crm.cap.form.CrmCapForm;
import com.np.tele.crm.cap.form.CrmCapFormHelper;
import com.np.tele.crm.cap.form.CrmNPCrfFormHelper;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.exceptions.NextraCrmException;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustAadharNumberPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class CrmCapAction
    extends DispatchAction
{
    private static final Logger LOGGER           = Logger.getLogger( CrmCapAction.class );
    private ICrmCapManager      capManagerImpl   = null;
    private IMasterBMngr        masterBMngr      = null;
    private ICrmConfigManager   crmConfigManager = null;

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr masterBMngr )
    {
        this.masterBMngr = masterBMngr;
    }

    public ICrmCapManager getCapManagerImpl()
    {
        return capManagerImpl;
    }

    public void setCapManagerImpl( ICrmCapManager capManagerImpl )
    {
        this.capManagerImpl = capManagerImpl;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ActionForward searchCrfPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        setUserAssociations( userDto, crmCapForm );
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        crmCustomerDetailsPojo.setServiceType( CRMDisplayListConstants.PRE_PAID.getCode() );
        crmCapForm.setCustomerDetailsPojo( crmCustomerDetailsPojo );
        if ( CommonValidator.isValidCollection( userDto.getUserAssociatedServices() ) )
        {
            List<ContentPojo> userServices = new ArrayList<ContentPojo>();
            ContentPojo contentPojo = null;
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PRODUCT.getParameter() )
                        && userDto.getUserAssociatedServices().contains( crmDisplayConst.getCode() ) )
                {
                    contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    /*switch ( crmDisplayConst )
                    {
                        case BROADBAND:
                            contentPojo = new ContentPojo( "FTTX", crmDisplayConst.getCode() );
                            break;
                        case ETHERNET_ON_CABLE:
                            contentPojo = new ContentPojo( "FTTN", crmDisplayConst.getCode() );
                            break;
                     case RADIO_FREQUENCY:
                         contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                         break;
                    }*/
                    userServices.add( contentPojo );
                }
            }
            crmCapForm.setProductTypeList( userServices );
        }
        else
        {
            //            List<ContentPojo> allServices = new ArrayList<ContentPojo>();
            //            ContentPojo contentPojo = null;
            //            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            //            {
            //                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PRODUCT.getParameter() ) )
            //                {
            //                    switch ( crmDisplayConst )
            //                    {
            //                        case BROADBAND:
            //                            contentPojo = new ContentPojo( "FTTX", crmDisplayConst.getCode() );
            //                            break;
            //                        case ETHERNET_ON_CABLE:
            //                            contentPojo = new ContentPojo( "FTTN", crmDisplayConst.getCode() );
            //                            break;
            //                    /* case RADIO_FREQUENCY:
            //                         contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
            //                         break;*/
            //                    }
            //                    allServices.add( contentPojo );
            //                }
            //            }
            crmCapForm.setProductTypeList( CRMUtils.getProducts() );
        }
        crmCapForm.setServiceTypes( CRMUtils.getServiceTypesList() );
        return inMapping.findForward( IActionForwardConstant.SEARCH_CRF_PAGE );
    }

    private void setUserAssociations( CrmuserDetailsDto inUserDto, CrmCapForm inCrmCapForm )
    {
        inCrmCapForm.setUserAssociatedServices( inUserDto.getUserAssociatedServices() );
        inCrmCapForm.setUserAssociatedPartners( inUserDto.getUserAssociatedPartners() );
    }

    public ActionForward searchCrf( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ActionMessages messages = getMessages( inRequest );
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        ActionMessages errors = getErrors( inRequest );
        String forward = IActionForwardConstant.SEARCH_CRF_PAGE;
        try
        {
            if ( !CrmCapFormHelper.validateSearchCrf( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "Forward Search page" );
                forward = IActionForwardConstant.SEARCH_CRF_PAGE;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                setUserAssociations( userDto, crmCapForm );
                String status = getMasterBMngr().validateCRFInMaster( crmCapForm.getCustomerDetailsPojo().getCrfId()
                                                                              .toUpperCase(),
                                                                      crmCapForm.getCustomerDetailsPojo().getProduct() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), status ) )
                {
                    crmCapDto = getCapManagerImpl().searchCrfDetails( crmCapForm,
                                                                      userDto.getCrmUserDetailsPojo().getUserId() );
                    String statusCode = crmCapDto.getStatusCode();
                    LOGGER.info( "Status Code for CRF Search: " + statusCode );
                    if ( !StringUtils.isEmpty( statusCode ) )
                    {
                        LOGGER.info( statusCode + ":: " + crmCapDto.getStatusDesc() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode )
                                || StringUtils.equals( CRMServiceCode.CRM302.getStatusCode(), statusCode )
                                || StringUtils.equals( CRMServiceCode.CRM305.getStatusCode(), statusCode ) )
                        {
                            boolean toCreate = CommonUtils.isAllowed( inRequest.getSession( false ), "create_ina" );
                            if ( toCreate )
                            {
                                crmCapForm.setCrfTabId( "#customerBasicInformation" );
                                getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                                getCapManagerImpl().setInstallationGISDetails( crmCapForm, true );
                                getCapManagerImpl().setBillingGISDetails( crmCapForm, true );
                                forward = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;
                            }
                            else
                            {
                                errors.add( IAppConstants.APP_ERROR,
                                            new ActionMessage( CRMServiceCode.CRM312.getStatusCode() ) );
                            }
                        }
                        else if ( StringUtils.equals( CRMServiceCode.CRM304.getStatusCode(), statusCode )
                                || StringUtils.equals( CRMServiceCode.CRM310.getStatusCode(), statusCode ) )
                        {
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                        }
                        else if ( StringUtils.equals( CRMServiceCode.CRM306.getStatusCode(), statusCode )
                                || StringUtils.equals( CRMServiceCode.CRM311.getStatusCode(), statusCode ) )
                        {
                            crmCapForm.setCrfTabId( "#customerBasicInformation" );
                            getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                            getCapManagerImpl().setInstallationGISDetails( crmCapForm, true );
                            getCapManagerImpl().setBillingGISDetails( crmCapForm, false );
                            crmCapForm.setHeading( true );
                            forward = IActionForwardConstant.VIEW_CRF_PAGE;
                        }
                        else
                        {
                            LOGGER.info( "Somthing has wrong in Search CRF ID" );
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Status code found null" );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
                else if ( StringUtils.equals( CRMServiceCode.CRM996.getStatusCode(), status ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM301.getStatusCode() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( status ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured search CAF", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                crmCapForm.setProductTypeList( CRMUtils.getProducts() );
                crmCapForm.setServiceTypes( CRMUtils.getServiceTypesList() );
            }
            saveMessages( inRequest, messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward saveCustomerBasicInfo( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forwardPage = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;
        try
        {
            LOGGER.info( "Tab Open No: " + crmCapForm.getCrfTabId() );
            if ( !CrmCapFormHelper
                    .validateIandAForm( IAppConstants.METHOD_SAVE_CUSTOMER_BASIC_INFO, errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "Error in I & A page ..." );
                forwardPage = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                setUserAssociations( userDto, crmCapForm );
                crmCapDto = getCapManagerImpl().saveCustomerBasicInfo( crmCapForm,
                                                                       userDto.getCrmUserDetailsPojo().getUserId() );
                String statusCode = crmCapDto.getStatusCode();
                if ( StringUtils.isNotEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        HttpSession session = inRequest.getSession( true );
                        session.setAttribute( "SuccessMsg", "Succesffully forwarded CRF to " + crmCapDto.getToStage() );
                        forwardPage = IActionForwardConstant.INBOX;
                    }
                    else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM995.getStatusCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                    else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM611.getStatusCode() ) )
                    {
                        LOGGER.info( "Receipt No. is already in use, Duplicate Receipt No. not allowed." );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getBillingErrorCode() ) );
                    }
                    else
                    {
                        LOGGER.info( "Somthing has wrong in Save CRF Form" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in save customer info", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCapForm.setAadharNumberPojo( crmCapForm.getAadharNumberPojo() );
                customerDetailsPojo.setProduct( crmCapForm.getCustomerDetailsPojo().getProduct() );
                customerDetailsPojo.setServiceType( crmCapForm.getCustomerDetailsPojo().getServiceType() );
                crmCapDto.setCustomerDetailsPojo( customerDetailsPojo );
                getCapManagerImpl().setActivationPlans( crmCapDto, crmCapForm );
                getCapManagerImpl().setPredefineList( crmCapDto, crmCapForm, crmCapForm.getAddressProofs(),
                                                      crmCapForm.getDocuments() );
            }
            saveMessages( inRequest, messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forwardPage );
    }

    public ActionForward checkFeasibility( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        crmCapForm.setStatePojoList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        String product = inRequest.getParameter( "Product" );
        if ( CommonValidator.isValidCollection( crmCapForm.getStatePojoList() ) )
        {
            crmCapForm.setCityList( GISUtils.getCities( crmCapForm.getStatePojoList(), crmCapForm.getState() ) );
        }
        if ( CommonValidator.isValidCollection( crmCapForm.getCityList() )
                && StringUtils.isNotEmpty( crmCapForm.getCity() ) )
        {
            crmCapForm.setAreaList( GISUtils.getAreas( crmCapForm.getCityList(), crmCapForm.getCity() ) );
        }
        /*if ( CommonValidator.isValidCollection( crmCapForm.getAreaList() )
                && StringUtils.isNotEmpty( crmCapForm.getArea() ) )
        {
            crmCapForm.setLocalityList( GISUtils.getLocalities( crmCapForm.getAreaList(), crmCapForm.getArea() ) );
        }*/
        if ( CommonValidator.isValidCollection( crmCapForm.getAreaList() )
                && StringUtils.isNotEmpty( crmCapForm.getArea() ) && StringUtils.isNotBlank( product ) )
        {
            crmCapForm
                    .setSocietyList( GISUtils.getSocieties( crmCapForm.getAreaList(), crmCapForm.getArea(), product ) );
            if ( CommonValidator.isValidCollection( crmCapForm.getSocietyList() )
                    && StringUtils.isNotBlank( crmCapForm.getLocality() ) )
            {
                if ( StringUtils.isNotBlank( crmCapForm.getSociety() ) )
                {
                    crmCapForm.setFeasiblelocSoc( crmCapForm.getLocality() + " - " + crmCapForm.getSociety() );
                }
                else
                {
                    crmCapForm.setFeasiblelocSoc( crmCapForm.getLocality() );
                }
            }
        }
        LOGGER.info( "Product Name:" + product );
        if ( StringUtils.isNotBlank( product ) )
            crmCapForm.setHiddenProductType( product );
        LOGGER.info( "Size of state :" + crmCapForm.getStatePojoList().size() );
        return inMapping.findForward( IActionForwardConstant.CHECK_FEASIBILITY );
    }

    public ActionForward submitCRFDetails( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = new CrmCapDto();
        ConfigDto configDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forwardPage = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;;
        try
        {
            if ( !CrmCapFormHelper.validateIandAForm( IAppConstants.SUBMIT_CRF_DETAILS, errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "Submit CAF Details page..." );
                forwardPage = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                setUserAssociations( userDto, crmCapForm );
                String crfStage = crmCapForm.getCustomerDetailsPojo().getCrfStage();
                configDto = getCapManagerImpl().listNPUploadedDoc( CRMRequestType.INA.getRequestCode(),
                                                                   crmCapForm.getCustomerDetailsPojo().getCrfId() );
                if ( CommonValidator.isValidCollection( configDto.getPodUploadedList() )
                        && configDto.getPodUploadedList().size() > 0
                        && ( configDto.getPodUploadedList().contains( "CAF" )
                                || configDto.getPodUploadedList().contains( "POA" ) || configDto.getPodUploadedList()
                                .contains( "POI" ) ) )
                {
                    crmCapDto = getCapManagerImpl().submitCRFDetails( crmCapForm,
                                                                      userDto.getCrmUserDetailsPojo().getUserId() );
                    String statusCode = crmCapDto.getStatusCode();
                    if ( StringUtils.isNotEmpty( statusCode ) )
                    {
                        if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                            HttpSession session = inRequest.getSession( true );
                            session.setAttribute( "SuccessMsg",
                                                  "Succesffully forwarded CRF to " + crmCapDto.getToStage() );
                            forwardPage = IActionForwardConstant.INBOX;
                        }
                        else
                        {
                            if ( StringUtils.isNotEmpty( crmCapDto.getBillingErrorCode() )
                                    && StringUtils.isEmpty( crfStage ) )
                            {
                                getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                            }
                            LOGGER.info( "Somthing has wrong in Save CRF Form" );
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode(),
                                                                                    crmCapDto.getBillingErrorCode() ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Status code found null" );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_FILE_UPLOAD_DOCUMENTS, "CAF, POA or POI" ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in submit CAF Deatails :", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                getCapManagerImpl().setUserPojos( crmCapForm );
                crmCapForm.setAadharNumberPojo( crmCapForm.getAadharNumberPojo() );
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                customerDetailsPojo.setProduct( crmCapForm.getCustomerDetailsPojo().getProduct() );
                customerDetailsPojo.setServiceType( crmCapForm.getCustomerDetailsPojo().getServiceType() );
                crmCapDto.setCustomerDetailsPojo( customerDetailsPojo );
                getCapManagerImpl().setActivationPlans( crmCapDto, crmCapForm );
                getCapManagerImpl().setPredefineList( crmCapDto, crmCapForm, crmCapForm.getAddressProofs(),
                                                      crmCapForm.getDocuments() );
            }
            saveMessages( inRequest, messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forwardPage );
    }

    public ActionForward saveValidateCRFEntry( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forwardPage = IActionForwardConstant.CRF_VALIDATE_ENTRY_PAGE;;
        try
        {
            crmCapDto = new CrmCapDto();
            if ( !CrmNPCrfFormHelper.validateCRFEntry( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "CRF Validate Entry Page" );
                forwardPage = IActionForwardConstant.CRF_VALIDATE_ENTRY_PAGE;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                crmCapDto = getCapManagerImpl().saveValidatedCRFEntry( crmCapForm,
                                                                       userDto.getCrmUserDetailsPojo().getUserId() );
                String statusCode = crmCapDto.getStatusCode();
                if ( !StringUtils.isEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( StringUtils.equals( crmCapForm.getRemarksPojo().getActions(), IAppConstants.APPROVE ) )
                        {
                            if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), crmCapForm
                                    .getCustomerDetailsPojo().getCrfStage() )
                                    || StringUtils.equals( CRMOperationStages.NP_REJECT.getCode(), crmCapForm
                                            .getCustomerDetailsPojo().getCrfStage() ) )
                            {
                                if ( crmCapForm.getCustomerDetailsPojo().getProduct()
                                        .equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                                {
                                    PartnerPojo partner = CommonUtils.getPartnerById( StringUtils
                                            .numericValue( crmCapForm.getCustomerDetailsPojo().getNpId() + "" ) );
                                    if ( StringUtils.isValidObj( partner ) )
                                    {
                                        getCrmConfigManager().getMacAddressByNP( partner.getCrmPartnerCode(),
                                                                                 crmCapForm.getCustomerDetailsPojo()
                                                                                         .getCustomerId(),
                                                                                 CRMRequestType.INA.getRequestCode() );
                                    }
                                }
                            }
                        }
                        LOGGER.info( "Succesffully forwarded CRF to :: " + crmCapDto.getToStage() );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        HttpSession session = inRequest.getSession( true );
                        session.setAttribute( "SuccessMsg", "Succesffully forwarded CRF to " + crmCapDto.getToStage() );
                        forwardPage = IActionForwardConstant.INBOX;
                    }
                    else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM201.getStatusCode() ) )
                    {
                        LOGGER.info( "No User Present In DataBase For Forwading Stage:: " + crmCapDto.getToStage() );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode, crmCapDto.getToStage() ) );
                    }
                    else
                    {
                        LOGGER.info( "Something has wrong in Save Validate CRF Entry" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in validate CRF Entry", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                crmCapForm.setRejectionReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_FT_REJECTION ) );
                crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                     .getRecordId(), null, null );
                if ( StringUtils.isValidObj( crmCapDto )
                        && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                }
                getCapManagerImpl().setNetworkPartnersByProduct( crmCapForm,
                                                                 crmCapForm.getCustomerDetailsPojo().getProduct() );
            }
            saveMessages( inRequest, messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forwardPage );
    }

    public ActionForward saveNetworkDetails( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forward = IActionForwardConstant.NETWORK_DETAILS_PAGE;
        try
        {
            crmCapDto = new CrmCapDto();
            if ( !CrmNPCrfFormHelper.validateNetworkDetails( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "Network Detail Page" );
                forward = IActionForwardConstant.NETWORK_DETAILS_PAGE;
            }
            else if ( StringUtils.equals( crmCapForm.getRemarksPojo().getActions(), IAppConstants.APPROVE )
                    && !CrmNPCrfFormHelper.validateNetConfDetail( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "Network Inventory Detail Page" );
                forward = IActionForwardConstant.NETWORK_DETAILS_PAGE;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                setUserAssociations( userDto, crmCapForm );
                crmCapDto = getCapManagerImpl().saveNetworkDetails( crmCapForm,
                                                                    userDto.getCrmUserDetailsPojo().getUserId() );
                String statusCode = crmCapDto.getStatusCode();
                LOGGER.info( "Status Code:: " + statusCode );
                if ( !StringUtils.isEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        HttpSession session = inRequest.getSession( true );
                        session.setAttribute( "SuccessMsg", "Succesffully forwarded CRF to " + crmCapDto.getToStage() );
                        forward = IActionForwardConstant.INBOX;
                    }
                    //201
                    else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM201.getStatusCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getToStage() ) );
                    }
                    else if ( StringUtils.isNotBlank( crmCapDto.getBillingErrorCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getBillingErrorCode() ) );
                    }
                    else
                    {
                        LOGGER.info( "Somthing has wrong in save network Details" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in save network Details", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                crmCapForm.setRejectionReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_SP_REJECTION ) );
                crmCapForm.setCancellationReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
                crmCapForm.setRefuselReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_CUSTOMER_REFUSAL ) );
                crmCapForm.setCrmMappingList( ( getCapManagerImpl().getServicePartnerList( crmCapForm ) ) );
                crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                     .getRecordId(), null, null );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                }
            }
            saveErrors( inRequest, errors );
            saveMessages( inRequest, messages );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward networkInventoryDetailsPage( final ActionMapping inMapping,
                                                      final ActionForm inForm,
                                                      final HttpServletRequest inRequest,
                                                      final HttpServletResponse inResponse )
    {
        CrmNetworkConfigurationsPojo crmNetworkConfigurationsPojo = null;
        try
        {
            CrmCapForm crmCapForm = (CrmCapForm) inForm;
            String npID = inRequest.getParameter( "NPID" );
            String product = inRequest.getParameter( "Product" );
            LOGGER.info( "NP ID::" + npID + "Product :" + product );
            List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
            if ( StringUtils.isValidObj( crmCapForm.getTempNetworkConfigurations() ) )
            {
                crmNetworkConfigurationsPojo = crmCapForm.getTempNetworkConfigurations();
            }
            else
            {
                crmNetworkConfigurationsPojo = new CrmNetworkConfigurationsPojo();
            }
            crmNetworkConfigurationsPojo.setServiceModel( "fttn" );
            crmCapForm.setCrmNetworkConfigurations( crmNetworkConfigurationsPojo );
            // crmCapForm.setProductTypeList( CRMUtils.getProducts() );
            crmCapForm.setProductType( product );
            crmCapForm.setOltTypeList( CRMUtils.getOltType() );
            if ( !StringUtils.equals( product, CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                LOGGER.info( "networkInventoryDetailsPage method for RF/EOC" );
                crmPartnerNetworkConfigPojos = getCapManagerImpl().getMasterNameList( Long.parseLong( npID ), product );
                if ( CommonValidator.isValidCollection( crmPartnerNetworkConfigPojos ) )
                {
                    crmCapForm.setMasterNameList( crmPartnerNetworkConfigPojos );
                    Set<String> masterNames = new TreeSet<String>();
                    for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo : crmPartnerNetworkConfigPojos )
                    {
                        masterNames.add( crmPartnerNetworkConfigPojo.getMasterName() );
                    }
                    crmCapForm.setMasterNames( new ArrayList<String>( masterNames ) );
                    if ( !StringUtils.isValidObj( crmCapForm.getCrmPartnerNetworkConfig() ) )
                    {
                        crmCapForm.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
                    }
                    crmCapForm.getCrmPartnerNetworkConfig().setPartnerDetailsId( crmPartnerNetworkConfigPojos.get( 0 )
                                                                                         .getPartnerDetailsId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in network configuration details", ex );
        }
        return inMapping.findForward( IActionForwardConstant.NETWORK_CONFIGURATION_DETAILS_PAGE );
    }

    public ActionForward saveMapMacIdDetails( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
        try
        {
            crmCapDto = new CrmCapDto();
            if ( !CrmNPCrfFormHelper.validateMapMacIdDetails( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "map mac details page" );
                forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                setUserAssociations( userDto, crmCapForm );
                crmCapDto = getCapManagerImpl().saveMapMacIdDetails( crmCapForm,
                                                                     userDto.getCrmUserDetailsPojo().getUserId() );
                String statusCode = crmCapDto.getStatusCode();
                LOGGER.info( "Status Code:: " + statusCode );
                if ( !StringUtils.isEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        HttpSession session = inRequest.getSession( true );
                        session.setAttribute( "SuccessMsg", "Succesffully forwarded CRF to " + crmCapDto.getToStage() );
                        forward = IActionForwardConstant.INBOX;
                    }
                    else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM201.getStatusCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getToStage() ) );
                    }
                    else
                    {
                        LOGGER.info( "Somthing has wrong in save map mac id Details" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            if ( ex instanceof NextraCrmException )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( ex.getMessage() ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
            LOGGER.error( "Error occured in save map mac id", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                crmCapForm.setRejectionReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_SP_REJECTION ) );
                crmCapForm.setCancellationReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
                crmCapForm.setRefuselReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_CUSTOMER_REFUSAL ) );
                crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                     .getRecordId(), null, null );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                }
            }
            saveErrors( inRequest, errors );
            saveMessages( inRequest, messages );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward bindCPEMACId( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
        try
        {
            crmCapDto = new CrmCapDto();
            if ( !CrmNPCrfFormHelper.validateBindMapMacId( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "map mac details page" );
                forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
            }
            else
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                setUserAssociations( userDto, crmCapForm );
                crmCapDto = getCapManagerImpl().bindCPEMACId( crmCapForm, userDto.getCrmUserDetailsPojo().getUserId() );
                if ( !StringUtils.isEmpty( crmCapDto.getStatusCode() ) )
                {
                    if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmCapDto.getStatusCode() ) );
                        crmCapForm.setCrmNetworkConfigurations( crmCapDto.getNetworkConfigurationsPojo() );
                        crmCapForm.setCustomerDetailsPojo( crmCapDto.getCustomerDetailsPojo() );
                        crmCapForm.setActivationDate( DateUtils.convertXmlCalToString( ( crmCapForm
                                .getCustomerDetailsPojo().getActivationDate() ) ) );
                        if ( !crmCapForm.getCustomerDetailsPojo().getProduct()
                                .equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                        {
                            crmCapForm.setMaterialList( getCapManagerImpl().getMaterialList( crmCapForm ) );
                            crmCapForm.setCustomerFeedBackList( getCapManagerImpl()
                                    .getCustomerFeedBackList( crmCapForm ) );
                        }
                        forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
                    }
                    else
                    {
                        LOGGER.info( "Somthing has wrong in bind CPE MAC Id" );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getBillingErrorCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in bind CPE MAC Id", ex );
        }
        finally
        {
            LOGGER.info( "Record ID::" + crmCapForm.getCustomerDetailsPojo().getRecordId() );
            crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                 .getRecordId(), null, null );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
            {
                getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
            }
            crmCapForm.setRejectionReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_SP_REJECTION ) );
            crmCapForm.setCancellationReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
            crmCapForm.setRefuselReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_CUSTOMER_REFUSAL ) );
            LOGGER.info( "CustomerDetails::" + crmCapForm.getCustomerDetailsPojo().toString() );
            saveErrors( inRequest, errors );
            saveMessages( inRequest, messages );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward cancelCRFPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        try
        {
            String crfID = inRequest.getParameter( "CrfID" );
            String recordId = inRequest.getParameter( "RecordId" );
            LOGGER.info( "CRF ID ::" + crfID + ",RecordId ::" + recordId );
            crmCapForm.setCancellationReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
            crmCapForm.setRemarksPojo( new RemarksPojo() );
            crmCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            crmCapForm.getCustomerDetailsPojo().setRecordId( StringUtils.numericValue( recordId ) );
            crmCapForm.setCrfTabId( crfID );
            CrmCapDto crmCapDto = getCapManagerImpl().checkCrfInLinkToCrf( crfID );
            LOGGER.info( "cancelCRFPage():::::status code" + crmCapDto.getStatusCode() );
            inRequest.setAttribute( "APPMSG", crmCapDto.getStatusCode() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured cancelCrfPage" + ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CANCLE_CRF_PAGE );
    }

    public ActionForward cancelCRFAction( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapDto crmCapDto = null;
        String linkedCRF = null;
        String status = CRMServiceCode.CRM001.getStatusCode();
        String forward = IActionForwardConstant.CANCLE_CRF_PAGE;
        try
        {
            if ( !CrmCapFormHelper.validateIandAForm( IAppConstants.SUBMIT_CANCEL_CRF, errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "cancel CRF Action page..." );
                forward = IActionForwardConstant.CANCLE_CRF_PAGE;
            }
            else
            {
                linkedCRF = crmCapForm.getCustomerDetailsPojo().getLinkedCRF();
                crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                     .getRecordId(), null, null );
                if ( StringUtils.isValidObj( crmCapDto )
                        && StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                }
                if ( StringUtils.isNotBlank( linkedCRF ) )
                {
                    crmCapForm.getCustomerDetailsPojo().setLinkedCRF( linkedCRF );
                    status = getMasterBMngr().validateCRFInMaster( linkedCRF.toUpperCase(), null );
                    if ( StringUtils.equals( status, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        crmCapDto = getCapManagerImpl().validateCrfInCustomerDetails( linkedCRF );
                        if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            status = CRMServiceCode.CRM303.getStatusCode();
                        }
                        else
                        {
                            crmCapDto = getCapManagerImpl().checkCrfInLinkToCrf( linkedCRF );
                            if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                status = CRMServiceCode.CRM321.getStatusCode();
                            }
                            else
                            {
                                status = CRMServiceCode.CRM001.getStatusCode();
                            }
                        }
                    }
                    else
                    {
                        status = CRMServiceCode.CRM301.getStatusCode();
                    }
                }
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), status ) )
                {
                    CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                            .getAttribute( IAppConstants.CRM_USER_OBJECT );
                    crmCapForm.getRemarksPojo().setActions( CrmActionEnum.CANCEL.getActionCode() );
                    crmCapDto = getCapManagerImpl().saveMapMacIdDetails( crmCapForm,
                                                                         userDto.getCrmUserDetailsPojo().getUserId() );
                    if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmCapDto.getStatusCode() ) );
                        if ( StringUtils.isNotBlank( linkedCRF ) )
                        {
                            crmCapForm.getCustomerDetailsPojo().setCrfId( linkedCRF );
                        }
                        crmCapForm.setProductTypeList( CRMUtils.getProducts() );
                        crmCapForm.setServiceTypes( CRMUtils.getServiceTypesList() );
                        forward = IActionForwardConstant.SEARCH_CRF_PAGE;
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getBillingErrorCode() ) );
                    }
                }
                else if ( StringUtils.equals( CRMServiceCode.CRM996.getStatusCode(), status ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM301.getStatusCode() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( status ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while submit cancel crf" + ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                crmCapForm.setCancellationReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
            }
            saveMessages( inRequest, messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward getCustomerBySociety( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm form = (CrmCapForm) inForm;
        String forward = IActionForwardConstant.VIEW_CUSTOMER_BY_SOCIETY;
        CrmCapDto crmCapDto = null;
        try
        {
            String state = inRequest.getParameter( "State" );
            String city = inRequest.getParameter( "City" );
            String area = inRequest.getParameter( "Area" );
            String locality = inRequest.getParameter( "Locality" );
            CrmAddressDetailsPojo addressDetailsPojo = (CrmAddressDetailsPojo) inRequest.getSession()
                    .getAttribute( CrmAddressDetailsPojo.class.getSimpleName() );
            if ( StringUtils.isValidObj( addressDetailsPojo ) )
            {
                form.setInstallationAddressPojo( addressDetailsPojo );
            }
            LOGGER.info( "Society ID:: " + form.getInstallationAddressPojo().getInstSocietyId() );
            crmCapDto = new CrmCapDto();
            crmCapDto = getCapManagerImpl().getCustomerBySociety( form );
            String statusCode = crmCapDto.getStatusCode();
            if ( !StringUtils.isEmpty( statusCode ) )
            {
                LOGGER.info( "List of CommonCustDetailsPojos::" + crmCapDto.getCommonCustDetailsPojos().size() );
                if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    form.setCommonCustDetailsPojos( crmCapDto.getCommonCustDetailsPojos() );
                    form.setState( state );
                    form.setCity( city );
                    form.setArea( area );
                    form.setLocality( locality );
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
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in getAllCustomerBySocity", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward editCPEStatusPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        try
        {
            String recordId = inRequest.getParameter( "OrderId" );
            String cpeStatus = inRequest.getParameter( "CPEStatus" );
            if ( StringUtils.isNotBlank( recordId ) && StringUtils.isNotBlank( cpeStatus ) )
            {
                crmCapForm.getOrderDetailsPojo().setCpeStatus( cpeStatus );
                crmCapForm.getOrderDetailsPojo().setRecordId( StringUtils.numericValue( recordId ) );
            }
            crmCapForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in edit CPE Status Page", ex );
        }
        return inMapping.findForward( IActionForwardConstant.EDIT_CPE_STATUS_PAGE );
    }

    public ActionForward saveDeviceStatus( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        CrmCapForm crmCapForm = (CrmCapForm) inForm;
        String forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
        try
        {
            crmCapDto = new CrmCapDto();
            LOGGER.info( "Customer Record ID::" + crmCapForm.getCustomerDetailsPojo().getRecordId() );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            setUserAssociations( userDto, crmCapForm );
            if ( !CrmNPCrfFormHelper.validateCPEStatus( errors, crmCapForm ).isEmpty() )
            {
                LOGGER.info( "saveDeviceStatus method " );
                crmCapForm.setOrderDetailsPojo( crmCapForm.getTempOrderDetailsPojo() );
                forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
            }
            else
            {
                crmCapDto = getCapManagerImpl().saveDeviceStatus( crmCapForm,
                                                                  userDto.getCrmUserDetailsPojo().getUserId() );
                if ( !StringUtils.isEmpty( crmCapDto.getStatusCode() ) )
                {
                    if ( StringUtils.equals( crmCapDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Succesffully save CPE device status" );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmCapDto.getStatusCode() ) );
                    }
                    else
                    {
                        LOGGER.info( "Somthing has wrong in save CPE Device Details" );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmCapDto.getStatusCode(), crmCapDto.getBillingErrorCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status code found null" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in save CPE Device Details", ex );
        }
        finally
        {
            crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                 .getRecordId(), null, null );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
            {
                getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
            }
            saveErrors( inRequest, errors );
            saveMessages( inRequest, messages );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward viewCRFDetails( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        HttpSession httpSession = inRequest.getSession( false );
        CrmCapDto crmCapDto = null;
        String forward = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            CrmCapForm crmCapForm = (CrmCapForm) inForm;
            crmCapForm.setCrfTabId( "#customerBasicInformation" );
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            String recordId = inRequest.getParameter( "RecordId" );
            String inboxId = inRequest.getParameter( "InboxId" );
            String inType = inRequest.getParameter( "inType" );
            if ( StringUtils.equals( inType, "customerProfile" ) )
            {
                crmCapForm.setRemarksPojo( new RemarksPojo() );
            }
            crmCapForm.setParameter( inType );
            customerDetailsPojo.setRecordId( StringUtils.numericValue( recordId ) );
            customerDetailsPojo.setInboxId( StringUtils.numericValue( inboxId ) );
            try
            {
                httpSession
                        .setAttribute( "FB_FT_ID",
                                       IAppConstants.COMMA
                                               + CRMCacheManager
                                                       .getFunctionalBinsByAlias( CRMOperationStages.FULFILLMENT_TEAM
                                                                                          .getCode() ).getCategoryId()
                                               + IAppConstants.COMMA );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Error occured setting  FB_FT_ID", ex );
            }
            crmCapForm.setCustomerDetailsPojo( customerDetailsPojo );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            crmCapDto = getCapManagerImpl().viewCrfDetails( crmCapForm, userDto.getCrmUserDetailsPojo().getUserId() );
            String statusCode = crmCapDto.getStatusCode();
            if ( !StringUtils.isEmpty( statusCode ) )
            {
                LOGGER.info( statusCode + " In viewCRFDetails :: " + crmCapDto.getStatusDesc() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode ) )
                {
                    ConfigDto configDto = getCapManagerImpl().getActivityLogs( recordId );
                    httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
                    if ( configDto.getAuditLogPojos().size() > 0 )
                    {
                        httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
                    }
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                    getCapManagerImpl().setInstallationGISDetails( crmCapForm, true );
                    getCapManagerImpl().setBillingGISDetails( crmCapForm, true );
                    //crmCapForm.setServiceToBeUsedList( CRMUtils.getProducts() );
                    forward = IActionForwardConstant.VIEW_CRF_DETAILS;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    LOGGER.info( "Somthing has wrong in view CRF Details" );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in view CRF Details", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward saveRemarks( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.VIEW_CRF_DETAILS;
        CrmCapForm form = (CrmCapForm) inForm;
        RemarksPojo remarksPojo = form.getRemarksPojo();
        MasterDataDto masterDto = new MasterDataDto();
        try
        {
            LOGGER.info( "Remarks Pojo is::::::" + remarksPojo );
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( remarksPojo, ICRMValidationCriteriaUtil.FORM_REMARKS_CRITERIA_COMMON, false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                remarksPojo.setActions( CrmActionEnum.SAVED_REMARKS.getActionCode() );
                remarksPojo.setMappingType( CRMRequestType.INA.getRequestCode() );
                masterDto = getMasterBMngr().saveRemarks( remarksPojo );
                String statusCode = masterDto.getStatusCode();
                if ( !StringUtils.isEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        forward = IActionForwardConstant.VIEW_CRF_DETAILS;
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in saving remarks", ex );
        }
        finally
        {
            LOGGER.info( "Record ID::" + form.getCustomerDetailsPojo().getRecordId() );
            CrmCapDto crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( form.getCustomerDetailsPojo()
                                                                                           .getRecordId(), null, null );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
            {
                getCapManagerImpl().setCRFDetails( crmCapDto, form );
            }
            form.setCrfTabId( "#customerBasicInformation" );
            saveMessages( inRequest, messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward editCRFEntryForAnyStage( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        String forward = null;
        ConfigDto configDto = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        try
        {
            CrmCapForm crmCapForm = (CrmCapForm) inForm;
            HttpSession httpSession = inRequest.getSession( false );
            crmCapForm.setCrfTabId( "#customerBasicInformation" );
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            String recordId = inRequest.getParameter( "RecordId" );
            String inboxId = inRequest.getParameter( "InboxId" );
            customerDetailsPojo.setRecordId( StringUtils.numericValue( recordId ) );
            customerDetailsPojo.setInboxId( StringUtils.numericValue( inboxId ) );
            crmCapForm.setCustomerDetailsPojo( customerDetailsPojo );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            setUserAssociations( userDto, crmCapForm );
            crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( StringUtils.numericValue( recordId ), null,
                                                                         null );//viewCrfDetails( crmCapForm, userDto.getCrmUserDetailsPojo().getUserId() );
            String statusCode = crmCapDto.getStatusCode();
            if ( !StringUtils.isEmpty( statusCode ) )
            {
                LOGGER.info( statusCode + ":: " + crmCapDto.getStatusDesc() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode ) )
                {
                    configDto = getCapManagerImpl().getActivityLogs( recordId );
                    httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
                    if ( configDto.getAuditLogPojos().size() > 0 )
                    {
                        httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
                    }
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                    MasterDataDto dataDto = getMasterBMngr().updateUnreadInbox( StringUtils.numericValue( inboxId ) );
                    if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Marked inbox entry to unbold" );
                    }
                    String crfStage = crmCapForm.getCustomerDetailsPojo().getCrfStage();
                    LOGGER.info( "CRF Stage:" + crfStage );
                    crmCapForm
                            .setRefuselReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_CUSTOMER_REFUSAL ) );
                    getCapManagerImpl().setInstallationGISDetails( crmCapForm, true );
                    getCapManagerImpl().setBillingGISDetails( crmCapForm, true );
                    inRequest.getSession().setAttribute( CrmAddressDetailsPojo.class.getSimpleName(),
                                                         crmCapForm.getInstallationAddressPojo() );
                    if ( StringUtils.equals( CRMOperationStages.FULFILLMENT_TEAM.getCode(), crfStage )
                            || StringUtils.equals( CRMOperationStages.NP_REJECT.getCode(), crfStage )
                            || StringUtils.equals( CRMOperationStages.FT_ISR.getCode(), crfStage ) )
                    {
                        if ( StringUtils.equals( CRMOperationStages.ISR_PUNCH.getCode(), crmCapForm
                                .getCustomerDetailsPojo().getCrfPreviousStage() ) )
                        {
                            crmCapForm.setDisplayISRDate( DateUtils.convertXGCtoCal( crmCapForm
                                    .getCustomerDetailsPojo().getDateOnIsr().toString() ) );
                        }
                        getCapManagerImpl().setNetworkPartnersByProduct( crmCapForm,
                                                                         crmCapForm.getCustomerDetailsPojo()
                                                                                 .getProduct() );
                        crmCapForm.setRemarksPojo( new RemarksPojo() );
                        crmCapForm.setHeading( false );
                        crmCapForm.setCrmUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                        crmCapForm.setRejectionReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_FT_REJECTION ) );
                        forward = IActionForwardConstant.CRF_VALIDATE_ENTRY_PAGE;
                    }
                    else if ( crfStage.equals( CRMOperationStages.FT_REJECT.getCode() ) )
                    {
                        forward = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;
                    }
                    else if ( crfStage.equals( CRMOperationStages.INITIATE.getCode() ) )
                    {
                        crmCapForm.setHeading( true );
                        forward = IActionForwardConstant.INSTALLATION_AND_ACTIVATION;
                    }
                    else if ( crfStage.equals( CRMOperationStages.NETWORK_PARTNER.getCode() )
                            || crfStage.equals( CRMOperationStages.SP_REJECT_NP.getCode() ) )
                    {
                        crmCapForm.setRemarksPojo( new RemarksPojo() );
                        // calling for NP to SP list
                        crmCapForm.setCrmMappingList( ( getCapManagerImpl().getServicePartnerList( crmCapForm ) ) );
                        crmCapForm.setRejectionReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_NP_REJECTION ) );
                        crmCapForm.setCancellationReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
                        crmCapForm.getCrmNetworkConfigurations().setOntRgMduDone( true );
                        crmCapForm.setErpReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_ERP_REJECTION ) );
                        /*if ( crmCapForm.getCrmNetworkConfigurations().getRecordId() == 0 )
                        {
                            crmCapForm.getCrmNetworkConfigurations().setOntRgMduDone( true );
                        }*/
                        forward = IActionForwardConstant.NETWORK_DETAILS_PAGE;
                    }
                    else if ( crfStage.equals( CRMOperationStages.NP_REJECT_SALES.getCode() )
                            || crfStage.equals( CRMOperationStages.SP_REJECT_BP.getCode() ) )
                    {
                        crmCapForm.setRefuselReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_CUSTOMER_REFUSAL ) );
                        crmCapForm.setCancellationReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
                        crmCapForm.setRemarksPojo( new RemarksPojo() );
                        crmCapForm.setHeading( false );
                        forward = IActionForwardConstant.SALES_CRF_CONFIRMATION;
                    }
                    else if ( crfStage.equals( CRMOperationStages.SERVICE_PARTNER.getCode() ) )
                    {
                        LOGGER.info( "Service Partner Stage::" );
                        crmCapForm.setRemarksPojo( new RemarksPojo() );
                        crmCapForm.setRejectionReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_SP_REJECTION ) );
                        crmCapForm.setCancellationReasonList( CRMCacheManager
                                .getINAReasons( CRMRCAReason.INA_REASON_CANCEL ) );
                        crmCapForm.getCrmNetworkConfigurations().setSpOntRgMduDone( true );
                        crmCapForm.setErpReasonList( CRMCacheManager.getINAReasons( CRMRCAReason.INA_ERP_REJECTION ) );
                        //crmCapForm.setMacFaulty( true );
                        /*   if ( crmCapForm.getCustomerDetailsPojo().getProduct()
                                   .equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                           {
                               // Get pre-configured secondary MAC address List
                               PartnerPojo partner = CommonUtils.getPartnerById( StringUtils.numericValue( crmCapForm
                                       .getCustomerDetailsPojo().getNpId() + "" ) );
                            if ( StringUtils.isValidObj( partner ) )
                               {
                                   configDto = getCrmConfigManager().getMacAddressByNP( partner.getCrmPartnerCode(),
                                                                                        crmCapForm
                                                                                                .getCustomerDetailsPojo()
                                                                                                .getCustomerId(),
                                                                                        CRMRequestType.INA
                                                                                                .getRequestCode() );
                                   if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                            configDto.getStatusCode() ) )
                                   {
                                       if ( CommonValidator.isValidCollection( configDto.getMacAddressList() ) )
                                       {
                                           crmCapForm.setSecondaryMACAddrList( configDto.getMacAddressList() );
                                       }
                                   }
                                   else
                                   {
                                       LOGGER.info( "Billing Error Code:" + configDto.getBillingErrorCode() );
                                       errors.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( configDto.getStatusCode(), configDto
                                                           .getBillingErrorCode() ) );
                                   }
                               }
                           }*/
                        if ( crmCapForm.getCustomerDetailsPojo().getActivationDate() != null )
                        {
                            crmCapForm.setActivationDate( DateUtils.convertXmlCalToString( ( crmCapForm
                                    .getCustomerDetailsPojo().getActivationDate() ) ) );
                            crmCapForm.setMaterialList( getCapManagerImpl().getMaterialList( crmCapForm ) );
                            crmCapForm.setCustomerFeedBackList( getCapManagerImpl()
                                    .getCustomerFeedBackList( crmCapForm ) );
                        }
                        if ( !StringUtils.isValidObj( crmCapForm.getTelecommunicationPayment() ) )
                        {
                            crmCapForm.setTelecommunicationPayment( new CrmPaymentDetailsPojo() );
                        }
                        forward = IActionForwardConstant.MAP_MAC_DETAILS_PAGE;
                    }
                    else if ( crfStage.equals( CRMOperationStages.ISR_PUNCH.getCode() ) )
                    {
                        crmCapForm.setMaterialList( getCapManagerImpl().getMaterialList( crmCapForm ) );
                        crmCapForm.setCustomerFeedBackList( getCapManagerImpl().getCustomerFeedBackList( crmCapForm ) );
                        crmCapForm.setCrmUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                        LOGGER.info( "Customer feedBack List ::"
                                + getCapManagerImpl().getCustomerFeedBackList( crmCapForm ).size() );
                        forward = IActionForwardConstant.INSTALLATION_SATISFACTION_REPORTS;
                    }
                    else if ( crfStage.equals( CRMOperationStages.FREEZE.getCode() ) )
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMOperationStages.FREEZE.getCode() ) );
                        forward = IActionForwardConstant.VIEW_CRF_PAGE;
                    }
                }
                else
                {
                    LOGGER.info( "Somthing has wrong in edit CRF Entry for any Stage" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                }
            }
            else
            {
                LOGGER.info( "Status code found null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in edit CRF Entry for any Stage", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward punchISR( final ActionMapping inMapping,
                                   final ActionForm inForm,
                                   final HttpServletRequest inRequest,
                                   final HttpServletResponse inResponse )
    {
        CrmCapDto crmCapDto = null;
        ConfigDto configDto = null;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.INSTALLATION_SATISFACTION_REPORTS;
        try
        {
            crmCapDto = new CrmCapDto();
            CrmCapForm crmCapForm = (CrmCapForm) inForm;
            if ( !CrmNPCrfFormHelper.validateISRDate( crmCapForm, errors ).isEmpty() )
            {
                forward = IActionForwardConstant.INSTALLATION_SATISFACTION_REPORTS;
            }
            else
            {
                LOGGER.info( "Record ID::" + crmCapForm.getCustomerDetailsPojo().getRecordId() );
                crmCapDto = getCapManagerImpl().trackCustomerProfileDetails( crmCapForm.getCustomerDetailsPojo()
                                                                                     .getRecordId(), null, null );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    getCapManagerImpl().setCRFDetails( crmCapDto, crmCapForm );
                }
                configDto = getCapManagerImpl().listNPUploadedDoc( CRMRequestType.INA.getRequestCode(),
                                                                   crmCapForm.getCustomerDetailsPojo().getCrfId() );
                if ( CommonValidator.isValidCollection( configDto.getPodUploadedList() )
                        && configDto.getPodUploadedList().size() > 0 && configDto.getPodUploadedList().contains( "ISR" ) )
                {
                    CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                            .getAttribute( IAppConstants.CRM_USER_OBJECT );
                    setUserAssociations( userDto, crmCapForm );
                    crmCapDto = getCapManagerImpl().punchISR( crmCapForm, userDto.getCrmUserDetailsPojo().getUserId() );
                    String statusCode = crmCapDto.getStatusCode();
                    LOGGER.info( "Status Code:: " + statusCode );
                    if ( !StringUtils.isEmpty( statusCode ) )
                    {
                        if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            HttpSession session = inRequest.getSession( true );
                            session.setAttribute( "SuccessMsg",
                                                  "Succesffully forwarded CRF to " + crmCapDto.getToStage() );
                            forward = IActionForwardConstant.INBOX;
                        }
                        else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM201.getStatusCode() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode(),
                                                                                    crmCapDto.getToStage() ) );
                        }
                        else
                        {
                            LOGGER.info( "Somthing has wrong in punch ISR." );
                            crmCapForm.setMaterialList( getCapManagerImpl().getMaterialList( crmCapForm ) );
                            crmCapForm.setCustomerFeedBackList( getCapManagerImpl()
                                    .getCustomerFeedBackList( crmCapForm ) );
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Status code found null" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_STATUS ) );
                    }
                }
                else
                {
                    LOGGER.info( "Error in ISR Upload Documents." );
                    crmCapForm.setMaterialList( getCapManagerImpl().getMaterialList( crmCapForm ) );
                    crmCapForm.setCustomerFeedBackList( getCapManagerImpl().getCustomerFeedBackList( crmCapForm ) );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( IPropertiesConstant.ERROR_FILE_UPLOAD_DOCUMENTS, "ISR" ) );
                    forward = IActionForwardConstant.INSTALLATION_SATISFACTION_REPORTS;
                }
            }
        }
        catch ( Exception ex )
        {
            if ( ex instanceof NextraCrmException )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( ex.getMessage() ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            }
            LOGGER.error( "Error occured in Punch ISR", ex );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward generateAndPrintISRReport( final ActionMapping inMapping,
                                                    final ActionForm inForm,
                                                    final HttpServletRequest inRequest,
                                                    final HttpServletResponse inResponse )
    {
        ActionMessages errors = new ActionMessages();
        CrmCapForm form = (CrmCapForm) inForm;
        CrmCapDto crmCapDto = null;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String cafId = inRequest.getParameter( "CAFID" );
            String product = inRequest.getParameter( "Product" );
            if ( StringUtils.isNotBlank( cafId ) && StringUtils.isNotBlank( product ) )
            {
                LOGGER.info( "CAF ID :: " + cafId + ",Product ::" + product );
                CrmCustomerDetailsPojo crmCustPojo = new CrmCustomerDetailsPojo();
                crmCustPojo.setCrfId( cafId );
                crmCustPojo.setProduct( product );
                form.setCustomerDetailsPojo( crmCustPojo );
                crmCapDto = getCapManagerImpl().searchCrfDetails( form, userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() )
                        || StringUtils.equals( CRMServiceCode.CRM311.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    LOGGER.info( "PlanDetails Size :: "
                            + crmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses().size() );
                    getCapManagerImpl().setCRFDetails( crmCapDto, form );
                    String client = getServlet().getServletContext().getInitParameter( "client" );
                    String iconPath = getServlet().getServletContext()
                            .getRealPath( String.format( IAppConstants.PDF_ICON_PATH, client ) );
                    String checkBoxPath = getServlet().getServletContext()
                            .getRealPath( IAppConstants.PDF_CHECK_BOX_PATH );
                    String checkBoxYesPath = getServlet().getServletContext()
                            .getRealPath( IAppConstants.PDF_CHECK_BOX_YES_PATH );
                    getCapManagerImpl().generateISRPdf( inResponse, iconPath, checkBoxPath, checkBoxYesPath, form );
                }
                else
                {
                    LOGGER.info( "status Code:: " + crmCapDto.getStatusCode() );
                }
            }
        }
        catch ( DocumentException ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in generate ISR pdf report", ex );
        }
        catch ( IOException ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Error occured in generate ISR pdf report", ex );
        }
        return null;
    }
}
