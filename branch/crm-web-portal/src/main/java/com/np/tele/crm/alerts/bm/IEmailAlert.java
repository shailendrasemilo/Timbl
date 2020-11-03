package com.np.tele.crm.alerts.bm;

import java.util.List;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.EmailCcBccPojo;
import com.np.tele.crm.service.client.EmailTemplatePojo;

public interface IEmailAlert
{
    public AlertsDto searchAndViewEmailTemplate( AlertsForm inAlertsForm );

    public EmailTemplatePojo searchByTemplateId( long inTemplateId );

    public AlertsDto activeAndDeactiveEmailTemplate( EmailTemplatePojo emailTemplatePojo );

    public boolean setCCBcc( String inEmailCc, String inParameter, List<EmailCcBccPojo> inEmailCcBccList );

    AlertsDto createEmailTemplate( AlertsForm inAlertsForm, EmailTemplatePojo inEmailTemplatePojo );

    void setCCBCCtoForm( AlertsForm inAlertsForm, List<EmailCcBccPojo> inEmailCcBccs );
}
