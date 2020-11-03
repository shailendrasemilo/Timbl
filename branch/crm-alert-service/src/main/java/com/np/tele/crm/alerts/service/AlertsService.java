package com.np.tele.crm.alerts.service;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ByteArrayResource;

import com.np.tele.crm.alerts.businessmgr.IAlertsTemplateMgr;
import com.np.tele.crm.alerts.service.IAlertsServiceLocal;
import com.np.tele.crm.alerts.service.IAlertsServiceRemote;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.dto.AlertsDto;
import com.np.tele.crm.dto.ExtAlertDto;
import com.np.tele.crm.pojos.AlertStatusPojo;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_ALERTS_SERVICE_EJB)
@Local(IAlertsServiceLocal.class)
@Remote(IAlertsServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.alerts.service.IAlertsService")
public class AlertsService
    implements IAlertsServiceLocal, IAlertsServiceRemote
{
    private BeanFactory          factory          = null;
    private static final Logger  LOGGER           = Logger.getLogger( AlertsService.class );
    protected IAlertsTemplateMgr alertTemplateMgr = null;

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "AlertService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_ALERT_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                alertTemplateMgr = (IAlertsTemplateMgr) factory.getBean( "alertsTemplateMgr" );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing AlertService", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    @Override
    public AlertsDto alertTemplate( String inServiceParam, String inAlertType, AlertsDto inAlertsDto )
        throws SOAPException
    {
        return alertTemplateMgr.alertTemplate( inServiceParam, inAlertType, inAlertsDto );
    }

    @Override
    public AlertsDto eventTemplate( String inServiceParam, AlertsDto inAlertsDto )
        throws SOAPException
    {
        return alertTemplateMgr.eventTemplate( inServiceParam, inAlertsDto );
    }

    @Override
    public AlertStatusPojo sendAlerts( AlertsDto inAlertsDto )
        throws SOAPException
    {
        return alertTemplateMgr.sendAlerts( inAlertsDto );
    }

    @Override
    public AlertStatusPojo sendExtAlerts( ExtAlertDto inExtAlertDto )
        throws SOAPException
    {
        return alertTemplateMgr.sendAlerts( inExtAlertDto );
    }

    @Override
    public AlertsDto alertOperations( String inServiceParam, String inAlertType, AlertsDto inAlertsDto )
    {
        return alertTemplateMgr.alertOperations( inServiceParam, inAlertType, inAlertsDto );
    }
}
