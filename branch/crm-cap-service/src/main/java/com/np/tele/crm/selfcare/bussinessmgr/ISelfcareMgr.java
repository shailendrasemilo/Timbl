package com.np.tele.crm.selfcare.bussinessmgr;

import com.np.tele.crm.dto.SelfcareDto;

public interface ISelfcareMgr
{
    SelfcareDto selfcareOperation( String inServiceParam, String inOperationParam, SelfcareDto inSelfcareDto );
}
