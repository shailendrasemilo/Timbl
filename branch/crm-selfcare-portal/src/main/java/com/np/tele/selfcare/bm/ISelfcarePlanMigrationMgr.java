package com.np.tele.selfcare.bm;

import java.util.List;

import com.np.tele.crm.service.client.CrmCustAssetDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.selfcare.forms.SelfcareMigrationForm;

public interface ISelfcarePlanMigrationMgr
{
    MasterDataDto getPlanInfo( SelfcareMigrationForm planMigrationForm );

    List<CrmPlanMasterPojo> getUnlimitedBasePlanDetails( CrmCustomerDetailsPojo crmCustomerDetailsPojo );

    List<CrmPlanMasterPojo> getLimitedBasePlanDetails( CrmCustomerDetailsPojo crmCustomerDetailsPojo );

    List<CrmPlanMasterPojo> getAddonPlanDetails( CrmCustomerDetailsPojo crmCustomerDetailsPojo );

    CrmQrcDto activateCustomerBasePlan( SelfcareMigrationForm planMigrationForm,
                                        CrmCustomerDetailsPojo crmCustomerDetailsPojo );

    List<CrmPlanMasterPojo> getBoosterUageTopUps( CrmPlanMasterPojo inPlanMasterPojo );

    CrmQrcDto topUpUsageBooster( CrmCustAssetDetailsPojo inCustAssetDetailsPojo,
                                 CrmCustomerDetailsPojo inCustomerDetailsPojo );

    CrmQrcDto getMigrationPolicy( CrmQrcDto crmQrcDto );

    CrmQrcDto getCustomerTickets( CrmQrcDto crmQrcDto );

    void setCategoriesNameById( List<CrmSrTicketsPojo> crmSrTicketsPojos );
}
