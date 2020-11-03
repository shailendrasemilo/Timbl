package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmFinanceService;
import com.np.tele.crm.service.client.CrmFinanceServiceService;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class CRMFinanceClient
    implements CrmFinanceService
{
    @Override
    public CrmFinanceDto trackPayment( String inServiceParam, String inCrmParam, CrmFinanceDto inCrmFinanceDto )
        throws SOAPException_Exception
    {
        CrmFinanceServiceService financeService = new CrmFinanceServiceService();
        CrmFinanceService crmFinanceService = financeService.getCrmFinanceServicePort();
        return crmFinanceService.trackPayment( inServiceParam, inCrmParam, inCrmFinanceDto );
    }

    @Override
    public CrmFinanceDto postPayment( String inServiceParam, String inCrmParam, CrmFinanceDto inCrmFinanceDto )
        throws SOAPException_Exception
    {
        CrmFinanceServiceService financeService = new CrmFinanceServiceService();
        CrmFinanceService crmFinanceService = financeService.getCrmFinanceServicePort();
        return crmFinanceService.postPayment( inServiceParam, inCrmParam, inCrmFinanceDto );
    }

    @Override
    public CrmFinanceDto pgwOperations( String inServiceParam, String inCrmParam, CrmFinanceDto inCrmFinanceDto )
        throws SOAPException_Exception
    {
        CrmFinanceServiceService financeService = new CrmFinanceServiceService();
        CrmFinanceService service = financeService.getCrmFinanceServicePort();
        return service.pgwOperations( inServiceParam, inCrmParam, inCrmFinanceDto );
    }
}
