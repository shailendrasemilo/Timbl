package com.np.tele.crm.faultRepair.businessmgr;

import org.apache.log4j.Logger;

import com.np.tele.crm.external.trigger.dao.IExternalTriggerDao;
import com.np.tele.crm.utils.StringUtils;

public class QRCFaultRepairMgrImpl
    implements IQRCFaultRepairMgr
{
    private static final Logger LOGGER             = Logger.getLogger( QRCFaultRepairMgrImpl.class );
    private IExternalTriggerDao externalTriggerDao = null;

    public IExternalTriggerDao getExternalTriggerDao()
    {
        return externalTriggerDao;
    }

    public void setExternalTriggerDao( IExternalTriggerDao inExternalTriggerDao )
    {
        externalTriggerDao = inExternalTriggerDao;
    }

    @Override
    public void qrcFaultRepair( String inTo, String inMessage, String inOprator, String inSender, String inDate )
    {
        LOGGER.info( "QRCFaultRepairMgrImpl : qrcFaultRepair call" );
        long recordId = 0l;
        boolean returnValue = false;
        String srId = null;
        try
        {
            if ( StringUtils.isNotBlank( inMessage ) )
            {
                String msg[] = inMessage.split( ":" );
                srId = StringUtils.removeEnd( msg[1], "NP" );
                LOGGER.info( "SR ID:" + srId );
                recordId = getExternalTriggerDao().saveFirstRequestValue( inTo, inMessage, inOprator, inSender, inDate );
                LOGGER.info( "First Request Record ID : " + recordId );
                if ( recordId > 0 )
                {
                    returnValue = getExternalTriggerDao().updateSRTicket( StringUtils.trim( srId ) ,inMessage,inSender);
                    LOGGER.info( "Getting return value :" + returnValue );
                    if ( returnValue )
                    {
                        getExternalTriggerDao().updateFirstRequestValue( recordId );
                    }
                }
            }
        }
        catch ( Exception ex )
        {
            LOGGER.info( "Exception occured while getting Ticket ID", ex.getCause() );
        }
    }
}
