package com.np.tele.crm.lms.service;

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
import com.np.tele.crm.dto.LMSDto;
import com.np.tele.crm.lms.businessmgr.ILMSOperationMgr;
import com.np.tele.crm.lms.service.ILMSServiceLocal;
import com.np.tele.crm.lms.service.ILMSServiceRemote;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_LMS_SERVICE_EJB)
@Local(ILMSServiceLocal.class)
@Remote(ILMSServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.lms.service.ILMSService")
public class LMSService
    implements ILMSServiceRemote, ILMSServiceLocal
{
    private BeanFactory         factory         = null;
    private static final Logger LOGGER          = Logger.getLogger( LMSService.class );
    private ILMSOperationMgr    lmsOperationMgr = null;

    public ILMSOperationMgr getLmsOperationMgr()
    {
        return lmsOperationMgr;
    }

    public void setLmsOperationMgr( ILMSOperationMgr lmsOperationMgr )
    {
        this.lmsOperationMgr = lmsOperationMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "LMSService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_LMS_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setLmsOperationMgr( (ILMSOperationMgr) factory.getBean( "lmsOperationMgr" ) );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing GISService", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    @Override
    public LMSDto lmsOperation( String inOperationParam, LMSDto inLMSDto )
        throws SOAPException
    {
        return getLmsOperationMgr().lmsOperation( inOperationParam, inLMSDto );
    }
}
