package com.np.tele.crm.config.bm;

import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.AlertMasterPojo;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.EmailServerPojo;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.SmsGatewayPojo;
import com.np.tele.crm.service.client.UserMasterPojo;
import com.np.tele.crm.service.client.ValidationMasterPojo;
import com.np.tele.crm.utils.StringUtils;

@AppMonitor
public class CrmConfigManagerImpl
    implements ICrmConfigManager
{
    private CRMConfigService    crmConfigClient = null;
    private static final Logger LOGGER          = Logger.getLogger( CrmConfigManagerImpl.class );

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    @Override
    public List<EmailServerPojo> getEmailServers()
    {
        List<EmailServerPojo> emailServerPojos = null;
        try
        {
            ConfigDto configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                                         EmailServerPojo.class.getSimpleName(), null );
            if ( StringUtils.isValidObj( configDto ) )
            {
                emailServerPojos = configDto.getEmailServers();
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Unable to retrive Email Servers: ", ex );
        }
        return emailServerPojos;
    }

    @Override
    public List<SmsGatewayPojo> getSmsGateways()
    {
        List<SmsGatewayPojo> smsGatewaysPojos = null;
        try
        {
            ConfigDto configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                                         SmsGatewayPojo.class.getSimpleName(), null );
            if ( StringUtils.isValidObj( configDto ) )
            {
                smsGatewaysPojos = configDto.getSmsGateways();
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Unable to retrive SMS Gateways: ", ex );
        }
        return smsGatewaysPojos;
    }

    @Override
    public UserMasterPojo getUserAccessPojo()
    {
        UserMasterPojo userMasterPojo = null;
        try
        {
            ConfigDto configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                                         CRMParameter.USER_ACCESS.getParameter(), null );
            userMasterPojo = configDto.getUserMasterPojo();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Unable to retrieve User Master Pojo: ", ex );
        }
        return userMasterPojo;
    }

    @Override
    public AlertMasterPojo getAlertMasterPojo()
    {
        AlertMasterPojo alertMasterPojo = null;
        try
        {
            ConfigDto configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                                         CRMParameter.ALERTS_MASTER.getParameter(),
                                                                         null );
            alertMasterPojo = configDto.getAlertMasterPojo();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Unable to retrieve User Master Pojo: ", ex );
        }
        return alertMasterPojo;
    }

    @Override
    public ValidationMasterPojo getValidationMasterPojo()
    {
        ValidationMasterPojo validationMasterPojo = null;
        try
        {
            ConfigDto configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                                         CRMParameter.VALIDATION_MASTER.getParameter(),
                                                                         null );
            validationMasterPojo = configDto.getValidationMaster();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Unable to retrieve Validation Master Pojo: ", ex );
        }
        return validationMasterPojo;
    }

    @Override
    public ConfigDto getCustomerDetails( ConfigDto inConfigDto )
    {
        ConfigDto configDto = null;
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CrmCustomerDetailsPojo.class.getSimpleName(),
                                                               inConfigDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to retrieve Config Dto : ", ex );
        }
        return configDto;
    }

    @Override
    public List<EventsPojo> getEvents()
    {
        ConfigDto configDto = new ConfigDto();
        List<EventsPojo> eventsPojos = null;
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.EVENTS.getParameter(), configDto );
            if ( StringUtils.isValidObj( configDto ) )
            {
                eventsPojos = configDto.getEventsPojoList();
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to retrieve Config Dto : ", ex );
        }
        return eventsPojos;
    }

    @Override
    public ConfigDto getMacAddressByNP( final String inServicePartner,
                                        final String inCustomerId,
                                        final String inModuleName )
    {
        ConfigDto configDto = new ConfigDto();
        configDto.setRequestType( inModuleName );
        configDto.setCustomerId( inCustomerId );
        configDto.setServicePartner( inServicePartner );
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.DISTRIBUTOR_INVENTORY_LIST.getParameter(),
                                                               configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to retrieve Config Dto : ", ex );
        }
        return configDto;
    }

    public ConfigDto updateInboxStatus( ConfigDto inConfigDto )
    {
        ConfigDto configDto = null;
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.UPDATE.getParameter(),
                                                               CRMParameter.INBOX.getParameter(), inConfigDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to retrieve Config Dto : ", ex );
            configDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return configDto;
    }

    @Override
    public ConfigDto getRemarks( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside QrcManagerImpl, getRemarks" );
        try
        {
            inConfigDto = getCrmConfigClient().geMappingIdByRemarks( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Remarks SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto listData( final String inConfigParam, final String inMappingId, final String inModuleName )
    {
        ConfigDto configDto = new ConfigDto();
        configDto.setRequestType( inModuleName );
        configDto.setMappingId( inMappingId );
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(), inConfigParam,
                                                               configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Unable to retrieve Config Dto : ", ex );
            configDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return configDto;
    }
}
