package com.np.tele.crm.inbox.bm;

import com.np.tele.crm.inbox.form.InboxForm;
import com.np.tele.crm.service.client.ConfigDto;

public interface IInboxManager
{

    ConfigDto getInboxData( ConfigDto inConfigDto );

    ConfigDto changeInboxBin( ConfigDto inConfigDto );

    //ConfigDto changeInboxBinForCRF( ConfigDto inConfigDto );
    
    ConfigDto getAppointments( ConfigDto inConfigDto );

    ConfigDto getRemarks( ConfigDto inConfigDto );
    
    ConfigDto getLeadCloseReason();
    
    ConfigDto getActivityLogs( ConfigDto inConfigDto );

    InboxForm createAllInbox( InboxForm inInboxForm );
}
