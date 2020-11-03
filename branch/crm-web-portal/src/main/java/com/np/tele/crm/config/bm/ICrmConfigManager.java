package com.np.tele.crm.config.bm;

import java.util.List;

import com.np.tele.crm.service.client.AlertMasterPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.EmailServerPojo;
import com.np.tele.crm.service.client.EventsPojo;
import com.np.tele.crm.service.client.SmsGatewayPojo;
import com.np.tele.crm.service.client.UserMasterPojo;
import com.np.tele.crm.service.client.ValidationMasterPojo;

public interface ICrmConfigManager
{
    public List<EmailServerPojo> getEmailServers();

    public List<SmsGatewayPojo> getSmsGateways();

    UserMasterPojo getUserAccessPojo();

    AlertMasterPojo getAlertMasterPojo();

    ValidationMasterPojo getValidationMasterPojo();

    ConfigDto getCustomerDetails( ConfigDto inConfigDto );

    public List<EventsPojo> getEvents();

    ConfigDto getMacAddressByNP( String inServicePartner, String inCustomerId, String inModuleName );

    ConfigDto updateInboxStatus( ConfigDto inConfigDto );

    ConfigDto getRemarks( ConfigDto inConfigDto );

    ConfigDto listData( String inConfigParam, String inMappingId, String inModuleName );
}
