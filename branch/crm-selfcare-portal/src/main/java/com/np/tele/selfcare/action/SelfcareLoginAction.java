package com.np.tele.selfcare.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.SelfcareDto;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.common.utils.SelfcareUtils;
import com.np.tele.selfcare.forms.SelfcareLoginForm;
import com.np.tele.selfcare.forms.SelfcareLoginFormHelper;

public class SelfcareLoginAction
    extends DispatchAction
{
    private final Logger     LOGGER          = Logger.getLogger( SelfcareLoginAction.class );
    private ISelfcareManager selfcareManager = null;

    public ISelfcareManager getSelfcareManager()
    {
        return selfcareManager;
    }

    public void setSelfcareManager( ISelfcareManager inSelfcareManager )
    {
        selfcareManager = inSelfcareManager;
    }

    @Override
    public ActionForward unspecified( ActionMapping inMapping,
                                      ActionForm inForm,
                                      HttpServletRequest inRequest,
                                      HttpServletResponse inResponse )
        throws Exception
    {
        LOGGER.info( "in default action :: method is " + inRequest.getParameter( IAppConstants.PARAMETER_NAME ) );
        return inMapping.findForward( IActionForwardConstant.SELFCARE_INDEX );
    }

    public ActionForward loginPage( final ActionMapping inMapping,
                                    final ActionForm inForm,
                                    final HttpServletRequest inRequest,
                                    final HttpServletResponse inResponse )
    {
        LOGGER.info( "inside loginPage" );
        SelfcareLoginForm loginForm = (SelfcareLoginForm) inForm;
        loginForm.setCustMyAccountPojo( new CrmCustMyAccountPojo() );
        loginForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        return inMapping.findForward( IActionForwardConstant.SELFCARE_LOGIN_PAGE_POPUP );
    }

    public ActionForward logout( final ActionMapping inMapping,
                                 final ActionForm inForm,
                                 final HttpServletRequest inRequest,
                                 final HttpServletResponse inResponse )
    {
        LOGGER.info( "inside logoutPage" );
        SelfcareLoginForm loginForm = (SelfcareLoginForm) inForm;
        loginForm.setCustMyAccountPojo( new CrmCustMyAccountPojo() );
        loginForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        HttpSession session = inRequest.getSession( false );
        if ( StringUtils.isValidObj( session ) )
        {
            session.removeAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
            session.removeAttribute( CrmCustomerDetailsPojo.class.getSimpleName() );
        }
        return inMapping.findForward( IActionForwardConstant.SELFCARE_INDEX );
    }

    public ActionForward authenticate( final ActionMapping inMapping,
                                       final ActionForm inForm,
                                       final HttpServletRequest inRequest,
                                       final HttpServletResponse inResponse )
    {
        LOGGER.info( "in SelfcareLoginAction:authenticate" );
        boolean authenticated = false;
        String forward = IActionForwardConstant.SELFCARE_LOGIN_PAGE_POPUP;
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareLoginForm loginForm = (SelfcareLoginForm) inForm;
        HttpSession session = null;
        SelfcareLoginFormHelper.validateLogin( loginForm, errors );
        if ( StringUtils.isValidObj( errors ) && errors.isEmpty() )
        {
            session = inRequest.getSession( true );
            if ( !StringUtils.isValidObj( session.getAttribute( IAppConstants.SELFCARE_UTILS ) ) )
            {
                session.setAttribute( IAppConstants.SELFCARE_UTILS, new SelfcareUtils() );
            }
            SelfcareDto selfcareDto = getSelfcareManager().authenticate( loginForm );
            if ( StringUtils.equals( selfcareDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                LOGGER.info( "authenticate successfully. " + selfcareDto.getCustomerDetailsPojo().getCustFname() );
                session.setAttribute( CrmCustMyAccountPojo.class.getSimpleName(), selfcareDto.getCustMyAccountPojo() );
                session.setAttribute( CrmCustomerDetailsPojo.class.getSimpleName(),
                                      selfcareDto.getCustomerDetailsPojo() );
                LOGGER.info( "Customer ID in authenticate is :: " + selfcareDto.getCustMyAccountPojo().getCustomerId() );
                loginForm.setCustMyAccountPojo( selfcareDto.getCustMyAccountPojo() );
                loginForm.setCustomerDetailsPojo( selfcareDto.getCustomerDetailsPojo() );
                forward = IActionForwardConstant.SELFCARE_HOME_PAGE;
                authenticated = true;
            }
            else if ( StringUtils.equals( selfcareDto.getStatusCode(), CRMServiceCode.CRM801.getStatusCode() ) )
            {
                LOGGER.info( selfcareDto.getStatusDesc() + " resulted accounts:"
                        + selfcareDto.getCustomerDetailsPojos().size() );
                messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( selfcareDto.getStatusCode() ) );
                loginForm.setCustomerDetailsPojos( selfcareDto.getCustomerDetailsPojos() );
                forward = IActionForwardConstant.SELFCARE_CHOOSE_ACCOUNT_POPUP;
            }
            else
            {
                LOGGER.info( selfcareDto.getStatusCode() + ": " + selfcareDto.getStatusDesc() );
                loginForm.getCustMyAccountPojo().setPassword( null );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( selfcareDto.getStatusCode() ) );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        if ( authenticated )
        {
            session.setAttribute( "next", "true" );
        }
        return inMapping.findForward( forward );
    }

    public ActionForward forgotPasswordPage( final ActionMapping inMapping,
                                             final ActionForm inForm,
                                             final HttpServletRequest inRequest,
                                             final HttpServletResponse inResponse )
    {
        LOGGER.info( "inside forgotPasswordPage" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        SelfcareLoginForm loginForm = (SelfcareLoginForm) inForm;
        loginForm.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( IActionForwardConstant.FORGOT_PASSWORD_POPUP );
    }

    public ActionForward forgotPassword( final ActionMapping inMapping,
                                         final ActionForm inForm,
                                         final HttpServletRequest inRequest,
                                         final HttpServletResponse inResponse )
    {
        String forward = IActionForwardConstant.FORGOT_PASSWORD_POPUP;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        SelfcareLoginForm loginForm = (SelfcareLoginForm) inForm;
        SelfcareLoginFormHelper.valideateForgotPswrd( loginForm, errors );
        if ( StringUtils.isValidObj( errors ) && errors.isEmpty() )
        {
            SelfcareDto selfcareDto = getSelfcareManager().forgotPassword( loginForm );
            if ( StringUtils.isValidObj( selfcareDto ) )
            {
                if ( StringUtils.equals( selfcareDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    forward = IActionForwardConstant.SELFCARE_LOGIN_PAGE_POPUP;
                    messages.add( IAppConstants.APP_MESSAGE,
                                  new ActionMessage( IPropertiesConstant.FORGET_PASSWORD_SUCCES ) );
                    LOGGER.info( "success forgotPassword and force to change password" );
                }
                else
                {
                    LOGGER.info( "Required details are not present" );
                    errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM997.getStatusCode() ) );
                }
            }
            else
            {
                LOGGER.info( "Unable to process the request" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( CRMServiceCode.CRM999.getStatusCode() ) );
            }
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }
}
