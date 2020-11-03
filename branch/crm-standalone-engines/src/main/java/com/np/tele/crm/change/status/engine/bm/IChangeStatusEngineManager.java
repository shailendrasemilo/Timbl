package com.np.tele.crm.change.status.engine.bm;

public interface IChangeStatusEngineManager
{
    boolean changeCustomersStatus( String inAuthority, byte inMonthDifference, int inProcessChunkSize );
}
