package com.np.tele.crm.gis.service;

import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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
import com.np.tele.crm.dto.GISDto;
import com.np.tele.crm.gis.businessmgr.IGISOperationMgr;
import com.np.tele.crm.gis.service.IGISServiceLocal;
import com.np.tele.crm.gis.service.IGISServiceRemote;
import com.np.tele.crm.utils.FileUtils;
import com.np.tele.crm.utils.HibernateUtil;
@Startup
@Singleton(name = IGlobalConstants.BEAN_NAME_GIS_SERVICE_EJB)
@Local(IGISServiceLocal.class)
@Remote(IGISServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.gis.service.IGISService")
public class GISService
    implements IGISServiceRemote, IGISServiceLocal
{
    private BeanFactory         factory         = null;
    private static final Logger LOGGER          = Logger.getLogger( GISService.class );
    private IGISOperationMgr    gisOperationMgr = null;

    public IGISOperationMgr getGisOperationMgr()
    {
        return gisOperationMgr;
    }

    public void setGisOperationMgr( IGISOperationMgr inGisOperationMgr )
    {
        gisOperationMgr = inGisOperationMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "GISService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_GIS_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setGisOperationMgr( (IGISOperationMgr) factory.getBean( "gisOperationMgr" ) );
            }
            HibernateUtil.getSessionFactory();
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
    public GISDto gisOperation( String inServiceParam, String inOperationParam, GISDto inGisDto )
        throws SOAPException
    {
        return getGisOperationMgr().gisOperation( inServiceParam, inOperationParam, inGisDto );
    }
}
