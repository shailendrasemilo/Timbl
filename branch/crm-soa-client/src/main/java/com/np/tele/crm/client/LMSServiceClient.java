package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CRMLMSService;
import com.np.tele.crm.service.client.LMSServiceService;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class LMSServiceClient
    implements CRMLMSService
{
    @Override
    public LmsDto lmsOperation( String inOperationParam, LmsDto inLmsDto )
        throws SOAPException_Exception
    {
        LMSServiceService lmsServiceService = new LMSServiceService();
        CRMLMSService lmsService = lmsServiceService.getLMSServicePort();
        LmsDto lmsDto = lmsService.lmsOperation( inOperationParam, inLmsDto );
        return lmsDto;
    }
}
