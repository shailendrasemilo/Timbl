package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;

import com.np.tele.crm.service.client.AlertStatusPojo;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.AlertsServiceService;
import com.np.tele.crm.service.client.CRMAlertsService;
import com.np.tele.crm.service.client.ExtAlertDto;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class CRMAlertsClient
    implements CRMAlertsService
{
    @Override
    public AlertsDto alertTemplate( String inServiceParam, String inAlertType, AlertsDto inAlertDto )
        throws SOAPException_Exception
    {
        AlertsServiceService alertsService = new AlertsServiceService();
        CRMAlertsService crmService = alertsService.getAlertsServicePort();
        return crmService.alertTemplate( inServiceParam, inAlertType, inAlertDto );
    }

    @Override
    public AlertsDto eventTemplate( String inServiceParam, AlertsDto inAlertDto )
        throws SOAPException_Exception
    {
        AlertsServiceService alertsService = new AlertsServiceService();
        CRMAlertsService crmService = alertsService.getAlertsServicePort();
        return crmService.eventTemplate( inServiceParam, inAlertDto );
    }

    @Override
    public AlertStatusPojo sendAlerts( AlertsDto inAlertDto )
        throws SOAPException_Exception
    {
        AlertsServiceService alertsService = new AlertsServiceService();
        CRMAlertsService crmService = alertsService.getAlertsServicePort();
        return crmService.sendAlerts( inAlertDto );
    }

    @Override
    public AlertStatusPojo sendExtAlerts( ExtAlertDto inExtAlertDto )
        throws SOAPException_Exception
    {
        AlertsServiceService alertsService = new AlertsServiceService();
        CRMAlertsService crmService = alertsService.getAlertsServicePort();
        return crmService.sendExtAlerts( inExtAlertDto );
    }

    @Override
    public AlertsDto alertOperations( String inServiceParam, String inAlertType, AlertsDto inAlertDto )
    {
        AlertsServiceService alertsService = new AlertsServiceService();
        CRMAlertsService crmService = alertsService.getAlertsServicePort();
        return crmService.alertOperations( inServiceParam, inAlertType, inAlertDto );
    }
}
