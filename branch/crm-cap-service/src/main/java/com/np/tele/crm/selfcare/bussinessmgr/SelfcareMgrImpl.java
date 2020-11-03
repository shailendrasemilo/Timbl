package com.np.tele.crm.selfcare.bussinessmgr;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.SelfcareDto;
import com.np.tele.crm.pojos.CrmCustMyAccountPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.selfcare.dao.ISelfcareDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.validator.util.ValidationPojoUtil;

public class SelfcareMgrImpl
    implements ISelfcareMgr
{
    private static final Logger LOGGER      = Logger.getLogger( SelfcareMgrImpl.class );
    private ISelfcareDao        selfcareDao = null;

    public ISelfcareDao getSelfcareDao()
    {
        return selfcareDao;
    }

    public void setSelfcareDao( ISelfcareDao inSelfcareDao )
    {
        selfcareDao = inSelfcareDao;
    }

    @Override
    public SelfcareDto selfcareOperation( String inServiceParam, String inOperationParam, SelfcareDto inSelfcareDto )
    {
        LOGGER.info( "in SelfcareMgrImpl:selfcareOperation" );
        invalidRequest( inSelfcareDto );
        if ( StringUtils.equals( ServiceParameter.VALIDATE.getParameter(), inServiceParam ) )
        {
            if ( StringUtils.equals( CrmCustMyAccountPojo.class.getSimpleName(), inOperationParam ) )
            {
                inSelfcareDto = validateSelfcareAccount( inSelfcareDto );
            }
            else if ( StringUtils.equals( CrmCustomerDetailsPojo.class.getSimpleName(), inOperationParam ) )
            {
                inSelfcareDto = validateQuickPayDetails( inSelfcareDto );
            }
        }
        else if ( StringUtils.equals( ServiceParameter.UPDATE.getParameter(), inServiceParam )
                && StringUtils.equals( CrmCustMyAccountPojo.class.getSimpleName(), inOperationParam ) )
        {
            inSelfcareDto = changeSelfcareAccountPassword( inSelfcareDto );
        }
        else if ( StringUtils.equals( ServiceParameter.RESET.getParameter(), inServiceParam )
                && StringUtils.equals( CrmCustMyAccountPojo.class.getSimpleName(), inOperationParam ) )
        {
            inSelfcareDto = resetSelfcareAccountPassword( inSelfcareDto );
        }
        return inSelfcareDto;
    }

    private SelfcareDto validateSelfcareAccount( SelfcareDto inSelfcareDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmCustMyAccountPojo custAccountPojo = inSelfcareDto.getCustMyAccountPojo();
        if ( StringUtils.isValidObj( custAccountPojo ) )
        {
            if ( StringUtils.isNotBlank( custAccountPojo.getPassword() ) )
            {
                if ( StringUtils.isBlank( custAccountPojo.getCustomerId() ) )
                {
                    if ( !ValidationPojoUtil
                            .validateSinglePojoProperty( custAccountPojo,
                                                         ICRMValidationCriteriaUtil.SELFCARE_LOGIN_OPTIONS ) )
                    {
                        inSelfcareDto.setStatusCode( CRMServiceCode.CRM806.getStatusCode() );
                        inSelfcareDto.setStatusDesc( CRMServiceCode.CRM806.getStatusDesc() );
                        return inSelfcareDto;
                    }
                    List<CrmCustomerDetailsPojo> custDetails = getSelfcareDao()
                            .getCustomerDetailAccounts( custAccountPojo );
                    if ( CommonValidator.isValidCollection( custDetails ) )
                    {
                        if ( custDetails.size() > 1 )
                        {
                            List<String> custIds = new ArrayList<String>();
                            for ( CrmCustomerDetailsPojo custDetail : custDetails )
                            {
                                custIds.add( custDetail.getCustomerId() );
                            }
                            LOGGER.info( "found " + custIds.size() + " customer ids:" + custIds );
                            List<CrmCustMyAccountPojo> custMyAccountPojos = getSelfcareDao()
                                    .getCustomerMyAccounts( custIds, custAccountPojo.getPassword() );
                            if ( CommonValidator.isValidCollection( custMyAccountPojos ) )
                            {
                                if ( custMyAccountPojos.size() > 1 )
                                {
                                    custIds.clear();
                                    for ( CrmCustMyAccountPojo accountPojo : custMyAccountPojos )
                                    {
                                        custIds.add( accountPojo.getCustomerId() );
                                    }
                                    custDetails = getSelfcareDao().getCustomerDetailAccounts( custIds );
                                    inSelfcareDto.setCustomerDetailsPojos( custDetails );
                                    serviceCode = CRMServiceCode.CRM801;
                                    inSelfcareDto.setStatusCode( serviceCode.getStatusCode() );
                                    inSelfcareDto.setStatusDesc( serviceCode.getStatusDesc() );
                                    return inSelfcareDto;
                                }
                                else
                                {
                                    LOGGER.info( "found myaccount id: " + custMyAccountPojos.get( 0 ).getCustomerId() );
                                    custAccountPojo.setCustomerId( custMyAccountPojos.get( 0 ).getCustomerId() );
                                }
                            }
                        }
                        else
                        {
                            LOGGER.info( "found customer id: " + custDetails.get( 0 ).getCustomerId() );
                            custAccountPojo.setCustomerId( custDetails.get( 0 ).getCustomerId() );
                        }
                    }
                    else
                    {
                        LOGGER.info( "no account associated" );
                        serviceCode = CRMServiceCode.CRM804;
                        inSelfcareDto.setStatusCode( serviceCode.getStatusCode() );
                        inSelfcareDto.setStatusDesc( serviceCode.getStatusDesc() );
                        return inSelfcareDto;
                    }
                }
                LOGGER.info( "going to authenticate" );
                CrmCustMyAccountPojo myAccountPojo = getSelfcareDao().authenticate( custAccountPojo );
                if ( StringUtils.isValidObj( myAccountPojo ) )
                {
                    LOGGER.info( "successfully authenticated" );
                    CrmCustomerDetailsPojo customerDetailsPojoDB = CRMServiceUtils
                            .getDBValues( CrmCustomerDetailsPojo.class, "customerId", myAccountPojo.getCustomerId() );
                    if ( StringUtils.equals( customerDetailsPojoDB.getStatus(), CRMStatusCode.PDC.getStatusCode() ) )
                    {
                        serviceCode = CRMServiceCode.CRM810;
                    }
                    else
                    {
                        inSelfcareDto.setCustomerDetailsPojo( customerDetailsPojoDB );
                        inSelfcareDto.setCustMyAccountPojo( myAccountPojo );
                        serviceCode = CRMServiceCode.CRM001;
                    }
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM804;
                }
            }
        }
        inSelfcareDto.setStatusCode( serviceCode.getStatusCode() );
        inSelfcareDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inSelfcareDto;
    }

    private SelfcareDto changeSelfcareAccountPassword( SelfcareDto inSelfcareDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmCustMyAccountPojo custMyAccountPojo = inSelfcareDto.getCustMyAccountPojo();
        if ( StringUtils.isValidObj( custMyAccountPojo ) )
        {
            if ( StringUtils.isBlank( custMyAccountPojo.getCustomerId() )
                    || StringUtils.isBlank( custMyAccountPojo.getPassword() )
                    || StringUtils.isBlank( custMyAccountPojo.getPassword1() ) )
            {
                serviceCode = CRMServiceCode.CRM803;
                LOGGER.info( "Old Password or New assword is empty" );
            }
            else
            {
                serviceCode = getSelfcareDao().changePassword( custMyAccountPojo );
            }
        }
        inSelfcareDto.setStatusCode( serviceCode.getStatusCode() );
        inSelfcareDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inSelfcareDto;
    }

    private SelfcareDto resetSelfcareAccountPassword( SelfcareDto inSelfcareDto )
    {
        LOGGER.info( "inside SelfcareMgrImpl: resetSelfcareAccountPassword" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmCustomerDetailsPojo customerDetailsPojo = inSelfcareDto.getCustomerDetailsPojo();
        if ( StringUtils.isValidObj( customerDetailsPojo ) )
        {
            if ( StringUtils.isBlank( customerDetailsPojo.getCustomerId() ) || customerDetailsPojo.getRmn() == 0 )
            {
                LOGGER.info( "customer id or rmn is blank" );
                serviceCode = CRMServiceCode.CRM805;
            }
            else
            {
                serviceCode = getSelfcareDao().resetPassword( customerDetailsPojo );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), serviceCode.getStatusCode() ) )
                {
                    CRMServiceUtils.sendAlerts( CRMEvents.RESET_MY_ACCOUNT_PASSWORD, CRMRequestType.CUSTOMER_ID,
                                                customerDetailsPojo.getCustomerId(), null );
                }
            }
        }
        inSelfcareDto.setStatusCode( serviceCode.getStatusCode() );
        inSelfcareDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inSelfcareDto;
    }

    private SelfcareDto validateQuickPayDetails( SelfcareDto inSelfcareDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmCustomerDetailsPojo customerDetailsPojo = inSelfcareDto.getCustomerDetailsPojo();
        String customerId = null;
        List<CrmCustomerDetailsPojo> customerDetails = null;
        if ( StringUtils.isValidObj( customerDetailsPojo ) )
        {
            if ( customerDetailsPojo.getRmn() > 0 )
            {
                customerDetails = getSelfcareDao().getCustomerDetailsList( customerDetailsPojo );
                if ( CommonValidator.isValidCollection( customerDetails ) && customerDetails.size() == 1 )
                {
                    customerId = customerDetails.get( 0 ).getCustomerId();
                    customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                       customerId );
                }
            }
            else
            {
                customerId = customerDetailsPojo.getCustomerId();
                customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                   customerId );
            }
            if ( StringUtils.isNotBlank( customerId ) && StringUtils.isValidObj( customerDetailsPojo ) )
            {
                inSelfcareDto.setCustomerDetailsPojo( customerDetailsPojo );
                serviceCode = CRMServiceCode.CRM001;
            }
            else if ( CommonValidator.isValidCollection( customerDetails ) && customerDetails.size() > 1 )
            {
                inSelfcareDto.setCustomerDetailsPojos( customerDetails );
                serviceCode = CRMServiceCode.CRM801;
            }
            else
            {
                serviceCode = CRMServiceCode.CRM809;
            }
        }
        inSelfcareDto.setStatusCode( serviceCode.getStatusCode() );
        inSelfcareDto.setStatusDesc( serviceCode.getStatusDesc() );
        return inSelfcareDto;
    }

    private void invalidRequest( SelfcareDto inSelfcareDto )
    {
        inSelfcareDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        inSelfcareDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
    }
}
