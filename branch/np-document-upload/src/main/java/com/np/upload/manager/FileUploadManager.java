package com.np.upload.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.ui.ModelMap;

import com.np.upload.constants.IAppConstantsDms;
import com.np.upload.pojo.NpUploads;
import com.np.upload.util.HibernateUtil;

public class FileUploadManager
{
    public boolean saveFile( NpUploads inNpUploads, ModelMap inModelMap )
    {
        boolean success = true;
        Session session = null;
        Transaction transaction = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria( NpUploads.class );
            NpUploads npUploads = new NpUploads( inNpUploads.getModuleName(),
                                                 inNpUploads.getMappingId(),
                                                 inNpUploads.getDocType() );
            criteria.add( Example.create( npUploads ) );
            @SuppressWarnings("unchecked")
            List<NpUploads> dmsDb = criteria.list();
            //            if ( dmsDb.size() > 0 )
            //            {
            //                NpUploads dms = dmsDb.get( 0 );
            //                dms.setDocPath( inPath );
            //                dms.setModifiedTime( Calendar.getInstance().getTime() );
            //                session.update( dms );
            //            }
            //            else
            //            {
            inNpUploads.setVersion( dmsDb.size() + 1 );
            Integer id = (Integer) session.save( inNpUploads );
            if ( id < 1 )
            {
                success = false;
            }
            //            }
            transaction.commit();
        }
        catch ( HibernateException exception )
        {
            exception.printStackTrace();
            HibernateUtil.rollback( transaction );
            inModelMap.put( IAppConstantsDms.EXCEPTION, exception.getMessage() );
            success = false;
        }
        finally
        {
            HibernateUtil.closeSession( session );
        }
        return success;
    }

    @Deprecated
    public NpUploads retrieveFile( NpUploads inNpUploads )
    {
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria( NpUploads.class );
            criteria.add( Example.create( inNpUploads ) );
            criteria.addOrder( Order.desc( "createdTime" ) );
            @SuppressWarnings("unchecked")
            List<NpUploads> dmsDb = criteria.list();
            if ( dmsDb.size() > 0 )
            {
                inNpUploads = dmsDb.get( 0 );
            }
        }
        catch ( HibernateException exception )
        {
            System.err.println( exception.getMessage() );
        }
        finally
        {
            HibernateUtil.closeSession( session );
        }
        return inNpUploads;
    }

    public List<NpUploads> listUploadedFiles( NpUploads inNpUploads )
    {
        List<NpUploads> dmsDb = null;
        Session session = null;
        Query query = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            query = session.getNamedQuery( "LAST_ADDED_DOC" );
            query.setString( "module", inNpUploads.getModuleName() );
            query.setString( "mapping", inNpUploads.getMappingId() );
            //            query.setResultTransformer( Transformers.aliasToBean( NpUploads.class ) );
            dmsDb = query.list();
        }
        catch ( HibernateException exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession( session );
        }
        return dmsDb;
    }

    public List<NpUploads> listAllUploadedFiles( NpUploads npUploads )
    {
        return listAllUploadedFiles( npUploads, null );
    }

    public List<NpUploads> listAllUploadedFiles( NpUploads npUploads, Integer version )
    {
        List<NpUploads> dmsDb = null;
        Session session = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria( NpUploads.class );
            criteria.add( Example.create( npUploads ) );
            if ( version != null )
            {
                criteria.add( Restrictions.eq( "version", version ) );
            }
            criteria.addOrder( Order.desc( "docType" ) );
            criteria.addOrder( Order.desc( "version" ) );
            dmsDb = criteria.list();
        }
        catch ( HibernateException exception )
        {
            exception.printStackTrace();
        }
        finally
        {
            HibernateUtil.closeSession( session );
        }
        return dmsDb;
    }

    public static String removeEnd( String str, String remove )
    {
        if ( isEmpty( str ) || isEmpty( remove ) )
        {
            return str;
        }
        if ( str.endsWith( remove ) )
        {
            return str.substring( 0, str.length() - remove.length() );
        }
        return str;
    }

    public static boolean isEmpty( CharSequence cs )
    {
        return cs == null || cs.length() == 0;
    }
}
