package com.np.tele.crm.alerts.util;

import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmbededMail
{
    public static void main( String[] args )
        throws AddressException, MessagingException, IOException
    {
        class MailAuthenticator
            extends Authenticator
        {
            final private PasswordAuthentication authentication;

            public MailAuthenticator( final String userName, final String password )
            {
                this.authentication = new PasswordAuthentication( userName, password );
            }

            protected PasswordAuthentication getPasswordAuthentication()
            {
                return this.authentication;
            }

            public String getUserName()
            {
                if ( authentication != null )
                {
                    return authentication.getUserName();
                }
                else
                {
                    return "";
                }
            }
        }
        String host = "smtp.gmail.com";
        String from = "mukesh.sharma@netprophetsglobal.com";
        String to = "shailendra.pathak@netprophetsglobal.com";
        // Get system properties
        Properties props = System.getProperties();
        // Setup mail server
        MailAuthenticator mailAuthenticator = new MailAuthenticator( "mukesh.sharma@netprophetsglobal.com",
                                                                     "mukeshsharma" );
        props.setProperty( "mail.smtp.submitter", mailAuthenticator.getUserName() );
        props.setProperty( "mail.smtp.starttls.enable", "true" );
        props.setProperty( "mail.smtp.auth", String.valueOf( true ) );
        props.setProperty( "mail.smtp.host", host );
        props.setProperty( "mail.smtp.port", "587" );
        props.setProperty( "mail.smtp.connectiontimeout", "500" );
        props.setProperty( "mail.smtp.timeout", "5000" );
        // Get session
        Session session = Session.getInstance( props, mailAuthenticator );
        // Define message
        MimeMessage message = new MimeMessage( session );
        message.setFrom( new InternetAddress( from ) );
        message.addRecipient( Message.RecipientType.TO, new InternetAddress( to ) );
        message.setSubject( "Hello JavaMail" );
        // Handle attachment 1
        MimeBodyPart messageBodyPart1 = new MimeBodyPart();
        messageBodyPart1.attachFile( "F:/RajeshOffer.doc" );
        // Handle attachment 2
        MimeBodyPart messageBodyPart2 = new MimeBodyPart();
        messageBodyPart2.attachFile( "F:/RajeshOffer.doc" );
        FileDataSource fileDs = new FileDataSource( "F:/imagePuzzle_Nawab.jpg" );
        MimeBodyPart imageBodypart = new MimeBodyPart();
        imageBodypart.setDataHandler( new DataHandler( fileDs ) );
        imageBodypart.setHeader( "Content-ID", "<myimg>" );
        imageBodypart.setDisposition( MimeBodyPart.INLINE );
        // Handle text
        String body = "<html><body>Elotte<img src=\"cid:myimg\" width=\"600\" height=\"90\" alt=\"myimg\" />Utana</body></html>";
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setHeader( "Content-Type", "text/plain; charset=\"utf-8\"" );
        textPart.setContent( body, "text/html; charset=utf-8" );
        MimeMultipart multipart = new MimeMultipart( "mixed" );
        multipart.addBodyPart( textPart );
        multipart.addBodyPart( imageBodypart );
        multipart.addBodyPart( messageBodyPart1 );
        multipart.addBodyPart( messageBodyPart2 );
        message.setContent( multipart );
        // Send message
        Transport.send( message );
    }
}
