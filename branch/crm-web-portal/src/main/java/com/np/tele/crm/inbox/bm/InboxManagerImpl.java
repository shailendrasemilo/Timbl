package com.np.tele.crm.inbox.bm;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.inbox.form.InboxForm;
import com.np.tele.crm.pojos.AllInboxPojo;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.utils.SortingComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class InboxManagerImpl
    implements IInboxManager
{
    private static final Logger LOGGER          = Logger.getLogger( InboxManagerImpl.class );
    private CRMConfigService    crmConfigClient = null;

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    @Override
    public ConfigDto getInboxData( ConfigDto inConfigDto )
    {
        inConfigDto.setLeadStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        inConfigDto.setInboxStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        try
        {
            inConfigDto = getCrmConfigClient().getInbox( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto changeInboxBin( ConfigDto inConfigDto )
    {
        LOGGER.info( "Inside InboxManagerImpl : changeInboxBin" );
        try
        {
            inConfigDto = getCrmConfigClient().changeInboxBin( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception : ", ex );
        }
        LOGGER.info( "Inbox Bin Stage has Changed, From : " + inConfigDto.getFromStage() + " To : "
                + inConfigDto.getTostage() );
        LOGGER.info( "Inbox Bin User has Changed, From : " + inConfigDto.getFromUserId() + " To : "
                + inConfigDto.getToUserId() );
        return inConfigDto;
    }

    @Override
    public ConfigDto getAppointments( ConfigDto inConfigDto )
    {
        try
        {
            inConfigDto = getCrmConfigClient().getMappingIdtByAppointMents( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto getRemarks( ConfigDto inConfigDto )
    {
        try
        {
            inConfigDto = getCrmConfigClient().geMappingIdByRemarks( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public ConfigDto getLeadCloseReason()
    {
        ConfigDto configDto = new ConfigDto();
        try
        {
            configDto = getCrmConfigClient().configOperations( ServiceParameter.LIST.getParameter(),
                                                               CRMParameter.LMS_CLOSE_REASON.getParameter(), configDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Lead Close Reasons SOAP Exception : ", ex );
        }
        return configDto;
    }

    /*@Override
    public ConfigDto changeInboxBinForCRF( ConfigDto inConfigDto )
    {
        inConfigDto.setRequestType( CRMRequestType.CRF.getRequestCode() );
        try
        {
            inConfigDto = getCrmConfigClient().changeInboxBin( inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "SOAP Exception For CRF: ", ex );
        }
        LOGGER.info( "Inbox Bin CRF Stage has Changed, From : " + inConfigDto.getFromStage() + " To : "
                + inConfigDto.getTostage() );
        LOGGER.info( "Inbox Bin User has Changed, From : " + inConfigDto.getFromUserId() + " To : "
                + inConfigDto.getToUserId() );
        return inConfigDto;
    }
    */
    @Override
    public ConfigDto getActivityLogs( ConfigDto inConfigDto )
    {
        try
        {
            inConfigDto = getCrmConfigClient().auditLogOperation( ServiceParameter.VIEW.getParameter(), inConfigDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Get Lead Close Reasons SOAP Exception : ", ex );
        }
        return inConfigDto;
    }

    @Override
    public InboxForm createAllInbox( InboxForm inInboxForm )
    {
        try
        {
            String customerName = null;
            inInboxForm.setAllInboxList( new ArrayList<AllInboxPojo>() );
            inInboxForm.setAllGroupInboxList( new ArrayList<AllInboxPojo>() );
            if ( CommonValidator.isValidCollection( inInboxForm.getCrfInboxList() ) )
            {
                synchronized ( inInboxForm.getCrfInboxList() )
                {
                    for ( CrmCustomerDetailsPojo crmCustomerDetailsPojo : inInboxForm.getCrfInboxList() )
                    {
                        AllInboxPojo allInboxPojo = new AllInboxPojo();
                        allInboxPojo.setRequestType( CRMRequestType.CAF.getRequestDesc() );
                        allInboxPojo.setNetworkPartner( "" + crmCustomerDetailsPojo.getNpId() );
                        allInboxPojo.setType( CRMRequestType.CAF.getRequestCode() );
                        allInboxPojo.setLeadIdCrfIdTicketId( String.valueOf( crmCustomerDetailsPojo.getCrfId() ) );
                        allInboxPojo.setContactNumber( crmCustomerDetailsPojo.getRmn() );
                        customerName = crmCustomerDetailsPojo.getCustFname();
                        if ( StringUtils.isNotEmpty( crmCustomerDetailsPojo.getCustLname() ) )
                        {
                            customerName += IAppConstants.SPACE + crmCustomerDetailsPojo.getCustLname();
                        }
                        allInboxPojo.setCustomerName( customerName );
                        allInboxPojo.setDate( crmCustomerDetailsPojo.getCreatedTime() );
                        allInboxPojo.setProduct( crmCustomerDetailsPojo.getProduct() );
                        allInboxPojo.setStage( crmCustomerDetailsPojo.getCrfStage() );
                        allInboxPojo.setLmsIdCrfRecordId( crmCustomerDetailsPojo.getRecordId() );
                        allInboxPojo.setUnRead( crmCustomerDetailsPojo.isUnRead() );
                        allInboxPojo.setInboxId( crmCustomerDetailsPojo.getInboxId() );
                        allInboxPojo.setPreviousStage( crmCustomerDetailsPojo.getCrfPreviousStage() );
                        allInboxPojo.setLastModifiedTime( crmCustomerDetailsPojo.getLastModifiedTime() );
                        if ( StringUtils.isEmpty( crmCustomerDetailsPojo.getLastModifiedBy() ) )
                        {
                            allInboxPojo.setPreviousStageOwner( crmCustomerDetailsPojo.getCreatedBy() );
                        }
                        else
                            allInboxPojo.setPreviousStageOwner( crmCustomerDetailsPojo.getLastModifiedBy() );
                        inInboxForm.getAllInboxList().add( allInboxPojo );
                    }
                }
            }
            if ( CommonValidator.isValidCollection( inInboxForm.getLmsInboxList() ) )
            {
                synchronized ( inInboxForm.getLmsInboxList() )
                {
                    for ( LmsPojo lmsPojo : inInboxForm.getLmsInboxList() )
                    {
                        AllInboxPojo allInboxPojo = new AllInboxPojo();
                        allInboxPojo.setType( CRMRequestType.LEAD.getRequestCode() );
                        allInboxPojo.setRequestType( CRMRequestType.LEAD.getRequestDesc() );
                        allInboxPojo.setLeadIdCrfIdTicketId( lmsPojo.getLeadId() );
                        allInboxPojo.setContactNumber( lmsPojo.getContactNumber() );
                        allInboxPojo.setCustomerName( lmsPojo.getCustomerName() );
                        allInboxPojo.setDate( lmsPojo.getCreatedTime() );
                        allInboxPojo.setProduct( lmsPojo.getProduct() );
                        allInboxPojo.setStage( lmsPojo.getLmsStage() );
                        allInboxPojo.setLmsIdCrfRecordId( lmsPojo.getLmsId() );
                        allInboxPojo.setUnRead( lmsPojo.isUnRead() );
                        allInboxPojo.setInboxId( lmsPojo.getInboxId() );
                        allInboxPojo.setPreviousStage( lmsPojo.getPreviousStage() );
                        allInboxPojo.setFeasibility( lmsPojo.getFeasibility() );
                        allInboxPojo.setLastModifiedTime( lmsPojo.getLastModifiedTime() );
                        if ( StringUtils.isEmpty( lmsPojo.getLastModifiedBy() ) )
                        {
                            allInboxPojo.setPreviousStageOwner( lmsPojo.getCreatedBy() );
                        }
                        else
                            allInboxPojo.setPreviousStageOwner( lmsPojo.getLastModifiedBy() );
                        inInboxForm.getAllInboxList().add( allInboxPojo );
                    }
                }
            }
            if ( CommonValidator.isValidCollection( inInboxForm.getQrcInboxList() ) )
            {
                synchronized ( inInboxForm.getQrcInboxList() )
                {
                    for ( CrmSrTicketsPojo crmSrTicketsPojo : inInboxForm.getQrcInboxList() )
                    {
                        AllInboxPojo allInboxPojo = new AllInboxPojo();
                        allInboxPojo.setType( CRMRequestType.QRC.getRequestCode() );
                        allInboxPojo.setExpectedSlaTime( crmSrTicketsPojo.getExpectedSLATime() );
                        allInboxPojo.setStatus( crmSrTicketsPojo.getStatus() );
                        allInboxPojo.setRequestType( crmSrTicketsPojo.getQrcType() );
                        if ( StringUtils.isValidObj( crmSrTicketsPojo.getCustomerDetailsPojo() ) )
                        {
                            allInboxPojo.setNetworkPartner( crmSrTicketsPojo.getCustomerDetailsPojo().getNpId() + "" );
                            allInboxPojo.setContactNumber( crmSrTicketsPojo.getCustomerDetailsPojo().getCustMobileNo() );
                            customerName = crmSrTicketsPojo.getCustomerDetailsPojo().getCustFname();
                            if ( StringUtils.isNotEmpty( crmSrTicketsPojo.getCustomerDetailsPojo().getCustLname() ) )
                            {
                                customerName += IAppConstants.SPACE
                                        + crmSrTicketsPojo.getCustomerDetailsPojo().getCustLname();
                            }
                            if ( StringUtils.isValidObj( crmSrTicketsPojo.getCustomerDetailsPojo() )
                                    && StringUtils.isNotBlank( crmSrTicketsPojo.getCustomerDetailsPojo().getProduct() ) )
                            {
                                allInboxPojo.setProduct( crmSrTicketsPojo.getCustomerDetailsPojo().getProduct() );
                            }
                            else
                            {
                                allInboxPojo.setProduct( crmSrTicketsPojo.getLmsPojo().getProduct() );
                            }
                        }
                        LOGGER.info( "Subb  Sub Cat:****:: " + crmSrTicketsPojo.getQrcSubSubCategory() );
                        allInboxPojo.setSubSubCategory( crmSrTicketsPojo.getQrcSubSubCategory() );
                        allInboxPojo.setLeadIdCrfIdTicketId( String.valueOf( crmSrTicketsPojo.getSrId() ) );
                        allInboxPojo.setCustomerName( customerName );
                        allInboxPojo.setDate( crmSrTicketsPojo.getCreatedTime() );
                        allInboxPojo.setStage( String.valueOf( crmSrTicketsPojo.getFunctionalbinId() ) );
                        allInboxPojo.setLmsIdCrfRecordId( crmSrTicketsPojo.getTicketId() );
                        allInboxPojo.setUnRead( crmSrTicketsPojo.isUnRead() );
                        allInboxPojo.setInboxId( crmSrTicketsPojo.getInboxId() );
                        allInboxPojo.setPreviousStage( crmSrTicketsPojo.getPreviousStage() );
                        allInboxPojo.setLastModifiedTime( crmSrTicketsPojo.getLastModifiedTime() );
                        if ( StringUtils.isEmpty( crmSrTicketsPojo.getLastModifiedBy() ) )
                        {
                            allInboxPojo.setPreviousStageOwner( crmSrTicketsPojo.getCreatedBy() );
                        }
                        else
                            allInboxPojo.setPreviousStageOwner( crmSrTicketsPojo.getLastModifiedBy() );
                        inInboxForm.getAllInboxList().add( allInboxPojo );
                    }
                }
            }
            if ( CommonValidator.isValidCollection( inInboxForm.getCrfGroupInboxList() ) )
            {
                synchronized ( inInboxForm.getCrfGroupInboxList() )
                {
                    for ( CrmCustomerDetailsPojo crmCustomerDetailsGroupPojo : inInboxForm.getCrfGroupInboxList() )
                    {
                        AllInboxPojo allInboxPojo = new AllInboxPojo();
                        allInboxPojo.setType( CRMRequestType.CAF.getRequestCode() );
                        allInboxPojo.setRequestType( CRMRequestType.CAF.getRequestDesc() );
                        allInboxPojo.setNetworkPartner( "" + crmCustomerDetailsGroupPojo.getNpId() );
                        allInboxPojo.setLeadIdCrfIdTicketId( String.valueOf( crmCustomerDetailsGroupPojo.getCrfId() ) );
                        allInboxPojo.setContactNumber( crmCustomerDetailsGroupPojo.getRmn() );
                        customerName = crmCustomerDetailsGroupPojo.getCustFname();
                        if ( StringUtils.isNotEmpty( crmCustomerDetailsGroupPojo.getCustLname() ) )
                        {
                            customerName += IAppConstants.SPACE + crmCustomerDetailsGroupPojo.getCustLname();
                        }
                        allInboxPojo.setCustomerName( customerName );
                        allInboxPojo.setDate( crmCustomerDetailsGroupPojo.getCreatedTime() );
                        allInboxPojo.setProduct( crmCustomerDetailsGroupPojo.getProduct() );
                        allInboxPojo.setStage( crmCustomerDetailsGroupPojo.getCrfStage() );
                        allInboxPojo.setLmsIdCrfRecordId( crmCustomerDetailsGroupPojo.getRecordId() );
                        allInboxPojo.setPreviousStage( crmCustomerDetailsGroupPojo.getCrfPreviousStage() );
                        if ( StringUtils.isEmpty( crmCustomerDetailsGroupPojo.getLastModifiedBy() ) )
                        {
                            allInboxPojo.setPreviousStageOwner( crmCustomerDetailsGroupPojo.getCreatedBy() );
                        }
                        else
                            allInboxPojo.setPreviousStageOwner( crmCustomerDetailsGroupPojo.getLastModifiedBy() );
                        inInboxForm.getAllGroupInboxList().add( allInboxPojo );
                    }
                }
            }
            if ( CommonValidator.isValidCollection( inInboxForm.getLmsGroupInboxList() ) )
            {
                synchronized ( inInboxForm.getLmsGroupInboxList() )
                {
                    for ( LmsPojo lmsGroupPojo : inInboxForm.getLmsGroupInboxList() )
                    {
                        AllInboxPojo allInboxPojo = new AllInboxPojo();
                        allInboxPojo.setType( CRMRequestType.LEAD.getRequestCode() );
                        allInboxPojo.setRequestType( CRMRequestType.LEAD.getRequestDesc() );
                        allInboxPojo.setLeadIdCrfIdTicketId( lmsGroupPojo.getLeadId() );
                        allInboxPojo.setContactNumber( lmsGroupPojo.getContactNumber() );
                        allInboxPojo.setCustomerName( lmsGroupPojo.getCustomerName() );
                        allInboxPojo.setDate( lmsGroupPojo.getCreatedTime() );
                        allInboxPojo.setProduct( lmsGroupPojo.getProduct() );
                        allInboxPojo.setStage( lmsGroupPojo.getLmsStage() );
                        allInboxPojo.setLmsIdCrfRecordId( lmsGroupPojo.getLmsId() );
                        allInboxPojo.setPreviousStage( lmsGroupPojo.getPreviousStage() );
                        allInboxPojo.setFeasibility( lmsGroupPojo.getFeasibility() );
                        if ( StringUtils.isEmpty( lmsGroupPojo.getLastModifiedBy() ) )
                        {
                            allInboxPojo.setPreviousStageOwner( lmsGroupPojo.getCreatedBy() );
                        }
                        else
                            allInboxPojo.setPreviousStageOwner( lmsGroupPojo.getLastModifiedBy() );
                        inInboxForm.getAllGroupInboxList().add( allInboxPojo );
                    }
                }
            }
            if ( CommonValidator.isValidCollection( inInboxForm.getQrcGroupInboxList() ) )
            {
                synchronized ( inInboxForm.getQrcGroupInboxList() )
                {
                    for ( CrmSrTicketsPojo crmSrTicketsGroupPojo : inInboxForm.getQrcGroupInboxList() )
                    {
                        AllInboxPojo allInboxPojo = new AllInboxPojo();
                        allInboxPojo.setType( CRMRequestType.QRC.getRequestCode() );
                        allInboxPojo.setExpectedSlaTime( crmSrTicketsGroupPojo.getExpectedSLATime() );
                        allInboxPojo.setStatus( crmSrTicketsGroupPojo.getStatus() );
                        allInboxPojo.setRequestType( crmSrTicketsGroupPojo.getQrcType() );
                        if ( StringUtils.isValidObj( crmSrTicketsGroupPojo.getCustomerDetailsPojo() )
                                && StringUtils.isNotEmpty( crmSrTicketsGroupPojo.getCustomerDetailsPojo().getNpId()
                                        + "" ) )
                        {
                            allInboxPojo.setNetworkPartner( crmSrTicketsGroupPojo.getCustomerDetailsPojo().getNpId()
                                    + "" );
                        }
                        allInboxPojo.setSubSubCategory( crmSrTicketsGroupPojo.getQrcSubSubCategory() );
                        allInboxPojo.setLeadIdCrfIdTicketId( String.valueOf( crmSrTicketsGroupPojo.getSrId() ) );
                        if ( StringUtils.isValidObj( crmSrTicketsGroupPojo.getCustomerDetailsPojo() )
                                && StringUtils.isNotEmpty( crmSrTicketsGroupPojo.getCustomerDetailsPojo()
                                        .getCustMobileNo() + "" ) )
                        {
                            allInboxPojo.setContactNumber( crmSrTicketsGroupPojo.getCustomerDetailsPojo()
                                    .getCustMobileNo() );
                        }
                        if ( StringUtils.isValidObj( crmSrTicketsGroupPojo.getCustomerDetailsPojo() )
                                && StringUtils.isNotEmpty( crmSrTicketsGroupPojo.getCustomerDetailsPojo()
                                        .getCustFname() ) )
                        {
                            customerName = crmSrTicketsGroupPojo.getCustomerDetailsPojo().getCustFname();
                            if ( StringUtils.isNotEmpty( crmSrTicketsGroupPojo.getCustomerDetailsPojo().getCustLname() ) )
                            {
                                customerName += IAppConstants.SPACE
                                        + crmSrTicketsGroupPojo.getCustomerDetailsPojo().getCustLname();
                            }
                            allInboxPojo.setCustomerName( customerName );
                        }
                        allInboxPojo.setDate( crmSrTicketsGroupPojo.getCreatedTime() );
                        if ( StringUtils.isValidObj( crmSrTicketsGroupPojo.getCustomerDetailsPojo() )
                                && StringUtils.isNotEmpty( crmSrTicketsGroupPojo.getCustomerDetailsPojo().getProduct() ) )
                        {
                            allInboxPojo.setProduct( crmSrTicketsGroupPojo.getCustomerDetailsPojo().getProduct() );
                        }
                        else
                        {
                            allInboxPojo.setProduct( crmSrTicketsGroupPojo.getLmsPojo().getProduct() );
                        }
                        allInboxPojo.setStage( String.valueOf( crmSrTicketsGroupPojo.getFunctionalbinId() ) );
                        allInboxPojo.setLmsIdCrfRecordId( crmSrTicketsGroupPojo.getTicketId() );
                        allInboxPojo.setPreviousStage( crmSrTicketsGroupPojo.getPreviousStage() );
                        if ( StringUtils.isEmpty( crmSrTicketsGroupPojo.getLastModifiedBy() ) )
                        {
                            allInboxPojo.setPreviousStageOwner( crmSrTicketsGroupPojo.getCreatedBy() );
                        }
                        else
                            allInboxPojo.setPreviousStageOwner( crmSrTicketsGroupPojo.getLastModifiedBy() );
                        inInboxForm.getAllGroupInboxList().add( allInboxPojo );
                    }
                }
            }
            SortingComparator<AllInboxPojo> sorter = new SortingComparator<AllInboxPojo>( "date" );
            Collections.sort( inInboxForm.getAllInboxList(), sorter );
            Collections.sort( inInboxForm.getAllGroupInboxList(), sorter );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "createAllInbox Exception : ", ex );
        }
        return inInboxForm;
    }
}
