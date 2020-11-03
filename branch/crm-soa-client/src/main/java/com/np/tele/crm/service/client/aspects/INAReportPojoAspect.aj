package com.np.tele.crm.service.client.aspects;

import java.util.Date;

import com.np.tele.crm.service.client.InaReportPojo;
import com.np.tele.crm.utils.DateUtils;
import com.np.tele.crm.utils.StringUtils;

public aspect INAReportPojoAspect
{
    public String InaReportPojo.getCRFEntrySLADay()
    {
        if ( StringUtils.isValidObj( this.getCreatedTime() ) && StringUtils.isValidObj( this.getCrfDate() ) )
        {
            Date crfDate = DateUtils.convertXmlCalToDate( this.getCrfDate() );
            crfDate.setHours( 0 );
            crfDate.setMinutes( 0 );
            crfDate.setSeconds( 0 );
            Date createdTime = DateUtils.convertXmlCalToDate( this.getCreatedTime() );
            createdTime.setHours( 0 );
            createdTime.setMinutes( 0 );
            createdTime.setSeconds( 0 );
            return String.valueOf( DateUtils.daysDiff( crfDate, createdTime ) );
        }
        return "";
    }

    public String InaReportPojo.getCRFToISRSLA()
    {
        if ( StringUtils.isValidObj( this.getIsrDate() ) && StringUtils.isValidObj( this.getCrfDate() ) )
        {
            Date IsrDate = DateUtils.convertXmlCalToDate( this.getIsrDate() );
            IsrDate.setHours( 0 );
            IsrDate.setMinutes( 0 );
            IsrDate.setSeconds( 0 );
            Date crfDate = DateUtils.convertXmlCalToDate( this.getCrfDate() );
            crfDate.setHours( 0 );
            crfDate.setMinutes( 0 );
            crfDate.setSeconds( 0 );
            return String.valueOf( DateUtils.daysDiff( crfDate, IsrDate ) );
        }
        return "";
    }

    public String InaReportPojo.getWOToISRSLA()
    {
        if ( StringUtils.isValidObj( this.getIsrDate() ) && StringUtils.isValidObj( this.getFtApprovalDate() ) )
        {
            Date IsrDate = DateUtils.convertXmlCalToDate( this.getIsrDate() );
            IsrDate.setHours( 0 );
            IsrDate.setMinutes( 0 );
            IsrDate.setSeconds( 0 );
            Date ftApprovalDate = DateUtils.convertXmlCalToDate( this.getFtApprovalDate() );
            ftApprovalDate.setHours( 0 );
            ftApprovalDate.setMinutes( 0 );
            ftApprovalDate.setSeconds( 0 );
            return String.valueOf( DateUtils.daysDiff( ftApprovalDate, IsrDate ) );
        }
        return "";
    }

    public String InaReportPojo.getFtinoutsla()
    {
        if ( StringUtils.isValidObj( this.getFtApprovalDate() ) && StringUtils.isValidObj( this.getFtSubmittionDate() ) )
        {
            Date ftApprovalDate = DateUtils.convertXmlCalToDate( this.getFtApprovalDate() );
            ftApprovalDate.setHours( 0 );
            ftApprovalDate.setMinutes( 0 );
            ftApprovalDate.setSeconds( 0 );
            Date ftSubmittionDate = DateUtils.convertXmlCalToDate( this.getFtSubmittionDate() );
            ftSubmittionDate.setHours( 0 );
            ftSubmittionDate.setMinutes( 0 );
            ftSubmittionDate.setSeconds( 0 );
            return String.valueOf( DateUtils.daysDiff( ftSubmittionDate, ftApprovalDate ) );
        }
        return "";
    }

    public String InaReportPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "INAReportPojo [crfId=" ).append( this.getCrfId() ).append( ", customerId=" )
                .append( this.getCustomerId() ).append( ", crfDate=" ).append( this.getCrfDate() )
                .append( ", connectionType=" ).append( this.getConnectionType() ).append( ", rmn=" )
                .append( this.getRmn() ).append( ", createdTime=" ).append( this.getCreatedTime() )
                .append( ", ftApprovalDate=" ).append( this.getFtApprovalDate() ).append( ", product=" )
                .append( this.getProduct() ).append( ", serviceType=" ).append( this.getServiceType() )
                .append( ", ftSubmittionDate=" ).append( this.getFtSubmittionDate() ).append( ", isrDate=" )
                .append( this.getIsrDate() ).append( "]" );
        return builder.toString();
    }
}
