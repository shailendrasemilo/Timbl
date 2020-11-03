package com.np.tele.crm.dto;

import java.util.List;
import java.util.Map;

import com.np.tele.crm.ext.pojos.AlertMasterPojo;
import com.np.tele.crm.ext.pojos.EmailServerPojo;
import com.np.tele.crm.ext.pojos.SmsGatewayPojo;
import com.np.tele.crm.ext.pojos.UserMasterPojo;
import com.np.tele.crm.pojos.AccessGroupPojo;
import com.np.tele.crm.pojos.CrmHolidayDetails;
import com.np.tele.crm.pojos.CrmNpMobilePojo;
import com.np.tele.crm.pojos.CrmParameterPojo;
import com.np.tele.crm.pojos.CrmPartnerDetailsPojo;
import com.np.tele.crm.pojos.CrmPartnerMappingPojo;
import com.np.tele.crm.pojos.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.pojos.CrmPlanMasterPojo;
import com.np.tele.crm.pojos.CrmRcaReasonPojo;
import com.np.tele.crm.pojos.CrmRolesPojo;
import com.np.tele.crm.pojos.CrmTicketHistoryPojo;
import com.np.tele.crm.pojos.CrmUserPojo;
import com.np.tele.crm.pojos.GroupsPojo;
import com.np.tele.crm.pojos.PartnerPojo;
import com.np.tele.crm.pojos.ProjectsPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class MasterDataDto
    implements java.io.Serializable
{
    private String                            userID                       = null;
    private String                            password                     = null;
    private String                            statusCode                   = null;
    private String                            statusDesc                   = null;
    private String                            clientIPAddress              = null;
    private String                            serverIPAddress              = null;
    private PartnerPojo                       partnerPojo                  = null;
    private List<PartnerPojo>                 partnerPojoList              = null;
    private Map<Long, PartnerPojo>            partnerPojoMap               = null;
    private CrmRolesPojo                      crmRolesPojo                 = null;
    private List<CrmRolesPojo>                crmRolesPojoList             = null;
    private Map<Long, CrmRolesPojo>           crmRolesPojoMap              = null;
    private AccessGroupPojo                   accessGroupPojo              = null;
    private List<AccessGroupPojo>             accessGroupPojoList          = null;
    private Map<Long, AccessGroupPojo>        accessGroupPojoMap           = null;
    private GroupsPojo                        groupsPojo                   = null;
    private List<GroupsPojo>                  groupsPojoList               = null;
    private List<CrmParameterPojo>            crmParameterPojos            = null;
    private ProjectsPojo                      projectsPojo                 = null;
    private List<ProjectsPojo>                projectsPojos                = null;
    private String                            fromDate                     = null;
    private String                            toDate                       = null;
    private EmailServerPojo                   emailServerPojo              = null;
    private SmsGatewayPojo                    smsGatewayPojo               = null;
    private UserMasterPojo                    userMasterPojo               = null;
    private AlertMasterPojo                   alertMasterPojo              = null;
    private List<CrmPartnerMappingPojo>       crmPartnerMappingList        = null;
    private CrmRcaReasonPojo                  crmRcaReason                 = null;
    private List<CrmRcaReasonPojo>            crmRcaReasonsList            = null;
    private CrmPartnerMappingPojo             crmPartnerMappingPojo        = null;
    private String                            categoryValueRangeStart      = null;
    private String                            categoryValueRangeEnd        = null;
    private String                            categoryValuePrefix          = null;
    private List<CrmPartnerDetailsPojo>       crmPartnerDetailsPojos       = null;
    private List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos = null;
    private long                              partnerId                    = 0;
    private String                            product                      = null;
    private CrmPartnerNetworkConfigPojo       crmPartnerNetworkConfigPojo  = null;
    private List<CrmPlanMasterPojo>           crmPlanMasterPojos           = null;
    private CrmPlanMasterPojo                 planMaster                   = null;
    private RemarksPojo                       remarks                      = null;
    private List<String>                      userAssociatedServices;
    private List<String>                      userAssociatedPartners;
    private CrmTicketHistoryPojo              ticketHistory                = null;
    private long                              inboxId                      = 0;
    private CrmHolidayDetails                 crmHolidayPojo;
    private List<CrmHolidayDetails>           crmHolidayPojos;
    private List<CrmUserPojo>                 crmUserPojos                 = null;
    private CrmNpMobilePojo                   crmNpMobilePojo              = null;
    private List<CrmNpMobilePojo>             crmNpMobilePojos             = null;
    private List<String>                      masterNames                  = null;

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

    public List<CrmPlanMasterPojo> getCrmPlanMasterPojos()
    {
        return crmPlanMasterPojos;
    }

    public void setCrmPlanMasterPojos( List<CrmPlanMasterPojo> crmPlanMasterPojos )
    {
        this.crmPlanMasterPojos = crmPlanMasterPojos;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long inPartnerId )
    {
        partnerId = inPartnerId;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct( String inProduct )
    {
        product = inProduct;
    }

    public List<CrmParameterPojo> getCrmParameterPojos()
    {
        return crmParameterPojos;
    }

    public void setCrmParameterPojos( List<CrmParameterPojo> crmParameterPojos )
    {
        this.crmParameterPojos = crmParameterPojos;
    }

    public List<GroupsPojo> getGroupsPojoList()
    {
        return groupsPojoList;
    }

    public void setGroupsPojoList( List<GroupsPojo> groupsPojoList )
    {
        this.groupsPojoList = groupsPojoList;
    }

    public GroupsPojo getGroupsPojo()
    {
        return groupsPojo;
    }

    public void setGroupsPojo( GroupsPojo groupsPojo )
    {
        this.groupsPojo = groupsPojo;
    }

    public List<PartnerPojo> getPartnerPojoList()
    {
        return partnerPojoList;
    }

    public void setPartnerPojoList( List<PartnerPojo> partnerPojoList )
    {
        this.partnerPojoList = partnerPojoList;
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

    public PartnerPojo getPartnerPojo()
    {
        return partnerPojo;
    }

    public void setPartnerPojo( PartnerPojo partnerPojo )
    {
        this.partnerPojo = partnerPojo;
    }

    public Map<Long, PartnerPojo> getPartnerPojoMap()
    {
        return partnerPojoMap;
    }

    public void setPartnerPojoMap( Map<Long, PartnerPojo> partnerPojoMap )
    {
        this.partnerPojoMap = partnerPojoMap;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID( String userID )
    {
        this.userID = userID;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public CrmRolesPojo getCrmRolesPojo()
    {
        return crmRolesPojo;
    }

    public void setCrmRolesPojo( CrmRolesPojo crmRolesPojo )
    {
        this.crmRolesPojo = crmRolesPojo;
    }

    public List<CrmRolesPojo> getCrmRolesPojoList()
    {
        return crmRolesPojoList;
    }

    public void setCrmRolesPojoList( List<CrmRolesPojo> crmRolesPojoList )
    {
        this.crmRolesPojoList = crmRolesPojoList;
    }

    public Map<Long, CrmRolesPojo> getCrmRolesPojoMap()
    {
        return crmRolesPojoMap;
    }

    public void setCrmRolesPojoMap( Map<Long, CrmRolesPojo> crmRolesPojoMap )
    {
        this.crmRolesPojoMap = crmRolesPojoMap;
    }

    public AccessGroupPojo getAccessGroupPojo()
    {
        return accessGroupPojo;
    }

    public void setAccessGroupPojo( AccessGroupPojo accessGroupPojo )
    {
        this.accessGroupPojo = accessGroupPojo;
    }

    public List<AccessGroupPojo> getAccessGroupPojoList()
    {
        return accessGroupPojoList;
    }

    public void setAccessGroupPojoList( List<AccessGroupPojo> accessGroupPojoList )
    {
        this.accessGroupPojoList = accessGroupPojoList;
    }

    public Map<Long, AccessGroupPojo> getAccessGroupPojoMap()
    {
        return accessGroupPojoMap;
    }

    public void setAccessGroupPojoMap( Map<Long, AccessGroupPojo> accessGroupPojoMap )
    {
        this.accessGroupPojoMap = accessGroupPojoMap;
    }

    public ProjectsPojo getProjectsPojo()
    {
        return projectsPojo;
    }

    public void setProjectsPojo( ProjectsPojo inProjectsPojo )
    {
        projectsPojo = inProjectsPojo;
    }

    public List<ProjectsPojo> getProjectsPojos()
    {
        return projectsPojos;
    }

    public void setProjectsPojos( List<ProjectsPojo> inProjectsPojos )
    {
        projectsPojos = inProjectsPojos;
    }

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate( String inFromDate )
    {
        fromDate = inFromDate;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate( String inToDate )
    {
        toDate = inToDate;
    }

    public EmailServerPojo getEmailServerPojo()
    {
        return emailServerPojo;
    }

    public void setEmailServerPojo( EmailServerPojo inEmailServerPojo )
    {
        emailServerPojo = inEmailServerPojo;
    }

    public SmsGatewayPojo getSmsGatewayPojo()
    {
        return smsGatewayPojo;
    }

    public void setSmsGatewayPojo( SmsGatewayPojo inSmsGatewayPojo )
    {
        smsGatewayPojo = inSmsGatewayPojo;
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

    public List<CrmPartnerMappingPojo> getCrmPartnerMappingList()
    {
        return crmPartnerMappingList;
    }

    public void setCrmPartnerMappingList( List<CrmPartnerMappingPojo> inCrmPartnerMappingList )
    {
        crmPartnerMappingList = inCrmPartnerMappingList;
    }

    public CrmRcaReasonPojo getCrmRcaReason()
    {
        return crmRcaReason;
    }

    public void setCrmRcaReason( CrmRcaReasonPojo inCrmRcaReason )
    {
        crmRcaReason = inCrmRcaReason;
    }

    public List<CrmRcaReasonPojo> getCrmRcaReasonsList()
    {
        return crmRcaReasonsList;
    }

    public void setCrmRcaReasonsList( List<CrmRcaReasonPojo> inCrmRcaReasonsList )
    {
        crmRcaReasonsList = inCrmRcaReasonsList;
    }

    public CrmPartnerMappingPojo getCrmPartnerMappingPojo()
    {
        return crmPartnerMappingPojo;
    }

    public void setCrmPartnerMappingPojo( CrmPartnerMappingPojo inCrmPartnerMappingPojo )
    {
        crmPartnerMappingPojo = inCrmPartnerMappingPojo;
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

    public String getCategoryValueRangeStart()
    {
        return categoryValueRangeStart;
    }

    public void setCategoryValueRangeStart( String inCategoryValueRangeStart )
    {
        categoryValueRangeStart = inCategoryValueRangeStart;
    }

    public String getCategoryValueRangeEnd()
    {
        return categoryValueRangeEnd;
    }

    public void setCategoryValueRangeEnd( String inCategoryValueRangeEnd )
    {
        categoryValueRangeEnd = inCategoryValueRangeEnd;
    }

    public String getCategoryValuePrefix()
    {
        return categoryValuePrefix;
    }

    public void setCategoryValuePrefix( String inCategoryValuePrefix )
    {
        categoryValuePrefix = inCategoryValuePrefix;
    }

    public List<CrmPartnerDetailsPojo> getCrmPartnerDetailsPojos()
    {
        return crmPartnerDetailsPojos;
    }

    public void setCrmPartnerDetailsPojos( List<CrmPartnerDetailsPojo> inCrmPartnerDetailsPojos )
    {
        crmPartnerDetailsPojos = inCrmPartnerDetailsPojos;
    }

    public List<CrmPartnerNetworkConfigPojo> getCrmPartnerNetworkConfigPojos()
    {
        return crmPartnerNetworkConfigPojos;
    }

    public void setCrmPartnerNetworkConfigPojos( List<CrmPartnerNetworkConfigPojo> inCrmPartnerNetworkConfigPojos )
    {
        crmPartnerNetworkConfigPojos = inCrmPartnerNetworkConfigPojos;
    }

    public CrmPartnerNetworkConfigPojo getCrmPartnerNetworkConfigPojo()
    {
        return crmPartnerNetworkConfigPojo;
    }

    public void setCrmPartnerNetworkConfigPojo( CrmPartnerNetworkConfigPojo inCrmPartnerNetworkConfigPojo )
    {
        crmPartnerNetworkConfigPojo = inCrmPartnerNetworkConfigPojo;
    }

    public RemarksPojo getRemarks()
    {
        return remarks;
    }

    public void setRemarks( RemarksPojo inRemarks )
    {
        remarks = inRemarks;
    }

    public CrmPlanMasterPojo getPlanMaster()
    {
        return planMaster;
    }

    public void setPlanMaster( CrmPlanMasterPojo inPlanMaster )
    {
        planMaster = inPlanMaster;
    }

    public CrmTicketHistoryPojo getTicketHistory()
    {
        return ticketHistory;
    }

    public void setTicketHistory( CrmTicketHistoryPojo inTicketHistory )
    {
        ticketHistory = inTicketHistory;
    }

    public long getInboxId()
    {
        return inboxId;
    }

    public void setInboxId( long inInboxId )
    {
        inboxId = inInboxId;
    }

    public CrmHolidayDetails getCrmHolidayPojo()
    {
        return crmHolidayPojo;
    }

    public void setCrmHolidayPojo( CrmHolidayDetails inCrmHolidayPojo )
    {
        crmHolidayPojo = inCrmHolidayPojo;
    }

    public List<CrmHolidayDetails> getCrmHolidayPojos()
    {
        return crmHolidayPojos;
    }

    public void setCrmHolidayPojos( List<CrmHolidayDetails> inCrmHolidayPojos )
    {
        crmHolidayPojos = inCrmHolidayPojos;
    }

    public List<CrmUserPojo> getCrmUserPojos()
    {
        return crmUserPojos;
    }

    public void setCrmUserPojos( List<CrmUserPojo> inCrmUserPojos )
    {
        crmUserPojos = inCrmUserPojos;
    }

    public CrmNpMobilePojo getCrmNpMobilePojo()
    {
        return crmNpMobilePojo;
    }

    public void setCrmNpMobilePojo( CrmNpMobilePojo inCrmNpMobilePojo )
    {
        crmNpMobilePojo = inCrmNpMobilePojo;
    }

    public List<CrmNpMobilePojo> getCrmNpMobilePojos()
    {
        return crmNpMobilePojos;
    }

    public void setCrmNpMobilePojos( List<CrmNpMobilePojo> inCrmNpMobilePojos )
    {
        crmNpMobilePojos = inCrmNpMobilePojos;
    }

    public List<String> getMasterNames()
    {
        return masterNames;
    }

    public void setMasterNames( List<String> inMasterNames )
    {
        masterNames = inMasterNames;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "MasterDataDto [userID=" ).append( userID ).append( ", password=" ).append( password )
                .append( ", statusCode=" ).append( statusCode ).append( ", statusDesc=" ).append( statusDesc )
                .append( ", clientIPAddress=" ).append( clientIPAddress ).append( ", serverIPAddress=" )
                .append( serverIPAddress ).append( ", partnerPojo=" ).append( partnerPojo )
                .append( ", partnerPojoList=" ).append( partnerPojoList ).append( ", partnerPojoMap=" )
                .append( partnerPojoMap ).append( ", crmRolesPojo=" ).append( crmRolesPojo )
                .append( ", crmRolesPojoList=" ).append( crmRolesPojoList ).append( ", crmRolesPojoMap=" )
                .append( crmRolesPojoMap ).append( ", accessGroupPojo=" ).append( accessGroupPojo )
                .append( ", accessGroupPojoList=" ).append( accessGroupPojoList ).append( ", accessGroupPojoMap=" )
                .append( accessGroupPojoMap ).append( ", groupsPojo=" ).append( groupsPojo )
                .append( ", groupsPojoList=" ).append( groupsPojoList ).append( ", crmParameterPojos=" )
                .append( crmParameterPojos ).append( ", projectsPojo=" ).append( projectsPojo )
                .append( ", projectsPojos=" ).append( projectsPojos ).append( ", fromDate=" ).append( fromDate )
                .append( ", toDate=" ).append( toDate ).append( ", emailServerPojo=" ).append( emailServerPojo )
                .append( ", smsGatewayPojo=" ).append( smsGatewayPojo ).append( ", userMasterPojo=" )
                .append( userMasterPojo ).append( ", alertMasterPojo=" ).append( alertMasterPojo )
                .append( ", crmPartnerMappingList=" ).append( crmPartnerMappingList ).append( ", crmRcaReason=" )
                .append( crmRcaReason ).append( ", crmRcaReasonsList=" ).append( crmRcaReasonsList )
                .append( ", crmPartnerMappingPojo=" ).append( crmPartnerMappingPojo )
                .append( ", categoryValueRangeStart=" ).append( categoryValueRangeStart )
                .append( ", categoryValueRangeEnd=" ).append( categoryValueRangeEnd ).append( ", categoryValuePrefix=" )
                .append( categoryValuePrefix ).append( ", crmPartnerDetailsPojos=" ).append( crmPartnerDetailsPojos )
                .append( ", crmPartnerNetworkConfigPojos=" ).append( crmPartnerNetworkConfigPojos )
                .append( ", partnerId=" ).append( partnerId ).append( ", product=" ).append( product )
                .append( ", crmPartnerNetworkConfigPojo=" ).append( crmPartnerNetworkConfigPojo )
                .append( ", crmPlanMasterPojos=" ).append( crmPlanMasterPojos ).append( ", planMaster=" )
                .append( planMaster ).append( ", remarks=" ).append( remarks ).append( ", userAssociatedServices=" )
                .append( userAssociatedServices ).append( ", userAssociatedPartners=" ).append( userAssociatedPartners )
                .append( ", ticketHistory=" ).append( ticketHistory ).append( ", inboxId=" ).append( inboxId )
                .append( "]" );
        return builder.toString();
    }
}
