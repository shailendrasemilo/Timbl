package com.np.tele.crm.config.businessmgr;

import com.np.tele.crm.dto.ConfigDto;

public interface ICrmConfigBusiness
{
    ConfigDto configOperations( String inServiceParam, String inConfigParam, ConfigDto inConfigDto );

    ConfigDto getInbox( ConfigDto inConfigDto );

    ConfigDto getMappingIdtByAppointMents( ConfigDto inConfigDto );

    ConfigDto geMappingIdByRemarks( ConfigDto inConfigDto );

    ConfigDto changeInboxBin( ConfigDto inConfigDto );

    ConfigDto saveAppointment( ConfigDto inConfigDto );

    ConfigDto getMappedUsersByUser( ConfigDto inConfigDto );

    ConfigDto auditLogOperation( String inOperationParam, ConfigDto inConfigDto );

    ConfigDto customerActivityOps( String inOperationParam, ConfigDto inConfigDto );
}
