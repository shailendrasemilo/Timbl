package com.np.tele.crm.massoutage.dao;

import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.pojos.CrmMassOutagePojo;

public interface IMassOutageDao
{
    public CrmMassOutageDto searchMassOutage( CrmMassOutageDto inCrmMassOutageDto );

    public void saveMassOutage( CrmMassOutageDto inCrmMassOutageDto );

    public void updateMassOutage( CrmMassOutageDto inCrmMassOutageDto );

    public void resolveMassOutage( CrmMassOutageDto inCrmMassOutageDto );

    public CRMServiceCode trackMassOutage( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutagePojo trackMassOutage( String inCustomerId );

    CrmMassOutageDto searchMassOutageMaster( CrmMassOutageDto inCrmMassOutageDto );

    public void updateMassOutageActivityStatus( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutageDto searchMassOutageEngine( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutageDto searchCustomerInOutageMaster( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutageDto updateMassOutageMasterActivityStatus( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutageDto searchMassOutageSociety( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutageDto updateMassOutageSocietyActivityStatus( CrmMassOutageDto inCrmMassOutageDto );

    public CrmMassOutageDto searchCustomerInOutageSociety( CrmMassOutageDto inCrmMassOutageDto );
}
