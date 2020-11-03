package com.np.tele.crm.config.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.np.tele.crm.service.client.CrmBillCyclePojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.RemarksPojo;

public class CRMLogForm
    extends ActionForm
{
    private RemarksPojo                remarksPojo;
    private List<RemarksPojo>          remarksPojoList;
    private List<CrmTicketHistoryPojo> ticketHistoryList = null;
    private String                     mappingId;
    private String                     param;
    private List<CrmBillCyclePojo>     crmBillCyclePojosList;
    private long                       customerRecordID;
    private String                     customerId;

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String inCustomerId )
    {
        customerId = inCustomerId;
    }

    public long getCustomerRecordID()
    {
        return customerRecordID;
    }

    public void setCustomerRecordID( long inCustomerRecordID )
    {
        customerRecordID = inCustomerRecordID;
    }

    public List<CrmBillCyclePojo> getCrmBillCyclePojosList()
    {
        return crmBillCyclePojosList;
    }

    public void setCrmBillCyclePojosList( List<CrmBillCyclePojo> inCrmBillCyclePojosList )
    {
        crmBillCyclePojosList = inCrmBillCyclePojosList;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
    }

    public List<RemarksPojo> getRemarksPojoList()
    {
        return remarksPojoList;
    }

    public void setRemarksPojoList( List<RemarksPojo> inRemarksPojoList )
    {
        remarksPojoList = inRemarksPojoList;
    }

    public List<CrmTicketHistoryPojo> getTicketHistoryList()
    {
        return ticketHistoryList;
    }

    public void setTicketHistoryList( List<CrmTicketHistoryPojo> inTicketHistoryList )
    {
        ticketHistoryList = inTicketHistoryList;
    }

    public String getMappingId()
    {
        return mappingId;
    }

    public void setMappingId( String inMappingId )
    {
        mappingId = inMappingId;
    }

    public String getParam()
    {
        return param;
    }

    public void setParam( String inParam )
    {
        param = inParam;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CRMLogForm [remarksPojo=" ).append( remarksPojo ).append( ", remarksPojoList=" )
                .append( remarksPojoList ).append( ", ticketHistoryList=" ).append( ticketHistoryList )
                .append( ", mappingId=" ).append( mappingId ).append( ", param=" ).append( param )
                .append( ", crmBillCyclePojosList=" ).append( crmBillCyclePojosList ).append( ", customerRecordID=" )
                .append( customerRecordID ).append( ", customerId=" ).append( customerId ).append( "]" );
        return builder.toString();
    }
}
