package com.np.tele.selfcare.action;

import java.util.List;

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

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcarePlanMigrationMgr;
import com.np.tele.selfcare.forms.SelfcareMigrationForm;
import com.np.validator.util.CommonValidator;

public class SelfcareMigrationAction
    extends DispatchAction
{
    private final Logger              LOGGER                   = Logger.getLogger( SelfcareMigrationAction.class );
    private ISelfcarePlanMigrationMgr selfcarePlanmigrationMgr = null;

    public ISelfcarePlanMigrationMgr getSelfcarePlanmigrationMgr()
    {
        return selfcarePlanmigrationMgr;
    }

    public void setSelfcarePlanmigrationMgr( ISelfcarePlanMigrationMgr selfcarePlanmigrationMgr )
    {
        this.selfcarePlanmigrationMgr = selfcarePlanmigrationMgr;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.SELFCARE_PLAN_MIGRATION_MANAGER, selfcarePlanmigrationMgr );
    }

    public ActionForward boosterTopUpPage( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside SelfcareMigrationAction, boosterTopUpPage" );
        SelfcareMigrationForm planMigrationForm = (SelfcareMigrationForm) inForm;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        CRMDisplayListConstants serviceType = null;
        CrmPlanMasterPojo planMasterPojo = null;
        List<CrmPlanMasterPojo> planMasterPojos = null;
        HttpSession session = inRequest.getSession( false );
        try
        {
            customerDetailsPojo = (CrmCustomerDetailsPojo) session.getAttribute( CrmCustomerDetailsPojo.class
                    .getSimpleName() );
            if ( StringUtils.isValidObj( customerDetailsPojo ) )
            {
                serviceType = CRMDisplayListConstants.getConstantByFilter( "PlanService",
                                                                           customerDetailsPojo.getProduct(),
                                                                           customerDetailsPojo.getServiceType() );
                planMasterPojo = new CrmPlanMasterPojo();
                planMasterPojo.setService( serviceType.getCode() );
                planMasterPojo.setPlanType( CRMDisplayListConstants.TOPUP_PLAN.getCode() );
                planMasterPojo.setPlanCategory( CRMDisplayListConstants.BOOSTER_PLAN.getCode() );
                planMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                planMasterPojo.setPrimaryQuota( 2L * ( 1024 * 1024 * 1024 ) );
                planMasterPojos = getSelfcarePlanmigrationMgr().getBoosterUageTopUps( planMasterPojo );
                if ( CommonValidator.isValidCollection( planMasterPojos ) )
                {
                    planMasterPojo = planMasterPojos.get( 0 );
                    planMigrationForm.setCrmPlanMasterPojo( planMasterPojo );
                }
                LOGGER.info( "Plan Price : " + planMasterPojo.getRentExclTax() );
                planMigrationForm.setCrmPlanMasterPojo( planMasterPojo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while loading Booster Usage Top-Up Page ", ex );
        }
        return inMapping.findForward( IActionForwardConstant.SELFCARE_BOOSTER_TOPUP_PAGE );
    }

    public ActionForward planMigrationPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside SelfcareMigrationAction, planMigrationPage" );
        SelfcareMigrationForm planMigrationForm = (SelfcareMigrationForm) inForm;
        HttpSession session = inRequest.getSession( false );
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                .getAttribute( CrmCustomerDetailsPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
        {
            planMigrationForm.setCrmPlanDetailsPojo( crmCustomerDetailsPojo.getCrmPlanDetailses().get( 0 ) );
            LOGGER.info( "base plan code:: " + planMigrationForm.getCrmPlanDetailsPojo().getBasePlanCode()
                    + " addon code:: " + planMigrationForm.getCrmPlanDetailsPojo().getAddOnPlanCode() + " recID:: "
                    + crmCustomerDetailsPojo.getRecordId() );
            MasterDataDto basePlanMasterDataDto = getSelfcarePlanmigrationMgr().getPlanInfo( planMigrationForm );
            if ( StringUtils.equals( basePlanMasterDataDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( basePlanMasterDataDto.getCrmPlanMasterPojos() ) )
                {
                    planMigrationForm.setCrmPlanMasterPojo( basePlanMasterDataDto.getCrmPlanMasterPojos().get( 0 ) );
                    LOGGER.info( "plan name :: " + planMigrationForm.getCrmPlanMasterPojo().getPlanName()
                            + " primary speed: " + planMigrationForm.getCrmPlanMasterPojo().getPrimarySpeed()
                            + " primary quota:" + planMigrationForm.getCrmPlanMasterPojo().getPrimaryQuota() );
                }
                else
                {
                    LOGGER.info( "invalid collection CrmPlanMasterPojos" );
                }
            }
            List<CrmPlanMasterPojo> unlimitedBasePlanList = getSelfcarePlanmigrationMgr()
                    .getUnlimitedBasePlanDetails( crmCustomerDetailsPojo );
            if ( CommonValidator.isValidCollection( unlimitedBasePlanList ) )
            {
                LOGGER.info( "Unlimited Base Plan list:: " + unlimitedBasePlanList.size() );
                planMigrationForm.setUnlimitedBasePlanMasterPojos( unlimitedBasePlanList );
            }
            List<CrmPlanMasterPojo> limitedBasePlanList = getSelfcarePlanmigrationMgr()
                    .getLimitedBasePlanDetails( crmCustomerDetailsPojo );
            if ( CommonValidator.isValidCollection( limitedBasePlanList ) )
            {
                LOGGER.info( "Limited Base Plan list:: " + limitedBasePlanList.size() );
                planMigrationForm.setLimitedBasePlanMasterPojos( limitedBasePlanList );
            }
            List<CrmPlanMasterPojo> addonPlanMasterList = getSelfcarePlanmigrationMgr()
                    .getAddonPlanDetails( crmCustomerDetailsPojo );
            if ( CommonValidator.isValidCollection( addonPlanMasterList ) )
            {
                LOGGER.info( "Addon Plan list:: " + addonPlanMasterList.size() );
                planMigrationForm.setCrmAddonPLMasterPojos( addonPlanMasterList );
            }
            CrmQrcDto crmQrcDto = new CrmQrcDto();
            crmQrcDto.setActivityAction( QRCRolCategories.PLAN_MIGRATION_REQUEST.getEvent() );
            crmQrcDto = getSelfcarePlanmigrationMgr().getCustomerTickets( crmQrcDto );
            if ( CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() ) )
            {
                //getSelfcarePlanmigrationMgr().setCategoriesNameById( crmQrcDto.getCrmSrTicketsPojos() );
                planMigrationForm.setSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
                LOGGER.info( "Ticket Pojo List size:: " + crmQrcDto.getCrmSrTicketsPojos().size() );
            }
            planMigrationForm.setServiceType( crmCustomerDetailsPojo.getServiceType() );
            planMigrationForm.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
        }
        return inMapping.findForward( IActionForwardConstant.SELFCARE_PLAN_MIGRATION_PAGE );
    }

    public ActionForward saveCustomerActivationPlan( final ActionMapping inMapping,
                                                     final ActionForm inForm,
                                                     final HttpServletRequest inRequest,
                                                     final HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages actionErrors = getErrors( inRequest );
        SelfcareMigrationForm planMigrationForm = (SelfcareMigrationForm) inForm;
        HttpSession session = inRequest.getSession( false );
        CrmQrcDto crmQrcDto = null;
        String forward = IActionForwardConstant.SELFCARE_PLAN_MIGRATION_PAGE;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                .getAttribute( CrmCustomerDetailsPojo.class.getSimpleName() );
        if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
        {
            if ( StringUtils.equals( planMigrationForm.getSelectedPlanCode(), planMigrationForm.getCrmPlanDetailsPojo()
                    .getBasePlanCode() )
                    && StringUtils.equals( planMigrationForm.getAddonPlanCode(), planMigrationForm
                            .getCrmPlanDetailsPojo().getAddOnPlanCode() ) )
            {
                actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM429.getStatusCode() ) );
            }
            else
            {
                crmQrcDto = getSelfcarePlanmigrationMgr().activateCustomerBasePlan( planMigrationForm,
                                                                                    crmCustomerDetailsPojo );
                LOGGER.info( "Status Code:: " + crmQrcDto.getStatusCode() );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_UPDATE_PLAN ) );
                }
                else
                {
                    actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, actionErrors );
        return inMapping.findForward( forward );
    }

    public ActionForward topUpUsageBooster( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside SelfcareMigrationAction, topUpUsageBooster" );
        SelfcareMigrationForm planMigrationForm = (SelfcareMigrationForm) inForm;
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        CrmPlanMasterPojo planMasterPojo = null;
        CrmCustAssetDetailsPojo custAssetDetailsPojo = null;
        CrmQrcDto crmQrcDto = null;
        HttpSession session = inRequest.getSession( false );
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            customerDetailsPojo = (CrmCustomerDetailsPojo) session.getAttribute( CrmCustomerDetailsPojo.class
                    .getSimpleName() );
            planMasterPojo = planMigrationForm.getCrmPlanMasterPojo();
            if ( StringUtils.isValidObj( customerDetailsPojo ) && StringUtils.isValidObj( planMasterPojo )
                    && StringUtils.isValidObj( planMasterPojo.getRentExclTax() )
                    && planMasterPojo.getRentExclTax().longValue() > 0
                    && StringUtils.isNotBlank( planMigrationForm.getTopUpUsage() ) )
            {
                byte optedUsageCountBy2gb = (byte) ( Byte.valueOf( planMigrationForm.getTopUpUsage() ) / (byte) 2 );
                custAssetDetailsPojo = new CrmCustAssetDetailsPojo();
                custAssetDetailsPojo.setCustomerRecordId( customerDetailsPojo.getRecordId() );
                custAssetDetailsPojo.setCategoryValue( planMasterPojo.getPlanCode() );
                custAssetDetailsPojo.setCategoryCount( optedUsageCountBy2gb );
                custAssetDetailsPojo.setCategoryAmount( planMasterPojo.getRentInclTax() * optedUsageCountBy2gb );
                //  .multiply( new BigDecimal( optedUsageCountBy2gb ) ) );
                custAssetDetailsPojo.setCategoryName( CRMParameter.BOOSTER_USAGE_PAID.getParameter() );
                custAssetDetailsPojo.setStatus( planMasterPojo.getStatus() );
                custAssetDetailsPojo.setCreatedBy( customerDetailsPojo.getCustomerId() );
                LOGGER.info( "CrmCustomerDetailsPojo  : " + custAssetDetailsPojo );
                crmQrcDto = getSelfcarePlanmigrationMgr().topUpUsageBooster( custAssetDetailsPojo, customerDetailsPojo );
                if ( StringUtils.equals( crmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_MESSAGE_BOOSTER_MOUNTED ) );
                    planMigrationForm.setTopUpUsage( null );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmQrcDto.getStatusCode() ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while loading Booster Usage Top-Up Page ", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.SELFCARE_BOOSTER_TOPUP_PAGE );
    }
}