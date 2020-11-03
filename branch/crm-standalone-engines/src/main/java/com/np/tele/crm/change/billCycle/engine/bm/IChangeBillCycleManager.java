package com.np.tele.crm.change.billCycle.engine.bm;

public interface IChangeBillCycleManager
{
    boolean changeCustomersBillCycle( String inAuthority, int inProcessChunkSize, String inProcessingDate );
}
