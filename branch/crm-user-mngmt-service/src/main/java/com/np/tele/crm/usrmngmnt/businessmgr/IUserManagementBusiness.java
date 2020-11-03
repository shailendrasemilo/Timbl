package com.np.tele.crm.usrmngmnt.businessmgr;

import com.np.tele.crm.dto.CrmuserDetailsDto;

public interface IUserManagementBusiness
{
    public CrmuserDetailsDto loginAuthentication( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto changePassword( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto userOperation( CrmuserDetailsDto inCrmuserDetailsDto, String inOperation );

    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto userMapping( CrmuserDetailsDto inCrmuserDetailsDto );

    public CrmuserDetailsDto getUserAssignAccess( String inUserID );

    public CrmuserDetailsDto userEscalationAndWorkflowMapping( CrmuserDetailsDto inCrmuserDetailsDto, String inOperation );

    public CrmuserDetailsDto getUsersByParameter( CrmuserDetailsDto inCrmuserDetailsDto );
    
    public CrmuserDetailsDto getCustomerAssociations(String inUserID);
}
