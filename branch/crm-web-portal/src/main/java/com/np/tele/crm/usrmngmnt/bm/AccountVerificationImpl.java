package com.np.tele.crm.usrmngmnt.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.service.client.CRMUserManagement;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.CrmuserDetailsDto;
import com.np.tele.crm.service.client.SOAPException_Exception;

public class AccountVerificationImpl
    implements IAccountVerification
{
    private static final Logger LOGGER = Logger.getLogger( AccountVerificationImpl.class );
    private CRMUserManagement   crmUserClient;

    public CRMUserManagement getCrmUserClient()
    {
        return crmUserClient;
    }

    public void setCrmUserClient( CRMUserManagement crmUserClient )
    {
        this.crmUserClient = crmUserClient;
    }

    @Override
    public CrmuserDetailsDto validateUser( String token )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        CrmUserPojo crmUsrPojo = null;
        try
        {
            crmuserDetailsDto = new CrmuserDetailsDto();
            crmUsrPojo = new CrmUserPojo();
            crmUsrPojo.setUserToken( token );
            crmuserDetailsDto.setCrmUserDetailsPojo( crmUsrPojo );
            crmuserDetailsDto = getCrmUserClient().userOperations( crmuserDetailsDto,
                                                                   CRMParameter.VERIFY_ACCOUNT.getParameter() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured in validate user", ex );
        }
        return crmuserDetailsDto;
    }

    @Override
    public CrmuserDetailsDto activateUser( long recordId )
    {
        CrmuserDetailsDto crmuserDetailsDto = null;
        CrmUserPojo crmUsrPojo = null;
        try
        {
            crmuserDetailsDto = new CrmuserDetailsDto();
            crmUsrPojo = new CrmUserPojo();
            crmUsrPojo.setRecordId( recordId );
            crmUsrPojo.setStatus( CRMStatusCode.NEW.getStatusCode() );
            crmuserDetailsDto.setCrmUserDetailsPojo( crmUsrPojo );
            crmuserDetailsDto = getCrmUserClient().userOperations( crmuserDetailsDto,
                                                                   CRMParameter.CHANGE_STATUS.getParameter() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured in activate user", ex );
        }
        return crmuserDetailsDto;
    }
}
