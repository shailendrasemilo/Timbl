package com.np.tele.paymentGateway;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.ext.pojos.TechProcessPgwPojo;
import com.np.tele.crm.pojos.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;
import com.tp.pg.util.TransactionRequestBean;
import com.tp.pg.util.TransactionResponseBean;

public class TPSL
{
    private static final Logger LOGGER = Logger.getLogger( TPSL.class );

    public CRMServiceCode getRedirectURL( CrmPgwTransactionsPojo inCrmPgwTransactionPojo, TechProcessPgwPojo inTpPgwPojo )
    {
        LOGGER.info( "in getRedirectURL()" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM901;
        TransactionRequestBean requestBean = new TransactionRequestBean();
        requestBean.setStrRequestType( inTpPgwPojo.getRequestType() );
        requestBean.setStrMerchantCode( inTpPgwPojo.getMerchantCode() );
        requestBean.setMerchantTxnRefNumber( inCrmPgwTransactionPojo.getPgwTrackId() );
        requestBean.setStrAmount( inCrmPgwTransactionPojo.getAmount() + "" );
        requestBean.setStrCurrencyCode( inTpPgwPojo.getCurrencyCode() );
        requestBean.setStrReturnURL( inTpPgwPojo.getReturnURL() );
        requestBean.setTxnDate( DateUtils.getCurrentDateInFormat( IDateConstants.SDF_DD_MM_YYYY ) );
        requestBean.setWebServiceLocator( inTpPgwPojo.getServiceLocator() );
        requestBean.setStrUniqueCustomerId( inCrmPgwTransactionPojo.getUdf1() );
        requestBean.setStrEmail( inCrmPgwTransactionPojo.getUdf2() );
        requestBean.setStrMobileNumber( inCrmPgwTransactionPojo.getUdf3() );
        requestBean.setKey( inTpPgwPojo.getKey().getBytes() );
        requestBean.setIv( inTpPgwPojo.getIv().getBytes() );
        requestBean.setLogPath( "../standalone/log/techprocess.log" );
        requestBean.setStrShoppingCartDetails( inTpPgwPojo.getSchemeCode() + "_" + inCrmPgwTransactionPojo.getAmount()
                + "_0.00" );
        String token = requestBean.getTransactionToken();
        LOGGER.info( "TPSL Transaction Token:: " + token );
        if ( !StringUtils.startsWith( token, "ERROR" ) )
        {
            inCrmPgwTransactionPojo.setPgwPaymentPage( token );
            inCrmPgwTransactionPojo.setRedirectUrl( token );
            inCrmPgwTransactionPojo.setAppReturnUrl( inTpPgwPojo.getAppReturnUrl() );
            serviceCode = CRMServiceCode.CRM001;
        }
        else
        {
            inCrmPgwTransactionPojo.setPgwErrorCode( token );
            inCrmPgwTransactionPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
        }
        return serviceCode;
    }

    public CRMServiceCode validateEncryptedResponse( CrmPgwTransactionsPojo inCrmPgwTransactionsPojo,
                                                     TechProcessPgwPojo inTpPgwPojo )
    {
        LOGGER.info( "in validateEncryptedResponse" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM901;
        TransactionResponseBean responseBean = new TransactionResponseBean();
        responseBean.setLogPath("../standalone/log/techprocess.log");
        responseBean.setIv( inTpPgwPojo.getIv().getBytes() );
        responseBean.setKey( inTpPgwPojo.getKey().getBytes() );
        responseBean.setResponsePayload( inCrmPgwTransactionsPojo.getUdf5() );
        String response = responseBean.getResponsePayload();
        LOGGER.info( "Response payload:::: " + response );
        String[] tokens = StringUtils.split( response, "|" );
        Map<String, String> params = new HashMap<String, String>();
        for ( int i = 0; i < tokens.length; i++ )
        {
            params.put( tokens[i].split( "=" )[0], tokens[i].split( "=" )[1] );
        }
        LOGGER.info( "response params:: " + params );
        inCrmPgwTransactionsPojo.setPgwAuthCode( params.get( "txn_status" ) );
        inCrmPgwTransactionsPojo.setPgwResult( params.get( "txn_msg" ) );
        inCrmPgwTransactionsPojo
                .setPgwErrorMsg( StringUtils.equalsIgnoreCase( params.get( "txn_err_msg" ), IAppConstants.NOTAPPLICABLE )
                                                                                                                         ? ""
                                                                                                                         : params.get( "txn_err_msg" ) );
        inCrmPgwTransactionsPojo.setPgwTrackId( params.get( "clnt_txn_ref" ) );
        inCrmPgwTransactionsPojo.setPgwTransactionId( params.get( "tpsl_txn_id" ) );
        inCrmPgwTransactionsPojo.setAmount( new Double( params.get( "txn_amt" ) ) );
        inCrmPgwTransactionsPojo.setPgwPostdate( params.get( "tpsl_txn_time" ) );
        inCrmPgwTransactionsPojo.setAppReturnUrl( inTpPgwPojo.getAppReturnUrl() );
        if ( StringUtils.equalsIgnoreCase( inCrmPgwTransactionsPojo.getPgwResult(),
                                           CRMStatusCode.SUCCESS.getStatusDesc() ) )
        {
            LOGGER.info( "Result:: " + inCrmPgwTransactionsPojo.getPgwResult() + " Status:: Success" );
            inCrmPgwTransactionsPojo.setStatus( CRMStatusCode.SUCCESS.getStatusCode() );
            serviceCode = CRMServiceCode.CRM001;
        }
        else
        {
            LOGGER.info( "Result:: " + inCrmPgwTransactionsPojo.getPgwResult() + " Status:: Failure" );
            inCrmPgwTransactionsPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
        }
        return serviceCode;
    }
}
