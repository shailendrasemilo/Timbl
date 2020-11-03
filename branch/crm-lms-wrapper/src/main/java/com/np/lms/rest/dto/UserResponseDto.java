package com.np.lms.rest.dto;

import java.util.List;

import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.pojos.CrmUserAssociationPojo;
import com.np.tele.crm.pojos.CrmUserMappingPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.UserRolesPojo;

public class UserResponseDto
{
    private List<CrmUserPojo>   crmUserDetailsPojoList = null;
    private List<UserRolesPojo> userRolesPojos         = null;
    private String              statusCode             = null;
    private String              statusDesc             = null;
    private CrmUserPojo         crmUserDetailsPojo     = null;
    private UserRolesPojo       userRolesPojo          = null;
    private List<ContentPojo>   binPojos;

    public List<ContentPojo> getBinPojos()
    {
        return binPojos;
    }

    public void setBinPojos( List<ContentPojo> inBinPojos )
    {
        binPojos = inBinPojos;
    }

    public List<CrmUserPojo> getCrmUserDetailsPojoList()
    {
        return crmUserDetailsPojoList;
    }

    public void setCrmUserDetailsPojoList( List<CrmUserPojo> inCrmUserDetailsPojoList )
    {
        crmUserDetailsPojoList = inCrmUserDetailsPojoList;
    }

    public List<UserRolesPojo> getUserRolesPojos()
    {
        return userRolesPojos;
    }

    public void setUserRolesPojos( List<UserRolesPojo> inUserRolesPojos )
    {
        userRolesPojos = inUserRolesPojos;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public CrmUserPojo getCrmUserDetailsPojo()
    {
        return crmUserDetailsPojo;
    }

    public void setCrmUserDetailsPojo( CrmUserPojo inCrmUserDetailsPojo )
    {
        crmUserDetailsPojo = inCrmUserDetailsPojo;
    }

    public UserRolesPojo getUserRolesPojo()
    {
        return userRolesPojo;
    }

    public void setUserRolesPojo( UserRolesPojo inUserRolesPojo )
    {
        userRolesPojo = inUserRolesPojo;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "UserResponseDto [crmUserDetailsPojoList=" ).append( crmUserDetailsPojoList )
                .append( ", userRolesPojos=" ).append( userRolesPojos ).append( ", statusCode=" ).append( statusCode )
                .append( ", statusDesc=" ).append( statusDesc ).append( ", crmUserDetailsPojo=" )
                .append( crmUserDetailsPojo ).append( ", userRolesPojo=" ).append( userRolesPojo ).append( "]" );
        return builder.toString();
    }
}
