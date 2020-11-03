package com.np.tele.crm.qrc.bm;

import com.np.tele.crm.qrc.forms.QrcMassOutageForm;
import com.np.tele.crm.service.client.CrmMassOutageDto;
import com.np.tele.crm.service.client.CrmMassOutagePojo;

public interface IQrcMassOutageManager
{
    public CrmMassOutageDto searchMassOutage( QrcMassOutageForm inQrcMassOutageForm );

    public CrmMassOutageDto addMassOutage( QrcMassOutageForm massOutageForm );

    public CrmMassOutageDto viewMassOutage( CrmMassOutagePojo inCrmMassOutagePojo );

    public CrmMassOutageDto updateOutage( CrmMassOutagePojo inMassOutagePojo, String inCrmUser );

    public CrmMassOutageDto resolveMassOutage( CrmMassOutagePojo inCrmMassOutagePojo, String inCrmUser );
}
