package com.np.tele.selfcare.action;

import java.math.BigDecimal;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;

public class SelfcareATOMResponseAction
    extends Action
{
    private static final Logger LOGGER          = Logger.getLogger( SelfcareATOMResponseAction.class );
    private ISelfcareManager    selfcareManager = null;

    public ISelfcareManager getSelfcareManager()
    {
        return selfcareManager;
    }

    public void setSelfcareManager( ISelfcareManager inSelfcareManager )
    {
        selfcareManager = inSelfcareManager;
    }

    @Override
    public ActionForward execute( ActionMapping inMapping,
                                  ActionForm inForm,
                                  HttpServletRequest inRequest,
                                  HttpServletResponse inResponse )
        throws Exception
    {
        String forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAYMENT_FAILURE;
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        if ( !StringUtils.isValidObj( quickPayForm.getPaymentGatewayPojo() ) )
        {
            LOGGER.info( "quickPayForm.getPaymentGatewayPojo is not valid object" );
            quickPayForm.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
        }
        boolean flag = false;
        Enumeration<String> params = inRequest.getParameterNames();
        while ( params.hasMoreElements() )
        {
            flag = true;
            String param = params.nextElement();
            LOGGER.info( "Param:: " + param + "    Value:: " + inRequest.getParameter( param ) );
        }
        if ( flag )
        {
            quickPayForm.getPaymentGatewayPojo().setPgwTransactionId( inRequest.getParameter( "mmp_txn" ) );
            quickPayForm.getPaymentGatewayPojo().setPgwTrackId( inRequest.getParameter( "mer_txn" ) );
            if ( StringUtils.isNotBlank( inRequest.getParameter( "amt" ) ) )
            {
                quickPayForm.getPaymentGatewayPojo().setAmount( Double.valueOf( inRequest.getParameter( "amt" ) ) );
            }
            quickPayForm.getPaymentGatewayPojo().setPgwPostdate( inRequest.getParameter( "date" ) );
            quickPayForm.getPaymentGatewayPojo().setPgwReferenceNumber( inRequest.getParameter( "bank_txn" ) );
            quickPayForm.getPaymentGatewayPojo().setPgwAvr( inRequest.getParameter( "bank_name" ) );
            quickPayForm.getPaymentGatewayPojo().setPgwErrorCode( inRequest.getParameter( "f_code" ) );
            quickPayForm.getPaymentGatewayPojo().setPgwErrorMsg( inRequest.getParameter( "desc" ) );
            quickPayForm.getPaymentGatewayPojo().setUdf1( inRequest.getParameter( "udf1" ) );
            quickPayForm.getPaymentGatewayPojo().setUdf2( inRequest.getParameter( "udf2" ) );
            quickPayForm.getPaymentGatewayPojo().setUdf3( inRequest.getParameter( "udf3" ) );
            quickPayForm.getPaymentGatewayPojo().setUdf4( inRequest.getParameter( "udf4" ) );
            if ( StringUtils.equalsIgnoreCase( inRequest.getParameter( "f_code" ),
                                               CRMStatusCode.FAILURE.getStatusCode() ) )
            {
                quickPayForm.getPaymentGatewayPojo().setStatus( CRMStatusCode.FAILURE.getStatusCode() );
            }
            else
            {
                quickPayForm.getPaymentGatewayPojo().setStatus( CRMStatusCode.SUCCESS.getStatusCode() );
            }
            getSelfcareManager().savePayment( quickPayForm );
            String requestUrl = inRequest.getRequestURL().toString();
            String url = StringUtils.substring( requestUrl, 0, requestUrl.lastIndexOf( "/" ) );
            forward = url + "/quickPay.do?method=paymentResponse&trackid="
                    + quickPayForm.getPaymentGatewayPojo().getPgwTrackId();
            ActionForward actionForward = new ActionForward( forward, true );
            return actionForward;
        }
        return inMapping.findForward( forward );
    }
}
