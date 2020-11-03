package com.np.tele.crm.finance.forms;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.CrmCmsFilePojo;
import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmCustomerRefundsPojo;
import com.np.tele.crm.service.client.CrmPaymentDetailsPojo;
import com.np.tele.crm.service.client.CrmPodDetailsPojo;
import com.np.tele.crm.service.client.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmUpCrfMappingPojo;
import com.np.tele.crm.service.client.CrmUpfrontPaymentPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;

public class FinanceForm
    extends ActionForm
{
    private static final Logger              LOGGER                = Logger.getLogger( FinanceForm.class );
    private List<ContentPojo>                productTypeList;
    private List<ContentPojo>                installationStatusList;
    private List<ContentPojo>                paymentStatusList;
    private List<ContentPojo>                chequeStatusList;
    private List<ContentPojo>                entityTypeList;
    private List<ContentPojo>                paymentModeList;
    private List<ContentPojo>                paymentChannelList;
    private List<CrmQrcSubSubCategoriesPojo> chequeRejectionReasonList;
    private List<CrmPaymentDetailsPojo>      searchPaymentList;
    private String                           customerId;
    private String                           paymentStatus;
    private String                           fromDate;
    private String                           toDate;
    private String                           productType;
    private String                           installationStatus;
    private String                           entity_type;
    private String                           payment_mode;
    private String                           channel_type;
    private String                           cheque_status;
    private CrmPaymentDetailsPojo            crmPaymentDetailsPojo;
    private String                           paymentDate;
    private String                           chequeDate;
    private String                           docSrPaymentReversal;
    private List<CrmRcaReasonPojo>           paymentReversalReason;
    private CrmCustomerDetailsPojo           crmCustomerDetailsPojo;
    private String                           crfId;
    private List<CrmRcaReasonPojo>           bankList;
    private RemarksPojo                      remarksPojo;
    private List<CrmPaymentDetailsPojo>      crmPaymentDetailsPojos;
    private long                             paymentId;
    private String                           bouncingDate;
    private String                           bouncingReason;
    private boolean                          reversalWOCrf;
    private CrmCmsFilePojo                   crmCmsFilePojo;
    private List<CrmCmsFilePojo>             crmCmsFilePojos;
    private CrmCmsPaymentPojo                crmCmsPaymentPojo;
    private List<CrmCmsPaymentPojo>          crmCmsPaymentPojos;
    private String                           modifiedBy;
    private String                           hiddenPaymentIdList;
    private CrmUpfrontPaymentPojo            crmUpfrontPaymentPojo;
    private Set<CrmUpCrfMappingPojo>         crfMappingPojos;
    private List<PartnerPojo>                partnerList;
    private List<CrmUpfrontPaymentPojo>      crmUpfrontPaymentPojoList;
    private int                              searchPaymentListSize = 0;
    private String                           changeDynamicVariable;
    private boolean                          realizationVariable;
    private String                           caseStatus;
    private List<ContentPojo>                caseStatusList;
    private List<ContentPojo>                fileStatusList;
    private List<CrmCmsFilePojo>             clearancefileList;
    private String                           newCustomerId;
    private List<ContentPojo>                caseStausList;
    private long                             upFrontId;
    private List<String>                     crfIds;
    private List<CrmCustomerDetailsPojo>     crmCustomerDetailsPojos;
    private FormFile                         formFile;
    private String                           customerServiceType;
    private List<ContentPojo>                customerServiceTypeList;
    private List<CrmRcaReasonPojo>           paymentGateways;
    private String                           podStatus;
    private String                           searchString;
    private List<CrmCustomerRefundsPojo>     crmCustomerRefundsPojos;
    private CrmCustomerRefundsPojo           crmCustomerRefundsPojo;
    List<CrmRcaReasonPojo>                   refundRjectionList;
    List<CrmPodDetailsPojo>                  crmPodDetailsPojos;
    private CrmPodDetailsPojo                crmPodDetailsPojo;
    private String                           deliverDate;
    private boolean                          displayButton;
    

    public boolean isDisplayButton()
    {
        return displayButton;
    }

    public void setDisplayButton( boolean inDisplayButton )
    {
        displayButton = inDisplayButton;
    }

    public String getDeliverDate()
    {
        return deliverDate;
    }

    public void setDeliverDate( String inDeliverDate )
    {
        deliverDate = inDeliverDate;
    }

    public CrmPodDetailsPojo getCrmPodDetailsPojo()
    {
        return crmPodDetailsPojo;
    }

    public void setCrmPodDetailsPojo( CrmPodDetailsPojo inCrmPodDetailsPojo )
    {
        crmPodDetailsPojo = inCrmPodDetailsPojo;
    }

    public List<CrmPodDetailsPojo> getCrmPodDetailsPojos()
    {
        return crmPodDetailsPojos;
    }

    public void setCrmPodDetailsPojos( List<CrmPodDetailsPojo> inCrmPodDetailsPojos )
    {
        crmPodDetailsPojos = inCrmPodDetailsPojos;
    }

    public List<CrmRcaReasonPojo> getRefundRjectionList()
    {
        return refundRjectionList;
    }

    public void setRefundRjectionList( List<CrmRcaReasonPojo> inRefundRjectionList )
    {
        refundRjectionList = inRefundRjectionList;
    }

    /**
     * @return the formFile
     */
    public FormFile getFormFile()
    {
        return formFile;
    }

    /**
     * @param inFormFile the formFile to set
     */
    public void setFormFile( FormFile inFormFile )
    {
        formFile = inFormFile;
    }

    public List<CrmCustomerDetailsPojo> getCrmCustomerDetailsPojos()
    {
        return crmCustomerDetailsPojos;
    }

    public void setCrmCustomerDetailsPojos( List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos )
    {
        this.crmCustomerDetailsPojos = crmCustomerDetailsPojos;
    }

    public List<String> getCrfIds()
    {
        return crfIds;
    }

    public void setCrfIds( List<String> crfIds )
    {
        this.crfIds = crfIds;
    }

    public long getUpFrontId()
    {
        return upFrontId;
    }

    public void setUpFrontId( long upFrontId )
    {
        this.upFrontId = upFrontId;
    }

    public List<CrmPaymentDetailsPojo> getCrmPaymentDetailsPojos()
    {
        return crmPaymentDetailsPojos;
    }

    public void setCrmPaymentDetailsPojos( List<CrmPaymentDetailsPojo> crmPaymentDetailsPojos )
    {
        this.crmPaymentDetailsPojos = crmPaymentDetailsPojos;
    }

    public String getChequeDate()
    {
        return chequeDate;
    }

    public void setChequeDate( String chequeDate )
    {
        this.chequeDate = chequeDate;
    }

    public List<ContentPojo> getProductTypeList()
    {
        return productTypeList;
    }

    public void setProductTypeList( List<ContentPojo> productTypeList )
    {
        this.productTypeList = productTypeList;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType( String productType )
    {
        this.productType = productType;
    }

    public String getInstallationStatus()
    {
        return installationStatus;
    }

    public void setInstallationStatus( String installationStatus )
    {
        this.installationStatus = installationStatus;
    }

    public String getChannel_type()
    {
        return channel_type;
    }

    public void setChannel_type( String inChannel_type )
    {
        channel_type = inChannel_type;
    }

    public String getPayment_mode()
    {
        return payment_mode;
    }

    public void setPayment_mode( String inPayment_mode )
    {
        payment_mode = inPayment_mode;
    }

    public String getEntity_type()
    {
        return entity_type;
    }

    public void setEntity_type( String inEntity_type )
    {
        entity_type = inEntity_type;
    }

    public String getCheque_status()
    {
        return cheque_status;
    }

    public void setCheque_status( String inCheque_status )
    {
        cheque_status = inCheque_status;
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

    public CrmPaymentDetailsPojo getCrmPaymentDetailsPojo()
    {
        return crmPaymentDetailsPojo;
    }

    public void setCrmPaymentDetailsPojo( CrmPaymentDetailsPojo crmPaymentDetailsPojo )
    {
        this.crmPaymentDetailsPojo = crmPaymentDetailsPojo;
    }

    public List<ContentPojo> getPaymentModeList()
    {
        return paymentModeList;
    }

    public void setPaymentModeList( List<ContentPojo> paymentModeList )
    {
        this.paymentModeList = paymentModeList;
    }

    public List<CrmRcaReasonPojo> getBankList()
    {
        return bankList;
    }

    public void setBankList( List<CrmRcaReasonPojo> bankList )
    {
        this.bankList = bankList;
    }

    public List<ContentPojo> getPaymentChannelList()
    {
        return paymentChannelList;
    }

    public void setPaymentChannelList( List<ContentPojo> paymentChannelList )
    {
        this.paymentChannelList = paymentChannelList;
    }

    public List<CrmPaymentDetailsPojo> getSearchPaymentList()
    {
        return searchPaymentList;
    }

    public void setSearchPaymentList( List<CrmPaymentDetailsPojo> searchPaymentList )
    {
        this.searchPaymentList = searchPaymentList;
    }

    public String getPaymentStatus()
    {
        return paymentStatus;
    }

    public void setPaymentStatus( String paymentStatus )
    {
        this.paymentStatus = paymentStatus;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getPaymentDate()
    {
        return paymentDate;
    }

    public void setPaymentDate( String paymentDate )
    {
        this.paymentDate = paymentDate;
    }

    public String getCrfId()
    {
        return crfId;
    }

    public void setCrfId( String inCrfId )
    {
        crfId = inCrfId;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public String getDocSrPaymentReversal()
    {
        return docSrPaymentReversal;
    }

    public void setDocSrPaymentReversal( String inDocSrPaymentReversal )
    {
        docSrPaymentReversal = inDocSrPaymentReversal;
    }

    public List<CrmRcaReasonPojo> getPaymentReversalReason()
    {
        return paymentReversalReason;
    }

    public void setPaymentReversalReason( List<CrmRcaReasonPojo> inPaymentReversalReason )
    {
        paymentReversalReason = inPaymentReversalReason;
    }

    public CrmCustomerDetailsPojo getCrmCustomerDetailsPojo()
    {
        return crmCustomerDetailsPojo;
    }

    public void setCrmCustomerDetailsPojo( CrmCustomerDetailsPojo crmCustomerDetailsPojo )
    {
        this.crmCustomerDetailsPojo = crmCustomerDetailsPojo;
    }

    public boolean isReversalWOCrf()
    {
        return reversalWOCrf;
    }

    public void setReversalWOCrf( boolean reversalWOCrf )
    {
        this.reversalWOCrf = reversalWOCrf;
    }

    public List<CrmQrcSubSubCategoriesPojo> getChequeRejectionReasonList()
    {
        return chequeRejectionReasonList;
    }

    public void setChequeRejectionReasonList( List<CrmQrcSubSubCategoriesPojo> inChequeRejectionReasonList )
    {
        chequeRejectionReasonList = inChequeRejectionReasonList;
    }

    public String getBouncingDate()
    {
        return bouncingDate;
    }

    public void setBouncingDate( String bouncingDate )
    {
        this.bouncingDate = bouncingDate;
    }

    public String getBouncingReason()
    {
        return bouncingReason;
    }

    public void setBouncingReason( String bouncingReason )
    {
        this.bouncingReason = bouncingReason;
    }

    public long getPaymentId()
    {
        return paymentId;
    }

    public void setPaymentId( long paymentId )
    {
        this.paymentId = paymentId;
    }

    public List<ContentPojo> getInstallationStatusList()
    {
        return installationStatusList;
    }

    public void setInstallationStatusList( List<ContentPojo> installationStatusList )
    {
        this.installationStatusList = installationStatusList;
    }

    public List<ContentPojo> getPaymentStatusList()
    {
        return paymentStatusList;
    }

    public void setPaymentStatusList( List<ContentPojo> paymentStatusList )
    {
        this.paymentStatusList = paymentStatusList;
    }

    public List<ContentPojo> getChequeStatusList()
    {
        return chequeStatusList;
    }

    public void setChequeStatusList( List<ContentPojo> chequeStatusList )
    {
        this.chequeStatusList = chequeStatusList;
    }

    public List<CrmCmsPaymentPojo> getCrmCmsPaymentPojos()
    {
        return crmCmsPaymentPojos;
    }

    public void setCrmCmsPaymentPojos( List<CrmCmsPaymentPojo> crmCmsPaymentPojos )
    {
        this.crmCmsPaymentPojos = crmCmsPaymentPojos;
    }

    public List<ContentPojo> getFileStatusList()
    {
        return fileStatusList;
    }

    public void setFileStatusList( List<ContentPojo> fileStatusList )
    {
        this.fileStatusList = fileStatusList;
    }

    public List<CrmCmsFilePojo> getClearancefileList()
    {
        return clearancefileList;
    }

    public void setClearancefileList( List<CrmCmsFilePojo> clearancefileList )
    {
        this.clearancefileList = clearancefileList;
    }

    public String getNewCustomerId()
    {
        return newCustomerId;
    }

    public void setNewCustomerId( String newCustomerId )
    {
        this.newCustomerId = newCustomerId;
    }

    public List<ContentPojo> getCaseStausList()
    {
        return caseStausList;
    }

    public void setCaseStausList( List<ContentPojo> caseStausList )
    {
        this.caseStausList = caseStausList;
    }

    public List<ContentPojo> getEntityTypeList()
    {
        return entityTypeList;
    }

    public void setEntityTypeList( List<ContentPojo> entityTypeList )
    {
        this.entityTypeList = entityTypeList;
    }

    public CrmCmsFilePojo getCrmCmsFilePojo()
    {
        return crmCmsFilePojo;
    }

    public void setCrmCmsFilePojo( CrmCmsFilePojo crmCmsFilePojo )
    {
        this.crmCmsFilePojo = crmCmsFilePojo;
    }

    public List<CrmCmsFilePojo> getCrmCmsFilePojos()
    {
        return crmCmsFilePojos;
    }

    public void setCrmCmsFilePojos( List<CrmCmsFilePojo> crmCmsFilePojos )
    {
        this.crmCmsFilePojos = crmCmsFilePojos;
    }

    public CrmCmsPaymentPojo getCrmCmsPaymentPojo()
    {
        return crmCmsPaymentPojo;
    }

    public void setCrmCmsPaymentPojo( CrmCmsPaymentPojo crmCmsPaymentPojo )
    {
        this.crmCmsPaymentPojo = crmCmsPaymentPojo;
    }

    public String getModifiedBy()
    {
        return modifiedBy;
    }

    public void setModifiedBy( String modifiedBy )
    {
        this.modifiedBy = modifiedBy;
    }

    public String getHiddenPaymentIdList()
    {
        return hiddenPaymentIdList;
    }

    public void setHiddenPaymentIdList( String inHiddenPaymentIdList )
    {
        hiddenPaymentIdList = inHiddenPaymentIdList;
    }

    public CrmUpfrontPaymentPojo getCrmUpfrontPaymentPojo()
    {
        return crmUpfrontPaymentPojo;
    }

    public void setCrmUpfrontPaymentPojo( CrmUpfrontPaymentPojo inCrmUpfrontPaymentPojo )
    {
        crmUpfrontPaymentPojo = inCrmUpfrontPaymentPojo;
    }

    public Set<CrmUpCrfMappingPojo> getCrfMappingPojos()
    {
        return crfMappingPojos;
    }

    public void setCrfMappingPojos( Set<CrmUpCrfMappingPojo> crfMappingPojos )
    {
        this.crfMappingPojos = crfMappingPojos;
    }

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> partnerList )
    {
        this.partnerList = partnerList;
    }

    public List<CrmUpfrontPaymentPojo> getCrmUpfrontPaymentPojoList()
    {
        return crmUpfrontPaymentPojoList;
    }

    public void setCrmUpfrontPaymentPojoList( List<CrmUpfrontPaymentPojo> crmUpfrontPaymentPojoList )
    {
        this.crmUpfrontPaymentPojoList = crmUpfrontPaymentPojoList;
    }

    public int getSearchPaymentListSize()
    {
        return searchPaymentListSize;
    }

    public void setSearchPaymentListSize( int inSearchPaymentListSize )
    {
        searchPaymentListSize = inSearchPaymentListSize;
    }

    public String getChangeDynamicVariable()
    {
        return changeDynamicVariable;
    }

    public void setChangeDynamicVariable( String changeDynamicVariable )
    {
        this.changeDynamicVariable = changeDynamicVariable;
    }

    public boolean isRealizationVariable()
    {
        return realizationVariable;
    }

    public void setRealizationVariable( boolean inRealizationVariable )
    {
        realizationVariable = inRealizationVariable;
    }

    public String getCaseStatus()
    {
        return caseStatus;
    }

    public void setCaseStatus( String caseStatus )
    {
        this.caseStatus = caseStatus;
    }

    public List<ContentPojo> getCaseStatusList()
    {
        return caseStatusList;
    }

    public void setCaseStatusList( List<ContentPojo> caseStatusList )
    {
        this.caseStatusList = caseStatusList;
    }

    public String getCustomerServiceType()
    {
        return customerServiceType;
    }

    public void setCustomerServiceType( String inCustomerServiceType )
    {
        customerServiceType = inCustomerServiceType;
    }

    public List<ContentPojo> getCustomerServiceTypeList()
    {
        return customerServiceTypeList;
    }

    public void setCustomerServiceTypeList( List<ContentPojo> inCustomerServiceTypeList )
    {
        customerServiceTypeList = inCustomerServiceTypeList;
    }

    public List<CrmRcaReasonPojo> getPaymentGateways()
    {
        return paymentGateways;
    }

    public void setPaymentGateways( List<CrmRcaReasonPojo> inPaymentGateways )
    {
        paymentGateways = inPaymentGateways;
    }

    public String getPodStatus()
    {
        return podStatus;
    }

    public void setPodStatus( String inPodStatus )
    {
        podStatus = inPodStatus;
    }

    public String getSearchString()
    {
        return searchString;
    }

    public void setSearchString( String inSearchString )
    {
        searchString = inSearchString;
    }

    public List<CrmCustomerRefundsPojo> getCrmCustomerRefundsPojos()
    {
        return crmCustomerRefundsPojos;
    }

    public void setCrmCustomerRefundsPojos( List<CrmCustomerRefundsPojo> inCrmCustomerRefundsPojos )
    {
        crmCustomerRefundsPojos = inCrmCustomerRefundsPojos;
    }

    public CrmCustomerRefundsPojo getCrmCustomerRefundsPojo()
    {
        return crmCustomerRefundsPojo;
    }

    public void setCrmCustomerRefundsPojo( CrmCustomerRefundsPojo inCrmCustomerRefundsPojo )
    {
        crmCustomerRefundsPojo = inCrmCustomerRefundsPojo;
    }

    @Override
    public ActionErrors validate( ActionMapping mapping, HttpServletRequest request )
    {
        String method = request.getParameter( IAppConstants.PARAMETER_NAME );
        ActionErrors actionError = new ActionErrors();
        FinanceFormHelper.validateForm( method, actionError, this );
        return actionError;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        LOGGER.info( "PaymentTracking reset start" );
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        if ( methodName != null )
        {
            FinanceFormHelper.resetFinanceForm( this, methodName );
        }
        LOGGER.info( "PaymentTracking Reset End" );
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "FinanceForm [customerId=" ).append( customerId ).append( ", fromDate=" ).append( fromDate )
                .append( ", toDate=" ).append( toDate ).append( ", productType=" ).append( productType )
                .append( ", installationStatus=" ).append( installationStatus ).append( ", payment_mode=" )
                .append( payment_mode ).append( ", channel_type=" ).append( channel_type ).append( ", paymentDate=" )
                .append( paymentDate ).append( ", crfId=" ).append( crfId ).append( ", paymentId=" ).append( paymentId )
                .append( ", reversalWOCrf=" ).append( reversalWOCrf ).append( ", caseStatus=" ).append( caseStatus )
                .append( "]" );
        return builder.toString();
    }
}