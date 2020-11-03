package com.np.tele.crm.alerts.businessmgr;

import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.dto.ExtAlertDto;
import com.np.tele.crm.pojos.AlertStatusPojo;

public interface IAlertsTemplateMgr
{
    AlertsDto alertTemplate( String inServiceParam, String inAlertType, AlertsDto inAlertsDto );

    AlertsDto eventTemplate( String inServiceParam, AlertsDto inAlertsDto );

    AlertStatusPojo sendAlerts( AlertsDto inAlertsDto );

    AlertStatusPojo sendAlerts( ExtAlertDto inExtAlertsDto );

    AlertsDto alertOperations( String inServiceParam, String inAlertType, AlertsDto inAlertsDto );
}
