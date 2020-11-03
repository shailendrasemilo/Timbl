package com.np.tele.crm.lms.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.config.service.ICrmConfigService;
import com.np.tele.crm.constants.CRMActionConstants;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmuserDetailsDto;
import com.np.tele.crm.dto.LMSDto;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.masterdata.dao.MasterDataDaoImpl;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmParameterPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.LmsCrfMappingPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;
import com.np.tele.crm.pojos.UserRolesPojo;
import com.np.tele.crm.usrmngmnt.service.IUserManagementService;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.PropertyUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class LMSOperationDaoImpl
    implements ILMSOperationDao
{
    private static final Logger LOGGER = Logger.getLogger( LMSOperationDaoImpl.class );

    @Override
    public LMSDto createLead( LMSDto inLMSDto )
    {
        LOGGER.info( "Inside lmsOperation" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        boolean saveAppointMent = false;
        boolean saveRemarks = false;
        String remarks = null;
        String leadStage = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Query query = null;
            String areaId = null;
            List<LMSPojo> rejectedPojos = new ArrayList<LMSPojo>();
            List<LMSPojo> successPojos = new ArrayList<LMSPojo>();
            if ( StringUtils.isValidObj( inLMSDto.getAppointmentPojo() )
                    && StringUtils.isNotBlank( inLMSDto.getAppointmentPojo().getDisplayDate() ) )
            {
                saveAppointMent = true;
            }
            if ( StringUtils.isValidObj( inLMSDto.getRemarksPojo() )
                    && StringUtils.isNotBlank( inLMSDto.getRemarksPojo().getRemarks() ) )
            {
                remarks = inLMSDto.getRemarksPojo().getRemarks();
                saveRemarks = true;
            }
            //LOGGER.info( "Size of pojos" + inLMSDto.getLeadPojos().size() + " values  ::" + inLMSDto.getLeadPojos() );
            for ( LMSPojo lmsPojo : inLMSDto.getLeadPojos() )
            {
                LOGGER.info( "contact no in service" + lmsPojo.getContactNumber() + " :: Source ::"
                        + lmsPojo.getLeadSource() );
                LOGGER.info( "Lead Object Property :: " + lmsPojo );
                lmsPojo.setCustomerName( StringUtils.upperCase( lmsPojo.getCustomerName() ) );
                lmsPojo.setArea( StringUtils.upperCase( lmsPojo.getArea() ) );
                lmsPojo.setLocality( StringUtils.upperCase( lmsPojo.getLocality() ) );
                lmsPojo.setHouseNumber( StringUtils.upperCase( lmsPojo.getHouseNumber() ) );
                lmsPojo.setLandmark( StringUtils.upperCase( lmsPojo.getLandmark() ) );
                query = session.getNamedQuery( "AreaNameByAreaId" );
                query.setString( "STATE", lmsPojo.getState() );
                query.setString( "CITY", lmsPojo.getCity() );
                query.setString( "AREA", lmsPojo.getArea() );
                query.setResultTransformer( Transformers.aliasToBean( AreaPojo.class ) );
                List<AreaPojo> areaPojos = query.list();
                if ( CommonValidator.isValidCollection( areaPojos ) )
                {
                    areaId = areaPojos.get( 0 ).getArea();
                }
                if ( ( ( String.valueOf( lmsPojo.getContactNumber() ).trim().length() >= 10 && StringUtils
                        .isNumeric( String.valueOf( lmsPojo.getContactNumber() ) ) ) || ( String
                        .valueOf( lmsPojo.getCtiNumber() ).trim().length() >= 10 && StringUtils.isNumeric( String
                        .valueOf( lmsPojo.getCtiNumber() ) ) ) )
                        && ( StringUtils.isValidObj( lmsPojo.getLeadSource() ) && lmsPojo.getLeadSource()
                                .matches( CRMDisplayListConstants.WEBSITE.getCode() + "|"
                                                  + CRMDisplayListConstants.SMS.getCode() + "|"
                                                  + CRMDisplayListConstants.CUSTOMER_CARE.getCode() + "|"
                                                  + CRMDisplayListConstants.APP.getCode() ) ) )
                {
                    lmsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    String assingUserId = null;
                    if ( StringUtils.equals( lmsPojo.getProduct(), CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                    {
                        leadStage = CRMOperationStages.SALES_COORDINATOR.getCode();
                        assingUserId = getUserIdByAreaAndStage( leadStage, areaId, 0 );
                    }
                    else if ( StringUtils.isNotBlank( lmsPojo.getArea() )
                            && ( StringUtils.equals( lmsPojo.getProduct(), CRMDisplayListConstants.BROADBAND.getCode() ) || StringUtils
                                    .equals( lmsPojo.getProduct(), CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() ) ) )
                    {
                        LOGGER.info( "Area In Lead " + lmsPojo.getArea() );
                        leadStage = CRMOperationStages.AREA_MANAGER.getCode();
                        assingUserId = getUserIdByAreaAndStage( leadStage, areaId, 0 );
                        if ( StringUtils.isBlank( assingUserId )
                                || StringUtils.equals( IAppConstants.NON_USER_FOUND, assingUserId ) )
                        {
                            LOGGER.info( "No Area Manager Found for Area :: " + areaId
                                    + " :: Changing Lead Stage to SC" );
                            leadStage = CRMOperationStages.SALES_COORDINATOR.getCode();
                            assingUserId = getUserIdByAreaAndStage( leadStage, lmsPojo.getArea(), 0 );
                        }
                    }
                    else
                    {
                        if ( StringUtils.equals( lmsPojo.getFeasibility(), IAppConstants.NO_CHAR ) )
                        {
                            LOGGER.info( "Closed Stage" + lmsPojo.getFeasibility() );
                            leadStage = CRMOperationStages.CLOSE.getCode();
                            lmsPojo.setLastModifiedBy( lmsPojo.getCreatedBy() );
                        }
                        else if ( !StringUtils.equals( lmsPojo.getFeasibility(), IAppConstants.NO_CHAR ) )
                        {
                            leadStage = CRMOperationStages.AREA_MANAGER.getCode();
                            assingUserId = getUserIdByAreaAndStage( leadStage, areaId, 0 );
                            if ( StringUtils.isBlank( assingUserId )
                                    || StringUtils.equals( IAppConstants.NON_USER_FOUND, assingUserId ) )
                            {
                                LOGGER.info( "No Area Manager Found for Area :: " + lmsPojo.getArea()
                                        + " :: Changing Lead Stage to SC" );
                                leadStage = CRMOperationStages.SALES_COORDINATOR.getCode();
                                assingUserId = getUserIdByAreaAndStage( leadStage, areaId, 0 );
                            }
                        }
                    }
                    if ( StringUtils.equals( IAppConstants.NON_USER_FOUND, assingUserId ) )
                    {
                        assingUserId = null;
                    }
                    //saving lead
                    lmsPojo.setLmsStage( leadStage );
                    if ( StringUtils.equals( lmsPojo.getFeasibility(), IAppConstants.NO_CHAR ) )
                    {
                        lmsPojo.setStatus( CRMStatusCode.CLOSE.getStatusCode() );
                        lmsPojo.setLastModifiedBy( lmsPojo.getCreatedBy() );
                    }
                    else
                    {
                        lmsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    }
                    Long generatedId = (Long) session.save( lmsPojo );
                    LOGGER.info( "Generated Id ::" + generatedId + "::: value in Pojo ::" + lmsPojo.getLmsId() );
                    lmsPojo.setLmsId( generatedId );
                    lmsPojo.setLeadId( "L" + generatedId );
                    session.merge( lmsPojo );
                    successPojos.add( lmsPojo );
                    if ( !StringUtils.equals( lmsPojo.getFeasibility(), IAppConstants.NO_CHAR ) )
                    {
                        CrmInboxPojo crmInboxPojo = new CrmInboxPojo();
                        crmInboxPojo.setUserId( assingUserId );
                        crmInboxPojo.setCreatedBy( lmsPojo.getCreatedBy() );
                        crmInboxPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        crmInboxPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        crmInboxPojo.setStage( leadStage );
                        crmInboxPojo.setMappingId( "L" + generatedId );
                        crmInboxPojo.setRequestType( CRMRequestType.LEAD.getRequestCode() );
                        Long inboxId = (Long) session.save( crmInboxPojo );
                        LOGGER.info( "Inbox Generated Id :: " + inboxId );
                        if ( saveAppointMent )
                        {
                            SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy" );
                            inLMSDto.getAppointmentPojo().setAppointmentDate( dateFormat.parse( inLMSDto
                                                                                      .getAppointmentPojo()
                                                                                      .getDisplayDate() ) );
                            inLMSDto.getAppointmentPojo().setMappingId( "L" + generatedId );
                            inLMSDto.getAppointmentPojo().setCreatedTime( Calendar.getInstance().getTime() );
                            Long appointmentId = (Long) session.save( inLMSDto.getAppointmentPojo() );
                            LOGGER.info( "Generated Appointment Id :: " + appointmentId );
                        }
                        //                    if ( i % 20 == 0 )
                        //                    {
                        session.flush();
                        session.clear();
                        //                    }
                        //                    i++;
                    }
                    inLMSDto.setLeadPojo( lmsPojo );
                    if ( StringUtils.equals( lmsPojo.getLeadSource(), CRMDisplayListConstants.WEBSITE.getCode() )
                            && StringUtils.isNotBlank( lmsPojo.getRemarks() ) )
                    {
                        inLMSDto.getRemarksPojo().setRemarks( lmsPojo.getRemarks() );
                    }
                    else
                    {
                        inLMSDto.getRemarksPojo().setRemarks( remarks );
                    }
                    CrmAuditLogPojo saveAuditLogPojo = CRMServiceUtils.createAuditLog( createLmsAuditLogs( inLMSDto ) );
                    if ( saveRemarks )
                    {
                        inLMSDto.getRemarksPojo().setMappingId( "L" + generatedId );
                        inLMSDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                        if ( StringUtils.isValidObj( saveAuditLogPojo ) )
                        {
                            inLMSDto.getRemarksPojo().setActivityId( saveAuditLogPojo.getAuditLogId() );
                        }
                        inLMSDto.getRemarksPojo().setMappingType( CRMActionConstants.CREATE_LEAD.getModule() );
                        Long remarksId = (Long) session.save( inLMSDto.getRemarksPojo() );
                        LOGGER.info( "Generated Remarks Id :: " + remarksId );
                    }
                    else if ( StringUtils.equals( lmsPojo.getLeadSource(), CRMDisplayListConstants.WEBSITE.getCode() ) )
                    {
                        RemarksPojo remarksPojo = new RemarksPojo();
                        remarksPojo.setRemarks( "" );//TODO get remarks for lead pojo
                        remarksPojo.setMappingId( "L" + generatedId );
                        remarksPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        if ( StringUtils.isValidObj( saveAuditLogPojo ) )
                        {
                            remarksPojo.setActivityId( saveAuditLogPojo.getAuditLogId() );
                        }
                        remarksPojo.setMappingType( CRMActionConstants.CREATE_LEAD.getModule() );
                        Long remarksId = (Long) session.save( remarksPojo );
                        LOGGER.info( "Generated Remarks Id :: " + remarksId );
                    }
                    if ( StringUtils.isValidObj( saveAuditLogPojo ) )
                    {
                        LOGGER.info( "Successfully insert in to audit Log for change lead status." );
                    }
                }
                else
                {
                    LOGGER.info( "Source OR Contact No ::- Invalid  or Not Present" );
                    rejectedPojos.add( lmsPojo );
                }
                //for auditLog Maintain
            }
            inLMSDto.setRejectedPojos( rejectedPojos );
            inLMSDto.setSuccessInsertPojos( successPojos );
            LOGGER.info( "Size of Rejected Pojos :: " + rejectedPojos.size() );
            transaction.commit();
            if ( !successPojos.isEmpty() )
            {
                LOGGER.info( ":::::::::::::::::::::::::Going to send mail:::::::::::::::::::::::::" );
                for ( LMSPojo lmsPojo : successPojos )
                {
                    if ( !StringUtils.equals( lmsPojo.getStatus(), CRMStatusCode.CLOSE.getStatusCode() ) )
                    {
                        if ( StringUtils.equals( lmsPojo.getLmsStage(), CRMOperationStages.AREA_MANAGER.getCode() ) )
                            CRMServiceUtils.sendAlerts( CRMEvents.LEAD_INTIMATION_AM, CRMRequestType.LEAD_USER,
                                                        lmsPojo.getLeadId(), null, null );
                        else
                            CRMServiceUtils.sendAlerts( CRMEvents.CREATE_LEAD, CRMRequestType.LEAD, lmsPojo.getLmsId()
                                    + "", null );
                    }
                }
                //process for send mail
            }
            statuCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in lmsOperation Method ::", ex );
            statuCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In LMSOperationDaoImpl : lmsOperation Method :: ", ex );
            statuCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( statuCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( LMSPojo.class.getName(), null, null );
                HibernateUtil.evictAll( CrmInboxPojo.class.getName(), null, null );
            }
        }
        inLMSDto.setStatusCode( statuCode.getStatusCode() );
        inLMSDto.setStatusDesc( statuCode.getStatusDesc() );
        return inLMSDto;
    }

    @Override
    public LMSDto saveLead( LMSDto inLMSDto )
    {
        LOGGER.info( "Inside saveLead" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            LMSPojo lmsPojo = null;
            if ( StringUtils.isValidObj( lmsPojo = inLMSDto.getLeadPojo() ) )
            {
                lmsPojo.setLocality( StringUtils.upperCase( lmsPojo.getLocality() ) );
                if ( saveRequiredDetailsOFLead( inLMSDto, session, evicts ) )
                {
                    transaction.commit();
                    LOGGER.info( "Result From saveRequiredDetailsOFLead Method " );
                    statuCode = CRMServiceCode.CRM001;
                }
            }
            LOGGER.info( "Size of pojos" + inLMSDto.getLeadPojos().size() + " values  ::" + inLMSDto.getLeadPojos() );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In save lead Method ::", ex );
            statuCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In save lead Method :: ", ex );
            statuCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( statuCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( evicts );
            }
        }
        inLMSDto.setStatusCode( statuCode.getStatusCode() );
        inLMSDto.setStatusDesc( statuCode.getStatusDesc() );
        return inLMSDto;
    }

    private boolean saveRequiredDetailsOFLead( LMSDto inLMSDto, Session session, Map<String, Long> inEvicts )
    {
        boolean result = false;
        CrmAuditLogPojo crmAuditLogPojo = null;
        try
        {
            LMSPojo lmsPojo = inLMSDto.getLeadPojo();
            if ( StringUtils.isNotEmpty( lmsPojo.getLeadId() ) )
            {
                LMSPojo daoPojo = (LMSPojo) session.get( LMSPojo.class, lmsPojo.getLmsId() );
                lmsPojo.setCreatedTime( daoPojo.getCreatedTime() );
                lmsPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                if ( StringUtils.isNotBlank( inLMSDto.getToStage() ) )
                {
                    LOGGER.info( "To Stage :: " + inLMSDto.getToStage() );
                    lmsPojo.setLmsStage( inLMSDto.getToStage() );
                }
                lmsPojo.setReferralSource( daoPojo.getReferralSource() );
                inLMSDto.setLeadPojo( lmsPojo );
                crmAuditLogPojo = createLmsAuditLogs( inLMSDto );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( daoPojo, lmsPojo );
                session.update( daoPojo );
                inEvicts.put( LMSPojo.class.getName(), daoPojo.getLmsId() );
            }
            if ( StringUtils.isValidObj( inLMSDto.getAppointmentPojo() )
                    && StringUtils.isNotEmpty( inLMSDto.getAppointmentPojo().getDisplayDate() ) )
            {
                AppointmentPojo appointmentPojo = inLMSDto.getAppointmentPojo();
                if ( appointmentPojo.getAppointmentId() > 0 )
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy" );
                    appointmentPojo.setAppointmentDate( dateFormat.parse( inLMSDto.getAppointmentPojo()
                            .getDisplayDate() ) );
                    AppointmentPojo daoAppointmentPojo = (AppointmentPojo) session.get( AppointmentPojo.class,
                                                                                        appointmentPojo
                                                                                                .getAppointmentId() );
                    org.apache.commons.beanutils.PropertyUtils.copyProperties( daoAppointmentPojo, appointmentPojo );
                    session.update( daoAppointmentPojo );
                    inEvicts.put( AppointmentPojo.class.getName(), daoAppointmentPojo.getAppointmentId() );
                }
                else
                {
                    SimpleDateFormat dateFormat = new SimpleDateFormat( "dd-MMM-yyyy" );
                    inLMSDto.getAppointmentPojo().setAppointmentDate( dateFormat.parse( inLMSDto.getAppointmentPojo()
                                                                              .getDisplayDate() ) );
                    inLMSDto.getAppointmentPojo().setMappingId( inLMSDto.getLeadPojo().getLeadId() );
                    inLMSDto.getAppointmentPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    Long appointmentId = (Long) session.save( inLMSDto.getAppointmentPojo() );
                    LOGGER.info( "Generated Appointment Id :: " + appointmentId );
                    inEvicts.put( AppointmentPojo.class.getName(), null );
                }
            }
            if ( StringUtils.isValidObj( crmAuditLogPojo ) )
            {
                crmAuditLogPojo = CRMServiceUtils.createAuditLog( crmAuditLogPojo );
                //LOGGER.info( "Audit log generated with ID : " + crmAuditLogPojo.getAuditLogId() );
            }
            if ( StringUtils.isValidObj( inLMSDto.getRemarksPojo() ) )
            {
                inLMSDto.getRemarksPojo().setMappingId( inLMSDto.getLeadPojo().getLeadId() );
                inLMSDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                if ( StringUtils.isValidObj( crmAuditLogPojo ) )
                {
                    inLMSDto.getRemarksPojo().setActivityId( crmAuditLogPojo.getAuditLogId() );
                }
                inLMSDto.getRemarksPojo().setMappingType( CRMActionConstants.CREATE_LEAD.getModule() );
                Long remarksId = (Long) session.save( inLMSDto.getRemarksPojo() );
                LOGGER.info( "Generated Remarks Id :: " + remarksId );
                inEvicts.put( RemarksPojo.class.getName(), null );
            }
            result = true;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In saveRequiredDetailsOFLead  Method", ex );
        }
        return result;
    }

    @Override
    public LMSDto forwardLead( LMSDto inLMSDto )
    {
        LOGGER.info( "Inside forward  Lead " );
        CRMEvents event = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        ICrmConfigService configService = null;
        String toUserId = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String lastLmsStage = inLMSDto.getLeadPojo().getLmsStage();
            if ( StringUtils.equals( CRMOperationStages.AREA_MANAGER.getCode(), inLMSDto.getToStage() ) )
            {
                if ( StringUtils.isNotBlank( inLMSDto.getToUserId() ) )
                {
                    toUserId = inLMSDto.getToUserId();
                }
                else
                {
                    toUserId = getUserIdByAreaAndStage( inLMSDto.getToStage(), inLMSDto.getLeadPojo().getArea(), 0 );
                }
                if ( StringUtils.isBlank( toUserId ) )
                {
                    LOGGER.info( "No User Found In DataBase For Stage :: " + inLMSDto.getToStage() );
                    statuCode = CRMServiceCode.CRM201;
                }
                else
                {
                    event = CRMEvents.LEAD_INTIMATION_AM;
                }
            }
            // here we will change the inbox/group Inbox of lead
            ConfigDto configDto = new ConfigDto();
            configDto.setFromStage( lastLmsStage );
            LOGGER.info( "Last Lms Stage " + lastLmsStage );
            inLMSDto.getLeadPojo().setPreviousStage( lastLmsStage );
            configDto.setTostage( inLMSDto.getToStage() );
            configDto.setFromUserId( inLMSDto.getLeadPojo().getLastModifiedBy() );
            // AFter full fill the requirement set toUserId we will write method for get user stage wise
            //if user to stage is FT && SC or touserId is blank  than no issue if AM than set status to sc
            //String toUserId = getUserIdByStage( inLMSDto.getToStage() );
            configDto.setToUserId( toUserId );
            configDto.setRequestType( CRMRequestType.LEAD.getRequestCode() );
            configDto.setMappingId( inLMSDto.getLeadPojo().getLeadId() );
            configDto.setUserId( inLMSDto.getLeadPojo().getLastModifiedBy() );
            configDto.setPartnerId( inLMSDto.getLeadPojo().getPartnerId() );
            configService = CRMServicesProxy.getInstance().getCRMConfigService( IGlobalConstants.REMOTE,
                                                                                IGlobalConstants.APP );
            if ( StringUtils.isValidObj( configService ) )
            {
                configDto = configService.changeInboxBin( configDto );
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), configDto.getStatusCode() ) )
                {
                    LOGGER.info( "Successfull changed Bin" );
                    //now we will change the status of Lead
                    if ( saveRequiredDetailsOFLead( inLMSDto, session, evicts ) )
                    {
                        statuCode = CRMServiceCode.CRM001;
                        LOGGER.info( "Successfully Change Status Of Lead" );
                        transaction.commit();
                    }
                    else
                    {
                        LOGGER.info( "Successfull Not Change the Status of Lead" );
                        statuCode = CRMServiceCode.CRM999;
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In forward lead Method :: ", ex );
            statuCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In forward lead Method :: ", ex );
            statuCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inLMSDto.setStatusCode( statuCode.getStatusCode() );
            inLMSDto.setStatusDesc( statuCode.getStatusDesc() );
            if ( statuCode == CRMServiceCode.CRM001 )
            {
                if ( StringUtils.isValidObj( event ) && StringUtils.isNotBlank( inLMSDto.getLeadPojo().getLeadId() ) )
                {
                    CRMServiceUtils.sendAlerts( event, CRMRequestType.LEAD_USER, inLMSDto.getLeadPojo().getLeadId(),
                                                null, null );
                }
                HibernateUtil.evictAll( evicts );
            }
        }
        return inLMSDto;
    }

    @Override
    public LMSDto changeLeadStatus( LMSDto inLMSDto, String inOperationParam )
    {
        LOGGER.info( "Inside Change Lead Status " + inOperationParam );
        SessionFactory sessionFactory = null;
        Session session = null;
        CrmAuditLogPojo crmAuditLogPojo = null;
        Transaction transaction = null;
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        Map<String, Long> evicts = new HashMap<String, Long>();
        boolean commit = true;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            LMSPojo lmsPojo = (LMSPojo) session.get( LMSPojo.class, inLMSDto.getLeadPojo().getLmsId() );
            if ( StringUtils.isValidObj( lmsPojo ) )
            {
                LOGGER.info( "Find Lead Id :: " + inLMSDto.getLeadPojo().getLmsId() );
                inLMSDto.getLeadPojo().setLmsStage( inLMSDto.getToStage() );
                if ( StringUtils.equals( CRMOperationStages.MATURE.getCode(), inLMSDto.getToStage() ) )
                {
                    inLMSDto.getLeadPojo().setStatus( CRMStatusCode.MATURE.getStatusCode() );
                }
                if ( StringUtils.equals( CRMOperationStages.CLOSE.getCode(), inLMSDto.getToStage() ) )
                {
                    inLMSDto.getLeadPojo().setStatus( CRMStatusCode.CLOSE.getStatusCode() );
                }
                inLMSDto.getLeadPojo().setLastModifiedTime( Calendar.getInstance().getTime() );
                inLMSDto.getLeadPojo().setCreatedBy( lmsPojo.getCreatedBy() );
                inLMSDto.getLeadPojo().setCreatedTime( lmsPojo.getCreatedTime() );
                inLMSDto.getLeadPojo().setReferralSource( lmsPojo.getReferralSource() );
                inLMSDto.getLeadPojo().setPreviousStage( lmsPojo.getPreviousStage() );
                crmAuditLogPojo = createLmsAuditLogs( inLMSDto );
                org.apache.commons.beanutils.PropertyUtils.copyProperties( lmsPojo, inLMSDto.getLeadPojo() );
                session.update( lmsPojo );
                evicts.put( LMSPojo.class.getName(), lmsPojo.getLmsId() );
                Criteria criteria = session.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( "mappingId", inLMSDto.getLeadPojo().getLeadId() ) )
                        .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) )
                        .add( Restrictions.eq( "requestType", CRMRequestType.LEAD.getRequestCode() ) );
                List<CrmInboxPojo> crmInboxPojos = criteria.list();
                if ( !crmInboxPojos.isEmpty() )
                {
                    CrmInboxPojo crmInboxPojo = crmInboxPojos.get( 0 );
                    LOGGER.info( "Find User Id In Inbox :: " + crmInboxPojo.getUserId() );
                    crmInboxPojo.setLastModifiedBy( inLMSDto.getLeadPojo().getLastModifiedBy() );
                    crmInboxPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmInboxPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    session.update( crmInboxPojo );
                    evicts.put( CrmInboxPojo.class.getName(), crmInboxPojo.getInboxId() );
                }
                if ( StringUtils.isValidObj( inLMSDto.getRemarksPojo() ) )
                {
                    inLMSDto.getRemarksPojo().setMappingId( inLMSDto.getLeadPojo().getLeadId() );
                    inLMSDto.getRemarksPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    inLMSDto.getRemarksPojo().setActivityId( crmAuditLogPojo.getAuditLogId() );
                    inLMSDto.getRemarksPojo().setMappingType( CRMActionConstants.CREATE_LEAD.getModule() );
                    Long remarksId = (Long) session.save( inLMSDto.getRemarksPojo() );
                    evicts.put( RemarksPojo.class.getName(), null );
                    LOGGER.info( "Generated Appointment Id :: " + remarksId );
                }
                if ( StringUtils.equals( ServiceParameter.MATURE.getParameter(), inOperationParam ) )
                {
                    String allowedINAModule = PropertyUtils.getModuleDetails( IPropertiesConstant.INA_MODULE );
                    if ( StringUtils.equals( allowedINAModule, IAppConstants.Y ) )
                    {
                        if ( StringUtils.isValidObj( inLMSDto.getCrfMappingPojos() )
                                && !inLMSDto.getCrfMappingPojos().isEmpty() )
                        {
                            {
                                if ( isDuplicateCRFId( inLMSDto, session ) )
                                {
                                    commit = false;
                                    statuCode = CRMServiceCode.CRM307;
                                    LOGGER.info( "Provided CRF Id already mapped with another Lead." );
                                }
                                else if ( !insertCrfForMature( inLMSDto, session ) )
                                {
                                    commit = false;
                                    statuCode = CRMServiceCode.CRM999;
                                    LOGGER.info( "Get Error From Method Insert Crf For Mature" );
                                }
                            }
                        }
                    }
                }
                if ( commit )
                {
                    transaction.commit();
                    statuCode = CRMServiceCode.CRM001;
                }
                if ( StringUtils.isValidObj( crmAuditLogPojo ) )
                {
                    //for auditLog Maintain
                    CrmAuditLogPojo saveAuditLogPojo = CRMServiceUtils.createAuditLog( crmAuditLogPojo );
                    if ( StringUtils.isValidObj( saveAuditLogPojo ) )
                    {
                        LOGGER.info( "Successfully insert in to audit Log for change lead status." );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Mature and close lead Method ::", ex );
            statuCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In Mature and close lead Method :: ", ex );
            statuCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( commit )
            {
                HibernateUtil.evictAll( evicts );
                HibernateUtil.evictAll( LmsCrfMappingPojo.class.getName(), null, null );
            }
        }
        inLMSDto.setStatusCode( statuCode.getStatusCode() );
        inLMSDto.setStatusDesc( statuCode.getStatusDesc() );
        return inLMSDto;
    }

    @Override
    public String getUserIdByAreaAndStage( String inStage, String inArea, long inPartnerId )
    {
        String userId = null;
        try
        {
            CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
            crmRcaReasonPojo.setCategory( CRMRCAReason.CRM.getStatusCode() );
            crmRcaReasonPojo.setSubCategory( CRMRCAReason.CRM_MASTER.getStatusCode() );
            crmRcaReasonPojo.setSubSubCategory( CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() );
            crmRcaReasonPojo.setValueAlias( inStage );
            MasterDataDto masterDataDto = new MasterDataDto();
            masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
            MasterDataDaoImpl masterDataDaoImpl = new MasterDataDaoImpl();
            masterDataDto = masterDataDaoImpl.searchAllCategoryValue( masterDataDto );
            long functionalBinID = 0;
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmRcaReasonsList() ) )
            {
                functionalBinID = masterDataDto.getCrmRcaReasonsList().get( 0 ).getCategoryId();
                LOGGER.info( "Functional bin ID" + masterDataDto.getCrmRcaReasonsList().get( 0 ).getCategoryId() );
            }
            List<String> areaManagers = getUsersByParameter( "Area", inArea, functionalBinID );
            if ( CommonValidator.isValidCollection( areaManagers ) )
            {
                if ( areaManagers.size() == 1 )
                {
                    userId = areaManagers.get( 0 );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error In Method getUserIdByStage While Fetching User Id ", ex );
        }
        return userId;
    }

    private List<String> getUsersByParameter( String inParam, String inValue, Long inFunctionalBin )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        crmuserDetailsDto.setCrmUserDetailsPojo( new CrmUserPojo() );
        crmuserDetailsDto.setUserRolesPojo( new UserRolesPojo() );
        crmuserDetailsDto.getUserRolesPojo().setCrmParameter( new CrmParameterPojo() );
        Set<String> areaManagers = null;
        try
        {
            crmuserDetailsDto.getCrmUserDetailsPojo().setFunctionalBin( IAppConstants.COMMA + inFunctionalBin
                                                                                + IAppConstants.COMMA );
            crmuserDetailsDto.getUserRolesPojo().setParameterValue( inValue );
            crmuserDetailsDto.getUserRolesPojo().getCrmParameter().setParameterName( inParam );
            IUserManagementService userManagementService;
            userManagementService = CRMServicesProxy.getInstance().getUserManagementService( IGlobalConstants.REMOTE,
                                                                                             IGlobalConstants.APP );
            if ( StringUtils.isValidObj( userManagementService ) )
            {
                crmuserDetailsDto = userManagementService.getUsersByParameter( crmuserDetailsDto );
            }
            if ( StringUtils.isValidObj( crmuserDetailsDto )
                    && CommonValidator.isValidCollection( crmuserDetailsDto.getUserRolesPojos() ) )
            {
                areaManagers = new HashSet<String>();
                for ( UserRolesPojo rolesPojo : crmuserDetailsDto.getUserRolesPojos() )
                {
                    areaManagers.add( rolesPojo.getUserId() );
                }
                LOGGER.info( "Area Managers List Size : " + areaManagers.size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting CRM UsersByParameter.", ex );
        }
        if ( CommonValidator.isValidCollection( areaManagers ) )
        {
            return new ArrayList<String>( areaManagers );
        }
        return null;
    }

    private boolean insertCrfForMature( LMSDto inLMSDto, Session inSession )
    {
        boolean result = false;
        try
        {
            LOGGER.info( "In InsertCrfForMature Method " );
            int i = 0;
            for ( LmsCrfMappingPojo crfMappingPojo : inLMSDto.getCrfMappingPojos() )
            {
                crfMappingPojo.setCreatedTime( Calendar.getInstance().getTime() );
                Long generatedId = (Long) inSession.save( crfMappingPojo );
                LOGGER.info( "Generated Id For Crf Entry " + generatedId );
                if ( i % 20 == 0 )
                {
                    inSession.flush();
                    inSession.clear();
                }
                i++;
            }
            result = true;
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error In insertCrfForMature Method ", ex );
        }
        return result;
    }

    private boolean isDuplicateCRFId( LMSDto inLMSDto, Session inSession )
    {
        boolean isDuplicate = false;
        List<String> inputCRFIdsList = null;
        LmsCrfMappingPojo lmsCrfMappingPojo = null;
        try
        {
            LOGGER.info( "In checkDuplicateCRFId Method " );
            inputCRFIdsList = new ArrayList<String>();
            for ( LmsCrfMappingPojo crfMappingPojo : inLMSDto.getCrfMappingPojos() )
            {
                inputCRFIdsList.add( crfMappingPojo.getCrfValue() );
            }
            Criteria criteria = inSession.createCriteria( LmsCrfMappingPojo.class );
            criteria.add( Restrictions.in( "crfValue", inputCRFIdsList ) );
            List<LmsCrfMappingPojo> existCrfs = criteria.list();
            LOGGER.info( "Existed CRF Ids list size : " + existCrfs.size() );
            if ( !existCrfs.isEmpty() )
            {
                lmsCrfMappingPojo = existCrfs.get( 0 );
                if ( StringUtils.isValidObj( lmsCrfMappingPojo ) )
                {
                    inLMSDto.setLmsCrfMappingPojo( lmsCrfMappingPojo );
                    isDuplicate = true;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error In checkDuplicateCRFId Method ", ex );
        }
        return isDuplicate;
    }

    private static CrmAuditLogPojo createLmsAuditLogs( LMSDto inLMSDto )
    {
        boolean isValueChanged = false;
        LMSPojo oldLMSPojo = null;
        StringBuilder oldValues = null;
        StringBuilder newValues = null;
        CrmAuditLogPojo crmAuditLogPojo = null;
        String event = null;
        String createdBy = "";
        String remarks = "";
        String mappingId = "";
        try
        {
            if ( StringUtils.isValidObj( inLMSDto.getLeadPojo() ) && StringUtils.isValidObj( inLMSDto.getRemarksPojo() ) )
            {
                LOGGER.info( "Start creation of [CrmAuditLogPojo] for LMS audits." );
                crmAuditLogPojo = new CrmAuditLogPojo();
                oldValues = new StringBuilder();
                newValues = new StringBuilder();
                mappingId = String.valueOf( inLMSDto.getLeadPojo().getLmsId() );
                if ( StringUtils.equals( inLMSDto.getRemarksPojo().getActions(),
                                         CRMActionConstants.CREATE_LEAD.getStoringValue() ) )
                {
                    event = CrmActionEnum.GENERATED.getActionCode();
                    inLMSDto.getRemarksPojo().setActions( event );
                    oldValues.append( IAppConstants.DASH );
                    newValues.append( "Lead ID=" ).append( inLMSDto.getLeadPojo().getLeadId() );
                    //newValues.append( inLMSDto.getLeadPojo().getLeadId() );
                    createdBy = inLMSDto.getLeadPojo().getCreatedBy();
                    remarks = inLMSDto.getRemarksPojo().getRemarks();
                    crmAuditLogPojo.setToBin( inLMSDto.getLeadPojo().getLmsStage() );
                    LOGGER.info( "[CrmAuditLogPojo] created for Create Lead." );
                }
                else
                {
                    createdBy = inLMSDto.getLeadPojo().getLastModifiedBy();
                    oldLMSPojo = CRMServiceUtils.getDBValues( LMSPojo.class, inLMSDto.getLeadPojo().getLmsId() );
                    if ( StringUtils.equals( inLMSDto.getRemarksPojo().getActions(),
                                             CRMActionConstants.SAVE_LEAD.getStoringValue() ) )
                    {
                        isValueChanged = CRMServiceUtils.getAuditValues( oldValues, newValues, oldLMSPojo,
                                                                         inLMSDto.getLeadPojo() );
                        if ( !isValueChanged )
                        {
                            oldValues.append( IAppConstants.DASH );
                            newValues.append( IAppConstants.DASH );
                        }
                        event = CrmActionEnum.SAVED.getActionCode();
                        inLMSDto.getRemarksPojo().setActions( event );
                        remarks = inLMSDto.getRemarksPojo().getRemarks();
                        LOGGER.info( "[CrmAuditLogPojo] created for Save case." );
                    }
                    else if ( StringUtils.equals( inLMSDto.getRemarksPojo().getActions(),
                                                  CRMActionConstants.CLOSE_LEAD.getStoringValue() ) )
                    {
                        event = CrmActionEnum.CLOSED.getActionCode();
                        inLMSDto.getRemarksPojo().setActions( event );
                        remarks = inLMSDto.getRemarksPojo().getRemarks();
                        oldValues.append( IAppConstants.DASH );
                        String reason = CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class,
                                                                     inLMSDto.getRemarksPojo().getReasonId() )
                                .getCategoryValue();
                        newValues.append( reason );
                        LOGGER.info( "[CrmAuditLogPojo] created for Close case." );
                    }
                    else if ( StringUtils.equals( inLMSDto.getRemarksPojo().getActions(),
                                                  CRMActionConstants.MATURE_LEAD.getStoringValue() ) )
                    {
                        oldValues.append( IAppConstants.DASH );
                        for ( LmsCrfMappingPojo lmsCRFMappingPojo : inLMSDto.getCrfMappingPojos() )
                        {
                            if ( StringUtils.isNotBlank( newValues ) )
                            {
                                newValues.append( IAppConstants.COMMA );
                            }
                            newValues.append( lmsCRFMappingPojo.getCrfValue() );
                        }
                        event = CrmActionEnum.MATURED.getActionCode();
                        inLMSDto.getRemarksPojo().setActions( event );
                        remarks = inLMSDto.getRemarksPojo().getRemarks();
                        LOGGER.info( "[CrmAuditLogPojo] created for Mature case." );
                    }
                    else if ( StringUtils.equals( inLMSDto.getRemarksPojo().getActions(),
                                                  CRMActionConstants.BACKWARD_SC.getStoringValue() ) )
                    {
                        if ( StringUtils.isNotBlank( inLMSDto.getToStage() ) && !isValueChanged )
                        {
                            oldValues.append( "LmsStage=" ).append( oldLMSPojo.getLmsStage() );
                            newValues.append( "LmsStage=" ).append( inLMSDto.getToStage() );
                        }
                        event = CrmActionEnum.FORWARDED.getActionCode();
                        inLMSDto.getRemarksPojo().setActions( event );
                        remarks = CRMUtils.getValue( inLMSDto.getRemarksPojo().getActions() );
                        LOGGER.info( "[CrmAuditLogPojo] created for BackWard case." );
                    }
                    else
                    {
                        if ( StringUtils.isNotBlank( inLMSDto.getToStage() ) && !isValueChanged )
                        {
                            oldValues.append( "LmsStage=" ).append( oldLMSPojo.getLmsStage() );
                            newValues.append( "LmsStage=" ).append( inLMSDto.getToStage() );
                        }
                        event = CrmActionEnum.FORWARDED.getActionCode();
                        inLMSDto.getRemarksPojo().setActions( event );
                        remarks = CRMUtils.getValue( inLMSDto.getRemarksPojo().getActions() );
                        LOGGER.info( "[CrmAuditLogPojo] created for Forward case." );
                    }
                }
                crmAuditLogPojo.setOldValues( oldValues.toString() );
                crmAuditLogPojo.setNewValues( newValues.toString() );
                crmAuditLogPojo.setMappingId( mappingId );
                crmAuditLogPojo.setCreatedBy( createdBy );
                crmAuditLogPojo.setRemarks( remarks );
                crmAuditLogPojo.setEvents( event );
                crmAuditLogPojo.setModule( CRMRequestType.LMS.getRequestCode() );
                crmAuditLogPojo.setClientIp( inLMSDto.getClientIPAddress() );
                crmAuditLogPojo.setServerIp( inLMSDto.getServerIPAddress() );
                if ( StringUtils.equals( CrmActionEnum.FORWARDED.getActionCode(), event ) )
                {
                    if ( StringUtils.isNotBlank( inLMSDto.getLeadPojo().getLmsStage() ) )
                    {
                        crmAuditLogPojo.setFromBin( inLMSDto.getLeadPojo().getLmsStage() );
                    }
                    if ( StringUtils.isNotBlank( inLMSDto.getToStage() ) )
                    {
                        crmAuditLogPojo.setToBin( inLMSDto.getToStage() );
                    }
                }
                LOGGER.info( "[CrmAuditLogPojo] for LMS audits created successfully." );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "Hibernate Exception while fetching OLD LMS OBJ : ", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while inserting auditlog for lead : " + inLMSDto.getLeadPojo().getLeadId(), ex );
        }
        return crmAuditLogPojo;
    }

    @Override
    public LMSDto leadCustomerProfileSearch( LMSDto inLMSDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( LMSPojo.class );
            if ( StringUtils.isNotBlank( inLMSDto.getLeadPojo().getLeadId() ) )
            {
                criteria.add( Restrictions.ilike( "leadId", inLMSDto.getLeadPojo().getLeadId(), MatchMode.ANYWHERE ) );
            }
            else if ( StringUtils.isNotBlank( inLMSDto.getLeadPojo().getCustomerName() ) )
            {
                criteria.add( Restrictions.like( "customerName", inLMSDto.getLeadPojo().getCustomerName(),
                                                 MatchMode.ANYWHERE ) );
            }
            else if ( inLMSDto.getLeadPojo().getContactNumber() > 0 )
            {
                criteria.add( Restrictions.eq( "contactNumber", inLMSDto.getLeadPojo().getContactNumber() ) );
            }
            if ( StringUtils.isNotBlank( inLMSDto.getLeadPojo().getLmsStage() ) )
            {
                criteria.add( Restrictions.like( "lmsStage", inLMSDto.getLeadPojo().getLmsStage() ) );
            }
            if ( StringUtils.isNotBlank( inLMSDto.getLeadPojo().getStatus() ) )
            {
                criteria.add( Restrictions.like( "status", inLMSDto.getLeadPojo().getStatus() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( LMSPojo.class.getName() );
            List<LMSPojo> lmsPojos = criteria.list();
            if ( CommonValidator.isValidCollection( lmsPojos ) )
            {
                for ( LMSPojo lmsPojo : lmsPojos )
                {
                    criteria = session.createCriteria( CrmInboxPojo.class );
                    criteria.add( Restrictions.eq( "mappingId", lmsPojo.getLeadId() ) )
                            .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                    criteria.setCacheable( true );
                    criteria.setCacheRegion( CrmInboxPojo.class.getName() );
                    List<CrmInboxPojo> inboxList = criteria.list();
                    if ( CommonValidator.isValidCollection( inboxList ) )
                    {
                        if ( StringUtils.isNotEmpty( inboxList.get( 0 ).getUserId() ) )
                        {
                            lmsPojo.setCurrentUser( inboxList.get( 0 ).getUserId() );
                        }
                        else
                            lmsPojo.setCurrentUser( "-" );
                    }
                    else
                        lmsPojo.setCurrentUser( "-" );
                }
                inLMSDto.setLeadPojos( lmsPojos );
                inLMSDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inLMSDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            }
            else
            {
                inLMSDto.setStatusCode( CRMServiceCode.CRM042.getStatusCode() );
                inLMSDto.setStatusDesc( CRMServiceCode.CRM042.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while Customer profile search for lead : ", ex );
            inLMSDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inLMSDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inLMSDto;
    }

    @Override
    public boolean checkDuplicateCRFId( LMSDto inLMSDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        boolean isDuplicate = false;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            isDuplicate = isDuplicateCRFId( inLMSDto, session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In checkDuplicateCRFId Method :: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return isDuplicate;
    }

    @SuppressWarnings("unchecked")
    @Override
    public LMSDto searchSocietyByPinCode( LMSDto inLMSDto )
    {
        LOGGER.info( "in searchSocietyByPinCode" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        int pinCode = 0;
        List<SocietyPojo> societyPojos = null;
        List<AreaPojo> areaPojos = null;
        List<CityPojo> cityPojos = null;
        AreaPojo areaPojo = null;
        CityPojo cityPojo = null;
        StatePojo statePojo = null;
        LMSPojo lmsPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( inLMSDto.getLeadPojo().getPincode() > 0 )
            {
                pinCode = inLMSDto.getLeadPojo().getPincode();
                LOGGER.info( "PIN COde::" + pinCode );
                criteria = session.createCriteria( SocietyPojo.class ).setFetchMode( "societyNetworkPartners",
                                                                                     FetchMode.SELECT );
                criteria.add( Restrictions.or( Restrictions.eq( "primaryPincode", pinCode ),
                                               Restrictions.eq( "secPincode", pinCode ) ) );
                societyPojos = criteria.list();
                if ( CommonValidator.isValidCollection( societyPojos ) )
                {
                    lmsPojo = new LMSPojo();
                    if ( societyPojos.size() > 1 )
                    {
                        Set<Long> areaIds = CRMServiceUtils.getGISIds( societyPojos, "areaId" );
                        if ( CommonValidator.isValidCollection( areaIds ) && areaIds.size() > 1 )
                        {
                            // get cities for areas
                            // single city or multiple
                            criteria = session.createCriteria( AreaPojo.class ).add( Restrictions
                                                                                             .in( "areaId", areaIds ) );
                            areaPojos = criteria.list();
                            if ( CommonValidator.isValidCollection( areaPojos ) )
                            {
                                Set<Long> cityIds = CRMServiceUtils.getGISIds( areaPojos, "cityId" );
                                if ( CommonValidator.isValidCollection( cityIds ) && cityIds.size() > 1 )
                                {
                                    criteria = session.createCriteria( CityPojo.class )
                                            .add( Restrictions.in( "cityId", cityIds ) );
                                    cityPojos = criteria.list();
                                    if ( CommonValidator.isValidCollection( cityPojos ) )
                                    {
                                        Set<Long> stateIds = CRMServiceUtils.getGISIds( cityPojos, "stateId" );
                                        if ( CommonValidator.isValidCollection( stateIds ) && stateIds.size() == 1 )
                                        {
                                            statePojo = (StatePojo) session.get( StatePojo.class, cityPojos.get( 0 )
                                                    .getStateId() );
                                            if ( StringUtils.isValidObj( statePojo ) )
                                            {
                                                lmsPojo.setState( statePojo.getStateName() );
                                            }
                                        }
                                        else
                                        {
                                            lmsPojo = null;
                                        }
                                    }
                                }
                                else
                                {
                                    cityPojo = (CityPojo) session.get( CityPojo.class, areaPojos.get( 0 ).getCityId() );
                                    if ( StringUtils.isValidObj( cityPojo ) )
                                    {
                                        lmsPojo.setCity( cityPojo.getCityName() );
                                        statePojo = (StatePojo) session.get( StatePojo.class, cityPojo.getStateId() );
                                        if ( StringUtils.isValidObj( statePojo ) )
                                        {
                                            lmsPojo.setState( statePojo.getStateName() );
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            areaPojo = (AreaPojo) session.get( AreaPojo.class, societyPojos.get( 0 ).getAreaId() );
                            if ( StringUtils.isValidObj( areaPojo ) )
                            {
                                lmsPojo.setArea( areaPojo.getArea() );
                                cityPojo = (CityPojo) session.get( CityPojo.class, areaPojo.getCityId() );
                                if ( StringUtils.isValidObj( cityPojo ) )
                                {
                                    lmsPojo.setCity( cityPojo.getCityName() );
                                    statePojo = (StatePojo) session.get( StatePojo.class, cityPojo.getStateId() );
                                    if ( StringUtils.isValidObj( statePojo ) )
                                    {
                                        lmsPojo.setState( statePojo.getStateName() );
                                    }
                                }
                            }
                        }
                    }
                    else
                    {
                        areaPojo = (AreaPojo) session.get( AreaPojo.class, societyPojos.get( 0 ).getAreaId() );
                        lmsPojo.setLocality( societyPojos.get( 0 ).getLocalityName()
                                + ( StringUtils.isNotBlank( societyPojos.get( 0 ).getSocietyName() ) ? " - "
                                        + societyPojos.get( 0 ).getSocietyName() : "" ) );
                        if ( StringUtils.isValidObj( areaPojo ) )
                        {
                            lmsPojo.setArea( areaPojo.getArea() );
                            cityPojo = (CityPojo) session.get( CityPojo.class, areaPojo.getCityId() );
                            if ( StringUtils.isValidObj( cityPojo ) )
                            {
                                lmsPojo.setCity( cityPojo.getCityName() );
                                statePojo = (StatePojo) session.get( StatePojo.class, cityPojo.getStateId() );
                                if ( StringUtils.isValidObj( statePojo ) )
                                {
                                    lmsPojo.setState( statePojo.getStateName() );
                                }
                            }
                        }
                    }
                    inLMSDto.setLeadPojo( lmsPojo );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In searchSocietyByPinCode Method :: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inLMSDto;
    }

    @Override
    public LMSDto getAreaNameByAreaId( LMSDto inLMSDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        String areaId = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "AreaNameByAreaId" );
            query.setString( "STATE", inLMSDto.getLeadPojo().getState() );
            query.setString( "CITY", inLMSDto.getLeadPojo().getCity() );
            query.setString( "AREA", inLMSDto.getLeadPojo().getArea() );
            query.setResultTransformer( Transformers.aliasToBean( AreaPojo.class ) );
            List<AreaPojo> areaPojos = query.list();
            if ( CommonValidator.isValidCollection( areaPojos ) )
            {
                areaId = areaPojos.get( 0 ).getArea();
                inLMSDto.getLeadPojo().setArea( areaId );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error In getAreaNameByAreaId Method :: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inLMSDto;
    }
}
