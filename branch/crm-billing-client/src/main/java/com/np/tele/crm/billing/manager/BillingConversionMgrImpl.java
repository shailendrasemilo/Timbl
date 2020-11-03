package com.np.tele.crm.billing.manager;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Set;

import org.apache.log4j.Logger;
import org.datacontract.schemas._2004._07.AddPlan;
import org.datacontract.schemas._2004._07.ApiRequest;
import org.datacontract.schemas._2004._07.BillingAddress;
import org.datacontract.schemas._2004._07.ChangeAddress;
import org.datacontract.schemas._2004._07.ChangeAddressAddressType;
import org.datacontract.schemas._2004._07.ChangeDeviceDetail;
import org.datacontract.schemas._2004._07.ClsAddCharge;
import org.datacontract.schemas._2004._07.ClsCustomerBaringExceptionList;
import org.datacontract.schemas._2004._07.ClsCustomerUnbaringExceptionList;
import org.datacontract.schemas._2004._07.ClsTerminationRefund;
import org.datacontract.schemas._2004._07.Customer;
import org.datacontract.schemas._2004._07.CustomerActivation;
import org.datacontract.schemas._2004._07.CustomerCancelPayment;
import org.datacontract.schemas._2004._07.CustomerPostPayment;
import org.datacontract.schemas._2004._07.CustomerUpdate;
import org.datacontract.schemas._2004._07.InstallationAddress;
import org.datacontract.schemas._2004._07.InstantOfferAllocation;
import org.datacontract.schemas._2004._07.LocalContactAddress;
import org.datacontract.schemas._2004._07.ObjectFactory;
import org.datacontract.schemas._2004._07.OfferAllocation;
import org.datacontract.schemas._2004._07.PaymentAdjustment;

import com.np.tele.crm.billing.utils.CrmPojoToBilling;
import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class BillingConversionMgrImpl
    implements IBillingConversionMgr
{
    private static final Logger LOGGER = Logger.getLogger( BillingConversionMgrImpl.class );

    @Override
    public Customer createCustomer( CrmCapDto inCrmCapDto, ObjectFactory billingFactory )
    {
        InstallationAddress installationAddress;
        BillingAddress billingAddress;
        LocalContactAddress contactAddress;
        boolean conversionFlag = true;
        Customer customer = null;
        if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
        {
            customer = billingFactory.createCustomer();
            conversionFlag = CrmPojoToBilling.covertToBilling( customer, inCrmCapDto.getCustomerDetailsPojo() );
            LOGGER.info( "Customer Details Pojo Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
            Set<CrmAddressDetailsPojo> crmAddressDetailses = inCrmCapDto.getCustomerDetailsPojo()
                    .getCrmAddressDetailses();
            if ( conversionFlag && CommonValidator.isValidCollection( crmAddressDetailses ) )
            {
                for ( CrmAddressDetailsPojo crmAddressDetailsPojo : crmAddressDetailses )
                {
                    if ( StringUtils.equals( crmAddressDetailsPojo.getAddressType(),
                                             IAppConstants.ADDRESS_TYPE_INSTALLATION ) )
                    {
                        installationAddress = billingFactory.createInstallationAddress();
                        conversionFlag = CrmPojoToBilling.covertToBilling( installationAddress, crmAddressDetailsPojo );
                        installationAddress.setNation( billingFactory
                                .createInstallationAddressNation( IAppConstants.COUNTRY_INDIA ) );
                        customer.setInstallationAddress( billingFactory
                                .createCustomerInstallationAddress( installationAddress ) );
                        conversionFlag = CrmPojoToBilling.covertToBilling( customer, crmAddressDetailsPojo );
                    }
                    else
                    {
                        billingAddress = billingFactory.createBillingAddress();
                        conversionFlag = CrmPojoToBilling.covertToBilling( billingAddress, crmAddressDetailsPojo );
                        billingAddress.setNation( billingFactory
                                .createBillingAddressNation( IAppConstants.COUNTRY_INDIA ) );
                        customer.setBillingAddress( billingFactory.createCustomerBillingAddress( billingAddress ) );
                    }
                }
                LOGGER.info( "Address Pojos Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
            }
            if ( conversionFlag
                    && CommonValidator.isValidCollection( inCrmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() ) )
            {
                for ( CrmPlanDetailsPojo planDetailsPojo : inCrmCapDto.getCustomerDetailsPojo().getCrmPlanDetailses() )
                {
                    conversionFlag = CrmPojoToBilling.covertToBilling( customer, planDetailsPojo );
                    break;
                }
                LOGGER.info( "Plan Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
            }
        }
        if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getNationalityDetailsPojo() ) )
        {
            if ( StringUtils.isNotEmpty( inCrmCapDto.getNationalityDetailsPojo().getLocalCpAdd() ) )
            {
                contactAddress = billingFactory.createLocalContactAddress();
                conversionFlag = CrmPojoToBilling.covertToBilling( contactAddress,
                                                                   inCrmCapDto.getNationalityDetailsPojo() );
                contactAddress
                        .setNation( billingFactory.createLocalContactAddressNation( IAppConstants.COUNTRY_INDIA ) );
                customer.setLocalContactAddress( billingFactory.createCustomerLocalContactAddress( contactAddress ) );
            }
            CrmPojoToBilling.covertToBilling( customer, inCrmCapDto.getNationalityDetailsPojo() );
            LOGGER.info( "Local Contact Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
        }
        if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getSalesPartner() ) )
        {
            conversionFlag = CrmPojoToBilling.covertToBilling( customer, inCrmCapDto.getSalesPartner() );
            LOGGER.info( "Partner Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
        }
        if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo().getSalesUser() ) )
        {
            conversionFlag = CrmPojoToBilling.covertToBilling( customer, inCrmCapDto.getCustomerDetailsPojo()
                    .getSalesUser() );
            LOGGER.info( "Partner Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
        }
        if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getOrderDetailsPojo() ) )
        {
            conversionFlag = CrmPojoToBilling.covertToBilling( customer, inCrmCapDto.getOrderDetailsPojo() );
        }
        LOGGER.info( "Order Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
        if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getPaymentDetailsPojosList() )
                && !inCrmCapDto.getPaymentDetailsPojosList().isEmpty() )
        {
            for ( CrmPaymentDetailsPojo paymentDetailsPojo : inCrmCapDto.getPaymentDetailsPojosList() )
            {
                if ( StringUtils.isNotBlank( paymentDetailsPojo.getPaymentMode() )
                        && !StringUtils.equals( paymentDetailsPojo.getPaymentChannel(),
                                                CRMDisplayListConstants.SECURITY_DEPOSIT.getCode() ) )
                {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime( paymentDetailsPojo.getCreatedTime() );
                    cal.set( Calendar.MILLISECOND, 0 );
                    StringBuilder sb = new StringBuilder();
                    sb.append( inCrmCapDto.getCustomerDetailsPojo().getRecordId() ).append( IAppConstants.UNDERSCORE )
                            .append( paymentDetailsPojo.getEntityType() ).append( IAppConstants.UNDERSCORE )
                            .append( cal.getTimeInMillis() );
                    paymentDetailsPojo.setRefTransId( sb.toString() );
                    double activationCharge = 0;
                    if(StringUtils.isValidObj(paymentDetailsPojo.getRegistrationCharges()) && paymentDetailsPojo.getRegistrationCharges()>0)
                    {
                    	DecimalFormat df2 = new DecimalFormat(".##");
                    	double taxCal = paymentDetailsPojo.getRegistrationCharges()*18/100;
                    	String value = df2.format(taxCal);
                    	activationCharge = paymentDetailsPojo.getActivationCharges();
                    	paymentDetailsPojo.setActivationCharges(paymentDetailsPojo.getActivationCharges() + Double.parseDouble(value) + paymentDetailsPojo.getRegistrationCharges());
                    }
                    conversionFlag = CrmPojoToBilling.covertToBilling( customer, paymentDetailsPojo,
                                                                       paymentDetailsPojo.getEntityType(), false );
                    if(activationCharge >0)
                    {
                    	paymentDetailsPojo.setActivationCharges(activationCharge);
                    }
                }
            }
        }
        LOGGER.info( "Payment Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
        if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getAdditionalDetailsPojo() ) )
        {
            conversionFlag = CrmPojoToBilling.covertToBilling( customer, inCrmCapDto.getAdditionalDetailsPojo() );
        }
        LOGGER.info( "Additional Details Added for CAF:" + inCrmCapDto.getCustomerDetailsPojo().getCrfId() );
        return customer;
    }

    @Override
    public ApiRequest getApiRequestPOJO( ObjectFactory inBillingFactory )
    {
        ApiRequest apiRequest = null;
        if ( StringUtils.isValidObj( inBillingFactory ) )
        {
            apiRequest = inBillingFactory.createApiRequest();
            apiRequest.setSystemId( inBillingFactory.createApiRequestSystemId( PropertyUtils
                    .getBillingMappingKeyValue( CRM_BILLING_SYSTEM_ID ) ) );
            apiRequest.setPassword( inBillingFactory.createApiRequestPassword( PropertyUtils
                    .getBillingMappingKeyValue( CRM_BILLING_SYSTEM_PASSWORD ) ) );
            apiRequest.setRequestDate( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
        }
        return apiRequest;
    }

    //Below this all code is just for testing purpose only
    private static <E> void printDataTypes( E e )
    {
        //System.out.println( e.getClass().getSimpleName() );
        Field[] efields = e.getClass().getDeclaredFields();
        for ( Field efield : efields )
        {
            System.out.println( efield.getName() );
        }
    }

    public static void main( String[] args )
    {
        CustomerActivation customerActivation = new CustomerActivation();
        System.out.println( customerActivation.getClass() );
        System.out.println( CustomerActivation.class );
        // printDataTypes( customerActivation );
    }

    @Override
    public <E> CustomerUpdate updateCustomer( E e, ObjectFactory inBillingFactory, long inCustomer )
    {
        CustomerUpdate customerUpdate = null;
        if ( StringUtils.isValidObj( e ) )
        {
            customerUpdate = inBillingFactory.createCustomerUpdate();
            CrmPojoToBilling.covertToBilling( customerUpdate, e, true );
            customerUpdate.setCustomerNo( inCustomer );
            if ( e instanceof CrmCustomerDetailsPojo )
            {
                CrmCustomerDetailsPojo customerDetailsPojo = (CrmCustomerDetailsPojo) e;
                if ( StringUtils.isValidObj( customerDetailsPojo )
                        && StringUtils.isValidObj( customerDetailsPojo.getDateOnIsr() ) )
                {
                    customerUpdate.setISRDate( DateUtils.toXMLGregorianCalendar( customerDetailsPojo.getDateOnIsr() ) );
                    LOGGER.info( "Date on ISR : " + customerUpdate.getISRDate() );
                    if ( StringUtils.isValidObj( customerUpdate.getISRDate() ) )
                    {
                        customerUpdate.setISRPuchDate( DateUtils.toXMLGregorianCalendar( Calendar.getInstance() ) );
                    }
                }
            }
        }
        return customerUpdate;
    }

    @Override
    public CustomerActivation customerActivation( CrmCapDto inCrmCapDto, ObjectFactory inBillingFactory )
    {
        boolean conversionFlag = true;
        CustomerActivation customerActivation = null;
        if ( StringUtils.isValidObj( inCrmCapDto.getCustomerDetailsPojo() ) )
        {
            customerActivation = inBillingFactory.createCustomerActivation();
            conversionFlag = CrmPojoToBilling
                    .covertToBilling( customerActivation, inCrmCapDto.getCustomerDetailsPojo() );
            if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getOrderDetailsPojo() ) )
            {
                conversionFlag = CrmPojoToBilling.covertToBilling( customerActivation,
                                                                   inCrmCapDto.getOrderDetailsPojo() );
            }
            if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getNetworkPartner() ) )
            {
                conversionFlag = CrmPojoToBilling.covertToBilling( customerActivation, inCrmCapDto.getNetworkPartner() );
            }
            if ( conversionFlag && StringUtils.isValidObj( inCrmCapDto.getNetworkConfigurationsPojo() ) )
            {
                conversionFlag = CrmPojoToBilling.covertToBilling( customerActivation,
                                                                   inCrmCapDto.getNetworkConfigurationsPojo() );
            }
            if ( StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), inCrmCapDto.getCustomerDetailsPojo()
                    .getProduct() ) )
            {
                customerActivation.setUserName( inBillingFactory.createCustomerActivationUserName( inCrmCapDto
                        .getNetworkConfigurationsPojo().getCurrentCpeMacId() + "@rinetworks.in" ) );
                customerActivation.setPassword( inBillingFactory.createCustomerActivationPassword( "rinetworks" ) );
                // As per mail from Hieght8 secondary to be same as primary.
                customerActivation.setSecondaryMacId( inBillingFactory
                        .createCustomerActivationSecondaryMacId( inCrmCapDto.getNetworkConfigurationsPojo()
                                .getCurrentCpeMacId() ) );
            }
            else
            {
                conversionFlag = CrmPojoToBilling.covertToBilling( customerActivation,
                                                                   inCrmCapDto.getCrmPartnerNetworkConfig() );
                customerActivation.setOption82( null );
            }
        }
        return customerActivation;
    }

    @Override
    public CustomerPostPayment postPayment( CrmPaymentDetailsPojo inCrmPaymentDetailsPojo,
                                            ObjectFactory inBillingFactory )
    {
        CustomerPostPayment customerPostPayment = null;
        if ( StringUtils.isValidObj( inCrmPaymentDetailsPojo ) )
        {
            customerPostPayment = inBillingFactory.createCustomerPostPayment();
            Calendar cal = Calendar.getInstance();
            cal.setTime( inCrmPaymentDetailsPojo.getCreatedTime() );
            cal.set( Calendar.MILLISECOND, 0 );
            StringBuilder sb = new StringBuilder();
            sb.append( inCrmPaymentDetailsPojo.getCustomerRecordId() ).append( IAppConstants.UNDERSCORE )
                    .append( inCrmPaymentDetailsPojo.getEntityType() ).append( IAppConstants.UNDERSCORE )
                    .append( cal.getTimeInMillis() );
            inCrmPaymentDetailsPojo.setRefTransId( sb.toString() );
            CrmPojoToBilling.covertToBilling( customerPostPayment, inCrmPaymentDetailsPojo );
            if ( StringUtils.equals( CRMDisplayListConstants.TELESOLUTIONS.getCode(),
                                     inCrmPaymentDetailsPojo.getEntityType() ) )
            {
                customerPostPayment.setIsTelesolutionPayment( true );
            }
            else
            {
                customerPostPayment.setIsTelesolutionPayment( false );
            }
        }
        return customerPostPayment;
    }

    @Override
    public CustomerCancelPayment cancelPayment( CrmPaymentDetailsPojo inPaymentDetailsPojo,
                                                ObjectFactory inBillingFactory )
    {
        CustomerCancelPayment billingRequest = null;
        if ( StringUtils.isValidObj( inPaymentDetailsPojo ) )
        {
            billingRequest = inBillingFactory.createCustomerCancelPayment();
            Calendar cal = Calendar.getInstance();
            cal.setTime( inPaymentDetailsPojo.getCreatedTime() );
            cal.set( Calendar.MILLISECOND, 0 );
            StringBuilder sb = new StringBuilder();
            sb.append( inPaymentDetailsPojo.getCustomerRecordId() ).append( IAppConstants.UNDERSCORE )
                    .append( inPaymentDetailsPojo.getEntityType() ).append( IAppConstants.UNDERSCORE )
                    .append( cal.getTimeInMillis() );
            inPaymentDetailsPojo.setRefTransId( sb.toString() );
            CrmPojoToBilling.covertToBilling( billingRequest, inPaymentDetailsPojo );
        }
        return billingRequest;
    }

    @Override
    public ClsAddCharge addCharge( CrmBillingDto inBillingDto, ObjectFactory inBillingFactory )
    {
        ClsAddCharge billingRequest = null;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingRequest = inBillingFactory.createClsAddCharge();
            CrmPojoToBilling.covertToBilling( billingRequest, inBillingDto );
        }
        return billingRequest;
    }

    @Override
    public ChangeAddress changeAddress( CrmAddressDetailsPojo inCrmAddrDetailsPojo, ObjectFactory inBillingFactory )
    {
        ChangeAddress billingRequest = null;
        if ( StringUtils.isValidObj( inCrmAddrDetailsPojo ) )
        {
            billingRequest = inBillingFactory.createChangeAddress();
            CrmPojoToBilling.covertToBilling( billingRequest, inCrmAddrDetailsPojo );
            billingRequest.setNation( inBillingFactory.createChangeAddressNation( IAppConstants.COUNTRY_INDIA ) );
            if ( StringUtils.equals( "IN", inCrmAddrDetailsPojo.getAddressType() ) )
            {
                billingRequest.setAddressType( ChangeAddressAddressType.INSTALLATION );
            }
            else if ( StringUtils.equals( "BL", inCrmAddrDetailsPojo.getAddressType() ) )
            {
                billingRequest.setAddressType( ChangeAddressAddressType.BILLING );
            }
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmAddrDetailsPojo.getCrmCustomerDetails()
                    .getCustomerId() ) );
        }
        return billingRequest;
    }

    @Override
    public ChangeDeviceDetail changeDeviceDetails( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory )
    {
        ChangeDeviceDetail billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createChangeDeviceDetail();
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            if ( StringUtils.equals( CRMCustomerActivityActions.MAC_CHANGE.getActionDesc(),
                                     inCrmQrcDto.getActivityAction() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getNetworkConfigurationsPojo() ) )
                {
                    billingRequest.setPrimaryMacId( inBillingFactory.createChangeDeviceDetailPrimaryMacId( inCrmQrcDto
                            .getNetworkConfigurationsPojo().getCurrentCpeMacId() ) );
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                             CRMDisplayListConstants.BROADBAND.getCode() ) )
                    {
                        billingRequest.setSecondaryMacId( inBillingFactory
                                .createChangeDeviceDetailSecondaryMacId( inCrmQrcDto.getNetworkConfigurationsPojo()
                                        .getCurrentCpeMacId() ) );
                        billingRequest.setUserName( inBillingFactory.createCustomerActivationUserName( inCrmQrcDto
                                .getNetworkConfigurationsPojo().getCurrentCpeMacId() + "@rinetworks.in" ) );
                    }
                    else
                    {
                        billingRequest.setSecondaryMacId( inBillingFactory
                                .createChangeDeviceDetailSecondaryMacId( inCrmQrcDto.getNetworkConfigurationsPojo()
                                        .getCurrentSlaveMacId() ) );
                        billingRequest.setMACFaultyStatus( inCrmQrcDto.isMacFaulty() );
                    }
                }
                // ------ Billing API :- Address Correction Web Service API
                //Billing API (Change Device Detail Web Service) will be invoked with the device details (Mac Address, Device user name).    
            }
            else if ( StringUtils.equals( CRMCustomerActivityActions.OPTION_82_CHANGE.getActionDesc(),
                                          inCrmQrcDto.getActivityAction() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getNetworkConfigurationsPojo() ) )
                {
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                             CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) )
                    {
                        billingRequest.setNasPortId( inBillingFactory.createChangeDeviceDetailNasPortId( inCrmQrcDto
                                .getCrmPartnerNetworkConfigPojo().getNasPortId() ) );
                        billingRequest.setDynamicPool( inBillingFactory
                                .createChangeDeviceDetailDynamicPool( inCrmQrcDto.getCrmPartnerNetworkConfigPojo()
                                        .getPoolName() ) );
                    }
                    else
                    {
                        billingRequest.setOption82( inBillingFactory.createChangeDeviceDetailOption82( inCrmQrcDto
                                .getNetworkConfigurationsPojo().getOption82() ) );
                    }
                }
                //Billing API (Change Device Detail Web Service) will be invoked with device details (Option82).
            }
            else if ( StringUtils.equals( CRMCustomerActivityActions.CPE_OWNERSHIP_CHANGE.getActionDesc(),
                                          inCrmQrcDto.getActivityAction() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getOrderDetailsPojo() ) )
                {
                    String value = CRMUtils.getEnumValue( "CPEStatus", String.valueOf( inCrmQrcDto
                            .getOrderDetailsPojo().getCpeStatus() ) );
                    billingRequest
                            .setDeviceOwnership( inBillingFactory.createChangeDeviceDetailDeviceOwnership( value ) );
                    billingRequest.setPrimaryMacId( inBillingFactory.createChangeDeviceDetailPrimaryMacId( inCrmQrcDto
                            .getNetworkConfigurationsPojo().getCurrentCpeMacId() ) );
                    if ( StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                             CRMDisplayListConstants.BROADBAND.getCode() )
                            || StringUtils.equals( inCrmQrcDto.getCustomerDetailsPojo().getProduct(),
                                                   CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                    {
                        billingRequest.setSecondaryMacId( inBillingFactory
                                .createChangeDeviceDetailSecondaryMacId( inCrmQrcDto.getNetworkConfigurationsPojo()
                                        .getCurrentCpeMacId() ) );
                    }
                    else
                    {
                        billingRequest.setSecondaryMacId( inBillingFactory
                                .createChangeDeviceDetailSecondaryMacId( inCrmQrcDto.getNetworkConfigurationsPojo()
                                        .getCurrentSlaveMacId() ) );
                    }
                }
            }
            /*else if ( StringUtils.equals( CRMCustomerActivityActions.CUSTOMER_POOL_CHANGE.getActionDesc(),
                                          inCrmQrcDto.getActivityAction() ) )
            {
                if ( StringUtils.isValidObj( inCrmQrcDto.getNetworkConfigurationsPojo() ) )
                {
                }
                //Billing API (Change Device Detail Web Service) will be invoked with device details (NAS-Port-ID, Master and Pool Name).
            }*/
            else if ( StringUtils.equals( CRMCustomerActivityActions.STATIC_IP.getActionCode(),
                                          inCrmQrcDto.getActivityAction() ) )
            {
                billingRequest.setStaticIp( inBillingFactory.createChangeDeviceDetailStaticIp( inCrmQrcDto
                        .getCrmCustAssetDetailsPojo().getCategoryValue() ) );
            }
        }
        return billingRequest;
    }

    @Override
    public PaymentAdjustment postWaiver( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory )
    {
        PaymentAdjustment billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createPaymentAdjustment();
            CrmPojoToBilling.covertToBilling( billingRequest, inCrmQrcDto.getCrmCustWaiverPojo() );
            billingRequest.setAdjustType( inBillingFactory.createPaymentAdjustmentAdjustType( "C" ) );
            billingRequest.setRemark( inBillingFactory.createPaymentAdjustmentRemark( inCrmQrcDto.getRemarksPojo()
                    .getRemarks() ) );
            billingRequest.setAdjustDate( DateUtils.toXMLGregorianCalendar( Calendar.getInstance().getTime() ) );
        }
        return billingRequest;
    }

    @Override
    public ClsCustomerBaringExceptionList barringException( CrmQrcDto inCrmQrcDto,
                                                            ObjectFactory inBillingFactory,
                                                            String param )
    {
        ClsCustomerBaringExceptionList billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createClsCustomerBaringExceptionList();
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCrmQrcWhitelistPojo()
                    .getCustomerId() ) );
            billingRequest.setAction( inBillingFactory.createClsCustomerBaringExceptionListAction( param ) );
            billingRequest.setReason( inBillingFactory.createClsCustomerBaringExceptionListReason( inCrmQrcDto
                    .getRemarksPojo().getRemarks() ) );
            billingRequest.setBarredOutDate( DateUtils.toXMLGregorianCalendar( inCrmQrcDto.getCrmQrcWhitelistPojo()
                    .getEndDate() ) );
        }
        return billingRequest;
    }

    @Override
    public ClsCustomerUnbaringExceptionList unbarringException( CrmQrcDto inCrmQrcDto,
                                                                ObjectFactory inBillingFactory,
                                                                String param )
    {
        ClsCustomerUnbaringExceptionList billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createClsCustomerUnbaringExceptionList();
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            billingRequest.setAction( inBillingFactory.createClsCustomerUnbaringExceptionListAction( param ) );
            billingRequest.setReason( inBillingFactory.createClsCustomerUnbaringExceptionListReason( inCrmQrcDto
                    .getRemarksPojo().getRemarks() ) );
            billingRequest.setUnbarredOutDate( DateUtils.toXMLGregorianCalendar( inCrmQrcDto.getCrmQrcWhitelistPojo()
                    .getEndDate() ) );
        }
        return billingRequest;
    }

    @Override
    public AddPlan addPlan( CrmQrcDto inCrmQrcDto, String inParam, ObjectFactory inBillingFactory )
    {
        AddPlan billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createAddPlan();
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCustomerDetailsPojo()
                    .getCustomerId() ) );
            billingRequest.setPlanCode( inBillingFactory.createAddPlanPlanCode( inParam ) );
        }
        return billingRequest;
    }

    @Override
    public OfferAllocation changeOffer( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory )
    {
        OfferAllocation billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createOfferAllocation();
            CrmPojoToBilling.covertToBilling( billingRequest, inCrmQrcDto.getCrmPlanDetailsPojo(), false );
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            /**/
        }
        return billingRequest;
    }

    @Override
    public InstantOfferAllocation instantOfferRenew( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory )
    {
        InstantOfferAllocation billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createInstantOfferAllocation();
            CrmPojoToBilling.covertToBilling( billingRequest, inCrmQrcDto.getCrmPlanDetailsPojo(), false );
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            /**/
        }
        return billingRequest;
    }

    @Override
    public ClsTerminationRefund terminationRefund( CrmBillingDto inBillingDto, ObjectFactory inBillingFactory )
    {
        ClsTerminationRefund billingRequest = null;
        if ( StringUtils.isValidObj( inBillingDto ) )
        {
            billingRequest = inBillingFactory.createClsTerminationRefund();
            CrmPojoToBilling.covertToBilling( billingRequest, inBillingDto, false );
        }
        return billingRequest;
    }

    @Override
    public InstantOfferAllocation instantPrepaidOfferRenew( CrmQrcDto inCrmQrcDto, ObjectFactory inBillingFactory )
    {
        InstantOfferAllocation billingRequest = null;
        if ( StringUtils.isValidObj( inCrmQrcDto ) )
        {
            billingRequest = inBillingFactory.createInstantOfferAllocation();
            CrmPojoToBilling.covertToBilling( billingRequest, inCrmQrcDto.getCrmPlanDetailsPojo(), false );
            billingRequest.setCustomerNo( StringUtils.numericValue( inCrmQrcDto.getCustomerId() ) );
            /**/
        }
        return billingRequest;
    }
}
