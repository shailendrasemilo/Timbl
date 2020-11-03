package com.np.tele.crm.paymentGateway.dao;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.pojos.CrmPgwTransactionsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;

public class PaymentGatewayDaoImpl
    implements IPaymentGatewayDao
{
    private static final Logger LOGGER = Logger.getLogger( PaymentGatewayDaoImpl.class );

    @Override
    public CRMServiceCode generateTrackId( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "Inside generateTrackId" );
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        CrmPgwTransactionsPojo pgwTransactionsPojo = inCrmFinanceDto.getCrmPgwTransactionsPojo();
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Long recordId = (Long) session.save( pgwTransactionsPojo );
            if ( recordId > 0 )
            {
                LOGGER.info( "pgw transaction recordID:: " + pgwTransactionsPojo.getRecordId() + " rid:: " + recordId );
                transaction.commit();
                CRMServiceUtils.closeSession( session );
                session = HibernateUtil.getSessionFactory().openSession();
                CrmPgwTransactionsPojo transactionsPojoDb = (CrmPgwTransactionsPojo) session
                        .get( CrmPgwTransactionsPojo.class, recordId );
                LOGGER.info( "pgwTrackId :::: " + transactionsPojoDb.getPgwTrackId() );
                pgwTransactionsPojo.setPgwTrackId( transactionsPojoDb.getPgwTrackId() );
                inCrmFinanceDto.setCrmPgwTransactionsPojo( pgwTransactionsPojo );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while generating track id:::: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while generating track id:::: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmPgwTransactionsPojo.class.getName(), null, null );
            }
        }
        return crmServiceCode;
    }

    //  pgwTransactionsPojoDb.setPgwPaymentPage( inCrmFinanceDto.getCrmPgwTransactionsPojo().getPgwPaymentPage() );
    //            pgwTransactionsPojoDb.setPgwPaymentId( inCrmFinanceDto.getCrmPgwTransactionsPojo().getPgwPaymentId() );
    //            pgwTransactionsPojoDb.setPgwAuthDatetime( authTime );
    //            session.update( pgwTransactionsPojoDb );
    @Override
    public CrmFinanceDto updatePgwAuth( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "in updatePgwAuthTime" );
        Session session = null;
        Transaction transaction = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Date authTime = Calendar.getInstance().getTime();
            inCrmFinanceDto.getCrmPgwTransactionsPojo().setPgwAuthDatetime( authTime );
            session.update( inCrmFinanceDto.getCrmPgwTransactionsPojo() );
            transaction.commit();
            transaction = null;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "error in updatePgwAuth:::: " + ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        return inCrmFinanceDto;
    }

    @Override
    public CRMServiceCode saveTransactionDetails( CrmFinanceDto inCrmFinanceDto )
    {
        LOGGER.info( "in saveTransactionDetails" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        Session session = null;
        Transaction transaction = null;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update( inCrmFinanceDto.getCrmPgwTransactionsPojo() );
            transaction.commit();
            serviceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmPgwTransactionsPojo.class.getName(), null, null );
            }
        }
        return serviceCode;
    }

    public static void main( String[] s )
    {
        CrmPgwTransactionsPojo transactionsPojo = new CrmPgwTransactionsPojo();
        transactionsPojo.setAmount( 200.25 );
        transactionsPojo.setCustomerId( "65413782" );
        transactionsPojo.setPgwName( "HDFC" );
        transactionsPojo.setPaymentOption( "CC" );
        //        transactionsPojo.setRecordId( 6 );
        //        transactionsPojo.setPgwTrackId( "65413782T6" );
        //        transactionsPojo.setPgwTransactionId( "12321" );
        Session session = null;
        Long recId = 0l;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            recId = (Long) session.save( transactionsPojo );
            //            session.update( transactionsPojo );
            transaction.commit();
            session = HibernateUtil.getSessionFactory().openSession();
            CrmPgwTransactionsPojo transactionsPojoDb = (CrmPgwTransactionsPojo) session
                    .get( CrmPgwTransactionsPojo.class, recId );
            System.out.println( transactionsPojoDb );
            transactionsPojo.setPgwTrackId( transactionsPojoDb.getPgwTrackId() );
        }
        catch ( HibernateException ex )
        {
            ex.printStackTrace();
        }
        CRMServiceUtils.closeSession( session );
        System.out.println( recId + " recid in pojo:: " + transactionsPojo.getRecordId() + " track id:: "
                + transactionsPojo.getPgwTrackId() );
        CRMServiceUtils.closeSession( session );
    }
}
