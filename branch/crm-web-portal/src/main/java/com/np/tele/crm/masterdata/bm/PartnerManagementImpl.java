package com.np.tele.crm.masterdata.bm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMRequestType;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.forms.PartnerManagementForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CRMConfigService;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.comparators.PojoComparator;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class PartnerManagementImpl
    implements IPartnerManagement
{
    private static final Logger LOGGER          = Logger.getLogger( PartnerManagementImpl.class );
    private MasterData          masterData      = null;
    private CRMConfigService    crmConfigClient = null;

    public MasterData getMasterData()
    {
        return masterData;
    }

    public void setMasterData( MasterData inMasterData )
    {
        masterData = inMasterData;
    }

    public CRMConfigService getCrmConfigClient()
    {
        return crmConfigClient;
    }

    public void setCrmConfigClient( CRMConfigService inCrmConfigClient )
    {
        crmConfigClient = inCrmConfigClient;
    }

    @Override
    public MasterDataDto addPartner( PartnerManagementForm inPmForm, String inUserId )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        PartnerPojo partnerPojo = new PartnerPojo();
        try
        {
            LOGGER.info( "Boarded Date : " + inPmForm.getBoardedDate() );
            setPartnerPojoProperty( inPmForm, inUserId, partnerPojo );
            partnerPojo.setCreatedBy( inUserId );
            partnerPojo.setPartnerName( inPmForm.getPartnerName() );
            partnerPojo.setStrBoardedDate( inPmForm.getBoardedDate() );
            partnerPojo.getCrmPartnerDetailses().clear();
            partnerPojo.getCrmPartnerDetailses().addAll( getPartnerDetails( inPmForm, inUserId ) );
            if ( CommonValidator.isValidCollection( partnerPojo.getCrmPartnerDetailses() ) )
            {
                LOGGER.info( "Partner Details Pojo  List: " + partnerPojo.getCrmPartnerDetailses().size() );
                masterDataDto.setPartnerPojo( partnerPojo );
                LOGGER.info( "Going to Register Partner " );
                masterDataDto = getMasterData().partnerOperations( ServiceParameter.CREATE.getParameter(),
                                                                   masterDataDto );
            }
            else
            {
                masterDataDto.setStatusCode( CRMServiceCode.CRM069.getStatusCode() );
            }
        }
        catch ( Exception ex )
        {
            LOGGER.error( "error occured calling client to service for add partner management", ex );
        }
        return masterDataDto;
    }

    private List<CrmPartnerDetailsPojo> getPartnerDetails( PartnerManagementForm inPmForm, String inUserId )
    {
        List<CrmPartnerDetailsPojo> crmPartnerDetailsPojo = new ArrayList<CrmPartnerDetailsPojo>();
        CrmPartnerDetailsPojo partnerDetailsPojo = null;
        for ( ContentPojo content : inPmForm.getProducts() )
        {
            if ( content.isSelected() )
            {
                LOGGER.info( "Selected type of Bussiness:: " + content );
                for ( ContentPojo partnerType : inPmForm.getPartnerTypeList() )
                {
                    if ( partnerType.isSelected() )
                    {
                        LOGGER.info( "Selected type of Partner:: " + partnerType );
                        partnerDetailsPojo = new CrmPartnerDetailsPojo();
                        partnerDetailsPojo.setBussinessType( content.getContentValue() );
                        partnerDetailsPojo.setPartnerType( partnerType.getContentValue() );
                        partnerDetailsPojo.setCreatedBy( inUserId );
                        partnerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                        crmPartnerDetailsPojo.add( partnerDetailsPojo );
                    }
                }
            }
        }
        return crmPartnerDetailsPojo;
    }

    @Override
    public MasterDataDto modifyPartner( PartnerManagementForm inPmForm, String inUserId )
    {
        MasterDataDto masterDataDto = null;
        PartnerPojo partnerPojo = new PartnerPojo();;
        List<CrmPartnerDetailsPojo> partnerDetailsToSend = null;
        try
        {
            masterDataDto = new MasterDataDto();
            if ( inPmForm.getPartnerId() > 0 && StringUtils.isNotBlank( inPmForm.getCurrentStatus() ) )
            {
                partnerPojo.setPartnerId( inPmForm.getPartnerId() );
                int pojoIndex = inPmForm.getPartnerList().indexOf( partnerPojo );
                if ( pojoIndex >= 0 )
                {
                    partnerPojo = inPmForm.getPartnerList().get( pojoIndex );
                    partnerPojo.setCurrentStatus( inPmForm.getCurrentStatus() );
                    inPmForm.setCurrentStatus( null );
                    inPmForm.setPartnerId( 0 );
                }
            }
            else
            {
                //                PartnerPojo tempPojo = new PartnerPojo();
                //                partnerPojo = inPmForm.getPartnerPojo();
                List<CrmPartnerDetailsPojo> partnerOldDetails = inPmForm.getPartnerPojo().getCrmPartnerDetailses();
                org.apache.commons.beanutils.PropertyUtils.copyProperties( partnerPojo, inPmForm.getPartnerPojo() );
                setPartnerPojoProperty( inPmForm, inUserId, partnerPojo );
                PojoComparator<PartnerPojo> comparator = new PojoComparator<PartnerPojo>();
                if ( comparator.compare( partnerPojo, inPmForm.getPartnerPojo() ) == 0
                        && !partnerDetailsModified( getPartnerDetails( inPmForm, inUserId ), partnerOldDetails ) )
                {
                    masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                    return masterDataDto;
                }
                partnerDetailsToSend = getModifiedPartnerDetails( getPartnerDetails( inPmForm, inUserId ), inPmForm
                        .getPartnerPojo().getCrmPartnerDetailses(), inUserId );
                if ( StringUtils.isValidObj( partnerDetailsToSend ) )
                {
                    partnerPojo.getCrmPartnerDetailses().clear();
                    partnerPojo.getCrmPartnerDetailses().addAll( partnerDetailsToSend );
                    LOGGER.info( " Size of final partner detaails mapping size::"
                            + partnerPojo.getCrmPartnerDetailses().size() );
                }
            }
            partnerPojo.setLastModifiedBy( inUserId );
            masterDataDto.setPartnerPojo( partnerPojo );
            LOGGER.info( "Going to Update Partner " );
            masterDataDto = getMasterData().partnerOperations( ServiceParameter.CREATE.getParameter(), masterDataDto );
        }
        catch ( Exception ex )
        {
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            masterDataDto.setStatusDesc( CRMServiceCode.CRM999.getStatusDesc() );
            LOGGER.error( "Error occured calling client to service for modify partner management", ex );
        }
        return masterDataDto;
    }

    private List<CrmPartnerDetailsPojo> getModifiedPartnerDetails( List<CrmPartnerDetailsPojo> inActivePartnerDetails,
                                                                   List<CrmPartnerDetailsPojo> inPartnerOldDetails,
                                                                   String inUserId )
    {
        CrmPartnerDetailsPojo activeDetailsPojo = null;
        int index = -1;
        for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : inPartnerOldDetails )
        {
            activeDetailsPojo = null;
            index = inActivePartnerDetails.indexOf( crmPartnerDetailsPojo );
            if ( index > -1 )
            {
                activeDetailsPojo = inActivePartnerDetails.get( index );
            }
            if ( StringUtils.isValidObj( activeDetailsPojo ) )
            {
                if ( StringUtils.equals( CRMStatusCode.INACTIVE.getStatusCode(), crmPartnerDetailsPojo.getStatus() ) )
                {
                    crmPartnerDetailsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
                    crmPartnerDetailsPojo.setLastModifiedBy( inUserId );
                }
                else
                {
                    LOGGER.info( "Partner Detail Pojo not modified." + crmPartnerDetailsPojo );
                }
            }
            else
            {
                crmPartnerDetailsPojo.setStatus( CRMStatusCode.INACTIVE.getStatusCode() );
                crmPartnerDetailsPojo.setLastModifiedBy( inUserId );
            }
        }
        Set<CrmPartnerDetailsPojo> partnerDetailSet = new HashSet<CrmPartnerDetailsPojo>();
        partnerDetailSet.addAll( inPartnerOldDetails );
        partnerDetailSet.addAll( inActivePartnerDetails );
        return new ArrayList<CrmPartnerDetailsPojo>( partnerDetailSet );
    }

    private boolean partnerDetailsModified( List<CrmPartnerDetailsPojo> inPartnerDetails,
                                            List<CrmPartnerDetailsPojo> inPartnerOldDetails )
    {
        if ( inPartnerDetails.size() <= inPartnerOldDetails.size() )
        {
            if ( inPartnerOldDetails.containsAll( inPartnerDetails ) )
            {
                List<CrmPartnerDetailsPojo> activeOldList = new ArrayList<CrmPartnerDetailsPojo>();
                for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : inPartnerOldDetails )
                {
                    if ( StringUtils.equals( CRMStatusCode.ACTIVE.getStatusCode(), crmPartnerDetailsPojo.getStatus() ) )
                    {
                        activeOldList.add( crmPartnerDetailsPojo );
                    }
                }
                LOGGER.info( "Active Old List:" + activeOldList );
                LOGGER.info( "Active New List:" + inPartnerDetails );
                if ( activeOldList.size() == inPartnerDetails.size() )
                {
                    return !inPartnerDetails.containsAll( activeOldList );
                }
                else
                {
                    return true;
                }
            }
            else
            {
                return true;
            }
        }
        return true;
    }

    private void setPartnerPojoProperty( PartnerManagementForm inPmForm, String inUserId, PartnerPojo partnerPojo )
    {
        partnerPojo.setPartnerCen( inPmForm.getChiefInitial().concat( " " )
                .concat( inPmForm.getChiefExFirstName().concat( " " ).concat( inPmForm.getChiefExLastName() ) ) );
        partnerPojo.setDesignation( inPmForm.getChiefDesignation() );
        partnerPojo.setMobileNo( Long.parseLong( inPmForm.getChiefMobile() ) );
        partnerPojo.setEmailId( inPmForm.getChiefEmail() );
        partnerPojo.setRelatedDept( inPmForm.getRelatedDept() );
        partnerPojo.setHoCpn( inPmForm.getContactChiefInitial().concat( " " )
                .concat( inPmForm.getContactFirstName().concat( " " ).concat( inPmForm.getContactLastName() ) ) );
        partnerPojo.setHoEmailId( inPmForm.getPartnerEmail() );
        partnerPojo.setPhoneNo( Long.parseLong( inPmForm.getPartnerPhone() ) );
        partnerPojo.setHoMobileNo( Long.parseLong( inPmForm.getPartnerMobile() ) );
        partnerPojo.setFax( inPmForm.getPartnerFax() );
        partnerPojo.setAddress( inPmForm.getPartnerAddress1() );
        partnerPojo.setCity( inPmForm.getPartnerCity() );
        partnerPojo.setState( inPmForm.getPartnerState() );
        partnerPojo.setPincode( Integer.parseInt( inPmForm.getPartnerPincode() ) );
        partnerPojo.setCurrentStatus( inPmForm.getCurrentStatus() );
        partnerPojo.setPartnerErpCode( inPmForm.getPartnerERPCode() );
        partnerPojo.setAddedErp( inPmForm.getAddedInERP() );
        partnerPojo.setCrmPartnerCode( inPmForm.getPartnerCode() );
    }

    @Override
    public MasterDataDto searchPartner( PartnerManagementForm inPmForm )
    {
        MasterDataDto masterDataDto = null;
        PartnerPojo partnerPojo = null;
        try
        {
            masterDataDto = new MasterDataDto();
            partnerPojo = new PartnerPojo();
            if ( StringUtils.isNotEmpty( inPmForm.getBoardedDateFrom() ) )
            {
                masterDataDto.setFromDate( inPmForm.getBoardedDateFrom() );
                masterDataDto.setToDate( inPmForm.getBoardedDateTo() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getPartnerName() ) )
            {
                partnerPojo.setPartnerName( inPmForm.getPartnerName().trim() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getCurrentStatus() ) )
            {
                partnerPojo.setCurrentStatus( inPmForm.getCurrentStatus() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getAddedInERP() ) )
            {
                partnerPojo.setAddedErp( inPmForm.getAddedInERP() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getPartnerState() ) )
            {
                partnerPojo.setState( inPmForm.getPartnerState() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getPartnerCity() )
                    && !StringUtils.equals( "All Cities", inPmForm.getPartnerCity() ) )
            {
                partnerPojo.setCity( inPmForm.getPartnerCity() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getPartnerType() ) )
            {
                partnerPojo.setPartnerType( inPmForm.getPartnerType() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getBusinessType() ) )
            {
                partnerPojo.setBussinessType( inPmForm.getBusinessType() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getSearchStatus() ) )
            {
                partnerPojo.setCurrentStatus( inPmForm.getSearchStatus() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getPartnerShortName() ) )
            {
                partnerPojo.setPartnerShortName( inPmForm.getPartnerShortName() );
            }
            if ( StringUtils.isNotEmpty( inPmForm.getPartnerCode() ) )
            {
                partnerPojo.setCrmPartnerCode( inPmForm.getPartnerCode() );
            }
            masterDataDto.setPartnerPojo( partnerPojo );
            LOGGER.info( "Going to Search partner " );
            masterDataDto = getMasterData().partnerOperations( ServiceParameter.LIST.getParameter(), masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "error occured calling client to service for search partner management", ex );
        }
        return masterDataDto;
    }

    @Override
    public void setPartnerForm( PartnerManagementForm inPmForm, PartnerPojo inPartner )
    {
        inPmForm.setBoardedDate( inPartner.getStrBoardedDate() );
        inPmForm.setBusinessType( inPartner.getBussinessType() );
        inPmForm.setPartnerType( inPartner.getPartnerType() );
        inPmForm.setPartnerName( inPartner.getPartnerName() );
        StringTokenizer partnerCen = new StringTokenizer( inPartner.getPartnerCen(), " " );
        while ( partnerCen.hasMoreElements() )
        {
            inPmForm.setChiefInitial( partnerCen.nextToken() );
            inPmForm.setChiefExFirstName( partnerCen.nextToken() );
            inPmForm.setChiefExLastName( partnerCen.nextToken() );
        }
        inPmForm.setChiefDesignation( inPartner.getDesignation() );
        inPmForm.setChiefMobile( String.valueOf( inPartner.getMobileNo() ) );
        inPmForm.setChiefEmail( inPartner.getEmailId() );
        inPmForm.setRelatedDept( inPartner.getRelatedDept() );
        StringTokenizer hoCpn = new StringTokenizer( inPartner.getHoCpn(), " " );
        while ( hoCpn.hasMoreElements() )
        {
            inPmForm.setContactInitial( hoCpn.nextToken() );
            inPmForm.setContactFirstName( hoCpn.nextToken() );
            inPmForm.setContactLastName( hoCpn.nextToken() );
        }
        inPmForm.setPartnerEmail( inPartner.getHoEmailId() );
        inPmForm.setPartnerPhone( String.valueOf( inPartner.getPhoneNo() ) );
        inPmForm.setPartnerMobile( String.valueOf( inPartner.getHoMobileNo() ) );
        inPmForm.setPartnerFax( inPartner.getFax() );
        inPmForm.setPartnerAddress1( inPartner.getAddress() );
        inPmForm.setPartnerCity( inPartner.getCity() );
        inPmForm.setPartnerState( inPartner.getState() );
        inPmForm.setPartnerPincode( String.valueOf( inPartner.getPincode() ) );
        inPmForm.setCurrentStatus( inPartner.getCurrentStatus() );
        inPmForm.setAddedInERP( inPartner.getAddedErp() );
        inPmForm.setPartnerERPCode( inPartner.getPartnerErpCode() );
        inPmForm.setPartnerId( inPartner.getPartnerId() );
        inPmForm.setPartnerCode( inPartner.getCrmPartnerCode() );
        inPmForm.setPartnerPojo( inPartner );
    }

    @Override
    public ConfigDto getActivityLogs( PartnerManagementForm inPmForm )
    {
        ConfigDto configDto = new ConfigDto();
        CrmAuditLogPojo auditLogPojo = new CrmAuditLogPojo();
        try
        {
            LOGGER.info( "Partner Id ::" + inPmForm.getPartnerId() );
            auditLogPojo.setMappingId( String.valueOf( inPmForm.getPartnerId() ) );
            auditLogPojo.setModule( CRMRequestType.PARTNER_MANAGEMENT.getRequestCode() );
            configDto.setAuditLogPojo( auditLogPojo );
            configDto = getCrmConfigClient().auditLogOperation( ServiceParameter.VIEW.getParameter(), configDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while get activity log of user", ex );
        }
        return configDto;
    }

    @Override
    public List<CrmPartnerDetailsPojo> getPartnerDetailMapping( PartnerPojo inPartnerPojo )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        masterDataDto.setPartnerPojo( inPartnerPojo );
        try
        {
            masterDataDto = getMasterData().partnerOperations( ServiceParameter.MAPPING_LIST.getParameter(),
                                                               masterDataDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while get mapping list of partner", ex );
        }
        if ( StringUtils.isValidObj( masterDataDto.getCrmPartnerDetailsPojos() ) )
        {
            return masterDataDto.getCrmPartnerDetailsPojos();
        }
        else
        {
            return new ArrayList<CrmPartnerDetailsPojo>();
        }
    }
}
