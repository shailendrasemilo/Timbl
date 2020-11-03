package com.np.tele.paymentGateway;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.ext.pojos.HDFCPgwPojo;
import com.np.tele.crm.pojos.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.SHAEncryptUtil;
import com.np.tele.crm.utils.StringUtils;

public class HDFC
{
    private static final Logger LOGGER = Logger.getLogger( HDFC.class );

    public CRMServiceCode getRedirectURL( CrmPgwTransactionsPojo inCrmPgwTransactionPojo, HDFCPgwPojo inHdfcPgwPojo )
    {
        LOGGER.info( "in getRedirectURL()" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM901;
        /* Payment Id, Payment Page and ConnectionResponse variables are declared */
        String paymentId = null, paymentPage = null, tranResponse = null;
        //        String reqTranportalId = null, reqTranportalPassword = null, reqAction = null, reqLangId = null, reqCurrency = null, reqResponseUrl = null, reqErrorUrl = null;
        String redirectUrl = "";
        /* Now merchant sets all the inputs in one string for passing to the Payment Gateway URL */
        try
        {
            String tranRequest = "id=" + inHdfcPgwPojo.getTransportal_id() + "&" + "password="
                    + inHdfcPgwPojo.getTransportal_password() + "&" + "action=" + inHdfcPgwPojo.getReq_action() + "&"
                    + "langid=" + inHdfcPgwPojo.getLanguage_code() + "&" + "currencycode="
                    + inHdfcPgwPojo.getCurrency_code() + "&" + "amt=" + inCrmPgwTransactionPojo.getAmount() + "&"
                    + "responseURL=" + inHdfcPgwPojo.getResponse_url() + "&" + "errorURL="
                    + inHdfcPgwPojo.getError_url() + "&" + "trackid=" + inCrmPgwTransactionPojo.getPgwTrackId() + "&"
                    + "udf1=" + inCrmPgwTransactionPojo.getUdf1() + "&" + "udf2=" + inCrmPgwTransactionPojo.getUdf2()
                    + "&" + "udf3=" + inCrmPgwTransactionPojo.getUdf3() + "&" + "udf4="
                    + inCrmPgwTransactionPojo.getUdf4() + "&" + "udf5="
                    + getUdf5( inCrmPgwTransactionPojo, inHdfcPgwPojo );
            /* This is Payment Gateway Test URL where merchant sends request. This is test enviornment URL, 
            production URL will be different and will be shared by Bank during production movement */
            LOGGER.info( "request::::: " + tranRequest );
            LOGGER.info( System.getProperty( "java.protocol.handler.pkgs" ) );
            //            URL url = new URL( "https://securepgtest.fssnet.co.in/pgway/servlet/PaymentInitHTTPServlet" );
            URL url = new URL( inHdfcPgwPojo.getGateway_url() );
            /* 
            Log the complete request in the log file for future reference
            Now creating a connection and sending request
            */
            Object obj;
            obj = (HttpsURLConnection) url.openConnection(); //create a SSL connection object server-to-server
            ( (URLConnection) obj ).setDoInput( true );
            ( (URLConnection) obj ).setDoOutput( true );
            ( (URLConnection) obj ).setUseCaches( false );
            ( (URLConnection) obj ).setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
            if ( tranRequest.length() > 0 )
            {
                // Here the HTTPS request URL is created
                DataOutputStream dataoutputstream = new DataOutputStream( ( (URLConnection) obj ).getOutputStream() );
                dataoutputstream.writeBytes( tranRequest ); // here the request is sent to payment gateway
                dataoutputstream.flush();
                dataoutputstream.close(); //connection closed
                BufferedReader bufferedreader = new BufferedReader( new InputStreamReader( ( (URLConnection) obj ).getInputStream() ) );
                tranResponse = bufferedreader.readLine(); //Payment Gateway response is read
                String ErrorCheck;
                try
                {
                    ErrorCheck = getTextBetweenTags( tranResponse, "!", "!-" );//This line will find Error Keyword in TranResponse   
                }
                catch ( Exception e )
                {
                    ErrorCheck = "";
                }
                if ( !ErrorCheck.equals( "ERROR" ) )//This block will check for Error in TranResponce
                {
                    // If Payment Gateway response has Payment ID & Pay page URL        
                    int index = tranResponse.indexOf( ":" );
                    int size = tranResponse.length();
                    // Merchant MUST map (update) the Payment ID received with the merchant Track Id in his database at this place.
                    paymentId = tranResponse.substring( 0, index );
                    paymentPage = tranResponse.substring( index + 1, size );
                    // here redirecting the customer browser from ME site to Payment Gateway Page with the Payment ID
                    redirectUrl = paymentPage + "?PaymentID=" + paymentId;
                    //out.println(hashString);
                    inCrmPgwTransactionPojo.setPgwPaymentId( paymentId );
                    inCrmPgwTransactionPojo.setPgwPaymentPage( paymentPage );
                    inCrmPgwTransactionPojo.setRedirectUrl( redirectUrl );
                    serviceCode = CRMServiceCode.CRM001;
                    LOGGER.info( "payment id:::: " + paymentId + " page:::: " + paymentPage );
                }
                else
                {
                    // here redirecting the error page 
                    redirectUrl = inHdfcPgwPojo.getError_url() + "?Error=" + serviceCode.getStatusCode()
                            + "&ErrorText=" + tranResponse + "&trackid=" + inCrmPgwTransactionPojo.getPgwTrackId()
                            + "&amt=" + inCrmPgwTransactionPojo.getAmount();
                    inCrmPgwTransactionPojo.setPgwErrorCode( serviceCode.getStatusCode() );
                    inCrmPgwTransactionPojo.setPgwErrorMsg( tranResponse );
                    inCrmPgwTransactionPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
                    inCrmPgwTransactionPojo.setRedirectUrl( redirectUrl );
                    LOGGER.info( "pgw error :::: " + tranResponse );
                }
            }
        }
        catch ( Exception localException )
        {
            // here redirecting the error page 
            String error = localException.getMessage() + StringUtils.trimToEmpty( tranResponse );
            inCrmPgwTransactionPojo.setPgwErrorCode( serviceCode.getStatusCode() );
            inCrmPgwTransactionPojo.setPgwErrorMsg( error );
            inCrmPgwTransactionPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
            redirectUrl = inHdfcPgwPojo.getError_url() + "?Error=" + serviceCode.getStatusCode() + "&ErrorText="
                    + error + "&trackid=" + inCrmPgwTransactionPojo.getPgwTrackId() + "&amt="
                    + inCrmPgwTransactionPojo.getAmount();
            inCrmPgwTransactionPojo.setRedirectUrl( redirectUrl );
            LOGGER.info( "redirect url:: " + redirectUrl );
            LOGGER.error( "exception :::: ", localException );
        }
        return serviceCode;
    }

    public CRMServiceCode validateHash( CrmPgwTransactionsPojo inCrmPgwTransactionPojo, HDFCPgwPojo inHdfcPgwPojo )
    {
        LOGGER.info( "in validateHash" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM902;
        String strHashTraportalID = inHdfcPgwPojo.getTransportal_id();
        StringBuilder strhashstring = new StringBuilder();//Declaration of Hashing String 
        strhashstring.append( StringUtils.trimToEmpty( strHashTraportalID ) ) //Padding Tranportal ID Value
                .append( StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwTrackId() ) ) //Padding TrackID Value,Mercahnt need to take this filed value  from his Secure channel such as DATABASE.
                .append( inCrmPgwTransactionPojo.getAmount() ) //Padding Amount Value,Mercahnt need to take this field value  from his Secure channel such as DATABASE,Value should be in same format which he pass in initial request for eg. if he passed amount as 1.00 then for hashing also mercahnt need to use 1.00
                .append( StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwResult() ) ) //Padding Result Value
                .append( StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwPaymentId() ) ) //Padding PaymentId Value
                .append( StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwReferenceNumber() ) ) //Padding Ref Value
                .append( StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwAuthCode() ) ) //Padding Auth Value
                .append( StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwTransactionId() ) ); //Padding TranId Value
        String hash = SHAEncryptUtil.getSHA256( strhashstring.toString() );
        if ( StringUtils.equals( inCrmPgwTransactionPojo.getUdf5(), hash ) )
        {
            LOGGER.info( "hash valid success" );
            if ( StringUtils.equalsIgnoreCase( inCrmPgwTransactionPojo.getPgwResult(), "CAPTURED" ) )
            {
                LOGGER.info( "Result :: " + inCrmPgwTransactionPojo.getPgwResult() + " Status:: Success" );
                inCrmPgwTransactionPojo.setStatus( CRMStatusCode.SUCCESS.getStatusCode() );
            }
            else
            {
                LOGGER.info( "Result :: " + inCrmPgwTransactionPojo.getPgwResult() + " Status:: Failure" );
                if ( StringUtils.isNotBlank( inCrmPgwTransactionPojo.getPgwResult() ) )
                {
                    inCrmPgwTransactionPojo.setPgwErrorMsg( "PAYMENT " + inCrmPgwTransactionPojo.getPgwResult() );
                }
                inCrmPgwTransactionPojo.setStatus( CRMStatusCode.FAILURE.getStatusCode() );
            }
            serviceCode = CRMServiceCode.CRM001;
        }
        return serviceCode;
    }

    private String getTextBetweenTags( String InputText, String Tag1, String Tag2 )
    {
        String Result;
        int index1 = InputText.indexOf( Tag1 );
        int index2 = InputText.indexOf( Tag2 );
        index1 = index1 + Tag1.length();
        Result = InputText.substring( index1, index2 );
        return Result;
    }

    private String getUdf5( CrmPgwTransactionsPojo inCrmPgwTransactionPojo, HDFCPgwPojo inHdfcPgwPojo )
    {
        String strhashTID = inHdfcPgwPojo.getTransportal_id();//USE Tranportal ID FIELD Value FOR HASHING 
        String strhashtrackid = StringUtils.trimToEmpty( inCrmPgwTransactionPojo.getPgwTrackId() );//USE Trackid FIELD Value FOR HASHING 
        String strhashamt = inCrmPgwTransactionPojo.getAmount()+"";//USE Amount FIELD Value FOR HASHING 
        String strhashcurrency = inHdfcPgwPojo.getCurrency_code();//USE Currencycode FIELD Value FOR HASHING 
        String strhashaction = inHdfcPgwPojo.getReq_action();//USE Action code FIELD Value FOR HASHING 
        //Create a Hashing String to Hash
        String strhashs = strhashTID + strhashtrackid + strhashamt + strhashcurrency + strhashaction;
        strhashs = SHAEncryptUtil.getSHA256( strhashs );
        return strhashs;
    }
}
