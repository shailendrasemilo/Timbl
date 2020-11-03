package com.np.tele.crm.cap.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CommonCustDetailsPojo;
import com.np.tele.crm.service.client.CrmAdditionalDetailsPojo;
import com.np.tele.crm.service.client.CrmAddressDetailsPojo;
import com.np.tele.crm.service.client.CrmCustAadharNumberPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmDocumentDetailsPojo;
import com.np.tele.crm.service.client.CrmNationalityDetailsPojo;
import com.np.tele.crm.service.client.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.service.client.CrmOrderDetailsPojo;
import com.np.tele.crm.service.client.CrmParamDataPojo;
import com.np.tele.crm.service.client.CrmPartnerMappingPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanDetailsPojo;
import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUserPojo;
import com.np.tele.crm.service.client.LocalityPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;

public class CrmCapForm
    extends ActionForm
{
    private final Logger                      LOGGER                       = Logger.getLogger( CrmCapForm.class );
    private CrmAdditionalDetailsPojo          additionalDetailsPojo;
    private CrmAddressDetailsPojo             installationAddressPojo;
    private CrmAddressDetailsPojo             billingAddressPojo;
    private CrmCustomerDetailsPojo            customerDetailsPojo;
    private CrmDocumentDetailsPojo            documentDetailsPojo;
    private CrmNationalityDetailsPojo         nationalityDetailsPojo;
    //    private CrmNetworkDetailsPojo             networkDetailsPojo;
    private CrmOrderDetailsPojo               orderDetailsPojo;
    private CrmPaymentDetailsPojo             telecommunicationPayment;
    private CrmPaymentDetailsPojo             teleservicesPayment;
    private CrmPlanDetailsPojo                planDetailsPojo;
    private AppointmentPojo                   appointmentPojo;
    private List<StatePojo>                   statePojoList;
    private List<CityPojo>                    cityList;
    private List<AreaPojo>                    areaList;
    private List<LocalityPojo>                localityList;
    private List<SocietyPojo>                 societyList;
    private List<ContentPojo>                 connectionTypeList;
    private List<ContentPojo>                 visaTypeList;
    private List<ContentPojo>                 productTypeList;
    private List<ContentPojo>                 billingPreferences;
    private List<ContentPojo>                 paymentModes;
    private List<CrmPlanMasterPojo>           basePlanNames;
    private List<CrmPlanMasterPojo>           addonPlanNames;
    private List<ContentPojo>                 documents;
    private List<ContentPojo>                 addressProofs;
    //    private List<ContentPojo>            networkConnectivityInfo;
    private List<CrmRcaReasonPojo>            bankList;
    private String                            state;
    private String                            city;
    private String                            area;
    private String                            locality;
    private String                            society;
    private String                            feasiblelocSoc;
    private String                            crfTabId;
    private CrmAddressDetailsPojo             tempInstallationAddressPojo  = null;
    private CrmAddressDetailsPojo             tempBillingAddressPojo       = null;
    private CrmCustomerDetailsPojo            tempCustomerDetailsPojo      = null;
    private CrmDocumentDetailsPojo            tempDocumentDetailsPojo      = null;
    private CrmNationalityDetailsPojo         tempNationalityDetailsPojo   = null;
    private CrmOrderDetailsPojo               tempOrderDetailsPojo         = null;
    private CrmPaymentDetailsPojo             tempTelecommunicationPayment = null;
    private CrmPaymentDetailsPojo             tempTeleservicesPayment      = null;
    private CrmPlanDetailsPojo                tempPlanDetailsPojo          = null;
    private AppointmentPojo                   tempAppointmentPojo          = null;
    private CrmAdditionalDetailsPojo          tempAdditionalDetailsPojo    = null;
    private RemarksPojo                       remarksPojo;
    private List<PartnerPojo>                 networkPartnerList;
    private List<PartnerPojo>                 servicePartnerList;
    private List<CrmPartnerMappingPojo>       crmMappingList;
    private List<CrmRcaReasonPojo>            refuselReasonList;
    private List<CrmRcaReasonPojo>            rejectionReasonList;
    private List<CrmRcaReasonPojo>            erpReasonList;
    private boolean                           heading;
    //NetworkConfigurations Related
    private CrmNetworkConfigurationsPojo      crmNetworkConfigurations;
    private CrmNetworkConfigurationsPojo      tempNetworkConfigurations;
    private List<ContentPojo>                 ontPortList;
    private List<ContentPojo>                 onuPortList;
    private String                            hiddenProductType            = null;
    private String                            productType;
    private String                            partnerName;
    private List<RemarksPojo>                 remarksPojoList;
    private List<CrmParamDataPojo>            materialList;
    private CrmParamDataPojo                  paramDataPojo;
    private List<CrmParamDataPojo>            customerFeedBackList;
    private String                            forward                      = null;
    private String                            displayISRDate;
    private String                            tempReceiptNo;
    private List<CrmRcaReasonPojo>            nationalityType;
    private List<ContentPojo>                 serviceTypes;
    private List<PartnerPojo>                 bussinessPartners;
    private List<CrmUserPojo>                 crmUserPojos;
    private List<CrmPartnerNetworkConfigPojo> masterNameList;
    private String                            planService;
    private double                            minimumSecurityDeposit;
    private double                            minimumRentalCharge;
    private double                            miniTotalRental;
    private double                            miniTelesolutionAmount;
    private List<String>                      secondaryMACAddrList;
    private List<ContentPojo>                 oltTypeList;
    private String                            oltType;
    private String                            businessPartner;
    private String                            crmUserId;
    private String                            nasportID;
    private CrmPartnerNetworkConfigPojo       crmPartnerNetworkConfig;
    private List<CrmParamDataPojo>            crmParamDataPojos;
    private String                            planType;
    private String                            parameter;
    private PartnerPojo                       partner;
    private boolean                           addOnNotAllowed;
    private List<String>                      masterNames;
    private List<String>                      nasportIds;
    private List<String>                      userAssociatedServices;
    private List<String>                      userAssociatedPartners;
    private List<CommonCustDetailsPojo>       commonCustDetailsPojos;
    private List<CrmRcaReasonPojo>            cancellationReasonList;
    private CrmPaymentDetailsPojo             securityDepositPayment;
    private CrmPaymentDetailsPojo             tempSecurityDepositPayment   = null;
    private String                            connectableHomes;
    private boolean                           macFaulty;
    private String                            activationDate;
    private CrmCustAadharNumberPojo           aadharNumberPojo;                                                    ;

    public List<String> getUserAssociatedServices()
    {
        return userAssociatedServices;
    }

    public void setUserAssociatedServices( List<String> inUserAssociatedServices )
    {
        userAssociatedServices = inUserAssociatedServices;
    }

    public List<String> getUserAssociatedPartners()
    {
        return userAssociatedPartners;
    }

    public void setUserAssociatedPartners( List<String> inUserAssociatedPartners )
    {
        userAssociatedPartners = inUserAssociatedPartners;
    }

    public List<ContentPojo> getProductTypeList()
    {
        return productTypeList;
    }

    public void setProductTypeList( List<ContentPojo> productTypeList )
    {
        this.productTypeList = productTypeList;
    }

    public String getCrfTabId()
    {
        return crfTabId;
    }

    public void setCrfTabId( String inCrfTabId )
    {
        crfTabId = inCrfTabId;
    }

    public CrmAdditionalDetailsPojo getAdditionalDetailsPojo()
    {
        return additionalDetailsPojo;
    }

    public void setAdditionalDetailsPojo( CrmAdditionalDetailsPojo inAdditionalDetailsPojo )
    {
        additionalDetailsPojo = inAdditionalDetailsPojo;
    }

    public CrmAddressDetailsPojo getInstallationAddressPojo()
    {
        return installationAddressPojo;
    }

    public void setInstallationAddressPojo( CrmAddressDetailsPojo inInstallationAddressPojo )
    {
        installationAddressPojo = inInstallationAddressPojo;
    }

    public CrmAddressDetailsPojo getBillingAddressPojo()
    {
        return billingAddressPojo;
    }

    public void setBillingAddressPojo( CrmAddressDetailsPojo inBillingAddressPojo )
    {
        billingAddressPojo = inBillingAddressPojo;
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }

    public CrmDocumentDetailsPojo getDocumentDetailsPojo()
    {
        return documentDetailsPojo;
    }

    public void setDocumentDetailsPojo( CrmDocumentDetailsPojo inDocumentDetailsPojo )
    {
        documentDetailsPojo = inDocumentDetailsPojo;
    }

    public CrmNationalityDetailsPojo getNationalityDetailsPojo()
    {
        return nationalityDetailsPojo;
    }

    public void setNationalityDetailsPojo( CrmNationalityDetailsPojo inNationalityDetailsPojo )
    {
        nationalityDetailsPojo = inNationalityDetailsPojo;
    }

    public CrmOrderDetailsPojo getOrderDetailsPojo()
    {
        return orderDetailsPojo;
    }

    public void setOrderDetailsPojo( CrmOrderDetailsPojo inOrderDetailsPojo )
    {
        orderDetailsPojo = inOrderDetailsPojo;
    }

    public CrmPlanDetailsPojo getPlanDetailsPojo()
    {
        return planDetailsPojo;
    }

    public void setPlanDetailsPojo( CrmPlanDetailsPojo inPlanDetailsPojo )
    {
        planDetailsPojo = inPlanDetailsPojo;
    }

    public List<StatePojo> getStatePojoList()
    {
        return statePojoList;
    }

    public void setStatePojoList( List<StatePojo> inStatePojoList )
    {
        statePojoList = inStatePojoList;
    }

    public List<CityPojo> getCityList()
    {
        return cityList;
    }

    public void setCityList( List<CityPojo> inCityList )
    {
        cityList = inCityList;
    }

    public List<AreaPojo> getAreaList()
    {
        return areaList;
    }

    public void setAreaList( List<AreaPojo> inAreaList )
    {
        areaList = inAreaList;
    }

    public List<LocalityPojo> getLocalityList()
    {
        return localityList;
    }

    public void setLocalityList( List<LocalityPojo> inLocalityList )
    {
        localityList = inLocalityList;
    }

    public List<SocietyPojo> getSocietyList()
    {
        return societyList;
    }

    public void setSocietyList( List<SocietyPojo> inSocietyList )
    {
        societyList = inSocietyList;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String inCity )
    {
        city = inCity;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String inArea )
    {
        area = inArea;
    }

    public List<ContentPojo> getConnectionTypeList()
    {
        return connectionTypeList;
    }

    public void setConnectionTypeList( List<ContentPojo> inConnectionTypeList )
    {
        connectionTypeList = inConnectionTypeList;
    }

    public List<ContentPojo> getVisaTypeList()
    {
        return visaTypeList;
    }

    public void setVisaTypeList( List<ContentPojo> inVisaTypeList )
    {
        visaTypeList = inVisaTypeList;
    }

    public AppointmentPojo getAppointmentPojo()
    {
        return appointmentPojo;
    }

    public void setAppointmentPojo( AppointmentPojo inAppointmentPojo )
    {
        appointmentPojo = inAppointmentPojo;
    }

    public List<CrmPlanMasterPojo> getBasePlanNames()
    {
        return basePlanNames;
    }

    public void setBasePlanNames( List<CrmPlanMasterPojo> basePlanNames )
    {
        this.basePlanNames = basePlanNames;
    }

    public List<ContentPojo> getBillingPreferences()
    {
        return billingPreferences;
    }

    public void setBillingPreferences( List<ContentPojo> inBillingPreferences )
    {
        billingPreferences = inBillingPreferences;
    }

    public List<ContentPojo> getPaymentModes()
    {
        return paymentModes;
    }

    public void setPaymentModes( List<ContentPojo> inPaymentModes )
    {
        paymentModes = inPaymentModes;
    }

    public List<ContentPojo> getDocuments()
    {
        return documents;
    }

    public void setDocuments( List<ContentPojo> inDocuments )
    {
        documents = inDocuments;
    }

    public List<ContentPojo> getAddressProofs()
    {
        return addressProofs;
    }

    public void setAddressProofs( List<ContentPojo> inAddressProofs )
    {
        addressProofs = inAddressProofs;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String inState )
    {
        state = inState;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality( String inLocality )
    {
        locality = inLocality;
    }

    public String getSociety()
    {
        return society;
    }

    public void setSociety( String inSociety )
    {
        society = inSociety;
    }

    public String getFeasiblelocSoc()
    {
        return feasiblelocSoc;
    }

    public void setFeasiblelocSoc( String inFeasiblelocSoc )
    {
        feasiblelocSoc = inFeasiblelocSoc;
    }

    /*public List<ContentPojo> getNetworkConnectivityInfo()
    {
        return networkConnectivityInfo;
    }

    public void setNetworkConnectivityInfo( List<ContentPojo> inNetworkConnectivityInfo )
    {
        networkConnectivityInfo = inNetworkConnectivityInfo;
    }*/
    public CrmPaymentDetailsPojo getTelecommunicationPayment()
    {
        return telecommunicationPayment;
    }

    public void setTelecommunicationPayment( CrmPaymentDetailsPojo inTelecommunicationPayment )
    {
        telecommunicationPayment = inTelecommunicationPayment;
    }

    public CrmPaymentDetailsPojo getTeleservicesPayment()
    {
        return teleservicesPayment;
    }

    public void setTeleservicesPayment( CrmPaymentDetailsPojo inTeleservicesPayment )
    {
        teleservicesPayment = inTeleservicesPayment;
    }

    public CrmAddressDetailsPojo getTempInstallationAddressPojo()
    {
        return tempInstallationAddressPojo;
    }

    public void setTempInstallationAddressPojo( CrmAddressDetailsPojo inTempInstallationAddressPojo )
    {
        tempInstallationAddressPojo = inTempInstallationAddressPojo;
    }

    public CrmAddressDetailsPojo getTempBillingAddressPojo()
    {
        return tempBillingAddressPojo;
    }

    public void setTempBillingAddressPojo( CrmAddressDetailsPojo inTempBillingAddressPojo )
    {
        tempBillingAddressPojo = inTempBillingAddressPojo;
    }

    public CrmCustomerDetailsPojo getTempCustomerDetailsPojo()
    {
        return tempCustomerDetailsPojo;
    }

    public void setTempCustomerDetailsPojo( CrmCustomerDetailsPojo inTempCustomerDetailsPojo )
    {
        tempCustomerDetailsPojo = inTempCustomerDetailsPojo;
    }

    public CrmDocumentDetailsPojo getTempDocumentDetailsPojo()
    {
        return tempDocumentDetailsPojo;
    }

    public void setTempDocumentDetailsPojo( CrmDocumentDetailsPojo inTempDocumentDetailsPojo )
    {
        tempDocumentDetailsPojo = inTempDocumentDetailsPojo;
    }

    public CrmNationalityDetailsPojo getTempNationalityDetailsPojo()
    {
        return tempNationalityDetailsPojo;
    }

    public void setTempNationalityDetailsPojo( CrmNationalityDetailsPojo inTempNationalityDetailsPojo )
    {
        tempNationalityDetailsPojo = inTempNationalityDetailsPojo;
    }

    //    public CrmNetworkDetailsPojo getTempNetworkDetailsPojo()
    //    {
    //        return tempNetworkDetailsPojo;
    //    }
    //
    //    public void setTempNetworkDetailsPojo( CrmNetworkDetailsPojo inTempNetworkDetailsPojo )
    //    {
    //        tempNetworkDetailsPojo = inTempNetworkDetailsPojo;
    //    }
    public CrmOrderDetailsPojo getTempOrderDetailsPojo()
    {
        return tempOrderDetailsPojo;
    }

    public void setTempOrderDetailsPojo( CrmOrderDetailsPojo inTempOrderDetailsPojo )
    {
        tempOrderDetailsPojo = inTempOrderDetailsPojo;
    }

    public CrmPaymentDetailsPojo getTempTelecommunicationPayment()
    {
        return tempTelecommunicationPayment;
    }

    public void setTempTelecommunicationPayment( CrmPaymentDetailsPojo inTempTelecommunicationPayment )
    {
        tempTelecommunicationPayment = inTempTelecommunicationPayment;
    }

    public CrmPaymentDetailsPojo getTempTeleservicesPayment()
    {
        return tempTeleservicesPayment;
    }

    public void setTempTeleservicesPayment( CrmPaymentDetailsPojo inTempTeleservicesPayment )
    {
        tempTeleservicesPayment = inTempTeleservicesPayment;
    }

    public CrmPlanDetailsPojo getTempPlanDetailsPojo()
    {
        return tempPlanDetailsPojo;
    }

    public void setTempPlanDetailsPojo( CrmPlanDetailsPojo inTempPlanDetailsPojo )
    {
        tempPlanDetailsPojo = inTempPlanDetailsPojo;
    }

    public AppointmentPojo getTempAppointmentPojo()
    {
        return tempAppointmentPojo;
    }

    public void setTempAppointmentPojo( AppointmentPojo inTempAppointmentPojo )
    {
        tempAppointmentPojo = inTempAppointmentPojo;
    }

    public CrmAdditionalDetailsPojo getTempAdditionalDetailsPojo()
    {
        return tempAdditionalDetailsPojo;
    }

    public void setTempAdditionalDetailsPojo( CrmAdditionalDetailsPojo inTempAdditionalDetailsPojo )
    {
        tempAdditionalDetailsPojo = inTempAdditionalDetailsPojo;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public List<PartnerPojo> getNetworkPartnerList()
    {
        return networkPartnerList;
    }

    public void setNetworkPartnerList( List<PartnerPojo> inNetworkPartnerList )
    {
        networkPartnerList = inNetworkPartnerList;
    }

    public List<CrmRcaReasonPojo> getRefuselReasonList()
    {
        return refuselReasonList;
    }

    public void setRefuselReasonList( List<CrmRcaReasonPojo> inRefuselReasonList )
    {
        refuselReasonList = inRefuselReasonList;
    }

    public boolean isHeading()
    {
        return heading;
    }

    public void setHeading( boolean heading )
    {
        this.heading = heading;
    }

    public List<PartnerPojo> getServicePartnerList()
    {
        return servicePartnerList;
    }

    public void setServicePartnerList( List<PartnerPojo> inServicePartnerList )
    {
        servicePartnerList = inServicePartnerList;
    }

    public CrmNetworkConfigurationsPojo getCrmNetworkConfigurations()
    {
        return crmNetworkConfigurations;
    }

    public void setCrmNetworkConfigurations( CrmNetworkConfigurationsPojo inCrmNetworkConfigurations )
    {
        crmNetworkConfigurations = inCrmNetworkConfigurations;
    }

    public List<ContentPojo> getOntPortList()
    {
        return ontPortList;
    }

    public void setOntPortList( List<ContentPojo> inOntPortList )
    {
        ontPortList = inOntPortList;
    }

    public List<ContentPojo> getOnuPortList()
    {
        return onuPortList;
    }

    public void setOnuPortList( List<ContentPojo> inOnuPortList )
    {
        onuPortList = inOnuPortList;
    }

    public String getHiddenProductType()
    {
        return hiddenProductType;
    }

    public void setHiddenProductType( String inHiddenProductType )
    {
        hiddenProductType = inHiddenProductType;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType( String inProductType )
    {
        productType = inProductType;
    }

    public String getPartnerName()
    {
        return partnerName;
    }

    public void setPartnerName( String inPartnerName )
    {
        partnerName = inPartnerName;
    }

    public List<RemarksPojo> getRemarksPojoList()
    {
        return remarksPojoList;
    }

    public void setRemarksPojoList( List<RemarksPojo> inRemarksPojoList )
    {
        remarksPojoList = inRemarksPojoList;
    }

    public List<CrmRcaReasonPojo> getBankList()
    {
        return bankList;
    }

    public void setBankList( List<CrmRcaReasonPojo> inBankList )
    {
        bankList = inBankList;
    }

    public List<CrmPartnerMappingPojo> getCrmMappingList()
    {
        return crmMappingList;
    }

    public void setCrmMappingList( List<CrmPartnerMappingPojo> inCrmMappingList )
    {
        crmMappingList = inCrmMappingList;
    }

    public List<CrmParamDataPojo> getMaterialList()
    {
        return materialList;
    }

    public void setMaterialList( List<CrmParamDataPojo> inMaterialList )
    {
        materialList = inMaterialList;
    }

    public CrmParamDataPojo getParamDataPojo()
    {
        return paramDataPojo;
    }

    public void setParamDataPojo( CrmParamDataPojo inParamDataPojo )
    {
        paramDataPojo = inParamDataPojo;
    }

    public List<CrmParamDataPojo> getCustomerFeedBackList()
    {
        return customerFeedBackList;
    }

    public void setCustomerFeedBackList( List<CrmParamDataPojo> inCustomerFeedBackList )
    {
        customerFeedBackList = inCustomerFeedBackList;
    }

    public String getForward()
    {
        return forward;
    }

    public CrmNetworkConfigurationsPojo getTempNetworkConfigurations()
    {
        return tempNetworkConfigurations;
    }

    public void setTempNetworkConfigurations( CrmNetworkConfigurationsPojo inTempNetworkConfigurations )
    {
        tempNetworkConfigurations = inTempNetworkConfigurations;
    }

    public void setForward( String inForward )
    {
        forward = inForward;
    }

    public String getDisplayISRDate()
    {
        return displayISRDate;
    }

    public void setDisplayISRDate( String inDisplayISRDate )
    {
        displayISRDate = inDisplayISRDate;
    }

    public String getTempReceiptNo()
    {
        return tempReceiptNo;
    }

    public void setTempReceiptNo( String tempReceiptNo )
    {
        this.tempReceiptNo = tempReceiptNo;
    }

    public List<CrmRcaReasonPojo> getNationalityType()
    {
        return nationalityType;
    }

    public void setNationalityType( List<CrmRcaReasonPojo> inNationalityType )
    {
        nationalityType = inNationalityType;
    }

    public List<ContentPojo> getServiceTypes()
    {
        return serviceTypes;
    }

    public void setServiceTypes( List<ContentPojo> inCustomerServiceTypes )
    {
        serviceTypes = inCustomerServiceTypes;
    }

    public List<PartnerPojo> getBussinessPartners()
    {
        return bussinessPartners;
    }

    public void setBussinessPartners( List<PartnerPojo> inBussinessPartners )
    {
        bussinessPartners = inBussinessPartners;
    }

    public List<CrmUserPojo> getCrmUserPojos()
    {
        return crmUserPojos;
    }

    public void setCrmUserPojos( List<CrmUserPojo> inCrmUserPojos )
    {
        crmUserPojos = inCrmUserPojos;
    }

    public List<CrmPlanMasterPojo> getAddonPlanNames()
    {
        return addonPlanNames;
    }

    public void setAddonPlanNames( List<CrmPlanMasterPojo> addonPlanNames )
    {
        this.addonPlanNames = addonPlanNames;
    }

    public List<CrmPartnerNetworkConfigPojo> getMasterNameList()
    {
        return masterNameList;
    }

    public void setMasterNameList( List<CrmPartnerNetworkConfigPojo> inMasterNameList )
    {
        masterNameList = inMasterNameList;
    }

    public String getPlanService()
    {
        return planService;
    }

    public void setPlanService( String inPlanService )
    {
        planService = inPlanService;
    }

    public List<String> getSecondaryMACAddrList()
    {
        return secondaryMACAddrList;
    }

    public void setSecondaryMACAddrList( List<String> secondaryMACAddrList )
    {
        this.secondaryMACAddrList = secondaryMACAddrList;
    }

    public List<ContentPojo> getOltTypeList()
    {
        return oltTypeList;
    }

    public void setOltTypeList( List<ContentPojo> inOltTypeList )
    {
        oltTypeList = inOltTypeList;
    }

    public String getOltType()
    {
        return oltType;
    }

    public void setOltType( String inOltType )
    {
        oltType = inOltType;
    }

    public String getBusinessPartner()
    {
        return businessPartner;
    }

    public void setBusinessPartner( String businessPartner )
    {
        this.businessPartner = businessPartner;
    }

    public String getCrmUserId()
    {
        return crmUserId;
    }

    public void setCrmUserId( String inCrmUserId )
    {
        crmUserId = inCrmUserId;
    }

    public double getMinimumSecurityDeposit()
    {
        return minimumSecurityDeposit;
    }

    public void setMinimumSecurityDeposit( double inMinimumSecurityDeposit )
    {
        minimumSecurityDeposit = inMinimumSecurityDeposit;
    }

    public double getMinimumRentalCharge()
    {
        return minimumRentalCharge;
    }

    public void setMinimumRentalCharge( double inMinimumRentalCharge )
    {
        minimumRentalCharge = inMinimumRentalCharge;
    }

    public double getMiniTotalRental()
    {
        return miniTotalRental;
    }

    public void setMiniTotalRental( double inMiniTotalRental )
    {
        miniTotalRental = inMiniTotalRental;
    }

    public double getMiniTelesolutionAmount()
    {
        return miniTelesolutionAmount;
    }

    public void setMiniTelesolutionAmount( double inMiniTelesolutionAmount )
    {
        miniTelesolutionAmount = inMiniTelesolutionAmount;
    }

    public List<CrmRcaReasonPojo> getRejectionReasonList()
    {
        return rejectionReasonList;
    }

    public void setRejectionReasonList( List<CrmRcaReasonPojo> rejectionReasonList )
    {
        this.rejectionReasonList = rejectionReasonList;
    }

    public String getNasportID()
    {
        return nasportID;
    }

    public void setNasportID( String nasportID )
    {
        this.nasportID = nasportID;
    }

    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfig()
    {
        return crmPartnerNetworkConfig;
    }

    public void setCrmPartnerNetworkConfig( CrmPartnerNetworkConfigPojo inCrmPartnerNetworkConfig )
    {
        crmPartnerNetworkConfig = inCrmPartnerNetworkConfig;
    }

    public List<CrmParamDataPojo> getCrmParamDataPojos()
    {
        return crmParamDataPojos;
    }

    public void setCrmParamDataPojos( List<CrmParamDataPojo> inCrmParamDataPojos )
    {
        crmParamDataPojos = inCrmParamDataPojos;
    }

    public String getPlanType()
    {
        return planType;
    }

    public void setPlanType( String inPlanType )
    {
        planType = inPlanType;
    }

    public String getParameter()
    {
        return parameter;
    }

    public void setParameter( String inParameter )
    {
        parameter = inParameter;
    }

    public PartnerPojo getPartner()
    {
        return partner;
    }

    public void setPartner( PartnerPojo inPartner )
    {
        partner = inPartner;
    }

    public boolean isAddOnNotAllowed()
    {
        return addOnNotAllowed;
    }

    public void setAddOnNotAllowed( boolean inAddOnNotAllowed )
    {
        addOnNotAllowed = inAddOnNotAllowed;
    }

    public List<String> getMasterNames()
    {
        return masterNames;
    }

    public void setMasterNames( List<String> inMasterNames )
    {
        masterNames = inMasterNames;
    }

    public List<String> getNasportIds()
    {
        return nasportIds;
    }

    public void setNasportIds( List<String> inNasportIds )
    {
        nasportIds = inNasportIds;
    }

    public List<CommonCustDetailsPojo> getCommonCustDetailsPojos()
    {
        return commonCustDetailsPojos;
    }

    public void setCommonCustDetailsPojos( List<CommonCustDetailsPojo> inCommonCustDetailsPojos )
    {
        commonCustDetailsPojos = inCommonCustDetailsPojos;
    }

    public List<CrmRcaReasonPojo> getCancellationReasonList()
    {
        return cancellationReasonList;
    }

    public void setCancellationReasonList( List<CrmRcaReasonPojo> cancellationReasonList )
    {
        this.cancellationReasonList = cancellationReasonList;
    }

    public CrmPaymentDetailsPojo getSecurityDepositPayment()
    {
        return securityDepositPayment;
    }

    public void setSecurityDepositPayment( CrmPaymentDetailsPojo inSecurityDepositPayment )
    {
        securityDepositPayment = inSecurityDepositPayment;
    }

    public CrmPaymentDetailsPojo getTempSecurityDepositPayment()
    {
        return tempSecurityDepositPayment;
    }

    public void setTempSecurityDepositPayment( CrmPaymentDetailsPojo inTempSecurityDepositPayment )
    {
        tempSecurityDepositPayment = inTempSecurityDepositPayment;
    }

    public String getConnectableHomes()
    {
        return connectableHomes;
    }

    public void setConnectableHomes( String inConnectableHomes )
    {
        connectableHomes = inConnectableHomes;
    }

    public boolean isMacFaulty()
    {
        return macFaulty;
    }

    public void setMacFaulty( boolean inMacFaulty )
    {
        macFaulty = inMacFaulty;
    }

    public String getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate( String inActivationDate )
    {
        activationDate = inActivationDate;
    }

    public List<CrmRcaReasonPojo> getErpReasonList()
    {
        return erpReasonList;
    }

    public void setErpReasonList( List<CrmRcaReasonPojo> erpReasonList )
    {
        this.erpReasonList = erpReasonList;
    }

    public CrmCustAadharNumberPojo getAadharNumberPojo()
    {
        return aadharNumberPojo;
    }

    public void setAadharNumberPojo( CrmCustAadharNumberPojo inAadharNumberPojo )
    {
        aadharNumberPojo = inAadharNumberPojo;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "Inside reset of InA form" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "Method is :" + methodName );
        CrmCapFormHelper.resetInAForm( this, methodName );
    }

    /*    @Override
        public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
        {
            LOGGER.info( "In validate method........" );
            String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
            LOGGER.info( "In validate method........" + method );
            ActionErrors actionError = new ActionErrors();
            CrmCapFormHelper.validateIandAForm( method, actionError, this );
            return actionError;
        }*/
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCapForm [LOGGER=" ).append( LOGGER ).append( ", additionalDetailsPojo=" )
                .append( additionalDetailsPojo ).append( ", installationAddressPojo=" )
                .append( installationAddressPojo ).append( ", billingAddressPojo=" ).append( billingAddressPojo )
                .append( ", customerDetailsPojo=" ).append( customerDetailsPojo ).append( ", documentDetailsPojo=" )
                .append( documentDetailsPojo ).append( ", nationalityDetailsPojo=" ).append( nationalityDetailsPojo )
                .append( ", orderDetailsPojo=" ).append( orderDetailsPojo ).append( ", telecommunicationPayment=" )
                .append( telecommunicationPayment ).append( ", teleservicesPayment=" ).append( teleservicesPayment )
                .append( ", planDetailsPojo=" ).append( planDetailsPojo ).append( ", appointmentPojo=" )
                .append( appointmentPojo ).append( ", statePojoList=" ).append( statePojoList ).append( ", cityList=" )
                .append( cityList ).append( ", areaList=" ).append( areaList ).append( ", localityList=" )
                .append( localityList ).append( ", connectionTypeList=" ).append( connectionTypeList )
                .append( ", visaTypeList=" ).append( visaTypeList ).append( ", productTypeList=" )
                .append( productTypeList ).append( ", billingPreferences=" ).append( billingPreferences )
                .append( ", paymentModes=" ).append( paymentModes ).append( ", basePlanNames=" ).append( basePlanNames )
                .append( ", addonPlanNames=" ).append( addonPlanNames ).append( ", documents=" ).append( documents )
                .append( ", addressProofs=" ).append( addressProofs ).append( ", bankList=" ).append( bankList )
                .append( ", state=" ).append( state ).append( ", city=" ).append( city ).append( ", area=" )
                .append( area ).append( ", locality=" ).append( locality ).append( ", society=" ).append( society )
                .append( ", crfTabId=" ).append( crfTabId ).append( ", tempInstallationAddressPojo=" )
                .append( tempInstallationAddressPojo ).append( ", tempBillingAddressPojo=" )
                .append( tempBillingAddressPojo ).append( ", tempCustomerDetailsPojo=" )
                .append( tempCustomerDetailsPojo ).append( ", tempDocumentDetailsPojo=" )
                .append( tempDocumentDetailsPojo ).append( ", tempNationalityDetailsPojo=" )
                .append( tempNationalityDetailsPojo ).append( ", tempOrderDetailsPojo=" ).append( tempOrderDetailsPojo )
                .append( ", tempTelecommunicationPayment=" ).append( tempTelecommunicationPayment )
                .append( ", tempTeleservicesPayment=" ).append( tempTeleservicesPayment )
                .append( ", tempPlanDetailsPojo=" ).append( tempPlanDetailsPojo ).append( ", tempAppointmentPojo=" )
                .append( tempAppointmentPojo ).append( ", tempAdditionalDetailsPojo=" )
                .append( tempAdditionalDetailsPojo ).append( ", remarksPojo=" ).append( remarksPojo )
                .append( ", networkPartnerList=" ).append( networkPartnerList ).append( ", servicePartnerList=" )
                .append( servicePartnerList ).append( ", crmMappingList=" ).append( crmMappingList )
                .append( ", refuselReasonList=" ).append( refuselReasonList ).append( ", rejectionReasonList=" )
                .append( rejectionReasonList ).append( ", heading=" ).append( heading )
                .append( ", crmNetworkConfigurations=" ).append( crmNetworkConfigurations )
                .append( ", tempNetworkConfigurations=" ).append( tempNetworkConfigurations ).append( ", ontPortList=" )
                .append( ontPortList ).append( ", onuPortList=" ).append( onuPortList ).append( ", hiddenProductType=" )
                .append( hiddenProductType ).append( ", productType=" ).append( productType ).append( ", partnerName=" )
                .append( partnerName ).append( ", remarksPojoList=" ).append( remarksPojoList )
                .append( ", materialList=" ).append( materialList ).append( ", paramDataPojo=" ).append( paramDataPojo )
                .append( ", customerFeedBackList=" ).append( customerFeedBackList ).append( ", forward=" )
                .append( forward ).append( ", displayISRDate=" ).append( displayISRDate ).append( ", tempReceiptNo=" )
                .append( tempReceiptNo ).append( ", nationalityType=" ).append( nationalityType )
                .append( ", serviceTypes=" ).append( serviceTypes ).append( ", bussinessPartners=" )
                .append( bussinessPartners ).append( ", crmUserPojos=" ).append( crmUserPojos )
                .append( ", masterNameList=" ).append( masterNameList ).append( ", planService=" ).append( planService )
                .append( ", minimumSecurityDeposit=" ).append( minimumSecurityDeposit )
                .append( ", minimumRentalCharge=" ).append( minimumRentalCharge ).append( ", miniTotalRental=" )
                .append( miniTotalRental ).append( ", miniTelesolutionAmount=" ).append( miniTelesolutionAmount )
                .append( ", secondaryMACAddrList=" ).append( secondaryMACAddrList ).append( ", oltTypeList=" )
                .append( oltTypeList ).append( ", oltType=" ).append( oltType ).append( ", businessPartner=" )
                .append( businessPartner ).append( ", crmUserId=" ).append( crmUserId ).append( ", nasportID=" )
                .append( nasportID ).append( ", crmPartnerNetworkConfig=" ).append( crmPartnerNetworkConfig )
                .append( ", crmParamDataPojos=" ).append( crmParamDataPojos ).append( ", planType=" ).append( planType )
                .append( ", parameter=" ).append( parameter ).append( ", partner=" ).append( partner ).append( "]" );
        return builder.toString();
    }
}
