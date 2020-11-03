package com.np.tele.crm.finance.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.QRCRolCategories;
import com.np.tele.crm.finance.bm.IFinanceManager;
import com.np.tele.crm.finance.forms.FinanceForm;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class PaymentPostingAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( PaymentPostingAction.class );
    private IFinanceManager     financeManagerBm;

    public IFinanceManager getFinanceManagerBm()
    {
        return financeManagerBm;
    }

    public void setFinanceManagerBm( IFinanceManager financeManagerBm )
    {
        this.financeManagerBm = financeManagerBm;
    }

    public ActionForward paymentPostingPage( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_POSTING_PAGE;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        QRCRolCategories qrcCategory = QRCRolCategories.CHEQUE_BOUNCE_BILL_PAYMENT;
        try
        {
            CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
            crmPaymentDetailsPojo.setEntityType( CRMDisplayListConstants.TELESERVICES.getCode() );
            crmPaymentDetailsPojo.setRealzationStatus( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED.getCode() );
            crmPaymentDetailsPojo.setAmount( 0.0 );
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            financeForm.setPaymentDate( sdf.format( new Date() ) );
            financeForm.setChequeDate( sdf.format( new Date() ) );
            financeForm.setBouncingDate( sdf.format( new Date() ) );
            financeForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
            financeForm.setPaymentModeList( CRMUtils.getPaymentMode() );
            financeForm.setPaymentChannelList( CRMUtils.getPaymentChannelList() );
            financeForm.setCaseStausList( CRMUtils.getPaymentCaseStatusList() );
            financeForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
            financeForm.setChequeStatusList( CRMUtils.getChequeRealizationStatusList() );
            financeForm.setCrmPaymentDetailsPojo( crmPaymentDetailsPojo );
            financeForm.setChequeRejectionReasonList( CRMCacheManager.getChequeBouncingReasons( qrcCategory ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured", ex );
            errors.add( "errorMessage", new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward paymentPosting( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_POSTING_PAGE;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        QRCRolCategories qrcCategory = QRCRolCategories.CHEQUE_BOUNCE_BILL_PAYMENT;
        try
        {
            FinanceForm financeForm = (FinanceForm) form;
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            if ( StringUtils.isValidObj( userDto ) )
            {
                financeForm.getCrmPaymentDetailsPojo().setCreatedBy( userDto.getCrmUserDetailsPojo().getUserId() );
            }
            CrmFinanceDto financeDetailsDto = getFinanceManagerBm().paymentPosting( financeForm );
            if ( StringUtils.isValidObj( financeDetailsDto ) )
            {
                String statusCode = financeDetailsDto.getStatusCode();
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    LOGGER.info( "status code" + statusCode );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    HttpSession httpSession = request.getSession( false );
                    httpSession.setAttribute( "successStatusCode", statusCode );
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                    financeForm.setToDate( sdf.format( new Date() ) );
                    financeForm.setFromDate( sdf.format( new Date() ) );
                    forwardPage = IActionForwardConstant.PAYMENT_TRACKING;
                }
                else if ( CRMServiceCode.CRM706.getStatusCode().equals( statusCode ) )
                {
                    LOGGER.info( "Status Code ::" + statusCode + "Billing Code :"
                            + financeDetailsDto.getBillingErrorCode() );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( statusCode, financeDetailsDto.getBillingErrorCode() ) );
                    SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
                    financeForm.setToDate( sdf.format( new Date() ) );
                    financeForm.setFromDate( sdf.format( new Date() ) );
                    forwardPage = IActionForwardConstant.PAYMENT_TRACKING;
                }
                else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM611.getStatusCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode, financeForm
                            .getCrmPaymentDetailsPojo().getReceiptNo() ) );
                }
                else if ( StringUtils.equals( statusCode, CRMServiceCode.CRM618.getStatusCode() ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode, financeForm
                            .getCrmPaymentDetailsPojo().getTransactionId() ) );
                }
                else
                {
                    LOGGER.info( "status code" + statusCode );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( statusCode, financeDetailsDto.getBillingErrorCode() ) );
                }
                if ( errors.isEmpty() && CommonValidator.isValidCollection( financeDetailsDto.getStatusCodes() ) )
                {
                    for ( String messageCode : financeDetailsDto.getStatusCodes() )
                    {
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( messageCode ) );
                    }
                }
            }
            financeForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
            financeForm.setPaymentModeList( CRMUtils.getPaymentMode() );
            financeForm.setPaymentChannelList( CRMUtils.getPaymentChannelList() );
            financeForm.setPaymentStatusList( CRMUtils.getPaymentRecievedStatus() );
            financeForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
            financeForm.setChequeStatusList( CRMUtils.getChequeRealizationStatusList() );
            financeForm.setPaymentModeList( CRMUtils.getPaymentMode() );
            financeForm.setInstallationStatusList( CRMUtils.getInstallationStatusList() );
            financeForm.setCaseStatusList( CRMUtils.getPaymentCaseStatusList() );
            financeForm.setChequeRejectionReasonList( CRMCacheManager.getChequeBouncingReasons( qrcCategory ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured", ex );
            errors.add( "errorMessage", new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }

    public ActionForward paymentReversalPage( ActionMapping mapping,
                                              ActionForm form,
                                              HttpServletRequest request,
                                              HttpServletResponse response )
        throws Exception
    {
        FinanceForm financeForm = (FinanceForm) form;
        SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy" );
        Date date = new Date();
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        financeForm.setCrmPaymentDetailsPojo( crmPaymentDetailsPojo );
        financeForm.setToDate( dateFormat.format( date ) );
        financeForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        financeForm.setRemarksPojo( new RemarksPojo() );
        String forwardPage = IActionForwardConstant.PAYMENT_REVERSAL_PAGE;
        return mapping.findForward( forwardPage );
    }

    public ActionForward searchReversalPaymentDetails( ActionMapping mapping,
                                                       ActionForm form,
                                                       HttpServletRequest request,
                                                       HttpServletResponse response )
        throws Exception
    {
        CrmFinanceDto financeDto = null;
        List<CrmPaymentDetailsPojo> crmPaymentDetailsList = null;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        String target = IActionForwardConstant.PAYMENT_REVERSAL_PAGE;
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateSingleFormProperty( financeForm, ICRMValidationCriteriaUtil.FORM_CUSTOMER_SEARCH_CRITERIA,
                                             false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil
                    .validateForm( financeForm, ICRMValidationCriteriaUtil.FORM_PAYMENT_SEARCH_CRITERIA, false );
        }
        ValidationUtil.prepareErrorMessage( errors, resultMap );
        if ( errors.isEmpty() )
        {
            try
            {
                financeForm.setFromDate( getFinanceManagerBm().getFromDate( financeForm.getToDate() ) );
                financeDto = getFinanceManagerBm().searchPaymentDetails( financeForm );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), financeDto.getStatusCode() ) )
                {
                    financeForm.setCrfId( financeDto.getCustomerDetailsPojo().getCrfId() );
                    financeForm.setCustomerId( financeDto.getCustomerDetailsPojo().getCustomerId() );
                    crmPaymentDetailsList = financeDto.getPaymentDetailsPojos();
                    if ( crmPaymentDetailsList.size() < 1 )
                    {
                        LOGGER.info( "Record not found..  " );
                        errors.add( IAppConstants.NO_RECORD_FOUND,
                                    new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                    }
                    else
                    {
                        financeForm.setSearchPaymentList( crmPaymentDetailsList );
                    }
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
                    LOGGER.info( "status code of finance dto: " + financeDto.getStatusCode() );
                }
            }
            catch ( Exception ex )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                LOGGER.error( "error occured while searching payment details", ex );
            }
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( target );
    }

    public ActionForward paymentReversalPopUp( ActionMapping inMapping,
                                               ActionForm inForm,
                                               HttpServletRequest inRequest,
                                               HttpServletResponse inResponse )
        throws Exception
    {
        FinanceForm financeForm = (FinanceForm) inForm;
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        crmPaymentDetailsPojo.setPaymentId( Long.parseLong( inRequest.getParameter( "paymentId" ) ) );
        financeForm.setCrmPaymentDetailsPojo( crmPaymentDetailsPojo );
        financeForm.setCrmCustomerDetailsPojo( crmCustomerDetailsPojo );
        financeForm
                .setPaymentReversalReason( CRMCacheManager.getFinanceReasons( CRMRCAReason.FINANCE_PAYMENT_REVERSAL ) );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_REVERSAL_POP_UP_PAGE );
    }

    public ActionForward paymentReversal( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
        throws Exception
    {
        CrmFinanceDto crmFinanceDto = null;
        String forwardPage = IActionForwardConstant.PAYMENT_REVERSAL_PAGE;
        ActionMessages actionMessages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        FinanceForm financeForm = (FinanceForm) inForm;
        validateReversalForm( errors, financeForm );
        if ( errors.isEmpty() )
        {
            try
            {
                CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                        .getAttribute( IAppConstants.CRM_USER_OBJECT );
                String user = userDto.getCrmUserDetailsPojo().getUserId();
                crmFinanceDto = getFinanceManagerBm().reversePayment( financeForm, user );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
                {
                    crmFinanceDto = getFinanceManagerBm().searchPaymentDetails( financeForm );
                    if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmFinanceDto.getStatusCode() ) )
                    {
                        financeForm.setSearchPaymentList( crmFinanceDto.getPaymentDetailsPojos() );
                    }
                    actionMessages.add( IAppConstants.APP_MESSAGE, new ActionMessage( crmFinanceDto.getStatusCode() ) );
                }
                else
                {
                    LOGGER.info( " Unable to Process Request Status Code " + crmFinanceDto.getStatusCode()
                            + " & Status Desc:" + crmFinanceDto.getStatusDesc() );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmFinanceDto.getStatusCode(),
                                                                            crmFinanceDto.getBillingErrorCode() ) );
                }
                if ( errors.isEmpty() && CommonValidator.isValidCollection( crmFinanceDto.getStatusCodes() ) )
                {
                    for ( String messageCode : crmFinanceDto.getStatusCodes() )
                    {
                        actionMessages.add( IAppConstants.APP_MESSAGE, new ActionMessage( messageCode ) );
                    }
                }
            }
            catch ( Exception ex )
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
                LOGGER.error( "error occured while searching payment details", ex );
            }
        }
        saveMessages( inRequest, actionMessages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    private void validateReversalForm( ActionMessages errors, FinanceForm financeForm )
    {
        commonReversalValidation( errors, financeForm );
        if ( errors.isEmpty() && !financeForm.isReversalWOCrf() )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil.validateSingleFormProperty( financeForm
                    .getCrmCustomerDetailsPojo(), ICRMValidationCriteriaUtil.FORM_CUSTOMER_SEARCH_CRITERIA, false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
        }
    }

    private void commonReversalValidation( ActionMessages errors, FinanceForm financeForm )
    {
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( financeForm.getRemarksPojo(), ICRMValidationCriteriaUtil.FORM_REMARKS_CRITERIA_COMMON,
                               false );
        ValidationUtil.prepareErrorMessage( errors, resultMap );
        if ( errors.isEmpty() )
        {
            resultMap = ValidationPojoUtil.validateForm( financeForm.getRemarksPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_REMARKS_CRITERIA_OTHER, false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                CrmRcaReasonPojo reason = CommonUtils.getReasonById( CRMCacheManager
                        .getFinanceReasons( CRMRCAReason.FINANCE_PAYMENT_REVERSAL ), financeForm.getRemarksPojo()
                        .getReasonId() );
                financeForm.getCrmPaymentDetailsPojo().setReversalReason( reason.getCategoryValue() );
                if ( StringUtils.isEmpty( financeForm.getDocSrPaymentReversal() ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_DOC_SR_REQUIRED ) );
                }
                else if ( StringUtils.isNotBlank( financeForm.getDocSrPaymentReversal() )
                        && StringUtils.equals( financeForm.getDocSrPaymentReversal(), IAppConstants.SR_NUMBER )
                        && ( StringUtils.isEmpty( financeForm.getCrmPaymentDetailsPojo().getSrId() ) ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SR_REQUIRED ) );
                }
            }
        }
    }

    /*public static List<ContentPojo> getpaymentModeList()
    {
        if ( !StringUtils.isValidObj( paymentModeList ) || paymentModeList.isEmpty() )
        {
            paymentModeList = new ArrayList<ContentPojo>();
             ContentPojo contentPojo = new ContentPojo( "Please Select", "0" );
             paymentModeList.add( contentPojo );
            for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
            {
                if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_MODE.getParameter() ) )
                {
                    ContentPojo contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                    paymentModeList.add( contentPojo );
                }
            }
        }
        return paymentModeList;
    }*/
    /*
        public static List<ContentPojo> getpaymentChannelList()
        {
            if ( !StringUtils.isValidObj( paymentChannelList ) || paymentChannelList.isEmpty() )
            {
                paymentChannelList = new ArrayList<ContentPojo>();
                 ContentPojo contentPojo = new ContentPojo( "Please Select", "0" );
                 paymentModeList.add( contentPojo );
                for ( CRMDisplayListConstants crmDisplayConst : CRMDisplayListConstants.values() )
                {
                    if ( StringUtils.equals( crmDisplayConst.getListType(), CRMParameter.PAYMENT_CHANNEL.getParameter() ) )
                    {
                        ContentPojo contentPojo = new ContentPojo( crmDisplayConst.getValue(), crmDisplayConst.getCode() );
                        paymentChannelList.add( contentPojo );
                    }
                }
            }
            return paymentChannelList;
        }*/
    public ActionForward changePaymentStatus( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_TRACKING;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        HttpSession httpSession = inRequest.getSession( false );
        FinanceForm financeForm = (FinanceForm) inForm;
        if ( StringUtils.isValidObj( httpSession.getAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE ) ) )
        {
            httpSession.removeAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE );
        }
        CrmFinanceDto crmFinanceDto = getFinanceManagerBm().changePaymentStatus( financeForm );
        if ( StringUtils.isValidObj( crmFinanceDto ) )
        {
            String statusCode = crmFinanceDto.getStatusCode();
            if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                httpSession.setAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE, statusCode );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        return inMapping.findForward( forwardPage );
    }

    public ActionForward changeRealizationStatusPage( ActionMapping inMapping,
                                                      ActionForm inForm,
                                                      HttpServletRequest inRequest,
                                                      HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.REALIZATION_POP_UP;
        CrmPaymentDetailsPojo crmPaymentDetailsPojo = new CrmPaymentDetailsPojo();
        FinanceForm financeForm = (FinanceForm) inForm;
        QRCRolCategories qrcCategory = QRCRolCategories.CHEQUE_BOUNCE_ADVANCE_PAYMENT;
        String installStatus = inRequest.getParameter( "installationStatus" );
        if ( StringUtils.equals( installStatus, CRMDisplayListConstants.POSTINSTALLATION.getCode() ) )
        {
            qrcCategory = QRCRolCategories.CHEQUE_BOUNCE_BILL_PAYMENT;
        }
        financeForm.setChequeRejectionReasonList( CRMCacheManager.getChequeBouncingReasons( qrcCategory ) );
        crmPaymentDetailsPojo.setPaymentId( ( Long.parseLong( inRequest.getParameter( "paymentId" ) ) ) );
        financeForm.setPaymentId( crmPaymentDetailsPojo.getPaymentId() );
        financeForm.setCrmPaymentDetailsPojo( crmPaymentDetailsPojo );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward changeRealizationStatus( ActionMapping inMapping,
                                                  ActionForm inForm,
                                                  HttpServletRequest inRequest,
                                                  HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_TRACKING;;
        CrmFinanceDto crmFinanceDto = null;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        try
        {
            FinanceForm financeForm = (FinanceForm) inForm;
            HttpSession httpSession = inRequest.getSession( false );
            //            LOGGER.info( "Payment ID:: " + financeForm.getPaymentId() );
            //            LOGGER.info( "values recieved for bouncing date" + financeForm.getBouncingDate() + "reason:"
            //                    + financeForm.getBouncingReason() );
            //            CrmuserDetailsDto crmuserDetailsDto = (CrmuserDetailsDto) httpSession
            //                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            //            financeForm.setModifiedBy( crmuserDetailsDto.getCrmUserDetailsPojo().getUserId() );
            //            LOGGER.info( ":::::modified by:" + financeForm.getModifiedBy() );
            //            List<CrmPaymentDetailsPojo> crmPaymentSearchList = (List<CrmPaymentDetailsPojo>) httpSession
            //                    .getAttribute( IAppConstants.CRM_PAYMENT_SEARCH_LIST );
            //            if ( CommonValidator.isValidCollection( crmPaymentSearchList ) )
            //            {
            //                financeForm.setSearchPaymentList( crmPaymentSearchList );
            //            }
            //            else
            //            {
            //                financeForm.setSearchPaymentList( new ArrayList<CrmPaymentDetailsPojo>() );
            //            }
            if ( StringUtils.isValidObj( httpSession.getAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE ) ) )
            {
                httpSession.removeAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE );
            }
            crmFinanceDto = getFinanceManagerBm().changeRealizationStatus( financeForm );
            if ( StringUtils.isValidObj( crmFinanceDto ) )
            {
                String statusCode = crmFinanceDto.getStatusCode();
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    LOGGER.info( "status code" + statusCode );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    httpSession.setAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE, statusCode );
                }
                else
                {
                    LOGGER.info( "status code" + statusCode );
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( statusCode, crmFinanceDto.getBillingErrorCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured while,change Realization Status", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward realizeRecieveAll( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_TRACKING;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        FinanceForm financeForm = (FinanceForm) inForm;
        HttpSession httpSession = inRequest.getSession( false );
        if ( StringUtils.isValidObj( httpSession.getAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE ) ) )
        {
            httpSession.removeAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE );
        }
        //        List<CrmPaymentDetailsPojo> crmPaymentSearchList = (List<CrmPaymentDetailsPojo>) httpSession
        //                .getAttribute( IAppConstants.CRM_PAYMENT_SEARCH_LIST );
        //        financeForm.setSearchPaymentList( crmPaymentSearchList );
        //        CrmuserDetailsDto crmuserDetailsDto = (CrmuserDetailsDto) httpSession
        //                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        CrmFinanceDto crmFinanceDto = getFinanceManagerBm().realizeRecieveAll( financeForm );
        if ( StringUtils.isValidObj( crmFinanceDto ) )
        {
            String statusCode = crmFinanceDto.getStatusCode();
            if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
            {
                LOGGER.info( "status code" + statusCode );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                httpSession.setAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE, statusCode );
            }
            else
            {
                LOGGER.info( "status code" + statusCode );
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( statusCode, crmFinanceDto.getBillingErrorCode() ) );
            }
        }
        // to display the processed payments.
        financeForm.setInstallationStatus( "" );
        financeForm.setPaymentStatus( "" );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward changeCaseStatus( ActionMapping inMapping,
                                           ActionForm inForm,
                                           HttpServletRequest inRequest,
                                           HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_TRACKING;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            FinanceForm financeForm = (FinanceForm) inForm;
            HttpSession httpSession = inRequest.getSession( false );
            //            List<CrmPaymentDetailsPojo> crmPaymentSearchList = (List<CrmPaymentDetailsPojo>) httpSession
            //                    .getAttribute( IAppConstants.CRM_PAYMENT_SEARCH_LIST );
            //            financeForm.setSearchPaymentList( crmPaymentSearchList );
            //            CrmuserDetailsDto crmuserDetailsDto = (CrmuserDetailsDto) httpSession
            //                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            //            financeForm.setModifiedBy( crmuserDetailsDto.getCrmUserDetailsPojo().getUserId() );
            CrmFinanceDto crmFinanceDto = getFinanceManagerBm().changeCaseStatus( financeForm );
            if ( StringUtils.isValidObj( crmFinanceDto ) )
            {
                String statusCode = crmFinanceDto.getStatusCode();
                LOGGER.info( "status Code:" + statusCode );
                if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                {
                    //                    financeForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                    httpSession.setAttribute( "successStatusCode", statusCode );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                }
            }
            //            financeForm.setInstallationStatus( "" );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured in changeCaseStatus", ex );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward rectifyRejectedRecord( ActionMapping inMapping,
                                                ActionForm inForm,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.SUSPENSE_REJECTED_PAYMENT;
        FinanceForm financeForm = (FinanceForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        try
        {
            CrmCmsPaymentPojo cmsPayment = financeForm.getCrmCmsPaymentPojo();
            cmsPayment.setInstrumentDate( DateUtils.changeDateFormat( financeForm.getChequeDate() ) );
            cmsPayment.setDepositDate( DateUtils.changeDateFormat( financeForm.getPaymentDate() ) );
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( cmsPayment, ICRMValidationCriteriaUtil.FORM_CMSRECORDS_CRITERIA_RECTIFY_REJECTED,
                                   false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                if ( StringUtils.equals( cmsPayment.getDraweeBank(), "0" ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( "error.invalid.cms.draweeBank" ) );
                }
            }
            if ( errors.isEmpty() )
            {
                CrmFinanceDto crmFinanceDto = getFinanceManagerBm().rectifyRejectedRecord( financeForm );
                if ( StringUtils.isValidObj( crmFinanceDto ) )
                {
                    String statusCode = crmFinanceDto.getStatusCode();
                    LOGGER.info( "statusCode" + statusCode );
                    if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                    {
                        financeForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        HttpSession httpSession = inRequest.getSession( false );
                        httpSession.setAttribute( "successStatusCode", statusCode );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                    }
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception occured", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward rectifySuspenseRecord( ActionMapping inMapping,
                                                ActionForm inForm,
                                                HttpServletRequest inRequest,
                                                HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.SUSPENSE_REJECTED_PAYMENT;
        FinanceForm financeForm = (FinanceForm) inForm;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        try
        {
            CrmCmsPaymentPojo cmsPaymentPojo = new CrmCmsPaymentPojo();
            if ( StringUtils.isValidObj( financeForm.getCrmCmsPaymentPojo().getCmsId() ) )
            {
                cmsPaymentPojo.setCmsId( financeForm.getCrmCmsPaymentPojo().getCmsId() );
            }
            int pojoIndex = financeForm.getCrmCmsPaymentPojos().indexOf( cmsPaymentPojo );
            LOGGER.info( "Pojo index........" + pojoIndex );
            if ( pojoIndex >= 0 )
            {
                cmsPaymentPojo = financeForm.getCrmCmsPaymentPojos().get( pojoIndex );
                cmsPaymentPojo.setIe2( financeForm.getCrmCmsPaymentPojo().getIe2() );
                cmsPaymentPojo.setLastModifiedBy( financeForm.getCrmCmsPaymentPojo().getLastModifiedBy() );
                LOGGER.info( "New customerId:" + cmsPaymentPojo.getIe2() );
                financeForm.setCrmCmsPaymentPojo( cmsPaymentPojo );
            }
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( cmsPaymentPojo,
                                   ICRMValidationCriteriaUtil.FORM_CMSRECORDS_CRITERIA_RECTIFY_SUSPENSE, false );
            ValidationUtil.prepareErrorMessage( errors, resultMap );
            if ( errors.isEmpty() )
            {
                if ( StringUtils.equals( cmsPaymentPojo.getDraweeBank(), "0" ) )
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( "error.invalid.cms.draweeBank" ) );
                }
            }
            if ( errors.isEmpty() )
            {
                CrmFinanceDto crmFinanceDto = getFinanceManagerBm().rectifySuspenseRecord( financeForm );
                if ( StringUtils.isValidObj( crmFinanceDto ) )
                {
                    String statusCode = crmFinanceDto.getStatusCode();
                    LOGGER.info( "StatusCode." + statusCode );
                    if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                    {
                        financeForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
                        messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( statusCode ) );
                        HttpSession httpSession = inRequest.getSession( false );
                        httpSession.setAttribute( "successStatusCode", statusCode );
                    }
                    else
                    {
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( statusCode, crmFinanceDto.getBillingErrorCode() ) );
                    }
                }
            }
            else
            {
                forwardPage = IActionForwardConstant.SUSPENSE_REJECTED_PAYMENT_PAGE;
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception occured", e );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }
}
