package com.np.tele.crm.masterdata.bm;

import java.util.List;

import com.np.tele.crm.masterdata.forms.PartnerManagementForm;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.PartnerPojo;

public interface IPartnerManagement
{
    MasterDataDto addPartner( PartnerManagementForm inPmForm, String inUserId );

    MasterDataDto searchPartner( PartnerManagementForm inPmForm );

    MasterDataDto modifyPartner( PartnerManagementForm inPmForm, String inUserId );

    void setPartnerForm( PartnerManagementForm inPmForm, PartnerPojo inPartner );

    ConfigDto getActivityLogs( PartnerManagementForm inPmForm );

    List<CrmPartnerDetailsPojo> getPartnerDetailMapping( PartnerPojo inPartnerPojo );
}
