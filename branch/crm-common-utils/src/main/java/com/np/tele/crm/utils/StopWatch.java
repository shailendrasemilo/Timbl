package com.np.tele.crm.utils;

public class StopWatch
{
    long   start = System.currentTimeMillis();
    long   stop  = 0;
    String title = null;

    public StopWatch( String title )
    {
        this.title = title;
    }

    public long start()
    {
        start = System.currentTimeMillis();
        stop = 0;
        return start;
    }

    public long stop()
    {
        stop = System.currentTimeMillis();
        return stop;
    }

    public long diff()
    {
        if ( stop == 0 )
        {
            return ( System.currentTimeMillis() - start );
        }
        return ( stop - start );
    }

    public String toString()
    {
        StringBuffer buffer = new StringBuffer( title );
        buffer.append( " : Time Taken = " ).append( diff() ).append( " ms." );
        return buffer.toString();
    }
}
