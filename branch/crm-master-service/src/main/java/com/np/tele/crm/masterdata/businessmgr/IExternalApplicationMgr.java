package com.np.tele.crm.masterdata.businessmgr;

import com.np.tele.crm.dto.MasterDataDto;

public interface IExternalApplicationMgr
{
    MasterDataDto externalApplication( String inServiceParam, MasterDataDto inMasterDataDto );
}
