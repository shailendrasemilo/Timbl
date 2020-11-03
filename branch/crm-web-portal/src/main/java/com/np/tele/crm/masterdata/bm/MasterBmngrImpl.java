package com.np.tele.crm.masterdata.bm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.common.utils.CRMCacheManager;
import com.np.tele.crm.constants.AppMonitor;
import com.np.tele.crm.constants.CRMDisplayListConstants;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.constants.CRMRCAReason;
import com.np.tele.crm.constants.CRMServiceCode;
import com.np.tele.crm.constants.CRMStatusCode;
import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.ServiceParameter;
import com.np.tele.crm.masterdata.forms.MasterForm;
import com.np.tele.crm.masterdata.forms.ParameterGroupForm;
import com.np.tele.crm.masterdata.forms.PartnerManagementForm;
import com.np.tele.crm.masterdata.forms.RoleGroupForm;
import com.np.tele.crm.pojos.AllInboxPojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmNpMobilePojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.MasterData;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SOAPException_Exception;
import com.np.tele.crm.service.comparators.GroupComparator;
import com.np.tele.crm.utils.CRMUtils;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

@AppMonitor
public class MasterBmngrImpl
    implements IMasterBMngr
{
    private static final Logger LOGGER = Logger.getLogger( MasterBmngrImpl.class );
    private MasterData          masterDataClient;

    public MasterData getMasterDataClient()
    {
        return masterDataClient;
    }

    public void setMasterDataClient( MasterData masterDataClient )
    {
        this.masterDataClient = masterDataClient;
    }

    @Override
    public List<CrmRolesPojo> getRoles()
    {
        List<CrmRolesPojo> crmRoleList = null;
        try
        {
            MasterDataDto masterDataDto = getMasterDataClient().getMasterData( null,
                                                                               CRMParameter.CRM_ROLES.getParameter() );
            crmRoleList = masterDataDto.getCrmRolesPojoList();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl  getRoles method" + ex );
        }
        return crmRoleList;
    }

    @Override
    public List<GroupsPojo> getActiveGroup( String inGroupType )
    {
        List<GroupsPojo> groupPojoList = null;
        try
        {
            MasterDataDto masterDataDto = getMasterDataClient().getMasterData( null, inGroupType );
            groupPojoList = masterDataDto.getGroupsPojoList();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl  getRoles method" + ex );
        }
        return groupPojoList;
    }

    @Override
    public MasterDataDto createRoleGroup( RoleGroupForm inRoleGroupForm, String inMethodName, String inCreatedBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupName( inRoleGroupForm.getRoleGroupName() );
            groupPojo.setGroupDescription( inRoleGroupForm.getRoleGroupDescription() );
            groupPojo.setCreatedBy( inCreatedBy );
            groupPojo.setGroupType( CRMParameter.ROLES_GROUP.getParameter() );
            groupPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            groupPojo.getAccessGroups().addAll( inRoleGroupForm.getAccessGroups() );
            masterDataDto.setGroupsPojo( groupPojo );
            masterDataDto = getMasterDataClient().rolesGroupOperations( masterDataDto, inMethodName );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while fetching service Create RoleGroup ", ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto createMasterData( String inServiceParam, String inMasterType, MasterDataDto inMasterDto )
    {
        MasterDataDto masterDataDto = null;
        if ( StringUtils.isValidObj( inMasterDto ) )
        {
            try
            {
                LOGGER.info( "calling masterOperations" );
                masterDataDto = masterDataClient.masterOperations( inServiceParam, inMasterType, inMasterDto );
                LOGGER.info( "got masterDataDto" );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "Error in Create Email Server in master operation" );
            }
        }
        return masterDataDto;
    }

    public MasterDataDto searchRoleGroup( RoleGroupForm inRoleGroupForm, String inMethodName )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            LOGGER.info( "searchRoleGroup by status" + inRoleGroupForm.getStatus() );
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupName( inRoleGroupForm.getRoleGroupName() );
            groupPojo.setStatus( inRoleGroupForm.getSearchStatus() );
            groupPojo.setGroupType( CRMParameter.ROLES_GROUP.getParameter() );
            masterDataDto.setGroupsPojo( groupPojo );
            masterDataDto = getMasterDataClient().rolesGroupOperations( masterDataDto, inMethodName );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Error occured while fetching service:searchRoleGroup", ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto modifyRoleGroup( RoleGroupForm inRoleGroupForm, String inMethodName, String inModifiedBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupId( inRoleGroupForm.getRoleGroupId() );
            groupPojo.setGroupName( inRoleGroupForm.getRoleGroupName() );
            groupPojo.setGroupDescription( inRoleGroupForm.getRoleGroupDescription() );
            groupPojo.setGroupType( CRMParameter.ROLES_GROUP.getParameter() );
            groupPojo.setLastModifiedBy( inModifiedBy );
            groupPojo.getAccessGroups().clear();
            groupPojo.getAccessGroups().addAll( inRoleGroupForm.getAccessGroups() );
            if ( StringUtils.isValidObj( inRoleGroupForm.getGroupPojo() ) )
            {
                GroupComparator comparator = new GroupComparator();
                if ( comparator.compare( inRoleGroupForm.getGroupPojo(), groupPojo ) == 0 )
                {
                    masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.setGroupsPojo( groupPojo );
                masterDataDto = getMasterDataClient().rolesGroupOperations( masterDataDto, inMethodName );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class", e );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto createParameterGroup( ParameterGroupForm inParameterGroupForm, String inCreatedBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupName( inParameterGroupForm.getParameterGroupName() );
            groupPojo.setGroupDescription( inParameterGroupForm.getParameterGroupDescription() );
            groupPojo.setGroupType( CRMParameter.PARAMETER_GROUP.getParameter() );
            groupPojo.setCreatedBy( inCreatedBy );
            groupPojo.getAccessGroups().addAll( inParameterGroupForm.getAccessGroups() );
            groupPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setGroupsPojo( groupPojo );
            LOGGER.info( "Access Groups to Create:" + groupPojo.getAccessGroups() );
            masterDataDto = getMasterDataClient().parameterGroupOperations( masterDataDto,
                                                                            ServiceParameter.CREATE.getParameter() );
        }
        catch ( SOAPException_Exception ex )
        {
            masterDataDto = null;
            LOGGER.error( "Error occured while fetching service Create RoleGroup ", ex );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto modifyParameterGroup( ParameterGroupForm inParameterGroupForm, String inModifyBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupName( inParameterGroupForm.getParameterGroupName() );
            groupPojo.setGroupDescription( inParameterGroupForm.getParameterGroupDescription() );
            groupPojo.setLastModifiedBy( inModifyBy );
            groupPojo.getAccessGroups().clear();
            groupPojo.getAccessGroups().addAll( inParameterGroupForm.getAccessGroups() );
            LOGGER.info( "Access Groups to Update:" + groupPojo.getAccessGroups() );
            if ( StringUtils.isValidObj( inParameterGroupForm.getGroupPojo() ) )
            {
                groupPojo.setGroupId( inParameterGroupForm.getGroupPojo().getGroupId() );
                LOGGER.info( "OLD Access Groups to Update:" + inParameterGroupForm.getGroupPojo().getAccessGroups() );
                GroupComparator comparator = new GroupComparator();
                if ( comparator.compare( inParameterGroupForm.getGroupPojo(), groupPojo ) == 0 )
                {
                    masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.setGroupsPojo( groupPojo );
                masterDataDto = getMasterDataClient().parameterGroupOperations( masterDataDto,
                                                                                ServiceParameter.MODIFY.getParameter() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class", e );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto changeParameterGroupStatus( ParameterGroupForm inParameterGroupForm,
                                                     String inMethodName,
                                                     String inModifyBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupId( inParameterGroupForm.getParameterGroupId() );
            groupPojo.setStatus( inParameterGroupForm.getStatus() );
            groupPojo.setLastModifiedBy( inModifyBy );
            masterDataDto.setGroupsPojo( groupPojo );
            masterDataDto = getMasterDataClient().parameterGroupOperations( masterDataDto, inMethodName );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class getRoles method" + e );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto changeRoleGroupStatus( RoleGroupForm inRoleGroupForm, String inMethodName, String inModifyBy )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupId( inRoleGroupForm.getRoleGroupId() );
            groupPojo.setStatus( inRoleGroupForm.getStatus() );
            LOGGER.info( "Change request status" + groupPojo.getStatus() );
            groupPojo.setLastModifiedBy( inModifyBy );
            masterDataDto.setGroupsPojo( groupPojo );
            masterDataDto = getMasterDataClient().rolesGroupOperations( masterDataDto, inMethodName );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class getRoles method" + e );
        }
        return masterDataDto;
    }

    /* public static void main( String[] args )
     {
         MasterBmngrImpl master=new MasterBmngrImpl();
         List<CrmParameterPojo> crmParameterList=   master.getParameter();
         System.out.println(crmParameterList.size());
     }*/
    @Override
    public MasterDataDto searchParameterGroup( ParameterGroupForm inParameterGroupForm, String inMethodName )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        try
        {
            LOGGER.info( "IN search Parameter Group" );
            GroupsPojo groupPojo = new GroupsPojo();
            groupPojo.setGroupName( inParameterGroupForm.getParameterGroupName() );
            LOGGER.info( "Status" + inParameterGroupForm.getSearchStatus() );
            groupPojo.setStatus( inParameterGroupForm.getSearchStatus() );
            groupPojo.setGroupType( CRMParameter.PARAMETER_GROUP.getParameter() );
            masterDataDto.setGroupsPojo( groupPojo );
            masterDataDto = getMasterDataClient().parameterGroupOperations( masterDataDto, inMethodName );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class :searchParameterGroup", e );
        }
        return masterDataDto;
    }

    @Override
    public List<ProjectsPojo> getProjectList( String inProjectType )
    {
        MasterDataDto masterDataDto = new MasterDataDto();
        ProjectsPojo projectsPojo = new ProjectsPojo();
        projectsPojo.setProjectType( inProjectType );
        projectsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDataDto.setProjectsPojo( projectsPojo );
        try
        {
            masterDataDto = getMasterDataClient().externalApplication( ServiceParameter.LIST.getParameter(),
                                                                       masterDataDto );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class :searchParameterGroup", e );
        }
        return masterDataDto.getProjectsPojos();
    }

    @Override
    public List<CrmParameterPojo> getParameters( long inProjectId, CRMParameter inGroup, String projectType )
    {
        List<CrmParameterPojo> parameterList = new ArrayList<CrmParameterPojo>();
        MasterDataDto masterDataDto = new MasterDataDto();
        ProjectsPojo projectsPojo = new ProjectsPojo();
        projectsPojo.setProjectId( inProjectId );
        projectsPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        projectsPojo.setProjectType( projectType );
        CrmParameterPojo crmParameterPojo = new CrmParameterPojo();
        crmParameterPojo.setParameterGroup( inGroup.getParameter() );
        crmParameterPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        if ( CRMParameter.ALERT == inGroup )
        {
            crmParameterPojo.setParameterGroupType( CRMParameter.TEMPLATE.getParameter() );
        }
        else if ( CRMParameter.USER == inGroup )
        {
            crmParameterPojo.setParameterGroupType( CRMParameter.ACCESS.getParameter() );
        }
        projectsPojo.getCrmParameterPojosSet().add( crmParameterPojo );
        masterDataDto.setProjectsPojo( projectsPojo );
        try
        {
            masterDataDto = getMasterDataClient().getMasterData( masterDataDto,
                                                                 CRMParameter.CRM_PARAMETER.getParameter() );
            parameterList = masterDataDto.getCrmParameterPojos();
            LOGGER.info( "parameterList.........." + parameterList.size() );
        }
        catch ( Exception e )
        {
            LOGGER.error( "Error occured in MasterBmngrImpl class :searchParameterGroup", e );
        }
        return parameterList;
    }

    @Override
    /*If you want all partner list[A & I], set status null*/
    public List<PartnerPojo> getPartnersByStatus( String inStatus )
    {
        List<PartnerPojo> partnerList = null;
        try
        {
            LOGGER.info( "In DWR IMPL getPartenet Method called::" );
            MasterDataDto masterDataDto = new MasterDataDto();
            masterDataDto.setPartnerPojo( new PartnerPojo() );
            masterDataDto.getPartnerPojo().setCurrentStatus( inStatus );
            masterDataDto = getMasterDataClient().getMasterData( masterDataDto,
                                                                 CRMParameter.PARTNER_POJO.getParameter() );
            LOGGER.info( "Status Method:: " + masterDataDto.getStatusDesc() );
            partnerList = masterDataDto.getPartnerPojoList();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error in getPartners mehtod:: " + ex );
        }
        return partnerList;
    }

    @Override
    public List<PartnerPojo> getPartnerbyType( String inPartnerType, String inBusinessType, String inStatus )
    {
        return CRMCacheManager.getPartnerbyType( inPartnerType, inBusinessType, inStatus );
    }

    @Override
    public MasterDataDto masterOperation( String option82, String inModifyBy, long inPartnerId )
    {
        LOGGER.info( "masterOperation..........................." );
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            PartnerPojo partnerPojo = new PartnerPojo();
            partnerPojo.setPartnerId( inPartnerId );
            partnerPojo.setOption82( option82 );
            partnerPojo.setLastModifiedBy( inModifyBy );
            masterDataDto.setPartnerPojo( partnerPojo );
            masterDataDto = getMasterDataClient()
                    .masterOperations( ServiceParameter.CREATE.getParameter(), CRMParameter.OPTION82.getParameter(),
                                       masterDataDto );
            LOGGER.info( "Status Code" + masterDataDto.getStatusCode() );
        }
        catch ( SOAPException_Exception e )
        {
            LOGGER.error( "SOAPException_Exception........", e );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto masterOperation( String inServiceParam, String inMasterType, MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            try
            {
                inMasterDataDto = getMasterDataClient()
                        .masterOperations( inServiceParam, inMasterType, inMasterDataDto );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "Error in get receipt no master operation" );
            }
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto smsConfigurationOperation( MasterForm inMasterForm,
                                                    String inCreatedBy,
                                                    String inServiceParameter )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            masterDataDto.setUserID( inCreatedBy );
            if ( StringUtils.isValidObj( inMasterForm.getSmsGatewayPojo().getSubCategory() ) )
            {
                masterDataDto.setSmsGatewayPojo( inMasterForm.getSmsGatewayPojo() );
                masterDataDto = getMasterDataClient().masterOperations( inServiceParameter,
                                                                        CRMParameter.SMS.getParameter(), masterDataDto );
            }
            else
            {
                masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
            }
        }
        catch ( Exception e )
        {
            LOGGER.error( "Exception........", e );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto npToSpMapping( PartnerManagementForm partnerManagementForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            if ( StringUtils.isValidObj( partnerManagementForm.getCrmMappingList() )
                    && !partnerManagementForm.getCrmMappingList().isEmpty() )
            {
                if ( StringUtils.isValidObj( partnerManagementForm.getOldCrmMappingList() )
                        && !partnerManagementForm.getOldCrmMappingList().isEmpty() )
                {
                    List<CrmPartnerMappingPojo> oldCrmPartnerMapping = partnerManagementForm.getOldCrmMappingList();
                    List<CrmPartnerMappingPojo> toUpdatePartnerMapping = partnerManagementForm.getCrmMappingList();
                    LOGGER.info( "oldCrmPartnerMapping" + oldCrmPartnerMapping );
                    LOGGER.info( "toUpdatePartnerMapping" + toUpdatePartnerMapping );
                    if ( oldCrmPartnerMapping.size() == toUpdatePartnerMapping.size() )
                    {
                        if ( oldCrmPartnerMapping.equals( toUpdatePartnerMapping ) )
                        {
                            masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                        }
                        else
                        {
                            LOGGER.info( "Mapping are not equals" );
                        }
                    }
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.getCrmPartnerMappingList().addAll( partnerManagementForm.getCrmMappingList() );
                masterDataDto = getMasterDataClient()
                        .partnerOperations( ServiceParameter.PARTNER_MAPPING.getParameter(), masterDataDto );
            }
            LOGGER.info( "Status" + masterDataDto.getStatusCode() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception........", ex );
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDataDto;
    }

    public MasterDataDto getCrmPartnerMappingList( PartnerManagementForm partnerManagementForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            PartnerPojo pojo = new PartnerPojo();
            pojo.setPartnerId( partnerManagementForm.getPartnerId() );
            CrmPartnerMappingPojo mappingpojo = new CrmPartnerMappingPojo();
            mappingpojo.setProduct( partnerManagementForm.getBusinessTypeSP() );
            mappingpojo.setPartnerByNpId( pojo );
            masterDataDto.setCrmPartnerMappingPojo( mappingpojo );
            LOGGER.info( "Client calling........" );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.VIEW.getParameter(),
                                                                     masterDataDto );
            LOGGER.info( "Result Mapping List Size........" + masterDataDto.getCrmPartnerMappingList().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception........", ex );
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDataDto;
    }

    @Override
    public List<PartnerPojo> getPartnersById( String inPartnerId )
    {
        List<PartnerPojo> partnerList = null;
        try
        {
            MasterDataDto masterDataDto = new MasterDataDto();
            PartnerPojo partnerPojo = new PartnerPojo();
            partnerPojo.setPartnerId( StringUtils.numericValue( inPartnerId ) );
            masterDataDto.setPartnerPojo( partnerPojo );
            masterDataDto = getMasterDataClient().getMasterData( masterDataDto,
                                                                 CRMParameter.PARTNER_POJO.getParameter() );
            LOGGER.info( "Status Method:: " + masterDataDto.getStatusDesc() );
            partnerList = masterDataDto.getPartnerPojoList();
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Error in getPartners mehtod:: " + ex );
        }
        return partnerList;
    }

    @Override
    public List<CrmPartnerMappingPojo> getServicePartner( String inNpId, String inProduct )
    {
        LOGGER.info( "Get PartnerId :: " + inNpId );
        MasterDataDto masterDataDto = null;
        List<CrmPartnerMappingPojo> mappingPojos = null;
        try
        {
            PartnerPojo pojo = new PartnerPojo();
            masterDataDto = new MasterDataDto();
            pojo.setPartnerId( Long.parseLong( inNpId ) );
            CrmPartnerMappingPojo mappingpojo = new CrmPartnerMappingPojo();
            mappingpojo.setPartnerByNpId( pojo );
            mappingpojo.setProduct( inProduct );
            mappingpojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
            masterDataDto.setCrmPartnerMappingPojo( mappingpojo );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.VIEW.getParameter(),
                                                                     masterDataDto );
            mappingPojos = masterDataDto.getCrmPartnerMappingList();
            LOGGER.info( "Result Mapping List Size........" + mappingPojos.size() );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.error( "Getting Error While, Fetching service partners: " + ex );
        }
        return mappingPojos;
    }

    @Override
    public MasterDataDto masterOperation( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            try
            {
                LOGGER.info( "calling masterOperations for get receipt No" );
                inMasterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                          CRMParameter.CRM_RCA_REASON.getParameter(),
                                                                          inMasterDataDto );
                LOGGER.info( "got masterDataDto" );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "Error in get receipt no master operation" );
            }
        }
        return inMasterDataDto;
    }

    @Override
    public List<PartnerPojo> getPartnerByName( String partnerName, String PartnerType )
    {
        List<PartnerPojo> finalpojos = new ArrayList<PartnerPojo>();
        List<PartnerPojo> pojos = getPartnerbyType( PartnerType );
        if ( StringUtils.isValidObj( pojos ) )
        {
            for ( PartnerPojo partnerPojo : pojos )
            {
                if ( StringUtils.contains( partnerPojo.getPartnerName(), partnerName ) )
                {
                    finalpojos.add( partnerPojo );
                }
            }
        }
        return finalpojos;
    }

    @Override
    public List<ContentPojo> getProductsByPartnerId( String inPartnerId, String inPartnerType )
    {
        List<ContentPojo> finalpojos = new ArrayList<ContentPojo>();
        List<ContentPojo> totalPartnerTypes = CRMUtils.getProducts();
        if ( !StringUtils.equals( inPartnerId, String.valueOf( 0 ) ) )
        {
            List<PartnerPojo> pojos = getPartnerbyType( inPartnerType );
            if ( StringUtils.isValidObj( pojos ) )
            {
                for ( PartnerPojo partnerPojo : pojos )
                {
                    if ( StringUtils.equals( String.valueOf( partnerPojo.getPartnerId() ), inPartnerId ) )
                    {
                        List<CrmPartnerDetailsPojo> partnerDetailsPojos = partnerPojo.getCrmPartnerDetailses();
                        for ( CrmPartnerDetailsPojo crmPartnerDetailsPojo : partnerDetailsPojos )
                        {
                            if ( StringUtils.equals( crmPartnerDetailsPojo.getPartnerType(), inPartnerType )
                                    && StringUtils.equals( crmPartnerDetailsPojo.getStatus(),
                                                           CRMStatusCode.ACTIVE.getStatusCode() ) )
                            {
                                for ( ContentPojo partnerType : totalPartnerTypes )
                                {
                                    if ( StringUtils.equals( crmPartnerDetailsPojo.getBussinessType(),
                                                             partnerType.getContentValue() ) )
                                    {
                                        ContentPojo contentPojo = new ContentPojo();
                                        contentPojo.setContentName( partnerType.getContentName() );
                                        contentPojo.setContentValue( crmPartnerDetailsPojo.getBussinessType() );
                                        finalpojos.add( contentPojo );
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return finalpojos;
    }

    @Override
    public List<PartnerPojo> getPartnerbyType( String inPartnerType )
    {
        return CRMCacheManager.getPartnerByPartnerType( inPartnerType );
    }

    @Override
    public MasterDataDto getPlanMaster( MasterDataDto inMasterDataDto )
    {
        if ( StringUtils.isValidObj( inMasterDataDto ) )
        {
            try
            {
                LOGGER.info( "calling masterOperations for get PLAN_DETAILS_MASTER" );
                inMasterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                          CRMParameter.PLAN_DETAILS_MASTER
                                                                                  .getParameter(), inMasterDataDto );
                LOGGER.info( "got masterDataDto" );
            }
            catch ( SOAPException_Exception ex )
            {
                LOGGER.info( "Error in get receipt no master operation" );
            }
        }
        return inMasterDataDto;
    }

    @Override
    public MasterDataDto getPartnerDetailsId( String inPartnerId )
    {
        PartnerPojo partnerPojo = null;
        MasterDataDto masterDataDto = null;
        partnerPojo = new PartnerPojo();
        masterDataDto = new MasterDataDto();
        partnerPojo.setPartnerId( Long.parseLong( inPartnerId ) );
        partnerPojo.setPartnerType( CRMDisplayListConstants.NETWORK_PARTNER.getCode() );
        partnerPojo.setBussinessType( CRMDisplayListConstants.BROADBAND.getCode() );
        masterDataDto.setPartnerPojo( partnerPojo );
        try
        {
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.MAPPING_LIST.getParameter(),
                                                                     masterDataDto );
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Error in getPartnerDetailsId method" );
        }
        return masterDataDto;
    }

    @Override
    public String validateCRFInMaster( String inCrfId, String inProduct )
    {
        MasterDataDto masterDto = new MasterDataDto();
        CrmRcaReasonPojo crmRcaReasonPojo = new CrmRcaReasonPojo();
        crmRcaReasonPojo.setCategory( CRMRCAReason.INA.getStatusCode() );
        crmRcaReasonPojo.setSubCategory( CRMDisplayListConstants.CAF.getCode() );
        /*if ( CRMDisplayListConstants.RADIO_FREQUENCY.getCode().equals( inProduct ) )
        {
            crmRcaReasonPojo.setSubSubCategory( CRMDisplayListConstants.BROADBAND.getCode() );
        }
        else
        {
            crmRcaReasonPojo.setSubSubCategory( inProduct );
        }*/
        crmRcaReasonPojo.setCategoryValue( inCrfId );
        crmRcaReasonPojo.setStatus( CRMStatusCode.ACTIVE.getStatusCode() );
        masterDto.setCrmRcaReason( crmRcaReasonPojo );
        try
        {
            masterDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                CRMParameter.CRM_RCA_REASON.getParameter(), masterDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in getPartnerDetailsId method", ex );
            masterDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDto.getStatusCode();
    }

    @Override
    public MasterDataDto getNetworkPartnerList( long PartnerDetailsId, String oltType )
    {
        MasterDataDto masterDto = new MasterDataDto();
        CrmPartnerNetworkConfigPojo crmPartnerNetworkConfigPojo = new CrmPartnerNetworkConfigPojo();
        crmPartnerNetworkConfigPojo.setPartnerDetailsId( PartnerDetailsId );
        crmPartnerNetworkConfigPojo.setOltType( oltType );
        masterDto.setCrmPartnerNetworkConfigPojo( crmPartnerNetworkConfigPojo );
        try
        {
            masterDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                CRMParameter.CRM_PARTNER_NETWORK_CONFIG.getParameter(),
                                                                masterDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in getCrmNetworkPartnerList method", ex );
            masterDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDto;
    }

    @Override
    public MasterDataDto saveRemarks( RemarksPojo inPojo )
    {
        MasterDataDto masterDto = new MasterDataDto();
        masterDto.setRemarks( inPojo );
        try
        {
            masterDto = getMasterDataClient().masterOperations( ServiceParameter.CREATE.getParameter(),
                                                                RemarksPojo.class.getSimpleName(), masterDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in getCrmNetworkPartnerList method", ex );
            masterDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDto;
    }

    @Override
    public MasterDataDto updateUnreadInbox( long inboxId )
    {
        LOGGER.info( "Going to update inbox" );
        MasterDataDto masterDto = new MasterDataDto();
        masterDto.setInboxId( inboxId );
        try
        {
            masterDto = getMasterDataClient().masterOperations( ServiceParameter.UPDATE.getParameter(),
                                                                AllInboxPojo.class.getSimpleName(), masterDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in marking unread inbox method", ex );
            masterDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDto;
    }

    @Override
    public List<CrmUserPojo> getAssociatedSRWithBP( String inProduct, long inPartnerId )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            masterDataDto.setPartnerId( inPartnerId );
            masterDataDto.setProduct( inProduct );
            masterDataDto = getMasterDataClient().masterOperations( ServiceParameter.LIST.getParameter(),
                                                                    CrmUserPojo.class.getSimpleName(), masterDataDto );
            if ( CommonValidator.isValidCollection( masterDataDto.getCrmUserPojos() ) )
            {
                LOGGER.info( "CrmUserPojos Size:" + masterDataDto.getCrmUserPojos().size() );
                return masterDataDto.getCrmUserPojos();
            }
        }
        catch ( SOAPException_Exception ex )
        {
            LOGGER.info( "Error in getAssociatedSRWithBP method" );
        }
        return null;
    }

    @Override
    public MasterDataDto addMultipleMobileWithNP( PartnerManagementForm inPmForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            if ( CommonValidator.isValidCollection( inPmForm.getCrmNpMobileList() )
                    && CommonValidator.isValidCollection( inPmForm.getOldCrmNpMobileList() ) )
            {
                List<CrmNpMobilePojo> oldNpMobileList = inPmForm.getOldCrmNpMobileList();
                List<CrmNpMobilePojo> toUpdateNpMobileList = inPmForm.getCrmNpMobileList();
                LOGGER.info( "OldCrmNpMobileList" + oldNpMobileList );
                LOGGER.info( "toUpdateCrmNpMobileList" + toUpdateNpMobileList );
                if ( oldNpMobileList.size() == toUpdateNpMobileList.size() )
                {
                    if ( oldNpMobileList.equals( toUpdateNpMobileList ) )
                    {
                        masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                    }
                    else
                    {
                        LOGGER.info( "Both List are not equals" );
                    }
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.getCrmNpMobilePojos().addAll( inPmForm.getCrmNpMobileList() );
                masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.SAVE.getParameter(),
                                                                         masterDataDto );
            }
            LOGGER.info( "Status" + masterDataDto.getStatusCode() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in addMultipleMobileWithNP :", ex );
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto getCrmNpMobileList( PartnerManagementForm inPmForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            CrmNpMobilePojo pojo = new CrmNpMobilePojo();
            pojo.setNpId( inPmForm.getPartnerId() );
            masterDataDto.setCrmNpMobilePojo( pojo );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.SEARCH.getParameter(),
                                                                     masterDataDto );
            LOGGER.info( "Result Mapping List Size........" + masterDataDto.getCrmNpMobilePojos().size() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured in getCrmNpMobileList", ex );
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDataDto;
    }

    public MasterDataDto updateReadInbox( CrmTicketHistoryPojo inTicketHistory )
    {
        MasterDataDto masterDto = new MasterDataDto();
        masterDto.setTicketHistory( inTicketHistory );
        try
        {
            masterDto = getMasterDataClient().masterOperations( ServiceParameter.UPDATE.getParameter(),
                                                                AllInboxPojo.class.getSimpleName(), masterDto );
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Error in marking Read inbox method", ex );
            masterDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDto;
    }

    @Override
    public MasterDataDto getCrmUserAlertMobileEmailList( AlertsForm inAlertsForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            CrmNpMobilePojo pojo = new CrmNpMobilePojo();
            pojo.setGenericType( IAppConstants.EVENT );
            pojo.setEventId( inAlertsForm.getEventId() );
            masterDataDto.setCrmNpMobilePojo( pojo );
            masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.SEARCH.getParameter(),
                                                                     masterDataDto );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception occured in getCrmUserAlertMobileEmailList", ex );
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDataDto;
    }

    @Override
    public MasterDataDto addUserAlertDetails( AlertsForm inAlertsForm )
    {
        MasterDataDto masterDataDto = null;
        try
        {
            masterDataDto = new MasterDataDto();
            if ( CommonValidator.isValidCollection( inAlertsForm.getCrmNpMobileList() )
                    && CommonValidator.isValidCollection( inAlertsForm.getOldCrmNpMobileList() ) )
            {
                List<CrmNpMobilePojo> oldNpMobileList = inAlertsForm.getOldCrmNpMobileList();
                List<CrmNpMobilePojo> toUpdateNpMobileList = inAlertsForm.getCrmNpMobileList();
                LOGGER.info( "OldCrmNpMobileList" + oldNpMobileList );
                LOGGER.info( "toUpdateCrmNpMobileList" + toUpdateNpMobileList );
                if ( oldNpMobileList.size() == toUpdateNpMobileList.size() )
                {
                    if ( oldNpMobileList.equals( toUpdateNpMobileList ) )
                    {
                        masterDataDto.setStatusCode( CRMServiceCode.CRM995.getStatusCode() );
                    }
                    else
                    {
                        LOGGER.info( "Both List are not equals" );
                    }
                }
                else
                {
                    /* CrmNpMobilePojo crmNpMobilePojo = inAlertsForm.getCrmNpMobileList().get( inAlertsForm
                                                                                                      .getRowCounter() );
                     crmNpMobilePojo.setEventId( inAlertsForm.getEventId() );*/
                    Set<String> crmNpMobilePojos = new HashSet<String>();
                    for ( CrmNpMobilePojo userAlertPojo : toUpdateNpMobileList )
                    {
                        if ( !crmNpMobilePojos.add( String.valueOf( userAlertPojo.getMobileNo() ) )
                                || !crmNpMobilePojos.add( userAlertPojo.getEmailId() ) )
                        {
                            masterDataDto.setStatusCode( CRMServiceCode.CRM990.getStatusCode() );
                            break;
                        }
                        /*if ( userAlertPojo.getEventId() == null )//setting eventIDs on newly created elements
                        {
                            userAlertPojo.setEventId( inAlertsForm.getEventId() );
                        }*/
                    }
                }
            }
            if ( StringUtils.isBlank( masterDataDto.getStatusCode() ) )
            {
                masterDataDto.getCrmNpMobilePojos().addAll( inAlertsForm.getCrmNpMobileList() );
                masterDataDto = getMasterDataClient().partnerOperations( ServiceParameter.SAVE.getParameter(),
                                                                         masterDataDto );
            }
            LOGGER.info( "Status" + masterDataDto.getStatusCode() );
        }
        catch ( Exception ex )
        {
            LOGGER.error( "Exception in addUserAlertDetails :", ex );
            masterDataDto.setStatusCode( CRMServiceCode.CRM999.getStatusCode() );
        }
        return masterDataDto;
    }
}
