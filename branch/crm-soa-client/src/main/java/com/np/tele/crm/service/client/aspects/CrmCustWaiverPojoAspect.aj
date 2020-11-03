package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmCustWaiverPojo;

public aspect CrmCustWaiverPojoAspect
{
    public String  CrmCustWaiverPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCustWaiverPojo [billNo=" ).append( this.getBillNo() ).append( ", customerId=" ).append( this.getCustomerId() )
                .append( ", status=" ).append( this.getStatus() ).append( ", waiverAmount=" ).append(this.getWaiverAmount() )
                .append( ", waiverHead=" ).append( this.getWaiverHead() ).append( ", waiverId=" ).append(this.getWaiverId() )
                .append( ", waiverType=" ).append(this.getWaiverType() ).append( ", workflowId=" ).append(this.getWorkflowId() )
                .append( ", workflowStage=" ).append(this.getWorkflowStage()
                                                      ).append( "]" );
        return builder.toString();
    }

    public int CrmCustWaiverPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getWorkflowId() == null ) ? 0 : this.getWorkflowId().hashCode() );
        return result;
    }

    public boolean CrmCustWaiverPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCustWaiverPojo other = (CrmCustWaiverPojo) obj;
        if ( this.getWorkflowId() == null )
        {
            if ( other.getWorkflowId() != null )
                return false;
        }
        else if ( !this.getWorkflowId().equals( other.getWorkflowId() ) )
            return false;
        return true;
    }
}
