package com.np.tele.selfcare.bm;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.forms.SelfcareMigrationForm;
import com.np.validator.util.CommonValidator;

public class SelfcarePlanMigrationMgrImpl
    implements ISelfcarePlanMigrationMgr
{
    private final Logger  LOGGER           = Logger.getLogger( SelfcarePlanMigrationMgrImpl.class );
    private CrmQrcService qrcServiceClient = null;
    private MasterData    masterDataClient = null;

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    public MasterData getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterData masterDataClient )
    {
        this.masterDataClient = masterDataClient;
    }

    @Override
    public MasterDataDto getPlanInfo( SelfcareMigrationForm planMigrationForm )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.BASE_PLAN.getCode() );
        crmPlanMasterPojo.setPlanCode( planMigrationForm.getCrmPlanDetailsPojo().getBasePlanCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getPlanInfo" );
            LOGGER.error( ex );
        }
        return masterDataDto;
    }

    @Override
    public List<CrmPlanMasterPojo> getUnlimitedBasePlanDetails( CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService",
                                                                                        crmCustomerDetailsPojo
                                                                                                .getProduct(),
                                                                                        crmCustomerDetailsPojo
                                                                                                .getServiceType() );
        String prepaid = ( crmCustomerDetailsPojo.getServiceType().equals( "PR" ) ? "Y" : "N" );
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.BASE_PLAN.getCode() );
        crmPlanMasterPojo.setPlanType( "PAID" );
        crmPlanMasterPojo.setPlanUsageType( "U" );
        crmPlanMasterPojo.setMigrAllowedYn( IAppConstants.Y );
        crmPlanMasterPojo.setPrepaidYn( prepaid );
        crmPlanMasterPojo.setService( constant.getCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getUnlimitedBasePlanDetails" );
            LOGGER.error( ex );
        }
        return masterDataDto.getCrmPlanMasterPojos();
    }

    @Override
    public List<CrmPlanMasterPojo> getLimitedBasePlanDetails( CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService",
                                                                                        crmCustomerDetailsPojo
                                                                                                .getProduct(),
                                                                                        crmCustomerDetailsPojo
                                                                                                .getServiceType() );
        String prepaid = ( crmCustomerDetailsPojo.getServiceType().equals( "PR" ) ? "Y" : "N" );
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.BASE_PLAN.getCode() );
        crmPlanMasterPojo.setPlanType( "PAID" );
        crmPlanMasterPojo.setPlanUsageType( "L" );
        crmPlanMasterPojo.setMigrAllowedYn( IAppConstants.Y );
        crmPlanMasterPojo.setService( constant.getCode() );
        crmPlanMasterPojo.setPrepaidYn( prepaid );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getLimitedBasePlanDetails" );
            LOGGER.error( ex );
        }
        return masterDataDto.getCrmPlanMasterPojos();
    }

    @Override
    public List<CrmPlanMasterPojo> getAddonPlanDetails( CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        CRMDisplayListConstants constant = CRMDisplayListConstants.getConstantByFilter( "PlanService",
                                                                                        crmCustomerDetailsPojo
                                                                                                .getProduct(),
                                                                                        crmCustomerDetailsPojo
                                                                                                .getServiceType() );
        MasterDataDto masterDataDto = new MasterDataDto();
        CrmPlanMasterPojo crmPlanMasterPojo = new CrmPlanMasterPojo();
        crmPlanMasterPojo.setPlanCategory( CRMDisplayListConstants.ADDON_PLAN.getCode() );
        crmPlanMasterPojo.setService( constant.getCode() );
        crmPlanMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setPlanMaster( crmPlanMasterPojo );
        try
        {
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CRMParameter.PLAN_DETAILS_MASTER.getParameter(),
                                                                    masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error getAddonPlanDetails" );
            LOGGER.error( ex );
        }
        return masterDataDto.getCrmPlanMasterPojos();
    }

    @Override
    public CrmQrcDto activateCustomerBasePlan( SelfcareMigrationForm planMigrationForm,
                                               CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        LOGGER.info( "Inside SelfcarPlanMigrationMgrImpl, activateCustomerBasePlan" );
        CrmQrcDto crmQrcDTO = null;
        try
        {
            crmQrcDTO = new CrmQrcDto();
            crmQrcDTO.setCustomerDetailsPojo( crmCustomerDetailsPojo );
            boolean allowedFlag = balanceCheckCondition( crmQrcDTO, planMigrationForm );
            if ( StringUtils.equals( crmCustomerDetailsPojo.getServiceType(),
                                     CRMDisplayListConstants.POST_PAID.getCode() ) )
            {
                allowedFlag = true;
            }
            if ( allowedFlag && !StringUtils.equals( crmQrcDTO.getStatusCode(), CRMServiceCode.CRM997.getStatusCode() ) )
            {
                LOGGER.info( "Selected Plan Code:: " + planMigrationForm.getSelectedPlanCode() );
                if ( StringUtils.equals( planMigrationForm.getSelectedPlanCode(), planMigrationForm
                        .getCrmPlanDetailsPojo().getBasePlanCode() ) )
                {
                    LOGGER.info( "Tarrif yes or no:: " + planMigrationForm.isTariffNoAddOn() );
                    if ( !planMigrationForm.isTariffNoAddOn()
                            && StringUtils.isNotEmpty( planMigrationForm.getAddonPlanCode() ) )
                    {
                        LOGGER.info( "Selected Addon Plan Code:: " + planMigrationForm.getAddonPlanCode() );
                        crmQrcDTO.setPlanCategory( "Add On Added" );
                    }
                    else
                    {
                        LOGGER.info( "Add On Removal Request" );
                        crmQrcDTO.setPlanCategory( "Add On Removal" );
                    }
                }
                else
                {
                    crmQrcDTO.setPlanCategory( "Base Plan Change" );
                    LOGGER.info( "Base Plan Change Request" );
                }
                if ( CommonValidator.isValidCollection( planMigrationForm.getSrTicketsPojos() ) )
                {
                    planMigrationForm.getSrTicketsPojos().get( 0 ).setEditable( true );
                    crmQrcDTO.getCrmSrTicketsPojos().addAll( planMigrationForm.getSrTicketsPojos() );
                }
                LOGGER.info( "Activation Time:: " + planMigrationForm.getActivationType() );
                crmQrcDTO.setActivationTime( planMigrationForm.getActivationType() );
                crmQrcDTO.setCrmPlanDetailsPojo( planMigrationForm.getCrmPlanDetailsPojo() );
                crmQrcDTO.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                crmQrcDTO.setActivityAction( QRCRolCategories.PLAN_MIGRATION_REQUEST.getEvent() );
                crmQrcDTO.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                crmQrcDTO.setUserId( crmCustomerDetailsPojo.getCustomerId() );
                crmQrcDTO = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                                 CRMParameter.PLAN_MIGRATION.getParameter(), crmQrcDTO );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while activating customer base plan ", ex );
            crmQrcDTO.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDTO.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDTO;
    }

    @Override
    public List<CrmPlanMasterPojo> getBoosterUageTopUps( CrmPlanMasterPojo inPlanMasterPojo )
    {
        LOGGER.info( "Inside SelfcarePlanMigrationMgrImpl, getBoosterUageTopUps" );
        List<CrmPlanMasterPojo> planMasterPojos = null;
        MasterDataDto masterDataDto = null;
        try
        {
            if ( StringUtils.isValidObj( inPlanMasterPojo ) )
            {
                masterDataDto = new MasterDataDto();
                masterDataDto.setPlanMaster( inPlanMasterPojo );
                masterDataDto = getMasterDataClient()
                        .masterOperations( ServiceParameter.LIST.getParameter(),
                                           CRMParameter.PLAN_DETAILS_MASTER.getParameter(), masterDataDto );
                if ( StringUtils.equals( masterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    planMasterPojos = new ArrayList<CrmPlanMasterPojo>();
                    for ( CrmPlanMasterPojo crmPlanMasterPojo : masterDataDto.getCrmPlanMasterPojos() )
                    {
                        if ( crmPlanMasterPojo.getRentInclTax().longValue() > 0 )
                        {
                            crmPlanMasterPojo
                                    .setPrimaryQuota( crmPlanMasterPojo.getPrimaryQuota() / 1024 * 1024 * 1024 );
                            //.divide( BigDecimal.valueOf( 1024 * 1024 * 1024 ), RoundingMode.CEILING ) );
                            planMasterPojos.add( crmPlanMasterPojo );
                        }
                    }
                    LOGGER.info( "Plan Master Pojos Size : " + planMasterPojos.size() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching Booster Usage Top Ups ", ex );
        }
        return planMasterPojos;
    }

    @Override
    public CrmQrcDto topUpUsageBooster( CrmCustAssetDetailsPojo inCustAssetDetailsPojo,
                                        CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "Inside QrcManagerImpl, mountBoosterPlan" );
        CrmQrcDto qrcDto = null;
        List<CrmCustAssetDetailsPojo> custAssetDetailsPojos = null;
        try
        {
            qrcDto = new CrmQrcDto();
            qrcDto.setCustomerDetailsPojo( inCustomerDetailsPojo );
            if ( StringUtils.isValidObj( inCustAssetDetailsPojo ) )
            {
                custAssetDetailsPojos = new ArrayList<CrmCustAssetDetailsPojo>();
                custAssetDetailsPojos.add( inCustAssetDetailsPojo );
                qrcDto.getCrmCustAssetDetailsPojos().addAll( custAssetDetailsPojos );
                qrcDto.setUserId( inCustomerDetailsPojo.getCustomerId() );
                boolean allowedFlag = balanceCheckCondition( qrcDto, null );
                if ( StringUtils.equals( inCustomerDetailsPojo.getServiceType(),
                                         CRMDisplayListConstants.POST_PAID.getCode() ) )
                {
                    allowedFlag = true;
                }
                if ( allowedFlag )
                {
                    qrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.ACTIVATE.getParameter(),
                                                                  CRMParameter.PLAN_BOOSTER.getParameter(), qrcDto );
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Exception while Mounting Booster Plan ", ex );
            qrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            qrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return qrcDto;
    }

    @Override
    public CrmQrcDto getMigrationPolicy( CrmQrcDto crmQrcDto )
    {
        try
        {
            crmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.PROCESS.getParameter(),
                                                             CRMParameter.PLAN_MIGRATION_POLICY.getParameter(),
                                                             crmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "exception getMigrationPolicy:: " + ex );
            crmQrcDto.setStatusCode( CRMServiceCode.CRM998.getStatusCode() );
            crmQrcDto.setStatusDesc( CRMServiceCode.CRM998.getStatusDesc() );
        }
        return crmQrcDto;
    }

    @Override
    public CrmQrcDto getCustomerTickets( CrmQrcDto inCrmQrcDto )
    {
        try
        {
            inCrmQrcDto = getQrcServiceClient()
                    .qrcOperations( ServiceParameter.VIEW.getParameter(), CRMParameter.SERVICE_REQUEST.getParameter(),
                                    inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( ex );
        }
        return inCrmQrcDto;
    }

    @Override
    public void setCategoriesNameById( List<CrmSrTicketsPojo> crmSrTicketsPojos )
    {
        // TODO Auto-generated method stub
    }

    private boolean balanceCheckCondition( CrmQrcDto inCrmQrcDto, SelfcareMigrationForm inSelfcareMigrationForm )
    {
        LOGGER.info( "Inside SelfcarePlanMigrationMgrImpl, -balanceCheckCondition" );
        CrmPlanMasterPojo crmPlanMasterPojo = null;
        CrmPlanMasterPojo crmAddonPlanMasterPojo = null;
        Double rentInclTax = 0D;
        try
        {
            if ( StringUtils.isValidObj( inSelfcareMigrationForm ) )
            {
                if ( CommonValidator.isValidCollection( inSelfcareMigrationForm.getUnlimitedBasePlanMasterPojos() )
                        && StringUtils.isNotBlank( inSelfcareMigrationForm.getSelectedPlanCode() ) )
                {
                    LOGGER.info( "Selected Plan Code:: " + inSelfcareMigrationForm.getSelectedPlanCode() );
                    crmPlanMasterPojo = new CrmPlanMasterPojo();
                    crmPlanMasterPojo.setPlanCode( inSelfcareMigrationForm.getSelectedPlanCode() );
                    int pojoIndex = inSelfcareMigrationForm.getUnlimitedBasePlanMasterPojos()
                            .indexOf( crmPlanMasterPojo );
                    if ( pojoIndex >= 0 )
                    {
                        crmPlanMasterPojo = inSelfcareMigrationForm.getUnlimitedBasePlanMasterPojos().get( pojoIndex );
                        inCrmQrcDto.setPlanMasterPojo( crmPlanMasterPojo );
                    }
                    else
                    {
                        pojoIndex = inSelfcareMigrationForm.getLimitedBasePlanMasterPojos().indexOf( crmPlanMasterPojo );
                        if ( pojoIndex >= 0 )
                        {
                            crmPlanMasterPojo = inSelfcareMigrationForm.getLimitedBasePlanMasterPojos().get( pojoIndex );
                            inCrmQrcDto.setPlanMasterPojo( crmPlanMasterPojo );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( inSelfcareMigrationForm.getCrmAddonPLMasterPojos() )
                        && StringUtils.isNotBlank( inSelfcareMigrationForm.getAddonPlanCode() ) )
                {
                    LOGGER.info( "Selected Addon Plan Code:: " + inSelfcareMigrationForm.getAddonPlanCode() );
                    crmAddonPlanMasterPojo = new CrmPlanMasterPojo();
                    crmAddonPlanMasterPojo.setPlanCode( inSelfcareMigrationForm.getAddonPlanCode() );
                    int pojoIndex = inSelfcareMigrationForm.getCrmAddonPLMasterPojos().indexOf( crmAddonPlanMasterPojo );
                    if ( pojoIndex >= 0 )
                    {
                        crmAddonPlanMasterPojo = inSelfcareMigrationForm.getCrmAddonPLMasterPojos().get( pojoIndex );
                        inCrmQrcDto.setAddOnPlanPojo( crmAddonPlanMasterPojo );
                    }
                }
                if ( StringUtils.isValidObj( crmPlanMasterPojo ) )
                {
                    rentInclTax = crmPlanMasterPojo.getRentInclTax().doubleValue();
                    if ( StringUtils.isValidObj( crmAddonPlanMasterPojo )
                            && StringUtils.isValidObj( crmAddonPlanMasterPojo.getRentInclTax() ) )
                    {
                        rentInclTax = rentInclTax + crmAddonPlanMasterPojo.getRentInclTax().doubleValue();
                    }
                }
            }
            else if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() ) )
            {
                CrmCustAssetDetailsPojo custAssetDetailsPojo = inCrmQrcDto.getCrmCustAssetDetailsPojos().get( 0 );
                rentInclTax = custAssetDetailsPojo.getCategoryCount()
                        * custAssetDetailsPojo.getCategoryAmount().doubleValue();
            }
            if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
            {
                inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.VIEW.getParameter(),
                                                                   CRMParameter.CUST_ADDITIONAL_DETAILS.getParameter(),
                                                                   inCrmQrcDto );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() )
                        && StringUtils.isValidObj( inCrmQrcDto.getCustAdditionalDetails() ) )
                {
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                             CRMDisplayListConstants.PRE_PAID.getCode() ) )
                    {
                        inCrmQrcDto.setExpiryDate( inCrmQrcDto.getCustAdditionalDetails().getExpiryDate() );
                    }
                    if ( StringUtils.isValidObj( crmPlanMasterPojo.getRentInclTax() )
                            && StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                                   CRMDisplayListConstants.PRE_PAID.getCode() ) )
                    {
                        LOGGER.info( "Calculated Rent : " + rentInclTax );
                        //Pre-paid - Allowed only if Customer wallet Balance is equal or greater than selected plan rental charges
                        if ( inCrmQrcDto.getCustAdditionalDetails().getBalance() < ( rentInclTax - 1 ) )
                        {
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM427.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM427.getStatusDesc() );
                            return false;
                        }
                    }
                    else if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getServiceType(),
                                                  CRMDisplayListConstants.POST_PAID.getCode() ) )
                    {
                        //Post-paid - Allowed only if Customer has no outstanding
                        if ( inCrmQrcDto.getCustAdditionalDetails().getBalance() > 0 )
                        {
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM428.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM428.getStatusDesc() );
                            return false;
                        }
                    }
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
            //return false;
        }
        return true;
    }
}
