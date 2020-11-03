package com.np.tele.crm.cap.service;

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

import com.np.tele.crm.cap.bussinessmgr.ICrmCapMgr;
import com.np.tele.crm.cap.service.ICrmCapServiceLocal;
import com.np.tele.crm.cap.service.ICrmCapServiceRemote;
import com.np.tele.crm.constants.IGlobalConstants;
import com.np.tele.crm.dto.CrmCapDto;
import com.np.tele.crm.dto.SelfcareDto;
import com.np.tele.crm.selfcare.bussinessmgr.ISelfcareMgr;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_CRM_CAP_SERVICE_EJB)
@Local(ICrmCapServiceLocal.class)
@Remote(ICrmCapServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.cap.service.ICrmCapService")
public class CrmCapService
    implements ICrmCapServiceLocal, ICrmCapServiceRemote
{
    private BeanFactory         beanFactory = null;
    private static final Logger LOGGER      = Logger.getLogger( CrmCapService.class );
    private ICrmCapMgr          crmCapMgr   = null;
    private ISelfcareMgr        selfcareMgr = null;

    public ICrmCapMgr getCrmCapMgr()
    {
        return crmCapMgr;
    }

    public void setCrmCapMgr( ICrmCapMgr inCrmCapMgr )
    {
        crmCapMgr = inCrmCapMgr;
    }

    public ISelfcareMgr getSelfcareMgr()
    {
        return selfcareMgr;
    }

    public void setSelfcareMgr( ISelfcareMgr inSelfcareMgr )
    {
        selfcareMgr = inSelfcareMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == beanFactory )
            {
                LOGGER.info( "CrmCapService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_CAP_SPRING_BEAN_XML );
                beanFactory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setCrmCapMgr( (ICrmCapMgr) beanFactory.getBean( "crmCapMgr" ) );
                setSelfcareMgr( (ISelfcareMgr) beanFactory.getBean( "selfcareMgr" ) );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing CrmCapService", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    //inOperationParam == create,modify del :: operation Name 
    @Override
    public CrmCapDto crmCapOperation( String inServiceParam, String inOperationParam, CrmCapDto inCrmCapDto )
        throws SOAPException
    {
        return getCrmCapMgr().crmCapOperation( inServiceParam, inOperationParam, inCrmCapDto );
    }

    @Override
    public SelfcareDto selfcareOperation( String inServiceParam, String inOperationParam, SelfcareDto inSelfcareDto )
        throws SOAPException
    {
        return getSelfcareMgr().selfcareOperation( inServiceParam, inOperationParam, inSelfcareDto );
    }
}
