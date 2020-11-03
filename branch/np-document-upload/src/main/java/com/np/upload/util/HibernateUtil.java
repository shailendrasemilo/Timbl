package com.np.upload.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.np.upload.pojo.NpUploads;

public class HibernateUtil
{
    private static final SessionFactory SESSION_FACTORY;
    static
    {
        try
        {
            System.out.println( "initializing session factory" );
            SESSION_FACTORY = new Configuration().configure( "hibernate.cfg.xml" ).buildSessionFactory();
        }
        catch ( Throwable ex )
        {
            System.out.println( "exception while starting session factory" );
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

    public static void closeSession( Session session )
    {
        if ( session != null && session.isOpen() )
        {
            session.close();
        }
    }

    public static void rollback( Transaction inTransaction )
    {
        if ( null != inTransaction && inTransaction.isActive() )
        {
            inTransaction.rollback();
        }
    }

    public static void main( String[] args )
    {
        System.out.println( "welcome" );
        SessionFactory sf = HibernateUtil.getSessionFactory();
        if ( null == sf )
        {
            System.out.println( "not connected" );
        }
        else
        {
            Session session = sf.openSession();
            Transaction t = session.beginTransaction();
            NpUploads npUploads = new NpUploads();
            npUploads.setMappingId( "123" );
            npUploads.setModuleName( "cde" );
            npUploads.setDocPath( "path" );
            session.save( npUploads );
            t.commit();
        }
    }
}
