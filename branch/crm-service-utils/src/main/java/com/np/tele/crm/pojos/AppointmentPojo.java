package com.np.tele.crm.pojos;

import java.util.Date;

import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.StringUtils;

/**
 * Appointment generated by hbm2java
 */
public class AppointmentPojo
    implements java.io.Serializable
{
    private long   appointmentId;
    private String modeOfContact;
    private Date   appointmentDate;
    private String appointmentTime;
    private String appointmentAddress;
    private String remarks;
    private Long   contactNumber;
    private String mappingId;
    private String createdBy;
    private Date   createdTime;
    private String displayDate;
    private String displayCreatedTime;
    private String stage;
    private String appointmentType;
    private String requestType;
    private String appointmentDay;

    public AppointmentPojo()
    {
    }

    public AppointmentPojo( long appointmentId,
                            String modeOfContact,
                            Date appointmentDate,
                            String appointmentTime,
                            String mappingId,
                            String createdBy )
    {
        this.appointmentId = appointmentId;
        this.modeOfContact = modeOfContact;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.mappingId = mappingId;
        this.createdBy = createdBy;
    }

    public AppointmentPojo( long appointmentId,
                            String modeOfContact,
                            Date appointmentDate,
                            String appointmentTime,
                            String appointmentAddress,
                            String remarks,
                            Long contactNumber,
                            String mappingId,
                            String createdBy,
                            Date createdTime )
    {
        this.appointmentId = appointmentId;
        this.modeOfContact = modeOfContact;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentAddress = appointmentAddress;
        this.remarks = remarks;
        this.contactNumber = contactNumber;
        this.mappingId = mappingId;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public String getStage()
    {
        return stage;
    }

    public void setStage( String inStage )
    {
        stage = inStage;
    }

    public long getAppointmentId()
    {
        return this.appointmentId;
    }

    public void setAppointmentId( long appointmentId )
    {
        this.appointmentId = appointmentId;
    }

    public String getModeOfContact()
    {
        return this.modeOfContact;
    }

    public void setModeOfContact( String modeOfContact )
    {
        this.modeOfContact = modeOfContact;
    }

    public Date getAppointmentDate()
    {
        return this.appointmentDate;
    }

    public void setAppointmentDate( Date inAppointmentDate )
    {
        appointmentDate = inAppointmentDate;
       /* if ( StringUtils.isEmpty( displayDate ) && StringUtils.isValidObj( appointmentDate ) )
        {
            displayDate = IDateConstants.SDF_DD_MMM_YYYY.format( appointmentDate );
        }*/
    }

    public String getAppointmentTime()
    {
        return this.appointmentTime;
    }

    public void setAppointmentTime( String appointmentTime )
    {
        this.appointmentTime = appointmentTime;
    }

    public String getAppointmentAddress()
    {
        return this.appointmentAddress;
    }

    public void setAppointmentAddress( String appointmentAddress )
    {
        this.appointmentAddress = appointmentAddress;
    }

    public String getRemarks()
    {
        return this.remarks;
    }

    public void setRemarks( String remarks )
    {
        this.remarks = remarks;
    }

    public Long getContactNumber()
    {
        return this.contactNumber;
    }

    public void setContactNumber( Long contactNumber )
    {
        this.contactNumber = contactNumber;
    }

    public String getMappingId()
    {
        return this.mappingId;
    }

    public void setMappingId( String mappingId )
    {
        this.mappingId = mappingId;
    }

    public String getCreatedBy()
    {
        return this.createdBy;
    }

    public void setCreatedBy( String createdBy )
    {
        this.createdBy = createdBy;
    }

    public Date getCreatedTime()
    {
        return this.createdTime;
    }

    public void setCreatedTime( Date inCreatedTime )
    {
        createdTime = inCreatedTime;
       /* if ( StringUtils.isEmpty( displayCreatedTime ) && StringUtils.isValidObj( createdTime ) )
        {
            displayCreatedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( createdTime );
        }*/
    }

    public String getDisplayDate()
    {
        return displayDate;
    }

    public void setDisplayDate( String inDisplayDate )
    {
        displayDate = inDisplayDate;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public String getAppointmentType()
    {
        return appointmentType;
    }

    public void setAppointmentType( String inAppointmentType )
    {
        appointmentType = inAppointmentType;
    }

    public String getRequestType()
    {
        return requestType;
    }

    public void setRequestType( String inRequestType )
    {
        requestType = inRequestType;
    }

    public String getAppointmentDay()
    {
        return appointmentDay;
    }

    public void setAppointmentDay( String inAppointmentDay )
    {
        appointmentDay = inAppointmentDay;
    }
}