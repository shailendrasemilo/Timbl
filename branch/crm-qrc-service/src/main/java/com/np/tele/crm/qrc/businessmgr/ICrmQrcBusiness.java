package com.np.tele.crm.qrc.businessmgr;

import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.dto.CrmQrcDto;

public interface ICrmQrcBusiness
{
    public CrmQrcDto qrcOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto );

    public CrmQrcDto customerProfileOperations( String inServiceParam, String method, CrmQrcDto inCrmQrcDto );

    public CrmQrcDto workFlowOperations( String inServiceParam, String method, CrmQrcDto inCrmQrcDto );

    public CrmMassOutageDto massOutageOperations( String inServiceParam, CrmMassOutageDto inCrmMassOutageDto );
}
