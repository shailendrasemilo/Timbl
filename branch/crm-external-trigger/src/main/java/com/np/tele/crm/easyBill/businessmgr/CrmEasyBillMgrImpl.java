package com.np.tele.crm.easyBill.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.EasyBillPaymentDto;
import com.np.tele.crm.dto.EasyBillRequestDto;
import com.np.tele.crm.dto.EasyBillResponseDto;
import com.np.tele.crm.easyBill.dao.IEasyBillDao;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.ValidationPojoUtil;

public class CrmEasyBillMgrImpl
    implements ICrmEasyBillMgr
{
    private static final Logger LOGGER      = Logger.getLogger( CrmEasyBillMgrImpl.class );
    private IEasyBillDao        easyBillDao = null;

    public IEasyBillDao getEasyBillDao()
    {
        return easyBillDao;
    }

    public void setEasyBillDao( IEasyBillDao inEasyBillDao )
    {
        easyBillDao = inEasyBillDao;
    }

    @Override
    public EasyBillResponseDto getCustomerDetails( EasyBillRequestDto inEasyBillRequestDto )
    {
        LOGGER.info( "IN getCustomerDetails method:" + inEasyBillRequestDto );
        EasyBillResponseDto responseDto = new EasyBillResponseDto();
        String statusCode = "";
        if ( StringUtils.isValidObj( inEasyBillRequestDto ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inEasyBillRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EASY_BILL_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                if ( !ValidationPojoUtil
                        .validateSinglePojoProperty( inEasyBillRequestDto,
                                                     ICRMValidationCriteriaUtil.EASY_BILL_CRITERIA_OPTION_FOR_CUSTOMERID_AND_RMN ) )
                {
                    statusCode = CRMServiceCode.CRM614.getStatusCode();
                }
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                responseDto = getEasyBillDao().getCustomerDetails( inEasyBillRequestDto, responseDto );
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }

    @Override
    public EasyBillResponseDto postPayment( EasyBillRequestDto inEasyBillRequestDto,
                                            EasyBillPaymentDto inEasyBillPaymentDto )
    {
        LOGGER.info( "IN postPayment method:" + inEasyBillPaymentDto );
        EasyBillResponseDto responseDto = new EasyBillResponseDto();
        String statusCode = "";
        if ( StringUtils.isValidObj( inEasyBillRequestDto ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inEasyBillRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EASY_BILL_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) && StringUtils.isValidObj( inEasyBillPaymentDto ) )
            {
                statusCode = ValidationPojoUtil
                        .validatePojo( inEasyBillPaymentDto, ICRMValidationCriteriaUtil.EASY_BILL_PAYMENT_DTO_CRITERIA );
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                responseDto = getEasyBillDao().postPayment( inEasyBillRequestDto, inEasyBillPaymentDto, responseDto );
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }

    @Override
    public EasyBillResponseDto getPaymentStatus( EasyBillRequestDto inEasyBillRequestDto )
    {
        LOGGER.info( "IN getPaymentStatus method:" + inEasyBillRequestDto );
        EasyBillResponseDto responseDto = new EasyBillResponseDto();
        String statusCode = "";
        if ( StringUtils.isValidObj( inEasyBillRequestDto ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inEasyBillRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EASY_BILL_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) && StringUtils.isValidObj( inEasyBillRequestDto.getPaymentDate() ) )
            {
                statusCode = ValidationPojoUtil
                        .validatePojo( inEasyBillRequestDto, ICRMValidationCriteriaUtil.EASY_BILL_PAYMENT_DATE_CRITERIA );
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                responseDto = getEasyBillDao().getPaymentStatus( inEasyBillRequestDto, responseDto );
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }

    private void setResponseStatus( String statusCode, EasyBillResponseDto responseDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.valueOf( statusCode );
        responseDto.setStatusCode( serviceCode.getStatusCode() );
        responseDto.setStatusDesc( serviceCode.getStatusDesc() );
    }

    @Override
    public EasyBillResponseDto getTransactionStatus( EasyBillRequestDto inEasyBillRequestDto )
    {
        LOGGER.info( "IN getTransactionStatus method:" + inEasyBillRequestDto );
        EasyBillResponseDto responseDto = new EasyBillResponseDto();
        String statusCode = "";
        if ( StringUtils.isValidObj( inEasyBillRequestDto ) )
        {
            statusCode = ValidationPojoUtil.validatePojo( inEasyBillRequestDto,
                                                          ICRMValidationCriteriaUtil.COMMON_EASY_BILL_CRITERIA );
            if ( StringUtils.isEmpty( statusCode ) )
            {
                statusCode = ValidationPojoUtil
                        .validatePojo( inEasyBillRequestDto,
                                       ICRMValidationCriteriaUtil.COMMON_EASY_BILL_TRANSACTION_STATUS_CRITERIA );
            }
            if ( StringUtils.isEmpty( statusCode ) )
            {
                responseDto = getEasyBillDao().getPaymentStatus( inEasyBillRequestDto, responseDto );
            }
            else
            {
                setResponseStatus( statusCode, responseDto );
            }
        }
        return responseDto;
    }
}
