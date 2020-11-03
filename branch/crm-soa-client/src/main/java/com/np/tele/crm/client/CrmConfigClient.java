package com.np.tele.crm.client;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.CRMConfigServiceService;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class CrmConfigClient
    implements CRMConfigService
{
    @AppMonitor
    @Override
    public ConfigDto configOperations( String inServiceParam, String inConfigParam, ConfigDto inConfigDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.configOperations( inServiceParam, inConfigParam, inConfigDto );
    }

    @Override
    public ConfigDto getInbox( ConfigDto inConfigDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.getInbox( inConfigDto );
    }

    @Override
    public ConfigDto geMappingIdByRemarks( ConfigDto configDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.geMappingIdByRemarks( configDto );
    }

    @Override
    public ConfigDto changeInboxBin( ConfigDto configDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.changeInboxBin( configDto );
    }

    @Override
    public ConfigDto saveAppointment( ConfigDto configDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.saveAppointment( configDto );
    }

    @Override
    public ConfigDto getMappingIdtByAppointMents( ConfigDto configDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.getMappingIdtByAppointMents( configDto );
    }

    @Override
    public ConfigDto getMappedUsersByUser( ConfigDto configDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.getMappedUsersByUser( configDto );
    }

    @Override
    public ConfigDto auditLogOperation( String inOperationParam, ConfigDto configDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.auditLogOperation( inOperationParam, configDto );
    }

    @Override
    public ConfigDto customerActivityOps( String inOperationParam, ConfigDto inConfigDto )
        throws SOAPException_Exception
    {
        CRMConfigServiceService configService = new CRMConfigServiceService();
        CRMConfigService configClient = configService.getCRMConfigServicePort();
        return configClient.customerActivityOps( inOperationParam, inConfigDto );
    }
}
