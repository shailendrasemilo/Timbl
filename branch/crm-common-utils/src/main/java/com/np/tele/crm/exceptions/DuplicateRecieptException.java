package com.np.tele.crm.exceptions;

public class DuplicateRecieptException
    extends Exception
{
    private String            receiptNo        = null;
    /**
     * 
     */
    private static final long serialVersionUID = -4933584318613436881L;

    public DuplicateRecieptException( String inStatusCode )
    {
        super( inStatusCode );
    }

    public DuplicateRecieptException( String inStatusCode, String inReceiptNo )
    {
        super( inStatusCode );
        this.receiptNo = inReceiptNo;
    }

    public String getReceiptNo()
    {
        return receiptNo;
    }
}
