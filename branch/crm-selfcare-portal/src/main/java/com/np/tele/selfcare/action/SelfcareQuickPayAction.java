package com.np.tele.selfcare.action;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
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
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmPaymentCentresPojo;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SelfcareDto;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.common.utils.SelfcareUtils;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;
import com.np.tele.selfcare.forms.SelfcareQuickPayFormHelper;
import com.np.validator.util.CommonValidator;

public class SelfcareQuickPayAction
    extends DispatchAction
{
    private final Logger     LOGGER          = Logger.getLogger( SelfcareQuickPayAction.class );
    private ISelfcareManager selfcareManager = null;

    public ISelfcareManager getSelfcareManager()
    {
        return selfcareManager;
    }

    public void setSelfcareManager( ISelfcareManager inSelfcareManager )
    {
        selfcareManager = inSelfcareManager;
    }

    public ActionForward quickPayPage( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
        throws IOException
    {
        LOGGER.info( "inside quickPayPage" );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        quickPayForm.setCustMyAccountPojo( new CrmCustMyAccountPojo() );
        quickPayForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        HttpSession session = inRequest.getSession( false );
        if ( StringUtils.isValidObj( session ) )
        {
            Object userDetails = session.getAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
            if ( StringUtils.isValidObj( ( userDetails ) ) )
            {
                LOGGER.info( "inside quickPayPage session" );
                inResponse.sendRedirect( "quickPay.do?method=quickPayAction" );
            }
        }
        return inMapping.findForward( IActionForwardConstant.SELFCARE_QUICK_PAY_PAGE );
    }

    public ActionForward quickPayAction( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        LOGGER.info( "quickPayPageAction" );
        String forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        HttpSession session = inRequest.getSession( false );
        Object userDetails = null;
        if ( StringUtils.isValidObj( session ) )
        {
            Object selfcareUtils = session.getAttribute( IAppConstants.SELFCARE_UTILS );
            if ( !StringUtils.isValidObj( selfcareUtils ) )
            {
                session.setAttribute( IAppConstants.SELFCARE_UTILS, new SelfcareUtils() );
            }
            userDetails = session.getAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
        }
        if ( StringUtils.isValidObj( ( userDetails ) ) )
        {
            CrmCustMyAccountPojo myaccount = (CrmCustMyAccountPojo) userDetails;
            CrmCustomerDetailsPojo customeDetailsPojo = new CrmCustomerDetailsPojo();
            customeDetailsPojo.setCustomerId( myaccount.getCustomerId() );
            quickPayForm.setCustomerDetailsPojo( customeDetailsPojo );
        }
        else
        {
            SelfcareQuickPayFormHelper.validateQuickPay( quickPayForm, errors );
        }
        if ( StringUtils.isValidObj( quickPayForm ) && errors.isEmpty() )
        {
            if ( StringUtils.isNotBlank( quickPayForm.getCustomerDetailsPojo().getCustomerId() ) )
            {
                quickPayForm.getCustomerDetailsPojo().setRmn( 0 );
            }
            SelfcareDto selfcareDto = getSelfcareManager().customerAccounts( quickPayForm );
            if ( StringUtils.equals( selfcareDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                quickPayForm.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
                quickPayForm.setCustomerDetailsPojo( selfcareDto.getCustomerDetailsPojo() );
                quickPayForm.setMaskedRmn( StringUtils.getMaskedMobileNo( ""
                        + quickPayForm.getCustomerDetailsPojo().getRmn() ) );
                quickPayForm.setMaskedEmailId( quickPayForm.getCustomerDetailsPojo().getCustEmailId() );
                if ( getBillingPaymentDetails( errors, quickPayForm ) )
                {
                    forward = IActionForwardConstant.SELFCARE_QUICK_PAY_TRANSACTION;
                }
            }
            else if ( StringUtils.equals( selfcareDto.getStatusCode(), CRMServiceCode.CRM801.getStatusCode() ) )
            {
                if ( CommonValidator.isValidCollection( selfcareDto.getCustomerDetailsPojos() ) )
                {
                    quickPayForm.setCustomerDetailsPojos( selfcareDto.getCustomerDetailsPojos() );
                    Iterator<CrmCustomerDetailsPojo> iterator = quickPayForm.getCustomerDetailsPojos().iterator();
                    while ( iterator.hasNext() )
                    {
                        CrmCustomerDetailsPojo crmCustomerDetailsPojo = (CrmCustomerDetailsPojo) iterator.next();
                        if ( StringUtils.isBlank( crmCustomerDetailsPojo.getCustomerId() ) )
                        {
                            iterator.remove();
                        }
                    }
                    if ( quickPayForm.getCustomerDetailsPojos().size() == 0 )
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM809.getStatusCode() ) );
                        quickPayForm.getCustomerDetailsPojo().setRmn( 0 );
                        quickPayForm.getCustomerDetailsPojo().setCustomerId( null );
                        forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAGE;
                    }
                    else if ( quickPayForm.getCustomerDetailsPojos().size() == 1 )
                    {
                        quickPayForm.setCustomerDetailsPojo( quickPayForm.getCustomerDetailsPojos().get( 0 ) );
                        if ( getBillingPaymentDetails( errors, quickPayForm ) )
                        {
                            forward = IActionForwardConstant.SELFCARE_QUICK_PAY_TRANSACTION;
                        }
                    }
                    else
                    {
                        forward = IActionForwardConstant.SELFCARE_QUICK_PAY_LIST;
                    }
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( selfcareDto.getStatusCode() ) );
                quickPayForm.getCustomerDetailsPojo().setRmn( 0 );
                quickPayForm.getCustomerDetailsPojo().setCustomerId( null );
                forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAGE;
            }
        }
        saveErrors( inRequest, errors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( forward );
    }

    private boolean getBillingPaymentDetails( ActionMessages errors, SelfcareQuickPayForm quickPayForm )
    {
        boolean forward = false;
        MasterDataDto masterDataDto;
        CrmQrcDto dtoBillOutstanding = getSelfcareManager().getBillOutstanding( quickPayForm.getCustomerDetailsPojo() );
        if ( StringUtils.equals( dtoBillOutstanding.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
        {
            if ( StringUtils.equals( quickPayForm.getCustomerDetailsPojo().getServiceType(),
                                     CRMDisplayListConstants.POST_PAID.getCode() ) )
            {
                if ( StringUtils.equals( quickPayForm.getCustomerDetailsPojo().getStatus(),
                                         CRMStatusCode.PDC.getStatusCode() )
                        && quickPayForm.getPaymentGatewayPojo().getAmount() < 100 )
                {
                    LOGGER.info( "outstanding amount :: " + quickPayForm.getPaymentGatewayPojo().getAmount() );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM810.getStatusCode() ) );
                }
                else if ( ! ( StringUtils.equals( quickPayForm.getCustomerDetailsPojo().getStatus(),
                                                  CRMStatusCode.INACTIVE.getStatusCode() ) || StringUtils
                        .equals( quickPayForm.getCustomerDetailsPojo().getCrfStage(),
                                 CRMOperationStages.CLOSE.getCode() ) ) )
                {
                    CrmQrcDto billHistoryDto = getSelfcareManager().getBillDetails( quickPayForm
                                                                                            .getCustomerDetailsPojo()
                                                                                            .getCustomerId() );
                    if ( CommonValidator.isValidCollection( billHistoryDto.getInvoiceDetailsPojos() ) )
                    {
                        quickPayForm.setInvoiceList( billHistoryDto.getInvoiceDetailsPojos() );
                        int size = billHistoryDto.getInvoiceDetailsPojos().size();
                        SortingComparator<CrmInvoiceDetailsPojo> sorter = new SortingComparator<CrmInvoiceDetailsPojo>( "billNumber" );
                        Collections.sort( billHistoryDto.getInvoiceDetailsPojos(), sorter );
                        quickPayForm.setInvoicePojo( billHistoryDto.getInvoiceDetailsPojos().get( size - 1 ) );
                        quickPayForm.setPlanBillAmount( quickPayForm.getInvoicePojo().getBillAmount() );
                    }
                }
            }
            else
            {
                masterDataDto = getSelfcareManager().getBasePlanInfo( quickPayForm.getCustomerDetailsPojo()
                                                                              .getCrmPlanDetailses().get( 0 ) );
                if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
                {
                    quickPayForm.setCrmPlanMasterPojo( masterDataDto.getCrmPlanMasterPojos().get( 0 ) );
                    double planBillAnt = quickPayForm.getCrmPlanMasterPojo().getRentInclTax();
                    if ( StringUtils.isNotBlank( quickPayForm.getCustomerDetailsPojo().getCrmPlanDetailses().get( 0 )
                            .getAddOnPlanCode() ) )
                    {
                        masterDataDto = getSelfcareManager().getAddonPlanInfo( quickPayForm.getCustomerDetailsPojo()
                                                                                       .getCrmPlanDetailses().get( 0 ) );
                        if ( CommonValidator.isValidCollection( masterDataDto.getCrmPlanMasterPojos() ) )
                        {
                            quickPayForm.setCrmAddonPlanMasterPojo( masterDataDto.getCrmPlanMasterPojos().get( 0 ) );
                        }
                    }
                    if ( StringUtils.isValidObj( quickPayForm.getCrmAddonPlanMasterPojo() )
                            && quickPayForm.getCrmAddonPlanMasterPojo().getRentInclTax() > 0 )
                    {
                        planBillAnt += quickPayForm.getCrmAddonPlanMasterPojo().getRentInclTax();
                    }
                    quickPayForm.setPlanBillAmount( planBillAnt );
                }
            }
            quickPayForm.setAccountBalance( dtoBillOutstanding.getCustAdditionalDetails().getBalance() );
            if ( quickPayForm.getPlanBillAmount() > 0 )
            {
                double amtDue = quickPayForm.getPlanBillAmount() - quickPayForm.getAccountBalance();
                LOGGER.info( "Amount Due:" + amtDue );
                if ( amtDue > 0 )
                {
                    quickPayForm.setAmountDue( amtDue );
                }
                else
                {
                    quickPayForm.setAmountDue( 0 );
                }
            }
            forward = true;
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( dtoBillOutstanding.getStatusCode() ) );
        }
        return forward;
    }

    public ActionForward quickPayTransaction( final ActionMapping inMapping,
                                              final ActionForm inForm,
                                              final HttpServletRequest inRequest,
                                              final HttpServletResponse inResponse )
    {
        LOGGER.info( "quickPayTransaction" );
        String forward = IActionForwardConstant.SELFCARE_QUICK_PAY_TRANSACTION;
        ActionMessages errors = getErrors( inRequest );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        try
        {
            if ( SelfcareQuickPayFormHelper.validateQuickPayAmount( quickPayForm, errors ).isEmpty() )
            {
                quickPayForm.getPaymentGatewayPojo().setCustomerId( quickPayForm.getCustomerDetailsPojo()
                                                                            .getCustomerId() );
                CrmFinanceDto dtoPgwTrans = getSelfcareManager().processPayment( quickPayForm );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), dtoPgwTrans.getStatusCode() ) )
                {
                    SelfcareDto selfcareDto = getSelfcareManager().customerAccounts( quickPayForm );
                    quickPayForm.setCustomerDetailsPojo( selfcareDto.getCustomerDetailsPojo() );
                    quickPayForm.setMaskedRmn( StringUtils.getMaskedMobileNo( ""
                            + quickPayForm.getCustomerDetailsPojo().getRmn() ) );
                    quickPayForm.setMaskedEmailId( quickPayForm.getCustomerDetailsPojo().getCustEmailId() );
                    quickPayForm.setPaymentGatewayPojo( dtoPgwTrans.getCrmPgwTransactionsPojo() );
                    quickPayForm.setPaymentOptions( getSelfcareManager().getPaymentOptions() );
                    forward = IActionForwardConstant.SELFCARE_QUICK_PAY_CONFIRM;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( dtoPgwTrans.getStatusCode() ) );
                }
                LOGGER.info( "quickPayTransaction :: status code" + dtoPgwTrans.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Exception occured in quickPayTransaction :", ex );
        }
        finally
        {
            if ( !errors.isEmpty() )
            {
                SelfcareDto selfcareDto = getSelfcareManager().customerAccounts( quickPayForm );
                if ( StringUtils.equals( selfcareDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    quickPayForm.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
                    quickPayForm.setCustomerDetailsPojo( selfcareDto.getCustomerDetailsPojo() );
                    quickPayForm.setMaskedRmn( StringUtils.getMaskedMobileNo( ""
                            + quickPayForm.getCustomerDetailsPojo().getRmn() ) );
                    quickPayForm.setMaskedEmailId( quickPayForm.getCustomerDetailsPojo().getCustEmailId() );
                    if ( getBillingPaymentDetails( errors, quickPayForm ) )
                    {
                        forward = IActionForwardConstant.SELFCARE_QUICK_PAY_TRANSACTION;
                    }
                }
            }
            saveErrors( inRequest, errors );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward paymentResponse( final ActionMapping inMapping,
                                          final ActionForm inForm,
                                          final HttpServletRequest inRequest,
                                          final HttpServletResponse inResponse )
    {
        LOGGER.info( "in paymentResponse" );
        String forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAGE;
        String trackid = inRequest.getParameter( "trackid" );
        if ( StringUtils.isValidObj( trackid ) )
        {
            SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
            if ( !StringUtils.isValidObj( quickPayForm ) )
            {
                quickPayForm = new SelfcareQuickPayForm();
            }
            CrmPgwTransactionsPojo pgwTransactionsPojo = new CrmPgwTransactionsPojo();
            pgwTransactionsPojo.setPgwTrackId( trackid );
            CrmFinanceDto crmFinanceDto = getSelfcareManager().getPaymentDetails( pgwTransactionsPojo );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
            {
                CrmCustomerDetailsPojo customerDetailsPojo = new CrmCustomerDetailsPojo();
                customerDetailsPojo.setCustomerId( crmFinanceDto.getCrmPgwTransactionsPojo().getCustomerId() );
                quickPayForm.setCustomerDetailsPojo( customerDetailsPojo );
                SelfcareDto selfcareDto = getSelfcareManager().customerAccounts( quickPayForm );
                quickPayForm.setCustomerDetailsPojo( selfcareDto.getCustomerDetailsPojo() );
                quickPayForm.setPaymentGatewayPojo( crmFinanceDto.getCrmPgwTransactionsPojo() );
                forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAYMENT_SUCCESS;
            }
        }
        return inMapping.findForward( forward );
    }

    public ActionForward quickPayRedirect( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
    {
        LOGGER.info( "quick pay redirect" );
        String forward = IActionForwardConstant.SELFCARE_QUICK_PAY_CONFIRM;
        ActionMessages errors = getErrors( inRequest );
        HttpSession session = inRequest.getSession( false );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        try
        {
            if ( StringUtils.isValidObj( quickPayForm.getPaymentGatewayPojo() )
                    && StringUtils.isNotBlank( quickPayForm.getPaymentGatewayPojo().getPaymentOption() ) )
            {
                quickPayForm.getPaymentGatewayPojo().setCustomerId( quickPayForm.getCustomerDetailsPojo()
                                                                            .getCustomerId() );
                quickPayForm.getPaymentGatewayPojo().setUdf1( quickPayForm.getCustomerDetailsPojo().getCustomerId() );
                quickPayForm.getPaymentGatewayPojo().setUdf2( quickPayForm.getCustomerDetailsPojo().getCustEmailId() );
                quickPayForm.getPaymentGatewayPojo().setUdf3( quickPayForm.getCustomerDetailsPojo().getRmn() + "" );
                quickPayForm.getPaymentGatewayPojo().setUdf4( quickPayForm.getPaymentGatewayPojo().getPaymentOption() );
                quickPayForm.getPaymentGatewayPojo().setPaymentOption( quickPayForm.getPaymentGatewayPojo()
                                                                               .getPaymentOption() );
                CrmFinanceDto crmFinanceDto = getSelfcareManager().postPayment( quickPayForm );
                if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "quickPayRedirect ::url:: "
                            + crmFinanceDto.getCrmPgwTransactionsPojo().getRedirectUrl() );
                    quickPayForm.getPaymentGatewayPojo().setRedirectUrl( crmFinanceDto.getCrmPgwTransactionsPojo()
                                                                                 .getRedirectUrl() );
                    quickPayForm.setAppReturnUrl( crmFinanceDto.getCrmPgwTransactionsPojo().getAppReturnUrl() );
                    session.setAttribute( "PayOption", crmFinanceDto.getCrmPgwTransactionsPojo().getPaymentOption() );
                    //            inResponse.sendRedirect( "quickPay.do?method=paymentResponse" );
                    //            inResponse.sendRedirect( crmFinanceDto.getCrmPgwTransactionsPojo().getRedirectUrl() );
                    ActionForward actionForward = new ActionForward( crmFinanceDto.getCrmPgwTransactionsPojo()
                            .getRedirectUrl(), true );
                    return actionForward;
                }
                //                else if ( StringUtils.isValidObj( crmFinanceDto.getCrmPgwTransactionsPojo() )
                //                        && StringUtils.isNotBlank( crmFinanceDto.getCrmPgwTransactionsPojo().getRedirectUrl() ) )
                //                {
                //                    ActionForward actionForward = new ActionForward( crmFinanceDto.getCrmPgwTransactionsPojo()
                //                            .getRedirectUrl(), true );
                //                    return actionForward;
                //                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmFinanceDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
            LOGGER.error( "Exception occured in quick pay redirect :", ex );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }

    public ActionForward paymentCenterPage( final ActionMapping inMapping,
                                            final ActionForm inForm,
                                            final HttpServletRequest inRequest,
                                            final HttpServletResponse inResponse )
    {
        LOGGER.info( "In paymentCenterPage Action" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        String method = "paymentCenterPage";
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        SelfcareQuickPayFormHelper.reset( quickPayForm, method );
        quickPayForm.setPaymentCentresPojo( new CrmPaymentCentresPojo() );
        List<String> pincodes = getSelfcareManager().getPincodeList( quickPayForm );
        Collections.sort( pincodes );
        quickPayForm.setListPincode( pincodes );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_CENTER_PAGE );
    }

    public ActionForward getPaymentCenterDetails( final ActionMapping inMapping,
                                                  final ActionForm inForm,
                                                  final HttpServletRequest inRequest,
                                                  final HttpServletResponse inResponse )
    {
        LOGGER.info( "In getPaymentCenterDetails Action" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        if ( StringUtils.isValidObj( quickPayForm ) )
        {
            if ( StringUtils.isValidObj( quickPayForm.getPaymentCentresPojo() ) )
            {
                SelfcareQuickPayFormHelper.validatePincode( quickPayForm, errors );
                if ( errors.isEmpty() )
                {
                    ConfigDto configDto = getSelfcareManager().getPaymentCenterDetails( quickPayForm );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), configDto.getStatusCode() ) )
                    {
                        if ( CommonValidator.isValidCollection( configDto.getPaymentCentres() ) )
                        {
                            quickPayForm.setPaymentCentresPojos( configDto.getPaymentCentres() );
                            SortingComparator<CrmPaymentCentresPojo> sorter = new SortingComparator<CrmPaymentCentresPojo>( "vendorName" );
                            Collections.sort( quickPayForm.getPaymentCentresPojos(), sorter );
                            sorter = null;
                        }
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( configDto.getStatusCode() ) );
                    }
                    LOGGER.info( "Payment center details :: status code" + configDto.getStatusCode() );
                }
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_CENTER_PAGE );
    }
}