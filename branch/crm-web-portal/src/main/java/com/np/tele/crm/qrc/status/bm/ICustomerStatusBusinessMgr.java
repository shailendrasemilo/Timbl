package com.np.tele.crm.qrc.status.bm;

import java.util.List;

import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.CrmQrcDto;

public interface ICustomerStatusBusinessMgr
{
    public CrmQrcDto updateSafeCustody( QrcForm inQrcForm );

    public List<String> updateCustomersStatus( QrcForm inQrcForm, String inUser, String inFilePath );
}
