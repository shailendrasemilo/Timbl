package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmCmsPaymentPojo;
import com.np.tele.crm.service.client.CrmPodDetailsPojo;

public aspect CrmPodDetailsPojoAspect
{
    public int CrmPodDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) ( this.getPodId() ^ ( this.getPodId() >>> 32 ) );
        return result;
    }

    public boolean CrmPodDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmPodDetailsPojo other = (CrmPodDetailsPojo) obj;
        if ( this.getPodId() != other.getPodId() )
            return false;
        return true;
    }
    
    public String CrmPodDetailsPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmPodDetailsPojo [contactNumber=" ).append( getContactNumber() ).append( ", createdBy=" )
                .append( getCreatedBy() ).append( ", createdTime=" ).append( getCreatedTime() ).append( ", customerId=" )
                .append( getCustomerId() ).append( ", customerRelation=" ).append( getCustomerRelation() )
                .append( ", deliveredDate=" ).append( getDeliveredDate() ).append( ", lastModifiedBy=" )
                .append( getLastModifiedBy() ).append( ", lastModifiedTime=" ).append( getLastModifiedTime() )
                .append( ", podId=" ).append( getPodId() ).append( ", receiverName=" ).append( getReceiverName() )
                .append( ", status=" ).append( getStatus() ).append( "]" );
        return builder.toString();
    }
    
}
