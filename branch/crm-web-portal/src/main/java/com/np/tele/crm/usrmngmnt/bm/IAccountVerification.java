package com.np.tele.crm.usrmngmnt.bm;

import com.np.tele.crm.service.client.CrmuserDetailsDto;

public interface IAccountVerification
{
    CrmuserDetailsDto validateUser(final String token );

    CrmuserDetailsDto activateUser(  long userID );
}
