package com.np.tele.crm.usrmngmnt.bm;

import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.forms.LoginForm;

public interface IUserBMngr
{
    public CrmuserDetailsDto loginAuthentication( String inUserName, String inPassword );

    public CrmuserDetailsDto changePassword( String inUserName, String inPassword, String inNewPassword );

    public CrmuserDetailsDto userOperation( LoginForm inLoginForm, String inMethodName, String inCreatedBy );

    public CrmuserDetailsDto searchUser( LoginForm inLoginForm );

    public CrmuserDetailsDto modifyUserOperation( LoginForm loginForm, String inMethodName, String inModifiedBy );

    public CrmuserDetailsDto changeUserStatus( LoginForm loginForm, String inMethodName, String inCreatedBy );

    public CrmuserDetailsDto resetPassword( LoginForm loginForm, String inMethodName, String inCreatedBy );

    public CrmuserDetailsDto forgetPassword( String inEmailId, String inUserId, String inMethodName );

    public CrmuserDetailsDto assignUserRole( LoginForm inLoginForm );

    CrmuserDetailsDto assignUserParameter( LoginForm inLoginForm );

    public CrmuserDetailsDto getUserAssignAccess( String userId );

    public boolean resendActivationLink( String inUserId );

    public ConfigDto getActivityLogs( LoginForm inUserForm );

    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto getCustomerAssociations( String inUserId );

    public CrmuserDetailsDto assignUserArea( LoginForm inLoginForm ,long parameterId);
}
