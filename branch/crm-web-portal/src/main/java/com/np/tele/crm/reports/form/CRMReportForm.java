package com.np.tele.crm.reports.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AreaPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmCustInteractionsPojo;
import com.np.tele.crm.service.client.CrmCustWaiverPojo;
import com.np.tele.crm.service.client.CrmCustomerActivityPojo;
import com.np.tele.crm.service.client.CrmMassOutagePojo;
import com.np.tele.crm.service.client.CrmQrcCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.InaReportPojo;
import com.np.tele.crm.service.client.LmsReportPojo;
import com.np.tele.crm.service.client.PaymentReportPojo;
import com.np.tele.crm.service.client.QrcTicketReportPojo;
import com.np.tele.crm.service.client.RfsReportPojo;
import com.np.tele.crm.service.client.SlaReportPojo;
import com.np.tele.crm.service.client.SocietyPojo;
import com.np.tele.crm.service.client.StatePojo;

public class CRMReportForm
    extends ActionForm
{
    private boolean                          toSearch = false;
    private String                           serviceType;
    private List<ContentPojo>                serviceTypeList;
    private String                           productType;
    private List<ContentPojo>                productTypeList;
    private CrmQrcCategoriesPojo             qrcCategory;
    private List<CrmQrcCategoriesPojo>       qrcCategoryList;
    private CrmQrcSubCategoriesPojo          qrcSubCategory;
    private List<CrmQrcSubCategoriesPojo>    qrcSubCategoryList;
    private CrmQrcSubSubCategoriesPojo       qrcSubSubCategory;
    private List<CrmQrcSubSubCategoriesPojo> qrcSubSubCategoryList;
    private Long                             functionalBinId;
    private List<CrmRcaReasonPojo>           functionalBins;
    private String                           toDate;
    private String                           fromDate;
    private Long                             qrcCategoryId;
    private Long                             qrcSubCategoryId;
    private Long                             qrcSubSubCategoryId;
    private List<CrmCustWaiverPojo>          custWaiverPojos;
    private List<InaReportPojo>              inaReportPojos;
    private List<CrmCustInteractionsPojo>    interactionPojos;
    private List<CrmCustomerActivityPojo>    crmCustomerActivityPojos;
    private List<QrcTicketReportPojo>        qrcReportPojos;
    private String                           qrcType;
    private String                           outageType;
    private String                           outageStatus;
    private List<LmsReportPojo>              lmsPojoList;
    private List<RfsReportPojo>              rfsPojoList;
    private List<ContentPojo>                installationStatusList;
    private List<ContentPojo>                paymentStatusList;
    private List<ContentPojo>                chequeStatusList;
    private List<ContentPojo>                entityTypeList;
    private List<ContentPojo>                paymentModeList;
    private List<ContentPojo>                paymentChannelList;
    private List<ContentPojo>                caseStatusList;
    private List<ContentPojo>                customerServiceTypeList;
    private String                           installationStatus;
    private String                           entityType;
    private String                           paymentMode;
    private String                           channelType;
    private String                           chequeStatus;
    private String                           paymentStatus;
    private String                           caseStatus;
    private String                           customerServiceType;
    private List<PaymentReportPojo>          paymentReportList;
    private String                           customerId;
    private String                           crfId;
    private List<CrmMassOutagePojo>          crmMassOutagePojos;
    private String                           userName;
    private List<SlaReportPojo>              slaReportPojos;
    private String                           postingStatus;
    private List<StatePojo>                  statePojoList;
    private List<CityPojo>                   cityPojoList;
    private List<AreaPojo>                   areaPojoList;
    private List<SocietyPojo>                societyPojoList;
    private String                           state;
    private String                           city;
    private String                           area;
    private String                           society;
    private String                           statusOfTicket;

    public boolean isToSearch()
    {
        return toSearch;
    }

    public void setToSearch( boolean inToSearch )
    {
        toSearch = inToSearch;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType( String inServiceType )
    {
        serviceType = inServiceType;
    }

    public List<ContentPojo> getServiceTypeList()
    {
        return serviceTypeList;
    }

    public void setServiceTypeList( List<ContentPojo> inServiceTypeList )
    {
        serviceTypeList = inServiceTypeList;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType( String inProductType )
    {
        productType = inProductType;
    }

    public List<ContentPojo> getProductTypeList()
    {
        return productTypeList;
    }

    public void setProductTypeList( List<ContentPojo> inProductTypeList )
    {
        productTypeList = inProductTypeList;
    }

    public CrmQrcCategoriesPojo getQrcCategory()
    {
        return qrcCategory;
    }

    public void setQrcCategory( CrmQrcCategoriesPojo inQrcCategory )
    {
        qrcCategory = inQrcCategory;
    }

    public List<CrmQrcCategoriesPojo> getQrcCategoryList()
    {
        return qrcCategoryList;
    }

    public void setQrcCategoryList( List<CrmQrcCategoriesPojo> inQrcCategoryList )
    {
        qrcCategoryList = inQrcCategoryList;
    }

    public CrmQrcSubCategoriesPojo getQrcSubCategory()
    {
        return qrcSubCategory;
    }

    public void setQrcSubCategory( CrmQrcSubCategoriesPojo inQrcSubCategory )
    {
        qrcSubCategory = inQrcSubCategory;
    }

    public List<CrmQrcSubCategoriesPojo> getQrcSubCategoryList()
    {
        return qrcSubCategoryList;
    }

    public void setQrcSubCategoryList( List<CrmQrcSubCategoriesPojo> inQrcSubCategoryList )
    {
        qrcSubCategoryList = inQrcSubCategoryList;
    }

    public CrmQrcSubSubCategoriesPojo getQrcSubSubCategory()
    {
        return qrcSubSubCategory;
    }

    public void setQrcSubSubCategory( CrmQrcSubSubCategoriesPojo inQrcSubSubCategory )
    {
        qrcSubSubCategory = inQrcSubSubCategory;
    }

    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategoryList()
    {
        return qrcSubSubCategoryList;
    }

    public void setQrcSubSubCategoryList( List<CrmQrcSubSubCategoriesPojo> inQrcSubSubCategoryList )
    {
        qrcSubSubCategoryList = inQrcSubSubCategoryList;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate( String inToDate )
    {
        toDate = inToDate;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate( String inFromDate )
    {
        fromDate = inFromDate;
    }

    public Long getFunctionalBinId()
    {
        return functionalBinId;
    }

    public void setFunctionalBinId( Long inFunctionalBinId )
    {
        functionalBinId = inFunctionalBinId;
    }

    public List<CrmRcaReasonPojo> getFunctionalBins()
    {
        return functionalBins;
    }

    public void setFunctionalBins( List<CrmRcaReasonPojo> inFunctionalBins )
    {
        functionalBins = inFunctionalBins;
    }

    public Long getQrcCategoryId()
    {
        return qrcCategoryId;
    }

    public void setQrcCategoryId( Long inQrcCategoryId )
    {
        qrcCategoryId = inQrcCategoryId;
    }

    public Long getQrcSubCategoryId()
    {
        return qrcSubCategoryId;
    }

    public void setQrcSubCategoryId( Long inQrcSubCategoryId )
    {
        qrcSubCategoryId = inQrcSubCategoryId;
    }

    public Long getQrcSubSubCategoryId()
    {
        return qrcSubSubCategoryId;
    }

    public void setQrcSubSubCategoryId( Long inQrcSubSubCategoryId )
    {
        qrcSubSubCategoryId = inQrcSubSubCategoryId;
    }

    public List<CrmCustWaiverPojo> getCustWaiverPojos()
    {
        return custWaiverPojos;
    }

    public void setCustWaiverPojos( List<CrmCustWaiverPojo> inCustWaiverPojos )
    {
        custWaiverPojos = inCustWaiverPojos;
    }

    public List<InaReportPojo> getInaReportPojos()
    {
        return inaReportPojos;
    }

    public void setInaReportPojos( List<InaReportPojo> inInaReportPojos )
    {
        inaReportPojos = inInaReportPojos;
    }

    public List<CrmCustInteractionsPojo> getInteractionPojos()
    {
        return interactionPojos;
    }

    public void setInteractionPojos( List<CrmCustInteractionsPojo> inInteractionPojos )
    {
        interactionPojos = inInteractionPojos;
    }

    public List<CrmCustomerActivityPojo> getCrmCustomerActivityPojos()
    {
        return crmCustomerActivityPojos;
    }

    public void setCrmCustomerActivityPojos( List<CrmCustomerActivityPojo> inCrmCustomerActivityPojos )
    {
        crmCustomerActivityPojos = inCrmCustomerActivityPojos;
    }

    public List<QrcTicketReportPojo> getQrcReportPojos()
    {
        return qrcReportPojos;
    }

    public void setQrcReportPojos( List<QrcTicketReportPojo> inQrcReportPojos )
    {
        qrcReportPojos = inQrcReportPojos;
    }

    public String getQrcType()
    {
        return qrcType;
    }

    public void setQrcType( String inQrcType )
    {
        qrcType = inQrcType;
    }

    public List<LmsReportPojo> getLmsPojoList()
    {
        return lmsPojoList;
    }

    public void setLmsPojoList( List<LmsReportPojo> inLmsPojoList )
    {
        lmsPojoList = inLmsPojoList;
    }

    public List<RfsReportPojo> getRfsPojoList()
    {
        return rfsPojoList;
    }

    public void setRfsPojoList( List<RfsReportPojo> rfsPojoList )
    {
        this.rfsPojoList = rfsPojoList;
    }

    public String getOutageType()
    {
        return outageType;
    }

    public void setOutageType( String inOutageType )
    {
        outageType = inOutageType;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName( String inUserName )
    {
        userName = inUserName;
    }

    public String getOutageStatus()
    {
        return outageStatus;
    }

    public void setOutageStatus( String inOutageStatus )
    {
        outageStatus = inOutageStatus;
    }

    public List<ContentPojo> getInstallationStatusList()
    {
        return installationStatusList;
    }

    public void setInstallationStatusList( List<ContentPojo> inInstallationStatusList )
    {
        installationStatusList = inInstallationStatusList;
    }

    public List<ContentPojo> getPaymentStatusList()
    {
        return paymentStatusList;
    }

    public void setPaymentStatusList( List<ContentPojo> inPaymentStatusList )
    {
        paymentStatusList = inPaymentStatusList;
    }

    public List<ContentPojo> getChequeStatusList()
    {
        return chequeStatusList;
    }

    public void setChequeStatusList( List<ContentPojo> inChequeStatusList )
    {
        chequeStatusList = inChequeStatusList;
    }

    public List<ContentPojo> getEntityTypeList()
    {
        return entityTypeList;
    }

    public void setEntityTypeList( List<ContentPojo> inEntityTypeList )
    {
        entityTypeList = inEntityTypeList;
    }

    public List<ContentPojo> getPaymentModeList()
    {
        return paymentModeList;
    }

    public void setPaymentModeList( List<ContentPojo> inPaymentModeList )
    {
        paymentModeList = inPaymentModeList;
    }

    public List<ContentPojo> getPaymentChannelList()
    {
        return paymentChannelList;
    }

    public void setPaymentChannelList( List<ContentPojo> inPaymentChannelList )
    {
        paymentChannelList = inPaymentChannelList;
    }

    public List<ContentPojo> getCaseStatusList()
    {
        return caseStatusList;
    }

    public void setCaseStatusList( List<ContentPojo> inCaseStatusList )
    {
        caseStatusList = inCaseStatusList;
    }

    public String getInstallationStatus()
    {
        return installationStatus;
    }

    public void setInstallationStatus( String inInstallationStatus )
    {
        installationStatus = inInstallationStatus;
    }

    public String getEntityType()
    {
        return entityType;
    }

    public void setEntityType( String inEntityType )
    {
        entityType = inEntityType;
    }

    public String getPaymentMode()
    {
        return paymentMode;
    }

    public void setPaymentMode( String inPaymentMode )
    {
        paymentMode = inPaymentMode;
    }

    public String getChannelType()
    {
        return channelType;
    }

    public void setChannelType( String inChannelType )
    {
        channelType = inChannelType;
    }

    public String getChequeStatus()
    {
        return chequeStatus;
    }

    public void setChequeStatus( String inChequeStatus )
    {
        chequeStatus = inChequeStatus;
    }

    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus( String inPaymentStatus )
    {
        paymentStatus = inPaymentStatus;
    }

    public String getCaseStatus()
    {
        return caseStatus;
    }

    public void setCaseStatus( String inCaseStatus )
    {
        caseStatus = inCaseStatus;
    }

    public List<PaymentReportPojo> getPaymentReportList()
    {
        return paymentReportList;
    }

    public void setPaymentReportList( List<PaymentReportPojo> inPaymentReportList )
    {
        paymentReportList = inPaymentReportList;
    }

    public List<ContentPojo> getCustomerServiceTypeList()
    {
        return customerServiceTypeList;
    }

    public void setCustomerServiceTypeList( List<ContentPojo> inCustomerServiceTypeList )
    {
        customerServiceTypeList = inCustomerServiceTypeList;
    }

    public String getCustomerServiceType()
    {
        return customerServiceType;
    }

    public void setCustomerServiceType( String inCustomerServiceType )
    {
        customerServiceType = inCustomerServiceType;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getCrfId()
    {
        return crfId;
    }

    public void setCrfId( String inCrfId )
    {
        crfId = inCrfId;
    }

    public List<CrmMassOutagePojo> getCrmMassOutagePojos()
    {
        return crmMassOutagePojos;
    }

    public void setCrmMassOutagePojos( List<CrmMassOutagePojo> inCrmMassOutagePojos )
    {
        crmMassOutagePojos = inCrmMassOutagePojos;
    }

    public List<SlaReportPojo> getSlaReportPojos()
    {
        return slaReportPojos;
    }

    public void setSlaReportPojos( List<SlaReportPojo> inSlaReportPojos )
    {
        slaReportPojos = inSlaReportPojos;
    }

    public String getPostingStatus()
    {
        return postingStatus;
    }

    public void setPostingStatus( String inPostingStatus )
    {
        postingStatus = inPostingStatus;
    }

    public List<StatePojo> getStatePojoList()
    {
        return statePojoList;
    }

    public void setStatePojoList( List<StatePojo> statePojoList )
    {
        this.statePojoList = statePojoList;
    }

    public List<CityPojo> getCityPojoList()
    {
        return cityPojoList;
    }

    public void setCityPojoList( List<CityPojo> cityPojoList )
    {
        this.cityPojoList = cityPojoList;
    }

    public List<AreaPojo> getAreaPojoList()
    {
        return areaPojoList;
    }

    public void setAreaPojoList( List<AreaPojo> areaPojoList )
    {
        this.areaPojoList = areaPojoList;
    }

    public List<SocietyPojo> getSocietyPojoList()
    {
        return societyPojoList;
    }

    public void setSocietyPojoList( List<SocietyPojo> societyPojoList )
    {
        this.societyPojoList = societyPojoList;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String area )
    {
        this.area = area;
    }

    public String getSociety()
    {
        return society;
    }

    public void setSociety( String society )
    {
        this.society = society;
    }

    public String getStatusOfTicket()
    {
        return statusOfTicket;
    }

    public void setStatusOfTicket( String inStatusOfTicket )
    {
        statusOfTicket = inStatusOfTicket;
    }
    
    
    
}
