package com.np.tele.crm.qrc.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CrmTicketActions;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.massoutage.dao.IMassOutageDao;
import com.np.tele.crm.masterdata.dao.MasterDataDaoImpl;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmHolidayDetails;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.CrmQrcActionTakenPojo;
import com.np.tele.crm.pojos.CrmQrcCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcRootCausePojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.SLACalculationUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class CrmTicketDaoImpl
    implements ICrmTicketDao
{
    private static final Logger                            LOGGER         = Logger.getLogger( CrmTicketDaoImpl.class );
    private IMassOutageDao                                 massOutageDao  = null;
    private IQrcManagerDao                                 qrcManagerDao  = null;
    private static final Map<String, CrmQrcCategoriesPojo> ROL_CATEGORIES = new HashMap<String, CrmQrcCategoriesPojo>();

    public IMassOutageDao getMassOutageDao()
    {
        return massOutageDao;
    }

    public void setMassOutageDao( IMassOutageDao inMassOutageDao )
    {
        massOutageDao = inMassOutageDao;
    }

    public IQrcManagerDao getQrcManagerDao()
    {
        return qrcManagerDao;
    }

    public void setQrcManagerDao( IQrcManagerDao inQrcManagerDao )
    {
        qrcManagerDao = inQrcManagerDao;
    }

    @Override
    public CrmQrcCategoriesPojo getQrcCategoriesID( String category,
                                                    String subCategory,
                                                    String subSubCategory,
                                                    String inQrcType )
    {
        LOGGER.info( "Inside getQrcSUBSUBCategories" );
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcCategoriesPojo.class );
            String str[] =
            { category, subCategory, subSubCategory };
            if ( StringUtils.checkAllvalidObj( str, false ) )
            {
                criteria.add( Restrictions.eq( "qrcCategory", category ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                session.enableFilter( "catqrcSubCategory" ).setParameter( "qrcSubCategory", subCategory );
                session.enableFilter( "catqrcSubSubCategory" ).setParameter( "qrcSubSubCategory", subSubCategory );
                if ( StringUtils.isNotBlank( inQrcType ) )
                {
                    session.enableFilter( "qrcType" ).setParameter( "qrcType", inQrcType );
                }
                else
                {
                    session.enableFilter( "qrcType" ).setParameter( "qrcType",
                                                                    CRMDisplayListConstants.REQUEST.getCode() );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcCategoriesPojo.class.getName() );
            List<CrmQrcCategoriesPojo> qrcCategory = criteria.list();
            if ( CommonValidator.isValidCollection( qrcCategory ) )
            {
                LOGGER.info( "Qrc List Size" + qrcCategory.size() );
                return qrcCategory.get( 0 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while get categories id: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return null;
    }

    @Override
    public void preparedQrcTicketPojo( CrmSrTicketsPojo inTicket )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl, preparedQrcTicketPojo" );
        CrmQrcCategoriesPojo crmCategoriesPojo = null;
        if ( ROL_CATEGORIES.containsKey( inTicket.getQrcCategory() + "-" + inTicket.getQrcSubCategory() + "-"
                + inTicket.getQrcSubSubCategory() ) )
        {
            crmCategoriesPojo = ROL_CATEGORIES.get( inTicket.getQrcCategory() + "-" + inTicket.getQrcSubCategory()
                    + "-" + inTicket.getQrcSubSubCategory() );
            LOGGER.info( "Category found from cache:" + crmCategoriesPojo );
        }
        else
        {
            crmCategoriesPojo = getQrcCategoriesID( inTicket.getQrcCategory(), inTicket.getQrcSubCategory(),
                                                    inTicket.getQrcSubSubCategory(), inTicket.getQrcType() );
            if ( StringUtils.isValidObj( crmCategoriesPojo ) )
            {
                ROL_CATEGORIES.put( inTicket.getQrcCategory() + "-" + inTicket.getQrcSubCategory() + "-"
                                            + inTicket.getQrcSubSubCategory(), crmCategoriesPojo );
            }
        }
        if ( StringUtils.isValidObj( crmCategoriesPojo ) )
        {
            inTicket.setQrcCategoryId( crmCategoriesPojo.getQrcCategoryId() );
            inTicket.setModuleType( CRMRequestType.QRC.getRequestCode() );
            LOGGER.info( "QRC Category Id:: " + crmCategoriesPojo.getQrcCategoryId() );
            outer: for ( CrmQrcSubCategoriesPojo crmQrcCategoriesPojo : crmCategoriesPojo.getCrmQrcSubCategorieses() )
            {
                inTicket.setQrcSubCategoryId( crmQrcCategoriesPojo.getQrcSubCategoryId() );
                LOGGER.info( "QRC Sub Category Id:: " + crmQrcCategoriesPojo.getQrcSubCategoryId() );
                for ( CrmQrcSubSubCategoriesPojo subSubCategoriesPojo : crmQrcCategoriesPojo
                        .getCrmQrcSubSubCategorieses() )
                {
                    LOGGER.info( "QRC Sub Sub Category Id:: " + subSubCategoriesPojo.getQrcSubSubCategoryId() );
                    inTicket.setQrcSubSubCategoryId( subSubCategoriesPojo.getQrcSubSubCategoryId() );
                    inTicket.setFunctionalbinId( subSubCategoriesPojo.getFunctionalBinId() );
                    break outer;
                }
            }
        }
    }

    @Override
    public CRMServiceCode openTicket( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        CrmSrTicketsPojo inTicket = inCrmQrcDto.getCrmSrTicketsPojo();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isEmpty( inTicket.getCreatedBy() ) )
            {
                inTicket.setCreatedBy( "System" );
            }
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            else if ( StringUtils.isNotBlank( inTicket.getSrId() ) )
            {
                dbPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inTicket.getSrId(), session );
            }
            if ( !StringUtils.isValidObj( dbPojo ) )
            {
                if ( CrmTicketActions.FORWARD.getCode() == inTicket.getResolutionType()
                        && massOutageROL( inTicket.getQrcSubSubCategoryId(), inTicket.getMappingId() ) )
                {
                    inTicket.setResolutionType( CrmTicketActions.ROL.getCode() );
                }
                String srId = generateTicketSRId( inTicket.getMappingId(), inTicket.getQrcType(),
                                                  inTicket.getModuleType() );
                if ( StringUtils.isNotBlank( srId ) )
                {
                    if ( inTicket.getResolutionType() == CrmTicketActions.ROL.getCode() )
                    {
                        // Resolving Ticket
                        CrmQrcActionTakenPojo actionTaken = null;
                        if ( StringUtils.isValidObj( inTicket.getActionTakenId() ) && inTicket.getActionTakenId() > 0 )
                        {
                            actionTaken = CRMServiceUtils.getDBValues( CrmQrcActionTakenPojo.class,
                                                                       inTicket.getActionTakenId() );
                            if ( StringUtils.isValidObj( actionTaken ) )
                            {
                                inTicket.setActionTaken( actionTaken.getActionTaken() );
                                inTicketHistory.setActionTaken( actionTaken.getActionTaken() );
                            }
                        }
                        CrmQrcRootCausePojo rootCause = null;
                        if ( StringUtils.isValidObj( inTicket.getRootCauseId() ) && inTicket.getRootCauseId() > 0 )
                        {
                            rootCause = CRMServiceUtils.getDBValues( CrmQrcRootCausePojo.class,
                                                                     inTicket.getRootCauseId() );
                            if ( StringUtils.isValidObj( rootCause ) )
                            {
                                inTicket.setRootCause( rootCause.getRootCause() );
                                inTicket.setAttributedTo( rootCause.getAttributedTo() );
                                inTicketHistory.setRootCause( rootCause.getRootCause() );
                                inTicketHistory.setAttributedTo( rootCause.getAttributedTo() );
                            }
                        }
                        inTicket.setSrResolvedOn( Calendar.getInstance().getTime() );
                        inTicket.setResolvedBy( inTicket.getCreatedBy() );
                        inTicket.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
                        inTicket.setCreatedTime( Calendar.getInstance().getTime() );
                        inTicket.setSrId( srId );
                        inTicket.setFollowupOn( Calendar.getInstance().getTime() );
                        inTicket.setTicketId( (Long) session.save( inTicket ) );
                        serviceCode = CRMServiceCode.CRM001;
                        inTicketHistory.setAction( CrmActionEnum.RESOLVED.getActionCode() );
                        inTicketHistory.setTicketId( inTicket.getSrId() );
                        inTicketHistory.setWrongTagging( inTicket.getWrongTagging() );
                        inTicketHistory.setVisitRequired( inTicket.getVisitRequired() );
                    }
                    else if ( inTicket.getResolutionType() == CrmTicketActions.FORWARD.getCode() )
                    {
                        dbPojo = fetchExistingPojo( inTicket );
                        if ( StringUtils.isValidObj( dbPojo ) )
                        {
                            Calendar now = Calendar.getInstance();
                            if ( StringUtils.equals( dbPojo.getStatus(), CRMStatusCode.RESOLVED.getStatusCode() ) )
                            {
                                // Reopening Ticket
                                if ( !StringUtils.isValidObj( inTicket.getFollowupOn() ) )
                                {
                                    inTicket.setFollowupOn( now.getTime() );
                                }
                                dbPojo.setFollowupOn( inTicket.getFollowupOn() );
                                dbPojo.setAPITransactionId( inTicket.getAPITransactionId() );
                                dbPojo.setProcessingDate( inTicket.getProcessingDate() );
                                dbPojo.setLastModifiedBy( inTicket.getCreatedBy() );
                                dbPojo.setLastModifiedTime( now.getTime() );
                                dbPojo.setSrReopenedOn( now.getTime() );
                                dbPojo.setSlaCalculatedOn( now.getTime() );
                                dbPojo.setStatus( CRMStatusCode.REOPEN.getStatusCode() );
                                long EOC_RB_ID = getEOCResolvedFB();
                                if ( EOC_RB_ID != dbPojo.getFunctionalbinId() )
                                {
                                    expectedReopenSLA( dbPojo );
                                }
                                session.merge( dbPojo );
                                inTicketHistory.setOldFollowupOn( now.getTime() );
                                inTicketHistory.setNewFollowupOn( dbPojo.getFollowupOn() );
                                inTicketHistory.setAction( CrmActionEnum.REOPENED.getActionCode() );
                                inTicketHistory.setTicketId( dbPojo.getSrId() );
                                inTicketHistory.setCreatedBy( inTicket.getCreatedBy() );
                                inTicket = dbPojo;
                                inCrmQrcDto.setCrmSrTicketsPojo( dbPojo );
                                serviceCode = CRMServiceCode.CRM001;
                            }
                            else
                            {
                                inCrmQrcDto.setCrmSrTicketsPojo( dbPojo );
                                serviceCode = CRMServiceCode.CRM404;
                            }
                        }
                        else
                        {
                            // Opening Ticket
                            Calendar now = Calendar.getInstance();
                            inTicket.setStatus( CRMStatusCode.OPEN.getStatusCode() );
                            inTicket.setSrId( srId );
                            inTicket.setCreatedTime( now.getTime() );
                            inTicket.setSlaCalculatedOn( Calendar.getInstance().getTime() );
                            if ( !StringUtils.isValidObj( inTicket.getFollowupOn() ) )
                            {
                                inTicket.setFollowupOn( now.getTime() );
                            }
                            long EOC_RB_ID = getEOCResolvedFB();
                            if ( EOC_RB_ID != inTicket.getFunctionalbinId() )
                            {
                                expectedOpenSLA( inTicket );
                            }
                            inTicket.setTicketId( (Long) session.save( inTicket ) );
                            serviceCode = CRMServiceCode.CRM001;
                            inTicketHistory.setAction( CrmActionEnum.OPENED.getActionCode() );
                            inTicketHistory.setTicketId( inTicket.getSrId() );
                            inTicketHistory.setOldFollowupOn( now.getTime() );
                            inTicketHistory.setNewFollowupOn( inTicket.getFollowupOn() );
                            inTicketHistory.setToBin( inTicket.getFunctionalbinId() );
                        }
                    }
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM433;
                }
            }
            else
            {
                serviceCode = CRMServiceCode.CRM404;
            }
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                if ( CrmTicketActions.FORWARD.getCode() == inTicket.getResolutionType() )
                {
                    serviceCode = activateInbox( CRMRequestType.QRC, inTicket.getSrId(), session,
                                                 inTicket.getCreatedBy(), inTicket.getFunctionalbinId() + "", null,
                                                 inTicket );
                }
            }
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                serviceCode = saveTicketHistory( inTicketHistory, session );
            }
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing open ticket", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing open ticket", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, inCrmQrcDto.getCrmSrTicketsPojo()
                        .getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while opening Ticket ID/Record ID:" + inTicket.getSrId() + "/"
                    + inTicket.getTicketId() );
        }
        return serviceCode;
    }

    private List<String> getFutureHolidays( boolean inFuture )
    {
        LOGGER.info( "In MasterDataDaoImpl :: getHoldayDetails " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<String> holidays = new ArrayList<String>();
        List<CrmHolidayDetails> records = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmHolidayDetails.class );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            if ( inFuture )
            {
                criteria.add( Restrictions.ge( "holidayDate", Calendar.getInstance().getTime() ) );
            }
            else
            {
                criteria.add( Restrictions.le( "holidayDate", Calendar.getInstance().getTime() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmHolidayDetails.class.getName() );
            records = criteria.list();
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error getHoldayDetails in master operation", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        if ( CommonValidator.isValidCollection( records ) )
        {
            for ( CrmHolidayDetails crmHolidayDetails : records )
            {
                holidays.add( DateUtils.getFormattedDate( crmHolidayDetails.getHolidayDate(),
                                                          IDateConstants.SDF_DD_MMM_YYYY ) );
            }
        }
        return holidays;
    }

    @Override
    public CRMServiceCode modifyFollowUpTime( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        CrmSrTicketsPojo inTicket = inCrmQrcDto.getCrmSrTicketsPojo();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            else if ( StringUtils.isNotBlank( inTicket.getSrId() ) )
            {
                dbPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inTicket.getSrId(), session );
            }
            if ( StringUtils.isValidObj( dbPojo ) )
            {
                inTicketHistory.setTicketId( dbPojo.getSrId() );
                inTicketHistory.setOldFollowupOn( dbPojo.getFollowupOn() );
                inTicketHistory.setNewFollowupOn( inTicket.getFollowupOn() );
                long EOC_RB_ID = getEOCResolvedFB();
                if ( EOC_RB_ID != dbPojo.getFunctionalbinId() )
                {
                    expectedFollwUpSLA( dbPojo, inTicket.getFollowupOn() );
                }
                dbPojo.setFollowupOn( inTicket.getFollowupOn() );
                if ( StringUtils.isNotBlank( inTicket.getAPITransactionId() ) )
                {
                    dbPojo.setAPITransactionId( inTicket.getAPITransactionId() );
                }
                if ( StringUtils.isValidObj( inTicket.getProcessingDate() ) )
                {
                    dbPojo.setProcessingDate( inTicket.getProcessingDate() );
                }
                dbPojo.setLastModifiedBy( inTicket.getLastModifiedBy() );
                dbPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                dbPojo.setQrcCategory( inTicket.getQrcCategory() );
                dbPojo.setQrcSubCategory( inTicket.getQrcSubCategory() );
                dbPojo.setQrcSubSubCategory( inTicket.getQrcSubSubCategory() );
                serviceCode = saveTicketHistory( inTicketHistory, session );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    if ( StringUtils.equals( inCrmQrcDto.getInboxSelected(), IAppConstants.GROUP_INBOX ) )
                    {
                        serviceCode = changeInbox( CRMRequestType.QRC, dbPojo.getSrId(), session,
                                                   inTicket.getLastModifiedBy() );
                    }
                    if ( serviceCode == CRMServiceCode.CRM001 )
                    {
                        transaction.commit();
                        inCrmQrcDto.setCrmSrTicketsPojo( dbPojo );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing modifyFollowUpTime", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing modifyFollowUpTime", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, dbPojo.getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while updating follow up time for Ticket ID/Record ID:"
                    + inTicket.getSrId() + "/" + inTicket.getTicketId() );
        }
        return serviceCode;
    }

    @Override
    public CRMServiceCode resolveTicket( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        CrmSrTicketsPojo newTicketPojo = null;
        CrmSrTicketsPojo inTicket = inCrmQrcDto.getCrmSrTicketsPojo();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            else if ( StringUtils.isNotBlank( inTicket.getSrId() ) )
            {
                dbPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inTicket.getSrId(), session );
            }
            if ( StringUtils.isValidObj( dbPojo )
                    && !StringUtils.equals( CRMStatusCode.CLOSE.getStatusCode(), dbPojo.getStatus() ) )
            {
                Calendar now = Calendar.getInstance();
                CrmQrcActionTakenPojo actionTaken = null;
                if ( StringUtils.isValidObj( inTicket.getActionTakenId() ) && inTicket.getActionTakenId() > 0 )
                {
                    actionTaken = CRMServiceUtils
                            .getDBValues( CrmQrcActionTakenPojo.class, inTicket.getActionTakenId() );
                    dbPojo.setActionTaken( actionTaken.getActionTaken() );
                    inTicketHistory.setActionTaken( actionTaken.getActionTaken() );
                }
                CrmQrcRootCausePojo rootCause = null;
                if ( StringUtils.isValidObj( inTicket.getRootCauseId() ) && inTicket.getRootCauseId() > 0 )
                {
                    rootCause = CRMServiceUtils.getDBValues( CrmQrcRootCausePojo.class, inTicket.getRootCauseId() );
                    dbPojo.setRootCause( rootCause.getRootCause() );
                    dbPojo.setAttributedTo( rootCause.getAttributedTo() );
                    inTicketHistory.setRootCause( rootCause.getRootCause() );
                    inTicketHistory.setAttributedTo( rootCause.getAttributedTo() );
                }
                inTicketHistory.setTicketId( dbPojo.getSrId() );
                inTicketHistory.setWrongTagging( inTicket.getWrongTagging() );
                inTicketHistory.setVisitRequired( inTicket.getVisitRequired() );
                dbPojo.setWrongTagging( inTicket.getWrongTagging() );
                dbPojo.setVisitRequired( inTicket.getVisitRequired() );
                dbPojo.setLastModifiedBy( inTicket.getLastModifiedBy() );
                dbPojo.setLastModifiedTime( now.getTime() );
                dbPojo.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
                dbPojo.setResolvedBy( inTicket.getLastModifiedBy() );
                dbPojo.setSrResolvedOn( now.getTime() );
                dbPojo.setQrcCategory( inTicket.getQrcCategory() );
                dbPojo.setQrcSubCategory( inTicket.getQrcSubCategory() );
                dbPojo.setQrcSubSubCategory( inTicket.getQrcSubSubCategory() );
                long EOC_RB_ID = getEOCResolvedFB();
                if ( EOC_RB_ID != dbPojo.getFunctionalbinId() )
                {
                    finalSLAHours( dbPojo );
                }
                serviceCode = saveTicketHistory( inTicketHistory, session );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    serviceCode = deactivateInbox( CRMRequestType.QRC, dbPojo.getSrId(), session,
                                                   inTicket.getLastModifiedBy() );
                }
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    if ( ( StringUtils.equalsIgnoreCase( dbPojo.getQrcCategory(), "Backend EOC" )
                            && StringUtils.equalsIgnoreCase( dbPojo.getQrcSubCategory(), "Slave Recovery" )
                            && StringUtils.equalsIgnoreCase( dbPojo.getQrcSubSubCategory(), "Mac Release" ) && StringUtils
                                .equalsIgnoreCase( dbPojo.getActionTaken(), "Slave Recovered" ) )
                            && ( StringUtils.equalsIgnoreCase( dbPojo.getRootCause(), "Refund by LMO" )
                                    || StringUtils.equalsIgnoreCase( dbPojo.getRootCause(), "Refund By Timbl" )
                                    || StringUtils.equalsIgnoreCase( dbPojo.getRootCause(),
                                                                     "Refund by Timbl AM, CPE with LMO" )
                                    || StringUtils.equalsIgnoreCase( dbPojo.getRootCause(),
                                                                     "Refund by Timbl AM, CPE with Timbl" )
                                    || StringUtils.equalsIgnoreCase( dbPojo.getRootCause(),
                                                                     "Refund by Timbl Finance, CPE with LMO" )
                                    || StringUtils.equalsIgnoreCase( dbPojo.getRootCause(),
                                                                     "Refund by Timbl Finance, CPE with Timbl" ) || StringUtils
                                        .equalsIgnoreCase( dbPojo.getRootCause(),
                                                           "(Slave not recovered) Slave Replaced by Other ISP" ) ) )
                    {
                        serviceCode = processPDC( inCrmQrcDto, dbPojo, inTicketHistory.getRemarks(), session );
                        if ( serviceCode == CRMServiceCode.CRM001 )
                        {
                            newTicketPojo = new CrmSrTicketsPojo();
                            CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = CRMServiceUtils
                                    .getDBValues( CrmQrcSubSubCategoriesPojo.class, "qrcSubSubCategory",
                                                  "MAC Release in NMS" );
                            if ( StringUtils.isValidObj( subSubCategoriesPojo ) )
                            {
                                CrmQrcSubCategoriesPojo subCategoriesPojo = CRMServiceUtils
                                        .getDBValues( CrmQrcSubCategoriesPojo.class, "qrcSubCategoryId",
                                                      subSubCategoriesPojo.getQrcSubCategoryId() );
                                newTicketPojo.setQrcSubSubCategoryId( subSubCategoriesPojo.getQrcSubSubCategoryId() );
                                newTicketPojo.setQrcSubCategoryId( subSubCategoriesPojo.getQrcSubCategoryId() );
                                newTicketPojo.setQrcCategoryId( subCategoriesPojo.getQrcCategoryId() );
                                LOGGER.info( "ids category:: " + newTicketPojo.getQrcCategoryId() + " subcat:: "
                                        + newTicketPojo.getQrcSubCategoryId() + " subsubcat:: "
                                        + newTicketPojo.getQrcSubSubCategoryId() );
                                newTicketPojo.setResolutionType( CrmTicketActions.FORWARD.getCode() );
                                newTicketPojo.setFunctionalbinId( subSubCategoriesPojo.getFunctionalBinId() );
                                newTicketPojo.setMappingId( inTicket.getMappingId() );
                                newTicketPojo.setQrcType( subSubCategoriesPojo.getQrcType() );
                                newTicketPojo.setModuleType( CRMRequestType.QRC.getRequestCode() );
                                newTicketPojo.setCreatedBy( "Auto" );
                                //set value in remarks 
                                CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                                ticketHistory.setRemarks( "Auto ticket generated by System." );
                                ticketHistory.setCreatedBy( "Auto" );
                                inCrmQrcDto.setCrmSrTicketsPojo( newTicketPojo );
                                //set value in crmQrcDto 
                                serviceCode = openTicket( inCrmQrcDto, ticketHistory );
                            }
                        }
                    }
                }
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    inCrmQrcDto.setCrmSrTicketsPojo( dbPojo );
                    transaction.commit();
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing resolveTicket", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing resolveTicket", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, dbPojo.getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while resolving Ticket ID/Record ID:" + inTicket.getSrId()
                    + "/" + inTicket.getTicketId() );
        }
        return serviceCode;
    }

    private long getEOCResolvedFB()
    {
        MasterDataDto inMasterDataDto = new MasterDataDto();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
        crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
        crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
        crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        crmRcaReasonPojo.setValueAlias( CRMOperationStages.EOC_RESOLVED_BIN.getCode() );
        inMasterDataDto.setCrmRcaReason( crmRcaReasonPojo );
        try
        {
            MasterDataDaoImpl masterDataDaoImpl = new MasterDataDaoImpl();
            inMasterDataDto = masterDataDaoImpl.searchAllCategoryValue( inMasterDataDto );
            if ( CommonValidator.isValidCollection( inMasterDataDto.getCrmRcaReasonsList() ) )
            {
                return inMasterDataDto.getCrmRcaReasonsList().get( 0 ).getCategoryId();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching Category Value: ", ex );
        }
        return 0l;
    }

    private CRMServiceCode processPDC( CrmQrcDto inCrmQrcDto,
                                       CrmSrTicketsPojo ticketPojo,
                                       String inRemarks,
                                       Session inSession )
    {
        LOGGER.info( "IN processPDC" );
        CrmCustomerDetailsPojo customerDetailsPojo = null;
        RemarksPojo remarksPojo = new RemarksPojo();
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            customerDetailsPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                               ticketPojo.getMappingId(), inSession );
            if ( StringUtils.isValidObj( customerDetailsPojo )
                    && !StringUtils.equals( customerDetailsPojo.getStatus(), CRMStatusCode.PDC.getStatusCode() ) )
            {
                customerDetailsPojo.setStatus( CRMStatusCode.PDC.getStatusCode() );
                inCrmQrcDto.setSrTicketNo( ticketPojo.getSrId() );
                inCrmQrcDto.setCustomerId( customerDetailsPojo.getCustomerId() );
                inCrmQrcDto.setCustomerRecordId( customerDetailsPojo.getRecordId() );
                inCrmQrcDto.setCustomerDetailsPojo( customerDetailsPojo );
                remarksPojo.setRemarks( inRemarks );
                inCrmQrcDto.setRemarksPojo( remarksPojo );
                getQrcManagerDao().updateCustomerStatus( inCrmQrcDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCrmQrcDto.getStatusCode() ) )
                {
                    customerDetailsPojo.setLastModifiedBy( inCrmQrcDto.getUserId() );
                    customerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    customerDetailsPojo.setPermanentDisconnectDate( Calendar.getInstance().getTime() );
                    inSession.merge( customerDetailsPojo );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    serviceCode = CRMServiceCode.valueOf( inCrmQrcDto.getStatusCode() );
                    inCrmQrcDto.setBillingErrorCode( inCrmQrcDto.getBillingErrorCode() );
                    LOGGER.info( "Status Code ::" + serviceCode );
                }
            }
            else
            {
                LOGGER.info( "Customer status is ::" + customerDetailsPojo.getStatus() );
                serviceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while executing processPDC", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        return serviceCode;
    }

    @Override
    public CRMServiceCode closeTicket( CrmSrTicketsPojo inTicket, CrmTicketHistoryPojo inTicketHistory )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            else if ( StringUtils.isNotBlank( inTicket.getSrId() ) )
            {
                dbPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inTicket.getSrId(), session );
            }
            if ( StringUtils.isValidObj( dbPojo )
                    && StringUtils.equals( CRMStatusCode.RESOLVED.getStatusCode(), dbPojo.getStatus() ) )
            {
                inTicketHistory.setTicketId( dbPojo.getSrId() );
                dbPojo.setStatus( CRMStatusCode.CLOSE.getStatusCode() );
                dbPojo.setLastModifiedBy( inTicket.getLastModifiedBy() );
                dbPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                dbPojo.setSrClosedOn( Calendar.getInstance().getTime() );
                serviceCode = saveTicketHistory( inTicketHistory, session );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    transaction.commit();
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing closeTicket", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing closeTicket", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, dbPojo.getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while closing Ticket ID/Record ID:" + inTicket.getSrId() + "/"
                    + inTicket.getTicketId() );
        }
        return serviceCode;
    }

    @Override
    public CRMServiceCode forwardTicket( CrmQrcDto inCrmQrcDto,
                                         CrmSrTicketsPojo inTicket,
                                         CrmTicketHistoryPojo inTicketHistory,
                                         String inStage,
                                         String inOwner )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            else if ( StringUtils.isNotBlank( inTicket.getSrId() ) )
            {
                dbPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inTicket.getSrId(), session );
            }
            /*if ( dbPojo.getQrcSubSubCategoryId() != inTicket.getQrcSubSubCategoryId() )
            {
                dbPojo.setQrcSubSubCategoryId( inTicket.getQrcSubSubCategoryId() );
            }*/
            if ( StringUtils.isValidObj( dbPojo )
                    && !StringUtils.equals( CRMStatusCode.RESOLVED.getStatusCode(), dbPojo.getStatus() )
                    && !StringUtils.equals( CRMStatusCode.CLOSE.getStatusCode(), dbPojo.getStatus() ) )
            {
                long EOC_RB_ID = getEOCResolvedFB();
                if ( EOC_RB_ID == dbPojo.getFunctionalbinId() )
                {
                    finalSLAHours( dbPojo );
                }
                inTicketHistory.setTicketId( dbPojo.getSrId() );
                inTicketHistory.setFromBin( dbPojo.getFunctionalbinId() );
                inTicketHistory.setToBin( StringUtils.numericValue( inStage ) );
                serviceCode = deactivateInbox( CRMRequestType.QRC, dbPojo.getSrId(), session,
                                               inTicket.getLastModifiedBy() );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    dbPojo.setPreviousStage( dbPojo.getFunctionalbinId() + "" );
                    dbPojo.setFunctionalbinId( StringUtils.numericValue( inStage ) );
                    dbPojo.setLastModifiedBy( inTicket.getLastModifiedBy() );
                    dbPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    if ( inTicket.isFlagSelected() )
                    {
                        dbPojo.setFlagRemarks( inTicketHistory.getRemarks() );
                    }
                    serviceCode = activateInbox( CRMRequestType.QRC, dbPojo.getSrId(), session,
                                                 inTicket.getLastModifiedBy(), inStage, inOwner, inTicket );
                }
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    serviceCode = saveTicketHistory( inTicketHistory, session );
                    if ( inTicket.isFlagSelected() )
                    {
                        CrmTicketHistoryPojo flagHistory = new CrmTicketHistoryPojo();
                        flagHistory.setTicketId( inTicketHistory.getTicketId() );
                        flagHistory.setAction( "FR" );
                        flagHistory.setRemarks( inTicketHistory.getRemarks() );
                        flagHistory.setCreatedBy( inTicket.getLastModifiedBy() );
                        flagHistory.setCreatedTime( Calendar.getInstance().getTime() );
                        saveTicketHistory( flagHistory, session );
                    }
                }
                /*if ( serviceCode == CRMServiceCode.CRM001 && StringUtils.isNotBlank( inOwner ) )
                {
                    CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                    ticketHistory.setAction( CrmActionEnum.ASSIGNED.getActionCode() );
                    ticketHistory.setCreatedBy( inTicket.getLastModifiedBy() );
                    ticketHistory.setRemarks( "Assigned by previous owner." );
                    ticketHistory.setTicketId( inTicket.getSrId() );
                    ticketHistory.setToUser( inOwner );
                    serviceCode = saveTicketHistory( ticketHistory, session );
                }*/
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    transaction.commit();
                    inCrmQrcDto.setCrmSrTicketsPojo( dbPojo );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing forwardTicket", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing forwardTicket", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, dbPojo.getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while forwarding Ticket ID/Record ID:" + inTicket.getSrId()
                    + "/" + inTicket.getTicketId() );
        }
        return serviceCode;
    }

    private CRMServiceCode deactivateInbox( CRMRequestType inQrc, String inSrId, Session inSession, String inUser )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "requestType", inQrc.getRequestCode() );
        criteriaMap.put( "mappingId", inSrId );
        criteriaMap.put( "status", CRMStatusCode.ACTIVE.getStatusCode() );
        List<CrmInboxPojo> inboxes = CRMServiceUtils.getDBValueList( CrmInboxPojo.class, criteriaMap, inSession );
        if ( CommonValidator.isValidCollection( inboxes ) )
        {
            for ( CrmInboxPojo inbox : inboxes )
            {
                inbox.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                inbox.setLastModifiedBy( inUser );
                inbox.setLastModifiedTime( Calendar.getInstance().getTime() );
            }
            serviceCode = CRMServiceCode.CRM001;
        }
        LOGGER.info( serviceCode.getStatusDesc() + " while Inbox Deactivating for Ticket ID:" + inSrId );
        return serviceCode;
    }

    private CRMServiceCode changeInbox( CRMRequestType inQrc, String inSrId, Session inSession, String inUser )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "requestType", inQrc.getRequestCode() );
        criteriaMap.put( "mappingId", inSrId );
        criteriaMap.put( "status", CRMStatusCode.ACTIVE.getStatusCode() );
        List<CrmInboxPojo> inboxes = CRMServiceUtils.getDBValueList( CrmInboxPojo.class, criteriaMap, inSession );
        if ( CommonValidator.isValidCollection( inboxes ) )
        {
            for ( CrmInboxPojo inbox : inboxes )
            {
                inbox.setUserId( "" );
                inbox.setLastModifiedBy( inUser );
                inbox.setLastModifiedTime( Calendar.getInstance().getTime() );
            }
            serviceCode = CRMServiceCode.CRM001;
        }
        LOGGER.info( serviceCode.getStatusDesc() + " while Inbox change for Ticket ID:" + inSrId );
        return serviceCode;
    }

    private CRMServiceCode activateInbox( CRMRequestType inQrc,
                                          String inSrId,
                                          Session inSession,
                                          String inUser,
                                          String inStage,
                                          String inOwner,
                                          CrmSrTicketsPojo inTicket )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        Map<String, Object> criteriaMap = new HashMap<String, Object>();
        criteriaMap.put( "requestType", inQrc.getRequestCode() );
        criteriaMap.put( "mappingId", inSrId );
        criteriaMap.put( "stage", inStage );
        CrmInboxPojo inbox = CRMServiceUtils.getDBValue( CrmInboxPojo.class, criteriaMap, null, true, inSession );
        if ( StringUtils.isValidObj( inbox ) )
        {
            inbox.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            inbox.setLastModifiedBy( inUser );
            inbox.setLastModifiedTime( Calendar.getInstance().getTime() );
            inbox.setUserId( inOwner );
            if ( StringUtils.isNotBlank( inOwner ) )
            {
                inbox.setUnRead( true );
            }
            serviceCode = CRMServiceCode.CRM001;
        }
        else
        {
            inbox = new CrmInboxPojo();
            inbox.setCreatedBy( inUser );
            inbox.setCreatedTime( Calendar.getInstance().getTime() );
            inbox.setRequestType( inQrc.getRequestCode() );
            inbox.setMappingId( inSrId );
            inbox.setStage( inStage );
            inbox.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            if ( StringUtils.isNotBlank( inOwner ) )
            {
                inbox.setUserId( inOwner );
                inbox.setUnRead( true );
            }
            if ( (Long) inSession.save( inbox ) > 0 )
            {
                serviceCode = CRMServiceCode.CRM001;
                inTicket.setEditable( true );
            }
        }
        LOGGER.info( serviceCode.getStatusDesc() + " while Inbox Activating for Ticket ID:" + inSrId );
        return serviceCode;
    }

    @Override
    public CRMServiceCode saveTicketHistory( CrmTicketHistoryPojo inTicketHistory, Session inSession )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        inTicketHistory.setCreatedTime( Calendar.getInstance().getTime() );
        long generatedId = 0l;
        if ( StringUtils.isValidObj( inSession ) && inSession.isOpen() )
        {
            generatedId = (Long) inSession.save( inTicketHistory );
        }
        else
        {
            generatedId = CRMServiceUtils.saveDBValues( inTicketHistory );
        }
        if ( generatedId > 0 )
        {
            serviceCode = CRMServiceCode.CRM001;
        }
        LOGGER.info( serviceCode.getStatusDesc() + " while Saving History for Ticket ID:"
                + inTicketHistory.getTicketId() );
        return serviceCode;
    }

    private Long getTicketCount( String customerId, String qrcType )
    {
        Session session = null;
        Criteria criteria = null;
        Long count = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmSrTicketsPojo.class );
            String str[] =
            { customerId, qrcType };
            if ( StringUtils.checkAllvalidObj( str, false ) )
            {
                criteria.setProjection( Projections.rowCount() );
                criteria.add( Restrictions.eq( "mappingId", customerId ) );
                criteria.add( Restrictions.eq( "qrcType", qrcType ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
            count = (Long) criteria.uniqueResult();
            if ( count < 999 )
            {
                count = count + 1;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while count tickets: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return count;
    }

    private String generateTicketSRId( String customerId, String qrcType, String inModuleType )
    {
        Long ticketCount = getTicketCount( customerId, qrcType );
        String generatedSR = null;
        if ( StringUtils.equals( inModuleType, CRMRequestType.QRC.getRequestCode() ) )
        {
            if ( StringUtils.isValidObj( ticketCount ) )
            {
                generatedSR = customerId + CRMRequestType.getCode( qrcType )
                        + StringUtils.leftPad( ticketCount + "", 3, "0" );
            }
        }
        else
        {
            if ( StringUtils.isValidObj( ticketCount ) )
            {
                generatedSR = customerId + "L" + CRMRequestType.getCode( qrcType )
                        + StringUtils.leftPad( ticketCount + "", 3, "0" );
            }
        }
        LOGGER.info( "Generated Ticket NO:" + generatedSR );
        return generatedSR;
    }

    private boolean massOutageROL( long inSubSubCategoryId, String inCustomerId )
    {
        if ( inSubSubCategoryId > 0 && StringUtils.isNotBlank( inCustomerId ) )
        {
            CrmQrcSubSubCategoriesPojo subSubCat = CRMServiceUtils.getDBValues( CrmQrcSubSubCategoriesPojo.class,
                                                                                inSubSubCategoryId );
            if ( StringUtils.isValidObj( subSubCat )
                    && StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), subSubCat.getStatus() )
                    && StringUtils.equals( IAppConstants.Y, subSubCat.getMassOutageRol() ) )
            {
                CrmMassOutagePojo massOutage = getMassOutageDao().trackMassOutage( inCustomerId );
                if ( StringUtils.isValidObj( massOutage ) )
                {
                    return true;
                }
            }
        }
        return false;
    }

    private CrmSrTicketsPojo fetchExistingPojo( CrmSrTicketsPojo inTicketPOJO )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : -fetchExistingPojo" );
        List<CrmSrTicketsPojo> ticketsPojos = null;
        CrmSrTicketsPojo ticketPOJO = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            if ( inTicketPOJO.getTicketId() < 1 )
            {
                criteria = session.createCriteria( CrmSrTicketsPojo.class );
                criteria.add( Restrictions.eq( "qrcCategoryId", inTicketPOJO.getQrcCategoryId() ) );
                criteria.add( Restrictions.eq( "qrcSubCategoryId", inTicketPOJO.getQrcSubCategoryId() ) );
                criteria.add( Restrictions.eq( "qrcSubSubCategoryId", inTicketPOJO.getQrcSubSubCategoryId() ) );
                criteria.add( Restrictions.eq( "resolutionType", CrmTicketActions.FORWARD.getCode() ) );
                criteria.add( Restrictions.eq( "qrcType", inTicketPOJO.getQrcType() ) );
                criteria.add( Restrictions.eq( "mappingId", inTicketPOJO.getMappingId() ) );
                criteria.add( Restrictions.ne( "status", CRMStatusCode.CLOSE.getStatusCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
                ticketsPojos = criteria.list();
                if ( CommonValidator.isValidCollection( ticketsPojos ) )
                {
                    ticketPOJO = ticketsPojos.get( 0 );
                }
            }
            else
            {
                ticketPOJO = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicketPOJO.getTicketId() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in fetch Existing Pojo ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Fetched POJO : " + ticketPOJO );
        return ticketPOJO;
    }

    @Override
    public CRMServiceCode listTickets( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside QrcManagerDaoImpl : -listTickets" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        CrmSrTicketsPojo ticket = inCrmQrcDto.getCrmSrTicketsPojo();
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmSrTicketsPojo.class );
            if ( ticket.getQrcCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcCategoryId", ticket.getQrcCategoryId() ) );
            }
            if ( ticket.getQrcSubCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcSubCategoryId", ticket.getQrcSubCategoryId() ) );
            }
            if ( ticket.getQrcSubSubCategoryId() > 0 )
            {
                criteria.add( Restrictions.eq( "qrcSubSubCategoryId", ticket.getQrcSubSubCategoryId() ) );
            }
            if ( ticket.getResolutionType() == 1 )
            {
                criteria.add( Restrictions.eq( "resolutionType", CrmTicketActions.FORWARD.getCode() ) );
            }
            if ( StringUtils.isNotBlank( ticket.getQrcType() ) )
            {
                criteria.add( Restrictions.eq( "qrcType", ticket.getQrcType() ) );
            }
            if ( StringUtils.isNotBlank( ticket.getMappingId() ) )
            {
                criteria.add( Restrictions.eq( "mappingId", ticket.getMappingId() ) );
            }
            if ( StringUtils.isNotBlank( ticket.getModuleType() ) )
            {
                criteria.add( Restrictions.eq( "moduleType", ticket.getModuleType() ) );
            }
            if ( StringUtils.isNotBlank( ticket.getStatus() ) )
            {
                if ( StringUtils.equals( ticket.getStatus(), CRMStatusCode.OPEN.getStatusCode() )
                        || StringUtils.equals( ticket.getStatus(), CRMStatusCode.REOPEN.getStatusCode() ) )
                {
                    Criterion statusOpen = Restrictions.eq( "status", CRMStatusCode.OPEN.getStatusCode() );
                    Criterion statusReopen = Restrictions.eq( "status", CRMStatusCode.REOPEN.getStatusCode() );
                    LogicalExpression logicalExpression = Restrictions.or( statusOpen, statusReopen );
                    criteria.add( logicalExpression );
                }
                else if ( StringUtils.equals( ticket.getStatus(), CRMStatusCode.RESOLVED.getStatusCode() ) )
                {
                    criteria.add( Restrictions.eq( "status", ticket.getStatus() ) );
                    if ( StringUtils.isValidObj( ticket.getSrResolvedOn() ) )
                    {
                        criteria.add( Restrictions.le( "srResolvedOn", ticket.getSrResolvedOn() ) );
                    }
                }
                else
                {
                    criteria.add( Restrictions.eq( "status", ticket.getStatus() ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmSrTicketsPojo.class.getName() );
            inCrmQrcDto.setCrmSrTicketsPojos( criteria.list() );
            serviceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in listTickets ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Fetched Data : " + inCrmQrcDto.getCrmSrTicketsPojos() );
        return serviceCode;
    }

    @Override
    public CRMServiceCode listTicketHistory( CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Inside CrmTicketDaoImpl : -listTicketHistory" );
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        CrmTicketHistoryPojo ticketHistory = inCrmQrcDto.getTicketHistory();
        Session session = null;
        Criteria criteria = null;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmTicketHistoryPojo.class );
            if ( StringUtils.isNotBlank( ticketHistory.getTicketId() ) )
            {
                criteria.add( Restrictions.eq( "ticketId", ticketHistory.getTicketId() ) );
            }
            if ( StringUtils.isValidObj( ticketHistory.getCreatedTime() ) )
            {
                criteria.add( Restrictions.ge( "createdTime", ticketHistory.getCreatedTime() ) );
            }
            if ( StringUtils.isNotBlank( ticketHistory.getAction() ) )
            {
                if ( StringUtils.equals( CrmActionEnum.FOLLOW_UP.getActionCode(), ticketHistory.getAction() ) )
                {
                    List<String> actions = new ArrayList<String>();
                    actions.add( CrmActionEnum.OPENED.getActionCode() );
                    actions.add( CrmActionEnum.FOLLOW_UP.getActionCode() );
                    actions.add( CrmActionEnum.REOPENED.getActionCode() );
                    criteria.add( Restrictions.in( "action", actions ) );
                }
                else
                {
                    criteria.add( Restrictions.eq( "action", ticketHistory.getAction() ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmTicketHistoryPojo.class.getName() );
            inCrmQrcDto.setTicketHistoryList( criteria.list() );
            serviceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in listTicketHistory ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Fetched Data : " + inCrmQrcDto.getTicketHistoryList() );
        return serviceCode;
    }

    @Override
    public CRMServiceCode updateTicket( CrmSrTicketsPojo inTicket )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            else if ( StringUtils.isNotBlank( inTicket.getSrId() ) )
            {
                dbPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId", inTicket.getSrId(), session );
            }
            if ( StringUtils.isValidObj( dbPojo ) )
            {
                if ( inTicket.getSlaTime() > 0 && StringUtils.isValidObj( inTicket.getSlaCalculatedOn() ) )
                {
                    dbPojo.setSlaTime( inTicket.getSlaTime() );
                    dbPojo.setSlaCalculatedOn( inTicket.getSlaCalculatedOn() );
                    transaction.commit();
                    serviceCode = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing updateTicket", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing updateTicket", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, dbPojo.getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while updating Ticket ID/Record ID:" + inTicket.getSrId()
                    + "/" + inTicket.getTicketId() );
        }
        return serviceCode;
    }

    private void expectedOpenSLA( CrmSrTicketsPojo inTicket )
    {
        CrmQrcSubSubCategoriesPojo subSubCat = CRMServiceUtils.getDBValues( CrmQrcSubSubCategoriesPojo.class,
                                                                            inTicket.getQrcSubSubCategoryId() );
        if ( StringUtils.isValidObj( subSubCat ) && StringUtils.isNotBlank( subSubCat.getSlaUnit() )
                && StringUtils.isValidObj( subSubCat.getSla() ) && subSubCat.getSla() > 0 )
        {
            long sla = subSubCat.getSla() * 24 * 60 * 60 * 1000;
            if ( StringUtils.equals( CRMDisplayListConstants.WORKING_HOURS.getCode(), subSubCat.getSlaUnit() ) )
            {
                sla = subSubCat.getSla() * 60 * 60 * 1000;
            }
            Calendar cal = Calendar.getInstance();
            if ( StringUtils.isValidObj( inTicket.getFollowupOn() ) )
            {
                cal.setTime( inTicket.getFollowupOn() );
            }
            SLACalculationUtil slaCalculationUtil = new SLACalculationUtil( getFutureHolidays( true ) );
            Calendar expectedSLA = slaCalculationUtil.calculateExpectedSLA( cal, subSubCat.getSlaUnit(), sla );
            if ( StringUtils.isValidObj( expectedSLA ) )
            {
                inTicket.setExpectedSLATime( expectedSLA.getTime() );
            }
            LOGGER.info( "@SLA Expected Value: " + expectedSLA + " for: " + inTicket.getSrId() );
        }
        else
        {
            LOGGER.info( "@SLA not required on Ticket: " + inTicket.getSrId() );
        }
        if ( StringUtils.isValidObj( subSubCat ) )
        {
            inTicket.setFunctionalbinId( subSubCat.getFunctionalBinId() );
        }
    }

    private void expectedReopenSLA( CrmSrTicketsPojo inTicket )
    {
        if ( StringUtils.isValidObj( inTicket ) )
        {
            if ( StringUtils.isValidObj( inTicket.getExpectedSLATime() )
                    && StringUtils.isValidObj( inTicket.getSrResolvedOn() ) )
            {
                if ( inTicket.getExpectedSLATime().after( inTicket.getSrResolvedOn() ) )
                {
                    CrmQrcSubSubCategoriesPojo subSubCat = CRMServiceUtils
                            .getDBValues( CrmQrcSubSubCategoriesPojo.class, inTicket.getQrcSubSubCategoryId() );
                    if ( StringUtils.isValidObj( subSubCat ) && StringUtils.isNotBlank( subSubCat.getSlaUnit() )
                            && StringUtils.isValidObj( subSubCat.getSla() ) && subSubCat.getSla() > 0 )
                    {
                        long sla = subSubCat.getSla() * 24 * 60 * 60 * 1000;
                        if ( StringUtils.equals( CRMDisplayListConstants.WORKING_HOURS.getCode(),
                                                 subSubCat.getSlaUnit() ) )
                        {
                            sla = subSubCat.getSla() * 60 * 60 * 1000;
                        }
                        if ( sla > 0 && StringUtils.numericValue( inTicket.getSlaTime() + "" ) > 0 )
                        {
                            long diff = sla - inTicket.getSlaTime();
                            if ( diff > 0 )
                            {
                                Calendar cal = Calendar.getInstance();
                                if ( StringUtils.isValidObj( inTicket.getFollowupOn() ) )
                                {
                                    cal.setTime( inTicket.getFollowupOn() );
                                }
                                SLACalculationUtil slaCalculationUtil = new SLACalculationUtil( getFutureHolidays( true ) );
                                Calendar expectedSLA = slaCalculationUtil.calculateExpectedSLA( cal,
                                                                                                subSubCat.getSlaUnit(),
                                                                                                diff );
                                if ( StringUtils.isValidObj( expectedSLA ) )
                                {
                                    inTicket.setExpectedSLATime( expectedSLA.getTime() );
                                }
                                LOGGER.info( "@SLA Expected Value: " + expectedSLA.getTime() + " for: "
                                        + inTicket.getSrId() );
                            }
                        }
                        else
                        {
                            LOGGER.info( "@SLA not required on Ticket: " + inTicket.getSrId() );
                        }
                    }
                    else
                    {
                        LOGGER.info( "@SLA not required on Ticket: " + inTicket.getSrId() );
                    }
                }
                else
                {
                    LOGGER.info( "@SLA Ticket: " + inTicket.getSrId() + " is already out of SLA." );
                }
            }
            else
            {
                expectedOpenSLA( inTicket );
            }
        }
        else
        {
            throw new IllegalArgumentException( "Ticket object not found" );
        }
    }

    private void expectedFollwUpSLA( CrmSrTicketsPojo inTicket, Date inFollowupOn )
    {
        if ( StringUtils.isValidObj( inFollowupOn ) )
        {
            SLACalculationUtil slaCalculationUtil = new SLACalculationUtil( getFutureHolidays( true ) );
            CrmQrcSubSubCategoriesPojo subSubCat = CRMServiceUtils.getDBValues( CrmQrcSubSubCategoriesPojo.class,
                                                                                inTicket.getQrcSubSubCategoryId() );
            if ( StringUtils.isValidObj( subSubCat ) && StringUtils.isNotBlank( subSubCat.getSlaUnit() )
                    && StringUtils.isValidObj( subSubCat.getSla() ) && subSubCat.getSla() > 0 )
            {
                Calendar cal = Calendar.getInstance();
                Calendar expectedSLA = null;
                if ( inFollowupOn.before( cal.getTime() ) || inFollowupOn.equals( cal.getTime() ) )
                {
                    expectedSLA = slaCalculationUtil.forFollowUpEnd( DateUtils.getCalendarFromDate( inTicket
                            .getFollowupOn() ), DateUtils.getCalendarFromDate( inFollowupOn ), DateUtils
                            .getCalendarFromDate( inTicket.getExpectedSLATime() ), subSubCat.getSlaUnit() );
                }
                else if ( inFollowupOn.after( cal.getTime() ) )
                {
                    expectedSLA = slaCalculationUtil.forFollowUpCreated( cal, DateUtils
                            .getCalendarFromDate( inFollowupOn ), DateUtils.getCalendarFromDate( inTicket
                            .getExpectedSLATime() ), subSubCat.getSlaUnit() );
                }
                if ( StringUtils.isValidObj( expectedSLA ) )
                {
                    inTicket.setExpectedSLATime( expectedSLA.getTime() );
                }
                LOGGER.info( "@SLA Expected Value: " + expectedSLA + " for: " + inTicket.getSrId() );
            }
        }
        else
        {
            LOGGER.info( "@SLA not required on Ticket: " + inTicket.getSrId() );
        }
    }

    private void finalSLAHours( CrmSrTicketsPojo inTicket )
    {
        CrmQrcSubSubCategoriesPojo subSubCat = CRMServiceUtils.getDBValues( CrmQrcSubSubCategoriesPojo.class,
                                                                            inTicket.getQrcSubSubCategoryId() );
        if ( StringUtils.isValidObj( subSubCat ) && StringUtils.isNotBlank( subSubCat.getSlaUnit() )
                && StringUtils.isValidObj( subSubCat.getSla() ) && subSubCat.getSla() > 0 )
        {
            Calendar calculatedOn = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            if ( StringUtils.isValidObj( inTicket.getSrResolvedOn() ) )
            {
                long EOC_RB_ID = getEOCResolvedFB();
                if ( EOC_RB_ID == inTicket.getFunctionalbinId() )
                {
                    now.setTime( now.getTime() );
                }
                else
                {
                    now.setTime( inTicket.getSrResolvedOn() );
                }
            }
            else
            {
                now.setTime( now.getTime() );
            }
            if ( StringUtils.isValidObj( inTicket.getSlaCalculatedOn() ) )
            {
                calculatedOn.setTime( inTicket.getSlaCalculatedOn() );
            }
            else
                calculatedOn.setTime( calculatedOn.getTime() );
            SLACalculationUtil slaCalculationUtil = new SLACalculationUtil( getFutureHolidays( true ) );
            long sla = 0l;
            if ( StringUtils.equals( CRMDisplayListConstants.WORKING_HOURS.getCode(), subSubCat.getSlaUnit() ) )
            {
                sla = slaCalculationUtil.getHoursForWorkingHours( calculatedOn, now );
            }
            else if ( StringUtils.equals( CRMDisplayListConstants.WORKING_DAYS.getCode(), subSubCat.getSlaUnit() ) )
            {
                sla = slaCalculationUtil.getHoursForDays( calculatedOn, now, true );
            }
            else
            {
                sla = slaCalculationUtil.getHoursForDays( calculatedOn, now, false );
            }
            if ( StringUtils.isValidObj( inTicket.getSlaTime() ) )
            {
                inTicket.setSlaTime( inTicket.getSlaTime() + sla );
                inTicket.setSlaCalculatedOn( now.getTime() );
            }
            else
            {
                inTicket.setSlaTime( sla );
            }
            LOGGER.info( "@SLA Final Value: " + inTicket.getSlaTime() + " for: " + inTicket.getSrId() );
        }
        else
        {
            LOGGER.info( "@SLA not required on Ticket: " + inTicket.getSrId() );
        }
    }

    @Override
    public CRMServiceCode reopenedTicket( CrmQrcDto inCrmQrcDto, CrmTicketHistoryPojo inTicketHistory )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmSrTicketsPojo dbPojo = null;
        CrmSrTicketsPojo inTicket = inCrmQrcDto.getCrmSrTicketsPojo();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( inTicket.getTicketId() > 0 )
            {
                dbPojo = (CrmSrTicketsPojo) session.get( CrmSrTicketsPojo.class, inTicket.getTicketId() );
            }
            if ( StringUtils.isValidObj( dbPojo ) )
            {
                if ( dbPojo.getResolutionType() == CrmTicketActions.FORWARD.getCode() )
                {
                    Calendar now = Calendar.getInstance();
                    if ( StringUtils.equals( dbPojo.getStatus(), CRMStatusCode.RESOLVED.getStatusCode() ) )
                    {
                        inTicketHistory.setOldFollowupOn( dbPojo.getFollowupOn() );
                        dbPojo.setFollowupOn( now.getTime() );
                        dbPojo.setLastModifiedBy( inTicket.getCreatedBy() );
                        dbPojo.setLastModifiedTime( now.getTime() );
                        dbPojo.setSrReopenedOn( now.getTime() );
                        dbPojo.setSlaCalculatedOn( now.getTime() );
                        dbPojo.setStatus( CRMStatusCode.REOPEN.getStatusCode() );
                        long EOC_RB_ID = getEOCResolvedFB();
                        if ( EOC_RB_ID != dbPojo.getFunctionalbinId() )
                        {
                            expectedReopenSLA( dbPojo );
                        }
                        session.merge( dbPojo );
                        inTicketHistory.setNewFollowupOn( dbPojo.getFollowupOn() );
                        inTicketHistory.setAction( CrmActionEnum.REOPENED.getActionCode() );
                        inTicketHistory.setTicketId( dbPojo.getSrId() );
                        inTicketHistory.setCreatedBy( inTicket.getCreatedBy() );
                        inTicket = dbPojo;
                        inCrmQrcDto.setCrmSrTicketsPojo( dbPojo );
                        serviceCode = CRMServiceCode.CRM001;
                    }
                }
            }
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                if ( CrmTicketActions.FORWARD.getCode() == inTicket.getResolutionType() )
                {
                    serviceCode = activateInbox( CRMRequestType.QRC, inTicket.getSrId(), session,
                                                 inTicket.getCreatedBy(), inTicket.getFunctionalbinId() + "", null,
                                                 null );
                }
            }
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                serviceCode = saveTicketHistory( inTicketHistory, session );
            }
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while executing reopen ticket", ex );
            serviceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            serviceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Exception while executing reopen ticket", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( serviceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmSrTicketsPojo.class.getName(), null, inCrmQrcDto.getCrmSrTicketsPojo()
                        .getTicketId() );
            }
            LOGGER.info( serviceCode.getStatusDesc() + " while reopening Ticket ID/Record ID:" + inTicket.getSrId()
                    + "/" + inTicket.getTicketId() );
        }
        return serviceCode;
    }
}
