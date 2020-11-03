package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlTransient;

import com.np.tele.crm.ext.pojos.CommonCustDetailsPojo;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.CrmAdditionalDetailsPojo;
import com.np.tele.crm.pojos.CrmAddressDetailsPojo;
import com.np.tele.crm.pojos.CrmCustAadharNumberPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmDocumentDetailsPojo;
import com.np.tele.crm.pojos.CrmNationalityDetailsPojo;
import com.np.tele.crm.pojos.CrmNetworkConfigurationsPojo;
import com.np.tele.crm.pojos.CrmOrderDetailsPojo;
import com.np.tele.crm.pojos.CrmParamDataPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPlanDetailsPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class CrmCapDto
    implements Serializable
{
    private CrmCustomerDetailsPojo             customerDetailsPojo;
    private CrmAdditionalDetailsPojo           additionalDetailsPojo;
    private CrmAddressDetailsPojo              addressDetailsPojo;
    private CrmPlanDetailsPojo                 planDetailsPojo;
    private CrmDocumentDetailsPojo             documentDetailsPojo;
    private CrmNationalityDetailsPojo          nationalityDetailsPojo;
    //    private CrmNetworkDetailsPojo              networkDetailsPojo;
    private CrmOrderDetailsPojo                orderDetailsPojo;
    private CrmPaymentDetailsPojo              paymentDetailsPojo;
    private AppointmentPojo                    appointmentPojo;
    private List<CrmCustomerDetailsPojo>       customerDetailsPojosList;
    private List<CrmAdditionalDetailsPojo>     additionalDetailsPojosList;
    private List<CrmAddressDetailsPojo>        addressDetailsPojosList;
    private List<CrmPlanDetailsPojo>           planDetailsPojosList;
    private List<CrmDocumentDetailsPojo>       documentDetailsPojosList;
    private List<CrmNationalityDetailsPojo>    nationalityDetailsPojosList;
    //    private List<CrmNetworkDetailsPojo>        networkDetailsPojosList;
    private List<CrmOrderDetailsPojo>          orderDetailsPojosList;
    private List<CrmPaymentDetailsPojo>        paymentDetailsPojosList;
    private List<AppointmentPojo>              appointmentPojos;
    private List<CrmNetworkConfigurationsPojo> networkConfigurationsList;
    private CrmNetworkConfigurationsPojo       networkConfigurationsPojo;
    private String                             statusCode              = null;
    private String                             statusDesc              = null;
    private String                             crfID                   = null;
    private LMSPojo                            lmsPojo;
    private String                             userId                  = null;
    private long                               customerRecordId;
    private String                             toStage                 = null;
    private RemarksPojo                        remarksPojo;
    private List<RemarksPojo>                  remarksPojos            = null;
    private List<CrmParamDataPojo>             crmParamDataPojos;
    private String                             changeRequest;
    private String                             clientIPAddress         = null;
    private String                             serverIPAddress         = null;
    private String                             activityAction;
    private String                             customerId;
    private String                             generatedTicketId;
    private PartnerPojo                        salesPartner            = null;
    private String                             billingErrorCode        = null;
    private PartnerPojo                        networkPartner          = null;
    private CrmPartnerNetworkConfigPojo        crmPartnerNetworkConfig = null;
    private List<String>                       userAssociatedServices;
    private List<String>                       userAssociatedPartners;
    private List<CommonCustDetailsPojo>        commonCustDetailsPojos;
    private boolean                            macFaulty;
    private CrmCustAadharNumberPojo            aadharNumberPojo;

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

    public List<CrmParamDataPojo> getCrmParamDataPojos()
    {
        return crmParamDataPojos;
    }

    public void setCrmParamDataPojos( List<CrmParamDataPojo> crmParamDataPojos )
    {
        this.crmParamDataPojos = crmParamDataPojos;
    }

    public List<RemarksPojo> getRemarksPojos()
    {
        return remarksPojos;
    }

    public void setRemarksPojos( List<RemarksPojo> remarksPojos )
    {
        this.remarksPojos = remarksPojos;
    }

    public String getToStage()
    {
        return toStage;
    }

    public void setToStage( String toStage )
    {
        this.toStage = toStage;
    }

    public long getCustomerRecordId()
    {
        return customerRecordId;
    }

    public void setCustomerRecordId( long customerRecordId )
    {
        this.customerRecordId = customerRecordId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public LMSPojo getLmsPojo()
    {
        return lmsPojo;
    }

    public void setLmsPojo( LMSPojo lmsPojo )
    {
        this.lmsPojo = lmsPojo;
    }

    public String getCrfID()
    {
        return crfID;
    }

    public void setCrfID( String crfID )
    {
        this.crfID = crfID;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String statusCode )
    {
        this.statusCode = statusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String statusDesc )
    {
        this.statusDesc = statusDesc;
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
    }

    public CrmAdditionalDetailsPojo getAdditionalDetailsPojo()
    {
        return additionalDetailsPojo;
    }

    public void setAdditionalDetailsPojo( CrmAdditionalDetailsPojo inAdditionalDetailsPojo )
    {
        additionalDetailsPojo = inAdditionalDetailsPojo;
    }

    public CrmAddressDetailsPojo getAddressDetailsPojo()
    {
        return addressDetailsPojo;
    }

    public void setAddressDetailsPojo( CrmAddressDetailsPojo inAddressDetailsPojo )
    {
        addressDetailsPojo = inAddressDetailsPojo;
    }

    public CrmPlanDetailsPojo getPlanDetailsPojo()
    {
        return planDetailsPojo;
    }

    public void setPlanDetailsPojo( CrmPlanDetailsPojo inPlanDetailsPojo )
    {
        planDetailsPojo = inPlanDetailsPojo;
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

    //    public CrmNetworkDetailsPojo getNetworkDetailsPojo()
    //    {
    //        return networkDetailsPojo;
    //    }
    //
    //    public void setNetworkDetailsPojo( CrmNetworkDetailsPojo inNetworkDetailsPojo )
    //    {
    //        networkDetailsPojo = inNetworkDetailsPojo;
    //    }
    public CrmOrderDetailsPojo getOrderDetailsPojo()
    {
        return orderDetailsPojo;
    }

    public void setOrderDetailsPojo( CrmOrderDetailsPojo inOrderDetailsPojo )
    {
        orderDetailsPojo = inOrderDetailsPojo;
    }

    public CrmPaymentDetailsPojo getPaymentDetailsPojo()
    {
        return paymentDetailsPojo;
    }

    public void setPaymentDetailsPojo( CrmPaymentDetailsPojo inPaymentDetailsPojo )
    {
        paymentDetailsPojo = inPaymentDetailsPojo;
    }

    public List<CrmCustomerDetailsPojo> getCustomerDetailsPojosList()
    {
        return customerDetailsPojosList;
    }

    public void setCustomerDetailsPojosList( List<CrmCustomerDetailsPojo> inCustomerDetailsPojosList )
    {
        customerDetailsPojosList = inCustomerDetailsPojosList;
    }

    public List<CrmAdditionalDetailsPojo> getAdditionalDetailsPojosList()
    {
        return additionalDetailsPojosList;
    }

    public void setAdditionalDetailsPojosList( List<CrmAdditionalDetailsPojo> inAdditionalDetailsPojosList )
    {
        additionalDetailsPojosList = inAdditionalDetailsPojosList;
    }

    public List<CrmAddressDetailsPojo> getAddressDetailsPojosList()
    {
        return addressDetailsPojosList;
    }

    public void setAddressDetailsPojosList( List<CrmAddressDetailsPojo> inAddressDetailsPojosList )
    {
        addressDetailsPojosList = inAddressDetailsPojosList;
    }

    public List<CrmPlanDetailsPojo> getPlanDetailsPojosList()
    {
        return planDetailsPojosList;
    }

    public void setPlanDetailsPojosList( List<CrmPlanDetailsPojo> inPlanDetailsPojosList )
    {
        planDetailsPojosList = inPlanDetailsPojosList;
    }

    public List<CrmDocumentDetailsPojo> getDocumentDetailsPojosList()
    {
        return documentDetailsPojosList;
    }

    public void setDocumentDetailsPojosList( List<CrmDocumentDetailsPojo> inDocumentDetailsPojosList )
    {
        documentDetailsPojosList = inDocumentDetailsPojosList;
    }

    public List<CrmNationalityDetailsPojo> getNationalityDetailsPojosList()
    {
        return nationalityDetailsPojosList;
    }

    public void setNationalityDetailsPojosList( List<CrmNationalityDetailsPojo> inNationalityDetailsPojosList )
    {
        nationalityDetailsPojosList = inNationalityDetailsPojosList;
    }

    //    public List<CrmNetworkDetailsPojo> getNetworkDetailsPojosList()
    //    {
    //        return networkDetailsPojosList;
    //    }
    //
    //    public void setNetworkDetailsPojosList( List<CrmNetworkDetailsPojo> inNetworkDetailsPojosList )
    //    {
    //        networkDetailsPojosList = inNetworkDetailsPojosList;
    //    }
    public List<CrmOrderDetailsPojo> getOrderDetailsPojosList()
    {
        return orderDetailsPojosList;
    }

    public void setOrderDetailsPojosList( List<CrmOrderDetailsPojo> inOrderDetailsPojosList )
    {
        orderDetailsPojosList = inOrderDetailsPojosList;
    }

    public List<CrmPaymentDetailsPojo> getPaymentDetailsPojosList()
    {
        return paymentDetailsPojosList;
    }

    public void setPaymentDetailsPojosList( List<CrmPaymentDetailsPojo> inPaymentDetailsPojosList )
    {
        paymentDetailsPojosList = inPaymentDetailsPojosList;
    }

    public AppointmentPojo getAppointmentPojo()
    {
        return appointmentPojo;
    }

    public void setAppointmentPojo( AppointmentPojo appointmentPojo )
    {
        this.appointmentPojo = appointmentPojo;
    }

    public List<AppointmentPojo> getAppointmentPojos()
    {
        return appointmentPojos;
    }

    public void setAppointmentPojos( List<AppointmentPojo> appointmentPojos )
    {
        this.appointmentPojos = appointmentPojos;
    }

    public List<CrmNetworkConfigurationsPojo> getNetworkConfigurationsList()
    {
        return networkConfigurationsList;
    }

    public void setNetworkConfigurationsList( List<CrmNetworkConfigurationsPojo> inNetworkConfigurationsList )
    {
        networkConfigurationsList = inNetworkConfigurationsList;
    }

    public CrmNetworkConfigurationsPojo getNetworkConfigurationsPojo()
    {
        return networkConfigurationsPojo;
    }

    public void setNetworkConfigurationsPojo( CrmNetworkConfigurationsPojo inNetworkConfigurationsPojo )
    {
        networkConfigurationsPojo = inNetworkConfigurationsPojo;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public String getChangeRequest()
    {
        return changeRequest;
    }

    public void setChangeRequest( String changeRequest )
    {
        this.changeRequest = changeRequest;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String clientIPAddress )
    {
        this.clientIPAddress = clientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String serverIPAddress )
    {
        this.serverIPAddress = serverIPAddress;
    }

    public String getActivityAction()
    {
        return activityAction;
    }

    public void setActivityAction( String activityAction )
    {
        this.activityAction = activityAction;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getGeneratedTicketId()
    {
        return generatedTicketId;
    }

    public void setGeneratedTicketId( String generatedTicketId )
    {
        this.generatedTicketId = generatedTicketId;
    }

    @XmlTransient
    public PartnerPojo getSalesPartner()
    {
        return salesPartner;
    }

    public void setSalesPartner( PartnerPojo inSalesPartner )
    {
        salesPartner = inSalesPartner;
    }

    public String getBillingErrorCode()
    {
        return billingErrorCode;
    }

    public void setBillingErrorCode( String inBillingErrorCode )
    {
        billingErrorCode = inBillingErrorCode;
    }

    public PartnerPojo getNetworkPartner()
    {
        return networkPartner;
    }

    public void setNetworkPartner( PartnerPojo inNetworkPartner )
    {
        networkPartner = inNetworkPartner;
    }

    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfig()
    {
        return crmPartnerNetworkConfig;
    }

    public void setCrmPartnerNetworkConfig( CrmPartnerNetworkConfigPojo inCrmPartnerNetworkConfig )
    {
        crmPartnerNetworkConfig = inCrmPartnerNetworkConfig;
    }

    public List<CommonCustDetailsPojo> getCommonCustDetailsPojos()
    {
        return commonCustDetailsPojos;
    }

    public void setCommonCustDetailsPojos( List<CommonCustDetailsPojo> inCommonCustDetailsPojos )
    {
        commonCustDetailsPojos = inCommonCustDetailsPojos;
    }

    public boolean isMacFaulty()
    {
        return macFaulty;
    }

    public void setMacFaulty( boolean inMacFaulty )
    {
        macFaulty = inMacFaulty;
    }

    public CrmCustAadharNumberPojo getAadharNumberPojo()
    {
        return aadharNumberPojo;
    }

    public void setAadharNumberPojo( CrmCustAadharNumberPojo inAadharNumberPojo )
    {
        aadharNumberPojo = inAadharNumberPojo;
    }
}
