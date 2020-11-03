package com.np.tele.crm.finance.service;

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
import com.np.tele.crm.dto.CrmFinanceDto;
import com.np.tele.crm.finance.businessmgr.ICRMFinanceBusiness;
import com.np.tele.crm.finance.service.ICrmFinanceServiceLocal;
import com.np.tele.crm.finance.service.ICrmFinanceServiceRemote;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_CRM_FINANCE_SERVICE_EJB)
@Local(ICrmFinanceServiceLocal.class)
@Remote(ICrmFinanceServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.finance.service.ICrmFinanceService")
public class CrmFinanceService
    implements ICrmFinanceServiceLocal, ICrmFinanceServiceRemote
{
    private static final Logger LOGGER             = Logger.getLogger( CrmFinanceService.class );
    private BeanFactory         factory            = null;
    private ICRMFinanceBusiness crmFinanceBusiness = null;

    public ICRMFinanceBusiness getCrmFinanceBusiness()
    {
        return crmFinanceBusiness;
    }

    public void setCrmFinanceBusiness( ICRMFinanceBusiness inCrmFinanceBusiness )
    {
        crmFinanceBusiness = inCrmFinanceBusiness;
    }

    @Override
    public CrmFinanceDto trackPayment( final String inServiceParam,
                                       final String inCrmParam,
                                       final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        return getCrmFinanceBusiness().trackPayment( inServiceParam, inCrmParam, inCrmFinanceDto );
    }

    @Override
    public CrmFinanceDto postPayment( final String inServiceParam,
                                      final String inCrmParam,
                                      final CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        return getCrmFinanceBusiness().postPayment( inServiceParam, inCrmParam, inCrmFinanceDto );
    }

    @Override
    public CrmFinanceDto pgwOperations( String inServiceParam, String inCrmParam, CrmFinanceDto inCrmFinanceDto )
        throws SOAPException
    {
        return getCrmFinanceBusiness().pgwOperations( inServiceParam, inCrmParam, inCrmFinanceDto );
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "CrmFinanceService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_FINANCE_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                setCrmFinanceBusiness( (ICRMFinanceBusiness) factory.getBean( "crmFinanceBusiness" ) );
                LOGGER.info( CrmFinanceService.class.getSimpleName() + "-Bean Factory Initialized" );
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
