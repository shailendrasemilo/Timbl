package com.np.tele.crm.faultRepair;

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
import com.np.tele.crm.faultRepair.businessmgr.IQRCFaultRepairMgr;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_QRC_FAULT_REPAIR_SERVICE_EJB)
@Local(IFaultRepairServiceLocal.class)
@Remote(IFaultRepairServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.faultRepair.IQRCFaultRepairService")
public class QRCFaultRepairService
    implements IFaultRepairServiceLocal, IFaultRepairServiceRemote
{
    private static final Logger LOGGER         = Logger.getLogger( QRCFaultRepairService.class );
    private BeanFactory         factory        = null;
    private IQRCFaultRepairMgr  faultRepairMgr = null;

    public IQRCFaultRepairMgr getFaultRepairMgr()
    {
        return faultRepairMgr;
    }

    public void setFaultRepairMgr( IQRCFaultRepairMgr inFaultRepairMgr )
    {
        faultRepairMgr = inFaultRepairMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "QRCFaultRepairService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_EXTERNAL_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setFaultRepairMgr( (IQRCFaultRepairMgr) factory.getBean( "qrcfaultRepairMgr" ) );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing QRCFaultRepairService", ex );
            throw new IllegalStateException( ex );
        }
        finally
        {
            IOUtils.closeQuietly( inputStream );
        }
    }

    @Override
    public void qrcFaultRepair( String inTo, String inMessage, String inOprator, String inSender, String inDate )
        throws SOAPException
    {
        getFaultRepairMgr().qrcFaultRepair( inTo, inMessage, inOprator, inSender, inDate );
    }
}
