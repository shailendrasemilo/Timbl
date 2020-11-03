package com.np.tele.selfcare.action;

import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IActionForwardConstant;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.tele.selfcare.bm.ISelfcareManager;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;

public class SelfcareHDFCResponseAction
    extends Action
{
    private final Logger     LOGGER          = Logger.getLogger( SelfcareHDFCResponseAction.class );
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
        String strResponseIPAdd = inRequest.getRemoteAddr();
        String requestUrl = inRequest.getRequestURL().toString();
        String url = StringUtils.substring( requestUrl, 0, requestUrl.lastIndexOf( "/" ) );
        SelfcareQuickPayForm quickPayForm = (SelfcareQuickPayForm) inForm;
        if ( !StringUtils.isValidObj( quickPayForm.getPaymentGatewayPojo() ) )
        {
            LOGGER.info( "quickPayForm.getPaymentGatewayPojo is not valid object" );
            quickPayForm.setPaymentGatewayPojo( new CrmPgwTransactionsPojo() );
        }
        if ( !strResponseIPAdd.equals( "221.134.101.174" ) && !strResponseIPAdd.equals( "221.134.101.169" )
                && !strResponseIPAdd.equals( "198.64.129.10" ) && !strResponseIPAdd.equals( "198.64.133.213" ) )
        {
            //            out.println( "REDIRECT=http://www.merchantdemo.com/StatusTRAN.jsp?ResError=--IP MISSMATCH-- Response IP Address is: "
            //                    + strResponseIPAdd );
            LOGGER.info( "ip address ::" + strResponseIPAdd );
            inResponse.sendRedirect( "quickPay.do?method=quickPayPage" );
            // return new ActionForward( IActionForwardConstant.SELFCARE_QUICK_PAY_PAGE, true );
        }
        PrintWriter out = inResponse.getWriter();
        CrmPgwTransactionsPojo pgwTransactionsPojo = null;
        LOGGER.info( "HDFC Response :::: Error Code:: " + inRequest.getParameter( "Error" ) + " trackid:: "
                + inRequest.getParameter( "trackid" ) + " amt:: " + inRequest.getParameter( "amt" ) + " tranid:: "
                + inRequest.getParameter( "tranid" ) + " paymentid:: " + inRequest.getParameter( "paymentid" )
                + " ref no.:: " + inRequest.getParameter( "ref" ) + " post date:: "
                + inRequest.getParameter( "postdate" ) + " auth code:: " + inRequest.getParameter( "auth" )
                + " result:: " + inRequest.getParameter( "result" ) );
        boolean flag = false;
        Enumeration<String> params = inRequest.getParameterNames();
        while ( params.hasMoreElements() )
        {
            flag = true;
            LOGGER.info( "Param:: " + params.nextElement() );
        }
        if ( flag )
        {
            LOGGER.info( "setting request params in pgwPojo" );
            quickPayForm.getPaymentGatewayPojo().setPgwErrorMsg( inRequest.getParameter( "ErrorText" ) ); //Error Text/message
            quickPayForm.getPaymentGatewayPojo().setPgwPaymentId( inRequest.getParameter( "paymentid" ) ); //Payment Id
            quickPayForm.getPaymentGatewayPojo().setPgwTrackId( inRequest.getParameter( "trackid" ) ); //Merchant Track ID
            quickPayForm.getPaymentGatewayPojo().setPgwErrorCode( inRequest.getParameter( "Error" ) ); //Error Number                  
            quickPayForm.getPaymentGatewayPojo().setPgwResult( inRequest.getParameter( "result" ) ); //Transaction Result
            quickPayForm.getPaymentGatewayPojo().setPgwPostdate( inRequest.getParameter( "postdate" ) ); //Postdate                  
            quickPayForm.getPaymentGatewayPojo().setPgwTransactionId( inRequest.getParameter( "tranid" ) ); //Transaction ID
            quickPayForm.getPaymentGatewayPojo().setPgwAuthCode( inRequest.getParameter( "auth" ) ); //Auth Code     
            quickPayForm.getPaymentGatewayPojo().setPgwAvr( inRequest.getParameter( "avr" ) ); //TRANSACTION avr
            quickPayForm.getPaymentGatewayPojo().setPgwReferenceNumber( inRequest.getParameter( "ref" ) ); //Reference Number also called Seq Number 
            if ( !StringUtils.isEmpty( inRequest.getParameter( "amt" ) ) )
            {
                quickPayForm.getPaymentGatewayPojo().setAmount( Double.valueOf( inRequest.getParameter( "amt" )
                                                                        .replaceAll( ",", "" ) ) ); //Transaction Amount
            }
            quickPayForm.getPaymentGatewayPojo().setUdf1( inRequest.getParameter( "udf1" ) ); //UDF1
            quickPayForm.getPaymentGatewayPojo().setUdf2( inRequest.getParameter( "udf2" ) ); //UDF2
            quickPayForm.getPaymentGatewayPojo().setUdf3( inRequest.getParameter( "udf3" ) ); //UDF3
            quickPayForm.getPaymentGatewayPojo().setUdf4( inRequest.getParameter( "udf4" ) ); //UDF4
            quickPayForm.getPaymentGatewayPojo().setUdf5( inRequest.getParameter( "udf5" ) ); //UDF5
            if ( StringUtils.isBlank( quickPayForm.getPaymentGatewayPojo().getPgwErrorCode() )
                    && StringUtils.isBlank( quickPayForm.getPaymentGatewayPojo().getPgwErrorMsg() ) )
            {
                CrmFinanceDto crmFinanceDto = getSelfcareManager().validateResponse( quickPayForm );
                if ( StringUtils.equals( crmFinanceDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    pgwTransactionsPojo = crmFinanceDto.getCrmPgwTransactionsPojo();
                    LOGGER.info( "success:::: saving payment transaction :: " + pgwTransactionsPojo.getStatus() );
                    quickPayForm.setPaymentGatewayPojo( pgwTransactionsPojo );
                    pgwTransactionsPojo = getSelfcareManager().savePayment( quickPayForm ).getCrmPgwTransactionsPojo();
                    //                    out.println( "REDIRECT=" + url + "/quickPay.do?method=paymentResponse&receipt="
                    //                            + StringUtils.trimToEmpty( pgwTransactionsPojo.getPgwTransactionId() ) + "&postdate="
                    //                            + pgwTransactionsPojo.getPgwResponseDatetime() + "&amount="
                    //                            + pgwTransactionsPojo.getAmount() + "&status=" + pgwTransactionsPojo.getStatus() );
                    out.println( "REDIRECT=" + url + "/quickPay.do?method=paymentResponse&trackid="
                            + pgwTransactionsPojo.getPgwTrackId() );
                    return null;
                }
                else
                {
                    quickPayForm.getPaymentGatewayPojo().setPgwErrorMsg( "Response Hash mismatch." );
                    pgwTransactionsPojo = getSelfcareManager().savePayment( quickPayForm ).getCrmPgwTransactionsPojo();
                    //                    out.println( "REDIRECT=" + url + "/quickPay.do?method=paymentResponse&receipt="
                    //                            + StringUtils.trimToEmpty( pgwTransactionsPojo.getPgwTransactionId() ) + "&postdate="
                    //                            + pgwTransactionsPojo.getPgwResponseDatetime() + "&amount="
                    //                            + pgwTransactionsPojo.getAmount() + "&status=" + pgwTransactionsPojo.getStatus()
                    //                            + "&error=" + pgwTransactionsPojo.getPgwErrorMsg() );
                    out.println( "REDIRECT=" + url + "/quickPay.do?method=paymentResponse&trackid="
                            + pgwTransactionsPojo.getPgwTrackId() );
                }
                LOGGER.info( "HDFC response status code ::" + crmFinanceDto.getStatusCode() + ":-"
                        + crmFinanceDto.getStatusDesc() );
            }
            else
            {
                quickPayForm.getPaymentGatewayPojo().setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                pgwTransactionsPojo = getSelfcareManager().savePayment( quickPayForm ).getCrmPgwTransactionsPojo();
                //                forward = url + "/quickPay.do?method=paymentResponse&receipt="
                //                        + StringUtils.trimToEmpty( pgwTransactionsPojo.getPgwTransactionId() ) + "&postdate="
                //                        + pgwTransactionsPojo.getPgwResponseDatetime() + "&amount=" + pgwTransactionsPojo.getAmount()
                //                        + "&status=" + pgwTransactionsPojo.getStatus() + "&error="
                //                        + pgwTransactionsPojo.getPgwErrorMsg();
                forward = url + "/quickPay.do?method=paymentResponse&trackid=" + pgwTransactionsPojo.getPgwTrackId();
                if ( StringUtils.equals( CRMServiceCode.CRM901.getStatusCode(), quickPayForm.getPaymentGatewayPojo()
                        .getPgwErrorCode() ) )
                {
                    ActionForward actionForward = new ActionForward( forward, true );
                    return actionForward;
                }
                else
                {
                    out.println( "REDIRECT=" + forward );
                    return null;
                }
            }
        }
        return inMapping.findForward( forward );
    }
}
