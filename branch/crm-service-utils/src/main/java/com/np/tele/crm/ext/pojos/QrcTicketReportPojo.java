package com.np.tele.crm.ext.pojos;

import java.math.BigInteger;
import java.util.Date;

import com.np.tele.crm.constants.IAppConstants;
import com.np.tele.crm.utils.StringUtils;

public class QrcTicketReportPojo
{
    private String     crfId;
    private String     customerId;
    private String     ticketId;
    private String     custFname;
    private String     custLname;
    private String     rmn;
    private String     rtn;
    private String     status;
    private String     option82;
    private String     qrcType;
    private String     category;
    private String     subCategory;
    private String     subSubCategory;
    private String     currentBin;
    private Date       inboxCreatedTime;
    private String     inboxUserId;
    private Date       inboxLastmodifiedTime;
    private String     inboxCreatedBy;
    private String     totalPendingHours;
    private String     pendingCurrentBinHours;
    private String     pendingCurrentUserHours;
    private String     slab;
    private String     ticketStatus;
    private Date       raisedDate;
    private String     raiseUser;
    private Date       srResolvedOn;
    private Date       srReopenedOn;
    private String     resolvedBy;
    private String     resolutionCode;
    private String     rca;
    private String     attributedTo;
    private String     fieldVisit;
    private String     binName;
    private String     resolutionTime;
    private Date       createdTime;
    private String     callingNo;
    private String     LMOName;
    private String     installationAddress;
    private String     basePlanName;
    private String     addonPlanName;
    private Date       lastModifiedTime;
    private String     reason;
    private Date       activationDate;
    private String     areaName;
    private String     product;
    private String     serviceType;
    private String     remarks;
    private String     latestRemarks;
    private String     action;
    private String     extendedDays;
    private Long       slabTime;
    private String     firstAssignUser;
    private String     totDurAssignTime;
    private String     totDurRemarkTime;
    private BigInteger reopenCount;
    private String     repeatTicketId;
    private String     resolutionType;
    private String     slaStatus;
    private String     configuredSLA;
    private String     statusOfTicket;
    private Double     securityDeposit;
    private String     flagRemarks;

    public String getFlagRemarks()
    {
        return flagRemarks;
    }

    public void setFlagRemarks( String flagRemarks )
    {
        this.flagRemarks = flagRemarks;
    }

    public Double getSecurityDeposit()
    {
        return securityDeposit;
    }

    public void setSecurityDeposit( Double securityDeposit )
    {
        this.securityDeposit = securityDeposit;
    }

    public String getCrfId()
    {
        return crfId;
    }

    public void setCrfId( String crfId )
    {
        this.crfId = crfId;
    }

    public String getCustomerId()
    {
        return customerId;
    }

    public void setCustomerId( String customerId )
    {
        this.customerId = customerId;
    }

    public String getCustFname()
    {
        return custFname;
    }

    public void setCustFname( String custFname )
    {
        this.custFname = custFname;
    }

    public String getCustLname()
    {
        return custLname;
    }

    public void setCustLname( String custLname )
    {
        this.custLname = custLname;
    }

    public String getRmn()
    {
        return rmn;
    }

    public void setRmn( String rmn )
    {
        this.rmn = rmn;
    }

    public String getRtn()
    {
        return rtn;
    }

    public void setRtn( String rtn )
    {
        this.rtn = rtn;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus( String status )
    {
        this.status = status;
    }

    public Date getCreatedTime()
    {
        return createdTime;
    }

    public void setCreatedTime( Date createdTime )
    {
        this.createdTime = createdTime;
    }

    public String getLMOName()
    {
        return LMOName;
    }

    public void setLMOName( String lMOName )
    {
        LMOName = lMOName;
    }

    public String getInstallationAddress()
    {
        return installationAddress;
    }

    public void setInstallationAddress( String installationAddress )
    {
        this.installationAddress = installationAddress;
    }

    public String getBasePlanName()
    {
        return basePlanName;
    }

    public void setBasePlanName( String basePlanName )
    {
        this.basePlanName = basePlanName;
    }

    public String getAddonPlanName()
    {
        return addonPlanName;
    }

    public void setAddonPlanName( String addonPlanName )
    {
        this.addonPlanName = addonPlanName;
    }

    public Date getLastModifiedTime()
    {
        return lastModifiedTime;
    }

    public void setLastModifiedTime( Date lastModifiedTime )
    {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason( String reason )
    {
        this.reason = reason;
    }

    public Date getActivationDate()
    {
        return activationDate;
    }

    public void setActivationDate( Date activationDate )
    {
        this.activationDate = activationDate;
    }

    public String getAreaName()
    {
        return areaName;
    }

    public void setAreaName( String areaName )
    {
        this.areaName = areaName;
    }

    public String getProduct()
    {
        return product;
    }

    public void setProduct( String product )
    {
        this.product = product;
    }

    public String getServiceType()
    {
        return serviceType;
    }

    public void setServiceType( String serviceType )
    {
        this.serviceType = serviceType;
    }

    public String getRemarks()
    {
        return remarks;
    }

    public void setRemarks( String remarks )
    {
        if ( StringUtils.contains( remarks, IAppConstants.SPACE ) )
        {
            this.remarks = remarks.trim().replaceAll( "\\s+", IAppConstants.SPACE );
        }
        else
        {
            this.remarks = remarks;
        }
    }

    public String getTicketId()
    {
        return ticketId;
    }

    public void setTicketId( String ticketId )
    {
        this.ticketId = ticketId;
    }

    public String getOption82()
    {
        return option82;
    }

    public void setOption82( String option82 )
    {
        this.option82 = option82;
    }

    public String getQrcType()
    {
        return qrcType;
    }

    public void setQrcType( String qrcType )
    {
        this.qrcType = qrcType;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory( String category )
    {
        this.category = category;
    }

    public String getSubCategory()
    {
        return subCategory;
    }

    public void setSubCategory( String subCategory )
    {
        this.subCategory = subCategory;
    }

    public String getSubSubCategory()
    {
        return subSubCategory;
    }

    public void setSubSubCategory( String subSubCategory )
    {
        this.subSubCategory = subSubCategory;
    }

    public String getCurrentBin()
    {
        return currentBin;
    }

    public void setCurrentBin( String currentBin )
    {
        this.currentBin = currentBin;
    }

    public Date getInboxCreatedTime()
    {
        return inboxCreatedTime;
    }

    public void setInboxCreatedTime( Date inboxCreatedTime )
    {
        this.inboxCreatedTime = inboxCreatedTime;
    }

    public String getInboxUserId()
    {
        return inboxUserId;
    }

    public void setInboxUserId( String inboxUserId )
    {
        this.inboxUserId = inboxUserId;
    }

    public Date getInboxLastmodifiedTime()
    {
        return inboxLastmodifiedTime;
    }

    public void setInboxLastmodifiedTime( Date inboxLastmodifiedTime )
    {
        this.inboxLastmodifiedTime = inboxLastmodifiedTime;
    }

    public String getInboxCreatedBy()
    {
        return inboxCreatedBy;
    }

    public void setInboxCreatedBy( String inboxCreatedBy )
    {
        this.inboxCreatedBy = inboxCreatedBy;
    }

    public String getSlab()
    {
        return slab;
    }

    public void setSlab( String slab )
    {
        this.slab = slab;
    }

    public String getTicketStatus()
    {
        return ticketStatus;
    }

    public void setTicketStatus( String ticketStatus )
    {
        this.ticketStatus = ticketStatus;
    }

    public Date getRaisedDate()
    {
        return raisedDate;
    }

    public void setRaisedDate( Date raisedDate )
    {
        this.raisedDate = raisedDate;
    }

    public String getRaiseUser()
    {
        return raiseUser;
    }

    public void setRaiseUser( String raiseUser )
    {
        this.raiseUser = raiseUser;
    }

    public Date getSrResolvedOn()
    {
        return srResolvedOn;
    }

    public void setSrResolvedOn( Date srResolvedOn )
    {
        this.srResolvedOn = srResolvedOn;
    }

    public Date getSrReopenedOn()
    {
        return srReopenedOn;
    }

    public void setSrReopenedOn( Date srReopenedOn )
    {
        this.srReopenedOn = srReopenedOn;
    }

    public String getResolvedBy()
    {
        return resolvedBy;
    }

    public void setResolvedBy( String resolvedBy )
    {
        this.resolvedBy = resolvedBy;
    }

    public String getResolutionCode()
    {
        return resolutionCode;
    }

    public void setResolutionCode( String resolutionCode )
    {
        this.resolutionCode = resolutionCode;
    }

    public String getRca()
    {
        return rca;
    }

    public void setRca( String rca )
    {
        this.rca = rca;
    }

    public String getAttributedTo()
    {
        return attributedTo;
    }

    public void setAttributedTo( String attributedTo )
    {
        this.attributedTo = attributedTo;
    }

    public String getFieldVisit()
    {
        return fieldVisit;
    }

    public void setFieldVisit( String fieldVisit )
    {
        this.fieldVisit = fieldVisit;
    }

    public String getBinName()
    {
        return binName;
    }

    public void setBinName( String binName )
    {
        this.binName = binName;
    }

    public String getCallingNo()
    {
        return callingNo;
    }

    public void setCallingNo( String callingNo )
    {
        this.callingNo = callingNo;
    }

    public String getLatestRemarks()
    {
        return latestRemarks;
    }

    public void setLatestRemarks( String latestRemarks )
    {
        this.latestRemarks = latestRemarks;
    }

    public String getAction()
    {
        return action;
    }

    public void setAction( String inAction )
    {
        action = inAction;
    }

    public String getExtendedDays()
    {
        return extendedDays;
    }

    public void setExtendedDays( String inExtendedDays )
    {
        extendedDays = inExtendedDays;
    }

    public Long getSlabTime()
    {
        return slabTime;
    }

    public void setSlabTime( Long inSlabTime )
    {
        slabTime = inSlabTime;
    }

    public String getFirstAssignUser()
    {
        return firstAssignUser;
    }

    public void setFirstAssignUser( String inFirstAssignUser )
    {
        firstAssignUser = inFirstAssignUser;
    }

    public String getTotDurAssignTime()
    {
        return totDurAssignTime;
    }

    public String getTotDurRemarkTime()
    {
        return totDurRemarkTime;
    }

    public void setTotDurAssignTime( String inTotDurAssignTime )
    {
        totDurAssignTime = inTotDurAssignTime;
    }

    public void setTotDurRemarkTime( String inTotDurRemarkTime )
    {
        totDurRemarkTime = inTotDurRemarkTime;
    }

    public String getRepeatTicketId()
    {
        return repeatTicketId;
    }

    public void setRepeatTicketId( String inRepeatTicketId )
    {
        repeatTicketId = inRepeatTicketId;
    }

    public String getResolutionType()
    {
        return resolutionType;
    }

    public void setResolutionType( String inResolutionType )
    {
        resolutionType = inResolutionType;
    }

    public String getTotalPendingHours()
    {
        return totalPendingHours;
    }

    public void setTotalPendingHours( String totalPendingHours )
    {
        this.totalPendingHours = totalPendingHours;
    }

    public String getPendingCurrentBinHours()
    {
        return pendingCurrentBinHours;
    }

    public void setPendingCurrentBinHours( String pendingCurrentBinHours )
    {
        this.pendingCurrentBinHours = pendingCurrentBinHours;
    }

    public String getPendingCurrentUserHours()
    {
        return pendingCurrentUserHours;
    }

    public void setPendingCurrentUserHours( String pendingCurrentUserHours )
    {
        this.pendingCurrentUserHours = pendingCurrentUserHours;
    }

    public String getResolutionTime()
    {
        return resolutionTime;
    }

    public void setResolutionTime( String resolutionTime )
    {
        this.resolutionTime = resolutionTime;
    }

    public BigInteger getReopenCount()
    {
        return reopenCount;
    }

    public void setReopenCount( BigInteger reopenCount )
    {
        this.reopenCount = reopenCount;
    }

    public String getSlaStatus()
    {
        return slaStatus;
    }

    public void setSlaStatus( String inSlaStatus )
    {
        slaStatus = inSlaStatus;
    }

    public String getConfiguredSLA()
    {
        return configuredSLA;
    }

    public void setConfiguredSLA( String inConfiguredSLA )
    {
        configuredSLA = inConfiguredSLA;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "QrcTicketReportPojo [qrcType=" ).append( qrcType ).append( ", category=" ).append( category )
                .append( ", subCategory=" ).append( subCategory ).append( ", subSubCategory=" ).append( subSubCategory )
                .append( ", currentBin=" ).append( currentBin ).append( "]" );
        return builder.toString();
    }

    public String getStatusOfTicket()
    {
        return statusOfTicket;
    }

    public void setStatusOfTicket( String inStatusOfTicket )
    {
        statusOfTicket = inStatusOfTicket;
    }
}
