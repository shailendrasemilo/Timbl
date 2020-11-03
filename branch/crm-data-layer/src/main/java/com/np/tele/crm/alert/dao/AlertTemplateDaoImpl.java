package com.np.tele.crm.alert.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CrmReasonsConstants;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.pojos.AlertStatusPojo;
import com.np.tele.crm.pojos.CrmEmailAttachment;
import com.np.tele.crm.pojos.EmailTemplatePojo;
import com.np.tele.crm.pojos.EventTemplatesPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.tele.crm.pojos.ProjectsPojo;
import com.np.tele.crm.pojos.SmsTemplatePojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class AlertTemplateDaoImpl
    implements IAlertTemplateDao
{
    private static final Logger LOGGER = Logger.getLogger( AlertTemplateDaoImpl.class );

    @Override
    public boolean createSMSTemplate( SmsTemplatePojo inSmsTemplatePojo )
    {
        LOGGER.info( "Inside createSMSTemplateDao" );
        boolean isSuccess = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        Set<Long> templateIds = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            inSmsTemplatePojo.setCreatedTime( Calendar.getInstance().getTime() );
            Long id = (Long) session.save( inSmsTemplatePojo );
            transaction.commit();
            session.clear();
            transaction = session.beginTransaction();
            criteria = session.createCriteria( SmsTemplatePojo.class );
            criteria.createCriteria( "projects", "p" );
            criteria.add( Restrictions.eq( "p.projectId", inSmsTemplatePojo.getProjects().getProjectId() ) );
            criteria.add( Restrictions.eq( "smsTemplateName", inSmsTemplatePojo.getSmsTemplateName() ) );
            criteria.add( Restrictions.lt( "smsTemplateId", id ) );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.setCacheMode( CacheMode.IGNORE );
            ScrollableResults listResult = criteria.scroll( ScrollMode.FORWARD_ONLY );
            if ( StringUtils.isValidObj( listResult ) )
            {
                templateIds = new TreeSet<Long>();
                while ( listResult.next() )
                {
                    LOGGER.info( "Deactivating old templates." );
                    SmsTemplatePojo pojo = (SmsTemplatePojo) listResult.get( 0 );
                    pojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    pojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    pojo.setLastModifiedBy( ( inSmsTemplatePojo.getCreatedBy() ) );
                    templateIds.add( pojo.getSmsTemplateId() );
                }
            }
            listResult.close();
            transaction.commit();
            if ( id >= 0 )
            {
                isSuccess = true;
                LOGGER.info( "Email Template ID:::::::::::" + inSmsTemplatePojo.getSmsTemplateId() );
                if ( CommonValidator.isValidCollection( templateIds ) )
                {
                    for ( Long templateId : templateIds )
                    {
                        updateEventTemplateMapping( templateId, id, CRMParameter.SMS );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            isSuccess = false;
            LOGGER.error( ex.getCause(), ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            isSuccess = false;
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Getting Error while create SMS Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return isSuccess;
    }

    @Override
    public boolean createEmailTemplate( EmailTemplatePojo inEmailTemplatePojo )
    {
        LOGGER.info( "Entering createEmailTemplate" );
        boolean isSuccess = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        Set<Long> templateIds = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            inEmailTemplatePojo.setCreatedTime( Calendar.getInstance().getTime() );
            Long id = (Long) session.save( inEmailTemplatePojo );
            criteria = session.createCriteria( EmailTemplatePojo.class );
            criteria.createCriteria( "projects", "p" );
            criteria.add( Restrictions.eq( "p.projectId", inEmailTemplatePojo.getProjects().getProjectId() ) );
            criteria.add( Restrictions.eq( "emailTemplateName", inEmailTemplatePojo.getEmailTemplateName() ) );
            criteria.add( Restrictions.lt( "emailTemplateId", id ) );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.setCacheMode( CacheMode.IGNORE );
            ScrollableResults listResult = criteria.scroll( ScrollMode.FORWARD_ONLY );
            if ( StringUtils.isValidObj( listResult ) )
            {
                templateIds = new TreeSet<Long>();
                while ( listResult.next() )
                {
                    LOGGER.info( "Deactivating old templates." );
                    EmailTemplatePojo pojo = (EmailTemplatePojo) listResult.get( 0 );
                    pojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    pojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    pojo.setLastModifiedBy( inEmailTemplatePojo.getCreatedBy() );
                    templateIds.add( pojo.getEmailTemplateId() );
                }
            }
            listResult.close();
            transaction.commit();
            if ( id > 0 )
            {
                isSuccess = true;
                LOGGER.info( "Email Template ID:::::::::::" + inEmailTemplatePojo.getEmailTemplateId() );
                if ( CommonValidator.isValidCollection( templateIds ) )
                {
                    for ( Long templateId : templateIds )
                    {
                        updateEventTemplateMapping( templateId, id, CRMParameter.EMAIL );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while create Email Template: ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Getting Error while create Email Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        LOGGER.info( "Exiting createEmailTemplate: Success:" + isSuccess );
        return isSuccess;
    }

    @Override
    public List<SmsTemplatePojo> listSMSTemplate( SmsTemplatePojo inInsmsTemplatePojo )
    {
        List<SmsTemplatePojo> smsTemplatePojoList = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( SmsTemplatePojo.class );
            if ( StringUtils.isValidObj( inInsmsTemplatePojo ) )
            {
                if ( inInsmsTemplatePojo.getSmsTemplateId() > 0 )
                {
                    criteria.add( Restrictions.eq( "smsTemplateId", inInsmsTemplatePojo.getSmsTemplateId() ) );
                }
                else if ( StringUtils.isValidObj( inInsmsTemplatePojo.getProjects() ) )
                {
                    criteria.createCriteria( "projects", "p" );
                    if ( inInsmsTemplatePojo.getProjects().getProjectId() > 0 )
                    {
                        criteria.add( Restrictions.eq( "p.projectId", inInsmsTemplatePojo.getProjects().getProjectId() ) );
                    }
                    if ( StringUtils.isValidObj( inInsmsTemplatePojo.getProjects().getProjectType() ) )
                    {
                        criteria.add( Restrictions.eq( "p.projectType", inInsmsTemplatePojo.getProjects()
                                .getProjectType() ) );
                    }
                    if ( StringUtils.isValidObj( inInsmsTemplatePojo.getProjects().getStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "p.status", inInsmsTemplatePojo.getProjects().getStatus() ) );
                    }
                }
                if ( StringUtils.isNotEmpty( inInsmsTemplatePojo.getSmsTemplateName() ) )
                {
                    LOGGER.info( "SMS Template Name:-" + inInsmsTemplatePojo.getSmsTemplateName() );
                    criteria.add( Restrictions.ilike( "smsTemplateName", inInsmsTemplatePojo.getSmsTemplateName(),
                                                      MatchMode.ANYWHERE ) );
                }
                if ( StringUtils.isNotEmpty( inInsmsTemplatePojo.getStatus() )
                        && !StringUtils.isNumeric( inInsmsTemplatePojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inInsmsTemplatePojo.getStatus() ) );
                }
                if ( StringUtils.isNotEmpty( inInsmsTemplatePojo.getSmsType() ) )
                {
                    criteria.add( Restrictions.eq( "smsType", inInsmsTemplatePojo.getSmsType() ) );
                }
                criteria.addOrder( Order.desc( "createdTime" ) );
                criteria.addOrder( Order.desc( "lastModifiedTime" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( SmsTemplatePojo.class.getName() );
                smsTemplatePojoList = criteria.list();
                LOGGER.info( "Size of SMS Template:- " + smsTemplatePojoList.size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while listing SMS Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return smsTemplatePojoList;
    }

    @Override
    public boolean updateSMSTemplateByArchive( SmsTemplatePojo inSmsTemplatePojo )
    {
        boolean isSuccess = true;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        long oldTemplateId = 0l;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            SmsTemplatePojo smsTemplatePojo = (SmsTemplatePojo) session.get( SmsTemplatePojo.class,
                                                                             inSmsTemplatePojo.getSmsTemplateId() );
            smsTemplatePojo.setStatus( inSmsTemplatePojo.getStatus() );
            smsTemplatePojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            smsTemplatePojo.setLastModifiedBy( inSmsTemplatePojo.getLastModifiedBy() );
            session.update( smsTemplatePojo );
            if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), smsTemplatePojo.getStatus() ) )
            {
                SmsTemplatePojo pojo = null;
                long newTemplateId = inSmsTemplatePojo.getSmsTemplateId();
                criteria = session.createCriteria( SmsTemplatePojo.class );
                criteria.add( Restrictions.eq( "smsTemplateName", smsTemplatePojo.getSmsTemplateName() ) );
                criteria.add( Restrictions.eq( "status", smsTemplatePojo.getStatus() ) );
                criteria.add( Restrictions.ne( "smsTemplateId", smsTemplatePojo.getSmsTemplateId() ) );
                List<SmsTemplatePojo> smsTemplatePojoList = criteria.list();
                if ( StringUtils.isValidObj( smsTemplatePojoList ) && smsTemplatePojoList.size() > 0 )
                {
                    pojo = (SmsTemplatePojo) smsTemplatePojoList.get( 0 );
                    pojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    oldTemplateId = pojo.getSmsTemplateId();
                    LOGGER.info( "SMS Tempalte Status Updated Successfully" );
                }
                updateEventTemplateMapping( oldTemplateId, newTemplateId, CRMParameter.SMS );
                if ( StringUtils.isValidObj( pojo ) )
                {
                    session.update( pojo );
                }
            }
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            isSuccess = false;
            LOGGER.error( "HibernateException while Archive SMS Template: ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            isSuccess = false;
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Getting Error while Archive SMS Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( isSuccess )
            {
                HibernateUtil.evictAll( SmsTemplatePojo.class.getName(), null, oldTemplateId );
            }
        }
        return isSuccess;
    }

    private void updateEventTemplateMapping( long inOldTemplateId, long inNewTemplateId, CRMParameter inParam )
    {
        if ( inOldTemplateId > 0 && inNewTemplateId > 0 && inOldTemplateId != inNewTemplateId )
        {
            LOGGER.info( "Event Template Mapping Updating from  " + inOldTemplateId + " to " + inNewTemplateId );
            SessionFactory sessionFactory = null;
            Session session = null;
            Transaction transaction = null;
            Criteria criteria = null;
            long tempID = 0;
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                criteria = session.createCriteria( EventTemplatesPojo.class );
                criteria.createCriteria( "events", "ev" );
                criteria.createCriteria( "emailTemplate", "et" );
                criteria.createCriteria( "smsTemplate", "st" );
                if ( CRMParameter.SMS == inParam )
                {
                    criteria.add( Restrictions.eq( "st.smsTemplateId", inOldTemplateId ) );
                }
                else if ( CRMParameter.EMAIL == inParam )
                {
                    criteria.add( Restrictions.eq( "et.emailTemplateId", inOldTemplateId ) );
                }
                criteria.add( Restrictions.eq( "ev.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.createCriteria( "et.projects", "pe" );
                criteria.add( Restrictions.eq( "pe.projectType", CRMParameter.INTERNAL.getParameter() ) );
                criteria.add( Restrictions.eq( "pe.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.createCriteria( "ev.projects", "pv" );
                criteria.add( Restrictions.eq( "pv.projectType", CRMParameter.INTERNAL.getParameter() ) );
                criteria.add( Restrictions.eq( "pv.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.createCriteria( "st.projects", "ps" );
                criteria.add( Restrictions.eq( "ps.projectType", CRMParameter.INTERNAL.getParameter() ) );
                criteria.add( Restrictions.eq( "ps.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                List<EventTemplatesPojo> eventTemplatesPojoList = criteria.list();
                if ( CommonValidator.isValidCollection( eventTemplatesPojoList ) )
                {
                    for ( EventTemplatesPojo eventTemplatesPojo : eventTemplatesPojoList )
                    {
                        if ( CRMParameter.SMS == inParam && eventTemplatesPojo.isSmsEnabled() )
                        {
                            SmsTemplatePojo smsPojo = new SmsTemplatePojo();
                            smsPojo.setSmsTemplateId( inNewTemplateId );
                            eventTemplatesPojo.setSmsTemplate( smsPojo );
                            session.update( eventTemplatesPojo );
                            tempID = eventTemplatesPojo.getEventTemplateId();
                            LOGGER.info( "SMS Mapping updating for:" + eventTemplatesPojo.getEventTemplateId() );
                        }
                        else if ( CRMParameter.EMAIL == inParam && eventTemplatesPojo.isEmailEnabled() )
                        {
                            EmailTemplatePojo emailPojo = new EmailTemplatePojo();
                            emailPojo.setEmailTemplateId( inNewTemplateId );
                            eventTemplatesPojo.setEmailTemplate( emailPojo );
                            session.update( eventTemplatesPojo );
                            tempID = eventTemplatesPojo.getEventTemplateId();
                            LOGGER.info( "Email Mapping updating for:" + eventTemplatesPojo.getEventTemplateId() );
                        }
                    }
                }
                transaction.commit();
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException while Updating Event Template Mapping for " + inParam, ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                CRMServiceUtils.rollback( transaction );
                LOGGER.error( "Getting Error while Updating Event Template Mapping for " + inParam, ex );
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
            }
        }
    }

    @Override
    public List<EmailTemplatePojo> listEmailTemplate( EmailTemplatePojo inEmailTemplatePojo )
    {
        List<EmailTemplatePojo> emailTemplatePojoList = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( EmailTemplatePojo.class );
            if ( StringUtils.isValidObj( inEmailTemplatePojo ) )
            {
                if ( inEmailTemplatePojo.getEmailTemplateId() > 0 )
                {
                    criteria.add( Restrictions.eq( "emailTemplateId", inEmailTemplatePojo.getEmailTemplateId() ) );
                }
                else if ( StringUtils.isValidObj( inEmailTemplatePojo.getProjects() ) )
                {
                    criteria.createCriteria( "projects", "p" );
                    if ( inEmailTemplatePojo.getProjects().getProjectId() > 0 )
                    {
                        criteria.add( Restrictions.eq( "p.projectId", inEmailTemplatePojo.getProjects().getProjectId() ) );
                    }
                    if ( StringUtils.isValidObj( inEmailTemplatePojo.getProjects().getProjectType() ) )
                    {
                        criteria.add( Restrictions.eq( "p.projectType", inEmailTemplatePojo.getProjects()
                                .getProjectType() ) );
                    }
                    if ( StringUtils.isValidObj( inEmailTemplatePojo.getProjects().getStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "p.status", inEmailTemplatePojo.getProjects().getStatus() ) );
                    }
                }
                if ( StringUtils.isNotBlank( inEmailTemplatePojo.getEmailTemplateName() ) )
                {
                    LOGGER.info( "Email Template Name:-" + inEmailTemplatePojo.getEmailTemplateName() );
                    criteria.add( Restrictions.ilike( "emailTemplateName", inEmailTemplatePojo.getEmailTemplateName(),
                                                      MatchMode.ANYWHERE ) );
                }
                if ( StringUtils.isNotBlank( inEmailTemplatePojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inEmailTemplatePojo.getStatus() ) );
                }
                criteria.addOrder( Order.desc( "createdTime" ) );
                criteria.addOrder( Order.desc( "lastModifiedTime" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( EmailTemplatePojo.class.getName() );
                emailTemplatePojoList = criteria.list();
                LOGGER.info( "Size of Email Template:- " + emailTemplatePojoList.size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while listing Email Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return emailTemplatePojoList;
    }

    @Override
    public boolean updateEmailTemplateByArchive( EmailTemplatePojo inEmailTemplatePojo )
    {
        boolean isUpdated = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        long oldTemplateId = 0l;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            EmailTemplatePojo emailTemplatePojo = (EmailTemplatePojo) session.get( EmailTemplatePojo.class,
                                                                                   inEmailTemplatePojo
                                                                                           .getEmailTemplateId() );
            emailTemplatePojo.setStatus( inEmailTemplatePojo.getStatus() );
            emailTemplatePojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            emailTemplatePojo.setLastModifiedBy( inEmailTemplatePojo.getLastModifiedBy() );
            session.update( emailTemplatePojo );
            if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), emailTemplatePojo.getStatus() ) )
            {
                EmailTemplatePojo pojo = null;
                long newTemplateId = inEmailTemplatePojo.getEmailTemplateId();
                criteria = session.createCriteria( EmailTemplatePojo.class );
                criteria.add( Restrictions.eq( "emailTemplateName", emailTemplatePojo.getEmailTemplateName() ) );
                criteria.add( Restrictions.eq( "status", emailTemplatePojo.getStatus() ) );
                criteria.add( Restrictions.ne( "emailTemplateId", emailTemplatePojo.getEmailTemplateId() ) );
                List<EmailTemplatePojo> emailTemplatePojoList = criteria.list();
                if ( StringUtils.isValidObj( emailTemplatePojoList ) && emailTemplatePojoList.size() > 0 )
                {
                    pojo = (EmailTemplatePojo) emailTemplatePojoList.get( 0 );
                    pojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    oldTemplateId = pojo.getEmailTemplateId();
                    LOGGER.info( "Email Tempalte Status Updated Successfully" );
                }
                updateEventTemplateMapping( oldTemplateId, newTemplateId, CRMParameter.EMAIL );
                if ( StringUtils.isValidObj( pojo ) )
                {
                    session.update( pojo );
                }
            }
            transaction.commit();
            isUpdated = true;
        }
        catch ( HibernateException ex )
        {
            isUpdated = false;
            LOGGER.error( "HibernateException while Archive Email Template: ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            isUpdated = false;
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Getting Error while Archive Email Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( isUpdated )
            {
                HibernateUtil.evictAll( EmailTemplatePojo.class.getName(), null, oldTemplateId );
            }
        }
        return isUpdated;
    }

    public static void main( String[] args )
    {
        /*EventTemplatesPojo evPojo = new 
         AlertTemplateDaoImpl alertTemplateDaoImpl = new AlertTemplateDaoImpl();
          AlertsDto inAlertsDto = new AlertsDto();
          alertTemplateDaoImpl.getEmailTemplateList( inAlertsDto );
          alertTemplateDaoImpl.getSmsTemplateList( inAlertsDto );
          alertTemplateDaoImpl.getEventTemplatesList( inAlertsDto );
        //LOGGER.info( "SISCCCC" + inAlertsDto.getEmailTemplatePojos().size() );
        */}

    @Override
    public AlertsDto getEventTemplatesList( AlertsDto inAlertsDto )
    {
        List<EventsPojo> eventPojoList = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( EventsPojo.class );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "projects", "p" );
            criteria.add( Restrictions.eq( "p.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "p.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            eventPojoList = criteria.list();
            if ( StringUtils.isValidObj( eventPojoList ) )
            {
                inAlertsDto.setEventsPojoList( eventPojoList );
                LOGGER.info( "Size of Event Template:- " + eventPojoList.size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while listing Event Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inAlertsDto;
    }

    @Override
    public AlertsDto getSmsTemplateList( AlertsDto inAlertsDto )
    {
        try
        {
            SmsTemplatePojo emPojo = new SmsTemplatePojo();
            emPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            ProjectsPojo pojo = new ProjectsPojo();
            pojo.setProjectType( CRMParameter.INTERNAL.getParameter() );
            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            emPojo.setProjects( pojo );
            inAlertsDto.setSmsTemplatePojos( listSMSTemplate( emPojo ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while listing SMS Template: ", ex );
        }
        return inAlertsDto;
    }

    @Override
    public AlertsDto getEmailTemplateList( AlertsDto inAlertsDto )
    {
        try
        {
            EmailTemplatePojo emPojo = new EmailTemplatePojo();
            emPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            ProjectsPojo pojo = new ProjectsPojo();
            pojo.setProjectType( CRMParameter.INTERNAL.getParameter() );
            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            emPojo.setProjects( pojo );
            inAlertsDto.setEmailTemplatePojos( listEmailTemplate( emPojo ) );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while listing Email Template: ", ex );
        }
        return inAlertsDto;
    }

    @Override
    public AlertsDto createTemplateMapping( AlertsDto inAlertsDto )
    {
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM001;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            for ( EventTemplatesPojo eventTemplatesPojo : inAlertsDto.getEvTempalteList() )
            {
                if ( eventTemplatesPojo.isEditable() )
                {
                    EventTemplatesPojo evPojo = null;
                    criteria = session.createCriteria( EventTemplatesPojo.class );
                    criteria.createCriteria( "events", "evnt" );
                    criteria.add( Restrictions.eq( "evnt.eventId", eventTemplatesPojo.getEvents().getEventId() ) );
                    List<EventTemplatesPojo> eventTmplts = criteria.list();
                    if ( StringUtils.isValidObj( eventTmplts ) && !eventTmplts.isEmpty() )
                    {
                        evPojo = (EventTemplatesPojo) eventTmplts.get( 0 );
                    }
                    StringBuilder oldValues = new StringBuilder();
                    if ( StringUtils.isValidObj( evPojo ) )
                    {
                        oldValues.append( "EventDesc=" ).append( evPojo.getEvents().getEventDescription() )
                                .append( IAppConstants.COMMA ).append( "SMSTemplateName=" )
                                .append( evPojo.getSmsTemplate().getSmsTemplateName() ).append( IAppConstants.COMMA )
                                .append( "EmailTemplateName=" )
                                .append( evPojo.getEmailTemplate().getEmailTemplateName() )
                                .append( IAppConstants.COMMA );
                        evPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        evPojo.setLastModifiedBy( eventTemplatesPojo.getLastModifiedBy() );
                        evPojo.setEmailTemplate( eventTemplatesPojo.getEmailTemplate() );
                        evPojo.setSmsTemplate( eventTemplatesPojo.getSmsTemplate() );
                        evPojo.setEvents( eventTemplatesPojo.getEvents() );
                        evPojo.setEmailEnabled( eventTemplatesPojo.isEmailEnabled() );
                        evPojo.setSmsEnabled( eventTemplatesPojo.isSmsEnabled() );
                        session.update( evPojo );
                        evicts.put( EventTemplatesPojo.class.getName(), evPojo.getEventTemplateId() );
                        //for auditLog Maintain
                        StringBuilder newValues = new StringBuilder();
                        newValues.append( "EventDesc=" ).append( eventTemplatesPojo.getEvents().getEventDescription() )
                                .append( IAppConstants.COMMA ).append( "SMSTemplateName=" )
                                .append( eventTemplatesPojo.getSmsTemplate().getSmsTemplateName() )
                                .append( IAppConstants.COMMA ).append( "EmailTemplateName=" )
                                .append( eventTemplatesPojo.getEmailTemplate().getEmailTemplateName() )
                                .append( IAppConstants.COMMA );
                        String mappingId = String.valueOf( eventTemplatesPojo.getEventTemplateId() );
                        String createdBy = eventTemplatesPojo.getCreatedBy();
                        CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy, IAppConstants.DASH,
                                                        CrmActionEnum.UPDATED.getActionCode(),
                                                        CRMRequestType.ALERT.getRequestCode(),
                                                        inAlertsDto.getClientIPAddress(),
                                                        inAlertsDto.getServerIPAddress() );
                    }
                    else
                    {
                        eventTemplatesPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        session.save( eventTemplatesPojo );
                        evicts.put( EventTemplatesPojo.class.getName(), null );
                        //for auditLog Maintain
                        EventsPojo eventsPojo = (EventsPojo) session.get( EventsPojo.class, eventTemplatesPojo
                                .getEvents().getEventId() );
                        StringBuilder newValues = new StringBuilder();
                        oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                        newValues.append( "EventDesc=" ).append( eventsPojo.getEventDescription() )
                                .append( IAppConstants.COMMA ).append( "SMSTemplateName=" )
                                .append( eventTemplatesPojo.getSmsTemplate().getSmsTemplateName() )
                                .append( IAppConstants.COMMA ).append( "EmailTemplateName=" )
                                .append( eventTemplatesPojo.getEmailTemplate().getEmailTemplateName() )
                                .append( IAppConstants.COMMA );
                        String mappingId = String.valueOf( eventTemplatesPojo.getEventTemplateId() );
                        String createdBy = eventTemplatesPojo.getCreatedBy();
                        CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy, IAppConstants.DASH,
                                                        CrmActionEnum.GENERATED.getActionCode(),
                                                        CRMRequestType.ALERT.getRequestCode(),
                                                        inAlertsDto.getClientIPAddress(),
                                                        inAlertsDto.getServerIPAddress() );
                    }
                }
            }
            transaction.commit();
            LOGGER.info( "Event Mapping Update Successfully" );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Mapping Event Templates: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while Mapping Event Templates: ", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        inAlertsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inAlertsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inAlertsDto;
    }

    @Override
    public AlertsDto viewEvTemplateMapping( AlertsDto inAlertsDto )
    {
        List<EventTemplatesPojo> evList = new ArrayList<EventTemplatesPojo>();
        EmailTemplatePojo emPojo = new EmailTemplatePojo();
        EventsPojo eventsPojo = new EventsPojo();
        SmsTemplatePojo smsTemplatePojo = new SmsTemplatePojo();
        smsTemplatePojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        eventsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        emPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        ProjectsPojo pojo = new ProjectsPojo();
        pojo.setProjectType( CRMParameter.INTERNAL.getParameter() );
        pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        emPojo.setProjects( pojo );
        eventsPojo.setProjects( pojo );
        smsTemplatePojo.setProjects( pojo );
        EventTemplatesPojo eventTemplatesPojo = new EventTemplatesPojo();
        eventTemplatesPojo.setEmailTemplate( emPojo );
        eventTemplatesPojo.setSmsTemplate( smsTemplatePojo );
        eventTemplatesPojo.setEvents( eventsPojo );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( EventTemplatesPojo.class );
            criteria.createCriteria( "emailTemplate", "e" );
            criteria.createCriteria( "events", "ev" );
            criteria.createCriteria( "smsTemplate", "st" );
            criteria.add( Restrictions.eq( "e.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.add( Restrictions.eq( "ev.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "e.projects", "pe" );
            criteria.add( Restrictions.eq( "pe.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "pe.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "ev.projects", "pv" );
            criteria.add( Restrictions.eq( "pv.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "pv.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "st.projects", "ps" );
            criteria.add( Restrictions.eq( "ps.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "ps.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            evList = criteria.list();
            LOGGER.info( "EVENT Mapped list size:" + evList.size() );
            inAlertsDto.setEvTempalteList( evList );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while viewing Event Template: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inAlertsDto;
    }

    @SuppressWarnings("unchecked")
    @Override
    public EventTemplatesPojo getEventTemplate( long inEventID )
    {
        EventTemplatesPojo eventTemplatesPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( EventTemplatesPojo.class );
            criteria.createCriteria( "events", "ev" );
            criteria.createCriteria( "emailTemplate", "et" );
            criteria.createCriteria( "smsTemplate", "st" );
            criteria.add( Restrictions.eq( "ev.eventId", inEventID ) );
            criteria.add( Restrictions.eq( "ev.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "et.projects", "pe" );
            criteria.add( Restrictions.eq( "pe.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "pe.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "ev.projects", "pv" );
            criteria.add( Restrictions.eq( "pv.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "pv.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "st.projects", "ps" );
            criteria.add( Restrictions.eq( "ps.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "ps.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            List<EventTemplatesPojo> eventTemplatesPojoList = criteria.list();
            if ( !eventTemplatesPojoList.isEmpty() )
                eventTemplatesPojo = eventTemplatesPojoList.get( 0 );
            LOGGER.info( "Event Template Pojo for Event Id: " + inEventID + " is " + eventTemplatesPojo );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching Event Template Pojo for Event Id: " + inEventID, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return eventTemplatesPojo;
    }

    @Override
    public Map<String, String> getDataForParameters( Set<String> inSet )
    {
        Map<String, String> map = new HashMap<String, String>();
        int i = 0;
        for ( String str : inSet )
        {
            map.put( str, "Replacement" + i++ );
        }
        return map;
    }

    @Override
    public Map<String, String> getParameterByUserId( String inUseriD )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByUserId" );
            query.setString( "USER_ID", StringUtils.trimToEmpty( inUseriD ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For User Id:" + inUseriD + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching parameter value: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterByCustomerId( String CustomerId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByCustomerId" );
            query.setString( "CUSTOMER_ID", StringUtils.trimToEmpty( CustomerId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Customer Id:" + CustomerId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching CRM Customer Pojo for Customer Id: " + CustomerId, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterByLeadId( String inLeadId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByLeadId" );
            query.setString( "LMS_ID", StringUtils.trimToEmpty( inLeadId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Lead Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Lead Id :" + inLeadId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching parameter value For Lead: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlertStatusPojo> getAlertStatusPojos( int inMaxRetry )
    {
        List<AlertStatusPojo> alertStatusPojoList = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            LOGGER.info( "in getPendingSends Method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( AlertStatusPojo.class );
            Criterion emailsent = Restrictions.eq( "emailSent", CRMParameter.NO.getParameter() );
            Criterion emailMaxTry = Restrictions.lt( "smsRetry", inMaxRetry );
            Criterion emailFail = Restrictions.eq( "emailFailureReason",
                                                   CrmReasonsConstants.CONFIGUREDSERVERISSUE.getDescription() );
            Criterion emailCriterion = Restrictions.and( emailsent, emailMaxTry );
            Criterion emailCriterionF = Restrictions.and( emailFail, emailCriterion );
            Criterion smsSent = Restrictions.eq( "smsSent", CRMParameter.NO.getParameter() );
            Criterion smsMaxTry = Restrictions.lt( "emailRetry", inMaxRetry );
            Criterion smsFail = Restrictions.eq( "smsFailureReason",
                                                 CrmReasonsConstants.CONFIGUREDSERVERISSUE.getDescription() );
            Criterion smsCriterion = Restrictions.and( smsSent, smsMaxTry );
            Criterion smsCriterionF = Restrictions.and( smsFail, smsCriterion );
            criteria.add( Restrictions.or( emailCriterionF, smsCriterionF ) );
            criteria.add( Restrictions.eq( "alertType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( AlertStatusPojo.class.getName() );
            alertStatusPojoList = criteria.list();
            LOGGER.info( "Size of fetched List :: " + alertStatusPojoList.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in search Pending Retry : " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return alertStatusPojoList;
    }

    @Override
    public boolean saveAlertStatus( AlertStatusPojo inAlertStatusPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        boolean result = false;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            AlertStatusPojo alertStatusPojo = (AlertStatusPojo) session.get( AlertStatusPojo.class,
                                                                             inAlertStatusPojo.getAlertId() );
            if ( StringUtils.isValidObj( alertStatusPojo ) )
            {
                session.merge( inAlertStatusPojo );
                transaction.commit();
                LOGGER.info( "Successfully Save AlertStatusPojo, Updated Id :: " + inAlertStatusPojo.getAlertId() );
            }
            else
            {
                inAlertStatusPojo.setSentDateTime( Calendar.getInstance().getTime() );
                Long id = (Long) session.save( inAlertStatusPojo );
                if ( id > 0 )
                {
                    if ( CommonValidator.isValidCollection( inAlertStatusPojo.getCrmEmailAttachments() ) )
                    {
                        LOGGER.info( "Email attachment list size:: "
                                + inAlertStatusPojo.getCrmEmailAttachments().size() );
                        for ( CrmEmailAttachment emailAttachment : inAlertStatusPojo.getCrmEmailAttachments() )
                        {
                            emailAttachment.setAlertId( id );
                            session.save( emailAttachment );
                        }
                    }
                    transaction.commit();
                    result = true;
                    LOGGER.info( "Successfully Save AlertStatusPojo, Generated Id :: " + id );
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Saving Alert Status Pojo: ", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while Saving Alert Status Pojo: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( result )
            {
                HibernateUtil.evictAll( AlertStatusPojo.class.getName(), null, null );
            }
        }
        return result;
    }

    @Override
    public Map<String, String> getParameterByPartnerId( String inPartnerId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByPartnerId" );
            query.setString( "PARTNER_ID", StringUtils.trimToEmpty( inPartnerId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Partner Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Partner Id :" + inPartnerId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching parameter value For Partner: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterByCrfNumber( String inCrfId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByCrfNumber" );
            query.setString( "CRF_ID", StringUtils.trimToEmpty( inCrfId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "CAF Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For CAF Id :" + inCrfId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching parameter value For CAF: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterBySrNo( String inSrNo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterBySrNumber" );
            query.setString( "SR_ID", StringUtils.trimToEmpty( inSrNo ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "SrNo Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For SR No : " + inSrNo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fetching parameter value For SR No : ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterByLMSSrNo( String inSrNo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByLMSSrNumber" );
            query.setString( "LMS_SR_ID", StringUtils.trimToEmpty( inSrNo ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "LMS SrNo Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For LMS SR No : " + inSrNo );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fetching parameter value For LMS SR No : ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterByParamValue( String inParamName, String inParamValue )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByothers" );
            if ( StringUtils.isNotBlank( inParamName )
                    && !StringUtils.equals( inParamName, CRMRequestType.OTHERS.getRequestCode() ) )
            {
                query = session.getNamedQuery( "ParameterBy" + inParamName );
                query.setString( inParamName, StringUtils.trimToEmpty( inParamValue ) );
            }
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Parameter Map Values:" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Value: " + inParamValue );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error while fatching parameter value For SR No : ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getParameterByPaymentId( String inPaymentId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByPaymentId" );
            query.setString( "PAYMENT_ID", StringUtils.trimToEmpty( inPaymentId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Payment Id:" + inPaymentId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching parameter value for Payment Id: " + inPaymentId, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getFileStatusByFileId( String inFileID )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "FileStatusByFileId" );
            query.setLong( "CMS_FILE_ID", Long.valueOf( inFileID ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For File Id:" + inFileID + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching parameter value for File Id: " + inFileID, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public List<Map<String, String>> getFileRecordsStatusByFileId( String inFileID )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        List<Map<String, String>> parameterValueMap = new ArrayList<Map<String, String>>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "FileRecordsStatusByFileId" );
            query.setLong( "CMS_FILE_ID", Long.valueOf( inFileID ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList;
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For File Id:" + inFileID + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching parameter value for File Id: " + inFileID, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public void getEmailAttachment( AlertStatusPojo inAlertStatusPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmEmailAttachment> crmEmailAttachments = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmEmailAttachment.class );
            criteria.add( Restrictions.eq( "alertId", inAlertStatusPojo.getAlertId() ) );
            crmEmailAttachments = criteria.list();
            if ( CommonValidator.isValidCollection( crmEmailAttachments ) )
            {
                inAlertStatusPojo.setCrmEmailAttachments( (Set<CrmEmailAttachment>) crmEmailAttachments );
                LOGGER.info( "Size of Email attachment:- " + crmEmailAttachments.size() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while getting email attachment: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
    }

    @Override
    public Map<String, String> getParameterByOutageId( String mappingId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = new HashMap<String, String>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "ParameterByOutageId" );
            query.setLong( "OUTAGE_ID", Long.valueOf( mappingId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Outage Id:" + mappingId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching parameter value for Outage: " + mappingId, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }

    @Override
    public Map<String, String> getNetworkParameterByCustomerId( String mappingId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        Map<String, String> parameterValueMap = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "NetworkParameterByCustomerId" );
            query.setString( "CUSTOMER_ID", StringUtils.trimToEmpty( mappingId ) );
            query.setResultTransformer( Transformers.ALIAS_TO_ENTITY_MAP );
            List<Map<String, String>> valueMapList = query.list();
            if ( !valueMapList.isEmpty() )
            {
                parameterValueMap = valueMapList.get( 0 );
                LOGGER.info( "Map Values" + parameterValueMap );
            }
            else
            {
                LOGGER.info( "No Value Present In Database For Customer Id:" + mappingId + ":" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while fetching CRM Customer Pojo for Customer Id: " + mappingId, ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return parameterValueMap;
    }
}
