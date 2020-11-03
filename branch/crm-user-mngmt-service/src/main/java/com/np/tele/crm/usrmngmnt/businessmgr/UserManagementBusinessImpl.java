package com.np.tele.crm.usrmngmnt.businessmgr;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.soap.SOAPException;

import org.apache.log4j.Logger;

import com.np.tele.crm.config.dao.IConfigManagerDao;
import com.np.tele.crm.constants.CRMEvents;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.dto.CrmuserDetailsDto;
import com.np.tele.crm.ext.pojos.UserMasterPojo;
import com.np.tele.crm.locator.CRMServicesProxy;
import com.np.tele.crm.pojos.CrmUserAssociationPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.usrmngmnt.dao.IUserManagementDao;
import com.np.tele.crm.utils.CRMServiceUtils;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.EncryptionUtil;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class UserManagementBusinessImpl
    implements IUserManagementBusiness
{
    private static final Logger LOGGER            = Logger.getLogger( UserManagementBusinessImpl.class );
    private IUserManagementDao  userManagementDao = null;
    private IConfigManagerDao   configManagerDao  = null;

    public IConfigManagerDao getConfigManagerDao()
    {
        return configManagerDao;
    }

    public void setConfigManagerDao( IConfigManagerDao inConfigManagerDao )
    {
        configManagerDao = inConfigManagerDao;
        CRMServiceUtils.configManagerDao = inConfigManagerDao;
    }

    public IUserManagementDao getUserManagementDao()
    {
        return userManagementDao;
    }

    public void setUserManagementDao( IUserManagementDao userManagementDao )
    {
        this.userManagementDao = userManagementDao;
    }

    /* @Override
     public CrmuserDetailsDto loginAuthentication( String inUserName, String inPassword )
     {
         CrmuserDetailsDto crmuserDetailsDto = null;
         try
         {
             crmuserDetailsDto = new CrmuserDetailsDto();
             CrmUserPojo crmuserDetailsPojo = getUserManagementDao()
                     .logingAuthentication( inUserName, EncryptionUtil.encrypt( inPassword ) );
             if ( null != crmuserDetailsPojo )
             {
                 crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                 crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM001.getStatusDesc() );
                 crmuserDetailsDto.setCrmUserDetailsPojo( crmuserDetailsPojo );
             }
             else
             {
                 crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM002.getStatusCode() );
                 crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM002.getStatusDesc() );
             }
         }
         catch ( Exception ex )
         {
             crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
             crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
             LOGGER.error( "Error in logingAtuhentication metod of UserManagementBusinessImpl class::" , ex );
         }
         return crmuserDetailsDto;
     }
    */
    @Override
    public CrmuserDetailsDto changePassword( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        String inUserName = null;
        String inPassword = null;
        String inNewPassword = null;
        if ( StringUtils.isValidObj( inCrmuserDetailsDto ) )
        {
            inUserName = inCrmuserDetailsDto.getUserID();
            inPassword = inCrmuserDetailsDto.getPassword();
            inNewPassword = inCrmuserDetailsDto.getNewPassword();
        }
        try
        {
            if ( StringUtils.isEmpty( inUserName ) || StringUtils.isEmpty( inPassword )
                    || StringUtils.isEmpty( inNewPassword ) )
            {
                inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM004.getStatusCode() );
                inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM004.getStatusDesc() );
                LOGGER.error( "New Password or Old Password or User Name Can Not be Blank " );
            }
            else
            {
                inCrmuserDetailsDto = getUserManagementDao().changePassword( inCrmuserDetailsDto );
                //added by rubina for mail send
                if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), inCrmuserDetailsDto.getStatusCode() ) )
                {
                    CRMServiceUtils.sendAlerts( CRMEvents.CHANGE_PASSWORD, CRMRequestType.USER, inCrmuserDetailsDto
                            .getCrmUserDetailsPojo().getUserId(), null );
                }
            }
        }
        catch ( Exception ex )
        {
            inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Error in changePassword method::", ex );
        }
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto userOperation( CrmuserDetailsDto inUserDetailsDto, final String inOperation )
    {
        UserMasterPojo masterPojo = null;
        try
        {
            ConfigDto configDto = CRMServicesProxy
                    .getInstance()
                    .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .configOperations( ServiceParameter.LIST.getParameter(), CRMParameter.USER_ACCESS.getParameter(),
                                       null );
            masterPojo = configDto.getUserMasterPojo();
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured while fething obj", e );
        }
        int lock_duration = 120;
        if ( StringUtils.isValidObj( masterPojo ) && masterPojo.getLock_duration() > 0 )
        {
            lock_duration = masterPojo.getLock_duration();
        }
        try
        {
            LOGGER.info( "in useroperation method" + inOperation );
            if ( null != inUserDetailsDto.getCrmUserDetailsPojo() )
            {
                CrmUserPojo crmUserPojo = inUserDetailsDto.getCrmUserDetailsPojo();
                if ( ServiceParameter.CREATE.getParameter().equals( inOperation ) )
                {
                    LOGGER.info( "Method called" + inOperation );
                    if ( StringUtils.isEmpty( crmUserPojo.getFirstName() )
                            //                            ||StringUtils.isEmpty( crmUserPojo.getEmpType() )
                            //                            || StringUtils.isEmpty( crmUserPojo.getAdCheck() )
                            || StringUtils.isEmpty( crmUserPojo.getLastName() )
                            || StringUtils.isEmpty( Long.toString( crmUserPojo.getMobileNo() ) )
                            || !StringUtils.isNumeric( Integer.toString( crmUserPojo.getPasswordExpiry() ) )
                            || !StringUtils.isNumeric( Integer.toString( crmUserPojo.getUserAccountExpiry() ) )
                            || !StringUtils.isValidEmailID( crmUserPojo.getEmailId() )
                            || Long.toString( crmUserPojo.getMobileNo() ).length() > 10
                            || Long.toString( crmUserPojo.getMobileNo() ).length() < 10 )
                    {
                        LOGGER.info( crmUserPojo );
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                        inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
                        LOGGER.info( "Data should be in correct format" );
                    }
                    else if ( !validUserAssociation( inUserDetailsDto.getCrmUserAssociationPojos(), false ) )
                    {
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                        inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
                    }
                    /*else if ( CRMParameter.NEXTRA.getParameter().equalsIgnoreCase( crmUserPojo.getEmpType() ) )
                    {
                        if ( StringUtils.isEmpty( crmUserPojo.getEmpCode() ) )
                        {
                            LOGGER.info( "Provide employee code" );
                            crmServiceCode = CRMServiceCode.CRM005;
                            process = false;
                            LOGGER.info( "Provide employee code" );
                        }
                        else
                        {
                            process = true;
                            LOGGER.info( "Employee code found" );
                        }
                    }
                    else if ( CRMParameter.PARTNER.getParameter().equalsIgnoreCase( crmUserPojo.getEmpType() ) )
                    {
                        if ( crmUserPojo.getPartner().getPartnerId() <= 0 )
                        {
                            crmServiceCode = CRMServiceCode.CRM005;
                            process = false;
                            LOGGER.info( "Provide partner name" );
                        }
                        else
                        {
                            process = true;
                            LOGGER.info( "partner name found" );
                        }
                    }*/
                    else
                    {
                        LOGGER.info( "Going to create" );
                        inUserDetailsDto = getUserManagementDao().createUser( inUserDetailsDto );
                        if ( CommonValidator.isValidCollection( inUserDetailsDto.getCrmUserAssociationPojos() ) )
                        {
                            getUserManagementDao().saveUserAssociation( inUserDetailsDto,
                                                                        inUserDetailsDto.getCrmUserDetailsPojo()
                                                                                .getUserId() );
                        }
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                 inUserDetailsDto.getStatusCode() ) )
                        {
                            CRMServiceUtils.sendAlerts( CRMEvents.CREATE_USER, CRMRequestType.USER, inUserDetailsDto
                                    .getCrmUserDetailsPojo().getUserId(), null );
                        }
                    }
                }
                else if ( ServiceParameter.MODIFY.getParameter().equals( inOperation ) )
                {
                    LOGGER.info( "Method called" + inOperation );
                    if ( StringUtils.isEmpty( Long.toString( crmUserPojo.getMobileNo() ) )
                            || !StringUtils.isNumeric( Integer.toString( crmUserPojo.getPasswordExpiry() ) )
                            || !StringUtils.isNumeric( Integer.toString( crmUserPojo.getUserAccountExpiry() ) )
                            || !StringUtils.isValidEmailID( crmUserPojo.getEmailId() )
                            || Long.toString( crmUserPojo.getMobileNo() ).length() > 10
                            || Long.toString( crmUserPojo.getMobileNo() ).length() < 10
                            || StringUtils.isEmpty( crmUserPojo.getLastModifiedBy() ) || crmUserPojo.getRecordId() <= 0
                            || !validUserAssociation( inUserDetailsDto.getCrmUserAssociationPojos(), true ) )
                    {
                        LOGGER.info( crmUserPojo );
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                        inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
                        LOGGER.info( "Data should be in correct format" );
                    }
                    else
                    {
                        LOGGER.info( "Going to update user" );
                        inUserDetailsDto = getUserManagementDao().updateUser( inUserDetailsDto );
                        if ( CommonValidator.isValidCollection( inUserDetailsDto.getCrmUserAssociationPojos() ) )
                        {
                            getUserManagementDao().saveUserAssociation( inUserDetailsDto, null );
                        }
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                 inUserDetailsDto.getStatusCode() ) )
                        {
                            CRMServiceUtils.sendAlerts( CRMEvents.UPDATE_USER, CRMRequestType.USER, inUserDetailsDto
                                    .getCrmUserDetailsPojo().getUserId(), null );
                        }
                    }
                }
                else if ( CRMParameter.CHANGE_STATUS.getParameter().equals( inOperation ) )
                {
                    if ( crmUserPojo.getStatus().equals( CRMStatusCode.ACTIVE.getStatusCode() )
                            || crmUserPojo.getStatus().equals( CRMStatusCode.LOCK.getStatusCode() )
                            || crmUserPojo.getStatus().equals( CRMStatusCode.EXPIRE.getStatusCode() )
                            || crmUserPojo.getStatus().equals( CRMStatusCode.DELETE.getStatusCode() )
                            || crmUserPojo.getStatus().equals( CRMStatusCode.NEW.getStatusCode() ) )
                    {
                        LOGGER.info( "Going to Change the Status of User" );
                        inUserDetailsDto = getUserManagementDao().updateUserStatus( inUserDetailsDto );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                 inUserDetailsDto.getStatusCode() ) )
                        {// Added by Rubina
                            CRMServiceUtils.sendAlerts( CRMEvents.CHANGE_STATUS, CRMRequestType.USER, inUserDetailsDto
                                    .getCrmUserDetailsPojo().getUserId(), null );
                        }
                    }
                    else
                    {
                        LOGGER.info( "Wrong Status Found for User " + crmUserPojo.getRecordId() );
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM019.getStatusCode() );
                        inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM019.getStatusDesc() );
                    }
                }
                else if ( CRMParameter.FORGET_PASSWORD.getParameter().equals( inOperation ) )
                {
                    if ( StringUtils.isEmpty( crmUserPojo.getEmailId() )
                            || StringUtils.isEmpty( crmUserPojo.getUserId() ) )
                    {
                        LOGGER.info( " User Id and Email Address Blank" );
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                        inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
                    }
                    else
                    {
                        LOGGER.info( "Going For Forget Password " );
                        inUserDetailsDto = getUserManagementDao().fogetPassword( inUserDetailsDto, lock_duration );
                        if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                 inUserDetailsDto.getStatusCode() ) )
                        {
                            if ( !CRMServiceUtils.sendAlerts( CRMEvents.RESET_PASSWORD, CRMRequestType.USER,
                                                              inUserDetailsDto.getCrmUserDetailsPojo().getUserId(),
                                                              null ) )
                            {
                                inUserDetailsDto.setStatusCode( CRMServiceCode.CRM111.getStatusCode() );
                                inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM111.getStatusDesc() );
                            }
                        }
                    }
                }
                else if ( CRMParameter.RESET_PASSWORD.getParameter().equals( inOperation ) )
                {
                    if ( crmUserPojo.getRecordId() > 0 )
                    {
                        LOGGER.info( "Going For Reset Password " );
                        inUserDetailsDto = getUserManagementDao().resetPassword( inUserDetailsDto );
                        if ( StringUtils.equals( inUserDetailsDto.getStatusDesc(),
                                                 CRMServiceCode.CRM001.getStatusDesc() ) )
                        {
                            // added by rubina for mail 
                            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(),
                                                     inUserDetailsDto.getStatusCode() ) )
                            {
                                CRMServiceUtils.sendAlerts( CRMEvents.RESET_PASSWORD, CRMRequestType.USER,
                                                            inUserDetailsDto.getCrmUserDetailsPojo().getUserId(), null );
                            }
                        }
                    }
                    else
                    {
                        LOGGER.info( " Record Id Blank" );
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                        inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
                    }
                }
                else if ( CRMParameter.VERIFY_ACCOUNT.getParameter().equals( inOperation ) )
                {
                    if ( StringUtils.isNotBlank( crmUserPojo.getUserToken() ) )
                    {
                        inUserDetailsDto = getUserManagementDao().verifyUser( crmUserPojo.getUserToken() );
                    }
                    else
                    {
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM103.getStatusCode() );
                    }
                }
                else if ( CRMParameter.ACTIVATION_LINK.getParameter().equals( inOperation ) )
                {
                    if ( StringUtils.isNotBlank( crmUserPojo.getUserId() ) )
                    {
                        boolean status = CRMServiceUtils.sendAlerts( CRMEvents.CREATE_USER, CRMRequestType.USER,
                                                                     inUserDetailsDto.getCrmUserDetailsPojo()
                                                                             .getUserId(), null );
                        if ( status )
                        {
                            inUserDetailsDto.setStatusCode( CRMServiceCode.CRM001.getStatusCode() );
                        }
                    }
                    else
                    {
                        inUserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                    }
                }
                else if ( ServiceParameter.LIST.getParameter().equals( inOperation ) )
                {
                    LOGGER.info( "call for All getUserList..." );
                    inUserDetailsDto = getUserManagementDao().getUserList();
                }
                else
                {
                    inUserDetailsDto.setStatusCode( CRMServiceCode.CRM007.getStatusCode() );
                    inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM007.getStatusDesc() );
                    LOGGER.info( ":::::::::::::::::::::::::::Operation is not correct :::::::::::::::::::::" );
                }
            }
            else
            {
                inUserDetailsDto.setStatusCode( CRMServiceCode.CRM013.getStatusCode() );
                inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM013.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            inUserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inUserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Error in User operation method::", ex );
        }
        //if crmstatus code not null
        return inUserDetailsDto;
    }

    private boolean validUserAssociation( List<CrmUserAssociationPojo> associationPojos, boolean tovalidate )
    {
        if ( CommonValidator.isValidCollection( associationPojos ) )
        {
            for ( CrmUserAssociationPojo crmUserAssociationPojo : associationPojos )
            {
                if ( StringUtils.isEmpty( crmUserAssociationPojo.getAssociationType() )
                        || StringUtils.isEmpty( crmUserAssociationPojo.getAssociatedValue() )
                        || StringUtils.isEmpty( crmUserAssociationPojo.getStatus() ) )
                {
                    LOGGER.info( crmUserAssociationPojo.toString() );
                    return false;
                }
                if ( tovalidate && StringUtils.isEmpty( crmUserAssociationPojo.getUserId() ) )
                {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public CrmuserDetailsDto loginAuthentication( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        CrmUserPojo crmUserPojo = null;
        UserMasterPojo masterPojo = null;
        String inUserName = null;
        String inPassword = null;
        if ( StringUtils.isValidObj( inCrmuserDetailsDto ) )
        {
            inUserName = inCrmuserDetailsDto.getUserID();
            inPassword = inCrmuserDetailsDto.getPassword();
        }
        try
        {
            ConfigDto configDto = CRMServicesProxy
                    .getInstance()
                    .getCRMConfigService( IGlobalConstants.REMOTE, IGlobalConstants.APP )
                    .configOperations( ServiceParameter.LIST.getParameter(), CRMParameter.USER_ACCESS.getParameter(),
                                       null );
            masterPojo = configDto.getUserMasterPojo();
        }
        catch ( SOAPException ex )
        {
            LOGGER.error( "Unable to retrive User Master Pojo:", ex );
        }
        int lock_duration = 120;
        int locked_value = 3;
        int expire_value = 10;
        if ( StringUtils.isValidObj( masterPojo ) && masterPojo.getLock_duration() > 0 )
        {
            lock_duration = masterPojo.getLock_duration();
        }
        if ( StringUtils.isValidObj( masterPojo ) && masterPojo.getLock_attempts() > 0 )
        {
            locked_value = masterPojo.getLock_attempts() - 1;
        }
        if ( StringUtils.isValidObj( masterPojo ) && masterPojo.getExpire_attempts() > 0 )
        {
            expire_value = masterPojo.getExpire_attempts() - 1;
        }
        boolean resetUnsuccessfulAttempt = false;
        boolean resetLastLoginDate = false;
        try
        {
            boolean process = true;
            CRMServiceCode serviceCode = null;
            CRMStatusCode crmStatusCode = null;
            crmuserDetailsDto = new CrmuserDetailsDto();
            crmUserPojo = getUserManagementDao().getUserByUserID( inUserName );
            if ( StringUtils.isValidObj( crmUserPojo ) )
            {
                String status = crmUserPojo.getStatus();
                if ( EncryptionUtil.encrypt( inPassword ).equals( crmUserPojo.getPassword() ) )
                {
                    if ( CRMStatusCode.DELETE.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM010;
                        process = false;
                        LOGGER.info( "User Deleted ::: " + inUserName );
                    }
                    else if ( CRMStatusCode.EXPIRE.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM009;
                        process = false;
                        LOGGER.info( "User Expired ::: " + inUserName );
                    }
                    else if ( CRMStatusCode.LOCK.getStatusCode().equals( status ) )
                    {
                        //if for time check if time is over than active
                        LOGGER.info( DateUtils.minuteDiff( crmUserPojo.getLockingTime(), new Date() ) + " and "
                                + lock_duration );
                        if ( DateUtils.minuteDiff( crmUserPojo.getLockingTime(), new Date() ) > lock_duration )
                        {
                            serviceCode = CRMServiceCode.CRM001;
                            crmStatusCode = CRMStatusCode.ACTIVE;
                            resetUnsuccessfulAttempt = true;
                            resetLastLoginDate = true;
                            crmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
                            process = true;
                        }
                        else
                        {
                            serviceCode = CRMServiceCode.CRM008;
                            process = false;
                            LOGGER.info( "User Loked ::: " + inUserName );
                        }
                    }
                    else if ( CRMStatusCode.INACTIVE.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM011;
                        process = false;
                        LOGGER.info( "User Deactivate ::: " + inUserName );//email verification link
                    }
                    else if ( CRMStatusCode.NEW.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM012;
                        process = false;
                        crmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
                        LOGGER.info( "New User found ::: " + inUserName );
                    }
                    else if ( CRMStatusCode.PENDING.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM108;
                        process = false;
                        LOGGER.info( "Pending User found ::: " + inUserName );
                    }
                    else if ( CRMStatusCode.ACTIVE.getStatusCode().equals( status ) )
                    {
                        //need to check user expiry and password expiry first user account expiry and second password expire if password expire then redirected to change password page
                        if ( DateUtils.daysDiff( crmUserPojo.getLastLoginTime(), new Date() ) > crmUserPojo
                                .getUserAccountExpiry() )
                        {
                            crmStatusCode = CRMStatusCode.EXPIRE;
                            LOGGER.info( "User going to expire " + crmUserPojo.getUserId() );
                            serviceCode = CRMServiceCode.CRM009;
                            /* //going to change status
                            if ( getUserManagementDao().changeUserStatus( crmUserPojo ) )
                            {
                            serviceCode = CRMServiceCode.CRM009;
                            process = false;
                            }
                            else
                            {
                            serviceCode = CRMServiceCode.CRM999;
                            process = false;
                            LOGGER.info( "Unable to update status for user :: " + crmUserPojo.getUserId() );
                            }*/
                        }
                        else if ( DateUtils.daysDiff( crmUserPojo.getLastLoginTime(), new Date() ) > crmUserPojo
                                .getPasswordExpiry() )
                        {
                            serviceCode = CRMServiceCode.CRM012;
                            process = false;
                            crmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
                            LOGGER.info( "User password has been expired ::: " + inUserName );
                        }
                        else
                        {
                            serviceCode = CRMServiceCode.CRM001;
                            process = true;
                            crmuserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
                            resetLastLoginDate = true;
                            resetUnsuccessfulAttempt = true;
                            CrmuserDetailsDto detailsDto = getUserManagementDao().getUserAssignAccess( inUserName );
                            if ( !detailsDto.getUserRolesPojos().isEmpty() )
                            {
                                LOGGER.info( "Size of Roles Pojo:: " + detailsDto.getUserRolesPojos().size()
                                        + " For User ::" + inUserName );
                                crmuserDetailsDto.setUserRolesPojos( detailsDto.getUserRolesPojos() );
                            }
                            else
                            {
                                LOGGER.info( "No Roles Found For User ::" + inUserName );
                            }
                        }
                        LOGGER.info( "Active User ::: " + inUserName );
                    }
                    else
                    {
                        serviceCode = CRMServiceCode.CRM999;
                        process = false;
                        LOGGER.info( "Other user status ::: " + inUserName + " :: status " + status );
                    }
                    if ( process )
                    {
                        if ( resetLastLoginDate )
                        {
                            crmUserPojo.setLastLoginTime( Calendar.getInstance().getTime() );
                        }
                        if ( null != crmStatusCode )
                        {
                            crmUserPojo.setStatus( crmStatusCode.getStatusCode() );
                        }
                        if ( resetUnsuccessfulAttempt )
                        {
                            Short attempts = 0;
                            crmUserPojo.setUnsuccessfullAttempts( attempts.intValue() );
                        }
                        if ( getUserManagementDao().changeUserStatus( crmUserPojo ) )
                        {
                            LOGGER.info( "User status changed successfully" );
                        }
                        else
                        {
                            LOGGER.info( "user status not changed successfully" );
                        }
                    }
                }
                else
                {
                    if ( CRMStatusCode.EXPIRE.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM009;
                        process = false;
                        LOGGER.info( " password not match and User Expired ::: " + inUserName );
                    }
                    else if ( CRMStatusCode.DELETE.getStatusCode().equals( status ) )
                    {
                        serviceCode = CRMServiceCode.CRM010;
                        process = false;
                        LOGGER.info( "password not match and User Deleted ::: " + inUserName );
                    }
                    else if ( CRMStatusCode.LOCK.getStatusCode().equals( status ) )
                    {
                        //if more than particular time than increase unsuccsfull attempt
                        if ( DateUtils.minuteDiff( crmUserPojo.getLockingTime(), new Date() ) > lock_duration )
                        {
                            process = true;
                        }
                        else
                        {
                            serviceCode = CRMServiceCode.CRM008;
                            process = false;
                            LOGGER.info( "User Locked ::: " + inUserName );
                        }
                    }
                    if ( process )
                    {
                        LOGGER.info( "Password not match, going to update unssuccessful attempt" );
                        boolean unsuccessfulAttemptUpdate = getUserManagementDao()
                                .increaseWrongPasswordAttempt( crmUserPojo, locked_value, expire_value );
                        if ( unsuccessfulAttemptUpdate )
                        {
                            LOGGER.info( "successfully update unssuccessful attempt for user id" + inUserName );
                            serviceCode = CRMServiceCode.CRM002;
                        }
                        else
                        {
                            serviceCode = CRMServiceCode.CRM999;
                            LOGGER.info( "User Not Found ::: " + inUserName );
                        }
                        //wrong password attempt coding
                    }
                }
            }
            else
            {
                serviceCode = CRMServiceCode.CRM002;
                LOGGER.info( "User Not Found ::: " + inUserName );
            }
            crmuserDetailsDto.setStatusCode( serviceCode.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        catch ( Exception ex )
        {
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Error in logingAtuhentication metod of UserManagementBusinessImpl class::", ex );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CrmUserPojo crmUserPojo = null;
        try
        {
            if ( StringUtils.isValidObj( inCrmuserDetailsDto.getCrmUserDetailsPojo() ) )
            {
                crmUserPojo = inCrmuserDetailsDto.getCrmUserDetailsPojo();
                if ( !StringUtils.isEmpty( crmUserPojo.getFirstName() )
                        || !StringUtils.isEmpty( crmUserPojo.getLastName() )
                        || !StringUtils.isEmpty( crmUserPojo.getEmailId() )
                        //                        || !StringUtils.isEmpty( crmUserPojo.getEmpType() )
                        || !StringUtils.isEmpty( crmUserPojo.getUserId() )
                        || !StringUtils.isEmpty( crmUserPojo.getFunctionalBin() )
                        || !StringUtils.isEmpty( crmUserPojo.getStatus() ) || crmUserPojo.getMobileNo() > 0 )
                {
                    LOGGER.info( crmUserPojo );
                    LOGGER.info( "field found in criteria" );
                    inCrmuserDetailsDto = getUserManagementDao().searchUser( inCrmuserDetailsDto );
                    LOGGER.info( "status code " + inCrmuserDetailsDto.getStatusCode() );
                }
                else
                {
                    LOGGER.info( "All fields are blank" );
                    inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                    inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
                }
            }
            else
            {
                LOGGER.info( " found crm details pojo null" );
                inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM005.getStatusCode() );
                inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM005.getStatusDesc() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in search user method", ex );
            inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
        }
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto userMapping( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        LOGGER.info( "IN User Mapping Method" );
        if ( !StringUtils.isValidObj( inCrmuserDetailsDto.getUserRolesPojos() )
                || inCrmuserDetailsDto.getUserRolesPojos().isEmpty() )
        {
            LOGGER.info( "User Roles Pojo  Find Null" );
            inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM058.getStatusCode() );
            inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM058.getStatusDesc() );
        }
        else
        {
            inCrmuserDetailsDto = getUserManagementDao().userMapping( inCrmuserDetailsDto );
        }
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getUserAssignAccess( String inUserID )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        try
        {
            if ( StringUtils.isEmpty( inUserID ) )
            {
                crmuserDetailsDto = new CrmuserDetailsDto();
                crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM101.getStatusCode() );
                crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM101.getStatusDesc() );
                LOGGER.info( "Please Provide User ID" );
            }
            else
            {
                crmuserDetailsDto = getUserManagementDao().getUserAssignAccess( inUserID );
                LOGGER.info( "User Id Found " + inUserID );
            }
        }
        catch ( Exception ex )
        {
            crmuserDetailsDto = new CrmuserDetailsDto();
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.info( "Error in User Assign Access Method", ex );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto userEscalationAndWorkflowMapping( CrmuserDetailsDto inCrmuserDetailsDto, String inOperation )
    {
        if ( StringUtils.equals( inOperation, ServiceParameter.CREATE.getParameter() ) )
        {
            if ( StringUtils.isValidObj( inCrmuserDetailsDto.getCrMappingPojosList() ) )
            {
                inCrmuserDetailsDto = getUserManagementDao().createUserEWMaping( inCrmuserDetailsDto );
            }
        }
        else if ( StringUtils.equals( inOperation, ServiceParameter.LIST.getParameter() ) )
        {
            if ( StringUtils.isValidObj( inCrmuserDetailsDto.getCrmUserMappingPojo() ) )
            {
                if ( StringUtils.isEmpty( inCrmuserDetailsDto.getCrmUserMappingPojo().getUserId() ) )
                {
                    inCrmuserDetailsDto.setStatusCode( CRMServiceCode.CRM101.getStatusCode() );
                    inCrmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM101.getStatusDesc() );
                }
                else
                {
                    inCrmuserDetailsDto = getUserManagementDao().searchUserEWMaping( inCrmuserDetailsDto );
                }
            }
        }
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getUsersByParameter( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        if ( StringUtils.isValidObj( inCrmuserDetailsDto.getUserRolesPojo() )
                && StringUtils.isNotBlank( inCrmuserDetailsDto.getUserRolesPojo().getParameterValue() )
                && StringUtils.isValidObj( inCrmuserDetailsDto.getUserRolesPojo().getCrmParameter() )
                && StringUtils.isNotBlank( inCrmuserDetailsDto.getUserRolesPojo().getCrmParameter().getParameterName() ) )
        {
            crmuserDetailsDto = getUserManagementDao().getUsersByParameter( inCrmuserDetailsDto );
        }
        else
        {
            crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM109.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( CRMServiceCode.CRM109.getStatusDesc() );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getCustomerAssociations( String inUserID )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        CRMServiceCode serviceCode = CRMServiceCode.CRM999;
        try
        {
            if ( StringUtils.isEmpty( inUserID ) )
            {
                LOGGER.info( "User Id is not present in request from client." );
                serviceCode = CRMServiceCode.CRM997;
            }
            else
            {
                LOGGER.info( "User Id Found " + inUserID );
                List<CrmUserAssociationPojo> associations = CRMServiceUtils
                        .getDBValueList( CrmUserAssociationPojo.class, "userId", inUserID );
                crmuserDetailsDto.setCrmUserAssociationPojos( associations );
                serviceCode = CRMServiceCode.CRM997;
            }
            //LOGGER.info("CustomerAssociations list size : " + crmuserDetailsDto.getCrmUserAssociationPojos().size());
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in getting customer Associations Method", ex );
        }
        finally
        {
            crmuserDetailsDto.setStatusCode( serviceCode.getStatusCode() );
            crmuserDetailsDto.setStatusDesc( serviceCode.getStatusDesc() );
        }
        return crmuserDetailsDto;
    }
}
