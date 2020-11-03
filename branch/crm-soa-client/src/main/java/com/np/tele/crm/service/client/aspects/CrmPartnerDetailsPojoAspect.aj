package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmPartnerDetailsPojo;

public aspect CrmPartnerDetailsPojoAspect
{
    public String CrmPartnerDetailsPojo.toString()
    {
        return "CrmPartnerDetailsPojo [bussinessType=" + this.getBussinessType() + ", createdBy=" + this.getCreatedBy()
                + ", createdTime=" + this.getCreatedTime() + ", lastModifiedBy=" + this.getLastModifiedBy()
                + ", lastModifiedTime=" + this.getLastModifiedTime() + ", partnerType=" + this.getPartnerType()
                + ", recordId=" + this.getRecordId() + ", status=" + this.getStatus() + "]";
    }

    public int CrmPartnerDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getBussinessType() == null ) ? 0 : this.getBussinessType().hashCode() );
        result = prime * result + ( ( this.getPartnerType() == null ) ? 0 : this.getPartnerType().hashCode() );
        return result;
    }

    public boolean CrmPartnerDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmPartnerDetailsPojo other = (CrmPartnerDetailsPojo) obj;
        if ( this.getBussinessType() == null )
        {
            if ( other.getBussinessType() != null )
                return false;
        }
        else if ( !this.getBussinessType().equals( other.getBussinessType() ) )
            return false;
        if ( this.getPartnerType() == null )
        {
            if ( other.getPartnerType() != null )
                return false;
        }
        else if ( !this.getPartnerType().equals( other.getPartnerType() ) )
            return false;
        return true;
    }
}
