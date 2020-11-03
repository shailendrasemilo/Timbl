package com.np.tele.crm.billing.dao;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.pojos.CrmBillingTransactionsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;

public class CRMBillingTransactionDao
    implements ICRMBillingTransactionDao
{
    private static final Logger LOGGER = Logger.getLogger( CRMBillingTransactionDao.class );

    /*@Override
    public boolean saveBillingEventData( CrmBillingEventsCalling inBillingEventsCalling )
    {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        boolean flag = false;
        inBillingEventsCalling.setCreatedDatetime( Calendar.getInstance().getTime() );
        JAXBContext context = null;
        String xmlStringData = null;
        try
        {
            context = JAXBContext.newInstance( CrmBillingEventsCalling.class );
            xmlStringData = JAXBConverter.pojo2Xml( inBillingEventsCalling, context );
            LOGGER.info( "\nPOJO to XML conversion(Marshal) demonstration in Java" );
            LOGGER.info( xmlStringData );
            inBillingEventsCalling.setEventData( xmlStringData );
            session.save( inBillingEventsCalling );
            transaction.commit();
            flag = true;
            System.out.println( "\n XML to POJO  conversion(Marshal) demonstration in Java" );
            CrmBillingEventsCalling cbn = (CrmBillingEventsCalling) JAXBConverter.xml2Pojo( xmlStringData, context );
            System.out.println( cbn );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Save Billing event Data ", ex );
            flag = false;
            if ( StringUtils.isValidObj( transaction ) )
            {
                LOGGER.info( "Going To Rollback Transection" );
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return flag;
    }*/
    @Override
    public ConfigDto saveTransactionPOJO( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside CRMBillingTransactionDao : saveTransactionPOJO( CrmBillingTransactionsPojo inCrmBillingTransactionsPojo )" );
        CrmBillingTransactionsPojo transactionPOJO = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Long transactionID = null;
        try
        {
            transactionPOJO = inConfigDto.getBillingTransactionsPojo();
            if ( StringUtils.isValidObj( transactionPOJO ) )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                if ( transactionPOJO.getTransId() > 0 )
                {
                    transactionPOJO.setLastModifiedTime( Calendar.getInstance().getTime() );
                    session.update( transactionPOJO );
                }
                else
                {
                    transactionPOJO.setCreatedTime( Calendar.getInstance().getTime() );
                    transactionID = (Long) session.save( transactionPOJO );
                    LOGGER.info( "Generated Transaction ID : " + transactionID );
                    transactionPOJO.setTransId( transactionID );
                }
                transaction.commit();
            }
        }
        catch ( Exception ex )
        {
            transactionPOJO = null;
            LOGGER.error( "Error In Save Billing event Data ", ex );
            if ( StringUtils.isValidObj( transaction ) )
            {
                LOGGER.info( "Going To Rollback Transection" );
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Return CrmBillingTransactionsPojo : " + transactionPOJO );
        return inConfigDto;
    }
}
