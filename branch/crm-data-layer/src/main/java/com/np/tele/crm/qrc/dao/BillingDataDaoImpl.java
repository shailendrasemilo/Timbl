package com.np.tele.crm.qrc.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ext.pojos.CustomerUsageDetailsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.HibernateSQLServerUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class BillingDataDaoImpl
    implements IBillingDataDao
{
    private static final Logger LOGGER = Logger.getLogger( BillingDataDaoImpl.class );

    @Override
    public CRMServiceCode getCustomerUsageDetails( String inUsageFromDate,
                                                   String inUsageToDate,
                                                   String inCustomerId,
                                                   List<CustomerUsageDetailsPojo> customerUsageDetailsPojos )
    {
        LOGGER.info( "Inside BillingDataDaoImpl : getCustomerUsageDetails" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateSQLServerUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CustomerUsageDetailsPojo.class );
            if ( StringUtils.isNotBlank( inUsageFromDate ) )
            {
                criteria.add( Restrictions.ge( "startTime",
                                               DateUtils.getDate( inUsageFromDate,
                                                                  IDateConstants.FORMAT_DD_MMM_YYYY_HH_MM_SS )
                                                       .getTime() ) );
            }
            if ( StringUtils.isNotBlank( inUsageToDate ) )
            {
                criteria.add( Restrictions.le( "startTime",
                                               DateUtils.getDate( inUsageToDate,
                                                                  IDateConstants.FORMAT_DD_MMM_YYYY_HH_MM_SS )
                                                       .getTime() ) );
            }
            if ( StringUtils.isNotBlank( inCustomerId ) )
            {
                criteria.add( Restrictions.eq( "customerNo", StringUtils.numericValue( inCustomerId ) ) );
            }
            if ( StringUtils.isValidObj( customerUsageDetailsPojos ) )
            {
                customerUsageDetailsPojos.clear();
                customerUsageDetailsPojos.addAll( criteria.list() );
            }
            if ( CommonValidator.isValidCollection( customerUsageDetailsPojos ) )
            {
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM996;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while retreiving Customer Usage Details List ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmServiceCode;
    }

    @Override
    public CRMServiceCode getCustUsageDetails( String inUsageFromDate,
                                               String inUsageToDate,
                                               String inCustomerId,
                                               List<CustomerUsageDetailsPojo> inCustomerUsageDetailsPojos )
    {
        LOGGER.info( "Inside BillingDataDaoImpl : getCustUsageDetails" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Query query = null;
        SessionFactory sessionFactory = null;
        try
        {
            sessionFactory = HibernateSQLServerUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "CUST_USAGE_DETAILS" );
            if ( StringUtils.isNotBlank( inUsageFromDate ) && StringUtils.isNotBlank( inUsageToDate ) )
            {
                String formDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS
                        .parse( inUsageFromDate ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) );
                String toDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS
                        .parse( inUsageToDate ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) );
                LOGGER.info( "from Date..." + formDate );
                LOGGER.info( "To Date....." + toDate );
                query.setString( "FROM_DATE", formDate );
                query.setString( "TO_DATE", toDate );
            }
            if ( StringUtils.isNotBlank( inCustomerId ) )
            {
                query.setString( "CUSTOMER_ID", inCustomerId );
            }
            query.setResultTransformer( Transformers.aliasToBean( CustomerUsageDetailsPojo.class ) );
            if ( StringUtils.isValidObj( inCustomerUsageDetailsPojos ) )
            {
                inCustomerUsageDetailsPojos.clear();
                inCustomerUsageDetailsPojos.addAll( query.list() );
            }
            if ( CommonValidator.isValidCollection( inCustomerUsageDetailsPojos ) )
            {
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM996;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while retreiving data from Customer Usage Details ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmServiceCode;
    }

    @Override
    public CrmQrcDto getCustUsageDetailsInGB( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside BillingDataDaoImpl : getCustUsageDetailsInGB" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        Session session = null;
        Query query = null;
        SessionFactory sessionFactory = null;
        try
        {
            sessionFactory = HibernateSQLServerUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inCrmQrcDto ) )
            {
                query = session.getNamedQuery( "CUST_USAGE_DETAILS_IN_GB" );
                inCrmQrcDto.setCustUsageDetailsPojos( new ArrayList<CustomerUsageDetailsPojo>() );
                if ( StringUtils.isNotBlank( inCrmQrcDto.getUsageFormDate() )
                        && StringUtils.isNotBlank( inCrmQrcDto.getUsageToDate() ) )
                {
                    String formDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS
                            .parse( inCrmQrcDto.getUsageFormDate() ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) );
                    String toDate = DateUtils.getFormattedDate( IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS
                            .parse( inCrmQrcDto.getUsageToDate() ), new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ) );
                    LOGGER.info( "from Date..." + formDate );
                    LOGGER.info( "To Date....." + toDate );
                    query.setString( "FROM_DATE", formDate );
                    query.setString( "TO_DATE", toDate );
                }
                if ( StringUtils.isNotBlank( inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() ) )
                {
                    query.setString( "CUSTOMER_ID", inCrmQrcDto.getCustomerDetailsPojo().getCustomerId() );
                }
                query.setResultTransformer( Transformers.aliasToBean( CustomerUsageDetailsPojo.class ) );
                if ( StringUtils.isValidObj( inCrmQrcDto.getCustUsageDetailsPojos() ) )
                {
                    inCrmQrcDto.getCustUsageDetailsPojos().clear();
                    inCrmQrcDto.getCustUsageDetailsPojos().addAll( query.list() );
                }
                if ( CommonValidator.isValidCollection( inCrmQrcDto.getCustUsageDetailsPojos() ) )
                {
                    LOGGER.info( "Usage Details Size for GB : " + inCrmQrcDto.getCustUsageDetailsPojos().size() );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM996;
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while retreiving data from Customer Usage Details in GB", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmQrcDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmQrcDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmQrcDto;
    }
}
