package com.np.tele.selfcare.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
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
import org.apache.struts.util.MessageResources;

import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmSelfcareCategoriesPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.CustomerUsageDetailsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SelfcareDto;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.Captcha;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ExcelWriteUtils;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.forms.SelfcareForm;
import com.np.tele.selfcare.forms.SelfcareFormHelper;
import com.np.validator.util.CommonValidator;

public class SelfcareAction
    extends DispatchAction
{
    private final Logger     LOGGER          = Logger.getLogger( SelfcareAction.class );
    private ISelfcareManager selfcareManager = null;

    public ISelfcareManager getSelfcareManager()
    {
        return selfcareManager;
    }

    public void setSelfcareManager( ISelfcareManager inSelfcareManager )
    {
        selfcareManager = inSelfcareManager;
        IAppConstants.flyWeightBeanMap.put( IAppConstants.SELFCARE_MANAGER, selfcareManager );
    }

    @Override
    protected ActionForward unspecified( ActionMapping inMapping,
                                         ActionForm inForm,
                                         HttpServletRequest inRequest,
                                         HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "in default action :: method is " + inRequest.getParameter( IAppConstants.PARAMETER_NAME ) );
        return inMapping.findForward( IActionForwardConstant.SELFCARE_HOME_PAGE );
    }

    public ActionForward myAccountPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "in selfcareAction : myAccountPage" );
        String forward = IActionForwardConstant.SELFCARE_INDEX;
        HttpSession session = inRequest.getSession( false );
        if ( StringUtils.isValidObj( session ) )
        {
            CrmCustMyAccountPojo custMyAccountPojo = (CrmCustMyAccountPojo) session
                    .getAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
            if ( StringUtils.isValidObj( custMyAccountPojo )
                    && StringUtils.isNotEmpty( custMyAccountPojo.getCustomerId() ) )
            {
                LOGGER.info( "Customer ID in myAccountPage is  ::" + custMyAccountPojo.getCustomerId() );
                SelfcareForm selfcareForm = (SelfcareForm) inForm;
                selfcareForm.setCustMyAccountPojo( custMyAccountPojo );
                if ( StringUtils.equals( custMyAccountPojo.getStatus(), CRMStatusCode.NEW.getStatusCode() ) )
                {
                    selfcareForm.getCustMyAccountPojo()
                            .setPassword1( selfcareForm.getCustMyAccountPojo().getPassword() );
                    selfcareForm.getCustMyAccountPojo().setPassword( null );
                    selfcareForm.getCustMyAccountPojo().setPassword2( null );
                    forward = IActionForwardConstant.CHANGE_PASSWORD_POPUP;
                }
                else
                {
                    String param = (String) session.getAttribute( "next" );
                    if ( StringUtils.isValidObj( param ) && StringUtils.equals( param, "true" ) )
                    {
                        session.removeAttribute( "next" );
                        return inMapping.findForward( IActionForwardConstant.MY_ACCOUNT_PAGE );
                    }
                    CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) session
                            .getAttribute( CrmCustomerDetailsPojo.class.getSimpleName() );
                    // customer Details n Address details
                    LOGGER.info( "calling for cutomer, address and plan details" );
                    CrmCapDto capDto = getSelfcareManager().customerDetails( crmCustomerDetailsPojo );
                    selfcareForm.setCustomerDetailsPojo( capDto.getCustomerDetailsPojo() );
                    session.setAttribute( CrmCustomerDetailsPojo.class.getSimpleName(), capDto.getCustomerDetailsPojo() );
                    for ( CrmAddressDetailsPojo addressDetailsPojo : capDto.getCustomerDetailsPojo()
                            .getCrmAddressDetailses() )
                    {
                        if ( StringUtils.equals( addressDetailsPojo.getAddressType(),
                                                 IAppConstants.ADDRESS_TYPE_BILLING ) )
                        {
                            selfcareForm.setBillingAddressPojo( addressDetailsPojo );
                        }
                        else if ( StringUtils.equals( addressDetailsPojo.getAddressType(),
                                                      IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
                        {
                            selfcareForm.setInstallationAddressPojo( addressDetailsPojo );
                        }
                    }
                    selfcareForm
                            .setCustPlanDetailsPojo( capDto.getCustomerDetailsPojo().getCrmPlanDetailses().get( 0 ) );
                    LOGGER.info( "base plan code:: " + selfcareForm.getCustPlanDetailsPojo().getBasePlanCode()
                            + " addon code:: " + selfcareForm.getCustPlanDetailsPojo().getAddOnPlanCode() + " recID:: "
                            + selfcareForm.getCustPlanDetailsPojo().getRecordId() );
                    // plan details
                    LOGGER.info( "calling plan details" );
                    MasterDataDto basePlanMasterDataDto = getSelfcareManager().getPlanInfo( selfcareForm );
                    if ( StringUtils.equals( basePlanMasterDataDto.getStatusCode(),
                                             CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( basePlanMasterDataDto.getCrmPlanMasterPojos() ) )
                        {
                            selfcareForm.setBasePlanMasterPojo( basePlanMasterDataDto.getCrmPlanMasterPojos().get( 0 ) );
                            LOGGER.info( "plan name :: " + selfcareForm.getBasePlanMasterPojo().getPlanName()
                                    + " primary speed: " + selfcareForm.getBasePlanMasterPojo().getPrimarySpeed()
                                    + " primary quota:" + selfcareForm.getBasePlanMasterPojo().getPrimaryQuota() );
                        }
                        else
                        {
                            LOGGER.info( "invalid collection CrmPlanMasterPojos" );
                            selfcareForm.setBasePlanMasterPojo( new CrmPlanMasterPojo() );
                        }
                    }
                    if ( StringUtils.isNotBlank( selfcareForm.getCustPlanDetailsPojo().getAddOnPlanCode() ) )
                    {
                        LOGGER.info( "calling addonplan details" );
                        MasterDataDto addonPlanMasterDataDto = getSelfcareManager().getAddonPlanInfo( selfcareForm );
                        if ( StringUtils.equals( addonPlanMasterDataDto.getStatusCode(),
                                                 CRMServiceCode.CRM001.getStatusCode() ) )
                        {
                            if ( CommonValidator.isValidCollection( addonPlanMasterDataDto.getCrmPlanMasterPojos() ) )
                            {
                                selfcareForm.setAddonPlanMasterPojo( addonPlanMasterDataDto.getCrmPlanMasterPojos()
                                        .get( 0 ) );
                                LOGGER.info( "addon plan name :: "
                                        + selfcareForm.getAddonPlanMasterPojo().getPlanName() + " primary quota:"
                                        + selfcareForm.getAddonPlanMasterPojo().getPrimaryQuota() );
                            }
                            else
                            {
                                LOGGER.info( "invalid collection CrmPlanMasterPojos for addon plan" );
                            }
                        }
                    }
                    // setting totalQuota
                    LOGGER.info( "setting total quota baseQuota + addonQuota" );
                    if ( StringUtils.isValidObj( selfcareForm.getBasePlanMasterPojo() )
                            && selfcareForm.getBasePlanMasterPojo().getPrimaryQuota() > 0 )
                    {
                        selfcareForm
                                .setTotalQuota( selfcareForm.getBasePlanMasterPojo().getPrimaryQuota() / 1024 / 1024 / 1024 );
                        if ( StringUtils.isValidObj( selfcareForm.getAddonPlanMasterPojo() )
                                && selfcareForm.getAddonPlanMasterPojo().getPrimaryQuota() > 0 )
                        {
                            selfcareForm.setTotalQuota( selfcareForm.getTotalQuota()
                                    + ( selfcareForm.getAddonPlanMasterPojo().getPrimaryQuota() / 1024 / 1024 / 1024 ) );
                        }
                        LOGGER.info( "totalQouta alloted: " + selfcareForm.getTotalQuota() + " GB" );
                    }
                    // payment history
                    LOGGER.info( "calling payment history" );
                    CrmFinanceDto crmFinanceDto = getSelfcareManager().trackPaymentHistory( selfcareForm );
                    if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( crmFinanceDto.getPaymentDetailsPojos() ) )
                        {
                            LOGGER.info( "payment history size:: " + crmFinanceDto.getPaymentDetailsPojos().size() );
                            selfcareForm.setPaymentDetailsPojos( crmFinanceDto.getPaymentDetailsPojos() );
                        }
                    }
                    // bill details
                    LOGGER.info( "calling bill history" );
                    CrmQrcDto dtoBillHistory = getSelfcareManager().getBillDetails( selfcareForm );
                    if ( StringUtils.equals( dtoBillHistory.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( dtoBillHistory.getInvoiceDetailsPojos() ) )
                        {
                            LOGGER.info( "bill details size: " + dtoBillHistory.getInvoiceDetailsPojos().size() );
                            selfcareForm.setInvoiceDetailsPojos( dtoBillHistory.getInvoiceDetailsPojos() );
                            SortingComparator<CrmInvoiceDetailsPojo> sorter = new SortingComparator<CrmInvoiceDetailsPojo>( "billNumber" );
                            Collections.sort( dtoBillHistory.getInvoiceDetailsPojos(), sorter );
                            selfcareForm.setInvoiceDetailsPojo( dtoBillHistory.getInvoiceDetailsPojos()
                                    .get( dtoBillHistory.getInvoiceDetailsPojos().size() - 1 ) );
                        }
                        else
                        {
                            LOGGER.info( "invalid collection BillDetailsPojos" );
                        }
                    }
                    // outstanding amount and due date
                    LOGGER.info( "calling outstanding details" );
                    CrmQrcDto dtoBillOutstanding = getSelfcareManager()
                            .getBillOutstanding( selfcareForm.getCustomerDetailsPojo() );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), dtoBillOutstanding.getStatusCode() ) )
                    {
                        LOGGER.info( "bill outstanding:: amount: "
                                + dtoBillOutstanding.getCustAdditionalDetails().getBalance() + " dueDate: "
                                + dtoBillOutstanding.getCustAdditionalDetails().getExpiryDate() );
                        selfcareForm.setCustAdditionalDetails( dtoBillOutstanding.getCustAdditionalDetails() );
                        selfcareForm.setCurrentUsage( dtoBillOutstanding.getCustAdditionalDetails()
                                .getUsedVolumeQuota() / 1024 / 1024 / 1024 );
                        LOGGER.info( "billing used quota :: "
                                + dtoBillOutstanding.getCustAdditionalDetails().getUsedVolumeQuota() );
                        LOGGER.info( "current usage :: " + selfcareForm.getCurrentUsage() + " GB" );
                    }
                    /*  selfcareForm.setPercentage( Math.round( selfcareForm.getCurrentUsage()
                                                              / selfcareForm.getTotalQuota() + 0.5 ) );
                                                      selfcareForm.setUsedPercentage( (double) selfcareForm.getCurrentUsage()
                                                              / selfcareForm.getTotalQuota() * 100 );*/
                    selfcareForm.setPercentage( Math.round( selfcareForm.getCurrentUsage()
                            / ( selfcareForm.getTotalQuota() > 0 ? selfcareForm.getTotalQuota() : 1 ) + 0.5 ) );
                    selfcareForm.setUsedPercentage( (double) selfcareForm.getCurrentUsage()
                            / ( selfcareForm.getTotalQuota() > 0 ? selfcareForm.getTotalQuota() : 1 ) * 100 );
                    LOGGER.info( "used :: " + selfcareForm.getUsedPercentage() + " % of "
                            + selfcareForm.getTotalQuota() + " quota - factor :: " + selfcareForm.getPercentage() );
                    // ticket details
                    LOGGER.info( "calling ticket history" );
                    CrmQrcDto qrcDto = getSelfcareManager().getCustomerTickets( selfcareForm );
                    LOGGER.info( "Size of ticket pojo list is :: " + qrcDto.getCrmSrTicketsPojos().size() );
                    if ( StringUtils.equals( qrcDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        List<CrmSrTicketsPojo> openTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
                        List<CrmSrTicketsPojo> nonOpenTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
                        List<CrmQrcCategoriesPojo> categories = getSelfcareManager().getQrcCategories();
                        CrmQrcCategoriesPojo category = null;
                        for ( CrmSrTicketsPojo srTicketsPojo : qrcDto.getCrmSrTicketsPojos() )
                        {
                            try
                            {
                                if ( CommonValidator.isValidCollection( categories ) )
                                {
                                    category = new CrmQrcCategoriesPojo();
                                    category.setQrcCategoryId( srTicketsPojo.getQrcCategoryId() );
                                    srTicketsPojo.setQrcCategory( categories.get( categories.indexOf( category ) )
                                            .getQrcCategory() );
                                }
                            }
                            catch ( Exception ex )
                            {
                                LOGGER.error( "Unable to find Ticket Category in master list for Category ID:"
                                        + srTicketsPojo.getQrcCategoryId(), ex );
                            }
                            if ( StringUtils.equals( srTicketsPojo.getStatus(), CRMStatusCode.OPEN.getStatusCode() )
                                    || StringUtils.equals( srTicketsPojo.getStatus(),
                                                           CRMStatusCode.REOPEN.getStatusCode() ) )
                            {
                                openTicketsPojos.add( srTicketsPojo );
                            }
                            else
                            {
                                nonOpenTicketsPojos.add( srTicketsPojo );
                            }
                        }
                        selfcareForm.setOpenTicketsPojos( openTicketsPojos );
                        selfcareForm.setNonOpenTicketsPojos( nonOpenTicketsPojos );
                    }
                    else
                    {
                        LOGGER.info( "No ticket found" );
                    }
                    forward = IActionForwardConstant.MY_ACCOUNT_PAGE;
                }
            }
        }
        //        ActionRedirect redirect = new ActionRedirect( inMapping.findForward( forward ) );
        //        redirect.addParameter( "login", true );
        //        return redirect;
        return inMapping.findForward( forward );
    }

    public ActionForward changePasswordPage( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        LOGGER.info( "Inside SelfcareAction : changePasswordPage" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        selfcareForm.setCustMyAccountPojo( selfcareForm.getCustMyAccountPojo() );
        selfcareForm.getCustMyAccountPojo().setRmn( 0 );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CHANGE_PASSWORD_POPUP );
    }

    public ActionForward changePassword( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "In SelfcareAction : changePassword" );
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        HttpSession httpSession = inRequest.getSession( false );
        try
        {
            String answer = inRequest.getParameter( "answer" );
            String capcha = (String) httpSession.getAttribute( Captcha.CAPTCHA_KEY );
            LOGGER.info( "The answer text is : " + answer );
            LOGGER.info( "The capcha text is : " + capcha );
            if ( StringUtils.isNotEmpty( answer ) && StringUtils.isNotEmpty( capcha ) )
            {
                if ( !StringUtils.equals( answer, capcha ) )
                {
                    LOGGER.info( "Captcha is not matched" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_WRONG_CAPTCHA ) );
                }
                else
                {
                    if ( StringUtils.equals( selfcareForm.getCustMyAccountPojo().getPassword(),
                                             selfcareForm.getRetypePassword() ) )
                    {
                        SelfcareFormHelper.validateSelfcareForm( selfcareForm, errors );
                        if ( errors.isEmpty() )
                        {
                            SelfcareDto selfcareDto = getSelfcareManager().changePassword( selfcareForm );
                            if ( StringUtils.isValidObj( selfcareDto ) )
                            {
                                if ( StringUtils.equals( selfcareDto.getStatusCode(),
                                                         CRMServiceCode.CRM001.getStatusCode() ) )
                                {
                                    LOGGER.info( "Password has been changed successfully" );
                                    messages.add( IAppConstants.APP_MESSAGE,
                                                  new ActionMessage( CRMServiceCode.CRM808.getStatusCode() ) );
                                    selfcareForm.setCustMyAccountPojo( selfcareDto.getCustMyAccountPojo() );
                                    httpSession.setAttribute( CrmCustMyAccountPojo.class.getSimpleName(),
                                                              selfcareDto.getCustMyAccountPojo() );
                                    httpSession.removeAttribute( "next" );
                                }
                                else
                                {
                                    LOGGER.info( "Required details are not present" );
                                    errors.add( IAppConstants.APP_ERROR,
                                                new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
                                }
                            }
                        }
                    }
                    else
                    {
                        LOGGER.info( "New password and retype password is not same" );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM807.getStatusCode() ) );
                    }
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.CHANGE_PSWRD );
    }

    public ActionForward updateAddressPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LOGGER.info( "In SelfcareAction : updateAddressPage" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        List<StatePojo> states = getSelfcareManager().getStates( CRMStatusCode.ACTIVE.getStatusCode() );
        CityPojo cityPojo = new CityPojo();
        cityPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        cityPojo.setStateId( selfcareForm.getBillingAddressPojo().getStateId() );
        List<CityPojo> cities = getSelfcareManager().getCities( cityPojo );
        LOGGER.info( "found cities:: " + cities.size() );
        selfcareForm.setCityList( cities );
        selfcareForm.setStateList( states );
        CrmAddressDetailsPojo crmBillAddPojo = new CrmAddressDetailsPojo();
        CRMUtils.copyAllProperties( crmBillAddPojo, selfcareForm.getBillingAddressPojo() );
        inRequest.getSession( false ).setAttribute( CrmAddressDetailsPojo.class.getSimpleName(), crmBillAddPojo );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.UPDATE_ADDRESS_POPUP );
    }

    public ActionForward updateBillAddr( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "in selfcareaction : updateBillAddr" );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareFormHelper.validateBillAddr( selfcareForm, errors );
        if ( errors.isEmpty() )
        {
            LOGGER.info( "going to update bill address" );
            CrmAddressDetailsPojo crmBillAddPojo = (CrmAddressDetailsPojo) inRequest.getSession( false )
                    .getAttribute( CrmAddressDetailsPojo.class.getSimpleName() );
            PojoComparator<CrmAddressDetailsPojo> pojoComparator = new PojoComparator<CrmAddressDetailsPojo>();
            int result = pojoComparator.compare( crmBillAddPojo, selfcareForm.getBillingAddressPojo() );
            if ( result == 0 )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM995.getStatusCode() ) );
            }
            if ( errors.isEmpty() )
            {
                selfcareForm.getBillingAddressPojo().setLastModifiedBy( selfcareForm.getCustomerDetailsPojo()
                                                                                .getCustomerId() );
                CrmCapDto crmCapDto = new CrmCapDto();
                crmCapDto.setActivityAction( CRMCustomerActivityActions.BILLING_ADDRESS_CHANGE.getActionDesc() );
                crmCapDto.setAddressDetailsPojo( selfcareForm.getBillingAddressPojo() );
                crmCapDto.setChangeRequest( QRCRolCategories.ADDRESS_MODIFY_BILLING.getEvent() );
                crmCapDto.setCustomerId( selfcareForm.getCustomerDetailsPojo().getCustomerId() );
                crmCapDto.setUserId( selfcareForm.getCustomerDetailsPojo().getCustomerId() );
                crmCapDto = getSelfcareManager().saveCustomerProfileDetails( crmCapDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmCapDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.SUCCESS_SELFCARE_LOG_TICKET, crmCapDto
                                          .getGeneratedTicketId() ) );
                    crmBillAddPojo = new CrmAddressDetailsPojo();
                    CRMUtils.copyAllProperties( crmBillAddPojo, selfcareForm.getBillingAddressPojo() );
                    inRequest.getSession( false ).setAttribute( CrmAddressDetailsPojo.class.getSimpleName(),
                                                                crmBillAddPojo );
                }
                else
                {
                    LOGGER.info( "error code:: " + crmCapDto.getStatusCode() );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmCapDto.getStatusCode() ) );
                }
            }
        }
        if ( selfcareForm.getBillingAddressPojo().getStateId() > 0 )
        {
            CityPojo cityPojo = new CityPojo();
            cityPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            cityPojo.setStateId( selfcareForm.getBillingAddressPojo().getStateId() );
            selfcareForm.setCityList( getSelfcareManager().getCities( cityPojo ) );
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.UPDATE_ADDRESS_POPUP );
    }

    public ActionForward nonOpenTickets( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "In selfcareAction : nonOpenTickets" );
        //        ActionMessages messages = getMessages( inRequest );
        //        ActionMessages errors = getErrors( inRequest );
        //        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        //        selfcareForm.setNonOpenTicketsPojos( selfcareForm.getNonOpenTicketsPojos() );
        //        saveMessages( inRequest, messages );
        //        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.NON_OPEN_TICKETS_POPUP );
    }

    public ActionForward logTicketPage( final ActionMapping inMapping,
                                        final ActionForm inForm,
                                        final HttpServletRequest inRequest,
                                        final HttpServletResponse inResponse )
    {
        LOGGER.info( "in SelfcareAction : logTicketPage" );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        selfcareForm.setCrmSelfcareCategoriesPojo( new CrmSelfcareCategoriesPojo() );
        selfcareForm.setTicketHistoryPojo( new CrmTicketHistoryPojo() );
        selfcareForm.getTicketHistoryPojo().setAction( CrmActionEnum.OPENED.getActionCode() );
        selfcareForm.getTicketHistoryPojo().setCreatedBy( selfcareForm.getCustomerDetailsPojo().getCustomerId() );
        CrmQrcDto qrcDto = getSelfcareManager().getSelfcareSubjects( selfcareForm );
        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
        {
            selfcareForm.setSelfcareSubjects( qrcDto.getSelfcareSubjects() );
        }
        return inMapping.findForward( IActionForwardConstant.LOG_TICKET_PAGE );
    }

    public ActionForward logTicket( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "in SelfcareAction : logTicket" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        SelfcareFormHelper.validateLogTicket( selfcareForm, errors );
        if ( errors.isEmpty() )
        {
            LOGGER.info( "validation pass. process to submit" );
            CrmQrcDto qrcDto = getSelfcareManager().logTicket( selfcareForm );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE,
                              new ActionMessage( IPropertiesConstant.SUCCESS_SELFCARE_LOG_TICKET, qrcDto
                                      .getCrmSrTicketsPojo().getSrId() ) );
                selfcareForm.setCrmSelfcareCategoriesPojo( new CrmSelfcareCategoriesPojo() );
                selfcareForm.setTicketHistoryPojo( new CrmTicketHistoryPojo() );
                selfcareForm.getTicketHistoryPojo().setAction( CrmActionEnum.OPENED.getActionCode() );
                selfcareForm.getTicketHistoryPojo()
                        .setCreatedBy( selfcareForm.getCustomerDetailsPojo().getCustomerId() );
            }
        }
        else if ( StringUtils.isNotBlank( selfcareForm.getCrmSelfcareCategoriesPojo().getSubject() ) )
        {
            CrmQrcDto qrcDto = getSelfcareManager().getSelfcareCategories( selfcareForm );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), qrcDto.getStatusCode() ) )
            {
                selfcareForm.setCrmSelfcareCategoriesPojos( qrcDto.getCrmSelfcareCategoriesPojos() );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.LOG_TICKET_PAGE );
    }

    public ActionForward downloadCustomerUsage( final ActionMapping inMapping,
                                                final ActionForm inForm,
                                                final HttpServletRequest inRequest,
                                                final HttpServletResponse inResponse )
        throws IOException
    {
        LOGGER.info( "Inside QrcAction, downloadCustomerUsage" );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        CrmQrcDto crmQrcDto = null;
        ServletContext servletContext = null;
        MessageResources resources = getResources( inRequest );
        try
        {
            if ( StringUtils.isValidObj( selfcareForm.getCustomerDetailsPojo() ) )
            {
                crmQrcDto = new CrmQrcDto();
                // unbilled
                if ( StringUtils.equals( selfcareForm.getCustomerDetailsPojo().getServiceType(),
                                         CRMDisplayListConstants.PRE_PAID.getCode() ) )
                {
                    crmQrcDto
                            .setUsageFormDate( DateUtils.getFormattedDate( DateUtils
                                                                                   .getPreviousBillingDate( selfcareForm
                                                                                           .getCustAdditionalDetails()
                                                                                           .getExpiryDate() ),
                                                                           IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                    LOGGER.info( "customer service type :: " + selfcareForm.getCustomerDetailsPojo().getServiceType()
                            + " prev. date :: " + crmQrcDto.getUsageFormDate() );
                }
                else
                {
                    crmQrcDto
                            .setUsageFormDate( DateUtils.getFormattedDate( DateUtils
                                                                                   .getPreviousBillingDate( selfcareForm
                                                                                           .getCustomerDetailsPojo()
                                                                                           .getBillDate() ),
                                                                           IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS ) );
                    LOGGER.info( "customer service type :: " + selfcareForm.getCustomerDetailsPojo().getServiceType()
                            + " prev. date :: " + crmQrcDto.getUsageFormDate() );
                }
                crmQrcDto.setUsageToDate( DateUtils.getCurrDateEndTime() );
                crmQrcDto.setCustomerDetailsPojo( selfcareForm.getCustomerDetailsPojo() );
                crmQrcDto = getSelfcareManager().getCustomerPriodicUsageDetails( crmQrcDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmQrcDto.getStatusCode() ) )
                {
                    servletContext = getServlet().getServletContext();
                    LOGGER.info( "Form : " + crmQrcDto.getUsageFormDate() + " To : " + crmQrcDto.getUsageToDate() );
                    String filePath = servletContext.getRealPath( "/" ) + IAppConstants.UNBILLED;
                    String customerName = prepareCustomerFullName( crmQrcDto.getCustomerDetailsPojo() );
                    if ( CommonValidator.isValidCollection( crmQrcDto.getCustomerUsageDetailsPojos() ) )
                    {
                        ExcelWriteUtils excelWriteUtils = new ExcelWriteUtils( customerName, crmQrcDto
                                .getCustomerDetailsPojo().getCustomerId(), filePath );
                        excelWriteUtils.prepareTopRows( IAppConstants.UNBILLED, null, null );
                        for ( CustomerUsageDetailsPojo usageDetailsPojo : crmQrcDto.getCustomerUsageDetailsPojos() )
                        {
                            if ( usageDetailsPojo.getUploadKB().doubleValue() > 0
                                    || usageDetailsPojo.getDownloadKB().doubleValue() > 0 )
                            {
                                excelWriteUtils.addUsageDetailsRow( usageDetailsPojo.getCallEndDate(),
                                                                    usageDetailsPojo.getStartTime(),
                                                                    usageDetailsPojo.getEndTime(),
                                                                    usageDetailsPojo.getUploadKB(),
                                                                    usageDetailsPojo.getDownloadKB() );
                            }
                        }
                        excelWriteUtils.prepareBottomRows();
                        excelWriteUtils.createExcel();
                        LOGGER.info( "File created : " + excelWriteUtils.getFileName() );
                        getSelfcareManager().prepareDownloadResponse( inResponse, excelWriteUtils, servletContext,
                                                                      IAppConstants.UNBILLED );
                    }
                }
                else
                {
                    inResponse.getWriter().println( resources.getMessage( crmQrcDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while download Unbilled Usage", ex );
            inResponse.getWriter().println( "Unable to download data. Please try again later." );
        }
        return null;
    }

    private String prepareCustomerFullName( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        LOGGER.info( "Inside prepareCustomerFullName" );
        String initial = null;
        String fullName = null;
        if ( StringUtils.equals( CRMDisplayListConstants.INDIVIDUAL.getCode(),
                                 inCustomerDetailsPojo.getConnectionType() ) )
        {
            if ( StringUtils.equals( "M", inCustomerDetailsPojo.getCustGender() ) )
            {
                initial = "Mr.";
            }
            else
            {
                initial = "Miss";
            }
            fullName = initial
                    + IAppConstants.SPACE
                    + inCustomerDetailsPojo.getCustFname()
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

    public ActionForward logout( final ActionMapping inMapping,
                                 final ActionForm inForm,
                                 final HttpServletRequest inRequest,
                                 final HttpServletResponse inResponse )
    {
        LOGGER.info( "inside logout" );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        selfcareForm.setCustMyAccountPojo( new CrmCustMyAccountPojo() );
        selfcareForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        HttpSession session = inRequest.getSession( false );
        if ( StringUtils.isValidObj( session ) )
        {
            session.removeAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
            session.removeAttribute( CrmCustomerDetailsPojo.class.getSimpleName() );
            session.invalidate();
        }
        return inMapping.findForward( IActionForwardConstant.SELFCARE_INDEX );
    }

    public ActionForward displayFaqPage( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "In displayFAQPage Action" );
        String forward = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareForm selfcareForm = (SelfcareForm) inForm;
        if ( StringUtils.equals( selfcareForm.getCustomerDetailsPojo().getServiceType(), "PO" ) )
        {
            forward = IActionForwardConstant.POSTPAID_FAQ_PAGE;
        }
        else
        {
            forward = IActionForwardConstant.PREPAID_FAQ_PAGE;
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }
}
