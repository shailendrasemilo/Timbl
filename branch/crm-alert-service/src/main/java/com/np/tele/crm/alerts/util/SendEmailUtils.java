package com.np.tele.crm.alerts.util;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.ext.pojos.EmailServerPojo;
import com.np.tele.crm.pojos.EmailCcBccPojo;
import com.np.tele.crm.utils.StringUtils;

public class SendEmailUtils
{
    private static final Logger LOGGER            = Logger.getLogger( SendEmailUtils.class );
    EmailServerPojo             emailServerPojo   = null;
    private Properties          serverProps       = null;
    MailAuthenticator           mailAuthenticator = null;

    public SendEmailUtils( EmailServerPojo inEmailServerPojo )
    {
        serverProps = new Properties();
        mailAuthenticator = new MailAuthenticator( inEmailServerPojo.getUserid(), inEmailServerPojo.getPassword() );
        serverProps.setProperty( "mail.smtp.submitter", mailAuthenticator.getUserName() );
        serverProps.setProperty( "mail.smtp.starttls.enable", String.valueOf( inEmailServerPojo.isAuth() ) );
        serverProps.setProperty( "mail.smtp.auth", String.valueOf( inEmailServerPojo.isAuth() ) );
        serverProps.setProperty( "mail.smtp.host", inEmailServerPojo.getHost() );
        serverProps.setProperty( "mail.smtp.port", String.valueOf( inEmailServerPojo.getPort() ) );
        serverProps.setProperty( "mail.smtp.connectiontimeout",
                                 String.valueOf( inEmailServerPojo.getConnection_time_out() ) );
        serverProps.setProperty( "mail.smtp.timeout", String.valueOf( inEmailServerPojo.getSocket_time_out() ) );
        serverProps.setProperty( "mail.smtp.user", inEmailServerPojo.getUserid() );
        serverProps.setProperty( "mail.smtp.password", inEmailServerPojo.getPassword() );
        this.emailServerPojo = inEmailServerPojo;
    }

    public boolean sendEmail( final String inFrom,
                              final Address[] inTo,
                              final Set<EmailCcBccPojo> inCcBcc,
                              final String inSubject,
                              final String inMessage,
                              final List<String> inAttachment )
    {
        LOGGER.info( "Subject: " + inSubject );
        LOGGER.info( "Message: " + inMessage );
        boolean sendStatus = false;
        Session session = null;
        Message message = null;
        Multipart multiPart = null;
        if ( !emailServerPojo.isEnable() )
        {
            LOGGER.info( "Email Alerts have been turned off...Returning false." );
        }
        else if ( !StringUtils.isEmpty( emailServerPojo.getFrom() ) && StringUtils.isValidObj( inTo )
                && !StringUtils.isEmpty( inMessage ) )
        {
            try
            {
                session = Session.getInstance( serverProps, mailAuthenticator );
                message = new MimeMessage( session );
                message.setFrom( new InternetAddress( inFrom ) );
                message.addRecipients( Message.RecipientType.TO, inTo );
                if ( !StringUtils.isEmpty( inSubject ) )
                    message.setSubject( inSubject );
                if ( StringUtils.isValidObj( inCcBcc ) && !inCcBcc.isEmpty() )
                {
                    for ( EmailCcBccPojo emailCcBccPojo : inCcBcc )
                    {
                        if ( emailCcBccPojo.getEmailType().equals( CRMParameter.CC.getParameter() ) )
                        {
                            message.addRecipient( Message.RecipientType.CC,
                                                  new InternetAddress( emailCcBccPojo.getEmailId() ) );
                        }
                        else if ( emailCcBccPojo.getEmailType().equals( CRMParameter.BCC.getParameter() ) )
                        {
                            message.addRecipient( Message.RecipientType.BCC,
                                                  new InternetAddress( emailCcBccPojo.getEmailId() ) );
                        }
                    }
                }
                if ( StringUtils.isValidObj( inAttachment ) && !inAttachment.isEmpty() )
                {
                    multiPart = new MimeMultipart();
                    MimeBodyPart textPart = new MimeBodyPart();
                    textPart.setContent( new String( inMessage ), "text/html;charset=utf-8" );
                    multiPart.addBodyPart( textPart );
                    for ( String filePath : inAttachment )
                    {
                        addAttachment( multiPart, filePath );
                    }
                    message.setContent( multiPart );
                }
                else
                {
                    message.setContent( inMessage, "text/html;charset=utf-8" );
                }
                message.setSentDate( new Date() );
                Transport.send( message );
                sendStatus = true;
            }
            catch ( AddressException ex )
            {
                LOGGER.error( "Address Exception occured while resolve recipients: ", ex );
            }
            catch ( MessagingException exc )
            {
                LOGGER.error( "Messaging Exception occured while preparing message: ", exc );
            }
        }
        return sendStatus;
    }

    private void addAttachment( Multipart inMultipart, String inFilename )
    {
        try
        {
            BodyPart messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource( inFilename );
            messageBodyPart.setFileName( source.getName() );
            if ( StringUtils.startsWith( inFilename, "http" ) )
            {
                source = new URLDataSource( new URL( inFilename ) );
            }
            messageBodyPart.setDataHandler( new DataHandler( source ) );
            inMultipart.addBodyPart( messageBodyPart );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while including Attachment: ", ex );
        }
    }
    private class MailAuthenticator
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
}
