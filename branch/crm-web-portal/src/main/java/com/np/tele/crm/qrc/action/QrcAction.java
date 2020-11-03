package com.np.tele.crm.qrc.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletContext;
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
import org.apache.struts.util.MessageResources;
import org.jboss.logging.MDC;

import com.np.tele.crm.cap.bm.ICrmCapManager;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.QRCCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.qrc.forms.QrcFormHelper;
import com.np.tele.crm.qrc.workflow.bm.IWorkFlowManager;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustInteractionsPojo;
import com.np.tele.crm.service.client.CrmCustWaiverPojo;
import com.np.tele.crm.service.client.CrmCustomerActivityPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcCategoryBinMappingPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmShiftingWorkflowPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.CustomerUsageDetailsPojo;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.usrmngmnt.bm.ICustomerProfileMgr;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.CustomerProfileForm;
import com.np.tele.crm.utils.BeanUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ExcelWriteUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class QrcAction
    extends DispatchAction
{
    private static final Logger LOGGER             = Logger.getLogger( QrcAction.class );
    private IQrcManager         qrcManagerBm       = null;
    private ICrmConfigManager   crmConfigManager   = null;
    private ICrmCapManager      capManagerImpl     = null;
    private IMasterBMngr        masterDataBm       = null;
    private IWorkFlowManager    workFlowManager    = null;
    private IUserBMngr          usermngmntbm       = null;
    private ICustomerProfileMgr custProfileMgrImpl = null;

    public IUserBMngr getUsermngmntbm()
    {
        return usermngmntbm;
    }

    public void setUsermngmntbm( IUserBMngr inUsermngmntbm )
    {
        usermngmntbm = inUsermngmntbm;
    }

    public ICustomerProfileMgr getCustProfileMgrImpl()
    {
        return custProfileMgrImpl;
    }

    public void setCustProfileMgrImpl( ICustomerProfileMgr inCustProfileMgrImpl )
    {
        custProfileMgrImpl = inCustProfileMgrImpl;
    }

    public IMasterBMngr getMasterDataBm()
    {
        return masterDataBm;
    }

    public void setMasterDataBm( IMasterBMngr masterDataBm )
    {
        this.masterDataBm = masterDataBm;
    }

    public IWorkFlowManager getWorkFlowManager()
    {
        return workFlowManager;
    }

    public void setWorkFlowManager( IWorkFlowManager inWorkFlowManager )
    {
        workFlowManager = inWorkFlowManager;
    }

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public ICrmCapManager getCapManagerImpl()
    {
        return capManagerImpl;
    }

    public void setCapManagerImpl( ICrmCapManager capManagerImpl )
    {
        this.capManagerImpl = capManagerImpl;
    }

    public ActionForward searchCustomerPage( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE );
    }

    public ActionForward searchCustomer( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside " + this.getClass().getSimpleName() + ":"
                + inRequest.getParameter( IAppConstants.PARAMETER_NAME ) );
        ActionMessages errors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
        try
        {
            if ( StringUtils.isNumeric( qrcForm.getSearchString() ) )
            {
                MDC.put( "KEY", qrcForm.getSearchString() );
                customerDetailsPojo.setCustomerId( qrcForm.getSearchString() );
                customerDetailsPojo.setCrfId( qrcForm.getSearchString() );
                CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                if ( StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    CrmCustomerDetailsPojo capCustomerDetailsPojo = capDto.getCustomerDetailsPojo();
                    if ( StringUtils.isValidObj( capCustomerDetailsPojo ) )
                    {
                        if ( StringUtils.isNotBlank( capCustomerDetailsPojo.getCustomerId() ) )
                        {
                            if ( capCustomerDetailsPojo.getNpId() > 0 )
                            {
                                PartnerPojo partner = CommonUtils.getPartnerById( StringUtils
                                        .numericValue( capCustomerDetailsPojo.getNpId() + "" ) );
                                ConfigDto configDto = getCrmConfigManager()
                                        .getMacAddressByNP( partner.getCrmPartnerCode(),
                                                            capCustomerDetailsPojo.getCustomerId(),
                                                            CRMRequestType.INA.getRequestCode() );
                                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                         configDto.getStatusCode() ) )
                                {
                                    if ( CommonValidator.isValidCollection( configDto.getMacAddressList() ) )
                                    {
                                        qrcForm.setInventoryName( configDto.getInventType() );
                                    }
                                }
                            }
                            if ( ! ( StringUtils.equals( capCustomerDetailsPojo.getStatus(),
                                                         CRMStatusCode.INACTIVE.getStatusCode() )
                                    || StringUtils.equals( capCustomerDetailsPojo.getCrfStage(),
                                                           CRMOperationStages.CLOSE.getCode() ) ) )
                            {
                                CrmQrcDto billHistoryDto = getQrcManagerBm()
                                        .getBillDetails( capCustomerDetailsPojo.getCustomerId(),
                                                         capCustomerDetailsPojo.getServiceType() );
                                if ( CommonValidator.isValidCollection( billHistoryDto.getInvoiceDetailsPojos() ) )
                                {
                                    qrcForm.setInvoiceList( billHistoryDto.getInvoiceDetailsPojos() );
                                    int size = billHistoryDto.getInvoiceDetailsPojos().size();
                                    SortingComparator<CrmInvoiceDetailsPojo> sorter = new SortingComparator<CrmInvoiceDetailsPojo>( "billNumber" );
                                    Collections.sort( billHistoryDto.getInvoiceDetailsPojos(), sorter );
                                    qrcForm.setInvoicePojo( billHistoryDto.getInvoiceDetailsPojos().get( size - 1 ) );
                                }
                                else
                                {
                                    qrcForm.setInvoicePojo( new CrmInvoiceDetailsPojo() );
                                }
                                qrcForm.setCustDetailsPojo( capCustomerDetailsPojo );
                                qrcForm.setCustomerId( capCustomerDetailsPojo.getCustomerId() );
                                qrcForm.setOrderDetailsPojo( capDto.getOrderDetailsPojo() );
                                qrcForm.setPlanDetailsPojo( capCustomerDetailsPojo.getCrmPlanDetailses().get( 0 ) );
                                getQrcManagerBm().getBasePlanInfo( qrcForm );
                                if ( StringUtils.isNotBlank( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                                {
                                    MasterDataDto masterDataDto = getQrcManagerBm().getAddonPlanInfo( qrcForm );
                                    if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
                                    {
                                        qrcForm.setCrmAddonPlanMasterPojo( masterDataDto.getCrmPlanMasterPojos()
                                                .get( 0 ) );
                                    }
                                }
                                qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                                if ( StringUtils.isValidObj( capDto.getCrmPartnerNetworkConfig() )
                                        || StringUtils.isValidObj( capDto.getNetworkConfigurationsPojo() ) )
                                {
                                    //   TestAPI testAPI = new TestAPI();
                                    RiMasterData riMasterData = null;
                                    if ( capCustomerDetailsPojo.getProduct().equals( "BB" ) )
                                    {
                                        riMasterData = TestAPI
                                                .getRIDetails( capDto.getNetworkConfigurationsPojo().getOption82() );
                                        qrcForm.setPartnerNetworkConfigPojo( new CrmPartnerNetworkConfigPojo() );
                                        qrcForm.getPartnerNetworkConfigPojo()
                                                .setMasterName( capDto.getNetworkConfigurationsPojo().getOption82() );
                                    }
                                    else
                                    {
                                        qrcForm.setPartnerNetworkConfigPojo( capDto.getCrmPartnerNetworkConfig() );
                                        riMasterData = TestAPI
                                                .getRIDetails( capDto.getCrmPartnerNetworkConfig().getMasterName() );
                                    }
                                    // LOGGER.info( "API Status Code:: " + riMasterData.getStatusCode() );
                                    if ( StringUtils.isValidObj( riMasterData ) && riMasterData.getDevice().size() > 0 )
                                    {
                                        LOGGER.info( "Device list size:: " + riMasterData.getDevice().size() );
                                        Device device = new Device();
                                        boolean flag = true;
                                       // List<Device> device1 = (List<Device>) riMasterData.getDevice();
                                        for ( Device class1 : riMasterData.getDevice() )
                                        {
                                            if ( flag )
                                            {
                                                device.setStatus( class1.getStatus() );
                                                device.setUptimesince( class1.getUptimesince() );
                                                device.setDowntimesince( class1.getDowntimesince() );
                                                flag = false;
                                            }
                                            if ( class1.getHostRaw() == null )
                                            {
                                                device.setHost( class1.getHost() );
                                            }
                                        }
                                        qrcForm.setDevice1Object( device );
                                    }
                                }
                                setAddressPojo( qrcForm, capCustomerDetailsPojo.getCrmAddressDetailses() );
                                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                                qrcForm.setCrmUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                                qrcForm.setConnectionTypes( CRMUtils.getConnectionTypes() );
                                qrcForm.setBillModes( CRMUtils.getBillingPreferences() );
                                CrmQrcDto qrcDto = getQrcManagerBm()
                                        .getCustAdditionDetails( qrcForm.getCustDetailsPojo() );
                                qrcForm.setCustAdditionalDetails( qrcDto.getCustAdditionalDetails() );
                                if ( StringUtils.isValidObj( capDto.getNationalityDetailsPojo() ) )
                                {
                                    qrcForm.setNationalityDetailsPojo( capDto.getNationalityDetailsPojo() );
                                }
                                if ( StringUtils.isValidObj( capDto.getDocumentDetailsPojo() ) )
                                {
                                    qrcForm.setDocumentDetailsPojo( capDto.getDocumentDetailsPojo() );
                                }
                                if ( CommonValidator.isValidCollection( capDto.getPaymentDetailsPojosList() ) )
                                {
                                    for ( CrmPaymentDetailsPojo paymentDetailsPojo : capDto
                                            .getPaymentDetailsPojosList() )
                                    {
                                        if ( StringUtils.equals( paymentDetailsPojo.getEntityType(),
                                                                 CRMDisplayListConstants.TELESERVICES.getCode() ) )
                                        {
                                            qrcForm.setCrmPaymentDetailsPojo( paymentDetailsPojo );
                                            break;
                                        }
                                    }
                                }
                                qrcForm.setMassOutagePojo( getQrcManagerBm()
                                        .getMassOutage( capCustomerDetailsPojo.getProduct(),
                                                        qrcForm.getInstallationAddressPojo(),
                                                        qrcForm.getNetworkConfigurationsPojo() ) );
                                CrmQrcDto crmQrcDto = new CrmQrcDto();
                                crmQrcDto.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
                                crmQrcDto = getQrcManagerBm().getCustomerFeedback( crmQrcDto );
                                if ( StringUtils.equals( crmQrcDto.getStatusCode(),
                                                         CRMServiceCode.CRM001.getStatusCode() ) )
                                {
                                    qrcForm.setCrmCustAssetDetailsPojo( crmQrcDto.getCrmCustAssetDetailsPojo() );
                                }
                                return inMapping.findForward( IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE );
                            }
                            else
                                errors.add( IAppConstants.APP_ERROR,
                                            new ActionMessage( IPropertiesConstant.ERROR_CRF_STAGE_OR_STATUS ) );
                        }
                        else
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_CUSTOMER_ID_NOT_MAPPED,
                                                           capCustomerDetailsPojo.getCrfId() ) );
                            inRequest.getSession().removeAttribute( "workingID" );
                        }
                    }
                    else
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_INVALID_CRF_CUSTOMER_ID ) );
                }
                else
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( capDto.getStatusCode() ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_INVALID_CRF_CUSTOMER_ID ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception searchCustomer:::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE );
    }

    public ActionForward ticketPage( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside " + this.getClass().getSimpleName() + ":"
                + inRequest.getParameter( IAppConstants.PARAMETER_NAME ) );
        CrmQrcDto crmQrcDto = null;
        CrmSrTicketsPojo ticketsPojo = null;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        CrmTicketHistoryPojo crmTicketHistoryPojo = null;
        ActionMessages errors = getErrors( inRequest );
        String forwardPage = IActionForwardConstant.TICKET_PAGE;
        try
        {
            QrcForm qrcForm = (QrcForm) inForm;
            qrcForm.setSrTicketsPojos( new ArrayList<CrmSrTicketsPojo>() );
            QrcFormHelper.validateCustomerID( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                crmQrcDto = new CrmQrcDto();
                ticketsPojo = new CrmSrTicketsPojo();
                customerDetailsPojo = new CrmCustomerDetailsPojo();
                ticketsPojo.setMappingId( qrcForm.getCustomerId() );
                customerDetailsPojo.setCrfId( qrcForm.getCustDetailsPojo().getCrfId() );
                crmQrcDto.setCustomerDetailsPojo( customerDetailsPojo );
                crmQrcDto.setCrmSrTicketsPojo( ticketsPojo );
                crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                    {
                        getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                        qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                        SortingComparator<CrmSrTicketsPojo> sorter = new SortingComparator<CrmSrTicketsPojo>( "createdTime" );
                        Collections.sort( qrcForm.getSrTicketsPojos(), Collections.reverseOrder( sorter ) );
                        sorter = null;
                    }
                }
                qrcForm.setTicketStatusList( CRMUtils.getQRCTicketStatus() );
                ticketsPojo = new CrmSrTicketsPojo();
                ticketsPojo.setMappingId( qrcForm.getCustomerId() );
                crmTicketHistoryPojo = new CrmTicketHistoryPojo();
                qrcForm.setSrTicketPojo( ticketsPojo );
                qrcForm.setTicketHistory( crmTicketHistoryPojo );
                setRequiredCollections( qrcForm );
            }
            else
            {
                forwardPage = IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while searching tickets", ex );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward reopenTicket( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside " + this.getClass().getSimpleName() + ":" + "reopenTicket" );
        CrmQrcDto crmQrcDto = null;
        CrmSrTicketsPojo ticketsPojo = null;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        String forwardPage = IActionForwardConstant.VIEW_TICKET_PAGE;
        try
        {
            String ticketId = inRequest.getParameter( "ticketID" );
            LOGGER.info( "Reopen TICKET RECORD ID:: " + ticketId );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            MDC.put( "KEY", "RQST USR:" + userDto.getCrmUserDetailsPojo().getUserId() + "TKTID:" + ticketId );
            QrcForm qrcForm = (QrcForm) inForm;
            qrcForm.setSrTicketsPojos( new ArrayList<CrmSrTicketsPojo>() );
            crmQrcDto = new CrmQrcDto();
            ticketsPojo = new CrmSrTicketsPojo();
            ticketsPojo.setTicketId( StringUtils.numericValue( ticketId ) );
            ticketsPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
            crmQrcDto.setCrmSrTicketsPojo( ticketsPojo );
            crmQrcDto = getQrcManagerBm().reopenTickets( crmQrcDto );
            if ( StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), crmQrcDto.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE,
                              new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_REOPENED,
                                                 crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
                if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                {
                    getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                    qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                    SortingComparator<CrmSrTicketsPojo> sorter = new SortingComparator<CrmSrTicketsPojo>( "createdTime" );
                    Collections.sort( qrcForm.getSrTicketsPojos(), Collections.reverseOrder( sorter ) );
                    sorter = null;
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while reopenTicket tickets", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward searchTicket( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction, searchTicket" );
        CrmQrcDto crmQrcDto = null;
        CrmSrTicketsPojo crmSrTicketsPojo = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        HttpSession inSession = inRequest.getSession( false );
        try
        {
            QrcForm qrcForm = (QrcForm) inForm;
            qrcForm.setSrTicketsPojos( new ArrayList<CrmSrTicketsPojo>() );
            crmQrcDto = new CrmQrcDto();
            if ( qrcForm.getSrTicketPojo().getTicketId() > 0 )
            {
                crmSrTicketsPojo = new CrmSrTicketsPojo();
                copyTicketDetailsToEmptyPojo( crmSrTicketsPojo, qrcForm.getSrTicketPojo(), "create" );
                qrcForm.setSrTicketPojo( crmSrTicketsPojo );
            }
            qrcForm.getSrTicketPojo().setMappingId( qrcForm.getCustomerId() );
            if ( StringUtils.isValidObj( qrcForm.getSrTicketPojo() ) )
            {
                MDC.put( "KEY", "CUST ID:" + qrcForm.getCustomerId() );
                if ( StringUtils.isNotEmpty( qrcForm.getSrTicketPojo().getDisplayCreatedTime() ) )
                {
                    qrcForm.getSrTicketPojo().setCreatedTime( DateUtils
                            .changeDateFormat( qrcForm.getSrTicketPojo().getDisplayCreatedTime() ) );
                }
                if ( StringUtils.isNotEmpty( qrcForm.getSrTicketPojo().getDisplayLastModifiedTime() ) )
                {
                    qrcForm.getSrTicketPojo()
                            .setLastModifiedTime( DateUtils
                                    .changeDateFormatWithTime( qrcForm.getSrTicketPojo().getDisplayLastModifiedTime()
                                            + " 23:59:59" ) );
                }
            }
            crmQrcDto.setCrmSrTicketsPojo( qrcForm.getSrTicketPojo() );
            crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
            /// To remove after test
            //            qrcForm.getSrTicketPojo().setCreatedTime( null );
            //            qrcForm.getSrTicketPojo().setLastModifiedTime( null );
            if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                {
                    getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                    qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                    SortingComparator<CrmSrTicketsPojo> sorter = new SortingComparator<CrmSrTicketsPojo>( "createdTime" );
                    Collections.sort( qrcForm.getSrTicketsPojos(), Collections.reverseOrder( sorter ) );
                    sorter = null;
                }
                else
                {
                    qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                    actionErrors.add( IAppConstants.NO_RECORD_FOUND,
                                      new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
            }
            else
            {
                qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
            }
            //qrcForm.setQrcCategoriesPojos( QRCCacheManager.getActiveQrcCategories() );
            qrcForm.setQrcCategoriesPojos( QRCCacheManager.getQrcCategories( CRMStatusCode.ACTIVE
                    .getStatusCode(), (String) inSession.getAttribute( CRMOperationStages.CUSTOMER_CARE.getCode() ) ) );
            if ( qrcForm.getSrTicketPojo().getQrcCategoryId() > 0 )
            {
                qrcForm.setQrcSubCategoriesPojos( QRCCacheManager
                        .getActiveQrcSubCategories( qrcForm.getSrTicketPojo().getQrcCategoryId() ) );
                if ( qrcForm.getSrTicketPojo().getQrcSubCategoryId() > 0 )
                {
                    qrcForm.setQrcSubSubCategoriesPojos( QRCCacheManager
                            .getActiveQrcSubSubCategories( null, qrcForm.getSrTicketPojo().getQrcCategoryId(),
                                                           qrcForm.getSrTicketPojo().getQrcSubCategoryId() ) );
                }
            }
            else
            {
                qrcForm.setQrcSubCategoriesPojos( new ArrayList<CrmQrcSubCategoriesPojo>() );
                qrcForm.setQrcSubSubCategoriesPojos( new ArrayList<CrmQrcSubSubCategoriesPojo>() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while searching tickets", ex );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( IActionForwardConstant.TICKET_PAGE );
    }

    public ActionForward activityLogPage( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.ACTIVITY_LOG_PAGE );
    }

    public ActionForward barringPage( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.BARRING_PAGE );
    }

    public ActionForward barringUnbarringPage( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.BARRING_PAGE;
        ActionMessages errors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            QrcFormHelper.validateCustomerID( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                List<CrmRcaReasonPojo> whitlistReasonList = new ArrayList<CrmRcaReasonPojo>();
                if ( qrcForm.getCustDetailsPojo().getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    whitlistReasonList = QRCCacheManager.getCustomerBarringReasons();
                }
                else if ( qrcForm.getCustDetailsPojo().getStatus().equals( CRMStatusCode.BARRED.getStatusCode() ) )
                {
                    whitlistReasonList = QRCCacheManager.getCustomerUnBarringReasons();
                }
                qrcForm.setCrmRcaReasonPojos( whitlistReasonList );
                qrcForm.setRemarksPojo( new RemarksPojo() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured::: ", e );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward barringUnbarringService( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.BARRING_PAGE;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            QrcFormHelper.checkValidBUBFields( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() + ", Reason:" + qrcForm.getExceptionReason() );
                CrmQrcDto crmQrcDto = getQrcManagerBm().barringUnbarringService( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isNotEmpty( crmQrcDto.getSrTicketNo() ) )
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET,
                                                         crmQrcDto.getSrTicketNo() ) );
                    else
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    forward = IActionForwardConstant.QRC_SEARCH_CUSTOMER_METHOD;
                }
                else
                {
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM403.getStatusCode() )
                            || StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM520.getStatusCode() )
                            || StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM521.getStatusCode() ) )
                    {
                        qrcForm.setInExceptionList( true );
                        /*qrcForm.setExceptionReason( 0 );
                        qrcForm.setSrTicketNo( null );
                        qrcForm.setRemarksPojo( new RemarksPojo() );*/
                        if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                                 CRMStatusCode.ACTIVE.getStatusCode() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( crmQrcDto.getStatusCode(),
                                                           "bar",
                                                           CRMStatusCode.BARRED.getStatusDesc() ) );
                        }
                        else if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                                      CRMStatusCode.BARRED.getStatusCode() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( crmQrcDto.getStatusCode(),
                                                           "unbarr",
                                                           CRMStatusCode.UNBARRED.getStatusDesc() ) );
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                    }
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception:::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward collectedPaymentPage( final ActionMapping inMapping,
                                               final ActionForm inForm,
                                               final HttpServletRequest inRequest,
                                               final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.COLLECTED_PAYMENT_PAGE );
    }

    public ActionForward planMigrationPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.PLAN_MIGRATION_PAGE );
    }

    public ActionForward waiverPage( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages errors = getErrors( inRequest );
        String forwardPage = IActionForwardConstant.WAIVER_PAGE;
        try
        {
            QrcFormHelper.validateCustomerID( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                qrcForm.setCustWaiverPojo( new CrmCustWaiverPojo() );
                qrcForm.getCustWaiverPojo().setWaiverType( QRCRolCategories.GOODWILL_WAIVER.getSubCategory() );
                qrcForm.getCustWaiverPojo().setWaiverAmount( 0.0 );
                qrcForm.setRemarksPojo( new RemarksPojo() );
                CrmQrcDto qrcDto = new CrmQrcDto();
                qrcDto.setCustomerId( qrcForm.getCustomerId() );
                qrcDto = getQrcManagerBm().getBillList( qrcDto );
                if ( CommonValidator.isValidCollection( qrcDto.getInvoiceDetailsPojos() ) )
                {
                    qrcForm.setInvoiceList( qrcDto.getInvoiceDetailsPojos() );
                    LOGGER.info( "recieved invoice size is:" + qrcDto.getInvoiceDetailsPojos().size() );
                }
                else
                {
                    qrcForm.setInvoiceList( new ArrayList<CrmInvoiceDetailsPojo>() );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception:::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward applyWaiver( final ActionMapping inMapping,
                                      final ActionForm inForm,
                                      final HttpServletRequest inRequest,
                                      final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.WAIVER_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        try
        {
            QrcFormHelper.validateWaiver( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                MDC.put( "KEY", "RQSTBY:" + qrcForm.getCrmUserId() + "CUSTOMERID"
                        + qrcForm.getCustWaiverPojo().getCustomerId() );
                CrmQrcDto qrcDto = getQrcManagerBm().applyWaiver( qrcForm );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.getRemarksPojo().setRemarks( null );
                    if ( StringUtils.isEmpty( qrcDto.getSrTicketNo() ) )
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                    else
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET,
                                                         qrcDto.getSrTicketNo() ) );
                    qrcDto = getQrcManagerBm().getCustWaiverPojos( qrcForm );
                    if ( CommonValidator.isValidCollection( qrcDto.getCrmCustWaiverPojosList() ) )
                    {
                        qrcForm.setCustWaiverPojos( qrcDto.getCrmCustWaiverPojosList() );
                        SortingComparator<CrmCustWaiverPojo> sorter = new SortingComparator<CrmCustWaiverPojo>( "createdTime" );
                        Collections.sort( qrcForm.getCustWaiverPojos(), Collections.reverseOrder( sorter ) );
                        sorter = null;
                    }
                }
                else if ( StringUtils.isNotBlank( qrcDto.getBillingErrorCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
                }
                else
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception:::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward viewTicketPage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        HttpSession httpSession = inRequest.getSession( false );
        CrmSrTicketsPojo srTicketPojo = null;
        CustomerProfileForm customerProfileForm = null;
        int pojoIndex;
        String forward = IActionForwardConstant.VIEW_TICKET_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        String inType = inRequest.getParameter( "inType" );
        String inboxId = inRequest.getParameter( "inboxId" );
        LOGGER.info( "Inbox Id: " + inboxId );
        try
        {
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            if ( StringUtils.equals( inRequest.getParameter( "boolValue" ), "true" )
                    && StringUtils.equals( inType, IAppConstants.INBOX ) )
            {
                MasterDataDto dataDto = getMasterDataBm().updateUnreadInbox( StringUtils.numericValue( inboxId ) );
                if ( StringUtils.isValidObj( dataDto )
                        && StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Marked inbox entry to unbold" );
                }
                forward = IActionForwardConstant.EDIT_TICKET_PAGE;
            }
            if ( !StringUtils.isValidObj( srTicketPojo ) )
            {
                srTicketPojo = new CrmSrTicketsPojo();
                srTicketPojo.setTicketId( StringUtils.numericValue( ( inRequest.getParameter( "ticketId" ) ) ) );
                LOGGER.info( "Working Ticket ID : " + srTicketPojo.getTicketId() );
                if ( StringUtils.equals( inType, IAppConstants.INBOX ) )
                {
                    //        MDC.put( "KEY", "inType:" + inType + "  TKTID:" + srTicketPojo.getTicketId() );
                    CrmQrcDto crmqrcDto = getQrcManagerBm().getTicketView( srTicketPojo );
                    if ( StringUtils.isValidObj( crmqrcDto )
                            && StringUtils.equals( crmqrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        srTicketPojo = crmqrcDto.getCrmSrTicketsPojo();
                    }
                    MasterDataDto dataDto = getMasterDataBm().updateUnreadInbox( StringUtils.numericValue( inboxId ) );
                    if ( StringUtils.isValidObj( dataDto )
                            && StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Marked inbox entry to unbold" );
                    }
                    qrcForm.setTicketActions( CRMUtils.getQrcTicketActions() );
                    customerDetailsPojo.setCustomerId( srTicketPojo.getMappingId() );
                    CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                    if ( StringUtils.isValidObj( capDto )
                            && StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        CrmCustomerDetailsPojo capCustomerDetailsPojo = capDto.getCustomerDetailsPojo();
                        qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                        srTicketPojo.setCustomerDetailsPojo( capCustomerDetailsPojo );
                        if ( StringUtils.isValidObj( capCustomerDetailsPojo.getCrmPlanDetailses() ) )
                        {
                            qrcForm.setPlanDetailsPojo( capCustomerDetailsPojo.getCrmPlanDetailses().get( 0 ) );
                        }
                        getQrcManagerBm().getBasePlanInfo( qrcForm );
                        CrmQrcDto qrcDto = getQrcManagerBm().getCustAdditionDetails( qrcForm.getCustDetailsPojo() );
                        qrcForm.setCustAdditionalDetails( qrcDto.getCustAdditionalDetails() );
                        qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                        qrcForm.setPartnerNetworkConfigPojo( capDto.getCrmPartnerNetworkConfig() );
                        setAddressPojo( qrcForm, capCustomerDetailsPojo.getCrmAddressDetailses() );
                    }
                }
                else if ( StringUtils.equals( inType, IAppConstants.CUSTOMER_PROFILE ) )
                {
                    customerProfileForm = (CustomerProfileForm) httpSession.getAttribute( "customerProfileForm" );
                    if ( StringUtils.isValidObj( customerProfileForm ) )
                    {
                        MDC.put( "KEY", "inType:" + inType + "  TKTID:" + srTicketPojo.getTicketId() );
                        if ( CommonValidator.isValidCollection( customerProfileForm.getSrTicketsPojos() ) )
                        {
                            pojoIndex = customerProfileForm.getSrTicketsPojos().indexOf( srTicketPojo );
                            if ( pojoIndex >= 0 )
                            {
                                srTicketPojo = customerProfileForm.getSrTicketsPojos().get( pojoIndex );
                            }
                        }
                        customerDetailsPojo.setCustomerId( srTicketPojo.getMappingId() );
                        CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                        if ( StringUtils.isValidObj( capDto )
                                && StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            CrmCustomerDetailsPojo capCustomerDetailsPojo = capDto.getCustomerDetailsPojo();
                            qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                            srTicketPojo.setCustomerDetailsPojo( capCustomerDetailsPojo );
                            if ( StringUtils.isValidObj( capCustomerDetailsPojo.getCrmPlanDetailses() ) )
                            {
                                qrcForm.setPlanDetailsPojo( capCustomerDetailsPojo.getCrmPlanDetailses().get( 0 ) );
                            }
                            getQrcManagerBm().getBasePlanInfo( qrcForm );
                            CrmQrcDto qrcDto = getQrcManagerBm().getCustAdditionDetails( qrcForm.getCustDetailsPojo() );
                            qrcForm.setCustAdditionalDetails( qrcDto.getCustAdditionalDetails() );
                            qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                            qrcForm.setPartnerNetworkConfigPojo( capDto.getCrmPartnerNetworkConfig() );
                            setAddressPojo( qrcForm, capCustomerDetailsPojo.getCrmAddressDetailses() );
                        }
                    }
                }
                else if ( StringUtils.equals( inType, "qrcTicketView" ) )
                {
                    inType = IAppConstants.CUSTOMER_PROFILE;
                    qrcForm = (QrcForm) httpSession.getAttribute( "qrcForm" );
                    if ( StringUtils.isValidObj( qrcForm )
                            && CommonValidator.isValidCollection( qrcForm.getSrTicketsPojos() ) )
                    {
                        pojoIndex = qrcForm.getSrTicketsPojos().indexOf( srTicketPojo );
                        if ( pojoIndex >= 0 )
                        {
                            srTicketPojo = qrcForm.getSrTicketsPojos().get( pojoIndex );
                        }
                    }
                }
                if ( StringUtils.equals( srTicketPojo.getModuleType(), CRMRequestType.LMS.getRequestCode() ) )
                {
                    MDC.put( "KEY", srTicketPojo.getModuleType() + "  TKTID:" + srTicketPojo.getMappingId() );
                    LmsDto lmsDto = getCustProfileMgrImpl()
                            .leadCustomerProfileSearch( CustomerProfile.LEAD_ID.getCode(),
                                                        srTicketPojo.getMappingId() );
                    if ( StringUtils.isValidObj( lmsDto )
                            && CommonValidator.isValidCollection( lmsDto.getLeadPojos() ) )
                    {
                        qrcForm.setLmsPojo( lmsDto.getLeadPojos().get( 0 ) );
                    }
                    else
                    {
                        qrcForm.setLmsPojo( new LmsPojo() );
                    }
                }
            }
            qrcForm.setTicketHistory( new CrmTicketHistoryPojo() );
            qrcForm.setCrmQrcActionTaken( new CrmQrcActionTakenPojo() );
            qrcForm.setCrmQrcRootCause( new CrmQrcRootCausePojo() );
            qrcForm.setBoolValue( inRequest.getParameter( "boolValue" ) );
            qrcForm.setInType( inType );
            qrcForm.setSrTicketPojo( srTicketPojo );
            if ( StringUtils.equals( inType, IAppConstants.INBOX ) )
            {
                String category = QRCCacheManager.getQrcCategory( srTicketPojo.getQrcCategoryId() );
                if ( StringUtils.isNotEmpty( category ) )
                {
                    srTicketPojo.setQrcCategory( category );
                }
                String subCategory = QRCCacheManager.getQrcSubCategory( srTicketPojo.getQrcCategoryId(),
                                                                        srTicketPojo.getQrcSubCategoryId() );
                if ( StringUtils.isNotEmpty( subCategory ) )
                {
                    srTicketPojo.setQrcSubCategory( subCategory );
                }
                String subSubCategory = QRCCacheManager.getQrcSubSubCategory( srTicketPojo.getQrcType(),
                                                                              srTicketPojo.getQrcCategoryId(),
                                                                              srTicketPojo.getQrcSubCategoryId(),
                                                                              srTicketPojo.getQrcSubSubCategoryId() );
                if ( StringUtils.isNotEmpty( subSubCategory ) )
                {
                    srTicketPojo.setQrcSubSubCategory( subSubCategory );
                }
                qrcForm.setQrcActions( "0" );
                qrcForm.setSrTicketPojo( srTicketPojo );
                qrcForm.setQrcSubSubCategoriesPojos( QRCCacheManager
                        .getActiveQrcSubSubCategories( srTicketPojo.getQrcType(), srTicketPojo.getQrcCategoryId(),
                                                       srTicketPojo.getQrcSubCategoryId() ) );
            }
            if ( null != srTicketPojo.getFollowupOn() )
            {
                if ( ( DateUtils.convertXmlCalToDate( srTicketPojo.getFollowupOn() ) )
                        .compareTo( Calendar.getInstance().getTime() ) > 0 )
                {
                    qrcForm.setVisibileButton( true );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( " viewTicketPage Exception:::", e );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward addWhiteList( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages errors = getErrors( inRequest );
        String forwardPage = IActionForwardConstant.ADD_SINGLE_WHITE_LIST;
        try
        {
            QrcFormHelper.validateCustomerID( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                CrmQrcWhitelistPojo crmQrcWhitelistPojo = new CrmQrcWhitelistPojo();
                crmQrcWhitelistPojo.setCustomerId( qrcForm.getCustomerId() );
                List<CrmRcaReasonPojo> whitlistReasonList = new ArrayList<CrmRcaReasonPojo>();
                if ( qrcForm.getCustDetailsPojo().getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    crmQrcWhitelistPojo.setWhitelistType( CRMStatusCode.BARRED.getStatusDesc() );
                    whitlistReasonList = QRCCacheManager.getWhitelistBarringReasons();
                }
                else if ( qrcForm.getCustDetailsPojo().getStatus().equals( CRMStatusCode.BARRED.getStatusCode() ) )
                {
                    crmQrcWhitelistPojo.setWhitelistType( CRMStatusCode.UNBARRED.getStatusDesc() );
                    whitlistReasonList = QRCCacheManager.getWhitelistUnBarringReasons();
                }
                qrcForm.setCrmQrcWhitelistPojo( crmQrcWhitelistPojo );
                qrcForm.setCrmRcaReasonPojos( whitlistReasonList );
                qrcForm.setRemarksPojo( new RemarksPojo() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception while searching tickets", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward addUpdateWhiteList( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.ADD_SINGLE_WHITE_LIST;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            QrcFormHelper.checkValidDetails( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                CrmQrcDto crmQrcDto = getQrcManagerBm().addUpdateWhiteList( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_BARRING_EXCEPTION_ADDED_TO,
                                                     qrcForm.getCrmQrcWhitelistPojo().getWhitelistType() ) );
                    forward = IActionForwardConstant.ADD_SINGLE_WHITE_LIST;
                }
                else if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM403.getStatusCode() )
                        || StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM520.getStatusCode() )
                        || StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM521.getStatusCode() ) )
                {
                    LOGGER.info( crmQrcDto.getStatusCode() );
                    qrcForm.setRemoveOption( "Remove" );
                    forward = IActionForwardConstant.ADD_SINGLE_WHITE_LIST;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward removeFromWhiteList( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.ADD_SINGLE_WHITE_LIST;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            Map<String, Object[]> inResultMap = null;
            inResultMap = QrcFormHelper.validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
            if ( StringUtils.isValidObj( inResultMap ) )
            {
                ValidationUtil.prepareErrorMessage( errors, inResultMap );
            }
            if ( errors.isEmpty() )
            {
                CrmQrcDto crmQrcDto = getQrcManagerBm().removeFromWhiteList( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setRemoveOption( null );
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_BARRING_EXCEPTION_REMOVED_FROM,
                                                     qrcForm.getCrmQrcWhitelistPojo().getWhitelistType() ) );
                    forward = IActionForwardConstant.ADD_SINGLE_WHITE_LIST;
                }
                else if ( StringUtils.isNotBlank( crmQrcDto.getBillingErrorCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                }
                else
                {
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM403.getStatusCode() ) )
                    {
                        if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                                 CRMStatusCode.ACTIVE.getStatusCode() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( crmQrcDto.getStatusCode(),
                                                           CRMStatusCode.BARRED.getStatusDesc() ) );
                        }
                        else if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                                      CRMStatusCode.BARRED.getStatusCode() ) )
                        {
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( crmQrcDto.getStatusCode(),
                                                           CRMStatusCode.UNBARRED.getStatusDesc() ) );
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    }
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward removeFromWhiteListBeforeBUB( final ActionMapping inMapping,
                                                       final ActionForm inForm,
                                                       final HttpServletRequest inRequest,
                                                       final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.BARRING_PAGE;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        Map<String, Object[]> inResultMap = null;
        try
        {
            inResultMap = QrcFormHelper.validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
            if ( StringUtils.isValidObj( inResultMap ) )
            {
                LOGGER.info( inResultMap.size() );
                ValidationUtil.prepareErrorMessage( errors, inResultMap );
            }
            if ( errors.isEmpty() )
            {
                CrmQrcWhitelistPojo qrcWhitelistPojo = new CrmQrcWhitelistPojo();
                qrcWhitelistPojo.setCustomerId( qrcForm.getCustomerId() );
                if ( qrcForm.getCustDetailsPojo().getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
                {
                    qrcWhitelistPojo.setWhitelistType( CRMStatusCode.BARRED.getStatusDesc() );
                }
                else if ( qrcForm.getCustDetailsPojo().getStatus().equals( CRMStatusCode.BARRED.getStatusCode() ) )
                {
                    qrcWhitelistPojo.setWhitelistType( CRMStatusCode.UNBARRED.getStatusDesc() );
                }
                qrcForm.setCrmQrcWhitelistPojo( qrcWhitelistPojo );
                CrmQrcDto crmQrcDto = getQrcManagerBm().removeFromWhiteList( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                             CRMStatusCode.ACTIVE.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_BARRING_EXCEPTION_REMOVED_FROM,
                                                         CRMStatusCode.BARRED.getStatusDesc() ) );
                    }
                    else if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                                  CRMStatusCode.BARRED.getStatusCode() ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_BARRING_EXCEPTION_REMOVED_FROM,
                                                         CRMStatusCode.UNBARRED.getStatusDesc() ) );
                    }
                    /* qrcForm.setSrTicketNo( null );
                     qrcForm.setExceptionReason( 0 );
                     qrcForm.setRemarksPojo( new RemarksPojo() );*/
                    qrcForm.setInExceptionList( false );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward bulkWhitelist( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        ActionMessages messages = new ActionMessages();
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String fileName = qrcForm.getWhitelistExcelFile().getFileName();
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        try
        {
            QrcFormHelper.bulkWhitelistFormValidation( actionErrors, qrcForm, fileName );
            if ( actionErrors.isEmpty() )
            {
                String filePath = getServlet().getServletContext().getRealPath( "/" ) + "WhitelistExcelUpload";
                List<String> statusList = getQrcManagerBm().processBulkWhitelistExcel( qrcForm, user, filePath );
                if ( statusList.size() > 1 )
                {
                    String errorFile = statusList.get( 1 );
                    String status = statusList.get( 0 ).substring( 0, statusList.get( 0 ).indexOf( "_" ) );
                    int validCount = Integer
                            .parseInt( statusList.get( 0 ).substring( statusList.get( 0 ).indexOf( "_" ) + 1,
                                                                      statusList.get( 0 ).lastIndexOf( "_" ) ) );
                    int totalRecords = Integer
                            .parseInt( statusList.get( 0 ).substring( statusList.get( 0 ).lastIndexOf( "_" ) + 1 ) );
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
                    }
                    qrcForm.setCrmQrcWhitelistPojo( new CrmQrcWhitelistPojo() );
                    qrcForm.setRemarksPojo( new RemarksPojo() );
                    qrcForm.setEndDate( null );
                }
                else
                    actionErrors.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.BULK_WHITELIST_PAGE );
    }

    public ActionForward logTicket( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : logTicket" );
        String target = IActionForwardConstant.TICKET_PAGE;
        CrmQrcDto crmQrcDto = null;
        QrcForm qrcForm = (QrcForm) inForm;
        CrmuserDetailsDto userDto = null;
        CrmSrTicketsPojo crmSrTicketsPojo = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        QrcFormHelper.validateServiceRequestPOJO( errors, qrcForm );
        HttpSession inSession = inRequest.getSession( false );
        LOGGER.info( "Action Error Message : " + errors );
        if ( errors.isEmpty() )
        {
            try
            {
                if ( qrcForm.getSrTicketPojo().getTicketId() > 0 )
                {
                    MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                    crmSrTicketsPojo = new CrmSrTicketsPojo();
                    copyTicketDetailsToEmptyPojo( crmSrTicketsPojo, qrcForm.getSrTicketPojo(), "create" );
                    qrcForm.setSrTicketPojo( crmSrTicketsPojo );
                }
                userDto = (CrmuserDetailsDto) inSession.getAttribute( IAppConstants.CRM_USER_OBJECT );
                String currentUser = userDto.getCrmUserDetailsPojo().getUserId();
                crmQrcDto = new CrmQrcDto();
                crmQrcDto.setUserId( currentUser );
                qrcForm.getSrTicketPojo().setCreatedBy( currentUser );
                qrcForm.getSrTicketPojo().setModuleType( CRMRequestType.QRC.getRequestCode() );
                crmQrcDto.setCrmSrTicketsPojo( qrcForm.getSrTicketPojo() );
                crmQrcDto.setCustAdditionalDetails( qrcForm.getCustAdditionalDetails() );
                crmQrcDto.setTicketHistory( qrcForm.getTicketHistory() );
                crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                if ( qrcForm.getSrTicketPojo().getResolutionType() == 1 )
                {
                    crmQrcDto.setFutureStage( qrcForm.getSrTicketPojo().getFunctionalbinId() + "" );
                }
                LOGGER.info( "Ticket Pojo : " + qrcForm.getSrTicketPojo() );
                crmQrcDto = getQrcManagerBm().logQrcTicket( crmQrcDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_LOGGED,
                                                     crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                }
                else if ( StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_REOPENED,
                                                     crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                }
                else if ( StringUtils.equals( CRMServiceCode.CRM404.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_EXIST_FORWARD,
                                                     crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() )
                        || StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(), crmQrcDto.getStatusCode() )
                        || StringUtils.equals( CRMServiceCode.CRM404.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    CrmQrcDto dto = new CrmQrcDto();
                    CrmSrTicketsPojo ticketsPojo = new CrmSrTicketsPojo();
                    ticketsPojo.setMappingId( qrcForm.getCustomerId() );
                    ticketsPojo.setQrcCategoryId( qrcForm.getSrTicketPojo().getQrcCategoryId() );
                    ticketsPojo.setQrcSubCategoryId( qrcForm.getSrTicketPojo().getQrcSubCategoryId() );
                    ticketsPojo.setQrcSubSubCategoryId( qrcForm.getSrTicketPojo().getQrcSubSubCategoryId() );
                    dto.setCrmSrTicketsPojo( ticketsPojo );
                    dto = getQrcManagerBm().getCustomerTickets( dto );
                    if ( StringUtils.equals( dto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( dto.getCrmSrTicketsPojos() ) )
                        {
                            getQrcManagerBm().setCategoriesNameById( dto.getCrmSrTicketsPojos() );
                            qrcForm.setSrTicketsPojos( dto.getCrmSrTicketsPojos() );
                            SortingComparator<CrmSrTicketsPojo> sorter = new SortingComparator<CrmSrTicketsPojo>( "createdTime" );
                            Collections.sort( qrcForm.getSrTicketsPojos(), Collections.reverseOrder( sorter ) );
                            sorter = null;
                        }
                    }
                }
                qrcForm.getSrTicketPojo().setCreatedTime( null );
                qrcForm.getSrTicketPojo().setLastModifiedTime( null );
                LOGGER.info( "Ticket Pojo : " + qrcForm.getSrTicketPojo() );
                setRequiredCollections( qrcForm );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while logging ticket for customerID : "
                        + qrcForm.getSrTicketPojo().getMappingId() + " ", ex );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( target );
    }

    private void copyTicketDetailsToEmptyPojo( CrmSrTicketsPojo inCrmSrTicketsPojo,
                                               CrmSrTicketsPojo inSrTicketPojo,
                                               String inParameter )
    {
        LOGGER.info( "Inside QrcAction, -copyTicketDetailsToEmptyPojo" );
        try
        {
            inCrmSrTicketsPojo.setQrcCategoryId( inSrTicketPojo.getQrcCategoryId() );
            inCrmSrTicketsPojo.setQrcSubCategoryId( inSrTicketPojo.getQrcSubCategoryId() );
            inCrmSrTicketsPojo.setQrcSubSubCategoryId( inSrTicketPojo.getQrcSubSubCategoryId() );
            if ( StringUtils.equals( inParameter, "create" ) )
            {
                inCrmSrTicketsPojo.setResolutionType( inSrTicketPojo.getResolutionType() );
                inCrmSrTicketsPojo.setMappingId( inSrTicketPojo.getMappingId() );
                inCrmSrTicketsPojo.setFunctionalbinId( inSrTicketPojo.getFunctionalbinId() );
                inCrmSrTicketsPojo.setQrcType( inSrTicketPojo.getQrcType() );
                inCrmSrTicketsPojo.setCallingNo( inSrTicketPojo.getCallingNo() );
                if ( StringUtils.equals( inCrmSrTicketsPojo.getQrcType(), CRMDisplayListConstants.COMPLAINTS.getCode() )
                        && inCrmSrTicketsPojo.getResolutionType() == CrmTicketActions.ROL.getCode() )
                {
                    inCrmSrTicketsPojo.setActionTakenId( inSrTicketPojo.getActionTakenId() );
                    inCrmSrTicketsPojo.setRootCauseId( inSrTicketPojo.getRootCauseId() );
                }
            }
            else if ( StringUtils.equals( inParameter, "search" ) )
            {
                if ( StringUtils.isNotBlank( inSrTicketPojo.getDisplayCreatedTime() ) )
                {
                    inCrmSrTicketsPojo.setDisplayCreatedTime( inSrTicketPojo.getDisplayCreatedTime() );
                }
                if ( StringUtils.isNotBlank( inSrTicketPojo.getDisplayLastModifiedTime() ) )
                {
                    inCrmSrTicketsPojo.setDisplayLastModifiedTime( inSrTicketPojo.getDisplayLastModifiedTime() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while copy ticket pojo data: ", ex );
        }
    }

    private void setRequiredCollections( QrcForm inQrcForm )
    {
        LOGGER.info( "Inside QrcAction, -setRequiredCollections" );
        inQrcForm.setQrcCategoriesPojos( QRCCacheManager.getActiveQrcCategories() );
        inQrcForm.setQrcSubCategoriesPojos( new ArrayList<CrmQrcSubCategoriesPojo>() );
        inQrcForm.setQrcSubSubCategoriesPojos( new ArrayList<CrmQrcSubSubCategoriesPojo>() );
        inQrcForm.setTicketActions( new ArrayList<ContentPojo>() );
        inQrcForm.setRcaList( new ArrayList<ContentPojo>() );
        inQrcForm.setResoulationCodelist( new ArrayList<ContentPojo>() );
        long categoryID = inQrcForm.getSrTicketPojo().getQrcCategoryId();
        long subCategoryID = inQrcForm.getSrTicketPojo().getQrcSubCategoryId();
        long subSubCategoryID = inQrcForm.getSrTicketPojo().getQrcSubSubCategoryId();
        String qrcType = inQrcForm.getSrTicketPojo().getQrcType();
        if ( categoryID > 0 )
        {
            inQrcForm.setQrcSubCategoriesPojos( QRCCacheManager.getActiveQrcSubCategories( categoryID ) );
            if ( StringUtils.isNotBlank( qrcType ) && subCategoryID > 0 )
            {
                inQrcForm.setQrcSubSubCategoriesPojos( QRCCacheManager
                        .getActiveQrcSubSubCategories( qrcType, categoryID, subCategoryID ) );
                if ( subSubCategoryID > 0 )
                {
                    inQrcForm.setTicketActions( QRCCacheManager.getQrcActionsList( categoryID, subCategoryID,
                                                                                   subSubCategoryID ) );
                }
            }
        }
    }

    public ActionForward bulkWhitelistPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        CrmQrcWhitelistPojo crmQrcWhitelistPojo = new CrmQrcWhitelistPojo();
        RemarksPojo remarksPojo = new RemarksPojo();
        qrcForm.setRemarksPojo( remarksPojo );
        qrcForm.setCrmQrcWhitelistPojo( crmQrcWhitelistPojo );
        qrcForm.getCrmQrcWhitelistPojo().setWhitelistType( "Barred" );
        qrcForm.setRemarksPojo( new RemarksPojo() );
        qrcForm.setCrmRcaReasonPojos( QRCCacheManager.getWhitelistBarringReasons() );
        return inMapping.findForward( IActionForwardConstant.BULK_WHITELIST_PAGE );
    }

    public ActionForward disconnectionPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        ActionMessages errors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String forward = IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE;
        QrcFormHelper.validateCustomerID( errors, qrcForm );
        if ( errors.isEmpty() )
        {
            forward = IActionForwardConstant.DISCONNECTION_PAGE;
            if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(), CRMStatusCode.ACTIVE.getStatusCode() )
                    || StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                           CRMStatusCode.BARRED.getStatusCode() ) )
            {
                qrcForm.setDisconnectionReasons( QRCCacheManager.getTemporaryDisconnectionReasons() );
            }
            else if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(), CRMStatusCode.TDC.getStatusCode() )
                    || StringUtils.equals( qrcForm.getCustDetailsPojo().getStatus(),
                                           CRMStatusCode.SC.getStatusCode() ) )
            {
                qrcForm.setDisconnectionReasons( QRCCacheManager.getPermanentDisconnectionReasons() );
            }
        }
        return inMapping.findForward( forward );
    }

    public ActionForward disconnection( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        String forward = IActionForwardConstant.DISCONNECTION_PAGE;
        try
        {
            QrcForm qrcForm = (QrcForm) inForm;
            String oldStatus = qrcForm.getCustDetailsPojo().getStatus();
            QrcFormHelper.validateDisconnection( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() + ", USERID:" + qrcForm.getCrmUserId() + ", REASON:"
                        + qrcForm.getRemarksPojo().getReasonId() );
                CrmQrcDto crmQrcDto = getQrcManagerBm().disconnectionCall( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isEmpty( crmQrcDto.getSrTicketNo() ) )
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    else
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET,
                                                         crmQrcDto.getSrTicketNo() ) );
                    forward = IActionForwardConstant.QRC_SEARCH_CUSTOMER_METHOD;
                }
                else if ( StringUtils.isNotBlank( crmQrcDto.getBillingErrorCode() ) )
                {
                    qrcForm.getCustDetailsPojo().setStatus( oldStatus );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                }
                else
                {
                    qrcForm.getCustDetailsPojo().setStatus( oldStatus );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception e )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.error( "Exception:::", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    //    public ActionForward addressChangePage( final ActionMapping inMapping,
    //                                            final ActionForm inForm,
    //                                            final HttpServletRequest inRequest,
    //                                            final HttpServletResponse inResponse )
    //    {
    //        String forward = IActionForwardConstant.ADDRESS_CHANGE_PAGE;
    //        ActionMessages errors = getErrors( inRequest );
    //        QrcForm qrcForm = (QrcForm) inForm;
    //        QrcFormHelper.validateCustomerID( errors, qrcForm );
    //        if ( errors.isEmpty() )
    //        {
    //            qrcForm.setRemarksPojo( new RemarksPojo() );
    //            qrcForm.setSrTicketNo( null );
    //            qrcForm.setNewInstallationAddress( new CrmAddressDetailsPojo() );
    //        }
    //        saveErrors( inRequest, errors );
    //        return inMapping.findForward( forward );
    //    }
    public ActionForward addressChangeFeasibility( final ActionMapping inMapping,
                                                   final ActionForm inForm,
                                                   final HttpServletRequest inRequest,
                                                   final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.ADDRESS_CHANGE_FEASIBILITY );
    }

    public ActionForward resetMyAccountPassword( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages msg = getMessages( inRequest );
        try
        {
            QrcFormHelper.validateCustomerID( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                CrmQrcDto crmQrcDto = getQrcManagerBm().resetPassword( qrcForm );
                LOGGER.info( "Status Code:: " + crmQrcDto.getStatusCode() );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isEmpty( crmQrcDto.getSrTicketNo() ) )
                        msg.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    else
                        msg.add( IAppConstants.APP_MESSAGE,
                                 new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET,
                                                    crmQrcDto.getSrTicketNo() ) );
                    CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                    customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
                    CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                    if ( StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        CrmCustomerDetailsPojo capCustomerDetailsPojo = capDto.getCustomerDetailsPojo();
                        qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                        qrcForm.setPlanDetailsPojo( capCustomerDetailsPojo.getCrmPlanDetailses().get( 0 ) );
                        getQrcManagerBm().getBasePlanInfo( qrcForm );
                        CrmQrcDto qrcDto = getQrcManagerBm().getCustAdditionDetails( qrcForm.getCustDetailsPojo() );
                        qrcForm.setCustAdditionalDetails( qrcDto.getCustAdditionalDetails() );
                        qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                        qrcForm.setPartnerNetworkConfigPojo( capDto.getCrmPartnerNetworkConfig() );
                        setAddressPojo( qrcForm, capCustomerDetailsPojo.getCrmAddressDetailses() );
                    }
                }
                else
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, msg );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    private String resolveStageByBinID( Long inFunctionalbinId )
    {
        LOGGER.info( "Inside QrcAction, resolveStageByBinID" );
        List<CrmRcaReasonPojo> functionalBinCache = CRMCacheManager.getFunctionalBins();
        String stage = null;
        for ( CrmRcaReasonPojo crmRcaReasonPojo : functionalBinCache )
        {
            if ( crmRcaReasonPojo.getCategoryId() == inFunctionalbinId )
            {
                stage = crmRcaReasonPojo.getValueAlias();
                break;
            }
        }
        if ( !StringUtils.isValidObj( stage ) )
        {
            stage = inFunctionalbinId + "";
        }
        return stage;
    }

    //    private void setEmptyFormPojos( QrcForm inQrcForm )
    //    {
    //        if ( !StringUtils.isValidObj( inQrcForm.getBillingAddressPojo() ) )
    //        {
    //            inQrcForm.setBillingAddressPojo( new CrmAddressDetailsPojo() );
    //        }
    //        if ( !StringUtils.isValidObj( inQrcForm.getInstallationAddressPojo() ) )
    //        {
    //            inQrcForm.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
    //        }
    //        if ( !StringUtils.isValidObj( inQrcForm.getCustDetailsPojo() ) )
    //        {
    //            inQrcForm.setCustDetailsPojo( new CrmCustomerDetailsPojo() );
    //        }
    //        if ( !StringUtils.isValidObj( inQrcForm.getRemarksPojo() ) )
    //        {
    //            inQrcForm.setRemarksPojo( new RemarksPojo() );
    //        }
    //        if ( !StringUtils.isValidObj( inQrcForm.getQrcCategoriesPojo() ) )
    //        {
    //            inQrcForm.setQrcCategoriesPojo( new CrmQrcCategoriesPojo() );
    //        }
    //    }
    public ActionForward customerInteractionPage( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : customerInteractionPage" );
        QrcForm qrcForm = (QrcForm) inForm;
        if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) )
        {
            LOGGER.info( "Form have valid object of customer" );
            List<CrmRcaReasonPojo> interactionSubCategories = new ArrayList<CrmRcaReasonPojo>();
            CrmCustInteractionsPojo custInteractionsPojo = new CrmCustInteractionsPojo();
            custInteractionsPojo.setCustomerId( qrcForm.getCustDetailsPojo().getCustomerId() );
            qrcForm.setCustInteractionsPojo( custInteractionsPojo );
            CrmQrcDto qrcDto = new CrmQrcDto();
            qrcDto.setCustInteractionsPojo( custInteractionsPojo );
            qrcDto = getQrcManagerBm().searchCustomerInteraction( qrcDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
            {
                qrcForm.setCustInteractionsPojos( qrcDto.getCustInteractionsPojos() );
            }
            qrcForm.setInteractionSubCategories( interactionSubCategories );
            qrcForm.setCustomerInteractionCategories( CRMUtils
                    .getCategories( CRMRCAReason.QRC.getStatusCode(), CRMRCAReason.QRC_INTERACTION.getStatusCode() ) );
        }
        return inMapping.findForward( IActionForwardConstant.CUSTOMER_INTERACTION_PAGE );
    }

    public ActionForward createCustomerInteraction( final ActionMapping inMapping,
                                                    final ActionForm inForm,
                                                    final HttpServletRequest inRequest,
                                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : createCustomerInteraction" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        QrcForm qrcForm = (QrcForm) inForm;
        CrmCustInteractionsPojo custInteractionsPojo = null;
        CrmQrcDto qrcDto = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String target = IActionForwardConstant.CUSTOMER_INTERACTION_PAGE;
        try
        {
            custInteractionsPojo = qrcForm.getCustInteractionsPojo();
            if ( StringUtils.isValidObj( custInteractionsPojo ) )
            {
                QrcFormHelper.validateCreateCustInteraction( actionErrors, custInteractionsPojo );
                if ( actionErrors.isEmpty() )
                {
                    qrcDto = new CrmQrcDto();
                    MDC.put( "KEY",
                             "CUSTID:" + qrcForm.getCustomerId() + ", CATOGORY"
                                     + qrcForm.getCustInteractionsPojo().getInteractionCategory() + ", SUBCATEGORY"
                                     + qrcForm.getCustInteractionsPojo().getInteractionSubCategory() );
                    custInteractionsPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                    qrcDto.setCustInteractionsPojo( custInteractionsPojo );
                    qrcDto = getQrcManagerBm().createCustomerInteraction( qrcDto );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
                    {
                        custInteractionsPojo.setRemarks( null );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                        qrcDto = new CrmQrcDto();
                        custInteractionsPojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                        qrcDto.setCustInteractionsPojo( custInteractionsPojo );
                        qrcDto = getQrcManagerBm().searchCustomerInteraction( qrcDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
                        {
                            qrcForm.setCustInteractionsPojos( qrcDto.getCustInteractionsPojos() );
                        }
                    }
                    else
                    {
                        actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.error( "Exception while creating Customer Interaction ", ex );
        }
        qrcForm.setCustomerInteractionCategories( CRMUtils
                .getCategories( CRMRCAReason.QRC.getStatusCode(), CRMRCAReason.QRC_INTERACTION.getStatusCode() ) );
        qrcForm.setInteractionSubCategories( CRMCacheManager
                .getCustInteractionSubCategories( qrcForm.getCustInteractionsPojo().getInteractionCategory() ) );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( target );
    }

    public ActionForward searchCustomerInteraction( final ActionMapping inMapping,
                                                    final ActionForm inForm,
                                                    final HttpServletRequest inRequest,
                                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : searchCustomerInteraction" );
        QrcForm qrcForm = (QrcForm) inForm;
        CrmCustInteractionsPojo custInteractionsPojo = null;
        CrmQrcDto qrcDto = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String target = IActionForwardConstant.CUSTOMER_INTERACTION_PAGE;
        try
        {
            custInteractionsPojo = qrcForm.getCustInteractionsPojo();
            if ( StringUtils.isValidObj( custInteractionsPojo ) )
            {
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                qrcDto = new CrmQrcDto();
                qrcDto.setCustInteractionsPojo( custInteractionsPojo );
                qrcDto = getQrcManagerBm().searchCustomerInteraction( qrcDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
                {
                    qrcForm.setCustInteractionsPojos( qrcDto.getCustInteractionsPojos() );
                    if ( !CommonValidator.isValidCollection( qrcDto.getCustInteractionsPojos() ) )
                    {
                        actionErrors.add( IAppConstants.NO_RECORD_FOUND,
                                          new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while searching Customer Interaction ", ex );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        qrcForm.setCustomerInteractionCategories( CRMUtils
                .getCategories( CRMRCAReason.QRC.getStatusCode(), CRMRCAReason.QRC_INTERACTION.getStatusCode() ) );
        if ( StringUtils.isNotEmpty( custInteractionsPojo.getInteractionCategory() ) )
        {
            qrcForm.setInteractionSubCategories( CRMCacheManager
                    .getCustInteractionSubCategories( qrcForm.getCustInteractionsPojo().getInteractionCategory() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( target );
    }

    public ActionForward deviceChangePage( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : deviceChangePage" );
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String forward = IActionForwardConstant.DEVICE_CHANGE_PAGE;
        qrcForm.setRemarksPojo( new RemarksPojo() );
        //qrcForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
        //        CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
        try
        {
            QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
            if ( actionErrors.isEmpty() )
            {
                if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                {
                    PartnerPojo partner = CommonUtils
                            .getPartnerById( StringUtils.numericValue( qrcForm.getCustDetailsPojo().getNpId() + "" ) );
                    if ( StringUtils.isValidObj( partner ) )
                    {
                        ConfigDto configDto = getCrmConfigManager()
                                .getMacAddressByNP( partner.getCrmPartnerCode(),
                                                    qrcForm.getCustDetailsPojo().getCustomerId(),
                                                    CRMRequestType.INA.getRequestCode() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), configDto.getStatusCode() ) )
                        {
                            if ( CommonValidator.isValidCollection( configDto.getMacAddressList() ) )
                            {
                                qrcForm.setSecondaryMACAddrList( configDto.getMacAddressList() );
                            }
                        }
                    }
                }
                //                customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
                //                CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                //                LOGGER.info( "customerDetails Pojo @ QRCACTION:DEVICE_CHANGE_PAGE status code for customer profile is:"
                //                        + capDto.getStatusCode() + ":" + capDto.getStatusDesc() );
                //                if ( StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                //                {
                //                    if ( CommonValidator.isValidCollection( capDto.getCustomerDetailsPojosList() ) )
                //                    {
                //                        LOGGER.info( "customerdetailsPojoList:" + capDto.getCustomerDetailsPojosList().size() );
                //                        CrmCustomerDetailsPojo capCustomerDetailsPojo = capDto.getCustomerDetailsPojosList().get( 0 );
                //                        if ( !StringUtils.isEmpty( capCustomerDetailsPojo.getCustomerId() ) )
                //                        {
                //                            qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                //                            qrcForm.setCustDetailsPojo( capCustomerDetailsPojo );
                //                            qrcForm.setCrmPartnerNetworkConfig( capDto.getCrmPartnerNetworkConfig() );
                //                            if ( StringUtils.isValidObj( capDto.getOrderDetailsPojo() ) )
                //                            {
                //                                LOGGER.info( "orderDetailsPojoooo:" + capDto.getOrderDetailsPojo() );
                //                            }
                //                            else
                //                            {
                //                                LOGGER.info( "orderDetailsPojooo is null" );
                //                            }
                //                            qrcForm.setOrderDetailsPojo( capDto.getOrderDetailsPojo() );
                //                        }
                //                    }
                //                }
            }
            else
            {
                forward = IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE;
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    public ActionForward editMac( final ActionMapping inMapping,
                                  final ActionForm inForm,
                                  final HttpServletRequest inRequest,
                                  final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : macChange" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String requestType = inRequest.getParameter( "inRequestType" );
        QrcForm qrcForm = (QrcForm) inForm;
        String forward = null;
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = null;
        String newPrimaryMacId = qrcForm.getNewPrimaryMacId();
        String oldPrimaryMacId = qrcForm.getNetworkConfigurationsPojo().getCurrentCpeMacId();
        if ( StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
        {
            shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
            shiftingWorkflowPojo.setWorkflowId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
            shiftingWorkflowPojo.setCustomerId( qrcForm.getCustomerId() );
            shiftingWorkflowPojo.setShiftingType( qrcForm.getShiftingWorkflowPojo().getShiftingType() );
            shiftingWorkflowPojo.setWorkflowStage( qrcForm.getShiftingWorkflowPojo().getWorkflowStage() );
            qrcForm.setParamValue( IAppConstants.WORKFLOW );
            forward = IActionForwardConstant.NOC_LEVEL1;
        }
        else
        {
            forward = IActionForwardConstant.DEVICE_CHANGE_PAGE;
        }
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            QrcFormHelper.validateMac( actionErrors, qrcForm );
            LOGGER.info( actionErrors.size() );
            if ( actionErrors.isEmpty() )
            {
                CrmQrcDto crmQrcDto = new CrmQrcDto();
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                crmQrcDto.setActivityAction( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() );
                qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( qrcForm.getNewPrimaryMacId() );
                //EOC
                if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                {
                    if ( StringUtils.isNotBlank( qrcForm.getNewSecondaryMacId() ) )
                    {
                        qrcForm.getNetworkConfigurationsPojo().setCurrentSlaveMacId( qrcForm.getNewSecondaryMacId() );
                    }
                    crmQrcDto.setMacFaulty( qrcForm.isMacFaulty() );
                }
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setProduct( qrcForm.getCustDetailsPojo().getProduct() );
                crmQrcDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                if ( StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
                {
                    crmQrcDto.getRemarksPojo().setMappingId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                }
                crmQrcDto.setNetworkConfigurationsPojo( qrcForm.getNetworkConfigurationsPojo() );
                crmQrcDto = getQrcManagerBm().editDevice( crmQrcDto );
                String statusCode = crmQrcDto.getStatusCode();
                if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setNetworkConfigurationsPojo( crmQrcDto.getNetworkConfigurationsPojo() );
                    qrcForm.setNewPrimaryMacId( null );
                    qrcForm.setNewSecondaryMacId( null );
                    qrcForm.setRemarksPojo( new RemarksPojo() );
                    if ( StringUtils.isValidObj( shiftingWorkflowPojo ) )
                    {
                        qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
                    }
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_DEVICE_MAC_CHANGE ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                    qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( oldPrimaryMacId );
                    qrcForm.setNewPrimaryMacId( newPrimaryMacId );
                    if ( StringUtils.isEmpty( qrcForm.getBoolValue() ) )
                    {
                        qrcForm.setBoolValue( qrcForm.getRemarksPojo().getRemarks() );
                    }
                }
                // LOGGER.info( "new MAC" + qrcForm.getNewSlaveMacId() + "new Option82:" + qrcForm.getNewOption82() );
            }
            // }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        if ( StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
        {
            saveErrors( inRequest.getSession(), actionErrors );
            saveMessages( inRequest.getSession(), messages );
            actionErrors = null;
            messages = null;
        }
        else
        {
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest, messages );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward editOption82( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : editOption82" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String requestType = inRequest.getParameter( "inRequestType" );
        LOGGER.info( "req type :: " + requestType );
        String forward = IActionForwardConstant.DEVICE_CHANGE_PAGE;
        String custId = inRequest.getParameter( "customerId" );
        //check for remarks
        //check for SRT
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        try
        {
            QrcFormHelper.validateOption82( actionErrors, qrcForm );
            if ( actionErrors.isEmpty() )
            {
                CrmQrcDto crmQrcDto = new CrmQrcDto();
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                String oldOption82 = qrcForm.getNetworkConfigurationsPojo().getOption82();
                String newOption82 = qrcForm.getNewOption82();
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                crmQrcDto.setActivityAction( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc() );
                qrcForm.getNetworkConfigurationsPojo().setOption82( qrcForm.getNewOption82() );
                crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                if ( StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
                {
                    crmQrcDto.getRemarksPojo().setMappingId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                    forward = IActionForwardConstant.INSTALLATION_PARTNER_STAGE;
                    LOGGER.info( "In workflow" );
                    CrmShiftingWorkflowPojo pojo = new CrmShiftingWorkflowPojo();
                    pojo.setShiftingType( qrcForm.getShiftingWorkflowPojo().getShiftingType() );
                    pojo.setWorkflowId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                    pojo.setCustomerId( custId );
                    pojo.setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                    pojo.setWorkflowStage( CRMOperationStages.NETWORK_PARTNER.getCode() );
                    qrcForm.setShiftingWorkflowPojo( pojo );
                    qrcForm.setCustomerId( custId );
                    qrcForm.setParamValue( IAppConstants.WORKFLOW );
                }
                crmQrcDto.setNetworkConfigurationsPojo( qrcForm.getNetworkConfigurationsPojo() );
                crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                crmQrcDto = getQrcManagerBm().editDevice( crmQrcDto );
                String statusCode = crmQrcDto.getStatusCode();
                if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    if ( StringUtils.isValidObj( crmQrcDto.getCrmPartnerNetworkConfigPojo() ) )
                    {
                        qrcForm.setPartnerNetworkConfigPojo( crmQrcDto.getCrmPartnerNetworkConfigPojo() );
                    }
                    if ( StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
                    {
                        RemarksPojo remarks = BeanUtils.getTemporaryPojo( qrcForm.getRemarksPojo() );
                        /*remarks.setActions( CrmActionEnum.FORWARDED.getActionCode() );*/
                        remarks.setRemarks( "Forwarded to next stage." );
                        qrcForm.setRemarksPojo( remarks );
                        crmQrcDto = getWorkFlowManager().forwardToNextBin( qrcForm );
                        if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            forward = IActionForwardConstant.WORKFLOW_INBOX;
                            qrcForm.setShiftingWorkflowPojo( crmQrcDto.getCrmShiftingWorkflowPojo() );
                            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                            qrcForm.setNewOption82( "" );
                            qrcForm.setShowDivValue( "" );
                            qrcForm.setRemarksPojo( new RemarksPojo() );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( crmQrcDto.getStatusCode(),
                                                                 crmQrcDto.getBillingErrorCode() ) );
                        }
                    }
                    if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getProduct(),
                                             CRMDisplayListConstants.BROADBAND.getCode() )
                            && ( !StringUtils.equals( requestType, IAppConstants.WORKFLOW ) ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_DEVICE_OPTION82_CHANGE ) );
                        qrcForm.setNewOption82( "" );
                        qrcForm.setShowDivValue( "" );
                        qrcForm.setRemarksPojo( new RemarksPojo() );
                    }
                    else if ( !StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
                    {
                        messages.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.SUCCESS_DEVICE_NASPORTID_CHANGE ) );
                        qrcForm.setNewOption82( "" );
                        qrcForm.setShowDivValue( "" );
                        qrcForm.setRemarksPojo( new RemarksPojo() );
                    }
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                    qrcForm.getNetworkConfigurationsPojo().setOption82( oldOption82 );
                    qrcForm.setNewOption82( newOption82 );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        if ( StringUtils.equals( requestType, IAppConstants.WORKFLOW ) )
        {
            saveMessages( inRequest.getSession(), messages );
            saveErrors( inRequest.getSession(), actionErrors );
            messages = null;
            actionErrors = null;
        }
        else
        {
            saveMessages( inRequest, messages );
            saveErrors( inRequest, actionErrors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward editCPE( final ActionMapping inMapping,
                                  final ActionForm inForm,
                                  final HttpServletRequest inRequest,
                                  final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : editCPE" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String forward = IActionForwardConstant.DEVICE_CHANGE_PAGE;
        //check for remarks
        //check for SRT
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        try
        {
            Map<String, Object[]> inResultMap = null;
            inResultMap = QrcFormHelper.validateRemarks( inResultMap, qrcForm.getRemarksPojo() );
            if ( !StringUtils.isValidObj( inResultMap ) )
            {
                inResultMap = QrcFormHelper.validateCustomer( inResultMap, qrcForm );
            }
            if ( StringUtils.isValidObj( inResultMap ) )
            {
                ValidationUtil.prepareErrorMessage( actionErrors, inResultMap );
            }
            if ( actionErrors.isEmpty() )
            {
                LOGGER.info( "the customer id is:" + qrcForm.getCustomerId() );
                CrmQrcDto crmQrcDto = new CrmQrcDto();
                String newPrimaryMacId = qrcForm.getNewPrimaryMacId();
                String oldPrimaryMacId = qrcForm.getNetworkConfigurationsPojo().getCurrentCpeMacId();
                crmQrcDto.setActivityAction( CRMCustomerActivityActions.CPE_OWNERSHIP_CHANGE.getActionDesc() );
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                crmQrcDto.setOrderDetailsPojo( qrcForm.getOrderDetailsPojo() );
                CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
                crmCustomerDetailsPojo.setProduct( qrcForm.getCustDetailsPojo().getProduct() );
                crmQrcDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( qrcForm.getNewPrimaryMacId() );
                crmQrcDto.setNetworkConfigurationsPojo( qrcForm.getNetworkConfigurationsPojo() );
                crmQrcDto = getQrcManagerBm().editDevice( crmQrcDto );
                String statusCode = crmQrcDto.getStatusCode();
                if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setRemarksPojo( new RemarksPojo() );
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_DEVICE_CPE_CHANGE ) );
                    qrcForm.setNewPrimaryMacId( "" );
                    qrcForm.setNewSecondaryMacId( "" );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                    qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( oldPrimaryMacId );
                    qrcForm.setNewPrimaryMacId( newPrimaryMacId );
                    if ( StringUtils.isEmpty( qrcForm.getBoolValue() ) )
                    {
                        qrcForm.setBoolValue( qrcForm.getRemarksPojo().getRemarks() );
                    }
                }
                // LOGGER.info( "new MAC" + qrcForm.getNewSlaveMacId() + "new Option82:" + qrcForm.getNewOption82() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        //}
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    public ActionForward changeDevicePopupPage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : changeDevicePopupPage" );
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
            qrcForm.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            qrcForm.getNetworkConfigurationsPojo().setServiceModel( "fttn" );
            // crmCapForm.setProductTypeList( CRMUtils.getProducts() );
            qrcForm.setOltTypeList( CRMUtils.getOltType() );
            if ( !StringUtils.equals( qrcForm.getCustDetailsPojo().getProduct(),
                                      CRMDisplayListConstants.BROADBAND.getCode() ) )
            {
                LOGGER.info( "networkInventoryDetailsPage method for RF/EOC" );
                crmPartnerNetworkConfigPojos = getCapManagerImpl()
                        .getMasterNameList( qrcForm.getCustDetailsPojo().getNpId(),
                                            qrcForm.getCustDetailsPojo().getProduct() );
                if ( CommonValidator.isValidCollection( crmPartnerNetworkConfigPojos ) )
                {
                    qrcForm.setMasterNameList( crmPartnerNetworkConfigPojos );
                    Set<String> masterNames = new TreeSet<String>();
                    for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo : crmPartnerNetworkConfigPojos )
                    {
                        masterNames.add( crmPartnerNetworkConfigPojo.getMasterName() );
                    }
                    qrcForm.setMasterNames( new ArrayList<String>( masterNames ) );
                    qrcForm.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
                    qrcForm.getCrmPartnerNetworkConfig()
                            .setPartnerDetailsId( crmPartnerNetworkConfigPojos.get( 0 ).getPartnerDetailsId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in network configuration details", ex );
        }
        return inMapping.findForward( IActionForwardConstant.DEVICE_CHANGE_POPUP );
    }

    public ActionForward sendLegalMail( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : sendLegalMail" );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String forward = IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE;
        try
        {
            if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                     CRMDisplayListConstants.POST_PAID.getCode() ) )
            {
                QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
                if ( actionErrors.isEmpty() )
                {
                    CrmQrcDto crmQrcDto = new CrmQrcDto();
                    crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                    crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                    crmQrcDto.setCrmInvoiceDetailsPojo( qrcForm.getInvoicePojo() );
                    crmQrcDto.setCustAdditionalDetails( qrcForm.getCustAdditionalDetails() );
                    crmQrcDto = getQrcManagerBm().sendLegalMail( crmQrcDto );
                    LOGGER.info( "Status Code:: " + crmQrcDto.getStatusCode() );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( StringUtils.isEmpty( crmQrcDto.getSrTicketNo() ) )
                            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        else
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET,
                                                             crmQrcDto.getSrTicketNo() ) );
                    }
                    else
                    {
                        actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                    }
                }
            }
            else
            {
                actionErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( IPropertiesConstant.NOT_APPLICABLE_FOR_PREPAID_CUSTOMER ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    public ActionForward tariffMigrationPage( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        qrcForm.setAddonAllowed( false );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
        if ( actionErrors.isEmpty() )
        {
            if ( StringUtils.isValidObj( qrcForm.getPlanDetailsPojo() ) )
            {
                long basePlanQuota = 0;
                long addOnPlanQuota = 0;
                if ( StringUtils.isNotEmpty( qrcForm.getPlanDetailsPojo().getBasePlanCode() ) )
                {
                    getQrcManagerBm().getBasePlanInfo( qrcForm );
                    if ( StringUtils.isValidObj( qrcForm.getCrmPlanMasterPojo() )
                            && StringUtils.isValidObj( qrcForm.getCrmPlanMasterPojo().getPrimaryQuota() ) )
                    {
                        basePlanQuota = qrcForm.getCrmPlanMasterPojo().getPrimaryQuota() / 1024 / 1024 / 1024;
                        qrcForm.setBasePlanQuota( basePlanQuota );
                    }
                }
                if ( StringUtils.isNotEmpty( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                {
                    MasterDataDto addonPlanMasterDataDto = getQrcManagerBm().getAddonPlanInfo( qrcForm );
                    if ( CommonValidator.isValidCollection( addonPlanMasterDataDto.getCrmPlanMasterPojos() ) )
                    {
                        addOnPlanQuota = addonPlanMasterDataDto.getCrmPlanMasterPojos().get( 0 ).getPrimaryQuota()
                                / 1024 / 1024 / 1024;
                        qrcForm.setAddonPlanQuota( addOnPlanQuota );
                    }
                }
                qrcForm.setTotalQuota( basePlanQuota + addOnPlanQuota );
                LOGGER.info( "Total Quota:: " + qrcForm.getTotalQuota() );
                // outstanding amount and due date
                if ( StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) )
                {
                    LOGGER.info( "bill outstanding:: amount: " + qrcForm.getCustAdditionalDetails().getBalance()
                            + " dueDate: " + qrcForm.getCustAdditionalDetails().getExpiryDate() );
                    qrcForm.setCurrentUsage( qrcForm.getCustAdditionalDetails().getUsedVolumeQuota() / 1024 / 1024
                            / 1024 );
                    LOGGER.info( "billing used quota :: " + qrcForm.getCustAdditionalDetails().getUsedVolumeQuota() );
                    LOGGER.info( "current usage :: " + qrcForm.getCurrentUsage() + " GB" );
                    qrcForm.setPercentage( Math.round( qrcForm.getCurrentUsage()
                            / ( qrcForm.getTotalQuota() > 0 ? qrcForm.getTotalQuota() : 1 ) + 0.5 ) );
                    qrcForm.setUsedPercentage( (double) qrcForm.getCurrentUsage()
                            / ( qrcForm.getTotalQuota() > 0 ? qrcForm.getTotalQuota() : 1 ) * 100 );
                    LOGGER.info( "used :: " + qrcForm.getUsedPercentage() + " % of " + qrcForm.getTotalQuota()
                            + " quota - factor :: " + qrcForm.getPercentage() );
                }
            }
            QrcFormHelper.resetFormProperty( qrcForm );
        }
        return inMapping.findForward( IActionForwardConstant.TARIFF_MIGRATION_PAGE );
    }

    public ActionForward getCustomerActivationPlan( final ActionMapping inMapping,
                                                    final ActionForm inForm,
                                                    final HttpServletRequest inRequest,
                                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction, getCustomerActivationPlan" );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        qrcForm.setRemarksPojo( new RemarksPojo() );
        String forward = IActionForwardConstant.TARIFF_MIGRATION_PAGE;
        CrmQrcDto crmQrcDto = null;
        QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
        if ( actionErrors.isEmpty() )
        {
            if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) )
            {
                LOGGER.info( qrcForm.getCustDetailsPojo().getProduct() );
                LOGGER.info( qrcForm.getCustDetailsPojo().getServiceType() );
                LOGGER.info( "plan category :: " + qrcForm.getPlanCategory() );
                CRMDisplayListConstants constant = CRMDisplayListConstants
                        .getConstantByFilter( "PlanService", qrcForm.getCustDetailsPojo().getProduct(),
                                              qrcForm.getCustDetailsPojo().getServiceType() );
                LOGGER.info( "Constant" + constant );
                if ( StringUtils.equals( qrcForm.getPlanCategory(),
                                         CRMCustomerActivityActions.BASE_PLAN_MIGRATION.getActionDesc() ) )
                {
                    qrcForm.setAddonAllowed( false );
                    qrcForm.setPlanMasterList( getQrcManagerBm().getMigratedActivationPlan( qrcForm ) );
                    if ( CommonValidator.isValidCollection( qrcForm.getPlanMasterList() ) )
                    {
                        Iterator<CrmPlanMasterPojo> basePlanIterator = qrcForm.getPlanMasterList().iterator();
                        while ( basePlanIterator.hasNext() )
                        {
                            String planCode = basePlanIterator.next().getPlanCode();
                            LOGGER.info( planCode + " == " + qrcForm.getPlanDetailsPojo().getBasePlanCode() );
                            if ( planCode.equals( qrcForm.getPlanDetailsPojo().getBasePlanCode() ) )
                            {
                                LOGGER.info( planCode + " removed" );
                                basePlanIterator.remove();
                            }
                        }
                    }
                    qrcForm.setSelectedPlanCode( qrcForm.getPlanDetailsPojo().getBasePlanCode() );
                    if ( StringUtils.equals( qrcForm.getPlanType(), "APP" )
                            || StringUtils.equals( qrcForm.getPlanType(), "MBO" ) )
                    {
                        getQrcManagerBm().trackPaymentHistory( qrcForm, false );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Plan Renewal" ) )
                {
                    qrcForm.setAddonAllowed( false );
                    qrcForm.setPlanMasterList( getQrcManagerBm().getMigratedActivationPlan( qrcForm ) );
                    qrcForm.setAddonPlanMasterList( getQrcManagerBm().getAddonPlanDetails( qrcForm ) );
                    qrcForm.setSelectedPlanCode( qrcForm.getPlanDetailsPojo().getBasePlanCode() );
                    if ( StringUtils.equals( qrcForm.getPlanType(), "APP" )
                            || StringUtils.equals( qrcForm.getPlanType(), "MBO" ) )
                    {
                        getQrcManagerBm().trackPaymentHistory( qrcForm, false );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Plan Reactivation" ) )
                {
                    qrcForm.setAddonAllowed( false );
                    qrcForm.setPlanMasterList( getQrcManagerBm().getReActivationPlan( qrcForm ) );
                    qrcForm.setSelectedPlanCode( qrcForm.getPlanDetailsPojo().getBasePlanCode() );
                    if ( StringUtils.equals( qrcForm.getPlanType(), "APP" )
                            || StringUtils.equals( qrcForm.getPlanType(), "MBO" ) )
                    {
                        getQrcManagerBm().trackPaymentHistory( qrcForm, false );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(),
                                              CRMCustomerActivityActions.BOOSTER_PLAN.getActionDesc() ) )
                {
                    qrcForm.setAddonAllowed( false );
                    List<CrmPlanMasterPojo> planMasterList = null;
                    if ( StringUtils.equals( qrcForm.getPlanUsageType(), "BOOSTER_USAGE" ) )
                    {
                        planMasterList = CRMCacheManager.getTopUpBoosterPlanDetails( constant.getCode(),
                                                                                     qrcForm.getPlanType() );
                    }
                    else if ( StringUtils.equals( qrcForm.getPlanUsageType(), "BOOSTER_SPEED" ) )
                    {
                        LOGGER.info( "Plan type:: " + qrcForm.getPlanType() );
                        planMasterList = CRMCacheManager.getSpeedBoosterPlanDetails( constant.getCode(),
                                                                                     qrcForm.getPlanType() );
                    }
                    if ( CommonValidator.isValidCollection( planMasterList ) )
                    {
                        LOGGER.info( "Plan Master list size:: " + planMasterList.size() );
                        qrcForm.setPlanMasterList( planMasterList );
                    }
                    else
                    {
                        qrcForm.setPlanMasterList( new ArrayList<CrmPlanMasterPojo>() );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "VAS_MANAGEMENT" ) )
                {
                    qrcForm.setAddonAllowed( false );
                    // fetch all activated VAS for current customer
                    Set<String> activatedVASSet = null;
                    crmQrcDto = new CrmQrcDto();
                    CrmCustAssetDetailsPojo custAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                    custAssetDetailsPojo.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
                    custAssetDetailsPojo.setCategoryName( fetchCategoryCriteria( qrcForm ) );
                    custAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmQrcDto.setCrmCustAssetDetailsPojo( custAssetDetailsPojo );
                    crmQrcDto = getQrcManagerBm().fetchActivatedVAS( crmQrcDto );
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                            && CommonValidator.isValidCollection( crmQrcDto.getCrmCustAssetDetailsPojos() ) )
                    {
                        qrcForm.setCrmCustAssetDetailsPojos( crmQrcDto.getCrmCustAssetDetailsPojos() );
                        activatedVASSet = new HashSet<String>();
                        for ( CrmCustAssetDetailsPojo assetDetailsPojo : crmQrcDto.getCrmCustAssetDetailsPojos() )
                        {
                            activatedVASSet.add( assetDetailsPojo.getCategoryValue() );
                        }
                    }
                    // fetch all VAS which can be applied
                    List<CrmPlanMasterPojo> planMasterList = null;
                    List<CrmPlanMasterPojo> toActivateVASList = new ArrayList<CrmPlanMasterPojo>();
                    List<CrmPlanMasterPojo> toDeactivateVASList = new ArrayList<CrmPlanMasterPojo>();
                    planMasterList = CRMCacheManager.getVasPlanDetails( constant.getCode(), qrcForm.getPlanType() );
                    if ( CommonValidator.isValidCollection( planMasterList ) )
                    {
                        for ( CrmPlanMasterPojo crmPlanMasterPojo : planMasterList )
                        {
                            if ( CommonValidator.isValidCollection( activatedVASSet )
                                    && activatedVASSet.contains( crmPlanMasterPojo.getPlanCode() ) )
                            {
                                toDeactivateVASList.add( crmPlanMasterPojo );
                            }
                            else
                            {
                                toActivateVASList.add( crmPlanMasterPojo );
                            }
                        }
                    }
                    LOGGER.info( planMasterList.size() + "       " + toActivateVASList.size() + "       "
                            + toDeactivateVASList.size() );
                    if ( StringUtils.equals( qrcForm.getPlanUsageType(),
                                             CRMCustomerActivityActions.SMART_BROADBAND_ACTIVATION.getActionDesc() )
                            && !toActivateVASList.isEmpty() )
                    {
                        qrcForm.setPlanMasterList( planMasterList );
                        qrcForm.setVasToActivatePojos( toActivateVASList );
                    }
                    else if ( StringUtils
                            .equals( qrcForm.getPlanUsageType(),
                                     CRMCustomerActivityActions.SMART_BROADBAND_DEACTIVATION.getActionDesc() )
                            && !toDeactivateVASList.isEmpty() )
                    {
                        qrcForm.setPlanMasterList( planMasterList );
                        qrcForm.setVasToDeactivatePojos( toDeactivateVASList );
                    }
                    else
                    {
                        qrcForm.setPlanMasterList( null );
                        qrcForm.setVasToActivatePojos( null );
                        qrcForm.setVasToDeactivatePojos( null );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Addon Plans" ) )
                {
                    qrcForm.setPlanMasterList( null );
                    if ( StringUtils.equals( qrcForm.getPlanUsageType(), IAppConstants.CHANGE_ADDON ) )
                    {
                        qrcForm.setAddonPlanMasterList( getQrcManagerBm().getAddonPlanDetails( qrcForm ) );
                        if ( StringUtils.isNotEmpty( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                        {
                            if ( CommonValidator.isValidCollection( qrcForm.getAddonPlanMasterList() ) )
                            {
                                Iterator<CrmPlanMasterPojo> addOnListIterator = qrcForm.getAddonPlanMasterList()
                                        .iterator();
                                while ( addOnListIterator.hasNext() )
                                {
                                    if ( addOnListIterator.next().getPlanCode()
                                            .equals( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                                    {
                                        addOnListIterator.remove();
                                    }
                                }
                            }
                        }
                        qrcForm.setAddonPlanCode( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
                    }
                }
                if ( !StringUtils.isValidObj( qrcForm.getCrmPlanMasterPojo() ) )
                {
                    qrcForm.setCrmPlanMasterPojo( new CrmPlanMasterPojo() );
                }
                crmQrcDto = new CrmQrcDto();
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                LOGGER.info( "plan category :: " + qrcForm.getPlanCategory() + " :: usageType :: "
                        + qrcForm.getPlanUsageType() );
                if ( StringUtils.equals( qrcForm.getPlanCategory(), "VAS_MANAGEMENT" )
                        || StringUtils.equals( qrcForm.getPlanCategory(), "Addon Plans" ) )
                {
                    crmQrcDto.setActivityAction( qrcForm.getPlanUsageType() );
                }
                else
                {
                    crmQrcDto.setActivityAction( qrcForm.getPlanCategory() );
                }
                crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
                if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                {
                    getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                    qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                    LOGGER.info( "Ticket Pojo List size:: " + crmQrcDto.getCrmSrTicketsPojos().size() );
                }
                else
                {
                    qrcForm.setSrTicketsPojos( new ArrayList<CrmSrTicketsPojo>() );
                }
            }
        }
        return inMapping.findForward( forward );
    }

    private String fetchCategoryCriteria( QrcForm inQrcForm )
    {
        if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_PAID" ) )
        {
            return CRMParameter.VAS_ACTIVATION_PAID.getParameter();
        }
        else if ( StringUtils.equals( inQrcForm.getPlanType(), "VAS_FOC" ) )
        {
            return CRMParameter.VAS_ACTIVATION_FOC.getParameter();
        }
        return null;
    }

    public ActionForward cancelPlanMigration( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        LOGGER.info( "in cancelPlanMigration" );
        String forward = IActionForwardConstant.TARIFF_MIGRATION_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        QrcForm qrcForm = (QrcForm) inForm;
        if ( CommonValidator.isValidCollection( qrcForm.getSrTicketsPojos() ) )
        {
            QrcFormHelper.remarksValidation( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                String presentStage = resolveStageByBinID( qrcForm.getSrTicketsPojos().get( 0 ).getFunctionalbinId() );
                CrmQrcDto qrcDto = getQrcManagerBm()
                        .cancelPlanMigration( qrcForm, userDto.getCrmUserDetailsPojo().getUserId(), presentStage );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
                {
                    qrcDto = new CrmQrcDto();
                    qrcDto.setCustomerId( qrcForm.getCustomerId() );
                    if ( StringUtils.equals( qrcForm.getPlanCategory(), "VAS_MANAGEMENT" ) )
                    {
                        qrcDto.setActivityAction( qrcForm.getPlanUsageType() );
                    }
                    else
                    {
                        qrcDto.setActivityAction( qrcForm.getPlanCategory() );
                    }
                    qrcDto = getQrcManagerBm().getCustomerTickets( qrcDto );
                    if ( CommonValidator.isValidCollection( qrcDto.getCrmSrTicketsPojos() ) )
                    {
                        getQrcManagerBm().setCategoriesNameById( qrcDto.getCrmSrTicketsPojos() );
                        qrcForm.setSrTicketsPojos( qrcDto.getCrmSrTicketsPojos() );
                        LOGGER.info( "Ticket Pojo List size:: " + qrcDto.getCrmSrTicketsPojos().size() );
                    }
                    else
                    {
                        qrcForm.setSrTicketsPojos( new ArrayList<CrmSrTicketsPojo>() );
                    }
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_CANCEL_PLAN_MIGRATION ) );
                    qrcForm.setRemarksPojo( new RemarksPojo() );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
                }
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR,
                        new ActionMessage( IPropertiesConstant.ERROR_EMPTY_TICKET_LIST_CANCEL ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward saveCustomerActivationPlan( final ActionMapping inMapping,
                                                     final ActionForm inForm,
                                                     final HttpServletRequest inRequest,
                                                     final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String forward = IActionForwardConstant.TARIFF_MIGRATION_PAGE;
        CrmQrcDto crmQrcDto = null;
        QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
        if ( actionErrors.isEmpty() )
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) )
            {
                MDC.put( "KEY",
                         "CUSTID:" + qrcForm.getCustomerId() + ", BASEPLAN:"
                                 + qrcForm.getPlanDetailsPojo().getBasePlanCode() + ", ADDON:"
                                 + qrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
                LOGGER.info( "Going to save " + qrcForm.getPlanCategory() );
                if ( StringUtils.equals( qrcForm.getPlanCategory(),
                                         CRMCustomerActivityActions.BASE_PLAN_MIGRATION.getActionDesc() ) )
                {
                    boolean validate = QrcFormHelper.planMigrationValidation( actionErrors, qrcForm );
                    if ( validate && actionErrors.isEmpty() )
                    {
                        crmQrcDto = getQrcManagerBm()
                                .activateCustomerBasePlan( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(),
                                              CRMCustomerActivityActions.BOOSTER_PLAN.getActionDesc() ) )
                {
                    XMLGregorianCalendar boosterExpiryDate = null;
                    if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                             CRMDisplayListConstants.PRE_PAID.getCode() ) )
                    {
                        if ( StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) )
                        {
                            boosterExpiryDate = qrcForm.getCustAdditionalDetails().getExpiryDate();
                        }
                    }
                    else
                    {
                        boosterExpiryDate = DateUtils.changeDateFormat( IDateConstants.SDF_DD_MMM_YYYY
                                .format( DateUtils.getNextBillingDate( qrcForm.getCustDetailsPojo().getBillDate() ) ) );
                    }
                    LOGGER.info( "Booster Expiry Date : " + boosterExpiryDate );
                    boolean proceed = QrcFormHelper.planBoosterValidations( actionErrors, qrcForm,
                                                                            userDto.getCrmUserDetailsPojo().getUserId(),
                                                                            boosterExpiryDate );
                    if ( proceed && actionErrors.isEmpty() )
                    {
                        crmQrcDto = getQrcManagerBm().mountBoosterPlan( qrcForm,
                                                                        userDto.getCrmUserDetailsPojo().getUserId() );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "VAS_MANAGEMENT" ) )
                {
                    boolean proceed = QrcFormHelper
                            .customerVASValidations( actionErrors, qrcForm,
                                                     userDto.getCrmUserDetailsPojo().getUserId() );
                    if ( proceed && actionErrors.isEmpty() )
                    {
                        crmQrcDto = getQrcManagerBm()
                                .activateDeactivateVAS( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Plan Reactivation" ) )
                {
                    LOGGER.info( "Customer Plan Reactivation" );
                    boolean validate = QrcFormHelper.planReactivationOrRenewValidation( actionErrors, qrcForm );
                    if ( validate && actionErrors.isEmpty() )
                    {
                        crmQrcDto = getQrcManagerBm()
                                .customerPlanReactivation( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Plan Renewal" ) )
                {
                    boolean validate = QrcFormHelper.planReactivationOrRenewValidation( actionErrors, qrcForm );
                    if ( validate && actionErrors.isEmpty() )
                    {
                        crmQrcDto = getQrcManagerBm()
                                .customerPlanRenewal( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                    }
                }
                else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Addon Plans" ) )
                {
                    if ( StringUtils.equals( qrcForm.getPlanUsageType(), IAppConstants.REMOVE_ADDON ) )
                    {
                        crmQrcDto = getQrcManagerBm()
                                .removeCustomerAddonPlan( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                        LOGGER.info( "status code ::::: " + crmQrcDto.getStatusCode() + " :: "
                                + crmQrcDto.getStatusDesc() );
                    }
                    else
                    {
                        crmQrcDto = getQrcManagerBm()
                                .activateCustomerAddonPlan( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                        LOGGER.info( "status code ::::: " + crmQrcDto.getStatusCode() + " :: "
                                + crmQrcDto.getStatusDesc() );
                    }
                }
                if ( StringUtils.isValidObj( crmQrcDto ) )
                {
                    if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( StringUtils.equals( qrcForm.getPlanCategory(),
                                                 CRMCustomerActivityActions.BASE_PLAN_MIGRATION.getActionDesc() )
                                || StringUtils.equals( qrcForm.getPlanCategory(), "Plan Renewal" ) )
                        {
                            if ( StringUtils.equals( qrcForm.getCrmPlanMasterPojo().getAddonAllowedYn(),
                                                     IAppConstants.Y ) )
                            {
                                qrcForm.setAddonAllowed( false );
                            }
                            if ( StringUtils.equals( qrcForm.getActivationType(), IAppConstants.IMMEDIATE_ACTIVATION ) )
                            {
                                qrcForm.setPlanDetailsPojo( crmQrcDto.getCrmPlanDetailsPojo() );
                                getQrcManagerBm().getBasePlanInfo( qrcForm );
                                if ( StringUtils.isValidObj( qrcForm.getCrmPlanMasterPojo() )
                                        && StringUtils.isValidObj( qrcForm.getCrmPlanMasterPojo().getPrimaryQuota() ) )
                                {
                                    long basePlanQuota = qrcForm.getCrmPlanMasterPojo().getPrimaryQuota() / 1024 / 1024
                                            / 1024;
                                    qrcForm.setBasePlanQuota( basePlanQuota );
                                }
                                if ( StringUtils.isBlank( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                                {
                                    qrcForm.setAddonPlanQuota( 0l );
                                }
                            }
                            crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
                            if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                            {
                                getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                                qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                                LOGGER.info( "Ticket Pojo List size:: " + crmQrcDto.getCrmSrTicketsPojos().size() );
                            }
                            QrcFormHelper.resetFormProperty( qrcForm );
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_UPDATE_PLAN,
                                                             crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                            qrcForm.setSrTicketPojo( crmQrcDto.getCrmSrTicketsPojo() );
                        }
                        else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Addon Plans" ) )
                        {
                            if ( qrcForm.isAddonAllowed() )
                            {
                                qrcForm.setAddonAllowed( false );
                            }
                            if ( StringUtils.equals( qrcForm.getPlanUsageType(), IAppConstants.REMOVE_ADDON ) )
                            {
                                if ( StringUtils.equals( qrcForm.getActivationType(),
                                                         IAppConstants.IMMEDIATE_ACTIVATION ) )
                                {
                                    qrcForm.setAddonPlanQuota( 0l );
                                }
                                QrcFormHelper.resetFormProperty( qrcForm );
                                messages.add( IAppConstants.APP_MESSAGE,
                                              new ActionMessage( IPropertiesConstant.SUCCESS_ADDON_REMOVE,
                                                                 crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                            }
                            else
                            {
                                if ( StringUtils.equals( qrcForm.getActivationType(),
                                                         IAppConstants.IMMEDIATE_ACTIVATION ) )
                                {
                                    qrcForm.setPlanDetailsPojo( crmQrcDto.getCrmPlanDetailsPojo() );
                                    MasterDataDto addonPlanMasterDataDto = getQrcManagerBm()
                                            .getAddonPlanInfo( qrcForm );
                                    if ( CommonValidator
                                            .isValidCollection( addonPlanMasterDataDto.getCrmPlanMasterPojos() ) )
                                    {
                                        long addOnPlanQuota = addonPlanMasterDataDto.getCrmPlanMasterPojos().get( 0 )
                                                .getPrimaryQuota() / 1024 / 1024 / 1024;
                                        qrcForm.setAddonPlanQuota( addOnPlanQuota );
                                    }
                                }
                                crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
                                if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                                {
                                    getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                                    qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                                    LOGGER.info( "Ticket Pojo List size:: " + crmQrcDto.getCrmSrTicketsPojos().size() );
                                }
                                QrcFormHelper.resetFormProperty( qrcForm );
                                messages.add( IAppConstants.APP_MESSAGE,
                                              new ActionMessage( IPropertiesConstant.SUCCESS_ADDON_UPDATE,
                                                                 crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                            }
                        }
                        else if ( StringUtils.equals( qrcForm.getPlanCategory(),
                                                      CRMCustomerActivityActions.BOOSTER_PLAN.getActionDesc() ) )
                        {
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_BOOSTER_MOUNTED ) );
                            if ( CommonValidator.isValidCollection( crmQrcDto.getCrmCustAssetDetailsPojos() ) )
                            {
                                for ( CrmCustAssetDetailsPojo custAssetDetailsPojo : crmQrcDto
                                        .getCrmCustAssetDetailsPojos() )
                                {
                                    messages.add( IAppConstants.APP_MESSAGE,
                                                  new ActionMessage( IPropertiesConstant.SUCCESS_BOOSTER_MESSAGE,
                                                                     custAssetDetailsPojo.getCategoryCount() ) );
                                }
                            }
                            QrcFormHelper.resetFormProperty( qrcForm );
                        }
                        else if ( StringUtils.equals( qrcForm.getPlanCategory(), "Plan Reactivation" ) )
                        {
                            String appendSuccessMsg = "";
                            if ( StringUtils.isNotBlank( qrcForm.getSrTicketNo() ) )
                            {
                                appendSuccessMsg = "Please, resolve Ticket-ID : " + qrcForm.getSrTicketNo()
                                        + " manually.";
                            }
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_PLAN_REACTIVATION,
                                                             appendSuccessMsg ) );
                            forward = IActionForwardConstant.QRC_SEARCH_CUSTOMER_METHOD;
                        }
                        else if ( StringUtils.equals( qrcForm.getPlanCategory(), "VAS_MANAGEMENT" ) )
                        {
                            QrcFormHelper.resetFormProperty( qrcForm );
                            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        }
                        qrcForm.setRemarksPojo( new RemarksPojo() );
                    }
                    else
                    {
                        if ( !StringUtils.equals( qrcForm.getPlanCategory(), "Addon Plans" ) )
                        {
                            qrcForm.setAddonAllowed( false );
                        }
                        if ( StringUtils.isNotBlank( crmQrcDto.getBillingErrorCode() ) )
                        {
                            actionErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( crmQrcDto.getStatusCode(),
                                                                 crmQrcDto.getBillingErrorCode() ) );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        }
                    }
                }
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    /*  private Object getQoutaByCode( List<CrmPlanMasterPojo> inPlanMasterList, String inCategoryName )
      {
          LOGGER.info( "Inside QrcAction, -getQoutaByCode" );
          if ( CommonValidator.isValidCollection( inPlanMasterList ) && StringUtils.isNotBlank( inCategoryName ) )
          {
              for ( CrmPlanMasterPojo crmPlanMasterPojo : inPlanMasterList )
              {
                  if ( StringUtils.equals( crmPlanMasterPojo.getPlanCode(), inCategoryName ) )
                  {
                      return crmPlanMasterPojo.getPrimaryQuota();
                  }
              }
          }
          return null;
      }*/
    public ActionForward getAccessories( ActionMapping inMapping,
                                         ActionForm inForm,
                                         HttpServletRequest inRequest,
                                         HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String forward = IActionForwardConstant.QRC_ADD_ACC_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        CrmCustAssetDetailsPojo crmCustAssetDetailsPojo = null;
        String requestType;
        try
        {
            crmQrcDto.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
            HttpSession httpSession = inRequest.getSession( false );
            requestType = inRequest.getParameter( IAppConstants.QRC_REQUEST_PARAM );
            RolesPojo rolesPojo = (RolesPojo) httpSession.getAttribute( IAppConstants.CRM_ROLES );
            if ( StringUtils.isValidObj( requestType ) && StringUtils.equals( requestType, "menuPage" ) )
            {
                if ( StringUtils.equals( rolesPojo.getAvailable( "view_qrc_stip" ), "true" ) )
                {
                    requestType = "staticIP";
                }
                else if ( StringUtils.equals( rolesPojo.getAvailable( "view_qrc_stipchg" ), "true" ) )
                {
                    requestType = "staticIPCharges";
                }
                else if ( StringUtils.equals( rolesPojo.getAvailable( "view_qrc_wrchg" ), "true" ) )
                {
                    requestType = "wiring";
                }
            }
            if ( !StringUtils.isValidObj( requestType ) || StringUtils.isBlank( requestType ) )
            {
                requestType = (String) httpSession.getAttribute( IAppConstants.QRC_REQUEST_PARAM );
                httpSession.removeAttribute( IAppConstants.QRC_REQUEST_PARAM );
            }
            LOGGER.info( "request type:" + requestType );
            if ( StringUtils.equals( requestType, CRMParameter.STATIC_IP_CHARGES.getParameter() ) )
            {
                if ( !StringUtils.equals( rolesPojo.getAvailable( "view_qrc_stipchg" ), "true" ) )
                {
                    return inMapping.findForward( IActionForwardConstant.HOME_PAGE );
                }
                crmCustAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                crmCustAssetDetailsPojo.setCategoryAmount( 0.0 );
                qrcForm.setCrmCustAssetDetailsPojo( crmCustAssetDetailsPojo );
            }
            else if ( StringUtils.equals( requestType, CRMParameter.WIRING.getParameter() ) )
            {
                if ( !StringUtils.equals( rolesPojo.getAvailable( "view_qrc_wrchg" ), "true" ) )
                {
                    return inMapping.findForward( IActionForwardConstant.HOME_PAGE );
                }
                crmCustAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                crmCustAssetDetailsPojo.setCategoryAmount( 0.0 );
                qrcForm.setCrmCustAssetDetailsPojo( crmCustAssetDetailsPojo );
            }
            else if ( StringUtils.equals( requestType, CRMParameter.STATIC_IP.getParameter() ) )
            {
                if ( !StringUtils.equals( rolesPojo.getAvailable( "view_qrc_stip" ), "true" ) )
                {
                    return inMapping.findForward( IActionForwardConstant.HOME_PAGE );
                }
                crmQrcDto = getQrcManagerBm().searchAccessories( crmQrcDto, requestType );
                if ( StringUtils.isValidObj( crmQrcDto ) && StringUtils.isNotBlank( crmQrcDto.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCrmCustAssetDetailsPojos() ) )
                    {
                        qrcForm.setCrmCustAssetDetailsPojos( crmQrcDto.getCrmCustAssetDetailsPojos() );
                    }
                    else
                    {
                        crmCustAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                        qrcForm.setCrmCustAssetDetailsPojo( crmCustAssetDetailsPojo );
                    }
                }
            }
            qrcForm.setRemarksPojo( new RemarksPojo() );
            qrcForm.setShowDivValue( requestType );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    public ActionForward addRemoveAccessories( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages actionErrors = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        String param = inRequest.getParameter( "inParamValue" );
        String forward = IActionForwardConstant.QRC_GET_ACCESSORIES;
        try
        {
            MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
            crmQrcDto.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
            if ( StringUtils.equals( param, IAppConstants.WORKFLOW ) )
            {
                CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
                shiftingWorkflowPojo.setWorkflowId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                shiftingWorkflowPojo.setCustomerId( qrcForm.getCustomerId() );
                shiftingWorkflowPojo.setShiftingType( qrcForm.getShiftingWorkflowPojo().getShiftingType() );
                shiftingWorkflowPojo.setWorkflowStage( CRMOperationStages.SERVICE_PARTNER.getCode() );
                crmQrcDto.setCrmShiftingWorkflowPojo( shiftingWorkflowPojo );
                qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
                qrcForm.setParamValue( IAppConstants.WORKFLOW );
                forward = IActionForwardConstant.INSTALLATION_PARTNER_STAGE;
            }
            crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
            crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
            String typeOfReq = inRequest.getParameter( IAppConstants.QRC_REQUEST_PARAM );
            LOGGER.info( "In qrcAction for saving the wiring points and type of request is :: " + typeOfReq );
            qrcForm.setShowDivValue( typeOfReq );
            HttpSession inSession = inRequest.getSession( false );
            PojoComparator<CrmCustAssetDetailsPojo> pojoComparator = new PojoComparator<CrmCustAssetDetailsPojo>();
            Set<String> compareString = new HashSet<String>();
            QrcFormHelper.checkValidStaticIPWiring( actionErrors, qrcForm, typeOfReq );
            if ( actionErrors.isEmpty() && StringUtils.equals( typeOfReq, CRMParameter.STATIC_IP.getParameter() ) )
            {
                if ( CommonValidator.isValidCollection( qrcForm.getCrmCustAssetDetailsPojos() ) )
                {
                    int count = 0;
                    crmQrcDto = getQrcManagerBm().searchAccessories( crmQrcDto, CRMParameter.STATIC_IP.getParameter() );
                    for ( CrmCustAssetDetailsPojo assetDetailsPojo : qrcForm.getCrmCustAssetDetailsPojos() )
                    {
                        LOGGER.info( "Status:: " + assetDetailsPojo.getStatus() );
                        if ( !compareString.add( assetDetailsPojo.getCategoryValue() ) )
                        {
                            actionErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( CRMServiceCode.CRM990.getStatusCode(),
                                                                 assetDetailsPojo.getCategoryValue(),
                                                                 "Static IP" ) );
                            break;
                        }
                        count = validateCustAssetDetails( pojoComparator, crmQrcDto.getCrmCustAssetDetailsPojos(),
                                                          count, assetDetailsPojo );
                    }
                    if ( count == qrcForm.getCrmCustAssetDetailsPojos().size() )
                    {
                        actionErrors.add( IAppConstants.APP_ERROR,
                                          new ActionMessage( CRMServiceCode.CRM995.getStatusCode() ) );
                    }
                    if ( actionErrors.isEmpty() )
                    {
                        crmQrcDto.getCrmCustAssetDetailsPojos().clear();
                        crmQrcDto.getCrmCustAssetDetailsPojos().addAll( qrcForm.getCrmCustAssetDetailsPojos() );
                        crmQrcDto = getQrcManagerBm().saveAccessories( crmQrcDto, qrcForm, typeOfReq );
                        if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            qrcForm.setRemarksPojo( new RemarksPojo() );
                            if ( StringUtils.isEmpty( crmQrcDto.getSrTicketNo() ) )
                            {
                                messages.add( IAppConstants.APP_MESSAGE,
                                              new ActionMessage( IPropertiesConstant.SUCCESS_STATICIP_ADDED_SUCCESSFULLY ) );
                            }
                            else
                            {
                                messages.add( IAppConstants.APP_MESSAGE,
                                              new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET,
                                                                 crmQrcDto.getSrTicketNo() ) );
                            }
                            forward = IActionForwardConstant.QRC_GET_ACCESSORIES;
                        }
                        else if ( StringUtils.equals( crmQrcDto.getStatusCode(),
                                                      CRMServiceCode.CRM308.getStatusCode() ) )
                        {
                            actionErrors.add( IAppConstants.APP_MESSAGE,
                                              new ActionMessage( IPropertiesConstant.ERROR_STATICIP_ALREADY_EXISTS,
                                                                 crmQrcDto.getCrmCustAssetDetailsPojo()
                                                                         .getCategoryValue() ) );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( crmQrcDto.getStatusCode(),
                                                                 crmQrcDto.getBillingErrorCode() ) );
                            if ( StringUtils.isEmpty( qrcForm.getBoolValue() ) )
                            {
                                qrcForm.setBoolValue( qrcForm.getRemarksPojo().getRemarks() );
                            }
                        }
                    }
                }
                else
                {
                    LOGGER.info( "error unchanged static ip pojos @client" );
                    actionErrors.add( IAppConstants.APP_MESSAGE,
                                      new ActionMessage( IPropertiesConstant.ERROR_EMPTY_STATICIP_POJOS ) );
                }
                inSession.setAttribute( IAppConstants.QRC_REQUEST_PARAM, CRMParameter.STATIC_IP.getParameter() );
            }
            else if ( actionErrors.isEmpty() )
            {
                if ( StringUtils.equals( param, IAppConstants.WORKFLOW ) )
                {
                    crmQrcDto.getRemarksPojo().setMappingId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                }
                crmQrcDto = getQrcManagerBm().saveAccessories( crmQrcDto, qrcForm, typeOfReq );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setCrmCustAssetDetailsPojo( crmQrcDto.getCrmCustAssetDetailsPojo() );
                    qrcForm.setRemarksPojo( new RemarksPojo() );
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_WIRING_CHARGES_APPLIED ) );
                    if ( StringUtils.equals( param, IAppConstants.WORKFLOW ) )
                    {
                        forward = IActionForwardConstant.INSTALLATION_PARTNER_STAGE;
                        qrcForm.setParamValue( IAppConstants.WORKFLOW );
                    }
                    else
                    {
                        forward = IActionForwardConstant.QRC_GET_ACCESSORIES;
                    }
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                    if ( StringUtils.isEmpty( qrcForm.getBoolValue() ) )
                    {
                        qrcForm.setBoolValue( qrcForm.getRemarksPojo().getRemarks() );
                    }
                }
                inSession.setAttribute( IAppConstants.QRC_REQUEST_PARAM, typeOfReq );
            }
            else if ( !actionErrors.isEmpty() )
            {
                qrcForm.setParamValue( "serviceRequest" );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest.getSession(), actionErrors );
        saveMessages( inRequest.getSession(), messages );
        actionErrors = null;
        messages = null;
        return inMapping.findForward( forward );
    }

    private int validateCustAssetDetails( PojoComparator<CrmCustAssetDetailsPojo> pojoComparator,
                                          List<CrmCustAssetDetailsPojo> dbAssetDetailsPojos,
                                          int inCount,
                                          CrmCustAssetDetailsPojo inAssetDetailsPojo )
    {
        CrmCustAssetDetailsPojo tmpAssetDetailsPojo;
        int index;
        index = dbAssetDetailsPojos.indexOf( inAssetDetailsPojo );
        if ( inAssetDetailsPojo.getAssetDetailsId() > 0 && index > -1 )
        {
            tmpAssetDetailsPojo = dbAssetDetailsPojos.get( index );
            if ( ( pojoComparator.compare( inAssetDetailsPojo, tmpAssetDetailsPojo ) == 0 ) )
            {
                inCount++;
            }
        }
        return inCount;
    }

    public ActionForward addRowStaticIP( ActionMapping inMapping,
                                         ActionForm inForm,
                                         HttpServletRequest inRequest,
                                         HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        CrmCustAssetDetailsPojo crmCustAssetDetailsPojo = new CrmCustAssetDetailsPojo();
        crmCustAssetDetailsPojo.setAssetDetailsId( 0l );
        crmCustAssetDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmCustAssetDetailsPojo.setCategoryAmount( 0.0 );
        crmCustAssetDetailsPojo.setCustomerRecordId( qrcForm.getCustDetailsPojo().getRecordId() );
        if ( CommonValidator.isValidCollection( qrcForm.getCrmCustAssetDetailsPojos() ) )
        {
            qrcForm.getCrmCustAssetDetailsPojos().add( 0, crmCustAssetDetailsPojo );
        }
        else
        {
            qrcForm.setCrmCustAssetDetailsPojos( new ArrayList<CrmCustAssetDetailsPojo>() );
            qrcForm.getCrmCustAssetDetailsPojos().add( 0, crmCustAssetDetailsPojo );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        //inRequest.setAttribute( IAppConstants.QRC_REQUEST_PARAM,CRMParameter.STATIC_IP.getParameter() );
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_ADD_ACC_PAGE ).getPath(), true );
    }

    public ActionForward removeRowStaticIP( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        int index = Integer.parseInt( inRequest.getParameter( "index" ) );
        if ( index > -1 )
        {
            qrcForm.getCrmCustAssetDetailsPojos().remove( index );
        }
        //inRequest.setAttribute( IAppConstants.QRC_REQUEST_PARAM,CRMParameter.STATIC_IP.getParameter() );
        return new ActionForward( inMapping.findForward( IActionForwardConstant.QRC_ADD_ACC_PAGE ).getPath(), true );
    }

    public ActionForward editServiceRequest( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        boolean process = false;
        LOGGER.info( "Inside QrcAction, editServiceRequest" );
        String target = IActionForwardConstant.VIEW_TICKET_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        CrmQrcDto crmQrcDto = null;
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        String param = inRequest.getParameter( "param" );
        if ( StringUtils.isValidObj( param ) && ( StringUtils.equals( param, "true" ) ) )
        {
            qrcForm.setQrcActions( CRMActionConstants.FOLLOW_UP.getStoringValue() );
            process = true;
        }
        else
        {
            process = QrcFormHelper.validateEditConditions( actionErrors, qrcForm );
            if ( !actionErrors.isEmpty() )
            {
                LOGGER.info( "QRC ACTION:::" + qrcForm.getQrcActions() );
                if ( StringUtils.isValidObj( qrcForm.getQrcActions() ) && ( StringUtils
                        .equals( qrcForm.getQrcActions(), CRMActionConstants.RESOLVE.getStoringValue() ) ) )
                {
                    crmQrcDto = new CrmQrcDto();
                    getActionTakenRootCause( qrcForm, crmQrcDto );
                }
                if ( StringUtils.isValidObj( qrcForm.getQrcActions() ) && ( StringUtils
                        .equals( qrcForm.getQrcActions(), CRMActionConstants.FORWARD.getStoringValue() ) ) )
                {
                    LOGGER.info( "Bin ID & SUB CAT" + qrcForm.getSrTicketPojo().getFunctionalbinId()
                            + qrcForm.getSrTicketPojo().getQrcSubCategoryId() );
                    List<ContentPojo> pojos = getFunctionalBinbyId( qrcForm.getSrTicketPojo().getQrcSubSubCategoryId(),
                                                                    +qrcForm.getSrTicketPojo().getFunctionalbinId() );
                    LOGGER.info( "pojos size:" + pojos.size() );
                    qrcForm.setActionTakens( pojos );
                    qrcForm.setCrmUsers( getUsersByBinID( qrcForm.getSrTicketPojo().getFunctionalbinId() ) );
                    qrcForm.setQrcSubSubCategoriesPojos( QRCCacheManager
                            .getActiveQrcSubSubCategories( qrcForm.getSrTicketPojo().getQrcType(),
                                                           qrcForm.getSrTicketPojo().getQrcCategoryId(),
                                                           qrcForm.getSrTicketPojo().getQrcSubCategoryId() ) );
                }
                saveMessages( inRequest, messages );
                saveErrors( inRequest, actionErrors );
                target = IActionForwardConstant.EDIT_TICKET_PAGE;
                return inMapping.findForward( target );
            }
        }
        try
        {
            LOGGER.info( "Process::" + process );
            if ( ( process ) && StringUtils.isNotEmpty( qrcForm.getQrcActions() ) )
            {
                crmQrcDto = new CrmQrcDto();
                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                qrcForm.getSrTicketPojo().setQrcSubSubCategoryId( qrcForm.getSubSubCategoryId() );
                crmQrcDto.setCrmSrTicketsPojo( qrcForm.getSrTicketPojo() );
                crmQrcDto.setTicketHistory( qrcForm.getTicketHistory() );
                crmQrcDto.getCrmSrTicketsPojo().setLastModifiedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                CRMActionConstants actionConstants = CRMActionConstants
                        .getCRMActionConstants( qrcForm.getQrcActions() );
                crmQrcDto.getTicketHistory().setAction( qrcForm.getQrcActions() );
                crmQrcDto.setInboxSelected( qrcForm.getInboxSelected() );
                switch ( actionConstants )
                {
                    case SAVE_REMARKS:
                        crmQrcDto = getQrcManagerBm().saveTicketHistory( qrcForm.getTicketHistory() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                        {
                            MasterDataDto dataDto = getMasterDataBm().updateReadInbox( qrcForm.getTicketHistory() );
                            if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                LOGGER.info( "Marked inbox entry to Bold" );
                            }
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MSG_REMARKS ) );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        }
                        break;
                    case SAVE_FLAG:
                        qrcForm.getTicketHistory().setAction( "FR" );
                        crmQrcDto = getQrcManagerBm().saveTicketHistory( qrcForm.getTicketHistory() );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                        {
                            MasterDataDto dataDto = getMasterDataBm().updateReadInbox( qrcForm.getTicketHistory() );
                            if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                LOGGER.info( "Marked inbox entry to Bold" );
                            }
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MSG_REMARKS ) );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        }
                        break;
                    case FOLLOW_UP:
                        if ( StringUtils.isValidObj( param ) )
                        {
                            crmQrcDto.getCrmSrTicketsPojo().setFollowupOn( DateUtils
                                    .toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
                        }
                        else
                        {
                            crmQrcDto.getCrmSrTicketsPojo()
                                    .setFollowupOn( DateUtils.changeDateFormatWithTime( qrcForm.getFollowupOn() ) );
                        }
                        crmQrcDto = getQrcManagerBm().ticketOperations( qrcForm.getQrcActions(), crmQrcDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                        {
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_FOLLOWUP,
                                                             crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                            qrcForm.setVisibileButton( false );
                            qrcForm.setSrTicketPojo( crmQrcDto.getCrmSrTicketsPojo() );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        }
                        break;
                    case FORWARD:
                        crmQrcDto.setFutureStage( qrcForm.getFutureStage() );
                        crmQrcDto.setFutureOwner( qrcForm.getFutureOwner() );
                        LOGGER.info( "Flag Selected value :: " + qrcForm.getSrTicketPojo().isFlagSelected() );
                        LOGGER.info( "Flag Remarks :: " + qrcForm.getTicketHistory().getRemarks() );
                        crmQrcDto = getQrcManagerBm().ticketOperations( qrcForm.getQrcActions(), crmQrcDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                        {
                            HttpSession session = inRequest.getSession( false );
                            session.setAttribute( "SuccessMsg", crmQrcDto.getStatusCode() );
                            target = IActionForwardConstant.INBOX;
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                        }
                        break;
                    case RESOLVE:
                        crmQrcDto = getQrcManagerBm().ticketOperations( qrcForm.getQrcActions(), crmQrcDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                        {
                            qrcForm.setSrTicketPojo( crmQrcDto.getCrmSrTicketsPojo() );
                            messages.add( IAppConstants.APP_MESSAGE,
                                          new ActionMessage( IPropertiesConstant.SUCCESS_MSG_TICKET_RESOLVE,
                                                             crmQrcDto.getCrmSrTicketsPojo().getSrId() ) );
                        }
                        else
                        {
                            actionErrors.add( IAppConstants.APP_ERROR,
                                              new ActionMessage( crmQrcDto.getStatusCode(),
                                                                 crmQrcDto.getBillingErrorCode() ) );
                        }
                        break;
                    default:
                        break;
                }
                /*if ( !actionErrors.isEmpty()  )
                {
                    target = IActionForwardConstant.EDIT_TICKET_PAGE;
                }*/
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while saving ticket actions", ex );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        qrcForm.setQrcActions( "0" );
        return inMapping.findForward( target );
    }

    private void getActionTakenRootCause( QrcForm qrcForm, CrmQrcDto crmQrcDto )
    {
        CrmSrTicketsPojo ticketPojo = new CrmSrTicketsPojo();
        CrmCustomerDetailsPojo customerDetailPojo = new CrmCustomerDetailsPojo();
        ticketPojo.setQrcCategoryId( qrcForm.getSrTicketPojo().getQrcCategoryId() );
        customerDetailPojo.setProduct( qrcForm.getCustDetailsPojo().getProduct() );
        ticketPojo.setCustomerDetailsPojo( customerDetailPojo );
        crmQrcDto.setCrmSrTicketsPojo( ticketPojo );
        crmQrcDto = getQrcManagerBm().qrcRcaList( crmQrcDto );
        List<CrmQrcActionTakenPojo> actionTakenPojosdb = crmQrcDto.getQrcActionTakenPojos();
        if ( CommonValidator.isValidCollection( actionTakenPojosdb ) )
        {
            List<ContentPojo> rcaContentPojos = new ArrayList<ContentPojo>();
            for ( CrmQrcActionTakenPojo rcaPojo : actionTakenPojosdb )
            {
                ContentPojo contentPojo = new ContentPojo( rcaPojo.getActionTaken(), rcaPojo.getActionId() + "" );
                rcaContentPojos.add( contentPojo );
            }
            qrcForm.setActionTakens( rcaContentPojos );
        }
        if ( StringUtils.isValidObj( qrcForm.getActionTakens() ) )
        {
            CrmQrcActionTakenPojo rcaPojo = new CrmQrcActionTakenPojo();
            rcaPojo.setActionId( qrcForm.getSrTicketPojo().getActionTakenId() );
            crmQrcDto = new CrmQrcDto();
            crmQrcDto.setQrcActionTakenPojo( rcaPojo );
            crmQrcDto = getQrcManagerBm().qrcRcaReasonList( crmQrcDto );
            List<CrmQrcRootCausePojo> resolutionCodePojos = crmQrcDto.getRootCausePojos();
            if ( StringUtils.isValidObj( resolutionCodePojos ) && !resolutionCodePojos.isEmpty() )
            {
                List<ContentPojo> contentPojos = new ArrayList<ContentPojo>();
                for ( CrmQrcRootCausePojo resolutionCodePojo : resolutionCodePojos )
                {
                    ContentPojo contentPojo = new ContentPojo( resolutionCodePojo.getRootCause(),
                                                               resolutionCodePojo.getRootCauseId() + "" );
                    contentPojos.add( contentPojo );
                }
                qrcForm.setRcaList( contentPojos );
            }
        }
    }

    public ActionForward viewServiceRequestPage( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction, viewServiceRequestPage" );
        CrmSrTicketsPojo srTicketPojo = null;
        CrmQrcDto crmQrcDto = null;
        QrcForm qrcForm = null;
        String target = null;
        String inSR_No = null;
        String customerID = null;
        try
        {
            target = IActionForwardConstant.VIEW_TICKET_PAGE;
            qrcForm = (QrcForm) inForm;
            inSR_No = inRequest.getParameter( "qrcSR_No" );
            customerID = inRequest.getParameter( "customerID" );
            if ( StringUtils.isNotBlank( inSR_No ) && StringUtils.isNotBlank( customerID ) )
            {
                srTicketPojo = new CrmSrTicketsPojo();
                srTicketPojo.setSrId( inSR_No );
                srTicketPojo.setMappingId( customerID );
                crmQrcDto = new CrmQrcDto();
                crmQrcDto.setCrmSrTicketsPojo( srTicketPojo );
                crmQrcDto = getQrcManagerBm().getCustomerTickets( crmQrcDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                    {
                        srTicketPojo = crmQrcDto.getCrmSrTicketsPojos().get( 0 );
                    }
                    String category = QRCCacheManager.getQrcCategory( srTicketPojo.getQrcCategoryId() );
                    if ( StringUtils.isNotEmpty( category ) )
                    {
                        srTicketPojo.setQrcCategory( category );
                    }
                    String subCategory = QRCCacheManager.getQrcSubCategory( srTicketPojo.getQrcCategoryId(),
                                                                            srTicketPojo.getQrcSubCategoryId() );
                    if ( StringUtils.isNotEmpty( subCategory ) )
                    {
                        srTicketPojo.setQrcSubCategory( subCategory );
                    }
                    String subSubCategory = QRCCacheManager
                            .getQrcSubSubCategory( srTicketPojo.getQrcType(), srTicketPojo.getQrcCategoryId(),
                                                   srTicketPojo.getQrcSubCategoryId(),
                                                   srTicketPojo.getQrcSubSubCategoryId() );
                    if ( StringUtils.isNotEmpty( subSubCategory ) )
                    {
                        srTicketPojo.setQrcSubSubCategory( subSubCategory );
                    }
                    qrcForm.setSrTicketPojo( srTicketPojo );
                    ConfigDto configDto = new ConfigDto();
                    configDto.setMappingId( srTicketPojo.getSrId() );
                    configDto = getQrcManagerBm().getRemarks( configDto );
                    qrcForm.getSrTicketPojo().getRemarksPojos().addAll( configDto.getRemarksPojos() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while view Service Request Page", ex );
        }
        return inMapping.findForward( target );
    }

    public ActionForward viewPaymentAndInvoiceDetails( final ActionMapping inMapping,
                                                       final ActionForm inForm,
                                                       final HttpServletRequest inRequest,
                                                       final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction, viewPaymentAndInvoiceDetails" );
        String forward = IActionForwardConstant.VIEW_PAYMENT_AND_INVOICE_DETAILS;
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            getQrcManagerBm().trackPaymentHistory( qrcForm, true );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while view payment and invoice details", ex );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward downloadCustomerUsage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
        throws IOException
    {
        LOGGER.info( "Inside QrcAction, downloadCustomerUsage" );
        QrcForm qrcForm = (QrcForm) inForm;
        CrmQrcDto crmQrcDto = null;
        ServletContext servletContext = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        MessageResources resources = getResources( inRequest );
        String usageType = inRequest.getParameter( "usageType" );
        ActionForward forward = null;
        boolean process = true;
        try
        {
            if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) && StringUtils.isNotBlank( usageType ) )
            {
                crmQrcDto = new CrmQrcDto();
                if ( StringUtils.equals( usageType, IAppConstants.UNBILLED ) )
                {
                    if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
                                             CRMDisplayListConstants.PRE_PAID.getCode() ) )
                    {
                        if ( StringUtils.isValidObj( qrcForm.getCustAdditionalDetails() ) )
                        {
                            crmQrcDto.setUsageFormDate( DateUtils
                                    .getFormattedDate( qrcForm.getCustAdditionalDetails().getActivationDate(),
                                                       IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                            LOGGER.info( "customer service type :: " + qrcForm.getCustDetailsPojo().getServiceType()
                                    + " From date :: " + crmQrcDto.getUsageFormDate() );
                        }
                    }
                    else
                    {
                        crmQrcDto.setUsageFormDate( DateUtils
                                .getFormattedDate( DateUtils
                                        .getPreviousBillingDate( qrcForm.getCustDetailsPojo()
                                                .getBillDate() ), IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                        LOGGER.info( "customer service type :: " + qrcForm.getCustDetailsPojo().getServiceType()
                                + " From date :: " + crmQrcDto.getUsageFormDate() );
                    }
                    crmQrcDto.setUsageToDate( DateUtils.getCurrDateEndTime() );
                    LOGGER.info( "To Date:" + crmQrcDto.getUsageToDate() );
                }
                else if ( StringUtils.equals( usageType, IAppConstants.BILLED ) )
                {
                    QrcFormHelper.validateUsagePeriod( qrcForm, actionErrors );
                    if ( !actionErrors.isEmpty() )
                    {
                        process = false;
                        //                        forward = inMapping.findForward( IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE );
                        PrintWriter out = inResponse.getWriter();
                        ActionMessage msg;
                        for ( Iterator<ActionMessage> iterator = actionErrors.get(); iterator.hasNext(); )
                        {
                            msg = iterator.next();
                            out.println( resources.getMessage( msg.getKey(), msg.getValues() ) );
                        }
                    }
                    crmQrcDto.setUsageFormDate( DateUtils
                            .getFormattedDate( qrcForm.getUsageFormDate(), IDateConstants.FORMAT_DD_MMM_YYYY,
                                               IDateConstants.FORMAT_DD_MMM_YYYY_HH_MM_SS ) );
                    crmQrcDto.setUsageToDate( DateUtils.getEndTimeToDate( qrcForm.getUsageToDate(),
                                                                          IDateConstants.FORMAT_DD_MMM_YYYY ) );
                }
                if ( process )
                {
                    crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                    crmQrcDto = getQrcManagerBm().getCustomerPriodicUsageDetails( crmQrcDto );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                    {
                        servletContext = getServlet().getServletContext();
                        LOGGER.info( "Form : " + crmQrcDto.getUsageFormDate() + " To : " + crmQrcDto.getUsageToDate() );
                        String filePath = servletContext.getRealPath( "/" ) + usageType;
                        String customerName = prepareCustomerFullName( crmQrcDto.getCustomerDetailsPojo() );
                        ExcelWriteUtils excelWriteUtils = new ExcelWriteUtils( customerName,
                                                                               crmQrcDto.getCustomerDetailsPojo()
                                                                                       .getCustomerId(),
                                                                               filePath );
                        if ( CommonValidator.isValidCollection( crmQrcDto.getCustomerUsageDetailsPojos() ) )
                        {
                            /* SortingComparator<CustomerUsageDetailsPojo> sorter = new SortingComparator<CustomerUsageDetailsPojo>( "callEndDate" );
                             Collections.sort( crmQrcDto.getCustomerUsageDetailsPojos(),
                                               Collections.reverseOrder( sorter ) );*/
                            excelWriteUtils.prepareTopRows( usageType, crmQrcDto.getUsageFormDate(),
                                                            crmQrcDto.getUsageToDate() );
                            for ( CustomerUsageDetailsPojo usageDetailsPojo : crmQrcDto.getCustomerUsageDetailsPojos() )
                            {
                                if ( usageDetailsPojo.getUploadKB().intValue() > 0
                                        || usageDetailsPojo.getDownloadKB().intValue() > 0 )
                                {
                                    excelWriteUtils.addUsageDetailsRow( usageDetailsPojo.getCallEndDate(),
                                                                        usageDetailsPojo.getStartTime(),
                                                                        usageDetailsPojo.getEndTime(),
                                                                        usageDetailsPojo.getUploadKB(),
                                                                        usageDetailsPojo.getDownloadKB() );
                                }
                            }
                            excelWriteUtils.prepareBottomRows();
                            excelWriteUtils.lastRowCalculate();
                        }
                        if ( CommonValidator.isValidCollection( crmQrcDto.getCustUsageDetailsPojos() ) )
                        {
                            SortingComparator<CustomerUsageDetailsPojo> sorter = new SortingComparator<CustomerUsageDetailsPojo>( "endTime" );
                            Collections.sort( crmQrcDto.getCustUsageDetailsPojos(),
                                              Collections.reverseOrder( sorter ) );
                            excelWriteUtils.prepareTopRowsForGB( usageType, crmQrcDto.getUsageFormDate(),
                                                                 crmQrcDto.getUsageToDate() );
                            for ( CustomerUsageDetailsPojo usageDetailsPojo : crmQrcDto.getCustUsageDetailsPojos() )
                            {
                                if ( usageDetailsPojo.getUploadKB().doubleValue() > 0
                                        || usageDetailsPojo.getDownloadKB().doubleValue() > 0 )
                                {
                                    excelWriteUtils.addUsageDetailsRow( usageDetailsPojo.getEndTime(),
                                                                        usageDetailsPojo.getUploadKB(),
                                                                        usageDetailsPojo.getDownloadKB() );
                                }
                            }
                            excelWriteUtils.prepareBottomRowsForGB();
                            excelWriteUtils.lastRowCalculateForGB();
                        }
                        LOGGER.info( "File created : " + excelWriteUtils.getFileName() );
                        excelWriteUtils.createExcel();
                        getQrcManagerBm().prepareDownloadResponse( inResponse, excelWriteUtils, servletContext,
                                                                   usageType );
                    }
                    else
                    {
                        //                        actionErrors.add( IAppConstants.APP_ERROR,
                        //                                          new ActionMessage( IPropertiesConstant.USAGE_DETAILS_NOT_FOUND ) );
                        //                        forward = inMapping.findForward( IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE );
                        inResponse.getWriter()
                                .println( resources.getMessage( IPropertiesConstant.USAGE_DETAILS_NOT_FOUND ) );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while download Unbilled Usage", ex );
            inResponse.getWriter().println( "Unable to process your request. Please try again later." );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return forward;
    }

    private String prepareCustomerFullName( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "Inside QrcAction, -downloadBilledUsage" );
        String fullName = null;
        if ( StringUtils.equals( CRMDisplayListConstants.INDIVIDUAL.getCode(),
                                 inCustomerDetailsPojo.getConnectionType() ) )
        {
            /*if ( StringUtils.equals( "M", inCustomerDetailsPojo.getCustGender() ) )
            {
                initial = "Mr.";
            }
            else
            {
                initial = "Miss";
            }*/
            fullName = inCustomerDetailsPojo.getCustFname()
                    + ( StringUtils.isNotBlank( inCustomerDetailsPojo.getCustLname() ) ? ( IAppConstants.SPACE
                            + inCustomerDetailsPojo.getCustLname() ) : IAppConstants.SPACE );
        }
        else
        {
            fullName = inCustomerDetailsPojo.getCustFname();
        }
        return fullName;
    }

    //    public ActionForward sendCustomerUsage( final ActionMapping inMapping,
    //                                            final ActionForm inForm,
    //                                            final HttpServletRequest inRequest,
    //                                            final HttpServletResponse inResponse )
    //    {
    //        LOGGER.info( "Inside QrcAction : sendCustomerUsage" );
    //        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
    //                .getAttribute( IAppConstants.CRM_USER_OBJECT );
    //        QrcForm qrcForm = (QrcForm) inForm;
    //        ActionMessages messages = getMessages( inRequest );
    //        ActionMessages actionErrors = getErrors( inRequest );
    //        String forward = IActionForwardConstant.QRC_CUSTOMER_PROFILE_PAGE;
    //        String usageType = inRequest.getParameter( "usageType" );
    //        QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
    //        /* if ( StringUtils.isEmpty( qrcForm.getCustomerId() ) )
    //         {
    //             actionErrors.add( IAppConstants.APP_ERROR,
    //                               new ActionMessage( IPropertiesConstant.ERROR_INVALID_CUSTOMER_ID ) );
    //             forward = IActionForwardConstant.QRC_SEARCH_CUSTOMER_PAGE;
    //         }
    //         else
    //         {*/
    //        if ( actionErrors.isEmpty() )
    //        {
    //            if ( StringUtils.isNotBlank( usageType ) )
    //            {
    //                CrmQrcDto crmQrcDto = new CrmQrcDto();
    //                if ( StringUtils.equals( usageType, IAppConstants.UNBILLED ) )
    //                {
    //                    if ( StringUtils.equals( qrcForm.getCustDetailsPojo().getServiceType(),
    //                                             CRMDisplayListConstants.PRE_PAID.getCode() ) )
    //                    {
    //                        crmQrcDto
    //                                .setUsageFormDate( DateUtils.getFormattedDate( DateUtils
    //                                                                                       .getPreviousBillingDate( qrcForm
    //                                                                                               .getCustAdditionalDetails()
    //                                                                                               .getExpiryDate() ),
    //                                                                               IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
    //                        LOGGER.info( "customer service type :: " + qrcForm.getCustDetailsPojo().getServiceType()
    //                                + " prev. date :: " + crmQrcDto.getUsageFormDate() );
    //                    }
    //                    else
    //                    {
    //                        crmQrcDto
    //                                .setUsageFormDate( DateUtils.getFormattedDate( DateUtils
    //                                                                                       .getPreviousBillingDate( qrcForm
    //                                                                                               .getCustDetailsPojo()
    //                                                                                               .getBillDate() ),
    //                                                                               IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
    //                        LOGGER.info( "customer service type :: " + qrcForm.getCustDetailsPojo().getServiceType()
    //                                + " prev. date :: " + crmQrcDto.getUsageFormDate() );
    //                    }
    //                    crmQrcDto.setUsageToDate( DateUtils.getCurrDateEndTime() );
    //                }
    //                else if ( StringUtils.equals( usageType, IAppConstants.BILLED ) )
    //                {
    //                    String billPeriod = inRequest.getParameter( "billPeriod" );
    //                    String[] dates = StringUtils.split( billPeriod, " to " );
    //                    crmQrcDto.setUsageFormDate( dates[0] );
    //                    crmQrcDto.setUsageToDate( dates[1] );
    //                    forward = IActionForwardConstant.VIEW_PAYMENT_AND_INVOICE_DETAILS;
    //                }
    //                crmQrcDto.setUsageType( usageType );
    //                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
    //                crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
    //                crmQrcDto = getQrcManagerBm().sendCustomerUsage( crmQrcDto );
    //                LOGGER.info( "Status Code:: " + crmQrcDto.getStatusCode() );
    //                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
    //                {
    //                    if ( StringUtils.isEmpty( crmQrcDto.getSrTicketNo() ) )
    //                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
    //                    else
    //                        messages.add( IAppConstants.APP_MESSAGE,
    //                                      new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_WITH_TICKET, crmQrcDto
    //                                              .getSrTicketNo() ) );
    //                }
    //                else
    //                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
    //            }
    //        }
    //        saveMessages( inRequest, messages );
    //        saveErrors( inRequest, actionErrors );
    //        return inMapping.findForward( forward );
    //    }
    public ActionForward sendEBill( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        CrmQrcDto crmQrcDto = null;
        CrmInvoiceDetailsPojo invoiceDetails = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        String billNumber = inRequest.getParameter( "billNumber" );
        LOGGER.info( "Invoice to Send::" + billNumber );
        try
        {
            crmQrcDto = new CrmQrcDto();
            invoiceDetails = new CrmInvoiceDetailsPojo();
            LOGGER.info( "CustomerId:" + qrcForm.getCustDetailsPojo().getCustomerId() );
            if ( StringUtils.isNotBlank( billNumber ) && CommonValidator.isValidCollection( qrcForm.getInvoiceList() ) )
            {
                invoiceDetails.setBillNumber( billNumber );
                invoiceDetails.setCustomerId( qrcForm.getCustDetailsPojo().getCustomerId() );
                int index = qrcForm.getInvoiceList().indexOf( invoiceDetails );
                if ( index >= 0 )
                {
                    invoiceDetails = qrcForm.getInvoiceList().get( index );
                    crmQrcDto = getQrcManagerBm().sendEBill( invoiceDetails, qrcForm.getCustDetailsPojo().getBrand() );
                }
                LOGGER.info( "Status Code:: " + crmQrcDto.getStatusCode() );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "SucessFully send E-Bill" );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "error ocured while Send-E-Bill", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( IActionForwardConstant.VIEW_PAYMENT_AND_INVOICE_DETAILS );
    }

    private void setAddressPojo( QrcForm qrcForm, List<CrmAddressDetailsPojo> pojos )
    {
        for ( CrmAddressDetailsPojo addressDetailsPojo : pojos )
        {
            if ( StringUtils.equals( addressDetailsPojo.getAddressType(), IAppConstants.ADDRESS_TYPE_BILLING ) )
            {
                qrcForm.setBillingAddressPojo( addressDetailsPojo );
            }
            else if ( StringUtils.equals( addressDetailsPojo.getAddressType(),
                                          IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
            {
                qrcForm.setInstallationAddressPojo( addressDetailsPojo );
            }
        }
        qrcForm.setStateList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        if ( StringUtils.isValidObj( qrcForm.getBillingAddressPojo() ) )
        {
            StatePojo statePojo = GISUtils.getState( qrcForm.getBillingAddressPojo().getStateId() );
            if ( StringUtils.isValidObj( statePojo ) )
            {
                qrcForm.setCityList( statePojo.getCities() );
            }
        }
    }

    private void setSearchString( QrcForm qrcForm, CrmCustomerDetailsPojo customerDetailsPojo )
    {
        String searchString = qrcForm.getSearchString();
        if ( StringUtils.isNumeric( searchString ) )
        {
            customerDetailsPojo.setCustomerId( searchString );
        }
        else
        {
            customerDetailsPojo.setCrfId( searchString );
        }
    }

    public ActionForward saveTicketRemarks( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        String forward = IActionForwardConstant.VIEW_TICKET_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        CrmTicketHistoryPojo ticketHistory = qrcForm.getTicketHistory();
        try
        {
            LOGGER.info( "Remarks Pojo is::::::" + ticketHistory );
            Map<String, Object[]> resultMap = ValidationPojoUtil.validateForm( ticketHistory,
                                                                               ICRMValidationCriteriaUtil.FORM_TICKET_HISTORY_CRITERIA_COMMON,
                                                                               false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                CrmQrcDto qrcDto = getQrcManagerBm().saveTicketHistory( ticketHistory );
                String statusCode = qrcDto.getStatusCode();
                if ( !StringUtils.isEmpty( statusCode ) )
                {
                    if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        MasterDataDto dataDto = getMasterDataBm().updateReadInbox( qrcForm.getTicketHistory() );
                        if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            LOGGER.info( "Marked inbox entry to Bold" );
                        }
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        ticketHistory.setRemarks( null );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in saving remarks", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward getActivityLog( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        HttpSession httpSession = inRequest.getSession( false );
        ConfigDto configDto = getQrcManagerBm().customerActivityOps( qrcForm.getCustDetailsPojo() );
        qrcForm.setCrmCustomerActivityList( configDto.getCustomerActivityPojos() );
        httpSession.setAttribute( CrmCustomerActivityPojo.class.getSimpleName(), qrcForm.getCrmCustomerActivityList() );
        return inMapping.findForward( "customerActivity" );
    }

    public ActionForward getRemarksLog( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "QRCAction::::getRemarksLog()" );
        ConfigDto configDto = null;
        QrcForm qrcForm = (QrcForm) inForm;
        configDto = new ConfigDto();
        if ( StringUtils.isValidObj( qrcForm.getCustDetailsPojo() ) )
        {
            configDto.setMappingId( qrcForm.getCustDetailsPojo().getCustomerId() );
            configDto = getQrcManagerBm().getRemarks( configDto );
            qrcForm.setRemarksPojoList( configDto.getRemarksPojos() );
        }
        return inMapping.findForward( IActionForwardConstant.CUSTOMER_REMARKS );
    }

    //    private List<RemarksPojo> getTicketRemarks( final String inTicketID )
    //    {
    //        LOGGER.info( "Inside QrcAction, -getTicketRemarks" );
    //        List<RemarksPojo> remarksPojos = null;
    //        ConfigDto configDto = null;
    //        if ( StringUtils.isNotBlank( inTicketID ) )
    //        {
    //            configDto = new ConfigDto();
    //            configDto.setMappingId( inTicketID );
    //            configDto = getQrcManagerBm().getRemarks( configDto );
    //            if ( StringUtils.equals( configDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
    //            {
    //                remarksPojos = configDto.getRemarksPojos();
    //            }
    //            else
    //            {
    //                remarksPojos = new ArrayList<RemarksPojo>();
    //            }
    //        }
    //        return remarksPojos;
    //    }
    public ActionForward getWaiverHistory( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        QrcForm qrcForm = (QrcForm) inForm;
        String forward = IActionForwardConstant.WAIVER_HISTORY;
        HttpSession session = inRequest.getSession( false );
        CrmQrcDto qrcDto = getQrcManagerBm().getCustWaiverPojos( qrcForm );
        if ( CommonValidator.isValidCollection( qrcDto.getCrmCustWaiverPojosList() ) )
        {
            qrcForm.setCustWaiverPojos( qrcDto.getCrmCustWaiverPojosList() );
            SortingComparator<CrmCustWaiverPojo> sorter = new SortingComparator<CrmCustWaiverPojo>( "createdTime" );
            Collections.sort( qrcForm.getCustWaiverPojos(), Collections.reverseOrder( sorter ) );
            sorter = null;
        }
        session.setAttribute( CrmCustWaiverPojo.class.getSimpleName(), qrcForm.getCustWaiverPojos() );
        return inMapping.findForward( forward );
    }

    private List<ContentPojo> getFunctionalBinbyId( long inSubSubCategoryId, long inFunctionalBinId )
    {
        List<CrmQrcCategoryBinMappingPojo> binMappingPojos = null;
        List<CrmRcaReasonPojo> rcaReasonPojos = null;
        List<ContentPojo> contentPojos = null;
        CrmSrTicketsPojo srTicketsPojo = null;
        ContentPojo contentPojo = null;
        CrmQrcDto crmQrcDto = null;
        try
        {
            crmQrcDto = new CrmQrcDto();
            srTicketsPojo = new CrmSrTicketsPojo();
            srTicketsPojo.setFunctionalbinId( inFunctionalBinId );
            srTicketsPojo.setQrcSubSubCategoryId( inSubSubCategoryId );
            crmQrcDto.setCrmSrTicketsPojo( srTicketsPojo );
            crmQrcDto = getQrcManagerBm().getFunctionalBinbyId( crmQrcDto );
            binMappingPojos = crmQrcDto.getCrmQrcCategoryBinMappingPojos();
            if ( StringUtils.isValidObj( binMappingPojos ) && !binMappingPojos.isEmpty() )
            {
                contentPojos = new ArrayList<ContentPojo>();
                rcaReasonPojos = CRMCacheManager.getFunctionalBins();
                if ( StringUtils.isValidObj( rcaReasonPojos ) && !rcaReasonPojos.isEmpty() )
                {
                    for ( CrmRcaReasonPojo rcaReasonPojo : rcaReasonPojos )
                    {
                        for ( CrmQrcCategoryBinMappingPojo mappingPojo : binMappingPojos )
                        {
                            if ( rcaReasonPojo.getCategoryId() == mappingPojo.getToBinId() )
                            {
                                contentPojo = new ContentPojo( rcaReasonPojo.getCategoryValue(),
                                                               mappingPojo.getToBinId() + "" );
                                contentPojos.add( contentPojo );
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching Function Bin IDs List ", ex );
        }
        return contentPojos;
    }

    private List<String> getUsersByBinID( long inFunctionalBinId )
    {
        List<CrmUserPojo> crmUserPojos = null;
        List<String> crmUsers = null;
        CrmuserDetailsDto crmUserDetailsDto = null;
        CrmUserPojo crmUserPojo = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            crmUserPojo = new CrmUserPojo();
            crmUserPojo.setFunctionalBin( inFunctionalBinId + "" );
            crmUserPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getUsermngmntbm().searchUser( crmUserDetailsDto );
            crmUserPojos = crmUserDetailsDto.getCrmUserDetailsPojoList();
            if ( StringUtils.isValidObj( crmUserPojos ) && !crmUserPojos.isEmpty() )
            {
                crmUsers = new ArrayList<String>();
                for ( CrmUserPojo userPojo : crmUserPojos )
                {
                    crmUsers.add( userPojo.getUserId() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while get users by Bin ID ", ex );
        }
        return crmUsers;
    }

    public ActionForward viewLMSTicket( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        String forwardPage = IActionForwardConstant.VIEW_TICKET_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        try
        {
            String ticketID = inRequest.getParameter( "ticketId" );
            if ( StringUtils.isValidObj( ticketID ) )
            {
                crmQrcDto = getCustProfileMgrImpl().ticketIDprofileSearch( CustomerProfile.TICKET_ID.getCode(),
                                                                           ticketID, crmQrcDto );
                LOGGER.info( "Status Code::: " + crmQrcDto.getStatusCode() );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        || StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM042.getStatusCode() ) )
                {
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
                    {
                        LOGGER.info( "Lms Ticket Size::: " + crmQrcDto.getCrmSrTicketsPojos().size() );
                        getQrcManagerBm().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                        qrcForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                        qrcForm.setSrTicketPojo( crmQrcDto.getCrmSrTicketsPojos().get( 0 ) );
                        LmsDto lmsDto = getCustProfileMgrImpl()
                                .leadCustomerProfileSearch( CustomerProfile.LEAD_ID.getCode(),
                                                            qrcForm.getSrTicketPojo().getMappingId() );
                        if ( StringUtils.isValidObj( lmsDto )
                                && CommonValidator.isValidCollection( lmsDto.getLeadPojos() ) )
                        {
                            qrcForm.setLmsPojo( lmsDto.getLeadPojos().get( 0 ) );
                        }
                        else
                        {
                            qrcForm.setLmsPojo( new LmsPojo() );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Record not found..  " );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while view LMS tickets", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }
}