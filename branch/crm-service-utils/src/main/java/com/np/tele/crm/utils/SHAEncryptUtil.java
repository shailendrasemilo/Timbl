package com.np.tele.crm.utils;

import java.security.MessageDigest;

public class SHAEncryptUtil
{
    public static String getSHA256( String inString )
    {
        StringBuffer strhash = new StringBuffer();
        try
        {
            //-------- Tampering code starts here -----
            String message = inString;
            MessageDigest messagedigest = MessageDigest.getInstance( "SHA-256" );
            messagedigest.update( message.getBytes() );
            byte digest[] = messagedigest.digest();
            strhash = new StringBuffer( digest.length * 2 );
            int length = digest.length;
            for ( int n = 0; n < length; n++ )
            {
                //System.out.println("-==--=>"+String.valueOf(bytes[n]));
                int number = digest[n];
                // System.out.println("number==="+ number);
                if ( number < 0 )
                {
                    number = number + 256;
                }
                //number = (number < 0) ? (number + 256) : number; // shift to positive range
                // System.out.println("to strimg=="+Integer.toString(number,16));
                String str1 = "";
                if ( Integer.toString( number, 16 ).length() == 1 )
                {
                    str1 = "0" + String.valueOf( Integer.toString( number, 16 ) );
                }
                else
                {
                    str1 = String.valueOf( Integer.toString( number, 16 ) );
                }
                strhash.append( str1 );
            }
        }
        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
        }
        return strhash.toString();
    }
}
