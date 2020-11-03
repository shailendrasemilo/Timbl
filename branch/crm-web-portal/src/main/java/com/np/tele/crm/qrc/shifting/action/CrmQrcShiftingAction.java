package com.np.tele.crm.qrc.shifting.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.jboss.logging.MDC;

import com.np.tele.crm.cap.bm.ICrmCapManager;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.config.bm.ICrmConfigManager;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.masterdata.bm.IMasterBMngr;
import com.np.tele.crm.qrc.bm.IQrcManager;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.qrc.forms.QrcFormHelper;
import com.np.tele.crm.qrc.forms.QrcWorkFlowHelper;
import com.np.tele.crm.qrc.workflow.bm.IWorkFlowManager;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmShiftingWorkflowPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SocietyNetworkPartnerPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.utils.SortingComparator;
import com.np.util.StringUtils;
import com.np.validator.util.CommonValidator;

public class CrmQrcShiftingAction
    extends DispatchAction
{
    private static final Logger LOGGER           = Logger.getLogger( CrmQrcShiftingAction.class );
    private IWorkFlowManager    workFlowManager  = null;
    private IQrcManager         qrcManagerBm     = null;
    private ICrmConfigManager   crmConfigManager = null;
    private IMasterBMngr        masterBMngr      = null;
    private ICrmCapManager      capManagerImpl   = null;

    public ICrmConfigManager getCrmConfigManager()
    {
        return crmConfigManager;
    }

    public void setCrmConfigManager( ICrmConfigManager inCrmConfigManager )
    {
        crmConfigManager = inCrmConfigManager;
    }

    public IWorkFlowManager getWorkFlowManager()
    {
        return workFlowManager;
    }

    public void setWorkFlowManager( IWorkFlowManager workFlowManager )
    {
        this.workFlowManager = workFlowManager;
    }

    public IQrcManager getQrcManagerBm()
    {
        return qrcManagerBm;
    }

    public void setQrcManagerBm( IQrcManager inQrcManagerBm )
    {
        qrcManagerBm = inQrcManagerBm;
    }

    public IMasterBMngr getMasterBMngr()
    {
        return masterBMngr;
    }

    public void setMasterBMngr( IMasterBMngr inMasterBMngr )
    {
        masterBMngr = inMasterBMngr;
    }

    public ICrmCapManager getCapManagerImpl()
    {
        return capManagerImpl;
    }

    public void setCapManagerImpl( ICrmCapManager inCapManagerImpl )
    {
        capManagerImpl = inCapManagerImpl;
    }

    public ActionForward shiftingInitiationPage( final ActionMapping inMapping,
                                                 final ActionForm inForm,
                                                 final HttpServletRequest inRequest,
                                                 final HttpServletResponse inResponse )
    {
        LOGGER.info( "In CrmQrcShiftingAction :: shiftingInitiationPage" );
        String forward = IActionForwardConstant.QRC_SHIFTING_INITIATION_PAGE;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        CrmCapDto capDto = null;
        try
        {
            LOGGER.info( "Customer ID::" + qrcForm.getCustomerId() );
            qrcForm.setStateList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
            capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
            if ( StringUtils.isValidObj( capDto )
                    && StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                setShiftingAdd( qrcForm, capDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while shifting Initiation Page ::", ex );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    public ActionForward shiftingInitiation( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.QRC_SHIFTING_INITIATION_PAGE;
        QrcForm qrcForm = (QrcForm) inForm;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        List<CityPojo> cities = new ArrayList<CityPojo>();
        CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
        CrmQrcDto qrcDto = null;
        CrmuserDetailsDto userDto = null;
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_SHIFTING_INITIATION, actionErrors );
            userDto = (CrmuserDetailsDto) inRequest.getSession().getAttribute( IAppConstants.CRM_USER_OBJECT );
            qrcForm.setCrmUserId( userDto.getCrmUserDetailsPojo().getUserId() );
            customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
            if ( actionErrors.isEmpty() )
            {
                MDC.put( "KEY", "CUSTID:" + qrcForm.getCustomerId() );
                qrcDto = getWorkFlowManager().shiftingInitiation( qrcForm );
                if ( StringUtils.isValidObj( qrcDto )
                        && StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    /*get Customer Details for CRF ID and CUSTOMER detail on the top*/
                    CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                    qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                    /**get updated shifting details*/
                    qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
                    if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                        {
                            qrcForm.setCrmShiftingWorkflowPojos( qrcDto.getCrmShiftingWorkflowPojos() );
                            SortingComparator<CrmShiftingWorkflowPojo> sorter = new SortingComparator<CrmShiftingWorkflowPojo>( "createdTime" );
                            Collections
                                    .sort( qrcForm.getCrmShiftingWorkflowPojos(), Collections.reverseOrder( sorter ) );
                            sorter = null;
                            for ( CrmShiftingWorkflowPojo shiftingPojo : qrcForm.getCrmShiftingWorkflowPojos() )
                            {
                                if ( StringUtils.equals( shiftingPojo.getStatus(),
                                                         CRMStatusCode.INPROCESS.getStatusCode() ) )
                                {
                                    /*get latest details for form bcz of page is in request mode*/
                                    qrcForm.setShiftingWorkflowPojo( shiftingPojo );
                                }
                            }
                            CrmCapDto crmCapDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                            if ( StringUtils.isValidObj( crmCapDto )
                                    && StringUtils.equals( crmCapDto.getStatusCode(),
                                                           CRMServiceCode.CRM001.getStatusCode() ) )
                            {
                                setShiftingAdd( qrcForm, crmCapDto );
                            }
                        }
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                    }
                    else
                    {
                        messages.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "Status Code ::" + qrcDto.getStatusCode() );
                    CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                    if ( StringUtils.isValidObj( capDto )
                            && StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        setShiftingAdd( qrcForm, capDto );
                    }
                    LOGGER.info( "CAP Status Code ::" + capDto.getStatusCode() );
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
                }
            }
            qrcForm.setStateList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
            if ( StringUtils.isValidObj( qrcForm.getStateName() ) )
            {
                cities = GISUtils.getCities( qrcForm.getStateList(), qrcForm.getStateName() );
            }
            qrcForm.setCityList( cities );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured while shifting Initiation:", ex );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest.getSession(), messages );
        return inMapping.findForward( target );
    }

    private void setShiftingAdd( QrcForm qrcForm, CrmCapDto capDto )
    {
        qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
        qrcForm.setOldshiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
        for ( CrmAddressDetailsPojo addressDetailsPojo : capDto.getCustomerDetailsPojo().getCrmAddressDetailses() )
        {
            if ( StringUtils.equals( addressDetailsPojo.getAddressType(), IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
            {
                qrcForm.getShiftingWorkflowPojo().setShiftingType( CRMDisplayListConstants.OUTSIDE_PREMISES.getCode() );
                qrcForm.getOldshiftingWorkflowPojo().setAddressLine1( addressDetailsPojo.getAddLine1() );
                qrcForm.getOldshiftingWorkflowPojo().setAddressLine2( addressDetailsPojo.getAddLine2() );
                qrcForm.getOldshiftingWorkflowPojo().setAddressLine3( addressDetailsPojo.getAddLine3() );
                qrcForm.getOldshiftingWorkflowPojo().setPincode( addressDetailsPojo.getPincode() );
            }
        }
    }

    public ActionForward editStage( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        String target = null;
        String workflowStage = "";
        String workflowId = "";
        String shiftingType = "";
        String customerId = "";
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
        if ( StringUtils.equals( qrcForm.getParamValue(), IAppConstants.WORKFLOW ) )
        {
            CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
            workflowId = qrcForm.getShiftingWorkflowPojo().getWorkflowId();
            customerId = qrcForm.getCustomerId();
            customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
            CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
            qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
            shiftingType = qrcForm.getShiftingWorkflowPojo().getShiftingType();
            workflowStage = qrcForm.getShiftingWorkflowPojo().getWorkflowStage();
            qrcForm.setRemarksPojo( new RemarksPojo() );
        }
        else
        {
            workflowStage = inRequest.getParameter( "inStage" );
            workflowId = inRequest.getParameter( "workflowId" );
            shiftingType = inRequest.getParameter( "inWorkflowType" );
            customerId = inRequest.getParameter( "inCustomerId" );
            String inboxId = inRequest.getParameter( "inboxId" );
            qrcForm.setRemarksPojo( new RemarksPojo() );
        }
        if ( StringUtils.equals( workflowStage, CRMOperationStages.FT_LEVEL1.getCode() ) )
        {
            try
            {
                target = IActionForwardConstant.IFR_STAGE;
                String method = IAppConstants.METHOD_IFR_STAGE;
                if ( StringUtils.isEmpty( qrcForm.getParamValue() ) )
                {
                    QrcWorkFlowHelper.reset( qrcForm, method );
                }
                shiftingWorkflowPojo.setWorkflowId( workflowId );
                shiftingWorkflowPojo.setWorkflowStage( workflowStage );
                shiftingWorkflowPojo.setShiftingType( shiftingType );
                shiftingWorkflowPojo.setCustomerId( customerId );
                qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                customerDetailsPojo.setCustomerId( customerId );
                CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                qrcForm.setCustomerId( customerId );
                shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
                CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                    setNetworkPartners( qrcForm, actionErrors );
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( ex.getCause(), ex );
                actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            }
        }
        else if ( StringUtils.equals( workflowStage, CRMOperationStages.CSD_LEVEL2.getCode() ) )
        {
            try
            {
                target = IActionForwardConstant.CSD_OUTCALL_STAGE;
                qrcForm.setCustomerId( customerId );
                shiftingWorkflowPojo.setWorkflowId( workflowId );
                shiftingWorkflowPojo.setWorkflowStage( workflowStage );
                shiftingWorkflowPojo.setShiftingType( shiftingType );
                shiftingWorkflowPojo.setCustomerId( customerId );
                shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
                qrcForm.setPlanDetailsPojo( new CrmPlanDetailsPojo() );
                qrcForm.setTeleservicesPayment( new CrmPaymentDetailsPojo() );
                qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
                CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                }
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                customerDetailsPojo.setCustomerId( customerId );
                CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                if ( StringUtils.isValidObj( capDto )
                        && StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                    LOGGER.info( "Service::" + qrcForm.getCustDetailsPojo().getServiceType() + ",Product:"
                            + qrcForm.getShiftingWorkflowPojo().getProduct() );
                    CRMDisplayListConstants constant = CRMDisplayListConstants
                            .getConstantByFilter( "PlanService", qrcForm.getShiftingWorkflowPojo().getProduct(),
                                                  qrcForm.getCustDetailsPojo().getServiceType() );
                    String prepaid = ( qrcForm.getCustDetailsPojo().getServiceType().equals( "PR" ) ? "Y" : "N" );
                    if ( StringUtils.isNotBlank( qrcForm.getPlanTypeShifting() ) )
                    {
                        qrcForm.setBasePlanNames( CRMCacheManager.getActivationPlansList( constant.getCode(), prepaid,
                                                                                          qrcForm.getPlanType() ) );
                    }
                    else
                    {
                        qrcForm.setBasePlanNames( CRMCacheManager.getActivationPlans( constant.getCode(), prepaid ) );
                    }
                    qrcForm.setAddonPlanNames( CRMCacheManager.getAddonPlanDetails( constant.getCode() ) );
                    SortingComparator<CrmPlanMasterPojo> sorter = new SortingComparator<CrmPlanMasterPojo>( "planName" );
                    Collections.sort( qrcForm.getBasePlanNames(), sorter );
                    Collections.sort( qrcForm.getAddonPlanNames(), sorter );
                    sorter = null;
                    qrcForm.setPlanService( constant.getCode() );
                    MasterDataDto masterDataDto = getCloserReason();
                    if ( CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
                    {
                        qrcForm.setCloserReasonList( masterDataDto.getCrmRcaReasonsList() );
                    }
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( ex.getCause(), ex );
            }
        }
        else if ( StringUtils.equals( workflowStage, CRMOperationStages.IFR_EOC_LEVEL1.getCode() ) )
        {
            target = IActionForwardConstant.IFR_EOC_LEVEL1;
            shiftingWorkflowPojo.setWorkflowId( workflowId );
            shiftingWorkflowPojo.setWorkflowStage( workflowStage );
            shiftingWorkflowPojo.setCustomerId( customerId );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            qrcForm.setCustomerId( customerId );
            shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                }
                if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                    List<CrmPartnerNetworkConfigPojo> networkConfigList = getCapManagerImpl()
                            .getMasterNameList( qrcForm.getShiftingWorkflowPojo().getNpId(),
                                                qrcForm.getShiftingWorkflowPojo().getProduct() );
                    if ( CommonValidator.isValidCollection( networkConfigList ) )
                    {
                        qrcForm.setMasterNameList( networkConfigList );
                    }
                }
            }
            qrcForm.getShiftingWorkflowPojo().setPhysicalInstallation( IAppConstants.YES_CHAR );
            qrcForm.getShiftingWorkflowPojo().setCustomerRefusal( IAppConstants.NO_CHAR );
            qrcForm.getShiftingWorkflowPojo().setCpeAvailable( IAppConstants.YES_CHAR );
        }
        else if ( StringUtils.equals( workflowStage, CRMOperationStages.CSD_LEVEL3.getCode() ) )
        {
            target = IActionForwardConstant.CSD_LEVEL3;
            shiftingWorkflowPojo.setWorkflowId( workflowId );
            shiftingWorkflowPojo.setWorkflowStage( workflowStage );
            shiftingWorkflowPojo.setShiftingType( shiftingType );
            shiftingWorkflowPojo.setCustomerId( customerId );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            qrcForm.setNetworkConfigurationsPojo( new CrmNetworkConfigurationsPojo() );
            qrcForm.setCustomerId( customerId );
            shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                }
            }
            qrcForm.setCustomerResponse( IAppConstants.YES_CHAR );
            //Fetch Closer Reason
            MasterDataDto masterDataDto = getCloserReason();
            if ( StringUtils.isValidObj( masterDataDto.getCrmRcaReasonsList() )
                    && !masterDataDto.getCrmRcaReasonsList().isEmpty() )
            {
                qrcForm.setCloserReasonList( masterDataDto.getCrmRcaReasonsList() );
            }
        }
        else if ( StringUtils.equals( workflowStage, CRMOperationStages.NOC_LEVEL1.getCode() ) )
        {
            target = IActionForwardConstant.NOC_LEVEL1;
            shiftingWorkflowPojo.setWorkflowId( workflowId );
            shiftingWorkflowPojo.setWorkflowStage( workflowStage );
            shiftingWorkflowPojo.setShiftingType( shiftingType );
            shiftingWorkflowPojo.setCustomerId( customerId );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            qrcForm.setCustomerId( customerId );
            shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                customerDetailsPojo.setCustomerId( customerId );
                CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                    if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getProduct(),
                                             CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                    {
                        if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getPreviousNetwork(),
                                                 CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                        {
                            List<CrmPartnerNetworkConfigPojo> oldnetworkConfigList = getCapManagerImpl()
                                    .getMasterNameList( qrcForm.getCustDetailsPojo().getNpId(),
                                                        qrcForm.getCustDetailsPojo().getProduct() );
                            for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo : oldnetworkConfigList )
                            {
                                if ( StringUtils.equals( qrcForm.getNetworkConfigurationsPojo().getOption82(),
                                                         String.valueOf( crmPartnerNetworkConfigPojo.getRecordId() ) ) )
                                {
                                    qrcForm.setCrmPartnerNetworkConfig( crmPartnerNetworkConfigPojo );
                                    break;
                                }
                                else
                                {
                                    qrcForm.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
                                }
                            }
                        }
                        else
                        {
                            qrcForm.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
                        }
                        List<CrmPartnerNetworkConfigPojo> networkConfigList = getCapManagerImpl()
                                .getMasterNameList( qrcForm.getShiftingWorkflowPojo().getNpId(),
                                                    qrcForm.getShiftingWorkflowPojo().getProduct() );
                        for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo1 : networkConfigList )
                        {
                            if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getOption82(),
                                                     String.valueOf( crmPartnerNetworkConfigPojo1.getRecordId() ) ) )
                            {
                                qrcForm.setNewcrmPartnerNetworkConfig( crmPartnerNetworkConfigPojo1 );
                                break;
                            }
                            else
                            {
                                qrcForm.setNewcrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
                            }
                        }
                    }
                }
            }
        }
        else if ( StringUtils.equals( workflowStage, CRMOperationStages.IFR_EOC_LEVEL2.getCode() ) )
        {
            target = IActionForwardConstant.ADDRESS_CHANGE_PAGE;
            String method = IAppConstants.METHOD_EDIT_IFRAD;
            if ( StringUtils.isEmpty( qrcForm.getParamValue() ) )
            {
                QrcWorkFlowHelper.reset( qrcForm, method );
            }
            qrcForm.setCustomerId( customerId );
            shiftingWorkflowPojo.setWorkflowId( workflowId );
            shiftingWorkflowPojo.setCustomerId( customerId );
            shiftingWorkflowPojo.setShiftingType( shiftingType );
            //qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            QrcFormHelper.validateCustomerID( actionErrors, qrcForm );
            if ( actionErrors.isEmpty() )
            {
                qrcForm.setCustomerId( customerId );
                shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
                qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
                CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                    setGisDataForAddressChange( customerId, qrcForm );
                }
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( target );
    }

    private void setGisDataForAddressChange( String customerId, QrcForm qrcForm )
    {
        GisDto dto = getWorkFlowManager().getGISDetailsById( String.valueOf( qrcForm.getShiftingWorkflowPojo()
                                                                     .getStateId() ),
                                                             String.valueOf( qrcForm.getShiftingWorkflowPojo()
                                                                     .getCityId() ),
                                                             String.valueOf( qrcForm.getShiftingWorkflowPojo()
                                                                     .getAreaId() ),
                                                             String.valueOf( qrcForm.getShiftingWorkflowPojo()
                                                                     .getSocietyId() ) );
        CrmAddressDetailsPojo pojo = new CrmAddressDetailsPojo();
        pojo.setStateName( dto.getAreaPojo().getDisplaystateId() );
        pojo.setStateId( qrcForm.getShiftingWorkflowPojo().getStateId() );
        pojo.setCityId( qrcForm.getShiftingWorkflowPojo().getCityId() );
        pojo.setCityName( dto.getAreaPojo().getDisplayCityId() );
        pojo.setInstAreaId( qrcForm.getShiftingWorkflowPojo().getAreaId() );
        pojo.setInstSocietyId( qrcForm.getShiftingWorkflowPojo().getSocietyId() );
        pojo.setPincode( qrcForm.getShiftingWorkflowPojo().getPincode() );
        pojo.setLandmark( qrcForm.getShiftingWorkflowPojo().getLandmark() );
        String arr[] = dto.getAreaPojo().getDisplaysocietyId().split( "-" );
        pojo.setAddLine1( qrcForm.getShiftingWorkflowPojo().getHouseNumber().concat( ", " ).concat( arr[0] ) );
        if ( arr.length >= 2 )
        {
            pojo.setAddLine2( arr[1].concat( ", " ) + dto.getAreaPojo().getDisplayareaId() );
        }
        else
        {
            pojo.setAddLine2( dto.getAreaPojo().getDisplayareaId() );
        }
        if ( StringUtils.isNotEmpty( qrcForm.getShiftingWorkflowPojo().getHouseNumber() ) )
        {
            qrcForm.setHouseNumber( qrcForm.getShiftingWorkflowPojo().getHouseNumber() );
        }
        pojo.setAddLine3( dto.getAreaPojo().getDisplayCityId() + IAppConstants.COMMA
                + dto.getAreaPojo().getDisplaystateId() );
        qrcForm.setChangeInstArea( dto.getAreaPojo().getDisplayareaId() );
        qrcForm.setChangeInnstSociety( dto.getAreaPojo().getDisplaysocietyId() );
        CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
        customerDetailsPojo.setCustomerId( customerId );
        CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
        if ( StringUtils.equals( capDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            customerDetailsPojo = capDto.getCustomerDetailsPojo();
            qrcForm.setCustDetailsPojo( customerDetailsPojo );
            for ( CrmAddressDetailsPojo pojo1 : customerDetailsPojo.getCrmAddressDetailses() )
            {
                if ( StringUtils.equals( pojo1.getAddressType(), IAppConstants.INSTALLATION_ADDRESS ) )
                {
                    qrcForm.setInstallationAddressPojo( pojo1 );
                    break;
                }
            }
            setAddressPojo( qrcForm, customerDetailsPojo.getCrmAddressDetailses() );
            pojo.setRecordId( qrcForm.getInstallationAddressPojo().getRecordId() );
            qrcForm.setChangeInstallationAddressPojo( pojo );
        }
    }

    private CrmQrcDto getShiftingDetailsForCustomer( QrcForm qrcForm, CrmShiftingWorkflowPojo shiftingWorkflowPojo )
    {
        shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
        qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
        CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
        qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
        return qrcDto;
    }

    private MasterDataDto getCloserReason()
    {
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        crmRcaReasonPojo.setCategory( CRMRCAReason.ADS.getStatusCode() );
        crmRcaReasonPojo.setSubCategory( CRMRCAReason.ADS_REASON.getStatusCode() );
        crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.ADDRESS_SHIFTING_REASON_CLOSE.getStatusCode() );
        crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        MasterDataDto masterDataDto = new MasterDataDto();
        masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
        masterDataDto = getMasterBMngr().masterOperation( masterDataDto );
        return masterDataDto;
    }

    public ActionForward submitIfrStage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.IFR_STAGE;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        CrmQrcDto qrcDto = null;
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_IFR_STAGE, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                qrcForm.getShiftingWorkflowPojo().setCustomerId( qrcForm.getCustomerId() );
                qrcForm.getShiftingWorkflowPojo().setWorkflowStage( CRMOperationStages.FT_LEVEL1.getCode() );
                qrcDto = getWorkFlowManager().submitIfr( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.isValidObj( qrcDto )
                        && StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    target = IActionForwardConstant.WORKFLOW_INBOX;
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                }
                else
                {
                    LOGGER.info( "Status Code::" + qrcDto.getStatusCode() );
                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in submitIfrStage", ex );
        }
        finally
        {
            setIfrNetworkPartner( actionErrors, qrcForm, qrcDto );
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest.getSession(), messages );
        }
        return inMapping.findForward( target );
    }

    public void setIfrNetworkPartner( ActionMessages actionErrors, QrcForm qrcForm, CrmQrcDto qrcDto )
    {
        CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
        try
        {
            customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
            CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
            qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
            shiftingWorkflowPojo.setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
            qrcForm.setShiftingWorkflowPojo( shiftingWorkflowPojo );
            qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                    && CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
            {
                qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                setNetworkPartners( qrcForm, actionErrors );
            }
            else
            {
                actionErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in setIfrNetworkPartner", ex );
        }
    }

    public ActionForward submitcsdStage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.CSD_OUTCALL_STAGE;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        CrmuserDetailsDto userDto = null;
        CrmQrcDto qrcDto = null;
        CrmCapDto capDto = null;
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_CSD_LEVEL2, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                userDto = (CrmuserDetailsDto) inRequest.getSession().getAttribute( IAppConstants.CRM_USER_OBJECT );
                qrcForm.getShiftingWorkflowPojo().setCustomerId( qrcForm.getCustomerId() );
                qrcForm.getShiftingWorkflowPojo().setWorkflowStage( CRMOperationStages.CSD_LEVEL2.getCode() );
                qrcForm.getShiftingWorkflowPojo().setBasePlanCode( qrcForm.getPlanDetailsPojo().getBasePlanCode() );
                qrcForm.getShiftingWorkflowPojo().setAddOnPlanCode( qrcForm.getPlanDetailsPojo().getAddOnPlanCode() );
                if ( StringUtils.equals( qrcForm.getCustomerResponse(), IAppConstants.YES_CHAR ) )
                {
                    CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                    customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
                    capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                    qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                    qrcDto = getQrcManagerBm().getCustAdditionDetails( qrcForm.getCustDetailsPojo() );
                    if ( StringUtils.isValidObj( qrcDto )
                            && StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        qrcForm.setCustAdditionalDetails( qrcDto.getCustAdditionalDetails() );
                    }
                    CRMDisplayListConstants constant = CRMDisplayListConstants
                            .getConstantByFilter( "PlanService", qrcForm.getCustDetailsPojo().getProduct(), qrcForm
                                    .getCustDetailsPojo().getServiceType() );
                    LOGGER.info( "Constant" + constant );
                    qrcForm.setPlanMasterList( getQrcManagerBm().getMigratedActivationPlan( qrcForm ) );
                    qrcForm.setSelectedPlanCode( qrcForm.getPlanDetailsPojo().getBasePlanCode() );
                }
                qrcDto = getWorkFlowManager().submitcsd( qrcForm, userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.isValidObj( qrcDto )
                        && StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    target = IActionForwardConstant.WORKFLOW_INBOX;
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                }
                else if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM440.getStatusCode() ) )
                {
                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode(), qrcForm
                            .getCustAdditionalDetails().getBalance(), qrcForm.getDiffRentInclTax() ) );
                }
                else
                {
                    LOGGER.info( "Status Code::" + qrcDto.getStatusCode() );
                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in submitcsdStage ::", ex );
        }
        finally
        {
            if ( !actionErrors.isEmpty() )
            {
                MasterDataDto masterDataDto = getCloserReason();
                if ( CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
                {
                    qrcForm.setCloserReasonList( masterDataDto.getCrmRcaReasonsList() );
                }
                CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", qrcForm
                        .getShiftingWorkflowPojo().getProduct(), qrcForm.getCustDetailsPojo().getServiceType() );
                String prepaid = ( qrcForm.getCustDetailsPojo().getServiceType().equals( "PR" ) ? "Y" : "N" );
                if ( StringUtils.isNotBlank( qrcForm.getPlanTypeShifting() ) )
                {
                    qrcForm.setBasePlanNames( CRMCacheManager.getActivationPlansList( constant.getCode(), prepaid,
                                                                                      qrcForm.getPlanType() ) );
                }
                else
                {
                    qrcForm.setBasePlanNames( CRMCacheManager.getActivationPlans( constant.getCode(), prepaid ) );
                }
                qrcForm.setAddonPlanNames( CRMCacheManager.getAddonPlanDetails( constant.getCode() ) );
                SortingComparator<CrmPlanMasterPojo> sorter = new SortingComparator<CrmPlanMasterPojo>( "planName" );
                Collections.sort( qrcForm.getBasePlanNames(), sorter );
                Collections.sort( qrcForm.getAddonPlanNames(), sorter );
                sorter = null;
            }
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest.getSession(), messages );
        }
        return inMapping.findForward( target );
    }

    public ActionForward submitftLevel2( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.FTLEVEL2;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        CrmuserDetailsDto userDto = null;
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_IFR_EOC_LEVEL2, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                userDto = (CrmuserDetailsDto) inRequest.getSession().getAttribute( IAppConstants.CRM_USER_OBJECT );
                qrcForm.getShiftingWorkflowPojo().setCustomerId( qrcForm.getCustomerId() );
                qrcForm.getShiftingWorkflowPojo().setPreviousNetwork( qrcForm.getShiftingWorkflowPojo()
                                                                              .getPreviousNetwork() );
                qrcForm.getShiftingWorkflowPojo().setPreviousNpId( Long.parseLong( qrcForm.getPreviousPartnerId() ) );
                CrmQrcDto qrcDto = getWorkFlowManager().submitftLevel2( qrcForm,
                                                                        userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    target = IActionForwardConstant.WORKFLOW_INBOX;
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in submitftLevel2", ex );
        }
        finally
        {
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest.getSession(), messages );
        }
        return inMapping.findForward( target );
    }

    public ActionForward submitIfrEOCL1( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.IFR_EOC_LEVEL1;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        CrmuserDetailsDto userDto = null;
        QrcForm qrcForm = (QrcForm) inForm;
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_EOC_LEVEL_ONE, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                userDto = (CrmuserDetailsDto) inRequest.getSession().getAttribute( IAppConstants.CRM_USER_OBJECT );
                qrcForm.getShiftingWorkflowPojo().setCustomerId( qrcForm.getCustomerId() );
                CrmQrcDto qrcDto = getWorkFlowManager().submitIfrEOCL1( qrcForm,
                                                                        userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    target = IActionForwardConstant.WORKFLOW_INBOX;
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in submitIfrEOCL1", ex );
        }
        finally
        {
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest.getSession(), messages );
        }
        return inMapping.findForward( target );
    }

    public ActionForward submitCSDLevel3( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        String target = IActionForwardConstant.CSD_LEVEL3;
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        CrmuserDetailsDto userDto = null;
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_CSD_LEVEL3, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                userDto = (CrmuserDetailsDto) inRequest.getSession().getAttribute( IAppConstants.CRM_USER_OBJECT );
                qrcForm.getShiftingWorkflowPojo().setCustomerId( qrcForm.getCustomerId() );
                CrmQrcDto qrcDto = getWorkFlowManager().submitCSDLevel3( qrcForm,
                                                                         userDto.getCrmUserDetailsPojo().getUserId() );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    target = IActionForwardConstant.WORKFLOW_INBOX;
                    qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( qrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured in submitCSDLevel3", ex );
        }
        finally
        {
            MasterDataDto masterDataDto = getCloserReason();
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
            {
                qrcForm.setCloserReasonList( masterDataDto.getCrmRcaReasonsList() );
            }
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest.getSession(), messages );
        }
        return inMapping.findForward( target );
    }

    public ActionForward submitNOCLevel1( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        String target = IActionForwardConstant.NOC_LEVEL1;
        CrmuserDetailsDto userDto = null;
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        try
        {
            QrcWorkFlowHelper.validate( qrcForm, IAppConstants.METHOD_NOC_LEVEL1, actionErrors );
            if ( actionErrors.isEmpty() )
            {
                userDto = (CrmuserDetailsDto) inRequest.getSession().getAttribute( IAppConstants.CRM_USER_OBJECT );
                //For MAC
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                crmQrcDto.setCrmShiftingWorkflowPojo( qrcForm.getShiftingWorkflowPojo() );
                crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                qrcForm.getNetworkConfigurationsPojo().setOption82( qrcForm.getNewOption82() );
                crmCustomerDetailsPojo.setProduct( qrcForm.getShiftingWorkflowPojo().getProduct() );
                crmCustomerDetailsPojo.setNpId( qrcForm.getShiftingWorkflowPojo().getNpId() );
                crmQrcDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
                crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                crmQrcDto.getRemarksPojo().setMappingId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                crmQrcDto.setNetworkConfigurationsPojo( qrcForm.getNetworkConfigurationsPojo() );
                crmQrcDto = getWorkFlowManager().submitNOCLevel1( crmQrcDto );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    target = IActionForwardConstant.WORKFLOW_INBOX;
                    qrcForm.setShiftingWorkflowPojo( crmQrcDto.getCrmShiftingWorkflowPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR,
                                      new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error occured in submitNOCLevel1", ex );
        }
        finally
        {
            saveErrors( inRequest, actionErrors );
            saveMessages( inRequest.getSession(), messages );
        }
        return inMapping.findForward( target );
    }

    public ActionForward forwardToNextBin( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        ActionMessages actionErrors = new ActionMessages();
        ActionMessages messages = new ActionMessages();
        QrcForm qrcForm = (QrcForm) inForm;
        String workflowId = inRequest.getParameter( "inWorkflowId" );
        String custId = inRequest.getParameter( "inCustomerId" );
        String shiftingType = inRequest.getParameter( "inShiftingType" );
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
        String forward = IActionForwardConstant.INSTALLATION_PARTNER_STAGE;
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        qrcForm.getShiftingWorkflowPojo().setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
        qrcForm.getShiftingWorkflowPojo().setWorkflowId( workflowId );
        qrcForm.getShiftingWorkflowPojo().setCustomerId( custId );
        qrcForm.getShiftingWorkflowPojo().setShiftingType( shiftingType );
        qrcForm.setCustomerId( custId );
        CrmQrcDto qrcDto = getShiftingDetailsForCustomer( qrcForm, shiftingWorkflowPojo );
        if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            qrcDto = getWorkFlowManager().forwardToNextBin( qrcForm );
            if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojo() );
                forward = IActionForwardConstant.WORKFLOW_INBOX;
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
            }
            else
            {
                actionErrors.add( IAppConstants.APP_ERROR,
                                  new ActionMessage( qrcDto.getStatusCode(), qrcDto.getBillingErrorCode() ) );
            }
        }
        saveMessages( inRequest.getSession(), messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    /*public ActionForward addressChangeFeasibility( final ActionMapping inMapping,
                                                   final ActionForm inForm,
                                                   final HttpServletRequest inRequest,
                                                   final HttpServletResponse inResponse )
    {
        return inMapping.findForward( IActionForwardConstant.ADDRESS_CHANGE_FEASIBILITY );
    }*/
    private void setAddressPojo( QrcForm qrcForm, List<CrmAddressDetailsPojo> pojos )
    {
        for ( CrmAddressDetailsPojo addressDetailsPojo : pojos )
        {
            if ( StringUtils.equals( addressDetailsPojo.getAddressType(), IAppConstants.ADDRESS_TYPE_BILLING ) )
            {
                qrcForm.setBillingAddressPojo( addressDetailsPojo );
            }
            else if ( StringUtils.equals( addressDetailsPojo.getAddressType(), IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
            {
                qrcForm.setInstallationAddressPojo( addressDetailsPojo );
            }
        }
        qrcForm.setStateList( GISUtils.getActiveStates( IAppConstants.COUNTRY_INDIA ) );
        if ( CommonValidator.isValidCollection( qrcForm.getStateList() )
                && qrcForm.getInstallationAddressPojo().getStateId() > 0 )
        {
            qrcForm.setCityList( GISUtils.getCities( qrcForm.getStateList(), qrcForm.getInstallationAddressPojo()
                    .getStateId() ) );
        }
        if ( CommonValidator.isValidCollection( qrcForm.getCityList() )
                && qrcForm.getInstallationAddressPojo().getCityId() > 0 )
        {
            qrcForm.setAreaList( GISUtils.getAreas( qrcForm.getCityList(), qrcForm.getInstallationAddressPojo()
                    .getCityId() ) );
        }
        if ( CommonValidator.isValidCollection( qrcForm.getAreaList() )
                && qrcForm.getInstallationAddressPojo().getInstAreaId() > 0 )
        {
            AreaPojo areaPojo = GISUtils.getArea( qrcForm.getInstallationAddressPojo().getInstAreaId(),
                                                  qrcForm.getAreaList() );
            if ( StringUtils.isValidObj( areaPojo ) )
            {
                qrcForm.setInstAddrArea( areaPojo.getArea() );
                qrcForm.setSocietyList( GISUtils.getSocietybyAreaPartnerService( areaPojo.getAreaId(), qrcForm
                        .getCustDetailsPojo().getNpId(), qrcForm.getCustDetailsPojo().getProduct() ) );
                SocietyPojo societyPojo = GISUtils.getSociety( qrcForm.getInstallationAddressPojo().getInstSocietyId(),
                                                               qrcForm.getSocietyList() );
                if ( StringUtils.isValidObj( societyPojo ) )
                {
                    if ( StringUtils.isNotBlank( societyPojo.getSocietyName() ) )
                    {
                        qrcForm.setInstAddrSociety( societyPojo.getLocalityName() + " - "
                                + societyPojo.getSocietyName() );
                    }
                    else
                    {
                        qrcForm.setInstAddrSociety( societyPojo.getLocalityName() );
                    }
                }
            }
        }
    }

    public ActionForward changeInstallationAddress( final ActionMapping inMapping,
                                                    final ActionForm inForm,
                                                    final HttpServletRequest inRequest,
                                                    final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.ADDRESS_CHANGE_PAGE;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        QrcForm qrcForm = (QrcForm) inForm;
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
        shiftingWorkflowPojo.setCustomerId( inRequest.getParameter( "inCustomerId" ) );
        shiftingWorkflowPojo.setWorkflowId( inRequest.getParameter( "inWorkflowId" ) );
        shiftingWorkflowPojo.setShiftingType( inRequest.getParameter( "inShiftingType" ) );
        try
        {
            qrcForm.setCustomerId( inRequest.getParameter( "inCustomerId" ) );
            getShiftingDetailsForCustomer( qrcForm, shiftingWorkflowPojo );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            qrcForm.setCrmUserId( userDto.getCrmUserDetailsPojo().getUserId() );
            QrcWorkFlowHelper.validateChangeInstAddr( errors, qrcForm );
            if ( errors.isEmpty() )
            {
                if ( qrcForm.getChangeInstallationAddressPojo().getAddLine1().charAt( 0 ) == ',' )
                {
                    qrcForm.getChangeInstallationAddressPojo().getAddLine1().replaceFirst( ",", "" );
                }
                qrcForm.getShiftingWorkflowPojo().setAddressLine1( qrcForm.getChangeInstallationAddressPojo()
                                                                           .getAddLine1() );
                qrcForm.getShiftingWorkflowPojo().setAddressLine3( qrcForm.getChangeInstallationAddressPojo()
                                                                           .getAddLine3() );
                qrcForm.getShiftingWorkflowPojo().setAddressLine2( qrcForm.getChangeInstallationAddressPojo()
                                                                           .getAddLine2() );
                qrcForm.getShiftingWorkflowPojo().setHouseNumber( qrcForm.getHouseNumber() );
                qrcForm.getShiftingWorkflowPojo()
                        .setLandmark( qrcForm.getChangeInstallationAddressPojo().getLandmark() );
                qrcForm.getShiftingWorkflowPojo().setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                qrcForm.getShiftingWorkflowPojo().setLastModifiedBy( userDto.getCrmUserDetailsPojo().getUserId() );
                qrcForm.getShiftingWorkflowPojo().setWorkflowStage( CRMOperationStages.IFR_EOC_LEVEL2.getCode() );
                crmQrcDto = getQrcManagerBm().changeInstallationAddress( qrcForm );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    forward = IActionForwardConstant.WORKFLOW_INBOX;
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.error( "Exception occured while final change Installation Address", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                setGisDataForAddressChange( qrcForm.getCustomerId(), qrcForm );
            }
            saveMessages( inRequest.getSession(), messages );
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward viewShiftingPage( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.SHIFTING_VIEW;
        ActionMessages errors = getErrors( inRequest );
        ActionMessages messages = getMessages( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        String workflowId = inRequest.getParameter( "inWorkflowId" );
        String requestType = inRequest.getParameter( "inRequestType" );
        String inboxId = inRequest.getParameter( "inInboxId" );
        try
        {
            qrcForm.setShiftingWorkflowPojo( new CrmShiftingWorkflowPojo() );
            if ( StringUtils.isNotEmpty( workflowId )
                    && StringUtils.equals( requestType, CRMRequestType.SHIFTING.getRequestCode() ) )
            {
                qrcForm.getShiftingWorkflowPojo().setWorkflowId( workflowId );
                qrcForm.getShiftingWorkflowPojo().setStatus( CRMStatusCode.INPROCESS.getStatusCode() );
                CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
                if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    MasterDataDto dataDto = getMasterBMngr().updateUnreadInbox( StringUtils.numericValue( inboxId ) );
                    if ( StringUtils.equals( dataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Marked inbox entry to unbold" );
                    }
                    if ( StringUtils.isValidObj( qrcDto.getCrmShiftingWorkflowPojo() ) )
                    {
                        GisDto dto = getWorkFlowManager().getGISDetailsById( String.valueOf( qrcDto
                                                                                     .getCrmShiftingWorkflowPojos()
                                                                                     .get( 0 ).getStateId() ),
                                                                             String.valueOf( qrcDto
                                                                                     .getCrmShiftingWorkflowPojos()
                                                                                     .get( 0 ).getCityId() ),
                                                                             String.valueOf( qrcDto
                                                                                     .getCrmShiftingWorkflowPojos()
                                                                                     .get( 0 ).getAreaId() ),
                                                                             String.valueOf( qrcDto
                                                                                     .getCrmShiftingWorkflowPojos()
                                                                                     .get( 0 ).getSocietyId() ) );
                        qrcForm.setStateName( dto.getAreaPojo().getDisplaystateId() );
                        qrcForm.setCityName( dto.getAreaPojo().getDisplayCityId() );
                        qrcForm.setAreaName( dto.getAreaPojo().getDisplayareaId() );
                        qrcForm.setSocietyName( dto.getAreaPojo().getDisplaysocietyId() );
                        qrcForm.setShiftingWorkflowPojo( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ) );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            LOGGER.error( "Exception Occured::", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward getShiftingHistory( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.SHIFTING_HISTORY;
        QrcForm qrcForm = (QrcForm) inForm;
        String method = IAppConstants.METHOD_GET_SHIFTING_HISTORY;
        qrcForm.setCustomerId( inRequest.getParameter( "customerId" ) );
        QrcWorkFlowHelper.reset( qrcForm, method );
        CrmQrcDto qrcDto = getWorkFlowManager().getShiftingDetials( qrcForm );
        if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
            {
                qrcForm.setCrmShiftingWorkflowPojos( qrcDto.getCrmShiftingWorkflowPojos() );
                SortingComparator<CrmShiftingWorkflowPojo> sorter = new SortingComparator<CrmShiftingWorkflowPojo>( "createdTime" );
                Collections.sort( qrcForm.getCrmShiftingWorkflowPojos(), Collections.reverseOrder( sorter ) );
                sorter = null;
            }
        }
        return inMapping.findForward( forward );
    }

    private void setNetworkPartners( QrcForm qrcForm, ActionMessages actionErrors )
    {
        List<SocietyPojo> societyPojos = GISUtils.getActiveSocieties( IAppConstants.COUNTRY_INDIA, qrcForm
                .getShiftingWorkflowPojo().getStateId(), qrcForm.getShiftingWorkflowPojo().getCityId(), qrcForm
                .getShiftingWorkflowPojo().getAreaId() );
        Set<PartnerPojo> partnerPojos = new HashSet<PartnerPojo>();
        if ( CommonValidator.isValidCollection( societyPojos ) )
        {
            List<SocietyNetworkPartnerPojo> societyNetworkPartnerPojos = null;
            if ( qrcForm.getShiftingWorkflowPojo().getSocietyId() > 0 )
            {
                SocietyPojo societyPojo = GISUtils.getSociety( qrcForm.getShiftingWorkflowPojo().getSocietyId(),
                                                               societyPojos );
                if ( StringUtils.isValidObj( societyPojo ) )
                {
                    societyNetworkPartnerPojos = societyPojo.getSocietyNetworkPartners();
                    if ( societyNetworkPartnerPojos.size() > 0 )
                    {
                        for ( SocietyNetworkPartnerPojo spojo : societyNetworkPartnerPojos )
                        {
                            if ( StringUtils.equals( spojo.getStatus(), CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                partnerPojos.add( CRMCacheManager.getPartnerById( spojo.getPartnerId() + "" ) );
                            }
                        }
                    }
                }
            }
            List<PartnerPojo> activePartners = CRMCacheManager
                    .getPartnerByPartnerType( CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
            if ( CommonValidator.isValidCollection( activePartners )
                    && CommonValidator.isValidCollection( partnerPojos ) )
            {
                partnerPojos.retainAll( activePartners );
            }
            if ( CommonValidator.isValidCollection( partnerPojos ) )
            {
                qrcForm.setPartnerPojoList( new ArrayList<PartnerPojo>( partnerPojos ) );
            }
            else
            {
                actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( "error.no.active.np" ) );
            }
        }
    }

    /*   public ActionForward bindOption82( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
       {
           LOGGER.info( "Inside Shifting : editOption82" );
           ActionMessages messages = getMessages( inRequest );
           ActionMessages actionErrors = getErrors( inRequest );
           QrcForm qrcForm = (QrcForm) inForm;
           String requestType = "workflow";
           String masterName = "";
           String poolName = "";
           String nassPortId = "";
           String forward = IActionForwardConstant.NOC_LEVEL1;
           String custId = inRequest.getParameter( "customerId" );
           CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                   .getAttribute( IAppConstants.CRM_USER_OBJECT );
           try
           {
               CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
               QrcFormHelper.validateOption82( actionErrors, qrcForm );
               if ( actionErrors.isEmpty() )
               {
                   CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                   customerDetailsPojo.setCustomerId( custId );
                   qrcForm.setCustomerId( custId );
                   CrmQrcDto qrcDto = getShiftingDetailsForCustomer( qrcForm, shiftingWorkflowPojo );
                   CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
                   if ( StringUtils.equals( capDto.getCustomerDetailsPojo().getProduct(),
                                            CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                   {
                       masterName = qrcForm.getCrmPartnerNetworkConfig().getMasterName();
                       poolName = qrcForm.getCrmPartnerNetworkConfig().getPoolName();
                       nassPortId = qrcForm.getCrmPartnerNetworkConfig().getNasPortId();
                   }
                   qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
                   capDto.getCustomerDetailsPojo().setNpId( qrcForm.getShiftingWorkflowPojo().getNpId() );
                   capDto.getCustomerDetailsPojo().setProduct( qrcForm.getShiftingWorkflowPojo().getProduct() );
                   qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                    String oldprimaryMacAddress = capDto.getNetworkConfigurationsPojo().getCurrentCpeMacId();
                    String oldSecondryMacAddress = capDto.getNetworkConfigurationsPojo().getCurrentSlaveMacId();
                   CrmQrcDto crmQrcDto = new CrmQrcDto();
                   crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                   crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
                   crmQrcDto.setActivityAction( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc() );
                   qrcForm.getNetworkConfigurationsPojo().setOption82( qrcForm.getNewOption82() );
                   qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( capDto.getNetworkConfigurationsPojo()
                                                                                      .getCurrentCpeMacId() );
                   qrcForm.getNetworkConfigurationsPojo().setCurrentSlaveMacId( capDto.getNetworkConfigurationsPojo()
                                                                                        .getCurrentCpeMacId() );
                   crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
                   crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
                   crmQrcDto.getRemarksPojo().setMappingId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
                   forward = IActionForwardConstant.NOC_LEVEL1;
                   qrcForm.setParamValue( IAppConstants.WORKFLOW );
                   crmQrcDto.setNetworkConfigurationsPojo( qrcForm.getNetworkConfigurationsPojo() );
                   crmQrcDto.setCustomerDetailsPojo( qrcForm.getCustDetailsPojo() );
                   crmQrcDto = getQrcManagerBm().editDevice( crmQrcDto );
                   String statusCode = crmQrcDto.getStatusCode();
                   if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
                   {
                       crmQrcDto = getWorkFlowManager().updateCustomerDetailsForMAC( qrcForm,
                                                                                     userDto.getCrmUserDetailsPojo()
                                                                                             .getUserId() );
                       CrmCustomerDetailsPojo customerDetailsPojo2 = new CrmCustomerDetailsPojo();
                       customerDetailsPojo.setCustomerId( custId );
                       //updated customer details are
                       CrmCapDto capDto2 = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo2 );
                        qrcForm.setCustDetailsPojo( capDto2.getCustomerDetailsPojo() );
                        qrcForm.setNetworkConfigurationsPojo( capDto.getNetworkConfigurationsPojo() );
                       if ( CommonValidator.isValidCollection( qrcDto.getCrmShiftingWorkflowPojos() ) )
                       {
                           if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getProduct(),
                                                    CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                           {
                               oldNnewMacDetails( qrcForm, qrcDto );
                           }
                       }
                       CrmPartnerNetworkConfigPojo pojo = new CrmPartnerNetworkConfigPojo();
                       if ( StringUtils.isNotBlank( nassPortId ) )
                       {
                           pojo.setNasPortId( nassPortId );
                       }
                       if ( StringUtils.isNotBlank( poolName ) )
                       {
                           pojo.setPoolName( poolName );
                       }
                       if ( StringUtils.isNotBlank( masterName ) )
                       {
                           pojo.setMasterName( masterName );
                       }
                       qrcForm.setCrmPartnerNetworkConfig( pojo );
                       if ( StringUtils.isValidObj( crmQrcDto.getCrmPartnerNetworkConfigPojo() ) )
                       {
                           qrcForm.setPartnerNetworkConfigPojo( crmQrcDto.getCrmPartnerNetworkConfigPojo() );
                       }
                       messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( qrcDto.getStatusCode() ) );
                   }
               }
           }
           catch ( Exception ex )
           {
               LOGGER.error( "Exception :::", ex );
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

       private void oldNnewMacDetails( QrcForm qrcForm, CrmQrcDto qrcDto )
       {
           List<CrmPartnerNetworkConfigPojo> oldnetworkConfigList = getCapManagerImpl()
                   .getMasterNameList( qrcDto.getCrmShiftingWorkflowPojos().get( 0 ).getNpId(),
                                       qrcDto.getCrmShiftingWorkflowPojos().get( 0 ).getProduct() );
           List<CrmPartnerNetworkConfigPojo> networkConfigList = getCapManagerImpl()
                   .getMasterNameList( qrcForm.getShiftingWorkflowPojo().getNpId(),
                                       qrcForm.getShiftingWorkflowPojo().getProduct() );
           for ( CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo1 : networkConfigList )
           {
               if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getOption82(),
                                        String.valueOf( crmPartnerNetworkConfigPojo1.getRecordId() ) ) )
               {
                   qrcForm.setNewcrmPartnerNetworkConfig( crmPartnerNetworkConfigPojo1 );
                   break;
               }
               else
               {
                   qrcForm.setNewcrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
               }
           }
       }*/
    /*public ActionForward editMacForShifting( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside QrcAction : macChange" );
        String forward = IActionForwardConstant.NOC_LEVEL1;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        QrcForm qrcForm = (QrcForm) inForm;
        CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
        customerDetailsPojo.setCustomerId( qrcForm.getCustomerId() );
        CrmShiftingWorkflowPojo shiftingWorkflowPojo = new CrmShiftingWorkflowPojo();
        CrmQrcDto qrcDto = getShiftingDetailsForCustomer( qrcForm, shiftingWorkflowPojo );
        CrmCapDto capDto = getQrcManagerBm().getCustomerProfileDetails( customerDetailsPojo );
        qrcForm.setCustDetailsPojo( capDto.getCustomerDetailsPojo() );
         String newPrimaryMacId = qrcForm.getShiftingWorkflowPojo().getCurrentCPeMacId();
         String oldPrimaryMacId = qrcForm.getNetworkConfigurationsPojo().getCurrentCpeMacId();
        qrcForm.setCustomerId( qrcForm.getCustomerId() );
        qrcForm.setParamValue( IAppConstants.WORKFLOW );
        String oldSecondaryMac = null;
        try
        {
            String oldMacId = qrcForm.getNetworkConfigurationsPojo().getCurrentCpeMacId();
            if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                oldSecondaryMac = qrcForm.getNetworkConfigurationsPojo().getCurrentSlaveMacId();
            }
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
            crmQrcDto.setUserId( userDto.getCrmUserDetailsPojo().getUserId() );
            crmQrcDto.setActivityAction( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc() );
            qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( qrcForm.getShiftingWorkflowPojo()
                                                                               .getCurrentCPeMacId() );
            //EOC
            if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getProduct(),
                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
            {
                if ( StringUtils.isNotBlank( qrcForm.getShiftingWorkflowPojo().getCurrentSlaveMacId() ) )
                {
                    qrcForm.getNetworkConfigurationsPojo().setCurrentSlaveMacId( qrcForm.getShiftingWorkflowPojo()
                                                                                         .getCurrentSlaveMacId() );
                }
                crmQrcDto.setMacFaulty( true );
            }
            CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
            crmCustomerDetailsPojo.setProduct( qrcForm.getShiftingWorkflowPojo().getProduct() );
            crmQrcDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            crmQrcDto.setSrTicketNo( qrcForm.getSrTicketNo() );
            crmQrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
            crmQrcDto.getRemarksPojo().setMappingId( qrcForm.getShiftingWorkflowPojo().getWorkflowId() );
            crmQrcDto.setNetworkConfigurationsPojo( qrcForm.getNetworkConfigurationsPojo() );
            crmQrcDto = getQrcManagerBm().editDevice( crmQrcDto );
            String statusCode = crmQrcDto.getStatusCode();
            if ( StringUtils.equals( statusCode, CRMServiceCode.CRM001.getStatusCode() ) )
            {
                qrcForm.setNetworkConfigurationsPojo( crmQrcDto.getNetworkConfigurationsPojo() );
                qrcForm.getShiftingWorkflowPojo().setCurrentSlaveMacId( null );
                if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                {
                    qrcForm.getShiftingWorkflowPojo().setCurrentSlaveMacId( null );
                }
                qrcForm.setRemarksPojo( new RemarksPojo() );
                messages.add( IAppConstants.APP_MESSAGE,
                              new ActionMessage( IPropertiesConstant.SUCCESS_DEVICE_MAC_CHANGE ) );
            }
            else
            {
                qrcForm.getNetworkConfigurationsPojo().setCurrentCpeMacId( oldMacId );
                if ( StringUtils.equals( qrcForm.getShiftingWorkflowPojo().getProduct(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                {
                    qrcForm.getNetworkConfigurationsPojo().setCurrentSlaveMacId( oldSecondaryMac );
                }
                actionErrors.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( crmQrcDto.getStatusCode(), crmQrcDto.getBillingErrorCode() ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest.getSession(), actionErrors );
        saveMessages( inRequest.getSession(), messages );
        return inMapping.findForward( forward );
    }*/
    public ActionForward getCustomerPlan( final ActionMapping inMapping,
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
            if ( StringUtils.isValidObj( qrcForm.getShiftingWorkflowPojo() ) )
            {
                LOGGER.info( qrcForm.getShiftingWorkflowPojo().getProduct() );
                LOGGER.info( qrcForm.getCustDetailsPojo().getServiceType() );
                /*  LOGGER.info( "plan category :: " + qrcForm.getPlanCategory() );*/
                CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService", qrcForm
                        .getShiftingWorkflowPojo().getProduct(), qrcForm.getCustDetailsPojo().getServiceType() );
                LOGGER.info( "Constant" + constant );
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
                if ( !StringUtils.isValidObj( qrcForm.getCrmPlanMasterPojo() ) )
                {
                    qrcForm.setCrmPlanMasterPojo( new CrmPlanMasterPojo() );
                }
                /*crmQrcDto = new CrmQrcDto();
                crmQrcDto.setCustomerId( qrcForm.getCustomerId() );
                LOGGER.info( "plan category :: " + qrcForm.getPlanCategory() + " :: usageType :: "
                        + qrcForm.getPlanUsageType() );*/
            }
        }
        return inMapping.findForward( forward );
    }
}
