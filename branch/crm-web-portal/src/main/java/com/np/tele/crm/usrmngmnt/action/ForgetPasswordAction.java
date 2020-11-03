package com.np.tele.crm.usrmngmnt.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.Captcha;

public class ForgetPasswordAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( LoginAction.class );
    private IUserBMngr          usermngmntbm;

    public IUserBMngr getUsermngmntbm()
    {
        return usermngmntbm;
    }

    public void setUsermngmntbm( IUserBMngr usermngmntbm )
    {
        this.usermngmntbm = usermngmntbm;
    }

    public ActionForward forgetPasswordPage( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
    {
        return mapping.findForward( IActionForwardConstant.FORGET_PASSWORD_PAGE );
    }

    public ActionForward forgetPassword( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.FORGET_PASSWORD_PAGE;
        LoginForm loginForm = (LoginForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        try
        {
            String answer = request.getParameter( "answer" );
            String capcha = (String) request.getSession().getAttribute( Captcha.CAPTCHA_KEY );
            if ( null != answer && null != capcha )
            {
                if ( !capcha.equals( answer ) )
                {
                    LOGGER.info( "Wrong Captcha Entered By User" );
                    // ActionErrors actionErrors = new ActionErrors();
                    // actionErrors.add( "catchaReenter", new ActionMessage( IPropertiesConstant.ERROR_WRONG_CAPTCHA ) );
                    //  saveErrors( request, actionErrors);
                    messages.add( "captchaReenter", new ActionMessage( IPropertiesConstant.ERROR_WRONG_CAPTCHA ) );
                }
                else
                {
                    CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm()
                            .forgetPassword( loginForm.getEmailId(), loginForm.getUserId(),
                                             CRMParameter.FORGET_PASSWORD.getParameter() );
                    if ( null != crmUserDetailsDto )
                    {
                        String statusCode = crmUserDetailsDto.getStatusCode();
                        if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                        {
                            LOGGER.info( "Response status description" + crmUserDetailsDto.getStatusDesc() );
                            messages.add( IAppConstants.APP_MESSAGE, new ActionMessage( IPropertiesConstant.FORGET_PASSWORD_SUCCES ) );
                            forwardPage = IActionForwardConstant.LOGIN;
                        }
                        else
                        {
                            LOGGER.info( "Response status description" + crmUserDetailsDto.getStatusDesc() );
                            errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Service Unavailable ." );
                        errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                    }
                }
            }
            else
            {
                LOGGER.info( "Captcha required" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.FIELDREQUIRED ) );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error Occured in Forget Password method", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_UNABLE_PROCESS ) );
        }
        saveMessages( request, messages );
        saveErrors( request.getSession(), errors );
        return mapping.findForward( forwardPage );
    }
}
