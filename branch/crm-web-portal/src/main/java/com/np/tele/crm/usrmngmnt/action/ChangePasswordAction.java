package com.np.tele.crm.usrmngmnt.action;

import java.util.List;

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
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.usrmngmnt.bm.IUserBMngr;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.Captcha;

public class ChangePasswordAction
    extends DispatchAction
{
    private static final Logger LOGGER = Logger.getLogger( ChangePasswordAction.class );
    private IUserBMngr          usermngmntbm;

    public IUserBMngr getUsermngmntbm()
    {
        return usermngmntbm;
    }

    public void setUsermngmntbm( IUserBMngr usermngmntbm )
    {
        this.usermngmntbm = usermngmntbm;
    }

    public ActionForward changePasswordPage( ActionMapping mapping,
                                             ActionForm form,
                                             HttpServletRequest request,
                                             HttpServletResponse response )
        throws Exception
    {
        return mapping.findForward( IActionForwardConstant.CHANGE_PASSWORD );
    }

    public ActionForward changePassword( ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response )
        throws Exception
    {
        String forwardPage = IActionForwardConstant.CHANGE_PASSWORD;
        LoginForm loginForm = (LoginForm) form;
        ActionMessages messages = new ActionMessages();
        ActionMessages errors = new ActionMessages();
        HttpSession httpSession = request.getSession( false );
        try
        {
            String answer = request.getParameter( "answer" );
            String capcha = (String) httpSession.getAttribute( Captcha.CAPTCHA_KEY );
            LOGGER.info( "The answer text is : " + answer );
            LOGGER.info( "The capcha text is : " + capcha );
            if ( null != answer && null != capcha )
            {
                if ( !capcha.equals( answer ) )
                {
                    messages.add( "captchaReenter", new ActionMessage( IPropertiesConstant.ERROR_WRONG_CAPTCHA ) );
                }
                else
                {
                    CrmuserDetailsDto userDto = (CrmuserDetailsDto) request.getSession( false )
                            .getAttribute( IAppConstants.CRM_USER_OBJECT );
                    if ( null != userDto )
                    {
                        CrmuserDetailsDto crmUserDetailsDto = getUsermngmntbm()
                                .changePassword( userDto.getCrmUserDetailsPojo().getUserId(),
                                                 loginForm.getOldPassword(), loginForm.getNewPassword() );
                        if ( null != crmUserDetailsDto )
                        {
                            String statusCode = crmUserDetailsDto.getStatusCode();
                            LOGGER.info( "Response Status code " + statusCode );
                            if ( CRMServiceCode.CRM001.getStatusCode().equals( statusCode ) )
                            {
                                messages.add( IAppConstants.APP_MESSAGE,
                                              new ActionMessage( IPropertiesConstant.ERROR_CHANGE_PASSWORD ) );
                                forwardPage = IActionForwardConstant.LOGIN;
                            }
                            else
                            {
                                errors.add( IAppConstants.APP_ERROR, new ActionMessage( statusCode ) );
                            }
                        }
                        else
                        {
                            LOGGER.info( "found CrmuserDetailsDto null " );
                            errors.add( IAppConstants.APP_ERROR,
                                        new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
                        }
                    }
                    else
                    {
                        LOGGER.info( "found CrmuserDetailsDto null. Forward to Login Page." );
                        errors.add( IAppConstants.APP_ERROR,
                                    new ActionMessage( IPropertiesConstant.ERROR_SESSION_EXPIRED ) );
                        forwardPage = IActionForwardConstant.LOGIN;
                    }
                }
            }
            else
            {
                LOGGER.info( "captcha name or value null" );
                errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.FIELDREQUIRED ) );
            }
            if ( loginForm.getChangePasswordFlag() > 0 )
            {
                CrmUserPojo crmUserPojo = (CrmUserPojo) httpSession.getAttribute( IAppConstants.CRM_USER_POJO );
                if ( null != crmUserPojo )
                {
                    loginForm.setCrmUserPojo( crmUserPojo );
                    String userID = loginForm.getCrmUserPojo().getUserId();
                    LOGGER.info( "user id:::is:::" + userID );
                    List<UserRolesPojo> userRolePojos = getUsermngmntbm().getUserAssignAccess( userID )
                            .getUserRolesPojos();
                    if ( !userRolePojos.isEmpty() )
                    {
                        loginForm.setUserRolesPojos( userRolePojos );
                    }
                    else
                    {
                        LOGGER.info( "------------====userRolePojos is empty" );
                    }
                }
                forwardPage = IActionForwardConstant.MY_ACCOUNT;
            }
            else if ( errors.isEmpty() )
            {
                request.getSession( false ).invalidate();
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured", e );
            errors.add( IAppConstants.APP_ERROR, new ActionMessage( IPropertiesConstant.ERROR_SERVICE_FAIL ) );
        }
        saveMessages( request, messages );
        saveErrors( request, errors );
        return mapping.findForward( forwardPage );
    }
}
