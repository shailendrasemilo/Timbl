package com.np.tele.crm.ext.pojos;

import java.util.Date;

import com.np.tele.crm.utils.StringUtils;

public class LmsReportPojo
{
    private String customerName;
    private String callingNumber;
    private String mobileNumber;
    private String business;
    private String state;
    private String city;
    private String area;
    private String locality;
    private String society;
    private String houseNumber;
    private String landmark;
    private String feasibility;
    private String leadSource;
    private String referralSource;
    private String createdBy;
    private Date   createdTime;
    private String lastModifiedBy;
    private Date   lastModifiedTime;
    private String status;
    private String pincode;
    private String emailId;
    private String currentOwner;
    private String modeOfContact;
    private Date   appointmentDate;
    private String appointmentTime;
    private String appointmentRemarks;
    private Date   leadForwardedOn;
    private String reason;
    private String crfNo;
    private String remarks;
    private String latestRemarks;
    private String lmsId;
    private String secondLastRemarks;
    private String thirdOneRemarks;
    private Date   secondLastRemarksTime;
    private Date   thirdOneRemarksTime;
    private Date   latestRemarksTime;

    public String getCustomerName()
    {
        return this.customerName;
    }

    public void setCustomerName( String customerName )
    {
        this.customerName = StringUtils.upperCase( customerName );
    }

    public String getState()
    {
        return this.state;
    }

    public String getCallingNumber()
    {
        return callingNumber;
    }

    public void setCallingNumber( String inCallingNumber )
    {
        callingNumber = inCallingNumber;
    }

    public String getMobileNumber()
    {
        return mobileNumber;
    }

    public void setMobileNumber( String inMobileNumber )
    {
        mobileNumber = inMobileNumber;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getArea()
    {
        return this.area;
    }

    public void setArea( String area )
    {
        this.area = StringUtils.upperCase( area );
    }

    public String getLocality()
    {
        return this.locality;
    }

    public void setLocality( String locality )
    {
        this.locality = StringUtils.upperCase( locality );
    }

    public String getSociety()
    {
        return this.society;
    }

    public void setSociety( String society )
    {
        this.society = StringUtils.upperCase( society );
    }

    public String getHouseNumber()
    {
        return this.houseNumber;
    }

    public void setHouseNumber( String houseNumber )
    {
        this.houseNumber = StringUtils.upperCase( houseNumber );
    }

    public String getLandmark()
    {
        return this.landmark;
    }

    public void setLandmark( String landmark )
    {
        this.landmark = StringUtils.upperCase( landmark );
    }

    public String getFeasibility()
    {
        return this.feasibility;
    }

    public void setFeasibility( String feasibility )
    {
        this.feasibility = feasibility;
    }

    public String getLeadSource()
    {
        return this.leadSource;
    }

    public void setLeadSource( String leadSource )
    {
        this.leadSource = leadSource;
    }

    public String getReferralSource()
    {
        return this.referralSource;
    }

    public void setReferralSource( String referralSource )
    {
        this.referralSource = referralSource;
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
    }

    public String getLastModifiedBy()
    {
        return this.lastModifiedBy;
    }

    public void setLastModifiedBy( String lastModifiedBy )
    {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedTime()
    {
        return this.lastModifiedTime;
    }

    public void setLastModifiedTime( Date inLastModifiedTime )
    {
        lastModifiedTime = inLastModifiedTime;
    }

    public String getStatus()
    {
        return this.status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public String getPincode()
    {
        return pincode;
    }

    public void setPincode( String pincode )
    {
        this.pincode = pincode;
    }

    public String getEmailId()
    {
        return emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    public String getBusiness()
    {
        return business;
    }

    public void setBusiness( String inBusiness )
    {
        business = inBusiness;
    }

    public String getModeOfContact()
    {
        return modeOfContact;
    }

    public void setModeOfContact( String inModeOfContact )
    {
        modeOfContact = inModeOfContact;
    }

    public Date getAppointmentDate()
    {
        return appointmentDate;
    }

    public void setAppointmentDate( Date inAppointmentDate )
    {
        appointmentDate = inAppointmentDate;
    }

    public String getAppointmentTime()
    {
        return appointmentTime;
    }

    public void setAppointmentTime( String inAppointmentTime )
    {
        appointmentTime = inAppointmentTime;
    }

    public String getAppointmentRemarks()
    {
        return appointmentRemarks;
    }

    public void setAppointmentRemarks( String inAppointmentRemarks )
    {
        appointmentRemarks = inAppointmentRemarks;
    }

    public Date getLeadForwardedOn()
    {
        return leadForwardedOn;
    }

    public void setLeadForwardedOn( Date inLeadForwardedOn )
    {
        leadForwardedOn = inLeadForwardedOn;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason( String inReason )
    {
        reason = inReason;
    }

    public String getCrfNo()
    {
        return crfNo;
    }

    public void setCrfNo( String inCrfNo )
    {
        crfNo = inCrfNo;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String inRemarks )
    {
        remarks = inRemarks;
    }

    public String getLatestRemarks()
    {
        return latestRemarks;
    }

    public void setLatestRemarks( String inLatestRemarks )
    {
        latestRemarks = inLatestRemarks;
    }

    public String getCurrentOwner()
    {
        return currentOwner;
    }

    public void setCurrentOwner( String inCurrentOwner )
    {
        currentOwner = inCurrentOwner;
    }

    public String getLmsId()
    {
        return lmsId;
    }

    public void setLmsId( String inLmsId )
    {
        lmsId = inLmsId;
    }

    public String getSecondLastRemarks()
    {
        return secondLastRemarks;
    }

    public void setSecondLastRemarks( String secondLastRemarks )
    {
        this.secondLastRemarks = secondLastRemarks;
    }

    public String getThirdOneRemarks()
    {
        return thirdOneRemarks;
    }

    public void setThirdOneRemarks( String thirdOneRemarks )
    {
        this.thirdOneRemarks = thirdOneRemarks;
    }

    public Date getSecondLastRemarksTime()
    {
        return secondLastRemarksTime;
    }

    public void setSecondLastRemarksTime( Date secondLastRemarksTime )
    {
        this.secondLastRemarksTime = secondLastRemarksTime;
    }

    public Date getThirdOneRemarksTime()
    {
        return thirdOneRemarksTime;
    }

    public void setThirdOneRemarksTime( Date thirdOneRemarksTime )
    {
        this.thirdOneRemarksTime = thirdOneRemarksTime;
    }

    public Date getLatestRemarksTime()
    {
        return latestRemarksTime;
    }

    public void setLatestRemarksTime( Date latestRemarksTime )
    {
        this.latestRemarksTime = latestRemarksTime;
    }
}
