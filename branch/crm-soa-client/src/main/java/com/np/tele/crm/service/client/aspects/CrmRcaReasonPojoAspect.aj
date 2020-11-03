package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmRcaReasonPojo;
import com.np.tele.crm.utils.StringUtils;

public aspect CrmRcaReasonPojoAspect
{
    public int CrmRcaReasonPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getCategory() == null ) ? 0 : this.getCategory().hashCode() );
        result = prime * result + ( ( this.getSubCategory() == null ) ? 0 : this.getSubCategory().hashCode() );
        if ( StringUtils.isValidObj( this.getSubSubCategory() ) )
            result = prime * result + ( ( this.getSubSubCategory() == null ) ? 0 : this.getSubSubCategory().hashCode() );
        result = prime * result + ( ( this.getCategoryValue() == null ) ? 0 : this.getCategoryValue().hashCode() );
        return result;
    }

    public boolean CrmRcaReasonPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmRcaReasonPojo other = (CrmRcaReasonPojo) obj;
        if ( this.getCategory() == null )
        {
            if ( other.getCategory() != null )
                return false;
        }
        else if ( !this.getCategory().equals( other.getCategory() ) )
            return false;
        if ( this.getSubCategory() == null )
        {
            if ( other.getSubCategory() != null )
                return false;
        }
        else if ( !this.getSubCategory().equals( other.getSubCategory() ) )
            return false;
        if ( StringUtils.isValidObj( this.getSubSubCategory() ) )
        {
            if ( this.getSubSubCategory() == null )
            {
                if ( other.getSubSubCategory() != null )
                    return false;
            }
            else if ( !this.getSubSubCategory().equals( other.getSubSubCategory() ) )
                return false;
        }
        if ( this.getCategoryValue() == null )
        {
            if ( other.getCategoryValue() != null )
                return false;
        }
        else if ( !this.getCategoryValue().equalsIgnoreCase( other.getCategoryValue() ) )
            return false;
        return true;
    }

    public String CrmRcaReasonPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmRcaReason [category=" ).append( this.getCategory() ).append( ", categoryId=" )
                .append( this.getCategoryId() ).append( ", categoryValue=" ).append( this.getCategoryValue() )
                .append( ", status=" ).append( this.getStatus() ).append( ", subCategory=" )
                .append( this.getSubCategory() ).append( ", subSubCategory=" ).append( this.getSubSubCategory() )
                .append( "]" );
        return builder.toString();
    }
}
