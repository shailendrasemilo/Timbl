package com.np.tele.crm.finance.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

import com.np.tele.crm.common.utils.CRMCacheManager;
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
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class PaymentTrackingAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( PaymentTrackingAction.class );
    private IFinanceManager     financeManagerBm;

    public IFinanceManager getFinanceManagerBm()
    {
        return financeManagerBm;
    }

    public void setFinanceManagerBm( IFinanceManager financeManagerBm )
    {
        this.financeManagerBm = financeManagerBm;
    }

    public ActionForward paymentTrackingPage( ActionMapping inMapping,
                                              ActionForm inForm,
                                              HttpServletRequest inRequest,
                                              HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.PAYMENT_TRACKING_PAGE;
        FinanceForm financeForm = (FinanceForm) inForm;
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        financeForm.setToDate( sdf.format( new Date() ) );
        financeForm.setFromDate( sdf.format( new Date() ) );
        financeForm.setInstallationStatusList( CRMUtils.getInstallationStatusList() );
        financeForm.setPaymentStatusList( CRMUtils.getPaymentRecievedStatus() );
        financeForm.setCaseStatusList( CRMUtils.getPaymentCaseStatusList() );
        financeForm.setCustomerServiceTypeList( CRMUtils.getCustomerServiceTypeList() );
        financeForm.setChequeStatusList( CRMUtils.getChequeRealizationStatusList() );
        financeForm.setEntityTypeList( CRMUtils.getEntityTypeList() );
        financeForm.setPaymentModeList( CRMUtils.getPaymentMode() );
        financeForm.setPaymentChannelList( CRMUtils.getPaymentChannelList() );
        financeForm.setInstallationStatus( CRMDisplayListConstants.PREINSTALLATION.getCode() );
        financeForm.setCheque_status( CRMDisplayListConstants.CHEQUE_CLEARANCE_AWAITED.getCode() );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward paymentTracking( ActionMapping inMapping,
                                          ActionForm inForm,
                                          HttpServletRequest inRequest,
                                          HttpServletResponse inResponse )
    {
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        String forwardPage = IActionForwardConstant.PAYMENT_TRACKING_PAGE;
        FinanceForm financeForm = (FinanceForm) inForm;
        LOGGER.info( financeForm );
        HttpSession httpSession = inRequest.getSession( false );
        if ( StringUtils.isBlank( financeForm.getFromDate() ) && StringUtils.isBlank( financeForm.getToDate() ) )
        {
            SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
            financeForm.setToDate( sdf.format( new Date() ) );
            financeForm.setFromDate( sdf.format( new Date() ) );
        }
        if ( StringUtils.isValidObj( httpSession.getAttribute( IAppConstants.CRM_PAYMENT_SEARCH_LIST ) ) )
        {
            httpSession.removeAttribute( IAppConstants.CRM_PAYMENT_SEARCH_LIST );
        }
        CrmFinanceDto crmFinanceDto = getFinanceManagerBm().searchPayment( financeForm );
        if ( StringUtils.isValidObj( crmFinanceDto ) )
        {
            if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                financeForm.setSearchPaymentList( crmFinanceDto.getPaymentDetailsPojos() );
                if ( !financeForm.getSearchPaymentList().isEmpty() )
                {
                    httpSession
                            .setAttribute( IAppConstants.CRM_PAYMENT_SEARCH_LIST, financeForm.getSearchPaymentList() );
                    financeForm.setSearchPaymentListSize( financeForm.getSearchPaymentList().size() );
                }
                else
                {
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
            }
            else
            {
                LOGGER.info( "error code" + crmFinanceDto.getStatusCode() );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmFinanceDto.getStatusCode() ) );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward suspenseRejectedPaymentPage( ActionMapping inMapping,
                                                      ActionForm inForm,
                                                      HttpServletRequest inRequest,
                                                      HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.SUSPENSE_REJECTED_PAYMENT_PAGE;
        FinanceForm financeForm = (FinanceForm) inForm;
        CrmCmsFilePojo cmsFilePojo = new CrmCmsFilePojo();
        CrmPaymentDetailsPojo paymentPojo = new CrmPaymentDetailsPojo();
        CrmCmsPaymentPojo cmsPaymentPojo = new CrmCmsPaymentPojo();
        financeForm.setCrmCmsPaymentPojo( cmsPaymentPojo );
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MMM-yyyy" );
        financeForm.setToDate( sdf.format( new Date() ) );
        financeForm.setFromDate( sdf.format( new Date() ) );
        cmsFilePojo.setStatus( CRMStatusCode.ARCHIVE.getStatusCode() );
        cmsFilePojo.setCmsFileType( CRMStatusCode.DEPOSIT.getStatusCode() );
        financeForm.setCrmCmsFilePojo( cmsFilePojo );
        List<CrmCmsFilePojo> depositfileList = getFinanceManagerBm().getFileList( financeForm ).getCmsFiles();
        financeForm.setFileStatusList( CRMUtils.getCMSRecordStatusList() );
        financeForm.setCrmCmsFilePojos( depositfileList );
        cmsFilePojo.setCmsFileType( CRMStatusCode.CLEARANCE.getStatusCode() );
        financeForm.setCrmCmsFilePojo( cmsFilePojo );
        List<CrmCmsFilePojo> clearancefileList = getFinanceManagerBm().getFileList( financeForm ).getCmsFiles();
        LOGGER.info( "File List size" + clearancefileList.size() );
        financeForm.setClearancefileList( clearancefileList );
        financeForm.setCrmPaymentDetailsPojo( paymentPojo );
        //  financeForm.setCrmCmsFilePojo( cmsFilePojo );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward viewSuspenseRejectedRecord( ActionMapping inMapping,
                                                     ActionForm inForm,
                                                     HttpServletRequest inRequest,
                                                     HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.SUSPENSE_REJECTED_PAYMENT_PAGE;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        FinanceForm financeForm = (FinanceForm) inForm;
        HttpSession httpSession = inRequest.getSession( false );
        if ( StringUtils.isValidObj( httpSession.getAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE ) ) )
        {
            String msg = (String) httpSession.getAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE );
            messages.add( IAppConstants.CRM_SUCCESS_STATUS_CODE, new ActionMessage( msg ) );
            httpSession.removeAttribute( IAppConstants.CRM_SUCCESS_STATUS_CODE );
        }
        CrmFinanceDto financeDto = getFinanceManagerBm().searchSuspenseRecord( financeForm );
        if ( StringUtils.isValidObj( financeDto ) )
        {
            String statusCode = financeDto.getStatusCode();
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), statusCode ) )
            {
                LOGGER.info( "CMS Payments Size: " + financeDto.getCmsPayments().size() );
                financeForm.getCrmCmsFilePojo().setStatus( CRMStatusCode.ARCHIVE.getStatusCode() );
                financeForm.getCrmCmsFilePojo().setCmsFileType( CRMStatusCode.DEPOSIT.getStatusCode() );
                List<CrmCmsFilePojo> depositfileList = getFinanceManagerBm().getFileList( financeForm ).getCmsFiles();
                financeForm.setCrmCmsFilePojos( depositfileList );
                financeForm.getCrmCmsFilePojo().setCmsFileType( CRMStatusCode.CLEARANCE.getStatusCode() );
                List<CrmCmsFilePojo> clearancefileList = getFinanceManagerBm().getFileList( financeForm ).getCmsFiles();
                financeForm.setClearancefileList( clearancefileList );
                if ( !financeDto.getCmsPayments().isEmpty() )
                {
                    financeForm.setCrmCmsPaymentPojos( financeDto.getCmsPayments() );
                }
                else
                {
                    errors.add( IAppConstants.NO_RECORD_FOUND,
                                new ActionMessage( IPropertiesConstant.ERROR_NO_RECORD_FOUND ) );
                }
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward editRejectedRecordPage( ActionMapping inMapping,
                                                 ActionForm inForm,
                                                 HttpServletRequest inRequest,
                                                 HttpServletResponse inResponse )
    {
        String forwardPage = IActionForwardConstant.EDIT_REJECTED_RECORD_PAGE;
        FinanceForm financeForm = (FinanceForm) inForm;
        CrmCmsPaymentPojo cmsPaymentPojo = new CrmCmsPaymentPojo();
        if ( !StringUtils.isBlank( inRequest.getParameter( "cmsId" ) ) )
        {
            cmsPaymentPojo.setCmsId( Long.parseLong( inRequest.getParameter( "cmsId" ) ) );
        }
        if ( !StringUtils.isBlank( inRequest.getParameter( "toDate" ) ) )
        {
            financeForm.setToDate( inRequest.getParameter( "toDate" ) );
        }
        if ( !StringUtils.isBlank( inRequest.getParameter( "fromDate" ) ) )
        {
            financeForm.setFromDate( inRequest.getParameter( "fromDate" ) );
        }
        int pojoIndex = financeForm.getCrmCmsPaymentPojos().indexOf( cmsPaymentPojo );
        LOGGER.info( "Pojo index........" + pojoIndex );
        if ( pojoIndex >= 0 )
        {
            financeForm.setCrmCmsPaymentPojo( financeForm.getCrmCmsPaymentPojos().get( pojoIndex ) );
            financeForm.setPaymentDate( DateUtils.convertXmlCalToString( financeForm.getCrmCmsPaymentPojo()
                    .getDepositDate() ) );
            financeForm.setChequeDate( DateUtils.convertXmlCalToString( financeForm.getCrmCmsPaymentPojo()
                    .getInstrumentDate() ) );
        }
        List<CrmRcaReasonPojo> crmRcaReasonList = CRMCacheManager.getBankListByStatus(CRMStatusCode.ACTIVE);
        financeForm.setBankList( crmRcaReasonList );
        return inMapping.findForward( forwardPage );
    }

    public ActionForward viewPayment( ActionMapping inMapping,
                                      ActionForm inForm,
                                      HttpServletRequest inRequest,
                                      HttpServletResponse inResponse )
    {
        FinanceForm financeForm = (FinanceForm) inForm;
        HttpSession httpSession = inRequest.getSession( false );
        ConfigDto configDto = null;
        try
        {
            financeForm.setPaymentId( Long.parseLong( inRequest.getParameter( "paymentId" ) ) );
            LOGGER.info( "Payment ID::" + financeForm.getPaymentId() );
            httpSession.removeAttribute( CrmAuditLogPojo.class.getSimpleName() );
            configDto = financeManagerBm.getActivityLogs( financeForm );
            if ( CommonValidator.isValidCollection( configDto.getAuditLogPojos() ) )
            {
                httpSession.setAttribute( CrmAuditLogPojo.class.getSimpleName(), configDto.getAuditLogPojos() );
                LOGGER.info( "size of pojo is:" + configDto.getAuditLogPojos().size() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in view Payment Details", e );
        }
        return inMapping.findForward( IActionForwardConstant.VIEW_PAYMENT );
    }

    public ActionForward configPaymentGatewaysPage( ActionMapping inMapping,
                                                    ActionForm inForm,
                                                    HttpServletRequest inRequest,
                                                    HttpServletResponse inResponse )
    {
        LOGGER.info( "inside configPaymentGatewaysPage" );
        FinanceForm financeForm = (FinanceForm) inForm;
        CrmRcaReasonPojo crmRcaReason = new CrmRcaReasonPojo();
        crmRcaReason.setCategory( CRMRCAReason.FINANCE.getStatusCode() );
        crmRcaReason.setSubCategory( CRMParameter.PAYMENT_GATEWAY.getParameter() );
        List<CrmRcaReasonPojo> crmRcaReasonPojos = CRMCacheManager.getMasterData( crmRcaReason );
        if ( !CommonValidator.isValidCollection( crmRcaReasonPojos ) )
        {
            crmRcaReasonPojos = new ArrayList<CrmRcaReasonPojo>();
            crmRcaReason = new CrmRcaReasonPojo();
            crmRcaReason.setCategory( CRMRCAReason.FINANCE.getStatusCode() );
            crmRcaReason.setSubCategory( CRMParameter.PAYMENT_GATEWAY.getParameter() );
            crmRcaReason.setSubSubCategory( "DC" );
            crmRcaReason.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            crmRcaReasonPojos.add( crmRcaReason );
            crmRcaReason = new CrmRcaReasonPojo();
            crmRcaReason.setCategory( CRMRCAReason.FINANCE.getStatusCode() );
            crmRcaReason.setSubCategory( CRMParameter.PAYMENT_GATEWAY.getParameter() );
            crmRcaReason.setSubSubCategory( "CC" );
            crmRcaReason.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            crmRcaReasonPojos.add( crmRcaReason );
            crmRcaReason = new CrmRcaReasonPojo();
            crmRcaReason.setCategory( CRMRCAReason.FINANCE.getStatusCode() );
            crmRcaReason.setSubCategory( CRMParameter.PAYMENT_GATEWAY.getParameter() );
            crmRcaReason.setSubSubCategory( "IB" );
            crmRcaReason.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            crmRcaReasonPojos.add( crmRcaReason );
        }
        financeForm.setPaymentGateways( crmRcaReasonPojos );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_GATEWAYS_CONFIG_PAGE );
    }

    public ActionForward paymentGatewaysConfigure( ActionMapping inMapping,
                                                   ActionForm inForm,
                                                   HttpServletRequest inRequest,
                                                   HttpServletResponse inResponse )
    {
        LOGGER.info( "inside paymentGatewaysConfigure" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        FinanceForm financeForm = (FinanceForm) inForm;
        CrmuserDetailsDto userDto = (CrmuserDetailsDto) inRequest.getSession()
                .getAttribute( IAppConstants.CRM_USER_OBJECT );
        try
        {
            CrmFinanceDto financeDto = getFinanceManagerBm().paymentGatewaysConfigure( financeForm,
                                                                                       userDto.getCrmUserDetailsPojo()
                                                                                               .getUserId() );
            if ( StringUtils.isValidObj( financeDto ) )
            {
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), financeDto.getStatusCode() ) )
                {
                    messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( financeDto.getStatusCode() ) );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( financeDto.getStatusCode() ) );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while,Configure payment mode", ex );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.PAYMENT_GATEWAYS_CONFIG_PAGE );
    }
}