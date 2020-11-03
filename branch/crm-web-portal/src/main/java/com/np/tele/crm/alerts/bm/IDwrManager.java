package com.np.tele.crm.alerts.bm;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmQrcActionTakenPojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcRootCausePojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.ProjectsPojo;

public interface IDwrManager
{
    public List<ProjectsPojo> getProject( String type );

    public List<PartnerPojo> getPartners();

    public List<CrmParameterPojo> getParameters( String type );

    public String checkProjectNameAvailability( String projectName );

    public String convertToStr( Object tmpltBody );

    public Map<String, String> getTemplate( Long id, String type );

    public Map<String, String> getSmsTemplate( Long id, String type );

    public Map<String, String> getEmailTemplate( Long id, String type );

    List<PartnerPojo> getBussinessPartners();

    List<ContentPojo> getProduct();

    public String getOption82String( String partnerId, String[] nameof82, String[] valueof82, String oltType );

    CrmCustomerDetailsPojo getCustomerDetails( String inCustCrfId, String idType );

    public String[] saveCustomerProfileDetails( String recordId,
                                                String changePropertyValue,
                                                String rolCategory,
                                                String custId,
                                                String userId,
                                                String inBrand );

    public String[] sendEmailVerificationLink( HttpSession inSession );

    List<CrmCmsFilePojo> getFileDetails( String todate, String fromDate, String fileType );

    public List<PartnerPojo> getProductByNPartner( String partnerName );

    List<ContentPojo> getCRFMasterCategories( String category, String subCategory );

    int getResolutionType( long inQrcCategoryId );

    String crfIdMasterValidation( String inCrfId, String inProduct );

    /*    List<String> getUsersByParameter( String inParam, String inValue, String inFunctionalBin );*/
    List<String> getUsersByParameter( String inParam,
                                      String inStateName,
                                      String inCityName,
                                      String inValue,
                                      String inFunctionalBin );

    //    String getConnectionTypeList( String inValue );
    //  List<CrmPartnerNetworkConfigPojo> getPoolName( long inRecordId, String nasPortId );
    //    List<CrmPartnerNetworkConfigPojo> getNasportId( long inRecordId );
    List<CrmQrcSubCategoriesPojo> getActiveQrcSubCategories( String categoryId );

    List<CrmQrcSubSubCategoriesPojo> getActiveQrcSubSubCategories( String categoryId, String subCategoryId );

    List<ContentPojo> getQrcActionsList( long categoryId, long subCategoryId, long subSubCategoryId );

    String getQRCType( long categoryId, long subCategoryId, long subSubCategoryId );

    long getFunctionalBinID( long categoryId, long subCategoryId, long subSubCategoryId );

    List<CrmRcaReasonPojo> getWhitelistReasons( String whitelistType );

    CrmPlanMasterPojo getPlanAmounts( String inPlanService, String inPlanCode, String inPlanType );

    List<ContentPojo> getFunctionalBinbyId( long inSubSubCategoryId, long inFunctionalBinId );

    List<String> getUsersByBinID( final long inFunctionalBinId );

    List<ContentPojo> getRca( final String product, final long inQrcCategoryID );

    List<ContentPojo> getRcaReason( final long rcaID );

    String getPrimaryMacAdd( String inSecondaryMacAdd );

    List<CrmRcaReasonPojo> getInteractionSubCategories( final String inCategory );

    List<CrmParameterPojo> getCrmParameterListOption82( String partnerId, String Product, String oltType );

    List<CrmQrcSubSubCategoriesPojo> getWaiverCategories( String type );

    LmsPojo getSocietyByPinCode( int pinCode );

    List<CrmQrcSubSubCategoriesPojo> getActiveQrcSubSubCategoriesByType( final String inQrcType,
                                                                         final String inCategory,
                                                                         final String inSubCategory );

    List<CrmQrcActionTakenPojo> getQrcRcaPojos( final long inCategoryId, final String inService );

    List<CrmQrcRootCausePojo> getResolutionCodes( long inRcaId );

    List<CrmQrcCategoriesPojo> getQrcRcaCategoryies();

    List<ContentPojo> getServiceNames();

    PartnerPojo getPartnerById( String inPartnerId );

    CrmUserPojo getUserById( String inUserId );

    //    List<CrmQrcSubCategoriesPojo> getQrcSubCategories( long categoryId );
    List<String> getNasportId( long inPartnerId, String inMasterName );

    List<CrmPartnerNetworkConfigPojo> getPoolName( long inPartnerDetailsId, String inMasterName, String inNasPortId );

    List<CrmRcaReasonPojo> getReasonsList( String type );

    List<ContentPojo> getServicePartner( String inNpId, String inProduct );

    boolean changeBinOwner( HttpSession session,
                            HttpServletRequest request,
                            long rowId,
                            String elementId,
                            String inboxOwner,
                            String currentStage,
                            String binChangeInbox,
                            String inboxType );

    String[] sendCustomerUsage( HttpSession inSession, HttpServletRequest inRequest, String usageType, String billPeriod );

    boolean isLmsDataFeasible( final String inState,
                               final String inCity,
                               final String inArea,
                               final String inLocality,
                               final String inSociety );

    public List<ContentPojo> getNassPortList( String networkPartnerId, String Product );

    public List<PartnerPojo> getPartnerByType( String product );

    public List<ContentPojo> getPaymentChannelByPaymentMode( String inPaymentMode );

    // boolean resetInboxSearch( HttpSession inSession );
    List<CrmQrcSubCategoriesPojo> filterSubCategories( HttpSession inSession, String inSubCategoryValue );

    long getCategoryIdBySubcategory( HttpSession inSession, long inSubcategoryId );

    int checkValidCRFNo( String crfNo );

    List<CrmQrcSubSubCategoriesPojo> filterSubSubCategories( HttpSession inSession,
                                                             String inCategoryId,
                                                             String inSubCategoryId,
                                                             String inSubSubCategoryValue );

    List<com.np.tele.crm.service.client.ContentPojo> getAddonPlanMigrationPolicy( String oldBasePlanCode,
                                                                                  String oldAddonPlanCode,
                                                                                  String newAddonPlanCode,
                                                                                  String serviceType,
                                                                                  String customerId );

    List<com.np.tele.crm.service.client.ContentPojo> getBasePlanMigrationPolicy( String oldBasePlanCode,
                                                                                 String newBasePlanCode,
                                                                                 String oldAddonPlanCode,
                                                                                 String serviceType,
                                                                                 String customerId );

    public long getRfsDus( HttpSession session, int partnerId );

    List<CrmUserPojo> getAssociatedSRWithBP( String product, long partnerId );

    List<String> getSecondaryMacAdd( long partnerId, String customerId );

    /*List<ContentPojo> getCaseStatusByPaymentMode( String inPaymentMode );*/
    List<CrmQrcCategoriesPojo> getCategoryiesByModuleType( String inModuleType );

    List<CrmQrcSubCategoriesPojo> getActiveSubCategoriesByModuleType( String inCategoryId, String inModuleType );
    
    List<CrmQrcSubSubCategoriesPojo> getQrcRcaSubSubCategoryies();
}
