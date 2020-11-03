package com.np.tele.crm.gis.pojo;

import com.np.tele.crm.service.client.SocietyPojo;

/**
 * @author mohammad.faisal
 *
 */
public class GISUploadPojo
{
    private long        partnerId;
    private long        areaId;
    private String      pincode;
    private String      secPincode;
    private String      country;
    private String      state;
    private String      city;
    private String      area;
    private String      locality;
    private String      society;
    private String      networkPartner;
    private String      networkType;     // , separated string
    private String      connectableHomes;
    private String      rfsDus;
    private String      longitude;
    private String      latitude;
    private String      customerCategory;
    private String      typeOfField;
    private SocietyPojo societyPojo;

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long partnerId )
    {
        this.partnerId = partnerId;
    }

    public long getAreaId()
    {
        return areaId;
    }

    public void setAreaId( long areaId )
    {
        this.areaId = areaId;
    }

    public String getPincode()
    {
        return pincode;
    }

    public void setPincode( String pincode )
    {
        this.pincode = pincode;
    }

    public String getSecPincode()
    {
        return secPincode;
    }

    public void setSecPincode( String secPincode )
    {
        this.secPincode = secPincode;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry( String country )
    {
        this.country = country;
    }

    public String getState()
    {
        return state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getArea()
    {
        return area;
    }

    public void setArea( String area )
    {
        this.area = area;
    }

    public String getLocality()
    {
        return locality;
    }

    public void setLocality( String locality )
    {
        this.locality = locality;
    }

    public String getSociety()
    {
        return society;
    }

    public void setSociety( String society )
    {
        this.society = society;
    }

    public String getNetworkPartner()
    {
        return networkPartner;
    }

    public void setNetworkPartner( String networkPartner )
    {
        this.networkPartner = networkPartner;
    }

    public String getNetworkType()
    {
        return networkType;
    }

    public void setNetworkType( String networkType )
    {
        this.networkType = networkType;
    }

    public String getConnectableHomes()
    {
        return connectableHomes;
    }

    public void setConnectableHomes( String connectableHomes )
    {
        this.connectableHomes = connectableHomes;
    }

    public String getRfsDus()
    {
        return rfsDus;
    }

    public void setRfsDus( String rfsDus )
    {
        this.rfsDus = rfsDus;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude( String longitude )
    {
        this.longitude = longitude;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude( String latitude )
    {
        this.latitude = latitude;
    }

    public String getCustomerCategory()
    {
        return customerCategory;
    }

    public void setCustomerCategory( String customerCategory )
    {
        this.customerCategory = customerCategory;
    }

    public String getTypeOfField()
    {
        return typeOfField;
    }

    public void setTypeOfField( String typeOfField )
    {
        this.typeOfField = typeOfField;
    }

    public SocietyPojo getSocietyPojo()
    {
        return societyPojo;
    }

    public void setSocietyPojo( SocietyPojo societyPojo )
    {
        this.societyPojo = societyPojo;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "GISUploadPojo [pincode=" ).append( pincode ).append( ", secPincode=" ).append( secPincode )
                .append( ", country=" ).append( country ).append( ", city=" ).append( city ).append( ", area=" )
                .append( area ).append( ", locality=" ).append( locality ).append( ", society=" ).append( society )
                .append( ", networkPartner=" ).append( networkPartner ).append( ", networkType=" ).append( networkType )
                .append( ", connectableHomes=" ).append( connectableHomes ).append( ", rfsDus=" ).append( rfsDus )
                .append( ", longitude=" ).append( longitude ).append( ", latitude=" ).append( latitude )
                .append( ", customerCategory=" ).append( customerCategory ).append( ", typeOfField=" )
                .append( typeOfField ).append( "]" );
        return builder.toString();
    }
}
