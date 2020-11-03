package com.np.tele.crm.selfcare.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.pojos.CrmCustMyAccountPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SelfcareDaoImpl
    implements ISelfcareDao
{
    private final Logger LOGGER = Logger.getLogger( SelfcareDaoImpl.class );

    @Override
    public List<CrmCustomerDetailsPojo> getCustomerDetailAccounts( CrmCustMyAccountPojo inCustAccountPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmCustomerDetailsPojo> custDetails = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            if ( StringUtils.isNotBlank( inCustAccountPojo.getCustomerId() ) )
            {
                criteria.add( Restrictions.eq( "customerId", inCustAccountPojo.getCustomerId() ) );
            }
            else if ( StringUtils.isNotBlank( inCustAccountPojo.getCustEmailId() ) )
            {
                criteria.add( Restrictions.eq( "custEmailId", inCustAccountPojo.getCustEmailId() ) );
            }
            else if ( inCustAccountPojo.getRmn() > 0 )
            {
                criteria.add( Restrictions.eq( "rmn", inCustAccountPojo.getRmn() ) );
            }
            criteria.add( Restrictions.isNotNull( "activationDate" ) );
            criteria.add( Restrictions.in( "status",
                                           new String[]
                                           { CRMStatusCode.ACTIVE.getStatusCode(),
                                                   CRMStatusCode.BARRED.getStatusCode(),
                                                   CRMStatusCode.TDC.getStatusCode(), CRMStatusCode.SC.getStatusCode() } ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
            custDetails = criteria.list();
            LOGGER.info( "customer accounts: " + custDetails.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error SelfcareDaoImpl:getCustomerAccounts " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return custDetails;
    }

    @Override
    public List<CrmCustomerDetailsPojo> getCustomerDetailAccounts( List<String> inCustIds )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmCustomerDetailsPojo> custDetails = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            criteria.add( Restrictions.in( "customerId", inCustIds ) );
            criteria.add( Restrictions.isNotNull( "activationDate" ) );
            criteria.add( Restrictions.in( "status",
                                           new String[]
                                           { CRMStatusCode.ACTIVE.getStatusCode(),
                                                   CRMStatusCode.BARRED.getStatusCode(),
                                                   CRMStatusCode.SC.getStatusCode(), CRMStatusCode.TDC.getStatusCode() } ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
            custDetails = criteria.list();
            LOGGER.info( "customer accounts: " + custDetails.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error SelfcareDaoImpl:getCustomerAccounts " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return custDetails;
    }

    @Override
    public List<CrmCustMyAccountPojo> getCustomerMyAccounts( List<String> inCustIds, String inPassword )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmCustMyAccountPojo> custMyAccountPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustMyAccountPojo.class );
            criteria.add( Restrictions.in( "customerId", inCustIds ) );
            criteria.add( Restrictions.eq( "password", EncryptionUtil.encrypt( inPassword ) ) );
            criteria.add( Restrictions.in( "status", new String[]
            { CRMStatusCode.ACTIVE.getStatusCode(), CRMStatusCode.NEW.getStatusCode() } ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustMyAccountPojo.class.getName() );
            custMyAccountPojos = criteria.list();
            LOGGER.info( "myaccount list: " + custMyAccountPojos.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while get customer my account details", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return custMyAccountPojos;
    }

    @Override
    public List<CrmCustomerDetailsPojo> getCustomerDetailsList( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        Session session = null;
        Criteria criteria = null;
        List<CrmCustomerDetailsPojo> customerDetailsPojos = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            if ( inCustomerDetailsPojo.getRmn() > 0 )
            {
                criteria.add( Restrictions.eq( "rmn", inCustomerDetailsPojo.getRmn() ) );
            }
            criteria.add( Restrictions.isNotNull( "customerId" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
            customerDetailsPojos = criteria.list();
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while get Customer Details List", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return customerDetailsPojos;
    }

    @Override
    public CrmCustMyAccountPojo authenticate( CrmCustMyAccountPojo inCustAccountPojo )
    {
        CrmCustMyAccountPojo custMyAccountPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustMyAccountPojo.class );
            criteria.add( Restrictions.eq( "customerId", inCustAccountPojo.getCustomerId() ) );
            criteria.add( Restrictions.eq( "password", EncryptionUtil.encrypt( inCustAccountPojo.getPassword() ) ) );
            criteria.add( Restrictions.in( "status", new String[]
            { CRMStatusCode.ACTIVE.getStatusCode(), CRMStatusCode.NEW.getStatusCode() } ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmCustMyAccountPojo.class.getName() );
            List<CrmCustMyAccountPojo> myAccountPojos = criteria.list();
            LOGGER.info( "authentication list size: " + myAccountPojos.size() );
            if ( myAccountPojos.size() == 1 )
            {
                custMyAccountPojo = myAccountPojos.get( 0 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while Customer authenticate", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return custMyAccountPojo;
    }

    @Override
    public CRMServiceCode changePassword( CrmCustMyAccountPojo custMyAccountPojo )
    {
        String oldPassword = null;
        String newPassword = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            oldPassword = EncryptionUtil.encrypt( custMyAccountPojo.getPassword1() );
            newPassword = EncryptionUtil.encrypt( custMyAccountPojo.getPassword() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria( CrmCustMyAccountPojo.class );
            criteria.add( Restrictions.eq( "customerId", custMyAccountPojo.getCustomerId() ) );
            criteria.add( Restrictions.eq( "password", oldPassword ) );
            List<CrmCustMyAccountPojo> myAccountDBPojos = criteria.list();
            if ( CommonValidator.isValidCollection( myAccountDBPojos ) && myAccountDBPojos.size() == 1 )
            {
                CrmCustMyAccountPojo myAccountDBPojo = myAccountDBPojos.get( 0 );
                myAccountDBPojo.setPassword3( myAccountDBPojo.getPassword2() );
                myAccountDBPojo.setPassword2( myAccountDBPojo.getPassword1() );
                myAccountDBPojo.setPassword1( myAccountDBPojo.getPassword() );
                myAccountDBPojo.setPassword( newPassword );
                myAccountDBPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                myAccountDBPojo.setLastModifiedBy( custMyAccountPojo.getCustomerId() );
                myAccountDBPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                session.merge( myAccountDBPojo );
                transaction.commit();
                custMyAccountPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM802;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while change Password", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while change Password", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        return crmServiceCode;
    }

    @Override
    public CRMServiceCode resetPassword( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            criteria.add( Restrictions.eq( "customerId", inCustomerDetailsPojo.getCustomerId() ) );
            criteria.add( Restrictions.eq( "rmn", inCustomerDetailsPojo.getRmn() ) );
            List<String> custStatus = new ArrayList<String>();
            custStatus.add( CRMStatusCode.ACTIVE.getStatusCode() );
            custStatus.add( CRMStatusCode.BARRED.getStatusCode() );
            custStatus.add( CRMStatusCode.TDC.getStatusCode() );
            criteria.add( Restrictions.isNotNull( "activationDate" ) );
            criteria.add( Restrictions.in( "status", custStatus ) );
            List<CrmCustomerDetailsPojo> custDetailDBPojos = criteria.list();
            LOGGER.info( "customerDetailDBPojos size " + custDetailDBPojos.size() );
            if ( CommonValidator.isValidCollection( custDetailDBPojos ) && custDetailDBPojos.size() == 1 )
            {
                criteria = session.createCriteria( CrmCustMyAccountPojo.class );
                criteria.add( Restrictions.eq( "customerId", inCustomerDetailsPojo.getCustomerId() ) );
                criteria.add( Restrictions.ne( "status", CRMStatusCode.INACTIVE.getStatusCode() ) );
                List<CrmCustMyAccountPojo> myAccountPojos = criteria.list();
                if ( CommonValidator.isValidCollection( myAccountPojos ) && myAccountPojos.size() == 1 )
                {
                    CrmCustMyAccountPojo myAccountPojo = myAccountPojos.get( 0 );
                    if ( StringUtils.isValidObj( myAccountPojo ) )
                    {
                        String password = CRMServiceUtils.getMyAccountRandomPassword();
                        myAccountPojo.setPassword3( myAccountPojo.getPassword2() );
                        myAccountPojo.setPassword2( myAccountPojo.getPassword1() );
                        myAccountPojo.setPassword1( myAccountPojo.getPassword() );
                        myAccountPojo.setPassword( EncryptionUtil.encrypt( password ) );
                        myAccountPojo.setStatus( CRMStatusCode.NEW.getStatusCode() );
                        myAccountPojo.setLastModifiedBy( custDetailDBPojos.get( 0 ).getCustomerId() );
                        myAccountPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        session.merge( myAccountPojo );
                        transaction.commit();
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    else
                    {
                        crmServiceCode = CRMServiceCode.CRM997;
                    }
                }
            }
            else
            {
                LOGGER.info( "No record found" );
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while reset Password", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while reset Password", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        return crmServiceCode;
    }
}
