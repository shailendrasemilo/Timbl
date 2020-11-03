/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.np.tele.crm.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.jboss.logging.MDC;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ModulesPojo;
import com.np.tele.crm.pojos.RolesPojo;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;

/**
 *
 * @author user
 */
public class SecurityFilter
    implements Filter
{
    private static final boolean DEBUG        = true;
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig         filterConfig = null;
    private static final Logger  LOGGER       = Logger.getLogger( SecurityFilter.class );

    public SecurityFilter()
    {
    }

    private void doBeforeProcessing( ServletRequest inRequest, ServletResponse inResponse )
        throws IOException, ServletException
    {
        if ( DEBUG )
        {
            log( "SecurityFilter:DoBeforeProcessing" );
        }
        try
        {
            HttpServletRequest httpRequest = (HttpServletRequest) inRequest;
            HttpSession session = httpRequest.getSession( false );
            boolean toProcess = StringUtils.isValidObj( session );
            if ( toProcess )
            {
                Object moduleObj = session.getAttribute( IAppConstants.MODULES );
                List<ModulesPojo> modules = null;
                List<ModulesPojo> subModules = null;
                List<ModulesPojo> subSubModules = null;
                String methodName = null;
                if ( StringUtils.isValidObj( moduleObj ) && moduleObj instanceof List<?> )
                {
                    methodName = httpRequest.getParameter( IAppConstants.PARAMETER_NAME );
                }
                LOGGER.info( "Method Name:" + methodName );
                toProcess = validateRole( session, methodName );
                if ( !toProcess )
                {
                    session.setAttribute( IAppConstants.INVALID_ROLE, true );
                    return;
                }
                Map<String, List<ModulesPojo>> selectedModules = ModuleManager.getSelectedModule( methodName );
                if ( StringUtils.isValidObj( selectedModules ) && !selectedModules.isEmpty() )
                {
                    modules = selectedModules.get( IAppConstants.MODULES );
                    subModules = selectedModules.get( IAppConstants.SUB_MODULES );
                    subSubModules = selectedModules.get( IAppConstants.SUB_SUB_MODULES );
                    if ( StringUtils.isValidObj( modules ) && !modules.isEmpty() )
                    {
                        session.setAttribute( IAppConstants.MODULES, modules );
                    }
                    if ( StringUtils.isValidObj( subModules ) && !subModules.isEmpty() )
                    {
                        session.setAttribute( IAppConstants.SUB_MODULES, subModules );
                    }
                    if ( StringUtils.isValidObj( subSubModules ) && !subSubModules.isEmpty() )
                    {
                        session.setAttribute( IAppConstants.SUB_SUB_MODULES, subSubModules );
                    }
                }
                RolesPojo rolesPojo = (RolesPojo) session.getAttribute( IAppConstants.CRM_ROLES );
                List<String> roles = (List<String>) session.getAttribute( IAppConstants.CRM_ROLES_LIST );
                if ( ( StringUtils.isValidObj( rolesPojo ) ) && ( !StringUtils.isValidObj( roles ) || roles.isEmpty() ) )
                {
                    Map<String, Boolean> rolesPojos = rolesPojo.getRolesPojos();
                    roles = new ArrayList<String>();
                    if ( StringUtils.isValidObj( rolesPojos ) && !rolesPojos.isEmpty() )
                    {
                        roles.addAll( rolesPojos.keySet() );
                    }
                    session.setAttribute( IAppConstants.CRM_ROLES_LIST, roles );
                }
                LOGGER.info( "Roles Pojos:" + roles );
                if ( StringUtils.isValidObj( roles ) && !roles.contains( "ALL" ) )
                {
                    if ( StringUtils.isValidObj( modules ) )
                    {
                        ModuleManager.processRoles( modules, roles, true );
                        if ( StringUtils.isValidObj( modules ) && !modules.isEmpty() )
                        {
                            session.setAttribute( IAppConstants.MODULES, modules );
                        }
                    }
                    if ( StringUtils.isValidObj( subModules ) )
                    {
                        ModuleManager.processRoles( subModules, roles, true );
                        if ( StringUtils.isValidObj( subModules ) && !subModules.isEmpty() )
                        {
                            session.setAttribute( IAppConstants.SUB_MODULES, subModules );
                        }
                    }
                    if ( StringUtils.isValidObj( subSubModules ) )
                    {
                        ModuleManager.processRoles( subSubModules, roles, false );
                        if ( StringUtils.isValidObj( subSubModules ) && !subSubModules.isEmpty() )
                        {
                            session.setAttribute( IAppConstants.SUB_SUB_MODULES, subSubModules );
                        }
                    }
                }
            }
        }
        catch ( Throwable t )
        {
            LOGGER.error( "Error in Do After Processing", t );
        }
        // Write code here to process the request and/or response before
        // the rest of the filter chain is invoked.
        // For example, a logging filter might log items on the request object,
        // such as the parameters.
        /*
            for (Enumeration en = request.getParameterNames(); en.hasMoreElements(); ) {
            String name = (String)en.nextElement();
            String values[] = request.getParameterValues(name);
            int n = values.length;
            StringBuffer buf = new StringBuffer();
            buf.append(name);
            buf.append("=");
            for(int i=0; i < n; i++) {
            buf.append(values[i]);
            if (i < n-1)
            buf.append(",");
            }
            log(buf.toString());
            }
             */
    }

    private boolean validateRole( HttpSession inSession, String inMethodName )
    {
        try
        {
            if ( StringUtils.isNotBlank( inMethodName ) )
            {
                String strModule = PropertyUtils.getModuleDetails( inMethodName );
                String[] roleArr = null;
                if ( StringUtils.isNotBlank( strModule ) && StringUtils.contains( strModule, IAppConstants.COMMA ) )
                {
                    LOGGER.info( "Selected Module = " + strModule );
                    roleArr = StringUtils.split( strModule, IAppConstants.COMMA );
                    if ( roleArr.length > 1 )
                    {
                        List<String> roles = (List<String>) inSession.getAttribute( IAppConstants.CRM_ROLES_LIST );
                        roleArr[0] = "";
                        roleArr[roleArr.length - 1] = StringUtils.trim( roleArr[roleArr.length - 1] );
                        Set<String> moduleRoles = new HashSet<String>( Arrays.asList( roleArr ) );
                        LOGGER.info( "Roles for a " + inMethodName + " = " + moduleRoles );
                        if ( StringUtils.isValidObj( roles ) && !roles.isEmpty() && !moduleRoles.isEmpty()
                                && !roles.contains( "ALL" ) )
                        {
                            moduleRoles.retainAll( roles );
                            LOGGER.info( "Roles present for " + inMethodName + " = " + moduleRoles );
                            if ( moduleRoles.isEmpty() )
                            {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception", ex );
        }
        return true;
    }

    private void doAfterProcessing( ServletRequest inRequest, ServletResponse inResponse )
        throws IOException, ServletException
    {
        if ( DEBUG )
        {
            log( "SecurityFilter:DoAfterProcessing" );
        }
    }

    /**
     *
     * @param inRequest The servlet request we are processing
     * @param inResponse The servlet response we are creating
     * @param inChain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
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
            Object userDetails = session.getAttribute( IAppConstants.CRM_USER_OBJECT );
            String methodName = httpRequest.getParameter( IAppConstants.PARAMETER_NAME );
            LOGGER.info( "Method Name : " + methodName );
            if ( StringUtils.isNotBlank( methodName ) )
            {
                MDC.put( "METHOD", methodName );
            }
            if ( StringUtils.isValidObj( ( userDetails ) ) )
            {
                Boolean invalidRole = false;
                try
                {
                    invalidRole = (Boolean) session.getAttribute( IAppConstants.INVALID_ROLE );
                }
                catch ( Exception ex )
                {
                    LOGGER.error( "Exception", ex );
                }
                if ( !StringUtils.isValidObj( invalidRole ) || !invalidRole )
                {
                    inChain.doFilter( inRequest, inResponse );
                }
                else
                {
                    session.removeAttribute( IAppConstants.INVALID_ROLE );
                    ServletContext ctx = this.filterConfig.getServletContext();
                    LOGGER.info( "SecurityFilter doFilter: ctx.getContextPath() = '" + ctx.getContextPath() + "'" );;
                    RequestDispatcher dispatcher = ctx.getRequestDispatcher( "/login.do?method=redirectHome" );
                    dispatcher.forward( inRequest, inResponse );
                }
            }
            else
            {
                String uri = httpRequest.getRequestURI();
                LOGGER.info( "SecurityFilter doFilter: request.uri = '" + uri + "'" );
                /*String methodName = httpRequest.getParameter( IAppConstants.PARAMETER_NAME );*/
                if ( ( uri.endsWith( "login.do" ) || ( uri.endsWith( "forgetPassword.do" ) )
                        || ( uri.endsWith( "faultRepair.do" ) ) || uri.endsWith( "accountVerification.do" ) )
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
        finally
        {
            MDC.remove( "METHOD" );
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

    /**
     * Return the filter configuration object for this filter.
     * @return
     */
    public FilterConfig getFilterConfig()
    {
        return ( this.filterConfig );
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig( FilterConfig filterConfig )
    {
        this.filterConfig = filterConfig;
    }

    /**
     * Destroy method for this filter 
     */
    public void destroy()
    {
        this.filterConfig = null;
    }

    /**
     * Init method for this filter 
     */
    public void init( FilterConfig filterConfig )
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

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString()
    {
        if ( filterConfig == null )
        {
            return ( "SecurityFilter()" );
        }
        StringBuffer sb = new StringBuffer( "SecurityFilter(" );
        sb.append( filterConfig );
        sb.append( ")" );
        return ( sb.toString() );
    }

    private void sendProcessingError( Throwable t, ServletResponse response )
    {
        String stackTrace = getStackTrace( t );
        if ( stackTrace != null && !stackTrace.equals( "" ) )
        {
            try
            {
                response.setContentType( "text/html" );
                PrintStream ps = new PrintStream( response.getOutputStream() );
                PrintWriter pw = new PrintWriter( ps );
                pw.print( "<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n" ); //NOI18N
                // PENDING! Localize this for next official release
                pw.print( "<h1>The resource did not process correctly</h1>\n<pre>\n" );
                pw.print( stackTrace );
                pw.print( "</pre></body>\n</html>" ); //NOI18N
                pw.close();
                ps.close();
                response.getOutputStream().close();
            }
            catch ( Exception ex )
            {
            }
        }
        else
        {
            try
            {
                PrintStream ps = new PrintStream( response.getOutputStream() );
                t.printStackTrace( ps );
                ps.close();
                response.getOutputStream().close();
            }
            catch ( Exception ex )
            {
            }
        }
    }

    public static String getStackTrace( Throwable t )
    {
        String stackTrace = null;
        try
        {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter( sw );
            t.printStackTrace( pw );
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        }
        catch ( Exception ex )
        {
        }
        return stackTrace;
    }

    public void log( String msg )
    {
        filterConfig.getServletContext().log( msg );
    }
}
