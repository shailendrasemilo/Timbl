package com.np.tele.crm.utils;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.np.tele.crm.constants.IPropertiesConstant;

public class HibernateReportUtil
{
    private static final SessionFactory SESSION_FACTORY;
    private static final Logger         LOGGER   = Logger.getLogger( HibernateReportUtil.class );
    private static String               filePath = null;
    static
    {
        try
        {
            filePath = PropertyUtils.getServiceDetails( IPropertiesConstant.HIBERNATE_REPORT_PATH );
            LOGGER.info( "hibernate File" + filePath );
            SESSION_FACTORY = new Configuration().configure( filePath ).buildSessionFactory();
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception ::" + ex );
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
}
