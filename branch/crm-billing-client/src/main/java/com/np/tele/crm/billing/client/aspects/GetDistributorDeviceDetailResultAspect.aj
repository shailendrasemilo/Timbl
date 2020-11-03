package com.np.tele.crm.billing.client.aspects;

import org.datacontract.schemas._2004._07.GetDistributorDeviceDetailResult;

public aspect GetDistributorDeviceDetailResultAspect
{
    public String GetDistributorDeviceDetailResult.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "GetDistributorDeviceDetailResult [deviceName=" ).append( this.getDeviceName().getValue() )
                .append( ", distributor=" ).append( this.getDistributor().getValue() ).append( ", mfgUniqueId=" )
                .append( this.getMfgUniqueId().getValue() ).append( ", modelNo=" )
                .append( this.getModelNo().getValue() ).append( ", serialNo=" ).append( this.getSerialNo().getValue() )
                .append( ", softwareVersion=" ).append( this.getSoftwareVersion().getValue() ).append( "]" );
        return builder.toString();
    }
}
