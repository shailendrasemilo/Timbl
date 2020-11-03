package com.np.tele.crm.exceptions;

public class NextraCrmException
    extends Exception
{
    /**
     * 
     */
    private static final long serialVersionUID = -7060666759173157538L;

    public NextraCrmException( String inStatusCode )
    {
        super( inStatusCode );
    }
}
