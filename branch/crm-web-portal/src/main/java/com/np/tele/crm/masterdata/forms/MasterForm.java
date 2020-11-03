package com.np.tele.crm.masterdata.forms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AccessGroupPojo;
import com.np.tele.crm.service.client.AlertMasterPojo;
import com.np.tele.crm.service.client.CrmParameterPojo;
import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;
import com.np.tele.crm.service.client.CrmPartnerNetworkConfigPojo;
import com.np.tele.crm.service.client.EmailServerPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.SmsGatewayPojo;

public class MasterForm
    extends ActionForm
{
    private static final Logger               LOGGER = Logger.getLogger( MasterForm.class );
    //option 82 
    private List<PartnerPojo>                 partnerList;
    private List<CrmParameterPojo>            crmParameterList;
    private List<CrmParameterPojo>            paramIdList;
    private EmailServerPojo                   emailServerPojo;
    private EmailServerPojo                   oldEmailServerPojo;
    private List<EmailServerPojo>             searchEmailServerList;
    private String                            masterAlias;
    private AlertMasterPojo                   alertMasterPojo;
    private Long                              partnerId;
    private Long                              partnerDetailsId;
    private SmsGatewayPojo                    smsGatewayPojo;
    private List<SmsGatewayPojo>              smsGateWayList;
    private String                            searchsubCategory;
    private String                            smsAlias;
    private List<String>                      partnerNames;
    private List<PartnerPojo>                 partnerPojos;
    private String                            partnerName;
    private String                            businessType;
    private List<CrmPartnerDetailsPojo>       partnerDetailsList;
    private List<ContentPojo>                 productTypeList;
    private List<ContentPojo>                 oltTypeList;
    private String                            oltType;
    private String                            showDivValue;
    private int                               rowCounter;
    private List<CrmPartnerNetworkConfigPojo> crmPartnerNetworkConfigPojos;
    private CrmPartnerNetworkConfigPojo       crmPartnerNetworkConfigPojo;
    private SmsGatewayPojo                    oldSmsGatewayPojo;

    public String getSmsAlias()
    {
        return smsAlias;
    }

    public void setSmsAlias( String smsAlias )
    {
        this.smsAlias = smsAlias;
    }

    public String getSearchsubCategory()
    {
        return searchsubCategory;
    }

    public void setSearchsubCategory( String searchsubCategory )
    {
        this.searchsubCategory = searchsubCategory;
    }

    public List<SmsGatewayPojo> getSmsGateWayList()
    {
        return smsGateWayList;
    }

    public void setSmsGateWayList( List<SmsGatewayPojo> smsGateWayList )
    {
        this.smsGateWayList = smsGateWayList;
    }

    public SmsGatewayPojo getSmsGatewayPojo()
    {
        return smsGatewayPojo;
    }

    public void setSmsGatewayPojo( SmsGatewayPojo smsGatewayPojo )
    {
        this.smsGatewayPojo = smsGatewayPojo;
    }

    public Long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( Long partnerId )
    {
        this.partnerId = partnerId;
    }

    public List<CrmParameterPojo> getCrmParameterList()
    {
        return crmParameterList;
    }

    public void setCrmParameterList( List<CrmParameterPojo> crmParameterList )
    {
        this.crmParameterList = crmParameterList;
    }

    public List<AccessGroupPojo> getAccessGroups()
    {
        return accessGroups;
    }

    public void setAccessGroups( List<AccessGroupPojo> accessGroups )
    {
        this.accessGroups = accessGroups;
    }
    private List<AccessGroupPojo> accessGroups;

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> partnerList )
    {
        this.partnerList = partnerList;
    }

    public EmailServerPojo getEmailServerPojo()
    {
        return emailServerPojo;
    }

    public void setEmailServerPojo( EmailServerPojo emailServerPojo )
    {
        this.emailServerPojo = emailServerPojo;
    }

    public List<CrmParameterPojo> getParamIdList()
    {
        return paramIdList;
    }

    public void setParamIdList( List<CrmParameterPojo> paramIdList )
    {
        this.paramIdList = paramIdList;
    }

    public void setSearchEmailServerList( List<EmailServerPojo> searchEmailServerList )
    {
        this.searchEmailServerList = searchEmailServerList;
    }

    public List<EmailServerPojo> getSearchEmailServerList()
    {
        return searchEmailServerList;
    }

    public String getMasterAlias()
    {
        return masterAlias;
    }

    public void setMasterAlias( String masterAlias )
    {
        this.masterAlias = masterAlias;
    }

    public List<String> getPartnerNames()
    {
        return partnerNames;
    }

    public void setPartnerNames( List<String> inPartnerNames )
    {
        partnerNames = inPartnerNames;
    }

    public String getPartnerName()
    {
        return partnerName;
    }

    public void setPartnerName( String inPartnerName )
    {
        partnerName = inPartnerName;
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

    public List<PartnerPojo> getPartnerPojos()
    {
        return partnerPojos;
    }

    public void setPartnerPojos( List<PartnerPojo> inPartnerPojos )
    {
        partnerPojos = inPartnerPojos;
    }

    public AlertMasterPojo getAlertMasterPojo()
    {
        return alertMasterPojo;
    }

    public void setAlertMasterPojo( AlertMasterPojo alertMasterPojo )
    {
        this.alertMasterPojo = alertMasterPojo;
    }

    public Long getPartnerDetailsId()
    {
        return partnerDetailsId;
    }

    public void setPartnerDetailsId( Long inPartnerDetailsId )
    {
        partnerDetailsId = inPartnerDetailsId;
    }

    public List<CrmPartnerDetailsPojo> getPartnerDetailsList()
    {
        return partnerDetailsList;
    }

    public void setPartnerDetailsList( List<CrmPartnerDetailsPojo> inPartnerDetailsList )
    {
        partnerDetailsList = inPartnerDetailsList;
    }

    public String getOltType()
    {
        return oltType;
    }

    public void setOltType( String inOltType )
    {
        oltType = inOltType;
    }

    public List<ContentPojo> getProductTypeList()
    {
        return productTypeList;
    }

    public void setProductTypeList( List<ContentPojo> inProductTypeList )
    {
        productTypeList = inProductTypeList;
    }

    public List<ContentPojo> getOltTypeList()
    {
        return oltTypeList;
    }

    public void setOltTypeList( List<ContentPojo> inOltTypeList )
    {
        oltTypeList = inOltTypeList;
    }

    public String getBusinessType()
    {
        return businessType;
    }

    public void setBusinessType( String inBusinessType )
    {
        businessType = inBusinessType;
    }

    public String getShowDivValue()
    {
        return showDivValue;
    }

    public void setShowDivValue( String inShowDivValue )
    {
        showDivValue = inShowDivValue;
    }

    public EmailServerPojo getOldEmailServerPojo()
    {
        return oldEmailServerPojo;
    }

    public void setOldEmailServerPojo( EmailServerPojo inOldEmailServerPojo )
    {
        oldEmailServerPojo = inOldEmailServerPojo;
    }

    public int getRowCounter()
    {
        return rowCounter;
    }

    public void setRowCounter( int inRowCounter )
    {
        rowCounter = inRowCounter;
    }

    public SmsGatewayPojo getOldSmsGatewayPojo()
    {
        return oldSmsGatewayPojo;
    }

    public void setOldSmsGatewayPojo( SmsGatewayPojo inOldSmsGatewayPojo )
    {
        oldSmsGatewayPojo = inOldSmsGatewayPojo;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "MasterForm [partnerList=" ).append( partnerList ).append( ", crmParameterList=" )
                .append( crmParameterList ).append( ", paramIdList=" ).append( paramIdList )
                .append( ", emailServerPojo=" ).append( emailServerPojo ).append( ", oldEmailServerPojo=" )
                .append( oldEmailServerPojo ).append( ", searchEmailServerList=" ).append( searchEmailServerList )
                .append( ", masterAlias=" ).append( masterAlias ).append( ", alertMasterPojo=" )
                .append( alertMasterPojo ).append( ", partnerId=" ).append( partnerId ).append( ", partnerDetailsId=" )
                .append( partnerDetailsId ).append( ", smsGatewayPojo=" ).append( smsGatewayPojo )
                .append( ", oldSmsGatewayPojo=" ).append( oldSmsGatewayPojo ).append( ", smsGateWayList=" )
                .append( smsGateWayList ).append( ", searchsubCategory=" ).append( searchsubCategory )
                .append( ", smsAlias=" ).append( smsAlias ).append( ", partnerNames=" ).append( partnerNames )
                .append( ", partnerPojos=" ).append( partnerPojos ).append( ", partnerName=" ).append( partnerName )
                .append( ", businessType=" ).append( businessType ).append( ", partnerDetailsList=" )
                .append( partnerDetailsList ).append( ", productTypeList=" ).append( productTypeList )
                .append( ", oltTypeList=" ).append( oltTypeList ).append( ", oltType=" ).append( oltType )
                .append( ", showDivValue=" ).append( showDivValue ).append( ", rowCounter=" ).append( rowCounter )
                .append( ", crmPartnerNetworkConfigPojos=" ).append( crmPartnerNetworkConfigPojos )
                .append( ", crmPartnerNetworkConfigPojo=" ).append( crmPartnerNetworkConfigPojo )
                .append( ", accessGroups=" ).append( accessGroups ).append( "]" );
        return builder.toString();
    }

    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LOGGER.info( "In validate method........" + method + "parameter name:"
                + inRequest.getParameter( IAppConstants.PARAMETER_NAME ) );
        ActionErrors actionError = new ActionErrors();
        MasterFormHelper.validateForm( actionError, method, this );
        return actionError;
    }

    @Override
    public void reset( ActionMapping mapping, HttpServletRequest request )
    {
        LOGGER.info( "got parameter name:" + request.getParameter( IAppConstants.PARAMETER_NAME ) );
        String methodName = request.getParameter( IAppConstants.PARAMETER_NAME );
        MasterFormHelper.resetMasterData( methodName, this );
    }
}
