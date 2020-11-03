package com.np.tele.crm.config.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.CRMCustomerActivityActions;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMFunctionalBinStages;
import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.ext.pojos.CommonWorkflowPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmCustWaiverPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmPaymentCentresPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmShiftingWorkflowPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.CrmUserMappingPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;
import com.np.tele.crm.utils.BeanUtils;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class ConfigManagerDaoImpl
    implements IConfigManagerDao
{
    private static final Logger LOGGER = Logger.getLogger( ConfigManagerDaoImpl.class );

    @SuppressWarnings("unchecked")
    @Override
    public List<CrmMasterPojo> getConfiguration( String inConfigType, String inConfigSubType )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmMasterPojo> configList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmMasterPojo.class );
            if ( StringUtils.isNotBlank( inConfigType ) )
            {
                criteria.add( Restrictions.eq( "category", inConfigType ) );
                if ( StringUtils.isNotBlank( inConfigSubType ) )
                {
                    criteria.add( Restrictions.eq( "subCategory", inConfigSubType ) );
                }
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmMasterPojo.class.getName() );
                configList = criteria.list();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching configuration settings for Email & SMS: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return configList;
    }

    @Override
    public EventsPojo getEventsPojoByName( final String inEventName )
    {
        EventsPojo eventsPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            eventsPojo = new EventsPojo();
            LOGGER.info( "in getEventsPojoByName method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( EventsPojo.class );
            criteria.add( Restrictions.eq( "eventName", inEventName ) );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.createCriteria( "projects", "p" );
            criteria.add( Restrictions.eq( "p.projectType", CRMParameter.INTERNAL.getParameter() ) );
            criteria.add( Restrictions.eq( "p.status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            //criteria.setCacheable( true );
            //criteria.setCacheRegion( EventsPojo.class.getName() );
            List<EventsPojo> eventsPojoList = criteria.list();
            LOGGER.info( "Size of fetched List :: " + eventsPojoList.size() );
            if ( !eventsPojoList.isEmpty() )
            {
                eventsPojo = eventsPojoList.get( 0 );
                if ( eventsPojoList.size() > 1 )
                {
                    LOGGER.info( "Multiple Events available with same name" + inEventName );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in search Event Pojo " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return eventsPojo;
    }

    @Override
    public List<CrmMasterPojo> getMasterConfiguration( String inAlias, CRMStatusCode inCRMStatusCode )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmMasterPojo> configList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmMasterPojo.class );
            if ( StringUtils.isNotBlank( inAlias ) )
            {
                criteria.add( Restrictions.eq( "categoryAlias", inAlias ) );
                if ( StringUtils.isValidObj( inCRMStatusCode ) )
                {
                    criteria.add( Restrictions.eq( "status", inCRMStatusCode.getStatusCode() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmMasterPojo.class.getName() );
                configList = criteria.list();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching configuration settings for Email & SMS: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return configList;
    }

    @Override
    public ConfigDto getInbox( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            String mappingId = null;
            String mappingLike = null;
            String workFlowMapping = null;
            CrmCustomerDetailsPojo customerPojo = null;
            CrmSrTicketsPojo ticketPojo = null;
            LMSPojo leadPojo = null;
            boolean toProcess = true;
            if ( StringUtils.isNotBlank( inConfigDto.getRequestType() )
                    && StringUtils.isNotBlank( inConfigDto.getMappingId() ) )
            {
                CustomerProfile csp = CustomerProfile.getProfiler( inConfigDto.getRequestType() );
                switch ( csp )
                {
                    case CUSTOMER_ID:
                        customerPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                    inConfigDto.getMappingId() );
                        if ( StringUtils.isValidObj( customerPojo ) )
                        {
                            if ( StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inConfigDto.getInboxType() ) )
                            {
                                mappingId = customerPojo.getRecordId() + "";
                            }
                            else if ( StringUtils.equals( CRMRequestType.QRC.getRequestCode(),
                                                          inConfigDto.getInboxType() ) )
                            {
                                mappingLike = customerPojo.getCustomerId();
                            }
                            else if ( StringUtils.equals( CRMRequestType.SHIFTING.getRequestCode(),
                                                          inConfigDto.getInboxType() )
                                    || StringUtils.equals( CRMRequestType.WAIVER.getRequestCode(),
                                                           inConfigDto.getInboxType() ) )
                            {
                                workFlowMapping = customerPojo.getCustomerId();
                            }
                        }
                        else
                        {
                            toProcess = false;
                        }
                        break;
                    case CAF_ID:
                        customerPojo = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "crfId",
                                                                    inConfigDto.getMappingId() );
                        if ( StringUtils.isValidObj( customerPojo ) )
                        {
                            if ( StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inConfigDto.getInboxType() ) )
                            {
                                mappingId = customerPojo.getRecordId() + "";
                            }
                            else if ( StringUtils.equals( CRMRequestType.QRC.getRequestCode(),
                                                          inConfigDto.getInboxType() ) )
                            {
                                mappingLike = customerPojo.getCustomerId();
                            }
                            else if ( StringUtils.equals( CRMRequestType.SHIFTING.getRequestCode(),
                                                          inConfigDto.getInboxType() )
                                    || StringUtils.equals( CRMRequestType.WAIVER.getRequestCode(),
                                                           inConfigDto.getInboxType() ) )
                            {
                                workFlowMapping = customerPojo.getCustomerId();
                            }
                        }
                        else
                        {
                            toProcess = false;
                        }
                        break;
                    case LEAD_ID:
                        leadPojo = CRMServiceUtils.getDBValues( LMSPojo.class, "leadId", inConfigDto.getMappingId() );
                        if ( StringUtils.isValidObj( leadPojo ) )
                        {
                            mappingId = inConfigDto.getMappingId();
                        }
                        else
                        {
                            toProcess = false;
                        }
                        break;
                    case TICKET_ID:
                        ticketPojo = CRMServiceUtils.getDBValues( CrmSrTicketsPojo.class, "srId",
                                                                  inConfigDto.getMappingId() );
                        if ( StringUtils.isValidObj( ticketPojo ) )
                        {
                            mappingId = inConfigDto.getMappingId();
                        }
                        else
                        {
                            toProcess = false;
                        }
                        break;
                }
            }
            if ( !toProcess )
            {
                inConfigDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                inConfigDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                return inConfigDto;
            }
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmInboxPojo.class );
            if ( StringUtils.equals( inConfigDto.getInboxType(), CRMRequestType.FREEZE.getRequestCode() ) )
            {
                criteria.add( Restrictions.eq( "requestType", CRMRequestType.CAF.getRequestCode() ) );
            }
            else
            {
                criteria.add( Restrictions.eq( "requestType", inConfigDto.getInboxType() ) );
            }
            if ( CommonValidator.isValidCollection( inConfigDto.getStages() ) )
            {
                criteria.add( Restrictions.in( "stage", inConfigDto.getStages() ) );
            }
            if ( StringUtils.isNotBlank( inConfigDto.getInboxStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", inConfigDto.getInboxStatus() ) );
            }
            if ( StringUtils.isNotBlank( mappingId ) )
            {
                criteria.add( Restrictions.eq( "mappingId", mappingId ) );
            }
            if ( StringUtils.isNotBlank( mappingLike ) )
            {
                criteria.add( Restrictions.ilike( "mappingId", mappingLike, MatchMode.START ) );
            }
            criteria.addOrder( Order.asc( "createdTime" ) );
            List<CrmInboxPojo> crmInboxPojos = criteria.list();
            Map<String, Long> unReadDockets = new HashMap<String, Long>();
            Set<String> inboxMappingIdList = new HashSet<String>();
            Set<String> groupInboxMappingIdList = new HashSet<String>();
            for ( CrmInboxPojo crmInboxPojo : crmInboxPojos )
            {
                if ( StringUtils.equals( inConfigDto.getUserId(), crmInboxPojo.getUserId() ) )
                {
                    inboxMappingIdList.add( crmInboxPojo.getMappingId() );
                    if ( crmInboxPojo.isUnRead() )
                    {
                        unReadDockets.put( crmInboxPojo.getMappingId(), crmInboxPojo.getInboxId() );
                    }
                }
                else if ( StringUtils.isEmpty( crmInboxPojo.getUserId() ) )
                {
                    groupInboxMappingIdList.add( crmInboxPojo.getMappingId() );
                }
            }
            LOGGER.info( "Unread Dockets" + unReadDockets );
            if ( StringUtils.equals( CRMRequestType.LEAD.getRequestCode(), inConfigDto.getInboxType() ) )
            {
                if ( !inboxMappingIdList.isEmpty() )
                {
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        criteria = session.createCriteria( LMSPojo.class );
                        criteria.add( Restrictions.in( "leadId", inboxMappingIdList ) );
                        if ( StringUtils.isNotBlank( inConfigDto.getLeadStatus() ) )
                        {
                            criteria.add( Restrictions.eq( "status", inConfigDto.getLeadStatus() ) );
                        }
                        inConfigDto.setInboxLeadPojos( criteria.list() );
                    }
                    else
                    {
                        inConfigDto.setInboxLeadPojos( new ArrayList<LMSPojo>() );
                        inConfigDto.getInboxLeadPojos().add( leadPojo );
                    }
                    ListIterator<LMSPojo> lmsPojoIterator = inConfigDto.getInboxLeadPojos().listIterator();
                    while ( lmsPojoIterator.hasNext() )
                    {
                        LMSPojo lmsPojo = (LMSPojo) lmsPojoIterator.next();
                        if ( lmsPojo.getLmsStage().matches( CRMOperationStages.SALES.getCode() ) )
                        {
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedServices() )
                                    && !inConfigDto.getUserAssociatedServices().contains( lmsPojo.getProduct() ) )
                            {
                                lmsPojoIterator.remove();
                                continue;
                            }
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedPartners() )
                                    && !inConfigDto.getUserAssociatedPartners().contains( lmsPojo.getPartnerId() + "" ) )
                            {
                                lmsPojoIterator.remove();
                                continue;
                            }
                        }
                        if ( unReadDockets.containsKey( lmsPojo.getLeadId() ) )
                        {
                            lmsPojo.setUnRead( true );
                            lmsPojo.setInboxId( unReadDockets.get( lmsPojo.getLeadId() ) );
                        }
                    }
                }
                if ( !groupInboxMappingIdList.isEmpty() )
                {
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        criteria = session.createCriteria( LMSPojo.class );
                        criteria.add( Restrictions.in( "leadId", groupInboxMappingIdList ) );
                        if ( StringUtils.isNotBlank( inConfigDto.getLeadStatus() ) )
                        {
                            criteria.add( Restrictions.eq( "status", inConfigDto.getLeadStatus() ) );
                        }
                        inConfigDto.setGroupInboxLeadPojos( criteria.list() );
                    }
                    else
                    {
                        inConfigDto.setGroupInboxLeadPojos( new ArrayList<LMSPojo>() );
                        inConfigDto.getGroupInboxLeadPojos().add( leadPojo );
                    }
                    ListIterator<LMSPojo> lmsPojoIterator = inConfigDto.getGroupInboxLeadPojos().listIterator();
                    while ( lmsPojoIterator.hasNext() )
                    {
                        LMSPojo lmsPojo = (LMSPojo) lmsPojoIterator.next();
                        if ( lmsPojo.getLmsStage().matches( CRMOperationStages.SALES.getCode() ) )
                        {
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedServices() )
                                    && !inConfigDto.getUserAssociatedServices().contains( lmsPojo.getProduct() ) )
                            {
                                lmsPojoIterator.remove();
                                continue;
                            }
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedPartners() )
                                    && !inConfigDto.getUserAssociatedPartners().contains( lmsPojo.getPartnerId() + "" ) )
                            {
                                lmsPojoIterator.remove();
                                continue;
                            }
                        }
                    }
                }
            }
            else if ( StringUtils.equals( CRMRequestType.CAF.getRequestCode(), inConfigDto.getInboxType() ) )
            {
                if ( !inboxMappingIdList.isEmpty() )
                {
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        List<Long> inboxList = new ArrayList<Long>();
                        for ( String inboxValue : inboxMappingIdList )
                        {
                            inboxList.add( StringUtils.numericValue( inboxValue ) );
                        }
                        criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                        criteria.add( Restrictions.in( "recordId", inboxList ) );
                        if ( StringUtils.isNotBlank( inConfigDto.getLeadStatus() ) )
                        {
                            criteria.add( Restrictions.or( Restrictions.eq( "status", inConfigDto.getLeadStatus() ),
                                                           Restrictions.eq( "status", CRMStatusCode.ACTIVATION_PENDING
                                                                   .getStatusCode() ) ) );
                        }
                        inConfigDto.setInboxCRFPojos( criteria.list() );
                    }
                    else
                    {
                        inConfigDto.setInboxCRFPojos( new ArrayList<CrmCustomerDetailsPojo>() );
                        inConfigDto.getInboxCRFPojos().add( customerPojo );
                    }
                    if ( CommonValidator.isValidCollection( inConfigDto.getInboxCRFPojos() ) )
                    {
                        ListIterator<CrmCustomerDetailsPojo> crfPojoIterator = inConfigDto.getInboxCRFPojos()
                                .listIterator();
                        while ( crfPojoIterator.hasNext() )
                        {
                            CrmCustomerDetailsPojo crfPojo = (CrmCustomerDetailsPojo) crfPojoIterator.next();
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedServices() )
                                    && !inConfigDto.getUserAssociatedServices().contains( crfPojo.getProduct() ) )
                            {
                                crfPojoIterator.remove();
                                continue;
                            }
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedPartners() ) )
                            {
                                if ( crfPojo.getCrfStage()
                                        .matches( CRMOperationStages.INITIATE.getCode() + "|"
                                                          + CRMOperationStages.FT_REJECT.getCode() + "|"
                                                          + CRMOperationStages.NP_REJECT_SALES.getCode() + "|"
                                                          + CRMOperationStages.SP_REJECT_BP.getCode() )
                                        && !inConfigDto.getUserAssociatedPartners()
                                                .contains( crfPojo.getBusinessPartner() + "" ) )
                                {
                                    crfPojoIterator.remove();
                                    continue;
                                }
                                else if ( crfPojo.getCrfStage().matches( CRMOperationStages.NETWORK_PARTNER.getCode()
                                                                                 + "|"
                                                                                 + CRMOperationStages.SP_REJECT_NP
                                                                                         .getCode() )
                                        && !inConfigDto.getUserAssociatedPartners().contains( crfPojo.getNpId() + "" ) )
                                {
                                    crfPojoIterator.remove();
                                    continue;
                                }
                                else if ( crfPojo.getCrfStage().matches( CRMOperationStages.SERVICE_PARTNER.getCode() )
                                        && !inConfigDto.getUserAssociatedPartners().contains( crfPojo.getSpId() + "" ) )
                                {
                                    crfPojoIterator.remove();
                                    continue;
                                }
                            }
                            if ( unReadDockets.containsKey( crfPojo.getRecordId() + "" ) )
                            {
                                crfPojo.setUnRead( true );
                                crfPojo.setInboxId( unReadDockets.get( crfPojo.getRecordId() + "" ) );
                            }
                        }
                    }
                }
                if ( !groupInboxMappingIdList.isEmpty() )
                {
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        List<Long> listGroupInboxLong = new ArrayList<Long>();
                        for ( String valueGroupInbox : groupInboxMappingIdList )
                        {
                            listGroupInboxLong.add( Long.parseLong( valueGroupInbox ) );
                        }
                        criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                        criteria.add( Restrictions.in( "recordId", listGroupInboxLong ) );
                        if ( StringUtils.isNotBlank( inConfigDto.getLeadStatus() ) )
                        {
                            criteria.add( Restrictions.or( Restrictions.eq( "status", inConfigDto.getLeadStatus() ),
                                                           Restrictions.eq( "status", CRMStatusCode.ACTIVATION_PENDING
                                                                   .getStatusCode() ) ) );
                        }
                        inConfigDto.setGroupInboxCRFPojos( criteria.list() );
                    }
                    else
                    {
                        inConfigDto.setGroupInboxCRFPojos( new ArrayList<CrmCustomerDetailsPojo>() );
                        inConfigDto.getGroupInboxCRFPojos().add( customerPojo );
                    }
                    if ( CommonValidator.isValidCollection( inConfigDto.getGroupInboxCRFPojos() ) )
                    {
                        ListIterator<CrmCustomerDetailsPojo> crfPojoIterator = inConfigDto.getGroupInboxCRFPojos()
                                .listIterator();
                        while ( crfPojoIterator.hasNext() )
                        {
                            CrmCustomerDetailsPojo crfPojo = (CrmCustomerDetailsPojo) crfPojoIterator.next();
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedServices() )
                                    && !inConfigDto.getUserAssociatedServices().contains( crfPojo.getProduct() ) )
                            {
                                crfPojoIterator.remove();
                                continue;
                            }
                            if ( CommonValidator.isValidCollection( inConfigDto.getUserAssociatedPartners() ) )
                            {
                                if ( crfPojo.getCrfStage()
                                        .matches( CRMOperationStages.INITIATE.getCode() + "|"
                                                          + CRMOperationStages.FT_REJECT.getCode() + "|"
                                                          + CRMOperationStages.NP_REJECT_SALES.getCode() + "|"
                                                          + CRMOperationStages.SP_REJECT_BP.getCode() )
                                        && !inConfigDto.getUserAssociatedPartners()
                                                .contains( crfPojo.getBusinessPartner() + "" ) )
                                {
                                    crfPojoIterator.remove();
                                    continue;
                                }
                                else if ( crfPojo.getCrfStage().matches( CRMOperationStages.NETWORK_PARTNER.getCode()
                                                                                 + "|"
                                                                                 + CRMOperationStages.SP_REJECT_NP
                                                                                         .getCode() )
                                        && !inConfigDto.getUserAssociatedPartners().contains( crfPojo.getNpId() + "" ) )
                                {
                                    crfPojoIterator.remove();
                                    continue;
                                }
                                else if ( crfPojo.getCrfStage().matches( CRMOperationStages.SERVICE_PARTNER.getCode() )
                                        && !inConfigDto.getUserAssociatedPartners().contains( crfPojo.getSpId() + "" ) )
                                {
                                    crfPojoIterator.remove();
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            else if ( StringUtils.equals( CRMRequestType.FREEZE.getRequestCode(), inConfigDto.getInboxType() ) )
            {
                if ( !inboxMappingIdList.isEmpty() )
                {
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        List<Long> inboxList = new ArrayList<Long>();
                        for ( String inboxValue : inboxMappingIdList )
                        {
                            inboxList.add( StringUtils.numericValue( inboxValue ) );
                        }
                        criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                        criteria.add( Restrictions.in( "recordId", inboxList ) );
                        criteria.add( Restrictions.eq( "crfStage", CRMOperationStages.FREEZE.getCode() ) );
                        inConfigDto.setInboxFreezePojos( criteria.list() );
                    }
                    else
                    {
                        inConfigDto.setInboxFreezePojos( new ArrayList<CrmCustomerDetailsPojo>() );
                        inConfigDto.getInboxFreezePojos().add( customerPojo );
                    }
                }
                if ( !groupInboxMappingIdList.isEmpty() )
                {
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        List<Long> listGroupInboxLong = new ArrayList<Long>();
                        for ( String valueGroupInbox : groupInboxMappingIdList )
                        {
                            listGroupInboxLong.add( Long.parseLong( valueGroupInbox ) );
                        }
                        criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                        criteria.add( Restrictions.in( "recordId", listGroupInboxLong ) );
                        criteria.add( Restrictions.eq( "crfStage", CRMOperationStages.FREEZE.getCode() ) );
                        inConfigDto.setGroupInboxFreezePojos( criteria.list() );
                    }
                    else
                    {
                        inConfigDto.setGroupInboxFreezePojos( new ArrayList<CrmCustomerDetailsPojo>() );
                        inConfigDto.getGroupInboxFreezePojos().add( customerPojo );
                    }
                }
            }
            else if ( StringUtils.equals( CRMRequestType.QRC.getRequestCode(), inConfigDto.getInboxType() ) )
            {
                Object[] ticketStatus =
                { CRMStatusCode.OPEN.getStatusCode(), CRMStatusCode.REOPEN.getStatusCode() };
                if ( !inboxMappingIdList.isEmpty() )
                {
                    List<CrmSrTicketsPojo> srTicketsPojos = null;
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        criteria = session.createCriteria( CrmSrTicketsPojo.class );
                        criteria.add( Restrictions.in( "status", ticketStatus ) );
                        criteria.add( Restrictions.in( "srId", inboxMappingIdList ) );
                        criteria.add( Restrictions.le( "followupOn", Calendar.getInstance().getTime() ) );
                        srTicketsPojos = criteria.list();
                    }
                    else
                    {
                        srTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
                        srTicketsPojos.add( ticketPojo );
                    }
                    if ( StringUtils.isBlank( mappingLike ) )
                    {
                        getCustomerDetails( srTicketsPojos, session );
                    }
                    else
                    {
                        for ( CrmSrTicketsPojo crmSrTicketsPojo : srTicketsPojos )
                        {
                            crmSrTicketsPojo.setCustomerDetailsPojo( customerPojo );
                        }
                    }
                    inConfigDto.setInboxSrPojosPersonal( srTicketsPojos );
                }
                if ( !groupInboxMappingIdList.isEmpty() )
                {
                    List<CrmSrTicketsPojo> srTicketsPojos;
                    if ( StringUtils.isBlank( mappingId ) )
                    {
                        criteria = session.createCriteria( CrmSrTicketsPojo.class );
                        criteria.add( Restrictions.in( "status", ticketStatus ) );
                        criteria.add( Restrictions.in( "srId", groupInboxMappingIdList ) );
                        criteria.add( Restrictions.le( "followupOn", Calendar.getInstance().getTime() ) );
                        srTicketsPojos = criteria.list();
                    }
                    else
                    {
                        srTicketsPojos = new ArrayList<CrmSrTicketsPojo>();
                        srTicketsPojos.add( ticketPojo );
                    }
                    if ( StringUtils.isBlank( mappingLike ) )
                    {
                        getCustomerDetails( srTicketsPojos, session );
                    }
                    else
                    {
                        for ( CrmSrTicketsPojo crmSrTicketsPojo : srTicketsPojos )
                        {
                            crmSrTicketsPojo.setCurrentUser( "-" );
                            crmSrTicketsPojo.setCustomerDetailsPojo( customerPojo );
                        }
                    }
                    inConfigDto.setInboxSrPojosGroup( srTicketsPojos );
                    if ( CommonValidator.isValidCollection( inConfigDto.getInboxSrPojosPersonal() ) )
                    {
                        for ( CrmSrTicketsPojo srPojo : inConfigDto.getInboxSrPojosPersonal() )
                        {
                            srPojo.setCurrentUser( inConfigDto.getUserId() );
                            if ( unReadDockets.containsKey( srPojo.getSrId() + "" ) )
                            {
                                srPojo.setUnRead( true );
                                srPojo.setInboxId( unReadDockets.get( srPojo.getSrId() + "" ) );
                            }
                        }
                    }
                }
            }
            else if ( StringUtils.equals( CRMRequestType.SHIFTING.getRequestCode(), inConfigDto.getInboxType() ) )
            {
                if ( !inboxMappingIdList.isEmpty() )
                {
                    criteria = session.createCriteria( CrmShiftingWorkflowPojo.class );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.INPROCESS.getStatusCode() ) );
                    criteria.add( Restrictions.in( "workflowId", inboxMappingIdList ) );
                    if ( StringUtils.isNotBlank( workFlowMapping ) )
                    {
                        criteria.add( Restrictions.eq( "customerId", workFlowMapping ) );
                    }
                    List<CrmShiftingWorkflowPojo> crmShiftingWorkflowPojos = criteria.list();
                    List<CommonWorkflowPojo> commonWorkflowPojos = new ArrayList<CommonWorkflowPojo>();
                    for ( CrmShiftingWorkflowPojo shiftingWorkflowPojo : crmShiftingWorkflowPojos )
                    {
                        CommonWorkflowPojo commonWorkflowPojo = new CommonWorkflowPojo();
                        commonWorkflowPojo.setWorkflowId( shiftingWorkflowPojo.getWorkflowId() );
                        commonWorkflowPojo.setWorkflowStage( shiftingWorkflowPojo.getWorkflowStage() );
                        commonWorkflowPojo.setCustomerId( shiftingWorkflowPojo.getCustomerId() );
                        commonWorkflowPojo.setRequestType( CRMRequestType.SHIFTING.getRequestCode() );
                        commonWorkflowPojo.setPreviousStage( shiftingWorkflowPojo.getPreviousStage() );
                        commonWorkflowPojo.setCreatedTime( shiftingWorkflowPojo.getCreatedTime() );
                        commonWorkflowPojo.setCurrentBin( shiftingWorkflowPojo.getCurrentBin() );
                        if ( StringUtils.isEmpty( shiftingWorkflowPojo.getLastModifiedBy() ) )
                        {
                            commonWorkflowPojo.setPreviousStageOwner( shiftingWorkflowPojo.getCreatedBy() );
                        }
                        else
                            commonWorkflowPojo.setPreviousStageOwner( shiftingWorkflowPojo.getLastModifiedBy() );
                        if ( StringUtils.equals( shiftingWorkflowPojo.getShiftingType(),
                                                 CRMDisplayListConstants.WITHIN_PREMISES.getCode() ) )
                        {
                            commonWorkflowPojo.setWorkflowType( CRMDisplayListConstants.WITHIN_PREMISES.getValue() );
                        }
                        else
                            commonWorkflowPojo.setWorkflowType( CRMDisplayListConstants.OUTSIDE_PREMISES.getValue() );
                        if ( unReadDockets.containsKey( shiftingWorkflowPojo.getWorkflowId() ) )
                        {
                            commonWorkflowPojo.setUnRead( true );
                            commonWorkflowPojo.setInboxId( unReadDockets.get( shiftingWorkflowPojo.getWorkflowId() ) );
                        }
                        commonWorkflowPojos.add( commonWorkflowPojo );
                    }
                    inConfigDto.setCommonWorkflowPojos( commonWorkflowPojos );
                }
                if ( !groupInboxMappingIdList.isEmpty() )
                {
                    criteria = session.createCriteria( CrmShiftingWorkflowPojo.class );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.INPROCESS.getStatusCode() ) );
                    criteria.add( Restrictions.in( "workflowId", groupInboxMappingIdList ) );
                    if ( StringUtils.isNotBlank( workFlowMapping ) )
                    {
                        criteria.add( Restrictions.eq( "customerId", workFlowMapping ) );
                    }
                    List<CrmShiftingWorkflowPojo> crmShiftingWorkflowPojos = criteria.list();
                    List<CommonWorkflowPojo> commonWorkflowPojos = new ArrayList<CommonWorkflowPojo>();
                    for ( CrmShiftingWorkflowPojo shiftingWorkflowPojo : crmShiftingWorkflowPojos )
                    {
                        CommonWorkflowPojo commonWorkflowPojo = new CommonWorkflowPojo();
                        commonWorkflowPojo.setWorkflowId( shiftingWorkflowPojo.getWorkflowId() );
                        commonWorkflowPojo.setWorkflowStage( shiftingWorkflowPojo.getWorkflowStage() );
                        commonWorkflowPojo.setCustomerId( shiftingWorkflowPojo.getCustomerId() );
                        commonWorkflowPojo.setRequestType( CRMRequestType.SHIFTING.getRequestCode() );
                        commonWorkflowPojo.setPreviousStage( shiftingWorkflowPojo.getPreviousStage() );
                        commonWorkflowPojo.setCreatedTime( shiftingWorkflowPojo.getCreatedTime() );
                        commonWorkflowPojo.setCurrentBin( shiftingWorkflowPojo.getCurrentBin() );
                        if ( StringUtils.isEmpty( shiftingWorkflowPojo.getLastModifiedBy() ) )
                        {
                            commonWorkflowPojo.setPreviousStageOwner( shiftingWorkflowPojo.getCreatedBy() );
                        }
                        else
                            commonWorkflowPojo.setPreviousStageOwner( shiftingWorkflowPojo.getLastModifiedBy() );
                        if ( StringUtils.equals( shiftingWorkflowPojo.getShiftingType(),
                                                 CRMDisplayListConstants.WITHIN_PREMISES.getCode() ) )
                        {
                            commonWorkflowPojo.setWorkflowType( CRMDisplayListConstants.WITHIN_PREMISES.getValue() );
                        }
                        else
                            commonWorkflowPojo.setWorkflowType( CRMDisplayListConstants.OUTSIDE_PREMISES.getValue() );
                        commonWorkflowPojos.add( commonWorkflowPojo );
                    }
                    inConfigDto.setCommonWorkflowPojoGroup( commonWorkflowPojos );
                }
            }
            else if ( StringUtils.equals( CRMRequestType.WAIVER.getRequestCode(), inConfigDto.getInboxType() ) )
            {
                if ( !inboxMappingIdList.isEmpty() )
                {
                    criteria = session.createCriteria( CrmCustWaiverPojo.class );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.OPEN.getStatusCode() ) );
                    criteria.add( Restrictions.in( "workflowId", inboxMappingIdList ) );
                    if ( StringUtils.isNotBlank( workFlowMapping ) )
                    {
                        criteria.add( Restrictions.eq( "customerId", workFlowMapping ) );
                    }
                    List<CrmCustWaiverPojo> crmCustWaiverPojoList = criteria.list();
                    List<CommonWorkflowPojo> commonWorkflowPojos = new ArrayList<CommonWorkflowPojo>();
                    for ( CrmCustWaiverPojo crmCustWaiverPojo : crmCustWaiverPojoList )
                    {
                        CommonWorkflowPojo commonWorkflowPojo = new CommonWorkflowPojo();
                        commonWorkflowPojo.setWorkflowId( crmCustWaiverPojo.getWorkflowId() );
                        commonWorkflowPojo.setWorkflowStage( crmCustWaiverPojo.getWorkflowStage() );
                        commonWorkflowPojo.setCustomerId( crmCustWaiverPojo.getCustomerId() );
                        commonWorkflowPojo.setRequestType( CRMRequestType.WAIVER.getRequestCode() );
                        commonWorkflowPojo.setWorkflowType( crmCustWaiverPojo.getWaiverType() );
                        commonWorkflowPojo.setPreviousStage( crmCustWaiverPojo.getPreviousStage() );
                        commonWorkflowPojo.setCreatedTime( crmCustWaiverPojo.getCreatedTime() );
                        if ( StringUtils.isEmpty( crmCustWaiverPojo.getLastModifiedBy() ) )
                        {
                            commonWorkflowPojo.setPreviousStageOwner( crmCustWaiverPojo.getCreatedBy() );
                        }
                        else
                            commonWorkflowPojo.setPreviousStageOwner( crmCustWaiverPojo.getLastModifiedBy() );
                        if ( unReadDockets.containsKey( crmCustWaiverPojo.getWorkflowId() ) )
                        {
                            commonWorkflowPojo.setUnRead( true );
                            commonWorkflowPojo.setInboxId( unReadDockets.get( crmCustWaiverPojo.getWorkflowId() ) );
                        }
                        commonWorkflowPojos.add( commonWorkflowPojo );
                    }
                    inConfigDto.setCommonWorkflowPojos( commonWorkflowPojos );
                }
                if ( !groupInboxMappingIdList.isEmpty() )
                {
                    criteria = session.createCriteria( CrmCustWaiverPojo.class );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.OPEN.getStatusCode() ) );
                    criteria.add( Restrictions.in( "workflowId", groupInboxMappingIdList ) );
                    if ( StringUtils.isNotBlank( workFlowMapping ) )
                    {
                        criteria.add( Restrictions.eq( "customerId", workFlowMapping ) );
                    }
                    List<CrmCustWaiverPojo> crmCustWaiverPojoList = criteria.list();
                    List<CommonWorkflowPojo> commonWorkflowPojos = new ArrayList<CommonWorkflowPojo>();
                    for ( CrmCustWaiverPojo crmCustWaiverPojo : crmCustWaiverPojoList )
                    {
                        CommonWorkflowPojo commonWorkflowPojo = new CommonWorkflowPojo();
                        commonWorkflowPojo.setWorkflowId( crmCustWaiverPojo.getWorkflowId() );
                        commonWorkflowPojo.setWorkflowStage( crmCustWaiverPojo.getWorkflowStage() );
                        commonWorkflowPojo.setCustomerId( crmCustWaiverPojo.getCustomerId() );
                        commonWorkflowPojo.setRequestType( CRMRequestType.WAIVER.getRequestCode() );
                        commonWorkflowPojo.setWorkflowType( crmCustWaiverPojo.getWaiverType() );
                        commonWorkflowPojo.setPreviousStage( crmCustWaiverPojo.getPreviousStage() );
                        commonWorkflowPojo.setCreatedTime( crmCustWaiverPojo.getCreatedTime() );
                        if ( StringUtils.isEmpty( crmCustWaiverPojo.getLastModifiedBy() ) )
                        {
                            commonWorkflowPojo.setPreviousStageOwner( crmCustWaiverPojo.getCreatedBy() );
                        }
                        else
                            commonWorkflowPojo.setPreviousStageOwner( crmCustWaiverPojo.getLastModifiedBy() );
                        commonWorkflowPojos.add( commonWorkflowPojo );
                    }
                    inConfigDto.setCommonWorkflowPojoGroup( commonWorkflowPojos );
                }
                //  LOGGER.info( "Size Of Waiver Inbox List:: " + inConfigDto.getCommonWorkflowPojos().size() );
                // LOGGER.info( "Size Of Waiver Group Inbox List:: " + inConfigDto.getCommonWorkflowPojoGroup() );
            }
            inConfigDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            inConfigDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            inConfigDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inConfigDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Error while fetching inbox for user: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inConfigDto;
    }

    private void getCustomerDetails( List<CrmSrTicketsPojo> inSrTicketsPojos, Session inSession )
    {
        if ( StringUtils.isValidObj( inSrTicketsPojos ) && !inSrTicketsPojos.isEmpty() )
        {
            for ( CrmSrTicketsPojo crmSrTicketsPojo : inSrTicketsPojos )
            {
                Criteria criteria = null;
                if ( StringUtils.equals( crmSrTicketsPojo.getModuleType(), CRMRequestType.QRC.getRequestCode() ) )
                {
                    criteria = inSession.createCriteria( CrmCustomerDetailsPojo.class );
                    criteria.add( Restrictions.eq( "customerId", crmSrTicketsPojo.getMappingId() ) );
                    criteria.setCacheable( true );
                    criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
                    List<CrmCustomerDetailsPojo> crmCustomerDetailsPojo = criteria.list();
                    if ( CommonValidator.isValidCollection( crmCustomerDetailsPojo ) )
                    {
                        crmSrTicketsPojo.setCustomerDetailsPojo( crmCustomerDetailsPojo.get( 0 ) );
                    }
                    crmSrTicketsPojo.setCurrentUser( "-" );
                }
                else
                {
                    criteria = inSession.createCriteria( LMSPojo.class );
                    criteria.add( Restrictions.eq( "leadId", crmSrTicketsPojo.getMappingId() ) );
                    List<LMSPojo> crmLmsDetailsPojo = criteria.list();
                    if ( CommonValidator.isValidCollection( crmLmsDetailsPojo ) )
                    {
                        crmSrTicketsPojo.setLmsPojo( crmLmsDetailsPojo.get( 0 ) );
                    }
                    crmSrTicketsPojo.setCurrentUser( "-" );
                }
            }
        }
    }

    @Override
    public List<AppointmentPojo> getMappingIdtByAppointMents( String inMappingId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<AppointmentPojo> appointmentPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( AppointmentPojo.class );
            criteria.add( Restrictions.eq( "mappingId", inMappingId ) );
            criteria.addOrder( Order.desc( "createdTime" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( AppointmentPojo.class.getName() );
            appointmentPojos = criteria.list();
            LOGGER.info( "Size Of Appointment Pojo " + appointmentPojos.size() + " For Mapping Id :: " + inMappingId );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getMappingIdtByAppointMents method", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return appointmentPojos;
    }

    @Override
    public List<RemarksPojo> geRemarksByMappingId( String inMappingId )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<RemarksPojo> remarksPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( RemarksPojo.class ).add( Restrictions.eq( "mappingId", inMappingId ) );
            criteria.addOrder( Order.desc( "createdTime" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( RemarksPojo.class.getName() );
            remarksPojos = criteria.list();
            LOGGER.info( "Size Of Remarks Pojo " + remarksPojos.size() + " For Mapping Id :: " + inMappingId );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in geMappingIdByRemarkssk method", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return remarksPojos;
    }

    public static void main( String[] args )
    {
        /*ConfigManagerDaoImpl configManagerDaoImpl = new ConfigManagerDaoImpl();
        List<CrmRcaReasonPojo> crmRcaReasonPojos = configManagerDaoImpl.getRCAReasonList( CRMParameter.LMS
                .getParameter(), CRMParameter.REFERRAL.getParameter() );
        System.out.println( "Size ::" + crmRcaReasonPojos.size() );
        /*ConfigDto configDto = configManagerDaoImpl.getInbox( "nawab", "lead", "A", "A" );
        System.out.println( "Size ::" + crmRcaReasonPojos.size() );
        ConfigDto configDto = configManagerDaoImpl.getInbox( "nawab", "lead", "A", "A" );
        System.out.println( "inbox size :: " + configDto.getInboxLeadPojos().size() + " ::group inbox size :: "
                + configDto.getGroupInboxLeadPojos().size() );
        //configManagerDaoImpl.getMappingUsers( "Area.Manager", "BP" );
        */
        //        SessionFactory factory = HibernateUtil.getSessionFactory();
        //        Session session = factory.openSession();
        //        Set<String> inboxMappingIdList = new HashSet<String>();
        //        inboxMappingIdList.add( 29 + "" );
        //        inboxMappingIdList.add( 30 + "" );
        //        List<Long> list = new ArrayList<Long>();
        //        for ( String value : inboxMappingIdList )
        //        {
        //            list.add( Long.parseLong( value ) );
        //        }
        //        Criteria criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
        //        criteria.add( Restrictions.in( "recordId", list ) );
        //        System.out.println( "size :::" + criteria.list().size() );
        /*Set liststring = new HashSet<String>();
        liststring.add( "123" );
        liststring.add( "234" );
        System.out.println( ":::::" + (HashSet<Long>) liststring );
        List<Long> listLong = new ArrayList<Long>( liststring );
        System.out.println( "list " + listLong );
        System.out.println();*/
    }

    @Override
    public boolean saveAppointment( AppointmentPojo inAppointmentPojo )
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
            inAppointmentPojo.setCreatedTime( Calendar.getInstance().getTime() );
            Long generatedId = (Long) session.save( inAppointmentPojo );
            LOGGER.info( "Generated Id for Appointment " + generatedId );
            transaction.commit();
            result = true;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saveAppointment method", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in saveAppointment method", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( result )
            {
                HibernateUtil.evictAll( AppointmentPojo.class.getName(), null, null );
            }
        }
        return result;
    }

    @Override
    public ConfigDto getMappingUsers( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        CRMServiceCode statusCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserMappingPojo.class );
            criteria.add( Restrictions.eq( "userId", inConfigDto.getUserId() ) )
                    .add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            //here we will add the check for mapping type
            ProjectionList proList = Projections.projectionList();
            proList.add( Projections.property( "mappedUserId" ) );
            criteria.setProjection( proList );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUserMappingPojo.class.getName() );
            List<String> crmMappingList = criteria.list();
            if ( !crmMappingList.isEmpty() )
            {
                LOGGER.info( "User Found In Table CrmUserMapping  " + crmMappingList.size() );
                LOGGER.info( "User Ids " + crmMappingList.get( 0 ) + " ;; functin bin :: " + inConfigDto.getUserType() );
                criteria = session
                        .createCriteria( CrmUserPojo.class )
                        .add( Restrictions.in( "userId", crmMappingList ) )
                        .add( Restrictions.ilike( "functionalBin", "," + inConfigDto.getUserType() + ",",
                                                  MatchMode.ANYWHERE ) )
                        .add( Restrictions.in( "status", new String[]
                              { CRMStatusCode.ACTIVE.getStatusCode(), CRMStatusCode.NEW.getStatusCode(),
                                      CRMStatusCode.PENDING.getStatusCode() } ) );
                ProjectionList proListForUser = Projections.projectionList();
                proListForUser.add( Projections.property( "userId" ) );
                criteria.setProjection( proListForUser );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmUserPojo.class.getName() );
                List<String> userList = criteria.list();
                if ( !userList.isEmpty() )
                {
                    inConfigDto.setUserIds( userList );
                    LOGGER.info( "Find User iDs :: " + userList );
                    statusCode = CRMServiceCode.CRM001;
                }
                else
                {
                    LOGGER.info( "No  Mapped User Found  in Crm User Table For User ::" + inConfigDto.getUserId() );
                    statusCode = CRMServiceCode.CRM202;
                }
            }
            else
            {
                LOGGER.info( "No  Mapped User Found For User ::" + inConfigDto.getUserId() );
                statusCode = CRMServiceCode.CRM202;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while getMappingUsers method", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getMappingUsers method", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
        }
        inConfigDto.setStatusCode( statusCode.getStatusCode() );
        inConfigDto.setStatusDesc( statusCode.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public ConfigDto insertAuditLog( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode code = null;
        try
        {
            LOGGER.info( "In Insert Audit Log Method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            inConfigDto.getAuditLogPojo().setCreatedTime( Calendar.getInstance().getTime() );
            Long generatedId = (Long) session.save( inConfigDto.getAuditLogPojo() );
            transaction.commit();
            LOGGER.info( "Successfully Generate Id For Audit Log " + generatedId );
            code = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while insert Log mehtod", ex );
            code = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            code = CRMServiceCode.CRM999;
            LOGGER.info( "Getting error in insert Log mehtod " + ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == code )
            {
                HibernateUtil.evictAll( CrmAuditLogPojo.class.getName(), null, null );
            }
        }
        inConfigDto.setStatusCode( code.getStatusCode() );
        inConfigDto.setStatusDesc( code.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public ConfigDto searchAuditLog( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        CRMServiceCode code = null;
        try
        {
            LOGGER.info( "In Search Audit Log Method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            Criteria criteria = session.createCriteria( CrmAuditLogPojo.class );
            if ( StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getMappingId() ) )
            {
                criteria.add( Restrictions.eq( "mappingId", inConfigDto.getAuditLogPojo().getMappingId() ) );
            }
            if ( StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getModule() ) )
            {
                criteria.add( Restrictions.eq( "module", inConfigDto.getAuditLogPojo().getModule() ) );
            }
            if ( StringUtils.isNotBlank( inConfigDto.getAuditLogPojo().getCreatedBy() ) )
            {
                criteria.add( Restrictions.eq( "createdBy", inConfigDto.getAuditLogPojo().getCreatedBy() ) );
            }
            criteria.addOrder( Order.desc( "createdTime" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmAuditLogPojo.class.getName() );
            List<CrmAuditLogPojo> auditLogPojos = criteria.list();
            inConfigDto.setAuditLogPojos( auditLogPojos );
            LOGGER.info( "Size Of Search Audit Log Pojos " + auditLogPojos.size() );
            code = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            code = CRMServiceCode.CRM999;
            LOGGER.error( "Getting error in insert Log mehtod " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inConfigDto.setStatusCode( code.getStatusCode() );
        inConfigDto.setStatusDesc( code.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public List<CrmRcaReasonPojo> getRCAReasonList( String inConfigType, String inConfigSubType )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmRcaReasonPojo> crmRcaReasonPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmRcaReasonPojo.class );
            if ( StringUtils.isNotBlank( inConfigType ) )
            {
                System.out.println( inConfigType + " :: " + inConfigSubType );
                criteria.add( Restrictions.eq( "category", inConfigType ) );
                if ( StringUtils.isNotBlank( inConfigSubType ) )
                {
                    criteria.add( Restrictions.eq( "subCategory", inConfigSubType ) );
                }
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmRcaReasonPojo.class.getName() );
                crmRcaReasonPojos = criteria.list();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error while fetching CRM RCA Reason List: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmRcaReasonPojos;
    }

    @Override
    public boolean changeInboxBin( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();;
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( changeInboxBin( inConfigDto, session ) )
            {
                transaction.commit();
                return true;
            }
        }
        catch ( HibernateException ex )
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in changeInboxBin method", ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            HibernateUtil.evictAll( CrmInboxPojo.class.getName(), null, null );
        }
        return false;
    }

    @Override
    public boolean changeInboxBin( ConfigDto inConfigDto, Session inSession )
    {
        Criteria criteria = null;
        List<CrmInboxPojo> crmInboxPojos = null;
        boolean result = false;
        try
        {
            //if fromUserId is coming means it in group inbox
            // here we are searching current ownership
            //now we will search the all stages for particular bin from crmfunctionalbinstages
            LOGGER.info( "@InboxRequest: Request Type=" + inConfigDto.getRequestType() + ", Mapping ID="
                    + inConfigDto.getMappingId() + ", From Stage=" + inConfigDto.getFromStage() + ", To Stage="
                    + inConfigDto.getTostage() );
            if ( StringUtils.isNotBlank( inConfigDto.getFromStage() )
                    && !StringUtils.equals( inConfigDto.getFromStage(), inConfigDto.getTostage() ) )
            {
                criteria = inSession.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( IAppConstants.PARAM_MAPPING_ID, inConfigDto.getMappingId() ) )
                        .add( Restrictions.eq( "requestType", inConfigDto.getRequestType() ) )
                        .add( Restrictions.eq( "stage", inConfigDto.getFromStage() ) );
                crmInboxPojos = criteria.list();
                if ( crmInboxPojos.size() > 0 )
                {
                    CrmInboxPojo crmInboxPojo = crmInboxPojos.get( 0 );
                    LOGGER.info( "@InboxRequest:From Inbox=" + crmInboxPojo );
                    crmInboxPojo.setLastModifiedBy( inConfigDto.getUserId() );
                    crmInboxPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmInboxPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    inSession.update( crmInboxPojo );
                    //update current statust
                }
                else
                {
                    CrmInboxPojo crmInboxPojoForInsert = new CrmInboxPojo();
                    crmInboxPojoForInsert.setMappingId( inConfigDto.getMappingId() );
                    crmInboxPojoForInsert.setRequestType( inConfigDto.getRequestType() );
                    crmInboxPojoForInsert.setUserId( inConfigDto.getFromUserId() );
                    crmInboxPojoForInsert.setStage( inConfigDto.getFromStage() );
                    crmInboxPojoForInsert.setCreatedBy( inConfigDto.getUserId() );
                    crmInboxPojoForInsert.setPartnerId( inConfigDto.getPartnerId() );
                    crmInboxPojoForInsert.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                    crmInboxPojoForInsert.setCreatedTime( Calendar.getInstance().getTime() );
                    inSession.save( crmInboxPojoForInsert );
                    LOGGER.info( "@InboxRequest:From Inbox (Insert)=" + crmInboxPojoForInsert );
                }
            }
            //check for to
            if ( ! ( ( StringUtils.equals( CRMOperationStages.CANCEL.getCode(), inConfigDto.getTostage() ) || StringUtils
                    .equals( CRMOperationStages.ON_BOARD.getCode(), inConfigDto.getTostage() ) ) && StringUtils
                    .equals( CRMRequestType.CAF.getRequestCode(), inConfigDto.getRequestType() ) ) )
            {
                criteria = inSession.createCriteria( CrmInboxPojo.class )
                        .add( Restrictions.eq( IAppConstants.PARAM_MAPPING_ID, inConfigDto.getMappingId() ) )
                        .add( Restrictions.eq( "requestType", inConfigDto.getRequestType() ) )
                        .add( Restrictions.eq( "stage", inConfigDto.getTostage() ) );
                List<CrmInboxPojo> crmInboxPojosForTo = criteria.list();
                if ( !crmInboxPojosForTo.isEmpty() )
                {
                    CrmQrcDto qrcDto = null;
                    CrmAuditLogPojo crmAuditLogPojo = null;
                    CrmInboxPojo crmInboxPojoForTo = crmInboxPojosForTo.get( 0 );
                    LOGGER.info( "@InboxRequest:To Inbox=" + crmInboxPojoForTo );
                    crmInboxPojoForTo.setLastModifiedBy( inConfigDto.getUserId() );
                    crmInboxPojoForTo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmInboxPojoForTo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    if ( StringUtils.equals( inConfigDto.getUserIdChange(), IAppConstants.NO ) )
                    {
                        LOGGER.info( "No Need To Change User ID" );//TODO
                        if ( !StringUtils.equals( inConfigDto.getFromStage(), inConfigDto.getTostage() ) )
                        {
                            crmInboxPojoForTo.setUserId( null );
                            LOGGER.info( "Previous entry send to default group inbox" );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Need To update User Id" );
                        LOGGER.info( inConfigDto.getFromStage() + "/" + inConfigDto.getTostage() + "/"
                                + crmInboxPojoForTo.getRequestType() );
                        try
                        {
                            if ( StringUtils.equals( inConfigDto.getFromStage(), inConfigDto.getTostage() )
                                    && StringUtils.equals( CRMRequestType.QRC.getRequestCode(),
                                                           crmInboxPojoForTo.getRequestType() ) )
                            {
                                CrmTicketHistoryPojo ticketHistory = new CrmTicketHistoryPojo();
                                ticketHistory.setTicketId( crmInboxPojoForTo.getMappingId() );
                                ticketHistory.setAction( CrmActionEnum.ASSIGNED.getActionCode() );
                                ticketHistory.setRemarks( "Assigning Ticket" );
                                ticketHistory.setFromUser( crmInboxPojoForTo.getUserId() );
                                ticketHistory.setToUser( inConfigDto.getToUserId() );
                                ticketHistory.setCreatedBy( inConfigDto.getUserId() );
                                ticketHistory.setFromBin( StringUtils.numericValue( inConfigDto.getFromStage() ) );
                                ticketHistory.setToBin( StringUtils.numericValue( inConfigDto.getTostage() ) );
                                qrcDto = new CrmQrcDto();
                                qrcDto.setTicketHistory( ticketHistory );
                            }
                            if ( StringUtils.equals( inConfigDto.getFromStage(), inConfigDto.getTostage() )
                                    && ( StringUtils.equals( CRMRequestType.LEAD.getRequestCode(),
                                                             crmInboxPojoForTo.getRequestType() ) || StringUtils
                                            .equals( CRMRequestType.CAF.getRequestCode(),
                                                     crmInboxPojoForTo.getRequestType() ) ) )
                            {
                                crmAuditLogPojo = new CrmAuditLogPojo();
                                crmAuditLogPojo
                                        .setOldValues( StringUtils.isNotBlank( crmInboxPojoForTo.getUserId() )
                                                                                                              ? crmInboxPojoForTo
                                                                                                                      .getUserId()
                                                                                                              : IAppConstants.DASH );
                                crmAuditLogPojo
                                        .setNewValues( StringUtils.isNotBlank( inConfigDto.getToUserId() )
                                                                                                          ? inConfigDto
                                                                                                                  .getToUserId()
                                                                                                          : IAppConstants.DASH );
                                crmAuditLogPojo.setModule( crmInboxPojoForTo.getRequestType() );
                                crmAuditLogPojo.setMappingId( crmInboxPojoForTo.getMappingId() );
                                crmAuditLogPojo.setRemarks( "Assigning CAF" );
                                if ( StringUtils.equals( CRMRequestType.LEAD.getRequestCode(),
                                                         crmInboxPojoForTo.getRequestType() ) )
                                {
                                    crmAuditLogPojo.setModule( CRMRequestType.LMS.getRequestCode() );
                                    crmAuditLogPojo.setMappingId( StringUtils.remove( crmInboxPojoForTo.getMappingId(),
                                                                                      "L" ) );
                                    if ( StringUtils.equals( crmAuditLogPojo.getNewValues(), IAppConstants.DASH ) )
                                    {
                                        crmAuditLogPojo.setRemarks( "Assigning Lead to group" );
                                    }//remarks change to get exact audit log history for SLA calculation
                                    else
                                    {
                                        crmAuditLogPojo.setRemarks( "Assigning Lead to self" );
                                    }// don't change text as same string is use to fetch audit log history in SLA calculation
                                }
                                crmAuditLogPojo.setCreatedBy( inConfigDto.getUserId() );
                                crmAuditLogPojo.setEvents( CrmActionEnum.ASSIGNED.getActionCode() );
                                crmAuditLogPojo.setClientIp( inConfigDto.getClientIPAddress() );
                                crmAuditLogPojo.setServerIp( inConfigDto.getServerIPAddress() );
                                crmAuditLogPojo.setFromBin( inConfigDto.getFromStage() );
                                crmAuditLogPojo.setToBin( inConfigDto.getTostage() );
                            }
                        }
                        catch ( Exception ex )
                        {
                            LOGGER.error( "Unable to create history for Ticket assignment", ex );
                        }
                        crmInboxPojoForTo.setUserId( inConfigDto.getToUserId() );
                    }
                    crmInboxPojoForTo.setUnRead( true );
                    if ( inConfigDto.getPartnerId() > 0 )
                    {
                        crmInboxPojoForTo.setPartnerId( inConfigDto.getPartnerId() );
                    }
                    //crmInboxPojoForTo.setStage( inConfigDto.getTostage() );
                    inSession.update( crmInboxPojoForTo );
                    if ( StringUtils.isValidObj( qrcDto ) )
                    {
                        CRMServicesProxy
                                .getInstance()
                                .getCrmQrcService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                                .ticketOperations( CrmActionEnum.ASSIGNED.getActionCode(),
                                                   CrmTicketHistoryPojo.class.getSimpleName(), qrcDto );
                    }
                    if ( StringUtils.isValidObj( crmAuditLogPojo ) )
                    {
                        CRMServiceUtils.createAuditLog( crmAuditLogPojo );
                        LOGGER.info( "Audit log inserted successfully for change inbox." );
                    }
                }
                else
                {
                    CrmInboxPojo crmInboxPojoForInsert = new CrmInboxPojo();
                    crmInboxPojoForInsert.setMappingId( inConfigDto.getMappingId() );
                    crmInboxPojoForInsert.setRequestType( inConfigDto.getRequestType() );
                    if ( StringUtils.isNotBlank( inConfigDto.getToUserId() ) )
                    {
                        crmInboxPojoForInsert.setUserId( inConfigDto.getToUserId() );
                    }
                    if ( inConfigDto.getPartnerId() > 0 )
                    {
                        crmInboxPojoForInsert.setPartnerId( inConfigDto.getPartnerId() );
                        if ( !StringUtils.equals( inConfigDto.getRequestType(), CRMRequestType.LEAD.getRequestCode() )
                                || !StringUtils.equals( inConfigDto.getRequestType(),
                                                        CRMRequestType.CAF.getRequestCode() ) )
                        {
                            crmInboxPojoForInsert.setPartnerType( CRMFunctionalBinStages
                                    .getStageBySubStage( inConfigDto.getTostage() ) );
                        }
                    }
                    crmInboxPojoForInsert.setStage( inConfigDto.getTostage() );
                    crmInboxPojoForInsert.setCreatedBy( inConfigDto.getUserId() );
                    crmInboxPojoForInsert.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmInboxPojoForInsert.setCreatedTime( Calendar.getInstance().getTime() );
                    LOGGER.info( "@InboxRequest:To Inbox (Insert)=" + crmInboxPojoForInsert );
                    inSession.save( crmInboxPojoForInsert );
                }
            }
            LOGGER.info( "Successfully Transaction for change Bin Done" );
            result = true;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in changeInboxBin method", ex );
            result = false;
        }
        return result;
    }

    @Override
    public ConfigDto getCustomerDetails( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode statusCode = null;
        ConfigDto configDto = invalidRequest();
        boolean toProcess = false;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
            CrmCustomerDetailsPojo inCrmCustomerDetailsPojo = inConfigDto.getCustomerDetail();
            if ( StringUtils.isValidObj( inCrmCustomerDetailsPojo ) )
            {
                if ( StringUtils.isNotBlank( inCrmCustomerDetailsPojo.getCrfId() ) )
                {
                    criteria.add( Restrictions.eq( "crfId", StringUtils.upperCase( inCrmCustomerDetailsPojo.getCrfId() ) ) );
                }
                if ( StringUtils.isNotBlank( inCrmCustomerDetailsPojo.getCustomerId() ) )
                {
                    criteria.add( Restrictions.eq( "customerId", inCrmCustomerDetailsPojo.getCustomerId() ) );
                }
                toProcess = true;
            }
            else if ( StringUtils.isValidObj( inConfigDto.getUserIds() ) && !inConfigDto.getUserIds().isEmpty() )
            {
                criteria.add( Restrictions.in( "customerId", inConfigDto.getUserIds() ) );
                toProcess = true;
            }
            else if ( StringUtils.isValidObj( inConfigDto.getCrfIds() ) && !inConfigDto.getCrfIds().isEmpty() )
            {
                criteria.add( Restrictions.in( "crfId", inConfigDto.getCrfIds() ) );
                toProcess = true;
            }
            if ( toProcess )
            {
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustomerDetailsPojo.class.getName() );
                List<CrmCustomerDetailsPojo> customerDetails = criteria.list();
                if ( !customerDetails.isEmpty() )
                {
                    statusCode = CRMServiceCode.CRM001;
                    configDto.setCustomerDetailPojos( customerDetails );
                }
                else
                {
                    statusCode = CRMServiceCode.CRM309;
                }
            }
            else
            {
                statusCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            statusCode = CRMServiceCode.CRM999;
            LOGGER.error( "Error In Get CAF Dtails Method ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( StringUtils.isValidObj( statusCode ) )
            {
                configDto.setStatusCode( statusCode.getStatusCode() );
                configDto.setStatusDesc( statusCode.getStatusDesc() );
            }
        }
        return configDto;
    }

    private ConfigDto invalidRequest()
    {
        ConfigDto configDto = new ConfigDto();
        configDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        configDto.setStatusDesc( CRMServiceCode.CRM997.getStatusDesc() );
        return configDto;
    }

    @Override
    public ConfigDto getEvents( ConfigDto inConfigDto )
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
            criteria.setCacheable( true );
            criteria.setCacheRegion( EventsPojo.class.getName() );
            eventPojoList = criteria.list();
            if ( StringUtils.isValidObj( eventPojoList ) )
            {
                inConfigDto.setEventsPojoList( eventPojoList );
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
        return inConfigDto;
    }

    @Override
    public ConfigDto insertCustomerActivity( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside ConfigManagerDaoImpl :: insertCustomerActivity Method" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode code = CRMServiceCode.CRM999;
        CrmCustomerActivityPojo customerActivityPojo = null;
        Long generatedId = 0l;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inConfigDto.getCustomerActivityPojo() ) )
            {
                if ( CommonValidator.isValidCollection( inConfigDto.getUserIds() ) )
                {
                    for ( String customer : inConfigDto.getUserIds() )
                    {
                        if ( StringUtils.isNotBlank( customer ) )
                        {
                            LOGGER.info( "Customer ID:" + customer );
                            customerActivityPojo = BeanUtils.getTemporaryPojo( inConfigDto.getCustomerActivityPojo() );
                            customerActivityPojo.setCustomerId( customer );
                            customerActivityPojo.setCreatedTime( Calendar.getInstance().getTime() );
                            if ( StringUtils.isEmpty( CRMCustomerActivityActions.getActionByCode( customerActivityPojo
                                    .getAction() ) ) )
                            {
                                customerActivityPojo.setAction( CRMCustomerActivityActions
                                        .getCodeByAction( customerActivityPojo.getAction() ) );
                            }
                            if ( StringUtils.isEmpty( customerActivityPojo.getCreatedBy() ) )
                            {
                                customerActivityPojo.setCreatedBy( "-" );
                            }
                            generatedId = (Long) session.save( customerActivityPojo );
                            if ( generatedId > 0 )
                            {
                                code = CRMServiceCode.CRM001;
                            }
                            LOGGER.info( "SMS Enable:" + inConfigDto.isUnRead() );
                            if ( inConfigDto.isUnRead() )
                            {
                                Criteria criteria = session.createCriteria( CrmCustomerDetailsPojo.class );
                                criteria.add( Restrictions.eq( "customerId", customer ) );
                                List<CrmCustomerDetailsPojo> customerDetailsPojos = criteria.list();
                                Map<String, String> paramValues = new HashMap<String, String>();
                                paramValues.put( CRMParameter.CUSTOMER_RMN.getParameter(), customerDetailsPojos.get( 0 )
                                        .getRmn() + "" );
                                if ( StringUtils.equals( inConfigDto.getUserType(),
                                                         CRMCustomerActivityActions.ADD_MASSOUTAGE.getActionCode() ) )
                                {
                                    //Send SMS to ADD 
                                    boolean isAlertSent = CRMServiceUtils.sendAlerts( CRMEvents.ADD_MASSOUTAGE,
                                                                                      CRMRequestType.MASS_OUTAGE,
                                                                                      inConfigDto.getMappingId(), null,
                                                                                      paramValues );
                                    if ( isAlertSent )
                                    {
                                        LOGGER.info( "Sending SMS successfully......" );
                                    }
                                    else
                                    {
                                        LOGGER.info( "SMS sent failed...." );
                                    }
                                }
                                else
                                {
                                    //Send SMS to Resolved 
                                    boolean isAlertSent = CRMServiceUtils.sendAlerts( CRMEvents.RESOLVED_MASSOUTAGE,
                                                                                      CRMRequestType.MASS_OUTAGE,
                                                                                      inConfigDto.getMappingId(), null,
                                                                                      paramValues );
                                    if ( isAlertSent )
                                    {
                                        LOGGER.info( "Sending SMS successfully......" );
                                    }
                                    else
                                    {
                                        LOGGER.info( "SMS sent failed...." );
                                    }
                                }
                            }
                        }
                    }
                }
                else
                {
                    customerActivityPojo = inConfigDto.getCustomerActivityPojo();
                    customerActivityPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    if ( StringUtils.isEmpty( customerActivityPojo.getCreatedBy() ) )
                    {
                        customerActivityPojo.setCreatedBy( "-" );
                    }
                    if ( StringUtils.isEmpty( CRMCustomerActivityActions.getActionByCode( customerActivityPojo
                            .getAction() ) ) )
                    {
                        customerActivityPojo.setAction( CRMCustomerActivityActions
                                .getCodeByAction( customerActivityPojo.getAction() ) );
                    }
                    generatedId = (Long) session.save( customerActivityPojo );
                }
                if ( generatedId > 0 )
                {
                    transaction.commit();
                    LOGGER.info( "Successfully Generate Id For Customer Activity "
                            + customerActivityPojo.getActivityId() );
                    code = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( Exception ex )
        {
            code = CRMServiceCode.CRM999;
            LOGGER.info( "Getting error in insert Customer Activity " + ex );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == code )
            {
                HibernateUtil.evictAll( CrmCustomerActivityPojo.class.getName(), null, null );
            }
        }
        inConfigDto.setStatusCode( code.getStatusCode() );
        inConfigDto.setStatusDesc( code.getStatusDesc() );
        return inConfigDto;
    }

    @Override
    public void searchCustomerActivity( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside ConfigManagerDaoImpl :: searchCustomerActivity Method" );
        List<CrmCustomerActivityPojo> customerActivityPojos = null;
        Session session = null;
        CRMServiceCode code = CRMServiceCode.CRM997;
        try
        {
            if ( StringUtils.isValidObj( inConfigDto.getCustomerActivityPojo() )
                    && StringUtils.isNotBlank( inConfigDto.getCustomerActivityPojo().getCustomerId() ) )
            {
                session = HibernateUtil.getCurrentSession();
                Criteria criteria = session.createCriteria( CrmCustomerActivityPojo.class );
                criteria.add( Restrictions.eq( "customerId", inConfigDto.getCustomerActivityPojo().getCustomerId() ) );
                if ( StringUtils.isNotBlank( inConfigDto.getCustomerActivityPojo().getAction() ) )
                {
                    criteria.add( Restrictions.eq( "action", inConfigDto.getCustomerActivityPojo().getAction() ) );
                }
                criteria.addOrder( Order.desc( "createdTime" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmCustomerActivityPojo.class.getName() );
                customerActivityPojos = criteria.list();
                if ( CommonValidator.isValidCollection( customerActivityPojos ) )
                {
                    inConfigDto.setCustomerActivityPojos( customerActivityPojos );
                    LOGGER.info( "Size Of Search Customer Activity Pojos : " + customerActivityPojos.size() );
                    code = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( Exception ex )
        {
            code = CRMServiceCode.CRM999;
            LOGGER.error( "Getting error while Search Customer Activity " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inConfigDto.setStatusCode( code.getStatusCode() );
        inConfigDto.setStatusDesc( code.getStatusDesc() );
    }

    @Override
    public List<CrmInboxPojo> getPartnerIdFromInbox( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmInboxPojo> crmInboxPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmInboxPojo.class );
            criteria.add( Restrictions.eq( "mappingId", inConfigDto.getMappingId() ) );
            if ( StringUtils.isValidObj( inConfigDto.getStages() ) && !inConfigDto.getStages().isEmpty() )
            {
                criteria.add( Restrictions.in( "stage", inConfigDto.getStages() ) );
            }
            if ( StringUtils.isNotBlank( inConfigDto.getInboxStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", inConfigDto.getInboxStatus() ) );
            }
            if ( StringUtils.isNotBlank( inConfigDto.getInboxType() ) )
            {
                criteria.add( Restrictions.eq( "requestType", inConfigDto.getInboxType() ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmInboxPojo.class.getName() );
            crmInboxPojos = criteria.list();
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting error in  getPartnerIdFromInbox  mehtod " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmInboxPojos;
    }

    @Override
    public CrmMasterPojo getCMSRecipient( String inSubSubCategory )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmMasterPojo> crmMasterPojos = null;
        CrmMasterPojo crmMasterPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmMasterPojo.class );
            criteria.add( Restrictions.eq( "category", "ALERT" ) );
            criteria.add( Restrictions.eq( "subCategory", "MASTER" ) );
            criteria.add( Restrictions.eq( "subSubCategory", inSubSubCategory ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmMasterPojo.class.getName() );
            crmMasterPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmMasterPojos ) )
            {
                crmMasterPojo = crmMasterPojos.get( 0 );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting error in  getPartnerIdFromInbox  mehtod " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmMasterPojo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ConfigDto getPaymentCenters( ConfigDto inConfigDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CrmPaymentCentresPojo record = inConfigDto.getPaymentCentre();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmPaymentCentresPojo.class );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPaymentCentresPojo.class.getName() );
            if ( StringUtils.isValidObj( record ) && StringUtils.isNotBlank( record.getPincode() ) )
            {
                criteria.add( Restrictions.eq( "pincode", record.getPincode() ) );
                inConfigDto.setPaymentCentres( criteria.list() );
            }
            else
            {
                criteria.setProjection( Projections.distinct( Projections.property( "pincode" ) ) );
                inConfigDto.setStages( criteria.list() );
            }
            LOGGER.info( inConfigDto.getPaymentCentres() );
            inConfigDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
        }
        catch ( Exception ex )
        {
            inConfigDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            LOGGER.error( "Getting error in  getPaymentCenters  mehtod " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto listUploadedFiles( ConfigDto inConfigDto )
    {
        List<List<String>> dmsDb = null;
        List<String> listValue = null;
        Session session = null;
        Query query = null;
        try
        {
            if ( StringUtils.isNotBlank( inConfigDto.getMappingId() )
                    && StringUtils.isNotBlank( inConfigDto.getRequestType() ) )
            {
                session = HibernateUtil.getSessionFactory().openSession();
                query = session.getNamedQuery( "ADDED_DOC_LIST" );
                query.setString( "MODULE", inConfigDto.getRequestType() );
                query.setString( "MAPPING", inConfigDto.getMappingId() );
                query.setResultTransformer( Transformers.TO_LIST );
                dmsDb = query.list();
            }
            if ( CommonValidator.isValidCollection( dmsDb ) )
            {
                LOGGER.info( "List Size::" + dmsDb.size() );
                listValue = new ArrayList<String>();
                for ( List<String> list : dmsDb )
                {
                    listValue.addAll( list );
                }
                inConfigDto.setPodUploadedList( listValue );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error In Get listUploadedFiles list Method ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inConfigDto;
    }
}
