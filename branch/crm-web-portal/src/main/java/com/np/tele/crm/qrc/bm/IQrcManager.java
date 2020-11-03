package com.np.tele.crm.qrc.bm;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.np.tele.crm.qrc.forms.QrcForm;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcWhitelistPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.ExcelWriteUtils;

public interface IQrcManager
{
    List<CrmQrcCategoriesPojo> getQrcCategories( String inStatus );

    CrmQrcCategoriesPojo getCrmQrcCategoriesPojo( long inQrcCategoryId );

    CrmQrcDto logQrcTicket( CrmQrcDto inCrmQrcDto );

    CrmCapDto saveCustomerProfileDetails( CrmCapDto crmCapDto );

    CrmCapDto sendVerificationLink( CrmCapDto capDto );

    CrmCapDto getCustomerProfileDetails( CrmCustomerDetailsPojo inCustomerDetailsPojo );

    ConfigDto customerActivityOps( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo );

    CrmQrcDto getCustomerTickets( String inCustomerId );

    CrmQrcDto getTicketView( CrmSrTicketsPojo crmSrTicketsPojo );

    void setCategoriesNameById( List<CrmSrTicketsPojo> list );

    List<CrmQrcWhitelistPojo> checkWhiteListCustomer( CrmQrcWhitelistPojo crmQrcWhitelistPojo );

    CrmQrcDto addUpdateWhiteList( QrcForm qrcForm );

    CrmQrcDto removeFromWhiteList( QrcForm qrcForm );

    List<String> processBulkWhitelistExcel( QrcForm inQrcForm, String inUser, String inFilePath );

    CrmQrcDto bulkWhiteList( List<CrmQrcWhitelistPojo> crmQrcWhitelistPojos, RemarksPojo remarksPojo );

    CrmQrcDto barringUnbarringService( QrcForm qrcForm );

    CrmQrcDto createCustomerInteraction( CrmQrcDto inQrcDto );

    CrmQrcDto searchCustomerInteraction( CrmQrcDto inQrcDto );

    CrmQrcDto resetPassword( QrcForm inQrcForm );

    CrmQrcDto changeInstallationAddress( QrcForm inQrcForm );

    CrmQrcDto disconnectionCall( QrcForm qrcForm );

    CrmMassOutagePojo getMassOutage( String inService,
                                     CrmAddressDetailsPojo inCrmAddressDetailsPojo,
                                     CrmNetworkConfigurationsPojo inCrmNetworkConfigurationsPojo );

    CrmQrcDto getFunctionalBinbyId( CrmQrcDto inCrmQrcDto );

    CrmQrcDto qrcRcaList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto qrcRcaReasonList( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getCustomerTickets( CrmQrcDto inCrmQrcDto );

    CrmQrcDto getCustWaiverPojos( QrcForm inQrcForm );

    CrmQrcDto applyWaiver( QrcForm inQrcForm );

    CrmQrcDto editDevice( CrmQrcDto crmQrcDto );

    CrmQrcDto sendLegalMail( CrmQrcDto inCrmQrcDto );

    CrmQrcDto saveAccessories( CrmQrcDto crmQrcDto, QrcForm inQrcForm, String param );

    CrmQrcDto searchAccessories( CrmQrcDto crmQrcDto, String param );

    void getBasePlanInfo( QrcForm qrcForm );

    CrmQrcDto activateCustomerBasePlan( QrcForm qrcForm, String userId );

    //    CrmQrcDto getTicketOtherDetails( CrmQrcDto crmQrcDto );
    CrmQrcDto mountBoosterPlan( QrcForm inQrcForm, String userID );

    ConfigDto getRemarks( ConfigDto inConfigDto );

    CrmQrcDto fetchActivatedVAS( CrmQrcDto inCrmQrcDto );

    CrmQrcDto activateDeactivateVAS( QrcForm inQrcForm, String inUserId );

    CrmQrcDto customerPlanReactivation( QrcForm qrcForm, String userId );

    CrmQrcDto cancelPlanMigration( QrcForm inQrcForm, String userId, String presentStage );

    CrmQrcDto getBillList( CrmQrcDto inCrmQrcDto );

    void trackPaymentHistory( QrcForm qrcForm, boolean inAll );

    CrmQrcDto getBillDetails( String customerId, String serviceType );

    List<CrmPlanMasterPojo> getMigratedActivationPlan( QrcForm qrcForm );

    List<CrmPlanMasterPojo> getAddonPlanDetails( QrcForm qrcForm );

    List<CrmPlanMasterPojo> getReActivationPlan( QrcForm qrcForm );

    CrmQrcDto getMigrationPolicy( CrmQrcDto crmQrcDto );

    CrmQrcDto getCustomerPriodicUsageDetails( CrmQrcDto inCrmQrcDto );

    void prepareDownloadResponse( HttpServletResponse inResponse,
                                  ExcelWriteUtils inExcelWriteUtils,
                                  ServletContext inServletContext,
                                  String directory );

    CrmQrcDto sendCustomerUsage( CrmQrcDto inCrmQrcDto );

    CrmQrcDto customerPlanRenewal( QrcForm qrcForm, String userId );

    CrmQrcDto sendEBill( CrmInvoiceDetailsPojo inInvoiceDetails, String inBrand );

    CrmQrcDto getCustAdditionDetails( CrmCustomerDetailsPojo inCustDetailsPojo );

    MasterDataDto getAddonPlanInfo( QrcForm qrcForm );

    CrmQrcDto getAddOnMigrationPolicy( CrmQrcDto crmQrcDto );

    CrmQrcDto removeCustomerAddonPlan( QrcForm inQrcForm, String inUserId );

    CrmQrcDto activateCustomerAddonPlan( QrcForm inQrcForm, String inUserId );

    CrmQrcDto saveTicketHistory( CrmTicketHistoryPojo inTicketHistory );

    CrmQrcDto ticketOperations( String inQrcActions, CrmQrcDto inCrmQrcDto );

    CrmQrcDto reopenTickets( CrmQrcDto crmQrcDto );

    void callFaultRepairAPI( String inTo, String inMessage, String inForm, String inOprator, String inDate );

    List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategories( String inStatus );

    CrmQrcDto getCustomerFeedback( CrmQrcDto inCrmQrcDto );
}
