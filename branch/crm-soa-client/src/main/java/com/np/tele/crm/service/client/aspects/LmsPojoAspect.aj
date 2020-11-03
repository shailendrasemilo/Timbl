package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.LmsPojo;

public aspect LmsPojoAspect
{
    public String LmsPojo.getLmsColor()
    {
        return "color_" + this.getFeasibility();
    }

    public int LmsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getLmsId() ^ ( this.getLmsId() >>> 32 ) );
        return result;
    }

    public boolean LmsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        LmsPojo other = (LmsPojo) obj;
        if ( this.getLmsId() != other.getLmsId() )
            return false;
        return true;
    }

    public String LmsPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "LmsPojo [area=" ).append( this.getArea() ).append( ", city=" ).append( this.getCity() )
                .append( ", contactNumber=" ).append( this.getContactNumber() ).append( ", customerName=" )
                .append( this.getCustomerName() ).append( ", emailId=" ).append( this.getEmailId() )
                .append( ", feasibility=" ).append( this.getFeasibility() ).append( ", houseNumber=" )
                .append( this.getHouseNumber() ).append( ", inboxId=" ).append( this.getInboxId() )
                .append( ", landmark=" ).append( this.getLandmark() ).append( ", leadId=" ).append( this.getLeadId() )
                .append( ", leadSource=" ).append( this.getLeadSource() ).append( ", lmsId=" ).append( this.getLmsId() )
                .append( ", lmsStage=" ).append( this.getLmsStage() ).append( ", locality=" )
                .append( this.getLocality() ).append( ", pincode=" ).append( this.getPincode() ).append( ", product=" )
                .append( this.getProduct() ).append( ", referralSource=" ).append( this.getReferralSource() )
                .append( ", state=" ).append( this.getState() ).append( ", status=" ).append( this.getStatus() )
                .append( "]" );
        return builder.toString();
    }
}
