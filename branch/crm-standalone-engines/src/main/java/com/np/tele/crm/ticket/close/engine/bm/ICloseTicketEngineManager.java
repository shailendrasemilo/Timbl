package com.np.tele.crm.ticket.close.engine.bm;

public interface ICloseTicketEngineManager
{
    boolean closeResolvedTickets( String inAuthority, byte inHourDifference, int inProcessChunkSize);
}
