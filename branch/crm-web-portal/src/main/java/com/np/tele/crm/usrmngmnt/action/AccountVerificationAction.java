package com.np.tele.crm.usrmngmnt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.bm.IAccountVerification;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.StringUtils;

public class AccountVerificationAction
    extends DispatchAction
{
    private static final Logger  LOGGER                = Logger.getLogger( AccountVerificationAction.class );
    private IAccountVerification accountVerificationBm = null;

    public IAccountVerification getAccountVerificationBm()
    {
        return accountVerificationBm;
    }

    public void setAccountVerificationBm( IAccountVerification inAccountVerificationBm )
    {
        accountVerificationBm = inAccountVerificationBm;
    }

    public ActionForward verifyUser( final ActionMapping inMapping,
                                     final ActionForm inForm,
                                     final HttpServletRequest inRequest,
                                     final HttpServletResponse inResponse )
        throws Exception
    {
        String toForward = IActionForwardConstant.VERIFY_USER;
        String token = inRequest.getParameter( "sessionId" );
        LOGGER.info( "Given Token : " + token );
        LoginForm loginForm = (LoginForm) inForm;
        ActionMessages errors = new ActionMessages();
        CrmuserDetailsDto crmuserDetailsDto = null;
        CrmUserPojo crmUserPojo = new CrmUserPojo();
        loginForm.setCrmUserPojo( crmUserPojo );
        if ( StringUtils.isNotBlank( token ) )
        {
            crmuserDetailsDto = getAccountVerificationBm().validateUser( token );
            if ( StringUtils.isValidObj( crmuserDetailsDto ) )
            {
                if ( crmuserDetailsDto.getStatusCode().equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "verify user successfully." );
                    loginForm.setRecordID( crmuserDetailsDto.getCrmUserDetailsPojo().getRecordId() );
                    loginForm.setFirstName( crmuserDetailsDto.getCrmUserDetailsPojo().getFirstName() );
                    loginForm.setLastName( crmuserDetailsDto.getCrmUserDetailsPojo().getLastName() );
                    loginForm.getCrmUserPojo().setStatus( crmuserDetailsDto.getCrmUserDetailsPojo().getStatus() );
                }
                else
                {
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmuserDetailsDto.getStatusCode() ) );
                }
            }
            else
            {
                LOGGER.info( "crmUserDetails Dto found null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
        }
        else
        {
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM103.getStatusCode() ) );
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( toForward );
    }

    public ActionForward activateUser( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
        throws Exception
    {
        String toForward = IActionForwardConstant.LOGIN;
        LoginForm loginForm = (LoginForm) inForm;
        ActionMessages errors = new ActionMessages();
        CrmuserDetailsDto crmuserDetailsDto = null;
        if ( loginForm.getRecordID() != 0 )
        {
            crmuserDetailsDto = getAccountVerificationBm().activateUser( loginForm.getRecordID() );
            if ( StringUtils.isValidObj( crmuserDetailsDto ) )
            {
                if ( crmuserDetailsDto.getStatusCode().equals( CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    LOGGER.info( "account has been activated successfully." );
                }
                else
                {
                    toForward = IActionForwardConstant.VERIFY_USER;
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( crmuserDetailsDto.getStatusCode(),
                                                                            loginForm.getFirstName(),
                                                                            loginForm.getLastName() ) );
                    loginForm.setUserId( null );
                }
            }
            else
            {
                LOGGER.info( "crmUserDetails Dto found null " );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
            }
        }
        saveErrors( inRequest, errors );
        return inMapping.findForward( toForward );
    }

    public ActionForward verifyCustomerPage( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
        throws Exception
    {
        String toForward = IActionForwardConstant.VERIFY_USER;
        return inMapping.findForward( toForward );
    }

    public ActionForward activateCustomer( final ActionMapping inMapping,
                                           final ActionForm inForm,
                                           final HttpServletRequest inRequest,
                                           final HttpServletResponse inResponse )
        throws Exception
    {
        String toForward = IActionForwardConstant.LOGIN;
        return inMapping.findForward( toForward );
    }
}
