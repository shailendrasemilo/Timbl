package com.np.tele.crm.masterdata.bm;

import java.util.List;

import com.np.tele.crm.masterdata.forms.CrmRcaReasonForm;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.MasterDataDto;

public interface ICrmRcaReasonMgr
{
    MasterDataDto createCategoryValues( CrmRcaReasonForm inCrmRcaReasonForm, String inString );

    void setCategoriesByForm( CrmRcaReasonPojo inCrmRcaReason, CrmRcaReasonForm inCrmRcaReasonForm );

    MasterDataDto searchCategoryValue( CrmRcaReasonForm inCrmRcaReasonForm );

    MasterDataDto addRecietValues( List<CrmRcaReasonPojo> inContentPojo );

    MasterDataDto changeStatus( CrmRcaReasonForm inCrmRcaReasonForm, String inModifiedBy );

    MasterDataDto getHolidaysList();

    MasterDataDto postHoliday( CrmRcaReasonForm inCrmRcaReasonForm, String inUserId );
}
