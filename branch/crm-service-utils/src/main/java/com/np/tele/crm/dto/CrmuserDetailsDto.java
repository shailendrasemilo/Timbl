package com.np.tele.crm.dto;

import java.util.List;
import java.util.Map;

import com.np.tele.crm.pojos.CrmUserAssociationPojo;
import com.np.tele.crm.pojos.CrmUserMappingPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.UserRolesPojo;

public class CrmuserDetailsDto
    implements java.io.Serializable
{
    private List<CrmUserPojo>            crmUserDetailsPojoList  = null;
    private List<UserRolesPojo>          userRolesPojos          = null;
    private String                       statusCode              = null;
    private String                       statusDesc              = null;
    private CrmUserPojo                  crmUserDetailsPojo      = null;
    private UserRolesPojo                userRolesPojo           = null;
    private Map<Long, CrmUserPojo>       crmUserMap              = null;
    private String                       userID                  = null;
    private String                       password                = null;
    private String                       userToken               = null;
    private String                       customerToken           = null;
    private CrmUserMappingPojo           crmUserMappingPojo      = null;
    private List<CrmUserMappingPojo>     crMappingPojosList      = null;
    private String                       clientIPAddress         = null;
    private String                       serverIPAddress         = null;
    private String                       newPassword             = null;
    private String                       waiverLimitAmmount      = null;
    private CrmUserAssociationPojo       crmUserAssociationPojo  = null;
    private List<CrmUserAssociationPojo> crmUserAssociationPojos = null;
    private List<String>                 userAssociatedServices;
    private List<String>                 userAssociatedPartners;

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

    public List<UserRolesPojo> getUserRolesPojos()
    {
        return userRolesPojos;
    }

    public void setUserRolesPojos( List<UserRolesPojo> userRolesPojos )
    {
        this.userRolesPojos = userRolesPojos;
    }

    public List<CrmUserPojo> getCrmUserDetailsPojoList()
    {
        return crmUserDetailsPojoList;
    }

    public void setCrmUserDetailsPojoList( List<CrmUserPojo> crmUserDetailsPojoList )
    {
        this.crmUserDetailsPojoList = crmUserDetailsPojoList;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String statusCode )
    {
        this.statusCode = statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public Map<Long, CrmUserPojo> getCrmUserMap()
    {
        return crmUserMap;
    }

    public void setCrmUserMap( Map<Long, CrmUserPojo> crmUserMap )
    {
        this.crmUserMap = crmUserMap;
    }

    public void setStatusDesc( String statusDesc )
    {
        this.statusDesc = statusDesc;
    }

    public CrmUserPojo getCrmUserDetailsPojo()
    {
        return crmUserDetailsPojo;
    }

    public void setCrmUserDetailsPojo( CrmUserPojo crmUserDetailsPojo )
    {
        this.crmUserDetailsPojo = crmUserDetailsPojo;
    }

    public UserRolesPojo getUserRolesPojo()
    {
        return userRolesPojo;
    }

    public void setUserRolesPojo( UserRolesPojo inUserRolesPojo )
    {
        userRolesPojo = inUserRolesPojo;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID( String userID )
    {
        this.userID = userID;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public String getUserToken()
    {
        return userToken;
    }

    public void setUserToken( String inUserToken )
    {
        userToken = inUserToken;
    }

    public String getCustomerToken()
    {
        return customerToken;
    }

    public void setCustomerToken( String inCustomerToken )
    {
        customerToken = inCustomerToken;
    }

    public CrmUserMappingPojo getCrmUserMappingPojo()
    {
        return crmUserMappingPojo;
    }

    public void setCrmUserMappingPojo( CrmUserMappingPojo inCrmUserMappingPojo )
    {
        crmUserMappingPojo = inCrmUserMappingPojo;
    }

    public List<CrmUserMappingPojo> getCrMappingPojosList()
    {
        return crMappingPojosList;
    }

    public void setCrMappingPojosList( List<CrmUserMappingPojo> inCrMappingPojosList )
    {
        crMappingPojosList = inCrMappingPojosList;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword( String inNewPassword )
    {
        newPassword = inNewPassword;
    }

    public String getWaiverLimitAmmount()
    {
        return waiverLimitAmmount;
    }

    public void setWaiverLimitAmmount( String waiverLimitAmmount )
    {
        this.waiverLimitAmmount = waiverLimitAmmount;
    }

    public CrmUserAssociationPojo getCrmUserAssociationPojo()
    {
        return crmUserAssociationPojo;
    }

    public void setCrmUserAssociationPojo( CrmUserAssociationPojo inCrmUserAssociationPojo )
    {
        crmUserAssociationPojo = inCrmUserAssociationPojo;
    }

    public List<CrmUserAssociationPojo> getCrmUserAssociationPojos()
    {
        return crmUserAssociationPojos;
    }

    public void setCrmUserAssociationPojos( List<CrmUserAssociationPojo> inCrmUserAssociationPojos )
    {
        crmUserAssociationPojos = inCrmUserAssociationPojos;
    }
}
