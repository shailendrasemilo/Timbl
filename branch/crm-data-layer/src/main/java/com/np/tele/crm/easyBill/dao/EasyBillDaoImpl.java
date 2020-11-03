package com.np.tele.crm.easyBill.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.dto.EasyBillPaymentDto;
import com.np.tele.crm.dto.EasyBillRequestDto;
import com.np.tele.crm.dto.EasyBillResponseDto;
import com.np.tele.crm.ext.pojos.EasyBillCustomerDetailsPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.qrc.dao.IQrcManagerDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class EasyBillDaoImpl
    implements IEasyBillDao
{
    private static final Logger LOGGER        = Logger.getLogger( EasyBillDaoImpl.class );
    private IQrcManagerDao      qrcManagerDao = null;

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao inQrcManagerDao )
    {
        qrcManagerDao = inQrcManagerDao;
    }

    @Override
    public EasyBillResponseDto getCustomerDetails( EasyBillRequestDto inEasyBillRequestDto,
                                                   EasyBillResponseDto inResponseDto )
    {
        LOGGER.info( "inside :getCustomerDetails " );
        List<CrmCustomerDetailsPojo> crmCustomerDetailsList = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM614;
        try
        {
            Map<String, Object> criteriaMap = new HashMap<String, Object>();
            if ( StringUtils.isValidObj( inEasyBillRequestDto ) )
            {
                if ( StringUtils.isNotBlank( inEasyBillRequestDto.getCustomerId() ) )
                {
                    criteriaMap.put( "customerId", inEasyBillRequestDto.getCustomerId() );
                }
                else if ( StringUtils.isNotBlank( inEasyBillRequestDto.getRmn() ) )
                {
                    criteriaMap.put( "rmn", Long.parseLong( inEasyBillRequestDto.getRmn() ) );
                }
                crmCustomerDetailsList = CRMServiceUtils.getDBValueList( CrmCustomerDetailsPojo.class, criteriaMap );
                if ( CommonValidator.isValidCollection( crmCustomerDetailsList ) )
                {
                    List<EasyBillCustomerDetailsPojo> easyBillCustPojos = new ArrayList<EasyBillCustomerDetailsPojo>();
                    EasyBillCustomerDetailsPojo billCustDetailsPojo = null;
                    for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : crmCustomerDetailsList )
                    {
                        if ( StringUtils.isNotBlank( crmCustomerDetailsPojo.getCustomerId() ) )
                        {
                            billCustDetailsPojo = new EasyBillCustomerDetailsPojo();
                            billCustDetailsPojo.setCrfNo( crmCustomerDetailsPojo.getCrfId() );
                            billCustDetailsPojo.setCustomerId( crmCustomerDetailsPojo.getCustomerId() );
                            billCustDetailsPojo.setRmn( crmCustomerDetailsPojo.getRmn() );
                            billCustDetailsPojo.setRtn( crmCustomerDetailsPojo.getRtn() );
                            billCustDetailsPojo.setEmailId( crmCustomerDetailsPojo.getCustEmailId() );
                            billCustDetailsPojo.setBalance( setBalance( crmCustomerDetailsPojo.getCustomerId(),
                                                                        crmCustomerDetailsPojo.getServiceType() ) );
                            easyBillCustPojos.add( billCustDetailsPojo );
                        }
                    }
                    inResponseDto.setEasyBillCustDetailsPojos( easyBillCustPojos );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while getCustomerDetails", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
        }
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }

    private Double setBalance( String inCustomerId, String inServiceType )
    {
        CrmBillingDto billingDto = getQrcManagerDao().getAdditionalDetails( inCustomerId, inServiceType );
        if ( StringUtils.isValidObj( billingDto.getCustAdditionalDetails() ) )
        {
            return billingDto.getCustAdditionalDetails().getBalance();
        }
        return 0.0;
    }

    @Override
    public EasyBillResponseDto postPayment( EasyBillRequestDto inEasyBillRequestDto,
                                            EasyBillPaymentDto inEasyBillPaymentDto,
                                            EasyBillResponseDto inResponseDto )
    {
        LOGGER.info( "inside :postPayment " );
        CrmPaymentDetailsPojo paymentDetailsPojo;
        CrmFinanceDto crmFinanceDto;
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            if ( StringUtils.isValidObj( inEasyBillRequestDto ) && StringUtils.isValidObj( inEasyBillPaymentDto ) )
            {
                crmCustomerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                      inEasyBillPaymentDto.getCustomerId() );
                if ( StringUtils.isValidObj( crmCustomerDetailsPojo ) )
                {
                    paymentDetailsPojo = new CrmPaymentDetailsPojo();
                    if ( StringUtils.isValidObj( crmCustomerDetailsPojo.getActivationDate() ) )
                    {
                        paymentDetailsPojo.setInstallationStatus( CRMDisplayListConstants.POSTINSTALLATION.getCode() );
                    }
                    else
                    {
                        paymentDetailsPojo.setInstallationStatus( CRMDisplayListConstants.PREINSTALLATION.getCode() );
                    }
                    crmFinanceDto.setUserId( inEasyBillRequestDto.getAuthUsername() );
                    paymentDetailsPojo.setCustomerId( inEasyBillPaymentDto.getCustomerId() );
                    paymentDetailsPojo.setAmount( inEasyBillPaymentDto.getAmount().doubleValue() );
                    paymentDetailsPojo.setPaymentMode( CRMDisplayListConstants.ONLINE_PAYMENT.getCode() );
                    paymentDetailsPojo.setPaymentChannel( CRMDisplayListConstants.EASYBILL.getCode() );
                    paymentDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    paymentDetailsPojo.setPaymentStatus( CRMDisplayListConstants.PAYMENT_RECIEVED.getCode() );
                    paymentDetailsPojo.setTransactionId( inEasyBillPaymentDto.getTransactionId() );
                    paymentDetailsPojo.setCreatedBy( inEasyBillRequestDto.getAuthUsername() );
                    paymentDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    paymentDetailsPojo.setCustomerRecordId( crmCustomerDetailsPojo.getRecordId() );
                    paymentDetailsPojo.setEntityType( CRMDisplayListConstants.TELESERVICES.getCode() );
                    paymentDetailsPojo.setPaymentDate( Calendar.getInstance().getTime() );
                    crmFinanceDto.setPaymentDetailsPojo( paymentDetailsPojo );
                    crmFinanceDto = CRMServicesProxy
                            .getInstance()
                            .getCRMFinanceService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                            .postPayment( ServiceParameter.POST.getParameter(), CRMParameter.PAYMENT.getParameter(),
                                          crmFinanceDto );
                    if ( StringUtils.isValidObj( crmFinanceDto )
                            && StringUtils
                                    .equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        LOGGER.info( "Successfully post single payment" );
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    else
                    {
                        LOGGER.info( CRMServiceCode.CRM616.getStatusDesc() + " due to " + crmFinanceDto.getStatusCode()
                                + ":" + crmFinanceDto.getStatusDesc() + ":" + crmFinanceDto.getBillingErrorCode() );
                        crmServiceCode = CRMServiceCode.CRM616;
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM603;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while postPayment", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }

    @Override
    public EasyBillResponseDto getPaymentStatus( EasyBillRequestDto inEasyBillRequestDto,
                                                 EasyBillResponseDto inResponseDto )
    {
        LOGGER.info( "inside :getPaymentStatus " );
        CrmPaymentDetailsPojo paymentDetailsPojo;
        CrmFinanceDto crmFinanceDto;
        EasyBillPaymentDto easyBillPaymentPojo;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            crmFinanceDto = new CrmFinanceDto();
            paymentDetailsPojo = new CrmPaymentDetailsPojo();
            if ( StringUtils.isValidObj( inEasyBillRequestDto ) )
            {
                boolean toProcess = false;
                if ( StringUtils.isValidObj( inEasyBillRequestDto.getPaymentDate() ) )
                {
                    crmFinanceDto.setFromDate( DateUtils.convertXmlCalToString( DateUtils
                            .toXMLGregorianCalendar( inEasyBillRequestDto.getPaymentDate() ) ) );
                    crmFinanceDto.setToDate( DateUtils.convertXmlCalToString( DateUtils
                            .toXMLGregorianCalendar( inEasyBillRequestDto.getPaymentDate() ) ) );
                    toProcess = true;
                }
                else if ( StringUtils.isNotBlank( inEasyBillRequestDto.getTransactionId() ) )
                {
                    paymentDetailsPojo.setTransactionId( inEasyBillRequestDto.getTransactionId() );
                    toProcess = true;
                }
                if ( toProcess )
                {
                    paymentDetailsPojo.setPaymentMode( CRMDisplayListConstants.ONLINE_PAYMENT.getCode() );
                    paymentDetailsPojo.setPaymentChannel( CRMDisplayListConstants.EASYBILL.getCode() );
                    crmFinanceDto.setPaymentDetailsPojo( paymentDetailsPojo );
                    crmFinanceDto = CRMServicesProxy
                            .getInstance()
                            .getCRMFinanceService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                            .trackPayment( ServiceParameter.TRACK.getParameter(), CRMParameter.PAYMENT.getParameter(),
                                           crmFinanceDto );
                    if ( StringUtils.isValidObj( crmFinanceDto )
                            && StringUtils
                                    .equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                    {
                        List<EasyBillPaymentDto> easyBillPaymentPojos = new ArrayList<EasyBillPaymentDto>();
                        if ( CommonValidator.isValidCollection( crmFinanceDto.getPaymentDetailsPojos() ) )
                        {
                            LOGGER.info( "Size of PaymentDetailsPojo ::"
                                    + crmFinanceDto.getPaymentDetailsPojos().size() );
                            for ( CrmPaymentDetailsPojo crmPaymentDetailsPojo : crmFinanceDto.getPaymentDetailsPojos() )
                            {
                                easyBillPaymentPojo = new EasyBillPaymentDto();
                                easyBillPaymentPojo.setAmount( BigDecimal.valueOf( crmPaymentDetailsPojo.getAmount() ) );
                                easyBillPaymentPojo.setTransactionId( crmPaymentDetailsPojo.getTransactionId() );
                                easyBillPaymentPojo.setStatus( CRMStatusCode.getStatus( crmPaymentDetailsPojo
                                        .getStatus() ) );
                                CrmCustomerDetailsPojo customerPojo = CRMServiceUtils
                                        .getDBValues( CrmCustomerDetailsPojo.class,
                                                      crmPaymentDetailsPojo.getCustomerRecordId() );
                                if ( StringUtils.isValidObj( customerPojo ) )
                                {
                                    easyBillPaymentPojo.setCustomerId( customerPojo.getCustomerId() );
                                }
                                easyBillPaymentPojos.add( easyBillPaymentPojo );
                            }
                            crmServiceCode = CRMServiceCode.CRM001;
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM996;
                        }
                        inResponseDto.setEasyBillPaymentList( easyBillPaymentPojos );
                    }
                    else
                    {
                        LOGGER.info( "Unable to retrieve Easy Bill payments due to " + crmFinanceDto.getStatusCode()
                                + ":" + crmFinanceDto.getStatusDesc() + ":" + crmFinanceDto.getBillingErrorCode() );
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM997;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while getPaymentStatus", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        inResponseDto.setStatusCode( crmServiceCode.getStatusCode() );
        inResponseDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inResponseDto;
    }
}
