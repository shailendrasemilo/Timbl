package com.np.tele.crm.config.service;

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

import com.np.tele.crm.config.businessmgr.ICrmConfigBusiness;
import com.np.tele.crm.config.service.ICrmConfigServiceLocal;
import com.np.tele.crm.config.service.ICrmConfigServiceRemote;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.dto.ConfigDto;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_CONFIG_SERVICE_EJB)
@Local(ICrmConfigServiceLocal.class)
@Remote(ICrmConfigServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.config.service.ICrmConfigService")
public class CRMConfigService
    implements ICrmConfigServiceLocal, ICrmConfigServiceRemote
{
    private static final Logger LOGGER            = Logger.getLogger( CRMConfigService.class );
    private BeanFactory         factory           = null;
    private ICrmConfigBusiness  crmConfigBusiness = null;

    public ICrmConfigBusiness getCrmConfigBusiness()
    {
        return crmConfigBusiness;
    }

    public void setCrmConfigBusiness( ICrmConfigBusiness inCrmConfigBusiness )
    {
        crmConfigBusiness = inCrmConfigBusiness;
    }

    @Override
    public ConfigDto configOperations( String inServiceParam, String inConfigParam, ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().configOperations( inServiceParam, inConfigParam, inConfigDto );
    }

    @Override
    public ConfigDto getInbox( ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().getInbox( inConfigDto );
    }

    @Override
    public ConfigDto getMappingIdtByAppointMents( ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().getMappingIdtByAppointMents( inConfigDto );
    }

    @Override
    public ConfigDto geMappingIdByRemarks( ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().geMappingIdByRemarks( inConfigDto );
    }

    @Override
    public ConfigDto changeInboxBin( ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().changeInboxBin( inConfigDto );
    }

    @Override
    public ConfigDto saveAppointment( ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().saveAppointment( inConfigDto );
    }

    @Override
    public ConfigDto getMappedUsersByUser( ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().getMappedUsersByUser( inConfigDto );
    }

    @Override
    public ConfigDto auditLogOperation( String inOperationParam, ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().auditLogOperation( inOperationParam, inConfigDto );
    }

    @Override
    public ConfigDto customerActivityOps( String inOperationParam, ConfigDto inConfigDto )
        throws SOAPException
    {
        return getCrmConfigBusiness().customerActivityOps( inOperationParam, inConfigDto );
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "MasterService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_CONFIG_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                setCrmConfigBusiness( (ICrmConfigBusiness) factory.getBean( "crmConfigBusiness" ) );
                LOGGER.info( CRMConfigService.class.getSimpleName() + "-Bean Factory Initialized" );
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
}
