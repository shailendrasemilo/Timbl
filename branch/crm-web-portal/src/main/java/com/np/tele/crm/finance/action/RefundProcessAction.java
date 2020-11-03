package com.np.tele.crm.finance.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.finance.bm.IFinanceManager;
import com.np.tele.crm.finance.forms.FinanceForm;
import com.np.tele.crm.finance.forms.FinanceFormHelper;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerRefundsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPodDetailsPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class RefundProcessAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( RefundProcessAction.class );
    private IFinanceManager     financeManagerBm;

    public IFinanceManager getFinanceManagerBm()
    {
        return financeManagerBm;
    }

    public void setFinanceManagerBm( IFinanceManager financeManagerBm )
    {
        this.financeManagerBm = financeManagerBm;
    }

    public ActionForward postRefundPage( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
    {
        String findForward = IActionForwardConstant.POST_REFUND_PAGE;
        LOGGER.info( "RefundProcessAction : postRefundPage" );
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        financeForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        financeForm.setCrmCustomerRefundsPojo( new CrmCustomerRefundsPojo() );
        financeForm.setRemarksPojo( new RemarksPojo() );
        financeForm.setChequeDate( null );
        financeForm.setPaymentDate( null );
        financeForm.setBankList( CRMCacheManager.getBankListByStatus( CRMStatusCode.ACTIVE ) );
        financeForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
        financeForm.getCrmCustomerRefundsPojo().setChequeStatus( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED
                                                                         .getCode() );
        financeForm.setPaymentStatusList( CRMUtils.getWorkflowStatus() );
        financeForm.setDisplayButton( true );
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( findForward );
    }

    public ActionForward updateRefund( ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response )
    {
        String findForward = IActionForwardConstant.POST_REFUND_PAGE;
        LOGGER.info( "RefundProcessAction : updateRefund" );
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        financeForm.setDisplayButton( true );
        if ( StringUtils.isValidObj( financeForm ) && StringUtils.isValidObj( financeForm.getCrmCustomerRefundsPojo() ) )
        {
            financeForm.getCrmCustomerRefundsPojo().setChequeStatus( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED
                                                                             .getCode() );
            if ( StringUtils.equals( financeForm.getCrmCustomerRefundsPojo().getRefundMode(),
                                     CRMDisplayListConstants.CHEQUE.getCode() ) )
            {
                financeForm.getCrmCustomerRefundsPojo().setChequeDate( DateUtils.changeDateFormat( financeForm
                                                                               .getChequeDate() ) );
                financeForm.getCrmCustomerRefundsPojo().setTransactionId( null );
                financeForm.getCrmCustomerRefundsPojo().setCustAccountNumber( null );
                financeForm.getCrmCustomerRefundsPojo().setCustBankName( null );
                financeForm.getCrmCustomerRefundsPojo().setIfscCode( null );
                financeForm.setPaymentDate( null );
            }
            else if ( StringUtils.equals( financeForm.getCrmCustomerRefundsPojo().getRefundMode(),
                                          CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
            {
                financeForm.getCrmCustomerRefundsPojo().setRefundDate( DateUtils.changeDateFormat( financeForm
                                                                               .getPaymentDate() ) );
                financeForm.getCrmCustomerRefundsPojo().setChequeNumber( null );
                financeForm.setChequeDate( null );
                financeForm.getCrmCustomerRefundsPojo().setPayerBankName( null );
            }
        }
        FinanceFormHelper.validateRefundProcess( errors, financeForm );
        if ( errors.isEmpty() )
        {
            financeForm.getCrmCustomerRefundsPojo().setCustomerId( financeForm.getCrmCustomerDetailsPojo()
                                                                           .getCustomerId() );
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            String userId = userDto.getCrmUserDetailsPojo().getUserId();
            CrmFinanceDto financeDto = getFinanceManagerBm().processRefund( financeForm, userId );
            if ( StringUtils.isValidObj( financeDto ) )
            {
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), financeDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( financeDto.getStatusCode() ) );
                    findForward = IActionForwardConstant.POST_REFUND_SUCCESS;
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR,
                                new ActionMessage( financeDto.getStatusCode(), financeDto.getBillingErrorCode() ) );
                }
            }
        }
        else
        {
            financeForm.setDisplayButton( false );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( findForward );
    }

    public ActionForward refundTrackingPage( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
    {
        String target = IActionForwardConstant.REFUND_TRACKING_PAGE;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        financeForm.setPaymentStatusList( CRMUtils.getWorkflowStatus() );
        financeForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
        financeForm.setPaymentModeList( CRMUtils.getPaymentMode( CRMParameter.REFUND.getParameter() ) );
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( target );
    }

    public ActionForward refundTracking( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
    {
        String target = IActionForwardConstant.REFUND_TRACKING_PAGE;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateSingleFormProperty( financeForm, ICRMValidationCriteriaUtil.FORM_CUSTOMER_SEARCH_CRITERIA,
                                             false );
        ValidationUtil.prepareErrorMessage( errors, resultMap );
        if ( errors.isEmpty() )
        {
            CrmFinanceDto financeDto = getFinanceManagerBm().searchRefundDetails( financeForm );
            if ( StringUtils.equals( financeDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( financeDto.getRefundDetailsPojos().isEmpty() )
                {
                    financeForm.setCrmCustomerRefundsPojos( new ArrayList<CrmCustomerRefundsPojo>() );
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
                else
                {
                    financeForm.setCrmCustomerRefundsPojos( financeDto.getRefundDetailsPojos() );
                    LOGGER.info( "Size of List" + financeForm.getCrmCustomerRefundsPojos().size() );
                }
            }
            else if ( StringUtils.equals( financeDto.getStatusCode(), CRMServiceCode.CRM309.getStatusCode() ) )
            {
                financeForm.setCrmCustomerRefundsPojos( new ArrayList<CrmCustomerRefundsPojo>() );
                errors.add( IAppConstants.NO_RECORD_FOUND,
                            new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
            }
            else
            {
                financeForm.setCrmCustomerRefundsPojos( new ArrayList<CrmCustomerRefundsPojo>() );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
            }
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( target );
    }

    public ActionForward fileUploadPODPage( ActionMapping inMapping,
                                            ActionForm inForm,
                                            HttpServletRequest inRequest,
                                            HttpServletResponse inResponse )
        throws Exception
    {
        return inMapping.findForward( IActionForwardConstant.FILE_UPLOAD_POD_PAGE );
    }

    public ActionForward uploadPODFile( ActionMapping inMapping,
                                        ActionForm inForm,
                                        HttpServletRequest inRequest,
                                        HttpServletResponse inResponse )
    {
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession( false )
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        ActionMessages messages = new ActionMessages();
        ActionMessages actionErrors = getErrors( inRequest );
        FinanceForm financeForm = (FinanceForm) inForm;
        String user = userDto.getCrmUserDetailsPojo().getUserId();
        try
        {
            FinanceFormHelper.validatePODFile( actionErrors, financeForm );
            if ( actionErrors.isEmpty() )
            {
                String filePath = getServlet().getServletContext().getRealPath( "/" ) + "PODUpload";
                List<String> statusList = getFinanceManagerBm().processPODExcel( financeForm, user, filePath );
                if ( statusList.size() > 1 )
                {
                    String errorFile = statusList.get( 1 );
                    String code = statusList.get( 0 ).substring( 0, statusList.get( 0 ).indexOf( "_" ) );
                    LOGGER.info( code );
                    if ( StringUtils.equals( code, CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        int validCount = Integer
                                .parseInt( statusList.get( 0 ).substring( statusList.get( 0 ).indexOf( "_" ) + 1,
                                                                          statusList.get( 0 ).lastIndexOf( "_" ) ) );
                        LOGGER.info( "valid Count::" + validCount );
                        int totalRecords = Integer.parseInt( statusList.get( 0 )
                                .substring( statusList.get( 0 ).lastIndexOf( "_" ) + 1 ) );
                        LOGGER.info( "Valid Records: " + validCount + " Total Records : " + totalRecords );
                        messages.add( "PODStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS2,
                                                                      totalRecords ) );
                        messages.add( "PODStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS3,
                                                                      validCount ) );
                        if ( validCount < totalRecords )
                        {
                            messages.add( "displayErrorFile", new ActionMessage( "" ) );
                            inRequest.setAttribute( "fileName", errorFile );
                        }
                        if ( validCount > 0 )
                        {
                            messages.add( "PODStatus", new ActionMessage( IPropertiesConstant.FILE_UPLOAD_STATUS4,
                                                                          "success" ) );
                        }
                    }
                    else
                    {
                        actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( code ) );
                    }
                }
                else
                {
                    actionErrors.add( "invalidHeader", new ActionMessage( IPropertiesConstant.FILE_INVALID_HEADER ) );
                }
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception :::", e );
            actionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
        }
        saveErrors( inRequest, actionErrors );
        saveMessages( inRequest, messages );
        return inMapping.findForward( IActionForwardConstant.FILE_UPLOAD_POD_PAGE );
    }

    public ActionForward changeRefundStatus( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
    {
        String target = IActionForwardConstant.REFUND_TRACKING_PAGE;
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        try
        {
            CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession()
                    .getAttribute( IAppConstants.CRM_USER_OBJECT );
            CrmCustomerRefundsPojo pojo = new CrmCustomerRefundsPojo();
            pojo.setRefundId( Long.parseLong( request.getParameter( "refundId" ) ) );
            pojo.setChequeStatus( request.getParameter( "chequeStatus" ) );
            if ( StringUtils.isNotBlank( financeForm.getBouncingReason() ) )
            {
                pojo.setRejectionReason( financeForm.getBouncingReason() );
            }
            financeForm.setCrmCustomerRefundsPojo( pojo );
            CrmFinanceDto financeDto = getFinanceManagerBm().getchangeRefundStatus( financeForm,
                                                                                    userDto.getCrmUserDetailsPojo()
                                                                                            .getUserId() );
            if ( StringUtils.equals( financeDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( financeDto.getStatusCode() ) );
            }
            else if ( StringUtils.isNotBlank( financeDto.getBillingErrorCode() ) )
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( financeDto.getStatusCode(), financeDto.getBillingErrorCode() ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
            }
            financeForm.setCustomerId( request.getParameter( "customerId" ) );
            financeForm.setCrmCustomerRefundsPojos( getFinanceManagerBm().searchRefundDetails( financeForm )
                    .getRefundDetailsPojos() );
            if ( StringUtils.isValidObj( financeForm.getCrmCustomerRefundsPojo() ) )
            {
                financeForm.getCrmCustomerRefundsPojo().setRefundId( 0 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured::", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( target );
    }

    public ActionForward changeNonRealizationStatusPage( ActionMapping mapping,
                                                         ActionForm form,
                                                         HttpServletRequest request,
                                                         HttpServletResponse response )
    {
        String forwardPage = IActionForwardConstant.NON_REALIZATION_POP_UP;
        FinanceForm financeForm = (FinanceForm) form;
        financeForm.setRefundRjectionList( CRMCacheManager.getFinanceReasons( CRMRCAReason.REFUND_REASON ) );
        CrmCustomerRefundsPojo pojo = new CrmCustomerRefundsPojo();
        pojo.setRefundId( Long.parseLong( request.getParameter( "Id" ) ) );
        pojo.setChequeStatus( ( request.getParameter( "cheStatus" ) ) );
        pojo.setCustomerId( request.getParameter( "custId" ) );
        financeForm.setCrmCustomerRefundsPojo( pojo );
        return mapping.findForward( forwardPage );
    }

    public ActionForward suspenseRejectedRefundPage( ActionMapping mapping,
                                                     ActionForm form,
                                                     HttpServletRequest request,
                                                     HttpServletResponse response )
    {
        String findForward = IActionForwardConstant.VIEW_POD_TRACKING_PAGE;
        LOGGER.info( "RefundProcessAction : suspenseRejectedRefundPage" );
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            financeForm.setToDate( sdf.format( new Date() ) );
            financeForm.setFromDate( sdf.format( new Date() ) );
            financeForm.setFileStatusList( CRMUtils.getCMSRecordStatusList() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( findForward );
    }

    public ActionForward trackSuspenseRejectedRecord( ActionMapping mapping,
                                                      ActionForm form,
                                                      HttpServletRequest request,
                                                      HttpServletResponse response )
    {
        String findForward = IActionForwardConstant.VIEW_POD_TRACKING_PAGE;
        LOGGER.info( "RefundProcessAction : suspenseRejectedRefundPage" );
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        try
        {
            LOGGER.info( "track suspense rejected records::" );
            CrmFinanceDto financeDto = getFinanceManagerBm().trackPODRecords( financeForm );
            LOGGER.info( "Status Code::" + financeDto.getStatusCode() );
            if ( StringUtils.equals( financeDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                if ( financeDto.getCrmPodDetailsPojos().isEmpty() )
                {
                    financeForm.setCrmPodDetailsPojos( new ArrayList<CrmPodDetailsPojo>() );
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
                else
                {
                    financeForm.setCrmPodDetailsPojos( financeDto.getCrmPodDetailsPojos() );
                    LOGGER.info( "Size of List" + financeForm.getCrmPodDetailsPojos().size() );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( findForward );
    }

    public ActionForward rectifyRecordPage( ActionMapping mapping,
                                            ActionForm form,
                                            HttpServletRequest request,
                                            HttpServletResponse response )
    {
        String findForward = IActionForwardConstant.VIEW_POD_RECTIFY_PAGE;
        LOGGER.info( "RefundProcessAction : suspenseRejectedRefundPage" );
        ActionMessages messages = getMessages( request );
        ActionMessages errors = getErrors( request );
        FinanceForm financeForm = (FinanceForm) form;
        try
        {
            List<CrmPodDetailsPojo> crmPodDetailsPojoList = financeForm.getCrmPodDetailsPojos();
            LOGGER.info( "No of pojo find in list" + crmPodDetailsPojoList.size() );
            CrmPodDetailsPojo crmPodDetailsPojo = new CrmPodDetailsPojo();
            crmPodDetailsPojo.setPodId( Long.parseLong( request.getParameter( "Id" ) ) );
            int pojoIndex = crmPodDetailsPojoList.indexOf( crmPodDetailsPojo );
            LOGGER.info( pojoIndex );
            if ( pojoIndex >= 0 )
            {
                financeForm.setCrmPodDetailsPojo( financeForm.getCrmPodDetailsPojos().get( pojoIndex ) );
                financeForm.setDeliverDate( DateUtils.convertXmlCalToString( financeForm.getCrmPodDetailsPojo()
                        .getDeliveredDate() ) );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured", ex );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( findForward );
    }
}
