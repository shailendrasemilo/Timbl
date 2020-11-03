package com.np.tele.crm.usrmngmnt.bm;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;

import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.service.comparators.CrmUserPojoComparator;
import com.np.tele.crm.service.comparators.UserRolesPojoComparator;
import com.np.tele.crm.usrmngmnt.forms.AreaMappingPojo;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;
import com.np.tele.crm.service.client.*;

@AppMonitor
public class UserBMngrImpl
    implements IUserBMngr
{
    private static final Logger LOGGER = Logger.getLogger( UserBMngrImpl.class );

    public CRMUserManagement getCrmUserClient()
    {
        return crmUserClient;
    }

    public void setCrmUserClient( CRMUserManagement crmUserClient )
    {
        this.crmUserClient = crmUserClient;
    }

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }
    private CRMUserManagement crmUserClient;
    private CRMConfigService  crmConfigClient = null;

    @Override
    public CrmuserDetailsDto loginAuthentication( String inUserName, String inPassword )
    {
        CrmuserDetailsDto crmUserDetailsDto = new CrmuserDetailsDto();
        try
        {
            crmUserDetailsDto.setUserID( inUserName );
            crmUserDetailsDto.setPassword( inPassword );
            crmUserDetailsDto = getCrmUserClient().loginAuthentication( crmUserDetailsDto );
            LOGGER.info( "Respone Code in LoginAuthentication Method::::: " + crmUserDetailsDto.getStatusCode() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "In catch block error occured while fetching Service", ex );
        }
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto changePassword( String inUserName, String inPassword, String inNewPassword )
    {
        CrmuserDetailsDto crmUserDetailsDto = new CrmuserDetailsDto();
        try
        {
            crmUserDetailsDto.setUserID( inUserName );
            crmUserDetailsDto.setPassword( inPassword );
            crmUserDetailsDto.setNewPassword( inNewPassword );
            crmUserDetailsDto = getCrmUserClient().changePassword( crmUserDetailsDto );
            LOGGER.info( "change Password method  status code" + crmUserDetailsDto.getStatusCode() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured while fetching Service", ex );
        }
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto userOperation( LoginForm inLoginForm, String inMethod, String inCreatedBy )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            LOGGER.info( "userOperation for method called for method" + inMethod );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            LOGGER.info( "Setting value in CrmuserDetailsDto from LoginForm started. " );
            crmUserPojo.setFirstName( inLoginForm.getFirstName().trim() );
            crmUserPojo.setLastName( inLoginForm.getLastName().trim() );
            crmUserPojo.setEmailId( inLoginForm.getEmailId() );
            crmUserPojo.setMobileNo( inLoginForm.getMobileNo() );
            crmUserPojo.setPasswordExpiry( (short) inLoginForm.getPasswordExpiry() );
            crmUserPojo.setUserAccountExpiry( (short) inLoginForm.getUserAccountExpiry() );
            crmUserPojo.setCreatedBy( inCreatedBy );
            crmUserPojo.setFunctionalBin( StringUtils.arrayToStringWithComma( inLoginForm.getFunctionalBinArray() ) );
            crmUserPojo.setSrCode( inLoginForm.getSrCode() );
            //   crmUserPojo.setFunctionalBin( inLoginForm.getFunctionalBin() );
            /* if ( null != inLoginForm.getAdCheck() )
             {
                 crmUserPojo.setAdCheck( IAppConstants.Y );
             }
             else
             {
                 crmUserPojo.setAdCheck( IAppConstants.AD_STATUS_INACTIVE );
             }
             if ( inLoginForm.getEmpType().equals( IAppConstants.EMP_TYPE_NEXTRA ) )
             {
                 crmUserPojo.setEmpType( CRMParameter.NEXTRA.getParameter() );
                 crmUserPojo.setEmpCode( inLoginForm.getEmpCode() );
             }
             else
             {
                 crmUserPojo.setEmpType( CRMParameter.PARTNER.getParameter() );
                 PartnerPojo partnerPojo = new PartnerPojo();
                 LOGGER.info( "Partner Name" + inLoginForm.getPartnerName() );
                 partnerPojo.setPartnerId( Integer.parseInt( inLoginForm.getPartnerName() ) );
                 crmUserPojo.setPartner( partnerPojo );
             }*/
            if ( CommonValidator.isValidCollection( inLoginForm.getAssociationPojos() ) )
            {
                crmUserDetailsDto.getCrmUserAssociationPojos().addAll( inLoginForm.getAssociationPojos() );
            }
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto, inMethod );
            LOGGER.info( "Response status code::::: " + crmUserDetailsDto.getStatusCode() );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured while fetching Service", e );
        }
        return crmUserDetailsDto;
    }

    public CrmuserDetailsDto searchUser( LoginForm inUserForm )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            LOGGER.info( "Setting value in CrmuserDetailsDto from LoginForm started. " );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setFirstName( StringUtils.trimToEmpty( inUserForm.getFirstName() ) );
            crmUserPojo.setLastName( StringUtils.trimToEmpty( inUserForm.getLastName() ) );
            crmUserPojo.setEmailId( StringUtils.trimToEmpty( inUserForm.getEmailId() ) );
            crmUserPojo.setMobileNo( inUserForm.getMobileNo() );
            crmUserPojo.setUserId( StringUtils.trimToEmpty( inUserForm.getUserId() ) );
            crmUserPojo.setStatus( inUserForm.getSearchStatus() );
            crmUserPojo.setFunctionalBin( inUserForm.getFunctionalBin() );
            /*if ( inUserForm.getEmpType().equals( IAppConstants.EMP_TYPE_NEXTRA ) )
            {
                crmUserPojo.setEmpType( CRMParameter.NEXTRA.getParameter() );
                if ( StringUtils.isNotBlank( StringUtils.trimToNull( inUserForm.getEmpCode() ) ) )
                {
                    crmUserPojo.setEmpCode( StringUtils.trimToEmpty( inUserForm.getEmpCode() ) );
                }
            }
            else if ( inUserForm.getEmpType().equals( IAppConstants.EMP_TYPE_PARTNER ) )
            {
                crmUserPojo.setEmpType( CRMParameter.PARTNER.getParameter() );
                if ( StringUtils.isNotBlank( StringUtils.trimToNull( inUserForm.getPartnerName() ) )
                        && StringUtils.isNumeric( inUserForm.getPartnerName() ) )
                {
                    PartnerPojo partnerPojo = new PartnerPojo();
                    partnerPojo.setPartnerId( Integer.parseInt( inUserForm.getPartnerName().trim() ) );
                    crmUserPojo.setPartner( partnerPojo );
                }
            }*/
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getCrmUserClient().searchUser( crmUserDetailsDto );
            LOGGER.info( "Status code in  Impl class" + crmUserDetailsDto.getStatusCode() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while fetching Service in searchUser bussiness manager", ex );
        }
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto modifyUserOperation( LoginForm inLoginForm, String inMethodName, String inModifiedBy )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setRecordId( inLoginForm.getRecordID() );
            crmUserPojo.setEmailId( inLoginForm.getEmailId().trim() );
            crmUserPojo.setMobileNo( inLoginForm.getMobileNo() );
            crmUserPojo.setPasswordExpiry( (short) inLoginForm.getPasswordExpiry() );
            crmUserPojo.setUserAccountExpiry( (short) inLoginForm.getUserAccountExpiry() );
            crmUserPojo.setLastModifiedBy( inModifiedBy.trim() );
            crmUserPojo.setStatus( inLoginForm.getUserStatus() );
            crmUserPojo.setFunctionalBin( StringUtils.arrayToStringWithComma( inLoginForm.getFunctionalBinArray() ) );
            crmUserPojo.setSrCode( inLoginForm.getSrCode() );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            LOGGER.info( "Exist CRM user pojo:" + inLoginForm.getCrmUserPojo() );
            LOGGER.info( "New CRM user pojo:" + crmUserPojo );
            if ( StringUtils.isValidObj( inLoginForm.getCrmUserPojo() ) )
            {
                CrmUserPojoComparator comparator = new CrmUserPojoComparator();
                if ( comparator.compare( inLoginForm.getCrmUserPojo(), crmUserPojo ) == 0
                        && !isAdditionalDetailsChanged( inLoginForm ) )
                {
                    crmUserDetailsDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
                else
                {
                    LOGGER.info( "Both the Templates are different." );
                }
            }
            if ( StringUtils.isValidObj( inLoginForm.getAssociationPojos() ) )
            {
                crmUserDetailsDto.getCrmUserAssociationPojos().addAll( inLoginForm.getAssociationPojos() );
            }
            else
            {
                LOGGER.info( "CrmUserAssociationPjojs is not valid" );
            }
            if ( StringUtils.isBlank( crmUserDetailsDto.getStatusCode() ) )
            {
                crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto, inMethodName );
            }
            LOGGER.info( "Response Status Code" + crmUserDetailsDto.getStatusCode() );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured while fetching service in modify UserOperation bussiness", e );
        }
        return crmUserDetailsDto;
    }

    private boolean isAdditionalDetailsChanged( LoginForm inLoginForm )
    {
        LOGGER.info( "Inside UserBMngrImpl, -checkAdditionalChanges" );
        try
        {
            String[] currentProducts = StringUtils.tokenizeString( inLoginForm.getServiceNames(), ',' );
            if ( ( StringUtils.isValidObj( currentProducts ) ? currentProducts.length : 0 ) == ( StringUtils
                    .isValidObj( inLoginForm.getServiceNameArray() ) ? inLoginForm.getServiceNameArray().length : 0 ) )
            {
                if ( StringUtils.isValidObj( currentProducts ) )
                {
                    for ( String string : currentProducts )
                    {
                        if ( !Arrays.asList( inLoginForm.getServiceNameArray() ).contains( string ) )
                        {
                            return true;
                        }
                    }
                }
            }
            else
            {
                return true;
            }
            String[] currentPartners = StringUtils.tokenizeString( inLoginForm.getPartnerNames(), ',' );
            if ( ( StringUtils.isValidObj( currentPartners ) ? currentPartners.length : 0 ) == ( StringUtils
                    .isValidObj( inLoginForm.getPartnerNameArray() ) ? inLoginForm.getPartnerNameArray().length : 0 ) )
            {
                if ( StringUtils.isValidObj( currentPartners ) )
                {
                    for ( String string : currentPartners )
                    {
                        if ( !Arrays.asList( inLoginForm.getPartnerNameArray() ).contains( string ) )
                        {
                            return true;
                        }
                    }
                }
            }
            else
            {
                return true;
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while checking addtion checks in modify customer. ", ex );
        }
        return false;
    }

    @Override
    public CrmuserDetailsDto changeUserStatus( LoginForm inLoginForm, String inMethodName, String inModifiedBy )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            LOGGER.info( "Record Id for change status" + inLoginForm.getRecordID() + inLoginForm.getUserStatus() );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setRecordId( inLoginForm.getRecordID() );
            crmUserPojo.setStatus( inLoginForm.getUserStatus().trim() );
            crmUserPojo.setLastModifiedBy( inModifiedBy.trim() );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto, inMethodName );
            LOGGER.info( "changeUserStatus method  status code" + crmUserDetailsDto.getStatusCode() );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured while fetching service Change UserStatus", e );
        }
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto resetPassword( LoginForm inLoginForm, String inMethodName, String inModifiedBy )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            LOGGER.info( "Record Id for reset Password :: " + inLoginForm.getRecordID() + inLoginForm.getUserStatus() );
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setRecordId( inLoginForm.getRecordID() );
            crmUserPojo.setLastModifiedBy( inModifiedBy.trim() );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto, inMethodName );
            LOGGER.info( "reset Password method  status code" + crmUserDetailsDto.getStatusCode() );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured while reset Password", e );
        }
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto forgetPassword( String inEmailId, String inUserId, String inMethodName )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setEmailId( inEmailId.trim() );
            crmUserPojo.setUserId( inUserId.trim() );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto, inMethodName );
            LOGGER.info( "forgetPassword method  status code" + crmUserDetailsDto.getStatusCode() );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error occured In Forget Password Method :: ", ex );
        }
        return crmUserDetailsDto;
    }

    public CrmuserDetailsDto assignUserRole( LoginForm inLoginForm )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            List<UserRolesPojo> finalPojo = new ArrayList<UserRolesPojo>();
            if ( !inLoginForm.getUserRolesPojos().isEmpty() )
            {
                finalPojo.addAll( inLoginForm.getUserRolesPojos() );
            }
            if ( !inLoginForm.getUserRolesPojosForGroup().isEmpty() )
            {
                for ( UserRolesPojo pojo : inLoginForm.getUserRolesPojosForGroup() )
                {
                    if ( null != pojo.getGroups() && ( pojo.isEditable() || pojo.getRecordId() > 0 ) )
                    {
                        finalPojo.add( pojo );
                    }
                }
            }
            if ( finalPojo.isEmpty() )
            {
                crmUserDetailsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            }
            if ( !finalPojo.isEmpty() && StringUtils.isValidObj( inLoginForm.getOldUserRolesPojos() ) )
            {
                List<UserRolesPojo> existingPojos = inLoginForm.getOldUserRolesPojos();
                Iterator<UserRolesPojo> itFinalPojo = finalPojo.iterator();
                LOGGER.info( "Existing UserRolesPojo" + existingPojos );
                LOGGER.info( "To Update UserRolesPojo" + finalPojo );
                UserRolesPojo userRolesPojo = null;
                UserRolesPojoComparator comparator = new UserRolesPojoComparator();
                while ( itFinalPojo.hasNext() )
                {
                    userRolesPojo = itFinalPojo.next();
                    for ( UserRolesPojo tmpPojo : existingPojos )
                    {
                        if ( comparator.compare( tmpPojo, userRolesPojo ) == 0 )
                        {
                            itFinalPojo.remove();
                        }
                    }
                }
                if ( finalPojo.isEmpty() )
                {
                    crmUserDetailsDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isEmpty( crmUserDetailsDto.getStatusCode() ) )
            {
                LOGGER.info( "Roles to update" + finalPojo );
                crmUserDetailsDto.getUserRolesPojos().addAll( finalPojo );
                crmUserDetailsDto = getCrmUserClient().userMapping( crmUserDetailsDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error occured In Assign User RoleMethod :: ", ex );
        }
        return crmUserDetailsDto;
    }

    public CrmuserDetailsDto assignUserParameter( LoginForm inLoginForm )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            List<UserRolesPojo> finalPojo = new ArrayList<UserRolesPojo>();
            if ( !inLoginForm.getUserRolesPojos().isEmpty() )
            {
                finalPojo.addAll( inLoginForm.getUserRolesPojos() );
            }
            if ( !inLoginForm.getUserRolesPojosForGroup().isEmpty() )
            {
                for ( UserRolesPojo pojo : inLoginForm.getUserRolesPojosForGroup() )
                {
                    if ( null != pojo.getGroups() && ( pojo.isEditable() || pojo.getRecordId() > 0 ) )
                    {
                        finalPojo.add( pojo );
                    }
                }
            }
            if ( finalPojo.isEmpty() )
            {
                crmUserDetailsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            }
            if ( !finalPojo.isEmpty() && StringUtils.isValidObj( inLoginForm.getOldUserRolesPojos() ) )
            {
                List<UserRolesPojo> existingPojos = inLoginForm.getOldUserRolesPojos();
                Iterator<UserRolesPojo> itFinalPojo = finalPojo.iterator();
                LOGGER.info( "Existing UserRolesPojo" + existingPojos );
                LOGGER.info( "To Update UserRolesPojo" + finalPojo );
                UserRolesPojo userRolesPojo = null;
                UserRolesPojoComparator comparator = new UserRolesPojoComparator();
                while ( itFinalPojo.hasNext() )
                {
                    userRolesPojo = itFinalPojo.next();
                    for ( UserRolesPojo tmpPojo : existingPojos )
                    {
                        if ( comparator.compare( tmpPojo, userRolesPojo ) == 0 )
                        {
                            itFinalPojo.remove();
                        }
                    }
                }
                if ( finalPojo.isEmpty() )
                {
                    crmUserDetailsDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isEmpty( crmUserDetailsDto.getStatusCode() ) )
            {
                LOGGER.info( "Parameters to update" + finalPojo );
                crmUserDetailsDto.getUserRolesPojos().addAll( finalPojo );
                crmUserDetailsDto = getCrmUserClient().userMapping( crmUserDetailsDto );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error occured In Assign User RoleMethod :: ", ex );
        }
        return crmUserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getUserAssignAccess( String userId )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        try
        {
            crmuserDetailsDto = getCrmUserClient().getUserAssignAccess( userId );
            List<UserRolesPojo> userRolesPojos = crmuserDetailsDto.getUserRolesPojos();
            LOGGER.info( "In Impl class........." + userRolesPojos.size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "getting error while calling user assign access  in getUserAssignAccess ", ex );
        }
        return crmuserDetailsDto;
    }

    @Override
    public boolean resendActivationLink( String inUserId )
    {
        CrmuserDetailsDto crmUserDetailsDto = null;
        try
        {
            crmUserDetailsDto = new CrmuserDetailsDto();
            CrmUserPojo crmUserPojo = new CrmUserPojo();
            crmUserPojo.setUserId( inUserId );
            crmUserDetailsDto.setCrmUserDetailsPojo( crmUserPojo );
            crmUserDetailsDto = getCrmUserClient().userOperations( crmUserDetailsDto,
                                                                   CRMParameter.ACTIVATION_LINK.getParameter() );
            LOGGER.info( "reset Password method  status code" + crmUserDetailsDto.getStatusCode() );
            if ( StringUtils.equals( CRMServiceCode.CRM001.getStatusCode(), crmUserDetailsDto.getStatusCode() ) )
            {
                return true;
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured while reset Password", e );
        }
        return false;
    }

    @Override
    public ConfigDto getActivityLogs( LoginForm inUserForm )
    {
        ConfigDto configDto = new ConfigDto();
        CrmAuditLogPojo auditLogPojo = new CrmAuditLogPojo();
        try
        {
            LOGGER.info( "User Id ::" + inUserForm.getCrmUserPojo().getUserId() );
            auditLogPojo.setMappingId( inUserForm.getCrmUserPojo().getUserId() );
            auditLogPojo.setModule( CRMParameter.USER.getParameter() );
            configDto.setAuditLogPojo( auditLogPojo );
            configDto = getCrmConfigClient().auditLogOperation( ServiceParameter.VIEW.getParameter(), configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while get activity log of user", ex );
        }
        return configDto;
    }

    @Override
    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto )
    {
        try
        {
            inCrmuserDetailsDto = getCrmUserClient().searchUser( inCrmuserDetailsDto );;
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception while searching users ", ex );
        }
        return inCrmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto getCustomerAssociations( String inUserId )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        try
        {
            crmuserDetailsDto = getCrmUserClient().getCustomerAssociations( inUserId );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Getting error while going to fetch user association.", ex );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto assignUserArea( LoginForm inUserForm, long parameterId )
    {
        CrmuserDetailsDto crmuserDetailsDto = new CrmuserDetailsDto();
        LOGGER.info("assignUserArea method called"  );
        try
        {
            List<UserRolesPojo> finalPojo = new ArrayList<UserRolesPojo>();
            if ( !inUserForm.getAreaMappingPojos().isEmpty() )
            {
                for ( AreaMappingPojo pojo : inUserForm.getAreaMappingPojos() )
                {
                    UserRolesPojo userPojo = new UserRolesPojo();
                    CrmParameterPojo crmParmPojo = new CrmParameterPojo();
                    crmParmPojo.setParameterId( parameterId );
                    userPojo.setRecordId( pojo.getMappingId() );
                    userPojo.setCrmParameter( crmParmPojo );
                    userPojo.setParameterValue( pojo.getAreaId() + "" );
                    userPojo.setCreatedBy( pojo.getCreatedBy() );
                    userPojo.setLastModifiedBy(pojo.getLastModifiedBy());
                    userPojo.setStatus( pojo.getStatus() );
                    userPojo.setUserId( pojo.getUserId() );
                    finalPojo.add( userPojo );
                }
            }
            if ( finalPojo.isEmpty() )
            {
                crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM997.getStatusCode() );
            }
            if ( !finalPojo.isEmpty() && StringUtils.isValidObj( inUserForm.getOldUserRolesPojos() ) )
            {
                List<UserRolesPojo> existingPojos = inUserForm.getOldUserRolesPojos();
                Iterator<UserRolesPojo> itFinalPojo = finalPojo.iterator();
                LOGGER.info( "Existing UserRolesPojo" + existingPojos );
                LOGGER.info( "To Update UserRolesPojo" + finalPojo );
                UserRolesPojo userRolesPojo = null;
                UserRolesPojoComparator comparator = new UserRolesPojoComparator();
                while ( itFinalPojo.hasNext() )
                {
                    userRolesPojo = itFinalPojo.next();
                    for ( UserRolesPojo tmpPojo : existingPojos )
                    {
                        if ( comparator.compare( tmpPojo, userRolesPojo ) == 0 )
                        {
                            itFinalPojo.remove();
                        }
                    }
                }
                if ( finalPojo.isEmpty() )
                {
                    crmuserDetailsDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isEmpty( crmuserDetailsDto.getStatusCode() ) )
            {
                LOGGER.info( "Roles to update" + finalPojo );
                crmuserDetailsDto.getUserRolesPojos().addAll( finalPojo );
                crmuserDetailsDto = getCrmUserClient().userMapping( crmuserDetailsDto );
            }
            /*for(AreaMappingPojo pojo:inUserForm.getAreaMappingPojos())
            {
            UserRolesPojo userPojo = new UserRolesPojo();
            CrmParameterPojo crmParmPojo = new CrmParameterPojo();
            crmParmPojo.setParameterId( parameterId );
            userPojo.setCrmParameter( crmParmPojo );
            userPojo.setParameterValue( pojo.getAreaId() + "" );
            userPojo.setCreatedBy( pojo.getCreatedBy() );
            userPojo.setStatus( pojo.getStatus() );
            userPojo.setUserId( pojo.getUserId() );
            finalPojo.add( userPojo );
            }LOGGER.info("finalPojo.size::"+finalPojo.size());try
            {
            crmuserDetailsDto.getUserRolesPojos().addAll( finalPojo );
            crmuserDetailsDto = getCrmUserClient().userMapping( crmuserDetailsDto );
            LOGGER.info( "StatuCode::" + crmuserDetailsDto.getStatusCode() );
            }catch(
            Exception ex)
            {
            LOGGER.info( "Getting error assignUserArea.", ex );
            }*/
        }
        catch ( Exception ex )
        {
            // TODO Auto-generated catch block
            LOGGER.info( "Getting error while going to fetch user association.", ex );
        }
        return crmuserDetailsDto;
    }
}
