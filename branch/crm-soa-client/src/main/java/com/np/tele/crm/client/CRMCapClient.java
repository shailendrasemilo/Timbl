package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCapServiceService;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SelfcareDto;

@ClientOperations
public class CRMCapClient
    implements CrmCapService
{
    @Override
    public CrmCapDto crmCapOperation( String inServiceParam, String inOperationParam, CrmCapDto inCrmCapDto )
        throws SOAPException_Exception
    {
        CrmCapServiceService capServiceService = new CrmCapServiceService();
        CrmCapService crmCapService = capServiceService.getCrmCapServicePort();
        CrmCapDto crmCapDto = crmCapService.crmCapOperation( inServiceParam, inOperationParam, inCrmCapDto );
        return crmCapDto;
    }

    @Override
    public SelfcareDto selfcareOperation( String inServiceParam, String inOperationParam, SelfcareDto inSelfcareDto )
        throws SOAPException_Exception
    {
        CrmCapServiceService capServiceService = new CrmCapServiceService();
        CrmCapService crmCapService = capServiceService.getCrmCapServicePort();
        SelfcareDto selfcareDto = crmCapService.selfcareOperation( inServiceParam, inOperationParam, inSelfcareDto );
        return selfcareDto;
    }
}
