package com.np.tele.crm.lms.form;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.pojos.ContentPojo;
import com.np.tele.crm.service.client.AppointmentPojo;
import com.np.tele.crm.service.client.CityPojo;
import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.service.client.CrmSrTicketsPojo;
import com.np.tele.crm.service.client.CrmTicketHistoryPojo;
import com.np.tele.crm.service.client.LmsPojo;
import com.np.tele.crm.service.client.PartnerPojo;
import com.np.tele.crm.service.client.RemarksPojo;
import com.np.tele.crm.service.client.StatePojo;

public class LmsForm
    extends ActionForm
{
    private LmsPojo                lmsPojo;
    private RemarksPojo            remarksPojo;
    private AppointmentPojo        appointmentPojo;
    private List<LmsPojo>          lmsPojoList;
    private FormFile               formFile;
    private List<ContentPojo>      products;
    private List<StatePojo>        statePojoList;
    private List<CityPojo>         cityPojoList;
    private List<AppointmentPojo>  appointmentPojoList;
    private List<RemarksPojo>      remarksPojoList;
    private List<CrmRcaReasonPojo> closeReasons;
    private List<ContentPojo>      appointmentModes;
    private List<ContentPojo>      appointmentTimings;
    private List<ContentPojo>      performingActions;
    private List<ContentPojo>      performingActionsAll;
    private int                    stageIndex;
    private List<String>           mappedUsers;
    private String                 toUser;
    private String                 crfIds;
    private List<CrmRcaReasonPojo> crmReferralSources;
    private boolean                showAppointmentDiv;
    private boolean                nonEditableAtSC;
    private List<PartnerPojo>      partnerList;
    private String                 parameter;
    private String                 inaModule;
    private CrmSrTicketsPojo       srTicketPojo;
    private CrmTicketHistoryPojo   ticketHistory;
    private List<ContentPojo>      ticketActions;
    private List<CrmSrTicketsPojo> srTicketsPojos;

    public List<PartnerPojo> getPartnerList()
    {
        return partnerList;
    }

    public void setPartnerList( List<PartnerPojo> inPartnerList )
    {
        partnerList = inPartnerList;
    }

    /**
     * @return the performingActionsAll
     */
    public List<ContentPojo> getPerformingActionsAll()
    {
        return performingActionsAll;
    }

    /**
     * @param inPerformingActionsAll the performingActionsAll to set
     */
    public void setPerformingActionsAll( List<ContentPojo> inPerformingActionsAll )
    {
        performingActionsAll = inPerformingActionsAll;
    }

    public boolean isNonEditableAtSC()
    {
        return nonEditableAtSC;
    }

    public void setNonEditableAtSC( boolean inNonEditableAtSC )
    {
        nonEditableAtSC = inNonEditableAtSC;
    }

    public boolean isShowAppointmentDiv()
    {
        return showAppointmentDiv;
    }

    public void setShowAppointmentDiv( boolean inShowAppointmentDiv )
    {
        showAppointmentDiv = inShowAppointmentDiv;
    }

    public List<CrmRcaReasonPojo> getCrmReferralSources()
    {
        return crmReferralSources;
    }

    public void setCrmReferralSources( List<CrmRcaReasonPojo> inCrmReferralSources )
    {
        crmReferralSources = inCrmReferralSources;
    }

    public String getCrfIds()
    {
        return crfIds;
    }

    public void setCrfIds( String inCrfIds )
    {
        crfIds = inCrfIds;
    }

    public String getToUser()
    {
        return toUser;
    }

    public void setToUser( String inToUser )
    {
        toUser = inToUser;
    }

    public List<String> getMappedUsers()
    {
        return mappedUsers;
    }

    public void setMappedUsers( List<String> inMappedUsers )
    {
        mappedUsers = inMappedUsers;
    }

    public int getStageIndex()
    {
        return stageIndex;
    }

    public void setStageIndex( int inStageIndex )
    {
        stageIndex = inStageIndex;
    }

    public List<ContentPojo> getPerformingActions()
    {
        return performingActions;
    }

    public void setPerformingActions( List<ContentPojo> inPerformingActions )
    {
        performingActions = inPerformingActions;
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

    public FormFile getFormFile()
    {
        return formFile;
    }

    public void setFormFile( FormFile inFormFile )
    {
        formFile = inFormFile;
    }

    public LmsPojo getLmsPojo()
    {
        return lmsPojo;
    }

    public void setLmsPojo( LmsPojo lmsPojo )
    {
        this.lmsPojo = lmsPojo;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo remarksPojo )
    {
        this.remarksPojo = remarksPojo;
    }

    public AppointmentPojo getAppointmentPojo()
    {
        return appointmentPojo;
    }

    public void setAppointmentPojo( AppointmentPojo appointmentPojo )
    {
        this.appointmentPojo = appointmentPojo;
    }

    public List<LmsPojo> getLmsPojoList()
    {
        return lmsPojoList;
    }

    public void setLmsPojoList( List<LmsPojo> lmsPojoList )
    {
        this.lmsPojoList = lmsPojoList;
    }

    public List<StatePojo> getStatePojoList()
    {
        return statePojoList;
    }

    public void setStatePojoList( List<StatePojo> inStatePojoList )
    {
        statePojoList = inStatePojoList;
    }

    public List<CityPojo> getCityPojoList()
    {
        return cityPojoList;
    }

    public void setCityPojoList( List<CityPojo> inCityPojoList )
    {
        cityPojoList = inCityPojoList;
    }

    @Override
    public void reset( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String methodName = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        LmsFormHelper.resetLMSForm( this, methodName );
    }

    /*@Override
    public ActionErrors validate( ActionMapping inMapping, HttpServletRequest inRequest )
    {
        String method = inRequest.getParameter( IAppConstants.PARAMETER_NAME );
        ActionErrors actionError = new ActionErrors();
        LmsFormHelper.validateLmsForm( method, actionError, this );
        return actionError;
    }*/
    public List<ContentPojo> getProducts()
    {
        return products;
    }

    public void setProducts( List<ContentPojo> products )
    {
        this.products = products;
    }

    public List<AppointmentPojo> getAppointmentPojoList()
    {
        return appointmentPojoList;
    }

    public void setAppointmentPojoList( List<AppointmentPojo> appointmentPojoList )
    {
        this.appointmentPojoList = appointmentPojoList;
    }

    public List<RemarksPojo> getRemarksPojoList()
    {
        return remarksPojoList;
    }

    public void setRemarksPojoList( List<RemarksPojo> remarksPojoList )
    {
        this.remarksPojoList = remarksPojoList;
    }

    public String getParameter()
    {
        return parameter;
    }

    public void setParameter( String inParameter )
    {
        parameter = inParameter;
    }

    public String getInaModule()
    {
        return inaModule;
    }

    public void setInaModule( String inInaModule )
    {
        inaModule = inInaModule;
    }

    public CrmSrTicketsPojo getSrTicketPojo()
    {
        return srTicketPojo;
    }

    public void setSrTicketPojo( CrmSrTicketsPojo inSrTicketPojo )
    {
        srTicketPojo = inSrTicketPojo;
    }

    public CrmTicketHistoryPojo getTicketHistory()
    {
        return ticketHistory;
    }

    public void setTicketHistory( CrmTicketHistoryPojo inTicketHistory )
    {
        ticketHistory = inTicketHistory;
    }

    public List<ContentPojo> getTicketActions()
    {
        return ticketActions;
    }

    public void setTicketActions( List<ContentPojo> inTicketActions )
    {
        ticketActions = inTicketActions;
    }

    public List<CrmSrTicketsPojo> getSrTicketsPojos()
    {
        return srTicketsPojos;
    }

    public void setSrTicketsPojos( List<CrmSrTicketsPojo> inSrTicketsPojos )
    {
        srTicketsPojos = inSrTicketsPojos;
    }
}
