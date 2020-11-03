package com.np.tele.crm.external.trigger;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.SOAPException;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ByteArrayResource;

import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.dto.TriggerRequestDto;
import com.np.tele.crm.dto.TriggerResponseDto;
import com.np.tele.crm.ext.pojos.CustomerDetailsPojo;
import com.np.tele.crm.ext.pojos.PrepaidPaymentPojo;
import com.np.tele.crm.ext.pojos.QrcTicketPojo;
import com.np.tele.crm.external.businessmgr.ICrmExternalTriggerMgr;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_CRM_EXTERNAL_TRIGGER_SERVICE_EJB)
@Local(ICrmExternalTriggerServiceLocal.class)
@Remote(ICrmExternalTriggerServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.external.trigger.ICrmExternalTriggerService")
public class CrmExternalTriggerService
    implements ICrmExternalTriggerServiceLocal, ICrmExternalTriggerServiceRemote
{
    private static final Logger    LOGGER                 = Logger.getLogger( CrmExternalTriggerService.class );
    private BeanFactory            factory                = null;
    private ICrmExternalTriggerMgr iCrmExternalTriggerMgr = null;
    @Resource
    private WebServiceContext      wsContext              = null;

    public ICrmExternalTriggerMgr getiCrmExternalTriggerMgr()
    {
        return iCrmExternalTriggerMgr;
    }

    public void setiCrmExternalTriggerMgr( ICrmExternalTriggerMgr inICrmExternalTriggerMgr )
    {
        iCrmExternalTriggerMgr = inICrmExternalTriggerMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "CrmExternalTriggerService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_EXTERNAL_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setiCrmExternalTriggerMgr( (ICrmExternalTriggerMgr) factory.getBean( "iCrmExternalTriggerMgr" ) );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing CrmExternalTriggerService", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    @Override
    public TriggerResponseDto postPrepaidPayment( String inAuthUsername,
                                                  String inAuthPassword,
                                                  TriggerRequestDto inTriggerRequestDto,
                                                  PrepaidPaymentPojo inPrepaidPaymentPojo )
        throws SOAPException
    {
        inTriggerRequestDto.setAuthUsername( inAuthUsername );
        inTriggerRequestDto.setAuthPassword( inAuthPassword );
        inTriggerRequestDto.setClientIp( getClientIP() );
        return getiCrmExternalTriggerMgr().postPrepaidPayment( inPrepaidPaymentPojo, inTriggerRequestDto );
    }

    @Override
    public TriggerResponseDto generateQrcTicket( String inAuthUsername,
                                                 String inAuthPassword,
                                                 TriggerRequestDto inTriggerRequestDto,
                                                 QrcTicketPojo inQrcTicketPojo )
        throws SOAPException
    {
        inTriggerRequestDto.setAuthUsername( inAuthUsername );
        inTriggerRequestDto.setAuthPassword( inAuthPassword );
        inTriggerRequestDto.setClientIp( getClientIP() );
        return getiCrmExternalTriggerMgr().generateQrcTicket( inQrcTicketPojo, inTriggerRequestDto );
    }

    @Override
    public TriggerResponseDto changeCustomerStatus( String inAuthUsername,
                                                    String inAuthPassword,
                                                    TriggerRequestDto inTriggerRequestDto,
                                                    CustomerDetailsPojo inCustomerDetailsPojo )
        throws SOAPException
    {
        inTriggerRequestDto.setAuthUsername( inAuthUsername );
        inTriggerRequestDto.setAuthPassword( inAuthPassword );
        inTriggerRequestDto.setClientIp( getClientIP() );
        return getiCrmExternalTriggerMgr().changeCustomerStatus( inCustomerDetailsPojo, inTriggerRequestDto );
    }

    private String getClientIP()
    {
        final MessageContext mesgCont = wsContext.getMessageContext();
        final HttpServletRequest req = (HttpServletRequest) mesgCont.get( MessageContext.SERVLET_REQUEST );
        String ip = req.getRemoteAddr();
        LOGGER.info( "Requested Client IP:" + ip );
        return ip;
    }

    @Override
    public TriggerResponseDto changePlanStatus( String inAuthUsername,
                                                String inAuthPassword,
                                                CrmBillingPlanRequestPojo inBillingPlanRequestPojo )
        throws SOAPException
    {
        TriggerRequestDto triggerRequestDto = new TriggerRequestDto();
        triggerRequestDto.setAuthUsername( inAuthUsername );
        triggerRequestDto.setAuthPassword( inAuthPassword );
        triggerRequestDto.setClientIp( getClientIP() );
        return getiCrmExternalTriggerMgr().changePlanStatus( inBillingPlanRequestPojo, triggerRequestDto );
    }
}
