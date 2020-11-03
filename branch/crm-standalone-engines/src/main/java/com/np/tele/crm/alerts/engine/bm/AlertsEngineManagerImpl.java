package com.np.tele.crm.alerts.engine.bm;

import java.util.List;

import org.apache.log4j.Logger;

import com.np.tele.crm.client.CRMAlertsClient;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.service.client.AlertStatusPojo;
import com.np.tele.crm.service.client.AlertsDto;
import com.np.tele.crm.service.client.CRMAlertsService;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class AlertsEngineManagerImpl
    implements IAlertsEngineManager
{
    private static final Logger LOGGER = Logger.getLogger( AlertsEngineManagerImpl.class );

    @Override
    public void processAlerts()
    {
        LOGGER.info( "Inside AlertsEngineManagerImpl, processAlerts" );
        CRMAlertsService crmAlertsService = null;
        AlertsDto alertDto = null;
        try
        {
            crmAlertsService = new CRMAlertsClient();
            alertDto = new AlertsDto();
            alertDto = crmAlertsService.alertOperations( CRMParameter.PENDING_ALERTS.getParameter(), null, alertDto );
            List<AlertStatusPojo> alertStatusPojos = alertDto.getAlertStatusPojos();
            if ( CommonValidator.isValidCollection( alertStatusPojos ) )
            {
                alertDto.setScheduler( true );
                for ( AlertStatusPojo alertStatusPojo : alertStatusPojos )
                {
                    alertDto.setAlertStatusPojo( alertStatusPojo );
                    setRetryCount( alertStatusPojo );
                    alertStatusPojo = crmAlertsService.sendAlerts( alertDto );
                }
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception while process alert records. ", ex );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception while process alerts. ", ex );
        }
        // SendSMS
        // SendEmail
    }

    private void setRetryCount( AlertStatusPojo inAlertStatusPojo )
    {
        LOGGER.info( "Inside AlertsEngineManagerImpl, setRetryCount" );
        if ( StringUtils.equals( inAlertStatusPojo.getEmailSent(), CRMParameter.NO.getParameter() ) )
        {
            inAlertStatusPojo.setEmailRetry( (byte) ( inAlertStatusPojo.getEmailRetry() + 1 ) );
        }
        if ( StringUtils.equals( inAlertStatusPojo.getSmsSent(), CRMParameter.NO.getParameter() ) )
        {
            inAlertStatusPojo.setSmsRetry( (byte) ( inAlertStatusPojo.getSmsRetry() + 1 ) );
        }
    }
}
