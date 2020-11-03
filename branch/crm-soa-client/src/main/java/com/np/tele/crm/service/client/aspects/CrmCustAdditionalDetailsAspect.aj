package com.np.tele.crm.service.client.aspects;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.constants.IDateConstants;
import com.np.tele.crm.service.client.CrmCustAdditionalDetails;
import com.np.tele.crm.utils.StringUtils;

public aspect CrmCustAdditionalDetailsAspect
{
    public String CrmCustAdditionalDetails.getPrimaryQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getPrimaryQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( this.getPrimaryQuota().doubleValue() / ( 1024 * 1024 * 1024 ) )
                    + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getPrimaryUsedQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getPrimaryUsedQuota() ) )
        {
            return new DecimalFormat( "#.##" )
                    .format( this.getPrimaryUsedQuota().doubleValue() / ( 1024 * 1024 * 1024 ) ) + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getPrimaryRemainQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getPrimaryRemainQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( this.getPrimaryRemainQuota().doubleValue()
                    / ( 1024 * 1024 * 1024 ) )
                    + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getSecondaryQuotaInGB()
    {
        if ( StringUtils.isNumeric( this.getSecondaryQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( StringUtils.numericValue( this.getSecondaryQuota() )
                    .doubleValue() / ( 1024 * 1024 * 1024 ) )
                    + " GB";
        }
        return this.getSecondaryQuota();
    }

    public String CrmCustAdditionalDetails.getSecondaryUsedQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getSecondaryUsedQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( this.getSecondaryUsedQuota().doubleValue()
                    / ( 1024 * 1024 * 1024 ) )
                    + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getSecondaryRemainQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getSecondaryRemainQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( this.getSecondaryRemainQuota().doubleValue()
                    / ( 1024 * 1024 * 1024 ) )
                    + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getPrimaryUsagePercentage()
    {
        if ( StringUtils.isValidObj( this.getPrimaryUsedQuota() ) )
        {
            if ( this.getPrimaryQuota().doubleValue() == 0 )
            {
                return " - ";
            }
            else if ( this.getPrimaryQuota().doubleValue() > 0 )
            {
                return new DecimalFormat( "#.##" ).format( ( this.getPrimaryUsedQuota().doubleValue() / this
                        .getPrimaryQuota().doubleValue() ) * 100 ) + "%";
            }
            else
            {
                return new DecimalFormat( "#.##" ).format( this.getPrimaryUsedQuota().doubleValue() * 100 ) + "%";
            }
        }
        return "0 %";
    }

    public String CrmCustAdditionalDetails.getUsedVolumeQuotaInGB()
    {
        if ( StringUtils.isValidObj( this.getUsedVolumeQuota() ) )
        {
            return new DecimalFormat( "#.##" ).format( this.getUsedVolumeQuota().doubleValue() / 1024 / 1024 / 1024 )
                    + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getAdditionalAvailableDULAtPrimarySpeed()
    {
        if ( StringUtils.isValidObj( this.getPrimaryQuota() ) && StringUtils.isValidObj( this.getPrimaryUsedQuota() )
                && ( this.getPrimaryQuota().doubleValue() - this.getPrimaryUsedQuota().doubleValue() > 0 ) )
        {
            return new DecimalFormat( "#.##" ).format( ( this.getPrimaryQuota().doubleValue() - this
                    .getPrimaryUsedQuota().doubleValue() ) / 1024 / 1024 / 1024 ) + " GB";
        }
        return "0.0 GB";
    }

    public String CrmCustAdditionalDetails.getNextChargeDate()
    {
        if ( StringUtils.isValidObj( this.getExpiryDate() ) )
        {
            Calendar gc = this.getExpiryDate().toGregorianCalendar();
            gc.add( GregorianCalendar.DATE, 1 );
            return IDateConstants.SDF_DD_MMM_YYYY.format( gc.getTime() );
        }
        return IAppConstants.DASH;
    }

    public boolean CrmCustAdditionalDetails.isExpired()
    {
        if ( StringUtils.isValidObj( this.getExpiryDate() ) )
        {
            Calendar gc = this.getExpiryDate().toGregorianCalendar();
            return gc.getTime().before( Calendar.getInstance().getTime() );
        }
        return false;
    }

    public String CrmCustAdditionalDetails.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCustAdditionalDetails [allocatedVolumeQuota=" ).append( this.getAllocatedVolumeQuota() )
                .append( ", balance=" ).append( this.getBalance() ).append( ", currentBandwidth=" )
                .append( this.getCurrentBandwidth() ).append( ", expiryDate=" ).append( this.getExpiryDate() )
                .append( ", primaryQuota=" ).append( this.getPrimaryQuota() ).append( ", primaryRemainQuota=" )
                .append( this.getPrimaryRemainQuota() ).append( ", primaryUsedQuota=" )
                .append( this.getPrimaryUsedQuota() ).append( ", remainVolumeQuota=" )
                .append( this.getRemainVolumeQuota() ).append( ", secondaryQuota=" ).append( this.getSecondaryQuota() )
                .append( ", secondaryRemainQuota=" ).append( this.getSecondaryRemainQuota() )
                .append( ", secondaryUsedQuota=" ).append( this.getSecondaryUsedQuota() ).append( ", usedVolumeQuota=" )
                .append( this.getUsedVolumeQuota() ).append( "]" );
        return builder.toString();
    }
}
