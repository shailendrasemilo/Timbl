package com.np.lms.rest.dto;

import com.np.tele.crm.pojos.AppointmentPojo;
import com.np.tele.crm.pojos.LMSPojo;
import com.np.tele.crm.pojos.RemarksPojo;

public class LeadRequestDto
{
    private String          operation;
    private String          serviceParam;
    private LMSPojo         leadPojo;
    private RemarksPojo     remarksPojo;
    private String          clientIPAddress = null;
    private String          serverIPAddress = null;
    private String          functionBin;
    private String          userId;
    private String          action;
    private AppointmentPojo appointmentPojo;

    public String getUserId()
    {
        return userId;
    }

    public void setUserId( String inUserId )
    {
        userId = inUserId;
    }

    public String getOperation()
    {
        return operation;
    }

    public void setOperation( String inOperation )
    {
        operation = inOperation;
    }

    public String getServiceParam()
    {
        return serviceParam;
    }

    public void setServiceParam( String inServiceParam )
    {
        serviceParam = inServiceParam;
    }

    public LMSPojo getLeadPojo()
    {
        return leadPojo;
    }

    public void setLeadPojo( LMSPojo inLeadPojo )
    {
        leadPojo = inLeadPojo;
    }

    public RemarksPojo getRemarksPojo()
    {
        return remarksPojo;
    }

    public void setRemarksPojo( RemarksPojo inRemarksPojo )
    {
        remarksPojo = inRemarksPojo;
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

    public String getFunctionBin()
    {
        return functionBin;
    }

    public void setFunctionBin( String inFunctionBin )
    {
        functionBin = inFunctionBin;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction( String inAction )
    {
        action = inAction;
    }

    public AppointmentPojo getAppointmentPojo()
    {
        return appointmentPojo;
    }

    public void setAppointmentPojo( AppointmentPojo inAppointmentPojo )
    {
        appointmentPojo = inAppointmentPojo;
    }
}
