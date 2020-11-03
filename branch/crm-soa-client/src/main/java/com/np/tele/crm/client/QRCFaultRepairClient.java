package com.np.tele.crm.client;

import com.np.tele.crm.constants.ClientOperations;
import com.np.tele.crm.service.client.QRCFaultRepairService;
import com.np.tele.crm.service.client.QRCFaultRepairServiceService;
import com.np.tele.crm.service.client.SOAPException_Exception;

@ClientOperations
public class QRCFaultRepairClient
    implements QRCFaultRepairService
{
    @Override
    public void qrcFaultRepair( String inTo, String inMessage, String inOprator, String inSender, String inDate )
        throws SOAPException_Exception
    {
        QRCFaultRepairServiceService faultRepairService = new QRCFaultRepairServiceService();
        QRCFaultRepairService repairService = faultRepairService.getQRCFaultRepairServicePort();
        repairService.qrcFaultRepair( inTo, inMessage, inOprator, inSender, inDate );
    }
}
