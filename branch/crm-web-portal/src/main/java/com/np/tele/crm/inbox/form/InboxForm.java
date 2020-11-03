package com.np.tele.crm.inbox.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.AllInboxPojo;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.CommonWorkflowPojo;
import com.np.tele.crm.service.client.CrmAuditLogPojo;
import com.np.tele.crm.service.client.CrmCustomerDetailsPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.utils.StringUtils;
import com.np.validator.util.CommonValidator;

public class InboxForm
    extends ActionForm
{
    private List<LmsPojo>                lmsInboxList          = null;
    private List<LmsPojo>                lmsGroupInboxList     = null;
    private String                       leadId                = null;
    private long                         lmsId;
    private LmsPojo                      lmsPojo;
    private List<AppointmentPojo>        appointmentPojos;
    private List<RemarksPojo>            remarksPojos;
    private String                       inboxOwner;
    private List<ContentPojo>            products              = null;
    private List<ContentPojo>            appointmentModes;
    private List<ContentPojo>            appointmentTimings;
    private String                       userFunctionBin       = null;
    private List<ContentPojo>            performingActions;
    private List<CrmRcaReasonPojo>       closeReasons;
    private String                       inboxType             = null;
    private List<CrmCustomerDetailsPojo> crfInboxList          = null;
    private List<CrmCustomerDetailsPojo> crfGroupInboxList     = null;
    private long                         recordId;
    private String                       crfId;
    private String                       ticketId;
    private List<CrmAuditLogPojo>        crmAuditLogPojos;
    private List<CrmSrTicketsPojo>       qrcInboxList          = null;
    private List<CrmSrTicketsPojo>       qrcGroupInboxList     = null;
    private List<AllInboxPojo>           allInboxList          = null;
    private List<AllInboxPojo>           allGroupInboxList     = null;
    private long                         rowID;
    private String                       elementID;
    private String                       currentStage;
    private String                       binChangeInbox;
    private List<CrmSrTicketsPojo>       crmSrTicketsPojo;
    private List<CommonWorkflowPojo>     workflowInboxList;
    private List<CommonWorkflowPojo>     workflowGroupInboxList;
    private String                       searchCriteria        = null;
    private String                       searchCriteriaValue   = null;
    private List<ContentPojo>            inboxSearchList       = null;
    private String                       searchMethod          = null;
    private int[]                        selfIds;
    private int[]                        groupIds;
    private List<AllInboxPojo>           tempAllInboxList      = null;
    private List<AllInboxPojo>           tempAllGroupInboxList = null;
    private List<CrmCustomerDetailsPojo> freezeInboxList       = null;
    private List<CrmCustomerDetailsPojo> freezeGroupInboxList  = null;

    public List<ContentPojo> getInboxSearchList()
    {
        return inboxSearchList;
    }

    public void setInboxSearchList( List<ContentPojo> inInboxSearchList )
    {
        this.inboxSearchList = inInboxSearchList;
    }

    public String getSearchCriteria()
    {
        return searchCriteria;
    }

    public void setSearchCriteria( String inSearchCriteria )
    {
        this.searchCriteria = inSearchCriteria;
    }

    public String getSearchCriteriaValue()
    {
        return searchCriteriaValue;
    }

    public void setSearchCriteriaValue( String inSearchCriteriaValue )
    {
        this.searchCriteriaValue = inSearchCriteriaValue;
    }

    public synchronized List<CommonWorkflowPojo> getWorkflowInboxList()
    {
        return workflowInboxList;
    }

    public synchronized void setWorkflowInboxList( List<CommonWorkflowPojo> workflowInboxList )
    {
        this.workflowInboxList = workflowInboxList;
    }

    public synchronized List<CommonWorkflowPojo> getWorkflowGroupInboxList()
    {
        return workflowGroupInboxList;
    }

    public synchronized void setWorkflowGroupInboxList( List<CommonWorkflowPojo> workflowGroupInboxList )
    {
        this.workflowGroupInboxList = workflowGroupInboxList;
    }

    public synchronized List<CrmSrTicketsPojo> getCrmSrTicketsPojo()
    {
        return crmSrTicketsPojo;
    }

    public synchronized void setCrmSrTicketsPojo( List<CrmSrTicketsPojo> inCrmSrTicketsPojo )
    {
        crmSrTicketsPojo = inCrmSrTicketsPojo;
    }

    public String getBinChangeInbox()
    {
        return binChangeInbox;
    }

    public void setBinChangeInbox( String inBinChangeInbox )
    {
        binChangeInbox = inBinChangeInbox;
    }

    public String getCurrentStage()
    {
        return currentStage;
    }

    public void setCurrentStage( String inCurrentStage )
    {
        currentStage = inCurrentStage;
    }

    public long getRowID()
    {
        return rowID;
    }

    public void setRowID( long inRowID )
    {
        rowID = inRowID;
    }

    public String getElementID()
    {
        return elementID;
    }

    public void setElementID( String inElementID )
    {
        elementID = inElementID;
    }

    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId( String inTicketId )
    {
        ticketId = inTicketId;
    }

    /**
     * @return the qrcInboxList
     */
    public synchronized List<CrmSrTicketsPojo> getQrcInboxList()
    {
        return qrcInboxList;
    }

    /**
     * @param inQrcInboxList the qrcInboxList to set
     */
    public synchronized void setQrcInboxList( List<CrmSrTicketsPojo> inQrcInboxList )
    {
        qrcInboxList = inQrcInboxList;
    }

    /**
     * @return the qrcGroupInboxList
     */
    public synchronized List<CrmSrTicketsPojo> getQrcGroupInboxList()
    {
        return qrcGroupInboxList;
    }

    /**
     * @param inQrcGroupInboxList the qrcGroupInboxList to set
     */
    public synchronized void setQrcGroupInboxList( List<CrmSrTicketsPojo> inQrcGroupInboxList )
    {
        qrcGroupInboxList = inQrcGroupInboxList;
    }

    /**
     * @return the crmAuditLogPojos
     */
    public List<CrmAuditLogPojo> getCrmAuditLogPojos()
    {
        return crmAuditLogPojos;
    }

    /**
     * @param inCrmAuditLogPojos the crmAuditLogPojos to set
     */
    public void setCrmAuditLogPojos( List<CrmAuditLogPojo> inCrmAuditLogPojos )
    {
        crmAuditLogPojos = inCrmAuditLogPojos;
    }

    public String getInboxType()
    {
        return inboxType;
    }

    public void setInboxType( String inboxType )
    {
        this.inboxType = inboxType;
    }

    public synchronized List<CrmCustomerDetailsPojo> getCrfInboxList()
    {
        return crfInboxList;
    }

    public synchronized void setCrfInboxList( List<CrmCustomerDetailsPojo> crfInboxList )
    {
        this.crfInboxList = crfInboxList;
    }

    public synchronized List<CrmCustomerDetailsPojo> getCrfGroupInboxList()
    {
        return crfGroupInboxList;
    }

    public synchronized void setCrfGroupInboxList( List<CrmCustomerDetailsPojo> crfGroupInboxList )
    {
        this.crfGroupInboxList = crfGroupInboxList;
    }

    /**
     * @return the performingActions
     */
    public List<ContentPojo> getPerformingActions()
    {
        return performingActions;
    }

    /**
     * @param inPerformingActions the performingActions to set
     */
    public void setPerformingActions( List<ContentPojo> inPerformingActions )
    {
        performingActions = inPerformingActions;
    }

    /**
     * @return the closeReasons
     */
    public List<CrmRcaReasonPojo> getCloseReasons()
    {
        return closeReasons;
    }

    /**
     * @param inCloseReasons the closeReasons to set
     */
    public void setCloseReasons( List<CrmRcaReasonPojo> inCloseReasons )
    {
        closeReasons = inCloseReasons;
    }

    public String getUserFunctionBin()
    {
        return userFunctionBin;
    }

    public void setUserFunctionBin( String inUserFunctionBin )
    {
        userFunctionBin = inUserFunctionBin;
    }

    public List<ContentPojo> getAppointmentModes()
    {
        return appointmentModes;
    }

    public void setAppointmentModes( List<ContentPojo> inAppointmentModes )
    {
        appointmentModes = inAppointmentModes;
    }

    public List<ContentPojo> getAppointmentTimings()
    {
        return appointmentTimings;
    }

    public void setAppointmentTimings( List<ContentPojo> inAppointmentTimings )
    {
        appointmentTimings = inAppointmentTimings;
    }

    public List<ContentPojo> getProducts()
    {
        return products;
    }

    public void setProducts( List<ContentPojo> inProducts )
    {
        products = inProducts;
    }

    public String getInboxOwner()
    {
        return inboxOwner;
    }

    public void setInboxOwner( String inInboxOwner )
    {
        inboxOwner = inInboxOwner;
    }

    public List<AppointmentPojo> getAppointmentPojos()
    {
        return appointmentPojos;
    }

    public void setAppointmentPojos( List<AppointmentPojo> inAppointmentPojos )
    {
        appointmentPojos = inAppointmentPojos;
    }

    public List<RemarksPojo> getRemarksPojos()
    {
        return remarksPojos;
    }

    public void setRemarksPojos( List<RemarksPojo> inRemarksPojos )
    {
        remarksPojos = inRemarksPojos;
    }

    public LmsPojo getLmsPojo()
    {
        return lmsPojo;
    }

    public void setLmsPojo( LmsPojo inLmsPojo )
    {
        lmsPojo = inLmsPojo;
    }

    public long getLmsId()
    {
        return lmsId;
    }

    public void setLmsId( long inLmsId )
    {
        lmsId = inLmsId;
    }

    public String getLeadId()
    {
        return leadId;
    }

    public void setLeadId( String inLeadId )
    {
        leadId = inLeadId;
    }

    public synchronized List<LmsPojo> getLmsInboxList()
    {
        return lmsInboxList;
    }

    public synchronized void setLmsInboxList( List<LmsPojo> inLmsInboxList )
    {
        lmsInboxList = inLmsInboxList;
    }

    public synchronized List<LmsPojo> getLmsGroupInboxList()
    {
        return lmsGroupInboxList;
    }

    public synchronized void setLmsGroupInboxList( List<LmsPojo> inLmsGroupInboxList )
    {
        lmsGroupInboxList = inLmsGroupInboxList;
    }

    public long getRecordId()
    {
        return recordId;
    }

    public void setRecordId( long inRecordId )
    {
        recordId = inRecordId;
    }

    public String getCrfId()
    {
        return crfId;
    }

    public void setCrfId( String inCrfId )
    {
        crfId = inCrfId;
    }

    public synchronized List<AllInboxPojo> getAllInboxList()
    {
        return allInboxList;
    }

    public synchronized void setAllInboxList( List<AllInboxPojo> inAllInboxList )
    {
        allInboxList = inAllInboxList;
    }

    public synchronized List<AllInboxPojo> getAllGroupInboxList()
    {
        return allGroupInboxList;
    }

    public synchronized void setAllGroupInboxList( List<AllInboxPojo> inAllGroupInboxList )
    {
        allGroupInboxList = inAllGroupInboxList;
    }

    public String getSearchMethod()
    {
        return searchMethod;
    }

    public void setSearchMethod( String inSearchMethod )
    {
        searchMethod = inSearchMethod;
    }

    public int[] getSelfIds()
    {
        return selfIds;
    }

    public void setSelfIds( int[] inSelfIds )
    {
        selfIds = inSelfIds;
    }

    public int[] getGroupIds()
    {
        return groupIds;
    }

    public void setGroupIds( int[] inGroupIds )
    {
        groupIds = inGroupIds;
    }

    public List<AllInboxPojo> getTempAllInboxList()
    {
        return tempAllInboxList;
    }

    public void setTempAllInboxList( List<AllInboxPojo> inTempAllInboxList )
    {
        tempAllInboxList = inTempAllInboxList;
    }

    public List<AllInboxPojo> getTempAllGroupInboxList()
    {
        return tempAllGroupInboxList;
    }

    public void setTempAllGroupInboxList( List<AllInboxPojo> inTempAllGroupInboxList )
    {
        tempAllGroupInboxList = inTempAllGroupInboxList;
    }

    public List<CrmCustomerDetailsPojo> getFreezeInboxList()
    {
        return freezeInboxList;
    }

    public synchronized void setFreezeInboxList( List<CrmCustomerDetailsPojo> inFreezeInboxList )
    {
        freezeInboxList = inFreezeInboxList;
    }

    public synchronized List<CrmCustomerDetailsPojo> getFreezeGroupInboxList()
    {
        return freezeGroupInboxList;
    }

    public synchronized void setFreezeGroupInboxList( List<CrmCustomerDetailsPojo> inFreezeGroupInboxList )
    {
        freezeGroupInboxList = inFreezeGroupInboxList;
    }

    @Override
    public void reset( ActionMapping mapping, HttpServletRequest request )
    {
        String method = request.getParameter( IAppConstants.PARAMETER_NAME );
        if ( StringUtils.equals( method, IAppConstants.METHOD_INBOX_WORKFLOW ) )
        {
            if ( CommonValidator.isValidCollection( this.getWorkflowInboxList() ) )
            {
                this.workflowInboxList.clear();
            }
            if ( CommonValidator.isValidCollection( this.getWorkflowGroupInboxList() ) )
            {
                this.workflowGroupInboxList.clear();
            }
        }
        else if ( StringUtils.equals( method, IAppConstants.METHOD_CHANGE_INBOX_BIN ) )
        {
            if ( CommonValidator.isValidCollection( this.getWorkflowInboxList() ) )
            {
                this.workflowInboxList.clear();
            }
            if ( CommonValidator.isValidCollection( this.getWorkflowGroupInboxList() ) )
            {
                this.workflowGroupInboxList.clear();
            }
        }
        if ( !StringUtils.equals( method, getSearchMethod() ) )
        {
            setSearchCriteria( null );
            setSearchCriteriaValue( null );
        }
        if ( StringUtils.equals( method, "allInbox" ) )
        {
            String reset = request.getParameter( "rs" );
            if ( StringUtils.equals( reset, "true" ) )
            {
                setSearchCriteria( null );
                setSearchCriteriaValue( null );
            }
        }
        super.reset( mapping, request );
    }
}
