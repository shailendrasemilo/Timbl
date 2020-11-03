package com.np.tele.crm.usrmngmnt.dao;

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
import org.hibernate.criterion.Restrictions;

import com.np.tele.crm.constants.CRMOperationStages;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.CrmActionEnum;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.dto.CrmuserDetailsDto;
import com.np.tele.crm.pojos.AccessGroupPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmUserAssociationPojo;
import com.np.tele.crm.pojos.CrmUserMappingPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.GroupsPojo;
import com.np.tele.crm.pojos.UserRolesPojo;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.HibernateUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class UserManagementDaoImpl
    implements IUserManagementDao
{
    private static final Logger LOGGER = Logger.getLogger( UserManagementDaoImpl.class );

    @Override
    public CrmUserPojo getUserByUserID( String inUserName )
    {
        CrmUserPojo crmuserDetailsPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserPojo.class );
            criteria.add( Restrictions.eq( "userId", inUserName ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUserPojo.class.getName() );
            List<CrmUserPojo> crmuserDetailsPojoList = criteria.list();
            if ( crmuserDetailsPojoList.size() > 0 )
            {
                crmuserDetailsPojo = crmuserDetailsPojoList.get( 0 );
                LOGGER.info( "user found " + inUserName );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in logingAuthentication method of UserManagementDaoImpl class" + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmuserDetailsPojo;
    }

    //    public CrmuserDetailsDto doSomething( CrmuserDetailsDto crmuserDetailsDto )
    //    {
    //        CrmUserPojo crmUserDetailsPojo = null;
    //        SessionFactory sessionFactory = null;
    //        Session session = null;
    //        Criteria criteria = null;
    //        sessionFactory = HibernateUtil.getSessionFactory();
    //        session = sessionFactory.openSession();
    //        criteria = session.createCriteria( CrmUserPojo.class );
    //        if ( StringUtils.isNotEmpty( crmuserDetailsDto.getUserID() ) )
    //        {
    //            criteria.add( Restrictions.eq( "userId", crmuserDetailsDto.getUserID() ) );
    //        }
    //        List<CrmUserPojo> crmUserPojoList = criteria.list();
    //        if ( crmUserPojoList.size() > 0 )
    //        {
    //            crmUserDetailsPojo = crmUserPojoList.get( 0 );
    //            LOGGER.info( "user found" );
    //        }
    //        crmuserDetailsDto.setCrmUserDetailsPojo( crmUserDetailsPojo );
    //        return crmuserDetailsDto;
    //    }
    /*    @Override change method at 8:17 on 08-07-2013
        public CrmUserPojo logingAuthentication( String inUserName, String inUserPassword )
        {
            CrmUserPojo crmuserDetailsPojo = null;
            try
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmUserPojo.class );
                criteria.add( Restrictions.eq( "userId", inUserName ) );
                criteria.add( Restrictions.eq( "password", inUserPassword ) );
                List<CrmUserPojo> crmuserDetailsPojoList = criteria.list();
                if ( crmuserDetailsPojoList.size() > 0 )
                {
                    crmuserDetailsPojo = crmuserDetailsPojoList.get( 0 );
                    LOGGER.info( "user found " + inUserName );
                }
                //System.out.println(crmuserDetailsPojoList.size());
                //crmuserDetailsPojo = (CrmuserDetailsPojo) criteria.list().get( 0 );
                //System.out.println( "size:::" + crmuserDetailsPojo.getPassword() );
            }
            catch ( Exception ex )
            {
                LOGGER.error( "Getting Error in logingAuthentication method of UserManagementDaoImpl class" + ex );
            }
            finally
            {
                if ( null != session )
                {
                    session.flush();
                    session.close();
                }
            }
            return crmuserDetailsPojo;
        }
    */
    @Override
    public CrmuserDetailsDto changePassword( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        String inUserName = null;
        String inUserPassword = null;
        String inNewUserPassword = null;
        CrmUserPojo crmuserDetailsPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            if ( StringUtils.isValidObj( inCrmuserDetailsDto ) )
            {
                inUserName = inCrmuserDetailsDto.getUserID();
                inUserPassword = EncryptionUtil.encrypt( inCrmuserDetailsDto.getPassword() );
                inNewUserPassword = EncryptionUtil.encrypt( inCrmuserDetailsDto.getNewPassword() );
            }
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserPojo.class );
            criteria.add( Restrictions.eq( "userId", inUserName ) );
            criteria.add( Restrictions.eq( "password", inUserPassword ) );
            List<CrmUserPojo> crmuserDetailsPojoList = criteria.list();
            if ( crmuserDetailsPojoList.size() > 0 )
            {
                crmuserDetailsPojo = crmuserDetailsPojoList.get( 0 );
                if ( StringUtils.equals( inNewUserPassword, crmuserDetailsPojo.getPassword() )
                        || StringUtils.equals( inNewUserPassword, crmuserDetailsPojo.getPassword1() )
                        || StringUtils.equals( inNewUserPassword, crmuserDetailsPojo.getPassword2() )
                        || StringUtils.equals( inNewUserPassword, crmuserDetailsPojo.getPassword3() ) )
                {
                    crmServiceCode = CRMServiceCode.CRM003;
                }
                else
                {
                    crmuserDetailsPojo.setPassword3( crmuserDetailsPojo.getPassword2() );
                    crmuserDetailsPojo.setPassword2( crmuserDetailsPojo.getPassword1() );
                    crmuserDetailsPojo.setPassword1( crmuserDetailsPojo.getPassword() );
                    crmuserDetailsPojo.setPassword( inNewUserPassword );
                    crmuserDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmuserDetailsPojo.setLastLoginTime( new Date() );
                    transaction = session.beginTransaction();
                    session.update( crmuserDetailsPojo );
                    transaction.commit();
                    crmServiceCode = CRMServiceCode.CRM001;
                    //for auditLog Maintain
                    StringBuilder newValues = new StringBuilder();
                    StringBuilder oldValues = new StringBuilder();
                    oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                    newValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                    boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, inUserName,
                                                                          crmuserDetailsPojo.getCreatedBy(),
                                                                          IAppConstants.DASH,
                                                                          CrmActionEnum.PASSWORD.getActionCode(),
                                                                          CRMRequestType.USER.getRequestCode(),
                                                                          inCrmuserDetailsDto.getClientIPAddress(),
                                                                          inCrmuserDetailsDto.getServerIPAddress() );
                    if ( returnValue )
                    {
                        LOGGER.info( "Successfully insert audit log for change password " );
                    }
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM102;
                LOGGER.info( "User Not found in database:: " + inUserName + ":" + crmServiceCode.getStatusDesc() );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in logingAuthentication method", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Getting Error in logingAuthentication method of UserManagementDaoImpl class" + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmuserDetailsPojo.getRecordId() );
                inCrmuserDetailsDto.setCrmUserDetailsPojo( getUserByUserID( inUserName ) );
            }
        }
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto createUser( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CrmUserPojo crmUserPojoCap = inCrmuserDetailsDto.getCrmUserDetailsPojo();
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CrmUserPojo crmUserPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            boolean process = true;
            criteria = session.createCriteria( CrmUserPojo.class );
            criteria.add( Restrictions.eq( "firstName", crmUserPojoCap.getFirstName() ) );
            criteria.add( Restrictions.eq( "lastName", crmUserPojoCap.getLastName() ) );
            criteria.add( Restrictions.eq( "emailId", crmUserPojoCap.getEmailId() ) );
            criteria.add( Restrictions.eq( "mobileNo", crmUserPojoCap.getMobileNo() ) );
            List<CrmUserPojo> crmUserPojoList = criteria.list();
            LOGGER.info( "size of list" + crmUserPojoList.size() );
            if ( crmUserPojoList.size() > 0 )
            {
                inCrmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojoList.get( 0 ) );
                String userStatus = crmUserPojoList.get( 0 ).getStatus();
                LOGGER.info( "First name, last name, email id or mobile no already exist and user status :: "
                        + userStatus );
                if ( CRMStatusCode.DELETE.getStatusCode().equals( userStatus ) )
                {
                    LOGGER.info( "existing user deleted" );
                    process = true;
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM006;
                    process = false;
                }
            }
            if ( process )
            {
                LOGGER.info( "Going to create user" );
                crmUserPojo = inCrmuserDetailsDto.getCrmUserDetailsPojo();
                crmUserPojo.setStatus( CRMStatusCode.PENDING.getStatusCode() );//should be inactive for shoot mail 
                crmUserPojo
                        .setPassword( EncryptionUtil.encrypt( CRMUtils.generatePassword( crmUserPojo.getFirstName(),
                                                                                         crmUserPojo.getLastName(),
                                                                                         Long.toString( crmUserPojo
                                                                                                 .getMobileNo() ) ) ) );
                crmUserPojo.setUnsuccessfullAttempts( 0 );
                crmUserPojo.setCreatedTime( new Date() );
                transaction = session.beginTransaction();
                Long recordID = (Long) session.save( crmUserPojo );
                LOGGER.info( " generated ID" + recordID );
                transaction.commit();
                if ( recordID > 0 )
                {
                    LOGGER.info( "Going to search user for id" + recordID );
                    session.clear();
                    CrmUserPojo getCrmUserPojo = (CrmUserPojo) session.get( CrmUserPojo.class, recordID );
                    inCrmuserDetailsDto.setCrmUserDetailsPojo( getCrmUserPojo );
                    // LOGGER.info( "id ::::::::::::::::::::::::::::::" + getCrmUserPojo.getUserId() );
                    crmServiceCode = CRMServiceCode.CRM001;
                    //for auditLog Maintain
                    StringBuilder newValues = new StringBuilder();
                    StringBuilder oldValues = new StringBuilder();
                    oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                    newValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                    /*newValues.append( "First Name=" )
                            .append( inCrmuserDetailsDto.getCrmUserDetailsPojo().getFirstName() )
                            .append( ",Last Name=" ).append( inCrmuserDetailsDto.getCrmUserDetailsPojo().getLastName() )
                            .append( ",Status=" ).append( inCrmuserDetailsDto.getCrmUserDetailsPojo().getStatus() )
                            .append( "," + IAppConstants.FUNCTIONAL_BIN )
                            .append( inCrmuserDetailsDto.getCrmUserDetailsPojo().getFunctionalBin().substring( 1 ) );*/
                    String mappingId = getCrmUserPojo.getUserId();
                    String createdBy = inCrmuserDetailsDto.getCrmUserDetailsPojo().getCreatedBy();
                    boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                          IAppConstants.DASH,
                                                                          CrmActionEnum.GENERATED.getActionCode(),
                                                                          CRMRequestType.USER.getRequestCode(),
                                                                          inCrmuserDetailsDto.getClientIPAddress(),
                                                                          inCrmuserDetailsDto.getServerIPAddress() );
                    if ( returnValue )
                    {
                        LOGGER.info( "Successfully insert audit log " );
                    }
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM999;
                }
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in method userOperations", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.error( "Error in method userOperations" + ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( crmServiceCode == CRMServiceCode.CRM001 )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmUserPojo.getRecordId() );
            }
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public boolean increaseWrongPasswordAttempt( CrmUserPojo inUserPojo, int inLocked_value, int inExpire_value )
    {
        boolean result = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            int currentUnsuccessfulAttempt = inUserPojo.getUnsuccessfullAttempts();
            int newUnsuccessfulAttempt = currentUnsuccessfulAttempt + 1;
            LOGGER.info( "Current unssuccessful attempt =" + currentUnsuccessfulAttempt + " for user ="
                    + inUserPojo.getUserId() );
            inUserPojo.setUnsuccessfullAttempts( newUnsuccessfulAttempt );
            //change status for user  
            if ( newUnsuccessfulAttempt > inLocked_value && newUnsuccessfulAttempt < inExpire_value
                    && inUserPojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
            {
                inUserPojo.setStatus( CRMStatusCode.LOCK.getStatusCode() );
                inUserPojo.setLockingTime( new Date() );
            }
            else if ( inUserPojo.getUnsuccessfullAttempts() > inExpire_value
                    && inUserPojo.getStatus().equals( CRMStatusCode.LOCK.getStatusCode() ) )
            {
                //here time will be come z units of time after lock
                inUserPojo.setStatus( CRMStatusCode.EXPIRE.getStatusCode() );
            }
            else
            {
                LOGGER.info( "No change required" );
            }
            result = true;
            session.update( inUserPojo );
            transaction.commit();
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException unsuccessful attempt::", ex );
            result = false;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            result = false;
            LOGGER.info( "Usnable to update unsuccessful attempt for user " + inUserPojo.getUserId() + " ERROR ::: "
                    + ex );
        }
        finally
        {
            if ( result )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, inUserPojo.getRecordId() );
            }
            CRMServiceUtils.closeSession( session );
        }
        return result;
    }

    /* @Override
     public boolean changeUserStatus( CrmUserPojo inUserPojo )
     {
         boolean result = false;
         int locked_value = 3;
         int expire_value = 10;
         try
         {
             sessionFactory = HibernateUtil.getSessionFactory();
             session = sessionFactory.openSession();
             transaction = session.beginTransaction();
             if ( inUserPojo.getUnsuccessfullAttempts() > locked_value
                     && inUserPojo.getUnsuccessfullAttempts() < expire_value
                     && inUserPojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() ) )
             {
                 inUserPojo.setStatus( CRMStatusCode.L.getStatusCode() );
             }
             else if ( inUserPojo.getUnsuccessfullAttempts() > expire_value
                     && inUserPojo.getStatus().equals( CRMStatusCode.L.getStatusCode() ) )
             {
                 //here time will be come z units of time after lock
                 inUserPojo.setStatus( CRMStatusCode.E.getStatusCode() );
             }
             else
             {
                 //no change required
             }
             session.update( inUserPojo );
             transaction.commit();
             result = true;
         }
         catch ( Exception ex )
         {
             result = false;
             if ( null != transaction )
             {
                 transaction.rollback();
             }
             LOGGER.info( "Usnable to update status for user " + inUserPojo.getUserId() + " ERROR ::: " + ex );
         }
         finally
         {
             if ( null != session )
             {
                 session.flush();
                 session.close();
             }
         }
         return result;
     }*/
    @Override
    public boolean changeUserStatus( CrmUserPojo inUserPojo )
    {
        boolean result = false;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update( inUserPojo );
            transaction.commit();
            result = true;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException Unable to update status", ex );
            result = false;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            result = false;
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Unable to update status for user " + inUserPojo.getUserId() + " ERROR ::: " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( result )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, inUserPojo.getRecordId() );
            }
        }
        return result;
    }

    //    @Override
    //    public boolean updateLastLoginDate( CrmUserPojo inUserPojo )
    //    {
    //        boolean result = false;
    //        SessionFactory sessionFactory = null;
    //        Session session = null;
    //        Transaction transaction = null;
    //        try
    //        {
    //            sessionFactory = HibernateUtil.getSessionFactory();
    //            session = sessionFactory.openSession();
    //            transaction = session.beginTransaction();
    //            inUserPojo.setLastLoginTime( Calendar.getInstance().getTime() );
    //            session.update( inUserPojo );
    //            transaction.commit();
    //            result = true;
    //        }
    //        catch ( Exception ex )
    //        {
    //            result = false;
    //            CRMServiceUtils.rollback( transaction );
    //            LOGGER.info( "Unable to update last login date for user " + inUserPojo.getUserId() + " ERROR ::: " + ex );
    //        }
    //        finally
    //        {
    //            CRMServiceUtils.closeSession( session );
    //            if ( result )
    //            {
    //                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, inUserPojo.getRecordId() );
    //            }
    //        }
    //        return result;
    //    }
    public static void main( String[] args )
    {
        //        UserManagementDaoImpl userManagementDaoImpl = new UserManagementDaoImpl();
        /*//CrmUserPojo crmUserPojo = new CrmUserPojo();
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        crmuserDetailsDto.setUserID( "nawab" );
        
        
        crmuserDetailsDto=userManagementDaoImpl.doSomething( crmuserDetailsDto);
        
        CrmUserPojo crmUserPojo=crmuserDetailsDto.getCrmUserDetailsPojo();
        System.out.println(crmUserPojo.toString());*/
        CRMServiceUtils.getRandomPassword();
        try
        {
            /*//crmUserPojo.setUserId( "test.test3" );
            //crmUserPojo.setRecordId( 49 );
            // crmUserPojo.setUserId( "deepak.kumar23" );
            crmUserPojo.setFirstName( "rubina33" );
            crmUserPojo.setLastName( "Kumar1" );
            crmUserPojo.setEmailId( "deepak1@test.com" );
            crmUserPojo.setMobileNo( 999999906 );
            crmUserPojo.setEmpType( "N" );
            crmUserPojo.setEmpCode( "001" );
            crmUserPojo.setPasswordExpiry( 3 );
            crmUserPojo.setUserAccountExpiry( 5 );
            crmUserPojo.setCreatedBy( "uuuu" );
            crmUserPojo.setStatus( CRMStatusCode.N.getStatusCode() );//should be inactive for shoot mail 
            crmUserPojo.setPassword( EncryptionUtil.encrypt( "Deepak@123" ) );
            crmUserPojo.setUnsuccessfullAttempts( (short) 0 );
            crmUserPojo.setCreatedBy( "test" );
            crmUserPojo.setCreatedTime( new Date() );
            //PartnerPojo p = new PartnerPojo();
            //p.setPartnerId( 1 );
            //crmUserPojo.setPartner( p );
            inCrmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            userManagementDaoImpl.createUser( inCrmuserDetailsDto );*/
            /*crmUserPojo.setEmailId( "deepak" );
            crmUserPojo.setMobileNo( 99999999 );
            crmUserPojo.setUserAccountExpiry( 3 );
            crmUserPojo.setLastModifiedBy( "deepak" );
            crmUserPojo.setPasswordExpiry( 10 );
            crmUserPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
            crmUserPojo.setRecordId( 122 );
            inCrmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            userManagementDaoImpl.updateUser( inCrmuserDetailsDto );*/
            //            System.out.println( "size::::::::::::::::::::"
            //                    + userManagementDaoImpl.getUserAssignAccess( "rubinatest.test" ).getUserRolesPojos().size() );
        }
        catch ( Exception ex )
        {
            //            System.out.println( "Exception ::::: " + ex );
        }
    }

    @Override
    public CrmuserDetailsDto updateUser( CrmuserDetailsDto inCrmuserDetailsDton )
    {
        LOGGER.info( "Inside UserManagementDaoImpl, updateUser" );
        CrmUserPojo crmUserPojoCap = inCrmuserDetailsDton.getCrmUserDetailsPojo();
        crmUserPojoCap.setFirstName( StringUtils.upperCase( crmUserPojoCap.getFirstName() ) );
        crmUserPojoCap.setLastName( StringUtils.upperCase( crmUserPojoCap.getLastName() ) );
        inCrmuserDetailsDton.setCrmUserDetailsPojo( crmUserPojoCap );
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            LOGGER.info( "Find Record Id in method " + inCrmuserDetailsDton.getCrmUserDetailsPojo().getRecordId() );
            CrmUserPojo crmUserPojo = (CrmUserPojo) session.get( CrmUserPojo.class, inCrmuserDetailsDton
                    .getCrmUserDetailsPojo().getRecordId() );
            StringBuilder oldValues = new StringBuilder();
            StringBuilder newValues = new StringBuilder();
            if ( null != crmUserPojo )
            {
                if ( CRMServiceUtils.isValueModified( crmUserPojo, inCrmuserDetailsDton.getCrmUserDetailsPojo(),
                                                      "emailId" ) )
                {
                    oldValues.append( "Email Id=" ).append( crmUserPojo.getEmailId() ).append( IAppConstants.COMMA );
                    newValues.append( "Email Id=" ).append( inCrmuserDetailsDton.getCrmUserDetailsPojo().getEmailId() )
                            .append( IAppConstants.COMMA );
                }
                crmUserPojo.setEmailId( inCrmuserDetailsDton.getCrmUserDetailsPojo().getEmailId() );
                if ( CRMServiceUtils.isValueModified( crmUserPojo, inCrmuserDetailsDton.getCrmUserDetailsPojo(),
                                                      "mobileNo" ) )
                {
                    oldValues.append( "Mobile No=" ).append( crmUserPojo.getMobileNo() ).append( IAppConstants.COMMA );
                    newValues.append( "Mobile No=" )
                            .append( inCrmuserDetailsDton.getCrmUserDetailsPojo().getMobileNo() )
                            .append( IAppConstants.COMMA );
                }
                crmUserPojo.setMobileNo( inCrmuserDetailsDton.getCrmUserDetailsPojo().getMobileNo() );
                if ( CRMServiceUtils.isValueModified( crmUserPojo, inCrmuserDetailsDton.getCrmUserDetailsPojo(),
                                                      "userAccountExpiry" ) )
                {
                    oldValues.append( "Account Expiry=" ).append( crmUserPojo.getUserAccountExpiry() )
                            .append( IAppConstants.COMMA );
                    newValues.append( "Account Expiry=" )
                            .append( inCrmuserDetailsDton.getCrmUserDetailsPojo().getUserAccountExpiry() )
                            .append( IAppConstants.COMMA );
                }
                crmUserPojo.setUserAccountExpiry( inCrmuserDetailsDton.getCrmUserDetailsPojo().getUserAccountExpiry() );
                if ( CRMServiceUtils.isValueModified( crmUserPojo, inCrmuserDetailsDton.getCrmUserDetailsPojo(),
                                                      "passwordExpiry" ) )
                {
                    oldValues.append( "Password Expiry=" ).append( crmUserPojo.getPasswordExpiry() )
                            .append( IAppConstants.COMMA );
                    newValues.append( "Password Expiry=" )
                            .append( inCrmuserDetailsDton.getCrmUserDetailsPojo().getPasswordExpiry() )
                            .append( IAppConstants.COMMA );
                }
                crmUserPojo.setPasswordExpiry( inCrmuserDetailsDton.getCrmUserDetailsPojo().getPasswordExpiry() );
                if ( CRMServiceUtils.isValueModified( crmUserPojo, inCrmuserDetailsDton.getCrmUserDetailsPojo(),
                                                      "functionalBin" ) )
                {
                    if ( StringUtils.isNotEmpty( crmUserPojo.getFunctionalBin() ) )
                        oldValues.append( IAppConstants.FUNCTIONAL_BIN ).append( crmUserPojo.getFunctionalBin()
                                                                                         .substring( 1 ) );
                    if ( StringUtils.isNotEmpty( inCrmuserDetailsDton.getCrmUserDetailsPojo().getFunctionalBin() ) )
                        newValues.append( IAppConstants.FUNCTIONAL_BIN ).append( inCrmuserDetailsDton
                                                                                         .getCrmUserDetailsPojo()
                                                                                         .getFunctionalBin()
                                                                                         .substring( 1 ) );
                }
                crmUserPojo.setFunctionalBin( inCrmuserDetailsDton.getCrmUserDetailsPojo().getFunctionalBin() );
                crmUserPojo.setSrCode( inCrmuserDetailsDton.getCrmUserDetailsPojo().getSrCode() );
                crmUserPojo.setLastModifiedBy( inCrmuserDetailsDton.getCrmUserDetailsPojo().getLastModifiedBy() );
                crmUserPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                session.update( crmUserPojo );
                transaction.commit();
                LOGGER.info( "User updated successfully :: " + crmUserPojo.getUserId() );
                inCrmuserDetailsDton.setCrmUserDetailsPojo( crmUserPojo );
                crmServiceCode = CRMServiceCode.CRM001;
                //for auditLog Maintain
                String mappingId = crmUserPojo.getUserId();
                String createdBy = inCrmuserDetailsDton.getCrmUserDetailsPojo().getLastModifiedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH,
                                                                      CrmActionEnum.UPDATED.getActionCode(),
                                                                      CRMRequestType.USER.getRequestCode(),
                                                                      inCrmuserDetailsDton.getClientIPAddress(),
                                                                      inCrmuserDetailsDton.getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log " );
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
            LOGGER.error( "HibernateException in update user ::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Error in update user :: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmUserPojoCap.getRecordId() );
            }
        }
        inCrmuserDetailsDton.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDton.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDton;
    }

    @Override
    public CrmuserDetailsDto deleteUser( CrmuserDetailsDto inCrmuserDetailsDton )
    {
        CRMServiceCode crmServiceCode = null;
        CrmUserPojo crmUserPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            LOGGER.info( "in delete user method" );
            crmUserPojo = inCrmuserDetailsDton.getCrmUserDetailsPojo();
            if ( CRMStatusCode.ACTIVE.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                crmUserPojo.setStatus( CRMStatusCode.LOCK.getStatusCode() );
            }
            else if ( CRMStatusCode.LOCK.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                crmUserPojo.setStatus( CRMStatusCode.EXPIRE.getStatusCode() );
            }
            else if ( CRMStatusCode.EXPIRE.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                crmUserPojo.setStatus( CRMStatusCode.DELETE.getStatusCode() );
            }
            else if ( CRMStatusCode.INACTIVE.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                crmUserPojo.setStatus( CRMStatusCode.LOCK.getStatusCode() );
            }
            else if ( CRMStatusCode.NEW.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                crmUserPojo.setStatus( CRMStatusCode.LOCK.getStatusCode() );
            }
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update( crmUserPojo );
            transaction.commit();
            LOGGER.info( "User status changed successfully :: "
                    + inCrmuserDetailsDton.getCrmUserDetailsPojo().getUserId() );
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in update user ::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Error in update user :: " + ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmUserPojo.getRecordId() );
            }
        }
        inCrmuserDetailsDton.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDton.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDton;
    }

    @Override
    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            LOGGER.info( "in search user method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserPojo.class );
            LOGGER.info( "going to take user pojo" );
            CrmUserPojo crmUserPojo = inCrmuserDetailsDto.getCrmUserDetailsPojo();
            LOGGER.info( crmUserPojo );
            if ( !StringUtils.isEmpty( crmUserPojo.getFirstName() ) )
            {
                criteria.add( Restrictions.ilike( "firstName", crmUserPojo.getFirstName(), MatchMode.ANYWHERE ) );
            }
            if ( !StringUtils.isEmpty( crmUserPojo.getUserId() ) )
            {
                criteria.add( Restrictions.ilike( "userId", crmUserPojo.getUserId(), MatchMode.ANYWHERE ) );
            }
            if ( !StringUtils.isEmpty( crmUserPojo.getLastName() ) )
            {
                criteria.add( Restrictions.ilike( "lastName", crmUserPojo.getLastName(), MatchMode.ANYWHERE ) );
            }
            if ( !StringUtils.isEmpty( crmUserPojo.getEmailId() ) )
            {
                criteria.add( Restrictions.ilike( "emailId", crmUserPojo.getEmailId(), MatchMode.ANYWHERE ) );
            }
            if ( crmUserPojo.getMobileNo() > 0 )
            {
                criteria.add( Restrictions.eq( "mobileNo", crmUserPojo.getMobileNo() ) );
            }
            /*if ( !StringUtils.isEmpty( crmUserPojo.getEmpType() ) )
            {
                criteria.add( Restrictions.ilike( "empType", crmUserPojo.getEmpType(), MatchMode.ANYWHERE ) );
            }
            if ( !StringUtils.isEmpty( crmUserPojo.getEmpCode() ) )
            {
                criteria.add( Restrictions.ilike( "empCode", crmUserPojo.getEmpCode(), MatchMode.ANYWHERE ) );
            }*/
            if ( StringUtils.isNotBlank( crmUserPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", crmUserPojo.getStatus() ) );
            }
            if ( StringUtils.isNotBlank( crmUserPojo.getFunctionalBin() ) )
            {
                criteria.add( Restrictions.ilike( "functionalBin", IAppConstants.COMMA + crmUserPojo.getFunctionalBin()
                        + IAppConstants.COMMA, MatchMode.ANYWHERE ) );
            }
            /* if ( StringUtils.isValidObj( crmUserPojo.getPartner() ) )
             {
                 if ( crmUserPojo.getPartner().getPartnerId() > 0 )
                 {
                     criteria.createCriteria( "partner", "p" );
                     criteria.add( Restrictions.eq( "p.partnerId", crmUserPojo.getPartner().getPartnerId() ) );
                 }
             }*/
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUserPojo.class.getName() );
            List<CrmUserPojo> crmUserPojos = criteria.list();
            LOGGER.info( "size of list fo user pojo" + crmUserPojos.size() );
            if ( crmUserPojos.size() > 0 )
            {
                LOGGER.info( "in if block" );
                inCrmuserDetailsDto.setCrmUserDetailsPojoList( crmUserPojos );
                LOGGER.info( "Size of map" + crmUserPojo );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in searchUser method ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto updateUserStatus( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        LOGGER.info( "Inside UserManagementDaoImpl, updateUserStatus" );
        CRMServiceCode crmServiceCode = null;
        CrmUserPojo crmUserPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CrmUserPojo dbcrmUserPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            StringBuilder newValues = new StringBuilder();
            StringBuilder oldValues = new StringBuilder();
            crmUserPojo = inCrmuserDetailsDto.getCrmUserDetailsPojo();
            LOGGER.info( "Working for Record_ID : " + crmUserPojo.getRecordId() );
            dbcrmUserPojo = (CrmUserPojo) session.get( CrmUserPojo.class, crmUserPojo.getRecordId() );
            oldValues.append( CRMStatusCode.getStatus( dbcrmUserPojo.getStatus() ) ).append( IAppConstants.COMMA );
            criteria = session.createCriteria( CrmUserPojo.class );
            criteria.add( Restrictions.eq( "recordId", crmUserPojo.getRecordId() ) );
            if ( StringUtils.isNotBlank( crmUserPojo.getUserId() ) )
            {
                criteria.add( Restrictions.eq( "userId", crmUserPojo.getUserId() ) );
            }
            if ( CRMStatusCode.ACTIVE.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                criteria.add( Restrictions.ne( "status", CRMStatusCode.DELETE.getStatusCode() ) );
                crmServiceCode = CRMServiceCode.CRM015;
            }
            else if ( CRMStatusCode.LOCK.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                criteria.add( Restrictions.in( "status",
                                               new String[]
                                               { CRMStatusCode.ACTIVE.getStatusCode(),
                                                       CRMStatusCode.INACTIVE.getStatusCode(),
                                                       CRMStatusCode.NEW.getStatusCode(),
                                                       CRMStatusCode.PENDING.getStatusCode() } ) );
                crmServiceCode = CRMServiceCode.CRM016;
            }
            else if ( CRMStatusCode.EXPIRE.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", CRMStatusCode.LOCK.getStatusCode() ) );
                crmServiceCode = CRMServiceCode.CRM017;
            }
            else if ( CRMStatusCode.DELETE.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", CRMStatusCode.EXPIRE.getStatusCode() ) );
                crmServiceCode = CRMServiceCode.CRM018;
            }
            else if ( CRMStatusCode.NEW.getStatusCode().equals( crmUserPojo.getStatus() ) )
            {
                criteria.add( Restrictions.eq( "status", CRMStatusCode.PENDING.getStatusCode() ) );
                crmServiceCode = CRMServiceCode.CRM105;
            }
            List<CrmUserPojo> crmUserPojoList = criteria.list();
            if ( crmUserPojoList.size() > 0 )
            {
                CrmUserPojo crmUserPojoFrmDb = crmUserPojoList.get( 0 );
                crmUserPojoFrmDb.setStatus( crmUserPojo.getStatus() );
                crmUserPojoFrmDb.setLastModifiedBy( crmUserPojo.getLastModifiedBy() );
                crmUserPojoFrmDb.setLastModifiedTime( Calendar.getInstance().getTime() );
                if ( CRMStatusCode.ACTIVE.getStatusCode().equals( crmUserPojo.getStatus() ) )
                {
                    crmUserPojoFrmDb.setLastLoginTime( Calendar.getInstance().getTime() );
                }
                else if ( CRMStatusCode.LOCK.getStatusCode().equals( crmUserPojo.getStatus() ) )
                {
                    crmUserPojoFrmDb.setLockingTime( Calendar.getInstance().getTime() );
                }
                transaction = session.beginTransaction();
                session.update( crmUserPojoFrmDb );
                transaction.commit();
                inCrmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojoFrmDb );
                crmServiceCode = CRMServiceCode.CRM001;
                //for auditLog Maintain
                String mappingId = inCrmuserDetailsDto.getCrmUserDetailsPojo().getUserId();
                String createdBy = crmUserPojoFrmDb.getLastModifiedBy();
                newValues.append( CRMStatusCode.getStatus( crmUserPojo.getStatus() ) ).append( IAppConstants.COMMA );
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH,
                                                                      CrmActionEnum.UPDATED.getActionCode(),
                                                                      CRMRequestType.USER.getRequestCode(),
                                                                      inCrmuserDetailsDto.getClientIPAddress(),
                                                                      inCrmuserDetailsDto.getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log for change status" );
                }
            }
            else
            {
                LOGGER.info( "Record Not Found on Condition" );
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in update user ::", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Error in update user :: " + ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmUserPojo.getRecordId() );
            }
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto fogetPassword( CrmuserDetailsDto inCrmuserDetailsDto, int lock_duration )
    {
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CrmUserPojo crmUserPojoFrmDb = null;
        CrmUserPojo crmUserPojo = null;
        try
        {
            LOGGER.info( "In Forget Password Method " + lock_duration );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            crmUserPojo = inCrmuserDetailsDto.getCrmUserDetailsPojo();
            criteria = session.createCriteria( CrmUserPojo.class );
            criteria.add( Restrictions.eq( "userId", crmUserPojo.getUserId() ) );
            criteria.add( Restrictions.eq( "emailId", crmUserPojo.getEmailId() ) );
            criteria.add( Restrictions.in( "status",
                                           new String[]
                                           { CRMStatusCode.NEW.getStatusCode(), CRMStatusCode.ACTIVE.getStatusCode(),
                                                   CRMStatusCode.LOCK.getStatusCode() } ) );
            List<CrmUserPojo> crmUserPojoList = criteria.list();
            /*if ( StringUtils.isValidObj( session ) )
            {
                session.flush();
                session.close();
            }*/
            if ( crmUserPojoList.size() > 0 )
            {
                LOGGER.info( "List Size : " + crmUserPojoList.size() );
                crmUserPojoFrmDb = crmUserPojoList.get( 0 );
                if ( StringUtils.isValidObj( crmUserPojoFrmDb ) )
                {
                    if ( !StringUtils.equals( CRMStatusCode.LOCK.getStatusCode(), crmUserPojoFrmDb.getStatus() )
                            || !StringUtils.isValidObj( crmUserPojoFrmDb.getLockingTime() )
                            || DateUtils.minuteDiff( crmUserPojoFrmDb.getLockingTime(), new Date() ) > lock_duration )
                    {
                        crmUserPojoFrmDb.setLastModifiedBy( crmUserPojo.getLastModifiedBy() );
                        inCrmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojoFrmDb );
                        inCrmuserDetailsDto = resetPassword( inCrmuserDetailsDto );
                    }
                    else
                    {
                        LOGGER.info( "Account Lock" + CRMServiceCode.CRM106 );
                        crmServiceCode = CRMServiceCode.CRM106;
                    }
                }
            }
            else
            {
                LOGGER.info( "User ID and Email Not Found In Database" );
                crmServiceCode = CRMServiceCode.CRM107;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in Forget Password Method :: ", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmUserPojo.getRecordId() );
            }
        }
        if ( StringUtils.isValidObj( crmServiceCode ) )
        {
            inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        return inCrmuserDetailsDto;
    }

    /*    @Override
        public CrmuserDetailsDto userMapping( CrmuserDetailsDto inCrmuserDetailsDto )
        {
            CRMServiceCode crmServiceCode = null;
            try
            {
                LOGGER.info( "In User Mapping Method" );
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                transaction = session.beginTransaction();
                int i = 0;
                if ( !inCrmuserDetailsDto.getUserRolesPojos().isEmpty() )
                {
                    for ( UserRolesPojo rolesPojo : inCrmuserDetailsDto.getUserRolesPojos() )
                    {
                        rolesPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        session.save( rolesPojo );
                        if ( i % 50 == 0 )
                        {
                            session.flush();
                            session.clear();
                        }
                        i++;
                    }
                }
                else
                {
                    LOGGER.info( "User Roles Pojo List Find Null" );
                }
                transaction.commit();
                crmServiceCode = CRMServiceCode.CRM001;
            }
            catch ( Exception ex )
            {
                if ( StringUtils.isValidObj( transaction ) )
                {
                    transaction.rollback();
                }
                LOGGER.info( "Error in update user :: " + ex );
                crmServiceCode = CRMServiceCode.CRM999;
            }
            finally
            {
                if ( StringUtils.isValidObj( session ) )
                {
                    session.flush();
                    session.close();
                }
            }
            inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
            return inCrmuserDetailsDto;
        }*/
    @Override
    public CrmuserDetailsDto userMapping( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            LOGGER.info( "In User Mapping Method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            if ( !inCrmuserDetailsDto.getUserRolesPojos().isEmpty() )
            {
                LOGGER.info( "Size of UserRoles Pojo in userMapping method:::::::"
                        + inCrmuserDetailsDto.getUserRolesPojos().size() );
                for ( UserRolesPojo rolesPojo : inCrmuserDetailsDto.getUserRolesPojos() )
                {
                    Long recordId = rolesPojo.getRecordId();
                    UserRolesPojo userRolesPojo = null;
                    boolean process = true;
                    if ( recordId == 0 )
                    {
                        rolesPojo.setCreatedTime( Calendar.getInstance().getTime() );
                        process = false;
                    }
                    else if ( recordId > 0 && null != rolesPojo.getCrmRoles() )
                    {
                        userRolesPojo = (UserRolesPojo) session.get( UserRolesPojo.class, recordId );
                        userRolesPojo.setReadAccess( rolesPojo.getReadAccess() );
                        userRolesPojo.setCreateAccess( rolesPojo.getCreateAccess() );
                        userRolesPojo.setUpdateAccess( rolesPojo.getUpdateAccess() );
                        userRolesPojo.setDeleteAccess( rolesPojo.getDeleteAccess() );
                        userRolesPojo.setRecoverAccess( rolesPojo.isRecoverAccess() );
                        userRolesPojo.setStatus( rolesPojo.getStatus() );
                        userRolesPojo.setLastModifiedBy( rolesPojo.getLastModifiedBy() );
                    }
                    else if ( recordId > 0 && null != rolesPojo.getGroups() )
                    {
                        LOGGER.info( "IN roles group checking :::::::: record Id " + rolesPojo.getRecordId()
                                + " ::::: editable :::" + rolesPojo.isEditable() + " ::::: group type:::"
                                + rolesPojo.getGroups().getGroupType() );
                        userRolesPojo = (UserRolesPojo) session.get( UserRolesPojo.class, recordId );
                        userRolesPojo.setLastModifiedBy( rolesPojo.getLastModifiedBy() );
                        if ( !rolesPojo.isEditable() )
                        {
                            userRolesPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                        }
                        else
                        {
                            userRolesPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        }
                    }
                    else if ( recordId > 0 && null != rolesPojo.getCrmParameter() )
                    {
                        userRolesPojo = (UserRolesPojo) session.get( UserRolesPojo.class, recordId );
                        userRolesPojo.setParameterValue( rolesPojo.getParameterValue() );
                        userRolesPojo.setStatus( rolesPojo.getStatus() );
                        userRolesPojo.setLastModifiedBy( rolesPojo.getLastModifiedBy() );
                    }
                    if ( process )
                    {
                        userRolesPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                        session.update( userRolesPojo );
                    }
                    else
                    {
                        session.save( rolesPojo );
                    }
                }
            }
            else
            {
                LOGGER.info( "User Roles Pojo List Find Null" );
            }
            transaction.commit();
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In User Mapping Method:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            LOGGER.info( "Error In User Mapping Method:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( UserRolesPojo.class.getName(), null, null );
            }
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getUserAssignAccess( String inUserID )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            crmuserDetailsDto = new CrmuserDetailsDto();
            LOGGER.info( "in getUserAssignAccess user method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( UserRolesPojo.class );
            criteria.add( Restrictions.eq( "userId", inUserID ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( UserRolesPojo.class.getName() );
            List<UserRolesPojo> userRolesPojos = criteria.list();
            LOGGER.info( "Size of Assigend Access :: " + userRolesPojos.size() );
            crmuserDetailsDto.setUserRolesPojos( userRolesPojos );
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in searchUser method " + ex );
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmuserDetailsDto;
    }

    private CrmuserDetailsDto getUserAssignAccess( List<String> inUsers )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            crmuserDetailsDto = new CrmuserDetailsDto();
            LOGGER.info( "in getUserAssignAccess user method" );
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( UserRolesPojo.class );
            criteria.add( Restrictions.in( "userId", inUsers ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( UserRolesPojo.class.getName() );
            List<UserRolesPojo> userRolesPojos = criteria.list();
            LOGGER.info( "Size of Assigend Access :: " + userRolesPojos.size() );
            crmuserDetailsDto.setUserRolesPojos( userRolesPojos );
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error in searchUser method " + ex );
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto resetPassword( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CRMServiceCode crmServiceCode = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        Criteria criteria = null;
        CrmUserPojo crmUserPojo = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserPojo.class );
            crmUserPojo = (CrmUserPojo) session.get( CrmUserPojo.class, inCrmuserDetailsDto.getCrmUserDetailsPojo()
                    .getRecordId() );
            List<CrmUserPojo> crList = criteria.list();
            if ( crList.size() > 0 )
            {
                String password = CRMServiceUtils.getRandomPassword();
                crmUserPojo.setPassword3( crmUserPojo.getPassword2() );
                crmUserPojo.setPassword2( crmUserPojo.getPassword1() );
                crmUserPojo.setPassword1( crmUserPojo.getPassword() );
                crmUserPojo.setPassword( EncryptionUtil.encrypt( password ) );
                crmUserPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                crmUserPojo.setLastModifiedBy( inCrmuserDetailsDto.getCrmUserDetailsPojo().getLastModifiedBy() );
                crmUserPojo.setStatus( CRMStatusCode.NEW.getStatusCode() );
                transaction = session.beginTransaction();
                session.update( crmUserPojo );
                transaction.commit();
                inCrmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
                crmServiceCode = CRMServiceCode.CRM001;
                //for auditLog Maintain
                StringBuilder oldValues = new StringBuilder();
                StringBuilder newValues = new StringBuilder();
                oldValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                newValues.append( IAppConstants.DASH ).append( IAppConstants.COMMA );
                String mappingId = inCrmuserDetailsDto.getCrmUserDetailsPojo().getUserId();
                String createdBy = inCrmuserDetailsDto.getCrmUserDetailsPojo().getLastModifiedBy();
                boolean returnValue = CRMServiceUtils.createAuditLog( oldValues, newValues, mappingId, createdBy,
                                                                      IAppConstants.DASH,
                                                                      CrmActionEnum.PASSWORD.getActionCode(),
                                                                      CRMRequestType.USER.getRequestCode(),
                                                                      inCrmuserDetailsDto.getClientIPAddress(),
                                                                      inCrmuserDetailsDto.getServerIPAddress() );
                if ( returnValue )
                {
                    LOGGER.info( "Successfully insert audit log " );
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM002;
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException In resetPassword Method:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            CRMServiceUtils.rollback( transaction );
            crmServiceCode = CRMServiceCode.CRM999;
            LOGGER.error( "Getting Error while In resetPassword Method: ", ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserPojo.class.getName(), null, crmUserPojo.getRecordId() );
            }
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto verifyUser( String inUserToken )
    {
        CrmUserPojo crmUserDetailsPojo = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM001;
        CrmuserDetailsDto crmUserDetailsDto = new CrmuserDetailsDto();
        try
        {
            if ( StringUtils.isNotBlank( inUserToken ) )
            {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();
                criteria = session.createCriteria( CrmUserPojo.class );
                criteria.add( Restrictions.eq( "userToken", inUserToken ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmUserPojo.class.getName() );
                List<CrmUserPojo> crmuserDetailsPojoList = criteria.list();
                if ( crmuserDetailsPojoList.size() > 0 )
                {
                    crmUserDetailsPojo = crmuserDetailsPojoList.get( 0 );
                    LOGGER.info( "User verified for account activation: " + crmUserDetailsPojo.getUserId() );
                    crmUserDetailsDto.setCrmUserDetailsPojo( crmUserDetailsPojo );
                }
                else
                {
                    crmServiceCode = CRMServiceCode.CRM104;
                }
            }
            else
            {
                crmServiceCode = CRMServiceCode.CRM103;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in logingAuthentication method of UserManagementDaoImpl class", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        crmUserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmUserDetailsDto.setStatusDesc( crmServiceCode.getStatusCode() );
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto createUserEWMaping( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        boolean successFlag = false;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            for ( CrmUserMappingPojo crmUserMappingPojo : inCrmuserDetailsDto.getCrMappingPojosList() )
            {
                if ( crmUserMappingPojo.getUserMappingId() > 0 )
                {
                    crmUserMappingPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    session.update( crmUserMappingPojo );
                    successFlag = true;
                }
                else
                {
                    crmUserMappingPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    Long id = (Long) session.save( crmUserMappingPojo );
                    if ( id > 0 )
                    {
                        successFlag = true;
                        LOGGER.info( "Escalation Workflow User Mapping Created Successfully. Generated Id :: " + id );
                    }
                    else
                    {
                        successFlag = false;
                        crmServiceCode = CRMServiceCode.CRM999;
                        LOGGER.info( "Getting Error While Creating Escalation Workflow User Mapping" );
                    }
                }
            }
            if ( successFlag )
            {
                crmServiceCode = CRMServiceCode.CRM001;
                transaction.commit();
            }
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException in save and update of Escalation Workflow User Mapping:", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in save and update of Escalation Workflow User Mapping", ex );
            crmServiceCode = CRMServiceCode.CRM999;
            CRMServiceUtils.rollback( transaction );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserMappingPojo.class.getName(), null, null );
            }
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto searchUserEWMaping( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserMappingPojo.class );
            criteria.add( Restrictions.eq( "userId", inCrmuserDetailsDto.getCrmUserMappingPojo().getUserId() ) );
            criteria.add( Restrictions.eq( "mappingType", inCrmuserDetailsDto.getCrmUserMappingPojo().getMappingType() ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUserMappingPojo.class.getName() );
            List<CrmUserMappingPojo> crmUserMappingPojos = criteria.list();
            if ( !crmUserMappingPojos.isEmpty() )
            {
                LOGGER.info( "Size Of Mapped User List EW: " + crmUserMappingPojos.size() );
                inCrmuserDetailsDto.setCrMappingPojosList( crmUserMappingPojos );
            }
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in Search Escalation Workflow User Mapping", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getUsersByParameter( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        List<String> users = null;
        boolean toProceed = true;
        List<CrmUserPojo> userPojos = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            if ( StringUtils.isValidObj( inCrmuserDetailsDto.getCrmUserDetailsPojo() )
                    && StringUtils.isNotBlank( inCrmuserDetailsDto.getCrmUserDetailsPojo().getFunctionalBin() ) )
            {
                if ( StringUtils.equals( inCrmuserDetailsDto.getCrmUserDetailsPojo().getFunctionalBin(),
                                         CRMOperationStages.CLUSTER_HEAD.getCode() ) )
                {
                    toProceed = false;
                }
                if ( inCrmuserDetailsDto
                        .getCrmUserDetailsPojo()
                        .getFunctionalBin()
                        .matches( CRMOperationStages.AREA_MANAGER.getCode() + "|"
                                          + CRMOperationStages.CLUSTER_HEAD.getCode() ) )
                {
                    criteria = session.createCriteria( CrmRcaReasonPojo.class );
                    criteria.add( Restrictions.eq( "category", CRMRCAReason.CRM.getStatusCode() ) );
                    criteria.add( Restrictions.eq( "subCategory", CRMRCAReason.CRM_MASTER.getStatusCode() ) );
                    criteria.add( Restrictions.eq( "subSubCategory", CRMRCAReason.FUNCTIONAL_BIN.getStatusCode() ) );
                    criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                    criteria.add( Restrictions.eq( "valueAlias", inCrmuserDetailsDto.getCrmUserDetailsPojo()
                            .getFunctionalBin() ) );
                    List<CrmRcaReasonPojo> crmRcaReasonPojos = criteria.list();
                    if ( CommonValidator.isValidCollection( crmRcaReasonPojos ) )
                    {
                        inCrmuserDetailsDto.getCrmUserDetailsPojo().setFunctionalBin( IAppConstants.COMMA
                                                                                              + crmRcaReasonPojos
                                                                                                      .get( 0 )
                                                                                                      .getCategoryId()
                                                                                              + IAppConstants.COMMA );
                    }
                }
                criteria = session.createCriteria( CrmUserPojo.class );
                criteria.add( Restrictions.ilike( "functionalBin", inCrmuserDetailsDto.getCrmUserDetailsPojo()
                        .getFunctionalBin(), MatchMode.ANYWHERE ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( CrmUserPojo.class.getName() );
                userPojos = criteria.list();
                if ( CommonValidator.isValidCollection( userPojos ) )
                {
                    users = new ArrayList<String>();
                    for ( CrmUserPojo crmUserPojo : userPojos )
                    {
                        users.add( crmUserPojo.getUserId() );
                    }
                }
                else
                {
                    toProceed = false;
                    crmServiceCode = CRMServiceCode.CRM110;
                }
            }
            if ( toProceed )
            {
                criteria = session.createCriteria( UserRolesPojo.class );
                criteria.add( Restrictions.eq( "parameterValue", inCrmuserDetailsDto.getUserRolesPojo()
                        .getParameterValue() ) );
                criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
                if ( CommonValidator.isValidCollection( users ) )
                {
                    criteria.add( Restrictions.in( "userId", users ) );
                }
                criteria.createCriteria( "crmParameter", "CP" );
                criteria.add( Restrictions.eq( "CP.parameterName", inCrmuserDetailsDto.getUserRolesPojo()
                        .getCrmParameter().getParameterName() ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( UserRolesPojo.class.getName() );
                List<UserRolesPojo> userRolesPojos = criteria.list();
                List<UserRolesPojo> userRolesPojosGroups = getUsersByParamGroup( inCrmuserDetailsDto, session, users );
                Set<UserRolesPojo> userRoles = new HashSet<UserRolesPojo>();
                if ( CommonValidator.isValidCollection( userRolesPojos ) )
                {
                    userRoles.addAll( userRolesPojos );
                }
                if ( CommonValidator.isValidCollection( userRolesPojosGroups ) )
                {
                    userRoles.addAll( userRolesPojosGroups );
                }
                if ( CommonValidator.isValidCollection( userRoles ) )
                {
                    LOGGER.info( "Size Of Users " + userRoles.size() + " for "
                            + inCrmuserDetailsDto.getUserRolesPojo().getCrmParameter().getParameterName() + " : "
                            + inCrmuserDetailsDto.getUserRolesPojo().getParameterValue() );
                    crmuserDetailsDto.setUserRolesPojos( new ArrayList<UserRolesPojo>( userRoles ) );
                }
                crmServiceCode = CRMServiceCode.CRM001;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in Search Escalation Workflow User Mapping", ex );
            crmServiceCode = CRMServiceCode.CRM999;
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        crmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
        crmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        return crmuserDetailsDto;
    }

    private List<UserRolesPojo> getUsersByParamGroup( CrmuserDetailsDto inCrmuserDetailsDto,
                                                      Session session,
                                                      List<String> users )
    {
        List<UserRolesPojo> userRolesPojos = null;
        Criteria criteria;
        Set<GroupsPojo> groups = null;
        if ( CommonValidator.isValidCollection( users ) )
        {
            groups = new HashSet<GroupsPojo>();
            List<UserRolesPojo> userRoles = getUserAssignAccess( users ).getUserRolesPojos();
            for ( UserRolesPojo userRolesPojo : userRoles )
            {
                if ( StringUtils.isValidObj( userRolesPojo.getGroups() ) )
                {
                    groups.add( userRolesPojo.getGroups() );
                }
            }
        }
        criteria = session.createCriteria( AccessGroupPojo.class );
        criteria.add( Restrictions.eq( "parameterValue", inCrmuserDetailsDto.getUserRolesPojo().getParameterValue() ) );
        criteria.add( Restrictions.eq( "status", CRMStatusCode.ACTIVE.getStatusCode() ) );
        if ( CommonValidator.isValidCollection( groups ) )
        {
            criteria.add( Restrictions.in( "groups", groups ) );
        }
        criteria.createCriteria( "crmParameter", "CP" );
        criteria.add( Restrictions.eq( "CP.parameterName", inCrmuserDetailsDto.getUserRolesPojo().getCrmParameter()
                .getParameterName() ) );
        criteria.setCacheable( true );
        criteria.setCacheRegion( AccessGroupPojo.class.getName() );
        List<AccessGroupPojo> accessPojos = criteria.list();
        if ( CommonValidator.isValidCollection( accessPojos ) )
        {
            groups = new HashSet<GroupsPojo>();
            for ( AccessGroupPojo accessGroupPojo : accessPojos )
            {
                if ( StringUtils.isValidObj( accessGroupPojo.getGroups() ) )
                {
                    groups.add( accessGroupPojo.getGroups() );
                }
            }
            if ( CommonValidator.isValidCollection( groups ) )
            {
                criteria = session.createCriteria( UserRolesPojo.class );
                criteria.add( Restrictions.in( "groups", groups ) );
                criteria.setCacheable( true );
                criteria.setCacheRegion( UserRolesPojo.class.getName() );
                userRolesPojos = criteria.list();
            }
        }
        return userRolesPojos;
    }

    @Override
    public CrmuserDetailsDto getUserList()
    {
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        List<CrmUserPojo> crmUserPojos = null;
        CrmuserDetailsDto crmuserDetailsDto = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            crmuserDetailsDto = new CrmuserDetailsDto();
            criteria = session.createCriteria( CrmUserPojo.class );
            criteria.add( Restrictions.eq( "status", "A" ) );
            criteria.add( Restrictions.isNotNull( "srCode" ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUserPojo.class.getName() );
            crmUserPojos = criteria.list();
            if ( CommonValidator.isValidCollection( crmUserPojos ) )
            {
                crmuserDetailsDto.setCrmUserDetailsPojoList( crmUserPojos );
            }
            LOGGER.info( "Size of UserPojos :: " + crmUserPojos.size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Getting Error in getUserList method of UserManagementDaoImpl class" + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
        }
        return crmuserDetailsDto;
    }

    @Override
    public void saveUserAssociation( CrmuserDetailsDto inCrmuserDetailsDto, String inUser )
    {
        LOGGER.info( "In UserManagementDaoImpl ::::::::::::: saveUserAssociation" );
        CRMServiceCode crmServiceCode = CRMServiceCode.CRM999;
        SessionFactory sessionFactory = null;
        Session session = null;
        Transaction transaction = null;
        try
        {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            Set<CrmUserAssociationPojo> associationSet = new HashSet<CrmUserAssociationPojo>( inCrmuserDetailsDto.getCrmUserAssociationPojos() );
            if ( associationSet.size() < inCrmuserDetailsDto.getCrmUserAssociationPojos().size() )
            {
                crmServiceCode = CRMServiceCode.CRM990;
            }
            for ( CrmUserAssociationPojo crmUserAssociationPojo : inCrmuserDetailsDto.getCrmUserAssociationPojos() )
            {
                if ( StringUtils.isNotBlank( inUser ) )
                {
                    crmUserAssociationPojo.setUserId( inUser );
                }
                if ( crmUserAssociationPojo.getAssociationId() > 0 )
                {
                    crmUserAssociationPojo.setLastModifiedTime( Calendar.getInstance().getTime() );
                    session.update( crmUserAssociationPojo );
                }
                else
                {
                    crmUserAssociationPojo.setCreatedTime( Calendar.getInstance().getTime() );
                    session.save( crmUserAssociationPojo );
                }
            }
            transaction.commit();
            crmServiceCode = CRMServiceCode.CRM001;
        }
        catch ( HibernateException ex )
        {
            LOGGER.error( "HibernateException while saving the user association for new user creation:", ex );
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSessionOnException( session );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error while saving the user association for new user creation" );
        }
        finally
        {
            CRMServiceUtils.rollback( transaction );
            CRMServiceUtils.closeSession( session );
            if ( CRMServiceCode.CRM001 == crmServiceCode )
            {
                HibernateUtil.evictAll( CrmUserAssociationPojo.class.getName(), null, null );
            }
            inCrmuserDetailsDto.setStatusCode( crmServiceCode.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( crmServiceCode.getStatusDesc() );
        }
        LOGGER.info( " request status code " + crmServiceCode.getStatusCode() );
    }

    @Override
    public CrmuserDetailsDto getCustomerAssociations( String inUserId )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        SessionFactory sessionFactory = null;
        Session session = null;
        Criteria criteria = null;
        try
        {
            crmuserDetailsDto = new CrmuserDetailsDto();
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            criteria = session.createCriteria( CrmUserAssociationPojo.class );
            criteria.add( Restrictions.eq( "userId", inUserId ) );
            criteria.setCacheable( true );
            criteria.setCacheRegion( CrmUserAssociationPojo.class.getName() );
            List<CrmUserAssociationPojo> associationPojos = criteria.list();
            crmuserDetailsDto.setCrmUserAssociationPojos( associationPojos );
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error while fetching user associations method " + ex );
        }
        finally
        {
            CRMServiceUtils.closeSession( session );
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return crmuserDetailsDto;
    }
}
