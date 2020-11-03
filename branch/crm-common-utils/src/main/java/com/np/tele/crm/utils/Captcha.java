package com.np.tele.crm.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;


public class Captcha
    extends HttpServlet
{
    
    /**
     * 
     */
    private static final Logger logger            = Logger.getLogger( Captcha.class );
    private static final long   serialVersionUID  = -4687606895768367L;
    private int                 height            = 0;
    private int                 width             = 0;
    public static final String  CAPTCHA_KEY       = "captcha_key_name";
    private boolean             useFixedCaptcha   = false;
    private String              fixedCaptchaValue = "1234";
    private String              severIP           = null;

    public void init( ServletConfig config )
        throws ServletException
    {
        super.init( config );
        height = Integer.parseInt( getServletConfig().getInitParameter( "height" ) );
        width = Integer.parseInt( getServletConfig().getInitParameter( "width" ) );
        useFixedCaptcha = Boolean.parseBoolean( getServletConfig().getInitParameter( "FixedCaptchaEnabled" ) );
        fixedCaptchaValue = getServletConfig().getInitParameter( "FixedCaptchaValue" );
     
        String applicationName=getServletConfig().getInitParameter( "ApplicationName" );//get application name from the servlet init param
        
        if ( useFixedCaptcha )
        {
            logger.warn( "Using fixed CAPTCHA in "+applicationName+" : " + fixedCaptchaValue
                    + " This should only be done in TEST ENVIRONMENT" );
        }
      //  severIP = VTACommonUtils.getServerIp();
        if ( useFixedCaptcha )
        {
            try
            {
                logger.info( "using fixed captcha on SB Portal with server IP " + severIP );
               //SendEmailUtil.getInstance().sendEmail( null, "Using fixed captcha in VTA Smartbytes Portal",
                                       //  "Please check, Using fixed captcha on server IP :: " + severIP ,MailAndSMSAccounts.INTERNAL_ALERT);*///Should be pass sender profile name for the sending mail account
           
            }
            catch ( Exception ex )
            {
                logger.info( "Unable to send mail." + ex );
            }
        }
    }

    protected void doGet( HttpServletRequest req, HttpServletResponse response )
        throws IOException, ServletException
    {
        //Expire response
        response.setHeader( "Cache-Control", "no-cache" );
        response.setDateHeader( "Expires", 0 );
        response.setHeader( "Pragma", "no-cache" );
        response.setDateHeader( "Max-Age", 0 );
        BufferedImage image = new BufferedImage( width, height, BufferedImage.TYPE_INT_RGB );
        Graphics2D graphics2D = image.createGraphics();

        Random r = new Random();
        String token = Long.toString( Math.abs( r.nextLong() ), 36 );
        String ch = null;
        if ( useFixedCaptcha )
        {
            ch = fixedCaptchaValue;
        }
        else
        {
            ch = token.substring( 0, 6 );
        }
        Color c = new Color( 0.6662f, 0.4569f, 0.3232f );
        GradientPaint gp = new GradientPaint( 30, 30, c, 15, 25, Color.white, true );
        graphics2D.setPaint( gp );
        Font font = new Font( "Lucida Calligraphy", Font.CENTER_BASELINE, 18 );
        graphics2D.setFont( font );
        graphics2D.drawString( ch, 2, 20 );
        graphics2D.dispose();
        HttpSession session = req.getSession( true );
        session.setAttribute( CAPTCHA_KEY, ch );
        OutputStream outputStream = response.getOutputStream();
        ImageIO.write( image, "jpeg", outputStream );
        outputStream.close();
    }

    
}
