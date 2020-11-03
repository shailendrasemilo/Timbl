package com.np.tele.selfcare.forms;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.common.utils.ValidationUtil;
import com.np.validator.util.ValidationPojoUtil;

public class SelfcareLoginFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( SelfcareLoginFormHelper.class );

    public static void validateLogin( SelfcareLoginForm inLoginForm, ActionMessages inErrors )
    {
        LOGGER.info( "in SelfcareLoginFormHelper:validateLogin" );
        if ( StringUtils.isValidObj( inLoginForm.getCustMyAccountPojo() ) )
        {
            if ( StringUtils.isNotBlank( inLoginForm.getCustMyAccountPojo().getPassword() ) )
            {
                Map<String, Object[]> resultMap = ValidationPojoUtil.validateSingleFormProperty( inLoginForm
                        .getCustMyAccountPojo(), ICRMValidationCriteriaUtil.FORM_SELFCARE_LOGIN_OPTIONS, false );
                ValidationUtil.prepareErrorMessage( inErrors, resultMap );
            }
            //            if ( StringUtils.isNotBlank( inLoginForm.getCustMyAccountPojo().getCustomerId() ) )
            //            {
            //                LOGGER.info( "validate login custid" );
            //                Map<String, Object[]> resultMap = ValidationPojoUtil
            //                        .validateForm( inLoginForm.getCustMyAccountPojo(),
            //                                       ICRMValidationCriteriaUtil.FORM_SELFCARE_LOGIN_CUSTID, false );
            //                ValidationUtil.prepareErrorMessage( inErrors, resultMap );
            //            }
            //            else if ( StringUtils.isNotBlank( inLoginForm.getCustMyAccountPojo().getCustEmailId() ) )
            //            {
            //                LOGGER.info( "validate login email" );
            //                Map<String, Object[]> resultMap = ValidationPojoUtil
            //                        .validateForm( inLoginForm.getCustMyAccountPojo(),
            //                                       ICRMValidationCriteriaUtil.FORM_SELFCARE_LOGIN_EMAIL, false );
            //                ValidationUtil.prepareErrorMessage( inErrors, resultMap );
            //            }
            //            else if ( inLoginForm.getCustMyAccountPojo().getRmn() > 0 )
            //            {
            //                LOGGER.info( "validate login rmn" );
            //                Map<String, Object[]> resultMap = ValidationPojoUtil
            //                        .validateForm( inLoginForm.getCustMyAccountPojo(),
            //                                       ICRMValidationCriteriaUtil.FORM_SELFCARE_LOGIN_RMN, false );
            //                ValidationUtil.prepareErrorMessage( inErrors, resultMap );
            //            }
            else
            {
                LOGGER.info( "customer id / email / rmn not supplied" );
                inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM806.getStatusCode() ) );
            }
        }
        else
        {
            LOGGER.info( "invalid object custMyAccountPojo" );
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM806.getStatusCode() ) );
        }
    }

    public static void valideateForgotPswrd( SelfcareLoginForm inLoginForm, ActionMessages inErrors )
    {
        LOGGER.info( "in SelfcareLoginFormHelper:valideateForgotPswrd" );
        if ( StringUtils.isNotBlank( inLoginForm.getCustomerDetailsPojo().getCustomerId() ) )
        {
            LOGGER.info( "validate forgotPswrd custID" );
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inLoginForm.getCustomerDetailsPojo(),
                                   ICRMValidationCriteriaUtil.FORM_SELFCARE_FORGOTPSWRD, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
        }
    }
}
