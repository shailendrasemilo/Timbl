package com.np.tele.crm.dto;

import java.util.List;
import java.util.Map;

import com.np.tele.crm.ext.pojos.InaReportPojo;
import com.np.tele.crm.ext.pojos.LmsReportPojo;
import com.np.tele.crm.ext.pojos.PaymentReportPojo;
import com.np.tele.crm.ext.pojos.QrcTicketReportPojo;
import com.np.tele.crm.ext.pojos.RfsReportPojo;
import com.np.tele.crm.ext.pojos.SlaReportPojo;
import com.np.tele.crm.pojos.AreaPojo;
import com.np.tele.crm.pojos.CrmAuditLogPojo;
import com.np.tele.crm.pojos.CrmCustInteractionsPojo;
import com.np.tele.crm.pojos.CrmCustWaiverPojo;
import com.np.tele.crm.pojos.CrmCustomerActivityPojo;
import com.np.tele.crm.pojos.CrmMassOutagePojo;
import com.np.tele.crm.pojos.CrmQrcSubSubCategoriesPojo;
import com.np.tele.crm.pojos.CrmSlaLogPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class ReportDto
{
    private String                        statusCode;
    private String                        statusDesc;
    private String                        fromDate            = null;
    private String                        toDate              = null;
    private List<InaReportPojo>           inaReportPojos;
    private List<QrcTicketReportPojo>     qrcReportPojos;
    private List<CrmMassOutagePojo>       crmMassOutagePojos  = null;
    private CrmMassOutagePojo             crmMassOutagePojo   = null;
    private List<CrmCustWaiverPojo>       crmCustWaiverPojos;
    private List<CrmCustInteractionsPojo> crmCustInteractionsPojos;
    private List<CrmCustomerActivityPojo> crmCustomerActivityPojos;
    private String                        clientIPAddress     = null;
    private String                        serverIPAddress     = null;
    private String                        serviceType;
    private String                        productType;
    private CrmSlaLogPojo                 crmSlaLogPojo;
    private CrmAuditLogPojo               crmAuditLogPojo;
    private List<CrmSlaLogPojo>           slaLogPojos;
    private List<CrmAuditLogPojo>         auditLogPojos;
    private QrcTicketReportPojo           qrcTicketReportPojo = null;
    private List<LmsReportPojo>           lmsReportPojos;
    private List<RfsReportPojo>           rfsReportPojos;
    private List<PaymentReportPojo>       paymentReportPojos;
    private PaymentReportPojo             paymentReportPojo;
    private List<RemarksPojo>             remarksPojos;
    private RemarksPojo                   remarksPojo;
    private AreaPojo                      areaPojo;
    private Map<String, String>           paramMap;
    private LMSPojo                       leadPojo;
    private SlaReportPojo                 slaReportPojo;
    private List<SlaReportPojo>           slaReportPojos;
    List<CrmQrcSubSubCategoriesPojo>      qrcSubSubCategoriesPojos;
    CrmQrcSubSubCategoriesPojo            subSubCategoriesPojo;
    private String                        stateName;
    private String                        cityName;
    private String                        areaName;
    private String                        societyName;

    public List<CrmQrcSubSubCategoriesPojo> getQrcSubSubCategoriesPojos()
    {
        return qrcSubSubCategoriesPojos;
    }

    public void setQrcSubSubCategoriesPojos( List<CrmQrcSubSubCategoriesPojo> inQrcSubSubCategoriesPojos )
    {
        qrcSubSubCategoriesPojos = inQrcSubSubCategoriesPojos;
    }

    public CrmQrcSubSubCategoriesPojo getSubSubCategoriesPojo()
    {
        return subSubCategoriesPojo;
    }

    public void setSubSubCategoriesPojo( CrmQrcSubSubCategoriesPojo inSubSubCategoriesPojo )
    {
        subSubCategoriesPojo = inSubSubCategoriesPojo;
    }

    public LMSPojo getLeadPojo()
    {
        return leadPojo;
    }

    public void setLeadPojo( LMSPojo inLeadPojo )
    {
        leadPojo = inLeadPojo;
    }

    public String getStatusCode()
    {
        return statusCode;
    }

    public void setStatusCode( String inStatusCode )
    {
        statusCode = inStatusCode;
    }

    public String getStatusDesc()
    {
        return statusDesc;
    }

    public void setStatusDesc( String inStatusDesc )
    {
        statusDesc = inStatusDesc;
    }

    public Map<String, String> getParamMap()
    {
        return paramMap;
    }

    public void setParamMap( Map<String, String> inParamMap )
    {
        paramMap = inParamMap;
    }

    public AreaPojo getAreaPojo()
    {
        return areaPojo;
    }

    public void setAreaPojo( AreaPojo inAreaPojo )
    {
        areaPojo = inAreaPojo;
    }

    public List<RemarksPojo> getRemarksPojos()
    {
        return remarksPojos;
    }

    public void setRemarksPojos( List<RemarksPojo> inRemarksPojos )
    {
        remarksPojos = inRemarksPojos;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public CrmAuditLogPojo getCrmAuditLogPojo()
    {
        return crmAuditLogPojo;
    }

    public void setCrmAuditLogPojo( CrmAuditLogPojo inCrmAuditLogPojo )
    {
        crmAuditLogPojo = inCrmAuditLogPojo;
    }

    public CrmSlaLogPojo getCrmSlaLogPojo()
    {
        return crmSlaLogPojo;
    }

    public void setCrmSlaLogPojo( CrmSlaLogPojo inCrmSlaLogPojo )
    {
        crmSlaLogPojo = inCrmSlaLogPojo;
    }

    public List<CrmSlaLogPojo> getSlaLogPojos()
    {
        return slaLogPojos;
    }

    public void setSlaLogPojos( List<CrmSlaLogPojo> inSlaLogPojos )
    {
        slaLogPojos = inSlaLogPojos;
    }

    public List<CrmAuditLogPojo> getAuditLogPojos()
    {
        return auditLogPojos;
    }

    public void setAuditLogPojos( List<CrmAuditLogPojo> inAuditLogPojos )
    {
        auditLogPojos = inAuditLogPojos;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType( String inServiceType )
    {
        serviceType = inServiceType;
    }

    public String getProductType()
    {
        return productType;
    }

    public void setProductType( String inProductType )
    {
        productType = inProductType;
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

    public String getFromDate()
    {
        return fromDate;
    }

    public void setFromDate( String fromDate )
    {
        this.fromDate = fromDate;
    }

    public String getToDate()
    {
        return toDate;
    }

    public void setToDate( String toDate )
    {
        this.toDate = toDate;
    }

    public List<InaReportPojo> getInaReportPojos()
    {
        return inaReportPojos;
    }

    public void setInaReportPojos( List<InaReportPojo> inaReportPojos )
    {
        this.inaReportPojos = inaReportPojos;
    }

    public List<QrcTicketReportPojo> getQrcReportPojos()
    {
        return qrcReportPojos;
    }

    public void setQrcReportPojos( List<QrcTicketReportPojo> qrcReportPojos )
    {
        this.qrcReportPojos = qrcReportPojos;
    }

    public List<CrmMassOutagePojo> getCrmMassOutagePojos()
    {
        return crmMassOutagePojos;
    }

    public CrmMassOutagePojo getCrmMassOutagePojo()
    {
        return crmMassOutagePojo;
    }

    public void setCrmMassOutagePojos( List<CrmMassOutagePojo> inCrmMassOutagePojos )
    {
        crmMassOutagePojos = inCrmMassOutagePojos;
    }

    public void setCrmMassOutagePojo( CrmMassOutagePojo inCrmMassOutagePojo )
    {
        crmMassOutagePojo = inCrmMassOutagePojo;
    }

    public List<CrmCustWaiverPojo> getCrmCustWaiverPojos()
    {
        return crmCustWaiverPojos;
    }

    public void setCrmCustWaiverPojos( List<CrmCustWaiverPojo> crmCustWaiverPojos )
    {
        this.crmCustWaiverPojos = crmCustWaiverPojos;
    }

    public List<CrmCustInteractionsPojo> getCrmCustInteractionsPojos()
    {
        return crmCustInteractionsPojos;
    }

    public void setCrmCustInteractionsPojos( List<CrmCustInteractionsPojo> crmCustInteractionsPojos )
    {
        this.crmCustInteractionsPojos = crmCustInteractionsPojos;
    }

    public List<CrmCustomerActivityPojo> getCrmCustomerActivityPojos()
    {
        return crmCustomerActivityPojos;
    }

    public void setCrmCustomerActivityPojos( List<CrmCustomerActivityPojo> crmCustomerActivityPojos )
    {
        this.crmCustomerActivityPojos = crmCustomerActivityPojos;
    }

    public QrcTicketReportPojo getQrcTicketReportPojo()
    {
        return qrcTicketReportPojo;
    }

    public void setQrcTicketReportPojo( QrcTicketReportPojo inQrcTicketReportPojo )
    {
        qrcTicketReportPojo = inQrcTicketReportPojo;
    }

    public List<LmsReportPojo> getLmsReportPojos()
    {
        return lmsReportPojos;
    }

    public void setLmsReportPojos( List<LmsReportPojo> inLmsReportPojos )
    {
        lmsReportPojos = inLmsReportPojos;
    }

    public List<RfsReportPojo> getRfsReportPojos()
    {
        return rfsReportPojos;
    }

    public void setRfsReportPojos( List<RfsReportPojo> rfsReportPojos )
    {
        this.rfsReportPojos = rfsReportPojos;
    }

    public List<PaymentReportPojo> getPaymentReportPojos()
    {
        return paymentReportPojos;
    }

    public void setPaymentReportPojos( List<PaymentReportPojo> inPaymentReportPojos )
    {
        paymentReportPojos = inPaymentReportPojos;
    }

    public PaymentReportPojo getPaymentReportPojo()
    {
        return paymentReportPojo;
    }

    public void setPaymentReportPojo( PaymentReportPojo inPaymentReportPojo )
    {
        paymentReportPojo = inPaymentReportPojo;
    }

    public SlaReportPojo getSlaReportPojo()
    {
        return slaReportPojo;
    }

    public void setSlaReportPojo( SlaReportPojo inSlaReportPojo )
    {
        slaReportPojo = inSlaReportPojo;
    }

    public List<SlaReportPojo> getSlaReportPojos()
    {
        return slaReportPojos;
    }

    public void setSlaReportPojos( List<SlaReportPojo> inSlaReportPojos )
    {
        slaReportPojos = inSlaReportPojos;
    }

    public String getStateName()
    {
        return stateName;
    }

    public void setStateName( String stateName )
    {
        this.stateName = stateName;
    }
    
    public String getCityName()
    {
        return cityName;
    }

    public void setCityName( String cityName )
    {
        this.cityName = cityName;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String areaName )
    {
        this.areaName = areaName;
    }

    public String getSocietyName()
    {
        return societyName;
    }

    public void setSocietyName( String societyName )
    {
        this.societyName = societyName;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ReportDto [fromDate=" ).append( fromDate ).append( ", toDate=" ).append( toDate )
                .append( ", serviceType=" ).append( serviceType ).append( ", productType=" ).append( productType )
                .append( ", qrcTicketReportPojo=" ).append( qrcTicketReportPojo ).append( "]" );
        return builder.toString();
    }
}
