package com.np.tele.crm.sla.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.LMSDto;
import com.np.tele.crm.dto.ReportDto;
import com.np.tele.crm.lms.dao.ILMSOperationDao;
import com.np.tele.crm.lms.dao.LMSOperationDaoImpl;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmSlaLogPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class SLAManagerDaoImpl
    implements ISLAManagerDoa
{
    private static final Logger LOGGER = Logger.getLogger( SLAManagerDaoImpl.class );

    @Override
    public ReportDto getAuditLogHistory( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, getAuditLogHistory" );
        Session session = null;
        Criteria criteria = null;
        List<String> fromStageList = null;
        CrmAuditLogPojo crmAuditLogPojo = null;
        List<CrmAuditLogPojo> crmAuditLogPojos = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmAuditLogPojo.class );
            crmAuditLogPojo = inReportDto.getCrmAuditLogPojo();
            if ( StringUtils.equals( CRMOperationStages.SALES.getCode(), crmAuditLogPojo.getToBin() ) )
            {
                fromStageList = new ArrayList<String>();
                fromStageList.add( crmAuditLogPojo.getFromBin() );
                fromStageList.add( CRMOperationStages.SALES.getCode() );
            }
            else if ( StringUtils.equals( CRMOperationStages.SALES_COORDINATOR.getCode(), crmAuditLogPojo.getToBin() ) )
            {
                criteria.add( Restrictions.eq( "events", CrmActionEnum.GENERATED.getActionCode() ) );
            }
            if ( StringUtils.isNotBlank( crmAuditLogPojo.getModule() ) )
            {
                criteria.add( Restrictions.eq( "module", crmAuditLogPojo.getModule() ) );
            }
            if ( StringUtils.isNotBlank( crmAuditLogPojo.getMappingId() ) )
            {
                criteria.add( Restrictions.eq( "mappingId", crmAuditLogPojo.getMappingId() ) );
            }
            if ( CommonValidator.isValidCollection( fromStageList ) )
            {
                criteria.add( Restrictions.in( "fromBin", fromStageList ) );
                criteria.add( Restrictions.ne( "remarks", "Assigning Lead to self" ) );
            }
            else if ( StringUtils.isNotBlank( crmAuditLogPojo.getFromBin() ) )
            {
                criteria.add( Restrictions.eq( "fromBin", crmAuditLogPojo.getFromBin() ) );
            }
            if ( StringUtils.isNotBlank( crmAuditLogPojo.getToBin() ) )
            {
                criteria.add( Restrictions.eq( "toBin", crmAuditLogPojo.getToBin() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmAuditLogPojo.class.getName() );
            crmAuditLogPojos = criteria.list();
            inReportDto.setAuditLogPojos( crmAuditLogPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting Audit Log.", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inReportDto.setStatusCode( crmServiceCode.getStatusCode() );
            inReportDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inReportDto;
    }

    @Override
    public ReportDto getSlaLogHistory( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, getSlaLogHistory" );
        Session session = null;
        Criteria criteria = null;
        CrmSlaLogPojo crmSlaLogPojo = null;
        List<CrmSlaLogPojo> crmSlaLogPojos = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmSlaLogPojo.class );
            crmSlaLogPojo = inReportDto.getCrmSlaLogPojo();
            if ( StringUtils.isNotBlank( crmSlaLogPojo.getCrmModule() ) )
            {
                criteria.add( Restrictions.eq( "crmModule", crmSlaLogPojo.getCrmModule() ) );
            }
            if ( StringUtils.isNotBlank( crmSlaLogPojo.getMappingId() ) )
            {
                criteria.add( Restrictions.eq( "mappingId", crmSlaLogPojo.getMappingId() ) );
            }
            if ( crmSlaLogPojo.getSlaValueMillis() > 0 )
            {
                criteria.add( Restrictions.eq( "slaValueMillis", crmSlaLogPojo.getSlaValueMillis() ) );
            }
            if ( StringUtils.isNotBlank( crmSlaLogPojo.getSlaUnit() ) )
            {
                criteria.add( Restrictions.eq( "slaUnit", crmSlaLogPojo.getSlaUnit() ) );
            }
            if ( StringUtils.isNotBlank( crmSlaLogPojo.getAlertType() ) )
            {
                criteria.add( Restrictions.eq( "alertType", crmSlaLogPojo.getAlertType() ) );
            }
            if ( StringUtils.isNotBlank( crmSlaLogPojo.getSlaFromStage() ) )
            {
                criteria.add( Restrictions.eq( "slaFromStage", crmSlaLogPojo.getSlaFromStage() ) );
            }
            if ( StringUtils.isNotBlank( crmSlaLogPojo.getSlaToStage() ) )
            {
                criteria.add( Restrictions.eq( "slaToStage", crmSlaLogPojo.getSlaToStage() ) );
            }
            if ( StringUtils.isValidObj( crmSlaLogPojo.getAuditLogId() ) && crmSlaLogPojo.getAuditLogId() > 0 )
            {
                criteria.add( Restrictions.eq( "auditLogId", crmSlaLogPojo.getAuditLogId() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmSlaLogPojo.class.getName() );
            crmSlaLogPojos = criteria.list();
            inReportDto.setSlaLogPojos( crmSlaLogPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting SLA Log.", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inReportDto.setStatusCode( crmServiceCode.getStatusCode() );
            inReportDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inReportDto;
    }

    @Override
    public ReportDto getRemarksHistory( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, getRemarksHistory" );
        Session session = null;
        Criteria criteria = null;
        RemarksPojo remarksPojo = null;
        List<RemarksPojo> remarksPojos = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( RemarksPojo.class );
            remarksPojo = inReportDto.getRemarksPojo();
            if ( StringUtils.isNotBlank( remarksPojo.getMappingId() ) )
            {
                criteria.add( Restrictions.eq( "mappingId", remarksPojo.getMappingId() ) );
            }
            if ( StringUtils.isValidObj( remarksPojo.getCreatedTime() ) )
            {
                criteria.add( Restrictions.ge( "createdTime", remarksPojo.getCreatedTime() ) );
            }
            if ( StringUtils.isNotBlank( remarksPojo.getCreatedBy() ) )
            {
                criteria.add( Restrictions.eq( "createdBy", remarksPojo.getCreatedBy() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( RemarksPojo.class.getName() );
            remarksPojos = criteria.list();
            inReportDto.setRemarksPojos( remarksPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting SLA Log.", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inReportDto.setStatusCode( crmServiceCode.getStatusCode() );
            inReportDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inReportDto;
    }

    @Override
    public ReportDto sendSLAAlertAndSaveStatus( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, sendSLAAlertAndSaveStatus" );
        try
        {
            if ( StringUtils.isNotBlank( inReportDto.getCrmSlaLogPojo().getSlaRecipients() ) )
            {
                LOGGER.info( "User Ids List : " + inReportDto.getCrmSlaLogPojo().getSlaRecipients() );
                String emailIDs = null;
                if ( !StringUtils.equals( inReportDto.getCrmSlaLogPojo().getCrmModule(),
                                          CRMRequestType.QRC.getRequestCode() ) )
                {
                    emailIDs = getEmailIdsByUserIds( inReportDto.getCrmSlaLogPojo().getSlaRecipients() );
                }
                else
                {
                    emailIDs = inReportDto.getCrmSlaLogPojo().getSlaRecipients();
                    if ( StringUtils
                            .equals( inReportDto.getParamMap().get( CRMParameter.CURRENT_OWNER.getParameter() ),
                                     IAppConstants.NOTAPPLICABLE ) )
                    {
                        String currentUser = getCurrentOwnerByTicketId( inReportDto.getCrmSlaLogPojo().getMappingId() );
                        if ( StringUtils.isNotBlank( currentUser ) )
                        {
                            inReportDto.getParamMap().remove( CRMParameter.CURRENT_OWNER.getParameter() );
                            inReportDto.getParamMap().put( CRMParameter.CURRENT_OWNER.getParameter(), currentUser );
                        }
                    }
                }
                Set<String> uniqueEmailIDs = new HashSet<String>( Arrays.asList( StringUtils
                        .split( emailIDs, IAppConstants.COMMA ) ) );
                LOGGER.info( "Email Ids List : " + uniqueEmailIDs );
                if ( CommonValidator.isValidCollection( uniqueEmailIDs ) )
                {
                    inReportDto.getParamMap().put( CRMParameter.RECIPIENTS.getParameter(),
                                                   uniqueEmailIDs.toString().replaceAll( "\\[|\\]", "" ) );
                    CRMEvents crmEvent = null;
                    if ( StringUtils.equals( inReportDto.getCrmSlaLogPojo().getCrmModule(),
                                             CRMRequestType.LMS.getRequestCode() ) )
                    {
                        crmEvent = CRMEvents.SLA_LMS;
                    }
                    else if ( StringUtils.equals( inReportDto.getCrmSlaLogPojo().getCrmModule(),
                                                  CRMRequestType.INA.getRequestCode() ) )
                    {
                        crmEvent = CRMEvents.SLA_INA;
                    }
                    else if ( StringUtils.equals( inReportDto.getCrmSlaLogPojo().getCrmModule(),
                                                  CRMRequestType.QRC.getRequestCode() ) )
                    {
                        crmEvent = CRMEvents.SLA_TKT;
                    }
                    boolean isAlertSent = CRMServiceUtils.sendAlerts( crmEvent, CRMRequestType.SLA, inReportDto
                            .getCrmSlaLogPojo().getMappingId(), null, inReportDto.getParamMap() );
                    if ( isAlertSent )
                    {
                        inReportDto = saveSLALogPojo( inReportDto );
                    }
                    else
                    {
                        inReportDto.getCrmSlaLogPojo().setSlaStatus( CRMStatusCode.FAILURE.getStatusCode() );
                        inReportDto.getCrmSlaLogPojo().setSlaFailReason( "Email sent failed" );
                        inReportDto = saveSLALogPojo( inReportDto );
                    }
                }
            }
            else
            {
                inReportDto = saveSLALogPojo( inReportDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing SLA Alerts and Save Status", ex );
        }
        return inReportDto;
    }

    private String getCurrentOwnerByTicketId( String inTicketID )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        String currentUser = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "CURRENT_OWNER_BY_TICKET_ID" );
            query.setString( "TICKET_ID", StringUtils.trimToEmpty( inTicketID ) );
            List<String> strList = query.list();
            if ( !strList.isEmpty() )
            {
                currentUser = strList.get( 0 );
                LOGGER.info( "Current User : " + currentUser );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Ticket ID :" + inTicketID );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching parameter value For Ticket: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return currentUser;
    }

    private ReportDto saveSLALogPojo( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, saveSLALogPojo" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode statuCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            inReportDto.getCrmSlaLogPojo().setSlaDateTime( Calendar.getInstance().getTime() );
            Long generateID = (Long) session.save( inReportDto.getCrmSlaLogPojo() );
            if ( generateID > 0 )
            {
                LOGGER.info( "Crm SLA Log generated successfully for Lead ID "
                        + inReportDto.getCrmSlaLogPojo().getMappingId() + " with Record ID " + generateID );
                inReportDto.getCrmSlaLogPojo().setSlaId( generateID );
                inReportDto.setCrmSlaLogPojo( inReportDto.getCrmSlaLogPojo() );
                statuCode = CRMServiceCode.CRM001;
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saving CRM SLA Log pojo ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while saving CRM SLA Log pojo ", ex );
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
        }
        finally
        {
            inReportDto.setStatusCode( statuCode.getStatusCode() );
            inReportDto.setStatusDesc( statuCode.getStatusDesc() );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == statuCode )
            {
                HibernateUtil.evictAll( CrmSlaLogPojo.class.getName(), null, null );
            }
        }
        return inReportDto;
    }

    private String getEmailIdsByUserIds( String inSlaRecipients )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, getEmailIdsByUserIds" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        StringBuilder emailIds = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "EMAIL_IDS_BY_USER_IDS" );
            query.setParameterList( "USER_IDS",
                                    Arrays.asList( StringUtils.split( inSlaRecipients, IAppConstants.COMMA ) ) );
            List<String> emailIDsList = query.list();
            emailIds = new StringBuilder();
            if ( CommonValidator.isValidCollection( emailIDsList ) )
            {
                for ( String string : emailIDsList )
                {
                    if ( StringUtils.isNotBlank( emailIds ) )
                    {
                        emailIds.append( IAppConstants.COMMA );
                    }
                    emailIds.append( string );
                }
            }
            else
            {
                LOGGER.info( "No Value Present In Database For User Id :" + inSlaRecipients );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching Email Ids By User Ids ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return emailIds.toString();
    }

    @Override
    public ReportDto forwardAndSaveStatus( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, forwardAndSaveStatus" );
        ILMSOperationDao lmsOperationDaoImpl = null;
        LMSDto lmsDto = null;
        try
        {
            if ( StringUtils.isNotBlank( inReportDto.getCrmSlaLogPojo().getSlaRecipients() ) )
            {
                lmsOperationDaoImpl = new LMSOperationDaoImpl();
                String[] reciepientArray = StringUtils.split( inReportDto.getCrmSlaLogPojo().getSlaRecipients(),
                                                              IAppConstants.COMMA );
                lmsDto = new LMSDto();
                lmsDto.setToUserId( reciepientArray[0] );
                lmsDto.setToStage( inReportDto.getCrmSlaLogPojo().getSlaToStage() );
                inReportDto.getLeadPojo().setLastModifiedBy( "System" );
                lmsDto.setLeadPojo( inReportDto.getLeadPojo() );
                lmsDto = lmsOperationDaoImpl.forwardLead( lmsDto );
                if ( StringUtils.equals( lmsDto.getStatusCode(), CRMServiceCode.CRM001.getStatusCode() ) )
                {
                    inReportDto = saveSLALogPojo( inReportDto );
                }
                else
                {
                    inReportDto.getCrmSlaLogPojo().setSlaStatus( CRMStatusCode.FAILURE.getStatusCode() );
                    inReportDto.getCrmSlaLogPojo().setSlaFailReason( "Unable to forward to AM" );
                    inReportDto = saveSLALogPojo( inReportDto );
                }
            }
            else
            {
                inReportDto = saveSLALogPojo( inReportDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while processing SLA forward and Save Status", ex );
        }
        return inReportDto;
    }

    @Override
    public ReportDto getQrcSubSubCategoriesPojos( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, getQrcSubSubCategoriesPojos" );
        Session session = null;
        Criteria criteria = null;
        CrmQrcSubSubCategoriesPojo subSubCategoriesPojo = null;
        List<CrmQrcSubSubCategoriesPojo> subSubCategoriesPojos = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            session = HibernateUtil.getCurrentSession();
            criteria = session.createCriteria( CrmQrcSubSubCategoriesPojo.class );
            subSubCategoriesPojo = inReportDto.getSubSubCategoriesPojo();
            if ( StringUtils.isNotBlank( subSubCategoriesPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", subSubCategoriesPojo.getStatus() ) );
            }
            criteria.add( Restrictions.isNotNull( "sla" ) );
            criteria.add( Restrictions.isNotNull( "slaUnit" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmQrcSubSubCategoriesPojo.class.getName() );
            subSubCategoriesPojos = criteria.list();
            inReportDto.setQrcSubSubCategoriesPojos( subSubCategoriesPojos );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting Qrc Sub-Sub-Categories.", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inReportDto.setStatusCode( crmServiceCode.getStatusCode() );
            inReportDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inReportDto;
    }

    @Override
    public ReportDto getQrcSubCategoriesPojos( ReportDto inReportDto )
    {
        LOGGER.info( "Inside SLAManagerDaoImpl, getQrcSubCategoriesPojos" );
        CrmQrcSubCategoriesPojo subCategoriesPojo = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            subCategoriesPojo = CRMServiceUtils.getDBValues( CrmQrcSubCategoriesPojo.class, inReportDto
                    .getSubSubCategoriesPojo().getQrcSubCategoryId() );
            if ( StringUtils.isValidObj( subCategoriesPojo ) )
            {
                inReportDto.getSubSubCategoriesPojo().setQrcSubSubCategory( subCategoriesPojo.getQrcSubCategory() );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while getting Qrc Sub-Sub-Categories.", ex );
        }
        finally
        {
            inReportDto.setStatusCode( crmServiceCode.getStatusCode() );
            inReportDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inReportDto;
    }
}
