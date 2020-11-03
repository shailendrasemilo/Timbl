package com.np.tele.crm.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class EncryptionUtil
{
    private static final byte[]    _3desData  =
                                              { 122, 53, 125, 9, 51, 125, 76, 110, 31, 29, 22, 97, 59, 20, 16, 31, 109,
            94, 61, 42, 36, 11, 88, 21       };
    //    private static final String    IV         = "airtelds";
    private static final byte[]    myIVString = "airtelds".getBytes();
    private static SecretKeySpec   _key       = new SecretKeySpec( _3desData, "DESede" );
    private static IvParameterSpec iv         = new IvParameterSpec( myIVString );

    public static String encrypt( String text )
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        byte[] plaintext = text.getBytes();
        Cipher cipher = Cipher.getInstance( "TRIPLEDES/CBC/PKCS5Padding" );
        cipher.init( 1, _key, iv );
        byte[] cipherText = cipher.doFinal( plaintext );
        Base64 b64 = new Base64();
        return new String( b64.encode( cipherText ) );
    }

    public static String decrypt( String text )
        throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
        InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException
    {
        Base64 b64 = new Base64();
        byte[] cipherText = b64.decode( text );
        Cipher cipher = Cipher.getInstance( "TRIPLEDES/CBC/PKCS5Padding" );
        cipher.init( 2, _key, iv );
        String plainText = new String( cipher.doFinal( cipherText ) );
        return plainText;
    }

    public static String base64encode( String text, boolean urlEncode )
    {
        if ( urlEncode )
        {
            try
            {
                return URLEncoder.encode( Base64.encodeBase64String( text.getBytes() ), "UTF-8" );
            }
            catch ( UnsupportedEncodingException ex )
            {
                return text;
            }
        }
        return Base64.encodeBase64String( text.getBytes() );
    }

    public static void main( String args[] )
        throws Exception
    {
        System.out.println( decrypt( "nqQLgK5u5E3IJnErzbFDPQ==" ) );
        
        boolean x=false;
        if(x==x){
            System.out.println("yoyo");
        }
        else{
            System.out.println("not");
            
        }
             
        
       // System.out.println( encrypt( "Neer$4321" ) );
        //  System.out.println("code:"+CRMDisplayListConstants.PREINSTALLATION.getCode());
    }

    public static String getFromDate( String inDate )
    {
        String oldDate = null;
        try
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy" );
            Date date = dateFormat.parse( inDate );
            dateFormat.format( date );
            Calendar calendar = Calendar.getInstance();
            calendar.setTime( date );
            calendar.add( Calendar.DAY_OF_YEAR, -180 );
            Date previousDate = calendar.getTime();
            oldDate = dateFormat.format( previousDate );
        }
        catch ( Exception ex )
        {
            ex.printStackTrace();
        }
        return oldDate;
    }
}
