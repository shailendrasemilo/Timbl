package com.np.tele.crm.qrc.config.businessmgr;

import com.np.tele.crm.dto.QrcConfigDto;

public interface IQrcConfigBusiness
{
    QrcConfigDto qrcConfigOperations( String inServiceParam, String inCrmParam, QrcConfigDto inQrcConfigDto );
}
