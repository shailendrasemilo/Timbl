package com.np.tele.crm.alerts.bm;


import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.service.client.AlertsDto;

public interface IEventTemplateMapping
{
    AlertsDto eventTemplateMappingList( AlertsDto inAlertsDto );

    AlertsDto createEventTemplateMapping( AlertsForm inAlertsForm, String inUserId );

    AlertsDto templateMappedWithEvent(AlertsDto inAlertsDto );

}
