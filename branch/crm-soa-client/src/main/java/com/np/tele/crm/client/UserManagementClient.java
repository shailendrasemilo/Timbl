package com.np.tele.crm.client;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.client.UserManagementServiceService;

@ClientOperations
public class UserManagementClient
    implements CRMUserManagement
{
    private static final Logger LOGGER = Logger.getLogger( UserManagementClient.class );

    @Override
    public CrmuserDetailsDto loginAuthentication( CrmuserDetailsDto inUserDto )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.loginAuthentication( inUserDto );
    }

    @Override
    public CrmuserDetailsDto changePassword( CrmuserDetailsDto inUserDto )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.changePassword( inUserDto );
    }

    @Override
    public CrmuserDetailsDto userOperations( CrmuserDetailsDto userDto, String inOperationName )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.userOperations( userDto, inOperationName );
    }

    @Override
    public CrmuserDetailsDto searchUser( CrmuserDetailsDto userDto )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.searchUser( userDto );
    }

    @Override
    public CrmuserDetailsDto userMapping( CrmuserDetailsDto userDto )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.userMapping( userDto );
    }

    @Override
    public CrmuserDetailsDto getUserAssignAccess( String userId )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.getUserAssignAccess( userId );
    }

    @Override
    public CrmuserDetailsDto userEscalationAndWorkflowMapping( CrmuserDetailsDto inUserDto, String inOperationName )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.userEscalationAndWorkflowMapping( inUserDto, inOperationName );
    }

    @Override
    public CrmuserDetailsDto getUsersByParameter( CrmuserDetailsDto userDto )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.getUsersByParameter( userDto );
    }

    @Override
    public CrmuserDetailsDto getCustomerAssociations( String inUserId )
        throws SOAPException_Exception
    {
        UserManagementServiceService usrManagementStub = new UserManagementServiceService();
        CRMUserManagement userManagementService = usrManagementStub.getUserManagementServicePort();
        return userManagementService.getCustomerAssociations( inUserId );
    }
    
    
}
