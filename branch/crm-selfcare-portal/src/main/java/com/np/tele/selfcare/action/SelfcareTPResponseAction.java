package com.np.tele.selfcare.action;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;

public class SelfcareTPResponseAction
    extends Action
{
    private final Logger     LOGGER          = Logger.getLogger( SelfcareTPResponseAction.class );
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
    public ActionForward execute( ActionMapping inMapping,
                                  ActionForm inForm,
                                  HttpServletRequest inRequest,
                                  HttpServletResponse inResponse )
        throws Exception
    {
        String forward = IActionForwardConstant.SELFCARE_QUICK_PAY_PAYMENT_FAILURE;
        LOGGER.info( "...........SelfcareTPResponseAction" );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        String PayOption = (String) inRequest.getSession( false ).getAttribute( "PayOption" );
        CrmPgwTransactionsPojo pgwTransactionsPojo = null;
        boolean flag = false;
        String param = null;
        Enumeration<String> params = inRequest.getParameterNames();
        while ( params.hasMoreElements() )
        {
            flag = true;
            param = params.nextElement();
            LOGGER.info( "Param:: " + param + " = " + inRequest.getParameter( param ) );
        }
        if ( !StringUtils.isValidObj( quickPayForm.getPaymentGatewayPojo() ) )
        {
            LOGGER.info( "quickPayForm.getPaymentGatewayPojo is not valid object" );
            quickPayForm.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
        }
        if ( flag )
        {
            quickPayForm.getPaymentGatewayPojo().setUdf5( inRequest.getParameter( "msg" ) );
            quickPayForm.getPaymentGatewayPojo().setPaymentOption( PayOption );
            LOGGER.info( "validating paymentResponse" );
            CrmFinanceDto crmFinanceDto = getSelfcareManager().validateResponse( quickPayForm );
            if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
            {
                pgwTransactionsPojo = crmFinanceDto.getCrmPgwTransactionsPojo();
                LOGGER.info( "success:::: saving payment transaction :: " + pgwTransactionsPojo.getStatus() );
                quickPayForm.setPaymentGatewayPojo( pgwTransactionsPojo );
                pgwTransactionsPojo = getSelfcareManager().savePayment( quickPayForm ).getCrmPgwTransactionsPojo();
            }
            else
            {
                LOGGER.info( "validation error" );
                if ( StringUtils.isValidObj( crmFinanceDto.getCrmPgwTransactionsPojo() ) )
                {
                    quickPayForm.setPaymentGatewayPojo( crmFinanceDto.getCrmPgwTransactionsPojo() );
                }
                pgwTransactionsPojo = getSelfcareManager().savePayment( quickPayForm ).getCrmPgwTransactionsPojo();
            }
            forward = quickPayForm.getPaymentGatewayPojo().getAppReturnUrl() + "trackid="
                    + quickPayForm.getPaymentGatewayPojo().getPgwTrackId();
            LOGGER.info( "APP Return URL::" + forward );
            /*forward = url + "/quickPay.do?method=paymentResponse&trackid="
                    + quickPayForm.getPaymentGatewayPojo().getPgwTrackId();*/
            ActionForward actionForward = new ActionForward( forward, true );
            return actionForward;
        }
        return inMapping.findForward( forward );
    }
}
