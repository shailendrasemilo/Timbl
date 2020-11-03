package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmPlanDetailsPojo;

public aspect CrmPlanDetailsAspect
{
    public int CrmPlanDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getAddOnPlanCode() == null ) ? 0 : this.getAddOnPlanCode().hashCode() );
        result = prime * result + ( ( this.getBasePlanCode() == null ) ? 0 : this.getBasePlanCode().hashCode() );
        result = prime * result + ( ( this.getBillMode() == null ) ? 0 : this.getBillMode().hashCode() );
        result = prime * result
                + ( ( this.getPreferredBillLang() == null ) ? 0 : this.getPreferredBillLang().hashCode() );
        result = prime * result + ( ( this.getPromoCode() == null ) ? 0 : this.getPromoCode().hashCode() );
        result = prime * result + (int) ( this.getRecordId() ^ ( this.getRecordId() >>> 32 ) );
        result = prime * result + ( ( this.getUsageType() == null ) ? 0 : this.getUsageType().hashCode() );
        return result;
    }

    public boolean CrmPlanDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmPlanDetailsPojo other = (CrmPlanDetailsPojo) obj;
        if ( this.getAddOnPlanCode() == null )
        {
            if ( other.getAddOnPlanCode() != null )
                return false;
        }
        else if ( !this.getAddOnPlanCode().equals( other.getAddOnPlanCode() ) )
            return false;
        if ( this.getBasePlanCode() == null )
        {
            if ( other.getBasePlanCode() != null )
                return false;
        }
        else if ( !this.getBasePlanCode().equals( other.getBasePlanCode() ) )
            return false;
        if ( this.getBillMode() == null )
        {
            if ( other.getBillMode() != null )
                return false;
        }
        else if ( !this.getBillMode().equals( other.getBillMode() ) )
            return false;
        if ( this.getPreferredBillLang() == null )
        {
            if ( other.getPreferredBillLang() != null )
                return false;
        }
        else if ( !this.getPreferredBillLang().equals( other.getPreferredBillLang() ) )
            return false;
        if ( this.getPromoCode() == null )
        {
            if ( other.getPromoCode() != null )
                return false;
        }
        else if ( !this.getPromoCode().equals( other.getPromoCode() ) )
            return false;
        if ( this.getRecordId() != other.getRecordId() )
            return false;
        if ( this.getUsageType() == null )
        {
            if ( other.getUsageType() != null )
                return false;
        }
        else if ( !this.getUsageType().equals( other.getUsageType() ) )
            return false;
        return true;
    }
}
