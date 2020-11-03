package com.np.tele.crm.utils;

import java.io.Serializable;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.np.tele.crm.utils.StringUtils;

public class HibernateSQLServerUtil
{
    //private static final Logger logger       = Logger.getLogger( HibernateUtil.class );
    private static final SessionFactory SESSION_FACTORY;
    private static final Logger         LOGGER = Logger.getLogger( HibernateSQLServerUtil.class );
    static
    {
        try
        {
            SESSION_FACTORY = new Configuration().configure( "hibernate.sql.server.cfg.xml" ).buildSessionFactory();
        }
        catch ( Throwable ex )
        {
            //logger.error( "Initial SessionFactory creation failed." + ex );
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
        catch ( Exception ex )
        {
            LOGGER.error( ex.getMessage(), ex );
        }
        return true;
    }
}
