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

public class SelfcareQuickPayFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( SelfcareQuickPayFormHelper.class );

    public static void validateQuickPay( SelfcareQuickPayForm inQuickPayForm, ActionMessages inErrors )
    {
        LOGGER.info( "in SelfcareQuickPayFormHelper:validateQuickPay" );
        if ( StringUtils.isValidObj( inQuickPayForm.getCustomerDetailsPojo() ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil.validateSingleFormProperty( inQuickPayForm
                    .getCustomerDetailsPojo(), ICRMValidationCriteriaUtil.FORM_SELFCARE_QUICKPAY_OPTIONS, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else
        {
            LOGGER.info( "Invalid object custMyAccountPojo" );
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM809.getStatusCode() ) );
        }
    }

    public static ActionMessages validateQuickPayAmount( SelfcareQuickPayForm inQuickPayForm, ActionMessages inErrors )
    {
        if ( StringUtils.isValidObj( inQuickPayForm.getPaymentGatewayPojo() ) )
        {
            LOGGER.info( "in SelfcareQuickPayFormHelper:validateQuickPayAmount"
                    + inQuickPayForm.getPaymentGatewayPojo().getAmount() );
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQuickPayForm.getPaymentGatewayPojo(),
                                   ICRMValidationCriteriaUtil.FORM_SELFCARE_QUICKPAY_AMOUNT, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else
        {
            LOGGER.info( "Invalid object custMyAccountPojo" );
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM809.getStatusCode() ) );
        }
        return inErrors;
    }

    public static void validatePincode( SelfcareQuickPayForm inQuickPayForm, ActionMessages inErrors )
    {
        LOGGER.info( "In SelfcareQuickPayFormHelper :: validatePincode is :: "
                + inQuickPayForm.getPaymentCentresPojo().getPincode() );
        if ( StringUtils.isValidObj( inQuickPayForm.getPaymentCentresPojo() ) )
        {
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inQuickPayForm.getPaymentCentresPojo(),
                                   ICRMValidationCriteriaUtil.FORM_SELFCARE_PAYMENT_CENTER_PINCODE, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else
        {
            LOGGER.info( "Invalid object selfcareQuickPayForm" );
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM811.getStatusCode() ) );
        }
    }

    public static void reset( SelfcareQuickPayForm inForm, String inMethod )
    {
        inForm.setPaymentCentresPojos( null );
    }
}
