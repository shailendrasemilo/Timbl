package com.np.tele.crm.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.np.tele.crm.pojos.CrmCmsFilePojo;
import com.np.tele.crm.pojos.CrmCmsPaymentPojo;
import com.np.tele.crm.pojos.CrmCustomerDetailsPojo;
import com.np.tele.crm.pojos.CrmCustomerRefundsPojo;
import com.np.tele.crm.pojos.CrmPaymentDetailsPojo;
import com.np.tele.crm.pojos.CrmPgwTransactionsPojo;
import com.np.tele.crm.pojos.CrmPodDetailsPojo;
import com.np.tele.crm.pojos.CrmUpCrfMappingPojo;
import com.np.tele.crm.pojos.CrmUpfrontPaymentPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class CrmFinanceDto
    implements Serializable
{
    private String                       userId                  = null;
    private String                       password                = null;
    private String                       statusDesc              = null;
    private String                       statusCode              = null;
    private Set<String>                  statusCodes             = null;
    private String                       clientIPAddress         = null;
    private String                       serverIPAddress         = null;
    private CrmCmsFilePojo               cmsFile                 = null;
    private List<CrmCmsFilePojo>         cmsFiles                = null;
    private CrmCmsPaymentPojo            cmsPayment              = null;
    private List<CrmCmsPaymentPojo>      cmsPayments             = null;
    private CrmPaymentDetailsPojo        paymentDetailsPojo      = null;
    private List<CrmPaymentDetailsPojo>  paymentDetailsPojos     = null;
    private CrmCustomerDetailsPojo       customerDetailsPojo     = null;
    private String                       fromDate                = null;
    private String                       toDate                  = null;
    private RemarksPojo                  remarks                 = null;
    private boolean                      reversalWOCrf           = false;
    private CrmUpfrontPaymentPojo        crmUpfrontPaymentPojo   = null;
    private List<CrmUpfrontPaymentPojo>  crmUpfrontPaymentPojos  = null;
    private CrmUpCrfMappingPojo          crfMappingPojo          = null;
    private List<CrmUpCrfMappingPojo>    crfMappingPojos         = null;
    private List<String>                 crfIDs                  = null;
    private List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos = null;
    private String                       billingErrorCode        = null;
    private CrmPgwTransactionsPojo       crmPgwTransactionsPojo  = null;
    private CrmCustomerRefundsPojo       crmCustomerRefundsPojo  = null;
    private List<CrmCustomerRefundsPojo> refundDetailsPojos      = null;
    private CrmPodDetailsPojo            crmPodDetailsPojo       = null;
    private List<CrmPodDetailsPojo>      crmPodDetailsPojos      = null;
    private List<String>                 userAssociatedServices;
    private List<String>                 userAssociatedPartners;
    private int                          dbSuccessCount;
 
    
    

    public int getDbSuccessCount()
    {
        return dbSuccessCount;
    }

    public void setDbSuccessCount( int inDbSuccessCount )
    {
        dbSuccessCount = inDbSuccessCount;
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

    public List<CrmCustomerDetailsPojo> getCrmCustomerDetailsPojos()
    {
        return crmCustomerDetailsPojos;
    }

    public void setCrmCustomerDetailsPojos( List<CrmCustomerDetailsPojo> crmCustomerDetailsPojos )
    {
        this.crmCustomerDetailsPojos = crmCustomerDetailsPojos;
    }

    public List<String> getCrfIDs()
    {
        return crfIDs;
    }

    public void setCrfIDs( List<String> crfIDs )
    {
        this.crfIDs = crfIDs;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String inPassword )
    {
        password = inPassword;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public Set<String> getStatusCodes()
    {
        return statusCodes;
    }

    public void setStatusCodes( Set<String> inStatusCodes )
    {
        statusCodes = inStatusCodes;
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

    public CrmCmsFilePojo getCmsFile()
    {
        return cmsFile;
    }

    public void setCmsFile( CrmCmsFilePojo inCmsFile )
    {
        cmsFile = inCmsFile;
    }

    public List<CrmCmsFilePojo> getCmsFiles()
    {
        return cmsFiles;
    }

    public void setCmsFiles( List<CrmCmsFilePojo> inCmsFiles )
    {
        cmsFiles = inCmsFiles;
    }

    public CrmCmsPaymentPojo getCmsPayment()
    {
        return cmsPayment;
    }

    public void setCmsPayment( CrmCmsPaymentPojo inCmsPayment )
    {
        cmsPayment = inCmsPayment;
    }

    public List<CrmCmsPaymentPojo> getCmsPayments()
    {
        return cmsPayments;
    }

    public void setCmsPayments( List<CrmCmsPaymentPojo> inCmsPayments )
    {
        cmsPayments = inCmsPayments;
    }

    public CrmPaymentDetailsPojo getPaymentDetailsPojo()
    {
        return paymentDetailsPojo;
    }

    public void setPaymentDetailsPojo( CrmPaymentDetailsPojo inPaymentDetailsPojo )
    {
        paymentDetailsPojo = inPaymentDetailsPojo;
    }

    public List<CrmPaymentDetailsPojo> getPaymentDetailsPojos()
    {
        return paymentDetailsPojos;
    }

    public void setPaymentDetailsPojos( List<CrmPaymentDetailsPojo> inPaymentDetailsPojos )
    {
        paymentDetailsPojos = inPaymentDetailsPojos;
    }

    public CrmCustomerDetailsPojo getCustomerDetailsPojo()
    {
        return customerDetailsPojo;
    }

    public void setCustomerDetailsPojo( CrmCustomerDetailsPojo inCustomerDetailsPojo )
    {
        customerDetailsPojo = inCustomerDetailsPojo;
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

    public RemarksPojo getRemarks()
    {
        return remarks;
    }

    public void setRemarks( RemarksPojo inRemarks )
    {
        remarks = inRemarks;
    }

    public boolean isReversalWOCrf()
    {
        return reversalWOCrf;
    }

    public void setReversalWOCrf( boolean reversalWOCrf )
    {
        this.reversalWOCrf = reversalWOCrf;
    }

    public CrmUpfrontPaymentPojo getCrmUpfrontPaymentPojo()
    {
        return crmUpfrontPaymentPojo;
    }

    public void setCrmUpfrontPaymentPojo( CrmUpfrontPaymentPojo crmUpfrontPaymentPojo )
    {
        this.crmUpfrontPaymentPojo = crmUpfrontPaymentPojo;
    }

    public List<CrmUpfrontPaymentPojo> getCrmUpfrontPaymentPojos()
    {
        return crmUpfrontPaymentPojos;
    }

    public void setCrmUpfrontPaymentPojos( List<CrmUpfrontPaymentPojo> crmUpfrontPaymentPojos )
    {
        this.crmUpfrontPaymentPojos = crmUpfrontPaymentPojos;
    }

    public CrmUpCrfMappingPojo getCrfMappingPojo()
    {
        return crfMappingPojo;
    }

    public void setCrfMappingPojo( CrmUpCrfMappingPojo crfMappingPojo )
    {
        this.crfMappingPojo = crfMappingPojo;
    }

    public List<CrmUpCrfMappingPojo> getCrfMappingPojos()
    {
        return crfMappingPojos;
    }

    public void setCrfMappingPojos( List<CrmUpCrfMappingPojo> crfMappingPojos )
    {
        this.crfMappingPojos = crfMappingPojos;
    }

    public String getBillingErrorCode()
    {
        return billingErrorCode;
    }

    public void setBillingErrorCode( String inBillingErrorCode )
    {
        billingErrorCode = inBillingErrorCode;
    }

    public CrmPgwTransactionsPojo getCrmPgwTransactionsPojo()
    {
        return crmPgwTransactionsPojo;
    }

    public void setCrmPgwTransactionsPojo( CrmPgwTransactionsPojo inCrmPgwTransactionsPojo )
    {
        crmPgwTransactionsPojo = inCrmPgwTransactionsPojo;
    }

    public CrmCustomerRefundsPojo getCrmCustomerRefundsPojo()
    {
        return crmCustomerRefundsPojo;
    }

    public void setCrmCustomerRefundsPojo( CrmCustomerRefundsPojo inCrmCustomerRefundsPojo )
    {
        crmCustomerRefundsPojo = inCrmCustomerRefundsPojo;
    }

    public List<CrmCustomerRefundsPojo> getRefundDetailsPojos()
    {
        return refundDetailsPojos;
    }

    public void setRefundDetailsPojos( List<CrmCustomerRefundsPojo> inRefundDetailsPojos )
    {
        refundDetailsPojos = inRefundDetailsPojos;
    }

    public CrmPodDetailsPojo getCrmPodDetailsPojo()
    {
        return crmPodDetailsPojo;
    }

    public List<CrmPodDetailsPojo> getCrmPodDetailsPojos()
    {
        return crmPodDetailsPojos;
    }

    public void setCrmPodDetailsPojo( CrmPodDetailsPojo inCrmPodDetailsPojo )
    {
        crmPodDetailsPojo = inCrmPodDetailsPojo;
    }

    public void setCrmPodDetailsPojos( List<CrmPodDetailsPojo> inCrmPodDetailsPojos )
    {
        crmPodDetailsPojos = inCrmPodDetailsPojos;
    }
}
