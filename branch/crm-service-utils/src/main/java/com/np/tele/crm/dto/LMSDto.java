package com.np.tele.crm.dto;

import java.util.List;

import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.LmsCrfMappingPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class LMSDto
{
    private String                  statusCode;
    private String                  statusDesc;
    private String                  clientIPAddress = null;
    private String                  serverIPAddress = null;
    private AppointmentPojo         appointmentPojo;
    private RemarksPojo             remarksPojo;
    private LMSPojo                 leadPojo;
    private List<AppointmentPojo>   appointmentPojos;
    private List<RemarksPojo>       remarksPojos;
    private List<LMSPojo>           leadPojos;
    private List<LMSPojo>           rejectedPojos;
    private List<LMSPojo>           successInsertPojos;
    private String                  toStage;
    private String                  toUserId;
    private List<LmsCrfMappingPojo> crfMappingPojos;
    private LmsCrfMappingPojo       lmsCrfMappingPojo;
    private List<String>            userAssociatedServices;
    private List<String>            userAssociatedPartners;

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

    /**
     * @return the lmsCrfMappingPojo
     */
    public LmsCrfMappingPojo getLmsCrfMappingPojo()
    {
        return lmsCrfMappingPojo;
    }

    /**
     * @param inLmsCrfMappingPojo the lmsCrfMappingPojo to set
     */
    public void setLmsCrfMappingPojo( LmsCrfMappingPojo inLmsCrfMappingPojo )
    {
        lmsCrfMappingPojo = inLmsCrfMappingPojo;
    }

    public List<LmsCrfMappingPojo> getCrfMappingPojos()
    {
        return crfMappingPojos;
    }

    public void setCrfMappingPojos( List<LmsCrfMappingPojo> crfMappingPojos )
    {
        this.crfMappingPojos = crfMappingPojos;
    }

    public String getToUserId()
    {
        return toUserId;
    }

    public void setToUserId( String toUserId )
    {
        this.toUserId = toUserId;
    }

    public String getToStage()
    {
        return toStage;
    }

    public void setToStage( String toStage )
    {
        this.toStage = toStage;
    }

    public List<LMSPojo> getRejectedPojos()
    {
        return rejectedPojos;
    }

    public void setRejectedPojos( List<LMSPojo> rejectedPojos )
    {
        this.rejectedPojos = rejectedPojos;
    }

    public AppointmentPojo getAppointmentPojo()
    {
        return appointmentPojo;
    }

    public void setAppointmentPojo( AppointmentPojo appointmentPojo )
    {
        this.appointmentPojo = appointmentPojo;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo remarksPojo )
    {
        this.remarksPojo = remarksPojo;
    }

    public LMSPojo getLeadPojo()
    {
        return leadPojo;
    }

    public void setLeadPojo( LMSPojo leadPojo )
    {
        this.leadPojo = leadPojo;
    }

    public List<AppointmentPojo> getAppointmentPojos()
    {
        return appointmentPojos;
    }

    public void setAppointmentPojos( List<AppointmentPojo> appointmentPojos )
    {
        this.appointmentPojos = appointmentPojos;
    }

    public List<RemarksPojo> getRemarksPojos()
    {
        return remarksPojos;
    }

    public void setRemarksPojos( List<RemarksPojo> remarksPojos )
    {
        this.remarksPojos = remarksPojos;
    }

    public List<LMSPojo> getLeadPojos()
    {
        return leadPojos;
    }

    public void setLeadPojos( List<LMSPojo> leadPojos )
    {
        this.leadPojos = leadPojos;
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

    public List<LMSPojo> getSuccessInsertPojos()
    {
        return successInsertPojos;
    }

    public void setSuccessInsertPojos( List<LMSPojo> inSuccessInsertPojos )
    {
        successInsertPojos = inSuccessInsertPojos;
    }
}
