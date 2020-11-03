package com.np.tele.crm.usrmngmnt.service;

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
import com.np.tele.crm.dto.CrmuserDetailsDto;
import com.np.tele.crm.usrmngmnt.businessmgr.IUserManagementBusiness;
import com.np.tele.crm.usrmngmnt.service.IUserManagementServiceLocal;
import com.np.tele.crm.usrmngmnt.service.IUserManagementServiceRemote;
import com.np.tele.crm.utils.FileUtils;

@Singleton(name = IGlobalConstants.BEAN_NAME_USER_MANAGEMENT_EJB)
@Local(IUserManagementServiceLocal.class)
@Remote(IUserManagementServiceRemote.class)
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
@WebService(endpointInterface = "com.np.tele.crm.usrmngmnt.service.IUserManagementService")
public class UserManagementService
    implements IUserManagementServiceLocal, IUserManagementServiceRemote
{
    private static final Logger     LOGGER                 = Logger.getLogger( UserManagementService.class );
    private BeanFactory             factory                = null;
    private IUserManagementBusiness userManagementBusiness = null;

    @Override
    public CrmuserDetailsDto loginAuthentication( CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException
    {
        return getUserManagementBusiness().loginAuthentication( inCrmuserDetailsDto );
    }

    public IUserManagementBusiness getUserManagementBusiness()
    {
        return userManagementBusiness;
    }

    public void setUserManagementBusiness( IUserManagementBusiness userManagementBusiness )
    {
        this.userManagementBusiness = userManagementBusiness;
    }

    @PostConstruct
    public void init()
    {
        InputStream inputStream = null;
        try
        {
            if ( null == factory )
            {
                LOGGER.info( "AlertService PostConstruct" );
                inputStream = FileUtils.getInputStreamForFile( IGlobalConstants.CRM_USER_SPRING_BEAN_XML );
                factory = new XmlBeanFactory( new ByteArrayResource( IOUtils.toByteArray( inputStream ) ) );
                setUserManagementBusiness( (IUserManagementBusiness) factory.getBean( "userManagementBusiness" ) );
                LOGGER.info( "Bean Factory Initialized" );
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

    /*@Override
    public boolean login( final String inUserName, final String inPassword )
        throws SOAPException
    {
        if ( "deepak".equals( inUserName ) )
        {
            IAlertsService alertsService = CRMServicesProxy.getInstance().getAlertsService( IGlobalConstants.REMOTE,
                                                                                            IGlobalConstants.APP );
            LOGGER.info( "Ejb Object :" + alertsService );
            return alertsService.sendEMail( inUserName );
        }
        return false;
    }*/
    @Override
    public CrmuserDetailsDto changePassword( CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException
    {
        return getUserManagementBusiness().changePassword( inCrmuserDetailsDto );
    }

    @Override
    public CrmuserDetailsDto userOperations( CrmuserDetailsDto inCrmuserDetailsDto, String inOperation )
        throws SOAPException
    {
        return getUserManagementBusiness().userOperation( inCrmuserDetailsDto, inOperation );
    }

    @Override
    public CrmuserDetailsDto searchUser( CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException
    {
        return getUserManagementBusiness().searchUser( inCrmuserDetailsDto );
    }

    @Override
    public CrmuserDetailsDto userMapping( CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException
    {
        return getUserManagementBusiness().userMapping( inCrmuserDetailsDto );
    }

    @Override
    public CrmuserDetailsDto getUserAssignAccess( String inUserID )
        throws SOAPException
    {
        return getUserManagementBusiness().getUserAssignAccess( inUserID );
    }

    @Override
    public CrmuserDetailsDto userEscalationAndWorkflowMapping( CrmuserDetailsDto inCrmuserDetailsDto, String inOperation )
        throws SOAPException
    {
        return getUserManagementBusiness().userEscalationAndWorkflowMapping( inCrmuserDetailsDto, inOperation );
    }

    @Override
    public CrmuserDetailsDto getUsersByParameter( CrmuserDetailsDto inCrmuserDetailsDto )
        throws SOAPException
    {
        return getUserManagementBusiness().getUsersByParameter( inCrmuserDetailsDto );
    }

    @Override
    public CrmuserDetailsDto getCustomerAssociations( String inUserId )
        throws SOAPException
    {
        return getUserManagementBusiness().getCustomerAssociations( inUserId );
    }
}
