package com.np.tele.crm.cap.bussinessmgr;

import com.np.tele.crm.dto.CrmCapDto;

public interface ICrmCapMgr
{
    CrmCapDto crmCapOperation( String inServiceParam, String inOperationParam, CrmCapDto inCrmCapDto );
}
