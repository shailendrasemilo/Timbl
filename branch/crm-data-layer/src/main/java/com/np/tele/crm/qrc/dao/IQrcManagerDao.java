package com.np.tele.crm.qrc.dao;

import java.math.BigDecimal;
import java.util.List;

import com.np.tele.crm.dto.CrmBillingDto;
import com.np.tele.crm.dto.CrmQrcDto;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;

public interface IQrcManagerDao
{
    CrmQrcDto getQrcCategories( CrmQrcDto inCrmQrcDto );

    //    CrmQrcDto postSR( CrmQrcDto inCrmQrcDto );
    CrmQrcDto viewCustomerTickets( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getWhiteListCustomers( CrmQrcDto inCrmQrcDto );

    CrmQrcDto addUpdateRemoveWhiteList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto bulkWhiteList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto barringUnbarringCustService( CrmQrcDto inCrmQrcDto );

    CrmQrcDto secondLevelAndFurtherBins( CrmQrcDto inCrmQrcDto );

    CrmQrcDto qrcActionTakenList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto qrcRootCauseList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto resetMyAccountPassword( CrmQrcDto inCrmQrcDto );

    CrmQrcDto searchCustomerInteraction( CrmQrcDto inCrmQrcDto );

    CrmQrcDto createCustomerInteraction( CrmQrcDto inCrmQrcDto );

    CrmQrcDto updateCustomerStatus( CrmQrcDto inCrmQrcDto );

    CrmQrcDto modifyInstallationAddresses( CrmQrcDto inCrmQrcDto );

    CrmQrcDto postWaiver( CrmQrcDto inCrmQrcDto );

    CrmQrcDto searchCustomerWaiverList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto modifyDeviceAndNetworkDetails( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getQrcSubCategories( CrmQrcDto inCrmQrcDto );

    CrmQrcDto saveAccessories( CrmQrcDto inCrmQrcDto, String param );

    /*CrmQrcDto saveStaticIP( CrmQrcDto inCrmQrcDto, String param );*/
    CrmQrcDto searchAccessories( CrmQrcDto inCrmQrcDto, String inParam );

    CrmQrcDto activateCustomerBasePlan( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getSelfcareSubjects( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getSelfcareCategories( CrmQrcDto inCrmQrcDto );

    //    CrmQrcDto getTicketOtherDetails( CrmQrcDto inCrmQrcDto );
    CrmQrcDto mountBoosterPlan( CrmQrcDto inCrmQrcDto );

    CrmQrcDto fetchActivatedVAS( CrmQrcDto inCrmQrcDto );

    CrmQrcDto activateDeactivateVAS( CrmQrcDto inCrmQrcDto );

    CrmQrcDto customerPlanReactivation( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getBillingDetails( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getPOPlanMigrationPolicy( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getPRPlanMigrationPolicy( CrmQrcDto inCrmQrcDto );

    //    void preparedQrcTicketPojo( CrmSrTicketsPojo crmSrTicketsPojo );
    CrmQrcDto customerPlanRenewal( CrmQrcDto inCrmQrcDto );

    CrmQrcDto setBillPdfPaths( CrmQrcDto inCrmQrcDto );

    CrmBillingDto getAdditionalDetails( String inCustomerId, String inServiceType );

    //    CrmQrcDto closeResolvedSR( CrmQrcDto inCrmQrcDto );
    CrmQrcDto bulkUpdateCustomerStatus( CrmQrcDto inCrmQrcDto );

    //    CrmQrcDto getCustomerUsageDetails( CrmQrcDto inCrmQrcDto );
    public boolean isValidTicketNo( String generatedTicketId, String customerId );

    //    CrmQrcDto updateCustomerStatus( CrmQrcDto inCrmQrcDto );
    public boolean isValidTicket( String generatedTicketId, String customerId );

    //    public CrmSrTicketsPojo preparedQrcTicketPojo( String customerId, String categoryValue );
    CrmQrcDto ticketIDProfileSearch( CrmQrcDto inCrmQrcDto );

    boolean updateCustomerPlanDetailsInCRM( String inMappingId, String inAuthority );

    public List<CrmRcaReasonPojo> getWaiverFunctionBin();

    public List<CrmRcaReasonPojo> getBinIdForPostWaiver( List<CrmRcaReasonPojo> waiverFunctionalBinList,
                                                         Double waiverAmount );

    //    void generateROLTicket( CrmCapDto inCrmCapDto );
    CrmQrcDto getBillingPlanRequestList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto updateBillingPlanRequest( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getPOAddOnPlanMigrationPolicy( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getPRAddOnPlanMigrationPolicy( CrmQrcDto inCrmQrcDto );

    CrmQrcDto cancelPlanMigrationRequest( CrmQrcDto inCrmQrcDto );

    CrmQrcDto updatePlansFromBilling( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getQrcSubSubCategories( CrmQrcDto inCrmQrcDto );

    CrmQrcDto bulkMountBoosterPlan( CrmQrcDto inCrmQrcDto );

    CrmQrcDto saveCustomerFeedback( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getCustomerFeedback( CrmQrcDto inCrmQrcDto );
}
