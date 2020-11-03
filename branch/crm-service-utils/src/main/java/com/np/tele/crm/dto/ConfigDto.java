package com.np.tele.crm.dto;

import java.util.List;

import com.np.tele.crm.ext.pojos.AlertMasterPojo;
import com.np.tele.crm.ext.pojos.AtomPgwPojo;
import com.np.tele.crm.ext.pojos.CommonWorkflowPojo;
import com.np.tele.crm.ext.pojos.EmailServerPojo;
import com.np.tele.crm.ext.pojos.HDFCPgwPojo;
import com.np.tele.crm.ext.pojos.PlanMigrationPolicyPojo;
import com.np.tele.crm.ext.pojos.SmsGatewayPojo;
import com.np.tele.crm.ext.pojos.TechProcessPgwPojo;
import com.np.tele.crm.ext.pojos.UserMasterPojo;
import com.np.tele.crm.ext.pojos.ValidationMasterPojo;
import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmBillingTransactionsPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmPaymentCentresPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmSrTicketsPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.EventsPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class ConfigDto
    implements java.io.Serializable
{
    private String                        statusCode              = null;
    private String                        statusDesc              = null;
    private String                        clientIPAddress         = null;
    private String                        serverIPAddress         = null;
    private List<EmailServerPojo>         emailServers            = null;
    private List<SmsGatewayPojo>          smsGateways             = null;
    private UserMasterPojo                userMasterPojo          = null;
    private AlertMasterPojo               alertMasterPojo         = null;
    private List<LMSPojo>                 inboxLeadPojos          = null;
    private List<LMSPojo>                 groupInboxLeadPojos     = null;
    private List<CrmCustomerDetailsPojo>  inboxCRFPojos           = null;
    private List<CrmCustomerDetailsPojo>  groupInboxCRFPojos      = null;
    private String                        leadStatus              = null;
    private String                        inboxStatus             = null;
    private String                        userId                  = null;
    private String                        inboxType               = null;
    private String                        requestType             = null;
    private String                        fromUserId              = null;
    private String                        toUserId                = null;
    private String                        fromStage               = null;
    private String                        tostage                 = null;
    private String                        mappingId               = null;
    private List<RemarksPojo>             remarksPojos            = null;
    private List<AppointmentPojo>         appointmentPojos        = null;
    private AppointmentPojo               appointmentPojo         = null;
    private List<String>                  stages                  = null;
    private List<String>                  userIds                 = null;
    private String                        userType                = null;
    private List<CrmAuditLogPojo>         auditLogPojos           = null;
    private CrmAuditLogPojo               auditLogPojo            = null;
    private List<CrmRcaReasonPojo>        crmRcaReasons           = null;
    private List<CrmRcaReasonPojo>        crmCloseReasons         = null;
    private ValidationMasterPojo          validationMaster        = null;
    private long                          partnerId;
    private String                        userIdChange;
    private List<CrmCustomerDetailsPojo>  customerDetailPojos     = null;
    private CrmCustomerDetailsPojo        customerDetail          = null;
    private List<String>                  crfIds                  = null;
    private List<EventsPojo>              eventsPojoList          = null;
    private List<CrmCustomerActivityPojo> customerActivityPojos   = null;
    private CrmCustomerActivityPojo       customerActivityPojo    = null;
    private CrmBillingTransactionsPojo    billingTransactionsPojo = null;
    private List<CrmSrTicketsPojo>        inboxSrPojosPersonal    = null;
    private List<CrmSrTicketsPojo>        inboxSrPojosGroup       = null;
    private String                        servicePartner          = null;
    private String                        customerId              = null;
    private String                        billingErrorCode        = null;
    private List<String>                  macAddressList          = null;
    private boolean                       unRead                  = false;
    private long                          inboxId                 = 0l;
    private HDFCPgwPojo                   hdfcPgwPojo             = null;
    private AtomPgwPojo                   atomPgwPojo             = null;
    private TechProcessPgwPojo            techProcessPgwPojo      = null;
    private PlanMigrationPolicyPojo       planMigrationPolicyPojo = null;
    private List<CommonWorkflowPojo>      commonWorkflowPojos;
    private List<CommonWorkflowPojo>      commonWorkflowPojoGroup;
    private List<String>                  userAssociatedServices;
    private List<String>                  userAssociatedPartners;
    private CrmPaymentCentresPojo         paymentCentre           = null;
    private List<CrmPaymentCentresPojo>   paymentCentres          = null;
    private List<CrmTicketHistoryPojo>    ticketHistoryList       = null;
    private List<String>                  podUploadedList;
    private List<CrmCustomerDetailsPojo>  inboxFreezePojos        = null;
    private List<CrmCustomerDetailsPojo>  groupInboxFreezePojos   = null;
    private String                        inventType;

    public String getInventType()
    {
        return inventType;
    }

    public void setInventType( String inventType )
    {
        this.inventType = inventType;
    }

    public List<CrmCustomerDetailsPojo> getInboxFreezePojos()
    {
        return inboxFreezePojos;
    }

    public void setInboxFreezePojos( List<CrmCustomerDetailsPojo> inInboxFreezePojos )
    {
        inboxFreezePojos = inInboxFreezePojos;
    }

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

    public List<CrmSrTicketsPojo> getInboxSrPojosPersonal()
    {
        return inboxSrPojosPersonal;
    }

    public void setInboxSrPojosPersonal( List<CrmSrTicketsPojo> inInboxSrPojosPersonal )
    {
        inboxSrPojosPersonal = inInboxSrPojosPersonal;
    }

    public List<CrmSrTicketsPojo> getInboxSrPojosGroup()
    {
        return inboxSrPojosGroup;
    }

    public void setInboxSrPojosGroup( List<CrmSrTicketsPojo> inInboxSrPojosGroup )
    {
        inboxSrPojosGroup = inInboxSrPojosGroup;
    }

    public CrmBillingTransactionsPojo getBillingTransactionsPojo()
    {
        return billingTransactionsPojo;
    }

    public void setBillingTransactionsPojo( CrmBillingTransactionsPojo inBillingTransactionsPojo )
    {
        billingTransactionsPojo = inBillingTransactionsPojo;
    }

    public List<CrmCustomerActivityPojo> getCustomerActivityPojos()
    {
        return customerActivityPojos;
    }

    public void setCustomerActivityPojos( List<CrmCustomerActivityPojo> inCustomerActivityPojos )
    {
        customerActivityPojos = inCustomerActivityPojos;
    }

    public CrmCustomerActivityPojo getCustomerActivityPojo()
    {
        return customerActivityPojo;
    }

    public void setCustomerActivityPojo( CrmCustomerActivityPojo inCustomerActivityPojo )
    {
        customerActivityPojo = inCustomerActivityPojo;
    }

    public List<String> getCrfIds()
    {
        return crfIds;
    }

    public void setCrfIds( List<String> crfIds )
    {
        this.crfIds = crfIds;
    }

    public String getUserIdChange()
    {
        return userIdChange;
    }

    public void setUserIdChange( String userIdChange )
    {
        this.userIdChange = userIdChange;
    }

    public List<CrmRcaReasonPojo> getCrmCloseReasons()
    {
        return crmCloseReasons;
    }

    public void setCrmCloseReasons( List<CrmRcaReasonPojo> inCrmCloseReasons )
    {
        crmCloseReasons = inCrmCloseReasons;
    }

    public List<CrmAuditLogPojo> getAuditLogPojos()
    {
        return auditLogPojos;
    }

    public void setAuditLogPojos( List<CrmAuditLogPojo> auditLogPojos )
    {
        this.auditLogPojos = auditLogPojos;
    }

    public CrmAuditLogPojo getAuditLogPojo()
    {
        return auditLogPojo;
    }

    public void setAuditLogPojo( CrmAuditLogPojo auditLogPojo )
    {
        this.auditLogPojo = auditLogPojo;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType( String userType )
    {
        this.userType = userType;
    }

    public List<String> getUserIds()
    {
        return userIds;
    }

    public void setUserIds( List<String> userIds )
    {
        this.userIds = userIds;
    }

    public List<String> getStages()
    {
        return stages;
    }

    public void setStages( List<String> stages )
    {
        this.stages = stages;
    }

    public List<RemarksPojo> getRemarksPojos()
    {
        return remarksPojos;
    }

    public void setRemarksPojos( List<RemarksPojo> remarksPojos )
    {
        this.remarksPojos = remarksPojos;
    }

    public List<AppointmentPojo> getAppointmentPojos()
    {
        return appointmentPojos;
    }

    public void setAppointmentPojos( List<AppointmentPojo> appointmentPojos )
    {
        this.appointmentPojos = appointmentPojos;
    }

    public AppointmentPojo getAppointmentPojo()
    {
        return appointmentPojo;
    }

    public void setAppointmentPojo( AppointmentPojo appointmentPojo )
    {
        this.appointmentPojo = appointmentPojo;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType( String requestType )
    {
        this.requestType = requestType;
    }

    public String getFromUserId()
    {
        return fromUserId;
    }

    public void setFromUserId( String fromUserId )
    {
        this.fromUserId = fromUserId;
    }

    public String getToUserId()
    {
        return toUserId;
    }

    public void setToUserId( String toUserId )
    {
        this.toUserId = toUserId;
    }

    public String getFromStage()
    {
        return fromStage;
    }

    public void setFromStage( String fromStage )
    {
        this.fromStage = fromStage;
    }

    public String getTostage()
    {
        return tostage;
    }

    public void setTostage( String tostage )
    {
        this.tostage = tostage;
    }

    public String getMappingId()
    {
        return mappingId;
    }

    public List<CrmCustomerDetailsPojo> getInboxCRFPojos()
    {
        return inboxCRFPojos;
    }

    public void setInboxCRFPojos( List<CrmCustomerDetailsPojo> inboxCRFPojos )
    {
        this.inboxCRFPojos = inboxCRFPojos;
    }

    public List<CrmCustomerDetailsPojo> getGroupInboxCRFPojos()
    {
        return groupInboxCRFPojos;
    }

    public void setGroupInboxCRFPojos( List<CrmCustomerDetailsPojo> groupInboxCRFPojos )
    {
        this.groupInboxCRFPojos = groupInboxCRFPojos;
    }

    public void setMappingId( String mappingId )
    {
        this.mappingId = mappingId;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String userId )
    {
        this.userId = userId;
    }

    public String getInboxType()
    {
        return inboxType;
    }

    public void setInboxType( String inboxType )
    {
        this.inboxType = inboxType;
    }

    public String getLeadStatus()
    {
        return leadStatus;
    }

    public void setLeadStatus( String leadStatus )
    {
        this.leadStatus = leadStatus;
    }

    public String getInboxStatus()
    {
        return inboxStatus;
    }

    public void setInboxStatus( String inboxStatus )
    {
        this.inboxStatus = inboxStatus;
    }

    public List<LMSPojo> getInboxLeadPojos()
    {
        return inboxLeadPojos;
    }

    public void setInboxLeadPojos( List<LMSPojo> inboxLeadPojos )
    {
        this.inboxLeadPojos = inboxLeadPojos;
    }

    public List<LMSPojo> getGroupInboxLeadPojos()
    {
        return groupInboxLeadPojos;
    }

    public void setGroupInboxLeadPojos( List<LMSPojo> groupInboxLeadPojos )
    {
        this.groupInboxLeadPojos = groupInboxLeadPojos;
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

    public List<EmailServerPojo> getEmailServers()
    {
        return emailServers;
    }

    public void setEmailServers( List<EmailServerPojo> inEmailServers )
    {
        emailServers = inEmailServers;
    }

    public List<SmsGatewayPojo> getSmsGateways()
    {
        return smsGateways;
    }

    public void setSmsGateways( List<SmsGatewayPojo> inSmsGateways )
    {
        smsGateways = inSmsGateways;
    }

    public UserMasterPojo getUserMasterPojo()
    {
        return userMasterPojo;
    }

    public void setUserMasterPojo( UserMasterPojo inUserMasterPojo )
    {
        userMasterPojo = inUserMasterPojo;
    }

    public AlertMasterPojo getAlertMasterPojo()
    {
        return alertMasterPojo;
    }

    public void setAlertMasterPojo( AlertMasterPojo inAlertMasterPojo )
    {
        alertMasterPojo = inAlertMasterPojo;
    }

    public String getClientIPAddress()
    {
        return clientIPAddress;
    }

    public void setClientIPAddress( String inClientIPAddress )
    {
        clientIPAddress = inClientIPAddress;
    }

    public String getServerIPAddress()
    {
        return serverIPAddress;
    }

    public void setServerIPAddress( String inServerIPAddress )
    {
        serverIPAddress = inServerIPAddress;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasons()
    {
        return crmRcaReasons;
    }

    public void setCrmRcaReasons( List<CrmRcaReasonPojo> inCrmRcaReasons )
    {
        crmRcaReasons = inCrmRcaReasons;
    }

    public ValidationMasterPojo getValidationMaster()
    {
        return validationMaster;
    }

    public void setValidationMaster( ValidationMasterPojo inValidationMaster )
    {
        validationMaster = inValidationMaster;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long inPartnerId )
    {
        partnerId = inPartnerId;
    }

    public List<CrmCustomerDetailsPojo> getCustomerDetailPojos()
    {
        return customerDetailPojos;
    }

    public void setCustomerDetailPojos( List<CrmCustomerDetailsPojo> inCustomerDetailPojos )
    {
        customerDetailPojos = inCustomerDetailPojos;
    }

    public CrmCustomerDetailsPojo getCustomerDetail()
    {
        return customerDetail;
    }

    public void setCustomerDetail( CrmCustomerDetailsPojo inCustomerDetail )
    {
        customerDetail = inCustomerDetail;
    }

    public List<EventsPojo> getEventsPojoList()
    {
        return eventsPojoList;
    }

    public void setEventsPojoList( List<EventsPojo> inEventsPojoList )
    {
        eventsPojoList = inEventsPojoList;
    }

    public String getServicePartner()
    {
        return servicePartner;
    }

    public void setServicePartner( String inServicePartner )
    {
        servicePartner = inServicePartner;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public String getBillingErrorCode()
    {
        return billingErrorCode;
    }

    public void setBillingErrorCode( String inBillingErrorCode )
    {
        billingErrorCode = inBillingErrorCode;
    }

    public List<String> getMacAddressList()
    {
        return macAddressList;
    }

    public void setMacAddressList( List<String> inMacAddressList )
    {
        macAddressList = inMacAddressList;
    }

    public boolean isUnRead()
    {
        return unRead;
    }

    public void setUnRead( boolean inUnRead )
    {
        unRead = inUnRead;
    }

    public long getInboxId()
    {
        return inboxId;
    }

    public void setInboxId( long inInboxId )
    {
        inboxId = inInboxId;
    }

    public HDFCPgwPojo getHdfcPgwPojo()
    {
        return hdfcPgwPojo;
    }

    public void setHdfcPgwPojo( HDFCPgwPojo inHdfcPgwPojo )
    {
        hdfcPgwPojo = inHdfcPgwPojo;
    }

    public AtomPgwPojo getAtomPgwPojo()
    {
        return atomPgwPojo;
    }

    public void setAtomPgwPojo( AtomPgwPojo inAtomPgwPojo )
    {
        atomPgwPojo = inAtomPgwPojo;
    }

    public TechProcessPgwPojo getTechProcessPgwPojo()
    {
        return techProcessPgwPojo;
    }

    public void setTechProcessPgwPojo( TechProcessPgwPojo inTechProcessPgwPojo )
    {
        techProcessPgwPojo = inTechProcessPgwPojo;
    }

    public PlanMigrationPolicyPojo getPlanMigrationPolicyPojo()
    {
        return planMigrationPolicyPojo;
    }

    public void setPlanMigrationPolicyPojo( PlanMigrationPolicyPojo planMigrationPolicyPojo )
    {
        this.planMigrationPolicyPojo = planMigrationPolicyPojo;
    }

    public List<CommonWorkflowPojo> getCommonWorkflowPojos()
    {
        return commonWorkflowPojos;
    }

    public void setCommonWorkflowPojos( List<CommonWorkflowPojo> commonWorkflowPojos )
    {
        this.commonWorkflowPojos = commonWorkflowPojos;
    }

    public List<CommonWorkflowPojo> getCommonWorkflowPojoGroup()
    {
        return commonWorkflowPojoGroup;
    }

    public void setCommonWorkflowPojoGroup( List<CommonWorkflowPojo> commonWorkflowPojoGroup )
    {
        this.commonWorkflowPojoGroup = commonWorkflowPojoGroup;
    }

    public CrmPaymentCentresPojo getPaymentCentre()
    {
        return paymentCentre;
    }

    public void setPaymentCentre( CrmPaymentCentresPojo inPaymentCentre )
    {
        paymentCentre = inPaymentCentre;
    }

    public List<CrmPaymentCentresPojo> getPaymentCentres()
    {
        return paymentCentres;
    }

    public void setPaymentCentres( List<CrmPaymentCentresPojo> inPaymentCentres )
    {
        paymentCentres = inPaymentCentres;
    }

    public List<CrmTicketHistoryPojo> getTicketHistoryList()
    {
        return ticketHistoryList;
    }

    public void setTicketHistoryList( List<CrmTicketHistoryPojo> inTicketHistoryList )
    {
        ticketHistoryList = inTicketHistoryList;
    }

    public List<String> getPodUploadedList()
    {
        return podUploadedList;
    }

    public void setPodUploadedList( List<String> inPodUploadedList )
    {
        podUploadedList = inPodUploadedList;
    }

    public List<CrmCustomerDetailsPojo> getGroupInboxFreezePojos()
    {
        return groupInboxFreezePojos;
    }

    public void setGroupInboxFreezePojos( List<CrmCustomerDetailsPojo> inGroupInboxFreezePojos )
    {
        groupInboxFreezePojos = inGroupInboxFreezePojos;
    }
}
