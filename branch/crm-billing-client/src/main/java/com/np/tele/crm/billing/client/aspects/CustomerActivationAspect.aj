package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.CustomerActivation;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect CustomerActivationAspect
{
    public String CustomerActivation.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CustomerActivation [crfNo=" ).append( BillingUtils.convertToJavaObject( this.getCrfNo() ) )
                .append( ", deviceOwner=" ).append( BillingUtils.convertToJavaObject( this.getDeviceOwner() ) )
                .append( ", dynamicIpPool=" ).append( BillingUtils.convertToJavaObject( this.getDynamicIpPool() ) )
                .append( ", ftApprovalDate=" ).append( this.getFtApprovalDate() ).append( ", nasPortId=" )
                .append( BillingUtils.convertToJavaObject( this.getNasPortId() ) ).append( ", noOfWiringPoints=" )
                .append( this.getNoOfWiringPoints() ).append( ", option82=" )
                .append( BillingUtils.convertToJavaObject( this.getOption82() ) ).append( ", password=" )
                .append( BillingUtils.convertToJavaObject( this.getPassword() ) ).append( ", primaryMacId=" )
                .append( BillingUtils.convertToJavaObject( this.getPrimaryMacId() ) ).append( ", resellerNWPartner=" )
                .append( BillingUtils.convertToJavaObject( this.getResellerNWPartner() ) ).append( ", secondaryMacId=" )
                .append( BillingUtils.convertToJavaObject( this.getSecondaryMacId() ) ).append( ", staticIp=" )
                .append( BillingUtils.convertToJavaObject( this.getStaticIp() ) ).append( ", userName=" )
                .append( BillingUtils.convertToJavaObject( this.getUserName() ) ).append( ", chargeName=" )
                .append( BillingUtils.convertToJavaObject( this.getChargename() ) ).append( ", chargeAmmount=" )
                .append( this.getChargeamount() ).append( "]" );
        return builder.toString();
    }
}
