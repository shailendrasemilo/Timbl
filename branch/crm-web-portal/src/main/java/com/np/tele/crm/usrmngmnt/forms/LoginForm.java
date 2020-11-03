package com.np.tele.crm.usrmngmnt.forms;

import java.util.Arrays;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.np.tele.crm.constants.CRMRegex;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IPropertiesConstant;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.CrmUserAssociationPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.UserRolesPojo;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;

public class LoginForm
    extends ActionForm
{
    private static final Logger          LOGGER = Logger.getLogger( LoginForm.class );
    private long                         recordID;
    private String                       userId;
    private String                       password;
    private String                       firstName;
    private String                       adCheck;
    private String                       lastName;
    private String                       emailId;
    private long                         mobileNo;
    //    private String                 empType;
    //    private String                 empCode;
    //    private String                 partnerName;
    private int                          passwordExpiry;
    private int                          userAccountExpiry;
    private String                       lastModifiedBy;
    private Calendar                     lastModifiedTime;
    private String                       createdBy;
    private Calendar                     createdTime;
    private int                          status;
    private String                       message;
    private String                       newPassword;
    private String                       confirmPassword;
    private String                       oldPassword;
    private Calendar                     lastLoginDate;
    private String                       searchCriteria;
    private String                       partnerId;
    private String                       adUserName;
    private List<CrmUserPojo>            searchUserList;
    private String                       userStatus;
    private CrmUserPojo                  crmUserPojo;
    private String                       password1;
    private String                       password2;
    private String                       password3;
    private int                          unsuccessfullAttempts;
    private Calendar                     lockingTime;
    private List<UserRolesPojo>          userRolesPojos;
    private UserRolesPojo                userRolesPojo;
    private List<CrmRolesPojo>           crmRoleList;
    private List<CrmRolesPojo>           crmRoles;
    private int                          rowCounter;
    private CrmParameterPojo             crmParameterPojo;
    private List<CrmParameterPojo>       crmParameterPojos;
    private List<UserRolesPojo>          userRolesPojosForGroup;
    private int                          changePasswordFlag;
    private List<ContentPojo>            statusList;
    private String                       searchStatus;
    private List<UserRolesPojo>          oldUserRolesPojos;
    private List<CrmRcaReasonPojo>       leadStagesList;
    private String                       functionalBin;
    private String[]                     functionalBinArray;
    private String                       srCode;
    private List<PartnerPojo>            partnerList;
    private List<ContentPojo>            productList;
    private String                       serviceNames;
    private String[]                     serviceNameArray;
    private String                       partnerNames;
    private String[]                     partnerNameArray;
    private List<CrmUserAssociationPojo> associationPojos;
    private CrmUserAssociationPojo       associationPojo;
    private List<String>                 userAssociatedServices;
    private List<String>                 userAssociatedPartners;
    private List<StatePojo>              stateDataList;
    private List<CityPojo>               cityList;
    private List<AreaPojo>               areaList;
    private List<AreaMappingPojo>        areaMappingPojos;
    private AreaMappingPojo              areaMappingPojo;
    private List<AreaMappingPojo>        oldAreaMappingPojos;
    private List<UserRolesPojo>          parameterList;

    public List<String> getUserAssociatedServices()
    {
        return userAssociatedServices;
    }

    public void setUserAssociatedServices( List<String> inUserAssociatedServices )
    {
        userAssociatedServices = inUserAssociatedServices;
    }

    public List<String> getUserAssociatedPartners()
    {
        return userAssociatedPartners;
    }

    public void setUserAssociatedPartners( List<String> inUserAssociatedPartners )
    {
        userAssociatedPartners = inUserAssociatedPartners;
    }

    public String getServiceNames()
    {
        return serviceNames;
    }

    public void setServiceNames( String inServiceNames )
    {
        serviceNames = inServiceNames;
    }

    public String getPartnerNames()
    {
        return partnerNames;
    }

    public void setPartnerNames( String inPartnerNames )
    {
        partnerNames = inPartnerNames;
    }

    public String getSearchStatus()
    {
        return searchStatus;
    }

    public void setSearchStatus( String searchStatus )
    {
        this.searchStatus = searchStatus;
    }

    public List<ContentPojo> getStatusList()
    {
        return statusList;
    }

    public void setStatusList( List<ContentPojo> statusList )
    {
        this.statusList = statusList;
    }

    public List<UserRolesPojo> getUserRolesPojosForGroup()
    {
        return userRolesPojosForGroup;
    }

    public void setUserRolesPojosForGroup( List<UserRolesPojo> userRolesPojosForGroup )
    {
        this.userRolesPojosForGroup = userRolesPojosForGroup;
    }

    public List<CrmParameterPojo> getCrmParameterPojos()
    {
        return crmParameterPojos;
    }

    public void setCrmParameterPojos( List<CrmParameterPojo> crmParameterPojos )
    {
        this.crmParameterPojos = crmParameterPojos;
    }

    public CrmParameterPojo getCrmParameterPojo()
    {
        return crmParameterPojo;
    }

    public void setCrmParameterPojo( CrmParameterPojo crmParameterPojo )
    {
        this.crmParameterPojo = crmParameterPojo;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int rowCounter )
    {
        this.rowCounter = rowCounter;
    }

    public List<CrmRolesPojo> getCrmRoles()
    {
        return crmRoles;
    }

    public void setCrmRoles( List<CrmRolesPojo> inCrmRoles )
    {
        crmRoles = inCrmRoles;
    }

    public List<CrmRolesPojo> getCrmRoleList()
    {
        return crmRoleList;
    }

    public void setCrmRoleList( List<CrmRolesPojo> crmRoleList )
    {
        this.crmRoleList = crmRoleList;
    }

    public List<UserRolesPojo> getUserRolesPojos()
    {
        return userRolesPojos;
    }

    public void setUserRolesPojos( List<UserRolesPojo> userRolesPojos )
    {
        this.userRolesPojos = userRolesPojos;
    }

    public UserRolesPojo getUserRolesPojo()
    {
        return userRolesPojo;
    }

    public void setUserRolesPojo( UserRolesPojo userRolesPojo )
    {
        this.userRolesPojo = userRolesPojo;
    }

    public Calendar getLastLoginDate()
    {
        return lastLoginDate;
    }

    public List<UserRolesPojo> getParameterList()
    {
        return parameterList;
    }

    public void setParameterList( List<UserRolesPojo> inParameterList )
    {
        parameterList = inParameterList;
    }

    public void setLastLoginDate( Calendar lastLoginDate )
    {
        this.lastLoginDate = lastLoginDate;
    }

    public Calendar getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    public void setLastModifiedTime( Calendar lastModifiedTime )
    {
        this.createdTime = lastModifiedTime;
    }

    public Calendar getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime( Calendar createdTime )
    {
        this.createdTime = createdTime;
    }

    public Calendar getLockingTime()
    {
        return lockingTime;
    }

    public void setLockingTime( Calendar lockingTime )
    {
        this.lockingTime = lockingTime;
    }

    public String getPassword1()
    {
        return password1;
    }

    public void setPassword1( String password1 )
    {
        this.password1 = password1;
    }

    public String getPassword2()
    {
        return password2;
    }

    public void setPassword2( String password2 )
    {
        this.password2 = password2;
    }

    public String getPassword3()
    {
        return password3;
    }

    public void setPassword3( String password3 )
    {
        this.password3 = password3;
    }

    public int getUnsuccessfullAttempts()
    {
        return unsuccessfullAttempts;
    }

    public void setUnsuccessfullAttempts( int unsuccessfullAttempts )
    {
        this.unsuccessfullAttempts = unsuccessfullAttempts;
    }

    public String getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus( String userStatus )
    {
        this.userStatus = userStatus;
    }

    public String getAdUserName()
    {
        return adUserName;
    }

    public void setAdUserName( String adUserName )
    {
        this.adUserName = adUserName;
    }

    public String getSearchCriteria()
    {
        return searchCriteria;
    }

    public void setSearchCriteria( String searchCriteria )
    {
        this.searchCriteria = searchCriteria;
    }

    public String getSearchCriteriaValue()
    {
        return searchCriteriaValue;
    }

    public void setSearchCriteriaValue( String searchCriteriaValue )
    {
        this.searchCriteriaValue = searchCriteriaValue;
    }
    private String searchCriteriaValue;

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName( String firstName )
    {
        this.firstName = firstName;
    }

    public String getAdCheck()
    {
        return adCheck;
    }

    public void setAdCheck( String adCheck )
    {
        this.adCheck = adCheck;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName( String lastName )
    {
        this.lastName = lastName;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public String getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( String inPartnerId )
    {
        partnerId = inPartnerId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    public long getMobileNo()
    {
        return mobileNo;
    }

    public void setMobileNo( long mobileNo )
    {
        this.mobileNo = mobileNo;
    }

    /* public String getEmpType()
     {
         return empType;
     }
    
     public void setEmpType( String empType )
     {
         this.empType = empType;
     }
    
     public String getEmpCode()
     {
         return empCode;
     }
    
     public void setEmpCode( String empCode )
     {
         this.empCode = empCode;
     }
    
     public String getPartnerName()
     {
         return partnerName;
     }
    
     public void setPartnerName( String partnerName )
     {
         this.partnerName = partnerName;
     }*/
    public String getLastModifiedBy()
    {
        return lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public String getCreatedBy()
    {
        return createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public int getPasswordExpiry()
    {
        return passwordExpiry;
    }

    public void setPasswordExpiry( int passwordExpiry )
    {
        this.passwordExpiry = passwordExpiry;
    }

    public int getUserAccountExpiry()
    {
        return userAccountExpiry;
    }

    public void setUserAccountExpiry( int userAccountExpiry )
    {
        this.userAccountExpiry = userAccountExpiry;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus( int status )
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage( String message )
    {
        this.message = message;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword( String newPassword )
    {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword()
    {
        return confirmPassword;
    }

    public void setConfirmPassword( String confirmPassword )
    {
        this.confirmPassword = confirmPassword;
    }

    public String getOldPassword()
    {
        return oldPassword;
    }

    public void setOldPassword( String oldPassword )
    {
        this.oldPassword = oldPassword;
    }

    public List<CrmUserPojo> getSearchUserList()
    {
        return searchUserList;
    }

    public void setSearchUserList( List<CrmUserPojo> searchUserList )
    {
        this.searchUserList = searchUserList;
    }

    public long getRecordID()
    {
        return recordID;
    }

    public void setRecordID( long inRecordID )
    {
        recordID = inRecordID;
    }

    public CrmUserPojo getCrmUserPojo()
    {
        return crmUserPojo;
    }

    public void setCrmUserPojo( CrmUserPojo inCrmUserPojo )
    {
        crmUserPojo = inCrmUserPojo;
    }

    public List<UserRolesPojo> getOldUserRolesPojos()
    {
        return oldUserRolesPojos;
    }

    public void setOldUserRolesPojos( List<UserRolesPojo> inOldUserRolesPojos )
    {
        oldUserRolesPojos = inOldUserRolesPojos;
    }

    public int getChangePasswordFlag()
    {
        return changePasswordFlag;
    }

    public void setChangePasswordFlag( int changePasswordFlag )
    {
        this.changePasswordFlag = changePasswordFlag;
    }

    public String getFunctionalBin()
    {
        return functionalBin;
    }

    public void setFunctionalBin( String functionalBin )
    {
        this.functionalBin = functionalBin;
    }

    public List<CrmRcaReasonPojo> getLeadStagesList()
    {
        return leadStagesList;
    }

    public void setLeadStagesList( List<CrmRcaReasonPojo> leadStagesList )
    {
        this.leadStagesList = leadStagesList;
    }

    public String[] getFunctionalBinArray()
    {
        return functionalBinArray;
    }

    public void setFunctionalBinArray( String[] functionalBinArray )
    {
        this.functionalBinArray = functionalBinArray;
    }

    public String getSrCode()
    {
        return srCode;
    }

    public void setSrCode( String inSrCode )
    {
        srCode = inSrCode;
    }

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> inPartnerList )
    {
        partnerList = inPartnerList;
    }

    public List<ContentPojo> getProductList()
    {
        return productList;
    }

    public void setProductList( List<ContentPojo> inProductList )
    {
        productList = inProductList;
    }

    public String[] getServiceNameArray()
    {
        return serviceNameArray;
    }

    public void setServiceNameArray( String[] inServiceNameArray )
    {
        serviceNameArray = inServiceNameArray;
    }

    public String[] getPartnerNameArray()
    {
        return partnerNameArray;
    }

    public void setPartnerNameArray( String[] inPartnerNameArray )
    {
        partnerNameArray = inPartnerNameArray;
    }

    public List<CrmUserAssociationPojo> getAssociationPojos()
    {
        return associationPojos;
    }

    public void setAssociationPojos( List<CrmUserAssociationPojo> inAssociationPojos )
    {
        associationPojos = inAssociationPojos;
    }

    public CrmUserAssociationPojo getAssociationPojo()
    {
        return associationPojo;
    }

    public void setAssociationPojo( CrmUserAssociationPojo inAssociationPojo )
    {
        associationPojo = inAssociationPojo;
    }

    public List<StatePojo> getStateDataList()
    {
        return stateDataList;
    }

    public void setStateDataList( List<StatePojo> stateDataList )
    {
        this.stateDataList = stateDataList;
    }

    public List<CityPojo> getCityList()
    {
        return cityList;
    }

    public void setCityList( List<CityPojo> cityList )
    {
        this.cityList = cityList;
    }

    public List<AreaPojo> getAreaList()
    {
        return areaList;
    }

    public void setAreaList( List<AreaPojo> areaList )
    {
        this.areaList = areaList;
    }

    public List<AreaMappingPojo> getAreaMappingPojos()
    {
        return areaMappingPojos;
    }

    public void setAreaMappingPojos( List<AreaMappingPojo> areaMappingPojos )
    {
        this.areaMappingPojos = areaMappingPojos;
    }

    public AreaMappingPojo getAreaMappingPojo()
    {
        return areaMappingPojo;
    }

    public void setAreaMappingPojo( AreaMappingPojo areaMappingPojo )
    {
        this.areaMappingPojo = areaMappingPojo;
    }

    public List<AreaMappingPojo> getOldAreaMappingPojos()
    {
        return oldAreaMappingPojos;
    }

    public void setOldAreaMappingPojos( List<AreaMappingPojo> oldAreaMappingPojos )
    {
        this.oldAreaMappingPojos = oldAreaMappingPojos;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "LoginForm [recordID=" ).append( recordID ).append( ", userId=" ).append( userId )
                .append( ", password=" ).append( password ).append( ", firstName=" ).append( firstName )
                .append( ", adCheck=" ).append( adCheck ).append( ", lastName=" ).append( lastName )
                .append( ", emailId=" ).append( emailId ).append( ", mobileNo=" ).append( mobileNo )
                .append( ", passwordExpiry=" ).append( passwordExpiry ).append( ", userAccountExpiry=" )
                .append( userAccountExpiry ).append( ", lastModifiedBy=" ).append( lastModifiedBy )
                .append( ", lastModifiedTime=" ).append( lastModifiedTime ).append( ", createdBy=" ).append( createdBy )
                .append( ", createdTime=" ).append( createdTime ).append( ", status=" ).append( status )
                .append( ", message=" ).append( message ).append( ", newPassword=" ).append( newPassword )
                .append( ", confirmPassword=" ).append( confirmPassword ).append( ", oldPassword=" )
                .append( oldPassword ).append( ", lastLoginDate=" ).append( lastLoginDate )
                .append( ", searchCriteria=" ).append( searchCriteria ).append( ", partnerId=" ).append( partnerId )
                .append( ", adUserName=" ).append( adUserName ).append( ", searchUserList=" ).append( searchUserList )
                .append( ", userStatus=" ).append( userStatus ).append( ", crmUserPojo=" ).append( crmUserPojo )
                .append( ", password1=" ).append( password1 ).append( ", password2=" ).append( password2 )
                .append( ", password3=" ).append( password3 ).append( ", unsuccessfullAttempts=" )
                .append( unsuccessfullAttempts ).append( ", lockingTime=" ).append( lockingTime )
                .append( ", userRolesPojos=" ).append( userRolesPojos ).append( ", userRolesPojo=" )
                .append( userRolesPojo ).append( ", crmRoleList=" ).append( crmRoleList ).append( ", crmRoles=" )
                .append( crmRoles ).append( ", rowCounter=" ).append( rowCounter ).append( ", crmParameterPojo=" )
                .append( crmParameterPojo ).append( ", crmParameterPojos=" ).append( crmParameterPojos )
                .append( ", userRolesPojosForGroup=" ).append( userRolesPojosForGroup )
                .append( ", changePasswordFlag=" ).append( changePasswordFlag ).append( ", statusList=" )
                .append( statusList ).append( ", searchStatus=" ).append( searchStatus )
                .append( ", oldUserRolesPojos=" ).append( oldUserRolesPojos ).append( ", leadStagesList=" )
                .append( leadStagesList ).append( ", functionalBin=" ).append( functionalBin )
                .append( ", functionalBinArray=" ).append( Arrays.toString( functionalBinArray ) ).append( ", srCode=" )
                .append( srCode ).append( ", partnerList=" ).append( partnerList ).append( ", productList=" )
                .append( productList ).append( ", serviceNameArray=" ).append( Arrays.toString( serviceNameArray ) )
                .append( ", partnerNameArray=" ).append( Arrays.toString( partnerNameArray ) )
                .append( ", associationPojos=" ).append( associationPojos ).append( ", associationPojo=" )
                .append( associationPojo ).append( ", searchCriteriaValue=" ).append( searchCriteriaValue )
                .append( "]" );
        return builder.toString();
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Master Data Reset Start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( methodName != null )
        {
            LoginFormHelper.resetUserForm( this, methodName );
        }
        //        MasterFormHelper.resetRolesPojo( methodName, getUserRolesPojos() );
        //        MasterFormHelper.resetRolesPojo( methodName, getUserRolesPojosForGroup() );
        LOGGER.info( "Master Data Reset End" );
    }

    @Override
    public ActionErrors validate( ActionMapping mapping, HttpServletRequest request )
    {
        String method = request.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "Inside Login Form: Validate" + method );
        ActionErrors actionError = new ActionErrors();
        if ( null != method )
        {
            LOGGER.info( "In validate method Name........" + method );
            /* if(StringUtils.equals( "submitAssignArea", method )){
                 LoginFormHelper.validateArea( actionError, this )  ;
             }*/
            if ( StringUtils.equals( IAppConstants.METHOD_ASSIGN_USER_ROLE, method ) )
            {
                LoginFormHelper.validateForm( actionError, this );
            }
            if ( StringUtils.equals( IAppConstants.METHOD_ASSIGN_USER_PARAMETER, method ) )
            {
                LoginFormHelper.validateParameters( actionError, this );
            }
            if ( StringUtils.equals( IAppConstants.METHOD_LOGIN_AUTHENTICATION, method ) )
            {
                if ( StringUtils.isEmpty( this.userId ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_USER_ID_REQUIRED ) );
                }
                if ( StringUtils.isEmpty( this.password ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_PASSWORD_REQUIRED ) );
                }
            }
            else if ( StringUtils.equals( IAppConstants.METHOD_CHANGE_PASSWORD, method ) )
            {
                if ( StringUtils.isEmpty( this.oldPassword ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_OLDPASSWORD_REQUIRED ) );
                }
                else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_PASSWORD.getPattern(), this.oldPassword ) )
                {
                    actionError.add( oldPassword, new ActionMessage( CRMRegex.PATTERN_PASSWORD.getErrorKey() ) );
                }
                if ( StringUtils.isEmpty( this.newPassword ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_NEW_PASSWORD_REQUIRED ) );
                }
                else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_PASSWORD.getPattern(), this.newPassword ) )
                {
                    actionError.add( newPassword, new ActionMessage( CRMRegex.PATTERN_PASSWORD.getErrorKey() ) );
                }
                if ( StringUtils.isEmpty( this.confirmPassword ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_CONFIRM_PASSWORD_REQUIRED ) );
                }
                else if ( !newPassword.equals( confirmPassword ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_CONFIRM_PASSWORD_NOT_MATCH ) );
                }
            }
            else if ( StringUtils.equals( IAppConstants.METHOD_REGISTER_NEW_USER, method ) )
            {
                if ( StringUtils.isEmpty( this.firstName ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_FIRST_NAME_REQUIRED ) );
                }
                if ( StringUtils.isEmpty( this.lastName ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_LAST_NAME_REQUIRED ) );
                }
                if ( StringUtils.isEmpty( this.emailId ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_EMAIL_ID_REQUIRED ) );
                }
                else if ( !StringUtils.compareRegularExp( CRMRegex.PATTERN_EMAIL.getPattern(), this.emailId ) )
                {
                    actionError
                            .add( IAppConstants.APP_ERROR, new ActionMessage( CRMRegex.PATTERN_EMAIL.getErrorKey() ) );
                }
                if ( this.mobileNo == 0 )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_MOBILE_NO_REQUIRED ) );
                }
                /*if ( this.empType.equals( "0" ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_EMP_TYPE_REQUIRED ) );
                }*/
                if ( ( this.passwordExpiry == 0 ) )
                {
                    actionError.add( "passwordExpiry",
                                     new ActionMessage( IPropertiesConstant.ERROR_PASSWORD_EXPIRY_REQUIRED ) );
                }
                if ( this.userAccountExpiry == 0 )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_USER_ACCOUNT_EXPIRY_REQUIRED ) );
                }
                if ( this.passwordExpiry > this.userAccountExpiry )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_PASSWORD_EXPIRY_ACCOUNT_EXPIRY ) );
                }
                if ( StringUtils.isValidObj( this.functionalBinArray ) )
                {
                    LoginFormHelper.checkFunctionalBinforSales( actionError, this, method );
                }
            }
            else if ( StringUtils.equals( IAppConstants.METHOD_SEARCH_USER, method ) )
            {
                if ( ( this.firstName == null ) && ( this.lastName == null ) && ( this.emailId == null )
                        && ( this.mobileNo == 0 ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_SEARCH_FIELD_REQUIRED ) );
                }
                if ( this.mobileNo != 0 )
                {
                    if ( IAppConstants.MAX_MOBLIE_LENGTH != ( String.valueOf( this.mobileNo ).length() ) )
                    {
                        actionError.add( IAppConstants.APP_ERROR,
                                         new ActionMessage( IPropertiesConstant.ERROR_MOBILE_LENGTH ) );
                    }
                }
                if ( !actionError.isEmpty() )
                {
                    List<ContentPojo> statusList = CRMUtils.getFullUserStatusList();
                    setStatusList( statusList );
                }
            }
            else if ( StringUtils.equals( IAppConstants.METHOD_FORGET_PASSWORD, method ) )
            {
                if ( ( StringUtils.isEmpty( this.userId ) ) || ( StringUtils.isEmpty( this.emailId ) ) )
                {
                    actionError.add( IAppConstants.APP_ERROR,
                                     new ActionMessage( IPropertiesConstant.ERROR_USERID_EMAIL_REQUIRED ) );
                }
            }
            else if ( StringUtils.equals( IAppConstants.METHOD_MODIFY_USER_PAGE, method ) )
            {
            }
        }
        if ( !actionError.isEmpty() )
        {
            LOGGER.info( "Validation Failed." );
        }
        return actionError;
    }
}
