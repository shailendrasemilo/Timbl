package com.np.tele.crm.cap.form;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.cap.bm.CrmCapManagerImpl;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.common.utils.CommonUtils;
import com.np.tele.crm.common.utils.ValidationUtil;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.FileHeaderConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.gis.bm.GISUtils;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.CrmAdditionalDetailsPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCustAadharNumberPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmDocumentDetailsPojo;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmOrderDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class CrmCapFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( CrmCapFormHelper.class );

    public static ActionMessages validateIandAForm( String inMethod,
                                                    ActionMessages inActionError,
                                                    CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "validate Installation and Activatation Form" );
        if ( StringUtils.equals( IAppConstants.METHOD_SAVE_CUSTOMER_BASIC_INFO, inMethod ) )
        {
            LOGGER.info( "validate save customer basic information" );
            commonCrfValidation( inActionError, inCrmCapForm, inMethod );
            if ( inActionError.isEmpty() )
            {
                crfAndCustomerDetailsFullCheck( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                fullInstallationAddress( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                fullBillingAddress( inActionError, inCrmCapForm );
            }
            else
            {
                setBillingGISDetails( inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                fullTeleServPaymentDetails( inActionError, inCrmCapForm, inMethod );
            }
            if ( inActionError.isEmpty()
                    && StringUtils.equals( inCrmCapForm.getOrderDetailsPojo().getCpeStatus(),
                                           CRMDisplayListConstants.NEXTRA_RECOMMENDED.getCode() ) )
            {
                fullTelecomPaymentDetails( inActionError, inCrmCapForm, inMethod );
            }
            if ( inActionError.isEmpty() )
            {
                validateSecurityDepositPayment( inActionError, inCrmCapForm, inMethod );
            }
            if ( inActionError.isEmpty() )
            {
                fullAdditionalInfo( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                validateRemarks( inActionError, inCrmCapForm, inMethod );
            }
        }
        else if ( StringUtils.equals( IAppConstants.SUBMIT_CRF_DETAILS, inMethod ) )
        {
            LOGGER.info( "validate submit crf details" );
            commonCrfValidation( inActionError, inCrmCapForm, inMethod );
            if ( inActionError.isEmpty() )
            {
                crfIdValidation( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                emptyMobnContactNo( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                emptyPlanDetails( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                emptyIdAndAddressProof( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                emptyIndivCustDetails( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() )
            {
                fullAdditionalInfo( inActionError, inCrmCapForm );
            }
            if ( inActionError.isEmpty() && crfAndCustomerDetailsEmptyCheck( inActionError, inCrmCapForm ) )
                crfAndCustomerDetailsFullCheck( inActionError, inCrmCapForm );
            if ( inActionError.isEmpty() && nonEmptyInstallationAddress( inActionError, inCrmCapForm ) )
                fullInstallationAddress( inActionError, inCrmCapForm );
            if ( inActionError.isEmpty() && emptyBillingAddress( inActionError, inCrmCapForm ) )
                fullBillingAddress( inActionError, inCrmCapForm );
            else
                setBillingGISDetails( inCrmCapForm );
            if ( inActionError.isEmpty() )
                fullTeleServPaymentDetails( inActionError, inCrmCapForm, inMethod );
            if ( inActionError.isEmpty()
                    && StringUtils.equals( inCrmCapForm.getOrderDetailsPojo().getCpeStatus(),
                                           CRMDisplayListConstants.NEXTRA_RECOMMENDED.getCode() ) )
            {
                fullTelecomPaymentDetails( inActionError, inCrmCapForm, inMethod );
            }
            if ( inActionError.isEmpty() )
            {
                validateSecurityDepositPayment( inActionError, inCrmCapForm, inMethod );
            }
            if ( inActionError.isEmpty() )
            {
                validateRemarks( inActionError, inCrmCapForm, inMethod );
            }
        }
        else if ( StringUtils.equals( IAppConstants.SUBMIT_CANCEL_CRF, inMethod ) )
        {
            Map<String, Object[]> resultMap = null;
            if ( StringUtils.isBlank( inCrmCapForm.getRemarksPojo().getReasonId() + "" ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CANCEL_CRF_REASON ) );
            }
            else if ( StringUtils.isNotBlank( inCrmCapForm.getCustomerDetailsPojo().getLinkedCRF() )
                    && StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getCrfId(), inCrmCapForm
                            .getCustomerDetailsPojo().getLinkedCRF() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_DUPLICATE_LINK_CRF ) );
            }
            else
            {
                resultMap = ValidationPojoUtil.validateForm( inCrmCapForm.getRemarksPojo(),
                                                             ICRMValidationCriteriaUtil.FORM_CRF_REMARKS_FOR_APPROVE,
                                                             false );
            }
            ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        }
        return inActionError;
    }

    private static void validateRemarks( ActionMessages inActionError, CrmCapForm inCrmCapForm, String inMethod )
    {
        LOGGER.info( "inside validateRemarks method." );
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getCrfStage(),
                                 CRMOperationStages.FT_REJECT.getCode() )
                && StringUtils.isValidObj( inCrmCapForm.getRemarksPojo() ) )
        {
            RemarksPojo remarksPojo = inCrmCapForm.getRemarksPojo();
            remarksPojo.setRemarks( StringUtils.trimToEmpty( remarksPojo.getRemarks() ) );
            if ( StringUtils.equals( IAppConstants.METHOD_SAVE_CUSTOMER_BASIC_INFO, inMethod )
                    && StringUtils.isNotBlank( remarksPojo.getRemarks() )
                    && ( remarksPojo.getRemarks().length() < 2 || remarksPojo.getRemarks().length() > 4000 ) )
            {
                inActionError
                        .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_BLANK_REMARKS ) );
            }
            else if ( StringUtils.equals( IAppConstants.SUBMIT_CRF_DETAILS, inMethod )
                    && ( ( remarksPojo.getRemarks().length() < 2 || remarksPojo.getRemarks().length() > 4000 ) ) )
            {
                inActionError
                        .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_BLANK_REMARKS ) );
            }
        }
    }

    private static ActionMessages fullTelecomPaymentDetails( ActionMessages inActionError,
                                                             CrmCapForm inCrmCapForm,
                                                             String inMethod )
    {
        LOGGER.info( "inside fullTelecomPaymentDetails method." );
        if ( StringUtils.isNotEmpty( inCrmCapForm.getTelecommunicationPayment().getPaymentMode() ) )
        {
            if ( StringUtils.equals( inCrmCapForm.getTelecommunicationPayment().getPaymentMode(),
                                     CRMDisplayListConstants.CHEQUE.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getTelecommunicationPayment().getPaymentMode() );
                if ( StringUtils.isNotEmpty( inCrmCapForm.getTelecommunicationPayment().getChequeDdNo() ) )
                {
                    LOGGER.info( "cheque number is not empty"
                            + inCrmCapForm.getTelecommunicationPayment().getChequeDdNo() );
                    if ( inCrmCapForm.getTelecommunicationPayment().getChequeDdNo().length() > 15
                            || !StringUtils.isNumeric( inCrmCapForm.getTelecommunicationPayment().getChequeDdNo() )
                            || ( StringUtils.numericValue( inCrmCapForm.getTelecommunicationPayment().getChequeDdNo() ) == 0 ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LENGTH_DD_NUMBER ) );
                    }
                }
                else
                {
                    LOGGER.info( "cheque number empty " );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CHEQUE_DD_NUMBER ) );
                }
                if ( StringUtils.isEmpty( inCrmCapForm.getTelecommunicationPayment().getBankName() ) )
                {
                    LOGGER.info( "bank name not selected" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BANK_NAME ) );
                }
                if ( StringUtils.isNotEmpty( inCrmCapForm.getTelecommunicationPayment().getBankBranch() ) )
                {
                    LOGGER.info( "bank branch is not empty" + inCrmCapForm.getTeleservicesPayment().getBankBranch() );
                    if ( inCrmCapForm.getTelecommunicationPayment().getBankBranch().length() > 45
                            || StringUtils.isNumeric( inCrmCapForm.getTelecommunicationPayment().getBankBranch() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_BANK_BRANCH_LENGTH ) );
                    }
                }
                if ( StringUtils.isEmpty( inCrmCapForm.getTelecommunicationPayment().getDisplayChequeDate() ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CHEQUE_DATE ) );
                }
                else if ( StringUtils.isNotEmpty( inCrmCapForm.getTelecommunicationPayment().getDisplayChequeDate() ) )
                {
                    Date chequeDate;
                    try
                    {
                        chequeDate = IDateConstants.SDF_DD_MMM_YYYY.parse( inCrmCapForm.getTelecommunicationPayment()
                                .getDisplayChequeDate() );
                        long diff = DateUtils.daysDiff( chequeDate, DateUtils.getCurrentDate() );
                        LOGGER.info( "CRF ChequeDate difference: " + ( diff ) );
                        if ( diff < 0 || diff > 90 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_CHEQUE_DATE_VALIDITY ) );
                        }
                    }
                    catch ( ParseException ex )
                    {
                        LOGGER.error( ex );
                    }
                }
                if ( StringUtils.isNotEmpty( inCrmCapForm.getTelecommunicationPayment().getBankCity() ) )
                {
                    LOGGER.info( "bank city is not empty" + inCrmCapForm.getTelecommunicationPayment().getBankCity() );
                    if ( inCrmCapForm.getTelecommunicationPayment().getBankCity().length() > 35
                            || StringUtils.isNumeric( inCrmCapForm.getTelecommunicationPayment().getBankCity() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_BANK_CITY_LENGTH ) );
                    }
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getTelecommunicationPayment().getPaymentMode(),
                                          CRMDisplayListConstants.CASH.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getTelecommunicationPayment().getPaymentMode() );
                LOGGER.info( "Aadhar no::" + inCrmCapForm.getAadharNumberPojo().getAadharNumber() );
                if ( StringUtils.isEmpty( inCrmCapForm.getAadharNumberPojo().getAadharNumber() ) )
                {
                    if ( StringUtils.isEmpty( inCrmCapForm.getTelecommunicationPayment().getReceiptNo() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RECEIPT_NUMBER ) );
                    }
                    else
                    {
                        /*if ( !StringUtils.startsWith( inCrmCapForm.getTelecommunicationPayment().getReceiptNo(),
                                                  CRMDisplayListConstants.TELESOLUTIONS.getCode() ) )
                        {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( CRMServiceCode.CRM703.getStatusCode(),
                                                              CRMDisplayListConstants.TELESOLUTIONS.getValue(),
                                                              CRMDisplayListConstants.TELESOLUTIONS.getCode() ) );
                        }*/
                        if ( inActionError.isEmpty() )
                        {
                            CrmRcaReasonPojo master = new CrmRcaReasonPojo();
                            master.setCategory( CRMRCAReason.INA.getStatusCode() );
                            master.setSubCategory( "CashReceipt" );
                            master.setSubSubCategory( CRMDisplayListConstants.TELESOLUTIONS.getCode() );
                            master.setCategoryValue( inCrmCapForm.getTelecommunicationPayment().getReceiptNo() );
                            if ( !CRMCacheManager.validInMaster( master ) )
                            {
                                inActionError.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( IPropertiesConstant.ERROR_INVALID_RECEIPT_NUMBER,
                                                                      inCrmCapForm.getTelecommunicationPayment()
                                                                              .getReceiptNo() ) );
                            }
                        }
                    }
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getTelecommunicationPayment().getPaymentMode(),
                                          CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getTelecommunicationPayment().getPaymentMode() );
                if ( StringUtils.isNotEmpty( inCrmCapForm.getTelecommunicationPayment().getTransactionId() ) )
                {
                    LOGGER.info( "Transation id is not empty"
                            + inCrmCapForm.getTelecommunicationPayment().getTransactionId() );
                    if ( inCrmCapForm.getTelecommunicationPayment().getTransactionId().length() > 45 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_TRANSACTION_ID_LENGTH ) );
                    }
                }
                else
                {
                    LOGGER.info( "transaction id is empty" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_TRANSACTION_ID ) );
                }
            }
        }
        else if ( StringUtils.isValidObj( inCrmCapForm.getTelecommunicationPayment() )
                && StringUtils.isValidObj( inCrmCapForm.getTelecommunicationPayment().getAmount() )
                && inCrmCapForm.getTelecommunicationPayment().getAmount() > 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PAYMENT_MODE,
                                                  CRMDisplayListConstants.TELESOLUTIONS.getValue() ) );
        }
        return inActionError;
    }

    private static ActionMessages validateSecurityDepositPayment( ActionMessages inActionError,
                                                                  CrmCapForm inCrmCapForm,
                                                                  String inMethod )
    {
        LOGGER.info( "inside fullTelecomPaymentDetails method." );
        if ( StringUtils.isNotEmpty( inCrmCapForm.getSecurityDepositPayment().getPaymentMode() ) )
        {
            if ( StringUtils.equals( inCrmCapForm.getSecurityDepositPayment().getPaymentMode(),
                                     CRMDisplayListConstants.CHEQUE.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getSecurityDepositPayment().getPaymentMode() );
                if ( StringUtils.isNotEmpty( inCrmCapForm.getSecurityDepositPayment().getChequeDdNo() ) )
                {
                    LOGGER.info( "cheque number is not empty"
                            + inCrmCapForm.getSecurityDepositPayment().getChequeDdNo() );
                    if ( inCrmCapForm.getSecurityDepositPayment().getChequeDdNo().length() > 15
                            || !StringUtils.isNumeric( inCrmCapForm.getSecurityDepositPayment().getChequeDdNo() )
                            || ( StringUtils.numericValue( inCrmCapForm.getSecurityDepositPayment().getChequeDdNo() ) == 0 ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LENGTH_DD_NUMBER ) );
                    }
                }
                else
                {
                    LOGGER.info( "cheque number empty " );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CHEQUE_DD_NUMBER ) );
                }
                if ( StringUtils.isEmpty( inCrmCapForm.getSecurityDepositPayment().getBankName() ) )
                {
                    LOGGER.info( "bank name not selected" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BANK_NAME ) );
                }
                if ( StringUtils.isNotEmpty( inCrmCapForm.getSecurityDepositPayment().getBankBranch() ) )
                {
                    LOGGER.info( "bank branch is not empty" + inCrmCapForm.getTeleservicesPayment().getBankBranch() );
                    if ( inCrmCapForm.getSecurityDepositPayment().getBankBranch().length() > 45
                            || StringUtils.isNumeric( inCrmCapForm.getSecurityDepositPayment().getBankBranch() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_BANK_BRANCH_LENGTH ) );
                    }
                }
                if ( StringUtils.isEmpty( inCrmCapForm.getSecurityDepositPayment().getDisplayChequeDate() ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CHEQUE_DATE ) );
                }
                else if ( StringUtils.isNotEmpty( inCrmCapForm.getSecurityDepositPayment().getDisplayChequeDate() ) )
                {
                    Date chequeDate;
                    try
                    {
                        chequeDate = IDateConstants.SDF_DD_MMM_YYYY.parse( inCrmCapForm.getSecurityDepositPayment()
                                .getDisplayChequeDate() );
                        long diff = DateUtils.daysDiff( chequeDate, DateUtils.getCurrentDate() );
                        LOGGER.info( "CRF ChequeDate difference: " + ( diff ) );
                        if ( diff < 0 || diff > 90 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_CHEQUE_DATE_VALIDITY ) );
                        }
                    }
                    catch ( ParseException ex )
                    {
                        LOGGER.error( ex );
                    }
                }
                if ( StringUtils.isNotEmpty( inCrmCapForm.getSecurityDepositPayment().getBankCity() ) )
                {
                    LOGGER.info( "bank city is not empty" + inCrmCapForm.getSecurityDepositPayment().getBankCity() );
                    if ( inCrmCapForm.getSecurityDepositPayment().getBankCity().length() > 35
                            || StringUtils.isNumeric( inCrmCapForm.getSecurityDepositPayment().getBankCity() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_BANK_CITY_LENGTH ) );
                    }
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getSecurityDepositPayment().getPaymentMode(),
                                          CRMDisplayListConstants.CASH.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getSecurityDepositPayment().getPaymentMode() );
                LOGGER.info( "Aadhar no: " + inCrmCapForm.getAadharNumberPojo().getAadharNumber() );
                if ( StringUtils.isEmpty( inCrmCapForm.getAadharNumberPojo().getAadharNumber() ) )
                {
                    if ( StringUtils.isEmpty( inCrmCapForm.getSecurityDepositPayment().getReceiptNo() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RECEIPT_NUMBER ) );
                    }
                    else
                    {
                        /*if ( !StringUtils.startsWith( inCrmCapForm.getSecurityDepositPayment().getReceiptNo(),
                                                  CRMDisplayListConstants.TELESERVICES.getCode() ) )
                        {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( CRMServiceCode.CRM703.getStatusCode(),
                                                              CRMDisplayListConstants.SECURITY_DEPOSIT.getValue(),
                                                              CRMDisplayListConstants.TELESERVICES.getCode() ) );
                        }*/
                        if ( StringUtils.equals( inCrmCapForm.getTeleservicesPayment().getPaymentMode(),
                                                 CRMDisplayListConstants.CASH.getCode() )
                                && StringUtils.equals( inCrmCapForm.getTeleservicesPayment().getReceiptNo(),
                                                       inCrmCapForm.getSecurityDepositPayment().getReceiptNo() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( CRMServiceCode.CRM611.getStatusCode(), inCrmCapForm
                                                       .getSecurityDepositPayment().getReceiptNo() ) );
                        }
                        if ( inActionError.isEmpty() )
                        {
                            CrmRcaReasonPojo master = new CrmRcaReasonPojo();
                            master.setCategory( CRMRCAReason.INA.getStatusCode() );
                            master.setSubCategory( "CashReceipt" );
                            master.setSubSubCategory( CRMDisplayListConstants.TELESERVICES.getCode() );
                            master.setCategoryValue( inCrmCapForm.getSecurityDepositPayment().getReceiptNo() );
                            if ( !CRMCacheManager.validInMaster( master ) )
                            {
                                inActionError.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( IPropertiesConstant.ERROR_INVALID_RECEIPT_NUMBER,
                                                                      inCrmCapForm.getSecurityDepositPayment()
                                                                              .getReceiptNo() ) );
                            }
                        }
                    }
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getSecurityDepositPayment().getPaymentMode(),
                                          CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getSecurityDepositPayment().getPaymentMode() );
                if ( StringUtils.isNotEmpty( inCrmCapForm.getSecurityDepositPayment().getTransactionId() ) )
                {
                    LOGGER.info( "Transation id is not empty"
                            + inCrmCapForm.getSecurityDepositPayment().getTransactionId() );
                    if ( inCrmCapForm.getSecurityDepositPayment().getTransactionId().length() > 45 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_TRANSACTION_ID_LENGTH ) );
                    }
                }
                else
                {
                    LOGGER.info( "transaction id is empty" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_TRANSACTION_ID ) );
                }
            }
        }
        else if ( StringUtils.isValidObj( inCrmCapForm.getSecurityDepositPayment() )
                && StringUtils.isValidObj( inCrmCapForm.getSecurityDepositPayment().getAmount() )
                && inCrmCapForm.getSecurityDepositPayment().getAmount() > 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PAYMENT_MODE,
                                                  CRMDisplayListConstants.SECURITY_DEPOSIT.getValue() ) );
        }
        return inActionError;
    }

    private static ActionMessages fullTeleServPaymentDetails( ActionMessages inActionError,
                                                              CrmCapForm inCrmCapForm,
                                                              String inMethod )
    {
        LOGGER.info( "Inside fullTeleServPaymentDetails method" );
        CrmPlanMasterPojo basePlanDetails = CommonUtils.getPlanDetails( inCrmCapForm.getPlanService(), inCrmCapForm
                .getPlanDetailsPojo().getBasePlanCode(), CRMDisplayListConstants.BASE_PLAN.getCode() );
        double miniSecurity = 0;
        double miniRental = 0;
        double miniTelSolnAmount = 0;
        boolean toValidate = true;
        if ( StringUtils.isValidObj( basePlanDetails ) )
        {
            miniSecurity = basePlanDetails.getSecurityDeposit();
            miniTelSolnAmount = basePlanDetails.getAdvTelsolnPayment();
            miniRental = basePlanDetails.getAdvTelservPayment();
            if ( StringUtils.equals( basePlanDetails.getAddonAllowedYn(), "N" )
                    || StringUtils.equals( inCrmCapForm.getPlanDetailsPojo().getAddOnPlanCode(), "0" ) )
            {
                inCrmCapForm.getPlanDetailsPojo().setAddOnPlanCode( null );
            }
            CrmPlanMasterPojo addOnPlanDetails = CommonUtils.getPlanDetails( inCrmCapForm.getPlanService(),
                                                                             inCrmCapForm.getPlanDetailsPojo()
                                                                                     .getAddOnPlanCode(),
                                                                             CRMDisplayListConstants.ADDON_PLAN
                                                                                     .getCode() );
            if ( StringUtils.isValidObj( addOnPlanDetails )
                    && StringUtils.equals( CRMDisplayListConstants.PRE_PAID.getCode(), inCrmCapForm
                            .getCustomerDetailsPojo().getServiceType() ) )
            {
                miniRental = addOnPlanDetails.getRentInclTax();
            }
        }
        double miniTotalRental = miniRental;
        LOGGER.info( "minimum Security" + miniSecurity );
        LOGGER.info( "MiniRental ::" + miniRental );
        LOGGER.info( "MinitotalRental ::" + miniTotalRental );
        LOGGER.info( "miniTelesolutionAmount :" + miniTelSolnAmount );
        inCrmCapForm.getTeleservicesPayment().setAmount( inCrmCapForm.getTeleservicesPayment().getActivationCharges() );
        if ( StringUtils.equals( CRMDisplayListConstants.NEXTRA_RECOMMENDED.getCode(), inCrmCapForm
                .getOrderDetailsPojo().getCpeStatus() )
                && miniTelSolnAmount > 0 )
        {
            if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getProduct(),
                                     CRMDisplayListConstants.BROADBAND.getCode() )
                    && StringUtils.equals( CRMDisplayListConstants.POST_PAID.getCode(), inCrmCapForm
                            .getCustomerDetailsPojo().getServiceType() ) )
            {
                toValidate = false;
            }
            if ( inCrmCapForm.getTelecommunicationPayment().getAmount() < miniTelSolnAmount )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_NS_AMOUNT_MINIMUM_VALUE,
                                                      miniTelSolnAmount ) );
            }
        }
        if ( StringUtils.isValidObj( inCrmCapForm.getSecurityDepositPayment() )
                && StringUtils.isValidObj( inCrmCapForm.getSecurityDepositPayment().getAmount() )
                && inCrmCapForm.getSecurityDepositPayment().getAmount() > 0 )
        {
            inCrmCapForm.getTeleservicesPayment().setSecurityCharges( inCrmCapForm.getSecurityDepositPayment()
                                                                              .getAmount() );
        }
        if ( toValidate && inActionError.isEmpty()
                && inCrmCapForm.getTeleservicesPayment().getSecurityCharges() < miniSecurity )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_AMOUNT_MINIMUM_VALUE,
                                                  IAppConstants.SECURITY_DEPOSIT ) );
        }
        if ( toValidate && inActionError.isEmpty() )
        {
            if ( StringUtils.equals( CRMDisplayListConstants.POST_PAID.getCode(), inCrmCapForm.getCustomerDetailsPojo()
                    .getServiceType() ) )
            {
                if ( inCrmCapForm.getTeleservicesPayment().getAmount() < miniTotalRental )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_NT_AMOUNT_MINIMUM_VALUE,
                                                          miniTotalRental ) );
                }
            }
            else
            {
                if ( inCrmCapForm.getTeleservicesPayment().getAmount() < miniTotalRental - 1 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_NT_AMOUNT_MINIMUM_VALUE,
                                                          miniTotalRental - 1 ) );
                }
            }
        }
        if ( inActionError.isEmpty() && StringUtils.isNotEmpty( inCrmCapForm.getTeleservicesPayment().getPaymentMode() ) )
        {
            if ( StringUtils.equals( inCrmCapForm.getTeleservicesPayment().getPaymentMode(),
                                     CRMDisplayListConstants.CHEQUE.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getTeleservicesPayment().getPaymentMode() );
                if ( StringUtils.isNotEmpty( inCrmCapForm.getTeleservicesPayment().getChequeDdNo() ) )
                {
                    LOGGER.info( "cheque number is not empty " + inCrmCapForm.getTeleservicesPayment().getChequeDdNo() );
                    if ( inCrmCapForm.getTeleservicesPayment().getChequeDdNo().length() > 6
                            || !StringUtils.isNumeric( inCrmCapForm.getTeleservicesPayment().getChequeDdNo() )
                            || ( StringUtils.numericValue( inCrmCapForm.getTeleservicesPayment().getChequeDdNo() ) == 0 ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LENGTH_DD_NUMBER ) );
                    }
                }
                else
                {
                    LOGGER.info( "cheque number is empty" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CHEQUE_DD_NUMBER ) );
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isEmpty( inCrmCapForm.getTeleservicesPayment().getBankName() ) )
                {
                    LOGGER.info( "Bank not selected " );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BANK_NAME ) );
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getTeleservicesPayment().getBankBranch() ) )
                {
                    LOGGER.info( "bank branch is not empty " + inCrmCapForm.getTeleservicesPayment().getBankBranch() );
                    if ( inCrmCapForm.getTeleservicesPayment().getBankBranch().length() > 45
                            || StringUtils.isNumeric( inCrmCapForm.getTeleservicesPayment().getBankBranch() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_BANK_BRANCH_LENGTH ) );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isEmpty( inCrmCapForm.getTeleservicesPayment().getDisplayChequeDate() ) )
                {
                    LOGGER.info( "empty cheque date" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CHEQUE_DATE ) );
                }
                else if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getTeleservicesPayment().getDisplayChequeDate() ) )
                {
                    Date chequeDate;
                    try
                    {
                        chequeDate = IDateConstants.SDF_DD_MMM_YYYY.parse( inCrmCapForm.getTeleservicesPayment()
                                .getDisplayChequeDate() );
                        long diff = DateUtils.daysDiff( chequeDate, DateUtils.getCurrentDate() );
                        LOGGER.info( "CRF ChequeDate difference: " + ( diff ) );
                        if ( diff < 0 || diff > 90 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_CHEQUE_DATE_VALIDITY ) );
                        }
                    }
                    catch ( ParseException ex )
                    {
                        LOGGER.error( ex );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getTeleservicesPayment().getBankCity() ) )
                {
                    LOGGER.info( "bank city name: " + inCrmCapForm.getTeleservicesPayment().getBankCity() );
                    if ( inCrmCapForm.getTeleservicesPayment().getBankCity().length() > 35
                            || StringUtils.isNumeric( inCrmCapForm.getTeleservicesPayment().getBankCity() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_BANK_CITY_LENGTH ) );
                    }
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getTeleservicesPayment().getPaymentMode(),
                                          CRMDisplayListConstants.CASH.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getTeleservicesPayment().getPaymentMode() );
                LOGGER.info( "Aadhar no: " + inCrmCapForm.getAadharNumberPojo().getAadharNumber() );
                if ( StringUtils.isEmpty( inCrmCapForm.getAadharNumberPojo().getAadharNumber() ) )
                {
                    if ( StringUtils.isEmpty( inCrmCapForm.getTeleservicesPayment().getReceiptNo() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_EMPTY_RECEIPT_NUMBER ) );
                    }
                    else
                    {
                        /* if ( !StringUtils.startsWith( inCrmCapForm.getTeleservicesPayment().getReceiptNo(),
                                                   CRMDisplayListConstants.TELESERVICES.getCode() ) )
                        {
                         inActionError.add( IAppConstants.APP_ERROR,
                                            new ActionMessage( CRMServiceCode.CRM703.getStatusCode(),
                                                               CRMDisplayListConstants.TELESERVICES.getValue(),
                                                               CRMDisplayListConstants.TELESERVICES.getCode() ) );
                        }*/
                        if ( inActionError.isEmpty() )
                        {
                            CrmRcaReasonPojo master = new CrmRcaReasonPojo();
                            master.setCategory( CRMRCAReason.INA.getStatusCode() );
                            master.setSubCategory( "CashReceipt" );
                            master.setSubSubCategory( CRMDisplayListConstants.TELESERVICES.getCode() );
                            master.setCategoryValue( inCrmCapForm.getTeleservicesPayment().getReceiptNo() );
                            if ( !CRMCacheManager.validInMaster( master ) )
                            {
                                inActionError.add( IAppConstants.APP_ERROR,
                                                   new ActionMessage( IPropertiesConstant.ERROR_INVALID_RECEIPT_NUMBER,
                                                                      inCrmCapForm.getTeleservicesPayment()
                                                                              .getReceiptNo() ) );
                            }
                        }
                    }
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getTeleservicesPayment().getPaymentMode(),
                                          CRMDisplayListConstants.ONLINE_PAYMENT.getCode() ) )
            {
                LOGGER.info( "payment mode is: " + inCrmCapForm.getTeleservicesPayment().getPaymentMode() );
                if ( StringUtils.isNotEmpty( inCrmCapForm.getTeleservicesPayment().getTransactionId() ) )
                {
                    LOGGER.info( "Transation id is not empty"
                            + inCrmCapForm.getTeleservicesPayment().getTransactionId() );
                    if ( inCrmCapForm.getTeleservicesPayment().getTransactionId().length() > 45 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_TRANSACTION_ID_LENGTH ) );
                    }
                }
                else
                {
                    LOGGER.info( "Transaction id is empty" );
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_TRANSACTION_ID ) );
                }
            }
        }
        else if ( inActionError.isEmpty() && toValidate
                && StringUtils.isValidObj( inCrmCapForm.getTeleservicesPayment() )
                && StringUtils.isValidObj( inCrmCapForm.getTeleservicesPayment().getAmount() )
                && inCrmCapForm.getTeleservicesPayment().getAmount() > 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PAYMENT_MODE,
                                                  CRMDisplayListConstants.TELESERVICES.getValue() ) );
        }
        return inActionError;
    }

    private static ActionMessages fullBillingAddress( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        if ( StringUtils.isNotEmpty( inCrmCapForm.getBillingAddressPojo().getAddLine1() )
                || StringUtils.isNotEmpty( inCrmCapForm.getBillingAddressPojo().getAddLine2() )
                || StringUtils.isNotEmpty( inCrmCapForm.getBillingAddressPojo().getAddLine3() ) )
        {
            if ( inCrmCapForm.getBillingAddressPojo().getAddLine1().length() > 60
                    || inCrmCapForm.getBillingAddressPojo().getAddLine1().length() > 60
                    || inCrmCapForm.getBillingAddressPojo().getAddLine1().length() > 60 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_ADDRESS_LENGTH ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getBillingAddressPojo().getLandmark() ) )
        {
            if ( inCrmCapForm.getBillingAddressPojo().getLandmark().length() > 45 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LANDMARK_LENGTH ) );
            }
        }
        if ( inCrmCapForm.getBillingAddressPojo().getPincode() > 0 )
        {
            if ( String.valueOf( inCrmCapForm.getBillingAddressPojo().getPincode() ).length() != 6 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_PINCODE_LENGTH ) );
            }
        }
        if ( inCrmCapForm.getBillingAddressPojo().getContactNo() > 0 )
        {
            String contactNum = String.valueOf( inCrmCapForm.getBillingAddressPojo().getContactNo() );
            LOGGER.info( "The billing contact number is : " + contactNum );
            if ( contactNum.length() != 10 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_TELEPHONE_NUMBER_LENGTH ) );
            }
        }
        setBillingGISDetails( inCrmCapForm );
        return inActionError;
    }

    private static void setBillingGISDetails( CrmCapForm inCrmCapForm )
    {
        CrmAddressDetailsPojo inBillingAddress = inCrmCapForm.getBillingAddressPojo();
        StatePojo statePojo = GISUtils.getState( inBillingAddress.getStateName() );
        if ( StringUtils.isValidObj( statePojo ) )
        {
            inCrmCapForm.setCityList( statePojo.getCities() );
        }
    }

    private static ActionMessages fullInstallationAddress( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "Inside fullInstallationAddress validation method: " );
        if ( StringUtils.isNotEmpty( inCrmCapForm.getInstallationAddressPojo().getAddLine1() )
                || StringUtils.isNotEmpty( inCrmCapForm.getInstallationAddressPojo().getAddLine2() )
                || StringUtils.isNotEmpty( inCrmCapForm.getInstallationAddressPojo().getAddLine3() ) )
        {
            StringUtils.removeStart( inCrmCapForm.getInstallationAddressPojo().getAddLine1(), IAppConstants.COMMA );
            StringUtils.removeStart( inCrmCapForm.getInstallationAddressPojo().getAddLine2(), IAppConstants.COMMA );
            StringUtils.removeStart( inCrmCapForm.getInstallationAddressPojo().getAddLine3(), IAppConstants.COMMA );
            if ( inCrmCapForm.getInstallationAddressPojo().getAddLine1().length() > 60
                    || inCrmCapForm.getInstallationAddressPojo().getAddLine2().length() > 60
                    || inCrmCapForm.getInstallationAddressPojo().getAddLine3().length() > 60 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_ADDRESS_LENGTH ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getInstallationAddressPojo().getLandmark() ) )
        {
            if ( inCrmCapForm.getInstallationAddressPojo().getLandmark().length() > 45 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LANDMARK_LENGTH ) );
            }
        }
        if ( inCrmCapForm.getInstallationAddressPojo().getPincode() > 0 )
        {
            if ( String.valueOf( inCrmCapForm.getInstallationAddressPojo().getPincode() ).length() != 6 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_PINCODE_LENGTH ) );
            }
        }
        if ( inCrmCapForm.getInstallationAddressPojo().getContactNo() > 0 )
        {
            String contactNum = String.valueOf( inCrmCapForm.getInstallationAddressPojo().getContactNo() );
            LOGGER.info( "The installation contact number is : " + contactNum );
            LOGGER.info( "The installation contact number starts with is : "
                    + StringUtils.startsWith( contactNum, String.valueOf( IAppConstants.ZERO ) ) );
            if ( contactNum.length() != 10 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_TELEPHONE_NUMBER_LENGTH ) );
            }
        }
        return inActionError;
    }

    private static ActionMessages crfAndCustomerDetailsFullCheck( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "crfAndCustomerDetailsFullCheck method: " );
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustEmailId() ) )
        {
            if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_EMAIL.getPattern(), inCrmCapForm
                    .getCustomerDetailsPojo().getCustEmailId() )
                    || inCrmCapForm.getCustomerDetailsPojo().getCustEmailId().length() > 256 )
            {
                inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( CRMRegex.PATTERN_EMAIL.getErrorKey(),
                                                                               inCrmCapForm.getCustomerDetailsPojo()
                                                                                       .getCustEmailId() ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getAltEmailId() ) )
        {
            if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_ALT_EMAIL.getPattern(), inCrmCapForm
                    .getCustomerDetailsPojo().getAltEmailId() )
                    || inCrmCapForm.getCustomerDetailsPojo().getAltEmailId().length() > 256 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( CRMRegex.PATTERN_ALT_EMAIL.getErrorKey(), inCrmCapForm
                                           .getCustomerDetailsPojo().getAltEmailId() ) );
            }
        }
        if ( inCrmCapForm.getCustomerDetailsPojo().getCustMobileNo() > 0 )
        {
            LOGGER.info( "customer mobile number is numeric " + inCrmCapForm.getCustomerDetailsPojo().getCustMobileNo() );
            String contactNum = String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getCustMobileNo() );
            String startNos = ValidationUtil.validateInputMobile( contactNum );
            if ( contactNum.length() != FileHeaderConstants.LMS2.getSize() )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_LENGTH ) );
            }
            else if ( !StringUtils.isEmpty( startNos ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH, startNos ) );
            }
        }
        if ( inCrmCapForm.getCustomerDetailsPojo().getRmn() > 0 )
        {
            String contactNum = String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getRmn() );
            String startNos = ValidationUtil.validateInputMobile( contactNum );
            if ( contactNum.length() != FileHeaderConstants.LMS2.getSize() )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_LENGTH ) );
            }
            else if ( !StringUtils.isEmpty( startNos ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH, startNos ) );
            }
        }
        if ( inCrmCapForm.getCustomerDetailsPojo().getRtn() > 0 )
        {
            String contactNum = String.valueOf( inCrmCapForm.getCustomerDetailsPojo().getRtn() );
            LOGGER.info( "The RTn contact number is : " + contactNum );
            if ( contactNum.length() != 10 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_TELEPHONE_NUMBER_LENGTH ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustPanGirNo() ) )
        {
            if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_VALIDATE_PAN_NO.getPattern(), inCrmCapForm
                    .getCustomerDetailsPojo().getCustPanGirNo() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_PAN_NUMBER ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getAadharNo() ) )
        {
            if ( inCrmCapForm.getCustomerDetailsPojo().getAadharNo().length() != 12 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_AADHAR_NO_LENGTH ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getConnectionType() ) )
        {
            if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                     CRMDisplayListConstants.INDIVIDUAL.getCode() ) )
            {
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getFhFname() ) )
                {
                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_1_45.getPattern(), inCrmCapForm
                            .getCustomerDetailsPojo().getFhFname() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_NAME_LENGTH_BETWEEN_1_45,
                                                              IAppConstants.FATHER_HUSBAND,
                                                              IAppConstants.FIRST ) );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getFhLname() ) )
                {
                    //                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_45.getPattern(), inCrmCapForm
                    //                            .getCustomerDetailsPojo().getFhLname() ) )
                    if ( inCrmCapForm.getCustomerDetailsPojo().getFhLname().length() > 45 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LAST_NAME_LENGTH,
                                                              IAppConstants.FATHER_HUSBAND,
                                                              IAppConstants.LAST,
                                                              45 ) );
                    }
                }
                if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getNationality(), IAppConstants.OTHER ) )
                {
                    if ( StringUtils.isNotEmpty( inCrmCapForm.getNationalityDetailsPojo().getPassportNo() ) )
                    {
                        if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_PASSPORT_NUMBER.getPattern(),
                                                             inCrmCapForm.getNationalityDetailsPojo().getPassportNo() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_PASSPORT_NUMBER ) );
                        }
                    }
                    if ( StringUtils.isNotEmpty( inCrmCapForm.getNationalityDetailsPojo().getLocalCpFname() ) )
                    {
                        if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_1_45.getPattern(), inCrmCapForm
                                .getNationalityDetailsPojo().getLocalCpFname() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_NAME_LENGTH_BETWEEN_1_45,
                                                                  IAppConstants.LOCAL_REFERENCE,
                                                                  IAppConstants.FIRST ) );
                        }
                    }
                    if ( StringUtils.isNotEmpty( inCrmCapForm.getNationalityDetailsPojo().getLocalCpLname() ) )
                    {
                        //                        if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_45.getPattern(), inCrmCapForm
                        //                                .getNationalityDetailsPojo().getLocalCpLname() ) )
                        if ( inCrmCapForm.getNationalityDetailsPojo().getLocalCpLname().length() > 45 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_LAST_NAME_LENGTH,
                                                                  IAppConstants.LOCAL_REFERENCE,
                                                                  IAppConstants.LAST,
                                                                  45 ) );
                        }
                    }
                    if ( inCrmCapForm.getNationalityDetailsPojo().getLocalCpMobileNo() > 0 )
                    {
                        String contactNum = String.valueOf( inCrmCapForm.getNationalityDetailsPojo()
                                .getLocalCpMobileNo() );
                        String startNos = ValidationUtil.validateInputMobile( contactNum );
                        if ( contactNum.length() != FileHeaderConstants.LMS2.getSize() )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_LEAD_CONTACT_LENGTH ) );
                        }
                        if ( !StringUtils.isEmpty( startNos ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_START_WITH,
                                                                  startNos ) );
                        }
                    }
                    if ( StringUtils.isNotEmpty( inCrmCapForm.getNationalityDetailsPojo().getDisplayVisaValidity() ) )
                    {
                        if ( DateUtils
                                .getDate( inCrmCapForm.getNationalityDetailsPojo().getDisplayVisaValidity(),
                                          "dd-MMM-yyyy" ).getTime()
                                .before( DateUtils.getFutureEndDate( 30, Calendar.DAY_OF_YEAR ).getTime() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_VISA_VALIDITY ) );
                        }
                    }
                    /*if ( StringUtils.isNotEmpty( inCrmCapForm.getNationalityDetailsPojo().getDisplayPassportValidity() ) )
                    {
                        if ( DateUtils
                                .getDate( inCrmCapForm.getNationalityDetailsPojo().getDisplayPassportValidity(),
                                          "dd-MMM-yyyy" ).getTime()
                                .before( DateUtils.getFutureEndDate( 90, Calendar.DAY_OF_YEAR ).getTime() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_PASSPORT_VALIDITY ) );
                        }
                    }*/
                    if ( StringUtils.isNotEmpty( inCrmCapForm.getNationalityDetailsPojo().getLocalCpAdd() ) )
                    {
                        if ( inCrmCapForm.getNationalityDetailsPojo().getLocalCpAdd().length() > 128 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_CP_ADDRESS_LENGTH ) );
                        }
                    }
                    if ( StringUtils.isNotBlank( inCrmCapForm.getNationalityDetailsPojo().getNationality() ) )
                    {
                        if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_200.getPattern(),
                                                             inCrmCapForm.getNationalityDetailsPojo().getNationality() ) )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_OTHER_NATIONALITY ) );
                        }
                    }
                }
            }
            if ( !StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                      CRMDisplayListConstants.INDIVIDUAL.getCode() )
                    || !StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                            CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) )
            {
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getOrgCordFname() ) )
                {
                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_1_45.getPattern(), inCrmCapForm
                            .getCustomerDetailsPojo().getOrgCordFname() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_NAME_LENGTH_BETWEEN_1_45,
                                                              IAppConstants.COORDINATOR,
                                                              IAppConstants.FIRST ) );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getOrgCordLname() ) )
                {
                    //                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_45.getPattern(), inCrmCapForm
                    //                            .getCustomerDetailsPojo().getOrgCordLname() ) )
                    if ( inCrmCapForm.getCustomerDetailsPojo().getOrgCordLname().length() > 45 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LAST_NAME_LENGTH,
                                                              IAppConstants.COORDINATOR,
                                                              IAppConstants.LAST,
                                                              45 ) );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getAuthSignFname() ) )
                {
                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_1_45.getPattern(), inCrmCapForm
                            .getCustomerDetailsPojo().getAuthSignFname() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_NAME_LENGTH_BETWEEN_1_45,
                                                              IAppConstants.AUTHORIZED_SIGNATORY,
                                                              IAppConstants.FIRST ) );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getAuthSignLname() ) )
                {
                    //                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_45.getPattern(), inCrmCapForm
                    //                            .getCustomerDetailsPojo().getAuthSignLname() ) )
                    if ( inCrmCapForm.getCustomerDetailsPojo().getAuthSignLname().length() > 45 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LAST_NAME_LENGTH,
                                                              IAppConstants.AUTHORIZED_SIGNATORY,
                                                              IAppConstants.LAST,
                                                              45 ) );
                    }
                }
                if ( inActionError.isEmpty()
                        && StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getAuthSignDesg() ) )
                {
                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_45.getPattern(), inCrmCapForm
                            .getCustomerDetailsPojo().getAuthSignDesg() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_DESIGNATION_LENGTH ) );
                    }
                }
            }
        }
        return inActionError;
    }

    private static boolean emptyPassportDetails( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getPassportNo() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PASSPORT_NUMBER ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getDisplayPassportValidity() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PASSPORT_VALIDITY ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getVisaType() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_VISA_TYPE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getDisplayVisaValidity() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_VISA_VALIDITY ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getLocalCpFname() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LOCAL_REFERENCE_FNAME ) );
        }
        else if ( inCrmCapForm.getNationalityDetailsPojo().getLocalCpMobileNo() == 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LOCAL_REFERENCE_MOBILE_NO ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getLocalCpAdd() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LOCAL_REFERENCE_ADDRESS ) );
        }
        /*
        if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getAddLine1() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_ONE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getAddLine2() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_TWO ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getAddLine3() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_THREE ) );
        }
        if ( inCrmCapForm.getNationalityDetailsPojo().getPincode() > 0 )
        {
            if ( String.valueOf(inCrmCapForm.getNationalityDetailsPojo().getPincode() ).length() != 6 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_PINCODE_LENGTH ) );
            }
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getState() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LOCAL_REFERENCE_STATE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getNationalityDetailsPojo().getCity() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LOCAL_REFERENCE_CITY ) );
        }
        */
        return flag;
    }

    private static boolean emptyIndivCustDetails( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                 CRMDisplayListConstants.INDIVIDUAL.getCode() ) )
        {
            if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getFhFname() ) )
            {
                flag = false;
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_EMPTY_FATHER_FNAME ) );
            }
            else if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustGender() ) )
            {
                flag = false;
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_CUSTOMER_GENDER ) );
            }
            else if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getDisplayCustDob() ) )
            {
                flag = false;
                inActionError
                        .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_CUSTOMER_DOB ) );
            }
            else if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getNationality() ) )
            {
                flag = false;
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_CUSTOMER_NATIONALITY ) );
            }
        }
        return flag;
    }

    private static boolean emptyIdAndAddressProof( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = false;
        for ( ContentPojo idProof : inCrmCapForm.getDocuments() )
        {
            if ( idProof.isSelected() )
            {
                flag = true;
                break;
            }
        }
        if ( !flag )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ID_PROOF ) );
        }
        for ( ContentPojo addProof : inCrmCapForm.getAddressProofs() )
        {
            if ( addProof.isSelected() )
            {
                flag = true;
                break;
            }
        }
        if ( !flag )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_PROOF ) );
        }
        /*if ( StringUtils.isEmpty( inCrmCapForm.getDocumentDetailsPojo().getIdProof() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ID_PROOF ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getDocumentDetailsPojo().getAddressProof() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_PROOF ) );
        }*/
        return flag;
    }

    private static boolean emptyPlanDetails( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.isEmpty( inCrmCapForm.getPlanDetailsPojo().getBasePlanCode() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BASE_PLAN_NAME ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getPlanDetailsPojo().getBillMode() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_BILLING_MODE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getPlanDetailsPojo().getUsageType() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_USAGE_TYPE ) );
        }
        return flag;
    }

    private static boolean emptyServiceDetails( ActionErrors inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.isEmpty( inCrmCapForm.getOrderDetailsPojo().getServices() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_SERVICE_SELECT ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getOrderDetailsPojo().getCpeStatus() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CPE_STATUS ) );
        }
        return flag;
    }

    private static boolean nonEmptyInstallationAddress( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.isEmpty( inCrmCapForm.getInstallationAddressPojo().getAddLine1() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_ONE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getInstallationAddressPojo().getAddLine2() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_TWO ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getInstallationAddressPojo().getAddLine3() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_THREE ) );
        }
        //        else if ( StringUtils.isEmpty( inCrmCapForm.getInstallationAddressPojo().getLandmark() ) )
        //        {
        //            flag = false;
        //            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LANDMARK ) );
        //        }
        else if ( inCrmCapForm.getInstallationAddressPojo().getPincode() == 0 )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PINCODE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getInstallationAddressPojo().getPropertyDetails() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PROPERTY_DETAILS ) );
        }
        return flag;
    }

    private static boolean emptyBillingAddress( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.isEmpty( inCrmCapForm.getBillingAddressPojo().getAddLine1() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_ONE ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getBillingAddressPojo().getAddLine2() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_TWO ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getBillingAddressPojo().getAddLine3() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ADDRESS_THREE ) );
        }
        //        else if ( StringUtils.isEmpty( inCrmCapForm.getBillingAddressPojo().getLandmark() ) )
        //        {
        //            flag = false;
        //            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_LANDMARK ) );
        //        }
        else if ( inCrmCapForm.getBillingAddressPojo().getPincode() == 0 )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_PINCODE ) );
        }
        return flag;
    }

    private static boolean crfAndCustomerDetailsEmptyCheck( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        boolean flag = true;
        if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getProduct() ) )
        {
            flag = false;
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_SERVICE_NAME ) );
        }
        else if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustEmailId() ) )
        {
            flag = false;
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMAIL_ID_REQUIRED ) );
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getConnectionType() ) )
        {
            if ( ! ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                         CRMDisplayListConstants.INDIVIDUAL.getCode() ) || StringUtils
                    .equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                             CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) ) )
            {
                if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getOrgCordFname() ) )
                {
                    flag = false;
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_ORG_COORDINATOR_FNAME ) );
                }
                else if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getAuthSignFname() ) )
                {
                    flag = false;
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_AUTH_SIGNATORY_FNAME ) );
                }
                else if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getAuthSignDesg() ) )
                {
                    flag = false;
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_AUTH_SIGNATORY_DESIGNATION ) );
                }
            }
            else if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                          CRMDisplayListConstants.INDIVIDUAL.getValue() )
                    && !StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getNationality(),
                                            IAppConstants.INDIAN ) )
            {
                emptyPassportDetails( inActionError, inCrmCapForm );
            }
        }
        return flag;
    }

    private static ActionMessages commonCrfValidation( ActionMessages inActionError,
                                                       CrmCapForm inCrmCapForm,
                                                       String inSaveCustomerBasicInfo )
    {
        if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getDisplayCrfDate() ) )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CRF_DATE ) );
        }
        else
        {
            Date crfDate;
            try
            {
                crfDate = new SimpleDateFormat( "dd-MMM-yyyy" ).parse( inCrmCapForm.getCustomerDetailsPojo()
                        .getDisplayCrfDate() );
                Date today = new Date();
                long diff = TimeUnit.DAYS.convert( today.getTime() - crfDate.getTime(), TimeUnit.MILLISECONDS );
                LOGGER.info( "CRF Date difference: " + ( diff ) );
                LOGGER.info( today.toString() );
                if ( diff > 90 || diff < 0 )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_INVALID_CRF_DATE ) );
                }
            }
            catch ( ParseException ex )
            {
                LOGGER.error( "Getting Error while date parsing in commonCrfValidation:: ", ex );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getConnectionType() ) )
        {
            if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                     CRMDisplayListConstants.INDIVIDUAL.getCode() )
                    || StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                           CRMDisplayListConstants.PROPRIETORSHIP.getCode() ) )
            {
                if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustFname() ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CUSTOMER_FNAME ) );
                }
                else if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustFname() ) )
                {
                    //                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_50.getPattern(), inCrmCapForm
                    //                            .getCustomerDetailsPojo().getCustFname() ) )
                    if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(),
                                             CRMDisplayListConstants.INDIVIDUAL.getCode() ) )
                    {
                        if ( StringUtils.isNumeric( inCrmCapForm.getCustomerDetailsPojo().getCustFname() )
                                || inCrmCapForm.getCustomerDetailsPojo().getCustFname().length() < 1
                                || inCrmCapForm.getCustomerDetailsPojo().getCustFname().length() > 50 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_NAME_LENGTH,
                                                                  IAppConstants.CUSTOMER,
                                                                  IAppConstants.FIRST ) );
                        }
                    }
                    else
                    {
                        if ( inCrmCapForm.getCustomerDetailsPojo().getCustFname().length() < 1
                                || inCrmCapForm.getCustomerDetailsPojo().getCustFname().length() > 50 )
                        {
                            inActionError.add( IAppConstants.APP_ERROR,
                                               new ActionMessage( IPropertiesConstant.ERROR_NAME_LENGTH,
                                                                  IAppConstants.CUSTOMER,
                                                                  IAppConstants.FIRST ) );
                        }
                    }
                }
                if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustLname() ) )
                {
                    //                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_50.getPattern(), inCrmCapForm
                    //                            .getCustomerDetailsPojo().getCustLname() ) )
                    if ( inCrmCapForm.getCustomerDetailsPojo().getCustLname().length() > 50 )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_LAST_NAME_LENGTH,
                                                              IAppConstants.CUSTOMER,
                                                              IAppConstants.LAST,
                                                              50 ) );
                    }
                }
            }
            else
            {
                if ( StringUtils.isEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustFname() ) )
                {
                    inActionError.add( IAppConstants.APP_ERROR,
                                       new ActionMessage( IPropertiesConstant.ERROR_EMPTY_COMPANY_NAME ) );
                }
                else if ( StringUtils.isNotEmpty( inCrmCapForm.getCustomerDetailsPojo().getCustFname() ) )
                {
                    //                    if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_CAPS_TEXT_3_200.getPattern(), inCrmCapForm
                    //                            .getCustomerDetailsPojo().getCustFname() ) )
                    if ( inCrmCapForm.getCustomerDetailsPojo().getCustFname().length() < 1
                            || inCrmCapForm.getCustomerDetailsPojo().getCustFname().length() > 200
                            || StringUtils.isNumeric( inCrmCapForm.getCustomerDetailsPojo().getCustFname() ) )
                    {
                        inActionError.add( IAppConstants.APP_ERROR,
                                           new ActionMessage( IPropertiesConstant.ERROR_COMPANY_NAME_LENGTH,
                                                              IAppConstants.COMPANY ) );
                    }
                }
            }
        }
        if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getConnectionType(), "0" ) )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_EMPTY_CONNECTION_TYPE ) );
        }
        return inActionError;
    }

    public static ActionMessages validateSearchCrf( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        return crfIdValidation( inActionError, inCrmCapForm );
    }

    private static ActionMessages crfIdValidation( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "validate search crf form" );
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inCrmCapForm.getCustomerDetailsPojo(),
                               ICRMValidationCriteriaUtil.FORM_CRF_CRITERIA_SEARCH, false );
        ValidationUtil.prepareErrorMessage( inActionError, resultMap );
        return inActionError;
    }

    private static ActionMessages fullAdditionalInfo( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        LOGGER.info( "Inside fullAdditionalInfo method.." );
        if ( StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getVehicleDetail() ) )
        {
            LOGGER.info( "vehicle detail is not empty. " + inCrmCapForm.getAdditionalDetailsPojo().getVehicleDetail() );
            if ( inCrmCapForm.getAdditionalDetailsPojo().getVehicleDetail().length() > 45
                    || StringUtils.isNumeric( inCrmCapForm.getAdditionalDetailsPojo().getVehicleDetail() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_DETAILS_NUMERIC_LENGTH_CHECK,
                                                      IAppConstants.VEHICLE ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getMobileMake() ) )
        {
            LOGGER.info( "mobile make is not empty. " + inCrmCapForm.getAdditionalDetailsPojo().getMobileMake() );
            if ( inCrmCapForm.getAdditionalDetailsPojo().getMobileMake().length() > 45
                    || StringUtils.isNumeric( inCrmCapForm.getAdditionalDetailsPojo().getMobileMake() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_DETAILS_NUMERIC_LENGTH_CHECK,
                                                      IAppConstants.MOBILE_MAKE ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getMobileNo() ) )
        {
            LOGGER.info( "mobile model number is not empty. " + inCrmCapForm.getAdditionalDetailsPojo().getMobileNo() );
            if ( inCrmCapForm.getAdditionalDetailsPojo().getMobileNo().length() > 45 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_MOBILE_INFO_FORMAT ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getMobileImeiNo() ) )
        {
            LOGGER.info( "mobile IMEI number is not empty. "
                    + inCrmCapForm.getAdditionalDetailsPojo().getMobileImeiNo() );
            if ( inCrmCapForm.getAdditionalDetailsPojo().getMobileImeiNo().length() > 15
                    || !StringUtils.isNumeric( inCrmCapForm.getAdditionalDetailsPojo().getMobileImeiNo() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_MOBILE_IMEI_FORMAT ) );
            }
        }
        if ( StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getBankBranch() ) )
        {
            LOGGER.info( "Bank branch is not empty. " + inCrmCapForm.getAdditionalDetailsPojo().getBankBranch() );
            if ( inCrmCapForm.getAdditionalDetailsPojo().getBankBranch().length() > 45
                    || StringUtils.isNumeric( inCrmCapForm.getAdditionalDetailsPojo().getBankBranch() ) )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_BANK_BRANCH_LENGTH ) );
            }
        }
        else if ( inCrmCapForm.getAdditionalDetailsPojo().getBankAccountNo() > 0 )
        {
            if ( String.valueOf( inCrmCapForm.getAdditionalDetailsPojo().getBankAccountNo() ).length() > 24 )
            {
                inActionError.add( IAppConstants.APP_ERROR,
                                   new ActionMessage( IPropertiesConstant.ERROR_BANK_ACCOUNT_NUMBER ) );
            }
        }
        if ( inCrmCapForm.getCustomerDetailsPojo().getBusinessPartner() == 0 )
        {
            inActionError
                    .add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_BUSINESS_PARTNER ) );
        }
        else if ( StringUtils.equals( inCrmCapForm.getCustomerDetailsPojo().getSalesRepresentative(), "0" ) )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_SALES_REPRESENTATIVE_CODE ) );
        }
        return inActionError;
    }

    private static ActionMessages emptyMobnContactNo( ActionMessages inActionError, CrmCapForm inCrmCapForm )
    {
        if ( inCrmCapForm.getCustomerDetailsPojo().getRmn() == 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR,
                               new ActionMessage( IPropertiesConstant.ERROR_RMN_MOBILE_NUMERIC ) );
        }
        /*else if ( inCrmCapForm.getCustomerDetailsPojo().getRtn() == 0 )
        {
            inActionError.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_RTN_NUMERIC ) );
        }*/
        return inActionError;
    }

    /* private static ActionErrors fullCreditCard( ActionErrors inActionError, CrmCapForm inCrmCapForm )
     {
         if ( inCrmCapForm.getAdditionalDetailsPojo().getCrcdCardNo() > 0
                 || StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getCrcdType() )
                 || StringUtils.isNotEmpty( inCrmCapForm.getAdditionalDetailsPojo().getDisplayCrcdExpiryDate() ) )
         {
             if ( inCrmCapForm.getAdditionalDetailsPojo().getCrcdCardNo() > 0 )
             {
                 LOGGER.info( "credit card number is not empty. "
                         + inCrmCapForm.getAdditionalDetailsPojo().getCrcdCardNo() );
                 if ( String.valueOf( inCrmCapForm.getAdditionalDetailsPojo().getCrcdCardNo() ).length() > 24
                         || !StringUtils.isNumeric( String.valueOf( inCrmCapForm.getAdditionalDetailsPojo()
                                 .getCrcdCardNo() ) ) )
                 {
                     inActionError.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_CREDIT_CARD_FORMAT ) );
                 }
             }
             if ( ! ( inCrmCapForm.getAdditionalDetailsPojo().getCrcdCardNo() > 0 ) )
             {
                 inActionError.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_CREDIT_CARD_FORMAT ) );
             }
             else if ( StringUtils.isEmpty( inCrmCapForm.getAdditionalDetailsPojo().getCrcdType() ) )
             {
                 inActionError.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_CREDIT_CARD_TYPE ) );
             }
             else if ( StringUtils.isEmpty( inCrmCapForm.getAdditionalDetailsPojo().getDisplayCrcdExpiryDate() ) )
             {
                 inActionError.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_CREDIT_CARD_EXP_DATE ) );
             }
         }
         return inActionError;
     }*/
    public static void resetInAForm( CrmCapForm inCapForm, String inMethodName )
    {
        LOGGER.info( "In side resetInAForm........." );
        if ( StringUtils.equals( IAppConstants.EDIT_CRF_AT_ANY_STAGE, inMethodName ) )
        {
            inCapForm.setRemarksPojo( new RemarksPojo() );
            inCapForm.setRemarksPojoList( new ArrayList<RemarksPojo>() );
            inCapForm.setCrmMappingList( new ArrayList<CrmPartnerMappingPojo>() );
            inCapForm.setCancellationReasonList( new ArrayList<CrmRcaReasonPojo>() );
            inCapForm.setRejectionReasonList( new ArrayList<CrmRcaReasonPojo>() );
            inCapForm.setRefuselReasonList( new ArrayList<CrmRcaReasonPojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SAVE_CUSTOMER_BASIC_INFO, inMethodName )
                || StringUtils.equals( IAppConstants.SUBMIT_CRF_PAGE, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SAVE_REMARKS, inMethodName ) )
        {
            inCapForm.setAdditionalDetailsPojo( new CrmAdditionalDetailsPojo() );
            inCapForm.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
            inCapForm.setBillingAddressPojo( new CrmAddressDetailsPojo() );
            inCapForm.getBillingAddressPojo().setSameAsInstAddr( false );
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.getCustomerDetailsPojo().setSalesUser( new CrmUserPojo() );
            inCapForm.setDocumentDetailsPojo( new CrmDocumentDetailsPojo() );
            inCapForm.setNationalityDetailsPojo( new CrmNationalityDetailsPojo() );
            inCapForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
            inCapForm.setTelecommunicationPayment( new CrmPaymentDetailsPojo() );
            inCapForm.setTeleservicesPayment( new CrmPaymentDetailsPojo() );
            inCapForm.setSecurityDepositPayment( new CrmPaymentDetailsPojo() );
            inCapForm.setPlanDetailsPojo( new CrmPlanDetailsPojo() );
            inCapForm.setAppointmentPojo( new AppointmentPojo() );
            inCapForm.setCrmNetworkConfigurations( new CrmNetworkConfigurationsPojo() );
            inCapForm.setCrmPartnerNetworkConfig( new CrmPartnerNetworkConfigPojo() );
            inCapForm.setPartner( new PartnerPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
            inCapForm.setDocuments( CRMUtils.getDocuments() );
            inCapForm.setAddressProofs( CRMUtils.getAddressProofList() );
            inCapForm.setAadharNumberPojo( new CrmCustAadharNumberPojo() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SAVE_VALIDATE_CRF, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.setInstallationAddressPojo( new CrmAddressDetailsPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
            inCapForm.setRejectionReasonList( new ArrayList<CrmRcaReasonPojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SAVE_NETWORK_DETAILS, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.setCrmNetworkConfigurations( new CrmNetworkConfigurationsPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
            inCapForm.setCrmMappingList( new ArrayList<CrmPartnerMappingPojo>() );
            inCapForm.setCancellationReasonList( new ArrayList<CrmRcaReasonPojo>() );
            inCapForm.setRejectionReasonList( new ArrayList<CrmRcaReasonPojo>() );
            inCapForm.setRefuselReasonList( new ArrayList<CrmRcaReasonPojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_SAVE_MAPMACID_DETAILS, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_BINDCPE_MACID, inMethodName )
                || StringUtils.equals( IAppConstants.METHOD_SAVE_DEVICE_STATUS, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.setCrmNetworkConfigurations( new CrmNetworkConfigurationsPojo() );
            inCapForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
            inCapForm.setTelecommunicationPayment( new CrmPaymentDetailsPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
            inCapForm.setCrmMappingList( new ArrayList<CrmPartnerMappingPojo>() );
            inCapForm.setCancellationReasonList( new ArrayList<CrmRcaReasonPojo>() );
            inCapForm.setRejectionReasonList( new ArrayList<CrmRcaReasonPojo>() );
            inCapForm.setRefuselReasonList( new ArrayList<CrmRcaReasonPojo>() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_EDIT_CPE_STATUS_PAGE, inMethodName ) )
        {
            inCapForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
            inCapForm.setTelecommunicationPayment( new CrmPaymentDetailsPojo() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_PUNCH_ISR, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.setCrmNetworkConfigurations( new CrmNetworkConfigurationsPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
            CrmCapManagerImpl capManagerImpl = new CrmCapManagerImpl();
            inCapForm.setMaterialList( capManagerImpl.getMaterialList( inCapForm ) );
            inCapForm.setCustomerFeedBackList( capManagerImpl.getCustomerFeedBackList( inCapForm ) );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_CANCEL_CRF_ACTION, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.setCrmNetworkConfigurations( new CrmNetworkConfigurationsPojo() );
            inCapForm.setOrderDetailsPojo( new CrmOrderDetailsPojo() );
            inCapForm.setTelecommunicationPayment( new CrmPaymentDetailsPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_NETWORK_INVENTORY_DETAILS_PAGE, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        }
        else if ( StringUtils.equals( IAppConstants.SEARCH_CRF, inMethodName ) )
        {
            inCapForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inCapForm.setRemarksPojo( new RemarksPojo() );
        }
    }
}
