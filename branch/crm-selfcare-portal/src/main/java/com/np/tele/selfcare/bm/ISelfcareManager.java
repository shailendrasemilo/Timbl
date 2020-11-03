package com.np.tele.selfcare.bm;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmFinanceDto;
import com.np.tele.crm.service.client.CrmPgwTransactionsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcDto;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.MasterDataDto;
import com.np.tele.crm.service.client.SelfcareDto;
import com.np.tele.crm.service.client.StatePojo;
import com.np.tele.selfcare.forms.SelfcareForm;
import com.np.tele.selfcare.forms.SelfcareLoginForm;
import com.np.tele.selfcare.forms.SelfcareQuickPayForm;

public interface ISelfcareManager
{
    SelfcareDto authenticate( SelfcareLoginForm inLoginForm );

    SelfcareDto forgotPassword( SelfcareLoginForm inLoginForm );

    SelfcareDto changePassword( SelfcareForm inSelfcareForm );

    CrmCapDto customerDetails( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo );

    CrmFinanceDto trackPaymentHistory( SelfcareForm inSelfcareForm );

    MasterDataDto getPlanInfo( SelfcareForm inSelfcareForm );

    MasterDataDto getAddonPlanInfo( SelfcareForm inSelfcareForm );

    CrmQrcDto getBillDetails( SelfcareForm inSelfcareForm );

    CrmQrcDto getBillOutstanding( CrmCustomerDetailsPojo inCustomerDetailsPojo );

    CrmQrcDto getCustomerTickets( SelfcareForm inSelfcareForm );

    CrmQrcDto getSelfcareSubjects( SelfcareForm inSelfcareForm );

    CrmQrcDto getSelfcareCategories( SelfcareForm inSelfcareForm );

    CrmQrcDto logTicket( SelfcareForm inSelfcareForm );

    SelfcareDto customerAccounts( SelfcareQuickPayForm inQuickPayForm );

    CrmCapDto saveCustomerProfileDetails( CrmCapDto inCrmCapDto );

    List<StatePojo> getStates( String inStatus );

    List<CityPojo> getCities( CityPojo inCityPojo );

    List<CrmQrcCategoriesPojo> getQrcCategories();

    CrmFinanceDto validateResponse( SelfcareQuickPayForm inQuickPayForm );

    CrmFinanceDto processPayment( SelfcareQuickPayForm inQuickPayForm );

    CrmFinanceDto postPayment( SelfcareQuickPayForm inQuickPayForm );

    List<CrmRcaReasonPojo> getPaymentOptions();

    CrmFinanceDto savePayment( SelfcareQuickPayForm inQuickPayForm );

    CrmFinanceDto getPaymentDetails( CrmPgwTransactionsPojo inPgwTransactionsPojo );

    CrmQrcDto getCustomerPriodicUsageDetails( CrmQrcDto inCrmQrcDto );

    CrmCapDto sendVerificationLink( CrmCapDto inCrmCapDto );

    CrmCapDto validateEmailToken( CrmCapDto inCapDto );

    void prepareDownloadResponse( HttpServletResponse inResponse,
                                  com.np.tele.crm.utils.ExcelWriteUtils inExcelWriteUtils,
                                  ServletContext inServletContext,
                                  String directory );

    List<String> getPincodeList( SelfcareQuickPayForm inQuickPayForm );

    ConfigDto getPaymentCenterDetails( SelfcareQuickPayForm inQuickPayForm );

    CrmQrcDto getBillDetails( String inCustomerId );

    MasterDataDto getBasePlanInfo( CrmPlanDetailsPojo inCrmPlanDetailsPojo );

    MasterDataDto getAddonPlanInfo( CrmPlanDetailsPojo inCrmPlanDetailsPojo );
}
