package com.np.tele.crm.qrc.workflow.bm;

import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.MasterDataClient;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.CRMGISService;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmFinanceService;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.GisDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class WorkFlowManagerImpl
    implements IWorkFlowManager
{
    private static final Logger LOGGER           = Logger.getLogger( WorkFlowManagerImpl.class );
    private CrmQrcService       qrcServiceClient = null;
    private MasterDataClient    masterDataClient = null;
    private CrmFinanceService   financeService   = null;
    private CRMGISService       gisServiceClient = null;

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService qrcServiceClient )
    {
        this.qrcServiceClient = qrcServiceClient;
    }

    public MasterDataClient getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterDataClient inMasterDataClient )
    {
        masterDataClient = inMasterDataClient;
    }

    public CrmFinanceService getFinanceService()
    {
        return financeService;
    }

    public void setFinanceService( CrmFinanceService inFinanceService )
    {
        financeService = inFinanceService;
    }

    public CRMGISService getGisServiceClient()
    {
        return gisServiceClient;
    }

    public void setGisServiceClient( CRMGISService inGisServiceClient )
    {
        gisServiceClient = inGisServiceClient;
    }

    public CrmQrcDto approveRejectWaiver( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        LOGGER.info( "User id" + inQrcForm.getCrmUserId() );
        crmQrcDto.setUserId( inQrcForm.getCrmUserId() );
        crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        crmQrcDto.setCrmCustWaiverPojo( inQrcForm.getCustWaiverPojo() );
        try
        {
            crmQrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.MODIFY.getParameter(),
                                                                  CRMParameter.WAIVER.getParameter(), crmQrcDto );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception Occured", e );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto shiftingInitiation( QrcForm inQrcForm )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        inQrcForm.getShiftingWorkflowPojo().setCustomerId( inQrcForm.getCustomerId() );
        inQrcForm.getShiftingWorkflowPojo().setWorkflowStage( CRMOperationStages.INITIATE.getCode() );
        qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        qrcDto.setUserId( inQrcForm.getCrmUserId() );
        String locality = inQrcForm.getShiftingWorkflowPojo().getLocality();
        String society = "";
        String[] arr = StringUtils.split( locality, IAppConstants.DASH );
        if ( arr.length > 1 )
        {
            locality = arr[0];
            society = arr[1];
        }
        inQrcForm.getShiftingWorkflowPojo().setLocality( locality );
        inQrcForm.getShiftingWorkflowPojo().setSociety( society );
        qrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        qrcDto.getCrmShiftingWorkflowPojo().setShiftingType( inQrcForm.getShiftingWorkflowPojo().getShiftingType() );
        try
        {
            GisDto dto = new GisDto();
            dto.setStateName( inQrcForm.getStateName() );
            dto.setCityName( inQrcForm.getCityName() );
            dto.setAreaName( inQrcForm.getAreaName() );
            dto.setSocietyName( StringUtils.isNotBlank( qrcDto.getCrmShiftingWorkflowPojo().getSociety() ) ? ( qrcDto
                    .getCrmShiftingWorkflowPojo().getLocality() + "-" + qrcDto.getCrmShiftingWorkflowPojo()
                    .getSociety() ) : qrcDto.getCrmShiftingWorkflowPojo().getLocality() );
            dto = getGisServiceClient().gisOperation( ServiceParameter.LIST.getParameter(),
                                                      CRMParameter.GIS_DETAILS.getParameter(), dto );
            LOGGER.info( "Status Code ::" + dto.getStatusCode() );
            if ( StringUtils.equals( dto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "Area Pojo ::" + dto.getAreaPojo() );
                //qrcDto.getCrmShiftingWorkflowPojo().setShiftingType(CRMDisplayListConstants.OUTSIDE_PREMISES.getCode() );
                qrcDto.getCrmShiftingWorkflowPojo()
                        .setStateId( Long.parseLong( dto.getAreaPojo().getDisplaystateId() ) );
                qrcDto.getCrmShiftingWorkflowPojo().setCityId( Long.parseLong( dto.getAreaPojo().getDisplayCityId() ) );
                qrcDto.getCrmShiftingWorkflowPojo().setAreaId( Long.parseLong( dto.getAreaPojo().getDisplayareaId() ) );
                qrcDto.getCrmShiftingWorkflowPojo().setSocietyId( Long.parseLong( dto.getAreaPojo()
                                                                          .getDisplaysocietyId() ) );
                qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                                   CRMParameter.INITIATE.getParameter(), qrcDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto submitIfr( QrcForm inQrcForm, String inUserId )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        LOGGER.info( "Customer Id::>>>>>>>>>>>:::" + inQrcForm.getCustomerId() );
        LOGGER.info( "Network Partner Id:: " + inQrcForm.getShiftingWorkflowPojo().getNpId() );
        qrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        qrcDto.setUserId( inUserId );
        qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        LOGGER.info( "shifting workflow pojo on managerImpl is :: " + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                               CRMParameter.FT_STAGE.getParameter(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while ifr stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public List<CrmPartnerMappingPojo> getServicePartners( String inNpId, String inProduct )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            PartnerPojo pojo = new PartnerPojo();
            masterDataDto = new MasterDataDto();
            pojo.setPartnerId( Long.parseLong( inNpId ) );
            CrmPartnerMappingPojo mappingpojo = new CrmPartnerMappingPojo();
            mappingpojo.setPartnerByNpId( pojo );
            mappingpojo.setProduct( inProduct );
            mappingpojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setCrmPartnerMappingPojo( mappingpojo );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.VIEW.getParameter(),
                                                                     masterDataDto );
            LOGGER.info( "Result Mapping List Size........" + masterDataDto.getCrmPartnerMappingList().size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching service partners: " + ex );
        }
        return masterDataDto.getCrmPartnerMappingList();
    }

    @Override
    public CrmFinanceDto paymentPosting( QrcForm inQrcForm )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        crmFinanceDto.setPaymentDetailsPojo( inQrcForm.getCrmPaymentDetailsPojo() );
        crmFinanceDto.getPaymentDetailsPojo().setCustomerId( inQrcForm.getCustomerId() );
        crmFinanceDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
        crmFinanceDto.setUserId( inQrcForm.getCrmPaymentDetailsPojo().getCreatedBy() );
        try
        {
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.POST.getParameter(),
                                                             CRMParameter.PAYMENT.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error While, posting the payment: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmFinanceDto paymentReversal( QrcForm inQrcForm, String userId, RemarksPojo inRemarks )
    {
        CrmFinanceDto crmFinanceDto = new CrmFinanceDto();
        inQrcForm.getCrmPaymentDetailsPojo().setStatus( CRMStatusCode.PAYMENT_REVERSAL.getStatusCode() );
        inQrcForm.getCrmPaymentDetailsPojo().setLastModifiedBy( userId );
        crmFinanceDto.setRemarks( inRemarks );
        crmFinanceDto.setPaymentDetailsPojo( inQrcForm.getCrmPaymentDetailsPojo() );
        crmFinanceDto.setCustomerDetailsPojo( inQrcForm.getCustDetailsPojo() );
        crmFinanceDto.setUserId( userId );
        crmFinanceDto.setReversalWOCrf( true );
        try
        {
            crmFinanceDto = getFinanceService().postPayment( ServiceParameter.POST.getParameter(),
                                                             CRMParameter.REVERSAL.getParameter(), crmFinanceDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error While, posting the payment: ", ex );
        }
        return crmFinanceDto;
    }

    @Override
    public CrmQrcDto forwardToNextBin( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        crmQrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        crmQrcDto.setUserId( inQrcForm.getShiftingWorkflowPojo().getCreatedBy() );
        try
        {
            if ( StringUtils.equals( inQrcForm.getShiftingWorkflowPojo().getWorkflowStage(),
                                     CRMOperationStages.IFR_EOC_LEVEL2.getCode() ) )
            {
                crmQrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                                      CRMParameter.IFR_EOC_LEVEL2.getParameter(),
                                                                      crmQrcDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while forward the docket to the next bin :: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getShiftingDetials( QrcForm inQrcForm )
    {
        CrmQrcDto crmQrcDto = new CrmQrcDto();
        crmQrcDto.setCustomerId( inQrcForm.getCustomerId() );
        LOGGER.info( "Customer Id" + crmQrcDto.getCustomerId() );
        crmQrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        try
        {
            crmQrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                                  ServiceParameter.VIEW.getParameter(), crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Exception while retriveing shifting details:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    private boolean balanceCheckCondition( CrmQrcDto inCrmQrcDto, QrcForm inQrcForm )
    {
        LOGGER.info( "Inside QrcManagerImpl, -balanceCheckCondition" );
        CrmPlanMasterPojo newPlanMasterPojo = null;
        CrmPlanMasterPojo oldPlanMasterPojo = null;
        CrmPlanMasterPojo newAddonPlanMasterPojo = null;
        CrmPlanMasterPojo oldAddonPlanMasterPojo = null;
        try
        {
            if ( CommonValidator.isValidCollection( inQrcForm.getPlanMasterList() )
                    && StringUtils.isNotBlank( inQrcForm.getSelectedPlanCode() ) )
            {
                LOGGER.info( "Selected Plan Code:: " + inQrcForm.getSelectedPlanCode() );
                newPlanMasterPojo = CommonUtils.getPlanByPlanCode( inQrcForm.getSelectedPlanCode() );
                oldPlanMasterPojo = CommonUtils.getPlanByPlanCode( inQrcForm.getCustDetailsPojo().getCrmPlanDetailses()
                        .get( 0 ).getBasePlanCode() );
                if ( StringUtils.isNotEmpty( inQrcForm.getPlanDetailsPojo().getAddOnPlanCode() ) )
                {
                    newAddonPlanMasterPojo = CommonUtils.getPlanByPlanCode( inQrcForm.getPlanDetailsPojo()
                            .getAddOnPlanCode() );
                }
                if ( StringUtils.isNotEmpty( inQrcForm.getCustDetailsPojo().getCrmPlanDetailses().get( 0 )
                        .getAddOnPlanCode() ) )
                {
                    oldAddonPlanMasterPojo = CommonUtils.getPlanByPlanCode( inQrcForm.getCustDetailsPojo()
                            .getCrmPlanDetailses().get( 0 ).getAddOnPlanCode() );
                }
            }
            if ( StringUtils.isValidObj( inQrcForm.getCustAdditionalDetails() )
                    && StringUtils.isValidObj( inQrcForm.getCustDetailsPojo() ) )
            {
                if ( StringUtils.isValidObj( newPlanMasterPojo )
                        && StringUtils.isValidObj( oldPlanMasterPojo )
                        && StringUtils.equals( inQrcForm.getCustDetailsPojo().getServiceType(),
                                               CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    Double diff = 0D;
                    Double newRentInclTax = 0D;
                    Double oldRentInclTax = 0D;
                    newRentInclTax = newPlanMasterPojo.getRentInclTax().doubleValue();
                    newRentInclTax += newPlanMasterPojo.getSecurityDeposit();
                    if ( StringUtils.isValidObj( newAddonPlanMasterPojo ) )
                    {
                        newRentInclTax += newAddonPlanMasterPojo.getRentInclTax().doubleValue();
                    }
                    oldRentInclTax = oldPlanMasterPojo.getRentInclTax().doubleValue();
                    oldRentInclTax += oldPlanMasterPojo.getSecurityDeposit();
                    if ( StringUtils.isValidObj( oldAddonPlanMasterPojo ) )
                    {
                        oldRentInclTax += oldAddonPlanMasterPojo.getRentInclTax().doubleValue();
                    }
                    if ( newRentInclTax > oldRentInclTax )
                    {
                        diff = newRentInclTax - oldRentInclTax;
                        LOGGER.info( "Calculated Rent Diff: " + diff );
                        if ( inQrcForm.getCustAdditionalDetails().getBalance() < ( diff - 1 ) )
                        {
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM440.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM440.getStatusDesc() );
                            inQrcForm.setDiffRentInclTax( diff );
                            return false;
                        }
                    }
                    inCrmQrcDto.setCustAdditionalDetails( inQrcForm.getCustAdditionalDetails() );
                }
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking balance condition for customer", ex );
        }
        return true;
    }

    @Override
    public CrmQrcDto submitcsd( QrcForm inQrcForm, String inUserId )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        qrcDto.setUserId( inUserId );
        qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        LOGGER.info( "shifting workflow pojo on managerImpl is :: " + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            if ( StringUtils.equals( inQrcForm.getCustomerResponse(), IAppConstants.YES_CHAR )
                    && balanceCheckCondition( qrcDto, inQrcForm ) )
            {
                if ( !StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM997.getStatusCode() ) )
                {
                    qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                                       CRMParameter.FT_NETWORK_STAGE.getParameter(),
                                                                       qrcDto );
                }
            }
            else if ( StringUtils.equals( inQrcForm.getCustomerResponse(), IAppConstants.NO_CHAR ) )
            {
                qrcDto = getQrcServiceClient()
                        .workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                             CRMParameter.FT_NETWORK_STAGE.getParameter(), qrcDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while csd stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    public CrmQrcDto submitftLevel2( QrcForm qrcForm, String userId )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCrmShiftingWorkflowPojo( qrcForm.getShiftingWorkflowPojo() );
        qrcDto.setUserId( userId );
        qrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
        LOGGER.info( "shifting workflow pojo on managerImpl is :: " + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                               CRMParameter.IFR_EOC_LEVEL1.getParameter(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while csd stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public List<PartnerPojo> getPartnerDetailsbyId( String inNpId )
    {
        MasterDataDto masterDataDto = null;
        List<PartnerPojo> partnerList = null;
        try
        {
            masterDataDto = new MasterDataDto();
            PartnerPojo partnerPojo = new PartnerPojo();
            partnerPojo.setPartnerId( StringUtils.numericValue( inNpId ) );
            masterDataDto.setPartnerPojo( partnerPojo );
            masterDataDto = getMasterDataClient().getMasterData( masterDataDto,
                                                                 CRMParameter.PARTNER_POJO.getParameter() );
            LOGGER.info( "Status Method:: " + masterDataDto.getStatusDesc() );
            partnerList = masterDataDto.getPartnerPojoList();
            LOGGER.info( "Result Mapping List Size........" + masterDataDto.getCrmPartnerMappingList().size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching service partners: " + ex );
        }
        return partnerList;
    }

    public CrmQrcDto submitIfrEOCL1( QrcForm qrcForm, String userId )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCrmShiftingWorkflowPojo( qrcForm.getShiftingWorkflowPojo() );
        qrcDto.setUserId( userId );
        qrcDto.setRemarksPojo( qrcForm.getRemarksPojo() );
        LOGGER.info( "shifting workflow pojo on managerImpl is :: " + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            String stage = CRMParameter.CSD_OUTCALL_LEVEL3.getParameter();
            if ( StringUtils.equals( IAppConstants.NO_CHAR, qrcForm.getCustomerResponse() ) )
            {
                if ( StringUtils.equals( IAppConstants.YES_CHAR, qrcForm.getPhysicalInstallation() )
                        && StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), qrcForm
                                .getShiftingWorkflowPojo().getProduct() ) )
                {
                    if ( StringUtils.equals( IAppConstants.YES_CHAR, qrcForm.getCpeAvailable() ) )
                    {
                        stage = CRMParameter.NOC_LEVEL1.getParameter();
                    }
                }
                if ( StringUtils.equals( IAppConstants.YES_CHAR, qrcForm.getPhysicalInstallation() )
                        && StringUtils.equals( CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode(), qrcForm
                                .getShiftingWorkflowPojo().getProduct() ) )
                {
                    stage = CRMParameter.NOC_LEVEL1.getParameter();
                }
            }
            qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(), stage, qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while csd stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto submitCSDLevel3( QrcForm inQrcForm, String inUserId )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        qrcDto.setUserId( inUserId );
        qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        //LOGGER.info( "Reason Id::" + inQrcForm.getRemarksPojo().getReasonId() );
        LOGGER.info( "shifting workflow pojo on managerImpl is :: " + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                               CRMParameter.CSD_OUTCALL_LEVEL3.getParameter(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while csd stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto submitNOCLevel1( CrmQrcDto qrcDto )
    {
        LOGGER.info( "shifting workflow pojo on managerImpl is :: " + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                               CRMParameter.NOC_LEVEL1.getParameter(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while csd stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public GisDto getGISDetailsById( String stateId, String cityId, String areaId, String societyId )
    {
        GisDto dto = new GisDto();
        try
        {
            dto.setStateName( stateId );
            dto.setCityName( cityId );
            dto.setAreaName( areaId );
            dto.setSocietyName( societyId );
            LOGGER.info( dto.getStateName() + "StateId" + dto.getCityName() + "CityId" + dto.getAreaName() + "Area"
                    + dto.getSocietyName() );
            dto = getGisServiceClient().gisOperation( ServiceParameter.LIST.getParameter(),
                                                      CRMParameter.GIS_DETAILS_BY_ID.getParameter(), dto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
            dto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            dto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return dto;
    }

    public CrmQrcDto updateCustomerDetailsForMAC( QrcForm inQrcForm, String userId )
    {
        CrmQrcDto qrcDto = new CrmQrcDto();
        qrcDto.setCrmShiftingWorkflowPojo( inQrcForm.getShiftingWorkflowPojo() );
        qrcDto.setUserId( userId );
        // qrcDto.setRemarksPojo( inQrcForm.getRemarksPojo() );
        LOGGER.info( "shifting workflow pojo on updateCustomerDetailsForMAC  is :: "
                + qrcDto.getCrmShiftingWorkflowPojo() );
        try
        {
            qrcDto = getQrcServiceClient().workFlowOperations( ServiceParameter.SHIFTING.getParameter(),
                                                               CRMParameter.BIND_MAC.getParameter(), qrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while csd stage on shifting workflow :: " + ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }
}
