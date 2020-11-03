package com.np.tele.crm.utils;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.utils.StringUtils;

public class HibernateUtil
{
    //private static final Logger logger       = Logger.getLogger( HibernateUtil.class );
    private static final SessionFactory SESSION_FACTORY;
    private static final Logger         LOGGER   = Logger.getLogger( HibernateUtil.class );
    private static String               filePath = null;
    static
    {
        try
        {
            filePath = PropertyUtils.getServiceDetails( IPropertiesConstant.HIBERNATE_PATH );
            LOGGER.info( "hibernate File" + filePath );
            SESSION_FACTORY = new Configuration().configure( filePath ).buildSessionFactory();
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "exceptin " + ex );
            throw new ExceptionInInitializerError( ex );
        }
    }

    public static SessionFactory getSessionFactory()
    {
        return SESSION_FACTORY;
    }

    public static Session getCurrentSession()
    {
        Session session = getSessionFactory().openSession();
        return session;
    }

    /* public static void main( String[] args )
     {
         System.out.println( "deepak1" );
         SessionFactory sf = HibernateUtil.getSessionFactory();
         if ( null == sf )
         {
             System.out.println( "not connected" );
         }
         else
         {
             Session session = sf.openSession();
             Query q = session.createQuery( "from Student" );
             Transaction t = session.beginTransaction();
             Student s = new Student();
             s.setDegree( "MCA" );
             s.setName( "deepak" );
             s.setPhone( "99999999" );
             session.save( s );
             t.commit();
             System.out.println( "" + q.list().size() );
         }
     }*/
    public static boolean evictAll( String entityName, String inCollection, Serializable inSerilaizedId )
    {
        try
        {
            if ( StringUtils.isValidObj( inSerilaizedId ) )
            {
                getSessionFactory().evictEntity( entityName, inSerilaizedId );
            }
            else
            {
                getSessionFactory().evictEntity( entityName );
            }
            if ( StringUtils.isNotBlank( inCollection ) )
            {
                getSessionFactory().evictCollection( entityName + "." + inCollection );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        return true;
    }

    public static void evictAll( Map<String, Long> inEvicts )
    {
        if ( StringUtils.isValidObj( inEvicts ) )
        {
            Set<String> keys = inEvicts.keySet();
            for ( String key : keys )
            {
                HibernateUtil.evictAll( key, null, inEvicts.get( key ) );
            }
        }
    }
}
