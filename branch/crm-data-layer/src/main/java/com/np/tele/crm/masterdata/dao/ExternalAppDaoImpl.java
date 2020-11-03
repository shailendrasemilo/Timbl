package com.np.tele.crm.masterdata.dao;

import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.pojos.ProjectsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;

public class ExternalAppDaoImpl
    implements IExternalApplicationDao
{
    private static final Logger LOGGER = Logger.getLogger( ExternalAppDaoImpl.class );

    @Override
    public MasterDataDto registerExternalApp( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        LOGGER.info( "Entering registerExternalAppDao" );
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria( ProjectsPojo.class );
            criteria.add( Restrictions.eq( "projectName", inMasterDataDto.getProjectsPojo().getProjectName() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( ProjectsPojo.class.getName() );
            List<ProjectsPojo> projectsPojosList = criteria.list();
            if ( projectsPojosList.size() > 0 )
            {
                crmServiceCode = CRMServiceCode.CRM054;
                LOGGER.info( "Project Name Already Exist" + inMasterDataDto.getProjectsPojo().getProjectName() );
            }
            else
            {
                ProjectsPojo projectsPojo = inMasterDataDto.getProjectsPojo();
                projectsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                Long id = (Long) session.save( projectsPojo );
                if ( id > 0 )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                    LOGGER.info( "External Project Registeration Successfully. Generated Id :: " + id );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM999;
                    LOGGER.info( "Getting Error While Registered External Project" );
                }
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Register External Application: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while Register External Application: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( ProjectsPojo.class.getName(), null, null );
            }
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        LOGGER.info( "Exit registerExternalAppDao" );
        return inMasterDataDto;
    }

    /* public static void main( String[] args )
     {
         ProjectsPojo p = new ProjectsPojo();
         p.setProjectName( "NexterOne12" );
         p.setProjectType( "E" );
         p.setProjectDescription( "Test Desc" );
         p.setStatus( "A" );
         p.setCreatedBy( "TestUser" );
         CrmParameterPojo crmParameterPojo = new CrmParameterPojo();
         crmParameterPojo.setParameterGroup( "tempalte" );
         crmParameterPojo.setParameterGroupType( "template" );
         crmParameterPojo.setParameterLength( 5L );
         crmParameterPojo.setParameterName( "Salary" );
         crmParameterPojo.setParameterType( "long" );
         crmParameterPojo.setCreatedBy( "TestUser" );
         CrmParameterPojo crmParameterPojo1 = new CrmParameterPojo();
         crmParameterPojo1.setParameterGroup( "tempalte" );
         crmParameterPojo1.setParameterGroupType( "template" );
         crmParameterPojo1.setParameterLength( 5L );
         crmParameterPojo1.setParameterName( "Salary" );
         crmParameterPojo1.setParameterType( "long" );
         crmParameterPojo1.setCreatedBy( "TestUser" );
         Set<CrmParameterPojo> crmParameterPojos = new HashSet<CrmParameterPojo>();
         crmParameterPojos.add( crmParameterPojo );
         crmParameterPojos.add( crmParameterPojo1 );
         p.setCrmParameterPojosSet( crmParameterPojos );
         ExternalAppDaoImpl eImpl = new ExternalAppDaoImpl();
         eImpl.registerExternalApp( p );
     }*/
    @Override
    public List<ProjectsPojo> listRegisteredExternalApp( ProjectsPojo inPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        LOGGER.info( "Inside listRegisteredExternalAppDao" );
        List<ProjectsPojo> pojosList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( ProjectsPojo.class );
            criteria.add( Restrictions.eq( "projectType", inPojo.getProjectType() ) );
            if ( StringUtils.isNotEmpty( inPojo.getProjectName() ) )
            {
                criteria.add( Restrictions.ilike( "projectName", inPojo.getProjectName(), MatchMode.ANYWHERE ) );
            }
            if ( StringUtils.isNotBlank( inPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", inPojo.getStatus() ) );
            }
            criteria.addOrder( Order.desc( "createdTime" ) );
            criteria.addOrder( Order.desc( "lastModifiedTime" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( ProjectsPojo.class.getName() );
            pojosList = criteria.list();
            LOGGER.info( "External Project List" + pojosList.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Listing Register External Application: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Exit listRegisteredExternalAppDao" );
        return pojosList;
    }

    @Override
    public boolean modifyExternalAppStatus( ProjectsPojo inPojo )
    {
        LOGGER.info( "Inside modifyExternalAppStatusDao" );
        boolean isSuccess = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            ProjectsPojo pojo = (ProjectsPojo) session.get( ProjectsPojo.class, inPojo.getProjectId() );
            LOGGER.info( "Status:" + inPojo.getStatus() + "Project ID:" + inPojo.getProjectId() );
            pojo.setStatus( inPojo.getStatus() );
            pojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            pojo.setLastModifiedBy( inPojo.getLastModifiedBy() );
            session.update( pojo );
            transaction.commit();
            isSuccess = true;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Change Status of External Application:", ex );
            isSuccess = false;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            isSuccess = false;
            LOGGER.error( "Getting Error while Change Status of External Application: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( isSuccess )
            {
                HibernateUtil.evictAll( ProjectsPojo.class.getName(), null, null );
            }
        }
        LOGGER.info( "Exit modifyExternalAppStatusDao" );
        return isSuccess;
    }

    @Override
    public MasterDataDto updateExternalProjectParam( MasterDataDto inMasterDataDto )
    {
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            ProjectsPojo projectsPojo = inMasterDataDto.getProjectsPojo();
            ProjectsPojo pojo = (ProjectsPojo) session.get( ProjectsPojo.class, projectsPojo.getProjectId() );
            if ( StringUtils.isValidObj( pojo ) )
            {
                pojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                pojo.setLastModifiedBy( projectsPojo.getLastModifiedBy() );
                pojo.setProjectDescription( projectsPojo.getProjectDescription() );
                session.flush();
                session.clear();
                pojo.getCrmParameterPojosSet().removeAll( projectsPojo.getCrmParameterPojosSet() );
                pojo.getCrmParameterPojosSet().addAll( projectsPojo.getCrmParameterPojosSet() );
                transaction = session.beginTransaction();
                session.update( pojo );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                LOGGER.info( "Successfully Updated parameter which mapped by External project " );
            }
            else
            {
                LOGGER.info( "External Project Id not Exist in database." );
                crmServiceCode = CRMServiceCode.CRM046;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Update parameter which mapped by External project:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while Update parameter which mapped by External project " + ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( ProjectsPojo.class.getName(), null, null );
            }
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    @Override
    public ProjectsPojo getProject( final ProjectsPojo inPojo )
    {
        LOGGER.info( "Inside getProject" );
        SessionFactory sessionFactory = null;
        Session session = null;
        ProjectsPojo pojo = null;
        try
        {
            String status = inPojo.getStatus();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            pojo = (ProjectsPojo) session.get( ProjectsPojo.class, inPojo.getProjectId() );
            if ( StringUtils.isValidObj( inPojo ) && !StringUtils.equals( status, inPojo.getStatus() ) )
            {
                pojo = null;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Listing Register External Application: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Exit listRegisteredExternalAppDao" );
        return pojo;
    }
}