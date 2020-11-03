package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.ChangeDeviceDetail;

import com.np.tele.crm.billing.utils.BillingUtils;

public aspect ChangeDeviceDetailAspect
{
    public String ChangeDeviceDetail.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "ChangeDeviceDetail [cpePassword=" )
                .append( BillingUtils.convertToJavaObject( this.getCpePassword() ) ).append( ", customerNo=" )
                .append( this.getCustomerNo() ).append( ", deviceOwnership=" )
                .append( BillingUtils.convertToJavaObject( this.getDeviceOwnership() ) ).append( ", dynamicPool=" )
                .append( BillingUtils.convertToJavaObject( this.getDynamicPool() ) ).append( ", nasPortId=" )
                .append( BillingUtils.convertToJavaObject( this.getNasPortId() ) ).append( ", option82=" )
                .append( BillingUtils.convertToJavaObject( this.getOption82() ) ).append( ", primaryMacId=" )
                .append( BillingUtils.convertToJavaObject( this.getPrimaryMacId() ) ).append( ", secondaryMacId=" )
                .append( BillingUtils.convertToJavaObject( this.getSecondaryMacId() ) ).append( ", serviceModel=" )
                .append( BillingUtils.convertToJavaObject( this.getServiceModel() ) ).append( ", staticIp=" )
                .append( BillingUtils.convertToJavaObject( this.getStaticIp() ) ).append( ", macFaulty=" )
                .append( this.isMACFaultyStatus() ).append( ", userName=" )
                .append( BillingUtils.convertToJavaObject( this.getUserName() ) ).append( "]" );
        return builder.toString();
    }
}
