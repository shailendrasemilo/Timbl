package com.np.tele.crm.client;


import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CrmMassOutageDto;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmQrcServiceService;
import com.np.tele.crm.service.client.QrcConfigDto;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class CrmQrcClient
    implements CrmQrcService
{
    @Override
    public CrmQrcDto qrcOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
        throws SOAPException_Exception
    {
        CrmQrcServiceService qrcService = new CrmQrcServiceService();
        CrmQrcService crmQrcService = qrcService.getCrmQrcServicePort();
        return crmQrcService.qrcOperations( inServiceParam, inCrmParam, inCrmQrcDto );
    }

    @Override
    public QrcConfigDto qrcConfigOperations( String inServiceParam, String inCrmParam, QrcConfigDto inQrcConfigDto )
        throws SOAPException_Exception
    {
        CrmQrcServiceService qrcService = new CrmQrcServiceService();
        CrmQrcService crmQrcService = qrcService.getCrmQrcServicePort();
        return crmQrcService.qrcConfigOperations( inServiceParam, inCrmParam, inQrcConfigDto );
    }

    @Override
    public CrmQrcDto customerProfileOperations( String serviceParam, String methodName, CrmQrcDto crmQrcDto )
        throws SOAPException_Exception
    {
        CrmQrcServiceService qrcService = new CrmQrcServiceService();
        CrmQrcService crmQrcService = qrcService.getCrmQrcServicePort();
        return crmQrcService.customerProfileOperations( serviceParam, methodName, crmQrcDto );
    }

    @Override
    public CrmQrcDto workFlowOperations( String serviceParam, String methodName, CrmQrcDto crmQrcDto )
        throws SOAPException_Exception
    {
        CrmQrcServiceService qrcService = new CrmQrcServiceService();
        CrmQrcService crmQrcService = qrcService.getCrmQrcServicePort();
        return crmQrcService.workFlowOperations( serviceParam, methodName, crmQrcDto );
    }

    @Override
    public CrmMassOutageDto massOutageOperations( String serviceParam, CrmMassOutageDto crmMassOutageDto )
        throws SOAPException_Exception
    {
        CrmQrcServiceService qrcService = new CrmQrcServiceService();
        CrmQrcService crmQrcService = qrcService.getCrmQrcServicePort();
        return crmQrcService.massOutageOperations( serviceParam, crmMassOutageDto );
    }

    @Override
    public CrmQrcDto ticketOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
        throws SOAPException_Exception
    {
        CrmQrcServiceService qrcService = new CrmQrcServiceService();
        CrmQrcService crmQrcService = qrcService.getCrmQrcServicePort();
        return crmQrcService.ticketOperations( inServiceParam, inCrmParam, inCrmQrcDto );
    }
}
