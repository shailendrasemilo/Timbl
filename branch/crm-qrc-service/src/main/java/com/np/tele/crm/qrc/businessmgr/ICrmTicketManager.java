package com.np.tele.crm.qrc.businessmgr;

import com.np.tele.crm.dto.CrmQrcDto;

public interface ICrmTicketManager
{
    CrmQrcDto ticketOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto );
}
