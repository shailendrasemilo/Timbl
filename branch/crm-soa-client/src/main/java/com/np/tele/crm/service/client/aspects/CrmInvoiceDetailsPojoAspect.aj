package com.np.tele.crm.service.client.aspects;

import com.np.tele.crm.service.client.CrmInvoiceDetailsPojo;

public aspect CrmInvoiceDetailsPojoAspect
{
    public int CrmInvoiceDetailsPojo.hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( this.getBillNumber() == null ) ? 0 : this.getBillNumber().hashCode() );
        result = prime * result + ( ( this.getCustomerId() == null ) ? 0 : this.getCustomerId().hashCode() );
        return result;
    }

    public boolean CrmInvoiceDetailsPojo.equals( Object obj )
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        CrmInvoiceDetailsPojo other = (CrmInvoiceDetailsPojo) obj;
        if ( this.getBillNumber() == null )
        {
            if ( other.getBillNumber() != null )
                return false;
        }
        else if ( !this.getBillNumber().equals( other.getBillNumber() ) )
            return false;
        if ( this.getCustomerId() == null )
        {
            if ( other.getCustomerId() != null )
                return false;
        }
        else if ( !this.getCustomerId().equals( other.getCustomerId() ) )
            return false;
        return true;
    }

    public String CrmInvoiceDetailsPojo.toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append( "CrmInvoiceDetailsPojo [billAmount=" ).append( this.getBillAmount() ).append( ", billDate=" )
                .append( this.getBillDate() ).append( ", billNumber=" ).append( this.getBillNumber() )
                .append( ", billPeriod=" ).append( this.getBillPeriod() ).append( ", customerId=" )
                .append( this.getCustomerId() ).append( ", dueDate=" ).append( this.getDueDate() )
                .append( ", nopasswordPdfFile=" ).append( this.getNopasswordPdfFile() ).append( ", passwordPdfFile=" )
                .append( this.getPasswordPdfFile() ).append( "]" );
        return builder.toString();
    }
}
