package com.np.tele.crm.pojos;

// Generated 5 Jul, 2013 11:15:37 AM by Hibernate Tools 3.4.0.CR1
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.utils.StringUtils;

/**
 * Partner generated by hbm2java
 */
public class PartnerPojo
    implements java.io.Serializable
{
    private long                              partnerId;
    private String                            partnerName;
    private String                            partnerCen;
    private String                            designation;
    private long                              mobileNo;
    private String                            emailId;
    private String                            relatedDept;
    private String                            hoCpn;
    private String                            hoEmailId;
    private long                              hoMobileNo;
    private String                            fax;
    private String                            address;
    private String                            city;
    private String                            state;
    private int                               pincode;
    private String                            addedErp;
    private String                            partnerErpCode;
    private String                            createdBy;
    private Date                              createdTime;
    private String                            lastModifiedBy;
    private Date                              lastModifiedTime;
    private String                            currentStatus;
    private long                              phoneNo;
    private String                            bussinessType;
    private String                            partnerType;
    private Date                              boardedDate;
    private String                            partnerAlias;
    private double                            commissionPercent;
    private String                            option82;
    private String                            strBoardedDate;
    private String                            crmPartnerCode;
    private String                            displayCreatedTime;
    private String                            displayLastModifiedTime;
    private String                            partnerShortName;
    private Set<CrmPartnerDetailsPojo>        crmPartnerDetailses = new HashSet<CrmPartnerDetailsPojo>( 0 );
    private boolean                           editable;
    private List<StatePojo>                   statePojos;
    private List<CrmPartnerNetworkConfigPojo> partnerNetworkConfigPojos;

    /*private Set    crmUsers = new HashSet( 0 );*/
    /*private Set        societyNetworkPartners = new HashSet( 0 );*/
    public PartnerPojo()
    {
    }

    public PartnerPojo( Long partnerId,
                        String partnerName,
                        String partnerCen,
                        String designation,
                        long mobileNo,
                        String emailId,
                        String relatedDept,
                        String hoCpn,
                        String hoEmailId,
                        long hoMobileNo,
                        String createdBy,
                        String lastModifiedBy,
                        Date lastModifiedTime )
    {
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.partnerCen = partnerCen;
        this.designation = designation;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.relatedDept = relatedDept;
        this.hoCpn = hoCpn;
        this.hoEmailId = hoEmailId;
        this.hoMobileNo = hoMobileNo;
        this.createdBy = createdBy;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
    }

    public PartnerPojo( Long partnerId,
                        String partnerName,
                        String partnerCen,
                        String designation,
                        long mobileNo,
                        String emailId,
                        String relatedDept,
                        String hoCpn,
                        String hoEmailId,
                        long hoMobileNo,
                        String fax,
                        String address,
                        String city,
                        String state,
                        int pincode,
                        String addedErp,
                        String partnerErpCode,
                        String createdBy,
                        Date createdTime,
                        String lastModifiedBy,
                        Date lastModifiedTime,
                        String currentStatus,
                        Set crmUsers )
    {
        this.partnerId = partnerId;
        this.partnerName = partnerName;
        this.partnerCen = partnerCen;
        this.designation = designation;
        this.mobileNo = mobileNo;
        this.emailId = emailId;
        this.relatedDept = relatedDept;
        this.hoCpn = hoCpn;
        this.hoEmailId = hoEmailId;
        this.hoMobileNo = hoMobileNo;
        this.fax = fax;
        this.address = address;
        this.city = city;
        this.state = state;
        this.pincode = pincode;
        this.addedErp = addedErp;
        this.partnerErpCode = partnerErpCode;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedTime = lastModifiedTime;
        this.currentStatus = currentStatus;
    }

    public String getPartnerShortName()
    {
        return partnerShortName;
    }

    public void setPartnerShortName( String partnerShortName )
    {
        this.partnerShortName = partnerShortName;
    }

    public String getPartnerName()
    {
        return this.partnerName;
    }

    public void setPartnerName( String partnerName )
    {
        this.partnerName = partnerName;
    }

    public String getPartnerCen()
    {
        return this.partnerCen;
    }

    public void setPartnerCen( String partnerCen )
    {
        this.partnerCen = StringUtils.upperCase( partnerCen );
    }

    public String getDesignation()
    {
        return this.designation;
    }

    public void setDesignation( String designation )
    {
        this.designation = StringUtils.upperCase( designation );
    }

    public long getMobileNo()
    {
        return this.mobileNo;
    }

    public void setMobileNo( long mobileNo )
    {
        this.mobileNo = mobileNo;
    }

    public String getEmailId()
    {
        return this.emailId;
    }

    public void setEmailId( String emailId )
    {
        this.emailId = emailId;
    }

    public String getRelatedDept()
    {
        return relatedDept;
    }

    public void setRelatedDept( String inRelatedDept )
    {
        relatedDept = inRelatedDept;
    }

    public String getHoCpn()
    {
        return this.hoCpn;
    }

    public void setHoCpn( String hoCpn )
    {
        this.hoCpn = StringUtils.upperCase( hoCpn );
    }

    public String getHoEmailId()
    {
        return this.hoEmailId;
    }

    public void setHoEmailId( String hoEmailId )
    {
        this.hoEmailId = hoEmailId;
    }

    public long getHoMobileNo()
    {
        return this.hoMobileNo;
    }

    public void setHoMobileNo( long hoMobileNo )
    {
        this.hoMobileNo = hoMobileNo;
    }

    public String getFax()
    {
        return this.fax;
    }

    public void setFax( String fax )
    {
        this.fax = fax;
    }

    public String getAddress()
    {
        return this.address;
    }

    public void setAddress( String address )
    {
        this.address = StringUtils.upperCase( address );
    }

    public String getCity()
    {
        return this.city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getState()
    {
        return this.state;
    }

    public void setState( String state )
    {
        this.state = state;
    }

    public String getAddedErp()
    {
        return this.addedErp;
    }

    public void setAddedErp( String addedErp )
    {
        this.addedErp = addedErp;
    }

    public String getPartnerErpCode()
    {
        return this.partnerErpCode;
    }

    public void setPartnerErpCode( String partnerErpCode )
    {
        this.partnerErpCode = partnerErpCode;
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
        //        if ( StringUtils.isEmpty( displayLastModifiedTime ) && StringUtils.isValidObj( lastModifiedTime ) )
        //        {
        //            displayLastModifiedTime = IDateConstants.SDF_DD_MMM_YYYY_HH_MM_SS.format( lastModifiedTime );
        //        }
    }

    public String getCurrentStatus()
    {
        return this.currentStatus;
    }

    public void setCurrentStatus( String currentStatus )
    {
        this.currentStatus = currentStatus;
    }

    public long getPhoneNo()
    {
        return phoneNo;
    }

    public void setPhoneNo( long inPhoneNo )
    {
        phoneNo = inPhoneNo;
    }

    public String getBussinessType()
    {
        return bussinessType;
    }

    public void setBussinessType( String inBussinessType )
    {
        bussinessType = inBussinessType;
    }

    public String getPartnerType()
    {
        return partnerType;
    }

    public void setPartnerType( String inPartnerType )
    {
        partnerType = inPartnerType;
    }

    public Date getBoardedDate()
    {
        return boardedDate;
    }

    public void setBoardedDate( Date inBoardedDate )
    {
        boardedDate = inBoardedDate;
        if ( StringUtils.isEmpty( strBoardedDate ) && StringUtils.isValidObj( boardedDate ) )
        {
            strBoardedDate = IDateConstants.SDF_DD_MMM_YYYY.format( boardedDate );
        }
    }

    public String getPartnerAlias()
    {
        return partnerAlias;
    }

    public void setPartnerAlias( String partnerAlias )
    {
        this.partnerAlias = partnerAlias;
    }

    public String getOption82()
    {
        return option82;
    }

    public void setOption82( String option82 )
    {
        this.option82 = option82;
    }

    public String getStrBoardedDate()
    {
        return strBoardedDate;
    }

    public void setStrBoardedDate( String inStrBoardedDate )
    {
        strBoardedDate = inStrBoardedDate;
    }

    public String getCrmPartnerCode()
    {
        return crmPartnerCode;
    }

    public void setCrmPartnerCode( String inCrmPartnerCode )
    {
        crmPartnerCode = inCrmPartnerCode;
    }

    public String getDisplayCreatedTime()
    {
        return displayCreatedTime;
    }

    public void setDisplayCreatedTime( String inDisplayCreatedTime )
    {
        displayCreatedTime = inDisplayCreatedTime;
    }

    public String getDisplayLastModifiedTime()
    {
        return displayLastModifiedTime;
    }

    public void setDisplayLastModifiedTime( String inDisplayLastModifiedTime )
    {
        displayLastModifiedTime = inDisplayLastModifiedTime;
    }

    public Set<CrmPartnerDetailsPojo> getCrmPartnerDetailses()
    {
        return crmPartnerDetailses;
    }

    public void setCrmPartnerDetailses( Set<CrmPartnerDetailsPojo> inCrmPartnerDetailses )
    {
        crmPartnerDetailses = inCrmPartnerDetailses;
    }

    public boolean isEditable()
    {
        return editable;
    }

    public void setEditable( boolean editable )
    {
        this.editable = editable;
    }

    public List<StatePojo> getStatePojos()
    {
        return statePojos;
    }

    public void setStatePojos( List<StatePojo> statePojos )
    {
        this.statePojos = statePojos;
    }

    public List<CrmPartnerNetworkConfigPojo> getPartnerNetworkConfigPojos()
    {
        return partnerNetworkConfigPojos;
    }

    public void setPartnerNetworkConfigPojos( List<CrmPartnerNetworkConfigPojo> partnerNetworkConfigPojos )
    {
        this.partnerNetworkConfigPojos = partnerNetworkConfigPojos;
    }

    public long getPartnerId()
    {
        return partnerId;
    }

    public void setPartnerId( long partnerId )
    {
        this.partnerId = partnerId;
    }

    public int getPincode()
    {
        return pincode;
    }

    public void setPincode( int pincode )
    {
        this.pincode = pincode;
    }

    public double getCommissionPercent()
    {
        return commissionPercent;
    }

    public void setCommissionPercent( double commissionPercent )
    {
        this.commissionPercent = commissionPercent;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( partnerId ^ ( partnerId >>> 32 ) );
        return result;
    }

    @Override
    public boolean equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        PartnerPojo other = (PartnerPojo) obj;
        if ( partnerId != other.partnerId )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "PartnerPojo [partnerId=" ).append( partnerId ).append( ", partnerName=" ).append( partnerName )
                .append( ", partnerCen=" ).append( partnerCen ).append( ", designation=" ).append( designation )
                .append( ", mobileNo=" ).append( mobileNo ).append( ", emailId=" ).append( emailId )
                .append( ", relatedDept=" ).append( relatedDept ).append( ", hoCpn=" ).append( hoCpn )
                .append( ", hoEmailId=" ).append( hoEmailId ).append( ", hoMobileNo=" ).append( hoMobileNo )
                .append( ", fax=" ).append( fax ).append( ", address=" ).append( address ).append( ", city=" )
                .append( city ).append( ", state=" ).append( state ).append( ", pincode=" ).append( pincode )
                .append( ", addedErp=" ).append( addedErp ).append( ", partnerErpCode=" ).append( partnerErpCode )
                .append( ", createdBy=" ).append( createdBy ).append( ", createdTime=" ).append( createdTime )
                .append( ", lastModifiedBy=" ).append( lastModifiedBy ).append( ", lastModifiedTime=" )
                .append( lastModifiedTime ).append( ", currentStatus=" ).append( currentStatus ).append( "]" );
        return builder.toString();
    }
}
