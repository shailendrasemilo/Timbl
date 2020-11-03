package com.np.tele.crm.service.client.aspects;

import java.text.DecimalFormat;

import com.np.tele.crm.service.client.CrmPlanMasterPojo;
import com.np.tele.crm.utils.StringUtils;

public aspect CrmPlanMasterAspect
{
    public String CrmPlanMasterPojo.getTotalDULInGB()
    {
        double dul = StringUtils.isValidObj( this.getPrimaryQuota() ) ? this.getPrimaryQuota() : 0;
        dul += StringUtils.isValidObj( this.getSecondaryQuota() ) ? this.getSecondaryQuota() : 0;
        return new DecimalFormat( "#.##" ).format( dul / ( 1024 * 1024 * 1024 ) ) + " GB";
    }

    public String CrmPlanMasterPojo.getTotalDUL()
    {
        double dul = StringUtils.isValidObj( this.getPrimaryQuota() ) ? this.getPrimaryQuota() : 0;
        dul += StringUtils.isValidObj( this.getSecondaryQuota() ) ? this.getSecondaryQuota() : 0;
        return new DecimalFormat( "#.##" ).format( dul / ( 1024 * 1024 * 1024 ) );
    }

    public String CrmPlanMasterPojo.getPrimaryQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getPrimaryQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( this.getPrimaryQuota() / ( 1024 * 1024 * 1024 ) ) + " GB";
        }
        return "0.0 GB";
    }

    public String CrmPlanMasterPojo.getSecondaryQuotaInGB()
    {
        if ( StringUtils.equals( this.getPlanUsageType(), "U" ) )
        {
            return "Unlimited";
        }
        else if ( StringUtils.isValidObj( this.getSecondaryQuota() ) && this.getSecondaryQuota() > 0 )
        {
            return new DecimalFormat( "#.##" ).format( this.getSecondaryQuota() / ( 1024 * 1024 * 1024 ) ) + " GB";
        }
        return "-";
    }

    public int CrmPlanMasterPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getPlanCode() == null ) ? 0 : this.getPlanCode().hashCode() );
        return result;
    }

    public boolean CrmPlanMasterPojo.equals( Object obj )
    {
        if ( this == obj )
        {
            return true;
        }
        if ( obj == null )
        {
            return false;
        }
        if ( getClass() != obj.getClass() )
        {
            return false;
        }
        CrmPlanMasterPojo other = (CrmPlanMasterPojo) obj;
        if ( this.getPlanCode() == null )
        {
            if ( other.getPlanCode() != null )
            {
                return false;
            }
        }
        else if ( !this.getPlanCode().equals( other.getPlanCode() ) )
        {
            return false;
        }
        return true;
    }

    public String CrmPlanMasterPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmPlanMasterPojo [actvAllowedYn=" ).append( this.getActvAllowedYn() )
                .append( ", addonAllowedYn=" ).append( this.getAddonAllowedYn() ).append( ", migrAllowedYn=" )
                .append( this.getMigrAllowedYn() ).append( ", advTelservPayment=" )
                .append( this.getAdvTelservPayment() ).append( ", advTelsolnPayment=" )
                .append( this.getAdvTelsolnPayment() ).append( ", boosterAllowedYn=" )
                .append( this.getBoosterAllowedYn() ).append( ", planCategory=" ).append( this.getPlanCategory() )
                .append( ", planCode=" ).append( this.getPlanCode() ).append( ", planName=" )
                .append( this.getPlanName() ).append( ", planType=" ).append( this.getPlanType() )
                .append( ", planUsageType=" ).append( this.getPlanUsageType() ).append( ", prepaidYn=" )
                .append( this.getPrepaidYn() ).append( ", rentInclTax=" ).append( this.getRentInclTax() )
                .append( ", securityDeposit=" ).append( this.getSecurityDeposit() ).append( ", service=" )
                .append( this.getService() ).append( ", status=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
