package com.np.tele.crm.qrc.service;

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

import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.dto.CrmMassOutageDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.dto.QrcConfigDto;
import com.np.tele.crm.qrc.businessmgr.ICrmQrcBusiness;
import com.np.tele.crm.qrc.businessmgr.ICrmTicketManager;
import com.np.tele.crm.qrc.config.businessmgr.IQrcConfigBusiness;
import com.np.tele.crm.qrc.service.ICrmQrcServiceLocal;
import com.np.tele.crm.qrc.service.ICrmQrcServiceRemote;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_CRM_QRC_SERVICE_EJB)
@Local(ICrmQrcServiceLocal.class)
@Remote(ICrmQrcServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.qrc.service.ICrmQrcService")
public class CrmQrcService
    implements ICrmQrcServiceLocal, ICrmQrcServiceRemote
{
    private static final Logger LOGGER            = Logger.getLogger( CrmQrcService.class );
    private BeanFactory         factory           = null;
    private ICrmQrcBusiness     crmQrcBusiness    = null;
    private IQrcConfigBusiness  qrcConfigBusiness = null;
    private ICrmTicketManager   ticketManager     = null;

    public ICrmQrcBusiness getCrmQrcBusiness()
    {
        return crmQrcBusiness;
    }

    public void setCrmQrcBusiness( ICrmQrcBusiness inCrmQrcBusiness )
    {
        crmQrcBusiness = inCrmQrcBusiness;
    }

    public IQrcConfigBusiness getQrcConfigBusiness()
    {
        return qrcConfigBusiness;
    }

    public void setQrcConfigBusiness( IQrcConfigBusiness inQrcConfigBusiness )
    {
        qrcConfigBusiness = inQrcConfigBusiness;
    }

    public ICrmTicketManager getTicketManager()
    {
        return ticketManager;
    }

    public void setTicketManager( ICrmTicketManager inTicketManager )
    {
        ticketManager = inTicketManager;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "CrmQrcService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_QRC_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                setCrmQrcBusiness( (ICrmQrcBusiness) factory.getBean( "crmQrcBusiness" ) );
                setQrcConfigBusiness( (IQrcConfigBusiness) factory.getBean( "qrcConfigBusiness" ) );
                setTicketManager( (ICrmTicketManager) factory.getBean( "ticketManager" ) );
                LOGGER.info( CrmQrcService.class.getSimpleName() + "-Bean Factory Initialized" );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing QRC Service", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    @Override
    public CrmQrcDto qrcOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
        throws SOAPException
    {
        return getCrmQrcBusiness().qrcOperations( inServiceParam, inCrmParam, inCrmQrcDto );
    }

    @Override
    public QrcConfigDto qrcConfigOperations( String inServiceParam, String inCrmParam, QrcConfigDto inQrcConfigDto )
        throws SOAPException
    {
        return getQrcConfigBusiness().qrcConfigOperations( inServiceParam, inCrmParam, inQrcConfigDto );
    }

    @Override
    public CrmQrcDto customerProfileOperations( String inServiceParam, String methodName, CrmQrcDto inCrmQrcDto )
        throws SOAPException
    {
        return getCrmQrcBusiness().customerProfileOperations( inServiceParam, methodName, inCrmQrcDto );
    }

    @Override
    public CrmQrcDto workFlowOperations( String inServiceParam, String methodName, CrmQrcDto inCrmQrcDto )
        throws SOAPException
    {
        return getCrmQrcBusiness().workFlowOperations( inServiceParam, methodName, inCrmQrcDto );
    }

    @Override
    public CrmMassOutageDto massOutageOperations( String inService, CrmMassOutageDto inCrmMassOutageDto )
        throws SOAPException
    {
        return getCrmQrcBusiness().massOutageOperations( inService, inCrmMassOutageDto );
    }

    @Override
    public CrmQrcDto ticketOperations( String inServiceParam, String inCrmParam, CrmQrcDto inCrmQrcDto )
        throws SOAPException
    {
        LOGGER.info( "Calling EJB ticket operatino" );
        return getTicketManager().ticketOperations( inServiceParam, inCrmParam, inCrmQrcDto );
    }
}
