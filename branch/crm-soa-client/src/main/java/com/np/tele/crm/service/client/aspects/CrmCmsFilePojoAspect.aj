package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmCmsFilePojo;

public aspect CrmCmsFilePojoAspect
{
    public int CrmCmsFilePojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getCmsFileName() == null ) ? 0 : this.getCmsFileName().hashCode() );
        result = prime * result + ( ( this.getCmsFileType() == null ) ? 0 : this.getCmsFileType().hashCode() );
        return result;
    }

    public boolean CrmCmsFilePojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmCmsFilePojo other = (CrmCmsFilePojo) obj;
        if ( this.getCmsFileName() == null )
        {
            if ( other.getCmsFileName() != null )
                return false;
        }
        else if ( !this.getCmsFileName().equals( other.getCmsFileName() ) )
            return false;
        if ( this.getCmsFileType() == null )
        {
            if ( other.getCmsFileType() != null )
                return false;
        }
        else if ( !this.getCmsFileType().equals( other.getCmsFileType() ) )
            return false;
        return true;
    }

    public String CrmCmsFilePojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmCmsFilePojo [cmsFileId=" ).append( this.getCmsFileId() ).append( ", cmsFileName=" )
                .append( this.getCmsFileName() ).append( ", cmsFileType=" ).append( this.getCmsFileType() )
                .append( ", createdBy=" ).append( this.getCreatedBy() ).append( ", createdTime=" )
                .append( this.getCreatedTime() ).append( ", failReason=" ).append( this.getFailReason() )
                .append( ", status=" ).append( this.getStatus() ).append( "]" );
        return builder.toString();
    }
}
