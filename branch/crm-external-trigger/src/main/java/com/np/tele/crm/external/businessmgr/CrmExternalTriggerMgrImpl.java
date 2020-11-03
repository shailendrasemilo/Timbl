package com.np.tele.crm.external.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.billing.constants.CrmBillingOperations;
import com.np.tele.crm.billing.dao.ICRMBillingTransactionDao;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.TriggerRequestDto;
import com.np.tele.crm.dto.TriggerResponseDto;
import com.np.tele.crm.ext.pojos.CustomerDetailsPojo;
import com.np.tele.crm.ext.pojos.PrepaidPaymentPojo;
import com.np.tele.crm.ext.pojos.QrcTicketPojo;
import com.np.tele.crm.external.trigger.dao.IExternalTriggerDao;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;
import com.np.tele.crm.pojos.CrmBillingTransactionsPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StopWatch;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class CrmExternalTriggerMgrImpl
    implements ICrmExternalTriggerMgr
{
    private static final Logger       LOGGER                = Logger.getLogger( CrmExternalTriggerMgrImpl.class );
    private IExternalTriggerDao       externalTriggerDao    = null;
    private ICRMBillingTransactionDao billingTransactionDao = null;

    public IExternalTriggerDao getExternalTriggerDao()
    {
        return externalTriggerDao;
    }

    public void setExternalTriggerDao( IExternalTriggerDao inExternalTriggerDao )
    {
        externalTriggerDao = inExternalTriggerDao;
    }

    public ICRMBillingTransactionDao getBillingTransactionDao()
    {
        return billingTransactionDao;
    }

    public void setBillingTransactionDao( ICRMBillingTransactionDao inBillingTransactionDao )
    {
        billingTransactionDao = inBillingTransactionDao;
    }

    @Override
    public TriggerResponseDto postPrepaidPayment( PrepaidPaymentPojo inPrepaidPaymentPojo,
                                                  TriggerRequestDto inTriggerRequestDto )
    {
        TriggerResponseDto responseDto = new TriggerResponseDto();
        String statusCode = "";
        LOGGER.info( "IN postPrepaidPayment method:" + inTriggerRequestDto );
        if ( StringUtils.isValidObj( inTriggerRequestDto ) && StringUtils.isValidObj( inPrepaidPaymentPojo ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inTriggerRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EXTERNAL_TRIGGER_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                statusCode = ValidationPojoUtil
                        .validatePojo( inPrepaidPaymentPojo,
                                       ICRMValidationCriteriaUtil.EXTERNAL_TRIGGER_CRITERIA_FOR_PREPAID_PAYMENT );
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                ConfigDto configDto = new ConfigDto();
                CrmBillingOperations billingOperation = CrmBillingOperations.POST_PREPAID_PAYMENT;
                String requestDetails = inPrepaidPaymentPojo.toString() + inTriggerRequestDto.toString();
                CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
                transactionsPojo.setModuleName( CRMRequestType.FINANCE.getRequestCode() );
                createBillingTransaction( inTriggerRequestDto, billingOperation, requestDetails, transactionsPojo,
                                          configDto );
                StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
                sw.start();
                responseDto = getExternalTriggerDao().postPrepaidPayment( inPrepaidPaymentPojo, inTriggerRequestDto,
                                                                          responseDto );
                sw.stop();
                updateBillingTransaction( responseDto, configDto, transactionsPojo, sw );
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }

    public void updateBillingTransaction( TriggerResponseDto responseDto,
                                          ConfigDto inConfigDto,
                                          CrmBillingTransactionsPojo transactionsPojo,
                                          StopWatch sw )
    {
        long responseTimeInMills = sw.diff();
        transactionsPojo.setResponseTimeInMills( responseTimeInMills );
        if ( StringUtils.isValidObj( responseDto ) )
        {
            transactionsPojo.setResponseDetails( responseDto.toString().getBytes() );
            transactionsPojo.setReason( responseDto.getStatusDesc() );
            transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            inConfigDto.setBillingTransactionsPojo( transactionsPojo );
        }
        getBillingTransactionDao().saveTransactionPOJO( inConfigDto );
    }

    public void createBillingTransaction( TriggerRequestDto inTriggerRequestDto,
                                          CrmBillingOperations billingOperation,
                                          String requestDetails,
                                          CrmBillingTransactionsPojo transactionsPojo,
                                          ConfigDto inConfigDto )
    {
        transactionsPojo.setRequestDetails( requestDetails.getBytes() );
        transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
        transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
        transactionsPojo.setCustomerId( StringUtils.numericValue( inTriggerRequestDto.getCustomerId() ) );
        transactionsPojo.setBillingTransId( inTriggerRequestDto.getExtTransactionId() );
        transactionsPojo.setRequestType( IAppConstants.INBOUND );
        inConfigDto.setBillingTransactionsPojo( transactionsPojo );
        getBillingTransactionDao().saveTransactionPOJO( inConfigDto );
        inTriggerRequestDto.setTransId( inConfigDto.getBillingTransactionsPojo().getTransId() );
    }

    @Override
    public TriggerResponseDto generateQrcTicket( QrcTicketPojo inQrcTicketPojo, TriggerRequestDto inTriggerRequestDto )
    {
        TriggerResponseDto responseDto = new TriggerResponseDto();
        String statusCode = "";
        LOGGER.info( "IN generateQrcTicket method:" + inTriggerRequestDto );
        if ( StringUtils.isValidObj( inTriggerRequestDto ) && StringUtils.isValidObj( inQrcTicketPojo ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inTriggerRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EXTERNAL_TRIGGER_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.isBlank( inQrcTicketPojo.getRemarks() ) )
                {
                    inQrcTicketPojo.setRemarks( inQrcTicketPojo.getReason() );
                }
                statusCode = ValidationPojoUtil
                        .validatePojo( inQrcTicketPojo,
                                       ICRMValidationCriteriaUtil.EXTERNAL_TRIGGER_CRITERIA_FOR_QRC_TICKET );
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( StringUtils.equals( inQrcTicketPojo.getQrcEvent(), "Prepaid 1st Recharge" )
                        || StringUtils.equals( inQrcTicketPojo.getQrcEvent(), "Plan Confirmation" )
                        || StringUtils.equals( inQrcTicketPojo.getQrcEvent(), "TnB Confirmation" ) )
                {
                    ConfigDto configDto = new ConfigDto();
                    CrmBillingOperations billingOperation = CrmBillingOperations.GENERATE_QRC_TICKET;
                    String requestDetails = inQrcTicketPojo.toString() + inTriggerRequestDto.toString();
                    CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
                    transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
                    createBillingTransaction( inTriggerRequestDto, billingOperation, requestDetails, transactionsPojo,
                                              configDto );
                    StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
                    sw.start();
                    responseDto = getExternalTriggerDao().generateQRCTicket( inQrcTicketPojo, inTriggerRequestDto,
                                                                             responseDto );
                    sw.stop();
                    updateBillingTransaction( responseDto, configDto, transactionsPojo, sw );
                }
                else
                {
                    setResponseStatus( CRMServiceCode.CRM607.getStatusCode(), responseDto );
                }
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }

    @Override
    public TriggerResponseDto changeCustomerStatus( CustomerDetailsPojo inCustomerDetailsPojo,
                                                    TriggerRequestDto inTriggerRequestDto )
    {
        TriggerResponseDto responseDto = new TriggerResponseDto();
        String statusCode = "";
        LOGGER.info( "IN changeCustomerStatus method:" + inTriggerRequestDto );
        if ( StringUtils.isValidObj( inTriggerRequestDto ) && StringUtils.isValidObj( inCustomerDetailsPojo ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inTriggerRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EXTERNAL_TRIGGER_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                statusCode = ValidationPojoUtil
                        .validatePojo( inCustomerDetailsPojo,
                                       ICRMValidationCriteriaUtil.EXTERNAL_TRIGGER_CRITERIA_FOR_CUSTOMER );
            }
            if ( StringUtils.isNotEmpty( ( statusCode ) )
                    && StringUtils.equals( CRMServiceCode.CRM609.getStatusCode(), statusCode ) )
            {
                inCustomerDetailsPojo.setRemarks( "Billing Activity" );
                statusCode = "";
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                ConfigDto configDto = new ConfigDto();
                CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_CUSTOMER_STATUS;
                String requestDetails = inCustomerDetailsPojo.toString() + inTriggerRequestDto.toString();
                CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
                transactionsPojo.setModuleName( CRMRequestType.INA.getRequestCode() );
                createBillingTransaction( inTriggerRequestDto, billingOperation, requestDetails, transactionsPojo,
                                          configDto );
                StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
                sw.start();
                responseDto = getExternalTriggerDao().changeCustomerStatus( inCustomerDetailsPojo, inTriggerRequestDto,
                                                                            responseDto );
                sw.stop();
                updateBillingTransaction( responseDto, configDto, transactionsPojo, sw );
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }

    private void setResponseStatus( String statusCode, TriggerResponseDto responseDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.valueOf( statusCode );
        responseDto.setStatusCode( serviceCode.getStatusCode() );
        responseDto.setStatusDesc( serviceCode.getStatusDesc() );
    }

    @Override
    public TriggerResponseDto changePlanStatus( CrmBillingPlanRequestPojo inBillingPlanRequestPojo,
                                                TriggerRequestDto inTriggerRequestDto )
    {
        TriggerResponseDto responseDto = new TriggerResponseDto();
        String statusCode = "";
        LOGGER.info( "IN changePlanStatus method:" + inTriggerRequestDto );
        if ( StringUtils.isValidObj( inTriggerRequestDto ) && StringUtils.isValidObj( inBillingPlanRequestPojo ) )
        {
            statusCode = ValidationPojoUtil
                    .validatePojo( inTriggerRequestDto,
                                   ICRMValidationCriteriaUtil.EXTERNAL_TRIGGER_CRITERIA_CHANGE_PLAN_REQUEST_AUTHENTICATION );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                statusCode = ValidationPojoUtil
                        .validatePojo( inBillingPlanRequestPojo,
                                       ICRMValidationCriteriaUtil.EXTERNAL_TRIGGER_CRITERIA_FOR_CHANGE_PLAN_REQUEST );
                if ( StringUtils.isEmpty( statusCode ) )
                {
                    responseDto = getExternalTriggerDao().changePlanStatus( inBillingPlanRequestPojo,
                                                                            inTriggerRequestDto, responseDto );
                }
                else
                {
                    setResponseStatus( statusCode, responseDto );
                }
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }
}
