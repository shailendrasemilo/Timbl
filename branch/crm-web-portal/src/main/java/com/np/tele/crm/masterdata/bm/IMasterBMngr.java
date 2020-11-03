package com.np.tele.crm.masterdata.bm;

import java.util.List;

import com.np.tele.crm.alerts.forms.AlertsForm;
import com.np.tele.crm.constants.CRMParameter;
import com.np.tele.crm.masterdata.forms.MasterForm;
import com.np.tele.crm.masterdata.forms.ParameterGroupForm;
import com.np.tele.crm.masterdata.forms.PartnerManagementForm;
import com.np.tele.crm.masterdata.forms.RoleGroupForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmRolesPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.GroupsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.ProjectsPojo;
import com.np.tele.crm.service.client.RemarksPojo;

public interface IMasterBMngr
{
    MasterDataDto createRoleGroup( RoleGroupForm inRoleGroupForm, String inMethodName, String inCreatedBy );

    MasterDataDto modifyRoleGroup( RoleGroupForm inRoleGroupForm, String inMethodName, String inModifyBy );

    MasterDataDto changeRoleGroupStatus( RoleGroupForm inRoleGroupForm, String inMethodName, String inModifyBy );

    MasterDataDto searchRoleGroup( RoleGroupForm inRoleGroupForm, String inMethodName );

    MasterDataDto createParameterGroup( ParameterGroupForm inParameterGroupForm, String inCreatedBy );

    MasterDataDto modifyParameterGroup( ParameterGroupForm inParameterGroupForm, String inModifyBy );

    MasterDataDto changeParameterGroupStatus( ParameterGroupForm inParameterGroupForm,
                                              String inMethodName,
                                              String inModifyBy );

    List<CrmRolesPojo> getRoles();

    List<ProjectsPojo> getProjectList( String inProjectType );

    public List<GroupsPojo> getActiveGroup( String inGroupType );

    public MasterDataDto searchParameterGroup( ParameterGroupForm inParameterGroupForm, String inMethodName );

    List<CrmParameterPojo> getParameters( long inProjectId, CRMParameter inGroup, String inProjectType );

    List<PartnerPojo> getPartnersByStatus( String inStatus );

    List<PartnerPojo> getPartnerbyType( String partnerType );

    List<PartnerPojo> getPartnerbyType( String partnerType, String inBusinessType, String inStatus );

    public MasterDataDto masterOperation( String option82, String inModifyBy, long inPartnerId );

    public MasterDataDto smsConfigurationOperation( MasterForm inMasterForm,
                                                    String inCreatedBy,
                                                    String inServiceParameter );

    public MasterDataDto createMasterData( String inServiceParam, String inMasterType, MasterDataDto inMasterDto );

    public MasterDataDto npToSpMapping( PartnerManagementForm partnerManagementForm );

    MasterDataDto getCrmPartnerMappingList( PartnerManagementForm partnerManagementForm );

    public List<PartnerPojo> getPartnersById( String inPartnerId );

    public MasterDataDto masterOperation( MasterDataDto inMasterDataDto );

    List<PartnerPojo> getPartnerByName( String inPartnerName, String inPartnerType );

    List<ContentPojo> getProductsByPartnerId( String inPartnerId, String inPartnerType );

    MasterDataDto getPlanMaster( MasterDataDto masterDataDto );

    MasterDataDto masterOperation( String inServiceParam, String inMasterType, MasterDataDto inMasterDataDto );

    MasterDataDto getPartnerDetailsId( String inPartnerId );

    String validateCRFInMaster( String inUpperCase, String inProduct );

    MasterDataDto getNetworkPartnerList( long PartnerDetailsId, String oltType );

    MasterDataDto saveRemarks( RemarksPojo inPojo );

    List<CrmPartnerMappingPojo> getServicePartner( String inNpId, String inProduct );

    MasterDataDto updateUnreadInbox( long inInboxId );

    List<CrmUserPojo> getAssociatedSRWithBP( String inProduct, long inPartnerId );

    MasterDataDto addMultipleMobileWithNP( PartnerManagementForm inPmForm );

    MasterDataDto getCrmNpMobileList( PartnerManagementForm inPmForm );

    MasterDataDto updateReadInbox( CrmTicketHistoryPojo inTicketHistory );

    MasterDataDto getCrmUserAlertMobileEmailList( AlertsForm inAlertForm );

    MasterDataDto addUserAlertDetails( AlertsForm inAlertsForm );
}
