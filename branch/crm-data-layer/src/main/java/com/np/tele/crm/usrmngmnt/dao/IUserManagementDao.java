package com.np.tele.crm.usrmngmnt.dao;

import com.np.tele.crm.dto.CrmuserDetailsDto;
import com.np.tele.crm.pojos.CrmUserPojo;

public interface IUserManagementDao
{
    public CrmUserPojo getUserByUserID( String inUserName );

    public CrmuserDetailsDto changePassword( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto createUser( CrmuserDetailsDto inCrmuserDetailsDton );

    public CrmuserDetailsDto updateUser( CrmuserDetailsDto inCrmuserDetailsDton );

    public CrmuserDetailsDto deleteUser( CrmuserDetailsDto inCrmuserDetailsDton );

    public boolean increaseWrongPasswordAttempt( CrmUserPojo inUserPojo, int inLocked_value, int inExpire_value );

    public boolean changeUserStatus( CrmUserPojo inUserPojo );

//    public boolean updateLastLoginDate( CrmUserPojo inUserPojo );

    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto updateUserStatus( CrmuserDetailsDto crmuserDetailsDto );

    public CrmuserDetailsDto fogetPassword( CrmuserDetailsDto crmuserDetailsDto, int lock_duration );

    public CrmuserDetailsDto userMapping( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto getUserAssignAccess( final String inUserID );

    public CrmuserDetailsDto resetPassword( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto verifyUser( String inUserToken );

    public CrmuserDetailsDto createUserEWMaping( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto searchUserEWMaping( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto getUsersByParameter( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto getUserList();

    public void saveUserAssociation( CrmuserDetailsDto inCrmuserDetailsDto, String inUser );

    public CrmuserDetailsDto getCustomerAssociations( String inUserId );
}
