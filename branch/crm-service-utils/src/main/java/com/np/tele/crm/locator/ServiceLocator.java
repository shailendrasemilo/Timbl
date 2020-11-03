package com.np.tele.crm.locator;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;

import com.np.tele.crm.utils.StopWatch;

@SuppressWarnings(
{ "unchecked", "rawtypes" })
public class ServiceLocator
{
    private static InitialContext ctx;
    private static Logger         logger = Logger.getLogger( ServiceLocator.class );

    public static <T> T lookup( Class<T> clazz, String name )
    {
        return lookup( clazz, name, getContext() );
    }

    public static <T> T lookup( Class<T> clazz, String name, Hashtable icProps )
    {
        try
        {
            return lookup( clazz, name, getCustomInitialContext( icProps ) );
        }
        catch ( NamingException ex )
        {
            String mesg = "Naming Exception while getting Remote Initial Context";
            logger.error( mesg );
            throw new RuntimeException( mesg );
        }
    }

    public static <T> T lookup( Class<T> clazz, String name, InitialContext localCtx )
    {
        StopWatch sw = new StopWatch( "lookup-" + name );
        try
        {
            final Object object = localCtx.lookup( name );
            if ( clazz.isAssignableFrom( object.getClass() ) )
            {
                return (T) object;
            }
            else
            {
                String mesg = String
                        .format( "Class found: %s cannot be assigned to type: %s", object.getClass(), clazz );
                logger.error( mesg );
                throw new RuntimeException( mesg );
            }
        }
        catch ( NamingException e )
        {
            throw new RuntimeException( String.format( "Unable to find Resource for %s. Name passed is %s",
                                                       clazz.getName(), name ), e );
        }
        finally
        {
            if ( logger.isDebugEnabled() )
            {
                logger.debug( sw );
            }
        }
    }

    private static InitialContext getCustomInitialContext( Hashtable envProps )
        throws NamingException
    {
        InitialContext remoteInitialContext = new InitialContext( envProps );
        return remoteInitialContext;
    }

    private static InitialContext getContext()
    {
        if ( ctx == null )
        {
            try
            {
                ctx = new InitialContext();
            }
            catch ( NamingException e )
            {
                String mesg = "Unable to get initial context";
                logger.fatal( mesg, e );
                throw new RuntimeException( mesg, e );
            }
        }
        return ctx;
    }
}
