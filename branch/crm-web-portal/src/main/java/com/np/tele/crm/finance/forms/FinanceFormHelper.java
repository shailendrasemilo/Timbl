package com.np.tele.crm.finance.forms;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerRefundsPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class FinanceFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( FinanceFormHelper.class );

    public static void resetFinanceForm( FinanceForm inFinanceForm, String inMethodName )
    {
        LOGGER.info( "in resetFinanceForm method" );
        if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_TRACKING_PAGE )
                || StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_REVERSAL_PAGE ) )
        {
            inFinanceForm.setFromDate( "" );
            inFinanceForm.setToDate( "" );
            inFinanceForm.setProductType( "" );
            inFinanceForm.setCustomerServiceType( "" );
            inFinanceForm.setEntity_type( "" );
            inFinanceForm.setPayment_mode( "" );
            inFinanceForm.setChannel_type( "" );
            inFinanceForm.setCrfId( "" );
            inFinanceForm.setCustomerId( "" );
            inFinanceForm.setCustomerServiceType( "" );
            inFinanceForm.setChangeDynamicVariable( "" );
            inFinanceForm.setSearchPaymentList( null );
            inFinanceForm.setCheque_status( "" );
            inFinanceForm.setInstallationStatus( "" );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_TRACKING ) )
        {
            inFinanceForm.setFromDate( null );
            inFinanceForm.setToDate( null );
            inFinanceForm.setProductType( null );
            inFinanceForm.setCustomerServiceType( null );
            inFinanceForm.setEntity_type( null );
            inFinanceForm.setPayment_mode( null );
            inFinanceForm.setChannel_type( null );
            inFinanceForm.setCrfId( null );
            inFinanceForm.setCustomerId( null );
            inFinanceForm.setCustomerServiceType( null );
            inFinanceForm.setChangeDynamicVariable( null );
            inFinanceForm.setCheque_status( null );
            inFinanceForm.setInstallationStatus( null );
            inFinanceForm.setSearchPaymentList( null );
            //inFinanceForm.setChequeStatusList( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_REVERSAL_POP_UP ) )
        {
            inFinanceForm.setRemarksPojo( new RemarksPojo() );
            inFinanceForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inFinanceForm.setCrmPaymentDetailsPojo( new CrmPaymentDetailsPojo() );
            inFinanceForm.setReversalWOCrf( false );
            inFinanceForm.setDocSrPaymentReversal( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_SEARCH_PAYMENT_REVERSAL ) )
        {
            inFinanceForm.setSearchPaymentList( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_POSTING_PAGE ) )
        {
            inFinanceForm.setCrfId( null );
            inFinanceForm.setCustomerId( null );
            if ( CommonValidator.isValidCollection( inFinanceForm.getCrmPaymentDetailsPojos() ) )
            {
                inFinanceForm.setCrmPaymentDetailsPojos( null );
            }
            if ( StringUtils.isValidObj( inFinanceForm.getCrmPaymentDetailsPojo() ) )
            {
                inFinanceForm.getCrmPaymentDetailsPojo().setInstallationStatus( null );
                inFinanceForm.getCrmPaymentDetailsPojo().setCustomerRecordId( 0 );
                inFinanceForm.getCrmPaymentDetailsPojo().setPaymentMode( null );
                inFinanceForm.setChequeDate( null );
                inFinanceForm.setCrfId( null );
                inFinanceForm.setCustomerId( null );
                inFinanceForm.getCrmPaymentDetailsPojo().setChequeDdNo( null );
                inFinanceForm.getCrmPaymentDetailsPojo().setReceiptNo( null );
                inFinanceForm.getCrmPaymentDetailsPojo().setTransactionId( null );
                inFinanceForm.getCrmPaymentDetailsPojo().setRealzationStatus( null );
            }
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_REVERSAL_PAGE ) )
        {
            inFinanceForm.setCrfId( null );
            inFinanceForm.setCustomerId( null );
            inFinanceForm.setToDate( null );
            inFinanceForm.setFromDate( null );
            inFinanceForm.setSearchPaymentList( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_REVERSAL_POP_UP ) )
        {
            inFinanceForm.setRemarksPojo( new RemarksPojo() );
            inFinanceForm.setCrmCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inFinanceForm.setCrmPaymentDetailsPojo( new CrmPaymentDetailsPojo() );
            inFinanceForm.setReversalWOCrf( false );
            inFinanceForm.setDocSrPaymentReversal( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_SEARCH_PAYMENT_REVERSAL ) )
        {
            inFinanceForm.setSearchPaymentList( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_CHANGE_PAYMENT_STATUS ) )
        {
            inFinanceForm.setBouncingDate( null );
            inFinanceForm.setBouncingReason( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_CHANGE_REALIZATION_STATUS_PAGE ) )
        {
            //inFinanceForm.setCrmPaymentDetailsPojo( null );
            inFinanceForm.setBouncingDate( null );
            inFinanceForm.setBouncingReason( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.MANUALY_UPFRONT_PAYMENT_PAGE ) )
        {
            inFinanceForm.setCrfId( null );
            inFinanceForm.setCrmUpfrontPaymentPojo( new CrmUpfrontPaymentPojo() );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.SEARCH_UPFRONT_PAYMENT_PAGE ) )
        {
            inFinanceForm.setToDate( null );
            inFinanceForm.setFromDate( null );
            inFinanceForm.setCrmUpfrontPaymentPojoList( new ArrayList<CrmUpfrontPaymentPojo>() );
            inFinanceForm.setCrmUpfrontPaymentPojo( new CrmUpfrontPaymentPojo() );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_PAYMENT_POSTING ) )
        {
            if ( StringUtils.equals( inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode(), "online" ) )
            {
                inFinanceForm.setChequeDate( null );
                if ( StringUtils.isValidObj( inFinanceForm.getCrmPaymentDetailsPojo() ) )
                {
                    inFinanceForm.getCrmPaymentDetailsPojo().setRealzationStatus( null );
                }
            }
            if ( StringUtils.equals( inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode(), "cash" ) )
            {
                inFinanceForm.setChequeDate( null );
                if ( StringUtils.isValidObj( inFinanceForm.getCrmPaymentDetailsPojo() ) )
                {
                    inFinanceForm.getCrmPaymentDetailsPojo().setRealzationStatus( null );
                }
            }
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_SUSPENSE_REJECTED_PAYMENET_PAGE )
                || ( StringUtils.equals( inMethodName, IAppConstants.METHOD_VIEW_SUSPENSE_REJECTED_PAYMENET ) ) )
        {
            LOGGER.info( "in resetFinanceForm METHOD_SUSPENSE_REJECTED_PAYMENET_PAGE" );
            inFinanceForm.setCrmCmsPaymentPojos( null );
            if ( StringUtils.isValidObj( inFinanceForm.getCrmCmsPaymentPojo() ) )
            {
                inFinanceForm.getCrmCmsPaymentPojo().setIe2( null );
            }
            else
            {
                inFinanceForm.setCrmCmsPaymentPojo( new CrmCmsPaymentPojo() );
            }
            inFinanceForm.setPaymentDate( null );
            inFinanceForm.setChequeDate( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.SUB_UPFRONT_POPUP_SEARCH ) )
        {
            inFinanceForm.setCrmCustomerDetailsPojos( null );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_SEARCH_UPFRONT_PAYMENT ) )
        {
            inFinanceForm.setCrmUpfrontPaymentPojoList( new ArrayList<CrmUpfrontPaymentPojo>() );
        }
        else if ( StringUtils.equals( inMethodName, IAppConstants.METHOD_REFUND_TRACKING_PAGE ) )
        {
            inFinanceForm.setCustomerId( "" );
            inFinanceForm.setCrfId( "" );
            if ( StringUtils.isValidObj( inFinanceForm.getCrmCustomerRefundsPojo() ) )
            {
                inFinanceForm.setCrmCustomerRefundsPojo( new CrmCustomerRefundsPojo() );
            }
            if ( StringUtils.isValidObj( inFinanceForm.getCrmCustomerRefundsPojos() )
                    && !inFinanceForm.getCrmCustomerRefundsPojos().isEmpty() )
            {
                inFinanceForm.setCrmCustomerRefundsPojos( new ArrayList<CrmCustomerRefundsPojo>() );
            }
        }
    }

    public static void validateForm( String inMethod, ActionErrors inActionError, FinanceForm inFinanceForm )
    {
        Map<String, Object[]> resultMap = null;
        if ( StringUtils.equals( IAppConstants.METHOD_PAYMENT_POSTING, inMethod ) )
        {
            resultMap = ValidationPojoUtil
                    .validateSingleFormProperty( inFinanceForm,
                                                 ICRMValidationCriteriaUtil.FORM_CUSTOMER_SEARCH_CRITERIA, false );
            if ( StringUtils.isNotBlank( inFinanceForm.getPaymentDate() ) )
            {
                inFinanceForm.getCrmPaymentDetailsPojo().setPaymentDate( DateUtils.changeDateFormat( inFinanceForm
                                                                                 .getPaymentDate() ) );
            }
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getCrmPaymentDetailsPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_PAYMENT_CRITERIA_COMMON,
                                                             false );
            }
            LOGGER.info( "Payment Mode........." + inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode() );
            if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
            {
                if ( StringUtils.equals( inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode(),
                                         CRMDisplayListConstants.CASH.getCode() ) )
                {
                    /*if ( StringUtils.startsWith( inFinanceForm.getCrmPaymentDetailsPojo().getReceiptNo(), inFinanceForm
                            .getCrmPaymentDetailsPojo().getEntityType() ) )
                    {*/
                    resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getCrmPaymentDetailsPojo(),
                                                                 ICRMValidationCriteriaUtil.FORM_CASH_PAYMENT_CRITERIA,
                                                                 false );
                    if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                    {
                        CrmRcaReasonPojo master = new CrmRcaReasonPojo();
                        master.setCategory( CRMRCAReason.INA.getStatusCode() );
                        master.setSubCategory( "CashReceipt" );
                        master.setSubSubCategory( inFinanceForm.getCrmPaymentDetailsPojo().getEntityType() );
                        master.setCategoryValue( inFinanceForm.getCrmPaymentDetailsPojo().getReceiptNo() );
                        master.setValueAlias( CRMStatusCode.UNUSED.getStatusCode() );
                        if ( !CRMCacheManager.validInMaster( master ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_INVALID_RECEIPT_NUMBER,
                                                                  inFinanceForm.getCrmPaymentDetailsPojo()
                                                                          .getReceiptNo() ) );
                        }
                    }
                    /*}
                    else
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( CRMServiceCode.CRM703.getStatusCode(),
                                                              CRMDisplayListConstants
                                                                      .getValueByCode( CRMParameter.ENTITY_TYPE_LIST
                                                                              .getParameter(), inFinanceForm
                                                                              .getCrmPaymentDetailsPojo()
                                                                              .getEntityType() ), inFinanceForm
                                                                      .getCrmPaymentDetailsPojo().getEntityType() ) );
                    }*/
                }
                else if ( StringUtils.equals( inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode(),
                                              CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
                {
                    resultMap = ValidationPojoUtil
                            .validateForm( inFinanceForm.getCrmPaymentDetailsPojo(),
                                           ICRMValidationCriteriaUtil.FORM_ONLINE_PAYMENT_CRITERIA, false );
                    if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                    {
                        CrmPaymentDetailsPojo paymentDetailsPojo = new CrmPaymentDetailsPojo();
                        paymentDetailsPojo.setTransactionId( inFinanceForm.getCrmPaymentDetailsPojo()
                                .getTransactionId() );
                        paymentDetailsPojo.setCustomerRecordId( inFinanceForm.getCrmPaymentDetailsPojo()
                                .getCustomerRecordId() );
                        paymentDetailsPojo.setPaymentMode( inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode() );
                        if ( CRMCacheManager.validPaymentTransactionId( paymentDetailsPojo ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_INVALID_TRANSACTION_ID,
                                                                  inFinanceForm.getCrmPaymentDetailsPojo()
                                                                          .getTransactionId() ) );
                        }
                    }
                }
                else if ( StringUtils.equals( inFinanceForm.getCrmPaymentDetailsPojo().getPaymentMode(),
                                              CRMDisplayListConstants.CHEQUE.getCode() ) )
                {
                    if ( StringUtils.isNotBlank( inFinanceForm.getChequeDate() ) )
                    {
                        inFinanceForm.getCrmPaymentDetailsPojo()
                                .setChequeDate( DateUtils.changeDateFormat( inFinanceForm.getChequeDate() ) );
                    }
                    resultMap = ValidationPojoUtil
                            .validateForm( inFinanceForm.getCrmPaymentDetailsPojo(),
                                           ICRMValidationCriteriaUtil.FORM_CHEQUE_PAYMENT_CRITERIA, false );
                    if ( ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
                            && StringUtils.equals( CRMDisplayListConstants.CHEQUE_NOTREALIZED.getCode(), inFinanceForm
                                    .getCrmPaymentDetailsPojo().getRealzationStatus() ) )
                    {
                        if ( StringUtils.isNotBlank( inFinanceForm.getBouncingDate() ) )
                        {
                            inFinanceForm.getCrmPaymentDetailsPojo()
                                    .setBouncingDate( DateUtils.changeDateFormat( inFinanceForm.getBouncingDate() ) );
                        }
                        resultMap = ValidationPojoUtil
                                .validateForm( inFinanceForm.getCrmPaymentDetailsPojo(),
                                               ICRMValidationCriteriaUtil.FORM_CHEQUE_BOUNCE_CRITERIA, false );
                    }
                }
            }
            LOGGER.info( "Validation Result Map for " + IAppConstants.METHOD_PAYMENT_POSTING + ":" + resultMap );
        }
        if ( StringUtils.equals( IAppConstants.METHOD_SUBMIT_UP_FRONT_PAYMENT, inMethod ) )
        {
            LOGGER.info( "The method name is : " + inMethod );
            if ( StringUtils.isNotBlank( inFinanceForm.getCrmUpfrontPaymentPojo().getDisplayChequeDate() ) )
            {
                inFinanceForm.getCrmUpfrontPaymentPojo().setChequeDate( DateUtils.changeDateFormat( inFinanceForm
                                                                                .getCrmUpfrontPaymentPojo()
                                                                                .getDisplayChequeDate() ) );
            }
            resultMap = ValidationPojoUtil
                    .validateForm( inFinanceForm.getCrmUpfrontPaymentPojo(),
                                   ICRMValidationCriteriaUtil.FORM_UPFRONT_PAYMENT_POJO_CRITERIA_COMMON, false );
            LOGGER.info( "The starting result map: " + resultMap );
        }
        ValidationUtil.prepareErrorMessage( inActionError, resultMap );
    }

    public static ActionMessages validateUpfrontDetails( ActionMessages inErrors, FinanceForm inFinanceForm )
    {
        LOGGER.info( " validate upfront payment deatils " );
        Map<String, Object[]> resultMap = null;
        resultMap = ValidationPojoUtil.validateForm( inFinanceForm,
                                                     ICRMValidationCriteriaUtil.FORM_UPFRONT_SEARCH_CRITERIA, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil
                    .validateForm( inFinanceForm.getCrmUpfrontPaymentPojo(),
                                   ICRMValidationCriteriaUtil.FORM_UPFRONT_SEARCH_CRITERIA_FOR_BP, false );
        }
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        return inErrors;
    }

    public static void validatePODFile( ActionMessages inActionErrors, FinanceForm inFinanceForm )
    {
        LOGGER.info( "File : " + inFinanceForm.getFormFile().getFileName() + " Size : "
                + inFinanceForm.getFormFile().getFileSize() );
        // validation before processing
        if ( inFinanceForm.getFormFile().getFileSize() == 0 )
        {
            inActionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_REQUIRED ) );
        }
        else if ( inFinanceForm.getFormFile().getFileSize() > 20480 )
        {
            inActionErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_FILE_SIZE ) );
        }
    }

    public static void validateRefundProcess( ActionMessages inErrors, FinanceForm inFinanceForm )
    {
        LOGGER.info( "validate refund payment process ::" );
        Map<String, Object[]> resultMap = null;
       /* if ( ( !StringUtils.equals( inFinanceForm.getCrmCustomerDetailsPojo().getStatus(),
                                    CRMStatusCode.PDC.getStatusDesc() ) )
                && ( !StringUtils.equals( inFinanceForm.getCrmCustomerDetailsPojo().getStatus(),
                                          CRMStatusCode.CANCELED.getStatusDesc() ) ) )
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM438.getStatusCode() ) );
            return;
        }*/
        resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getCrmCustomerDetailsPojo(),
                                                     ICRMValidationCriteriaUtil.FORM_FINANCE_REFUND_CUSTOMER, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            if ( StringUtils.isEmpty( inFinanceForm.getCrmCustomerRefundsPojo().getEntityType() ) )
            {
                inErrors.add( IAppConstants.APP_ERROR,
                              new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PAYMENT_ENTITY_TYPE ) );
            }
            if ( StringUtils.equals( inFinanceForm.getCrmCustomerRefundsPojo().getRefundMode(),
                                     CRMDisplayListConstants.CHEQUE.getCode() ) )
            {
                resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getCrmCustomerRefundsPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_FINANCE_REFUND_CHEQUE,
                                                             false );
            }
            else if ( StringUtils.equals( inFinanceForm.getCrmCustomerRefundsPojo().getRefundMode(),
                                          CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
            {
                resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getCrmCustomerRefundsPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_FINANCE_REFUND_ONLINE,
                                                             false );
            }
            else
            {
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PAYMENT_MODE,
                                                                          "refund" ) );
            }
        }
        if ( inErrors.isEmpty() && ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() ) )
        {
            resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getCrmCustomerRefundsPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_FINANCE_REFUND, false );
        }
        if ( inErrors.isEmpty() && ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() ) )
        {
            resultMap = ValidationPojoUtil.validateForm( inFinanceForm.getRemarksPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_FINANCE_REMARKS, false );
        }
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
    }

    public static ActionMessages validateCRFCustomerID( ActionMessages inErrors, FinanceForm inFinanceForm )
    {
        return inErrors;
    }
}
