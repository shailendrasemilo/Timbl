package com.np.tele.selfcare.action;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;

public class SelfcareVerificationAction
    extends Action
{
    private static final Logger LOGGER          = Logger.getLogger( SelfcareVerificationAction.class );
    private ISelfcareManager    selfcareManager = null;

    public ISelfcareManager getSelfcareManager()
    {
        return selfcareManager;
    }

    public void setSelfcareManager( ISelfcareManager inSelfcareManager )
    {
        selfcareManager = inSelfcareManager;
    }

    public ActionForward execute( ActionMapping inMapping,
                                  ActionForm inForm,
                                  HttpServletRequest inRequest,
                                  HttpServletResponse inResponse )
    {
        LOGGER.info( " verification action email verification::::::" );
        String forward = IActionForwardConstant.SELFCARE_VERIFICATION_PAGE;
        String sessionId = inRequest.getParameter( "sessionId" );
        ActionMessages messages = getMessages( inRequest );
        ActionMessages errors = getErrors( inRequest );
        if ( StringUtils.isNotBlank( sessionId ) )
        {
            CrmCapDto capDto = new CrmCapDto();
            capDto.setCustomerDetailsPojo( new CrmCustomerDetailsPojo() );
            try
            {
                capDto.getCustomerDetailsPojo().setCustToken( URLEncoder.encode( sessionId, "UTF-8" ) );
            }
            catch ( UnsupportedEncodingException ex )
            {
                LOGGER.error( "unable to encode token", ex );
            }
            capDto = getSelfcareManager().validateEmailToken( capDto );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), capDto.getStatusCode() ) )
            {
                messages.add( IAppConstants.APP_MESSAGE,
                              new ActionMessage( IPropertiesConstant.SUCCESS_SELFCARE_EMAIL_VERIFY ) );
            }
            else
            {
                errors.add( IAppConstants.APP_ERROR,
                            new ActionMessage( IPropertiesConstant.ERROR_SELFCARE_EMAIL_VERIFY ) );
            }
            LOGGER.info( "status code in verification action verify email :::" + capDto.getStatusCode() );
        }
        saveMessages( inRequest, messages );
        saveErrors( inRequest, errors );
        return inMapping.findForward( forward );
    }
}
