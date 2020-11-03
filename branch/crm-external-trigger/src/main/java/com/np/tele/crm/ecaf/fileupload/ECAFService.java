package com.np.tele.crm.ecaf.fileupload;

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
import com.np.tele.crm.dto.ECafDto;
import com.np.tele.crm.ecaf.businessmgr.IECafMgr;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_ECAF_SERVICE_EJB)
@Local(IECAFServiceLocal.class)
@Remote(IECAFServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.ecaf.fileupload.IECAFService")
public class ECAFService
    implements IECAFServiceLocal, IECAFServiceRemote
{
    private static final Logger LOGGER    = Logger.getLogger( ECAFService.class );
    private BeanFactory         factory   = null;
    private IECafMgr            eCafMgr   = null;
    @Resource
    private WebServiceContext   wsContext = null;

    public IECafMgr geteCafMgr()
    {
        return eCafMgr;
    }

    public void seteCafMgr( IECafMgr inECafMgr )
    {
        eCafMgr = inECafMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "ECAFService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_EXTERNAL_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "ECafService Bean Factory Initialized" );
                seteCafMgr( (IECafMgr) factory.getBean( "eCafMgr" ) );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing ECafService", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
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
    public ECafDto saveECAF( String inAuthUsername, String inAuthPassword, ECafDto inECafDto )
        throws SOAPException
    {
        inECafDto.setClientIp( getClientIP() );
        inECafDto.setAuthUsername( inAuthUsername );
        inECafDto.setAuthPassword( inAuthPassword );
        return geteCafMgr().saveECAF( inECafDto );
    }
}
