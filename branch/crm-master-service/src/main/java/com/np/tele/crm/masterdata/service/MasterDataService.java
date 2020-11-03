package com.np.tele.crm.masterdata.service;

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
import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.masterdata.businessmgr.IBussinessPartnerMgt;
import com.np.tele.crm.masterdata.businessmgr.IExternalApplicationMgr;
import com.np.tele.crm.masterdata.businessmgr.IMasterDataBusiness;
import com.np.tele.crm.masterdata.service.IMasterDataServiceLocal;
import com.np.tele.crm.masterdata.service.IMasterDataServiceRemote;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_MASTER_SERVICE_EJB)
@Local(IMasterDataServiceLocal.class)
@Remote(IMasterDataServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.masterdata.service.IMasterDataService")
public class MasterDataService
    implements IMasterDataServiceLocal, IMasterDataServiceRemote
{
    private static final Logger     LOGGER                 = Logger.getLogger( MasterDataService.class );
    private BeanFactory             factory                = null;
    private IMasterDataBusiness     masterDataBusiness     = null;
    private IExternalApplicationMgr externalApplicationMgr = null;
    private IBussinessPartnerMgt    bussinessPartnerMgt    = null;

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "MasterService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_MASTER_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                setMasterDataBusiness( (IMasterDataBusiness) factory.getBean( "masterDataBusiness" ) );
                setExternalApplicationMgr( (IExternalApplicationMgr) factory.getBean( "externalApplicationMgr" ) );
                setBussinessPartnerMgt( (IBussinessPartnerMgt) factory.getBean( "bussinessPartnerMgt" ) );
                LOGGER.info( "Bean Factory Initialized" );
                /*alertTemplateMgr = (IAlertsTemplateMgr) factory.getBean( "alertsTemplateMgr" );*/
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

    public IMasterDataBusiness getMasterDataBusiness()
    {
        return masterDataBusiness;
    }

    public void setMasterDataBusiness( IMasterDataBusiness masterDataBusiness )
    {
        this.masterDataBusiness = masterDataBusiness;
    }

    public IExternalApplicationMgr getExternalApplicationMgr()
    {
        return externalApplicationMgr;
    }

    public void setExternalApplicationMgr( IExternalApplicationMgr inExternalApplicationMgr )
    {
        externalApplicationMgr = inExternalApplicationMgr;
    }

    public IBussinessPartnerMgt getBussinessPartnerMgt()
    {
        return bussinessPartnerMgt;
    }

    public void setBussinessPartnerMgt( IBussinessPartnerMgt inBussinessPartnerMgt )
    {
        bussinessPartnerMgt = inBussinessPartnerMgt;
    }

    @Override
    public MasterDataDto getMasterData( MasterDataDto inMasterDataDto, String inPojoName )
        throws SOAPException
    {
        LOGGER.info( "IN GetMasterData Service" );
        return getMasterDataBusiness().getMasterData( inMasterDataDto, inPojoName );
    }

    @Override
    public MasterDataDto rolesGroupOperations( MasterDataDto inMasterDataDto, String inOperation )
        throws SOAPException
    {
        LOGGER.info( "In Roles Group Operation Method" );
        return getMasterDataBusiness().rolesGroupOperations( inMasterDataDto, inOperation );
    }

    @Override
    public MasterDataDto parameterGroupOperations( MasterDataDto inMasterDataDto, String inOperation )
        throws SOAPException
    {
        LOGGER.info( "In Parameter Group Operation Method" );
        return getMasterDataBusiness().parameterGroupOperations( inMasterDataDto, inOperation );
    }

    @Override
    public MasterDataDto externalApplication( String inServiceParam, MasterDataDto inMasterDataDto )
        throws SOAPException
    {
        return getExternalApplicationMgr().externalApplication( inServiceParam, inMasterDataDto );
    }

    @Override
    public MasterDataDto partnerOperations( String inServiceParam, MasterDataDto inMasterDataDto )
        throws SOAPException
    {
        return getBussinessPartnerMgt().bussinessPartnerMgt( inServiceParam, inMasterDataDto );
    }

    @Override
    public MasterDataDto masterOperations( String inServiceParam, String inMasterType, MasterDataDto inMasterDataDto )
        throws SOAPException
    {
        LOGGER.info( "In Master Operation Method" );
        return getMasterDataBusiness().masterOperation( inServiceParam,inMasterType,inMasterDataDto);
    }
}
