package com.np.tele.crm.masterdata.dao;

import java.util.List;

import com.np.tele.crm.dto.MasterDataDto;
import com.np.tele.crm.pojos.CrmMasterPojo;
import com.np.tele.crm.pojos.CrmRolesPojo;
import com.np.tele.crm.pojos.GroupsPojo;
import com.np.tele.crm.pojos.PartnerPojo;

public interface IMasterDataDao
{
    public List<PartnerPojo> getPartnerPojoList( MasterDataDto inMasterDataDto );

    public List<CrmRolesPojo> getCRMRolesPojoList();

    public MasterDataDto getCRMParameterPojoListForPrmtrGroup( MasterDataDto inMasterDataDto );

    public List<GroupsPojo> getActiveGroup( String inGroupType );

    public MasterDataDto changeStatusGroup( MasterDataDto inMasterdataDto );

    public MasterDataDto createCRMRolesGroup( MasterDataDto inMasterdataDto );

    public MasterDataDto updateCRMRolesGroup( MasterDataDto inMasterdataDto );

    public MasterDataDto createCRMParameterGroup( MasterDataDto inMasterdataDto );

    public MasterDataDto updateCRMParameterGroup( MasterDataDto inMasterdataDto );

    public MasterDataDto searchGroup( MasterDataDto inMasterdataDto );

    MasterDataDto getGroupById( MasterDataDto inMasterdataDto );

    public MasterDataDto createOption82( MasterDataDto inMasterDataDto );

    public MasterDataDto updateMasterData( List<CrmMasterPojo> inMasterPojos, boolean inToCreate );

    public MasterDataDto createAndUpdateCategoryValue( MasterDataDto inMasterDataDto );

    public MasterDataDto searchAllCategoryValue( MasterDataDto inMasterDataDto );

    MasterDataDto searchOption82( MasterDataDto inMasterDataDto );

    public MasterDataDto getAllPlanDetails( MasterDataDto inMasterDataDto );

    public MasterDataDto getPartnerNetworkConfig( MasterDataDto inMasterDataDto );

    public MasterDataDto updateUnreadInbox( MasterDataDto inMasterDataDto );

    public MasterDataDto postHolidayDetails( MasterDataDto inMasterDataDto );

    public MasterDataDto getHoldayDetails( MasterDataDto inMasterDataDto );

    public MasterDataDto getAssociatSRWithBP( MasterDataDto inMasterDataDto );
}
