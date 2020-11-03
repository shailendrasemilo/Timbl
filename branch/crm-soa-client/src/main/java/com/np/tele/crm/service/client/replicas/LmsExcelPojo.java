package com.np.tele.crm.service.client.replicas;

public class LmsExcelPojo
{
    private String serialNumber;
    private String customerName;
    private String contactNumber;
    private String state;
    private String city;
    private String area;
    private String locality;
    private String houseNo;
    private String landMark;
    private String service;
    private String remarks;
    private String failReason;
    private int    pincode;
    private String referralSource;

    public String getSerialNumber()
    {
        return serialNumber;
    }

    public void setSerialNumber( String inSerialNumber )
    {
        serialNumber = inSerialNumber;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName( String inCustomerName )
    {
        customerName = inCustomerName;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber( String inContactNumber )
    {
        contactNumber = inContactNumber;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String inState )
    {
        state = inState;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String inCity )
    {
        city = inCity;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String inArea )
    {
        area = inArea;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality( String inLocality )
    {
        locality = inLocality;
    }

    public String getHouseNo()
    {
        return houseNo;
    }

    public void setHouseNo( String inHouseNo )
    {
        houseNo = inHouseNo;
    }

    public String getLandMark()
    {
        return landMark;
    }

    public void setLandMark( String inLandMark )
    {
        landMark = inLandMark;
    }

    public String getService()
    {
        return service;
    }

    public void setService( String inService )
    {
        service = inService;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String inRemarks )
    {
        remarks = inRemarks;
    }

    public String getFailReason()
    {
        return failReason;
    }

    public void setFailReason( String inFailReason )
    {
        failReason = inFailReason;
    }

    public int getPincode()
    {
        return pincode;
    }

    public void setPincode( int pincode )
    {
        this.pincode = pincode;
    }

    public String getReferralSource()
    {
        return referralSource;
    }

    public void setReferralSource( String referralSource )
    {
        this.referralSource = referralSource;
    }
}
