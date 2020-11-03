package com.np.tele.crm.massoutage.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmMassOutageAreaPojo;
import com.np.tele.crm.pojos.CrmMassOutageCityPojo;
import com.np.tele.crm.pojos.CrmMassOutageMastersPojo;
import com.np.tele.crm.pojos.CrmMassOutagePartnerPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.CrmMassOutageSocietyPojo;
import com.np.tele.crm.pojos.CrmMassOutageStatePojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class MassOutageDaoImpl
    implements IMassOutageDao
{
    private static final Logger LOGGER = Logger.getLogger( MassOutageDaoImpl.class );

    @Override
    public CrmMassOutageDto searchMassOutage( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "Inside MassOutageDaoImpl, searchMassOutage" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmMassOutagePojo crmMassOutagePojo = inCrmMassOutageDto.getCrmMassOutagePojo();
        if ( StringUtils.isValidObj( crmMassOutagePojo ) )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmMassOutagePojo.class );
                if ( StringUtils.isNotBlank( crmMassOutagePojo.getServiceName() ) )
                {
                    criteria.add( Restrictions.eq( "serviceName", crmMassOutagePojo.getServiceName() ) );
                }
                if ( StringUtils.isNotBlank( crmMassOutagePojo.getOutageType() ) )
                {
                    criteria.add( Restrictions.eq( "outageType", crmMassOutagePojo.getOutageType() ) );
                }
                if ( StringUtils.isValidObj( crmMassOutagePojo.getOutageStartTime() ) )
                {
                    criteria.add( Restrictions.eq( "outageStartTime", crmMassOutagePojo.getOutageStartTime() ) );
                }
                if ( StringUtils.isValidObj( crmMassOutagePojo.getOutageEtrTime() ) )
                {
                    criteria.add( Restrictions.eq( "outageEtrTime", crmMassOutagePojo.getOutageEtrTime() ) );
                }
                if ( StringUtils.isValidObj( crmMassOutagePojo.getStatusCode() ) )
                {
                    criteria.add( Restrictions.eq( "status", crmMassOutagePojo.getStatusCode() ) );
                }
                criteria.addOrder( Order.desc( "outageStartTime" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmMassOutagePojo.class.getName() );
                List<CrmMassOutagePojo> massOutageSearchList = criteria.list();
                if ( StringUtils.isValidObj( massOutageSearchList ) )
                {
                    LOGGER.info( "massOutageSearchList****" + massOutageSearchList.size() );
                    inCrmMassOutageDto.setMassOutagePojos( massOutageSearchList );
                }
                serviceCode = CRMServiceCode.CRM001;
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Getting Error while getQrcCategories: ", ex );
                serviceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        else
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public CrmMassOutageDto searchMassOutageMaster( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "Inside MassOutageDaoImpl, searchMassOutageMaster" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmMassOutageMastersPojo> massOutageMasterSearchList = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmMassOutageMastersPojo outageMastersPojo = inCrmMassOutageDto.getCrmMassOutageMastersPojo();
        if ( StringUtils.isValidObj( outageMastersPojo ) && outageMastersPojo.getOutageId() > 0 )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmMassOutageMastersPojo.class );
                criteria.add( Restrictions.eq( "outageId", outageMastersPojo.getOutageId() ) );
                //not resolve
                massOutageMasterSearchList = criteria.list();
                if ( CommonValidator.isValidCollection( massOutageMasterSearchList ) )
                {
                    LOGGER.info( "CrmMassOutageMastersPojo Size ::" + massOutageMasterSearchList.size() );
                    inCrmMassOutageDto.setOutageMastersPojos( massOutageMasterSearchList );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM996;
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Getting Error while searchMassOutageMaster: ", ex );
                serviceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        else
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public void saveMassOutage( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "in saveMassOutage" );
        CrmMassOutagePojo massOutagePojo = inCrmMassOutageDto.getCrmMassOutagePojo();
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        long massOutageId = 0l;
        long masterRecId = 0l;
        long societyRecId = 0l;
        Map<String, Long> evicts = new HashMap<String, Long>();
        if ( StringUtils.isValidObj( massOutagePojo )
                && CommonValidator.isValidCollection( massOutagePojo.getCrmMassOutagePartners() ) )
        {
            try
            {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                if ( !StringUtils.equals( massOutagePojo.getOutageType(), "Planned" ) )
                {
                    massOutagePojo.setOutageStartTime( Calendar.getInstance().getTime() );
                }
                if ( massOutagePojo.getOutageEtrTime().compareTo( massOutagePojo.getOutageStartTime() ) != 1 )
                {
                    serviceCode = CRMServiceCode.CRM452;
                    return;
                }
                LOGGER.info( "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" );
                criteria = session.createCriteria( CrmMassOutagePojo.class );
                criteria.add( Restrictions.eq( "serviceName", massOutagePojo.getServiceName() ) );
                Criterion startBetween = Restrictions.between( "outageStartTime", massOutagePojo.getOutageStartTime(),
                                                               massOutagePojo.getOutageEtrTime() );
                Criterion endBetween = Restrictions.between( "outageEtrTime", massOutagePojo.getOutageStartTime(),
                                                             massOutagePojo.getOutageEtrTime() );
                Criterion inBetween = Restrictions.and( Restrictions.le( "outageStartTime",
                                                                         massOutagePojo.getOutageStartTime() ),
                                                        Restrictions.ge( "outageEtrTime",
                                                                         massOutagePojo.getOutageEtrTime() ) );
                Criterion outBetween = Restrictions.and( Restrictions.gt( "outageStartTime",
                                                                          massOutagePojo.getOutageStartTime() ),
                                                         Restrictions.lt( "outageEtrTime",
                                                                          massOutagePojo.getOutageEtrTime() ) );
                criteria.add( Restrictions.and( Restrictions
                                                        .or( Restrictions.or( startBetween, endBetween ), inBetween ),
                                                Restrictions.not( outBetween ) ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmMassOutagePojo.class.getName() );
                List<CrmMassOutagePojo> massOutagePojos = criteria.list();
                LOGGER.info( "::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::" );
                int count = 0;
                long dupOutageId = 0l;
                if ( CommonValidator.isValidCollection( massOutagePojos ) )
                {
                    for ( CrmMassOutagePojo outagePojo : massOutagePojos )
                    {
                        List<CrmMassOutagePartnerPojo> partners = CRMServiceUtils
                                .getDBValueList( CrmMassOutagePartnerPojo.class, "outageId", outagePojo.getOutageId() );
                        if ( partners.retainAll( massOutagePojo.getCrmMassOutagePartners() ) )
                        {
                            if ( !partners.isEmpty() )
                            {
                                count++;
                                dupOutageId = outagePojo.getOutageId();
                            }
                        }
                        else
                        {
                            count++;
                            dupOutageId = outagePojo.getOutageId();
                        }
                    }
                    if ( count > 1 )
                    {
                        serviceCode = CRMServiceCode.CRM451;
                        return;
                    }
                }
                massOutageId = (Long) session.save( massOutagePojo );
                evicts.put( CrmMassOutagePojo.class.getName(), massOutagePojo.getOutageId() );
                LOGGER.info( "massOutageId :: " + massOutageId );
                if ( massOutageId > 0 )
                {
                    for ( CrmMassOutagePartnerPojo partnerPojo : massOutagePojo.getCrmMassOutagePartners() )
                    {
                        partnerPojo.setOutageId( massOutageId );
                        if ( dupOutageId > 0 && partnerPojo.isEnabled() )
                        {
                            criteria = session.createCriteria( CrmMassOutagePartnerPojo.class );
                            criteria.add( Restrictions.eq( "outageId", dupOutageId ) );
                            criteria.add( Restrictions.eq( "partnerId", partnerPojo.getPartnerId() ) );
                            criteria.add( Restrictions.eq( "enabled", partnerPojo.isEnabled() ) );
                            if ( CommonValidator.isValidCollection( criteria.list() ) )
                            {
                                partnerPojo.setEnabled( false );
                            }
                        }
                        long partnerRecId = (Long) session.save( partnerPojo );
                        evicts.put( CrmMassOutagePartnerPojo.class.getName(), partnerPojo.getPartnerId() );
                        LOGGER.info( "partner record id for '" + partnerPojo.getPartnerName() + "' is " + partnerRecId );
                        if ( partnerRecId > 0 )
                        {
                            if ( StringUtils.equals( massOutagePojo.getServiceName(),
                                                     CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )
                                    || StringUtils.equals( massOutagePojo.getServiceName(),
                                                           CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                            {
                                for ( CrmMassOutageMastersPojo mastersPojo : partnerPojo.getCrmMassOutageMasterses() )
                                {
                                    mastersPojo.setOutageId( massOutageId );
                                    mastersPojo.setOutageNpId( partnerRecId );
                                    if ( dupOutageId > 0 && mastersPojo.isEnabled() )
                                    {
                                        criteria = session.createCriteria( CrmMassOutageMastersPojo.class );
                                        criteria.add( Restrictions.eq( "outageId", dupOutageId ) );
                                        criteria.add( Restrictions.eq( "masterId", mastersPojo.getMasterId() ) );
                                        criteria.add( Restrictions.eq( "enabled", mastersPojo.isEnabled() ) );
                                        if ( CommonValidator.isValidCollection( criteria.list() ) )
                                        {
                                            mastersPojo.setEnabled( false );
                                        }
                                    }
                                    masterRecId = (Long) session.save( mastersPojo );
                                    evicts.put( CrmMassOutageMastersPojo.class.getName(), mastersPojo.getMasterId() );
                                    LOGGER.info( "masterRecId :: " + masterRecId );
                                }
                            }
                            else
                            {
                                for ( CrmMassOutageStatePojo statePojo : partnerPojo.getCrmMassOutageStates() )
                                {
                                    statePojo.setOutageId( massOutageId );
                                    statePojo.setOutageNpId( partnerRecId );
                                    if ( dupOutageId > 0 && statePojo.isEnabled() )
                                    {
                                        criteria = session.createCriteria( CrmMassOutageStatePojo.class );
                                        criteria.add( Restrictions.eq( "outageId", dupOutageId ) );
                                        criteria.add( Restrictions.eq( "stateId", statePojo.getStateId() ) );
                                        criteria.add( Restrictions.eq( "enabled", statePojo.isEnabled() ) );
                                        if ( CommonValidator.isValidCollection( criteria.list() ) )
                                        {
                                            statePojo.setEnabled( false );
                                        }
                                    }
                                    long stateRecId = (Long) session.save( statePojo );
                                    evicts.put( CrmMassOutageStatePojo.class.getName(), statePojo.getStateId() );
                                    LOGGER.info( "stateRecId :: " + stateRecId + " >>>> " + statePojo.getStateName() );
                                    if ( stateRecId > 0 )
                                    {
                                        for ( CrmMassOutageCityPojo cityPojo : statePojo.getCrmMassOutageCities() )
                                        {
                                            cityPojo.setOutageId( massOutageId );
                                            cityPojo.setOutageStateId( stateRecId );
                                            if ( dupOutageId > 0 && cityPojo.isEnabled() )
                                            {
                                                criteria = session.createCriteria( CrmMassOutageCityPojo.class );
                                                criteria.add( Restrictions.eq( "outageId", dupOutageId ) );
                                                criteria.add( Restrictions.eq( "cityId", cityPojo.getCityId() ) );
                                                criteria.add( Restrictions.eq( "enabled", cityPojo.isEnabled() ) );
                                                if ( CommonValidator.isValidCollection( criteria.list() ) )
                                                {
                                                    cityPojo.setEnabled( false );
                                                }
                                            }
                                            long cityRecId = (Long) session.save( cityPojo );
                                            evicts.put( CrmMassOutageCityPojo.class.getName(), cityPojo.getCityId() );
                                            LOGGER.info( "cityRecId :: " + cityRecId + " >>>> "
                                                    + cityPojo.getCityName() );
                                            if ( cityRecId > 0 )
                                            {
                                                for ( CrmMassOutageAreaPojo areaPojo : cityPojo.getCrmMassOutageAreas() )
                                                {
                                                    areaPojo.setOutageId( massOutageId );
                                                    areaPojo.setOutageCityId( cityRecId );
                                                    if ( dupOutageId > 0 && areaPojo.isEnabled() )
                                                    {
                                                        criteria = session.createCriteria( CrmMassOutageAreaPojo.class );
                                                        criteria.add( Restrictions.eq( "outageId", dupOutageId ) );
                                                        criteria.add( Restrictions.eq( "areaId", areaPojo.getAreaId() ) );
                                                        criteria.add( Restrictions.eq( "enabled", areaPojo.isEnabled() ) );
                                                        if ( CommonValidator.isValidCollection( criteria.list() ) )
                                                        {
                                                            areaPojo.setEnabled( false );
                                                        }
                                                    }
                                                    long areaRecId = (Long) session.save( areaPojo );
                                                    evicts.put( CrmMassOutageAreaPojo.class.getName(),
                                                                areaPojo.getAreaId() );
                                                    LOGGER.info( "areaRecId :: " + areaRecId + " >>>> "
                                                            + areaPojo.getArea() );
                                                    if ( areaRecId > 0 )
                                                    {
                                                        for ( CrmMassOutageSocietyPojo societyPojo : areaPojo
                                                                .getCrmMassOutageSocietyPojos() )
                                                        {
                                                            societyPojo.setOutageId( massOutageId );
                                                            societyPojo.setOutageAreaId( areaRecId );
                                                            if ( dupOutageId > 0 && societyPojo.isEnabled() )
                                                            {
                                                                criteria = session
                                                                        .createCriteria( CrmMassOutageSocietyPojo.class );
                                                                criteria.add( Restrictions.eq( "outageId", dupOutageId ) );
                                                                criteria.add( Restrictions.eq( "societyId", societyPojo
                                                                        .getSocietyId() ) );
                                                                criteria.add( Restrictions.eq( "enabled",
                                                                                               societyPojo.isEnabled() ) );
                                                                if ( CommonValidator
                                                                        .isValidCollection( criteria.list() ) )
                                                                {
                                                                    societyPojo.setEnabled( false );
                                                                }
                                                            }
                                                            societyRecId = (Long) session.save( societyPojo );
                                                            evicts.put( CrmMassOutageSocietyPojo.class.getName(),
                                                                        societyPojo.getSocietyId() );
                                                            LOGGER.info( "societyRecId :: " + societyRecId + " >>>> "
                                                                    + societyPojo.getSociety() );
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
                LOGGER.info( "moi " + massOutageId + " mri " + masterRecId + " sri " + societyRecId );
                if ( massOutageId > 0 && ( masterRecId > 0 || societyRecId > 0 ) )
                {
                    serviceCode = CRMServiceCode.CRM001;
                    transaction.commit();
                }
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "HibernateException In saveMassOutage:", ex );
                serviceCode = CRMServiceCode.CRM999;
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "exception occured", ex );
                CRMServiceUtils.rollback( transaction );
                serviceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSession( session );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( evicts );
                }
                inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        else
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
    }

    @Override
    public void updateMassOutage( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "in updateMassOutage" );
        CrmMassOutagePojo massOutagePojo = inCrmMassOutageDto.getCrmMassOutagePojo();
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        String userId = inCrmMassOutageDto.getUserId();
        Date now = Calendar.getInstance().getTime();
        Session session = null;
        Transaction transaction = null;
        boolean partner, master, state, city, area, locality, society;
        Map<String, Long> evicts = new HashMap<String, Long>();
        if ( StringUtils.isValidObj( massOutagePojo )
                && CommonValidator.isValidCollection( massOutagePojo.getCrmMassOutagePartners() ) )
        {
            try
            {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                CrmMassOutagePojo massOutagePojoDb = (CrmMassOutagePojo) session.get( CrmMassOutagePojo.class,
                                                                                      massOutagePojo.getOutageId() );
                if ( massOutagePojo.getOutageEtrTime().compareTo( massOutagePojoDb.getOutageEtrTime() ) != 0 )
                {
                    if ( massOutagePojo.getOutageEtrTime().compareTo( massOutagePojo.getOutageStartTime() ) == 1 )
                    {
                        massOutagePojoDb.setOutageEtrTime( massOutagePojo.getOutageEtrTime() );
                    }
                    else
                    {
                        serviceCode = CRMServiceCode.CRM452;
                        return;
                    }
                }
                if ( StringUtils.equals( massOutagePojo.getServiceName(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )
                        || StringUtils.equals( massOutagePojo.getServiceName(),
                                               CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                {
                    partner = true;
                    for ( CrmMassOutagePartnerPojo outagePartnerPojo : massOutagePojo.getCrmMassOutagePartners() )
                    {
                        master = true;
                        for ( CrmMassOutageMastersPojo outageMastersPojo : outagePartnerPojo
                                .getCrmMassOutageMasterses() )
                        {
                            if ( !outageMastersPojo.isEnabled()
                                    && StringUtils.isBlank( outageMastersPojo.getResolvedBy() ) )
                            {
                                outageMastersPojo.setResolvedBy( userId );
                                outageMastersPojo.setResolvedOn( now );
                                session.update( outageMastersPojo );
                                evicts.put( CrmMassOutageMastersPojo.class.getName(), outageMastersPojo.getOutageId() );
                            }
                            else
                            {
                                master = !outageMastersPojo.isEnabled() && master;
                            }
                        }
                        if ( master && !outagePartnerPojo.isEnabled()
                                && StringUtils.isBlank( outagePartnerPojo.getResolvedBy() ) )
                        {
                            outagePartnerPojo.setResolvedBy( userId );
                            outagePartnerPojo.setResolvedOn( now );
                            session.update( outagePartnerPojo );
                            evicts.put( CrmMassOutagePartnerPojo.class.getName(), outagePartnerPojo.getPartnerId() );
                        }
                        else
                        {
                            partner = !outagePartnerPojo.isEnabled() && master && partner;
                        }
                    }
                }
                else
                {
                    partner = true;
                    for ( CrmMassOutagePartnerPojo outagePartnerPojo : massOutagePojo.getCrmMassOutagePartners() )
                    {
                        state = true;
                        for ( CrmMassOutageStatePojo outageStatePojo : outagePartnerPojo.getCrmMassOutageStates() )
                        {
                            city = true;
                            for ( CrmMassOutageCityPojo outageCityPojo : outageStatePojo.getCrmMassOutageCities() )
                            {
                                area = true;
                                for ( CrmMassOutageAreaPojo outageAreaPojo : outageCityPojo.getCrmMassOutageAreas() )
                                {
                                    society = true;
                                    for ( CrmMassOutageSocietyPojo outageSocietyPojo : outageAreaPojo
                                            .getCrmMassOutageSocietyPojos() )
                                    {
                                        if ( !outageSocietyPojo.isEnabled()
                                                && StringUtils.isBlank( outageSocietyPojo.getResolvedBy() ) )
                                        {
                                            outageSocietyPojo.setResolvedBy( userId );
                                            outageSocietyPojo.setResolvedOn( now );
                                            session.update( outageSocietyPojo );
                                            evicts.put( CrmMassOutageSocietyPojo.class.getName(),
                                                        outageSocietyPojo.getSocietyId() );
                                        }
                                        else
                                        {
                                            society = !outageSocietyPojo.isEnabled() && society;
                                        }
                                    }
                                    if ( society && !outageAreaPojo.isEnabled()
                                            && StringUtils.isBlank( outageAreaPojo.getResolvedBy() ) )
                                    {
                                        outageAreaPojo.setResolvedBy( userId );
                                        outageAreaPojo.setResolvedOn( now );
                                        session.update( outageAreaPojo );
                                        evicts.put( CrmMassOutageAreaPojo.class.getName(), outageAreaPojo.getAreaId() );
                                    }
                                    else
                                    {
                                        area = !outageAreaPojo.isEnabled() && society && area;
                                    }
                                }
                                if ( area && !outageCityPojo.isEnabled()
                                        && StringUtils.isBlank( outageCityPojo.getResolvedBy() ) )
                                {
                                    outageCityPojo.setResolvedBy( userId );
                                    outageCityPojo.setResolvedOn( now );
                                    session.update( outageCityPojo );
                                    evicts.put( CrmMassOutageCityPojo.class.getName(), outageCityPojo.getCityId() );
                                }
                                else
                                {
                                    city = !outageCityPojo.isEnabled() && area && city;
                                }
                            }
                            if ( city && !outageStatePojo.isEnabled()
                                    && StringUtils.isBlank( outageStatePojo.getResolvedBy() ) )
                            {
                                outageStatePojo.setResolvedBy( userId );
                                outageStatePojo.setResolvedOn( now );
                                session.update( outageStatePojo );
                                evicts.put( CrmMassOutageStatePojo.class.getName(), outageStatePojo.getStateId() );
                            }
                            else
                            {
                                state = !outageStatePojo.isEnabled() && city && state;
                            }
                        }
                        if ( state && !outagePartnerPojo.isEnabled()
                                && StringUtils.isBlank( outagePartnerPojo.getResolvedBy() ) )
                        {
                            outagePartnerPojo.setResolvedBy( userId );
                            outagePartnerPojo.setResolvedOn( now );
                            session.update( outagePartnerPojo );
                            evicts.put( CrmMassOutagePartnerPojo.class.getName(), outagePartnerPojo.getPartnerId() );
                        }
                        else
                        {
                            partner = !outagePartnerPojo.isEnabled() && state && partner;
                        }
                    }
                }
                if ( partner )
                {
                    massOutagePojoDb.setResolutionTime( now );
                    massOutagePojoDb.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
                }
                massOutagePojoDb.setLastModifiedBy( userId );
                massOutagePojoDb.setLastModifiedTime( now );
                massOutagePojoDb.setSendSms( massOutagePojo.isSendSms() );
                massOutagePojoDb.setReason( massOutagePojo.getReason() );
                massOutagePojoDb.setRemarks( massOutagePojo.getRemarks() );
                session.merge( massOutagePojoDb );
                serviceCode = CRMServiceCode.CRM001;
                transaction.commit();
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "exception occured", ex );
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
                serviceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSession( session );
                inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( evicts );
                }
            }
        }
        else
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
    }

    @Override
    public void resolveMassOutage( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "in updateMassOutage" );
        CrmMassOutagePojo massOutagePojo = inCrmMassOutageDto.getCrmMassOutagePojo();
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        String userId = inCrmMassOutageDto.getUserId();
        Date now = Calendar.getInstance().getTime();
        Session session = null;
        Transaction transaction = null;
        Map<String, Long> evicts = new HashMap<String, Long>();
        if ( StringUtils.isValidObj( massOutagePojo )
                && CommonValidator.isValidCollection( massOutagePojo.getCrmMassOutagePartners() ) )
        {
            try
            {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                if ( StringUtils.equals( massOutagePojo.getServiceName(),
                                         CRMDisplayListConstants.ETHERNET_ON_CABLE.getCode() )
                        || StringUtils.equals( massOutagePojo.getServiceName(),
                                               CRMDisplayListConstants.RADIO_FREQUENCY.getCode() ) )
                {
                    for ( CrmMassOutagePartnerPojo outagePartnerPojo : massOutagePojo.getCrmMassOutagePartners() )
                    {
                        for ( CrmMassOutageMastersPojo outageMastersPojo : outagePartnerPojo
                                .getCrmMassOutageMasterses() )
                        {
                            if ( StringUtils.isBlank( outageMastersPojo.getResolvedBy() ) )
                            {
                                outageMastersPojo.setEnabled( false );
                                outageMastersPojo.setResolvedBy( userId );
                                outageMastersPojo.setResolvedOn( now );
                                session.update( outageMastersPojo );
                                evicts.put( CrmMassOutageMastersPojo.class.getName(), outageMastersPojo.getOutageId() );
                            }
                        }
                        if ( StringUtils.isBlank( outagePartnerPojo.getResolvedBy() ) )
                        {
                            outagePartnerPojo.setEnabled( false );
                            outagePartnerPojo.setResolvedBy( userId );
                            outagePartnerPojo.setResolvedOn( now );
                            session.update( outagePartnerPojo );
                            evicts.put( CrmMassOutagePartnerPojo.class.getName(), outagePartnerPojo.getPartnerId() );
                        }
                    }
                }
                else
                {
                    for ( CrmMassOutagePartnerPojo outagePartnerPojo : massOutagePojo.getCrmMassOutagePartners() )
                    {
                        for ( CrmMassOutageStatePojo outageStatePojo : outagePartnerPojo.getCrmMassOutageStates() )
                        {
                            for ( CrmMassOutageCityPojo outageCityPojo : outageStatePojo.getCrmMassOutageCities() )
                            {
                                for ( CrmMassOutageAreaPojo outageAreaPojo : outageCityPojo.getCrmMassOutageAreas() )
                                {
                                    for ( CrmMassOutageSocietyPojo outageSocietyPojo : outageAreaPojo
                                            .getCrmMassOutageSocietyPojos() )
                                    {
                                        if ( outageSocietyPojo.isEnabled()
                                                || StringUtils.isBlank( outageSocietyPojo.getResolvedBy() ) )
                                        {
                                            outageSocietyPojo.setEnabled( false );
                                            outageSocietyPojo.setResolvedBy( userId );
                                            outageSocietyPojo.setResolvedOn( now );
                                            session.update( outageSocietyPojo );
                                            evicts.put( CrmMassOutageSocietyPojo.class.getName(),
                                                        outageSocietyPojo.getSocietyId() );
                                        }
                                    }
                                    if ( StringUtils.isBlank( outageAreaPojo.getResolvedBy() ) )
                                    {
                                        outageAreaPojo.setEnabled( false );
                                        outageAreaPojo.setResolvedBy( userId );
                                        outageAreaPojo.setResolvedOn( now );
                                        session.update( outageAreaPojo );
                                        evicts.put( CrmMassOutageAreaPojo.class.getName(), outageAreaPojo.getAreaId() );
                                    }
                                }
                                if ( StringUtils.isBlank( outageCityPojo.getResolvedBy() ) )
                                {
                                    outageCityPojo.setEnabled( false );
                                    outageCityPojo.setResolvedBy( userId );
                                    outageCityPojo.setResolvedOn( now );
                                    session.update( outageCityPojo );
                                    evicts.put( CrmMassOutageCityPojo.class.getName(), outageCityPojo.getCityId() );
                                }
                            }
                            if ( StringUtils.isBlank( outageStatePojo.getResolvedBy() ) )
                            {
                                outageStatePojo.setEnabled( false );
                                outageStatePojo.setResolvedBy( userId );
                                outageStatePojo.setResolvedOn( now );
                                session.update( outageStatePojo );
                                evicts.put( CrmMassOutageStatePojo.class.getName(), outageStatePojo.getStateId() );
                            }
                        }
                        if ( StringUtils.isBlank( outagePartnerPojo.getResolvedBy() ) )
                        {
                            outagePartnerPojo.setEnabled( false );
                            outagePartnerPojo.setResolvedBy( userId );
                            outagePartnerPojo.setResolvedOn( now );
                            session.update( outagePartnerPojo );
                            evicts.put( CrmMassOutagePartnerPojo.class.getName(), outagePartnerPojo.getPartnerId() );
                        }
                    }
                }
                CrmMassOutagePojo massOutagePojoDb = (CrmMassOutagePojo) session.get( CrmMassOutagePojo.class,
                                                                                      massOutagePojo.getOutageId() );
                massOutagePojoDb.setResolutionTime( now );
                massOutagePojoDb.setLastModifiedBy( userId );
                massOutagePojoDb.setLastModifiedTime( now );
                massOutagePojoDb.setReason( massOutagePojo.getReason() );
                massOutagePojoDb.setStatus( CRMStatusCode.RESOLVED.getStatusCode() );
                session.merge( massOutagePojoDb );
                serviceCode = CRMServiceCode.CRM001;
                transaction.commit();
            }
            catch ( HibernateException ex )
            {
                LOGGER.error( "unable to resolve mass outage", ex );
                serviceCode = CRMServiceCode.CRM999;
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSessionOnException( session );
            }
            finally
            {
                CRMServiceUtils.rollback( transaction );
                CRMServiceUtils.closeSession( session );
                inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
                if ( serviceCode == CRMServiceCode.CRM001 )
                {
                    HibernateUtil.evictAll( evicts );
                }
            }
        }
        else
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
    }

    @Override
    public CRMServiceCode trackMassOutage( CrmMassOutageDto inCrmMassOutageDto )
    {
        CRMServiceCode serviceCode = CRMServiceCode.CRM996;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        if ( StringUtils.isValidObj( inCrmMassOutageDto ) )
        {
            try
            {
                LOGGER.info( "trackMassOutage" );
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmMassOutagePojo.class );
                criteria.add( Restrictions.le( "outageStartTime", Calendar.getInstance().getTime() ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.add( Restrictions.isNull( "resolutionTime" ) );
                if ( StringUtils.isValidObj( inCrmMassOutageDto.getCrmMassOutagePojo() )
                        && StringUtils.isNotBlank( inCrmMassOutageDto.getCrmMassOutagePojo().getServiceName() ) )
                {
                    criteria.add( Restrictions.eq( "serviceName", inCrmMassOutageDto.getCrmMassOutagePojo()
                            .getServiceName() ) );
                }
                criteria.addOrder( Order.desc( "outageStartTime" ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmMassOutagePojo.class.getName() );
                List<CrmMassOutagePojo> records = criteria.list();
                if ( CommonValidator.isValidCollection( records ) )
                {
                    if ( StringUtils.isValidObj( inCrmMassOutageDto.getCrmMassOutageSocietyPojo() ) )
                    {
                        for ( CrmMassOutagePojo crmMassOutagePojo : records )
                        {
                            criteria = session.createCriteria( CrmMassOutageSocietyPojo.class );
                            criteria.add( Restrictions.eq( "societyId", inCrmMassOutageDto
                                    .getCrmMassOutageSocietyPojo().getSocietyId() ) );
                            criteria.add( Restrictions.eq( "outageId", crmMassOutagePojo.getOutageId() ) );
                            criteria.add( Restrictions.eq( "enabled", true ) );
                            criteria.setCacheable( true );
                            criteria.setCacheRegion( CrmMassOutageSocietyPojo.class.getName() );
                            List<CrmMassOutageSocietyPojo> outageRecords = criteria.list();
                            if ( CommonValidator.isValidCollection( outageRecords ) )
                            {
                                inCrmMassOutageDto.setCrmMassOutagePojo( crmMassOutagePojo );
                                serviceCode = CRMServiceCode.CRM001;
                                break;
                            }
                        }
                    }
                    else if ( StringUtils.isValidObj( inCrmMassOutageDto.getCrmMassOutageMastersPojo() ) )
                    {
                        if ( inCrmMassOutageDto.getCrmMassOutageMastersPojo().getMasterId() > 0 )
                        {
                            CrmPartnerNetworkConfigPojo networkPartnerConfig = CRMServiceUtils
                                    .getDBValues( CrmPartnerNetworkConfigPojo.class, inCrmMassOutageDto
                                            .getCrmMassOutageMastersPojo().getMasterId() );
                            if ( StringUtils.isValidObj( networkPartnerConfig ) )
                            {
                                inCrmMassOutageDto.getCrmMassOutageMastersPojo()
                                        .setMasterName( networkPartnerConfig.getMasterName() );
                            }
                        }
                        for ( CrmMassOutagePojo crmMassOutagePojo : records )
                        {
                            criteria = session.createCriteria( CrmMassOutageMastersPojo.class );
                            criteria.add( Restrictions.eq( "masterName", inCrmMassOutageDto
                                    .getCrmMassOutageMastersPojo().getMasterName() ) );
                            criteria.add( Restrictions.eq( "outageId", crmMassOutagePojo.getOutageId() ) );
                            criteria.add( Restrictions.isNull( "resolvedOn" ) );
                            // criteria.add( Restrictions.eq( "enabled", true ) );
                            criteria.setCacheable( true );
                            criteria.setCacheRegion( CrmMassOutageMastersPojo.class.getName() );
                            List<CrmMassOutageMastersPojo> outageRecords = criteria.list();
                            if ( CommonValidator.isValidCollection( outageRecords ) )
                            {
                                inCrmMassOutageDto.setCrmMassOutagePojo( crmMassOutagePojo );
                                serviceCode = CRMServiceCode.CRM001;
                                break;
                            }
                        }
                    }
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Exception while tracking mass outage:", ex );
                serviceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
            }
        }
        return serviceCode;
    }

    @Override
    public CrmMassOutagePojo trackMassOutage( String inCustomerId )
    {
        if ( StringUtils.isNotBlank( inCustomerId ) && StringUtils.isNumeric( inCustomerId ) )
        {
            CrmCustomerDetailsPojo customer = CRMServiceUtils.getDBValues( CrmCustomerDetailsPojo.class, "customerId",
                                                                           inCustomerId );
            if ( StringUtils.isValidObj( customer ) )
            {
                try
                {
                    CrmMassOutageDto outageDto = new CrmMassOutageDto();
                    CrmMassOutagePojo outagePojo = new CrmMassOutagePojo();
                    outagePojo.setServiceName( customer.getProduct() );
                    outageDto.setCrmMassOutagePojo( outagePojo );
                    if ( StringUtils.equals( CRMDisplayListConstants.BROADBAND.getCode(), customer.getProduct() ) )
                    {
                        CrmAddressDetailsPojo instAdd = null;
                        Set<CrmAddressDetailsPojo> addresses = customer.getCrmAddressDetailses();
                        if ( StringUtils.isValidObj( addresses ) )
                        {
                            for ( CrmAddressDetailsPojo crmAddressDetailsPojo : addresses )
                            {
                                if ( StringUtils.equals( IAppConstants.ADDRESS_TYPE_INSTALLATION,
                                                         crmAddressDetailsPojo.getAddressType() ) )
                                {
                                    instAdd = crmAddressDetailsPojo;
                                }
                            }
                        }
                        if ( StringUtils.isValidObj( instAdd ) )
                        {
                            CrmMassOutageSocietyPojo societyOutage = new CrmMassOutageSocietyPojo();
                            societyOutage.setSocietyId( instAdd.getInstSocietyId() );
                            outageDto.setCrmMassOutageSocietyPojo( societyOutage );
                        }
                    }
                    else
                    {
                        CrmNetworkConfigurationsPojo networkConfig = CRMServiceUtils
                                .getDBValues( CrmNetworkConfigurationsPojo.class,
                                              IAppConstants.PARAM_CUSTOMER_RECORD_ID, customer.getRecordId() );
                        if ( StringUtils.isValidObj( networkConfig ) )
                        {
                            CrmPartnerNetworkConfigPojo networkPartnerConfig = CRMServiceUtils
                                    .getDBValues( CrmPartnerNetworkConfigPojo.class,
                                                  Long.valueOf( networkConfig.getOption82() ) );
                            if ( StringUtils.isValidObj( networkPartnerConfig ) )
                            {
                                CrmMassOutageMastersPojo masterOutage = new CrmMassOutageMastersPojo();
                                masterOutage.setMasterName( networkPartnerConfig.getMasterName() );
                                outageDto.setCrmMassOutageMastersPojo( masterOutage );
                                // masterOutage.setMasterId( StringUtils.numericValue( networkConfig.getOption82() ) );
                            }
                        }
                    }
                    CRMServiceCode serviceCode = trackMassOutage( outageDto );
                    if ( CRMServiceCode.CRM001 == serviceCode )
                    {
                        return outageDto.getCrmMassOutagePojo();
                    }
                }
                catch ( Exception ex )
                {
                    LOGGER.error( "Exception while fetching Customer mass outage info ", ex );
                }
            }
        }
        return null;
    }

    @Override
    public void updateMassOutageActivityStatus( CrmMassOutageDto inCrmMassOutageDto )
    {
        Session session = null;
        Transaction transaction = null;
        CrmMassOutagePojo massOutagePojoDb = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inCrmMassOutageDto )
                    && inCrmMassOutageDto.getCrmMassOutagePojo().getOutageId() > 0 )
            {
                massOutagePojoDb = (CrmMassOutagePojo) session.get( CrmMassOutagePojo.class, inCrmMassOutageDto
                        .getCrmMassOutagePojo().getOutageId() );
                if ( StringUtils.isValidObj( massOutagePojoDb ) )
                {
                    massOutagePojoDb.setOutageActivity( inCrmMassOutageDto.getCrmMassOutagePojo().getOutageActivity() );
                    session.update( massOutagePojoDb );
                    transaction.commit();
                    serviceCode = CRMServiceCode.CRM001;
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while updateMassOutageActivityStatus ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
    }

    @Override
    public CrmMassOutageDto searchMassOutageEngine( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "Inside MassOutageDaoImpl, searchMassOutageEngine" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmMassOutagePojo.class );
            criteria.add( Restrictions.lt( "outageStartTime", Calendar.getInstance().getTime() ) );
            criteria.add( Restrictions.ne( "outageActivity", CRMStatusCode.RESOLVED.getStatusCode() ) );
            criteria.addOrder( Order.asc( "outageStartTime" ) );
            List<CrmMassOutagePojo> massOutageSearchList = criteria.list();
            if ( StringUtils.isValidObj( massOutageSearchList ) )
            {
                LOGGER.info( "massOutageSearchList****" + massOutageSearchList.size() );
                inCrmMassOutageDto.setMassOutagePojos( massOutageSearchList );
                serviceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while searchMassOutageEngine: ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public CrmMassOutageDto searchCustomerInOutageMaster( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "Inside MassOutageDaoImpl, searchCustomerInOutageMaster" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "GetCustomerByMaster" );
            query.setParameterList( "MASTER", inCrmMassOutageDto.getMasterList() );
            List<String> customerList = query.list();
            customerList.remove( null );
            if ( CommonValidator.isValidCollection( customerList ) )
            {
                serviceCode = CRMServiceCode.CRM001;
                inCrmMassOutageDto.setCustomerList( customerList );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while searchCustomerInOutageMaster: ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public CrmMassOutageDto updateMassOutageMasterActivityStatus( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "In updateMassOutageMasterActivityStatus" );
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmMassOutageMastersPojo.class );
            criteria.add( Restrictions.eq( "outageId", inCrmMassOutageDto.getCrmMassOutageMastersPojo().getOutageId() ) );
            criteria.add( Restrictions.in( "masterName", inCrmMassOutageDto.getMasterList() ) );
            List<CrmMassOutageMastersPojo> massOutageMastersPojos = criteria.list();
            if ( CommonValidator.isValidCollection( massOutageMastersPojos ) )
            {
                Transaction tx = session.beginTransaction();
                for ( CrmMassOutageMastersPojo crmMassOutageMastersPojo : massOutageMastersPojos )
                {
                    crmMassOutageMastersPojo.setOutageActivity( inCrmMassOutageDto.getCrmMassOutageMastersPojo()
                            .getOutageActivity() );
                    session.merge( crmMassOutageMastersPojo );
                }
                tx.commit();
                serviceCode = CRMServiceCode.CRM001;
            }
            /*String hqlUpdate = "update CrmMassOutageMastersPojo set outageActivity = :newStatus where masterName in(:masterName)";
            int updatedEntities = session.createQuery( hqlUpdate )
                    .setString( "newStatus", inCrmMassOutageDto.getCrmMassOutageMastersPojo().getOutageActivity() )
                    .setParameterList( "masterName", inCrmMassOutageDto.getMasterList() ).executeUpdate();*/
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while updateMassOutageMasterActivityStatus ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public CrmMassOutageDto searchMassOutageSociety( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "Inside MassOutageDaoImpl, searchMassOutageSociety" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmMassOutageSocietyPojo> massOutageSocietySearchList = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        CrmMassOutageSocietyPojo outageSocietyPojo = inCrmMassOutageDto.getCrmMassOutageSocietyPojo();
        if ( StringUtils.isValidObj( outageSocietyPojo ) && outageSocietyPojo.getOutageId() > 0 )
        {
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmMassOutageSocietyPojo.class );
                criteria.add( Restrictions.eq( "outageId", outageSocietyPojo.getOutageId() ) );
                massOutageSocietySearchList = criteria.list();
                if ( CommonValidator.isValidCollection( massOutageSocietySearchList ) )
                {
                    LOGGER.info( "CrmMassOutageSocietyPojo Size ::" + massOutageSocietySearchList.size() );
                    inCrmMassOutageDto.setCrmMassOutageSocietyPojos( massOutageSocietySearchList );
                    serviceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    serviceCode = CRMServiceCode.CRM996;
                }
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Getting Error while searchMassOutageSociety: ", ex );
                serviceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                CRMServiceUtils.closeSession( session );
                inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
                inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
            }
        }
        else
        {
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public CrmMassOutageDto updateMassOutageSocietyActivityStatus( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "In updateMassOutageSocietyActivityStatus" );
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        SessionFactory sessionFactory = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmMassOutageSocietyPojo.class );
            criteria.add( Restrictions.in( "societyId", inCrmMassOutageDto.getSocietyList() ) );
            List<CrmMassOutageSocietyPojo> massOutageSocietyPojos = criteria.list();
            if ( CommonValidator.isValidCollection( massOutageSocietyPojos ) )
            {
                Transaction tx = session.beginTransaction();
                for ( CrmMassOutageSocietyPojo crmMassOutageSocietyPojo : massOutageSocietyPojos )
                {
                    crmMassOutageSocietyPojo.setOutageActivity( inCrmMassOutageDto.getCrmMassOutageSocietyPojo()
                            .getOutageActivity() );
                    session.merge( crmMassOutageSocietyPojo );
                }
                tx.commit();
                serviceCode = CRMServiceCode.CRM001;
            }
            /*String hqlUpdate = "update CrmMassOutageMastersPojo set outageActivity = :newStatus where masterName in(:masterName)";
            int updatedEntities = session.createQuery( hqlUpdate )
                    .setString( "newStatus", inCrmMassOutageDto.getCrmMassOutageMastersPojo().getOutageActivity() )
                    .setParameterList( "masterName", inCrmMassOutageDto.getMasterList() ).executeUpdate();*/
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while updateMassOutageSocietyActivityStatus ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }

    @Override
    public CrmMassOutageDto searchCustomerInOutageSociety( CrmMassOutageDto inCrmMassOutageDto )
    {
        LOGGER.info( "Inside MassOutageDaoImpl, searchCustomerInOutageSociety" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        CRMServiceCode serviceCode = CRMServiceCode.CRM997;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            query = session.getNamedQuery( "GetCustomerBySociety" );
            query.setParameterList( "SOCIETY", inCrmMassOutageDto.getSocietyList() );
            List<String> customerList = query.list();
            if ( CommonValidator.isValidCollection( customerList ) )
            {
                serviceCode = CRMServiceCode.CRM001;
                inCrmMassOutageDto.setCustomerList( customerList );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while searchCustomerInOutageSociety: ", ex );
            serviceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmMassOutageDto.setStatusCode( serviceCode.getStatusCode() );
            inCrmMassOutageDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return inCrmMassOutageDto;
    }
}
