package com.np.tele.crm.lms.businessmgr;

import com.np.tele.crm.dto.LMSDto;

public interface ILMSOperationMgr
{
    LMSDto lmsOperation( String inOperationParam, LMSDto inLMSDto );
}
