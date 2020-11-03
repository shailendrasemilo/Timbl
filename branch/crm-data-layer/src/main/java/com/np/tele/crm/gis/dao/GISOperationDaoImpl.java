package com.np.tele.crm.gis.dao;

import java.util.ArrayList;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.dto.GISDto;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CityPojo;
import com.np.tele.crm.pojos.CountryPojo;
import com.np.tele.crm.pojos.SocietyNetworkPartnerPojo;
import com.np.tele.crm.pojos.SocietyPojo;
import com.np.tele.crm.pojos.StatePojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class GISOperationDaoImpl
    implements IGISOperationDao
{
    private static final Logger     LOGGER  = Logger.getLogger( GISOperationDaoImpl.class );
    private static volatile boolean evicted = true;

    @Override
    public GISDto countryOperation( CountryPojo inCountryPojo )
    {
        LOGGER.info( "Inside createCountry" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        boolean result = false;
        try
        {
            inCountryPojo.setCountryName( StringUtils.upperCase( inCountryPojo.getCountryName() ) );
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CountryPojo.class );
            if ( inCountryPojo.getCountryId() > 0 )
            {
                inCountryPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                transaction = session.beginTransaction();
                session.update( inCountryPojo );
                transaction.commit();
                LOGGER.info( "Successfully Update Country :: " + inCountryPojo.getCountryName() );
                result = true;
            }
            else
            {
                criteria.add( Restrictions.eq( "countryName", inCountryPojo.getCountryName() ) );
                List<CountryPojo> countryPojoList = criteria.list();
                if ( countryPojoList.isEmpty() )
                {
                    transaction = session.beginTransaction();
                    inCountryPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    Long generatedId = (Long) session.save( inCountryPojo );
                    transaction.commit();
                    if ( generatedId > 0 )
                    {
                        LOGGER.info( "New Country Successfully Inserted. Generated ID:: " + generatedId );
                        result = true;
                    }
                }
                else
                {
                    LOGGER.info( "Same Country Already Exist::" + inCountryPojo.getCountryName() );
                    gisDto.setStatusCode( CRMServiceCode.CRM151.getStatusCode() );
                    gisDto.setStatusDesc( CRMServiceCode.CRM151.getStatusDesc() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while create Country: ", ex );
            gisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        if ( result )
        {
            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            evicted = HibernateUtil.evictAll( CountryPojo.class.getName(), "states", null );
        }
        return gisDto;
    }

    @Override
    public GISDto stateOperation( List<StatePojo> inList )
    {
        LOGGER.info( "Inside createState" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        boolean result = false;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( StatePojo.class );
            for ( StatePojo inStatePojo : inList )
            {
                inStatePojo.setStateName( StringUtils.upperCase( inStatePojo.getStateName() ) );
                if ( inStatePojo.getStateId() > 0 )
                {
                    inStatePojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    transaction = session.beginTransaction();
                    session.update( inStatePojo );
                    transaction.commit();
                    LOGGER.info( "Successfully Update State :: " + inStatePojo.getStateName() );
                    result = true;
                }
                else
                {
                    criteria.add( Restrictions.eq( "stateName", inStatePojo.getStateName() ) )
                            .add( Restrictions.eq( "countryId", inStatePojo.getCountryId() ) );
                    List<StatePojo> statePojoList = criteria.list();
                    if ( statePojoList.isEmpty() )
                    {
                        transaction = session.beginTransaction();
                        inStatePojo.setCreatedTime( Calendar.getInstance().getTime() );
                        Long generatedId = (Long) session.save( inStatePojo );
                        transaction.commit();
                        if ( generatedId > 0 )
                        {
                            LOGGER.info( "New State Successfully Inserted. Generated ID:: " + generatedId );
                            result = true;
                        }
                    }
                    else
                    {
                        LOGGER.info( "Same State Already Exist::" + inStatePojo.getStateName() );
                        crmServiceCode = CRMServiceCode.CRM152;
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while create State:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
            LOGGER.error( "Getting Error while create State: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( result )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                evicted = HibernateUtil.evictAll( StatePojo.class.getName(), "cities", null );
            }
        }
        gisDto.setStatePojosList( inList );
        gisDto.setStatusCode( crmServiceCode.getStatusCode() );
        gisDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return gisDto;
    }

    @Override
    public GISDto cityOperation( List<CityPojo> inList )
    {
        LOGGER.info( "Inside createCity" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        boolean result = false;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CityPojo.class );
            for ( CityPojo inCityPojo : inList )
            {
                inCityPojo.setCityName( StringUtils.upperCase( inCityPojo.getCityName() ) );
                if ( inCityPojo.getCityId() > 0 )
                {
                    inCityPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    transaction = session.beginTransaction();
                    session.update( inCityPojo );
                    transaction.commit();
                    LOGGER.info( "Successfully Update City :: " + inCityPojo.getCityName() );
                    result = true;
                }
                else
                {
                    criteria.add( Restrictions.eq( "cityName", inCityPojo.getCityName() ) )
                            .add( Restrictions.eq( "stateId", inCityPojo.getStateId() ) );
                    List<CityPojo> cityPojoList = criteria.list();
                    if ( cityPojoList.isEmpty() )
                    {
                        transaction = session.beginTransaction();
                        inCityPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        Long generatedId = (Long) session.save( inCityPojo );
                        transaction.commit();
                        if ( generatedId > 0 )
                        {
                            LOGGER.info( "New City Successfully Inserted. Generated ID:: " + generatedId );
                            result = true;
                        }
                    }
                    else
                    {
                        LOGGER.info( "Same City Already Exist::" + inCityPojo.getCityName() );
                        crmServiceCode = CRMServiceCode.CRM153;
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while create City:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
            LOGGER.error( "Getting Error while create City: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( result )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                evicted = HibernateUtil.evictAll( CityPojo.class.getName(), "areas", null );
            }
        }
        gisDto.setCityPojosList( inList );
        gisDto.setStatusCode( crmServiceCode.getStatusCode() );
        gisDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return gisDto;
    }

    @Override
    public GISDto areaOperation( List<AreaPojo> inList )
    {
        LOGGER.info( "Inside createArea" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        boolean result = false;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            for ( AreaPojo inAreaPojo : inList )
            {
                inAreaPojo.setArea( StringUtils.upperCase( inAreaPojo.getArea() ) );
                if ( inAreaPojo.getAreaId() > 0 )
                {
                    inAreaPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    transaction = session.beginTransaction();
                    session.update( inAreaPojo );
                    transaction.commit();
                    LOGGER.info( "Successfully Update Area :: " + inAreaPojo.getArea() );
                    result = true;
                }
                else
                {
                    criteria = session.createCriteria( AreaPojo.class );
                    criteria.add( Restrictions.eq( "area", inAreaPojo.getArea() ) )
                            .add( Restrictions.eq( "cityId", inAreaPojo.getCityId() ) );
                    List<AreaPojo> areaPojoList = criteria.list();
                    if ( areaPojoList.isEmpty() )
                    {
                        transaction = session.beginTransaction();
                        inAreaPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        Long generatedId = (Long) session.save( inAreaPojo );
                        transaction.commit();
                        if ( generatedId > 0 )
                        {
                            LOGGER.info( "New Area Successfully Inserted. Generated ID:: " + generatedId );
                            result = true;
                        }
                    }
                    else
                    {
                        LOGGER.info( "Same Area Already Exist::" + inAreaPojo.getArea() );
                        crmServiceCode = CRMServiceCode.CRM154;
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while create Area:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            if ( StringUtils.isValidObj( transaction ) )
            {
                transaction.rollback();
            }
            LOGGER.error( "Getting Error while create Area: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( result )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                evicted = HibernateUtil.evictAll( AreaPojo.class.getName(), null, null );
            }
        }
        gisDto.setAreaPojosList( inList );
        gisDto.setStatusCode( crmServiceCode.getStatusCode() );
        gisDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return gisDto;
    }

    @Override
    public GISDto createSociety( GISDto inGisDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            LOGGER.info( "Partner Id " + inGisDto.getPartnerId() + "  Product Name :: " + inGisDto.getProductName() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            LOGGER.info( "Size of Society List " + inGisDto.getSocietyPojosList().size() );
            for ( SocietyPojo societyPojo : inGisDto.getSocietyPojosList() )
            {
                if ( StringUtils.isNotEmpty( societyPojo.getSocietyName() ) )
                {
                    societyPojo.setSocietyName( StringUtils.upperCase( societyPojo.getSocietyName() ) );
                }
                if ( StringUtils.isNotEmpty( societyPojo.getLocalityName() ) )
                {
                    societyPojo.setLocalityName( StringUtils.upperCase( societyPojo.getLocalityName() ) );
                }
                societyPojo.setConnectableHomes( StringUtils.upperCase( societyPojo.getConnectableHomes() ) );
                if ( societyPojo.getSocietyId() > 0 )
                {
                    criteria = session.createCriteria( SocietyPojo.class );
                    if ( StringUtils.isNotEmpty( societyPojo.getSocietyName() ) )
                    {
                        criteria.add( Restrictions.eq( "societyName", societyPojo.getSocietyName() ) );
                    }
                    if ( StringUtils.isNotEmpty( societyPojo.getLocalityName() ) )
                    {
                        criteria.add( Restrictions.eq( "localityName", societyPojo.getLocalityName() ) );
                    }
                    criteria.add( Restrictions.eq( "areaId", societyPojo.getAreaId() ) );
                    List<SocietyPojo> scList = criteria.list();
                    LOGGER.info( "scList:::" + scList.size() );
                    if ( scList.size() > 0 )
                    {
                        if ( societyPojo.getSocietyId() == scList.get( 0 ).getSocietyId() )
                        {
                            session.merge( societyPojo );
                            if ( inGisDto.getPartnerId() > 0 )
                            {
                                crmServiceCode = saveSocietyNetworkPartner( inGisDto, session, societyPojo );
                            }
                        }
                        else
                        {
                            crmServiceCode = CRMServiceCode.CRM161;
                        }
                    }
                    else
                    {
                        session.merge( societyPojo );
                        LOGGER.info( "Society Data Successfully Update for Id :: " + societyPojo.getSocietyId() );
                        if ( inGisDto.getPartnerId() > 0 )
                        {
                            crmServiceCode = saveSocietyNetworkPartner( inGisDto, session, societyPojo );
                        }
                    }
                }
                else
                {
                    criteria = session.createCriteria( SocietyPojo.class );
                    //code for id basis only update in society table
                    criteria.add( Restrictions.eq( "societyName", societyPojo.getSocietyName() ) );
                    criteria.add( Restrictions.eq( "localityName", societyPojo.getLocalityName() ) );
                    criteria.add( Restrictions.eq( "areaId", societyPojo.getAreaId() ) );
                    List<SocietyPojo> scList = criteria.list();
                    if ( scList.isEmpty() )
                    {
                        societyPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        Long id = (Long) session.save( societyPojo );
                        if ( id > 0 )
                        {
                            LOGGER.info( "GIS Society Inserted Successfully. Generated Society Id :: " + id
                                    + " Society Name:: " + societyPojo.getSocietyName() + " Locality Name:: "
                                    + societyPojo.getLocalityName() );
                            societyPojo.setSocietyId( id );
                        }
                        crmServiceCode = saveSocietyNetworkPartner( inGisDto, session, societyPojo );
                    }
                    else
                    {
                        SocietyPojo dbSocietyPojo = scList.get( 0 );
                        LOGGER.info( "Same Society :: " + dbSocietyPojo.getSocietyName()
                                + " Already Exist With Area Id" + dbSocietyPojo.getAreaId() + "Going For Update" );
                        societyPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        societyPojo.setLastModifiedBy( societyPojo.getCreatedBy() );
                        societyPojo.setSocietyId( dbSocietyPojo.getSocietyId() );
                        societyPojo.setCreatedTime( dbSocietyPojo.getCreatedTime() );
                        session.merge( societyPojo );
                        LOGGER.info( "GIS Society Updated Successfully, Updated Society Name:: "
                                + societyPojo.getSocietyName() );
                        criteria = session.createCriteria( SocietyNetworkPartnerPojo.class );
                        criteria.add( Restrictions.eq( "societyId", societyPojo.getSocietyId() ) );
                        criteria.add( Restrictions.eq( "partnerId", inGisDto.getPartnerId() ) );
                        criteria.add( Restrictions.eq( "productName", inGisDto.getProductName() ) );
                        List<SocietyNetworkPartnerPojo> networkPartnerPojos = criteria.list();
                        if ( networkPartnerPojos.isEmpty() )
                        {
                            LOGGER.info( "Going for insert in Society_Network_Partner table" );
                            crmServiceCode = saveSocietyNetworkPartner( inGisDto, session, societyPojo );
                        }
                        else
                        {
                            LOGGER.info( "Society :: " + societyPojo.getSocietyName() + "Locality :: "
                                    + societyPojo.getLocalityName() + "  Already Mapped With Product Name :: "
                                    + inGisDto.getProductName() + " And Partner Id :: " + inGisDto.getPartnerId() );
                            if ( !StringUtils.equals( CRMParameter.GISFILE_UPLOAD.getParameter(),
                                                      inGisDto.getOperationType() ) )
                            {
                                crmServiceCode = CRMServiceCode.CRM161;
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.CRM162;
                            }
                        }
                    }
                    session.flush();
                    session.clear();
                }
            }
            if ( !StringUtils.isValidObj( crmServiceCode )
                    || ( StringUtils.isValidObj( crmServiceCode ) && ( crmServiceCode.getStatusCode()
                            .equals( CRMServiceCode.CRM001.getStatusCode() ) ) )
                    || ( StringUtils.isValidObj( crmServiceCode ) && ( crmServiceCode.getStatusCode()
                            .equals( CRMServiceCode.CRM162.getStatusCode() ) ) ) )
            {
                transaction.commit();
                if ( !StringUtils.isValidObj( crmServiceCode )
                        || !crmServiceCode.getStatusCode().equals( CRMServiceCode.CRM162.getStatusCode() ) )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                evicted = HibernateUtil.evictAll( SocietyPojo.class.getName(), "societyNetworkPartners", null );
                evicted = HibernateUtil.evictAll( SocietyNetworkPartnerPojo.class.getName(), null, null );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while Create GIS Society method :", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while Create GIS Society method ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inGisDto.setStatusCode( crmServiceCode.getStatusCode() );
            inGisDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inGisDto;
    }

    private CRMServiceCode saveSocietyNetworkPartner( GISDto inGisDto, Session session, SocietyPojo societyPojo )
    {
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        List<SocietyNetworkPartnerPojo> networkPartnerPojos = null;
        try
        {
            criteria = session.createCriteria( SocietyNetworkPartnerPojo.class );
            criteria.add( Restrictions.eq( "societyId", societyPojo.getSocietyId() ) );
            criteria.add( Restrictions.eq( "productName", inGisDto.getProductName() ) );
            criteria.add( Restrictions.eq( "partnerId", inGisDto.getPartnerId() ) );
            networkPartnerPojos = criteria.list();
            if ( !CommonValidator.isValidCollection( networkPartnerPojos ) )
            {
                LOGGER.info( "Going for insert in Society Network Partner" );
                SocietyNetworkPartnerPojo networkPartnerPojo = new SocietyNetworkPartnerPojo();
                networkPartnerPojo.setSocietyId( societyPojo.getSocietyId() );
                networkPartnerPojo.setCreatedBy( societyPojo.getCreatedBy() );
                networkPartnerPojo.setCreatedTime( Calendar.getInstance().getTime() );
                networkPartnerPojo.setPartnerId( inGisDto.getPartnerId() );
                networkPartnerPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                networkPartnerPojo.setProductName( inGisDto.getProductName() );
                if ( StringUtils.isValidObj( inGisDto.getSoNetworkPartnerPojo() ) )
                {
                    networkPartnerPojo.setRfsDus( inGisDto.getSoNetworkPartnerPojo().getRfsDus() );
                }
                else
                {
                    networkPartnerPojo.setRfsDus( societyPojo.getRfsDus() );
                }
                Long generatedId = (Long) session.save( networkPartnerPojo );
                LOGGER.info( "Successfully Insert SocietyNetworkPartnerPojo, Generated Id :: " + generatedId );
            }
            else
            {
                SocietyNetworkPartnerPojo networkPartnerPojo = networkPartnerPojos.get( 0 );
                networkPartnerPojo.setLastModifiedBy( societyPojo.getLastModifiedBy() );
                networkPartnerPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                networkPartnerPojo.setPartnerId( inGisDto.getPartnerId() );
                networkPartnerPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                networkPartnerPojo.setProductName( inGisDto.getProductName() );
                if ( StringUtils.isValidObj( inGisDto.getSoNetworkPartnerPojo() ) )
                {
                    networkPartnerPojo.setRfsDus( inGisDto.getSoNetworkPartnerPojo().getRfsDus() );
                }
                else
                {
                    networkPartnerPojo.setRfsDus( societyPojo.getRfsDus() );
                }
                session.update( networkPartnerPojo );
                LOGGER.info( "Successfully update SocietyNetworkPartnerPojo:: " );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while inserting SocietyNetworkPartnerPojo", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        return crmServiceCode;
    }

    @Override
    public GISDto getCountryList( CountryPojo inCountryPojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CountryPojo.class );
            if ( StringUtils.isValidObj( inCountryPojo ) )
            {
                if ( StringUtils.isNotBlank( inCountryPojo.getCountryName() ) )
                {
                    if ( evicted )
                    {
                        CRMServiceUtils.getDBValues( CountryPojo.class, "countryName", inCountryPojo.getCountryName() );
                        evicted = false;
                    }
                    criteria.add( Restrictions.ilike( "countryName", inCountryPojo.getCountryName(), MatchMode.ANYWHERE ) );
                }
                if ( StringUtils.isNotBlank( inCountryPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCountryPojo.getStatus() ) );
                    session.enableFilter( "ActiveStates" ).setParameter( "stStatus", inCountryPojo.getStatus() );
                    session.enableFilter( "ActiveCities" ).setParameter( "stStatus", inCountryPojo.getStatus() );
                    session.enableFilter( "ActiveAreas" ).setParameter( "stStatus", inCountryPojo.getStatus() );
                    //                    session.enableFilter( "ActiveLocalities" ).setParameter( "stStatus", inCountryPojo.getStatus() );
                    /* session.enableFilter( "ActiveSocieties" ).setParameter( "stStatus", inCountryPojo.getStatus() );
                     session.enableFilter( "Feasible" ).setParameter( "stStatus", inCountryPojo.getStatus() );*/
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CountryPojo.class.getName() );
            List<CountryPojo> countryPojoList = criteria.list();
            LOGGER.info( "No of Countries Found :: " + countryPojoList.size() );
            gisDto.setCountryPojosList( countryPojoList );
            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while searching Country: ", ex );
            gisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return gisDto;
    }

    @Override
    public GISDto getStateList( StatePojo inStatePojo )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( StatePojo.class );
            if ( StringUtils.isValidObj( inStatePojo ) )
            {
                if ( !StringUtils.isEmpty( inStatePojo.getStateName() ) )
                {
                    criteria.add( Restrictions.ilike( "stateName", inStatePojo.getStateName(), MatchMode.ANYWHERE ) );
                }
                if ( StringUtils.isNotBlank( inStatePojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inStatePojo.getStatus() ) );
                }
                if ( inStatePojo.getCountryId() > 0 )
                {
                    criteria.add( Restrictions.eq( "countryId", inStatePojo.getCountryId() ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( StatePojo.class.getName() );
            List<StatePojo> statePojoList = criteria.list();
            LOGGER.info( "No Of State found:: " + statePojoList.size() );
            gisDto.setStatePojosList( statePojoList );
            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            LOGGER.info( "AFTER++ No Of State found from DTO(service):: " + gisDto.getStatePojosList().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Searching State: ", ex );
            gisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return gisDto;
    }

    @Override
    public GISDto getCityList( CityPojo inCityPojo )
    {
        LOGGER.info( "Inside createCity" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CityPojo.class );
            if ( StringUtils.isValidObj( inCityPojo ) )
            {
                if ( !StringUtils.isEmpty( inCityPojo.getCityName() ) )
                {
                    criteria.add( Restrictions.ilike( "cityName", inCityPojo.getCityName(), MatchMode.ANYWHERE ) );
                }
                if ( StringUtils.isNotBlank( inCityPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inCityPojo.getStatus() ) );
                }
                if ( inCityPojo.getStateId() > 0 )
                {
                    criteria.add( Restrictions.eq( "stateId", inCityPojo.getStateId() ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CityPojo.class.getName() );
            List<CityPojo> cityPojoList = criteria.list();
            gisDto.setCityPojosList( cityPojoList );
            LOGGER.info( "No Of City Found :: " + cityPojoList.size() );
            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
            LOGGER.info( "++No Of City Found :: SERVER " + gisDto.getCityPojosList().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Searching City: ", ex );
            gisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return gisDto;
    }

    @Override
    public GISDto getAreaList( final GISDto inGisDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        AreaPojo inAreaPojo = inGisDto.getAreaPojo();
        try
        {
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( AreaPojo.class );
            if ( StringUtils.isValidObj( inAreaPojo ) )
            {
                if ( !StringUtils.isEmpty( inAreaPojo.getArea() ) )
                {
                    criteria.add( Restrictions.ilike( "area", inAreaPojo.getArea(), MatchMode.ANYWHERE ) );
                }
                if ( StringUtils.isNotBlank( inAreaPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inAreaPojo.getStatus() ) );
                }
                if ( inAreaPojo.getCityId() > 0 )
                {
                    criteria.add( Restrictions.eq( "cityId", inAreaPojo.getCityId() ) );
                }
                else if ( StringUtils.isValidObj( inGisDto.getCityPojosList() )
                        && !inGisDto.getCityPojosList().isEmpty() )
                {
                    Set<Long> cityIds = CRMServiceUtils.getGISIds( inGisDto.getCityPojosList(), "cityId" );
                    if ( StringUtils.isValidObj( cityIds ) && !cityIds.isEmpty() )
                    {
                        criteria.add( Restrictions.in( "cityId", cityIds ) );
                    }
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( AreaPojo.class.getName() );
            List<AreaPojo> areaPojoList = criteria.list();
            gisDto.setAreaPojosList( areaPojoList );
            LOGGER.info( "No Of Area Found " + areaPojoList.size() );
            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Searching Area: ", ex );
            gisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return gisDto;
    }

    @Override
    public GISDto getSocietyList( GISDto inGisDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        GISDto gisDto = null;
        try
        {
            SocietyPojo inSocietyPojo = inGisDto.getSocietyPojo();
            gisDto = new GISDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( SocietyPojo.class );
            if ( StringUtils.isValidObj( inSocietyPojo ) )
            {
                if ( StringUtils.isNotEmpty( inSocietyPojo.getSocietyName() ) )
                {
                    criteria.add( Restrictions.or( Restrictions.ilike( "societyName", inSocietyPojo.getSocietyName(),
                                                                       MatchMode.ANYWHERE ), Restrictions
                            .ilike( "localityName", inSocietyPojo.getSocietyName(), MatchMode.ANYWHERE ) ) );
                }
                if ( StringUtils.isNotBlank( inSocietyPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inSocietyPojo.getStatus() ) );
                }
                if ( inSocietyPojo.getAreaId() > 0 )
                {
                    criteria.add( Restrictions.eq( "areaId", inSocietyPojo.getAreaId() ) );
                }
                if ( StringUtils.isValidObj( inSocietyPojo.getSocietyNetworkPartners() ) )
                {
                    List<SocietyNetworkPartnerPojo> networkPartnerPojos = new ArrayList<SocietyNetworkPartnerPojo>( inSocietyPojo
                            .getSocietyNetworkPartners() );
                    if ( !networkPartnerPojos.isEmpty() )
                    {
                        SocietyNetworkPartnerPojo societyNetworkPartnerPojo = networkPartnerPojos.get( 0 );
                        LOGGER.info( "Size of network partner list :::" + networkPartnerPojos.size() );
                        if ( StringUtils.isValidObj( societyNetworkPartnerPojo )
                                && ( societyNetworkPartnerPojo.getPartnerId() > 0 )
                                || StringUtils.isNotBlank( societyNetworkPartnerPojo.getProductName() ) )
                        {
                            criteria.createCriteria( "societyNetworkPartners", "soc" );
                            if ( societyNetworkPartnerPojo.getPartnerId() > 0 )
                            {
                                criteria.add( Restrictions.eq( "soc.partnerId",
                                                               societyNetworkPartnerPojo.getPartnerId() ) );
                                LOGGER.info( "Partner iD:::::" + societyNetworkPartnerPojo.getPartnerId() );
                            }
                            if ( StringUtils.isNotBlank( societyNetworkPartnerPojo.getProductName() ) )
                            {
                                LOGGER.info( "Product Name ::" + societyNetworkPartnerPojo.getProductName() );
                                criteria.add( Restrictions.eq( "soc.productName",
                                                               societyNetworkPartnerPojo.getProductName() ) );
                            }
                            if ( StringUtils.isNotBlank( inSocietyPojo.getStatus() ) )
                            {
                                criteria.add( Restrictions.eq( "soc.status", inSocietyPojo.getStatus() ) );
                            }
                            criteria.addOrder( Order.asc( "soc.rfsDus" ) ).addOrder( Order.asc( "soc.partnerId" ) );
                        }
                    }
                }
                if ( CommonValidator.isValidCollection( inGisDto.getAreaPojosList() ) )
                {
                    Set<Long> areaList = new HashSet<Long>();
                    for ( AreaPojo areaPojo : inGisDto.getAreaPojosList() )
                    {
                        LOGGER.info( "Area Name :" + areaPojo.getArea() + ",Area ID::" + areaPojo.getAreaId() );
                        areaList.add( areaPojo.getAreaId() );
                    }
                    if ( CommonValidator.isValidCollection( areaList ) )
                    {
                        criteria.add( Restrictions.in( "areaId", areaList ) );
                    }
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( SocietyPojo.class.getName() );
            List<SocietyPojo> societyPojoList = criteria.list();
            gisDto.setSocietyPojosList( new ArrayList<SocietyPojo>( new HashSet<SocietyPojo>( societyPojoList ) ) );
            LOGGER.info( "No of Society Found:: " + societyPojoList.size() );
            gisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error while Searching Society: ", ex );
            gisDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            gisDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return gisDto;
    }

    //
    //    public static void main( String[] args )
    //    {
    //        GISOperationDaoImpl daoImpl = new GISOperationDaoImpl();
    //        CountryPojo countryPojo = new CountryPojo();
    //        countryPojo.setCountryName( IAppConstants.COUNTRY_INDIA );
    //        daoImpl.getCountryList( countryPojo );
    //    }
    @Override
    public GISDto updateSocietyStatus( SocietyPojo inSocietyPojo )
    {
        GISDto gisDto = new GISDto();
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        Set<SocietyNetworkPartnerPojo> networkPartnerList = new HashSet<SocietyNetworkPartnerPojo>();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inSocietyPojo ) && inSocietyPojo.getSocietyId() > 0 )
            {
                SocietyPojo pojo = (SocietyPojo) session.get( SocietyPojo.class, inSocietyPojo.getSocietyId() );
                pojo.setLastModifiedBy( inSocietyPojo.getLastModifiedBy() );
                pojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                pojo.setStatus( inSocietyPojo.getStatus() );
                if ( StringUtils.equals( inSocietyPojo.getStatus(), CRMStatusCode.INACTIVE.getStatusCode() ) )
                {
                    for ( SocietyNetworkPartnerPojo societyNetworkPojo : pojo.getSocietyNetworkPartners() )
                    {
                        societyNetworkPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        societyNetworkPojo.setLastModifiedBy( inSocietyPojo.getLastModifiedBy() );
                        societyNetworkPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                        networkPartnerList.add( societyNetworkPojo );
                    }
                    LOGGER.info( "Status::" + inSocietyPojo.getStatus() + "Size Network Pojo::"
                            + networkPartnerList.size() );
                    pojo.setSocietyNetworkPartners( networkPartnerList );
                }
                session.update( pojo );
                LOGGER.info( "Society Data Successfully Update for Id :: " + pojo.getSocietyId() );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( SocietyPojo.class.getName(), "societyNetworkPartners",
                                        inSocietyPojo.getSocietyId() );
            }
            gisDto.setStatusCode( crmServiceCode.getStatusCode() );
            gisDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return gisDto;
    }

    //        static
    //        {
    //            DOMConfigurator.configureAndWatch( "E:/Official/delivery/logs/log4j.xml" );
    //        }
    //    
    //        public static void main( String[] args )
    //        {
    //            CountryPojo pojo = new CountryPojo();
    //            pojo.setCountryName( IAppConstants.COUNTRY_INDIA );
    //            pojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
    //            new GISOperationDaoImpl().getCountryList( pojo );
    //            System.out
    //                    .println( "-------------------------------------------------------------Rerunning-----------------------------------------------------" );
    //            LOGGER.debug( "-------------------------------------------------------------Rerunning-----------------------------------------------------" );
    //            new GISOperationDaoImpl().getCountryList( pojo );
    //            System.out
    //                    .println( "-------------------------------------------------------------Rerunning1111-----------------------------------------------------" );
    //            LOGGER.debug( "-------------------------------------------------------------Rerunning1111-----------------------------------------------------" );
    //            HibernateUtil.getSessionFactory().evictEntity( CountryPojo.class.getName() );
    //            HibernateUtil.getSessionFactory().evictCollection( CountryPojo.class.getName() + ".states" );
    //            evicted = true;
    //            new GISOperationDaoImpl().getCountryList( pojo );
    //        }
    @Override
    public GISDto updateSocietyNPStatus( SocietyNetworkPartnerPojo inSocietyNetworkPartnerPojo )
    {
        GISDto gisDto = new GISDto();
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inSocietyNetworkPartnerPojo ) && inSocietyNetworkPartnerPojo.getId() > 0 )
            {
                SocietyNetworkPartnerPojo dbPojo = (SocietyNetworkPartnerPojo) session
                        .get( SocietyNetworkPartnerPojo.class, inSocietyNetworkPartnerPojo.getId() );
                dbPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                dbPojo.setLastModifiedBy( inSocietyNetworkPartnerPojo.getLastModifiedBy() );
                dbPojo.setStatus( inSocietyNetworkPartnerPojo.getStatus() );
                session.merge( dbPojo );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM997;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while change status:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( SocietyPojo.class.getName(), "societyNetworkPartners",
                                        inSocietyNetworkPartnerPojo.getSocietyId() );
            }
            gisDto.setStatusCode( crmServiceCode.getStatusCode() );
            gisDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return gisDto;
    }

    @Override
    public GISDto getGISIdByName( GISDto inGisDto, String str )
    {
        inGisDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
        SessionFactory sessionFactory = null;
        Session session = null;
        Query query = null;
        try
        {
            LOGGER.info( inGisDto.getStateName() + "StateId" + inGisDto.getCityName() + "CityId"
                    + inGisDto.getAreaName() + "Area" + inGisDto.getSocietyName() );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.equals( str, "ByName" ) )
            {
                query = session.getNamedQuery( "getGISIdByName" );
                query.setString( "STATE", inGisDto.getStateName() );
                query.setString( "CITY", inGisDto.getCityName() );
                query.setString( "AREA", inGisDto.getAreaName() );
                String[] strarr = inGisDto.getSocietyName().split( "-" );
                if ( strarr.length > 1 )
                {
                    query.setString( "LOCALITY", strarr[0].trim() );
                    query.setString( "SOCIETY", strarr[1].trim() );
                }
                else
                {
                    query.setString( "LOCALITY", inGisDto.getSocietyName() );
                    query.setString( "SOCIETY", null );
                }
            }
            else if ( StringUtils.equals( str, "ByID" ) )
            {
                query = session.getNamedQuery( "getGISIdByID" );
                query.setString( "STATE", inGisDto.getStateName() );
                query.setString( "CITY", inGisDto.getCityName() );
                query.setString( "AREA", inGisDto.getAreaName() );
                query.setString( "SOCIETY", inGisDto.getSocietyName() );
            }
            query.setResultTransformer( Transformers.aliasToBean( AreaPojo.class ) );
            List<AreaPojo> areaPojos = query.list();
            LOGGER.info( areaPojos.size() );
            if ( CommonValidator.isValidCollection( areaPojos ) )
            {
                inGisDto.setAreaPojo( areaPojos.get( 0 ) );
                inGisDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
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
        return inGisDto;
    }
}
