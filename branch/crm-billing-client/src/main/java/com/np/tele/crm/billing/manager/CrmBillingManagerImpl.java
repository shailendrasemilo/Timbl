package com.np.tele.crm.billing.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;
import org.datacontract.schemas._2004._07.ObjectFactory;
import org.datacontract.schemas._2004._07.AddChargeResult;
import org.datacontract.schemas._2004._07.AddPlan;
import org.datacontract.schemas._2004._07.ApiRequest;
import org.datacontract.schemas._2004._07.ApiResponse;
import org.datacontract.schemas._2004._07.BillDetails;
import org.datacontract.schemas._2004._07.CancelPlanMigrationResult;
import org.datacontract.schemas._2004._07.ChangeAddress;
import org.datacontract.schemas._2004._07.ChangeDeviceDetail;
import org.datacontract.schemas._2004._07.ClsAddCharge;
import org.datacontract.schemas._2004._07.ClsCustomerBaringExceptionList;
import org.datacontract.schemas._2004._07.ClsCustomerUnbaringExceptionList;
import org.datacontract.schemas._2004._07.ClsTerminationRefund;
import org.datacontract.schemas._2004._07.CreateCustomerResult;
import org.datacontract.schemas._2004._07.Customer;
import org.datacontract.schemas._2004._07.CustomerActivation;
import org.datacontract.schemas._2004._07.CustomerActivationResult;
import org.datacontract.schemas._2004._07.CustomerBaringExceptionListResult;
import org.datacontract.schemas._2004._07.CustomerCancelPayment;
import org.datacontract.schemas._2004._07.CustomerPostPayment;
import org.datacontract.schemas._2004._07.CustomerPostPaymentResult;
import org.datacontract.schemas._2004._07.CustomerUpdate;
import org.datacontract.schemas._2004._07.CustomerUsageDetail;
import org.datacontract.schemas._2004._07.GetCurrentUsageResult;
import org.datacontract.schemas._2004._07.GetDistributorDeviceDetailResult;
import org.datacontract.schemas._2004._07.InstantOfferAllocation;
import org.datacontract.schemas._2004._07.InventoryItemToLCOResult;
import org.datacontract.schemas._2004._07.OfferAllocation;
import org.datacontract.schemas._2004._07.PaymentAdjustment;
import org.datacontract.schemas._2004._07.PrBillingDetailsResult;

import com.np.tele.crm.billing.client.CrmBillingClient;
import com.np.tele.crm.billing.constants.CrmBillingOperations;
import com.np.tele.crm.billing.utils.BillingUtils;
import com.np.tele.crm.config.service.ICrmConfigService;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.ext.pojos.CrmCustAdditionalDetails;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.masterdata.service.IMasterDataService;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmBillingTransactionsPojo;
import com.np.tele.crm.pojos.CrmInvoiceDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StopWatch;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CrmBillingManagerImpl
    implements ICrmBillingManager
{
    private static final Logger   LOGGER                = Logger.getLogger( CrmBillingManagerImpl.class );
    private IBillingConversionMgr billingConversionImpl = null;

    public IBillingConversionMgr getBillingConversionImpl()
    {
        return billingConversionImpl;
    }

    public void setBillingConversionImpl( IBillingConversionMgr inBillingConversionImpl )
    {
        billingConversionImpl = inBillingConversionImpl;
    }

    @Override
    public void createCustomer( CrmCapDto inCrmCapDto )
    {
        CrmBillingOperations billingOperation = CrmBillingOperations.CREATE_CUSTOMER;
        LOGGER.info( "Inside BillingConversionMgrImpl : createCustomer( CrmCapDto inCrmCapDto )" );
        ObjectFactory billingFactory = null;
        Customer billingRequest = null;
        ApiRequest apiRequest = null;
        if ( StringUtils.isValidObj( inCrmCapDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().createCustomer( inCrmCapDto, billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            String requestDetails = apiRequest.toString() + billingRequest.toString(); //billingRequest
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.INA.getRequestCode() );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            CreateCustomerResult createCustomerResult = null;
            try
            {
                createCustomerResult = billingClient.createCustomer( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmCapDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                LOGGER.error( "Exception While :" + billingOperation.getDisplayValue(), ex );
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( createCustomerResult ) )
            {
                LOGGER.info( billingOperation.getDisplayValue() + " Response: " + createCustomerResult.toString() );
                transactionsPojo.setResponseDetails( createCustomerResult.toString().getBytes() );
                transactionsPojo.setCustomerId( createCustomerResult.getCustomerNo() );
                transactionsPojo.setBillingTransId( String.valueOf( createCustomerResult.getTransactionId() ) );
                transactionsPojo.setReason( createCustomerResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( createCustomerResult )
                    && ( createCustomerResult.getReturnCode() == 0 || createCustomerResult.getReturnCode() == 612 ) )
            {
                if ( createCustomerResult.getCustomerNo() > 0 )
                {
                    inCrmCapDto.setCustomerId( String.valueOf( createCustomerResult.getCustomerNo() ) );
                }
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM501.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM501.getStatusDesc() );
                inCrmCapDto.setBillingErrorCode( String.valueOf( createCustomerResult.getReturnCode() + ":"
                        + createCustomerResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    private CrmBillingTransactionsPojo updateBillingTransactions( CrmBillingTransactionsPojo transactionsPojo )
    {
        ConfigDto configDto = new ConfigDto();
        configDto.setBillingTransactionsPojo( transactionsPojo );
        ICrmConfigService configService = CRMServicesProxy.getInstance().getCRMConfigService( IGlobalConstants.REMOTE,
                                                                                              IGlobalConstants.APP );
        try
        {
            configDto = configService.customerActivityOps( ServiceParameter.APICALL.getParameter(), configDto );
            transactionsPojo = configDto.getBillingTransactionsPojo();
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "SOAP Exception while saving CrmBillingTransactionsPojo ", ex );
        }
        return transactionsPojo;
    }

    private Double getPlanPrice( String planCode )
    {
        MasterDataDto masterDto = new MasterDataDto();
        CrmPlanMasterPojo planMasterPojo = new CrmPlanMasterPojo();
        planMasterPojo.setPlanCode( planCode );
        masterDto.setPlanMaster( planMasterPojo );
        IMasterDataService masterService = CRMServicesProxy.getInstance()
                .getCRMMasterDataService( IGlobalConstants.REMOTE, IGlobalConstants.APP );
        try
        {
            masterDto = masterService.masterOperations( ServiceParameter.LIST.getParameter(),
                                                        CRMParameter.PLAN_DETAILS_MASTER.getParameter(), masterDto );
            if ( CommonValidator.isValidCollection( masterDto.getCrmPlanMasterPojos() ) )
            {
                return masterDto.getCrmPlanMasterPojos().get( 0 ).getRentInclTax();
            }
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "SOAP Exception while getPlanPrice ", ex );
        }
        return 0.0;
    }

    @Override
    public <E> CrmBillingDto updateCustomer( E e, long customerNo )
    {
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        LOGGER.info( "Inside BillingConversionMgrImpl : updateCustomer( CrmCapDto inCrmCapDto )" );
        ObjectFactory billingFactory = null;
        CustomerUpdate billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.UPDATE_CUSTOMER;
        if ( StringUtils.isValidObj( e ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().updateCustomer( e, billingFactory, customerNo );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.INA.getRequestCode() );
            transactionsPojo.setCustomerId( customerNo );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( CrmBillingOperations.UPDATE_CUSTOMER.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.updateCustomer( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                LOGGER.error( "Exception While :" + billingOperation.getDisplayValue(), ex );
                return crmBillingDto;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + "Response: " + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && billingResult.getReturnCode() == 0 )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM502.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM502.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode().intValue() ) + ":"
                        + billingResult.getReturnMessage().getValue() );
                LOGGER.info( "Billing Error Code:" + crmBillingDto.getBillingErrorCode() );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        return crmBillingDto;
    }

    @Override
    public void customerActivation( CrmCapDto inCrmCapDto )
    {
        CrmBillingOperations billingOperation = CrmBillingOperations.CUSTOMER_ACTIVATION;
        LOGGER.info( "Inside BillingConversionMgrImpl : customerActivation( CrmCapDto inCrmCapDto )" );
        ObjectFactory billingFactory = null;
        CustomerActivation customerActivation = null;
        ApiRequest apiRequest = null;
        if ( StringUtils.isValidObj( inCrmCapDto ) )
        {
            billingFactory = new ObjectFactory();
            customerActivation = getBillingConversionImpl().customerActivation( inCrmCapDto, billingFactory );
        }
        if ( StringUtils.isValidObj( customerActivation ) )
        {
            if ( StringUtils.isValidObj( inCrmCapDto.getPaymentDetailsPojo() )
                    && inCrmCapDto.getPaymentDetailsPojo().getRegistrationCharges() > 0 )
            {
                LOGGER.info( "Installation charge found" );
                double chargeAmmo = inCrmCapDto.getPaymentDetailsPojo().getRegistrationCharges();
                customerActivation.setChargeamount( BigDecimal.valueOf( chargeAmmo ) );
                customerActivation.setChargename( billingFactory
                        .createCustomerActivationChargename( "Activation_One Time Charges" ) );
                LOGGER.info( "charge name:: " + customerActivation.getChargename().toString() );
                LOGGER.info( "charge ammount:: " + customerActivation.getChargeamount() );
            }
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( customerActivation ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.INA.getRequestCode() );
            if ( StringUtils.numericValue( inCrmCapDto.getCustomerDetailsPojo().getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmCapDto.getCustomerDetailsPojo()
                        .getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + customerActivation.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            CustomerActivationResult apiResult = null;
            try
            {
                apiResult = billingClient.customerActivation( customerActivation, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmCapDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                LOGGER.error( "Exception While :" + billingOperation.getDisplayValue(), ex );
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Result: " + apiResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( apiResult ) )
            {
                transactionsPojo.setResponseDetails( apiResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( apiResult.getTransactionId() ) );
                transactionsPojo.setReason( apiResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( apiResult ) && apiResult.getReturnCode() == 0 )
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmCapDto.setStatusCode( CRMServiceCode.CRM504.getStatusCode() );
                inCrmCapDto.setStatusDesc( CRMServiceCode.CRM504.getStatusDesc() );
                inCrmCapDto.setBillingErrorCode( String.valueOf( apiResult.getReturnCode() + ":"
                        + apiResult.getReturnMessage().getValue() ) );
                LOGGER.info( "Billing Error Code:" + inCrmCapDto.getBillingErrorCode() );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmCapDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmCapDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void getDistributorInventoryList( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : getDistributorInventoryList( String inServicePartner )" );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.DISTRIBUTOR_INVENTORY_LIST;
        if ( StringUtils.isNotBlank( inConfigDto.getServicePartner() ) )
        {
            billingFactory = new ObjectFactory();
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( inConfigDto.getRequestType() );
            if ( StringUtils.numericValue( inConfigDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inConfigDto.getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + ",LCO=" + inConfigDto.getServicePartner();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( CrmBillingOperations.DISTRIBUTOR_INVENTORY_LIST.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            InventoryItemToLCOResult apiResult = null;
            try
            {
                apiResult = billingClient.getDistributorInventoryList( null, inConfigDto.getServicePartner(),
                                                                       apiRequest );
            }
            catch ( Exception ex )
            {
                inConfigDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inConfigDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inConfigDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( "DistributorInventoryList Response:" + apiResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( apiResult ) )
            {
                transactionsPojo.setResponseDetails( apiResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( apiResult.getTransactionId() ) );
                transactionsPojo.setReason( apiResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( apiResult ) && apiResult.getReturnCode() == 0 )
            {
                inConfigDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inConfigDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                List<GetDistributorDeviceDetailResult> inventoryList = apiResult.getItems().getValue()
                        .getGetDistributorDeviceDetailResult();
                List<String> contents = new ArrayList<String>();
                if ( CommonValidator.isValidCollection( inventoryList ) )
                {
                    LOGGER.info( "Inventory Name::" + inventoryList.get( 0 ).getDeviceName().getValue() );
                    inConfigDto.setInventType( inventoryList.get( 0 ).getDeviceName().getValue() );
                    for ( GetDistributorDeviceDetailResult distributorDeviceDetailResult : inventoryList )
                    {
                        contents.add( prepareMacAddress( distributorDeviceDetailResult.getMfgUniqueId().getValue() ) );
                    }
                }
                inConfigDto.setMacAddressList( contents );
            }
            else
            {
                inConfigDto.setStatusCode( CRMServiceCode.CRM503.getStatusCode() );
                inConfigDto.setStatusDesc( CRMServiceCode.CRM503.getStatusDesc() );
                inConfigDto.setBillingErrorCode( String.valueOf( apiResult.getReturnCode() + ":"
                        + apiResult.getReturnMessage().getValue() ) );
                LOGGER.info( "Billing Error Code:" + inConfigDto.getBillingErrorCode() );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inConfigDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inConfigDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    private String prepareMacAddress( String inMacAddress )
    {
        if ( !StringUtils.contains( inMacAddress, IAppConstants.DOT ) && StringUtils.isNotBlank( inMacAddress ) )
        {
            inMacAddress = StringUtils.lowerCase( inMacAddress );
            inMacAddress = StringUtils.insertCharacterAtIndex( inMacAddress, 4, "." );
            System.out.println( "MacAdress:" + inMacAddress );
        }
        return inMacAddress;
    }

    @Override
    public void cancelPayment( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : postPayment( CrmFinanceDto inCrmFinanceDto )" );
        ObjectFactory billingFactory = null;
        CustomerCancelPayment billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.CANCEL_PAYMENT;
        if ( StringUtils.isValidObj( inCrmFinanceDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().cancelPayment( inCrmFinanceDto.getPaymentDetailsPojo(),
                                                                       billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.FINANCE.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inCrmFinanceDto.getPaymentDetailsPojo().getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmFinanceDto.getPaymentDetailsPojo()
                        .getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.cancelPayment( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmFinanceDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult )
                    && ( billingResult.getReturnCode() == 0 || billingResult.getReturnCode() == 1905 ) )
            {
                // Billing Code: 1905:Payment has been already canceled.
                inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM506.getStatusCode() );
                inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM506.getStatusDesc() );
                inCrmFinanceDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void postPayment( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : postPayment( CrmFinanceDto inCrmFinanceDto )" );
        ObjectFactory billingFactory = null;
        CustomerPostPayment billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.POST_PAYMENT;
        if ( StringUtils.isValidObj( inCrmFinanceDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().postPayment( inCrmFinanceDto.getPaymentDetailsPojo(),
                                                                     billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.FINANCE.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inCrmFinanceDto.getPaymentDetailsPojo().getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmFinanceDto.getPaymentDetailsPojo()
                        .getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            CustomerPostPaymentResult billingResult = null;
            try
            {
                billingResult = billingClient.postPayment( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmFinanceDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM505.getStatusCode() );
                inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM505.getStatusDesc() );
                inCrmFinanceDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmFinanceDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmFinanceDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void addCharge( CrmBillingDto inBillingDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : addCharge( CrmBillingDto inBillingDto )" );
        ObjectFactory billingFactory = null;
        ClsAddCharge billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.ADD_CHARGE;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().addCharge( inBillingDto, billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.FINANCE.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inBillingDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inBillingDto.getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            AddChargeResult billingResult = null;
            try
            {
                billingResult = billingClient.addCharge( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                inBillingDto.setChargeAmount( billingResult.getChargeAmount() );
            }
            else
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM507.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM507.getStatusDesc() );
                inBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void changeStatus( CrmBillingDto inBillingDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : changeStatus( CrmBillingDto inBillingDto )" );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_STATUS;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.FINANCE.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inBillingDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inBillingDto.getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + ", Customer Id = "
                    + StringUtils.numericValue( inBillingDto.getCustomerId() ) + ", Remarks  = "
                    + inBillingDto.getRemarks() + ", Status = "
                    + PropertyUtils.getBillingMappingKeyValue( inBillingDto.getStatus() );
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.changeStatus( StringUtils.numericValue( inBillingDto.getCustomerId() ),
                                                            inBillingDto.getRemarks(), PropertyUtils
                                                                    .getBillingMappingKeyValue( inBillingDto
                                                                            .getStatus() ), apiRequest );
            }
            catch ( Exception ex )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult )
                    && ( billingResult.getReturnCode() == 0 || billingResult.getReturnCode() == 1002 || billingResult
                            .getReturnCode() == 1009 ) )
            {
                //[1002:Customer already in this state
                inBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM508.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM508.getStatusDesc() );
                inBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    //    public static void main( String[] args )
    //    {
    //        new CrmBillingManagerImpl().prepareMacAddress( "1C184A067ADF" ); //1c18.4a06.7adf
    //    }
    @Override
    public CrmBillingDto changeAddress( CrmAddressDetailsPojo inCrmAddrDetailsPojo )
    {
        LOGGER.info( "Inside CrmBillingManagerImpl : changeAddress( CrmAddressDetailsPojo inCrmAddrDetailsPojo ) : "
                + inCrmAddrDetailsPojo );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        ChangeAddress billingRequest = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_ADDRESS;
        if ( StringUtils.isValidObj( inCrmAddrDetailsPojo ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().changeAddress( inCrmAddrDetailsPojo, billingFactory );
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmAddrDetailsPojo.getCrmCustomerDetails()
                    .getCustomerId() ) );
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.changeAddress( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return crmBillingDto;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                if ( StringUtils.equals( "IN", inCrmAddrDetailsPojo.getAddressType() ) )
                {
                    crmBillingDto.setStatusCode( CRMServiceCode.CRM509.getStatusCode() );
                    crmBillingDto.setStatusDesc( CRMServiceCode.CRM509.getStatusDesc() );
                }
                else if ( StringUtils.equals( "BL", inCrmAddrDetailsPojo.getAddressType() ) )
                {
                    crmBillingDto.setStatusCode( CRMServiceCode.CRM510.getStatusCode() );
                    crmBillingDto.setStatusDesc( CRMServiceCode.CRM510.getStatusDesc() );
                }
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public CrmBillingDto changeDeviceDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : changeDeviceDetails( CrmBillingDto inBillingDto )+"
                + inCrmQrcDto );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        ChangeDeviceDetail billingRequest = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_DEVICE_DETAILS;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().changeDeviceDetails( inCrmQrcDto, billingFactory );
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.changeDeviceDetail( billingRequest, apiRequest );
                /*LOGGER.info( billingResult.getReturnCode() );*/
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM511.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM511.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public CrmBillingDto postWaiver( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : postWaiver( CrmBillingDto inBillingDto ):" + inCrmQrcDto );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        PaymentAdjustment billingRequest = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        CrmBillingOperations billingOperation = CrmBillingOperations.POST_WAIVER;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().postWaiver( inCrmQrcDto, billingFactory );
            LOGGER.info( "CREATED BILLING REQUEST POSTWAIVER IS:" + billingRequest );
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.paymentAdjustment( billingRequest, apiRequest );
                /*LOGGER.info( billingResult.getReturnCode() );*/
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM512.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM512.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public void getBillingDetails( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : getBillingDetails( CrmQrcDto inCrmQrcDto )" + inCrmQrcDto );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.GET_BILLING_DETAILS;
        if ( StringUtils.isNotBlank( inCrmQrcDto.getCustomerId() ) )
        {
            billingFactory = new ObjectFactory();
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            if ( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            }
            Calendar date = Calendar.getInstance();
            Date todayDate = date.getTime();
            date.set( Calendar.YEAR, date.get( Calendar.YEAR ) - 1 );
            Date oneYearBackDate = date.getTime();
            XMLGregorianCalendar inStartDate = DateUtils.toXMLGregorianCalendar( oneYearBackDate );
            XMLGregorianCalendar inEndDate = DateUtils.toXMLGregorianCalendar( todayDate );
            String requestDetails = apiRequest.toString() + ",CUSTOMER=" + inCrmQrcDto.getCustomerId() + ",Start Date="
                    + DateUtils.getFormattedDate( oneYearBackDate, IDateConstants.SDF_DD_MMM_YYYY ) + ", End Date="
                    + DateUtils.getFormattedDate( todayDate, IDateConstants.SDF_DD_MMM_YYYY );
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            BillDetails apiResult = null;
            try
            {
                apiResult = billingClient.getBillingDetails( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ),
                                                             inStartDate, inEndDate, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + apiResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( apiResult ) )
            {
                transactionsPojo.setResponseDetails( apiResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( apiResult.getTransactionId() ) );
                transactionsPojo.setReason( apiResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( apiResult ) && apiResult.getReturnCode() == 0 )
            {
                List<PrBillingDetailsResult> billingDetailsResult = apiResult.getBillingDetails().getValue()
                        .getPrBillingDetailsResult();
                List<String> contents = new ArrayList<String>();
                List<CrmInvoiceDetailsPojo> invoiceDetailsList = new ArrayList<CrmInvoiceDetailsPojo>();
                LOGGER.info( "billingDetails result size:" + billingDetailsResult.size() );
                if ( CommonValidator.isValidCollection( billingDetailsResult ) )
                {
                    for ( PrBillingDetailsResult prBillingDetailsResult : billingDetailsResult )
                    {
                        CrmInvoiceDetailsPojo invoiceDetailsPojo = new CrmInvoiceDetailsPojo();
                        invoiceDetailsPojo.setBillNumber( BillingUtils.convertToJavaObject( prBillingDetailsResult
                                .getBillNumber() ) );
                        Calendar cal = DateUtils.getDate( BillingUtils.convertToJavaObject( prBillingDetailsResult
                                .getBillDate() ), "DD/MM/yyyy" );
                        if ( StringUtils.isValidObj( cal ) )
                        {
                            invoiceDetailsPojo.setBillDate( cal.getTime() );
                        }
                        invoiceDetailsPojo.setBillAmount( BillingUtils.convertToJavaObject( prBillingDetailsResult
                                                                                                    .getBillAmount() )
                                .doubleValue() );
                        invoiceDetailsPojo.setBillPeriod( BillingUtils.convertToJavaObject( prBillingDetailsResult
                                .getBillPeriod() ) );
                        invoiceDetailsPojo.setPaymentDueDate( BillingUtils.convertToJavaObject( prBillingDetailsResult
                                .getPaymentDueDate() ) );
                        cal = DateUtils.getDate( BillingUtils.convertToJavaObject( prBillingDetailsResult
                                .getPaymentDueDate() ), "DD/MM/yyyy" );
                        if ( StringUtils.isValidObj( cal ) )
                        {
                            invoiceDetailsPojo.setDueDate( cal.getTime() );
                        }
                        invoiceDetailsPojo.setCustomerId( inCrmQrcDto.getCustomerId() );
                        invoiceDetailsList.add( invoiceDetailsPojo );
                        contents.add( prBillingDetailsResult.getBillNumber().getValue() );
                        LOGGER.info( "billno:" + prBillingDetailsResult.getBillNumber().getValue() );
                    }
                }
                else
                {
                    LOGGER.info( "invalid collection @ billingDetailsResult" );
                }
                LOGGER.info( "invoice details list size final:" + invoiceDetailsList.size() );
                inCrmQrcDto.setInvoiceDetailsPojos( invoiceDetailsList );
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM522.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM522.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( String.valueOf( apiResult.getReturnCode().intValue() ) );
                LOGGER.info( "Billing Error Code:" + inCrmQrcDto.getBillingErrorCode() );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public CrmBillingDto barringException( CrmQrcDto inCrmQrcDto, String param )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : barringException( CrmBillingDto inBillingDto ):" + inCrmQrcDto );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        ClsCustomerBaringExceptionList billingRequest = null;
        CrmBillingOperations billingOperation = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        Integer successCode = 0;
        if ( StringUtils.equals( param, "ADD" ) )
        {
            billingOperation = CrmBillingOperations.ADD_EXCEPTION_LIST;
            successCode = 3905;
        }
        else
        {
            billingOperation = CrmBillingOperations.REMOVE_EXCEPTION_LIST;
            successCode = 3904;
        }
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().barringException( inCrmQrcDto, billingFactory, param );
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            CustomerBaringExceptionListResult billingResult = null;
            try
            {
                billingResult = billingClient.customerBaringExceptionList( billingRequest, apiRequest );
                /*LOGGER.info( billingResult.getReturnCode() );*/
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult )
                    && ( billingResult.getReturnCode() == 0 || billingResult.getReturnCode().intValue() == successCode
                            .intValue() ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM520.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM520.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public CrmBillingDto unbarringException( CrmQrcDto inCrmQrcDto, String param )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : unbarringException( CrmBillingDto inBillingDto ):"
                + inCrmQrcDto );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        ClsCustomerUnbaringExceptionList billingRequest = null;
        CrmBillingOperations billingOperation = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        Integer successCode = 0;
        if ( StringUtils.equals( param, "ADD" ) )
        {
            billingOperation = CrmBillingOperations.ADD_EXCEPTION_LIST;
        }
        else
        {
            billingOperation = CrmBillingOperations.REMOVE_EXCEPTION_LIST;
            successCode = 4004;
        }
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().unbarringException( inCrmQrcDto, billingFactory, param );
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.customerUnbaringExceptionList( billingRequest, apiRequest );
                /*LOGGER.info( billingResult.getReturnCode() );*/
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult )
                    && ( billingResult.getReturnCode() == 0 || billingResult.getReturnCode() == successCode ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM521.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM521.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public CrmBillingDto addPlan( CrmQrcDto inCrmQrcDto, String inParam, boolean immediate )
    {
        LOGGER.info( "Inside addPlan( CrmQrcDto inCrmQrcDto ):" + inParam );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        AddPlan billingRequest = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        CrmBillingOperations billingOperation = CrmBillingOperations.ADD_PLAN;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().addPlan( inCrmQrcDto, inParam, billingFactory );
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            billingRequest.setIsImmediate( immediate );
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerDetailsPojo()
                    .getCustomerId() ) );
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.addPlan( billingRequest, apiRequest );
                /*LOGGER.info( billingResult.getReturnCode() );*/
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setParamValue( String.valueOf( billingResult.getTransactionId() ) );
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM513.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM513.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public void changeOffer( CrmQrcDto inCrmQrcDto, Date inActivationDate )
    {
        LOGGER.info( "Inside CrmBillingManagerImpl : changeOffer( CrmQrcDto inCrmQrcDto )" );
        CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_OFFER;
        ObjectFactory billingFactory = null;
        OfferAllocation billingRequest = null;
        ApiRequest apiRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().changeOffer( inCrmQrcDto, billingFactory );
            billingRequest.setActivationDate( DateUtils.toXMLGregorianCalendar( inActivationDate ) );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            String requestDetails = apiRequest.toString() + billingRequest.toString(); //billingRequest
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.changeOffer( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                inCrmQrcDto.setSrTicketNo( String.valueOf( billingResult.getTransactionId() ) );
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM514.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM514.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public CrmBillingDto cancelPlanOfferMigration( String inApiTransID )
    {
        LOGGER.info( "Inside " + this.getClass().getSimpleName()
                + " : cancelPlanOfferMigration( String inApiTransID ):" + inApiTransID );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        CrmBillingOperations billingOperation = CrmBillingOperations.CANCEL_PLAN_MIGRATION;
        if ( StringUtils.isNotBlank( inApiTransID ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( 0l );
            String requestDetails = apiRequest.toString() + ", API Transaction Id = "
                    + StringUtils.numericValue( inApiTransID );
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            CancelPlanMigrationResult billingResult = null;
            try
            {
                billingResult = billingClient.cancelPlanOfferMigration( StringUtils.numericValue( inApiTransID ),
                                                                        apiRequest );
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM518.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM518.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public void instantOfferRenew( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : instantOfferRenew( CrmQrcDto inCrmQrcDto )" );
        ObjectFactory billingFactory = null;
        InstantOfferAllocation billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.INSTANT_OFFER_RENEW;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().instantOfferRenew( inCrmQrcDto, billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.instantOfferRenew( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && billingResult.getReturnCode() == 0 )
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM519.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM519.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void instantPrepaidOfferRenew( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : instantPrepaidOfferRenew( CrmQrcDto inCrmQrcDto )" );
        ObjectFactory billingFactory = null;
        InstantOfferAllocation billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.INSTANT_PREPAID_OFFER_RENEW;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().instantPrepaidOfferRenew( inCrmQrcDto, billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.instantPrepaidOfferRenew( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && billingResult.getReturnCode() == 0 )
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inCrmQrcDto.setStatusCode( CRMServiceCode.CRM519.getStatusCode() );
                inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM519.getStatusDesc() );
                inCrmQrcDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inCrmQrcDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmQrcDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void cancelAdditionalPlan( CrmBillingDto inBillingDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : changeStatus( CrmBillingDto inBillingDto )" );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.CANCEL_ADDITIONAL_PLAN;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inBillingDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inBillingDto.getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + ", Customer Id = "
                    + StringUtils.numericValue( inBillingDto.getCustomerId() ) + ", PlanCode ="
                    + inBillingDto.getParamValue() + ", Immediate = " + inBillingDto.isBoolValue();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.cancelAdditionalPlan( StringUtils.numericValue( inBillingDto
                        .getCustomerId() ), inBillingDto.getParamValue(), inBillingDto.isBoolValue(), apiRequest );
            }
            catch ( Exception ex )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                inBillingDto.setParamValue( String.valueOf( billingResult.getTransactionId() ) );
                inBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM515.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM515.getStatusDesc() );
                inBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public CrmBillingDto getCustomerUsageDetail( String inCustomerId, boolean isPostPaid )
    {
        LOGGER.info( "Inside " + this.getClass().getSimpleName() + " : getCustomerUsageDetail( String inCustomerId ):"
                + inCustomerId );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        CrmBillingOperations billingOperation = CrmBillingOperations.CUSTOMER_USAGE_DETAIL;
        if ( StringUtils.isNotBlank( inCustomerId ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo.setCustomerId( StringUtils.numericValue( inCustomerId ) );
            String requestDetails = apiRequest.toString() + ", Customer Id = "
                    + StringUtils.numericValue( inCustomerId );
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            CustomerUsageDetail billingResult = null;
            try
            {
                billingResult = billingClient.getCustomerUsageDetail( apiRequest,
                                                                      StringUtils.numericValue( inCustomerId ), null,
                                                                      null, null, null );
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                return crmBillingDto;
            }
            finally
            {
                sw.stop();
            }
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                CrmCustAdditionalDetails additionalDetails = new CrmCustAdditionalDetails();
                //                if ( isPostPaid )
                //                {
                //                    additionalDetails
                //                            .setBalance( -1
                //                                    * ( StringUtils.isValidObj( billingResult.getBalance() ) ? billingResult
                //                                            .getBalance() : 0.0 ) );
                //                }
                //                else
                //                {
                additionalDetails.setBalance( billingResult.getBalance() );
                //                }
                additionalDetails.setCurrentBandwidth( billingResult.getCurrentBandwidth() );
                additionalDetails.setPlanCode( BillingUtils.convertToJavaObject( billingResult.getPlanCode() ) );
                additionalDetails.setAddonPlanCode( BillingUtils.convertToJavaObject( billingResult.getAddOnCode() ) );
                additionalDetails.setBillCycle( BillingUtils.convertToJavaObject( billingResult.getBillCycleName() ) );
                LOGGER.info( "Session Status :: " + billingResult.getSessionStatus().getValue() );
                if ( StringUtils.equals( billingResult.getSessionStatus().getValue(), "Active" ) )
                {
                    additionalDetails.setStatus( "ON" );
                }
                else
                    additionalDetails.setStatus( "OFF" );
                if ( StringUtils.isValidObj( billingResult.getCurrentSessionStartTime().getValue() ) )
                {
                    additionalDetails.setCurrentSession( billingResult.getCurrentSessionStartTime().getValue()
                            .toGregorianCalendar().getTime() );
                }
                List<GetCurrentUsageResult> currentUsageResult = billingResult.getUsageDetails().getValue()
                        .getGetCurrentUsageResult();
                if ( CommonValidator.isValidCollection( currentUsageResult ) )
                {
                    GetCurrentUsageResult usageResultPrimary = currentUsageResult.get( 0 );
                    GetCurrentUsageResult usageResultSecondary = null;
                    if ( currentUsageResult.size() > 1 )
                    {
                        if ( StringUtils.contains( BillingUtils.convertToJavaObject( usageResultPrimary.getBucket() ),
                                                   "Primary" ) )
                        {
                            usageResultSecondary = currentUsageResult.get( 1 );
                        }
                        else
                        {
                            usageResultSecondary = currentUsageResult.get( 0 );
                            usageResultPrimary = currentUsageResult.get( 1 );
                        }
                    }
                    if ( StringUtils.isValidObj( usageResultPrimary.getExpiryDate() ) )
                    {
                        additionalDetails.setExpiryDate( usageResultPrimary.getExpiryDate().getValue()
                                .toGregorianCalendar().getTime() );
                    }
                    if ( StringUtils.isValidObj( usageResultPrimary.getActivationDate() ) )
                    {
                        additionalDetails.setActivationDate( usageResultPrimary.getActivationDate()
                                .toGregorianCalendar().getTime() );
                    }
                    additionalDetails.setAllocatedVolumeQuota( StringUtils.numericValue( BillingUtils
                            .convertToJavaObject( usageResultPrimary.getAllocatedVolumeQuota() ) ) );
                    additionalDetails.setRemainVolumeQuota( StringUtils.numericValue( BillingUtils
                            .convertToJavaObject( usageResultPrimary.getRemainVolumeQuota() ) ) );
                    additionalDetails.setUsedVolumeQuota( StringUtils.numericValue( BillingUtils
                            .convertToJavaObject( usageResultPrimary.getUsedVolumeQuota() ) ) );
                    additionalDetails.setPrimaryQuota( StringUtils.numericValue( BillingUtils
                            .convertToJavaObject( usageResultPrimary.getAllocatedVolumeQuota() ) ) );
                    additionalDetails.setPrimaryRemainQuota( StringUtils.numericValue( BillingUtils
                            .convertToJavaObject( usageResultPrimary.getRemainVolumeQuota() ) ) );
                    additionalDetails.setPrimaryUsedQuota( StringUtils.numericValue( BillingUtils
                            .convertToJavaObject( usageResultPrimary.getUsedVolumeQuota() ) ) );
                    if ( StringUtils.isValidObj( usageResultSecondary ) )
                    {
                        additionalDetails.setAllocatedVolumeQuota( additionalDetails.getAllocatedVolumeQuota()
                                + StringUtils.numericValue( BillingUtils.convertToJavaObject( usageResultSecondary
                                        .getAllocatedVolumeQuota() ) ) );
                        additionalDetails.setRemainVolumeQuota( additionalDetails.getRemainVolumeQuota()
                                + StringUtils.numericValue( BillingUtils.convertToJavaObject( usageResultSecondary
                                        .getRemainVolumeQuota() ) ) );
                        additionalDetails.setUsedVolumeQuota( additionalDetails.getUsedVolumeQuota()
                                + StringUtils.numericValue( BillingUtils.convertToJavaObject( usageResultSecondary
                                        .getUsedVolumeQuota() ) ) );
                        additionalDetails.setSecondaryQuota( BillingUtils.convertToJavaObject( usageResultSecondary
                                .getAllocatedVolumeQuota() ) );
                        additionalDetails.setSecondaryRemainQuota( StringUtils.numericValue( BillingUtils
                                .convertToJavaObject( usageResultSecondary.getRemainVolumeQuota() ) ) );
                        additionalDetails.setSecondaryUsedQuota( StringUtils.numericValue( BillingUtils
                                .convertToJavaObject( usageResultSecondary.getUsedVolumeQuota() ) ) );
                    }
                    additionalDetails.setPlanRental( getPlanPrice( additionalDetails.getPlanCode() ) );
                }
                crmBillingDto.setCustAdditionalDetails( additionalDetails );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM517.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM517.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }

    @Override
    public void changeBillCycle( CrmBillingDto inBillingDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : changeBillCycle( CrmBillingDto inBillingDto )" );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_BILL_CYCLE;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inBillingDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inBillingDto.getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + "[Customer Id = "
                    + StringUtils.numericValue( inBillingDto.getCustomerId() ) + ", Bill Cycle ="
                    + inBillingDto.getParamValue() + ", Status = " + inBillingDto.getStatus() + "]";
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient
                        .changeBillCycle( StringUtils.numericValue( inBillingDto.getCustomerId() ),
                                          inBillingDto.getParamValue(), inBillingDto.getStatus(), apiRequest );
            }
            catch ( Exception ex )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM524.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM524.getStatusDesc() );
                inBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void allowGracePeriod( CrmBillingDto inBillingDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl : allowGracePeriod( CrmBillingDto inBillingDto )" );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.ALLOW_GRACE_PERIOD;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inBillingDto.getCustomerId() ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inBillingDto.getCustomerId() ) );
            }
            String requestDetails = apiRequest.toString() + ", Customer Id = "
                    + StringUtils.numericValue( inBillingDto.getCustomerId() ) + ", Expiry Date ="
                    + inBillingDto.getExpiryDate();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient
                        .allowGracePeriod( StringUtils.numericValue( inBillingDto.getCustomerId() ),
                                           DateUtils.toXMLGregorianCalendar( inBillingDto.getExpiryDate() ), apiRequest );
            }
            catch ( Exception ex )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM525.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM525.getStatusDesc() );
                inBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public void terminationRefund( CrmBillingDto inBillingDto )
    {
        LOGGER.info( "Inside BillingConversionMgrImpl,  terminationRefund( CrmBillingDto inBillingDto )" );
        ObjectFactory billingFactory = null;
        ClsTerminationRefund billingRequest = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.TERMINATE_REFUND_PROCESS;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingFactory = new ObjectFactory();
            billingRequest = getBillingConversionImpl().terminationRefund( inBillingDto, billingFactory );
        }
        if ( StringUtils.isValidObj( billingRequest ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) && StringUtils.isValidObj( billingRequest ) )
        {
            String requestDetails = apiRequest.toString() + billingRequest.toString();
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setCustomerId( StringUtils.numericValue( inBillingDto.getCustomerId() ) );
            transactionsPojo.setModuleName( CRMRequestType.QRC.getRequestCode() );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.terminationRefund( billingRequest, apiRequest );
            }
            catch ( Exception ex )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                inBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && billingResult.getReturnCode() == 0 )
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inBillingDto.setStatusCode( CRMServiceCode.CRM527.getStatusCode() );
                inBillingDto.setStatusDesc( CRMServiceCode.CRM527.getStatusDesc() );
                inBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            inBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
    }

    @Override
    public CrmBillingDto changeBusinessPartner( String inCustomerId, String inOldPartner, String inNewPartner )
    {
        CrmBillingDto crmBillingDto = new CrmBillingDto();
        LOGGER.info( "Inside BillingConversionMgrImpl : changeBusinessPartner( CrmCapDto inCrmCapDto )" );
        ObjectFactory billingFactory = null;
        ApiRequest apiRequest = null;
        CrmBillingOperations billingOperation = CrmBillingOperations.CHANGE_BUSINESS_PARTNER;
        if ( StringUtils.isNotBlank( inCustomerId ) )
        {
            billingFactory = new ObjectFactory();
        }
        if ( StringUtils.isValidObj( billingFactory ) )
        {
            apiRequest = getBillingConversionImpl().getApiRequestPOJO( billingFactory );
        }
        if ( StringUtils.isValidObj( apiRequest ) )
        {
            CrmBillingTransactionsPojo transactionsPojo = new CrmBillingTransactionsPojo();
            transactionsPojo.setModuleName( CRMRequestType.INA.getRequestCode() );
            transactionsPojo.setApiOperation( billingOperation.getOperationCode() );
            transactionsPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );
            if ( StringUtils.numericValue( inCustomerId ) > 0 )
            {
                transactionsPojo.setCustomerId( StringUtils.numericValue( inCustomerId ) );
            }
            String requestDetails = apiRequest.toString() + ", Customer Id = " + inCustomerId
                    + ", Old Business Partner =" + inOldPartner + ", New Business Partner =" + inNewPartner;
            LOGGER.info( billingOperation.getDisplayValue() + " Request:" + requestDetails );
            transactionsPojo.setRequestDetails( requestDetails.getBytes() );
            transactionsPojo = updateBillingTransactions( transactionsPojo );
            apiRequest.setExtTransactionId( billingFactory.createApiRequestExtTransactionId( String
                    .valueOf( transactionsPojo.getTransId() ) ) );
            StopWatch sw = new StopWatch( billingOperation.getDisplayValue() );
            sw.start();
            CrmBillingClient billingClient = new CrmBillingClient();
            ApiResponse billingResult = null;
            try
            {
                billingResult = billingClient.changeBusinessPartner( StringUtils.numericValue( inCustomerId ),
                                                                     inOldPartner, inNewPartner, apiRequest );
            }
            catch ( Exception ex )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM500.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM500.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( ex.getMessage() );
                sw.stop();
                return crmBillingDto;
            }
            sw.stop();
            long responseTimeInMills = sw.diff();
            LOGGER.info( billingOperation.getDisplayValue() + " Response:" + billingResult.toString() );
            transactionsPojo.setResponseTimeInMills( responseTimeInMills );
            if ( StringUtils.isValidObj( billingResult ) )
            {
                transactionsPojo.setResponseDetails( billingResult.toString().getBytes() );
                transactionsPojo.setBillingTransId( String.valueOf( billingResult.getTransactionId() ) );
                transactionsPojo.setReason( billingResult.getReturnMessage().getValue() );
                transactionsPojo.setStatus( CRMStatusCode.COMPLETED.getStatusCode() );
            }
            if ( StringUtils.isValidObj( billingResult ) && ( billingResult.getReturnCode() == 0 ) )
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                crmBillingDto.setStatusCode( CRMServiceCode.CRM526.getStatusCode() );
                crmBillingDto.setStatusDesc( CRMServiceCode.CRM526.getStatusDesc() );
                crmBillingDto.setBillingErrorCode( String.valueOf( billingResult.getReturnCode() + ":"
                        + billingResult.getReturnMessage().getValue() ) );
            }
            transactionsPojo = updateBillingTransactions( transactionsPojo );
        }
        else
        {
            crmBillingDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmBillingDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmBillingDto;
    }
}
