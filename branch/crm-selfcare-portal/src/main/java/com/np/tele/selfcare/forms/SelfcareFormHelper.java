package com.np.tele.selfcare.forms;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.utils.ICRMValidationCriteriaUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.common.utils.ValidationUtil;
import com.np.validator.util.ValidationPojoUtil;

public class SelfcareFormHelper
{
    private static final Logger LOGGER = Logger.getLogger( SelfcareFormHelper.class );

    public static void validateSelfcareForm( SelfcareForm inSelfcareForm, ActionMessages inErrors )
    {
        LOGGER.info( "in SelfcareFormHelper : validateSelfcareForm" );
        if ( StringUtils.isNotBlank( inSelfcareForm.getCustMyAccountPojo().getPassword() ) )
        {
            LOGGER.info( "validate custMyAccountPojo for changePassword" );
            Map<String, Object[]> resultMap = ValidationPojoUtil
                    .validateForm( inSelfcareForm.getCustMyAccountPojo(),
                                   ICRMValidationCriteriaUtil.FORM_SELFCARE_CHANGEPSWRD, false );
            ValidationUtil.prepareErrorMessage( inErrors, resultMap );
        }
        else
        {
            inErrors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
        }
    }

    public static void resetSelfcareForm( SelfcareForm inSelfcareForm, String inMethodName )
    {
        LOGGER.info( "Inside SelfcareFormHelper : resetSelfcareForm" );
        if ( StringUtils.equals( IAppConstants.METHOD_CHANGE_PASSWORD_PAGE, inMethodName ) )
        {
            LOGGER.info( "Inside resetSelfcareForm :: " + inMethodName );
            inSelfcareForm.getCustMyAccountPojo().setPassword( null );
            inSelfcareForm.getCustMyAccountPojo().setPassword1( null );
            inSelfcareForm.setRetypePassword( null );
        }
        else if ( StringUtils.equals( IAppConstants.METHOD_MYACCOUNTPAGE, inMethodName ) )
        {
            LOGGER.info( "Inside resetSelfcareForm :: " + inMethodName );
            inSelfcareForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            inSelfcareForm.setCustMyAccountPojo( new CrmCustMyAccountPojo() );
        }
    }

    public static void validateLogTicket( SelfcareForm inSelfcareForm, ActionMessages inErrors )
    {
        Map<String, Object[]> resultMap = ValidationPojoUtil.validateForm( inSelfcareForm
                .getCrmSelfcareCategoriesPojo(), ICRMValidationCriteriaUtil.FORM_SELFCARE_LOG_TICKET, false );
        if ( !StringUtils.isValidObj( resultMap ) || resultMap.isEmpty() )
        {
            resultMap = ValidationPojoUtil.validateForm( inSelfcareForm.getTicketHistoryPojo(),
                                                         ICRMValidationCriteriaUtil.FORM_SELFCARE_LOG_TICKET_REMARKS,
                                                         false );
        }
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
    }

    public static void validateBillAddr( SelfcareForm inSelfcareForm, ActionMessages inErrors )
    {
        LOGGER.info( "validate billaddrpojo for update bill addr" );
        Map<String, Object[]> resultMap = ValidationPojoUtil
                .validateForm( inSelfcareForm.getBillingAddressPojo(),
                               ICRMValidationCriteriaUtil.FORM_SELFCARE_UPDATE_BILLING_ADDRESS, false );
        ValidationUtil.prepareErrorMessage( inErrors, resultMap );
    }
}
