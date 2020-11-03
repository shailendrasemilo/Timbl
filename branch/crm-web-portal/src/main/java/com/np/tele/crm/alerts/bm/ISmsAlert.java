package com.np.tele.crm.alerts.bm;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.SmsTemplatePojo;

public interface ISmsAlert
{
    public AlertsDto createSmsTemplate( AlertsForm inAlertsForm,String inUserId );

    public AlertsDto modifySmsTemplate( AlertsForm inAlertsForm,String inUserId );

    public AlertsDto searchAndViewSMSTemplate( AlertsForm inAlertsForm );

    public AlertsDto activeAndDeactiveSMSTemplate( SmsTemplatePojo inSmspojo );
}
