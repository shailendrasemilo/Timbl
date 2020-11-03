package com.np.tele.crm.masterdata.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;

import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.pojos.AccessGroupPojo;
import com.np.tele.crm.pojos.CrmHolidayDetails;
import com.np.tele.crm.pojos.CrmInboxPojo;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmParameterPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmRolesPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.GroupsPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.ProjectsPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class MasterDataDaoImpl
    implements IMasterDataDao
{
    private static final Logger      LOGGER            = Logger.getLogger( MasterDataDaoImpl.class );
    private static Map<Long, String> OPTION_82_RAW_MAP = null;

    @Override
    public List<PartnerPojo> getPartnerPojoList( final MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<PartnerPojo> partnerPojoList = null;
        List<PartnerPojo> uniquePartnerList = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( PartnerPojo.class );
            if ( StringUtils.isValidObj( inMasterDataDto ) && StringUtils.isValidObj( inMasterDataDto.getPartnerPojo() ) )
            {
                if ( StringUtils.isNotBlank( inMasterDataDto.getPartnerPojo().getCurrentStatus() ) )
                {
                    criteria.add( Restrictions
                            .eq( "currentStatus", inMasterDataDto.getPartnerPojo().getCurrentStatus() ) );
                }
                String partnerType = inMasterDataDto.getPartnerPojo().getPartnerType();
                String bussinessType = inMasterDataDto.getPartnerPojo().getBussinessType();
                long partnerId = inMasterDataDto.getPartnerPojo().getPartnerId();
                if ( StringUtils.isNotBlank( partnerType ) || StringUtils.isNotBlank( bussinessType ) )
                {
                    criteria.createCriteria( "crmPartnerDetailses", "PDM" );
                }
                if ( StringUtils.isNotBlank( partnerType ) )
                {
                    criteria.add( Restrictions.eq( "PDM.partnerType", partnerType ) );
                }
                if ( StringUtils.isNotBlank( bussinessType ) )
                {
                    criteria.add( Restrictions.eq( "PDM.bussinessType", bussinessType ) );
                }
                if ( partnerId > 0 )
                {
                    criteria.add( Restrictions.eq( "partnerId", partnerId ) );
                }
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( PartnerPojo.class.getName() );
            partnerPojoList = criteria.list();
            if ( StringUtils.isValidObj( partnerPojoList ) )
            {
                Set<PartnerPojo> paSet = new HashSet<PartnerPojo>( partnerPojoList );
                uniquePartnerList = new ArrayList<PartnerPojo>( paSet );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getPartnerPojoList method", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return uniquePartnerList;
    }

    @Override
    public List<CrmRolesPojo> getCRMRolesPojoList()
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmRolesPojo> crmRolesPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmRolesPojo.class );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.addOrder( Order.asc( "roleDescription" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmRolesPojo.class.getName() );
            crmRolesPojo = criteria.list();
            LOGGER.info( "Size " + crmRolesPojo.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getPartnerPojoList method", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmRolesPojo;
    }

    @Override
    public MasterDataDto getCRMParameterPojoListForPrmtrGroup( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmParameterPojo> crmParameterPojos = null;
        try
        {
            ProjectsPojo projectsPojo = null;
            if ( StringUtils.isValidObj( inMasterDataDto ) )
            {
                projectsPojo = inMasterDataDto.getProjectsPojo();
            }
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmParameterPojo.class );
            if ( StringUtils.isValidObj( projectsPojo ) )
            {
                criteria.createCriteria( "projectsPojo", "p" );
                if ( projectsPojo.getProjectId() > 0 )
                {
                    criteria.add( Restrictions.eq( "p.projectId", projectsPojo.getProjectId() ) );
                }
                else if ( StringUtils.isNotBlank( projectsPojo.getProjectName() ) )
                {
                    criteria.add( Restrictions.eq( "p.projectName", projectsPojo.getProjectName() ) );
                }
                if ( StringUtils.isNotBlank( projectsPojo.getProjectType() ) )
                {
                    criteria.add( Restrictions.eq( "p.projectType", projectsPojo.getProjectType() ) );
                }
                if ( StringUtils.isNotBlank( projectsPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "p.status", projectsPojo.getStatus() ) );
                }
                if ( StringUtils.isValidObj( projectsPojo.getCrmParameterPojosSet() )
                        && !projectsPojo.getCrmParameterPojosSet().isEmpty() )
                {
                    crmParameterPojos = new ArrayList<CrmParameterPojo>( projectsPojo.getCrmParameterPojosSet() );
                    CrmParameterPojo crmParameterPojo = crmParameterPojos.get( 0 );
                    if ( StringUtils.isValidObj( crmParameterPojo ) )
                    {
                        if ( StringUtils.isNotBlank( crmParameterPojo.getParameterName() ) )
                        {
                            criteria.add( Restrictions.eq( "parameterName", crmParameterPojo.getParameterName() ) );
                        }
                        if ( StringUtils.isNotBlank( crmParameterPojo.getParameterGroup() ) )
                        {
                            criteria.add( Restrictions.eq( "parameterGroup", crmParameterPojo.getParameterGroup() ) );
                        }
                        if ( StringUtils.isNotBlank( crmParameterPojo.getParameterGroupType() ) )
                        {
                            criteria.add( Restrictions.eq( "parameterGroupType",
                                                           crmParameterPojo.getParameterGroupType() ) );
                        }
                        if ( StringUtils.isNotBlank( crmParameterPojo.getStatus() ) )
                        {
                            criteria.add( Restrictions.eq( "status", crmParameterPojo.getStatus() ) );
                        }
                    }
                }
            }
            criteria.addOrder( Order.asc( "parameterName" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmParameterPojo.class.getName() );
            crmParameterPojos = criteria.list();
            if ( !StringUtils.isValidObj( crmParameterPojos ) )
            {
                crmParameterPojos = new ArrayList<CrmParameterPojo>();
            }
            LOGGER.info( "Size " + crmParameterPojos.size() );
            inMasterDataDto.setCrmParameterPojos( crmParameterPojos );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getPartnerPojoList method", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto createCRMRolesGroup( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        boolean returnValue = false;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria( GroupsPojo.class );
            criteria.add( Restrictions.eq( "groupName", inMasterdataDto.getGroupsPojo().getGroupName() ) );
            criteria.add( Restrictions.eq( "groupType", inMasterdataDto.getGroupsPojo().getGroupType() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( GroupsPojo.class.getName() );
            List<GroupsPojo> groupsPojos = criteria.list();
            if ( groupsPojos.size() > 0 )
            {
                crmServiceCode = CRMServiceCode.CRM043;
                LOGGER.info( "Same Group Name Already Exist" + inMasterdataDto.getGroupsPojo().getGroupName() );
            }
            else
            {
                GroupsPojo groupsPojo = inMasterdataDto.getGroupsPojo();
                LOGGER.info( "group Type" + groupsPojo.getGroupType() );
                groupsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                groupsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                Long id = (Long) session.save( groupsPojo );
                if ( id > 0 )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                    LOGGER.info( "Role Group Inserted Successfully. Generated Id :: " + id );
                    returnValue = true;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM999;
                    LOGGER.info( "Getting Error While Saving Roles Group" );
                }
                if ( returnValue )
                {
                    transaction.commit();
                    //for auditLog Maintain
                    StringBuilder oldValues = new StringBuilder();
                    StringBuilder newValues = new StringBuilder();
                    oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                    newValues.append( "RoleGroupName=" ).append( groupsPojo.getGroupName() )
                            .append( IAppConstants.COMMA ).append( "RoleGroupType=" )
                            .append( groupsPojo.getGroupType() ).append( IAppConstants.COMMA );
                    String mappingId = String.valueOf( groupsPojo.getGroupId() );
                    String createdBy = groupsPojo.getCreatedBy();
                    returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                  IAppConstants.DASH,
                                                                  CrmActionEnum.GENERATED.getActionCode(),
                                                                  CRMRequestType.MASTER.getRequestCode(),
                                                                  inMasterdataDto.getClientIPAddress(),
                                                                  inMasterdataDto.getServerIPAddress() );
                    if ( returnValue )
                    {
                        LOGGER.info( "Successfully insert audit log for create role group" );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Create Role Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Getting Error In Create Role Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( returnValue )
            {
                HibernateUtil.evictAll( GroupsPojo.class.getName(), "accessGroups", null );
            }
        }
        inMasterdataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterdataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterdataDto;
    }

    /* public static void main( String[] args )
     {
         try
         {
             CrmRolesPojo crmRolesPojo1 = new CrmRolesPojo();
             crmRolesPojo1.setRoleId( 1 );
             CrmRolesPojo crmRolesPojo2 = new CrmRolesPojo();
             crmRolesPojo2.setRoleId( 2 );
             Set<AccessGroupPojo> accessGroupPojos = new HashSet<AccessGroupPojo>();
             AccessGroupPojo accessGroupPojo1 = new AccessGroupPojo();
             accessGroupPojo1.setCrmRoles( crmRolesPojo2 );
             accessGroupPojo1.setStatus( "A" );
             accessGroupPojo1.setReadAccess( true );
             accessGroupPojo1.setUpdateAccess( false );
             accessGroupPojo1.setCreatedBy( "test" );
             accessGroupPojo1.setCreatedTime( Calendar.getInstance().getTime() );
             AccessGroupPojo accessGroupPojo = new AccessGroupPojo();
             accessGroupPojo.setCrmRoles( crmRolesPojo1 );
             accessGroupPojo.setStatus( "A" );
             accessGroupPojo.setReadAccess( true );
             accessGroupPojo.setUpdateAccess( true );
             accessGroupPojo.setCreatedBy( "test" );
             accessGroupPojo.setCreatedTime( Calendar.getInstance().getTime() );
             accessGroupPojos.add( accessGroupPojo1 );
             accessGroupPojos.add( accessGroupPojo );
             GroupsPojo groupsPojo = new GroupsPojo();
             groupsPojo.setGroupName( "deepak" );
             groupsPojo.setGroupType( "role" );
             groupsPojo.setGroupDescription( "for testingsss" );
             groupsPojo.setCreatedBy( "testind" );
             groupsPojo.setCreatedTime( Calendar.getInstance().getTime() );
             groupsPojo.setAccessGroups( accessGroupPojos );
             MasterDataDto dataDto = new MasterDataDto();
             dataDto.setGroupsPojo( groupsPojo );
             MasterDataDaoImpl daoImpl = new MasterDataDaoImpl();
             daoImpl.createCRMRolesGroup( dataDto );
         }
         catch ( Exception ex )
         {
             System.out.println( ex );
         }
     }
    */
    @Override
    public MasterDataDto updateCRMRolesGroup( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            GroupsPojo groupsPojo = inMasterdataDto.getGroupsPojo();
            GroupsPojo groupsPojoFmDb = (GroupsPojo) session.get( GroupsPojo.class, groupsPojo.getGroupId() );
            String newStatus = groupsPojo.getStatus();
            if ( StringUtils.isValidObj( groupsPojoFmDb ) )
            {
                LOGGER.info( "In If Block" );
                session.flush();
                session.clear();
                groupsPojoFmDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                groupsPojoFmDb.setGroupDescription( groupsPojo.getGroupDescription() );
                groupsPojoFmDb.setLastModifiedBy( groupsPojo.getLastModifiedBy() );
                groupsPojoFmDb.getAccessGroups().removeAll( groupsPojo.getAccessGroups() );
                groupsPojoFmDb.getAccessGroups().addAll( groupsPojo.getAccessGroups() );
                for ( AccessGroupPojo accGroupPojo : groupsPojoFmDb.getAccessGroups() )
                {
                    accGroupPojo.setGroups( groupsPojoFmDb );
                }
                transaction = session.beginTransaction();
                session.merge( groupsPojoFmDb );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                LOGGER.info( "Successfully Update Role Group" );
                //for auditLog Maintain
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                oldValues.append( "groupName=" ).append( groupsPojoFmDb.getGroupName() ).append( IAppConstants.COMMA )
                        .append( "groupType=" ).append( groupsPojoFmDb.getGroupType() ).append( IAppConstants.COMMA )
                        .append( "status=" ).append( groupsPojoFmDb.getStatus() ).append( IAppConstants.COMMA );
                newValues.append( "groupName=" ).append( groupsPojo.getGroupName() ).append( IAppConstants.COMMA )
                        .append( "groupType=" ).append( groupsPojo.getGroupType() ).append( IAppConstants.COMMA )
                        .append( "status=" ).append( newStatus ).append( IAppConstants.COMMA );
                String mappingId = String.valueOf( groupsPojoFmDb.getGroupId() );
                String createdBy = groupsPojo.getLastModifiedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH, CRMServiceUtils
                                                                              .getEventId( CRMEvents.UPDATE_ROLE_GROUP
                                                                                      .getEventName() ),
                                                                      CRMRequestType.MASTER.getRequestCode(),
                                                                      inMasterdataDto.getClientIPAddress(),
                                                                      inMasterdataDto.getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log for update role group" );
                }
            }
            else
            {
                LOGGER.info( "Record Id Not Found in Database" );
                crmServiceCode = CRMServiceCode.CRM014;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Update Role Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Getting Error In Update Role Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( GroupsPojo.class.getName(), "accessGroups", null );
                getGroupById( inMasterdataDto );
            }
        }
        return inMasterdataDto;
    }

    @Override
    public MasterDataDto createCRMParameterGroup( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        boolean returnValue = false;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            GroupsPojo groupsPojo = inMasterdataDto.getGroupsPojo();
            criteria = session.createCriteria( GroupsPojo.class );
            criteria.add( Restrictions.eq( "groupName", inMasterdataDto.getGroupsPojo().getGroupName() ) );
            criteria.add( Restrictions.eq( "groupType", inMasterdataDto.getGroupsPojo().getGroupType() ) );
            List<GroupsPojo> groupsPojos = criteria.list();
            if ( groupsPojos.size() > 0 )
            {
                crmServiceCode = CRMServiceCode.CRM043;
                LOGGER.info( "Same Group Name Already Exist" + inMasterdataDto.getGroupsPojo().getGroupName() );
            }
            else
            {
                groupsPojo.setCreatedTime( Calendar.getInstance().getTime() );
                groupsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                LOGGER.info( "size of accessgroup pojo " + groupsPojo.getAccessGroups().size() );
                LOGGER.info( "Server Access Groups to Create:" + groupsPojo.getAccessGroups() );
                Long id = (Long) session.save( groupsPojo );
                if ( id > 0 )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                    LOGGER.info( "Parameter Group Inserted Successfully. Generated Id :: " + id );
                    returnValue = true;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM999;
                    LOGGER.info( "Getting Error While Saving Parameter Group" );
                }
                if ( returnValue )
                {
                    transaction.commit();
                    //for auditLog Maintain
                    StringBuilder oldValues = new StringBuilder();
                    StringBuilder newValues = new StringBuilder();
                    oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                    newValues.append( "ParameterGroupName=" ).append( groupsPojo.getGroupName() )
                            .append( IAppConstants.COMMA ).append( "ParameterGroupType=" )
                            .append( groupsPojo.getGroupType() ).append( IAppConstants.COMMA );
                    String mappingId = String.valueOf( groupsPojo.getGroupId() );
                    String createdBy = groupsPojo.getCreatedBy();
                    returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                  IAppConstants.DASH,
                                                                  CrmActionEnum.GENERATED.getActionCode(),
                                                                  CRMRequestType.MASTER.getRequestCode(),
                                                                  inMasterdataDto.getClientIPAddress(),
                                                                  inMasterdataDto.getServerIPAddress() );
                    if ( returnValue )
                    {
                        LOGGER.info( "Successfully insert audit log for create parameter group" );
                    }
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Create Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Getting Error In Create Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( returnValue )
            {
                HibernateUtil.evictAll( GroupsPojo.class.getName(), "accessGroups", null );
            }
        }
        inMasterdataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterdataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterdataDto;
    }

    @Override
    public MasterDataDto updateCRMParameterGroup( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            GroupsPojo groupsPojo = inMasterdataDto.getGroupsPojo();
            GroupsPojo groupsPojoFmDb = (GroupsPojo) session.get( GroupsPojo.class, groupsPojo.getGroupId() );
            String newStatus = groupsPojo.getStatus();
            if ( StringUtils.isValidObj( groupsPojoFmDb ) )
            {
                session.flush();
                session.clear();
                groupsPojoFmDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                groupsPojoFmDb.setGroupDescription( groupsPojo.getGroupDescription() );
                groupsPojoFmDb.setLastModifiedBy( groupsPojo.getLastModifiedBy() );
                groupsPojoFmDb.getAccessGroups().clear();
                groupsPojoFmDb.getAccessGroups().addAll( groupsPojo.getAccessGroups() );
                for ( AccessGroupPojo accGroupPojo : groupsPojoFmDb.getAccessGroups() )
                {
                    accGroupPojo.setGroups( groupsPojoFmDb );
                }
                transaction = session.beginTransaction();
                //session.saveOrUpdate( groupsPojoFmDb );
                session.merge( groupsPojoFmDb );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                LOGGER.info( "Successfully Update Parameter Group" );
                //for auditLog Maintain
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                oldValues.append( "ParameterGroupName=" ).append( groupsPojoFmDb.getGroupName() )
                        .append( IAppConstants.COMMA ).append( "ParameterGroupType=" )
                        .append( groupsPojoFmDb.getGroupType() ).append( IAppConstants.COMMA )
                        .append( "ParameterGroupstatus=" ).append( groupsPojoFmDb.getStatus() )
                        .append( IAppConstants.COMMA );
                newValues.append( "ParameterGroupName=" ).append( groupsPojo.getGroupName() )
                        .append( IAppConstants.COMMA ).append( "ParameterGroupType=" )
                        .append( groupsPojo.getGroupType() ).append( IAppConstants.COMMA )
                        .append( "ParameterGroupstatus=" ).append( newStatus ).append( IAppConstants.COMMA );
                String mappingId = String.valueOf( groupsPojoFmDb.getGroupId() );
                String createdBy = groupsPojo.getLastModifiedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH,
                                                                      CrmActionEnum.UPDATED.getActionCode(),
                                                                      CRMRequestType.MASTER.getRequestCode(),
                                                                      inMasterdataDto.getClientIPAddress(),
                                                                      inMasterdataDto.getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log for update parameter group" );
                }
            }
            else
            {
                LOGGER.info( "Record Id Not Found in Database" );
                crmServiceCode = CRMServiceCode.CRM014;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Update Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Getting Error In Update Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( GroupsPojo.class.getName(), "accessGroups", null );
                getGroupById( inMasterdataDto );
            }
        }
        return inMasterdataDto;
    }

    @Override
    public MasterDataDto getGroupById( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            GroupsPojo groupsPojo = inMasterdataDto.getGroupsPojo();
            groupsPojo = (GroupsPojo) session.get( GroupsPojo.class, groupsPojo.getGroupId() );
            if ( StringUtils.isValidObj( groupsPojo ) )
            {
                inMasterdataDto.setGroupsPojo( groupsPojo );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error In Update Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterdataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterdataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterdataDto;
    }

    @Override
    public MasterDataDto searchGroup( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            LOGGER.info( "IN Search Group Method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            criteria = session.createCriteria( GroupsPojo.class );
            if ( !StringUtils.isEmpty( inMasterdataDto.getGroupsPojo().getGroupName() ) )
            {
                criteria.add( Restrictions.ilike( "groupName", inMasterdataDto.getGroupsPojo().getGroupName(),
                                                  MatchMode.ANYWHERE ) );
            }
            if ( !StringUtils.isEmpty( inMasterdataDto.getGroupsPojo().getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", inMasterdataDto.getGroupsPojo().getStatus() ) );
            }
            if ( inMasterdataDto.getGroupsPojo().getGroupId() > 0 )
            {
                criteria.add( Restrictions.eq( "groupId", inMasterdataDto.getGroupsPojo().getGroupId() ) );
            }
            criteria.add( Restrictions.eq( "groupType", inMasterdataDto.getGroupsPojo().getGroupType() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( GroupsPojo.class.getName() );
            List<GroupsPojo> groupsPojoList = criteria.list();
            inMasterdataDto.setGroupsPojoList( groupsPojoList );
            crmServiceCode = CRMServiceCode.CRM001;
            LOGGER.info( "Successfully Search Group" );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Update Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Getting Error In Update Parameter Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterdataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterdataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterdataDto;
    }

    //do work on the status
    @Override
    public MasterDataDto changeStatusGroup( MasterDataDto inMasterdataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        GroupsPojo groupsPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            groupsPojo = inMasterdataDto.getGroupsPojo();
            criteria = session.createCriteria( GroupsPojo.class );
            criteria.add( Restrictions.eq( "groupId", groupsPojo.getGroupId() ) );
            //            if ( CRMStatusCode.ACTIVE.getStatusCode().equals( groupsPojo.getStatus() ) )
            //            {
            ////                criteria.add( Restrictions.ne( "status", CRMStatusCode.DELETE.getStatusCode() ) );
            ////                crmServiceCode = CRMServiceCode.CRM051;
            //            }
            //            else 
            if ( CRMStatusCode.INACTIVE.getStatusCode().equals( groupsPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                crmServiceCode = CRMServiceCode.CRM052;
            }
            else if ( CRMStatusCode.DELETE.getStatusCode().equals( groupsPojo.getStatus() ) )
            {
                criteria.add( Restrictions.in( "status", new String[]
                { CRMStatusCode.ACTIVE.getStatusCode(), CRMStatusCode.INACTIVE.getStatusCode() } ) );
                crmServiceCode = CRMServiceCode.CRM053;
            }
            List<GroupsPojo> groupsPojos = criteria.list();
            if ( groupsPojos.size() > 0 )
            {
                GroupsPojo groupsPojoFmDb = groupsPojos.get( 0 );
                groupsPojoFmDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                groupsPojoFmDb.setLastModifiedBy( groupsPojo.getLastModifiedBy() );
                String oldStatus = groupsPojoFmDb.getStatus();
                groupsPojoFmDb.setStatus( groupsPojo.getStatus() );
                LOGGER.info( "status from client" + groupsPojo.getStatus() );
                session.update( groupsPojoFmDb );
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
                LOGGER.info( "Successfully Update Role Group" );
                //for auditLog Maintain
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                oldValues.append( "OldStatus=" ).append( oldStatus ).append( IAppConstants.COMMA );
                newValues.append( "NewStatus=" ).append( groupsPojo.getStatus() ).append( IAppConstants.COMMA );
                String mappingId = String.valueOf( groupsPojoFmDb.getGroupId() );
                String createdBy = groupsPojo.getLastModifiedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH,
                                                                      CrmActionEnum.UPDATED.getActionCode(),
                                                                      CRMRequestType.MASTER.getRequestCode(),
                                                                      inMasterdataDto.getClientIPAddress(),
                                                                      inMasterdataDto.getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log for create role group" );
                }
            }
            else
            {
                LOGGER.info( "Record Id Not Found in Database" );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In Update Role Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Getting Error In Update Role Group method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( GroupsPojo.class.getName(), "accessGroups", groupsPojo.getGroupId() );
            }
        }
        inMasterdataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterdataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterdataDto;
    }

    @Override
    public List<GroupsPojo> getActiveGroup( String inGroupType )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<GroupsPojo> groupPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( GroupsPojo.class );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.add( Restrictions.eq( "groupType", inGroupType ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( GroupsPojo.class.getName() );
            groupPojos = criteria.list();
            LOGGER.info( "Size " + groupPojos.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getPartnerPojoList method", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return groupPojos;
    }

    @Override
    public MasterDataDto createOption82( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "Inside MasterDataDaoImpl, createOption82" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CrmPartnerNetworkConfigPojo networkConfigPojoDB = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        boolean returnValue = false;
        try
        {
            if ( StringUtils.isValidObj( inMasterDataDto )
                    && CommonValidator.isValidCollection( inMasterDataDto.getCrmPartnerNetworkConfigPojos() )
                    && inMasterDataDto.getPartnerId() > 0 && !StringUtils.isEmpty( inMasterDataDto.getProduct() ) )
            {
                LOGGER.info( "inside inMasterDataDto: create:" + inMasterDataDto );
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                long partnerID = 0;
                String eventID = "";
                int processCount = 0;
                boolean commonProcessFlag = false;
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                Map<Byte, Long> oldConfigMap = new TreeMap<Byte, Long>();
                Map<Byte, Long> newConfigMap = new TreeMap<Byte, Long>();
                for ( CrmPartnerNetworkConfigPojo configPojo : inMasterDataDto.getCrmPartnerNetworkConfigPojos() )
                {
                    if ( configPojo.getPartnerDetailsId() > 0 )
                    {
                        if ( !commonProcessFlag )
                        {
                            commonProcessFlag = true;
                            partnerID = inMasterDataDto.getPartnerId();
                            if ( !StringUtils.equals( inMasterDataDto.getProduct(),
                                                      CRMDisplayListConstants.BROADBAND.getCode() ) )
                            {
                                eventID = CRMEvents.UPDATE_NASPORTID.getEventName();
                                oldValues.append( IAppConstants.COMMA ).append( "NasportIds-Count=" )
                                        .append( getActiveNasportIdsCount( session, configPojo.getPartnerDetailsId() ) )
                                        .append( IAppConstants.DASH );
                            }
                            else
                            {
                                if ( StringUtils.isEmpty( configPojo.getOltType() ) )
                                {
                                    crmServiceCode = CRMServiceCode.CRM068;
                                    break;
                                }
                                eventID = CRMEvents.UPDATE_OPTION82.getEventName();
                                oldValues.append( IAppConstants.COMMA ).append( "Option82=" );
                                newValues.append( IAppConstants.COMMA ).append( "Option82=" );
                            }
                        }
                        if ( configPojo.getRecordId() > 0 )
                        {
                            networkConfigPojoDB = (CrmPartnerNetworkConfigPojo) session
                                    .get( CrmPartnerNetworkConfigPojo.class, configPojo.getRecordId() );
                            if ( StringUtils.equals( inMasterDataDto.getProduct(),
                                                     CRMDisplayListConstants.BROADBAND.getCode() )
                                    && networkConfigPojoDB.getParameterId() > 0
                                    && networkConfigPojoDB.getParameterSequenceNo() > 0 )
                            {
                                oldConfigMap.put( networkConfigPojoDB.getParameterSequenceNo(),
                                                  networkConfigPojoDB.getParameterId() );
                            }
                            PropertyUtils.copyProperties( networkConfigPojoDB, configPojo );
                            networkConfigPojoDB.setLastModifiedTime( Calendar.getInstance().getTime() );
                            networkConfigPojoDB.setLastModifiedBy( inMasterDataDto.getUserID() );
                            LOGGER.info( "Updating Pojo : " + networkConfigPojoDB );
                            session.merge( networkConfigPojoDB );
                        }
                        else
                        {
                            configPojo.setCreatedTime( Calendar.getInstance().getTime() );
                            configPojo.setCreatedBy( inMasterDataDto.getUserID() );
                            LOGGER.info( "Creating Pojo : " + configPojo );
                            session.save( configPojo );
                        }
                        if ( StringUtils.equals( inMasterDataDto.getProduct(),
                                                 CRMDisplayListConstants.BROADBAND.getCode() )
                                && configPojo.getParameterId() > 0 && configPojo.getParameterSequenceNo() > 0 )
                        {
                            newConfigMap.put( configPojo.getParameterSequenceNo(), configPojo.getParameterId() );
                        }
                        processCount++;
                    }
                    else
                    {
                        LOGGER.info( "Partner Details ID : " + configPojo.getPartnerDetailsId() );
                    }
                }
                if ( processCount == inMasterDataDto.getCrmPartnerNetworkConfigPojos().size() )
                {
                    LOGGER.info( "Successfully Created option82/nasPort" );
                    crmServiceCode = CRMServiceCode.CRM001;
                    if ( StringUtils.equals( inMasterDataDto.getProduct(), CRMDisplayListConstants.BROADBAND.getCode() ) )
                    {
                        if ( oldConfigMap.size() > 0 )
                        {
                            prepareOption82String( oldValues, oldConfigMap );
                        }
                        if ( newConfigMap.size() > 0 )
                        {
                            prepareOption82String( newValues, newConfigMap );
                        }
                    }
                    else
                    {
                        newValues
                                .append( IAppConstants.COMMA )
                                .append( "NasportIds-Count=" )
                                .append( getActiveNasportIdsCount( session, inMasterDataDto
                                                 .getCrmPartnerNetworkConfigPojos().get( 0 ).getPartnerDetailsId() ) )
                                .append( IAppConstants.DASH );
                    }
                    String mappingId = String.valueOf( partnerID );
                    String createdBy = inMasterDataDto.getUserID();
                    returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                  IAppConstants.DASH,
                                                                  CrmActionEnum.GENERATED.getActionCode(),
                                                                  CRMRequestType.PARTNER_MANAGEMENT.getRequestCode(),
                                                                  inMasterDataDto.getClientIPAddress(),
                                                                  inMasterDataDto.getServerIPAddress() );
                    if ( returnValue )
                    {
                        LOGGER.info( "Successfully insert audit log for update option82 " );
                    }
                }
                else
                {
                    LOGGER.info( "Problem Creating option82/NasPort" );
                }
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "HibernateException in insert crm master table", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error in insert crm master table", ex );
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( returnValue )
            {
                HibernateUtil.evictAll( CrmPartnerNetworkConfigPojo.class.getName(), null, null );
            }
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    private long getActiveNasportIdsCount( Session inSession, long inPartnerDetailsId )
    {
        long count = 0;
        Criteria criteria = null;
        try
        {
            criteria = inSession.createCriteria( CrmPartnerNetworkConfigPojo.class );
            criteria.add( Restrictions.eq( "partnerDetailsId", inPartnerDetailsId ) );
            criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
            criteria.setProjection( Projections.rowCount() );
            count = (Long) criteria.uniqueResult();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "Hibernate Exception while fetching current Partner Network Config count", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while fetching current Partner Network Config count", ex );
        }
        return count;
    }

    private void prepareOption82String( StringBuilder inValue, Map<Byte, Long> inConfigMap )
    {
        if ( !StringUtils.isValidObj( OPTION_82_RAW_MAP ) )
        {
            prepareMapFromDB();
        }
        for ( Map.Entry<Byte, Long> entry : inConfigMap.entrySet() )
        {
            inValue.append( OPTION_82_RAW_MAP.get( entry.getValue() ) );
            inValue.append( IAppConstants.SLASH );
        }
        LOGGER.info( "Prepared Option 82 String : " + inValue.toString() );
    }

    private void prepareMapFromDB()
    {
        if ( !StringUtils.isValidObj( OPTION_82_RAW_MAP ) )
        {
            List<CrmParameterPojo> parameterList = new ArrayList<CrmParameterPojo>();
            MasterDataDto masterDataDto = new MasterDataDto();
            ProjectsPojo projectsPojo = new ProjectsPojo();
            projectsPojo.setProjectId( 0 );
            projectsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            projectsPojo.setProjectType( CRMParameter.INTERNAL.getParameter() );
            CrmParameterPojo crmParameterPojo = new CrmParameterPojo();
            crmParameterPojo.setParameterGroup( CRMParameter.OPTION82.getParameter() );
            crmParameterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            projectsPojo.getCrmParameterPojosSet().add( crmParameterPojo );
            masterDataDto.setProjectsPojo( projectsPojo );
            masterDataDto = getCRMParameterPojoListForPrmtrGroup( masterDataDto );
            parameterList = masterDataDto.getCrmParameterPojos();
            if ( !parameterList.isEmpty() )
            {
                OPTION_82_RAW_MAP = new HashMap<Long, String>();
                for ( CrmParameterPojo crmPojo : parameterList )
                {
                    OPTION_82_RAW_MAP.put( crmPojo.getParameterId(), crmPojo.getParameterName() );
                }
            }
        }
    }

    @Override
    public MasterDataDto updateMasterData( List<CrmMasterPojo> inMasterPojos, boolean inToCreate )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            for ( CrmMasterPojo crmMasterPojo : inMasterPojos )
            {
                if ( StringUtils.isBlank( crmMasterPojo.getCategoryValue() ) )
                {
                    crmMasterPojo.setCategoryValue( " " );
                }
                if ( StringUtils.equals( crmMasterPojo.getStatus(), CRMStatusCode.PENDING.getStatusCode() ) )
                {
                    crmMasterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    session.save( crmMasterPojo );
                }
                else
                {
                    session.update( crmMasterPojo );
                }
            }
            transaction.commit();
            crmServiceCode = CRMServiceCode.CRM001;
            LOGGER.info( "successfully inserted/updated master data " );
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in insert crm master table", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in insert crm master table", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmMasterPojo.class.getName(), null, null );
            }
        }
        masterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        masterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return masterDataDto;
    }

    @Override
    public MasterDataDto createAndUpdateCategoryValue( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = null;
        boolean successFlag = false;
        int count = 0;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            for ( CrmRcaReasonPojo crmRcaReasonPojo : inMasterDataDto.getCrmRcaReasonsList() )
            {
                if ( crmRcaReasonPojo.getCategoryId() > 0 )
                {
                    CrmRcaReasonPojo rcaReasonPojo = (CrmRcaReasonPojo) session.get( CrmRcaReasonPojo.class,
                                                                                     crmRcaReasonPojo.getCategoryId() );
                    rcaReasonPojo.setCategoryValue( crmRcaReasonPojo.getCategoryValue() );
                    rcaReasonPojo.setStatus( crmRcaReasonPojo.getStatus() );
                    rcaReasonPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    rcaReasonPojo.setLastModifiedBy( crmRcaReasonPojo.getLastModifiedBy() );
                    session.merge( rcaReasonPojo );
                    successFlag = true;
                }
                else
                {
                    Criteria criteria = session.createCriteria( CrmRcaReasonPojo.class );
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "category", crmRcaReasonPojo.getCategory() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "subCategory", crmRcaReasonPojo.getSubCategory() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubSubCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "subSubCategory", crmRcaReasonPojo.getSubSubCategory() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getCategoryValue() ) )
                    {
                        criteria.add( Restrictions.eq( "categoryValue", crmRcaReasonPojo.getCategoryValue() ) );
                    }
                    List<CrmRcaReasonPojo> crmRcaReasonPojos = criteria.list();
                    if ( crmRcaReasonPojos.size() > 0 )
                    {
                        if ( !successFlag )
                        {
                            successFlag = false;
                            if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubCategory() )
                                    && StringUtils.equals( "CashReceipt", crmRcaReasonPojo.getSubCategory() ) )
                            {
                                crmServiceCode = CRMServiceCode.CRM071;
                            }
                            else
                            {
                                crmServiceCode = CRMServiceCode.CRM308;
                            }
                        }
                    }
                    else
                    {
                        crmRcaReasonPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        Long id = (Long) session.save( crmRcaReasonPojo );
                        if ( id > 0 )
                        {
                            count++;
                            successFlag = true;
                            LOGGER.info( "Category Value Created Successfully. Generated Id :: " + id );
                        }
                    }
                }
            }
            LOGGER.info( "Count Value........" + count );
            if ( successFlag )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in save and update of Category value", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in save and update of Category value", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( successFlag )
            {
                HibernateUtil.evictAll( CrmRcaReasonPojo.class.getName(), null, null );
            }
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto searchAllCategoryValue( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            CrmRcaReasonPojo crmRcaReasonPojo = inMasterDataDto.getCrmRcaReason();
            LOGGER.info( "Category Name: " + crmRcaReasonPojo.getCategory() );
            LOGGER.info( "Sub Category Name: " + crmRcaReasonPojo.getSubCategory() );
            LOGGER.info( "Sub Sub Category Name: " + crmRcaReasonPojo.getSubSubCategory() );
            List<CrmRcaReasonPojo> crmRcaReasonPojos = null;
            if ( StringUtils.isNotBlank( inMasterDataDto.getCategoryValueRangeStart() )
                    && StringUtils.isNotBlank( inMasterDataDto.getCategoryValueRangeEnd() ) )
            {
                /*Query query = session.getNamedQuery( "CategoryValuesByRange" );
                query.setResultTransformer( Transformers.aliasToBean( CrmRcaReasonPojo.class ) );
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getCategory() ) )
                {
                    query.setString( "CATEGORY", StringUtils.trimToEmpty( crmRcaReasonPojo.getCategory() ) );
                }
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubCategory() ) )
                {
                    query.setString( "SUB_CATEGORY", StringUtils.trimToEmpty( crmRcaReasonPojo.getSubCategory() ) );
                }
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubSubCategory() ) )
                {
                    query.setString( "SUB_SUB_CATEGORY", StringUtils.trimToEmpty( crmRcaReasonPojo.getSubSubCategory() ) );
                }
                if ( StringUtils.isNotBlank( crmRcaReasonPojo.getValueAlias() ) )
                {
                    query.setString( "STATUS", StringUtils.trimToEmpty( crmRcaReasonPojo.getStatus() ) );
                }
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getValueAlias() ) )
                {
                    query.setString( "VALUE_ALIAS", StringUtils.trimToEmpty( crmRcaReasonPojo.getValueAlias() ) );
                }
                //                    query.setString( "CATEGORY_VALUE_PREFIX",
                //                                     StringUtils.trimToEmpty( inMasterDataDto.getCategoryValuePrefix() + "%" ) );
                query.setString( "RANGE_START", StringUtils.trimToEmpty( inMasterDataDto.getCategoryValuePrefix() )
                        + StringUtils.trimToEmpty( inMasterDataDto.getCategoryValueRangeStart() ) );
                query.setString( "RANGE_END", StringUtils.trimToEmpty( inMasterDataDto.getCategoryValuePrefix() )
                        + StringUtils.trimToEmpty( inMasterDataDto.getCategoryValueRangeEnd() ) );
                // query.setString( "CATEGORY_VALUE_REGEX", StringUtils.trimToEmpty( "[0-9]{1,6}" ) );
                crmRcaReasonPojos = query.list();*/
                criteria = session.createCriteria( CrmRcaReasonPojo.class );
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getCategory() ) )
                {
                    criteria.add( Restrictions.eq( "category", crmRcaReasonPojo.getCategory() ) );
                }
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubCategory() ) )
                {
                    criteria.add( Restrictions.eq( "subCategory", crmRcaReasonPojo.getSubCategory() ) );
                }
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubSubCategory() ) )
                {
                    criteria.add( Restrictions.eq( "subSubCategory", crmRcaReasonPojo.getSubSubCategory() ) );
                }
                if ( StringUtils.isNotBlank( crmRcaReasonPojo.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", crmRcaReasonPojo.getStatus() ) );
                }
                if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getValueAlias() ) )
                {
                    criteria.add( Restrictions.eq( "valueAlias", crmRcaReasonPojo.getValueAlias() ) );
                }
                String rangeStart = StringUtils.trimToEmpty( inMasterDataDto.getCategoryValuePrefix() )
                        + StringUtils.trimToEmpty( inMasterDataDto.getCategoryValueRangeStart() );
                String rangeEnd = StringUtils.trimToEmpty( inMasterDataDto.getCategoryValuePrefix() )
                        + StringUtils.trimToEmpty( inMasterDataDto.getCategoryValueRangeEnd() );
                criteria.add( Restrictions.between( "categoryValue", rangeStart, rangeEnd ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmRcaReasonPojo.class.getName() );
                crmRcaReasonPojos = criteria.list();
            }
            else
            {
                if ( crmRcaReasonPojo.getCategoryId() > 0 )
                {
                    CrmRcaReasonPojo rcaReasonPojo = CRMServiceUtils.getDBValues( CrmRcaReasonPojo.class,
                                                                                  crmRcaReasonPojo.getCategoryId() );
                    crmRcaReasonPojos = new ArrayList<CrmRcaReasonPojo>();
                    if ( StringUtils.isValidObj( rcaReasonPojo ) )
                    {
                        crmRcaReasonPojos.add( rcaReasonPojo );
                    }
                }
                else
                {
                    criteria = session.createCriteria( CrmRcaReasonPojo.class );
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "category", crmRcaReasonPojo.getCategory() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "subCategory", crmRcaReasonPojo.getSubCategory() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getSubSubCategory() ) )
                    {
                        criteria.add( Restrictions.eq( "subSubCategory", crmRcaReasonPojo.getSubSubCategory() ) );
                    }
                    if ( StringUtils.isNotBlank( crmRcaReasonPojo.getStatus() ) )
                    {
                        criteria.add( Restrictions.eq( "status", crmRcaReasonPojo.getStatus() ) );
                    }
                    if ( StringUtils.isNotBlank( crmRcaReasonPojo.getCategoryValue() ) )
                    {
                        criteria.add( Restrictions.eq( "categoryValue", crmRcaReasonPojo.getCategoryValue() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getValueAlias() ) )
                    {
                        criteria.add( Restrictions.eq( "valueAlias", crmRcaReasonPojo.getValueAlias() ) );
                    }
                    if ( StringUtils.isNotEmpty( crmRcaReasonPojo.getModificationAllowed() ) )
                    {
                        criteria.add( Restrictions.eq( "modificationAllowed", crmRcaReasonPojo.getModificationAllowed() ) );
                    }
                    if ( StringUtils.isNotEmpty( inMasterDataDto.getCategoryValuePrefix() ) )
                    {
                        criteria.add( Restrictions.like( "categoryValue", inMasterDataDto.getCategoryValuePrefix(),
                                                         MatchMode.START ) );
                    }
                    criteria.setCacheable( true );
                    criteria.setCacheRegion( CrmRcaReasonPojo.class.getName() );
                    crmRcaReasonPojos = criteria.list();
                }
            }
            if ( crmRcaReasonPojos.isEmpty() )
            {
                crmServiceCode = CRMServiceCode.CRM996;
            }
            else
            {
                inMasterDataDto.setCrmRcaReasonsList( crmRcaReasonPojos );
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in search All Category value", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
        inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inMasterDataDto;
    }

    //    public static void main( String[] args )
    //    {
    //        MasterDataDaoImpl masterDataDaoImpl = new MasterDataDaoImpl();
    //        MasterDataDto masterDataDto = new MasterDataDto();
    //        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
    //        crmRcaReasonPojo.setCategory( CRMDisplayListConstants.INA.getCode() );
    //        crmRcaReasonPojo.setSubCategory( "CashReceipt" );
    //        crmRcaReasonPojo.setSubSubCategory( "Unicon.BP.R" );
    //        masterDataDto.setCrmRcaReason( crmRcaReasonPojo );
    //        System.out.println( masterDataDaoImpl.searchAllCategoryValue( masterDataDto ).getCrmRcaReasonsList() );
    //    }
    @Override
    public MasterDataDto searchOption82( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "Inside MasterDataDaoImpl, searchOption82" );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<CrmPartnerNetworkConfigPojo> networkConfigPojos = null;
        try
        {
            if ( StringUtils.isValidObj( inMasterDataDto )
                    && StringUtils.isValidObj( inMasterDataDto.getCrmPartnerNetworkConfigPojo() )
                    && inMasterDataDto.getCrmPartnerNetworkConfigPojo().getPartnerDetailsId() > 0 )
            {
                LOGGER.info( "inside inMasterDataDto to search:" + inMasterDataDto );
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmPartnerNetworkConfigPojo.class );
                criteria.add( Restrictions.eq( "partnerDetailsId", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                        .getPartnerDetailsId() ) );
                if ( StringUtils.isNotBlank( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getNasPortId() ) )
                {
                    criteria.add( Restrictions.eq( "nasPortId", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                            .getNasPortId() ) );
                }
                if ( StringUtils.isNotBlank( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getOltType() ) )
                {
                    criteria.add( Restrictions.eq( "oltType", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                            .getOltType() ) );
                    criteria.addOrder( Order.asc( "parameterSequenceNo" ) );
                }
                else
                {
                    criteria.addOrder( Order.asc( "createdTime" ) );
                }
                if ( StringUtils.isNotBlank( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                            .getStatus() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPartnerNetworkConfigPojo.class.getName() );
                networkConfigPojos = criteria.list();
                if ( !networkConfigPojos.isEmpty() )
                {
                    inMasterDataDto.setCrmPartnerNetworkConfigPojos( networkConfigPojos );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                else
                {
                    inMasterDataDto.setCrmPartnerNetworkConfigPojos( new ArrayList<CrmPartnerNetworkConfigPojo>() );
                    crmServiceCode = CRMServiceCode.CRM996;
                }
                LOGGER.info( "Successfully Search Partner Network Configuration" );
            }
            else if ( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getRecordId() > 0 )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmPartnerNetworkConfigPojo.class );
                criteria.add( Restrictions.eq( "recordId", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                        .getRecordId() ) );
                if ( StringUtils.isNotBlank( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                            .getStatus() ) );
                }
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPartnerNetworkConfigPojo.class.getName() );
                networkConfigPojos = criteria.list();
                if ( CommonValidator.isValidCollection( networkConfigPojos ) )
                {
                    inMasterDataDto.setCrmPartnerNetworkConfigPojos( networkConfigPojos );
                    LOGGER.info( "Size of networkConfigPojos ::" + networkConfigPojos.size() );
                    crmServiceCode = CRMServiceCode.CRM001;
                }
                LOGGER.info( "Successfully Search Partner Network Configuration by record Id" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting Error in search Partner Network Configuration ", ex );
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
    public MasterDataDto getAllPlanDetails( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmPlanMasterPojo.class );
            if ( StringUtils.isValidObj( inMasterDataDto.getPlanMaster() ) )
            {
                CrmPlanMasterPojo planMaster = inMasterDataDto.getPlanMaster();
                if ( StringUtils.isNotBlank( planMaster.getPlanCategory() ) )
                {
                    criteria.add( Restrictions.eq( "planCategory", planMaster.getPlanCategory() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getStatus() ) )
                {
                    criteria.add( Restrictions.eq( "status", planMaster.getStatus() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getPrepaidYn() ) )
                {
                    criteria.add( Restrictions.eq( "prepaidYn", planMaster.getPrepaidYn() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getActvAllowedYn() ) )
                {
                    criteria.add( Restrictions.eq( "actvAllowedYn", planMaster.getActvAllowedYn() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getPlanType() ) )
                {
                    criteria.add( Restrictions.eq( "planType", planMaster.getPlanType() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getService() ) )
                {
                    criteria.add( Restrictions.eq( "service", planMaster.getService() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getPlanCode() ) )
                {
                    criteria.add( Restrictions.eq( "planCode", planMaster.getPlanCode() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getMigrAllowedYn() ) )
                {
                    criteria.add( Restrictions.eq( "migrAllowedYn", planMaster.getMigrAllowedYn() ) );
                }
                if ( StringUtils.isValidObj( planMaster.getPrimaryQuota() ) && planMaster.getPrimaryQuota() > 0 )
                {
                    criteria.add( Restrictions.eq( "primaryQuota", planMaster.getPrimaryQuota() ) );
                }
                if ( StringUtils.isNotBlank( planMaster.getPlanUsageType() ) )
                {
                    criteria.add( Restrictions.eq( "planUsageType", planMaster.getPlanUsageType() ) );
                }
                if ( StringUtils.equals( planMaster.getPlanCategory(), "ADDON" ) )
                {
                    criteria.addOrder( Order.asc( "rentExclTax" ) );
                }
                else
                    criteria.addOrder( Order.asc( "planName" ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmPlanMasterPojo.class.getName() );
            List<CrmPlanMasterPojo> crmPlanMasterPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmPlanMasterPojos ) )
            {
                LOGGER.info( "Size Of All Plan Master List:: " + crmPlanMasterPojos.size() );
                crmServiceCode = CRMServiceCode.CRM001;
                inMasterDataDto.setCrmPlanMasterPojos( crmPlanMasterPojos );
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM996;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in get All plan Details list", ex );
            crmServiceCode = CRMServiceCode.CRM999;
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
    public MasterDataDto getPartnerNetworkConfig( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        List<CrmPartnerNetworkConfigPojo> networkConfigPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inMasterDataDto.getCrmPartnerNetworkConfigPojo() )
                    && ( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getPartnerDetailsId() > 0 )
                    && ( !StringUtils.isEmpty( inMasterDataDto.getCrmPartnerNetworkConfigPojo().getOltType() ) ) )
            {
                criteria = session.createCriteria( CrmPartnerNetworkConfigPojo.class );
                criteria.add( Restrictions.eq( "partnerDetailsId", inMasterDataDto.getCrmPartnerNetworkConfigPojo()
                        .getPartnerDetailsId() ) );
                criteria.add( Restrictions
                        .eq( "oltType", inMasterDataDto.getCrmPartnerNetworkConfigPojo().getOltType() ) );
                inMasterDataDto.getCrmPartnerNetworkConfigPojo().getPartnerDetailsId();
                inMasterDataDto.getCrmPartnerNetworkConfigPojo().getOltType();
                List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmPartnerNetworkConfigPojo.class.getName() );
                crmPartnerNetworkConfigPojos = criteria.list();
                if ( crmPartnerNetworkConfigPojos.size() > 0 )
                {
                    LOGGER.info( "Size Of All Plan Master List:: " + crmPartnerNetworkConfigPojos.size() );
                    crmServiceCode = CRMServiceCode.CRM001;
                    inMasterDataDto.setCrmPartnerNetworkConfigPojos( crmPartnerNetworkConfigPojos );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM996;
                }
            }
            else if ( StringUtils.isValidObj( inMasterDataDto.getCrmPartnerNetworkConfigPojo() )
                    && CommonValidator.isValidCollection( inMasterDataDto.getMasterNames() ) )
            {
                networkConfigPojos = new ArrayList<CrmPartnerNetworkConfigPojo>();
                criteria = session.createCriteria( CrmPartnerNetworkConfigPojo.class );
                criteria.add( Restrictions.in( "masterName", inMasterDataDto.getMasterNames() ) );
                networkConfigPojos = criteria.list();
                if ( CommonValidator.isValidCollection( networkConfigPojos ) )
                {
                    LOGGER.info( "Size Of PartnerNetworkConfigPojos:: " + networkConfigPojos.size() );
                    crmServiceCode = CRMServiceCode.CRM001;
                    inMasterDataDto.setCrmPartnerNetworkConfigPojos( networkConfigPojos );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM996;
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM992;
                LOGGER.info( "Please Give correct values to MasterDataDTO && CrmNetworkConfigPojo's PartnerDetailsId,oltType" );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error ingetPartnerNetworkConfig in master operation", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
            inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            CRMServiceUtils.closeSession( session );
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto updateUnreadInbox( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "In MasterDataDaoImpl :: updateUnreadInbox" );
        if ( inMasterDataDto.getInboxId() > 0 )
        {
            CrmInboxPojo inbox = CRMServiceUtils.getDBValues( CrmInboxPojo.class, inMasterDataDto.getInboxId() );
            if ( StringUtils.isValidObj( inbox ) )
            {
                if ( StringUtils.isNotBlank( inbox.getMappingId() ) )
                {
                    inbox.setUnRead( false );
                    inbox = CRMServiceUtils.mergeDBValues( inbox );
                    inMasterDataDto.setInboxId( inbox.getInboxId() );
                }
            }
        }
        else if ( StringUtils.isValidObj( inMasterDataDto.getTicketHistory() )
                && StringUtils.isNotBlank( inMasterDataDto.getTicketHistory().getTicketId() ) )
        {
            CrmInboxPojo inboxObj = CRMServiceUtils.getDBValues( CrmInboxPojo.class, "mappingId", inMasterDataDto
                    .getTicketHistory().getTicketId() );
            if ( StringUtils.isValidObj( inboxObj ) )
            {
                inboxObj.setUnRead( true );
                inboxObj = CRMServiceUtils.mergeDBValues( inboxObj );
                inMasterDataDto.setInboxId( inboxObj.getInboxId() );
            }
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto postHolidayDetails( MasterDataDto inMasterDataDto )
    {
        LOGGER.info( "In MasterDataDaoImpl :: postHoliday " );
        SessionFactory sessionFactory = null;
        Session session = null;
        boolean successFlag = false;
        Transaction transaction = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM997;
        if ( !StringUtils.isValidObj( inMasterDataDto ) )
        {
            inMasterDataDto = new MasterDataDto();
            return inMasterDataDto;
        }
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( StringUtils.isValidObj( inMasterDataDto.getCrmHolidayPojo() ) )
            {
                if ( inMasterDataDto.getCrmHolidayPojo().getRecordId() > 0 )
                {
                    LOGGER.info( "Going to update for  recordId::" + inMasterDataDto.getCrmHolidayPojo().getRecordId() );
                    CrmHolidayDetails crmHolidayDetailsDb = (CrmHolidayDetails) session
                            .get( CrmHolidayDetails.class, inMasterDataDto.getCrmHolidayPojo().getRecordId() );
                    crmHolidayDetailsDb.setLastModifiedBy( inMasterDataDto.getUserID() );
                    crmHolidayDetailsDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                    crmHolidayDetailsDb.setStatus( inMasterDataDto.getCrmHolidayPojo().getStatus() );
                    session.merge( crmHolidayDetailsDb );
                    successFlag = true;
                }
                else
                {
                    inMasterDataDto.getCrmHolidayPojo().setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    inMasterDataDto.getCrmHolidayPojo().setCreatedBy( inMasterDataDto.getUserID() );
                    inMasterDataDto.getCrmHolidayPojo().setCreatedTime( Calendar.getInstance().getTime() );
                    Long id = (Long) session.save( inMasterDataDto.getCrmHolidayPojo() );
                    if ( id > 0 )
                    {
                        LOGGER.info( "Record successfully save generated record Id:" + id );
                        successFlag = true;
                    }
                }
                if ( successFlag )
                {
                    crmServiceCode = CRMServiceCode.CRM001;
                    transaction.commit();
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in postHoldayDetails in master operation", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error postHoldayDetails in master operation", ex );
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inMasterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
            inMasterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto getHoldayDetails( MasterDataDto masterDataDto )
    {
        LOGGER.info( "In MasterDataDaoImpl :: gettHoldayDetails " );
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmHolidayDetails.class );
            if ( StringUtils.isValidObj( masterDataDto.getCrmHolidayPojo() )
                    && StringUtils.isNotBlank( masterDataDto.getCrmHolidayPojo().getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", masterDataDto.getCrmHolidayPojo().getStatus() ) );
                // Below code will use by SLA processes, it'll help to fetch last 6 months holiday list only.
                Calendar cal = Calendar.getInstance();
                Date currentDate = cal.getTime();
                DateUtils.setDayEndTime( cal );
                cal.add( Calendar.MONTH, -6 );
                Date previousDate = cal.getTime();
                criteria.add( Restrictions.between( "holidayDate", previousDate, currentDate ) );
            }
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmHolidayDetails.class.getName() );
            List<CrmHolidayDetails> holidayList = criteria.list();
            if ( !holidayList.isEmpty() )
            {
                masterDataDto.setCrmHolidayPojos( holidayList );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error getHoldayDetails in master operation", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            masterDataDto.setStatusCode( crmServiceCode.getStatusCode() );
            masterDataDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return masterDataDto;
    }

    public static void main( String[] args )
    {
        System.out.println( Calendar.getInstance().getTime() );
    }

    @Override
    public MasterDataDto getAssociatSRWithBP( MasterDataDto inMasterDataDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inMasterDataDto ) )
            {
                LOGGER.info( "Product::" + inMasterDataDto.getProduct() );
                LOGGER.info( "PartnerId::" + inMasterDataDto.getPartnerId() );
                if ( inMasterDataDto.getPartnerId() > 0 && StringUtils.isNotEmpty( inMasterDataDto.getProduct() ) )
                {
                    Query query = session.getNamedQuery( "ASSOCIATTE_SR_WITH_BP" );
                    query.setResultTransformer( Transformers.aliasToBean( CrmUserPojo.class ) );
                    query.setLong( "PARTNER_ID", inMasterDataDto.getPartnerId() );
                    query.setString( "PRODUCT", inMasterDataDto.getProduct() );
                    inMasterDataDto.setCrmUserPojos( query.list() );
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to retrieve AssociatSRWithBP data:", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return inMasterDataDto;
    }
}
