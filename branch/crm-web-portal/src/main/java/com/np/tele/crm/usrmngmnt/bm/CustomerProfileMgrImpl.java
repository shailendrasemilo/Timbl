package com.np.tele.crm.usrmngmnt.bm;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CustomerProfile;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.service.client.CRMLMSService;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCapService;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcService;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.LmsDto;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.StringUtils;

public class CustomerProfileMgrImpl
    implements ICustomerProfileMgr
{
    private static final Logger LOGGER           = Logger.getLogger( CustomerProfileMgrImpl.class );
    private CrmCapService       capServiceClient = null;
    private CRMLMSService       lmsServiceClient = null;
    private CrmQrcService       qrcServiceClient = null;

    public CrmCapService getCapServiceClient()
    {
        return capServiceClient;
    }

    public void setCapServiceClient( CrmCapService inCapServiceClient )
    {
        capServiceClient = inCapServiceClient;
    }

    public CRMLMSService getLmsServiceClient()
    {
        return lmsServiceClient;
    }

    public void setLmsServiceClient( CRMLMSService inLmsServiceClient )
    {
        lmsServiceClient = inLmsServiceClient;
    }

    public CrmQrcService getQrcServiceClient()
    {
        return qrcServiceClient;
    }

    public void setQrcServiceClient( CrmQrcService inQrcServiceClient )
    {
        qrcServiceClient = inQrcServiceClient;
    }

    @Override
    public CrmCapDto CRFCustomerProfilesearch( String inProfileSearchName, String inSearchValue )
    {
        CrmCapDto capDto = new CrmCapDto();
        CrmCustomerDetailsPojo crmCustomerDetailsPojo = new CrmCustomerDetailsPojo();
        CrmNetworkConfigurationsPojo networkConfigurationsPojo = new CrmNetworkConfigurationsPojo();
        CustomerProfile customerProfile = CustomerProfile.getProfiler( inProfileSearchName );
        switch ( customerProfile )
        {
            case REGISTERED_MOBILE:
                crmCustomerDetailsPojo.setRmn( Long.parseLong( inSearchValue ) );
                break;
            /*case REGISTERED_TELEPHONE:
                crmCustomerDetailsPojo.setRtn( Long.parseLong( inSearchValue ) );
                break;*/
            case CAF_ID:
                crmCustomerDetailsPojo.setCrfId( inSearchValue );
                break;
            case CUSTOMER_FIRST_NAME:
                crmCustomerDetailsPojo.setCustFname( inSearchValue );
                break;
            case CUSTOMER_LAST_NAME:
                crmCustomerDetailsPojo.setCustLname( inSearchValue );
                break;
            case CUSTOMER_ID:
                crmCustomerDetailsPojo.setCustomerId( inSearchValue );
            case CUSTOMER_MAC_ADDRESS:
                networkConfigurationsPojo.setFirstCpeMacId( inSearchValue );
        }
        LOGGER.info( "Profile Search Name : " + inProfileSearchName );
        try
        {
            if ( StringUtils.equals( inProfileSearchName, CustomerProfile.CUSTOMER_MAC_ADDRESS.getCode() ) )
            {
                capDto.setNetworkConfigurationsPojo( networkConfigurationsPojo );
                capDto = getCapServiceClient().crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                                CRMRequestType.MAC_ADDRESS.getRequestCode(), capDto );
            }
            else
            {
                capDto.setCustomerDetailsPojo( crmCustomerDetailsPojo );
                capDto = getCapServiceClient().crmCapOperation( ServiceParameter.LIST.getParameter(),
                                                                CRMRequestType.CAF.getRequestCode(), capDto );
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search customer profile", ex );
        }
        return capDto;
    }

    @Override
    public LmsDto leadCustomerProfileSearch( String inProfileSearchName, String inSearchValue )
    {
        LOGGER.info( "Customer Profile search criteria:: " + inProfileSearchName );
        LOGGER.info( "Search Value:: " + inSearchValue );
        LmsDto lmsDto = new LmsDto();
        LmsPojo lmsPojo = new LmsPojo();
        if ( CustomerProfile.LEAD_ID.getCode().equals( inProfileSearchName ) )
            lmsPojo.setLeadId( inSearchValue );
        else if ( CustomerProfile.LEAD_CUSTOMER_NAME.getCode().equals( inProfileSearchName ) )
            lmsPojo.setCustomerName( inSearchValue );
        else if ( CustomerProfile.LEAD_CONTACT_NO.getCode().equals( inProfileSearchName ) )
            lmsPojo.setContactNumber( Long.parseLong( inSearchValue ) );
        try
        {
            lmsDto.setLeadPojo( lmsPojo );
            lmsDto = getLmsServiceClient().lmsOperation( ServiceParameter.LIST.getParameter(), lmsDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "error occured calling client to service for search customer profile", ex );
        }
        return lmsDto;
    }

    @Override
    public CrmQrcDto ticketIDprofileSearch( String inProfileSearchName, String inSearchValue, CrmQrcDto inCrmQrcDto )
    {
        LOGGER.info( "Customer Profile search criteria:: " + inProfileSearchName );
        LOGGER.info( "Search Value:: " + inSearchValue );
        CrmSrTicketsPojo crmSrTicketsPojo = new CrmSrTicketsPojo();
        if ( CustomerProfile.TICKET_ID.getCode().equals( inProfileSearchName ) )
        {
            crmSrTicketsPojo.setSrId( inSearchValue );
            inCrmQrcDto.setCrmSrTicketsPojo( crmSrTicketsPojo );
        }
        try
        {
            inCrmQrcDto = getQrcServiceClient().qrcOperations( ServiceParameter.LIST.getParameter(),
                                                               CrmSrTicketsPojo.class.getSimpleName(), inCrmQrcDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error occured calling client to service for ticket search ", ex );
        }
        return inCrmQrcDto;
    }
}
