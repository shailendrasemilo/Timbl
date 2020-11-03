package com.np.tele.crm.easyBill;

import java.io.InputStream;
import java.util.Date;

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
import com.np.tele.crm.dto.EasyBillPaymentDto;
import com.np.tele.crm.dto.EasyBillRequestDto;
import com.np.tele.crm.dto.EasyBillResponseDto;
import com.np.tele.crm.easyBill.businessmgr.ICrmEasyBillMgr;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_CRM_EASY_BILL_SERVICE_EJB)
@Local(ICrmEasyBillServiceLocal.class)
@Remote(ICrmEasyBillServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.easyBill.ICrmEasyBillService")
public class CrmEasyBillService
    implements ICrmEasyBillServiceLocal, ICrmEasyBillServiceRemote
{
    private static final Logger LOGGER          = Logger.getLogger( CrmEasyBillService.class );
    private BeanFactory         factory         = null;
    private ICrmEasyBillMgr     iCrmEasyBillMgr = null;
    @Resource
    private WebServiceContext   wsContext       = null;

    public ICrmEasyBillMgr getiCrmEasyBillMgr()
    {
        return iCrmEasyBillMgr;
    }

    public void setiCrmEasyBillMgr( ICrmEasyBillMgr inICrmEasyBillMgr )
    {
        iCrmEasyBillMgr = inICrmEasyBillMgr;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "CrmEasyBillService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_EXTERNAL_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                LOGGER.info( "Bean Factory Initialized" );
                setiCrmEasyBillMgr( (ICrmEasyBillMgr) factory.getBean( "iCrmEasyBillMgr" ) );
            }
        }
        catch ( Throwable ex )
        {
            LOGGER.error( "Exception while initializing CrmEasyBillService", ex );
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
    public EasyBillResponseDto getCustomerDetails( final String inAuthUsername,
                                                   final String inAuthPassword,
                                                   final long inRmn,
                                                   final String inCustomerID )
        throws SOAPException
    {
        EasyBillRequestDto easyBillRequestDto = new EasyBillRequestDto();
        easyBillRequestDto.setAuthUsername( inAuthUsername );
        easyBillRequestDto.setAuthPassword( inAuthPassword );
        easyBillRequestDto.setClientIp( getClientIP() );
        easyBillRequestDto.setCustomerId( inCustomerID );
        easyBillRequestDto.setRmn( inRmn == 0 ? null : inRmn + "" );
        return getiCrmEasyBillMgr().getCustomerDetails( easyBillRequestDto );
    }

    @Override
    public EasyBillResponseDto postPayment( final String inAuthUsername,
                                            final String inAuthPassword,
                                            final EasyBillPaymentDto inEasyBillPaymentDto )
        throws SOAPException
    {
        EasyBillRequestDto easyBillRequestDto = new EasyBillRequestDto();
        easyBillRequestDto.setAuthUsername( inAuthUsername );
        easyBillRequestDto.setAuthPassword( inAuthPassword );
        easyBillRequestDto.setClientIp( getClientIP() );
        return getiCrmEasyBillMgr().postPayment( easyBillRequestDto, inEasyBillPaymentDto );
    }

    @Override
    public EasyBillResponseDto getPaymentStatus( final String inAuthUsername,
                                                 final String inAuthPassword,
                                                 final Date inPaymentDate )
        throws SOAPException
    {
        EasyBillRequestDto easyBillRequestDto = new EasyBillRequestDto();
        easyBillRequestDto.setAuthUsername( inAuthUsername );
        easyBillRequestDto.setAuthPassword( inAuthPassword );
        easyBillRequestDto.setClientIp( getClientIP() );
        easyBillRequestDto.setPaymentDate( inPaymentDate );
        return getiCrmEasyBillMgr().getPaymentStatus( easyBillRequestDto );
    }

    @Override
    public EasyBillResponseDto getTransactionStatus( final String inAuthUsername,
                                                     final String inAuthPassword,                                                     
                                                     final String transactionId )
        throws SOAPException
    {
        EasyBillRequestDto easyBillRequestDto = new EasyBillRequestDto();
        easyBillRequestDto.setAuthUsername( inAuthUsername );
        easyBillRequestDto.setAuthPassword( inAuthPassword );
        easyBillRequestDto.setClientIp( getClientIP() );
        easyBillRequestDto.setTransactionId( transactionId );
        return getiCrmEasyBillMgr().getTransactionStatus( easyBillRequestDto );
    }
}
