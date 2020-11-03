package com.np.tele.crm.cap.bm;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.DocumentException;
import com.np.tele.crm.cap.form.CrmCapForm;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.ConfigDto;
import com.np.tele.crm.service.client.CrmCapDto;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmParamDataPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;

public interface ICrmCapManager
{
    public CrmCapDto searchCrfDetails( CrmCapForm inCapForm, String inString );

    public CrmCapDto saveCustomerBasicInfo( CrmCapForm inCrmCapForm, String inUserId );

    public void setCRFDetails( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm );

    public CrmCapDto submitCRFDetails( CrmCapForm inCrmCapForm, String inUserId );

    public CrmCapDto saveValidatedCRFEntry( CrmCapForm inCrmCapForm, String inUserId );

    public void setInstallationGISDetails( CrmCapForm inCrmCapForm, boolean toDisplay );

    public CrmCapDto saveNetworkDetails( CrmCapForm inCrmCapForm, String inUserId );

    public void setNetworkPartnersByProduct( CrmCapForm inCrmCapForm, String inProduct );

    public List<CrmPartnerMappingPojo> getServicePartnerList( CrmCapForm inCrmCapForm );

    public CrmCapDto saveMapMacIdDetails( CrmCapForm inCrmCapForm, String inUserId )
        throws Exception;

    public List<CrmParamDataPojo> getCustomerFeedBackList( CrmCapForm inCrmCapForm );

    public List<CrmParamDataPojo> getMaterialList( CrmCapForm inCrmCapForm );

    public void generateISRPdf( HttpServletResponse inResponse,
                                String iconPath,
                                String checkBoxPath,
                                String checkBoxYesPath,
                                CrmCapForm form )
        throws DocumentException, IOException;

    public String[] generalInformationArray( CrmCapForm form );

    public String[] installationSiteInformationArray( CrmCapForm form );

    public List<CrmPartnerNetworkConfigPojo> getMasterNameList( long inPartnerId, String inServiceName );

    public void getInboxData( CrmCapForm inCrmCapForm, String inRecordId, String inCrfStage, String inRequestType );

    void setBillingGISDetails( CrmCapForm inCrmCapForm, boolean inToDisplay );

    public CrmCapDto saveCRFReference( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo, String inUserId );

    public CrmCapDto saveISRReferenceNo( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo, String inUserId );

    CrmCapDto viewCrfDetails( CrmCapForm inCapForm, String inUserId );

    public CrmCapDto bindCPEMACId( CrmCapForm inCrmCapForm, String inUserId );

    public CrmCapDto saveDeviceStatus( CrmCapForm inCrmCapForm, String inUserId );

    public ConfigDto getActivityLogs( String inRecordId );

    public CrmCapDto getCustomerBySociety( CrmCapForm inForm );

    CrmCapDto trackCustomerProfileDetails( long inCustomerRecordId, String inCRFId, String inCustomerId );

    CrmCapDto validateCrfInCustomerDetails( String inCrfId );

    CrmCapDto checkCrfInLinkToCrf( String inCrfId );

    public CrmCapDto punchISR( CrmCapForm inCrmCapForm, String inUserId )
        throws Exception;

    public ConfigDto listNPUploadedDoc( String inModule, String inMapping );

    void setPredefineList( CrmCapDto inCrmCapDto,
                           CrmCapForm inCrmCapForm,
                           List<ContentPojo> inAllAddProofList,
                           List<ContentPojo> inAllIdProofList );

    void setActivationPlans( CrmCapDto inCrmCapDto, CrmCapForm inCrmCapForm );

    public void setUserPojos( CrmCapForm inCrmCapForm );

    CrmCapDto getCAFReferenceNo( CrmCustomerDetailsPojo inCrmCustomerDetailsPojo );
}
