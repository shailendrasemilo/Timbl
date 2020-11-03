package com.np.tele.crm.report.service;

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
import com.np.tele.crm.dto.ReportDto;
import com.np.tele.crm.report.businessmgr.IReportOperationMgr;
import com.np.tele.crm.report.service.IReportServiceLocal;
import com.np.tele.crm.report.service.IReportServiceRemote;
import com.np.tele.crm.utils.FileUtils;
import com.np.tele.sla.businessmgr.ISLABusinessMgr;

@Singleton(name = IGlobalConstants.BEAN_NAME_REPORT_SERVICE_EJB)
@Local(IReportServiceLocal.class)
@Remote(IReportServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.report.service.ICRMReportService")
public class CRMReportService
    implements IReportServiceRemote, IReportServiceLocal
{
    private BeanFactory         factory             = null;
    private static final Logger LOGGER              = Logger.getLogger( CRMReportService.class );
    private IReportOperationMgr reportOperationMgr  = null;
    private ISLABusinessMgr     slaOperationMgrImpl = null;

    public ISLABusinessMgr getSlaOperationMgrImpl()
    {
        return slaOperationMgrImpl;
    }

    public void setSlaOperationMgrImpl( ISLABusinessMgr inSlaOperationMgrImpl )
    {
        slaOperationMgrImpl = inSlaOperationMgrImpl;
    }

    public IReportOperationMgr getReportOperationMgr()
    {
        return reportOperationMgr;
    }

    public void setReportOperationMgr( IReportOperationMgr reportOperationMgr )
    {
        this.reportOperationMgr = reportOperationMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "ReportService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_REPORT_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setReportOperationMgr( (IReportOperationMgr) factory.getBean( "reportOperationMgr" ) );
                setSlaOperationMgrImpl( (ISLABusinessMgr) factory.getBean( "slaOperationMgrImpl" ) );
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
    public ReportDto INAReportsOperation( String inOperationParam, ReportDto inReportDto )
        throws SOAPException
    {
        return getReportOperationMgr().INAReportsOperation( inOperationParam, inReportDto );
    }

    @Override
    public ReportDto QRCReportsOperation( String inOperationParam, ReportDto inReportDto )
        throws SOAPException
    {
        return getReportOperationMgr().QRCReportsOperation( inOperationParam, inReportDto );
    }

    @Override
    public ReportDto SLAOperations( String inServiceParam, String inCrmParam, ReportDto inReportDto )
        throws SOAPException
    {
        return getSlaOperationMgrImpl().SLAOperation( inServiceParam, inCrmParam, inReportDto );
    }
}
