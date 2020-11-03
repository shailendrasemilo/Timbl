package com.np.tele.crm.masterdata.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.pojos.CrmNpMobilePojo;
import com.np.tele.crm.pojos.CrmPartnerDetailsPojo;
import com.np.tele.crm.pojos.CrmPartnerMappingPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class BussinessPartnerMgtDao
    implements IBussinessPartnerMgtDao
{
    private static final Logger LOGGER = Logger.getLogger( BussinessPartnerMgtDao.class );

    @Override
    public MasterDataDto createUpdatePartner( MasterDataDto inMasterDataDto )
    {
        PartnerPojo partnerPojo = inMasterDataDto.getPartnerPojo();
        partnerPojo.setPartnerCen( StringUtils.upperCase( partnerPojo.getPartnerCen() ) );
        partnerPojo.setDesignation( StringUtils.upperCase( partnerPojo.getDesignation() ) );
        partnerPojo.setHoCpn( StringUtils.upperCase( partnerPojo.getHoCpn() ) );
        partnerPojo.setAddress( StringUtils.upperCase( partnerPojo.getAddress() ) );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        Criteria criteria = null;
        Long id = 0l;
        SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
        String inBoardedDate = null;
        CRMEvents events = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            LOGGER.info( "Partner Pojo:" + inMasterDataDto.getPartnerPojo() );
            if ( inMasterDataDto.getPartnerPojo().getPartnerId() > 0 )
            {
                PartnerPojo dbPartnerPojo = (PartnerPojo) session.get( PartnerPojo.class, inMasterDataDto
                        .getPartnerPojo().getPartnerId() );
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                if ( CRMServiceUtils.isValueModified( dbPartnerPojo, inMasterDataDto.getPartnerPojo(), "partnerCen" ) )
                {
                    oldValues.append( "CEN=" ).append( dbPartnerPojo.getPartnerCen() ).append( IAppConstants.COMMA );
                    newValues.append( "CEN=" ).append( inMasterDataDto.getPartnerPojo().getPartnerCen() )
                            .append( IAppConstants.COMMA );
                }
                if ( CRMServiceUtils.isValueModified( dbPartnerPojo, inMasterDataDto.getPartnerPojo(), "emailId" ) )
                {
                    oldValues.append( "Email=" ).append( dbPartnerPojo.getEmailId() ).append( IAppConstants.COMMA );
                    newValues.append( "Email=" ).append( inMasterDataDto.getPartnerPojo().getEmailId() )
                            .append( IAppConstants.COMMA );
                }
                if ( CRMServiceUtils.isValueModified( dbPartnerPojo, inMasterDataDto.getPartnerPojo(), "mobileNo" ) )
                {
                    oldValues.append( "Mobile=" ).append( dbPartnerPojo.getMobileNo() ).append( IAppConstants.COMMA );
                    newValues.append( "Mobile=" ).append( inMasterDataDto.getPartnerPojo().getMobileNo() )
                            .append( IAppConstants.COMMA );
                }
                if ( CRMServiceUtils.isValueModified( dbPartnerPojo, inMasterDataDto.getPartnerPojo(), "hoEmailId" ) )
                {
                    oldValues.append( "HO Email=" ).append( dbPartnerPojo.getHoEmailId() ).append( IAppConstants.COMMA );
                    newValues.append( "HO Email=" ).append( inMasterDataDto.getPartnerPojo().getHoEmailId() )
                            .append( IAppConstants.COMMA );
                }
                if ( CRMServiceUtils.isValueModified( dbPartnerPojo, inMasterDataDto.getPartnerPojo(), "hoMobileNo" ) )
                {
                    oldValues.append( "HO Mobile=" ).append( dbPartnerPojo.getHoMobileNo() )
                            .append( IAppConstants.COMMA );
                    newValues.append( "HO Mobile=" ).append( inMasterDataDto.getPartnerPojo().getHoMobileNo() )
                            .append( IAppConstants.COMMA );
                }
                if ( CRMServiceUtils.isValueModified( dbPartnerPojo, inMasterDataDto.getPartnerPojo(), "currentStatus" ) )
                {
                    oldValues.append( "Status=" ).append( CRMStatusCode.getStatus( dbPartnerPojo.getCurrentStatus() ) )
                            .append( IAppConstants.COMMA );
                    newValues.append( "Status=" )
                            .append( CRMStatusCode.getStatus( inMasterDataDto.getPartnerPojo().getCurrentStatus() ) )
                            .append( IAppConstants.COMMA );
                }
                if ( StringUtils.isValidObj( dbPartnerPojo ) )
                {
                    inMasterDataDto.getPartnerPojo().setLastModifiedTime( Calendar.getInstance().getTime() );
                    org.apache.commons.beanutils.PropertyUtils.copyProperties( dbPartnerPojo,
                                                                               inMasterDataDto.getPartnerPojo() );
                    for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : dbPartnerPojo.getCrmPartnerDetailses() )
                    {
                        crmPartnerDetailsPojo.setPartner( inMasterDataDto.getPartnerPojo() );
                        if ( crmPartnerDetailsPojo.getRecordId() > 0 )
                        {
                            crmPartnerDetailsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        }
                        LOGGER.info( "Crm Partner Details object value: " + crmPartnerDetailsPojo );
                    }
                    session.merge( dbPartnerPojo );
                    LOGGER.info( "Bussiness Partner Updated Successfully" );
                    crmServiceCode = CRMServiceCode.CRM001;
                    LOGGER.info( "Going to save PartnerDetailsMapping" );
                }
                String mappingId = String.valueOf( inMasterDataDto.getPartnerPojo().getPartnerId() );
                String createdBy = inMasterDataDto.getPartnerPojo().getCreatedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH, CrmActionEnum.UPDATED
                                                                              .getActionCode(),
                                                                      CRMRequestType.PARTNER_MANAGEMENT
                                                                              .getRequestCode(), inMasterDataDto
                                                                              .getClientIPAddress(), inMasterDataDto
                                                                              .getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log for update partner " );
                }
                events = CRMEvents.UPDATE_PARTNER;
                id = StringUtils.numericValue( mappingId );
            }
            else
            {
                criteria = session.createCriteria( PartnerPojo.class );
                criteria.add( Restrictions.eq( "partnerName", inMasterDataDto.getPartnerPojo().getPartnerName() ) );
                /*
                 * criteria.add( Restrictions.eq(
                 * "bussinessType",partnerPojo.getBussinessType()) );
                 * criteria.add( Restrictions.eq( "partnerType",
                 * partnerPojo.getPartnerType()) );
                 */
                criteria.setCacheable( true );
                criteria.setCacheRegion( PartnerPojo.class.getName() );
                List<PartnerPojo> partnerPojos = criteria.list();
                if ( partnerPojos.size() > 0 )
                {
                    LOGGER.info( "Partner Name Already Exist :: " + inMasterDataDto.getPartnerPojo().getPartnerName() );
                    if ( CRMServiceCode.CRM001 != crmServiceCode )
                    {
                        crmServiceCode = CRMServiceCode.CRM060;
                    }
                }
                else
                {
                    LOGGER.info( "Crm Partner Details Partner List Size::: "
                            + inMasterDataDto.getPartnerPojo().getCrmPartnerDetailses().size() );
                    inBoardedDate = inMasterDataDto.getPartnerPojo().getStrBoardedDate();
                    inBoardedDate += IAppConstants.START_TIME;
                    Date boardedDate = formatter.parse( inBoardedDate );
                    LOGGER.info( "After Formatting: Boarded Date: " + boardedDate );
                    inMasterDataDto.getPartnerPojo().setPartnerShortName( StringUtils.normalize( inMasterDataDto
                                                                                  .getPartnerPojo().getPartnerName() ) );
                    inMasterDataDto.getPartnerPojo().setBoardedDate( boardedDate );
                    inMasterDataDto.getPartnerPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : inMasterDataDto.getPartnerPojo()
                            .getCrmPartnerDetailses() )
                    {
                        crmPartnerDetailsPojo.setPartner( inMasterDataDto.getPartnerPojo() );
                        crmPartnerDetailsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        LOGGER.info( "Crm Partner Details object value: " + crmPartnerDetailsPojo );
                    }
                    id = (Long) session.save( inMasterDataDto.getPartnerPojo() );
                    if ( id > 0 )
                    {
                        LOGGER.info( "Bussiness Partner Inserted Successfully. Generated Id :: " + id );
                        crmServiceCode = CRMServiceCode.CRM001;
                    }
                    else
                    {
                        LOGGER.info( "Required Details are not present for creating Bussiness Partner:" );
                        if ( CRMServiceCode.CRM001 != crmServiceCode )
                        {
                            crmServiceCode = CRMServiceCode.CRM997;
                        }
                    }
                }
                // for auditLog Maintain
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                newValues.append( "Partner Name=" ).append( inMasterDataDto.getPartnerPojo().getPartnerName() )
                        .append( IAppConstants.COMMA );
                String mappingId = String.valueOf( inMasterDataDto.getPartnerPojo().getPartnerId() );
                String createdBy = inMasterDataDto.getPartnerPojo().getCreatedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH, CrmActionEnum.GENERATED
                                                                              .getActionCode(),
                                                                      CRMRequestType.PARTNER_MANAGEMENT
                                                                              .getRequestCode(), inMasterDataDto
                                                                              .getClientIPAddress(), inMasterDataDto
                                                                              .getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log for create partner" );
                }
                events = CRMEvents.CREATE_PARTNER;
            }
            transaction.commit();
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmServiceCode.getStatusCode() ) )
            {
                LOGGER.info( "Going to send mail " + events.getEventName() );
                CRMServiceUtils.sendAlerts( events, CRMRequestType.PARTNER_MANAGEMENT, id.toString(), null );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Create Bussiness Partner method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while Create Bussiness Partner method ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( PartnerPojo.class.getName(), null, null );
                HibernateUtil.evictAll( CrmPartnerDetailsPojo.class.getName(), null, null );
            }
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    //    private void savePartnerDetailsMapping( MasterDataDto inMasterDataDto, Session inSession )
    //    {
    //        if ( StringUtils.isValidObj( inMasterDataDto.getPartnerPojo().getCrmPartnerDetailses() )
    //                && !inMasterDataDto.getPartnerPojo().getCrmPartnerDetailses().isEmpty() )
    //        {
    //            for ( CrmPartnerDetailsPojo partnerDetailsPojo : inMasterDataDto.getPartnerPojo().getCrmPartnerDetailses() )
    //            {
    //                if ( !StringUtils.isValidObj( partnerDetailsPojo.getPartner() ) )
    //                    partnerDetailsPojo.setPartner( inMasterDataDto.getPartnerPojo() );
    //                inSession.saveOrUpdate( partnerDetailsPojo );
    //                // LOGGER.info(
    //                // "Successfully Inserted CrmPartnerDetailsPojos, Generated Id :: "
    //                // + id );
    //            }
    //        }
    //        else
    //            LOGGER.info( "No change found in partner details mapping" );
    //    }
    @Override
    public MasterDataDto listOFBussinessPartner( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        PartnerPojo partnerPojo = inMasterDataDto.getPartnerPojo();
        List<PartnerPojo> partnerPojos = new ArrayList<PartnerPojo>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( PartnerPojo.class );
            if ( StringUtils.isValidObj( inMasterDataDto.getFromDate() ) )
            {
                String start = IAppConstants.START_TIME;
                String end = IAppConstants.END_TIME;
                String inFromDate = inMasterDataDto.getFromDate();
                LOGGER.info( "Before Parsing: From Date: " + inFromDate );
                inFromDate += start;
                SimpleDateFormat formatter = new SimpleDateFormat( "dd-MMM-yyyy HH:mm:ss" );
                Date fromDate = formatter.parse( inFromDate );
                Date toDate = Calendar.getInstance().getTime();
                if ( StringUtils.isNotBlank( inMasterDataDto.getToDate() ) )
                {
                    String inToDate = inMasterDataDto.getToDate();
                    inToDate += end;
                    toDate = formatter.parse( inToDate );
                }
                LOGGER.info( "After Formatting: From Date: " + fromDate + " and To Date: " + toDate );
                criteria.add( Restrictions.between( "boardedDate", fromDate, toDate ) );
                criteria.addOrder( Order.asc( "boardedDate" ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getCurrentStatus() ) )
            {
                criteria.add( Restrictions.eq( "currentStatus", partnerPojo.getCurrentStatus() ) );
            }
            //            if ( StringUtils.isNotEmpty( partnerPojo.getAddedErp() ) )
            //            {
            //                criteria.add( Restrictions.eq( "addedErp", partnerPojo.getAddedErp() ) );
            //            }
            if ( partnerPojo.getPartnerId() > 0 )
            {
                criteria.add( Restrictions.eq( "partnerId", partnerPojo.getPartnerId() ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getPartnerName() ) )
            {
                criteria.add( Restrictions.ilike( "partnerName", partnerPojo.getPartnerName(), MatchMode.ANYWHERE ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getState() ) )
            {
                criteria.add( Restrictions.eq( "state", partnerPojo.getState() ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getCity() ) )
            {
                criteria.add( Restrictions.eq( "city", partnerPojo.getCity() ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getPartnerType() )
                    || StringUtils.isNotEmpty( partnerPojo.getBussinessType() ) )
            {
                criteria.createCriteria( "crmPartnerDetailses", "PDM" );
                if ( StringUtils.isNotEmpty( partnerPojo.getCurrentStatus() ) )
                {
                    criteria.add( Restrictions.eq( "PDM.status", partnerPojo.getCurrentStatus() ) );
                }
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getPartnerType() ) )
            {
                criteria.add( Restrictions.eq( "PDM.partnerType", partnerPojo.getPartnerType() ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getBussinessType() ) )
            {
                criteria.add( Restrictions.eq( "PDM.bussinessType", partnerPojo.getBussinessType() ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getPartnerShortName() ) )
            {
                criteria.add( Restrictions.ilike( "partnerShortName", partnerPojo.getPartnerShortName(),
                                                  MatchMode.ANYWHERE ) );
            }
            if ( StringUtils.isNotEmpty( partnerPojo.getCrmPartnerCode() ) )
            {
                criteria.add( Restrictions.ilike( "crmPartnerCode", partnerPojo.getCrmPartnerCode(), MatchMode.ANYWHERE ) );
            }
            criteria.addOrder( Order.desc( "lastModifiedTime" ) );
            criteria.addOrder( Order.asc( "partnerName" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( PartnerPojo.class.getName() );
            partnerPojos = criteria.list();
            if ( StringUtils.isValidObj( partnerPojos ) )
            {
                Set<PartnerPojo> paSet = new HashSet<PartnerPojo>( partnerPojos );
                if ( !StringUtils.isValidObj( inMasterDataDto.getPartnerPojoList() ) )
                {
                    List<PartnerPojo> partnerPojoList = new ArrayList<PartnerPojo>( paSet );
                    inMasterDataDto.setPartnerPojoList( partnerPojoList );
                }
                else
                    inMasterDataDto.getPartnerPojoList().addAll( paSet );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.info( "Getting Error while Listing Bussiness Partner method ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto crmPartnerMapping( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inMasterDataDto.getCrmPartnerMappingList() ) )
            {
                for ( CrmPartnerMappingPojo crmPartnerMappingPojo : inMasterDataDto.getCrmPartnerMappingList() )
                {
                    if ( crmPartnerMappingPojo.isEditable() )
                    {
                        CrmPartnerMappingPojo crmPartnerMapPojoDb = null;
                        criteria = session.createCriteria( CrmPartnerMappingPojo.class );
                        criteria.add( Restrictions.eq( "recordId", crmPartnerMappingPojo.getRecordId() ) );
                        criteria.setCacheable( true );
                        criteria.setCacheRegion( CrmPartnerMappingPojo.class.getName() );
                        List<CrmPartnerMappingPojo> partnerMappingList = criteria.list();
                        if ( StringUtils.isValidObj( partnerMappingList ) && !partnerMappingList.isEmpty() )
                        {
                            crmPartnerMapPojoDb = (CrmPartnerMappingPojo) partnerMappingList.get( 0 );
                            if ( StringUtils.isValidObj( crmPartnerMapPojoDb ) )
                            {
                                crmPartnerMapPojoDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                                crmPartnerMapPojoDb.setLastModifiedBy( crmPartnerMappingPojo.getLastModifiedBy() );
                                crmPartnerMapPojoDb.setStatus( crmPartnerMappingPojo.getStatus() );
                                session.merge( crmPartnerMapPojoDb );
                            }
                        }
                        else
                        {
                            crmPartnerMappingPojo.setCreatedTime( Calendar.getInstance().getTime() );
                            session.save( crmPartnerMappingPojo );
                        }
                    }
                }
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Create Partner mapping method", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while Create Partner mapping method ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmPartnerMappingPojo.class.getName(), null, null );
            }
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto listOFPartnerMapping( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        List<CrmPartnerMappingPojo> partnerMappingList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            partnerMappingList = new ArrayList<CrmPartnerMappingPojo>();
            criteria = session.createCriteria( CrmPartnerMappingPojo.class );
            if ( StringUtils.isValidObj( inMasterDataDto.getCrmPartnerMappingPojo() ) )
            {
                if ( StringUtils.isNotBlank( inMasterDataDto.getCrmPartnerMappingPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inMasterDataDto.getCrmPartnerMappingPojo().getStatus() ) );
                }
                if ( StringUtils.isValidObj( inMasterDataDto.getCrmPartnerMappingPojo().getPartnerByNpId() ) )
                {
                    criteria.createCriteria( "partnerByNpId", "partner" );
                    criteria.add( Restrictions.eq( "partner.partnerId", inMasterDataDto.getCrmPartnerMappingPojo()
                            .getPartnerByNpId().getPartnerId() ) );
                }
                if ( StringUtils.isValidObj( inMasterDataDto.getCrmPartnerMappingPojo().getPartnerBySpId() ) )
                {
                    criteria.createCriteria( "partnerBySpId", "partner" );
                    criteria.add( Restrictions.eq( "partner.partnerId", inMasterDataDto.getCrmPartnerMappingPojo()
                            .getPartnerBySpId().getPartnerId() ) );
                }
                if ( StringUtils.isNotBlank( inMasterDataDto.getCrmPartnerMappingPojo().getProduct() ) )
                {
                    criteria.add( Restrictions.eq( "product", inMasterDataDto.getCrmPartnerMappingPojo().getProduct() ) );
                }
            }
            else
            {
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPartnerMappingPojo.class.getName() );
            partnerMappingList = criteria.list();
            if ( StringUtils.isValidObj( partnerMappingList ) )
            {
                inMasterDataDto.setCrmPartnerMappingList( partnerMappingList );
                LOGGER.info( "Size of Partner Mapping List:- " + partnerMappingList.size() );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while list of partner mapping : ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    /*
     * public static void main( String[] args ) throws Exception { String start
     * = IAppConstants.START_TIME; String inFromDate =
     * "2013-09-03T00:00:00+05:30"; inFromDate += start; LOGGER.info(
     * "After Formatting: From Date: " + inFromDate ); SimpleDateFormat
     * formatter = new SimpleDateFormat( "dd-MM-yyyy hh:mm:ss" ); Date fromDate
     * = formatter.parse( inFromDate ); System.out.println( fromDate ); }
     */
    @Override
    public MasterDataDto listOFPartnerDetailMapping( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        List<CrmPartnerDetailsPojo> partnerMappingList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            partnerMappingList = new ArrayList<CrmPartnerDetailsPojo>();
            criteria = session.createCriteria( CrmPartnerDetailsPojo.class );
            if ( StringUtils.isValidObj( inMasterDataDto.getPartnerPojo() ) )
            {
                if ( inMasterDataDto.getPartnerPojo().getPartnerId() > 0 )
                {
                    criteria.add( Restrictions.eq( "partner", inMasterDataDto.getPartnerPojo() ) );
                }
                if ( StringUtils.isNotBlank( inMasterDataDto.getPartnerPojo().getBussinessType() ) )
                {
                    criteria.add( Restrictions
                            .eq( "bussinessType", inMasterDataDto.getPartnerPojo().getBussinessType() ) );
                }
                if ( StringUtils.isNotBlank( inMasterDataDto.getPartnerPojo().getPartnerType() ) )
                {
                    criteria.add( Restrictions.eq( "partnerType", inMasterDataDto.getPartnerPojo().getPartnerType() ) );
                }
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPartnerDetailsPojo.class.getName() );
            partnerMappingList = criteria.list();
            if ( CommonValidator.isValidCollection( partnerMappingList ) )
            {
                inMasterDataDto.setCrmPartnerDetailsPojos( partnerMappingList );
                LOGGER.info( "Size of Partner Mapping List:- " + partnerMappingList.size() );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while list of partner mapping : ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto saveMultipleMobileWithNP( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inMasterDataDto.getCrmNpMobilePojos() ) )
            {
                for ( CrmNpMobilePojo npMobilePojo : inMasterDataDto.getCrmNpMobilePojos() )
                {
                    LOGGER.info( "Mobile NO:" + npMobilePojo.getMobileNo() + "Record ID:=" + npMobilePojo.getRecordId() );
                    if ( npMobilePojo.isEditable() )
                    {
                        CrmNpMobilePojo npMobilePojoDb = (CrmNpMobilePojo) session.get( CrmNpMobilePojo.class,
                                                                                        npMobilePojo.getRecordId() );
                        if ( StringUtils.isValidObj( npMobilePojoDb ) )
                        {
                            LOGGER.info( "DB Call" );
                            npMobilePojoDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                            npMobilePojoDb.setLastModifiedBy( npMobilePojo.getLastModifiedBy() );
                            npMobilePojoDb.setStatus( npMobilePojo.getStatus() );
                            if ( npMobilePojo.getGenericType().equals( IAppConstants.EVENT ) )
                            {
                                npMobilePojoDb.setMobileNo( npMobilePojo.getMobileNo() );
                                npMobilePojoDb.setEmailId( npMobilePojo.getEmailId() );
                            }
                            session.merge( npMobilePojoDb );
                        }
                        else
                        {
                            LOGGER.info( "Fresh Call" );
                            npMobilePojo.setCreatedTime( Calendar.getInstance().getTime() );
                            session.save( npMobilePojo );
                        }
                    }
                    LOGGER.info( "POJO:" + npMobilePojo );
                }
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Error while  saveMultipleMobileWithNP method", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while  saveMultipleMobileWithNP method", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto getNpMobileList( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        List<CrmNpMobilePojo> crmNpMobileList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            crmNpMobileList = new ArrayList<CrmNpMobilePojo>();
            criteria = session.createCriteria( CrmNpMobilePojo.class );
            if ( StringUtils.isValidObj( inMasterDataDto.getCrmNpMobilePojo() ) )
            {
                if ( StringUtils.isValidObj( inMasterDataDto.getCrmNpMobilePojo().getGenericType() )
                        && StringUtils.equals( IAppConstants.EVENT, inMasterDataDto.getCrmNpMobilePojo()
                                .getGenericType() ) )
                {
                    if ( StringUtils.isValidObj( inMasterDataDto.getCrmNpMobilePojo().getEventId() ) )
                    {
                        criteria.add( Restrictions.eq( "eventId", inMasterDataDto.getCrmNpMobilePojo().getEventId() ) );
                    }
                    if ( StringUtils.isNotEmpty( inMasterDataDto.getCrmNpMobilePojo().getGenericType() ) )
                    {
                        criteria.add( Restrictions.eq( "genericType", inMasterDataDto.getCrmNpMobilePojo()
                                .getGenericType() ) );
                    }
                    if ( StringUtils.isNotEmpty( inMasterDataDto.getCrmNpMobilePojo().getStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                    }
                }
                else
                {
                    LOGGER.info( "NP ID::" + inMasterDataDto.getCrmNpMobilePojo().getNpId() );
                    if ( StringUtils.isValidObj( inMasterDataDto.getCrmNpMobilePojo().getNpId() ) )
                    {
                        criteria.add( Restrictions.eq( "npId", inMasterDataDto.getCrmNpMobilePojo().getNpId() ) );
                        criteria.add( Restrictions.eq( "genericType", IAppConstants.PARTNER ) );
                    }
                }
            }
            else
            {
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.add( Restrictions.eq( "genericType", IAppConstants.PARTNER ) );
            }
            crmNpMobileList = criteria.list();
            if ( CommonValidator.isValidCollection( crmNpMobileList ) )
            {
                inMasterDataDto.setCrmNpMobilePojos( crmNpMobileList );
                LOGGER.info( "Size of NpMobilePojo::- " + crmNpMobileList.size() );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while list of getNpMobileList : ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }
}
