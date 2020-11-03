package com.np.tele.crm.exceptions;

public class DuplicateTransactionException
    extends Exception
{
    private static final long serialVersionUID = 2061883781892935307L;
    private String            transactionId    = null;

    public DuplicateTransactionException( String inStatusCode )
    {
        super( inStatusCode );
    }

    public DuplicateTransactionException( String inStatusCode, String inTransactionId )
    {
        super( inStatusCode );
        this.transactionId = inTransactionId;
    }

    public String getTransactionId()
    {
        return transactionId;
    }
}
