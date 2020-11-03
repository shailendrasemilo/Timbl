package com.np.tele.crm.report.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ReportEnum;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.dto.ReportDto;
import com.np.tele.crm.ext.pojos.LmsReportPojo;
import com.np.tele.crm.ext.pojos.PaymentReportPojo;
import com.np.tele.crm.ext.pojos.QrcTicketReportPojo;
import com.np.tele.crm.ext.pojos.RfsReportPojo;
import com.np.tele.crm.ext.pojos.SlaReportPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmCustWaiverPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmMassOutageMastersPojo;
import com.np.tele.crm.pojos.CrmMassOutagePartnerPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateReportUtil;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StopWatch;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class QRCReportDaoImpl
    implements IQRCReportDao
{
    private static final Logger LOGGER = Logger.getLogger( QRCReportDaoImpl.class );

    @Override
    public ReportDto listAdjustmentReports( ReportDto inReportDto )
    {
        LOGGER.info( "QRCReportDaoImpl::listAdjustmentReports" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ADJUSTMENT_RPT" );
            String fromDateStr = inReportDto.getFromDate();
            String toDateStr = inReportDto.getToDate();
            fromDateStr += IAppConstants.START_TIME;
            toDateStr += IAppConstants.END_TIME;
            LOGGER.info( "from Date:::;" + fromDateStr );
            LOGGER.info( "To Date:::;" + toDateStr );
            Date fromDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr );
            Date toDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr );
            query.setTimestamp( "FROM_DATE", fromDate );
            query.setTimestamp( "TO_DATE", toDate );
            query.setResultTransformer( Transformers.aliasToBean( CrmCustWaiverPojo.class ) );
            List<CrmCustWaiverPojo> crmCustWaiverPojos = query.list();
            if ( CommonValidator.isValidCollection( crmCustWaiverPojos ) )
            {
                inReportDto.setCrmCustWaiverPojos( crmCustWaiverPojos );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching adjustments reports: " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }

    @Override
    public ReportDto listMigrationReports( ReportDto inReportDto )
    {
        LOGGER.info( "QRCReportDaoImpl::listMigrationReports" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( ReportEnum.MIGRATION_REPORTS.getReportName() );
            String fromDateStr = inReportDto.getFromDate();
            String toDateStr = inReportDto.getToDate();
            fromDateStr += IAppConstants.START_TIME;
            toDateStr += IAppConstants.END_TIME;
            LOGGER.info( "from Date:::;" + fromDateStr );
            LOGGER.info( "To Date:::;" + toDateStr );
            Date fromDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr );
            Date toDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr );
            query.setTimestamp( "FROM_DATE", fromDate );
            query.setTimestamp( "TO_DATE", toDate );
            query.setString( "ACTION1", CRMCustomerActivityActions.ADDON_PLAN_CHANGE.getActionCode() );
            query.setString( "ACTION2", CRMCustomerActivityActions.BASE_PLAN_MIGRATION.getActionCode() );
            query.setString( "ACTION3", CRMCustomerActivityActions.PLAN_RENEW_BASE.getActionCode() );
            query.setString( "ACTION4", CRMCustomerActivityActions.PLAN_REACTIVATION_BASE.getActionCode() );
            query.setString( "ACTION5", CRMCustomerActivityActions.ADDON_PLAN_REMOVAL.getActionCode() );
            if ( StringUtils.isNotBlank( inReportDto.getServiceType() ) )
            {
                query.setString( "STYPE1", inReportDto.getServiceType() );
                query.setString( "STYPE2", inReportDto.getServiceType() );
            }
            else
            {
                query.setString( "STYPE1", CRMDisplayListConstants.POST_PAID.getCode() );
                query.setString( "STYPE2", CRMDisplayListConstants.PRE_PAID.getCode() );
            }
            if ( StringUtils.isNotBlank( inReportDto.getProductType() ) )
            {
                query.setString( "SNAME1", inReportDto.getProductType() );
                query.setString( "SNAME2", inReportDto.getProductType() );
                query.setString( "SNAME3", inReportDto.getProductType() );
            }
            else
            {
                query.setString( "SNAME1", CRMDisplayListConstants.BROADBAND.getCode() );
                query.setString( "SNAME2", CRMDisplayListConstants.RADIO_FREQUENCY.getCode() );
                query.setString( "SNAME3", CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() );
            }
            query.setResultTransformer( Transformers.aliasToBean( CrmCustomerActivityPojo.class ) );
            List<CrmCustomerActivityPojo> crmCustomerActivityPojos = query.list();
            if ( CommonValidator.isValidCollection( crmCustomerActivityPojos ) )
            {
                inReportDto.setCrmCustomerActivityPojos( crmCustomerActivityPojos );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while migration adjustments reports: " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }

    @Override
    public ReportDto ValidityExtensionHistory( ReportDto inReportDto, String inQuery )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            String fromDateStr = inReportDto.getFromDate();
            String toDateStr = inReportDto.getToDate();
            fromDateStr += IAppConstants.START_TIME;
            toDateStr += IAppConstants.END_TIME;
            LOGGER.info( "from Date:::;" + fromDateStr );
            LOGGER.info( "To Date:::;" + toDateStr );
            query = session.getNamedQuery( inQuery );
            query.setString( "action", CRMCustomerActivityActions.VALIDITY_EXTENSION.getActionCode() );
            query.setTimestamp( "fromDate", IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr ) );
            query.setTimestamp( "toDate", IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr ) );
            query.setResultTransformer( Transformers.aliasToBean( QrcTicketReportPojo.class ) );
            List<QrcTicketReportPojo> pojos = query.list();
            LOGGER.info( "List of Pojo size ::" + pojos.size() );
            inReportDto.setQrcReportPojos( pojos );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while generating  customerIdList reports: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }

    @Override
    public ReportDto getMassOutageReport( ReportDto inReportDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmMassOutagePojo> crmMassOutagePojos = null;
        Set<String> activeMastersList = null;
        CrmMassOutageDto massOutageDto = null;
        if ( StringUtils.isValidObj( inReportDto ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                String fromDateStr = inReportDto.getFromDate();
                String toDateStr = inReportDto.getToDate();
                criteria = session.createCriteria( CrmMassOutagePojo.class );
                if ( StringUtils.isNotBlank( fromDateStr ) && StringUtils.isNotBlank( toDateStr ) )
                {
                    fromDateStr += IAppConstants.START_TIME;
                    toDateStr += IAppConstants.END_TIME;
                    SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
                    Date fromDate = formatter.parse( fromDateStr );
                    Date toDate = formatter.parse( toDateStr );
                    LOGGER.info( "After Formatting: From Date: " + fromDate + " and To Date: " + toDate );
                    criteria.add( Restrictions.between( "outageStartTime", fromDate, toDate ) );
                    criteria.addOrder( Order.desc( "outageStartTime" ) );
                }
                if ( StringUtils.isNotBlank( inReportDto.getCrmMassOutagePojo().getServiceName() ) )
                {
                    criteria.add( Restrictions.eq( "serviceName", inReportDto.getCrmMassOutagePojo().getServiceName() ) );
                }
                if ( StringUtils.isNotBlank( inReportDto.getCrmMassOutagePojo().getOutageType() ) )
                {
                    criteria.add( Restrictions.eq( "outageType", inReportDto.getCrmMassOutagePojo().getOutageType() ) );
                }
                if ( StringUtils.isNotBlank( inReportDto.getCrmMassOutagePojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inReportDto.getCrmMassOutagePojo().getStatus() ) );
                }
                crmMassOutagePojos = criteria.list();
                if ( CommonValidator.isValidCollection( crmMassOutagePojos ) )
                {
                    for ( CrmMassOutagePojo outagePojo : crmMassOutagePojos )
                    {
                        criteria = session.createCriteria( CrmMassOutagePartnerPojo.class );
                        criteria.add( Restrictions.eq( "outageId", outagePojo.getOutageId() ) );
                        List<CrmMassOutagePartnerPojo> crmMassOutagePartnerPojos = criteria.list();
                        Set<CrmMassOutagePartnerPojo> cPartnerPojos = new HashSet<CrmMassOutagePartnerPojo>( crmMassOutagePartnerPojos );
                        outagePojo.setCrmMassOutagePartners( cPartnerPojos );
                        //Search For Master names
                        criteria = session.createCriteria( CrmMassOutageMastersPojo.class );
                        criteria.add( Restrictions.eq( "outageId", outagePojo.getOutageId() ) );
                        List<CrmMassOutageMastersPojo> crmMassOutageMastersPojos = criteria.list();
                        Set<CrmMassOutageMastersPojo> massOutageMastersPojos = new HashSet<CrmMassOutageMastersPojo>( crmMassOutageMastersPojos );
                        outagePojo.setCrmMassOutageMastersPojos( massOutageMastersPojos );
                        LOGGER.info( "SIZE ::" + massOutageMastersPojos.size() );
                        //Search for Users names
                        if ( CommonValidator.isValidCollection( massOutageMastersPojos ) )
                        {
                            activeMastersList = new HashSet<String>();
                            for ( CrmMassOutageMastersPojo crmMassOutageMastersPojo : massOutageMastersPojos )
                            {
                                LOGGER.info( "Masters Name::" + crmMassOutageMastersPojo.getMasterName() );
                                activeMastersList.add( crmMassOutageMastersPojo.getMasterName() );
                            }
                        }
                        if ( CommonValidator.isValidCollection( activeMastersList ) )
                        {
                            massOutageDto = new CrmMassOutageDto();
                            massOutageDto.setMasterList( new ArrayList<String>( activeMastersList ) );
                            massOutageDto = CRMServicesProxy
                                    .getInstance()
                                    .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                    .massOutageOperations( ServiceParameter.CUSTOMER_LIST.getParameter(), massOutageDto );
                            if ( StringUtils.equals( massOutageDto.getStatusCode(),
                                                     CRMServiceCode.CRM001.getStatusCode() )
                                    && CommonValidator.isValidCollection( massOutageDto.getCustomerList() ) )
                            {
                                LOGGER.info( "Size of Customer List ::" + massOutageDto.getCustomerList().size() );
                                /*if ( massOutageDto.getCustomerList().size() == 0 )
                                {
                                    outagePojo.setCustomerCount( massOutageDto.getCustomerList().size() + 1 );
                                }
                                else*/
                                outagePojo.setCustomerCount( massOutageDto.getCustomerList().size() );
                            }
                        }
                    }
                }
                /*if ( CommonValidator.isValidCollection( crmMassOutagePojos ) )
                {
                    CrmMassOutagePojo outagePojo = null;
                    for ( CrmMassOutagePojo crmMassOutagePojo : crmMassOutagePojos )
                    {
                        criteria = session.createCriteria( CrmMassOutagePartnerPojo.class );
                        criteria.add( Restrictions.eq( "outageId", crmMassOutagePojo.getOutageId() ) );
                        List<CrmMassOutagePartnerPojo> crmMassOutagePartnerPojos = criteria.list();
                        if ( CommonValidator.isValidCollection( crmMassOutagePartnerPojos ) )
                        {
                            for ( CrmMassOutagePartnerPojo outagePartnerPojo : crmMassOutagePartnerPojos )
                            {
                                if ( CommonValidator.isValidCollection( outagePartnerPojo.getCrmMassOutageMasterses() ) )
                                {
                                    for ( CrmMassOutageMastersPojo masterPojo : outagePartnerPojo
                                            .getCrmMassOutageMasterses() )
                                    {
                                        if ( masterPojo.isStartPoint() )
                                        {
                                            outagePojo = BeanUtils.getTemporaryPojo( crmMassOutagePojo );
                                            outagePojo.setOutageLevel( masterPojo.getMasterName() );
                                            records.add( outagePojo );
                                            break;
                                        }
                                    }
                                }
                                else
                                {
                                    for ( CrmMassOutageStatePojo statePojo : outagePartnerPojo.getCrmMassOutageStates() )
                                    {
                                        if ( statePojo.isStartPoint() )
                                        {
                                            outagePojo = BeanUtils.getTemporaryPojo( crmMassOutagePojo );
                                            outagePojo.setOutageLevel( statePojo.getStateName() );
                                            records.add( outagePojo );
                                            break;
                                        }
                                        if ( CommonValidator.isValidCollection( statePojo.getCrmMassOutageCities() ) )
                                        {
                                            for ( CrmMassOutageCityPojo cityPojo : statePojo.getCrmMassOutageCities() )
                                            {
                                                if ( cityPojo.isStartPoint() )
                                                {
                                                    outagePojo = BeanUtils.getTemporaryPojo( crmMassOutagePojo );
                                                    outagePojo.setOutageLevel( cityPojo.getCityName() );
                                                    records.add( outagePojo );
                                                    break;
                                                }
                                                if ( CommonValidator.isValidCollection( cityPojo
                                                        .getCrmMassOutageAreas() ) )
                                                {
                                                    for ( CrmMassOutageAreaPojo areaPojo : cityPojo
                                                            .getCrmMassOutageAreas() )
                                                    {
                                                        if ( areaPojo.isStartPoint() )
                                                        {
                                                            outagePojo = BeanUtils.getTemporaryPojo( crmMassOutagePojo );
                                                            outagePojo.setOutageLevel( areaPojo.getArea() );
                                                            records.add( outagePojo );
                                                            break;
                                                        }
                                                        if ( CommonValidator.isValidCollection( areaPojo
                                                                .getCrmMassOutageLocalities() ) )
                                                        {
                                                            for ( CrmMassOutageLocalityPojo localitiespojo : areaPojo
                                                                    .getCrmMassOutageLocalities() )
                                                            {
                                                                if ( localitiespojo.isStartPoint() )
                                                                {
                                                                    outagePojo = BeanUtils
                                                                            .getTemporaryPojo( crmMassOutagePojo );
                                                                    outagePojo.setOutageLevel( localitiespojo
                                                                            .getLocality() );
                                                                    records.add( outagePojo );
                                                                    break;
                                                                }
                                                                if ( CommonValidator.isValidCollection( localitiespojo
                                                                        .getCrmMassOutageSocieties() ) )
                                                                {
                                                                    for ( CrmMassOutageSocietyPojo societyPojo : localitiespojo
                                                                            .getCrmMassOutageSocieties() )
                                                                    {
                                                                        if ( societyPojo.isStartPoint() )
                                                                        {
                                                                            outagePojo = BeanUtils
                                                                                    .getTemporaryPojo( crmMassOutagePojo );
                                                                            outagePojo.setOutageLevel( societyPojo
                                                                                    .getSociety() );
                                                                            records.add( outagePojo );
                                                                            break;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }*/
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while getMassOutageReport:", ex );
            }
            finally
            {
                if ( CommonValidator.isValidCollection( crmMassOutagePojos ) )
                {
                    LOGGER.info( "Size of MassOutagePojo::" + crmMassOutagePojos.size() );
                    inReportDto.setCrmMassOutagePojos( crmMassOutagePojos );
                }
                CRMServiceUtils.closeSession( session );
            }
        }
        return inReportDto;
    }

    @Override
    public ReportDto getTicketReport( ReportDto inReportDto, String inNamedQuery )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            if ( StringUtils.isValidObj( inReportDto.getFromDate() )
                    && StringUtils.isValidObj( inReportDto.getToDate() ) )
            {
                String fromDateStr = inReportDto.getFromDate();
                String toDateStr = inReportDto.getToDate();
                fromDateStr += IAppConstants.START_TIME;
                toDateStr += IAppConstants.END_TIME;
                LOGGER.info( "from Date:::;" + fromDateStr );
                LOGGER.info( "To Date:::;" + toDateStr );
                Date fromDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr );
                Date toDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr );
                query.setTimestamp( "FROM_DATE", fromDate );
                query.setTimestamp( "TO_DATE", toDate );
            }
            if ( StringUtils.isValidObj( inReportDto.getQrcTicketReportPojo() ) )
            {
                if ( StringUtils.isNotBlank( inReportDto.getQrcTicketReportPojo().getQrcType() ) )
                {
                    query.setString( "QTYPE", inReportDto.getQrcTicketReportPojo().getQrcType() );
                }
                else
                {
                    query.setString( "QTYPE", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getQrcTicketReportPojo().getCategory() ) )
                {
                    query.setString( "CATGRY", inReportDto.getQrcTicketReportPojo().getCategory() );
                }
                else
                {
                    query.setString( "CATGRY", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getQrcTicketReportPojo().getSubCategory() ) )
                {
                    query.setString( "SUBCATGRY", inReportDto.getQrcTicketReportPojo().getSubCategory() );
                }
                else
                {
                    query.setString( "SUBCATGRY", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getQrcTicketReportPojo().getSubSubCategory() ) )
                {
                    query.setString( "SUBSUBCATGRY", inReportDto.getQrcTicketReportPojo().getSubSubCategory() );
                }
                else
                {
                    query.setString( "SUBSUBCATGRY", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getQrcTicketReportPojo().getBinName() )
                        && !StringUtils.equals( inReportDto.getQrcTicketReportPojo().getBinName(), "0" ) )
                {
                    query.setString( "CURRENTBIN", inReportDto.getQrcTicketReportPojo().getBinName() );
                }
                else
                {
                    query.setString( "CURRENTBIN", null );
                }
                if ( StringUtils.equals( inNamedQuery, ReportEnum.OPEN_TICKET_REPORT.getReportName() ) )
                {
                    if ( StringUtils.isValidObj( inReportDto.getQrcTicketReportPojo().getStatusOfTicket() ) )
                    {
                        if ( StringUtils.equals( inReportDto.getQrcTicketReportPojo().getStatusOfTicket(), "O" ) )
                        {
                            query.setString( "TKTOPENSTATUS", inReportDto.getQrcTicketReportPojo().getStatusOfTicket() );
                            query.setString( "TKTREOPENSTATUS", null );
                        }
                        else
                        {
                            query.setString( "TKTOPENSTATUS", null );
                            query.setString( "TKTREOPENSTATUS", inReportDto.getQrcTicketReportPojo()
                                    .getStatusOfTicket() );
                        }
                    }
                    else
                    {
                        query.setString( "TKTOPENSTATUS", "O" );
                        query.setString( "TKTREOPENSTATUS", "RO" );
                    }
                }
            }
            query.setResultTransformer( Transformers.aliasToBean( QrcTicketReportPojo.class ) );
            inReportDto.setQrcReportPojos( query.list() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "unable to retrieve report data: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }

    /*public static void main( String[] args )
    {
        ReportDto rep = new ReportDto();
        rep.setToDate( "8-jun-2015" );
        rep.setFromDate( "1-jun-2015" );
        QRCReportDaoImpl impl = new QRCReportDaoImpl();
        impl.ValidityExtensionHistory( rep );
    }*/
    @Override
    public ReportDto getLMSReport( ReportDto inReportDto, String inNamedQuery )
    {
        LOGGER.info( "Lms Report generation start time ::" + System.currentTimeMillis() );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Query subQuery = null;
        List<LmsReportPojo> lmsQueryPojos = null;
        List<LmsReportPojo> lmsSubQueryPojos = null;
        try
        {
            StopWatch sw = new StopWatch( inNamedQuery );
            sw.start();
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            subQuery = session.getNamedQuery( ReportEnum.LMS_RPT_SUBQUERY.getReportName() );
            String fromDateStr = inReportDto.getFromDate();
            String toDateStr = inReportDto.getToDate();
            fromDateStr += IAppConstants.START_TIME;
            toDateStr += IAppConstants.END_TIME;
            LOGGER.info( "from Date:::;" + fromDateStr );
            LOGGER.info( "To Date:::;" + toDateStr );
            Date fromDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr );
            Date toDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr );
            query.setTimestamp( "FROM_DATE", fromDate );
            query.setTimestamp( "TO_DATE", toDate );
            query.setResultTransformer( Transformers.aliasToBean( LmsReportPojo.class ) );
            subQuery.setTimestamp( "FROM_DATE", fromDate );
            subQuery.setTimestamp( "TO_DATE", toDate );
            subQuery.setResultTransformer( Transformers.aliasToBean( LmsReportPojo.class ) );
            lmsQueryPojos = query.list();
            lmsSubQueryPojos = subQuery.list();
            //inReportDto.setLmsReportPojos( query.list() );0
            if ( CommonValidator.isValidCollection( lmsSubQueryPojos ) )
            {
                LOGGER.info( "lmsSubQueryPojos Size::" + lmsSubQueryPojos.size() );
                LOGGER.info( "lmsQueryPojos Size::" + lmsQueryPojos.size() );
                for ( LmsReportPojo lmsSubReportPojo : lmsSubQueryPojos )
                {
                    LOGGER.info( "LMS ID:" + lmsSubReportPojo.getLmsId() + ",Created Time :"
                            + lmsSubReportPojo.getLeadForwardedOn() );
                    for ( LmsReportPojo lmsReportPojo : lmsQueryPojos )
                    {
                        if ( StringUtils.equals( lmsSubReportPojo.getLmsId(), lmsReportPojo.getLmsId() ) )
                        {
                            lmsReportPojo.setLeadForwardedOn( lmsSubReportPojo.getLeadForwardedOn() );
                            break;
                            // LOGGER.info( "LeadForwardedOn::" + lmsReportPojo.getLeadForwardedOn() );
                        }
                    }
                }
            }
            if ( CommonValidator.isValidCollection( lmsQueryPojos ) )
            {
                LOGGER.info( "lmsQueryPojos Size::" + lmsQueryPojos.size() );
                for ( LmsReportPojo lmsReportPojo : lmsQueryPojos )
                {
                    LOGGER.info( "Lead ID:: " + lmsReportPojo.getLmsId() );
                    Criteria criteria = session.createCriteria( RemarksPojo.class );
                    criteria.add( Restrictions.eq( "mappingId", "L" + lmsReportPojo.getLmsId() ) );
                    criteria.addOrder( Order.desc( "createdTime" ) );
                    List<RemarksPojo> list = criteria.list();
                    if ( CommonValidator.isValidCollection( list ) && list.size() > 2 )
                    {
                        SortingComparator<RemarksPojo> sortComparator = new SortingComparator<RemarksPojo>( "createdTime" );
                        Collections.sort( list, sortComparator );
                        sortComparator = null;
                        //  int i = list.size();
                        LOGGER.info( "List Size::" + list.size() );
                        lmsReportPojo.setSecondLastRemarks( list.get( ( list.size() - list.size() + 1 ) ).getRemarks() );
                        lmsReportPojo.setThirdOneRemarks( list.get( ( list.size() - list.size() ) + 2 ).getRemarks() );
                        lmsReportPojo.setSecondLastRemarksTime( list.get( ( list.size() - list.size() + 1 ) )
                                .getCreatedTime() );
                        lmsReportPojo.setThirdOneRemarksTime( list.get( ( list.size() - list.size() + 2 ) )
                                .getCreatedTime() );
                    }
                }
            }
            inReportDto.setLmsReportPojos( lmsQueryPojos );
            sw.stop();
            LOGGER.info( "Stop Watch ::" + sw );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "unable to retrieve lms report data: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Lms Report generation end time ::" + System.currentTimeMillis() );
        return inReportDto;
    }

    @Override
    public ReportDto getRFSReport( ReportDto inReportDto, String inNamedQuery )
    {
        LOGGER.info( "Rfs Report generation start time ::" + System.currentTimeMillis() );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            if ( StringUtils.isNotBlank( inReportDto.getStateName() )
                    && StringUtils.numericValue( inReportDto.getStateName() ) > 0 )
            {
                LOGGER.info( "" + inReportDto.getStateName() );
                query.setString( "STATE", inReportDto.getStateName() );
            }
            else
            {
                query.setString( "STATE", null );
            }
            if ( StringUtils.isNotBlank( inReportDto.getCityName() ) )
            {
                LOGGER.info( inReportDto.getCityName() );
                query.setString( "CITY", inReportDto.getCityName() );
            }
            else
            {
                query.setString( "CITY", null );
            }
            if ( StringUtils.isNotBlank( inReportDto.getAreaName() ) )
            {
                LOGGER.info( inReportDto.getAreaName() );
                query.setString( "AREA", inReportDto.getAreaName() );
            }
            else
            {
                query.setString( "AREA", null );
            }
            if ( StringUtils.isNotBlank( inReportDto.getSocietyName() ) )
            {
                query.setString( "SOCIETY", inReportDto.getSocietyName() );
            }
            else
            {
                LOGGER.info( inReportDto.getSocietyName() );
                query.setString( "SOCIETY", null );
            }
            query.setResultTransformer( Transformers.aliasToBean( RfsReportPojo.class ) );
            List<RfsReportPojo> list = query.list();
            LOGGER.info( "List Size in getRFSReport" + list.size() );
            inReportDto.setRfsReportPojos( list );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "unable to retrieve rfs report data: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "RFs Report generation end time ::" + System.currentTimeMillis() );
        return inReportDto;
    }

    @Override
    public ReportDto getPaymentReport( ReportDto inReportDto, String inNamedQuery )
    {
        LOGGER.info( "payment Report generation start time ::" + System.currentTimeMillis() );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            String fromDateStr = inReportDto.getFromDate();
            String toDateStr = inReportDto.getToDate();
            fromDateStr += IAppConstants.START_TIME;
            toDateStr += IAppConstants.END_TIME;
            LOGGER.info( "from Date:::;" + fromDateStr );
            LOGGER.info( "To Date:::;" + toDateStr );
            Date fromDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( fromDateStr );
            Date toDate = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.parse( toDateStr );
            query.setTimestamp( "FROM_DATE", fromDate );
            query.setTimestamp( "TO_DATE", toDate );
            query.setString( "CUSTOMER_ID", inReportDto.getPaymentReportPojo().getCustomerId() );
            query.setString( "CRF_ID", inReportDto.getPaymentReportPojo().getCrfId() );
            query.setString( "SERVICE_TYPE", inReportDto.getPaymentReportPojo().getServiceType() );
            query.setString( "INSTALLATION_STATUS", inReportDto.getPaymentReportPojo().getInstallationStatus() );
            query.setString( "PAYMENT_STATUS", inReportDto.getPaymentReportPojo().getPaymentStatus() );
            query.setString( "CASE_STATUS", inReportDto.getPaymentReportPojo().getCaseStatus() );
            query.setString( "CHEQUE_STATUS", inReportDto.getPaymentReportPojo().getChequeStatus() );
            query.setString( "ENTITY_TYPE", inReportDto.getPaymentReportPojo().getEntityType() );
            query.setString( "PAYMENT_MODE", inReportDto.getPaymentReportPojo().getPaymentMode() );
            query.setString( "PAYMENT_CHANNEL", inReportDto.getPaymentReportPojo().getPaymentChannel() );
            query.setString( "POSTING_STATUS", inReportDto.getPaymentReportPojo().getPostingStatus() );
            query.setResultTransformer( Transformers.aliasToBean( PaymentReportPojo.class ) );
            inReportDto.setPaymentReportPojos( query.list() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "unable to retrieve lms report data: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Payment Report generation end time ::" + System.currentTimeMillis() );
        return inReportDto;
    }

    @Override
    public ReportDto catWiseTicketReport( ReportDto inReportDto, String inNamedQuery )
    {
        LOGGER.info( "catWiseTicketReport" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            if ( StringUtils.isValidObj( inReportDto.getSlaReportPojo() ) )
            {
                if ( StringUtils.isNotBlank( inReportDto.getSlaReportPojo().getQrcType() ) )
                {
                    query.setString( "QTYPE", inReportDto.getSlaReportPojo().getQrcType() );
                }
                else
                {
                    query.setString( "QTYPE", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getSlaReportPojo().getCategory() ) )
                {
                    query.setString( "CATGRY", inReportDto.getSlaReportPojo().getCategory() );
                }
                else
                {
                    query.setString( "CATGRY", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getSlaReportPojo().getSubCategory() ) )
                {
                    query.setString( "SUBCATGRY", inReportDto.getSlaReportPojo().getSubCategory() );
                }
                else
                {
                    query.setString( "SUBCATGRY", null );
                }
                if ( StringUtils.isNotBlank( inReportDto.getSlaReportPojo().getSubSubCategory() ) )
                {
                    query.setString( "SUBSUBCATGRY", inReportDto.getSlaReportPojo().getSubSubCategory() );
                }
                else
                {
                    query.setString( "SUBSUBCATGRY", null );
                }
            }
            query.setResultTransformer( Transformers.aliasToBean( SlaReportPojo.class ) );
            List<SlaReportPojo> pojos = query.list();
            LOGGER.info( "List Size:" + pojos.size() );
            inReportDto.setSlaReportPojos( pojos );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }

    @Override
    public ReportDto userWiseTicketReport( ReportDto inReportDto, String inNamedQuery )
    {
        LOGGER.info( "userWiseTicketReport" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            sessionFactory = HibernateReportUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( inNamedQuery );
            if ( StringUtils.isValidObj( inReportDto.getSlaReportPojo() ) )
            {
                if ( StringUtils.isNotBlank( inReportDto.getSlaReportPojo().getUserName() ) )
                {
                    query.setString( "USERID", "%" + inReportDto.getSlaReportPojo().getUserName() + "%" );
                }
                else
                {
                    query.setString( "USERID", null );
                }
            }
            query.setResultTransformer( Transformers.aliasToBean( SlaReportPojo.class ) );
            List<SlaReportPojo> pojos = query.list();
            LOGGER.info( "List Size:" + pojos.size() );
            inReportDto.setSlaReportPojos( pojos );
        }
        catch ( Exception ex )
        {
            LOGGER.error( ex.getCause(), ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inReportDto;
    }
}
