package com.np.tele.crm.alerts.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

import com.np.tele.crm.ext.pojos.SmsGatewayPojo;
import com.np.tele.crm.utils.StringUtils;

public class SendSmsUtils
{
    private static final Logger LOGGER             = Logger.getLogger( SendSmsUtils.class );
    SmsGatewayPojo              smsGatewayPojo     = null;
    int                         connectionTimeOut;
    int                         socketTimeOut;
    private String              embedServerDetails = null;
    private String              gateway            = null;

    public SendSmsUtils( final SmsGatewayPojo inSmsGatewayPojo )
    {
        try
        {
            final String userName = inSmsGatewayPojo.getUserid();
            final String password = inSmsGatewayPojo.getPassword();
            final String url = inSmsGatewayPojo.getUrl();
            final String from = inSmsGatewayPojo.getFrom();
            gateway = inSmsGatewayPojo.getGatewayProvider();
            connectionTimeOut = inSmsGatewayPojo.getConnection_time_out();
            socketTimeOut = inSmsGatewayPojo.getSocket_time_out();
            if ( StringUtils.equalsIgnoreCase( "mgage", gateway ) )
            {
                embedServerDetails = url + "?aid=" + userName + "&pin=" + password;
            }
            else
            {
                embedServerDetails = url + "?username=" + userName + "&password=" + password + "&from="
                        + inSmsGatewayPojo.getFrom();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to set Sms Gateway details", ex );
        }
        smsGatewayPojo = inSmsGatewayPojo;
    }

    public boolean sendSms( final String inMobileNumber, final String inMessage )
    {
        boolean isSuccess = false;
        HttpURLConnection httpURLConnection = null;
        if ( !StringUtils.isEmpty( inMobileNumber ) && StringUtils.isNumeric( inMobileNumber )
                && !StringUtils.isEmpty( inMessage ) )
        {
            try
            {
                StringBuilder requestURL = new StringBuilder( embedServerDetails );
                LOGGER.info( "Trying to send message to " + inMobileNumber );
                final String message = URLEncoder.encode( inMessage, "UTF-8" );
                if ( StringUtils.equalsIgnoreCase( "mgage", gateway ) )
                {
                    requestURL.append( "&mnumber=" + inMobileNumber + "&message=" + message );
                }
                else
                {
                    requestURL.append( "&to=" + inMobileNumber + "&text=" + message );
                }
                
                LOGGER.info( "URL for sending SMS " + requestURL.toString() );
                final URL url = new URL( requestURL.toString() );
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setConnectTimeout( connectionTimeOut );
                httpURLConnection.setReadTimeout( socketTimeOut );
                httpURLConnection.connect();
                final int statusCode = httpURLConnection.getResponseCode();
                String line = null;
                final StringBuffer page = new StringBuffer();
                final BufferedReader in = new BufferedReader( new InputStreamReader( httpURLConnection.getInputStream() ) );
                if ( in != null )
                {
                    while ( ( line = in.readLine() ) != null )
                    {
                        page.append( line + "\n" );
                    }
                }
                final String ret = page.toString();
                if ( ( statusCode != 200 ) || ( !ret.startsWith( smsGatewayPojo.getResponse() ) ) )
                {
                    isSuccess = false;
                    LOGGER.warn( " Profile Exception sending SMS to mobile # : " + inMobileNumber + " Status Code : "
                            + statusCode + "\n" + ret );
                    LOGGER.info( " Profile SMS sending failed for mobile num : " + inMobileNumber );
                    throw new Exception( ret );
                }
                else
                {
                    LOGGER.info( "SMS sent succssfully to mobile : " + inMobileNumber );
                    isSuccess = true;
                }
            }
            catch ( IOException ioe )
            {
                LOGGER.error( "IOException sending SMS to Mobile # : " + inMobileNumber + " Exception is : " + ioe );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception sending SMS to Mobile # : " + inMobileNumber + " Exception is : " + ex );
            }
            finally
            {
                httpURLConnection.disconnect();
            }
        }
        return isSuccess;
    }
}
