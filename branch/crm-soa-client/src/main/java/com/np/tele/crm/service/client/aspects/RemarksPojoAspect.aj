package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.RemarksPojo;

public aspect RemarksPojoAspect
{
    public String RemarksPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "RemarksPojoAspect [actions=" ).append( this.getActions() ).append( ", createdBy=" )
                .append( this.getCreatedBy() ).append( ", mappingId=" ).append( this.getMappingId() )
                .append( ", reasonId=" ).append( this.getReasonId() ).append( ", remarkId=" )
                .append( this.getRemarkId() ).append( ", remarks=" ).append( this.getRemarks() ).append( "]" );
        return builder.toString();
    }
}
