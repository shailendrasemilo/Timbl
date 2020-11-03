package com.np.tele.crm.external.trigger.dao;

import com.np.tele.crm.dto.TriggerRequestDto;
import com.np.tele.crm.dto.TriggerResponseDto;
import com.np.tele.crm.ext.pojos.CustomerDetailsPojo;
import com.np.tele.crm.ext.pojos.PrepaidPaymentPojo;
import com.np.tele.crm.ext.pojos.QrcTicketPojo;
import com.np.tele.crm.pojos.CrmBillingPlanRequestPojo;

public interface IExternalTriggerDao
{
    TriggerResponseDto changeCustomerStatus( CustomerDetailsPojo inCustomerDetailsPojo,
                                             TriggerRequestDto inTriggerRequestDto,
                                             TriggerResponseDto inResponseDto );

    TriggerResponseDto generateQRCTicket( QrcTicketPojo inQrcTicketPojo,
                                          TriggerRequestDto inTriggerRequestDto,
                                          TriggerResponseDto inResponseDto );

    TriggerResponseDto postPrepaidPayment( PrepaidPaymentPojo inPrepaidPaymentPojo,
                                           TriggerRequestDto inTriggerRequestDto,
                                           TriggerResponseDto inResponseDto );

    boolean updateSRTicket( String inSRID, String inMessage, String inSender );

    Long saveFirstRequestValue( String inTo, String inMessage, String inOprator, String inSender, String inDate );

    void updateFirstRequestValue( Long inRecordId );

    TriggerResponseDto changePlanStatus( CrmBillingPlanRequestPojo inBillingPlanRequestPojo,
                                         TriggerRequestDto inTriggerRequestDto,
                                         TriggerResponseDto inResponseDto );
}
