package com.np.lms.rest.dto;

import com.np.tele.crm.pojos.CrmUserPojo;

public class UserRequestDto
{
    private String      userID             = null;
    private String      password           = null;
    private String      newPassword        = null;
    private String      opretaion;
    private String      serviceParam;
    private CrmUserPojo crmUserDetailsPojo = null;

    public String getUserID()
    {
        return userID;
    }

    public void setUserID( String inUserID )
    {
        userID = inUserID;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String inPassword )
    {
        password = inPassword;
    }

    public String getOpretaion()
    {
        return opretaion;
    }

    public void setOpretaion( String inOpretaion )
    {
        opretaion = inOpretaion;
    }

    public String getServiceParam()
    {
        return serviceParam;
    }

    public void setServiceParam( String inServiceParam )
    {
        serviceParam = inServiceParam;
    }

    public String getNewPassword()
    {
        return newPassword;
    }

    public void setNewPassword( String inNewPassword )
    {
        newPassword = inNewPassword;
    }

    public CrmUserPojo getCrmUserDetailsPojo()
    {
        return crmUserDetailsPojo;
    }

    public void setCrmUserDetailsPojo( CrmUserPojo inCrmUserDetailsPojo )
    {
        crmUserDetailsPojo = inCrmUserDetailsPojo;
    }
}
