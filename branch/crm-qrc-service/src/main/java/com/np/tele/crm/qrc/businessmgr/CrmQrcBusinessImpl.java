package com.np.tele.crm.qrc.businessmgr;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.jboss.logging.MDC;

import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ext.pojos.CustomerUsageDetailsPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.massoutage.dao.IMassOutageDao;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmInvoiceDetailsPojo;
import com.np.tele.crm.pojos.CrmMassOutagePartnerPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.CrmNpMobilePojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.qrc.dao.IBillingDataDao;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.qrc.profile.dao.ICustomerProfileDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ExcelWriteUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.crm.workflow.dao.IWorkFlowDao;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class CrmQrcBusinessImpl
    implements ICrmQrcBusiness
{
    private static final Logger LOGGER             = Logger.getLogger( CrmQrcBusinessImpl.class );
    private IQrcManagerDao      qrcManagerDao      = null;
    private ICustomerProfileDao customerProfileDao = null;
    private IWorkFlowDao        workFlowDao        = null;
    private IConfigManagerDao   configManagerDao   = null;
    private IMassOutageDao      massOutageDao      = null;
    private IBillingDataDao     billingDao         = null;

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao inQrcManagerDao )
    {
        qrcManagerDao = inQrcManagerDao;
    }

    public ICustomerProfileDao getCustomerProfileDao()
    {
        return customerProfileDao;
    }

    public void setCustomerProfileDao( ICustomerProfileDao customerProfileDao )
    {
        this.customerProfileDao = customerProfileDao;
    }

    public IWorkFlowDao getWorkFlowDao()
    {
        return workFlowDao;
    }

    public void setWorkFlowDao( IWorkFlowDao workFlowDao )
    {
        this.workFlowDao = workFlowDao;
    }

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
    }

    public IMassOutageDao getMassOutageDao()
    {
        return massOutageDao;
    }

    public void setMassOutageDao( IMassOutageDao inMassOutageDao )
    {
        massOutageDao = inMassOutageDao;
    }

    public IBillingDataDao getBillingDao()
    {
        return billingDao;
    }

    public void setBillingDao( IBillingDataDao inBillingDao )
    {
        billingDao = inBillingDao;
    }

    @Override
    public CrmQrcDto qrcOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method QRC-Operations" + ":CRM PARAM::" + inCrmParam
                + ":Service PARAM::" + inServiceParam );
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        if ( StringUtils.isNotBlank( inServiceParam ) && StringUtils.isNotBlank( inCrmParam )
                && StringUtils.isValidObj( inCrmQrcDto ) )
        {
            MDC.put( "KEY", "SERVICEPARAM:" + inServiceParam + ", CRMPARAM:" + inCrmParam );
            if ( StringUtils.equals( ServiceParameter.APICALL.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.USAGE_DETAILS.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getCustomerPriodicUsageDetails( inCrmQrcDto );
                }
                if ( StringUtils.equals( CRMParameter.SEND_USAGE_DETAILS.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = sendCustomerUsageDetails( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.TRACK.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.QRCCATEGORY.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().getQrcCategories( inCrmQrcDto );
                }
                else if ( StringUtils.equals( CRMParameter.QRCSUBCATEGORY.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().getQrcSubCategories( inCrmQrcDto );
                }
                else if ( StringUtils.equals( CRMParameter.QRCSUBSUBCATEGORY.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().getQrcSubSubCategories( inCrmQrcDto );
                }
                else if ( StringUtils.equals( CRMParameter.CUSTOMER_FEEDBACK.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().getCustomerFeedback( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.POST.getParameter(), inServiceParam ) )
            {
                // if ( StringUtils.equals(
                // CRMParameter.SERVICE_REQUEST.getParameter(), inCrmParam ) )
                // {
                // if ( StringUtils.isValidObj(
                // inCrmQrcDto.getCrmSrTicketsPojo() ) )
                // {
                // if ( inCrmQrcDto.getCrmSrTicketsPojo().getQrcCategoryId() > 0
                // )
                // {
                // inCrmQrcDto = postServiceRequest( inCrmQrcDto );
                // }
                // else if ( StringUtils.isNotEmpty(
                // inCrmQrcDto.getCrmSrTicketsPojo().getQrcCategory() ) )
                // {
                // getQrcManagerDao().preparedQrcTicketPojo(
                // inCrmQrcDto.getCrmSrTicketsPojo() );
                // inCrmQrcDto = postServiceRequest( inCrmQrcDto );
                // }
                // }
                // }
                // else
                if ( StringUtils.equals( CRMParameter.SELFCARE_SERVICE_REQUEST.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSelfcareCategoriesPojo() ) )
                    {
                        inCrmQrcDto = postSelfcareServiceRequest( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.WAIVER.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = validateWaiverData( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.CREATE.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.CUSTOMER_INTERACTION.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCustInteractionsPojo() ) )
                    {
                        inCrmQrcDto = createCustomerInteraction( inCrmQrcDto );
                    }
                }
            }
            else if ( StringUtils.equals( ServiceParameter.VIEW.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.SERVICE_REQUEST.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().viewCustomerTickets( inCrmQrcDto );
                    }
                    else if ( StringUtils.isNotEmpty( inCrmQrcDto.getActivityAction() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().viewCustomerTickets( inCrmQrcDto );
                    }
                }
                // else if ( StringUtils.equals(
                // CRMParameter.TICKET_OTHER_DETAILS.getParameter(), inCrmParam
                // ) )
                // {
                // inCrmQrcDto = getQrcManagerDao().getTicketOtherDetails(
                // inCrmQrcDto );
                // }
                else if ( StringUtils.equals( CRMParameter.CUST_ADDITIONAL_DETAILS.getParameter(), inCrmParam ) )
                {
                    getAdditionalDetails( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.LIST.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.VAS.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmCustAssetDetailsPojo() ) )
                    {
                        inCrmQrcDto = fetchActivatedVAS( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.WHITELIST.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcWhitelistPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().getWhiteListCustomers( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.DEPENDENT_FUNCTION_BINS.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
                    {
                        inCrmQrcDto = secondLevelAndFurtherBins( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.QRC_RCA_LIST.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
                    {
                        inCrmQrcDto = qrcRcaList( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.QRC_RCA_REASONS.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getQrcActionTakenPojo() ) )
                    {
                        inCrmQrcDto = qrcRcaReasonList( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.CUSTOMER_INTERACTION.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto ) )
                    {
                        inCrmQrcDto = searchCustomerInteraction( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.WAIVER.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().searchCustomerWaiverList( inCrmQrcDto );
                }
                else if ( StringUtils.equals( inCrmParam, CRMParameter.STATIC_IP.getParameter() )
                        || StringUtils.equals( inCrmParam, CRMParameter.WIRING.getParameter() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().searchAccessories( inCrmQrcDto, inCrmParam );
                }
                else if ( StringUtils.equals( inCrmParam, CrmInvoiceDetailsPojo.class.getSimpleName() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().getBillingDetails( inCrmQrcDto );
                    /* if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                     {
                         inCrmQrcDto = getQrcManagerDao().setBillPdfPaths( inCrmQrcDto );
                     }*/
                }
                else if ( StringUtils.equals( inCrmParam, CrmSelfcareCategoriesPojo.class.getSimpleName() ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSelfcareCategoriesPojo() )
                            && StringUtils.isNotBlank( inCrmQrcDto.getCrmSelfcareCategoriesPojo().getSubject() ) )
                    {
                        inCrmQrcDto = getSelfcareCategories( inCrmQrcDto );
                    }
                    else
                    {
                        inCrmQrcDto = getSelfcareSubjects( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( inCrmParam, CrmSrTicketsPojo.class.getSimpleName() ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().ticketIDProfileSearch( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( inCrmParam, CrmBillingPlanRequestPojo.class.getSimpleName() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().getBillingPlanRequestList( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.SAVE.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.WHITELIST.getParameter(), inCrmParam )
                        && StringUtils.isValidObj( inCrmQrcDto.getCrmQrcWhitelistPojo() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().addUpdateRemoveWhiteList( inCrmQrcDto );
                }
                else if ( StringUtils.equals( CRMParameter.BULK_WHITELIST.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().bulkWhiteList( inCrmQrcDto );
                }
                else if ( StringUtils.equals( CRMParameter.STATIC_IP.getParameter(), inCrmParam )
                        || StringUtils.equals( CRMParameter.WIRING.getParameter(), inCrmParam )
                        || StringUtils.equals( CRMParameter.STATIC_IP_CHARGES.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().saveAccessories( inCrmQrcDto, inCrmParam );
                }
                else if ( StringUtils.equals( CRMParameter.CUSTOMER_FEEDBACK.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().saveCustomerFeedback( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.PROCESS.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.BARRING_UNBARRING.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmQrcCommonPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().barringUnbarringCustService( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.VAS.getParameter(), inCrmParam ) )
                {
                    if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() )
                            && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().activateDeactivateVAS( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.PLAN_MIGRATION_POLICY.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isNotEmpty( inCrmQrcDto.getCustomerId() ) )
                    {
                        CrmBillingDto billingDto = getQrcManagerDao()
                                .getAdditionalDetails( inCrmQrcDto.getCustomerId(), inCrmQrcDto.getServiceType() );
                        if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() )
                                && StringUtils.equals( billingDto.getStatusCode(),
                                                       CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            inCrmQrcDto.setCustAdditionalDetails( billingDto.getCustAdditionalDetails() );
                            inCrmQrcDto.setStatusCode( billingDto.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( billingDto.getStatusDesc() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            if ( StringUtils
                                    .equals( CRMServiceCode.CRM001.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
                            {
                                if ( StringUtils.equals( inCrmQrcDto.getServiceType(),
                                                         CRMDisplayListConstants.POST_PAID.getCode() ) )
                                {
                                    inCrmQrcDto = getQrcManagerDao().getPOPlanMigrationPolicy( inCrmQrcDto );
                                }
                                else
                                {
                                    inCrmQrcDto = getQrcManagerDao().getPRPlanMigrationPolicy( inCrmQrcDto );
                                }
                            }
                        }
                        else
                        {
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM517.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM517.getStatusDesc() );
                        }
                    }
                }
                else if ( StringUtils.equals( CRMParameter.ADDON_PLAN_MIGRATION_POLICY.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isNotEmpty( inCrmQrcDto.getCustomerId() ) )
                    {
                        CrmBillingDto billingDto = getQrcManagerDao()
                                .getAdditionalDetails( inCrmQrcDto.getCustomerId(), inCrmQrcDto.getServiceType() );
                        if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() )
                                && StringUtils.equals( billingDto.getStatusCode(),
                                                       CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            inCrmQrcDto.setCustAdditionalDetails( billingDto.getCustAdditionalDetails() );
                            inCrmQrcDto.setStatusCode( billingDto.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( billingDto.getStatusDesc() );
                            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
                            if ( StringUtils
                                    .equals( CRMServiceCode.CRM001.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
                            {
                                if ( StringUtils.equals( inCrmQrcDto.getServiceType(),
                                                         CRMDisplayListConstants.POST_PAID.getCode() ) )
                                {
                                    inCrmQrcDto = getQrcManagerDao().getPOAddOnPlanMigrationPolicy( inCrmQrcDto );
                                }
                                else
                                {
                                    inCrmQrcDto = getQrcManagerDao().getPRAddOnPlanMigrationPolicy( inCrmQrcDto );
                                }
                            }
                        }
                        else
                        {
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM517.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM517.getStatusDesc() );
                        }
                    }
                }
            }
            else if ( StringUtils.equals( ServiceParameter.OUTSTANDING_NOTICE.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.SEND_LEGAL_MAIL.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = sendLegalMail( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.RESET.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.RESET_PASSWORD.getParameter(), inCrmParam ) )
                {
                    inCrmQrcDto = getQrcManagerDao().resetMyAccountPassword( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.UPDATE.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.CHANGE_STATUS.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().updateCustomerStatus( inCrmQrcDto );
                    }
                    else
                    {
                        inCrmQrcDto = getQrcManagerDao().bulkUpdateCustomerStatus( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( inCrmParam, CrmBillingPlanRequestPojo.class.getSimpleName() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().updateBillingPlanRequest( inCrmQrcDto );
                }
                else if ( StringUtils.equals( inCrmParam, CrmPlanDetailsPojo.class.getSimpleName() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().updatePlansFromBilling( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.MODIFY.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.MODIFY_ADDRESS.getParameter(), inCrmParam )
                        && StringUtils.isValidObj( inCrmQrcDto.getCrmAddressDetailsPojo() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().modifyInstallationAddresses( inCrmQrcDto );
                }
                else if ( StringUtils.equals( CRMParameter.DEVICE_AND_NETWORK_DETAILS.getParameter(), inCrmParam )
                        && ( StringUtils.isValidObj( inCrmQrcDto.getNetworkConfigurationsPojo() ) || StringUtils
                                .isValidObj( inCrmQrcDto.getOrderDetailsPojo() ) ) )
                {
                    boolean isValid = validateDeviceAndNetworkData( inCrmQrcDto );
                    if ( isValid )
                    {
                        inCrmQrcDto = getQrcManagerDao().modifyDeviceAndNetworkDetails( inCrmQrcDto );
                    }
                    else
                    {
                        if ( StringUtils.isEmpty( inCrmQrcDto.getStatusCode() ) )
                        {
                            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
                            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
                        }
                    }
                }
            }
            else if ( StringUtils.equals( ServiceParameter.ACTIVATE.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.PLAN_MIGRATION.getParameter(), inCrmParam ) )
                {
                    if ( ( StringUtils.isValidObj( inCrmQrcDto.getCrmPlanDetailsPojo() )
                            || StringUtils.isValidObj( inCrmQrcDto.getPlanMasterPojo() ) || StringUtils
                                .isValidObj( inCrmQrcDto.getAddOnPlanPojo() ) )
                            && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().activateCustomerBasePlan( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.PLAN_BOOSTER.getParameter(), inCrmParam ) )
                {
                    if ( CommonValidator.isValidCollection( inCrmQrcDto.getCrmCustAssetDetailsPojos() )
                            && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().mountBoosterPlan( inCrmQrcDto );
                    }
                    else
                    {
                        LOGGER.info( "Bulk upload for mount booster" );
                        inCrmQrcDto = getQrcManagerDao().bulkMountBoosterPlan( inCrmQrcDto );
                    }
                }
                else if ( StringUtils.equals( CRMParameter.PLAN_REACTIVATION.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmPlanDetailsPojo() )
                            && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                    {
                        inCrmQrcDto = getQrcManagerDao().customerPlanReactivation( inCrmQrcDto );
                    }
                }
            }
            else if ( StringUtils.equals( ServiceParameter.RENEW.getParameter(), inServiceParam )
                    && StringUtils.equals( CRMParameter.PLAN_RENEWAL.getParameter(), inCrmParam ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getCrmPlanDetailsPojo() )
                        && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) )
                {
                    inCrmQrcDto = getQrcManagerDao().customerPlanRenewal( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( ServiceParameter.CANCEL.getParameter(), inServiceParam ) )
            {
                if ( StringUtils.equals( CRMParameter.PLAN_MIGRATION.getParameter(), inCrmParam ) )
                {
                    if ( StringUtils.isValidObj( inCrmQrcDto )
                            && CommonValidator.isValidCollection( inCrmQrcDto.getCrmSrTicketsPojos() )
                            && StringUtils.isValidObj( inCrmQrcDto.getRemarksPojo() )
                            && StringUtils.isNotBlank( inCrmQrcDto.getRemarksPojo().getRemarks() ) )
                    {
                        LOGGER.info( "in qrcbusiness :: cancel plan migration" );
                        inCrmQrcDto = getQrcManagerDao().cancelPlanMigrationRequest( inCrmQrcDto );
                    }
                }
            }
        }
        if ( StringUtils.isBlank( inCrmQrcDto.getStatusCode() ) )
        {
            inCrmQrcDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    public static void main( String[] args )
    {
        System.out.println( System.getProperty( "user.dir" ) );
    }

    private CrmQrcDto sendCustomerUsageDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl, sendCustomerUsageDetails" );
        CRMEvents event = null;
        String filePath = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM997;
        inCrmQrcDto = getCustomerPriodicUsageDetails( inCrmQrcDto );
        try
        {
            if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                crmServiceCode = CRMServiceCode.CRM999;
                String usageType = inCrmQrcDto.getUsageType();
                CRMCustomerActivityActions activityAction = null;
                if ( StringUtils.equals( usageType, IAppConstants.UNBILLED ) )
                {
                    filePath = PropertyUtils.getServiceDetails( IPropertiesConstant.UNBILLED_USAGE_PATH ) + usageType;
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getBrand(),
                                             IAppConstants.BRAND_INITIA ) )
                    {
                        event = CRMEvents.SEND_UNBILLED_USAGE_INITIA;
                    }
                    else
                    {
                        event = CRMEvents.SEND_UNBILLED_USAGE;
                    }
                    activityAction = CRMCustomerActivityActions.UNBILLED_USAGE_SENT;
                }
                else if ( StringUtils.equals( usageType, IAppConstants.BILLED ) )
                {
                    filePath = PropertyUtils.getServiceDetails( IPropertiesConstant.BILLED_USAGE_PATH ) + usageType;
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getBrand(),
                                             IAppConstants.BRAND_INITIA ) )
                    {
                        event = CRMEvents.SEND_BILLED_USAGE_INITIA;
                    }
                    else
                    {
                        event = CRMEvents.SEND_BILLED_USAGE;
                    }
                    activityAction = CRMCustomerActivityActions.BILLED_USAGE_SENT;
                }
                String customerName = prepareCustomerFullName( inCrmQrcDto.getCustomerDetailsPojo() );
                ExcelWriteUtils excelWriteUtils = new ExcelWriteUtils( customerName, inCrmQrcDto
                        .getCustomerDetailsPojo().getCustomerId(), filePath );
                if ( CommonValidator.isValidCollection( inCrmQrcDto.getCustomerUsageDetailsPojos() ) )
                {
                    /*SortingComparator<CustomerUsageDetailsPojo> sorter = new SortingComparator<CustomerUsageDetailsPojo>( "callEndDate" );
                    Collections.sort( inCrmQrcDto.getCustomerUsageDetailsPojos(), Collections.reverseOrder( sorter ) );*/
                    excelWriteUtils.prepareTopRows( usageType, inCrmQrcDto.getUsageFormDate(),
                                                    inCrmQrcDto.getUsageToDate() );
                    for ( CustomerUsageDetailsPojo usageDetailsPojo : inCrmQrcDto.getCustomerUsageDetailsPojos() )
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
                if ( CommonValidator.isValidCollection( inCrmQrcDto.getCustUsageDetailsPojos() ) )
                {
                    SortingComparator<CustomerUsageDetailsPojo> sorter = new SortingComparator<CustomerUsageDetailsPojo>( "endTime" );
                    Collections.sort( inCrmQrcDto.getCustUsageDetailsPojos(), Collections.reverseOrder( sorter ) );
                    excelWriteUtils.prepareTopRowsForGB( usageType, inCrmQrcDto.getUsageFormDate(),
                                                         inCrmQrcDto.getUsageToDate() );
                    for ( CustomerUsageDetailsPojo usageDetailsPojo : inCrmQrcDto.getCustUsageDetailsPojos() )
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
                excelWriteUtils.createExcel();
                if ( StringUtils.isValidObj( excelWriteUtils.getCreatedExcel() ) )
                {
                    LOGGER.info( "File created : " + excelWriteUtils.getFileName() );
                    List<String> attachments = new ArrayList<String>();
                    String invoicePath = excelWriteUtils.getCreatedExcel().getAbsolutePath();
                    attachments.add( invoicePath );
                    boolean isSent = CRMServiceUtils.sendAlerts( event, CRMRequestType.CUSTOMER_ID, inCrmQrcDto
                            .getCustomerDetailsPojo().getCustomerId(), attachments );
                    if ( isSent )
                    {
                        crmServiceCode = CRMServiceCode.CRM001;
                        /**
                         * Customer Activity Log
                         */
                        long customerActivityID = CRMServiceUtils.createActivityLog( inCrmQrcDto.getUsageFormDate(),
                                                                                     inCrmQrcDto.getUsageToDate(),
                                                                                     inCrmQrcDto
                                                                                             .getCustomerDetailsPojo()
                                                                                             .getCustomerId(),
                                                                                     inCrmQrcDto.getUserId(), null,
                                                                                     activityAction.getActionDesc(),
                                                                                     null, inCrmQrcDto
                                                                                             .getClientIPAddress(),
                                                                                     inCrmQrcDto.getServerIPAddress() );
                        LOGGER.info( "Customer activity generated successfully for sent usage details. Activity ID : "
                                + customerActivityID );
                        /*
                         * Going to save system audit log
                         */
                        // boolean isAuditLogged = CRMServiceUtils
                        // .createAuditLog( new StringBuilder(
                        // IAppConstants.NOTAPPLICABLE )
                        // .append( IAppConstants.COMMA ),
                        // new StringBuilder( IAppConstants.NOTAPPLICABLE )
                        // .append( IAppConstants.COMMA ), inCrmQrcDto
                        // .getCustomerDetailsPojo().getCustomerId(),
                        // inCrmQrcDto
                        // .getUserId(), "Sent Customer Usage Details",
                        // CRMServiceUtils.getEventId( event.getEventName() ),
                        // CRMRequestType.QRC
                        // .getRequestCode(), inCrmQrcDto.getClientIPAddress(),
                        // inCrmQrcDto.getServerIPAddress() );
                        // if ( isAuditLogged )
                        // {
                        // LOGGER.info(
                        // "Audit log inserted successfully for send unbilled usage details "
                        // +
                        // inCrmQrcDto.getCustomerDetailsPojo().getCustomerId()
                        // );
                        // }
                        // boolean isAuditLogged = CRMServiceUtils
                        // .createAuditLog( new StringBuilder(
                        // IAppConstants.NOTAPPLICABLE )
                        // .append( IAppConstants.COMMA ),
                        // new StringBuilder( IAppConstants.NOTAPPLICABLE )
                        // .append( IAppConstants.COMMA ), inCrmQrcDto
                        // .getCustomerDetailsPojo().getCustomerId(),
                        // inCrmQrcDto
                        // .getUserId(), "Sent Customer Usage Details",
                        // CRMServiceUtils.getEventId( event.getEventName() ),
                        // CRMRequestType.QRC
                        // .getRequestCode(), inCrmQrcDto.getClientIPAddress(),
                        // inCrmQrcDto.getServerIPAddress() );
                        // if ( isAuditLogged )
                        // {
                        // LOGGER.info(
                        // "Audit log inserted successfully for send unbilled usage details "
                        // +
                        // inCrmQrcDto.getCustomerDetailsPojo().getCustomerId()
                        // );
                        // }
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM529;
                    inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                }
            }
        }
        catch ( Exception ex )
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Exception while sending unbilled usage details mail ", ex );
        }
        finally
        {
            if ( StringUtils.isBlank( inCrmQrcDto.getStatusCode() ) )
            {
                inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            }
        }
        return inCrmQrcDto;
    }

    private String prepareCustomerFullName( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl, -prepareCustomerFullName" );
        String fullName = null;
        if ( StringUtils.equals( CRMDisplayListConstants.INDIVIDUAL.getCode(),
                                 inCustomerDetailsPojo.getConnectionType() ) )
        {
            /* if ( StringUtils.equals( "M", inCustomerDetailsPojo.getCustGender() ) )
             {
                 initial = "Mr.";
             }
             else
             {
                 initial = "Miss";
             }*/
            fullName = inCustomerDetailsPojo.getCustFname()
                    + ( StringUtils.isNotBlank( inCustomerDetailsPojo.getCustLname() )
                                                                                      ? ( IAppConstants.SPACE + inCustomerDetailsPojo
                                                                                              .getCustLname() )
                                                                                      : IAppConstants.SPACE );
        }
        else
        {
            fullName = inCustomerDetailsPojo.getCustFname();
        }
        return fullName;
    }

    private CrmQrcDto getCustomerPriodicUsageDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl, getCustomerPriodicUsageDetails" );
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        if ( StringUtils.isNotBlank( inCrmQrcDto.getUsageFormDate() )
                && StringUtils.isNotBlank( inCrmQrcDto.getUsageToDate() )
                && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() ) && validateUsagePeriod( inCrmQrcDto ) )
        {
            LOGGER.info( "Form : " + inCrmQrcDto.getUsageFormDate() + " To : " + inCrmQrcDto.getUsageToDate() );
            inCrmQrcDto.setCustomerUsageDetailsPojos( new ArrayList<CustomerUsageDetailsPojo>() );
            inCrmQrcDto = getBillingDao().getCustUsageDetailsInGB( inCrmQrcDto );
            if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                CRMServiceCode serviceCode = getBillingDao()
                        .getCustUsageDetails( inCrmQrcDto.getUsageFormDate(), inCrmQrcDto.getUsageToDate(),
                                              inCrmQrcDto.getCustomerDetailsPojo().getCustomerId(),
                                              inCrmQrcDto.getCustomerUsageDetailsPojos() );
                inCrmQrcDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmQrcDto.setStatusDesc( serviceCode.getStatusDesc() );
                if ( StringUtils.equals( inCrmQrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "Usage Details Size : " + inCrmQrcDto.getCustomerUsageDetailsPojos().size() );
                }
            }
        }
        else
        {
            invalidRequest( inCrmQrcDto );
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    private boolean validateUsagePeriod( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl, validateUsagePeriod" );
        boolean validDates = true;
        Date fromDate = null;
        Date toDate = null;
        Date currDate = null;
        if ( StringUtils.contains( inCrmQrcDto.getUsageFormDate(), "/" ) )
        {
            fromDate = DateUtils.getDate( inCrmQrcDto.getUsageFormDate(), "dd/MM/yyyy" ).getTime();
            toDate = DateUtils.getDate( inCrmQrcDto.getUsageToDate(), "dd/MM/yyyy" ).getTime();
            inCrmQrcDto.setUsageFormDate( DateUtils.getFormattedDate( fromDate, IDateConstants.SDF_DD_MMM_YYYY ) );
            inCrmQrcDto.setUsageToDate( DateUtils.getFormattedDate( toDate, IDateConstants.SDF_DD_MMM_YYYY ) );
        }
        else
        {
            fromDate = DateUtils.getDate( inCrmQrcDto.getUsageFormDate(), "dd-MMM-yyyy" ).getTime();
            toDate = DateUtils.getDate( inCrmQrcDto.getUsageToDate(), "dd-MMM-yyyy" ).getTime();
        }
        LOGGER.info( "fromDate :" + fromDate + ",toDate" + toDate );
        currDate = DateUtils.getCurrentDate();
        if ( DateUtils.daysDiff( fromDate, currDate ) < 0 )
        {
            validDates = false;
            LOGGER.info( "fromDate daysDiff" );
        }
        else if ( DateUtils.daysDiff( toDate, currDate ) < 0 )
        {
            validDates = false;
            LOGGER.info( "toDate daysDiff" );
        }
        else if ( DateUtils.daysDiff( fromDate, toDate ) < 0 )
        {
            validDates = false;
            LOGGER.info( "fromDate, toDate daysDiff" );
        }
        else if ( DateUtils.daysDiff( fromDate, toDate ) > 90 )
        {
            validDates = false;
            LOGGER.info( "fromDate, 90 daysDiff" );
        }
        return validDates;
    }

    private CrmQrcDto fetchActivatedVAS( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl, fetchActivatedVAS" );
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        invalidRequest( inCrmQrcDto );
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getCrmCustAssetDetailsPojo() ) )
        {
            boolean isValid = false;
            isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getCrmCustAssetDetailsPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_SEARCH_ACTIVATED_VAS_CRITERIA ) == null;
            if ( isValid )
            {
                inCrmQrcDto = getQrcManagerDao().fetchActivatedVAS( inCrmQrcDto );
            }
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    private CrmQrcDto getSelfcareCategories( CrmQrcDto inCrmQrcDto )
    {
        inCrmQrcDto = getQrcManagerDao().getSelfcareCategories( inCrmQrcDto );
        return inCrmQrcDto;
    }

    private CrmQrcDto getSelfcareSubjects( CrmQrcDto inCrmQrcDto )
    {
        inCrmQrcDto = getQrcManagerDao().getSelfcareSubjects( inCrmQrcDto );
        return inCrmQrcDto;
    }

    private void getAdditionalDetails( CrmQrcDto inCrmQrcDto )
    {
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() )
                && StringUtils.isNotBlank( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() ) )
        {
            CrmBillingDto billingDto = getQrcManagerDao().getAdditionalDetails( inCrmQrcDto.getCustomerDetailsPojo()
                                                                                        .getCustomerId(),
                                                                                inCrmQrcDto.getCustomerDetailsPojo()
                                                                                        .getServiceType() );
            inCrmQrcDto.setCustAdditionalDetails( billingDto.getCustAdditionalDetails() );
            inCrmQrcDto.setStatusCode( billingDto.getStatusCode() );
            inCrmQrcDto.setStatusDesc( billingDto.getStatusDesc() );
            inCrmQrcDto.setBillingErrorCode( billingDto.getBillingErrorCode() );
        }
        else
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        }
        MDC.remove( "KEY" );
    }

    private boolean validateDeviceAndNetworkData( CrmQrcDto inCrmQrcDto )
    {
        boolean isValid = true;
        if ( StringUtils
                .equals( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc(), inCrmQrcDto.getActivityAction() ) )
        {
            String macValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getNetworkConfigurationsPojo(),
                                                               ICRMValidationCriteriaUtil.QRC_MAC_ADDRESS_CRITERIA );
            if ( StringUtils.isValidObj( macValid ) )
            {
                isValid = false;
                CRMServiceCode serviceCode = CRMServiceCode.valueOf( macValid );
                inCrmQrcDto.setStatusCode( serviceCode.getStatusCode() );
            }
        }
        else if ( StringUtils.equals( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc(),
                                      inCrmQrcDto.getActivityAction() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getNetworkConfigurationsPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_NETWORK_OPTION82_CRITERIA ) == null;
        }
        else if ( StringUtils.equals( CRMCustomerActivityActions.CPE_OWNERSHIP_CHANGE.getActionDesc(),
                                      inCrmQrcDto.getActivityAction() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getOrderDetailsPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_OWNERSHIP_CHANGE_CRITERIA ) == null;
        }
        return isValid;
    }

    private CrmQrcDto validateWaiverData( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method validateRequestedDate" );
        boolean isValid = false;
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getCrmCustWaiverPojo() ) )
        {
            isValid = ValidationPojoUtil
                    .validatePojo( inCrmQrcDto.getCrmCustWaiverPojo(),
                                   ICRMValidationCriteriaUtil.QRCPOST_WAIVER_SERVICE_REQUEST_CRITERIA ) == null;
            LOGGER.info( "Criteria Result : " + isValid );
            if ( isValid )
            {
                inCrmQrcDto = getQrcManagerDao().postWaiver( inCrmQrcDto );
            }
            else
                invalidRequest( inCrmQrcDto );
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto searchCustomerInteraction( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method searchCustomerInteraction" );
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        invalidRequest( inCrmQrcDto );
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            inCrmQrcDto = getQrcManagerDao().searchCustomerInteraction( inCrmQrcDto );
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    private CrmQrcDto createCustomerInteraction( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method createCustomerInteraction" );
        invalidRequest( inCrmQrcDto );
        boolean isValid = false;
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getCustInteractionsPojo() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getCustInteractionsPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_CUST_INTERACTION_CREATE_CRITERIA ) == null;
            LOGGER.info( "Criteria Result : " + isValid );
            if ( isValid )
            {
                inCrmQrcDto = getQrcManagerDao().createCustomerInteraction( inCrmQrcDto );
            }
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    private CrmQrcDto sendLegalMail( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method sendLegalMail" );
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        invalidRequest( inCrmQrcDto );
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() )
                && StringUtils.isValidObj( inCrmQrcDto.getCrmInvoiceDetailsPojo() ) )
        {
            if ( !StringUtils.isEmpty( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() )
                    && !StringUtils
                            .equals( inCrmQrcDto.getCustomerDetailsPojo().getBrand(), IAppConstants.BRAND_INITIA ) )
            {
                CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
                boolean isSent = false;
                List<String> attachments = new ArrayList<String>();
                String invoicePath = inCrmQrcDto.getCrmInvoiceDetailsPojo().getPasswordPdfFile();
                attachments.add( invoicePath );
                try
                {
                    Map<String, String> paramValues = new HashMap<String, String>();
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCrmInvoiceDetailsPojo() ) )
                    {
                        paramValues.put( "BILL_NUMBER", inCrmQrcDto.getCrmInvoiceDetailsPojo().getBillNumber() );
                        paramValues.put( "BILL_DATE", DateUtils.getFormattedDate( inCrmQrcDto
                                .getCrmInvoiceDetailsPojo().getBillDate(), IDateConstants.SDF_DD_MMM_YYYY ) );
                        paramValues.put( "BILL_PERIOD", inCrmQrcDto.getCrmInvoiceDetailsPojo().getBillPeriod() );
                        paramValues.put( "BILL_DUE_DATE", DateUtils.getFormattedDate( inCrmQrcDto
                                .getCrmInvoiceDetailsPojo().getDueDate(), IDateConstants.SDF_DD_MMM_YYYY ) );
                        if ( StringUtils.isValidObj( inCrmQrcDto.getCrmInvoiceDetailsPojo().getBillAmount() ) )
                        {
                            paramValues.put( "ACCOUNT_BALANCE", inCrmQrcDto.getCrmInvoiceDetailsPojo().getBillAmount()
                                    + "" );
                        }
                    }
                    if ( StringUtils.isValidObj( inCrmQrcDto.getCustAdditionalDetails() ) )
                    {
                        paramValues.put( "ACCOUNT_BALANCE", inCrmQrcDto.getCustAdditionalDetails().getBalance() + "" );
                    }
                    isSent = CRMServiceUtils.sendAlerts( CRMEvents.SEND_LEGAL_MAIL, CRMRequestType.CUSTOMER_ID,
                                                         inCrmQrcDto.getCustomerDetailsPojo().getCustomerId(),
                                                         attachments, paramValues );
                    if ( isSent )
                    {
                        crmServiceCode = CRMServiceCode.CRM001;
                        /*
                         * Going to save customer activity log
                         */
                        CrmCustomerActivityPojo activityLogPojo = new CrmCustomerActivityPojo();
                        activityLogPojo.setAction( CRMCustomerActivityActions.SEND_LEGAL_MAIL.getActionDesc() );
                        activityLogPojo.setCustomerId( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                        activityLogPojo.setOldValue( inCrmQrcDto.getCrmInvoiceDetailsPojo().getBillNumber() );
                        // activityLogPojo.setReason( "Outstanding amount due"
                        // );
                        activityLogPojo.setCreatedBy( inCrmQrcDto.getUserId() );
                        activityLogPojo.setServiceIp( CRMUtils.getServerIp() );
                        activityLogPojo.setClientIp( inCrmQrcDto.getClientIPAddress() );
                        activityLogPojo.setServerIp( inCrmQrcDto.getServerIPAddress() );
                        CRMServiceUtils.createActivityLog( activityLogPojo );
                        /*
                         * Going to save system audit log
                         */
                        CRMServiceUtils.createAuditLog( new StringBuilder( IAppConstants.NOTAPPLICABLE ),
                                                        new StringBuilder( IAppConstants.NOTAPPLICABLE ), inCrmQrcDto
                                                                .getCustomerDetailsPojo().getCustomerId(), inCrmQrcDto
                                                                .getUserId(), activityLogPojo.getReason(),
                                                        CRMServiceUtils.getEventId( CRMEvents.SEND_LEGAL_MAIL
                                                                .getEventName() ), CRMRequestType.QRC.getRequestCode(),
                                                        inCrmQrcDto.getClientIPAddress(), inCrmQrcDto
                                                                .getServerIPAddress() );
                    }
                }
                catch ( Exception ex )
                {
                    LOGGER.error( "Exception while sending legal mail ", ex );
                }
                finally
                {
                    inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
                    inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
                    MDC.remove( "KEY" );
                }
            }
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto qrcRcaReasonList( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method qrcRcaReasonList" );
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID" + inCrmQrcDto.getUserId() );
        invalidRequest( inCrmQrcDto );
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getQrcActionTakenPojo() ) )
        {
            if ( inCrmQrcDto.getQrcActionTakenPojo().getActionId() > 0 )
            {
                inCrmQrcDto = getQrcManagerDao().qrcRootCauseList( inCrmQrcDto );
            }
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    private CrmQrcDto qrcRcaList( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method qrcRcaList" );
        invalidRequest( inCrmQrcDto );
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() )
                && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo().getCustomerDetailsPojo() ) )
        {
            if ( inCrmQrcDto.getCrmSrTicketsPojo().getQrcCategoryId() > 0
                    && !StringUtils.isEmpty( inCrmQrcDto.getCrmSrTicketsPojo().getCustomerDetailsPojo().getProduct() ) )
            {
                inCrmQrcDto = getQrcManagerDao().qrcActionTakenList( inCrmQrcDto );
            }
        }
        return inCrmQrcDto;
    }

    private CrmQrcDto secondLevelAndFurtherBins( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmQrcBusinessImpl : method secondLevelAndFurtherBins" );
        invalidRequest( inCrmQrcDto );
        boolean isValid = false;
        if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj( inCrmQrcDto.getCrmSrTicketsPojo() ) )
        {
            isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getCrmSrTicketsPojo(),
                                                       ICRMValidationCriteriaUtil.QRC_FUNCTIONBIN_LIST_CRITERIA ) == null;
            LOGGER.info( "Criteria Result : " + isValid );
            if ( isValid )
            {
                inCrmQrcDto = getQrcManagerDao().secondLevelAndFurtherBins( inCrmQrcDto );
            }
        }
        return inCrmQrcDto;
    }

    // private CrmQrcDto postServiceRequest( CrmQrcDto inCrmQrcDto )
    // {
    // LOGGER.info( "Inside CrmQrcBusinessImpl : method postServiceRequest" );
    // invalidRequest( inCrmQrcDto );
    // boolean isValid = false;
    // if ( StringUtils.isValidObj( inCrmQrcDto ) && StringUtils.isValidObj(
    // inCrmQrcDto.getCrmSrTicketsPojo() ) )
    // {
    // isValid = ValidationPojoUtil.validatePojo(
    // inCrmQrcDto.getCrmSrTicketsPojo(),
    // ICRMValidationCriteriaUtil.QRCPOST_SERVICE_REQUEST_CRITERIA ) == null;
    // LOGGER.info( "Criteria Result : " + isValid );
    // if ( isValid )
    // {
    // if ( StringUtils.equals( CRMStatusCode.CLOSE.getStatusCode(),
    // inCrmQrcDto.getCrmSrTicketsPojo()
    // .getStatus() ) )
    // {
    // inCrmQrcDto = getQrcManagerDao().closeResolvedSR( inCrmQrcDto );
    // }
    // else
    // {
    // if ( StringUtils.equals( inCrmQrcDto.getActivityAction(),
    // QRCRolCategories.PLAN_MIGRATION_REQUEST.getEvent() )
    // && StringUtils.equals( inCrmQrcDto.getCrmSrTicketsPojo().getStatus(),
    // CRMStatusCode.RESOLVED.getStatusCode() )
    // && inCrmQrcDto.getCrmSrTicketsPojo().getResolutionType() == 1 )
    // {
    // boolean processed = getQrcManagerDao()
    // .updateCustomerPlanDetailsInCRM(
    // inCrmQrcDto.getCrmSrTicketsPojo().getMappingId(),
    // inCrmQrcDto.getCrmSrTicketsPojo().getLastModifiedBy() );
    // if ( processed )
    // {
    // inCrmQrcDto = getQrcManagerDao().postSR( inCrmQrcDto );
    // }
    // }
    // else
    // {
    // inCrmQrcDto = getQrcManagerDao().postSR( inCrmQrcDto );
    // if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
    // inCrmQrcDto.getStatusCode() )
    // || StringUtils.equals( CRMServiceCode.CRM405.getStatusCode(),
    // inCrmQrcDto.getStatusCode() ) )
    // {
    // CrmSrTicketsPojo srTicketsPojo = new CrmSrTicketsPojo();
    // srTicketsPojo.setMappingId(
    // inCrmQrcDto.getCrmSrTicketsPojo().getMappingId() );
    // srTicketsPojo.setQrcCategoryId(
    // inCrmQrcDto.getCrmSrTicketsPojo().getQrcCategoryId() );
    // srTicketsPojo.setQrcSubCategoryId(
    // inCrmQrcDto.getCrmSrTicketsPojo().getQrcSubCategoryId() );
    // srTicketsPojo.setQrcSubSubCategoryId( inCrmQrcDto.getCrmSrTicketsPojo()
    // .getQrcSubSubCategoryId() );
    // CrmQrcDto crmQrcDto = new CrmQrcDto();
    // crmQrcDto.setCrmSrTicketsPojo( srTicketsPojo );
    // crmQrcDto = getQrcManagerDao().viewCustomerTickets( crmQrcDto );
    // if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
    // crmQrcDto.getStatusCode() )
    // && CommonValidator.isValidCollection( crmQrcDto.getCrmSrTicketsPojos() )
    // )
    // {
    // inCrmQrcDto.setCrmSrTicketsPojos( crmQrcDto.getCrmSrTicketsPojos() );
    // }
    // }
    // }
    // }
    // }
    // }
    // return inCrmQrcDto;
    // }
    private CrmQrcDto postSelfcareServiceRequest( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "inside CrmQrcBusinessImpl : postSelfcareServiceRequest" );
        MDC.put( "KEY", "CUSTID:" + inCrmQrcDto.getCustomerId() + ", USERID:" + inCrmQrcDto.getUserId() );
        invalidRequest( inCrmQrcDto );
        if ( inCrmQrcDto.getCrmSelfcareCategoriesPojo().getQrcSubSubCategoryId() > 0
                || StringUtils.isNotBlank( inCrmQrcDto.getRemarksPojo().getRemarks() ) )
        {
            CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = CRMServiceUtils
                    .getDBValues( CrmQrcSubSubCategoriesPojo.class, "qrcSubSubCategoryId", inCrmQrcDto
                            .getCrmSelfcareCategoriesPojo().getQrcSubSubCategoryId() );
            LOGGER.info( "subsubcat:: " + subSubCategoriesPojo );
            CrmQrcSubCategoriesPojo subCategoriesPojo = CRMServiceUtils.getDBValues( CrmQrcSubCategoriesPojo.class,
                                                                                     "qrcSubCategoryId",
                                                                                     subSubCategoriesPojo
                                                                                             .getQrcSubCategoryId() );
            LOGGER.info( "subcat:: " + subCategoriesPojo );
            CrmSrTicketsPojo ticketsPojo = new CrmSrTicketsPojo();
            ticketsPojo.setQrcSubSubCategoryId( subSubCategoriesPojo.getQrcSubSubCategoryId() );
            ticketsPojo.setQrcSubCategoryId( subSubCategoriesPojo.getQrcSubCategoryId() );
            ticketsPojo.setQrcCategoryId( subCategoriesPojo.getQrcCategoryId() );
            LOGGER.info( "ids category:: " + ticketsPojo.getQrcCategoryId() + " subcat:: "
                    + ticketsPojo.getQrcSubCategoryId() + " subsubcat:: " + ticketsPojo.getQrcSubSubCategoryId() );
            ticketsPojo.setResolutionType( CrmTicketActions.FORWARD.getCode() );
            ticketsPojo.setFunctionalbinId( subSubCategoriesPojo.getFunctionalBinId() );
            ticketsPojo.setMappingId( inCrmQrcDto.getUserId() );
            ticketsPojo.setQrcType( subSubCategoriesPojo.getQrcType() );
            ticketsPojo.setModuleType( CRMRequestType.SELFCARE.getRequestCode() );
            ticketsPojo.setCreatedBy( inCrmQrcDto.getUserId() );
            inCrmQrcDto.setCrmSrTicketsPojo( ticketsPojo );
            try
            {
                inCrmQrcDto = CRMServicesProxy
                        .getInstance()
                        .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                        .ticketOperations( CrmActionEnum.OPENED.getActionCode(),
                                           CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
            }
            catch ( SOAPException ex )
            {
                LOGGER.error( "Unable to generate ticket for postSelfcareServiceRequest", ex );
            }
        }
        MDC.remove( "KEY" );
        return inCrmQrcDto;
    }

    private void invalidRequest( CrmQrcDto inCrmQrcDto )
    {
        inCrmQrcDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
    }

    @Override
    public CrmQrcDto customerProfileOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
    {
        boolean isValid = false;
        invalidRequest( inCrmQrcDto );
        MDC.put( "KEY", "SERVICEPARAM:" + inServiceParam + ", CRMPARAM:" + inCrmParam );
        if ( StringUtils.equals( inServiceParam, ServiceParameter.MODIFY.getParameter() )
                && StringUtils.isValidObj( inCrmQrcDto ) )
        {
            if ( StringUtils.equals( inCrmParam, IAppConstants.SYNC_BILL_CYCLE_WITH_BILLING )
                    && StringUtils.isValidObj( inCrmQrcDto.getBillCyclePojo() ) )
            {
                isValid = true; /*
                                 * ValidationPojoUtil.validatePojo(
                                 * inCrmQrcDto.getCustomerDetailsPojo(),
                                 * ICRMValidationCriteriaUtil
                                 * .QRC_BILL_CYCLE_CHANGE ) == null;
                                 */
                if ( isValid )
                {
                    inCrmQrcDto = getCustomerProfileDao().synchronizeBillCycleToBilling( inCrmQrcDto );
                }
            }
            else if ( StringUtils.equals( inCrmParam, IAppConstants.METHOD_UPDATE_CUSTOMER_BILLCYCLE ) )
            {
                isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getCustomerDetailsPojo(),
                                                           ICRMValidationCriteriaUtil.QRC_BILL_CYCLE_CHANGE ) == null;
                if ( isValid )
                {
                    inCrmQrcDto = getCustomerProfileDao().updateBillingCycle( inCrmQrcDto );
                }
            }
            else
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getCustomerDetailsPojo() )
                        && ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getConnectionType(),
                                                 CRMDisplayListConstants.INDIVIDUAL.getCode() ) )
                        || ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getConnectionType(),
                                                 CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) ) )
                {
                    isValid = ValidationPojoUtil
                            .validatePojo( inCrmQrcDto.getCustomerDetailsPojo(),
                                           ICRMValidationCriteriaUtil.QRC_CHANGE_CATEGORY_CRITERIA_INDIVIDUAL ) == null;
                    if ( ( isValid )
                            && ( !StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getNationality(),
                                                      IAppConstants.INDIAN ) ) )
                    {
                        isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getNationaltyDetailPojo(),
                                                                   ICRMValidationCriteriaUtil.QRC_PASSPORT_DETAILS ) == null;
                    }
                }
                else
                {
                    isValid = ValidationPojoUtil
                            .validatePojo( inCrmQrcDto.getCustomerDetailsPojo(),
                                           ICRMValidationCriteriaUtil.QRC_CHANGE_CATEGORY_CRITERIA_LTD ) == null;
                }
                if ( isValid )
                {
                    inCrmQrcDto = getCustomerProfileDao().updateCustomerProfile( inCrmQrcDto );
                }
                LOGGER.info( " Service Code Recived::" + inCrmQrcDto.getStatusCode() );
            }
        }
        else if ( StringUtils.equals( inServiceParam, ServiceParameter.LIST.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, IAppConstants.METHOD_VIEW_CUSTOMER_BILLCYCLE ) )
            {
                inCrmQrcDto = getCustomerProfileDao().getBillCycleHistory( inCrmQrcDto );
            }
        }
        else if ( StringUtils.equals( inServiceParam, ServiceParameter.SAVE.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, IAppConstants.METHOD_GRACE_PERIOD ) )
            {
                inCrmQrcDto = getCustomerProfileDao().updatePrepaidCustomerGracePeriod( inCrmQrcDto );
            }
            if ( StringUtils.equals( inCrmParam, IAppConstants.MULTIPLE_GRACE_PERIOD ) )
            {
                inCrmQrcDto = getCustomerProfileDao().updateMultipleGracePeriod( inCrmQrcDto );
            }
        }
        if ( StringUtils.equals( inServiceParam, ServiceParameter.CANCEL.getParameter() ) )
        {
            if ( StringUtils.equals( inCrmParam, IAppConstants.METHOD_CANCEL_CUSTOMER_BILLCYCLE ) )
            {
                inCrmQrcDto = getCustomerProfileDao().cancelBillCycleRequest( inCrmQrcDto );
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmQrcDto workFlowOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
    {
        invalidRequest( inCrmQrcDto );
        boolean isValid = false;
        if ( StringUtils.equals( inCrmParam, CRMParameter.BIND_MAC.getParameter() )
                && StringUtils.isValidObj( inCrmQrcDto ) )
        {
            inCrmQrcDto = getWorkFlowDao().updateCustomerDetails( inCrmQrcDto );
        }
        else if ( StringUtils.equals( inServiceParam, ServiceParameter.SHIFTING.getParameter() )
                && StringUtils.isValidObj( inCrmQrcDto ) )
        {
            if ( StringUtils.equals( inCrmParam, ServiceParameter.VIEW.getParameter() ) )
            {
                inCrmQrcDto = getWorkFlowDao().getShiftingDetails( inCrmQrcDto );
            }
            else
            {
                inCrmQrcDto = getWorkFlowDao().saveShiftingAddressStages( inCrmQrcDto );
            }
        }
        if ( StringUtils.equals( inServiceParam, ServiceParameter.MODIFY.getParameter() )
                && StringUtils.isValidObj( inCrmQrcDto ) )
        {
            LOGGER.info( "Befor approveRejectWaiver" );
            if ( StringUtils.equals( CRMParameter.WAIVER.getParameter(), inCrmParam ) )
            {
                isValid = ValidationPojoUtil.validatePojo( inCrmQrcDto.getCrmCustWaiverPojo(),
                                                           ICRMValidationCriteriaUtil.QRC_WAIVER_CRITERIA ) == null;
                if ( isValid )
                {
                    inCrmQrcDto = getWorkFlowDao().approveRejectWaiver( inCrmQrcDto );
                }
            }
        }
        return inCrmQrcDto;
    }

    @Override
    public CrmMassOutageDto massOutageOperations( String inServiceParam, CrmMassOutageDto inCrmMassOutageDto )
    {
        if ( !StringUtils.isValidObj( inCrmMassOutageDto ) )
        {
            return invalidRequest();
        }
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        if ( StringUtils.isNotBlank( inServiceParam ) )
        {
            if ( StringUtils.equals( inServiceParam, ServiceParameter.LIST.getParameter() ) )
            {
                inCrmMassOutageDto = getMassOutageDao().searchMassOutage( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.SAVE.getParameter() ) )
            {
                getMassOutageDao().saveMassOutage( inCrmMassOutageDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCrmMassOutageDto.getStatusCode() ) )
                {
                    sendAlertForRIEmployee( inCrmMassOutageDto );
                }
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.UPDATE.getParameter() ) )
            {
                getMassOutageDao().updateMassOutage( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.CLOSE.getParameter() ) )
            {
                /* viewMassOutage( inCrmMassOutageDto );
                 if ( StringUtils.equals( inCrmMassOutageDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )*/
                getMassOutageDao().resolveMassOutage( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.VIEW.getParameter() ) )
            {
                viewMassOutage( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.TRACK.getParameter() ) )
            {
                serviceCode = getMassOutageDao().trackMassOutage( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.TRACKBYCUSTOMERID.getParameter() ) )
            {
                String statusCode = "";
                CrmMassOutagePojo massOutagePojo = null;
                if ( StringUtils.isValidObj( inCrmMassOutageDto )
                        && StringUtils.isNotBlank( inCrmMassOutageDto.getCustomerId() ) )
                {
                    statusCode = ValidationPojoUtil
                            .validatePojo( inCrmMassOutageDto,
                                           ICRMValidationCriteriaUtil.MASS_OUTAGE_TRACK_BY_CUSTOMER_ID );
                    if ( StringUtils.isEmpty( statusCode ) )
                    {
                        massOutagePojo = getMassOutageDao().trackMassOutage( inCrmMassOutageDto.getCustomerId() );
                        if ( StringUtils.isValidObj( massOutagePojo ) )
                        {
                            inCrmMassOutageDto.setCrmMassOutagePojo( massOutagePojo );
                            serviceCode = CRMServiceCode.CRM001;
                        }
                        else
                        {
                            serviceCode = CRMServiceCode.CRM1000;
                        }
                    }
                    else
                    {
                        serviceCode = CRMServiceCode.valueOf( statusCode );
                    }
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM603;
                }
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.SEARCH.getParameter() ) )
            {
                inCrmMassOutageDto = getMassOutageDao().searchMassOutageMaster( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.MODIFY_STATUS.getParameter() ) )
            {
                getMassOutageDao().updateMassOutageActivityStatus( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.MAPPING_LIST.getParameter() ) )
            {
                inCrmMassOutageDto = getMassOutageDao().searchMassOutageEngine( inCrmMassOutageDto );
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.CUSTOMER_LIST.getParameter() ) )
            {
                if ( CommonValidator.isValidCollection( inCrmMassOutageDto.getMasterList() ) )
                {
                    inCrmMassOutageDto = getMassOutageDao().searchCustomerInOutageMaster( inCrmMassOutageDto );
                }
                else if ( CommonValidator.isValidCollection( inCrmMassOutageDto.getSocietyList() ) )
                {
                    inCrmMassOutageDto = getMassOutageDao().searchCustomerInOutageSociety( inCrmMassOutageDto );
                }
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.UPDATE_STATUS.getParameter() ) )
            {
                if ( StringUtils.isValidObj( inCrmMassOutageDto.getCrmMassOutageMastersPojo() ) )
                {
                    inCrmMassOutageDto = getMassOutageDao().updateMassOutageMasterActivityStatus( inCrmMassOutageDto );
                }
                else if ( StringUtils.isValidObj( inCrmMassOutageDto.getCrmMassOutageSocietyPojo() ) )
                {
                    inCrmMassOutageDto = getMassOutageDao().updateMassOutageSocietyActivityStatus( inCrmMassOutageDto );
                }
            }
            else if ( StringUtils.equals( inServiceParam, ServiceParameter.SEARCH_OUTAGE_SOCIETY.getParameter() ) )
            {
                inCrmMassOutageDto = getMassOutageDao().searchMassOutageSociety( inCrmMassOutageDto );
            }
        }
        if ( StringUtils.isBlank( inCrmMassOutageDto.getStatusCode() ) )
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    private void sendAlertForRIEmployee( CrmMassOutageDto inCrmMassOutageDto )
    {
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        Map<String, String> paramValues = new HashMap<String, String>();
        List<CrmNpMobilePojo> crmNpMobilePojos = null;
        try
        {
            criteriaMap.put( "genericType", IAppConstants.EVENT );
            criteriaMap.put( "status", CRMStatusCode.ACTIVE.getStatusCode() );
            crmNpMobilePojos = CRMServiceUtils.getDBValueList( CrmNpMobilePojo.class, criteriaMap, null, false );
            if ( CommonValidator.isValidCollection( crmNpMobilePojos ) )
            {
                LOGGER.info( "NpMobilePojos Size ::" + crmNpMobilePojos.size() );
                for ( CrmNpMobilePojo crmNpMobilePojo : crmNpMobilePojos )
                {
                    LOGGER.info( "MobileNo ::" + crmNpMobilePojo.getMobileNo() );
                    LOGGER.info( "Email ID ::" + crmNpMobilePojo.getEmailId() );
                    if ( StringUtils.isValidObj( crmNpMobilePojo.getMobileNo() ) )
                    {
                        paramValues.put( CRMParameter.USERMOBILENO.getParameter(),
                                         String.valueOf( crmNpMobilePojo.getMobileNo() ) );
                        boolean isAlertSent = CRMServiceUtils.sendAlerts( CRMEvents.ADD_MASSOUTAGE,
                                                                          CRMRequestType.MASS_OUTAGE,
                                                                          inCrmMassOutageDto.getCrmMassOutagePojo()
                                                                                  .getOutageId() + "", null,
                                                                          paramValues );
                        if ( isAlertSent )
                        {
                            LOGGER.info( "Sending SMS successfully......" );
                        }
                        else
                        {
                            LOGGER.info( "SMS sent failed...." );
                        }
                    }
                    if ( StringUtils.isNotBlank( crmNpMobilePojo.getEmailId() ) )
                    {
                        paramValues.put( CRMParameter.RECIPIENTS.getParameter(), crmNpMobilePojo.getEmailId() );
                        boolean isAlertSent = CRMServiceUtils.sendAlerts( CRMEvents.ADD_MASSOUTAGE,
                                                                          CRMRequestType.MASS_OUTAGE,
                                                                          inCrmMassOutageDto.getCrmMassOutagePojo()
                                                                                  .getOutageId() + "", null,
                                                                          paramValues );
                        if ( isAlertSent )
                        {
                            LOGGER.info( "Sending successfully......" );
                        }
                        else
                        {
                            LOGGER.info( "Email sent failed...." );
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing Event for RI Employee Alerts", ex );
        }
    }

    private void viewMassOutage( CrmMassOutageDto inCrmMassOutageDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmMassOutagePojo crmMassOutagePojo = inCrmMassOutageDto.getCrmMassOutagePojo();
        if ( StringUtils.isValidObj( crmMassOutagePojo ) && crmMassOutagePojo.getOutageId() > 0 )
        {
            LOGGER.info( "received mass outage id :: " + crmMassOutagePojo.getOutageId() );
            crmMassOutagePojo = CRMServiceUtils.getDBValues( CrmMassOutagePojo.class, crmMassOutagePojo.getOutageId() );
            if ( StringUtils.isValidObj( crmMassOutagePojo ) )
            {
                if ( !StringUtils.equals( crmMassOutagePojo.getStatus(), CRMStatusCode.RESOLVED.getStatusCode() ) )
                {
                    List<CrmMassOutagePartnerPojo> crmMassOutagePartnerPojos = CRMServiceUtils
                            .getDBValueList( CrmMassOutagePartnerPojo.class, "outageId",
                                             crmMassOutagePojo.getOutageId() );
                    Set<CrmMassOutagePartnerPojo> partnerPojos = new HashSet<CrmMassOutagePartnerPojo>( crmMassOutagePartnerPojos );
                    LOGGER.info( partnerPojos.size() + " partners in this outage" );
                    crmMassOutagePojo.setCrmMassOutagePartners( partnerPojos );
                    inCrmMassOutageDto.setCrmMassOutagePojo( crmMassOutagePojo );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM453;
                }
            }
            else
            {
                serviceCode = CRMServiceCode.CRM996;
            }
        }
        inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
        inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
    }

    private CrmMassOutageDto invalidRequest()
    {
        CrmMassOutageDto crmMassOutageDto = new CrmMassOutageDto();
        crmMassOutageDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        crmMassOutageDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        return crmMassOutageDto;
    }
}
