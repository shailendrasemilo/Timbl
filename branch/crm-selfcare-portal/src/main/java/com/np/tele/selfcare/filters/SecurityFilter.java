package com.np.tele.selfcare.filters;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.service.client.CrmCustMyAccountPojo;
import com.np.tele.crm.utils.StringUtils;

public class SecurityFilter
    implements Filter
{
    private static final boolean DEBUG        = true;
    private FilterConfig         filterConfig = null;
    private static final Logger  LOGGER       = Logger.getLogger( SecurityFilter.class );

    @Override
    public void destroy()
    {
        this.filterConfig = null;
    }

    @Override
    public void doFilter( ServletRequest inRequest, ServletResponse inResponse, FilterChain inChain )
        throws IOException, ServletException
    {
        if ( DEBUG )
        {
            log( "SecurityFilter:doFilter()" );
        }
        doBeforeProcessing( inRequest, inResponse );
        Throwable problem = null;
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest) inRequest;
            HttpSession session = httpRequest.getSession( true );
            LOGGER.info( "SecurityFilter doFilter: request.url = '" + httpRequest.getRequestURL() + "' method='"
                    + httpRequest.getMethod() + "' queryString='" + httpRequest.getQueryString() + "'" + " protocol='"
                    + httpRequest.getProtocol() + "' referer='" + httpRequest.getHeader( "referer" ) + "' remote_ip='"
                    + httpRequest.getRemoteAddr() + "'" );
            LOGGER.info( "headers:::::::::::::::::::" );
            Enumeration<String> headerNames = httpRequest.getHeaderNames();
            while ( headerNames.hasMoreElements() )
            {
                String headerName = headerNames.nextElement();
                LOGGER.info( headerName );
                Enumeration<String> headers = httpRequest.getHeaders( headerName );
                while ( headers.hasMoreElements() )
                {
                    String headerValue = headers.nextElement();
                    LOGGER.info( "\t" + headerValue );
                }
            }
            LOGGER.info( "end headers:::::::::::::::::::" );
            Object userDetails = session.getAttribute( CrmCustMyAccountPojo.class.getSimpleName() );
            if ( StringUtils.isValidObj( ( userDetails ) ) )
            {
                inChain.doFilter( inRequest, inResponse );
            }
            else
            {
                String uri = httpRequest.getRequestURI();
                LOGGER.info( "SecurityFilter doFilter: request.uri = '" + uri + "'" );
                String methodName = httpRequest.getParameter( IAppConstants.PARAMETER_NAME );
                if ( ( uri.endsWith( "login.do" ) || uri.endsWith( "quickPay.do" ) || uri.endsWith( "hdfcresponse.do" )
                        || uri.endsWith( "tpresponse.do" ) || uri.endsWith( "atomresponse.do" )
                        || ( uri.endsWith( "forgetPassword.do" ) ) || uri.endsWith( "accountVerification.do" )
                        || uri.endsWith( "generatepdf.do" ) || uri.endsWith( "verify.do" ) )
                        && !StringUtils.equals( "redirectHome", methodName ) )
                {
                    inChain.doFilter( inRequest, inResponse );
                }
                else
                {
                    ServletContext ctx = this.filterConfig.getServletContext();
                    LOGGER.info( "SecurityFilter doFilter: ctx.getContextPath() = '" + ctx.getContextPath() + "'" );;
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher( "/" );
                    dispatcher.forward( inRequest, inResponse );
                }
            }
        }
        catch ( Throwable t )
        {
            // If an exception is thrown somewhere down the filter chain,
            // we still want to execute our after processing, and then
            // rethrow the problem after that.
            problem = t;
            t.printStackTrace();
        }
        doAfterProcessing( inRequest, inResponse );
        // If there was a problem, we want to rethrow it if it is
        // a known type, otherwise log it.
        if ( problem != null )
        {
            if ( problem instanceof ServletException )
            {
                throw (ServletException) problem;
            }
            if ( problem instanceof IOException )
            {
                throw (IOException) problem;
            }
            sendProcessingError( problem, inResponse );
        }
    }

    private void sendProcessingError( Throwable inProblem, ServletResponse inResponse )
    {
    }

    private void doAfterProcessing( ServletRequest inRequest, ServletResponse inResponse )
    {
    }

    private void doBeforeProcessing( ServletRequest inRequest, ServletResponse inResponse )
    {
    }

    public void setFilterConfig( FilterConfig filterConfig )
    {
        this.filterConfig = filterConfig;
    }

    @Override
    public void init( FilterConfig filterConfig )
        throws ServletException
    {
        this.filterConfig = filterConfig;
        if ( filterConfig != null )
        {
            if ( DEBUG )
            {
                log( "SecurityFilter:Initializing filter" );
            }
        }
    }

    public void log( String msg )
    {
        filterConfig.getServletContext().log( msg );
    }
}
